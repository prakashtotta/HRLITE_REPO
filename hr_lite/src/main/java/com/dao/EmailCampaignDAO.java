package com.dao;

import com.bean.User;
import com.bean.pool.EmailCampaign;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class EmailCampaignDAO
  extends HibernateDaoSupport
{
  protected static final Logger logger = Logger.getLogger(EmailCampaignDAO.class);
  
  public List getAllEmailCampaign()
  {
    logger.info("Inside getAllEmailCampaign method");
    List emList = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      emList = session.createCriteria(EmailCampaign.class).add(Restrictions.eq("status", "A")).list();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllEmailCampaign()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return emList;
  }
  
  public List getAllEmailCampaignForPagination(User user, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getAllEmailCampaignForPagination method");
    List emailcampaignList = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      Criteria query = session.createCriteria(EmailCampaign.class).add(Restrictions.eq("status", "A"));
      
      query.add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      if (dir_str.equalsIgnoreCase("asc")) {
        query.addOrder(Order.asc(sort_str));
      } else {
        query.addOrder(Order.desc(sort_str));
      }
      query = query.setFirstResult(startIndex);
      query.setMaxResults(pageSize);
      emailcampaignList = query.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllEmailCampaignForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return emailcampaignList;
  }
  
  public List getAllEmailCampaignDetails(User user)
  {
    logger.info("Inside getAllEmailCampaignDetails method");
    List emailcampaignList = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      emailcampaignList = session.createCriteria(EmailCampaign.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key()))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllEmailCampaignDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return emailcampaignList;
  }
  
  public EmailCampaign getEmailCampaignDetails(long emailCampaignId)
  {
    logger.info("Inside saveEmailCampaign method");
    EmailCampaign emailCampaign = null;
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      emailCampaign = (EmailCampaign)session.createCriteria(EmailCampaign.class).add(Restrictions.eq("emailCampaignId", Long.valueOf(emailCampaignId))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on saveEmailCampaignsaveEmailCampaign()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return emailCampaign;
  }
  
  public EmailCampaign saveEmailCampaign(EmailCampaign emailCampaign)
  {
    logger.info("Inside saveEmailCampaign method");
    
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.save(emailCampaign);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveEmailCampaignsaveEmailCampaign()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return emailCampaign;
  }
  
  public EmailCampaign updateEmailCampaign(EmailCampaign emailCampaign)
  {
    logger.info("Inside updateEmailCampaign method");
    
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.update(emailCampaign);
    }
    catch (Exception e)
    {
      logger.error("Exception on updateEmailCampaign()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return emailCampaign;
  }
  
  public EmailCampaign deleteEmailCampaign(EmailCampaign emailCampaign)
  {
    logger.info("Inside deleteEmailCampaign method");
    
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.delete(emailCampaign);
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteEmailCampaign()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return emailCampaign;
  }
}
