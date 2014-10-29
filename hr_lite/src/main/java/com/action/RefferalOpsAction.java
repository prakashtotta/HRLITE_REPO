package com.action;

import com.bean.ApplicantAttachments;
import com.bean.ApplicantComment;
import com.bean.ApplicantOtherDetails;
import com.bean.JobApplicant;
import com.bean.JobRequisition;
import com.bean.RefferalEmployee;
import com.bean.User;
import com.bean.UserGroup;
import com.bo.ApplicantBO;
import com.bo.BOFactory;
import com.bo.JobRequistionBO;
import com.bo.LovBO;
import com.common.Common;
import com.dao.ApplicantUserDAO;
import com.form.ApplicantForm;
import com.resources.Constant;
import com.util.FileExtractor;
import com.util.StringUtils;
import java.io.File;
import java.io.RandomAccessFile;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class RefferalOpsAction
  extends CommonRefAction
{
  protected static final Logger logger = Logger.getLogger(RefferalOpsAction.class);
  
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
    RefferalEmployee user1 = (RefferalEmployee)request.getSession().getAttribute("employee_refferal_data");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    ApplicantForm applicantform = (ApplicantForm)form;
    
    ApplicantComment comment = new ApplicantComment();
    comment.setApplicantId(new Long(applicantId).longValue());
    comment.setAppuuid(uuid);
    comment.setComment(applicantcomments);
    
    comment.setCreatedBy(user1.getEmployeename());
    comment.setCreatedDate(new Date());
    comment.setBytype("Employee Ref");
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
    
    BOFactory.getApplicantBO().commentEmail(user1.getEmployeeemail(), to, applicant, applicantform.getComment());
    


    List commentList = ApplicantUserDAO.getApplicantCommentsListVisibleToReferrer(new Long(applicantId).longValue(), uuid);
    
    applicantform.setCommentList(commentList);
    
    applicantform.setComment("");
    applicantform.setApplicantId(new Long(applicantId).longValue());
    applicantform.setUuid(uuid);
    
    return mapping.findForward("add_text");
  }
  
  public ActionForward searchownapplicantinit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchownapplicantinit method");
    RefferalEmployee empref = (RefferalEmployee)request.getSession().getAttribute("employee_refferal_data");
    logger.info("empref.getSuper_user_key() >> " + empref.getSuper_user_key());
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantForm applicantform = (ApplicantForm)form;
    List jobtitleList = BOFactory.getJobRequistionBO().getRequisitionsByRefferalPortal(empref.getSuper_user_key());
    List newjobtitleList = new ArrayList();
    for (int i = 0; i < jobtitleList.size(); i++)
    {
      JobRequisition jreq = (JobRequisition)jobtitleList.get(i);
      jreq.setJobTitle(jreq.getJobreqcode() + " # " + jreq.getJobTitle());
      
      newjobtitleList.add(jreq);
    }
    applicantform.setJobtitleList(newjobtitleList);
    
    request.setAttribute("applicantform", applicantform);
    return mapping.findForward("searchownapplicantinit");
  }
  
  public ActionForward searchownapplicant(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchownapplicant method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantForm applicantform = (ApplicantForm)form;
    String previousOrganization = request.getParameter("previousOrganization");
    String applieddate = request.getParameter("applieddate");
    String cri = request.getParameter("cri");
    applicantform.setPreviousOrganization(previousOrganization);
    applicantform.setFullName(applicantform.getFullName());
    applicantform.setReferrerName(applicantform.getReferrerName());
    applicantform.setRequitionId(applicantform.getRequitionId());
    applicantform.setSearchapplieddate(applieddate);
    applicantform.setAppliedcri(cri);
    List jobtitleList = BOFactory.getJobRequistionBO().getRequisitionsActiveList();
    List newjobtitleList = new ArrayList();
    for (int i = 0; i < jobtitleList.size(); i++)
    {
      JobRequisition jreq = (JobRequisition)jobtitleList.get(i);
      jreq.setJobTitle(jreq.getJobreqcode() + " # " + jreq.getJobTitle());
      
      newjobtitleList.add(jreq);
    }
    applicantform.setJobtitleList(newjobtitleList);
    
    request.setAttribute("searchpagedisplay", "yes");
    return mapping.findForward("searchownapplicantinit");
  }
  
  public ActionForward applicantDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside applicantDetails method");
    ApplicantForm applicantform = (ApplicantForm)form;
    
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
    
    return mapping.findForward("applicantDetails");
  }
  
  public ActionForward additionalDetailsajax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside additionalDetailsajax method");
    ApplicantForm applicantform = (ApplicantForm)form;
    
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    
    List eduList = BOFactory.getApplicantBO().getEducationsByApplicantAndType(new Long(applicantId).longValue(), Common.EDUCATION_COLLEGE);
    applicantform.setEducationsList(eduList);
    List certList = BOFactory.getApplicantBO().getEducationsByApplicantAndType(new Long(applicantId).longValue(), Common.EDUCATION_CERTIFICATION);
    applicantform.setCertificationsList(certList);
    List orgList = BOFactory.getApplicantBO().getPreviousOrgDetailsByApplicant(new Long(applicantId).longValue());
    applicantform.setPreviousOrgList(orgList);
    List appskillList = BOFactory.getApplicantBO().getSkillsByApplicant(new Long(applicantId).longValue());
    applicantform.setApplicantSkillList(appskillList);
    
    return mapping.findForward("additionalDetailsajax");
  }
  
  public ActionForward resumeDetailsajax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside resumeDetailsajax method ... new");
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
  
  public ActionForward applicantlogdetailsajax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside applicantlogdetailsajax method");
    ApplicantForm applicantform = (ApplicantForm)form;
    
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    if (applicant != null)
    {
      applicantform.fromValue(applicant, request);
      List eventList = BOFactory.getApplicantBO().getApplicationEventList(applicantId);
      logger.info("interview events size" + eventList.size());
      
      applicantform.setEventList(eventList);
    }
    return mapping.findForward("applicantlogdetailsajax");
  }
  
  public ActionForward downloadApplicantDetailPdf(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside downloadApplicantDetailPdf method .. in RefferalOpsAction");
    
    String applicantid = request.getParameter("applicantid");
    String uuid = request.getParameter("secureid");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantForm applicantForm = (ApplicantForm)form;
    applicantForm.setUuid(uuid);
    
    RefferalEmployee user1 = (RefferalEmployee)request.getSession().getAttribute("employee_refferal_data");
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
}
