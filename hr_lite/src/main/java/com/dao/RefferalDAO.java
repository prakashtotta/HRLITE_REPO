package com.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bean.AgencyRedemption;
import com.bean.FavoriteJob;
import com.bean.ProfilePhoto;
import com.bean.ReferAFriend;
import com.bean.ReferralAgencyApplicants;
import com.bean.ReferralRedemption;
import com.bean.RefferalEmployee;
import com.bean.RefferalRedemptionRule;
import com.bean.RefferalScheme;
import com.bean.User;
import com.bean.criteria.AgencyRedumptionSearchCriteria;
import com.bean.criteria.ReferalRedumptionSearchCriteria;
import com.bean.lov.KeyValue;
import com.util.ConvertBeanUtil;
import com.util.DateUtil;
import com.util.StringUtils;

public class RefferalDAO
  extends HibernateDaoSupport
{
  protected static final Logger logger = Logger.getLogger(RefferalDAO.class);
  
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
  
  public static RefferalEmployee saveeRefferalEmployee(RefferalEmployee employee)
  {
    logger.info("Inside saveeRefferalEmployee method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.save(employee);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on saveeRefferalEmployee()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return employee;
  }
  
  public List getAllReferalByTypeTx()
  {
    logger.info("Inside getAllReferalByTypeTx method");
    List referalList = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      Criteria outer = session.createCriteria(RefferalEmployee.class).add(Restrictions.eq("status", "A"));
      
      referalList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllReferalByTypeTx()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    logger.info("Inside finally method1234" + referalList.size());
    return referalList;
  }
  
  public List exportAgencyRedumptionSearch(User user, AgencyRedumptionSearchCriteria criteria)
  {
    logger.info("Inside exportAgencyRedumptionSearch method");
    List tmplList = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      Criteria outer = session.createCriteria(AgencyRedemption.class);
      outer.add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      if (criteria.getAgencyId() != 0L) {
        outer.add(Restrictions.eq("agencyId", new Long(criteria.getAgencyId())));
      }
      if (!StringUtils.isNullOrEmpty(criteria.getApplicantName())) {
        outer.add(Restrictions.like("applicantName", "%" + criteria.getApplicantName() + "%"));
      }
      if (!StringUtils.isNullOrEmpty(criteria.getApplicantNo())) {
        outer.add(Restrictions.eq("applicantId", new Long(criteria.getApplicantNo())));
      }
      if (criteria.getRequitionId() != 0L) {
        outer.add(Restrictions.eq("jobReqId", new Long(criteria.getRequitionId())));
      }
      if (criteria.getRefBudgetId() != 0L) {
        outer.add(Restrictions.eq("refBudgetId", new Long(criteria.getRefBudgetId())));
      }
      if (criteria.getRefferalSchemeId() != 0L) {
        outer.add(Restrictions.eq("refferalSchemeId", new Long(criteria.getRefferalSchemeId())));
      }
      if (criteria.getRefferalSchemeTypeId() != 0L) {
        outer.add(Restrictions.eq("refferalSchemeTypeId", new Long(criteria.getRefferalSchemeTypeId())));
      }
      if (criteria.getRuleId() != 0) {
        outer.add(Restrictions.eq("ruleId", new Integer(criteria.getRuleId())));
      }
      if (!StringUtils.isNullOrEmpty(criteria.getUom())) {
        outer.add(Restrictions.eq("uom", criteria.getUom()));
      }
      if (!StringUtils.isNullOrEmpty(criteria.getState())) {
        outer.add(Restrictions.eq("state", criteria.getState()));
      }
      if (!StringUtils.isNullOrEmpty(criteria.getIsPaid())) {
        outer.add(Restrictions.eq("isPaid", criteria.getIsPaid()));
      }
      logger.info("releasedate" + criteria.getReleasedate());
      if ((!StringUtils.isNullOrEmpty(criteria.getReleasedate())) && (!criteria.getReleasedate().equals("null")))
      {
        String datepattern = DateUtil.getDatePatternFormat(user.getLocale());
        

        DateFormat format = new SimpleDateFormat(datepattern + " hh:mm:ss");
        
        Date aDate = format.parse(criteria.getReleasedate() + " 00:00:00");
        
        Date bDate = format.parse(criteria.getReleasedate() + " 23:59:00");
        if (criteria.getReleasecri().equals("on")) {
          outer.add(Expression.between("releaseddate", new Date(aDate.getTime()), new Date(bDate.getTime())));
        }
        if (criteria.getReleasecri().equals("after")) {
          outer.add(Expression.gt("releaseddate", new Date(aDate.getTime())));
        }
        if (criteria.getReleasecri().equals("before")) {
          outer.add(Expression.lt("releaseddate", new Date(aDate.getTime())));
        }
      }
      outer.addOrder(Order.desc("agencyId"));
      tmplList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on searchAgencyRedemptions()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    logger.info("tmplList.size" + tmplList.size());
    return tmplList;
  }
  
  public List exportReferalRedumptionSearch(User user, ReferalRedumptionSearchCriteria criteria)
  {
    logger.info("Inside exportAgencyRedumptionSearch method");
    List tmplList = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      Criteria outer = session.createCriteria(ReferralRedemption.class);
      outer.add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      if (!StringUtils.isNullOrEmpty(criteria.getEmpcode())) {
        outer.add(Restrictions.eq("employeecode", new Long(criteria.getEmpcode())));
      }
      if (!StringUtils.isNullOrEmpty(criteria.getApplicantName())) {
        outer.add(Restrictions.like("applicantName", "%" + criteria.getApplicantName() + "%"));
      }
      if (!StringUtils.isNullOrEmpty(criteria.getApplicantNo())) {
        outer.add(Restrictions.eq("applicantId", new Long(criteria.getApplicantNo())));
      }
      if (criteria.getRequitionId() != 0L) {
        outer.add(Restrictions.eq("jobReqId", new Long(criteria.getRequitionId())));
      }
      if (criteria.getRefBudgetId() != 0L) {
        outer.add(Restrictions.eq("refBudgetId", new Long(criteria.getRefBudgetId())));
      }
      if (criteria.getRefferalSchemeId() != 0L) {
        outer.add(Restrictions.eq("refferalSchemeId", new Long(criteria.getRefferalSchemeId())));
      }
      if (criteria.getRefferalSchemeTypeId() != 0L) {
        outer.add(Restrictions.eq("refferalSchemeTypeId", new Long(criteria.getRefferalSchemeTypeId())));
      }
      if (criteria.getRuleId() != 0) {
        outer.add(Restrictions.eq("ruleId", new Integer(criteria.getRuleId())));
      }
      if (!StringUtils.isNullOrEmpty(criteria.getUom())) {
        outer.add(Restrictions.eq("uom", criteria.getUom()));
      }
      if (!StringUtils.isNullOrEmpty(criteria.getState())) {
        outer.add(Restrictions.eq("state", criteria.getState()));
      }
      if (!StringUtils.isNullOrEmpty(criteria.getIsPaid())) {
        outer.add(Restrictions.eq("isPaid", criteria.getIsPaid()));
      }
      if (criteria.getOrgId() != 0L) {
        outer.add(Restrictions.eq("orgId", new Long(criteria.getOrgId())));
      }
      if (criteria.getDepartmentId() != 0L) {
        outer.add(Restrictions.eq("departmentId", new Long(criteria.getDepartmentId())));
      }
      if (!StringUtils.isNullOrEmpty(criteria.getEmpemail())) {
        outer.add(Restrictions.eq("employeeemail", criteria.getEmpemail()));
      }
      if (!StringUtils.isNullOrEmpty(criteria.getEmpname())) {
        outer.add(Restrictions.eq("employeename", criteria.getEmpname()));
      }
      logger.info("releasedate" + criteria.getReleasedate());
      if ((!StringUtils.isNullOrEmpty(criteria.getReleasedate())) && (!criteria.getReleasedate().equals("null")))
      {
        String datepattern = DateUtil.getDatePatternFormat(user.getLocale());
        

        DateFormat format = new SimpleDateFormat(datepattern + " hh:mm:ss");
        
        Date aDate = format.parse(criteria.getReleasedate() + " 00:00:00");
        
        Date bDate = format.parse(criteria.getReleasedate() + " 23:59:00");
        if (criteria.getReleasecri().equals("on")) {
          outer.add(Expression.between("releaseddate", new Date(aDate.getTime()), new Date(bDate.getTime())));
        }
        if (criteria.getReleasecri().equals("after")) {
          outer.add(Expression.gt("releaseddate", new Date(aDate.getTime())));
        }
        if (criteria.getReleasecri().equals("before")) {
          outer.add(Expression.lt("releaseddate", new Date(aDate.getTime())));
        }
      }
      outer.addOrder(Order.desc("applicantId"));
      tmplList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on searchAgencyRedemptions()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    logger.info("tmplList.size" + tmplList.size());
    return tmplList;
  }
  
  public static ReferAFriend savereferfriend(ReferAFriend reffriend)
  {
    logger.info("Inside savereferfriend method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.save(reffriend);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on savereferfriend()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return reffriend;
  }
  
  public static ReferAFriend updatereferfriend(ReferAFriend reffriend)
  {
    logger.info("Inside updatereferfriend method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.update(reffriend);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on updatereferfriend()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return reffriend;
  }
  
  public static void deleteBookMark(long favjobid)
  {
    logger.info("Inside deleteEducation method");
    FavoriteJob favoriteJob = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      favoriteJob = (FavoriteJob)session.createCriteria(FavoriteJob.class).add(Restrictions.eq("favjobid", Long.valueOf(favjobid))).uniqueResult();
      session.delete(favoriteJob);
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteEducation()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
  }
  
  public FavoriteJob saveBookmark(FavoriteJob fav)
  {
    logger.info("Inside saveBookmark method");
    
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      session.save(fav);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveBookmark()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return fav;
  }
  
  public static void updateRefferalEmployee(RefferalEmployee employee)
  {
    logger.info("Inside updateRefferalEmployee method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.update(employee);
      


      User usr = (User)session.createCriteria(User.class).add(Restrictions.eq("emailId", employee.getEmployeeemail())).uniqueResult();
      if (usr != null)
      {
        usr = ConvertBeanUtil.convertEmployeeReferralToUser(usr, employee);
        session.update(usr);
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on updateRefferalEmployee()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
  }
  
  public static List getMyReferralRedumptionAgencyApplicantNames(long agencyId, String query)
  {
    logger.info("Inside getMyReferralRedumptionAgencyApplicantNames method");
    List applicantList = new ArrayList();
    List<String> newFullNameList = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      Criteria outer = session.createCriteria(AgencyRedemption.class);
      



      outer.add(Restrictions.eq("agencyId", Long.valueOf(agencyId)));
      outer.add(Restrictions.like("applicantName", "%" + query + "%"));
      

      applicantList = outer.list();
      for (int i = 0; i < applicantList.size(); i++)
      {
        AgencyRedemption usr = (AgencyRedemption)applicantList.get(i);
        String fname = usr.getApplicantName();
        logger.info("********** applicant Name : " + fname);
        newFullNameList.add(fname);
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getMyReferralRedumptionAgencyApplicantNames()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return newFullNameList;
  }
  
  public static List getMyReferfriendNames(RefferalEmployee emp, String query)
  {
    logger.info("Inside getMyReferfriendNames method");
    List friendsList = new ArrayList();
    List<String> newFullNameList = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      Criteria outer = session.createCriteria(ReferAFriend.class);
      
      outer.add(Restrictions.disjunction().add(Restrictions.eq("employeerefferal", emp)));
      

      outer.add(Restrictions.like("name", "%" + query + "%"));
      

      friendsList = outer.list();
      for (int i = 0; i < friendsList.size(); i++)
      {
        ReferAFriend usr = (ReferAFriend)friendsList.get(i);
        String fname = usr.getName();
        logger.info("***** friends Name : " + fname);
        newFullNameList.add(fname);
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getReferralApplicantNames()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return newFullNameList;
  }
  
  public static List getMyReferralRedumptionApplicantNames(RefferalEmployee emp, String query)
  {
    logger.info("Inside getMyReferralRedumptionApplicantNames method");
    List applicantList = new ArrayList();
    List<String> newFullNameList = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      Criteria outer = session.createCriteria(ReferralRedemption.class);
      
      outer.add(Restrictions.disjunction().add(Restrictions.eq("employeecode", emp.getEmployeecode())).add(Restrictions.eq("createdBy", emp.getEmployeeemail())));
      


      outer.add(Restrictions.like("applicantName", "%" + query + "%"));
      

      applicantList = outer.list();
      for (int i = 0; i < applicantList.size(); i++)
      {
        ReferralRedemption usr = (ReferralRedemption)applicantList.get(i);
        String fname = usr.getApplicantName();
        logger.info("********** applicant Name : " + fname);
        newFullNameList.add(fname);
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getReferralApplicantNames()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return newFullNameList;
  }
  
  public static void updateRefferalRededmption(ReferralRedemption refRedemption)
  {
    logger.info("Inside updateRefferalRededmption method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.update(refRedemption);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on updateRefferalRededmption()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
  }
  
  public static void updateAgencyRededmption(AgencyRedemption agRedemption)
  {
    logger.info("Inside updateAgencyRededmption method");
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.update(agRedemption);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on updateAgencyRededmption()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
  }
  
  public static RefferalEmployee getRefferalEmployee(String id)
  {
    logger.info("Inside getRefferalEmployee method");
    User user = null;
    Session session = null;
    RefferalEmployee emp = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      emp = (RefferalEmployee)session.createCriteria(RefferalEmployee.class).add(Restrictions.eq("employeeReferalId", new Long(id))).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getRefferalEmployee()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return emp;
  }
  
  public static RefferalEmployee getRefferalEmployeeByUserName(String username)
  {
    logger.info("Inside getRefferalEmployeeByUserName method");
    User user = null;
    Session session = null;
    RefferalEmployee emp = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      emp = (RefferalEmployee)session.createCriteria(RefferalEmployee.class).add(Restrictions.eq("userName", username)).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getRefferalEmployeeByUserName()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return emp;
  }
  
  public static AgencyRedemption getAgencyRedmptionDetails(String redemptionId, String uuid)
  {
    logger.info("Inside getAgencyRedmptionDetails method");
    User user = null;
    Session session = null;
    AgencyRedemption ag = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      ag = (AgencyRedemption)session.createCriteria(AgencyRedemption.class).add(Restrictions.eq("agredid", new Long(redemptionId))).add(Restrictions.eq("uuid", uuid)).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAgencyRedmptionDetails()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return ag;
  }
  
  public static Set getDistinctYearsName(long agencyId, String uom)
  {
    logger.info("Inside getDistinctYearsName method");
    Session session = null;
    Set years = new HashSet();
    List agList = new ArrayList();
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      agList = session.createCriteria(AgencyRedemption.class).add(Restrictions.eq("agencyId", new Long(agencyId))).add(Restrictions.eq("uom", uom)).add(Restrictions.eq("state", "Released")).list();
      for (int i = 0; i < agList.size(); i++)
      {
        AgencyRedemption ag = (AgencyRedemption)agList.get(i);
        Calendar cal = Calendar.getInstance();
        cal.setTime(ag.getReleaseddate());
        years.add(new Integer(cal.get(1)));
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getDistinctYearsName()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return years;
  }
  
  public static Set getApplicantsAddedDistinctYearsName(long agencyId)
  {
    logger.info("Inside getApplicantsAddedDistinctYearsName method");
    Session session = null;
    Set years = new HashSet();
    List agList = new ArrayList();
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      

      String hql = "select a.createdDate from JobApplicant a where a.vendorId = :agencyId";
      Query query = session.createQuery(hql);
      query.setLong("agencyId", agencyId);
      
      agList = query.list();
      for (int i = 0; i < agList.size(); i++)
      {
        Date dt = (Date)agList.get(i);
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        years.add(new Integer(cal.get(1)));
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantsAddedDistinctYearsName()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return years;
  }
  
  public static AgencyRedemption getAgencyRedmptionDetails(String redemptionId)
  {
    logger.info("Inside getAgencyRedmptionDetails method");
    User user = null;
    Session session = null;
    AgencyRedemption ag = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      ag = (AgencyRedemption)session.createCriteria(AgencyRedemption.class).add(Restrictions.eq("agredid", new Long(redemptionId))).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAgencyRedmptionDetails()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return ag;
  }
  
  public static AgencyRedemption getAgencyRedmptionDetailsByApplicantId(long applicantId)
  {
    logger.info("Inside getAgencyRedmptionDetailsByApplicantId method");
    User user = null;
    Session session = null;
    AgencyRedemption ag = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      ag = (AgencyRedemption)session.createCriteria(AgencyRedemption.class).add(Restrictions.eq("applicantId", Long.valueOf(applicantId))).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAgencyRedmptionDetailsByApplicantId()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return ag;
  }
  
  public static ReferralRedemption getReferralRedmptionDetails(String redemptionId, String uuid)
  {
    logger.info("Inside getReferralRedmptionDetails method");
    User user = null;
    Session session = null;
    ReferralRedemption ag = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      ag = (ReferralRedemption)session.createCriteria(ReferralRedemption.class).add(Restrictions.eq("refredid", new Long(redemptionId))).add(Restrictions.eq("uuid", uuid)).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getReferralRedmptionDetails()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return ag;
  }
  
  public static ReferralRedemption getReferralRedmptionDetails(String redemptionId)
  {
    logger.info("Inside getReferralRedmptionDetails method");
    User user = null;
    Session session = null;
    ReferralRedemption ag = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      ag = (ReferralRedemption)session.createCriteria(ReferralRedemption.class).add(Restrictions.eq("refredid", new Long(redemptionId))).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getReferralRedmptionDetails()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return ag;
  }
  
  public static ReferralRedemption getReferralRedmptionDetailsByApplicantId(long applicantId)
  {
    logger.info("Inside getReferralRedmptionDetailsByApplicantId method");
    User user = null;
    Session session = null;
    ReferralRedemption ag = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      ag = (ReferralRedemption)session.createCriteria(ReferralRedemption.class).add(Restrictions.eq("applicantId", Long.valueOf(applicantId))).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getReferralRedmptionDetailsByApplicantId()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return ag;
  }
  
  public static List getAllReferralApplicants()
  {
    logger.info("Inside getAllReferralAgencyApplicants method");
    List appList = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      appList = session.createCriteria(ReferralAgencyApplicants.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("resumeSourceId", Integer.valueOf(1))).list();
      

      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllReferralAgencyApplicants()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return appList;
  }
  
  public static List getAllAgencyApplicants()
  {
    logger.info("Inside getAllAgencyApplicants method");
    List appList = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      appList = session.createCriteria(ReferralAgencyApplicants.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("resumeSourceId", Integer.valueOf(5))).list();
      

      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllAgencyApplicants()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return appList;
  }
  
  public static List getAllReferralRedemptionsWithNotReleased(RefferalRedemptionRule rule)
  {
    logger.info("Inside getAllReferralRedemptionsWithNotReleased method");
    List appList = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      appList = session.createCriteria(ReferralRedemption.class).add(Restrictions.eq("ruleId", Integer.valueOf(rule.getRuleId()))).add(Restrictions.eq("status", "A")).add(Restrictions.eq("state", "Not Released")).list();
      

      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllReferralRedemptionsWithNotReleased()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return appList;
  }
  
  public List getAllAgencyRedemptionByBudget(long budget_id)
  {
    logger.info("Inside getAllAgencyRedemptionByBudget method");
    List appList = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      appList = session.createCriteria(AgencyRedemption.class).add(Restrictions.eq("refBudgetId", Long.valueOf(budget_id))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllAgencyRedemptionByBudget()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return appList;
  }
  
  public List getAllEmployeeRedemptionByBudget(long budget_id)
  {
    logger.info("Inside getAllEmployeeRedemptionByBudget method");
    List appList = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      appList = session.createCriteria(ReferralRedemption.class).add(Restrictions.eq("refBudgetId", Long.valueOf(budget_id))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllEmployeeRedemptionByBudget()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return appList;
  }
  
  public static List getAllAgencyRedemptionsWithNotReleased(RefferalRedemptionRule rule)
  {
    logger.info("Inside getAllAgencyRedemptionsWithNotReleased method");
    List appList = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      appList = session.createCriteria(AgencyRedemption.class).add(Restrictions.eq("ruleId", Integer.valueOf(rule.getRuleId()))).add(Restrictions.eq("status", "A")).add(Restrictions.eq("state", "Not Released")).list();
      

      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllAgencyRedemptionsWithNotReleased()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return appList;
  }
  
  public static RefferalEmployee getRefferalEmployeeByEmail(String emailid)
  {
    logger.info("Inside getRefferalEmployeeByEmail method");
    User user = null;
    Session session = null;
    RefferalEmployee emp = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      emp = (RefferalEmployee)session.createCriteria(RefferalEmployee.class).add(Restrictions.eq("employeeemail", emailid)).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getRefferalEmployeeByEmail()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return emp;
  }
  
  public static RefferalEmployee isLoginSuccess(String email, String password)
  {
    logger.info("Inside isLoginSuccess method");
    RefferalEmployee user = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      user = (RefferalEmployee)session.createCriteria(RefferalEmployee.class).add(Restrictions.eq("employeeemail", email)).add(Restrictions.eq("password", password)).uniqueResult();
      
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
  
  public static RefferalEmployee isLoginSuccessByUserName(String userName, String password)
  {
    logger.info("Inside isLoginSuccessByUserName method");
    RefferalEmployee user = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      user = (RefferalEmployee)session.createCriteria(RefferalEmployee.class).add(Restrictions.eq("userName", userName)).add(Restrictions.eq("password", password)).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on isLoginSuccessByUserName()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return user;
  }
  
  public static RefferalEmployee getRefferalEmployeeByEmailWithActive(String emailid)
  {
    logger.info("Inside getRefferalEmployeeByEmailWithActive method");
    User user = null;
    Session session = null;
    RefferalEmployee emp = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      emp = (RefferalEmployee)session.createCriteria(RefferalEmployee.class).add(Restrictions.eq("employeeemail", emailid)).add(Restrictions.eq("status", "A")).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getRefferalEmployeeByEmailWithActive()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return emp;
  }
  
  public static List getMybookmarks(String email, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getMybookmarks method");
    
    List tmplList = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      

      Criteria outer = null;
      outer = session.createCriteria(FavoriteJob.class).add(Restrictions.eq("empemail", email));
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      tmplList = outer.list();
      



      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getMybookmarks()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return tmplList;
  }
  
  public List getMybookmarksByReqIdandAgencyId(long reqId, long agencyId)
  {
    logger.info("Inside getMybookmarksByReqIdandAgencyId method");
    
    List tmplList = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = null;
      outer = session.createCriteria(FavoriteJob.class).add(Restrictions.eq("reqId", Long.valueOf(reqId))).add(Restrictions.eq("agencyId", Long.valueOf(agencyId)));
      

      tmplList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getMybookmarks()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return tmplList;
  }
  
  public List getMybookmarksByReqIdandEmpemail(long reqId, String empEmail)
  {
    logger.info("Inside getMybookmarksByReqIdandEmpemail method");
    
    List tmplList = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = null;
      outer = session.createCriteria(FavoriteJob.class).add(Restrictions.eq("reqId", Long.valueOf(reqId))).add(Restrictions.eq("empemail", empEmail));
      

      tmplList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getMybookmarks()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return tmplList;
  }
  
  public static List getMybookmarks(long agencyId, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getMybookmarks method");
    
    List tmplList = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      

      Criteria outer = null;
      outer = session.createCriteria(FavoriteJob.class).add(Restrictions.eq("agencyId", Long.valueOf(agencyId)));
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      tmplList = outer.list();
      



      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getMybookmarks()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return tmplList;
  }
  
  public static int getCountOfMybookmarks(String email)
  {
    logger.info("Inside getMybookmarks method");
    int totaluser = 0;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      

      Criteria outer = null;
      outer = session.createCriteria(FavoriteJob.class).add(Restrictions.eq("empemail", email));
      

      outer.setProjection(Projections.rowCount());
      totaluser = ((Integer)outer.list().get(0)).intValue();
      



      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getMybookmarks()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return totaluser;
  }
  
  public static int getCountOfMybookmarks(long agencyId)
  {
    logger.info("Inside getMybookmarks method");
    int totaluser = 0;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      

      Criteria outer = null;
      outer = session.createCriteria(FavoriteJob.class).add(Restrictions.eq("agencyId", Long.valueOf(agencyId)));
      

      outer.setProjection(Projections.rowCount());
      totaluser = ((Integer)outer.list().get(0)).intValue();
      



      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getMybookmarks()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return totaluser;
  }
  
  public static int getReferralSchemeByCriteraCount(User user, String refschmename, long schemetypeid, long orgId, long deptId, long refbudgetcodeid, int ruleid, String type)
  {
    logger.info("Inside getReferralSchemeByCriteraCount method");
    int totalcount = 0;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      Criteria crit = session.createCriteria(RefferalScheme.class).add(Restrictions.eq("status", "A"));
      crit.add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      if (!StringUtils.isNullOrEmpty(refschmename)) {
        crit.add(Restrictions.like("refferalScheme_Name", "%" + refschmename + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(type)) && (!type.equals("null"))) {
        crit.add(Restrictions.like("schemeType", type));
      }
      if (schemetypeid != 0L)
      {
        crit.createAlias("refferalSchemeType", "refferalSchemeType");
        crit.add(Restrictions.eq("refferalSchemeType.refferalSchemeTypeId", Long.valueOf(schemetypeid)));
      }
      if ((orgId != 0L) || (deptId != 0L) || (refbudgetcodeid != 0L)) {
        crit.createAlias("refferalBudgetCode", "refferalBudgetCode");
      }
      if (orgId != 0L) {
        crit.add(Restrictions.eq("refferalBudgetCode.org.orgId", Long.valueOf(orgId)));
      }
      if (deptId != 0L) {
        crit.add(Restrictions.disjunction().add(Restrictions.eq("refferalBudgetCode.department.departmentId", Long.valueOf(deptId))).add(Restrictions.isNull("refferalBudgetCode.department.departmentId")));
      }
      if (refbudgetcodeid != 0L) {
        crit.add(Restrictions.eq("refferalBudgetCode.ref_budgetId", Long.valueOf(refbudgetcodeid)));
      }
      if (ruleid != 0)
      {
        crit.createAlias("rule", "rule");
        crit.add(Restrictions.eq("rule.ruleId", Integer.valueOf(ruleid)));
      }
      crit.setProjection(Projections.rowCount());
      totalcount = ((Integer)crit.list().get(0)).intValue();
      

      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getReferralSchemeByCriteraCount()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return totalcount;
  }
  
  public static List getReferralSchemeByCritera(User user, String refschmename, long schemetypeid, long orgId, long deptId, long refbudgetcodeid, int ruleid, String type, int start, int range)
  {
    logger.info("Inside getReferralSchemeByCritera method");
    List refschemeList = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      Criteria crit = session.createCriteria(RefferalScheme.class).add(Restrictions.eq("status", "A"));
      crit.add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      if (!StringUtils.isNullOrEmpty(refschmename)) {
        crit.add(Restrictions.like("refferalScheme_Name", "%" + refschmename + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(type)) && (!type.equals("null"))) {
        crit.add(Restrictions.like("schemeType", type));
      }
      if (schemetypeid != 0L)
      {
        crit.createAlias("refferalSchemeType", "refferalSchemeType");
        crit.add(Restrictions.eq("refferalSchemeType.refferalSchemeTypeId", Long.valueOf(schemetypeid)));
      }
      if ((orgId != 0L) || (deptId != 0L) || (refbudgetcodeid != 0L)) {
        crit.createAlias("refferalBudgetCode", "refferalBudgetCode");
      }
      if (orgId != 0L) {
        crit.add(Restrictions.eq("refferalBudgetCode.org.orgId", Long.valueOf(orgId)));
      }
      if (deptId != 0L) {
        crit.add(Restrictions.disjunction().add(Restrictions.eq("refferalBudgetCode.department.departmentId", Long.valueOf(deptId))).add(Restrictions.isNull("refferalBudgetCode.department.departmentId")));
      }
      if (refbudgetcodeid != 0L) {
        crit.add(Restrictions.eq("refferalBudgetCode.ref_budgetId", Long.valueOf(refbudgetcodeid)));
      }
      if (ruleid != 0)
      {
        crit.createAlias("rule", "rule");
        crit.add(Restrictions.eq("rule.ruleId", Integer.valueOf(ruleid)));
      }
      crit.setFirstResult(start);
      crit.setMaxResults(range);
      refschemeList = crit.list();
      

      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getReferralSchemeByCritera()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return refschemeList;
  }
  
  public static List searchfriendsForPagination(RefferalEmployee user1, String friendsName, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside searchfriendsForPagination method");
    logger.info("user1.getEmployeeReferalId() : " + user1.getEmployeeReferalId());
    List applicantList = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      Criteria outer = session.createCriteria(ReferAFriend.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("emp_ref_id", Long.valueOf(user1.getEmployeeReferalId()))).add(Restrictions.like("name", "%" + friendsName + "%"));
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      applicantList = outer.list();
      


      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on searchfriendsForPagination()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return applicantList;
  }
  
  public static int getCountOfsearchfriends(RefferalEmployee user1, String friendsName)
  {
    logger.info("Inside getCountOfsearchfriends method");
    logger.info("user1.getEmployeeReferalId() : " + user1.getEmployeeReferalId());
    int totaluser = 0;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      

      Criteria outer = session.createCriteria(ReferAFriend.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("emp_ref_id", Long.valueOf(user1.getEmployeeReferalId()))).add(Restrictions.like("name", "%" + friendsName + "%"));
      


      outer.setProjection(Projections.rowCount());
      totaluser = ((Integer)outer.list().get(0)).intValue();
      System.out.println("totaluser" + totaluser);
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfsearchfriends()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return totaluser;
  }
  
  public static List searchfriendsForPagination(RefferalEmployee user1, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside searchfriendsForPagination method");
    logger.info("user1.getEmployeeReferalId() : " + user1.getEmployeeReferalId());
    List applicantList = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      Criteria outer = session.createCriteria(ReferAFriend.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("emp_ref_id", Long.valueOf(user1.getEmployeeReferalId())));
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      applicantList = outer.list();
      


      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on searchfriendsForPagination()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return applicantList;
  }
  
  public static int getCountOfsearchfriends(RefferalEmployee user1)
  {
    logger.info("Inside getCountOfsearchfriends method");
    logger.info("user1.getEmployeeReferalId() : " + user1.getEmployeeReferalId());
    int totaluser = 0;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      

      Criteria outer = session.createCriteria(ReferAFriend.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("emp_ref_id", Long.valueOf(user1.getEmployeeReferalId())));
      


      outer.setProjection(Projections.rowCount());
      totaluser = ((Integer)outer.list().get(0)).intValue();
      System.out.println("totaluser" + totaluser);
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfsearchfriends()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return totaluser;
  }
  
  public static List searchOwnRefferalRedemptionsForPagination(RefferalEmployee emp, String applicantName, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside searchOwnRefferalRedemptionsForPagination method");
    logger.info("applicantName" + applicantName);
    List applicantList = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      Criteria outer = session.createCriteria(ReferralRedemption.class);
      
      outer.add(Restrictions.disjunction().add(Restrictions.eq("employeecode", emp.getEmployeecode())).add(Restrictions.eq("employeeemail", emp.getEmployeeemail())));
      if ((!StringUtils.isNullOrEmpty(applicantName)) && (!applicantName.equals("null"))) {
        outer.add(Restrictions.like("applicantName", "%" + applicantName + "%"));
      }
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      applicantList = outer.list();
      


      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on searchOwnRefferalRedemptionsForPagination()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return applicantList;
  }
  
  public static Set<String> getDistinctRedemptionUOMByReferral(RefferalEmployee emp, String state)
  {
    logger.info("Inside getDistinctRedemptionUOMByReferral method");
    
    List redList = new ArrayList();
    Set<String> uom = new HashSet();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      Criteria outer = session.createCriteria(ReferralRedemption.class);
      
      outer.add(Restrictions.disjunction().add(Restrictions.eq("employeecode", emp.getEmployeecode())).add(Restrictions.eq("employeeemail", emp.getEmployeeemail())));
      

      outer.add(Restrictions.eq("state", state));
      redList = outer.list();
      for (int i = 0; i < redList.size(); i++)
      {
        ReferralRedemption ref = (ReferralRedemption)redList.get(i);
        uom.add(ref.getUom());
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getDistinctRedemptionUOMByReferral()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return uom;
  }
  
  public static Set<String> getDistinctRedemptionUOMByAgency(User agency, String state)
  {
    logger.info("Inside getDistinctRedemptionUOMByAgency method");
    
    List redList = new ArrayList();
    Set<String> uom = new HashSet();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      Criteria outer = session.createCriteria(AgencyRedemption.class);
      outer.add(Restrictions.like("agencyId", Long.valueOf(agency.getUserId())));
      
      outer.add(Restrictions.eq("state", state));
      redList = outer.list();
      for (int i = 0; i < redList.size(); i++)
      {
        AgencyRedemption ref = (AgencyRedemption)redList.get(i);
        uom.add(ref.getUom());
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getDistinctRedemptionUOMByAgency()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return uom;
  }
  
  public static List getRedemptionsByUOMBandState(RefferalEmployee emp, String uom, String state, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getRedemptionsByUOMBandState method");
    
    List redList = new ArrayList();
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      Criteria outer = session.createCriteria(ReferralRedemption.class);
      
      outer.add(Restrictions.disjunction().add(Restrictions.eq("employeecode", emp.getEmployeecode())).add(Restrictions.eq("employeeemail", emp.getEmployeeemail())));
      

      outer.add(Restrictions.eq("state", state));
      outer.add(Restrictions.eq("uom", uom));
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      redList = outer.list();
      


      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getRedemptionsByUOMBandState()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return redList;
  }
  
  public static List getRedemptionsByUOMBandStateForAgency(User agency, String uom, String state, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getRedemptionsByUOMBandStateForAgency method");
    
    List redList = new ArrayList();
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      

      Criteria outer = session.createCriteria(AgencyRedemption.class);
      outer.add(Restrictions.like("agencyId", Long.valueOf(agency.getUserId())));
      
      outer.add(Restrictions.eq("state", state));
      outer.add(Restrictions.eq("uom", uom));
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      redList = outer.list();
      


      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getRedemptionsByUOMBandStateForAgency()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return redList;
  }
  
  public static List getAllAgencyRedemptions(int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getAllAgencyRedemptions method");
    
    List redList = new ArrayList();
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      

      Criteria outer = session.createCriteria(AgencyRedemption.class);
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      redList = outer.list();
      


      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllAgencyRedemptions()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return redList;
  }
  
  public static List getAllEmployeeReferralRedemptions(User user, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getAllEmployeeReferralRedemptions method");
    
    List redList = new ArrayList();
    
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      

      Criteria outer = session.createCriteria(ReferralRedemption.class);
      outer.add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      redList = outer.list();
      


      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllEmployeeReferralRedemptions()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return redList;
  }
  
  public static List searchAgencyRedemptions(User user, String agencyId, String applicantNo, String applicantName, String requitionId, String refBudgetId, String refferalSchemeId, String refferalSchemeTypeId, String ruleId, String releasedate, String cri, String uom, String state, String isPaid, String orgId, String departmentId, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside searchAgencyRedemptions method");
    List redList = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      Criteria outer = session.createCriteria(AgencyRedemption.class);
      if ((!StringUtils.isNullOrEmpty(agencyId)) && (!agencyId.equals("0"))) {
        outer.add(Restrictions.eq("agencyId", new Long(agencyId)));
      }
      if (!StringUtils.isNullOrEmpty(applicantName)) {
        outer.add(Restrictions.like("applicantName", "%" + applicantName + "%"));
      }
      if (!StringUtils.isNullOrEmpty(applicantNo)) {
        outer.add(Restrictions.eq("applicantId", new Long(applicantNo)));
      }
      if ((!StringUtils.isNullOrEmpty(requitionId)) && (!requitionId.equals("0"))) {
        outer.add(Restrictions.eq("jobReqId", new Long(requitionId)));
      }
      if ((!StringUtils.isNullOrEmpty(refBudgetId)) && (!refBudgetId.equals("0"))) {
        outer.add(Restrictions.eq("refBudgetId", new Long(refBudgetId)));
      }
      if ((!StringUtils.isNullOrEmpty(refferalSchemeId)) && (!refferalSchemeId.equals("0"))) {
        outer.add(Restrictions.eq("refferalSchemeId", new Long(refferalSchemeId)));
      }
      if ((!StringUtils.isNullOrEmpty(refferalSchemeTypeId)) && (!refferalSchemeTypeId.equals("0"))) {
        outer.add(Restrictions.eq("refferalSchemeTypeId", new Long(refferalSchemeTypeId)));
      }
      if ((!StringUtils.isNullOrEmpty(ruleId)) && (!ruleId.equals("0"))) {
        outer.add(Restrictions.eq("ruleId", new Integer(ruleId)));
      }
      if (!StringUtils.isNullOrEmpty(uom)) {
        outer.add(Restrictions.eq("uom", uom));
      }
      if (!StringUtils.isNullOrEmpty(state)) {
        outer.add(Restrictions.eq("state", state));
      }
      if (!StringUtils.isNullOrEmpty(isPaid)) {
        outer.add(Restrictions.eq("isPaid", isPaid));
      }
      if ((!StringUtils.isNullOrEmpty(orgId)) && (!orgId.equals("0"))) {
        outer.add(Restrictions.eq("orgId", new Long(orgId)));
      }
      if ((!StringUtils.isNullOrEmpty(departmentId)) && (!departmentId.equals("0"))) {
        outer.add(Restrictions.eq("departmentId", new Long(departmentId)));
      }
      logger.info("releasedate" + releasedate);
      if ((!StringUtils.isNullOrEmpty(releasedate)) && (!releasedate.equals("null")))
      {
        String datepattern = DateUtil.getDatePatternFormat(user.getLocale());
        

        DateFormat format = new SimpleDateFormat(datepattern + " hh:mm:ss");
        
        Date aDate = format.parse(releasedate + " 00:00:00");
        
        Date bDate = format.parse(releasedate + " 23:59:00");
        if (cri.equals("on")) {
          outer.add(Expression.between("releaseddate", new Date(aDate.getTime()), new Date(bDate.getTime())));
        }
        if (cri.equals("after")) {
          outer.add(Expression.gt("releaseddate", new Date(aDate.getTime())));
        }
        if (cri.equals("before")) {
          outer.add(Expression.lt("releaseddate", new Date(aDate.getTime())));
        }
      }
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      redList = outer.list();
      






      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on searchAgencyRedemptions()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return redList;
  }
  
  public static List searchReferralRedemptions(User user, String employeecode, String employeename, String employeeemail, String applicantNo, String applicantName, String requitionId, String refBudgetId, String refferalSchemeId, String refferalSchemeTypeId, String ruleId, String releasedate, String cri, String uom, String state, String isPaid, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside searchReferralRedemptions method");
    List redList = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      Criteria outer = session.createCriteria(ReferralRedemption.class);
      outer.add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      Disjunction disjunction = Restrictions.disjunction();
      if (!StringUtils.isNullOrEmpty(employeecode))
      {
        Criterion empcode = Restrictions.eq("employeecode", employeecode);
        disjunction.add(empcode);
      }
      if (!StringUtils.isNullOrEmpty(employeeemail))
      {
        Criterion empemail = Restrictions.like("employeeemail", "%" + employeeemail + "%");
        disjunction.add(empemail);
      }
      if (!StringUtils.isNullOrEmpty(employeename))
      {
        Criterion empname = Restrictions.like("employeename", "%" + employeename + "%");
        disjunction.add(empname);
      }
      outer.add(disjunction);
      if (!StringUtils.isNullOrEmpty(applicantName)) {
        outer.add(Restrictions.like("applicantName", "%" + applicantName + "%"));
      }
      if (!StringUtils.isNullOrEmpty(applicantNo)) {
        outer.add(Restrictions.eq("applicantId", new Long(applicantNo)));
      }
      if ((!StringUtils.isNullOrEmpty(requitionId)) && (!requitionId.equals("0"))) {
        outer.add(Restrictions.eq("jobReqId", new Long(requitionId)));
      }
      if ((!StringUtils.isNullOrEmpty(refBudgetId)) && (!refBudgetId.equals("0"))) {
        outer.add(Restrictions.eq("refBudgetId", new Long(refBudgetId)));
      }
      if ((!StringUtils.isNullOrEmpty(refferalSchemeId)) && (!refferalSchemeId.equals("0"))) {
        outer.add(Restrictions.eq("refferalSchemeId", new Long(refferalSchemeId)));
      }
      if ((!StringUtils.isNullOrEmpty(refferalSchemeTypeId)) && (!refferalSchemeTypeId.equals("0"))) {
        outer.add(Restrictions.eq("refferalSchemeTypeId", new Long(refferalSchemeTypeId)));
      }
      if ((!StringUtils.isNullOrEmpty(ruleId)) && (!ruleId.equals("0"))) {
        outer.add(Restrictions.eq("ruleId", new Integer(ruleId)));
      }
      if (!StringUtils.isNullOrEmpty(uom)) {
        outer.add(Restrictions.eq("uom", uom));
      }
      if (!StringUtils.isNullOrEmpty(state)) {
        outer.add(Restrictions.eq("state", state));
      }
      if (!StringUtils.isNullOrEmpty(isPaid)) {
        outer.add(Restrictions.eq("isPaid", isPaid));
      }
      logger.info("releasedate" + releasedate);
      if ((!StringUtils.isNullOrEmpty(releasedate)) && (!releasedate.equals("null")))
      {
        String datepattern = DateUtil.getDatePatternFormat(user.getLocale());
        

        DateFormat format = new SimpleDateFormat(datepattern + " hh:mm:ss");
        
        Date aDate = format.parse(releasedate + " 00:00:00");
        
        Date bDate = format.parse(releasedate + " 23:59:00");
        if (cri.equals("on")) {
          outer.add(Expression.between("releaseddate", new Date(aDate.getTime()), new Date(bDate.getTime())));
        }
        if (cri.equals("after")) {
          outer.add(Expression.gt("releaseddate", new Date(aDate.getTime())));
        }
        if (cri.equals("before")) {
          outer.add(Expression.lt("releaseddate", new Date(aDate.getTime())));
        }
      }
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      redList = outer.list();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on searchReferralRedemptions()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return redList;
  }
  
  public static int getCountOfsearchReferralRedemptions(User user, String employeecode, String employeename, String employeeemail, String applicantNo, String applicantName, String requitionId, String refBudgetId, String refferalSchemeId, String refferalSchemeTypeId, String ruleId, String releasedate, String cri, String uom, String state, String isPaid)
  {
    logger.info("Inside getCountOfsearchReferralRedemptions method");
    int totaluser = 0;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      

      Criteria outer = session.createCriteria(ReferralRedemption.class);
      outer.add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      
      Disjunction disjunction = Restrictions.disjunction();
      if (!StringUtils.isNullOrEmpty(employeecode))
      {
        Criterion empcode = Restrictions.eq("employeecode", employeecode);
        disjunction.add(empcode);
      }
      if (!StringUtils.isNullOrEmpty(employeeemail))
      {
        Criterion empemail = Restrictions.like("employeeemail", "%" + employeeemail + "%");
        disjunction.add(empemail);
      }
      if (!StringUtils.isNullOrEmpty(employeename))
      {
        Criterion empname = Restrictions.like("employeename", "%" + employeename + "%");
        disjunction.add(empname);
      }
      outer.add(disjunction);
      if (!StringUtils.isNullOrEmpty(applicantName)) {
        outer.add(Restrictions.like("applicantName", "%" + applicantName + "%"));
      }
      if (!StringUtils.isNullOrEmpty(applicantNo)) {
        outer.add(Restrictions.eq("applicantId", new Long(applicantNo)));
      }
      if ((!StringUtils.isNullOrEmpty(requitionId)) && (!requitionId.equals("0"))) {
        outer.add(Restrictions.eq("jobReqId", new Long(requitionId)));
      }
      if ((!StringUtils.isNullOrEmpty(refBudgetId)) && (!refBudgetId.equals("0"))) {
        outer.add(Restrictions.eq("refBudgetId", new Long(refBudgetId)));
      }
      if ((!StringUtils.isNullOrEmpty(refferalSchemeId)) && (!refferalSchemeId.equals("0"))) {
        outer.add(Restrictions.eq("refferalSchemeId", new Long(refferalSchemeId)));
      }
      if ((!StringUtils.isNullOrEmpty(refferalSchemeTypeId)) && (!refferalSchemeTypeId.equals("0"))) {
        outer.add(Restrictions.eq("refferalSchemeTypeId", new Long(refferalSchemeTypeId)));
      }
      if ((!StringUtils.isNullOrEmpty(ruleId)) && (!ruleId.equals("0"))) {
        outer.add(Restrictions.eq("ruleId", new Long(ruleId)));
      }
      if (!StringUtils.isNullOrEmpty(uom)) {
        outer.add(Restrictions.eq("uom", uom));
      }
      if (!StringUtils.isNullOrEmpty(state)) {
        outer.add(Restrictions.eq("state", state));
      }
      if (!StringUtils.isNullOrEmpty(isPaid)) {
        outer.add(Restrictions.eq("isPaid", isPaid));
      }
      logger.info("releasedate" + releasedate);
      if ((!StringUtils.isNullOrEmpty(releasedate)) && (!releasedate.equals("null")))
      {
        String datepattern = DateUtil.getDatePatternFormat(user.getLocale());
        
        DateFormat format = new SimpleDateFormat(datepattern + " hh:mm:ss");
        
        Date aDate = format.parse(releasedate + " 00:00:00");
        
        Date bDate = format.parse(releasedate + " 23:59:00");
        if (cri.equals("on")) {
          outer.add(Expression.between("releaseddate", new Date(aDate.getTime()), new Date(bDate.getTime())));
        }
        if (cri.equals("after")) {
          outer.add(Expression.gt("releaseddate", new Date(aDate.getTime())));
        }
        if (cri.equals("before")) {
          outer.add(Expression.lt("releaseddate", new Date(aDate.getTime())));
        }
      }
      outer.setProjection(Projections.rowCount());
      totaluser = ((Integer)outer.list().get(0)).intValue();
      System.out.println("totaluser" + totaluser);
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfsearchReferralRedemptions()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return totaluser;
  }
  
  public static int getCountOfsearchAgencyRedemptions(User user, String agencyId, String applicantNo, String applicantName, String requitionId, String refBudgetId, String refferalSchemeId, String refferalSchemeTypeId, String ruleId, String releasedate, String cri, String uom, String state, String isPaid, String orgId, String departmentId)
  {
    logger.info("Inside getCountOfsearchAgencyRedemptions method");
    int totaluser = 0;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      

      Criteria outer = session.createCriteria(AgencyRedemption.class);
      if ((!StringUtils.isNullOrEmpty(agencyId)) && (!agencyId.equals("0"))) {
        outer.add(Restrictions.eq("agencyId", new Long(agencyId)));
      }
      if (!StringUtils.isNullOrEmpty(applicantName)) {
        outer.add(Restrictions.like("applicantName", "%" + applicantName + "%"));
      }
      if (!StringUtils.isNullOrEmpty(applicantNo)) {
        outer.add(Restrictions.eq("applicantId", new Long(applicantNo)));
      }
      if ((!StringUtils.isNullOrEmpty(requitionId)) && (!requitionId.equals("0"))) {
        outer.add(Restrictions.eq("jobReqId", new Long(requitionId)));
      }
      if ((!StringUtils.isNullOrEmpty(refBudgetId)) && (!refBudgetId.equals("0"))) {
        outer.add(Restrictions.eq("refBudgetId", new Long(refBudgetId)));
      }
      if ((!StringUtils.isNullOrEmpty(refferalSchemeId)) && (!refferalSchemeId.equals("0"))) {
        outer.add(Restrictions.eq("refferalSchemeId", new Long(refferalSchemeId)));
      }
      if ((!StringUtils.isNullOrEmpty(refferalSchemeTypeId)) && (!refferalSchemeTypeId.equals("0"))) {
        outer.add(Restrictions.eq("refferalSchemeTypeId", new Long(refferalSchemeTypeId)));
      }
      if ((!StringUtils.isNullOrEmpty(ruleId)) && (!ruleId.equals("0"))) {
        outer.add(Restrictions.eq("ruleId", new Long(ruleId)));
      }
      if (!StringUtils.isNullOrEmpty(uom)) {
        outer.add(Restrictions.eq("uom", uom));
      }
      if (!StringUtils.isNullOrEmpty(state)) {
        outer.add(Restrictions.eq("state", state));
      }
      if (!StringUtils.isNullOrEmpty(isPaid)) {
        outer.add(Restrictions.eq("isPaid", isPaid));
      }
      if ((!StringUtils.isNullOrEmpty(orgId)) && (!orgId.equals("0"))) {
        outer.add(Restrictions.eq("orgId", new Long(orgId)));
      }
      if ((!StringUtils.isNullOrEmpty(departmentId)) && (!departmentId.equals("0"))) {
        outer.add(Restrictions.eq("departmentId", new Long(departmentId)));
      }
      logger.info("releasedate" + releasedate);
      if ((!StringUtils.isNullOrEmpty(releasedate)) && (!releasedate.equals("null")))
      {
        String datepattern = DateUtil.getDatePatternFormat(user.getLocale());
        
        DateFormat format = new SimpleDateFormat(datepattern + " hh:mm:ss");
        
        Date aDate = format.parse(releasedate + " 00:00:00");
        
        Date bDate = format.parse(releasedate + " 23:59:00");
        if (cri.equals("on")) {
          outer.add(Expression.between("releaseddate", new Date(aDate.getTime()), new Date(bDate.getTime())));
        }
        if (cri.equals("after")) {
          outer.add(Expression.gt("releaseddate", new Date(aDate.getTime())));
        }
        if (cri.equals("before")) {
          outer.add(Expression.lt("releaseddate", new Date(aDate.getTime())));
        }
      }
      outer.setProjection(Projections.rowCount());
      totaluser = ((Integer)outer.list().get(0)).intValue();
      System.out.println("totaluser" + totaluser);
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfsearchAgencyRedemptions()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return totaluser;
  }
  
  public static int getCountOfAllAgencyRedemptions()
  {
    logger.info("Inside getCountOfAllAgencyRedemptions method");
    int totaluser = 0;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      

      Criteria outer = session.createCriteria(AgencyRedemption.class);
      
      outer.setProjection(Projections.rowCount());
      totaluser = ((Integer)outer.list().get(0)).intValue();
      System.out.println("totaluser" + totaluser);
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfAllAgencyRedemptions()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return totaluser;
  }
  
  public static int getCountOfAllEmployeeReferralRedemptions(User user)
  {
    logger.info("Inside getCountOfAllEmployeeReferralRedemptions method");
    int totaluser = 0;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      

      Criteria outer = session.createCriteria(ReferralRedemption.class);
      outer.add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      outer.setProjection(Projections.rowCount());
      totaluser = ((Integer)outer.list().get(0)).intValue();
      System.out.println("totaluser" + totaluser);
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfAllEmployeeReferralRedemptions()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return totaluser;
  }
  
  public static int searchRefferalEmpCount()
  {
    logger.info("Inside searchRefferalEmpCount method");
    int totaluser = 0;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      

      Criteria criteria = session.createCriteria(RefferalEmployee.class).add(Restrictions.ne("status", "D"));
      
      criteria.setProjection(Projections.rowCount());
      totaluser = ((Integer)criteria.list().get(0)).intValue();
      System.out.println("totaluser : " + totaluser);
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on searchRefferalEmpCount()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return totaluser;
  }
  
  public static List searchRefferalEmpForPagination(int pageSize, int startIndex, String dir, String sort_str)
  {
    logger.info("**** Inside searchRefferalEmpForPagination method");
    List refList = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      

      Criteria query = session.createCriteria(RefferalEmployee.class).add(Restrictions.ne("status", "D"));
      logger.info("dir : " + dir);
      if (dir.equalsIgnoreCase("asc")) {
        query.addOrder(Order.asc(sort_str));
      } else {
        query.addOrder(Order.desc(sort_str));
      }
      query.setFirstResult(startIndex);
      
      query.setMaxResults(pageSize);
      
      refList = query.list();
      

      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on searchRefferalEmpForPagination()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return refList;
  }
  
  public static List searchRefferalEmpForPagination(String employeecode, String employeename, String employeeemail, String orgId, String departmentId, String projectId, String status, String dir, String sort, int pageSize, int startIndex)
  {
    logger.info(" *** Inside searchRefferalEmpForPagination method ***");
    List charList = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      Criteria outer;
      if (status.equalsIgnoreCase("A"))
      {
        outer = session.createCriteria(RefferalEmployee.class).add(Restrictions.eq("status", "A"));
      }
      else
      {
        if (status.equalsIgnoreCase("I")) {
          outer = session.createCriteria(RefferalEmployee.class).add(Restrictions.eq("status", "I"));
        } else {
          outer = session.createCriteria(RefferalEmployee.class).add(Restrictions.eq("status", "A"));
        }
      }
      logger.info("** 00 ...");
      if (!StringUtils.isNullOrEmpty(employeecode))
      {
        outer.add(Restrictions.like("employeecode", "%" + employeecode + "%"));
        logger.info("** 01 ...");
      }
      if (!StringUtils.isNullOrEmpty(employeename))
      {
        outer.add(Restrictions.like("employeename", "%" + employeename + "%"));
        logger.info("** 02 ...");
      }
      if (!StringUtils.isNullOrEmpty(employeeemail))
      {
        outer.add(Restrictions.like("employeeemail", "%" + employeeemail + "%"));
        logger.info("** 03 ...");
      }
      if (!StringUtils.isNullOrEmpty(status))
      {
        outer.add(Restrictions.eq("status", status));
        logger.info("** 04 ...");
      }
      if ((!StringUtils.isNullOrEmpty(orgId)) && (!orgId.equals("0")))
      {
        outer.add(Restrictions.eq("organization.orgId", new Long(orgId)));
        logger.info("** 05 ...");
      }
      if ((!StringUtils.isNullOrEmpty(departmentId)) && (!departmentId.equals("0")))
      {
        outer.add(Restrictions.eq("department.departmentId", new Long(departmentId)));
        logger.info("** 06 ...");
      }
      if ((!StringUtils.isNullOrEmpty(projectId)) && (!projectId.equals("0")))
      {
        outer.add(Restrictions.eq("projectcode.projectId", new Long(projectId)));
        logger.info("** 07 ...");
      }
      logger.info("** 1 ...");
      if (dir.equalsIgnoreCase("asc"))
      {
        outer.addOrder(Order.asc(sort));
        logger.info("** 2 if ...");
      }
      else
      {
        outer.addOrder(Order.desc(sort));
        logger.info("** 2 else ...");
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      logger.info("** 3 ...");
      charList = outer.list();
      logger.info("** 4 ...");
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on searchRefferalEmpForPagination method", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return charList;
  }
  
  public static int searchRefferalEmpCount(String employeecode, String employeename, String employeeemail, String orgId, String departmentId, String projectId, String status)
  {
    logger.info("Inside searchRefferalEmpCount method ********* ");
    List charList = new ArrayList();
    int totaluser = 0;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      Criteria outer;
      if (status.equalsIgnoreCase("A"))
      {
        outer = session.createCriteria(RefferalEmployee.class).add(Restrictions.eq("status", "A"));
      }
      else
      {
        if (status.equalsIgnoreCase("I")) {
          outer = session.createCriteria(RefferalEmployee.class).add(Restrictions.eq("status", "I"));
        } else {
          outer = session.createCriteria(RefferalEmployee.class).add(Restrictions.eq("status", "A"));
        }
      }
      if (!StringUtils.isNullOrEmpty(employeecode)) {
        outer.add(Restrictions.like("employeecode", "%" + employeecode + "%"));
      }
      if (!StringUtils.isNullOrEmpty(employeename)) {
        outer.add(Restrictions.like("employeename", "%" + employeename + "%"));
      }
      if (!StringUtils.isNullOrEmpty(employeeemail)) {
        outer.add(Restrictions.like("employeeemail", "%" + employeeemail + "%"));
      }
      if (!StringUtils.isNullOrEmpty(status)) {
        outer.add(Restrictions.eq("status", status));
      }
      if ((!StringUtils.isNullOrEmpty(orgId)) && (!orgId.equals("0"))) {
        outer.add(Restrictions.eq("organization.orgId", new Long(orgId)));
      }
      if ((!StringUtils.isNullOrEmpty(departmentId)) && (!departmentId.equals("0"))) {
        outer.add(Restrictions.eq("department.departmentId", new Long(departmentId)));
      }
      if ((!StringUtils.isNullOrEmpty(projectId)) && (!projectId.equals("0"))) {
        outer.add(Restrictions.eq("projectcode.projectId", new Long(projectId)));
      }
      outer.setProjection(Projections.rowCount());
      totaluser = ((Integer)outer.list().get(0)).intValue();
      System.out.println("** totaluser : " + totaluser);
      

      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on searchRefferalEmpCount method *********", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return totaluser;
  }
  
  public static List searchOwnAgencyRedemptionsForPagination(User agency, String applicantName, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside searchOwnAgencyRedemptionsForPagination method");
    logger.info("applicantName" + applicantName);
    List applicantList = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      Criteria outer = session.createCriteria(AgencyRedemption.class);
      outer.add(Restrictions.like("agencyId", Long.valueOf(agency.getUserId())));
      if ((!StringUtils.isNullOrEmpty(applicantName)) && (!applicantName.equals("null"))) {
        outer.add(Restrictions.like("applicantName", "%" + applicantName + "%"));
      }
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      applicantList = outer.list();
      


      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on searchOwnAgencyRedemptionsForPagination()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return applicantList;
  }
  
  public static int getCountOfsearchOwnAgencyRedemptions(User agency, String applicantName)
  {
    logger.info("Inside getCountOfsearchOwnAgencyRedemptions method");
    int totaluser = 0;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      

      Criteria outer = session.createCriteria(AgencyRedemption.class);
      outer.add(Restrictions.like("agencyId", Long.valueOf(agency.getUserId())));
      if ((!StringUtils.isNullOrEmpty(applicantName)) && (!applicantName.equals("null"))) {
        outer.add(Restrictions.like("applicantName", "%" + applicantName + "%"));
      }
      outer.setProjection(Projections.rowCount());
      totaluser = ((Integer)outer.list().get(0)).intValue();
      System.out.println("totaluser" + totaluser);
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfsearchOwnAgencyRedemptions()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return totaluser;
  }
  
  public static int getCountOfRedemptionsByUOMBandState(RefferalEmployee emp, String uom, String state)
  {
    logger.info("Inside getCountOfRedemptionsByUOMBandState method");
    int totaluser = 0;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      

      Criteria outer = session.createCriteria(ReferralRedemption.class);
      
      outer.add(Restrictions.disjunction().add(Restrictions.eq("employeecode", emp.getEmployeecode())).add(Restrictions.eq("employeeemail", emp.getEmployeeemail())));
      

      outer.add(Restrictions.eq("state", state));
      outer.add(Restrictions.eq("uom", uom));
      
      outer.setProjection(Projections.rowCount());
      totaluser = ((Integer)outer.list().get(0)).intValue();
      System.out.println("totaluser" + totaluser);
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfRedemptionsByUOMBandState()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return totaluser;
  }
  
  public static int getCountOfRedemptionsByUOMBandStateForAgency(User agency, String uom, String state)
  {
    logger.info("Inside getCountOfRedemptionsByUOMBandStateForAgency method");
    int totaluser = 0;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      

      Criteria outer = session.createCriteria(AgencyRedemption.class);
      outer.add(Restrictions.like("agencyId", Long.valueOf(agency.getUserId())));
      
      outer.add(Restrictions.eq("state", state));
      outer.add(Restrictions.eq("uom", uom));
      
      outer.setProjection(Projections.rowCount());
      totaluser = ((Integer)outer.list().get(0)).intValue();
      System.out.println("totaluser" + totaluser);
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfRedemptionsByUOMBandStateForAgency()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return totaluser;
  }
  
  public static long getTotalRedemptionsByUOMBandState(RefferalEmployee emp, String uom, String state)
  {
    logger.info("Inside getTotalRedemptionsByUOMBandState method");
    long total = 0L;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      

      Criteria outer = session.createCriteria(ReferralRedemption.class);
      
      outer.add(Restrictions.disjunction().add(Restrictions.eq("employeecode", emp.getEmployeecode())).add(Restrictions.eq("employeeemail", emp.getEmployeeemail())));
      

      outer.add(Restrictions.eq("state", state));
      outer.add(Restrictions.eq("uom", uom));
      
      outer.setProjection(Projections.sum("refferalSchemeAmount"));
      
      total = ((Long)outer.uniqueResult()).longValue();
      

      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getTotalRedemptionsByUOMBandState()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return total;
  }
  
  public static long getTotalRedemptionsByUOMBandStateForAgency(User agency, String uom, String state)
  {
    logger.info("Inside getTotalRedemptionsByUOMBandStateForAgency method");
    long total = 0L;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      

      Criteria outer = session.createCriteria(AgencyRedemption.class);
      outer.add(Restrictions.eq("agencyId", Long.valueOf(agency.getUserId())));
      
      outer.add(Restrictions.eq("state", state));
      outer.add(Restrictions.eq("uom", uom));
      
      outer.setProjection(Projections.sum("refferalSchemeAmount"));
      
      total = ((Long)outer.uniqueResult()).longValue();
      

      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getTotalRedemptionsByUOMBandStateForAgency()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return total;
  }
  
  public static long getTotalRedemptionsNotPiadByUOMBandState(RefferalEmployee emp, String uom, String state)
  {
    logger.info("Inside getTotalRedemptionsNotPiadByUOMBandState method");
    long total = 0L;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      

      Criteria outer = session.createCriteria(ReferralRedemption.class);
      
      outer.add(Restrictions.disjunction().add(Restrictions.eq("employeecode", emp.getEmployeecode())).add(Restrictions.eq("employeeemail", emp.getEmployeeemail())));
      

      outer.add(Restrictions.eq("state", state));
      outer.add(Restrictions.eq("uom", uom));
      outer.add(Restrictions.eq("isPaid", "N"));
      
      outer.setProjection(Projections.sum("refferalSchemeAmount"));
      
      total = ((Long)outer.uniqueResult()).longValue();
      

      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getTotalRedemptionsNotPiadByUOMBandState()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return total;
  }
  
  public static long getTotalRedemptionsNotPiadByUOMBandStateForAgency(User agency, String uom, String state)
  {
    logger.info("Inside getTotalRedemptionsNotPiadByUOMBandStateForAgency method");
    long total = 0L;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      

      Criteria outer = session.createCriteria(AgencyRedemption.class);
      outer.add(Restrictions.eq("agencyId", Long.valueOf(agency.getUserId())));
      
      outer.add(Restrictions.eq("state", state));
      outer.add(Restrictions.eq("uom", uom));
      outer.add(Restrictions.eq("isPaid", "N"));
      
      outer.setProjection(Projections.sum("refferalSchemeAmount"));
      
      total = ((Long)outer.uniqueResult()).longValue();
      

      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getTotalRedemptionsNotPiadByUOMBandStateForAgency()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return total;
  }
  
  public static long getTotalAgencyRedemptionByYear(long agencyId, String uom, int year)
  {
    logger.info("Inside getTotalAgencyRedemptionByYear method");
    long total = 0L;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      










      String hql = "select sum(a.refferalSchemeAmount) from AgencyRedemption a where a.agencyId = :agencyId and a.state= :state and a.uom = :uom and year(a.releaseddate) = :year";
      Query query = session.createQuery(hql);
      query.setLong("agencyId", agencyId);
      query.setString("state", "Released");
      query.setString("uom", uom);
      query.setInteger("year", year);
      
      Object ob = query.uniqueResult();
      total = ((Long)ob).longValue();
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getTotalAgencyRedemptionByYear()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return total;
  }
  
  public static List getApplicantsStateAndCountByAgencyIdAndYear(long agencyId, int year)
  {
    logger.info("Inside getApplicantsStateAndCountByAgencyIdAndYear method");
    long total = 0L;
    List statewithcountlist = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      










      String hql = "select a.interviewState from JobApplicant a where a.vendorId = :agencyId and year(a.createdDate) = :year";
      Query query = session.createQuery(hql);
      query.setLong("agencyId", agencyId);
      query.setInteger("year", year);
      
      List intstateList = query.list();
      KeyValue keyv = new KeyValue();
      keyv.setKey("Application Submitted");
      keyv.setValue(String.valueOf(intstateList.size()));
      statewithcountlist.add(keyv);
      int offeredcount = 0;
      int joinedcount = 0;
      for (int i = 0; i < intstateList.size(); i++)
      {
        String interviewstate = (String)intstateList.get(i);
        if ((interviewstate != null) && ((interviewstate.equals("On Board - Joined")) || (interviewstate.equals("Offer Released")) || (interviewstate.equals("Offer Accepted")) || (interviewstate.equals("Offer Declined")) || (interviewstate.equals("Offer Canceled")) || (interviewstate.equals("Offer In Negotiation")))) {
          offeredcount++;
        }
        if ((interviewstate != null) && (interviewstate.equals("On Board - Joined"))) {
          joinedcount++;
        }
      }
      KeyValue keyv1 = new KeyValue();
      keyv1.setKey("Offer Released");
      keyv1.setValue(String.valueOf(offeredcount));
      statewithcountlist.add(keyv1);
      
      KeyValue keyv2 = new KeyValue();
      keyv2.setKey("On Board - Joined");
      keyv2.setValue(String.valueOf(joinedcount));
      statewithcountlist.add(keyv2);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantsStateAndCountByAgencyIdAndYear()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return statewithcountlist;
  }
  
  public static List getAgencyPerformanceByYear(long agencyId, int year)
  {
    logger.info("Inside getAgencyPerformanceByYear method");
    long total = 0L;
    List perflist = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      String hql = "select a.interviewState from JobApplicant a where a.vendorId = :agencyId and year(a.createdDate) = :year";
      Query query = session.createQuery(hql);
      query.setLong("agencyId", agencyId);
      query.setInteger("year", year);
      
      List intstateList = query.list();
      int totalapplicant = intstateList.size();
      int offeredcount = 0;
      int joinedcount = 0;
      for (int i = 0; i < intstateList.size(); i++)
      {
        String interviewstate = (String)intstateList.get(i);
        if ((interviewstate != null) && ((interviewstate.equals("On Board - Joined")) || (interviewstate.equals("Offer Released")) || (interviewstate.equals("Offer Accepted")) || (interviewstate.equals("Offer Declined")) || (interviewstate.equals("Offer Canceled")) || (interviewstate.equals("Offer In Negotiation")))) {
          offeredcount++;
        }
        if ((interviewstate != null) && (interviewstate.equals("On Board - Joined"))) {
          joinedcount++;
        }
      }
      KeyValue keyv = new KeyValue();
      keyv.setKey("Applications To Offer Ratio");
      float apptooffer = offeredcount / totalapplicant * 100.0F;
      keyv.setValue(String.valueOf(apptooffer));
      perflist.add(keyv);
      
      KeyValue keyv1 = new KeyValue();
      keyv1.setKey("Applications To Onboard Ratio");
      float apptoon = joinedcount / totalapplicant * 100.0F;
      keyv1.setValue(String.valueOf(apptoon));
      perflist.add(keyv1);
      
      KeyValue keyv2 = new KeyValue();
      keyv2.setKey("Offer To Onboard Ratio");
      float offtojoined = joinedcount / offeredcount * 100.0F;
      keyv2.setValue(String.valueOf(offtojoined));
      perflist.add(keyv2);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAgencyPerformanceByYear()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return perflist;
  }
  
  public static List getAgencyOverallPerformance(long agencyId)
  {
    logger.info("Inside getAgencyOverallPerformance method");
    long total = 0L;
    List perflist = new ArrayList();
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      String hql = "select a.interviewState from JobApplicant a where a.vendorId = :agencyId";
      Query query = session.createQuery(hql);
      query.setLong("agencyId", agencyId);
      

      List intstateList = query.list();
      int totalapplicant = intstateList.size();
      int offeredcount = 0;
      int joinedcount = 0;
      for (int i = 0; i < intstateList.size(); i++)
      {
        String interviewstate = (String)intstateList.get(i);
        if ((interviewstate != null) && ((interviewstate.equals("On Board - Joined")) || (interviewstate.equals("Offer Released")) || (interviewstate.equals("Offer Accepted")) || (interviewstate.equals("Offer Declined")) || (interviewstate.equals("Offer Canceled")) || (interviewstate.equals("Offer In Negotiation")))) {
          offeredcount++;
        }
        if ((interviewstate != null) && (interviewstate.equals("On Board - Joined"))) {
          joinedcount++;
        }
      }
      KeyValue keyv = new KeyValue();
      keyv.setKey("Applications To Offer Ratio");
      float apptooffer = offeredcount / totalapplicant * 100.0F;
      keyv.setValue(String.valueOf(apptooffer));
      perflist.add(keyv);
      
      KeyValue keyv1 = new KeyValue();
      keyv1.setKey("Applications To Onboard Ratio");
      float apptoon = joinedcount / totalapplicant * 100.0F;
      keyv1.setValue(String.valueOf(apptoon));
      perflist.add(keyv1);
      
      KeyValue keyv2 = new KeyValue();
      keyv2.setKey("Offer To Onboard Ratio");
      float offtojoined = joinedcount / offeredcount * 100.0F;
      keyv2.setValue(String.valueOf(offtojoined));
      perflist.add(keyv2);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAgencyOverallPerformance()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return perflist;
  }
  
  public static int getCountOfsearchOwnRefferalRedemptions(RefferalEmployee emp, String applicantName)
  {
    logger.info("Inside getCountOfsearchOwnRefferalRedemptions method");
    int totaluser = 0;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      

      Criteria outer = session.createCriteria(ReferralRedemption.class);
      
      outer.add(Restrictions.disjunction().add(Restrictions.eq("employeecode", emp.getEmployeecode())).add(Restrictions.eq("employeeemail", emp.getEmployeeemail())));
      if ((!StringUtils.isNullOrEmpty(applicantName)) && (!applicantName.equals("null"))) {
        outer.add(Restrictions.like("applicantName", "%" + applicantName + "%"));
      }
      outer.setProjection(Projections.rowCount());
      totaluser = ((Integer)outer.list().get(0)).intValue();
      System.out.println("totaluser" + totaluser);
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfsearchOwnRefferalRedemptions()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return totaluser;
  }
  
  public static ReferralAgencyApplicants getReferralAgencyApplicantByApplicantId(long applicantId)
  {
    logger.info("Inside getReferralAgencyApplicantByApplicantId method");
    ReferralAgencyApplicants refag = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      refag = (ReferralAgencyApplicants)session.createCriteria(ReferralAgencyApplicants.class).add(Restrictions.eq("applicantId", Long.valueOf(applicantId))).uniqueResult();
      

      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getReferralAgencyApplicantByApplicantId()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return refag;
  }
  
  public static void updateReferralAgencyApplicant(ReferralAgencyApplicants refagapp)
  {
    logger.info("Inside updateReferralAgencyApplicant method");
    User user = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      session.update(refagapp);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on updateReferralAgencyApplicant()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
  }
  
  public static RefferalEmployee getRefferalEmp(long id)
  {
    logger.info("Inside getRefferalEmp method");
    logger.info(".. id .. : " + id);
    
    Session session = null;
    RefferalEmployee usr = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      logger.info("Inside getRefferalEmp method ... 1");
      usr = (RefferalEmployee)session.createCriteria(RefferalEmployee.class).add(Restrictions.eq("employeeReferalId", Long.valueOf(id))).uniqueResult();
      logger.info("Inside getRefferalEmp method  .. 2");
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getRefferalEmp()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return usr;
  }
  
  public static ProfilePhoto saveUserProfilePhoto(ProfilePhoto pphoto)
  {
    logger.info("Inside saveUserProfilePhoto method");
    User user = null;
    Session session = null;
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
  
  public static String isEmailIdExist(String emailId)
  {
    logger.info("Inside isEmailIdExist method");
    String email = null;
    Session session = null;
    User usr = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      String hql = "select employeeemail from RefferalEmployee a where a.employeeemail = :employeeemail and a.status != :status";
      Query query = session.createQuery(hql);
      query.setString("employeeemail", emailId);
      query.setString("status", "D");
      
      Object ob = query.uniqueResult();
      if (ob != null) {
        email = (String)ob;
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
    return email;
  }
  
  public static String isfriendExists(String emailId, long reqId)
  {
    logger.info("Inside isfriendExists method");
    String email = null;
    Session session = null;
    User usr = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      String hql = "select emailId from ReferAFriend a where a.emailId = :emailId and a.jobRequisitionId = :jobRequisitionId and a.status != :status";
      Query query = session.createQuery(hql);
      query.setString("emailId", emailId);
      query.setLong("jobRequisitionId", reqId);
      query.setString("status", "D");
      
      Object ob = query.uniqueResult();
      if (ob != null) {
        email = (String)ob;
      }
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on isfriendExists()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return email;
  }
  
  public static ReferAFriend getReferFriend(String id)
  {
    logger.info("Inside getReferFriend method");
    Session session = null;
    ReferAFriend reffriend = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      
      reffriend = (ReferAFriend)session.createCriteria(ReferAFriend.class).add(Restrictions.eq("referafriendId", new Long(id))).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getReferFriend()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return reffriend;
  }
  
  public static void deletefriend(long friendId)
  {
    logger.info("Inside deletefriend method");
    ReferAFriend reffriend = null;
    Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      reffriend = (ReferAFriend)session.createCriteria(ReferAFriend.class).add(Restrictions.eq("referafriendId", Long.valueOf(friendId))).uniqueResult();
      session.delete(reffriend);
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on deletefriend()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
  }
}
