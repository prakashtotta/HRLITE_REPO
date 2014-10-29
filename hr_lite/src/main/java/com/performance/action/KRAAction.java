package com.performance.action;

import com.action.CommonAction;
import com.bean.User;
import com.bo.BOFactory;
import com.performance.bean.GoalKraKpi;
import com.performance.bean.Kra;
import com.performance.bean.KraKPI;
import com.performance.bo.KRABO;
import com.performance.form.KRAForm;
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

public class KRAAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(KRAAction.class);
  
  public ActionForward kraList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside kraList method");
    
    return mapping.findForward("kraList");
  }
  
  public ActionForward searchKRAListPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchKRAListPage method");
    

    User user1 = (User)request.getSession().getAttribute("user_data");
    

    String results_str = request.getParameter("results");
    String startIndex_str = request.getParameter("startIndex");
    String sort_str = request.getParameter("sort");
    String dir_str = request.getParameter("dir");
    


    String data = BOFactory.getKRABO().searchKRAListPage(user1, results_str, startIndex_str, dir_str, sort_str);
    


    request.setAttribute("data", data);
    return mapping.findForward("searchKRAListPage");
  }
  
  public ActionForward createKraWithGoal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside createKraWithGoal method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user1 = (User)request.getSession().getAttribute("user_data");
    String goalId = request.getParameter("goalId");
    String typekra = request.getParameter("typekra");
    String kraid = request.getParameter("kraid");
    KRAForm kForm = (KRAForm)form;
    if ((!StringUtils.isNullOrEmpty(typekra)) && (typekra.equalsIgnoreCase("true")))
    {
      request.setAttribute("isexisting", "yes");
      if (!StringUtils.isNullOrEmpty(kraid))
      {
        Kra esitingkra = BOFactory.getKRABO().getKRA(kForm.getKraId());
        kForm.fromValue(esitingkra, request);
        
        List kpiList = BOFactory.getKRABO().getKPIListByKra(kForm.getKraId());
        kForm.setKpiList(kpiList);
      }
    }
    else
    {
      request.setAttribute("isexisting", "no");
      kForm.clear();
    }
    kForm.setKraList(BOFactory.getKRABO().getAllKRAs());
    kForm.setGoalId(new Long(goalId).longValue());
    


    return mapping.findForward("createKraWithGoal");
  }
  
  public ActionForward saveKraWithGoal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveKraWithGoal method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user1 = (User)request.getSession().getAttribute("user_data");
    String goalId = request.getParameter("goalId");
    String typekra = request.getParameter("typekra");
    KRAForm kForm = (KRAForm)form;
    List gkpiList = new ArrayList();
    setKRAKpi(gkpiList, request);
    kForm.setKpiList(gkpiList);
    


    BOFactory.getKRABO().saveKraWithGoal(typekra, goalId, kForm, user1, request);
    

    kForm.setKraList(BOFactory.getKRABO().getAllKRAs());
    kForm.setGoalId(new Long(goalId).longValue());
    
    request.setAttribute("kraadded", "yes");
    
    return mapping.findForward("createKraWithGoal");
  }
  
  private void setGoalKpi(List gkpiList, HttpServletRequest request)
  {
    for (int i = 1; i < 50; i++)
    {
      String kpi = request.getParameter("kpi_" + i);
      String kpimeasure = request.getParameter("kpimeasure_" + i);
      if (!StringUtils.isNullOrEmpty(kpi))
      {
        logger.info("kpi:" + kpi);
        
        GoalKraKpi gkrakpi = new GoalKraKpi();
        gkrakpi.setKpiName(kpi);
        gkrakpi.setKraMeasure(kpimeasure);
        
        gkpiList.add(gkrakpi);
      }
    }
  }
  
  private void setKRAKpi(List gkpiList, HttpServletRequest request)
  {
    for (int i = 1; i < 50; i++)
    {
      String kpi = request.getParameter("kpi_" + i);
      String kpimeasure = request.getParameter("kpimeasure_" + i);
      if (!StringUtils.isNullOrEmpty(kpi))
      {
        logger.info("kpi:" + kpi);
        
        KraKPI krakpi = new KraKPI();
        krakpi.setKpiName(kpi);
        krakpi.setKraMeasure(kpimeasure);
        
        gkpiList.add(krakpi);
      }
    }
  }
}
