package com.action;

import com.bean.EmailEvents;
import com.bean.EmailTemplates;
import com.bean.Organization;
import com.bean.OrganizationEmailTemplate;
import com.bean.User;
import com.bo.BOFactory;
import com.bo.LovBO;
import com.dao.LovOpsDAO;
import com.form.OrganizationEmailTemplateForm;
import com.util.StringUtils;
import java.util.ArrayList;
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

public class OrganizationEmailTemplateAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(OrganizationEmailTemplateAction.class);
  
  public ActionForward orgemailTemplatelist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside orgemailTemplatelist method");
    User user = (User)request.getSession().getAttribute("user_data");
    OrganizationEmailTemplateForm orgemailtemplateForm = (OrganizationEmailTemplateForm)form;
    List orgList = BOFactory.getLovBO().getAllOrganization(user.getSuper_user_key());
    Organization org = (Organization)orgList.get(0);
    List orgemailtemplateList = BOFactory.getLovBO().getAllorganizationemailtemplate(org.getOrgId());
    if (orgemailtemplateList.size() != 0)
    {
      OrganizationEmailTemplate orgemail = (OrganizationEmailTemplate)orgemailtemplateList.get(0);
      orgemailtemplateForm.setOrgId(orgemail.getOrganisation().getOrgId());
    }
    orgemailtemplateForm.setOrganizationList(orgList);
    List emailEventLIst = BOFactory.getLovBO().getAllEmailEvents();
    List orgemailtmplList = new ArrayList();
    for (int i = 0; i < emailEventLIst.size(); i++)
    {
      EmailEvents event = (EmailEvents)emailEventLIst.get(i);
      OrganizationEmailTemplate tmpl = BOFactory.getLovBO().getOrganizationEmailTemplateBYEventcodeOrgid(event.getEventCode(), org.getOrgId());
      if (tmpl != null)
      {
        orgemailtmplList.add(tmpl);
      }
      else
      {
        OrganizationEmailTemplate tmplnew = new OrganizationEmailTemplate();
        tmplnew.setEmailevent(event);
        EmailTemplates emlmpl = new EmailTemplates();
        tmplnew.setOrganisation(org);
        tmplnew.setEmailtemplate(emlmpl);
        orgemailtmplList.add(tmplnew);
      }
    }
    orgemailtemplateForm.setFunctionTypeList(emailEventLIst);
    orgemailtemplateForm.setOrgemailtempList(orgemailtmplList);
    return mapping.findForward("OrganizationEmailTemplatemapping");
  }
  
  public ActionForward orgemailTemplatelistNew(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside orgemailTemplatelistNew method");
    User user = (User)request.getSession().getAttribute("user_data");
    OrganizationEmailTemplateForm orgemailtemplateForm = (OrganizationEmailTemplateForm)form;
    List orgList = BOFactory.getLovBO().getAllOrganization(user.getSuper_user_key());
    Organization org = (Organization)orgList.get(0);
    List orgemailtemplateList = BOFactory.getLovBO().getAllorganizationemailtemplate(org.getOrgId());
    if (orgemailtemplateList.size() != 0)
    {
      OrganizationEmailTemplate orgemail = (OrganizationEmailTemplate)orgemailtemplateList.get(0);
      orgemailtemplateForm.setOrgId(orgemail.getOrganisation().getOrgId());
    }
    orgemailtemplateForm.setOrganizationList(orgList);
    List emailEventLIst = BOFactory.getLovBO().getAllEmailEvents();
    List orgemailtmplList = new ArrayList();
    for (int i = 0; i < emailEventLIst.size(); i++)
    {
      EmailEvents event = (EmailEvents)emailEventLIst.get(i);
      
      OrganizationEmailTemplate tmpl = BOFactory.getLovBO().getOrganizationEmailTemplateBYEventcodeOrgid(event.getEventCode(), org.getOrgId());
      if (tmpl != null)
      {
        orgemailtmplList.add(tmpl);
      }
      else
      {
        OrganizationEmailTemplate tmplnew = new OrganizationEmailTemplate();
        tmplnew.setEmailevent(event);
        EmailTemplates emlmpl = new EmailTemplates();
        tmplnew.setOrganisation(org);
        tmplnew.setEmailtemplate(emlmpl);
        orgemailtmplList.add(tmplnew);
      }
    }
    orgemailtemplateForm.setEmailEventCodeList(emailEventLIst);
    orgemailtemplateForm.setFunctionTypeList(emailEventLIst);
    orgemailtemplateForm.setOrgemailtempList(orgemailtmplList);
    request.setAttribute("shoeEmailTemplate", "no");
    return mapping.findForward("OrganizationEmailTemplatemappingNew");
  }
  
  public ActionForward saveOrganizationEmailTemplateNew(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveOrganizationEmailTemplateNew method");
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String readPreview = request.getParameter("readPreview");
    User user = (User)request.getSession().getAttribute("user_data");
    OrganizationEmailTemplateForm orgemailtemplateForm = (OrganizationEmailTemplateForm)form;
    logger.info("orgemailtemplateForm.getOrgId()" + orgemailtemplateForm.getOrgId());
    List orgemailtemplateList = BOFactory.getLovBO().getAllorganizationemailtemplate(orgemailtemplateForm.getOrgId());
    OrganizationEmailTemplate orgemailtemplate = new OrganizationEmailTemplate();
    if (orgemailtemplateList.size() != 0) {
      LovOpsDAO.deleteOrganizationEmailTemplateMapping(orgemailtemplateList);
    }
    List orgList = BOFactory.getLovBO().getAllOrganization(user.getSuper_user_key());
    Organization org = (Organization)orgList.get(0);
    orgemailtemplateForm.setOrganizationList(orgList);
    List emailEventLIst = BOFactory.getLovBO().getAllEmailEvents();
    List orgemailtmplList = new ArrayList();
    List orgemailtmplListdummy = new ArrayList();
    for (int i = 0; i < emailEventLIst.size(); i++)
    {
      String emailEventId = request.getParameter("emailEventId_" + i);
      String emailtemplatename = request.getParameter("emailtemplatenameval_" + i);
      String emailtemplateId = request.getParameter("emailtemplateId_" + i);
      String eventCode = request.getParameter("eventCode_" + i);
      String isenabled = request.getParameter("isenabled_" + i);
      if ((!StringUtils.isNullOrEmpty(emailtemplateId)) && (new Long(emailtemplateId).longValue() != 0L))
      {
        logger.info("emailtemplateId" + emailtemplateId);
        
        Organization org1 = new Organization();
        org1.setOrgId(orgemailtemplateForm.getOrgId());
        EmailEvents emailev = (EmailEvents)emailEventLIst.get(i);
        OrganizationEmailTemplate orgemail = new OrganizationEmailTemplate();
        EmailTemplates emlmpl = new EmailTemplates();
        emlmpl.setEmailtemplateId(new Long(emailtemplateId).longValue());
        emlmpl.setEmailtemplatename(emailtemplatename);
        emailev.setEmailEventId(new Long(emailEventId).longValue());
        orgemail.setEmailevent(emailev);
        orgemail.setOrganisation(org1);
        orgemail.setEmailtemplate(emlmpl);
        orgemail.setEventCode(eventCode);
        orgemail.setCreatedBy(user.getUserName());
        orgemail.setCreatedDate(new Date());
        orgemail.setStatus(isenabled);
        orgemailtmplList.add(orgemail);
        orgemailtmplListdummy.add(orgemail);
      }
      else
      {
        Organization org1 = new Organization();
        org1.setOrgId(orgemailtemplateForm.getOrgId());
        EmailEvents emailev = (EmailEvents)emailEventLIst.get(i);
        OrganizationEmailTemplate orgemail = new OrganizationEmailTemplate();
        EmailTemplates emlmpl = new EmailTemplates();
        emlmpl.setEmailtemplateId(0L);
        emlmpl.setEmailtemplatename("");
        
        orgemail.setEmailevent(emailev);
        orgemail.setOrganisation(org1);
        orgemail.setEmailtemplate(emlmpl);
        orgemail.setStatus(isenabled);
        



        orgemailtmplListdummy.add(orgemail);
      }
    }
    LovOpsDAO.saveOrganizationEmailTemplateList(orgemailtmplList);
    
    orgemailtemplateForm.setEmailEventCodeList(emailEventLIst);
    orgemailtemplateForm.setFunctionTypeList(emailEventLIst);
    orgemailtemplateForm.setOrgemailtempList(orgemailtmplListdummy);
    request.setAttribute("mappingsave", "yes");
    return mapping.findForward("OrganizationEmailTemplatemappingNew");
  }
  
  public ActionForward saveOrganizationEmailTemplate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveOrganizationEmailTemplate method");
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String readPreview = request.getParameter("readPreview");
    User user = (User)request.getSession().getAttribute("user_data");
    OrganizationEmailTemplateForm orgemailtemplateForm = (OrganizationEmailTemplateForm)form;
    logger.info("orgemailtemplateForm.getOrgId()" + orgemailtemplateForm.getOrgId());
    List orgemailtemplateList = BOFactory.getLovBO().getAllorganizationemailtemplate(orgemailtemplateForm.getOrgId());
    OrganizationEmailTemplate orgemailtemplate = new OrganizationEmailTemplate();
    if (orgemailtemplateList.size() != 0) {
      LovOpsDAO.deleteOrganizationEmailTemplateMapping(orgemailtemplateList);
    }
    List orgList = BOFactory.getLovBO().getAllOrganization(user.getSuper_user_key());
    Organization org = (Organization)orgList.get(0);
    orgemailtemplateForm.setOrganizationList(orgList);
    List emailEventLIst = BOFactory.getLovBO().getAllEmailEvents();
    List orgemailtmplList = new ArrayList();
    List orgemailtmplListdummy = new ArrayList();
    for (int i = 0; i < emailEventLIst.size(); i++)
    {
      String emailEventId = request.getParameter("emailEventId_" + i);
      String emailtemplatename = request.getParameter("emailtemplatenameval_" + i);
      String emailtemplateId = request.getParameter("emailtemplateId_" + i);
      String eventCode = request.getParameter("eventCode_" + i);
      String isenabled = request.getParameter("isenabled_" + i);
      if ((!StringUtils.isNullOrEmpty(emailtemplateId)) && (new Long(emailtemplateId).longValue() != 0L))
      {
        logger.info("emailtemplateId" + emailtemplateId);
        
        Organization org1 = new Organization();
        org1.setOrgId(orgemailtemplateForm.getOrgId());
        EmailEvents emailev = (EmailEvents)emailEventLIst.get(i);
        OrganizationEmailTemplate orgemail = new OrganizationEmailTemplate();
        EmailTemplates emlmpl = new EmailTemplates();
        emlmpl.setEmailtemplateId(new Long(emailtemplateId).longValue());
        emlmpl.setEmailtemplatename(emailtemplatename);
        emailev.setEmailEventId(new Long(emailEventId).longValue());
        orgemail.setEmailevent(emailev);
        orgemail.setOrganisation(org1);
        orgemail.setEmailtemplate(emlmpl);
        orgemail.setEventCode(eventCode);
        orgemail.setCreatedBy(user.getUserName());
        orgemail.setCreatedDate(new Date());
        orgemail.setStatus(isenabled);
        orgemailtmplList.add(orgemail);
        orgemailtmplListdummy.add(orgemail);
      }
      else
      {
        Organization org1 = new Organization();
        org1.setOrgId(orgemailtemplateForm.getOrgId());
        EmailEvents emailev = (EmailEvents)emailEventLIst.get(i);
        OrganizationEmailTemplate orgemail = new OrganizationEmailTemplate();
        EmailTemplates emlmpl = new EmailTemplates();
        emlmpl.setEmailtemplateId(0L);
        emlmpl.setEmailtemplatename("");
        
        orgemail.setEmailevent(emailev);
        orgemail.setOrganisation(org1);
        orgemail.setEmailtemplate(emlmpl);
        orgemail.setStatus(isenabled);
        



        orgemailtmplListdummy.add(orgemail);
      }
    }
    LovOpsDAO.saveOrganizationEmailTemplateList(orgemailtmplList);
    orgemailtemplateForm.setFunctionTypeList(emailEventLIst);
    orgemailtemplateForm.setOrgemailtempList(orgemailtmplListdummy);
    request.setAttribute("mappingsave", "yes");
    return mapping.findForward("OrganizationEmailTemplatemapping");
  }
  
  public ActionForward orgEmaillistselector(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside orgEmaillistselector method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user1 = (User)request.getSession().getAttribute("user_data");
    String emailtemplatenameval = request.getParameter("emailtemplatenameval");
    String emailspanvalue = request.getParameter("emailvalue");
    String emailtemplateidval = request.getParameter("emailtemplateidval");
    logger.info("emailtemplatenameval : " + emailtemplatenameval);
    logger.info("emailspanvalue : " + emailspanvalue);
    logger.info("emailtemplateidval : " + emailtemplateidval);
    String boxnumber = request.getParameter("boxnumber");
    String start = request.getParameter("start");
    String range = request.getParameter("range");
    String results = request.getParameter("results");
    OrganizationEmailTemplateForm orgemailtemplateForm = (OrganizationEmailTemplateForm)form;
    int start1 = StringUtils.stringToInt(start, 0);
    int range1 = StringUtils.stringToInt(range, 15);
    

    List emailtemplateList = BOFactory.getLovBO().getEmailTemplateByCritera(user1, orgemailtemplateForm.getEmailtemplatename(), start1, range1);
    int totaluser = 0;
    if (StringUtils.isNullOrEmpty(results)) {
      totaluser = BOFactory.getLovBO().getCountOfEmailTemplateByCriteria(user1, orgemailtemplateForm.getEmailtemplatename());
    } else {
      totaluser = new Integer(results).intValue();
    }
    logger.info("emailtemplateList : " + emailtemplateList.size());
    logger.info("totaluser : " + totaluser);
    orgemailtemplateForm.setStart(String.valueOf(start1));
    orgemailtemplateForm.setRange(String.valueOf(range1));
    orgemailtemplateForm.setResults(String.valueOf(totaluser));
    
    orgemailtemplateForm.setEmailtemplatename(orgemailtemplateForm.getEmailtemplatename());
    orgemailtemplateForm.setEmailtemplateList(emailtemplateList);
    if (boxnumber != null) {
      request.setAttribute("boxnumber", boxnumber);
    }
    if (request.getSession().getAttribute("checkeduserids") != null) {
      request.getSession().removeAttribute("checkeduserids");
    }
    if (request.getSession().getAttribute("checkedusernames") != null) {
      request.getSession().removeAttribute("checkedusernames");
    }
    String readpreview = request.getParameter("readPreview");
    orgemailtemplateForm.setReadPreview(readpreview);
    
    orgemailtemplateForm.setEmailvaluespan(emailspanvalue);
    orgemailtemplateForm.setEmailtemplateidval(emailtemplateidval);
    orgemailtemplateForm.setEmailtemplatenameval(emailtemplatenameval);
    return mapping.findForward("orgemaillistselector");
  }
  
  public ActionForward loadEmailtemplate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside loadEmailtemplate method");
    User user = (User)request.getSession().getAttribute("user_data");
    OrganizationEmailTemplateForm orgemailtemplateForm = (OrganizationEmailTemplateForm)form;
    List orgList = BOFactory.getLovBO().getAllOrganization(user.getSuper_user_key());
    Organization org = (Organization)orgList.get(0);
    orgemailtemplateForm.setOrganizationList(orgList);
    List emailEventLIst = BOFactory.getLovBO().getAllEmailEvents();
    List orgemailtmplList = new ArrayList();
    for (int i = 0; i < emailEventLIst.size(); i++)
    {
      EmailEvents event = (EmailEvents)emailEventLIst.get(i);
      OrganizationEmailTemplate tmpl = BOFactory.getLovBO().getOrganizationEmailTemplateBYEventcodeOrgid(event.getEventCode(), orgemailtemplateForm.getOrgId());
      if (tmpl != null)
      {
        orgemailtmplList.add(tmpl);
      }
      else
      {
        OrganizationEmailTemplate tmplnew = new OrganizationEmailTemplate();
        tmplnew.setEmailevent(event);
        EmailTemplates emlmpl = new EmailTemplates();
        tmplnew.setOrganisation(org);
        tmplnew.setEmailtemplate(emlmpl);
        
        orgemailtmplList.add(tmplnew);
      }
    }
    orgemailtemplateForm.setFunctionTypeList(emailEventLIst);
    orgemailtemplateForm.setOrgemailtempList(orgemailtmplList);
    return mapping.findForward("OrganizationEmailTemplatemapping");
  }
  
  public ActionForward loadEmailtemplateNew(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside loadEmailtemplateNew method");
    User user = (User)request.getSession().getAttribute("user_data");
    OrganizationEmailTemplateForm orgemailtemplateForm = (OrganizationEmailTemplateForm)form;
    List orgList = BOFactory.getLovBO().getAllOrganization(user.getSuper_user_key());
    Organization org = (Organization)orgList.get(0);
    orgemailtemplateForm.setOrganizationList(orgList);
    List emailEventLIst = BOFactory.getLovBO().getAllEmailEvents();
    List orgemailtmplList = new ArrayList();
    for (int i = 0; i < emailEventLIst.size(); i++)
    {
      EmailEvents event = (EmailEvents)emailEventLIst.get(i);
      OrganizationEmailTemplate tmpl = BOFactory.getLovBO().getOrganizationEmailTemplateBYEventcodeOrgid(event.getEventCode(), orgemailtemplateForm.getOrgId());
      if (tmpl != null)
      {
        orgemailtmplList.add(tmpl);
      }
      else
      {
        OrganizationEmailTemplate tmplnew = new OrganizationEmailTemplate();
        tmplnew.setEmailevent(event);
        EmailTemplates emlmpl = new EmailTemplates();
        tmplnew.setOrganisation(org);
        tmplnew.setEmailtemplate(emlmpl);
        
        orgemailtmplList.add(tmplnew);
      }
    }
    orgemailtemplateForm.setEmailEventCodeList(emailEventLIst);
    orgemailtemplateForm.setFunctionTypeList(emailEventLIst);
    orgemailtemplateForm.setOrgemailtempList(orgemailtmplList);
    request.setAttribute("shoeEmailTemplate", "no");
    return mapping.findForward("OrganizationEmailTemplatemappingNew");
  }
  
  public ActionForward searchEmailtemplates(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchEmailtemplates method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    OrganizationEmailTemplateForm orgemailtemplateForm = (OrganizationEmailTemplateForm)form;
    
    String boxnumber = request.getParameter("boxnumber");
    
    String start = request.getParameter("start");
    String range = request.getParameter("range");
    String results = request.getParameter("results");
    

    int start1 = StringUtils.stringToInt(start, 0);
    int range1 = StringUtils.stringToInt(range, 15);
    
    String readpreview = request.getParameter("readPreview");
    orgemailtemplateForm.setReadPreview(readpreview);
    

    List emailtemplateList = BOFactory.getLovBO().getEmailTemplateByCritera(user1, orgemailtemplateForm.getEmailtemplatename(), start1, range1);
    int totaluser = 0;
    if (StringUtils.isNullOrEmpty(results)) {
      totaluser = BOFactory.getLovBO().getCountOfEmailTemplateByCriteria(user1, orgemailtemplateForm.getEmailtemplatename());
    } else {
      totaluser = new Integer(results).intValue();
    }
    logger.info("emailtemplateList : " + emailtemplateList.size());
    logger.info("totaluser : " + totaluser);
    orgemailtemplateForm.setStart(String.valueOf(start1));
    orgemailtemplateForm.setRange(String.valueOf(range1));
    orgemailtemplateForm.setResults(String.valueOf(totaluser));
    
    orgemailtemplateForm.setEmailtemplatename(orgemailtemplateForm.getEmailtemplatename());
    orgemailtemplateForm.setEmailtemplateList(emailtemplateList);
    if (boxnumber != null) {
      request.setAttribute("boxnumber", boxnumber);
    }
    return mapping.findForward("orgemaillistselector");
  }
  
  public ActionForward showOrgEmailTemplate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside showEmailTemplate method .. ");
    User user = (User)request.getSession().getAttribute("user_data");
    OrganizationEmailTemplateForm orgemailtemplateForm = (OrganizationEmailTemplateForm)form;
    
    List emailEventLIst = BOFactory.getLovBO().getAllEmailEvents();
    List orgList = BOFactory.getLovBO().getAllOrganization(user.getSuper_user_key());
    

    String emailEventId = request.getParameter("emailEventId");
    logger.info("emailEventId >> " + emailEventId);
    String orgid = request.getParameter("orgid");
    logger.info("orgid >> " + orgid);
    String functionName = "";
    EmailEvents emailEvents = BOFactory.getLovBO().getEmailEventsDetails(emailEventId);
    functionName = emailEvents.getEventCode();
    OrganizationEmailTemplate OrgEmailTemplate = BOFactory.getLovBO().getOrganizationEmailTemplateBYEventIdOrgId(new Long(emailEventId).longValue(), new Long(orgid).longValue());
    if (OrgEmailTemplate != null)
    {
      orgemailtemplateForm.setOrgemailtemplid(OrgEmailTemplate.getOrgemailtemplid());
      String emailtemplateId = String.valueOf(OrgEmailTemplate.getEmailtemplate().getEmailtemplateId());
      logger.info("OrgEmailTemplate Id >> " + emailtemplateId);
      EmailTemplates empt = BOFactory.getLovBO().getEmailTemplateDetails(emailtemplateId);
      
      orgemailtemplateForm.setEmailtemplateId(empt.getEmailtemplateId());
      orgemailtemplateForm.setEmailtemplatename(empt.getEmailtemplatename());
      orgemailtemplateForm.setEmailtemplateData(empt.getEmailtemplateData());
      orgemailtemplateForm.setEmailSubject(empt.getEmailSubject());
      logger.info(empt.getEmailtemplateData());
      request.setAttribute("emailtemplateid", String.valueOf(empt.getEmailtemplateId()));
    }
    else
    {
      orgemailtemplateForm.setEmailtemplatename("");
      orgemailtemplateForm.setEmailtemplateData("");
      orgemailtemplateForm.setEmailSubject("");
      
      EmailTemplates empt = BOFactory.getLovBO().getEmailTemplateDetailsByDefaultComponent(functionName);
      

      orgemailtemplateForm.setEmailtemplatename(empt.getEmailtemplatename());
      orgemailtemplateForm.setEmailtemplateData(empt.getEmailtemplateData());
      orgemailtemplateForm.setEmailSubject(empt.getEmailSubject());
    }
    orgemailtemplateForm.setOrgId(new Long(orgid).longValue());
    orgemailtemplateForm.setEmailEventCodeList(emailEventLIst);
    orgemailtemplateForm.setOrganizationList(orgList);
    
    request.setAttribute("fn_name", functionName);
    request.setAttribute("shoeEmailTemplate", "yes");
    return mapping.findForward("OrganizationEmailTemplatemappingNew");
  }
  
  public ActionForward updateOrgemailtemplateNew(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updateOrgemailtemplateNew method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    OrganizationEmailTemplateForm orgemailtemplateForm = (OrganizationEmailTemplateForm)form;
    List emailEventLIst = BOFactory.getLovBO().getAllEmailEvents();
    List orgList = BOFactory.getLovBO().getAllOrganization(user1.getSuper_user_key());
    
    String orgemailtemplid = request.getParameter("orgemailtemplid");
    String orgId = request.getParameter("orgId");
    String emailEventId = request.getParameter("emailEventId");
    String emailtemplateId = request.getParameter("emailtemplateId");
    String emailmessage = request.getParameter("emailmessage");
    logger.info("orgemailtemplid >>" + orgemailtemplid);
    logger.info("orgId >>" + orgId);
    logger.info("emailEventId >>" + emailEventId);
    logger.info("emailtemplateId >>" + emailtemplateId);
    logger.info("orgemailtemplateForm.getEmailmessage() >>" + orgemailtemplateForm.getEmailmessage());
    logger.info("emailmessage >>" + emailmessage);
    String functionName = "";
    



    logger.info("eform >> " + orgemailtemplateForm.getEmailtemplatename());
    logger.info("eform >> " + request.getParameter("emailSubject"));
    
    OrganizationEmailTemplate orgEmailTemplate = BOFactory.getLovBO().getOrganizationEmailTemplateById(new Long(orgemailtemplid).longValue());
    orgEmailTemplate.setUpdatedBy(user1.getFirstName() + " " + user1.getLastName());
    orgEmailTemplate.setUpdatedDate(new Date());
    
    orgEmailTemplate = BOFactory.getLovBO().updateOrgEmailTemplate(orgEmailTemplate);
    orgemailtemplateForm.setOrgId(new Long(orgId).longValue());
    functionName = orgEmailTemplate.getEmailevent().getEventCode();
    


    EmailTemplates empt = BOFactory.getLovBO().getEmailTemplateDetails(emailtemplateId);
    
    empt.setEmailtemplatename(orgemailtemplateForm.getEmailtemplatename());
    empt.setEmailSubject(orgemailtemplateForm.getEmailSubject());
    empt.setEmailtemplateData(orgemailtemplateForm.getEmailmessage());
    empt.setSuper_user_key(user1.getSuper_user_key());
    empt.setUpdatedBy(user1.getFirstName() + " " + user1.getLastName());
    empt.setUpdatedDate(new Date());
    
    empt = BOFactory.getLovBO().updateEmailTemplate(empt);
    request.setAttribute("emailtemplateid", String.valueOf(empt.getEmailtemplateId()));
    functionName = orgEmailTemplate.getEmailevent().getEventCode();
    

    orgemailtemplateForm.setEmailEventCodeList(emailEventLIst);
    orgemailtemplateForm.setOrganizationList(orgList);
    orgemailtemplateForm.setEmailtemplateData(empt.getEmailtemplateData());
    
    request.setAttribute("fn_name", functionName);
    
    request.setAttribute("mappingsave", "yes");
    request.setAttribute("shoeEmailTemplate", "yes");
    return mapping.findForward("OrganizationEmailTemplatemappingNew");
  }
  
  public ActionForward saveOrgemailtemplateNew(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveOrgemailtemplateNew method");
    
    User user1 = (User)request.getSession().getAttribute("user_data");
    OrganizationEmailTemplateForm orgemailtemplateForm = (OrganizationEmailTemplateForm)form;
    List emailEventLIst = BOFactory.getLovBO().getAllEmailEvents();
    List orgList = BOFactory.getLovBO().getAllOrganization(user1.getSuper_user_key());
    String orgId = request.getParameter("orgId");
    String emailEventId = request.getParameter("emailEventId");
    EmailEvents emailEvents = BOFactory.getLovBO().getEmailEventsDetails(emailEventId);
    String functionName = emailEvents.getEventCode();
    
    EmailTemplates empt = new EmailTemplates();
    


    empt.setEmailtemplatename(orgemailtemplateForm.getEmailtemplatename());
    empt.setEmailSubject(orgemailtemplateForm.getEmailSubject());
    empt.setEmailtemplateData(orgemailtemplateForm.getEmailmessage());
    
    empt.setCreatedBy(user1.getFirstName() + " " + user1.getLastName());
    empt.setCreatedDate(new Date());
    empt.setSuper_user_key(user1.getSuper_user_key());
    
    empt = BOFactory.getLovBO().saveEmailTemplate(empt);
    request.setAttribute("emailtemplateid", String.valueOf(empt.getEmailtemplateId()));
    

    OrganizationEmailTemplate orgEmailTemplate = new OrganizationEmailTemplate();
    Organization Organization = new Organization();
    Organization.setOrgId(new Long(orgId).longValue());
    orgEmailTemplate.setOrganisation(Organization);
    
    empt.setEmailtemplateId(empt.getEmailtemplateId());
    orgEmailTemplate.setEmailtemplate(empt);
    
    EmailEvents emailEvents2 = new EmailEvents();
    emailEvents2.setEmailEventId(new Long(emailEventId).longValue());
    orgEmailTemplate.setEmailevent(emailEvents2);
    orgEmailTemplate.setEventCode(functionName);
    orgEmailTemplate.setCreatedBy(user1.getFirstName() + " " + user1.getLastName());
    orgEmailTemplate.setCreatedDate(new Date());
    orgEmailTemplate.setStatus("Y");
    
    orgEmailTemplate = BOFactory.getLovBO().saveOrgEmailTemplate(orgEmailTemplate);
    

    orgemailtemplateForm.setEmailtemplatename(empt.getEmailtemplatename());
    orgemailtemplateForm.setEmailSubject(empt.getEmailSubject());
    orgemailtemplateForm.setEmailtemplateData(empt.getEmailtemplateData());
    orgemailtemplateForm.setEmailEventCodeList(emailEventLIst);
    orgemailtemplateForm.setOrganizationList(orgList);
    

    logger.info(empt.getEmailtemplateData());
    
    request.setAttribute("fn_name", functionName);
    
    request.setAttribute("mappingsave", "yes");
    request.setAttribute("shoeEmailTemplate", "yes");
    return mapping.findForward("OrganizationEmailTemplatemappingNew");
  }
}
