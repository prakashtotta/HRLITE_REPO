package com.dao;

import com.bean.User;
import com.bean.UserGroup;
import java.math.BigInteger;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class UserTXDAO
  extends HibernateDaoSupport
{
  protected static final Logger logger = Logger.getLogger(UserTXDAO.class);
  
  public User getUserByUserName(String username)
  {
    logger.info("Inside getUserByUserName method");
    User usr = (User)getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(User.class).add(Restrictions.eq("userName", username)).uniqueResult();
    return usr;
  }
  
  public User getUser(long userId)
  {
    logger.info("Inside getUser method");
    User usr = (User)getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(User.class).add(Restrictions.eq("userId", Long.valueOf(userId))).uniqueResult();
    return usr;
  }
  
  public User getUserFullNameEmailAndUserId(long userId)
  {
    logger.info("Inside getUserFullNameAndUserId method");
    User user = new User();
    
    String sql = "select u.user_id, u.first_name, u.last_name , u.email_id, u.user_name from user_data u where user_id = :user_id";
    Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
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
    

    return user;
  }
  
  public UserGroup getUserGroupNameandId(long usegroupid)
  {
    logger.info("Inside getUserGroupNameandId method");
    UserGroup usergroup = new UserGroup();
    
    String sql = "select u.user_group_id, u.user_group_name from user_group u where user_group_id = :user_group_id";
    Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
    query.setLong("user_group_id", usegroupid);
    List usergrpList = query.list();
    Object[] obj = (Object[])usergrpList.get(0);
    BigInteger userId1 = (BigInteger)obj[0];
    long uid = userId1.longValue();
    usergroup.setUsergrpId(uid);
    usergroup.setUsergrpName((String)obj[1]);
    

    return usergroup;
  }
  
  public UserGroup getUserGroupNameandIdById(long usegroupid)
    throws Exception
  {
    logger.info("Inside getUserGroupNameandIdById method" + usegroupid);
    UserGroup usergroup = new UserGroup();
    try
    {
      String sql = "select u.user_group_id, u.user_group_name from user_group u where u.user_group_id = :user_group_id";
      Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
      query.setLong("user_group_id", usegroupid);
      List usergrpList = query.list();
      if ((usergrpList != null) && (usergrpList.size() > 0))
      {
        Object[] obj = (Object[])usergrpList.get(0);
        BigInteger userId1 = (BigInteger)obj[0];
        long uid = userId1.longValue();
        usergroup.setUsergrpId(uid);
        usergroup.setUsergrpName((String)obj[1]);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserGroupNameandIdById()", e);
      throw e;
    }
    return usergroup;
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
}
