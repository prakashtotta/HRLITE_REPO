package com.bo;

import com.bean.JobApplicant;
import com.bean.JobApplicationEvent;
import com.bean.TaskData;
import com.bean.User;
import com.common.Common;
import com.dao.TaskDAO;
import com.manager.EmailTaskManager;
import com.resources.Constant;
import com.util.EmailTask;
import com.util.StringUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

public class TaskBO
{
  TaskDAO taskdao;
  protected static final Logger logger = Logger.getLogger(TaskBO.class);
  
  public static void sendTaskAssignEmail(User user1, TaskData taskdata, String comment)
  {
    BOFactory.getTaskBO().setTaskEventDate(user1, taskdata);
    logger.info("sendTaskAssignEmail start");
    try
    {
      User touser = UserBO.getUserByUserId(taskdata.getAssignedtoUserId());
      
      logger.info("email need to send to" + touser.getEmailId());
      
      String toemailid = "\"" + touser.getFirstName() + " " + touser.getLastName() + "\"" + " " + "<" + touser.getEmailId() + ">";
      
      String[] tonew = { toemailid };
      String alltaskcc = Constant.getValue("all.task.cc");
      List ccList = new ArrayList();
      if ((!StringUtils.isNullOrEmpty(Constant.getValue("task.assigner.email.included.in.notification"))) && (Constant.getValue("task.assigner.email.included.in.notification").equals("yes"))) {
        ccList.add(user1.getEmailId());
      }
      if (!StringUtils.isNullOrEmpty(alltaskcc)) {
        ccList.add(alltaskcc);
      }
      String[] ccnew = new String[ccList.size()];
      ccList.toArray(ccnew);
      
      String from = "\"" + user1.getFirstName() + " " + user1.getLastName() + "\"" + " " + "<" + user1.getEmailId() + ">";
      
      EmailTask emailtasknew = new EmailTask(from, tonew, ccnew, null, from, "dummysubject", null, "dummybody", null, 0, null);
      emailtasknew.setFunctionType(Common.TASK_ASSIGN);
      emailtasknew.setUser(user1);
      emailtasknew.setTaskdata(taskdata);
      emailtasknew.setComment(comment);
      EmailTaskManager.sendEmail(emailtasknew);
    }
    catch (Exception e) {}
    logger.info("sendTaskAssignEmail end");
  }
  
  public static void sendTaskAssignEmail(JobApplicant applicant, TaskData taskdata, String comment)
  {
    BOFactory.getTaskBO().setTaskEventDate(applicant.getSuper_user_key(), taskdata);
    logger.info("sendTaskAssignEmail start");
    try
    {
      User touser = UserBO.getUserByUserId(taskdata.getAssignedtoUserId());
      
      logger.info("email need to send to" + touser.getEmailId());
      
      String toemailid = "\"" + touser.getFirstName() + " " + touser.getLastName() + "\"" + " " + "<" + touser.getEmailId() + ">";
      
      String[] tonew = { toemailid };
      String alltaskcc = Constant.getValue("all.task.cc");
      List ccList = new ArrayList();
      if (!StringUtils.isNullOrEmpty(alltaskcc)) {
        ccList.add(alltaskcc);
      }
      String[] ccnew = new String[ccList.size()];
      ccList.toArray(ccnew);
      
      String from = "\"" + applicant.getFullName() + "\"" + " " + "<" + applicant.getEmail() + ">";
      
      EmailTask emailtasknew = new EmailTask(from, tonew, ccnew, null, from, "dummysubject", null, "dummybody", null, 0, null);
      emailtasknew.setFunctionType(Common.TASK_ASSIGN);
      emailtasknew.setUser(touser);
      emailtasknew.setTaskdata(taskdata);
      emailtasknew.setComment(comment);
      EmailTaskManager.sendEmail(emailtasknew);
    }
    catch (Exception e) {}
    logger.info("sendTaskAssignEmail end");
  }
  
  public void setTaskEventDate(User user1, TaskData taskdata)
  {
    try
    {
      if ((taskdata.getTasktype() != null) && (taskdata.getEventdate() == null) && ((taskdata.getTasktype().equals(Common.APPLICANT_INTERVIEW_TASK)) || (taskdata.getTasktype().equals(Common.APPLICANT_INTERVIEW_TASK))))
      {
        JobApplicationEvent event = BOFactory.getApplicantBO().getApplicationEventByApplicantIdSortByCreatedDate(taskdata.getIdvalue());
        if (event != null) {
          taskdata.setEventdate(event.getInterviewDate());
        } else {
          taskdata.setEventdate(new Date());
        }
      }
      else if ((taskdata.getTasktype() != null) && (taskdata.getTasktype().equals(Common.ONBOARDING_TASK)))
      {
        if (taskdata.getEventdate() == null) {
          taskdata.setEventdate(new Date());
        }
      }
      else if (taskdata.getEventdate() == null)
      {
        taskdata.setEventdate(new Date());
      }
      taskdata.setSuper_user_key(user1.getSuper_user_key());
      this.taskdao.updateTaskTx(taskdata);
    }
    catch (Exception e)
    {
      logger.info("exception on setTaskEventDate", e);
    }
  }
  
