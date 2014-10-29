package com.dao;

import com.bean.ApplicantActivity;
import com.bean.ApplicantAttachments;
import com.bean.ApplicantOfferAttachment;
import com.bean.ApplicantOtherBenefits;
import com.bean.ApplicantOtherDetails;
import com.bean.ApplicantRating;
import com.bean.ApplicantUser;
import com.bean.ApplicantUserActions;
import com.bean.InterviewWatchList;
import com.bean.JobApplicant;
import com.bean.JobApplicationEvent;
import com.bean.OfferApprover;
import com.bean.OfferWatchList;
import com.bean.ReferralAgencyApplicants;
import com.bean.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class ApplicantTXDAO
  extends HibernateDaoSupport
{
  protected static final Logger logger = Logger.getLogger(ApplicantTXDAO.class);
  
  public void saveApplicantActivity(ApplicantActivity applicantactivity)
  {
    logger.info("Inside saveApplicantActivity method");
    getHibernateTemplate().save(applicantactivity);
  }
  
  public void saveApplicant(JobApplicant applicant)
  {
    logger.info("Inside saveApplicant method");
    getHibernateTemplate().save(applicant);
  }
  
  public void saveApplicantAttachment(ApplicantAttachments attach)
  {
    logger.info("Inside saveApplicantAttachment method");
    getHibernateTemplate().save(attach);
  }
  
  public void saveApplicantOtherDetails(ApplicantOtherDetails otherdetails)
  {
    logger.info("Inside saveApplicantOtherDetails method");
    getHibernateTemplate().save(otherdetails);
  }
  
  public void saveReferralAgenecyApplicant(ReferralAgencyApplicants applicant)
  {
    logger.info("Inside saveReferralAgenecyApplicant method");
    getHibernateTemplate().save(applicant);
  }
  
  public void saveInterviewSchedule(JobApplicationEvent event)
  {
    logger.info("Inside saveInterviewSchedule method");
    getHibernateTemplate().save(event);
  }
  
  public void updateApplicationEvent(JobApplicationEvent event)
  {
    logger.info("Inside updateApplicationEvent method");
    getHibernateTemplate().update(event);
  }
  
  public void updateOfferWaltcher(OfferWatchList app)
  {
    logger.info("Inside updateOfferWaltcher method");
    getHibernateTemplate().update(app);
  }
  
  public void updateOfferApprover(OfferApprover offerapprover)
  {
    logger.info("Inside updateOfferApprover method");
    getHibernateTemplate().update(offerapprover);
  }
  
  public void updateApplicant(JobApplicant applicant)
  {
    logger.info("Inside updateApplicant method");
    
    getHibernateTemplate().update(applicant);
    logger.info("final updateApplicant method");
  }
  
  public void saveApplicantUser(ApplicantUser appuser)
  {
    logger.info("Inside saveApplicantUser method");
    getHibernateTemplate().save(appuser);
  }
  
  public void addapplicantaction(ApplicantUserActions action)
  {
    logger.info("Inside addapplicantaction method");
    getHibernateTemplate().save(action);
  }
  
  public void updateapplicantaction(ApplicantUserActions action)
  {
    logger.info("Inside updateapplicantaction method");
    getHibernateTemplate().update(action);
  }
  
  public void deleteapplicantaction(ApplicantUserActions action)
  {
    logger.info("Inside deleteapplicantaction method");
    getHibernateTemplate().delete(action);
  }
  
  public void updateApplicantUser(ApplicantUser appuser)
  {
    logger.info("Inside updateApplicantUser method");
    getHibernateTemplate().update(appuser);
  }
  
  public void mergeandupdateApplicant(JobApplicant applicant)
  {
    logger.info("Inside mergeandupdateApplicant method");
    getHibernateTemplate().merge(applicant);
    getHibernateTemplate().update(applicant);
  }
  
  public void saveOfferAttachment(ApplicantOfferAttachment attachment)
  {
    logger.info("Inside saveOfferAttachment method");
    getHibernateTemplate().save(attachment);
  }
  
  public void deleteApplicationEvent(JobApplicationEvent event)
  {
    logger.info("Inside deleteApplicationEvent method");
    getHibernateTemplate().delete(event);
  }
  
  public void addInterviewWatchList(List watchList, long eventId, long applicantId)
  {
    logger.info("Inside addInterviewWatchList method");
    if (watchList != null) {
      for (int i = 0; i < watchList.size(); i++)
      {
        InterviewWatchList app = (InterviewWatchList)watchList.get(i);
        app.setApplicationeventId(eventId);
        app.setApplicantId(applicantId);
        getHibernateTemplate().save(app);
      }
    }
  }
  
  public void addOfferWatchList(List watchList)
  {
    logger.info("Inside addOfferWatchList method");
    if (watchList != null) {
      for (int i = 0; i < watchList.size(); i++)
      {
        OfferWatchList app = (OfferWatchList)watchList.get(i);
        getHibernateTemplate().save(app);
      }
    }
  }
  
  public void addApplicantRatingList(List appRatingList)
  {
    logger.info("Inside addApplicantRatingList method");
    if (appRatingList != null) {
      for (int i = 0; i < appRatingList.size(); i++)
      {
        ApplicantRating app = (ApplicantRating)appRatingList.get(i);
        getHibernateTemplate().save(app);
      }
    }
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
  
  public void deleteApplicantRatingList(long applicantId, long appeventid)
  {
    logger.info("Inside deleteApplicantRatingList method");
    String hql = "delete from ApplicantRating where applicantId = :applicantId  and applicantEventId = :applicantEventId ";
    Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
    query.setLong("applicantId", applicantId);
    query.setLong("applicantEventId", appeventid);
    int rowCount = query.executeUpdate();
  }
  
  public void deleteApplicantAttachment(long applicantId, String appuuid, String type)
  {
    logger.info("Inside deleteApplicantAttachment method");
    String hql = "delete from ApplicantAttachments where applicantId = :applicantId  and appuuid = :appuuid and type = :type";
    Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
    query.setLong("applicantId", applicantId);
    query.setString("appuuid", appuuid);
    query.setString("type", type);
    int rowCount = query.executeUpdate();
  }
  
  public void deleteApplicantOtherDetails(long applicantId)
  {
    logger.info("Inside deleteApplicantOtherDetails method");
    String hql = "delete from ApplicantOtherDetails where applicantId = :applicantId";
    Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
    query.setLong("applicantId", applicantId);
    
    int rowCount = query.executeUpdate();
  }
  
  public void deleteOfferWatchList(long applicantId)
  {
    logger.info("Inside deleteOfferWatchList method");
    String hql = "delete from OfferWatchList where applicantId = :applicantId";
    Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
    query.setLong("applicantId", applicantId);
    int rowCount = query.executeUpdate();
  }
  
  public void deleteInterviewWatchList(long applicantId, long applicationeventId)
  {
    logger.info("Inside deleteInterviewWatchList method");
    String hql = "delete from InterviewWatchList where applicantId = :applicantId and applicationeventId = :applicationeventId";
    Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
    query.setLong("applicantId", applicantId);
    query.setLong("applicationeventId", applicationeventId);
    int rowCount = query.executeUpdate();
  }
  
  public void deleteInterviewWatchList(long applicantId)
  {
    logger.info("Inside deleteInterviewWatchList method");
    String hql = "delete from InterviewWatchList where applicantId = :applicantId ";
    Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
    query.setLong("applicantId", applicantId);
    int rowCount = query.executeUpdate();
  }
  
  public void deleteApplicantReferencee(long applicantId)
  {
    logger.info("Inside deleteApplicantReferencee method");
    String hql = "delete from ApplicantReferencee where applicantId = :applicantId ";
    Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
    query.setLong("applicantId", applicantId);
    int rowCount = query.executeUpdate();
  }
  
  public void deleteQuestionAnswers(long applicantId)
  {
    logger.info("Inside deleteQuestionAnswers method");
    String hql = "delete from QuestionAnswers where applicantId = :applicantId ";
    Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
    query.setLong("applicantId", applicantId);
    int rowCount = query.executeUpdate();
  }
  
  public void deleteApplicantRating(long applicantId)
  {
    logger.info("Inside deleteApplicantRating method");
    String hql = "delete from ApplicantRating where applicantId = :applicantId ";
    Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
    query.setLong("applicantId", applicantId);
    int rowCount = query.executeUpdate();
  }
  
  public void deleteEducationDetails(long applicantId)
  {
    logger.info("Inside deleteEducationDetails method");
    String hql = "delete from EducationDetails where applicantId = :applicantId ";
    Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
    query.setLong("applicantId", applicantId);
    int rowCount = query.executeUpdate();
  }
  
  public void deletePreviousOrgDetails(long applicantId)
  {
    logger.info("Inside deletePreviousOrgDetails method");
    String hql = "delete from PreviousOrgDetails where applicantId = :applicantId ";
    Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
    query.setLong("applicantId", applicantId);
    int rowCount = query.executeUpdate();
  }
  
  public void deleteApplicantSkills(long applicantId)
  {
    logger.info("Inside deleteApplicantSkills method");
    String hql = "delete from ApplicantSkills where applicantId = :applicantId ";
    Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
    query.setLong("applicantId", applicantId);
    int rowCount = query.executeUpdate();
  }
  
  public void deleteReferralAgencyApplicants(long applicantId)
  {
    logger.info("Inside deleteReferralAgencyApplicants method");
    String hql = "delete from ReferralAgencyApplicants where applicantId = :applicantId ";
    Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
    query.setLong("applicantId", applicantId);
    int rowCount = query.executeUpdate();
  }
  
  public void deleteOnboarding(long applicantId)
  {
    logger.info("Inside deleteOnboarding method");
    String hql = "delete from Onboarding where applicantId = :applicantId ";
    Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
    query.setLong("applicantId", applicantId);
    int rowCount = query.executeUpdate();
  }
  
  public void deleteOnBoardingTask(long applicantId)
  {
    logger.info("Inside deleteOnBoardingTask method");
    String hql = "delete from OnBoardingTask where applicantId = :applicantId ";
    Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
    query.setLong("applicantId", applicantId);
    int rowCount = query.executeUpdate();
  }
  
  public void deleteApplicantUserActions(long applicantId)
  {
    logger.info("Inside deleteApplicantUserActions method");
    String hql = "delete from ApplicantUserActions where applicantId = :applicantId ";
    Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
    query.setLong("applicantId", applicantId);
    int rowCount = query.executeUpdate();
  }
  
  public void deleteActionsAttachment(long applicantId)
  {
    logger.info("Inside deleteActionsAttachment method");
    String hql = "delete from ActionsAttachment where applicantId = :applicantId ";
    Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
    query.setLong("applicantId", applicantId);
    int rowCount = query.executeUpdate();
  }
  
  public void deleteJobApplicationEvent(long applicantId)
  {
    logger.info("Inside deleteActionsAttachment method");
    String hql = "delete from JobApplicationEvent where applicant.applicantId = :applicantId ";
    Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
    query.setLong("applicantId", applicantId);
    int rowCount = query.executeUpdate();
  }
  
  public void deleteApplicantActivity(long applicantId)
  {
    logger.info("Inside deleteApplicantActivity method");
    String hql = "delete from ApplicantActivity where applicantId = :applicantId ";
    Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
    query.setLong("applicantId", applicantId);
    int rowCount = query.executeUpdate();
  }
  
  public void deleteApplicantAttachments(long applicantId)
  {
    logger.info("Inside deleteApplicantAttachments method");
    String hql = "delete from ApplicantAttachments where applicantId = :applicantId ";
    Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
    query.setLong("applicantId", applicantId);
    int rowCount = query.executeUpdate();
  }
  
  public void deleteApplicantComment(long applicantId)
  {
    logger.info("Inside deleteApplicantComment method");
    String hql = "delete from ApplicantComment where applicantId = :applicantId ";
    Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
    query.setLong("applicantId", applicantId);
    int rowCount = query.executeUpdate();
  }
  
  public void deleteApplicantUser(long applicantId)
  {
    logger.info("Inside deleteApplicantUser method");
    String hql = "delete from ApplicantUser where applicant.applicantId = :applicantId ";
    Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
    query.setLong("applicantId", applicantId);
    int rowCount = query.executeUpdate();
  }
  
  public void deleteOfferApprovers(long applicantId)
  {
    logger.info("Inside deleteOfferApprovers method");
    String hql = "delete from OfferApprover where applicantId = :applicantId";
    Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
    query.setLong("applicantId", applicantId);
    int rowCount = query.executeUpdate();
  }
  
  public void deleteOtherBenefits(long applicantId)
  {
    logger.info("Inside deleteOtherBenefits method");
    String hql = "delete from ApplicantOtherBenefits where applicantId = :applicantId";
    Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
    query.setLong("applicantId", applicantId);
    int rowCount = query.executeUpdate();
  }
  
  public void deleteOfferAttachments(long applicantId)
  {
    logger.info("Inside deleteOfferAttachments method");
    String hql = "delete from ApplicantOfferAttachment where applicantId = :applicantId";
    Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
    query.setLong("applicantId", applicantId);
    int rowCount = query.executeUpdate();
  }
  
  public void deleteApplicant(long applicantId)
  {
    logger.info("Inside deleteApplicant method");
    String hql = "delete from JobApplicant where applicantId = :applicantId ";
    Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
    query.setLong("applicantId", applicantId);
    int rowCount = query.executeUpdate();
  }
  
  public void saveOfferApproversList(List approverslist)
  {
    logger.info("Inside saveOfferApproversList method");
    if (approverslist != null) {
      for (int i = 0; i < approverslist.size(); i++)
      {
        OfferApprover app = (OfferApprover)approverslist.get(i);
        getHibernateTemplate().save(app);
      }
    }
  }
  
  public void saveOtherBenefits(List otherbenlist)
  {
    logger.info("Inside saveOtherBenefits method");
    if (otherbenlist != null) {
      for (int i = 0; i < otherbenlist.size(); i++)
      {
        ApplicantOtherBenefits app = (ApplicantOtherBenefits)otherbenlist.get(i);
        getHibernateTemplate().save(app);
      }
    }
  }
  
  public OfferApprover getNextOfferApprover(long id, int level)
  {
    logger.info("Inside getNextOfferApprover method");
    OfferApprover approver = null;
    List aaproverList = new ArrayList();
    
    aaproverList = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(OfferApprover.class).add(Restrictions.eq("applicantId", new Long(id))).add(Restrictions.eq("isapprover", "Y")).add(Restrictions.gt("levelorder", Integer.valueOf(level))).addOrder(Order.asc("levelorder")).list();
    


    int i = 0;
    if (i < aaproverList.size()) {
      approver = (OfferApprover)aaproverList.get(i);
    }
    return approver;
  }
  
  public JobApplicationEvent getJobApplicationEvent(long applicantId, int eventType)
  {
    logger.info("Inside getJobApplicationEvent method");
    JobApplicationEvent event = null;
    logger.info("eventType" + eventType);
    logger.info("applicantId" + applicantId);
    
    event = (JobApplicationEvent)getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(JobApplicationEvent.class).createAlias("applicant", "applicant").add(Restrictions.eq("applicant.applicantId", new Long(applicantId))).add(Restrictions.eq("eventType", new Integer(eventType))).uniqueResult();
    


    return event;
  }
  
  public JobApplicant getJobApplicant(long applicantId)
  {
    logger.info("Inside getJobApplicant method");
    JobApplicant applicant = null;
    
    logger.info("applicantId" + applicantId);
    
    applicant = (JobApplicant)getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(JobApplicant.class).add(Restrictions.eq("applicantId", Long.valueOf(applicantId))).uniqueResult();
    


    return applicant;
  }
  
  public JobApplicant getJobApplicantByUuid(String uuid)
  {
    logger.info("Inside getJobApplicantByUuid method");
    JobApplicant applicant = null;
    
    applicant = (JobApplicant)getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(JobApplicant.class).add(Restrictions.eq("uuid", uuid)).uniqueResult();
    


    return applicant;
  }
  
  public void updateInterviewComments(JobApplicationEvent event, JobApplicant applicant)
  {
    logger.info("Inside updateInterviewComments method");
    User user = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String sql = "update job_application_events a set status = ? , interviewerComments = ?  , updated_by = ? , updated_date= ?, updated_by_name = ? , evtmplfileName = ? , evtmplfile = ?  , evTmplfeedback = ? where application_id= ? and event_type=? ";
      if ((event.getEvtmplFileName() != null) && (event.getEvtmplFileName() != null) && (event.getEvtmplFileName().length() > 0)) {
        sql = "update job_application_events a set status = ? , interviewerComments = ?  , updated_by = ? , updated_date= ?, updated_by_name = ? ,interview_state = ?, evtmplfileName = ? , evtmplfile = ? , evTmplfeedback = ?  where application_id= ? and event_type=? ";
      } else {
        sql = "update job_application_events a set status = ? , interviewerComments = ?  , updated_by = ? , updated_date= ?, updated_by_name = ? , interview_state = ? , evTmplfeedback = ? where application_id= ? and event_type=? ";
      }
      logger.info(sql);
      logger.info(event.getInterviewerComments());
      Connection con = session.connection();
      PreparedStatement pstmt = con.prepareStatement(sql);
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
        pstmt.setString(9, event.getEvTmplfeedback());
        pstmt.setLong(10, new Long(event.getApplicant().getApplicantId()).longValue());
        pstmt.setInt(11, new Integer(event.getEventType()).intValue());
      }
      else
      {
        pstmt.setString(7, event.getEvTmplfeedback());
        pstmt.setLong(8, new Long(event.getApplicant().getApplicantId()).longValue());
        pstmt.setInt(9, new Integer(event.getEventType()).intValue());
      }
      pstmt.execute();
      

      session.update(applicant);
      
      pstmt.close();con.commit();con.close();
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
  
  public List getOfferwatchListByApplicantId(long applicantId, String type)
  {
    logger.info("Inside getOfferwatchListByApplicantId method");
    List offerwatch = new ArrayList();
    try
    {
      offerwatch = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(OfferWatchList.class).add(Restrictions.eq("applicantId", Long.valueOf(applicantId))).add(Restrictions.eq("functionType", type)).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getOfferwatchListByApplicantId()", e);
    }
    return offerwatch;
  }
  
  public List getOfferAttachmentList(long applicantId, String type)
  {
    logger.info("Inside getOfferAttachmentList method");
    
    List offerAttachmentList = new ArrayList();
    try
    {
      Criteria outer = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(ApplicantOfferAttachment.class).add(Restrictions.eq("applicantId", Long.valueOf(applicantId))).add(Restrictions.eq("type", type));
      
      offerAttachmentList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getOfferAttachmentList()", e);
    }
    return offerAttachmentList;
  }
  
  public ApplicantUser getApplicantUser(long applicantId)
  {
    logger.info("Inside getApplicantUser method");
    
    ApplicantUser applicantuser = null;
    applicantuser = (ApplicantUser)getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(ApplicantUser.class).add(Restrictions.eq("status", "A")).createAlias("applicant", "applicant").add(Restrictions.eq("applicant.applicantId", Long.valueOf(applicantId))).uniqueResult();
    


    return applicantuser;
  }
  
  public String isEmailIdExist(String emailId)
  {
    logger.info("Inside isEmailIdExist method");
    String email = null;
    String hql = "select emailId from ApplicantUser a where a.emailId = :emailId and a.status != :status";
    Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
    query.setString("emailId", emailId);
    query.setString("status", "D");
    
    Object ob = query.uniqueResult();
    if (ob != null) {
      email = (String)ob;
    }
    return email;
  }
}
