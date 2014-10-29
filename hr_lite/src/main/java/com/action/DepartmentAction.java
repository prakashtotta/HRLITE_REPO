package com.action;

import com.bean.Department;
import com.bean.Organization;
import com.bean.User;
import com.bo.BOFactory;
import com.bo.LovBO;
import com.bo.OrganizationBO;
import com.common.Common;
import com.common.ValidationException;
import com.dao.LovOpsDAO;
import com.form.DepartmentForm;
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

public class DepartmentAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(DepartmentAction.class);
  
  public ActionForward createDepartment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    User user1 = (User)request.getSession().getAttribute("user_data");
    DepartmentForm departmentForm = (DepartmentForm)form;
    String orgId = request.getParameter("orgId");
    departmentForm.setOrgId(new Long(orgId).longValue());
    List orgList = BOFactory.getLovBO().getAllOrganization(user1.getSuper_user_key());
    departmentForm.setOrgnizationList(orgList);
    return mapping.findForward("createDepartment");
  }
  
  public ActionForward saveDepartment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveDepartment method");
    
    ActionForward forward = new ActionForward();
    User user = (User)request.getSession().getAttribute("user_data");
    DepartmentForm departmentForm = (DepartmentForm)form;
    Department department = new Department();
    toValue(department, departmentForm);
    department.setCreatedBy(user.getUserName());
    department.setCreatedDate(new Date());
    boolean isError = false;
    String deptCode = BOFactory.getOrganizationBO().isDepartmentCodeExist(departmentForm.getDepartmentCode(), departmentForm.getOrgId());
    if (deptCode != null)
    {
      isError = true;
      ActionErrors errors = new ActionErrors();
      
      errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError("error.deptcode.alreadyexist"));
      saveErrors(request, errors);
    }
    if (!isError)
    {
      LovOpsDAO.saveDepartment(department);
      request.setAttribute("saveddepartment", "yes");
    }
    List orgList = BOFactory.getLovBO().getAllOrganization(user.getSuper_user_key());
    departmentForm.setOrgnizationList(orgList);
    departmentForm.setDepartmentId(department.getDepartmentId());
    return mapping.findForward("createDepartment");
  }
  
  public ActionForward departmentlocator(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside selectlocation method");
    User user = (User)request.getSession().getAttribute("user_data");
    String orgId = request.getParameter("orgId");
    DepartmentForm deptForm = (DepartmentForm)form;
    deptForm.setOrgId(new Long(orgId).longValue());
    
    String readpreview = request.getParameter("readPreview");
    deptForm.setReadPreview(readpreview);
    List orgList = BOFactory.getLovBO().getAllOrganization(user.getSuper_user_key());
    deptForm.setOrgnizationList(orgList);
    
    return mapping.findForward("departmentlocator");
  }
  
  public ActionForward searchdepartment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchdepartment method");
    User user = (User)request.getSession().getAttribute("user_data");
    String start = request.getParameter("start");
    String range = request.getParameter("range");
    String results = request.getParameter("results");
    
    String orgid = request.getParameter("orgid");
    String deptcode = request.getParameter("deptcode");
    String readpreview = request.getParameter("readPreview");
    
    int start1 = StringUtils.stringToInt(start, 0);
    int range1 = StringUtils.stringToInt(range, 15);
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    DepartmentForm deptForm = (DepartmentForm)form;
    
    List orgList = BOFactory.getLovBO().getAllOrganization(user.getSuper_user_key());
    deptForm.setOrgnizationList(orgList);
    deptForm.setReadPreview(readpreview);
    deptForm.setDepartmentName(deptForm.getDepartmentName());
    deptForm.setDepartmentCode(deptForm.getDepartmentCode());
    deptForm.setOrgId(new Long(orgid).longValue());
    
    List departmentList = LovOpsDAO.getDeparmentByCritiriya(deptForm.getDepartmentName(), deptForm.getDepartmentCode(), deptForm.getOrgId(), start1, range1);
    int totaluser = 0;
    if (StringUtils.isNullOrEmpty(results)) {
      totaluser = LovOpsDAO.getCountOfDepartmentsByCritera(deptForm.getDepartmentName(), deptForm.getDepartmentCode(), deptForm.getOrgId());
    } else {
      totaluser = new Integer(results).intValue();
    }
    logger.info("results" + totaluser);
    deptForm.setStart(String.valueOf(start1));
    deptForm.setRange(String.valueOf(range1));
    deptForm.setResults(String.valueOf(totaluser));
    


    deptForm.setDepartmentList(departmentList);
    

    return mapping.findForward("departmentlocator");
  }
  
  public ActionForward editDepartmentDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside editDepartmentDetails method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user = (User)request.getSession().getAttribute("user_data");
    String deptId = request.getParameter("id");
    
    DepartmentForm departmentForm = (DepartmentForm)form;
    
    Department dept = LovOpsDAO.getDepartmentDetails(deptId);
    
    toForm(departmentForm, dept);
    String readpreview = request.getParameter("readPreview");
    departmentForm.setReadPreview(readpreview);
    List orgList = BOFactory.getLovBO().getAllOrganization(user.getSuper_user_key());
    departmentForm.setOrgnizationList(orgList);
    return mapping.findForward("createDepartment");
  }
  
  public ActionForward departmentinfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside departmentinfo method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user = (User)request.getSession().getAttribute("user_data");
    String deptId = request.getParameter("id");
    
    DepartmentForm departmentForm = (DepartmentForm)form;
    
    Department dept = LovOpsDAO.getDepartmentDetails(deptId);
    
    toForm(departmentForm, dept);
    
    List orgList = BOFactory.getLovBO().getAllOrganization(user.getSuper_user_key());
    departmentForm.setOrgnizationList(orgList);
    return mapping.findForward("departmentinfo");
  }
  
  public ActionForward updateDepartment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updateDepartment method");
    
    ActionForward forward = new ActionForward();
    User user = (User)request.getSession().getAttribute("user_data");
    DepartmentForm departmentForm = (DepartmentForm)form;
    
    String id = request.getParameter("departmentId");
    
    Department department = LovOpsDAO.getDepartmentDetails(id);
    toValue(department, departmentForm);
    
    department.setUpdatedBy(user.getUserName());
    department.setUpdatedDate(new Date());
    boolean isError = false;
    String deptCode = BOFactory.getOrganizationBO().isDepartmentCodeExist(departmentForm.getDepartmentCode(), departmentForm.getOrgId());
    if ((deptCode != null) && (!deptCode.equals(department.getDepartmentCode())))
    {
      isError = true;
      ActionErrors errors = new ActionErrors();
      
      errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError("error.deptcode.alreadyexist"));
      saveErrors(request, errors);
    }
    if (!isError)
    {
      department = LovOpsDAO.updateDepartment(department);
      request.setAttribute("saveddepartment", "yes");
    }
    List orgList = BOFactory.getLovBO().getAllOrganization(user.getSuper_user_key());
    departmentForm.setOrgnizationList(orgList);
    return mapping.findForward("createDepartment");
  }
  
  public ActionForward deleteDepartment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteDepartment method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user = (User)request.getSession().getAttribute("user_data");
    DepartmentForm departmentForm = (DepartmentForm)form;
    
    String id = request.getParameter("departmentId");
    try
    {
      BOFactory.getOrganizationBO().deleteDepartment(new Long(id).longValue());
      
      request.setAttribute("deleteddepartment", "yes");
      
      return mapping.findForward("createDepartment");
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
  
  public ActionForward departmentDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside departmentDetails method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    String deptId = request.getParameter("departmentId");
    
    DepartmentForm departmentForm = (DepartmentForm)form;
    
    Department departnent = LovOpsDAO.getDepartmentDetails(deptId);
    
    toForm(departmentForm, departnent);
    
    return mapping.findForward("createDepartment");
  }
  
  public void toValue(Department department, DepartmentForm departmentForm)
  {
    department.setDepartmentId(departmentForm.getDepartmentId());
    department.setDepartmentName(departmentForm.getDepartmentName());
    department.setDepartmentCode(departmentForm.getDepartmentCode());
    department.setDepartmentDesc(departmentForm.getDepartmentDesc());
    department.setNotes(departmentForm.getNotes());
    Organization org = new Organization();
    org.setOrgId(new Long(departmentForm.getOrgId()).longValue());
    department.setOrganization(org);
    department.setStatus("A");
  }
  
  public void toForm(DepartmentForm departmentForm, Department dept)
  {
    departmentForm.setDepartmentId(dept.getDepartmentId());
    departmentForm.setDepartmentName(dept.getDepartmentName());
    departmentForm.setDepartmentCode(dept.getDepartmentCode());
    departmentForm.setDepartmentDesc(dept.getDepartmentDesc());
    departmentForm.setCreatedBy(dept.getCreatedBy());
    departmentForm.setCreatedDate(dept.getCreatedDate());
    departmentForm.setUpdatedDate(dept.getUpdatedDate());
    departmentForm.setUpdatedBy(dept.getUpdatedBy());
    departmentForm.setNotes(dept.getNotes());
    departmentForm.setOrgId(dept.getOrganization().getOrgId());
    departmentForm.setStatus(dept.getStatus());
    departmentForm.setOrgName(dept.getOrganization().getOrgName());
  }
}
