package com.dao;

import com.bean.ApplicantReferencee;
import com.bean.BudgetCode;
import com.bean.Department;
import com.bean.GroupCharacteristic;
import com.bean.JobGrade;
import com.bean.JobRequisition;
import com.bean.Organization;
import com.bean.ProjectCodes;
import com.bean.RefferalEmployee;
import com.bean.RefferalSchemeType;
import com.bean.SalaryPlan;
import com.bean.UrlEncodeData;
import com.bean.User;
import com.bean.UserGroup;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class LovOpsTXDAO
  extends HibernateDaoSupport
{
  protected static final Logger logger = Logger.getLogger(LovOpsTXDAO.class);
  
  public void deleteCharsList(List charlist)
  {
    logger.info("Inside deleteCharsList method");
    getHibernateTemplate().deleteAll(charlist);
  }
  
  public BudgetCode getBudgetCode(String id)
    throws Exception
  {
    logger.info("Inside getBudgetCode method");
    BudgetCode budgetCode = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      budgetCode = (BudgetCode)session.createCriteria(BudgetCode.class).add(Restrictions.eq("budgetId", new Long(id))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getBudgetCode()", e);
      throw e;
    }
    return budgetCode;
  }
  
  public SalaryPlan getSalaryPlan(long id)
    throws Exception
  {
    logger.info("Inside getSalaryPlan method");
    SalaryPlan sl = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      sl = (SalaryPlan)session.createCriteria(SalaryPlan.class).add(Restrictions.eq("salaryplanId", new Long(id))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getSalaryPlan()", e);
      throw e;
    }
    return sl;
  }
  
  public RefferalEmployee getRefferalEmployee(String id)
  {
    logger.info("Inside getRefferalEmployee method");
    User user = null;
    org.hibernate.Session session = null;
    RefferalEmployee emp = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      emp = (RefferalEmployee)session.createCriteria(RefferalEmployee.class).add(Restrictions.eq("employeeReferalId", new Long(id))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getRefferalEmployee()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return emp;
  }
  
  public List getTotalAllRequistionsByBudgetCodeId(long budgetcodeId)
    throws Exception
  {
    logger.info("Inside getTotalAllRequistionsByBudgetCodeId method");
    List tmplList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      Criteria outer = session.createCriteria(JobRequisition.class).add(Restrictions.eq("status", "Open")).add(Restrictions.ne("state", "In Approval-Rejected"));
      outer.createAlias("budgetcode", "budgetcode");
      outer.add(Restrictions.eq("budgetcode.budgetId", new Long(budgetcodeId)));
      tmplList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getTotalAllRequistionsByBudgetCodeId()", e);
      throw e;
    }
    return tmplList;
  }
  
  public ProjectCodes getProjectCode(long pjid)
  {
    logger.info("Inside updateGroupChar method");
    ProjectCodes projectCodes = (ProjectCodes)getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(ProjectCodes.class).add(Restrictions.eq("projectId", Long.valueOf(pjid))).uniqueResult();
    
    return projectCodes;
  }
  
  public UserGroup getUserGroup(long usrgrpid)
  {
    logger.info("Inside getUserGroup method");
    UserGroup usrgrp = (UserGroup)getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(UserGroup.class).add(Restrictions.eq("usergrpId", new Long(usrgrpid))).add(Restrictions.eq("status", "A")).uniqueResult();
    
    return usrgrp;
  }
  
  public UserGroup getUserGroupTx(long usrgrpid)
    throws Exception
  {
    UserGroup usrgrp = null;
    try
    {
      logger.info("Inside getUserGroupTx method");
      usrgrp = (UserGroup)getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(UserGroup.class).add(Restrictions.eq("usergrpId", new Long(usrgrpid))).add(Restrictions.eq("status", "A")).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserGroupTx()", e);
      throw e;
    }
    return usrgrp;
  }
  
  public Department getDepartmentDetails(long id)
  {
    Department department = (Department)getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Department.class).add(Restrictions.eq("departmentId", Long.valueOf(id))).uniqueResult();
    

    return department;
  }
  
  public Organization getOrganization(long id)
  {
    Organization org = (Organization)getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Organization.class).add(Restrictions.eq("orgId", Long.valueOf(id))).uniqueResult();
    

    return org;
  }
  
  public JobGrade getJobGradeDetails(long id)
  {
    JobGrade jobGrade = (JobGrade)getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(JobGrade.class).add(Restrictions.eq("jobgradeId", Long.valueOf(id))).uniqueResult();
    
    return jobGrade;
  }
  
  public void updateGroupChar(GroupCharacteristic grch)
  {
    logger.info("Inside updateGroupChar method");
    getHibernateTemplate().update(grch);
  }
  
  public UrlEncodeData saveeEncodeUrl(UrlEncodeData emtmpl)
  {
    logger.info("Inside saveeEncodeUrl method");
    User user = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.save(emtmpl);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveeEncodeUrl()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return emtmpl;
  }
  
  public ApplicantReferencee saveApplicantReferencee(ApplicantReferencee emtmpl)
  {
    logger.info("Inside saveApplicantReferencee method");
    getHibernateTemplate().save(emtmpl);
    
    return emtmpl;
  }
  
  public ApplicantReferencee updateApplicantReferencee(ApplicantReferencee emtmpl)
  {
    logger.info("Inside updateApplicantReferencee method");
    getHibernateTemplate().update(emtmpl);
    return emtmpl;
  }
  
  public void deleteApplicantReference(long apprefid)
  {
    logger.info("Inside deleteApplicantReference method");
    String hql = "delete from ApplicantReferencee where applicantReferenceId = :applicantReferenceId";
    Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
    query.setLong("applicantReferenceId", apprefid);
    
    int rowCount = query.executeUpdate();
  }
  
  public RefferalSchemeType getRefferalSchemeTypeDetails(String id)
  {
    logger.info("Inside getRefferalSchemeDetails method");
    RefferalSchemeType refferalschemetype = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      refferalschemetype = (RefferalSchemeType)session.createCriteria(RefferalSchemeType.class).add(Restrictions.eq("refferalSchemeTypeId", new Long(id))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getRefferalSchemeTypeDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return refferalschemetype;
  }
  
  public RefferalSchemeType getRefferalSchemeTypeByUOM(String uom)
  {
    logger.info("Inside getRefferalSchemeDetails method");
    RefferalSchemeType refferalschemetype = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      refferalschemetype = (RefferalSchemeType)session.createCriteria(RefferalSchemeType.class).add(Restrictions.eq("uom", uom)).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getRefferalSchemeTypeDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return refferalschemetype;
  }
}
