package com.dao;

import com.bean.onboard.OnBoardingTask;
import com.bean.onboard.OnBoardingTaskAttributeValues;
import com.bean.onboard.OnBoardingTaskDefinitions;
import com.bean.onboard.Onboarding;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class OnBoardingTXDAO
  extends HibernateDaoSupport
{
  protected static final Logger logger = Logger.getLogger(OnBoardingTXDAO.class);
  
  public void saveOnBoardingTask(OnBoardingTask task)
  {
    logger.info("Inside saveOnBoardingTask method");
    getHibernateTemplate().save(task);
  }
  
  public void updateOnBoardingTask(OnBoardingTask task)
  {
    logger.info("Inside updateOnBoardingTask method");
    getHibernateTemplate().update(task);
  }
  
  public void saveOnBoarding(Onboarding onboard)
  {
    logger.info("Inside saveOnBoarding method");
    getHibernateTemplate().save(onboard);
  }
  
  public void updateOnBoarding(Onboarding onboard)
  {
    logger.info("Inside updateOnBoarding method");
    getHibernateTemplate().update(onboard);
  }
  
  public void saveOnBoardingTaskAttributeValues(OnBoardingTaskAttributeValues taskattributevalues)
  {
    logger.info("Inside saveOnBoardingTaskAttributeValues method");
    getHibernateTemplate().save(taskattributevalues);
  }
  
  public void updateOnBoardingTaskAttributeValues(OnBoardingTaskAttributeValues taskattributevalues)
  {
    logger.info("Inside updateOnBoardingTaskAttributeValues method");
    getHibernateTemplate().update(taskattributevalues);
  }
  
  public OnBoardingTaskDefinitions getOnBoardingTaskDefinitions(long taskdefid)
  {
    logger.info("Inside getOnBoardingTaskDefinitions method");
    OnBoardingTaskDefinitions ontaskdef = null;
    
    ontaskdef = (OnBoardingTaskDefinitions)getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(OnBoardingTaskDefinitions.class).add(Restrictions.eq("taskdefid", Long.valueOf(taskdefid))).uniqueResult();
    
    return ontaskdef;
  }
  
  public Onboarding getOnBoarding(long applicantId, String uuid)
  {
    logger.info("Inside getOnBoarding method");
    Onboarding onboard = null;
    onboard = (Onboarding)getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Onboarding.class).add(Restrictions.eq("applicantId", Long.valueOf(applicantId))).add(Restrictions.eq("uuid", uuid)).uniqueResult();
    return onboard;
  }
  
  public Onboarding getOnBoarding(long onboardingid)
  {
    logger.info("Inside getOnBoarding method");
    Onboarding onboard = null;
    onboard = (Onboarding)getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Onboarding.class).add(Restrictions.eq("onboardingid", Long.valueOf(onboardingid))).uniqueResult();
    return onboard;
  }
  
  public List getOnBoardingTaskList(long onboardingId)
  {
    logger.info("Inside getOnBoardingTaskList method");
    List onBoradTaskList = new ArrayList();
    onBoradTaskList = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(OnBoardingTask.class).add(Restrictions.eq("onboardingid", Long.valueOf(onboardingId))).list();
    return onBoradTaskList;
  }
  
  public OnBoardingTask getOnBoardingTask(long onboardingTaskId, String taskuuid)
  {
    logger.info("Inside getOnBoardingTask method");
    OnBoardingTask task = null;
    task = (OnBoardingTask)getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(OnBoardingTask.class).add(Restrictions.eq("onboardingTaskId", Long.valueOf(onboardingTaskId))).add(Restrictions.eq("taskuuid", taskuuid)).uniqueResult();
    return task;
  }
  
  public void deleteOnboardingTask(OnBoardingTask otask)
  {
    logger.info("Inside deleteOnboardingTask method");
    
    String hql = "delete from OnBoardingTaskAttributeValues where onBoardingTaskId = :onBoardingTaskId";
    Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
    query.setLong("onBoardingTaskId", otask.getOnboardingTaskId());
    int rowCount = query.executeUpdate();
    String hql1 = "delete from OnBoardingTask where onboardingTaskId = :onboardingTaskId and taskuuid=:taskuuid";
    Query query1 = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql1);
    query1.setLong("onboardingTaskId", otask.getOnboardingTaskId());
    query1.setString("taskuuid", otask.getTaskuuid());
    rowCount = query1.executeUpdate();
  }
}
