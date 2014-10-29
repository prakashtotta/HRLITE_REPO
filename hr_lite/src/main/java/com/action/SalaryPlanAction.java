package com.action;

import com.bean.SalaryPlan;
import com.bean.User;
import com.bo.BOFactory;
import com.bo.LovBO;
import com.common.Common;
import com.common.ValidationException;
import com.dao.LovOpsDAO;
import com.form.SalaryForm;
import com.resources.Constant;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class SalaryPlanAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(SalaryPlanAction.class);
  
  public ActionForward createSalary(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    SalaryForm salaryForm = (SalaryForm)form;
    String readpreview = request.getParameter("readPreview");
    salaryForm.setReadPreview(readpreview);
    List currencyList = BOFactory.getLovBO().getAllCurrencies();
    salaryForm.setCurrencyCodeList(currencyList);
    return mapping.findForward("createSalary");
  }
  
  public ActionForward saveslaryplan(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveslaryplan method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user = (User)request.getSession().getAttribute("user_data");
    SalaryForm salaryForm = (SalaryForm)form;
    SalaryPlan slp = new SalaryPlan();
    toValue(slp, salaryForm);
    String readpreview = request.getParameter("readPreview");
    salaryForm.setReadPreview(readpreview);
    slp.setSuper_user_key(user.getSuper_user_key());
    LovOpsDAO.saveSalary(slp);
    List currencyList = BOFactory.getLovBO().getAllCurrencies();
    salaryForm.setCurrencyCodeList(currencyList);
    request.setAttribute("savesalary", "savesalary");
    
    return mapping.findForward("createSalary");
  }
  
  public ActionForward updateslaryplan(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updateslaryplan method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    SalaryForm salaryForm = (SalaryForm)form;
    
    String id = request.getParameter("id");
    SalaryPlan slp = LovOpsDAO.getSalary(id);
    toValue(slp, salaryForm);
    String readpreview = request.getParameter("readPreview");
    salaryForm.setReadPreview(readpreview);
    slp = LovOpsDAO.updateSalary(slp);
    List currencyList = BOFactory.getLovBO().getAllCurrencies();
    salaryForm.setCurrencyCodeList(currencyList);
    request.setAttribute("updatesalary", "updatesalary");
    
    return mapping.findForward("createSalary");
  }
  
  public ActionForward salarydelete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside salarydelete method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user = (User)request.getSession().getAttribute("user_data");
    String planId = request.getParameter("planId");
    try
    {
      BOFactory.getLovBO().deleteSalaryPlan(new Long(planId).longValue());
      


      request.setAttribute("deletesalary", "yes");
      return mapping.findForward("createSalary");
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
  
  public void toValue(SalaryPlan salaryPlan, SalaryForm salaryForm)
  {
    salaryPlan.setSalaryPlanName(salaryForm.getSalaryPlanName());
    salaryPlan.setSalaryPlanDesc(salaryForm.getSalaryPlanDesc());
    salaryPlan.setFromRangeAmount(salaryForm.getFromRangeAmount());
    salaryPlan.setToRangeAmount(salaryForm.getToRangeAmount());
    salaryPlan.setCurrencyCode(salaryForm.getCurrencyCode());
    salaryPlan.setEffectiveStartDate(salaryForm.getEffectiveStartDate());
    salaryPlan.setEffectiveEndDate(salaryForm.getEffectiveEndDate());
    salaryPlan.setSalaryPlanYear(salaryForm.getSalaryPlanYear());
    salaryPlan.setStatus("A");
  }
  
  public ActionForward salarylist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    SalaryForm salaryForm = (SalaryForm)form;
    
    return mapping.findForward("salarylist");
  }
  
  public ActionForward editsaaryplan(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside editsaaryplan method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    String salaryplanId = request.getParameter("planId");
    SalaryForm salaryForm = (SalaryForm)form;
    SalaryPlan salaryPlan = LovOpsDAO.getSalaryDetails(salaryplanId);
    toForm(salaryForm, salaryPlan);
    List currencyList = BOFactory.getLovBO().getAllCurrencies();
    salaryForm.setCurrencyCodeList(currencyList);
    String redpreview = request.getParameter("readPreview");
    salaryForm.setReadPreview(redpreview);
    return mapping.findForward("createSalary");
  }
  
  public void toForm(SalaryForm salaryForm, SalaryPlan salaryPlan)
  {
    salaryForm.setSalaryplanId(salaryPlan.getSalaryplanId());
    salaryForm.setSalaryPlanName(salaryPlan.getSalaryPlanName());
    salaryForm.setSalaryPlanDesc(salaryPlan.getSalaryPlanDesc());
    salaryForm.setFromRangeAmount(salaryPlan.getFromRangeAmount());
    salaryForm.setToRangeAmount(salaryPlan.getToRangeAmount());
    salaryForm.setCurrencyCode(salaryPlan.getCurrencyCode());
    salaryForm.setStatus(salaryPlan.getStatus());
    salaryForm.setEffectiveStartDate(salaryPlan.getEffectiveStartDate());
    salaryForm.setEffectiveEndDate(salaryPlan.getEffectiveEndDate());
    salaryForm.setSalaryPlanYear(salaryPlan.getSalaryPlanYear());
  }
}
