package com.dao;

import com.bean.Organization;
import com.bean.system.SystemRule;
import com.bean.system.SystemRuleUser;
import com.resources.Constant;
import com.util.StringUtils;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class RuleTXDAO
  extends HibernateDaoSupport
{
  protected static final Logger logger = Logger.getLogger(RuleTXDAO.class);
  
  public SystemRule getOrganizationRuleByType(long orgid, long deptId, String ruleType)
    throws Exception
  {
    logger.info("Inside getOrganizationRuleByType method");
    logger.info("Inside getOrganizationRuleByType method orgid" + orgid);
    logger.info("system.rule.approver.publisher.preference" + Constant.getValue("system.rule.approver.publisher.preference"));
    SystemRule systemrule = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

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
          logger.info("org" + org);
          
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
          logger.info("org" + org);
          if (org == null) {
            return systemrule;
          }
          orgid = org.getParent_org_id();
        }
      }
      logger.info("Rule for parent org" + systemrule);
    }
    catch (Exception e)
    {
      logger.info("Exception on getOrganizationRuleByType()", e);
      throw e;
    }
    return systemrule;
  }
  
  public static SystemRule getOrganizationRuleByTypeByNewSession(long orgid, long deptId, String ruleType)
    throws Exception
  {
    logger.info("Inside getOrganizationRuleByType method");
    logger.info("Inside getOrganizationRuleByType method orgid" + orgid);
    logger.info("system.rule.approver.publisher.preference" + Constant.getValue("system.rule.approver.publisher.preference"));
    SystemRule systemrule = null;
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      

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
          if (org == null) {
            return systemrule;
          }
          orgid = org.getParent_org_id();
        }
      }
      logger.info("Rule for parent org" + systemrule);
    }
    catch (Exception e)
    {
      logger.info("Exception on getOrganizationRuleByType()", e);
      throw e;
    }
    return systemrule;
  }
  
  public void deleteSystemRule(long ruleid)
  {
    logger.info("Inside deleteSystemRule method");
    String hql = "delete from SystemRuleUser where systemRuleId =:systemRuleId";
    Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
    query.setLong("systemRuleId", ruleid);
    int rowCount = query.executeUpdate();
  }
  
  public void updateSystemRule(SystemRule sysrule)
  {
    logger.info("Inside updateSystemRule method");
    getHibernateTemplate().update(sysrule);
  }
  
  public void saveSystemRule(SystemRule sysrule)
  {
    logger.info("Inside saveSystemRule method");
    getHibernateTemplate().save(sysrule);
  }
  
  public void saveSystemRuleUsers(SystemRuleUser sysruleuser)
  {
    logger.info("Inside saveSystemRuleUsers method");
    getHibernateTemplate().save(sysruleuser);
  }
}