  public void setTaskEventDate(long superUserKey, TaskData taskdata)
  {
    try
    {
      if ((taskdata.getTasktype() != null) && (taskdata.getEventdate() == null) && ((taskdata.getTasktype().equals(Common.APPLICANT_INTERVIEW_TASK)) || (taskdata.getTasktype().equals(Common.APPLICANT_INTERVIEW_TASK))))
      {
        JobApplicationEvent event = BOFactory.getApplicantBO().getApplicationEventByApplicantIdSortByCreatedDate(taskdata.getIdvalue());
        if (event != null) {
          taskdata.setEventdate(event.getInterviewDate());
        } else {
          taskdata.setEventdate(new Date());
        }
      }
      else if ((taskdata.getTasktype() != null) && (taskdata.getTasktype().equals(Common.ONBOARDING_TASK)))
      {
        if (taskdata.getEventdate() == null) {
          taskdata.setEventdate(new Date());
        }
      }
      else if (taskdata.getEventdate() == null)
      {
        taskdata.setEventdate(new Date());
      }
      taskdata.setSuper_user_key(superUserKey);
      this.taskdao.updateTaskTx(taskdata);
    }
    catch (Exception e)
    {
      logger.info("exception on setTaskEventDate", e);
    }
  }
  
  public static void sendTaskAssignEmail(User user1, List taskList, String comment)
  {
    for (int i = 0; i < taskList.size(); i++)
    {
      TaskData taskdata = (TaskData)taskList.get(i);
      sendTaskAssignEmail(user1, taskdata, comment);
    }
  }
  
  public static void sendTaskAssignEmail(JobApplicant applicant, List taskList, String comment)
  {
    for (int i = 0; i < taskList.size(); i++)
    {
      TaskData taskdata = (TaskData)taskList.get(i);
      sendTaskAssignEmail(applicant, taskdata, comment);
    }
  }
  
  public static void sendEventEmail(JobApplicant applicant, User user1, String comment, String event, boolean isApplicantInEmail, String emailNotificationSettingFunctionName)
  {
    try
    {
      String toemail = "";
      String toemailto = "";
      if (user1 == null)
      {
        User hiringmgr = BOFactory.getJobRequistionBO().getHiringManagerByReqId(applicant.getReqId());
        toemail = hiringmgr.getEmailId();
        toemailto = hiringmgr.getEmailId();
      }
      else
      {
        toemail = user1.getEmailId();
        toemailto = user1.getEmailId();
      }
      if (isApplicantInEmail) {
        toemail = applicant.getEmail();
      }
      String[] to = { toemail };
      
      String[] bcc = null;
      



      List cclist = BOFactory.getApplicantBO().getCCListForApplicant(user1, applicant, null, emailNotificationSettingFunctionName, false);
      if (isApplicantInEmail) {
        cclist.add(toemailto);
      }
      String[] cc = new String[cclist.size()];
      cclist.toArray(cc);
      
      String replyTo = toemailto;
      
      EmailTask emailtask = new EmailTask(replyTo, to, cc, bcc, replyTo, "dummysubject", null, "dummybody", null, 0, null);
      


      emailtask.setUser(user1);
      emailtask.setEvent(event);
      emailtask.setComment(comment);
      emailtask.setFunctionType(Common.EVENT_TO_ALL);
      emailtask.setApplicant(applicant);
      EmailTaskManager.sendEmail(emailtask);
    }
    catch (Exception e)
    {
      logger.info("Exception on sendEventEmail", e);
    }
  }
  
  public void sendTaskCompletionEmail(User user1, TaskData taskdata, String comment)
  {
    logger.info("sendTaskCompletionEmail start");
    try
    {
      User touser = UserBO.getUserByUserId(taskdata.getAssignedbyUserId());
      
      logger.info("email need to send to" + touser.getEmailId());
      
      String toemailid = "\"" + touser.getFirstName() + " " + touser.getLastName() + "\"" + " " + "<" + touser.getEmailId() + ">";
      
      String[] tonew = { toemailid };
      String alltaskcc = Constant.getValue("all.task.cc");
      List ccList = new ArrayList();
      if ((!StringUtils.isNullOrEmpty(Constant.getValue("task.assigner.email.included.in.notification"))) && (Constant.getValue("task.assigner.email.included.in.notification").equals("yes"))) {
        ccList.add(user1.getEmailId());
      }
      if (!StringUtils.isNullOrEmpty(alltaskcc)) {
        ccList.add(alltaskcc);
      }
      String[] ccnew = new String[ccList.size()];
      ccList.toArray(ccnew);
      
      String from = "\"" + user1.getFirstName() + " " + user1.getLastName() + "\"" + " " + "<" + user1.getEmailId() + ">";
      
      EmailTask emailtasknew = new EmailTask(from, tonew, ccnew, null, from, "dummysubject", null, "dummybody", null, 0, null);
      emailtasknew.setFunctionType(Common.TASK_COMPLETE);
      emailtasknew.setUser(user1);
      emailtasknew.setTaskdata(taskdata);
      emailtasknew.setComment(comment);
      EmailTaskManager.sendEmail(emailtasknew);
    }
    catch (Exception e) {}
    logger.info("sendTaskCompletionEmail end");
  }
  
