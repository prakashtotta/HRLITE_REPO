package com.action;

import com.bean.ApplicantUserActions;
import com.bean.JobApplicant;
import com.bean.User;
import com.bo.ApplicantBO;
import com.bo.ApplicantTXBO;
import com.bo.BOFactory;
import com.common.Common;
import com.dao.ApplicantUserDAO;
import com.form.ApplicantUserActionsForm;
import com.manager.EmailTaskManager;
import com.resources.Constant;
import com.util.EmailTask;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ApplicantUserActionsAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(ApplicantUserActionsAction.class);
  
  public ActionForward addapplicantactionsscr(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside addapplicantactionsscr method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantUserActionsForm appuserform = (ApplicantUserActionsForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("uuid");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    List actionsList = Constant.getApplicantUserActionsList(user1);
    appuserform.setApplicantId(applicant.getApplicantId());
    appuserform.setUuid(applicant.getUuid());
    appuserform.setActionsList(actionsList);
    return mapping.findForward("addapplicantactionsscr");
  }
  
  public ActionForward editapplicantactions(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside editapplicantactions method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantUserActionsForm appuserform = (ApplicantUserActionsForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("uuid");
    String actionid = request.getParameter("actionid");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    List actionsList = Constant.getApplicantUserActionsList(user1);
    appuserform.setApplicantId(applicant.getApplicantId());
    appuserform.setUuid(applicant.getUuid());
    appuserform.setActionsList(actionsList);
    ApplicantUserActions action = ApplicantUserDAO.getApplicantUserActions(applicant.getApplicantId(), uuid, new Long(actionid).longValue());
    appuserform.setActionName(action.getActionName());
    appuserform.setCreatorComment(action.getCreatorComment());
    appuserform.setCreatedBy(action.getCreatedBy());
    appuserform.setCreatedDate(action.getCreatedDate());
    appuserform.setUserId(action.getUserId());
    appuserform.setUpdatedBy(action.getUpdatedBy());
    appuserform.setUpdatedDate(action.getUpdatedDate());
    appuserform.setAppuseractionId(action.getAppuseractionId());
    return mapping.findForward("addapplicantactionsscr");
  }
  
  public ActionForward updateapplicantactions(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updateapplicantactions method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantUserActionsForm appuserform = (ApplicantUserActionsForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("uuid");
    String actionid = request.getParameter("actionid");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    
    ApplicantUserActions actionexist = ApplicantUserDAO.getApplicantAction(new Long(applicantId).longValue(), uuid, appuserform.getActionName());
    if (actionexist == null)
    {
      ApplicantUserActions action = ApplicantUserDAO.getApplicantUserActions(applicant.getApplicantId(), uuid, new Long(actionid).longValue());
      
      action.setActionName(appuserform.getActionName());
      action.setCreatorComment(appuserform.getCreatorComment());
      
      action.setUpdatedBy(user1.getFirstName() + " " + user1.getLastName());
      action.setUpdatedDate(new Date());
      action.setStatus("A");
      


      applicant = BOFactory.getApplicantTXBO().updateapplicantactions(applicant, action, user1, null);
      

      addActionEmail(applicant, user1, appuserform.getActionName(), appuserform.getCreatorComment());
      request.setAttribute("isactionadded", "yes");
    }
    else if (actionexist.getAppuseractionId() == new Long(actionid).longValue())
    {
      ApplicantUserActions action = ApplicantUserDAO.getApplicantUserActions(applicant.getApplicantId(), uuid, new Long(actionid).longValue());
      
      action.setActionName(appuserform.getActionName());
      action.setCreatorComment(appuserform.getCreatorComment());
      
      action.setUpdatedBy(user1.getFirstName() + " " + user1.getLastName());
      action.setUpdatedDate(new Date());
      action.setStatus("A");
      


      applicant = BOFactory.getApplicantTXBO().updateapplicantactions(applicant, action, user1, null);
      

      addActionEmail(applicant, user1, appuserform.getActionName(), appuserform.getCreatorComment());
      request.setAttribute("isactionadded", "yes");
    }
    else
    {
      request.setAttribute("isactionalreadyadded", "yes");
    }
    List actionsList = Constant.getApplicantUserActionsList(user1);
    appuserform.setActionsList(actionsList);
    return mapping.findForward("addapplicantactionsscr");
  }
  
  public ActionForward deleteapplicantactions(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteapplicantactions method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantUserActionsForm appuserform = (ApplicantUserActionsForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("uuid");
    String actionid = request.getParameter("actionid");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    ApplicantUserActions action = ApplicantUserDAO.getApplicantUserActions(applicant.getApplicantId(), uuid, new Long(actionid).longValue());
    








    action.setUpdatedBy(user1.getFirstName() + " " + user1.getLastName());
    action.setUpdatedDate(new Date());
    action.setStatus("D");
    


    applicant = BOFactory.getApplicantTXBO().deleteapplicantactions(applicant, action, user1, null);
    

    List actionsList = Constant.getApplicantUserActionsList(user1);
    appuserform.setActionsList(actionsList);
    request.setAttribute("isactiondeleted", "yes");
    return mapping.findForward("addapplicantactionsscr");
  }
  
  public ActionForward addapplicantactions(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside addapplicantactions method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantUserActionsForm appuserform = (ApplicantUserActionsForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("uuid");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    
    ApplicantUserActions actionexist = ApplicantUserDAO.getApplicantAction(new Long(applicantId).longValue(), uuid, appuserform.getActionName());
    if (actionexist == null)
    {
      ApplicantUserActions action = new ApplicantUserActions();
      action.setActionName(appuserform.getActionName());
      action.setCreatorComment(appuserform.getCreatorComment());
      action.setApplicantId(applicant.getApplicantId());
      action.setUuid(applicant.getUuid());
      action.setApplicantName(applicant.getFullName());
      action.setUserId(user1.getUserId());
      action.setCreatedBy(user1.getFirstName() + " " + user1.getLastName());
      action.setCreatedDate(new Date());
      action.setStatus("A");
      

      applicant = BOFactory.getApplicantTXBO().addapplicantactions(applicant, action, user1, null);
      




      addActionEmail(applicant, user1, appuserform.getActionName(), appuserform.getCreatorComment());
      request.setAttribute("isactionadded", "yes");
    }
    else
    {
      request.setAttribute("isactionalreadyadded", "yes");
    }
    List actionsList = Constant.getApplicantUserActionsList(user1);
    appuserform.setActionsList(actionsList);
    return mapping.findForward("addapplicantactionsscr");
  }
  
  private void addActionEmail(JobApplicant applicant, User user1, String actionName, String commentfromuser)
    throws Exception
  {
    String[] to = { applicant.getEmail() };
    
    String[] bcc = null;
    
    String[] cc = { user1.getEmailId() };
    
    String replyTo = "\"" + user1.getFirstName() + " " + user1.getLastName() + "\"" + " " + "<" + user1.getEmailId() + ">";
    


    EmailTask emailtask = new EmailTask(replyTo, to, cc, bcc, replyTo, "dummysubject", null, "dummybody", null, 0, null);
    

    String comment = "<br>Action required from you : " + Constant.getResourceStringValue(actionName, user1.getLocale());
    comment = comment + "<br>" + commentfromuser + "<br>";
    emailtask.setUser(user1);
    emailtask.setComment(comment);
    emailtask.setFunctionType(Common.COMMENT_TO_APPLICANT);
    emailtask.setApplicant(applicant);
    EmailTaskManager.sendEmail(emailtask);
  }
}
