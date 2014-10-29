package com.action;

import com.bean.ApplicantAttachments;
import com.bean.ApplicantComment;
import com.bean.ApplicantOtherDetails;
import com.bean.ApplicantProfilePhoto;
import com.bean.JobApplicant;
import com.bean.JobRequisition;
import com.bean.Organization;
import com.bean.User;
import com.bean.UserGroup;
import com.bo.ApplicantBO;
import com.bo.BOFactory;
import com.bo.JobRequistionBO;
import com.bo.LovBO;
import com.common.Common;
import com.dao.ApplicantUserDAO;
import com.form.ApplicantForm;
import com.form.VariableDataCaptureUtil;
import com.resources.Constant;
import com.util.DateUtil;
import com.util.FileExtractor;
import com.util.StringUtils;
import java.io.File;
import java.io.RandomAccessFile;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

public class AgencyOpsAction
  extends CommonAgencyAction
{
  protected static final Logger logger = Logger.getLogger(AgencyOpsAction.class);
  
  public ActionForward downloadApplicantDetailPdf(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside downloadApplicantDetailPdf method .. in ApplicantAction");
    
    String applicantid = request.getParameter("applicantid");
    String uuid = request.getParameter("secureid");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantForm applicantForm = (ApplicantForm)form;
    applicantForm.setUuid(uuid);
    User user1 = (User)request.getSession().getAttribute("user_data");
    try
    {
      applicantForm.setApplicantId(new Long(applicantid).longValue());
      logger.info(".. applicant id : " + applicantid);
      
      BOFactory.getApplicantBO().downloadpdf(applicantForm, request);
      return mapping.findForward("downloadPdf");
    }
    catch (Exception e)
    {
      String msg = Constant.getResourceStringValue("server.error", user1.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
    }
    return mapping.findForward("exception");
  }
  
  public ActionForward resumeDetailsajax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside resumeDetailsajax method");
    ApplicantForm applicantform = (ApplicantForm)form;
    
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    if (applicant != null)
    {
      ApplicantAttachments appattach = BOFactory.getApplicantBO().getApplicantAttachments(applicant.getApplicantId(), Common.APPLICANT_ATTACHMENT_TYPE_RESUME);
      if (appattach != null)
      {
        FileExtractor fileExt = new FileExtractor();
        String filePath = Constant.getValue("ATTACHMENT_PATH");
        filePath = filePath + File.separator + "applicantAttachment" + File.separator + appattach.getUuid() + File.separator;
        String content = fileExt.getFileContent(filePath + applicant.getResumename());
        
        applicantform.setResumedetails(content);
        applicantform.scribddocumentid = appattach.getScribddocumentid();
        applicantform.setScribddocumentkey(appattach.getScribddocumentkey());
      }
      else
      {
        applicantform.setResumedetails(applicant.getOtherdetails().getResumeDetails());
      }
    }
    return mapping.findForward("resumeDetailsajax");
  }
  
  public ActionForward uploadApplicantPhotoscr(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside uploadApplicantPhotoscr method ... ");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user1 = (User)request.getSession().getAttribute("agency_data");
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    ApplicantForm applicantform = (ApplicantForm)form;
    applicantform.setUuid(uuid);
    applicantform.setApplicantId(new Long(applicantId).longValue());
    return mapping.findForward("uploadApplicantPhoto");
  }
  
  public ActionForward uploadApplicantPhoto(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside uploadApplicantPhoto method ... ");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user1 = (User)request.getSession().getAttribute("agency_data");
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    
    ApplicantForm applicantform = (ApplicantForm)form;
    
    FormFile myFile = applicantform.getProfilePhoto();
    if (myFile != null)
    {
      String contentType = myFile.getContentType();
      String fileName = myFile.getFileName();
      int fileSize = myFile.getFileSize();
      byte[] fileData = myFile.getFileData();
      
      ApplicantProfilePhoto pp = new ApplicantProfilePhoto();
      Blob blob = null;
      
      blob = new SerialBlob(fileData);
      pp.setProfilePhoto(blob);
      
      pp.setUpdatedBy(user1.getUserName());
      pp.setUpdatedDate(new Date());
      if (applicant.getProfilePhotoId() > 0L) {
        pp.setProfilePhotoId(applicant.getProfilePhotoId());
      }
      pp = BOFactory.getApplicantBO().saveApplicantProfilePhoto(pp);
      
      applicant.setProfilePhotoId(pp.getProfilePhotoId());
      
      BOFactory.getApplicantBO().updateApplicant(applicant);
    }
    applicantform.setUuid(uuid);
    request.setAttribute("uploadApplicantPhoto", "yes");
    return mapping.findForward("uploadApplicantPhoto");
  }
  
  public ActionForward applicantDetailsForTree(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside applicantDetailsForTree method");
    ApplicantForm applicantform = (ApplicantForm)form;
    

    String uuid = request.getParameter("secureid");
    
    JobApplicant applicanttemp = BOFactory.getApplicantBO().getApplicantIdNameEmailByUUID(uuid);
    JobApplicant applicant = BOFactory.getApplicantBO().requistionApplicantDetailstree(applicantform, request, String.valueOf(applicanttemp.getApplicantId()), uuid);
    if (applicant != null)
    {
      applicantform.fromValue(applicant, request);
      applicantform.setScreenCode(applicant.getScreenCode());
      BOFactory.getApplicantBO().fromValueCustomVariablesAllForApplicant(request, applicantform);
    }
    return mapping.findForward("applicantDetailsForTree");
  }
  
  public ActionForward applicantDetailsAg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside applicantDetailsAg method");
    ApplicantForm applicantform = (ApplicantForm)form;
    

    String uuid = request.getParameter("secureid");
    
    JobApplicant applicanttemp = BOFactory.getApplicantBO().getApplicantIdNameEmailByUUID(uuid);
    JobApplicant applicant = BOFactory.getApplicantBO().requistionApplicantDetailstree(applicantform, request, String.valueOf(applicanttemp.getApplicantId()), uuid);
    if (applicant != null)
    {
      applicantform.fromValue(applicant, request);
      applicantform.setScreenCode(applicant.getScreenCode());
      BOFactory.getApplicantBO().fromValueCustomVariablesAllForApplicant(request, applicantform);
    }
    return mapping.findForward("applicantDetailsAg");
  }
  
  public ActionForward home(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside home method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    return mapping.findForward("success");
  }
  
  public ActionForward searchownapplicantinit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchownapplicantinit method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantForm applicantform = (ApplicantForm)form;
    User user1 = (User)request.getSession().getAttribute("agency_data");
    long userid = user1.getUserId();
    List jobtitleList = BOFactory.getJobRequistionBO().getRequisitionsByAgency(userid);
    
    applicantform.setJobtitleList(jobtitleList);
    List interviewstatelist = Constant.getInterviewStates();
    applicantform.setInterviewstateList(interviewstatelist);
    
    return mapping.findForward("searchownapplicantinit");
  }
  
  public ActionForward searchownapplicant(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchownapplicant method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantForm applicantform = (ApplicantForm)form;
    User user1 = (User)request.getSession().getAttribute("agency_data");
    
    String previousOrganization = request.getParameter("previousOrganization");
    String applieddate = request.getParameter("applieddate");
    String cri = request.getParameter("cri");
    applicantform.setPreviousOrganization(previousOrganization);
    applicantform.setFullName(applicantform.getFullName());
    applicantform.setReferrerName(applicantform.getReferrerName());
    applicantform.setRequitionId(applicantform.getRequitionId());
    applicantform.setSearchapplieddate(applieddate);
    applicantform.setAppliedcri(cri);
    long userid = user1.getUserId();
    List jobtitleList = BOFactory.getJobRequistionBO().getRequisitionsByAgency(userid);
    


    applicantform.setJobtitleList(jobtitleList);
    List interviewstatelist = Constant.getInterviewStates();
    applicantform.setInterviewstateList(interviewstatelist);
    
    request.setAttribute("searchpagedisplay", "yes");
    return mapping.findForward("searchownapplicantinit");
  }
  
  public ActionForward applicantDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside applicantDetails method");
    ApplicantForm applicantform = (ApplicantForm)form;
    String backurl = request.getParameter("backurl");
    logger.info("backurlbackurl" + backurl);
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    
    ApplicantOtherDetails otherdetails = BOFactory.getApplicantBO().getApplicantOtherDetails(applicant.getApplicantId());
    
    applicant.setOtherdetails(otherdetails);
    if (applicant != null)
    {
      List attachlist = BOFactory.getApplicantBO().getApplicantAttachments(applicant.getApplicantId(), applicant.getUuid(), Common.APPLICANT_ATTACHMENT_TYPE_RESUME);
      if ((attachlist != null) && (attachlist.size() > 0)) {
        applicant.setApplicantAttachment((ApplicantAttachments)attachlist.get(0));
      }
      if ((applicant.getApplicantAttachment() != null) && (applicant.getApplicantAttachment().getAttachmentdata() != null) && (applicant.getResumename().length() > 0))
      {
        String filePath = Constant.getValue("ATTACHMENT_PATH");
        Blob blob = applicant.getApplicantAttachment().getAttachmentdata();
        File file = new File(filePath);
        if (!file.exists()) {
          file.mkdirs();
        }
        RandomAccessFile raf = new RandomAccessFile(filePath + applicant.getResumename(), "rw");
        
        int length = (int)blob.length();
        byte[] _blob = blob.getBytes(1L, length);
        raf.write(_blob);
        raf.close();
        
        filePath = applicant.getResumename();
        request.setAttribute("filePath", filePath);
      }
    }
    if (applicant != null)
    {
      applicantform.fromValue(applicant, request);
      applicantform.setScreenCode(applicant.getScreenCode());
      BOFactory.getApplicantBO().fromValueCustomVariablesAllForApplicant(request, applicantform);
      if ((applicant.getGender() != null) && (applicant.getGender().equals("M"))) {
        applicantform.setGender("Male");
      } else if ((applicant.getGender() != null) && (applicant.getGender().equals("F"))) {
        applicantform.setGender("Female");
      }
    }
    List eduList = BOFactory.getApplicantBO().getEducationsByApplicantAndType(new Long(applicantId).longValue(), Common.EDUCATION_COLLEGE);
    applicantform.setEducationsList(eduList);
    List certList = BOFactory.getApplicantBO().getEducationsByApplicantAndType(new Long(applicantId).longValue(), Common.EDUCATION_CERTIFICATION);
    applicantform.setCertificationsList(certList);
    List orgList = BOFactory.getApplicantBO().getPreviousOrgDetailsByApplicant(new Long(applicantId).longValue());
    applicantform.setPreviousOrgList(orgList);
    List appskillList = BOFactory.getApplicantBO().getSkillsByApplicant(new Long(applicantId).longValue());
    applicantform.setApplicantSkillList(appskillList);
    
    List comptetencyList = BOFactory.getJobRequistionBO().getJobRequisionTemplateComptetencyList(applicant.getReqId(), "job");
    List accomplishmentList = BOFactory.getJobRequistionBO().getJobRequisionTemplateAccomplishmentList(applicant.getReqId(), "job");
    applicantform.setAccomplishmentList(accomplishmentList);
    applicantform.setComptetencyList(comptetencyList);
    

    applicantform.setBackUrl(backurl);
    return mapping.findForward("applicantDetails");
  }
  
  public ActionForward additionalDetailsajax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside additionalDetailsajax method");
    ApplicantForm applicantform = (ApplicantForm)form;
    
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    
    BOFactory.getApplicantBO().additionalDetailsajax(applicantform, applicantId, uuid);
    
    return mapping.findForward("additionalDetailsajax");
  }
  
  public ActionForward applicantlogdetailsajax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside applicantlogdetailsajax method");
    ApplicantForm applicantform = (ApplicantForm)form;
    
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    JobApplicant applicant = BOFactory.getApplicantBO().applicantlogdetailsajax(applicantform, applicantId, uuid);
    if (applicant != null) {
      applicantform.fromValue(applicant, request);
    }
    return mapping.findForward("applicantlogdetailsajax");
  }
  
  public ActionForward createApplicantPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside createApplicantPage method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user1 = (User)request.getSession().getAttribute("agency_data");
    ApplicantForm applicantform = (ApplicantForm)form;
    
    String requitionId = request.getParameter("requistionId");
    if (!StringUtils.isNullOrEmpty(requitionId))
    {
      JobRequisition jobreq = BOFactory.getJobRequistionBO().getActiveOpenJobRequision(requitionId);
      if (jobreq != null)
      {
        applicantform.setRequitionId(jobreq.getJobreqId());
        applicantform.setJobTitle(jobreq.getJobTitle());
        String currencycode = jobreq.getOrganization().getCurrencyCode();
        applicantform.setCurrectctccurrencycode(currencycode);
        applicantform.setExpectedctccurrencycode(currencycode);
      }
    }
    BOFactory.getApplicantBO().commonLovPopulateOnCreate(applicantform, request, user1.getSuper_user_key());
    if ((user1.getType() != null) && (user1.getType().equals("Vendor")))
    {
      applicantform.setSourceTypeId(5);
      applicantform.setVendorId(user1.getUserId());
      applicantform.setHidesource("vendor");
    }
    String fromWhere = request.getParameter("frm");
    request.setAttribute("frm", fromWhere);
    request.setAttribute("applicantsaved", "no");
    return mapping.findForward("createApplicantPage");
  }
  
  public ActionForward saveapplicantData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveapplicantData method");
    User agency = (User)request.getSession().getAttribute("agency_data");
    try
    {
      ApplicantForm applicantform = (ApplicantForm)form;
      
      JobApplicant applicant = new JobApplicant();
      String requitionId = request.getParameter("reqid1");
      String jobTitle = request.getParameter("jobt1");
      String dateofbirth = request.getParameter("dateofbirth");
      String previousOrganization = request.getParameter("previousOrganization");
      String fromattachmentpage = request.getParameter("fromattachmentpage");
      List errorList = new ArrayList();
      if ((!StringUtils.isNullOrEmpty(fromattachmentpage)) && (fromattachmentpage.equals("yes")))
      {
        String applicantId = request.getParameter("applicantId");
        applicant = BOFactory.getApplicantBO().getApplicantDetails(applicantId);
        applicantform.fromValue(applicant, request);
        
        BOFactory.getApplicantBO().commonLovPopulate(request, applicantform, applicant, agency.getSuper_user_key());
        
        List formVariablesList = applicantform.getFormVariablesList();
        List formVariableDataList = applicantform.getFormVariableDataList();
        
        applicantform.setFormVariablesList(VariableDataCaptureUtil.mergeCustomVariableValues(formVariablesList, formVariableDataList, request));
      }
      else
      {
        applicantform.toValue(applicant, request);
        String attachmentsizeexceed = (String)request.getAttribute("attachmentsizeexceed");
        logger.info("request.getParameter request.getParameter");
        logger.info("applicantform.getSourceTypeId()" + applicantform.getSourceTypeId());
        
        String gender = request.getParameter("gender");
        logger.info(gender);
        if (!StringUtils.isNullOrEmpty(dateofbirth))
        {
          String datepattern = DateUtil.getDatePatternFormat(agency.getLocale());
          Calendar cal = DateUtil.convertStringDateToCalendar(dateofbirth, datepattern);
          
          applicant.setDateofbirth(cal.getTime());
        }
        logger.info(requitionId);
        logger.info(jobTitle);
        applicant.setReqId(new Long(requitionId).longValue());
        

        applicant.setGender(gender);
        JobRequisition jb = BOFactory.getJobRequistionBO().getJobRequision(requitionId);
        applicant.setJobTitle(jb.getJobreqName());
        applicant.setOwner(jb.getHiringmgr());
        applicant.setPreviousOrganization(previousOrganization);
        applicant.setUuid(UUID.randomUUID().toString());
        

        applicant.setCurrectctc(applicantform.getCurrectctc());
        applicant.setExpectedctc(applicantform.getExpectedctc());
        String currencycode = jb.getOrganization().getCurrencyCode();
        

        applicant.setCurrectctccurrencycode(currencycode);
        applicant.setExpectedctccurrencycode(currencycode);
        
        List formVariablesList = applicantform.getFormVariablesList();
        List formVariableDataList = applicantform.getFormVariableDataList();
        

        boolean isSuccess = true;
        JobApplicant oldapplicant = BOFactory.getApplicantBO().isApplicantDuplicate(applicant, agency.getSuper_user_key());
        if ((!StringUtils.isNullOrEmpty(attachmentsizeexceed)) && (attachmentsizeexceed.equals("yes")))
        {
          request.setAttribute("attachmentsizeexceed", "yes");
          isSuccess = false;
        }
        else if (oldapplicant != null)
        {
          request.setAttribute("oldapplicant", oldapplicant);
        }
        else
        {
          BOFactory.getApplicantBO().saveApplicant(applicant, null, agency, null, null, errorList, applicantform.getErrorListCustomVariable(), applicantform.getFormVariableDataList());
          
          errorList = applicant.getErrorList();
          if ((errorList != null) && (errorList.size() > 0)) {
            isSuccess = false;
          }
          if (isSuccess)
          {
            request.setAttribute("applicantsaved", "yes");
            request.setAttribute("applicantdatacustom", "yes");
          }
          else
          {
            logger.info("in error condition");
            request.setAttribute("error_list", errorList);
          }
        }
        applicantform.fromValue(applicant, request);
        
        BOFactory.getApplicantBO().commonLovPopulate(request, applicantform, applicant, agency.getSuper_user_key());
        if (!isSuccess)
        {
          logger.info("formVariablesList" + formVariablesList);
          applicantform.setFormVariablesList(VariableDataCaptureUtil.mergeCustomVariableValues(formVariablesList, formVariableDataList, request));
        }
      }
      return mapping.findForward("createApplicantPage");
    }
    catch (Exception e)
    {
      String msg = Constant.getResourceStringValue("server.error", agency.getLocale());
      if (e.getMessage() == null) {
        request.setAttribute(Common.ERROR_MSG, msg);
      } else {
        request.setAttribute(Common.ERROR_MSG, e.getMessage());
      }
    }
    return mapping.findForward("exception");
  }
  
  public ActionForward getcomments(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside getcomments method");
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    
    ApplicantForm applicantform = (ApplicantForm)form;
    
    List commentList = ApplicantUserDAO.getApplicantCommentsListVisibleToReferrer(new Long(applicantId).longValue(), uuid);
    

    applicantform.setCommentList(commentList);
    
    applicantform.setApplicantId(new Long(applicantId).longValue());
    applicantform.setUuid(uuid);
    
    applicantform.setCommentVisibleList(Constant.getCommentVisibleList());
    
    return mapping.findForward("add_text");
  }
  
  public ActionForward addcomment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside addcomment method");
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    String applicantcomments = request.getParameter("comment");
    User agency = (User)request.getSession().getAttribute("agency_data");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    ApplicantForm applicantform = (ApplicantForm)form;
    
    ApplicantComment comment = new ApplicantComment();
    comment.setApplicantId(new Long(applicantId).longValue());
    comment.setAppuuid(uuid);
    comment.setComment(applicantcomments);
    
    comment.setCreatedBy(agency.getFirstName());
    comment.setCreatedDate(new Date());
    comment.setBytype("Agency");
    comment.setIsVisibleToApplicant("R");
    ApplicantUserDAO.saveApplicantComment(comment);
    
    List tolist = new ArrayList();
    if ((!StringUtils.isEmpty(applicant.getIsGroup())) && (applicant.getIsGroup().equals("Y")))
    {
      long groupId = 0L;
      if (applicant.getOwnerGroup() != null) {
        groupId = applicant.getOwnerGroup().getUsergrpId();
      }
      UserGroup usrgrp = BOFactory.getLovBO().getUserGroup(groupId);
      Set users = usrgrp.getUsers();
      if ((users != null) && (users.size() > 0))
      {
        Iterator itr = users.iterator();
        while (itr.hasNext())
        {
          User touser = (User)itr.next();
          tolist.add(touser.getEmailId());
        }
      }
    }
    else
    {
      tolist.add(applicant.getOwner().getEmailId());
    }
    String[] to = new String[tolist.size()];
    tolist.toArray(to);
    
    BOFactory.getApplicantBO().commentEmail(agency.getEmailId(), to, applicant, applicantform.getComment());
    


    List commentList = ApplicantUserDAO.getApplicantCommentsListVisibleToReferrer(new Long(applicantId).longValue(), uuid);
    
    applicantform.setCommentList(commentList);
    
    applicantform.setComment("");
    applicantform.setApplicantId(new Long(applicantId).longValue());
    applicantform.setUuid(uuid);
    
    return mapping.findForward("add_text");
  }
  
  public ActionForward updateapplicantData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updateapplicantData method");
    User agency = (User)request.getSession().getAttribute("agency_data");
    ApplicantForm applicantform = (ApplicantForm)form;
    

    String requitionId = request.getParameter("reqid1");
    String jobTitle = request.getParameter("jobt1");
    String dateofbirth = request.getParameter("dateofbirth");
    String previousOrganization = request.getParameter("previousOrganization");
    String applicantId = request.getParameter("applicantId");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetails(applicantId);
    
    String rname = applicant.getResumename();
    applicantform.toValue(applicant, request);
    String attachmentsizeexceed = (String)request.getAttribute("attachmentsizeexceed");
    logger.info("request.getParameter request.getParameter");
    
    String gender = request.getParameter("gender");
    logger.info(gender);
    if (!StringUtils.isNullOrEmpty(dateofbirth))
    {
      String datepattern = DateUtil.getDatePatternFormat(agency.getLocale());
      Calendar cal = DateUtil.convertStringDateToCalendar(dateofbirth, datepattern);
      
      applicant.setDateofbirth(cal.getTime());
    }
    logger.info(requitionId);
    logger.info(jobTitle);
    applicant.setReqId(new Long(requitionId).longValue());
    applicant.setJobTitle(jobTitle);
    
    applicant.setGender(gender);
    JobRequisition jb = BOFactory.getJobRequistionBO().getJobRequision(requitionId);
    applicant.setOwner(jb.getHiringmgr());
    applicant.setPreviousOrganization(previousOrganization);
    logger.info("rname" + rname);
    logger.info("applicantform.getResumename()" + applicantform.getResumename());
    boolean isAttachmentupdata = true;
    if ((rname != null) && (StringUtils.isNullOrEmpty(applicantform.getResumename())))
    {
      applicant.setResumename(rname);
      isAttachmentupdata = false;
    }
    applicant.setCurrectctc(applicantform.getCurrectctc());
    applicant.setExpectedctc(applicantform.getExpectedctc());
    String currencycode = jb.getOrganization().getCurrencyCode();
    applicant.setCurrectctccurrencycode(currencycode);
    applicant.setExpectedctccurrencycode(currencycode);
    
    List formVariablesList = applicantform.getFormVariablesList();
    List formVariableDataList = applicantform.getFormVariableDataList();
    
    boolean isSuccess = true;
    List errorList = new ArrayList();
    if ((!StringUtils.isNullOrEmpty(attachmentsizeexceed)) && (attachmentsizeexceed.equals("yes")))
    {
      request.setAttribute("attachmentsizeexceed", "yes");
      isSuccess = false;
    }
    else
    {
      applicant = BOFactory.getApplicantBO().updateApplicant(applicant, null, agency, null, null, null, errorList, applicantform.getErrorListCustomVariable(), applicantform.getFormVariableDataList(), isAttachmentupdata);
      

      errorList = applicant.getErrorList();
      if ((errorList != null) && (errorList.size() > 0)) {
        isSuccess = false;
      }
    }
    if (isSuccess)
    {
      request.setAttribute("applicantupdated", "yes");
      request.setAttribute("applicantdatacustom", "yes");
    }
    else
    {
      logger.info("in error condition");
      request.setAttribute("error_list", errorList);
    }
    applicantform.fromValue(applicant, request);
    
    BOFactory.getApplicantBO().commonLovPopulate(request, applicantform, applicant, agency.getSuper_user_key());
    if (!isSuccess) {
      applicantform.setFormVariablesList(VariableDataCaptureUtil.mergeCustomVariableValues(formVariablesList, formVariableDataList, request));
    }
    return mapping.findForward("createApplicantPage");
  }
  
  public ActionForward editapplicant(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside editapplicant method");
    User user1 = (User)request.getSession().getAttribute("agency_data");
    ApplicantForm applicantform = (ApplicantForm)form;
    
    String applicantId = request.getParameter("applicantId");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetails(applicantId);
    List attachlist = BOFactory.getApplicantBO().getApplicantAttachments(applicant.getApplicantId(), applicant.getUuid(), Common.APPLICANT_ATTACHMENT_TYPE_RESUME);
    if ((attachlist != null) && (attachlist.size() > 0)) {
      applicant.setApplicantAttachment((ApplicantAttachments)attachlist.get(0));
    }
    if ((applicant.getApplicantAttachment() != null) && (applicant.getApplicantAttachment().getAttachmentdata() != null) && (applicant.getResumename().length() > 0))
    {
      String filePath = Constant.getValue("ATTACHMENT_PATH");
      Blob blob = applicant.getApplicantAttachment().getAttachmentdata();
      File file = new File(filePath);
      if (!file.exists()) {
        file.mkdirs();
      }
      RandomAccessFile raf = new RandomAccessFile(filePath + applicant.getResumename(), "rw");
      
      int length = (int)blob.length();
      byte[] _blob = blob.getBytes(1L, length);
      raf.write(_blob);
      raf.close();
      
      filePath = applicant.getResumename();
      request.setAttribute("filePath", filePath);
    }
    applicantform.fromValue(applicant, request);
    
    BOFactory.getApplicantBO().commonLovPopulate(request, applicantform, applicant, user1.getSuper_user_key());
    


    applicantform.setCurrectctc(applicant.getCurrectctc());
    applicantform.setExpectedctc(applicant.getExpectedctc());
    applicantform.setCurrectctccurrencycode(applicant.getCurrectctccurrencycode());
    applicantform.setExpectedctccurrencycode(applicant.getExpectedctccurrencycode());
    if ((user1.getType() != null) && (user1.getType().equals("Vendor")))
    {
      applicantform.setSourceTypeId(5);
      applicantform.setVendorId(user1.getUserId());
      applicantform.setHidesource("vendor");
    }
    request.setAttribute("applicantsaved", "no");
    request.setAttribute("editmode", "yes");
    return mapping.findForward("createApplicantPage");
  }
  
  public ActionForward deleteResume(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteResume method");
    User user1 = (User)request.getSession().getAttribute("agency_data");
    
    ApplicantForm applicantform = (ApplicantForm)form;
    
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("uuid");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    if (applicant == null) {
      return null;
    }
    List attachlist = BOFactory.getApplicantBO().getApplicantAttachments(applicant.getApplicantId(), applicant.getUuid(), Common.APPLICANT_ATTACHMENT_TYPE_RESUME);
    if ((attachlist != null) && (attachlist.size() > 0)) {
      applicant.setApplicantAttachment((ApplicantAttachments)attachlist.get(0));
    }
    if ((applicant.getApplicantAttachment() != null) && (applicant.getResumename() != null) && (applicant.getResumename().length() > 0))
    {
      BOFactory.getApplicantBO().deleteResume(new Long(applicantId).longValue(), Common.APPLICANT_ATTACHMENT_TYPE_RESUME);
      
      applicant.setResumename(null);
      applicantform.setResumedetails("");
      BOFactory.getApplicantBO().updateApplicant(applicant);
    }
    logger.info("Inside deleteResume method");
    
    List jobtitleList = BOFactory.getJobRequistionBO().getJobTitlesActiveAndOpenListAssigedToUser(user1);
    

    applicantform.setJobtitleList(jobtitleList);
    applicantform.fromValue(applicant, request);
    
    BOFactory.getApplicantBO().commonLovPopulate(request, applicantform, applicant, user1.getSuper_user_key());
    


    applicantform.setCurrectctc(applicant.getCurrectctc());
    applicantform.setExpectedctc(applicant.getExpectedctc());
    applicantform.setCurrectctccurrencycode(applicant.getCurrectctccurrencycode());
    
    applicantform.setExpectedctccurrencycode(applicant.getExpectedctccurrencycode());
    

    BOFactory.getApplicantBO().commonSourceLovPopulate(applicantform, applicant);
    

    request.setAttribute("applicantsaved", "no");
    
    return mapping.findForward("createApplicantPage");
  }
}
