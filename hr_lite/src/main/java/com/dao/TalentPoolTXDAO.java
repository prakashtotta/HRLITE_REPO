package com.dao;

import com.bean.Organization;
import com.bean.pool.TalentPool;
import com.bean.pool.TalentPoolElements;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class TalentPoolTXDAO
  extends HibernateDaoSupport
{
  protected static final Logger logger = Logger.getLogger(TalentPoolTXDAO.class);
  
  public TalentPool getTalentPoolByApplicantId(long applicantId)
  {
    logger.info("Inside getTalentPoolByApplicantId method");
    TalentPool tp = null;
    List tpList = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      String hqlquery = "select distinct t from TalentPool t , TalentPoolElements te where t.talentPoolId=te.applicantpoolidInitial and te.applicantId = :applicantId ";
      
      Query query = session.createQuery(hqlquery);
      
      query.setLong("applicantId", new Long(applicantId).longValue());
      
      logger.info(query.getQueryString());
      
      tpList = query.list();
      if ((tpList != null) && (tpList.size() > 0)) {
        tp = (TalentPool)tpList.get(0);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getTalentPoolByApplicantId()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return tp;
  }
  
  public TalentPoolElements getTalentPoolElementByApplicantId(long applicantId)
  {
    logger.info("Inside getTalentPoolElementByApplicantId method");
    TalentPoolElements te = null;
    List tpList = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      tpList = session.createCriteria(TalentPoolElements.class).add(Restrictions.eq("applicantId", Long.valueOf(applicantId))).list();
      if ((tpList != null) && (tpList.size() > 0)) {
        te = (TalentPoolElements)tpList.get(0);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getTalentPoolElementByApplicantId()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return te;
  }
  
  public TalentPool getTalentpoolByOrganization(long orgId)
  {
    logger.info("Inside getTalentpoolByOrganization method");
    List talentpoolList = new ArrayList();
    Session session = null;
    TalentPool tpool = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      talentpoolList = session.createCriteria(TalentPool.class).add(Restrictions.eq("organization.orgId", Long.valueOf(orgId))).add(Restrictions.eq("status", "A")).list();
      if ((talentpoolList != null) && (talentpoolList.size() > 0)) {
        tpool = (TalentPool)talentpoolList.get(0);
      }
      if (tpool == null)
      {
        boolean isTalentPoolNotFound = false;
        while (!isTalentPoolNotFound)
        {
          Organization org = (Organization)session.createCriteria(Organization.class).add(Restrictions.eq("orgId", Long.valueOf(orgId))).add(Restrictions.eq("status", "A")).uniqueResult();
          
          talentpoolList = session.createCriteria(TalentPool.class).add(Restrictions.eq("organization.orgId", Long.valueOf(org.getParent_org_id()))).add(Restrictions.eq("status", "A")).list();
          if ((talentpoolList != null) && (talentpoolList.size() > 0))
          {
            tpool = (TalentPool)talentpoolList.get(0);
            isTalentPoolNotFound = true;
          }
          orgId = org.getParent_org_id();
        }
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getTalentpoolByOrganization()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return tpool;
  }
  
  public void updateTalentPoolElement(TalentPoolElements te)
  {
    logger.info("Inside updateTalentPoolElement method");
    getHibernateTemplate().update(te);
  }
  
  public TalentPoolElements getTalentPoolElementBySkill(String name, long talentPoolId)
  {
    TalentPoolElements tpool = null;
    Session session = null;
    
    session = getHibernateTemplate().getSessionFactory().getCurrentSession();
    List telemtlist = session.createCriteria(TalentPoolElements.class).add(Restrictions.eq("name", name)).add(Restrictions.eq("slave", Integer.valueOf(new Integer(0).intValue()))).add(Restrictions.eq("applicantId", Long.valueOf(new Long(0L).longValue()))).add(Restrictions.eq("applicantpoolidInitial", Long.valueOf(talentPoolId))).list();
    if ((telemtlist != null) && (telemtlist.size() > 0)) {
      tpool = (TalentPoolElements)telemtlist.get(0);
    }
    return tpool;
  }
  
  public TalentPoolElements insertTalentPoolElement(long ownerId, String name, int slave, long applicantId, long applicantpoolidInitial)
  {
    logger.info("Inside insertTalentPoolElement method");
    TalentPoolElements tpool = null;
    Session session = null;
    
    session = getHibernateTemplate().getSessionFactory().getCurrentSession();
    

    String sql = "SELECT ifnull(max(el.position)+1, 0) FROM talent_pool_elements el WHERE el.ownerEl = :ownerEl and el.name <> :name";
    


    Query querymax = session.createSQLQuery(sql);
    querymax.setLong("ownerEl", new Long(ownerId).longValue());
    querymax.setString("name", "donot delete or move");
    Object obj = querymax.uniqueResult();
    
    BigInteger max = (BigInteger)obj;
    
    tpool = new TalentPoolElements();
    tpool.setName(name);
    tpool.setOwnerId(ownerId);
    tpool.setPosition(max.longValue());
    tpool.setSlave(slave);
    tpool.setApplicantId(applicantId);
    tpool.setApplicantpoolidInitial(applicantpoolidInitial);
    session.save(tpool);
    


    return tpool;
  }
  
  public void deleteTalentPoolElements(String elementId, int index, String ownerEl)
  {
    logger.info("Inside deleteTalentPoolElements method");
    
    Session session = null;
    Connection connection = null;
    PreparedStatement ps = null;
    List idList = new ArrayList();
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      connection = session.connection();
      
      List telemtlist = session.createCriteria(TalentPoolElements.class).add(Restrictions.eq("ownerId", Long.valueOf(new Long(elementId).longValue()))).list();
      index++;
      if (telemtlist != null) {
        for (int i = 0; i < telemtlist.size(); i++)
        {
          TalentPoolElements tp = (TalentPoolElements)telemtlist.get(i);
          if (tp.getSlave() == 0)
          {
            String sql1 = "delete from talent_pool_elements WHERE ownerEl = ? OR Id = ?";
            ps = connection.prepareStatement(sql1);
            ps.setLong(1, new Long(elementId).longValue());
            ps.setLong(2, new Long(elementId).longValue());
            ps.execute();
            connection.commit();
            HibernateUtil.closeSession();
            deleteTalentPoolElements(String.valueOf(tp.getId()), index, ownerEl);
          }
        }
      }
      if (index == 0)
      {
        TalentPoolElements tpn = (TalentPoolElements)session.createCriteria(TalentPoolElements.class).add(Restrictions.eq("id", Long.valueOf(new Long(elementId).longValue()))).uniqueResult();
        if (tpn != null)
        {
          String sql1 = "UPDATE talent_pool_elements SET  position = position - 1 WHERE ownerEl = ? AND position > ?";
          
          ps = connection.prepareStatement(sql1);
          ps.setLong(1, tpn.getOwnerId());
          ps.setLong(2, tpn.getPosition());
          ps.executeUpdate();
        }
      }
      String sql1 = "delete from talent_pool_elements WHERE ownerEl = ? OR Id = ?";
      ps = connection.prepareStatement(sql1);
      ps.setLong(1, new Long(elementId).longValue());
      ps.setLong(2, new Long(elementId).longValue());
      ps.execute();
      


      connection.commit();
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteTalentPoolElements()", e);
    }
    finally
    {
      try
      {
        if (ps != null) {
          ps.close();
        }
        if (connection != null) {
          connection.close();
        }
      }
      catch (SQLException e) {}
      logger.info("Inside finally method");
    }
  }
  
  public TalentPool getTalentpoolDetails(long id)
  {
    logger.info("Inside getTalentpoolDetails method");
    TalentPool talent = null;
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      talent = (TalentPool)session.createCriteria(TalentPool.class).add(Restrictions.eq("talentPoolId", Long.valueOf(id))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getTalentpoolDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return talent;
  }
}
