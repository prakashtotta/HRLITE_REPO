package com.dao;

import com.bean.User;
import com.bean.pool.TalentPool;
import com.bean.pool.TalentPoolElements;
import com.util.StringUtils;
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
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class TalentPoolDAO
{
  protected static final Logger logger = Logger.getLogger(TalentPoolDAO.class);
  
  public static TalentPool getDefaultTalentPools(long super_user_key)
  {
    logger.info("Inside getDefaultTalentPools method");
    List talentPoolList = new ArrayList();
    TalentPool tp = new TalentPool();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      talentPoolList = session.createCriteria(TalentPool.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key))).addOrder(Order.asc("createdDate")).list();
      if ((talentPoolList != null) && (talentPoolList.size() > 0)) {
        tp = (TalentPool)talentPoolList.get(0);
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getDefaultTalentPools()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return tp;
  }
  
  public static List getAllTalentPools(long super_user_key)
  {
    logger.info("Inside getAllTalentPools method");
    List talentPoolList = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      talentPoolList = session.createCriteria(TalentPool.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key))).list();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllTalentPools()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return talentPoolList;
  }
  
  public static List getAllTalentPoolsAssignedToUser(long userId)
  {
    logger.info("Inside getAllTalentPoolsAssignedToUser method");
    List talentPoolList = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      String hqlquery = "select distinct j from TalentPool j , UserGroup ug where j.status = 'A' and ((j.assignedtoid = :currentuserid and j.isGroup <> 'Y') or  (j.assignedtoid = ug.usergrpId and  j.isGroup='Y' and ug.users.userId =:currentuserid)) ";
      

      Query query = session.createQuery(hqlquery);
      
      query.setLong("currentuserid", new Long(userId).longValue());
      
      logger.info(query.getQueryString());
      
      talentPoolList = query.list();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllTalentPoolsAssignedToUser()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return talentPoolList;
  }
  
  public static List getTalentPoolElementsByOwnerId(long ownerId)
  {
    logger.info("Inside getTalentPoolElementsByOwnerId method");
    List talentPoolList = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      talentPoolList = session.createCriteria(TalentPoolElements.class).add(Restrictions.eq("ownerId", Long.valueOf(ownerId))).addOrder(Order.desc("position")).list();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getTalentPoolElementsByOwnerId()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return talentPoolList;
  }
  
  public static TalentPoolElements getTalentPoolElement(long id)
  {
    logger.info("Inside getTalentPoolElement method");
    TalentPoolElements te = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      te = (TalentPoolElements)session.createCriteria(TalentPoolElements.class).add(Restrictions.eq("id", Long.valueOf(id))).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getTalentPoolElement()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return te;
  }
  
  public static TalentPoolElements getTalentPoolElementByApplicantId(long applicantId)
  {
    logger.info("Inside getTalentPoolElementByApplicantId method");
    TalentPoolElements te = null;
    List tpList = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      tpList = session.createCriteria(TalentPoolElements.class).add(Restrictions.eq("applicantId", Long.valueOf(applicantId))).list();
      if ((tpList != null) && (tpList.size() > 0)) {
        te = (TalentPoolElements)tpList.get(0);
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getTalentPoolElementByApplicantId()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return te;
  }
  
  public static TalentPool getTalentPoolByApplicantId(long applicantId)
  {
    logger.info("Inside getTalentPoolByApplicantId method");
    TalentPool tp = null;
    List tpList = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      String hqlquery = "select distinct t from TalentPool t , TalentPoolElements te where t.talentPoolId=te.applicantpoolidInitial and te.applicantId = :applicantId ";
      
      Query query = session.createQuery(hqlquery);
      
      query.setLong("applicantId", new Long(applicantId).longValue());
      
      logger.info(query.getQueryString());
      
      tpList = query.list();
      if ((tpList != null) && (tpList.size() > 0)) {
        tp = (TalentPool)tpList.get(0);
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getTalentPoolByApplicantId()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return tp;
  }
  
  public static boolean isApplicantFromAssignedTalentPool(long userId, long applicantId)
  {
    logger.info("Inside isApplicantFromAssignedTalentPool method");
    TalentPool tp = null;
    List tpList = new ArrayList();
    Session session = null;
    boolean success = false;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      String hqlquery = "select distinct t from TalentPool t , TalentPoolElements te , UserGroup ug where t.talentPoolId=te.applicantpoolidInitial and te.applicantId = :applicantId  and ((t.assignedtoid = :currentuserid and t.isGroup <> 'Y') or  (t.assignedtoid = ug.usergrpId and  t.isGroup='Y' and ug.users.userId =:currentuserid))";
      



      Query query = session.createQuery(hqlquery);
      
      query.setLong("applicantId", new Long(applicantId).longValue());
      query.setLong("currentuserid", new Long(userId).longValue());
      
      logger.info(query.getQueryString());
      
      tpList = query.list();
      if ((tpList != null) && (tpList.size() > 0)) {
        success = true;
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on isApplicantFromAssignedTalentPool()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return success;
  }
  
  public static void updateTalentPoolElement(String name, String elementId, String ownerId)
  {
    logger.info("Inside updateTalentPoolElement method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      TalentPoolElements tt = (TalentPoolElements)session.createCriteria(TalentPoolElements.class).add(Restrictions.eq("id", Long.valueOf(new Long(elementId).longValue()))).uniqueResult();
      tt.setName(name);
      session.update(tt);
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on updateTalentPoolElement()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
  }
  
  public static TalentPoolElements insertTalentPoolElement(String ownerId, String name, String slave)
  {
    logger.info("Inside insertTalentPoolElement method");
    TalentPoolElements tpool = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      String sql = "SELECT ifnull(max(el.position)+1, 0) FROM talent_pool_elements el WHERE el.ownerEl = :ownerEl and el.name <> :name";
      

      Query querymax = session.createSQLQuery(sql);
      querymax.setLong("ownerEl", new Long(ownerId).longValue());
      querymax.setString("name", "donot delete or move");
      Object obj = querymax.uniqueResult();
      
      BigInteger max = (BigInteger)obj;
      
      tpool = new TalentPoolElements();
      tpool.setName(name);
      tpool.setOwnerId(new Long(ownerId).longValue());
      tpool.setPosition(max.longValue());
      tpool.setSlave(new Integer(slave).intValue());
      session.save(tpool);
      

      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on insertTalentPoolElement()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return tpool;
  }
  
  public static TalentPoolElements insertTalentPoolElement(long ownerId, String name, int slave, long applicantId, long applicantpoolidInitial)
  {
    logger.info("Inside insertTalentPoolElement method");
    TalentPoolElements tpool = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      

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
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on insertTalentPoolElement()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return tpool;
  }
  
  public static void deleteTalentPoolElements(String elementId, int index, String ownerEl)
  {
    logger.info("Inside deleteTalentPoolElements method");
    logger.info("Inside deleteTalentPoolElements method");
    Session session = null;
    Connection connection = null;
    PreparedStatement ps = null;
    List idList = new ArrayList();
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      connection = session.connection();
      
      List telemtlist = session.createCriteria(TalentPoolElements.class).add(Restrictions.eq("ownerId", Long.valueOf(new Long(elementId).longValue()))).list();
      index++;
      if (telemtlist != null) {
        for (int i = 0; i < telemtlist.size(); i++)
        {
          TalentPoolElements tp = (TalentPoolElements)telemtlist.get(i);
          if (tp.getSlave() == 0)
          {
            String sql1 = "update job_applications set status = 'D' , interview_state = 'Mark deleted-Talent Pool' where application_id in ( select applicantId from talent_pool_elements WHERE ownerEl = ? OR Id = ?)";
            
            ps = connection.prepareStatement(sql1);
            ps.setLong(1, new Long(elementId).longValue());
            ps.setLong(2, new Long(elementId).longValue());
            ps.executeUpdate();
            
            sql1 = "delete from talent_pool_elements WHERE ownerEl = ? OR Id = ?";
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
      String sql1 = "update job_applications set status = 'D' , interview_state = 'Mark deleted-Talent Pool' where application_id in ( select applicantId from talent_pool_elements WHERE ownerEl = ? OR Id = ?)";
      
      ps = connection.prepareStatement(sql1);
      ps.setLong(1, new Long(elementId).longValue());
      ps.setLong(2, new Long(elementId).longValue());
      ps.executeUpdate();
      
      sql1 = "delete from talent_pool_elements WHERE ownerEl = ? OR Id = ?";
      ps = connection.prepareStatement(sql1);
      ps.setLong(1, new Long(elementId).longValue());
      ps.setLong(2, new Long(elementId).longValue());
      ps.execute();
      




      connection.commit();
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteTalentPoolElements()", e);
      HibernateUtil.rollbackTransaction();
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
      HibernateUtil.closeSession();
    }
  }
  
  public static void changeOrderTalentPoolElement(String elementId, String oldOwnerEl, String destOwnerEl, String destPosition)
  {
    logger.info("Inside changeOrderTalentPoolElement method");
    TalentPoolElements tpool = null;
    Session session = null;
    Connection connection = null;
    PreparedStatement ps = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      

      tpool = (TalentPoolElements)session.createCriteria(TalentPoolElements.class).add(Restrictions.eq("id", Long.valueOf(new Long(elementId).longValue()))).uniqueResult();
      if (tpool != null)
      {
        connection = session.connection();
        String sql1 = "UPDATE talent_pool_elements SET position = position - 1  WHERE \tposition > ? AND ownerEl = ?";
        ps = connection.prepareStatement(sql1);
        ps.setLong(1, tpool.getPosition());
        ps.setLong(2, tpool.getOwnerId());
        ps.executeUpdate();
        

        String sql2 = "UPDATE talent_pool_elements SET position = position + 1  WHERE \tposition >= ? AND ownerEl = ?";
        ps = connection.prepareStatement(sql2);
        ps.setLong(1, new Long(destPosition).longValue());
        ps.setLong(2, new Long(destOwnerEl).longValue());
        ps.executeUpdate();
        

        String sql3 = "UPDATE talent_pool_elements SET position = ?, ownerEl = ? , applicantpoolidInitial = ? WHERE \tId = ?";
        ps = connection.prepareStatement(sql3);
        ps.setLong(1, new Long(destPosition).longValue());
        ps.setLong(2, new Long(destOwnerEl).longValue());
        ps.setLong(3, tpool.getApplicantpoolidInitial());
        ps.setLong(4, new Long(elementId).longValue());
        
        ps.executeUpdate();
        
        connection.commit();
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on changeOrderTalentPoolElement()", e);
      HibernateUtil.rollbackTransaction();
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
      HibernateUtil.closeSession();
    }
  }
  
  public static List getAllTalentpoolForPagination(int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getAllTalentpoolForPagination method");
    List talentpoolList = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      


      Criteria outer = session.createCriteria(TalentPool.class).add(Restrictions.eq("status", "A"));
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      talentpoolList = outer.list();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllTalentpoolForPagination()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return talentpoolList;
  }
  
  public static List getAllTalentpool()
  {
    logger.info("Inside getAllTalentpool method");
    List talentpoolList = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      talentpoolList = session.createCriteria(TalentPool.class).add(Restrictions.eq("status", "A")).list();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllTalentpool()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return talentpoolList;
  }
  
  public static List getAllTalentpoolForPaginationBySearchCriteria(User user, String name, String emailid, String smtpserver, String smtpuser, String isgroup, String description, String orgId, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getAllTalentpoolForPaginationBySearchCriteria method");
    List talentpoolList = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      Criteria crit = session.createCriteria(TalentPool.class).add(Restrictions.eq("status", "A"));
      crit.add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      if ((!StringUtils.isNullOrEmpty(name)) && (!name.equals("null"))) {
        crit.add(Restrictions.like("talentPoolName", "%" + name + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(emailid)) && (!emailid.equals("null"))) {
        crit.add(Restrictions.like("talentPoolemail", "%" + emailid + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(smtpserver)) && (!smtpserver.equals("null"))) {
        crit.add(Restrictions.like("smtpserver", "%" + smtpserver + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(smtpuser)) && (!smtpuser.equals("null"))) {
        crit.add(Restrictions.like("smtpuser", "%" + smtpuser + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(description)) && (!description.equals("null"))) {
        crit.add(Restrictions.like("talentPoolDesc", "%" + description + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(orgId)) && (!orgId.equals("0"))) {
        crit.add(Restrictions.eq("organization.orgId", new Long(orgId)));
      }
      logger.info("organization.orgId" + orgId);
      crit.setFirstResult(startIndex);
      crit.setMaxResults(pageSize);
      talentpoolList = crit.list();
      

      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllTagsForPaginationBySearchCriteria()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return talentpoolList;
  }
  
  public static int getCountOfTalentpoolBySearchCriteria(User user, String name, String emailid, String smtpserver, String smtpuser, String isgroup, String description, String orgId)
  {
    logger.info("Inside getCountOfTalentpoolBySearchCriteria method");
    List talentpoolList = new ArrayList();
    Session session = null;
    int totaluser = 0;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      

      Criteria crit = session.createCriteria(TalentPool.class).add(Restrictions.eq("status", "A"));
      crit.add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      if ((!StringUtils.isNullOrEmpty(name)) && (!name.equals("null"))) {
        crit.add(Restrictions.like("talentPoolName", "%" + name + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(emailid)) && (!emailid.equals("null"))) {
        crit.add(Restrictions.like("talentPoolemail", "%" + emailid + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(smtpserver)) && (!smtpserver.equals("null"))) {
        crit.add(Restrictions.like("smtpserver", "%" + smtpserver + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(smtpuser)) && (!smtpuser.equals("null"))) {
        crit.add(Restrictions.like("smtpuser", "%" + smtpuser + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(description)) && (!description.equals("null"))) {
        crit.add(Restrictions.like("talentPoolDesc", "%" + description + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(orgId)) && (!orgId.equals("0"))) {
        crit.add(Restrictions.eq("organization.orgId", new Long(orgId)));
      }
      crit.setProjection(Projections.rowCount());
      totaluser = ((Integer)crit.list().get(0)).intValue();
      

      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfTalentpoolBySearchCriteria()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return totaluser;
  }
  
  public static TalentPool saveTalentpool(TalentPool talentpool)
  {
    logger.info("Inside saveTalentpool method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.save(talentpool);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on saveTags()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return talentpool;
  }
  
  public static TalentPool updateTalentpool(TalentPool talentpool)
  {
    logger.info("Inside updateTalentpool method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.update(talentpool);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on updateTalentpool()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return talentpool;
  }
  
  public static TalentPool getTalentpoolDetails(String id)
  {
    logger.info("Inside getTalentpoolDetails method");
    TalentPool talent = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      talent = (TalentPool)session.createCriteria(TalentPool.class).add(Restrictions.eq("talentPoolId", new Long(id))).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getTalentpoolDetails()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return talent;
  }
}
