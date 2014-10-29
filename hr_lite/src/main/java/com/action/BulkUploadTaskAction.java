package com.action;

import com.bean.BulkUploadTask;
import com.dao.TaskDAO;
import com.form.BulkUploadTaskForm;
import com.resources.Constant;
import com.util.StringUtils;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class BulkUploadTaskAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(BulkUploadTaskAction.class);
  
  public ActionForward bulktasklist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside bulktasklist method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    BulkUploadTaskForm taskform = (BulkUploadTaskForm)form;
    List tasktypeList = Constant.getBulUploadTaskTypesList();
    
    taskform.setTasktypeList(tasktypeList);
    List statusList = Constant.getBulUploadTaskStatusList();
    taskform.setStatusList(statusList);
    return mapping.findForward("bulktasklist");
  }
  
  public ActionForward mybulktasklist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside mybulktasklist method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    BulkUploadTaskForm taskform = (BulkUploadTaskForm)form;
    List tasktypeList = Constant.getBulUploadTaskTypesList();
    
    taskform.setTasktypeList(tasktypeList);
    List statusList = Constant.getBulUploadTaskStatusList();
    taskform.setStatusList(statusList);
    return mapping.findForward("mybulktasklist");
  }
  
  public ActionForward mybulktasklistsearch(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside mybulktasklistsearch method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String assigneddate = request.getParameter("assigneddate");
    String cri = request.getParameter("cri");
    logger.info("Inside mybulktasklistsearch method22222222222222222222222" + cri);
    BulkUploadTaskForm taskform = (BulkUploadTaskForm)form;
    taskform.setAssignedcri(cri);
    List tasktypeList = Constant.getBulUploadTaskTypesList();
    taskform.setSearchassigneddate(assigneddate);
    taskform.setTasktypeList(tasktypeList);
    List statusList = Constant.getBulUploadTaskStatusList();
    taskform.setStatusList(statusList);
    request.setAttribute("searchpagedisplay", "yes");
    return mapping.findForward("mybulktasklist");
  }
  
  public ActionForward searcjbulktasks(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searcjbulktasks method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    BulkUploadTaskForm taskform = (BulkUploadTaskForm)form;
    String cri = request.getParameter("cri");
    String createdbyid = request.getParameter("createdbyid");
    String assigneddate = request.getParameter("assigneddate");
    if (!StringUtils.isNullOrEmpty(createdbyid)) {
      taskform.setCreatedbyid(new Long(createdbyid).longValue());
    }
    if (!StringUtils.isNullOrEmpty(assigneddate)) {
      taskform.setSearchassigneddate(assigneddate);
    }
    taskform.setAssignedcri(cri);
    
    List tasktypeList = Constant.getBulUploadTaskTypesList();
    taskform.setTasktypeList(tasktypeList);
    List statusList = Constant.getBulUploadTaskStatusList();
    taskform.setStatusList(statusList);
    taskform.setCreatedBy(taskform.getCreatedBy());
    request.setAttribute("searchpagedisplay", "yes");
    return mapping.findForward("bulktasklist");
  }
  
  public ActionForward bulkloadtaskdetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String taskid = request.getParameter("taskid");
    BulkUploadTask task = TaskDAO.getBulkUploadTaskByTaskId(new Long(taskid).longValue());
    BulkUploadTaskForm taskform = (BulkUploadTaskForm)form;
    taskform.setTask(task);
    logger.info("Inside bulkloadtaskdetails method");
    return mapping.findForward("bulkloadtaskdetails");
  }
}
