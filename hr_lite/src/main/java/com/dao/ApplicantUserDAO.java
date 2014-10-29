package com.dao;

import com.bean.ActionsAttachment;
import com.bean.ApplicantComment;
import com.bean.ApplicantUser;
import com.bean.ApplicantUserActions;
import com.bean.JobApplicationEvent;
import com.bean.User;
import com.util.StringUtils;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class ApplicantUserDAO
{
  protected static final Logger logger = Logger.getLogger(ApplicantUserDAO.class);
  
  public static String isEmailIdExist(String emailId, long applicant_number, long super_user_key)
  {
    logger.info("Inside isEmailIdExist method");
    String email = null;
    Session session = null;
    User usr = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      String hql = "select emailId from ApplicantUser a where a.emailId = :emailId and a.status != :status and a.applicant.applicant_number = :applicant_number and a.applicant.super_user_key=:super_user_key";
      
      Query query = session.createQuery(hql);
      query.setString("emailId", emailId);
      query.setString("status", "D");
      query.setLong("applicant_number", applicant_number);
      query.setLong("super_user_key", super_user_key);
      
      Object ob = query.uniqueResult();
      if (ob != null) {
        email = (String)ob;
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on isEmailIdExist()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return email;
  }
  
  public static ApplicantUser saveApplicantUser(ApplicantUser usr)
  {
    logger.info("Inside saveApplicantUser method");
    ApplicantUser user = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      session.save(usr);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on saveApplicantUser()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return usr;
  }
  
  public static ApplicantComment saveApplicantComment(ApplicantComment comment)
  {
    logger.info("Inside saveApplicantComment method");
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      session.save(comment);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on saveApplicantComment()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return comment;
  }
  
  public static ApplicantComment deleteApplicantComment(ApplicantComment comment)
  {
    logger.info("Inside deleteApplicantComment method");
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      

      session.delete(comment);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteApplicantComment()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return comment;
  }
  
  public static ApplicantComment updateApplicantComment(ApplicantComment comment)
  {
    logger.info("Inside updateApplicantComment method");
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      

      session.update(comment);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on updateApplicantComment()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return comment;
  }
  
  public static ApplicantUser updateApplicantUser(ApplicantUser usr)
  {
    logger.info("Inside updateApplicantUser method");
    ApplicantUser user = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      session.update(usr);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on updateApplicantUser()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return usr;
  }
  
  public static ApplicantUser getApplicantUser(long applicantId)
  {
    logger.info("Inside getApplicantUser method");
    
    ApplicantUser applicantuser = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      applicantuser = (ApplicantUser)session.createCriteria(ApplicantUser.class).createAlias("applicant", "applicant").add(Restrictions.eq("applicant.applicantId", Long.valueOf(applicantId))).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantUser()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return applicantuser;
  }
  
  public static ApplicantComment getApplicantComment(long applicantId, String uuid, long commentId)
  {
    logger.info("Inside getApplicantComment method");
    
    ApplicantComment applicantcomment = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      applicantcomment = (ApplicantComment)session.createCriteria(ApplicantComment.class).add(Restrictions.eq("applicantcommenttId", Long.valueOf(commentId))).add(Restrictions.eq("applicantId", Long.valueOf(applicantId))).add(Restrictions.eq("appuuid", uuid)).uniqueResult();
      




      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantComment()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return applicantcomment;
  }
  
  public static ApplicantUser isLoginSuccess(String emailId, String password, String applicantCode)
  {
    logger.info("Inside isLoginSuccess method");
    ApplicantUser user = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      user = (ApplicantUser)session.createCriteria(ApplicantUser.class).add(Restrictions.eq("emailId", emailId)).add(Restrictions.eq("applicantCode", applicantCode)).add(Restrictions.eq("password", password)).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on isLoginSuccess()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return user;
  }
  
  public static List getApplicantActionsList(long applicantId, String uuid)
  {
    logger.info("Inside getApplicantActionsList method");
    
    List actionsList = new ArrayList();
    JobApplicationEvent event = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      actionsList = session.createCriteria(ApplicantUserActions.class).add(Restrictions.eq("applicantId", Long.valueOf(applicantId))).add(Restrictions.eq("uuid", uuid)).addOrder(Order.asc("appuseractionId")).list();
      



      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getLastEvent()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return actionsList;
  }
  
  public static List getApplicantCommentsListByCreatedBy(long applicantId, String uuid, String createdBy)
  {
    logger.info("Inside getApplicantCommentsListByCreatedBy method");
    
    List actionsList = new ArrayList();
    JobApplicationEvent event = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      actionsList = session.createCriteria(ApplicantComment.class).add(Restrictions.eq("applicantId", Long.valueOf(applicantId))).add(Restrictions.eq("appuuid", uuid)).add(Restrictions.eq("createdBy", createdBy)).addOrder(Order.asc("applicantcommenttId")).list();
      



      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantCommentsListByCreatedBy()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return actionsList;
  }
  
  public static List getApplicantCommentsListVisibleToApplicant(long applicantId, String uuid)
  {
    logger.info("Inside getApplicantCommentsListVisibleToApplicant method");
    
    List actionsList = new ArrayList();
    JobApplicationEvent event = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      actionsList = session.createCriteria(ApplicantComment.class).add(Restrictions.eq("applicantId", Long.valueOf(applicantId))).add(Restrictions.eq("appuuid", uuid)).add(Restrictions.eq("isVisibleToApplicant", "Y")).addOrder(Order.asc("applicantcommenttId")).list();
      



      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantCommentsListVisibleToApplicant()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return actionsList;
  }
  
  public static List getApplicantCommentsListVisibleToReferrer(long applicantId, String uuid)
  {
    logger.info("Inside getApplicantCommentsListVisibleToReferrer method");
    
    List actionsList = new ArrayList();
    JobApplicationEvent event = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      actionsList = session.createCriteria(ApplicantComment.class).add(Restrictions.eq("applicantId", Long.valueOf(applicantId))).add(Restrictions.eq("appuuid", uuid)).add(Restrictions.eq("isVisibleToApplicant", "R")).addOrder(Order.asc("applicantcommenttId")).list();
      



      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantCommentsListVisibleToReferrer()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return actionsList;
  }
  
  public static List getApplicantCommentsList(long applicantId, String uuid)
  {
    logger.info("Inside getApplicantCommentsList method");
    
    List actionsList = new ArrayList();
    JobApplicationEvent event = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      actionsList = session.createCriteria(ApplicantComment.class).add(Restrictions.eq("applicantId", Long.valueOf(applicantId))).add(Restrictions.eq("appuuid", uuid)).addOrder(Order.asc("applicantcommenttId")).list();
      



      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantCommentsList()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return actionsList;
  }
  
  public static ApplicantUserActions getApplicantAction(long applicantId, String uuid, String actionName)
  {
    logger.info("Inside getApplicantAction method");
    
    ApplicantUserActions action = null;
    JobApplicationEvent event = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      action = (ApplicantUserActions)session.createCriteria(ApplicantUserActions.class).add(Restrictions.eq("applicantId", Long.valueOf(applicantId))).add(Restrictions.eq("uuid", uuid)).add(Restrictions.eq("actionName", actionName)).uniqueResult();
      



      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getLastEvent()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return action;
  }
  
  public static ApplicantUserActions saveApplicantUserActions(ApplicantUserActions appuseraction)
  {
    logger.info("Inside saveApplicantUserActions method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      session.save(appuseraction);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on updateApplicantUser()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return appuseraction;
  }
  
  public static ApplicantUserActions getApplicantUserActions(long applicantId, String uuid, long appuseractionId)
  {
    logger.info("Inside getApplicantUserActions method");
    ApplicantUserActions appuseraction = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      appuseraction = (ApplicantUserActions)session.createCriteria(ApplicantUserActions.class).add(Restrictions.eq("appuseractionId", Long.valueOf(appuseractionId))).add(Restrictions.eq("applicantId", Long.valueOf(applicantId))).add(Restrictions.eq("uuid", uuid)).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantUserActions()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return appuseraction;
  }
  
  public static void saveActionAttachment(ActionsAttachment attachment)
  {
    logger.info("Inside saveActionAttachment method");
    User user = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      session.save(attachment);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on saveActionAttachment()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
  }
  
  public static List getActionAttachmentList(long idvalue, String actionName)
  {
    logger.info("Inside getActionAttachmentList method");
    
    List attachmentList = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      Criteria outer = session.createCriteria(ActionsAttachment.class).add(Restrictions.eq("idvalue", Long.valueOf(idvalue))).add(Restrictions.eq("action", actionName));
      
      attachmentList = outer.list();
      




      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getActionAttachmentList()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return attachmentList;
  }
  
  public static ActionsAttachment getActionAttachment(String uuid)
  {
    logger.info("Inside getActionAttachment method");
    
    ActionsAttachment attach = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      Criteria outer = session.createCriteria(ActionsAttachment.class).add(Restrictions.eq("uuid", uuid));
      attach = (ActionsAttachment)outer.uniqueResult();
      




      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getActionAttachment()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return attach;
  }
  
  public static void deleteActionAttachment(String uuid)
  {
    logger.info("Inside deleteActionAttachment method");
    
    ActionsAttachment attach = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      Criteria outer = session.createCriteria(ActionsAttachment.class).add(Restrictions.eq("uuid", uuid));
      attach = (ActionsAttachment)outer.uniqueResult();
      
      String hql = "delete from ActionsAttachment where uuid = :uuid ";
      Query query = session.createQuery(hql);
      query.setString("uuid", uuid);
      int rowCount = query.executeUpdate();
      



      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteActionAttachment()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
  }
  
  public static List getApplicantUsersForPagination(String fullName, long applicant_number, String emailId, String createdBy, long super_user_key, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getApplicantUsersForPagination method");
    logger.info(" fullName : " + fullName);
    
    logger.info(" emailId : " + emailId);
    logger.info(" createdBy : " + createdBy);
    List applicantUserList = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      Criteria criteria = session.createCriteria(ApplicantUser.class).createAlias("applicant", "applicant");
      criteria.add(Restrictions.eq("applicant.super_user_key", Long.valueOf(super_user_key)));
      if ((!StringUtils.isNullOrEmpty(fullName)) && (!fullName.equals("null"))) {
        criteria.add(Restrictions.like("applicant.fullName", "%" + fullName + "%"));
      }
      if (applicant_number != 0L) {
        criteria.add(Restrictions.eq("applicant.applicant_number", Long.valueOf(applicant_number)));
      }
      if ((!StringUtils.isNullOrEmpty(emailId)) && (!emailId.equals("null"))) {
        criteria.add(Restrictions.like("emailId", "%" + emailId + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(createdBy)) && (!emailId.equals("null"))) {
        criteria.add(Restrictions.like("createdBy", "%" + createdBy + "%"));
      }
      if (dir_str.equalsIgnoreCase("asc")) {
        criteria.addOrder(Order.asc(sort_str));
      } else {
        criteria.addOrder(Order.desc(sort_str));
      }
      criteria.setFirstResult(startIndex);
      criteria.setMaxResults(pageSize);
      applicantUserList = criteria.list();
      


      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantUsersForPagination()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return applicantUserList;
  }
  
  public static int getCountOfAllApplicantUsers(String fullName, long applicant_number, String emailId, String createdBy, long super_user_key)
  {
    logger.info("Inside getCountOfAllApplicantUsers method");
    int totaluser = 0;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      

      Criteria criteria = session.createCriteria(ApplicantUser.class).createAlias("applicant", "applicant");
      
      criteria.add(Restrictions.eq("applicant.super_user_key", Long.valueOf(super_user_key)));
      if ((!StringUtils.isNullOrEmpty(fullName)) && (!fullName.equals("null"))) {
        criteria.add(Restrictions.like("applicant.fullName", "%" + fullName + "%"));
      }
      if (applicant_number != 0L) {
        criteria.add(Restrictions.eq("applicant.applicant_number", Long.valueOf(applicant_number)));
      }
      if ((!StringUtils.isNullOrEmpty(emailId)) && (!emailId.equals("null"))) {
        criteria.add(Restrictions.like("emailId", "%" + emailId + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(createdBy)) && (!emailId.equals("null"))) {
        criteria.add(Restrictions.like("createdBy", "%" + createdBy + "%"));
      }
      criteria.setProjection(Projections.rowCount());
      totaluser = ((Integer)criteria.list().get(0)).intValue();
      System.out.println("totaluser" + totaluser);
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfAllApplicantUsers()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return totaluser;
  }
  
  public static List getApplicantUsersForPagination(int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getApplicantUsersForPagination method");
    List applicantUserList = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      Criteria outer = session.createCriteria(ApplicantUser.class);
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      applicantUserList = outer.list();
      


      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantUsersForPagination()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return applicantUserList;
  }
  
  public static int getCountOfAllApplicantUsers()
  {
    logger.info("Inside getCountOfAllApplicantUsers method");
    int totaluser = 0;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      

      Criteria criteria = session.createCriteria(ApplicantUser.class);
      criteria.setProjection(Projections.rowCount());
      totaluser = ((Integer)criteria.list().get(0)).intValue();
      System.out.println("totaluser" + totaluser);
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfAllApplicantUsers()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return totaluser;
  }
}
