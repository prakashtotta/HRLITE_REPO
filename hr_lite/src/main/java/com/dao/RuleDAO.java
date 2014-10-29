package com.dao;

import com.bean.Organization;
import com.bean.User;
import com.bean.system.SystemRule;
import com.bean.system.SystemRuleUser;
import com.common.Common;
import com.resources.Constant;
import com.util.StringUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class RuleDAO
{
  protected static final Logger logger = Logger.getLogger(RuleDAO.class);
  
  public static SystemRule getOrganizationRuleByType(long orgid, long deptId, String ruleType)
  {
    logger.info("Inside getOrganizationRuleByType method");
    logger.info("Inside getOrganizationRuleByType method orgid" + orgid);
    logger.info("system.rule.approver.publisher.preference" + Constant.getValue("system.rule.approver.publisher.preference"));
    SystemRule systemrule = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      Criteria outer = session.createCriteria(SystemRule.class).add(Restrictions.eq("ruleType", ruleType)).add(Restrictions.eq("organization.orgId", Long.valueOf(orgid)));
      


      Criterion dept1 = Restrictions.eq("department.departmentId", new Long(deptId));
      Criterion dept2 = Restrictions.isNull("department.departmentId");
      LogicalExpression orExp = Restrictions.or(dept1, dept2);
      outer.add(orExp);
      if ((!StringUtils.isNullOrEmpty(Constant.getValue("system.rule.approver.publisher.preference"))) && (Constant.getValue("system.rule.approver.publisher.preference").equalsIgnoreCase("Department"))) {
        outer.addOrder(Order.desc("department.departmentId"));
      } else {
        outer.addOrder(Order.asc("department.departmentId"));
      }
      outer.add(Restrictions.eq("status", "A")).list();
      List lst = outer.list();
      if ((lst != null) && (lst.size() > 0))
      {
        systemrule = (SystemRule)lst.get(0);
        logger.info("Rule for own org" + systemrule.getRuleName());
      }
      logger.info("Rule for own org" + systemrule);
      if (systemrule == null)
      {
        boolean isrulefound = false;
        while (!isrulefound)
        {
          Organization org = (Organization)session.createCriteria(Organization.class).add(Restrictions.eq("orgId", Long.valueOf(orgid))).add(Restrictions.eq("status", "A")).uniqueResult();
          

          outer = session.createCriteria(SystemRule.class).add(Restrictions.eq("ruleType", ruleType)).add(Restrictions.eq("organization.orgId", Long.valueOf(orgid)));
          



          dept1 = Restrictions.eq("department.departmentId", new Long(deptId));
          dept2 = Restrictions.isNull("department.departmentId");
          orExp = Restrictions.or(dept1, dept2);
          outer.add(orExp);
          if ((!StringUtils.isNullOrEmpty(Constant.getValue("system.rule.approver.publisher.preference"))) && (Constant.getValue("system.rule.approver.publisher.preference").equalsIgnoreCase("Department"))) {
            outer.addOrder(Order.desc("department.departmentId"));
          } else {
            outer.addOrder(Order.asc("department.departmentId"));
          }
          outer.add(Restrictions.eq("status", "A")).list();
          
          lst = outer.list();
          if ((lst != null) && (lst.size() > 0))
          {
            systemrule = (SystemRule)lst.get(0);
            isrulefound = true;
            logger.info("Rule for parent org id" + orgid);
            logger.info("Rule for parent org" + systemrule.getRuleName());
          }
          orgid = org.getParent_org_id();
        }
      }
      logger.info("Rule for parent org" + systemrule);
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getOrganizationRuleByType()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return systemrule;
  }
  
  public static Map getSystemRulesForPagination(long orgId, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getSystemRulesForPagination method");
    List ruleList = new ArrayList();
    Map m = new HashMap();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      

      Criteria outer = session.createCriteria(SystemRule.class).add(Restrictions.eq("organization.orgId", Long.valueOf(orgId))).add(Restrictions.eq("status", "A"));
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      ruleList = outer.list();
      m.put(Common.ORGS_LIST, ruleList);
      

      Criteria outerc = session.createCriteria(SystemRule.class).add(Restrictions.eq("organization.orgId", Long.valueOf(orgId))).add(Restrictions.eq("status", "A"));
      
      outerc.setProjection(Projections.rowCount());
      int totaluser = ((Integer)outerc.list().get(0)).intValue();
      
      Integer totalsize = new Integer(totaluser);
      m.put(Common.ORGS_COUNT, totalsize);
      


      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getSystemRulesForPagination()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return m;
  }
  
  public static SystemRule getSystemRule(String id)
  {
    logger.info("Inside getSystemRule method");
    SystemRule sysrule = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      sysrule = (SystemRule)session.createCriteria(SystemRule.class).add(Restrictions.eq("systemRuleId", new Long(id))).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getSystemRule()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return sysrule;
  }
  
  public static SystemRule isSystemRuleExist(long orgid, long deptid, String ruletype)
  {
    logger.info("Inside isSystemRuleExist method");
    SystemRule sysrule = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      Criteria outer = session.createCriteria(SystemRule.class).add(Restrictions.eq("organization.orgId", Long.valueOf(orgid))).add(Restrictions.eq("ruleType", ruletype)).add(Restrictions.eq("status", "A"));
      if (deptid == 0L) {
        outer.add(Restrictions.isNull("department.departmentId"));
      } else {
        outer.add(Restrictions.eq("department.departmentId", Long.valueOf(deptid)));
      }
      sysrule = (SystemRule)outer.uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on isSystemRuleExist()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return sysrule;
  }
  
  public static List getSystemRuleUser(String id)
  {
    logger.info("Inside getSystemRuleUser method");
    List userList = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      userList = session.createCriteria(SystemRuleUser.class).add(Restrictions.eq("systemRuleId", new Long(id))).list();
      

      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getSystemRuleUser()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return userList;
  }
  
  public static SystemRule SaveSystemRule(SystemRule sysrule, String[] user)
  {
    logger.info("Inside SaveSystemRule method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.save(sysrule);
      if (user != null) {
        for (int i = 0; i < user.length; i++)
        {
          SystemRuleUser sysu = new SystemRuleUser();
          User u = new User();
          u.setUserId(Long.valueOf(user[i]).longValue());
          sysu.setUser(u);
          sysu.setLevelorder(1);
          sysu.setSystemRuleId(sysrule.getSystemRuleId());
          sysu.setIsGroup("N");
          session.save(sysu);
        }
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on SaveSystemRule()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return sysrule;
  }
  
  public static void deleteSystemRuleUser(long ruleid)
  {
    logger.info("Inside deleteSystemRuleUser method");
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      List systemRuleUserLIstlist = session.createCriteria(SystemRuleUser.class).add(Restrictions.eq("systemRuleId", Long.valueOf(ruleid))).list();
      for (int i = 0; i < systemRuleUserLIstlist.size(); i++)
      {
        SystemRuleUser sysruleuser = (SystemRuleUser)systemRuleUserLIstlist.get(i);
        session.delete(sysruleuser);
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteSystemRuleUser()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
  }
  
  public static SystemRule UpdateSystemRule(SystemRule sysrule, String[] user)
  {
    logger.info("Inside UpdateSystemRule method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.update(sysrule);
      for (int i = 0; i < user.length; i++)
      {
        SystemRuleUser sysu = new SystemRuleUser();
        User u = new User();
        u.setUserId(Long.valueOf(user[i]).longValue());
        sysu.setUser(u);
        sysu.setLevelorder(1);
        sysu.setSystemRuleId(sysrule.getSystemRuleId());
        sysu.setIsGroup("N");
        
        session.save(sysu);
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on UpdateSystemRule()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return sysrule;
  }
  
  public static SystemRule UpdateSystemRuledeletetime(SystemRule sysrule)
  {
    logger.info("Inside UpdateSystemRule method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.update(sysrule);
      

      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on UpdateSystemRule()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return sysrule;
  }
}