  public TaskData saveTask(User user1, TaskData task, String comment)
  {
    task = this.taskdao.saveTaskTx(task);
    task.setIdvalue(task.getTaskId());
    this.taskdao.updateTaskTx(task);
    sendTaskAssignEmail(user1, task, comment);
    return task;
  }
  
  public static TaskData completeTask(TaskData task, String updatedBy)
  {
    task.setUpdatedBy(updatedBy);
    task.setUpdatedDate(new Date());
    task.setStatus("C");
    task = TaskDAO.updateTask(task);
    return task;
  }
  
  public TaskData updateTaskTx(TaskData task)
  {
    return this.taskdao.updateTaskTx(task);
  }
  
  public static List getPendingTasksForPagination(int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return TaskDAO.getPendingTasksForPagination(pageSize, startIndex, dir_str, sort_str);
  }
  
  public static int getCountOfPendingTasks()
  {
    return TaskDAO.getCountOfPendingTasks();
  }
  
  public static List getBulkUploadTasksForPagination(int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return TaskDAO.getBulkUploadTasksForPagination(pageSize, startIndex, dir_str, sort_str);
  }
  
  public static int getCountOfBulkUploadTasks()
  {
    return TaskDAO.getCountOfBulkUploadTasks();
  }
  
  public static TaskData getTaskAssignedToUser(long userid, long idvalue, String taskType)
  {
    return TaskDAO.getTaskAssignedToUser(userid, idvalue, taskType);
  }
  
  public static TaskData getTask(long id)
  {
    return TaskDAO.getTask(id);
  }
  
  public static List getPendingTasksForPagination(User user, String cri, String assigneddate, String assignedbyUserId, String assignedtoUserId, String tasktype, String updatedBy, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return TaskDAO.getPendingTasksForPagination(user, cri, assigneddate, assignedbyUserId, assignedtoUserId, tasktype, updatedBy, pageSize, startIndex, dir_str, sort_str);
  }
  
  public static List getBulkUploadTasksForPagination(User user, String cri, String assigneddate, String createdBy, String tasktype, String status, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return TaskDAO.getBulkUploadTasksForPagination(user, cri, assigneddate, createdBy, tasktype, status, pageSize, startIndex, dir_str, sort_str);
  }
  
  public static int getCountOfBulkUploadTasks(User user, String cri, String assigneddate, String createdBy, String tasktype, String status)
  {
    return TaskDAO.getCountOfBulkUploadTasks(user, cri, assigneddate, createdBy, tasktype, status);
  }
  
  public static int getCountOfPendingTasks(User user, String cri, String assigneddate, String assignedbyUserId, String assignedtoUserId, String tasktype, String updatedBy)
  {
    return TaskDAO.getCountOfPendingTasks(user, cri, assigneddate, assignedbyUserId, assignedtoUserId, tasktype, updatedBy);
  }
  
  public static List getPendingTasksForPagination(long userId, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return TaskDAO.getPendingTasksForPagination(userId, pageSize, startIndex, dir_str, sort_str);
  }
  
  public static int getCountOfPendingTasks(long userId)
  {
    return TaskDAO.getCountOfPendingTasks(userId);
  }
  
  public static List getPendingTasksForPaginationBysearchTypeandAssignedby(long userId, String tasktype, String assignedbyUserId, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return TaskDAO.getPendingTasksForPaginationBysearchTypeandAssignedby(userId, tasktype, assignedbyUserId, pageSize, startIndex, dir_str, sort_str);
  }
  
  public static int getCountOfPendingTasksBysearchTypeandAssignedby(long userId, String tasktype, String assignedbyUserId)
  {
    return TaskDAO.getCountOfPendingTasksBysearchTypeandAssignedby(userId, tasktype, assignedbyUserId);
  }
  
  public static void deleteCompletedTasks()
  {
    TaskDAO.deleteCompletedTasks();
  }
  
  public static List getAllTaskBySuperUserKeyWithCurrentMonth(long super_user_key, int month, int year)
  {
    return TaskDAO.getAllTaskBySuperUserKeyWithCurrentMonth(super_user_key, month, year);
  }
  
  public static List getAllTaskByDay(List taskList, int day)
  {
    List ntaskList = new ArrayList();
    for (int i = 0; i < taskList.size(); i++)
    {
      TaskData task = (TaskData)taskList.get(i);
      if (task.getDayid() == day) {
        ntaskList.add(task);
      }
    }
    return ntaskList;
  }
  
  public TaskDAO getTaskdao()
  {
    return this.taskdao;
  }
  
  public void setTaskdao(TaskDAO taskdao)
  {
    this.taskdao = taskdao;
  }
}
