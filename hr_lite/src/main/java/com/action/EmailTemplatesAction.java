package com.action;

import com.bean.EmailTemplates;
import com.bean.User;
import com.bo.BOFactory;
import com.bo.LovBO;
import com.form.EmailTemplatesForm;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class EmailTemplatesAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(EmailTemplatesAction.class);
  
  public ActionForward emailtemplatelist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside emailtemplatelist method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    return mapping.findForward("emailtemplatelist");
  }
  
  public ActionForward createEmailtemplate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside createemailtemplate method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    EmailTemplatesForm eform = (EmailTemplatesForm)form;
    

    request.setAttribute("emailtemplateupdate", "no");
    return mapping.findForward("createEmailtemplate");
  }
  
  public ActionForward editemailtemplate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside editemailtemplate method");
    String emailtemplateId = request.getParameter("emailtemplateId");
    String readPreview = request.getParameter("readPreview");
    
    EmailTemplates empt = BOFactory.getLovBO().getEmailTemplateDetails(emailtemplateId);
    
    EmailTemplatesForm eform = (EmailTemplatesForm)form;
    eform.fromValue(empt, request);
    logger.info(empt.getEmailtemplateData());
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    eform.setReadPreview(readPreview);
    request.setAttribute("emailtemplateupdate", "no");
    return mapping.findForward("editemailtemplate");
  }
  
  public ActionForward searchEmailtemplatelistpage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchEmailtemplatelistpage method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    EmailTemplatesForm eform = (EmailTemplatesForm)form;
    EmailTemplates empt = new EmailTemplates();
    
    eform.setEmailtemplatename(eform.getEmailtemplatename());
    eform.setEmailSubject(eform.getEmailSubject());
    eform.setCreatedBy(eform.getCreatedBy());
    

    request.setAttribute("searchpagedisplay", "yes");
    return mapping.findForward("emailtemplatelist");
  }
  
  public ActionForward deletetemplate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deletetemplate method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String emailtemplateId = request.getParameter("emailtemplateId");
    
    BOFactory.getLovBO().deleteEmailTemplateDetails(emailtemplateId);
    request.setAttribute("emailtemplatedeleted", "yes");
    return mapping.findForward("editemailtemplate");
  }
  
  public ActionForward saveemailtemplate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveemailtemplate method");
    String emailtemplateId = request.getParameter("emailtemplateId");
    User user1 = (User)request.getSession().getAttribute("user_data");
    EmailTemplates empt = new EmailTemplates();
    
    EmailTemplatesForm eform = (EmailTemplatesForm)form;
    logger.info("eform >> " + eform.getEmailtemplatename());
    eform.toValue(empt, request);
    empt.setCreatedBy(user1.getFirstName() + " " + user1.getLastName());
    empt.setCreatedDate(new Date());
    empt.setSuper_user_key(user1.getSuper_user_key());
    empt = BOFactory.getLovBO().saveEmailTemplate(empt);
    
    eform.fromValue(empt, request);
    logger.info(empt.getEmailtemplateData());
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    request.setAttribute("emailtemplatesave", "yes");
    return mapping.findForward("createEmailtemplate");
  }
  
  public ActionForward saveemailtemplateNew(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveemailtemplateNew method");
    String emailtemplateId = request.getParameter("emailtemplateId");
    User user1 = (User)request.getSession().getAttribute("user_data");
    EmailTemplates empt = new EmailTemplates();
    
    EmailTemplatesForm eform = (EmailTemplatesForm)form;
    logger.info("eform >> " + eform.getEmailtemplatename());
    logger.info("eform >> " + request.getParameter("emailSubject"));
    eform.toValue(empt, request);
    empt.setCreatedBy(user1.getFirstName() + " " + user1.getLastName());
    empt.setCreatedDate(new Date());
    empt.setSuper_user_key(user1.getSuper_user_key());
    empt = BOFactory.getLovBO().saveEmailTemplate(empt);
    
    eform.fromValue(empt, request);
    logger.info(empt.getEmailtemplateData());
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    request.setAttribute("emailtemplatesave", "yes");
    return mapping.findForward("showOrgEmailTemplate");
  }
  
  public ActionForward updateemailtemplate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updateemailtemplate method");
    String emailtemplateId = request.getParameter("emailtemplateId");
    User user1 = (User)request.getSession().getAttribute("user_data");
    EmailTemplates empt = BOFactory.getLovBO().getEmailTemplateDetails(emailtemplateId);
    
    EmailTemplatesForm eform = (EmailTemplatesForm)form;
    eform.toValue(empt, request);
    empt.setSuper_user_key(user1.getSuper_user_key());
    empt.setUpdatedBy(user1.getFirstName() + " " + user1.getLastName());
    empt.setUpdatedDate(new Date());
    
    empt = BOFactory.getLovBO().updateEmailTemplate(empt);
    

    eform.fromValue(empt, request);
    logger.info(empt.getEmailtemplateData());
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    request.setAttribute("emailtemplateupdate", "yes");
    return mapping.findForward("editemailtemplate");
  }
  
  public ActionForward saveas(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveas method");
    String emailtemplateId = request.getParameter("emailtemplateId");
    User user1 = (User)request.getSession().getAttribute("user_data");
    EmailTemplates empt = BOFactory.getLovBO().getEmailTemplateDetails(emailtemplateId);
    EmailTemplatesForm eform = (EmailTemplatesForm)form;
    
    EmailTemplates empt_new = new EmailTemplates();
    empt_new.setEmailtemplatename(empt.getEmailtemplatename() + "_1");
    empt_new.setEmailSubject(empt.getEmailSubject());
    empt_new.setEmailtemplateData(empt.getEmailtemplateData());
    empt_new.setEmailtemplateVmname("1_" + empt.getEmailtemplateVmname());
    empt_new.setCreatedBy(user1.getUserName());
    empt_new.setCreatedDate(new Date());
    
    empt_new = BOFactory.getLovBO().saveeEmailTemplate(empt_new);
    
    eform.fromValue(empt_new, request);
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    request.setAttribute("emailtemplateupdate", "saveas");
    return mapping.findForward("editemailtemplate");
  }
  
  public ActionForward previewMessage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside previewMessage method");
    
    EmailTemplatesForm eform = (EmailTemplatesForm)form;
    
    String emailtemplateid = request.getParameter("emailtemplateid");
    
    logger.info("Message to preview : " + emailtemplateid);
    request.setAttribute("emailtemplateid", emailtemplateid);
    return mapping.findForward("previewMessage");
  }
}
