package com.dao;

import com.bean.Organization;
import com.bean.OrganizationEmailTemplate;
import com.bean.TaskData;
import com.bean.User;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class TaskTXDAO
  extends HibernateDaoSupport
{
  protected static final Logger logger = Logger.getLogger(TaskTXDAO.class);
  
  public void saveTask(TaskData task)
  {
    logger.info("Inside saveTask method");
    getHibernateTemplate().save(task);
  }
  
  public void saveAllTask(List<TaskData> taskList)
  {
    logger.info("Inside saveAllTask method");
    getHibernateTemplate().saveOrUpdateAll(taskList);
  }
  
  public void updateTask(TaskData task)
  {
    logger.info("Inside updateTask method");
    getHibernateTemplate().update(task);
  }
  
  public void markCompleteTask(TaskData task, String updatedBy)
  {
    logger.info("Inside markCompleteTask method");
    task.setUpdatedBy(updatedBy);
    task.setUpdatedDate(new Date());
    task.setStatus("C");
    getHibernateTemplate().update(task);
  }
  
  public void markTaskActive(TaskData task)
  {
    logger.info("Inside markTaskActive method");
    task.setUpdatedBy(null);
    task.setUpdatedDate(null);
    task.setStatus("A");
    getHibernateTemplate().update(task);
  }
  
  public TaskData getTask(long idvalue, String taskType)
  {
    logger.info("Inside getTask method");
    TaskData task = null;
    List tasklist = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(TaskData.class).add(Restrictions.eq("idvalue", new Long(idvalue))).add(Restrictions.eq("tasktype", taskType)).add(Restrictions.eq("status", "A")).list();
    if ((tasklist != null) && (tasklist.size() > 0)) {
      task = (TaskData)tasklist.get(0);
    }
    return task;
  }
  
  public TaskData getMyTask(long idvalue, String taskType, long userId)
  {
    logger.info("Inside getTask method");
    TaskData task = null;
    
    List tasklist = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(TaskData.class).add(Restrictions.eq("idvalue", new Long(idvalue))).add(Restrictions.eq("tasktype", taskType)).add(Restrictions.eq("status", "A")).add(Restrictions.eq("assignedtoUserId", Long.valueOf(userId))).list();
    if ((tasklist != null) && (tasklist.size() > 0)) {
      task = (TaskData)tasklist.get(0);
    }
    return task;
  }
  
  public TaskData getLastCompleteTask(long idvalue, String taskType)
  {
    logger.info("Inside getTask method");
    TaskData task = null;
    
    List tasklist = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(TaskData.class).add(Restrictions.eq("idvalue", new Long(idvalue))).add(Restrictions.eq("tasktype", taskType)).add(Restrictions.eq("status", "C")).addOrder(Order.desc("taskId")).list();
    if ((tasklist != null) && (tasklist.size() > 0)) {
      task = (TaskData)tasklist.get(0);
    }
    return task;
  }
  
  public TaskData getLastCompleteTask(long idvalue, List<String> taskTypes)
  {
    logger.info("Inside getTask method");
    TaskData task = null;
    
    List tasklist = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(TaskData.class).add(Restrictions.eq("idvalue", new Long(idvalue))).add(Restrictions.in("tasktype", taskTypes)).add(Restrictions.eq("status", "C")).addOrder(Order.desc("taskId")).list();
    if ((tasklist != null) && (tasklist.size() > 0)) {
      task = (TaskData)tasklist.get(0);
    }
    return task;
  }
  
  public User getOfferOwnerDetails(long ownertId)
  {
    logger.info("Inside getTask method");
    User usr = null;
    
    usr = (User)getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(User.class).add(Restrictions.eq("userId", new Long(ownertId))).uniqueResult();
    
    return usr;
  }
  
  public void deleteTaskTx(long taskid, String tasktype)
    throws Exception
  {
    try
    {
      logger.info("Inside deleteTask method");
      String hql = "delete from TaskData where idvalue = :idvalue and tasktype=:tasktype";
      Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
      query.setLong("idvalue", taskid);
      query.setString("tasktype", tasktype);
      int rowCount = query.executeUpdate();
      logger.info("Inside deleteTask method end");
    }
    catch (Exception e)
    {
      logger.error("Exception on markcompleteforgroup()", e);
      throw e;
    }
  }
  
  public void deleteTask(long taskid, String tasktype)
  {
    logger.info("Inside deleteTask method");
    String hql = "delete from TaskData where idvalue = :idvalue and tasktype=:tasktype";
    Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
    query.setLong("idvalue", taskid);
    query.setString("tasktype", tasktype);
    int rowCount = query.executeUpdate();
    logger.info("Inside deleteTask method end");
  }
  
  public void markTaskComplete(long idvalue, List<String> taskTypes, String updatedby)
  {
    logger.info("Inside markTaskComplete method");
    TaskData task = null;
    

    String queryString = "update TaskData set status = :status , updatedBy = :updatedBy, updatedDate = :updatedDate where idvalue = :idvalue and tasktype IN (:taskTypes)";
    
    Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(queryString);
    query.setString("status", "C");
    query.setString("updatedBy", updatedby);
    query.setDate("updatedDate", new Date());
    query.setLong("idvalue", idvalue);
    query.setParameterList("taskTypes", taskTypes);
    int rowCount = query.executeUpdate();
    
    logger.info("Inside markTaskComplete method end");
  }
  
  public void markTaskComplete(long idvalue, String uuid, String updatedby)
  {
    logger.info("Inside markTaskComplete method" + idvalue + " uuid:" + uuid);
    TaskData task = null;
    

    String queryString = "update TaskData set status = :status , updatedBy = :updatedBy, updatedDate = :updatedDate where idvalue = :idvalue and uuid = :uuid";
    
    Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(queryString);
    query.setString("status", "C");
    query.setString("updatedBy", updatedby);
    query.setDate("updatedDate", new Date());
    query.setLong("idvalue", idvalue);
    query.setString("uuid", uuid);
    
    int rowCount = query.executeUpdate();
    
    logger.info("Inside markTaskComplete method end");
  }
  
  public void markcompleteforgroup(long idvalue, String tasktype, String updatedby)
  {
    logger.info("Inside markcompleteforgroup method");
    String hql = "update TaskData set status = :status , updatedBy = :updatedBy, updatedDate = :updatedDate where idvalue = :idvalue and tasktype=:tasktype";
    Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
    query.setString("status", "C");
    query.setString("updatedBy", updatedby);
    query.setDate("updatedDate", new Date());
    query.setLong("idvalue", idvalue);
    query.setString("tasktype", tasktype);
    int rowCount = query.executeUpdate();
    logger.info("Inside markcompleteforgroup method end");
  }
  
  public void markCompleteForGroup(long idvalue, String tasktype, String updatedby)
    throws Exception
  {
    try
    {
      logger.info("Inside markCompleteForGroup method");
      String hql = "update TaskData set status = :status , updatedBy = :updatedBy, updatedDate = :updatedDate where idvalue = :idvalue and tasktype=:tasktype";
      Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
      query.setString("status", "C");
      query.setString("updatedBy", updatedby);
      query.setDate("updatedDate", new Date());
      query.setLong("idvalue", idvalue);
      query.setString("tasktype", tasktype);
      int rowCount = query.executeUpdate();
      logger.info("Inside markCompleteForGroup method end");
    }
    catch (Exception e)
    {
      logger.error("Exception on markcompleteforgroup()", e);
      throw e;
    }
  }
  
  public OrganizationEmailTemplate getOrganizationEmailTemplate(long orgid, String eventCode)
  {
    logger.info("Inside getOrganizationEmailTemplate method");
    OrganizationEmailTemplate orgemtmpl = null;
    try
    {
      List lst = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(OrganizationEmailTemplate.class).add(Restrictions.eq("eventCode", eventCode)).createAlias("organisation", "organisation").add(Restrictions.eq("organisation.orgId", Long.valueOf(orgid))).list();
      if (lst != null) {
        for (int i = 0; i < lst.size(); i++) {
          orgemtmpl = (OrganizationEmailTemplate)lst.get(i);
        }
      }
      logger.info("email template own org" + orgemtmpl);
      if (orgemtmpl == null)
      {
        boolean istemplfound = false;
        while (!istemplfound)
        {
          Organization org = (Organization)getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Organization.class).add(Restrictions.eq("orgId", new Long(orgid))).uniqueResult();
          

          lst = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(OrganizationEmailTemplate.class).add(Restrictions.eq("eventCode", eventCode)).createAlias("organisation", "organisation").add(Restrictions.eq("organisation.orgId", Long.valueOf(org.getParent_org_id()))).list();
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
    }
    catch (Exception e)
    {
      logger.error("Exception on getOrganizationEmailTemplate()", e);
    }
    return orgemtmpl;
  }
}
