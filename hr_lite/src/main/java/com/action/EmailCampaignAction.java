package com.action;

import com.bean.User;
import com.bean.pool.EmailCampaign;
import com.bo.BOFactory;
import com.bo.EmailCampaignBO;
import com.form.EmailCampaignForm;
import com.util.EmailResumeReaderUtil;
import com.util.EncryptDecrypt;
import com.util.StringUtils;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class EmailCampaignAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(EmailCampaignAction.class);
  
  public ActionForward emailCampaignLovList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside emailCampaignLovList method");
    EmailCampaignForm emailCampaignForm = (EmailCampaignForm)form;
    return mapping.findForward("emailCampaignLovList");
  }
  
  public ActionForward createEmailCampaign(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside createEmailCampaign method");
    EmailCampaignForm emailCampaignForm = (EmailCampaignForm)form;
    return mapping.findForward("createEmailCampaign");
  }
  
  public ActionForward saveEmailCampaign(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveEmailCampaign method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    EmailCampaignForm emailCampaignForm = (EmailCampaignForm)form;
    EmailCampaign emailCampaign = new EmailCampaign();
    emailCampaignForm.toValue(emailCampaign, request);
    emailCampaign.setStatus("A");
    emailCampaign.setSmtppassword(EncryptDecrypt.encrypt(emailCampaignForm.getSmtppassword()));
    emailCampaign.setCreatedBy(user1.getUserName());
    emailCampaign.setCreatedDate(new Date());
    emailCampaign.setSuper_user_key(user1.getSuper_user_key());
    try
    {
      EmailResumeReaderUtil.checkEmailCampaign(emailCampaign);
      
      emailCampaign = BOFactory.getEmailCampaignBO().saveEmailCampaign(emailCampaign);
      request.setAttribute("emailCampaignSaved", "yes");
    }
    catch (Exception e)
    {
      request.setAttribute("error", e.getMessage());
    }
    emailCampaignForm.fromValue(emailCampaign, request);
    
    return mapping.findForward("createEmailCampaign");
  }
  
  public ActionForward editEmailCampaign(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside editEmailCampaign method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    EmailCampaignForm emailCampaignForm = (EmailCampaignForm)form;
    String emailCampaignId = request.getParameter("emailCampaignId");
    logger.info("emailCampaignId >> " + emailCampaignId);
    
    EmailCampaign emailCampaign = BOFactory.getEmailCampaignBO().getEmailCampaignDetails(new Long(emailCampaignId).longValue());
    
    emailCampaignForm.fromValue(emailCampaign, request);
    
    return mapping.findForward("createEmailCampaign");
  }
  
  public ActionForward updateEmailCampaign(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside editEmailCampaign method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    EmailCampaignForm emailCampaignForm = (EmailCampaignForm)form;
    String emailCampaignId = request.getParameter("emailCampaignId");
    logger.info("emailCampaignId >> " + emailCampaignId);
    
    EmailCampaign emailCampaign = BOFactory.getEmailCampaignBO().getEmailCampaignDetails(new Long(emailCampaignId).longValue());
    
    emailCampaignForm.toValue(emailCampaign, request);
    emailCampaign.setSmtppassword(EncryptDecrypt.encrypt(emailCampaignForm.getSmtppassword()));
    emailCampaign.setUpdatedBy(user1.getUserName());
    emailCampaign.setUpdatedDate(new Date());
    try
    {
      EmailResumeReaderUtil.checkEmailCampaign(emailCampaign);
      
      emailCampaign = BOFactory.getEmailCampaignBO().updateEmailCampaign(emailCampaign);
      request.setAttribute("emailCampaignupdated", "yes");
    }
    catch (Exception e)
    {
      request.setAttribute("error", e.getMessage());
    }
    emailCampaignForm.fromValue(emailCampaign, request);
    
    return mapping.findForward("createEmailCampaign");
  }
  
  public ActionForward deleteEmailCampaign(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteEmailCampaign method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    EmailCampaignForm emailCampaignForm = (EmailCampaignForm)form;
    String emailCampaignId = request.getParameter("emailCampaignId");
    String fromwhere = request.getParameter("fromwhere");
    

    EmailCampaign emailCampaign = BOFactory.getEmailCampaignBO().getEmailCampaignDetails(new Long(emailCampaignId).longValue());
    

    emailCampaign = BOFactory.getEmailCampaignBO().deleteEmailCampaign(emailCampaign);
    
    emailCampaignForm.fromValue(emailCampaign, request);
    request.setAttribute("emailcampaigndeleted", "yes");
    if ((!StringUtils.isNullOrEmpty(fromwhere)) && (fromwhere.equals("lov"))) {
      return mapping.findForward("createEmailCampaign");
    }
    return mapping.findForward("emailCampaignLovList");
  }
}
