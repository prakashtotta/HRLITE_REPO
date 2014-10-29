package com.action;

import com.bean.TaskData;
import com.bean.User;
import com.bo.BOFactory;
import com.bo.TaskBO;
import com.common.Common;
import com.form.TaskForm;
import com.resources.Constant;
import com.util.DateUtil;
import com.util.StringUtils;
import java.util.ArrayList;
import java.util.Calendar;
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

public class TaskAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(TaskAction.class);
  
  public ActionForward pendingtasks(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside pendingtasks method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    TaskForm taskform = (TaskForm)form;
    List tasktypeList = Constant.getTaskTypesList(user1);
    
    List userList = new ArrayList();
    
    taskform.setTasktypeList(tasktypeList);
    taskform.setAssignedbyUsersList(userList);
    taskform.setAssignedtoUsersList(userList);
    
    request.setAttribute("searchpagedisplay", "no");
    return mapping.findForward("pendingtasks");
  }
  
  public ActionForward searchpendingtasks(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchpendingtasks method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    TaskForm taskform = (TaskForm)form;
    String cri = request.getParameter("cri");
    String assigneddate = request.getParameter("assigneddate");
    
    taskform.setAssignedcri(cri);
    taskform.setSearchassigneddate(assigneddate);
    List tasktypeList = Constant.getTaskTypesList(user1);
    List userList = new ArrayList();
    taskform.setTasktypeList(tasktypeList);
    taskform.setAssignedbyUsersList(userList);
    taskform.setAssignedtoUsersList(userList);
    request.setAttribute("searchpagedisplay", "yes");
    return mapping.findForward("pendingtasks");
  }
  
  public ActionForward mypendingtasks(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside mypendingtasks method");
    
    String url = "/dashboard.do?method=dashboardlist";
    ActionForward forward = new ActionForward(url);
    forward.setRedirect(true);
    return forward;
  }
  
  public ActionForward mypendingtasksajax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside mypendingtasksajax method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user1 = (User)request.getSession().getAttribute("user_data");
    TaskForm taskform = (TaskForm)form;
    List tasktypeList = Constant.getTaskTypesList(user1);
    taskform.setTasktypeList(tasktypeList);
    request.setAttribute("searchpagedisplaymytask", "no");
    return mapping.findForward("mypendingtasksajax");
  }
  
  public ActionForward searchmypendingtasks(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchmypendingtasks method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    TaskForm taskform = (TaskForm)form;
    String cri = request.getParameter("cri");
    String assigneddate = request.getParameter("assigneddate");
    
    taskform.setAssignedcri(cri);
    taskform.setSearchassigneddate(assigneddate);
    List tasktypeList = Constant.getTaskTypesList(user1);
    taskform.setTasktypeList(tasktypeList);
    request.setAttribute("searchpagedisplaymytask", "yes");
    return mapping.findForward("mypendingtasks");
  }
  
  public ActionForward searchmypendingtasksajax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchmypendingtasks method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    TaskForm taskform = (TaskForm)form;
    String tasktype = request.getParameter("tasktype");
    String assignedbyUserId = request.getParameter("assignedbyUserId");
    
    taskform.setTasktype(tasktype);
    if (!StringUtils.isNullOrEmpty(assignedbyUserId)) {
      taskform.setAssignedbyUserId(new Long(assignedbyUserId).longValue());
    }
    List tasktypeList = Constant.getTaskTypesList(user1);
    taskform.setTasktypeList(tasktypeList);
    request.setAttribute("searchpagedisplaymytask", "yes");
    return mapping.findForward("searchmypendingtasksajax");
  }
  
  public ActionForward addtasksrc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside addtasksrc method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    TaskForm taskform = (TaskForm)form;
    
    request.setAttribute("addtask", "no");
    return mapping.findForward("addtask");
  }
  
  public ActionForward addtask(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside addtasksrc method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    TaskForm taskform = (TaskForm)form;
    String date = request.getParameter("date");
    String assignedtoUserId = request.getParameter("assignedtoUserId");
    logger.info("Inside addtasksrc method" + assignedtoUserId);
    
    TaskData task = new TaskData();
    task.setTaskname(taskform.getTaskname());
    task.setIdvalue(0L);
    task.setTasktype(Common.MANUAL_ADD_TASK);
    task.setAssignedbyUserId(user1.getUserId());
    task.setAssignedbyUserName(user1.getFirstName() + " " + user1.getLastName());
    task.setAssignedtoUserId(new Long(assignedtoUserId).longValue());
    task.setAssignedtoUserName(taskform.getAssignedtoUserName());
    task.setCreatedBy(user1.getUserName());
    task.setCreatedDate(new Date());
    task.setStatus("A");
    task.setUuid("");
    if (!StringUtils.isNullOrEmpty(date))
    {
      String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
      Calendar cal = DateUtil.convertStringDateToCalendar(date, datepattern);
      
      task.setEventdate(cal.getTime());
    }
    else
    {
      task.setEventdate(new Date());
    }
    task.setSuper_user_key(user1.getSuper_user_key());
    task.setNotes(taskform.getNotes());
    task = BOFactory.getTaskBO().saveTask(user1, task, taskform.getNotes());
    

    request.setAttribute("addtask", "yes");
    return mapping.findForward("addtask");
  }
  
  public ActionForward edittask(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside edittask method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    TaskForm taskform = (TaskForm)form;
    String tasdkid = request.getParameter("tasdkid");
    
    TaskData task = TaskBO.getTask(new Long(tasdkid).longValue());
    taskform.setTaskId(task.getTaskId());
    taskform.setTaskname(task.getTaskname());
    taskform.setIdvalue(task.getIdvalue());
    taskform.setTasktype(task.getTasktype());
    taskform.setAssignedbyUserId(task.getAssignedbyUserId());
    taskform.setAssignedbyUserName(task.getAssignedbyUserName());
    taskform.setAssignedtoUserId(task.getAssignedtoUserId());
    taskform.setAssignedtoUserName(task.getAssignedtoUserName());
    taskform.setCreatedBy(task.getCreatedBy());
    taskform.setCreatedDate(task.getCreatedDate());
    taskform.setStatus(task.getStatus());
    taskform.setUuid(task.getUuid());
    

    String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
    String dt = DateUtil.convertDateToStringDate(task.getEventdate(), datepattern);
    taskform.setEventdate(dt);
    
    taskform.setNotes(task.getNotes());
    
    request.setAttribute("addtask", "no");
    return mapping.findForward("addtask");
  }
  
  public ActionForward updatetask(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updatetask method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    TaskForm taskform = (TaskForm)form;
    String tasdkid = request.getParameter("tasdkid");
    String status = request.getParameter("status");
    logger.info("Inside updatetask method" + status);
    
    TaskData task = TaskBO.getTask(new Long(tasdkid).longValue());
    taskform.setTaskId(task.getTaskId());
    
    task.setNotes(taskform.getNotes());
    if ((!StringUtils.isNullOrEmpty(status)) && (status.equals("on"))) {
      task.setStatus("C");
    }
    task = BOFactory.getTaskBO().updateTaskTx(task);
    if (task.getStatus().equals("C")) {
      BOFactory.getTaskBO().sendTaskCompletionEmail(user1, task, task.getNotes());
    }
    request.setAttribute("addtask", "yes");
    return mapping.findForward("addtask");
  }
}
