package com.action;

import com.bean.Department;
import com.bean.Organization;
import com.bean.User;
import com.bean.UserGroup;
import com.bean.lov.KeyValue;
import com.bean.system.SystemRule;
import com.bean.system.SystemRuleUser;
import com.bo.BOFactory;
import com.bo.LovBO;
import com.bo.RuleTXBO;
import com.common.AppContextUtil;
import com.common.Common;
import com.dao.RuleDAO;
import com.form.SystemRuleForm;
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
import org.springframework.context.ApplicationContext;

public class SystemRuleAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(SystemRuleAction.class);
  
  public ActionForward createPublishCheckBox(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside createSystemrule method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    String orgId = request.getParameter("orgid");
    String rultypeval = request.getParameter("ruleTypeval");
    logger.info("rultypeval" + rultypeval);
    String ruleId = request.getParameter("ruleId");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    SystemRuleForm sysForm = (SystemRuleForm)form;
    sysForm.setRuletypeList(Constant.getSystemRuleTypeList(user1));
    List orgList = BOFactory.getLovBO().getAllOrganization(user1.getSuper_user_key());
    sysForm.setOrganizationList(orgList);
    List deptlist = new ArrayList();
    sysForm.setDepartmentList(deptlist);
    if (orgId != "0")
    {
      sysForm.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(orgId)));
    }
    else if (orgList.size() > 0)
    {
      Organization org = (Organization)orgList.get(0);
      sysForm.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(org.getOrgId())));
    }
    sysForm.setOrgId(new Long(orgId).longValue());
    if ((rultypeval != null) && (rultypeval.equals("PUBLISH_REQUISTIONS")))
    {
      if (new Long(ruleId).longValue() != 0L)
      {
        SystemRule sysRule = RuleDAO.getSystemRule(ruleId);
        sysForm.setPublishToEmpRef(sysRule.getPublishToEmpRef());
        sysForm.setPublishToExternal(sysRule.getPublishToExternal());
      }
      request.setAttribute("PUBLISH_REQUISTIONS", "yes");
    }
    else
    {
      request.setAttribute("PUBLISH_REQUISTIONS", "no");
    }
    if (new Long(ruleId).longValue() != 0L)
    {
      SystemRule sysRule = RuleDAO.getSystemRule(ruleId);
      if ((rultypeval != null) && (rultypeval.equals(sysRule.getRuleType()))) {
        sysForm.setRuleUsers(sysRule.getRuleUsers());
      } else {
        sysForm.setRuleUsers(null);
      }
    }
    logger.info("Inside createPublishCheckBox method");
    return mapping.findForward("createsysrule");
  }
  
  public ActionForward loadDepartments(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String orgId = request.getParameter("orgId");
    SystemRuleForm sysForm = (SystemRuleForm)form;
    sysForm.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(orgId));
    return mapping.findForward("deptlistforSystemRule");
  }
  
  public ActionForward sysruleorglist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside sysruleorglist method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    SystemRuleForm sysForm = (SystemRuleForm)form;
    return mapping.findForward("sysruleorglist");
  }
  
  public ActionForward sysruleorglistajax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside sysruleorglistajax method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    SystemRuleForm sysForm = (SystemRuleForm)form;
    String orgId = request.getParameter("orgId");
    if (!StringUtils.isNullOrEmpty(orgId)) {
      request.setAttribute("orgId", orgId.trim());
    }
    return mapping.findForward("sysruleorglistajax");
  }
  
  public ActionForward createSystemrule(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside createSystemrule method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    String orgId = request.getParameter("orgid");
    logger.info("Inside createSystemrule method orgId" + orgId);
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    SystemRuleForm sysForm = (SystemRuleForm)form;
    sysForm.setRuletypeList(Constant.getSystemRuleTypeList(user1));
    List orgList = BOFactory.getLovBO().getAllOrganization(user1.getSuper_user_key());
    sysForm.setOrganizationList(orgList);
    List deptlist = new ArrayList();
    sysForm.setDepartmentList(deptlist);
    if (orgId != "0")
    {
      List dlist = BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(orgId));
      if (dlist != null) {
        sysForm.setDepartmentList(dlist);
      }
    }
    else if (orgList.size() > 0)
    {
      Organization org = (Organization)orgList.get(0);
      List dlist = BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(orgId));
      if (dlist != null) {
        sysForm.setDepartmentList(dlist);
      }
    }
    sysForm.setOrgId(new Long(orgId).longValue());
    if ((sysForm.getRuletypeList() != null) && (sysForm.getRuletypeList().size() > 0))
    {
      KeyValue keyvalue = (KeyValue)sysForm.getRuletypeList().get(0);
      sysForm.setRuleType(keyvalue.getKey());
      if ((keyvalue.getKey() != null) && (keyvalue.getKey().equals(Common.SYSTEM_RULE_PUBLISH_REQUISTIONS_TO_VENDORS))) {
        request.setAttribute("PUBLISH_REQUISTIONS", "yes");
      } else {
        request.setAttribute("PUBLISH_REQUISTIONS", "no");
      }
    }
    else
    {
      request.setAttribute("PUBLISH_REQUISTIONS", "no");
    }
    return mapping.findForward("createsysrule");
  }
  
  public ActionForward editSystemRule(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside editSystemRule method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user1 = (User)request.getSession().getAttribute("user_data");
    String ruleId = request.getParameter("ruleid");
    String orgid = request.getParameter("orgid");
    logger.info("Inside editSystemRule method" + ruleId);
    logger.info("Inside editSystemRule method" + orgid);
    SystemRuleForm sysForm = (SystemRuleForm)form;
    sysForm.setRuletypeList(Constant.getSystemRuleTypeList(user1));
    List orgList = BOFactory.getLovBO().getAllOrganization(user1.getSuper_user_key());
    sysForm.setOrganizationList(orgList);
    List deptlist = new ArrayList();
    sysForm.setDepartmentList(deptlist);
    if (orgid != "0")
    {
      List dlist = BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(orgid));
      if (dlist != null) {
        sysForm.setDepartmentList(dlist);
      }
    }
    else if (orgList.size() > 0)
    {
      Organization org = (Organization)orgList.get(0);
      List dlist = BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(orgid));
      if (dlist != null) {
        sysForm.setDepartmentList(dlist);
      }
    }
    SystemRule sysRule = RuleDAO.getSystemRule(ruleId);
    logger.info("Inside editSystemRule sysRule" + sysRule);
    sysForm.setRuleUsers(sysRule.getRuleUsers());
    toForm(sysForm, sysRule);
    if ((sysRule.getPublishToEmpRef() != null) && (sysRule.getPublishToEmpRef().equals("Y"))) {
      sysForm.setPublishToEmpRef("Y");
    } else {
      sysForm.setPublishToEmpRef("N");
    }
    if ((sysRule.getPublishToExternal() != null) && (sysRule.getPublishToExternal().equals("Y"))) {
      sysForm.setPublishToExternal("Y");
    } else {
      sysForm.setPublishToExternal("N");
    }
    String idvalue = "";
    if (sysForm.getRuleUsers() != null) {
      for (int i = 0; i < sysForm.getRuleUsers().size(); i++)
      {
        SystemRuleUser sysuser = (SystemRuleUser)sysForm.getRuleUsers().get(i);
        if ((sysuser != null) && (sysuser.getIsGroup().equals("Y"))) {
          idvalue = idvalue + String.valueOf(sysuser.getUserGroup().getUsergrpId()) + ",";
        } else {
          idvalue = idvalue + String.valueOf(sysuser.getUser().getUserId()) + ",";
        }
      }
    }
    logger.info(" idvalue >>>> " + idvalue);
    sysForm.setIdlistvaluser(idvalue);
    if (sysRule.getRuleType().equals("PUBLISH_REQUISTIONS")) {
      request.setAttribute("PUBLISH_REQUISTIONS", "yes");
    }
    request.setAttribute("saveSystemRule", "no");
    return mapping.findForward("createsysrule");
  }
  
  public ActionForward saveSystemRule(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveSystemRule method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String orgid = request.getParameter("orgId");
    String deptId = request.getParameter("deptId");
    SystemRuleForm sysForm = (SystemRuleForm)form;
    

    SystemRule sysRule = new SystemRule();
    sysForm.setRuletypeList(Constant.getSystemRuleTypeList(user1));
    List orgList = BOFactory.getLovBO().getAllOrganization(user1.getSuper_user_key());
    sysForm.setOrganizationList(orgList);
    List deptlist = new ArrayList();
    sysForm.setDepartmentList(deptlist);
    if (orgid != "0")
    {
      sysForm.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(orgid)));
    }
    else if (orgList.size() > 0)
    {
      Organization org = (Organization)orgList.get(0);
      sysForm.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(org.getOrgId())));
    }
    String userids = request.getParameter("userids");
    toValue(sysRule, sysForm);
    logger.info("Inside saveSystemRule method" + sysForm.getOrgId() + sysForm.getDeptId() + sysForm.getRuleType());
    SystemRule sysruleexist = RuleDAO.isSystemRuleExist(sysForm.getOrgId(), sysForm.getDeptId(), sysForm.getRuleType());
    if (sysruleexist != null)
    {
      request.setAttribute("systemruleexist", "yes");
    }
    else
    {
      sysRule.setCreatedBy("admin");
      sysRule.setCreatedDate(new Date());
      String[] ids = StringUtils.tokenize(userids, ",");
      if (sysForm.getRuleType().equals(Common.SYSTEM_RULE_PUBLISH_REQUISTIONS_TO_VENDORS))
      {
        RuleDAO.SaveSystemRule(sysRule, ids);
      }
      else
      {
        String approveruserids = request.getParameter("approveruserids");
        
        List sysuserList = new ArrayList();
        setApproversList(sysRule.getSystemRuleId(), sysuserList, approveruserids);
        sysRule.setRuleUsers(sysuserList);
        
        ApplicationContext appContext = AppContextUtil.getAppcontext();
        RuleTXBO ruletxbo = (RuleTXBO)appContext.getBean("ruletxboProxy");
        logger.info("Inside editSystemRule sysRule" + ruletxbo);
        ruletxbo.saveSystemRule(sysRule, user1);
      }
      sysRule = RuleDAO.getSystemRule(String.valueOf(sysRule.getSystemRuleId()));
      logger.info("Inside editSystemRule sysRule" + sysRule);
      sysForm.setRuleUsers(sysRule.getRuleUsers());
      


      sysForm.setSystemRuleId(sysRule.getSystemRuleId());
      if ((sysRule.getPublishToEmpRef() != null) && (sysRule.getPublishToEmpRef().equals("Y"))) {
        sysForm.setPublishToEmpRef("Y");
      } else {
        sysForm.setPublishToEmpRef("N");
      }
      if ((sysRule.getPublishToExternal() != null) && (sysRule.getPublishToExternal().equals("Y"))) {
        sysForm.setPublishToExternal("Y");
      } else {
        sysForm.setPublishToExternal("N");
      }
      request.setAttribute("saveSystemRule", "yes");
    }
    if (sysRule.getRuleType().equals("PUBLISH_REQUISTIONS")) {
      request.setAttribute("PUBLISH_REQUISTIONS", "yes");
    } else {
      request.setAttribute("PUBLISH_REQUISTIONS", "no");
    }
    return mapping.findForward("createsysrule");
  }
  
  public ActionForward updateSystemRule(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updateSystemRule method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String ruleid = request.getParameter("ruleid");
    String orgid = request.getParameter("orgId");
    String deptId = request.getParameter("deptId");
    SystemRuleForm sysForm = (SystemRuleForm)form;
    SystemRule sysRule = RuleDAO.getSystemRule(ruleid);
    sysForm.setRuletypeList(Constant.getSystemRuleTypeList(user1));
    List orgList = BOFactory.getLovBO().getAllOrganization(user1.getSuper_user_key());
    sysForm.setOrganizationList(orgList);
    List deptlist = new ArrayList();
    sysForm.setDepartmentList(deptlist);
    if (orgid != "0")
    {
      sysForm.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(orgid)));
    }
    else if (orgList.size() > 0)
    {
      Organization org = (Organization)orgList.get(0);
      sysForm.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(org.getOrgId())));
    }
    String userids = request.getParameter("userids");
    
    SystemRule sysruleexist = RuleDAO.isSystemRuleExist(sysForm.getOrgId(), sysForm.getDeptId(), sysForm.getRuleType());
    if ((sysruleexist != null) && (sysruleexist.getSystemRuleId() != sysRule.getSystemRuleId()))
    {
      request.setAttribute("systemruleexist", "yes");
    }
    else
    {
      toValue(sysRule, sysForm);
      sysRule.setSystemRuleId(new Long(ruleid).longValue());
      sysRule.setUpdatedDate(new Date());
      sysRule.setUpdatedBy(user1.getUserName());
      if (sysForm.getRuleType().equals(Common.SYSTEM_RULE_PUBLISH_REQUISTIONS_TO_VENDORS))
      {
        String[] ids = StringUtils.tokenize(userids, ",");
        
        RuleDAO.deleteSystemRuleUser(new Long(ruleid).longValue());
        RuleDAO.UpdateSystemRule(sysRule, ids);
      }
      else
      {
        String approveruserids = request.getParameter("approveruserids");
        logger.info("Inside editSystemRule approveruserids" + approveruserids);
        List sysuserList = new ArrayList();
        setApproversList(sysRule.getSystemRuleId(), sysuserList, approveruserids);
        sysRule.setRuleUsers(sysuserList);
        
        ApplicationContext appContext = AppContextUtil.getAppcontext();
        RuleTXBO ruletxbo = (RuleTXBO)appContext.getBean("ruletxboProxy");
        
        ruletxbo.updateSystemRule(sysRule, user1);
      }
      sysRule = RuleDAO.getSystemRule(String.valueOf(sysRule.getSystemRuleId()));
      logger.info("Inside editSystemRule sysRule" + sysRule);
      sysForm.setRuleUsers(sysRule.getRuleUsers());
      
      sysForm.setDeptId(new Long(deptId).longValue());
      sysForm.setSystemRuleId(sysRule.getSystemRuleId());
      if ((sysRule.getPublishToEmpRef() != null) && (sysRule.getPublishToEmpRef().equals("Y"))) {
        sysForm.setPublishToEmpRef("Y");
      } else {
        sysForm.setPublishToEmpRef("N");
      }
      if ((sysRule.getPublishToExternal() != null) && (sysRule.getPublishToExternal().equals("Y"))) {
        sysForm.setPublishToExternal("Y");
      } else {
        sysForm.setPublishToExternal("N");
      }
      request.setAttribute("updateSystemRule", "yes");
    }
    if (sysRule.getRuleType().equals("PUBLISH_REQUISTIONS")) {
      request.setAttribute("PUBLISH_REQUISTIONS", "yes");
    } else {
      request.setAttribute("PUBLISH_REQUISTIONS", "no");
    }
    return mapping.findForward("createsysrule");
  }
  
  public void setApproversList(long sysruleId, List sysuserList, String appuserids)
  {
    String[] ids = StringUtils.tokenize(appuserids, ",");
    int k = 1;
    if (ids != null) {
      for (int i = 0; i < ids.length; i++)
      {
        String id = ids[i];
        String isgroup = id.substring(id.indexOf("|") + 1);
        String userid = id.substring(0, id.indexOf("|"));
        SystemRuleUser sysrule = new SystemRuleUser();
        sysrule.setSystemRuleId(sysruleId);
        sysrule.setLevelorder(k);
        if ((!StringUtils.isNullOrEmpty(isgroup)) && (isgroup.equalsIgnoreCase("G")))
        {
          sysrule.setIsGroup("Y");
          UserGroup usergrp = new UserGroup();
          usergrp.setUsergrpId(new Long(userid).longValue());
          sysrule.setUserGroup(usergrp);
        }
        else
        {
          sysrule.setIsGroup("N");
          User user = new User();
          user.setUserId(new Long(userid).longValue());
          sysrule.setUser(user);
        }
        sysuserList.add(sysrule);
        k++;
      }
    }
  }
  
  public ActionForward updatestatus(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updatestatus method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String ruleid = request.getParameter("ruleid");
    String status = request.getParameter("status");
    SystemRuleForm sysForm = (SystemRuleForm)form;
    SystemRule sysRule = RuleDAO.getSystemRule(ruleid);
    sysRule.setStatus(status);
    sysRule.setUpdatedBy(user1.getUserName());
    sysRule.setUpdatedDate(new Date());
    RuleDAO.UpdateSystemRuledeletetime(sysRule);
    return mapping.findForward("updatestatussysrule");
  }
  
  public ActionForward deleteSystemRule(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteSystemRule method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    SystemRuleForm sysForm = (SystemRuleForm)form;
    String ruleid = request.getParameter("ruleid");
    SystemRule sysRule = RuleDAO.getSystemRule(ruleid);
    String userids = request.getParameter("userids");
    String[] ids = StringUtils.tokenize(userids, ",");
    toDelete(sysRule, sysForm);
    RuleDAO.UpdateSystemRuledeletetime(sysRule);
    request.setAttribute("deleteSystemRule", "yes");
    return mapping.findForward("createsysrule");
  }
  
  public void toDelete(SystemRule sysRule, SystemRuleForm sysForm)
  {
    sysRule.setStatus("D");
  }
  
  public void toValue(SystemRule sysRule, SystemRuleForm sysForm)
  {
    sysRule.setRuleName(sysForm.getRuleName());
    sysRule.setRuleDesc(sysForm.getRuleDesc());
    if (sysForm.getOrgId() != 0L)
    {
      Organization org = new Organization();
      org.setOrgId(sysForm.getOrgId());
      sysRule.setOrganization(org);
    }
    if (sysForm.getDeptId() != 0L)
    {
      Department dept = new Department();
      dept.setDepartmentId(sysForm.getDeptId());
      sysRule.setDepartment(dept);
    }
    else
    {
      sysRule.setDepartment(null);
    }
    sysRule.setRuleType(sysForm.getRuleType());
    if ((sysForm.getPublishToEmpRef() != null) && (sysForm.getPublishToEmpRef().equals("Y"))) {
      sysRule.setPublishToEmpRef("Y");
    } else {
      sysRule.setPublishToEmpRef("N");
    }
    sysRule.setStatus("A");
    if ((sysForm.getPublishToExternal() != null) && (sysForm.getPublishToExternal().equals("Y"))) {
      sysRule.setPublishToExternal("Y");
    } else {
      sysRule.setPublishToExternal("N");
    }
    if ((sysForm.getEeoInfoIncluded() != null) && (sysForm.getEeoInfoIncluded().equals("Y"))) {
      sysRule.setEeoInfoIncluded("Y");
    } else {
      sysRule.setEeoInfoIncluded("N");
    }
  }
  
  public void toForm(SystemRuleForm sysForm, SystemRule sysRule)
  {
    sysForm.setSystemRuleId(sysRule.getSystemRuleId());
    sysForm.setRuleName(sysRule.getRuleName());
    sysForm.setRuleDesc(sysRule.getRuleDesc());
    sysForm.setOrgId(sysRule.getOrganization().getOrgId());
    if (sysRule.getDepartment() != null) {
      sysForm.setDeptId(sysRule.getDepartment().getDepartmentId());
    }
    sysForm.setRuleType(sysRule.getRuleType());
    sysForm.setPublishToEmpRef(sysRule.getPublishToEmpRef());
    sysRule.setStatus("A");
    sysForm.setPublishToExternal(sysRule.getPublishToEmpRef());
    sysForm.setEeoInfoIncluded(sysRule.getEeoInfoIncluded());
  }
}
