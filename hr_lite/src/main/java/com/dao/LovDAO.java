package com.dao;

import com.bean.Accomplishments;
import com.bean.ApplicantReferencee;
import com.bean.ApplicantTags;
import com.bean.BudgetCode;
import com.bean.Characteristic;
import com.bean.City;
import com.bean.CompFrequency;
import com.bean.Country;
import com.bean.Currency;
import com.bean.DeclinedResons;
import com.bean.Department;
import com.bean.Designations;
import com.bean.EmailEvents;
import com.bean.EmailNotificationSetting;
import com.bean.EmailTemplates;
import com.bean.EvaluationTemplate;
import com.bean.ExpenseTypes;
import com.bean.Feedbacks;
import com.bean.FlsaStatus;
import com.bean.GroupCharacteristic;
import com.bean.JobApplicant;
import com.bean.JobCode;
import com.bean.JobGrade;
import com.bean.JobRequisition;
import com.bean.JobType;
import com.bean.Locale;
import com.bean.Location;
import com.bean.Organization;
import com.bean.OrganizationEmailTemplate;
import com.bean.OrganizationType;
import com.bean.Permissions;
import com.bean.ProfilePhoto;
import com.bean.ProjectCodes;
import com.bean.QuestionAnswers;
import com.bean.QuestionGroupApplicants;
import com.bean.QuestionGroups;
import com.bean.QuestionOptions;
import com.bean.QuestionnaireAnswers;
import com.bean.Questions;
import com.bean.RefferalBudgetCode;
import com.bean.RefferalRedemptionRule;
import com.bean.RefferalScheme;
import com.bean.RefferalSchemeType;
import com.bean.Region;
import com.bean.RequistionExpenses;
import com.bean.ResumeSourceType;
import com.bean.ResumeSources;
import com.bean.Role;
import com.bean.Round;
import com.bean.SalaryPlan;
import com.bean.State;
import com.bean.Tags;
import com.bean.TaskData;
import com.bean.Timezone;
import com.bean.UrlEncodeData;
import com.bean.User;
import com.bean.UserGroup;
import com.bean.WatchList;
import com.bean.WorkShift;
import com.bean.lov.Category;
import com.bean.lov.Education;
import com.bean.lov.EthnicRace;
import com.bean.lov.JobCategory;
import com.bean.lov.Languages;
import com.bean.lov.LicenseType;
import com.bean.lov.LovList;
import com.bean.lov.MembershipType;
import com.bean.lov.TechnicalSkills;
import com.bean.lov.VeteranDisability;
import com.common.ValidationException;
import com.util.StringUtils;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class LovDAO
  extends HibernateDaoSupport
{
  protected static final Logger logger = Logger.getLogger(LovDAO.class);
  
  public EmailNotificationSetting getEmailNotificationSetting(String notificationFunction, long super_user_key)
  {
    logger.info("Inside getEmailNotificationSetting method");
    boolean success = false;
    org.hibernate.Session session = null;
    EmailNotificationSetting emp = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      emp = (EmailNotificationSetting)session.createCriteria(EmailNotificationSetting.class).add(Restrictions.eq("functionName", notificationFunction)).add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getEmailNotificationSetting()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return emp;
  }
  
  public List getAllCountriesByRegion(long regionId)
  {
    logger.info("Inside getAllCountriesByRegion method");
    List countryList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      countryList = session.createCriteria(Country.class).add(Restrictions.eq("status", "A")).createAlias("region", "rg").add(Restrictions.eq("rg.regionId", new Long(regionId))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllCountriesByRegion()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return countryList;
  }
  
  public List getAllRequistionExpenses(long reqId)
  {
    logger.info("Inside getAllRequistionExpenses method");
    List expenseList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      expenseList = session.createCriteria(RequistionExpenses.class).add(Restrictions.eq("jobRequision.jobreqId", Long.valueOf(reqId))).list();
      logger.info("Inside getAllRequistionExpenses method end");
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllRequistionExpenses()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return expenseList;
  }
  
  public RequistionExpenses saveRequistionExpenses(RequistionExpenses expense)
  {
    logger.info("Inside saveRequistionExpenses method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.save(expense);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveRequistionExpenses()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return expense;
  }
  
  public List getAllExpenseTypesList()
  {
    logger.info("Inside getAllExpenseTypesList method");
    List expenseList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      expenseList = session.createCriteria(ExpenseTypes.class).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllExpenseTypesList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return expenseList;
  }
  
  public List getDepartmentList()
  {
    logger.info("Inside getDepartmentList method");
    List deptList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      deptList = session.createCriteria(Department.class).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getDepartmentList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return deptList;
  }
  
  public RefferalBudgetCode getRefferalBudgetCode(String id)
  {
    logger.info("Inside getBudgetCode method");
    RefferalBudgetCode refferalbudgetCode = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      refferalbudgetCode = (RefferalBudgetCode)session.createCriteria(RefferalBudgetCode.class).add(Restrictions.eq("ref_budgetId", new Long(id))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getRefferalBudgetCode()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return refferalbudgetCode;
  }
  
  public Country getCountry(long id)
  {
    logger.info("Inside getCountry method");
    Country ct = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      ct = (Country)session.createCriteria(Country.class).add(Restrictions.eq("countryId", Long.valueOf(id))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountry()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return ct;
  }
  
  public State getState(long id)
  {
    logger.info("Inside getState method");
    State ct = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      ct = (State)session.createCriteria(State.class).add(Restrictions.eq("stateId", Long.valueOf(id))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getState()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return ct;
  }
  
  public Country getCountryByName(String code)
  {
    logger.info("Inside getCountryByName method");
    Country ct = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      ct = (Country)session.createCriteria(Country.class).add(Restrictions.eq("countryName", code)).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountryByName()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return ct;
  }
  
  public State getStateByStateIdAndCountryId(long stateid, long countryId)
  {
    logger.info("Inside getStateByStateIdAndCountryId method");
    State st = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      st = (State)session.createCriteria(State.class).add(Restrictions.eq("stateId", Long.valueOf(stateid))).createAlias("country", "country").add(Restrictions.eq("country.countryId", Long.valueOf(countryId))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getStateByStateIdAndCountryId()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return st;
  }
  
  public RefferalBudgetCode updateRefferalBudgetCode(RefferalBudgetCode refferalbudgetCode)
  {
    logger.info("Inside updateRefferalBudgetCode method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.saveOrUpdate(refferalbudgetCode);
    }
    catch (Exception e)
    {
      logger.error("Exception on updateRefferalBudgetCode()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return refferalbudgetCode;
  }
  
  public RefferalBudgetCode saveRefferalBudgetCode(RefferalBudgetCode refferalbudgetCode)
  {
    logger.info("Inside saveRefferalBudgetCode method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.save(refferalbudgetCode);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveRefferalBudgetCode()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return refferalbudgetCode;
  }
  
  public List getDepartmentListByOrg(String orgid)
  {
    logger.info("Inside getDepartmentListByOrg method");
    List deptList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      deptList = session.createCriteria(Department.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("organization.orgId", Long.valueOf(new Long(orgid).longValue()))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getDepartmentListByOrg()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return deptList;
  }
  
  public List getDepartmentListByOrg(List<Long> orgIdsList)
  {
    logger.info("Inside getDepartmentListByOrg method");
    List deptList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      deptList = session.createCriteria(Department.class).add(Restrictions.eq("status", "A")).add(Restrictions.in("organization.orgId", orgIdsList)).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getDepartmentListByOrg()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return deptList;
  }
  
  public List getProjectCodesByDept(List<Long> deptIdsList)
  {
    logger.info("Inside getProjectCodesByDept method");
    List projectList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      projectList = session.createCriteria(ProjectCodes.class).add(Restrictions.in("department.departmentId", deptIdsList)).add(Restrictions.eq("status", "A")).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getProjectCodesByDept()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return projectList;
  }
  
  public List getProjectCodesByDept(String deptId)
  {
    logger.info("Inside getProjectCodesByDept method");
    List projectList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      projectList = session.createCriteria(ProjectCodes.class).createAlias("department", "department").add(Restrictions.eq("department.departmentId", new Long(deptId))).add(Restrictions.eq("status", "A")).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getProjectCodesByDept()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return projectList;
  }
  
  public List getLOVListValues(String listcode, long localeId, long superUserKey)
  {
    logger.info("Inside getLOVListValues method");
    List orgList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      orgList = session.createCriteria(LovList.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(superUserKey))).add(Restrictions.eq("lovListCode", listcode)).add(Restrictions.eq("localeId", Long.valueOf(localeId))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getLOVListValues()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return orgList;
  }
  
  public List getLOVListValues(String listcode, long localeId)
  {
    logger.info("Inside getLOVListValues method");
    List orgList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      orgList = session.createCriteria(LovList.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("lovListCode", listcode)).add(Restrictions.eq("localeId", Long.valueOf(localeId))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getLOVListValues()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return orgList;
  }
  
  public List getAllOrganization(long superUserKey)
  {
    logger.info("Inside getAllOrganization method");
    List orgList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      orgList = session.createCriteria(Organization.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(superUserKey))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllOrganization()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return orgList;
  }
  
  public List getAllCurrencies()
  {
    logger.info("Inside getAllCurrencies method");
    List orgList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      orgList = session.createCriteria(Currency.class).add(Restrictions.eq("status", "A")).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllCurrencies()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return orgList;
  }
  
  public List getAllLanguages()
  {
    logger.info("Inside getAllLanguages method");
    List languageList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      languageList = session.createCriteria(Languages.class).add(Restrictions.eq("status", "A")).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllLanguages()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return languageList;
  }
  
  public List getAllLicense()
  {
    logger.info("Inside getAllLicense method");
    List licenseList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      licenseList = session.createCriteria(LicenseType.class).add(Restrictions.eq("status", "A")).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllLicense()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return licenseList;
  }
  
  public List getAllOrganizationWithParent()
  {
    logger.info("Inside getAllOrganizationWithParent method");
    List orgList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      orgList = session.createCriteria(Organization.class).add(Restrictions.eq("status", "A")).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllOrganizationWithParent()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return orgList;
  }
  
  public List getAllRefferalBudgetCode(long superUserKey)
  {
    logger.info("Inside getAllRefferalBudgetCode method");
    List refferalBudgetCodeLIst = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      refferalBudgetCodeLIst = session.createCriteria(RefferalBudgetCode.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(superUserKey))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllRefferalBudgetCode()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return refferalBudgetCodeLIst;
  }
  
  public List getAllRefferalSchemeType(long superUserKey)
  {
    logger.info("Inside getAllRefferalSchemeType method");
    List refferalSchemeTypeLIst = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      refferalSchemeTypeLIst = session.createCriteria(RefferalSchemeType.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(superUserKey))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllRefferalSchemeType()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return refferalSchemeTypeLIst;
  }
  
  public List getAllRefferalSchemes(String usertype, long superUserKey)
  {
    logger.info("Inside getAllRefferalSchemes method");
    List refferalSchemeTypeLIst = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      if (usertype.equals("Employee")) {
        refferalSchemeTypeLIst = session.createCriteria(RefferalScheme.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("schemeType", "E")).add(Restrictions.eq("super_user_key", Long.valueOf(superUserKey))).list();
      } else {
        refferalSchemeTypeLIst = session.createCriteria(RefferalScheme.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("schemeType", "V")).add(Restrictions.eq("super_user_key", Long.valueOf(superUserKey))).list();
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllRefferalSchemes()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return refferalSchemeTypeLIst;
  }
  
  public List getAllRefferalRules(long superUserKey)
  {
    logger.info("Inside getAllRefferalRules method");
    List ruleList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      ruleList = session.createCriteria(RefferalRedemptionRule.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(superUserKey))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllRefferalRules()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return ruleList;
  }
  
  public List getAllRefferalRules()
  {
    logger.info("Inside getAllRefferalRules method");
    List ruleList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      ruleList = session.createCriteria(RefferalRedemptionRule.class).add(Restrictions.eq("status", "A")).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllRefferalRules()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return ruleList;
  }
  
  public List getAllEmailtemplate()
  {
    logger.info("Inside getAllEmailtemplate method");
    List emailTempList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      emailTempList = session.createCriteria(EmailTemplates.class).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllEmailtemplate()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return emailTempList;
  }
  
  public List getAllQuestions(long super_user_key)
  {
    logger.info("Inside getAllQuestions method");
    List queList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      queList = session.createCriteria(Questions.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllQuestions()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return queList;
  }
  
  public List getLocationList()
  {
    logger.info("Inside getLocationList method");
    List locList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      locList = session.createCriteria(Location.class).add(Restrictions.eq("status", "A")).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getLocationList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return locList;
  }
  
  public List getRegions()
  {
    logger.info("Inside getRegions method");
    List roleList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      roleList = session.createCriteria(Region.class).add(Restrictions.eq("status", "A")).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getRegions()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return roleList;
  }
  
  public List getAllRoles(long superUserKey)
  {
    logger.info("Inside getAllRoles method");
    List roleList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      roleList = session.createCriteria(Role.class).add(Restrictions.eq("status", "A")).add(Restrictions.ne("roleCode", "VENDOR")).add(Restrictions.eq("super_user_key", Long.valueOf(superUserKey))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllRoles()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return roleList;
  }
  
  public List getAllDesignations(long superUserKey)
  {
    logger.info("Inside getAllDesignations method");
    List roleList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      roleList = session.createCriteria(Designations.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(superUserKey))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllDesignations()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return roleList;
  }
  
  public List getJobCategoryList(long superUserKey)
  {
    logger.info("Inside getJobCategoryList method");
    List jobcategoryList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      jobcategoryList = session.createCriteria(JobCategory.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(superUserKey))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getJobCategoryList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return jobcategoryList;
  }
  
  public List getAllLocationDetails()
  {
    logger.info("Inside getAllLocationDetails method");
    List locationList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      locationList = session.createCriteria(Location.class).add(Restrictions.eq("status", "A")).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllLocationDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return locationList;
  }
  
  public List getAllquestionsDetails(User user)
  {
    logger.info("Inside getAllquestionsDetails method");
    List questionList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      questionList = session.createCriteria(Questions.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key()))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllquestionsDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return questionList;
  }
  
  public List getAllquestionsGroupDetails(User user)
  {
    logger.info("Inside getAllquestionsGroupDetails method");
    List questiongroupList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      questiongroupList = session.createCriteria(QuestionGroups.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key()))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllquestionsGroupDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return questiongroupList;
  }
  
  public List getAssignedQuestionGroupsToApplicant(long applicantId)
  {
    logger.info("Inside getAssignedQuestionGroupsToApplicant method");
    List questiongroupList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      questiongroupList = session.createCriteria(QuestionGroupApplicants.class).add(Restrictions.eq("applicantId", Long.valueOf(applicantId))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAssignedQuestionGroupsToApplicant()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return questiongroupList;
  }
  
  public List getAllquestionOptionByQuestion(long queid)
  {
    logger.info("Inside getAllquestionOptionByQuestion method");
    List questionoptionList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      Criteria query = session.createCriteria(QuestionOptions.class).add(Restrictions.eq("questionId", Long.valueOf(queid)));
      



      questionoptionList = query.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllquestionsGroupDetailsForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    logger.info("Inside finally method4444444" + questionoptionList.size());
    return questionoptionList;
  }
  
  public List getAllquestionsGroupDetailsForPagination(User user, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getAllquestionsGroupDetailsForPagination method");
    List questiongroupList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      Criteria query = session.createCriteria(QuestionGroups.class).add(Restrictions.eq("status", "A"));
      query.add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      if (dir_str.equalsIgnoreCase("asc")) {
        query.addOrder(Order.asc(sort_str));
      } else {
        query.addOrder(Order.desc(sort_str));
      }
      query.setFirstResult(startIndex);
      query.setMaxResults(pageSize);
      questiongroupList = query.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllquestionsGroupDetailsForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    logger.info("Inside finally method4444444" + questiongroupList.size());
    return questiongroupList;
  }
  
  public List getAllquestionsDetailsForPagination(User user, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getAllquestionsDetailsForPagination method");
    List questionList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria query = session.createCriteria(Questions.class).add(Restrictions.eq("status", "A"));
      query.add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      if (dir_str.equalsIgnoreCase("asc")) {
        query.addOrder(Order.asc(sort_str));
      } else {
        query.addOrder(Order.desc(sort_str));
      }
      query.setFirstResult(startIndex);
      query.setMaxResults(pageSize);
      questionList = query.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllquestionsDetailsForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return questionList;
  }
  
  public int getCountOfAllLocations()
  {
    logger.info("Inside getCountOfAllLocations method");
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      Criteria criteria = session.createCriteria(Location.class).add(Restrictions.eq("status", "A"));
      criteria.setProjection(Projections.rowCount());
      totaluser = ((Integer)criteria.list().get(0)).intValue();
      System.out.println("totaluser" + totaluser);
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfAllLocations()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totaluser;
  }
  
  public int getCountOfAllLocations(User user, String locationname, String locationcode, String countryid, String stateid, String city)
  {
    logger.info("Inside getCountOfAllLocations method");
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      Criteria query = session.createCriteria(Location.class).add(Restrictions.eq("status", "A"));
      
      query.add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      if ((!StringUtils.isNullOrEmpty(locationname)) && (!locationname.equals("null"))) {
        query.add(Restrictions.like("locationName", "%" + locationname + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(locationcode)) && (!locationcode.equals("null"))) {
        query.add(Restrictions.like("locationCode", "%" + locationcode + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(city)) && (!city.equals("null"))) {
        query.add(Restrictions.like("city", "%" + city + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(countryid)) && (!countryid.equals("0")))
      {
        query.createAlias("country", "country");
        query.add(Restrictions.eq("country.countryId", new Long(countryid)));
      }
      if ((!StringUtils.isNullOrEmpty(stateid)) && (!stateid.equals("0")))
      {
        query.createAlias("state", "state");
        query.add(Restrictions.eq("state.stateId", new Long(stateid)));
      }
      query.setProjection(Projections.rowCount());
      totaluser = ((Integer)query.list().get(0)).intValue();
      System.out.println("totaluser" + totaluser);
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfAllLocations()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totaluser;
  }
  
  public int getCountOfAllDesignations()
  {
    logger.info("Inside getCountOfAllDesignations method ...");
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      Criteria criteria = session.createCriteria(Designations.class).add(Restrictions.eq("status", "A"));
      criteria.setProjection(Projections.rowCount());
      totaluser = ((Integer)criteria.list().get(0)).intValue();
      System.out.println("totaluser" + totaluser);
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfAllDesignations()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totaluser;
  }
  
  public int getCountOfAllDesignations(String designationName)
  {
    logger.info("Inside getCountOfAllDesignations method");
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      Criteria query = session.createCriteria(Designations.class).add(Restrictions.eq("status", "A"));
      if (!StringUtils.isNullOrEmpty(designationName)) {
        query.add(Restrictions.like("designationName", "%" + designationName + "%"));
      }
      query.setProjection(Projections.rowCount());
      totaluser = ((Integer)query.list().get(0)).intValue();
      System.out.println("totaluser" + totaluser);
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfAllDesignations()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totaluser;
  }
  
  public List getAllSalaryDetails(User user)
  {
    logger.info("Inside getAllSalaryDetails method");
    List salaryList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      salaryList = session.createCriteria(SalaryPlan.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key()))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllSalaryDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return salaryList;
  }
  
  public List getAllSalaryPlanDetailsByCurrency(String currencyCode, long superUserKey)
  {
    logger.info("Inside getAllSalaryPlanDetailsByCurrency method");
    List salaryList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      salaryList = session.createCriteria(SalaryPlan.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("currencyCode", currencyCode)).add(Restrictions.eq("super_user_key", Long.valueOf(superUserKey))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllSalaryPlanDetailsByCurrency()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return salaryList;
  }
  
  public List getAllRefferalSchemeTypeDetails(User user)
  {
    logger.info("Inside getAllRefferalSchemeDetails method");
    List refferalList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      refferalList = session.createCriteria(RefferalSchemeType.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key()))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllRefferalSchemeDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return refferalList;
  }
  
  public List getAllRefferalSchemeDetails()
  {
    logger.info("Inside getAllRefferalSchemeDetails method");
    List refferalList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      refferalList = session.createCriteria(RefferalScheme.class).add(Restrictions.eq("status", "A")).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllRefferalSchemeDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return refferalList;
  }
  
  public List getAllSalaryDetailsForPagination(User user, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getAllRolesForPagination method");
    List salaryList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      Criteria query = session.createCriteria(SalaryPlan.class).add(Restrictions.eq("status", "A"));
      
      query.add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      if (dir_str.equalsIgnoreCase("asc")) {
        query.addOrder(Order.asc(sort_str));
      } else {
        query.addOrder(Order.desc(sort_str));
      }
      query = query.setFirstResult(startIndex);
      query.setMaxResults(pageSize);
      salaryList = query.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllRolesForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return salaryList;
  }
  
  public List getAllJobCategoryDetailsForPagination(User user, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getAllJobCategoryDetailsForPagination method");
    List salaryList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      Criteria query = session.createCriteria(JobCategory.class).add(Restrictions.eq("status", "A"));
      

      query.add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      if (dir_str.equalsIgnoreCase("asc")) {
        query.addOrder(Order.asc(sort_str));
      } else {
        query.addOrder(Order.desc(sort_str));
      }
      query = query.setFirstResult(startIndex);
      query.setMaxResults(pageSize);
      salaryList = query.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllJobCategoryDetailsForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return salaryList;
  }
  
  public List getAllJobCategoryDetails(User user)
  {
    logger.info("Inside getAllJobCategoryDetails method");
    List salaryList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      salaryList = session.createCriteria(JobCategory.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key()))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllJobCategoryDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return salaryList;
  }
  
  public JobCategory getJobCategoryDetails(long jobCategoryId)
  {
    logger.info("Inside getJobCategoryDetails method");
    JobCategory jobCategory = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      jobCategory = (JobCategory)session.createCriteria(JobCategory.class).add(Restrictions.eq("jobCategoryId", Long.valueOf(jobCategoryId))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getJobCategoryDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return jobCategory;
  }
  
  public JobCategory updateJobCategoryDetails(JobCategory jobCategory)
  {
    logger.info("Inside updateJobCategoryDetails method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      session.update(jobCategory);
    }
    catch (Exception e)
    {
      logger.error("Exception on updateJobCategoryDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return jobCategory;
  }
  
  public JobCategory saveJobCategoryDetails(JobCategory jobCategory)
  {
    logger.info("Inside saveJobCategoryDetails method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      session.save(jobCategory);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveJobCategoryDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return jobCategory;
  }
  
  public JobCategory deleteJobCategoryDetails(JobCategory jobCategory)
  {
    logger.info("Inside deleteJobCategoryDetails method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      session.delete(jobCategory);
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteJobCategoryDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return jobCategory;
  }
  
  public MembershipType getMembershipTypeDetails(long membershipTypeId)
  {
    logger.info("Inside getJobCategoryDetails method");
    MembershipType membershipType = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      membershipType = (MembershipType)session.createCriteria(MembershipType.class).add(Restrictions.eq("membershipTypeId", Long.valueOf(membershipTypeId))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getJobCategoryDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return membershipType;
  }
  
  public MembershipType saveMembershipTypeDetails(MembershipType membershipType)
  {
    logger.info("Inside saveMembershipTypeDetails method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      session.save(membershipType);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveMembershipTypeDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return membershipType;
  }
  
  public MembershipType updateMembershipTypeDetails(MembershipType membershipType)
  {
    logger.info("Inside updateMembershipTypeDetails method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      session.update(membershipType);
    }
    catch (Exception e)
    {
      logger.error("Exception on updateMembershipTypeDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return membershipType;
  }
  
  public MembershipType deleteMembershipTypeDetails(MembershipType membershipType)
  {
    logger.info("Inside deleteMembershipTypeDetails method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      session.delete(membershipType);
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteMembershipTypeDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return membershipType;
  }
  
  public List getAllMembershipTypeDetailsForPagination(User user, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getAllMembershipTypeDetailsForPagination method");
    List membershiptypelist = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      Criteria query = session.createCriteria(MembershipType.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      if (dir_str.equalsIgnoreCase("asc")) {
        query.addOrder(Order.asc(sort_str));
      } else {
        query.addOrder(Order.desc(sort_str));
      }
      query = query.setFirstResult(startIndex);
      query.setMaxResults(pageSize);
      membershiptypelist = query.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllMembershipTypeDetailsForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return membershiptypelist;
  }
  
  public List getAllMembershipTypeDetails(User user)
  {
    logger.info("Inside getAllMembershipTypeDetails method");
    List membershiptypelist = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      membershiptypelist = session.createCriteria(MembershipType.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key()))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllMembershipTypeDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return membershiptypelist;
  }
  
  public List getRefferalSchemeTypeDetailsForPagination(User user, int pageSize, int startIndex)
  {
    logger.info("Inside getRefferalSchemeDetailsForPagination method");
    List refferalscheamelist = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria query = session.createCriteria(RefferalSchemeType.class).add(Restrictions.eq("status", "A"));
      query.add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      

      query = query.setFirstResult(startIndex);
      query.setMaxResults(pageSize);
      refferalscheamelist = query.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getRefferalSchemeDetailsForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return refferalscheamelist;
  }
  
  public List getRefferalSchemeDetailsForPagination(User user, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getRefferalSchemeDetailsForPagination method");
    List refferalscheamelist = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria query = session.createCriteria(RefferalScheme.class).add(Restrictions.eq("status", "A"));
      query.add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      if (dir_str.equalsIgnoreCase("asc")) {
        query.addOrder(Order.asc(sort_str));
      } else {
        query.addOrder(Order.desc(sort_str));
      }
      query = query.setFirstResult(startIndex);
      query.setMaxResults(pageSize);
      refferalscheamelist = query.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getRefferalSchemeDetailsForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return refferalscheamelist;
  }
  
  public int getCountOfAllRefferalSchemes(User user)
  {
    logger.info("Inside getCountOfAllRefferalSchemes method");
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      Criteria criteria = session.createCriteria(RefferalScheme.class).add(Restrictions.eq("status", "A"));
      criteria.add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      criteria.setProjection(Projections.rowCount());
      totaluser = ((Integer)criteria.list().get(0)).intValue();
      System.out.println("totaluser" + totaluser);
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfAllRefferalSchemes()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totaluser;
  }
  
  public int getCountOfBudgetCodes(User user)
  {
    logger.info("Inside getCountOfBudgetCodes method");
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria criteria = session.createCriteria(BudgetCode.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      criteria.setProjection(Projections.rowCount());
      totaluser = ((Integer)criteria.list().get(0)).intValue();
      System.out.println("totaluser" + totaluser);
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfBudgetCodes()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totaluser;
  }
  
  public List getAllProjectCodeDetails()
  {
    logger.info("Inside getAllProjectCodeDetails method");
    List projectcodeList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      Criteria query = session.createCriteria(ProjectCodes.class).add(Restrictions.eq("status", "A"));
      
      projectcodeList = query.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllProjectCodeDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return projectcodeList;
  }
  
  public List getAllProjectCodeDetailsforpagination(long orgId, long departmentId)
  {
    logger.info("Inside getAllProjectCodeDetailsforpagination method ... 1");
    List projectcodeList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      Criteria query = session.createCriteria(ProjectCodes.class).add(Restrictions.eq("status", "A"));
      if (orgId != 0L) {
        query.add(Restrictions.like("organization.orgId", Long.valueOf(new Long(orgId).longValue())));
      }
      if (departmentId != 0L) {
        query.add(Restrictions.like("department.departmentId", Long.valueOf(new Long(departmentId).longValue())));
      }
      projectcodeList = query.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllProjectCodeDetailsforpagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return projectcodeList;
  }
  
  public List getAllProjectCodeDetailsForPagination(int pageSize, int startIndex, String dir_str, String sort_str, long orgId, long departmentId)
  {
    logger.info("Inside getAllProjectCodeDetailsForPagination method .. 1");
    List projectCodeList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria query = session.createCriteria(ProjectCodes.class).add(Restrictions.eq("status", "A"));
      if (orgId != 0L) {
        query.add(Restrictions.like("organization.orgId", Long.valueOf(new Long(orgId).longValue())));
      }
      if (departmentId != 0L) {
        query.add(Restrictions.like("department.departmentId", Long.valueOf(new Long(departmentId).longValue())));
      }
      if (dir_str.equalsIgnoreCase("asc")) {
        query.addOrder(Order.asc(sort_str));
      } else {
        query.addOrder(Order.desc(sort_str));
      }
      query = query.setFirstResult(startIndex);
      query.setMaxResults(pageSize);
      projectCodeList = query.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllProjectCodeDetailsForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return projectCodeList;
  }
  
  public List getAllLocationDetailsForPagination(int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getAllLocationDetailsForPagination method");
    List locationList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria query = session.createCriteria(Location.class).add(Restrictions.eq("status", "A"));
      if (dir_str.equalsIgnoreCase("asc")) {
        query.addOrder(Order.asc(sort_str));
      } else {
        query.addOrder(Order.desc(sort_str));
      }
      query.setFirstResult(startIndex);
      query.setMaxResults(pageSize);
      locationList = query.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllLocationDetailsForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return locationList;
  }
  
  public List getAllLocationDetailsForPagination(User user, String locationname, String locationCode, String countryid, String stateid, String city, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getAllLocationDetailsForPagination method");
    List locationList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria query = session.createCriteria(Location.class).add(Restrictions.eq("status", "A"));
      
      query.add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      if ((!StringUtils.isNullOrEmpty(locationname)) && (!locationname.equals("null"))) {
        query.add(Restrictions.like("locationName", "%" + locationname + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(locationCode)) && (!locationCode.equals("null"))) {
        query.add(Restrictions.like("locationCode", "%" + locationCode + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(city)) && (!city.equals("null"))) {
        query.add(Restrictions.like("city", "%" + city + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(countryid)) && (!countryid.equals("0")))
      {
        query.createAlias("country", "country");
        query.add(Restrictions.eq("country.countryId", new Long(countryid)));
      }
      if ((!StringUtils.isNullOrEmpty(stateid)) && (!stateid.equals("0")))
      {
        query.createAlias("state", "state");
        query.add(Restrictions.eq("state.stateId", new Long(stateid)));
      }
      if (dir_str.equalsIgnoreCase("asc")) {
        query.addOrder(Order.asc(sort_str));
      } else {
        query.addOrder(Order.desc(sort_str));
      }
      query.setFirstResult(startIndex);
      query.setMaxResults(pageSize);
      locationList = query.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllLocationDetailsForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return locationList;
  }
  
  public List getAllDesignationDetailsForPagination(int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getAllDesignationDetailsForPagination method ....");
    List designationList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria query = session.createCriteria(Designations.class).add(Restrictions.eq("status", "A"));
      if (dir_str.equalsIgnoreCase("asc")) {
        query.addOrder(Order.asc(sort_str));
      } else {
        query.addOrder(Order.desc(sort_str));
      }
      query.setFirstResult(startIndex);
      query.setMaxResults(pageSize);
      designationList = query.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllDesignationDetailsForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return designationList;
  }
  
  public List getAllDesignationDetailsForPagination(String designationName, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getAllDesignationDetailsForPagination method");
    List designationList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria query = session.createCriteria(Designations.class).add(Restrictions.eq("status", "A"));
      if (!StringUtils.isNullOrEmpty(designationName)) {
        query.add(Restrictions.like("designationName", "%" + designationName + "%"));
      }
      if (dir_str.equalsIgnoreCase("asc")) {
        query.addOrder(Order.asc(sort_str));
      } else {
        query.addOrder(Order.desc(sort_str));
      }
      query.setFirstResult(startIndex);
      query.setMaxResults(pageSize);
      designationList = query.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllDesignationDetailsForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return designationList;
  }
  
  public List getAllJobGradeDetails(long superUserKey)
  {
    logger.info("Inside getAllJobGradeDetails method");
    List jobgradeList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      jobgradeList = session.createCriteria(JobGrade.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(superUserKey))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllJobGradeDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return jobgradeList;
  }
  
  public List getAllCategories()
  {
    logger.info("Inside getAllCategories method");
    List catList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      catList = session.createCriteria(Category.class).add(Restrictions.eq("status", "A")).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllCategories()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return catList;
  }
  
  public List getAllCategoriesDetails(long superUserKey)
  {
    logger.info("Inside getAllCategoriesDetails method");
    List catList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      catList = session.createCriteria(Category.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(superUserKey))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllCategoriesDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return catList;
  }
  
  public List getAllRefferalRuleDetails(User user)
  {
    logger.info("Inside getAllRefferalRuleDetails method");
    List ruleList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      ruleList = session.createCriteria(RefferalRedemptionRule.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key()))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllRefferalRuleDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return ruleList;
  }
  
  public List getAllRefferalRuleDetailsForPagination(User user, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getAllRefferalRuleDetailsForPagination method");
    List ruleList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria query = session.createCriteria(RefferalRedemptionRule.class).add(Restrictions.eq("status", "A"));
      query.add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      if (dir_str.equalsIgnoreCase("asc")) {
        query.addOrder(Order.asc(sort_str));
      } else {
        query.addOrder(Order.desc(sort_str));
      }
      query = query.setFirstResult(startIndex);
      query.setMaxResults(pageSize);
      ruleList = query.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllRefferalRuleDetailsForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return ruleList;
  }
  
  public List getLovDetailsForPagination(User user, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getLovDetailsForPagination method");
    List ruleList = new ArrayList();
    List lovList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      String hqlquery = " select distinct j.lovListId, j.lovListCode, j.status from LovList j where j.super_user_key=:super_user_key group by j.lovListCode ";
      if (dir_str.equalsIgnoreCase("asc")) {
        hqlquery = hqlquery + " order by j." + sort_str + " asc";
      } else {
        hqlquery = hqlquery + " order by j." + sort_str + " desc";
      }
      Query query1 = session.createQuery(hqlquery);
      query1.setLong("super_user_key", user.getSuper_user_key());
      query1 = query1.setFirstResult(startIndex);
      query1.setMaxResults(pageSize);
      ruleList = query1.list();
      for (int i = 0; i < ruleList.size(); i++)
      {
        LovList lv = new LovList();
        Object[] obj = (Object[])ruleList.get(i);
        
        lv.setLovListId(((Long)obj[0]).longValue());
        lv.setLovListCode((String)obj[1]);
        lv.setStatus((String)obj[2]);
        

        lovList.add(lv);
      }
      logger.info("*** rulelist .. " + ruleList.toString());
    }
    catch (Exception e)
    {
      logger.error("Exception on getLovDetailsForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return lovList;
  }
  
  public LovList getLovDetails(long lovid)
  {
    logger.info("Inside getLovDetails method");
    
    LovList lovList = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      lovList = (LovList)session.createCriteria(LovList.class).add(Restrictions.eq("lovListId", Long.valueOf(lovid))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getLovDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return lovList;
  }
  
  public Locale getLocaleName(long localeId)
  {
    logger.info("Inside getLocaleName method");
    
    Locale locale = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      locale = (Locale)session.createCriteria(Locale.class).add(Restrictions.eq("localeId", Long.valueOf(localeId))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getLocaleName()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return locale;
  }
  
  public EmailNotificationSetting getEmailNotificationSettingDetails(String weid, User user)
  {
    logger.info("Inside getEmailNotificationSettingDetails method");
    
    EmailNotificationSetting emailNotificationSetting = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      emailNotificationSetting = (EmailNotificationSetting)session.createCriteria(EmailNotificationSetting.class).add(Restrictions.eq("weid", Integer.valueOf(new Integer(weid).intValue()))).add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key()))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getEmailNotificationSettingDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return emailNotificationSetting;
  }
  
  public void updateEmailNotificationStatus(EmailNotificationSetting emailNotificationSetting)
  {
    logger.info("Inside updateEmailNotificationStatus method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.update(emailNotificationSetting);
    }
    catch (Exception e)
    {
      logger.error("Exception on updateEmailNotificationStatus()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public List getAllLovDetails(User user)
  {
    logger.info("Inside getAllLovDetails method");
    List ruleList = new ArrayList();
    List lovList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      String hqlquery = " select distinct j.lovListId, j.lovListCode , j.status from LovList j where j.super_user_key=:super_user_key  group by j.lovListCode ";
      
      Query query1 = session.createQuery(hqlquery);
      query1.setLong("super_user_key", user.getSuper_user_key());
      ruleList = query1.list();
      for (int i = 0; i < ruleList.size(); i++)
      {
        LovList lv = new LovList();
        Object[] obj = (Object[])ruleList.get(i);
        
        lv.setLovListId(((Long)obj[0]).longValue());
        lv.setLovListCode((String)obj[1]);
        lv.setStatus((String)obj[2]);
        
        lovList.add(lv);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllLovDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return lovList;
  }
  
  public List getAllEmailNotificationFunctionList(User user)
  {
    logger.info("Inside getAllEmailNotificationFunctionList method");
    logger.info("super user key >> " + user.getSuper_user_key());
    List emailFunctionList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      emailFunctionList = session.createCriteria(EmailNotificationSetting.class).add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key()))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getLovlistvaluecodeList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return emailFunctionList;
  }
  
  public List getLovlistvaluecodeList(String lovListCode)
  {
    logger.info("Inside getLovlistvaluecodeList method");
    List lovList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      lovList = session.createCriteria(LovList.class).add(Restrictions.eq("lovListCode", lovListCode)).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getLovlistvaluecodeList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return lovList;
  }
  
  public List getAllJobGradeDetailsForPagination(int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getAllJobGradeDetailsForPagination method");
    List jobgradeList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      



      Criteria outer = session.createCriteria(JobGrade.class).add(Restrictions.eq("status", "A"));
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      jobgradeList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllJobGradeDetailsForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return jobgradeList;
  }
  
  public Tags saveTags(Tags tag)
  {
    logger.info("Inside saveTags method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.save(tag);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveTags()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return tag;
  }
  
  public void saveTimeZone(Timezone tz)
  {
    logger.info("Inside saveTimeZone method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.save(tz);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveTimeZone()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void saveApplicantTag(ApplicantTags apptag)
  {
    logger.info("Inside saveApplicantTag method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.save(apptag);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveApplicantTag()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void deleteApplicantTag(long applicantId, String tagname)
  {
    logger.info("Inside deleteEmailTemplateDetails method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String hql = "delete from ApplicantTags where applicantId = :applicantId and tagname = :tagname";
      Query query = session.createQuery(hql);
      query.setLong("applicantId", applicantId);
      query.setString("tagname", tagname);
      int rowCount = query.executeUpdate();
    }
    catch (Exception e)
    {
      int rowCount;
      logger.error("Exception on deleteApplicantTag()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public QuestionnaireAnswers saveQuestionnaireAns(QuestionnaireAnswers qanswer)
  {
    logger.info("Inside saveQuestionnaireAns method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.save(qanswer);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveQuestionnaireAns()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return qanswer;
  }
  
  public WatchList saveWatchList(WatchList watch)
  {
    logger.info("Inside saveWatchList method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.save(watch);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveWatchList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return watch;
  }
  
  public void copyWatchListTemplateToReq(List watchList, long reqId)
  {
    logger.info("Inside copyWatchListTemplateToReq method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      for (int i = 0; i < watchList.size(); i++)
      {
        WatchList watch = (WatchList)watchList.get(i);
        watch.setIdvalue(reqId);
        watch.setType("REQ");
        session.save(watch);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on copyWatchListTemplateToReq()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public Tags updateTags(Tags tag)
  {
    logger.info("Inside updateTags method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.saveOrUpdate(tag);
    }
    catch (Exception e)
    {
      logger.error("Exception on updateTags()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return tag;
  }
  
  public QuestionGroupApplicants updateQuestionGroupApplicants(QuestionGroupApplicants qnsgrpapp)
  {
    logger.info("Inside updateQuestionGroupApplicants method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.saveOrUpdate(qnsgrpapp);
    }
    catch (Exception e)
    {
      logger.error("Exception on updateQuestionGroupApplicants()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return qnsgrpapp;
  }
  
  public Tags getTagsDetails(String id)
  {
    logger.info("Inside getTagsDetails method");
    Tags tag = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      tag = (Tags)session.createCriteria(Tags.class).add(Restrictions.eq("tagId", new Long(id))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getTagsDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return tag;
  }
  
  public Boolean isTagExist(String tagName, long super_user_key)
  {
    logger.info("Inside isTagExist method");
    Tags tag = null;
    boolean istagexist = false;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      tag = (Tags)session.createCriteria(Tags.class).add(Restrictions.eq("tagName", tagName)).add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key))).uniqueResult();
      if (tag != null) {
        istagexist = true;
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on isTagExist()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return Boolean.valueOf(istagexist);
  }
  
  public Boolean isTagExist(long id, String tagName, long super_user_key)
  {
    logger.info("Inside isTagExist method");
    Tags tag = null;
    boolean istagexist = false;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      tag = (Tags)session.createCriteria(Tags.class).add(Restrictions.eq("tagName", tagName)).add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key))).uniqueResult();
      if ((tag != null) && (id != tag.getTagId())) {
        istagexist = true;
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on isTagExist()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return Boolean.valueOf(istagexist);
  }
  
  public List getAllTagsForPagination(int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getAllTagsForPagination method");
    List tagsList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      



      Criteria outer = session.createCriteria(Tags.class).add(Restrictions.eq("status", "A"));
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      tagsList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllTagsForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return tagsList;
  }
  
  public List getAllTags()
  {
    logger.info("Inside getAllTags method");
    List tagsList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      tagsList = session.createCriteria(Tags.class).add(Restrictions.eq("status", "A")).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllTags()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return tagsList;
  }
  
  public List getAllTags(long superUserKey, String type, String query)
  {
    logger.info("Inside getAllTags method");
    List tagsList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      tagsList = session.createCriteria(Tags.class).add(Restrictions.like("tagName", "%" + query + "%")).add(Restrictions.eq("tagType", type)).add(Restrictions.eq("super_user_key", Long.valueOf(superUserKey))).add(Restrictions.eq("status", "A")).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllTags()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return tagsList;
  }
  
  public List getAllTagsByApplicantId(long applicantId)
  {
    logger.info("Inside getAllTagsByApplicantId method");
    List tagsList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      tagsList = session.createCriteria(ApplicantTags.class).add(Restrictions.eq("applicantId", Long.valueOf(applicantId))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllTagsByApplicantId()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return tagsList;
  }
  
  public List getAllTagsForPaginationBySearchCriteria(User user, String name, String type, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getAllTagsForPaginationBySearchCriteria method");
    List tagsList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      Criteria crit = session.createCriteria(Tags.class).add(Restrictions.eq("status", "A"));
      
      crit.add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      if ((!StringUtils.isNullOrEmpty(name)) && (!name.equals("null"))) {
        crit.add(Restrictions.like("tagName", "%" + name + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(type)) && (!type.equals("null"))) {
        crit.add(Restrictions.like("tagType", "%" + type + "%"));
      }
      crit.setFirstResult(startIndex);
      crit.setMaxResults(pageSize);
      tagsList = crit.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllTagsForPaginationBySearchCriteria()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return tagsList;
  }
  
  public List getAllTagsBySearchCriteria(User user, String name, String type)
  {
    logger.info("Inside getAllTagsBySearchCriteria method");
    List tagsList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      Criteria crit = session.createCriteria(Tags.class).add(Restrictions.eq("status", "A"));
      
      crit.add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      if ((!StringUtils.isNullOrEmpty(name)) && (!name.equals("null"))) {
        crit.add(Restrictions.like("tagName", "%" + name + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(type)) && (!type.equals("null"))) {
        crit.add(Restrictions.like("tagType", "%" + type + "%"));
      }
      tagsList = crit.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllTagsBySearchCriteria()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return tagsList;
  }
  
  public List getAllJobGradeDetailsForPaginationByJobgradeName(User user, String name, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getAllJobGradeDetailsForPaginationByJobgradeName method");
    List jobgradeList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      if ((!StringUtils.isNullOrEmpty(name)) && (!name.equals("null")))
      {
        Criteria crit = session.createCriteria(JobGrade.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key()))).add(Restrictions.like("jobGradeName", "%" + name + "%"));
        
        crit = crit.setFirstResult(startIndex);
        crit.setMaxResults(pageSize);
        jobgradeList = crit.list();
      }
      else
      {
        Criteria crit4 = session.createCriteria(JobGrade.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
        crit4.setFirstResult(startIndex);
        crit4.setMaxResults(pageSize);
        jobgradeList = crit4.list();
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllJobGradeDetailsForPaginationByJobgradeName()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return jobgradeList;
  }
  
  public List getAllJobGradeDetailsByJoggradeName(User user, String name)
  {
    logger.info("Inside getAllJobGradeDetails method");
    List jobgradeList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      if ((!StringUtils.isNullOrEmpty(name)) && (!name.equals("null"))) {
        jobgradeList = session.createCriteria(JobGrade.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key()))).add(Restrictions.like("jobGradeName", "%" + name + "%")).list();
      } else {
        jobgradeList = session.createCriteria(JobGrade.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key()))).list();
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllJobGradeDetailsByJoggradeName()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return jobgradeList;
  }
  
  public List getAllAccopmlishmentDetails(User user, String accomplishmentName)
  {
    logger.info("Inside getAllAccopmlishmentDetails method");
    logger.info("accomplishmentName : " + accomplishmentName);
    List accomplishmentList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = session.createCriteria(Accomplishments.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      if ((!StringUtils.isNullOrEmpty(accomplishmentName)) && (!accomplishmentName.equals("null"))) {
        outer.add(Restrictions.like("accName", "%" + accomplishmentName + "%"));
      }
      accomplishmentList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllAccopmlishmentDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return accomplishmentList;
  }
  
  public List getAllAccopmlishmentDetailsForPagination(User user, int pageSize, int startIndex, String dir_str, String sort_str, String accomplishmentName)
  {
    logger.info("Inside getAllAccopmlishmentDetailsForPagination method");
    logger.info("accomplishmentName : " + accomplishmentName);
    List accomplishmentList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      




      Criteria outer = session.createCriteria(Accomplishments.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      if ((!StringUtils.isNullOrEmpty(accomplishmentName)) && (!accomplishmentName.equals("null"))) {
        outer.add(Restrictions.like("accName", "%" + accomplishmentName + "%"));
      }
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      
      accomplishmentList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllAccopmlishmentDetailsForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return accomplishmentList;
  }
  
  public List getAllJobCodeDetails()
  {
    logger.info("Inside getAllJobCodeDetails method");
    List jobcodeList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      jobcodeList = session.createCriteria(JobCode.class).add(Restrictions.eq("status", "A")).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllJobCodeDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return jobcodeList;
  }
  
  public List getJobCodeDetails(String query)
  {
    logger.info("Inside getJobCodeDetails method");
    List jobcodeList = new ArrayList();
    List<String> newjobCodeList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      





      Criteria outer = session.createCriteria(JobRequisition.class);
      

      outer.add(Restrictions.eq("status", "Open"));
      outer.add(Restrictions.eq("state", "Active"));
      outer.add(Restrictions.like("jobreqcode", "%" + query + "%"));
      
      jobcodeList = outer.list();
      for (int i = 0; i < jobcodeList.size(); i++)
      {
        JobRequisition usr = (JobRequisition)jobcodeList.get(i);
        String jobCode = usr.getJobreqcode();
        newjobCodeList.add(jobCode);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllJobCodeDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return newjobCodeList;
  }
  
  public List getJobtitleforrefferal(String query)
  {
    logger.info("Inside getJobtitle method");
    List jobtitleList = new ArrayList();
    List<String> newjobTitleList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      



      Criteria outer = session.createCriteria(JobRequisition.class);
      

      outer.add(Restrictions.eq("status", "Open"));
      outer.add(Restrictions.eq("state", "Active"));
      outer.add(Restrictions.eq("publishToExternal", "Y"));
      outer.add(Restrictions.like("jobTitle", "%" + query + "%"));
      
      jobtitleList = outer.list();
      for (int i = 0; i < jobtitleList.size(); i++)
      {
        JobRequisition usr = (JobRequisition)jobtitleList.get(i);
        String jobCode = usr.getJobTitle();
        newjobTitleList.add(jobCode);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getJobtitle()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return newjobTitleList;
  }
  
  public List getJobtitle(String query)
  {
    logger.info("Inside getJobtitle method");
    List jobtitleList = new ArrayList();
    List<String> newjobTitleList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      



      Criteria outer = session.createCriteria(JobRequisition.class);
      outer.add(Restrictions.eq("status", "Open"));
      outer.add(Restrictions.eq("state", "Active"));
      
      outer.add(Restrictions.like("jobTitle", "%" + query + "%"));
      
      jobtitleList = outer.list();
      for (int i = 0; i < jobtitleList.size(); i++)
      {
        JobRequisition usr = (JobRequisition)jobtitleList.get(i);
        String jobCode = usr.getJobTitle();
        newjobTitleList.add(jobCode);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getJobtitle()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return newjobTitleList;
  }
  
  public List getAllJobCodeDetailsForPagination(int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getAllJobCodeDetailsForPagination method");
    List jobcodeList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = session.createCriteria(JobCode.class).add(Restrictions.eq("status", "A"));
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      jobcodeList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllJobCodeDetailsForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return jobcodeList;
  }
  
  public List getAllBudgetCodeDetails(long superUserKey)
  {
    logger.info("Inside getAllBudgetCodeDetails method");
    List budgetCodeList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      budgetCodeList = session.createCriteria(BudgetCode.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(superUserKey))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllBudgetCodeDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return budgetCodeList;
  }
  
  public List getAllBudgetCodeDetailsForPagination(User user, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getAllBudgetCodeDetailsForPagination method");
    List budgetCodeList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria query = session.createCriteria(BudgetCode.class).add(Restrictions.eq("status", "A"));
      query.add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      if (dir_str.equalsIgnoreCase("asc")) {
        query.addOrder(Order.asc(sort_str));
      } else {
        query.addOrder(Order.desc(sort_str));
      }
      query = query.setFirstResult(startIndex);
      query.setMaxResults(pageSize);
      budgetCodeList = query.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllBudgetCodeDetailsForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return budgetCodeList;
  }
  
  public List getAllRefferalBudgetCodeDetails(User user)
  {
    logger.info("Inside getAllRefferalBudgetCodeDetails method");
    List refferalbudgetCodeList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      refferalbudgetCodeList = session.createCriteria(RefferalBudgetCode.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key()))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllRefferalBudgetCodeDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return refferalbudgetCodeList;
  }
  
  public List getAllRefferalBudgetDetailsForPagination(User user, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getAllRefferalBudgetDetailsForPagination method");
    List refferalbudgetCodeList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      Criteria outer = session.createCriteria(RefferalBudgetCode.class).add(Restrictions.eq("status", "A"));
      outer.add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      refferalbudgetCodeList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllRefferalBudgetDetailsForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return refferalbudgetCodeList;
  }
  
  public List getAllRolesForPagination(User user, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getAllRolesForPagination method");
    List roleList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      Criteria outer = session.createCriteria(Role.class).add(Restrictions.eq("status", "A"));
      
      outer.add(Restrictions.ne("roleCode", "VENDOR")).add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key()))).list();
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      roleList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllRolesForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return roleList;
  }
  
  public List getAllLocales()
  {
    logger.info("Inside getAllLocales method");
    List roleList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      roleList = session.createCriteria(Locale.class).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllLocales()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return roleList;
  }
  
  public List getAllTimezones()
  {
    logger.info("Inside getAllTimezones method");
    List roleList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      roleList = session.createCriteria(Timezone.class).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllTimezones()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return roleList;
  }
  
  public List getAllDepartments()
  {
    logger.info("Inside getAllDepartments method");
    List roleList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      roleList = session.createCriteria(Department.class).add(Restrictions.eq("status", "A")).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllDepartments()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return roleList;
  }
  
  public List getAllCountries()
  {
    logger.info("Inside getAllCountries method");
    List roleList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      roleList = session.createCriteria(Country.class).add(Restrictions.eq("status", "A")).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllCountries()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return roleList;
  }
  
  public List getAllResumeSourceTypes()
  {
    logger.info("Inside getAllResumeSourceTypes method");
    List roleList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      roleList = session.createCriteria(ResumeSourceType.class).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllResumeSourceTypes()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return roleList;
  }
  
  public List getAllResumeSource(int typeId)
  {
    logger.info("Inside getAllResumeSource method");
    List roleList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      roleList = session.createCriteria(ResumeSources.class).add(Restrictions.eq("resumeSourceTypeId", Integer.valueOf(typeId))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllResumeSource()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return roleList;
  }
  
  public List getAllJobRequistions()
  {
    logger.info("Inside getAllJobRequistions method");
    List roleList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      roleList = session.createCriteria(JobRequisition.class).add(Restrictions.eq("state", "Active")).add(Restrictions.eq("status", "Open")).list();
      JobRequisition jb;
      for (int i = 0; i < roleList.size(); i++) {
        jb = (JobRequisition)roleList.get(i);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllJobRequistions()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return roleList;
  }
  
  public List getLOVData(String className)
  {
    logger.info("Inside getDepartments method");
    List lovList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      lovList = session.createCriteria(className).add(Restrictions.eq("status", "A")).list();
      System.out.println(lovList.size());
    }
    catch (Exception e)
    {
      logger.error("Exception on getDepartments()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return lovList;
  }
  
  public List getStateList()
  {
    logger.info("Inside getStateList method");
    List stateList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      stateList = session.createCriteria(State.class).add(Restrictions.eq("status", "A")).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getStateList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return stateList;
  }
  
  public List getStateListByCountry(long countryId)
  {
    logger.info("Inside getStateList method");
    List stateList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      stateList = session.createCriteria(State.class).add(Restrictions.eq("status", "A")).createAlias("country", "ct").add(Restrictions.eq("ct.countryId", new Long(countryId))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getStateList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return stateList;
  }
  
  public List getCityList(long stateId)
  {
    logger.info("Inside getStateList method");
    List cityList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      cityList = session.createCriteria(City.class).add(Restrictions.eq("status", "A")).createAlias("state", "st").add(Restrictions.eq("st.stateId", new Long(stateId))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getStateList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return cityList;
  }
  
  public List getAllPermissions()
  {
    logger.info("Inside getAllPermissions method");
    List roleList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      roleList = session.createCriteria(Permissions.class).add(Restrictions.eq("status", "A")).addOrder(Order.asc("perCode")).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllPermissions()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    logger.info("Inside getAllPermissions method" + roleList.size());
    return roleList;
  }
  
  public Permissions getPermissions()
  {
    logger.info("Inside getPermissions method");
    Permissions permission = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      permission = (Permissions)session.createCriteria(Permissions.class).add(Restrictions.eq("status", "A")).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getPermissions()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    logger.info("permission123" + permission);
    return permission;
  }
  
  public List getCharListForPagination(User user, int pageSize, int startIndex, String dir_str, String sort_str, String charname)
  {
    logger.info("Inside getCharListForPagination method");
    List selList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      Criteria outer = session.createCriteria(Characteristic.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      if (!StringUtils.isNullOrEmpty(charname)) {
        outer.add(Restrictions.like("charName", "%" + charname + "%"));
      }
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      selList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getCharListForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return selList;
  }
  
  public List getCompetencies(String query)
  {
    logger.info("Inside getCompetencies method");
    List compList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      Criteria outer = session.createCriteria(Characteristic.class).add(Restrictions.eq("status", "A")).add(Restrictions.like("charName", "%" + query + "%"));
      
      compList = outer.list();
      
      logger.info("compList size" + compList.size());
    }
    catch (Exception e)
    {
      logger.error("Exception on getCompetencies()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return compList;
  }
  
  public List getAccomplishments(String query)
  {
    logger.info("Inside getAccomplishments method");
    List compList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      Criteria outer = session.createCriteria(Accomplishments.class).add(Restrictions.eq("status", "A")).add(Restrictions.like("accName", "%" + query + "%"));
      
      compList = outer.list();
      
      logger.info("accomp size" + compList.size());
    }
    catch (Exception e)
    {
      logger.error("Exception on getAccomplishments()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return compList;
  }
  
  public List getGroupCharListForPagination(int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getGroupCharListForPagination method");
    List selList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      





      Criteria outer = session.createCriteria(GroupCharacteristic.class).add(Restrictions.eq("status", "A"));
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      selList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getGroupCharListForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return selList;
  }
  
  public List getEvaluationTmplListForPagination(int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getEvaluationTmplListForPagination method");
    List selList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      






      Criteria outer = session.createCriteria(EvaluationTemplate.class).add(Restrictions.eq("status", "A"));
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      selList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getEvaluationTmplListForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return selList;
  }
  
  public int getCountOfAllGroupsChar()
  {
    logger.info("Inside getCountOfAllGroupsChar method");
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria criteria = session.createCriteria(GroupCharacteristic.class).add(Restrictions.eq("status", "A"));
      criteria.setProjection(Projections.rowCount());
      totaluser = ((Integer)criteria.list().get(0)).intValue();
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfAllGroupsChar()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totaluser;
  }
  
  public int getCountOfAllEvaluationTemplates()
  {
    logger.info("Inside getCountOfAllEvaluationTemplates method");
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      Criteria criteria = session.createCriteria(EvaluationTemplate.class).add(Restrictions.eq("status", "A"));
      criteria.setProjection(Projections.rowCount());
      totaluser = ((Integer)criteria.list().get(0)).intValue();
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfAllEvaluationTemplates()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totaluser;
  }
  
  public List getRoundListForPagination(int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getRoundListForPagination method");
    List selList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      






      Criteria outer = session.createCriteria(Round.class);
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      selList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getRoundListForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return selList;
  }
  
  public List getAllCharList()
  {
    logger.info("Inside getAllCharList method");
    List selList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      selList = session.createCriteria(Characteristic.class).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllCharList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return selList;
  }
  
  public int getCountOfAllChar(User user, String charname)
  {
    logger.info("Inside getCountOfAllChar method");
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria criteria = session.createCriteria(Characteristic.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      if (!StringUtils.isNullOrEmpty(charname)) {
        criteria.add(Restrictions.like("charName", "%" + charname + "%"));
      }
      criteria.setProjection(Projections.rowCount());
      totaluser = ((Integer)criteria.list().get(0)).intValue();
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfAllChar()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totaluser;
  }
  
  public List getAllRoundList()
  {
    logger.info("Inside getAllRoundList method");
    List selList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      selList = session.createCriteria(Round.class).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllRoundList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return selList;
  }
  
  public List getOrganizationTypeList()
  {
    logger.info("Inside getOrganizationTypeList method");
    List selList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      selList = session.createCriteria(OrganizationType.class).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getOrganizationTypeList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return selList;
  }
  
  public List getJobTypeList(long superUserKey)
  {
    logger.info("Inside getJobTypeList method");
    List selList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      selList = session.createCriteria(JobType.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(superUserKey))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getJobTypeList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return selList;
  }
  
  public List getMembershipTypesList()
  {
    logger.info("Inside getMembershipTypesList method");
    List membershipTypesList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      membershipTypesList = session.createCriteria(MembershipType.class).add(Restrictions.eq("status", "A")).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getMembershipTypesList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return membershipTypesList;
  }
  
  public List getJobGradeList()
  {
    logger.info("Inside getJobGradeList method");
    List selList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      selList = session.createCriteria(JobGrade.class).add(Restrictions.eq("status", "A")).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getJobGradeList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return selList;
  }
  
  public List getAllBudgetCodeDetailsbyorganizationanddepartment(String orgid, String departmentid)
  {
    logger.info("Inside getAllBudgetCodeDetailsbyorganizationanddepartment method");
    List budgetcodelist = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      if ((StringUtils.isNullOrEmpty(departmentid)) || (departmentid.equals("0"))) {
        budgetcodelist = session.createCriteria(BudgetCode.class).createAlias("org", "org").add(Restrictions.eq("org.orgId", new Long(orgid))).list();
      } else {
        budgetcodelist = session.createCriteria(BudgetCode.class).createAlias("org", "org").createAlias("department", "department").add(Restrictions.eq("org.orgId", new Long(orgid))).add(Restrictions.eq("department.departmentId", new Long(departmentid))).list();
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllBudgetCodeDetailsbyorganizationanddepartment()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return budgetcodelist;
  }
  
  public List getAllProjectCodeDetailsbyorganizationanddepartment(String orgid, String departmentid)
  {
    logger.info("Inside getAllProjectCodeDetailsbyorganizationanddepartment method");
    List projectcodelist = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      if ((StringUtils.isNullOrEmpty(departmentid)) || (departmentid.equals("0"))) {
        projectcodelist = session.createCriteria(ProjectCodes.class).createAlias("organization", "organization").add(Restrictions.eq("organization.orgId", new Long(orgid))).list();
      } else {
        projectcodelist = session.createCriteria(ProjectCodes.class).createAlias("organization", "organization").createAlias("department", "department").add(Restrictions.eq("organization.orgId", new Long(orgid))).add(Restrictions.eq("department.departmentId", new Long(departmentid))).list();
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllProjectCodeDetailsbyorganizationanddepartment()", e);
    }
    finally
    {
      logger.info("Inside finally method22");
    }
    return projectcodelist;
  }
  
  public List getWorkShiftList()
  {
    logger.info("Inside getWorkShiftList method");
    List selList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      selList = session.createCriteria(WorkShift.class).add(Restrictions.eq("status", "A")).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getWorkShiftList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return selList;
  }
  
  public List getFlsaStatusList()
  {
    logger.info("Inside getFlsaStatusList method");
    List selList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      selList = session.createCriteria(FlsaStatus.class).add(Restrictions.eq("status", "A")).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getFlsaStatusList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return selList;
  }
  
  public List getCompFrequencyList()
  {
    logger.info("Inside getCompFrequencyList method");
    List selList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      selList = session.createCriteria(CompFrequency.class).add(Restrictions.eq("status", "A")).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getCompFrequencyList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return selList;
  }
  
  public EmailEvents getEmailEventsDetails(String id)
  {
    logger.info("Inside getEmailEventsDetails method");
    EmailEvents emailEvents = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      emailEvents = (EmailEvents)session.createCriteria(EmailEvents.class).add(Restrictions.eq("emailEventId", new Long(id))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getEmailEventsDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return emailEvents;
  }
  
  public List getEmailTempltesForPagination(User user, String emailtemplatename, String emailSubject, String createdBy, String dir_str, String sort_str, int pageSize, int startIndex)
  {
    logger.info("Inside getEmailTempltesForPagination method");
    logger.info("createdby" + createdBy);
    List emailtemplList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = session.createCriteria(EmailTemplates.class);
      outer.add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      if ((!StringUtils.isNullOrEmpty(emailtemplatename)) && (!emailtemplatename.equals("null"))) {
        outer.add(Restrictions.like("emailtemplatename", "%" + emailtemplatename + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(emailSubject)) && (!emailSubject.equals("null"))) {
        outer.add(Restrictions.like("emailSubject", "%" + emailSubject + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(createdBy)) && (!createdBy.equals("null"))) {
        outer.add(Restrictions.like("createdBy", "%" + createdBy + "%"));
      }
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      emailtemplList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getEmailTempltesForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return emailtemplList;
  }
  
  public List getEmailTempltesForPagination(int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getEmailTempltesForPagination method");
    List emailtemplList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = session.createCriteria(EmailTemplates.class);
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      emailtemplList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getEmailTempltesForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return emailtemplList;
  }
  
  public int getCountOfAllEmailTemplates(User user, String emailtemplatename, String emailSubject, String createdBy)
  {
    logger.info("Inside getCountOfAllEmailTemplates method");
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      Criteria outer = session.createCriteria(EmailTemplates.class);
      outer.add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      if ((!StringUtils.isNullOrEmpty(emailtemplatename)) && (!emailtemplatename.equals("null"))) {
        outer.add(Restrictions.like("emailtemplatename", "%" + emailtemplatename + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(emailSubject)) && (!emailSubject.equals("null"))) {
        outer.add(Restrictions.like("emailSubject", "%" + emailSubject + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(createdBy)) && (!createdBy.equals("null"))) {
        outer.add(Restrictions.like("createdBy", "%" + createdBy + "%"));
      }
      outer.setProjection(Projections.rowCount());
      totaluser = ((Integer)outer.list().get(0)).intValue();
      System.out.println("totaluser" + totaluser);
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfAllEmailTemplates()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totaluser;
  }
  
  public int getCountOfAllEmailTemplates()
  {
    logger.info("Inside getCountOfAllEmailTemplates method");
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      Criteria criteria = session.createCriteria(EmailTemplates.class);
      criteria.setProjection(Projections.rowCount());
      totaluser = ((Integer)criteria.list().get(0)).intValue();
      System.out.println("totaluser" + totaluser);
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfAllEmailTemplates()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totaluser;
  }
  
  public EmailTemplates getEmailTemplateDetails(String id)
  {
    logger.info("Inside getEmailTemplateDetails method");
    EmailTemplates empt = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      empt = (EmailTemplates)session.createCriteria(EmailTemplates.class).add(Restrictions.eq("emailtemplateId", new Long(id))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getEmailTemplateDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return empt;
  }
  
  public EmailTemplates getEmailTemplateDetailsByDefaultComponent(String component)
  {
    logger.info("Inside getEmailTemplateDetailsByDefaultComponent method");
    EmailTemplates empt = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      empt = (EmailTemplates)session.createCriteria(EmailTemplates.class).add(Restrictions.eq("defaultcomponent", component)).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getEmailTemplateDetailsByDefaultComponent()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return empt;
  }
  
  public void deleteEmailTemplateDetails(String id)
  {
    logger.info("Inside deleteEmailTemplateDetails method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String hql = "delete from EmailTemplates where emailtemplateId = :id";
      Query query = session.createQuery(hql);
      query.setLong("id", new Long(id).longValue());
      int rowCount = query.executeUpdate();
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteEmailTemplateDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public EmailTemplates saveEmailTemplate(EmailTemplates emtmpl)
  {
    logger.info("Inside saveEmailTemplate method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.save(emtmpl);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveEmailTemplate()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return emtmpl;
  }
  
  public EmailTemplates updateEmailTemplate(EmailTemplates emtmpl)
  {
    logger.info("Inside updateEmailTemplate method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.update(emtmpl);
    }
    catch (Exception e)
    {
      logger.error("Exception on updateEmailTemplate()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return emtmpl;
  }
  
  public EmailTemplates saveeEmailTemplate(EmailTemplates emtmpl)
  {
    logger.info("Inside saveeEmailTemplate method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.save(emtmpl);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveeEmailTemplate()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return emtmpl;
  }
  
  public OrganizationEmailTemplate updateOrgEmailTemplate(OrganizationEmailTemplate orgEmailTemplate)
  {
    logger.info("Inside updateOrgEmailTemplate method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.update(orgEmailTemplate);
    }
    catch (Exception e)
    {
      logger.error("Exception on updateOrgEmailTemplate()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return orgEmailTemplate;
  }
  
  public OrganizationEmailTemplate saveOrgEmailTemplate(OrganizationEmailTemplate orgEmailTemplate)
  {
    logger.info("Inside saveOrgEmailTemplate method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.save(orgEmailTemplate);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveOrgEmailTemplate()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return orgEmailTemplate;
  }
  
  public UrlEncodeData saveeEncodeUrl(UrlEncodeData emtmpl)
  {
    logger.info("Inside saveeEncodeUrl method");
    
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
  
  public UrlEncodeData getEncodeUrl(String encodekey)
  {
    logger.info("Inside getEncodeUrl method");
    UrlEncodeData empt = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      empt = (UrlEncodeData)session.createCriteria(UrlEncodeData.class).add(Restrictions.eq("encodedurl", encodekey)).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getEncodeUrl()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return empt;
  }
  
  public UrlEncodeData getEncodeUrlByUrl(String url)
  {
    logger.info("Inside getEncodeUrlByUrl method");
    UrlEncodeData empt = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      empt = (UrlEncodeData)session.createCriteria(UrlEncodeData.class).add(Restrictions.eq("url", url)).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getEncodeUrlByUrl()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return empt;
  }
  
  public List getAllOfferDeclinedResons()
  {
    logger.info("Inside getAllOfferDeclinedResons method");
    List<DeclinedResons> declinedresasonList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      declinedresasonList = session.createCriteria(DeclinedResons.class).add(Restrictions.eq("resonType", "offer_decline")).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllOfferDeclinedResons()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return declinedresasonList;
  }
  
  public List getAllApplicantRejectionResons()
  {
    logger.info("Inside getAllOfferDeclinedResons method");
    List roleList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      roleList = session.createCriteria(DeclinedResons.class).add(Restrictions.eq("resonType", "applicant_reject")).list();
      logger.info(Integer.valueOf(roleList.size()));
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllApplicantRejectionResons()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return roleList;
  }
  
  public String getRejectedReasonName(long applicantId)
  {
    logger.info("Inside getRejectedReasonName method");
    String rejectionreasonname = "";
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      JobApplicant applicant = (JobApplicant)session.createCriteria(JobApplicant.class).add(Restrictions.eq("applicantId", new Long(applicantId))).uniqueResult();
      if (applicant.getRejectionreasonId() != 0) {
        if (applicant.getRejectionreasonId() == -1)
        {
          rejectionreasonname = applicant.getRejectionreasonname();
        }
        else
        {
          DeclinedResons dr = (DeclinedResons)session.createCriteria(DeclinedResons.class).add(Restrictions.eq("offerdeclinedreasonId", new Integer(applicant.getRejectionreasonId()))).uniqueResult();
          rejectionreasonname = dr.getOfferdecliedreasonName();
        }
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getRejectedReasonName()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return rejectionreasonname;
  }
  
  public List getAllOfferCancelResons()
  {
    logger.info("Inside getAllOfferCancelResons method");
    List roleList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      roleList = session.createCriteria(DeclinedResons.class).add(Restrictions.eq("resonType", "offer_cancel")).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllOfferCancelResons()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return roleList;
  }
  
  public DeclinedResons getOfferDecliendReson(int id)
  {
    logger.info("Inside getOfferDecliendReson method");
    DeclinedResons empt = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      empt = (DeclinedResons)session.createCriteria(DeclinedResons.class).add(Restrictions.eq("offerdeclinedreasonId", Integer.valueOf(id))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getOfferDecliendReson()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return empt;
  }
  
  public List getWatchList(long reqid, long applicantId, String type)
  {
    logger.info("Inside getWatchList method");
    List wlist = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      Criteria outer = session.createCriteria(WatchList.class);
      if ((!StringUtils.isNullOrEmpty(type)) && (type.equals("TEMPLATE")))
      {
        outer.add(Restrictions.eq("idvalue", Long.valueOf(reqid)));
        outer.add(Restrictions.eq("type", "TEMPLATE"));
      }
      else if ((!StringUtils.isNullOrEmpty(type)) && (type.equals("REQ")))
      {
        outer.add(Restrictions.eq("idvalue", Long.valueOf(reqid)));
        outer.add(Restrictions.eq("type", "REQ"));
      }
      else
      {
        outer.add(Restrictions.disjunction().add(Restrictions.eq("idvalue", Long.valueOf(reqid))).add(Restrictions.eq("idvalue", Long.valueOf(applicantId))));
        
        List typeList = new ArrayList();
        typeList.add("REQ");
        typeList.add("APPLICANT");
        outer.add(Restrictions.in("type", typeList));
      }
      outer.addOrder(Order.asc("userUserGrpName"));
      wlist = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getWatchList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return wlist;
  }
  
  public WatchList getWatcher(long reqid, long applicantId, String type, long userUserGrpId, String isGroup)
  {
    logger.info("Inside getWatcher method");
    WatchList watcher = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      Criteria outer = session.createCriteria(WatchList.class);
      outer.add(Restrictions.eq("userUserGrpId", Long.valueOf(userUserGrpId)));
      outer.add(Restrictions.eq("isGroup", isGroup));
      if ((!StringUtils.isNullOrEmpty(type)) && (type.equals("TEMPLATE")))
      {
        outer.add(Restrictions.eq("idvalue", Long.valueOf(reqid)));
        outer.add(Restrictions.eq("type", "TEMPLATE"));
      }
      else if ((!StringUtils.isNullOrEmpty(type)) && (type.equals("REQ")))
      {
        outer.add(Restrictions.eq("idvalue", Long.valueOf(reqid)));
        outer.add(Restrictions.eq("type", "REQ"));
      }
      else
      {
        outer.add(Restrictions.disjunction().add(Restrictions.eq("idvalue", Long.valueOf(reqid))).add(Restrictions.eq("idvalue", Long.valueOf(applicantId))));
        
        List typeList = new ArrayList();
        typeList.add("REQ");
        typeList.add("APPLICANT");
        outer.add(Restrictions.in("type", typeList));
      }
      watcher = (WatchList)outer.uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getWatcher()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return watcher;
  }
  
  public void deleteWatcher(List<Long> idList)
  {
    logger.info("Inside deleteWatcher method");
    TaskData task = null;
    

    String queryString = "delete from WatchList where watchListId IN (:idList)";
    
    Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(queryString);
    
    query.setParameterList("idList", idList);
    
    int rowCount = query.executeUpdate();
    
    logger.info("Inside deleteWatcher method end");
  }
  
  public void deleteExpenses(List<Long> idList)
  {
    logger.info("Inside deleteExpenses method");
    TaskData task = null;
    

    String queryString = "delete from RequistionExpenses where expenseId IN (:idList)";
    
    Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(queryString);
    
    query.setParameterList("idList", idList);
    
    int rowCount = query.executeUpdate();
    
    logger.info("Inside deleteExpenses method end");
  }
  
  public List getQuestionanswerListByApplicantRefId(String apprefid)
  {
    logger.info("Inside getQuestionanswerListByApplicantRefId method");
    List qnsanslist = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      Criteria outer = session.createCriteria(QuestionAnswers.class).add(Restrictions.eq("applicantrefId", Long.valueOf(new Long(apprefid).longValue())));
      outer.addOrder(Order.asc("questionId"));
      qnsanslist = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getQuestionanswerListByApplicantRefId()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return qnsanslist;
  }
  
  public ApplicantReferencee getReferencedetails(String id)
  {
    logger.info("Inside getReferencedetails method");
    ApplicantReferencee empt = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      empt = (ApplicantReferencee)session.createCriteria(ApplicantReferencee.class).add(Restrictions.eq("applicantReferenceId", new Long(id))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getReferencedetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return empt;
  }
  
  public List getReferenceListByApplicantId(long applicantId)
  {
    logger.info("Inside getReferenceListByApplicantId method");
    List refList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      refList = session.createCriteria(ApplicantReferencee.class).add(Restrictions.eq("applicantId", new Long(applicantId))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getReferenceListByApplicantId()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return refList;
  }
  
  public Designations getDesignation(long id)
  {
    logger.info("Inside getDesignation method");
    Designations empt = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      empt = (Designations)session.createCriteria(Designations.class).add(Restrictions.eq("designationId", new Long(id))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getDesignation()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return empt;
  }
  
  public Designations getDesignationByCode(String code)
  {
    logger.info("Inside getDesignationByCode method");
    Designations empt = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      empt = (Designations)session.createCriteria(Designations.class).add(Restrictions.eq("designationCode", code)).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getDesignationByCode()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return empt;
  }
  
  public Locale getLocaleByCode(String code)
  {
    logger.info("Inside getLocaleByCode method");
    Locale locale = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      locale = (Locale)session.createCriteria(Locale.class).add(Restrictions.eq("localeCode", code)).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getLocaleByCode()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return locale;
  }
  
  public Timezone getTimeZoneByCode(String code)
  {
    logger.info("Inside getTimeZoneByCode method");
    Timezone tz = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      tz = (Timezone)session.createCriteria(Timezone.class).add(Restrictions.eq("timezoneCode", code)).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getTimeZoneByCode()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return tz;
  }
  
  public QuestionGroups getQuestionGroup(String id)
  {
    logger.info("Inside getQuestionByGroupId method");
    QuestionGroups qnn = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      qnn = (QuestionGroups)session.createCriteria(QuestionGroups.class).add(Restrictions.eq("questiongroupId", new Long(id))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getQuestionByGroupId()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return qnn;
  }
  
  public Questions getQuestion(String id)
  {
    logger.info("Inside getQuestion method");
    Questions qnn = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      qnn = (Questions)session.createCriteria(Questions.class).add(Restrictions.eq("questionId", new Long(id))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getQuestion()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return qnn;
  }
  
  public OrganizationEmailTemplate getEmailtemplateIdInOrganizationemailtemplate(long id)
  {
    logger.info("Inside getEmailtemplateIdInOrganizationemailtemplate method");
    OrganizationEmailTemplate orgemailtemid = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      orgemailtemid = (OrganizationEmailTemplate)session.createCriteria(OrganizationEmailTemplate.class).add(Restrictions.eq("orgemailtemplid", Long.valueOf(id))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getQuestion()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return orgemailtemid;
  }
  
  public ApplicantReferencee saveApplicantReference(ApplicantReferencee emtmpl)
  {
    logger.info("Inside saveApplicantReference method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.save(emtmpl);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveApplicantReference()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return emtmpl;
  }
  
  public ApplicantReferencee updateeApplicantReference(ApplicantReferencee emtmpl)
  {
    logger.info("Inside updateeApplicantReference method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.update(emtmpl);
    }
    catch (Exception e)
    {
      logger.error("Exception on updateeApplicantReference()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return emtmpl;
  }
  
  public void deleteApplicantReference(String id)
  {
    logger.info("Inside deleteApplicantReference method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String hql = "delete from ApplicantReferencee where applicantReferenceId = :id";
      Query query = session.createQuery(hql);
      query.setLong("id", new Long(id).longValue());
      int rowCount = query.executeUpdate();
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteApplicantReference()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public List getAllorganizationemailtemplate(long orgid)
  {
    logger.info("Inside getAllorganizationemailtemplate method");
    List orgemailList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      orgemailList = session.createCriteria(OrganizationEmailTemplate.class).createAlias("organisation", "organisation").add(Restrictions.eq("organisation.orgId", Long.valueOf(orgid))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllorganizationemailtemplate()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return orgemailList;
  }
  
  public List getAllorganizationemailtemplate()
  {
    logger.info("Inside getAllorganizationemailtemplate method");
    List orgemailList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      orgemailList = session.createCriteria(OrganizationEmailTemplate.class).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllorganizationemailtemplate()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return orgemailList;
  }
  
  public OrganizationEmailTemplate getOrganizationEmailTemplateBYEventcodeOrgid(String event, long id)
  {
    logger.info("Inside getOrganizationEmailTemplateBYEventcodeOrgid method");
    OrganizationEmailTemplate orgemailtemid = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      orgemailtemid = (OrganizationEmailTemplate)session.createCriteria(OrganizationEmailTemplate.class).add(Restrictions.eq("eventCode", event)).createAlias("organisation", "organisation").add(Restrictions.eq("organisation.orgId", Long.valueOf(id))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getQuestion()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return orgemailtemid;
  }
  
  public OrganizationEmailTemplate getOrganizationEmailTemplateById(long id)
  {
    logger.info("Inside getOrganizationEmailTemplateById method");
    OrganizationEmailTemplate orgemailtemid = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      orgemailtemid = (OrganizationEmailTemplate)session.createCriteria(OrganizationEmailTemplate.class).add(Restrictions.eq("orgemailtemplid", Long.valueOf(id))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getOrganizationEmailTemplateById()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return orgemailtemid;
  }
  
  public List getEmailTemplateByCritera(User user, String emailtemplatename, int start, int range)
  {
    logger.info("Inside getEmailTemplateByCritera method");
    List charList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      Criteria crit = session.createCriteria(EmailTemplates.class);
      
      crit.add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      if (!StringUtils.isNullOrEmpty(emailtemplatename)) {
        crit.add(Restrictions.like("emailtemplatename", "%" + emailtemplatename + "%"));
      }
      crit.setFirstResult(start);
      crit.setMaxResults(range);
      charList = crit.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getEmailTemplateByCritera()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return charList;
  }
  
  public int getCountOfEmailTemplateByCriteria(User user, String emailtemplatename)
  {
    logger.info("Inside getCountOfEmailTemplateByCriteria method");
    List charList = new ArrayList();
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      Criteria crit = session.createCriteria(EmailTemplates.class);
      crit.add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      if (!StringUtils.isNullOrEmpty(emailtemplatename)) {
        crit.add(Restrictions.like("emailtemplatename", "%" + emailtemplatename + "%"));
      }
      crit.setProjection(Projections.rowCount());
      totaluser = ((Integer)crit.list().get(0)).intValue();
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfEmailTemplateByCriteria()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totaluser;
  }
  
  public OrganizationEmailTemplate getOrganizationEmailTemplateBYEvent(String event)
  {
    logger.info("Inside getOrganizationEmailTemplateBYEvent method");
    OrganizationEmailTemplate orgemailtemid = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      orgemailtemid = (OrganizationEmailTemplate)session.createCriteria(OrganizationEmailTemplate.class).add(Restrictions.eq("eventCode", event)).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getQuestion()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return orgemailtemid;
  }
  
  public OrganizationEmailTemplate getOrganizationEmailTemplateBYEventIdOrgId(long eventId, long orgid)
  {
    logger.info("Inside getOrganizationEmailTemplateBYEventIdOrgId method");
    OrganizationEmailTemplate orgemailtemid = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      orgemailtemid = (OrganizationEmailTemplate)session.createCriteria(OrganizationEmailTemplate.class).add(Restrictions.eq("emailevent.emailEventId", Long.valueOf(eventId))).add(Restrictions.eq("organisation.orgId", Long.valueOf(orgid))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Inside getOrganizationEmailTemplateBYEventIdOrgId method()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return orgemailtemid;
  }
  
  public List getAllorganizationemailtemplateList()
  {
    logger.info("Inside getAllorganizationemailtemplateList method");
    List orgemailList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      orgemailList = session.createCriteria(OrganizationEmailTemplate.class).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllorganizationemailtemplateList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    System.out.println("''''''''''''kkkkkkkkkkkkkkkkkkkk''''''''''''''''''''''''");
    System.out.println(orgemailList.size());
    return orgemailList;
  }
  
  public List getAllEmailEventsCount()
  {
    logger.info("Inside getAllEmailEventsCount method");
    List emailEventsList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      emailEventsList = session.createCriteria(EmailEvents.class).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllEmailEventsCount()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return emailEventsList;
  }
  
  public List getAllorganizationemailtemplateForPagination(int pageSize, int startIndex)
  {
    logger.info("Inside getAllorganizationemailtemplateForPagination method");
    List orgemailtempList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      




      Query query = session.createQuery("from OrganizationEmailTemplate");
      query = query.setFirstResult(startIndex);
      query.setMaxResults(pageSize);
      orgemailtempList = query.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllorganizationemailtemplateForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return orgemailtempList;
  }
  
  public List getAllEmailEvents()
  {
    logger.info("Inside getAllEmailEvents method");
    List emailEventsList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      Query query = session.createQuery("from EmailEvents");
      emailEventsList = query.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllEmailEvents()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return emailEventsList;
  }
  
  public void saveanswers(List answerlist)
  {
    logger.info("Inside saveanswers method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      for (int i = 0; i < answerlist.size(); i++)
      {
        QuestionAnswers answer = (QuestionAnswers)answerlist.get(i);
        session.save(answer);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on saveanswers()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public ProfilePhoto saveRefEmpProfilePhoto(ProfilePhoto pphoto)
  {
    logger.info("Inside saveUserProfilePhoto method");
    User user = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      session.saveOrUpdate(pphoto);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveUserProfilePhoto()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return pphoto;
  }
  
  public List getTagsList(String tagtype, long superUserKey)
  {
    logger.info("Inside getTagsList method");
    List taglist = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      taglist = session.createCriteria(Tags.class).add(Restrictions.eq("tagType", tagtype)).add(Restrictions.eq("super_user_key", Long.valueOf(superUserKey))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getTagsList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return taglist;
  }
  
  public Tags getTagById(long tagId)
  {
    logger.info("Inside getTagsList method");
    Tags tag = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      tag = (Tags)session.createCriteria(Tags.class).add(Restrictions.eq("tagId", Long.valueOf(tagId))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getTagsList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return tag;
  }
  
  public List getQuestionsByCritera(String questionName, int start, int range)
  {
    logger.info("Inside getQuestionsByCritera method");
    List charList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      Criteria crit = session.createCriteria(Questions.class).add(Restrictions.eq("status", "A"));
      if (!StringUtils.isNullOrEmpty(questionName))
      {
        crit.add(Restrictions.like("questionName", "%" + questionName + "%"));
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
    }
    catch (Exception e)
    {
      logger.error("Exception on getQuestionsByCritera()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return charList;
  }
  
  public int getCountOfQuestionsByCriteria(String questionName)
  {
    logger.info("Inside getCountOfQuestionsByCriteria method");
    List charList = new ArrayList();
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      Criteria crit = session.createCriteria(Questions.class).add(Restrictions.eq("status", "A"));
      if (!StringUtils.isNullOrEmpty(questionName))
      {
        crit.add(Restrictions.like("questionName", "%" + questionName + "%"));
        
        crit.setProjection(Projections.rowCount());
        totaluser = ((Integer)crit.list().get(0)).intValue();
      }
      else
      {
        crit.setProjection(Projections.rowCount());
        totaluser = ((Integer)crit.list().get(0)).intValue();
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfQuestionsByCriteria()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totaluser;
  }
  
  public UserGroup getUserGroup(long usrgrpid)
  {
    logger.info("Inside getUserGroup method");
    UserGroup usrgrp = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      usrgrp = (UserGroup)session.createCriteria(UserGroup.class).add(Restrictions.eq("usergrpId", new Long(usrgrpid))).add(Restrictions.ne("status", "D")).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserGroup()", e);
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
  
  public static Category saveCategory(Category category)
  {
    logger.info("Inside saveCategory method");
    
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.save(category);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on saveCategory()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return category;
  }
  
  public static Category updateCategory(Category category)
  {
    logger.info("Inside updateCategory method");
    
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.saveOrUpdate(category);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on updateCategory()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return category;
  }
  
  public static Category getCategoryDetails(String id)
  {
    logger.info("Inside getCategoryDetails method");
    Category category = null;
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      category = (Category)session.createCriteria(Category.class).add(Restrictions.eq("catId", new Long(id))).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getCategoryDetails()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return category;
  }
  
  public static List getAllCatagoryForPagination(int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getAllCatagoryForPagination method");
    List catagoryList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      Criteria outer = session.createCriteria(Category.class).add(Restrictions.eq("status", "A"));
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      catagoryList = outer.list();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllCatagoryForPagination()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return catagoryList;
  }
  
  public static List getAllCatagory()
  {
    logger.info("Inside getAllCatagory method");
    List catagoryList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      catagoryList = session.createCriteria(Category.class).add(Restrictions.eq("status", "A")).list();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllCatagory()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return catagoryList;
  }
  
  public static List getAllCatagoryForPaginationBySearchCriteria(User user, String name, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getAllCatagoryForPaginationBySearchCriteria method");
    List catagoryList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      if ((!StringUtils.isNullOrEmpty(name)) && (!name.equals("null")))
      {
        Criteria crit = session.createCriteria(Category.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key()))).add(Restrictions.like("catName", "%" + name + "%"));
        

        crit = crit.setFirstResult(startIndex);
        crit.setMaxResults(pageSize);
        catagoryList = crit.list();
      }
      else
      {
        Criteria crit = session.createCriteria(Category.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
        
        crit.setFirstResult(startIndex);
        crit.setMaxResults(pageSize);
        catagoryList = crit.list();
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllCatagoryForPaginationBySearchCriteria()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return catagoryList;
  }
  
  public static List getAllCatagoryBySearchCriteria(User user, String name)
  {
    logger.info("Inside getAllCatagoryBySearchCriteria method");
    List catagoryList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      if ((!StringUtils.isNullOrEmpty(name)) && (!name.equals("null"))) {
        catagoryList = session.createCriteria(Category.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key()))).add(Restrictions.like("catName", "%" + name + "%")).list();
      } else {
        catagoryList = session.createCriteria(Category.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key()))).list();
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllCatagoryBySearchCriteria()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return catagoryList;
  }
  
  public static QuestionGroups getQuestionsByQuestionGroup(long id)
  {
    logger.info("Inside getQuestionsByQuestionGroup method");
    
    QuestionGroups questiongroup = null;
    org.hibernate.Session session = null;
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
  
  public static Questions getQuestiondetails(String id)
  {
    logger.info("Inside getQuestions method");
    Questions question = null;
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      question = (Questions)session.createCriteria(Questions.class).add(Restrictions.eq("questionId", new Long(id))).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getQuestions()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return question;
  }
  
  public static QuestionGroups updateQuestionsGroup(QuestionGroups questiongroup)
  {
    logger.info("Inside updateQuestionsGroup method");
    
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.update(questiongroup);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on updateQuestionsGroup()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return questiongroup;
  }
  
  public static List getQuestionsoptionDetails(String id)
  {
    logger.info("Inside getQuestionsoptionDetails method");
    List queoptLIst = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      queoptLIst = session.createCriteria(QuestionOptions.class).add(Restrictions.eq("questionId", new Long(id))).list();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getQuestionsoptionDetails()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return queoptLIst;
  }
  
  public List getQuestionnaireAnsList(long qnsgrpappid)
  {
    logger.info("Inside getQuestionnaireAnsList method");
    List queLIst = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      queLIst = session.createCriteria(QuestionnaireAnswers.class).add(Restrictions.eq("questionGrpAppId", Long.valueOf(qnsgrpappid))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getQuestionnaireAnsList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return queLIst;
  }
  
  public static QuestionGroups saveQuestionGroup(QuestionGroups questiongroup)
  {
    logger.info("Inside saveQuestionGroup method");
    
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      Questions quws = new Questions();
      


      session.save(questiongroup);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on saveQuestionGroup()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return questiongroup;
  }
  
  public static Questions saveQuestionwithGroup(Questions question, List selecteoption, String correctans)
  {
    logger.info("Inside saveQuestionwithGroup method");
    logger.info("Inside selecteoption method" + selecteoption);
    logger.info("Inside selecteoption method" + selecteoption.size());
    
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      question.setOptionList(selecteoption);
      session.save(question);
      if (selecteoption.size() > 0)
      {
        Iterator itr = selecteoption.iterator();
        while (itr.hasNext())
        {
          QuestionOptions queoption = (QuestionOptions)itr.next();
          QuestionOptions queoption1 = new QuestionOptions();
          queoption1.setQnsoptId(queoption.getQnsoptId());
          queoption1.setQuestionId(question.getQuestionId());
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
      logger.error("Exception on saveQuestion()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return question;
  }
  
  public static QuestionGroups getQuestionsGroupDetails(String id)
  {
    logger.info("Inside getQuestionsGroupDetails method");
    QuestionGroups que = null;
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      que = (QuestionGroups)session.createCriteria(QuestionGroups.class).add(Restrictions.eq("questiongroupId", new Long(id))).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getQuestionsGroupDetails()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return que;
  }
  
  public static QuestionGroupApplicants getQuestionGroupApplicants(long id, long applicantId, String uuid)
  {
    logger.info("Inside QuestionGroupApplicants method");
    QuestionGroupApplicants que = null;
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      que = (QuestionGroupApplicants)session.createCriteria(QuestionGroupApplicants.class).add(Restrictions.eq("qnsGrpAppId", Long.valueOf(id))).add(Restrictions.eq("applicantId", Long.valueOf(applicantId))).add(Restrictions.eq("uuid", uuid)).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on QuestionGroupApplicants()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return que;
  }
  
  public static QuestionOptions deleteQuestionOption(QuestionOptions questionOptions)
  {
    logger.info("Inside deleteQuestionOption method");
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.delete(questionOptions);
      
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
    return questionOptions;
  }
  
  public static Questions updateQuestions(Questions question, List selecteoption, String correctans)
  {
    logger.info("Inside updateQuestions method");
    org.hibernate.Session session = null;
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
  
  public static Questions saveQuestion(Questions question, List selecteoption, String correctans)
  {
    logger.info("Inside saveQuestion method");
    logger.info("Inside selecteoption method" + selecteoption);
    logger.info("Inside selecteoption method" + selecteoption.size());
    
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      question.setOptionList(selecteoption);
      session.save(question);
      if (selecteoption.size() > 0)
      {
        Iterator itr = selecteoption.iterator();
        while (itr.hasNext())
        {
          QuestionOptions queoption = (QuestionOptions)itr.next();
          QuestionOptions queoption1 = new QuestionOptions();
          queoption1.setQnsoptId(queoption.getQnsoptId());
          queoption1.setQuestionId(question.getQuestionId());
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
      logger.error("Exception on saveQuestion()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return question;
  }
  
  public static Questions updateQuestionsforDelete(Questions question)
  {
    logger.info("Inside updateQuestionsforDelete method");
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.update(question);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on updateQuestionsforDelete()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return question;
  }
  
  public String isProjectCodeExist(String projCode)
  {
    logger.info("Inside isProjectCodeExist method");
    String projectcode = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      String hql = "select projCode from ProjectCodes a where a.projCode = :projCode and a.status != :status";
      Query query = session.createQuery(hql);
      query.setString("projCode", projCode);
      query.setString("status", "D");
      
      Object ob = query.uniqueResult();
      if (ob != null) {
        projectcode = (String)ob;
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on isProjectCodeExist()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return projectcode;
  }
  
  public String isRoleCodeExist(String roleCode, long superUserKey)
  {
    logger.info("Inside isRoleCodeExist method");
    String rolecode = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      String hql = "select roleCode from Role a where a.roleCode = :roleCode and a.status != :status and (a.super_user_key =:super_user_key or a.super_user_key=:zerovalue)";
      Query query = session.createQuery(hql);
      query.setString("roleCode", roleCode);
      query.setString("status", "D");
      query.setLong("super_user_key", superUserKey);
      query.setLong("zerovalue", new Long(0L).longValue());
      
      Object ob = query.uniqueResult();
      if (ob != null) {
        rolecode = (String)ob;
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on isRoleCodeExist()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return rolecode;
  }
  
  public List getAllJobTypeForPagination(User user, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getAllJobTypeForPagination method");
    List jobTypeList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      Criteria query = session.createCriteria(JobType.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      if (dir_str.equalsIgnoreCase("asc")) {
        query.addOrder(Order.asc(sort_str));
      } else {
        query.addOrder(Order.desc(sort_str));
      }
      query = query.setFirstResult(startIndex);
      query.setMaxResults(pageSize);
      jobTypeList = query.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllJobTypeForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return jobTypeList;
  }
  
  public List getAllJobTypeDetails(User user)
  {
    logger.info("Inside getAllJobTypeDetails method");
    List jobtypeList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      jobtypeList = session.createCriteria(JobType.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key()))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllJobTypeDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return jobtypeList;
  }
  
  public JobType saveJobTypeDetails(JobType jobType)
  {
    logger.info("Inside saveJobTypeDetails method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      session.save(jobType);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveJobTypeDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return jobType;
  }
  
  public JobType updateJobTypeDetails(JobType jobType)
  {
    logger.info("Inside updateJobTypeDetails method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      session.update(jobType);
    }
    catch (Exception e)
    {
      logger.error("Exception on updateJobTypeDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return jobType;
  }
  
  public JobType getJobTypeDetails(long jobTypeId)
  {
    logger.info("Inside getJobTypeDetails method");
    JobType jobType = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      jobType = (JobType)session.createCriteria(JobType.class).add(Restrictions.eq("jobTypeId", Long.valueOf(jobTypeId))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getJobTypeDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return jobType;
  }
  
  public List getAllWorkShiftForPagination(User user, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getAllWorkShiftForPagination method");
    List workshiftList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      Criteria query = session.createCriteria(WorkShift.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      if (dir_str.equalsIgnoreCase("asc")) {
        query.addOrder(Order.asc(sort_str));
      } else {
        query.addOrder(Order.desc(sort_str));
      }
      query = query.setFirstResult(startIndex);
      query.setMaxResults(pageSize);
      workshiftList = query.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllWorkShiftForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return workshiftList;
  }
  
  public List getAllWorkShiftDetails(User user)
  {
    logger.info("Inside getAllWorkShiftDetails method");
    List emailcampaignList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      emailcampaignList = session.createCriteria(WorkShift.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key()))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllWorkShiftDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return emailcampaignList;
  }
  
  public WorkShift getWorkShiftDetails(long shiftId)
  {
    logger.info("Inside getWorkShiftDetails method");
    WorkShift workShift = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      workShift = (WorkShift)session.createCriteria(WorkShift.class).add(Restrictions.eq("shiftId", Long.valueOf(shiftId))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getWorkShiftDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return workShift;
  }
  
  public WorkShift saveWorkShift(WorkShift workShift)
  {
    logger.info("Inside saveWorkShift method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      session.save(workShift);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveWorkShift()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return workShift;
  }
  
  public WorkShift updateWorkShift(WorkShift workShift)
  {
    logger.info("Inside updateWorkShift method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      session.update(workShift);
    }
    catch (Exception e)
    {
      logger.error("Exception on updateWorkShift()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return workShift;
  }
  
  public List getAllLanguagesForPagination(User user, int pageSize, int startIndex, String dir_str, String sort_str, String languageName)
  {
    logger.info("Inside getAllLanguagesForPagination method");
    List languageList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      Criteria query = session.createCriteria(Languages.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      if ((!StringUtils.isNullOrEmpty(languageName)) && (!languageName.equals("null"))) {
        query.add(Restrictions.like("languageName", "%" + languageName + "%"));
      }
      if (dir_str.equalsIgnoreCase("asc")) {
        query.addOrder(Order.asc(sort_str));
      } else {
        query.addOrder(Order.desc(sort_str));
      }
      query.setFirstResult(startIndex);
      query.setMaxResults(pageSize);
      
      languageList = query.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllLanguagesForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return languageList;
  }
  
  public List getAllLanguagesDetails(User user, String languageName)
  {
    logger.info("Inside getAllLanguagesDetails method");
    List languageList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = session.createCriteria(Languages.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      if ((!StringUtils.isNullOrEmpty(languageName)) && (!languageName.equals("null"))) {
        outer.add(Restrictions.like("languageName", "%" + languageName + "%"));
      }
      languageList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllLanguagesDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return languageList;
  }
  
  public Languages getLanguagesDetails(long languageId)
  {
    logger.info("Inside getLanguagesDetails method");
    Languages languages = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      languages = (Languages)session.createCriteria(Languages.class).add(Restrictions.eq("languageId", Long.valueOf(languageId))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getLanguagesDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return languages;
  }
  
  public Languages saveLanguage(Languages languages)
  {
    logger.info("Inside saveLanguage method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      session.save(languages);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveLanguage()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return languages;
  }
  
  public Languages updateLanguage(Languages languages)
  {
    logger.info("Inside updateLanguage method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      session.update(languages);
    }
    catch (Exception e)
    {
      logger.error("Exception on updateLanguage()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return languages;
  }
  
  public Languages deleteLanguage(Languages languages)
  {
    logger.info("Inside deleteLanguage method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      session.delete(languages);
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteLanguage()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return languages;
  }
  
  public List getAllLicenseTypeForPagination(User user, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getAllLicenseTypeForPagination method");
    List licensetypeList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      Criteria query = session.createCriteria(LicenseType.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      if (dir_str.equalsIgnoreCase("asc")) {
        query.addOrder(Order.asc(sort_str));
      } else {
        query.addOrder(Order.desc(sort_str));
      }
      query = query.setFirstResult(startIndex);
      query.setMaxResults(pageSize);
      licensetypeList = query.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllLicenseTypeForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return licensetypeList;
  }
  
  public List getAllLicenseTypeDetails(User user)
  {
    logger.info("Inside getAllLicenseTypeDetails method");
    List licensetypeList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      licensetypeList = session.createCriteria(LicenseType.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key()))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllLicenseTypeDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return licensetypeList;
  }
  
  public LicenseType getLicenseTypeDetails(long licenseTypeId)
  {
    logger.info("Inside getLicenseTypeDetails method");
    LicenseType licenseType = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      licenseType = (LicenseType)session.createCriteria(LicenseType.class).add(Restrictions.eq("licenseTypeId", Long.valueOf(licenseTypeId))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getLicenseTypeDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return licenseType;
  }
  
  public LicenseType saveLicenseType(LicenseType licenseType)
  {
    logger.info("Inside saveLicenseType method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      session.save(licenseType);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveLicenseType()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return licenseType;
  }
  
  public LicenseType updateLicenseType(LicenseType licenseType)
  {
    logger.info("Inside updateLicenseType method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      session.update(licenseType);
    }
    catch (Exception e)
    {
      logger.error("Exception on updateLicenseType()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return licenseType;
  }
  
  public List getAllEthnicRaceForPagination(User user, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getAllEthnicRaceForPagination method");
    List ethnicRaceList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      Criteria query = session.createCriteria(EthnicRace.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      if (dir_str.equalsIgnoreCase("asc")) {
        query.addOrder(Order.asc(sort_str));
      } else {
        query.addOrder(Order.desc(sort_str));
      }
      query = query.setFirstResult(startIndex);
      query.setMaxResults(pageSize);
      ethnicRaceList = query.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllEthnicRaceForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return ethnicRaceList;
  }
  
  public List getAllEthnicRaceDetails(User user)
  {
    logger.info("Inside getAllEthnicRaceDetails method");
    List ethnicRaceList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      ethnicRaceList = session.createCriteria(EthnicRace.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key()))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllEthnicRaceDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return ethnicRaceList;
  }
  
  public EthnicRace getEthnicRaceDetails(long ethnicRaceId)
  {
    logger.info("Inside getEthnicRaceDetails method");
    EthnicRace ethnicRace = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      ethnicRace = (EthnicRace)session.createCriteria(EthnicRace.class).add(Restrictions.eq("ethnicRaceId", Long.valueOf(ethnicRaceId))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getEthnicRaceDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return ethnicRace;
  }
  
  public EthnicRace saveEthnicRaceDetails(EthnicRace ethnicRace)
  {
    logger.info("Inside saveEthnicRaceDetails method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      session.save(ethnicRace);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveEthnicRaceDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return ethnicRace;
  }
  
  public EthnicRace updateEthnicRaceDetails(EthnicRace ethnicRace)
  {
    logger.info("Inside updateEthnicRaceDetails method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      session.update(ethnicRace);
    }
    catch (Exception e)
    {
      logger.error("Exception on updateEthnicRaceDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return ethnicRace;
  }
  
  public EthnicRace deleteEthnicRaceDetails(EthnicRace ethnicRace)
  {
    logger.info("Inside deleteEthnicRaceDetails method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      session.delete(ethnicRace);
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteEthnicRaceDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return ethnicRace;
  }
  
  public List getAllEthnicRaceList()
  {
    logger.info("Inside getAllEthnicRaceList method");
    List ethnicRaceList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      ethnicRaceList = session.createCriteria(EthnicRace.class).add(Restrictions.eq("status", "A")).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllEthnicRaceList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return ethnicRaceList;
  }
  
  public List getAllVeteranDisabilityList()
  {
    logger.info("Inside getAllVeteranDisabilityList method");
    List lst = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      lst = session.createCriteria(VeteranDisability.class).add(Restrictions.eq("status", "A")).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllVeteranDisabilityList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return lst;
  }
  
  public List getAllTechnicalSkillDetailsForPagination(User user, int pageSize, int startIndex, String dir_str, String sort_str, String techSkillName)
  {
    logger.info("Inside getAllTechnicalSkillDetailsForPagination method");
    logger.info("accomplishmentName : " + techSkillName);
    List skillList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = session.createCriteria(TechnicalSkills.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      if ((!StringUtils.isNullOrEmpty(techSkillName)) && (!techSkillName.equals("null"))) {
        outer.add(Restrictions.like("technialSkillName", "%" + techSkillName + "%"));
      }
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      
      skillList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllTechnicalSkillDetailsForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return skillList;
  }
  
  public List getAllTechnicalSkillDetails(User user, String techSkillName)
  {
    logger.info("Inside getAllTechnicalSkillDetails method");
    logger.info("techSkillName : " + techSkillName);
    List skillList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = session.createCriteria(TechnicalSkills.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      if ((!StringUtils.isNullOrEmpty(techSkillName)) && (!techSkillName.equals("null"))) {
        outer.add(Restrictions.like("technialSkillName", "%" + techSkillName + "%"));
      }
      skillList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllTechnicalSkillDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return skillList;
  }
  
  public TechnicalSkills getTechnicalSkillsDetails(long technialSkillId)
  {
    logger.info("Inside getTechnicalSkillsDetails method");
    TechnicalSkills technicalSkills = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      technicalSkills = (TechnicalSkills)session.createCriteria(TechnicalSkills.class).add(Restrictions.eq("technialSkillId", Long.valueOf(technialSkillId))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getTechnicalSkillsDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return technicalSkills;
  }
  
  public TechnicalSkills saveTechnicalSkillsDetails(TechnicalSkills technicalSkills)
  {
    logger.info("Inside saveTechnicalSkillsDetails method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      session.save(technicalSkills);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveTechnicalSkillsDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return technicalSkills;
  }
  
  public TechnicalSkills updateTechnicalSkillsDetails(TechnicalSkills technicalSkills)
  {
    logger.info("Inside updateTechnicalSkillsDetails method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      session.update(technicalSkills);
    }
    catch (Exception e)
    {
      logger.error("Exception on updateTechnicalSkillsDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return technicalSkills;
  }
  
  public TechnicalSkills deleteTechnicalSkillsDetails(TechnicalSkills technicalSkills)
  {
    logger.info("Inside deleteTechnicalSkillsDetails method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      session.delete(technicalSkills);
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteTechnicalSkillsDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return technicalSkills;
  }
  
  public List getAllEducationDetailsForPagination(User user, int pageSize, int startIndex, String dir_str, String sort_str, String educationName)
  {
    logger.info("Inside getAllEducationDetailsForPagination method");
    logger.info("educationName : " + educationName);
    List educationList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = session.createCriteria(Education.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      if ((!StringUtils.isNullOrEmpty(educationName)) && (!educationName.equals("null"))) {
        outer.add(Restrictions.like("educationName", "%" + educationName + "%"));
      }
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      
      educationList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllEducationDetailsForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return educationList;
  }
  
  public List getAllEducationDetails(User user, String educationName)
  {
    logger.info("Inside getAllEducationDetails method");
    logger.info("educationName : " + educationName);
    List educationList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = session.createCriteria(TechnicalSkills.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      if ((!StringUtils.isNullOrEmpty(educationName)) && (!educationName.equals("null"))) {
        outer.add(Restrictions.like("educationName", "%" + educationName + "%"));
      }
      educationList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllEducationDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return educationList;
  }
  
  public Education getEducationDetails(long educationId)
  {
    logger.info("Inside getEducationDetails method");
    Education education = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      education = (Education)session.createCriteria(Education.class).add(Restrictions.eq("educationId", Long.valueOf(educationId))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getEducationDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return education;
  }
  
  public Education saveEducation(Education education)
  {
    logger.info("Inside saveEducation method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      session.save(education);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveEducation()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return education;
  }
  
  public Education updateEducation(Education education)
  {
    logger.info("Inside updateEducation method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      session.update(education);
    }
    catch (Exception e)
    {
      logger.error("Exception on updateEducation()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return education;
  }
  
  public Education deleteEducation(Education education)
  {
    logger.info("Inside deleteEducation method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      session.delete(education);
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteEducation()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return education;
  }
  
  public Role getRoleByRoleCode(User user, String rolecode)
  {
    logger.info("Inside getRoleByRoleCode method");
    Role role = null;
    List rlist = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      rlist = session.createCriteria(Role.class).add(Restrictions.eq("roleCode", rolecode)).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key()))).list();
      if ((rlist != null) && (rlist.size() > 0)) {
        role = (Role)rlist.get(0);
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getRoleByRoleCode()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return role;
  }
  
  public static UrlEncodeData saveeEncodeUrlNewSession(UrlEncodeData emtmpl)
  {
    logger.info("Inside saveeEncodeUrlNewSession method");
    User user = null;
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      

      session.save(emtmpl);
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on saveeEncodeUrlNewSession()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return emtmpl;
  }
  
  public Role getRoleByRoleCode(long super_user_key, String rolecode)
  {
    logger.info("Inside getRoleByRoleCode method");
    Role role = null;
    List rlist = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      rlist = session.createCriteria(Role.class).add(Restrictions.eq("roleCode", rolecode)).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key))).list();
      if ((rlist != null) && (rlist.size() > 0)) {
        role = (Role)rlist.get(0);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getRoleByRoleCode()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return role;
  }
  
  public void deleteLocation(long locationId)
    throws ValidationException, Exception
  {
    logger.info("Inside deleteLocation method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String hql = "delete from Location where locationId = :locationId";
      Query query = session.createQuery(hql);
      query.setLong("locationId", locationId);
      int rowCount = query.executeUpdate();
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteLocation()", e);
      if ((e instanceof ConstraintViolationException)) {
        throw new ValidationException("ER105", e.getMessage());
      }
      throw e;
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void deleteDesignation(long designationId)
    throws ValidationException, Exception
  {
    logger.info("Inside deleteDesignation method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String hql = "delete from Designations where designationId = :designationId";
      Query query = session.createQuery(hql);
      query.setLong("designationId", designationId);
      int rowCount = query.executeUpdate();
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteDesignation()", e);
      if ((e instanceof ConstraintViolationException)) {
        throw new ValidationException("ER106", e.getMessage());
      }
      throw e;
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void deleteJobCategory(long jobCategoryId)
    throws ValidationException, Exception
  {
    logger.info("Inside deleteJobCategory method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String hql = "delete from JobCategory where jobCategoryId = :jobCategoryId";
      Query query = session.createQuery(hql);
      query.setLong("jobCategoryId", jobCategoryId);
      int rowCount = query.executeUpdate();
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteJobCategory()", e);
      if ((e instanceof ConstraintViolationException)) {
        throw new ValidationException("ER107", e.getMessage());
      }
      throw e;
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void deleteCategory(long catId)
    throws ValidationException, Exception
  {
    logger.info("Inside deleteCategory method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String hql = "delete from Category where catId = :catId";
      Query query = session.createQuery(hql);
      query.setLong("catId", catId);
      int rowCount = query.executeUpdate();
    }
    catch (Exception e)
    {
      
      logger.error("Exception on deleteCategory()", e);
      if ((e instanceof ConstraintViolationException)) {
        throw new ValidationException("ER108", e.getMessage());
      }
      throw e;
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void deleteJobType(long jobTypeId)
    throws ValidationException, Exception
  {
    logger.info("Inside deleteJobType method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String hql = "delete from JobType where jobTypeId = :jobTypeId";
      Query query = session.createQuery(hql);
      query.setLong("jobTypeId", jobTypeId);
      int rowCount = query.executeUpdate();
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteJobType()", e);
      if ((e instanceof ConstraintViolationException)) {
        throw new ValidationException("ER109", e.getMessage());
      }
      throw e;
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void deleteJobGrade(long jobgradeId)
    throws ValidationException, Exception
  {
    logger.info("Inside deleteJobGrade method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String hql = "delete from JobGrade where jobgradeId = :jobgradeId";
      Query query = session.createQuery(hql);
      query.setLong("jobgradeId", jobgradeId);
      int rowCount = query.executeUpdate();
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteJobGrade()", e);
      if ((e instanceof ConstraintViolationException)) {
        throw new ValidationException("ER110", e.getMessage());
      }
      throw e;
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void deleteWorkShift(long shiftId)
    throws ValidationException, Exception
  {
    logger.info("Inside deleteWorkShift method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String hql = "delete from WorkShift where shiftId = :shiftId";
      Query query = session.createQuery(hql);
      query.setLong("shiftId", shiftId);
      int rowCount = query.executeUpdate();
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteWorkShift()", e);
      if ((e instanceof ConstraintViolationException)) {
        throw new ValidationException("ER111", e.getMessage());
      }
      throw e;
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void deleteSalaryPlan(long salaryplanId)
    throws ValidationException, Exception
  {
    logger.info("Inside deleteSalaryPlan method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String hql = "delete from SalaryPlan where salaryplanId = :salaryplanId";
      Query query = session.createQuery(hql);
      query.setLong("salaryplanId", salaryplanId);
      int rowCount = query.executeUpdate();
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteSalaryPlan()", e);
      if ((e instanceof ConstraintViolationException)) {
        throw new ValidationException("ER112", e.getMessage());
      }
      throw e;
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void deleteBudgetCode(long budgetId)
    throws ValidationException, Exception
  {
    logger.info("Inside deleteBudgetCode method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String hql = "delete from BudgetCode where budgetId = :budgetId";
      Query query = session.createQuery(hql);
      query.setLong("budgetId", budgetId);
      int rowCount = query.executeUpdate();
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteBudgetCode()", e);
      if ((e instanceof ConstraintViolationException)) {
        throw new ValidationException("ER113", e.getMessage());
      }
      throw e;
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void deleteReferralBudgetCode(long ref_budgetId)
    throws ValidationException, Exception
  {
    logger.info("Inside deleteReferralBudgetCode method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String hql = "delete from RefferalBudgetCode where ref_budgetId = :ref_budgetId";
      Query query = session.createQuery(hql);
      query.setLong("ref_budgetId", ref_budgetId);
      int rowCount = query.executeUpdate();
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteReferralBudgetCode()", e);
      if ((e instanceof ConstraintViolationException)) {
        throw new ValidationException("ER114", e.getMessage());
      }
      throw e;
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void deleteReferralScheme(long refferalScheme_Id)
    throws ValidationException, Exception
  {
    logger.info("Inside deleteReferralScheme method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String hql = "delete from RefferalScheme where refferalScheme_Id = :refferalScheme_Id";
      Query query = session.createQuery(hql);
      query.setLong("refferalScheme_Id", refferalScheme_Id);
      int rowCount = query.executeUpdate();
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteReferralScheme()", e);
      if ((e instanceof ConstraintViolationException)) {
        throw new ValidationException("ER115", e.getMessage());
      }
      throw e;
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void deleteOnBoardingTaskDefinition(long taskdefid)
    throws ValidationException, Exception
  {
    logger.info("Inside deleteOnBoardingTaskDefinition method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String hql = "delete from OnBoardingTaskDefinitions where taskdefid = :taskdefid";
      Query query = session.createQuery(hql);
      query.setLong("taskdefid", taskdefid);
      int rowCount = query.executeUpdate();
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteOnBoardingTaskDefinition()", e);
      if ((e instanceof ConstraintViolationException)) {
        throw new ValidationException("ER116", e.getMessage());
      }
      throw e;
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void deleteOnBoardingTemplate(long templateid)
    throws ValidationException, Exception
  {
    logger.info("Inside deleteOnBoardingTemplate method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String hql = "delete from OnBoardingTemplate where templateid = :templateid";
      Query query = session.createQuery(hql);
      query.setLong("templateid", templateid);
      int rowCount = query.executeUpdate();
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteOnBoardingTemplate()", e);
      if ((e instanceof ConstraintViolationException)) {
        throw new ValidationException("ER117", e.getMessage());
      }
      throw e;
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void deleteApplicantFilter(long businessCriteraId)
    throws ValidationException, Exception
  {
    logger.info("Inside deleteApplicantFilter method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String hql = "delete from BusinessCriteria where businessCriteraId = :businessCriteraId";
      Query query = session.createQuery(hql);
      query.setLong("businessCriteraId", businessCriteraId);
      int rowCount = query.executeUpdate();
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteApplicantFilter()", e);
      if ((e instanceof ConstraintViolationException)) {
        throw new ValidationException("ER118", e.getMessage());
      }
      throw e;
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void deleteMemberShipType(long membershipTypeId)
    throws ValidationException, Exception
  {
    logger.info("Inside deleteMemberShipType method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String hql = "delete from MembershipType where membershipTypeId = :membershipTypeId";
      Query query = session.createQuery(hql);
      query.setLong("membershipTypeId", membershipTypeId);
      int rowCount = query.executeUpdate();
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteMemberShipType()", e);
      if ((e instanceof ConstraintViolationException)) {
        throw new ValidationException("ER119", e.getMessage());
      }
      throw e;
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void deleteLanguage(long languageId)
    throws ValidationException, Exception
  {
    logger.info("Inside deleteLanguage method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String hql = "delete from Languages where languageId = :languageId";
      Query query = session.createQuery(hql);
      query.setLong("languageId", languageId);
      int rowCount = query.executeUpdate();
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteLanguage()", e);
      if ((e instanceof ConstraintViolationException)) {
        throw new ValidationException("ER120", e.getMessage());
      }
      throw e;
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void deleteLicenseType(long licenseTypeId)
    throws ValidationException, Exception
  {
    logger.info("Inside deleteLicenseType method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String hql = "delete from LicenseType where licenseTypeId = :licenseTypeId";
      Query query = session.createQuery(hql);
      query.setLong("licenseTypeId", licenseTypeId);
      int rowCount = query.executeUpdate();
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteLicenseType()", e);
      if ((e instanceof ConstraintViolationException)) {
        throw new ValidationException("ER121", e.getMessage());
      }
      throw e;
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void deleteEthnicRace(long ethnicRaceId)
    throws ValidationException, Exception
  {
    logger.info("Inside deleteEthnicRace method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String hql = "delete from EthnicRace where ethnicRaceId = :ethnicRaceId";
      Query query = session.createQuery(hql);
      query.setLong("ethnicRaceId", ethnicRaceId);
      int rowCount = query.executeUpdate();
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteEthnicRace()", e);
      if ((e instanceof ConstraintViolationException)) {
        throw new ValidationException("ER122", e.getMessage());
      }
      throw e;
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public List getAllDeclinedReasonsDetailsForPagination(User user, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getAllDeclinedReasonsDetailsForPagination method");
    List declinedReasonsList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      



      Criteria crit = session.createCriteria(DeclinedResons.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      crit.setFirstResult(startIndex);
      crit.setMaxResults(pageSize);
      declinedReasonsList = crit.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllDeclinedReasonsDetailsForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return declinedReasonsList;
  }
  
  public List getAllDeclinedReasonsDetails(User user)
  {
    logger.info("Inside getAllDeclinedReasonsDetails method");
    List jobgradeList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      jobgradeList = session.createCriteria(JobGrade.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key()))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllDeclinedReasonsDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return jobgradeList;
  }
  
  public DeclinedResons saveDeclinedReasons(DeclinedResons declinedResons)
  {
    logger.info("Inside saveDeclinedReasons method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.save(declinedResons);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveDeclinedReasons()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return declinedResons;
  }
  
  public DeclinedResons getDeclinedReasons(int offerdeclinedreasonId)
  {
    logger.info("Inside getDeclinedReasons method");
    DeclinedResons declinedResons = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      declinedResons = (DeclinedResons)session.createCriteria(DeclinedResons.class).add(Restrictions.eq("offerdeclinedreasonId", Integer.valueOf(offerdeclinedreasonId))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getDeclinedReasons()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return declinedResons;
  }
  
  public DeclinedResons updateDeclinedReasons(DeclinedResons declinedResons)
  {
    logger.info("Inside updateDeclinedReasons method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.update(declinedResons);
    }
    catch (Exception e)
    {
      logger.error("Exception on updateDeclinedReasons()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return declinedResons;
  }
  
  public Feedbacks saveFeedbacks(Feedbacks feedbacks)
  {
    logger.info("Inside saveFeedbacks method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      session.save(feedbacks);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveFeedbacks()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return feedbacks;
  }
}
