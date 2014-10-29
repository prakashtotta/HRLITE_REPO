package com.action;

import com.bean.Organization;
import com.bean.RefferalBudgetCode;
import com.bean.RefferalRedemptionRule;
import com.bean.RefferalScheme;
import com.bean.RefferalSchemeType;
import com.bean.User;
import com.bean.lov.KeyValue;
import com.bo.BOFactory;
import com.bo.LovBO;
import com.bo.RefferalSchemeBO;
import com.common.Common;
import com.common.ValidationException;
import com.dao.LovOpsDAO;
import com.dao.RefferalDAO;
import com.form.RefferalSchemeForm;
import com.resources.Constant;
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

public class RefferalSchemeAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(RefferalSchemeAction.class);
  
  public ActionForward refferalSchemelist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    RefferalSchemeForm refferalSchemeform = (RefferalSchemeForm)form;
    request.setAttribute("refferalHome", "refferalscheme");
    return mapping.findForward("refferalSchemelist");
  }
  
  public ActionForward createRefferalScheme(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    User user = (User)request.getSession().getAttribute("user_data");
    RefferalSchemeForm refferalSchemeform = (RefferalSchemeForm)form;
    List refferalBudgetCodeList = BOFactory.getLovBO().getAllRefferalBudgetCode(user.getSuper_user_key());
    refferalSchemeform.setRefferalBudgetCodeList(refferalBudgetCodeList);
    List refferalSchemeTypeList = BOFactory.getLovBO().getAllRefferalSchemeType(user.getSuper_user_key());
    refferalSchemeform.setRefferalSchemeTypeList(refferalSchemeTypeList);
    List ruleList = BOFactory.getLovBO().getAllRefferalRules(user.getSuper_user_key());
    refferalSchemeform.setRuleList(ruleList);
    if ((refferalSchemeTypeList != null) && (refferalSchemeTypeList.size() > 0) && (refferalBudgetCodeList != null) && (refferalBudgetCodeList.size() > 0))
    {
      RefferalSchemeType type = (RefferalSchemeType)refferalSchemeTypeList.get(0);
      
      String uom = BOFactory.getRefferalSchemeBO().getUOMWithCurrency(1L, type.getUom());
      refferalSchemeform.setUom(uom);
    }
    refferalSchemeform.setUomList(Constant.uomList);
    refferalSchemeform.setCriteriaList(Constant.getCriteriaList());
    String readPreview = request.getParameter("readPreview");
    refferalSchemeform.setReadPreview(readPreview);
    
    return mapping.findForward("createrefferalscheme");
  }
  
  public ActionForward loadUom(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside loadUom method");
    RefferalSchemeForm refferalSchemeform = (RefferalSchemeForm)form;
    String rerreralbudgetcodeid = request.getParameter("rerreralbudgetcodeid");
    String uomscheme = request.getParameter("uomscheme");
    
    long refbudgetcodeid = 0L;
    long refschemetypeid = 0L;
    if (!StringUtils.isNullOrEmpty(rerreralbudgetcodeid)) {
      refbudgetcodeid = new Long(rerreralbudgetcodeid).longValue();
    }
    String uom = BOFactory.getRefferalSchemeBO().getUOMWithCurrency(refbudgetcodeid, uomscheme);
    refferalSchemeform.setUom(uom);
    
    return mapping.findForward("loadUom");
  }
  
  public ActionForward editRefferalScheme(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside editRefferalScheme method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user = (User)request.getSession().getAttribute("user_data");
    String Id = request.getParameter("id");
    RefferalSchemeForm refferalSchemeform = (RefferalSchemeForm)form;
    RefferalScheme refferalscheme = BOFactory.getRefferalSchemeBO().getRefferalSchemeDetails(new Long(Id).longValue());
    toForm(refferalSchemeform, refferalscheme);
    List refferalBudgetCodeList = BOFactory.getLovBO().getAllRefferalBudgetCode(user.getSuper_user_key());
    refferalSchemeform.setRefferalBudgetCodeList(refferalBudgetCodeList);
    List refferalSchemeTypeList = BOFactory.getLovBO().getAllRefferalSchemeType(user.getSuper_user_key());
    refferalSchemeform.setRefferalSchemeTypeList(refferalSchemeTypeList);
    List ruleList = BOFactory.getLovBO().getAllRefferalRules(user.getSuper_user_key());
    refferalSchemeform.setRuleList(ruleList);
    
    String readPreview = request.getParameter("readPreview");
    refferalSchemeform.setReadPreview(readPreview);
    refferalSchemeform.setUomList(Constant.uomList);
    refferalSchemeform.setCriteriaList(Constant.getCriteriaList());
    request.setAttribute("updatebutton", "yes");
    return mapping.findForward("createrefferalscheme");
  }
  
  public ActionForward updateRefferalScheme(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updateRefferalScheme method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user = (User)request.getSession().getAttribute("user_data");
    RefferalSchemeForm refferalSchemeform = (RefferalSchemeForm)form;
    String Id = request.getParameter("id");
    String schemeType = request.getParameter("schemeType");
    RefferalScheme refferalscheme = LovOpsDAO.getRefferalSchemeDetails(Id);
    toValue(refferalscheme, refferalSchemeform);
    refferalscheme.setSchemeType(schemeType);
    



    RefferalRedemptionRule refferalRedemptionRule = BOFactory.getLovBO().getRefferalRuleDetails(refferalSchemeform.getCreditAfterdays(), refferalSchemeform.getCriteria(), user.getSuper_user_key());
    if (refferalRedemptionRule == null)
    {
      refferalRedemptionRule = new RefferalRedemptionRule();
      refferalRedemptionRule.setCreditAfterdays(refferalSchemeform.getCreditAfterdays());
      refferalRedemptionRule.setCriteria(refferalSchemeform.getCriteria());
      refferalRedemptionRule.setCreatedDate(new Date());
      refferalRedemptionRule.setCreatedBy(user.getUserName());
      refferalRedemptionRule.setSuper_user_key(user.getSuper_user_key());
      String criValue = "";
      List crilist = Constant.getCriteriaList();
      for (int i = 0; i < crilist.size(); i++)
      {
        KeyValue m = (KeyValue)crilist.get(i);
        if ((m.getKey() != null) && (m.getKey().equalsIgnoreCase(refferalSchemeform.getCriteria())))
        {
          criValue = m.getValue();
          break;
        }
      }
      String desc = Constant.getResourceStringValue("admin.RefferelRedumptionRule.Credit_After_Days", user.getLocale()) + " " + refferalSchemeform.getCreditAfterdays() + " " + Constant.getResourceStringValue("admin.RefferelRedumptionRule.Credit_After_Days.days", user.getLocale()) + " off " + criValue;
      
      refferalRedemptionRule.setRuleDesc(desc);
      refferalRedemptionRule.setStatus("A");
      refferalRedemptionRule = LovOpsDAO.saveRule(refferalRedemptionRule);
    }
    refferalscheme.setRule(refferalRedemptionRule);
    refferalscheme = LovOpsDAO.updateRefferalScheme(refferalscheme);
    List refferalBudgetCodeList = BOFactory.getLovBO().getAllRefferalBudgetCode(user.getSuper_user_key());
    refferalSchemeform.setRefferalBudgetCodeList(refferalBudgetCodeList);
    List refferalSchemeTypeList = BOFactory.getLovBO().getAllRefferalSchemeType(user.getSuper_user_key());
    refferalSchemeform.setRefferalSchemeTypeList(refferalSchemeTypeList);
    List ruleList = BOFactory.getLovBO().getAllRefferalRules(user.getSuper_user_key());
    refferalSchemeform.setRuleList(ruleList);
    toForm(refferalSchemeform, refferalscheme);
    

    refferalSchemeform.setUomList(Constant.uomList);
    refferalSchemeform.setCriteriaList(Constant.getCriteriaList());
    String readPreview = request.getParameter("readPreview");
    
    refferalSchemeform.setReadPreview(readPreview);
    
    request.setAttribute("updateReffScheme", "yes");
    return mapping.findForward("createrefferalscheme");
  }
  
  public ActionForward saveRefferalScheme(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveRefferalScheme method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user = (User)request.getSession().getAttribute("user_data");
    String schemeType = request.getParameter("schemeType");
    RefferalSchemeForm refferalSchemeform = (RefferalSchemeForm)form;
    RefferalScheme refferalscheme = new RefferalScheme();
    toValue(refferalscheme, refferalSchemeform);
    refferalscheme.setSchemeType(schemeType);
    refferalscheme.setSuper_user_key(user.getSuper_user_key());
    



    RefferalRedemptionRule refferalRedemptionRule = BOFactory.getLovBO().getRefferalRuleDetails(refferalSchemeform.getCreditAfterdays(), refferalSchemeform.getCriteria(), user.getSuper_user_key());
    if (refferalRedemptionRule == null)
    {
      refferalRedemptionRule = new RefferalRedemptionRule();
      refferalRedemptionRule.setCreditAfterdays(refferalSchemeform.getCreditAfterdays());
      refferalRedemptionRule.setCriteria(refferalSchemeform.getCriteria());
      refferalRedemptionRule.setCreatedDate(new Date());
      refferalRedemptionRule.setCreatedBy(user.getUserName());
      refferalRedemptionRule.setSuper_user_key(user.getSuper_user_key());
      String criValue = "";
      List crilist = Constant.getCriteriaList();
      for (int i = 0; i < crilist.size(); i++)
      {
        KeyValue m = (KeyValue)crilist.get(i);
        if ((m.getKey() != null) && (m.getKey().equalsIgnoreCase(refferalSchemeform.getCriteria())))
        {
          criValue = m.getValue();
          break;
        }
      }
      String desc = Constant.getResourceStringValue("admin.RefferelRedumptionRule.Credit_After_Days", user.getLocale()) + " " + refferalSchemeform.getCreditAfterdays() + " " + Constant.getResourceStringValue("admin.RefferelRedumptionRule.Credit_After_Days.days", user.getLocale()) + " off " + criValue;
      
      refferalRedemptionRule.setRuleDesc(desc);
      refferalRedemptionRule.setStatus("A");
      refferalRedemptionRule = LovOpsDAO.saveRule(refferalRedemptionRule);
    }
    refferalscheme.setRule(refferalRedemptionRule);
    refferalscheme = LovOpsDAO.saveReffaralScheme(refferalscheme);
    
    List refferalBudgetCodeList = BOFactory.getLovBO().getAllRefferalBudgetCode(user.getSuper_user_key());
    refferalSchemeform.setRefferalBudgetCodeList(refferalBudgetCodeList);
    List refferalSchemeTypeList = BOFactory.getLovBO().getAllRefferalSchemeType(user.getSuper_user_key());
    refferalSchemeform.setRefferalSchemeTypeList(refferalSchemeTypeList);
    List ruleList = BOFactory.getLovBO().getAllRefferalRules(user.getSuper_user_key());
    refferalSchemeform.setRuleList(ruleList);
    refferalSchemeform.setUomList(Constant.uomList);
    refferalSchemeform.setCriteriaList(Constant.getCriteriaList());
    toForm(refferalSchemeform, refferalscheme);
    

    String readPreview = request.getParameter("readPreview");
    refferalSchemeform.setReadPreview(readPreview);
    request.setAttribute("saveReffScheme", "yes");
    
    return mapping.findForward("createrefferalscheme");
  }
  
  public ActionForward deleteRefferalScheme(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteRefferalScheme method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user = (User)request.getSession().getAttribute("user_data");
    RefferalSchemeForm refferalSchemeform = (RefferalSchemeForm)form;
    
    String id = request.getParameter("id");
    try
    {
      BOFactory.getLovBO().deleteReferralScheme(new Long(id).longValue());
      

      request.setAttribute("deleteReffScheme", "yes");
      return mapping.findForward("createrefferalscheme");
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
  
  public ActionForward referralscemeselector(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    User user = (User)request.getSession().getAttribute("user_data");
    logger.info("Inside referralscemeselector method");
    String orgid = request.getParameter("orgid");
    String deptid = request.getParameter("deptid");
    String tempvalue = request.getParameter("tempvalue");
    String schemeType = request.getParameter("type");
    String boxnumber = request.getParameter("boxnumber");
    if (boxnumber != null) {
      request.setAttribute("boxnumber", boxnumber);
    }
    RefferalSchemeForm refferalSchemeform = (RefferalSchemeForm)form;
    List orgList = BOFactory.getLovBO().getAllOrganization(user.getSuper_user_key());
    refferalSchemeform.setOrgnizationList(orgList);
    refferalSchemeform.setSchemeType(schemeType);
    if (!StringUtils.isNullOrEmpty(orgid))
    {
      refferalSchemeform.setOrgId(new Long(orgid).longValue());
      request.setAttribute("orgredonly", "yes");
      refferalSchemeform.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(orgid));
      if (!StringUtils.isNullOrEmpty(deptid)) {
        refferalSchemeform.setDeptId(new Long(deptid).longValue());
      }
    }
    else
    {
      List deptlist = new ArrayList();
      refferalSchemeform.setDepartmentList(deptlist);
      if (orgList.size() > 0)
      {
        Organization org = (Organization)orgList.get(0);
        refferalSchemeform.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(org.getOrgId())));
      }
    }
    List refferalBudgetCodeList = BOFactory.getLovBO().getAllRefferalBudgetCode(user.getSuper_user_key());
    refferalSchemeform.setRefferalBudgetCodeList(refferalBudgetCodeList);
    List refferalSchemeTypeList = BOFactory.getLovBO().getAllRefferalSchemeType(user.getSuper_user_key());
    refferalSchemeform.setRefferalSchemeTypeList(refferalSchemeTypeList);
    List ruleList = BOFactory.getLovBO().getAllRefferalRules(user.getSuper_user_key());
    refferalSchemeform.setRuleList(ruleList);
    request.setAttribute("tempvalue", tempvalue);
    return mapping.findForward("referralscemeselector");
  }
  
  public ActionForward searchreferralscheme(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchreferralscheme method");
    User user = (User)request.getSession().getAttribute("user_data");
    String tempvalue = request.getParameter("tempvalue");
    String orgredonly = request.getParameter("orgredonly");
    String boxnumber = request.getParameter("boxnumber");
    if (boxnumber != null) {
      request.setAttribute("boxnumber", boxnumber);
    }
    if ((!StringUtils.isNullOrEmpty(orgredonly)) && (!orgredonly.equals("null"))) {
      request.setAttribute("orgredonly", "yes");
    }
    String orgid = request.getParameter("orgid");
    String deptid = request.getParameter("deptid");
    String schemeType = request.getParameter("type");
    
    RefferalSchemeForm refferalSchemeform = (RefferalSchemeForm)form;
    refferalSchemeform.setSchemeType(schemeType);
    if (!StringUtils.isNullOrEmpty(orgid)) {
      refferalSchemeform.setOrgId(new Long(orgid).longValue());
    }
    if (!StringUtils.isNullOrEmpty(deptid)) {
      refferalSchemeform.setDeptId(new Long(deptid).longValue());
    }
    String start = request.getParameter("start");
    String range = request.getParameter("range");
    String results = request.getParameter("results");
    
    int start1 = StringUtils.stringToInt(start, 0);
    int range1 = StringUtils.stringToInt(range, 15);
    
    int totaluser = 0;
    if (StringUtils.isNullOrEmpty(results)) {
      totaluser = RefferalDAO.getReferralSchemeByCriteraCount(user, refferalSchemeform.getRefferalScheme_Name(), refferalSchemeform.getRefferalSchemeTypeId(), refferalSchemeform.getOrgId(), refferalSchemeform.getDeptId(), refferalSchemeform.getRef_budgetId(), refferalSchemeform.getRuleId(), refferalSchemeform.getSchemeType());
    } else {
      totaluser = new Integer(results).intValue();
    }
    List referralSchemeList = RefferalDAO.getReferralSchemeByCritera(user, refferalSchemeform.getRefferalScheme_Name(), refferalSchemeform.getRefferalSchemeTypeId(), refferalSchemeform.getOrgId(), refferalSchemeform.getDeptId(), refferalSchemeform.getRef_budgetId(), refferalSchemeform.getRuleId(), refferalSchemeform.getSchemeType(), start1, range1);
    refferalSchemeform.setReferralSchemeList(referralSchemeList);
    


    List orgList = BOFactory.getLovBO().getAllOrganization(user.getSuper_user_key());
    refferalSchemeform.setOrgnizationList(orgList);
    List deptlist = new ArrayList();
    refferalSchemeform.setDepartmentList(deptlist);
    if (refferalSchemeform.getOrgId() != 0L)
    {
      refferalSchemeform.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(refferalSchemeform.getOrgId())));
    }
    else if (orgList.size() > 0)
    {
      Organization org = (Organization)orgList.get(0);
      refferalSchemeform.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(org.getOrgId())));
    }
    List refferalBudgetCodeList = BOFactory.getLovBO().getAllRefferalBudgetCode(user.getSuper_user_key());
    refferalSchemeform.setRefferalBudgetCodeList(refferalBudgetCodeList);
    List refferalSchemeTypeList = BOFactory.getLovBO().getAllRefferalSchemeType(user.getSuper_user_key());
    refferalSchemeform.setRefferalSchemeTypeList(refferalSchemeTypeList);
    List ruleList = BOFactory.getLovBO().getAllRefferalRules(user.getSuper_user_key());
    refferalSchemeform.setRuleList(ruleList);
    refferalSchemeform.setStart(String.valueOf(start1));
    refferalSchemeform.setRange(String.valueOf(range1));
    refferalSchemeform.setResults(String.valueOf(totaluser));
    refferalSchemeform.setDeptId(refferalSchemeform.getDeptId());
    refferalSchemeform.setOrgId(refferalSchemeform.getOrgId());
    request.setAttribute("tempvalue", tempvalue);
    return mapping.findForward("referralscemeselector");
  }
  
  public void toDelete(RefferalScheme refferalscheme, RefferalSchemeForm refferalSchemeform)
  {
    refferalscheme.setStatus("D");
  }
  
  public void toValue(RefferalScheme refferalscheme, RefferalSchemeForm refferalSchemeform)
  {
    refferalscheme.setRefferalScheme_Name(refferalSchemeform.getRefferalScheme_Name());
    refferalscheme.setRefferalScheme_Desc(refferalSchemeform.getRefferalScheme_Desc());
    refferalscheme.setRefferalScheme_Amount(refferalSchemeform.getRefferalScheme_Amount());
    if (refferalSchemeform.getRef_budgetId() != 0L)
    {
      RefferalBudgetCode refferalBudgetCode = new RefferalBudgetCode();
      refferalBudgetCode.setRef_budgetId(refferalSchemeform.getRef_budgetId());
      refferalscheme.setRefferalBudgetCode(refferalBudgetCode);
    }
    RefferalSchemeType refferalSchemeType = LovOpsDAO.getRefferalSchemeTypeByUOM(refferalSchemeform.getUomscheme());
    refferalscheme.setRefferalSchemeType(refferalSchemeType);
    

    refferalscheme.setUom(refferalSchemeform.getUom());
    refferalscheme.setStatus("A");
  }
  
  public void toForm(RefferalSchemeForm refferalSchemeform, RefferalScheme refferalscheme)
  {
    refferalSchemeform.setRefferalScheme_Id(refferalscheme.getRefferalScheme_Id());
    refferalSchemeform.setRefferalScheme_Name(refferalscheme.getRefferalScheme_Name());
    refferalSchemeform.setRefferalScheme_Desc(refferalscheme.getRefferalScheme_Desc());
    refferalSchemeform.setRefferalScheme_Amount(refferalscheme.getRefferalScheme_Amount());
    if (refferalscheme.getRefferalBudgetCode() != null) {
      refferalSchemeform.setRef_budgetId(refferalscheme.getRefferalBudgetCode().getRef_budgetId());
    }
    refferalSchemeform.setRefferalSchemeTypeId(refferalscheme.getRefferalSchemeType().getRefferalSchemeTypeId());
    refferalSchemeform.setUomscheme(refferalscheme.getRefferalSchemeType().getUom());
    
    RefferalRedemptionRule rule = refferalscheme.getRule();
    refferalSchemeform.setRuleId(refferalscheme.getRule().getRuleId());
    refferalSchemeform.setCreditAfterdays(rule.getCreditAfterdays());
    refferalSchemeform.setCriteria(rule.getCriteria());
    refferalSchemeform.setRulestr(rule.getRuleDesc());
    
    refferalSchemeform.setStatus(refferalscheme.getStatus());
    refferalSchemeform.setUom(refferalscheme.getUom());
    refferalSchemeform.setSchemeType(refferalscheme.getSchemeType());
    

    refferalSchemeform.setRefferalbudgetcode(refferalscheme.getRefferalBudgetCode());
    refferalSchemeform.setRule(refferalscheme.getRule());
    refferalSchemeform.setRefferalSchemeType(refferalscheme.getRefferalSchemeType());
  }
}
