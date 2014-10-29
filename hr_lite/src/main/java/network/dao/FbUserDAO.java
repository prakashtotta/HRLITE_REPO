package network.dao;

import com.lucene.IndexSearch;
import com.util.StringUtils;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import network.bean.FaceBookUser;
import network.bean.FbConcentration;
import network.bean.FbDTO;
import network.bean.FbEmployer;
import network.bean.FbEndorsementAsk;
import network.bean.FbEndorsements;
import network.bean.FbJobApplicants;
import network.bean.FbJobs;
import network.bean.FbLocation;
import network.bean.FbPositions;
import network.bean.FbSavedCompanies;
import network.bean.FbSavedJobs;
import network.bean.FbSchool;
import network.bean.FbUserAchivements;
import network.bean.FbUserCertifications;
import network.bean.FbUserEmployer;
import network.bean.FbUserSchools;
import network.bean.FbUserSkills;
import network.bean.FbUserSpecialities;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class FbUserDAO
  extends HibernateDaoSupport
{
  protected static final Logger logger = Logger.getLogger(FbUserDAO.class);
  
  public FaceBookUser saveFbUser(FaceBookUser user)
  {
    logger.info("Inside saveFbUser method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      user.setUpdatedDate(new Date());
      session.save(user);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveFbUser()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return user;
  }
  
  public FbSavedCompanies saveFbSavedCompanies(FbSavedCompanies fbc)
  {
    logger.info("Inside saveFbSavedCompanies method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.save(fbc);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveFbSavedCompanies()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return fbc;
  }
  
  public FbSavedJobs saveFbSavedJob(FbSavedJobs fsj)
  {
    logger.info("Inside saveFbSavedJob method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.save(fsj);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveFbSavedJob()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return fsj;
  }
  
  public void saveAskForEndorsement(List uids, String fromid)
  {
    logger.info("Inside saveAskForEndorsement method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      if (uids != null) {
        for (int i = 0; i < uids.size(); i++)
        {
          String toid = (String)uids.get(i);
          FbEndorsementAsk fbask = isAskForEndorseExist(toid, fromid);
          if (fbask == null)
          {
            fbask = new FbEndorsementAsk();
            fbask.setFromFbId(fromid);
            fbask.setToFbId(toid);
            fbask.setStatus("I");
            session.save(fbask);
          }
        }
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on saveAskForEndorsement()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void saveEndorsements(List uids, String fromid, String comment)
  {
    logger.info("Inside saveEndorsements method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      if (uids != null) {
        for (int i = 0; i < uids.size(); i++)
        {
          String toid = (String)uids.get(i);
          FbEndorsements fbask = new FbEndorsements();
          fbask.setFromFbId(fromid);
          fbask.setToFbId(toid);
          fbask.setEndorse(comment);
          fbask.setCreatedDate(new Date());
          session.save(fbask);
          
          deleteFbEndorsementAsk(toid, fromid);
        }
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on saveEndorsements()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public FaceBookUser updateFbUser(FaceBookUser user)
  {
    logger.info("Inside updateFbUser method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      user.setUpdatedDate(new Date());
      session.update(user);
    }
    catch (Exception e)
    {
      logger.error("Exception on updateFbUser()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return user;
  }
  
  public FbJobs updateFbJob(FbJobs job)
  {
    logger.info("Inside updateFbJob method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      job.setUpdatedDate(new Date());
      session.update(job);
    }
    catch (Exception e)
    {
      logger.error("Exception on updateFbJob()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return job;
  }
  
  public List searchFbUsers(String queryString, int pageSize, int startIndex)
  {
    logger.info("Inside searchFbUsers method");
    List ulist = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      Criteria outer = session.createCriteria(FaceBookUser.class);
      
      IndexSearch idxsearch = new IndexSearch();
      String keyword = queryString;
      if (!StringUtils.isNullOrEmpty(keyword)) {
        if (((!keyword.equals("null") ? 1 : 0) & (!keyword.equals("Search..") ? 1 : 0)) != 0)
        {
          while ((keyword.startsWith("*")) && (keyword != null)) {
            keyword = keyword.substring(1, keyword.length());
          }
          logger.info("final keyword is after remove star first " + keyword);
          while ((keyword.startsWith("?")) && (keyword != null)) {
            keyword = keyword.substring(1, keyword.length());
          }
          logger.info("final keyword is after remove question first " + keyword);
          
          List<Long> ids = idxsearch.searchFaceBookUsers(keyword);
          if (!StringUtils.isNullOrEmpty(keyword)) {
            if (((!keyword.equals("null") ? 1 : 0) & (!keyword.equals("Search..") ? 1 : 0)) != 0)
            {
              List<Long> users = new ArrayList();
              for (int i = 0; i < ids.size(); i++)
              {
                Long userid = (Long)ids.get(i);
                users.add(userid);
              }
              outer.add(Restrictions.in("userId", users));
            }
          }
        }
      }
      outer.addOrder(Order.desc("updatedDate"));
      
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      
      ulist = outer.list();
      logger.info(Integer.valueOf(ulist.size()));
    }
    catch (Exception e)
    {
      logger.info("Exception on searchFbUsers()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return ulist;
  }
  
  public List searchFbJobs(String queryString, int pageSize, int startIndex)
  {
    logger.info("Inside searchFbJobs method");
    List ulist = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      Criteria outer = session.createCriteria(FbJobs.class);
      
      IndexSearch idxsearch = new IndexSearch();
      String keyword = queryString;
      if (!StringUtils.isNullOrEmpty(keyword)) {
        if (((!keyword.equals("null") ? 1 : 0) & (!keyword.equals("Search..") ? 1 : 0)) != 0)
        {
          while ((keyword.startsWith("*")) && (keyword != null)) {
            keyword = keyword.substring(1, keyword.length());
          }
          logger.info("final keyword is after remove star first " + keyword);
          while ((keyword.startsWith("?")) && (keyword != null)) {
            keyword = keyword.substring(1, keyword.length());
          }
          logger.info("final keyword is after remove question first " + keyword);
          
          List<Long> ids = idxsearch.searchFaceBookJobs(keyword);
          if (!StringUtils.isNullOrEmpty(keyword)) {
            if (((!keyword.equals("null") ? 1 : 0) & (!keyword.equals("Search..") ? 1 : 0)) != 0)
            {
              List<Long> jobs = new ArrayList();
              for (int i = 0; i < ids.size(); i++)
              {
                Long userid = (Long)ids.get(i);
                jobs.add(userid);
              }
              outer.add(Restrictions.in("jobId", jobs));
            }
          }
        }
      }
      outer.addOrder(Order.desc("updatedDate"));
      
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      
      ulist = outer.list();
      logger.info(Integer.valueOf(ulist.size()));
    }
    catch (Exception e)
    {
      logger.info("Exception on searchFbJobs()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return ulist;
  }
  
  public List allFbJobsList()
  {
    logger.info("Inside allFbJobsList method");
    List ulist = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      Criteria outer = session.createCriteria(FbJobs.class);
      
      ulist = outer.list();
      logger.info(Integer.valueOf(ulist.size()));
    }
    catch (Exception e)
    {
      logger.info("Exception on allFbJobsList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return ulist;
  }
  
  public List searchCompanies(String queryString, int pageSize, int startIndex)
  {
    logger.info("Inside searchCompanies method");
    List ulist = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      Criteria outer = session.createCriteria(FbEmployer.class);
      if (!StringUtils.isNullOrEmpty(queryString)) {
        if (((!queryString.equals("null") ? 1 : 0) & (!queryString.equals("Search..") ? 1 : 0)) != 0) {
          outer.add(Restrictions.like("name", "%" + queryString + "%"));
        }
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      
      ulist = outer.list();
    }
    catch (Exception e)
    {
      logger.info("Exception on searchCompanies()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return ulist;
  }
  
  public FbConcentration saveFbConcentration(FbConcentration con)
  {
    logger.info("Inside saveFbConcentration method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.save(con);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveFbConcentration()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return con;
  }
  
  public FbEmployer saveFbEmployer(FbEmployer emp)
  {
    logger.info("Inside FbEmployer method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.save(emp);
    }
    catch (Exception e)
    {
      logger.error("Exception on FbEmployer()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return emp;
  }
  
  public FbLocation saveFbLocation(FbLocation emp)
  {
    logger.info("Inside saveFbLocation method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.save(emp);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveFbLocation()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return emp;
  }
  
  public FbPositions saveFbPositions(FbPositions emp)
  {
    logger.info("Inside saveFbPositions method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.save(emp);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveFbPositions()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return emp;
  }
  
  public FbSchool saveFbSchool(FbSchool emp)
  {
    logger.info("Inside saveFbSchool method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.save(emp);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveFbSchool()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return emp;
  }
  
  public void saveFbEmployerList(List<FbUserEmployer> ulist, long userId)
  {
    logger.info("Inside saveFbEmployerList method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      for (int i = 0; i < ulist.size(); i++)
      {
        FbUserEmployer fbe = (FbUserEmployer)ulist.get(i);
        fbe.setUserId(userId);
        session.save(fbe);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on saveFbEmployerList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void saveFbUserSchoolsList(List<FbUserSchools> ulist, long userId)
  {
    logger.info("Inside saveFbUserSchoolsList method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      for (int i = 0; i < ulist.size(); i++)
      {
        FbUserSchools fbe = (FbUserSchools)ulist.get(i);
        fbe.setUserId(userId);
        session.save(fbe);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on saveFbUserSchoolsList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public FbEmployer isEmployeerExist(String facebookid)
  {
    logger.info("Inside isEmployeerExist method");
    FbEmployer fb = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      fb = (FbEmployer)session.createCriteria(FbEmployer.class).add(Restrictions.eq("facebookid", facebookid)).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on isEmployeerExist()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return fb;
  }
  
  public List getAllEndorsements(String toFbId)
  {
    logger.info("Inside getAllEndorsements method");
    FbEmployer fb = null;
    org.hibernate.Session session = null;
    List endList = new ArrayList();
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      endList = session.createCriteria(FbEndorsements.class).add(Restrictions.eq("toFbId", toFbId)).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllEndorsements()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return endList;
  }
  
  public List getAllFbUsers(int indexdone)
  {
    logger.info("Inside getAllFbUsers method");
    org.hibernate.Session session = null;
    List uList = new ArrayList();
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      uList = session.createCriteria(FaceBookUser.class).add(Restrictions.eq("indexdone", Integer.valueOf(indexdone))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllFbUsers()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return uList;
  }
  
  public List getAllFbJobs(int indexdone)
  {
    logger.info("Inside getAllFbJobs method");
    org.hibernate.Session session = null;
    List uList = new ArrayList();
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      uList = session.createCriteria(FbJobs.class).add(Restrictions.eq("indexdone", Integer.valueOf(indexdone))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllFbJobs()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return uList;
  }
  
  public List getAllEndorsementsGiven(String fromFbId)
  {
    logger.info("Inside getAllEndorsementsGiven method");
    
    org.hibernate.Session session = null;
    List endList = new ArrayList();
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      endList = session.createCriteria(FbEndorsements.class).add(Restrictions.eq("fromFbId", fromFbId)).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllEndorsementsGiven()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return endList;
  }
  
  public List getAllAskEndorsesTo(String toFbId)
  {
    logger.info("Inside getAllAskEndorsesTo method");
    FbEmployer fb = null;
    org.hibernate.Session session = null;
    List endList = new ArrayList();
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      endList = session.createCriteria(FbEndorsementAsk.class).add(Restrictions.eq("toFbId", toFbId)).add(Restrictions.eq("status", "I")).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllAskEndorsesTo()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return endList;
  }
  
  public FbEndorsementAsk isAskForEndorseExist(String toid, String fromid)
  {
    logger.info("Inside isAskForEndorseExist method");
    FbEndorsementAsk fb = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      fb = (FbEndorsementAsk)session.createCriteria(FbEndorsementAsk.class).add(Restrictions.eq("fromFbId", fromid)).add(Restrictions.eq("toFbId", toid)).add(Restrictions.eq("status", "I")).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on isAskForEndorseExist()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return fb;
  }
  
  public FaceBookUser isFaceBookUserExist(String facebookid)
  {
    logger.info("Inside isFaceBookUserExist method");
    FaceBookUser fb = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      fb = (FaceBookUser)session.createCriteria(FaceBookUser.class).add(Restrictions.eq("facebookid", facebookid)).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on isFaceBookUserExist()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return fb;
  }
  
  public FbSchool isSchoolExist(String facebookid)
  {
    logger.info("Inside isSchoolExist method");
    FbSchool fb = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      fb = (FbSchool)session.createCriteria(FbSchool.class).add(Restrictions.eq("facebookid", facebookid)).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on isSchoolExist()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return fb;
  }
  
  public FbConcentration isConcentrationExist(String facebookid)
  {
    logger.info("Inside isConcentrationExist method");
    FbConcentration fb = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      fb = (FbConcentration)session.createCriteria(FbConcentration.class).add(Restrictions.eq("facebookid", facebookid)).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on isConcentrationExist()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return fb;
  }
  
  public FbPositions isPositionsExist(String facebookid)
  {
    logger.info("Inside isPositionsExist method");
    FbPositions fb = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      fb = (FbPositions)session.createCriteria(FbPositions.class).add(Restrictions.eq("facebookid", facebookid)).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on isPositionsExist()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return fb;
  }
  
  public FbLocation isLocationExist(String facebookid)
  {
    logger.info("Inside isLocationExist method");
    FbLocation fb = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      fb = (FbLocation)session.createCriteria(FbLocation.class).add(Restrictions.eq("facebookid", facebookid)).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on isLocationExist()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return fb;
  }
  
  public void deleteFbUserEmployers(long userId)
  {
    logger.info("Inside deleteFbUserEmployers method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      String hql = "delete from FbUserEmployer where userId = :userId";
      Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
      query.setLong("userId", userId);
      
      int rowCount = query.executeUpdate();
    }
    catch (Exception e)
    {
      int rowCount;
      logger.error("Exception on deleteFbUserEmployers()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void deleteSavedCompany(long userId, String comFacebookid)
  {
    logger.info("Inside deleteSavedCompany method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      String hql = "delete from FbSavedCompanies where userId = :userId and comFacebookid =:comFacebookid";
      Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
      query.setLong("userId", userId);
      query.setString("comFacebookid", comFacebookid);
      
      int rowCount = query.executeUpdate();
    }
    catch (Exception e)
    {
      
      logger.error("Exception on deleteSavedCompany()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void deleteFbEndorsementAsk(String fromFbId, String toFbId)
  {
    logger.info("Inside deleteFbEndorsementAsk method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      String hql = "delete from FbEndorsementAsk where fromFbId = :fromFbId and toFbId =:toFbId";
      Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
      query.setString("fromFbId", fromFbId);
      query.setString("toFbId", toFbId);
      
      int  rowCount = query.executeUpdate();
    }
    catch (Exception e)
    {
      
      logger.error("Exception on deleteFbEndorsementAsk()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void deleteFbUserSchools(long userId)
  {
    logger.info("Inside deleteFbUserSchools method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      String hql = "delete from FbUserSchools where userId = :userId";
      Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
      query.setLong("userId", userId);
      
      int rowCount = query.executeUpdate();
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteFbUserSchools()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public FbDTO getEmployeeCountAndSavedInfo(String facebookid, long userId)
  {
    logger.info("Inside getEmployeeCountAndSavedInfo method");
    int totalcount = 0;
    org.hibernate.Session session = null;
    FbDTO fbdto = new FbDTO();
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String sql = "select count(fue.user_emp_id) as countvalue  from fb_user_employer fue, fb_employer fe  where fue.employer_id=fe.employer_id and fue.level=0  and fe.facebookid=:facebookid";
      

      Query query = session.createSQLQuery(sql);
      query.setString("facebookid", facebookid);
      BigInteger total = (BigInteger)query.uniqueResult();
      fbdto.setCount(total.intValue());
      
      sql = " select count(jb.job_id) as countvalue  from fb_jobs jb, fb_employer fe  where jb.companyName = fe.name  and  fe.facebookid=:facebookid";
      

      query = session.createSQLQuery(sql);
      query.setString("facebookid", facebookid);
      total = (BigInteger)query.uniqueResult();
      fbdto.setJobCount(total.intValue());
      
      sql = " select fud.facebookid,fud.full_name,fud.link,fud.user_name  from fb_user_data fud, fb_user_employer fue, fb_employer fe  where fud.user_id =fue.user_id and  fue.employer_id=fe.employer_id and fue.level=0  and fe.facebookid=:facebookid";
      



      query = session.createSQLQuery(sql);
      query.setString("facebookid", facebookid);
      List lst = query.list();
      List flist = new ArrayList();
      for (int i = 0; i < lst.size(); i++)
      {
        Object[] obj = (Object[])lst.get(i);
        FaceBookUser fuser = new FaceBookUser();
        fuser.setFacebookid((String)obj[0]);
        fuser.setFullName((String)obj[1]);
        fuser.setLink((String)obj[2]);
        fuser.setFuserName((String)obj[3]);
        
        flist.add(fuser);
        if (i == 4) {
          break;
        }
      }
      fbdto.setLst(flist);
      
      FbSavedCompanies fbc = (FbSavedCompanies)session.createCriteria(FbSavedCompanies.class).add(Restrictions.eq("comFacebookid", facebookid)).add(Restrictions.eq("userId", Long.valueOf(userId))).uniqueResult();
      if (fbc != null) {
        fbdto.setSaved(true);
      } else {
        fbdto.setSaved(false);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getEmployeeCountAndSavedInfo()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return fbdto;
  }
  
  public FbDTO getSavedJobInfo(long jobId, long userId)
  {
    logger.info("Inside getSavedJobInfo method");
    int totalcount = 0;
    org.hibernate.Session session = null;
    FbDTO fbdto = new FbDTO();
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      FbSavedJobs fbc = (FbSavedJobs)session.createCriteria(FbSavedJobs.class).add(Restrictions.eq("jobId", Long.valueOf(jobId))).add(Restrictions.eq("userId", Long.valueOf(userId))).uniqueResult();
      if (fbc != null) {
        fbdto.setSaved(true);
      } else {
        fbdto.setSaved(false);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getSavedJobInfo()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return fbdto;
  }
  
  public List getSavedCompanies(long userId, int startIndex, int pagesize)
  {
    logger.info("Inside getSavedCompanies method");
    List savedList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      String sql = " select fbe.employer_id, fbe.facebookid,fbe.name from fb_employer fbe , fb_saved_companies fbs  where fbe.facebookid=fbs.facebookid  and fbs.user_id = :user_id order by saved_comp_id desc LIMIT " + startIndex + "," + pagesize;
      


      Query query = session.createSQLQuery(sql);
      query.setLong("user_id", userId);
      List lst = query.list();
      for (int i = 0; i < lst.size(); i++)
      {
        Object[] obj = (Object[])lst.get(i);
        FbEmployer femp = new FbEmployer();
        
        BigInteger id = (BigInteger)obj[0];
        long empid = id.longValue();
        
        femp.setEmId(empid);
        femp.setFacebookid((String)obj[1]);
        femp.setName((String)obj[2]);
        

        savedList.add(femp);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on savedList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return savedList;
  }
  
  public List getSavedJobs(long userId, int startIndex, int pagesize)
  {
    logger.info("Inside getSavedJobs method");
    List savedList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      




      String hql = "select j from FbJobs j, FbSavedJobs f where j.jobId=f.jobId and f.userId=:userId order by f.fbsaveJobId desc";
      Query query = session.createQuery(hql);
      query.setLong("userId", userId);
      
      query.setFirstResult(startIndex);
      query.setMaxResults(pagesize);
      savedList = query.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getSavedJobs()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return savedList;
  }
  
  public List getFbUsersskillListByUserId(long userId)
  {
    logger.info("Inside getFbUsersskillListByUserId method");
    List skillListbyUserId = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      skillListbyUserId = session.createCriteria(FbUserSkills.class).add(Restrictions.eq("userId", Long.valueOf(userId))).list();
      

      logger.info(Integer.valueOf(skillListbyUserId.size()));
    }
    catch (Exception e)
    {
      logger.info("Exception on getFbUsersskillListByUserId()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return skillListbyUserId;
  }
  
  public FbUserSkills saveFbUserSkills(FbUserSkills fbUserSkills)
  {
    logger.info("Inside saveFbUserSkills method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      session.save(fbUserSkills);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveFbUserSkills()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return fbUserSkills;
  }
  
  public FbUserSkills updateFbUserSkills(FbUserSkills fbUserSkills)
  {
    logger.info("Inside updateFbUserSkills method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      session.update(fbUserSkills);
    }
    catch (Exception e)
    {
      logger.error("Exception on updateFbUserSkills()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return fbUserSkills;
  }
  
  public List getFbUsersSpecialitiesListByUserId(long userId)
  {
    logger.info("Inside getFbUsersSpecialitiesListByUserId method");
    List specialitieslListbyUserId = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      specialitieslListbyUserId = session.createCriteria(FbUserSpecialities.class).add(Restrictions.eq("userId", Long.valueOf(userId))).list();
      

      logger.info(Integer.valueOf(specialitieslListbyUserId.size()));
    }
    catch (Exception e)
    {
      logger.info("Exception on getFbUsersSpecialitiesListByUserId()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return specialitieslListbyUserId;
  }
  
  public FbUserSpecialities saveFbUserSpecialiteis(FbUserSpecialities fbUserSpecialities)
  {
    logger.info("Inside saveFbUserSpecialiteis method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      session.save(fbUserSpecialities);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveFbUserSpecialiteis()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return fbUserSpecialities;
  }
  
  public FbUserSpecialities updateFbUserSpecialiteis(FbUserSpecialities fbUserSpecialities)
  {
    logger.info("Inside updateFbUserSpecialiteis method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      session.update(fbUserSpecialities);
    }
    catch (Exception e)
    {
      logger.error("Exception on updateFbUserSpecialiteis()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return fbUserSpecialities;
  }
  
  public List getFbUsersAchievementListByUserId(long userId)
  {
    logger.info("Inside getFbUsersAchievementListByUserId method");
    List achievementListbyUserId = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      achievementListbyUserId = session.createCriteria(FbUserAchivements.class).add(Restrictions.eq("userId", Long.valueOf(userId))).list();
      

      logger.info("size >> " + achievementListbyUserId.size());
    }
    catch (Exception e)
    {
      logger.info("Exception on getFbUsersAchievementListByUserId()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return achievementListbyUserId;
  }
  
  public FbUserAchivements saveFbUserAchievement(FbUserAchivements fbUserAchivements)
  {
    logger.info("Inside saveFbUserAchievement method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      session.save(fbUserAchivements);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveFbUserAchievement()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return fbUserAchivements;
  }
  
  public FbUserAchivements updateFbUserAchievement(FbUserAchivements fbUserAchivements)
  {
    logger.info("Inside updateFbUserAchievement method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      session.update(fbUserAchivements);
    }
    catch (Exception e)
    {
      logger.error("Exception on updateFbUserAchievement()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return fbUserAchivements;
  }
  
  public FbJobs savePostJob(FbJobs fbJobs)
  {
    logger.info("Inside savePostJob method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      session.save(fbJobs);
    }
    catch (Exception e)
    {
      logger.error("Exception on savePostJob()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return fbJobs;
  }
  
  public FbUserCertifications updateFBUserCertifications(FbUserCertifications fbUserCertifications)
  {
    logger.info("Inside updateFBUserCertifications method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      session.update(fbUserCertifications);
    }
    catch (Exception e)
    {
      logger.error("Exception on updateFBUserCertifications()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return fbUserCertifications;
  }
  
  public FbUserCertifications saveFBUserCertifications(FbUserCertifications fbUserCertifications)
  {
    logger.info("Inside updateFBUserCertifications method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      session.save(fbUserCertifications);
    }
    catch (Exception e)
    {
      logger.error("Exception on updateFBUserCertifications()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return fbUserCertifications;
  }
  
  public List getFBUserCertificationsListByUserId(long userId)
  {
    logger.info("Inside getFBUserCertificationsListByUserId method");
    List certificationsListbyUserId = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      certificationsListbyUserId = session.createCriteria(FbUserCertifications.class).add(Restrictions.eq("userId", Long.valueOf(userId))).list();
      

      logger.info("size >> " + certificationsListbyUserId.size());
    }
    catch (Exception e)
    {
      logger.info("Exception on getFBUserCertificationsListByUserId()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return certificationsListbyUserId;
  }
  
  public FbJobs getFBJobsByJobId(long jobId)
  {
    logger.info("Inside getFBJobsByJobId method");
    FbJobs fbJobs = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      fbJobs = (FbJobs)session.createCriteria(FbJobs.class).add(Restrictions.eq("jobId", Long.valueOf(jobId))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.info("Exception on getFBJobsByJobId()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return fbJobs;
  }
  
  public FbJobApplicants applyJob(FbJobApplicants fsj)
  {
    logger.info("Inside applyJob method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.save(fsj);
    }
    catch (Exception e)
    {
      logger.error("Exception on applyJob()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return fsj;
  }
  
  public List getApplicantListByJobId(long jobId)
  {
    logger.info("Inside getApplicantListByJobId method");
    List ulist = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      ulist = session.createCriteria(FbJobApplicants.class).add(Restrictions.eq("jobId", Long.valueOf(jobId))).list();
      
      logger.info(Integer.valueOf(ulist.size()));
    }
    catch (Exception e)
    {
      logger.info("Exception on getApplicantListByJobId()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return ulist;
  }
  
  public FaceBookUser getFbUser(long userId)
  {
    logger.info("Inside getFbUser method");
    org.hibernate.Session session = null;
    FaceBookUser faceBookUser = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      faceBookUser = (FaceBookUser)session.createCriteria(FaceBookUser.class).add(Restrictions.eq("userId", Long.valueOf(userId))).uniqueResult();
      
      logger.info("faceBookUser name >> " + faceBookUser.getFullName());
    }
    catch (Exception e)
    {
      logger.error("Exception on getFbUser()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return faceBookUser;
  }
  
  public boolean isAppliedtoSameJob(long userId, long jobId)
  {
    logger.info("Inside isAppliedtoSameJob method");
    
    org.hibernate.Session session = null;
    FbJobApplicants fbJobApplicants = null;
    boolean isapplied = false;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      fbJobApplicants = (FbJobApplicants)session.createCriteria(FbJobApplicants.class).add(Restrictions.eq("userId", Long.valueOf(userId))).add(Restrictions.eq("jobId", Long.valueOf(jobId))).uniqueResult();
      if (fbJobApplicants != null) {
        isapplied = true;
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on isAppliedtoSameJob()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return isapplied;
  }
}
