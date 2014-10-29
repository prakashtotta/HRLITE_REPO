package com.dao;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bean.ApplicantOfferAttachment;
import com.bean.JobRequisionTemplate;
import com.bean.JobRequisition;
import com.bean.JobTemplateAccomplishment;
import com.bean.JobTemplateApprovers;
import com.bean.JobTemplateCompetency;
import com.bean.Organization;
import com.bean.ProjectCodes;
import com.bean.PublishJobBoards;
import com.bean.RequisitionActivity;
import com.bean.RequisitionComments;
import com.bean.RequistionAttachments;
import com.bean.RequistionExamQnsAssign;
import com.bean.User;
import com.bean.criteria.RequisitionSearchCriteriaMultiple;
import com.bean.criteria.RequisitionTemplateSearchCriteriaMultiple;
import com.bean.criteria.SearchCustomFields;
import com.bean.dto.Recruiter;
import com.bean.system.PublishToVendor;
import com.common.Common;
import com.lucene.IndexSearch;
import com.resources.Constant;
import com.util.DateUtil;
import com.util.StringUtils;

public class JobRequistionDAO
  extends HibernateDaoSupport
{
  protected static final Logger logger = Logger.getLogger(JobRequistionDAO.class);
  
  public List getRequisitionsActiveList()
  {
    logger.info("Inside getRequisitionsActiveList method");
    List charList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria crit = session.createCriteria(JobRequisition.class).add(Restrictions.eq("state", "Active"));
      

      charList = crit.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getRequisitionsActiveList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return charList;
  }
  
  public List getRequisitionsByRefferalPortal(long superuserkey)
  {
    logger.info("Inside getRequisitionsActiveList method");
    List charList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria crit = session.createCriteria(JobRequisition.class).add(Restrictions.eq("state", "Active")).add(Restrictions.eq("super_user_key", Long.valueOf(superuserkey))).add(Restrictions.eq("publishToExternal", "Y"));
      




      charList = crit.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getRequisitionsActiveList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return charList;
  }
  
  public long getRequisitionIdByUuid(String uuid)
    throws Exception
  {
    logger.info("Inside getRequisitionIdByUuid method");
    long reqId = 0L;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      String sql = "select jb_req_id from job_requisition  where uuid = :uuid";
      Query query = session.createSQLQuery(sql);
      query.setString("uuid", uuid);
      Object obj = query.uniqueResult();
      
      BigInteger reqid = (BigInteger)obj;
      reqId = reqid.longValue();
    }
    catch (Exception e)
    {
      logger.error("Exception on getRequisitionIdByUuid()", e);
      throw e;
    }
    return reqId;
  }
  
  public RequistionExamQnsAssign getRequistionExamQnsAssignWithExamName(long jb_req_id)
  {
    logger.info("Inside getRequistionExamQnsAssignWithExamName method");
    RequistionExamQnsAssign rq = new RequistionExamQnsAssign();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      String sql = "select mockexam_set_id, (select name from mockquestionset where cat_id=r.mockexam_set_id) exam, pass_percentage,question_group_id ,(select questions_groups_name from questions_groups where questions_groups_id=r.question_group_id) questinnaire  from requisition_question_exam r  where jb_req_id = :jb_req_id ";
      

      Query query = session.createSQLQuery(sql);
      query.setLong("jb_req_id", jb_req_id);
      List lst = query.list();
      if ((lst != null) && (lst.size() > 0))
      {
        Object[] obj = (Object[])lst.get(0);
        Integer examid = (Integer)obj[0];
        rq.setExamId(examid.intValue());
        rq.setExamname((String)obj[1]);
        Double passp = (Double)obj[2];
        rq.setPassPercentage(passp.doubleValue());
        BigInteger qnsid = (BigInteger)obj[3];
        rq.setQnsgrpId(qnsid.longValue());
        rq.setQuestionairename((String)obj[4]);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getRequistionExamQnsAssignWithExamName()", e);
    }
    return rq;
  }
  
  public long getSuperUserKeyByReqId(long jb_req_id)
    throws Exception
  {
    logger.info("Inside getSuperUserKeyByReqId method");
    long superuserkey = 0L;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      String sql = "select super_user_key from job_requisition  where jb_req_id = :jb_req_id";
      Query query = session.createSQLQuery(sql);
      query.setLong("jb_req_id", jb_req_id);
      Object obj = query.uniqueResult();
      
      BigInteger reqid = (BigInteger)obj;
      superuserkey = reqid.longValue();
    }
    catch (Exception e)
    {
      logger.error("Exception on getSuperUserKeyByReqId()", e);
      throw e;
    }
    return superuserkey;
  }
  
  public List getJobTitlesByAgency(String querystring, long id)
  {
    logger.info("Inside getRequisitionsActiveList method");
    List charList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      String hqlquery = " select j from JobRequisition j , PublishToVendor v where j.jobreqId=v.jobreqId and v.userId=:id";
      if ((!StringUtils.isNullOrEmpty(querystring)) && (!querystring.equals("null"))) {
        hqlquery = hqlquery + " and j.jobTitle like :jobTitle";
      }
      Query query = session.createQuery(hqlquery);
      
      query.setLong("id", id);
      if ((!StringUtils.isNullOrEmpty(querystring)) && (!querystring.equals("null"))) {
        query.setString("jobTitle", '%' + querystring + '%');
      }
      charList = query.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getRequisitionsActiveList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return charList;
  }
  
  public List getRequisitionsByAgency(long id)
  {
    logger.info("Inside getRequisitionsActiveList method");
    List charList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      String hqlquery = " select j from JobRequisition j , PublishToVendor v where j.jobreqId=v.jobreqId and v.userId=:id";
      

      Query query = session.createQuery(hqlquery);
      
      query.setLong("id", id);
      charList = query.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getRequisitionsActiveList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return charList;
  }
  
  public List getRequisitionsTemplateActiveList(long superUserKey)
  {
    logger.info("Inside getRequisitionsTemplateActiveList method");
    List charList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria crit = session.createCriteria(JobRequisionTemplate.class).add(Restrictions.eq("super_user_key", Long.valueOf(superUserKey))).add(Restrictions.eq("status", "Active"));
      

      charList = crit.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getRequisitionsTemplateActiveList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return charList;
  }
  
  public List getRequisitionsActiveListByOrgDeptProjCode(long orgId, long departmentId, long projectCodeId)
  {
    logger.info("Inside getRequisitionsActiveListByOrgDeptProjCode method");
    List charList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = session.createCriteria(JobRequisition.class).add(Restrictions.eq("state", "Active"));
      if (orgId != 0L)
      {
        outer.createAlias("organization", "organization");
        outer.add(Restrictions.eq("organization.orgId", Long.valueOf(orgId)));
      }
      if (departmentId != 0L)
      {
        outer.createAlias("department", "department");
        outer.add(Restrictions.eq("department.departmentId", Long.valueOf(departmentId)));
      }
      if (projectCodeId != 0L)
      {
        outer.createAlias("projectcode", "projectcode");
        outer.add(Restrictions.eq("projectcode.projectId", Long.valueOf(projectCodeId)));
      }
      charList = outer.list();
      
      logger.info("charList.size()" + charList.size());
    }
    catch (Exception e)
    {
      logger.error("Exception on getRequisitionsActiveListByOrgDeptProjCode()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return charList;
  }
  
  public List getRequisitionsActiveListByOrgDeptProjCode(List<Long> orgidList, List<Long> deptidList, List<Long> projectcodeidList)
  {
    logger.info("Inside getRequisitionsActiveListByOrgDeptProjCode method");
    List charList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = session.createCriteria(JobRequisition.class).add(Restrictions.eq("state", "Active"));
      if (orgidList.size() > 0) {
        outer.add(Restrictions.in("organization.orgId", orgidList));
      }
      if (deptidList.size() > 0) {
        outer.add(Restrictions.in("department.departmentId", deptidList));
      }
      if (projectcodeidList.size() > 0) {
        outer.add(Restrictions.in("projectcode.projectId", projectcodeidList));
      }
      charList = outer.list();
      
      logger.info("charList.size()" + charList.size());
    }
    catch (Exception e)
    {
      logger.error("Exception on getRequisitionsActiveListByOrgDeptProjCode()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return charList;
  }
  
  public List getRequisitionsActiveAndOpenListForRecruiter(long userId)
  {
    logger.info("Inside getRequisitionsActiveAndOpenListForRecruiter method");
    List charList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria crit = session.createCriteria(JobRequisition.class).add(Restrictions.eq("state", "Active")).add(Restrictions.eq("status", "Open")).add(Restrictions.eq("recruiterId", Long.valueOf(userId)));
      


      charList = crit.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getRequisitionsActiveAndOpenListForRecruiter()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return charList;
  }
  
  public List getRequisitionsActiveAndOpenListForSuperUser(long super_user_key)
  {
    logger.info("Inside getRequisitionsActiveAndOpenListForSuperUser method");
    List charList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria crit = session.createCriteria(JobRequisition.class).add(Restrictions.eq("status", "Open")).add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key)));
      


      charList = crit.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getRequisitionsActiveAndOpenListForSuperUser()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return charList;
  }
  
  public List getJobTitlesActiveAndOpenListAssigedToUser(long userId)
  {
    logger.info("Inside getJobTitlesActiveAndOpenListAssigedToUser method");
    List charList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String hqlquery = " select distinct j from JobRequisition j , UserGroup ug where  ((j.hiringmgr.userId =:currentuserid) or (j.recruiterId = :currentuserid and j.isgrouprecruiter <> 'Y') or (j.recruiterId = ug.usergrpId and  j.isgrouprecruiter='Y' and ug.users.userId =:currentuserid)) ";
      

      hqlquery = hqlquery + " and j.state = 'Active' and j.status = 'Open'";
      
      Query query = session.createQuery(hqlquery);
      
      query.setLong("currentuserid", new Long(userId).longValue());
      charList = query.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getJobTitlesActiveAndOpenListAssigedToUser()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return charList;
  }
  
  public List getJobTitlesActiveListAssigedToUser(long userId)
  {
    logger.info("Inside getJobTitlesActiveAndOpenListAssigedToUser method");
    List charList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String hqlquery = " select distinct j from JobRequisition j , UserGroup ug where  ((j.hiringmgr.userId =:currentuserid) or (j.recruiterId = :currentuserid and j.isgrouprecruiter <> 'Y') or (j.recruiterId = ug.usergrpId and  j.isgrouprecruiter='Y' and ug.users.userId =:currentuserid)) ";
      

      hqlquery = hqlquery + " and j.state = 'Active'";
      
      Query query = session.createQuery(hqlquery);
      
      query.setLong("currentuserid", new Long(userId).longValue());
      charList = query.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getJobTitlesActiveAndOpenListAssigedToUser()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return charList;
  }
  
  public List getRequisitionsActiveAndOpenList(long super_user_key)
  {
    logger.info("Inside getRequisitionsActiveAndOpenList method");
    List charList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria crit = session.createCriteria(JobRequisition.class).add(Restrictions.eq("state", "Active")).add(Restrictions.eq("status", "Open")).add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key)));
      

      charList = crit.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getRequisitionsActiveAndOpenList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return charList;
  }
  
  public List getRequisitionsActiveList(long superUserKey)
  {
    logger.info("Inside getRequisitionsActiveAndOpenList method");
    List charList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria crit = session.createCriteria(JobRequisition.class).add(Restrictions.eq("state", "Active")).add(Restrictions.eq("super_user_key", Long.valueOf(superUserKey)));
      

      charList = crit.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getRequisitionsActiveAndOpenList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return charList;
  }
  
  public List getActiveNotDeletedRequisitionsList(long superUserKey)
  {
    logger.info("Inside getActiveNotDeletedRequisitionsList method");
    List charList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria crit = session.createCriteria(JobRequisition.class).add(Restrictions.eq("state", "Active")).add(Restrictions.ne("status", "Deleted")).add(Restrictions.eq("super_user_key", Long.valueOf(superUserKey)));
      

      charList = crit.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getActiveNotDeletedRequisitionsList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return charList;
  }
  
  public Organization getOrganizationByReqId(long reqId)
  {
    logger.info("Inside getOrganizationByReqId method");
    Organization org = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      JobRequisition req = (JobRequisition)session.createCriteria(JobRequisition.class).add(Restrictions.eq("jobreqId", Long.valueOf(reqId))).uniqueResult();
      

      org = req.getOrganization();
    }
    catch (Exception e)
    {
      logger.error("Exception on getOrganizationByReqId()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return org;
  }
  
  public List getJobRequistionTemplatesForPagination(int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getJobRequistionTemplatesForPagination method");
    List tmplList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      Criteria outer = session.createCriteria(JobRequisionTemplate.class).add(Restrictions.ne("status", "Deleted"));
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      tmplList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getJobRequistionTemplatesForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return tmplList;
  }
  
  public void saveAttachment(RequistionAttachments attachment)
  {
    logger.info("Inside saveAttachment method");
    User user = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      session.save(attachment);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveAttachment()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public List getAttachmentList(long reqId, String type)
  {
    logger.info("Inside getAttachmentList method");
    
    List offerAttachmentList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = session.createCriteria(RequistionAttachments.class).add(Restrictions.eq("reqId", Long.valueOf(reqId))).add(Restrictions.eq("type", type));
      
      offerAttachmentList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAttachmentList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return offerAttachmentList;
  }
  
  public List getActivityList(long reqId)
  {
    logger.info("Inside getActivityList method");
    
    List atList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = session.createCriteria(RequisitionActivity.class).add(Restrictions.eq("reqId", Long.valueOf(reqId)));
      atList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getActivityList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return atList;
  }
  
  public RequistionAttachments getAttachmentDetailsByUuid(String uuid)
  {
    logger.info("Inside getAttachmentDetailsByUuid method");
    
    RequistionAttachments offattach = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      offattach = (RequistionAttachments)session.createCriteria(RequistionAttachments.class).add(Restrictions.eq("uuid", uuid)).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAttachmentDetailsByUuid()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return offattach;
  }
  
  public void deleteAttachment(long id, String type)
  {
    logger.info("Inside deleteAttachment method");
    
    ApplicantOfferAttachment offattach = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      String hql = "delete from RequistionAttachments where reqId = " + id + " and type=" + "'" + type + "'";
      Query query = session.createQuery(hql);
      int row = query.executeUpdate();
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteAttachment()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void deleteAttachment(String uuid)
  {
    logger.info("Inside deleteAttachment method");
    
    ApplicantOfferAttachment offattach = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      String hql = "delete from RequistionAttachments where uuid = :inuuid";
      Query query = session.createQuery(hql);
      query.setString("inuuid", uuid);
      int rowCount = query.executeUpdate();
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteAttachment()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public List getJobRequistionTemplatesForPagination(String templatename, String orgId, String departmentId, String jobgradeId, String jobtypeId, String statuscri, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getJobRequistionTemplatesForPagination method");
    List tmplList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      Criteria outer = session.createCriteria(JobRequisionTemplate.class).add(Restrictions.ne("status", "Deleted"));
      if (!StringUtils.isNullOrEmpty(templatename)) {
        outer.add(Restrictions.like("templateName", "%" + templatename + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(orgId)) && (!orgId.equals("0")))
      {
        outer.createAlias("organization", "organization");
        outer.add(Restrictions.eq("organization.orgId", new Long(orgId)));
      }
      if ((!StringUtils.isNullOrEmpty(departmentId)) && (!departmentId.equals("0")))
      {
        outer.createAlias("department", "department");
        outer.add(Restrictions.eq("department.departmentId", new Long(departmentId)));
      }
      if ((!StringUtils.isNullOrEmpty(jobgradeId)) && (!jobgradeId.equals("0")))
      {
        outer.createAlias("jobgrade", "jobgrade");
        outer.add(Restrictions.eq("jobgrade.jobgradeId", new Long(jobgradeId)));
      }
      if ((!StringUtils.isNullOrEmpty(jobtypeId)) && (!jobtypeId.equals("0")))
      {
        outer.createAlias("jobtype", "jobtype");
        outer.add(Restrictions.eq("jobtype.jobTypeId", new Long(jobtypeId)));
      }
      if (!StringUtils.isNullOrEmpty(statuscri)) {
        outer.add(Restrictions.eq("status", statuscri));
      }
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      tmplList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getJobRequistionTemplatesForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return tmplList;
  }
  
  public List getTotalAllRequistionsByBudgetCodeId(long budgetcodeId)
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
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return tmplList;
  }
  
  public List getSearchJobRequisitionPagination(String jobreqname, String jobreqid, String orgId, String departmentId, String jobgradeId, String jobtypeId, String statuscri, String statecri, String jobreqcode, String budgetcodeid, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getSearchJobRequisitionPagination method");
    List tmplList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      Criteria outer = null;
      if ((!StringUtils.isNullOrEmpty(statuscri)) && (statuscri.equals("Deleted"))) {
        outer = session.createCriteria(JobRequisition.class);
      } else {
        outer = session.createCriteria(JobRequisition.class).add(Restrictions.ne("status", "Deleted"));
      }
      if (!StringUtils.isNullOrEmpty(jobreqname)) {
        outer.add(Restrictions.like("jobreqName", "%" + jobreqname + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(jobreqid)) && (!jobreqid.equals("0"))) {
        outer.add(Restrictions.eq("jobreqId", new Long(jobreqid)));
      }
      if (!StringUtils.isNullOrEmpty(jobreqcode)) {
        outer.add(Restrictions.eq("jobreqcode", jobreqcode));
      }
      if ((!StringUtils.isNullOrEmpty(orgId)) && (!orgId.equals("0")))
      {
        outer.createAlias("organization", "organization");
        outer.add(Restrictions.eq("organization.orgId", new Long(orgId)));
      }
      if ((!StringUtils.isNullOrEmpty(departmentId)) && (!departmentId.equals("0")))
      {
        outer.createAlias("department", "department");
        outer.add(Restrictions.eq("department.departmentId", new Long(departmentId)));
      }
      if ((!StringUtils.isNullOrEmpty(jobgradeId)) && (!jobgradeId.equals("0")))
      {
        outer.createAlias("jobgrade", "jobgrade");
        outer.add(Restrictions.eq("jobgrade.jobgradeId", new Long(jobgradeId)));
      }
      if ((!StringUtils.isNullOrEmpty(jobtypeId)) && (!jobtypeId.equals("0")))
      {
        outer.createAlias("jobtype", "jobtype");
        outer.add(Restrictions.eq("jobtype.jobTypeId", new Long(jobtypeId)));
      }
      if ((!StringUtils.isNullOrEmpty(budgetcodeid)) && (!budgetcodeid.equals("0")))
      {
        outer.createAlias("budgetcode", "budgetcode");
        outer.add(Restrictions.eq("budgetcode.budgetId", new Long(budgetcodeid)));
      }
      if (!StringUtils.isNullOrEmpty(statuscri)) {
        outer.add(Restrictions.eq("status", statuscri));
      }
      if (!StringUtils.isNullOrEmpty(statecri)) {
        outer.add(Restrictions.eq("state", statecri));
      }
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      tmplList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getSearchJobRequisitionPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return tmplList;
  }
  
  public Map searchJobRequisitions(User user, RequisitionSearchCriteriaMultiple searchCriteria, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside searchJobRequisitions method");
    List tmplList = new ArrayList();
    List reqIdList = new ArrayList();
    Map m = new HashMap();
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      String sql = requitionCustomSearchQuery(searchCriteria);
      if (!StringUtils.isNullOrEmpty(sql))
      {
        Query query = session.createSQLQuery(sql);
        
        List idList = query.list();
        if ((idList != null) && (idList.size() > 0)) {
          for (int i = 0; i < idList.size(); i++)
          {
            BigInteger id = (BigInteger)idList.get(i);
            reqIdList.add(Long.valueOf(id.longValue()));
          }
        } else {
          reqIdList.add(Long.valueOf(new Long(0L).longValue()));
        }
      }
      Criteria outer = null;
      if (searchCriteria.getStatusList().size() > 0) {
        outer = session.createCriteria(JobRequisition.class).add(Expression.in("status", searchCriteria.getStatusList()));
      } else {
        outer = session.createCriteria(JobRequisition.class).add(Restrictions.ne("status", "Deleted"));
      }
      if (!StringUtils.isNullOrEmpty(sql)) {
        outer.add(Restrictions.in("jobreqId", reqIdList));
      }
      outer.add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      if ((!StringUtils.isNullOrEmpty(searchCriteria.getJobreqName())) && (!searchCriteria.getJobreqName().equals("null"))) {
        outer.add(Restrictions.like("jobreqName", "%" + searchCriteria.getJobreqName() + "%"));
      }
      if (searchCriteria.getRequisition_number() != 0L) {
        outer.add(Restrictions.eq("requisition_number", new Long(searchCriteria.getRequisition_number())));
      }
      if ((!StringUtils.isNullOrEmpty(searchCriteria.getJobreqcode())) && (!searchCriteria.getJobreqcode().equals("null"))) {
        outer.add(Restrictions.eq("jobreqcode", searchCriteria.getJobreqcode()));
      }
      if (searchCriteria.getOrgIdList().size() > 0) {
        outer.add(Expression.in("organization.orgId", searchCriteria.getOrgIdList()));
      }
      if (searchCriteria.getDepartmentIdsList().size() > 0) {
        outer.add(Expression.in("department.departmentId", searchCriteria.getDepartmentIdsList()));
      }
      if (searchCriteria.getJobgradeIdList().size() > 0) {
        outer.add(Expression.in("jobgrade.jobgradeId", searchCriteria.getJobgradeIdList()));
      }
      if (searchCriteria.getJobtypeIdList().size() > 0) {
        outer.add(Expression.in("jobtype.jobTypeId", searchCriteria.getJobtypeIdList()));
      }
      if (searchCriteria.getBudgetcodeIdList().size() > 0) {
        outer.add(Expression.in("budgetcode.budgetId", searchCriteria.getBudgetcodeIdList()));
      }
      if (searchCriteria.getStateList().size() > 0) {
        outer.add(Expression.in("state", searchCriteria.getStateList()));
      }
      if ((!StringUtils.isNullOrEmpty(searchCriteria.getIsinternal())) && (!searchCriteria.getIsinternal().equals("null"))) {
        outer.add(Restrictions.eq("internal", searchCriteria.getIsinternal()));
      }
      if ((!StringUtils.isNullOrEmpty(searchCriteria.getIsnewposition())) && (!searchCriteria.getIsnewposition().equals("null"))) {
        outer.add(Restrictions.eq("isnewPositions", searchCriteria.getIsnewposition()));
      }
      if ((!StringUtils.isNullOrEmpty(searchCriteria.getIspublishredtoemployee())) && (!searchCriteria.getIspublishredtoemployee().equals("null"))) {
        outer.add(Restrictions.eq("publishToEmpRef", searchCriteria.getIspublishredtoemployee()));
      }
      if ((!StringUtils.isNullOrEmpty(searchCriteria.getIspublishredtoexternal())) && (!searchCriteria.getIspublishredtoexternal().equals("null"))) {
        outer.add(Restrictions.eq("publishToExternal", searchCriteria.getIspublishredtoexternal()));
      }
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      logger.info("outer.toString()" + outer.toString());
      
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      tmplList = outer.list();
      




      m.put(Common.REQUISTION_LIST, tmplList);
      

      outer = null;
      if (searchCriteria.getStatusList().size() > 0) {
        outer = session.createCriteria(JobRequisition.class).add(Expression.in("status", searchCriteria.getStatusList()));
      } else {
        outer = session.createCriteria(JobRequisition.class).add(Restrictions.ne("status", "Deleted"));
      }
      if (!StringUtils.isNullOrEmpty(sql)) {
        outer.add(Restrictions.in("jobreqId", reqIdList));
      }
      outer.add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      if ((!StringUtils.isNullOrEmpty(searchCriteria.getJobreqName())) && (!searchCriteria.getJobreqName().equals("null"))) {
        outer.add(Restrictions.like("jobreqName", "%" + searchCriteria.getJobreqName() + "%"));
      }
      if (searchCriteria.getRequisition_number() != 0L) {
        outer.add(Restrictions.eq("requisition_number", new Long(searchCriteria.getRequisition_number())));
      }
      if ((!StringUtils.isNullOrEmpty(searchCriteria.getJobreqcode())) && (!searchCriteria.getJobreqcode().equals("null"))) {
        outer.add(Restrictions.eq("jobreqcode", searchCriteria.getJobreqcode()));
      }
      if (searchCriteria.getOrgIdList().size() > 0) {
        outer.add(Expression.in("organization.orgId", searchCriteria.getOrgIdList()));
      }
      if (searchCriteria.getDepartmentIdsList().size() > 0) {
        outer.add(Expression.in("department.departmentId", searchCriteria.getDepartmentIdsList()));
      }
      if (searchCriteria.getJobgradeIdList().size() > 0) {
        outer.add(Expression.in("jobgrade.jobgradeId", searchCriteria.getJobgradeIdList()));
      }
      if (searchCriteria.getJobtypeIdList().size() > 0) {
        outer.add(Expression.in("jobtype.jobTypeId", searchCriteria.getJobtypeIdList()));
      }
      if (searchCriteria.getBudgetcodeIdList().size() > 0) {
        outer.add(Expression.in("budgetcode.budgetId", searchCriteria.getBudgetcodeIdList()));
      }
      if (searchCriteria.getStateList().size() > 0) {
        outer.add(Expression.in("state", searchCriteria.getStateList()));
      }
      if ((!StringUtils.isNullOrEmpty(searchCriteria.getIsinternal())) && (!searchCriteria.getIsinternal().equals("null"))) {
        outer.add(Restrictions.eq("internal", searchCriteria.getIsinternal()));
      }
      if ((!StringUtils.isNullOrEmpty(searchCriteria.getIsnewposition())) && (!searchCriteria.getIsnewposition().equals("null"))) {
        outer.add(Restrictions.eq("isnewPositions", searchCriteria.getIsnewposition()));
      }
      if ((!StringUtils.isNullOrEmpty(searchCriteria.getIspublishredtoemployee())) && (!searchCriteria.getIspublishredtoemployee().equals("null"))) {
        outer.add(Restrictions.eq("publishToEmpRef", searchCriteria.getIspublishredtoemployee()));
      }
      if ((!StringUtils.isNullOrEmpty(searchCriteria.getIspublishredtoexternal())) && (!searchCriteria.getIspublishredtoexternal().equals("null"))) {
        outer.add(Restrictions.eq("publishToExternal", searchCriteria.getIspublishredtoexternal()));
      }
      outer.setProjection(Projections.rowCount());
      totaluser = ((Integer)outer.list().get(0)).intValue();
      
      m.put(Common.REQUISTION_COUNT, new Integer(totaluser));
    }
    catch (Exception e)
    {
      logger.error("Exception on searchJobRequisitions()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return m;
  }
  
  private String requitionCustomSearchQuery(RequisitionSearchCriteriaMultiple criteria)
  {
    String sql = "";
    List formList = new ArrayList();
    formList.add("REQUISITION_FORM");
    if ((criteria.getCustomFieldCriList() != null) && (criteria.getCustomFieldCriList().size() > 0))
    {
      logger.info("criteria.getCustomFieldCriList().size()" + criteria.getCustomFieldCriList().size());
      
      List<String> sqlList = new ArrayList();
      for (int i = 0; i < criteria.getCustomFieldCriList().size(); i++)
      {
        String sqltemp = "";
        
        SearchCustomFields customdata = (SearchCustomFields)criteria.getCustomFieldCriList().get(i);
        logger.info("customdata.getFilterValue1()" + customdata.getFilterValue1());
        if ((customdata.getVaribale_type() != null) && (customdata.getVaribale_type().equals("numeric")))
        {
          if (!StringUtils.isNullOrEmpty(customdata.getFilterValue1()))
          {
            sqltemp = sqltemp + " select idvalue from variable_data_values where form_code in  (" + buildCommaseparated(formList) + ") and variable_id=" + customdata.getVariable_id() + " and CONVERT(value_text, DECIMAL(10,2)) = " + customdata.getFilterValue1();
            

            sqlList.add(sqltemp);
          }
        }
        else if ((customdata.getVaribale_type() != null) && (customdata.getVaribale_type().equals("date")))
        {
          if (!StringUtils.isNullOrEmpty(customdata.getFilterValue1()))
          {
            String datepattern = DateUtil.defaultdateformatforSQL;
            

            Date fdate = DateUtil.convertStringDateToDate(customdata.getFilterValue1(), datepattern);
            String date1 = DateUtil.convertDateToStringDate(fdate, datepattern);
            

            sqltemp = sqltemp + " select idvalue from variable_data_values where form_code in  (" + buildCommaseparated(formList) + ") and variable_id=" + customdata.getVariable_id() + " and DATE_FORMAT( value_text, '%M %e, %Y') = '" + date1 + "'";
            

            sqlList.add(sqltemp);
          }
        }
        else if ((customdata.getVaribale_type() != null) && ((customdata.getVaribale_type().equals("text")) || (customdata.getVaribale_type().equals("textarea"))))
        {
          if (!StringUtils.isNullOrEmpty(customdata.getFilterValue1()))
          {
            sqltemp = sqltemp + " select idvalue from variable_data_values where form_code in  (" + buildCommaseparated(formList) + ") and variable_id=" + customdata.getVariable_id() + " and value_text like '%" + customdata.getFilterValue1() + "%'";
            if (!StringUtils.isNullOrEmpty(sqltemp)) {
              sqlList.add(sqltemp);
            }
          }
        }
        else if ((customdata.getVaribale_type() != null) && (customdata.getVaribale_type().equals("list"))) {
          if (!StringUtils.isNullOrEmpty(customdata.getAnswerOption()))
          {
            sqltemp = sqltemp + " select idvalue from variable_data_values where form_code in  (" + buildCommaseparated(formList) + ") and variable_id=" + customdata.getVariable_id() + " and value_text = '" + customdata.getAnswerOption() + "'";
            
            sqlList.add(sqltemp);
          }
        }
      }
      String finalsql = "";
      if (sqlList.size() == 1)
      {
        finalsql = finalsql + (String)sqlList.get(0);
      }
      else if (sqlList.size() > 1)
      {
        finalsql = (String)sqlList.get(0);
        for (int i = 1; i < sqlList.size(); i++) {
          finalsql = finalsql + " and idvalue  in ( " + (String)sqlList.get(i) + " ) ";
        }
      }
      logger.info("finalsql" + finalsql);
      


      sql = finalsql;
    }
    return sql;
  }
  
  public static String buildCommaseparated(List<String> lst)
  {
    String str = "";
    for (int i = 0; i < lst.size(); i++) {
      str = str + "'" + (String)lst.get(i) + "'" + ",";
    }
    if (!StringUtils.isNullOrEmpty(str)) {
      str = str.substring(0, str.length() - 1);
    }
    return str;
  }
  
  public Map searchJobRequisitionsTemplate(User user, RequisitionTemplateSearchCriteriaMultiple searchCriteria, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside searchJobRequisitionsTemplate method");
    List tmplList = new ArrayList();
    Map m = new HashMap();
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      Criteria outer = null;
      if (searchCriteria.getStatusList().size() > 0) {
        outer = session.createCriteria(JobRequisionTemplate.class).add(Expression.in("status", searchCriteria.getStatusList()));
      } else {
        outer = session.createCriteria(JobRequisionTemplate.class).add(Restrictions.ne("status", "Deleted"));
      }
      outer.add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      if ((!StringUtils.isNullOrEmpty(searchCriteria.getTemplateName())) && (!searchCriteria.getTemplateName().equals("null"))) {
        outer.add(Restrictions.like("templateName", "%" + searchCriteria.getTemplateName() + "%"));
      }
      if (searchCriteria.getOrgIdList().size() > 0) {
        outer.add(Expression.in("organization.orgId", searchCriteria.getOrgIdList()));
      }
      if (searchCriteria.getDepartmentIdsList().size() > 0) {
        outer.add(Expression.in("department.departmentId", searchCriteria.getDepartmentIdsList()));
      }
      if (searchCriteria.getJobgradeIdList().size() > 0) {
        outer.add(Expression.in("jobgrade.jobgradeId", searchCriteria.getJobgradeIdList()));
      }
      if (searchCriteria.getJobtypeIdList().size() > 0) {
        outer.add(Expression.in("jobtype.jobTypeId", searchCriteria.getJobtypeIdList()));
      }
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      logger.info("outer.toString()" + outer.toString());
      
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      tmplList = outer.list();
      

      m.put(Common.REQUISTION_TEMPLATE_LIST, tmplList);
      

      outer = null;
      if (searchCriteria.getStatusList().size() > 0) {
        outer = session.createCriteria(JobRequisionTemplate.class).add(Expression.in("status", searchCriteria.getStatusList()));
      } else {
        outer = session.createCriteria(JobRequisionTemplate.class).add(Restrictions.ne("status", "Deleted"));
      }
      outer.add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      if ((!StringUtils.isNullOrEmpty(searchCriteria.getTemplateName())) && (!searchCriteria.getTemplateName().equals("null"))) {
        outer.add(Restrictions.like("templateName", "%" + searchCriteria.getTemplateName() + "%"));
      }
      if (searchCriteria.getOrgIdList().size() > 0) {
        outer.add(Expression.in("organization.orgId", searchCriteria.getOrgIdList()));
      }
      if (searchCriteria.getDepartmentIdsList().size() > 0) {
        outer.add(Expression.in("department.departmentId", searchCriteria.getDepartmentIdsList()));
      }
      if (searchCriteria.getJobgradeIdList().size() > 0) {
        outer.add(Expression.in("jobgrade.jobgradeId", searchCriteria.getJobgradeIdList()));
      }
      if (searchCriteria.getJobtypeIdList().size() > 0) {
        outer.add(Expression.in("jobtype.jobTypeId", searchCriteria.getJobtypeIdList()));
      }
      outer.setProjection(Projections.rowCount());
      totaluser = ((Integer)outer.list().get(0)).intValue();
      
      m.put(Common.REQUISTION_TEMPLATE_COUNT, new Integer(totaluser));
    }
    catch (Exception e)
    {
      logger.error("Exception on searchJobRequisitionsTemplate()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return m;
  }
  
  public Map searchJobRequisitionsExternalUser(long superUserKey, RequisitionSearchCriteriaMultiple searchCriteria, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside searchJobRequisitionsExternalUser method");
    List tmplList = new ArrayList();
    Map m = new HashMap();
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      Criteria outer = session.createCriteria(JobRequisition.class).add(Restrictions.eq("state", "Active")).add(Restrictions.eq("status", "Open")).add(Restrictions.eq("publishToExternal", "Y")).add(Restrictions.eq("super_user_key", Long.valueOf(superUserKey)));
      
      List<Long> appids = new ArrayList();
      if ((!StringUtils.isNullOrEmpty(searchCriteria.getKeyword())) && (!searchCriteria.getKeyword().equals("null")))
      {
        IndexSearch idxsearch = new IndexSearch();
        String keyword = searchCriteria.getKeyword();
        while ((keyword.startsWith("*")) && (keyword != null)) {
          keyword = keyword.substring(1, keyword.length());
        }
        logger.info("final keyword is after remove star first " + keyword);
        while ((keyword.startsWith("?")) && (keyword != null)) {
          keyword = keyword.substring(1, keyword.length());
        }
        logger.info("final keyword is after remove question first " + keyword);
        
        appids = idxsearch.searchRequistion(keyword);
        if (appids.size() > 0)
        {
          outer.add(Expression.in("jobreqId", appids));
        }
        else
        {
          appids.add(new Long(0L));
          outer.add(Expression.in("jobreqId", appids));
        }
      }
      if (searchCriteria.getJobreqId() != 0L) {
        outer.add(Restrictions.like("jobreqId", Long.valueOf(searchCriteria.getJobreqId())));
      }
      if ((!StringUtils.isNullOrEmpty(searchCriteria.getJobTitle())) && (!searchCriteria.getJobTitle().equals("null"))) {
        outer.add(Restrictions.like("jobTitle", "%" + searchCriteria.getJobTitle() + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(searchCriteria.getJobreqcode())) && (!searchCriteria.getJobreqcode().equals("null"))) {
        outer.add(Restrictions.eq("jobreqcode", searchCriteria.getJobreqcode()));
      }
      if (searchCriteria.getLocationIdList().size() > 0) {
        outer.add(Expression.in("location.locationId", searchCriteria.getLocationIdList()));
      }
      if (searchCriteria.getOrgIdList().size() > 0) {
        outer.add(Expression.in("organization.orgId", searchCriteria.getOrgIdList()));
      }
      if (searchCriteria.getDepartmentIdsList().size() > 0) {
        outer.add(Expression.in("department.departmentId", searchCriteria.getDepartmentIdsList()));
      }
      if (searchCriteria.getStateList().size() > 0) {
        outer.add(Expression.in("state", searchCriteria.getStateList()));
      }
      if (searchCriteria.getStatusList().size() > 0) {
        outer.add(Expression.in("status", searchCriteria.getStatusList()));
      }
      if (searchCriteria.getJobgradeIdList().size() > 0) {
        outer.add(Expression.in("jobgrade.jobgradeId", searchCriteria.getJobgradeIdList()));
      }
      if (searchCriteria.getJobtypeIdList().size() > 0) {
        outer.add(Expression.in("jobtype.jobTypeId", searchCriteria.getJobtypeIdList()));
      }
      if (searchCriteria.getCategoryIdList().size() > 0) {
        outer.add(Expression.in("catagory.catId", searchCriteria.getCategoryIdList()));
      }
      if (searchCriteria.getWorkshiftIdList().size() > 0) {
        outer.add(Expression.in("workshift.shiftId", searchCriteria.getWorkshiftIdList()));
      }
      if (searchCriteria.getPrimarySkillList().size() > 0) {
        outer.add(Expression.in("primarySkill", searchCriteria.getPrimarySkillList()));
      }
      if (searchCriteria.getBudgetcodeIdList().size() > 0) {
        outer.add(Expression.in("budgetcode.budgetId", searchCriteria.getBudgetcodeIdList()));
      }
      outer.add(Restrictions.ge("minyearsofExpRequired", Integer.valueOf(searchCriteria.getMinyearsofExpRequired())));
      if ((!StringUtils.isNullOrEmpty(searchCriteria.getSearchposteddate())) && (!searchCriteria.getSearchposteddate().equals("null")))
      {
        String datepattern = Constant.getValue("defaultdateformat");
        

        DateFormat format = new SimpleDateFormat(datepattern + " hh:mm:ss");
        
        Date aDate = format.parse(searchCriteria.getSearchposteddate() + " 00:00:00");
        
        Date bDate = format.parse(searchCriteria.getSearchposteddate() + " 23:59:00");
        if (searchCriteria.getAppliedCri().equals("on")) {
          outer.add(Expression.between("publishedDate", new Date(aDate.getTime()), new Date(bDate.getTime())));
        }
        if (searchCriteria.getAppliedCri().equals("after")) {
          outer.add(Expression.gt("publishedDate", new Date(aDate.getTime())));
        }
        if (searchCriteria.getAppliedCri().equals("before")) {
          outer.add(Expression.lt("publishedDate", new Date(aDate.getTime())));
        }
      }
      if ((StringUtils.isNullOrEmpty(searchCriteria.getKeyword())) || (searchCriteria.getKeyword().equals("null"))) {
        if (dir_str.equalsIgnoreCase("asc")) {
          outer.addOrder(Order.asc(sort_str));
        } else {
          outer.addOrder(Order.desc(sort_str));
        }
      }
      logger.info("outer.toString()" + outer.toString());
      
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      tmplList = outer.list();
      

      m.put(Common.REQUISTION_LIST, tmplList);
      

      outer = null;
      outer = session.createCriteria(JobRequisition.class).add(Restrictions.eq("state", "Active")).add(Restrictions.eq("status", "Open")).add(Restrictions.eq("publishToExternal", "Y")).add(Restrictions.eq("super_user_key", Long.valueOf(superUserKey)));
      if ((!StringUtils.isNullOrEmpty(searchCriteria.getKeyword())) && (!searchCriteria.getKeyword().equals("null"))) {
        if (appids.size() > 0)
        {
          outer.add(Expression.in("jobreqId", appids));
        }
        else
        {
          appids.add(new Long(0L));
          outer.add(Expression.in("jobreqId", appids));
        }
      }
      if (searchCriteria.getJobreqId() != 0L) {
        outer.add(Restrictions.like("jobreqId", Long.valueOf(searchCriteria.getJobreqId())));
      }
      if ((!StringUtils.isNullOrEmpty(searchCriteria.getJobTitle())) && (!searchCriteria.getJobTitle().equals("null"))) {
        outer.add(Restrictions.like("jobTitle", "%" + searchCriteria.getJobTitle() + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(searchCriteria.getJobreqcode())) && (!searchCriteria.getJobreqcode().equals("null"))) {
        outer.add(Restrictions.eq("jobreqcode", searchCriteria.getJobreqcode()));
      }
      if (searchCriteria.getLocationIdList().size() > 0) {
        outer.add(Expression.in("location.locationId", searchCriteria.getLocationIdList()));
      }
      if (searchCriteria.getOrgIdList().size() > 0) {
        outer.add(Expression.in("organization.orgId", searchCriteria.getOrgIdList()));
      }
      if (searchCriteria.getDepartmentIdsList().size() > 0) {
        outer.add(Expression.in("department.departmentId", searchCriteria.getDepartmentIdsList()));
      }
      if (searchCriteria.getStateList().size() > 0) {
        outer.add(Expression.in("state", searchCriteria.getStateList()));
      }
      if (searchCriteria.getStatusList().size() > 0) {
        outer.add(Expression.in("status", searchCriteria.getStatusList()));
      }
      if (searchCriteria.getJobgradeIdList().size() > 0) {
        outer.add(Expression.in("jobgrade.jobgradeId", searchCriteria.getJobgradeIdList()));
      }
      if (searchCriteria.getJobtypeIdList().size() > 0) {
        outer.add(Expression.in("jobtype.jobTypeId", searchCriteria.getJobtypeIdList()));
      }
      if (searchCriteria.getCategoryIdList().size() > 0) {
        outer.add(Expression.in("catagory.catId", searchCriteria.getCategoryIdList()));
      }
      if (searchCriteria.getWorkshiftIdList().size() > 0) {
        outer.add(Expression.in("workshift.shiftId", searchCriteria.getWorkshiftIdList()));
      }
      if (searchCriteria.getPrimarySkillList().size() > 0) {
        outer.add(Expression.in("primarySkill", searchCriteria.getPrimarySkillList()));
      }
      if (searchCriteria.getBudgetcodeIdList().size() > 0) {
        outer.add(Expression.in("budgetcode.budgetId", searchCriteria.getBudgetcodeIdList()));
      }
      outer.add(Restrictions.ge("minyearsofExpRequired", Integer.valueOf(searchCriteria.getMinyearsofExpRequired())));
      if ((!StringUtils.isNullOrEmpty(searchCriteria.getSearchposteddate())) && (!searchCriteria.getSearchposteddate().equals("null")))
      {
        String datepattern = Constant.getValue("defaultdateformat");
        

        DateFormat format = new SimpleDateFormat(datepattern + " hh:mm:ss");
        
        Date aDate = format.parse(searchCriteria.getSearchposteddate() + " 00:00:00");
        
        Date bDate = format.parse(searchCriteria.getSearchposteddate() + " 23:59:00");
        if (searchCriteria.getAppliedCri().equals("on")) {
          outer.add(Expression.between("publishedDate", new Date(aDate.getTime()), new Date(bDate.getTime())));
        }
        if (searchCriteria.getAppliedCri().equals("after")) {
          outer.add(Expression.gt("publishedDate", new Date(aDate.getTime())));
        }
        if (searchCriteria.getAppliedCri().equals("before")) {
          outer.add(Expression.lt("publishedDate", new Date(aDate.getTime())));
        }
      }
      outer.setProjection(Projections.rowCount());
      totaluser = ((Integer)outer.list().get(0)).intValue();
      
      m.put(Common.REQUISTION_COUNT, new Integer(totaluser));
    }
    catch (Exception e)
    {
      logger.error("Exception on searchJobRequisitionsExternalUser()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return m;
  }
  
  public Map searchJobRequisitionsExternalUserSimple(long superUserKey, RequisitionSearchCriteriaMultiple searchCriteria, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside searchJobRequisitionsExternalUserSimple method");
    List tmplList = new ArrayList();
    Map m = new HashMap();
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      Criteria outer = session.createCriteria(JobRequisition.class).add(Restrictions.eq("state", "Active")).add(Restrictions.eq("status", "Open")).add(Restrictions.eq("publishToExternal", "Y")).add(Restrictions.eq("super_user_key", Long.valueOf(superUserKey)));
      
      List<Long> appids = new ArrayList();
      if ((!StringUtils.isNullOrEmpty(searchCriteria.getKeyword())) && (!searchCriteria.getKeyword().equals("null")))
      {
        IndexSearch idxsearch = new IndexSearch();
        String keyword = searchCriteria.getKeyword();
        while ((keyword.startsWith("*")) && (keyword != null)) {
          keyword = keyword.substring(1, keyword.length());
        }
        logger.info("final keyword is after remove star first " + keyword);
        while ((keyword.startsWith("?")) && (keyword != null)) {
          keyword = keyword.substring(1, keyword.length());
        }
        logger.info("final keyword is after remove question first " + keyword);
        
        appids = idxsearch.searchRequistion(keyword);
        if (appids.size() > 0)
        {
          outer.add(Expression.in("jobreqId", appids));
        }
        else
        {
          appids.add(new Long(0L));
          outer.add(Expression.in("jobreqId", appids));
        }
      }
      if ((StringUtils.isNullOrEmpty(searchCriteria.getKeyword())) || (searchCriteria.getKeyword().equals("null"))) {
        if (dir_str.equalsIgnoreCase("asc")) {
          outer.addOrder(Order.asc(sort_str));
        } else {
          outer.addOrder(Order.desc(sort_str));
        }
      }
      logger.info("outer.toString()" + outer.toString());
      
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      tmplList = outer.list();
      

      m.put(Common.REQUISTION_LIST, tmplList);
      

      outer = null;
      outer = session.createCriteria(JobRequisition.class).add(Restrictions.eq("state", "Active")).add(Restrictions.eq("status", "Open")).add(Restrictions.eq("publishToExternal", "Y")).add(Restrictions.eq("super_user_key", Long.valueOf(superUserKey)));
      if ((!StringUtils.isNullOrEmpty(searchCriteria.getKeyword())) && (!searchCriteria.getKeyword().equals("null"))) {
        if (appids.size() > 0)
        {
          outer.add(Expression.in("jobreqId", appids));
        }
        else
        {
          appids.add(new Long(0L));
          outer.add(Expression.in("jobreqId", appids));
        }
      }
      outer.setProjection(Projections.rowCount());
      totaluser = ((Integer)outer.list().get(0)).intValue();
      
      m.put(Common.REQUISTION_COUNT, new Integer(totaluser));
    }
    catch (Exception e)
    {
      logger.error("Exception on searchJobRequisitionsExternalUserSimple()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return m;
  }
  
  public List getSearchJobRequisitionforExport(RequisitionSearchCriteriaMultiple searchCriteria)
  {
    logger.info("Inside getSearchJobRequisitionforExport method");
    List tmplList = new ArrayList();
    List reqIdList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      String sql = requitionCustomSearchQuery(searchCriteria);
      if (!StringUtils.isNullOrEmpty(sql))
      {
        Query query = session.createSQLQuery(sql);
        
        List idList = query.list();
        if ((idList != null) && (idList.size() > 0)) {
          for (int i = 0; i < idList.size(); i++)
          {
            BigInteger id = (BigInteger)idList.get(i);
            reqIdList.add(Long.valueOf(id.longValue()));
          }
        } else {
          reqIdList.add(Long.valueOf(new Long(0L).longValue()));
        }
      }
      Criteria outer = null;
      if (searchCriteria.getStatusList().size() > 0) {
        outer = session.createCriteria(JobRequisition.class).add(Expression.in("status", searchCriteria.getStatusList()));
      } else {
        outer = session.createCriteria(JobRequisition.class).add(Restrictions.ne("status", "Deleted"));
      }
      if (!StringUtils.isNullOrEmpty(sql)) {
        outer.add(Restrictions.in("jobreqId", reqIdList));
      }
      if ((!StringUtils.isNullOrEmpty(searchCriteria.getJobreqName())) && (!searchCriteria.getJobreqName().equals("null"))) {
        outer.add(Restrictions.like("jobreqName", "%" + searchCriteria.getJobreqName() + "%"));
      }
      if (searchCriteria.getJobreqId() != 0L) {
        outer.add(Restrictions.eq("jobreqId", new Long(searchCriteria.getJobreqId())));
      }
      if ((!StringUtils.isNullOrEmpty(searchCriteria.getJobreqcode())) && (!searchCriteria.getJobreqcode().equals("null"))) {
        outer.add(Restrictions.eq("jobreqcode", searchCriteria.getJobreqcode()));
      }
      if (searchCriteria.getOrgIdList().size() > 0) {
        outer.add(Expression.in("organization.orgId", searchCriteria.getOrgIdList()));
      }
      if (searchCriteria.getDepartmentIdsList().size() > 0) {
        outer.add(Expression.in("department.departmentId", searchCriteria.getDepartmentIdsList()));
      }
      if (searchCriteria.getJobgradeIdList().size() > 0) {
        outer.add(Expression.in("jobgrade.jobgradeId", searchCriteria.getJobgradeIdList()));
      }
      if (searchCriteria.getJobtypeIdList().size() > 0) {
        outer.add(Expression.in("jobtype.jobTypeId", searchCriteria.getJobtypeIdList()));
      }
      if (searchCriteria.getBudgetcodeIdList().size() > 0) {
        outer.add(Expression.in("budgetcode.budgetId", searchCriteria.getBudgetcodeIdList()));
      }
      if (searchCriteria.getStateList().size() > 0) {
        outer.add(Expression.in("state", searchCriteria.getStateList()));
      }
      if ((!StringUtils.isNullOrEmpty(searchCriteria.getIsinternal())) && (!searchCriteria.getIsinternal().equals("null"))) {
        outer.add(Restrictions.eq("internal", searchCriteria.getIsinternal()));
      }
      if ((!StringUtils.isNullOrEmpty(searchCriteria.getIsnewposition())) && (!searchCriteria.getIsnewposition().equals("null"))) {
        outer.add(Restrictions.eq("isnewPositions", searchCriteria.getIsnewposition()));
      }
      if ((!StringUtils.isNullOrEmpty(searchCriteria.getIspublishredtoemployee())) && (!searchCriteria.getIspublishredtoemployee().equals("null"))) {
        outer.add(Restrictions.eq("publishToEmpRef", searchCriteria.getIspublishredtoemployee()));
      }
      if ((!StringUtils.isNullOrEmpty(searchCriteria.getIspublishredtoexternal())) && (!searchCriteria.getIspublishredtoexternal().equals("null"))) {
        outer.add(Restrictions.eq("publishToExternal", searchCriteria.getIspublishredtoexternal()));
      }
      outer.addOrder(Order.desc("jobreqId"));
      tmplList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getSearchJobRequisitionforExport()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return tmplList;
  }
  
  public List getSearchJobMyRequisitionHigiringManagerforExport(long currentuserid, String currentusername, RequisitionSearchCriteriaMultiple searchCriteria)
  {
    logger.info("Inside getSearchJobMyRequisitionHigiringManagerforExport method");
    List tmplList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = null;
      if (searchCriteria.getStatusList().size() > 0) {
        outer = session.createCriteria(JobRequisition.class).add(Expression.in("status", searchCriteria.getStatusList()));
      } else {
        outer = session.createCriteria(JobRequisition.class).add(Restrictions.ne("status", "Deleted"));
      }
      outer.createAlias("hiringmgr", "hiringmgr");
      
      Criterion hiringmgr = Restrictions.eq("hiringmgr.userId", new Long(currentuserid));
      Criterion createdby = Restrictions.eq("createdBy", currentusername);
      Disjunction disjunction = Restrictions.disjunction();
      disjunction.add(hiringmgr);
      disjunction.add(createdby);
      outer.add(disjunction);
      if ((!StringUtils.isNullOrEmpty(searchCriteria.getJobreqName())) && (!searchCriteria.getJobreqName().equals("null"))) {
        outer.add(Restrictions.like("jobreqName", "%" + searchCriteria.getJobreqName() + "%"));
      }
      if (searchCriteria.getJobreqId() != 0L) {
        outer.add(Restrictions.eq("jobreqId", new Long(searchCriteria.getJobreqId())));
      }
      if ((!StringUtils.isNullOrEmpty(searchCriteria.getJobreqcode())) && (!searchCriteria.getJobreqcode().equals("null"))) {
        outer.add(Restrictions.eq("jobreqcode", searchCriteria.getJobreqcode()));
      }
      if (searchCriteria.getOrgIdList().size() > 0) {
        outer.add(Expression.in("organization.orgId", searchCriteria.getOrgIdList()));
      }
      if (searchCriteria.getDepartmentIdsList().size() > 0) {
        outer.add(Expression.in("department.departmentId", searchCriteria.getDepartmentIdsList()));
      }
      if (searchCriteria.getJobgradeIdList().size() > 0) {
        outer.add(Expression.in("jobgrade.jobgradeId", searchCriteria.getJobgradeIdList()));
      }
      if (searchCriteria.getJobtypeIdList().size() > 0) {
        outer.add(Expression.in("jobtype.jobTypeId", searchCriteria.getJobtypeIdList()));
      }
      if (searchCriteria.getBudgetcodeIdList().size() > 0) {
        outer.add(Expression.in("budgetcode.budgetId", searchCriteria.getBudgetcodeIdList()));
      }
      if (searchCriteria.getStateList().size() > 0) {
        outer.add(Expression.in("state", searchCriteria.getStateList()));
      }
      outer.addOrder(Order.desc("jobreqId"));
      tmplList = outer.list();
      



      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getSearchJobMyRequisitionHigiringManagerforExport()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return tmplList;
  }
  
  public List getSearchJobMyRequisitionRecruiterforExport(long currentuserid, RequisitionSearchCriteriaMultiple searchCriteria)
  {
    logger.info("Inside getSearchJobMyRequisitionRecruiterforExport method");
    List tmplList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String hqlquery = " select distinct j from JobRequisition j , UserGroup ug where  ((j.recruiterId = :currentuserid and j.isgrouprecruiter <> 'Y') or (j.recruiterId = ug.usergrpId and  j.isgrouprecruiter='Y' and ug.users.userId =:currentuserid)) ";
      if (searchCriteria.getStatusList().size() > 0) {
        hqlquery = hqlquery + " and j.status IN (:statusList)";
      } else {
        hqlquery = hqlquery + " and j.status <> 'Deleted'";
      }
      if ((!StringUtils.isNullOrEmpty(searchCriteria.getJobreqName())) && (!searchCriteria.getJobreqName().equals("null"))) {
        hqlquery = hqlquery + " and j.jobreqName like :jobreqname";
      }
      if (searchCriteria.getJobreqId() != 0L) {
        hqlquery = hqlquery + " and j.jobreqId = :jobreqid";
      }
      if ((!StringUtils.isNullOrEmpty(searchCriteria.getJobreqcode())) && (!searchCriteria.getJobreqcode().equals("null")) && (!searchCriteria.getJobreqcode().equals("0"))) {
        hqlquery = hqlquery + " and j.jobreqcode = :jobreqcode";
      }
      if (searchCriteria.getOrgIdList().size() > 0) {
        hqlquery = hqlquery + " and j.organization.orgId IN (:orgList)";
      }
      if (searchCriteria.getDepartmentIdsList().size() > 0) {
        hqlquery = hqlquery + " and j.department.departmentId IN (:departmentIdsList)";
      }
      if (searchCriteria.getJobgradeIdList().size() > 0) {
        hqlquery = hqlquery + " and j.jobgrade.jobgradeId IN (:jobgradeIdList)";
      }
      if (searchCriteria.getJobtypeIdList().size() > 0) {
        hqlquery = hqlquery + " and j.jobtype.jobTypeId IN (:jobtypeIdList)";
      }
      if (searchCriteria.getBudgetcodeIdList().size() > 0) {
        hqlquery = hqlquery + " and j.budgetcode.budgetId IN (:budgetcodeIdList)";
      }
      if (searchCriteria.getStatusList().size() > 0) {
        hqlquery = hqlquery + " and j.status IN (:statusList)";
      }
      if (searchCriteria.getStateList().size() > 0) {
        hqlquery = hqlquery + " and j.state IN (:stateList)";
      }
      hqlquery = hqlquery + " order by j.jobreqId asc";
      


      Query query = session.createQuery(hqlquery);
      
      query.setLong("currentuserid", new Long(currentuserid).longValue());
      if (searchCriteria.getStatusList().size() > 0) {
        query.setParameterList("statusList", searchCriteria.getStatusList());
      }
      if ((!StringUtils.isNullOrEmpty(searchCriteria.getJobreqName())) && (!searchCriteria.getJobreqName().equals("null"))) {
        query.setString("jobreqname", '%' + searchCriteria.getJobreqName() + '%');
      }
      if (searchCriteria.getJobreqId() != 0L) {
        query.setLong("jobreqid", new Long(searchCriteria.getJobreqId()).longValue());
      }
      if ((!StringUtils.isNullOrEmpty(searchCriteria.getJobreqcode())) && (!searchCriteria.getJobreqcode().equals("null")) && (!searchCriteria.getJobreqcode().equals("0"))) {
        query.setString("jobreqcode", searchCriteria.getJobreqcode());
      }
      if (searchCriteria.getOrgIdList().size() > 0) {
        query.setParameterList("orgList", searchCriteria.getOrgIdList());
      }
      if (searchCriteria.getDepartmentIdsList().size() > 0) {
        query.setParameterList("departmentIdsList", searchCriteria.getDepartmentIdsList());
      }
      if (searchCriteria.getJobgradeIdList().size() > 0) {
        query.setParameterList("jobgradeIdList", searchCriteria.getJobgradeIdList());
      }
      if (searchCriteria.getJobtypeIdList().size() > 0) {
        query.setParameterList("jobtypeIdList", searchCriteria.getJobtypeIdList());
      }
      if (searchCriteria.getBudgetcodeIdList().size() > 0) {
        query.setParameterList("budgetcodeIdList", searchCriteria.getBudgetcodeIdList());
      }
      if (searchCriteria.getStatusList().size() > 0) {
        query.setParameterList("statusList", searchCriteria.getStatusList());
      }
      if (searchCriteria.getStateList().size() > 0) {
        query.setParameterList("stateList", searchCriteria.getStateList());
      }
      tmplList = query.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getSearchJobMyRequisitionRecruiterforExport()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return tmplList;
  }
  
  public List getSearchJobRequisitionPagination(int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getSearchJobRequisitionPagination method");
    List tmplList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      Criteria outer = null;
      
      outer = session.createCriteria(JobRequisition.class).add(Restrictions.ne("status", "Deleted"));
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      tmplList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getSearchJobRequisitionPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return tmplList;
  }
  
  public List getSearchJobsPagination(int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getSearchJobsPagination method");
    List tmplList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      



      Criteria outer = session.createCriteria(JobRequisition.class).add(Restrictions.eq("state", "Active")).add(Restrictions.eq("status", "Open"));
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      tmplList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getSearchJobsPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return tmplList;
  }
  
  public String getCurrencyCodeByReqId(long reqId)
  {
    logger.info("Inside getCurrencyCodeByReqId method");
    String currencycode = "";
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = null;
      
      outer = session.createCriteria(JobRequisition.class).add(Restrictions.eq("jobreqId", Long.valueOf(reqId)));
      JobRequisition req = (JobRequisition)outer.uniqueResult();
      currencycode = req.getOrganization().getCurrencyCode();
    }
    catch (Exception e)
    {
      logger.error("Exception on getCurrencyCodeByReqId()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return currencycode;
  }
  
  public int getCountOfSearchJobRequisitionPagination(String jobreqname, String jobreqid, String orgId, String departmentId, String jobgradeId, String jobtypeId, String statuscri, String statecri, String jobreqcode, String budgetcodeid)
  {
    logger.info("Inside getCountOfSearchJobRequisitionPagination method");
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      Criteria outer = null;
      if ((!StringUtils.isNullOrEmpty(statuscri)) && (statuscri.equals("Deleted"))) {
        outer = session.createCriteria(JobRequisition.class);
      } else {
        outer = session.createCriteria(JobRequisition.class).add(Restrictions.ne("status", "Deleted"));
      }
      if (!StringUtils.isNullOrEmpty(jobreqname)) {
        outer.add(Restrictions.like("jobreqName", "%" + jobreqname + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(jobreqid)) && (!jobreqid.equals("0"))) {
        outer.add(Restrictions.eq("jobreqId", new Long(jobreqid)));
      }
      if (!StringUtils.isNullOrEmpty(jobreqcode)) {
        outer.add(Restrictions.eq("jobreqcode", jobreqcode));
      }
      if ((!StringUtils.isNullOrEmpty(orgId)) && (!orgId.equals("0")))
      {
        outer.createAlias("organization", "organization");
        outer.add(Restrictions.eq("organization.orgId", new Long(orgId)));
      }
      if ((!StringUtils.isNullOrEmpty(departmentId)) && (!departmentId.equals("0")))
      {
        outer.createAlias("department", "department");
        outer.add(Restrictions.eq("department.departmentId", new Long(departmentId)));
      }
      if ((!StringUtils.isNullOrEmpty(jobgradeId)) && (!jobgradeId.equals("0")))
      {
        outer.createAlias("jobgrade", "jobgrade");
        outer.add(Restrictions.eq("jobgrade.jobgradeId", new Long(jobgradeId)));
      }
      if ((!StringUtils.isNullOrEmpty(jobtypeId)) && (!jobtypeId.equals("0")))
      {
        outer.createAlias("jobtype", "jobtype");
        outer.add(Restrictions.eq("jobtype.jobTypeId", new Long(jobtypeId)));
      }
      if ((!StringUtils.isNullOrEmpty(budgetcodeid)) && (!budgetcodeid.equals("0")))
      {
        outer.createAlias("budgetcode", "budgetcode");
        outer.add(Restrictions.eq("budgetcode.budgetId", new Long(budgetcodeid)));
      }
      if (!StringUtils.isNullOrEmpty(statuscri)) {
        outer.add(Restrictions.eq("status", statuscri));
      }
      if (!StringUtils.isNullOrEmpty(statecri)) {
        outer.add(Restrictions.eq("state", statecri));
      }
      outer.setProjection(Projections.rowCount());
      totaluser = ((Integer)outer.list().get(0)).intValue();
      System.out.println("totaluser" + totaluser);
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfSearchJobRequisitionPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totaluser;
  }
  
  public int getCountOfSearchJobRequisitionPagination()
  {
    logger.info("Inside getCountOfSearchJobRequisitionPagination method");
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      Criteria outer = null;
      
      outer = session.createCriteria(JobRequisition.class).add(Restrictions.ne("status", "Deleted"));
      

      outer.setProjection(Projections.rowCount());
      totaluser = ((Integer)outer.list().get(0)).intValue();
      System.out.println("totaluser" + totaluser);
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfSearchJobRequisitionPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totaluser;
  }
  
  public int getCountOfAllJobRequistionTemplates(String templatename, String orgId, String departmentId, String jobgradeId, String jobtypeId, String statuscri)
  {
    logger.info("Inside getCountOfAllJobRequistionTemplates method");
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      Criteria outer = session.createCriteria(JobRequisionTemplate.class).add(Restrictions.ne("status", "Deleted"));
      if (!StringUtils.isNullOrEmpty(templatename)) {
        outer.add(Restrictions.like("templateName", "%" + templatename + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(orgId)) && (!orgId.equals("0")))
      {
        outer.createAlias("organization", "organization");
        outer.add(Restrictions.eq("organization.orgId", new Long(orgId)));
      }
      if ((!StringUtils.isNullOrEmpty(departmentId)) && (!departmentId.equals("0")))
      {
        outer.createAlias("department", "department");
        outer.add(Restrictions.eq("department.departmentId", new Long(departmentId)));
      }
      if ((!StringUtils.isNullOrEmpty(jobgradeId)) && (!jobgradeId.equals("0")))
      {
        outer.createAlias("jobgrade", "jobgrade");
        outer.add(Restrictions.eq("jobgrade.jobgradeId", new Long(jobgradeId)));
      }
      if ((!StringUtils.isNullOrEmpty(jobtypeId)) && (!jobtypeId.equals("0")))
      {
        outer.createAlias("jobtype", "jobtype");
        outer.add(Restrictions.eq("jobtype.jobTypeId", new Long(jobtypeId)));
      }
      if (!StringUtils.isNullOrEmpty(statuscri)) {
        outer.add(Restrictions.eq("status", statuscri));
      }
      outer.setProjection(Projections.rowCount());
      totaluser = ((Integer)outer.list().get(0)).intValue();
      System.out.println("totaluser" + totaluser);
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfAllJobRequistionTemplates()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totaluser;
  }
  
  public List getMyJobRequistionsByOrgByDeptForPagination(String orgid, String hiringmgrid, String deptid, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getMyJobRequistionsByOrgByDeptForPagination method");
    List tmplList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      logger.info("orgid" + orgid);
      logger.info("hiringmgrid" + hiringmgrid);
      logger.info("deptid" + deptid);
      Criteria outer = session.createCriteria(JobRequisition.class).add(Restrictions.ne("status", "Deleted")).add(Restrictions.eq("state", "Active"));
      if ((!StringUtils.isNullOrEmpty(orgid)) && (!orgid.equals("0"))) {
        outer.createAlias("organization", "organization").add(Restrictions.eq("organization.orgId", new Long(orgid)));
      }
      if ((!StringUtils.isNullOrEmpty(hiringmgrid)) && (!hiringmgrid.equals("0"))) {
        outer.createAlias("hiringmgr", "hiringmgr").add(Restrictions.eq("hiringmgr.userId", new Long(hiringmgrid)));
      }
      if ((!StringUtils.isNullOrEmpty(deptid)) && (!deptid.equals("0"))) {
        outer.createAlias("department", "department").add(Restrictions.eq("department.departmentId", new Long(deptid)));
      }
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      tmplList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getMyJobRequistionsByOrgByDeptForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return tmplList;
  }
  
  public List getAllJobRequistionsByOrgByDeptForPagination(String orgid, String deptid, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getAllJobRequistionsByOrgByDeptForPagination method");
    List tmplList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      logger.info("orgid" + orgid);
      logger.info("deptid" + deptid);
      Criteria outer = session.createCriteria(JobRequisition.class).add(Restrictions.ne("status", "Deleted"));
      if ((!StringUtils.isNullOrEmpty(orgid)) && (!orgid.equals("0"))) {
        outer.createAlias("organization", "organization").add(Restrictions.eq("organization.orgId", new Long(orgid)));
      }
      if ((!StringUtils.isNullOrEmpty(deptid)) && (!deptid.equals("0"))) {
        outer.createAlias("department", "department").add(Restrictions.eq("department.departmentId", new Long(deptid)));
      }
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      tmplList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllJobRequistionsByOrgByDeptForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return tmplList;
  }
  
  public List getAllJobRequistions()
  {
    logger.info("Inside getAllJobRequistions method");
    List jobList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      jobList = session.createCriteria(JobRequisition.class).add(Restrictions.ne("status", "D")).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllJobRequistionsByOrgByDeptForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return jobList;
  }
  
  public List getNonIndexedJobRequistions()
  {
    logger.info("Inside getNonIndexedJobRequistions method");
    List jobList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      jobList = session.createCriteria(JobRequisition.class).add(Restrictions.ne("status", "D")).add(Restrictions.eq("isindexSearchApplied", Integer.valueOf(0))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getNonIndexedJobRequistions()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return jobList;
  }
  
  public List getNonFilteredJobRequistions()
  {
    logger.info("Inside getNonFilteredJobRequistions method");
    List jobList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      jobList = session.createCriteria(JobRequisition.class).add(Restrictions.ne("status", "D")).add(Restrictions.eq("isFilterApplied", Integer.valueOf(0))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getNonFilteredJobRequistions()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return jobList;
  }
  
  public List getAllRequisitionComments(String uuid)
  {
    logger.info("Inside getAllRequisitionComments method");
    List cList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      cList = session.createCriteria(RequisitionComments.class).add(Restrictions.eq("uuid", uuid)).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllRequisitionComments()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return cList;
  }
  
  public void saveRequisitionComment(RequisitionComments comment)
  {
    logger.info("Inside saveRequisitionComment method");
    User user = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      session.save(comment);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveRequisitionComment()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public int getCountOfMyJobRequistionsByOrgByDept(String orgid, String hiringmgrid, String deptid)
  {
    logger.info("Inside getCountOfMyJobRequistionsByOrgByDept method");
    int total = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      Criteria outer = session.createCriteria(JobRequisition.class).add(Restrictions.ne("status", "Deleted")).add(Restrictions.eq("state", "Active"));
      if ((!StringUtils.isNullOrEmpty(orgid)) && (!orgid.equals("0"))) {
        outer.createAlias("organization", "organization").add(Restrictions.eq("organization.orgId", new Long(orgid)));
      }
      if ((!StringUtils.isNullOrEmpty(hiringmgrid)) && (!hiringmgrid.equals("0"))) {
        outer.createAlias("hiringmgr", "hiringmgr").add(Restrictions.eq("hiringmgr.userId", new Long(hiringmgrid)));
      }
      if ((!StringUtils.isNullOrEmpty(deptid)) && (!deptid.equals("0"))) {
        outer.createAlias("department", "department").add(Restrictions.eq("department.departmentId", new Long(deptid)));
      }
      outer.setProjection(Projections.rowCount());
      total = ((Integer)outer.list().get(0)).intValue();
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfMyJobRequistionsByOrgByDept()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return total;
  }
  
  public int getCountOfAllJobRequistionsByOrgByDept(String orgid, String deptid)
  {
    logger.info("Inside getCountOfAllJobRequistionsByOrgByDept method");
    int total = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      Criteria outer = session.createCriteria(JobRequisition.class).add(Restrictions.ne("status", "Deleted"));
      if ((!StringUtils.isNullOrEmpty(orgid)) && (!orgid.equals("0"))) {
        outer.createAlias("organization", "organization").add(Restrictions.eq("organization.orgId", new Long(orgid)));
      }
      if ((!StringUtils.isNullOrEmpty(deptid)) && (!deptid.equals("0"))) {
        outer.createAlias("department", "department").add(Restrictions.eq("department.departmentId", new Long(deptid)));
      }
      outer.setProjection(Projections.rowCount());
      total = ((Integer)outer.list().get(0)).intValue();
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfAllJobRequistionsByOrgByDept()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return total;
  }
  
  public int getCountOfJobRequistionsByOrgByRecruiterIdByDept(String orgid, String recruiterId, String deptid)
  {
    logger.info("Inside getCountOfJobRequistionsByOrgByRecruiterIdByDept method");
    int total = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String sql = " select distinct j.jb_req_id  from job_requisition j , user_group ug ,user_group_users ugs  where  ((j.recruiter_Id = :currentuserid and j.is_recruiter_group <> 'Y')  or (j.recruiter_Id = ug.user_group_id and  j.is_recruiter_group='Y' and ugs.user_id =:currentuserid) )   and j.state = 'Active' and j.status <> 'Deleted'";
      if ((!StringUtils.isNullOrEmpty(orgid)) && (!orgid.equals("0"))) {
        sql = sql + " and j.org_id = :orgId";
      }
      if ((!StringUtils.isNullOrEmpty(deptid)) && (!deptid.equals("0"))) {
        sql = sql + " and j.department_Id = :departmentId";
      }
      Query query = session.createSQLQuery(sql);
      query.setLong("currentuserid", new Long(recruiterId).longValue());
      if ((!StringUtils.isNullOrEmpty(orgid)) && (!orgid.equals("0"))) {
        query.setLong("orgId", new Long(orgid).longValue());
      }
      if ((!StringUtils.isNullOrEmpty(deptid)) && (!deptid.equals("0"))) {
        query.setLong("departmentId", new Long(deptid).longValue());
      }
      List rlist = query.list();
      
      total = rlist.size();
      logger.info("total" + total);
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfJobRequistionsByOrgByRecruiterIdByDept()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return total;
  }
  
  public List getJobRequistionsByOrgByRecruiterByDeptForPagination(String orgid, String recruiterId, String deptid, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getJobRequistionsByOrgByRecruiterByDeptForPagination method");
    List tmplList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      logger.info("orgid" + orgid);
      logger.info("recruiterId" + recruiterId);
      logger.info("deptid" + deptid);
      String hqlquery = " select distinct j from JobRequisition j , UserGroup ug where  ((j.recruiterId = :currentuserid and j.isgrouprecruiter <> 'Y') or (j.recruiterId = ug.usergrpId and  j.isgrouprecruiter='Y' and ug.users.userId =:currentuserid)) ";
      



      hqlquery = hqlquery + " and j.state = 'Active' and j.status <> 'Deleted' ";
      if ((!StringUtils.isNullOrEmpty(orgid)) && (!orgid.equals("0"))) {
        hqlquery = hqlquery + " and j.organization.orgId = :orgId";
      }
      if ((!StringUtils.isNullOrEmpty(deptid)) && (!deptid.equals("0"))) {
        hqlquery = hqlquery + " and j.department.departmentId = :departmentId";
      }
      if (dir_str.equalsIgnoreCase("asc")) {
        hqlquery = hqlquery + " order by j." + sort_str + " asc";
      } else {
        hqlquery = hqlquery + " order by j." + sort_str + " desc";
      }
      Query query = session.createQuery(hqlquery);
      
      query.setLong("currentuserid", new Long(recruiterId).longValue());
      if ((!StringUtils.isNullOrEmpty(orgid)) && (!orgid.equals("0"))) {
        query.setLong("orgId", new Long(orgid).longValue());
      }
      if ((!StringUtils.isNullOrEmpty(deptid)) && (!deptid.equals("0"))) {
        query.setLong("departmentId", new Long(deptid).longValue());
      }
      query.setFirstResult(startIndex);
      query.setMaxResults(pageSize);
      tmplList = query.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getJobRequistionsByOrgByRecruiterByDeptForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return tmplList;
  }
  
  public int getCountsearchJobs()
  {
    logger.info("Inside getCountsearchJobs method");
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = session.createCriteria(JobRequisition.class).add(Restrictions.eq("state", "Active")).add(Restrictions.eq("status", "Open"));
      
      outer.setProjection(Projections.rowCount());
      totaluser = ((Integer)outer.list().get(0)).intValue();
      System.out.println("totaluser" + totaluser);
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountsearchJobs()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totaluser;
  }
  
  public int getCountsearchJobs(User user, String jobTilte, String postdate, String cri, String locationId, String orgId, String departmentId, String jobgradeId, String jobtypeId, String jobreqcode)
  {
    logger.info("Inside getCountsearchJobs method");
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = session.createCriteria(JobRequisition.class).add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key()))).add(Restrictions.eq("state", "Active")).add(Restrictions.eq("status", "Open")).add(Restrictions.eq("publishToEmpRef", "Y"));
      if ((!StringUtils.isNullOrEmpty(jobTilte)) && (!jobTilte.equals("null"))) {
        outer.add(Restrictions.like("jobTitle", "%" + jobTilte + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(locationId)) && (!locationId.equals("0")) && (!locationId.equals("null"))) {
        outer.createAlias("location", "location").add(Restrictions.eq("location.locationId", new Long(locationId)));
      }
      if ((!StringUtils.isNullOrEmpty(jobreqcode)) && (!jobreqcode.equals("null"))) {
        outer.add(Restrictions.eq("jobreqcode", jobreqcode));
      }
      if ((!StringUtils.isNullOrEmpty(orgId)) && (!orgId.equals("0")) && (!orgId.equals("null")))
      {
        outer.createAlias("organization", "organization");
        outer.add(Restrictions.eq("organization.orgId", new Long(orgId)));
      }
      if ((!StringUtils.isNullOrEmpty(departmentId)) && (!departmentId.equals("0")) && (!departmentId.equals("null")))
      {
        outer.createAlias("department", "department");
        outer.add(Restrictions.eq("department.departmentId", new Long(departmentId)));
      }
      if ((!StringUtils.isNullOrEmpty(jobgradeId)) && (!jobgradeId.equals("0")) && (!jobgradeId.equals("null")))
      {
        outer.createAlias("jobgrade", "jobgrade");
        outer.add(Restrictions.eq("jobgrade.jobgradeId", new Long(jobgradeId)));
      }
      if ((!StringUtils.isNullOrEmpty(jobtypeId)) && (!jobtypeId.equals("0")) && (!jobtypeId.equals("null")))
      {
        outer.createAlias("jobtype", "jobtype");
        outer.add(Restrictions.eq("jobtype.jobTypeId", new Long(jobtypeId)));
      }
      logger.info("appdate" + postdate);
      if ((!StringUtils.isNullOrEmpty(postdate)) && (!postdate.equals("null")))
      {
        String datepattern = Constant.getValue("defaultdateformat");
        if (user != null) {
          datepattern = DateUtil.getDatePatternFormat(user.getLocale());
        }
        DateFormat format = new SimpleDateFormat(datepattern + " hh:mm:ss");
        
        Date aDate = format.parse(postdate + " 00:00:00");
        
        Date bDate = format.parse(postdate + " 23:59:00");
        if (cri.equals("on")) {
          outer.add(Expression.between("publishedDate", new Date(aDate.getTime()), new Date(bDate.getTime())));
        }
        if (cri.equals("after")) {
          outer.add(Expression.gt("publishedDate", new Date(aDate.getTime())));
        }
        if (cri.equals("before")) {
          outer.add(Expression.lt("publishedDate", new Date(aDate.getTime())));
        }
      }
      outer.setProjection(Projections.rowCount());
      totaluser = ((Integer)outer.list().get(0)).intValue();
      System.out.println("totaluser" + totaluser);
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountsearchJobs()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totaluser;
  }
  
  public int getCountsearchJobsByVendor(User user, String jobTilte, String postdate, String cri, String locationId, String orgId, String departmentId, String jobgradeId, String jobtypeId, String jobreqcode)
  {
    logger.info("Inside getCountsearchJobsByVendor method");
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = session.createCriteria(JobRequisition.class).add(Restrictions.eq("state", "Active")).add(Restrictions.eq("status", "Open"));
      if ((!StringUtils.isNullOrEmpty(jobTilte)) && (!jobTilte.equals("null"))) {
        outer.add(Restrictions.like("jobTitle", "%" + jobTilte + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(locationId)) && (!locationId.equals("0")) && (!locationId.equals("null"))) {
        outer.createAlias("location", "location").add(Restrictions.eq("location.locationId", new Long(locationId)));
      }
      if ((!StringUtils.isNullOrEmpty(jobreqcode)) && (!jobreqcode.equals("null"))) {
        outer.add(Restrictions.eq("jobreqcode", jobreqcode));
      }
      if ((!StringUtils.isNullOrEmpty(orgId)) && (!orgId.equals("0")) && (!orgId.equals("null")))
      {
        outer.createAlias("organization", "organization");
        outer.add(Restrictions.eq("organization.orgId", new Long(orgId)));
      }
      if ((!StringUtils.isNullOrEmpty(departmentId)) && (!departmentId.equals("0")) && (!departmentId.equals("null")))
      {
        outer.createAlias("department", "department");
        outer.add(Restrictions.eq("department.departmentId", new Long(departmentId)));
      }
      if ((!StringUtils.isNullOrEmpty(jobgradeId)) && (!jobgradeId.equals("0")) && (!jobgradeId.equals("null")))
      {
        outer.createAlias("jobgrade", "jobgrade");
        outer.add(Restrictions.eq("jobgrade.jobgradeId", new Long(jobgradeId)));
      }
      if ((!StringUtils.isNullOrEmpty(jobtypeId)) && (!jobtypeId.equals("0")) && (!jobtypeId.equals("null")))
      {
        outer.createAlias("jobtype", "jobtype");
        outer.add(Restrictions.eq("jobtype.jobTypeId", new Long(jobtypeId)));
      }
      logger.info("appdate" + postdate);
      if ((!StringUtils.isNullOrEmpty(postdate)) && (!postdate.equals("null")))
      {
        String datepattern = Constant.getValue("defaultdateformat");
        if (user != null) {
          datepattern = DateUtil.getDatePatternFormat(user.getLocale());
        }
        DateFormat format = new SimpleDateFormat(datepattern + " hh:mm:ss");
        
        Date aDate = format.parse(postdate + " 00:00:00");
        
        Date bDate = format.parse(postdate + " 23:59:00");
        if (cri.equals("on")) {
          outer.add(Expression.between("publishedDate", new Date(aDate.getTime()), new Date(bDate.getTime())));
        }
        if (cri.equals("after")) {
          outer.add(Expression.gt("publishedDate", new Date(aDate.getTime())));
        }
        if (cri.equals("before")) {
          outer.add(Expression.lt("publishedDate", new Date(aDate.getTime())));
        }
      }
      outer.setProjection(Projections.rowCount());
      totaluser = ((Integer)outer.list().get(0)).intValue();
      System.out.println("totaluser" + totaluser);
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountsearchJobsByVendor()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totaluser;
  }
  
  public int getCountsearchJobs(User user, String jobTilte, String postdate, String cri, String locationId)
  {
    logger.info("Inside getCountsearchJobs method");
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = session.createCriteria(JobRequisition.class).add(Restrictions.eq("state", "Active")).add(Restrictions.eq("status", "Open"));
      if (!StringUtils.isNullOrEmpty(jobTilte)) {
        outer.add(Restrictions.like("jobTitle", "%" + jobTilte + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(locationId)) && (!locationId.equals("0"))) {
        outer.createAlias("location", "location").add(Restrictions.eq("location.locationId", new Long(locationId)));
      }
      logger.info("appdate" + postdate);
      if ((!StringUtils.isNullOrEmpty(postdate)) && (!postdate.equals("null")))
      {
        String datepattern = Constant.getValue("defaultdateformat");
        if (user != null) {
          datepattern = DateUtil.getDatePatternFormat(user.getLocale());
        }
        DateFormat format = new SimpleDateFormat(datepattern + " hh:mm:ss");
        
        Date aDate = format.parse(postdate + " 00:00:00");
        
        Date bDate = format.parse(postdate + " 23:59:00");
        if (cri.equals("on")) {
          outer.add(Expression.between("publishedDate", new Date(aDate.getTime()), new Date(bDate.getTime())));
        }
        if (cri.equals("after")) {
          outer.add(Expression.gt("publishedDate", new Date(aDate.getTime())));
        }
        if (cri.equals("before")) {
          outer.add(Expression.lt("publishedDate", new Date(aDate.getTime())));
        }
      }
      outer.setProjection(Projections.rowCount());
      totaluser = ((Integer)outer.list().get(0)).intValue();
      System.out.println("totaluser" + totaluser);
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountsearchJobs()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totaluser;
  }
  
  public List searchJobsForPagination(User user, String jobTilte, String postdate, String cri, String locationId, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside searchJobsForPagination method");
    List applicantList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = session.createCriteria(JobRequisition.class).add(Restrictions.eq("state", "Active")).add(Restrictions.eq("status", "Open"));
      if (!StringUtils.isNullOrEmpty(jobTilte)) {
        outer.add(Restrictions.like("jobTitle", "%" + jobTilte + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(locationId)) && (!locationId.equals("0"))) {
        outer.createAlias("location", "location").add(Restrictions.eq("location.locationId", new Long(locationId)));
      }
      logger.info("appdate" + postdate);
      if ((!StringUtils.isNullOrEmpty(postdate)) && (!postdate.equals("null")))
      {
        String datepattern = Constant.getValue("defaultdateformat");
        if (user != null) {
          datepattern = DateUtil.getDatePatternFormat(user.getLocale());
        }
        DateFormat format = new SimpleDateFormat(datepattern + " hh:mm:ss");
        
        Date aDate = format.parse(postdate + " 00:00:00");
        
        Date bDate = format.parse(postdate + " 23:59:00");
        if (cri.equals("on")) {
          outer.add(Expression.between("publishedDate", new Date(aDate.getTime()), new Date(bDate.getTime())));
        }
        if (cri.equals("after")) {
          outer.add(Expression.gt("publishedDate", new Date(aDate.getTime())));
        }
        if (cri.equals("before")) {
          outer.add(Expression.lt("publishedDate", new Date(aDate.getTime())));
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
      logger.error("Exception on searchJobsForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return applicantList;
  }
  
  public List newJobsForPagination(int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside newJobsForPagination method");
    List applicantList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = session.createCriteria(JobRequisition.class).add(Restrictions.eq("state", "Active")).add(Restrictions.eq("status", "Open"));
      
      String offset = Constant.getValue("new.jobs.offset");
      int offsetn = new Integer(offset).intValue();
      offsetn = 0 - offsetn;
      Calendar cal = Calendar.getInstance();
      cal.add(2, offsetn);
      

      outer.add(Expression.gt("publishedDate", cal.getTime()));
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
      logger.error("Exception on searchJobsForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return applicantList;
  }
  
  public Map newJobsForPaginationByVendor(User user, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside newJobsForPaginationByVendor method");
    List jobList = new ArrayList();
    List newjoblist = new ArrayList();
    Map searchJobMap = new HashMap();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = session.createCriteria(JobRequisition.class).add(Restrictions.eq("state", "Active")).add(Restrictions.eq("status", "Open"));
      
      String offset = Constant.getValue("new.jobs.offset");
      int offsetn = new Integer(offset).intValue();
      offsetn = 0 - offsetn;
      Calendar cal = Calendar.getInstance();
      cal.add(2, offsetn);
      

      outer.add(Expression.gt("publishedDate", cal.getTime()));
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      jobList = outer.list();
      for (int i = 0; i < jobList.size(); i++)
      {
        JobRequisition jbr = (JobRequisition)jobList.get(i);
        Criteria pubcri = session.createCriteria(PublishToVendor.class).add(Restrictions.eq("jobreqId", Long.valueOf(jbr.getJobreqId()))).add(Restrictions.eq("userId", Long.valueOf(user.getUserId())));
        List publist = pubcri.list();
        if ((publist != null) && (publist.size() > 0)) {
          newjoblist.add(jbr);
        }
      }
      searchJobMap.put(Common.JOBS_LIST, newjoblist);
      
      String sqlcount = " select  count(distinct jr.jb_req_id) as count from job_requisition jr, publish_to_vendor p  where jr.jb_req_id=p.jb_req_id  and p.user_id = :user_id and jr.status='Open' and jr.state='Active' ";
      


      String datepatternuser = DateUtil.getDatePatternFormat(user.getLocale());
      String offesetdate = DateUtil.convertDateToStringDate(cal.getTime(), datepatternuser);
      

      sqlcount = sqlcount + " and DATE_FORMAT( jr.published_date, '%M %e, %Y')  > " + "'" + offesetdate + "'";
      
      Query querycount = session.createSQLQuery(sqlcount);
      querycount.setLong("user_id", user.getUserId());
      Object obj = querycount.uniqueResult();
      logger.info("obj" + obj);
      BigInteger count = (BigInteger)obj;
      Integer totalsize = new Integer(count.intValue());
      
      searchJobMap.put(Common.JOBS_COUNT, totalsize);
    }
    catch (Exception e)
    {
      logger.error("Exception on newJobsForPaginationByVendor()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return searchJobMap;
  }
  
  public int getCountNewJobs()
  {
    logger.info("Inside getCountNewJobs method");
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = session.createCriteria(JobRequisition.class).add(Restrictions.eq("state", "Active")).add(Restrictions.eq("status", "Open"));
      
      String offset = Constant.getValue("new.jobs.offset");
      int offsetn = new Integer(offset).intValue();
      offsetn = 0 - offsetn;
      Calendar cal = Calendar.getInstance();
      cal.add(2, offsetn);
      
      outer.add(Expression.gt("publishedDate", cal.getTime()));
      
      outer.setProjection(Projections.rowCount());
      totaluser = ((Integer)outer.list().get(0)).intValue();
      System.out.println("totaluser" + totaluser);
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountNewJobs()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totaluser;
  }
  
  public List searchJobsForPagination(User user, String jobTilte, String postdate, String cri, String locationId, String orgId, String departmentId, String jobgradeId, String jobtypeId, String jobreqcode, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside searchJobsForPagination method" + user.getSuper_user_key());
    List applicantList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = session.createCriteria(JobRequisition.class).add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key()))).add(Restrictions.eq("state", "Active")).add(Restrictions.eq("status", "Open")).add(Restrictions.eq("publishToEmpRef", "Y"));
      if ((!StringUtils.isNullOrEmpty(jobTilte)) && (!jobTilte.equals("null"))) {
        outer.add(Restrictions.like("jobTitle", "%" + jobTilte + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(locationId)) && (!locationId.equals("0")) && (!locationId.equals("null"))) {
        outer.createAlias("location", "location").add(Restrictions.eq("location.locationId", new Long(locationId)));
      }
      if ((!StringUtils.isNullOrEmpty(jobreqcode)) && (!jobreqcode.equals("null"))) {
        outer.add(Restrictions.eq("jobreqcode", jobreqcode));
      }
      if ((!StringUtils.isNullOrEmpty(orgId)) && (!orgId.equals("0")) && (!orgId.equals("null")))
      {
        outer.createAlias("organization", "organization");
        outer.add(Restrictions.eq("organization.orgId", new Long(orgId)));
      }
      if ((!StringUtils.isNullOrEmpty(departmentId)) && (!departmentId.equals("0")) && (!departmentId.equals("null")))
      {
        outer.createAlias("department", "department");
        outer.add(Restrictions.eq("department.departmentId", new Long(departmentId)));
      }
      if ((!StringUtils.isNullOrEmpty(jobgradeId)) && (!jobgradeId.equals("0")) && (!jobgradeId.equals("null")))
      {
        outer.createAlias("jobgrade", "jobgrade");
        outer.add(Restrictions.eq("jobgrade.jobgradeId", new Long(jobgradeId)));
      }
      if ((!StringUtils.isNullOrEmpty(jobtypeId)) && (!jobtypeId.equals("0")) && (!jobtypeId.equals("null")))
      {
        outer.createAlias("jobtype", "jobtype");
        outer.add(Restrictions.eq("jobtype.jobTypeId", new Long(jobtypeId)));
      }
      logger.info("appdate" + postdate);
      if ((!StringUtils.isNullOrEmpty(postdate)) && (!postdate.equals("null")))
      {
        String datepattern = Constant.getValue("defaultdateformat");
        if (user != null) {
          datepattern = DateUtil.getDatePatternFormat(user.getLocale());
        }
        DateFormat format = new SimpleDateFormat(datepattern + " hh:mm:ss");
        
        Date aDate = format.parse(postdate + " 00:00:00");
        
        Date bDate = format.parse(postdate + " 23:59:00");
        if (cri.equals("on")) {
          outer.add(Expression.between("publishedDate", new Date(aDate.getTime()), new Date(bDate.getTime())));
        }
        if (cri.equals("after")) {
          outer.add(Expression.gt("publishedDate", new Date(aDate.getTime())));
        }
        if (cri.equals("before")) {
          outer.add(Expression.lt("publishedDate", new Date(aDate.getTime())));
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
      logger.error("Exception on searchJobsForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return applicantList;
  }
  
  public boolean isRedemptionRuleAppliedToAgency(long jobReqId, String criteria)
  {
    logger.info("Inside isRedemptionRuleAppliedToAgency method");
    org.hibernate.Session session = null;
    boolean isapplied = false;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      String sql = "select jar.* from job_req_agency_referral jar,refferal_scheme rs,refferal_redemption_rule r where  jar.job_req_id= :job_req_id  and jar.reffscheme_id= rs.reffscheme_id  and rs.scheme_type='V'  and rs.rule_id=r.rule_id  and r.criteria=:criteria ";
      





      Query querycount = session.createSQLQuery(sql);
      querycount.setLong("job_req_id", jobReqId);
      querycount.setString("criteria", criteria);
      List lst = querycount.list();
      if ((lst != null) && (lst.size() > 0)) {
        isapplied = true;
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on isRedemptionRuleAppliedToAgency()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return isapplied;
  }
  
  public boolean isEEOInfoIncluded(long jobReqId)
  {
    logger.info("Inside isEEOInfoIncluded method");
    org.hibernate.Session session = null;
    boolean isapplied = false;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      String sql = "select eeoInfoIncluded from job_requisition where jb_req_id= :jb_req_id and eeoInfoIncluded=:eeoInfoIncluded";
      

      Query querycount = session.createSQLQuery(sql);
      querycount.setLong("jb_req_id", jobReqId);
      querycount.setString("eeoInfoIncluded", "Y");
      List lst = querycount.list();
      if ((lst != null) && (lst.size() > 0)) {
        isapplied = true;
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on isEEOInfoIncluded()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return isapplied;
  }
  
  public boolean isRedemptionRuleAppliedToEmployeeRef(long jobReqId, String criteria)
  {
    logger.info("Inside isRedemptionRuleAppliedToEmployeeRef method");
    org.hibernate.Session session = null;
    boolean isapplied = false;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      String sql = "select jar.* from job_req_employee_referral jar,refferal_scheme rs,refferal_redemption_rule r where  jar.job_req_id= :job_req_id  and jar.reffscheme_id= rs.reffscheme_id  and rs.scheme_type='E'  and rs.rule_id=r.rule_id  and r.criteria=:criteria ";
      





      Query querycount = session.createSQLQuery(sql);
      querycount.setLong("job_req_id", jobReqId);
      querycount.setString("criteria", criteria);
      List lst = querycount.list();
      if ((lst != null) && (lst.size() > 0)) {
        isapplied = true;
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on isRedemptionRuleAppliedToEmployeeRef()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return isapplied;
  }
  
  public Map searchJobsForPaginationByVendor(User user, String jobTilte, String postdate, String cri, String locationId, String orgId, String departmentId, String jobgradeId, String jobtypeId, String jobreqcode, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside searchJobsForPaginationByVendor method");
    List jobList = new ArrayList();
    List newjoblist = new ArrayList();
    Map searchJobMap = new HashMap();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = session.createCriteria(JobRequisition.class).add(Restrictions.eq("state", "Active")).add(Restrictions.eq("status", "Open"));
      if ((!StringUtils.isNullOrEmpty(jobTilte)) && (!jobTilte.equals("null"))) {
        outer.add(Restrictions.like("jobTitle", "%" + jobTilte + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(locationId)) && (!locationId.equals("0")) && (!locationId.equals("null"))) {
        outer.createAlias("location", "location").add(Restrictions.eq("location.locationId", new Long(locationId)));
      }
      if ((!StringUtils.isNullOrEmpty(jobreqcode)) && (!jobreqcode.equals("null"))) {
        outer.add(Restrictions.eq("jobreqcode", jobreqcode));
      }
      if ((!StringUtils.isNullOrEmpty(orgId)) && (!orgId.equals("0")) && (!orgId.equals("null")))
      {
        outer.createAlias("organization", "organization");
        outer.add(Restrictions.eq("organization.orgId", new Long(orgId)));
      }
      if ((!StringUtils.isNullOrEmpty(departmentId)) && (!departmentId.equals("0")) && (!departmentId.equals("null")))
      {
        outer.createAlias("department", "department");
        outer.add(Restrictions.eq("department.departmentId", new Long(departmentId)));
      }
      if ((!StringUtils.isNullOrEmpty(jobgradeId)) && (!jobgradeId.equals("0")) && (!jobgradeId.equals("null")))
      {
        outer.createAlias("jobgrade", "jobgrade");
        outer.add(Restrictions.eq("jobgrade.jobgradeId", new Long(jobgradeId)));
      }
      if ((!StringUtils.isNullOrEmpty(jobtypeId)) && (!jobtypeId.equals("0")) && (!jobtypeId.equals("null")))
      {
        outer.createAlias("jobtype", "jobtype");
        outer.add(Restrictions.eq("jobtype.jobTypeId", new Long(jobtypeId)));
      }
      logger.info("appdate" + postdate);
      if ((!StringUtils.isNullOrEmpty(postdate)) && (!postdate.equals("null")))
      {
        String datepattern = Constant.getValue("defaultdateformat");
        if (user != null) {
          datepattern = DateUtil.getDatePatternFormat(user.getLocale());
        }
        DateFormat format = new SimpleDateFormat(datepattern + " hh:mm:ss");
        
        Date aDate = format.parse(postdate + " 00:00:00");
        
        Date bDate = format.parse(postdate + " 23:59:00");
        if (cri.equals("on")) {
          outer.add(Expression.between("publishedDate", new Date(aDate.getTime()), new Date(bDate.getTime())));
        }
        if (cri.equals("after")) {
          outer.add(Expression.gt("publishedDate", new Date(aDate.getTime())));
        }
        if (cri.equals("before")) {
          outer.add(Expression.lt("publishedDate", new Date(aDate.getTime())));
        }
      }
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      jobList = outer.list();
      for (int i = 0; i < jobList.size(); i++)
      {
        JobRequisition jbr = (JobRequisition)jobList.get(i);
        Criteria pubcri = session.createCriteria(PublishToVendor.class).add(Restrictions.eq("jobreqId", Long.valueOf(jbr.getJobreqId()))).add(Restrictions.eq("userId", Long.valueOf(user.getUserId())));
        List publist = pubcri.list();
        if ((publist != null) && (publist.size() > 0)) {
          newjoblist.add(jbr);
        }
      }
      searchJobMap.put(Common.JOBS_LIST, newjoblist);
      
      String sqlcount = " select  count(distinct jr.jb_req_id) as count from job_requisition jr, publish_to_vendor p  where jr.jb_req_id=p.jb_req_id  and p.user_id = :user_id and jr.status='Open' and jr.state='Active' ";
      if ((!StringUtils.isNullOrEmpty(jobTilte)) && (!jobTilte.equals("null"))) {
        sqlcount = sqlcount + " and jr.jb_req_title like '%" + jobTilte + "%' ";
      }
      if ((!StringUtils.isNullOrEmpty(locationId)) && (!locationId.equals("0")) && (!locationId.equals("null"))) {
        sqlcount = sqlcount + " and jr.location_id = " + locationId;
      }
      if ((!StringUtils.isNullOrEmpty(jobreqcode)) && (!jobreqcode.equals("null"))) {
        sqlcount = sqlcount + " and jr.jb_req_code = " + "'" + jobreqcode + "'";
      }
      if ((!StringUtils.isNullOrEmpty(orgId)) && (!orgId.equals("0")) && (!orgId.equals("null"))) {
        sqlcount = sqlcount + " and jr.org_id = " + orgId;
      }
      if ((!StringUtils.isNullOrEmpty(departmentId)) && (!departmentId.equals("0")) && (!departmentId.equals("null"))) {
        sqlcount = sqlcount + " and jr.department_id = " + departmentId;
      }
      if ((!StringUtils.isNullOrEmpty(jobgradeId)) && (!jobgradeId.equals("0")) && (!jobgradeId.equals("null"))) {
        sqlcount = sqlcount + " and jr.job_grade_id = " + jobgradeId;
      }
      if ((!StringUtils.isNullOrEmpty(jobtypeId)) && (!jobtypeId.equals("0")) && (!jobtypeId.equals("null"))) {
        sqlcount = sqlcount + " and jr.job_type_id = " + jobtypeId;
      }
      String datepatternuser = DateUtil.getDatePatternFormat(user.getLocale());
      if ((!StringUtils.isNullOrEmpty(postdate)) && (!postdate.equals("null")))
      {
        Date fromdt1 = DateUtil.convertStringDateToDate(postdate, datepatternuser);
        
        postdate = DateUtil.convertDateToStringDate(fromdt1, datepatternuser);
        if (cri.equals("on")) {
          sqlcount = sqlcount + " and DATE_FORMAT( jr.published_date, '%M %e, %Y')  = " + "'" + postdate + "'";
        }
        if (cri.equals("after")) {
          sqlcount = sqlcount + " and DATE_FORMAT( jr.published_date, '%M %e, %Y')  > " + "'" + postdate + "'";
        }
        if (cri.equals("before")) {
          sqlcount = sqlcount + " and DATE_FORMAT( jr.published_date, '%M %e, %Y')  < " + "'" + postdate + "'";
        }
      }
      Query querycount = session.createSQLQuery(sqlcount);
      querycount.setLong("user_id", user.getUserId());
      Object obj = querycount.uniqueResult();
      logger.info("obj" + obj);
      BigInteger count = (BigInteger)obj;
      Integer totalsize = new Integer(count.intValue());
      
      searchJobMap.put(Common.JOBS_COUNT, totalsize);
    }
    catch (Exception e)
    {
      logger.error("Exception on searchJobsForPaginationByVendor()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return searchJobMap;
  }
  
  public Map myJobRequistionByVendor(User agency, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside myJobRequistionByVendor method");
    List jobList = new ArrayList();
    List newjoblist = new ArrayList();
    Map searchJobMap = new HashMap();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String sql = " select distinct jr.jb_req_id ,jr.jb_req_title,(select o.org_name from organization o where jr.org_id=o.org_id) org_name,  (select l.location_name from locations l where jr.location_id =l.location_id) location_name, jr.recruiter_name ,jr.published_date, jr.target_finish_date,jr.uuid, (select a.reffscheme_name from refferal_scheme a where a.reffscheme_id=jr.agency_reffscheme_id) agency_scheme_name,  jr.state from job_requisition jr, publish_to_vendor p  where jr.jb_req_id=p.jb_req_id  and p.user_id = :user_id and jr.status='Open' and jr.state='Active' ";
      







      Map m = new HashMap();
      m.put("jobreqId", "jb_req_id");
      m.put("jobTitle", "jb_req_title");
      m.put("recruiterName", "recruiter_name");
      m.put("publishedDate", "published_date");
      m.put("targetfinishdate", "target_finish_date");
      if (dir_str.equalsIgnoreCase("asc")) {
        sql = sql + "order by " + m.get(sort_str) + " ASC";
      } else {
        sql = sql + "order by " + m.get(sort_str) + " DESC";
      }
      logger.info("sql" + sql);
      Query query = session.createSQLQuery(sql);
      query.setLong("user_id", agency.getUserId());
      query.setFirstResult(startIndex);
      query.setMaxResults(pageSize);
      
      jobList = query.list();
      for (int i = 0; i < jobList.size(); i++)
      {
        Object[] obj = (Object[])jobList.get(i);
        JobRequisition jb = new JobRequisition();
        
        BigInteger appId = (BigInteger)obj[0];
        long reqid = appId.longValue();
        jb.setJobreqId(reqid);
        jb.setJobTitle((String)obj[1]);
        jb.organizationValue = ((String)obj[2]);
        jb.locationValue = ((String)obj[3]);
        jb.setRecruiterName((String)obj[4]);
        jb.setPublishedDate((Date)obj[5]);
        jb.setTargetfinishdate((Date)obj[6]);
        jb.setUuid((String)obj[7]);
        jb.setAgencyRefferalSchemeName((String)obj[8]);
        jb.setState((String)obj[9]);
        

        newjoblist.add(jb);
      }
      searchJobMap.put(Common.JOBS_LIST, newjoblist);
      
      String sqlcount = " select  count(distinct jr.jb_req_id) as count from job_requisition jr, publish_to_vendor p  where jr.jb_req_id=p.jb_req_id  and p.user_id = :user_id and jr.status='Open' and jr.state='Active' ";
      




      Query querycount = session.createSQLQuery(sqlcount);
      querycount.setLong("user_id", agency.getUserId());
      Object obj = querycount.uniqueResult();
      logger.info("obj" + obj);
      BigInteger count = (BigInteger)obj;
      Integer totalsize = new Integer(count.intValue());
      
      searchJobMap.put(Common.JOBS_COUNT, totalsize);
    }
    catch (Exception e)
    {
      logger.error("Exception on myJobRequistionByVendor()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return searchJobMap;
  }
  
  public List getMyJobRequistionsForPagination(String currentuserid, String currentusername, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getMyJobRequistionsForPagination method");
    List tmplList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      Criteria outer = session.createCriteria(JobRequisition.class).add(Restrictions.ne("status", "Deleted")).createAlias("hiringmgr", "hiringmgr");
      
      Criterion hiringmgr = Restrictions.eq("hiringmgr.userId", new Long(currentuserid));
      Criterion createdby = Restrictions.eq("createdBy", currentusername);
      Criterion recruiter = Restrictions.eq("recruiterId", new Long(currentuserid));
      
      Disjunction disjunction = Restrictions.disjunction();
      disjunction.add(hiringmgr);
      disjunction.add(createdby);
      disjunction.add(recruiter);
      outer.add(disjunction);
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      tmplList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getMyJobRequistionsForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return tmplList;
  }
  
  public int getCountOfMyJobRequistions(String currentuserid, String currentusername, RequisitionSearchCriteriaMultiple searchCriteria)
  {
    logger.info("Inside getCountOfMyJobRequistions method");
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      Criteria outer = null;
      if (searchCriteria.getStatusList().size() > 0) {
        outer = session.createCriteria(JobRequisition.class).add(Expression.in("status", searchCriteria.getStatusList()));
      } else {
        outer = session.createCriteria(JobRequisition.class).add(Restrictions.ne("status", "Deleted"));
      }
      outer.createAlias("hiringmgr", "hiringmgr");
      
      Criterion hiringmgr = Restrictions.eq("hiringmgr.userId", new Long(currentuserid));
      Criterion createdby = Restrictions.eq("createdBy", currentusername);
      Disjunction disjunction = Restrictions.disjunction();
      disjunction.add(hiringmgr);
      disjunction.add(createdby);
      outer.add(disjunction);
      if ((!StringUtils.isNullOrEmpty(searchCriteria.getJobreqName())) && (!searchCriteria.getJobreqName().equals("null"))) {
        outer.add(Restrictions.like("jobreqName", "%" + searchCriteria.getJobreqName() + "%"));
      }
      if (searchCriteria.getJobreqId() != 0L) {
        outer.add(Restrictions.eq("jobreqId", new Long(searchCriteria.getJobreqId())));
      }
      if ((!StringUtils.isNullOrEmpty(searchCriteria.getJobreqcode())) && (!searchCriteria.getJobreqcode().equals("null"))) {
        outer.add(Restrictions.eq("jobreqcode", searchCriteria.getJobreqcode()));
      }
      if (searchCriteria.getOrgIdList().size() > 0) {
        outer.add(Expression.in("organization.orgId", searchCriteria.getOrgIdList()));
      }
      if (searchCriteria.getDepartmentIdsList().size() > 0) {
        outer.add(Expression.in("department.departmentId", searchCriteria.getDepartmentIdsList()));
      }
      if (searchCriteria.getJobgradeIdList().size() > 0) {
        outer.add(Expression.in("jobgrade.jobgradeId", searchCriteria.getJobgradeIdList()));
      }
      if (searchCriteria.getJobtypeIdList().size() > 0) {
        outer.add(Expression.in("jobtype.jobTypeId", searchCriteria.getJobtypeIdList()));
      }
      if (searchCriteria.getBudgetcodeIdList().size() > 0) {
        outer.add(Expression.in("budgetcode.budgetId", searchCriteria.getBudgetcodeIdList()));
      }
      if (searchCriteria.getStateList().size() > 0) {
        outer.add(Expression.in("state", searchCriteria.getStateList()));
      }
      outer.setProjection(Projections.rowCount());
      totaluser = ((Integer)outer.list().get(0)).intValue();
      logger.info("total requistions " + totaluser);
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfMyJobRequistions()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totaluser;
  }
  
  public List getMyJobRequistionsForPagination(String currentuserid, String currentusername, RequisitionSearchCriteriaMultiple searchCriteria, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getJobRequistionsForPagination method");
    List tmplList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = null;
      if (searchCriteria.getStatusList().size() > 0) {
        outer = session.createCriteria(JobRequisition.class).add(Expression.in("status", searchCriteria.getStatusList()));
      } else {
        outer = session.createCriteria(JobRequisition.class).add(Restrictions.ne("status", "Deleted"));
      }
      outer.createAlias("hiringmgr", "hiringmgr");
      outer.add(Restrictions.eq("hiringmgr.userId", new Long(currentuserid)));
      if ((!StringUtils.isNullOrEmpty(searchCriteria.getJobreqName())) && (!searchCriteria.getJobreqName().equals("null"))) {
        outer.add(Restrictions.like("jobreqName", "%" + searchCriteria.getJobreqName() + "%"));
      }
      if (searchCriteria.getJobreqId() != 0L) {
        outer.add(Restrictions.eq("jobreqId", new Long(searchCriteria.getJobreqId())));
      }
      if ((!StringUtils.isNullOrEmpty(searchCriteria.getJobreqcode())) && (!searchCriteria.getJobreqcode().equals("null"))) {
        outer.add(Restrictions.eq("jobreqcode", searchCriteria.getJobreqcode()));
      }
      if (searchCriteria.getOrgIdList().size() > 0) {
        outer.add(Expression.in("organization.orgId", searchCriteria.getOrgIdList()));
      }
      if (searchCriteria.getDepartmentIdsList().size() > 0) {
        outer.add(Expression.in("department.departmentId", searchCriteria.getDepartmentIdsList()));
      }
      if (searchCriteria.getJobgradeIdList().size() > 0) {
        outer.add(Expression.in("jobgrade.jobgradeId", searchCriteria.getJobgradeIdList()));
      }
      if (searchCriteria.getJobtypeIdList().size() > 0) {
        outer.add(Expression.in("jobtype.jobTypeId", searchCriteria.getJobtypeIdList()));
      }
      if (searchCriteria.getBudgetcodeIdList().size() > 0) {
        outer.add(Expression.in("budgetcode.budgetId", searchCriteria.getBudgetcodeIdList()));
      }
      if (searchCriteria.getStateList().size() > 0) {
        outer.add(Expression.in("state", searchCriteria.getStateList()));
      }
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      tmplList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getJobRequistionsForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return tmplList;
  }
  
  public List getMyJobRequistionsForPaginationRecruiter(String currentuserid, RequisitionSearchCriteriaMultiple searchCriteria, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getMyJobRequistionsForPaginationRecruiter method");
    List tmplList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String hqlquery = " select distinct j from JobRequisition j , UserGroup ug where  ((j.recruiterId = :currentuserid and j.isgrouprecruiter <> 'Y') or (j.recruiterId = ug.usergrpId and  j.isgrouprecruiter='Y' and ug.users.userId =:currentuserid)) ";
      if (searchCriteria.getStatusList().size() > 0) {
        hqlquery = hqlquery + " and j.status IN (:statusList)";
      } else {
        hqlquery = hqlquery + " and j.status <> 'Deleted'";
      }
      if ((!StringUtils.isNullOrEmpty(searchCriteria.getJobreqName())) && (!searchCriteria.getJobreqName().equals("null"))) {
        hqlquery = hqlquery + " and j.jobreqName like :jobreqname";
      }
      if (searchCriteria.getJobreqId() != 0L) {
        hqlquery = hqlquery + " and j.jobreqId = :jobreqid";
      }
      if ((!StringUtils.isNullOrEmpty(searchCriteria.getJobreqcode())) && (!searchCriteria.getJobreqcode().equals("null")) && (!searchCriteria.getJobreqcode().equals("0"))) {
        hqlquery = hqlquery + " and j.jobreqcode = :jobreqcode";
      }
      if (searchCriteria.getOrgIdList().size() > 0) {
        hqlquery = hqlquery + " and j.organization.orgId IN (:orgList)";
      }
      if (searchCriteria.getDepartmentIdsList().size() > 0) {
        hqlquery = hqlquery + " and j.department.departmentId IN (:departmentIdsList)";
      }
      if (searchCriteria.getJobgradeIdList().size() > 0) {
        hqlquery = hqlquery + " and j.jobgrade.jobgradeId IN (:jobgradeIdList)";
      }
      if (searchCriteria.getJobtypeIdList().size() > 0) {
        hqlquery = hqlquery + " and j.jobtype.jobTypeId IN (:jobtypeIdList)";
      }
      if (searchCriteria.getBudgetcodeIdList().size() > 0) {
        hqlquery = hqlquery + " and j.budgetcode.budgetId IN (:budgetcodeIdList)";
      }
      if (searchCriteria.getStatusList().size() > 0) {
        hqlquery = hqlquery + " and j.status IN (:statusList)";
      }
      if (searchCriteria.getStateList().size() > 0) {
        hqlquery = hqlquery + " and j.state IN (:stateList)";
      }
      if (dir_str.equalsIgnoreCase("asc")) {
        hqlquery = hqlquery + " order by j." + sort_str + " asc";
      } else {
        hqlquery = hqlquery + " order by j." + sort_str + " desc";
      }
      Query query = session.createQuery(hqlquery);
      
      query.setLong("currentuserid", new Long(currentuserid).longValue());
      if (searchCriteria.getStatusList().size() > 0) {
        query.setParameterList("statusList", searchCriteria.getStatusList());
      }
      if ((!StringUtils.isNullOrEmpty(searchCriteria.getJobreqName())) && (!searchCriteria.getJobreqName().equals("null"))) {
        query.setString("jobreqname", '%' + searchCriteria.getJobreqName() + '%');
      }
      if (searchCriteria.getJobreqId() != 0L) {
        query.setLong("jobreqid", new Long(searchCriteria.getJobreqId()).longValue());
      }
      if ((!StringUtils.isNullOrEmpty(searchCriteria.getJobreqcode())) && (!searchCriteria.getJobreqcode().equals("null")) && (!searchCriteria.getJobreqcode().equals("0"))) {
        query.setString("jobreqcode", searchCriteria.getJobreqcode());
      }
      if (searchCriteria.getOrgIdList().size() > 0) {
        query.setParameterList("orgList", searchCriteria.getOrgIdList());
      }
      if (searchCriteria.getDepartmentIdsList().size() > 0) {
        query.setParameterList("departmentIdsList", searchCriteria.getDepartmentIdsList());
      }
      if (searchCriteria.getJobgradeIdList().size() > 0) {
        query.setParameterList("jobgradeIdList", searchCriteria.getJobgradeIdList());
      }
      if (searchCriteria.getJobtypeIdList().size() > 0) {
        query.setParameterList("jobtypeIdList", searchCriteria.getJobtypeIdList());
      }
      if (searchCriteria.getBudgetcodeIdList().size() > 0) {
        query.setParameterList("budgetcodeIdList", searchCriteria.getBudgetcodeIdList());
      }
      if (searchCriteria.getStatusList().size() > 0) {
        query.setParameterList("statusList", searchCriteria.getStatusList());
      }
      if (searchCriteria.getStateList().size() > 0) {
        query.setParameterList("stateList", searchCriteria.getStateList());
      }
      query.setFirstResult(startIndex);
      query.setMaxResults(pageSize);
      
      logger.info(query.getQueryString());
      
      tmplList = query.list();
      
      logger.info(Integer.valueOf(tmplList.size()));
    }
    catch (Exception e)
    {
      logger.error("Exception on getMyJobRequistionsForPaginationRecruiter()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return tmplList;
  }
  
  public int getCountOfMyJobRequistionsRecruiter(String currentuserid, RequisitionSearchCriteriaMultiple searchCriteria)
  {
    logger.info("Inside getCountOfMyJobRequistionsRecruiter method");
    int totaluser = 0;
    List tmplList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      String hqlquery = " select distinct j.jobreqId from JobRequisition j , UserGroup ug where  ((j.recruiterId = :currentuserid and j.isgrouprecruiter <> 'Y') or (j.recruiterId = ug.usergrpId and  j.isgrouprecruiter='Y' and ug.users.userId =:currentuserid)) ";
      if (searchCriteria.getStatusList().size() > 0) {
        hqlquery = hqlquery + " and j.status IN (:statusList)";
      } else {
        hqlquery = hqlquery + " and j.status <> 'Deleted'";
      }
      if ((!StringUtils.isNullOrEmpty(searchCriteria.getJobreqName())) && (!searchCriteria.getJobreqName().equals("null"))) {
        hqlquery = hqlquery + " and j.jobreqName like :jobreqname";
      }
      if (searchCriteria.getJobreqId() != 0L) {
        hqlquery = hqlquery + " and j.jobreqId = :jobreqid";
      }
      if ((!StringUtils.isNullOrEmpty(searchCriteria.getJobreqcode())) && (!searchCriteria.getJobreqcode().equals("null")) && (!searchCriteria.getJobreqcode().equals("0"))) {
        hqlquery = hqlquery + " and j.jobreqcode = :jobreqcode";
      }
      if (searchCriteria.getOrgIdList().size() > 0) {
        hqlquery = hqlquery + " and j.organization.orgId IN (:orgList)";
      }
      if (searchCriteria.getDepartmentIdsList().size() > 0) {
        hqlquery = hqlquery + " and j.department.departmentId IN (:departmentIdsList)";
      }
      if (searchCriteria.getJobgradeIdList().size() > 0) {
        hqlquery = hqlquery + " and j.jobgrade.jobgradeId IN (:jobgradeIdList)";
      }
      if (searchCriteria.getJobtypeIdList().size() > 0) {
        hqlquery = hqlquery + " and j.jobtype.jobTypeId IN (:jobtypeIdList)";
      }
      if (searchCriteria.getBudgetcodeIdList().size() > 0) {
        hqlquery = hqlquery + " and j.budgetcode.budgetId IN (:budgetcodeIdList)";
      }
      if (searchCriteria.getStatusList().size() > 0) {
        hqlquery = hqlquery + " and j.status IN (:statusList)";
      }
      if (searchCriteria.getStateList().size() > 0) {
        hqlquery = hqlquery + " and j.state IN (:stateList)";
      }
      Query query = session.createQuery(hqlquery);
      
      query.setLong("currentuserid", new Long(currentuserid).longValue());
      if (searchCriteria.getStatusList().size() > 0) {
        query.setParameterList("statusList", searchCriteria.getStatusList());
      }
      if ((!StringUtils.isNullOrEmpty(searchCriteria.getJobreqName())) && (!searchCriteria.getJobreqName().equals("null"))) {
        query.setString("jobreqname", '%' + searchCriteria.getJobreqName() + '%');
      }
      if (searchCriteria.getJobreqId() != 0L) {
        query.setLong("jobreqid", new Long(searchCriteria.getJobreqId()).longValue());
      }
      if ((!StringUtils.isNullOrEmpty(searchCriteria.getJobreqcode())) && (!searchCriteria.getJobreqcode().equals("null")) && (!searchCriteria.getJobreqcode().equals("0"))) {
        query.setString("jobreqcode", searchCriteria.getJobreqcode());
      }
      if (searchCriteria.getOrgIdList().size() > 0) {
        query.setParameterList("orgList", searchCriteria.getOrgIdList());
      }
      if (searchCriteria.getDepartmentIdsList().size() > 0) {
        query.setParameterList("departmentIdsList", searchCriteria.getDepartmentIdsList());
      }
      if (searchCriteria.getJobgradeIdList().size() > 0) {
        query.setParameterList("jobgradeIdList", searchCriteria.getJobgradeIdList());
      }
      if (searchCriteria.getJobtypeIdList().size() > 0) {
        query.setParameterList("jobtypeIdList", searchCriteria.getJobtypeIdList());
      }
      if (searchCriteria.getBudgetcodeIdList().size() > 0) {
        query.setParameterList("budgetcodeIdList", searchCriteria.getBudgetcodeIdList());
      }
      if (searchCriteria.getStatusList().size() > 0) {
        query.setParameterList("statusList", searchCriteria.getStatusList());
      }
      if (searchCriteria.getStateList().size() > 0) {
        query.setParameterList("stateList", searchCriteria.getStateList());
      }
      logger.info(query.getQueryString());
      
      tmplList = query.list();
      
      totaluser = tmplList.size();
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfMyJobRequistionsRecruiter()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totaluser;
  }
  
  public List getMyActiveJobRequistionsForPagination(String currentuserid, String currentusername, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getActiveJobRequistionsForPagination method");
    List tmplList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      Criteria outer = session.createCriteria(JobRequisition.class).add(Restrictions.eq("status", "Open")).createAlias("hiringmgr", "hiringmgr").add(Restrictions.eq("hiringmgr.userId", new Long(currentuserid)));
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      tmplList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getJobRequistionsForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return tmplList;
  }
  
  public Map<String, Integer> getCountMapOfRequitionOpenWithStatus(long userId, long super_user_key, String type)
  {
    logger.info("Inside getCountMapOfRequitionOpenWithStatus method");
    int totalcount = 0;
    org.hibernate.Session session = null;
    
    Map<String, Integer> countMap = new HashMap();
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      if (type.equalsIgnoreCase("admin"))
      {
        String sql = "select state , count(*) as countvalue  from job_requisition where status = 'Open' and super_user_key=:super_user_key group by  state";
        Query query = session.createSQLQuery(sql);
        query.setLong("super_user_key", super_user_key);
        List reqList = query.list();
        

        int rejectcount = 0;
        int onholdcount = 0;
        for (int i = 0; i < reqList.size(); i++)
        {
          Object[] obj = (Object[])reqList.get(i);
          String intstate = (String)obj[0];
          BigInteger total = (BigInteger)obj[1];
          int totalc = total.intValue();
          
          countMap.put(intstate, Integer.valueOf(totalc));
        }
      }
      if (type.equalsIgnoreCase("hiringmgr"))
      {
        String sql = "select state , count(*) as countvalue  from job_requisition where status = 'Open' and  hiring_mgr_id=:hiring_mgr_id group by  state";
        Query query = session.createSQLQuery(sql);
        query.setLong("hiring_mgr_id", userId);
        List reqList = query.list();
        

        int rejectcount = 0;
        int onholdcount = 0;
        for (int i = 0; i < reqList.size(); i++)
        {
          Object[] obj = (Object[])reqList.get(i);
          String intstate = (String)obj[0];
          BigInteger total = (BigInteger)obj[1];
          int totalc = total.intValue();
          
          countMap.put(intstate, Integer.valueOf(totalc));
        }
      }
      if (type.equalsIgnoreCase("recruiter"))
      {
        String sql = "select distinct state  from job_requisition j ,user_group_users ug  where j.status = 'Open' and ( (j.is_recruiter_group <> 'Y' and j.recruiter_id=:recruiter_id) or (j.is_recruiter_group = 'Y' and ug.user_group_id=j.recruiter_id and ug.user_id=:recruiter_id)) ";
        


        Query query = session.createSQLQuery(sql);
        query.setLong("recruiter_id", userId);
        List stateList = query.list();
        
        sql = "select distinct state , j.jb_req_id  from job_requisition j ,user_group_users ug  where j.status = 'Open' and ( (j.is_recruiter_group <> 'Y' and j.recruiter_id=:recruiter_id) or (j.is_recruiter_group = 'Y' and ug.user_group_id=j.recruiter_id and ug.user_id=:recruiter_id)) ";
        


        query = session.createSQLQuery(sql);
        query.setLong("recruiter_id", userId);
        List reqList = query.list();
        for (int j = 0; j < stateList.size(); j++)
        {
          String state = (String)stateList.get(j);
          logger.info("state" + state);
          int count = 0;
          for (int i = 0; i < reqList.size(); i++)
          {
            Object[] obj = (Object[])reqList.get(i);
            String intstate = (String)obj[0];
            if ((intstate != null) && (intstate.equals(state))) {
              count++;
            }
          }
          countMap.put(state, Integer.valueOf(count));
        }
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountMapOfRequitionOpenWithStatus()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return countMap;
  }
  
  public List activeJobRequistionsForPagination(long superUserKey, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside activeJobRequistionsForPagination method");
    List tmplList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      




      Criteria outer = session.createCriteria(JobRequisition.class).add(Restrictions.eq("status", "Open")).add(Restrictions.eq("super_user_key", Long.valueOf(superUserKey)));
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      tmplList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on activeJobRequistionsForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return tmplList;
  }
  
  public List getActiveAndFilledJobRequistions(long superUserKey, long currentuserid)
  {
    logger.info("Inside getActiveAndFilledJobRequistions method");
    List reqListf = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      



      String sql = "select jb_req_id,jb_req_name,uuid,requisition_number, (select count(*) from job_applications where jb_req_id = j.jb_req_id) as countapp, j.number_of_opening,j.number_of_remaining from job_requisition j where j.status = :status and  j.state= :state and j.super_user_key =:super_user_key and j.number_of_remaining=0";
      Query query = session.createSQLQuery(sql);
      query.setString("status", "Open");
      query.setString("state", "Active");
      query.setLong("super_user_key", superUserKey);
      
      List reqList = query.list();
      if ((reqList != null) && (reqList.size() > 0)) {
        for (int i = 0; i < reqList.size(); i++)
        {
          JobRequisition jobreq = new JobRequisition();
          Object[] obj = (Object[])reqList.get(i);
          BigInteger id = (BigInteger)obj[0];
          long idv = id.longValue();
          
          jobreq.setJobreqId(idv);
          jobreq.setJobreqName((String)obj[1]);
          jobreq.setUuid((String)obj[2]);
          BigInteger number = (BigInteger)obj[3];
          long numberv = number.longValue();
          jobreq.setRequisition_number(numberv);
          
          BigInteger totalcount = (BigInteger)obj[4];
          int totalcountv = totalcount.intValue();
          jobreq.setTotalAppcount(totalcountv);
          
          Short noofopen = (Short)obj[5];
          int noofopenv = noofopen.intValue();
          jobreq.setNumberOfOpening(noofopenv);
          

          Short noofremain = (Short)obj[6];
          int noofremainv = noofremain.intValue();
          jobreq.setNumberOfOpeningRemain(noofremainv);
          
          reqListf.add(jobreq);
        }
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getActiveAndFilledJobRequistions()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return reqListf;
  }
  
  public List getMyActiveAndFilledJobRequistionsHiringMgr(long currentuserid)
  {
    logger.info("Inside getMyActiveAndFilledJobRequistionsHiringMgr method");
    List reqListf = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      



      String sql = "select jb_req_id,jb_req_name,uuid,requisition_number, (select count(*) from job_applications where jb_req_id = j.jb_req_id) as countapp, j.number_of_opening,j.number_of_remaining from job_requisition j where j.status = :status and  j.state= :state and j.hiring_mgr_id =:hiring_mgr_id and j.number_of_remaining=0";
      Query query = session.createSQLQuery(sql);
      query.setString("status", "Open");
      query.setString("state", "Active");
      query.setLong("hiring_mgr_id", currentuserid);
      
      List reqList = query.list();
      if ((reqList != null) && (reqList.size() > 0)) {
        for (int i = 0; i < reqList.size(); i++)
        {
          JobRequisition jobreq = new JobRequisition();
          Object[] obj = (Object[])reqList.get(i);
          BigInteger id = (BigInteger)obj[0];
          long idv = id.longValue();
          
          jobreq.setJobreqId(idv);
          jobreq.setJobreqName((String)obj[1]);
          jobreq.setUuid((String)obj[2]);
          BigInteger number = (BigInteger)obj[3];
          long numberv = number.longValue();
          jobreq.setRequisition_number(numberv);
          
          BigInteger totalcount = (BigInteger)obj[4];
          int totalcountv = totalcount.intValue();
          jobreq.setTotalAppcount(totalcountv);
          
          Short noofopen = (Short)obj[5];
          int noofopenv = noofopen.intValue();
          jobreq.setNumberOfOpening(noofopenv);
          

          Short noofremain = (Short)obj[6];
          int noofremainv = noofremain.intValue();
          jobreq.setNumberOfOpeningRemain(noofremainv);
          
          reqListf.add(jobreq);
        }
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getMyActiveAndFilledJobRequistionsHiringMgr()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return reqListf;
  }
  
  public List getMyActiveAndNotFilledWithTargetDateSlipingJobRequistionsHiringMgr(long currentuserid)
  {
    logger.info("Inside getMyActiveAndNotFilledWithTargetDateSlipingJobRequistionsHiringMgr method");
    List reqListf = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      



      String sql = "select jb_req_id,jb_req_name,uuid,requisition_number, (select count(*) from job_applications where jb_req_id = j.jb_req_id) as countapp, j.number_of_opening,j.number_of_remaining,j.target_finish_date from job_requisition j where j.status = :status and  j.state= :state and j.hiring_mgr_id =:hiring_mgr_id and j.number_of_remaining <> 0 and (target_finish_date <= CURDATE() or  target_finish_date is null)";
      Query query = session.createSQLQuery(sql);
      query.setString("status", "Open");
      query.setString("state", "Active");
      query.setLong("hiring_mgr_id", currentuserid);
      
      List reqList = query.list();
      if ((reqList != null) && (reqList.size() > 0)) {
        for (int i = 0; i < reqList.size(); i++)
        {
          JobRequisition jobreq = new JobRequisition();
          Object[] obj = (Object[])reqList.get(i);
          BigInteger id = (BigInteger)obj[0];
          long idv = id.longValue();
          
          jobreq.setJobreqId(idv);
          jobreq.setJobreqName((String)obj[1]);
          jobreq.setUuid((String)obj[2]);
          BigInteger number = (BigInteger)obj[3];
          long numberv = number.longValue();
          jobreq.setRequisition_number(numberv);
          
          BigInteger totalcount = (BigInteger)obj[4];
          int totalcountv = totalcount.intValue();
          jobreq.setTotalAppcount(totalcountv);
          
          Short noofopen = (Short)obj[5];
          int noofopenv = noofopen.intValue();
          jobreq.setNumberOfOpening(noofopenv);
          

          Short noofremain = (Short)obj[6];
          int noofremainv = noofremain.intValue();
          jobreq.setNumberOfOpeningRemain(noofremainv);
          
          jobreq.setTargetfinishdate((Date)obj[7]);
          
          reqListf.add(jobreq);
        }
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getMyActiveAndNotFilledWithTargetDateSlipingJobRequistionsHiringMgr()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return reqListf;
  }
  
  public List getActiveJobRequistionsAssignedToMeAsRecruiterForPagination(String currentuserid, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getActiveJobRequistionsAssignedToMeAsRecruiterForPagination method" + currentuserid);
    List<JobRequisition> tmplList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      




      String hqlquery = " select distinct j from JobRequisition j , UserGroup ug where  (j.recruiterId = :currentuserid and j.isgrouprecruiter <> 'Y' and j.state = 'Active' and j.status = 'Open')  or ( j.state = 'Active'  and j.status = 'Open' and j.recruiterId = ug.usergrpId and  j.isgrouprecruiter='Y' and ug.users.userId =:currentuserid)";
      if (dir_str.equalsIgnoreCase("asc")) {
        hqlquery = hqlquery + " order by j." + sort_str + " asc";
      } else {
        hqlquery = hqlquery + " order by j." + sort_str + " desc";
      }
      Query query = session.createQuery(hqlquery);
      query.setLong("currentuserid", new Long(currentuserid).longValue());
      



      query.setFirstResult(startIndex);
      query.setMaxResults(pageSize);
      
      logger.info(query.getQueryString());
      
      tmplList = query.list();
      
      logger.info("tmplList" + tmplList.size());
    }
    catch (Exception e)
    {
      logger.error("Exception on getActiveJobRequistionsAssignedToMeAsRecruiterForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return tmplList;
  }
  
  public List getJobRequistionsAssignedToMeAsRecruiterForPagination(String currentuserid, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getJobRequistionsAssignedToMeAsRecruiterForPagination method" + currentuserid);
    List<JobRequisition> tmplList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      




      String hqlquery = " select distinct j from JobRequisition j , UserGroup ug where  (j.recruiterId = :currentuserid and j.isgrouprecruiter <> 'Y'  and j.status = 'Open')  or ( j.status = 'Open' and j.recruiterId = ug.usergrpId and  j.isgrouprecruiter='Y' and ug.users.userId =:currentuserid)";
      if (dir_str.equalsIgnoreCase("asc")) {
        hqlquery = hqlquery + " order by j." + sort_str + " asc";
      } else {
        hqlquery = hqlquery + " order by j." + sort_str + " desc";
      }
      Query query = session.createQuery(hqlquery);
      query.setLong("currentuserid", new Long(currentuserid).longValue());
      



      query.setFirstResult(startIndex);
      query.setMaxResults(pageSize);
      
      logger.info(query.getQueryString());
      
      tmplList = query.list();
      
      logger.info("tmplList" + tmplList.size());
    }
    catch (Exception e)
    {
      logger.error("Exception on getJobRequistionsAssignedToMeAsRecruiterForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return tmplList;
  }
  
  public List getMyActiveAndFilledJobRequistionsRecruiter(long currentuserid)
  {
    logger.info("Inside getMyActiveAndFilledJobRequistionsRecruiter method" + currentuserid);
    List<JobRequisition> tmplList = new ArrayList();
    List reqListf = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
















      String sql = "select distinct jb_req_id,jb_req_name,uuid,requisition_number, (select count(*) from job_applications where jb_req_id = j.jb_req_id) as countapp, j.number_of_opening,j.number_of_remaining from job_requisition j ,user_group_users ug  where j.status = 'Open' and  j.state= 'Active' and  j.number_of_remaining=0  and ( (j.is_recruiter_group <> 'Y' and j.recruiter_id=:recruiter_id) or (j.is_recruiter_group = 'Y' and ug.user_group_id=j.recruiter_id and ug.user_id=:recruiter_id))";
      




      Query query = session.createSQLQuery(sql);
      
      query.setLong("recruiter_id", currentuserid);
      
      List reqList = query.list();
      if ((reqList != null) && (reqList.size() > 0)) {
        for (int i = 0; i < reqList.size(); i++)
        {
          JobRequisition jobreq = new JobRequisition();
          Object[] obj = (Object[])reqList.get(i);
          BigInteger id = (BigInteger)obj[0];
          long idv = id.longValue();
          
          jobreq.setJobreqId(idv);
          jobreq.setJobreqName((String)obj[1]);
          jobreq.setUuid((String)obj[2]);
          BigInteger number = (BigInteger)obj[3];
          long numberv = number.longValue();
          jobreq.setRequisition_number(numberv);
          
          BigInteger totalcount = (BigInteger)obj[4];
          int totalcountv = totalcount.intValue();
          jobreq.setTotalAppcount(totalcountv);
          
          Short noofopen = (Short)obj[5];
          int noofopenv = noofopen.intValue();
          jobreq.setNumberOfOpening(noofopenv);
          

          Short noofremain = (Short)obj[6];
          int noofremainv = noofremain.intValue();
          jobreq.setNumberOfOpeningRemain(noofremainv);
          
          reqListf.add(jobreq);
        }
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getMyActiveAndFilledJobRequistionsRecruiter()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return reqListf;
  }
  
  public List getMyActiveAndNotFilledWithTargetDateSlipingJobRequistionsRecruiter(long currentuserid)
  {
    logger.info("Inside getMyActiveAndNotFilledWithTargetDateSlipingJobRequistionsRecruiter method" + currentuserid);
    List<JobRequisition> tmplList = new ArrayList();
    List reqListf = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
















      String sql = "select distinct jb_req_id,jb_req_name,uuid,requisition_number, (select count(*) from job_applications where jb_req_id = j.jb_req_id) as countapp, j.number_of_opening,j.number_of_remaining,j.target_finish_date from job_requisition j ,user_group_users ug  where j.status = 'Open' and  j.state= 'Active' and j.number_of_remaining <> 0 and (target_finish_date <= CURDATE() or  target_finish_date is null)  and ( (j.is_recruiter_group <> 'Y' and j.recruiter_id=:recruiter_id) or (j.is_recruiter_group = 'Y' and ug.user_group_id=j.recruiter_id and ug.user_id=:recruiter_id))";
      




      Query query = session.createSQLQuery(sql);
      
      query.setLong("recruiter_id", currentuserid);
      
      List reqList = query.list();
      if ((reqList != null) && (reqList.size() > 0)) {
        for (int i = 0; i < reqList.size(); i++)
        {
          JobRequisition jobreq = new JobRequisition();
          Object[] obj = (Object[])reqList.get(i);
          BigInteger id = (BigInteger)obj[0];
          long idv = id.longValue();
          
          jobreq.setJobreqId(idv);
          jobreq.setJobreqName((String)obj[1]);
          jobreq.setUuid((String)obj[2]);
          BigInteger number = (BigInteger)obj[3];
          long numberv = number.longValue();
          jobreq.setRequisition_number(numberv);
          
          BigInteger totalcount = (BigInteger)obj[4];
          int totalcountv = totalcount.intValue();
          jobreq.setTotalAppcount(totalcountv);
          
          Short noofopen = (Short)obj[5];
          int noofopenv = noofopen.intValue();
          jobreq.setNumberOfOpening(noofopenv);
          

          Short noofremain = (Short)obj[6];
          int noofremainv = noofremain.intValue();
          jobreq.setNumberOfOpeningRemain(noofremainv);
          
          jobreq.setTargetfinishdate((Date)obj[7]);
          
          reqListf.add(jobreq);
        }
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getMyActiveAndNotFilledWithTargetDateSlipingJobRequistionsRecruiter()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return reqListf;
  }
  
  public List getMyActiveAndNotFilledWithTargetDateSlipingJobRequistions(long superUserKey, long currentuserid)
  {
    logger.info("Inside getMyActiveAndNotFilledWithTargetDateSlipingJobRequistions method" + currentuserid);
    List<JobRequisition> tmplList = new ArrayList();
    List reqListf = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String sql = "select distinct jb_req_id,jb_req_name,uuid,requisition_number, (select count(*) from job_applications where jb_req_id = j.jb_req_id) as countapp, j.number_of_opening,j.number_of_remaining,j.target_finish_date from job_requisition j   where j.status = 'Open' and  j.state= 'Active' and j.number_of_remaining <> 0 and (target_finish_date <= CURDATE() or  target_finish_date is null)  and j.super_user_key =:super_user_key";
      




      Query query = session.createSQLQuery(sql);
      
      query.setLong("super_user_key", superUserKey);
      
      List reqList = query.list();
      if ((reqList != null) && (reqList.size() > 0)) {
        for (int i = 0; i < reqList.size(); i++)
        {
          JobRequisition jobreq = new JobRequisition();
          Object[] obj = (Object[])reqList.get(i);
          BigInteger id = (BigInteger)obj[0];
          long idv = id.longValue();
          
          jobreq.setJobreqId(idv);
          jobreq.setJobreqName((String)obj[1]);
          jobreq.setUuid((String)obj[2]);
          BigInteger number = (BigInteger)obj[3];
          long numberv = number.longValue();
          jobreq.setRequisition_number(numberv);
          
          BigInteger totalcount = (BigInteger)obj[4];
          int totalcountv = totalcount.intValue();
          jobreq.setTotalAppcount(totalcountv);
          
          Short noofopen = (Short)obj[5];
          int noofopenv = noofopen.intValue();
          jobreq.setNumberOfOpening(noofopenv);
          

          Short noofremain = (Short)obj[6];
          int noofremainv = noofremain.intValue();
          jobreq.setNumberOfOpeningRemain(noofremainv);
          
          jobreq.setTargetfinishdate((Date)obj[7]);
          reqListf.add(jobreq);
        }
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getMyActiveAndNotFilledWithTargetDateSlipingJobRequistions()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return reqListf;
  }
  
  public int getCountOfAllMyActiveJobRequistions(String currentuserid, String currentusername)
  {
    logger.info("Inside getCountOfAllMyActiveJobRequistions method");
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria criteria = session.createCriteria(JobRequisition.class).add(Restrictions.eq("status", "Open")).createAlias("hiringmgr", "hiringmgr").add(Restrictions.eq("hiringmgr.userId", new Long(currentuserid)));
      
      criteria.setProjection(Projections.rowCount());
      totaluser = ((Integer)criteria.list().get(0)).intValue();
      logger.info("total requistions" + totaluser);
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfAllMyActiveJobRequistions()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totaluser;
  }
  
  public int getCountOfAllActiveJobRequistions(long superUserKey)
  {
    logger.info("Inside getCountOfAllActiveJobRequistions method");
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria criteria = session.createCriteria(JobRequisition.class).add(Restrictions.eq("status", "Open")).add(Restrictions.eq("super_user_key", Long.valueOf(superUserKey)));
      
      criteria.setProjection(Projections.rowCount());
      totaluser = ((Integer)criteria.list().get(0)).intValue();
      logger.info("total requistions" + totaluser);
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfAllActiveJobRequistions()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totaluser;
  }
  
  public int getCountOfActiveJobRequistionsAssignedToMeAsRecruiter(String currentuserid)
  {
    logger.info("Inside getCountOfActiveJobRequistionsAssignedToMeAsRecruiter method");
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String hqlquery = " select distinct j.jobreqId from JobRequisition j , UserGroup ug where  (j.recruiterId = :currentuserid and j.isgrouprecruiter <> 'Y' and j.state = 'Active' and j.status = 'Open')  or (j.state = 'Active' and j.status = 'Open' and j.recruiterId = ug.usergrpId and  j.isgrouprecruiter='Y' and ug.users.userId =:currentuserid)";
      










      Query query = session.createQuery(hqlquery);
      query.setLong("currentuserid", new Long(currentuserid).longValue());
      

      totaluser = query.list().size();
      logger.info("total requistions" + totaluser);
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfActiveJobRequistionsAssignedToMeAsRecruiter()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totaluser;
  }
  
  public int getCountOfJobRequistionsAssignedToMeAsRecruiter(String currentuserid)
  {
    logger.info("Inside getCountOfActiveJobRequistionsAssignedToMeAsRecruiter method");
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String hqlquery = " select distinct j.jobreqId from JobRequisition j , UserGroup ug where  (j.recruiterId = :currentuserid and j.isgrouprecruiter <> 'Y'  and j.status = 'Open')  or (j.status = 'Open' and j.recruiterId = ug.usergrpId and  j.isgrouprecruiter='Y' and ug.users.userId =:currentuserid)";
      










      Query query = session.createQuery(hqlquery);
      query.setLong("currentuserid", new Long(currentuserid).longValue());
      

      totaluser = query.list().size();
      logger.info("total requistions" + totaluser);
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfActiveJobRequistionsAssignedToMeAsRecruiter()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totaluser;
  }
  
  public List getMyApprovalJobRequistionsForPagination(String currentownerid, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getMyApprovalJobRequistionsForPagination method");
    List tmplList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      Criteria outer = session.createCriteria(JobRequisition.class).add(Restrictions.ne("status", "Deleted")).add(Restrictions.ne("status", "Closed")).add(Restrictions.eq("currentOwnerId", Long.valueOf(new Long(currentownerid).longValue())));
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      tmplList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getMyApprovalJobRequistionsForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return tmplList;
  }
  
  public int getCountOfAllJobRequistionTemplates()
  {
    logger.info("Inside getCountOfAllJobRequistionTemplates method");
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      Criteria criteria = session.createCriteria(JobRequisionTemplate.class).add(Restrictions.ne("status", "Deleted"));
      criteria.setProjection(Projections.rowCount());
      totaluser = ((Integer)criteria.list().get(0)).intValue();
      System.out.println("totaluser" + totaluser);
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfAllJobRequistionTemplates()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totaluser;
  }
  
  public int getCountOfMyJobRequistions(String currentuserid, String currentusername)
  {
    logger.info("Inside getCountOfMyJobRequistions method");
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      Criteria criteria = session.createCriteria(JobRequisition.class).add(Restrictions.ne("status", "Deleted")).createAlias("hiringmgr", "hiringmgr");
      
      Criterion hiringmgr = Restrictions.eq("hiringmgr.userId", new Long(currentuserid));
      Criterion createdby = Restrictions.eq("createdBy", currentusername);
      Criterion recruiter = Restrictions.eq("recruiterId", new Long(currentuserid));
      
      Disjunction disjunction = Restrictions.disjunction();
      disjunction.add(hiringmgr);
      disjunction.add(createdby);
      disjunction.add(recruiter);
      criteria.add(disjunction);
      criteria.setProjection(Projections.rowCount());
      totaluser = ((Integer)criteria.list().get(0)).intValue();
      logger.info("total requistions created" + totaluser);
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfMyJobRequistions()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totaluser;
  }
  
  public int getCountOfAllMyApprovalJobRequistions(String currentownerid)
  {
    logger.info("Inside getCountOfAllMyApprovalJobRequistions method");
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      Criteria criteria = session.createCriteria(JobRequisition.class).add(Restrictions.ne("status", "Deleted")).add(Restrictions.ne("status", "Closed")).add(Restrictions.eq("currentOwnerId", Long.valueOf(new Long(currentownerid).longValue())));
      criteria.setProjection(Projections.rowCount());
      totaluser = ((Integer)criteria.list().get(0)).intValue();
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfAllMyApprovalJobRequistions()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totaluser;
  }
  
  public JobRequisionTemplate saveJobRequisitionTemplate(JobRequisionTemplate jbt)
  {
    logger.info("Inside saveJobRequisitionTemplate method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.save(jbt);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveJobRequisitionTemplate()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return jbt;
  }
  
  public JobRequisition saveJobRequistion(JobRequisition jb)
  {
    logger.info("**** Inside saveJobRequistion method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.save(jb);
    }
    catch (Exception e)
    {
      logger.error("**** Exception on saveJobRequistion()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return jb;
  }
  
  public JobRequisionTemplate updateJobRequisitionTemplate(JobRequisionTemplate jbt)
  {
    logger.info("Inside updateJobRequisitionTemplate method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.update(jbt);
    }
    catch (Exception e)
    {
      logger.error("Exception on updateJobRequisitionTemplate()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return jbt;
  }
  
  public JobRequisition updateJobRequistion(JobRequisition jb)
  {
    logger.info("Inside updateJobRequistion method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.update(jb);
    }
    catch (Exception e)
    {
      logger.error("Exception on updateJobRequistion()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return jb;
  }
  
  public void deleteCompetencies(long id, String type)
  {
    logger.info("Inside deleteCompetencies method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      String hql = "delete from JobTemplateCompetency where jbTmplId = " + id + " and type=" + "'" + type + "'";
      Query query = session.createQuery(hql);
      int row = query.executeUpdate();
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteCompetencies()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void deleteCompetency(long id)
  {
    logger.info("Inside deleteCompetency method");
    JobTemplateCompetency edu = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      edu = (JobTemplateCompetency)session.createCriteria(JobTemplateCompetency.class).add(Restrictions.eq("jbTmplcompId", Long.valueOf(id))).uniqueResult();
      session.delete(edu);
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteCompetency()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void deleteAccomplishment(long id)
  {
    logger.info("Inside deleteAccomplishment method");
    JobTemplateAccomplishment edu = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      edu = (JobTemplateAccomplishment)session.createCriteria(JobTemplateAccomplishment.class).add(Restrictions.eq("jbTmplAccId", Long.valueOf(id))).uniqueResult();
      session.delete(edu);
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteAccomplishment()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void deleteApprovers(long id)
  {
    logger.info("Inside deleteApprovers method");
    JobTemplateApprovers edu = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      edu = (JobTemplateApprovers)session.createCriteria(JobTemplateApprovers.class).add(Restrictions.eq("jbTmplApproverId", Long.valueOf(id))).uniqueResult();
      session.delete(edu);
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteApprovers()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void deleteAccomplishments(long id, String type)
  {
    logger.info("Inside deleteAccomplishments method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      String hql = "delete from JobTemplateAccomplishment where jbTmplId = " + id + " and type=" + "'" + type + "'";
      Query query = session.createQuery(hql);
      int row = query.executeUpdate();
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteAccomplishments()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void deleteApprovers(long id, String type)
  {
    logger.info("Inside deleteApprovers method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      String hql = "delete from JobTemplateApprovers where jbTmplId = " + id + " and type=" + "'" + type + "'";
      Query query = session.createQuery(hql);
      int row = query.executeUpdate();
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteApprovers()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void saveJobReqCompetency(List jbcompetecnyList, String type, long id)
  {
    logger.info("Inside saveJobReqCompetency method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      for (int i = 0; i < jbcompetecnyList.size(); i++)
      {
        JobTemplateCompetency jbc = (JobTemplateCompetency)jbcompetecnyList.get(i);
        jbc.setType(type);
        jbc.setJbTmplId(id);
        session.save(jbc);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on saveJobReqCompetency()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void saveJobReqCompetency(JobTemplateCompetency jbc)
  {
    logger.info("Inside saveJobReqCompetency method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.save(jbc);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveJobReqCompetency()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void saveJobReqAccomplieshment(JobTemplateAccomplishment jbc)
  {
    logger.info("Inside saveJobReqAccomplieshment method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.save(jbc);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveJobReqAccomplieshment()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void saveJobReqApprover(JobTemplateApprovers jbc)
  {
    logger.info("Inside saveJobReqApprover method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.save(jbc);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveJobReqApprover()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public int getMaxLevelForApprover(long id, String type)
  {
    logger.info("Inside saveJobReqApprover method");
    int level = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      String hql = "select max(levelorder)from JobTemplateApprovers a where a.jbTmplId= :id and a.type= :type";
      Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
      query.setLong("id", id);
      query.setString("type", type);
      
      List lst = query.list();
      logger.info("lst.size()" + lst.size());
      if ((lst != null) && (lst.size() > 0))
      {
        Integer l = (Integer)lst.get(0);
        if (l != null) {
          level = l.intValue();
        }
      }
      logger.info("level" + level);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveJobReqApprover()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return level;
  }
  
  public void saveJobReqAccomplishments(List jbaccomplistmentList, String type, long id)
  {
    logger.info("Inside saveJobReqAccomplishments method");
    logger.info(" and the id is : " + id);
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      for (int i = 0; i < jbaccomplistmentList.size(); i++)
      {
        JobTemplateAccomplishment jbc1 = (JobTemplateAccomplishment)jbaccomplistmentList.get(i);
        jbc1.setType(type);
        jbc1.setJbTmplId(id);
        session.save(jbc1);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on saveJobReqAccomplishments()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void saveApproversList(List approverList, String type, long id)
  {
    logger.info("Inside saveApproversList method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      for (int i = 0; i < approverList.size(); i++)
      {
        JobTemplateApprovers jbapp = (JobTemplateApprovers)approverList.get(i);
        jbapp.setType(type);
        jbapp.setJbTmplId(id);
        session.save(jbapp);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on saveApproversList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void saveAttachmentList(List attachmentList, String type, long id)
  {
    logger.info("Inside saveAttachmentList method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      for (int i = 0; i < attachmentList.size(); i++)
      {
        RequistionAttachments jbattach = (RequistionAttachments)attachmentList.get(i);
        jbattach.setType(type);
        jbattach.setReqId(id);
        session.save(jbattach);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on saveAttachmentList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public JobRequisionTemplate getJobRequisionTemplate(long id)
  {
    logger.info("Inside getJobRequisionTemplate method");
    JobRequisionTemplate jobreqTmpl = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      jobreqTmpl = (JobRequisionTemplate)session.createCriteria(JobRequisionTemplate.class).add(Restrictions.eq("templateId", new Long(id))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getJobRequisionTemplate()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return jobreqTmpl;
  }
  
  public JobRequisition getJobRequision(String id)
  {
    logger.info("Inside getJobRequistion method");
    JobRequisition jobreq = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      jobreq = (JobRequisition)session.createCriteria(JobRequisition.class).add(Restrictions.eq("jobreqId", new Long(id))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getJobRequistion()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return jobreq;
  }
  
  public JobRequisition getJobRequisionByJobCode(String jobreqcode, long super_user_key)
  {
    logger.info("Inside getJobRequisionByJobCode method");
    JobRequisition jobreq = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      jobreq = (JobRequisition)session.createCriteria(JobRequisition.class).add(Restrictions.eq("jobreqcode", jobreqcode)).add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getJobRequisionByJobCode()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return jobreq;
  }
  
  public List getJobRequisionByName(String name, long super_user_key)
  {
    logger.info("Inside getJobRequisionByName method");
    List rlist = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      rlist = session.createCriteria(JobRequisition.class).add(Restrictions.eq("jobreqName", name)).add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getJobRequisionByName()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return rlist;
  }
  
  public JobRequisition getJobRequisionByNumber(long requisition_number, long super_user_key)
  {
    logger.info("Inside getJobRequisionByNumber method");
    JobRequisition jobreq = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      jobreq = (JobRequisition)session.createCriteria(JobRequisition.class).add(Restrictions.eq("requisition_number", Long.valueOf(requisition_number))).add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getJobRequisionByNumber()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return jobreq;
  }
  
  public JobRequisition getJobRequisionByUUid(String uuid)
  {
    logger.info("Inside getJobRequisionByUUid method");
    JobRequisition jobreq = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      jobreq = (JobRequisition)session.createCriteria(JobRequisition.class).add(Restrictions.eq("uuid", uuid)).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getJobRequisionByUUid()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return jobreq;
  }
  
  public JobRequisition getJobRequisionActive(String id)
  {
    logger.info("Inside getJobRequisionActive method");
    JobRequisition jobreq = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      jobreq = (JobRequisition)session.createCriteria(JobRequisition.class).add(Restrictions.eq("jobreqId", new Long(id))).add(Restrictions.eq("state", "Active")).add(Restrictions.eq("status", "Open")).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getJobRequisionActive()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return jobreq;
  }
  
  public String getUUIDbyReqId(long reqId)
  {
    logger.info("Inside getUUIDbyReqId method");
    String uuid = "";
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      String sql = "select u.uuid from job_requisition u where jb_req_id = :jb_req_id";
      Query query = session.createSQLQuery(sql);
      query.setLong("jb_req_id", reqId);
      uuid = (String)query.uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUUIDbyReqId()", e);
    }
    return uuid;
  }
  
  public String getRequisitionNamebyReqId(long reqId)
  {
    logger.info("Inside getUUIDbyReqId method");
    String name = "";
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      String sql = "select u.jb_req_name from job_requisition u where jb_req_id = :jb_req_id";
      Query query = session.createSQLQuery(sql);
      query.setLong("jb_req_id", reqId);
      name = (String)query.uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUUIDbyReqId()", e);
    }
    return name;
  }
  
  public JobRequisition getJobRequision(String id, String uuid)
  {
    logger.info("Inside getJobRequistion method");
    JobRequisition jobreq = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      jobreq = (JobRequisition)session.createCriteria(JobRequisition.class).add(Restrictions.eq("uuid", uuid)).add(Restrictions.eq("jobreqId", new Long(id))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getJobRequistion()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return jobreq;
  }
  
  public JobRequisition getJobRequisionByUUID(String id)
  {
    logger.info("Inside getJobRequisionByUUID method");
    JobRequisition jobreq = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      jobreq = (JobRequisition)session.createCriteria(JobRequisition.class).add(Restrictions.eq("uuid", id)).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getJobRequisionByUUID()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return jobreq;
  }
  
  public JobRequisition getActiveJobRequisionByJobCode(String jobcode)
  {
    logger.info("Inside getActiveJobRequisionByJobCode method");
    JobRequisition jobreq = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      jobreq = (JobRequisition)session.createCriteria(JobRequisition.class).add(Restrictions.eq("jobreqcode", jobcode)).add(Restrictions.eq("state", "Active")).add(Restrictions.eq("status", "Open")).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getActiveJobRequisionByJobCode()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return jobreq;
  }
  
  public JobTemplateApprovers getApprover(String id)
  {
    logger.info("Inside getApprover method");
    JobTemplateApprovers app = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      app = (JobTemplateApprovers)session.createCriteria(JobTemplateApprovers.class).add(Restrictions.eq("jbTmplApproverId", Long.valueOf(new Long(id).longValue()))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getApprover()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return app;
  }
  
  public JobRequisition getActiveOpenJobRequision(String id)
  {
    logger.info("Inside getActiveJobRequision method");
    JobRequisition jobreq = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      jobreq = (JobRequisition)session.createCriteria(JobRequisition.class).add(Restrictions.eq("jobreqId", new Long(id))).add(Restrictions.eq("state", "Active")).add(Restrictions.eq("status", "Open")).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getActiveOpenJobRequision()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return jobreq;
  }
  
  public JobRequisition getActiveOpenJobRequision(String id, String uuid)
  {
    logger.info("Inside getActiveJobRequision method");
    JobRequisition jobreq = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      jobreq = (JobRequisition)session.createCriteria(JobRequisition.class).add(Restrictions.eq("jobreqId", new Long(id))).add(Restrictions.eq("uuid", uuid)).add(Restrictions.eq("state", "Active")).add(Restrictions.eq("status", "Open")).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getActiveOpenJobRequision()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return jobreq;
  }
  
  public User getHiringManagerByReqId(long requisitionId)
  {
    logger.info("Inside getHiringManagerByReqId method");
    JobRequisition jobreq = null;
    User user = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      jobreq = (JobRequisition)session.createCriteria(JobRequisition.class).add(Restrictions.eq("jobreqId", new Long(requisitionId))).uniqueResult();
      if (jobreq != null) {
        user = jobreq.getHiringmgr();
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getHiringManagerByReqId()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return user;
  }
  
  public long getRecruiterIdByReqId(long requisitionId)
  {
    logger.info("Inside getRecruiterIdByReqId method");
    JobRequisition jobreq = null;
    long recruiterId = 0L;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      jobreq = (JobRequisition)session.createCriteria(JobRequisition.class).add(Restrictions.eq("jobreqId", new Long(requisitionId))).uniqueResult();
      if (jobreq != null) {
        recruiterId = jobreq.getRecruiterId();
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getRecruiterIdByReqId()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return recruiterId;
  }
  
  public Recruiter getRecruiterByReqId(long requisitionId)
  {
    logger.info("Inside getRecruiterByReqId method");
    JobRequisition jobreq = null;
    Recruiter recruiter = new Recruiter();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String sql = "select u.recruiter_id, u.recruiter_name ,u.is_recruiter_group  from job_requisition u  where jb_req_id = :jb_req_id ";
      Query query = session.createSQLQuery(sql);
      query.setLong("jb_req_id", new Long(requisitionId).longValue());
      List userList = query.list();
      if ((userList != null) && (userList.size() > 0))
      {
        Object[] obj = (Object[])userList.get(0);
        BigInteger userId1 = (BigInteger)obj[0];
        long uid = userId1.longValue();
        recruiter.setRecruiterId(uid);
        recruiter.setRecruiterName((String)obj[1]);
        recruiter.setIsgrouprecruiter((String)obj[2]);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getRecruiterByReqId()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return recruiter;
  }
  
  public List<String> getRequistionDataByQuery(String query, String cri)
  {
    logger.info("Inside getRequistionDataByQuery method");
    List reqList = new ArrayList();
    
    org.hibernate.Session session = null;
    try
    {
      logger.info("query" + query);
      logger.info("cri" + cri);
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      Criteria outer = session.createCriteria(JobRequisition.class).add(Restrictions.eq("state", "Active")).add(Restrictions.ne("status", "Deleted"));
      if ((!StringUtils.isNullOrEmpty(cri)) && (cri.equals("REQ_NO"))) {
        try
        {
          Long id = new Long(query);
          outer.add(Restrictions.eq("requisition_number", id));
        }
        catch (Exception e) {}
      } else if ((!StringUtils.isNullOrEmpty(cri)) && (cri.equals("REQ_NAME"))) {
        outer.add(Restrictions.like("jobreqName", "%" + query + "%"));
      } else if ((!StringUtils.isNullOrEmpty(cri)) && (cri.equals("REQ_CODE"))) {
        outer.add(Restrictions.like("jobreqcode", "%" + query + "%"));
      } else {
        logger.info("All requistions");
      }
      reqList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getRequistionDataByQuery()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return reqList;
  }
  
  public JobTemplateApprovers getJobRequisionApprover(String id)
  {
    logger.info("Inside getJobRequisionApprover method");
    JobTemplateApprovers jobreqapprover = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      jobreqapprover = (JobTemplateApprovers)session.createCriteria(JobTemplateApprovers.class).add(Restrictions.eq("jbTmplApproverId", new Long(id))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getJobRequisionApprover()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return jobreqapprover;
  }
  
  public JobTemplateApprovers updateJobRequisionApprover(JobTemplateApprovers jobapprover)
  {
    logger.info("Inside updateJobRequisionApprover method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.update(jobapprover);
    }
    catch (Exception e)
    {
      logger.error("Exception on updateJobRequisionApprover()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return jobapprover;
  }
  
  public JobTemplateApprovers saveJobRequisionApprover(JobTemplateApprovers jobapprover)
  {
    logger.info("Inside saveJobRequisionApprover method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.save(jobapprover);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveJobRequisionApprover()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return jobapprover;
  }
  
  public List getJobRequisionTemplateComptetencyList(long id, String type)
  {
    logger.info("Inside getJobRequisionTemplateComptetencyList method");
    List compenectList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      compenectList = session.createCriteria(JobTemplateCompetency.class).add(Restrictions.eq("jbTmplId", new Long(id))).add(Restrictions.eq("type", type)).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getJobRequisionTemplateComptetencyList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return compenectList;
  }
  
  public List getJobRequisionTemplateAccomplishmentList(long id, String type)
  {
    logger.info("Inside getJobRequisionTemplateAccomplishmentList method");
    List compenectList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      compenectList = session.createCriteria(JobTemplateAccomplishment.class).add(Restrictions.eq("jbTmplId", new Long(id))).add(Restrictions.eq("type", type)).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getJobRequisionTemplateAccomplishmentList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return compenectList;
  }
  
  public List getJobRequisionTemplateApproversList(long id, String type)
  {
    logger.info("Inside getJobRequisionTemplateApproversList method");
    List compenectList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      compenectList = session.createCriteria(JobTemplateApprovers.class).add(Restrictions.eq("jbTmplId", new Long(id))).add(Restrictions.eq("type", type)).add(Restrictions.eq("isapprover", "Y")).addOrder(Order.asc("levelorder")).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getJobRequisionTemplateApproversList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return compenectList;
  }
  
  public List getPublishToVendorList(long reqId)
  {
    logger.info("Inside getPublishToVendorList method");
    List pList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      pList = session.createCriteria(PublishToVendor.class).add(Restrictions.eq("jobreqId", Long.valueOf(reqId))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getPublishToVendorList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return pList;
  }
  
  public JobTemplateApprovers getNextRequistionApprover(long id, String type, int level)
  {
    logger.info("Inside getNextRequistionApprover method");
    JobTemplateApprovers approver = null;
    List aaproverList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      aaproverList = session.createCriteria(JobTemplateApprovers.class).add(Restrictions.eq("jbTmplId", new Long(id))).add(Restrictions.eq("type", type)).add(Restrictions.eq("isapprover", "Y")).add(Restrictions.gt("levelorder", Integer.valueOf(level))).addOrder(Order.asc("levelorder")).list();
      


      int i = 0;
      if (i < aaproverList.size()) {
        approver = (JobTemplateApprovers)aaproverList.get(i);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getNextRequistionApprover()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return approver;
  }
  
  public List getMyJobRequistionsGroupByOrganization(long currentuserid)
  {
    logger.info("Inside getMyJobRequistionsGroupByOrganization method");
    List tmplList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      Criteria outer = session.createCriteria(JobRequisition.class).add(Restrictions.ne("status", "Deleted")).add(Restrictions.eq("state", "Active")).createAlias("hiringmgr", "hiringmgr");
      

      Criterion hiringmgr = Restrictions.eq("hiringmgr.userId", new Long(currentuserid));
      Criterion recruiter = Restrictions.eq("recruiterId", new Long(currentuserid));
      
      Disjunction disjunction = Restrictions.disjunction();
      disjunction.add(hiringmgr);
      disjunction.add(recruiter);
      outer.add(disjunction);
      
      outer.createAlias("organization", "organization");
      ProjectionList projList = Projections.projectionList();
      projList.add(Projections.property("organization.orgName"));
      projList.add(Projections.groupProperty("organization.orgId"));
      
      outer.setProjection(projList);
      tmplList = outer.list();
      

      logger.info(Integer.valueOf(tmplList.size()));
    }
    catch (Exception e)
    {
      logger.error("Exception on getMyJobRequistionsGroupByOrganization()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return tmplList;
  }
  
  public List getJobRequistionsGroupByOrganizationByRecruiterId(long recruiterId)
  {
    logger.info("Inside getJobRequistionsGroupByOrganizationByRecruiterId method");
    List tmplList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      











      String hqlquery = " select distinct o.orgName, o.orgId from JobRequisition j , UserGroup ug, Organization o where  (o.orgId= j.organization.orgId and j.recruiterId = :currentuserid and j.isgrouprecruiter='N'  and j.status <> 'Deleted')  or (o.orgId= j.organization.orgId and j.status <> 'Deleted' and j.recruiterId = ug.usergrpId and  j.isgrouprecruiter='Y' and ug.users.userId =:currentuserid)";
      




      Query query = session.createQuery(hqlquery);
      query.setLong("currentuserid", new Long(recruiterId).longValue());
      

      tmplList = query.list();
      logger.info(Integer.valueOf(tmplList.size()));
    }
    catch (Exception e)
    {
      logger.error("Exception on getJobRequistionsGroupByOrganizationByRecruiterId()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return tmplList;
  }
  
  public List getJobRequistionsGroupByDepartment(long currentuserid, long orgid)
  {
    logger.info("Inside getJobRequistionsGroupByDepartment method");
    List tmplList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      Criteria outer = session.createCriteria(JobRequisition.class).add(Restrictions.ne("status", "Deleted")).createAlias("hiringmgr", "hiringmgr").add(Restrictions.eq("hiringmgr.userId", new Long(currentuserid)));
      
      outer.createAlias("organization", "organization").add(Restrictions.eq("organization.orgId", new Long(orgid)));
      outer.createAlias("department", "department");
      ProjectionList projList = Projections.projectionList();
      projList.add(Projections.property("department.departmentName"));
      projList.add(Projections.groupProperty("department.departmentId"));
      
      outer.setProjection(projList);
      tmplList = outer.list();
      
      logger.info(Integer.valueOf(tmplList.size()));
    }
    catch (Exception e)
    {
      logger.error("Exception on getJobRequistionsGroupByDepartment()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return tmplList;
  }
  
  public List getJobRequistionsGroupByDepartment(long orgid)
  {
    logger.info("Inside getJobRequistionsGroupByDepartment(long orgid)");
    List tmplList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      Criteria outer = session.createCriteria(JobRequisition.class).add(Restrictions.ne("status", "Deleted"));
      outer.createAlias("organization", "organization").add(Restrictions.eq("organization.orgId", new Long(orgid)));
      outer.createAlias("department", "department");
      ProjectionList projList = Projections.projectionList();
      projList.add(Projections.property("department.departmentName"));
      projList.add(Projections.groupProperty("department.departmentId"));
      
      outer.setProjection(projList);
      tmplList = outer.list();
      
      logger.info(Integer.valueOf(tmplList.size()));
    }
    catch (Exception e)
    {
      logger.error("Exception on getJobRequistionsGroupByDepartment(long orgid)", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return tmplList;
  }
  
  public List getJobRequistionsByOrganizationByDepartmentByHiringMgr(long currentuserid, long orgid, long deptId)
  {
    logger.info("Inside getJobRequistionsByOrganizationByDepartmentByHiringMgr method");
    List tmplList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      Criteria outer = session.createCriteria(JobRequisition.class).add(Restrictions.ne("status", "Deleted")).createAlias("hiringmgr", "hiringmgr").add(Restrictions.eq("hiringmgr.userId", new Long(currentuserid)));
      
      outer.createAlias("organization", "organization").add(Restrictions.eq("organization.orgId", new Long(orgid)));
      outer.createAlias("department", "department").add(Restrictions.eq("department.departmentId", new Long(deptId)));
      outer.createAlias("projectcode", "projectcode");
      
      ProjectionList projList = Projections.projectionList();
      projList.add(Projections.property("jobreqId"));
      projList.add(Projections.property("jobreqName"));
      projList.add(Projections.property("projectcode.projCode"));
      



      outer.setProjection(projList);
      
      outer.addOrder(Order.asc("jobreqName"));
      
      tmplList = outer.list();
      
      logger.info(Integer.valueOf(tmplList.size()));
    }
    catch (Exception e)
    {
      logger.error("Exception on getJobRequistionsByOrganizationByDepartmentByHiringMgr()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return tmplList;
  }
  
  public List getJobRequistionsByOrganizationByDepartment(long orgid, long deptId)
  {
    logger.info("Inside getJobRequistionsByOrganizationByDepartment method");
    List tmplList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      Criteria outer = session.createCriteria(JobRequisition.class).add(Restrictions.ne("status", "Deleted"));
      outer.createAlias("organization", "organization").add(Restrictions.eq("organization.orgId", new Long(orgid)));
      outer.createAlias("department", "department").add(Restrictions.eq("department.departmentId", new Long(deptId)));
      outer.createAlias("projectcode", "projectcode");
      
      ProjectionList projList = Projections.projectionList();
      projList.add(Projections.property("jobreqId"));
      projList.add(Projections.property("jobreqName"));
      projList.add(Projections.property("projectcode.projCode"));
      

      outer.setProjection(projList);
      
      outer.addOrder(Order.asc("jobreqName"));
      
      tmplList = outer.list();
      
      logger.info(Integer.valueOf(tmplList.size()));
    }
    catch (Exception e)
    {
      logger.error("Exception on getJobRequistionsByOrganizationByDepartment()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return tmplList;
  }
  
  public List getJobRequistionsByOrganizationByDepartmentTx(long orgid, long deptId)
  {
    logger.info("Inside getJobRequistionsByOrganizationByDepartmentTx method");
    List tmplList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = session.createCriteria(JobRequisition.class).add(Restrictions.ne("status", "Deleted"));
      outer.createAlias("organization", "organization").add(Restrictions.eq("organization.orgId", new Long(orgid)));
      outer.createAlias("department", "department").add(Restrictions.eq("department.departmentId", new Long(deptId)));
      outer.createAlias("projectcode", "projectcode");
      
      ProjectionList projList = Projections.projectionList();
      projList.add(Projections.property("jobreqId"));
      projList.add(Projections.property("jobreqName"));
      projList.add(Projections.property("projectcode.projCode"));
      

      outer.setProjection(projList);
      
      outer.addOrder(Order.asc("jobreqName"));
      
      tmplList = outer.list();
      
      logger.info(Integer.valueOf(tmplList.size()));
    }
    catch (Exception e)
    {
      logger.error("Exception on getJobRequistionsByOrganizationByDepartmentTx()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return tmplList;
  }
  
  public List getJobRequistionsByOrganizationByDepartmentByRecruiter(long orgid, long deptId, long recruiterId)
  {
    logger.info("Inside getJobRequistionsByOrganizationByDepartmentByRecruiter method");
    List tmplList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String sql = " select distinct j.jb_req_id ,j.jb_req_name,(select proj_code  from project_codes where proj_id=j.project_id) as project_code  from job_requisition j , user_group ug ,user_group_users ugs  where  ((j.recruiter_Id = :currentuserid and j.is_recruiter_group <> 'Y')  or (j.recruiter_Id = ug.user_group_id and  j.is_recruiter_group='Y' and ugs.user_id =:currentuserid) )   and j.state = 'Active' and j.status <> 'Deleted'";
      if (orgid != 0L) {
        sql = sql + " and j.org_id = :orgId";
      }
      if (deptId != 0L) {
        sql = sql + " and j.department_Id = :departmentId";
      }
      sql = sql + " order by j.jb_req_name asc ";
      

      Query query = session.createSQLQuery(sql);
      query.setLong("currentuserid", new Long(recruiterId).longValue());
      if (orgid != 0L) {
        query.setLong("orgId", new Long(orgid).longValue());
      }
      if (deptId != 0L) {
        query.setLong("departmentId", new Long(deptId).longValue());
      }
      List rlist = query.list();
      for (int i = 0; i < rlist.size(); i++)
      {
        Object[] obj = (Object[])rlist.get(i);
        JobRequisition ja = new JobRequisition();
        
        BigInteger id = (BigInteger)obj[0];
        long reqId = id.longValue();
        ja.setJobreqId(reqId);
        ja.setJobreqName((String)obj[1]);
        ProjectCodes pj = new ProjectCodes();
        pj.setProjCode((String)obj[2]);
        ja.setProjectcode(pj);
        tmplList.add(ja);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getJobRequistionsByOrganizationByDepartmentByRecruiter()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return tmplList;
  }
  
  public List getJobRequistionsByOrganizationByDepartmentByHiringManager(long orgid, long deptId, long hiringmgrId)
  {
    logger.info("Inside getJobRequistionsByOrganizationByDepartmentByHiringManager method");
    List tmplList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      Criteria outer = session.createCriteria(JobRequisition.class).add(Restrictions.ne("status", "Deleted"));
      outer.createAlias("organization", "organization").add(Restrictions.eq("organization.orgId", new Long(orgid)));
      outer.createAlias("department", "department").add(Restrictions.eq("department.departmentId", new Long(deptId)));
      outer.createAlias("hiringmgr", "hiringmgr");
      outer.add(Restrictions.eq("hiringmgr.userId", new Long(hiringmgrId)));
      outer.createAlias("projectcode", "projectcode");
      
      ProjectionList projList = Projections.projectionList();
      projList.add(Projections.property("jobreqId"));
      projList.add(Projections.property("jobreqName"));
      projList.add(Projections.property("projectcode.projCode"));
      

      outer.setProjection(projList);
      
      outer.addOrder(Order.asc("jobreqName"));
      
      tmplList = outer.list();
      
      logger.info(Integer.valueOf(tmplList.size()));
    }
    catch (Exception e)
    {
      logger.error("Exception on getJobRequistionsByOrganizationByDepartmentByHiringManager()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return tmplList;
  }
  
  public List getMyJobRequistionsByOrganizationByDepartment(long orgid, long deptId, long hiringmgrId)
  {
    logger.info("Inside getMyJobRequistionsByOrganizationByDepartment method");
    List tmplList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      Criteria outer = session.createCriteria(JobRequisition.class).add(Restrictions.ne("status", "Deleted")).add(Restrictions.eq("state", "Active"));
      outer.createAlias("organization", "organization").add(Restrictions.eq("organization.orgId", new Long(orgid)));
      outer.createAlias("department", "department").add(Restrictions.eq("department.departmentId", new Long(deptId)));
      outer.createAlias("hiringmgr", "hiringmgr");
      Criterion hiringmgr = Restrictions.eq("hiringmgr.userId", new Long(hiringmgrId));
      Criterion recruiter = Restrictions.eq("recruiterId", new Long(hiringmgrId));
      
      Disjunction disjunction = Restrictions.disjunction();
      disjunction.add(hiringmgr);
      disjunction.add(recruiter);
      outer.add(disjunction);
      outer.createAlias("projectcode", "projectcode");
      
      ProjectionList projList = Projections.projectionList();
      projList.add(Projections.property("jobreqId"));
      projList.add(Projections.property("jobreqName"));
      projList.add(Projections.property("projectcode.projCode"));
      

      outer.setProjection(projList);
      
      outer.addOrder(Order.asc("jobreqName"));
      
      tmplList = outer.list();
      
      logger.info(Integer.valueOf(tmplList.size()));
    }
    catch (Exception e)
    {
      logger.error("Exception on getMyJobRequistionsByOrganizationByDepartment()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return tmplList;
  }
  
  public long getMaxValueFromRequistion(long superUserKey)
  {
    logger.info("Inside getMaxValueFromRequistion method");
    long maxvalue = 0L;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      String sql = "select max(requisition_number) from job_requisition  where super_user_key = :super_user_key";
      Query query = session.createSQLQuery(sql);
      query.setLong("super_user_key", superUserKey);
      Object obj = query.uniqueResult();
      
      BigInteger maxv = (BigInteger)obj;
      maxvalue = maxv.longValue();
    }
    catch (Exception e)
    {
      logger.info("Exception on getMaxValueFromRequistion()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return maxvalue;
  }
  
  public List getMyJobRequistionsByOrganizationByDepartmentTx(long orgid, long deptId, long hiringmgrId)
  {
    logger.info("Inside getMyJobRequistionsByOrganizationByDepartmentTx method");
    List tmplList = new ArrayList();
    org.hibernate.Session session = null;
    logger.info("orgid:" + orgid + " deptId:" + deptId + " hiringmgrId:" + hiringmgrId);
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String sql = " select distinct j.jb_req_id ,j.jb_req_name,(select proj_code  from project_codes where proj_id=j.project_id) as project_code  from job_requisition j   where  j.hiring_mgr_id = :hiring_mgr_id  and j.state = 'Active' and j.status <> 'Deleted' ";
      if (orgid != 0L) {
        sql = sql + " and j.org_id = :orgId";
      }
      if (deptId != 0L) {
        sql = sql + " and j.department_Id = :departmentId";
      }
      sql = sql + " order by j.jb_req_name asc ";
      

      Query query = session.createSQLQuery(sql);
      query.setLong("hiring_mgr_id", new Long(hiringmgrId).longValue());
      if (orgid != 0L) {
        query.setLong("orgId", new Long(orgid).longValue());
      }
      if (deptId != 0L) {
        query.setLong("departmentId", new Long(deptId).longValue());
      }
      List rlist = query.list();
      
      logger.info("sql" + sql);
      logger.info("total size" + rlist.size());
      for (int i = 0; i < rlist.size(); i++)
      {
        Object[] obj = (Object[])rlist.get(i);
        JobRequisition ja = new JobRequisition();
        
        BigInteger id = (BigInteger)obj[0];
        long reqId = id.longValue();
        ja.setJobreqId(reqId);
        ja.setJobreqName((String)obj[1]);
        ProjectCodes pj = new ProjectCodes();
        pj.setProjCode((String)obj[2]);
        ja.setProjectcode(pj);
        tmplList.add(ja);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getMyJobRequistionsByOrganizationByDepartmentTx()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return tmplList;
  }
  
  public List getJobRequistionsByOrganizationByDepartmentByRecruiterTx(long orgid, long deptId, long recruiterId)
  {
    logger.info("Inside getJobRequistionsByOrganizationByDepartmentByRecruiterTx method");
    List tmplList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      logger.info("recruiterId" + recruiterId);
      String sql = " select distinct j.jb_req_id ,j.jb_req_name,(select proj_code  from project_codes where proj_id=j.project_id) as project_code  from job_requisition j , user_group ug ,user_group_users ugs  where  ((j.recruiter_Id = :currentuserid and j.is_recruiter_group <> 'Y')  or (j.recruiter_Id = ug.user_group_id and  j.is_recruiter_group='Y' and ugs.user_id =:currentuserid) )   and j.state = 'Active' and j.status <> 'Deleted' ";
      if (orgid != 0L) {
        sql = sql + " and j.org_id = :orgId";
      }
      if (deptId != 0L) {
        sql = sql + " and j.department_Id = :departmentId";
      }
      sql = sql + " order by j.jb_req_name asc ";
      

      Query query = session.createSQLQuery(sql);
      query.setLong("currentuserid", new Long(recruiterId).longValue());
      if (orgid != 0L) {
        query.setLong("orgId", new Long(orgid).longValue());
      }
      if (deptId != 0L) {
        query.setLong("departmentId", new Long(deptId).longValue());
      }
      List rlist = query.list();
      for (int i = 0; i < rlist.size(); i++)
      {
        Object[] obj = (Object[])rlist.get(i);
        JobRequisition ja = new JobRequisition();
        
        BigInteger id = (BigInteger)obj[0];
        long reqId = id.longValue();
        ja.setJobreqId(reqId);
        ja.setJobreqName((String)obj[1]);
        ProjectCodes pj = new ProjectCodes();
        pj.setProjCode((String)obj[2]);
        ja.setProjectcode(pj);
        tmplList.add(ja);
      }
    }
    catch (Exception e)
    {
      logger.info("Exception on getJobRequistionsByOrganizationByDepartmentByRecruiterTx()", e);
      logger.error("Exception on getJobRequistionsByOrganizationByDepartmentByRecruiterTx()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return tmplList;
  }
  
  public List getPublishToVendorList(long reqId, String isFromSystemRule)
  {
    logger.info("Inside getPublishToVendorList method");
    List aList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      aList = session.createCriteria(PublishToVendor.class).add(Restrictions.eq("jobreqId", Long.valueOf(reqId))).add(Restrictions.eq("isFromSystemRule", "Y")).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getPublishToVendorList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return aList;
  }
  
  public void deletePublishToVendor(long reqId, long publishToVendorId)
  {
    logger.info("Inside getPublishToVendorList method");
    List aList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      String hql = "delete from PublishToVendor where jobreqId = :reqId and publishToVendorId = :publishToVendorId";
      Query query = session.createQuery(hql);
      query.setLong("reqId", reqId);
      query.setLong("publishToVendorId", publishToVendorId);
      int rowCount = query.executeUpdate();
    }
    catch (Exception e)
    {
      logger.error("Exception on deletePublishToVendor()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public boolean isAgencyHeaderTreeAllowed(User user, JobRequisition jb)
  {
    logger.info("inside isAgencyHeaderTreeAllowed");
    boolean isSuccess = false;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      Criteria pubcri = session.createCriteria(PublishToVendor.class).add(Restrictions.eq("jobreqId", Long.valueOf(jb.getJobreqId()))).add(Restrictions.eq("userId", Long.valueOf(user.getUserId())));
      List publist = pubcri.list();
      if ((publist != null) && (publist.size() > 0)) {
        isSuccess = true;
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on isAgencyHeaderTreeAllowed()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return isSuccess;
  }
  
  public PublishJobBoards savePublishJobBoards(PublishJobBoards publishJobBoards)
  {
    logger.info("Inside savePublishJobBoards method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.save(publishJobBoards);
    }
    catch (Exception e)
    {
      logger.error("Exception on savePublishJobBoards()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return publishJobBoards;
  }
  
  public PublishJobBoards updatePublishJobBoards(PublishJobBoards publishJobBoards)
  {
    logger.info("Inside updatePublishJobBoards method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.update(publishJobBoards);
    }
    catch (Exception e)
    {
      logger.error("Exception on updatePublishJobBoards()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return publishJobBoards;
  }
  
  public PublishJobBoards deletePublishedJobBoards(PublishJobBoards publishJobBoards)
  {
    logger.info("Inside deletePublishedJobBoards method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.delete(publishJobBoards);
    }
    catch (Exception e)
    {
      logger.error("Exception on deletePublishedJobBoards()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return publishJobBoards;
  }
  
  public PublishJobBoards getPublishJobBoardsByReqIdandjobBoardCode(String jobreqId, String jobBoardCode)
  {
    logger.info("Inside getPublishJobBoardsByReqIdandjobBoardCode method");
    PublishJobBoards publishJobBoards = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      publishJobBoards = (PublishJobBoards)session.createCriteria(PublishJobBoards.class).add(Restrictions.eq("jobRequisition.jobreqId", Long.valueOf(new Long(jobreqId).longValue()))).add(Restrictions.eq("jobBoardCode", jobBoardCode)).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getPublishJobBoardsByReqIdandjobBoardCode()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return publishJobBoards;
  }
  
  public boolean isjobBoardCodeExist(String jobreqId, String jobBoardCode)
  {
    logger.info("Inside isjobBoardCodeExist method");
    boolean flag = false;
    PublishJobBoards publishJobBoards = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      publishJobBoards = (PublishJobBoards)session.createCriteria(PublishJobBoards.class).add(Restrictions.eq("jobRequisition.jobreqId", Long.valueOf(new Long(jobreqId).longValue()))).add(Restrictions.eq("jobBoardCode", jobBoardCode)).uniqueResult();
      if (publishJobBoards != null) {
        flag = true;
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on isjobBoardCodeExist()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return flag;
  }
  
  public List getAllPublishJobBoardsListByReqId(long jobreqId)
  {
    logger.info("Inside getAllPublishJobBoardsListByReqId method");
    List publishJobBoardsList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      publishJobBoardsList = session.createCriteria(PublishJobBoards.class).createAlias("jobRequisition", "jobRequisition").add(Restrictions.eq("jobRequisition.jobreqId", Long.valueOf(jobreqId))).add(Restrictions.eq("status", "P")).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllPublishJobBoardsListByReqId()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return publishJobBoardsList;
  }
  
  public List getJobBoardsListByJobBoardCode(String jobBoardCode)
  {
    logger.info("Inside getJobBoardsListByJobBoardCode method");
    List jobBoardsList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      jobBoardsList = session.createCriteria(PublishJobBoards.class).add(Restrictions.eq("jobBoardCode", jobBoardCode)).add(Restrictions.eq("status", "P")).createAlias("jobRequisition", "jobRequisition").add(Restrictions.eq("jobRequisition.state", "Active")).add(Restrictions.eq("jobRequisition.status", "Open")).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getJobBoardsListByJobBoardCode()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return jobBoardsList;
  }
}
