package com.dao;

import com.bean.ActionsAttachment;
import com.bean.ApplicantActivity;
import com.bean.ApplicantAttachments;
import com.bean.ApplicantComment;
import com.bean.ApplicantOfferAttachment;
import com.bean.ApplicantOtherBenefits;
import com.bean.ApplicantOtherDetails;
import com.bean.ApplicantProfilePhoto;
import com.bean.ApplicantRating;
import com.bean.ApplicantReferencee;
import com.bean.ApplicantScoring;
import com.bean.ApplicantSkills;
import com.bean.ApplicantTags;
import com.bean.ApplicantUser;
import com.bean.DataTableBean;
import com.bean.DeclinedResons;
import com.bean.EducationDetails;
import com.bean.INineFormBean;
import com.bean.InterviewWatchList;
import com.bean.JobApplicant;
import com.bean.JobApplicationEvent;
import com.bean.JobRequisition;
import com.bean.OfferApprover;
import com.bean.OfferWatchList;
import com.bean.PreviousOrgDetails;
import com.bean.PreviousOrganization;
import com.bean.RefferalEmployee;
import com.bean.ResumeSources;
import com.bean.SalaryPlan;
import com.bean.SearchApplicant;
import com.bean.SearchApplicantCustomFields;
import com.bean.SearchApplicantQuestions;
import com.bean.Timezone;
import com.bean.User;
import com.bean.criteria.ApplicantSearchCriteria;
import com.bean.criteria.IndexApplcantSearch;
import com.bean.filter.ScreenFields;
import com.bean.lov.ZipCode;
import com.bo.QueryBuilderApplicant;
import com.common.Common;
import com.lucene.IndexSearch;
import com.resources.Constant;
import com.util.DateUtil;
import com.util.ProximityUtil;
import com.util.StringUtils;
import java.io.File;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class ApplicantDAO
  extends HibernateDaoSupport
{
  protected static final Logger logger = Logger.getLogger(ApplicantDAO.class);
  
  public long getMaxValueFromApplicant(long superUserKey)
  {
    logger.info("Inside getMaxValueFromApplicant method");
    long maxvalue = 0L;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      String sql = "select max(applicant_number) from job_applications  where super_user_key = :super_user_key";
      Query query = session.createSQLQuery(sql);
      query.setLong("super_user_key", superUserKey);
      Object obj = query.uniqueResult();
      
      BigInteger maxv = (BigInteger)obj;
      maxvalue = maxv.longValue();
    }
    catch (Exception e)
    {
      logger.info("Exception on getMaxValueFromApplicant()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return maxvalue;
  }
  
  public void updateIsViewFlag(long application_id)
  {
    logger.info("Inside updateIsViewFlag method");
    long maxvalue = 0L;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      String sql = "update JobApplicant  set isViewed=0 where applicantId =:applicantId";
      Query query = session.createQuery(sql);
      query.setLong("applicantId", application_id);
      
      query.executeUpdate();
    }
    catch (Exception e)
    {
      logger.info("Exception on updateIsViewFlag()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public long getReqIdByApplicantNumber(long applicant_number, long superUserKey)
  {
    logger.info("Inside getReqIdByApplicantNumber method");
    long reqid = 0L;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      String sql = "select jb_req_id from job_applications  where super_user_key = :super_user_key and applicant_number = :applicant_number";
      Query query = session.createSQLQuery(sql);
      query.setLong("super_user_key", superUserKey);
      query.setLong("applicant_number", applicant_number);
      Object obj = query.uniqueResult();
      
      BigInteger reqidv = (BigInteger)obj;
      reqid = reqidv.longValue();
    }
    catch (Exception e)
    {
      logger.info("Exception on getReqIdByApplicantNumber()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return reqid;
  }
  
  public List getPreviousOrganizationsList()
  {
    logger.info("Inside getPreviousOrganizationsList method");
    
    List plist = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      List porglist = session.createQuery("select distinct ja.previousOrganization from JobApplicant ja").list();
      for (int i = 0; i < porglist.size(); i++)
      {
        PreviousOrganization po = new PreviousOrganization();
        po.setOrgname((String)porglist.get(i));
        po.setId(i);
        plist.add(po);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getPreviousOrganizationsList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return plist;
  }
  
  public long getApplicantIdByUuid(String uuid)
    throws Exception
  {
    logger.info("Inside getApplicantIdByUuid method");
    long appId = 0L;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      String sql = "select application_id from job_applications  where uuid = :uuid";
      Query query = session.createSQLQuery(sql);
      query.setString("uuid", uuid);
      Object obj = query.uniqueResult();
      
      BigInteger reqid = (BigInteger)obj;
      appId = reqid.longValue();
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantIdByUuid()", e);
      throw e;
    }
    return appId;
  }
  
  public String getInterviewStateById(String uuid)
  {
    logger.info("Inside getInterviewStateById method");
    String st = "";
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      String sql = "select interview_state from job_applications  where uuid = :uuid";
      Query query = session.createSQLQuery(sql);
      query.setString("uuid", uuid);
      Object obj = query.uniqueResult();
      
      st = (String)obj;
    }
    catch (Exception e)
    {
      logger.error("Exception on getInterviewStateById()", e);
    }
    return st;
  }
  
  public List getApplicantsForPagination(int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getApplicantsForPagination method");
    List applicantList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = session.createCriteria(JobApplicant.class).add(Restrictions.ne("status", "R")).add(Restrictions.ne("status", "T"));
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      applicantList = outer.list();
      JobApplicant jb;
      for (int i = 0; i < applicantList.size(); i++) {
        jb = (JobApplicant)applicantList.get(i);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantsForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return applicantList;
  }
  
  public List getApplicantsForPaginationByUser(User user, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getApplicantsForPaginationByUser method");
    List applicantList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = session.createCriteria(JobApplicant.class).createAlias("owner", "owner");
      









      outer.add(Restrictions.disjunction().add(Restrictions.eq("owner.userId", new Long(user.getUserId()))).add(Restrictions.eq("createdBy", user.getUserName())).add(Restrictions.eq("interview_organizer_id", new Long(user.getUserId()))));
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      applicantList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantsForPaginationByUser()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return applicantList;
  }
  
  public List searchOwnApplicantsForPagination(User user, String fullname, String reqid, String prevorg, String email, String appdate, String cri, String applicantNo, String interviewstate, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside searchOwnApplicantsForPagination method");
    List applicantList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String hqlquery = " select distinct j from JobApplicant j , UserGroup ug where  ((j.owner.userId = :currentuserid and j.isGroup <> 'Y') or (j.ownerGroup.usergrpId = ug.usergrpId and  j.isGroup='Y' and ug.users.userId =:currentuserid)) ";
      if ((!StringUtils.isNullOrEmpty(fullname)) && (!fullname.equals("null"))) {
        hqlquery = hqlquery + " and j.fullName like :fullName";
      }
      if ((!StringUtils.isNullOrEmpty(applicantNo)) && (!applicantNo.equals("0")) && (!applicantNo.equals("null"))) {
        hqlquery = hqlquery + " and j.applicantId = :applicantId";
      }
      if ((!StringUtils.isNullOrEmpty(email)) && (!email.equals("null"))) {
        hqlquery = hqlquery + " and j.email like :email";
      }
      if ((!StringUtils.isNullOrEmpty(interviewstate)) && (!interviewstate.equals("NoValue")) && (!interviewstate.equals("null"))) {
        if (interviewstate.equalsIgnoreCase("Offer Accepted")) {
          hqlquery = hqlquery + " and j.interviewState = :interviewState";
        } else {
          hqlquery = hqlquery + " and j.interviewState like :interviewState";
        }
      }
      if ((!StringUtils.isNullOrEmpty(prevorg)) && (!prevorg.equals("null"))) {
        hqlquery = hqlquery + " and j.previousOrganization like :previousOrganization";
      }
      if (dir_str.equalsIgnoreCase("asc")) {
        hqlquery = hqlquery + " order by j." + sort_str + " asc";
      } else {
        hqlquery = hqlquery + " order by j." + sort_str + " desc";
      }
      Query query = session.createQuery(hqlquery);
      
      query.setLong("currentuserid", user.getUserId());
      if ((!StringUtils.isNullOrEmpty(fullname)) && (!fullname.equals("null"))) {
        query.setString("fullName", '%' + fullname + '%');
      }
      if ((!StringUtils.isNullOrEmpty(applicantNo)) && (!applicantNo.equals("0")) && (!applicantNo.equals("null"))) {
        query.setLong("applicantId", new Long(applicantNo).longValue());
      }
      if ((!StringUtils.isNullOrEmpty(email)) && (!email.equals("null"))) {
        query.setString("email", '%' + email + '%');
      }
      if ((!StringUtils.isNullOrEmpty(interviewstate)) && (!interviewstate.equals("NoValue")) && (!interviewstate.equals("null"))) {
        if (interviewstate.equalsIgnoreCase("Offer Accepted")) {
          query.setString("interviewState", interviewstate);
        } else {
          query.setString("interviewState", '%' + interviewstate + '%');
        }
      }
      if ((!StringUtils.isNullOrEmpty(prevorg)) && (!prevorg.equals("null"))) {
        query.setString("previousOrganization", '%' + prevorg + '%');
      }
      query.setFirstResult(startIndex);
      query.setMaxResults(pageSize);
      
      logger.info(query.getQueryString());
      

      applicantList = query.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantsForPaginationByUser()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return applicantList;
  }
  
  private String buildSQLQueryforSearch(User user, ApplicantSearchCriteria criteria)
  {
    String sql = "";
    if ((!StringUtils.isNullOrEmpty(criteria.getInterviewstate())) && (!criteria.getInterviewstate().equals("NoValue")) && (!criteria.getInterviewstate().equals("null")))
    {
      if (criteria.getInterviewstate().equalsIgnoreCase("Offer Accepted")) {
        sql = sql + " and ja.interview_state = " + "'" + criteria.getInterviewstate() + "'";
      } else if (criteria.getInterviewstate().equalsIgnoreCase("Application Submitted")) {
        sql = sql + " and ja.interview_state = " + "'" + criteria.getInterviewstate() + "'";
      } else {
        sql = sql + " and ja.interview_state like '%" + criteria.getInterviewstate() + "%' ";
      }
    }
    else if ((!StringUtils.isNullOrEmpty(criteria.getScreenName())) && (criteria.getScreenName().equals("ON_BOARDING_SEARCH_SCREEN")))
    {
      String insql = "'Offer Accepted','On Board - Joined'";
      sql = sql + " and ja.interview_state in (" + insql + ") ";
    }
    if ((!StringUtils.isNullOrEmpty(criteria.getFullname())) && (!criteria.getFullname().equals("null"))) {
      sql = sql + " and ja.full_name like '%" + criteria.getFullname() + "%' ";
    }
    if ((!StringUtils.isNullOrEmpty(criteria.getApplicantNo())) && (!criteria.getApplicantNo().equals("0")) && (!criteria.getApplicantNo().equals("null"))) {
      sql = sql + " and ja.application_id = " + criteria.getApplicantNo();
    }
    if ((!StringUtils.isNullOrEmpty(criteria.getVendorId())) && (!criteria.getVendorId().equals("0")) && (!criteria.getVendorId().equals("null"))) {
      sql = sql + " and ja.vendor_id = " + criteria.getVendorId();
    }
    if ((criteria.getTagList() != null) && (criteria.getTagList().size() > 0)) {
      sql = sql + " and ja.application_id in ( select applicant_id from applicant_tags where tag_name in (" + QueryBuilderApplicant.buildCommaseparated(criteria.getTagList()) + "))";
    }
    if ((!StringUtils.isNullOrEmpty(criteria.getReqid())) && (!criteria.getReqid().equals("0")) && (!criteria.getReqid().equals("null"))) {
      sql = sql + " and ja.jb_req_id = " + criteria.getReqid();
    }
    if ((!StringUtils.isNullOrEmpty(criteria.getPrevorg())) && (!criteria.getPrevorg().equals("null"))) {
      sql = sql + " and ja.previousOrganization like '%" + criteria.getPrevorg() + "%' ";
    }
    if ((!StringUtils.isNullOrEmpty(criteria.getEmail())) && (!criteria.getEmail().equals("null"))) {
      sql = sql + " and ja.email_id like '%" + criteria.getEmail() + "%' ";
    }
    if ((!StringUtils.isNullOrEmpty(criteria.getOnboardingProcessStatus())) && (!criteria.getOnboardingProcessStatus().equals("null"))) {
      sql = sql + " and ja.isInitiateJoiningProcess = '" + criteria.getOnboardingProcessStatus() + "' ";
    }
    if ((!StringUtils.isNullOrEmpty(criteria.getOrgId())) && (!criteria.getOrgId().equals("0")) && (!criteria.getOrgId().equals("null"))) {
      sql = sql + " and jr.org_id = " + criteria.getOrgId();
    }
    if ((!StringUtils.isNullOrEmpty(criteria.getDepartmentId())) && (!criteria.getDepartmentId().equals("0")) && (!criteria.getDepartmentId().equals("null"))) {
      sql = sql + " and jr.department_id = " + criteria.getDepartmentId();
    }
    if ((!StringUtils.isNullOrEmpty(criteria.getProjectcodeId())) && (!criteria.getProjectcodeId().equals("0")) && (!criteria.getProjectcodeId().equals("null"))) {
      sql = sql + " and jr.project_id = " + criteria.getProjectcodeId();
    }
    if ((!StringUtils.isNullOrEmpty(criteria.getAppliedcri())) && (!criteria.getAppliedcri().equals("NoValue")))
    {
      String datepattern = DateUtil.defaultdateformatforSQL;
      String datepatternuser = DateUtil.getDatePatternFormat(user.getLocale());
      

      String fromdate = criteria.getFromdate();
      String todate = criteria.getTodate();
      if (criteria.getAppliedcri().equals(Common.APPLIED_DATE))
      {
        logger.info("inside applied date");
        if (((StringUtils.isNullOrEmpty(fromdate)) || (fromdate.equals("null"))) && (StringUtils.isNullOrEmpty(todate)) && (todate.equals("null")))
        {
          logger.info("fromdate and todate is null" + fromdate + todate);
        }
        else
        {
          if ((!StringUtils.isNullOrEmpty(fromdate)) && (!fromdate.equals("null")))
          {
            java.util.Date fromdt1 = DateUtil.convertStringDateToDate(fromdate, datepatternuser);
            
            logger.info("fromdt1" + fromdt1);
            fromdate = DateUtil.convertDateToStringDate(fromdt1, datepattern);
          }
          else
          {
            fromdate = DateUtil.convertDateToStringDate(new java.util.Date(), datepattern);
          }
          if ((!StringUtils.isNullOrEmpty(todate)) && (!todate.equals("null")))
          {
            java.util.Date todt1 = DateUtil.convertStringDateToDate(todate, datepatternuser);
            
            logger.info("todt1" + todt1);
            todate = DateUtil.convertDateToStringDate(todt1, datepattern);
          }
          else
          {
            todate = DateUtil.convertDateToStringDate(new java.util.Date(), datepattern);
          }
          sql = sql + " and DATE_FORMAT( ja.applied_datetime, '%M %e, %Y')  >= " + "'" + fromdate + "'" + " and DATE_FORMAT( ja.applied_datetime, '%M %e, %Y')  <= " + "'" + todate + "'";
        }
      }
      if (criteria.getAppliedcri().equals(Common.TARGET_ON_BOARD_DATE))
      {
        logger.info("inside target on board");
        if (((StringUtils.isNullOrEmpty(fromdate)) || (fromdate.equals("null"))) && (StringUtils.isNullOrEmpty(todate)) && (todate.equals("null")))
        {
          logger.info("fromdate and todate is null" + fromdate + todate);
        }
        else
        {
          if ((!StringUtils.isNullOrEmpty(fromdate)) && (!fromdate.equals("null")))
          {
            java.util.Date fromdt1 = DateUtil.convertStringDateToDate(fromdate, datepatternuser);
            
            logger.info("fromdt1" + fromdt1);
            fromdate = DateUtil.convertDateToStringDate(fromdt1, datepattern);
          }
          else
          {
            fromdate = DateUtil.convertDateToStringDate(new java.util.Date(), datepattern);
          }
          if ((!StringUtils.isNullOrEmpty(todate)) && (!todate.equals("null")))
          {
            java.util.Date todt1 = DateUtil.convertStringDateToDate(todate, datepatternuser);
            
            logger.info("todt1" + todt1);
            todate = DateUtil.convertDateToStringDate(todt1, datepattern);
          }
          else
          {
            todate = DateUtil.convertDateToStringDate(new java.util.Date(), datepattern);
          }
          sql = sql + " and DATE_FORMAT( ja.targetjoining_date, '%M %e, %Y')  >= " + "'" + fromdate + "'" + " and DATE_FORMAT( ja.targetjoining_date, '%M %e, %Y')  <= " + "'" + todate + "'";
        }
      }
      if (criteria.getAppliedcri().equals(Common.TARGET_OFFER_RELEASE_DATE))
      {
        logger.info("inside target offer release date");
        if (((StringUtils.isNullOrEmpty(fromdate)) || (fromdate.equals("null"))) && (StringUtils.isNullOrEmpty(todate)) && (todate.equals("null")))
        {
          logger.info("fromdate and todate is null" + fromdate + todate);
        }
        else
        {
          if ((!StringUtils.isNullOrEmpty(fromdate)) && (!fromdate.equals("null")))
          {
            java.util.Date fromdt1 = DateUtil.convertStringDateToDate(fromdate, datepatternuser);
            
            logger.info("fromdt1" + fromdt1);
            fromdate = DateUtil.convertDateToStringDate(fromdt1, datepattern);
          }
          else
          {
            fromdate = DateUtil.convertDateToStringDate(new java.util.Date(), datepattern);
          }
          if ((!StringUtils.isNullOrEmpty(todate)) && (!todate.equals("null")))
          {
            java.util.Date todt1 = DateUtil.convertStringDateToDate(todate, datepatternuser);
            
            logger.info("todt1" + todt1);
            todate = DateUtil.convertDateToStringDate(todt1, datepattern);
          }
          else
          {
            todate = DateUtil.convertDateToStringDate(new java.util.Date(), datepattern);
          }
          sql = sql + " and DATE_FORMAT( ja.targerofferdate, '%M %e, %Y')  >= " + "'" + fromdate + "'" + " and DATE_FORMAT( ja.targerofferdate, '%M %e, %Y')  <= " + "'" + todate + "'";
        }
      }
      if (criteria.getAppliedcri().equals(Common.OFFER_RELEASED_DATE))
      {
        logger.info("inside offer released date");
        if (((StringUtils.isNullOrEmpty(fromdate)) || (fromdate.equals("null"))) && (StringUtils.isNullOrEmpty(todate)) && (todate.equals("null")))
        {
          logger.info("fromdate and todate is null" + fromdate + todate);
        }
        else
        {
          if ((!StringUtils.isNullOrEmpty(fromdate)) && (!fromdate.equals("null")))
          {
            java.util.Date fromdt1 = DateUtil.convertStringDateToDate(fromdate, datepatternuser);
            
            logger.info("fromdt1" + fromdt1);
            fromdate = DateUtil.convertDateToStringDate(fromdt1, datepattern);
          }
          else
          {
            fromdate = DateUtil.convertDateToStringDate(new java.util.Date(), datepattern);
          }
          if ((!StringUtils.isNullOrEmpty(todate)) && (!todate.equals("null")))
          {
            java.util.Date todt1 = DateUtil.convertStringDateToDate(todate, datepatternuser);
            
            logger.info("todt1" + todt1);
            todate = DateUtil.convertDateToStringDate(todt1, datepattern);
          }
          else
          {
            todate = DateUtil.convertDateToStringDate(new java.util.Date(), datepattern);
          }
          sql = sql + " and DATE_FORMAT( ja.offerreleasedate, '%M %e, %Y')  >= " + "'" + fromdate + "'" + " and DATE_FORMAT( ja.offerreleasedate, '%M %e, %Y')  <= " + "'" + todate + "'";
        }
      }
      if (criteria.getAppliedcri().equals(Common.ON_BOARDED_DATE))
      {
        logger.info("inside on boarded date");
        if (((StringUtils.isNullOrEmpty(fromdate)) || (fromdate.equals("null"))) && (StringUtils.isNullOrEmpty(todate)) && (todate.equals("null")))
        {
          logger.info("fromdate and todate is null" + fromdate + todate);
        }
        else
        {
          if ((!StringUtils.isNullOrEmpty(fromdate)) && (!fromdate.equals("null")))
          {
            java.util.Date fromdt1 = DateUtil.convertStringDateToDate(fromdate, datepatternuser);
            
            logger.info("fromdt1" + fromdt1);
            fromdate = DateUtil.convertDateToStringDate(fromdt1, datepattern);
          }
          else
          {
            fromdate = DateUtil.convertDateToStringDate(new java.util.Date(), datepattern);
          }
          if ((!StringUtils.isNullOrEmpty(todate)) && (!todate.equals("null")))
          {
            java.util.Date todt1 = DateUtil.convertStringDateToDate(todate, datepatternuser);
            
            logger.info("todt1" + todt1);
            todate = DateUtil.convertDateToStringDate(todt1, datepattern);
          }
          else
          {
            todate = DateUtil.convertDateToStringDate(new java.util.Date(), datepattern);
          }
          sql = sql + " and DATE_FORMAT( ja.joined_date, '%M %e, %Y')  >= " + "'" + fromdate + "'" + " and DATE_FORMAT( ja.joined_date, '%M %e, %Y')  <= " + "'" + todate + "'";
        }
      }
    }
    if (((!StringUtils.isNullOrEmpty(criteria.getScreenName())) && ((criteria.getScreenName().equals("ALL_APP_SEARCH_SCREEN")) || (criteria.getScreenName().equals("APP_SEARCH_SCREEN_HIRING_MGR")))) || (criteria.getScreenName().equals("APP_SEARCH_SCREEN_RECRUITER")) || (criteria.getScreenName().equals("ON_BOARDING_SEARCH_SCREEN")))
    {
      if ((!StringUtils.isNullOrEmpty(criteria.getInterviewstate())) && (!criteria.getInterviewstate().equals("NoValue")) && (!criteria.getInterviewstate().equals("null")))
      {
        if (criteria.getInterviewstate().startsWith("Rejected")) {
          sql = sql + " and ja.status='R'  ";
        } else if (criteria.getInterviewstate().startsWith("OnHold")) {
          sql = sql + " and ja.status='H'  ";
        } else if (criteria.getInterviewstate().startsWith("Mark deleted")) {
          sql = sql + " and ja.status='D'  ";
        } else {
          sql = sql + " and ja.status='A'  ";
        }
      }
      else {
        sql = sql + " and ja.status='A'  ";
      }
    }
    else if ((!StringUtils.isNullOrEmpty(criteria.getInterviewstate())) && (!criteria.getInterviewstate().equals("NoValue")) && (!criteria.getInterviewstate().equals("null")))
    {
      if (criteria.getInterviewstate().startsWith("Rejected")) {
        sql = sql + " and ja.status='R' and u.user_id = ja.owner ";
      } else if (criteria.getInterviewstate().startsWith("OnHold")) {
        sql = sql + " and ja.status='H' and u.user_id = ja.owner ";
      } else if (criteria.getInterviewstate().startsWith("Mark deleted")) {
        sql = sql + " and ja.status='D' and u.user_id = ja.owner ";
      } else {
        sql = sql + " and ja.status='A' and u.user_id = ja.owner ";
      }
    }
    else {
      sql = sql + " and ja.status='A' and u.user_id = ja.owner ";
    }
    return sql;
  }
  
  public Map searchOwnApplicantsForPaginationHiringMgr(User user, ApplicantSearchCriteria criteria, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside searchOwnApplicantsForPaginationHiringMgr method");
    List applicantList = new ArrayList();
    List newapplicantList = new ArrayList();
    Map searchApplicantMap = new HashMap();
    String sessionId = "";
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String indexsaerchtable = "";
      if ((!StringUtils.isNullOrEmpty(criteria.getKeywords())) && (!criteria.getKeywords().equals("null"))) {
        indexsaerchtable = " , index_applicant_search idx ";
      }
      String sqlgen = getSQLApplicants("APP_SEARCH_SCREEN_HIRING_MGR");
      String sql = "select ja.application_id,ja.uuid,ja.jb_req_id,ja.offerownerId,ja.full_name,jr.jb_req_code,jr.recruiter_name,(select concat(u.first_name,' ', u.last_name) from user_data u where u.user_id=ja.owner) ownername,ja.owner,(select ug.user_group_name from user_group ug where ug.user_group_id=ja.owner_group) user_group_name,ja.is_group,ja.owner_group,jr.recruiter_id,jr.recruiter_name,jr.is_recruiter_group,jr.uuid as juuid," + sqlgen + " from job_applications ja , job_requisition jr " + indexsaerchtable + " where ja.jb_req_id = jr.jb_req_id " + " and jr.hiring_mgr_id = :hiring_mgr_id ";
      







      String sqlcount = "select count(ja.application_id) as count from job_applications ja , job_requisition jr  " + indexsaerchtable + " where ja.jb_req_id = jr.jb_req_id " + " and jr.hiring_mgr_id = :hiring_mgr_id ";
      


      String orgsqlgen = getOtherSQLApplicants("APP_SEARCH_SCREEN_HIRING_MGR");
      if (!StringUtils.isNullOrEmpty(orgsqlgen))
      {
        sql = "select ja.application_id,ja.uuid,ja.jb_req_id,ja.offerownerId,ja.full_name,jr.jb_req_code,jr.recruiter_name,(select concat(u.first_name,' ', u.last_name) from user_data u where u.user_id=ja.owner) ownername,ja.owner,(select ug.user_group_name from user_group ug where ug.user_group_id=ja.owner_group) user_group_name,ja.is_group,ja.owner_group,jr.recruiter_id,jr.recruiter_name,jr.is_recruiter_group,jr.uuid as juuid," + orgsqlgen + "," + sqlgen + " from job_applications ja , job_requisition jr  " + indexsaerchtable + " where ja.jb_req_id = jr.jb_req_id " + " and jr.hiring_mgr_id = :hiring_mgr_id ";
        









        sqlcount = "select count(ja.application_id) as count from job_applications ja , job_requisition jr  " + indexsaerchtable + " where ja.jb_req_id = jr.jb_req_id " + " and jr.hiring_mgr_id = :hiring_mgr_id ";
      }
      criteria.setScreenName("APP_SEARCH_SCREEN_HIRING_MGR");
      String sql1 = buildSQLQueryforSearch(user, criteria);
      sqlcount = sqlcount + sql1;
      sql = sql + sql1;
      

      logger.info("criteria.getKeywords()" + criteria.getKeywords());
      if ((!StringUtils.isNullOrEmpty(criteria.getKeywords())) && (!criteria.getKeywords().equals("null")))
      {
        IndexSearch idxsearch = new IndexSearch();
        List appids = idxsearch.search(criteria.getKeywords());
        if ((appids != null) && (appids.size() > 0))
        {
          sessionId = UUID.randomUUID().toString();
          addToIndexSearch(appids, sessionId);
          

          sql = sql + " and ja.application_id = idx.applicant_id and idx.session_id = " + "'" + sessionId + "'" + "  order by idx.sequence ";
          sqlcount = sqlcount + " and ja.application_id = idx.applicant_id and idx.session_id = " + "'" + sessionId + "'";
        }
      }
      logger.info("sql" + sql);
      String tmpcolumnname = (String)Constant.applicantTableBeanColumnNameMap.get(sort_str);
      
      sort_str = "ja." + tmpcolumnname;
      if (tmpcolumnname == null) {
        sort_str = (String)Constant.notApplicantTableBeanColumnNameMap.get(sort_str);
      }
      if ((StringUtils.isNullOrEmpty(criteria.getKeywords())) || (criteria.getKeywords().equals("null"))) {
        if (dir_str.equalsIgnoreCase("asc")) {
          sql = sql + "order by " + sort_str + " ASC";
        } else {
          sql = sql + "order by " + sort_str + " DESC";
        }
      }
      logger.info("sql" + sql);
      Query query = session.createSQLQuery(sql);
      query.setLong("hiring_mgr_id", user.getUserId());
      query.setFirstResult(startIndex);
      query.setMaxResults(pageSize);
      
      applicantList = query.list();
      
      logger.info("applicantList.size()" + applicantList.size());
      for (int i = 0; i < applicantList.size(); i++)
      {
        Object[] obj = (Object[])applicantList.get(i);
        JobApplicant jobapp = new JobApplicant();
        
        BigInteger appId = (BigInteger)obj[0];
        long applicationId = appId.longValue();
        jobapp.setApplicantId(applicationId);
        jobapp.setUuid((String)obj[1]);
        BigInteger reqId = (BigInteger)obj[2];
        long requistionId = reqId.longValue();
        jobapp.setReqId(requistionId);
        BigInteger offowId = (BigInteger)obj[3];
        long offerownerid = offowId.longValue();
        jobapp.setOfferownerId(offerownerid);
        jobapp.setFullName((String)obj[4]);
        jobapp.setJobCode((String)obj[5]);
        jobapp.setRecruiter((String)obj[6]);
        
        jobapp.ownername = ((String)obj[7]);
        BigInteger ownerId = (BigInteger)obj[8];
        if (ownerId != null) {
          jobapp.ownerId = ownerId.longValue();
        }
        jobapp.ownernamegroup = ((String)obj[9]);
        jobapp.isGroup = ((String)obj[10]);
        BigInteger ownergroupId = (BigInteger)obj[11];
        if (ownergroupId != null) {
          jobapp.ownergroupId = ownergroupId.longValue();
        }
        BigInteger recruiterId = (BigInteger)obj[12];
        if (recruiterId != null) {
          jobapp.recruiterId = recruiterId.longValue();
        }
        jobapp.setRecruiter((String)obj[13]);
        jobapp.recruitergroup = ((String)obj[14]);
        jobapp.setJuuid((String)obj[15]);
        int basevalue = 16;
        if (!StringUtils.isNullOrEmpty(orgsqlgen))
        {
          if (isColumnPresent("APP_SEARCH_SCREEN_HIRING_MGR", "hiringManager"))
          {
            jobapp.setHiringManager((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("APP_SEARCH_SCREEN_HIRING_MGR", "orgName"))
          {
            jobapp.setOrgName((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("APP_SEARCH_SCREEN_HIRING_MGR", "department"))
          {
            jobapp.setDepartment((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("APP_SEARCH_SCREEN_HIRING_MGR", "projectcode"))
          {
            jobapp.setProjectcode((String)obj[basevalue]);
            basevalue += 1;
          }
        }
        jobapp = setApplicantListValues(obj, "APP_SEARCH_SCREEN_HIRING_MGR", jobapp, basevalue);
        
        newapplicantList.add(jobapp);
      }
      logger.info("sqlcount" + sqlcount);
      if (criteria.isCountRequired())
      {
        Query querycount = session.createSQLQuery(sqlcount);
        querycount.setLong("hiring_mgr_id", user.getUserId());
        Object obj = querycount.uniqueResult();
        logger.info("obj" + obj);
        BigInteger count = (BigInteger)obj;
        Integer totalsize = new Integer(count.intValue());
        logger.info("totalsize" + totalsize.intValue());
        searchApplicantMap.put(Common.APPLICANT_COUNT, totalsize);
      }
      searchApplicantMap.put(Common.APPLICANT_LIST, newapplicantList);
      
      deleteIndexSearch(sessionId);
    }
    catch (Exception e)
    {
      logger.error("Exception on searchOwnApplicantsForPaginationHiringMgr()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return searchApplicantMap;
  }
  
  public Map searchTalentPoolApplicants(User user, String id, String applicantNo, String applicantName, String primarySkill, String keywords, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside searchTalentPoolApplicants method");
    List applicantList = new ArrayList();
    List newapplicantList = new ArrayList();
    Map searchApplicantMap = new HashMap();
    org.hibernate.Session session = null;
    String sessionId = "";
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String indexsaerchtable = "";
      if ((!StringUtils.isNullOrEmpty(keywords)) && (!keywords.equals("null"))) {
        indexsaerchtable = " , index_applicant_search idx ";
      }
      String sql = "select ja.application_id,ja.uuid,ja.full_name,ja.email_id,ja.city,ja.heighest_qualification,ja.applied_datetime,ja.referer_name,ja.created_by,ja.phone,ja.resume_source_id,ja.primary_skill from job_applications ja ,talent_pool_elements t" + indexsaerchtable + " where t.applicantId=ja.application_id" + " and (t.ownerEl= :id or t.applicantpoolidInitial = :id)";
      


      String sqlcount = "select count(ja.application_id) as count from job_applications ja ,talent_pool_elements t" + indexsaerchtable + " where t.applicantId=ja.application_id" + " and (t.ownerEl= :id or t.applicantpoolidInitial = :id) ";
      if ((!StringUtils.isNullOrEmpty(applicantNo)) && (!applicantNo.equals("null")) && (!applicantNo.equals("0")))
      {
        sql = sql + " and ja.application_id = " + new Long(applicantNo).longValue();
        
        sqlcount = sqlcount + " and ja.application_id = " + new Long(applicantNo).longValue();
      }
      if ((!StringUtils.isNullOrEmpty(applicantName)) && (!applicantName.equals("null")))
      {
        sql = sql + " and ja.full_name like '%" + applicantName + "%' ";
        sqlcount = sqlcount + " and ja.full_name like '%" + applicantName + "%' ";
      }
      if ((!StringUtils.isNullOrEmpty(primarySkill)) && (!primarySkill.equals("null")))
      {
        sql = sql + " and ja.primary_skill like '%" + primarySkill + "%' ";
        
        sqlcount = sqlcount + " and ja.primary_skill like '%" + primarySkill + "%' ";
      }
      logger.info("sql" + sql);
      
      logger.info("keywords" + keywords);
      if ((!StringUtils.isNullOrEmpty(keywords)) && (!keywords.equals("null")))
      {
        IndexSearch idxsearch = new IndexSearch();
        List appids = idxsearch.search(keywords);
        if ((appids != null) && (appids.size() > 0))
        {
          sessionId = UUID.randomUUID().toString();
          addToIndexSearch(appids, sessionId);
          

          sql = sql + " and ja.application_id = idx.applicant_id and idx.session_id = " + "'" + sessionId + "'" + "  order by idx.sequence ";
          sqlcount = sqlcount + " and ja.application_id = idx.applicant_id and idx.session_id = " + "'" + sessionId + "'";
        }
      }
      String tmpcolumnname = (String)Constant.applicantTableBeanColumnNameMap.get(sort_str);
      
      sort_str = "ja." + tmpcolumnname;
      if (tmpcolumnname == null) {
        sort_str = (String)Constant.notApplicantTableBeanColumnNameMap.get(sort_str);
      }
      if ((StringUtils.isNullOrEmpty(keywords)) || (keywords.equals("null"))) {
        if (dir_str.equalsIgnoreCase("asc")) {
          sql = sql + " order by " + sort_str + " ASC";
        } else {
          sql = sql + " order by " + sort_str + " DESC";
        }
      }
      logger.info("sql" + sql);
      Query query = session.createSQLQuery(sql);
      query.setLong("id", new Long(id).longValue());
      query.setFirstResult(startIndex);
      query.setMaxResults(pageSize);
      
      applicantList = query.list();
      
      logger.info("applicantList.size()" + applicantList.size());
      for (int i = 0; i < applicantList.size(); i++)
      {
        Object[] obj = (Object[])applicantList.get(i);
        JobApplicant jobapp = new JobApplicant();
        
        BigInteger appId = (BigInteger)obj[0];
        long applicationId = appId.longValue();
        jobapp.setApplicantId(applicationId);
        jobapp.setUuid((String)obj[1]);
        jobapp.setFullName((String)obj[2]);
        jobapp.setEmail((String)obj[3]);
        jobapp.setCity((String)obj[4]);
        jobapp.setHeighestQualification((String)obj[5]);
        jobapp.setAppliedDate((java.util.Date)obj[6]);
        jobapp.setReferrerName((String)obj[7]);
        jobapp.setCreatedBy((String)obj[8]);
        jobapp.setPhone((String)obj[9]);
        Short resourceId = (Short)obj[10];
        if (resourceId != null) {
          jobapp.setResumeSourcesId(resourceId.intValue());
        }
        jobapp.setPrimarySkill((String)obj[11]);
        newapplicantList.add(jobapp);
      }
      Query querycount = session.createSQLQuery(sqlcount);
      querycount.setLong("id", new Long(id).longValue());
      Object obj1 = querycount.uniqueResult();
      BigInteger count = (BigInteger)obj1;
      Integer totalsize = new Integer(count.intValue());
      logger.info("totalsize" + totalsize.intValue());
      searchApplicantMap.put(Common.APPLICANT_COUNT, totalsize);
      
      searchApplicantMap.put(Common.APPLICANT_LIST, newapplicantList);
      
      deleteIndexSearch(sessionId);
    }
    catch (Exception e)
    {
      logger.error("Exception on searchTalentPoolApplicants()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return searchApplicantMap;
  }
  
  public Map searchApplicantsInTalentPool(User user, String applicantNo, String applicantName, String primarySkill, String keywords, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside searchApplicantsInTalentPool method");
    List applicantList = new ArrayList();
    List newapplicantList = new ArrayList();
    Map searchApplicantMap = new HashMap();
    org.hibernate.Session session = null;
    String sessionId = "";
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String indexsaerchtable = "";
      if ((!StringUtils.isNullOrEmpty(keywords)) && (!keywords.equals("null"))) {
        indexsaerchtable = " , index_applicant_search idx ";
      }
      String sql1 = " select distinct ja.application_id,ja.uuid,ja.full_name,ja.email_id,ja.city,ja.heighest_qualification,ja.applied_datetime,ja.referer_name,ja.created_by,ja.phone,ja.resume_source_id,ja.primary_skill from job_applications ja ,talent_pool tp ,  talent_pool_elements te,user_group_users ug " + indexsaerchtable + " where ((te.applicantId=ja.application_id " + " and tp.status='A' and tp.is_group='N' AND tp.assign_to_id= :user_id and tp.talent_pool_id=te.applicantpoolidInitial ";
      



      String sql2 = " (te.applicantId=ja.application_id  and tp.status='A' and tp.is_group='Y' AND tp.assign_to_id=ug.user_group_id and ug.user_id= :user_id and tp.talent_pool_id=te.applicantpoolidInitial";
      if ((!StringUtils.isNullOrEmpty(applicantNo)) && (!applicantNo.equals("null")) && (!applicantNo.equals("0")))
      {
        sql1 = sql1 + " and ja.applicant_number = " + new Long(applicantNo).longValue();
        
        sql2 = sql2 + " and ja.applicant_number = " + new Long(applicantNo).longValue();
      }
      if ((!StringUtils.isNullOrEmpty(applicantName)) && (!applicantName.equals("null")))
      {
        sql1 = sql1 + " and ja.full_name like '%" + applicantName + "%' ";
        
        sql2 = sql2 + " and ja.full_name like '%" + applicantName + "%' ";
      }
      if ((!StringUtils.isNullOrEmpty(primarySkill)) && (!primarySkill.equals("null")))
      {
        sql1 = sql1 + " and ja.primary_skill like '%" + primarySkill + "%' ";
        
        sql2 = sql2 + " and ja.primary_skill like '%" + primarySkill + "%' ";
      }
      String sql = sql1 + " ) or " + sql2 + "))";
      logger.info("sql" + sql);
      String sqlcount = sql;
      

      logger.info("keywords" + keywords);
      if ((!StringUtils.isNullOrEmpty(keywords)) && (!keywords.equals("null")))
      {
        IndexSearch idxsearch = new IndexSearch();
        List appids = idxsearch.search(keywords);
        if ((appids != null) && (appids.size() > 0))
        {
          sessionId = UUID.randomUUID().toString();
          addToIndexSearch(appids, sessionId);
          

          sql = sql + " and ja.application_id = idx.applicant_id and idx.session_id = " + "'" + sessionId + "'" + "  order by idx.sequence ";
          sqlcount = sqlcount + " and ja.application_id = idx.applicant_id and idx.session_id = " + "'" + sessionId + "'";
        }
      }
      sql = sql + " and ja.super_user_key=" + user.getSuper_user_key() + " ";
      sqlcount = sqlcount + " and ja.super_user_key=" + user.getSuper_user_key() + " ";
      
      String tmpcolumnname = (String)Constant.applicantTableBeanColumnNameMap.get(sort_str);
      
      sort_str = "ja." + tmpcolumnname;
      if (tmpcolumnname == null) {
        sort_str = (String)Constant.notApplicantTableBeanColumnNameMap.get(sort_str);
      }
      if ((StringUtils.isNullOrEmpty(keywords)) || (keywords.equals("null"))) {
        if (dir_str.equalsIgnoreCase("asc")) {
          sql = sql + "order by " + sort_str + " ASC";
        } else {
          sql = sql + "order by " + sort_str + " DESC";
        }
      }
      logger.info("sql" + sql);
      Query query = session.createSQLQuery(sql);
      query.setLong("user_id", user.getUserId());
      query.setFirstResult(startIndex);
      query.setMaxResults(pageSize);
      
      applicantList = query.list();
      
      logger.info("applicantList.size()" + applicantList.size());
      for (int i = 0; i < applicantList.size(); i++)
      {
        Object[] obj = (Object[])applicantList.get(i);
        JobApplicant jobapp = new JobApplicant();
        
        BigInteger appId = (BigInteger)obj[0];
        long applicationId = appId.longValue();
        jobapp.setApplicantId(applicationId);
        jobapp.setUuid((String)obj[1]);
        jobapp.setFullName((String)obj[2]);
        jobapp.setEmail((String)obj[3]);
        jobapp.setCity((String)obj[4]);
        jobapp.setHeighestQualification((String)obj[5]);
        jobapp.setAppliedDate((java.util.Date)obj[6]);
        jobapp.setReferrerName((String)obj[7]);
        jobapp.setCreatedBy((String)obj[8]);
        jobapp.setPhone((String)obj[9]);
        Short resourceId = (Short)obj[10];
        if (resourceId != null) {
          jobapp.setResumeSourcesId(resourceId.intValue());
        }
        jobapp.setPrimarySkill((String)obj[11]);
        newapplicantList.add(jobapp);
      }
      Query querycount = session.createSQLQuery(sqlcount);
      querycount.setLong("user_id", user.getUserId());
      applicantList = querycount.list();
      
      Integer totalsize = new Integer(applicantList.size());
      
      searchApplicantMap.put(Common.APPLICANT_COUNT, totalsize);
      
      searchApplicantMap.put(Common.APPLICANT_LIST, newapplicantList);
      
      deleteIndexSearch(sessionId);
    }
    catch (Exception e)
    {
      logger.error("Exception on searchApplicantsInTalentPool()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return searchApplicantMap;
  }
  
  public Map searchOwnApplicantsForPaginationRecruiter(User user, ApplicantSearchCriteria criteria, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside searchOwnApplicantsForPaginationRecruiter method");
    List applicantList = new ArrayList();
    List newapplicantList = new ArrayList();
    Map searchApplicantMap = new HashMap();
    String sessionId = "";
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String indexsaerchtable = "";
      if ((!StringUtils.isNullOrEmpty(criteria.getKeywords())) && (!criteria.getKeywords().equals("null"))) {
        indexsaerchtable = " , index_applicant_search idx ";
      }
      String sqlgen = getSQLApplicants("APP_SEARCH_SCREEN_RECRUITER");
      String sql = "select distinct ja.application_id,ja.uuid,ja.jb_req_id,ja.offerownerId,ja.full_name,jr.jb_req_code,jr.recruiter_name, (select concat(u.first_name,' ', u.last_name) from user_data u where u.user_id=ja.owner) ownername,ja.owner,(select ug.user_group_name from user_group ug where ug.user_group_id=ja.owner_group) user_group_name,ja.is_group,ja.owner_group ,jr.recruiter_id,jr.recruiter_name,jr.is_recruiter_group,jr.uuid as juuid," + sqlgen + " from job_applications ja , job_requisition jr , user_group_users ugu " + indexsaerchtable + " where ja.jb_req_id = jr.jb_req_id " + " and ((jr.recruiter_id = :recruiter_id and jr.is_recruiter_group <> 'Y' ) " + " or (ja.offerownerid=:offerownerid  AND  ja.is_offer_owner_group <> 'Y') " + " or (ja.offerownerid= ugu.user_group_id  AND ja.is_offer_owner_group = 'Y' and ugu.user_id=:offerownerid)  " + " or (jr.recruiter_id= ugu.user_group_id  AND jr.is_recruiter_group = 'Y' and ugu.user_id=:recruiter_id)" + ")";
      











      String sqlcount = "select distinct ja.application_id  from  job_applications ja , job_requisition jr ,user_group_users ugu " + indexsaerchtable + " where ja.jb_req_id = jr.jb_req_id " + " and ((jr.recruiter_id = :recruiter_id and jr.is_recruiter_group <> 'Y' ) " + " or (ja.offerownerid=:offerownerid  AND  ja.is_offer_owner_group <> 'Y') " + " or (ja.offerownerid= ugu.user_group_id  AND ja.is_offer_owner_group = 'Y' and ugu.user_id=:offerownerid)  " + " or (jr.recruiter_id= ugu.user_group_id  AND jr.is_recruiter_group = 'Y' and ugu.user_id=:recruiter_id)" + ")";
      






      String orgsqlgen = getOtherSQLApplicants("APP_SEARCH_SCREEN_RECRUITER");
      if (!StringUtils.isNullOrEmpty(orgsqlgen))
      {
        sql = "select distinct ja.application_id,ja.uuid,ja.jb_req_id,ja.offerownerId,ja.full_name,jr.jb_req_code,jr.recruiter_name, (select concat(u.first_name,' ', u.last_name) from user_data u where u.user_id=ja.owner) ownername,ja.owner,(select ug.user_group_name from user_group ug where ug.user_group_id=ja.owner_group) user_group_name,ja.is_group,ja.owner_group ,jr.recruiter_id,jr.recruiter_name,jr.is_recruiter_group,jr.uuid as juuid," + orgsqlgen + "," + sqlgen + " from job_applications ja , job_requisition jr ,user_group_users ugu " + indexsaerchtable + " where ja.jb_req_id = jr.jb_req_id " + " and ((jr.recruiter_id = :recruiter_id and jr.is_recruiter_group <> 'Y' ) " + " or (ja.offerownerid=:offerownerid  AND  ja.is_offer_owner_group <> 'Y') " + " or (ja.offerownerid= ugu.user_group_id  AND ja.is_offer_owner_group = 'Y' and ugu.user_id=:offerownerid)  " + " or (jr.recruiter_id= ugu.user_group_id  AND jr.is_recruiter_group = 'Y' and ugu.user_id=:recruiter_id)" + ")";
        












        sqlcount = "select distinct ja.application_id from  job_applications ja , job_requisition jr , user_group_users ugu " + indexsaerchtable + " where ja.jb_req_id = jr.jb_req_id " + " and ((jr.recruiter_id = :recruiter_id and jr.is_recruiter_group <> 'Y' ) " + " or (ja.offerownerid=:offerownerid  AND  ja.is_offer_owner_group <> 'Y') " + " or (ja.offerownerid= ugu.user_group_id  AND ja.is_offer_owner_group = 'Y' and ugu.user_id=:offerownerid)  " + " or (jr.recruiter_id= ugu.user_group_id  AND jr.is_recruiter_group = 'Y' and ugu.user_id=:recruiter_id)" + ")";
      }
      criteria.setScreenName("APP_SEARCH_SCREEN_RECRUITER");
      String sql1 = buildSQLQueryforSearch(user, criteria);
      sqlcount = sqlcount + sql1;
      sql = sql + sql1;
      
      logger.info("criteria.getKeywords()" + criteria.getKeywords());
      if ((!StringUtils.isNullOrEmpty(criteria.getKeywords())) && (!criteria.getKeywords().equals("null")))
      {
        IndexSearch idxsearch = new IndexSearch();
        List appids = idxsearch.search(criteria.getKeywords());
        if ((appids != null) && (appids.size() > 0))
        {
          sessionId = UUID.randomUUID().toString();
          addToIndexSearch(appids, sessionId);
          

          sql = sql + " and ja.application_id = idx.applicant_id and idx.session_id = " + "'" + sessionId + "'" + "  order by idx.sequence ";
          sqlcount = sqlcount + " and ja.application_id = idx.applicant_id and idx.session_id = " + "'" + sessionId + "'";
        }
      }
      String tmpcolumnname = (String)Constant.applicantTableBeanColumnNameMap.get(sort_str);
      
      sort_str = "ja." + tmpcolumnname;
      if (tmpcolumnname == null) {
        sort_str = (String)Constant.notApplicantTableBeanColumnNameMap.get(sort_str);
      }
      if ((StringUtils.isNullOrEmpty(criteria.getKeywords())) || (criteria.getKeywords().equals("null"))) {
        if (dir_str.equalsIgnoreCase("asc")) {
          sql = sql + "order by " + sort_str + " ASC";
        } else {
          sql = sql + "order by " + sort_str + " DESC";
        }
      }
      logger.info("sql" + sql);
      Query query = session.createSQLQuery(sql);
      query.setLong("recruiter_id", user.getUserId());
      query.setLong("offerownerid", user.getUserId());
      query.setFirstResult(startIndex);
      query.setMaxResults(pageSize);
      logger.info("before sql" + new java.util.Date());
      applicantList = query.list();
      logger.info("after sql" + new java.util.Date());
      


      logger.info("applicantList.size()" + applicantList.size());
      for (int i = 0; i < applicantList.size(); i++)
      {
        Object[] obj = (Object[])applicantList.get(i);
        JobApplicant jobapp = new JobApplicant();
        
        BigInteger appId = (BigInteger)obj[0];
        long applicationId = appId.longValue();
        jobapp.setApplicantId(applicationId);
        jobapp.setUuid((String)obj[1]);
        BigInteger reqId = (BigInteger)obj[2];
        long requistionId = reqId.longValue();
        jobapp.setReqId(requistionId);
        BigInteger offowId = (BigInteger)obj[3];
        long offerownerid = offowId.longValue();
        jobapp.setOfferownerId(offerownerid);
        jobapp.setFullName((String)obj[4]);
        jobapp.setJobCode((String)obj[5]);
        jobapp.setRecruiter((String)obj[6]);
        
        jobapp.ownername = ((String)obj[7]);
        BigInteger ownerId = (BigInteger)obj[8];
        if (ownerId != null) {
          jobapp.ownerId = ownerId.longValue();
        }
        jobapp.ownernamegroup = ((String)obj[9]);
        jobapp.isGroup = ((String)obj[10]);
        BigInteger ownergroupId = (BigInteger)obj[11];
        if (ownergroupId != null) {
          jobapp.ownergroupId = ownergroupId.longValue();
        }
        BigInteger recruiterId = (BigInteger)obj[12];
        if (recruiterId != null) {
          jobapp.recruiterId = recruiterId.longValue();
        }
        jobapp.setRecruiter((String)obj[13]);
        jobapp.recruitergroup = ((String)obj[14]);
        jobapp.setJuuid((String)obj[15]);
        int basevalue = 16;
        if (!StringUtils.isNullOrEmpty(orgsqlgen))
        {
          if (isColumnPresent("APP_SEARCH_SCREEN_RECRUITER", "hiringManager"))
          {
            jobapp.setHiringManager((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("APP_SEARCH_SCREEN_RECRUITER", "orgName"))
          {
            jobapp.setOrgName((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("APP_SEARCH_SCREEN_RECRUITER", "department"))
          {
            jobapp.setDepartment((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("APP_SEARCH_SCREEN_RECRUITER", "projectcode"))
          {
            jobapp.setProjectcode((String)obj[basevalue]);
            basevalue += 1;
          }
        }
        jobapp = setApplicantListValues(obj, "APP_SEARCH_SCREEN_RECRUITER", jobapp, basevalue);
        
        newapplicantList.add(jobapp);
      }
      logger.info("sqlcount" + sqlcount);
      if (criteria.isCountRequired())
      {
        Query querycount = session.createSQLQuery(sqlcount);
        querycount.setLong("recruiter_id", user.getUserId());
        querycount.setLong("offerownerid", user.getUserId());
        List countList = querycount.list();
        Integer totalsize = new Integer(countList.size());
        logger.info("totalsize" + totalsize.intValue());
        searchApplicantMap.put(Common.APPLICANT_COUNT, totalsize);
      }
      searchApplicantMap.put(Common.APPLICANT_LIST, newapplicantList);
      
      deleteIndexSearch(sessionId);
    }
    catch (Exception e)
    {
      logger.error("Exception on searchOwnApplicantsForPaginationRecruiter()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return searchApplicantMap;
  }
  
  public List searchOwnApplicantsForPaginationHiringMgr(User user, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside searchOwnApplicantsForPaginationHiringMgr method");
    List applicantList = new ArrayList();
    List newapplicantList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String sql = "select ja.application_id,ja.full_name,ja.jb_req_id,ja.req_name,ja.interview_state,ja.targetjoining_date,ja.isInitiateJoiningProcess,ja.uuid,ja.offerownerId,ja.offerownerName,ja.email_id,ja.city,ja.heighest_qualification,ja.applied_datetime,ja.referer_name,u.first_name, u.last_name,ja.created_by,ja.phone from job_applications ja , job_requisition jr , user_data u  where ja.jb_req_id = jr.jb_req_id  and jr.hiring_mgr_id = :hiring_mgr_id ";
      


      sql = sql + " and ja.status='A' and u.user_id = ja.owner ";
      
      sort_str = "ja." + Constant.applicantTableBeanColumnNameMap.get(sort_str);
      if (Constant.applicantTableBeanColumnNameMap.get(sort_str) == null) {
        sort_str = (String)Constant.notApplicantTableBeanColumnNameMap.get(sort_str);
      }
      if (dir_str.equalsIgnoreCase("asc")) {
        sql = sql + "order by " + sort_str + " ASC";
      } else {
        sql = sql + "order by " + sort_str + " DESC";
      }
      Query query = session.createSQLQuery(sql);
      query.setLong("hiring_mgr_id", user.getUserId());
      query.setFirstResult(startIndex);
      query.setMaxResults(pageSize);
      
      applicantList = query.list();
      
      logger.info("applicantList.size()" + applicantList.size());
      for (int i = 0; i < applicantList.size(); i++)
      {
        Object[] obj = (Object[])applicantList.get(i);
        JobApplicant jobapp = new JobApplicant();
        
        BigInteger appId = (BigInteger)obj[0];
        long applicationId = appId.longValue();
        
        jobapp.setApplicantId(applicationId);
        jobapp.setFullName((String)obj[1]);
        BigInteger reqId = (BigInteger)obj[2];
        long requistionId = reqId.longValue();
        jobapp.setReqId(requistionId);
        jobapp.setJobTitle((String)obj[3]);
        jobapp.setInterviewState((String)obj[4]);
        java.util.Date targetjoingdate = (java.util.Date)obj[5];
        String targetjd = "";
        if (targetjoingdate != null)
        {
          String datepattern = Constant.getValue("defaultdateformat");
          if (user != null) {
            datepattern = DateUtil.getDatePatternFormat(user.getLocale());
          }
          targetjd = DateUtil.convertDateToStringDate(targetjoingdate, datepattern);
        }
        jobapp.setTargetjoiningdateStr(targetjd);
        jobapp.setInitiateJoiningProcess((String)obj[6]);
        jobapp.setUuid((String)obj[7]);
        BigInteger offowId = (BigInteger)obj[8];
        long offerownerid = offowId.longValue();
        jobapp.setOfferownerId(offerownerid);
        jobapp.setOfferownerName((String)obj[9]);
        jobapp.setEmail((String)obj[10]);
        jobapp.setCity((String)obj[11]);
        jobapp.setHeighestQualification((String)obj[12]);
        jobapp.setAppliedDate((java.util.Date)obj[13]);
        jobapp.setReferrerName((String)obj[14]);
        jobapp.ownername = ((String)obj[15] + " " + (String)obj[16]);
        jobapp.setCreatedBy((String)obj[17]);
        jobapp.setPhone((String)obj[18]);
        newapplicantList.add(jobapp);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on searchOwnApplicantsForPaginationHiringMgr()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return newapplicantList;
  }
  
  private String getSQLApplicants(String screenName)
  {
    String strcontents = "";
    if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("APP_SEARCH_SCREEN_HIRING_MGR")))
    {
      String strtemp = Constant.getValue("applicant.page.hiring.mgr.default.columns");
      
      String[] columns = StringUtils.tokenize(strtemp, ",");
      for (int i = 0; i < columns.length; i++) {
        if (!StringUtils.isNullOrEmpty((String)Constant.applicantTableBeanColumnNameMap.get(columns[i]))) {
          strcontents = strcontents + "ja." + Constant.applicantTableBeanColumnNameMap.get(columns[i]) + ",";
        }
      }
    }
    if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("APPLICANT_SUMMARY_SCREEN")))
    {
      String strtemp = Constant.getValue("applicant.page.applicant.summary.default.columns");
      
      String[] columns = StringUtils.tokenize(strtemp, ",");
      for (int i = 0; i < columns.length; i++) {
        if (!StringUtils.isNullOrEmpty((String)Constant.applicantTableBeanColumnNameMap.get(columns[i]))) {
          strcontents = strcontents + "ja." + Constant.applicantTableBeanColumnNameMap.get(columns[i]) + ",";
        }
      }
    }
    if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("APP_SEARCH_SCREEN_RECRUITER")))
    {
      String strtemp = Constant.getValue("applicant.page.recruiter.default.columns");
      
      String[] columns = StringUtils.tokenize(strtemp, ",");
      for (int i = 0; i < columns.length; i++) {
        if (!StringUtils.isNullOrEmpty((String)Constant.applicantTableBeanColumnNameMap.get(columns[i]))) {
          strcontents = strcontents + "ja." + Constant.applicantTableBeanColumnNameMap.get(columns[i]) + ",";
        }
      }
    }
    if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("ALL_APP_SEARCH_SCREEN")))
    {
      String strtemp = Constant.getValue("applicant.page.all.default.columns");
      
      String[] columns = StringUtils.tokenize(strtemp, ",");
      for (int i = 0; i < columns.length; i++) {
        if (!StringUtils.isNullOrEmpty((String)Constant.applicantTableBeanColumnNameMap.get(columns[i]))) {
          strcontents = strcontents + "ja." + Constant.applicantTableBeanColumnNameMap.get(columns[i]) + ",";
        }
      }
    }
    if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("ON_BOARDING_SEARCH_SCREEN")))
    {
      String strtemp = Constant.getValue("onboarding.page.default.columns");
      
      String[] columns = StringUtils.tokenize(strtemp, ",");
      for (int i = 0; i < columns.length; i++) {
        if (!StringUtils.isNullOrEmpty((String)Constant.applicantTableBeanColumnNameMap.get(columns[i]))) {
          strcontents = strcontents + "ja." + Constant.applicantTableBeanColumnNameMap.get(columns[i]) + ",";
        }
      }
    }
    if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("DASHBOARD_RECRUITER_IN_OFFER_PROCESS")))
    {
      String strtemp = Constant.getValue("dashboard.recruiter.applicants.offer.process");
      
      String[] columns = StringUtils.tokenize(strtemp, ",");
      for (int i = 0; i < columns.length; i++) {
        if (!StringUtils.isNullOrEmpty((String)Constant.applicantTableBeanColumnNameMap.get(columns[i]))) {
          strcontents = strcontents + "ja." + Constant.applicantTableBeanColumnNameMap.get(columns[i]) + ",";
        }
      }
    }
    if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("DASHBOARD_RECRUITER_IN_JOINED_PROCESS")))
    {
      String strtemp = Constant.getValue("dashboard.recruiter.applicants.joining.process");
      
      String[] columns = StringUtils.tokenize(strtemp, ",");
      for (int i = 0; i < columns.length; i++) {
        if (!StringUtils.isNullOrEmpty((String)Constant.applicantTableBeanColumnNameMap.get(columns[i]))) {
          strcontents = strcontents + "ja." + Constant.applicantTableBeanColumnNameMap.get(columns[i]) + ",";
        }
      }
    }
    strcontents = strcontents + "ja.offerownerId,ja.offerownerName,ja.is_offer_owner_group,";
    
    strcontents = strcontents.substring(0, strcontents.length() - 1);
    logger.info(strcontents);
    return strcontents;
  }
  
  private boolean isColumnPresent(String screenName, String firldname)
  {
    boolean isPresent = false;
    List columnsList = new ArrayList();
    if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("APP_SEARCH_SCREEN_HIRING_MGR")))
    {
      String strtemp = Constant.getValue("applicant.page.hiring.mgr.default.columns");
      
      String[] columns = StringUtils.tokenize(strtemp, ",");
      if (columns != null) {
        Collections.addAll(columnsList, columns);
      }
    }
    if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("APPLICANT_SUMMARY_SCREEN")))
    {
      String strtemp = Constant.getValue("applicant.page.applicant.summary.default.columns");
      
      String[] columns = StringUtils.tokenize(strtemp, ",");
      if (columns != null) {
        Collections.addAll(columnsList, columns);
      }
    }
    if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("APP_SEARCH_SCREEN_RECRUITER")))
    {
      String strtemp = Constant.getValue("applicant.page.recruiter.default.columns");
      
      String[] columns = StringUtils.tokenize(strtemp, ",");
      if (columns != null) {
        Collections.addAll(columnsList, columns);
      }
    }
    if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("ALL_APP_SEARCH_SCREEN")))
    {
      String strtemp = Constant.getValue("applicant.page.all.default.columns");
      
      String[] columns = StringUtils.tokenize(strtemp, ",");
      if (columns != null) {
        Collections.addAll(columnsList, columns);
      }
    }
    if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("ON_BOARDING_SEARCH_SCREEN")))
    {
      String strtemp = Constant.getValue("onboarding.page.default.columns");
      
      String[] columns = StringUtils.tokenize(strtemp, ",");
      if (columns != null) {
        Collections.addAll(columnsList, columns);
      }
    }
    if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("DASHBOARD_RECRUITER_IN_OFFER_PROCESS")))
    {
      String strtemp = Constant.getValue("dashboard.recruiter.applicants.offer.process");
      
      String[] columns = StringUtils.tokenize(strtemp, ",");
      if (columns != null) {
        Collections.addAll(columnsList, columns);
      }
    }
    if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("DASHBOARD_RECRUITER_IN_JOINED_PROCESS")))
    {
      String strtemp = Constant.getValue("dashboard.recruiter.applicants.joining.process");
      
      String[] columns = StringUtils.tokenize(strtemp, ",");
      if (columns != null) {
        Collections.addAll(columnsList, columns);
      }
    }
    if (columnsList.contains(firldname)) {
      isPresent = true;
    }
    return isPresent;
  }
  
  private String getOtherSQLApplicants(String screenName)
  {
    String strcontents = "";
    if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("APP_SEARCH_SCREEN_HIRING_MGR")))
    {
      String strtemp = Constant.getValue("applicant.page.hiring.mgr.default.columns");
      
      String[] columns = StringUtils.tokenize(strtemp, ",");
      List columnsList = new ArrayList();
      if (columns != null) {
        Collections.addAll(columnsList, columns);
      }
      if (columnsList.contains("hiringManager")) {
        strcontents = strcontents + " (select concat(u1.first_name, ' ', u1.last_name) from user_data u1 where jr.hiring_mgr_id=u1.user_id) hiring_mgr_name ,";
      }
      if (columnsList.contains("orgName")) {
        strcontents = strcontents + " (select o.org_name from organization o where jr.org_id=o.org_id) org_name,";
      }
      if (columnsList.contains("department")) {
        strcontents = strcontents + " (select d.department_name from department d where jr.department_id=d.department_id) dept_name,";
      }
      if (columnsList.contains("projectcode")) {
        strcontents = strcontents + " (select pj.proj_code from project_codes pj where jr.project_id=pj.proj_id) proj_code,";
      }
    }
    if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("APPLICANT_SUMMARY_SCREEN")))
    {
      String strtemp = Constant.getValue("applicant.page.applicant.summary.default.columns");
      
      String[] columns = StringUtils.tokenize(strtemp, ",");
      List columnsList = new ArrayList();
      if (columns != null) {
        Collections.addAll(columnsList, columns);
      }
      if (columnsList.contains("hiringManager")) {
        strcontents = strcontents + " (select concat(u1.first_name, ' ', u1.last_name) from user_data u1 where jr.hiring_mgr_id=u1.user_id) hiring_mgr_name ,";
      }
      if (columnsList.contains("orgName")) {
        strcontents = strcontents + " (select o.org_name from organization o where jr.org_id=o.org_id) org_name,";
      }
      if (columnsList.contains("department")) {
        strcontents = strcontents + " (select d.department_name from department d where jr.department_id=d.department_id) dept_name,";
      }
      if (columnsList.contains("projectcode")) {
        strcontents = strcontents + " (select pj.proj_code from project_codes pj where jr.project_id=pj.proj_id) proj_code,";
      }
    }
    if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("APP_SEARCH_SCREEN_RECRUITER")))
    {
      String strtemp = Constant.getValue("applicant.page.recruiter.default.columns");
      
      String[] columns = StringUtils.tokenize(strtemp, ",");
      
      List columnsList = new ArrayList();
      if (columns != null) {
        Collections.addAll(columnsList, columns);
      }
      if (columnsList.contains("hiringManager")) {
        strcontents = strcontents + " (select concat(u1.first_name, ' ', u1.last_name) from user_data u1 where jr.hiring_mgr_id=u1.user_id) hiring_mgr_name ,";
      }
      if (columnsList.contains("orgName")) {
        strcontents = strcontents + " (select o.org_name from organization o where jr.org_id=o.org_id) org_name,";
      }
      if (columnsList.contains("department")) {
        strcontents = strcontents + " (select d.department_name from department d where jr.department_id=d.department_id) dept_name,";
      }
      if (columnsList.contains("projectcode")) {
        strcontents = strcontents + " (select pj.proj_code from project_codes pj where jr.project_id=pj.proj_id) proj_code,";
      }
    }
    if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("ALL_APP_SEARCH_SCREEN")))
    {
      String strtemp = Constant.getValue("applicant.page.all.default.columns");
      
      String[] columns = StringUtils.tokenize(strtemp, ",");
      
      List columnsList = new ArrayList();
      if (columns != null) {
        Collections.addAll(columnsList, columns);
      }
      if (columnsList.contains("hiringManager")) {
        strcontents = strcontents + " (select concat(u1.first_name, ' ', u1.last_name) from user_data u1 where jr.hiring_mgr_id=u1.user_id) hiring_mgr_name ,";
      }
      if (columnsList.contains("orgName")) {
        strcontents = strcontents + " (select o.org_name from organization o where jr.org_id=o.org_id) org_name,";
      }
      if (columnsList.contains("department")) {
        strcontents = strcontents + " (select d.department_name from department d where jr.department_id=d.department_id) dept_name,";
      }
      if (columnsList.contains("projectcode")) {
        strcontents = strcontents + " (select pj.proj_code from project_codes pj where jr.project_id=pj.proj_id) proj_code,";
      }
    }
    if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("ON_BOARDING_SEARCH_SCREEN")))
    {
      String strtemp = Constant.getValue("onboarding.page.default.columns");
      
      String[] columns = StringUtils.tokenize(strtemp, ",");
      
      List columnsList = new ArrayList();
      if (columns != null) {
        Collections.addAll(columnsList, columns);
      }
      if (columnsList.contains("hiringManager")) {
        strcontents = strcontents + " (select concat(u1.first_name, ' ', u1.last_name) from user_data u1 where jr.hiring_mgr_id=u1.user_id) hiring_mgr_name ,";
      }
      if (columnsList.contains("orgName")) {
        strcontents = strcontents + " (select o.org_name from organization o where jr.org_id=o.org_id) org_name,";
      }
      if (columnsList.contains("department")) {
        strcontents = strcontents + " (select d.department_name from department d where jr.department_id=d.department_id) dept_name,";
      }
      if (columnsList.contains("projectcode")) {
        strcontents = strcontents + " (select pj.proj_code from project_codes pj where jr.project_id=pj.proj_id) proj_code,";
      }
    }
    if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("DASHBOARD_RECRUITER_IN_OFFER_PROCESS")))
    {
      String strtemp = Constant.getValue("dashboard.recruiter.applicants.offer.process");
      
      String[] columns = StringUtils.tokenize(strtemp, ",");
      
      List columnsList = new ArrayList();
      if (columns != null) {
        Collections.addAll(columnsList, columns);
      }
      if (columnsList.contains("hiringManager")) {
        strcontents = strcontents + " (select concat(u1.first_name, ' ', u1.last_name) from user_data u1 where jr.hiring_mgr_id=u1.user_id) hiring_mgr_name ,";
      }
      if (columnsList.contains("orgName")) {
        strcontents = strcontents + " (select o.org_name from organization o where jr.org_id=o.org_id) org_name,";
      }
      if (columnsList.contains("department")) {
        strcontents = strcontents + " (select d.department_name from department d where jr.department_id=d.department_id) dept_name,";
      }
      if (columnsList.contains("projectcode")) {
        strcontents = strcontents + " (select pj.proj_code from project_codes pj where jr.project_id=pj.proj_id) proj_code,";
      }
    }
    if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("DASHBOARD_RECRUITER_IN_JOINED_PROCESS")))
    {
      String strtemp = Constant.getValue("dashboard.recruiter.applicants.joining.process");
      
      String[] columns = StringUtils.tokenize(strtemp, ",");
      
      List columnsList = new ArrayList();
      if (columns != null) {
        Collections.addAll(columnsList, columns);
      }
      if (columnsList.contains("hiringManager")) {
        strcontents = strcontents + " (select concat(u1.first_name, ' ', u1.last_name) from user_data u1 where jr.hiring_mgr_id=u1.user_id) hiring_mgr_name ,";
      }
      if (columnsList.contains("orgName")) {
        strcontents = strcontents + " (select o.org_name from organization o where jr.org_id=o.org_id) org_name,";
      }
      if (columnsList.contains("department")) {
        strcontents = strcontents + " (select d.department_name from department d where jr.department_id=d.department_id) dept_name,";
      }
      if (columnsList.contains("projectcode")) {
        strcontents = strcontents + " (select pj.proj_code from project_codes pj where jr.project_id=pj.proj_id) proj_code,";
      }
    }
    strcontents = strcontents.substring(0, strcontents.length() - 1);
    logger.info(strcontents);
    return strcontents;
  }
  
  private JobApplicant setApplicantListValues(Object[] obj, String screenName, JobApplicant jobapp, int objectid)
  {
    Class applicantClass = jobapp.getClass();
    String strtemp = "";
    if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("APP_SEARCH_SCREEN_HIRING_MGR"))) {
      strtemp = Constant.getValue("applicant.page.hiring.mgr.default.columns");
    }
    if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("APPLICANT_SUMMARY_SCREEN"))) {
      strtemp = Constant.getValue("applicant.page.applicant.summary.default.columns");
    }
    if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("APP_SEARCH_SCREEN_RECRUITER"))) {
      strtemp = Constant.getValue("applicant.page.recruiter.default.columns");
    }
    if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("ALL_APP_SEARCH_SCREEN"))) {
      strtemp = Constant.getValue("applicant.page.all.default.columns");
    }
    if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("ON_BOARDING_SEARCH_SCREEN"))) {
      strtemp = Constant.getValue("onboarding.page.default.columns");
    }
    if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("DASHBOARD_RECRUITER_IN_OFFER_PROCESS"))) {
      strtemp = Constant.getValue("dashboard.recruiter.applicants.offer.process");
    }
    if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("DASHBOARD_RECRUITER_IN_JOINED_PROCESS"))) {
      strtemp = Constant.getValue("dashboard.recruiter.applicants.joining.process");
    }
    strtemp = strtemp + ",offerownerId,offerownerName,isofferownerGroup,";
    String[] columns = StringUtils.tokenize(strtemp, ",");
    int objseq = objectid;
    for (int i = 0; i < columns.length; i++)
    {
      logger.info(" columns >>>>> " + columns[i]);
      try
      {
        if (!StringUtils.isNullOrEmpty((String)Constant.applicantTableBeanColumnNameMap.get(columns[i])))
        {
          String type = (String)Constant.applicantAttributeTypeMap.get(columns[i]);
          
          Field field = applicantClass.getField(columns[i]);
          if (type.equals("java.util.Date"))
          {
            if (obj[objseq] != null) {
              field.set(jobapp, (java.util.Date)obj[objseq]);
            }
          }
          else if (type.equals("java.lang.String"))
          {
            if (obj[objseq] != null) {
              field.set(jobapp, (String)obj[objseq]);
            }
          }
          else if (type.equals("long"))
          {
            if (obj[objseq] != null)
            {
              BigInteger tempbig = (BigInteger)obj[objseq];
              if (tempbig != null) {
                field.set(jobapp, Long.valueOf(tempbig.longValue()));
              }
            }
          }
          else if (type.equals("int"))
          {
            if (obj[objseq] != null)
            {
              Integer tempint = (Integer)obj[objseq];
              if (tempint != null) {
                field.set(jobapp, Long.valueOf(tempint.longValue()));
              }
            }
          }
          else if (type.equals("double")) {
            if (obj[objseq] != null)
            {
              Double tempint = (Double)obj[objseq];
              if (tempint != null) {
                field.set(jobapp, Double.valueOf(tempint.doubleValue()));
              }
            }
          }
          objseq++;
        }
      }
      catch (Exception e)
      {
        objseq++;
        
        logger.info(e.getMessage());
      }
    }
    return jobapp;
  }
  
  public List searchOwnRefferalApplicantsForPagination(RefferalEmployee emp, String fullname, String reqid, String prevorg, String email, String appdate, String cri, String applicantNo, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside searchOwnRefferalApplicantsForPagination method");
    List applicantList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = session.createCriteria(JobApplicant.class);
      
      outer.add(Restrictions.disjunction().add(Restrictions.eq("employeecode", emp.getEmployeecode())).add(Restrictions.eq("referrerEmail", emp.getEmployeeemail())));
      if ((!StringUtils.isNullOrEmpty(fullname)) && (!fullname.equals("null"))) {
        outer.add(Restrictions.like("fullName", "%" + fullname + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(applicantNo)) && (!applicantNo.equals("0")) && (!applicantNo.equals("null"))) {
        outer.add(Restrictions.eq("applicantId", new Long(applicantNo)));
      }
      if ((!StringUtils.isNullOrEmpty(reqid)) && (!reqid.equals("0")) && (!reqid.equals("null"))) {
        outer.add(Restrictions.eq("reqId", new Long(reqid)));
      }
      if ((!StringUtils.isNullOrEmpty(prevorg)) && (!prevorg.equals("null"))) {
        outer.add(Restrictions.like("previousOrganization", "%" + prevorg + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(email)) && (!email.equals("null"))) {
        outer.add(Restrictions.like("email", "%" + email + "%"));
      }
      logger.info("appdate" + appdate);
      if ((!StringUtils.isNullOrEmpty(appdate)) && (!appdate.equals("null")))
      {
        String datepattern = Constant.getValue("defaultdateformat");
        
        DateFormat format = new SimpleDateFormat(datepattern + " hh:mm:ss");
        
        java.util.Date aDate = format.parse(appdate + " 00:00:00");
        java.util.Date bDate = format.parse(appdate + " 23:59:00");
        if (cri.equals("on")) {
          outer.add(Expression.between("appliedDate", new java.util.Date(aDate.getTime()), new java.util.Date(bDate.getTime())));
        }
        if (cri.equals("after")) {
          outer.add(Expression.gt("appliedDate", new java.util.Date(aDate.getTime())));
        }
        if (cri.equals("before")) {
          outer.add(Expression.lt("appliedDate", new java.util.Date(aDate.getTime())));
        }
      }
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      applicantList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on searchOwnRefferalApplicantsForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return applicantList;
  }
  
  public List searchOwnAgencyApplicantsForPagination(User agency, String fullname, String reqid, String prevorg, String email, String appdate, String cri, String applicantNo, String interviewState, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside searchOwnAgencyApplicantsForPagination method");
    logger.info("interviewState" + interviewState);
    List applicantList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = session.createCriteria(JobApplicant.class);
      
      outer.add(Restrictions.eq("resumesourcetype.resumeSourceTypeId", Integer.valueOf(5)));
      outer.add(Restrictions.disjunction().add(Restrictions.eq("resumeSourcesId", Long.valueOf(agency.getUserId()))).add(Restrictions.eq("vendorId", Long.valueOf(agency.getUserId()))));
      if ((!StringUtils.isNullOrEmpty(fullname)) && (!fullname.equals("null"))) {
        outer.add(Restrictions.like("fullName", "%" + fullname + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(applicantNo)) && (!applicantNo.equals("0")) && (!applicantNo.equals("null"))) {
        outer.add(Restrictions.eq("applicantId", new Long(applicantNo)));
      }
      if ((!StringUtils.isNullOrEmpty(reqid)) && (!reqid.equals("0")) && (!reqid.equals("null"))) {
        outer.add(Restrictions.eq("reqId", new Long(reqid)));
      }
      if ((!StringUtils.isNullOrEmpty(prevorg)) && (!prevorg.equals("null"))) {
        outer.add(Restrictions.like("previousOrganization", "%" + prevorg + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(interviewState)) && (!interviewState.equals("NoValue")) && (!interviewState.equals("null"))) {
        if (interviewState.equalsIgnoreCase("Offer Accepted")) {
          outer.add(Restrictions.eq("interviewState", interviewState));
        } else if (interviewState.equalsIgnoreCase("Application Submitted")) {
          outer.add(Restrictions.eq("interviewState", interviewState));
        } else {
          outer.add(Restrictions.like("interviewState", "%" + interviewState + "%"));
        }
      }
      if ((!StringUtils.isNullOrEmpty(email)) && (!email.equals("null"))) {
        outer.add(Restrictions.like("email", "%" + email + "%"));
      }
      logger.info("appdate" + appdate);
      if ((!StringUtils.isNullOrEmpty(appdate)) && (!appdate.equals("null")))
      {
        String datepattern = Constant.getValue("defaultdateformat");
        
        DateFormat format = new SimpleDateFormat(datepattern + " hh:mm:ss");
        
        java.util.Date aDate = format.parse(appdate + " 00:00:00");
        java.util.Date bDate = format.parse(appdate + " 23:59:00");
        if (cri.equals("on")) {
          outer.add(Expression.between("appliedDate", new java.util.Date(aDate.getTime()), new java.util.Date(bDate.getTime())));
        }
        if (cri.equals("after")) {
          outer.add(Expression.gt("appliedDate", new java.util.Date(aDate.getTime())));
        }
        if (cri.equals("before")) {
          outer.add(Expression.lt("appliedDate", new java.util.Date(aDate.getTime())));
        }
      }
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      applicantList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on searchOwnAgencyApplicantsForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return applicantList;
  }
  
  public List exportApplicants(User user, String cri, String targetjoiningdate, String interviewstate, long reqId)
  {
    logger.info("Inside exportApplicants method");
    List applicantList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      




      Criteria outer = session.createCriteria(JobApplicant.class).add(Restrictions.eq("reqId", Long.valueOf(reqId)));
      if ((!StringUtils.isNullOrEmpty(interviewstate)) && (!interviewstate.equals("NoValue"))) {
        outer.add(Restrictions.like("interviewState", "%" + interviewstate + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(targetjoiningdate)) && (!targetjoiningdate.equals("null")))
      {
        String datepattern = DateUtil.getDatePatternFormat(user.getLocale());
        

        DateFormat format = new SimpleDateFormat(datepattern + " hh:mm:ss");
        
        java.util.Date aDate = format.parse(targetjoiningdate + " 00:00:00");
        
        java.util.Date bDate = format.parse(targetjoiningdate + " 23:59:00");
        if (cri.equals("on")) {
          outer.add(Expression.between("targetjoiningdate", new java.util.Date(aDate.getTime()), new java.util.Date(bDate.getTime())));
        }
        if (cri.equals("after")) {
          outer.add(Expression.gt("targetjoiningdate", new java.util.Date(aDate.getTime())));
        }
        if (cri.equals("before")) {
          outer.add(Expression.lt("targetjoiningdate", new java.util.Date(aDate.getTime())));
        }
      }
      applicantList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantsForPaginationByUser()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return applicantList;
  }
  
  public List exportApplicantsSearchHiringMgr(User user, ApplicantSearchCriteria criteria)
  {
    logger.info("Inside exportApplicantsSearchHiringMgr method");
    List applicantList = new ArrayList();
    List newapplicantList = new ArrayList();
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String sqlgen = getSQLApplicants("APP_SEARCH_SCREEN_HIRING_MGR");
      String sql = "select ja.application_id,u.first_name, u.last_name,ja.uuid,ja.jb_req_id,ja.offerownerId,ja.full_name,jr.jb_req_code,jr.recruiter_name," + sqlgen + " from job_applications ja , job_requisition jr , user_data u " + " where ja.jb_req_id = jr.jb_req_id " + " and jr.hiring_mgr_id = :hiring_mgr_id ";
      




      String orgsqlgen = getOtherSQLApplicants("APP_SEARCH_SCREEN_HIRING_MGR");
      if (!StringUtils.isNullOrEmpty(orgsqlgen)) {
        sql = "select ja.application_id,u.first_name, u.last_name,ja.uuid,ja.jb_req_id,ja.offerownerId,ja.full_name,jr.jb_req_code,jr.recruiter_name," + orgsqlgen + "," + sqlgen + " from job_applications ja , job_requisition jr , user_data u " + " where ja.jb_req_id = jr.jb_req_id " + " and jr.hiring_mgr_id = :hiring_mgr_id ";
      }
      String sql1 = buildSQLQueryforSearch(user, criteria);
      sql = sql + sql1;
      
      logger.info("sql" + sql);
      Query query = session.createSQLQuery(sql);
      query.setLong("hiring_mgr_id", user.getUserId());
      
      applicantList = query.list();
      
      logger.info("applicantList.size()" + applicantList.size());
      for (int i = 0; i < applicantList.size(); i++)
      {
        Object[] obj = (Object[])applicantList.get(i);
        JobApplicant jobapp = new JobApplicant();
        
        BigInteger appId = (BigInteger)obj[0];
        long applicationId = appId.longValue();
        jobapp.setApplicantId(applicationId);
        jobapp.ownername = ((String)obj[1] + " " + (String)obj[2]);
        jobapp.setUuid((String)obj[3]);
        BigInteger reqId = (BigInteger)obj[4];
        long requistionId = reqId.longValue();
        jobapp.setReqId(requistionId);
        BigInteger offowId = (BigInteger)obj[5];
        long offerownerid = offowId.longValue();
        jobapp.setOfferownerId(offerownerid);
        jobapp.setFullName((String)obj[6]);
        jobapp.setJobCode((String)obj[7]);
        jobapp.setRecruiter((String)obj[8]);
        int basevalue = 9;
        if (!StringUtils.isNullOrEmpty(orgsqlgen))
        {
          if (isColumnPresent("APP_SEARCH_SCREEN_HIRING_MGR", "hiringManager"))
          {
            jobapp.setHiringManager((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("APP_SEARCH_SCREEN_HIRING_MGR", "orgName"))
          {
            jobapp.setOrgName((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("APP_SEARCH_SCREEN_HIRING_MGR", "department"))
          {
            jobapp.setDepartment((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("APP_SEARCH_SCREEN_HIRING_MGR", "projectcode"))
          {
            jobapp.setProjectcode((String)obj[basevalue]);
            basevalue += 1;
          }
        }
        jobapp = setApplicantListValues(obj, "APP_SEARCH_SCREEN_HIRING_MGR", jobapp, basevalue);
        
        newapplicantList.add(jobapp);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on exportApplicantsSearchHiringMgr()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return newapplicantList;
  }
  
  public List exportApplicantsSearchRecruiter(User user, ApplicantSearchCriteria criteria)
  {
    logger.info("Inside exportApplicantsSearchRecruiter method");
    List applicantList = new ArrayList();
    List newapplicantList = new ArrayList();
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String sqlgen = getSQLApplicants("APP_SEARCH_SCREEN_RECRUITER");
      String sql = "select ja.application_id,u.first_name, u.last_name,ja.uuid,ja.jb_req_id,ja.offerownerId,ja.full_name,jr.jb_req_code,jr.recruiter_name," + sqlgen + " from job_applications ja , job_requisition jr , user_data u " + " where ja.jb_req_id = jr.jb_req_id " + " and  (jr.recruiter_id = :recruiter_id or ja.offerownerid= :offerownerid ) ";
      




      String orgsqlgen = getOtherSQLApplicants("APP_SEARCH_SCREEN_RECRUITER");
      if (!StringUtils.isNullOrEmpty(orgsqlgen)) {
        sql = "select ja.application_id,u.first_name, u.last_name,ja.uuid,ja.jb_req_id,ja.offerownerId,ja.full_name,jr.jb_req_code,jr.recruiter_name," + orgsqlgen + "," + sqlgen + " from job_applications ja , job_requisition jr , user_data u " + " where ja.jb_req_id = jr.jb_req_id " + " and  (jr.recruiter_id = :recruiter_id or ja.offerownerid= :offerownerid ) ";
      }
      String sql1 = buildSQLQueryforSearch(user, criteria);
      sql = sql + sql1;
      
      logger.info("sql" + sql);
      Query query = session.createSQLQuery(sql);
      query.setLong("recruiter_id", user.getUserId());
      query.setLong("offerownerid", user.getUserId());
      
      applicantList = query.list();
      
      logger.info("applicantList.size()" + applicantList.size());
      for (int i = 0; i < applicantList.size(); i++)
      {
        Object[] obj = (Object[])applicantList.get(i);
        JobApplicant jobapp = new JobApplicant();
        
        BigInteger appId = (BigInteger)obj[0];
        long applicationId = appId.longValue();
        jobapp.setApplicantId(applicationId);
        jobapp.ownername = ((String)obj[1] + " " + (String)obj[2]);
        jobapp.setUuid((String)obj[3]);
        BigInteger reqId = (BigInteger)obj[4];
        long requistionId = reqId.longValue();
        jobapp.setReqId(requistionId);
        BigInteger offowId = (BigInteger)obj[5];
        long offerownerid = offowId.longValue();
        jobapp.setOfferownerId(offerownerid);
        jobapp.setFullName((String)obj[6]);
        jobapp.setJobCode((String)obj[7]);
        jobapp.setRecruiter((String)obj[8]);
        int basevalue = 9;
        if (!StringUtils.isNullOrEmpty(orgsqlgen))
        {
          if (isColumnPresent("APP_SEARCH_SCREEN_RECRUITER", "hiringManager"))
          {
            jobapp.setHiringManager((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("APP_SEARCH_SCREEN_RECRUITER", "orgName"))
          {
            jobapp.setOrgName((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("APP_SEARCH_SCREEN_RECRUITER", "department"))
          {
            jobapp.setDepartment((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("APP_SEARCH_SCREEN_RECRUITER", "projectcode"))
          {
            jobapp.setProjectcode((String)obj[basevalue]);
            basevalue += 1;
          }
        }
        jobapp = setApplicantListValues(obj, "APP_SEARCH_SCREEN_RECRUITER", jobapp, basevalue);
        
        newapplicantList.add(jobapp);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on exportApplicantsSearchRecruiter()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return newapplicantList;
  }
  
  public List exportApplicantsSearchAllSearch(User user, ApplicantSearchCriteria criteria)
  {
    logger.info("Inside exportApplicantsSearchAllSearch method");
    
    String sessionId = "";
    List applicantList = new ArrayList();
    List newapplicantList = new ArrayList();
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String indexsaerchtable = "";
      if ((!StringUtils.isNullOrEmpty(criteria.getKeywords())) && (!criteria.getKeywords().equals("null"))) {
        indexsaerchtable = " , index_applicant_search idx ";
      }
      String sqlgen = getSQLApplicants("ALL_APP_SEARCH_SCREEN");
      String sqlcount = "select count(ja.application_id) as count from job_applications ja , job_requisition jr, applicants_other_details jao " + indexsaerchtable + " where ja.jb_req_id = jr.jb_req_id and  jao.application_id=ja.application_id ";
      
      String sql = " select ja.application_id, (select concat(u.first_name,' ', u.last_name) from user_data u where u.user_id=ja.owner) ownername,ja.owner,ja.uuid,ja.jb_req_id,ja.offerownerId,ja.full_name,jr.jb_req_code,jr.recruiter_name,(select ug.user_group_name from user_group ug where ug.user_group_id=ja.owner_group) user_group_name,ja.is_group,ja.owner_group,jr.recruiter_id,jr.recruiter_name,jr.is_recruiter_group, (select resume_source_type_name from resume_source_types rst where rst.resume_source_type_id=ja.resume_source_type_id) sourceTypeName,  (select resume_source_name from resume_sources rs where rs.resume_sources_id=ja.resume_source_id) sourceName,  (select u.first_name from user_data u where u.user_id=ja.vendor_id) agencyName ,ja.applicant_number, (select del.declined_reason_name from declined_reason_lov del where del.offer_declined_reason_id=ja.offer_declined_reason_id) offer_declined_reason_name , (select del.declined_reason_name from declined_reason_lov del where del.offer_declined_reason_id=ja.offer_cancelled_reason_id) offer_cancel_reason_name , (select del.declined_reason_name from declined_reason_lov del where del.offer_declined_reason_id=ja.rejectionreasonId) rejection_reason_name , " + sqlgen + " from job_applications ja , job_requisition jr, applicants_other_details jao " + indexsaerchtable + " where ja.jb_req_id = jr.jb_req_id and  jao.application_id=ja.application_id ";
      












      String orgsqlgen = getOtherSQLApplicants("ALL_APP_SEARCH_SCREEN");
      if (!StringUtils.isNullOrEmpty(orgsqlgen))
      {
        sql = " select ja.application_id, (select concat(u.first_name,' ', u.last_name) from user_data u where u.user_id=ja.owner) ownername,ja.owner,ja.uuid,ja.jb_req_id,ja.offerownerId,ja.full_name,jr.jb_req_code,jr.recruiter_name,(select ug.user_group_name from user_group ug where ug.user_group_id=ja.owner_group) user_group_name,ja.is_group,ja.owner_group, jr.recruiter_id,jr.recruiter_name,jr.is_recruiter_group, (select resume_source_type_name from resume_source_types rst where rst.resume_source_type_id=ja.resume_source_type_id) sourceTypeName,  (select resume_source_name from resume_sources rs where rs.resume_sources_id=ja.resume_source_id) sourceName,  (select u.first_name from user_data u where u.user_id=ja.vendor_id) agencyName ,ja.applicant_number, (select del.declined_reason_name from declined_reason_lov del where del.offer_declined_reason_id=ja.offer_declined_reason_id) offer_declined_reason_name , (select del.declined_reason_name from declined_reason_lov del where del.offer_declined_reason_id=ja.offer_cancelled_reason_id) offer_cancel_reason_name , (select del.declined_reason_name from declined_reason_lov del where del.offer_declined_reason_id=ja.rejectionreasonId) rejection_reason_name , " + orgsqlgen + "," + sqlgen + " from job_applications ja , job_requisition jr , applicants_other_details jao " + indexsaerchtable + " where ja.jb_req_id = jr.jb_req_id and  jao.application_id=ja.application_id";
        














        sqlcount = "select count(ja.application_id) as count from job_applications ja , job_requisition jr, applicants_other_details jao " + indexsaerchtable + " where ja.jb_req_id = jr.jb_req_id  and  jao.application_id=ja.application_id ";
      }
      criteria.setScreenName("ALL_APP_SEARCH_SCREEN");
      String sql1 = QueryBuilderApplicant.buildSQLQueryforSearch(user, criteria);
      logger.info("query from query builder" + sql1);
      sqlcount = sqlcount + sql1;
      

      sql = sql + sql1;
      logger.info("criteria.getKeywords()" + criteria.getKeywords());
      if ((!StringUtils.isNullOrEmpty(criteria.getKeywords())) && (!criteria.getKeywords().equals("null")))
      {
        IndexSearch idxsearch = new IndexSearch();
        List appids = idxsearch.search(criteria.getKeywords());
        if ((appids != null) && (appids.size() > 0))
        {
          sessionId = UUID.randomUUID().toString();
          addToIndexSearch(appids, sessionId);
          

          sql = sql + " and ja.application_id = idx.applicant_id and idx.session_id = " + "'" + sessionId + "'" + "  order by idx.sequence ";
          sqlcount = sqlcount + " and ja.application_id = idx.applicant_id and idx.session_id = " + "'" + sessionId + "'";
        }
      }
      sql = sql + " and ja.super_user_key=" + user.getSuper_user_key() + " ";
      sqlcount = sqlcount + " and ja.super_user_key=" + user.getSuper_user_key() + " ";
      logger.info("final build sql" + sql);
      Query query = session.createSQLQuery(sql);
      

      applicantList = query.list();
      
      logger.info("applicantList.size()" + applicantList.size());
      for (int i = 0; i < applicantList.size(); i++)
      {
        Object[] obj = (Object[])applicantList.get(i);
        JobApplicant jobapp = new JobApplicant();
        
        BigInteger appId = (BigInteger)obj[0];
        long applicationId = appId.longValue();
        jobapp.setApplicantId(applicationId);
        jobapp.ownername = ((String)obj[1]);
        BigInteger ownerId = (BigInteger)obj[2];
        if (ownerId != null) {
          jobapp.ownerId = ownerId.longValue();
        }
        jobapp.setUuid((String)obj[3]);
        BigInteger reqId = (BigInteger)obj[4];
        long requistionId = reqId.longValue();
        jobapp.setReqId(requistionId);
        BigInteger offowId = (BigInteger)obj[5];
        long offerownerid = offowId.longValue();
        jobapp.setOfferownerId(offerownerid);
        jobapp.setFullName((String)obj[6]);
        jobapp.setJobCode((String)obj[7]);
        jobapp.setRecruiter((String)obj[8]);
        jobapp.ownernamegroup = ((String)obj[9]);
        jobapp.isGroup = ((String)obj[10]);
        BigInteger ownergroupId = (BigInteger)obj[11];
        if (ownergroupId != null) {
          jobapp.ownergroupId = ownergroupId.longValue();
        }
        BigInteger recruiterId = (BigInteger)obj[12];
        if (recruiterId != null) {
          jobapp.recruiterId = recruiterId.longValue();
        }
        jobapp.setRecruiter((String)obj[13]);
        jobapp.recruitergroup = ((String)obj[14]);
        
        String sourcetypename = (String)obj[15];
        sourcetypename = StringUtils.isNullOrEmpty(sourcetypename) ? "" : sourcetypename;
        jobapp.setSourceTypeName(sourcetypename);
        
        String sourcename = (String)obj[16];
        String agencyname = (String)obj[17];
        if (!StringUtils.isNullOrEmpty(agencyname)) {
          sourcename = agencyname;
        }
        sourcename = StringUtils.isNullOrEmpty(sourcename) ? "" : sourcename;
        jobapp.setSourceName(sourcename);
        
        BigInteger appno = (BigInteger)obj[18];
        long applicationno = appno.longValue();
        jobapp.setApplicant_number(applicationno);
        
        String offerdeclinedreasonname = (String)obj[19];
        if (!StringUtils.isNullOrEmpty(offerdeclinedreasonname)) {
          jobapp.setOfferDeclinedResonName(offerdeclinedreasonname);
        } else {
          jobapp.setOfferDeclinedResonName("");
        }
        String offercancelreasonname = (String)obj[20];
        if (!StringUtils.isNullOrEmpty(offercancelreasonname)) {
          jobapp.setOfferCancelResonName(offercancelreasonname);
        } else {
          jobapp.setOfferCancelResonName("");
        }
        String rejectionreasonname = (String)obj[21];
        if (!StringUtils.isNullOrEmpty(rejectionreasonname)) {
          jobapp.setRejectionreasonname(rejectionreasonname);
        } else {
          jobapp.setRejectionreasonname("");
        }
        int basevalue = 22;
        if (!StringUtils.isNullOrEmpty(orgsqlgen))
        {
          if (isColumnPresent("ALL_APP_SEARCH_SCREEN", "hiringManager"))
          {
            jobapp.setHiringManager((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("ALL_APP_SEARCH_SCREEN", "orgName"))
          {
            jobapp.setOrgName((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("ALL_APP_SEARCH_SCREEN", "department"))
          {
            jobapp.setDepartment((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("ALL_APP_SEARCH_SCREEN", "projectcode"))
          {
            jobapp.setProjectcode((String)obj[basevalue]);
            basevalue += 1;
          }
        }
        jobapp = setApplicantListValues(obj, "ALL_APP_SEARCH_SCREEN", jobapp, basevalue);
        

        newapplicantList.add(jobapp);
      }
      logger.info("sqlcount" + sqlcount);
      

      deleteIndexSearch(sessionId);
    }
    catch (Exception e)
    {
      logger.error("Exception on exportApplicantsSearchAllSearch()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return newapplicantList;
  }
  
  public List exportOnBoardApplicantsSearch(User user, ApplicantSearchCriteria criteria)
  {
    logger.info("Inside exportOnBoardApplicantsSearch method");
    List applicantList = new ArrayList();
    List newapplicantList = new ArrayList();
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String sqlgen = getSQLApplicants("ON_BOARDING_SEARCH_SCREEN");
      
      String sql = "select ja.application_id,u.first_name, u.last_name,ja.uuid,ja.jb_req_id,ja.offerownerId,ja.full_name,jr.jb_req_code,jr.recruiter_name," + sqlgen + " from job_applications ja , job_requisition jr , user_data u " + " where ja.jb_req_id = jr.jb_req_id ";
      



      String orgsqlgen = getOtherSQLApplicants("ON_BOARDING_SEARCH_SCREEN");
      if (!StringUtils.isNullOrEmpty(orgsqlgen)) {
        sql = "select ja.application_id,u.first_name, u.last_name,ja.uuid,ja.jb_req_id,ja.offerownerId,ja.full_name,jr.jb_req_code,jr.recruiter_name," + orgsqlgen + "," + sqlgen + " from job_applications ja , job_requisition jr , user_data u " + " where ja.jb_req_id = jr.jb_req_id ";
      }
      String sql1 = buildSQLQueryforSearch(user, criteria);
      sql = sql + sql1;
      
      logger.info("sql" + sql);
      Query query = session.createSQLQuery(sql);
      

      applicantList = query.list();
      
      logger.info("applicantList.size()" + applicantList.size());
      for (int i = 0; i < applicantList.size(); i++)
      {
        Object[] obj = (Object[])applicantList.get(i);
        JobApplicant jobapp = new JobApplicant();
        
        BigInteger appId = (BigInteger)obj[0];
        long applicationId = appId.longValue();
        jobapp.setApplicantId(applicationId);
        jobapp.ownername = ((String)obj[1] + " " + (String)obj[2]);
        jobapp.setUuid((String)obj[3]);
        BigInteger reqId = (BigInteger)obj[4];
        long requistionId = reqId.longValue();
        jobapp.setReqId(requistionId);
        BigInteger offowId = (BigInteger)obj[5];
        long offerownerid = offowId.longValue();
        jobapp.setOfferownerId(offerownerid);
        jobapp.setFullName((String)obj[6]);
        jobapp.setJobCode((String)obj[7]);
        jobapp.setRecruiter((String)obj[8]);
        int basevalue = 9;
        if (!StringUtils.isNullOrEmpty(orgsqlgen))
        {
          if (isColumnPresent("ON_BOARDING_SEARCH_SCREEN", "hiringManager"))
          {
            jobapp.setHiringManager((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("ON_BOARDING_SEARCH_SCREEN", "orgName"))
          {
            jobapp.setOrgName((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("ON_BOARDING_SEARCH_SCREEN", "department"))
          {
            jobapp.setDepartment((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("ON_BOARDING_SEARCH_SCREEN", "projectcode"))
          {
            jobapp.setProjectcode((String)obj[basevalue]);
            basevalue += 1;
          }
        }
        jobapp = setApplicantListValues(obj, "ON_BOARDING_SEARCH_SCREEN", jobapp, basevalue);
        
        newapplicantList.add(jobapp);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on exportOnBoardApplicantsSearch()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return newapplicantList;
  }
  
  public List getApplicantsTrackingByStatusPaginationByUser(User user, String status, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getApplicantsTrackingByStatusPaginationByUser method");
    
    List applicantList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = session.createCriteria(JobApplicant.class);
      if ((!StringUtils.isNullOrEmpty(status)) && (status.equals("Application Submitted")))
      {
        outer.add(Restrictions.eq("createdBy", user.getUserName()));
        outer.add(Restrictions.eq("interviewState", status)).add(Restrictions.eq("status", "A"));
      }
      else if ((!StringUtils.isNullOrEmpty(status)) && (status.equals("Rejected")))
      {
        outer.add(Restrictions.like("interviewState", "%" + status + "%")).add(Restrictions.eq("status", "R"));
        



        outer.add(Restrictions.eq("interview_organizer_id", new Long(user.getUserId())));
      }
      else if ((!StringUtils.isNullOrEmpty(status)) && (status.equals("OnHold")))
      {
        outer.add(Restrictions.like("interviewState", "%" + status + "%")).add(Restrictions.eq("status", "H"));
        



        outer.add(Restrictions.eq("interview_organizer_id", new Long(user.getUserId())));
      }
      else if ((!StringUtils.isNullOrEmpty(status)) && (status.equals("Reference Check")))
      {
        outer.add(Restrictions.eq("isreferenceadded", Integer.valueOf(1)));
        outer.add(Restrictions.eq("interview_organizer_id", new Long(user.getUserId())));
      }
      else
      {
        outer.add(Restrictions.eq("interviewState", status)).add(Restrictions.eq("status", "A"));
        
        outer.add(Restrictions.eq("interview_organizer_id", new Long(user.getUserId())));
      }
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      applicantList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantsTrackingByStatusPaginationByUser()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return applicantList;
  }
  
  public List searchOwnApplicantsForPagination(User user, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside searchOwnApplicantsForPagination method");
    List applicantList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = session.createCriteria(JobApplicant.class).createAlias("owner", "owner");
      








      outer.add(Restrictions.disjunction().add(Restrictions.eq("owner.userId", new Long(user.getUserId()))).add(Restrictions.eq("createdBy", user.getUserName())));
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      applicantList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantsForPaginationByUser()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return applicantList;
  }
  
  public List searchOwnRefferalApplicantsForPagination(RefferalEmployee emp, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside searchOwnRefferalApplicantsForPagination method");
    List applicantList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = session.createCriteria(JobApplicant.class);
      
      outer.add(Restrictions.disjunction().add(Restrictions.eq("employeecode", emp.getEmployeecode())).add(Restrictions.eq("createdBy", emp.getEmployeeemail())).add(Restrictions.eq("referrerEmail", emp.getEmployeeemail())));
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      applicantList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on searchOwnRefferalApplicantsForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return applicantList;
  }
  
  public List getAgencyApplicantNames(String query, long agencyId)
  {
    logger.info("Inside getAgencyApplicantNames method");
    List applicantList = new ArrayList();
    List<String> newFullNameList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = session.createCriteria(JobApplicant.class);
      
      outer.add(Restrictions.eq("vendorId", Long.valueOf(agencyId)));
      
      outer.add(Restrictions.like("fullName", "%" + query + "%"));
      
      applicantList = outer.list();
      for (int i = 0; i < applicantList.size(); i++)
      {
        JobApplicant usr = (JobApplicant)applicantList.get(i);
        String fname = usr.getFullName();
        newFullNameList.add(fname);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getAgencyApplicantNames()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return newFullNameList;
  }
  
  public List getcurrentOrganizationNames(String query, long agencyId)
  {
    logger.info("Inside getcurrentOrganizationNames method");
    List orgList = new ArrayList();
    List<String> newOrgNameList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = session.createCriteria(JobApplicant.class);
      


      outer.add(Restrictions.like("previousOrganization", "%" + query + "%"));
      

      orgList = outer.list();
      for (int i = 0; i < orgList.size(); i++)
      {
        JobApplicant usr = (JobApplicant)orgList.get(i);
        String org = usr.getPreviousOrganization();
        newOrgNameList.add(org);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getcurrentOrganizationNames()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return newOrgNameList;
  }
  
  public List getReferralApplicantNames(RefferalEmployee emp, String query)
  {
    logger.info("Inside getReferralApplicantNames method");
    List applicantList = new ArrayList();
    List<String> newFullNameList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = session.createCriteria(JobApplicant.class);
      
      outer.add(Restrictions.disjunction().add(Restrictions.eq("employeecode", emp.getEmployeecode())).add(Restrictions.eq("createdBy", emp.getEmployeeemail())).add(Restrictions.eq("referrerEmail", emp.getEmployeeemail())));
      





      outer.add(Restrictions.like("fullName", "%" + query + "%"));
      
      applicantList = outer.list();
      for (int i = 0; i < applicantList.size(); i++)
      {
        JobApplicant usr = (JobApplicant)applicantList.get(i);
        String fname = usr.getFullName();
        newFullNameList.add(fname);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getReferralApplicantNames()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return newFullNameList;
  }
  
  public List searchOwnAgencyApplicantsForPagination(User agency, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside searchOwnAgencyApplicantsForPagination method");
    List applicantList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = session.createCriteria(JobApplicant.class);
      
      outer.add(Restrictions.eq("vendorId", Long.valueOf(agency.getUserId())));
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      applicantList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on searchOwnAgencyApplicantsForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return applicantList;
  }
  
  public void addToIndexSearch(List appids, String sessionid)
  {
    logger.info("Inside addToIndexSearch method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      for (int i = 0; i < appids.size(); i++)
      {
        String appid = (String)appids.get(i);
        
        IndexApplcantSearch se = new IndexApplcantSearch();
        se.setAppLicantId(new Long(appid).longValue());
        se.setSeq(i + 1);
        se.setSessionid(sessionid);
        session.save(se);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on searchApplicantsByVendorForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void deleteIndexSearch(String sessionId)
  {
    logger.info("Inside deleteIndexSearch method");
    int rowCount;
    if (!StringUtils.isNullOrEmpty(sessionId))
    {
      String hql = "delete from IndexApplcantSearch where sessionid = :sessionid";
      Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
      query.setString("sessionid", sessionId);
      
      rowCount = query.executeUpdate();
    }
  }
  
  public void deleteIndexApplicantScoringByApplicantId(long applicantId)
  {
    logger.info("Inside deleteIndexApplicantScoringByApplicantId method");
    
    String hql = "delete from ApplicantScoring where applicantId = :applicantId";
    Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
    query.setLong("applicantId", applicantId);
    
    int rowCount = query.executeUpdate();
  }
  
  public Map searchApplicantsByVendorForPagination(User user, ApplicantSearchCriteria criteria, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside searchApplicantsByVendorForPagination method");
    String sessionId = "";
    List applicantList = new ArrayList();
    List newapplicantList = new ArrayList();
    Map searchApplicantMap = new HashMap();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String indexsaerchtable = "";
      if ((!StringUtils.isNullOrEmpty(criteria.getKeywords())) && (!criteria.getKeywords().equals("null"))) {
        indexsaerchtable = " , index_applicant_search idx ";
      }
      String sqlgen = getSQLApplicants("ALL_APP_SEARCH_SCREEN");
      String sqlcount = "select count(ja.application_id) as count from job_applications ja , job_requisition jr, applicants_other_details jao " + indexsaerchtable + " where ja.jb_req_id = jr.jb_req_id and  jao.application_id=ja.application_id ";
      
      String sql = " select ja.application_id, (select concat(u.first_name,' ', u.last_name) from user_data u where u.user_id=ja.owner) ownername,ja.owner,ja.uuid,ja.jb_req_id,ja.offerownerId,ja.full_name,jr.jb_req_code,jr.recruiter_name,(select ug.user_group_name from user_group ug where ug.user_group_id=ja.owner_group) user_group_name,ja.is_group,ja.owner_group,jr.recruiter_id,jr.recruiter_name,jr.is_recruiter_group,jr.uuid as juuid, (select resume_source_type_name from resume_source_types rst where rst.resume_source_type_id=ja.resume_source_type_id) sourceTypeName,  (select resume_source_name from resume_sources rs where rs.resume_sources_id=ja.resume_source_id) sourceName,  (select u.first_name from user_data u where u.user_id=ja.vendor_id) agencyName , (select del.declined_reason_name from declined_reason_lov del where del.offer_declined_reason_id=ja.offer_declined_reason_id) offer_declined_reason_name , (select del.declined_reason_name from declined_reason_lov del where del.offer_declined_reason_id=ja.offer_cancelled_reason_id) offer_cancel_reason_name , (select del.declined_reason_name from declined_reason_lov del where del.offer_declined_reason_id=ja.rejectionreasonId) rejection_reason_name , " + sqlgen + " from job_applications ja , job_requisition jr, applicants_other_details jao " + indexsaerchtable + " where ja.jb_req_id = jr.jb_req_id and  jao.application_id=ja.application_id ";
      












      String orgsqlgen = getOtherSQLApplicants("ALL_APP_SEARCH_SCREEN");
      if (!StringUtils.isNullOrEmpty(orgsqlgen))
      {
        sql = " select ja.application_id, (select concat(u.first_name,' ', u.last_name) from user_data u where u.user_id=ja.owner) ownername,ja.owner,ja.uuid,ja.jb_req_id,ja.offerownerId,ja.full_name,jr.jb_req_code,jr.recruiter_name,(select ug.user_group_name from user_group ug where ug.user_group_id=ja.owner_group) user_group_name,ja.is_group,ja.owner_group, jr.recruiter_id,jr.recruiter_name,jr.is_recruiter_group,jr.uuid as juuid, (select resume_source_type_name from resume_source_types rst where rst.resume_source_type_id=ja.resume_source_type_id) sourceTypeName,  (select resume_source_name from resume_sources rs where rs.resume_sources_id=ja.resume_source_id) sourceName,  (select u.first_name from user_data u where u.user_id=ja.vendor_id) agencyName , (select del.declined_reason_name from declined_reason_lov del where del.offer_declined_reason_id=ja.offer_declined_reason_id) offer_declined_reason_name , (select del.declined_reason_name from declined_reason_lov del where del.offer_declined_reason_id=ja.offer_cancelled_reason_id) offer_cancel_reason_name , (select del.declined_reason_name from declined_reason_lov del where del.offer_declined_reason_id=ja.rejectionreasonId) rejection_reason_name , " + orgsqlgen + "," + sqlgen + " from job_applications ja , job_requisition jr , applicants_other_details jao " + indexsaerchtable + " where ja.jb_req_id = jr.jb_req_id and  jao.application_id=ja.application_id";
        














        sqlcount = "select count(ja.application_id) as count from job_applications ja , job_requisition jr, applicants_other_details jao " + indexsaerchtable + " where ja.jb_req_id = jr.jb_req_id  and  jao.application_id=ja.application_id ";
      }
      criteria.setScreenName("ALL_APP_SEARCH_SCREEN");
      String sql1 = QueryBuilderApplicant.buildSQLQueryforSearch(user, criteria);
      logger.info("query from query builder" + sql1);
      sqlcount = sqlcount + sql1;
      

      sql = sql + sql1;
      logger.info("criteria.getKeywords()" + criteria.getKeywords());
      if ((!StringUtils.isNullOrEmpty(criteria.getKeywords())) && (!criteria.getKeywords().equals("null")))
      {
        IndexSearch idxsearch = new IndexSearch();
        List appids = idxsearch.search(criteria.getKeywords());
        if ((appids != null) && (appids.size() > 0))
        {
          sessionId = UUID.randomUUID().toString();
          addToIndexSearch(appids, sessionId);
          

          sql = sql + " and ja.application_id = idx.applicant_id and idx.session_id = " + "'" + sessionId + "'" + "  order by idx.sequence ";
          sqlcount = sqlcount + " and ja.application_id = idx.applicant_id and idx.session_id = " + "'" + sessionId + "'";
        }
      }
      sql = sql + " and ja.super_user_key=" + user.getSuper_user_key() + " ";
      sqlcount = sqlcount + " and ja.super_user_key=" + user.getSuper_user_key() + " ";
      
      String tmpcolumnname = (String)Constant.applicantTableBeanColumnNameMap.get(sort_str);
      
      sort_str = "ja." + tmpcolumnname;
      if (tmpcolumnname == null) {
        sort_str = (String)Constant.notApplicantTableBeanColumnNameMap.get(sort_str);
      }
      if ((StringUtils.isNullOrEmpty(criteria.getKeywords())) || (criteria.getKeywords().equals("null"))) {
        if (dir_str.equalsIgnoreCase("asc")) {
          sql = sql + "order by " + sort_str + " ASC";
        } else {
          sql = sql + "order by " + sort_str + " DESC";
        }
      }
      logger.info("final build sql" + sql);
      Query query = session.createSQLQuery(sql);
      query.setFirstResult(startIndex);
      query.setMaxResults(pageSize);
      
      applicantList = query.list();
      
      logger.info("applicantList.size()" + applicantList.size());
      for (int i = 0; i < applicantList.size(); i++)
      {
        Object[] obj = (Object[])applicantList.get(i);
        JobApplicant jobapp = new JobApplicant();
        
        BigInteger appId = (BigInteger)obj[0];
        long applicationId = appId.longValue();
        jobapp.setApplicantId(applicationId);
        jobapp.ownername = ((String)obj[1]);
        BigInteger ownerId = (BigInteger)obj[2];
        if (ownerId != null) {
          jobapp.ownerId = ownerId.longValue();
        }
        jobapp.setUuid((String)obj[3]);
        BigInteger reqId = (BigInteger)obj[4];
        long requistionId = reqId.longValue();
        jobapp.setReqId(requistionId);
        BigInteger offowId = (BigInteger)obj[5];
        long offerownerid = offowId.longValue();
        jobapp.setOfferownerId(offerownerid);
        jobapp.setFullName((String)obj[6]);
        jobapp.setJobCode((String)obj[7]);
        jobapp.setRecruiter((String)obj[8]);
        jobapp.ownernamegroup = ((String)obj[9]);
        jobapp.isGroup = ((String)obj[10]);
        BigInteger ownergroupId = (BigInteger)obj[11];
        if (ownergroupId != null) {
          jobapp.ownergroupId = ownergroupId.longValue();
        }
        BigInteger recruiterId = (BigInteger)obj[12];
        if (recruiterId != null) {
          jobapp.recruiterId = recruiterId.longValue();
        }
        jobapp.setRecruiter((String)obj[13]);
        jobapp.recruitergroup = ((String)obj[14]);
        jobapp.setJuuid((String)obj[15]);
        String sourcetypename = (String)obj[16];
        sourcetypename = StringUtils.isNullOrEmpty(sourcetypename) ? "" : sourcetypename;
        jobapp.setSourceTypeName(sourcetypename);
        
        String sourcename = (String)obj[17];
        String agencyname = (String)obj[18];
        if (!StringUtils.isNullOrEmpty(agencyname)) {
          sourcename = agencyname;
        }
        sourcename = StringUtils.isNullOrEmpty(sourcename) ? "" : sourcename;
        jobapp.setSourceName(sourcename);
        
        String offerdeclinedreasonname = (String)obj[19];
        if (!StringUtils.isNullOrEmpty(offerdeclinedreasonname)) {
          jobapp.setOfferDeclinedResonName(offerdeclinedreasonname);
        } else {
          jobapp.setOfferDeclinedResonName("");
        }
        String offercancelreasonname = (String)obj[20];
        if (!StringUtils.isNullOrEmpty(offercancelreasonname)) {
          jobapp.setOfferCancelResonName(offercancelreasonname);
        } else {
          jobapp.setOfferCancelResonName("");
        }
        String rejectionreasonname = (String)obj[21];
        if (!StringUtils.isNullOrEmpty(rejectionreasonname)) {
          jobapp.setRejectionreasonname(rejectionreasonname);
        } else {
          jobapp.setRejectionreasonname("");
        }
        int basevalue = 22;
        if (!StringUtils.isNullOrEmpty(orgsqlgen))
        {
          if (isColumnPresent("ALL_APP_SEARCH_SCREEN", "hiringManager"))
          {
            jobapp.setHiringManager((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("ALL_APP_SEARCH_SCREEN", "orgName"))
          {
            jobapp.setOrgName((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("ALL_APP_SEARCH_SCREEN", "department"))
          {
            jobapp.setDepartment((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("ALL_APP_SEARCH_SCREEN", "projectcode"))
          {
            jobapp.setProjectcode((String)obj[basevalue]);
            basevalue += 1;
          }
        }
        jobapp = setApplicantListValues(obj, "ALL_APP_SEARCH_SCREEN", jobapp, basevalue);
        

        newapplicantList.add(jobapp);
      }
      logger.info("sqlcount" + sqlcount);
      
      Query querycount = session.createSQLQuery(sqlcount);
      Object obj = querycount.uniqueResult();
      logger.info("obj" + obj);
      BigInteger count = (BigInteger)obj;
      Integer totalsize = new Integer(count.intValue());
      logger.info("totalsize" + totalsize.intValue());
      searchApplicantMap.put(Common.APPLICANT_COUNT, totalsize);
      

      searchApplicantMap.put(Common.APPLICANT_LIST, newapplicantList);
      
      deleteIndexSearch(sessionId);
    }
    catch (Exception e)
    {
      logger.error("Exception on searchApplicantsByVendorForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return searchApplicantMap;
  }
  
  public JobApplicant getApplicantStatusAndRequitionDetails(long applicantId)
  {
    logger.info("inside getApplicantStatusAndRequitionDetails");
    JobApplicant applicant = new JobApplicant();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      String sql = "select application_id,full_name,uuid,jb_req_id,req_name,status,screen_code,super_user_key from job_applications  where application_id = :application_id";
      Query query = session.createSQLQuery(sql);
      query.setLong("application_id", applicantId);
      List userList = query.list();
      Object[] obj = (Object[])userList.get(0);
      BigInteger appid = (BigInteger)obj[0];
      long aid = appid.longValue();
      applicant.setApplicantId(aid);
      applicant.setFullName((String)obj[1]);
      applicant.setUuid((String)obj[2]);
      BigInteger reqid = (BigInteger)obj[3];
      long rid = reqid.longValue();
      applicant.setReqId(rid);
      applicant.setJobTitle((String)obj[4]);
      applicant.setStatus((String)obj[5]);
      applicant.setScreenCode((String)obj[6]);
      BigInteger superuserkey = (BigInteger)obj[7];
      long superuserkeyv = superuserkey.longValue();
      applicant.setSuper_user_key(superuserkeyv);
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantStatusAndRequitionDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return applicant;
  }
  
  public List searchOnBoardingApplicantsForPagination(User user, ApplicantSearchCriteria criteria, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside searchOnBoardingApplicantsForPagination method");
    List applicantList = new ArrayList();
    List newapplicantList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String sqlgen = getSQLApplicants("ON_BOARDING_SEARCH_SCREEN");
      logger.info("sqlgen" + sqlgen);
      String sql = "select ja.application_id,ja.uuid,ja.jb_req_id,ja.offerownerId,ja.full_name,jr.jb_req_code,jr.recruiter_name, (select concat(u.first_name,' ', u.last_name) from user_data u where u.user_id=ja.owner) ownername,ja.owner,(select ug.user_group_name from user_group ug where ug.user_group_id=ja.owner_group) user_group_name,ja.is_group,ja.owner_group ,jr.recruiter_id,jr.recruiter_name,jr.is_recruiter_group," + sqlgen + " from job_applications ja , job_requisition jr  " + " where ja.jb_req_id = jr.jb_req_id " + " and jr.super_user_key = :super_user_key ";
      






      String orgsqlgen = getOtherSQLApplicants("ON_BOARDING_SEARCH_SCREEN");
      if (!StringUtils.isNullOrEmpty(orgsqlgen)) {
        sql = "select ja.application_id,ja.uuid,ja.jb_req_id,ja.offerownerId,ja.full_name,jr.jb_req_code,jr.recruiter_name, (select concat(u.first_name,' ', u.last_name) from user_data u where u.user_id=ja.owner) ownername,ja.owner,(select ug.user_group_name from user_group ug where ug.user_group_id=ja.owner_group) user_group_name,ja.is_group,ja.owner_group ,jr.recruiter_id,jr.recruiter_name,jr.is_recruiter_group," + orgsqlgen + "," + sqlgen + " from job_applications ja , job_requisition jr  " + " where ja.jb_req_id = jr.jb_req_id " + " and jr.super_user_key = :super_user_key ";
      }
      criteria.setScreenName("ON_BOARDING_SEARCH_SCREEN");
      String sql1 = buildSQLQueryforSearch(user, criteria);
      


      sql = sql + sql1;
      String tmpcolumnname = (String)Constant.applicantTableBeanColumnNameMap.get(sort_str);
      
      sort_str = "ja." + tmpcolumnname;
      if (tmpcolumnname == null) {
        sort_str = (String)Constant.notApplicantTableBeanColumnNameMap.get(sort_str);
      }
      if (dir_str.equalsIgnoreCase("asc")) {
        sql = sql + "order by " + sort_str + " ASC";
      } else {
        sql = sql + "order by " + sort_str + " DESC";
      }
      logger.info("sql" + sql);
      Query query = session.createSQLQuery(sql);
      query.setLong("super_user_key", user.getSuper_user_key());
      query.setFirstResult(startIndex);
      query.setMaxResults(pageSize);
      
      applicantList = query.list();
      
      logger.info("applicantList.size()" + applicantList.size());
      for (int i = 0; i < applicantList.size(); i++)
      {
        Object[] obj = (Object[])applicantList.get(i);
        JobApplicant jobapp = new JobApplicant();
        
        BigInteger appId = (BigInteger)obj[0];
        long applicationId = appId.longValue();
        jobapp.setApplicantId(applicationId);
        jobapp.setUuid((String)obj[1]);
        BigInteger reqId = (BigInteger)obj[2];
        long requistionId = reqId.longValue();
        jobapp.setReqId(requistionId);
        BigInteger offowId = (BigInteger)obj[3];
        long offerownerid = offowId.longValue();
        jobapp.setOfferownerId(offerownerid);
        jobapp.setFullName((String)obj[4]);
        jobapp.setJobCode((String)obj[5]);
        jobapp.setRecruiter((String)obj[6]);
        
        jobapp.ownername = ((String)obj[7]);
        BigInteger ownerId = (BigInteger)obj[8];
        if (ownerId != null) {
          jobapp.ownerId = ownerId.longValue();
        }
        jobapp.ownernamegroup = ((String)obj[9]);
        jobapp.isGroup = ((String)obj[10]);
        BigInteger ownergroupId = (BigInteger)obj[11];
        if (ownergroupId != null) {
          jobapp.ownergroupId = ownergroupId.longValue();
        }
        BigInteger recruiterId = (BigInteger)obj[12];
        if (recruiterId != null) {
          jobapp.recruiterId = recruiterId.longValue();
        }
        jobapp.setRecruiter((String)obj[13]);
        jobapp.recruitergroup = ((String)obj[14]);
        
        int basevalue = 15;
        if (!StringUtils.isNullOrEmpty(orgsqlgen))
        {
          if (isColumnPresent("ON_BOARDING_SEARCH_SCREEN", "hiringManager"))
          {
            jobapp.setHiringManager((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("ON_BOARDING_SEARCH_SCREEN", "orgName"))
          {
            jobapp.setOrgName((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("ON_BOARDING_SEARCH_SCREEN", "department"))
          {
            jobapp.setDepartment((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("ON_BOARDING_SEARCH_SCREEN", "projectcode"))
          {
            jobapp.setProjectcode((String)obj[basevalue]);
            basevalue += 1;
          }
        }
        jobapp = setApplicantListValues(obj, "ON_BOARDING_SEARCH_SCREEN", jobapp, basevalue);
        

        newapplicantList.add(jobapp);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on searchOnBoardingApplicantsForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return newapplicantList;
  }
  
  public List searchApplicantsByVendorForPagination(User user, String prevorg, String email, String interviewstate, String fullname, String vendorid, String reqid, String appdate, String cri, String applicantNo, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside searchApplicantsByVendorForPagination method");
    List applicantList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = session.createCriteria(JobApplicant.class);
      if ((!StringUtils.isNullOrEmpty(vendorid)) && (!vendorid.equals("0")) && (!vendorid.equals("null"))) {
        outer.add(Restrictions.eq("vendorId", new Long(vendorid)));
      }
      if ((!StringUtils.isNullOrEmpty(applicantNo)) && (!applicantNo.equals("0")) && (!applicantNo.equals("null"))) {
        outer.add(Restrictions.eq("applicantId", new Long(applicantNo)));
      }
      if (!StringUtils.isNullOrEmpty(prevorg)) {
        outer.add(Restrictions.like("previousOrganization", "%" + prevorg + "%"));
      }
      if (!StringUtils.isNullOrEmpty(email)) {
        outer.add(Restrictions.like("email", "%" + email + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(reqid)) && (!reqid.equals("0"))) {
        outer.add(Restrictions.eq("reqId", new Long(reqid)));
      }
      if (!StringUtils.isNullOrEmpty(fullname)) {
        outer.add(Restrictions.like("fullName", "%" + fullname + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(interviewstate)) && (!interviewstate.equals("NoValue"))) {
        if (interviewstate.equalsIgnoreCase("Offer Accepted")) {
          outer.add(Restrictions.eq("interviewState", "Offer Accepted"));
        } else {
          outer.add(Restrictions.like("interviewState", "%" + interviewstate + "%"));
        }
      }
      logger.info("appdate" + appdate);
      if ((!StringUtils.isNullOrEmpty(appdate)) && (!appdate.equals("null")))
      {
        String datepattern = DateUtil.getDatePatternFormat(user.getLocale());
        

        DateFormat format = new SimpleDateFormat(datepattern + " hh:mm:ss");
        
        java.util.Date aDate = format.parse(appdate + " 00:00:00");
        java.util.Date bDate = format.parse(appdate + " 23:59:00");
        if (cri.equals("on")) {
          outer.add(Expression.between("appliedDate", new java.util.Date(aDate.getTime()), new java.util.Date(bDate.getTime())));
        }
        if (cri.equals("after")) {
          outer.add(Expression.gt("appliedDate", new java.util.Date(aDate.getTime())));
        }
        if (cri.equals("before")) {
          outer.add(Expression.lt("appliedDate", new java.util.Date(aDate.getTime())));
        }
      }
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      applicantList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on searchApplicantsByVendorForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return applicantList;
  }
  
  public List getApplicantsByInterviewStateAndTargetJoiningDate(User user, String interviewState, int noofdays, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getApplicantsByInterviewStateAndTargetJoiningDate method");
    
    List applicantList = new ArrayList();
    List newapplicantList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String sqlgen = getSQLApplicants("DASHBOARD_RECRUITER_IN_JOINED_PROCESS");
      String orgsqlgen = getOtherSQLApplicants("DASHBOARD_RECRUITER_IN_JOINED_PROCESS");
      
      String sql = "select ja.application_id,ja.full_name,ja.jb_req_id,ja.req_name,ja.interview_state,ja.targetjoining_date,ja.isInitiateJoiningProcess,ja.uuid,ja.offerownerId,ja.offerownerName,ja.is_offer_owner_group,(select concat(u.first_name,' ', u.last_name) from user_data u where u.user_id=ja.owner) ownername,ja.owner,(select ug.user_group_name from user_group ug where ug.user_group_id=ja.owner_group) user_group_name,ja.is_group,ja.owner_group,jr.recruiter_id,jr.recruiter_name,jr.is_recruiter_group,ja.applicant_number," + orgsqlgen + "," + sqlgen + "  from job_applications ja , job_requisition jr " + " where ja.jb_req_id = jr.jb_req_id " + " and jr.hiring_mgr_id = :hiring_mgr_id " + " and ja.interview_state = :interview_state " + " and ja.status='A'" + " and ja.targetjoining_date  BETWEEN NOW() AND DATE_ADD(NOW(), INTERVAL " + noofdays + " DAY)  ";
      












      String tmpcolumnname = (String)Constant.applicantTableBeanColumnNameMap.get(sort_str);
      
      sort_str = "ja." + tmpcolumnname;
      if (tmpcolumnname == null) {
        sort_str = (String)Constant.notApplicantTableBeanColumnNameMap.get(sort_str);
      }
      if (dir_str.equalsIgnoreCase("asc")) {
        sql = sql + "order by " + sort_str + " ASC";
      } else {
        sql = sql + "order by " + sort_str + " DESC";
      }
      Query query = session.createSQLQuery(sql);
      query.setLong("hiring_mgr_id", user.getUserId());
      query.setString("interview_state", interviewState);
      query.setFirstResult(startIndex);
      query.setMaxResults(pageSize);
      
      applicantList = query.list();
      
      logger.info("sql" + sql);
      for (int i = 0; i < applicantList.size(); i++)
      {
        Object[] obj = (Object[])applicantList.get(i);
        JobApplicant jobapp = new JobApplicant();
        
        BigInteger appId = (BigInteger)obj[0];
        long applicationId = appId.longValue();
        
        jobapp.setApplicantId(applicationId);
        jobapp.setFullName((String)obj[1]);
        BigInteger reqId = (BigInteger)obj[2];
        long requistionId = reqId.longValue();
        jobapp.setReqId(requistionId);
        jobapp.setJobTitle((String)obj[3]);
        jobapp.setInterviewState((String)obj[4]);
        java.util.Date targetjoingdate = (java.util.Date)obj[5];
        String targetjd = "";
        if (targetjoingdate != null)
        {
          String datepattern = Constant.getValue("defaultdateformat");
          if (user != null) {
            datepattern = DateUtil.getDatePatternFormat(user.getLocale());
          }
          targetjd = DateUtil.convertDateToStringDate(targetjoingdate, datepattern);
        }
        jobapp.setTargetjoiningdateStr(targetjd);
        jobapp.setInitiateJoiningProcess((String)obj[6]);
        jobapp.setUuid((String)obj[7]);
        BigInteger offowId = (BigInteger)obj[8];
        long offerownerid = offowId.longValue();
        jobapp.setOfferownerId(offerownerid);
        jobapp.setOfferownerName((String)obj[9]);
        String isgrp = (String)obj[10];
        if ((!StringUtils.isNullOrEmpty(isgrp)) && (isgrp.equals("Y"))) {
          jobapp.setIsofferownerGroup("Y");
        } else {
          jobapp.setIsofferownerGroup("N");
        }
        jobapp.ownername = ((String)obj[11]);
        BigInteger ownerId = (BigInteger)obj[12];
        if (ownerId != null) {
          jobapp.ownerId = ownerId.longValue();
        }
        jobapp.ownernamegroup = ((String)obj[13]);
        jobapp.isGroup = ((String)obj[14]);
        BigInteger ownergroupId = (BigInteger)obj[15];
        if (ownergroupId != null) {
          jobapp.ownergroupId = ownergroupId.longValue();
        }
        BigInteger recruiterId = (BigInteger)obj[16];
        if (recruiterId != null) {
          jobapp.recruiterId = recruiterId.longValue();
        }
        jobapp.setRecruiter((String)obj[17]);
        jobapp.recruitergroup = ((String)obj[18]);
        BigInteger appNumber = (BigInteger)obj[19];
        long appNumberValue = appNumber.longValue();
        jobapp.setApplicant_number(appNumberValue);
        int basevalue = 20;
        if (!StringUtils.isNullOrEmpty(orgsqlgen))
        {
          if (isColumnPresent("DASHBOARD_RECRUITER_IN_JOINED_PROCESS", "hiringManager"))
          {
            jobapp.setHiringManager((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("DASHBOARD_RECRUITER_IN_JOINED_PROCESS", "orgName"))
          {
            jobapp.setOrgName((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("DASHBOARD_RECRUITER_IN_JOINED_PROCESS", "department"))
          {
            jobapp.setDepartment((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("DASHBOARD_RECRUITER_IN_JOINED_PROCESS", "projectcode"))
          {
            jobapp.setProjectcode((String)obj[basevalue]);
            basevalue += 1;
          }
        }
        jobapp = setApplicantListValues(obj, "DASHBOARD_RECRUITER_IN_JOINED_PROCESS", jobapp, basevalue);
        

        newapplicantList.add(jobapp);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantsByInterviewStateAndTargetJoiningDate()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return newapplicantList;
  }
  
  public List getApplicantsByInterviewStateAndTargetJoiningDateAdmin(User user, String interviewState, int noofdays, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getApplicantsByInterviewStateAndTargetJoiningDateAdmin method");
    
    List applicantList = new ArrayList();
    List newapplicantList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String sqlgen = getSQLApplicants("DASHBOARD_RECRUITER_IN_JOINED_PROCESS");
      String orgsqlgen = getOtherSQLApplicants("DASHBOARD_RECRUITER_IN_JOINED_PROCESS");
      
      String sql = "select ja.application_id,ja.full_name,ja.jb_req_id,ja.req_name,ja.interview_state,ja.targetjoining_date,ja.isInitiateJoiningProcess,ja.uuid,ja.offerownerId,ja.offerownerName,ja.is_offer_owner_group,(select concat(u.first_name,' ', u.last_name) from user_data u where u.user_id=ja.owner) ownername,ja.owner,(select ug.user_group_name from user_group ug where ug.user_group_id=ja.owner_group) user_group_name,ja.is_group,ja.owner_group,jr.recruiter_id,jr.recruiter_name,jr.is_recruiter_group,ja.applicant_number," + orgsqlgen + "," + sqlgen + "  from job_applications ja , job_requisition jr " + " where ja.jb_req_id = jr.jb_req_id " + " and jr.super_user_key = :super_user_key " + " and ja.interview_state = :interview_state " + " and ja.status='A'" + " and ja.targetjoining_date  BETWEEN NOW() AND DATE_ADD(NOW(), INTERVAL " + noofdays + " DAY)  ";
      












      String tmpcolumnname = (String)Constant.applicantTableBeanColumnNameMap.get(sort_str);
      
      sort_str = "ja." + tmpcolumnname;
      if (tmpcolumnname == null) {
        sort_str = (String)Constant.notApplicantTableBeanColumnNameMap.get(sort_str);
      }
      if (dir_str.equalsIgnoreCase("asc")) {
        sql = sql + "order by " + sort_str + " ASC";
      } else {
        sql = sql + "order by " + sort_str + " DESC";
      }
      Query query = session.createSQLQuery(sql);
      query.setLong("super_user_key", user.getSuper_user_key());
      query.setString("interview_state", interviewState);
      query.setFirstResult(startIndex);
      query.setMaxResults(pageSize);
      
      applicantList = query.list();
      
      logger.info("sql" + sql);
      for (int i = 0; i < applicantList.size(); i++)
      {
        Object[] obj = (Object[])applicantList.get(i);
        JobApplicant jobapp = new JobApplicant();
        
        BigInteger appId = (BigInteger)obj[0];
        long applicationId = appId.longValue();
        
        jobapp.setApplicantId(applicationId);
        jobapp.setFullName((String)obj[1]);
        BigInteger reqId = (BigInteger)obj[2];
        long requistionId = reqId.longValue();
        jobapp.setReqId(requistionId);
        jobapp.setJobTitle((String)obj[3]);
        jobapp.setInterviewState((String)obj[4]);
        java.util.Date targetjoingdate = (java.util.Date)obj[5];
        String targetjd = "";
        if (targetjoingdate != null)
        {
          String datepattern = Constant.getValue("defaultdateformat");
          if (user != null) {
            datepattern = DateUtil.getDatePatternFormat(user.getLocale());
          }
          targetjd = DateUtil.convertDateToStringDate(targetjoingdate, datepattern);
        }
        jobapp.setTargetjoiningdateStr(targetjd);
        jobapp.setInitiateJoiningProcess((String)obj[6]);
        jobapp.setUuid((String)obj[7]);
        BigInteger offowId = (BigInteger)obj[8];
        long offerownerid = offowId.longValue();
        jobapp.setOfferownerId(offerownerid);
        jobapp.setOfferownerName((String)obj[9]);
        String isgrp = (String)obj[10];
        if ((!StringUtils.isNullOrEmpty(isgrp)) && (isgrp.equals("Y"))) {
          jobapp.setIsofferownerGroup("Y");
        } else {
          jobapp.setIsofferownerGroup("N");
        }
        jobapp.ownername = ((String)obj[11]);
        BigInteger ownerId = (BigInteger)obj[12];
        if (ownerId != null) {
          jobapp.ownerId = ownerId.longValue();
        }
        jobapp.ownernamegroup = ((String)obj[13]);
        jobapp.isGroup = ((String)obj[14]);
        BigInteger ownergroupId = (BigInteger)obj[15];
        if (ownergroupId != null) {
          jobapp.ownergroupId = ownergroupId.longValue();
        }
        BigInteger recruiterId = (BigInteger)obj[16];
        if (recruiterId != null) {
          jobapp.recruiterId = recruiterId.longValue();
        }
        jobapp.setRecruiter((String)obj[17]);
        jobapp.recruitergroup = ((String)obj[18]);
        BigInteger appNumber = (BigInteger)obj[19];
        long appNumberValue = appNumber.longValue();
        jobapp.setApplicant_number(appNumberValue);
        int basevalue = 20;
        if (!StringUtils.isNullOrEmpty(orgsqlgen))
        {
          if (isColumnPresent("DASHBOARD_RECRUITER_IN_JOINED_PROCESS", "hiringManager"))
          {
            jobapp.setHiringManager((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("DASHBOARD_RECRUITER_IN_JOINED_PROCESS", "orgName"))
          {
            jobapp.setOrgName((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("DASHBOARD_RECRUITER_IN_JOINED_PROCESS", "department"))
          {
            jobapp.setDepartment((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("DASHBOARD_RECRUITER_IN_JOINED_PROCESS", "projectcode"))
          {
            jobapp.setProjectcode((String)obj[basevalue]);
            basevalue += 1;
          }
        }
        jobapp = setApplicantListValues(obj, "DASHBOARD_RECRUITER_IN_JOINED_PROCESS", jobapp, basevalue);
        

        newapplicantList.add(jobapp);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantsByInterviewStateAndTargetJoiningDateAdmin()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return newapplicantList;
  }
  
  public List getApplicantsByInterviewStateAndTargetJoiningDate(User user, String interviewState, int noofdays)
  {
    logger.info("Inside getApplicantsByInterviewStateAndTargetJoiningDate method");
    
    List applicantList = new ArrayList();
    List newapplicantList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String sqlgen = getSQLApplicants("DASHBOARD_RECRUITER_IN_JOINED_PROCESS");
      String orgsqlgen = getOtherSQLApplicants("DASHBOARD_RECRUITER_IN_JOINED_PROCESS");
      
      String sql = "select ja.application_id,ja.full_name,ja.jb_req_id,ja.req_name,ja.interview_state,ja.targetjoining_date,ja.isInitiateJoiningProcess,ja.uuid,ja.offerownerId,ja.offerownerName,ja.is_offer_owner_group,(select concat(u.first_name,' ', u.last_name) from user_data u where u.user_id=ja.owner) ownername,ja.owner,(select ug.user_group_name from user_group ug where ug.user_group_id=ja.owner_group) user_group_name,ja.is_group,ja.owner_group,jr.recruiter_id,jr.recruiter_name,jr.is_recruiter_group,ja.applicant_number," + orgsqlgen + "," + sqlgen + "  from job_applications ja , job_requisition jr " + " where ja.jb_req_id = jr.jb_req_id " + " and jr.hiring_mgr_id = :hiring_mgr_id " + " and ja.interview_state = :interview_state " + " and ja.status='A'" + " and ja.targetjoining_date  >= date(sysdate()) and ja.targetjoining_date <= date(sysdate()) +" + noofdays + " order by ja.targetjoining_date asc";
      















      Query query = session.createSQLQuery(sql);
      query.setLong("hiring_mgr_id", user.getUserId());
      query.setString("interview_state", interviewState);
      

      applicantList = query.list();
      
      logger.info("sql" + sql);
      for (int i = 0; i < applicantList.size(); i++)
      {
        Object[] obj = (Object[])applicantList.get(i);
        JobApplicant jobapp = new JobApplicant();
        
        BigInteger appId = (BigInteger)obj[0];
        long applicationId = appId.longValue();
        
        jobapp.setApplicantId(applicationId);
        jobapp.setFullName((String)obj[1]);
        BigInteger reqId = (BigInteger)obj[2];
        long requistionId = reqId.longValue();
        jobapp.setReqId(requistionId);
        jobapp.setJobTitle((String)obj[3]);
        jobapp.setInterviewState((String)obj[4]);
        java.util.Date targetjoingdate = (java.util.Date)obj[5];
        String targetjd = "";
        if (targetjoingdate != null)
        {
          String datepattern = Constant.getValue("defaultdateformat");
          if (user != null) {
            datepattern = DateUtil.getDatePatternFormat(user.getLocale());
          }
          targetjd = DateUtil.convertDateToStringDate(targetjoingdate, datepattern);
        }
        jobapp.setTargetjoiningdateStr(targetjd);
        jobapp.setInitiateJoiningProcess((String)obj[6]);
        jobapp.setUuid((String)obj[7]);
        BigInteger offowId = (BigInteger)obj[8];
        long offerownerid = offowId.longValue();
        jobapp.setOfferownerId(offerownerid);
        jobapp.setOfferownerName((String)obj[9]);
        String isgrp = (String)obj[10];
        if ((!StringUtils.isNullOrEmpty(isgrp)) && (isgrp.equals("Y"))) {
          jobapp.setIsofferownerGroup("Y");
        } else {
          jobapp.setIsofferownerGroup("N");
        }
        jobapp.ownername = ((String)obj[11]);
        BigInteger ownerId = (BigInteger)obj[12];
        if (ownerId != null) {
          jobapp.ownerId = ownerId.longValue();
        }
        jobapp.ownernamegroup = ((String)obj[13]);
        jobapp.isGroup = ((String)obj[14]);
        BigInteger ownergroupId = (BigInteger)obj[15];
        if (ownergroupId != null) {
          jobapp.ownergroupId = ownergroupId.longValue();
        }
        BigInteger recruiterId = (BigInteger)obj[16];
        if (recruiterId != null) {
          jobapp.recruiterId = recruiterId.longValue();
        }
        jobapp.setRecruiter((String)obj[17]);
        jobapp.recruitergroup = ((String)obj[18]);
        BigInteger appNumber = (BigInteger)obj[19];
        long appNumberValue = appNumber.longValue();
        jobapp.setApplicant_number(appNumberValue);
        int basevalue = 20;
        if (!StringUtils.isNullOrEmpty(orgsqlgen))
        {
          if (isColumnPresent("DASHBOARD_RECRUITER_IN_JOINED_PROCESS", "hiringManager"))
          {
            jobapp.setHiringManager((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("DASHBOARD_RECRUITER_IN_JOINED_PROCESS", "orgName"))
          {
            jobapp.setOrgName((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("DASHBOARD_RECRUITER_IN_JOINED_PROCESS", "department"))
          {
            jobapp.setDepartment((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("DASHBOARD_RECRUITER_IN_JOINED_PROCESS", "projectcode"))
          {
            jobapp.setProjectcode((String)obj[basevalue]);
            basevalue += 1;
          }
        }
        jobapp = setApplicantListValues(obj, "DASHBOARD_RECRUITER_IN_JOINED_PROCESS", jobapp, basevalue);
        

        newapplicantList.add(jobapp);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantsByInterviewStateAndTargetJoiningDate()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return newapplicantList;
  }
  
  public List getApplicantsByInterviewStateAndJoiningDate(User user, String interviewState, int noofdays)
  {
    logger.info("Inside getApplicantsByInterviewStateAndJoiningDate method");
    
    List applicantList = new ArrayList();
    List newapplicantList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String sqlgen = getSQLApplicants("DASHBOARD_RECRUITER_IN_JOINED_PROCESS");
      String orgsqlgen = getOtherSQLApplicants("DASHBOARD_RECRUITER_IN_JOINED_PROCESS");
      
      String sql = "select ja.application_id,ja.full_name,ja.jb_req_id,ja.req_name,ja.interview_state,ja.targetjoining_date,ja.isInitiateJoiningProcess,ja.uuid,ja.offerownerId,ja.offerownerName,ja.is_offer_owner_group,(select concat(u.first_name,' ', u.last_name) from user_data u where u.user_id=ja.owner) ownername,ja.owner,(select ug.user_group_name from user_group ug where ug.user_group_id=ja.owner_group) user_group_name,ja.is_group,ja.owner_group,jr.recruiter_id,jr.recruiter_name,jr.is_recruiter_group,ja.applicant_number," + orgsqlgen + "," + sqlgen + "  from job_applications ja , job_requisition jr " + " where ja.jb_req_id = jr.jb_req_id " + " and jr.hiring_mgr_id = :hiring_mgr_id " + " and ja.interview_state = :interview_state " + " and ja.status='A'" + " and  ja.joined_date  BETWEEN DATE_SUB(NOW(), INTERVAL " + noofdays + " DAY) AND NOW() order by ja.joining_date asc";
      














      Query query = session.createSQLQuery(sql);
      query.setLong("hiring_mgr_id", user.getUserId());
      query.setString("interview_state", interviewState);
      

      applicantList = query.list();
      
      logger.info("sql" + sql);
      for (int i = 0; i < applicantList.size(); i++)
      {
        Object[] obj = (Object[])applicantList.get(i);
        JobApplicant jobapp = new JobApplicant();
        
        BigInteger appId = (BigInteger)obj[0];
        long applicationId = appId.longValue();
        
        jobapp.setApplicantId(applicationId);
        jobapp.setFullName((String)obj[1]);
        BigInteger reqId = (BigInteger)obj[2];
        long requistionId = reqId.longValue();
        jobapp.setReqId(requistionId);
        jobapp.setJobTitle((String)obj[3]);
        jobapp.setInterviewState((String)obj[4]);
        java.util.Date targetjoingdate = (java.util.Date)obj[5];
        String targetjd = "";
        if (targetjoingdate != null)
        {
          String datepattern = Constant.getValue("defaultdateformat");
          if (user != null) {
            datepattern = DateUtil.getDatePatternFormat(user.getLocale());
          }
          targetjd = DateUtil.convertDateToStringDate(targetjoingdate, datepattern);
        }
        jobapp.setTargetjoiningdateStr(targetjd);
        jobapp.setInitiateJoiningProcess((String)obj[6]);
        jobapp.setUuid((String)obj[7]);
        BigInteger offowId = (BigInteger)obj[8];
        long offerownerid = offowId.longValue();
        jobapp.setOfferownerId(offerownerid);
        jobapp.setOfferownerName((String)obj[9]);
        String isgrp = (String)obj[10];
        if ((!StringUtils.isNullOrEmpty(isgrp)) && (isgrp.equals("Y"))) {
          jobapp.setIsofferownerGroup("Y");
        } else {
          jobapp.setIsofferownerGroup("N");
        }
        jobapp.ownername = ((String)obj[11]);
        BigInteger ownerId = (BigInteger)obj[12];
        if (ownerId != null) {
          jobapp.ownerId = ownerId.longValue();
        }
        jobapp.ownernamegroup = ((String)obj[13]);
        jobapp.isGroup = ((String)obj[14]);
        BigInteger ownergroupId = (BigInteger)obj[15];
        if (ownergroupId != null) {
          jobapp.ownergroupId = ownergroupId.longValue();
        }
        BigInteger recruiterId = (BigInteger)obj[16];
        if (recruiterId != null) {
          jobapp.recruiterId = recruiterId.longValue();
        }
        jobapp.setRecruiter((String)obj[17]);
        jobapp.recruitergroup = ((String)obj[18]);
        BigInteger appNumber = (BigInteger)obj[19];
        long appNumberValue = appNumber.longValue();
        jobapp.setApplicant_number(appNumberValue);
        int basevalue = 20;
        if (!StringUtils.isNullOrEmpty(orgsqlgen))
        {
          if (isColumnPresent("DASHBOARD_RECRUITER_IN_JOINED_PROCESS", "hiringManager"))
          {
            jobapp.setHiringManager((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("DASHBOARD_RECRUITER_IN_JOINED_PROCESS", "orgName"))
          {
            jobapp.setOrgName((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("DASHBOARD_RECRUITER_IN_JOINED_PROCESS", "department"))
          {
            jobapp.setDepartment((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("DASHBOARD_RECRUITER_IN_JOINED_PROCESS", "projectcode"))
          {
            jobapp.setProjectcode((String)obj[basevalue]);
            basevalue += 1;
          }
        }
        jobapp = setApplicantListValues(obj, "DASHBOARD_RECRUITER_IN_JOINED_PROCESS", jobapp, basevalue);
        

        newapplicantList.add(jobapp);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantsByInterviewStateAndJoiningDate()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return newapplicantList;
  }
  
  public List getApplicantsByInterviewStateAndOfferDeclinedDate(User user, String interviewState, int noofdays)
  {
    logger.info("Inside getApplicantsByInterviewStateAndOfferDeclinedDate method");
    
    List applicantList = new ArrayList();
    List newapplicantList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String sqlgen = getSQLApplicants("DASHBOARD_RECRUITER_IN_JOINED_PROCESS");
      String orgsqlgen = getOtherSQLApplicants("DASHBOARD_RECRUITER_IN_JOINED_PROCESS");
      
      String sql = "select ja.application_id,ja.full_name,ja.jb_req_id,ja.req_name,ja.interview_state,ja.targetjoining_date,ja.isInitiateJoiningProcess,ja.uuid,ja.offerownerId,ja.offerownerName,ja.is_offer_owner_group,(select concat(u.first_name,' ', u.last_name) from user_data u where u.user_id=ja.owner) ownername,ja.owner,(select ug.user_group_name from user_group ug where ug.user_group_id=ja.owner_group) user_group_name,ja.is_group,ja.owner_group,jr.recruiter_id,jr.recruiter_name,jr.is_recruiter_group,ja.applicant_number," + orgsqlgen + "," + sqlgen + "  from job_applications ja , job_requisition jr " + " where ja.jb_req_id = jr.jb_req_id " + " and jr.hiring_mgr_id = :hiring_mgr_id " + " and ja.interview_state = :interview_state " + " and ja.status='A'" + " and  ja.offer_decliend_date  BETWEEN DATE_SUB(NOW(), INTERVAL " + noofdays + " DAY) AND NOW() order by ja.offer_decliend_date asc";
      














      Query query = session.createSQLQuery(sql);
      query.setLong("hiring_mgr_id", user.getUserId());
      query.setString("interview_state", interviewState);
      

      applicantList = query.list();
      
      logger.info("sql" + sql);
      for (int i = 0; i < applicantList.size(); i++)
      {
        Object[] obj = (Object[])applicantList.get(i);
        JobApplicant jobapp = new JobApplicant();
        
        BigInteger appId = (BigInteger)obj[0];
        long applicationId = appId.longValue();
        
        jobapp.setApplicantId(applicationId);
        jobapp.setFullName((String)obj[1]);
        BigInteger reqId = (BigInteger)obj[2];
        long requistionId = reqId.longValue();
        jobapp.setReqId(requistionId);
        jobapp.setJobTitle((String)obj[3]);
        jobapp.setInterviewState((String)obj[4]);
        java.util.Date targetjoingdate = (java.util.Date)obj[5];
        String targetjd = "";
        if (targetjoingdate != null)
        {
          String datepattern = Constant.getValue("defaultdateformat");
          if (user != null) {
            datepattern = DateUtil.getDatePatternFormat(user.getLocale());
          }
          targetjd = DateUtil.convertDateToStringDate(targetjoingdate, datepattern);
        }
        jobapp.setTargetjoiningdateStr(targetjd);
        jobapp.setInitiateJoiningProcess((String)obj[6]);
        jobapp.setUuid((String)obj[7]);
        BigInteger offowId = (BigInteger)obj[8];
        long offerownerid = offowId.longValue();
        jobapp.setOfferownerId(offerownerid);
        jobapp.setOfferownerName((String)obj[9]);
        String isgrp = (String)obj[10];
        if ((!StringUtils.isNullOrEmpty(isgrp)) && (isgrp.equals("Y"))) {
          jobapp.setIsofferownerGroup("Y");
        } else {
          jobapp.setIsofferownerGroup("N");
        }
        jobapp.ownername = ((String)obj[11]);
        BigInteger ownerId = (BigInteger)obj[12];
        if (ownerId != null) {
          jobapp.ownerId = ownerId.longValue();
        }
        jobapp.ownernamegroup = ((String)obj[13]);
        jobapp.isGroup = ((String)obj[14]);
        BigInteger ownergroupId = (BigInteger)obj[15];
        if (ownergroupId != null) {
          jobapp.ownergroupId = ownergroupId.longValue();
        }
        BigInteger recruiterId = (BigInteger)obj[16];
        if (recruiterId != null) {
          jobapp.recruiterId = recruiterId.longValue();
        }
        jobapp.setRecruiter((String)obj[17]);
        jobapp.recruitergroup = ((String)obj[18]);
        BigInteger appNumber = (BigInteger)obj[19];
        long appNumberValue = appNumber.longValue();
        jobapp.setApplicant_number(appNumberValue);
        int basevalue = 20;
        if (!StringUtils.isNullOrEmpty(orgsqlgen))
        {
          if (isColumnPresent("DASHBOARD_RECRUITER_IN_JOINED_PROCESS", "hiringManager"))
          {
            jobapp.setHiringManager((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("DASHBOARD_RECRUITER_IN_JOINED_PROCESS", "orgName"))
          {
            jobapp.setOrgName((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("DASHBOARD_RECRUITER_IN_JOINED_PROCESS", "department"))
          {
            jobapp.setDepartment((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("DASHBOARD_RECRUITER_IN_JOINED_PROCESS", "projectcode"))
          {
            jobapp.setProjectcode((String)obj[basevalue]);
            basevalue += 1;
          }
        }
        jobapp = setApplicantListValues(obj, "DASHBOARD_RECRUITER_IN_JOINED_PROCESS", jobapp, basevalue);
        

        newapplicantList.add(jobapp);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantsByInterviewStateAndOfferDeclinedDate()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return newapplicantList;
  }
  
  public List getApplicantsByInterviewStateAndJoiningDateAdmin(User user, String interviewState, int noofdays)
  {
    logger.info("Inside getApplicantsByInterviewStateAndJoiningDateAdmin method");
    
    List applicantList = new ArrayList();
    List newapplicantList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String sqlgen = getSQLApplicants("DASHBOARD_RECRUITER_IN_JOINED_PROCESS");
      String orgsqlgen = getOtherSQLApplicants("DASHBOARD_RECRUITER_IN_JOINED_PROCESS");
      
      String sql = "select ja.application_id,ja.full_name,ja.jb_req_id,ja.req_name,ja.interview_state,ja.targetjoining_date,ja.isInitiateJoiningProcess,ja.uuid,ja.offerownerId,ja.offerownerName,ja.is_offer_owner_group,(select concat(u.first_name,' ', u.last_name) from user_data u where u.user_id=ja.owner) ownername,ja.owner,(select ug.user_group_name from user_group ug where ug.user_group_id=ja.owner_group) user_group_name,ja.is_group,ja.owner_group,jr.recruiter_id,jr.recruiter_name,jr.is_recruiter_group,ja.applicant_number," + orgsqlgen + "," + sqlgen + "  from job_applications ja , job_requisition jr " + " where ja.jb_req_id = jr.jb_req_id " + " and jr.super_user_key = :super_user_key " + " and ja.interview_state = :interview_state " + " and ja.status='A'" + " and  ja.joined_date  BETWEEN DATE_SUB(NOW(), INTERVAL " + noofdays + " DAY) AND NOW() order by ja.joining_date asc";
      













      Query query = session.createSQLQuery(sql);
      query.setLong("super_user_key", user.getSuper_user_key());
      query.setString("interview_state", interviewState);
      

      applicantList = query.list();
      
      logger.info("sql" + sql);
      for (int i = 0; i < applicantList.size(); i++)
      {
        Object[] obj = (Object[])applicantList.get(i);
        JobApplicant jobapp = new JobApplicant();
        
        BigInteger appId = (BigInteger)obj[0];
        long applicationId = appId.longValue();
        
        jobapp.setApplicantId(applicationId);
        jobapp.setFullName((String)obj[1]);
        BigInteger reqId = (BigInteger)obj[2];
        long requistionId = reqId.longValue();
        jobapp.setReqId(requistionId);
        jobapp.setJobTitle((String)obj[3]);
        jobapp.setInterviewState((String)obj[4]);
        java.util.Date targetjoingdate = (java.util.Date)obj[5];
        String targetjd = "";
        if (targetjoingdate != null)
        {
          String datepattern = Constant.getValue("defaultdateformat");
          if (user != null) {
            datepattern = DateUtil.getDatePatternFormat(user.getLocale());
          }
          targetjd = DateUtil.convertDateToStringDate(targetjoingdate, datepattern);
        }
        jobapp.setTargetjoiningdateStr(targetjd);
        jobapp.setInitiateJoiningProcess((String)obj[6]);
        jobapp.setUuid((String)obj[7]);
        BigInteger offowId = (BigInteger)obj[8];
        long offerownerid = offowId.longValue();
        jobapp.setOfferownerId(offerownerid);
        jobapp.setOfferownerName((String)obj[9]);
        String isgrp = (String)obj[10];
        if ((!StringUtils.isNullOrEmpty(isgrp)) && (isgrp.equals("Y"))) {
          jobapp.setIsofferownerGroup("Y");
        } else {
          jobapp.setIsofferownerGroup("N");
        }
        jobapp.ownername = ((String)obj[11]);
        BigInteger ownerId = (BigInteger)obj[12];
        if (ownerId != null) {
          jobapp.ownerId = ownerId.longValue();
        }
        jobapp.ownernamegroup = ((String)obj[13]);
        jobapp.isGroup = ((String)obj[14]);
        BigInteger ownergroupId = (BigInteger)obj[15];
        if (ownergroupId != null) {
          jobapp.ownergroupId = ownergroupId.longValue();
        }
        BigInteger recruiterId = (BigInteger)obj[16];
        if (recruiterId != null) {
          jobapp.recruiterId = recruiterId.longValue();
        }
        jobapp.setRecruiter((String)obj[17]);
        jobapp.recruitergroup = ((String)obj[18]);
        BigInteger appNumber = (BigInteger)obj[19];
        long appNumberValue = appNumber.longValue();
        jobapp.setApplicant_number(appNumberValue);
        int basevalue = 20;
        if (!StringUtils.isNullOrEmpty(orgsqlgen))
        {
          if (isColumnPresent("DASHBOARD_RECRUITER_IN_JOINED_PROCESS", "hiringManager"))
          {
            jobapp.setHiringManager((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("DASHBOARD_RECRUITER_IN_JOINED_PROCESS", "orgName"))
          {
            jobapp.setOrgName((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("DASHBOARD_RECRUITER_IN_JOINED_PROCESS", "department"))
          {
            jobapp.setDepartment((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("DASHBOARD_RECRUITER_IN_JOINED_PROCESS", "projectcode"))
          {
            jobapp.setProjectcode((String)obj[basevalue]);
            basevalue += 1;
          }
        }
        jobapp = setApplicantListValues(obj, "DASHBOARD_RECRUITER_IN_JOINED_PROCESS", jobapp, basevalue);
        

        newapplicantList.add(jobapp);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantsByInterviewStateAndJoiningDateAdmin()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return newapplicantList;
  }
  
  public List getApplicantsByInterviewStateAndTargetJoiningDateAdmin(User user, String interviewState, int noofdays)
  {
    logger.info("Inside getApplicantsByInterviewStateAndTargetJoiningDateAdmin method");
    
    List applicantList = new ArrayList();
    List newapplicantList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String sqlgen = getSQLApplicants("DASHBOARD_RECRUITER_IN_JOINED_PROCESS");
      String orgsqlgen = getOtherSQLApplicants("DASHBOARD_RECRUITER_IN_JOINED_PROCESS");
      
      String sql = "select ja.application_id,ja.full_name,ja.jb_req_id,ja.req_name,ja.interview_state,ja.targetjoining_date,ja.isInitiateJoiningProcess,ja.uuid,ja.offerownerId,ja.offerownerName,ja.is_offer_owner_group,(select concat(u.first_name,' ', u.last_name) from user_data u where u.user_id=ja.owner) ownername,ja.owner,(select ug.user_group_name from user_group ug where ug.user_group_id=ja.owner_group) user_group_name,ja.is_group,ja.owner_group,jr.recruiter_id,jr.recruiter_name,jr.is_recruiter_group,ja.applicant_number," + orgsqlgen + "," + sqlgen + "  from job_applications ja , job_requisition jr " + " where ja.jb_req_id = jr.jb_req_id " + " and jr.super_user_key = :super_user_key " + " and ja.interview_state = :interview_state " + " and ja.status='A'" + " and ja.targetjoining_date  >= date(sysdate()) and ja.targetjoining_date <= date(sysdate()) +" + noofdays + " order by ja.targetjoining_date asc";
      















      Query query = session.createSQLQuery(sql);
      query.setLong("super_user_key", user.getSuper_user_key());
      query.setString("interview_state", interviewState);
      

      applicantList = query.list();
      
      logger.info("sql" + sql);
      for (int i = 0; i < applicantList.size(); i++)
      {
        Object[] obj = (Object[])applicantList.get(i);
        JobApplicant jobapp = new JobApplicant();
        
        BigInteger appId = (BigInteger)obj[0];
        long applicationId = appId.longValue();
        
        jobapp.setApplicantId(applicationId);
        jobapp.setFullName((String)obj[1]);
        BigInteger reqId = (BigInteger)obj[2];
        long requistionId = reqId.longValue();
        jobapp.setReqId(requistionId);
        jobapp.setJobTitle((String)obj[3]);
        jobapp.setInterviewState((String)obj[4]);
        java.util.Date targetjoingdate = (java.util.Date)obj[5];
        String targetjd = "";
        if (targetjoingdate != null)
        {
          String datepattern = Constant.getValue("defaultdateformat");
          if (user != null) {
            datepattern = DateUtil.getDatePatternFormat(user.getLocale());
          }
          targetjd = DateUtil.convertDateToStringDate(targetjoingdate, datepattern);
        }
        jobapp.setTargetjoiningdateStr(targetjd);
        jobapp.setInitiateJoiningProcess((String)obj[6]);
        jobapp.setUuid((String)obj[7]);
        BigInteger offowId = (BigInteger)obj[8];
        long offerownerid = offowId.longValue();
        jobapp.setOfferownerId(offerownerid);
        jobapp.setOfferownerName((String)obj[9]);
        String isgrp = (String)obj[10];
        if ((!StringUtils.isNullOrEmpty(isgrp)) && (isgrp.equals("Y"))) {
          jobapp.setIsofferownerGroup("Y");
        } else {
          jobapp.setIsofferownerGroup("N");
        }
        jobapp.ownername = ((String)obj[11]);
        BigInteger ownerId = (BigInteger)obj[12];
        if (ownerId != null) {
          jobapp.ownerId = ownerId.longValue();
        }
        jobapp.ownernamegroup = ((String)obj[13]);
        jobapp.isGroup = ((String)obj[14]);
        BigInteger ownergroupId = (BigInteger)obj[15];
        if (ownergroupId != null) {
          jobapp.ownergroupId = ownergroupId.longValue();
        }
        BigInteger recruiterId = (BigInteger)obj[16];
        if (recruiterId != null) {
          jobapp.recruiterId = recruiterId.longValue();
        }
        jobapp.setRecruiter((String)obj[17]);
        jobapp.recruitergroup = ((String)obj[18]);
        BigInteger appNumber = (BigInteger)obj[19];
        long appNumberValue = appNumber.longValue();
        jobapp.setApplicant_number(appNumberValue);
        int basevalue = 20;
        if (!StringUtils.isNullOrEmpty(orgsqlgen))
        {
          if (isColumnPresent("DASHBOARD_RECRUITER_IN_JOINED_PROCESS", "hiringManager"))
          {
            jobapp.setHiringManager((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("DASHBOARD_RECRUITER_IN_JOINED_PROCESS", "orgName"))
          {
            jobapp.setOrgName((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("DASHBOARD_RECRUITER_IN_JOINED_PROCESS", "department"))
          {
            jobapp.setDepartment((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("DASHBOARD_RECRUITER_IN_JOINED_PROCESS", "projectcode"))
          {
            jobapp.setProjectcode((String)obj[basevalue]);
            basevalue += 1;
          }
        }
        jobapp = setApplicantListValues(obj, "DASHBOARD_RECRUITER_IN_JOINED_PROCESS", jobapp, basevalue);
        

        newapplicantList.add(jobapp);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantsByInterviewStateAndTargetJoiningDateAdmin()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return newapplicantList;
  }
  
  public List getApplicantsByInterviewStateAndOfferDeclinedDateAdmin(User user, String interviewState, int noofdays)
  {
    logger.info("Inside getApplicantsByInterviewStateAndOfferDeclinedDateAdmin method");
    
    List applicantList = new ArrayList();
    List newapplicantList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String sqlgen = getSQLApplicants("DASHBOARD_RECRUITER_IN_JOINED_PROCESS");
      String orgsqlgen = getOtherSQLApplicants("DASHBOARD_RECRUITER_IN_JOINED_PROCESS");
      
      String sql = "select ja.application_id,ja.full_name,ja.jb_req_id,ja.req_name,ja.interview_state,ja.targetjoining_date,ja.isInitiateJoiningProcess,ja.uuid,ja.offerownerId,ja.offerownerName,ja.is_offer_owner_group,(select concat(u.first_name,' ', u.last_name) from user_data u where u.user_id=ja.owner) ownername,ja.owner,(select ug.user_group_name from user_group ug where ug.user_group_id=ja.owner_group) user_group_name,ja.is_group,ja.owner_group,jr.recruiter_id,jr.recruiter_name,jr.is_recruiter_group,ja.applicant_number," + orgsqlgen + "," + sqlgen + "  from job_applications ja , job_requisition jr " + " where ja.jb_req_id = jr.jb_req_id " + " and jr.super_user_key = :super_user_key " + " and ja.interview_state = :interview_state " + " and ja.status='A'" + " and  ja.offer_decliend_date  BETWEEN DATE_SUB(NOW(), INTERVAL " + noofdays + " DAY) AND NOW() order by ja.offer_decliend_date asc";
      
















      Query query = session.createSQLQuery(sql);
      query.setLong("super_user_key", user.getSuper_user_key());
      query.setString("interview_state", interviewState);
      

      applicantList = query.list();
      
      logger.info("sql" + sql);
      for (int i = 0; i < applicantList.size(); i++)
      {
        Object[] obj = (Object[])applicantList.get(i);
        JobApplicant jobapp = new JobApplicant();
        
        BigInteger appId = (BigInteger)obj[0];
        long applicationId = appId.longValue();
        
        jobapp.setApplicantId(applicationId);
        jobapp.setFullName((String)obj[1]);
        BigInteger reqId = (BigInteger)obj[2];
        long requistionId = reqId.longValue();
        jobapp.setReqId(requistionId);
        jobapp.setJobTitle((String)obj[3]);
        jobapp.setInterviewState((String)obj[4]);
        java.util.Date targetjoingdate = (java.util.Date)obj[5];
        String targetjd = "";
        if (targetjoingdate != null)
        {
          String datepattern = Constant.getValue("defaultdateformat");
          if (user != null) {
            datepattern = DateUtil.getDatePatternFormat(user.getLocale());
          }
          targetjd = DateUtil.convertDateToStringDate(targetjoingdate, datepattern);
        }
        jobapp.setTargetjoiningdateStr(targetjd);
        jobapp.setInitiateJoiningProcess((String)obj[6]);
        jobapp.setUuid((String)obj[7]);
        BigInteger offowId = (BigInteger)obj[8];
        long offerownerid = offowId.longValue();
        jobapp.setOfferownerId(offerownerid);
        jobapp.setOfferownerName((String)obj[9]);
        String isgrp = (String)obj[10];
        if ((!StringUtils.isNullOrEmpty(isgrp)) && (isgrp.equals("Y"))) {
          jobapp.setIsofferownerGroup("Y");
        } else {
          jobapp.setIsofferownerGroup("N");
        }
        jobapp.ownername = ((String)obj[11]);
        BigInteger ownerId = (BigInteger)obj[12];
        if (ownerId != null) {
          jobapp.ownerId = ownerId.longValue();
        }
        jobapp.ownernamegroup = ((String)obj[13]);
        jobapp.isGroup = ((String)obj[14]);
        BigInteger ownergroupId = (BigInteger)obj[15];
        if (ownergroupId != null) {
          jobapp.ownergroupId = ownergroupId.longValue();
        }
        BigInteger recruiterId = (BigInteger)obj[16];
        if (recruiterId != null) {
          jobapp.recruiterId = recruiterId.longValue();
        }
        jobapp.setRecruiter((String)obj[17]);
        jobapp.recruitergroup = ((String)obj[18]);
        BigInteger appNumber = (BigInteger)obj[19];
        long appNumberValue = appNumber.longValue();
        jobapp.setApplicant_number(appNumberValue);
        int basevalue = 20;
        if (!StringUtils.isNullOrEmpty(orgsqlgen))
        {
          if (isColumnPresent("DASHBOARD_RECRUITER_IN_JOINED_PROCESS", "hiringManager"))
          {
            jobapp.setHiringManager((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("DASHBOARD_RECRUITER_IN_JOINED_PROCESS", "orgName"))
          {
            jobapp.setOrgName((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("DASHBOARD_RECRUITER_IN_JOINED_PROCESS", "department"))
          {
            jobapp.setDepartment((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("DASHBOARD_RECRUITER_IN_JOINED_PROCESS", "projectcode"))
          {
            jobapp.setProjectcode((String)obj[basevalue]);
            basevalue += 1;
          }
        }
        jobapp = setApplicantListValues(obj, "DASHBOARD_RECRUITER_IN_JOINED_PROCESS", jobapp, basevalue);
        

        newapplicantList.add(jobapp);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantsByInterviewStateAndOfferDeclinedDateAdmin()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return newapplicantList;
  }
  
  public DataTableBean getApplicantsByInterviewStateAndTargetJoiningDateRecruiter(User user, String interviewState, int noofdays, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getApplicantsByInterviewStateAndTargetJoiningDateRecruiter method");
    
    List applicantList = new ArrayList();
    List newapplicantList = new ArrayList();
    org.hibernate.Session session = null;
    DataTableBean dtbean = new DataTableBean();
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String sqlgen = getSQLApplicants("DASHBOARD_RECRUITER_IN_JOINED_PROCESS");
      String orgsqlgen = getOtherSQLApplicants("DASHBOARD_RECRUITER_IN_JOINED_PROCESS");
      String sql = " select distinct ja.application_id,ja.full_name,ja.jb_req_id,ja.req_name,ja.interview_state,ja.targetjoining_date,ja.isInitiateJoiningProcess,ja.uuid,ja.offerownerId,ja.offerownerName,ja.is_offer_owner_group, (select concat(u.first_name,' ', u.last_name) from user_data u where u.user_id=ja.owner) ownername,ja.owner,(select ug.user_group_name from user_group ug where ug.user_group_id=ja.owner_group) user_group_name,ja.is_group,ja.owner_group,jr.recruiter_id,jr.recruiter_name,jr.is_recruiter_group,jr.uuid as juuid," + orgsqlgen + "," + sqlgen + " from job_applications ja , job_requisition jr , user_group ug,user_group_users ugu" + " where ja.jb_req_id = jr.jb_req_id " + " and ((jr.recruiter_id = :recruiter_id and jr.is_recruiter_group <> 'Y' )or (ja.offerownerid=:offerownerid  AND  ja.is_offer_owner_group <> 'Y') " + " or (ja.offerownerid= ug.user_group_id  AND ja.is_offer_owner_group = 'Y' and ugu.user_id=:offerownerid) or " + " (jr.recruiter_id= ug.user_group_id  AND jr.is_recruiter_group = 'Y' and ugu.user_id=:recruiter_id)" + ")" + " and ja.interview_state = :interview_state " + " and ja.status='A'" + " and ja.targetjoining_date  BETWEEN NOW() AND DATE_ADD(NOW(), INTERVAL " + noofdays + " DAY)  ";
      















      String tmpcolumnname = (String)Constant.applicantTableBeanColumnNameMap.get(sort_str);
      
      sort_str = "ja." + tmpcolumnname;
      if (tmpcolumnname == null) {
        sort_str = (String)Constant.notApplicantTableBeanColumnNameMap.get(sort_str);
      }
      if (dir_str.equalsIgnoreCase("asc")) {
        sql = sql + "order by " + sort_str + " ASC";
      } else {
        sql = sql + "order by " + sort_str + " DESC";
      }
      logger.info("sql" + sql);
      Query query = session.createSQLQuery(sql);
      query.setLong("recruiter_id", user.getUserId());
      query.setLong("offerownerid", user.getUserId());
      query.setString("interview_state", interviewState);
      query.setFirstResult(startIndex);
      query.setMaxResults(pageSize);
      
      applicantList = query.list();
      for (int i = 0; i < applicantList.size(); i++)
      {
        Object[] obj = (Object[])applicantList.get(i);
        JobApplicant jobapp = new JobApplicant();
        
        BigInteger appId = (BigInteger)obj[0];
        long applicationId = appId.longValue();
        
        jobapp.setApplicantId(applicationId);
        jobapp.setFullName((String)obj[1]);
        BigInteger reqId = (BigInteger)obj[2];
        long requistionId = reqId.longValue();
        jobapp.setReqId(requistionId);
        jobapp.setJobTitle((String)obj[3]);
        jobapp.setInterviewState((String)obj[4]);
        java.util.Date targetjoingdate = (java.util.Date)obj[5];
        String targetjd = "";
        if (targetjoingdate != null)
        {
          String datepattern = Constant.getValue("defaultdateformat");
          if (user != null) {
            datepattern = DateUtil.getDatePatternFormat(user.getLocale());
          }
          targetjd = DateUtil.convertDateToStringDate(targetjoingdate, datepattern);
        }
        jobapp.setTargetjoiningdateStr(targetjd);
        jobapp.setInitiateJoiningProcess((String)obj[6]);
        jobapp.setUuid((String)obj[7]);
        BigInteger offowId = (BigInteger)obj[8];
        long offerownerid = offowId.longValue();
        jobapp.setOfferownerId(offerownerid);
        jobapp.setOfferownerName((String)obj[9]);
        String isgrp = (String)obj[10];
        if ((!StringUtils.isNullOrEmpty(isgrp)) && (isgrp.equals("Y"))) {
          jobapp.setIsofferownerGroup("Y");
        } else {
          jobapp.setIsofferownerGroup("N");
        }
        jobapp.ownername = ((String)obj[11]);
        BigInteger ownerId = (BigInteger)obj[12];
        if (ownerId != null) {
          jobapp.ownerId = ownerId.longValue();
        }
        jobapp.ownernamegroup = ((String)obj[13]);
        jobapp.isGroup = ((String)obj[14]);
        BigInteger ownergroupId = (BigInteger)obj[15];
        if (ownergroupId != null) {
          jobapp.ownergroupId = ownergroupId.longValue();
        }
        BigInteger recruiterId = (BigInteger)obj[16];
        if (recruiterId != null) {
          jobapp.recruiterId = recruiterId.longValue();
        }
        jobapp.setRecruiter((String)obj[17]);
        jobapp.recruitergroup = ((String)obj[18]);
        jobapp.setJuuid((String)obj[19]);
        int basevalue = 20;
        if (!StringUtils.isNullOrEmpty(orgsqlgen))
        {
          if (isColumnPresent("DASHBOARD_RECRUITER_IN_JOINED_PROCESS", "hiringManager"))
          {
            jobapp.setHiringManager((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("DASHBOARD_RECRUITER_IN_JOINED_PROCESS", "orgName"))
          {
            jobapp.setOrgName((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("DASHBOARD_RECRUITER_IN_JOINED_PROCESS", "department"))
          {
            jobapp.setDepartment((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("DASHBOARD_RECRUITER_IN_JOINED_PROCESS", "projectcode"))
          {
            jobapp.setProjectcode((String)obj[basevalue]);
            basevalue += 1;
          }
        }
        jobapp = setApplicantListValues(obj, "DASHBOARD_RECRUITER_IN_JOINED_PROCESS", jobapp, basevalue);
        

        newapplicantList.add(jobapp);
      }
      String sqlcount = " select distinct ja.application_id from job_applications ja , job_requisition jr , user_group ug,user_group_users ugu where ja.jb_req_id = jr.jb_req_id  and ((jr.recruiter_id = :recruiter_id and jr.is_recruiter_group <> 'Y' )or (ja.offerownerid=:offerownerid  AND  ja.is_offer_owner_group <> 'Y')  or (ja.offerownerid= ug.user_group_id  AND ja.is_offer_owner_group = 'Y' and ugu.user_id=:offerownerid) or  (jr.recruiter_id= ug.user_group_id  AND jr.is_recruiter_group = 'Y' and ugu.user_id=:recruiter_id)) and ja.interview_state = :interview_state  and ja.status='A' and ja.targetjoining_date  BETWEEN NOW() AND DATE_ADD(NOW(), INTERVAL " + noofdays + " DAY)  ";
      








      logger.info("sqlcount" + sqlcount);
      Query querycount = session.createSQLQuery(sqlcount);
      querycount.setLong("recruiter_id", user.getUserId());
      querycount.setLong("offerownerid", user.getUserId());
      querycount.setString("interview_state", interviewState);
      List applicantListcount = querycount.list();
      
      dtbean.setValueList(newapplicantList);
      dtbean.setTotalcount(applicantListcount.size());
      
      logger.info("applicantListcount.size()" + applicantListcount.size());
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantsByInterviewStateAndTargetJoiningDateRecruiter()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return dtbean;
  }
  
  public List getApplicantsByInterviewStateAndTargetJoiningDateRecruiter(User user, String interviewState, int noofdays)
  {
    logger.info("Inside getApplicantsByInterviewStateAndTargetJoiningDateRecruiter method");
    
    List applicantList = new ArrayList();
    List newapplicantList = new ArrayList();
    org.hibernate.Session session = null;
    DataTableBean dtbean = new DataTableBean();
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String sqlgen = getSQLApplicants("DASHBOARD_RECRUITER_IN_JOINED_PROCESS");
      String orgsqlgen = getOtherSQLApplicants("DASHBOARD_RECRUITER_IN_JOINED_PROCESS");
      String sql = " select distinct ja.application_id,ja.full_name,ja.jb_req_id,ja.req_name,ja.interview_state,ja.targetjoining_date,ja.isInitiateJoiningProcess,ja.uuid,ja.offerownerId,ja.offerownerName,ja.is_offer_owner_group, (select concat(u.first_name,' ', u.last_name) from user_data u where u.user_id=ja.owner) ownername,ja.owner,(select ug.user_group_name from user_group ug where ug.user_group_id=ja.owner_group) user_group_name,ja.is_group,ja.owner_group,jr.recruiter_id,jr.recruiter_name,jr.is_recruiter_group,jr.uuid as juuid,ja.applicant_number," + orgsqlgen + "," + sqlgen + " from job_applications ja , job_requisition jr , user_group ug,user_group_users ugu" + " where ja.jb_req_id = jr.jb_req_id " + " and ((jr.recruiter_id = :recruiter_id and jr.is_recruiter_group <> 'Y' ) or " + " (jr.recruiter_id= ug.user_group_id  AND jr.is_recruiter_group = 'Y' and ugu.user_id=:recruiter_id)" + ")" + " and ja.interview_state = :interview_state " + " and ja.status='A'" + " and ja.targetjoining_date  >= date(sysdate()) and ja.targetjoining_date <= date(sysdate()) +" + noofdays + " order by ja.targetjoining_date asc";
      

















      logger.info("sql" + sql);
      Query query = session.createSQLQuery(sql);
      query.setLong("recruiter_id", user.getUserId());
      query.setString("interview_state", interviewState);
      

      applicantList = query.list();
      for (int i = 0; i < applicantList.size(); i++)
      {
        Object[] obj = (Object[])applicantList.get(i);
        JobApplicant jobapp = new JobApplicant();
        
        BigInteger appId = (BigInteger)obj[0];
        long applicationId = appId.longValue();
        
        jobapp.setApplicantId(applicationId);
        jobapp.setFullName((String)obj[1]);
        BigInteger reqId = (BigInteger)obj[2];
        long requistionId = reqId.longValue();
        jobapp.setReqId(requistionId);
        jobapp.setJobTitle((String)obj[3]);
        jobapp.setInterviewState((String)obj[4]);
        java.util.Date targetjoingdate = (java.util.Date)obj[5];
        String targetjd = "";
        if (targetjoingdate != null)
        {
          String datepattern = Constant.getValue("defaultdateformat");
          if (user != null) {
            datepattern = DateUtil.getDatePatternFormat(user.getLocale());
          }
          targetjd = DateUtil.convertDateToStringDate(targetjoingdate, datepattern);
        }
        jobapp.setTargetjoiningdateStr(targetjd);
        jobapp.setInitiateJoiningProcess((String)obj[6]);
        jobapp.setUuid((String)obj[7]);
        BigInteger offowId = (BigInteger)obj[8];
        long offerownerid = offowId.longValue();
        jobapp.setOfferownerId(offerownerid);
        jobapp.setOfferownerName((String)obj[9]);
        String isgrp = (String)obj[10];
        if ((!StringUtils.isNullOrEmpty(isgrp)) && (isgrp.equals("Y"))) {
          jobapp.setIsofferownerGroup("Y");
        } else {
          jobapp.setIsofferownerGroup("N");
        }
        jobapp.ownername = ((String)obj[11]);
        BigInteger ownerId = (BigInteger)obj[12];
        if (ownerId != null) {
          jobapp.ownerId = ownerId.longValue();
        }
        jobapp.ownernamegroup = ((String)obj[13]);
        jobapp.isGroup = ((String)obj[14]);
        BigInteger ownergroupId = (BigInteger)obj[15];
        if (ownergroupId != null) {
          jobapp.ownergroupId = ownergroupId.longValue();
        }
        BigInteger recruiterId = (BigInteger)obj[16];
        if (recruiterId != null) {
          jobapp.recruiterId = recruiterId.longValue();
        }
        jobapp.setRecruiter((String)obj[17]);
        jobapp.recruitergroup = ((String)obj[18]);
        jobapp.setJuuid((String)obj[19]);
        BigInteger appNumber = (BigInteger)obj[20];
        long appNumberValue = appNumber.longValue();
        jobapp.setApplicant_number(appNumberValue);
        int basevalue = 21;
        if (!StringUtils.isNullOrEmpty(orgsqlgen))
        {
          if (isColumnPresent("DASHBOARD_RECRUITER_IN_JOINED_PROCESS", "hiringManager"))
          {
            jobapp.setHiringManager((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("DASHBOARD_RECRUITER_IN_JOINED_PROCESS", "orgName"))
          {
            jobapp.setOrgName((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("DASHBOARD_RECRUITER_IN_JOINED_PROCESS", "department"))
          {
            jobapp.setDepartment((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("DASHBOARD_RECRUITER_IN_JOINED_PROCESS", "projectcode"))
          {
            jobapp.setProjectcode((String)obj[basevalue]);
            basevalue += 1;
          }
        }
        jobapp = setApplicantListValues(obj, "DASHBOARD_RECRUITER_IN_JOINED_PROCESS", jobapp, basevalue);
        

        newapplicantList.add(jobapp);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantsByInterviewStateAndTargetJoiningDateRecruiter()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return newapplicantList;
  }
  
  public List getApplicantsByInterviewStateAndJoiningDateRecruiter(User user, String interviewState, int noofdays)
  {
    logger.info("Inside getApplicantsByInterviewStateAndJoiningDateRecruiter method");
    
    List applicantList = new ArrayList();
    List newapplicantList = new ArrayList();
    org.hibernate.Session session = null;
    DataTableBean dtbean = new DataTableBean();
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String sqlgen = getSQLApplicants("DASHBOARD_RECRUITER_IN_JOINED_PROCESS");
      String orgsqlgen = getOtherSQLApplicants("DASHBOARD_RECRUITER_IN_JOINED_PROCESS");
      String sql = " select distinct ja.application_id,ja.full_name,ja.jb_req_id,ja.req_name,ja.interview_state,ja.targetjoining_date,ja.isInitiateJoiningProcess,ja.uuid,ja.offerownerId,ja.offerownerName,ja.is_offer_owner_group, (select concat(u.first_name,' ', u.last_name) from user_data u where u.user_id=ja.owner) ownername,ja.owner,(select ug.user_group_name from user_group ug where ug.user_group_id=ja.owner_group) user_group_name,ja.is_group,ja.owner_group,jr.recruiter_id,jr.recruiter_name,jr.is_recruiter_group,jr.uuid as juuid,ja.applicant_number," + orgsqlgen + "," + sqlgen + " from job_applications ja , job_requisition jr , user_group ug,user_group_users ugu" + " where ja.jb_req_id = jr.jb_req_id " + " and ((jr.recruiter_id = :recruiter_id and jr.is_recruiter_group <> 'Y' ) or " + " (jr.recruiter_id= ug.user_group_id  AND jr.is_recruiter_group = 'Y' and ugu.user_id=:recruiter_id)" + ")" + " and ja.interview_state = :interview_state " + " and ja.status='A'" + " and  ja.joined_date  BETWEEN DATE_SUB(NOW(), INTERVAL " + noofdays + " DAY) AND NOW() order by ja.joining_date asc";
      
















      logger.info("sql" + sql);
      Query query = session.createSQLQuery(sql);
      query.setLong("recruiter_id", user.getUserId());
      query.setString("interview_state", interviewState);
      

      applicantList = query.list();
      for (int i = 0; i < applicantList.size(); i++)
      {
        Object[] obj = (Object[])applicantList.get(i);
        JobApplicant jobapp = new JobApplicant();
        
        BigInteger appId = (BigInteger)obj[0];
        long applicationId = appId.longValue();
        
        jobapp.setApplicantId(applicationId);
        jobapp.setFullName((String)obj[1]);
        BigInteger reqId = (BigInteger)obj[2];
        long requistionId = reqId.longValue();
        jobapp.setReqId(requistionId);
        jobapp.setJobTitle((String)obj[3]);
        jobapp.setInterviewState((String)obj[4]);
        java.util.Date targetjoingdate = (java.util.Date)obj[5];
        String targetjd = "";
        if (targetjoingdate != null)
        {
          String datepattern = Constant.getValue("defaultdateformat");
          if (user != null) {
            datepattern = DateUtil.getDatePatternFormat(user.getLocale());
          }
          targetjd = DateUtil.convertDateToStringDate(targetjoingdate, datepattern);
        }
        jobapp.setTargetjoiningdateStr(targetjd);
        jobapp.setInitiateJoiningProcess((String)obj[6]);
        jobapp.setUuid((String)obj[7]);
        BigInteger offowId = (BigInteger)obj[8];
        long offerownerid = offowId.longValue();
        jobapp.setOfferownerId(offerownerid);
        jobapp.setOfferownerName((String)obj[9]);
        String isgrp = (String)obj[10];
        if ((!StringUtils.isNullOrEmpty(isgrp)) && (isgrp.equals("Y"))) {
          jobapp.setIsofferownerGroup("Y");
        } else {
          jobapp.setIsofferownerGroup("N");
        }
        jobapp.ownername = ((String)obj[11]);
        BigInteger ownerId = (BigInteger)obj[12];
        if (ownerId != null) {
          jobapp.ownerId = ownerId.longValue();
        }
        jobapp.ownernamegroup = ((String)obj[13]);
        jobapp.isGroup = ((String)obj[14]);
        BigInteger ownergroupId = (BigInteger)obj[15];
        if (ownergroupId != null) {
          jobapp.ownergroupId = ownergroupId.longValue();
        }
        BigInteger recruiterId = (BigInteger)obj[16];
        if (recruiterId != null) {
          jobapp.recruiterId = recruiterId.longValue();
        }
        jobapp.setRecruiter((String)obj[17]);
        jobapp.recruitergroup = ((String)obj[18]);
        jobapp.setJuuid((String)obj[19]);
        BigInteger appNumber = (BigInteger)obj[20];
        long appNumberValue = appNumber.longValue();
        jobapp.setApplicant_number(appNumberValue);
        int basevalue = 21;
        if (!StringUtils.isNullOrEmpty(orgsqlgen))
        {
          if (isColumnPresent("DASHBOARD_RECRUITER_IN_JOINED_PROCESS", "hiringManager"))
          {
            jobapp.setHiringManager((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("DASHBOARD_RECRUITER_IN_JOINED_PROCESS", "orgName"))
          {
            jobapp.setOrgName((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("DASHBOARD_RECRUITER_IN_JOINED_PROCESS", "department"))
          {
            jobapp.setDepartment((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("DASHBOARD_RECRUITER_IN_JOINED_PROCESS", "projectcode"))
          {
            jobapp.setProjectcode((String)obj[basevalue]);
            basevalue += 1;
          }
        }
        jobapp = setApplicantListValues(obj, "DASHBOARD_RECRUITER_IN_JOINED_PROCESS", jobapp, basevalue);
        

        newapplicantList.add(jobapp);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantsByInterviewStateAndJoiningDateRecruiter()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return newapplicantList;
  }
  
  public List getApplicantsByInterviewStateAndOfferDeclinedDateRecruiter(User user, String interviewState, int noofdays)
  {
    logger.info("Inside getApplicantsByInterviewStateAndOfferDeclinedDateRecruiter method");
    
    List applicantList = new ArrayList();
    List newapplicantList = new ArrayList();
    org.hibernate.Session session = null;
    DataTableBean dtbean = new DataTableBean();
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String sqlgen = getSQLApplicants("DASHBOARD_RECRUITER_IN_JOINED_PROCESS");
      String orgsqlgen = getOtherSQLApplicants("DASHBOARD_RECRUITER_IN_JOINED_PROCESS");
      String sql = " select distinct ja.application_id,ja.full_name,ja.jb_req_id,ja.req_name,ja.interview_state,ja.targetjoining_date,ja.isInitiateJoiningProcess,ja.uuid,ja.offerownerId,ja.offerownerName,ja.is_offer_owner_group, (select concat(u.first_name,' ', u.last_name) from user_data u where u.user_id=ja.owner) ownername,ja.owner,(select ug.user_group_name from user_group ug where ug.user_group_id=ja.owner_group) user_group_name,ja.is_group,ja.owner_group,jr.recruiter_id,jr.recruiter_name,jr.is_recruiter_group,jr.uuid as juuid,ja.applicant_number," + orgsqlgen + "," + sqlgen + " from job_applications ja , job_requisition jr , user_group ug,user_group_users ugu" + " where ja.jb_req_id = jr.jb_req_id " + " and ((jr.recruiter_id = :recruiter_id and jr.is_recruiter_group <> 'Y' ) or " + " (jr.recruiter_id= ug.user_group_id  AND jr.is_recruiter_group = 'Y' and ugu.user_id=:recruiter_id)" + ")" + " and ja.interview_state = :interview_state " + " and ja.status='A'" + " and  ja.offer_decliend_date  BETWEEN DATE_SUB(NOW(), INTERVAL " + noofdays + " DAY) AND NOW() order by ja.offer_decliend_date asc";
      

















      logger.info("sql" + sql);
      Query query = session.createSQLQuery(sql);
      query.setLong("recruiter_id", user.getUserId());
      query.setString("interview_state", interviewState);
      

      applicantList = query.list();
      for (int i = 0; i < applicantList.size(); i++)
      {
        Object[] obj = (Object[])applicantList.get(i);
        JobApplicant jobapp = new JobApplicant();
        
        BigInteger appId = (BigInteger)obj[0];
        long applicationId = appId.longValue();
        
        jobapp.setApplicantId(applicationId);
        jobapp.setFullName((String)obj[1]);
        BigInteger reqId = (BigInteger)obj[2];
        long requistionId = reqId.longValue();
        jobapp.setReqId(requistionId);
        jobapp.setJobTitle((String)obj[3]);
        jobapp.setInterviewState((String)obj[4]);
        java.util.Date targetjoingdate = (java.util.Date)obj[5];
        String targetjd = "";
        if (targetjoingdate != null)
        {
          String datepattern = Constant.getValue("defaultdateformat");
          if (user != null) {
            datepattern = DateUtil.getDatePatternFormat(user.getLocale());
          }
          targetjd = DateUtil.convertDateToStringDate(targetjoingdate, datepattern);
        }
        jobapp.setTargetjoiningdateStr(targetjd);
        jobapp.setInitiateJoiningProcess((String)obj[6]);
        jobapp.setUuid((String)obj[7]);
        BigInteger offowId = (BigInteger)obj[8];
        long offerownerid = offowId.longValue();
        jobapp.setOfferownerId(offerownerid);
        jobapp.setOfferownerName((String)obj[9]);
        String isgrp = (String)obj[10];
        if ((!StringUtils.isNullOrEmpty(isgrp)) && (isgrp.equals("Y"))) {
          jobapp.setIsofferownerGroup("Y");
        } else {
          jobapp.setIsofferownerGroup("N");
        }
        jobapp.ownername = ((String)obj[11]);
        BigInteger ownerId = (BigInteger)obj[12];
        if (ownerId != null) {
          jobapp.ownerId = ownerId.longValue();
        }
        jobapp.ownernamegroup = ((String)obj[13]);
        jobapp.isGroup = ((String)obj[14]);
        BigInteger ownergroupId = (BigInteger)obj[15];
        if (ownergroupId != null) {
          jobapp.ownergroupId = ownergroupId.longValue();
        }
        BigInteger recruiterId = (BigInteger)obj[16];
        if (recruiterId != null) {
          jobapp.recruiterId = recruiterId.longValue();
        }
        jobapp.setRecruiter((String)obj[17]);
        jobapp.recruitergroup = ((String)obj[18]);
        jobapp.setJuuid((String)obj[19]);
        BigInteger appNumber = (BigInteger)obj[20];
        long appNumberValue = appNumber.longValue();
        jobapp.setApplicant_number(appNumberValue);
        int basevalue = 21;
        if (!StringUtils.isNullOrEmpty(orgsqlgen))
        {
          if (isColumnPresent("DASHBOARD_RECRUITER_IN_JOINED_PROCESS", "hiringManager"))
          {
            jobapp.setHiringManager((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("DASHBOARD_RECRUITER_IN_JOINED_PROCESS", "orgName"))
          {
            jobapp.setOrgName((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("DASHBOARD_RECRUITER_IN_JOINED_PROCESS", "department"))
          {
            jobapp.setDepartment((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("DASHBOARD_RECRUITER_IN_JOINED_PROCESS", "projectcode"))
          {
            jobapp.setProjectcode((String)obj[basevalue]);
            basevalue += 1;
          }
        }
        jobapp = setApplicantListValues(obj, "DASHBOARD_RECRUITER_IN_JOINED_PROCESS", jobapp, basevalue);
        

        newapplicantList.add(jobapp);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantsByInterviewStateAndOfferDeclinedDateRecruiter()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return newapplicantList;
  }
  
  public int getCountOfApplicantsByInterviewStateAndTargetJoiningDateRecruiter(User user, String interviewState, int noofdays)
  {
    logger.info("Inside getCountOfApplicantsByInterviewStateAndTargetJoiningDateRecruiter method");
    
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String sql = "select ja.application_id,ja.full_name,ja.jb_req_id,ja.req_name,ja.interview_state,ja.targetjoining_date,ja.isInitiateJoiningProcess,ja.uuid,ja.offerownerId,ja.offerownerName from job_applications ja , job_requisition jr  where ja.jb_req_id = jr.jb_req_id  and (jr.recruiter_id = :recruiter_id or ja.offerownerid= :offerownerid )  and ja.interview_state = :interview_state  and ja.status='A' and ja.targetjoining_date  >= sysdate() and ja.targetjoining_date <= sysdate() +" + noofdays;
      






      Query query = session.createSQLQuery(sql);
      query.setLong("recruiter_id", user.getUserId());
      query.setLong("offerownerid", user.getUserId());
      query.setString("interview_state", interviewState);
      
      totaluser = query.list().size();
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfApplicantsByInterviewStateAndTargetJoiningDateRecruiter()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totaluser;
  }
  
  public int getCountOfsearchOwnApplicantsForPaginationHiringMgr(User user, ApplicantSearchCriteria criteria)
  {
    logger.info("Inside getCountOfsearchOwnApplicantsForPaginationHiringMgr method");
    
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String sql = "select ja.application_id,ja.full_name,ja.jb_req_id,ja.req_name,ja.interview_state,ja.targetjoining_date,ja.isInitiateJoiningProcess,ja.uuid,ja.offerownerId,ja.offerownerName,ja.email_id,ja.city,ja.heighest_qualification,ja.applied_datetime,ja.referer_name,u.first_name, u.last_name,ja.created_by,ja.phone,ja.offerreleasedate,ja.targerofferdate,ja.joining_date from job_applications ja , job_requisition jr , user_data u  where ja.jb_req_id = jr.jb_req_id  and jr.hiring_mgr_id = :hiring_mgr_id ";
      


      String sql1 = buildSQLQueryforSearch(user, criteria);
      sql = sql + sql1;
      
      logger.info("sql" + sql);
      Query query = session.createSQLQuery(sql);
      query.setLong("hiring_mgr_id", user.getUserId());
      
      totaluser = query.list().size();
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfsearchOwnApplicantsForPaginationHiringMgr()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totaluser;
  }
  
  public int getCountOfsearchOwnApplicantsForPaginationRecruiter(User user, ApplicantSearchCriteria criteria)
  {
    logger.info("Inside getCountOfsearchOwnApplicantsForPaginationRecruiter method");
    
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String sql = "select ja.application_id,ja.full_name,ja.jb_req_id,ja.req_name,ja.interview_state,ja.targetjoining_date,ja.isInitiateJoiningProcess,ja.uuid,ja.offerownerId,ja.offerownerName,ja.email_id,ja.city,ja.heighest_qualification,ja.applied_datetime,ja.referer_name,u.first_name, u.last_name,ja.created_by,ja.phone,ja.offerreleasedate,ja.targerofferdate,ja.joining_date from job_applications ja , job_requisition jr , user_data u  where ja.jb_req_id = jr.jb_req_id  and (jr.recruiter_id = :recruiter_id or ja.offerownerid= :offerownerid ) ";
      


      String sql1 = buildSQLQueryforSearch(user, criteria);
      sql = sql + sql1;
      logger.info("sql" + sql);
      Query query = session.createSQLQuery(sql);
      query.setLong("recruiter_id", user.getUserId());
      query.setLong("offerownerid", user.getUserId());
      
      totaluser = query.list().size();
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfsearchOwnApplicantsForPaginationRecruiter()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totaluser;
  }
  
  public int getCountOfsearchOwnApplicantsForPaginationHiringMgr(User user)
  {
    logger.info("Inside getCountOfsearchOwnApplicantsForPaginationHiringMgr method");
    
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String sql = "select ja.application_id,ja.full_name,ja.jb_req_id,ja.req_name,ja.interview_state,ja.targetjoining_date,ja.isInitiateJoiningProcess,ja.uuid,ja.offerownerId,ja.offerownerName from job_applications ja , job_requisition jr  where ja.jb_req_id = jr.jb_req_id  and jr.hiring_mgr_id = :hiring_mgr_id ";
      


      sql = sql + " and ja.status='A' ";
      
      Query query = session.createSQLQuery(sql);
      query.setLong("hiring_mgr_id", user.getUserId());
      
      totaluser = query.list().size();
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfsearchOwnApplicantsForPaginationHiringMgr()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totaluser;
  }
  
  public int getCountOfApplicantsByInterviewStateAndTargetJoiningDate(User user, String interviewState, int noofdays)
  {
    logger.info("Inside getCountOfApplicantsByInterviewStateAndTargetJoiningDate method");
    
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String sql = "select ja.application_id,ja.full_name,ja.jb_req_id,ja.req_name,ja.interview_state,ja.targetjoining_date,ja.isInitiateJoiningProcess,ja.uuid,ja.offerownerId,ja.offerownerName from job_applications ja , job_requisition jr  where ja.jb_req_id = jr.jb_req_id  and jr.hiring_mgr_id = :hiring_mgr_id  and ja.interview_state = :interview_state  and ja.status='A' and ja.targetjoining_date  BETWEEN NOW() AND DATE_ADD(NOW(), INTERVAL " + noofdays + " DAY)  ";
      





      Query query = session.createSQLQuery(sql);
      query.setLong("hiring_mgr_id", user.getUserId());
      query.setString("interview_state", interviewState);
      
      totaluser = query.list().size();
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfApplicantsByInterviewStateAndTargetJoiningDate()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totaluser;
  }
  
  public int getCountOfApplicantsByInterviewStateAndTargetJoiningDateAdmin(User user, String interviewState, int noofdays)
  {
    logger.info("Inside getCountOfApplicantsByInterviewStateAndTargetJoiningDateAdmin method");
    
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String sql = "select ja.application_id,ja.full_name,ja.jb_req_id,ja.req_name,ja.interview_state,ja.targetjoining_date,ja.isInitiateJoiningProcess,ja.uuid,ja.offerownerId,ja.offerownerName from job_applications ja , job_requisition jr  where ja.jb_req_id = jr.jb_req_id  and jr.super_user_key = :super_user_key  and ja.interview_state = :interview_state  and ja.status='A' and ja.targetjoining_date  BETWEEN NOW() AND DATE_ADD(NOW(), INTERVAL " + noofdays + " DAY)  ";
      





      Query query = session.createSQLQuery(sql);
      query.setLong("super_user_key", user.getSuper_user_key());
      query.setString("interview_state", interviewState);
      
      totaluser = query.list().size();
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfApplicantsByInterviewStateAndTargetJoiningDateAdmin()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totaluser;
  }
  
  public List getApplicantsInOfferProcessByHiringMgr(User user, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getApplicantsInOfferProcessByHiringMgr method");
    List applicantList = new ArrayList();
    List newapplicantList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String sqlgen = getSQLApplicants("DASHBOARD_RECRUITER_IN_OFFER_PROCESS");
      String orgsqlgen = getOtherSQLApplicants("DASHBOARD_RECRUITER_IN_OFFER_PROCESS");
      
      String sql = "select ja.application_id,ja.full_name,ja.jb_req_id,ja.req_name,ja.interview_state,ja.offerreleasedate,ja.uuid,ja.offerownerId,ja.offerownerName,ja.is_offer_owner_group, (select concat(u.first_name,' ', u.last_name) from user_data u where u.user_id=ja.owner) ownername,ja.owner,(select ug.user_group_name from user_group ug where ug.user_group_id=ja.owner_group) user_group_name,ja.is_group,ja.owner_group,jr.recruiter_id,jr.recruiter_name,jr.is_recruiter_group," + orgsqlgen + "," + sqlgen + " from job_applications ja , job_requisition jr " + " where ja.jb_req_id = jr.jb_req_id " + " and jr.hiring_mgr_id = :hiring_mgr_id " + " and ja.interview_state like '%Offer%' and  ja.interview_state <> 'Offer Accepted'" + " and ja.status='A' ";
      











      String tmpcolumnname = (String)Constant.applicantTableBeanColumnNameMap.get(sort_str);
      
      sort_str = "ja." + tmpcolumnname;
      if (tmpcolumnname == null) {
        sort_str = (String)Constant.notApplicantTableBeanColumnNameMap.get(sort_str);
      }
      if (dir_str.equalsIgnoreCase("asc")) {
        sql = sql + "order by " + sort_str + " ASC";
      } else {
        sql = sql + "order by " + sort_str + " DESC";
      }
      Query query = session.createSQLQuery(sql);
      query.setLong("hiring_mgr_id", user.getUserId());
      query.setFirstResult(startIndex);
      query.setMaxResults(pageSize);
      
      applicantList = query.list();
      
      logger.info("sql" + sql);
      for (int i = 0; i < applicantList.size(); i++)
      {
        Object[] obj = (Object[])applicantList.get(i);
        JobApplicant jobapp = new JobApplicant();
        
        BigInteger appId = (BigInteger)obj[0];
        long applicationId = appId.longValue();
        
        jobapp.setApplicantId(applicationId);
        jobapp.setFullName((String)obj[1]);
        BigInteger reqId = (BigInteger)obj[2];
        long requistionId = reqId.longValue();
        jobapp.setReqId(requistionId);
        jobapp.setJobTitle((String)obj[3]);
        jobapp.setInterviewState((String)obj[4]);
        java.util.Date offerreleaseddate = (java.util.Date)obj[5];
        
        jobapp.setOfferreleasedate(offerreleaseddate);
        jobapp.setUuid((String)obj[6]);
        BigInteger offowId = (BigInteger)obj[7];
        long offerownerid = offowId.longValue();
        jobapp.setOfferownerId(offerownerid);
        jobapp.setOfferownerName((String)obj[8]);
        String isgrp = (String)obj[9];
        if ((!StringUtils.isNullOrEmpty(isgrp)) && (isgrp.equals("Y"))) {
          jobapp.setIsofferownerGroup("Y");
        } else {
          jobapp.setIsofferownerGroup("N");
        }
        jobapp.ownername = ((String)obj[10]);
        BigInteger ownerId = (BigInteger)obj[11];
        if (ownerId != null) {
          jobapp.ownerId = ownerId.longValue();
        }
        jobapp.ownernamegroup = ((String)obj[12]);
        jobapp.isGroup = ((String)obj[13]);
        BigInteger ownergroupId = (BigInteger)obj[14];
        if (ownergroupId != null) {
          jobapp.ownergroupId = ownergroupId.longValue();
        }
        BigInteger recruiterId = (BigInteger)obj[15];
        if (recruiterId != null) {
          jobapp.recruiterId = recruiterId.longValue();
        }
        jobapp.recruiter = ((String)obj[16]);
        jobapp.recruitergroup = ((String)obj[17]);
        
        int basevalue = 18;
        if (!StringUtils.isNullOrEmpty(orgsqlgen))
        {
          if (isColumnPresent("DASHBOARD_RECRUITER_IN_OFFER_PROCESS", "hiringManager"))
          {
            jobapp.setHiringManager((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("DASHBOARD_RECRUITER_IN_OFFER_PROCESS", "orgName"))
          {
            jobapp.setOrgName((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("DASHBOARD_RECRUITER_IN_OFFER_PROCESS", "department"))
          {
            jobapp.setDepartment((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("DASHBOARD_RECRUITER_IN_OFFER_PROCESS", "projectcode"))
          {
            jobapp.setProjectcode((String)obj[basevalue]);
            basevalue += 1;
          }
        }
        jobapp = setApplicantListValues(obj, "DASHBOARD_RECRUITER_IN_OFFER_PROCESS", jobapp, basevalue);
        

        newapplicantList.add(jobapp);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantsInOfferProcessByHiringMgr()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return newapplicantList;
  }
  
  public List getApplicantsInOfferProcessAdmin(User user, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getApplicantsInOfferProcessAdmin method");
    List applicantList = new ArrayList();
    List newapplicantList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String sqlgen = getSQLApplicants("DASHBOARD_RECRUITER_IN_OFFER_PROCESS");
      String orgsqlgen = getOtherSQLApplicants("DASHBOARD_RECRUITER_IN_OFFER_PROCESS");
      
      String sql = "select ja.application_id,ja.full_name,ja.jb_req_id,ja.req_name,ja.interview_state,ja.offerreleasedate,ja.uuid,ja.offerownerId,ja.offerownerName,ja.is_offer_owner_group, (select concat(u.first_name,' ', u.last_name) from user_data u where u.user_id=ja.owner) ownername,ja.owner,(select ug.user_group_name from user_group ug where ug.user_group_id=ja.owner_group) user_group_name,ja.is_group,ja.owner_group,jr.recruiter_id,jr.recruiter_name,jr.is_recruiter_group," + orgsqlgen + "," + sqlgen + " from job_applications ja , job_requisition jr " + " where ja.jb_req_id = jr.jb_req_id " + " and jr.super_user_key = :super_user_key " + " and ja.interview_state like '%Offer%' and  ja.interview_state <> 'Offer Accepted'" + " and ja.status='A' ";
      











      String tmpcolumnname = (String)Constant.applicantTableBeanColumnNameMap.get(sort_str);
      
      sort_str = "ja." + tmpcolumnname;
      if (tmpcolumnname == null) {
        sort_str = (String)Constant.notApplicantTableBeanColumnNameMap.get(sort_str);
      }
      if (dir_str.equalsIgnoreCase("asc")) {
        sql = sql + "order by " + sort_str + " ASC";
      } else {
        sql = sql + "order by " + sort_str + " DESC";
      }
      Query query = session.createSQLQuery(sql);
      query.setLong("super_user_key", user.getSuper_user_key());
      query.setFirstResult(startIndex);
      query.setMaxResults(pageSize);
      
      applicantList = query.list();
      
      logger.info("sql" + sql);
      for (int i = 0; i < applicantList.size(); i++)
      {
        Object[] obj = (Object[])applicantList.get(i);
        JobApplicant jobapp = new JobApplicant();
        
        BigInteger appId = (BigInteger)obj[0];
        long applicationId = appId.longValue();
        
        jobapp.setApplicantId(applicationId);
        jobapp.setFullName((String)obj[1]);
        BigInteger reqId = (BigInteger)obj[2];
        long requistionId = reqId.longValue();
        jobapp.setReqId(requistionId);
        jobapp.setJobTitle((String)obj[3]);
        jobapp.setInterviewState((String)obj[4]);
        java.util.Date offerreleaseddate = (java.util.Date)obj[5];
        
        jobapp.setOfferreleasedate(offerreleaseddate);
        jobapp.setUuid((String)obj[6]);
        BigInteger offowId = (BigInteger)obj[7];
        long offerownerid = offowId.longValue();
        jobapp.setOfferownerId(offerownerid);
        jobapp.setOfferownerName((String)obj[8]);
        String isgrp = (String)obj[9];
        if ((!StringUtils.isNullOrEmpty(isgrp)) && (isgrp.equals("Y"))) {
          jobapp.setIsofferownerGroup("Y");
        } else {
          jobapp.setIsofferownerGroup("N");
        }
        jobapp.ownername = ((String)obj[10]);
        BigInteger ownerId = (BigInteger)obj[11];
        if (ownerId != null) {
          jobapp.ownerId = ownerId.longValue();
        }
        jobapp.ownernamegroup = ((String)obj[12]);
        jobapp.isGroup = ((String)obj[13]);
        BigInteger ownergroupId = (BigInteger)obj[14];
        if (ownergroupId != null) {
          jobapp.ownergroupId = ownergroupId.longValue();
        }
        BigInteger recruiterId = (BigInteger)obj[15];
        if (recruiterId != null) {
          jobapp.recruiterId = recruiterId.longValue();
        }
        jobapp.recruiter = ((String)obj[16]);
        jobapp.recruitergroup = ((String)obj[17]);
        
        int basevalue = 18;
        if (!StringUtils.isNullOrEmpty(orgsqlgen))
        {
          if (isColumnPresent("DASHBOARD_RECRUITER_IN_OFFER_PROCESS", "hiringManager"))
          {
            jobapp.setHiringManager((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("DASHBOARD_RECRUITER_IN_OFFER_PROCESS", "orgName"))
          {
            jobapp.setOrgName((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("DASHBOARD_RECRUITER_IN_OFFER_PROCESS", "department"))
          {
            jobapp.setDepartment((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("DASHBOARD_RECRUITER_IN_OFFER_PROCESS", "projectcode"))
          {
            jobapp.setProjectcode((String)obj[basevalue]);
            basevalue += 1;
          }
        }
        jobapp = setApplicantListValues(obj, "DASHBOARD_RECRUITER_IN_OFFER_PROCESS", jobapp, basevalue);
        

        newapplicantList.add(jobapp);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantsInOfferProcessAdmin()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return newapplicantList;
  }
  
  public DataTableBean getApplicantsInOfferProcessByRecruiter(User user, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getApplicantsInOfferProcessByRecruiter method");
    List applicantList = new ArrayList();
    List newapplicantList = new ArrayList();
    org.hibernate.Session session = null;
    DataTableBean dtbean = new DataTableBean();
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String sqlgen = getSQLApplicants("DASHBOARD_RECRUITER_IN_OFFER_PROCESS");
      String orgsqlgen = getOtherSQLApplicants("DASHBOARD_RECRUITER_IN_OFFER_PROCESS");
      String sql = " select distinct ja.application_id,ja.full_name,ja.jb_req_id,ja.req_name,ja.interview_state,ja.offerreleasedate,ja.uuid,ja.offerownerId,ja.offerownerName,ja.is_offer_owner_group, (select concat(u.first_name,' ', u.last_name) from user_data u where u.user_id=ja.owner) ownername,ja.owner,(select ug.user_group_name from user_group ug where ug.user_group_id=ja.owner_group) user_group_name,ja.is_group,ja.owner_group ,jr.recruiter_id,jr.recruiter_name,jr.is_recruiter_group,jr.uuid as juuid," + orgsqlgen + "," + sqlgen + " from job_applications ja , job_requisition jr , user_group ug,user_group_users ugu" + " where ja.jb_req_id = jr.jb_req_id " + " and ((jr.recruiter_id = :recruiter_id and jr.is_recruiter_group <> 'Y' )or (ja.offerownerid=:offerownerid  AND  ja.is_offer_owner_group <> 'Y') " + " or (ja.offerownerid= ug.user_group_id  AND ja.is_offer_owner_group = 'Y' and ugu.user_id=:offerownerid) or " + " (jr.recruiter_id= ug.user_group_id  AND jr.is_recruiter_group = 'Y' and ugu.user_id=:recruiter_id)" + ")" + " and ja.interview_state like '%Offer%' and  ja.interview_state <> 'Offer Accepted' " + " and ja.status='A' ";
      














      String tmpcolumnname = (String)Constant.applicantTableBeanColumnNameMap.get(sort_str);
      
      sort_str = "ja." + tmpcolumnname;
      if (tmpcolumnname == null) {
        sort_str = (String)Constant.notApplicantTableBeanColumnNameMap.get(sort_str);
      }
      if (dir_str.equalsIgnoreCase("asc")) {
        sql = sql + "order by " + sort_str + " ASC";
      } else {
        sql = sql + "order by " + sort_str + " DESC";
      }
      Query query = session.createSQLQuery(sql);
      query.setLong("recruiter_id", user.getUserId());
      query.setLong("offerownerid", user.getUserId());
      query.setFirstResult(startIndex);
      query.setMaxResults(pageSize);
      
      applicantList = query.list();
      
      logger.info("final sql" + sql);
      for (int i = 0; i < applicantList.size(); i++)
      {
        Object[] obj = (Object[])applicantList.get(i);
        JobApplicant jobapp = new JobApplicant();
        
        BigInteger appId = (BigInteger)obj[0];
        long applicationId = appId.longValue();
        
        jobapp.setApplicantId(applicationId);
        jobapp.setFullName((String)obj[1]);
        BigInteger reqId = (BigInteger)obj[2];
        long requistionId = reqId.longValue();
        jobapp.setReqId(requistionId);
        jobapp.setJobTitle((String)obj[3]);
        jobapp.setInterviewState((String)obj[4]);
        java.util.Date offerreleaseddate = (java.util.Date)obj[5];
        jobapp.setOfferreleasedate(offerreleaseddate);
        jobapp.setUuid((String)obj[6]);
        BigInteger offowId = (BigInteger)obj[7];
        long offerownerid = offowId.longValue();
        jobapp.setOfferownerId(offerownerid);
        jobapp.setOfferownerName((String)obj[8]);
        String isgrp = (String)obj[9];
        if ((!StringUtils.isNullOrEmpty(isgrp)) && (isgrp.equals("Y"))) {
          jobapp.setIsofferownerGroup("Y");
        } else {
          jobapp.setIsofferownerGroup("N");
        }
        jobapp.ownername = ((String)obj[10]);
        BigInteger ownerId = (BigInteger)obj[11];
        if (ownerId != null) {
          jobapp.ownerId = ownerId.longValue();
        }
        jobapp.ownernamegroup = ((String)obj[12]);
        jobapp.isGroup = ((String)obj[13]);
        BigInteger ownergroupId = (BigInteger)obj[14];
        if (ownergroupId != null) {
          jobapp.ownergroupId = ownergroupId.longValue();
        }
        BigInteger recruiterId = (BigInteger)obj[15];
        if (recruiterId != null) {
          jobapp.recruiterId = recruiterId.longValue();
        }
        jobapp.setRecruiter((String)obj[16]);
        jobapp.recruitergroup = ((String)obj[17]);
        logger.info("(String) obj[18]" + (String)obj[18]);
        jobapp.setJuuid((String)obj[18]);
        int basevalue = 19;
        if (!StringUtils.isNullOrEmpty(orgsqlgen))
        {
          if (isColumnPresent("DASHBOARD_RECRUITER_IN_OFFER_PROCESS", "hiringManager"))
          {
            jobapp.setHiringManager((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("DASHBOARD_RECRUITER_IN_OFFER_PROCESS", "orgName"))
          {
            jobapp.setOrgName((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("DASHBOARD_RECRUITER_IN_OFFER_PROCESS", "department"))
          {
            jobapp.setDepartment((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("DASHBOARD_RECRUITER_IN_OFFER_PROCESS", "projectcode"))
          {
            jobapp.setProjectcode((String)obj[basevalue]);
            basevalue += 1;
          }
        }
        jobapp = setApplicantListValues(obj, "DASHBOARD_RECRUITER_IN_OFFER_PROCESS", jobapp, basevalue);
        


        newapplicantList.add(jobapp);
      }
      String sqlcount = " select distinct ja.application_id from job_applications ja , job_requisition jr , user_group ug,user_group_users ugu where ja.jb_req_id = jr.jb_req_id  and ((jr.recruiter_id = :recruiter_id and jr.is_recruiter_group <> 'Y' )or (ja.offerownerid=:offerownerid  AND  ja.is_offer_owner_group <> 'Y')  or (ja.offerownerid= ug.user_group_id  AND ja.is_offer_owner_group = 'Y' and ugu.user_id=:offerownerid) or  (jr.recruiter_id= ug.user_group_id  AND jr.is_recruiter_group = 'Y' and ugu.user_id=:recruiter_id)) and ja.interview_state like '%Offer%' and  ja.interview_state <> 'Offer Accepted'  and ja.status='A' ";
      







      Query querycount = session.createSQLQuery(sqlcount);
      querycount.setLong("recruiter_id", user.getUserId());
      querycount.setLong("offerownerid", user.getUserId());
      List applicantListcount = querycount.list();
      
      dtbean.setValueList(newapplicantList);
      dtbean.setTotalcount(applicantListcount.size());
      
      logger.info("applicantListcount.size()" + applicantListcount.size());
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantsInOfferProcessByRecruiter()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return dtbean;
  }
  
  public int getCountOfApplicantsInOfferProcessByRecruiter(User user)
  {
    logger.info("Inside getCountOfApplicantsInOfferProcessByRecruiter method");
    
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String sql = "select ja.application_id,ja.full_name,ja.jb_req_id,ja.req_name,ja.interview_state,ja.offerreleasedate,ja.uuid,ja.offerownerId,ja.offerownerName from job_applications ja , job_requisition jr  where ja.jb_req_id = jr.jb_req_id  and (jr.recruiter_id = :recruiter_id or ja.offerownerid= :offerownerid )  and ja.interview_state like '%Offer%' and  ja.interview_state <> 'Offer Accepted' and ja.status='A' ";
      




      Query query = session.createSQLQuery(sql);
      query.setLong("recruiter_id", user.getUserId());
      query.setLong("offerownerid", user.getUserId());
      
      totaluser = query.list().size();
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfApplicantsInOfferProcessByRecruiter()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totaluser;
  }
  
  public int getCountOfApplicantsInOfferProcessByHiringMgr(User user)
  {
    logger.info("Inside getCountOfApplicantsInOfferProcessByHiringMgr method");
    
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String sql = "select ja.application_id,ja.full_name,ja.jb_req_id,ja.req_name,ja.interview_state,ja.offerreleasedate,ja.uuid,ja.offerownerId,ja.offerownerName from job_applications ja , job_requisition jr  where ja.jb_req_id = jr.jb_req_id  and jr.hiring_mgr_id = :hiring_mgr_id  and ja.interview_state like '%Offer%' and  ja.interview_state <> 'Offer Accepted' and ja.status='A' ";
      




      Query query = session.createSQLQuery(sql);
      query.setLong("hiring_mgr_id", user.getUserId());
      
      totaluser = query.list().size();
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfApplicantsInOfferProcessByHiringMgr()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totaluser;
  }
  
  public int getCountOfApplicantsInOfferProcessAdmin(User user)
  {
    logger.info("Inside getCountOfApplicantsInOfferProcessAdmin method");
    
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String sql = "select ja.application_id,ja.full_name,ja.jb_req_id,ja.req_name,ja.interview_state,ja.offerreleasedate,ja.uuid,ja.offerownerId,ja.offerownerName from job_applications ja , job_requisition jr  where ja.jb_req_id = jr.jb_req_id  and jr.super_user_key = :super_user_key  and ja.interview_state like '%Offer%' and  ja.interview_state <> 'Offer Accepted' and ja.status='A' ";
      




      Query query = session.createSQLQuery(sql);
      query.setLong("super_user_key", user.getSuper_user_key());
      
      totaluser = query.list().size();
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfApplicantsInOfferProcessAdmin()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totaluser;
  }
  
  public SalaryPlan getSalaryPlanByApplicantId(long applicantId)
  {
    logger.info("Inside getSalaryPlanByApplicantId method");
    SalaryPlan sl = new SalaryPlan();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String sql = " select s from SalaryPlan s , JobRequisition j, JobApplicant a  where s.salaryplanId=j.salaryplan.salaryplanId  and j.jobreqId=a.reqId  and a.applicantId= :applicantId";
      



      Query query = session.createQuery(sql);
      query.setLong("applicantId", applicantId);
      
      sl = (SalaryPlan)query.uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getSalaryPlanByApplicantId()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return sl;
  }
  
  public List searchApplicantsByVendorForPagination(int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside searchApplicantsByVendorForPagination method");
    List applicantList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = session.createCriteria(JobApplicant.class);
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      applicantList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on searchApplicantsByVendorForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return applicantList;
  }
  
  public List getAllInterviewsByUser(User user, String interviewdate)
  {
    logger.info("Inside getAllInterviewsByUser method");
    List applicanteventList = new ArrayList();
    List newapplicanteventList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = session.createCriteria(JobApplicationEvent.class).createAlias("owner", "owner");
      










      outer.add(Restrictions.disjunction().add(Restrictions.eq("owner.userId", new Long(user.getUserId()))).add(Restrictions.eq("createdBy", user.getUserName())));
      if ((!StringUtils.isNullOrEmpty(interviewdate)) && (!interviewdate.equals("null")))
      {
        String datepattern = DateUtil.dateformatstandard;
        System.out.println("datepattern" + datepattern);
        DateFormat format = new SimpleDateFormat(datepattern + " hh:mm:ss");
        
        java.util.Date aDate = null;
        java.util.Date bDate = null;
        
        aDate = format.parse(interviewdate + " 00:00:00");
        aDate.setDate(aDate.getDate() - 1);
        
        bDate = format.parse(interviewdate + " 23:59:00");
        bDate.setDate(bDate.getDate() + 1);
        
        logger.info("aDate" + aDate);
        logger.info("bDate" + bDate);
        
        outer.add(Expression.between("interviewDate", new java.util.Date(aDate.getTime()), new java.util.Date(bDate.getTime())));
      }
      outer.addOrder(Order.asc("interviewDate"));
      
      applicanteventList = outer.list();
      for (int i = 0; i < applicanteventList.size(); i++)
      {
        JobApplicationEvent jb = (JobApplicationEvent)applicanteventList.get(i);
        

        String convertedinterviewdate = DateUtil.convertSourceToTargetTimezone(jb.getInterviewDate(), user.getTimezone().getTimezoneCode(), user.getLocale());
        



        java.util.Date convertedintdate = DateUtil.convertStringDateToDate(convertedinterviewdate, DateUtil.getDatePatternFormat(user.getLocale()));
        


        java.util.Date inputdate = DateUtil.convertStringDateToDate(interviewdate, DateUtil.dateformatstandard);
        

        String withoutTimeintdate = convertedintdate.toString().replaceAll("(\\d\\d:){2}\\d\\d\\s", "");
        
        String withoutTimeinputdate = inputdate.toString().replaceAll("(\\d\\d:){2}\\d\\d\\s", "");
        

        logger.info("withoutTimeintdate" + withoutTimeintdate);
        
        logger.info("withoutTimeinputdate" + withoutTimeinputdate);
        if (withoutTimeintdate.equals(withoutTimeinputdate)) {
          newapplicanteventList.add(jb);
        }
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllInterviewsByUser()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return newapplicanteventList;
  }
  
  public List getAllInterviewsByUser(User user, String reviewerId, String interviewdate)
  {
    logger.info("Inside getAllInterviewsByUser method");
    List applicanteventList = new ArrayList();
    List newapplicanteventList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      long revId = 0L;
      if (StringUtils.isNullOrEmpty(reviewerId)) {
        revId = user.getUserId();
      } else {
        revId = new Long(reviewerId).longValue();
      }
      String createdBy = "";
      
      String sql = "select u.user_id,u.user_name from user_data u where user_id = :user_id";
      Query query1 = session.createSQLQuery(sql);
      query1.setLong("user_id", revId);
      List userList = query1.list();
      Object[] obj = (Object[])userList.get(0);
      createdBy = (String)obj[1];
      
      String hqlquery = " select distinct j from JobApplicationEvent j , UserGroup ug where  ((j.owner.userId = :currentuserid and j.isGroup <> 'Y') or (j.ownerGroup.usergrpId = ug.usergrpId and  j.isGroup='Y' and ug.users.userId =:currentuserid) or (j.createdBy = :createdBy)) ";
      if ((!StringUtils.isNullOrEmpty(interviewdate)) && (!interviewdate.equals("null")))
      {
        String datepattern = DateUtil.defaultdateformatforSQL;
        logger.info("datepattern" + datepattern);
        DateFormat format = new SimpleDateFormat(datepattern + " hh:mm:ss");
        
        java.util.Date aDate = null;
        java.util.Date bDate = null;
        
        aDate = format.parse(interviewdate + " 00:00:00");
        aDate.setDate(aDate.getDate() - 1);
        

        String fromdate = DateUtil.convertDateToStringDate(aDate, "MMM d, yyyy");
        
        bDate = format.parse(interviewdate + " 23:59:00");
        bDate.setDate(bDate.getDate() + 2);
        
        String todate = DateUtil.convertDateToStringDate(bDate, "MMMM d, yyyy");
        
        logger.info("fromdate" + fromdate);
        logger.info("todate" + todate);
        
        hqlquery = hqlquery + " and DATE_FORMAT( j.interviewDate, '%M %e, %Y') >= " + "'" + fromdate + "'" + " and DATE_FORMAT( j.interviewDate, '%M %e, %Y') <= " + "'" + todate + "'";
      }
      hqlquery = hqlquery + " order by j.interviewDate asc";
      

      logger.info("final sql" + hqlquery);
      logger.info("revId" + revId);
      Query query = session.createQuery(hqlquery);
      
      query.setLong("currentuserid", revId);
      query.setString("createdBy", createdBy);
      




























      applicanteventList = query.list();
      
      logger.info(query.toString());
      
      logger.info("applicanteventList" + applicanteventList.size());
      for (int i = 0; i < applicanteventList.size(); i++)
      {
        JobApplicationEvent jb = (JobApplicationEvent)applicanteventList.get(i);
        

        String convertedinterviewdate = DateUtil.convertSourceToTargetTimezone(jb.getInterviewDate(), user.getTimezone().getTimezoneCode(), user.getLocale());
        



        java.util.Date convertedintdate = DateUtil.convertStringDateToDate(convertedinterviewdate, DateUtil.getDatePatternFormat(user.getLocale()));
        


        java.util.Date inputdate = DateUtil.convertStringDateToDate(interviewdate, DateUtil.dateformatstandard);
        

        String withoutTimeintdate = convertedintdate.toString().replaceAll("(\\d\\d:){2}\\d\\d\\s", "");
        
        String withoutTimeinputdate = inputdate.toString().replaceAll("(\\d\\d:){2}\\d\\d\\s", "");
        

        logger.info("withoutTimeintdate" + withoutTimeintdate);
        
        logger.info("withoutTimeinputdate" + withoutTimeinputdate);
        if (withoutTimeintdate.equals(withoutTimeinputdate)) {
          newapplicanteventList.add(jb);
        }
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllInterviewsByUser()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return newapplicanteventList;
  }
  
  public List getAllInterviewsByUserOrgDept(User user, String reviewerId, String interviewdate, String orgId, String departmentId)
  {
    logger.info("Inside getAllInterviewsByUser method");
    List applicanteventList = new ArrayList();
    List newapplicanteventList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      logger.info("reviewerId" + reviewerId);
      logger.info("orgId" + orgId);
      logger.info("departmentId" + departmentId);
      long revId = 0L;
      long orgid = 0L;
      long departmentid = 0L;
      if ((!StringUtils.isNullOrEmpty(reviewerId)) && (!reviewerId.equals("0")))
      {
        revId = new Long(reviewerId).longValue();
      }
      else
      {
        if ((!StringUtils.isNullOrEmpty(orgId)) && (!orgId.equals("null")) && (!orgId.equals("0"))) {
          orgid = new Long(orgId).longValue();
        }
        if ((!StringUtils.isNullOrEmpty(departmentId)) && (!departmentId.equals("null")) && (!departmentId.equals("0"))) {
          departmentid = new Long(departmentId).longValue();
        }
      }
      String createdBy = "";
      if (revId != 0L)
      {
        String sql = "select u.user_id,u.user_name from user_data u where user_id = :user_id";
        Query query1 = session.createSQLQuery(sql);
        query1.setLong("user_id", revId);
        List userList = query1.list();
        Object[] obj = (Object[])userList.get(0);
        createdBy = (String)obj[1];
      }
      String hqlquery = "";
      if (revId != 0L)
      {
        hqlquery = " select distinct j from JobApplicationEvent j , UserGroup ug where  ((j.owner.userId = :currentuserid and j.isGroup <> 'Y') or (j.ownerGroup.usergrpId = ug.usergrpId and  j.isGroup='Y' and ug.users.userId =:currentuserid) or (j.createdBy = :createdBy)) ";
      }
      else if (orgid != 0L)
      {
        hqlquery = " select distinct j from JobApplicationEvent j ,JobApplicant ja, JobRequisition jr where  j.applicant.applicantId=ja.applicantId  and ja.reqId=jr.jobreqId  and jr.organization.orgId= :orgId";
        if (departmentid != 0L) {
          hqlquery = hqlquery + " and jr.department.departmentId= :departmentId";
        }
      }
      if ((!StringUtils.isNullOrEmpty(interviewdate)) && (!interviewdate.equals("null")))
      {
        String datepattern = DateUtil.defaultdateformatforSQL;
        logger.info("datepattern" + datepattern);
        DateFormat format = new SimpleDateFormat(datepattern + " hh:mm:ss");
        
        java.util.Date aDate = null;
        java.util.Date bDate = null;
        
        aDate = format.parse(interviewdate + " 00:00:00");
        


        String fromdate = DateUtil.convertDateToStringDate(aDate, "MMM d, yyyy");
        
        bDate = format.parse(interviewdate + " 23:59:00");
        

        String todate = DateUtil.convertDateToStringDate(bDate, "MMMM d, yyyy");
        
        logger.info("fromdate" + fromdate);
        logger.info("todate" + todate);
        
        hqlquery = hqlquery + " and (DATE_FORMAT( j.interviewDate, '%M %e, %Y') = " + "'" + fromdate + "'" + " or DATE_FORMAT( j.interviewDate, '%M %e, %Y') = " + "'" + todate + "')";
      }
      hqlquery = hqlquery + " order by j.interviewDate asc";
      

      logger.info("final sql" + hqlquery);
      logger.info("revId" + revId);
      Query query = session.createQuery(hqlquery);
      if (revId != 0L)
      {
        query.setLong("currentuserid", revId);
        query.setString("createdBy", createdBy);
      }
      else if (orgid != 0L)
      {
        query.setLong("orgId", orgid);
        if (departmentid != 0L) {
          query.setLong("departmentId", departmentid);
        }
      }
      applicanteventList = query.list();
      logger.info(query.toString());
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllInterviewsByUser()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return applicanteventList;
  }
  
  public List getAllInterviewsByDateAndHiringManager(User user, long hiringMgrId, String interviewdate)
  {
    logger.info("Inside getAllInterviewsByDateAndHiringManager method");
    List applicanteventList = new ArrayList();
    List newapplicanteventList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      String hqlquery = " select distinct j from JobApplicationEvent j ,JobApplicant ja, JobRequisition jr where  j.applicant.applicantId=ja.applicantId  and ja.reqId=jr.jobreqId  and jr.hiringmgr.userId = :hiring_mgr_id ";
      if ((!StringUtils.isNullOrEmpty(interviewdate)) && (!interviewdate.equals("null")))
      {
        String datepattern = DateUtil.defaultdateformatforSQL;
        logger.info("datepattern" + datepattern);
        DateFormat format = new SimpleDateFormat(datepattern + " hh:mm:ss");
        
        java.util.Date aDate = null;
        java.util.Date bDate = null;
        
        aDate = format.parse(interviewdate + " 00:00:00");
        aDate.setDate(aDate.getDate());
        

        String fromdate = DateUtil.convertDateToStringDate(aDate, "MMM d, yyyy");
        
        bDate = format.parse(interviewdate + " 23:59:00");
        bDate.setDate(bDate.getDate());
        
        String todate = DateUtil.convertDateToStringDate(bDate, "MMMM d, yyyy");
        
        logger.info("fromdate" + fromdate);
        logger.info("todate" + todate);
        
        hqlquery = hqlquery + " and DATE_FORMAT( j.interviewDate, '%M %e, %Y') >= " + "'" + fromdate + "'" + " and DATE_FORMAT( j.interviewDate, '%M %e, %Y') <= " + "'" + todate + "'";
      }
      hqlquery = hqlquery + " order by j.interviewDate desc";
      


      Query query = session.createQuery(hqlquery);
      
      query.setLong("hiring_mgr_id", hiringMgrId);
      

      applicanteventList = query.list();
      
      logger.info(query.toString());
      
      logger.info("applicanteventList" + applicanteventList.size());
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllInterviewsByDateAndHiringManager()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return applicanteventList;
  }
  
  public List getAllInterviewsByDateAdmin(User user, String interviewdate)
  {
    logger.info("Inside getAllInterviewsByDateAdmin method");
    List applicanteventList = new ArrayList();
    List newapplicanteventList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      String hqlquery = " select distinct j from JobApplicationEvent j ,JobApplicant ja where  j.applicant.applicantId=ja.applicantId  and ja.super_user_key = :super_user_key ";
      if ((!StringUtils.isNullOrEmpty(interviewdate)) && (!interviewdate.equals("null")))
      {
        String datepattern = DateUtil.defaultdateformatforSQL;
        logger.info("datepattern" + datepattern);
        DateFormat format = new SimpleDateFormat(datepattern + " hh:mm:ss");
        
        java.util.Date aDate = null;
        java.util.Date bDate = null;
        
        aDate = format.parse(interviewdate + " 00:00:00");
        aDate.setDate(aDate.getDate());
        

        String fromdate = DateUtil.convertDateToStringDate(aDate, "MMM d, yyyy");
        
        bDate = format.parse(interviewdate + " 23:59:00");
        bDate.setDate(bDate.getDate());
        
        String todate = DateUtil.convertDateToStringDate(bDate, "MMMM d, yyyy");
        
        logger.info("fromdate" + fromdate);
        logger.info("todate" + todate);
        
        hqlquery = hqlquery + " and DATE_FORMAT( j.interviewDate, '%M %e, %Y') >= " + "'" + fromdate + "'" + " and DATE_FORMAT( j.interviewDate, '%M %e, %Y') <= " + "'" + todate + "'";
      }
      hqlquery = hqlquery + " order by j.interviewDate desc";
      


      Query query = session.createQuery(hqlquery);
      
      query.setLong("super_user_key", user.getSuper_user_key());
      

      applicanteventList = query.list();
      
      logger.info(query.toString());
      
      logger.info("applicanteventList" + applicanteventList.size());
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllInterviewsByDateAdmin()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return applicanteventList;
  }
  
  public List getAllInterviewsByDateByRecruiter(User user, long recruiter_id, String interviewdate)
  {
    logger.info("Inside getAllInterviewsByDateAndHiringManager method");
    List applicanteventList = new ArrayList();
    List newapplicanteventList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      String hqlquery = " select distinct j from JobApplicationEvent j ,JobApplicant ja, JobRequisition jr , UserGroup ug where  j.applicant.applicantId=ja.applicantId  and ja.reqId=jr.jobreqId  and ((j.isGroup <> 'Y' and j.owner.userId = :recruiter_id) or (j.isGroup = 'Y' and j.ownerGroup.usergrpId = ug.usergrpId and ug.users.userId =:recruiter_id))";
      if ((!StringUtils.isNullOrEmpty(interviewdate)) && (!interviewdate.equals("null")))
      {
        String datepattern = DateUtil.defaultdateformatforSQL;
        logger.info("datepattern" + datepattern);
        DateFormat format = new SimpleDateFormat(datepattern + " hh:mm:ss");
        
        java.util.Date aDate = null;
        java.util.Date bDate = null;
        
        aDate = format.parse(interviewdate + " 00:00:00");
        aDate.setDate(aDate.getDate());
        

        String fromdate = DateUtil.convertDateToStringDate(aDate, "MMM d, yyyy");
        
        bDate = format.parse(interviewdate + " 23:59:00");
        bDate.setDate(bDate.getDate());
        
        String todate = DateUtil.convertDateToStringDate(bDate, "MMMM d, yyyy");
        
        logger.info("fromdate" + fromdate);
        logger.info("todate" + todate);
        
        hqlquery = hqlquery + " and DATE_FORMAT( j.interviewDate, '%M %e, %Y') >= " + "'" + fromdate + "'" + " and DATE_FORMAT( j.interviewDate, '%M %e, %Y') <= " + "'" + todate + "'";
      }
      hqlquery = hqlquery + " order by j.interviewDate desc";
      


      Query query = session.createQuery(hqlquery);
      
      query.setLong("recruiter_id", recruiter_id);
      

      applicanteventList = query.list();
      
      logger.info(query.toString());
      
      logger.info("applicanteventList" + applicanteventList.size());
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllInterviewsByDateAndHiringManager()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return applicanteventList;
  }
  
  public int getCountOfAllApplicants()
  {
    logger.info("Inside getCountOfAllApplicants method");
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria criteria = session.createCriteria(JobApplicant.class).add(Restrictions.ne("status", "R"));
      
      criteria.setProjection(Projections.rowCount());
      totaluser = ((Integer)criteria.list().get(0)).intValue();
      System.out.println("totaluser" + totaluser);
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfAllApplicants()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totaluser;
  }
  
  public int getCountOfCommentsByLoginUser(long applicantId, long userId)
  {
    logger.info("Inside getCountOfCommentsByLoginUser method");
    int totalcomments = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      Criteria criteria = session.createCriteria(ApplicantComment.class).add(Restrictions.eq("applicantId", Long.valueOf(applicantId))).add(Restrictions.eq("byUserId", Long.valueOf(userId)));
      

      criteria.setProjection(Projections.rowCount());
      totalcomments = ((Integer)criteria.list().get(0)).intValue();
      System.out.println("totalcomments : " + totalcomments);
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfCommentsByLoginUser()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totalcomments;
  }
  
  public int getCountOfAllApplicantsByRequisitionId(long reqid)
  {
    logger.info("Inside getCountOfAllApplicants method");
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria criteria = session.createCriteria(JobApplicant.class).add(Restrictions.eq("reqId", Long.valueOf(reqid)));
      
      criteria.setProjection(Projections.rowCount());
      totaluser = ((Integer)criteria.list().get(0)).intValue();
      System.out.println("totaluser" + totaluser);
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfAllApplicants()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totaluser;
  }
  
  public int getCountOfAllApplicantsByRequisitionIdandVendorId(long reqid, long userid)
  {
    logger.info("Inside getCountOfAllApplicants method");
    logger.info("userId : " + userid);
    logger.info("reqid : " + reqid);
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria criteria = session.createCriteria(JobApplicant.class).add(Restrictions.eq("reqId", Long.valueOf(reqid))).add(Restrictions.eq("vendorId", Long.valueOf(userid))).add(Restrictions.eq("status", "A"));
      



      criteria.setProjection(Projections.rowCount());
      totaluser = ((Integer)criteria.list().get(0)).intValue();
      System.out.println("totaluser : " + totaluser);
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfAllApplicants()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totaluser;
  }
  
  public int getCountOfMyApplicants(User user)
  {
    logger.info("Inside getCountOfMyApplicants method");
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = session.createCriteria(JobApplicant.class).createAlias("owner", "owner");
      








      outer.add(Restrictions.disjunction().add(Restrictions.eq("owner.userId", new Long(user.getUserId()))).add(Restrictions.eq("createdBy", user.getUserName())).add(Restrictions.eq("interview_organizer_id", new Long(user.getUserId()))));
      







      outer.setProjection(Projections.rowCount());
      totaluser = ((Integer)outer.list().get(0)).intValue();
      System.out.println("totaluser" + totaluser);
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfMyApplicants()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totaluser;
  }
  
  public int getCountOfsearchOwnApplicant(User user, String fullname, String reqid, String prevorg, String email, String appdate, String cri, String applicantNo, String interviewstate)
  {
    logger.info("Inside getCountOfsearchOwnApplicant method");
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String hqlquery = " select distinct j from JobApplicant j , UserGroup ug where  ((j.owner.userId = :currentuserid and j.isGroup <> 'Y') or (j.ownerGroup.usergrpId = ug.usergrpId and  j.isGroup='Y' and ug.users.userId =:currentuserid)) ";
      if ((!StringUtils.isNullOrEmpty(fullname)) && (!fullname.equals("null"))) {
        hqlquery = hqlquery + " and j.fullName like :fullName";
      }
      if ((!StringUtils.isNullOrEmpty(applicantNo)) && (!applicantNo.equals("0")) && (!applicantNo.equals("null"))) {
        hqlquery = hqlquery + " and j.applicantId = :applicantId";
      }
      if ((!StringUtils.isNullOrEmpty(email)) && (!email.equals("null"))) {
        hqlquery = hqlquery + " and j.email like :email";
      }
      if ((!StringUtils.isNullOrEmpty(interviewstate)) && (!interviewstate.equals("NoValue")) && (!interviewstate.equals("null"))) {
        if (interviewstate.equalsIgnoreCase("Offer Accepted")) {
          hqlquery = hqlquery + " and j.interviewState = :interviewState";
        } else {
          hqlquery = hqlquery + " and j.interviewState like :interviewState";
        }
      }
      if ((!StringUtils.isNullOrEmpty(prevorg)) && (!prevorg.equals("null"))) {
        hqlquery = hqlquery + " and j.previousOrganization like :previousOrganization";
      }
      Query query = session.createQuery(hqlquery);
      
      query.setLong("currentuserid", user.getUserId());
      if ((!StringUtils.isNullOrEmpty(fullname)) && (!fullname.equals("null"))) {
        query.setString("fullName", '%' + fullname + '%');
      }
      if ((!StringUtils.isNullOrEmpty(applicantNo)) && (!applicantNo.equals("0")) && (!applicantNo.equals("null"))) {
        query.setLong("applicantId", new Long(applicantNo).longValue());
      }
      if ((!StringUtils.isNullOrEmpty(email)) && (!email.equals("null"))) {
        query.setString("email", '%' + email + '%');
      }
      if ((!StringUtils.isNullOrEmpty(interviewstate)) && (!interviewstate.equals("NoValue")) && (!interviewstate.equals("null"))) {
        if (interviewstate.equalsIgnoreCase("Offer Accepted")) {
          query.setString("interviewState", interviewstate);
        } else {
          query.setString("interviewState", '%' + interviewstate + '%');
        }
      }
      if ((!StringUtils.isNullOrEmpty(prevorg)) && (!prevorg.equals("null"))) {
        query.setString("previousOrganization", '%' + prevorg + '%');
      }
      totaluser = query.list().size();
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfsearchOwnApplicant()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totaluser;
  }
  
  public int getCountOfsearchOwnAgencyApplicant(User agency, String fullname, String reqid, String prevorg, String email, String appdate, String cri, String applicantNo, String interviewState)
  {
    logger.info("Inside getCountOfsearchOwnAgencyApplicant method");
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = session.createCriteria(JobApplicant.class);
      
      outer.add(Restrictions.eq("resumesourcetype.resumeSourceTypeId", Integer.valueOf(5)));
      outer.add(Restrictions.disjunction().add(Restrictions.eq("resumeSourcesId", Long.valueOf(agency.getUserId()))).add(Restrictions.eq("vendorId", Long.valueOf(agency.getUserId()))));
      if ((!StringUtils.isNullOrEmpty(fullname)) && (!fullname.equals("null"))) {
        outer.add(Restrictions.like("fullName", "%" + fullname + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(applicantNo)) && (!applicantNo.equals("0")) && (!applicantNo.equals("null"))) {
        outer.add(Restrictions.eq("applicantId", new Long(applicantNo)));
      }
      if ((!StringUtils.isNullOrEmpty(reqid)) && (!reqid.equals("0")) && (!reqid.equals("null"))) {
        outer.add(Restrictions.eq("reqId", new Long(reqid)));
      }
      if ((!StringUtils.isNullOrEmpty(prevorg)) && (!prevorg.equals("null"))) {
        outer.add(Restrictions.like("previousOrganization", "%" + prevorg + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(interviewState)) && (!interviewState.equals("NoValue")) && (!interviewState.equals("null"))) {
        if (interviewState.equalsIgnoreCase("Offer Accepted")) {
          outer.add(Restrictions.eq("interviewState", interviewState));
        } else if (interviewState.equalsIgnoreCase("Application Submitted")) {
          outer.add(Restrictions.eq("interviewState", interviewState));
        } else {
          outer.add(Restrictions.like("interviewState", "%" + interviewState + "%"));
        }
      }
      if ((!StringUtils.isNullOrEmpty(email)) && (!email.equals("null"))) {
        outer.add(Restrictions.like("email", "%" + email + "%"));
      }
      logger.info("appdate" + appdate);
      if ((!StringUtils.isNullOrEmpty(appdate)) && (!appdate.equals("null")))
      {
        String datepattern = Constant.getValue("defaultdateformat");
        
        DateFormat format = new SimpleDateFormat(datepattern + " hh:mm:ss");
        
        java.util.Date aDate = format.parse(appdate + " 00:00:00");
        java.util.Date bDate = format.parse(appdate + " 23:59:00");
        if (cri.equals("on")) {
          outer.add(Expression.between("appliedDate", new java.util.Date(aDate.getTime()), new java.util.Date(bDate.getTime())));
        }
        if (cri.equals("after")) {
          outer.add(Expression.gt("appliedDate", new java.util.Date(aDate.getTime())));
        }
        if (cri.equals("before")) {
          outer.add(Expression.lt("appliedDate", new java.util.Date(aDate.getTime())));
        }
      }
      outer.setProjection(Projections.rowCount());
      totaluser = ((Integer)outer.list().get(0)).intValue();
      System.out.println("totaluser" + totaluser);
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfsearchOwnAgencyApplicant()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totaluser;
  }
  
  public int getCountOfsearchOwnRefferalApplicant(RefferalEmployee emp, String fullname, String reqid, String prevorg, String email, String appdate, String cri, String applicantNo)
  {
    logger.info("Inside getCountOfsearchOwnRefferalApplicant method");
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = session.createCriteria(JobApplicant.class);
      
      outer.add(Restrictions.disjunction().add(Restrictions.eq("employeecode", emp.getEmployeecode())).add(Restrictions.eq("referrerEmail", emp.getEmployeeemail())));
      if ((!StringUtils.isNullOrEmpty(fullname)) && (!fullname.equals("null"))) {
        outer.add(Restrictions.like("fullName", "%" + fullname + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(applicantNo)) && (!applicantNo.equals("0")) && (!applicantNo.equals("null"))) {
        outer.add(Restrictions.eq("applicantId", new Long(applicantNo)));
      }
      if ((!StringUtils.isNullOrEmpty(reqid)) && (!reqid.equals("0")) && (!reqid.equals("null"))) {
        outer.add(Restrictions.eq("reqId", new Long(reqid)));
      }
      if ((!StringUtils.isNullOrEmpty(prevorg)) && (!prevorg.equals("null"))) {
        outer.add(Restrictions.like("previousOrganization", "%" + prevorg + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(email)) && (!email.equals("null"))) {
        outer.add(Restrictions.like("email", "%" + email + "%"));
      }
      logger.info("appdate" + appdate);
      if ((!StringUtils.isNullOrEmpty(appdate)) && (!appdate.equals("null")))
      {
        String datepattern = Constant.getValue("defaultdateformat");
        
        DateFormat format = new SimpleDateFormat(datepattern + " hh:mm:ss");
        
        java.util.Date aDate = format.parse(appdate + " 00:00:00");
        java.util.Date bDate = format.parse(appdate + " 23:59:00");
        if (cri.equals("on")) {
          outer.add(Expression.between("appliedDate", new java.util.Date(aDate.getTime()), new java.util.Date(bDate.getTime())));
        }
        if (cri.equals("after")) {
          outer.add(Expression.gt("appliedDate", new java.util.Date(aDate.getTime())));
        }
        if (cri.equals("before")) {
          outer.add(Expression.lt("appliedDate", new java.util.Date(aDate.getTime())));
        }
      }
      outer.setProjection(Projections.rowCount());
      totaluser = ((Integer)outer.list().get(0)).intValue();
      System.out.println("totaluser" + totaluser);
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfsearchOwnRefferalApplicant()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totaluser;
  }
  
  public int getCountOfsearchOwnApplicant(User user)
  {
    logger.info("Inside getCountOfsearchOwnApplicant method");
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = session.createCriteria(JobApplicant.class).createAlias("owner", "owner");
      

      outer.add(Restrictions.disjunction().add(Restrictions.eq("owner.userId", new Long(user.getUserId()))).add(Restrictions.eq("createdBy", user.getUserName())));
      




      outer.setProjection(Projections.rowCount());
      totaluser = ((Integer)outer.list().get(0)).intValue();
      System.out.println("totaluser" + totaluser);
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfsearchOwnApplicant()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totaluser;
  }
  
  public int getCountOfsearchOwnRefferalApplicant(RefferalEmployee emp)
  {
    logger.info("Inside getCountOfsearchOwnRefferalApplicant method");
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = session.createCriteria(JobApplicant.class);
      
      outer.add(Restrictions.disjunction().add(Restrictions.eq("employeecode", emp.getEmployeecode())).add(Restrictions.eq("createdBy", emp.getEmployeeemail())).add(Restrictions.eq("referrerEmail", emp.getEmployeeemail())));
      





      outer.setProjection(Projections.rowCount());
      totaluser = ((Integer)outer.list().get(0)).intValue();
      System.out.println("totaluser" + totaluser);
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfsearchOwnRefferalApplicant()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totaluser;
  }
  
  public int getCountOfsearchOwnAgencyApplicant(User agency)
  {
    logger.info("Inside getCountOfsearchOwnAgencyApplicant method");
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = session.createCriteria(JobApplicant.class);
      outer.add(Restrictions.eq("vendorId", Long.valueOf(agency.getUserId())));
      
      outer.setProjection(Projections.rowCount());
      totaluser = ((Integer)outer.list().get(0)).intValue();
      System.out.println("totaluser" + totaluser);
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfsearchOwnAgencyApplicant()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totaluser;
  }
  
  public int getCountOfApplicantsTrackingByStatus(User user, String status)
  {
    logger.info("Inside getCountOfApplicantsTrackingByStatus method");
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = session.createCriteria(JobApplicant.class);
      if ((!StringUtils.isNullOrEmpty(status)) && (status.equals("Application Submitted")))
      {
        outer.add(Restrictions.eq("createdBy", user.getUserName()));
        outer.add(Restrictions.eq("interviewState", status)).add(Restrictions.eq("status", "A"));
      }
      else if ((!StringUtils.isNullOrEmpty(status)) && (status.equals("Rejected")))
      {
        outer.add(Restrictions.like("interviewState", "%" + status + "%"));
        
        outer.add(Restrictions.eq("interview_organizer_id", new Long(user.getUserId())));
      }
      else if ((!StringUtils.isNullOrEmpty(status)) && (status.equals("Reference Check")))
      {
        outer.add(Restrictions.eq("isreferenceadded", Integer.valueOf(1)));
        outer.add(Restrictions.eq("interview_organizer_id", new Long(user.getUserId())));
      }
      else if ((!StringUtils.isNullOrEmpty(status)) && (status.equals("OnHold")))
      {
        outer.add(Restrictions.like("interviewState", "%" + status + "%"));
        
        outer.add(Restrictions.eq("interview_organizer_id", new Long(user.getUserId())));
      }
      else
      {
        outer.add(Restrictions.eq("interviewState", status)).add(Restrictions.eq("status", "A"));
        
        outer.add(Restrictions.eq("interview_organizer_id", new Long(user.getUserId())));
      }
      outer.setProjection(Projections.rowCount());
      totaluser = ((Integer)outer.list().get(0)).intValue();
      System.out.println("totaluser" + totaluser);
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfApplicantsTrackingByStatus()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totaluser;
  }
  
  public int getCountOfsearchApplicantsByVendor(User user, ApplicantSearchCriteria criteria)
  {
    logger.info("Inside getCountOfsearchApplicantsByVendor method");
    
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String sqlgen = getSQLApplicants("ALL_APP_SEARCH_SCREEN");
      String sql = "select ja.application_id,u.first_name, u.last_name,ja.uuid,ja.jb_req_id,ja.offerownerId,ja.full_name," + sqlgen + " from job_applications ja , job_requisition jr , user_data u " + " where ja.jb_req_id = jr.jb_req_id ";
      



      String sql1 = buildSQLQueryforSearch(user, criteria);
      sql = sql + sql1;
      
      logger.info("sql" + sql);
      Query query = session.createSQLQuery(sql);
      
      totaluser = query.list().size();
    }
    catch (Exception e)
    {
      logger.error("Exception on searchApplicantsByVendorForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totaluser;
  }
  
  public int getCountOfsearchOnBoardApplicants(User user, ApplicantSearchCriteria criteria)
  {
    logger.info("Inside getCountOfsearchOnBoardApplicants method");
    
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String sqlgen = getSQLApplicants("ON_BOARDING_SEARCH_SCREEN");
      String sql = "select ja.application_id  from job_applications ja , job_requisition jr  where ja.jb_req_id = jr.jb_req_id  and jr.super_user_key = :super_user_key ";
      


      criteria.setScreenName("ON_BOARDING_SEARCH_SCREEN");
      String sql1 = buildSQLQueryforSearch(user, criteria);
      sql = sql + sql1;
      
      logger.info("sql" + sql);
      Query query = session.createSQLQuery(sql);
      query.setLong("super_user_key", user.getSuper_user_key());
      totaluser = query.list().size();
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfsearchOnBoardApplicants()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totaluser;
  }
  
  public int getCountOfsearchApplicantsByVendor()
  {
    logger.info("Inside getCountOfsearchApplicantsByVendor method");
    
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = session.createCriteria(JobApplicant.class);
      
      outer.setProjection(Projections.rowCount());
      totaluser = ((Integer)outer.list().get(0)).intValue();
      System.out.println("totaluser" + totaluser);
    }
    catch (Exception e)
    {
      logger.error("Exception on searchApplicantsByVendorForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totaluser;
  }
  
  public JobApplicant saveApplicant(JobApplicant applicant)
  {
    logger.info("Inside saveApplicant method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      session.save(applicant);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveApplicant()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return applicant;
  }
  
  public ApplicantScoring saveApplicantScoring(ApplicantScoring appscore)
  {
    logger.info("Inside saveApplicantScoring method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      session.save(appscore);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveApplicantScoring()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return appscore;
  }
  
  public List getApplicantScoringsByApplicantId(long appplicantId)
  {
    logger.info("Inside getApplicantScoringsByApplicantId method");
    List appscoreList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = session.createCriteria(ApplicantScoring.class).add(Restrictions.eq("applicantId", Long.valueOf(appplicantId)));
      
      appscoreList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantScoringsByApplicantId()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return appscoreList;
  }
  
  public ApplicantActivity saveApplicantActivity(ApplicantActivity activity)
  {
    logger.info("Inside saveApplicantActivity method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      session.save(activity);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveApplicantActivity()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return activity;
  }
  
  public JobApplicationEvent saveApplicationEvent(JobApplicationEvent event)
  {
    logger.info("Inside saveApplicationEvent method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      session.save(event);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveApplicationEvent()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return event;
  }
  
  public SearchApplicant saveApplicantSearch(SearchApplicant search)
  {
    logger.info("Inside saveApplicantSearch method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      session.save(search);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveApplicantSearch()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return search;
  }
  
  public SearchApplicantQuestions saveSearchApplicantQuestions(SearchApplicantQuestions sq)
  {
    logger.info("Inside saveSearchApplicantQuestions method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      session.save(sq);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveSearchApplicantQuestions()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return sq;
  }
  
  public SearchApplicant updateApplicantSearch(SearchApplicant search)
  {
    logger.info("Inside updateApplicantSearch method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      session.update(search);
    }
    catch (Exception e)
    {
      logger.error("Exception on updateApplicantSearch()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return search;
  }
  
  public void saveCustomVariableListForSearch(List variabledatalist)
  {
    logger.info("Inside saveCustomVariableListForSearch method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      for (int i = 0; i < variabledatalist.size(); i++)
      {
        SearchApplicantCustomFields sacf = (SearchApplicantCustomFields)variabledatalist.get(i);
        session.save(sacf);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on saveCustomVariableListForSearch()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public List<SearchApplicantCustomFields> getCustomVariableListDataForSearch(long savesearchid)
  {
    logger.info("Inside getCustomVariableListDataForSearch method");
    List<SearchApplicantCustomFields> cList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      cList = session.createCriteria(SearchApplicantCustomFields.class).add(Restrictions.eq("searchappid", Long.valueOf(savesearchid))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getCustomVariableListDataForSearch()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return cList;
  }
  
  public List getSavedApplicantSearchesByUserid(long id)
  {
    logger.info("Inside getSavedApplicantSearchesByUserid method");
    List searchList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      searchList = session.createCriteria(SearchApplicant.class).add(Restrictions.eq("saveduserid", Long.valueOf(id))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getSavedApplicantSearchesByUserid()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return searchList;
  }
  
  public SearchApplicant getSavedApplicantSearchesBySearchId(String id)
  {
    logger.info("Inside getSavedApplicantSearchesBySearchId method");
    SearchApplicant search = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      search = (SearchApplicant)session.createCriteria(SearchApplicant.class).add(Restrictions.eq("searchappId", new Long(id))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getSavedApplicantSearchesBySearchId()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return search;
  }
  
  public List<SearchApplicantQuestions> getSearchApplicantQuestions(long id)
  {
    logger.info("Inside getSearchApplicantQuestions method");
    List<SearchApplicantQuestions> alist = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      alist = session.createCriteria(SearchApplicantQuestions.class).add(Restrictions.eq("searchappid", Long.valueOf(id))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getSearchApplicantQuestions()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return alist;
  }
  
  public SearchApplicant getSavedApplicantSearchesBySearchUUID(String uuid)
  {
    logger.info("Inside getSavedApplicantSearchesBySearchUUID method");
    SearchApplicant search = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      search = (SearchApplicant)session.createCriteria(SearchApplicant.class).add(Restrictions.eq("searchuuid", uuid)).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getSavedApplicantSearchesBySearchUUID()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return search;
  }
  
  public void deleteApplicantSearch(SearchApplicant search)
  {
    logger.info("Inside deleteApplicantSearch method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      session.delete(search);
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteApplicantSearch()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void deleteSearchApplicantQuestions(String uuid)
  {
    logger.info("Inside deleteSearchApplicantQuestions method");
    ApplicantAttachments appattch = null;
    
    org.hibernate.Session session = null;
    try
    {
      String hql = "delete from SearchApplicantQuestions where uuid = :uuid";
      Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
      query.setString("uuid", uuid);
      int rowCount = query.executeUpdate();
    }
    catch (Exception e)
    {
      int rowCount;
      logger.error("Exception on deleteSearchApplicantQuestions()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void deleteCustomVariableListForSearch(long searchappid)
  {
    logger.info("Inside deleteCustomVariableListForSearch method");
    ApplicantAttachments appattch = null;
    
    org.hibernate.Session session = null;
    try
    {
      String hql = "delete from SearchApplicantCustomFields where searchappid = :searchappid";
      Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
      query.setLong("searchappid", searchappid);
      int rowCount = query.executeUpdate();
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteCustomVariableListForSearch()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public List getSavedApplicantSearchesByUser(long userId, String savedsearchtype)
  {
    logger.info("Inside getSavedApplicantSearchesByUser method");
    List list = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      list = session.createCriteria(SearchApplicant.class).add(Restrictions.eq("saveduserid", Long.valueOf(userId))).add(Restrictions.eq("saveshare", savedsearchtype)).addOrder(Order.asc("createdDate")).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getSavedApplicantSearchesByUser()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return list;
  }
  
  public void deleteSavedApplicantSearch(String id, long userid)
  {
    logger.info("Inside deleteSavedApplicantSearch method");
    SearchApplicant search = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      search = (SearchApplicant)session.createCriteria(SearchApplicant.class).add(Restrictions.eq("searchuuid", id)).add(Restrictions.eq("saveduserid", Long.valueOf(userid))).uniqueResult();
      


      session.delete(search);
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteSavedApplicantSearch()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public JobApplicant getApplicantDetails(String id)
  {
    logger.info("Inside getApplicantDetails method");
    
    JobApplicant applicant = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      applicant = (JobApplicant)session.createCriteria(JobApplicant.class).add(Restrictions.eq("applicantId", new Long(id))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return applicant;
  }
  
  public JobApplicant getApplicantByApplicantNumber(long applicant_number, long superUserKey)
  {
    logger.info("Inside getApplicantByApplicantNumber method");
    
    JobApplicant applicant = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      applicant = (JobApplicant)session.createCriteria(JobApplicant.class).add(Restrictions.eq("applicant_number", Long.valueOf(applicant_number))).add(Restrictions.eq("super_user_key", Long.valueOf(superUserKey))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantByApplicantNumber()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return applicant;
  }
  
  public List getApplicantByApplicantName(String name, long superUserKey)
  {
    logger.info("Inside getApplicantByApplicantName method");
    List applist = new ArrayList();
    JobApplicant applicant = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      applist = session.createCriteria(JobApplicant.class).add(Restrictions.eq("fullName", name)).add(Restrictions.eq("super_user_key", Long.valueOf(superUserKey))).addOrder(Order.desc("createdDate")).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantByApplicantName()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return applist;
  }
  
  public JobApplicant getApplicantDetailsByUUID(String uuid)
  {
    logger.info("Inside getApplicantDetailsByUUID method");
    
    JobApplicant applicant = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      applicant = (JobApplicant)session.createCriteria(JobApplicant.class).add(Restrictions.eq("uuid", uuid)).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantDetailsByUUID()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return applicant;
  }
  
  public JobApplicant getUUIDNameEmailByApplicantId(long applicantId)
  {
    logger.info("Inside getUUIDNameEmailByApplicantId method");
    JobApplicant app = new JobApplicant();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      String sql = "select u.application_id,u.uuid,u.full_name,u.email_id from job_applications u where application_id = :application_id";
      Query query = session.createSQLQuery(sql);
      query.setLong("application_id", applicantId);
      List userList = query.list();
      Object[] obj = (Object[])userList.get(0);
      BigInteger userId1 = (BigInteger)obj[0];
      long uid = userId1.longValue();
      app.setApplicantId(uid);
      app.setUuid((String)obj[1]);
      app.setFullName((String)obj[2]);
      app.setEmail((String)obj[3]);
    }
    catch (Exception e)
    {
      logger.error("Exception on getUUIDNameEmailByApplicantId()", e);
    }
    return app;
  }
  
  public JobApplicant getApplicantIdNameEmailByUUID(String uuid)
  {
    logger.info("Inside getApplicantIdNameEmailByUUID method");
    JobApplicant app = new JobApplicant();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      String sql = "select u.application_id,u.uuid,u.full_name,u.email_id from job_applications u where uuid = :uuid";
      Query query = session.createSQLQuery(sql);
      query.setString("uuid", uuid);
      List userList = query.list();
      Object[] obj = (Object[])userList.get(0);
      BigInteger userId1 = (BigInteger)obj[0];
      long uid = userId1.longValue();
      app.setApplicantId(uid);
      app.setUuid((String)obj[1]);
      app.setFullName((String)obj[2]);
      app.setEmail((String)obj[3]);
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantIdNameEmailByUUID()", e);
    }
    return app;
  }
  
  public JobApplicant getApplicantIdNameEmailByID(long application_id)
  {
    logger.info("Inside getApplicantIdNameEmailByID method");
    JobApplicant app = new JobApplicant();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      String sql = "select u.application_id,u.uuid,u.full_name,u.email_id from job_applications u where application_id = :application_id";
      Query query = session.createSQLQuery(sql);
      query.setLong("application_id", application_id);
      List userList = query.list();
      Object[] obj = (Object[])userList.get(0);
      BigInteger userId1 = (BigInteger)obj[0];
      long uid = userId1.longValue();
      app.setApplicantId(uid);
      app.setUuid((String)obj[1]);
      app.setFullName((String)obj[2]);
      app.setEmail((String)obj[3]);
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantIdNameEmailByID()", e);
    }
    return app;
  }
  
  public JobApplicant getApplicantForDuplicateCheck(long jb_req_id, String email)
  {
    logger.info("Inside getApplicantForDuplicateCheck method");
    JobApplicant app = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      String sql = "select u.application_id,u.uuid,u.full_name,u.email_id from job_applications u where jb_req_id = :jb_req_id and email_id =:email_id order by applied_datetime desc";
      Query query = session.createSQLQuery(sql);
      query.setLong("jb_req_id", jb_req_id);
      query.setString("email_id", email);
      List userList = query.list();
      if ((userList != null) && (userList.size() > 0))
      {
        app = new JobApplicant();
        Object[] obj = (Object[])userList.get(0);
        BigInteger userId1 = (BigInteger)obj[0];
        long uid = userId1.longValue();
        app.setApplicantId(uid);
        app.setUuid((String)obj[1]);
        app.setFullName((String)obj[2]);
        app.setEmail((String)obj[3]);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantForDuplicateCheck()", e);
    }
    return app;
  }
  
  public JobApplicant getApplicantForDuplicateCheck(long jb_req_id, String email, long applicantId)
  {
    logger.info("Inside getApplicantForDuplicateCheck method");
    JobApplicant app = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      String sql = "select u.application_id,u.uuid,u.full_name,u.email_id from job_applications u where jb_req_id = :jb_req_id and email_id =:email_id and application_id <> :application_id order by applied_datetime desc";
      Query query = session.createSQLQuery(sql);
      query.setLong("jb_req_id", jb_req_id);
      query.setString("email_id", email);
      query.setLong("application_id", applicantId);
      List userList = query.list();
      if ((userList != null) && (userList.size() > 0))
      {
        app = new JobApplicant();
        Object[] obj = (Object[])userList.get(0);
        BigInteger userId1 = (BigInteger)obj[0];
        long uid = userId1.longValue();
        app.setApplicantId(uid);
        app.setUuid((String)obj[1]);
        app.setFullName((String)obj[2]);
        app.setEmail((String)obj[3]);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantForDuplicateCheck()", e);
    }
    return app;
  }
  
  public JobApplicant getApplicantForDuplicateCheckWithTimeSpan(String email, int days, long superUserKey, long applicantId)
  {
    logger.info("Inside getApplicantForDuplicateCheckWithTimeSpan method");
    JobApplicant app = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      String datepattern = DateUtil.defaultdateformatforSQL;
      
      String sql = "select u.application_id,u.uuid,u.full_name,u.email_id from job_applications u where super_user_key =:super_user_key and email_id =:email_id and application_id <> :application_id and  DATE_FORMAT(applied_datetime, '%M %e, %Y')   <= DATE_FORMAT(DATE_ADD(CURDATE(), INTERVAL " + -days + " DAY),'%M %e, %Y') order by applied_datetime desc ";
      

      logger.info(sql);
      Query query = session.createSQLQuery(sql);
      query.setLong("super_user_key", superUserKey);
      query.setString("email_id", email);
      query.setLong("application_id", applicantId);
      List userList = query.list();
      logger.info("userList.size()" + userList.size());
      if ((userList != null) && (userList.size() > 0))
      {
        app = new JobApplicant();
        Object[] obj = (Object[])userList.get(0);
        BigInteger userId1 = (BigInteger)obj[0];
        long uid = userId1.longValue();
        app.setApplicantId(uid);
        app.setUuid((String)obj[1]);
        app.setFullName((String)obj[2]);
        app.setEmail((String)obj[3]);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantForDuplicateCheckWithTimeSpan()", e);
    }
    return app;
  }
  
  public JobApplicant getApplicantForDuplicateCheckWithTimeSpan(String email, int days, long superUserKey)
  {
    logger.info("Inside getApplicantForDuplicateCheckWithTimeSpan method");
    JobApplicant app = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      String datepattern = DateUtil.defaultdateformatforSQL;
      
      String sql = "select u.application_id,u.uuid,u.full_name,u.email_id from job_applications u where super_user_key =:super_user_key and email_id =:email_id and  DATE_FORMAT(applied_datetime, '%M %e, %Y')   <= DATE_FORMAT(DATE_ADD(CURDATE(), INTERVAL " + -days + " DAY),'%M %e, %Y') order by applied_datetime desc ";
      

      logger.info(sql);
      Query query = session.createSQLQuery(sql);
      query.setLong("super_user_key", superUserKey);
      query.setString("email_id", email);
      List userList = query.list();
      logger.info("userList.size()" + userList.size());
      if ((userList != null) && (userList.size() > 0))
      {
        app = new JobApplicant();
        Object[] obj = (Object[])userList.get(0);
        BigInteger userId1 = (BigInteger)obj[0];
        long uid = userId1.longValue();
        app.setApplicantId(uid);
        app.setUuid((String)obj[1]);
        app.setFullName((String)obj[2]);
        app.setEmail((String)obj[3]);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantForDuplicateCheckWithTimeSpan()", e);
    }
    return app;
  }
  
  public ApplicantOtherDetails getApplicantOtherDetails(long applicantId)
  {
    logger.info("Inside getApplicantOtherDetails method");
    
    ApplicantOtherDetails applicantother = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      applicantother = (ApplicantOtherDetails)session.createCriteria(ApplicantOtherDetails.class).add(Restrictions.eq("applicantId", Long.valueOf(applicantId))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantOtherDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return applicantother;
  }
  
  public INineFormBean getINineFormDetails(String id)
  {
    logger.info("Inside getINineFormDetails method");
    
    INineFormBean iNineFormBean = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      iNineFormBean = (INineFormBean)session.createCriteria(INineFormBean.class).createAlias("applicant", "applicant").add(Restrictions.eq("applicant.applicantId", new Long(id))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getINineFormDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return iNineFormBean;
  }
  
  public INineFormBean getINineFormDetailsById(String id)
  {
    logger.info("Inside getINineFormDetailsById method");
    
    INineFormBean iNineFormBean = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      iNineFormBean = (INineFormBean)session.createCriteria(INineFormBean.class).add(Restrictions.eq("i9formid", new Long(id))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getINineFormDetailsById()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return iNineFormBean;
  }
  
  public List getScreenFieldsDetails(String screencode, long superUserKey)
  {
    logger.info("Inside getScreenFieldsDetails method");
    logger.info("*** screen Name : " + screencode);
    List screenfieldList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      screenfieldList = session.createCriteria(ScreenFields.class).add(Restrictions.eq("screenCode", screencode)).add(Restrictions.eq("super_user_key", Long.valueOf(superUserKey))).add(Restrictions.eq("status", "A")).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getScreenFieldsDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return screenfieldList;
  }
  
  public boolean isFieldAttachedToScreenCode(String screencode, String fieldCode, long super_user_key)
  {
    logger.info("Inside isFieldAttachedToScreenCode method");
    logger.info("*** screen Name : " + screencode + " fieldCode:" + fieldCode);
    List screenfieldList = new ArrayList();
    boolean success = false;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      screenfieldList = session.createCriteria(ScreenFields.class).add(Restrictions.eq("screenCode", screencode)).add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key))).add(Restrictions.eq("fieldcode", fieldCode)).add(Restrictions.eq("status", "A")).add(Restrictions.eq("isvisible", "Y")).list();
      if ((screenfieldList != null) && (screenfieldList.size() > 0)) {
        success = true;
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on isFieldAttachedToScreenCode()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return success;
  }
  
  private boolean isScreenFieldPresent(String screenCode, long superUserKey)
  {
    logger.info("Inside isScreenFieldNotPresent method");
    boolean ispresnt = false;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      List screenfieldList = session.createCriteria(ScreenFields.class).add(Restrictions.eq("screenCode", screenCode)).add(Restrictions.eq("super_user_key", Long.valueOf(superUserKey))).list();
      if ((screenfieldList != null) && (screenfieldList.size() > 0)) {
        ispresnt = true;
      }
      logger.info("Inside isScreenFieldNotPresent method" + screenCode + " " + ispresnt);
    }
    catch (Exception e)
    {
      logger.error("Exception on isScreenFieldNotPresent()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return ispresnt;
  }
  
  public List getVisibleScreenFieldList(String screencode, long superUserKey)
  {
    logger.info("Inside getVisibleScreenFieldList method");
    logger.info("*** screen Name : " + screencode);
    List screenfieldList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      if (isScreenFieldPresent(screencode, superUserKey)) {
        screenfieldList = session.createCriteria(ScreenFields.class).add(Restrictions.eq("screenCode", screencode)).add(Restrictions.eq("super_user_key", Long.valueOf(superUserKey))).add(Restrictions.eq("status", "A")).add(Restrictions.eq("isvisible", "Y")).list();
      } else {
        screenfieldList = session.createCriteria(ScreenFields.class).add(Restrictions.eq("screenCode", screencode)).add(Restrictions.eq("super_user_key", new Long(0L))).add(Restrictions.eq("status", "A")).add(Restrictions.eq("isvisible", "Y")).list();
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getVisibleScreenFieldList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return screenfieldList;
  }
  
  public ScreenFields getScreenField(String screenfieldId)
  {
    logger.info("Inside getScreenField method");
    
    ScreenFields scrf = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      scrf = (ScreenFields)session.createCriteria(ScreenFields.class).add(Restrictions.eq("screenfieldId", Long.valueOf(new Long(screenfieldId).longValue()))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getScreenField()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return scrf;
  }
  
  public void updateScreenField(ScreenFields scrf)
  {
    logger.info("Inside updateScreenField method");
    

    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      session.update(scrf);
    }
    catch (Exception e)
    {
      logger.error("Exception on updateScreenField()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public List getApplicantAttachments(long applicationId, String appuuid, String type)
  {
    logger.info("Inside getApplicantDetails method");
    
    List attachList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      attachList = session.createCriteria(ApplicantAttachments.class).add(Restrictions.eq("applicantId", Long.valueOf(applicationId))).add(Restrictions.eq("appuuid", appuuid)).add(Restrictions.eq("type", type)).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantAttachments()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return attachList;
  }
  
  public ApplicantAttachments getApplicantAttachments(long applicationId, String type)
  {
    logger.info("Inside getApplicantDetails method");
    ApplicantAttachments appattch = null;
    List attachList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      attachList = session.createCriteria(ApplicantAttachments.class).add(Restrictions.eq("applicantId", Long.valueOf(applicationId))).add(Restrictions.eq("type", type)).list();
      if ((attachList != null) && (attachList.size() > 0)) {
        appattch = (ApplicantAttachments)attachList.get(0);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantAttachments()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return appattch;
  }
  
  public ApplicantAttachments deleteResume(long applicationId, String type)
  {
    logger.info("Inside deleteResume method");
    ApplicantAttachments appattch = null;
    List attachList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      String hql = "delete from ApplicantAttachments where applicantId = :applicantId  and type = :type";
      Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
      query.setLong("applicantId", applicationId);
      query.setString("type", type);
      int rowCount = query.executeUpdate();
    }
    catch (Exception e)
    {
      int rowCount;
      logger.error("Exception on getApplicantAttachments()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return appattch;
  }
  
  public JobApplicant getApplicantDetailswithUUID(String id, String uuid)
  {
    logger.info("Inside getApplicantDetailswithUUID method");
    
    JobApplicant applicant = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      applicant = (JobApplicant)session.createCriteria(JobApplicant.class).add(Restrictions.eq("applicantId", new Long(id))).add(Restrictions.eq("uuid", uuid)).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantDetailswithUUID()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return applicant;
  }
  
  public JobApplicant getApplicantDetailsbyEmailId(String emailId)
  {
    logger.info("Inside getApplicantDetailsbyEmailId method");
    
    JobApplicant applicant = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      applicant = (JobApplicant)session.createCriteria(JobApplicant.class).add(Restrictions.eq("email", emailId)).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantDetailsbyEmailId()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return applicant;
  }
  
  public JobApplicant getApplicantDetailsByEmailId(String email)
  {
    logger.info("Inside getApplicantDetailsByEmailId method");
    List lst = new ArrayList();
    JobApplicant applicant = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      lst = session.createCriteria(JobApplicant.class).add(Restrictions.eq("email", email)).addOrder(Order.desc("appliedDate")).list();
      if ((lst != null) && (lst.size() > 0)) {
        applicant = (JobApplicant)lst.get(0);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantDetailsByEmailId()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return applicant;
  }
  
  public OfferApprover getOfferApprover(String id)
  {
    logger.info("Inside getOfferApprover method");
    OfferApprover oapprover = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      oapprover = (OfferApprover)session.createCriteria(OfferApprover.class).add(Restrictions.eq("offerApproverId", new Long(id))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getOfferApprover()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return oapprover;
  }
  
  public ResumeSources getResumeSourcesByCode(String code)
  {
    logger.info("Inside getResumeSourcesByCode method");
    logger.info("code : " + code);
    ResumeSources sc = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      sc = (ResumeSources)session.createCriteria(ResumeSources.class).add(Restrictions.eq("resumeSourceCode", code)).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getResumeSourcesByCode()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return sc;
  }
  
  public ResumeSources getResumeSourcesById(String sourceId)
  {
    logger.info("Inside getResumeSourcesById method");
    logger.info("sourceId : " + sourceId);
    ResumeSources sc = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      sc = (ResumeSources)session.createCriteria(ResumeSources.class).add(Restrictions.eq("resumeSourcesId", Integer.valueOf(Integer.parseInt(sourceId)))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getResumeSourcesByCode()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return sc;
  }
  
  public List getOfferApproverList(long applicantid)
  {
    logger.info("Inside getOfferApproverList method");
    List aList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      aList = session.createCriteria(OfferApprover.class).add(Restrictions.eq("applicantId", Long.valueOf(applicantid))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getOfferApproverList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return aList;
  }
  
  public List getOfferApproverListNotSystemRuleDefind(long applicantid)
  {
    logger.info("Inside getOfferApproverListNotSystemRuleDefind method");
    List aList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      aList = session.createCriteria(OfferApprover.class).add(Restrictions.eq("applicantId", new Long(applicantid))).add(Restrictions.eq("isFromSystemRule", "N")).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getOfferApproverListNotSystemRuleDefind()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return aList;
  }
  
  public List getAllReferences(long applicantid)
  {
    logger.info("Inside getAllReferences method");
    List aList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      aList = session.createCriteria(ApplicantReferencee.class).add(Restrictions.eq("applicantId", new Long(applicantid))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllReferences()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return aList;
  }
  
  public List getInterviewWatchlist(long eventId)
  {
    logger.info("Inside getInterviewWatchlist method");
    List aList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      aList = session.createCriteria(InterviewWatchList.class).add(Restrictions.eq("applicationeventId", new Long(eventId))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getInterviewWatchlist()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return aList;
  }
  
  public List getAllApplicantRatingsByType(long applicantId, String type)
  {
    logger.info("Inside getAllApplicantRatingsByType method");
    List aList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      aList = session.createCriteria(ApplicantRating.class).add(Restrictions.eq("applicantId", new Long(applicantId))).add(Restrictions.eq("type", type)).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllApplicantRatingsByType()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return aList;
  }
  
  public List getApplicantRatingsByNameAndType(long applicantId, String name, String type)
  {
    logger.info("Inside getApplicantRatingsByNameAndType method");
    List aList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      aList = session.createCriteria(ApplicantRating.class).add(Restrictions.eq("applicantId", new Long(applicantId))).add(Restrictions.eq("name", name)).add(Restrictions.eq("type", type)).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantRatingsByNameAndType()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return aList;
  }
  
  public List getApplicantRatingsByApplicantAndType(long applicantId, String type)
  {
    logger.info("Inside getApplicantRatingsByApplicantAndType method");
    List aList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      aList = session.createCriteria(ApplicantRating.class).add(Restrictions.eq("applicantId", new Long(applicantId))).add(Restrictions.eq("type", type)).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantRatingsByApplicantAndType()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return aList;
  }
  
  public List getEducationsByApplicantAndType(long applicantId, String type)
  {
    logger.info("Inside getEducationsByApplicantAndType method");
    List aList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      aList = session.createCriteria(EducationDetails.class).add(Restrictions.eq("applicantId", new Long(applicantId))).add(Restrictions.eq("eduType", type)).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getEducationsByApplicantAndType()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return aList;
  }
  
  public EducationDetails saveEducationDetails(EducationDetails edu)
  {
    logger.info("Inside saveEducationDetails method");
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      session.save(edu);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveEducationDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return edu;
  }
  
  public void deleteEducation(long eduId)
  {
    logger.info("Inside deleteEducation method");
    EducationDetails edu = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      edu = (EducationDetails)session.createCriteria(EducationDetails.class).add(Restrictions.eq("educationId", Long.valueOf(eduId))).uniqueResult();
      

      session.delete(edu);
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteEducation()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void deleteEducationByUUID(String uuid)
  {
    logger.info("Inside deleteEducationByUUID method");
    EducationDetails edu = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      edu = (EducationDetails)session.createCriteria(EducationDetails.class).add(Restrictions.eq("uuid", uuid)).uniqueResult();
      

      session.delete(edu);
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteEducation()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public ApplicantSkills saveSkillsDetails(ApplicantSkills edu)
  {
    logger.info("Inside saveSkillsDetails method");
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      session.save(edu);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveSkillsDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return edu;
  }
  
  public void deleteSkill(long skillid)
  {
    logger.info("Inside deleteSkill method");
    ApplicantSkills edu = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      edu = (ApplicantSkills)session.createCriteria(ApplicantSkills.class).add(Restrictions.eq("skillId", Long.valueOf(skillid))).uniqueResult();
      

      session.delete(edu);
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteSkill()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void deleteSkillByUUID(String uuid)
  {
    logger.info("Inside deleteSkillByUUID method");
    ApplicantSkills edu = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      edu = (ApplicantSkills)session.createCriteria(ApplicantSkills.class).add(Restrictions.eq("uuid", uuid)).uniqueResult();
      

      session.delete(edu);
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteSkillByUUID()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void deletePreviousOrg(long prevorgid)
  {
    logger.info("Inside deletePreviousOrg method");
    PreviousOrgDetails edu = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      edu = (PreviousOrgDetails)session.createCriteria(PreviousOrgDetails.class).add(Restrictions.eq("prevOrgDetailsId", Long.valueOf(prevorgid))).uniqueResult();
      


      session.delete(edu);
    }
    catch (Exception e)
    {
      logger.error("Exception on deletePreviousOrg()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public PreviousOrgDetails savePreviousOrgDetails(PreviousOrgDetails org)
  {
    logger.info("Inside PreviousOrgDetails method");
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      session.save(org);
    }
    catch (Exception e)
    {
      logger.error("Exception on PreviousOrgDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return org;
  }
  
  public List getPreviousOrgDetailsByApplicant(long applicantId)
  {
    logger.info("Inside getPreviousOrgDetailsByApplicant method");
    List aList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      aList = session.createCriteria(PreviousOrgDetails.class).add(Restrictions.eq("applicantId", new Long(applicantId))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getPreviousOrgDetailsByApplicant()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return aList;
  }
  
  public List getSkillsByApplicant(long applicantId)
  {
    logger.info("Inside getSkillsByApplicant method");
    List aList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      aList = session.createCriteria(ApplicantSkills.class).add(Restrictions.eq("applicantId", new Long(applicantId))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getSkillsByApplicant()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return aList;
  }
  
  public List getAverageApplicantRatingsByApplicantAndType(long applicantId, String type)
  {
    logger.info("Inside getApplicantRatingsByApplicantAndType method");
    List aList = new ArrayList();
    List aratingList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      aList = session.createQuery("select a.applicantId,a.name,avg(a.yourrating) from ApplicantRating a where a.applicantId=" + applicantId + " and a.type=" + "'" + type + "'" + " group by a.name").list();
      for (int i = 0; i < aList.size(); i++)
      {
        Object[] obj = (Object[])aList.get(i);
        ApplicantRating apprat = new ApplicantRating();
        
        apprat.setApplicantId(((Long)obj[0]).longValue());
        apprat.setName((String)obj[1]);
        apprat.setYourrating(((Float)obj[2]).floatValue());
        aratingList.add(apprat);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantRatingsByApplicantAndType()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return aratingList;
  }
  
  public ApplicantRating getAverageApplicantRatingsByApplicantAndTypeAndName(long applicantId, String name, String type)
  {
    logger.info("Inside getAverageApplicantRatingsByApplicantAndTypeAndName method");
    
    List aList = new ArrayList();
    ApplicantRating apprat = new ApplicantRating();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      aList = session.createQuery("select avg(a.yourrating) from ApplicantRating a where a.applicantId=" + applicantId + " and a.type=" + "'" + type + "'" + " and a.name=" + "'" + name + "'").list();
      for (int i = 0; i < aList.size(); i++)
      {
        Float fl = (Float)aList.get(i);
        apprat.setYourrating(fl.floatValue());
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getAverageApplicantRatingsByApplicantAndTypeAndName()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return apprat;
  }
  
  public long getTotalOfferedCTCByReqId(long requisitionId)
  {
    logger.info("Inside getTotalOfferedCTCByReqId method");
    List aList = new ArrayList();
    long totalctc = 0L;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      aList = session.createQuery("select offeredctc from JobApplicant a where a.reqId=" + requisitionId + " and (a.interviewState = " + "'" + "Offer Accepted" + "'" + " or a.interviewState = " + "'" + "On Board - Joined" + "'" + "or a.interviewState = " + "'" + "Offer Released" + "'" + "or a.interviewState = " + "'" + "Offer Initiated-In Approval" + "'" + "or a.interviewState = " + "'" + "Ready for Release Offer" + "'" + ")").list();
      for (int i = 0; i < aList.size(); i++)
      {
        String fl = (String)aList.get(i);
        totalctc += new Long(fl).longValue();
      }
      logger.info("totalctc" + totalctc);
    }
    catch (Exception e)
    {
      logger.error("Exception on getTotalOfferedCTCByReqId()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totalctc;
  }
  
  public int getTotalNoOfFilledPositionByReqId(long requisitionId)
  {
    logger.info("Inside getTotalNoOfFilledPositionByReqId method");
    List aList = new ArrayList();
    int totalreadytojoin = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      aList = session.createQuery("select applicantId from JobApplicant a where a.reqId=" + requisitionId + " and (a.interviewState = " + "'" + "Offer Accepted" + "'" + " or a.interviewState = " + "'" + "On Board - Joined" + "'" + "or a.interviewState = " + "'" + "Offer Released" + "'" + ")").list();
      







      totalreadytojoin = aList.size();
      
      logger.info("totalreadytojoin" + totalreadytojoin);
    }
    catch (Exception e)
    {
      logger.error("Exception on getTotalNoOfFilledPositionByReqId()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totalreadytojoin;
  }
  
  public void saveInterviewSchedule(JobApplicationEvent event)
  {
    logger.info("Inside saveInterviewSchedule method");
    User user = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      session.save(event);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveInterviewSchedule()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void updateApplicationEvent(JobApplicationEvent event)
  {
    logger.info("Inside updateApplicationEvent method");
    User user = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      session.update(event);
    }
    catch (Exception e)
    {
      logger.error("Exception on updateApplicationEvent()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void deleteApplicationEvent(JobApplicationEvent event)
  {
    logger.info("Inside deleteApplicationEvent method");
    User user = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      session.delete(event);
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteApplicationEvent()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public JobApplicationEvent getApplicationEvent(long applicantId, int eventtype)
  {
    logger.info("Inside getApplicationEvent method");
    User user = null;
    org.hibernate.Session session = null;
    JobApplicationEvent jb = null;
    List eventList = new ArrayList();
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      eventList = session.createCriteria(JobApplicationEvent.class).createAlias("applicant", "applicant").add(Restrictions.eq("applicant.applicantId", Long.valueOf(applicantId))).add(Restrictions.eq("eventType", Integer.valueOf(eventtype))).list();
      




      int size = eventList.size();
      if (size == 1) {
        jb = (JobApplicationEvent)eventList.get(0);
      }
      if (size > 1) {
        jb = (JobApplicationEvent)eventList.get(size - 1);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicationEvent()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return jb;
  }
  
  public JobApplicationEvent getApplicationEvent(long applicantId, int eventtype, long testId)
  {
    logger.info("Inside getApplicationEvent method");
    User user = null;
    org.hibernate.Session session = null;
    JobApplicationEvent jb = null;
    List eventList = new ArrayList();
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      eventList = session.createCriteria(JobApplicationEvent.class).createAlias("applicant", "applicant").add(Restrictions.eq("applicant.applicantId", Long.valueOf(applicantId))).add(Restrictions.eq("eventType", Integer.valueOf(eventtype))).add(Restrictions.eq("testId", Long.valueOf(testId))).list();
      





      int size = eventList.size();
      if (size == 1) {
        jb = (JobApplicationEvent)eventList.get(0);
      }
      if (size > 1) {
        jb = (JobApplicationEvent)eventList.get(size - 1);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicationEvent()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return jb;
  }
  
  public JobApplicationEvent getApplicationEventByApplicantIdSortByCreatedDate(long applicantId)
  {
    logger.info("Inside getApplicationEvent method");
    User user = null;
    org.hibernate.Session session = null;
    JobApplicationEvent jb = null;
    List eventList = new ArrayList();
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      eventList = session.createCriteria(JobApplicationEvent.class).createAlias("applicant", "applicant").add(Restrictions.eq("applicant.applicantId", Long.valueOf(applicantId))).addOrder(Order.desc("createdDate")).list();
      if ((eventList != null) && (eventList.size() > 0)) {
        jb = (JobApplicationEvent)eventList.get(0);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicationEventByApplicantIdSortByCreatedDate()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return jb;
  }
  
  public void saveOfferAttachment(ApplicantOfferAttachment attachment)
  {
    logger.info("Inside saveOfferAttachment method");
    User user = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      session.save(attachment);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveOfferAttachment()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public List getOfferwatchListByApplicantId(long applicantId, String type)
  {
    logger.info("Inside getOfferwatchListByApplicantId method");
    List offerwatch = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      offerwatch = session.createCriteria(OfferWatchList.class).add(Restrictions.eq("applicantId", Long.valueOf(applicantId))).add(Restrictions.eq("functionType", type)).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getOfferwatchListByApplicantId()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return offerwatch;
  }
  
  public ApplicantUser updateApplicantUser(ApplicantUser applicant)
  {
    logger.info("Inside updateApplicantUser method");
    User user = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      session.update(applicant);
    }
    catch (Exception e)
    {
      logger.error("Exception on updateApplicant()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return applicant;
  }
  
  public ApplicantUser deleteApplicantUser(ApplicantUser applicant)
  {
    logger.info("Inside deleteApplicantUser method");
    User user = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      session.delete(applicant);
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteApplicantUser()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return applicant;
  }
  
  public JobApplicant updateApplicant(JobApplicant applicant)
  {
    logger.info("Inside updateApplicant method");
    User user = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      session.update(applicant);
    }
    catch (Exception e)
    {
      logger.error("Exception on updateApplicant()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return applicant;
  }
  
  public void updateScreenFields(ScreenFields screenFields)
  {
    logger.info("Inside updateScreenFields method");
    
    User user = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      session.update(screenFields);
    }
    catch (Exception e)
    {
      logger.error("Exception on updateScreenFields()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void saveScreenFields(ScreenFields screenFields)
  {
    logger.info("Inside saveScreenFields method");
    
    User user = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      session.save(screenFields);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveScreenFields()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void deleteScreenFields(List screenfieldList)
  {
    logger.info("Inside deleteScreenFields method");
    User user = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      for (int i = 0; i < screenfieldList.size(); i++)
      {
        ScreenFields screenFields = (ScreenFields)screenfieldList.get(i);
        
        session.delete(screenFields);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteScreenFields()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public OfferApprover updateOfferApprover(OfferApprover app)
  {
    logger.info("Inside updateOfferApprover method");
    User user = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      session.update(app);
    }
    catch (Exception e)
    {
      logger.error("Exception on updateOfferApprover()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return app;
  }
  
  public OfferWatchList updateOfferWaltcher(OfferWatchList app)
  {
    logger.info("Inside updateOfferWaltcher method");
    User user = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      session.update(app);
    }
    catch (Exception e)
    {
      logger.error("Exception on updateOfferWaltcher()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return app;
  }
  
  public OfferApprover getNextOfferApprover(long id, int level)
  {
    logger.info("Inside getNextOfferApprover method");
    OfferApprover approver = null;
    List aaproverList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      aaproverList = session.createCriteria(OfferApprover.class).add(Restrictions.eq("applicantId", new Long(id))).add(Restrictions.eq("isapprover", "Y")).add(Restrictions.gt("levelorder", Integer.valueOf(level))).addOrder(Order.asc("levelorder")).list();
      




      int i = 0;
      if (i < aaproverList.size()) {
        approver = (OfferApprover)aaproverList.get(i);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getNextOfferApprover()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return approver;
  }
  
  public void saveOfferApproversList(List approverList)
  {
    logger.info("Inside saveOfferApproversList method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      for (int i = 0; i < approverList.size(); i++)
      {
        OfferApprover app = (OfferApprover)approverList.get(i);
        session.save(app);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on saveOfferApproversList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void deleteOfferApprovers(long applicantId)
  {
    logger.info("Inside deleteOfferApprovers method");
    
    ApplicantOfferAttachment offattach = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      String hql = "delete from OfferApprover where applicantId = :applicantId";
      Query query = session.createQuery(hql);
      query.setLong("applicantId", applicantId);
      int rowCount = query.executeUpdate();
    }
    catch (Exception e)
    {
      int rowCount;
      logger.error("Exception on deleteOfferApprovers()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void addWatchList(List watchList)
  {
    logger.info("Inside addWatchList method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      for (int i = 0; i < watchList.size(); i++)
      {
        OfferWatchList app = (OfferWatchList)watchList.get(i);
        session.save(app);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on addWatchList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void addInterviewWatchList(List watchList)
  {
    logger.info("Inside addInterviewWatchList method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      for (int i = 0; i < watchList.size(); i++)
      {
        InterviewWatchList app = (InterviewWatchList)watchList.get(i);
        session.save(app);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on addInterviewWatchList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public List getInterviewWatchList(long applicationId)
  {
    logger.info("Inside getInterviewWatchList method");
    List watchList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      watchList = session.createCriteria(InterviewWatchList.class).add(Restrictions.eq("applicantId", new Long(applicationId))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getInterviewWatchList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return watchList;
  }
  
  public void deleteWatchList(long applicantId)
  {
    logger.info("Inside deleteWatchList method");
    
    ApplicantOfferAttachment offattach = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      String hql = "delete from OfferWatchList where applicantId = :applicantId";
      Query query = session.createQuery(hql);
      query.setLong("applicantId", applicantId);
      int rowCount = query.executeUpdate();
    }
    catch (Exception e)
    {
      int rowCount;
      logger.error("Exception on deleteWatchList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void updateInterviewComments(JobApplicationEvent event)
  {
    logger.info("Inside updateInterviewComments method");
    User user = null;
    org.hibernate.Session session = null;
    Connection con = null;
    PreparedStatement pstmt = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      



      String sql = "update job_application_events a set status = ? , interviewerComments = ?  , updated_by = ? , updated_date= ?, updated_by_name = ? , evtmplfileName = ? , evtmplfile = ?   where application_id= ? and event_type=? ";
      if ((event.getEvtmplFileName() != null) && (event.getEvtmplFileName() != null) && (event.getEvtmplFileName().length() > 0)) {
        sql = "update job_application_events a set status = ? , interviewerComments = ?  , updated_by = ? , updated_date= ?, updated_by_name = ? ,interview_state = ?, evtmplfileName = ? , evtmplfile = ?   where application_id= ? and event_type=? ";
      } else {
        sql = "update job_application_events a set status = ? , interviewerComments = ?  , updated_by = ? , updated_date= ?, updated_by_name = ? , interview_state = ? where application_id= ? and event_type=? ";
      }
      logger.info(sql);
      logger.info(event.getInterviewerComments());
      con = session.connection();
      pstmt = con.prepareStatement(sql);
      pstmt.setInt(1, event.getStatus());
      pstmt.setString(2, event.getInterviewerComments());
      pstmt.setString(3, event.getUpdatedBy());
      pstmt.setDate(4, new java.sql.Date(new java.util.Date().getTime()));
      pstmt.setString(5, event.getUpdatedByName());
      pstmt.setString(6, event.getInterviewState());
      if ((event.getEvtmplFileName() != null) && (event.getEvtmplFileName() != null) && (event.getEvtmplFileName().length() > 0))
      {
        pstmt.setString(7, event.getEvtmplFileName());
        pstmt.setBlob(8, event.getEvtmplFile());
        pstmt.setLong(9, new Long(event.getApplicant().getApplicantId()).longValue());
        
        pstmt.setInt(10, new Integer(event.getEventType()).intValue());
      }
      else
      {
        pstmt.setLong(7, new Long(event.getApplicant().getApplicantId()).longValue());
        
        pstmt.setInt(8, new Integer(event.getEventType()).intValue());
      }
      pstmt.execute();
      con.commit(); return;
    }
    catch (Exception e)
    {
      logger.error("Exception on saveInterviewSchedule()", e);
    }
    finally
    {
      logger.info("Inside finally method");
      try
      {
        pstmt.close();
        con.close();
      }
      catch (SQLException e)
      {
        logger.error("Exception on closing comnnection()", e);
      }
    }
  }
  
  public List getApplicationEventListWithRatings(String applicationId)
  {
    logger.info("Inside getApplicationEventListWithRatings method");
    List eventlist = new ArrayList();
    List neweventlist = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      eventlist = session.createCriteria(JobApplicationEvent.class).createAlias("applicant", "applicant").add(Restrictions.eq("applicant.applicantId", new Long(applicationId))).list();
      for (int i = 0; i < eventlist.size(); i++)
      {
        JobApplicationEvent jbe = (JobApplicationEvent)eventlist.get(i);
        if ((jbe.getEvtmplFileName() != null) && (jbe.getEvtmplFileName() != null) && (jbe.getEvtmplFileName().length() > 0))
        {
          String filePath = Constant.getValue("ATTACHMENT_PATH") + jbe.getJobAppEventId() + "/";
          
          Blob blob = jbe.getEvtmplFile();
          File file = new File(filePath);
          if (!file.exists()) {
            file.mkdirs();
          }
          RandomAccessFile raf = new RandomAccessFile(filePath + jbe.getEvtmplFileName(), "rw");
          

          int length = (int)blob.length();
          byte[] _blob = blob.getBytes(1L, length);
          raf.write(_blob);
          raf.close();
        }
        List comprating = session.createCriteria(ApplicantRating.class).add(Restrictions.eq("applicantId", new Long(applicationId))).add(Restrictions.eq("applicantEventId", Long.valueOf(jbe.getJobAppEventId()))).add(Restrictions.eq("type", Common.COMPETENCY_TYPE)).list();
        






        jbe.setCompetencyRatingList(comprating);
        if (jbe.getEventType() == 0)
        {
          List accomprating = session.createCriteria(ApplicantRating.class).add(Restrictions.eq("applicantId", new Long(applicationId))).add(Restrictions.eq("applicantEventId", Long.valueOf(jbe.getJobAppEventId()))).add(Restrictions.eq("type", Common.ACCOMPLISHMENT_TYPE)).list();
          







          jbe.setAccomplishmentRatingList(accomprating);
        }
        neweventlist.add(jbe);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicationEventListWithRatings()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return neweventlist;
  }
  
  public List getApplicationEventList(String applicationId)
  {
    logger.info("Inside getApplicationEventList method");
    List eventlist = new ArrayList();
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      eventlist = session.createCriteria(JobApplicationEvent.class).createAlias("applicant", "applicant").add(Restrictions.eq("applicant.applicantId", new Long(applicationId))).list();
      for (int i = 0; i < eventlist.size(); i++)
      {
        JobApplicationEvent jbe = (JobApplicationEvent)eventlist.get(i);
        if ((jbe.getEvtmplFileName() != null) && (jbe.getEvtmplFileName() != null) && (jbe.getEvtmplFileName().length() > 0))
        {
          String filePath = Constant.getValue("ATTACHMENT_PATH") + jbe.getJobAppEventId() + "/";
          
          Blob blob = jbe.getEvtmplFile();
          File file = new File(filePath);
          if (!file.exists()) {
            file.mkdirs();
          }
          RandomAccessFile raf = new RandomAccessFile(filePath + jbe.getEvtmplFileName(), "rw");
          

          int length = (int)blob.length();
          byte[] _blob = blob.getBytes(1L, length);
          raf.write(_blob);
          raf.close();
        }
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicationEventList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return eventlist;
  }
  
  public List getApplicantsByRequitionIdAndStatus(long reqId, String status, String dir_str, String sort_str)
  {
    logger.info("Inside getApplicantsByRequitionIdAndStatus method");
    logger.info("reqId  : " + reqId);
    logger.info("status : " + status);
    List applicantList = new ArrayList();
    org.hibernate.Session session = null;
    Criteria outer = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      if ((status != null) && ((status.equals("OnHold")) || (status.equals("Rejected")))) {
        outer = session.createCriteria(JobApplicant.class).add(Restrictions.eq("reqId", Long.valueOf(reqId))).add(Restrictions.like("interviewState", status + "%"));
      } else {
        outer = session.createCriteria(JobApplicant.class).add(Restrictions.eq("reqId", Long.valueOf(reqId))).add(Restrictions.eq("interviewState", status));
      }
      ProjectionList projList = Projections.projectionList();
      projList.add(Projections.property("applicantId"));
      projList.add(Projections.property("fullName"));
      projList.add(Projections.property("uuid"));
      projList.add(Projections.property("isViewed"));
      

      outer.setProjection(projList);
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      applicantList = outer.list();
      
      logger.info(Integer.valueOf(applicantList.size()));
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantsByRequitionIdAndStatus()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return applicantList;
  }
  
  public List getApplicantsByRequitionIdAndStatusByVendor(long reqId, long vendorId, String status, String dir_str, String sort_str)
  {
    logger.info("Inside getApplicantsByRequitionIdAndStatusByVendor method");
    logger.info("reqId  : " + reqId);
    logger.info("status : " + status);
    List applicantList = new ArrayList();
    org.hibernate.Session session = null;
    Criteria outer = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      if ((status != null) && (status.equals("OnHold"))) {
        outer = session.createCriteria(JobApplicant.class).add(Restrictions.eq("reqId", Long.valueOf(reqId))).add(Restrictions.eq("vendorId", Long.valueOf(vendorId))).add(Restrictions.like("interviewState", status + "%"));
      } else {
        outer = session.createCriteria(JobApplicant.class).add(Restrictions.eq("reqId", Long.valueOf(reqId))).add(Restrictions.eq("vendorId", Long.valueOf(vendorId))).add(Restrictions.eq("interviewState", status));
      }
      ProjectionList projList = Projections.projectionList();
      projList.add(Projections.property("applicantId"));
      projList.add(Projections.property("fullName"));
      projList.add(Projections.property("uuid"));
      

      outer.setProjection(projList);
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      applicantList = outer.list();
      
      logger.info(Integer.valueOf(applicantList.size()));
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantsByRequitionIdAndStatusByVendor()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return applicantList;
  }
  
  public boolean isAllapplicantViewed(long reqId)
  {
    logger.info("Inside isAllapplicantViewed method");
    int totalcount = 0;
    org.hibernate.Session session = null;
    boolean issucess = true;
    Map<String, Integer> countMap = new HashMap();
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String sql = "select count(*) as countvalue  from job_applications where jb_req_id=:jb_req_id and is_viewed=1";
      Query query = session.createSQLQuery(sql);
      query.setLong("jb_req_id", reqId);
      BigInteger total = (BigInteger)query.uniqueResult();
      if (total.intValue() > 0) {
        issucess = false;
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on isAllapplicantViewed()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return issucess;
  }
  
  public List isAllapplicantViewed(List reqList)
  {
    logger.info("Inside isAllapplicantViewed method");
    int totalcount = 0;
    org.hibernate.Session session = null;
    boolean issucess = true;
    List reqListNew = new ArrayList();
    Map<String, Integer> countMap = new HashMap();
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      List<Long> reqIdList = new ArrayList();
      for (int i = 0; i < reqList.size(); i++)
      {
        JobRequisition jb = (JobRequisition)reqList.get(i);
        reqIdList.add(Long.valueOf(jb.getJobreqId()));
      }
      String reqids = QueryBuilderApplicant.buildCommaseparatedLong(reqIdList);
      
      String sql = "select jb_req_id , count(*) as countvalue  from job_applications where jb_req_id in (" + reqids + ") and is_viewed=1 group by jb_req_id";
      
      logger.info("sql in isAllapplicantViewed" + sql);
      Query query = session.createSQLQuery(sql);
      
      List list = query.list();
      
      Map<Long, Integer> m = new HashMap();
      for (int i = 0; i < list.size(); i++)
      {
        Object[] obj = (Object[])list.get(i);
        BigInteger reqid = (BigInteger)obj[0];
        long reqidv = reqid.longValue();
        
        BigInteger total = (BigInteger)obj[1];
        int totalc = total.intValue();
        
        m.put(Long.valueOf(reqidv), Integer.valueOf(totalc));
      }
      for (int i = 0; i < reqList.size(); i++)
      {
        JobRequisition jb = (JobRequisition)reqList.get(i);
        int total = m.get(Long.valueOf(jb.getJobreqId())) == null ? 0 : ((Integer)m.get(Long.valueOf(jb.getJobreqId()))).intValue();
        logger.info("jb.getJobreqId()" + jb.getJobreqId() + total);
        if (total > 0) {
          jb.setAllApplicantViewed("N");
        } else {
          jb.setAllApplicantViewed("Y");
        }
        reqListNew.add(jb);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on isAllapplicantViewed()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return reqListNew;
  }
  
  public Map<String, Integer> getCountMapOfApplicantsByRequitionIdAndStatus(long reqId)
  {
    logger.info("Inside getCountMapOfApplicantsByRequitionIdAndStatus method");
    int totalcount = 0;
    org.hibernate.Session session = null;
    
    Map<String, Integer> countMap = new HashMap();
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String sql = "select interview_state , count(*) as countvalue  from job_applications where jb_req_id=:jb_req_id group by  interview_state";
      Query query = session.createSQLQuery(sql);
      query.setLong("jb_req_id", reqId);
      List applicantList = query.list();
      
      logger.info("applicantList.size()" + applicantList.size());
      int rejectcount = 0;
      int onholdcount = 0;
      for (int i = 0; i < applicantList.size(); i++)
      {
        Object[] obj = (Object[])applicantList.get(i);
        String intstate = (String)obj[0];
        BigInteger total = (BigInteger)obj[1];
        int totalc = total.intValue();
        if (intstate.startsWith("Rejected")) {
          rejectcount += totalc;
        }
        if (intstate.startsWith("OnHold")) {
          onholdcount += totalc;
        }
        countMap.put(intstate, Integer.valueOf(totalc));
      }
      countMap.put("Rejected", Integer.valueOf(rejectcount));
      countMap.put("OnHold", Integer.valueOf(onholdcount));
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountMapOfApplicantsByRequitionIdAndStatus()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return countMap;
  }
  
  public Map<String, Integer> getCountMapOfApplicantsByRequitionIdAndStatus(long reqId, long vendorId)
  {
    logger.info("Inside getCountMapOfApplicantsByRequitionIdAndStatus method");
    int totalcount = 0;
    org.hibernate.Session session = null;
    
    Map<String, Integer> countMap = new HashMap();
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String sql = "select interview_state , count(*) as countvalue  from job_applications where jb_req_id=:jb_req_id and vendor_id=:vendor_id group by  interview_state";
      Query query = session.createSQLQuery(sql);
      query.setLong("jb_req_id", reqId);
      query.setLong("vendor_id", vendorId);
      List applicantList = query.list();
      
      int rejectcount = 0;
      int onholdcount = 0;
      for (int i = 0; i < applicantList.size(); i++)
      {
        Object[] obj = (Object[])applicantList.get(i);
        String intstate = (String)obj[0];
        BigInteger total = (BigInteger)obj[1];
        int totalc = total.intValue();
        if (intstate.startsWith("Rejected")) {
          rejectcount += totalc;
        }
        if (intstate.startsWith("OnHold")) {
          onholdcount += totalc;
        }
        countMap.put(intstate, Integer.valueOf(totalc));
      }
      countMap.put("Rejected", Integer.valueOf(rejectcount));
      countMap.put("OnHold", Integer.valueOf(onholdcount));
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountMapOfApplicantsByRequitionIdAndStatus()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return countMap;
  }
  
  public List getApplicantsByRequitionDeletedStatus(long reqId, String dir_str, String sort_str)
  {
    logger.info("Inside getApplicantsByRequitionDeletedStatus method");
    logger.info("reqId" + reqId);
    
    List applicantList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = session.createCriteria(JobApplicant.class).add(Restrictions.eq("reqId", Long.valueOf(reqId))).add(Restrictions.eq("status", "D"));
      


      ProjectionList projList = Projections.projectionList();
      projList.add(Projections.property("applicantId"));
      projList.add(Projections.property("fullName"));
      projList.add(Projections.property("uuid"));
      

      outer.setProjection(projList);
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      applicantList = outer.list();
      
      logger.info(Integer.valueOf(applicantList.size()));
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantsByRequitionDeletedStatus()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return applicantList;
  }
  
  public List getApplicantsByRequitionIdVendorIdAndStatus(long reqId, long userid, String state, String dir_str, String sort_str)
  {
    logger.info("Inside getApplicantsByRequitionIdAndStatus method");
    logger.info("reqId " + reqId);
    logger.info("status " + state);
    logger.info("userid " + userid);
    
    List applicantList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = null;
      if ((state != null) && (state.equals("OnHold"))) {
        outer = session.createCriteria(JobApplicant.class).add(Restrictions.eq("reqId", Long.valueOf(reqId))).add(Restrictions.eq("vendorId", Long.valueOf(userid))).add(Restrictions.like("interviewState", state + "%")).add(Restrictions.eq("status", "H"));
      } else {
        outer = session.createCriteria(JobApplicant.class).add(Restrictions.eq("reqId", Long.valueOf(reqId))).add(Restrictions.eq("vendorId", Long.valueOf(userid))).add(Restrictions.eq("interviewState", state)).add(Restrictions.eq("status", "A"));
      }
      ProjectionList projList = Projections.projectionList();
      projList.add(Projections.property("applicantId"));
      projList.add(Projections.property("fullName"));
      projList.add(Projections.property("uuid"));
      
      outer.setProjection(projList);
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      applicantList = outer.list();
      
      logger.info("total applicants : " + applicantList.size());
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantsByRequitionIdAndStatus()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return applicantList;
  }
  
  public List getOfferedApplicants(long reqId)
  {
    logger.info("Inside getOfferedApplicants method");
    logger.info("reqId" + reqId);
    
    List applicantList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = null;
      
      outer = session.createCriteria(JobApplicant.class).add(Restrictions.eq("reqId", new Long(reqId))).add(Restrictions.eq("status", "A"));
      

      outer.add(Restrictions.like("interviewState", "%Offer%"));
      
      applicantList = outer.list();
      
      logger.info(Integer.valueOf(applicantList.size()));
    }
    catch (Exception e)
    {
      logger.error("Exception on getOfferedApplicants()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return applicantList;
  }
  
  public int getCountOfOfferedApplicants(long reqId)
  {
    logger.info("Inside getCountOfOfferedApplicants method");
    logger.info("reqId" + reqId);
    
    int totalcount = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = null;
      
      outer = session.createCriteria(JobApplicant.class).add(Restrictions.eq("reqId", new Long(reqId))).add(Restrictions.eq("status", "A"));
      

      outer.add(Restrictions.like("interviewState", "%Offer%"));
      outer.setProjection(Projections.rowCount());
      totalcount = ((Integer)outer.list().get(0)).intValue();
    }
    catch (Exception e)
    {
      logger.error("Exception on getOfferedApplicants()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totalcount;
  }
  
  public List getReferenceCheckApplicantsByRequitionIdandVendorId(long reqId, long userid, String dir_str, String sort_str)
  {
    logger.info("Inside getReferenceCheckApplicantsByRequitionId method");
    logger.info("reqId" + reqId);
    
    List applicantList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      



      Criteria outer = session.createCriteria(JobApplicant.class).add(Restrictions.eq("reqId", Long.valueOf(reqId))).add(Restrictions.eq("vendorId", Long.valueOf(userid))).add(Restrictions.eq("isreferenceadded", Integer.valueOf(1))).add(Restrictions.eq("status", "A"));
      




      ProjectionList projList = Projections.projectionList();
      projList.add(Projections.property("applicantId"));
      projList.add(Projections.property("fullName"));
      projList.add(Projections.property("uuid"));
      

      outer.setProjection(projList);
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      applicantList = outer.list();
      
      logger.info(Integer.valueOf(applicantList.size()));
    }
    catch (Exception e)
    {
      logger.error("Exception on getReferenceCheckApplicantsByRequitionId()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return applicantList;
  }
  
  public List getReferenceCheckApplicantsByRequitionId(long reqId, String dir_str, String sort_str)
  {
    logger.info("Inside getReferenceCheckApplicantsByRequitionId method");
    logger.info("reqId" + reqId);
    
    List applicantList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = session.createCriteria(JobApplicant.class).add(Restrictions.eq("reqId", Long.valueOf(reqId))).add(Restrictions.eq("isreferenceadded", Integer.valueOf(1))).add(Restrictions.eq("status", "A"));
      



      ProjectionList projList = Projections.projectionList();
      projList.add(Projections.property("applicantId"));
      projList.add(Projections.property("fullName"));
      projList.add(Projections.property("uuid"));
      

      outer.setProjection(projList);
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      applicantList = outer.list();
      
      logger.info(Integer.valueOf(applicantList.size()));
    }
    catch (Exception e)
    {
      logger.error("Exception on getReferenceCheckApplicantsByRequitionId()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return applicantList;
  }
  
  public List getOfferAttachmentList(long applicantId, String type)
  {
    logger.info("Inside getOfferAttachmentList method");
    
    List offerAttachmentList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = session.createCriteria(ApplicantOfferAttachment.class).add(Restrictions.eq("applicantId", Long.valueOf(applicantId))).add(Restrictions.eq("type", type));
      


      offerAttachmentList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getOfferAttachmentList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return offerAttachmentList;
  }
  
  public List getOtherBenefitsList(long applicantId)
  {
    logger.info("Inside getOtherBenefitsList method");
    
    List benefitsList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = session.createCriteria(ApplicantOtherBenefits.class).add(Restrictions.eq("applicantId", Long.valueOf(applicantId)));
      

      benefitsList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getOtherBenefitsList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return benefitsList;
  }
  
  public void copyOfferAttachmentInitiateToRelease(JobApplicant applicant, long applicantId, String initiatetype, String releaseType)
  {
    logger.info("Inside getOfferAttachmentList method");
    
    List offerAttachmentList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = session.createCriteria(ApplicantOfferAttachment.class).add(Restrictions.eq("applicantId", Long.valueOf(applicantId))).add(Restrictions.eq("type", initiatetype));
      


      offerAttachmentList = outer.list();
      for (int i = 0; i < offerAttachmentList.size(); i++)
      {
        ApplicantOfferAttachment attach = (ApplicantOfferAttachment)offerAttachmentList.get(i);
        
        attach.setType(releaseType);
        
        session.save(attach);
      }
      applicant.setIsInitiateOfferAttachmentCopied("Y");
      session.update(applicant);
    }
    catch (Exception e)
    {
      logger.error("Exception on getOfferAttachmentList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public List getOfferAttachmentListInitiateAndRelease(long applicantId, String initiatetype, String releaseType)
  {
    logger.info("Inside getOfferAttachmentList method");
    
    List offerAttachmentList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = session.createCriteria(ApplicantOfferAttachment.class).add(Restrictions.eq("applicantId", Long.valueOf(applicantId)));
      


      Criterion type1 = Restrictions.eq("type", initiatetype);
      Criterion type2 = Restrictions.eq("type", releaseType);
      LogicalExpression orExp = Restrictions.or(type1, type2);
      outer.add(orExp);
      offerAttachmentList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getOfferAttachmentList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return offerAttachmentList;
  }
  
  public ApplicantOfferAttachment getOfferAttachmentDetails(long offerAttachmentId)
  {
    logger.info("Inside getOfferAttachmentDetails method");
    
    ApplicantOfferAttachment offattach = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      offattach = (ApplicantOfferAttachment)session.createCriteria(ApplicantOfferAttachment.class).add(Restrictions.eq("applicantattachmentId", Long.valueOf(offerAttachmentId))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getOfferAttachmentDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return offattach;
  }
  
  public ApplicantOfferAttachment getOfferAttachmentDetailsByUuid(String uuid)
  {
    logger.info("Inside getOfferAttachmentDetailsByUuid method");
    
    ApplicantOfferAttachment offattach = null;
    org.hibernate.Session session = null;
    List attachlist = new ArrayList();
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      attachlist = session.createCriteria(ApplicantOfferAttachment.class).add(Restrictions.eq("uuid", uuid)).list();
      if ((attachlist != null) && (attachlist.size() > 0)) {
        offattach = (ApplicantOfferAttachment)attachlist.get(0);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getOfferAttachmentDetailsByUuid()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return offattach;
  }
  
  public void deleteOfferAttachment(long offerAttachmentId)
  {
    logger.info("Inside deleteOfferAttachment method");
    
    ApplicantOfferAttachment offattach = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      String hql = "delete from ApplicantOfferAttachment where applicantattachmentId = :offerattachid";
      Query query = session.createQuery(hql);
      query.setLong("offerattachid", offerAttachmentId);
      int rowCount = query.executeUpdate();
    }
    catch (Exception e)
    {
      int rowCount;
      logger.error("Exception on deleteOfferAttachment()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void deleteOfferAttachment(String uuid)
  {
    logger.info("Inside deleteOfferAttachment method");
    
    ApplicantOfferAttachment offattach = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      String hql = "delete from ApplicantOfferAttachment where uuid = :uuid";
      Query query = session.createQuery(hql);
      query.setString("uuid", uuid);
      int rowCount = query.executeUpdate();
    }
    catch (Exception e)
    {
      int rowCount;
      logger.error("Exception on deleteOfferAttachment()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public Map getSummaryApplicantsForPaginationByReqIdAndState(String reqId, String state, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getSummaryApplicantsForPaginationByReqIdAndState method");
    List applicantList = new ArrayList();
    List newapplicantList = new ArrayList();
    Map searchApplicantMap = new HashMap();
    String sessionId = "";
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String indexsaerchtable = "";
      

      String sqlgen = getSQLApplicants("APPLICANT_SUMMARY_SCREEN");
      String sql = "select ja.application_id,ja.uuid,ja.jb_req_id,ja.offerownerId,ja.full_name,jr.jb_req_code,jr.recruiter_name,(select concat(u.first_name,' ', u.last_name) from user_data u where u.user_id=ja.owner) ownername,ja.owner,(select ug.user_group_name from user_group ug where ug.user_group_id=ja.owner_group) user_group_name,ja.is_group,ja.owner_group,jr.recruiter_id,jr.recruiter_name,jr.is_recruiter_group,ja.filter_error," + sqlgen + " from job_applications ja , job_requisition jr " + indexsaerchtable + " where ja.jb_req_id = jr.jb_req_id " + " and jr.jb_req_id = :jb_req_id ";
      








      String sqlcount = "select count(ja.application_id) as count from job_applications ja , job_requisition jr  " + indexsaerchtable + " where ja.jb_req_id = jr.jb_req_id " + " and jr.jb_req_id = :jb_req_id ";
      



      String orgsqlgen = getOtherSQLApplicants("APPLICANT_SUMMARY_SCREEN");
      if (!StringUtils.isNullOrEmpty(orgsqlgen))
      {
        sql = "select ja.application_id,ja.uuid,ja.jb_req_id,ja.offerownerId,ja.full_name,jr.jb_req_code,jr.recruiter_name,(select concat(u.first_name,' ', u.last_name) from user_data u where u.user_id=ja.owner) ownername,ja.owner,(select ug.user_group_name from user_group ug where ug.user_group_id=ja.owner_group) user_group_name,ja.is_group,ja.owner_group,jr.recruiter_id,jr.recruiter_name,jr.is_recruiter_group,ja.filter_error," + orgsqlgen + "," + sqlgen + " from job_applications ja , job_requisition jr  " + indexsaerchtable + " where ja.jb_req_id = jr.jb_req_id " + " and jr.jb_req_id = :jb_req_id ";
        









        sqlcount = "select count(ja.application_id) as count from job_applications ja , job_requisition jr  " + indexsaerchtable + " where ja.jb_req_id = jr.jb_req_id " + " and jr.jb_req_id = :jb_req_id ";
      }
      logger.info("final sql applicant summary before " + sql);
      if ((state.equals("OnHold")) || (state.equals("Rejected")) || (state.equals("Mark deleted")))
      {
        logger.info("in side hold or mark deleted or rejected state" + state);
        

        sqlcount = sqlcount + " and ja.interview_state like '" + state + "%' " + " and ja.status <> 'A' ";
        sql = sql + " and ja.interview_state like '" + state + "%' " + " and ja.status <> 'A' ";
      }
      else if (state.equals("Reference Check"))
      {
        logger.info("in side state" + state);
        
        sqlcount = sqlcount + " and ja.isreferenceadded = 1" + " and ja.status = 'A' ";
        sql = sql + " and ja.isreferenceadded = 1" + " and ja.status = 'A' ";
      }
      else
      {
        sqlcount = sqlcount + " and ja.status = 'A'";
        sql = sql + " and ja.status = 'A' ";
        if (!state.equals("0"))
        {
          sql = sql + " and ja.interview_state = '" + state + "'";
          sqlcount = sqlcount + " and ja.interview_state = '" + state + "'";
        }
      }
      String tmpcolumnname = (String)Constant.applicantTableBeanColumnNameMap.get(sort_str);
      
      sort_str = "ja." + tmpcolumnname;
      if (tmpcolumnname == null) {
        sort_str = (String)Constant.notApplicantTableBeanColumnNameMap.get(sort_str);
      }
      if (dir_str.equalsIgnoreCase("asc")) {
        sql = sql + "order by " + sort_str + " ASC";
      } else {
        sql = sql + "order by " + sort_str + " DESC";
      }
      logger.info("final sql applicant summary " + sql);
      Query query = session.createSQLQuery(sql);
      query.setLong("jb_req_id", new Long(reqId).longValue());
      query.setFirstResult(startIndex);
      query.setMaxResults(pageSize);
      
      applicantList = query.list();
      

      logger.info("applicantList.size()" + applicantList.size());
      for (int i = 0; i < applicantList.size(); i++)
      {
        Object[] obj = (Object[])applicantList.get(i);
        JobApplicant jobapp = new JobApplicant();
        
        BigInteger appId = (BigInteger)obj[0];
        long applicationId = appId.longValue();
        jobapp.setApplicantId(applicationId);
        jobapp.setUuid((String)obj[1]);
        BigInteger reqId1 = (BigInteger)obj[2];
        long requistionId = reqId1.longValue();
        jobapp.setReqId(requistionId);
        BigInteger offowId = (BigInteger)obj[3];
        long offerownerid = offowId.longValue();
        jobapp.setOfferownerId(offerownerid);
        jobapp.setFullName((String)obj[4]);
        jobapp.setJobCode((String)obj[5]);
        jobapp.setRecruiter((String)obj[6]);
        
        jobapp.ownername = ((String)obj[7]);
        BigInteger ownerId = (BigInteger)obj[8];
        if (ownerId != null) {
          jobapp.ownerId = ownerId.longValue();
        }
        jobapp.ownernamegroup = ((String)obj[9]);
        jobapp.isGroup = ((String)obj[10]);
        BigInteger ownergroupId = (BigInteger)obj[11];
        if (ownergroupId != null) {
          jobapp.ownergroupId = ownergroupId.longValue();
        }
        BigInteger recruiterId = (BigInteger)obj[12];
        if (recruiterId != null) {
          jobapp.recruiterId = recruiterId.longValue();
        }
        jobapp.setRecruiter((String)obj[13]);
        jobapp.recruitergroup = ((String)obj[14]);
        Short filterError = (Short)obj[15];
        jobapp.setFilterError(filterError.intValue());
        int basevalue = 16;
        if (!StringUtils.isNullOrEmpty(orgsqlgen))
        {
          if (isColumnPresent("APPLICANT_SUMMARY_SCREEN", "hiringManager"))
          {
            jobapp.setHiringManager((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("APPLICANT_SUMMARY_SCREEN", "orgName"))
          {
            jobapp.setOrgName((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("APPLICANT_SUMMARY_SCREEN", "department"))
          {
            jobapp.setDepartment((String)obj[basevalue]);
            basevalue += 1;
          }
          if (isColumnPresent("APPLICANT_SUMMARY_SCREEN", "projectcode"))
          {
            jobapp.setProjectcode((String)obj[basevalue]);
            basevalue += 1;
          }
        }
        String sqlExamScore = "select mt.percentage from mocktest mt where mt.applicant_id= :applicationId";
        Query query2 = session.createSQLQuery(sqlExamScore);
        query2.setLong("applicationId", applicationId);
        List score = query2.list();
        logger.info("Exam Score >> " + score.toString());
        






        List tags = new ArrayList();
        
        Criteria outer = session.createCriteria(ApplicantTags.class).add(Restrictions.eq("applicantId", Long.valueOf(applicationId)));
        
        tags = outer.list();
        

        logger.info("tags >> " + tags.toString());
        String tagNames = "";
        for (int k = 0; k < tags.size(); k++)
        {
          ApplicantTags apTags = (ApplicantTags)tags.get(k);
          
          String tag = apTags.getTagname();
          tagNames = tagNames + " " + tag;
        }
        jobapp.setPercentage(score.toString());
        jobapp.setTagsName(tagNames);
        


        jobapp = setApplicantListValues(obj, "APPLICANT_SUMMARY_SCREEN", jobapp, basevalue);
        
        newapplicantList.add(jobapp);
      }
      Query querycount = session.createSQLQuery(sqlcount);
      querycount.setLong("jb_req_id", new Long(reqId).longValue());
      Object obj = querycount.uniqueResult();
      logger.info("obj" + obj);
      BigInteger count = (BigInteger)obj;
      Integer totalsize = new Integer(count.intValue());
      logger.info("totalsize" + totalsize.intValue());
      searchApplicantMap.put(Common.APPLICANT_COUNT, totalsize);
      

      searchApplicantMap.put(Common.APPLICANT_LIST, newapplicantList);
    }
    catch (Exception e)
    {
      logger.error("Exception on getSummaryApplicantsForPaginationByReqIdAndState()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return searchApplicantMap;
  }
  
  public List getApplicantsForPaginationReqIdAndState(String reqId, String state, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getApplicantsForPaginationReqIdAndState method");
    logger.info("** reqId : " + reqId);
    logger.info("** state : " + state);
    List applicantList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = null;
      if ((state.equals("OnHold")) || (state.equals("Rejected")) || (state.equals("Mark deleted")))
      {
        logger.info("in side hold or mark deleted or rejected state" + state);
        

        outer = session.createCriteria(JobApplicant.class).add(Restrictions.eq("reqId", new Long(reqId))).add(Restrictions.ne("status", "A"));
        

        outer.add(Restrictions.like("interviewState", state + "%"));
      }
      else if (state.equals("Reference Check"))
      {
        logger.info("in side state" + state);
        outer = session.createCriteria(JobApplicant.class).add(Restrictions.eq("reqId", new Long(reqId))).add(Restrictions.eq("status", "A"));
        

        outer.add(Restrictions.eq("isreferenceadded", Integer.valueOf(1)));
      }
      else
      {
        outer = session.createCriteria(JobApplicant.class).add(Restrictions.eq("reqId", new Long(reqId))).add(Restrictions.eq("status", "A"));
        if (!state.equals("0")) {
          outer.add(Restrictions.eq("interviewState", state));
        }
      }
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      applicantList = outer.list();
      
      logger.info(Integer.valueOf(applicantList.size()));
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantsForPaginationReqIdAndState()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return applicantList;
  }
  
  public int getCountOfApplicantsForPaginationReqIdAndState(String reqId, String state)
  {
    logger.info("Inside getCountOfApplicantsForPaginationReqIdAndState method");
    
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = null;
      if ((state.equals("OnHold")) || (state.equals("Rejected")) || (state.equals("Mark deleted")))
      {
        logger.info("in side hold and rejected state" + state);
        outer = session.createCriteria(JobApplicant.class).add(Restrictions.eq("reqId", new Long(reqId))).add(Restrictions.ne("status", "A"));
        

        outer.add(Restrictions.like("interviewState", state + "%"));
      }
      else if (state.equals("Reference Check"))
      {
        logger.info("in side state" + state);
        outer = session.createCriteria(JobApplicant.class).add(Restrictions.eq("reqId", new Long(reqId))).add(Restrictions.eq("status", "A"));
        

        outer.add(Restrictions.eq("isreferenceadded", Integer.valueOf(1)));
      }
      else
      {
        outer = session.createCriteria(JobApplicant.class).add(Restrictions.eq("reqId", new Long(reqId))).add(Restrictions.eq("status", "A"));
        if (!state.equals("0")) {
          outer.add(Restrictions.eq("interviewState", state));
        }
      }
      outer.setProjection(Projections.rowCount());
      totaluser = ((Integer)outer.list().get(0)).intValue();
      System.out.println("totalapplicants" + totaluser);
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfApplicantsForPaginationReqIdAndState()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totaluser;
  }
  
  public List getApplicantsByRequitionIdVendorIdAndStatusLike(long reqId, long userid, String status, String dir_str, String sort_str)
  {
    logger.info("Inside getApplicantsByRequitionIdAndStatusLike method");
    logger.info("reqId" + reqId);
    logger.info("status" + status);
    List applicantList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = null;
      if ((status != null) && (status.equals("Rejected"))) {
        outer = session.createCriteria(JobApplicant.class).add(Restrictions.eq("reqId", Long.valueOf(reqId))).add(Restrictions.eq("vendorId", Long.valueOf(userid))).add(Restrictions.like("interviewState", status + "%")).add(Restrictions.eq("status", "R"));
      } else if ((status != null) && (status.equals("OnHold"))) {
        outer = session.createCriteria(JobApplicant.class).add(Restrictions.eq("reqId", Long.valueOf(reqId))).add(Restrictions.eq("vendorId", Long.valueOf(userid))).add(Restrictions.like("interviewState", status + "%")).add(Restrictions.eq("status", "H"));
      } else {
        outer = session.createCriteria(JobApplicant.class).add(Restrictions.eq("reqId", Long.valueOf(reqId))).add(Restrictions.eq("vendorId", Long.valueOf(userid))).add(Restrictions.like("interviewState", status + "%")).add(Restrictions.eq("status", "A"));
      }
      ProjectionList projList = Projections.projectionList();
      projList.add(Projections.property("applicantId"));
      projList.add(Projections.property("fullName"));
      projList.add(Projections.property("uuid"));
      

      outer.setProjection(projList);
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      applicantList = outer.list();
      
      logger.info(Integer.valueOf(applicantList.size()));
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantsByRequitionIdAndStatusLike()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return applicantList;
  }
  
  public List getApplicantsByRequitionIdAndStatusLike(long reqId, String status, String dir_str, String sort_str)
  {
    logger.info("Inside getApplicantsByRequitionIdAndStatusLike method");
    logger.info("reqId" + reqId);
    logger.info("status" + status);
    List applicantList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = null;
      if ((status != null) && (status.equals("Rejected"))) {
        outer = session.createCriteria(JobApplicant.class).add(Restrictions.eq("reqId", Long.valueOf(reqId))).add(Restrictions.like("interviewState", status + "%")).add(Restrictions.eq("status", "R"));
      } else if ((status != null) && (status.equals("OnHold"))) {
        outer = session.createCriteria(JobApplicant.class).add(Restrictions.eq("reqId", Long.valueOf(reqId))).add(Restrictions.like("interviewState", status + "%")).add(Restrictions.eq("status", "H"));
      } else if ((status != null) && (status.equals("Mark deleted"))) {
        outer = session.createCriteria(JobApplicant.class).add(Restrictions.eq("reqId", Long.valueOf(reqId))).add(Restrictions.eq("status", "D"));
      } else {
        outer = session.createCriteria(JobApplicant.class).add(Restrictions.eq("reqId", Long.valueOf(reqId))).add(Restrictions.like("interviewState", status + "%")).add(Restrictions.eq("status", "A"));
      }
      ProjectionList projList = Projections.projectionList();
      projList.add(Projections.property("applicantId"));
      projList.add(Projections.property("fullName"));
      projList.add(Projections.property("uuid"));
      

      outer.setProjection(projList);
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      applicantList = outer.list();
      
      logger.info(Integer.valueOf(applicantList.size()));
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantsByRequitionIdAndStatusLike()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return applicantList;
  }
  
  public List getDistictApplicantState(long reqId)
  {
    logger.info("Inside getDistictApplicantState method");
    List stateList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      stateList = session.createQuery("select distinct a.interviewState from JobApplicant a where a.reqId=" + reqId).list();
      


      logger.info(Integer.valueOf(stateList.size()));
      for (int i1 = 0; i1 < stateList.size(); i1++)
      {
        String statename = (String)stateList.get(i1);
        logger.info("statename" + statename);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getDistictApplicantState()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return stateList;
  }
  
  public List getDistinctRatingName(long applicantId, String qltype)
  {
    logger.info("Inside getDistinctRatingName method");
    List stateList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      stateList = session.createQuery("select distinct a.name from ApplicantRating a where a.applicantId=" + applicantId + " and a.type=" + "'" + qltype + "'").list();
      




      logger.info(Integer.valueOf(stateList.size()));
      for (int i1 = 0; i1 < stateList.size(); i1++)
      {
        String statename = (String)stateList.get(i1);
        logger.info("rating name" + statename);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getDistinctRatingName()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return stateList;
  }
  
  public List getAllApplicantsForRejectionEmail()
  {
    logger.info("Inside getAllApplicantsForRejectionEmail method");
    List applicantList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = session.createCriteria(JobApplicant.class).add(Restrictions.eq("status", "R")).add(Restrictions.eq("isRejectionEmailSent", "N"));
      


      applicantList = outer.list();
      JobApplicant jb;
      for (int i = 0; i < applicantList.size(); i++) {
        jb = (JobApplicant)applicantList.get(i);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllApplicantsForRejectionEmail()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return applicantList;
  }
  
  public List getAllApplicantsForOfferEmail()
  {
    logger.info("Inside getAllApplicantsForOfferEmail method");
    List applicantList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = session.createCriteria(JobApplicant.class).add(Restrictions.eq("status", "A")).add(Restrictions.ne("isOfferEmailSent", "Y")).add(Restrictions.eq("interviewState", "Offer Released"));
      



      applicantList = outer.list();
      JobApplicant jb;
      for (int i = 0; i < applicantList.size(); i++) {
        jb = (JobApplicant)applicantList.get(i);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllApplicantsForOfferEmail()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return applicantList;
  }
  
  public List getAllApplicantsForIndexCreation()
  {
    logger.info("Inside getAllApplicantsForIndexCreation method");
    List applicantList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = session.createCriteria(JobApplicant.class).add(Restrictions.eq("isindexSearchApplied", Integer.valueOf(0)));
      

      applicantList = outer.list();
      JobApplicant jb;
      for (int i = 0; i < applicantList.size(); i++) {
        jb = (JobApplicant)applicantList.get(i);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllApplicantsForIndexCreation()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return applicantList;
  }
  
  public List getAllApplicants()
  {
    logger.info("Inside getAllApplicants method");
    List applicantList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = session.createCriteria(JobApplicant.class);
      
      applicantList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllApplicants()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return applicantList;
  }
  
  public List getApplicantsByRequistionId(long reqId)
  {
    logger.info("Inside getApplicantsByRequistionId method");
    List applicantList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = session.createCriteria(JobApplicant.class).add(Restrictions.eq("reqId", Long.valueOf(reqId))).add(Restrictions.ne("status", "R"));
      


      applicantList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantsByRequistionId()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return applicantList;
  }
  
  public List getApplicantsByOfferReleaseAndAccepted(long reqid)
  {
    logger.info("Inside getApplicantsByOfferReleaseAndAccepted method");
    List applicantList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      List<String> incrilist = new ArrayList();
      incrilist.add("Offer Accepted");
      incrilist.add("Offer Released");
      incrilist.add("On Board - Joined");
      incrilist.add("Offer Initiated-In Approval");
      incrilist.add("Ready for Release Offer");
      incrilist.add("Offer approved by approver");
      incrilist.add("Offer rejected by approver");
      
      Criteria outer = session.createCriteria(JobApplicant.class).add(Restrictions.in("interviewState", incrilist)).add(Restrictions.eq("reqId", Long.valueOf(reqid))).add(Restrictions.eq("status", "A"));
      





      applicantList = outer.list();
      logger.info("size of appliant : " + applicantList.size());
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllApplicants()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return applicantList;
  }
  
  public List getAllApplicantsForDeletion()
  {
    logger.info("Inside getAllApplicantsForDeletion method");
    List applicantList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      int noofday = 0;
      String nofodays = Constant.getValue("applicant.remove.no.of.days.after.mark.delete");
      if (!StringUtils.isNullOrEmpty(nofodays)) {
        noofday = new Integer(nofodays).intValue();
      }
      java.util.Date dt = new java.util.Date();
      dt.setDate(dt.getDate() - noofday);
      
      Criteria outer = session.createCriteria(JobApplicant.class).add(Restrictions.eq("status", "D")).add(Restrictions.lt("updatedDate", dt)).add(Restrictions.like("interviewState", "Mark deleted%"));
      




      applicantList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllApplicantsForDeletion()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return applicantList;
  }
  
  public List getAllResignedApplicantsNotProcessed()
  {
    logger.info("Inside getAllResignedApplicantsNotProcessed method");
    List applicantList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = session.createCriteria(JobApplicant.class).add(Restrictions.eq("isRegignationSchedularProcessed", "N")).add(Restrictions.eq("interviewState", "RESIGNED"));
      


      applicantList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllResignedApplicantsNotProcessed()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return applicantList;
  }
  
  public ApplicantUser getApplicantByEmail(String emailid, String applicantCode)
  {
    logger.info("Inside getApplicantByEmail method");
    logger.info("email id : " + emailid);
    
    org.hibernate.Session session = null;
    ApplicantUser applicant = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      applicant = (ApplicantUser)session.createCriteria(ApplicantUser.class).add(Restrictions.eq("emailId", emailid)).add(Restrictions.eq("applicantCode", applicantCode)).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getRefferalEmployeeByEmail()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return applicant;
  }
  
  public List getActivityList(long applicantId, String uuid)
  {
    logger.info("Inside getActivityList method");
    
    List activityList = new ArrayList();
    org.hibernate.Session session = null;
    ApplicantUser applicant = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      activityList = session.createCriteria(ApplicantActivity.class).add(Restrictions.eq("applicantId", Long.valueOf(applicantId))).add(Restrictions.eq("uuid", uuid)).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getActivityList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return activityList;
  }
  
  public List getApplicantListByEmailIdWithoutCurrent(String emailId, long applicantId, long superUserKey)
  {
    logger.info("Inside getApplicantListByEmailIdWithoutCurrent method");
    List applist = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      



      String sql = "select application_id,full_name,email_id,uuid from job_applications where email_id=:email_id and application_id <> :application_id and super_user_key=:super_user_key order by created_date desc";
      Query query = session.createSQLQuery(sql);
      query.setString("email_id", emailId);
      query.setLong("application_id", applicantId);
      query.setLong("super_user_key", superUserKey);
      logger.info(query.toString());
      List aList = query.list();
      logger.info(Integer.valueOf(aList.size()));
      if ((aList != null) && (aList.size() > 0)) {
        for (int i = 0; i < aList.size(); i++)
        {
          JobApplicant applicant = new JobApplicant();
          Object[] obj = (Object[])aList.get(i);
          BigInteger id = (BigInteger)obj[0];
          long idv = id.longValue();
          
          applicant.setApplicantId(idv);
          applicant.setFullName((String)obj[1]);
          applicant.setEmail((String)obj[2]);
          applicant.setUuid((String)obj[3]);
          
          applist.add(applicant);
        }
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantListByEmailIdWithoutCurrent()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return applist;
  }
  
  public ApplicantActivity get2ndLastActivity(long applicantId, String uuid)
  {
    logger.info("Inside get2ndLastActivity method");
    
    List activityList = new ArrayList();
    ApplicantActivity activity = null;
    org.hibernate.Session session = null;
    ApplicantUser applicant = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      activityList = session.createCriteria(ApplicantActivity.class).add(Restrictions.eq("applicantId", Long.valueOf(applicantId))).add(Restrictions.eq("uuid", uuid)).addOrder(Order.desc("activityId")).list();
      



      activity = (ApplicantActivity)activityList.get(1);
    }
    catch (Exception e)
    {
      logger.error("Exception on get2ndLastActivity()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return activity;
  }
  
  public JobApplicationEvent getLastEvent(long applicantId)
  {
    logger.info("Inside getLastEvent method");
    
    List eventList = new ArrayList();
    JobApplicationEvent event = null;
    org.hibernate.Session session = null;
    ApplicantUser applicant = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      eventList = session.createCriteria(JobApplicationEvent.class).createAlias("applicant", "applicant").add(Restrictions.eq("applicant.applicantId", Long.valueOf(applicantId))).addOrder(Order.desc("jobAppEventId")).list();
      if ((eventList != null) && (eventList.size() > 0)) {
        event = (JobApplicationEvent)eventList.get(0);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getLastEvent()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return event;
  }
  
  public ApplicantActivity getLastApplicantActivity(long applicantId, String uuid)
  {
    logger.info("Inside getLastApplicantActivity method");
    
    List activityList = new ArrayList();
    ApplicantActivity activity = null;
    org.hibernate.Session session = null;
    ApplicantUser applicant = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      activityList = session.createCriteria(ApplicantActivity.class).add(Restrictions.eq("applicantId", Long.valueOf(applicantId))).add(Restrictions.eq("uuid", uuid)).addOrder(Order.desc("createdDate")).list();
      if ((activityList != null) && (activityList.size() > 0)) {
        activity = (ApplicantActivity)activityList.get(0);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getLastApplicantActivity()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return activity;
  }
  
  public Set getUniqueActivityList(long applicantId, String uuid)
  {
    logger.info("Inside getUniqueActivityList method");
    
    List activityList = new ArrayList();
    List stateList = new ArrayList();
    Set activitySet = new TreeSet();
    org.hibernate.Session session = null;
    ApplicantUser applicant = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      activityList = session.createCriteria(ApplicantActivity.class).add(Restrictions.eq("applicantId", Long.valueOf(applicantId))).add(Restrictions.eq("uuid", uuid)).addOrder(Order.asc("activityId")).list();
      



      logger.info("activityList.size()" + activityList.size());
      for (int i = 0; i < activityList.size(); i++)
      {
        ApplicantActivity activity = (ApplicantActivity)activityList.get(i);
        
        logger.info(activity.getActivityName());
        if ((!stateList.contains(activity.getActivityName())) || (!activity.getActivityName().equals("Application Submitted")))
        {
          stateList.add(activity.getActivityName());
          activitySet.add(activity);
        }
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getUniqueActivityList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return activitySet;
  }
  
  public Set getUniqueDisplaybleAppPageActivityList(long applicantId, String uuid)
  {
    logger.info("Inside getUniqueDisplaybleAppPageActivityList method");
    
    List activityList = new ArrayList();
    List stateList = new ArrayList();
    Set activitySet = new TreeSet();
    org.hibernate.Session session = null;
    ApplicantUser applicant = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      activityList = session.createCriteria(ApplicantActivity.class).add(Restrictions.eq("applicantId", Long.valueOf(applicantId))).add(Restrictions.eq("uuid", uuid)).add(Restrictions.eq("isDisplayAppPage", "Y")).addOrder(Order.asc("activityId")).list();
      




      logger.info("activityList.size()" + activityList.size());
      for (int i = 0; i < activityList.size(); i++)
      {
        ApplicantActivity activity = (ApplicantActivity)activityList.get(i);
        
        logger.info(activity.getActivityName());
        if ((!stateList.contains(activity.getActivityName())) || (!activity.getActivityName().equals("Application Submitted")))
        {
          stateList.add(activity.getActivityName());
          activitySet.add(activity);
        }
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getUniqueDisplaybleAppPageActivityList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return activitySet;
  }
  
  public String isEmailIdExist(String emailId)
  {
    logger.info("Inside isEmailIdExist method");
    String email = null;
    org.hibernate.Session session = null;
    JobApplicant usr = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String hql = "select email from JobApplicant where email = :email";
      Query query = session.createQuery(hql);
      query.setString("email", emailId);
      
      Object ob = query.uniqueResult();
      if (ob != null) {
        email = (String)ob;
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on isEmployeeCodeExist()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return email;
  }
  
  public ApplicantUser getApplicantUser(long applicantUserId)
  {
    logger.info("Inside getApplicantUser method");
    User user = null;
    org.hibernate.Session session = null;
    ApplicantUser usr = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      usr = (ApplicantUser)session.createCriteria(ApplicantUser.class).add(Restrictions.eq("applicantUserId", Long.valueOf(applicantUserId))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantUser()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return usr;
  }
  
  public ApplicantUser getApplicantUserByApplicantId(long applicantId)
  {
    logger.info("Inside getApplicantUserByApplicantId method");
    User user = null;
    org.hibernate.Session session = null;
    ApplicantUser usr = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      usr = (ApplicantUser)session.createCriteria(ApplicantUser.class).add(Restrictions.eq("applicant.applicantId", Long.valueOf(applicantId))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantUserByApplicantId()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return usr;
  }
  
  public void markDeleteApplicantsBySqlQuery(List applicaantList, User user1, String comment)
  {
    logger.info("Inside updateApplicantsBySqlQuery method");
    Connection con = null;
    PreparedStatement pstmt = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      con = session.connection();
      
      String applicantids = "";
      for (int i = 0; i < applicaantList.size(); i++)
      {
        String uuid = (String)applicaantList.get(i);
        applicantids = applicantids + "'" + uuid + "'" + ",";
      }
      if (!StringUtils.isNullOrEmpty(applicantids)) {
        applicantids = applicantids.substring(0, applicantids.length() - 1);
      }
      String sql = "update job_applications set status='D' , updated_by= '" + user1.getUserName() + "'" + " , updated_date=sysdate(), interview_state=(select CONCAT('Mark deleted-', interview_state) as interview_state)  where " + "uuid in ( " + applicantids + ") ";
      




      logger.info("sql" + sql);
      pstmt = con.prepareStatement(sql);
      
      pstmt.execute();
      


      sql = "update task_data set status='C' where idvalue in ( select application_id from job_applications where uuid in (" + applicantids + ") )  and task_type in ('Applicant Review','Offer Approval','Reference Check','Offer Release','Applicant In Queue','OnBoarding')";
      


      pstmt = con.prepareStatement(sql);
      
      pstmt.execute();
      logger.info("sql" + sql);
      
      String insql = " insert into applicant_activity (applicant_id, applicant_name, uuid, created_by, created_by_type, created_date, by_user_id, by_user_name, activity_name, comment, is_display_apppage)  values((select application_id from job_applications where uuid = ?) ,(select full_name from job_applications where uuid = ?),?,?,'Internal',?,?,?,?,?,'Y');";
      for (int i = 0; i < applicaantList.size(); i++)
      {
        String uuid = (String)applicaantList.get(i);
        pstmt = con.prepareStatement(insql);
        pstmt.setString(1, uuid);
        pstmt.setString(2, uuid);
        pstmt.setString(3, uuid);
        pstmt.setString(4, user1.getUserName());
        pstmt.setDate(5, new java.sql.Date(new java.util.Date().getTime()));
        pstmt.setLong(6, user1.getUserId());
        pstmt.setString(7, user1.getFirstName() + " " + user1.getLastName());
        
        pstmt.setString(8, "Applicant marked for deletion");
        pstmt.setString(9, comment);
        
        pstmt.execute();
      }
      pstmt.close();
      con.commit();
      con.close(); return;
    }
    catch (Exception e)
    {
      logger.error("Exception on updateApplicantsBySqlQuery()", e);
    }
    finally
    {
      logger.info("Inside updateApplicantsBySqlQuery method");
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
  
  public Map searchApplicantPaginationByReqIdAndVendorId(User user, String requistionId, String state, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside searchApplicantPaginationByReqIdAndVendorId method");
    
    List applicantList = new ArrayList();
    
    Map searchJobMap = new HashMap();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = null;
      if ((state.equals("OnHold")) || (state.equals("Rejected")))
      {
        logger.info("in side hold and rejected state " + state);
        outer = session.createCriteria(JobApplicant.class).add(Restrictions.eq("reqId", new Long(requistionId))).add(Restrictions.eq("vendorId", Long.valueOf(user.getUserId()))).add(Restrictions.ne("status", "A"));
        


        outer.add(Restrictions.like("interviewState", state + "%"));
      }
      else if (state.equals("Reference Check"))
      {
        logger.info("in side state " + state);
        outer = session.createCriteria(JobApplicant.class).add(Restrictions.eq("reqId", new Long(requistionId))).add(Restrictions.eq("vendorId", Long.valueOf(user.getUserId()))).add(Restrictions.eq("status", "A"));
        


        outer.add(Restrictions.eq("isreferenceadded", Integer.valueOf(1)));
      }
      else
      {
        outer = session.createCriteria(JobApplicant.class).add(Restrictions.eq("reqId", new Long(requistionId))).add(Restrictions.eq("vendorId", Long.valueOf(user.getUserId()))).add(Restrictions.eq("status", "A"));
        if (!state.equals("0")) {
          outer.add(Restrictions.eq("interviewState", state));
        }
      }
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      logger.info("startIndex : " + startIndex);
      logger.info("pageSize : " + pageSize);
      
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      applicantList = outer.list();
      
      searchJobMap.put(Common.JOBS_LIST, applicantList);
      

      outer = null;
      if ((state.equals("OnHold")) || (state.equals("Rejected")))
      {
        logger.info("in side hold and rejected state " + state);
        outer = session.createCriteria(JobApplicant.class).add(Restrictions.eq("reqId", new Long(requistionId))).add(Restrictions.eq("vendorId", Long.valueOf(user.getUserId()))).add(Restrictions.ne("status", "A"));
        


        outer.add(Restrictions.like("interviewState", state + "%"));
      }
      else if (state.equals("Reference Check"))
      {
        logger.info("in side state " + state);
        outer = session.createCriteria(JobApplicant.class).add(Restrictions.eq("reqId", new Long(requistionId))).add(Restrictions.eq("vendorId", Long.valueOf(user.getUserId()))).add(Restrictions.eq("status", "A"));
        


        outer.add(Restrictions.eq("isreferenceadded", Integer.valueOf(1)));
      }
      else
      {
        outer = session.createCriteria(JobApplicant.class).add(Restrictions.eq("reqId", new Long(requistionId))).add(Restrictions.eq("vendorId", Long.valueOf(user.getUserId()))).add(Restrictions.eq("status", "A"));
        if (!state.equals("0")) {
          outer.add(Restrictions.eq("interviewState", state));
        }
      }
      List totallist = outer.list();
      searchJobMap.put(Common.JOBS_COUNT, Integer.valueOf(totallist.size()));
    }
    catch (Exception e)
    {
      logger.error("Exception on searchApplicantPaginationByReqIdAndVendorId()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return searchJobMap;
  }
  
  public List getActionAttachmentList(long idvalue, String actionName)
  {
    logger.info("Inside getActionAttachmentList method");
    
    List attachmentList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = session.createCriteria(ActionsAttachment.class).add(Restrictions.eq("idvalue", Long.valueOf(idvalue))).add(Restrictions.eq("action", actionName));
      

      attachmentList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getActionAttachmentList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return attachmentList;
  }
  
  public void saveActionAttachment(ActionsAttachment attachment)
  {
    logger.info("Inside saveActionAttachment method");
    User user = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      session.save(attachment);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveActionAttachment()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void saveApplicantAttachment(ApplicantAttachments attachment)
  {
    logger.info("Inside saveApplicantAttachment method");
    User user = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      session.save(attachment);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveApplicantAttachment()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void updateApplicantAttachment(ApplicantAttachments attachment)
  {
    logger.info("Inside updateApplicantAttachment method");
    User user = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      session.update(attachment);
    }
    catch (Exception e)
    {
      logger.error("Exception on updateApplicantAttachment()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void deleteApplicantAttachment(long applicantId, String type)
  {
    logger.info("Inside deleteApplicantAttachment method");
    String hql = "delete from ApplicantAttachments where applicantId = :applicantId  and type = :type";
    Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
    query.setLong("applicantId", applicantId);
    query.setString("type", type);
    int rowCount = query.executeUpdate();
  }
  
  public void deleteActionAttachment(String uuid)
  {
    logger.info("Inside deleteActionAttachment method");
    
    ActionsAttachment attach = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = session.createCriteria(ActionsAttachment.class).add(Restrictions.eq("uuid", uuid));
      
      attach = (ActionsAttachment)outer.uniqueResult();
      
      String hql = "delete from ActionsAttachment where uuid = :uuid ";
      Query query = session.createQuery(hql);
      query.setString("uuid", uuid);
      int rowCount = query.executeUpdate();
    }
    catch (Exception e)
    {
      int rowCount;
      logger.error("Exception on deleteActionAttachment()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public ApplicantProfilePhoto saveApplicantProfilePhoto(ApplicantProfilePhoto pphoto)
  {
    logger.info("Inside saveApplicantProfilePhoto method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      session.saveOrUpdate(pphoto);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveApplicantProfilePhoto()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return pphoto;
  }
  
  public byte[] getApplicantProfilePhoto(long profilePhotoId)
  {
    logger.info("Inside getApplicantProfilePhoto method");
    ApplicantProfilePhoto pp = null;
    org.hibernate.Session session = null;
    
    byte[] imgData = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      pp = (ApplicantProfilePhoto)session.createCriteria(ApplicantProfilePhoto.class).add(Restrictions.eq("profilePhotoId", Long.valueOf(profilePhotoId))).uniqueResult();
      



      Blob img = pp.getProfilePhoto();
      
      imgData = img.getBytes(1L, (int)img.length());
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantProfilePhoto()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return imgData;
  }
  
  public List getAllOfferDeclinedResons()
  {
    logger.info("Inside getAllOfferDeclinedResons method");
    List roleList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      roleList = session.createCriteria(DeclinedResons.class).add(Restrictions.eq("resonType", "offer_decline")).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllOfferDeclinedResons()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return roleList;
  }
  
  public INineFormBean saveINineFormData(INineFormBean iNineFormBean)
  {
    logger.info("Inside saveINineFormData method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      session.save(iNineFormBean);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveINineFormData()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return iNineFormBean;
  }
  
  public INineFormBean updateINineFormData(INineFormBean iNineFormBean)
  {
    logger.info("Inside updateINineFormData method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      session.update(iNineFormBean);
    }
    catch (Exception e)
    {
      logger.error("Exception on updateINineFormData()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return iNineFormBean;
  }
  
  public List getApplicantsForBathEmail(String jobId, String[] selecteinterviewstate)
  {
    logger.info("Inside getApplicantsForBathEmail method");
    List applicantList = new ArrayList();
    List allApplicantList = new ArrayList();
    JobApplicant jobapplicant = new JobApplicant();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      if (selecteinterviewstate != null) {
        for (int i = 0; i < selecteinterviewstate.length; i++)
        {
          applicantList = session.createCriteria(JobApplicant.class).add(Restrictions.eq("reqId", Long.valueOf(new Long(jobId).longValue()))).add(Restrictions.eq("interviewState", selecteinterviewstate[i])).list();
          if ((applicantList != null) && (applicantList.size() > 0))
          {
            Iterator itr = applicantList.iterator();
            while (itr.hasNext())
            {
              JobApplicant job = (JobApplicant)itr.next();
              allApplicantList.add(job);
            }
          }
        }
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantsForBathEmail()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return allApplicantList;
  }
  
  public List getApplicantsForBathEmail(String jobId, List<String> selecteinterviewstate)
  {
    logger.info("Inside getApplicantsForBathEmail method");
    List applicantList = new ArrayList();
    List newapplicantList = new ArrayList();
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      if (selecteinterviewstate != null)
      {
        String sql = "select ja.application_id,ja.full_name,ja.jb_req_id,ja.req_name,ja.interview_state,ja.targetjoining_date,ja.isInitiateJoiningProcess,ja.uuid,ja.offerownerId,ja.offerownerName,ja.email_id,ja.city,ja.heighest_qualification,ja.applied_datetime,ja.referer_name, ja.created_by,ja.phone from job_applications ja  where ja.jb_req_id = :jb_req_id " + buildInterviewStates(selecteinterviewstate);
        



        Query query = session.createSQLQuery(sql);
        query.setLong("jb_req_id", new Long(jobId).longValue());
        

        applicantList = query.list();
        
        logger.info("applicantList.size()" + applicantList.size());
        for (int i = 0; i < applicantList.size(); i++)
        {
          Object[] obj = (Object[])applicantList.get(i);
          JobApplicant jobapp = new JobApplicant();
          
          BigInteger appId = (BigInteger)obj[0];
          long applicationId = appId.longValue();
          
          jobapp.setApplicantId(applicationId);
          jobapp.setFullName((String)obj[1]);
          BigInteger reqId = (BigInteger)obj[2];
          long requistionId = reqId.longValue();
          jobapp.setReqId(requistionId);
          jobapp.setJobTitle((String)obj[3]);
          jobapp.setInterviewState((String)obj[4]);
          java.util.Date targetjoingdate = (java.util.Date)obj[5];
          String targetjd = "";
          if (targetjoingdate != null)
          {
            String datepattern = Constant.getValue("defaultdateformat");
            
            targetjd = DateUtil.convertDateToStringDate(targetjoingdate, datepattern);
          }
          jobapp.setTargetjoiningdateStr(targetjd);
          jobapp.setInitiateJoiningProcess((String)obj[6]);
          jobapp.setUuid((String)obj[7]);
          BigInteger offowId = (BigInteger)obj[8];
          long offerownerid = offowId.longValue();
          jobapp.setOfferownerId(offerownerid);
          jobapp.setOfferownerName((String)obj[9]);
          jobapp.setEmail((String)obj[10]);
          jobapp.setCity((String)obj[11]);
          jobapp.setHeighestQualification((String)obj[12]);
          jobapp.setAppliedDate((java.util.Date)obj[13]);
          jobapp.setReferrerName((String)obj[14]);
          jobapp.setCreatedBy((String)obj[15]);
          jobapp.setPhone((String)obj[16]);
          newapplicantList.add(jobapp);
        }
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantsForBathEmail()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return newapplicantList;
  }
  
  private static String buildInterviewStates(List<String> lst)
  {
    String str = " and ( ";
    for (int i = 0; i < lst.size(); i++) {
      str = str + "  ja.interview_state like '%" + (String)lst.get(i) + "%' OR";
    }
    str = str.substring(0, str.length() - 2) + ")";
    
    return str;
  }
  
  public List getListOfZipCodes(long zipCode, double distance)
  {
    logger.info("Inside getListOfZipCodes method");
    List<Long> zipcodeList = new ArrayList();
    double lati = 0.0D;
    double longi = 0.0D;
    double dbDist = 0.0D;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      ZipCode zipcode = (ZipCode)session.createCriteria(ZipCode.class).add(Restrictions.eq("zip_code", String.valueOf(zipCode))).uniqueResult();
      if (zipcode != null)
      {
        lati = zipcode.getLat();
        longi = zipcode.getLon();
        
        ProximityUtil proxUtil = new ProximityUtil(lati, longi, distance);
        
        List ziplist = session.createCriteria(ZipCode.class).add(Restrictions.ge("lat", Double.valueOf(proxUtil.MinLatitude()))).add(Restrictions.le("lat", Double.valueOf(proxUtil.MaxLatitude()))).add(Restrictions.ge("lon", Double.valueOf(proxUtil.MinLongitude()))).add(Restrictions.le("lon", Double.valueOf(proxUtil.MaxLongitude()))).list();
        if (ziplist != null) {
          for (int i = 0; i < ziplist.size(); i++)
          {
            ZipCode zp = (ZipCode)ziplist.get(i);
            
            dbDist = proxUtil.distanceCalc(lati, longi, zp.getLat(), zp.getLon(), Common.DISTANCE_UNIT_MILES);
            if (dbDist <= distance) {
              zipcodeList.add(new Long(zp.getZip_code()));
            }
          }
        }
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getListOfZipCodes()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return zipcodeList;
  }
  
  public void updatestatusMultiple(String applicantUserIds, String status)
  {
    logger.info("Inside updatestatusMultiple method >>> ");
    logger.info("applicantUserIds >> " + applicantUserIds);
    
    String[] result = applicantUserIds.split(",");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      for (int x = 0; x < result.length; x++)
      {
        logger.info(">> " + result[x]);
        String id = result[x];
        ApplicantUser applicantUser = getApplicantUserByApplicantId(new Long(id).longValue());
        applicantUser.setStatus(status);
        session.update(applicantUser);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on updatestatusMultiple()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void deleteApplicantUserMultiple(String applicantUserIds)
  {
    logger.info("Inside deleteApplicantUserMultiple method >>> ");
    logger.info("applicantUserIds >> " + applicantUserIds);
    
    String[] result = applicantUserIds.split(",");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      for (int x = 0; x < result.length; x++)
      {
        logger.info(">> " + result[x]);
        String id = result[x];
        ApplicantUser applicantUser = getApplicantUserByApplicantId(new Long(id).longValue());
        
        session.delete(applicantUser);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteApplicantUserMultiple()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
}
