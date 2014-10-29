package com.performance.dao;

import com.bean.User;
import com.common.Common;
import com.performance.bean.Kra;
import com.performance.bean.KraKPI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class KRADAO
  extends HibernateDaoSupport
{
  public Map searchKRAListPage(User user, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    Session session = null;
    Map m = new HashMap();
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      Criteria outer = session.createCriteria(Kra.class);
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      List kraList = outer.list();
      m.put(Common.KRA_LIST, kraList);
      

      Criteria outerc = session.createCriteria(Kra.class);
      outerc.setProjection(Projections.rowCount());
      int totaluser = ((Integer)outerc.list().get(0)).intValue();
      
      Integer totalsize = new Integer(totaluser);
      m.put(Common.KRA_COUNT, totalsize);
    }
    catch (Exception e)
    {
      this.logger.error("Exception on getPreviousOrganizationsList()", e);
    }
    finally
    {
      this.logger.info("Inside finally method");
    }
    return m;
  }
  
  public List getAllKRAs()
  {
    this.logger.info("Inside getAllKras method");
    List kList = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      kList = session.createCriteria(Kra.class).list();
    }
    catch (Exception e)
    {
      this.logger.error("Exception on getAllKras()", e);
    }
    finally
    {
      this.logger.info("Inside finally method");
    }
    return kList;
  }
  
  public List<KraKPI> getKPIListByKra(long kraId)
  {
    this.logger.info("Inside getKPIListByKra method");
    List<KraKPI> kList = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      kList = session.createCriteria(KraKPI.class).add(Restrictions.eq("kraId", new Long(kraId))).list();
    }
    catch (Exception e)
    {
      this.logger.error("Exception on getKPIListByKra()", e);
    }
    finally
    {
      this.logger.info("Inside finally method");
    }
    return kList;
  }
  
  public Kra getKRA(long id)
  {
    this.logger.info("Inside getKRA method");
    Kra kra = null;
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      kra = (Kra)session.createCriteria(Kra.class).add(Restrictions.eq("kraId", new Long(id))).uniqueResult();
    }
    catch (Exception e)
    {
      this.logger.error("Exception on getKRA()", e);
    }
    finally
    {
      this.logger.info("Inside finally method");
    }
    return kra;
  }
  
  public Kra saveKra(Kra kra)
  {
    this.logger.info("Inside saveKra method");
    
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      session.save(kra);
    }
    catch (Exception e)
    {
      this.logger.error("Exception on saveKra()", e);
    }
    finally
    {
      this.logger.info("Inside finally method");
    }
    return kra;
  }
  
  public void saveKraKpi(List<KraKPI> kkpiList, long kraId)
  {
    this.logger.info("Inside saveKraKpi method");
    
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      for (int i = 0; i < kkpiList.size(); i++)
      {
        KraKPI krakpi = (KraKPI)kkpiList.get(i);
        krakpi.setKraId(kraId);
        session.save(krakpi);
      }
    }
    catch (Exception e)
    {
      this.logger.error("Exception on saveKraKpi()", e);
    }
    finally
    {
      this.logger.info("Inside finally method");
    }
  }
}
