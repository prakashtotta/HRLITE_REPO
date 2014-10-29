package com.dao;

import com.bean.DashBoardReport;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class ReportTXDAO
  extends HibernateDaoSupport
{
  protected static final Logger logger = Logger.getLogger(ReportTXDAO.class);
  
  public void deleteReportsFromDashboard(long userid)
  {
    logger.info("Inside deleteReportsFromDashboard method");
    String hql = "delete from DashBoardReport where userid = :userid";
    Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
    query.setLong("userid", userid);
    int rowCount = query.executeUpdate();
  }
  
  public void saveDashBoardReport(DashBoardReport grch)
  {
    logger.info("Inside saveDashBoardReport method");
    getHibernateTemplate().save(grch);
  }
}
