package com.action;

import com.bean.Organization;
import com.bean.User;
import com.bo.BOFactory;
import com.bo.LovBO;
import com.bo.OrganizationBO;
import com.common.Common;
import com.common.ValidationException;
import com.form.OrganizationForm;
import com.resources.Constant;
import com.util.StringUtils;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class OrganizationAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(OrganizationAction.class);
  
  public ActionForward createOrganization(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    OrganizationForm organizationForm = (OrganizationForm)form;
    
    return mapping.findForward("createorg");
  }
  
  public ActionForward orglist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside orglist method");
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    OrganizationForm organizationForm = (OrganizationForm)form;
    
    return mapping.findForward("orglist");
  }
  
  public ActionForward orglistajax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside orglistajax method");
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    OrganizationForm organizationForm = (OrganizationForm)form;
    String orgId = request.getParameter("orgId");
    if (!StringUtils.isNullOrEmpty(orgId)) {
      request.setAttribute("orgId", orgId.trim());
    }
    return mapping.findForward("orglistajax");
  }
  
  public ActionForward getcurrencycodebyorg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside getcurrencycodebyorg method");
    String orgId = request.getParameter("orgId");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    OrganizationForm orgForm = (OrganizationForm)form;
    Organization org = BOFactory.getOrganizationBO().getOrganization(orgId);
    orgForm.setCurrencyCode(org.getCurrencyCode());
    return mapping.findForward("getcurrencycodebyorg");
  }
  
  public ActionForward deptList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside orglist method");
    String orgId = request.getParameter("orgId");
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    return mapping.findForward("deptList");
  }
  
  public ActionForward createorg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside createorg method");
    String orgId = request.getParameter("orgId");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    OrganizationForm orgForm = (OrganizationForm)form;
    orgForm.setOrgnizationTypeList(BOFactory.getLovBO().getOrganizationTypeList());
    List currencyList = BOFactory.getLovBO().getAllCurrencies();
    orgForm.setCurrencyCodeList(currencyList);
    return mapping.findForward("createorg");
  }
  
  public ActionForward editorg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside editorg method");
    String orgId = request.getParameter("orgid");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    OrganizationForm orgForm = (OrganizationForm)form;
    Organization org = BOFactory.getOrganizationBO().getOrganization(orgId);
    logger.info("Inside editorg method" + org);
    orgForm.fromValue(org, request);
    orgForm.setOrganization(org);
    String readpreview = request.getParameter("readPreview");
    orgForm.setReadPreview(readpreview);
    List currencyList = BOFactory.getLovBO().getAllCurrencies();
    orgForm.setCurrencyCodeList(currencyList);
    


    return mapping.findForward("editorg");
  }
  
  public ActionForward orginfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("**** Inside orginfo method");
    String orgId = request.getParameter("orgid");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    OrganizationForm orgForm = (OrganizationForm)form;
    Organization org = BOFactory.getOrganizationBO().getOrganization(orgId);
    logger.info("Inside orginfo method" + org);
    orgForm.fromValue(org, request);
    orgForm.setOrganization(org);
    
    List currencyList = BOFactory.getLovBO().getAllCurrencies();
    orgForm.setCurrencyCodeList(currencyList);
    
    return mapping.findForward("orginfo");
  }
  
  public ActionForward saveOrg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveOrg method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    String parentOrgName = request.getParameter("parentOrgName");
    String locationName = request.getParameter("locationName");
    

    ActionForward forward = new ActionForward();
    
    OrganizationForm organizationForm = (OrganizationForm)form;
    
    Organization org = new Organization();
    organizationForm.toValue(org, organizationForm);
    organizationForm.setParentOrgName(parentOrgName);
    boolean isError = false;
    String orgcode = BOFactory.getOrganizationBO().isOrgCodeExist(organizationForm.getOrgCode(), user1.getSuper_user_key());
    logger.info("orgcode" + orgcode);
    if (orgcode != null)
    {
      isError = true;
      ActionErrors errors = new ActionErrors();
      
      errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError("error.orgcode.alreadyexist"));
      saveErrors(request, errors);
    }
    if (!isError)
    {
      org.setSuper_user_key(user1.getSuper_user_key());
      org = BOFactory.getOrganizationBO().saveOrg(organizationForm, org, user1);
      request.setAttribute("organizationsaved", "yes");
    }
    organizationForm.fromValue(org, request);
    logger.info("orgcode1111" + orgcode);
    organizationForm.setParentOrgName(parentOrgName);
    organizationForm.setLocationName(locationName);
    request.setAttribute("parentOrgName", parentOrgName);
    request.setAttribute("locationName", locationName);
    return mapping.findForward("createorg");
  }
  
  public ActionForward updateOrg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updateOrg method");
    String orgId = request.getParameter("orgId");
    String isLegal = request.getParameter("isLegal");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    OrganizationForm orgForm = (OrganizationForm)form;
    Organization org = new Organization();
    orgForm.toValue(org, request);
    org.setOrgId(new Long(orgId).longValue());
    org.setUpdatedBy(user1.getUserName());
    org.setUpdatedDate(new Date());
    org.setStatus("A");
    org.setIsLegal(isLegal);
    Organization org1 = (Organization)request.getAttribute("org1");
    org.setDeptlist(org1.getDeptlist());
    org = BOFactory.getOrganizationBO().updateOrg(org);
    orgForm.fromValue(org, request);
    orgForm.setOrganization(org);
    request.setAttribute("organizationsaved", "yes");
    return mapping.findForward("editorg");
  }
  
  public ActionForward updateOrganization(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updateOrganization method >> ");
    
    ActionForward forward = new ActionForward();
    User user = (User)request.getSession().getAttribute("user_data");
    OrganizationForm organizationForm = (OrganizationForm)form;
    
    String id = request.getParameter("orgId");
    organizationForm.setOrgId(new Long(id).longValue());
    boolean isError = false;
    String orgcode = BOFactory.getOrganizationBO().isOrgCodeExist(organizationForm.getOrgCode(), user.getSuper_user_key());
    Organization org = BOFactory.getOrganizationBO().getOrganization(id);
    logger.info("orgcode          >> " + orgcode);
    logger.info("org.getOrgCode() >> " + org.getOrgCode());
    if ((orgcode != null) && (!orgcode.equals(org.getOrgCode())))
    {
      isError = true;
      ActionErrors errors = new ActionErrors();
      
      errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError("error.orgcode.alreadyexist"));
      saveErrors(request, errors);
      org.setOrgCode(organizationForm.getOrgCode());
    }
    if (!isError)
    {
      org = BOFactory.getOrganizationBO().updateOrganization(organizationForm, user);
      request.setAttribute("organizationsaved", "yes");
    }
    organizationForm.fromValue(org, request);
    

    return mapping.findForward("editorg");
  }
  
  public ActionForward deleteOrganization(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteOrganization method");
    User user = (User)request.getSession().getAttribute("user_data");
    try
    {
      ActionErrors errors = new ActionErrors();
      ActionForward forward = new ActionForward();
      
      OrganizationForm organizationForm = (OrganizationForm)form;
      
      String id = request.getParameter("orgId");
      organizationForm.setOrgId(new Long(id).longValue());
      BOFactory.getOrganizationBO().deleteOrganization(organizationForm, user);
      

      request.setAttribute("organizationdeleted", "yes");
      return mapping.findForward("editorg");
    }
    catch (ValidationException e)
    {
      String msg = Constant.getResourceStringValue("errorcode." + e.getErrorCode(), user.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
      return mapping.findForward("exception");
    }
    catch (Exception e)
    {
      String msg = Constant.getResourceStringValue("server.error", user.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
    }
    return mapping.findForward("exception");
  }
  
  public ActionForward orgselector(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside orgselector method");
    String orgId = request.getParameter("orgId");
    User user = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    OrganizationForm orgForm = (OrganizationForm)form;
    if (!StringUtils.isNullOrEmpty(orgId)) {
      orgForm.setOrgId(new Long(orgId).longValue());
    }
    String readpreview = request.getParameter("readPreview");
    orgForm.setReadPreview(readpreview);
    BOFactory.getOrganizationBO().orgselector(orgForm, user);
    return mapping.findForward("orgselector");
  }
  
  public ActionForward searchorganization(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside orglist method");
    String start = request.getParameter("start");
    String range = request.getParameter("range");
    String results = request.getParameter("results");
    User user = (User)request.getSession().getAttribute("user_data");
    
    String isLegal = request.getParameter("isLegal");
    logger.info(isLegal);
    int start1 = StringUtils.stringToInt(start, 0);
    int range1 = StringUtils.stringToInt(range, 15);
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    OrganizationForm orgForm = (OrganizationForm)form;
    
    BOFactory.getOrganizationBO().searchorganization(user, orgForm, start1, range1, results, isLegal);
    

    String readpreview = request.getParameter("readPreview");
    orgForm.setReadPreview(readpreview);
    return mapping.findForward("orgselector");
  }
}
