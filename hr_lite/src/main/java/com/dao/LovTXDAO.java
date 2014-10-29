package com.dao;

import com.bean.BudgetCode;
import com.bean.CompFrequency;
import com.bean.Department;
import com.bean.Designations;
import com.bean.FlsaStatus;
import com.bean.JobGrade;
import com.bean.JobType;
import com.bean.Location;
import com.bean.Organization;
import com.bean.ProjectCodes;
import com.bean.QuestionGroups;
import com.bean.RefferalBudgetCode;
import com.bean.RefferalRedemptionRule;
import com.bean.RefferalScheme;
import com.bean.RefferalSchemeType;
import com.bean.RequistionExamQnsAssign;
import com.bean.ResumeSources;
import com.bean.SalaryPlan;
import com.bean.SocialUrlMapping;
import com.bean.User;
import com.bean.WorkShift;
import com.bean.lov.Category;
import com.bean.lov.DemoInterest;
import com.bean.lov.Education;
import com.bean.lov.JobCategory;
import com.bean.lov.KeyValue;
import com.bean.lov.LovList;
import com.bean.lov.TechnicalSkills;
import com.bean.testengine.MockQuestionSet;
import com.util.StringUtils;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class LovTXDAO
  extends HibernateDaoSupport
{
  protected static final Logger logger = Logger.getLogger(LovTXDAO.class);
  
  public List getAllOrganization(long superUserKey)
    throws Exception
  {
    logger.info("Inside getAllOrganization method");
    List orgList = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      orgList = session.createCriteria(Organization.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(superUserKey))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllOrganization()", e);
      throw e;
    }
    return orgList;
  }
  
  public RefferalScheme getRefferalSchemeDetails(long id)
  {
    logger.info("Inside getRefferalSchemeDetails method");
    RefferalScheme refferalscheme = null;
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      refferalscheme = (RefferalScheme)session.createCriteria(RefferalScheme.class).add(Restrictions.eq("refferalScheme_Id", Long.valueOf(id))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getRefferalSchemeDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return refferalscheme;
  }
  
  public List getDepartmentListByOrg(String orgid)
    throws Exception
  {
    logger.info("Inside getDepartmentListByOrg method");
    List deptList = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      deptList = session.createCriteria(Department.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("organization.orgId", Long.valueOf(new Long(orgid).longValue()))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getDepartmentListByOrg()", e);
      throw e;
    }
    return deptList;
  }
  
  public List getAllBudgetCodeDetailsbyorganizationanddepartment(String orgid, String departmentid)
    throws Exception
  {
    logger.info("Inside getAllBudgetCodeDetailsbyorganizationanddepartment method");
    List budgetcodelist = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      if (departmentid.equals("0")) {
        budgetcodelist = session.createCriteria(BudgetCode.class).createAlias("org", "org").add(Restrictions.eq("org.orgId", new Long(orgid))).list();
      } else {
        budgetcodelist = session.createCriteria(BudgetCode.class).createAlias("org", "org").createAlias("department", "department").add(Restrictions.eq("org.orgId", new Long(orgid))).add(Restrictions.eq("department.departmentId", new Long(departmentid))).list();
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllBudgetCodeDetailsbyorganizationanddepartment()", e);
      throw e;
    }
    return budgetcodelist;
  }
  
  public List getProjectCodesByDept(String deptId)
    throws Exception
  {
    logger.info("Inside getProjectCodesByDept method");
    List projectList = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      projectList = session.createCriteria(ProjectCodes.class).createAlias("department", "department").add(Restrictions.eq("department.departmentId", new Long(deptId))).add(Restrictions.eq("status", "A")).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getProjectCodesByDept()", e);
      throw e;
    }
    return projectList;
  }
  
  public List getAllLocationDetails(long superUserKey)
    throws Exception
  {
    logger.info("Inside getAllLocationDetails method");
    List locationList = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      locationList = session.createCriteria(Location.class).add(Restrictions.eq("super_user_key", Long.valueOf(superUserKey))).add(Restrictions.eq("status", "A")).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllLocationDetails()", e);
      throw e;
    }
    return locationList;
  }
  
  public List getAllJobGradeDetails(long superUserKey)
    throws Exception
  {
    logger.info("Inside getAllJobGradeDetails method");
    List jobgradeList = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      jobgradeList = session.createCriteria(JobGrade.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(superUserKey))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllJobGradeDetails()", e);
      throw e;
    }
    return jobgradeList;
  }
  
  public List getAllSalaryDetails(long superUserKey)
    throws Exception
  {
    logger.info("Inside getAllSalaryDetails method");
    List salaryList = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      salaryList = session.createCriteria(SalaryPlan.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(superUserKey))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllSalaryDetails()", e);
      throw e;
    }
    return salaryList;
  }
  
  public List getAllCategories(long superUserKey)
    throws Exception
  {
    logger.info("Inside getAllCategories method");
    List catList = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      catList = session.createCriteria(Category.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(superUserKey))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllCategories()", e);
      throw e;
    }
    return catList;
  }
  
  public List getAllJobCategories(long superUserKey)
    throws Exception
  {
    logger.info("Inside getAllJobCategories method");
    List catList = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      catList = session.createCriteria(JobCategory.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(superUserKey))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllJobCategories()", e);
      throw e;
    }
    return catList;
  }
  
  public List getAllDesignations(long superUserKey)
    throws Exception
  {
    logger.info("Inside getAllDesignations method");
    List catList = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      catList = session.createCriteria(Designations.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(superUserKey))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllDesignations()", e);
      throw e;
    }
    return catList;
  }
  
  public List getAllRefferalSchemeDetails()
    throws Exception
  {
    logger.info("Inside getAllRefferalSchemeDetails method");
    List refferalList = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      refferalList = session.createCriteria(RefferalScheme.class).add(Restrictions.eq("status", "A")).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllRefferalSchemeDetails()", e);
      throw e;
    }
    return refferalList;
  }
  
  public List getAllRefferalSchemeByType(String type)
    throws Exception
  {
    logger.info("Inside getAllRefferalSchemeByType method");
    List refferalList = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      refferalList = session.createCriteria(RefferalScheme.class).add(Restrictions.eq("schemeType", type)).add(Restrictions.eq("status", "A")).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllRefferalSchemeByType()", e);
      throw e;
    }
    return refferalList;
  }
  
  public List getJobTypeList(long superUserKey)
    throws Exception
  {
    logger.info("Inside getJobTypeList method");
    List selList = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      selList = session.createCriteria(JobType.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(superUserKey))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getJobTypeList()", e);
      throw e;
    }
    return selList;
  }
  
  public List getResumeSourcesList()
    throws Exception
  {
    logger.info("Inside getResumeSourcesList method");
    List selList = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      selList = session.createCriteria(ResumeSources.class).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getResumeSourcesList()", e);
      throw e;
    }
    return selList;
  }
  
  public List getMockExamListForForm(long superUserKey)
  {
    logger.info("Inside getMockExamList method");
    List mockexamList = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      String sql = "select u.cat_id, u.name from mockquestionset u where status = 'A' and u.super_user_key =:super_user_key";
      Query query = session.createSQLQuery(sql);
      query.setLong("super_user_key", superUserKey);
      List mList = query.list();
      for (int i = 0; i < mList.size(); i++)
      {
        Object[] obj = (Object[])mList.get(i);
        
        MockQuestionSet mk = new MockQuestionSet();
        
        Integer catid = (Integer)obj[0];
        mk.setCatId(catid.intValue());
        mk.setName((String)obj[1]);
        
        mockexamList.add(mk);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getMockExamList()", e);
    }
    return mockexamList;
  }
  
  public List getAddedMockExamList(long jobreqId)
    throws Exception
  {
    logger.info("Inside getAddedMockExamList method");
    List addedexamList = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      addedexamList = session.createCriteria(RequistionExamQnsAssign.class).add(Restrictions.eq("jobreqid", Long.valueOf(jobreqId))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAddedMockExamList()", e);
      throw e;
    }
    return addedexamList;
  }
  
  public MockQuestionSet getMockExamset(int examId, long superuserkey)
  {
    logger.info("Inside getMockExamset method");
    MockQuestionSet mockQuestionSet = null;
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      mockQuestionSet = (MockQuestionSet)session.createCriteria(MockQuestionSet.class).add(Restrictions.eq("catId", Integer.valueOf(examId))).add(Restrictions.eq("super_user_key", Long.valueOf(superuserkey))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getMockExamset()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return mockQuestionSet;
  }
  
  public List getQuestionGroupListForForm(long superUserKey)
  {
    logger.info("Inside getQuestionGroupListForForm method");
    List qnsgroupList = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      String sql = "select u.questions_groups_id, u.questions_groups_name from questions_groups u where status = 'A' and u.super_user_key =:super_user_key";
      Query query = session.createSQLQuery(sql);
      query.setLong("super_user_key", superUserKey);
      List mList = query.list();
      for (int i = 0; i < mList.size(); i++)
      {
        Object[] obj = (Object[])mList.get(i);
        
        QuestionGroups qg = new QuestionGroups();
        
        BigInteger id = (BigInteger)obj[0];
        qg.setQuestiongroupId(id.longValue());
        qg.setQuestiongroupName((String)obj[1]);
        
        qnsgroupList.add(qg);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getQuestionGroupListForForm()", e);
    }
    return qnsgroupList;
  }
  
  public List getWorkShiftList(long superUserKey)
    throws Exception
  {
    logger.info("Inside getWorkShiftList method");
    List selList = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      selList = session.createCriteria(WorkShift.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(superUserKey))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getWorkShiftList()", e);
      throw e;
    }
    return selList;
  }
  
  public List getFlsaStatusList()
    throws Exception
  {
    logger.info("Inside getFlsaStatusList method");
    List selList = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      selList = session.createCriteria(FlsaStatus.class).add(Restrictions.eq("status", "A")).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getFlsaStatusList()", e);
      throw e;
    }
    return selList;
  }
  
  public List getCompFrequencyList()
    throws Exception
  {
    logger.info("Inside getCompFrequencyList method");
    List selList = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      selList = session.createCriteria(CompFrequency.class).add(Restrictions.eq("status", "A")).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getCompFrequencyList()", e);
      throw e;
    }
    return selList;
  }
  
  public List getEducationList(long super_user_key)
    throws Exception
  {
    logger.info("Inside getEducationList method");
    List selList = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      selList = session.createCriteria(Education.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getEducationList()", e);
      throw e;
    }
    return selList;
  }
  
  public List getEducationListKeyValue(long super_user_key)
    throws Exception
  {
    logger.info("Inside getEducationListKeyValue method");
    List selList = new ArrayList();
    List nList = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      selList = session.createCriteria(Education.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key))).list();
      if (selList != null) {
        for (int i = 0; i < selList.size(); i++)
        {
          Education edu = (Education)selList.get(i);
          KeyValue m = new KeyValue();
          m.setKey(edu.getEducationName());
          m.setValue(edu.getEducationName());
          nList.add(m);
        }
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getEducationListKeyValue()", e);
      throw e;
    }
    return nList;
  }
  
  public List getTechnicalSkillListKeyValue(long super_user_key)
    throws Exception
  {
    logger.info("Inside getTechnicalSkillListKeyValue method");
    List selList = new ArrayList();
    List nList = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      selList = session.createCriteria(TechnicalSkills.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key))).list();
      if (selList != null) {
        for (int i = 0; i < selList.size(); i++)
        {
          TechnicalSkills tech = (TechnicalSkills)selList.get(i);
          KeyValue m = new KeyValue();
          m.setKey(tech.getTechnialSkillName());
          m.setValue(tech.getTechnialSkillName());
          nList.add(m);
        }
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getTechnicalSkillListKeyValue()", e);
      throw e;
    }
    return nList;
  }
  
  public List getTechnicalSkillList(long super_user_key)
    throws Exception
  {
    logger.info("Inside getTechnicalSkillList method");
    List selList = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      selList = session.createCriteria(TechnicalSkills.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getTechnicalSkillList()", e);
      throw e;
    }
    return selList;
  }
  
  public List getLOVListValues(String listcode, long localeId)
  {
    logger.info("Inside getLOVListValues method");
    List orgList = new ArrayList();
    Session session = null;
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
  
  public List getAllRefferalBudgetCode(long super_user_key)
  {
    logger.info("Inside getAllRefferalBudgetCode method");
    List refferalBudgetCodeLIst = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      refferalBudgetCodeLIst = session.createCriteria(RefferalBudgetCode.class).add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key))).add(Restrictions.eq("status", "A")).list();
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
  
  public List getAllRefferalSchemeType()
  {
    logger.info("Inside getAllRefferalSchemeType method");
    List refferalSchemeTypeLIst = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      refferalSchemeTypeLIst = session.createCriteria(RefferalSchemeType.class).add(Restrictions.eq("status", "A")).list();
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
  
  public List getAllRefferalSchemes(String usertype, long superuserkey)
  {
    logger.info("Inside getAllRefferalSchemes method");
    List refferalSchemeTypeLIst = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      if (usertype.equals("Vendor")) {
        refferalSchemeTypeLIst = session.createCriteria(RefferalScheme.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("schemeType", "V")).add(Restrictions.eq("super_user_key", Long.valueOf(superuserkey))).list();
      } else {
        refferalSchemeTypeLIst = session.createCriteria(RefferalScheme.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("schemeType", "E")).add(Restrictions.eq("super_user_key", Long.valueOf(superuserkey))).list();
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
  
  public List getAllRefferalRules()
  {
    logger.info("Inside getAllRefferalRules method");
    List ruleList = new ArrayList();
    Session session = null;
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
  
  public List getAllBudgetCodeDetails(long superUserKey)
  {
    logger.info("Inside getAllBudgetCodeDetails method");
    List budgetCodeList = new ArrayList();
    Session session = null;
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
  
  public List getLocationList(long super_user_key)
  {
    logger.info("Inside getLocationList method");
    List locList = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      locList = session.createCriteria(Location.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key))).list();
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
  
  public List getAllProjectCodeDetails()
  {
    logger.info("Inside getAllProjectCodeDetails method");
    List projectcodeList = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      projectcodeList = session.createCriteria(ProjectCodes.class).add(Restrictions.eq("status", "A")).list();
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
  
  public List getAllDesignationDetailsForPagination(int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getAllDesignationDetailsForPagination method ....");
    List designationList = new ArrayList();
    Session session = null;
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
  
  public List getAllDesignationDetailsForPagination(User user, String designationName, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getAllDesignationDetailsForPagination method");
    List designationList = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      Criteria query = session.createCriteria(Designations.class).add(Restrictions.eq("status", "A"));
      
      query.add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      if ((!StringUtils.isNullOrEmpty(designationName)) && (!designationName.equals("null"))) {
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
  
  public int getCountOfAllDesignations()
  {
    logger.info("Inside getCountOfAllDesignations method ...");
    int totaluser = 0;
    Session session = null;
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
  
  public int getCountOfAllDesignations(User user, String designationName)
  {
    logger.info("Inside getCountOfAllDesignations method");
    int totaluser = 0;
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      Criteria query = session.createCriteria(Designations.class).add(Restrictions.eq("status", "A"));
      
      query.add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      if ((!StringUtils.isNullOrEmpty(designationName)) && (!designationName.equals("null"))) {
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
  
  public SocialUrlMapping getSocialUrlMapping(String uuid)
    throws Exception
  {
    logger.info("Inside getSocialUrlMapping method");
    SocialUrlMapping mapping = null;
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      mapping = (SocialUrlMapping)session.createCriteria(SocialUrlMapping.class).add(Restrictions.eq("urlcode", uuid)).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getSocialUrlMapping()", e);
      throw e;
    }
    return mapping;
  }
  
  public SocialUrlMapping saveSocialUrlMapping(SocialUrlMapping socialurl)
  {
    logger.info("Inside saveSocialUrlMapping method");
    
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.save(socialurl);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveSocialUrlMapping()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return socialurl;
  }
  
  public void saveDemoInterest(DemoInterest dm)
  {
    logger.info("Inside saveDemoInterest method");
    
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.save(dm);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveDemoInterest()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public String isDesignationCodeExist(String designationCode, long super_user_key)
  {
    logger.info("Inside isDesignationCodeExist method");
    String designationcode = null;
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      String hql = "select designationCode from Designations a where a.designationCode = :designationCode and a.status != :status and a.super_user_key = :super_user_key";
      Query query = session.createQuery(hql);
      query.setString("designationCode", designationCode);
      query.setString("status", "D");
      query.setLong("super_user_key", super_user_key);
      
      Object ob = query.uniqueResult();
      if (ob != null) {
        designationcode = (String)ob;
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on isDesignationCodeExist()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return designationcode;
  }
}
