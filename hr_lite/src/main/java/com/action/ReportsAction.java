package com.action;

import com.bean.Department;
import com.bean.Organization;
import com.bean.User;
import com.bo.BOFactory;
import com.bo.JobRequistionBO;
import com.bo.LovBO;
import com.bo.UserBO;
import com.form.ReportForm;
import com.resources.Constant;
import com.util.StringUtils;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ReportsAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(ReportsAction.class);
  
  public ActionForward firstpage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside firstpage method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    return mapping.findForward("firstpage");
  }
  
  public ActionForward timetofillReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside timetofillReport method");
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user1 = (User)request.getSession().getAttribute("user_data");
    ReportForm rForm = (ReportForm)form;
    

    List jobtitleList = new ArrayList();
    jobtitleList = BOFactory.getJobRequistionBO().getJobTitlesActiveAndOpenListAssigedToUser(user1);
    if (jobtitleList.size() > 0) {
      rForm.setJobtitleList(jobtitleList);
    } else {
      rForm.setJobtitleList(jobtitleList);
    }
    List jobgradeList = new ArrayList();
    
    jobgradeList = BOFactory.getLovBO().getAllJobGradeDetails(user1.getSuper_user_key());
    if (jobgradeList.size() > 0) {
      rForm.setJobGradeList(jobgradeList);
    } else {
      rForm.setJobGradeList(jobgradeList);
    }
    List yearList = Constant.getYearsLovList();
    rForm.setYearList(yearList);
    List hiringMgrList = new ArrayList();
    List recruiterList = new ArrayList();
    List hiringMgrListnew = new ArrayList();
    List recruiterListnew = new ArrayList();
    

    List orgList = BOFactory.getLovBO().getAllOrganization(user1.getSuper_user_key());
    rForm.setOrganizationList(orgList);
    List deptlist = new ArrayList();
    rForm.setDepartmentList(deptlist);
    if (orgList.size() > 0)
    {
      Organization org = (Organization)orgList.get(0);
      
      deptlist = BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(org.getOrgId()));
      if (deptlist.size() > 0)
      {
        rForm.setDepartmentList(deptlist);
        Department department = (Department)deptlist.get(0);
        rForm.setDepartmentId(0L);
      }
      rForm.setOrgId(org.getOrgId());
    }
    return mapping.findForward("timetofill");
  }
  
  public ActionForward vendorPerformanceComparisonReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside vendorPerformanceComparisonReport method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ReportForm rForm = (ReportForm)form;
    List vendorList = new ArrayList();
    vendorList = BOFactory.getUserBO().getAllVendorList(user1);
    if (vendorList.size() > 0) {
      rForm.setVendorList(vendorList);
    } else {
      rForm.setVendorList(vendorList);
    }
    List yearList = Constant.getYearsLovList();
    rForm.setYearList(yearList);
    Calendar can = Calendar.getInstance();
    


    return mapping.findForward("vendorPerformanceComparison");
  }
  
  public ActionForward sourcesOfResumeReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside sourcesOfResumeReport method");
    ReportForm rForm = (ReportForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    
    List orgList = BOFactory.getLovBO().getAllOrganization(user1.getSuper_user_key());
    rForm.setOrganizationList(orgList);
    List deptlist = new ArrayList();
    rForm.setDepartmentList(deptlist);
    if (orgList.size() > 0)
    {
      Organization org = (Organization)orgList.get(0);
      
      deptlist = BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(org.getOrgId()));
      if (deptlist.size() > 0)
      {
        rForm.setDepartmentList(deptlist);
        Department department = (Department)deptlist.get(0);
        rForm.setDepartmentId(0L);
      }
      rForm.setOrgId(org.getOrgId());
    }
    List yearList = Constant.getYearsLovList();
    rForm.setYearList(yearList);
    Calendar can = Calendar.getInstance();
    
    return mapping.findForward("sourcesOfResume");
  }
  
  public ActionForward recruiterEfficiencyReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside recruiterEfficiencyReport method");
    ReportForm rForm = (ReportForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    

    List orgList = BOFactory.getLovBO().getAllOrganization(user1.getSuper_user_key());
    rForm.setOrganizationList(orgList);
    List deptlist = new ArrayList();
    rForm.setDepartmentList(deptlist);
    if (orgList.size() > 0)
    {
      Organization org = (Organization)orgList.get(0);
      
      deptlist = BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(org.getOrgId()));
      if (deptlist.size() > 0)
      {
        rForm.setDepartmentList(deptlist);
        Department department = (Department)deptlist.get(0);
        rForm.setDepartmentId(0L);
      }
      rForm.setOrgId(org.getOrgId());
    }
    List yearList = Constant.getYearsLovList();
    rForm.setYearList(yearList);
    Calendar can = Calendar.getInstance();
    



    return mapping.findForward("recruiterEfficiency");
  }
  
  public ActionForward offervsacceptance(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside offervsacceptance method");
    ReportForm rForm = (ReportForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    
    List orgList = BOFactory.getLovBO().getAllOrganization(user1.getSuper_user_key());
    rForm.setOrganizationList(orgList);
    List deptlist = new ArrayList();
    rForm.setDepartmentList(deptlist);
    if (orgList.size() > 0)
    {
      Organization org = (Organization)orgList.get(0);
      
      deptlist = BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(org.getOrgId()));
      if (deptlist.size() > 0)
      {
        rForm.setDepartmentList(deptlist);
        Department department = (Department)deptlist.get(0);
        rForm.setDepartmentId(0L);
      }
      rForm.setOrgId(org.getOrgId());
    }
    List yearList = Constant.getYearsLovList();
    rForm.setYearList(yearList);
    
    return mapping.findForward("offervsacceptance");
  }
  
  public ActionForward eeoreport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside eeoreport method");
    ReportForm rForm = (ReportForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    
    List orgList = BOFactory.getLovBO().getAllOrganization(user1.getSuper_user_key());
    rForm.setOrganizationList(orgList);
    List deptlist = new ArrayList();
    rForm.setDepartmentList(deptlist);
    if (orgList.size() > 0)
    {
      Organization org = (Organization)orgList.get(0);
      
      deptlist = BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(org.getOrgId()));
      if (deptlist.size() > 0)
      {
        rForm.setDepartmentList(deptlist);
        Department department = (Department)deptlist.get(0);
        rForm.setDepartmentId(0L);
      }
      rForm.setOrgId(org.getOrgId());
    }
    List yearList = Constant.getYearsLovList();
    rForm.setYearList(yearList);
    return mapping.findForward("eeoreport");
  }
  
  public ActionForward interviewprocessefficiency(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside interviewprocessefficiency method");
    ReportForm rForm = (ReportForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    
    List orgList = BOFactory.getLovBO().getAllOrganization(user1.getSuper_user_key());
    rForm.setOrganizationList(orgList);
    List deptlist = new ArrayList();
    rForm.setDepartmentList(deptlist);
    if (orgList.size() > 0)
    {
      Organization org = (Organization)orgList.get(0);
      
      deptlist = BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(org.getOrgId()));
      if (deptlist.size() > 0)
      {
        rForm.setDepartmentList(deptlist);
        Department department = (Department)deptlist.get(0);
        rForm.setDepartmentId(0L);
      }
      rForm.setOrgId(org.getOrgId());
    }
    List yearList = Constant.getYearsLovList();
    rForm.setYearList(yearList);
    return mapping.findForward("interviewprocessefficiency");
  }
  
  public ActionForward requisitionOpeningOtatisticsReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside requisitionOpeningOtatisticsReport method");
    ReportForm rForm = (ReportForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    List orgList = BOFactory.getLovBO().getAllOrganization(user1.getSuper_user_key());
    rForm.setOrganizationList(orgList);
    List deptlist = new ArrayList();
    rForm.setDepartmentList(deptlist);
    if (orgList.size() > 0)
    {
      Organization org = (Organization)orgList.get(0);
      
      deptlist = BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(org.getOrgId()));
      if (deptlist.size() > 0)
      {
        rForm.setDepartmentList(deptlist);
        Department department = (Department)deptlist.get(0);
        rForm.setDepartmentId(0L);
      }
      rForm.setOrgId(org.getOrgId());
    }
    List yearList = Constant.getYearsLovList();
    rForm.setYearList(yearList);
    
    return mapping.findForward("requisitionOpeningOtatisticsReport");
  }
  
  public ActionForward declineOfferdRatio(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside declineOfferdRatio method");
    ReportForm rForm = (ReportForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    List orgList = BOFactory.getLovBO().getAllOrganization(user1.getSuper_user_key());
    rForm.setOrganizationList(orgList);
    List deptlist = new ArrayList();
    rForm.setDepartmentList(deptlist);
    if (orgList.size() > 0)
    {
      Organization org = (Organization)orgList.get(0);
      
      deptlist = BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(org.getOrgId()));
      if (deptlist.size() > 0)
      {
        rForm.setDepartmentList(deptlist);
        Department department = (Department)deptlist.get(0);
        rForm.setDepartmentId(0L);
      }
      rForm.setOrgId(org.getOrgId());
    }
    List yearList = Constant.getYearsLovList();
    rForm.setYearList(yearList);
    
    return mapping.findForward("declineOfferdRatio");
  }
  
  public ActionForward applicantOfferedRatio(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside applicantOfferedRatio method");
    ReportForm rForm = (ReportForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    
    List orgList = BOFactory.getLovBO().getAllOrganization(user1.getSuper_user_key());
    rForm.setOrganizationList(orgList);
    List deptlist = new ArrayList();
    rForm.setDepartmentList(deptlist);
    if (orgList.size() > 0)
    {
      Organization org = (Organization)orgList.get(0);
      
      deptlist = BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(org.getOrgId()));
      if (deptlist.size() > 0)
      {
        rForm.setDepartmentList(deptlist);
        Department department = (Department)deptlist.get(0);
        rForm.setDepartmentId(0L);
      }
      rForm.setOrgId(org.getOrgId());
    }
    List jobtitleList = new ArrayList();
    jobtitleList = BOFactory.getJobRequistionBO().getJobTitlesActiveAndOpenListAssigedToUser(user1);
    if (jobtitleList.size() > 0) {
      rForm.setJobtitleList(jobtitleList);
    } else {
      rForm.setJobtitleList(jobtitleList);
    }
    List yearList = Constant.getYearsLovList();
    rForm.setYearList(yearList);
    return mapping.findForward("applicantOfferedRatio");
  }
  
  public ActionForward budgettracking(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside budgettracking method");
    ReportForm rForm = (ReportForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    List budgetcodelist = new ArrayList();
    List orgList = BOFactory.getLovBO().getAllOrganization(user1.getSuper_user_key());
    rForm.setOrganizationList(orgList);
    List deptlist = new ArrayList();
    rForm.setDepartmentList(deptlist);
    if (orgList.size() > 0)
    {
      Organization org = (Organization)orgList.get(0);
      
      deptlist = BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(org.getOrgId()));
      if (deptlist.size() > 0)
      {
        rForm.setDepartmentList(deptlist);
        Department department = (Department)deptlist.get(0);
        rForm.setDepartmentId(0L);
      }
      rForm.setOrgId(org.getOrgId());
    }
    List yearList = Constant.getYearsLovList();
    rForm.setYearList(yearList);
    
    budgetcodelist = BOFactory.getLovBO().getAllBudgetCodeDetails(user1.getSuper_user_key());
    rForm.setBudgetCodeList(budgetcodelist);
    return mapping.findForward("budgettracking");
  }
  
  public ActionForward requisitionstatfirstpage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside requisitionstatfirstpage method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user1 = (User)request.getSession().getAttribute("user_data");
    ReportForm rForm = (ReportForm)form;
    rForm.setOrgId(user1.getOrganization().getOrgId());
    rForm.setOrgName(user1.getOrganization().getOrgName());
    logger.info("user1.getOrganization()" + user1.getOrganization().getOrgName());
    return mapping.findForward("requisitionstatfirstpage");
  }
  
  public ActionForward applicantOfferedJoinedStatByOrg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside applicantOfferedJoinedStatByOrg method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user1 = (User)request.getSession().getAttribute("user_data");
    ReportForm rForm = (ReportForm)form;
    rForm.setOrgId(user1.getOrganization().getOrgId());
    rForm.setOrgName(user1.getOrganization().getOrgName());
    logger.info("user1.getOrganization()" + user1.getOrganization().getOrgName());
    return mapping.findForward("applicantOfferedJoinedStatByOrg");
  }
  
  public ActionForward applicantOfferedJoinedStatByReq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside applicantOfferedJoinedStatByReq method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user1 = (User)request.getSession().getAttribute("user_data");
    ReportForm rForm = (ReportForm)form;
    
    return mapping.findForward("applicantOfferedJoinedStatByReq");
  }
  
  public ActionForward deptlistmultiple(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deptlistmultiple method >>>");
    String orgId = request.getParameter("orgId");
    System.out.println("orgId" + orgId);
    ReportForm rForm = (ReportForm)form;
    
    List<Long> orgIdsList = new ArrayList();
    if ((!StringUtils.isNullOrEmpty(orgId)) && (!orgId.equals("null")))
    {
      StringTokenizer token = new StringTokenizer(orgId, ",");
      while (token.hasMoreTokens()) {
        orgIdsList.add(new Long(token.nextToken()));
      }
    }
    List deptList = BOFactory.getLovBO().getDepartmentListByOrg(orgIdsList);
    request.setAttribute("deptList", deptList);
    
    return mapping.findForward("deptlistmultiple");
  }
}
