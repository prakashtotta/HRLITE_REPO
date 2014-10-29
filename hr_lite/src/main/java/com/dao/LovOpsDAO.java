package com.dao;

import com.bean.Accomplishments;
import com.bean.BudgetCode;
import com.bean.Characteristic;
import com.bean.DeclinedResons;
import com.bean.Department;
import com.bean.Designations;
import com.bean.EvaluationTemplate;
import com.bean.GroupCharacteristic;
import com.bean.JobCode;
import com.bean.JobGrade;
import com.bean.JobRequisionTemplate;
import com.bean.JobRequisition;
import com.bean.Location;
import com.bean.OrganizationEmailTemplate;
import com.bean.ProjectCodes;
import com.bean.QuestionGroups;
import com.bean.QuestionOptions;
import com.bean.Questions;
import com.bean.RefferalBudgetCode;
import com.bean.RefferalRedemptionRule;
import com.bean.RefferalScheme;
import com.bean.RefferalSchemeType;
import com.bean.Role;
import com.bean.Round;
import com.bean.SalaryPlan;
import com.bean.User;
import com.bean.lov.LovList;
import com.bean.onboard.OnBoardingTaskAttributes;
import com.bean.onboard.OnBoardingTaskDefinitions;
import com.util.StringUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class LovOpsDAO
  extends HibernateDaoSupport
{
  protected static final Logger logger = Logger.getLogger(LovOpsDAO.class);
  
  public static ProjectCodes saveProjectCodes(ProjectCodes projectCodes)
  {
    logger.info("Inside saveProjectCodes method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.save(projectCodes);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on saveProjectCodes()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return projectCodes;
  }
  
  public static Accomplishments SaveAccomplishments(Accomplishments Accomplishments)
  {
    logger.info("Inside SaveAccomplishments method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.save(Accomplishments);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on SaveAccomplishments()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return Accomplishments;
  }
  
  public static OnBoardingTaskDefinitions SaveOnBoardingTaskDefi(OnBoardingTaskDefinitions OnBoardingTaskDefinitions)
  {
    logger.info("Inside SaveAccomplishments method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.save(OnBoardingTaskDefinitions);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on SaveAccomplishments()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return OnBoardingTaskDefinitions;
  }
  
  public static OrganizationEmailTemplate saveOrganizationEmailTemplate(OrganizationEmailTemplate orgemailtemplate)
  {
    logger.info("Inside saveOrganizationEmailTemplate method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.save(orgemailtemplate);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on saveOrganizationEmailTemplate()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return orgemailtemplate;
  }
  
  public static void saveOrganizationEmailTemplateList(List orgemailtemplateList)
  {
    logger.info("Inside saveOrganizationEmailTemplate method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      for (int i = 0; i < orgemailtemplateList.size(); i++)
      {
        OrganizationEmailTemplate tmpl = (OrganizationEmailTemplate)orgemailtemplateList.get(i);
        session.save(tmpl);
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on saveOrganizationEmailTemplate()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
  }
  
  public static ProjectCodes updateProjectCode(ProjectCodes projectCodes)
  {
    logger.info("Inside updateProjectCode method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.saveOrUpdate(projectCodes);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on updateProjectCode()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return projectCodes;
  }
  
  public static OrganizationEmailTemplate updateOrganizationEmailTemplate(OrganizationEmailTemplate orgemailtemplate)
  {
    logger.info("Inside updateOrganizationEmailTemplate method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.saveOrUpdate(orgemailtemplate);
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on updateOrganizationEmailTemplate()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return orgemailtemplate;
  }
  
  public static OrganizationEmailTemplate getOrgEmailTemplate(String id)
  {
    logger.info("Inside getOrgEmailTemplate method");
    OrganizationEmailTemplate orgemailtemplate = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      orgemailtemplate = (OrganizationEmailTemplate)session.createCriteria(OrganizationEmailTemplate.class).add(Restrictions.eq("orgemailtemplid", new Long(id))).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getOrgEmailTemplate()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return orgemailtemplate;
  }
  
  public static OrganizationEmailTemplate getOrgEmailTemplate(long orgid, String functionType)
  {
    logger.info("Inside getOrgEmailTemplate method");
    OrganizationEmailTemplate orgemailtemplate = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      orgemailtemplate = (OrganizationEmailTemplate)session.createCriteria(OrganizationEmailTemplate.class).createAlias("organisation", "organisation").add(Restrictions.eq("organisation.orgId", Long.valueOf(orgid))).add(Restrictions.eq("functiontype", functionType)).uniqueResult();
      


      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getOrgEmailTemplate()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return orgemailtemplate;
  }
  
  public static DeclinedResons getDeclinedReasonById(int offerdeclinedreasonId)
  {
    logger.info("Inside getDeclinedReasonById method");
    DeclinedResons reason = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      reason = (DeclinedResons)session.createCriteria(DeclinedResons.class).add(Restrictions.eq("offerdeclinedreasonId", Integer.valueOf(offerdeclinedreasonId))).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getDeclinedReasonById()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return reason;
  }
  
  public static Role getRole(String id)
  {
    logger.info("Inside getRole method");
    Role role = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      role = (Role)session.createCriteria(Role.class).add(Restrictions.eq("roleId", new Long(id))).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getRole()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return role;
  }
  
  public static Role getRoleByCode(String code, long super_user_key)
  {
    logger.info("Inside getRoleByCode method");
    Role role = null;
    List rlist = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      rlist = session.createCriteria(Role.class).add(Restrictions.eq("roleCode", code)).add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key))).list();
      if ((rlist != null) && (rlist.size() > 0)) {
        role = (Role)rlist.get(0);
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getRoleByCode()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return role;
  }
  
  public static JobGrade getJobGradeDetails(String id)
  {
    logger.info("Inside getJobGradeDetails method");
    JobGrade jobGrade = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      jobGrade = (JobGrade)session.createCriteria(JobGrade.class).add(Restrictions.eq("jobgradeId", new Long(id))).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getJobGradeDetails()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return jobGrade;
  }
  
  public RefferalRedemptionRule getRefferalRuleDetails(String id)
  {
    logger.info("Inside getRefferalRuleDetails method");
    RefferalRedemptionRule rule = null;
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      logger.info("Inside getRefferalRuleDetails method session" + session);
      rule = (RefferalRedemptionRule)session.createCriteria(RefferalRedemptionRule.class).add(Restrictions.eq("ruleId", Integer.valueOf(new Integer(id).intValue()))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getRefferalRuleDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return rule;
  }
  
  public RefferalRedemptionRule getRefferalRuleDetails(int creditAfterdays, String critera, long superUserKey)
  {
    logger.info("Inside getRefferalRuleDetails method");
    RefferalRedemptionRule rule = null;
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      logger.info("Inside getRefferalRuleDetails method session" + session);
      rule = (RefferalRedemptionRule)session.createCriteria(RefferalRedemptionRule.class).add(Restrictions.eq("creditAfterdays", Integer.valueOf(creditAfterdays))).add(Restrictions.eq("criteria", critera)).add(Restrictions.eq("super_user_key", Long.valueOf(superUserKey))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getRefferalRuleDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return rule;
  }
  
  public static Accomplishments getAccomplishmentDetails(String id)
  {
    logger.info("Inside getAccomplishmentDetails method");
    Accomplishments accomplishments = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      accomplishments = (Accomplishments)session.createCriteria(Accomplishments.class).add(Restrictions.eq("accId", new Long(id))).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAccomplishmentDetails()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return accomplishments;
  }
  
  public static ProjectCodes getProjectCodesDetails(String id)
  {
    logger.info("Inside getProjectCodesDetails method");
    ProjectCodes projectCodes = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      projectCodes = (ProjectCodes)session.createCriteria(ProjectCodes.class).add(Restrictions.eq("projectId", new Long(id))).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getProjectCodesDetails()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return projectCodes;
  }
  
  public static Department getDepartmentDetails(String id)
  {
    logger.info("Inside getDepartmentDetails method");
    Department department = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      department = (Department)session.createCriteria(Department.class).add(Restrictions.eq("departmentId", new Long(id))).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getDepartmentDetails()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return department;
  }
  
  public static Department getDepartmentDetailsByCode(String code, long orgId)
  {
    logger.info("Inside getDepartmentDetailsByCode method");
    Department department = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      department = (Department)session.createCriteria(Department.class).add(Restrictions.eq("departmentCode", code)).createAlias("organization", "organization").add(Restrictions.eq("organization.orgId", Long.valueOf(orgId))).uniqueResult();
      

      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getDepartmentDetailsByCode()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return department;
  }
  
  public static Department getDepartmentDetailsByDeptIdAndOrdId(long deptId, long orgId)
  {
    logger.info("Inside getDepartmentDetailsByDeptIdAndOrdId method");
    Department department = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      department = (Department)session.createCriteria(Department.class).add(Restrictions.eq("departmentId", Long.valueOf(deptId))).createAlias("organization", "organization").add(Restrictions.eq("organization.orgId", Long.valueOf(orgId))).uniqueResult();
      


      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getDepartmentDetailsByDeptIdAndOrdId()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return department;
  }
  
  public static ProjectCodes getProjectCodeByProjectCodeIdAndDeptIdAndOrdId(long projectcodeId, long deptId, long orgId)
  {
    logger.info("Inside getProjectCodeByProjectCodeIdAndDeptIdAndOrdId method");
    ProjectCodes pj = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      pj = (ProjectCodes)session.createCriteria(ProjectCodes.class).add(Restrictions.eq("projectId", Long.valueOf(projectcodeId))).createAlias("department", "department").add(Restrictions.eq("department.departmentId", Long.valueOf(deptId))).createAlias("organization", "organization").add(Restrictions.eq("organization.orgId", Long.valueOf(orgId))).uniqueResult();
      



      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getProjectCodeByProjectCodeIdAndDeptIdAndOrdId()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return pj;
  }
  
  public static ProjectCodes getProjectCodeByProjectCodeAndDeptIdAndOrdId(String projectcode, long deptId, long orgId)
  {
    logger.info("Inside getProjectCodeByProjectCodeAndDeptIdAndOrdId method");
    ProjectCodes pj = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      pj = (ProjectCodes)session.createCriteria(ProjectCodes.class).add(Restrictions.eq("projCode", projectcode)).createAlias("department", "department").add(Restrictions.eq("department.departmentId", Long.valueOf(deptId))).createAlias("organization", "organization").add(Restrictions.eq("organization.orgId", Long.valueOf(orgId))).uniqueResult();
      



      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getProjectCodeByProjectCodeAndDeptIdAndOrdId()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return pj;
  }
  
  public static Department saveDepartment(Department department)
  {
    logger.info("Inside saveDepartment method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.save(department);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on saveDepartment()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return department;
  }
  
  public static Department updateDepartment(Department department)
  {
    logger.info("Inside updateDepartment method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.saveOrUpdate(department);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on updateDepartment()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return department;
  }
  
  public static BudgetCode saveBudgetCode(BudgetCode budgetCode)
  {
    logger.info("Inside saveBudgetCode method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.save(budgetCode);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on saveBudgetCode()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return budgetCode;
  }
  
  public static RefferalBudgetCode saveRefferalBudgetCode(RefferalBudgetCode refferalbudgetCode)
  {
    logger.info("Inside saveRefferalBudgetCode method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.save(refferalbudgetCode);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on saveRefferalBudgetCode()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return refferalbudgetCode;
  }
  
  public static Questions getQuestionsDetails(String id)
  {
    logger.info("Inside getQuestionsDetails method");
    Questions que = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      que = (Questions)session.createCriteria(Questions.class).add(Restrictions.eq("questionId", new Long(id))).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getRole()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return que;
  }
  
  public static QuestionGroups getAllQuestionsByQuestionGroup(long id)
  {
    logger.info("Inside getQuestionsByQuestionGroup method");
    
    QuestionGroups questiongroup = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      questiongroup = (QuestionGroups)session.createCriteria(QuestionGroups.class).add(Restrictions.eq("questiongroupId", Long.valueOf(id))).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getQuestionsByQuestionGroup()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return questiongroup;
  }
  
  public static Questions updateQuestions(Questions question, List selecteoption, String correctans)
  {
    logger.info("Inside updateQuestions method");
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.update(question);
      if (((StringUtils.compare(question.getTypeVal(), "radio")) || (StringUtils.compare(question.getTypeVal(), "dropdown")) || (StringUtils.compare(question.getTypeVal(), "checkbox"))) && 
        (selecteoption.size() > 0))
      {
        Iterator itr = selecteoption.iterator();
        while (itr.hasNext())
        {
          QuestionOptions queoption = (QuestionOptions)itr.next();
          QuestionOptions queoption1 = new QuestionOptions();
          queoption1.setQnsoptId(queoption.getQnsoptId());
          queoption1.setQuestionId(question.getQuestionId());
          logger.info("dddddddddddddddddddddd" + question.getQuestionId());
          queoption1.setQuestionOptValue(String.valueOf(queoption.getQuestionOptValue()));
          if (StringUtils.compare(queoption.getQuestionOptValue(), String.valueOf(correctans))) {
            queoption1.setIscorrect(1);
          } else {
            queoption1.setIscorrect(0);
          }
          session.save(queoption1);
        }
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on updateQuestions()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return question;
  }
  
  public static BudgetCode updateBudgetCode(BudgetCode budgetCode)
  {
    logger.info("Inside updateBudgetCode method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.saveOrUpdate(budgetCode);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on updateBudgetCode()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return budgetCode;
  }
  
  public static RefferalBudgetCode updateRefferalBudgetCode(RefferalBudgetCode refferalbudgetCode)
  {
    logger.info("Inside updateRefferalBudgetCode method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.saveOrUpdate(refferalbudgetCode);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on updateRefferalBudgetCode()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return refferalbudgetCode;
  }
  
  public static EvaluationTemplate updateEvTemplate(EvaluationTemplate evtmpl)
  {
    logger.info("Inside updateEvTemplate method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.saveOrUpdate(evtmpl);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on updateEvTemplate()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return evtmpl;
  }
  
  public static JobGrade saveJobGrade(JobGrade jobGrade)
  {
    logger.info("Inside saveJobGrade method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.save(jobGrade);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on saveJobGrade()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return jobGrade;
  }
  
  public static RefferalRedemptionRule saveRule(RefferalRedemptionRule refferalRedemptionRule)
  {
    logger.info("Inside saveRule method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.save(refferalRedemptionRule);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on saveRule()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return refferalRedemptionRule;
  }
  
  public static Location getLocationDetails(String id)
  {
    logger.info("Inside getLocationDetails method");
    Location location = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      location = (Location)session.createCriteria(Location.class).add(Restrictions.eq("locationId", new Long(id))).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getLocationDetails()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return location;
  }
  
  public static Designations getDesignationDetails(String id)
  {
    logger.info("Inside getDesignationDetails method");
    Designations designation = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      designation = (Designations)session.createCriteria(Designations.class).add(Restrictions.eq("designationId", new Long(id))).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getDesignationDetails()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return designation;
  }
  
  public static Location updateLocation(Location location)
  {
    logger.info("Inside updateLocation method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.saveOrUpdate(location);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on updateLocation()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return location;
  }
  
  public static SalaryPlan saveSalary(SalaryPlan salaryPlan)
  {
    logger.info("Inside saveSalary method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.save(salaryPlan);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on saveSalary()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return salaryPlan;
  }
  
  public static RefferalSchemeType saveReffaralSchemeType(RefferalSchemeType refferalschemetype)
  {
    logger.info("Inside saveReffaralSchemeType method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.save(refferalschemetype);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on saveReffaralSchemeType()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return refferalschemetype;
  }
  
  public static RefferalScheme saveReffaralScheme(RefferalScheme refferalscheme)
  {
    logger.info("Inside saveReffaralScheme method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.save(refferalscheme);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on saveReffaralScheme()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return refferalscheme;
  }
  
  public static SalaryPlan getSalary(String id)
  {
    logger.info("Inside getSalary method");
    SalaryPlan salaryPlan = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      salaryPlan = (SalaryPlan)session.createCriteria(SalaryPlan.class).add(Restrictions.eq("salaryplanId", new Long(id))).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getSalary()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return salaryPlan;
  }
  
  public static SalaryPlan deleteSalary(SalaryPlan salaryPlan)
  {
    logger.info("Inside deleteSalary method");
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.delete(salaryPlan);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteSalary()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return salaryPlan;
  }
  
  public static void deleteOrganizationEmailTemplateMapping(List orgemailtemplateList)
  {
    logger.info("Inside deleteSalary method");
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      for (int i = 0; i < orgemailtemplateList.size(); i++)
      {
        OrganizationEmailTemplate tmpl = (OrganizationEmailTemplate)orgemailtemplateList.get(i);
        session.delete(tmpl);
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteSalary()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
  }
  
  public static SalaryPlan getSalaryDetails(String id)
  {
    logger.info("Inside getSalaryDetails method");
    SalaryPlan salaryPlan = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      salaryPlan = (SalaryPlan)session.createCriteria(SalaryPlan.class).add(Restrictions.eq("salaryplanId", new Long(id))).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getRole()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return salaryPlan;
  }
  
  public static RefferalSchemeType getRefferalSchemeTypeDetails(String id)
  {
    logger.info("Inside getRefferalSchemeDetails method");
    RefferalSchemeType refferalschemetype = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      refferalschemetype = (RefferalSchemeType)session.createCriteria(RefferalSchemeType.class).add(Restrictions.eq("refferalSchemeTypeId", new Long(id))).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getRefferalSchemeTypeDetails()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return refferalschemetype;
  }
  
  public static RefferalSchemeType getRefferalSchemeTypeByUOM(String uom)
  {
    logger.info("Inside getRefferalSchemeDetails method");
    RefferalSchemeType refferalschemetype = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      refferalschemetype = (RefferalSchemeType)session.createCriteria(RefferalSchemeType.class).add(Restrictions.eq("uom", uom)).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getRefferalSchemeTypeDetails()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return refferalschemetype;
  }
  
  public static RefferalScheme getRefferalSchemeDetails(String id)
  {
    logger.info("Inside getRefferalSchemeDetails method");
    RefferalScheme refferalscheme = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      refferalscheme = (RefferalScheme)session.createCriteria(RefferalScheme.class).add(Restrictions.eq("refferalScheme_Id", new Long(id))).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getRefferalSchemeDetails()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return refferalscheme;
  }
  
  public static SalaryPlan updateSalary(SalaryPlan salaryPlan)
  {
    logger.info("Inside updateSalary method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.saveOrUpdate(salaryPlan);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on saveSalary()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return salaryPlan;
  }
  
  public static RefferalSchemeType updateRefferalSchemeType(RefferalSchemeType refferalscheme)
  {
    logger.info("Inside updateRefferalSchemeType method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.saveOrUpdate(refferalscheme);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on updateRefferalSchemeType()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return refferalscheme;
  }
  
  public static RefferalScheme updateRefferalScheme(RefferalScheme refferalscheme)
  {
    logger.info("Inside updateRefferalScheme method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.saveOrUpdate(refferalscheme);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on updateRefferalScheme()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return refferalscheme;
  }
  
  public static Location saveLocation(Location location)
  {
    logger.info("Inside saveLocation method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.save(location);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on saveLocation()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return location;
  }
  
  public static LovList saveLovs(LovList LovList)
  {
    logger.info("Inside saveLovs method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.save(LovList);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on saveLovs()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return LovList;
  }
  
  public static LovList deleteLovlistcode(LovList LovList)
  {
    logger.info("Inside deleteLovlistcode method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.delete(LovList);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteLovlistcode()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return LovList;
  }
  
  public static LovList updateLovs(LovList LovList)
  {
    logger.info("Inside updateLovs method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.update(LovList);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on updateLovs()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return LovList;
  }
  
  public static Designations saveDesignation(Designations designations)
  {
    logger.info("Inside saveDesignation method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.save(designations);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on saveDesignation()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return designations;
  }
  
  public static Designations updateDesignation(Designations designation)
  {
    logger.info("Inside updateDesignation method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.saveOrUpdate(designation);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on updateDesignation()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return designation;
  }
  
  public static JobGrade updateJobGrade(JobGrade jobGrade)
  {
    logger.info("Inside updateJobGrade method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.saveOrUpdate(jobGrade);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on updateJobGrade()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return jobGrade;
  }
  
  public static RefferalRedemptionRule updateRefferalRule(RefferalRedemptionRule refferalRedemptionRule)
  {
    logger.info("Inside updateRefferalRule method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.saveOrUpdate(refferalRedemptionRule);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on updateRefferalRule()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return refferalRedemptionRule;
  }
  
  public static Accomplishments updateAccomplishment(Accomplishments accomp)
  {
    logger.info("Inside updateAccomplishment method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.saveOrUpdate(accomp);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on updateAccomplishment()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return accomp;
  }
  
  public static JobCode updateJobCode(JobCode jobCode)
  {
    logger.info("Inside updateJobCode method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.saveOrUpdate(jobCode);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on updateJobCode()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return jobCode;
  }
  
  public static JobCode getJobCodeDetails(String id)
  {
    logger.info("Inside getJobCodeDetails method");
    JobCode jobCode = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      jobCode = (JobCode)session.createCriteria(JobCode.class).add(Restrictions.eq("jobcodeId", new Long(id))).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getJobCodeDetails()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return jobCode;
  }
  
  public static JobCode saveJobCode(JobCode jobCode)
  {
    logger.info("Inside saveJobCode method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.save(jobCode);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on saveJobCode()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return jobCode;
  }
  
  public static BudgetCode getBudgetCode(String id)
  {
    logger.info("Inside getBudgetCode method");
    BudgetCode budgetCode = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      budgetCode = (BudgetCode)session.createCriteria(BudgetCode.class).add(Restrictions.eq("budgetId", new Long(id))).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getBudgetCode()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return budgetCode;
  }
  
  public static RefferalBudgetCode getRefferalBudgetCode(String id)
  {
    logger.info("Inside getBudgetCode method");
    RefferalBudgetCode refferalbudgetCode = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      refferalbudgetCode = (RefferalBudgetCode)session.createCriteria(RefferalBudgetCode.class).add(Restrictions.eq("ref_budgetId", new Long(id))).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getRefferalBudgetCode()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return refferalbudgetCode;
  }
  
  public static Characteristic getChar(String id)
  {
    logger.info("Inside getChar method");
    Characteristic chardata = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      chardata = (Characteristic)session.createCriteria(Characteristic.class).add(Restrictions.eq("charId", new Long(id))).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getChar()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return chardata;
  }
  
  public static GroupCharacteristic getGroupChar(String id)
  {
    logger.info("Inside getGroupChar method");
    GroupCharacteristic grp = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      grp = (GroupCharacteristic)session.createCriteria(GroupCharacteristic.class).add(Restrictions.eq("groupCharId", new Long(id))).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getGroupChar()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return grp;
  }
  
  public static Role saveUpdateRole(Role role)
  {
    logger.info("Inside saveUpdateRole method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.saveOrUpdate(role);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on saveUpdateRole()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return role;
  }
  
  public static Role uodateRole(Role role)
  {
    logger.info("Inside uodateRole method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.update(role);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on uodateRole()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return role;
  }
  
  public static Role saveRole(Role role)
  {
    logger.info("Inside saveRole method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.save(role);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on saveRole()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return role;
  }
  
  public static Role deleteRole(Role role)
  {
    logger.info("Inside deleteRole method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.delete(role);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteRole()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return role;
  }
  
  public static Characteristic saveChar(Characteristic ch)
  {
    logger.info("Inside saveChar method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.save(ch);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on saveChar()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return ch;
  }
  
  public static GroupCharacteristic saveGroupChar(GroupCharacteristic gp)
  {
    logger.info("Inside saveGroupChar method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.save(gp);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on saveGroupChar()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return gp;
  }
  
  public static GroupCharacteristic updateGroupChar(GroupCharacteristic gp)
  {
    logger.info("Inside updateGroupChar method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      session.update(gp);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on updateGroupChar()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return gp;
  }
  
  public static Characteristic updateChar(Characteristic gp)
  {
    logger.info("Inside updateChar method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      session.update(gp);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on updateChar()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return gp;
  }
  
  public static EvaluationTemplate saveEvaluationTmpl(EvaluationTemplate gp)
  {
    logger.info("Inside saveEvaluationTmpl method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.save(gp);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on saveEvaluationTmpl()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return gp;
  }
  
  public static Round saveRound(Round ch)
  {
    logger.info("Inside saveRound method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.save(ch);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on saveRound()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return ch;
  }
  
  public static Round getRound(String id)
  {
    logger.info("Inside getRound method");
    Round cy = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      cy = (Round)session.createCriteria(Round.class).add(Restrictions.eq("roundId", new Long(id))).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getRole()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return cy;
  }
  
  public static EvaluationTemplate getEvaluationCriteria(String id)
  {
    logger.info("Inside getEvaluationCriteria method");
    EvaluationTemplate evtmpl = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      evtmpl = (EvaluationTemplate)session.createCriteria(EvaluationTemplate.class).add(Restrictions.eq("evtmplId", new Long(id))).uniqueResult();
      logger.info(Integer.valueOf(evtmpl.getCharGroupList().size()));
      List grpcharlist = evtmpl.getCharGroupList();
      for (int i = 0; i < grpcharlist.size(); i++)
      {
        GroupCharacteristic grpchar = (GroupCharacteristic)grpcharlist.get(i);
        
        logger.info(Integer.valueOf(grpchar.getCharList().size()));
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getEvaluationCriteria()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return evtmpl;
  }
  
  public static Round getRoundByRoundName(String name)
  {
    logger.info("Inside getRound method");
    Round cy = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      cy = (Round)session.createCriteria(Round.class).add(Restrictions.eq("roundName", name)).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getRole()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return cy;
  }
  
  public static List getCharacteristicsByCritera(String criteria)
  {
    logger.info("Inside getCharacteristicsByCritera method");
    List charList = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      if ((criteria != null) && (criteria.length() > 0)) {
        charList = session.createCriteria(Characteristic.class).add(Restrictions.like("charName", "%" + criteria + "%")).list();
      } else {
        charList = session.createCriteria(Characteristic.class).list();
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getCharacteristicsByCritera()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return charList;
  }
  
  public static List getGroupCharacteristicsByCritera(String criteria)
  {
    logger.info("Inside getGroupCharacteristicsByCritera method");
    List charList = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      if ((criteria != null) && (criteria.length() > 0)) {
        charList = session.createCriteria(GroupCharacteristic.class).add(Restrictions.like("groupCharName", "%" + criteria + "%")).list();
      } else {
        charList = session.createCriteria(GroupCharacteristic.class).list();
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getGroupCharacteristicsByCritera()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return charList;
  }
  
  public static List getEvaluationTemplatesByCritera(String criteria)
  {
    logger.info("Inside getEvaluationTemplatesByCritera method");
    List charList = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      if ((criteria != null) && (criteria.length() > 0)) {
        charList = session.createCriteria(EvaluationTemplate.class).add(Restrictions.like("evTmplName", "%" + criteria + "%")).list();
      } else {
        charList = session.createCriteria(EvaluationTemplate.class).list();
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getEvaluationTemplatesByCritera()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return charList;
  }
  
  public static List getLocationsByCritera(String criteria)
  {
    logger.info("Inside getLocationsByCritera method");
    List charList = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      if ((criteria != null) && (criteria.length() > 0)) {
        charList = session.createCriteria(Location.class).add(Restrictions.like("locationName", "%" + criteria + "%")).list();
      } else {
        charList = session.createCriteria(Location.class).list();
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getLocationsByCritera()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return charList;
  }
  
  public static List getDepartmentsByCritera(String criteria, String orgId)
  {
    logger.info("Inside getDepartmentsByCritera method");
    List charList = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      if ((criteria != null) && (criteria.length() > 0)) {
        charList = session.createCriteria(Department.class).add(Restrictions.eq("status", "A")).createAlias("organization", "organization").add(Restrictions.eq("organization.orgId", new Long(orgId))).add(Restrictions.like("departmentName", "%" + criteria + "%")).list();
      } else {
        charList = session.createCriteria(Department.class).add(Restrictions.eq("status", "A")).createAlias("organization", "organization").add(Restrictions.eq("organization.orgId", new Long(orgId))).list();
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getDepartmentsByCritera()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return charList;
  }
  
  public static List getProjectCodesByCriteria(String projectCode, String projectName, long deptId, int start, int range)
  {
    logger.info("Inside getProjectCodesByCriteria method");
    List charList = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      Criteria crit = session.createCriteria(ProjectCodes.class).add(Restrictions.eq("status", "A"));
      if (!StringUtils.isNullOrEmpty(projectCode)) {
        crit.add(Restrictions.like("projCode", "%" + projectCode + "%"));
      }
      if (!StringUtils.isNullOrEmpty(projectName)) {
        crit.add(Restrictions.like("projName", "%" + projectName + "%"));
      }
      if (deptId != 0L)
      {
        crit.createAlias("department", "department");
        crit.add(Restrictions.eq("department.departmentId", new Long(deptId)));
      }
      crit.setFirstResult(start);
      crit.setMaxResults(range);
      charList = crit.list();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getProjectCodesByCriteria()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return charList;
  }
  
  public static int getCountOfProjectCodesByCriteria(String projectCode, String projectName, long deptId)
  {
    logger.info("Inside getCountOfProjectCodesByCriteria method");
    int totalcount = 0;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      Criteria crit = session.createCriteria(ProjectCodes.class).add(Restrictions.eq("status", "A"));
      if (!StringUtils.isNullOrEmpty(projectCode)) {
        crit.add(Restrictions.like("projCode", "%" + projectCode + "%"));
      }
      if (!StringUtils.isNullOrEmpty(projectName)) {
        crit.add(Restrictions.like("projName", "%" + projectName + "%"));
      }
      if (deptId != 0L)
      {
        crit.createAlias("department", "department");
        crit.add(Restrictions.eq("department.departmentId", new Long(deptId)));
      }
      crit.setProjection(Projections.rowCount());
      totalcount = ((Integer)crit.list().get(0)).intValue();
      

      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfProjectCodesByCriteria()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return totalcount;
  }
  
  public static List getProjectCodesByCriteriaWithPagination(String criteria, long orgId, long deptId, int start, int range)
  {
    logger.info("Inside getProjectCodesByCriteriaWithPagination method");
    List charList = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      if ((criteria != null) && (criteria.length() > 0))
      {
        Criteria crit = session.createCriteria(ProjectCodes.class).createAlias("department", "department").add(Restrictions.eq("department.departmentId", new Long(deptId))).createAlias("department.organization", "department.organization").add(Restrictions.eq("department.organization.orgId", new Long(orgId))).add(Restrictions.like("projCode", "%" + criteria + "%"));
        



        crit.setFirstResult(start);
        crit.setMaxResults(range);
        charList = crit.list();
      }
      else
      {
        Criteria crit = session.createCriteria(ProjectCodes.class).createAlias("department", "department").add(Restrictions.eq("department.departmentId", new Long(deptId))).createAlias("department.organization", "department.organization").add(Restrictions.eq("department.organization.orgId", new Long(orgId)));
        


        crit.setFirstResult(start);
        crit.setMaxResults(range);
        charList = crit.list();
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getProjectCodesByCriteriaWithPagination()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return charList;
  }
  
  public static Location getLocation(String id)
  {
    logger.info("Inside getLocation method");
    Location loc = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      loc = (Location)session.createCriteria(Location.class).add(Restrictions.eq("locationId", new Long(id))).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getLocation()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return loc;
  }
  
  public static int getJobGradesByCriteraCount(long superUserKey, String criteria)
  {
    logger.info("Inside getJobGradesByCriteraCount method");
    int totaljobgrades = 0;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      if ((criteria != null) && (criteria.length() > 0))
      {
        Criteria crit = session.createCriteria(JobGrade.class).add(Restrictions.like("jobGradeName", "%" + criteria + "%"));
        crit.add(Restrictions.eq("super_user_key", Long.valueOf(superUserKey)));
        crit.setProjection(Projections.rowCount());
        totaljobgrades = ((Integer)crit.list().get(0)).intValue();
      }
      else
      {
        Criteria crit = session.createCriteria(JobGrade.class);
        crit.add(Restrictions.eq("super_user_key", Long.valueOf(superUserKey)));
        crit.setProjection(Projections.rowCount());
        totaljobgrades = ((Integer)crit.list().get(0)).intValue();
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getJobGradesByCriteraCount()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return totaljobgrades;
  }
  
  public int getReqTemplatesByCriteraCount(User user, String criteria, long orgId, long departmentId, long projectId, long budgetId, String status)
  {
    logger.info("Inside getReqTemplatesByCriteraCount method");
    int totaljobgrades = 0;
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      Criteria crit = session.createCriteria(JobRequisionTemplate.class).add(Restrictions.eq("status", "Active"));
      
      crit.add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      if ((criteria != null) && (criteria.length() > 0)) {
        crit.add(Restrictions.like("templateName", "%" + criteria + "%"));
      }
      if (orgId != 0L)
      {
        crit.createAlias("organization", "organization");
        crit.add(Restrictions.eq("organization.orgId", Long.valueOf(orgId)));
      }
      if (departmentId != 0L)
      {
        crit.createAlias("department", "department");
        crit.add(Restrictions.eq("department.departmentId", Long.valueOf(departmentId)));
      }
      if (projectId != 0L)
      {
        logger.info("******** Project_Id **** " + projectId);
        crit.createAlias("projectcode", "projectcode");
        crit.add(Restrictions.eq("projectcode.projectId", Long.valueOf(projectId)));
      }
      if (budgetId != 0L)
      {
        logger.info("******** budgetId **** " + budgetId);
        crit.createAlias("budgetcode", "budgetcode");
        crit.add(Restrictions.eq("budgetcode.budgetId", Long.valueOf(budgetId)));
      }
      if (!StringUtils.isNullOrEmpty(status)) {
        crit.add(Restrictions.eq("status", status));
      }
      crit.setProjection(Projections.rowCount());
      totaljobgrades = ((Integer)crit.list().get(0)).intValue();
    }
    catch (Exception e)
    {
      logger.error("Exception on getReqTemplatesByCriteraCount()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totaljobgrades;
  }
  
  public static int getQuestiongroupsByCriteraCount(String criteria)
  {
    logger.info("Inside getQuestiongroupsByCriteraCount method");
    int totaljobgrades = 0;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      if ((criteria != null) && (criteria.length() > 0))
      {
        Criteria crit = session.createCriteria(QuestionGroups.class).add(Restrictions.like("questiongroupName", "%" + criteria + "%"));
        crit.setProjection(Projections.rowCount());
        totaljobgrades = ((Integer)crit.list().get(0)).intValue();
      }
      else
      {
        Criteria crit = session.createCriteria(QuestionGroups.class);
        crit.setProjection(Projections.rowCount());
        totaljobgrades = ((Integer)crit.list().get(0)).intValue();
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getQuestiongroupsByCriteraCount()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return totaljobgrades;
  }
  
  public static int getRequistionsByCriteraCount(String criteria)
  {
    logger.info("Inside getRequistionsByCriteraCount method");
    int totaljobgrades = 0;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      if ((criteria != null) && (criteria.length() > 0))
      {
        Criteria crit = session.createCriteria(JobRequisition.class).add(Restrictions.like("jobTitle", "%" + criteria + "%")).add(Restrictions.eq("state", "Active")).add(Restrictions.eq("status", "Open"));
        
        crit.setProjection(Projections.rowCount());
        totaljobgrades = ((Integer)crit.list().get(0)).intValue();
      }
      else
      {
        Criteria crit = session.createCriteria(JobRequisition.class).add(Restrictions.eq("state", "Active")).add(Restrictions.eq("status", "Open"));
        
        crit.setProjection(Projections.rowCount());
        totaljobgrades = ((Integer)crit.list().get(0)).intValue();
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getRequistionsByCriteraCount()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return totaljobgrades;
  }
  
  public static int getJobCodesByCriteraCount(String criteria)
  {
    logger.info("Inside getJobCodesByCriteraCount method");
    int totaljobcode = 0;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      if ((criteria != null) && (criteria.length() > 0))
      {
        Criteria crit = session.createCriteria(JobCode.class).add(Restrictions.like("jobName", "%" + criteria + "%"));
        crit.setProjection(Projections.rowCount());
        totaljobcode = ((Integer)crit.list().get(0)).intValue();
      }
      else
      {
        Criteria crit = session.createCriteria(JobCode.class);
        crit.setProjection(Projections.rowCount());
        totaljobcode = ((Integer)crit.list().get(0)).intValue();
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getJobCodesByCriteraCount()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return totaljobcode;
  }
  
  public static List getJobGradesByCritera(long superUserKey, String criteria, int start, int range)
  {
    logger.info("Inside getJobGradesByCritera method");
    List charList = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      if ((criteria != null) && (criteria.length() > 0))
      {
        Criteria crit = session.createCriteria(JobGrade.class).add(Restrictions.like("jobGradeName", "%" + criteria + "%"));
        crit.add(Restrictions.eq("super_user_key", Long.valueOf(superUserKey)));
        crit.setFirstResult(start);
        crit.setMaxResults(range);
        charList = crit.list();
      }
      else
      {
        Criteria crit = session.createCriteria(JobGrade.class);
        crit.add(Restrictions.eq("super_user_key", Long.valueOf(superUserKey)));
        crit.setFirstResult(start);
        crit.setMaxResults(range);
        charList = crit.list();
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getJobGradesByCritera()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return charList;
  }
  
  public static List getQuestionsGroupByCritera(String criteria, int start, int range)
  {
    logger.info("Inside getQuestionsGroupByCritera method");
    List charList = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      if ((criteria != null) && (criteria.length() > 0))
      {
        Criteria crit = session.createCriteria(QuestionGroups.class).add(Restrictions.like("questiongroupName", "%" + criteria + "%"));
        crit.setFirstResult(start);
        crit.setMaxResults(range);
        charList = crit.list();
      }
      else
      {
        Criteria crit = session.createCriteria(QuestionGroups.class);
        crit.setFirstResult(start);
        crit.setMaxResults(range);
        charList = crit.list();
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getQuestionsGroupByCritera()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return charList;
  }
  
  public List getRequisitionTemplatesByCritera(User user, String criteria, long orgId, long departmentId, long projectId, long budgetId, String status, int start, int range)
  {
    logger.info("Inside getRequisitionTemplatesByCritera method");
    List charList = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      Criteria crit = session.createCriteria(JobRequisionTemplate.class).add(Restrictions.eq("status", "Active"));
      
      crit.add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      if ((criteria != null) && (criteria.length() > 0)) {
        crit.add(Restrictions.like("templateName", "%" + criteria + "%"));
      }
      if (orgId != 0L)
      {
        crit.createAlias("organization", "organization");
        crit.add(Restrictions.eq("organization.orgId", Long.valueOf(orgId)));
      }
      if (departmentId != 0L)
      {
        crit.createAlias("department", "department");
        crit.add(Restrictions.eq("department.departmentId", Long.valueOf(departmentId)));
      }
      if (projectId != 0L)
      {
        logger.info("******** Project_Id **** " + projectId);
        crit.createAlias("projectcode", "projectcode");
        crit.add(Restrictions.eq("projectcode.projectId", Long.valueOf(projectId)));
      }
      if (budgetId != 0L)
      {
        logger.info("******** budgetId **** " + budgetId);
        crit.createAlias("budgetcode", "budgetcode");
        crit.add(Restrictions.eq("budgetcode.budgetId", Long.valueOf(budgetId)));
      }
      if (!StringUtils.isNullOrEmpty(status)) {
        crit.add(Restrictions.eq("state", status));
      }
      crit.setFirstResult(start);
      crit.setMaxResults(range);
      charList = crit.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getRequisitionTemplatesByCritera()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return charList;
  }
  
  public static List getRequisitionsByCritera(String criteria, int start, int range)
  {
    logger.info("Inside getRequisitionsByCritera method");
    List charList = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      if ((criteria != null) && (criteria.length() > 0))
      {
        Criteria crit = session.createCriteria(JobRequisition.class).add(Restrictions.like("jobTitle", "%" + criteria + "%")).add(Restrictions.eq("state", "Active")).add(Restrictions.eq("status", "Open"));
        
        crit.setFirstResult(start);
        crit.setMaxResults(range);
        charList = crit.list();
      }
      else
      {
        Criteria crit = session.createCriteria(JobRequisition.class).add(Restrictions.eq("state", "Active")).add(Restrictions.eq("status", "Open"));
        
        crit.setFirstResult(start);
        crit.setMaxResults(range);
        charList = crit.list();
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getRequisitionsByCritera()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return charList;
  }
  
  public static List getJobCodesByCritera(String criteria, int start, int range)
  {
    logger.info("Inside getJobCodesByCritera method");
    List charList = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      if ((criteria != null) && (criteria.length() > 0))
      {
        Criteria crit = session.createCriteria(JobCode.class).add(Restrictions.like("jobName", "%" + criteria + "%"));
        crit.setFirstResult(start);
        crit.setMaxResults(range);
        charList = crit.list();
      }
      else
      {
        Criteria crit = session.createCriteria(JobCode.class);
        crit.setFirstResult(start);
        crit.setMaxResults(range);
        charList = crit.list();
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getJobCodesByCritera()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return charList;
  }
  
  public static List getSalaryPlansByCritera(String criteris, String year, String currencyCode, int start, int range)
  {
    logger.info("Inside getSalaryPlansByCritera method");
    List charList = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      Criteria crit = session.createCriteria(SalaryPlan.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("currencyCode", currencyCode));
      if ((!StringUtils.isNullOrEmpty(criteris)) && (!StringUtils.isNullOrEmpty(year)))
      {
        Criterion fname = Restrictions.like("salaryPlanName", "%" + criteris + "%");
        Criterion lname = Restrictions.like("salaryPlanYear", "%" + year + "%");
        LogicalExpression orExp = Restrictions.or(fname, lname);
        crit.add(orExp);
        crit.setFirstResult(start);
        crit.setMaxResults(range);
        charList = crit.list();
      }
      else if ((!StringUtils.isNullOrEmpty(criteris)) && (StringUtils.isNullOrEmpty(year)))
      {
        crit.add(Restrictions.like("salaryPlanName", "%" + criteris + "%"));
        crit.setFirstResult(start);
        crit.setMaxResults(range);
        charList = crit.list();
      }
      else if ((StringUtils.isNullOrEmpty(criteris)) && (!StringUtils.isNullOrEmpty(year)))
      {
        crit.add(Restrictions.like("salaryPlanYear", "%" + year + "%"));
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
      logger.error("Exception on getSalaryPlansByCritera()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return charList;
  }
  
  public static int getCountOfSalaryPlansByCritera(String criteris, String year, String currencyCode)
  {
    logger.info("Inside getCountOfSalaryPlansByCritera method");
    int totalsalaryplan = 0;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      Criteria crit = session.createCriteria(SalaryPlan.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("currencyCode", currencyCode));
      if ((!StringUtils.isNullOrEmpty(criteris)) && (!StringUtils.isNullOrEmpty(year)))
      {
        Criterion fname = Restrictions.like("salaryPlanName", "%" + criteris + "%");
        Criterion lname = Restrictions.like("salaryPlanYear", "%" + year + "%");
        LogicalExpression orExp = Restrictions.or(fname, lname);
        crit.add(orExp);
        crit.setProjection(Projections.rowCount());
        totalsalaryplan = ((Integer)crit.list().get(0)).intValue();
      }
      else if ((!StringUtils.isNullOrEmpty(criteris)) && (StringUtils.isNullOrEmpty(year)))
      {
        crit.add(Restrictions.like("salaryPlanName", "%" + criteris + "%"));
        crit.setProjection(Projections.rowCount());
        totalsalaryplan = ((Integer)crit.list().get(0)).intValue();
      }
      else if ((StringUtils.isNullOrEmpty(criteris)) && (!StringUtils.isNullOrEmpty(year)))
      {
        crit.add(Restrictions.like("salaryPlanYear", "%" + year + "%"));
        crit.setProjection(Projections.rowCount());
        totalsalaryplan = ((Integer)crit.list().get(0)).intValue();
      }
      else
      {
        crit.setProjection(Projections.rowCount());
        totalsalaryplan = ((Integer)crit.list().get(0)).intValue();
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfSalaryPlansByCritera()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return totalsalaryplan;
  }
  
  public static List getBudgetCodesByCritera(String criteria, int budgetYear, long orgId, long deptId, int start, int range)
  {
    logger.info("Inside getBudgetCodesByCritera method");
    List charList = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      Criteria crit = session.createCriteria(BudgetCode.class).add(Restrictions.eq("status", "A"));
      if ((!StringUtils.isNullOrEmpty(criteria)) && (!criteria.equals("null"))) {
        crit.add(Restrictions.like("budgetCode", "%" + criteria + "%"));
      }
      if (budgetYear != 0) {
        crit.add(Restrictions.eq("budgetYear", Integer.valueOf(budgetYear)));
      }
      if (orgId != 0L) {
        crit.createAlias("org", "org").add(Restrictions.eq("org.orgId", Long.valueOf(orgId)));
      }
      if (deptId != 0L) {
        crit.createAlias("department", "department").add(Restrictions.eq("department.departmentId", Long.valueOf(deptId)));
      }
      crit.setFirstResult(start);
      crit.setMaxResults(range);
      charList = crit.list();
      

      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getBudgetCodesByCritera()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return charList;
  }
  
  public static int getCountOfBudgetCodesByCritera(String criteria, int budgetYear, long orgId, long deptId)
  {
    logger.info("Inside getCountOfBudgetCodesByCritera method");
    int totalcount = 0;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      Criteria crit = session.createCriteria(BudgetCode.class).add(Restrictions.eq("status", "A"));
      if ((!StringUtils.isNullOrEmpty(criteria)) && (!criteria.equals("null"))) {
        crit.add(Restrictions.like("budgetCode", "%" + criteria + "%"));
      }
      if (budgetYear != 0) {
        crit.add(Restrictions.eq("budgetYear", Integer.valueOf(budgetYear)));
      }
      if (orgId != 0L) {
        crit.createAlias("org", "org").add(Restrictions.eq("org.orgId", Long.valueOf(orgId)));
      }
      if (deptId != 0L) {
        crit.createAlias("department", "department").add(Restrictions.eq("department.departmentId", Long.valueOf(deptId)));
      }
      crit.setProjection(Projections.rowCount());
      totalcount = ((Integer)crit.list().get(0)).intValue();
      

      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfBudgetCodesByCritera()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return totalcount;
  }
  
  public static List getDeparmentByCritiriya(String deptname, String deptcode, long orgId, int start, int range)
  {
    logger.info("Inside getDeparmentByCritiriya method");
    List charList = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      Criteria crit = session.createCriteria(Department.class).add(Restrictions.eq("status", "A"));
      if (!StringUtils.isNullOrEmpty(deptname)) {
        crit.add(Restrictions.like("departmentName", "%" + deptname + "%"));
      }
      if (!StringUtils.isNullOrEmpty(deptname)) {
        crit.add(Restrictions.like("departmentCode", "%" + deptcode + "%"));
      }
      if (orgId != 0L)
      {
        crit.createAlias("organization", "organization");
        crit.add(Restrictions.eq("organization.orgId", new Long(orgId)));
      }
      crit.setFirstResult(start);
      crit.setMaxResults(range);
      charList = crit.list();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getDeparmentByCritiriya()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return charList;
  }
  
  public static int getCountOfDepartmentsByCritera(String deptname, String deptcode, long orgId)
  {
    logger.info("Inside getCountOfLocationByCritera method");
    int totalcount = 0;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      Criteria crit = session.createCriteria(Department.class).add(Restrictions.eq("status", "A"));
      if (!StringUtils.isNullOrEmpty(deptname)) {
        crit.add(Restrictions.like("departmentName", "%" + deptname + "%"));
      }
      if (!StringUtils.isNullOrEmpty(deptname)) {
        crit.add(Restrictions.like("departmentCode", "%" + deptcode + "%"));
      }
      if (orgId != 0L)
      {
        crit.createAlias("organization", "organization");
        crit.add(Restrictions.eq("organization.orgId", new Long(orgId)));
      }
      crit.setProjection(Projections.rowCount());
      totalcount = ((Integer)crit.list().get(0)).intValue();
      

      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfLocationByCritera()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return totalcount;
  }
  
  public static List getRequisitionsByCritera1(long super_user_key, String jobcode, String jobtitle, long orgId, long deptId, long locationId, String status, int start, int range)
  {
    logger.info("Inside getRequisitionsByCritera method");
    List charList = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      Criteria crit = session.createCriteria(JobRequisition.class).add(Restrictions.eq("state", "Active")).add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key)));
      if (!StringUtils.isNullOrEmpty(jobcode)) {
        crit.add(Restrictions.eq("jobreqcode", jobcode));
      }
      if (!StringUtils.isNullOrEmpty(jobtitle)) {
        crit.add(Restrictions.like("jobreqName", "%" + jobtitle + "%"));
      }
      if (orgId != 0L)
      {
        crit.createAlias("organization", "organization");
        crit.add(Restrictions.eq("organization.orgId", Long.valueOf(orgId)));
      }
      if (deptId != 0L)
      {
        crit.createAlias("department", "department");
        crit.add(Restrictions.eq("department.departmentId", Long.valueOf(deptId)));
      }
      if (locationId != 0L)
      {
        crit.createAlias("location", "location");
        crit.add(Restrictions.eq("location.locationId", Long.valueOf(locationId)));
      }
      if (!StringUtils.isNullOrEmpty(status)) {
        crit.add(Restrictions.eq("status", status));
      }
      crit.setFirstResult(start);
      crit.setMaxResults(range);
      charList = crit.list();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getRequisitionsByCritera()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return charList;
  }
  
  public static List getRequisitionsByCritera(long userId, String jobcode, String jobtitle, long orgId, long deptId, long locationId, String status, int start, int range)
  {
    logger.info("Inside getRequisitionsByCritera method");
    List charList = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      String hqlquery = " select distinct j from JobRequisition j , UserGroup ug where  ((j.hiringmgr.userId =:currentuserid) or (j.recruiterId = :currentuserid and j.isgrouprecruiter <> 'Y') or (j.recruiterId = ug.usergrpId and  j.isgrouprecruiter='Y' and ug.users.userId =:currentuserid)) ";
      

      hqlquery = hqlquery + " and j.state = 'Active' ";
      if ((!StringUtils.isNullOrEmpty(jobtitle)) && (!jobtitle.equals("null"))) {
        hqlquery = hqlquery + " and j.jobTitle like :jobTitle";
      }
      if ((!StringUtils.isNullOrEmpty(jobcode)) && (!jobcode.equals("null")) && (!jobcode.equals("0"))) {
        hqlquery = hqlquery + " and j.jobreqcode = :jobreqcode";
      }
      if (orgId != 0L) {
        hqlquery = hqlquery + " and j.organization.orgId = :orgId";
      }
      if (deptId != 0L) {
        hqlquery = hqlquery + " and j.department.departmentId = :departmentId";
      }
      if (locationId != 0L) {
        hqlquery = hqlquery + " and j.location.locationId = :locationId";
      }
      if ((!StringUtils.isNullOrEmpty(status)) && (!status.equals("null"))) {
        hqlquery = hqlquery + " and j.status = :status";
      }
      Query query = session.createQuery(hqlquery);
      
      query.setLong("currentuserid", new Long(userId).longValue());
      if ((!StringUtils.isNullOrEmpty(jobtitle)) && (!jobtitle.equals("null"))) {
        query.setString("jobTitle", '%' + jobtitle + '%');
      }
      if ((!StringUtils.isNullOrEmpty(jobcode)) && (!jobcode.equals("null")) && (!jobcode.equals("0"))) {
        query.setString("jobreqcode", jobcode);
      }
      if (orgId != 0L) {
        query.setLong("orgId", orgId);
      }
      if (deptId != 0L) {
        query.setLong("departmentId", deptId);
      }
      if (locationId != 0L) {
        query.setLong("locationId", locationId);
      }
      if ((!StringUtils.isNullOrEmpty(status)) && (!status.equals("null"))) {
        query.setString("status", status);
      }
      query.setFirstResult(start);
      query.setMaxResults(range);
      charList = query.list();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getRequisitionsByCritera()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return charList;
  }
  
  public static int getRequistionsByCriteraCount1(long super_user_key, String jobcode, String jobtitle, long orgId, long deptId, long locationId, String status)
  {
    logger.info("Inside getRequistionsByCriteraCount method");
    int totalcount = 0;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      Criteria crit = session.createCriteria(JobRequisition.class).add(Restrictions.eq("state", "Active")).add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key)));
      if (!StringUtils.isNullOrEmpty(jobcode)) {
        crit.add(Restrictions.eq("jobreqcode", jobcode));
      }
      if (!StringUtils.isNullOrEmpty(jobtitle)) {
        crit.add(Restrictions.like("jobreqName", "%" + jobtitle + "%"));
      }
      if (orgId != 0L)
      {
        crit.createAlias("organization", "organization");
        crit.add(Restrictions.eq("organization.orgId", Long.valueOf(orgId)));
      }
      if (deptId != 0L)
      {
        crit.createAlias("department", "department");
        crit.add(Restrictions.eq("department.departmentId", Long.valueOf(deptId)));
      }
      if (locationId != 0L)
      {
        crit.createAlias("location", "location");
        crit.add(Restrictions.eq("location.locationId", Long.valueOf(locationId)));
      }
      if (!StringUtils.isNullOrEmpty(status)) {
        crit.add(Restrictions.eq("status", status));
      }
      crit.setProjection(Projections.rowCount());
      totalcount = ((Integer)crit.list().get(0)).intValue();
      

      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getRequistionsByCriteraCount()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return totalcount;
  }
  
  public static int getRequistionsByCriteraCount(long userId, String jobcode, String jobtitle, long orgId, long deptId, long locationId, String status)
  {
    logger.info("Inside getRequistionsByCriteraCount method");
    int totalcount = 0;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      String hqlquery = " select distinct j.jobreqId from JobRequisition j , UserGroup ug where  ((j.hiringmgr.userId =:currentuserid) or (j.recruiterId = :currentuserid and j.isgrouprecruiter <> 'Y') or (j.recruiterId = ug.usergrpId and  j.isgrouprecruiter='Y' and ug.users.userId =:currentuserid)) ";
      

      hqlquery = hqlquery + " and j.state = 'Active' ";
      if ((!StringUtils.isNullOrEmpty(jobtitle)) && (!jobtitle.equals("null"))) {
        hqlquery = hqlquery + " and j.jobTitle like :jobTitle";
      }
      if ((!StringUtils.isNullOrEmpty(jobcode)) && (!jobcode.equals("null")) && (!jobcode.equals("0"))) {
        hqlquery = hqlquery + " and j.jobreqcode = :jobreqcode";
      }
      if (orgId != 0L) {
        hqlquery = hqlquery + " and j.organization.orgId = :orgId";
      }
      if (deptId != 0L) {
        hqlquery = hqlquery + " and j.department.departmentId = :departmentId";
      }
      if (locationId != 0L) {
        hqlquery = hqlquery + " and j.location.locationId = :locationId";
      }
      if ((!StringUtils.isNullOrEmpty(status)) && (!status.equals("null"))) {
        hqlquery = hqlquery + " and j.status = :status";
      }
      Query query = session.createQuery(hqlquery);
      
      query.setLong("currentuserid", new Long(userId).longValue());
      if ((!StringUtils.isNullOrEmpty(jobtitle)) && (!jobtitle.equals("null"))) {
        query.setString("jobTitle", '%' + jobtitle + '%');
      }
      if ((!StringUtils.isNullOrEmpty(jobcode)) && (!jobcode.equals("null")) && (!jobcode.equals("0"))) {
        query.setString("jobreqcode", jobcode);
      }
      if (orgId != 0L) {
        query.setLong("orgId", orgId);
      }
      if (deptId != 0L) {
        query.setLong("departmentId", deptId);
      }
      if (locationId != 0L) {
        query.setLong("locationId", locationId);
      }
      if ((!StringUtils.isNullOrEmpty(status)) && (!status.equals("null"))) {
        query.setString("status", status);
      }
      List lst = query.list();
      if ((lst != null) && (lst.size() > 0)) {
        totalcount = lst.size();
      }
      logger.info("Inside getRequistionsByCriteraCount method totalcount" + totalcount);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getRequistionsByCriteraCount()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return totalcount;
  }
  
  public static List getLocationsByCritera(User user, String locationName, String loccode, long regionId, long countryId, long stateId, int start, int range)
  {
    logger.info("Inside getLocationsByCritera method");
    List charList = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      Criteria crit = session.createCriteria(Location.class).add(Restrictions.eq("status", "A"));
      
      crit.add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      if (!StringUtils.isNullOrEmpty(locationName)) {
        crit.add(Restrictions.like("locationName", "%" + locationName + "%"));
      }
      if (!StringUtils.isNullOrEmpty(loccode)) {
        crit.add(Restrictions.like("locationCode", "%" + loccode + "%"));
      }
      if (regionId != 0L)
      {
        crit.createAlias("region", "region");
        crit.add(Restrictions.eq("region.regionId", Long.valueOf(regionId)));
      }
      if (countryId != 0L)
      {
        crit.createAlias("country", "country");
        crit.add(Restrictions.eq("country.countryId", Long.valueOf(countryId)));
      }
      if (stateId != 0L)
      {
        crit.createAlias("state", "state");
        crit.add(Restrictions.eq("state.stateId", Long.valueOf(stateId)));
      }
      crit.setFirstResult(start);
      crit.setMaxResults(range);
      charList = crit.list();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getLocationsByCritera()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return charList;
  }
  
  public static int getCountOfLocationByCritera(User user, String locationName, String loccode, long regionId, long countryId, long stateId)
  {
    logger.info("Inside getCountOfLocationByCritera method");
    int totalcount = 0;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      Criteria crit = session.createCriteria(Location.class).add(Restrictions.eq("status", "A"));
      
      crit.add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      if (!StringUtils.isNullOrEmpty(locationName)) {
        crit.add(Restrictions.like("locationName", "%" + locationName + "%"));
      }
      if (!StringUtils.isNullOrEmpty(loccode)) {
        crit.add(Restrictions.like("locationCode", "%" + loccode + "%"));
      }
      if (regionId != 0L)
      {
        crit.createAlias("region", "region");
        crit.add(Restrictions.eq("region.regionId", Long.valueOf(regionId)));
      }
      if (countryId != 0L)
      {
        crit.createAlias("country", "country");
        crit.add(Restrictions.eq("country.countryId", Long.valueOf(countryId)));
      }
      if (stateId != 0L)
      {
        crit.createAlias("state", "state");
        crit.add(Restrictions.eq("state.stateId", Long.valueOf(stateId)));
      }
      crit.setProjection(Projections.rowCount());
      totalcount = ((Integer)crit.list().get(0)).intValue();
      

      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfLocationByCritera()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return totalcount;
  }
  
  public static OnBoardingTaskAttributes saveAttributesDetails(OnBoardingTaskAttributes OnBoardingTaskAttributes)
  {
    logger.info("Inside saveAttributesDetails method");
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.save(OnBoardingTaskAttributes);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on saveAttributesDetails()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return OnBoardingTaskAttributes;
  }
  
  public static void deleteAttributes(long Attributeid)
  {
    logger.info("Inside deleteAttributes method");
    OnBoardingTaskAttributes edu = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      edu = (OnBoardingTaskAttributes)session.createCriteria(OnBoardingTaskAttributes.class).add(Restrictions.eq("taskattid", Long.valueOf(Attributeid))).uniqueResult();
      session.delete(edu);
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteAttributes()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
  }
  
  public static List getAttributeDetailsByTask(long taskattid)
  {
    logger.info("Inside getAttributeDetailsByTask method");
    List aList = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      aList = session.createCriteria(OnBoardingTaskAttributes.class).add(Restrictions.eq("taskattid", new Long(taskattid))).list();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAttributeDetailsByTask()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return aList;
  }
}
