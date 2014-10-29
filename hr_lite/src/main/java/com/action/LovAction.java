package com.action;

import com.bean.Organization;
import com.bean.Role;
import com.bean.User;
import com.bo.BOFactory;
import com.bo.LovBO;
import com.bo.OrganizationBO;
import com.bo.PermissionBO;
import com.dao.LovOpsDAO;
import com.form.LovForm;
import com.resources.Constant;
import com.util.StringUtils;
import java.io.PrintStream;
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

public class LovAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(LovAction.class);
  
  public ActionForward updateRoleTable(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updateRoleTable method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    LovForm lovForm = (LovForm)form;
    
    String id = request.getParameter("id");
    String newValue = request.getParameter("newValue");
    String column = request.getParameter("column");
    
    Role role = LovOpsDAO.getRole(id);
    if ((column != null) && (column.equalsIgnoreCase("rolename"))) {
      role.setRoleName(newValue);
    }
    if ((column != null) && (column.equalsIgnoreCase("roledes"))) {
      role.setRoleDesc(newValue);
    }
    role = LovOpsDAO.saveUpdateRole(role);
    
    request.setAttribute("newValue", newValue);
    
    return mapping.findForward("updateRoleTable");
  }
  
  public ActionForward lovlist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside lovlist method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    return mapping.findForward("lovlist");
  }
  
  public ActionForward callist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside callist method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String reviewerid = request.getParameter("reviewerid");
    String reviewername = request.getParameter("reviewername");
    String depttempid = request.getParameter("depttempid");
    LovForm lovForm = (LovForm)form;
    User user = (User)request.getSession().getAttribute("user_data");
    if (!StringUtils.isNullOrEmpty(reviewerid))
    {
      logger.info("reviewerid" + reviewerid.trim());
      logger.info("reviewername" + reviewername.trim());
      request.setAttribute("reviewerid", reviewerid.trim());
      request.setAttribute("reviewername", reviewername.trim());
    }
    else
    {
      request.setAttribute("reviewerid", String.valueOf(user.getUserId()));
      request.setAttribute("reviewername", user.getFirstName() + " " + user.getLastName());
    }
    BOFactory.getLovBO().callListFormSet(lovForm, user);
    if (!StringUtils.isNullOrEmpty(depttempid))
    {
      lovForm.setDeptId(new Long(depttempid).longValue());
      request.setAttribute("depttempid", depttempid.trim());
    }
    logger.info("depttempid" + depttempid);
    return mapping.findForward("callist");
  }
  
  public ActionForward callistajax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside callistajax method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String reviewerid = request.getParameter("reviewerid");
    String reviewername = request.getParameter("reviewername");
    String depttempid = request.getParameter("depttempid");
    LovForm lovForm = (LovForm)form;
    User user = (User)request.getSession().getAttribute("user_data");
    if (!StringUtils.isNullOrEmpty(reviewerid))
    {
      logger.info("reviewerid" + reviewerid.trim());
      logger.info("reviewername" + reviewername.trim());
      request.setAttribute("reviewerid", reviewerid.trim());
      request.setAttribute("reviewername", reviewername.trim());
    }
    else
    {
      request.setAttribute("reviewerid", String.valueOf(user.getUserId()));
      request.setAttribute("reviewername", user.getFirstName() + " " + user.getLastName());
    }
    BOFactory.getLovBO().callListFormSet(lovForm, user);
    if (!StringUtils.isNullOrEmpty(depttempid))
    {
      lovForm.setDeptId(new Long(depttempid).longValue());
      request.setAttribute("depttempid", depttempid.trim());
    }
    logger.info("depttempid" + depttempid);
    return mapping.findForward("callistajax");
  }
  
  public ActionForward callistsubmit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside callistsubmit method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String reviewerid = request.getParameter("reviewerid");
    String reviewername = request.getParameter("reviewername");
    String depttempid = request.getParameter("depttempid");
    LovForm lovForm = (LovForm)form;
    User user = (User)request.getSession().getAttribute("user_data");
    if (!StringUtils.isNullOrEmpty(reviewerid))
    {
      logger.info("reviewerid" + reviewerid.trim());
      logger.info("reviewername" + reviewername.trim());
      request.setAttribute("reviewerid", reviewerid.trim());
      request.setAttribute("reviewername", reviewername.trim());
    }
    else
    {
      request.setAttribute("reviewerid", String.valueOf(0));
      request.setAttribute("reviewername", "");
    }
    BOFactory.getLovBO().callListFormSet(lovForm, user);
    if (!StringUtils.isNullOrEmpty(depttempid))
    {
      lovForm.setDeptId(new Long(depttempid).longValue());
      request.setAttribute("depttempid", depttempid.trim());
    }
    logger.info("depttempid" + depttempid);
    return mapping.findForward("callist");
  }
  
  public ActionForward loadDepartmentsWithOutProjectCodeAndSpace(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String orgId = request.getParameter("orgId");
    System.out.println("orgId" + orgId);
    LovForm lovForm = (LovForm)form;
    lovForm.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(orgId));
    
    return mapping.findForward("loadDepartmentsWithOutProjectCodeAndSpace");
  }
  
  public ActionForward departmentlocator(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside selectlocation method");
    String orgId = request.getParameter("orgId");
    LovForm lovForm = (LovForm)form;
    lovForm.setOrgId(new Long(orgId).longValue());
    
    return mapping.findForward("departmentlocator");
  }
  
  public ActionForward searchdepartment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchdepartment method");
    String criteria = request.getParameter("criteria");
    String orgId = request.getParameter("orgId");
    List departmentList = LovOpsDAO.getDepartmentsByCritera(criteria, orgId);
    LovForm lovForm = (LovForm)form;
    lovForm.setDepartmentList(departmentList);
    return mapping.findForward("departmentlocator");
  }
  
  public ActionForward projectcodelocator(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside projectcodelocator method");
    String deptId = request.getParameter("deptId");
    String orgId = request.getParameter("orgId");
    LovForm lovForm = (LovForm)form;
    lovForm.setDepartmentId(new Long(deptId).longValue());
    List departmentList = LovOpsDAO.getDepartmentsByCritera(null, orgId);
    
    lovForm.setDepartmentList(departmentList);
    lovForm.setOrgId(new Long(orgId).longValue());
    lovForm.setDepartmentId(new Long(deptId).longValue());
    



    return mapping.findForward("projectcodelocator");
  }
  
  public ActionForward searchprojectcodes(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchprojectcodes method");
    String start = request.getParameter("start");
    String range = request.getParameter("range");
    String results = request.getParameter("results");
    int start1 = StringUtils.stringToInt(start, 0);
    int range1 = StringUtils.stringToInt(range, 15);
    
    String orgId = request.getParameter("orgid");
    String deptId = request.getParameter("departmentid");
    LovForm lovForm = (LovForm)form;
    List departmentList = LovOpsDAO.getDepartmentsByCritera(null, orgId);
    


    List projectcodeList = LovOpsDAO.getProjectCodesByCriteria(lovForm.getProjCode(), lovForm.getProjName(), new Long(deptId).longValue(), start1, range1);
    int totaluser = 0;
    if (StringUtils.isNullOrEmpty(results)) {
      totaluser = LovOpsDAO.getCountOfProjectCodesByCriteria(lovForm.getProjCode(), lovForm.getProjName(), new Long(deptId).longValue());
    } else {
      totaluser = new Integer(results).intValue();
    }
    lovForm.setStart(String.valueOf(start1));
    lovForm.setRange(String.valueOf(range1));
    lovForm.setResults(String.valueOf(totaluser));
    

    lovForm.setCriteria(lovForm.getCriteria());
    lovForm.setDepartmentList(departmentList);
    lovForm.setOrgId(new Long(orgId).longValue());
    lovForm.setDepartmentId(new Long(deptId).longValue());
    lovForm.setProjectcodeList(projectcodeList);
    
    return mapping.findForward("projectcodelocator");
  }
  
  public ActionForward jobgradelocator(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside jobgradelocator method");
    
    LovForm lovForm = (LovForm)form;
    
    return mapping.findForward("jobgradelocator");
  }
  
  public ActionForward reqtmplselector(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside reqtmplselector method");
    
    LovForm lovForm = (LovForm)form;
    User user = (User)request.getSession().getAttribute("user_data");
    BOFactory.getLovBO().reqtmplselector(lovForm, user);
    return mapping.findForward("reqtmplselector");
  }
  
  public ActionForward questiongroupselector(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside questiongroupselector method");
    
    LovForm lovForm = (LovForm)form;
    
    return mapping.findForward("questiongroupselector");
  }
  
  public ActionForward searchquestiongroups(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchquestiongroups method");
    
    String start = request.getParameter("start");
    String range = request.getParameter("range");
    String results = request.getParameter("results");
    int start1 = StringUtils.stringToInt(start, 0);
    int range1 = StringUtils.stringToInt(range, 15);
    
    LovForm lovForm = (LovForm)form;
    
    int totaluser = 0;
    if (StringUtils.isNullOrEmpty(results)) {
      totaluser = LovOpsDAO.getQuestiongroupsByCriteraCount(lovForm.getCriteria());
    } else {
      totaluser = new Integer(results).intValue();
    }
    logger.info("results" + totaluser);
    List questiongroupList = LovOpsDAO.getQuestionsGroupByCritera(lovForm.getCriteria(), start1, range1);
    lovForm.setQuestiongroupList(questiongroupList);
    lovForm.setStart(String.valueOf(start1));
    lovForm.setRange(String.valueOf(range1));
    lovForm.setResults(String.valueOf(totaluser));
    lovForm.setCriteria(lovForm.getCriteria());
    return mapping.findForward("questiongroupselector");
  }
  
  public ActionForward requisitionselector(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside requisitionselector method");
    String dummyvar = request.getParameter("dummyvar");
    String frompage = request.getParameter("frompage");
    
    LovForm lovForm = (LovForm)form;
    lovForm.setFrompage(frompage);
    if (!StringUtils.isNullOrEmpty(dummyvar)) {
      lovForm.setDummyvar(new Integer(dummyvar).intValue());
    }
    User user = (User)request.getSession().getAttribute("user_data");
    BOFactory.getLovBO().requisitionselector(lovForm, user);
    lovForm.setStatus("Open");
    return mapping.findForward("requisitionselector");
  }
  
  public ActionForward requisitionselectormuliple(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside requisitionselectormuliple method");
    
    LovForm lovForm = (LovForm)form;
    User user = (User)request.getSession().getAttribute("user_data");
    BOFactory.getLovBO().requisitionselector(lovForm, user);
    lovForm.setStatus("Open");
    return mapping.findForward("requisitionselectormuliple");
  }
  
  public ActionForward searchrequistions(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchrequistions method");
    String dummyvar = request.getParameter("dummyvar");
    User user1 = (User)request.getSession().getAttribute("user_data");
    LovForm lovForm = (LovForm)form;
    if (!StringUtils.isNullOrEmpty(dummyvar)) {
      lovForm.setDummyvar(new Integer(dummyvar).intValue());
    }
    String start = request.getParameter("start");
    String range = request.getParameter("range");
    String results = request.getParameter("results");
    String status = request.getParameter("status");
    int start1 = StringUtils.stringToInt(start, 0);
    int range1 = StringUtils.stringToInt(range, 15);
    

    lovForm.setStatus(status);
    int totaluser = 0;
    if (PermissionBO.isRequistionReadAllowed(user1))
    {
      if (StringUtils.isNullOrEmpty(results)) {
        totaluser = LovOpsDAO.getRequistionsByCriteraCount1(user1.getSuper_user_key(), lovForm.getJobcode(), lovForm.getJobtitle(), lovForm.getOrgId(), lovForm.getDepartmentId(), lovForm.getLocationId(), lovForm.getStatus());
      } else {
        totaluser = new Integer(results).intValue();
      }
      List reqList = LovOpsDAO.getRequisitionsByCritera1(user1.getSuper_user_key(), lovForm.getJobcode(), lovForm.getJobtitle(), lovForm.getOrgId(), lovForm.getDepartmentId(), lovForm.getLocationId(), lovForm.getStatus(), start1, range1);
      lovForm.setRequisitionList(reqList);
    }
    BOFactory.getLovBO().requisitionselector(lovForm, user1);
    lovForm.setStatus(lovForm.getStatus());
    lovForm.setStart(String.valueOf(start1));
    lovForm.setRange(String.valueOf(range1));
    lovForm.setResults(String.valueOf(totaluser));
    lovForm.setCriteria(lovForm.getCriteria());
    if (!StringUtils.isNullOrEmpty(dummyvar)) {
      lovForm.setStatus("Open");
    }
    return mapping.findForward("requisitionselector");
  }
  
  public ActionForward searchrequistionsmultiple(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchrequistionsmultiple method");
    User user = (User)request.getSession().getAttribute("user_data");
    String start = request.getParameter("start");
    String range = request.getParameter("range");
    String results = request.getParameter("results");
    String status = request.getParameter("status");
    int start1 = StringUtils.stringToInt(start, 0);
    int range1 = StringUtils.stringToInt(range, 15);
    
    LovForm lovForm = (LovForm)form;
    lovForm.setStatus(status);
    int totaluser = 0;
    if (PermissionBO.isRequistionReadAllowed(user))
    {
      if (StringUtils.isNullOrEmpty(results)) {
        totaluser = LovOpsDAO.getRequistionsByCriteraCount1(user.getSuper_user_key(), lovForm.getJobcode(), lovForm.getJobtitle(), lovForm.getOrgId(), lovForm.getDepartmentId(), lovForm.getLocationId(), lovForm.getStatus());
      } else {
        totaluser = new Integer(results).intValue();
      }
      List reqList = LovOpsDAO.getRequisitionsByCritera1(user.getSuper_user_key(), lovForm.getJobcode(), lovForm.getJobtitle(), lovForm.getOrgId(), lovForm.getDepartmentId(), lovForm.getLocationId(), lovForm.getStatus(), start1, range1);
      lovForm.setRequisitionList(reqList);
    }
    BOFactory.getLovBO().requisitionselector(lovForm, user);
    lovForm.setStatus(lovForm.getStatus());
    lovForm.setStart(String.valueOf(start1));
    lovForm.setRange(String.valueOf(range1));
    lovForm.setResults(String.valueOf(totaluser));
    lovForm.setCriteria(lovForm.getCriteria());
    return mapping.findForward("requisitionselectormuliple");
  }
  
  public ActionForward setreqidsinsession(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside setreqidsinsession method");
    String reqid = request.getParameter("reqid");
    String reqname = request.getParameter("reqname");
    List reqids = new ArrayList();
    List reqnames = new ArrayList();
    if (request.getSession().getAttribute("checkedreqids") != null)
    {
      reqids = (List)request.getSession().getAttribute("checkedreqids");
      reqnames = (List)request.getSession().getAttribute("checkedreqnames");
      if (reqids.contains(reqid))
      {
        reqids.remove(reqid);
        reqnames.remove(reqname);
      }
      else
      {
        reqids.add(reqid);
        reqnames.add(reqname);
      }
      request.getSession().setAttribute("checkedreqids", reqids);
      request.getSession().setAttribute("checkedreqnames", reqnames);
    }
    else
    {
      reqids.add(reqid);
      reqnames.add(reqname);
      request.getSession().setAttribute("checkedreqids", reqids);
      request.getSession().setAttribute("checkedreqnames", reqnames);
    }
    return null;
  }
  
  public ActionForward removereqidsinsession(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside removereqidsinsession method");
    if (request.getSession().getAttribute("checkedreqids") != null) {
      request.getSession().removeAttribute("checkedreqids");
    }
    if (request.getSession().getAttribute("checkedreqnames") != null) {
      request.getSession().removeAttribute("checkedreqnames");
    }
    return null;
  }
  
  public ActionForward searchreqtemplates(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchreqtemplates method");
    User user = (User)request.getSession().getAttribute("user_data");
    String start = request.getParameter("start");
    String range = request.getParameter("range");
    String results = request.getParameter("results");
    int start1 = StringUtils.stringToInt(start, 0);
    int range1 = StringUtils.stringToInt(range, 15);
    
    LovForm lovForm = (LovForm)form;
    

    BOFactory.getLovBO().searchreqtemplates(lovForm, user, results, start1, range1);
    

    return mapping.findForward("reqtmplselector");
  }
  
  public ActionForward searchjobgrade(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchjobgrade method");
    
    String start = request.getParameter("start");
    String range = request.getParameter("range");
    String results = request.getParameter("results");
    int start1 = StringUtils.stringToInt(start, 0);
    int range1 = StringUtils.stringToInt(range, 15);
    User user = (User)request.getSession().getAttribute("user_data");
    
    LovForm lovForm = (LovForm)form;
    
    int totaluser = 0;
    if (StringUtils.isNullOrEmpty(results)) {
      totaluser = LovOpsDAO.getJobGradesByCriteraCount(user.getSuper_user_key(), lovForm.getCriteria());
    } else {
      totaluser = new Integer(results).intValue();
    }
    logger.info("results" + totaluser);
    List jobgradeList = LovOpsDAO.getJobGradesByCritera(user.getSuper_user_key(), lovForm.getCriteria(), start1, range1);
    lovForm.setJobgradeList(jobgradeList);
    lovForm.setStart(String.valueOf(start1));
    lovForm.setRange(String.valueOf(range1));
    lovForm.setResults(String.valueOf(totaluser));
    lovForm.setCriteria(lovForm.getCriteria());
    return mapping.findForward("jobgradelocator");
  }
  
  public ActionForward jobcodeselector(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside jobcodeselector method");
    
    LovForm lovForm = (LovForm)form;
    
    return mapping.findForward("jobcodeselector");
  }
  
  public ActionForward searchjobcode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchjobcode method");
    
    String start = request.getParameter("start");
    String range = request.getParameter("range");
    String results = request.getParameter("results");
    int start1 = StringUtils.stringToInt(start, 0);
    int range1 = StringUtils.stringToInt(range, 15);
    
    LovForm lovForm = (LovForm)form;
    
    int totaluser = 0;
    if (StringUtils.isNullOrEmpty(results)) {
      totaluser = LovOpsDAO.getJobCodesByCriteraCount(lovForm.getCriteria());
    } else {
      totaluser = new Integer(results).intValue();
    }
    logger.info("results" + totaluser);
    List jobcodeList = LovOpsDAO.getJobCodesByCritera(lovForm.getCriteria(), start1, range1);
    lovForm.setJobcodeList(jobcodeList);
    lovForm.setStart(String.valueOf(start1));
    lovForm.setRange(String.valueOf(range1));
    lovForm.setResults(String.valueOf(totaluser));
    lovForm.setCriteria(lovForm.getCriteria());
    return mapping.findForward("jobcodeselector");
  }
  
  public ActionForward salaryplanselector(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside salaryplanselector method");
    String orgid = request.getParameter("orgid");
    LovForm lovForm = (LovForm)form;
    lovForm.setOrgId(new Long(orgid).longValue());
    return mapping.findForward("salaryplanselector");
  }
  
  public ActionForward searchsalaryplan(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchsalaryplan method");
    
    String start = request.getParameter("start");
    String range = request.getParameter("range");
    String results = request.getParameter("results");
    String orgid = request.getParameter("orgid");
    LovForm lovForm = (LovForm)form;
    int start1 = StringUtils.stringToInt(start, 0);
    int range1 = StringUtils.stringToInt(range, 15);
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    Organization org = BOFactory.getOrganizationBO().getOrganization(orgid);
    List salaryplanList = LovOpsDAO.getSalaryPlansByCritera(lovForm.getCriteria(), lovForm.getSalaryplanyear(), org.getCurrencyCode(), start1, range1);
    int totaluser = 0;
    if (StringUtils.isNullOrEmpty(results)) {
      totaluser = LovOpsDAO.getCountOfSalaryPlansByCritera(lovForm.getCriteria(), lovForm.getSalaryplanyear(), org.getCurrencyCode());
    } else {
      totaluser = new Integer(results).intValue();
    }
    logger.info("results" + totaluser);
    lovForm.setStart(String.valueOf(start1));
    lovForm.setRange(String.valueOf(range1));
    lovForm.setResults(String.valueOf(totaluser));
    lovForm.setCriteria(lovForm.getCriteria());
    lovForm.setSalaryplanyear(lovForm.getSalaryplanyear());
    lovForm.setSalaryplanList(salaryplanList);
    
    return mapping.findForward("salaryplanselector");
  }
  
  public ActionForward budgetcodeselector(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside budgetcodeselector method");
    String orgId = request.getParameter("orgId");
    String deptId = request.getParameter("deptId");
    User user1 = (User)request.getSession().getAttribute("user_data");
    LovForm lovForm = (LovForm)form;
    List yearList = Constant.getYearsLovList();
    lovForm.setYearsList(yearList);
    List orgList = BOFactory.getLovBO().getAllOrganization(user1.getSuper_user_key());
    lovForm.setOrgnizationList(orgList);
    lovForm.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(orgId)));
    lovForm.setOrgId(new Long(orgId).longValue());
    if (!StringUtils.isNullOrEmpty(deptId)) {
      lovForm.setDepartmentId(new Long(deptId).longValue());
    }
    lovForm.setBudgetCodeList(null);
    Calendar can = Calendar.getInstance();
    int currentYear = can.get(1);
    lovForm.setBudgetYear(currentYear);
    return mapping.findForward("budgetcodeselector");
  }
  
  public ActionForward searchbudgetcode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchbudgetcode method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    String start = request.getParameter("start");
    String range = request.getParameter("range");
    String results = request.getParameter("results");
    String orgId = request.getParameter("orgId");
    LovForm lovForm = (LovForm)form;
    int start1 = StringUtils.stringToInt(start, 0);
    int range1 = StringUtils.stringToInt(range, 15);
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    List budgetcodeList = LovOpsDAO.getBudgetCodesByCritera(lovForm.getCriteria(), lovForm.getBudgetYear(), lovForm.getOrgId(), lovForm.getDepartmentId(), start1, range1);
    int totaluser = 0;
    if (StringUtils.isNullOrEmpty(results)) {
      totaluser = LovOpsDAO.getCountOfBudgetCodesByCritera(lovForm.getCriteria(), lovForm.getBudgetYear(), lovForm.getOrgId(), lovForm.getDepartmentId());
    } else {
      totaluser = new Integer(results).intValue();
    }
    logger.info("results" + totaluser);
    lovForm.setStart(String.valueOf(start1));
    lovForm.setRange(String.valueOf(range1));
    lovForm.setResults(String.valueOf(totaluser));
    lovForm.setCriteria(lovForm.getCriteria());
    lovForm.setBudgetYear(lovForm.getBudgetYear());
    lovForm.setBudgetCodeList(budgetcodeList);
    List yearList = Constant.getYearsLovList();
    lovForm.setYearsList(yearList);
    List orgList = BOFactory.getLovBO().getAllOrganization(user1.getSuper_user_key());
    lovForm.setOrgnizationList(orgList);
    lovForm.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(orgId)));
    lovForm.setOrgId(new Long(orgId).longValue());
    

    return mapping.findForward("budgetcodeselector");
  }
  
  public ActionForward loadDeptlistWithProjectcode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String orgId = request.getParameter("orgId");
    
    System.out.println("orgId" + orgId);
    LovForm tmplForm = (LovForm)form;
    


    tmplForm.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(orgId));
    

    return mapping.findForward("deptlistwithprojectcode");
  }
  
  public ActionForward loadProjectCode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String departmentId = request.getParameter("departmentId");
    System.out.println("departmentId" + departmentId);
    LovForm tmplForm = (LovForm)form;
    tmplForm.setProjectcodeList(BOFactory.getLovBO().getProjectCodesByDept(departmentId));
    

    return mapping.findForward("projectcodelist");
  }
  
  public ActionForward loadBudgetCode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String orgId = request.getParameter("orgId");
    String departmentId = request.getParameter("departmentId");
    System.out.println("orgId" + orgId);
    System.out.println("departmentId" + departmentId);
    LovForm lvForm = (LovForm)form;
    
    lvForm.setBudgetCodeList(BOFactory.getLovBO().getAllBudgetCodeDetailsbyorganizationanddepartment(orgId, departmentId));
    
    return mapping.findForward("budgetcodelist");
  }
}
