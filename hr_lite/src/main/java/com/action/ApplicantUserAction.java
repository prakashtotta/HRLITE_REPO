package com.action;

import com.bean.ApplicantUser;
import com.bean.JobApplicant;
import com.bean.Locale;
import com.bean.Timezone;
import com.bean.User;
import com.bo.ApplicantBO;
import com.bo.ApplicantTXBO;
import com.bo.ApplicantUserBO;
import com.bo.BOFactory;
import com.bo.LovBO;
import com.common.Common;
import com.dao.ApplicantUserDAO;
import com.form.ApplicantUserForm;
import com.manager.EmailTaskManager;
import com.util.EmailTask;
import com.util.EncryptDecrypt;
import com.util.PasswordGenerator;
import com.util.StringUtils;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ApplicantUserAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(ApplicantUserAction.class);
  
  public ActionForward createApplicantUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside createApplicantUser method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantUserForm appuserform = (ApplicantUserForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("uuid");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    appuserform.setEmailId(applicant.getEmail());
    
    appuserform.setLocaleList(BOFactory.getLovBO().getAllLocales());
    appuserform.setTimezoneList(BOFactory.getLovBO().getAllTimezones());
    appuserform.setApplicantId(applicant.getApplicantId());
    appuserform.setUuid(applicant.getUuid());
    if (user1.getLocale() != null) {
      appuserform.setLocaleId(user1.getLocale().getLocaleId());
    }
    if (user1.getTimezone() != null) {
      appuserform.setTimezoneId(user1.getTimezone().getTimezoneId());
    }
    return mapping.findForward("createapplicantuser");
  }
  
  public ActionForward editApplicantUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside editApplicantUser method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantUserForm appuserform = (ApplicantUserForm)form;
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("uuid");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    appuserform.setEmailId(applicant.getEmail());
    
    ApplicantUser appuser = ApplicantUserDAO.getApplicantUser(applicant.getApplicantId());
    
    appuserform.setLocaleId(appuser.getLocale().getLocaleId());
    appuserform.setTimezoneId(appuser.getTimezone().getTimezoneId());
    appuserform.setApplicantUserId(appuser.getApplicantUserId());
    
    appuserform.setLocaleList(BOFactory.getLovBO().getAllLocales());
    appuserform.setTimezoneList(BOFactory.getLovBO().getAllTimezones());
    appuserform.setApplicantId(applicant.getApplicantId());
    appuserform.setUuid(applicant.getUuid());
    appuserform.setApplicantCode(appuser.getApplicantCode());
    return mapping.findForward("createapplicantuser");
  }
  
  public ActionForward viewApplicantUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside viewApplicantUser method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantUserForm appuserform = (ApplicantUserForm)form;
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("uuid");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    appuserform.setEmailId(applicant.getEmail());
    
    ApplicantUser appuser = ApplicantUserDAO.getApplicantUser(applicant.getApplicantId());
    
    appuserform.setLocaleId(appuser.getLocale().getLocaleId());
    appuserform.setTimezoneId(appuser.getTimezone().getTimezoneId());
    appuserform.setApplicantUserId(appuser.getApplicantUserId());
    
    appuserform.setLocaleList(BOFactory.getLovBO().getAllLocales());
    appuserform.setTimezoneList(BOFactory.getLovBO().getAllTimezones());
    appuserform.setApplicantId(applicant.getApplicantId());
    appuserform.setUuid(applicant.getUuid());
    appuserform.setFullName(applicant.getFullName());
    return mapping.findForward("editapplicantuser");
  }
  
  public ActionForward saveApplicantUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveApplicantUser method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantUserForm appuserform = (ApplicantUserForm)form;
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("uuid");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    logger.info("Inside saveApplicantUser applicant" + applicant);
    boolean isError = false;
    ApplicantUser appuser2 = new ApplicantUser();
    if (applicant != null)
    {
      ApplicantUser appuser = new ApplicantUser();
      String email = ApplicantUserDAO.isEmailIdExist(applicant.getEmail(), applicant.getApplicant_number(), applicant.getSuper_user_key());
      logger.info("Inside saveApplicantUser method" + email);
      if (email != null)
      {
        isError = true;
        logger.info("Inside saveApplicantUser method" + email);
        request.setAttribute("email_id_alerady_exist", "yes");
      }
      else
      {
        logger.info("Inside saveApplicantUser method" + applicant.getEmail());
        appuser.setEmailId(applicant.getEmail());
        
        Locale locale = new Locale();
        locale.setLocaleId(appuserform.getLocaleId());
        appuser.setLocale(locale);
        
        Timezone timezone = new Timezone();
        timezone.setTimezoneId(appuserform.getTimezoneId());
        appuser.setTimezone(timezone);
        
        appuser.setApplicant(applicant);
        appuser.setStatus("A");
        appuser.setPassword(EncryptDecrypt.encrypt(PasswordGenerator.getPassword(8)));
        
        appuser.setCreatedBy(user1.getUserName());
        appuser.setCreatedDate(new Date());
        logger.info("Inside saveApplicantUser method" + applicant.getEmail());
      }
      if (!isError)
      {
        logger.info("creating email address" + email);
        

        appuser.setApplicantCode(applicant.getSuper_user_key() + "" + applicant.getApplicant_number());
        appuser2 = BOFactory.getApplicantTXBO().saveApplicantUser(applicant, appuser, user1, null);
        



        request.setAttribute("isuseradded", "yes");
        


        appuser = ApplicantUserDAO.getApplicantUser(applicant.getApplicantId());
        String[] tonew = { appuser.getEmailId() };
        String[] ccnew = { user1.getEmailId() };
        String from = "\"" + user1.getFirstName() + " " + user1.getLastName() + "\"" + " " + "<" + user1.getEmailId() + ">";
        
        EmailTask emailtasknew = new EmailTask(from, tonew, ccnew, null, from, "dummysubject", null, "dummybody", null, 0, null);
        emailtasknew.setFunctionType(Common.APPLICANT_USER_CREATION);
        emailtasknew.setUser(user1);
        emailtasknew.setAppuser(appuser);
        EmailTaskManager.sendEmail(emailtasknew);
      }
    }
    appuserform.setEmailId(applicant.getEmail());
    appuserform.setApplicantUserId(appuser2.getApplicantUserId());
    
    appuserform.setLocaleList(BOFactory.getLovBO().getAllLocales());
    appuserform.setTimezoneList(BOFactory.getLovBO().getAllTimezones());
    appuserform.setApplicantId(applicant.getApplicantId());
    appuserform.setUuid(applicant.getUuid());
    appuserform.setApplicantCode(appuser2.getApplicantCode());
    



    return mapping.findForward("createapplicantuser");
  }
  
  public ActionForward updateApplicantUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updateApplicantUser method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantUserForm appuserform = (ApplicantUserForm)form;
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("uuid");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    logger.info("Inside saveApplicantUser applicant" + applicant);
    ApplicantUser appuser2 = new ApplicantUser();
    if (applicant != null)
    {
      ApplicantUser appuser = ApplicantUserDAO.getApplicantUser(applicant.getApplicantId());
      
      appuser.setEmailId(applicant.getEmail());
      
      Locale locale = new Locale();
      locale.setLocaleId(appuserform.getLocaleId());
      appuser.setLocale(locale);
      
      Timezone timezone = new Timezone();
      timezone.setTimezoneId(appuserform.getTimezoneId());
      appuser.setTimezone(timezone);
      




      appuser.setUpdatedBy(user1.getUserName());
      appuser.setUpdatedDate(new Date());
      



      appuser2 = BOFactory.getApplicantTXBO().updateApplicantUser(applicant, appuser, user1, null);
      


      ApplicantUserDAO.updateApplicantUser(appuser);
      request.setAttribute("isuserupdated", "yes");
    }
    appuserform.setEmailId(applicant.getEmail());
    appuserform.setApplicantUserId(appuser2.getApplicantUserId());
    
    appuserform.setLocaleList(BOFactory.getLovBO().getAllLocales());
    appuserform.setTimezoneList(BOFactory.getLovBO().getAllTimezones());
    appuserform.setApplicantId(applicant.getApplicantId());
    appuserform.setUuid(applicant.getUuid());
    appuserform.setApplicantCode(appuser2.getApplicantCode());
    

    String[] tonew = { appuser2.getEmailId() };
    String[] ccnew = { user1.getEmailId() };
    String from = "\"" + user1.getFirstName() + " " + user1.getLastName() + "\"" + " " + "<" + user1.getEmailId() + ">";
    
    EmailTask emailtasknew = new EmailTask(from, tonew, ccnew, null, from, "dummysubject", null, "dummybody", null, 0, null);
    emailtasknew.setFunctionType(Common.APPLICANT_USER_CREATION);
    emailtasknew.setUser(user1);
    emailtasknew.setAppuser(appuser2);
    EmailTaskManager.sendEmail(emailtasknew);
    

    return mapping.findForward("createapplicantuser");
  }
  
  public ActionForward applicantUsersList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside applicantUsersList method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantUserForm appuserform = (ApplicantUserForm)form;
    int totalUser = 0;
    totalUser = ApplicantUserBO.getCountOfAllApplicantUsers();
    request.setAttribute("totalUser", Integer.valueOf(totalUser));
    request.setAttribute("searchpagedisplay", "no");
    return mapping.findForward("applicantUsersList");
  }
  
  public ActionForward searchapplicantUsers(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("** Inside searchapplicantUsers method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantUserForm appuserform = (ApplicantUserForm)form;
    ApplicantUser app = new ApplicantUser();
    
    appuserform.setFullName(appuserform.getFullName());
    appuserform.setApplicant_number(appuserform.getApplicant_number());
    appuserform.setEmailId(appuserform.getEmailId());
    appuserform.setCreatedBy(appuserform.getCreatedBy());
    
    request.setAttribute("searchpagedisplay", "yes");
    return mapping.findForward("applicantUsersList");
  }
  
  public ActionForward updatestatus(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("** Inside updatestatus method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String applicantUserId = request.getParameter("applicantUserId");
    String status = request.getParameter("status");
    ApplicantUserForm appuserform = (ApplicantUserForm)form;
    
    ApplicantUser usr = BOFactory.getApplicantBO().getApplicantUser(new Long(applicantUserId).longValue());
    
    usr.setStatus(status);
    usr.setUpdatedBy(user1.getUserName());
    usr.setUpdatedDate(new Date());
    
    BOFactory.getApplicantBO().updateApplicantUser(usr);
    
    return mapping.findForward("updatestatus");
  }
  
  public ActionForward updatestatusMultiple(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("** Inside updatestatusMultiple method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ApplicantUserForm appuserform = (ApplicantUserForm)form;
    String status = request.getParameter("status");
    String applicantUserIds = request.getParameter("applicantUserIds");
    logger.info("status >> " + status);
    logger.info("applicantUserIds >> " + applicantUserIds);
    int totalUser = 0;
    if (((!StringUtils.isNullOrEmpty(status)) && (status.equals("I"))) || (status.equals("A"))) {
      BOFactory.getApplicantBO().updatestatusMultiple(applicantUserIds, status);
    } else {
      BOFactory.getApplicantBO().deleteApplicantUserMultiple(applicantUserIds);
    }
    totalUser = ApplicantUserBO.getCountOfAllApplicantUsers();
    request.setAttribute("totalUser", Integer.valueOf(totalUser));
    return mapping.findForward("applicantUsersList");
  }
  
  public ActionForward deleteApplicantUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("** Inside deleteApplicantUser method");
    
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("uuid");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    logger.info("Inside saveApplicantUser applicant" + applicant);
    if (applicant != null)
    {
      ApplicantUser appuser = ApplicantUserDAO.getApplicantUser(applicant.getApplicantId());
      
      BOFactory.getApplicantBO().deleteApplicantUser(appuser);
    }
    request.setAttribute("isuserdeleted", "yes");
    return mapping.findForward("createapplicantuser");
  }
}
