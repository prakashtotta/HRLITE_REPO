package com.action;

import com.bean.Department;
import com.bean.Organization;
import com.bean.ProjectCodes;
import com.bean.RefferalEmployee;
import com.bean.Timezone;
import com.bean.User;
import com.bo.BOFactory;
import com.bo.LovBO;
import com.bo.OrganizationBO;
import com.bo.ProjectCodeBO;
import com.common.Common;
import com.common.ValidationException;
import com.dao.LovOpsDAO;
import com.form.ProjectCodesForm;
import com.resources.Constant;
import com.util.DateUtil;
import com.util.StringUtils;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ProjectCodeAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(ProjectCodeAction.class);
  
  public ActionForward projectcodelist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside projectcodelist method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ProjectCodesForm projectcodeForm = (ProjectCodesForm)form;
    

    List orgList = BOFactory.getLovBO().getAllOrganization(user1.getSuper_user_key());
    projectcodeForm.setOrganizationList(orgList);
    List deptlist = new ArrayList();
    projectcodeForm.setDepartmentList(deptlist);
    if (orgList.size() > 0)
    {
      Organization org = (Organization)orgList.get(0);
      
      deptlist = BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(org.getOrgId()));
      if (deptlist.size() > 0)
      {
        projectcodeForm.setDepartmentList(deptlist);
        Department department = (Department)deptlist.get(0);
        projectcodeForm.setDepartmentId(0L);
      }
      projectcodeForm.setOrgId(org.getOrgId());
    }
    return mapping.findForward("projectcodelist");
  }
  
  public ActionForward searchProjectCode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside searchProjectCode method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ProjectCodesForm projectcodeForm = (ProjectCodesForm)form;
    String orgId = request.getParameter("orgId");
    String departmentId = request.getParameter("departmentId");
    
    logger.info("orgId >> " + orgId);
    logger.info("departmentId >> " + departmentId);
    
    projectcodeForm.setOrgId(new Long(orgId).longValue());
    projectcodeForm.setDepartmentId(new Long(departmentId).longValue());
    
    List orgList = BOFactory.getLovBO().getAllOrganization(user1.getSuper_user_key());
    projectcodeForm.setOrganizationList(orgList);
    List deptlist = new ArrayList();
    if (orgList.size() > 0)
    {
      Organization org = (Organization)orgList.get(0);
      projectcodeForm.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(orgId)));
    }
    return mapping.findForward("projectcodelist");
  }
  
  public ActionForward createProjectCodes(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    ProjectCodesForm projectCodeForm = (ProjectCodesForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    List orgList = BOFactory.getLovBO().getAllOrganization(user1.getSuper_user_key());
    projectCodeForm.setOrganizationList(orgList);
    List deptlist = new ArrayList();
    projectCodeForm.setDepartmentList(deptlist);
    if (orgList.size() > 0)
    {
      Organization org = (Organization)orgList.get(0);
      projectCodeForm.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(org.getOrgId())));
    }
    return mapping.findForward("createProjectCodes");
  }
  
  public ActionForward saveProjectCodes(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveProjectCodes method");
    
    ActionForward forward = new ActionForward();
    User user = (User)request.getSession().getAttribute("user_data");
    ProjectCodesForm projectCodeForm = (ProjectCodesForm)form;
    ProjectCodes projectCodes = new ProjectCodes();
    
    List orgList = BOFactory.getLovBO().getAllOrganization(user.getSuper_user_key());
    projectCodeForm.setOrganizationList(orgList);
    List deptlist = new ArrayList();
    projectCodeForm.setDepartmentList(deptlist);
    boolean isError = false;
    String projectcode = BOFactory.getProjectCodeBO().isProjectCodeExist(projectCodeForm.getProjCode());
    if (projectcode != null)
    {
      isError = true;
      ActionErrors errors = new ActionErrors();
      
      errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError("error.projectcode.alreadyexist"));
      saveErrors(request, errors);
    }
    toValue(projectCodes, projectCodeForm);
    projectCodes.setCreatedBy(user.getUserName());
    if (!StringUtils.isNullOrEmpty(projectCodeForm.getEffectiveEndDate()))
    {
      String datepattern = DateUtil.getDatePatternFormat(user.getLocale());
      String converteddate = DateUtil.convertFromTimezoneToTimezoneDate(projectCodeForm.getEffectiveEndDate(), datepattern, user.getTimezone().getTimezoneCode(), TimeZone.getDefault().getID());
      
      logger.info("converteddate" + converteddate);
      
      Calendar cal = DateUtil.convertStringDateToCalendar(converteddate, datepattern);
      
      projectCodes.setEffectiveEndDate(cal.getTime());
    }
    if (!StringUtils.isNullOrEmpty(projectCodeForm.getEffectiveStartDate()))
    {
      String datepattern = DateUtil.getDatePatternFormat(user.getLocale());
      String converteddate = DateUtil.convertFromTimezoneToTimezoneDate(projectCodeForm.getEffectiveStartDate(), datepattern, user.getTimezone().getTimezoneCode(), TimeZone.getDefault().getID());
      
      logger.info("converteddate" + converteddate);
      
      Calendar cal = DateUtil.convertStringDateToCalendar(converteddate, datepattern);
      
      projectCodes.setEffectiveStartDate(cal.getTime());
    }
    projectCodes.setCreatedBy(user.getUserName());
    projectCodes.setCreatedDate(new Date());
    if (!isError)
    {
      LovOpsDAO.saveProjectCodes(projectCodes);
      request.setAttribute("savedprojectcode", "yes");
      String redpreview = request.getParameter("readPreview");
      projectCodeForm.setReadPreview(redpreview);
    }
    toForm(projectCodeForm, projectCodes);
    projectCodeForm.setOrganizationList(orgList);
    projectCodeForm.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(projectCodes.getOrganization().getOrgId())));
    



    return mapping.findForward("createProjectCodes");
  }
  
  public ActionForward projectCodeinfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside projectCodeinfo method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    RefferalEmployee user = (RefferalEmployee)request.getSession().getAttribute("employee_refferal_data");
    String projectcodeId = request.getParameter("projectcodeId");
    logger.info("projectCodeId : " + projectcodeId);
    
    ProjectCodesForm projectCodeForm = (ProjectCodesForm)form;
    ProjectCodes projectCodes = LovOpsDAO.getProjectCodesDetails(projectcodeId);
    logger.info("Inside projectCodeinfo method .. 2");
    toForm(projectCodeForm, projectCodes);
    logger.info("Inside projectCodeinfo method .. 3");
    if (projectCodes.getEffectiveEndDate() != null)
    {
      String datepattern = Constant.getValue("defaultdateformat");
      if (user != null) {
        datepattern = DateUtil.getDatePatternFormat(user.getLocale());
      }
      projectCodeForm.setEffectiveEndDate(DateUtil.convertDateToStringDate(projectCodes.getEffectiveEndDate(), datepattern));
    }
    logger.info("Inside projectCodeinfo method .. 4");
    if (projectCodes.getEffectiveStartDate() != null)
    {
      String datepattern = Constant.getValue("defaultdateformat");
      if (user != null) {
        datepattern = DateUtil.getDatePatternFormat(user.getLocale());
      }
      projectCodeForm.setEffectiveStartDate(DateUtil.convertDateToStringDate(projectCodes.getEffectiveStartDate(), datepattern));
    }
    logger.info("Inside projectCodeinfo method .. 5");
    List orgList = BOFactory.getLovBO().getAllOrganization(user.getSuper_user_key());
    projectCodeForm.setOrganizationList(orgList);
    projectCodeForm.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(projectCodes.getOrganization().getOrgId())));
    logger.info("Inside projectCodeinfo method .. 6");
    

    return mapping.findForward("projectCodeinfo");
  }
  
  public ActionForward editProjectCodes(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside editProjectCodes method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user = (User)request.getSession().getAttribute("user_data");
    String projectCodeId = request.getParameter("projectCodeId");
    
    ProjectCodesForm projectCodeForm = (ProjectCodesForm)form;
    ProjectCodes projectCodes = LovOpsDAO.getProjectCodesDetails(projectCodeId);
    toForm(projectCodeForm, projectCodes);
    if (projectCodes.getEffectiveEndDate() != null)
    {
      String datepattern = Constant.getValue("defaultdateformat");
      if (user != null) {
        datepattern = DateUtil.getDatePatternFormat(user.getLocale());
      }
      projectCodeForm.setEffectiveEndDate(DateUtil.convertDateToStringDate(projectCodes.getEffectiveEndDate(), datepattern));
    }
    if (projectCodes.getEffectiveStartDate() != null)
    {
      String datepattern = Constant.getValue("defaultdateformat");
      if (user != null) {
        datepattern = DateUtil.getDatePatternFormat(user.getLocale());
      }
      projectCodeForm.setEffectiveStartDate(DateUtil.convertDateToStringDate(projectCodes.getEffectiveStartDate(), datepattern));
    }
    List orgList = BOFactory.getLovBO().getAllOrganization(user.getSuper_user_key());
    projectCodeForm.setOrganizationList(orgList);
    projectCodeForm.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(projectCodes.getOrganization().getOrgId())));
    
    String redpreview = request.getParameter("readPreview");
    projectCodeForm.setReadPreview(redpreview);
    
    return mapping.findForward("createProjectCodes");
  }
  
  public ActionForward updateProjectCodes(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updateProjectCodes method");
    
    ActionForward forward = new ActionForward();
    User user = (User)request.getSession().getAttribute("user_data");
    ProjectCodesForm projectCodeForm = (ProjectCodesForm)form;
    
    String id = request.getParameter("id");
    ProjectCodes projectCodes = LovOpsDAO.getProjectCodesDetails(id);
    
    boolean isError = false;
    String projectcode = BOFactory.getProjectCodeBO().isProjectCodeExist(projectCodeForm.getProjCode());
    if ((projectcode != null) && (!projectcode.equals(projectCodes.getProjCode())))
    {
      isError = true;
      ActionErrors errors = new ActionErrors();
      
      errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError("error.projectcode.alreadyexist"));
      saveErrors(request, errors);
    }
    toValue(projectCodes, projectCodeForm);
    if (!StringUtils.isNullOrEmpty(projectCodeForm.getEffectiveEndDate()))
    {
      String datepattern = DateUtil.getDatePatternFormat(user.getLocale());
      String converteddate = DateUtil.convertFromTimezoneToTimezoneDate(projectCodeForm.getEffectiveEndDate(), datepattern, user.getTimezone().getTimezoneCode(), TimeZone.getDefault().getID());
      
      logger.info("converteddate" + converteddate);
      
      Calendar cal = DateUtil.convertStringDateToCalendar(converteddate, datepattern);
      
      projectCodes.setEffectiveEndDate(cal.getTime());
    }
    if (!StringUtils.isNullOrEmpty(projectCodeForm.getEffectiveStartDate()))
    {
      String datepattern = DateUtil.getDatePatternFormat(user.getLocale());
      String converteddate = DateUtil.convertFromTimezoneToTimezoneDate(projectCodeForm.getEffectiveStartDate(), datepattern, user.getTimezone().getTimezoneCode(), TimeZone.getDefault().getID());
      
      logger.info("converteddate" + converteddate);
      
      Calendar cal = DateUtil.convertStringDateToCalendar(converteddate, datepattern);
      
      projectCodes.setEffectiveStartDate(cal.getTime());
    }
    List orgList = BOFactory.getLovBO().getAllOrganization(user.getSuper_user_key());
    projectCodeForm.setOrganizationList(orgList);
    projectCodeForm.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(projectCodes.getOrganization().getOrgId())));
    
    projectCodes.setUpdatedBy(user.getUserName());
    projectCodes.setUpdatedDate(new Date());
    if (!isError)
    {
      projectCodes = LovOpsDAO.updateProjectCode(projectCodes);
      request.setAttribute("Updateprojectcode", "yes");
      String readpreview = request.getParameter("readPreview");
      projectCodeForm.setReadPreview(readpreview);
    }
    toForm(projectCodeForm, projectCodes);
    

    return mapping.findForward("createProjectCodes");
  }
  
  public ActionForward projectCodedelete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside projectCodedelete method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user = (User)request.getSession().getAttribute("user_data");
    ProjectCodesForm projectCodeForm = (ProjectCodesForm)form;
    
    String id = request.getParameter("projectCodeId");
    try
    {
      BOFactory.getOrganizationBO().deleteProjectCode(new Long(id).longValue());
      
      request.setAttribute("Deleteprojectcode", "yes");
      
      return mapping.findForward("createProjectCodes");
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
  
  public ActionForward projectcodelocator(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside projectcodelocator method");
    String deptId = request.getParameter("deptId");
    String orgId = request.getParameter("orgId");
    ProjectCodesForm projectForm = (ProjectCodesForm)form;
    projectForm.setDepartmentId(new Long(deptId).longValue());
    List departmentList = LovOpsDAO.getDepartmentsByCritera(null, orgId);
    projectForm.setDepartmentList(departmentList);
    projectForm.setOrgId(new Long(orgId).longValue());
    projectForm.setDepartmentId(new Long(deptId).longValue());
    



    return mapping.findForward("projectcodelocator");
  }
  
  public ActionForward loadDepartments(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String orgId = request.getParameter("orgId");
    System.out.println("orgId" + orgId);
    ProjectCodesForm projectForm = (ProjectCodesForm)form;
    projectForm.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(orgId));
    
    return mapping.findForward("deptlist");
  }
  
  public void toValue(ProjectCodes projectCodes, ProjectCodesForm projectCodeForm)
  {
    projectCodes.setProjCode(projectCodeForm.getProjCode());
    projectCodes.setProjName(projectCodeForm.getProjName());
    projectCodes.setProjDesc(projectCodeForm.getProjDesc());
    
    projectCodes.setStatus("A");
    

    projectCodes.setNotes(projectCodeForm.getNotes());
    

    Organization org = new Organization();
    org.setOrgId(projectCodeForm.getOrgId());
    Department dept = new Department();
    dept.setDepartmentId(projectCodeForm.getDepartmentId());
    projectCodes.setOrganization(org);
    projectCodes.setDepartment(dept);
  }
  
  public void toForm(ProjectCodesForm projectCodeForm, ProjectCodes projectCodes)
  {
    projectCodeForm.setProjectId(projectCodes.getProjectId());
    projectCodeForm.setProjCode(projectCodes.getProjCode());
    projectCodeForm.setProjName(projectCodes.getProjName());
    projectCodeForm.setProjDesc(projectCodes.getProjDesc());
    
    projectCodeForm.setNotes(projectCodes.getNotes());
    projectCodeForm.setCreatedBy(projectCodes.getCreatedBy());
    projectCodeForm.setCreatedDate(projectCodes.getCreatedDate());
    projectCodeForm.setUpdatedDate(projectCodes.getUpdatedDate());
    projectCodeForm.setUpdatedBy(projectCodes.getUpdatedBy());
    


    projectCodeForm.setOrgId(projectCodes.getOrganization().getOrgId());
    projectCodeForm.setDepartmentId(projectCodes.getDepartment().getDepartmentId());
    projectCodeForm.setOrgName(projectCodes.getOrganization().getOrgName());
    projectCodeForm.setDepartmentName(projectCodes.getDepartment().getDepartmentName());
  }
  
  public void toDelete(ProjectCodes projectCodes, ProjectCodesForm projectCodeForm)
  {
    projectCodes.setStatus("D");
  }
}
