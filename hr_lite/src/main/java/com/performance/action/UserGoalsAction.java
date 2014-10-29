package com.performance.action;

import com.action.CommonAction;
import com.bean.User;
import com.bo.BOFactory;
import com.bo.UserBO;
import com.performance.bean.UserGoals;
import com.performance.bean.UserGoalsKpi;
import com.performance.bo.GoalBO;
import com.performance.bo.KRABO;
import com.performance.form.UserGoalsForm;
import com.util.StringUtils;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class UserGoalsAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(UserGoalsAction.class);
  
  public ActionForward editUserGoalKRA(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside editUserGoalKRA method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user1 = (User)request.getSession().getAttribute("user_data");
    String usergoalkraid = request.getParameter("usergoalkraid");
    
    UserGoalsForm uForm = (UserGoalsForm)form;
    if (!StringUtils.isNullOrEmpty(usergoalkraid))
    {
      UserGoals ugoal = BOFactory.getGoalBO().getUserGoal(new Long(usergoalkraid).longValue());
      uForm.fromValue(ugoal, request);
      

      uForm.setKpiList(ugoal.getKpiList());
    }
    uForm.setUserGoalId(new Long(usergoalkraid).longValue());
    uForm.setKraList(BOFactory.getKRABO().getAllKRAs());
    

    return mapping.findForward("editUserGoalKRA");
  }
  
  public ActionForward modifykra(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside modifykra method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user1 = (User)request.getSession().getAttribute("user_data");
    

    UserGoalsForm uForm = (UserGoalsForm)form;
    List<UserGoalsKpi> ukpiList = new ArrayList();
    setUserGoalsKpi(ukpiList, request, uForm.getUserGoalId());
    
    UserGoals ugoal = BOFactory.getGoalBO().getUserGoal(uForm.getUserGoalId());
    uForm.toValue(ugoal, request);
    

    BOFactory.getGoalBO().saveUserGoalWithKpi(ugoal, ukpiList);
    
    ugoal = BOFactory.getGoalBO().getUserGoal(uForm.getUserGoalId());
    
    uForm.setKpiList(ugoal.getKpiList());
    uForm.setKraList(BOFactory.getKRABO().getAllKRAs());
    uForm.setUserGoalId(ugoal.getUserGoalId());
    request.setAttribute("usergoalmodified", "yes");
    return mapping.findForward("editUserGoalKRA");
  }
  
  public ActionForward sendForReview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside sendForReview method");
    String userId = request.getParameter("userId");
    logger.info("Inside sendForReview method" + userId);
    User user1 = (User)request.getSession().getAttribute("user_data");
    UserGoalsForm uForm = (UserGoalsForm)form;
    if (!StringUtils.isNullOrEmpty(userId))
    {
      BOFactory.getUserBO();User user = UserBO.getUserFullNameEmailAndUserId(new Long(userId.trim()).longValue());
      uForm.setUserId(user.getUserId());
      uForm.setUserName(user.getFirstName() + " " + user.getLastName());
    }
    return mapping.findForward("sendForReview");
  }
  
  private void setUserGoalsKpi(List<UserGoalsKpi> ukpiList, HttpServletRequest request, long userGoalId)
  {
    for (int i = 1; i < 50; i++)
    {
      String kpi = request.getParameter("kpi_" + i);
      String kpimeasure = request.getParameter("kpimeasure_" + i);
      if (!StringUtils.isNullOrEmpty(kpi))
      {
        logger.info("kpi:" + kpi);
        
        UserGoalsKpi ukpi = new UserGoalsKpi();
        ukpi.setKpiName(kpi);
        ukpi.setKraMeasure(kpimeasure);
        ukpi.setUserGoalId(userGoalId);
        ukpiList.add(ukpi);
      }
    }
  }
}
