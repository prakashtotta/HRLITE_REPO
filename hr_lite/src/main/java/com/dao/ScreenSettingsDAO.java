package com.dao;

import com.bean.screensetting.ApplicantScreenSettings;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class ScreenSettingsDAO
{
  protected static final Logger logger = Logger.getLogger(ScreenSettingsDAO.class);
  
  public static ApplicantScreenSettings getApplicationScreenSettings(long userId, String screenName)
  {
    logger.info("Inside getApplicationSettings method");
    ApplicantScreenSettings appsetting = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      appsetting = (ApplicantScreenSettings)session.createCriteria(ApplicantScreenSettings.class).add(Restrictions.eq("userId", Long.valueOf(userId))).add(Restrictions.eq("screenName", screenName)).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicationSettings()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    logger.info("appsetting123" + appsetting);
    return appsetting;
  }
  
  public static ApplicantScreenSettings updateApplicantScreenSettings(ApplicantScreenSettings appsetting)
  {
    logger.info("Inside updateApplicantScreenSettings method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      session.saveOrUpdate(appsetting);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on updateApplicantScreenSettings()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return appsetting;
  }
}
