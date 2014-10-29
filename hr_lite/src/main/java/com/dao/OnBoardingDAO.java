package com.dao;

import com.bean.onboard.OnBoardingTask;
import com.bean.onboard.OnBoardingTaskDefinitions;
import com.bean.onboard.OnBoardingTemplate;
import com.bean.onboard.Onboarding;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class OnBoardingDAO
{
  protected static final Logger logger = Logger.getLogger(OnBoardingDAO.class);
  
  public static List getOnBoardingTemplateList(long super_user_key)
  {
    logger.info("Inside getOnBoardingTemplateList method");
    List tmpllist = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      tmpllist = session.createCriteria(OnBoardingTemplate.class).add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key))).add(Restrictions.eq("status", "A")).list();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getOnBoardingTemplateList()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return tmpllist;
  }
  
  public static List getOnBoardingTaskList(long onboardingId)
  {
    logger.info("Inside getOnBoardingTaskList method");
    List onBoradTaskList = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      onBoradTaskList = session.createCriteria(OnBoardingTask.class).add(Restrictions.eq("onboardingid", Long.valueOf(onboardingId))).list();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getOnBoardingTaskList()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return onBoradTaskList;
  }
  
  public static OnBoardingTemplate getOnBoardingTemplate(long templateid)
  {
    logger.info("Inside getOnBoardingTemplate method");
    OnBoardingTemplate ontmpl = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      ontmpl = (OnBoardingTemplate)session.createCriteria(OnBoardingTemplate.class).add(Restrictions.eq("templateid", Long.valueOf(templateid))).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getOnBoardingTemplate()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return ontmpl;
  }
  
  public static OnBoardingTaskDefinitions getOnBoardingTaskDefinitions(long taskdefid)
  {
    logger.info("Inside getOnBoardingTaskDefinitions method");
    OnBoardingTaskDefinitions taskdef = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      taskdef = (OnBoardingTaskDefinitions)session.createCriteria(OnBoardingTaskDefinitions.class).add(Restrictions.eq("taskdefid", Long.valueOf(taskdefid))).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getOnBoardingTaskDefinitions()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return taskdef;
  }
  
  public static Onboarding getOnBoarding(long applicantId, String uuid)
  {
    logger.info("Inside getOnBoarding method");
    Onboarding onboard = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      onboard = (Onboarding)session.createCriteria(Onboarding.class).add(Restrictions.eq("applicantId", Long.valueOf(applicantId))).add(Restrictions.eq("uuid", uuid)).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getOnBoarding()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return onboard;
  }
  
  public static OnBoardingTask getOnBoardingTask(long onboardingTaskId, String taskuuid)
  {
    logger.info("Inside getOnBoardingTask method");
    OnBoardingTask task = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      task = (OnBoardingTask)session.createCriteria(OnBoardingTask.class).add(Restrictions.eq("onboardingTaskId", Long.valueOf(onboardingTaskId))).add(Restrictions.eq("taskuuid", taskuuid)).uniqueResult();
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getOnBoardingTask()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return task;
  }
}
