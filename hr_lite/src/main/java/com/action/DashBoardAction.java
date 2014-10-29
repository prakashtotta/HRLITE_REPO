package com.action;

import com.bean.DashBoardConfig;
import com.bean.DashBoardReport;
import com.bean.ReportNames;
import com.bean.User;
import com.bo.BOFactory;
import com.bo.ReportTXBO;
import com.bo.UserBO;
import com.common.AppContextUtil;
import com.dao.ReportDAO;
import com.form.DashboardForm;
import com.resources.Constant;
import com.util.StringUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.context.ApplicationContext;

public class DashBoardAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(DashBoardAction.class);
  
  public ActionForward getwidgetcode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside getwidgetcode method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user = (User)request.getSession().getAttribute("user_data");
    if ((!StringUtils.isNullOrEmpty(user.getPackagetaken())) && (Constant.isPackageContainFunction(user.getPackagetaken(), "websiteitegrate"))) {
      return mapping.findForward("getwidgetcode");
    }
    return mapping.findForward("upgradeplan");
  }
  
  public ActionForward dashboardlist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside dashboardlist method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    return mapping.findForward("dashboardlist");
  }
  
  public ActionForward configureDashBoard(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside configureDashBoard method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user = (User)request.getSession().getAttribute("user_data");
    List widgetList = BOFactory.getUserBO().getWidgets(user.getUserId());
    DashboardForm dashform = (DashboardForm)form;
    dashform.setWidgetList(widgetList);
    Map widgetmap = new HashMap();
    for (int i = 0; i < widgetList.size(); i++)
    {
      DashBoardConfig dc = (DashBoardConfig)widgetList.get(i);
      widgetmap.put(dc.getWidgetCode(), dc.getStatus());
    }
    dashform.setWidgetMap(widgetmap);
    return mapping.findForward("configureDashBoard");
  }
  
  public ActionForward saveconfigureDashBoard(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside configureDashBoard method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    User user = (User)request.getSession().getAttribute("user_data");
    Map inputmap = new HashMap();
    BOFactory.getUserBO().setupDashBoard(user, request);
    

    List widgetList = BOFactory.getUserBO().getWidgets(user.getUserId());
    DashboardForm dashform = (DashboardForm)form;
    dashform.setWidgetList(widgetList);
    Map widgetmap = new HashMap();
    for (int i = 0; i < widgetList.size(); i++)
    {
      DashBoardConfig dc = (DashBoardConfig)widgetList.get(i);
      widgetmap.put(dc.getWidgetCode(), dc.getStatus());
    }
    dashform.setWidgetMap(widgetmap);
    request.setAttribute("isupdated", "yes");
    return mapping.findForward("configureDashBoard");
  }
  
  public ActionForward mydashboardsetup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside mydashboardsetup method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String userId = request.getParameter("userId");
    logger.info("userId >> " + userId);
    DashboardForm dashform = (DashboardForm)form;
    List reportList = ReportDAO.getAllReports();
    List<ReportNames> reportListNew = new ArrayList();
    
    List dashboardList = ReportDAO.getAllDashBoardReportByUserId(new Long(userId).longValue());
    dashform.setDashboardReportList(dashboardList);
    


    String[] reports = new String[dashboardList.size()];
    for (int i = 0; i < dashboardList.size(); i++)
    {
      DashBoardReport dash = (DashBoardReport)dashboardList.get(i);
      reports[i] = String.valueOf(dash.getReport().getReportId());
    }
    dashform.setReports(reports);
    List dlistids = new ArrayList();
    for (int j = 0; j < dashboardList.size(); j++)
    {
      DashBoardReport dash = (DashBoardReport)dashboardList.get(j);
      dlistids.add(Integer.valueOf(dash.getReport().getReportId()));
    }
    for (int i = 0; i < reportList.size(); i++)
    {
      ReportNames reportnames = (ReportNames)reportList.get(i);
      if (!dlistids.contains(Integer.valueOf(reportnames.getReportId()))) {
        reportListNew.add(reportnames);
      }
    }
    dashform.setReportList(reportListNew);
    dashform.setUserid(new Long(userId).longValue());
    return mapping.findForward("mydashboardsetup");
  }
  
  public ActionForward mydashboardsetupsubmit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside mydashboardsetupsubmit method");
    User user = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    DashboardForm dashform = (DashboardForm)form;
    String userId = request.getParameter("userId");
    String todata = request.getParameter("todata");
    logger.info("todata >> " + todata);
    if (!StringUtils.isNullOrEmpty(todata)) {
      dashform.setReports(todata.split(","));
    }
    if ((dashform.getReports() != null) && (dashform.getReports().length >= 9))
    {
      request.setAttribute("exceeddasboard", "yes");
      request.setAttribute("mydashboardsetup", "no");
    }
    else
    {
      ApplicationContext appContext = AppContextUtil.getAppcontext();
      ReportTXBO reporttxbo = (ReportTXBO)appContext.getBean("reporttxBoProxy");
      reporttxbo.updateDashboardReports(dashform.getReports(), user, new Long(userId).longValue());
      


      request.setAttribute("mydashboardsetup", "yes");
    }
    List reportList = ReportDAO.getAllReports();
    List<ReportNames> reportListNew = new ArrayList();
    
    List dashboardList = ReportDAO.getAllDashBoardReportByUserId(new Long(userId).longValue());
    dashform.setDashboardReportList(dashboardList);
    


    String[] reports = new String[dashboardList.size()];
    for (int i = 0; i < dashboardList.size(); i++)
    {
      DashBoardReport dash = (DashBoardReport)dashboardList.get(i);
      reports[i] = String.valueOf(dash.getReport().getReportId());
    }
    dashform.setReports(reports);
    List dlistids = new ArrayList();
    for (int j = 0; j < dashboardList.size(); j++)
    {
      DashBoardReport dash = (DashBoardReport)dashboardList.get(j);
      dlistids.add(Integer.valueOf(dash.getReport().getReportId()));
    }
    for (int i = 0; i < reportList.size(); i++)
    {
      ReportNames reportnames = (ReportNames)reportList.get(i);
      if (!dlistids.contains(Integer.valueOf(reportnames.getReportId()))) {
        reportListNew.add(reportnames);
      }
    }
    dashform.setReportList(reportListNew);
    

    dashform.setReports(reports);
    return mapping.findForward("mydashboardsetup");
  }
}
