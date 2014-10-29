package com.dao;

import com.bean.Round;
import com.bean.SelectionCycle;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class SelectionDAO
{
  protected static final Logger logger = Logger.getLogger(SelectionDAO.class);
  
  public static List getSelectionsListForPagination(int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getSelectionsListForPagination method");
    List selList = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      





      Criteria outer = session.createCriteria(SelectionCycle.class);
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      selList = outer.list();
      



      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getSelectionsListForPagination()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return selList;
  }
  
  public static List getAllSelectionsList()
  {
    logger.info("Inside getAllSelectionsList method");
    List selList = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      selList = session.createCriteria(SelectionCycle.class).list();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllSelectionsList()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return selList;
  }
  
  public static SelectionCycle saveSelection(SelectionCycle sel)
  {
    logger.info("Inside saveSelection method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.saveOrUpdate(sel);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on saveSelection()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return sel;
  }
  
  public static SelectionCycle getSeletion(String id)
  {
    logger.info("Inside getSeletion method");
    SelectionCycle cy = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      


      cy = (SelectionCycle)session.createCriteria(SelectionCycle.class).add(Restrictions.eq("selId", new Long(id))).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getRole()", e);
      session.getTransaction().rollback();
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return cy;
  }
  
  public static void saveRoundToSelection(long selid, long roundid)
  {
    logger.info("Inside getSeletion method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      SelectionCycle cy = (SelectionCycle)session.load(SelectionCycle.class, Long.valueOf(selid));
      Round rn = (Round)session.load(Round.class, Long.valueOf(roundid));
      
      cy.getRounds().add(rn);
      
      session.saveOrUpdate(cy);
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getRole()", e);
      session.getTransaction().rollback();
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
}
