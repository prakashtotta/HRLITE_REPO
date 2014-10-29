package com.performance.bo;

import com.bean.Department;
import com.bean.Designations;
import com.bean.Organization;
import com.bean.User;
import com.bo.BOFactory;
import com.bo.LovBO;
import com.bo.UserBO;
import com.performance.bean.Goal;
import com.performance.bean.GoalInitiation;
import com.performance.bean.GoalKra;
import com.performance.bean.GoalKraKpi;
import com.performance.bean.TimePeriod;
import com.performance.bean.UserGoals;
import com.performance.bean.UserGoalsKpi;
import com.performance.dao.GoalDAO;
import com.performance.form.GoalForm;
import com.util.StringUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

public class GoalBO
{
  protected static final Logger logger = Logger.getLogger(GoalBO.class);
  public GoalDAO goaldao;
  
  public Goal saveGoal(Goal goal)
  {
    return this.goaldao.saveGoal(goal);
  }
  
  public void lovPopulateOrgGoalCreate(GoalForm gform, User user, HttpServletRequest request)
  {
    gform.setDesignationList(BOFactory.getLovBO().getAllDesignations(user.getSuper_user_key()));
    
    List orgList = BOFactory.getLovBO().getAllOrganization(user.getSuper_user_key());
    gform.setOrganizationList(orgList);
    
    List deptlist = new ArrayList();
    gform.setDepartmentList(deptlist);
    if (gform.getOrgId() > 0L)
    {
      deptlist = BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(gform.getOrgId()));
      gform.setDepartmentList(deptlist);
    }
    gform.setTimePeriodList(getAllTimePeriod());
  }
  
  public void initiateGoalsSubmit(GoalForm gForm, User user, List userIdList, String emptype, HttpServletRequest request)
  {
    try
    {
      logger.info("Inside initiateGoalsSubmit method");
      GoalInitiation gini = new GoalInitiation();
      if ((!StringUtils.isNullOrEmpty(emptype)) && (emptype.equals("A")))
      {
        Set users = BOFactory.getUserBO().getUsers(gForm.getOrgId(), gForm.getDepartmentId(), gForm.getDesignationId());
        gForm.setUsersset(users);
        
        userIdList = new ArrayList();
        
        Iterator itr = users.iterator();
        while (itr.hasNext())
        {
          User user1 = (User)itr.next();
          userIdList.add(String.valueOf(user1.getUserId()));
        }
        gini.setAllEmployee("Y");
      }
      else
      {
        gini.setAllEmployee("N");
      }
      gini.setNote(gForm.getInitiationNote());
      if (gForm.getOrgId() != 0L)
      {
        Organization org = new Organization();
        org.setOrgId(gForm.getOrgId());
        gini.setOrganization(org);
      }
      else
      {
        gini.setOrganization(null);
      }
      if (gForm.getDepartmentId() != 0L)
      {
        Department depart = new Department();
        depart.setDepartmentId(gForm.getDepartmentId());
        gini.setDepartment(depart);
      }
      else
      {
        gini.setDepartment(null);
      }
      if (gForm.getDesignationId() != 0L)
      {
        Designations designation = new Designations();
        designation.setDesignationId(gForm.getDesignationId());
        gini.setDesignation(designation);
      }
      else
      {
        gini.setDesignation(null);
      }
      if (gForm.getTimePeriodId() != 0L)
      {
        TimePeriod timeperiod = new TimePeriod();
        timeperiod.setTimePeriodId(gForm.getTimePeriodId());
        gini.setTimeperiod(timeperiod);
      }
      gini.setCreatedBy(user.getUserName());
      gini.setCreatedDate(new Date());
      
      gini = saveGoalInitiation(gini);
      





      List goalList = BOFactory.getGoalBO().goalListsByOrgDeptTimePeriodDesignation(gForm.getOrgId(), gForm.getDepartmentId(), gForm.getDesignationId(), gForm.getTimePeriodId());
      
      List<UserGoals> userGoalsList = new ArrayList();
      if ((goalList != null) && (goalList.size() > 0)) {
        for (int i = 0; i < goalList.size(); i++)
        {
          Goal goal = (Goal)goalList.get(i);
          

          List<GoalKra> kraList = BOFactory.getGoalBO().getGoalKraListByGoal(goal.getGoalId());
          if ((kraList != null) && (kraList.size() > 0))
          {
            for (int j = 0; j < kraList.size(); j++)
            {
              GoalKra gkra = (GoalKra)kraList.get(j);
              UserGoals usergoals = new UserGoals();
              usergoals.setGoal(goal);
              usergoals.setGoalInitiationId(gini.getGoalInitiationId());
              usergoals.setCreatedBy(user.getUserName());
              usergoals.setCreatedDate(new Date());
              usergoals.setModifiable(goal.getModifiable());
              tranfromToUserGoals(usergoals, gkra);
              usergoals.setStatus("I");
              usergoals.setTimeperiod(gini.getTimeperiod());
              
              List<GoalKraKpi> kpiList = getKpiListByGoalKra(gkra.getGoalKraId());
              if ((kpiList != null) && (kpiList.size() > 0))
              {
                List<UserGoalsKpi> ukpiList = new ArrayList();
                for (int p = 0; p < kpiList.size(); p++)
                {
                  GoalKraKpi gkpi = (GoalKraKpi)kpiList.get(p);
                  UserGoalsKpi ukpri = new UserGoalsKpi();
                  
                  ukpri.setKpiName(gkpi.getKpiName());
                  ukpri.setKraMeasure(gkpi.getKraMeasure());
                  
                  ukpiList.add(ukpri);
                }
                usergoals.setKpiList(ukpiList);
              }
              userGoalsList.add(usergoals);
            }
          }
          else
          {
            UserGoals usergoals = new UserGoals();
            usergoals.setGoal(goal);
            usergoals.setGoalInitiationId(gini.getGoalInitiationId());
            usergoals.setCreatedBy(user.getUserName());
            usergoals.setCreatedDate(new Date());
            usergoals.setModifiable(goal.getModifiable());
            userGoalsList.add(usergoals);
          }
        }
      }
      logger.info("userIdList" + userIdList.toString());
      this.goaldao.initateGoalSubmit(userGoalsList, userIdList);
    }
    catch (Exception e)
    {
      logger.info("error on initiateGoalsSubmit", e);
    }
  }
  
  public List<GoalKraKpi> getKpiListByGoalKra(long goalKraId)
  {
    return this.goaldao.getKpiListByGoalKra(goalKraId);
  }
  
  public void tranfromToUserGoals(UserGoals usergoals, GoalKra gkra)
  {
    usergoals.setKraId(gkra.getKraId());
    usergoals.setKraName(gkra.getKraName());
    usergoals.setKradesc(gkra.getKradesc());
    usergoals.setKraWeight(gkra.getKraWeight());
    usergoals.setIsTrack(gkra.getIsTrack());
    usergoals.setTrackStartDate(gkra.getTrackStartDate());
    usergoals.setTrackEndDate(gkra.getTrackEndDate());
    usergoals.setTrackingFreqency(gkra.getTrackingFreqency());
    usergoals.setParentKraId(gkra.getParentKraId());
    usergoals.setKramodifiable(gkra.getModifiable());
  }
  
  public void lovPopulateGoalInitiate(GoalForm gform, User user, HttpServletRequest request)
  {
    gform.setDesignationList(BOFactory.getLovBO().getAllDesignations(user.getSuper_user_key()));
    
    List orgList = BOFactory.getLovBO().getAllOrganization(user.getSuper_user_key());
    gform.setOrganizationList(orgList);
    
    List deptlist = new ArrayList();
    gform.setDepartmentList(deptlist);
    if (gform.getOrgId() > 0L)
    {
      deptlist = BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(gform.getOrgId()));
      gform.setDepartmentList(deptlist);
    }
    gform.setTimePeriodList(getAllTimePeriod());
    
    Set users = BOFactory.getUserBO().getUsers(gform.getOrgId(), gform.getDepartmentId(), gform.getDesignationId());
    gform.setUsersset(users);
    
    List useridList = new ArrayList();
    Set set1 = users;
    Iterator itr = set1.iterator();
    while (itr.hasNext())
    {
      User user1 = (User)itr.next();
      useridList.add(Long.valueOf(user1.getUserId()));
    }
    gform.setUseridListVal(useridList);
  }
  
  public List goalListsByOrgDeptTimePeriodDesignation(long orgId, long departmentId, long designationId, long timePeriodId)
  {
    return this.goaldao.goalListsByOrgDeptTimePeriodDesignation(orgId, departmentId, designationId, timePeriodId);
  }
  
  public List userGoalsList(String userId)
  {
    if ((!StringUtils.isNullOrEmpty(userId)) && (!userId.equals("0")) && (!userId.equals("null"))) {
      return this.goaldao.userGoalsList(new Long(userId).longValue());
    }
    return null;
  }
  
  public List userGoalsList(String userId, Long timeperiod)
  {
    if (timeperiod == null) {
      return null;
    }
    if ((!StringUtils.isNullOrEmpty(userId)) && (!userId.equals("0")) && (!userId.equals("null"))) {
      return this.goaldao.userGoalsList(new Long(userId).longValue(), timeperiod.longValue());
    }
    return null;
  }
  
  public Map getOrgGoalsForPagination(long orgId, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.goaldao.getOrgGoalsForPagination(orgId, pageSize, startIndex, dir_str, sort_str);
  }
  
  public UserGoals getUserGoal(long userGoalId)
  {
    return this.goaldao.getUserGoal(userGoalId);
  }
  
  public List getAllTimePeriod()
  {
    return this.goaldao.getAllTimePeriod();
  }
  
  public String getTimePeriodValue(Long timePeriodId)
  {
    if (timePeriodId == null) {
      return "";
    }
    TimePeriod tp = this.goaldao.getTimePeriod(timePeriodId.longValue());
    if (tp != null) {
      return tp.getStartenddate();
    }
    return "";
  }
  
  public GoalKra saveGoalKra(GoalKra goalkra)
  {
    return this.goaldao.saveGoalKra(goalkra);
  }
  
  public UserGoals saveUserGoalWithKpi(UserGoals goal, List<UserGoalsKpi> ukpiList)
  {
    return this.goaldao.saveUserGoalWithKpi(goal, ukpiList);
  }
  
  public GoalInitiation saveGoalInitiation(GoalInitiation gini)
  {
    return this.goaldao.saveGoalInitiation(gini);
  }
  
  public void saveGoalKraKpi(List<GoalKraKpi> gkpiList)
  {
    this.goaldao.saveGoalKraKpi(gkpiList);
  }
  
  public List<GoalKra> getGoalKraListByGoal(long goalId)
  {
    return this.goaldao.getGoalKraListByGoal(goalId);
  }
  
  public GoalDAO getGoaldao()
  {
    return this.goaldao;
  }
  
  public void setGoaldao(GoalDAO goaldao)
  {
    this.goaldao = goaldao;
  }
}
