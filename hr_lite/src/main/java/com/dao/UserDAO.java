package com.dao;

import java.math.BigInteger;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bean.DashBoardConfig;
import com.bean.Designations;
import com.bean.EmailNotificationSetting;
import com.bean.EmailTemplates;
import com.bean.JobType;
import com.bean.Permissions;
import com.bean.ProfilePhoto;
import com.bean.RefferalEmployee;
import com.bean.Role;
import com.bean.User;
import com.bean.UserGroup;
import com.bean.UserRegData;
import com.bean.WorkShift;
import com.bean.employee.EmergencyContact;
import com.bean.employee.Imigrations;
import com.bean.employee.OrganizationDetails;
import com.bean.employee.UserContactInfo;
import com.bean.employee.UserDependents;
import com.bean.employee.UserEducationDetails;
import com.bean.employee.UserJobDetails;
import com.bean.employee.UserLanguages;
import com.bean.employee.UserLicenses;
import com.bean.employee.UserMemberShip;
import com.bean.employee.UserReportTo;
import com.bean.employee.UserSalary;
import com.bean.employee.UserSkills;
import com.bean.filter.ScreenFields;
import com.bean.lov.Category;
import com.bean.lov.EthnicRace;
import com.bean.lov.JobCategory;
import com.bean.lov.VeteranDisability;
import com.bean.pool.TalentPool;
import com.common.Common;
import com.util.ConvertBeanUtil;
import com.util.StringUtils;

