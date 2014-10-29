package com.dao;

import com.bean.QuestionGroupApplicants;
import com.bean.QuestionGroups;
import com.bean.User;
import com.bean.testengine.MockQuestionSet;
import com.bean.testengine.MockTest;
import com.bean.testengine.MockTestQuestion;
import com.bean.testengine.MockTestResult;
import com.util.StringUtils;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class MockTestDAO
  extends HibernateDaoSupport
{
  protected static final Logger logger = Logger.getLogger(MockTestDAO.class);
  
  public MockTest getMockTest(String catId, String applicantId, String uuid)
  {
    logger.info("Inside getMockTest method");
    org.hibernate.Session session = null;
    MockTest mockr = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      mockr = (MockTest)session.createCriteria(MockTest.class).add(Restrictions.eq("cat.catId", Integer.valueOf(new Integer(catId).intValue()))).add(Restrictions.eq("applicantId", Long.valueOf(new Long(applicantId).longValue()))).add(Restrictions.eq("uuid", uuid)).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getMockTest()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return mockr;
  }
  
  public List getAllMockQuestionSetsListForLov(long super_user_key)
  {
    logger.info("Inside getAllMockQuestionSetsListForLov method");
    org.hibernate.Session session = null;
    List mlist = new ArrayList();
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      String sql = "select cat_id, name, display_name  from mockquestionset  where status =:status and super_user_key = :super_user_key order by name";
      Query query = session.createSQLQuery(sql);
      query.setString("status", "A");
      query.setLong("super_user_key", super_user_key);
      List sList = query.list();
      for (int i = 0; i < sList.size(); i++)
      {
        MockQuestionSet m = new MockQuestionSet();
        Object[] obj = (Object[])sList.get(i);
        Integer userId1 = (Integer)obj[0];
        int uid = userId1.intValue();
        m.setCatId(uid);
        
        m.setName((String)obj[1]);
        m.setDisplayName((String)obj[2]);
        
        mlist.add(m);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllMockQuestionSetsListForLov()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return mlist;
  }
  
  public QuestionGroupApplicants getQuestionNaire(long qnsgrpid, String applicantId, String uuid)
  {
    logger.info("Inside getQuestionNaire method");
    org.hibernate.Session session = null;
    QuestionGroupApplicants qnsgroup = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      qnsgroup = (QuestionGroupApplicants)session.createCriteria(QuestionGroupApplicants.class).add(Restrictions.eq("questiongroup.questiongroupId", Long.valueOf(qnsgrpid))).add(Restrictions.eq("applicantId", Long.valueOf(new Long(applicantId).longValue()))).add(Restrictions.eq("uuid", uuid)).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getQuestionNaire()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return qnsgroup;
  }
  
  public QuestionGroupApplicants getQuestionNaire(long qnsGrpAppId)
  {
    logger.info("Inside getQuestionNaire method");
    org.hibernate.Session session = null;
    QuestionGroupApplicants qnsgroup = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      qnsgroup = (QuestionGroupApplicants)session.createCriteria(QuestionGroupApplicants.class).add(Restrictions.eq("qnsGrpAppId", Long.valueOf(qnsGrpAppId))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getQuestionNaire()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return qnsgroup;
  }
  
  public List getAllMockTestsByApplicant(long applicantId, String uuid)
  {
    logger.info("Inside getAllMockTestsByApplicant method");
    org.hibernate.Session session = null;
    List mocktests = new ArrayList();
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      mocktests = session.createCriteria(MockTest.class).add(Restrictions.eq("applicantId", Long.valueOf(applicantId))).add(Restrictions.eq("uuid", uuid)).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllMockTestsByApplicant()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return mocktests;
  }
  
  public List getAllMockCategoryList()
  {
    logger.info("Inside getAllMockCategoryList method");
    org.hibernate.Session session = null;
    List mockcatList = new ArrayList();
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      mockcatList = session.createCriteria(MockQuestionSet.class).add(Restrictions.eq("status", "A")).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllMockCategoryList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return mockcatList;
  }
  
  public MockTest getMockTest(String testId)
  {
    logger.info("Inside getMockTest method");
    org.hibernate.Session session = null;
    MockTest mockr = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      mockr = (MockTest)session.createCriteria(MockTest.class).add(Restrictions.eq("testId", Long.valueOf(new Long(testId).longValue()))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getMockTest()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return mockr;
  }
  
  public MockTest getMockTestByTestDetails(long testid, long applicantId, String uuid)
  {
    logger.info("Inside getMockTest method");
    org.hibernate.Session session = null;
    MockTest mockr = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      mockr = (MockTest)session.createCriteria(MockTest.class).add(Restrictions.eq("testId", Long.valueOf(testid))).add(Restrictions.eq("applicantId", Long.valueOf(applicantId))).add(Restrictions.eq("uuid", uuid)).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getMockTest()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return mockr;
  }
  
  public List getAllMockTestResult(long testId)
  {
    logger.info("Inside getAllMockTestResult method");
    org.hibernate.Session session = null;
    MockTestResult mockr = null;
    List mockTestResultList = new ArrayList();
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      mockTestResultList = session.createCriteria(MockTestResult.class).add(Restrictions.eq("testId", new Long(testId))).addOrder(Order.asc("mockQuestionId")).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllMockTestResult()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return mockTestResultList;
  }
  
  public void updateMockTestResult(MockTestResult mockResult)
  {
    logger.info("Inside updateMockTestResult method");
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      session.update(mockResult);
    }
    catch (Exception e)
    {
      logger.error("Exception on updateMockTestResult()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void updateMockTest(MockTest test)
  {
    logger.info("Inside updateMockTest method");
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      session.update(test);
    }
    catch (Exception e)
    {
      logger.error("Exception on updateMockTest()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void saveMockTest(MockTest test)
  {
    logger.info("Inside saveMockTest method");
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      session.save(test);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveMockTest()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void saveQuestionnaire(QuestionGroupApplicants quitionanire)
  {
    logger.info("Inside saveQuestionnaire method");
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      session.save(quitionanire);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveQuestionnaire()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public MockTestResult getMockTestResult(long testId, long qid)
  {
    logger.info("Inside getMockTestResult method");
    org.hibernate.Session session = null;
    MockTestResult mockr = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      mockr = (MockTestResult)session.createQuery(" from MockTestResult m where m.testId = :testId and m.mockQuestionId = :qid").setLong("testId", testId).setLong("qid", qid).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getMockTestResult()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return mockr;
  }
  
  public List getAllSkipedMockTestResult(long testId)
  {
    logger.info("Inside getAllSkipedMockTestResult method");
    org.hibernate.Session session = null;
    MockTestResult mockr = null;
    List mockTestResultList = new ArrayList();
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      mockTestResultList = session.createCriteria(MockTestResult.class).add(Restrictions.eq("testId", new Long(testId))).add(Restrictions.eq("ansNo", new Integer(0))).addOrder(Order.asc("mockQuestionId")).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllSkipedMockTestResult()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return mockTestResultList;
  }
  
  public MockQuestionSet getMockQuestionSet(int catId)
  {
    logger.info("Inside getMockQuestionSet method");
    org.hibernate.Session session = null;
    MockQuestionSet mockr = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      mockr = (MockQuestionSet)session.createCriteria(MockQuestionSet.class).add(Restrictions.eq("catId", Integer.valueOf(catId))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getMockQuestionSet()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return mockr;
  }
  
  public static QuestionGroups getQuestionsGroupDetails(long id)
  {
    logger.info("Inside getQuestionsGroupDetails method");
    QuestionGroups que = null;
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      que = (QuestionGroups)session.createCriteria(QuestionGroups.class).add(Restrictions.eq("questiongroupId", Long.valueOf(id))).uniqueResult();
      
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
  
  public QuestionGroups getQuestionsGroup(long id)
  {
    logger.info("Inside getQuestionsGroupDetails method");
    QuestionGroups que = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      que = (QuestionGroups)session.createCriteria(QuestionGroups.class).add(Restrictions.eq("questiongroupId", Long.valueOf(id))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getRole()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return que;
  }
  
  public List getAllMarkedMockTestResult(long testId)
  {
    logger.info("Inside getAllMarkedMockTestResult method");
    org.hibernate.Session session = null;
    MockTestResult mockr = null;
    List mockTestResultList = new ArrayList();
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      mockTestResultList = session.createCriteria(MockTestResult.class).add(Restrictions.eq("testId", new Long(testId))).add(Restrictions.eq("isMarked", new Integer(1))).addOrder(Order.asc("mockQuestionId")).list();
      
      System.out.println("mock" + mockr.getMockQuestionId());
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllMarkedMockTestResult()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return mockTestResultList;
  }
  
  public MockTestQuestion getMockQuestionById(long qid)
  {
    logger.info("Inside getMockQuestionById method");
    org.hibernate.Session session = null;
    MockTestQuestion mockq = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      mockq = (MockTestQuestion)session.createCriteria(MockTestQuestion.class).add(Restrictions.eq("mockquestionId", new Long(qid))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getMockQuestionById()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return mockq;
  }
  
  public List getAllMockQuestionByCatId(int cid)
  {
    logger.info("Inside getAllMockQuestionByCatId method");
    org.hibernate.Session session = null;
    List qlist = new ArrayList();
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      qlist = session.createCriteria(MockTestQuestion.class).add(Restrictions.eq("catId", Integer.valueOf(cid))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllMockQuestionByCatId()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return qlist;
  }
  
  public void addMockQuestion(MockTestQuestion question)
  {
    logger.info("Inside addMockQuestion method");
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.save(question);
    }
    catch (Exception e)
    {
      logger.error("Exception on addMockQuestion()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void deleteMockTest(long applicantId, String uuid, long testId)
  {
    logger.info("Inside deleteApplicantAttachment method");
    String hql = "delete from MockTest where applicantId = :applicantId  and uuid = :uuid and testId = :testId";
    Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
    query.setLong("applicantId", applicantId);
    query.setString("uuid", uuid);
    query.setLong("testId", testId);
    int rowCount = query.executeUpdate();
  }
  
  public void deleteQuestionnaire(long applicantId, String uuid, long testId)
  {
    logger.info("Inside deleteQuestionnaire method");
    String hql = "delete from QuestionGroupApplicants where applicantId = :applicantId  and uuid = :uuid and qnsGrpAppId = :testId";
    Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
    query.setLong("applicantId", applicantId);
    query.setString("uuid", uuid);
    query.setLong("testId", testId);
    int rowCount = query.executeUpdate();
  }
  
  public void deleteMockQuestions(long catId)
  {
    logger.info("Inside deleteMockQuestions method");
    String hql = "delete from MockTestQuestion where catId = :catId";
    Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
    query.setLong("catId", catId);
    
    int rowCount = query.executeUpdate();
  }
  
  public MockTest addMockTest(MockTest mock)
  {
    logger.info("Inside addMockTest method");
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.save(mock);
    }
    catch (Exception e)
    {
      logger.error("Exception on addMockTest()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return mock;
  }
  
  public List getPMPMockQuestions(int catId)
  {
    logger.info("Inside getPMPMockQuestions method");
    org.hibernate.Session session = null;
    
    List mockqList = new ArrayList();
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      System.out.println("catId in DAO" + catId);
      mockqList = session.createQuery("from MockTestQuestion qs where qs.catId = :catId").setInteger("catId", catId).list();
      
      Collections.shuffle(mockqList);
    }
    catch (Exception e)
    {
      logger.error("Exception on getMockQuestions()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return mockqList;
  }
  
  public void addPMPMockTestTemp(MockTestResult mock)
  {
    logger.info("Inside PMPMockTestResult method");
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.save(mock);
    }
    catch (Exception e)
    {
      logger.error("Exception on addMockTest()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public static MockQuestionSet saveMockQuestionSet(MockQuestionSet mockquestionSet)
  {
    logger.info("Inside saveMockQuestionSet method");
    
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.save(mockquestionSet);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on saveMockQuestionSet()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return mockquestionSet;
  }
  
  public static MockQuestionSet updateMockQuestionSet(MockQuestionSet mockquestionSet)
  {
    logger.info("Inside updateMockQuestionSet method");
    
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      session.update(mockquestionSet);
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on updateMockQuestionSet()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return mockquestionSet;
  }
  
  public static MockQuestionSet getMockQuestionSeDetails(String id)
  {
    logger.info("Inside getMockQuestionSeDetails method");
    MockQuestionSet mockquestionSet = null;
    org.hibernate.Session session = null;
    try
    {
      session = HibernateUtil.getSession();
      HibernateUtil.beginTransaction();
      mockquestionSet = (MockQuestionSet)session.createCriteria(MockQuestionSet.class).add(Restrictions.eq("catId", Integer.valueOf(Integer.parseInt(id)))).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getMockQuestionSeDetails()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
    return mockquestionSet;
  }
  
  public List getMockQuestionSetForPagination(int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getMockQuestionSetForPagination method");
    List mockquestionsetList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      



      Criteria outer = session.createCriteria(MockQuestionSet.class).add(Restrictions.eq("status", "A"));
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      mockquestionsetList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllTagsForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return mockquestionsetList;
  }
  
  public List getMockQuestionSetBySearchCriteria(User user, String name, String displayname, String description, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getMockQuestionSetBySearchCriteria method");
    List mockquestionsetList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      Criteria crit = session.createCriteria(MockQuestionSet.class).add(Restrictions.eq("status", "A"));
      
      crit.add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      if ((!StringUtils.isNullOrEmpty(name)) && (!name.equals("null"))) {
        crit.add(Restrictions.like("name", "%" + name + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(displayname)) && (!displayname.equals("null"))) {
        crit.add(Restrictions.like("displayName", "%" + displayname + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(description)) && (!description.equals("null"))) {
        crit.add(Restrictions.like("description", "%" + description + "%"));
      }
      crit = crit.setFirstResult(startIndex);
      crit.setMaxResults(pageSize);
      mockquestionsetList = crit.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getMockQuestionSetBySearchCriteria()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return mockquestionsetList;
  }
  
  public List getAllMockQuestionSetBySearchCriteria(User user, String name, String displayname, String description)
  {
    logger.info("Inside getAllMockQuestionSetBySearchCriteria method");
    List mockquestionsetList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      Criteria crit = session.createCriteria(MockQuestionSet.class).add(Restrictions.eq("status", "A"));
      
      crit.add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      if ((!StringUtils.isNullOrEmpty(name)) && (!name.equals("null"))) {
        crit.add(Restrictions.like("name", "%" + name + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(displayname)) && (!displayname.equals("null"))) {
        crit.add(Restrictions.like("displayName", "%" + displayname + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(description)) && (!description.equals("null"))) {
        crit.add(Restrictions.like("description", "%" + description + "%"));
      }
      mockquestionsetList = crit.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllMockQuestionSetBySearchCriteria()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return mockquestionsetList;
  }
  
  public List getMockQuestionSet()
  {
    logger.info("Inside getMockQuestionSet method");
    List mockquestionsetList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      mockquestionsetList = session.createCriteria(MockQuestionSet.class).add(Restrictions.eq("status", "A")).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getMockQuestionSet()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return mockquestionsetList;
  }
}
