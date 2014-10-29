package com.dao;

import com.bean.JobApplicant;
import com.bean.JobApplicationEvent;
import com.bean.JobRequisition;
import com.bean.lov.KeyValue;
import com.bean.report.ReviewerPerformance;
import java.math.BigInteger;
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
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class DashBoardDAO
  extends HibernateDaoSupport
{
  protected static final Logger logger = Logger.getLogger(DashBoardDAO.class);
  
  public List getAllReviewerIdsFromJobApplicantionsEvent()
  {
    logger.info("Inside getAllReviewersFromJobApplicantionsEvent method");
    Session session = null;
    List agList = new ArrayList();
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String sql = "SELECT distinct owner FROM job_application_events";
      
      Query query = session.createSQLQuery(sql);
      

      agList = query.list();
      

      logger.info("revierer size" + agList.size());
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllReviewersFromJobApplicantionsEvent()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return agList;
  }
  
  public Set getApplicantsReviewedDistinctYearsName(long reviewerId)
  {
    logger.info("Inside getApplicantsReviewedDistinctYearsName method");
    Session session = null;
    Set years = new HashSet();
    List agList = new ArrayList();
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String hql = "select a.createdDate from JobApplicationEvent a join a.owner ow where ow.userId = :reviewerId and a.eventType != 0";
      Query query = session.createQuery(hql);
      query.setLong("reviewerId", reviewerId);
      
      agList = query.list();
      for (int i = 0; i < agList.size(); i++)
      {
        Date dt = (Date)agList.get(i);
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        years.add(new Integer(cal.get(1)));
      }
      logger.info("years" + years.size());
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantsReviewedDistinctYearsName()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return years;
  }
  
  public Set getDistinctYearsNameOfRequistionsByHiringMgr(long hiringMgrId)
  {
    logger.info("Inside getDistinctYearsNameOfRequistionsByHiringMgr method");
    Session session = null;
    Set years = new HashSet();
    List agList = new ArrayList();
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String hql = "select a.createdDate from JobRequisition a join a.hiringmgr ow where ow.userId = :hiringMgrId ";
      Query query = session.createQuery(hql);
      query.setLong("hiringMgrId", hiringMgrId);
      
      agList = query.list();
      for (int i = 0; i < agList.size(); i++)
      {
        Date dt = (Date)agList.get(i);
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        years.add(new Integer(cal.get(1)));
      }
      logger.info("years" + years.size());
    }
    catch (Exception e)
    {
      logger.error("Exception on getDistinctYearsNameOfRequistionsByHiringMgr()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return years;
  }
  
  public Set getDistinctYearsNameOfRequistionsByRecruiter(long recruiterId)
  {
    logger.info("Inside getDistinctYearsNameOfRequistionsByRecruiter method");
    Session session = null;
    Set years = new HashSet();
    List agList = new ArrayList();
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String hql = "select a.createdDate from JobRequisition a  where a.recruiterId = :recruiterId ";
      Query query = session.createQuery(hql);
      query.setLong("recruiterId", recruiterId);
      
      agList = query.list();
      for (int i = 0; i < agList.size(); i++)
      {
        Date dt = (Date)agList.get(i);
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        years.add(new Integer(cal.get(1)));
      }
      logger.info("years" + years.size());
    }
    catch (Exception e)
    {
      logger.error("Exception on getDistinctYearsNameOfRequistionsByRecruiter()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return years;
  }
  
  public List getDistinctRequistionIdsOpenByHiringMgr(long hiringMgrId)
  {
    logger.info("Inside getDistinctRequistionIdsOpenByHiringMgr method");
    Session session = null;
    List agList = new ArrayList();
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String hql = "select a.jobreqId,a.jobreqName from JobRequisition a join a.hiringmgr ow where ow.userId = :hiringMgrId and a.status=:status and a.state=:state";
      Query query = session.createQuery(hql);
      query.setLong("hiringMgrId", hiringMgrId);
      query.setString("status", "Open");
      query.setString("state", "Active");
      
      agList = query.list();
      

      logger.info("requistions" + agList.size());
    }
    catch (Exception e)
    {
      logger.error("Exception on getDistinctYearsNameOfRequistionsByHiringMgr()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return agList;
  }
  
  public List getDistinctRequistionIdsOpenByRecruiter(long recruiterId)
  {
    logger.info("Inside getDistinctRequistionIdsOpenByRecruiter method");
    Session session = null;
    List agList = new ArrayList();
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String hql = "select a.jobreqId,a.jobreqName from JobRequisition a where a.recruiterId = :recruiterId and a.status=:status and a.state=:state";
      Query query = session.createQuery(hql);
      query.setLong("recruiterId", recruiterId);
      query.setString("status", "Open");
      query.setString("state", "Active");
      
      agList = query.list();
      

      logger.info("requistions" + agList.size());
    }
    catch (Exception e)
    {
      logger.error("Exception on getDistinctRequistionIdsOpenByRecruiter()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return agList;
  }
  
  public Set getApplicantsPerformanceDistinctYearsName(long reviewerId, String type)
  {
    logger.info("Inside getApplicantsPerformanceDistinctYearsName method");
    Session session = null;
    Set years = new HashSet();
    List agList = new ArrayList();
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String hql = "select a.yearValue from ReviewerPerformance a where a.reviewerId = :reviewerId and a.reviewerType= :type";
      Query query = session.createQuery(hql);
      query.setLong("reviewerId", reviewerId);
      query.setString("type", type);
      
      agList = query.list();
      for (int i = 0; i < agList.size(); i++)
      {
        Integer y = (Integer)agList.get(i);
        years.add(y);
      }
      logger.info("years" + years.size());
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantsPerformanceDistinctYearsName()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return years;
  }
  
  public Set getApplicantsScreeninedDistinctYearsName(long reviewerId)
  {
    logger.info("Inside getApplicantsScreeninedDistinctYearsName method");
    Session session = null;
    Set years = new HashSet();
    List agList = new ArrayList();
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String hql = "select a.createdDate from JobApplicationEvent a join a.owner ow where ow.userId = :reviewerId and a.eventType = 0";
      Query query = session.createQuery(hql);
      query.setLong("reviewerId", reviewerId);
      
      agList = query.list();
      for (int i = 0; i < agList.size(); i++)
      {
        Date dt = (Date)agList.get(i);
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        years.add(new Integer(cal.get(1)));
      }
      logger.info("years" + years.size());
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantsScreeninedDistinctYearsName()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return years;
  }
  
  public List getApplicantsStateAndCountByReviewerAndYear(long reviewerId, int year)
  {
    logger.info("Inside getApplicantsStateAndCountByReviewerAndYear method");
    long total = 0L;
    List statewithcountlist = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String hql = "select a.interviewState from JobApplicationEvent a join a.owner ow where ow.userId = :reviewerId and year(a.createdDate) = :year and a.eventType != 0";
      Query query = session.createQuery(hql);
      query.setLong("reviewerId", reviewerId);
      query.setInteger("year", year);
      
      List intstateList = query.list();
      KeyValue keyv = new KeyValue();
      keyv.setKey("Total Applicants Reviewed");
      keyv.setValue(String.valueOf(intstateList.size()));
      statewithcountlist.add(keyv);
      int clearedcount = 0;
      int failedcount = 0;
      for (int i = 0; i < intstateList.size(); i++)
      {
        String interviewstate = (String)intstateList.get(i);
        if ((interviewstate != null) && (interviewstate.indexOf("Cleared") != -1)) {
          clearedcount++;
        }
        if ((interviewstate != null) && (interviewstate.indexOf("Rejected") != -1)) {
          failedcount++;
        }
      }
      KeyValue keyv1 = new KeyValue();
      keyv1.setKey("Total Applicants Cleared On Review");
      keyv1.setValue(String.valueOf(clearedcount));
      statewithcountlist.add(keyv1);
      
      KeyValue keyv2 = new KeyValue();
      keyv2.setKey("Total Applicants Failed On Review");
      keyv2.setValue(String.valueOf(failedcount));
      statewithcountlist.add(keyv2);
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantsStateAndCountByReviewerAndYear()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return statewithcountlist;
  }
  
  public List getRequistionsStateAndCountByHiringMgrAndYear(long hiringMgrId, int year)
  {
    logger.info("Inside getRequistionsStateAndCountByHiringMgrAndYear method");
    long total = 0L;
    List statewithcountlist = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String hql = "select a.status, a.state from JobRequisition a join a.hiringmgr ow where ow.userId = :hiringMgrId and year(a.createdDate) = :year";
      Query query = session.createQuery(hql);
      query.setLong("hiringMgrId", hiringMgrId);
      query.setInteger("year", year);
      
      List intstateList = query.list();
      KeyValue keyv = new KeyValue();
      keyv.setKey("Total Requisitions Created");
      keyv.setValue(String.valueOf(intstateList.size()));
      statewithcountlist.add(keyv);
      int publishedcount = 0;
      for (int i = 0; i < intstateList.size(); i++)
      {
        Object[] obj = (Object[])intstateList.get(i);
        String status = (String)obj[0];
        String state = (String)obj[1];
        if ((state != null) && (state.equals("Active"))) {
          publishedcount++;
        }
      }
      KeyValue keyv1 = new KeyValue();
      keyv1.setKey("Total Requisitions Published");
      keyv1.setValue(String.valueOf(publishedcount));
      statewithcountlist.add(keyv1);
      
      int others = intstateList.size() - publishedcount;
      KeyValue keyv2 = new KeyValue();
      keyv2.setKey("Total Requisitions Not Published");
      keyv2.setValue(String.valueOf(others));
      statewithcountlist.add(keyv2);
    }
    catch (Exception e)
    {
      logger.error("Exception on getRequistionsStateAndCountByHiringMgrAndYear()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return statewithcountlist;
  }
  
  public List getRequistionsStateAndCountByRecruiterAndYear(long recruiterId, int year)
  {
    logger.info("Inside getRequistionsStateAndCountByRecruiterAndYear method");
    long total = 0L;
    List statewithcountlist = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String hql = "select a.status, a.state, a.closedByName, a.closedDate, a.targetfinishdate from JobRequisition a  where a.recruiterId = :recruiterId and year(a.createdDate) = :year";
      Query query = session.createQuery(hql);
      query.setLong("recruiterId", recruiterId);
      query.setInteger("year", year);
      
      List intstateList = query.list();
      KeyValue keyv = new KeyValue();
      keyv.setKey("Requisitions Assigned");
      keyv.setValue(String.valueOf(intstateList.size()));
      statewithcountlist.add(keyv);
      
      int totalomntarget = 0;
      int totalmissedtarget = 0;
      for (int i = 0; i < intstateList.size(); i++)
      {
        Object[] obj = (Object[])intstateList.get(i);
        String status = (String)obj[0];
        String state = (String)obj[1];
        String closebyname = (String)obj[2];
        Date closedate = (Date)obj[3];
        Date targetfinsihdate = (Date)obj[4];
        logger.info("closedate" + closedate);
        logger.info("targetfinsihdate" + targetfinsihdate);
        logger.info("closebyname" + closebyname);
        if ((state.equals("Active")) && (status.equals("Closed")) && (closebyname != null) && (closebyname.equals("system")))
        {
          Calendar closed = Calendar.getInstance();
          closed.setTime(closedate);
          closed.set(closed.get(1), closed.get(2), closed.get(5), 0, 0, 0);
          
          Calendar target = Calendar.getInstance();
          target.setTime(targetfinsihdate);
          target.set(target.get(1), target.get(2), target.get(5), 0, 0, 0);
          
          logger.info("inside loop");
          if (closed.compareTo(target) > 0)
          {
            totalmissedtarget++;
            logger.info("totalmissedtarget");
          }
          else
          {
            totalomntarget++;
            logger.info("totalomntarget");
          }
        }
      }
      KeyValue keyv1 = new KeyValue();
      keyv1.setKey("Requisitions Finished On Target");
      keyv1.setValue(String.valueOf(totalomntarget));
      statewithcountlist.add(keyv1);
      

      KeyValue keyv2 = new KeyValue();
      keyv2.setKey("Requisitions Missed Target Date");
      keyv2.setValue(String.valueOf(totalmissedtarget));
      statewithcountlist.add(keyv2);
    }
    catch (Exception e)
    {
      logger.error("Exception on getRequistionsStateAndCountByRecruiterAndYear()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return statewithcountlist;
  }
  
  public List getRequistionsSummary(long requistionId)
  {
    logger.info("Inside getRequistionsSummary method");
    long total = 0L;
    List statewithcountlist = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      JobRequisition jb = (JobRequisition)session.createCriteria(JobRequisition.class).add(Restrictions.eq("jobreqId", new Long(requistionId))).uniqueResult();
      
      String hql = "select a.interviewState from JobApplicant a where a.reqId=:reqId";
      Query query = session.createQuery(hql);
      query.setLong("reqId", requistionId);
      

      List intstateList = query.list();
      
      KeyValue keyv = new KeyValue();
      keyv.setKey("No Of Positions Open");
      keyv.setValue(String.valueOf(jb.getNumberOfOpening()));
      statewithcountlist.add(keyv);
      
      KeyValue keyv1 = new KeyValue();
      keyv1.setKey("No Of Positions Remaining");
      keyv1.setValue(String.valueOf(jb.getNumberOfOpeningRemain()));
      statewithcountlist.add(keyv1);
      

      int totaloffered = 0;
      int totalonboard = 0;
      for (int i = 0; i < intstateList.size(); i++)
      {
        String interviewstate = (String)intstateList.get(i);
        if ((interviewstate != null) && ((interviewstate.equals("On Board - Joined")) || (interviewstate.equals("Offer Released")) || (interviewstate.equals("Offer Accepted")) || (interviewstate.equals("Offer Declined")) || (interviewstate.equals("Offer Canceled")) || (interviewstate.equals("Offer In Negotiation")))) {
          totaloffered++;
        }
        if ((interviewstate != null) && (interviewstate.equals("On Board - Joined"))) {
          totalonboard++;
        }
      }
      KeyValue keyv2 = new KeyValue();
      keyv2.setKey("Application Submitted");
      keyv2.setValue(String.valueOf(intstateList.size()));
      statewithcountlist.add(keyv2);
      

      KeyValue keyv3 = new KeyValue();
      keyv3.setKey("Offer Released");
      keyv3.setValue(String.valueOf(totaloffered));
      statewithcountlist.add(keyv3);
      
      KeyValue keyv4 = new KeyValue();
      keyv4.setKey("On Board - Joined");
      keyv4.setValue(String.valueOf(totalonboard));
      statewithcountlist.add(keyv4);
    }
    catch (Exception e)
    {
      logger.error("Exception on getRequistionsSummary()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return statewithcountlist;
  }
  
  public List getApplicantsStateAndCountByScreenerAndYear(long reviewerId, int year)
  {
    logger.info("Inside getApplicantsStateAndCountByScreenerAndYear method");
    long total = 0L;
    List statewithcountlist = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      











      String hql = "select a.interviewState from JobApplicationEvent a join a.owner ow where ow.userId = :reviewerId and year(a.createdDate) = :year and a.eventType = 0";
      Query query = session.createQuery(hql);
      query.setLong("reviewerId", reviewerId);
      query.setInteger("year", year);
      
      List intstateList = query.list();
      KeyValue keyv = new KeyValue();
      keyv.setKey("Total Applicants Screened");
      keyv.setValue(String.valueOf(intstateList.size()));
      statewithcountlist.add(keyv);
      int clearedcount = 0;
      int failedcount = 0;
      for (int i = 0; i < intstateList.size(); i++)
      {
        String interviewstate = (String)intstateList.get(i);
        if ((interviewstate != null) && (interviewstate.indexOf("Cleared") != -1)) {
          clearedcount++;
        }
        if ((interviewstate != null) && (interviewstate.indexOf("Rejected") != -1)) {
          failedcount++;
        }
      }
      KeyValue keyv1 = new KeyValue();
      keyv1.setKey("Total Applicants Cleared On Screening");
      keyv1.setValue(String.valueOf(clearedcount));
      statewithcountlist.add(keyv1);
      
      KeyValue keyv2 = new KeyValue();
      keyv2.setKey("Total Applicants Failed On Screening");
      keyv2.setValue(String.valueOf(failedcount));
      statewithcountlist.add(keyv2);
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantsStateAndCountByScreenerAndYear()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return statewithcountlist;
  }
  
  public List getPerformanceByReviewerAndYear(long reviewerId, int year, String type)
  {
    logger.info("Inside getPerformanceByReviewerAndYear method");
    long total = 0L;
    List statewithcountlist = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      Criteria outer = session.createCriteria(ReviewerPerformance.class);
      outer.add(Restrictions.eq("reviewerId", Long.valueOf(reviewerId))).add(Restrictions.eq("yearValue", Integer.valueOf(year))).add(Restrictions.eq("reviewerType", type));
      
      ReviewerPerformance revPrf = (ReviewerPerformance)outer.uniqueResult();
      
      KeyValue keyv = new KeyValue();
      keyv.setKey("Total Applicants Reviewed");
      keyv.setValue(String.valueOf(revPrf.getTotalApplicants()));
      statewithcountlist.add(keyv);
      

      KeyValue keyv1 = new KeyValue();
      keyv1.setKey("Total Applicants Cleared On Review");
      keyv1.setValue(String.valueOf(revPrf.getTotalCleared()));
      statewithcountlist.add(keyv1);
      
      KeyValue keyv2 = new KeyValue();
      keyv2.setKey("Total Applicants Cleared Next Review");
      keyv2.setValue(String.valueOf(revPrf.getTotalClearedNextRound()));
      statewithcountlist.add(keyv2);
      
      KeyValue keyv3 = new KeyValue();
      keyv3.setKey("Total Applicants Offered");
      keyv3.setValue(String.valueOf(revPrf.getTotalOffered()));
      statewithcountlist.add(keyv3);
      
      KeyValue keyv4 = new KeyValue();
      keyv4.setKey("Total Applicants On Boarded");
      keyv4.setValue(String.valueOf(revPrf.getTotalOnBoard()));
      statewithcountlist.add(keyv4);
    }
    catch (Exception e)
    {
      logger.error("Exception on getPerformanceByReviewerAndYear()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return statewithcountlist;
  }
  
  public List getPerformanceDataByReviewerIdAndType(long reviewerId, String type)
  {
    logger.info("Inside getPerformanceDataByReviewerIdAndType method");
    long total = 0L;
    List plist = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      Criteria outer = session.createCriteria(ReviewerPerformance.class);
      outer.add(Restrictions.eq("reviewerId", Long.valueOf(reviewerId))).add(Restrictions.eq("reviewerType", type));
      
      plist = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getPerformanceDataByReviewerIdAndType()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return plist;
  }
  
  public ReviewerPerformance calculatePerformanceByReviewerAndYear(long reviewerId, int year)
  {
    logger.info("Inside calculatePerformanceByReviewerAndYear method");
    long total = 0L;
    List alist = new ArrayList();
    ReviewerPerformance reviewerperformance = new ReviewerPerformance();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String sql = "SELECT application_id , interview_state, jb_app_ev_id FROM job_application_events where year(created_Date) = ? and owner = ? and event_type !=0";
      
      Query query = session.createSQLQuery(sql);
      query.setInteger(0, year);
      query.setLong(1, reviewerId);
      
      List eventList = query.list();
      
      reviewerperformance.setReviewerId(reviewerId);
      reviewerperformance.setYearValue(year);
      reviewerperformance.setTotalApplicants(eventList.size());
      int clearedcount = 0;
      int totalclearednextround = 0;
      int totaloffered = 0;
      int totalonboard = 0;
      String offeredapplicantIds = "";
      String onboardapplicantIds = "";
      for (int i = 0; i < alist.size(); i++)
      {
        Object[] obj = (Object[])alist.get(i);
        BigInteger appId = (BigInteger)obj[0];
        long applicationId = appId.longValue();
        String interviewstate = (String)obj[1];
        if ((interviewstate != null) && (interviewstate.indexOf("Cleared") != -1)) {
          clearedcount++;
        }
        BigInteger evId = (BigInteger)obj[2];
        long eventId = evId.longValue();
        


        Criteria outer = session.createCriteria(JobApplicationEvent.class).createAlias("applicant", "applicant");
        outer.add(Restrictions.eq("applicant.applicantId", Long.valueOf(applicationId)));
        outer.add(Restrictions.ge("jobAppEventId", Long.valueOf(eventId)));
        outer.addOrder(Order.asc("jobAppEventId"));
        
        List evList = new ArrayList();
        evList = outer.list();
        if ((evList != null) && (evList.size() > 0))
        {
          JobApplicationEvent ev = (JobApplicationEvent)evList.get(0);
          if ((ev.getInterviewState() != null) && (ev.getInterviewState().indexOf("Cleared") != -1)) {
            totalclearednextround++;
          }
        }
        else
        {
          totalclearednextround++;
        }
        JobApplicant applicant = (JobApplicant)session.createCriteria(JobApplicant.class).add(Restrictions.eq("applicantId", Long.valueOf(applicationId))).uniqueResult();
        
        interviewstate = applicant.getInterviewState();
        if ((interviewstate != null) && ((interviewstate.equals("On Board - Joined")) || (interviewstate.equals("Offer Released")) || (interviewstate.equals("Offer Accepted")) || (interviewstate.equals("Offer Declined")) || (interviewstate.equals("Offer Canceled")) || (interviewstate.equals("Offer In Negotiation"))))
        {
          totaloffered++;
          offeredapplicantIds = offeredapplicantIds + applicant.getApplicantId() + ",";
        }
        if ((interviewstate != null) && (interviewstate.equals("On Board - Joined")))
        {
          totalonboard++;
          onboardapplicantIds = onboardapplicantIds + applicant.getApplicantId() + ",";
        }
      }
      reviewerperformance.setTotalCleared(clearedcount);
      reviewerperformance.setTotalClearedNextRound(totalclearednextround);
      reviewerperformance.setTotalOffered(totaloffered);
      reviewerperformance.setTotalOnBoard(totalonboard);
      reviewerperformance.setReviewerType("REVIEW");
      reviewerperformance.setCreatedDate(new Date());
      reviewerperformance.setApplicantIdsOffered(offeredapplicantIds);
      reviewerperformance.setApplicantIdsOnBorad(onboardapplicantIds);
      
      Calendar cal = Calendar.getInstance();
      int currentYear = cal.get(1);
      if (currentYear == year)
      {
        String hql = "delete from ReviewerPerformance where reviewerId = :reviewerId and yearValue = :year and reviewerType = :type";
        Query queryn = session.createQuery(hql);
        queryn.setLong("reviewerId", reviewerId);
        queryn.setInteger("year", year);
        queryn.setString("type", "REVIEW");
        queryn.executeUpdate();
      }
      session.save(reviewerperformance);
    }
    catch (Exception e)
    {
      logger.error("Exception on calculatePerformanceByReviewerAndYear()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return reviewerperformance;
  }
  
  public ReviewerPerformance calculatePerformanceByScreenerIdAndYear(long reviewerId, int year)
  {
    logger.info("Inside calculatePerformanceByScreenerIdAndYear method");
    long total = 0L;
    List alist = new ArrayList();
    ReviewerPerformance reviewerperformance = new ReviewerPerformance();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String sql = "SELECT application_id , interview_state, jb_app_ev_id FROM job_application_events where year(created_Date) = ? and owner = ? and event_type =0";
      
      Query query = session.createSQLQuery(sql);
      query.setInteger(0, year);
      query.setLong(1, reviewerId);
      
      List eventList = query.list();
      
      reviewerperformance.setReviewerId(reviewerId);
      reviewerperformance.setYearValue(year);
      reviewerperformance.setTotalApplicants(eventList.size());
      int clearedcount = 0;
      int totalclearednextround = 0;
      int totaloffered = 0;
      int totalonboard = 0;
      String offeredapplicantIds = "";
      String onboardapplicantIds = "";
      for (int i = 0; i < alist.size(); i++)
      {
        Object[] obj = (Object[])alist.get(i);
        BigInteger appId = (BigInteger)obj[0];
        long applicationId = appId.longValue();
        String interviewstate = (String)obj[1];
        if ((interviewstate != null) && (interviewstate.indexOf("Cleared") != -1)) {
          clearedcount++;
        }
        BigInteger evId = (BigInteger)obj[2];
        long eventId = evId.longValue();
        


        Criteria outer = session.createCriteria(JobApplicationEvent.class).createAlias("applicant", "applicant");
        outer.add(Restrictions.eq("applicant.applicantId", Long.valueOf(applicationId)));
        outer.add(Restrictions.ge("jobAppEventId", Long.valueOf(eventId)));
        outer.addOrder(Order.asc("jobAppEventId"));
        
        List evList = new ArrayList();
        evList = outer.list();
        if ((evList != null) && (evList.size() > 0))
        {
          JobApplicationEvent ev = (JobApplicationEvent)evList.get(0);
          if ((ev.getInterviewState() != null) && (ev.getInterviewState().indexOf("Cleared") != -1)) {
            totalclearednextround++;
          }
        }
        else
        {
          totalclearednextround++;
        }
        JobApplicant applicant = (JobApplicant)session.createCriteria(JobApplicant.class).add(Restrictions.eq("applicantId", Long.valueOf(applicationId))).uniqueResult();
        
        interviewstate = applicant.getInterviewState();
        if ((interviewstate != null) && ((interviewstate.equals("On Board - Joined")) || (interviewstate.equals("Offer Released")) || (interviewstate.equals("Offer Accepted")) || (interviewstate.equals("Offer Declined")) || (interviewstate.equals("Offer Canceled")) || (interviewstate.equals("Offer In Negotiation"))))
        {
          totaloffered++;
          offeredapplicantIds = offeredapplicantIds + applicant.getApplicantId() + ",";
        }
        if ((interviewstate != null) && (interviewstate.equals("On Board - Joined")))
        {
          totalonboard++;
          onboardapplicantIds = onboardapplicantIds + applicant.getApplicantId() + ",";
        }
      }
      reviewerperformance.setTotalCleared(clearedcount);
      reviewerperformance.setTotalClearedNextRound(totalclearednextround);
      reviewerperformance.setTotalOffered(totaloffered);
      reviewerperformance.setTotalOnBoard(totalonboard);
      reviewerperformance.setReviewerType("SCREENING");
      reviewerperformance.setCreatedDate(new Date());
      reviewerperformance.setApplicantIdsOffered(offeredapplicantIds);
      reviewerperformance.setApplicantIdsOnBorad(onboardapplicantIds);
      
      Calendar cal = Calendar.getInstance();
      int currentYear = cal.get(1);
      if (currentYear == year)
      {
        String hql = "delete from ReviewerPerformance where reviewerId = :reviewerId and yearValue = :year and reviewerType = :type";
        Query queryn = session.createQuery(hql);
        queryn.setLong("reviewerId", reviewerId);
        queryn.setInteger("year", year);
        queryn.setString("type", "SCREENING");
        queryn.executeUpdate();
      }
      session.save(reviewerperformance);
    }
    catch (Exception e)
    {
      logger.error("Exception on calculatePerformanceByScreenerIdAndYear()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return reviewerperformance;
  }
}
