package com.action;

import com.bean.Department;
import com.bean.Organization;
import com.bean.RefferalBudgetCode;
import com.bean.User;
import com.bo.BOFactory;
import com.bo.LovBO;
import com.bo.OrganizationBO;
import com.common.Common;
import com.common.ValidationException;
import com.dao.LovOpsDAO;
import com.form.RefferalBudgetCodeForm;
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

public class RefferalBudgetCodeAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(BudgetCodeAction.class);
  
  public ActionForward refferalbudgetCodelist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    RefferalBudgetCodeForm budgetCodeForm = (RefferalBudgetCodeForm)form;
    request.setAttribute("refferalHome", "budgetcode");
    return mapping.findForward("refferalbudgetCodelist");
  }
  
  public ActionForward createrefferalbudgetCode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    RefferalBudgetCodeForm reffbudgetCodeForm = (RefferalBudgetCodeForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    List yearList = Constant.getYearsLovList();
    reffbudgetCodeForm.setYearsList(yearList);
    Calendar can = Calendar.getInstance();
    int currentYear = can.get(1);
    reffbudgetCodeForm.setRef_budgetYear(String.valueOf(currentYear));
    List orgList = BOFactory.getLovBO().getAllOrganization(user1.getSuper_user_key());
    reffbudgetCodeForm.setOrganizationList(orgList);
    List deptlist = new ArrayList();
    reffbudgetCodeForm.setDepartmentList(deptlist);
    if (orgList.size() > 0)
    {
      Organization org = (Organization)orgList.get(0);
      reffbudgetCodeForm.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(org.getOrgId())));
      reffbudgetCodeForm.setRef_budgetCurrency(org.getCurrencyCode());
    }
    String readPreview = request.getParameter("readPreview");
    reffbudgetCodeForm.setReadPreview(readPreview);
    request.setAttribute("saveRefferalBudgetCode", "no");
    return mapping.findForward("createrefferalbudgetCode");
  }
  
  public ActionForward saveRefferalBudgetCode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveBudgetCode method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user = (User)request.getSession().getAttribute("user_data");
    RefferalBudgetCodeForm refferalbudgetCodeForm = (RefferalBudgetCodeForm)form;
    RefferalBudgetCode reffbudgetCode = new RefferalBudgetCode();
    toValue(reffbudgetCode, refferalbudgetCodeForm);
    String readpreview = request.getParameter("readPreview");
    refferalbudgetCodeForm.setReadPreview(readpreview);
    
    reffbudgetCode.setSuper_user_key(user.getSuper_user_key());
    reffbudgetCode = LovOpsDAO.saveRefferalBudgetCode(reffbudgetCode);
    refferalbudgetCodeForm.setRef_budgetId(reffbudgetCode.getRef_budgetId());
    toForm(refferalbudgetCodeForm, reffbudgetCode);
    List yearList = Constant.getYearsLovList();
    refferalbudgetCodeForm.setYearsList(yearList);
    


    List orgList = BOFactory.getLovBO().getAllOrganization(user.getSuper_user_key());
    refferalbudgetCodeForm.setOrganizationList(orgList);
    List deptlist = new ArrayList();
    refferalbudgetCodeForm.setDepartmentList(deptlist);
    if (orgList.size() > 0)
    {
      Organization org = (Organization)orgList.get(0);
      refferalbudgetCodeForm.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(reffbudgetCode.getOrg().getOrgId())));
      refferalbudgetCodeForm.setRef_budgetCurrency(org.getCurrencyCode());
    }
    request.setAttribute("saveReffBudgetCode", "yes");
    return mapping.findForward("createrefferalbudgetCode");
  }
  
  public ActionForward updateRefferalBudgetCode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updateRefferalBudgetCode method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user = (User)request.getSession().getAttribute("user_data");
    RefferalBudgetCodeForm refferalbudgetCodeForm = (RefferalBudgetCodeForm)form;
    
    String id = request.getParameter("id");
    
    RefferalBudgetCode refferalbudgetCode = LovOpsDAO.getRefferalBudgetCode(id);
    toValue(refferalbudgetCode, refferalbudgetCodeForm);
    String readpreview = request.getParameter("readPreview");
    refferalbudgetCodeForm.setReadPreview(readpreview);
    refferalbudgetCode = LovOpsDAO.updateRefferalBudgetCode(refferalbudgetCode);
    toForm(refferalbudgetCodeForm, refferalbudgetCode);
    refferalbudgetCodeForm.setRef_budgetId(refferalbudgetCode.getRef_budgetId());
    
    List yearList = Constant.getYearsLovList();
    refferalbudgetCodeForm.setYearsList(yearList);
    


    List orgList = BOFactory.getLovBO().getAllOrganization(user.getSuper_user_key());
    refferalbudgetCodeForm.setOrganizationList(orgList);
    List deptlist = new ArrayList();
    refferalbudgetCodeForm.setDepartmentList(deptlist);
    if (orgList.size() > 0)
    {
      Organization org = (Organization)orgList.get(0);
      refferalbudgetCodeForm.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(refferalbudgetCode.getOrg().getOrgId())));
      refferalbudgetCodeForm.setRef_budgetCurrency(org.getCurrencyCode());
    }
    request.setAttribute("updateReffBudgetCode", "yes");
    return mapping.findForward("createrefferalbudgetCode");
  }
  
  public ActionForward DeleteRefferelBudgetCode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside DeleteRefferelBudgetCode method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user = (User)request.getSession().getAttribute("user_data");
    RefferalBudgetCodeForm refferalbudgetCodeForm = (RefferalBudgetCodeForm)form;
    
    String id = request.getParameter("id");
    try
    {
      BOFactory.getLovBO().deleteReferralBudgetCode(new Long(id).longValue());
      

      request.setAttribute("deleteReffBudgetCode", "yes");
      return mapping.findForward("createrefferalbudgetCode");
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
  
  public void toDelete(RefferalBudgetCode RefferalBudgetCode, RefferalBudgetCodeForm RefferalBudgetCodeForm)
  {
    RefferalBudgetCode.setStatus("D");
  }
  
  public ActionForward editRefferalbudgetCode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside editRefferalbudgetCode method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user1 = (User)request.getSession().getAttribute("user_data");
    
    String id = request.getParameter("id");
    
    RefferalBudgetCodeForm refferalbudgetCodeForm = (RefferalBudgetCodeForm)form;
    
    RefferalBudgetCode refferalbudgetCode = LovOpsDAO.getRefferalBudgetCode(id);
    
    toForm(refferalbudgetCodeForm, refferalbudgetCode);
    String readpreview = request.getParameter("readPreview");
    refferalbudgetCodeForm.setReadPreview(readpreview);
    List yearList = Constant.getYearsLovList();
    refferalbudgetCodeForm.setYearsList(yearList);
    List orgList = BOFactory.getLovBO().getAllOrganization(user1.getSuper_user_key());
    refferalbudgetCodeForm.setOrganizationList(orgList);
    
    refferalbudgetCodeForm.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(refferalbudgetCode.getOrg().getOrgId())));
    

    return mapping.findForward("createrefferalbudgetCode");
  }
  
  public void toValue(RefferalBudgetCode refferalbudgetCode, RefferalBudgetCodeForm refferalbudgetCodeForm)
  {
    refferalbudgetCode.setRef_budgetCode(refferalbudgetCodeForm.getRef_budgetCode());
    refferalbudgetCode.setRef_budgetCentreName(refferalbudgetCodeForm.getRef_budgetCentreName());
    refferalbudgetCode.setRef_budgetCentreDesc(refferalbudgetCodeForm.getRef_budgetCentreDesc());
    refferalbudgetCode.setRef_budgetMonth(refferalbudgetCodeForm.getRef_budgetMonth());
    refferalbudgetCode.setRef_budgetQuarter(refferalbudgetCodeForm.getRef_budgetQuarter());
    refferalbudgetCode.setRef_budgetYear(new Integer(refferalbudgetCodeForm.getRef_budgetYear()).intValue());
    refferalbudgetCode.setStatus("A");
    refferalbudgetCode.setRef_budgetamount(refferalbudgetCodeForm.getRef_budgetamount());
    Organization org = new Organization();
    org.setOrgId(refferalbudgetCodeForm.getOrgId());
    refferalbudgetCode.setOrg(org);
    if (refferalbudgetCodeForm.getDepartmentId() != 0L)
    {
      Department dept = new Department();
      dept.setDepartmentId(refferalbudgetCodeForm.getDepartmentId());
      refferalbudgetCode.setDepartment(dept);
    }
    else if (refferalbudgetCodeForm.getDepartmentId() == 0L)
    {
      Department dept = null;
      refferalbudgetCode.setDepartment(dept);
    }
    Organization org1 = BOFactory.getOrganizationBO().getOrganization(String.valueOf(refferalbudgetCodeForm.getOrgId()));
    refferalbudgetCode.setRef_budgetCurrency(org1.getCurrencyCode());
  }
  
  public void toForm(RefferalBudgetCodeForm refferalbudgetCodeForm, RefferalBudgetCode refferalbudgetCode)
  {
    refferalbudgetCodeForm.setRef_budgetId(refferalbudgetCode.getRef_budgetId());
    refferalbudgetCodeForm.setRef_budgetCode(refferalbudgetCode.getRef_budgetCode());
    refferalbudgetCodeForm.setRef_budgetCentreName(refferalbudgetCode.getRef_budgetCentreName());
    refferalbudgetCodeForm.setRef_budgetCentreDesc(refferalbudgetCode.getRef_budgetCentreDesc());
    refferalbudgetCodeForm.setRef_budgetMonth(refferalbudgetCode.getRef_budgetMonth());
    refferalbudgetCodeForm.setRef_budgetQuarter(refferalbudgetCode.getRef_budgetQuarter());
    refferalbudgetCodeForm.setRef_budgetYear(String.valueOf(refferalbudgetCode.getRef_budgetYear()));
    refferalbudgetCodeForm.setRef_budgetamount(refferalbudgetCode.getRef_budgetamount());
    refferalbudgetCodeForm.setOrgId(refferalbudgetCode.getOrg().getOrgId());
    if (refferalbudgetCode.getDepartment() != null) {
      refferalbudgetCodeForm.setDepartmentId(refferalbudgetCode.getDepartment().getDepartmentId());
    }
    refferalbudgetCodeForm.setRef_budgetCurrency(refferalbudgetCode.getRef_budgetCurrency());
  }
}
