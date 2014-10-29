package com.dao;

import com.bean.BulkUploadTask;
import com.bean.EmailTemplates;
import com.bean.Organization;
import com.bean.OrganizationEmailTemplate;
import com.bean.TaskData;
import com.bean.User;
import com.bean.onboard.OnBoardingTaskAttributes;
import com.bean.onboard.OnBoardingTaskDefinitions;
import com.bean.onboard.OnBoardingTemplate;
import com.bean.system.SystemRule;
import com.bean.system.SystemRuleUser;
import com.resources.Constant;
import com.util.DateUtil;
import com.util.StringUtils;
import java.io.PrintStream;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class TaskDAO
  extends HibernateDaoSupport
{
  protected static final Logger logger = Logger.getLogger(TaskDAO.class);
  
  public static TaskData saveTask(TaskData task)
  {
    logger.info("Inside saveTask method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      session.save(task);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on saveTask()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return task;
  }
  
  public TaskData saveTaskTx(TaskData task)
  {
    logger.info("Inside saveTaskTx method");
    
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.save(task);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveTaskTx()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return task;
  }
  
  public TaskData updateTaskTx(TaskData task)
  {
    logger.info("Inside updateTaskTx method");
    
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.update(task);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveTaskTx()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return task;
  }
  
  public static BulkUploadTask saveBulkUploadTask(BulkUploadTask task)
  {
    logger.info("Inside saveBulkUploadTask method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      session.save(task);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on saveBulkUploadTask()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return task;
  }
  
  public static BulkUploadTask updateBulkUploadTask(BulkUploadTask task)
  {
    logger.info("Inside updateBulkUploadTask method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      session.update(task);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on updateBulkUploadTask()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return task;
  }
  
  public static TaskData updateTask(TaskData task)
  {
    logger.info("Inside updateTask method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      session.update(task);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on updateTask()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return task;
  }
  
  public static TaskData getTaskById(long id)
  {
    logger.info("Inside getTaskById method");
    TaskData task = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      task = (TaskData)session.createCriteria(TaskData.class).add(Restrictions.eq("id", new Long(id))).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getTaskById()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return task;
  }
  
  public static TaskData getTaskByTaskId(long taskid)
  {
    logger.info("Inside getTaskByTaskId method");
    TaskData task = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      task = (TaskData)session.createCriteria(TaskData.class).add(Restrictions.eq("taskId", new Long(taskid))).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getTaskByTaskId()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return task;
  }
  
  public static BulkUploadTask getBulkUploadTaskByTaskId(long taskid)
  {
    logger.info("Inside getBulkUploadTaskByTaskId method");
    BulkUploadTask task = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      task = (BulkUploadTask)session.createCriteria(BulkUploadTask.class).add(Restrictions.eq("bulkuploadtaskid", new Long(taskid))).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getBulkUploadTaskByTaskId()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return task;
  }
  
  public static TaskData getTask(long idvalue, String taskType)
  {
    logger.info("Inside getTask method");
    TaskData task = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      logger.info(Long.valueOf(idvalue));
      logger.info(taskType);
      task = (TaskData)session.createCriteria(TaskData.class).add(Restrictions.eq("idvalue", new Long(idvalue))).add(Restrictions.eq("tasktype", taskType)).add(Restrictions.eq("status", "A")).uniqueResult();
      logger.info(task);
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getTask()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return task;
  }
  
  public static TaskData getTaskAssignedToUser(long userid, long idvalue, String taskType)
  {
    logger.info("Inside getTaskAssignedToUser method");
    TaskData task = null;
    List list = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      logger.info(Long.valueOf(idvalue));
      logger.info(taskType);
      list = session.createCriteria(TaskData.class).add(Restrictions.eq("assignedtoUserId", new Long(userid))).add(Restrictions.eq("idvalue", new Long(idvalue))).add(Restrictions.eq("tasktype", taskType)).add(Restrictions.eq("status", "A")).list();
      if ((list != null) && (list.size() > 0)) {
        task = (TaskData)list.get(0);
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getTaskAssignedToUser()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return task;
  }
  
  public static TaskData getTask(long id)
  {
    logger.info("Inside getTask method");
    TaskData task = null;
    List list = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      task = (TaskData)session.createCriteria(TaskData.class).add(Restrictions.eq("taskId", Long.valueOf(id))).uniqueResult();
      

      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getTask()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return task;
  }
  
  public static List getPendingTasksForPagination(int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getPendingTasksForPagination method");
    List taskList = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      Criteria outer = session.createCriteria(TaskData.class).add(Restrictions.eq("status", "A"));
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      taskList = outer.list();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getPendingTasksForPagination()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return taskList;
  }
  
  public static List getBulkUploadTasksForPagination(int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getBulkUploadTasksForPagination method");
    List taskList = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      Criteria outer = session.createCriteria(BulkUploadTask.class);
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      taskList = outer.list();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getBulkUploadTasksForPagination()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return taskList;
  }
  
  public static int getCountOfPendingTasks()
  {
    logger.info("Inside getCountOfPendingTasks method");
    int totaluser = 0;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      

      Criteria outer = session.createCriteria(TaskData.class).add(Restrictions.eq("status", "A"));
      





      outer.setProjection(Projections.rowCount());
      totaluser = ((Integer)outer.list().get(0)).intValue();
      System.out.println("totaluser" + totaluser);
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfPendingTasks()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return totaluser;
  }
  
  public static int getCountOfBulkUploadTasks()
  {
    logger.info("Inside getCountOfBulkUploadTasks method");
    int totaluser = 0;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      

      Criteria outer = session.createCriteria(BulkUploadTask.class);
      





      outer.setProjection(Projections.rowCount());
      totaluser = ((Integer)outer.list().get(0)).intValue();
      System.out.println("totaluser" + totaluser);
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfBulkUploadTasks()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return totaluser;
  }
  
  public static int getCountOfBulkUploadTasks(User user, String cri, String assigneddate, String createdBy, String tasktype, String status)
  {
    logger.info("Inside getCountOfBulkUploadTasks method");
    int totaluser = 0;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      

      Criteria outer = session.createCriteria(BulkUploadTask.class);
      if ((!StringUtils.isNullOrEmpty(tasktype)) && (!tasktype.equals("NoValue")) && (!tasktype.equals("null"))) {
        outer.add(Restrictions.eq("tasktype", tasktype.trim()));
      }
      if ((!StringUtils.isNullOrEmpty(status)) && (!status.equals("NoValue")) && (!status.equals("null"))) {
        outer.add(Restrictions.eq("status", status.trim()));
      }
      if ((!StringUtils.isNullOrEmpty(createdBy)) && (!createdBy.equals("0")) && (!createdBy.equals("null"))) {
        outer.add(Restrictions.eq("createdById", Long.valueOf(new Long(createdBy).longValue())));
      }
      logger.info("assigneddate" + assigneddate);
      if ((!StringUtils.isNullOrEmpty(assigneddate)) && (!assigneddate.equals("null")))
      {
        String datepattern = DateUtil.getDatePatternFormat(user.getLocale());
        
        DateFormat format = new SimpleDateFormat(datepattern + " hh:mm:ss");
        
        Date aDate = format.parse(assigneddate + " 00:00:00");
        
        Date bDate = format.parse(assigneddate + " 23:59:00");
        if (cri.equals("on")) {
          outer.add(Expression.between("createdDate", new Date(aDate.getTime()), new Date(bDate.getTime())));
        }
        if (cri.equals("after")) {
          outer.add(Expression.gt("createdDate", new Date(aDate.getTime())));
        }
        if (cri.equals("before")) {
          outer.add(Expression.lt("createdDate", new Date(aDate.getTime())));
        }
      }
      outer.setProjection(Projections.rowCount());
      totaluser = ((Integer)outer.list().get(0)).intValue();
      System.out.println("totaluser" + totaluser);
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfBulkUploadTasks()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return totaluser;
  }
  
  public static List getPendingTasksForPagination(User user, String cri, String assigneddate, String assignedbyUserId, String assignedtoUserId, String tasktype, String updatedBy, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getPendingTasksForPagination method");
    List applicantList = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      Criteria outer = session.createCriteria(TaskData.class).add(Restrictions.eq("status", "A"));
      if ((!StringUtils.isNullOrEmpty(tasktype)) && (!tasktype.equals("NoValue"))) {
        outer.add(Restrictions.eq("tasktype", tasktype));
      }
      if ((!StringUtils.isNullOrEmpty(assignedbyUserId)) && (!assignedbyUserId.equals("0"))) {
        outer.add(Restrictions.eq("assignedbyUserId", new Long(assignedbyUserId)));
      }
      if ((!StringUtils.isNullOrEmpty(assignedtoUserId)) && (!assignedtoUserId.equals("0"))) {
        outer.add(Restrictions.eq("assignedtoUserId", new Long(assignedtoUserId)));
      }
      if (!StringUtils.isNullOrEmpty(updatedBy)) {
        outer.add(Restrictions.like("updatedBy", updatedBy));
      }
      logger.info("assigneddate" + assigneddate);
      if ((!StringUtils.isNullOrEmpty(assigneddate)) && (!assigneddate.equals("null")))
      {
        String datepattern = DateUtil.getDatePatternFormat(user.getLocale());
        
        DateFormat format = new SimpleDateFormat(datepattern + " hh:mm:ss");
        
        Date aDate = format.parse(assigneddate + " 00:00:00");
        
        Date bDate = format.parse(assigneddate + " 23:59:00");
        if (cri.equals("on")) {
          outer.add(Expression.between("createdDate", new Date(aDate.getTime()), new Date(bDate.getTime())));
        }
        if (cri.equals("after")) {
          outer.add(Expression.gt("createdDate", new Date(aDate.getTime())));
        }
        if (cri.equals("before")) {
          outer.add(Expression.lt("createdDate", new Date(aDate.getTime())));
        }
      }
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      applicantList = outer.list();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getPendingTasksForPagination()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return applicantList;
  }
  
  public static List getBulkUploadTasksForPagination(User user, String cri, String assigneddate, String createdBy, String tasktype, String status, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getBulkUploadTasksForPagination method");
    logger.info("Inside getBulkUploadTasksForPagination method33333333333333" + cri);
    List applicantList = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      Criteria outer = session.createCriteria(BulkUploadTask.class);
      logger.info("tasktype" + tasktype);
      logger.info("status" + status);
      if ((!StringUtils.isNullOrEmpty(tasktype)) && (!tasktype.equals("NoValue")) && (!tasktype.equals("null"))) {
        outer.add(Restrictions.eq("tasktype", tasktype.trim()));
      }
      if ((!StringUtils.isNullOrEmpty(status)) && (!status.equals("NoValue")) && (!status.equals("null"))) {
        outer.add(Restrictions.eq("status", status.trim()));
      }
      if ((!StringUtils.isNullOrEmpty(createdBy)) && (!createdBy.equals("0")) && (!createdBy.equals("null"))) {
        outer.add(Restrictions.eq("createdById", Long.valueOf(new Long(createdBy).longValue())));
      }
      logger.info("assigneddate" + assigneddate);
      if ((!StringUtils.isNullOrEmpty(assigneddate)) && (!assigneddate.equals("null")))
      {
        String datepattern = DateUtil.getDatePatternFormat(user.getLocale());
        
        DateFormat format = new SimpleDateFormat(datepattern + " hh:mm:ss");
        
        Date aDate = format.parse(assigneddate + " 00:00:00");
        
        Date bDate = format.parse(assigneddate + " 23:59:00");
        if (cri.equals("on")) {
          outer.add(Expression.between("createdDate", new Date(aDate.getTime()), new Date(bDate.getTime())));
        }
        if (cri.equals("after")) {
          outer.add(Expression.gt("createdDate", new Date(aDate.getTime())));
        }
        if (cri.equals("before")) {
          outer.add(Expression.lt("createdDate", new Date(aDate.getTime())));
        }
      }
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      applicantList = outer.list();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getBulkUploadTasksForPagination()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return applicantList;
  }
  
  public static List getPendingTasksForPagination(long userid, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getPendingTasksForPagination method");
    List applicantList = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      Criteria outer = session.createCriteria(TaskData.class).add(Restrictions.eq("status", "A"));
      if (userid != 0L) {
        outer.add(Restrictions.eq("assignedtoUserId", new Long(userid)));
      }
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      applicantList = outer.list();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getPendingTasksForPagination()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return applicantList;
  }
  
  public static List getAllTaskBySuperUserKeyWithCurrentMonth(long super_user_key, int month, int year)
  {
    logger.info("Inside getAllTaskBySuperUserKeyWithCurrentMonth method");
    List taskList = new ArrayList();
    List ntaskList = new ArrayList();
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      String sql = "select taskid, idvalue,task_name,task_type,assignedto_user_id,assignedto_user_name,event_date,uuid,DAYOFMONTH(event_date),status from task_data  where Month(event_date) = :cmonth and YEAR(event_date) = :cyear and super_user_key = :super_user_key";
      

      Query query = session.createSQLQuery(sql);
      query.setInteger("cmonth", month);
      query.setInteger("cyear", year);
      query.setLong("super_user_key", super_user_key);
      
      taskList = query.list();
      logger.info("taskList" + taskList.size());
      logger.info("sql" + sql);
      for (int i = 0; i < taskList.size(); i++)
      {
        Object[] obj = (Object[])taskList.get(i);
        TaskData task = new TaskData();
        
        BigInteger appId = (BigInteger)obj[0];
        long tid = appId.longValue();
        task.setTaskId(tid);
        
        BigInteger idv = (BigInteger)obj[1];
        long idvalue = idv.longValue();
        task.setIdvalue(idvalue);
        
        task.setTaskname((String)obj[2]);
        task.setTasktype((String)obj[3]);
        
        BigInteger assign = (BigInteger)obj[4];
        long assigntoid = assign.longValue();
        task.setAssignedbyUserId(assigntoid);
        
        task.setAssignedbyUserName((String)obj[5]);
        
        Date evdate = (Date)obj[6];
        task.setEventdate(evdate);
        
        task.setUuid((String)obj[7]);
        
        BigInteger dayid = (BigInteger)obj[8];
        int dayidv = dayid.intValue();
        
        task.setDayid(dayidv);
        
        task.setStatus((String)obj[9]);
        
        ntaskList.add(task);
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllTaskBySuperUserKeyWithCurrentMonth()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return ntaskList;
  }
  
  public static List getPendingTasksForPaginationBysearchTypeandAssignedby(long userid, String tasktype, String assignedbyUserId, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getPendingTasksForPaginationBysearchTypeandAssignedby method");
    List applicantList = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      Criteria outer = session.createCriteria(TaskData.class).add(Restrictions.eq("status", "A"));
      if (userid != 0L) {
        outer.add(Restrictions.eq("assignedtoUserId", new Long(userid)));
      }
      if ((!StringUtils.isNullOrEmpty(tasktype)) && (!tasktype.equals("NoValue"))) {
        outer.add(Restrictions.eq("tasktype", tasktype));
      }
      if ((!StringUtils.isNullOrEmpty(assignedbyUserId)) && (!assignedbyUserId.equals("0"))) {
        outer.add(Restrictions.eq("assignedbyUserId", new Long(assignedbyUserId)));
      }
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      applicantList = outer.list();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getPendingTasksForPaginationBysearchTypeandAssignedby()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return applicantList;
  }
  
  public static int getCountOfPendingTasks(User user, String cri, String assigneddate, String assignedbyUserId, String assignedtoUserId, String tasktype, String updatedBy)
  {
    logger.info("Inside getCountOfPendingTasks method");
    int totaluser = 0;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      

      Criteria outer = session.createCriteria(TaskData.class).add(Restrictions.eq("status", "A"));
      if ((!StringUtils.isNullOrEmpty(tasktype)) && (!tasktype.equals("NoValue"))) {
        outer.add(Restrictions.eq("tasktype", tasktype));
      }
      if ((!StringUtils.isNullOrEmpty(assignedbyUserId)) && (!assignedbyUserId.equals("0"))) {
        outer.add(Restrictions.eq("assignedbyUserId", new Long(assignedbyUserId)));
      }
      if ((!StringUtils.isNullOrEmpty(assignedtoUserId)) && (!assignedtoUserId.equals("0"))) {
        outer.add(Restrictions.eq("assignedtoUserId", new Long(assignedtoUserId)));
      }
      if (!StringUtils.isNullOrEmpty(updatedBy)) {
        outer.add(Restrictions.like("updatedBy", updatedBy));
      }
      logger.info("assigneddate" + assigneddate);
      if ((!StringUtils.isNullOrEmpty(assigneddate)) && (!assigneddate.equals("null")))
      {
        String datepattern = DateUtil.getDatePatternFormat(user.getLocale());
        
        DateFormat format = new SimpleDateFormat(datepattern + " hh:mm:ss");
        
        Date aDate = format.parse(assigneddate + " 00:00:00");
        
        Date bDate = format.parse(assigneddate + " 23:59:00");
        if (cri.equals("on")) {
          outer.add(Expression.between("createdDate", new Date(aDate.getTime()), new Date(bDate.getTime())));
        }
        if (cri.equals("after")) {
          outer.add(Expression.gt("createdDate", new Date(aDate.getTime())));
        }
        if (cri.equals("before")) {
          outer.add(Expression.lt("createdDate", new Date(aDate.getTime())));
        }
      }
      outer.setProjection(Projections.rowCount());
      totaluser = ((Integer)outer.list().get(0)).intValue();
      System.out.println("totaluser" + totaluser);
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfPendingTasks()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return totaluser;
  }
  
  public static int getCountOfPendingTasks(long userId)
  {
    logger.info("Inside getCountOfPendingTasks method");
    int totaluser = 0;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      

      Criteria outer = session.createCriteria(TaskData.class).add(Restrictions.eq("status", "A"));
      if (userId != 0L) {
        outer.add(Restrictions.eq("assignedtoUserId", new Long(userId)));
      }
      outer.setProjection(Projections.rowCount());
      totaluser = ((Integer)outer.list().get(0)).intValue();
      System.out.println("totaluser" + totaluser);
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfPendingTasks()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return totaluser;
  }
  
  public static int deleteCompletedTasks()
  {
    logger.info("Inside deleteCompletedTasks method");
    int totaluser = 0;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      int days = new Integer(Constant.getValue("offset.delete.completed.tasks")).intValue();
      
      Calendar cal = Calendar.getInstance();
      cal.add(6, -days);
      Date maxModDate = cal.getTime();
      

      String hql = "delete from TaskData where updatedDate <= :maxModDate and status = :status";
      Query query = session.createQuery(hql);
      query.setDate("maxModDate", maxModDate);
      query.setString("status", "C");
      
      int rowCount = query.executeUpdate();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteCompletedTasks()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return totaluser;
  }
  
  public static int getCountOfPendingTasksBysearchTypeandAssignedby(long userId, String tasktype, String assignedbyUserId)
  {
    logger.info("Inside getCountOfPendingTasksBysearchTypeandAssignedby method");
    int totaluser = 0;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      

      Criteria outer = session.createCriteria(TaskData.class).add(Restrictions.eq("status", "A"));
      if (userId != 0L) {
        outer.add(Restrictions.eq("assignedtoUserId", new Long(userId)));
      }
      if ((!StringUtils.isNullOrEmpty(tasktype)) && (!tasktype.equals("NoValue"))) {
        outer.add(Restrictions.eq("tasktype", tasktype));
      }
      if ((!StringUtils.isNullOrEmpty(assignedbyUserId)) && (!assignedbyUserId.equals("0"))) {
        outer.add(Restrictions.eq("assignedbyUserId", new Long(assignedbyUserId)));
      }
      outer.setProjection(Projections.rowCount());
      totaluser = ((Integer)outer.list().get(0)).intValue();
      System.out.println("totaluser" + totaluser);
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfPendingTasksBysearchTypeandAssignedby()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return totaluser;
  }
  
  public static OrganizationEmailTemplate getOrganizationEmailTemplate(String eventCode)
  {
    logger.info("Inside getOrganizationEmailTemplate method");
    OrganizationEmailTemplate orgemtmpl = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      List lst = session.createCriteria(OrganizationEmailTemplate.class).add(Restrictions.eq("eventCode", eventCode)).list();
      if (lst != null) {
        for (int i = 0; i < lst.size(); i++) {
          orgemtmpl = (OrganizationEmailTemplate)lst.get(i);
        }
      }
      logger.info("email template own org : " + orgemtmpl);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getOrganizationEmailTemplate()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return orgemtmpl;
  }
  
  public static OrganizationEmailTemplate getOrganizationEmailTemplate(long orgid, String eventCode)
  {
    logger.info("Inside getOrganizationEmailTemplate method");
    logger.info(" >> " + orgid);
    logger.info(" >> " + eventCode);
    OrganizationEmailTemplate orgemtmpl = null;
    Session session = null;
    long orgidkeeping = orgid;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      List lst = session.createCriteria(OrganizationEmailTemplate.class).add(Restrictions.eq("eventCode", eventCode)).createAlias("organisation", "organisation").add(Restrictions.eq("organisation.orgId", Long.valueOf(orgid))).list();
      if (lst != null) {
        for (int i = 0; i < lst.size(); i++) {
          orgemtmpl = (OrganizationEmailTemplate)lst.get(i);
        }
      }
      logger.info("email template own org" + orgemtmpl);
      if (orgemtmpl == null)
      {
        boolean istemplfound = false;
        while ((!istemplfound) && (orgid != 0L))
        {
          Organization org = (Organization)session.createCriteria(Organization.class).add(Restrictions.eq("orgId", new Long(orgid))).uniqueResult();
          logger.info("org " + org);
          

          lst = session.createCriteria(OrganizationEmailTemplate.class).add(Restrictions.eq("eventCode", eventCode)).createAlias("organisation", "organisation").add(Restrictions.eq("organisation.orgId", Long.valueOf(org.getParent_org_id()))).list();
          if (lst != null) {
            for (int i = 0; i < lst.size(); i++)
            {
              orgemtmpl = (OrganizationEmailTemplate)lst.get(i);
              istemplfound = true;
            }
          }
          orgid = org.getParent_org_id();
        }
      }
      logger.info("email template" + orgemtmpl);
      if (orgemtmpl == null)
      {
        EmailTemplates emailtemplte = (EmailTemplates)session.createCriteria(EmailTemplates.class).add(Restrictions.eq("defaultcomponent", eventCode)).add(Restrictions.eq("super_user_key", new Long(0L))).uniqueResult();
        
        orgemtmpl = new OrganizationEmailTemplate();
        orgemtmpl.setEmailtemplate(emailtemplte);
        orgemtmpl.setStatus("Y");
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getOrganizationEmailTemplate()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return orgemtmpl;
  }
  
  public List getAllOnBoardingTaskDefiDetails(long super_user_key)
  {
    logger.info("Inside getAllOnBoardingTaskDefiDetails method");
    List OnBoardingTaskDefiList = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      OnBoardingTaskDefiList = session.createCriteria(OnBoardingTaskDefinitions.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key))).list();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllOnBoardingTaskDefiDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return OnBoardingTaskDefiList;
  }
  
  public List getAllOnBoardingTaskDefiDetailsForPagination(long super_user_key, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getAllOnBoardingTaskDefiDetailsForPagination method");
    List OnBoardingTaskDefiList = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      Criteria outer = session.createCriteria(OnBoardingTaskDefinitions.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key)));
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      OnBoardingTaskDefiList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllAccopmlishmentDetailsForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return OnBoardingTaskDefiList;
  }
  
  public static OnBoardingTaskDefinitions SaveOnBoardingTaskDefi(OnBoardingTaskDefinitions OnBoardingTaskDefinitions)
  {
    logger.info("Inside SaveOnBoardingTaskDefi method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.save(OnBoardingTaskDefinitions);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on SaveOnBoardingTaskDefi()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return OnBoardingTaskDefinitions;
  }
  
  public static OnBoardingTaskDefinitions getOnBoardingTaskDefinitionsDetails(String id)
  {
    logger.info("Inside getOnBoardingTaskDefinitionsDetails method");
    OnBoardingTaskDefinitions OnBoardingTaskDefinitions = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      OnBoardingTaskDefinitions = (OnBoardingTaskDefinitions)session.createCriteria(OnBoardingTaskDefinitions.class).add(Restrictions.eq("taskdefid", new Long(id))).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getOnBoardingTaskDefinitionsDetails()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return OnBoardingTaskDefinitions;
  }
  
  public static SystemRuleUser getSystemRuleUserDetails(String id)
  {
    logger.info("Inside getSystemRuleUserDetails method");
    SystemRuleUser user = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      user = (SystemRuleUser)session.createCriteria(SystemRuleUser.class).createAlias("user", "user").add(Restrictions.eq("user.userId", new Long(id))).uniqueResult();
      

      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getSystemRuleUserDetails()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return user;
  }
  
  public static OnBoardingTaskDefinitions updateOnBoardingTaskDefinitions(OnBoardingTaskDefinitions onboardingtaskdefination)
  {
    logger.info("Inside updateOnBoardingTaskDefinitions method");
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.update(onboardingtaskdefination);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on updateOnBoardingTaskDefinitions()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
      System.out.println("update");
    }
    return onboardingtaskdefination;
  }
  
  public static List getOnboardTaskDefiAttributeList(String id)
  {
    logger.info("Inside getOnboardTaskDefiAttributeList method");
    List attributeList = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      


      attributeList = session.createCriteria(OnBoardingTaskAttributes.class).createAlias("taskdefinition", "taskdefinition").add(Restrictions.eq("taskdefinition.taskdefid", new Long(id))).list();
      

      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getOnboardTaskDefiAttributeList()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return attributeList;
  }
  
  public static void saveOnBoardTaskAttributes(List attributeList)
  {
    logger.info("Inside saveOnBoardTaskAttributes method");
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      for (int i = 0; i < attributeList.size(); i++)
      {
        OnBoardingTaskAttributes jbc = (OnBoardingTaskAttributes)attributeList.get(i);
        session.save(jbc);
        System.out.println(attributeList.get(i));
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on saveOnBoardTaskAttributes()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
  }
  
  public static void deleteTaskDefAttributes(OnBoardingTaskDefinitions tasdef)
  {
    logger.info("Inside deleteTaskDefAttributes method");
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      List attrbutelist = session.createCriteria(OnBoardingTaskAttributes.class).createAlias("taskdefinition", "taskdefinition").add(Restrictions.eq("taskdefinition.taskdefid", Long.valueOf(tasdef.getTaskdefid()))).list();
      for (int i = 0; i < attrbutelist.size(); i++)
      {
        OnBoardingTaskAttributes attr = (OnBoardingTaskAttributes)attrbutelist.get(i);
        session.delete(attr);
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteTaskDefAttributes()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
      System.out.println("update");
    }
  }
  
  public static OnBoardingTemplate SaveOnBoardingTemplate(OnBoardingTemplate onboardingTemplate)
  {
    logger.info("Inside SaveOnBoardingTemplate method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.save(onboardingTemplate);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on SaveOnBoardingTemplate()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return onboardingTemplate;
  }
  
  public static SystemRule UpdateSystemRule(SystemRule sysrule, String[] user)
  {
    logger.info("Inside UpdateSystemRule method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.update(sysrule);
      for (int i = 0; i < user.length; i++)
      {
        SystemRuleUser sysu = new SystemRuleUser();
        User u = new User();
        u.setUserId(Long.valueOf(user[i]).longValue());
        sysu.setUser(u);
        sysu.setLevelorder(1);
        sysu.setSystemRuleId(sysrule.getSystemRuleId());
        
        session.save(sysu);
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on UpdateSystemRule()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return sysrule;
  }
  
  public static OnBoardingTemplate getOnBoardingTemplateDetails(String id)
  {
    logger.info("Inside getOnBoardingTemplateDetails method");
    OnBoardingTemplate onboardingTemplate = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      onboardingTemplate = (OnBoardingTemplate)session.createCriteria(OnBoardingTemplate.class).add(Restrictions.eq("templateid", new Long(id))).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getOnBoardingTemplateDetails()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return onboardingTemplate;
  }
  
  public static OnBoardingTemplate updateOnBoardingTemplate(OnBoardingTemplate onboardingtemplate)
  {
    logger.info("Inside updateOnBoardingTemplate method");
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.update(onboardingtemplate);
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on updateOnBoardingTemplate()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
      System.out.println("update");
    }
    return onboardingtemplate;
  }
  
  public static OnBoardingTemplate getOnBoardingTtemplate(String id)
  {
    logger.info("Inside getOnBoardingTaskDefinitionsDetails method");
    OnBoardingTemplate onboardingTemplate = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      onboardingTemplate = (OnBoardingTemplate)session.createCriteria(OnBoardingTemplate.class).add(Restrictions.eq("templateid", new Long(id))).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getOnBoardingTtemplate()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return onboardingTemplate;
  }
  
  public static int getOnboardTemplateDefinationByCriteraCount(long super_user_key, String criteris, long owner, String pname)
  {
    logger.info("Inside getOnboardTemplateDefinationByCriteraCount method");
    int totalonboardtaskdefination = 0;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      if ((!StringUtils.isNullOrEmpty(criteris)) && (owner != 0L) && (!StringUtils.isNullOrEmpty(pname)))
      {
        Criteria crit = session.createCriteria(OnBoardingTaskDefinitions.class).add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key)));
        
        Criterion taskfdefinationname = Restrictions.like("taskName", "%" + criteris + "%");
        Criterion ownername = Restrictions.eq("primaryOwnerId", new Long(owner));
        LogicalExpression orExp = Restrictions.or(taskfdefinationname, ownername);
        crit.add(orExp);
        crit.setProjection(Projections.rowCount());
        totalonboardtaskdefination = ((Integer)crit.list().get(0)).intValue();
      }
      else if ((!StringUtils.isNullOrEmpty(criteris)) && (owner == 0L) && (StringUtils.isNullOrEmpty(pname)))
      {
        Criteria crit = session.createCriteria(OnBoardingTaskDefinitions.class).add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key))).add(Restrictions.like("taskName", "%" + criteris + "%"));
        crit.setProjection(Projections.rowCount());
        totalonboardtaskdefination = ((Integer)crit.list().get(0)).intValue();
      }
      else if ((StringUtils.isNullOrEmpty(criteris)) && (owner != 0L) && (!StringUtils.isNullOrEmpty(pname)))
      {
        Criteria crit2 = session.createCriteria(OnBoardingTaskDefinitions.class).add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key))).add(Restrictions.eq("primaryOwnerId", new Long(owner)));
        

        crit2.setProjection(Projections.rowCount());
        totalonboardtaskdefination = ((Integer)crit2.list().get(0)).intValue();
      }
      else
      {
        Criteria crit3 = session.createCriteria(OnBoardingTaskDefinitions.class).add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key))).add(Restrictions.like("status", "A"));
        

        crit3.setProjection(Projections.rowCount());
        totalonboardtaskdefination = ((Integer)crit3.list().get(0)).intValue();
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getOnboardTemplateDefinationByCriteraCount()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return totalonboardtaskdefination;
  }
  
  public static List getOnboardTemplateDefinationByCritera(long super_user_key, String criteris, long owner, String pname, int start, int range)
  {
    logger.info("Inside getOnboardTemplateDefinationByCritera method");
    List charList = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      if ((!StringUtils.isNullOrEmpty(criteris)) && (owner != 0L) && (!StringUtils.isNullOrEmpty(pname)))
      {
        Criteria crit = session.createCriteria(OnBoardingTaskDefinitions.class).add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key)));
        

        Criterion taskdefname = Restrictions.like("taskName", "%" + criteris + "%");
        Criterion ownername = Restrictions.eq("primaryOwnerId", new Long(owner));
        LogicalExpression orExp = Restrictions.or(taskdefname, ownername);
        crit.add(orExp);
        crit.setFirstResult(start);
        crit.setMaxResults(range);
        charList = crit.list();
      }
      else if ((!StringUtils.isNullOrEmpty(criteris)) && (owner == 0L) && (StringUtils.isNullOrEmpty(pname)))
      {
        Criteria crit1 = session.createCriteria(OnBoardingTaskDefinitions.class).add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key))).add(Restrictions.like("taskName", "%" + criteris + "%"));
        crit1.setFirstResult(start);
        crit1.setMaxResults(range);
        charList = crit1.list();
      }
      else if ((StringUtils.isNullOrEmpty(criteris)) && (owner != 0L) && (!StringUtils.isNullOrEmpty(pname)))
      {
        Criteria crit2 = session.createCriteria(OnBoardingTaskDefinitions.class).add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key))).add(Restrictions.eq("primaryOwnerId", new Long(owner)));
        


        crit2.setFirstResult(start);
        crit2.setMaxResults(range);
        charList = crit2.list();
      }
      else
      {
        Criteria crit3 = session.createCriteria(OnBoardingTaskDefinitions.class).add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key))).add(Restrictions.like("status", "A"));
        
        crit3.setFirstResult(start);
        crit3.setMaxResults(range);
        charList = crit3.list();
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getOnboardTemplateDefinationByCritera()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return charList;
  }
  
  public static List<String> getOnBoardTaskDataByQuery(String query)
  {
    logger.info("Inside getOnBoardTaskDataByQuery method");
    List onboardTaskDefList = new ArrayList();
    List<String> newTaskDefNameList = new ArrayList();
    Session session = null;
    try
    {
      logger.info("query" + query);
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      Criteria outer = session.createCriteria(OnBoardingTaskDefinitions.class).add(Restrictions.eq("status", "A"));
      Criterion f1name = Restrictions.like("taskName", "%" + query + "%");
      Disjunction disjunction = Restrictions.disjunction();
      disjunction.add(f1name);
      outer.add(disjunction);
      onboardTaskDefList = outer.list();
      for (int i = 0; i < onboardTaskDefList.size(); i++)
      {
        OnBoardingTaskDefinitions onboardTaskDef = (OnBoardingTaskDefinitions)onboardTaskDefList.get(i);
        String onboardtaskdefname = onboardTaskDef.getTaskName() + "|" + onboardTaskDef.getTaskdefid();
        newTaskDefNameList.add(onboardtaskdefname);
        logger.info("onboardtaskdefname" + onboardtaskdefname);
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getOnBoardTaskDataByQuery()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return newTaskDefNameList;
  }
  
  public static List getAllOnBoardingTemplateForPagination(long super_user_key, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getAllOnBoardingTemplateForPagination method");
    List OnBoardingTemplateList = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      Criteria outer = session.createCriteria(OnBoardingTemplate.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key)));
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      OnBoardingTemplateList = outer.list();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllOnBoardingTemplateForPagination()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return OnBoardingTemplateList;
  }
  
  public static int getAllOnBoardingTemplateDetails(long super_user_key)
  {
    logger.info("Inside getAllOnBoardingTemplateDetails method");
    List OnBoardingTemplateList = new ArrayList();
    int totaluser = 0;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      Criteria outer = session.createCriteria(OnBoardingTemplate.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key)));
      
      outer.setProjection(Projections.rowCount());
      totaluser = ((Integer)outer.list().get(0)).intValue();
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllOnBoardingTemplateDetails()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return totaluser;
  }
  
  public static List getAllOnBoardingTemplateForPaginationSearch(long super_user_key, String onboardtemname, String primaryownerid, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getAllOnBoardingTemplateForPaginationSearch method");
    List OnBoardingTemplateList = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      if ((!StringUtils.isNullOrEmpty(onboardtemname)) && (!StringUtils.isNullOrEmpty(primaryownerid)) && (!StringUtils.compare(String.valueOf(primaryownerid), "0")))
      {
        Criteria crit = session.createCriteria(OnBoardingTemplate.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key))).createAlias("taskdefinitions", "taskdefinitions");
        
        Criterion tasktempname = Restrictions.like("templateName", "%" + onboardtemname + "%");
        Criterion ownername = Restrictions.eq("taskdefinitions.primaryOwner.userId", new Long(primaryownerid));
        LogicalExpression orExp = Restrictions.or(tasktempname, ownername);
        crit.add(orExp);
        crit.setFirstResult(startIndex);
        crit.setMaxResults(pageSize);
        OnBoardingTemplateList = crit.list();
      }
      else if ((!StringUtils.isNullOrEmpty(onboardtemname)) && (StringUtils.compare(String.valueOf(primaryownerid), "0")))
      {
        Criteria crit1 = session.createCriteria(OnBoardingTemplate.class).add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key))).add(Restrictions.like("templateName", "%" + onboardtemname + "%")).add(Restrictions.eq("status", "A"));
        
        crit1.setFirstResult(startIndex);
        crit1.setMaxResults(pageSize);
        OnBoardingTemplateList = crit1.list();
      }
      else if ((StringUtils.isNullOrEmpty(onboardtemname)) && (!StringUtils.isNullOrEmpty(primaryownerid)) && (!StringUtils.compare(String.valueOf(primaryownerid), "0")))
      {
        Criteria crit3 = session.createCriteria(OnBoardingTemplate.class).add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key))).add(Restrictions.eq("status", "A")).createAlias("taskdefinitions", "taskdefinitions").add(Restrictions.eq("taskdefinitions.primaryOwner.userId", new Long(primaryownerid)));
        

        crit3.setFirstResult(startIndex);
        crit3.setMaxResults(pageSize);
        OnBoardingTemplateList = crit3.list();
      }
      else
      {
        Criteria crit4 = session.createCriteria(OnBoardingTemplate.class).add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key))).add(Restrictions.eq("status", "A"));
        crit4.setFirstResult(startIndex);
        crit4.setMaxResults(pageSize);
        OnBoardingTemplateList = crit4.list();
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllOnBoardingTemplateForPaginationSearch()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    System.out.println(OnBoardingTemplateList.size());
    return OnBoardingTemplateList;
  }
  
  public List getAllOnBoardingTaskDefinationForPaginationSearch(long super_user_key, String taskname, String primaryownerid, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getAllOnBoardingTaskDefinationForPaginationSearch method");
    List OnBoardingTaskList = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      Criteria crit = session.createCriteria(OnBoardingTaskDefinitions.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key)));
      if (!StringUtils.isNullOrEmpty(taskname)) {
        crit.add(Restrictions.like("taskName", "%" + taskname + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(primaryownerid)) && (!primaryownerid.equals("0")))
      {
        crit.createAlias("primaryOwner", "primaryOwner");
        crit.add(Restrictions.eq("primaryOwner.userId", new Long(primaryownerid)));
      }
      crit.setFirstResult(startIndex);
      crit.setMaxResults(pageSize);
      OnBoardingTaskList = crit.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllOnBoardingTemplateForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return OnBoardingTaskList;
  }
  
  public int getAllOnBoardingTaskDetailssearch(long super_user_key, String taskname, String primaryownerid)
  {
    logger.info("Inside getAllOnBoardingTaskDetailssearch method");
    
    int totaluser = 0;
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      Criteria crit = session.createCriteria(OnBoardingTaskDefinitions.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key)));
      if (!StringUtils.isNullOrEmpty(taskname)) {
        crit.add(Restrictions.like("taskName", "%" + taskname + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(primaryownerid)) && (!primaryownerid.equals("0")))
      {
        crit.createAlias("primaryOwner", "primaryOwner");
        crit.add(Restrictions.eq("primaryOwner.userId", new Long(primaryownerid)));
      }
      crit.setProjection(Projections.rowCount());
      totaluser = ((Integer)crit.list().get(0)).intValue();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllOnBoardingTaskDetailssearch()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    System.out.println(totaluser);
    return totaluser;
  }
  
  public static int getAllOnBoardingTemplateDetailssearch(long super_user_key, String onboardtemname, String primaryownerid)
  {
    logger.info("Inside getAllOnBoardingTemplateDetailssearch method");
    
    int totaluser = 0;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      if ((!StringUtils.isNullOrEmpty(onboardtemname)) && (!StringUtils.isNullOrEmpty(primaryownerid)) && (!StringUtils.compare(String.valueOf(primaryownerid), "0")))
      {
        Criteria crit = session.createCriteria(OnBoardingTemplate.class).add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key))).createAlias("taskdefinitions", "taskdefinitions").add(Restrictions.eq("status", "A"));
        
        Criterion tasktempname = Restrictions.like("templateName", "%" + onboardtemname + "%");
        Criterion ownername = Restrictions.eq("taskdefinitions.primaryOwner.userId", new Long(primaryownerid));
        LogicalExpression orExp = Restrictions.or(tasktempname, ownername);
        crit.add(orExp);
        crit.setProjection(Projections.rowCount());
        totaluser = ((Integer)crit.list().get(0)).intValue();
      }
      else if ((!StringUtils.isNullOrEmpty(onboardtemname)) && (StringUtils.compare(String.valueOf(primaryownerid), "0")))
      {
        Criteria crit1 = session.createCriteria(OnBoardingTemplate.class).add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key))).add(Restrictions.like("templateName", "%" + onboardtemname + "%")).add(Restrictions.eq("status", "A"));
        
        crit1.setProjection(Projections.rowCount());
        totaluser = ((Integer)crit1.list().get(0)).intValue();
      }
      else if ((StringUtils.isNullOrEmpty(onboardtemname)) && (!StringUtils.isNullOrEmpty(primaryownerid)) && (!StringUtils.compare(String.valueOf(primaryownerid), "0")))
      {
        Criteria crit3 = session.createCriteria(OnBoardingTemplate.class).add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key))).add(Restrictions.eq("status", "A")).createAlias("taskdefinitions", "taskdefinitions").add(Restrictions.eq("taskdefinitions.primaryOwner.userId", new Long(primaryownerid)));
        

        crit3.setProjection(Projections.rowCount());
        totaluser = ((Integer)crit3.list().get(0)).intValue();
      }
      else
      {
        Criteria crit4 = session.createCriteria(OnBoardingTemplate.class).add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key))).add(Restrictions.eq("status", "A"));
        crit4.setProjection(Projections.rowCount());
        totaluser = ((Integer)crit4.list().get(0)).intValue();
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllOnBoardingTemplateDetailssearch()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    System.out.println(totaluser);
    return totaluser;
  }
}
