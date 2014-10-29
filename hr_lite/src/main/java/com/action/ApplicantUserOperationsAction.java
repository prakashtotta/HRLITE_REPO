package com.action;

import com.bean.ActionsAttachment;
import com.bean.ApplicantComment;
import com.bean.ApplicantOfferAttachment;
import com.bean.ApplicantUser;
import com.bean.ApplicantUserActions;
import com.bean.JobApplicant;
import com.bean.JobRequisition;
import com.bean.RefferalEmployee;
import com.bean.User;
import com.bean.UserGroup;
import com.bo.ApplicantBO;
import com.bo.ApplicantTXBO;
import com.bo.BOFactory;
import com.bo.JobRequistionBO;
import com.bo.LovBO;
import com.bo.LovTXBO;
import com.common.AppContextUtil;
import com.common.Common;
import com.dao.ApplicantUserDAO;
import com.form.ApplicantForm;
import com.manager.EmailTaskManager;
import com.resources.Constant;
import com.util.DateUtil;
import com.util.EmailTask;
import com.util.StringUtils;
import java.io.File;
import java.io.RandomAccessFile;
import java.sql.Blob;
import java.util.ArrayList;
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
import org.springframework.context.ApplicationContext;

public class ApplicantUserOperationsAction
  extends CommonNoLoginAction
{
  protected static final Logger logger = Logger.getLogger(ApplicantUserOperationsAction.class);
  
  public ActionForward performactionscr(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside performactionscr method");
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    String actioname = request.getParameter("actionname");
    ApplicantUser user1 = (ApplicantUser)request.getSession().getAttribute("applicant_user_data");
    ApplicantForm applicantform = (ApplicantForm)form;
    
    logger.info("applicantId" + applicantId);
    logger.info("uuid" + uuid);
    logger.info("actioname" + actioname);
    ApplicantUserActions actionexist = ApplicantUserDAO.getApplicantAction(new Long(applicantId).longValue(), uuid, actioname);
    
    logger.info("actionexist" + actionexist);
    if (actionexist.getActionName().equals("experience_details_proof_attachment"))
    {
      List orgDetailsList = BOFactory.getApplicantBO().getPreviousOrgDetailsByApplicant(new Long(applicantId).longValue());
      applicantform.setPreviousOrgList(orgDetailsList);
      request.setAttribute("action_name", "experience_details_proof_attachment");
      return mapping.findForward("experience_details");
    }
    if (actionexist.getActionName().equals("education_details_proof_attachment"))
    {
      List eduList = BOFactory.getApplicantBO().getEducationsByApplicantAndType(new Long(applicantId).longValue(), Common.EDUCATION_COLLEGE);
      applicantform.setEducationsList(eduList);
      applicantform.setEducationNamesList(BOFactory.getLovTXBO().getEducationListKeyValue(user1.getApplicant().getSuper_user_key()));
      request.setAttribute("action_name", "education_details_proof_attachment");
      return mapping.findForward("education_details");
    }
    if (actionexist.getActionName().equals("certification_details_proof_attachment"))
    {
      List certList = BOFactory.getApplicantBO().getEducationsByApplicantAndType(new Long(applicantId).longValue(), Common.EDUCATION_CERTIFICATION);
      applicantform.setCertificationsList(certList);
      
      request.setAttribute("action_name", "certification_details_proof_attachment");
      return mapping.findForward("certification_details");
    }
    if (actionexist.getActionName().equals("add_text"))
    {
      List commentList = ApplicantUserDAO.getApplicantCommentsListVisibleToApplicant(new Long(applicantId).longValue(), uuid);
      applicantform.setCommentList(commentList);
      applicantform.setApplicantuseraction(actionexist);
      applicantform.setApplicantId(new Long(applicantId).longValue());
      applicantform.setUuid(uuid);
      request.setAttribute("action_name", "add_text");
      return mapping.findForward("add_text");
    }
    return null;
  }
  
  public ActionForward getcomments(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside getcomments method");
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    ApplicantUser user1 = (ApplicantUser)request.getSession().getAttribute("applicant_user_data");
    
    ApplicantForm applicantform = (ApplicantForm)form;
    


    List commentList = ApplicantUserDAO.getApplicantCommentsListVisibleToApplicant(new Long(applicantId).longValue(), uuid);
    
    applicantform.setCommentList(commentList);
    

    applicantform.setApplicantId(new Long(applicantId).longValue());
    applicantform.setUuid(uuid);
    
    return mapping.findForward("add_text");
  }
  
  public ActionForward addcomment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside addcomment method");
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    String applicantcomments = request.getParameter("comment");
    ApplicantUser user1 = (ApplicantUser)request.getSession().getAttribute("applicant_user_data");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    ApplicantForm applicantform = (ApplicantForm)form;
    
    ApplicantComment comment = new ApplicantComment();
    comment.setApplicantId(new Long(applicantId).longValue());
    comment.setAppuuid(uuid);
    String newComment = applicantcomments.replace("andSymbol", "&");
    String newComment2 = newComment.replace("hashSymbol", "#");
    comment.setComment(newComment2);
    
    comment.setCreatedBy(user1.getFullName());
    comment.setCreatedDate(new Date());
    comment.setBytype("APPLICANT");
    comment.setIsVisibleToApplicant("Y");
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
    

    List cclist = new ArrayList();
    cclist = BOFactory.getLovBO().getWatchersEmails(applicant, "APPLICANT");
    String[] bcc = null;
    




    String[] cc = new String[cclist.size()];
    cclist.toArray(cc);
    
    String replyTo = "\"" + applicant.getFullName() + "\"" + " " + "<" + applicant.getEmail() + ">";
    

    String htmlBody = applicantform.getComment();
    
    String[] arg = { applicant.getFullName(), String.valueOf(applicant.getApplicantId()) };
    String subject = Constant.getResourceStringValue("aquisition.applicant.comments.msg", user1.getLocale(), arg);
    
    EmailTask emailtask = new EmailTask(applicant.getEmail(), to, cc, bcc, replyTo, subject, null, htmlBody, null, 0, null);
    
    emailtask.setFunctionType(Common.ONLY_EMAIL_ONLY);
    EmailTaskManager.sendEmail(emailtask);
    


    List commentList = ApplicantUserDAO.getApplicantCommentsListVisibleToApplicant(new Long(applicantId).longValue(), uuid);
    
    applicantform.setCommentList(commentList);
    
    applicantform.setComment("");
    applicantform.setApplicantId(new Long(applicantId).longValue());
    applicantform.setUuid(uuid);
    
    return mapping.findForward("add_text");
  }
  
  public ActionForward deleteattachment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteattachment method");
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    String actioname = request.getParameter("actionname");
    String attachmentuuid = request.getParameter("uuid");
    ApplicantForm applicantform = (ApplicantForm)form;
    
    ApplicantUserDAO.deleteActionAttachment(attachmentuuid);
    try
    {
      String filePath = Constant.getValue("ATTACHMENT_PATH");
      filePath = filePath + File.separator + "actionAttachment" + File.separator + attachmentuuid + File.separator;
      File file = new File(filePath);
      if (file.isDirectory())
      {
        String[] files = file.list();
        if ((files != null) && (files.length > 0)) {
          for (int i = 0; i < files.length; i++)
          {
            String f1name = files[i];
            File f1 = new File(filePath + f1name);
            if (f1.exists()) {
              f1.delete();
            }
          }
        } else if (file.exists()) {
          file.delete();
        }
      }
      if (file.exists()) {
        file.delete();
      }
    }
    catch (Exception e)
    {
      logger.info("Exception on delete attachment file" + e.getMessage());
    }
    request.setAttribute("attachmentdeleted", "yes");
    
    return mapping.findForward("deleteattachment");
  }
  
  public ActionForward deleteattachmentpopup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteattachmentpopup method");
    String applicantId = request.getParameter("applicantId");
    String idvalue = request.getParameter("idvalue");
    String actioname = request.getParameter("actionname");
    String attachmentuuid = request.getParameter("uuid");
    ApplicantForm applicantform = (ApplicantForm)form;
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetails(applicantId);
    applicantform.setApplicantId(applicant.getApplicantId());
    applicantform.setUuid(applicant.getUuid());
    ApplicantUserDAO.deleteActionAttachment(attachmentuuid);
    try
    {
      String filePath = Constant.getValue("ATTACHMENT_PATH");
      filePath = filePath + File.separator + "actionAttachment" + File.separator + attachmentuuid + File.separator;
      File file = new File(filePath);
      if (file.isDirectory())
      {
        String[] files = file.list();
        if ((files != null) && (files.length > 0)) {
          for (int i = 0; i < files.length; i++)
          {
            String f1name = files[i];
            File f1 = new File(filePath + f1name);
            if (f1.exists()) {
              f1.delete();
            }
          }
        } else if (file.exists()) {
          file.delete();
        }
      }
      if (file.exists()) {
        file.delete();
      }
    }
    catch (Exception e)
    {
      logger.info("Exception on delete attachment file" + e.getMessage());
    }
    List attachmentList = ApplicantUserDAO.getActionAttachmentList(new Long(idvalue).longValue(), actioname);
    applicantform.setActionAttachmentList(attachmentList);
    request.setAttribute("action_name", actioname);
    request.setAttribute("idvalue", idvalue);
    return mapping.findForward("actionattachmentaddscr");
  }
  
  public ActionForward attachmentdetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside attachmentdetails method");
    String uuid = request.getParameter("uuid");
    
    ActionsAttachment appattach = ApplicantUserDAO.getActionAttachment(uuid);
    
    String filePath = Constant.getValue("ATTACHMENT_PATH");
    filePath = "actionAttachment" + File.separator + appattach.getUuid() + File.separator + appattach.getAttahmentname();
    
    request.setAttribute("filePath", filePath);
    
    return mapping.findForward("attachmentdetails");
  }
  
  public ActionForward actionattachmentaddscr(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside actionattachmentaddscr method");
    String applicantId = request.getParameter("applicantId");
    
    String appuuid = request.getParameter("secureid");
    String idvalue = request.getParameter("idvalue");
    String actionname = request.getParameter("action");
    logger.info("applicantId" + applicantId);
    logger.info("appuuid" + appuuid);
    logger.info("idvalue" + idvalue);
    logger.info("actionname" + actionname);
    
    ApplicantForm applicantform = (ApplicantForm)form;
    List attachmentList = ApplicantUserDAO.getActionAttachmentList(new Long(idvalue).longValue(), actionname);
    logger.info("attachmentList" + attachmentList.size());
    applicantform.setActionAttachmentList(attachmentList);
    request.setAttribute("action_name", actionname);
    request.setAttribute("idvalue", idvalue);
    applicantform.setApplicantId(new Long(applicantId).longValue());
    applicantform.setUuid(appuuid);
    return mapping.findForward("actionattachmentaddscr");
  }
  
  public ActionForward actionattachmentadd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside actionattachmentadd method");
    String applicantId = request.getParameter("applicantId");
    String filedetails = request.getParameter("filedetails");
    String appuuid = request.getParameter("secureid");
    String idvalue = request.getParameter("idvalue");
    String actionname = request.getParameter("action");
    try
    {
      ActionErrors errors = new ActionErrors();
      
      ApplicantForm applicantform = (ApplicantForm)form;
      


      logger.info("applicantId" + applicantId);
      logger.info("appuuid" + appuuid);
      JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, appuuid);
      
      FormFile myFile = applicantform.getAttachmentdata();
      
      int limitfileSize = new Integer(Constant.getValue("attachment.file.size.limit")).intValue();
      if (myFile != null)
      {
        String contentType = myFile.getContentType();
        String fileName = myFile.getFileName();
        int fileSize = myFile.getFileSize();
        byte[] fileData = myFile.getFileData();
        if (fileSize > limitfileSize)
        {
          request.setAttribute("attachmentsizeexceed", "yes");
        }
        else if (!StringUtils.isNullOrEmpty(fileName))
        {
          ActionsAttachment appattach = new ActionsAttachment();
          
          appattach.setAttahmentname(fileName);
          Blob blob = null;
          
          blob = new SerialBlob(fileData);
          

          appattach.setIdvalue(new Long(idvalue).longValue());
          appattach.setAction(actionname);
          appattach.setAttahmentdetails(filedetails);
          appattach.setApplicantId(new Long(applicantId).longValue());
          
          User userAg = (User)request.getSession().getAttribute("agency_data");
          User user = (User)request.getSession().getAttribute("user_data");
          RefferalEmployee userEmp = (RefferalEmployee)request.getSession().getAttribute("employee_refferal_data");
          ApplicantUser appuser = (ApplicantUser)request.getSession().getAttribute("applicant_user_data");
          if (userAg != null)
          {
            appattach.setCreatedBy(userAg.getLastName());
          }
          else if (user != null)
          {
            appattach.setCreatedBy(user.getFirstName() + " " + user.getLastName());
          }
          else if (userEmp != null)
          {
            appattach.setCreatedBy(userEmp.getEmployeeemail());
          }
          else if (appuser != null)
          {
            appattach.setCreatedBy(appuser.getFullName());
          }
          else
          {
            String refuserid = (String)request.getSession().getAttribute("refuserid");
            if (!StringUtils.isNullOrEmpty(refuserid)) {
              appattach.setCreatedBy("external");
            } else {
              appattach.setCreatedBy("external");
            }
          }
          appattach.setCreatedDate(new Date());
          appattach.setUuid(UUID.randomUUID().toString());
          appattach.setAppuuid(appuuid);
          ApplicantUserDAO.saveActionAttachment(appattach);
          
          String filePath = Constant.getValue("ATTACHMENT_PATH");
          filePath = filePath + File.separator + "actionAttachment" + File.separator + appattach.getUuid() + File.separator;
          File file = new File(filePath);
          if (!file.exists()) {
            file.mkdirs();
          }
          RandomAccessFile raf = new RandomAccessFile(filePath + fileName, "rw");
          

          int length = (int)blob.length();
          byte[] _blob = blob.getBytes(1L, length);
          raf.write(_blob);
          raf.close();
        }
      }
      logger.info("appuuid" + appuuid);
      List attachmentList = ApplicantUserDAO.getActionAttachmentList(new Long(idvalue).longValue(), actionname);
      applicantform.setActionAttachmentList(attachmentList);
      applicantform.setApplicantId(new Long(applicantId).longValue());
      applicantform.setUuid(appuuid);
      request.setAttribute("action_name", actionname);
      request.setAttribute("idvalue", idvalue);
    }
    catch (Exception e)
    {
      logger.info("exception on actionattachmentadd" + e.getMessage());
    }
    return mapping.findForward("actionattachmentaddscr");
  }
  
  public ActionForward applicantofferdetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String decrypted = "";
    logger.info("Inside acceptordeclinedurl method");
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    
    logger.info("applicantId" + applicantId);
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantForm applicantform = (ApplicantForm)form;
    applicantform.fromValueNologin(applicant, request);
    
    applicantform.setOfferownerId(applicant.getOfferownerId());
    applicantform.setOfferownerName(applicant.getOfferownerName());
    



    applicantform.setCurrectctc(applicant.getCurrectctc());
    applicantform.setExpectedctc(applicant.getExpectedctc());
    applicantform.setCurrectctccurrencycode(applicant.getCurrectctccurrencycode());
    applicantform.setExpectedctccurrencycode(applicant.getExpectedctccurrencycode());
    
    applicantform.setOfferInitiationComment(applicant.getOfferInitiationComment());
    applicantform.setOfferinitiationdate(applicant.getOfferinitiationdate());
    applicantform.setOfferinitiatedby(applicant.getOfferinitiatedby());
    

    applicantform.setInterviewState(applicant.getInterviewState());
    
    applicantform.setOfferacceptedcomment(applicant.getOfferacceptedcomment());
    applicantform.setOfferaccepteddate(applicant.getOfferaccepteddate());
    applicantform.setOfferdeclinedcomment(applicant.getOfferdeclinedcomment());
    applicantform.setOfferdeclineddate(applicant.getOfferdeclineddate());
    
    List offerreleaseattachmentList = BOFactory.getApplicantBO().getOfferAttachmentList(new Long(applicantId).longValue(), "reloffer");
    applicantform.setOfferReleaseAttachmentList(offerreleaseattachmentList);
    
    List appliocantattachmentList = BOFactory.getApplicantBO().getOfferAttachmentList(new Long(applicantId).longValue(), "applicantdoc");
    applicantform.setApplicantAttachmentList(appliocantattachmentList);
    
    List declinedresonlist = BOFactory.getLovBO().getAllOfferDeclinedResons();
    applicantform.setOfferdeclinedreasonslist(declinedresonlist);
    
    logger.info("finish applicantofferdetails");
    
    return mapping.findForward("applicantofferdetails");
  }
  
  public ActionForward applicantpfferattachment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String decrypted = "";
    logger.info("Inside applicantpfferattachment method");
    String applicantId = request.getParameter("applicantId");
    String filedetails = request.getParameter("filedetails");
    
    ActionErrors errors = new ActionErrors();
    
    ApplicantForm applicantform = (ApplicantForm)form;
    String uuid = request.getParameter("secureid");
    
    logger.info("applicantId" + applicantId);
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    
    FormFile myFile = applicantform.getAttachmentdata();
    int limitfileSize = new Integer(Constant.getValue("attachment.file.size.limit")).intValue();
    if (myFile != null)
    {
      String contentType = myFile.getContentType();
      String fileName = myFile.getFileName();
      int fileSize = myFile.getFileSize();
      byte[] fileData = myFile.getFileData();
      if (fileSize > limitfileSize)
      {
        request.setAttribute("attachmentsizeexceed", "yes");
      }
      else if (!StringUtils.isNullOrEmpty(fileName))
      {
        ApplicantOfferAttachment appattach = new ApplicantOfferAttachment();
        appattach.setAttahmentname(fileName);
        Blob blob = null;
        
        blob = new SerialBlob(fileData);
        


        appattach.setAttahmentdetails(filedetails);
        appattach.setApplicantId(new Long(applicantId).longValue());
        appattach.setCreatedBy(applicant.getFullName());
        appattach.setCreatedDate(new Date());
        appattach.setType("applicantdoc");
        appattach.setUuid(UUID.randomUUID().toString());
        BOFactory.getApplicantBO().saveOfferAttachment(appattach, applicant.getUuid(), applicant.getApplicantId(), applicant.getFullName(), null);
        


        String filePath = Constant.getValue("ATTACHMENT_PATH");
        filePath = filePath + File.separator + "offerAttachment" + File.separator + appattach.getUuid() + File.separator;
        File file = new File(filePath);
        if (!file.exists()) {
          file.mkdirs();
        }
        RandomAccessFile raf = new RandomAccessFile(filePath + fileName, "rw");
        

        int length = (int)blob.length();
        byte[] _blob = blob.getBytes(1L, length);
        raf.write(_blob);
        raf.close();
      }
    }
    applicantform.fromValueNologin(applicant, request);
    
    applicantform.setOfferownerId(applicant.getOfferownerId());
    applicantform.setOfferownerName(applicant.getOfferownerName());
    
    applicantform.setCurrectctc(applicant.getCurrectctc());
    applicantform.setExpectedctc(applicant.getExpectedctc());
    applicantform.setCurrectctccurrencycode(applicant.getCurrectctccurrencycode());
    applicantform.setExpectedctccurrencycode(applicant.getExpectedctccurrencycode());
    
    applicantform.setOfferInitiationComment(applicant.getOfferInitiationComment());
    applicantform.setOfferinitiationdate(applicant.getOfferinitiationdate());
    applicantform.setOfferinitiatedby(applicant.getOfferinitiatedby());
    

    applicantform.setInterviewState(applicant.getInterviewState());
    
    applicantform.setOfferacceptedcomment(applicant.getOfferacceptedcomment());
    applicantform.setOfferaccepteddate(applicant.getOfferaccepteddate());
    applicantform.setOfferdeclinedcomment(applicant.getOfferdeclinedcomment());
    applicantform.setOfferdeclineddate(applicant.getOfferdeclineddate());
    

    List offerreleaseattachmentList = BOFactory.getApplicantBO().getOfferAttachmentList(new Long(applicantId).longValue(), "reloffer");
    applicantform.setOfferReleaseAttachmentList(offerreleaseattachmentList);
    
    List appliocantattachmentList = BOFactory.getApplicantBO().getOfferAttachmentList(new Long(applicantId).longValue(), "applicantdoc");
    applicantform.setApplicantAttachmentList(appliocantattachmentList);
    
    List declinedresonlist = BOFactory.getLovBO().getAllOfferDeclinedResons();
    applicantform.setOfferdeclinedreasonslist(declinedresonlist);
    
    logger.info("finish applicantofferdetails");
    




    return mapping.findForward("applicantofferdetails");
  }
  
  public ActionForward acceptoffer(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside acceptoffer method");
    ApplicantForm applicantform = (ApplicantForm)form;
    String applicantId = request.getParameter("applicantId");
    

    String uuid = request.getParameter("secureid");
    
    logger.info("applicantId" + applicantId);
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    applicant.setInterviewState("Offer Accepted");
    applicant.setOfferacceptedcomment(applicantform.getOfferacceptedcomment());
    applicant.setOfferaccepteddate(new Date());
    


    ApplicationContext appContext = AppContextUtil.getAppcontext();
    ApplicantTXBO apptxbo = (ApplicantTXBO)appContext.getBean("apptxBoProxy");
    applicant = apptxbo.acceptoffer(applicant, Common.OFFER_ACCEPTED_DECLINED_BY_TYPE_APPLICANT);
    



    applicantform.fromValueNologin(applicant, request);
    
    applicantform.setOfferownerId(applicant.getOfferownerId());
    applicantform.setOfferownerName(applicant.getOfferownerName());
    



    applicantform.setCurrectctc(applicant.getCurrectctc());
    applicantform.setExpectedctc(applicant.getExpectedctc());
    applicantform.setCurrectctccurrencycode(applicant.getCurrectctccurrencycode());
    applicantform.setExpectedctccurrencycode(applicant.getExpectedctccurrencycode());
    
    applicantform.setOfferInitiationComment(applicant.getOfferInitiationComment());
    applicantform.setOfferinitiationdate(applicant.getOfferinitiationdate());
    applicantform.setOfferinitiatedby(applicant.getOfferinitiatedby());
    

    applicantform.setInterviewState(applicant.getInterviewState());
    
    applicantform.setOfferacceptedcomment(applicant.getOfferacceptedcomment());
    applicantform.setOfferaccepteddate(applicant.getOfferaccepteddate());
    applicantform.setOfferdeclinedcomment(applicant.getOfferdeclinedcomment());
    applicantform.setOfferdeclineddate(applicant.getOfferdeclineddate());
    
    List offerreleaseattachmentList = BOFactory.getApplicantBO().getOfferAttachmentList(new Long(applicantId).longValue(), "reloffer");
    applicantform.setOfferReleaseAttachmentList(offerreleaseattachmentList);
    List appliocantattachmentList = BOFactory.getApplicantBO().getOfferAttachmentList(new Long(applicantId).longValue(), "applicantdoc");
    applicantform.setApplicantAttachmentList(appliocantattachmentList);
    
    applicantform.setOfferacceptedcomment(applicant.getOfferacceptedcomment());
    applicantform.setOfferaccepteddate(applicant.getOfferaccepteddate());
    
    List declinedresonlist = BOFactory.getLovBO().getAllOfferDeclinedResons();
    applicantform.setOfferdeclinedreasonslist(declinedresonlist);
    





    request.setAttribute("acceptoffer", "yes");
    return mapping.findForward("acceptOfferScr");
  }
  
  public ActionForward declineoffer(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside declineoffer method");
    ApplicantForm applicantform = (ApplicantForm)form;
    String applicantId = request.getParameter("applicantId");
    String comment = request.getParameter("comment");
    String offerdeclinedresonid = request.getParameter("offerdeclinedresonid");
    
    String uuid = request.getParameter("secureid");
    
    logger.info("applicantId" + applicantId);
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    applicant.setInterviewState("Offer Declined");
    applicant.setOfferdeclinedcomment(applicantform.getOfferdeclinedcomment());
    applicant.setOfferdeclineddate(new Date());
    applicant.setOfferdeclinedresonid(new Integer(offerdeclinedresonid).intValue());
    


    JobRequisition jobreq = BOFactory.getJobRequistionBO().getJobRequision(String.valueOf(applicant.getReqId()));
    jobreq.setNumberOfOpeningRemain(jobreq.getNumberOfOpeningRemain() + 1);
    if ((!StringUtils.isNullOrEmpty(Constant.getValue("auto.close.requisition.on.position.filled"))) && (Constant.getValue("auto.close.requisition.on.position.filled").equalsIgnoreCase("yes")) && 
      (jobreq.getStatus().equals("Closed")) && (jobreq.getNumberOfOpeningRemain() > 0)) {
      jobreq.setStatus("Open");
    }
    BOFactory.getApplicantTXBO().declineoffer(applicant, jobreq, Common.OFFER_ACCEPTED_DECLINED_BY_TYPE_APPLICANT);
    



    applicantform.fromValueNologin(applicant, request);
    
    applicantform.setOfferownerId(applicant.getOfferownerId());
    applicantform.setOfferownerName(applicant.getOfferownerName());
    



    applicantform.setCurrectctc(applicant.getCurrectctc());
    applicantform.setExpectedctc(applicant.getExpectedctc());
    applicantform.setCurrectctccurrencycode(applicant.getCurrectctccurrencycode());
    applicantform.setExpectedctccurrencycode(applicant.getExpectedctccurrencycode());
    
    applicantform.setOfferInitiationComment(applicant.getOfferInitiationComment());
    applicantform.setOfferinitiationdate(applicant.getOfferinitiationdate());
    applicantform.setOfferinitiatedby(applicant.getOfferinitiatedby());
    

    applicantform.setInterviewState(applicant.getInterviewState());
    
    applicantform.setOfferdeclineddate(applicant.getOfferdeclineddate());
    applicantform.setOfferdeclinedcomment(applicant.getOfferdeclinedcomment());
    applicantform.setOfferdeclinedresonid(applicant.getOfferdeclinedresonid());
    
    List offerreleaseattachmentList = BOFactory.getApplicantBO().getOfferAttachmentList(new Long(applicantId).longValue(), "reloffer");
    applicantform.setOfferReleaseAttachmentList(offerreleaseattachmentList);
    List appliocantattachmentList = BOFactory.getApplicantBO().getOfferAttachmentList(new Long(applicantId).longValue(), "applicantdoc");
    applicantform.setApplicantAttachmentList(appliocantattachmentList);
    


    List declinedresonlist = BOFactory.getLovBO().getAllOfferDeclinedResons();
    applicantform.setOfferdeclinedreasonslist(declinedresonlist);
    







    request.setAttribute("declineoffer", "yes");
    return mapping.findForward("offerDeclinedScr");
  }
  
  public ActionForward sendoffermodifficationrequestscr(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside sendoffermodifficationrequestscr method");
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    
    logger.info("applicantId" + applicantId);
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    ActionErrors errors = new ActionErrors();
    
    ApplicantForm applicantform = (ApplicantForm)form;
    if (applicant.getTargetjoiningdate() != null) {
      applicantform.setChagetargetjoiningdate(DateUtil.convertDateToStringDate(applicant.getTargetjoiningdate(), Constant.getValue("email.defaultdateformatwithourday")));
    }
    applicantform.setChangeofferedctc(applicant.getOfferedctc());
    applicantform.setChangeofferedctccurrencycode(applicant.getOfferedctccurrencycode());
    applicantform.setOfferedctccurrencycode(applicant.getOfferedctccurrencycode());
    applicantform.setApplicantId(applicant.getApplicantId());
    
    applicantform.setUuid(uuid);
    applicantform.setApplicantId(new Long(applicantId).longValue());
    request.setAttribute("modificationrequestsaved", "no");
    return mapping.findForward("sendoffermodifficationrequestscr");
  }
  
  public ActionForward offerDeclinedScr(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside offerDeclinedScr method");
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    
    logger.info("applicantId" + applicantId);
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    ActionErrors errors = new ActionErrors();
    
    ApplicantForm applicantform = (ApplicantForm)form;
    
    List declinedresonlist = BOFactory.getLovBO().getAllOfferDeclinedResons();
    applicantform.setOfferdeclinedreasonslist(declinedresonlist);
    
    applicantform.setApplicantId(applicant.getApplicantId());
    
    applicantform.setUuid(uuid);
    applicantform.setApplicantId(new Long(applicantId).longValue());
    request.setAttribute("offerdeclinedsaved", "no");
    return mapping.findForward("offerDeclinedScr");
  }
  
  public ActionForward acceptOfferScr(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside offerDeclinedScr method");
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    
    logger.info("applicantId" + applicantId);
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    ActionErrors errors = new ActionErrors();
    
    ApplicantForm applicantform = (ApplicantForm)form;
    


    applicantform.setApplicantId(applicant.getApplicantId());
    applicantform.setUuid(uuid);
    applicantform.setApplicantId(new Long(applicantId).longValue());
    request.setAttribute("acceptoffer", "no");
    return mapping.findForward("acceptOfferScr");
  }
  
  public ActionForward sendoffermodifficationrequest(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside sendoffermodifficationrequest method");
    String applicantId = request.getParameter("applicantId");
    String chagetargetjoiningdate = request.getParameter("chagetargetjoiningdate");
    ActionErrors errors = new ActionErrors();
    
    ApplicantForm applicantform = (ApplicantForm)form;
    String uuid = request.getParameter("secureid");
    
    logger.info("applicantId" + applicantId);
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    if (chagetargetjoiningdate != null) {
      applicant.setChagetargetjoiningdate(DateUtil.convertStringDateToDate(chagetargetjoiningdate, Constant.getValue("email.defaultdateformatwithourday")));
    }
    applicant.setChangeofferComment(applicantform.getChangeofferComment());
    
    applicant.setChangeofferedctc(applicantform.getChangeofferedctc());
    applicant.setChangeofferedctccurrencycode(applicant.getOfferedctccurrencycode());
    applicantform.setOfferedctccurrencycode(applicant.getOfferedctccurrencycode());
    applicant.setInterviewState("Offer In Negotiation");
    


    applicant = BOFactory.getApplicantTXBO().offermodifficationrequest(applicant, Common.OFFER_ACCEPTED_DECLINED_BY_TYPE_APPLICANT);
    


    applicantform.setUuid(uuid);
    applicantform.setApplicantId(new Long(applicantId).longValue());
    request.setAttribute("modificationrequestsaved", "yes");
    return mapping.findForward("sendoffermodifficationrequestscr");
  }
}
