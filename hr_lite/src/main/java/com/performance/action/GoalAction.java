package com.performance.action;

import com.action.CommonAction;
import com.bean.Department;
import com.bean.Designations;
import com.bean.Organization;
import com.bean.User;
import com.bo.BOFactory;
import com.performance.bean.Goal;
import com.performance.bean.TimePeriod;
import com.performance.bo.GoalBO;
import com.performance.form.GoalForm;
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

public class GoalAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(GoalAction.class);
  
  public ActionForward goalsList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside goalsList method");
    
    return mapping.findForward("goalsList");
  }
  
  public ActionForward createOrgGoal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside createOrgGoal method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user1 = (User)request.getSession().getAttribute("user_data");
    String orgId = request.getParameter("orgId");
    GoalForm gForm = (GoalForm)form;
    if (!StringUtils.isNullOrEmpty(orgId)) {
      gForm.setOrgId(new Long(orgId).longValue());
    }
    BOFactory.getGoalBO().lovPopulateOrgGoalCreate(gForm, user1, request);
    

    return mapping.findForward("createOrgGoal");
  }
  
  public ActionForward defineOrgGoal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside defineOrgGoal method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user1 = (User)request.getSession().getAttribute("user_data");
    GoalForm gForm = (GoalForm)form;
    String orgId = request.getParameter("orgId");
    String departmentId = request.getParameter("departmentId");
    String designationId = request.getParameter("designationId");
    String timePeriodId = request.getParameter("timePeriodId");
    if (!StringUtils.isNullOrEmpty(orgId)) {
      gForm.setOrgId(new Long(orgId).longValue());
    }
    if (!StringUtils.isNullOrEmpty(departmentId)) {
      gForm.setDepartmentId(new Long(departmentId).longValue());
    }
    if (!StringUtils.isNullOrEmpty(designationId)) {
      gForm.setDesignationId(new Long(designationId).longValue());
    }
    if (!StringUtils.isNullOrEmpty(timePeriodId)) {
      gForm.setTimePeriodId(new Long(timePeriodId).longValue());
    }
    BOFactory.getGoalBO().lovPopulateOrgGoalCreate(gForm, user1, request);
    return mapping.findForward("defineOrgGoal");
  }
  
  public ActionForward saveOrgGoal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveOrgGoal method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user1 = (User)request.getSession().getAttribute("user_data");
    String orgId = request.getParameter("orgId");
    GoalForm gForm = (GoalForm)form;
    if (!StringUtils.isNullOrEmpty(orgId)) {
      gForm.setOrgId(new Long(orgId).longValue());
    }
    Goal goal = new Goal();
    if (gForm.getOrgId() != 0L)
    {
      Organization org = new Organization();
      org.setOrgId(gForm.getOrgId());
      goal.setOrganization(org);
    }
    else
    {
      goal.setOrganization(null);
    }
    if (gForm.getDepartmentId() != 0L)
    {
      Department depart = new Department();
      depart.setDepartmentId(gForm.getDepartmentId());
      goal.setDepartment(depart);
    }
    else
    {
      goal.setDepartment(null);
    }
    if (gForm.getDesignationId() != 0L)
    {
      Designations designation = new Designations();
      designation.setDesignationId(gForm.getDesignationId());
      goal.setDesignation(designation);
    }
    else
    {
      goal.setDesignation(null);
    }
    if (gForm.getTimePeriodId() != 0L)
    {
      TimePeriod timeperiod = new TimePeriod();
      timeperiod.setTimePeriodId(gForm.getTimePeriodId());
      goal.setTimeperiod(timeperiod);
    }
    goal.setGoalType("ORGANIZATION");
    goal.setGoalName(gForm.getGoalName());
    goal.setGoalDesc(gForm.getGoalDesc());
    
    logger.info("gForm.getModifiable()" + gForm.getModifiable());
    if ((!StringUtils.isNullOrEmpty(gForm.getModifiable())) && (gForm.getModifiable().equalsIgnoreCase("On"))) {
      goal.setModifiable("Y");
    } else {
      goal.setModifiable("N");
    }
    goal.setCreatedBy(user1.getUserName());
    goal.setCreatedDate(new Date());
    
    Goal goalnew = BOFactory.getGoalBO().saveGoal(goal);
    
    gForm.setGoalId(goalnew.getGoalId());
    gForm.setGoalDesc("");
    gForm.setGoalName("");
    gForm.setModifiable("");
    
    BOFactory.getGoalBO().lovPopulateOrgGoalCreate(gForm, user1, request);
    
    request.setAttribute("isReadOnly", "yes");
    return mapping.findForward("createOrgGoal");
  }
  
  public ActionForward goalorglist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside goalorglist method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    GoalForm gForm = (GoalForm)form;
    return mapping.findForward("goalorglist");
  }
  
  public ActionForward goalorglistajax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside goalorglistajax method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    GoalForm gForm = (GoalForm)form;
    String orgId = request.getParameter("orgId");
    if (!StringUtils.isNullOrEmpty(orgId)) {
      request.setAttribute("orgId", orgId.trim());
    }
    return mapping.findForward("goalorglistajax");
  }
  
  public ActionForward initiateGoals(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside initiateGoals method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user1 = (User)request.getSession().getAttribute("user_data");
    GoalForm gForm = (GoalForm)form;
    String orgId = request.getParameter("orgId");
    if (!StringUtils.isNullOrEmpty(orgId)) {
      gForm.setOrgId(new Long(orgId).longValue());
    }
    String departmentId = request.getParameter("departmentId");
    String designationId = request.getParameter("designationId");
    String timePeriodId = request.getParameter("timePeriodId");
    if (!StringUtils.isNullOrEmpty(orgId)) {
      gForm.setOrgId(new Long(orgId).longValue());
    }
    if (!StringUtils.isNullOrEmpty(departmentId)) {
      gForm.setDepartmentId(new Long(departmentId).longValue());
    }
    if (!StringUtils.isNullOrEmpty(designationId)) {
      gForm.setDesignationId(new Long(designationId).longValue());
    }
    if (!StringUtils.isNullOrEmpty(timePeriodId)) {
      gForm.setTimePeriodId(new Long(timePeriodId).longValue());
    }
    BOFactory.getGoalBO().lovPopulateGoalInitiate(gForm, user1, request);
    

    request.setAttribute("isReadOnly", "yes");
    return mapping.findForward("initiateGoals");
  }
  
  public ActionForward initiateGoalsSubmit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside initiateGoalsSubmit method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user1 = (User)request.getSession().getAttribute("user_data");
    GoalForm gForm = (GoalForm)form;
    String orgId = request.getParameter("orgId");
    if (!StringUtils.isNullOrEmpty(orgId)) {
      gForm.setOrgId(new Long(orgId).longValue());
    }
    String departmentId = request.getParameter("departmentId");
    String designationId = request.getParameter("designationId");
    String timePeriodId = request.getParameter("timePeriodId");
    if (!StringUtils.isNullOrEmpty(orgId)) {
      gForm.setOrgId(new Long(orgId).longValue());
    }
    if (!StringUtils.isNullOrEmpty(departmentId)) {
      gForm.setDepartmentId(new Long(departmentId).longValue());
    }
    if (!StringUtils.isNullOrEmpty(designationId)) {
      gForm.setDesignationId(new Long(designationId).longValue());
    }
    if (!StringUtils.isNullOrEmpty(timePeriodId)) {
      gForm.setTimePeriodId(new Long(timePeriodId).longValue());
    }
    String userids = request.getParameter("userids");
    List userList = new ArrayList();
    if (!StringUtils.isNullOrEmpty(userids))
    {
      userids = userids.substring(0, userids.length() - 1);
      
      userList = StringUtils.tokenizeString(userids, ",");
    }
    String empchoosetype = request.getParameter("empchoosetype");
    
    BOFactory.getGoalBO().initiateGoalsSubmit(gForm, user1, userList, empchoosetype, request);
    BOFactory.getGoalBO().lovPopulateGoalInitiate(gForm, user1, request);
    

    request.setAttribute("isReadOnly", "yes");
    request.setAttribute("initiateGoalsSubmit", "yes");
    if (!StringUtils.isNullOrEmpty(timePeriodId)) {
      request.getSession().setAttribute("TIMEPERIOD_ID", new Long(timePeriodId));
    }
    return mapping.findForward("viewinitiatedgoals");
  }
  
  public ActionForward viewinitiatedgoals(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside viewinitiatedgoals method");
    String userId = request.getParameter("userId");
    String empName = request.getParameter("empName");
    GoalForm gForm = (GoalForm)form;
    



    Long tp = (Long)request.getSession().getAttribute("TIMEPERIOD_ID");
    if (tp != null) {
      gForm.setTimePeriodId(tp.longValue());
    }
    if (!StringUtils.isNullOrEmpty(empName)) {
      request.setAttribute("empName", empName.trim());
    }
    if (!StringUtils.isNullOrEmpty(userId)) {
      request.setAttribute("userId", userId.trim());
    }
    gForm.setTimePeriodList(BOFactory.getGoalBO().getAllTimePeriod());
    return mapping.findForward("viewinitiatedgoals");
  }
  
  public ActionForward settimeperiod(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside settimeperiod method");
    String timePeriodId = request.getParameter("timePeriodId");
    GoalForm gForm = (GoalForm)form;
    if (!StringUtils.isNullOrEmpty(timePeriodId))
    {
      request.getSession().setAttribute("TIMEPERIOD_ID", new Long(timePeriodId));
      gForm.setTimePeriodId(new Long(timePeriodId).longValue());
    }
    String url = "/goal.do?method=viewinitiatedgoals&timeperiodset=yes";
    
    ActionForward forward = new ActionForward(url);
    forward.setRedirect(true);
    return forward;
  }
  
  public ActionForward viewinitiatedgoalsajax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside viewinitiatedgoalsajax method");
    String userId = request.getParameter("userId");
    if (!StringUtils.isNullOrEmpty(userId)) {
      request.setAttribute("userId", userId.trim());
    }
    String empName = request.getParameter("empName");
    if (!StringUtils.isNullOrEmpty(empName)) {
      request.setAttribute("empName", empName.trim());
    }
    return mapping.findForward("viewinitiatedgoalsajax");
  }
}
