package com.action;

import com.bean.RefferalRedemptionRule;
import com.bean.User;
import com.bo.BOFactory;
import com.bo.LovBO;
import com.dao.LovOpsDAO;
import com.form.RefferalRedemptionRuleForm;
import com.resources.Constant;
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

public class RuleLoveListAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(JobRequistionAction.class);
  
  public ActionForward ruleLovelist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    RefferalRedemptionRuleForm refferalRedemptionRuleForm = (RefferalRedemptionRuleForm)form;
    request.setAttribute("refferalHome", "redemptionrule");
    return mapping.findForward("refferalredemptionlist");
  }
  
  public ActionForward createRedumptionrule(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside createRedumptionrule method ");
    RefferalRedemptionRuleForm refferalRedemptionRuleForm = (RefferalRedemptionRuleForm)form;
    User user = (User)request.getSession().getAttribute("user_data");
    String readpreview = request.getParameter("readPreview");
    refferalRedemptionRuleForm.setReadPreview(readpreview);
    List orgList = BOFactory.getLovBO().getAllOrganization(user.getSuper_user_key());
    refferalRedemptionRuleForm.setOrgnizationList(orgList);
    refferalRedemptionRuleForm.setCriteriaList(Constant.getCriteriaList());
    logger.info("getCriteriaList : " + refferalRedemptionRuleForm.getCriteriaList().size());
    return mapping.findForward("createrule");
  }
  
  public ActionForward saveRule(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveRule method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user = (User)request.getSession().getAttribute("user_data");
    RefferalRedemptionRuleForm refferalRedemptionRuleForm = (RefferalRedemptionRuleForm)form;
    RefferalRedemptionRule refferalRedemptionRule = new RefferalRedemptionRule();
    String readpreview = request.getParameter("readPreview");
    toValue(refferalRedemptionRule, refferalRedemptionRuleForm);
    refferalRedemptionRule.setCreatedBy(user.getUserName());
    refferalRedemptionRule.setSuper_user_key(user.getSuper_user_key());
    refferalRedemptionRule = LovOpsDAO.saveRule(refferalRedemptionRule);
    refferalRedemptionRuleForm.setReadPreview(readpreview);
    refferalRedemptionRuleForm.setRuleId(refferalRedemptionRule.getRuleId());
    List orgList = BOFactory.getLovBO().getAllOrganization(user.getSuper_user_key());
    refferalRedemptionRuleForm.setOrgnizationList(orgList);
    refferalRedemptionRuleForm.setCriteriaList(Constant.getCriteriaList());
    request.setAttribute("savedRule", "yes");
    return mapping.findForward("createrule");
  }
  
  public ActionForward updateRule(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updateRule method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user = (User)request.getSession().getAttribute("user_data");
    RefferalRedemptionRuleForm refferalRedemptionRuleForm = (RefferalRedemptionRuleForm)form;
    String id = request.getParameter("id");
    String readpreview = request.getParameter("readPreview");
    RefferalRedemptionRule refferalRedemptionRule = BOFactory.getLovBO().getRefferalRuleDetails(id);
    toValue(refferalRedemptionRule, refferalRedemptionRuleForm);
    refferalRedemptionRule.setCreatedBy(user.getUserName());
    refferalRedemptionRule = LovOpsDAO.updateRefferalRule(refferalRedemptionRule);
    refferalRedemptionRuleForm.setReadPreview(readpreview);
    refferalRedemptionRuleForm.setRuleId(refferalRedemptionRule.getRuleId());
    List orgList = BOFactory.getLovBO().getAllOrganization(user.getSuper_user_key());
    refferalRedemptionRuleForm.setOrgnizationList(orgList);
    refferalRedemptionRuleForm.setCriteriaList(Constant.getCriteriaList());
    request.setAttribute("updateRule", "yes");
    return mapping.findForward("createrule");
  }
  
  public ActionForward editRule(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside editRule method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user = (User)request.getSession().getAttribute("user_data");
    String ruleId = request.getParameter("id");
    logger.info("Inside editRule method refferalRedemptionRule" + ruleId);
    RefferalRedemptionRuleForm refferalRedemptionRuleForm = (RefferalRedemptionRuleForm)form;
    RefferalRedemptionRule refferalRedemptionRule = BOFactory.getLovBO().getRefferalRuleDetails(ruleId.trim());
    toForm(refferalRedemptionRuleForm, refferalRedemptionRule);
    String readpreview = request.getParameter("readPreview");
    List orgList = BOFactory.getLovBO().getAllOrganization(user.getSuper_user_key());
    refferalRedemptionRuleForm.setOrgnizationList(orgList);
    refferalRedemptionRuleForm.setCriteriaList(Constant.getCriteriaList());
    refferalRedemptionRuleForm.setReadPreview(readpreview);
    logger.info("Inside editRule method refferalRedemptionRule" + refferalRedemptionRule.getRuleId());
    return mapping.findForward("createrule");
  }
  
  public ActionForward dedeleteRule(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside projectCodedelete method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    RefferalRedemptionRuleForm refferalRedemptionRuleForm = (RefferalRedemptionRuleForm)form;
    String ruleId = request.getParameter("id");
    RefferalRedemptionRule refferalRedemptionRule = BOFactory.getLovBO().getRefferalRuleDetails(ruleId);
    toDelete(refferalRedemptionRule, refferalRedemptionRuleForm);
    refferalRedemptionRule = LovOpsDAO.updateRefferalRule(refferalRedemptionRule);
    request.setAttribute("deleteRule", "yes");
    return mapping.findForward("createrule");
  }
  
  public void toForm(RefferalRedemptionRuleForm refferalRedemptionRuleForm, RefferalRedemptionRule refferalRedemptionRule)
  {
    refferalRedemptionRuleForm.setRuleId(refferalRedemptionRule.getRuleId());
    refferalRedemptionRuleForm.setRuleName(refferalRedemptionRule.getRuleName());
    refferalRedemptionRuleForm.setRuleDesc(refferalRedemptionRule.getRuleDesc());
    refferalRedemptionRuleForm.setCreditAfterdays(refferalRedemptionRule.getCreditAfterdays());
    

    refferalRedemptionRuleForm.setCriteria(refferalRedemptionRule.getCriteria());
  }
  
  public void toValue(RefferalRedemptionRule refferalRedemptionRule, RefferalRedemptionRuleForm refferalRedemptionRuleForm)
  {
    refferalRedemptionRule.setRuleName(refferalRedemptionRuleForm.getRuleName());
    refferalRedemptionRule.setRuleDesc(refferalRedemptionRuleForm.getRuleDesc());
    refferalRedemptionRule.setCreditAfterdays(refferalRedemptionRuleForm.getCreditAfterdays());
    




    refferalRedemptionRule.setCriteria(refferalRedemptionRuleForm.getCriteria());
    refferalRedemptionRule.setStatus("A");
    refferalRedemptionRule.setCreatedDate(new Date());
  }
  
  public void toDelete(RefferalRedemptionRule refferalRedemptionRule, RefferalRedemptionRuleForm refferalRedemptionRuleForm)
  {
    refferalRedemptionRule.setStatus("D");
  }
}
