package com.action;

import com.bean.BudgetCode;
import com.bean.Department;
import com.bean.Organization;
import com.bean.User;
import com.bo.BOFactory;
import com.bo.LovBO;
import com.common.Common;
import com.common.ValidationException;
import com.dao.LovOpsDAO;
import com.form.BudgetCodeForm;
import com.resources.Constant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class BudgetCodeAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(BudgetCodeAction.class);
  
  public ActionForward budgetCodelist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    BudgetCodeForm budgetCodeForm = (BudgetCodeForm)form;
    
    return mapping.findForward("budgetCodelist");
  }
  
  public ActionForward createbudgetCode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    BudgetCodeForm budgetCodeForm = (BudgetCodeForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    List yearList = Constant.getYearsLovList();
    budgetCodeForm.setYearsList(yearList);
    Calendar can = Calendar.getInstance();
    int currentYear = can.get(1);
    budgetCodeForm.setBudgetYear(String.valueOf(currentYear));
    List orgList = BOFactory.getLovBO().getAllOrganization(user1.getSuper_user_key());
    budgetCodeForm.setOrganizationList(orgList);
    List deptlist = new ArrayList();
    budgetCodeForm.setDepartmentList(deptlist);
    if (orgList.size() > 0)
    {
      Organization org = (Organization)orgList.get(0);
      budgetCodeForm.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(org.getOrgId())));
      budgetCodeForm.setBudgetCurrency(org.getCurrencyCode());
    }
    return mapping.findForward("createbudgetCode");
  }
  
  public ActionForward saveBudgetCode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveBudgetCode method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user = (User)request.getSession().getAttribute("user_data");
    BudgetCodeForm budgetCodeForm = (BudgetCodeForm)form;
    BudgetCode budgetCode = new BudgetCode();
    toValue(budgetCode, budgetCodeForm);
    budgetCode.setSuper_user_key(user.getSuper_user_key());
    LovOpsDAO.saveBudgetCode(budgetCode);
    
    request.setAttribute("saveBudgetCode", "yes");
    return mapping.findForward("createbudgetCode");
  }
  
  public ActionForward updateBudgetCode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updateBudgetCode method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    BudgetCodeForm budgetCodeForm = (BudgetCodeForm)form;
    
    String id = request.getParameter("id");
    
    BudgetCode budgetCode = LovOpsDAO.getBudgetCode(id);
    toValue(budgetCode, budgetCodeForm);
    
    budgetCode = LovOpsDAO.updateBudgetCode(budgetCode);
    
    request.setAttribute("updateBudgetCode", "yes");
    return mapping.findForward("createbudgetCode");
  }
  
  public ActionForward editbudgetCode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside editbudgetCode method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    String id = request.getParameter("id");
    
    BudgetCodeForm budgetCodeForm = (BudgetCodeForm)form;
    
    BudgetCode budgetCode = LovOpsDAO.getBudgetCode(id);
    
    toForm(budgetCodeForm, budgetCode);
    String readpreview = request.getParameter("readPreview");
    budgetCodeForm.setReadPreview(readpreview);
    List yearList = Constant.getYearsLovList();
    budgetCodeForm.setYearsList(yearList);
    List orgList = BOFactory.getLovBO().getAllOrganization(user1.getSuper_user_key());
    budgetCodeForm.setOrganizationList(orgList);
    List deptlist = new ArrayList();
    
    budgetCodeForm.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(budgetCode.getOrg().getOrgId())));
    

    return mapping.findForward("createbudgetCode");
  }
  
  public ActionForward DeleteBudgetCode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside DeleteBudgetCode method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user = (User)request.getSession().getAttribute("user_data");
    BudgetCodeForm BudgetCodeForm = (BudgetCodeForm)form;
    
    String id = request.getParameter("id");
    try
    {
      BOFactory.getLovBO().deleteBudgetCode(new Long(id).longValue());
      

      request.setAttribute("deleteBudgetCode", "yes");
      return mapping.findForward("createbudgetCode");
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
  
  public void toDelete(BudgetCode BudgetCode, BudgetCodeForm BudgetCodeForm)
  {
    BudgetCode.setStatus("D");
  }
  
  public void toValue(BudgetCode budgetCode, BudgetCodeForm budgetCodeForm)
  {
    budgetCode.setBudgetCode(budgetCodeForm.getBudgetCode());
    budgetCode.setBudgetCentreName(budgetCodeForm.getBudgetCentreName());
    budgetCode.setBudgetCentreDesc(budgetCodeForm.getBudgetCentreDesc());
    budgetCode.setBudgetMonth(budgetCodeForm.getBudgetMonth());
    budgetCode.setBudgetQuarter(budgetCodeForm.getBudgetQuarter());
    budgetCode.setBudgetYear(new Integer(budgetCodeForm.getBudgetYear()).intValue());
    budgetCode.setStatus("A");
    budgetCode.setBudgetamount(budgetCodeForm.getBudgetamount());
    Organization org = new Organization();
    org.setOrgId(budgetCodeForm.getOrgId());
    if (budgetCodeForm.getDepartmentId() != 0L)
    {
      Department dept = new Department();
      dept.setDepartmentId(budgetCodeForm.getDepartmentId());
      
      budgetCode.setDepartment(dept);
    }
    budgetCode.setOrg(org);
    budgetCode.setBudgetCurrency(budgetCodeForm.getBudgetCurrency());
  }
  
  public void toForm(BudgetCodeForm budgetCodeForm, BudgetCode budgetCode)
  {
    budgetCodeForm.setBudgetId(budgetCode.getBudgetId());
    budgetCodeForm.setBudgetCode(budgetCode.getBudgetCode());
    budgetCodeForm.setBudgetCentreName(budgetCode.getBudgetCentreName());
    budgetCodeForm.setBudgetCentreDesc(budgetCode.getBudgetCentreDesc());
    budgetCodeForm.setBudgetMonth(budgetCode.getBudgetMonth());
    budgetCodeForm.setBudgetQuarter(budgetCode.getBudgetQuarter());
    budgetCodeForm.setBudgetYear(String.valueOf(budgetCode.getBudgetYear()));
    budgetCodeForm.setBudgetamount(budgetCode.getBudgetamount());
    budgetCodeForm.setOrgId(budgetCode.getOrg().getOrgId());
    budgetCodeForm.setOrganizationStr(budgetCode.getOrg().getOrgName());
    if (budgetCode.getDepartment() != null)
    {
      budgetCodeForm.setDepartmentId(budgetCode.getDepartment().getDepartmentId());
      budgetCodeForm.setDepartmentStr(budgetCode.getDepartment().getDepartmentName());
    }
    budgetCodeForm.setBudgetCurrency(budgetCode.getBudgetCurrency());
  }
}
