package com.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bean.JobApplicant;
import com.bean.JobRequisionTemplate;
import com.bean.JobRequisition;
import com.bean.JobTemplateAccomplishment;
import com.bean.JobTemplateApprovers;
import com.bean.JobTemplateCompetency;
import com.bean.RequisitionActivity;
import com.bean.RequistionAttachments;
import com.bean.RequistionExamQnsAssign;
import com.bean.system.PublishToVendor;
import com.common.ObjectNotFoundException;

public class JobRequistionTXDAO
  extends HibernateDaoSupport
{
  protected static final Logger logger = Logger.getLogger(JobRequistionTXDAO.class);
  
  public void saveJobApprover(JobTemplateApprovers jbtapprover)
  {
    logger.info("Inside saveJobApprover method");
    getHibernateTemplate().save(jbtapprover);
  }
  
  public void updateJobRequistion(JobRequisition jobreq)
  {
    logger.info("Inside updateJobRequistion method");
    try
    {
      getHibernateTemplate().update(jobreq);
    }
    catch (Exception e)
    {
      logger.info("exception updateJobRequistion" + e.getMessage());
      logger.error(e);
    }
  }
  
  public void updateJobRequistionWithClear(JobRequisition jobreq)
    throws Exception
  {
    logger.info("Inside updateJobRequistionWithClear method");
    try
    {
      getHibernateTemplate().clear();
      getHibernateTemplate().update(jobreq);
    }
    catch (Exception e)
    {
      logger.info("exception updateJobRequistionWithClear" + e.getMessage());
      logger.error(e);
      throw e;
    }
  }
  
  public JobRequisition updateJobRequistionWithReturn(JobRequisition jobreq)
  {
    logger.info("Inside updateJobRequistionWithReturn method");
    getHibernateTemplate().update(jobreq);
    return jobreq;
  }
  
  public void saveJobRequistion(JobRequisition jobreq)
  {
    logger.info("Inside saveJobRequistion method");
    getHibernateTemplate().save(jobreq);
  }
  
  public void saveActivity(RequisitionActivity activity)
  {
    logger.info("Inside saveActivity method");
    getHibernateTemplate().save(activity);
  }
  
  public void saveJobRequistionTemplate(JobRequisionTemplate tmpl)
  {
    logger.info("Inside saveJobRequistionTemplate method");
    getHibernateTemplate().save(tmpl);
  }
  
  public void updateJobRequistionTemplate(JobRequisionTemplate tmpl)
  {
    logger.info("Inside updateJobRequistionTemplate method");
    getHibernateTemplate().update(tmpl);
  }
  
  public void updateJobRequisionApprover(JobTemplateApprovers jobapprover)
  {
    logger.info("Inside updateJobRequisionApprover method");
    getHibernateTemplate().update(jobapprover);
  }
  
  public JobTemplateApprovers updateJobRequisionApproverWithReturn(JobTemplateApprovers jobapprover)
  {
    logger.info("Inside updateJobRequisionApproverWithReturn method");
    getHibernateTemplate().update(jobapprover);
    return jobapprover;
  }
  
  public JobTemplateApprovers saveJobRequisionApprover(JobTemplateApprovers jobapprover)
  {
    logger.info("Inside saveJobRequisionApprover method");
    getHibernateTemplate().save(jobapprover);
    return jobapprover;
  }
  
  public RequistionExamQnsAssign getRequistionExamQnsAssign(long reqid)
  {
    logger.info("Inside getRequistionExamQnsAssign method");
    RequistionExamQnsAssign jobreqqns = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      jobreqqns = (RequistionExamQnsAssign)session.createCriteria(RequistionExamQnsAssign.class).add(Restrictions.eq("jobreqid", Long.valueOf(reqid))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getRequistionExamQnsAssign()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return jobreqqns;
  }
  
  public void saveRequistionExamQnsAssign(RequistionExamQnsAssign jb)
  {
    logger.info("**** Inside saveRequistionExamQnsAssign method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.save(jb);
    }
    catch (Exception e)
    {
      logger.error("**** Exception on saveRequistionExamQnsAssign()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void updateRequistionExamQnsAssign(RequistionExamQnsAssign jb)
  {
    logger.info("**** Inside updateRequistionExamQnsAssign method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.update(jb);
    }
    catch (Exception e)
    {
      logger.error("**** Exception on updateRequistionExamQnsAssign()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public JobTemplateApprovers getNextRequistionApprover(long id, String type, int level)
  {
    logger.info("Inside getNextRequistionApprover method");
    JobTemplateApprovers approver = null;
    List aaproverList = new ArrayList();
    
    aaproverList = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(JobTemplateApprovers.class).add(Restrictions.eq("jbTmplId", new Long(id))).add(Restrictions.eq("type", type)).add(Restrictions.eq("isapprover", "Y")).add(Restrictions.gt("levelorder", Integer.valueOf(level))).addOrder(Order.asc("levelorder")).list();
    


    int i = 0;
    if (i < aaproverList.size()) {
      approver = (JobTemplateApprovers)aaproverList.get(i);
    }
    return approver;
  }
  
  public JobTemplateApprovers getNextRequistionApproverTx(long id, String type, int level)
    throws Exception
  {
    logger.info("Inside getNextRequistionApprover method");
    JobTemplateApprovers approver = null;
    List aaproverList = new ArrayList();
    try
    {
      aaproverList = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(JobTemplateApprovers.class).add(Restrictions.eq("jbTmplId", new Long(id))).add(Restrictions.eq("type", type)).add(Restrictions.eq("isapprover", "Y")).add(Restrictions.gt("levelorder", Integer.valueOf(level))).addOrder(Order.asc("levelorder")).list();
      


      int i = 0;
      if (i < aaproverList.size()) {
        approver = (JobTemplateApprovers)aaproverList.get(i);
      }
    }
    catch (Exception e)
    {
      logger.info("Inside getNextRequistionApprover method");
      throw e;
    }
    return approver;
  }
  
  public List getJobRequisionTemplateApproversList(long id, String type)
  {
    logger.info("Inside getJobRequisionTemplateApproversList method");
    List compenectList = new ArrayList();
    
    compenectList = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(JobTemplateApprovers.class).add(Restrictions.eq("jbTmplId", new Long(id))).add(Restrictions.eq("type", type)).add(Restrictions.eq("isapprover", "Y")).addOrder(Order.asc("levelorder")).list();
    




    return compenectList;
  }
  
  public void deleteCompetencies(long reqId, String type)
  {
    logger.info("Inside deleteCompetencies method");
    String hql = "delete from JobTemplateCompetency where jbTmplId = :reqId and type=:type";
    Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
    query.setLong("reqId", reqId);
    query.setString("type", type);
    int rowCount = query.executeUpdate();
  }
  
  public void deleteAttachments(long reqId, String type)
  {
    logger.info("Inside deleteAttachments method");
    String hql = "delete from RequistionAttachments where reqId = :reqId and type=:type";
    Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
    query.setLong("reqId", reqId);
    query.setString("type", type);
    int rowCount = query.executeUpdate();
  }
  
  public void deleteAttachment(String uuid)
    throws Exception
  {
    try
    {
      logger.info("Inside deleteAttachment method");
      String hql = "delete from RequistionAttachments where uuid = :inuuid";
      Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
      query.setString("inuuid", uuid);
      int rowCount = query.executeUpdate();
    }
    catch (Exception e)
    {
      logger.info("error on deleteAttachment " + e);
      throw e;
    }
  }
  
  public void deleteApprover(long reqid, long id)
  {
    logger.info("Inside deleteApprover method");
    String hql = "delete from JobTemplateApprovers where jbTmplId =:jbTmplId and jbTmplApproverId = :id";
    Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
    query.setLong("jbTmplId", reqid);
    query.setLong("id", id);
    int rowCount = query.executeUpdate();
  }
  
  public void deleteAccomplishments(long reqId, String type)
  {
    logger.info("Inside deleteAccomplishments method");
    String hql = "delete from JobTemplateAccomplishment where jbTmplId = :reqId and type=:type";
    Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
    query.setLong("reqId", reqId);
    query.setString("type", type);
    int rowCount = query.executeUpdate();
  }
  
  public void deleteApprovers(long reqId, String type)
  {
    logger.info("Inside deleteApprovers method");
    String hql = "delete from JobTemplateApprovers where jbTmplId = :reqId and type=:type";
    Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
    query.setLong("reqId", reqId);
    query.setString("type", type);
    int rowCount = query.executeUpdate();
  }
  
  public void saveJobReqCompetency(List jbcompetecnyList, String type, long id)
  {
    logger.info("Inside saveJobReqCompetency method");
    for (int i = 0; i < jbcompetecnyList.size(); i++)
    {
      JobTemplateCompetency jbc = (JobTemplateCompetency)jbcompetecnyList.get(i);
      jbc.setType(type);
      jbc.setJbTmplId(id);
      getHibernateTemplate().save(jbc);
    }
  }
  
  public void saveJobReqAccomplishments(List jbaccomplistmentList, String type, long id)
  {
    logger.info("Inside saveJobReqAccomplishments method");
    for (int i = 0; i < jbaccomplistmentList.size(); i++)
    {
      JobTemplateAccomplishment jbc1 = (JobTemplateAccomplishment)jbaccomplistmentList.get(i);
      jbc1.setType(type);
      jbc1.setJbTmplId(id);
      getHibernateTemplate().save(jbc1);
    }
  }
  
  public void saveApproversList(List approverList, String type, long id)
  {
    logger.info("Inside saveApproversList method");
    for (int i = 0; i < approverList.size(); i++)
    {
      JobTemplateApprovers jbapp = (JobTemplateApprovers)approverList.get(i);
      jbapp.setType(type);
      jbapp.setJbTmplId(id);
      getHibernateTemplate().save(jbapp);
    }
  }
  
  public void saveAttachments(List attachementList, String type, long id)
  {
    logger.info("Inside saveAttachments method");
    for (int i = 0; i < attachementList.size(); i++)
    {
      RequistionAttachments jbapp = (RequistionAttachments)attachementList.get(i);
      jbapp.setType(type);
      jbapp.setReqId(id);
      getHibernateTemplate().save(jbapp);
    }
  }
  
  public void saveAttachment(RequistionAttachments appattach)
  {
    logger.info("Inside saveAttachment method");
    getHibernateTemplate().save(appattach);
  }
  
  public void savePublishToVendorList(List publishToList)
    throws Exception
  {
    logger.info("Inside savePublishToVendorList method");
    try
    {
      for (int i = 0; i < publishToList.size(); i++)
      {
        PublishToVendor jbapp = (PublishToVendor)publishToList.get(i);
        getHibernateTemplate().save(jbapp);
      }
    }
    catch (Exception e)
    {
      logger.info("Error on savePublishToVendorList" + e);
      throw e;
    }
  }
  
  public void deletePublishToVendorList(long reqId, String isFromSystemRule)
  {
    logger.info("Inside deletePublishToVendorList method");
    String hql = "delete from PublishToVendor where jobreqId = :reqId and isFromSystemRule = :isFromSystemRule";
    Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
    query.setLong("reqId", reqId);
    query.setString("isFromSystemRule", isFromSystemRule);
    int rowCount = query.executeUpdate();
  }
  
  public void deletePublishToVendorList(long reqId)
  {
    logger.info("Inside deletePublishToVendorList method");
    String hql = "delete from PublishToVendor where jobreqId = :reqId";
    Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
    query.setLong("reqId", reqId);
    int rowCount = query.executeUpdate();
  }
  
  public JobRequisition getJobRequision(String id)
  {
    logger.info("Inside getJobRequistion method");
    JobRequisition jobreq = null;
    
    jobreq = (JobRequisition)getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(JobRequisition.class).add(Restrictions.eq("jobreqId", new Long(id))).uniqueResult();
    
    return jobreq;
  }
  
  public JobRequisition getJobRequision(long id)
  {
    logger.info("Inside getJobRequistion method");
    JobRequisition jobreq = null;
    
    jobreq = (JobRequisition)getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(JobRequisition.class).add(Restrictions.eq("jobreqId", Long.valueOf(id))).uniqueResult();
    
    return jobreq;
  }
  
  public JobRequisition getJobRequisionTx(long id)
    throws ObjectNotFoundException
  {
    logger.info("Inside getJobRequisionTx method");
    JobRequisition jobreq = null;
    try
    {
      jobreq = (JobRequisition)getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(JobRequisition.class).add(Restrictions.eq("jobreqId", Long.valueOf(id))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getJobRequisionTx()", e);
      throw new ObjectNotFoundException("requistion id: " + id + " not found");
    }
    return jobreq;
  }
  
  public JobRequisition getJobRequisionTxByUUID(String uuid)
    throws ObjectNotFoundException
  {
    logger.info("Inside getJobRequisionTxByUUID method");
    JobRequisition jobreq = null;
    try
    {
      jobreq = (JobRequisition)getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(JobRequisition.class).add(Restrictions.eq("uuid", uuid)).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getJobRequisionTxByUUID()", e);
      throw new ObjectNotFoundException("requistion uuid: " + uuid + " not found");
    }
    return jobreq;
  }
  
  public RequistionAttachments getRequistionAttachment(String uuid)
  {
    logger.info("Inside getRequistionAttachment method");
    RequistionAttachments attach = null;
    
    attach = (RequistionAttachments)getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(RequistionAttachments.class).add(Restrictions.eq("uuid", uuid)).uniqueResult();
    
    return attach;
  }
  
  public JobTemplateApprovers getApprover(String id)
  {
    logger.info("Inside getApprover method");
    JobTemplateApprovers app = null;
    
    app = (JobTemplateApprovers)getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(JobTemplateApprovers.class).add(Restrictions.eq("jbTmplApproverId", Long.valueOf(new Long(id).longValue()))).uniqueResult();
    
    return app;
  }
  
  public JobTemplateApprovers getApproverByTmplApproverId(String jbTmplApproverId)
    throws Exception
  {
    logger.info("Inside getApprover method");
    JobTemplateApprovers app = null;
    try
    {
      app = (JobTemplateApprovers)getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(JobTemplateApprovers.class).add(Restrictions.eq("jbTmplApproverId", Long.valueOf(new Long(jbTmplApproverId).longValue()))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getJobRequisionTemplateComptetencyList()", e);
      throw e;
    }
    return app;
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
  
  public List getJobRequisionTemplateComptetencyList(long id, String type)
    throws Exception
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
      throw e;
    }
    return compenectList;
  }
  
  public List getJobRequisionTemplateAccomplishmentList(long id, String type)
    throws Exception
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
      throw e;
    }
    return compenectList;
  }
  
  public List getJobRequisionTmplApproversList(long id, String type)
    throws Exception
  {
    logger.info("Inside getJobRequisionTmplApproversList method");
    List compenectList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      compenectList = session.createCriteria(JobTemplateApprovers.class).add(Restrictions.eq("jbTmplId", new Long(id))).add(Restrictions.eq("type", type)).add(Restrictions.eq("isapprover", "Y")).addOrder(Order.asc("levelorder")).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getJobRequisionTmplApproversList()", e);
      throw e;
    }
    return compenectList;
  }
  
  public List getOfferedApplicants(long reqId)
    throws Exception
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
      
      outer.add(Restrictions.disjunction().add(Restrictions.like("interviewState", "%Offer%"))).add(Restrictions.like("interviewState", "%On Board%"));
      

      applicantList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getOfferedApplicants()", e);
      throw e;
    }
    return applicantList;
  }
  
  public List getAttachmentList(long reqId, String type)
    throws Exception
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
      throw e;
    }
    return offerAttachmentList;
  }
  
  public List getPublishToVendorList(long reqId)
    throws Exception
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
      throw e;
    }
    return pList;
  }
  
  public List getActivityList(long reqId)
    throws Exception
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
      throw e;
    }
    return atList;
  }
  
  public List getActivityListByUuid(String uuid)
    throws Exception
  {
    logger.info("Inside getActivityListByUuid method");
    
    List atList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      Criteria outer = session.createCriteria(RequisitionActivity.class).add(Restrictions.eq("uuid", uuid));
      atList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getActivityListByUuid()", e);
      throw e;
    }
    return atList;
  }
  
  public void deletePublishToVendor(long reqId, long publishToVendorId)
    throws Exception
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
      throw e;
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
}
