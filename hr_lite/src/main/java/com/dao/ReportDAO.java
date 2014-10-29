package com.dao;

import com.bean.DashBoardReport;
import com.bean.ReportNames;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class ReportDAO
{
  protected static final Logger logger = Logger.getLogger(ReportDAO.class);
  
  public static List getAllDashBoardReportByUserId(long userId)
  {
    logger.info("Inside getAllDashBoardReportByUserId method");
    List reportList = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      reportList = session.createCriteria(DashBoardReport.class).add(Restrictions.eq("userid", new Long(userId))).list();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllDashBoardReportByUserId()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return reportList;
  }
  
  public static List getAllReports()
  {
    logger.info("Inside getAllReports method");
    List reportList = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      reportList = session.createCriteria(ReportNames.class).list();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllReports()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return reportList;
  }
}