public class UserDAO
  extends HibernateDaoSupport
{
  protected static final Logger logger = Logger.getLogger(UserDAO.class);
  
  public static UserGroup getUserGroup(long usrgrpid)
  {
    logger.info("Inside getUserGroup method");
    UserGroup usrgrp = null;
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      usrgrp = (UserGroup)session.createCriteria(UserGroup.class).add(Restrictions.eq("usergrpId", new Long(usrgrpid))).add(Restrictions.ne("status", "D")).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserGroup()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return usrgrp;
  }
  
  public UserGroup getUserGroupTx(long usrgrpid)
  {
    logger.info("Inside getUserGroupTx method");
    UserGroup usrgrp = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      usrgrp = (UserGroup)session.createCriteria(UserGroup.class).add(Restrictions.eq("usergrpId", new Long(usrgrpid))).add(Restrictions.eq("status", "A")).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserGroupTx()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return usrgrp;
  }
  
  public User getUserFullNameEmailAndUserIdById(long userId)
    throws Exception
  {
    logger.info("Inside getUserFullNameEmailAndUserIdById method");
    User user = new User();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      String sql = "select u.user_id, u.first_name, u.last_name , u.email_id, u.user_name from user_data u where user_id = :user_id";
      Query query = session.createSQLQuery(sql);
      query.setLong("user_id", userId);
      List userList = query.list();
      Object[] obj = (Object[])userList.get(0);
      BigInteger userId1 = (BigInteger)obj[0];
      long uid = userId1.longValue();
      user.setUserId(uid);
      user.setFirstName((String)obj[1]);
      user.setLastName((String)obj[2]);
      user.setEmailId((String)obj[3]);
      user.setUserName((String)obj[4]);
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserFullNameEmailAndUserIdById()", e);
      throw e;
    }
    return user;
  }
  
  public List<User> getUserRepotingToUserAndGoals(long userId, String status, long timePeriodId)
    throws Exception
  {
    logger.info("Inside getUserRepotingToUserAndGoalInitiated method");
    List<User> userList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      String sql = " select distinct u.user_id , u.first_name, u.last_name from user_data u, user_report_to ur , perf_users_goal_kra pu  where u.user_id = pu.user_id  and pu.user_id = ur.user_id and pu.status= :status  and ur.report_to_user_id = :user_id and pu.time_period_id = :time_period_id ";
      


      Query query = session.createSQLQuery(sql);
      query.setString("status", status);
      query.setLong("user_id", userId);
      query.setLong("time_period_id", timePeriodId);
      List userListn = query.list();
      for (int i = 0; i < userListn.size(); i++)
      {
        Object[] obj = (Object[])userListn.get(i);
        BigInteger userId1 = (BigInteger)obj[0];
        long uid = userId1.longValue();
        User user = new User();
        user.setUserId(uid);
        user.setFirstName((String)obj[1]);
        user.setLastName((String)obj[2]);
        userList.add(user);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserRepotingToUserAndGoalInitiated()", e);
      throw e;
    }
    return userList;
  }
  
  public static String getCurrentOwnerName(long ownerId, String isGroup)
  {
    String oname = "";
    try
    {
      if ((isGroup != null) && (isGroup.equals("Y")))
      {
        UserGroup usergrp = getUserGroupNameandIdById(ownerId);
        oname = usergrp.getUsergrpName();
      }
      else
      {
        User usr = getUserFullNameEmailAndUserId(ownerId);
        oname = usr.getFirstName() + " " + usr.getLastName();
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    logger.info("owner name" + oname);
    return oname;
  }
  
  public static UserGroup getUserGroupNameandIdById(long usegroupid)
    throws Exception
  {
    logger.info("Inside getUserGroupNameandIdById method");
    UserGroup usergroup = new UserGroup();
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      String sql = "select u.user_group_id, u.user_group_name from user_group u where user_group_id = :user_group_id";
      Query query = session.createSQLQuery(sql);
      query.setLong("user_group_id", usegroupid);
      List usergrpList = query.list();
      Object[] obj = (Object[])usergrpList.get(0);
      BigInteger userId1 = (BigInteger)obj[0];
      long uid = userId1.longValue();
      usergroup.setUsergrpId(uid);
      usergroup.setUsergrpName((String)obj[1]);
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserGroupNameandIdById()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return usergroup;
  }
  
  public static String getUserFullName(long userId)
  {
    logger.info("Inside getUserFullName method");
    String userfullname = "";
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      String sql = "select u.first_name, u.last_name from user_data u where user_id = :user_id";
      Query query = session.createSQLQuery(sql);
      query.setLong("user_id", userId);
      List userList = query.list();
      Object[] obj = (Object[])userList.get(0);
      userfullname = (String)obj[0] + " " + (String)obj[1];
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserFullName()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return userfullname;
  }
  
  public static String getUserFirstName(long userId)
  {
    logger.info("Inside getUserFirstName method");
    String userFirstname = "";
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      String sql = "select u.first_name from user_data u where user_id = :user_id";
      Query query = session.createSQLQuery(sql);
      query.setLong("user_id", userId);
      List userList = query.list();
      Object[] obj = (Object[])userList.get(0);
      userFirstname = (String)obj[0];
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserFirstName()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return userFirstname;
  }
  
  public static User getUserFullNameEmailAndUserId(long userId)
  {
    logger.info("Inside getUserFullNameAndUserId method");
    User user = new User();
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      String sql = "select u.user_id, u.first_name, u.last_name , u.email_id, u.user_name from user_data u where user_id = :user_id";
      Query query = session.createSQLQuery(sql);
      query.setLong("user_id", userId);
      List userList = query.list();
      Object[] obj = (Object[])userList.get(0);
      BigInteger userId1 = (BigInteger)obj[0];
      long uid = userId1.longValue();
      user.setUserId(uid);
      user.setFirstName((String)obj[1]);
      user.setLastName((String)obj[2]);
      user.setEmailId((String)obj[3]);
      user.setUserName((String)obj[4]);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserFullNameAndUserId()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return user;
  }
  
  public static UserGroup getUserGroupNameandId(long usegroupid)
  {
    logger.info("Inside getUserGroupNameandId method");
    UserGroup usergroup = new UserGroup();
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      String sql = "select u.user_group_id, u.user_group_name from user_group u where user_group_id = :user_group_id";
      Query query = session.createSQLQuery(sql);
      query.setLong("user_group_id", usegroupid);
      List usergrpList = query.list();
      Object[] obj = (Object[])usergrpList.get(0);
      BigInteger userId1 = (BigInteger)obj[0];
      long uid = userId1.longValue();
      usergroup.setUsergrpId(uid);
      usergroup.setUsergrpName((String)obj[1]);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserGroupNameandId()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return usergroup;
  }
  
  public static User getUserFullNameEmailAndUserId(String username)
  {
    logger.info("Inside getUserFullNameAndUserId method");
    User user = new User();
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      String sql = "select u.user_id, u.first_name, u.last_name , u.email_id , u.user_name from user_data u where user_name = :user_name";
      Query query = session.createSQLQuery(sql);
      query.setString("user_name", username);
      List userList = query.list();
      Object[] obj = (Object[])userList.get(0);
      BigInteger userId = (BigInteger)obj[0];
      long uid = userId.longValue();
      user.setUserId(uid);
      user.setFirstName((String)obj[1]);
      user.setLastName((String)obj[2]);
      user.setEmailId((String)obj[3]);
      user.setUserName((String)obj[4]);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserFullNameAndUserId()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return user;
  }
  
  public static User isLoginSuccess(String userName, String password)
  {
    logger.info("Inside isLoginSuccess method");
    User user = null;
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      user = (User)session.createCriteria(User.class).add(Restrictions.eq("userName", userName)).add(Restrictions.eq("password", password)).uniqueResult();
      
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
  
  public static User isLoginSuccessTypeEmployee(String userName, String password)
  {
    logger.info("Inside isLoginSuccessTypeEmployee method");
    User user = null;
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      user = (User)session.createCriteria(User.class).add(Restrictions.eq("userName", userName)).add(Restrictions.eq("password", password)).add(Restrictions.eq("type", "Employee")).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on isLoginSuccessTypeEmployee()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return user;
  }
  
  public static User isLoginSuccessTypeAgency(String userName, String password)
  {
    logger.info("Inside isLoginSuccessTypeAgency method");
    User user = null;
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      user = (User)session.createCriteria(User.class).add(Restrictions.eq("userName", userName)).add(Restrictions.eq("password", password)).add(Restrictions.eq("type", "Vendor")).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on isLoginSuccessTypeAgency()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return user;
  }
  
  public static User saveUser(User usr)
  {
    logger.info("Inside saveUser method");
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      session.save(usr);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on saveUser()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return usr;
  }
  
  public User saveUserData(User usr)
    throws Exception
  {
    logger.info("Inside saveUserData method");
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.save(usr);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveUserData()", e);
      
      throw e;
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return usr;
  }
  
  public Role getRole(long id)
  {
    logger.info("Inside getRole method");
    Role role = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      role = (Role)session.createCriteria(Role.class).add(Restrictions.eq("roleId", Long.valueOf(id))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getRole()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return role;
  }
  
  public void saveDashBoardWidgetList(List widgetList, long userId)
    throws Exception
  {
    logger.info("Inside saveDashBoardWidgetList method");
    User user = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      String hql = "delete from DashBoardConfig where userId = :userId";
      Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
      query.setLong("userId", userId);
      
      int rowCount = query.executeUpdate();
      for (int i = 0; i < widgetList.size(); i++)
      {
        DashBoardConfig dbc = (DashBoardConfig)widgetList.get(i);
        session.save(dbc);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on saveDashBoardWidgetList()", e);
      
      throw e;
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public UserRegData saveUserRegData(UserRegData usr)
    throws Exception
  {
    logger.info("Inside saveUserRegData method"+"Created Date:>>>>"+usr.getCreatedDate());
    org.hibernate.Session session = null;
    usr.setCreatedDate(new Date());
    usr.setExpireDate(new Date());
    try
    {
    	logger.debug("Created Date:>>>>"+usr.getCreatedDate());
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.save(usr);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveUserRegData()", e);
      
      throw e;
    }
    finally
    {
      logger.info("Inside finally method");
      
    }
    return usr;
  }
  
  public UserRegData updateRegUserData(UserRegData usr)
    throws Exception
  {
    logger.info("Inside updateRegUserData method");
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.update(usr);
    }
    catch (Exception e)
    {
      logger.error("Exception on updateRegUserData()", e);
      
      throw e;
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return usr;
  }
  
  public static UserGroup saveUserGroup(UserGroup usrgrp)
  {
    logger.info("Inside saveUserGroup method");
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      session.save(usrgrp);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on saveUserGroup()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return usrgrp;
  }
  
  public static UserGroup updateUserGroup(UserGroup usrgrp)
  {
    logger.info("Inside updateUserGroup method");
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      session.update(usrgrp);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on updateUserGroup()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return usrgrp;
  }
  
  public static ProfilePhoto saveUserProfilePhoto(ProfilePhoto pphoto)
  {
    logger.info("Inside saveUserProfilePhoto method");
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      session.saveOrUpdate(pphoto);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on saveUserProfilePhoto()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return pphoto;
  }
  
  public static byte[] getUserProfilePhoto(long profilePhotoId)
  {
    logger.info("Inside getUserProfilePhoto method");
    ProfilePhoto pp = null;
    org.hibernate.Session session = null;
    
    byte[] imgData = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      pp = (ProfilePhoto)session.createCriteria(ProfilePhoto.class).add(Restrictions.eq("profilePhotoId", Long.valueOf(profilePhotoId))).uniqueResult();
      
      Blob img = pp.getProfilePhoto();
      
      imgData = img.getBytes(1L, (int)img.length());
      

      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserProfilePhoto()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return imgData;
  }
  
  public static User updateUser(User usr)
  {
    logger.info("Inside updateUser method");
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      session.update(usr);
      


      RefferalEmployee refemp = (RefferalEmployee)session.createCriteria(RefferalEmployee.class).add(Restrictions.eq("employeeemail", usr.getEmailId())).uniqueResult();
      if (refemp != null)
      {
        refemp = ConvertBeanUtil.convertUserToEmployeeReferral(usr, refemp);
        session.update(refemp);
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on updateUser()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return usr;
  }
  
  public User updateUserData(User usr)
    throws Exception
  {
    logger.info("Inside updateUserData method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      session.update(usr);
      


      RefferalEmployee refemp = (RefferalEmployee)session.createCriteria(RefferalEmployee.class).add(Restrictions.eq("employeeemail", usr.getEmailId())).uniqueResult();
      if (refemp != null)
      {
        refemp = ConvertBeanUtil.convertUserToEmployeeReferral(usr, refemp);
        session.update(refemp);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on updateUserData()", e);
      throw e;
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return usr;
  }
  
  public UserRegData updateUserRegData(UserRegData usr)
  {
    logger.info("Inside updateUserData method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      session.update(usr);
    }
    catch (Exception e)
    {
      logger.error("Exception on updateUserRegData()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return usr;
  }
  
  public static User getUserByEmailId(String email)
  {
    logger.info("Inside getUserByEmailId method");
    org.hibernate.Session session = null;
    User usr = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      usr = (User)session.createCriteria(User.class).add(Restrictions.eq("emailId", email)).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserByEmailId()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return usr;
  }
  
  public User getRootUserBySuperUserKey(long super_user_key)
  {
    logger.info("Inside getRootUserBySuperUserKey method");
    User user = null;
    org.hibernate.Session session = null;
    List ulist = new ArrayList();
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      ulist = session.createCriteria(User.class).add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key))).addOrder(Order.asc("createdDate")).list();
      if ((ulist != null) && (ulist.size() > 0)) {
        user = (User)ulist.get(0);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getRootUserBySuperUserKey()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return user;
  }
  
  public UserContactInfo getUserContactInfo(long userId)
  {
    logger.info("Inside getUserContactInfo method");
    org.hibernate.Session session = null;
    UserContactInfo usr = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      usr = (UserContactInfo)session.createCriteria(UserContactInfo.class).add(Restrictions.eq("userId", Long.valueOf(userId))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserContactInfo()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return usr;
  }
  
  public EmergencyContact getUserEmergencyContactInfo(long emergencyContactId)
  {
    logger.info("Inside getUserEmergencyContactInfo method >>> ");
    logger.info("emergencyContactId --->>> " + emergencyContactId);
    org.hibernate.Session session = null;
    EmergencyContact usr = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      usr = (EmergencyContact)session.createCriteria(EmergencyContact.class).add(Restrictions.eq("emergencyContactId", Long.valueOf(emergencyContactId))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserEmergencyContactInfo()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return usr;
  }
  
  public UserContactInfo getUserContactInfobyUsrContactId(long userContactId)
  {
    logger.info("Inside getUserContactInfobyUsrContactId method");
    org.hibernate.Session session = null;
    UserContactInfo usr = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      usr = (UserContactInfo)session.createCriteria(UserContactInfo.class).add(Restrictions.eq("userContactId", Long.valueOf(userContactId))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserContactInfo()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return usr;
  }
  
  public UserContactInfo updateUserContactInfo(UserContactInfo usercontact)
  {
    logger.info("Inside updateUserContactInfo method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.update(usercontact);
    }
    catch (Exception e)
    {
      logger.error("Exception on updateUserContactInfo()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return usercontact;
  }
  
  public UserContactInfo saveUserContactInfo(UserContactInfo usercontact)
  {
    logger.info("Inside saveUserContactInfo method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.save(usercontact);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveUserContactInfo()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return usercontact;
  }
  
  public EmergencyContact saveUserEmergencyContactInfo(EmergencyContact emergencyContact)
  {
    logger.info("Inside saveUserEmergencyContactInfo method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.save(emergencyContact);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveUserEmergencyContactInfo()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return emergencyContact;
  }
  
  public EmergencyContact updateUserEmergencyContactInfo(EmergencyContact emergencyContact)
  {
    logger.info("Inside updateUserEmergencyContactInfo method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.update(emergencyContact);
    }
    catch (Exception e)
    {
      logger.error("Exception on updateUserEmergencyContactInfo()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return emergencyContact;
  }
  
  public EmergencyContact deleteEmergencyContact(EmergencyContact emergencyContact)
  {
    logger.info("Inside deleteEmergencyContact method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.delete(emergencyContact);
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteEmergencyContact()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return emergencyContact;
  }
  
  public List getUserEmergencyContactlist(long userId)
  {
    logger.info("Inside getUserEmergencyContactlist method");
    List userList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      userList = session.createCriteria(EmergencyContact.class).add(Restrictions.eq("userId", Long.valueOf(userId))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserEmergencyContactlist()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return userList;
  }
  
  public UserDependents saveDependents(UserDependents userDependents)
  {
    logger.info("Inside saveDependents method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.save(userDependents);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveDependents()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return userDependents;
  }
  
  public UserDependents updateDependents(UserDependents userDependents)
  {
    logger.info("Inside updateDependents method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.update(userDependents);
    }
    catch (Exception e)
    {
      logger.error("Exception on updateDependents()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return userDependents;
  }
  
  public UserDependents deleteDependents(UserDependents userDependents)
  {
    logger.info("Inside deleteDependents method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.delete(userDependents);
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteDependents()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return userDependents;
  }
  
  public List getUserDependentslistByUserId(long userId)
  {
    logger.info("Inside getUserDependentslistByUserId method");
    List userList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      userList = session.createCriteria(UserDependents.class).add(Restrictions.eq("userId", Long.valueOf(userId))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserDependentslistByUserId()", e);
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return userList;
  }
  
  public UserDependents getUserDependentInfo(long userDependentId)
  {
    logger.info("Inside getUserDependentInfo method >>> ");
    
    org.hibernate.Session session = null;
    UserDependents userDependents = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      userDependents = (UserDependents)session.createCriteria(UserDependents.class).add(Restrictions.eq("userDependentId", Long.valueOf(userDependentId))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserDependentInfo()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return userDependents;
  }
  
  public Imigrations saveUserImmigration(Imigrations imigrations)
  {
    logger.info("Inside saveUserImmigration method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.save(imigrations);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveUserImmigration()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return imigrations;
  }
  
  public Imigrations updateUserImmigration(Imigrations imigrations)
  {
    logger.info("Inside updateUserImmigration method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.update(imigrations);
    }
    catch (Exception e)
    {
      logger.error("Exception on updateUserImmigration()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return imigrations;
  }
  
  public Imigrations deleteUserImmigration(Imigrations imigrations)
  {
    logger.info("Inside deleteUserImmigration method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.delete(imigrations);
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteUserImmigration()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return imigrations;
  }
  
  public List getUserImmigrationlistByUserId(long userId)
  {
    logger.info("Inside getUserImmigrationlistByUserId method");
    List userList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      userList = session.createCriteria(Imigrations.class).add(Restrictions.eq("userId", Long.valueOf(userId))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserImmigrationlistByUserId()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return userList;
  }
  
  public Imigrations getUserImmigrationInfo(long imigrationId)
  {
    logger.info("Inside getUserImmigrationInfo method >>> ");
    
    org.hibernate.Session session = null;
    Imigrations imigrations = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      imigrations = (Imigrations)session.createCriteria(Imigrations.class).add(Restrictions.eq("imigrationId", Long.valueOf(imigrationId))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserImmigrationInfo()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return imigrations;
  }
  
  public List getUserSalaryComponentListByUserId(long userId)
  {
    logger.info("Inside getUserSalaryComponentListByUserId method");
    List usersalaryList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      usersalaryList = session.createCriteria(UserSalary.class).add(Restrictions.eq("userId", Long.valueOf(userId))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserSalaryComponentListByUserId()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return usersalaryList;
  }
  
  public UserRegData getUserRegDataByipaddress(String ipaddress)
  {
    logger.info("Inside getUserRegDataByipaddress method");
    UserRegData reguser = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      reguser = (UserRegData)session.createCriteria(UserRegData.class).add(Restrictions.eq("ipaddress", ipaddress)).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserRegDataByipaddress()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return reguser;
  }
  
  public UserRegData getUserRegDataByid(String userregid)
  {
    logger.info("Inside getUserRegDataByid method");
    UserRegData reguser = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      reguser = (UserRegData)session.createCriteria(UserRegData.class).add(Restrictions.eq("userRegId", Long.valueOf(new Long(userregid).longValue()))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserRegDataByid()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return reguser;
  }
  
  public List getUserRegDataList()
  {
    logger.info("Inside getUserRegDataList method");
    List userRegDataList = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      userRegDataList = session.createCriteria(UserRegData.class).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserRegDataList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return userRegDataList;
  }
  
  public UserRegData getUserRegDataBySubdomain(String subdomain)
  {
    logger.info("Inside getUserRegDataBySubdomain method");
    UserRegData reguser = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      List lst = session.createCriteria(UserRegData.class).add(Restrictions.eq("subdomain", subdomain)).list();
      if ((lst != null) && (lst.size() > 0)) {
        reguser = (UserRegData)lst.get(0);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserRegDataBySubdomain()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return reguser;
  }
  
  public UserRegData getUserRegDataById(long id)
  {
    logger.info("Inside getUserRegDataById method");
    UserRegData reguser = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      reguser = (UserRegData)session.createCriteria(UserRegData.class).add(Restrictions.eq("userRegId", Long.valueOf(id))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserRegDataById()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return reguser;
  }
  
  public UserSalary saveUserSalaryComponent(UserSalary userSalary)
  {
    logger.info("Inside saveUserSalaryComponent method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.save(userSalary);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveUserSalaryComponent()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return userSalary;
  }
  
  public UserSalary updateUserSalaryComponent(UserSalary userSalary)
  {
    logger.info("Inside updateUserSalaryComponent method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.update(userSalary);
    }
    catch (Exception e)
    {
      logger.error("Exception on updateUserSalaryComponent()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return userSalary;
  }
  
  public UserSalary deleteUserSalaryComponent(UserSalary userSalary)
  {
    logger.info("Inside deleteUserSalaryComponent method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.delete(userSalary);
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteUserSalaryComponent()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return userSalary;
  }
  
  public UserSalary getUserSalaryInfo(long userSalaryId)
  {
    logger.info("Inside getUserSalaryInfo method >>> ");
    
    org.hibernate.Session session = null;
    UserSalary userSalary = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      userSalary = (UserSalary)session.createCriteria(UserSalary.class).add(Restrictions.eq("userSalaryId", Long.valueOf(userSalaryId))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserSalaryInfo()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return userSalary;
  }
  
  public void deleteUserSalarydetailsMultiple(String checkedItems)
  {
    logger.info("Inside deleteUserSalarydetailsMultiple method >>> ");
    logger.info("checkedItems >> " + checkedItems);
    
    String[] result = checkedItems.split(",");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      for (int x = 0; x < result.length; x++)
      {
        logger.info(">> " + result[x]);
        String id = result[x];
        UserSalary userSalary = getUserSalaryInfo(new Long(id).longValue());
        session.delete(userSalary);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteUserSalarydetailsMultiple()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void deleteUserEducationdetailsMultiple(String checkedItems)
  {
    logger.info("Inside deleteUserEducationdetailsMultiple method >>> ");
    logger.info("checkedItems >> " + checkedItems);
    
    String[] result = checkedItems.split(",");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      for (int x = 0; x < result.length; x++)
      {
        logger.info(">> " + result[x]);
        String id = result[x];
        UserEducationDetails userEducationDetails = getUserEducationDetails(new Long(id).longValue());
        
        session.delete(userEducationDetails);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteUserEducationdetailsMultiple()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void deleteUserSkillsdetailsMultiple(String checkedItems)
  {
    logger.info("Inside deleteUserSkillsdetailsMultiple method >>> ");
    logger.info("checkedItems >> " + checkedItems);
    
    String[] result = checkedItems.split(",");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      for (int x = 0; x < result.length; x++)
      {
        logger.info(">> " + result[x]);
        String id = result[x];
        UserSkills userSkills = getUserSkillDetails(new Long(id).longValue());
        
        session.delete(userSkills);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteUserSkillsdetailsMultiple()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void deleteUserLanguagesdetailsMultiple(String checkedItems)
  {
    logger.info("Inside deleteUserLanguagessdetailsMultiple method >>> ");
    logger.info("checkedItems >> " + checkedItems);
    
    String[] result = checkedItems.split(",");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      for (int x = 0; x < result.length; x++)
      {
        logger.info(">> " + result[x]);
        String id = result[x];
        UserLanguages userLanguages = getUserLanguagesDetails(new Long(id).longValue());
        
        session.delete(userLanguages);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteUserLanguagessdetailsMultiple()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void deleteUserLicensesdetailsMultiple(String checkedItems)
  {
    logger.info("Inside deleteUserLicensesdetailsMultiple method >>> ");
    logger.info("checkedItems >> " + checkedItems);
    
    String[] result = checkedItems.split(",");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      for (int x = 0; x < result.length; x++)
      {
        logger.info(">> " + result[x]);
        String id = result[x];
        UserLicenses userLicenses = getUserLicenseDetails(new Long(id).longValue());
        
        session.delete(userLicenses);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteUserLicensesdetailsMultiple()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void deleteUserExperienceMultiple(String checkedItems)
  {
    logger.info("Inside deleteUserExperienceMultiple method >>> ");
    logger.info("checkedItems >> " + checkedItems);
    
    String[] result = checkedItems.split(",");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      for (int x = 0; x < result.length; x++)
      {
        logger.info(">> " + result[x]);
        String id = result[x];
        OrganizationDetails organizationDetails = getUserExperienceDetails(new Long(id).longValue());
        session.delete(organizationDetails);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteUserExperienceMultiple()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void deleteUserDependentsMultiple(String checkedItems)
  {
    logger.info("Inside deleteUserDependentsMultiple method >>> ");
    logger.info("checkedItems >> " + checkedItems);
    
    String[] result = checkedItems.split(",");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      for (int x = 0; x < result.length; x++)
      {
        logger.info(">> " + result[x]);
        String id = result[x];
        UserDependents userDependents = getUserDependentInfo(new Long(id).longValue());
        session.delete(userDependents);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteUserDependentsMultiple()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void deleteUserMembershipMultiple(String checkedItems)
  {
    logger.info("Inside deleteUserMembershipMultiple method >>> ");
    logger.info("checkedItems >> " + checkedItems);
    
    String[] result = checkedItems.split(",");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      for (int x = 0; x < result.length; x++)
      {
        logger.info(">> " + result[x]);
        String id = result[x];
        UserMemberShip userMemberShip = getUserMembershipDetails(new Long(id).longValue());
        
        session.delete(userMemberShip);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteUserMembershipMultiple()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void deleteUserImmigrationMultiple(String checkedItems)
  {
    logger.info("Inside deleteUserImmigrationMultiple method >>> ");
    logger.info("checkedItems >> " + checkedItems);
    
    String[] result = checkedItems.split(",");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      for (int x = 0; x < result.length; x++)
      {
        logger.info(">> " + result[x]);
        String id = result[x];
        Imigrations imigrations = getUserImmigrationInfo(new Long(id).longValue());
        session.delete(imigrations);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteUserImmigrationMultiple()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void deleteEmergencyContactMultiple(String checkedItems)
  {
    logger.info("Inside deleteEmergencyContactMultiple method >>> ");
    logger.info("checkedItems >> " + checkedItems);
    
    String[] result = checkedItems.split(",");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      for (int x = 0; x < result.length; x++)
      {
        logger.info(">> " + result[x]);
        String id = result[x];
        EmergencyContact emergencyContact = getUserEmergencyContactInfo(new Long(id).longValue());
        session.delete(emergencyContact);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteEmergencyContactMultiple()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public UserJobDetails getUserJobdetailsByUserId(long userId)
  {
    logger.info("Inside getUserJobdetailsByUserId method >>> ");
    
    org.hibernate.Session session = null;
    UserJobDetails userJobDetails = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      userJobDetails = (UserJobDetails)session.createCriteria(UserJobDetails.class).add(Restrictions.eq("userId", Long.valueOf(userId))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserJobdetailsByUserId()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return userJobDetails;
  }
  
  public UserJobDetails getUserJobdetails(long jobdetailsId)
  {
    logger.info("Inside getUserJobdetails method >>> ");
    
    org.hibernate.Session session = null;
    UserJobDetails userJobDetails = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      userJobDetails = (UserJobDetails)session.createCriteria(UserJobDetails.class).add(Restrictions.eq("jobdetailsId", Long.valueOf(jobdetailsId))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserJobdetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return userJobDetails;
  }
  
  public UserJobDetails saveUserJobdata(UserJobDetails userJobDetails)
  {
    logger.info("Inside saveUserJobdata method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.save(userJobDetails);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveUserJobdata()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return userJobDetails;
  }
  
  public UserJobDetails updateUserJobdata(UserJobDetails userJobDetails)
  {
    logger.info("Inside updateUserJobdata method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.update(userJobDetails);
    }
    catch (Exception e)
    {
      logger.error("Exception on updateUserJobdata()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return userJobDetails;
  }
  
  public void updateDesignationByUserId(long userId, long designationId)
  {
    logger.info("Inside updateDesignationByUserId method");
    
    Connection con = null;
    PreparedStatement pstmt = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      con = session.connection();
      String sql = "update user_data set designation_id=? where user_id=?";
      

      pstmt = con.prepareStatement(sql);
      pstmt.setLong(1, designationId);
      pstmt.setLong(2, userId);
      pstmt.execute();
      con.commit();
      con.close(); return;
    }
    catch (Exception e)
    {
      logger.error("Exception on updateDesignationByUserId()", e);
    }
    finally
    {
      logger.info("Inside finally method");
      try
      {
        if (pstmt != null) {
          pstmt.close();
        }
        if (con != null) {
          con.close();
        }
      }
      catch (Exception e) {}
    }
  }
  
  public UserReportTo getUserReportTodetailsByUserId(long userId)
  {
    logger.info("Inside getUserReportTodetailsByUserId method >>> ");
    
    org.hibernate.Session session = null;
    UserReportTo userReportTo = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      userReportTo = (UserReportTo)session.createCriteria(UserReportTo.class).add(Restrictions.eq("userId", Long.valueOf(userId))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserReportTodetailsByUserId()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return userReportTo;
  }
  
  public UserReportTo getUserReportTodetailsById(long reportToId)
  {
    logger.info("Inside getUserReportTodetailsById method >>> ");
    
    org.hibernate.Session session = null;
    UserReportTo userReportTo = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      userReportTo = (UserReportTo)session.createCriteria(UserReportTo.class).add(Restrictions.eq("reportToId", Long.valueOf(reportToId))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserReportTodetailsById()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return userReportTo;
  }
  
  public UserReportTo saveUserReportTodata(UserReportTo userReportTo)
  {
    logger.info("Inside saveUserReportTodata method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.save(userReportTo);
    }
    catch (Exception e)
    {
      logger.info("Exception on saveUserReportTodata()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return userReportTo;
  }
  
  public UserReportTo updateUserReportTodata(UserReportTo userReportTo)
  {
    logger.info("Inside updateUserReportTodata method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.update(userReportTo);
    }
    catch (Exception e)
    {
      logger.error("Exception on updateUserReportTodata()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return userReportTo;
  }
  
  public UserMemberShip saveUserMembershipDetails(UserMemberShip userMemberShip)
  {
    logger.info("Inside saveUserMembershipDetails method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.save(userMemberShip);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveUserMembershipDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return userMemberShip;
  }
  
  public UserMemberShip updateUserMembershipDetails(UserMemberShip userMemberShip)
  {
    logger.info("Inside updateUserMembershipDetails method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.update(userMemberShip);
    }
    catch (Exception e)
    {
      logger.error("Exception on updateUserMembershipDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return userMemberShip;
  }
  
  public UserMemberShip deleteUserMembershipDetails(UserMemberShip userMemberShip)
  {
    logger.info("Inside deleteUserMembershipDetails method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.delete(userMemberShip);
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteUserMembershipDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return userMemberShip;
  }
  
  public List getUserMembershiplistByUserId(long userId)
  {
    logger.info("Inside getUserMembershiplistByUserId method");
    List usermembershipList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      usermembershipList = session.createCriteria(UserMemberShip.class).add(Restrictions.eq("userId", Long.valueOf(userId))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserMembershiplistByUserId()", e);
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return usermembershipList;
  }
  
  public UserMemberShip getUserMembershipDetails(long userMemberShipId)
  {
    logger.info("Inside getUserMembershipDetails method >>> ");
    
    org.hibernate.Session session = null;
    UserMemberShip userMemberShip = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      userMemberShip = (UserMemberShip)session.createCriteria(UserMemberShip.class).add(Restrictions.eq("userMemberShipId", Long.valueOf(userMemberShipId))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserMembershipDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return userMemberShip;
  }
  
  public UserMemberShip getUserMembershipDetailsByUserIdandMembershipTypeId(long userId, long membershipTypeId)
  {
    logger.info("Inside getUserMembershipDetailsByUserIdandMembershipTypeId method >>> ");
    
    org.hibernate.Session session = null;
    UserMemberShip userMemberShip = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      userMemberShip = (UserMemberShip)session.createCriteria(UserMemberShip.class).add(Restrictions.eq("userId", Long.valueOf(userId))).add(Restrictions.eq("membershiptype.membershipTypeId", Long.valueOf(membershipTypeId))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserMembershipDetailsByUserIdandMembershipTypeId()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return userMemberShip;
  }
  
  public UserEducationDetails saveUserEducationDetails(UserEducationDetails userEducationDetails)
  {
    logger.info("Inside saveUserEducationDetails method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.save(userEducationDetails);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveUserEducationDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return userEducationDetails;
  }
  
  public UserEducationDetails updateUserEducationDetails(UserEducationDetails userEducationDetails)
  {
    logger.info("Inside updateUserEducationDetails method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.update(userEducationDetails);
    }
    catch (Exception e)
    {
      logger.error("Exception on updateUserEducationDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return userEducationDetails;
  }
  
  public UserEducationDetails deleteUserEducationDetails(UserEducationDetails userEducationDetails)
  {
    logger.info("Inside deleteUserEducationDetails method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.delete(userEducationDetails);
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteUserEducationDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return userEducationDetails;
  }
  
  public List getUserEducationListByUserId(long userId)
  {
    logger.info("Inside getUserEducationListByUserId method");
    List usereducaitionList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      usereducaitionList = session.createCriteria(UserEducationDetails.class).add(Restrictions.eq("userId", Long.valueOf(userId))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserEducationListByUserId()", e);
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return usereducaitionList;
  }
  
  public List getUserSkillsListByUserId(long userId)
  {
    logger.info("Inside getUserSkillsListByUserId method");
    List userSkillsList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      userSkillsList = session.createCriteria(UserSkills.class).add(Restrictions.eq("userId", Long.valueOf(userId))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserSkillsListByUserId()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return userSkillsList;
  }
  
  public List getWidgets(long userId)
  {
    logger.info("Inside getWidgets method");
    List wList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      wList = session.createCriteria(DashBoardConfig.class).add(Restrictions.eq("userId", Long.valueOf(userId))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getWidgets()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return wList;
  }
  
  public List getUserLanguagesListByUserId(long userId)
  {
    logger.info("Inside getUserLanguagesListByUserId method");
    List userLanguagesList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      userLanguagesList = session.createCriteria(UserLanguages.class).add(Restrictions.eq("userId", Long.valueOf(userId))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserLanguagesListByUserId()", e);
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return userLanguagesList;
  }
  
  public List getUserLicensesListByUserId(long userId)
  {
    logger.info("Inside getUserLicensesListByUserId method");
    List userLicensesList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      userLicensesList = session.createCriteria(UserLicenses.class).add(Restrictions.eq("userId", Long.valueOf(userId))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserLicensesListByUserId()", e);
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return userLicensesList;
  }
  
  public List getListUsers(long orgId, long departmentId, long designationId)
  {
    logger.info("Inside getListUsers method");
    List uList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      Criteria criteria = session.createCriteria(User.class).add(Restrictions.eq("status", "A"));
      if (orgId != 0L) {
        criteria.add(Restrictions.eq("organization.orgId", Long.valueOf(orgId)));
      }
      if (departmentId != 0L) {
        criteria.add(Restrictions.eq("department.departmentId", Long.valueOf(departmentId)));
      }
      if (designationId != 0L) {
        criteria.add(Restrictions.eq("designation.designationId", Long.valueOf(designationId)));
      }
      uList = criteria.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getListUsers()", e);
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return uList;
  }
  
  public UserEducationDetails getUserEducationDetails(long educationId)
  {
    logger.info("Inside getUserEducationDetails method >>> ");
    
    org.hibernate.Session session = null;
    UserEducationDetails userEducationDetails = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      userEducationDetails = (UserEducationDetails)session.createCriteria(UserEducationDetails.class).add(Restrictions.eq("educationId", Long.valueOf(educationId))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserEducationDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return userEducationDetails;
  }
  
  public UserEducationDetails getUserEducationDetailsByUserIdandEducationName(long userId, String educationName)
  {
    logger.info("Inside getUserEducationDetailsByUserIdandEducationName method >>> ");
    
    org.hibernate.Session session = null;
    UserEducationDetails userEducationDetails = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      userEducationDetails = (UserEducationDetails)session.createCriteria(UserEducationDetails.class).add(Restrictions.eq("userId", Long.valueOf(userId))).add(Restrictions.eq("educationName", educationName)).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserEducationDetailsByUserIdandEducationName()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return userEducationDetails;
  }
  
  public UserLanguages saveUserLanguageDetails(UserLanguages userLanguages)
  {
    logger.info("Inside saveUserLanguageDetails method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.save(userLanguages);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveUserLanguageDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return userLanguages;
  }
  
  public UserLanguages updateUserLanguageDetails(UserLanguages userLanguages)
  {
    logger.info("Inside updateUserLanguageDetails method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.update(userLanguages);
    }
    catch (Exception e)
    {
      logger.error("Exception on updateUserLanguageDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return userLanguages;
  }
  
  public UserLanguages deleteUserLanguageDetails(UserLanguages userLanguages)
  {
    logger.info("Inside deleteUserLanguageDetails method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.delete(userLanguages);
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteUserLanguageDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return userLanguages;
  }
  
  public UserLanguages getUserLanguagesDetails(long userLangId)
  {
    logger.info("Inside getUserLanguagesDetails method >>> ");
    
    org.hibernate.Session session = null;
    UserLanguages userLanguages = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      userLanguages = (UserLanguages)session.createCriteria(UserLanguages.class).add(Restrictions.eq("userLangId", Long.valueOf(userLangId))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserLanguagesDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return userLanguages;
  }
  
  public UserLanguages getUserLanguagesDetailsByUserIdLanguageIdandFluency(long userId, long languageId, String fluency)
  {
    logger.info("Inside getUserLanguagesDetails method >>> ");
    
    org.hibernate.Session session = null;
    UserLanguages userLanguages = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      userLanguages = (UserLanguages)session.createCriteria(UserLanguages.class).add(Restrictions.eq("userId", Long.valueOf(userId))).add(Restrictions.eq("language.languageId", Long.valueOf(languageId))).add(Restrictions.eq("fluency", fluency)).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserLanguagesDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return userLanguages;
  }
  
  public UserSkills saveUserSkillsDetails(UserSkills userSkills)
  {
    logger.info("Inside saveUserSkillsDetails method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.save(userSkills);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveUserSkillsDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return userSkills;
  }
  
  public UserSkills updateUserSkillsDetails(UserSkills userSkills)
  {
    logger.info("Inside updateUserSkillsDetails method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.update(userSkills);
    }
    catch (Exception e)
    {
      logger.error("Exception on updateUserSkillsDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return userSkills;
  }
  
  public UserSkills deleteUserSkillsDetails(UserSkills userSkills)
  {
    logger.info("Inside deleteUserSkillsDetails method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.delete(userSkills);
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteUserSkillsDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return userSkills;
  }
  
  public UserSkills getUserSkillDetails(long skillId)
  {
    logger.info("Inside getUserSkillDetails method >>> ");
    
    org.hibernate.Session session = null;
    UserSkills userSkills = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      userSkills = (UserSkills)session.createCriteria(UserSkills.class).add(Restrictions.eq("skillId", Long.valueOf(skillId))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserSkillDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return userSkills;
  }
  
  public UserSkills getUserSkillDetailsByUserIdandSkillName(long userId, String skillName)
  {
    logger.info("Inside getUserSkillDetailsByUserIdandSkillName method >>> ");
    
    org.hibernate.Session session = null;
    UserSkills userSkills = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      userSkills = (UserSkills)session.createCriteria(UserSkills.class).add(Restrictions.eq("userId", Long.valueOf(userId))).add(Restrictions.eq("skillname", skillName)).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserSkillDetailsByUserIdandSkillName()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return userSkills;
  }
  
  public UserLicenses saveUserLicenseDetails(UserLicenses userLicenses)
  {
    logger.info("Inside saveUserLicenseDetails method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.save(userLicenses);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveUserLicenseDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return userLicenses;
  }
  
  public UserLicenses updateUserLicenseDetails(UserLicenses userLicenses)
  {
    logger.info("Inside updateUserLicenseDetails method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.update(userLicenses);
    }
    catch (Exception e)
    {
      logger.error("Exception on updateUserLicenseDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return userLicenses;
  }
  
  public UserLicenses deleteUserLicenseDetails(UserLicenses userLicenses)
  {
    logger.info("Inside deleteUserLicenseDetails method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.delete(userLicenses);
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteUserLicenseDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return userLicenses;
  }
  
  public UserLicenses getUserLicenseDetails(long userLicenseId)
  {
    logger.info("Inside getUserLicenseDetails method >>> ");
    
    org.hibernate.Session session = null;
    UserLicenses userLicenses = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      userLicenses = (UserLicenses)session.createCriteria(UserLicenses.class).add(Restrictions.eq("userLicenseId", Long.valueOf(userLicenseId))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserLicenseDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return userLicenses;
  }
  
  public UserLicenses getUserLicenseDetailsByUserIdandLicenseTypeId(long userId, long licenseTypeId)
  {
    logger.info("Inside getUserLicenseDetailsByUserIdandLicenseTypeId method >>> ");
    
    org.hibernate.Session session = null;
    UserLicenses userLicenses = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      userLicenses = (UserLicenses)session.createCriteria(UserLicenses.class).add(Restrictions.eq("userId", Long.valueOf(userId))).add(Restrictions.eq("licensetype.licenseTypeId", Long.valueOf(licenseTypeId))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserLicenseDetailsByUserIdandLicenseTypeId()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return userLicenses;
  }
  
  public OrganizationDetails saveUserExperienceDetails(OrganizationDetails organizationDetails)
  {
    logger.info("Inside saveUserExperienceDetails method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.save(organizationDetails);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveUserExperienceDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return organizationDetails;
  }
  
  public OrganizationDetails updateUserExperienceDetails(OrganizationDetails organizationDetails)
  {
    logger.info("Inside updateUserExperienceDetails method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.update(organizationDetails);
    }
    catch (Exception e)
    {
      logger.error("Exception on updateUserExperienceDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return organizationDetails;
  }
  
  public OrganizationDetails deleteUserExperienceDetails(OrganizationDetails organizationDetails)
  {
    logger.info("Inside deleteUserExperienceDetails method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.delete(organizationDetails);
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteUserExperienceDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return organizationDetails;
  }
  
  public OrganizationDetails getUserExperienceDetails(long orgDetailsId)
  {
    logger.info("Inside getUserExperienceDetails method >>> ");
    
    org.hibernate.Session session = null;
    OrganizationDetails organizationDetails = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      organizationDetails = (OrganizationDetails)session.createCriteria(OrganizationDetails.class).add(Restrictions.eq("orgDetailsId", Long.valueOf(orgDetailsId))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserExperienceDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return organizationDetails;
  }
  
  public List getUserExperienceListByUserId(long userId)
  {
    logger.info("Inside getUserExperienceListByUserId method");
    List userexpList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      userexpList = session.createCriteria(OrganizationDetails.class).add(Restrictions.eq("userId", Long.valueOf(userId))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserExperienceListByUserId()", e);
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return userexpList;
  }
  
  public static User getUserByEmailIdWithNotDeleted(String email)
  {
    logger.info("Inside getUserByEmailIdWithNotDeleted method");
    org.hibernate.Session session = null;
    User usr = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      usr = (User)session.createCriteria(User.class).add(Restrictions.eq("emailId", email)).add(Restrictions.ne("status", "D")).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserByEmailIdWithNotDeleted()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return usr;
  }
  
  public static String isEmailIdExist(String emailId)
  {
    logger.info("Inside isEmailIdExist method");
    String email = null;
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      String hql = "select emailId from User a where a.emailId = :emailId and a.status != :status";
      Query query = session.createQuery(hql);
      query.setString("emailId", emailId);
      query.setString("status", "D");
      
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
  
  public static String isUserNameExist(String userName)
  {
    logger.info("Inside isUserNameExist method");
    String usern = null;
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      String hql = "select userName from User a where a.userName = :userName and a.status != :status";
      Query query = session.createQuery(hql);
      query.setString("userName", userName);
      query.setString("status", "D");
      
      Object ob = query.uniqueResult();
      if (ob != null) {
        usern = (String)ob;
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on isEmployeeCodeExist()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return usern;
  }
  
  public static String isEmployeeCodeExist(String employeecode, long superUserKey)
  {
    logger.info("Inside isEmployeeCodeExist method");
    String empcode = null;
    org.hibernate.Session session = null;
    
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      String hql = "select employeecode from User a where a.employeecode = :employeecode and a.status != :status and a.super_user_key =:super_user_key";
      Query query = session.createQuery(hql);
      query.setString("employeecode", employeecode);
      query.setString("status", "D");
      query.setLong("super_user_key", superUserKey);
      
      Object ob = query.uniqueResult();
      if (ob != null) {
        empcode = (String)ob;
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on isEmployeeCodeExist()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return empcode;
  }
  
  public static User getUserByUserName(String username)
  {
    logger.info("Inside getUserByUserName method");
    org.hibernate.Session session = null;
    User usr = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      usr = (User)session.createCriteria(User.class).add(Restrictions.eq("userName", username)).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserByUserName()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return usr;
  }
  
  public static User getUserByUserNameNotDeleted(String username)
  {
    logger.info("Inside getUserByUserNameNotDeleted method");
    org.hibernate.Session session = null;
    User usr = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      usr = (User)session.createCriteria(User.class).add(Restrictions.eq("userName", username)).add(Restrictions.ne("status", "D")).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserByUserNameNotDeleted()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return usr;
  }
  
  public static User getUserByUserNameTypeEmployee(String username)
  {
    logger.info("Inside getUserByUserNameTypeEmployee method");
    org.hibernate.Session session = null;
    User usr = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      usr = (User)session.createCriteria(User.class).add(Restrictions.eq("userName", username)).add(Restrictions.eq("type", "Employee")).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserByUserNameTypeEmployee()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return usr;
  }
  
  public static User getUserByUserNameTypeVendor(String username)
  {
    logger.info("Inside getUserByUserNameTypeVendor method");
    org.hibernate.Session session = null;
    User usr = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      usr = (User)session.createCriteria(User.class).add(Restrictions.eq("userName", username)).add(Restrictions.eq("type", "Vendor")).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserByUserNameTypeVendor()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return usr;
  }
  
  public static User getUserByVendorIdandTypeVendor(long userId)
  {
    logger.info("Inside getUserByVendorIdandTypeVendor method");
    org.hibernate.Session session = null;
    User usr = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      usr = (User)session.createCriteria(User.class).add(Restrictions.eq("userId", Long.valueOf(userId))).add(Restrictions.eq("type", "Vendor")).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserByVendorIdandTypeVendor()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return usr;
  }
  
  public static User getUser(long userId)
  {
    logger.info("Inside getUser method");
    org.hibernate.Session session = null;
    User usr = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      usr = (User)session.createCriteria(User.class).add(Restrictions.eq("userId", Long.valueOf(userId))).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUser()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return usr;
  }
  
  public User getUserByUserid(long userId)
  {
    logger.info("Inside getUserByUserid method");
    org.hibernate.Session session = null;
    User usr = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      usr = (User)session.createCriteria(User.class).add(Restrictions.eq("userId", Long.valueOf(userId))).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserByUserid()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return usr;
  }
  
  public User getUserTx(long userId)
  {
    logger.info("Inside getUserTx method");
    org.hibernate.Session session = null;
    User usr = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      usr = (User)session.createCriteria(User.class).add(Restrictions.eq("userId", Long.valueOf(userId))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserTx()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return usr;
  }
  
  public static List getAllUsers(int pageSize, int pageNumber)
  {
    logger.info("Inside getAllUsers method");
    List userList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      Query query = session.createQuery("from User");
      query = query.setFirstResult(pageSize * (pageNumber - 1));
      query.setMaxResults(pageSize);
      userList = query.list();
      



      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllUsers()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return userList;
  }
  
  public static List getUsersForPagination(String dir_str, String sort_str, int pageSize, int startIndex, String type)
  {
    logger.info("Inside getUsersForPagination method");
    List userList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      logger.info("type" + type);
      Criteria outer = session.createCriteria(User.class).add(Restrictions.ne("userName", "admin")).add(Restrictions.eq("status", "A"));
      if ((!StringUtils.isNullOrEmpty(type)) && (type.equals("Vendor"))) {
        outer.add(Restrictions.eq("type", "Vendor"));
      } else if ((!StringUtils.isNullOrEmpty(type)) && (type.equals("Employee"))) {
        outer.add(Restrictions.eq("type", "Employee"));
      }
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      userList = outer.list();
      


      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUsersForPagination()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return userList;
  }
  
  public static Map getUserGroupsForPagination(User user, String groupname, String description, String userId, String dir_str, String sort_str, int pageSize, int startIndex)
  {
    logger.info("Inside getUserGroupsForPagination method");
    List usergrpList = new ArrayList();
    org.hibernate.Session session = null;
    int totaluser = 0;
    Map m = new HashMap();
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      Criteria outer = session.createCriteria(UserGroup.class).add(Restrictions.ne("status", "D"));
      outer.add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      if ((!StringUtils.isNullOrEmpty(groupname)) && (!groupname.equals("null"))) {
        outer.add(Restrictions.like("usergrpName", "%" + groupname + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(description)) && (!description.equals("null"))) {
        outer.add(Restrictions.like("usergrpDesc", "%" + description + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(userId)) && (!userId.equals("0")))
      {
        outer.createAlias("users", "users");
        outer.add(Restrictions.eq("users.userId", new Long(userId)));
        logger.info("Inside getUserGroupsForPagination method22222222");
      }
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      usergrpList = outer.list();
      
      m.put(Common.USER_GROUP_LIST, usergrpList);
      
      outer = session.createCriteria(UserGroup.class).add(Restrictions.eq("status", "A"));
      outer.add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      if ((!StringUtils.isNullOrEmpty(groupname)) && (!groupname.equals("null"))) {
        outer.add(Restrictions.like("usergrpName", "%" + groupname + "%"));
      }
      outer.setProjection(Projections.rowCount());
      totaluser = ((Integer)outer.list().get(0)).intValue();
      
      m.put(Common.USER_GROUP_COUNT, Integer.valueOf(totaluser));
      

      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserGroupsForPagination()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return m;
  }
  
  public static List getAllUsers()
  {
    logger.info("Inside getAllUsers method");
    List userList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      userList = session.createCriteria(User.class).add(Restrictions.eq("type", "Employee")).list();
      

      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllUsers()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return userList;
  }
  
  public static List getActiveAllUsers()
  {
    logger.info("Inside getActiveAllUsers method");
    List userList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      userList = session.createCriteria(User.class).add(Restrictions.eq("type", "Employee")).add(Restrictions.eq("status", "A")).list();
      

      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getActiveAllUsers()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return userList;
  }
  
  public static List getAllActiveUserNameEmail()
  {
    logger.info("Inside getAllActiveUserNameEmail method");
    
    List userList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      String sql = "select u.user_id, u.first_name, u.last_name , u.email_id, u.user_name from user_data u where u.type = :type and u.status = :status";
      Query query = session.createSQLQuery(sql);
      query.setString("type", "Employee");
      query.setString("status", "A");
      List userListtemp = query.list();
      logger.info("userListtemp.size()" + userListtemp.size());
      for (int i = 0; i < userListtemp.size(); i++)
      {
        User user = new User();
        Object[] obj = (Object[])userListtemp.get(i);
        BigInteger userId1 = (BigInteger)obj[0];
        long uid = userId1.longValue();
        user.setUserId(uid);
        user.setFirstName((String)obj[1]);
        user.setLastName((String)obj[2]);
        user.setEmailId((String)obj[3]);
        user.setUserName((String)obj[4]);
        userList.add(user);
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllActiveUserNameEmail()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return userList;
  }
  
  public static List getAllActiveVendors()
  {
    logger.info("Inside getAllActiveVendors method");
    List userList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      userList = session.createCriteria(User.class).add(Restrictions.eq("type", "Vendor")).add(Restrictions.eq("status", "A")).list();
      

      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllActiveVendors()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return userList;
  }
  
  public static List getEmailIds(List userids)
  {
    logger.info("Inside getEmailIds method");
    List userList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      String instmt = "";
      for (int i = 0; i < userids.size(); i++) {
        instmt = instmt + userids.get(i) + " ,";
      }
      instmt = instmt.substring(0, instmt.length() - 1);
      userList = session.createQuery("select emailId from User u where u.userId in ( " + instmt + " )").list();
      

      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getEmailIds()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return userList;
  }
  
  public static List getAllActiveUsers()
  {
    logger.info("Inside getAllUsers method");
    List userList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      userList = session.createCriteria(User.class).add(Restrictions.eq("type", "Employee")).add(Restrictions.eq("status", "A")).list();
      

      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllUsers()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return userList;
  }
  
  public static List getAllUsersByType(String type)
  {
    logger.info("Inside getAllUsersByType method");
    List userList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      Criteria outer = session.createCriteria(User.class).add(Restrictions.ne("userName", "admin")).add(Restrictions.eq("status", "A"));
      if ((!StringUtils.isNullOrEmpty(type)) && (type.equals("Vendor"))) {
        outer.add(Restrictions.eq("type", "Vendor"));
      } else if ((!StringUtils.isNullOrEmpty(type)) && (type.equals("Employee"))) {
        outer.add(Restrictions.eq("type", "Employee"));
      }
      userList = outer.list();
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllUsersByType()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return userList;
  }
  
  public List getAllUsersByTypeTx(String type, long superuserkey)
  {
    logger.info("Inside getAllUsersByTypeTx method");
    List userList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      Criteria outer = session.createCriteria(User.class).add(Restrictions.ne("userName", "admin")).add(Restrictions.eq("status", "A"));
      if ((!StringUtils.isNullOrEmpty(type)) && (type.equals("Vendor")))
      {
        outer.add(Restrictions.eq("type", "Vendor"));
        outer.add(Restrictions.eq("super_user_key", Long.valueOf(superuserkey)));
      }
      else if ((!StringUtils.isNullOrEmpty(type)) && (type.equals("Employee")))
      {
        outer.add(Restrictions.eq("type", "Employee"));
      }
      userList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllUsersByTypeTx()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return userList;
  }
  
  public static int getCountOfAllUsers(String type)
  {
    logger.info("Inside getCountofAllUsers method");
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      

      Criteria criteria = session.createCriteria(User.class).add(Restrictions.ne("userName", "admin")).add(Restrictions.eq("status", "A"));
      if ((!StringUtils.isNullOrEmpty(type)) && (type.equals("Vendor"))) {
        criteria.add(Restrictions.eq("type", "Vendor"));
      } else if ((!StringUtils.isNullOrEmpty(type)) && (type.equals("Employee"))) {
        criteria.add(Restrictions.eq("type", "Employee"));
      }
      criteria.setProjection(Projections.rowCount());
      totaluser = ((Integer)criteria.list().get(0)).intValue();
      System.out.println("totaluser" + totaluser);
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllUsers()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return totaluser;
  }
  
  public static List getUsersByCritera(User user, String finame, String laname, long orgId, long deptId, int start, int range)
  {
    logger.info("Inside getUsersByCritera method");
    List charList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      Criteria crit = session.createCriteria(User.class).add(Restrictions.eq("type", "Employee")).add(Restrictions.eq("status", "A"));
      crit.add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      if (orgId != 0L) {
        crit.add(Restrictions.eq("organization.orgId", new Long(orgId)));
      }
      if (deptId != 0L) {
        crit.add(Restrictions.eq("department.departmentId", new Long(deptId)));
      }
      if ((!StringUtils.isNullOrEmpty(finame)) && (!StringUtils.isNullOrEmpty(laname)))
      {
        Criterion fname = Restrictions.like("firstName", "%" + finame + "%");
        Criterion lname = Restrictions.like("lastName", "%" + laname + "%");
        LogicalExpression orExp = Restrictions.or(fname, lname);
        crit.add(orExp);
        crit.setFirstResult(start);
        crit.setMaxResults(range);
        charList = crit.list();
      }
      else if ((!StringUtils.isNullOrEmpty(finame)) && (StringUtils.isNullOrEmpty(laname)))
      {
        crit.add(Restrictions.like("firstName", "%" + finame + "%"));
        crit.setFirstResult(start);
        crit.setMaxResults(range);
        charList = crit.list();
      }
      else if ((StringUtils.isNullOrEmpty(finame)) && (!StringUtils.isNullOrEmpty(laname)))
      {
        crit.add(Restrictions.like("lastName", "%" + laname + "%"));
        crit.setFirstResult(start);
        crit.setMaxResults(range);
        charList = crit.list();
      }
      else
      {
        crit.setFirstResult(start);
        crit.setMaxResults(range);
        charList = crit.list();
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUsersByCritera()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return charList;
  }
  
  public List getAllHiringMgr(User user, long roleId)
  {
    logger.info("Inside getAllHiringMgr method");
    List hiringMgrList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      Criteria crit = session.createCriteria(User.class).add(Restrictions.eq("type", "Employee")).add(Restrictions.eq("status", "A")).add(Restrictions.eq("role.roleId", Long.valueOf(roleId))).add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      hiringMgrList = crit.list();
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUsersByCritera()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return hiringMgrList;
  }
  
  public List getAllRecruiter(User user, long roleId)
  {
    logger.info("Inside getAllHiringMgr method");
    List hiringMgrList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      Criteria crit = session.createCriteria(User.class).add(Restrictions.eq("type", "Employee")).add(Restrictions.eq("status", "A")).add(Restrictions.eq("role.roleId", Long.valueOf(roleId))).add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      hiringMgrList = crit.list();
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUsersByCritera()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return hiringMgrList;
  }
  
  public static List getUserGroupsByCritera(User user, String name, int start, int range)
  {
    logger.info("Inside getUserGroupsByCritera method");
    List charList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      Criteria crit = session.createCriteria(UserGroup.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key()))).add(Restrictions.like("usergrpName", "%" + name + "%"));
      crit.setFirstResult(start);
      crit.setMaxResults(range);
      charList = crit.list();
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUsersByCritera()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return charList;
  }
  
  public static List getVendorByCritera(long superUserKey, String finame, String laname, long countryId, long stateId, String city, int start, int range)
  {
    logger.info("Inside getVendorByCritera method");
    List charList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      Criteria crit = session.createCriteria(User.class).add(Restrictions.eq("type", "Vendor")).add(Restrictions.ne("status", "D"));
      if (countryId != 0L) {
        crit.add(Restrictions.eq("country.countryId", new Long(countryId)));
      }
      if (stateId != 0L) {
        crit.add(Restrictions.eq("state.stateId", new Long(stateId)));
      }
      crit.add(Restrictions.eq("super_user_key", Long.valueOf(superUserKey)));
      if ((!StringUtils.isNullOrEmpty(finame)) && (StringUtils.isNullOrEmpty(city)) && (StringUtils.isNullOrEmpty(laname)))
      {
        crit.add(Restrictions.like("firstName", "%" + finame + "%"));
        
        crit.setFirstResult(start);
        crit.setMaxResults(range);
        charList = crit.list();
      }
      else if ((StringUtils.isNullOrEmpty(finame)) && (!StringUtils.isNullOrEmpty(laname)) && (StringUtils.isNullOrEmpty(city)))
      {
        crit.add(Restrictions.like("lastName", "%" + laname + "%"));
        crit.setFirstResult(start);
        crit.setMaxResults(range);
        charList = crit.list();
      }
      else if ((StringUtils.isNullOrEmpty(finame)) && (StringUtils.isNullOrEmpty(laname)) && (!StringUtils.isNullOrEmpty(city)))
      {
        crit.add(Restrictions.like("city", "%" + city + "%"));
        crit.setFirstResult(start);
        crit.setMaxResults(range);
        charList = crit.list();
      }
      else if ((!StringUtils.isNullOrEmpty(finame)) && (!StringUtils.isNullOrEmpty(laname)) && (StringUtils.isNullOrEmpty(city)))
      {
        crit.add(Restrictions.like("firstName", "%" + finame + "%"));
        crit.add(Restrictions.like("lastName", "%" + laname + "%"));
        crit.setFirstResult(start);
        crit.setMaxResults(range);
        charList = crit.list();
      }
      else if ((!StringUtils.isNullOrEmpty(finame)) && (StringUtils.isNullOrEmpty(laname)) && (!StringUtils.isNullOrEmpty(city)))
      {
        crit.add(Restrictions.like("firstName", "%" + finame + "%"));
        crit.add(Restrictions.like("city", "%" + city + "%"));
        crit.setFirstResult(start);
        crit.setMaxResults(range);
        charList = crit.list();
      }
      else if ((StringUtils.isNullOrEmpty(finame)) && (!StringUtils.isNullOrEmpty(laname)) && (!StringUtils.isNullOrEmpty(city)))
      {
        crit.add(Restrictions.like("lastName", "%" + laname + "%"));
        crit.add(Restrictions.like("city", "%" + city + "%"));
        crit.setFirstResult(start);
        crit.setMaxResults(range);
        charList = crit.list();
      }
      else if ((!StringUtils.isNullOrEmpty(finame)) && (!StringUtils.isNullOrEmpty(laname)) && (!StringUtils.isNullOrEmpty(city)))
      {
        crit.add(Restrictions.like("firstName", "%" + finame + "%"));
        crit.add(Restrictions.like("lastName", "%" + laname + "%"));
        crit.add(Restrictions.like("city", "%" + city + "%"));
        crit.setFirstResult(start);
        crit.setMaxResults(range);
        charList = crit.list();
      }
      else
      {
        crit.setFirstResult(start);
        crit.setMaxResults(range);
        charList = crit.list();
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getVendorByCritera()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return charList;
  }
  
  public static List searchUsersForPagination(User user, String statuscri, String orgId, String departmentId, String projectId, String designationId, String firstname, String lastname, String userName, String emailid, String dir_str, String sort_str, int pageSize, int startIndex, String type)
  {
    logger.info("Inside searchUsersForPagination method");
    logger.info("Inside searchUsersForPagination method....................." + orgId);
    
    List charList = new ArrayList();
    logger.info("Porject Id :" + projectId);
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      Criteria outer = session.createCriteria(User.class).add(Restrictions.eq("type", "Employee")).add(Restrictions.ne("status", "D"));
      

      outer.add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      if ((!StringUtils.isNullOrEmpty(orgId)) && (!orgId.equals("0")) && (!orgId.equals("null"))) {
        outer.add(Restrictions.eq("organization.orgId", new Long(orgId)));
      }
      if ((!StringUtils.isNullOrEmpty(departmentId)) && (!departmentId.equals("0")) && (!departmentId.equals("null"))) {
        outer.add(Restrictions.eq("department.departmentId", new Long(departmentId)));
      }
      if ((!StringUtils.isNullOrEmpty(projectId)) && (!projectId.equals("0")) && (!projectId.equals("null"))) {
        outer.add(Restrictions.eq("projectcode.projectId", new Long(projectId)));
      }
      if ((!StringUtils.isNullOrEmpty(designationId)) && (!designationId.equals("0")) && (!designationId.equals("null"))) {
        outer.add(Restrictions.eq("designation.designationId", new Long(designationId)));
      }
      if ((!StringUtils.isNullOrEmpty(firstname)) && (!firstname.equals("null"))) {
        outer.add(Restrictions.like("firstName", "%" + firstname + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(lastname)) && (!lastname.equals("null"))) {
        outer.add(Restrictions.like("lastName", "%" + lastname + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(userName)) && (!userName.equals("null"))) {
        outer.add(Restrictions.like("userName", "%" + userName + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(emailid)) && (!emailid.equals("null"))) {
        outer.add(Restrictions.like("emailId", "%" + emailid + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(statuscri)) && (!statuscri.equals("null"))) {
        outer.add(Restrictions.eq("status", statuscri));
      }
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      charList = outer.list();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on searchUsersForPagination()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return charList;
  }
  
  public static List<String> getUserDataByQueryAutoSuggest(User user, String query)
  {
    logger.info("Inside getUserDataByQueryAutoSuggest method");
    List<String> newFullNameList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      logger.info("query" + query);
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      String sql = "select u.user_id, u.first_name, u.last_name , u.email_id, u.user_name from user_data u where (u.first_name like :fname or u.last_name like :lname) and u.status = :status and u.type= :type and u.super_user_key=:super_user_key";
      Query querynew = session.createSQLQuery(sql);
      querynew.setString("fname", "%" + query + "%");
      querynew.setString("lname", "%" + query + "%");
      querynew.setString("status", "A");
      querynew.setString("type", "Employee");
      querynew.setLong("super_user_key", user.getSuper_user_key());
      
      List userListtemp = querynew.list();
      for (int i = 0; i < userListtemp.size(); i++)
      {
        Object[] obj = (Object[])userListtemp.get(i);
        BigInteger userId1 = (BigInteger)obj[0];
        long uid = userId1.longValue();
        

        String name = (String)obj[1] + " " + (String)obj[2] + "|" + uid;
        newFullNameList.add(name);
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserDataByQueryAutoSuggest()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return newFullNameList;
  }
  
  public List<User> getUserDataByQueryAutoSuggestJSON(User user, String query)
  {
    logger.info("Inside getUserDataByQueryAutoSuggestJSON method");
    List<User> userList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      logger.info("query" + query);
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String sql = "select u.user_id, u.first_name, u.last_name , u.email_id, u.user_name from user_data u where (u.first_name like :fname or u.last_name like :lname) and u.status = :status and u.type= :type and u.super_user_key=:super_user_key";
      Query querynew = session.createSQLQuery(sql);
      querynew.setString("fname", "%" + query + "%");
      querynew.setString("lname", "%" + query + "%");
      querynew.setString("status", "A");
      querynew.setString("type", "Employee");
      querynew.setLong("super_user_key", user.getSuper_user_key());
      
      List userListtemp = querynew.list();
      for (int i = 0; i < userListtemp.size(); i++)
      {
        User userr = new User();
        Object[] obj = (Object[])userListtemp.get(i);
        BigInteger userId1 = (BigInteger)obj[0];
        long uid = userId1.longValue();
        
        String name = (String)obj[1] + " " + (String)obj[2];
        
        userr.setUserId(uid);
        userr.setFirstName((String)obj[1]);
        userr.setLastName((String)obj[2]);
        userList.add(userr);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserDataByQueryAutoSuggestJSON()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return userList;
  }
  
  public static List<String> getUserUserGroupDataByQueryAutoSuggest(User user, String query)
  {
    logger.info("Inside getUserUserGroupDataByQueryAutoSuggest method");
    List<String> newFullNameList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      logger.info("query" + query);
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      String sql = "select u.user_id, u.first_name, u.last_name , u.email_id, u.user_name from user_data u where (u.first_name like :fname or u.last_name like :lname) and u.status = :status and u.type= :type and u.super_user_key=:super_user_key";
      Query querynew = session.createSQLQuery(sql);
      querynew.setString("fname", "%" + query + "%");
      querynew.setString("lname", "%" + query + "%");
      querynew.setString("status", "A");
      querynew.setString("type", "Employee");
      querynew.setLong("super_user_key", user.getSuper_user_key());
      List userListtemp = querynew.list();
      for (int i = 0; i < userListtemp.size(); i++)
      {
        Object[] obj = (Object[])userListtemp.get(i);
        BigInteger userId1 = (BigInteger)obj[0];
        long uid = userId1.longValue();
        

        String name = "<img src='jsp/images/user.gif' />" + (String)obj[1] + " " + (String)obj[2] + "|" + uid;
        newFullNameList.add(name);
      }
      sql = "select u.user_group_id, u.user_group_name from user_group u where u.user_group_name like :gname and u.status = :status and u.super_user_key=:super_user_key";
      querynew = session.createSQLQuery(sql);
      querynew.setString("gname", "%" + query + "%");
      querynew.setString("status", "A");
      querynew.setLong("super_user_key", user.getSuper_user_key());
      
      List userListtempgroup = querynew.list();
      for (int i = 0; i < userListtempgroup.size(); i++)
      {
        Object[] obj = (Object[])userListtempgroup.get(i);
        BigInteger userId1 = (BigInteger)obj[0];
        long uid = userId1.longValue();
        
        String gid = "g" + uid;
        
        String name = "<img src='jsp/images/User-Group-icon.png' />" + (String)obj[1] + "|" + gid;
        newFullNameList.add(name);
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserUserGroupDataByQueryAutoSuggest()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return newFullNameList;
  }
  
  public List getUserUserGroupDataByQueryAutoSuggestJSONMulti(User user, String query)
  {
    logger.info("Inside getUserUserGroupDataByQueryAutoSuggestJSONMulti method");
    List newFullNameList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      logger.info("query" + query);
      session = session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String sql = "select u.user_id, u.first_name, u.last_name , u.email_id, u.user_name from user_data u where (u.first_name like :fname or u.last_name like :lname) and u.status = :status and u.type= :type and u.super_user_key=:super_user_key";
      Query querynew = session.createSQLQuery(sql);
      querynew.setString("fname", "%" + query + "%");
      querynew.setString("lname", "%" + query + "%");
      querynew.setString("status", "A");
      querynew.setString("type", "Employee");
      querynew.setLong("super_user_key", user.getSuper_user_key());
      List userListtemp = querynew.list();
      for (int i = 0; i < userListtemp.size(); i++)
      {
        Object[] obj = (Object[])userListtemp.get(i);
        BigInteger userId1 = (BigInteger)obj[0];
        long uid = userId1.longValue();
        String struserid = uid + "|" + "U";
        
        String name = "<img src='jsp/images/user.gif' />" + (String)obj[1] + " " + (String)obj[2];
        User usertemp = new User();
        usertemp.setUserName(struserid);
        usertemp.setFullName(name);
        newFullNameList.add(usertemp);
      }
      sql = "select u.user_group_id, u.user_group_name from user_group u where u.user_group_name like :gname and u.status = :status and u.super_user_key=:super_user_key";
      querynew = session.createSQLQuery(sql);
      querynew.setString("gname", "%" + query + "%");
      querynew.setString("status", "A");
      querynew.setLong("super_user_key", user.getSuper_user_key());
      
      List userListtempgroup = querynew.list();
      for (int i = 0; i < userListtempgroup.size(); i++)
      {
        Object[] obj = (Object[])userListtempgroup.get(i);
        BigInteger userId1 = (BigInteger)obj[0];
        long uid = userId1.longValue();
        
        String struserid = uid + "|" + "G";
        
        String name = "<img src='jsp/images/User-Group-icon.png' />" + (String)obj[1];
        User usertemp = new User();
        usertemp.setUserName(struserid);
        usertemp.setFullName(name);
        newFullNameList.add(usertemp);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserUserGroupDataByQueryAutoSuggestJSONMulti()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return newFullNameList;
  }
  
  public static List<String> getUserDataByQuery(String query)
  {
    logger.info("Inside getUserDataByQuery method");
    List userList = new ArrayList();
    List<String> newFullNameList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      logger.info("query" + query);
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      Criteria outer = session.createCriteria(User.class).add(Restrictions.eq("type", "Employee")).add(Restrictions.eq("status", "A"));
      


      Criterion f1name = Restrictions.like("firstName", "%" + query + "%");
      Criterion lname = Restrictions.like("lastName", "%" + query + "%");
      Disjunction disjunction = Restrictions.disjunction();
      disjunction.add(f1name);
      disjunction.add(lname);
      outer.add(disjunction);
      





      userList = outer.list();
      for (int i = 0; i < userList.size(); i++)
      {
        User usr = (User)userList.get(i);
        String fname = usr.getFirstName() + " " + usr.getLastName() + "|" + usr.getUserId();
        newFullNameList.add(fname);
        logger.info("fname" + fname);
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserDataByQuery()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return newFullNameList;
  }
  
  public static List searchUsersForPagination(String dir_str, String sort_str, int pageSize, int startIndex, String type)
  {
    logger.info("Inside searchUsersForPagination method");
    List charList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      Criteria outer = session.createCriteria(User.class).add(Restrictions.eq("type", "Employee")).add(Restrictions.ne("status", "D"));
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      charList = outer.list();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on searchUsersForPagination()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return charList;
  }
  
  public static List searchVendorsForPagination(User user, String searchpagedisplay, String statuscri, String countryId, String stateId, String city, String firstname, String lastname, String emailid, String dir_str, String sort_str, int pageSize, int startIndex, String type)
  {
    logger.info("Inside searchVendorsForPagination method");
    List charList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      Criteria outer = session.createCriteria(User.class).add(Restrictions.eq("type", "Vendor")).add(Restrictions.ne("status", "D"));
      outer.add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      if ((searchpagedisplay != null) && (searchpagedisplay.equals("yes")))
      {
        if ((!StringUtils.isNullOrEmpty(countryId)) && (!countryId.equals("0")))
        {
          outer.createAlias("country", "country");
          outer.add(Restrictions.eq("country.countryId", new Long(countryId)));
        }
        if ((!StringUtils.isNullOrEmpty(stateId)) && (!stateId.equals("0")))
        {
          outer.createAlias("state", "state");
          outer.add(Restrictions.eq("state.stateId", new Long(stateId)));
        }
        if (!StringUtils.isNullOrEmpty(city)) {
          outer.add(Restrictions.eq("city", city));
        }
        if (!StringUtils.isNullOrEmpty(firstname)) {
          outer.add(Restrictions.like("firstName", "%" + firstname + "%"));
        }
        if (!StringUtils.isNullOrEmpty(lastname)) {
          outer.add(Restrictions.like("lastName", "%" + lastname + "%"));
        }
        if (!StringUtils.isNullOrEmpty(emailid)) {
          outer.add(Restrictions.like("emailId", "%" + emailid + "%"));
        }
        if (!StringUtils.isNullOrEmpty(statuscri)) {
          outer.add(Restrictions.eq("status", statuscri));
        }
      }
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      charList = outer.list();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on searchVendorsForPagination()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return charList;
  }
  
  public static int searchVendorsCount(User user, String searchpagedisplay, String statuscri, String countryId, String stateId, String city, String firstname, String lastname, String type, String emailid)
  {
    logger.info("Inside searchVendorsCount method");
    List charList = new ArrayList();
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      Criteria outer = session.createCriteria(User.class).add(Restrictions.eq("type", "Vendor")).add(Restrictions.ne("status", "D"));
      outer.add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      if ((searchpagedisplay != null) && (searchpagedisplay.equals("yes")))
      {
        if ((!StringUtils.isNullOrEmpty(countryId)) && (!countryId.equals("0")))
        {
          outer.createAlias("country", "country");
          outer.add(Restrictions.eq("country.countryId", new Long(countryId)));
        }
        if ((!StringUtils.isNullOrEmpty(stateId)) && (!stateId.equals("0")))
        {
          outer.createAlias("state", "state");
          outer.add(Restrictions.eq("state.stateId", new Long(stateId)));
        }
        if (!StringUtils.isNullOrEmpty(city)) {
          outer.add(Restrictions.eq("city", city));
        }
        if (!StringUtils.isNullOrEmpty(firstname)) {
          outer.add(Restrictions.like("firstName", "%" + firstname + "%"));
        }
        if (!StringUtils.isNullOrEmpty(lastname)) {
          outer.add(Restrictions.like("lastName", "%" + lastname + "%"));
        }
        if (!StringUtils.isNullOrEmpty(statuscri)) {
          outer.add(Restrictions.eq("status", statuscri));
        }
        if (!StringUtils.isNullOrEmpty(emailid)) {
          outer.add(Restrictions.eq("emailId", emailid));
        }
      }
      outer.setProjection(Projections.rowCount());
      totaluser = ((Integer)outer.list().get(0)).intValue();
      

      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on searchVendorsCount", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return totaluser;
  }
  
  public static int searchUsersCount(User user, String statuscri, String orgId, String departmentId, String projectId, String designationId, String firstname, String lastname, String userName, String type, String emailid)
  {
    logger.info("Inside searchUsersCount method");
    
    List charList = new ArrayList();
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      Criteria outer = session.createCriteria(User.class).add(Restrictions.eq("type", "Employee")).add(Restrictions.ne("status", "D"));
      
      outer.add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      if ((!StringUtils.isNullOrEmpty(orgId)) && (!orgId.equals("0")) && (!orgId.equals("null"))) {
        outer.add(Restrictions.eq("organization.orgId", new Long(orgId)));
      }
      if ((!StringUtils.isNullOrEmpty(departmentId)) && (!departmentId.equals("0")) && (!departmentId.equals("null"))) {
        outer.add(Restrictions.eq("department.departmentId", new Long(departmentId)));
      }
      if ((!StringUtils.isNullOrEmpty(projectId)) && (!projectId.equals("0")) && (!projectId.equals("null"))) {
        outer.add(Restrictions.eq("projectcode.projectId", new Long(projectId)));
      }
      if ((!StringUtils.isNullOrEmpty(designationId)) && (!designationId.equals("0")) && (!designationId.equals("null"))) {
        outer.add(Restrictions.eq("designation.designationId", new Long(designationId)));
      }
      if ((!StringUtils.isNullOrEmpty(firstname)) && (!firstname.equals("null"))) {
        outer.add(Restrictions.like("firstName", "%" + firstname + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(lastname)) && (!lastname.equals("null"))) {
        outer.add(Restrictions.like("lastName", "%" + lastname + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(userName)) && (!userName.equals("null"))) {
        outer.add(Restrictions.like("userName", "%" + userName + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(emailid)) && (!emailid.equals("null"))) {
        outer.add(Restrictions.like("emailId", "%" + emailid + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(statuscri)) && (!statuscri.equals("null"))) {
        outer.add(Restrictions.eq("status", statuscri));
      }
      outer.setProjection(Projections.rowCount());
      totaluser = ((Integer)outer.list().get(0)).intValue();
      

      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on searchUsersCount()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return totaluser;
  }
  
  public static int searchUsersCount()
  {
    logger.info("Inside searchUsersCount method");
    List charList = new ArrayList();
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      Criteria outer = session.createCriteria(User.class).add(Restrictions.eq("type", "Employee")).add(Restrictions.ne("status", "D"));
      

      outer.setProjection(Projections.rowCount());
      totaluser = ((Integer)outer.list().get(0)).intValue();
      

      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on searchUsersCount()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return totaluser;
  }
  
  public static int getCountOfEmailTemplateByCriteria(String ename, long eId)
  {
    logger.info("Inside getCountOfEmailTemplateByCriteria method");
    List emailtempList = new ArrayList();
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      Criteria crit = session.createCriteria(EmailTemplates.class).add(Restrictions.eq("emailtemplatename", "%" + ename + "%"));
      if (eId != 0L) {
        crit.add(Restrictions.eq("eId", new Long(eId)));
      }
      if (!StringUtils.isNullOrEmpty(ename))
      {
        crit.add(Restrictions.like("emailtemplatename", "%" + ename + "%"));
        

        crit.setProjection(Projections.rowCount());
        totaluser = ((Integer)crit.list().get(0)).intValue();
      }
      else
      {
        crit.setProjection(Projections.rowCount());
        totaluser = ((Integer)crit.list().get(0)).intValue();
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfEmailTemplateByCriteria()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return totaluser;
  }
  
  public static List getEmailTEmplateByCritera(String ename, long eId, int start, int range)
  {
    logger.info("Inside getUsersByCritera method");
    List emailtempList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      Criteria crit = session.createCriteria(EmailTemplates.class).add(Restrictions.eq("emailtemplatename", "%" + ename + "%"));
      if (eId != 0L) {
        crit.add(Restrictions.eq("eId", new Long(eId)));
      }
      if (!StringUtils.isNullOrEmpty(ename))
      {
        crit.add(Restrictions.like("emailtemplatename", "%" + ename + "%"));
        crit.setFirstResult(start);
        crit.setMaxResults(range);
        emailtempList = crit.list();
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getEmailTEmplateByCritera()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return emailtempList;
  }
  
  public static int getCountOfUsersByCriteria(User user, String finame, String laname, long orgId, long deptId)
  {
    logger.info("Inside getCountOfUsersByCriteria method");
    List charList = new ArrayList();
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      Criteria crit = session.createCriteria(User.class).add(Restrictions.eq("type", "Employee")).add(Restrictions.eq("status", "A"));
      crit.add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      if (orgId != 0L) {
        crit.add(Restrictions.eq("organization.orgId", new Long(orgId)));
      }
      if (deptId != 0L) {
        crit.add(Restrictions.eq("department.departmentId", new Long(deptId)));
      }
      if ((!StringUtils.isNullOrEmpty(finame)) && (!StringUtils.isNullOrEmpty(laname)))
      {
        Criterion fname = Restrictions.like("firstName", "%" + finame + "%");
        Criterion lname = Restrictions.like("lastName", "%" + laname + "%");
        LogicalExpression orExp = Restrictions.or(fname, lname);
        crit.add(orExp);
        
        crit.setProjection(Projections.rowCount());
        totaluser = ((Integer)crit.list().get(0)).intValue();
      }
      else if ((!StringUtils.isNullOrEmpty(finame)) && (StringUtils.isNullOrEmpty(laname)))
      {
        crit.add(Restrictions.like("firstName", "%" + finame + "%"));
        
        crit.setProjection(Projections.rowCount());
        totaluser = ((Integer)crit.list().get(0)).intValue();
      }
      else if ((StringUtils.isNullOrEmpty(finame)) && (!StringUtils.isNullOrEmpty(laname)))
      {
        crit.add(Restrictions.like("lastName", "%" + laname + "%"));
        
        crit.setProjection(Projections.rowCount());
        totaluser = ((Integer)crit.list().get(0)).intValue();
      }
      else
      {
        crit.setProjection(Projections.rowCount());
        totaluser = ((Integer)crit.list().get(0)).intValue();
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfUsersByCriteria()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return totaluser;
  }
  
  public static int getCountOfUserGroupsByCriteria(User user, String name)
  {
    logger.info("Inside getCountOfUserGroupsByCriteria method");
    List charList = new ArrayList();
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      Criteria crit = session.createCriteria(UserGroup.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key()))).add(Restrictions.like("usergrpName", "%" + name + "%"));
      

      crit.setProjection(Projections.rowCount());
      totaluser = ((Integer)crit.list().get(0)).intValue();
      


      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfUserGroupsByCriteria()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return totaluser;
  }
  
  public static int getCountOfVendorByCriteria(long superUserKey, String finame, String laname, long countryId, long stateId, String city)
  {
    logger.info("Inside getCountOfVendorByCriteria method");
    List charList = new ArrayList();
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      Criteria crit = session.createCriteria(User.class).add(Restrictions.eq("type", "Vendor")).add(Restrictions.ne("status", "D"));
      if (countryId != 0L) {
        crit.add(Restrictions.eq("country.countryId", new Long(countryId)));
      }
      if (stateId != 0L)
      {
        crit.add(Restrictions.eq("state.stateId", new Long(stateId)));
        
        crit.add(Restrictions.eq("super_user_key", Long.valueOf(superUserKey)));
        


        Criterion fname = Restrictions.like("firstName", "%" + finame + "%");
        Criterion lname = Restrictions.like("lastName", "%" + laname + "%");
        LogicalExpression orExp = Restrictions.or(fname, lname);
        crit.add(orExp);
        
        crit.setProjection(Projections.rowCount());
        totaluser = ((Integer)crit.list().get(0)).intValue();
      }
      else if ((!StringUtils.isNullOrEmpty(finame)) && (StringUtils.isNullOrEmpty(city)) && (StringUtils.isNullOrEmpty(laname)))
      {
        crit.add(Restrictions.like("firstName", "%" + finame + "%"));
        
        crit.setProjection(Projections.rowCount());
        totaluser = ((Integer)crit.list().get(0)).intValue();
      }
      else if ((StringUtils.isNullOrEmpty(finame)) && (!StringUtils.isNullOrEmpty(laname)) && (StringUtils.isNullOrEmpty(city)))
      {
        crit.add(Restrictions.like("lastName", "%" + laname + "%"));
        
        crit.setProjection(Projections.rowCount());
        totaluser = ((Integer)crit.list().get(0)).intValue();
      }
      else if ((StringUtils.isNullOrEmpty(finame)) && (StringUtils.isNullOrEmpty(laname)) && (!StringUtils.isNullOrEmpty(city)))
      {
        crit.add(Restrictions.like("city", "%" + city + "%"));
        
        crit.setProjection(Projections.rowCount());
        totaluser = ((Integer)crit.list().get(0)).intValue();
      }
      else if ((!StringUtils.isNullOrEmpty(finame)) && (!StringUtils.isNullOrEmpty(laname)) && (StringUtils.isNullOrEmpty(city)))
      {
        crit.add(Restrictions.like("firstName", "%" + finame + "%"));
        crit.add(Restrictions.like("lastName", "%" + laname + "%"));
        
        crit.setProjection(Projections.rowCount());
        totaluser = ((Integer)crit.list().get(0)).intValue();
      }
      else if ((!StringUtils.isNullOrEmpty(finame)) && (StringUtils.isNullOrEmpty(laname)) && (!StringUtils.isNullOrEmpty(city)))
      {
        crit.add(Restrictions.like("firstName", "%" + finame + "%"));
        crit.add(Restrictions.like("city", "%" + city + "%"));
        
        crit.setProjection(Projections.rowCount());
        totaluser = ((Integer)crit.list().get(0)).intValue();
      }
      else if ((StringUtils.isNullOrEmpty(finame)) && (!StringUtils.isNullOrEmpty(laname)) && (!StringUtils.isNullOrEmpty(city)))
      {
        crit.add(Restrictions.like("lastName", "%" + laname + "%"));
        crit.add(Restrictions.like("city", "%" + city + "%"));
        
        crit.setProjection(Projections.rowCount());
        totaluser = ((Integer)crit.list().get(0)).intValue();
      }
      else if ((!StringUtils.isNullOrEmpty(finame)) && (!StringUtils.isNullOrEmpty(laname)) && (!StringUtils.isNullOrEmpty(city)))
      {
        crit.add(Restrictions.like("firstName", "%" + finame + "%"));
        crit.add(Restrictions.like("lastName", "%" + laname + "%"));
        crit.add(Restrictions.like("city", "%" + city + "%"));
        
        crit.setProjection(Projections.rowCount());
        totaluser = ((Integer)crit.list().get(0)).intValue();
      }
      else
      {
        crit.setProjection(Projections.rowCount());
        totaluser = ((Integer)crit.list().get(0)).intValue();
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfVendorByCriteria()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return totaluser;
  }
  
  public static User getVendorsByUsername(String username)
  {
    logger.info("username ********* " + username);
    logger.info("Inside getVendorsByUsername method");
    User user = null;
    org.hibernate.Session session = null;
    User emp = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      emp = (User)session.createCriteria(User.class).add(Restrictions.eq("userName", username)).add(Restrictions.eq("type", "Vendor")).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getVendorsByUsername()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return emp;
  }
  
  public User getUserCreatedFromApplicant(long applicantId)
  {
    logger.info("Inside getUserCreatedFromApplicant method");
    List userList = new ArrayList();
    org.hibernate.Session session = null;
    boolean success = false;
    User user = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      userList = session.createCriteria(User.class).add(Restrictions.eq("applicantId", Long.valueOf(applicantId))).list();
      if ((userList != null) && (userList.size() > 0)) {
        user = (User)userList.get(0);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserCreatedFromApplicant()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return user;
  }
  
  public void copyScreenFieldsToSuperUser(long superuserkey)
    throws Exception
  {
    logger.info("Inside copyScreenFieldsToSuperUser method");
    List sList = new ArrayList();
    org.hibernate.Session session = null;
    boolean success = false;
    User user = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      sList = session.createCriteria(ScreenFields.class).add(Restrictions.eq("super_user_key", new Long(0L))).list();
      for (int i = 0; i < sList.size(); i++)
      {
        ScreenFields sc = (ScreenFields)sList.get(i);
        ScreenFields scnew = new ScreenFields();
        
        scnew.screenCode = sc.screenCode;
        scnew.fieldcode = sc.fieldcode;
        scnew.fieldtype = sc.fieldtype;
        scnew.isvisible = sc.isvisible;
        scnew.issystem = sc.issystem;
        scnew.isMandatory = sc.isMandatory;
        scnew.status = sc.status;
        scnew.groupCode = sc.groupCode;
        scnew.sequenceId = sc.sequenceId;
        scnew.super_user_key = superuserkey;
        
        session.save(scnew);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on copyScreenFieldsToSuperUser()", e);
      throw e;
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void copyScreenFieldsToSuperUser(List<String> screenCodeList, long superuserkey)
    throws Exception
  {
    logger.info("Inside copyScreenFieldsToSuperUser method");
    List sList = new ArrayList();
    org.hibernate.Session session = null;
    boolean success = false;
    User user = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      



      sList = session.createCriteria(ScreenFields.class).add(Restrictions.eq("super_user_key", new Long(0L))).add(Restrictions.in("screenCode", screenCodeList)).list();
      for (int i = 0; i < sList.size(); i++)
      {
        ScreenFields sc = (ScreenFields)sList.get(i);
        ScreenFields scnew = new ScreenFields();
        
        scnew.screenCode = sc.screenCode;
        scnew.fieldcode = sc.fieldcode;
        scnew.fieldtype = sc.fieldtype;
        scnew.isvisible = sc.isvisible;
        scnew.issystem = sc.issystem;
        scnew.isMandatory = sc.isMandatory;
        scnew.status = sc.status;
        scnew.groupCode = sc.groupCode;
        scnew.sequenceId = sc.sequenceId;
        scnew.super_user_key = superuserkey;
        
        session.save(scnew);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on copyScreenFieldsToSuperUser()", e);
      throw e;
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void copyRolesToSuperUser(long superuserkey)
    throws Exception
  {
    logger.info("Inside copyRolesToSuperUser method");
    List sList = new ArrayList();
    org.hibernate.Session session = null;
    boolean success = false;
    User user = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      sList = session.createCriteria(Role.class).add(Restrictions.eq("super_user_key", new Long(0L))).list();
      for (int i = 0; i < sList.size(); i++)
      {
        Role role = (Role)sList.get(i);
        Role roleew = new Role();
        roleew.setRoleCode(role.getRoleCode());
        roleew.setRoleName(role.getRoleName());
        roleew.setRoleDesc(role.getRoleDesc());
        roleew.setCreatedBy(role.getCreatedBy());
        roleew.setCreatedDate(new Date());
        Set permissions = role.getPermissions();
        Set permissionsnew = new HashSet();
        if (permissions != null)
        {
          Iterator itr = permissions.iterator();
          while (itr.hasNext())
          {
            Permissions per = (Permissions)itr.next();
            permissionsnew.add(per);
          }
        }
        roleew.setPermissions(permissionsnew);
        roleew.setStatus("A");
        roleew.super_user_key = superuserkey;
        
        session.save(roleew);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on copyScreenFieldsToSuperUser()", e);
      throw e;
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void copyEthicityToSuperUser(long superuserkey)
    throws Exception
  {
    logger.info("Inside copyEthicityToSuperUser method");
    List sList = new ArrayList();
    org.hibernate.Session session = null;
    boolean success = false;
    User user = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      sList = session.createCriteria(EthnicRace.class).add(Restrictions.eq("super_user_key", new Long(0L))).list();
      for (int i = 0; i < sList.size(); i++)
      {
        EthnicRace eth = (EthnicRace)sList.get(i);
        EthnicRace ethnew = new EthnicRace();
        ethnew.setEthnicRaceName(eth.getEthnicRaceName());
        ethnew.setEthnicRaceDesc(eth.getEthnicRaceDesc());
        ethnew.setStatus("A");
        ethnew.setSuper_user_key(superuserkey);
        
        session.save(ethnew);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on copyEthicityToSuperUser()", e);
      throw e;
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void copyNotificationSettings(long superuserkey)
    throws Exception
  {
    logger.info("Inside copyNotificationSettings method");
    List sList = new ArrayList();
    org.hibernate.Session session = null;
    boolean success = false;
    User user = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      sList = session.createCriteria(EmailNotificationSetting.class).add(Restrictions.eq("super_user_key", new Long(0L))).list();
      for (int i = 0; i < sList.size(); i++)
      {
        EmailNotificationSetting eth = (EmailNotificationSetting)sList.get(i);
        EmailNotificationSetting ethnew = new EmailNotificationSetting();
        ethnew.setFunctionName(eth.getFunctionName());
        ethnew.setIsHiringMgr(eth.getIsHiringMgr());
        ethnew.setIsCurrentOwner(eth.getIsCurrentOwner());
        ethnew.setIsRecruiter(eth.getIsRecruiter());
        ethnew.setIsWatcher(eth.getIsWatcher());
        ethnew.setSuper_user_key(superuserkey);
        
        session.save(ethnew);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on copyNotificationSettings()", e);
      throw e;
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void copyVeteranDisability(long superuserkey)
    throws Exception
  {
    logger.info("Inside copyVeteranDisability method");
    List sList = new ArrayList();
    org.hibernate.Session session = null;
    boolean success = false;
    User user = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      sList = session.createCriteria(VeteranDisability.class).add(Restrictions.eq("super_user_key", new Long(0L))).list();
      for (int i = 0; i < sList.size(); i++)
      {
        VeteranDisability vth = (VeteranDisability)sList.get(i);
        VeteranDisability vthnew = new VeteranDisability();
        vthnew.setName(vth.getName());
        vthnew.setStatus("A");
        vthnew.setSuper_user_key(superuserkey);
        
        session.save(vthnew);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on copyVeteranDisability()", e);
      throw e;
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void copyWorkShift(long superuserkey)
    throws Exception
  {
    logger.info("Inside copyWorkShift method");
    List sList = new ArrayList();
    org.hibernate.Session session = null;
    boolean success = false;
    User user = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      sList = session.createCriteria(WorkShift.class).add(Restrictions.eq("super_user_key", new Long(0L))).list();
      for (int i = 0; i < sList.size(); i++)
      {
        WorkShift vth = (WorkShift)sList.get(i);
        WorkShift vthnew = new WorkShift();
        vthnew.setShiftName(vth.getShiftName());
        vthnew.setShiftDesc(vth.getShiftDesc());
        vthnew.setStatus("A");
        vthnew.setSuper_user_key(superuserkey);
        
        session.save(vthnew);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on copyWorkShift()", e);
      throw e;
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void copyJobType(long superuserkey)
    throws Exception
  {
    logger.info("Inside copyJobType method");
    List sList = new ArrayList();
    org.hibernate.Session session = null;
    boolean success = false;
    User user = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      sList = session.createCriteria(JobType.class).add(Restrictions.eq("super_user_key", new Long(0L))).list();
      for (int i = 0; i < sList.size(); i++)
      {
        JobType vth = (JobType)sList.get(i);
        JobType vthnew = new JobType();
        vthnew.setJobTypeName(vth.getJobTypeName());
        vthnew.setJobTypeDesc(vth.getJobTypeDesc());
        vthnew.setStatus("A");
        vthnew.setSuper_user_key(superuserkey);
        
        session.save(vthnew);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on copyJobType()", e);
      throw e;
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void copyCategoryLOV(long superuserkey)
    throws Exception
  {
    logger.info("Inside copyCategoryLOV method");
    List sList = new ArrayList();
    org.hibernate.Session session = null;
    boolean success = false;
    User user = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      sList = session.createCriteria(Category.class).add(Restrictions.eq("super_user_key", new Long(0L))).list();
      for (int i = 0; i < sList.size(); i++)
      {
        Category vth = (Category)sList.get(i);
        Category vthnew = new Category();
        vthnew.setCatName(vth.getCatName());
        vthnew.setCatDesc(vth.getCatDesc());
        vthnew.setStatus("A");
        vthnew.setSuper_user_key(superuserkey);
        
        session.save(vthnew);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on copyCategoryLOV()", e);
      throw e;
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void copyJobCategory(long superuserkey)
    throws Exception
  {
    logger.info("Inside copyJobCategory method");
    List sList = new ArrayList();
    org.hibernate.Session session = null;
    boolean success = false;
    User user = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      sList = session.createCriteria(JobCategory.class).add(Restrictions.eq("super_user_key", new Long(0L))).list();
      for (int i = 0; i < sList.size(); i++)
      {
        JobCategory vth = (JobCategory)sList.get(i);
        JobCategory vthnew = new JobCategory();
        vthnew.setJobCategoryName(vth.getJobCategoryName());
        vthnew.setJobCategoryDesc(vth.getJobCategoryDesc());
        vthnew.setStatus("A");
        vthnew.setSuper_user_key(superuserkey);
        
        session.save(vthnew);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on copyCategoryLOV()", e);
      throw e;
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void copyJobTitles(long superuserkey)
    throws Exception
  {
    logger.info("Inside copyJobTitles method");
    List sList = new ArrayList();
    org.hibernate.Session session = null;
    boolean success = false;
    User user = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      sList = session.createCriteria(Designations.class).add(Restrictions.eq("super_user_key", new Long(0L))).list();
      for (int i = 0; i < sList.size(); i++)
      {
        Designations vth = (Designations)sList.get(i);
        Designations vthnew = new Designations();
        vthnew.setDesignationName(vth.getDesignationName());
        vthnew.setDesignationCode(vth.getDesignationCode());
        vthnew.setStatus("A");
        vthnew.setSuper_user_key(superuserkey);
        
        session.save(vthnew);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on copyJobTitles()", e);
      throw e;
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public TalentPool saveTalentpool(TalentPool talentpool)
    throws Exception
  {
    logger.info("Inside saveTalentpool method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      session.save(talentpool);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveTags()", e);
      throw e;
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return talentpool;
  }
  
  public List getAllVendorList(User user)
  {
    logger.info("Inside getAllVendorList method");
    List vendorList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      Criteria crit = session.createCriteria(User.class).add(Restrictions.eq("type", "Vendor")).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      


      vendorList = crit.list();
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUsersByCritera()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return vendorList;
  }
}
