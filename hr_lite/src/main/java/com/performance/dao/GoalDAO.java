package com.performance.dao;

import com.common.Common;
import com.performance.bean.Goal;
import com.performance.bean.GoalInitiation;
import com.performance.bean.GoalKra;
import com.performance.bean.GoalKraKpi;
import com.performance.bean.TimePeriod;
import com.performance.bean.UserGoals;
import com.performance.bean.UserGoalsKpi;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class GoalDAO
  extends HibernateDaoSupport
{
  protected static final Logger logger = Logger.getLogger(GoalDAO.class);
  
  public List getAllTimePeriod()
  {
    logger.info("Inside getAllTimePeriod method");
    List expenseList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      expenseList = session.createCriteria(TimePeriod.class).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllTimePeriod()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return expenseList;
  }
  
  public Goal saveGoal(Goal goal)
  {
    logger.info("Inside saveGoal method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      session.save(goal);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveGoal()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return goal;
  }
  
  public UserGoals saveUserGoalWithKpi(UserGoals goal, List<UserGoalsKpi> ukpiList)
  {
    logger.info("Inside saveUserGoalWithKpi method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      session.update(goal);
      




      String hql = "delete from UserGoalsKpi where userGoalId = :userGoalId ";
      Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
      query.setLong("userGoalId", goal.getUserGoalId());
      int rowCount = query.executeUpdate();
      if ((ukpiList != null) && (ukpiList.size() > 0)) {
        for (int k = 0; k < ukpiList.size(); k++)
        {
          UserGoalsKpi kpi = (UserGoalsKpi)ukpiList.get(k);
          session.save(kpi);
        }
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on saveUserGoalWithKpi()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return goal;
  }
  
  public GoalKra saveGoalKra(GoalKra goalkra)
  {
    logger.info("Inside saveGoalKra method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      session.save(goalkra);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveGoalKra()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return goalkra;
  }
  
  public void initateGoalSubmit(List<UserGoals> userGoalsList, List useridList)
  {
    logger.info("Inside initateGoalSubmit method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      if ((userGoalsList != null) && (userGoalsList.size() > 0) && (useridList != null) && (useridList.size() > 0)) {
        for (int i = 0; i < useridList.size(); i++)
        {
          String userIdstr = (String)useridList.get(i);
          long userId = new Long(userIdstr).longValue();
          logger.info("userId" + userId);
          for (int j = 0; j < userGoalsList.size(); j++)
          {
            UserGoals usergoals = (UserGoals)userGoalsList.get(j);
            UserGoals newusergoal = new UserGoals();
            transformUserGoals(usergoals, newusergoal);
            logger.info("usergoals.getGoal().getGoalName()" + newusergoal.getGoal().getGoalName());
            newusergoal.setUserId(userId);
            List<UserGoalsKpi> kpiList = newusergoal.getKpiList();
            newusergoal.setKpiList(new ArrayList());
            session.save(newusergoal);
            logger.info("usergoals.getUserGoalId()" + newusergoal.getUserGoalId());
            if ((kpiList != null) && (kpiList.size() > 0)) {
              for (int k = 0; k < kpiList.size(); k++)
              {
                UserGoalsKpi kpi = (UserGoalsKpi)kpiList.get(k);
                UserGoalsKpi kpinew = new UserGoalsKpi();
                kpinew.setUserGoalId(newusergoal.getUserGoalId());
                kpinew.setKpiName(kpi.getKpiName());
                kpinew.setKraMeasure(kpi.getKraMeasure());
                session.save(kpinew);
              }
            }
          }
        }
      }
      session.flush();
    }
    catch (Exception e)
    {
      logger.error("Exception on initateGoalSubmit()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  private void transformUserGoals(UserGoals usergoals, UserGoals newusergoal)
  {
    newusergoal.setKraId(usergoals.getKraId());
    newusergoal.setKraName(usergoals.getKraName());
    newusergoal.setKradesc(usergoals.getKradesc());
    newusergoal.setKraWeight(usergoals.getKraWeight());
    newusergoal.setIsTrack(usergoals.getIsTrack());
    newusergoal.setTrackStartDate(usergoals.getTrackStartDate());
    newusergoal.setTrackEndDate(usergoals.getTrackEndDate());
    newusergoal.setTrackingFreqency(usergoals.getTrackingFreqency());
    newusergoal.setParentKraId(usergoals.getParentKraId());
    newusergoal.setKramodifiable(usergoals.getModifiable());
    newusergoal.setGoal(usergoals.getGoal());
    newusergoal.setGoalInitiationId(usergoals.getGoalInitiationId());
    newusergoal.setUserId(usergoals.getUserId());
    newusergoal.setModifiable(usergoals.getModifiable());
    newusergoal.setCreatedBy(usergoals.getCreatedBy());
    newusergoal.setCreatedDate(usergoals.getCreatedDate());
    newusergoal.setKpiList(usergoals.getKpiList());
  }
  
  public GoalInitiation saveGoalInitiation(GoalInitiation gini)
  {
    logger.info("Inside GoalInitiation method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      session.save(gini);
    }
    catch (Exception e)
    {
      logger.error("Exception on GoalInitiation()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return gini;
  }
  
  public void saveGoalKraKpi(List<GoalKraKpi> gkpiList)
  {
    logger.info("Inside saveGoalKraKpi method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      for (int i = 0; i < gkpiList.size(); i++)
      {
        GoalKraKpi gkrakpi = (GoalKraKpi)gkpiList.get(i);
        session.save(gkrakpi);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on saveGoalKraKpi()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public List goalListsByOrgDeptTimePeriodDesignation(long orgId, long departmentId, long designationId, long timePeriodId)
  {
    logger.info("Inside goalListsByOrgDeptTimePeriodDesignation method");
    List goalList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      Criteria criteria = session.createCriteria(Goal.class);
      if (orgId != 0L) {
        criteria.add(Restrictions.eq("organization.orgId", Long.valueOf(orgId)));
      }
      if (departmentId != 0L) {
        criteria.add(Restrictions.eq("department.departmentId", Long.valueOf(departmentId)));
      }
      if (designationId != 0L) {
        criteria.add(Restrictions.eq("designation.designationId", Long.valueOf(designationId)));
      }
      if (timePeriodId != 0L) {
        criteria.createAlias("timeperiod", "timeperiod").add(Restrictions.eq("timeperiod.timePeriodId", new Long(timePeriodId)));
      }
      goalList = criteria.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on goalListsByOrgDeptTimePeriodDesignation()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return goalList;
  }
  
  public UserGoals getUserGoal(long userGoalId)
  {
    logger.info("Inside getUserGoal method");
    UserGoals ug = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      ug = (UserGoals)session.createCriteria(UserGoals.class).add(Restrictions.eq("userGoalId", new Long(userGoalId))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getUserGoal()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return ug;
  }
  
  public TimePeriod getTimePeriod(long timePeriodId)
  {
    logger.info("Inside getTimePeriod method");
    TimePeriod ug = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      ug = (TimePeriod)session.createCriteria(TimePeriod.class).add(Restrictions.eq("timePeriodId", new Long(timePeriodId))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getTimePeriod()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return ug;
  }
  
  public List userGoalsList(long userId)
  {
    logger.info("Inside userGoalsList method");
    List goalList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      Criteria criteria = session.createCriteria(UserGoals.class);
      if (userId != 0L) {
        criteria.add(Restrictions.eq("userId", Long.valueOf(userId)));
      }
      goalList = criteria.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on userGoalsList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return goalList;
  }
  
  public List userGoalsList(long userId, long timePeriodId)
  {
    logger.info("Inside userGoalsList method");
    List goalList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      Criteria criteria = session.createCriteria(UserGoals.class);
      if (userId != 0L) {
        criteria.add(Restrictions.eq("userId", Long.valueOf(userId)));
      }
      if (timePeriodId != 0L) {
        criteria.add(Restrictions.eq("timeperiod.timePeriodId", Long.valueOf(timePeriodId)));
      }
      goalList = criteria.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on userGoalsList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return goalList;
  }
  
  public Map getOrgGoalsForPagination(long orgId, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getOrgGoalsForPagination method");
    List ruleList = new ArrayList();
    Map m = new HashMap();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = session.createCriteria(Goal.class).add(Restrictions.eq("organization.orgId", Long.valueOf(orgId)));
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      ruleList = outer.list();
      m.put(Common.ORGS_LIST, ruleList);
      

      Criteria outerc = session.createCriteria(Goal.class).add(Restrictions.eq("organization.orgId", Long.valueOf(orgId)));
      
      outerc.setProjection(Projections.rowCount());
      int totaluser = ((Integer)outerc.list().get(0)).intValue();
      
      Integer totalsize = new Integer(totaluser);
      m.put(Common.ORGS_COUNT, totalsize);
    }
    catch (Exception e)
    {
      logger.error("Exception on getOrgGoalsForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return m;
  }
  
  public List<GoalKra> getGoalKraListByGoal(long goalId)
  {
    logger.info("Inside getGoalKraListByGoal method");
    List<GoalKra> kList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      kList = session.createCriteria(GoalKra.class).add(Restrictions.eq("goalId", new Long(goalId))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getGoalKraListByGoal()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return kList;
  }
  
  public List<GoalKraKpi> getKpiListByGoalKra(long goalKraId)
  {
    logger.info("Inside getKpiListByGoalKra method");
    List<GoalKraKpi> kList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      kList = session.createCriteria(GoalKraKpi.class).add(Restrictions.eq("goalKraId", new Long(goalKraId))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getKpiListByGoalKra()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return kList;
  }
}
