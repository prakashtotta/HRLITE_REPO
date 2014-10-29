package com.action;

import com.bean.User;
import com.bean.lov.KeyValue;
import com.bean.screensetting.ApplicantScreenSettings;
import com.dao.ScreenSettingsDAO;
import com.form.ConfigureColumnsForm;
import com.resources.Constant;
import com.util.StringUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ConfigureColumnsAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(ConfigureColumnsAction.class);
  
  public ActionForward configureColumnsHriringMgr(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside configureColumnsHriringMgr method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ConfigureColumnsForm confform = (ConfigureColumnsForm)form;
    
    ApplicantScreenSettings appsettings = ScreenSettingsDAO.getApplicationScreenSettings(user1.getUserId(), "APP_SEARCH_SCREEN_HIRING_MGR");
    List fromColumnsList = new ArrayList();
    List toColumnsList = new ArrayList();
    setFromToCoulmnList(appsettings, fromColumnsList, toColumnsList, user1, "APP_SEARCH_SCREEN_HIRING_MGR");
    confform.setFromColumnsList(fromColumnsList);
    confform.setToColumnsList(toColumnsList);
    
    return mapping.findForward("configureColumnsHriringMgr");
  }
  
  public ActionForward configureColumnsApplicantSummary(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside configureColumnsApplicantSummary method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ConfigureColumnsForm confform = (ConfigureColumnsForm)form;
    
    ApplicantScreenSettings appsettings = ScreenSettingsDAO.getApplicationScreenSettings(user1.getUserId(), "APPLICANT_SUMMARY_SCREEN");
    List fromColumnsList = new ArrayList();
    List toColumnsList = new ArrayList();
    setFromToCoulmnList(appsettings, fromColumnsList, toColumnsList, user1, "APPLICANT_SUMMARY_SCREEN");
    confform.setFromColumnsList(fromColumnsList);
    confform.setToColumnsList(toColumnsList);
    
    return mapping.findForward("configureColumnsApplicantSummary");
  }
  
  public ActionForward configureColumnsAllApplicants(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside configureColumnsAllApplicants method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ConfigureColumnsForm confform = (ConfigureColumnsForm)form;
    
    ApplicantScreenSettings appsettings = ScreenSettingsDAO.getApplicationScreenSettings(user1.getUserId(), "ALL_APP_SEARCH_SCREEN");
    List fromColumnsList = new ArrayList();
    List toColumnsList = new ArrayList();
    setFromToCoulmnList(appsettings, fromColumnsList, toColumnsList, user1, "ALL_APP_SEARCH_SCREEN");
    confform.setFromColumnsList(fromColumnsList);
    confform.setToColumnsList(toColumnsList);
    
    return mapping.findForward("configureColumnsAllApplicants");
  }
  
  public ActionForward configureColumnsGeneric(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside configureColumnsGeneric method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    String screenname = request.getParameter("screenname");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ConfigureColumnsForm confform = (ConfigureColumnsForm)form;
    confform.setScreenName(screenname);
    ApplicantScreenSettings appsettings = ScreenSettingsDAO.getApplicationScreenSettings(user1.getUserId(), screenname);
    List fromColumnsList = new ArrayList();
    List toColumnsList = new ArrayList();
    setFromToCoulmnList(appsettings, fromColumnsList, toColumnsList, user1, screenname);
    confform.setFromColumnsList(fromColumnsList);
    confform.setToColumnsList(toColumnsList);
    
    return mapping.findForward("configureColumnsGeneric");
  }
  
  public ActionForward configureColumnsGenericSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside configureColumnsGenericSave method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    String screenname = request.getParameter("screenname");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ConfigureColumnsForm confform = (ConfigureColumnsForm)form;
    logger.info(confform.getTodata());
    String selecteddata = confform.getTodata();
    String fromsd = confform.getFromdata();
    ApplicantScreenSettings appsettings = ScreenSettingsDAO.getApplicationScreenSettings(user1.getUserId(), screenname);
    if (appsettings == null)
    {
      appsettings = new ApplicantScreenSettings();
      appsettings.setUserId(user1.getUserId());
      appsettings.setScreenName(screenname);
    }
    if ((!StringUtils.isNullOrEmpty(selecteddata)) && (!StringUtils.isNullOrEmpty(selecteddata)))
    {
      selecteddata = selecteddata.substring(0, selecteddata.length() - 1);
      
      appsettings.setToList(selecteddata);
    }
    else
    {
      appsettings.setToList(null);
    }
    if ((!StringUtils.isNullOrEmpty(fromsd)) && (!StringUtils.isNullOrEmpty(fromsd)))
    {
      fromsd = fromsd.substring(0, fromsd.length() - 1);
      
      appsettings.setFromList(fromsd);
    }
    else
    {
      appsettings.setFromList(null);
    }
    appsettings = ScreenSettingsDAO.updateApplicantScreenSettings(appsettings);
    List fromColumnsList = new ArrayList();
    List toColumnsList = new ArrayList();
    setFromToCoulmnList(appsettings, fromColumnsList, toColumnsList, user1, screenname);
    confform.setFromColumnsList(fromColumnsList);
    confform.setToColumnsList(toColumnsList);
    confform.setScreenName(screenname);
    request.setAttribute("configurecolumns", "yes");
    return mapping.findForward("configureColumnsGeneric");
  }
  
  public ActionForward configureColumnsOnBoardApplicants(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside configureColumnsOnBoardApplicants method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ConfigureColumnsForm confform = (ConfigureColumnsForm)form;
    
    ApplicantScreenSettings appsettings = ScreenSettingsDAO.getApplicationScreenSettings(user1.getUserId(), "ON_BOARDING_SEARCH_SCREEN");
    List fromColumnsList = new ArrayList();
    List toColumnsList = new ArrayList();
    setFromToCoulmnList(appsettings, fromColumnsList, toColumnsList, user1, "ON_BOARDING_SEARCH_SCREEN");
    confform.setFromColumnsList(fromColumnsList);
    confform.setToColumnsList(toColumnsList);
    
    return mapping.findForward("configureColumnsOnBoardApplicants");
  }
  
  public ActionForward configureColumnsOnBoardApplicantsSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside configureColumnsOnBoardApplicantsSave method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ConfigureColumnsForm confform = (ConfigureColumnsForm)form;
    logger.info(confform.getTodata());
    String selecteddata = confform.getTodata();
    String fromsd = confform.getFromdata();
    ApplicantScreenSettings appsettings = ScreenSettingsDAO.getApplicationScreenSettings(user1.getUserId(), "ON_BOARDING_SEARCH_SCREEN");
    if (appsettings == null)
    {
      appsettings = new ApplicantScreenSettings();
      appsettings.setUserId(user1.getUserId());
      appsettings.setScreenName("ON_BOARDING_SEARCH_SCREEN");
    }
    if ((!StringUtils.isNullOrEmpty(selecteddata)) && (!StringUtils.isNullOrEmpty(selecteddata)))
    {
      selecteddata = selecteddata.substring(0, selecteddata.length() - 1);
      
      appsettings.setToList(selecteddata);
    }
    else
    {
      appsettings.setToList(null);
    }
    if ((!StringUtils.isNullOrEmpty(fromsd)) && (!StringUtils.isNullOrEmpty(fromsd)))
    {
      fromsd = fromsd.substring(0, fromsd.length() - 1);
      
      appsettings.setFromList(fromsd);
    }
    else
    {
      appsettings.setFromList(null);
    }
    appsettings = ScreenSettingsDAO.updateApplicantScreenSettings(appsettings);
    List fromColumnsList = new ArrayList();
    List toColumnsList = new ArrayList();
    setFromToCoulmnList(appsettings, fromColumnsList, toColumnsList, user1, "ON_BOARDING_SEARCH_SCREEN");
    confform.setFromColumnsList(fromColumnsList);
    confform.setToColumnsList(toColumnsList);
    request.setAttribute("configurecolumns", "yes");
    return mapping.findForward("configureColumnsOnBoardApplicants");
  }
  
  public ActionForward configureColumnsAllApplicantsSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside configureColumnsAllApplicantsSave method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ConfigureColumnsForm confform = (ConfigureColumnsForm)form;
    logger.info(confform.getTodata());
    String selecteddata = confform.getTodata();
    String fromsd = confform.getFromdata();
    ApplicantScreenSettings appsettings = ScreenSettingsDAO.getApplicationScreenSettings(user1.getUserId(), "ALL_APP_SEARCH_SCREEN");
    if (appsettings == null)
    {
      appsettings = new ApplicantScreenSettings();
      appsettings.setUserId(user1.getUserId());
      appsettings.setScreenName("ALL_APP_SEARCH_SCREEN");
    }
    if ((!StringUtils.isNullOrEmpty(selecteddata)) && (!StringUtils.isNullOrEmpty(selecteddata)))
    {
      selecteddata = selecteddata.substring(0, selecteddata.length() - 1);
      
      appsettings.setToList(selecteddata);
    }
    else
    {
      appsettings.setToList(null);
    }
    if ((!StringUtils.isNullOrEmpty(fromsd)) && (!StringUtils.isNullOrEmpty(fromsd)))
    {
      fromsd = fromsd.substring(0, fromsd.length() - 1);
      
      appsettings.setFromList(fromsd);
    }
    else
    {
      appsettings.setFromList(null);
    }
    appsettings = ScreenSettingsDAO.updateApplicantScreenSettings(appsettings);
    List fromColumnsList = new ArrayList();
    List toColumnsList = new ArrayList();
    setFromToCoulmnList(appsettings, fromColumnsList, toColumnsList, user1, "ALL_APP_SEARCH_SCREEN");
    confform.setFromColumnsList(fromColumnsList);
    confform.setToColumnsList(toColumnsList);
    request.setAttribute("configurecolumns", "yes");
    return mapping.findForward("configureColumnsAllApplicants");
  }
  
  public ActionForward configureColumnsAllRequisitionSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside configureColumnsAllRequisitionSave method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ConfigureColumnsForm confform = (ConfigureColumnsForm)form;
    logger.info(confform.getTodata());
    String selecteddata = confform.getTodata();
    String fromsd = confform.getFromdata();
    ApplicantScreenSettings appsettings = ScreenSettingsDAO.getApplicationScreenSettings(user1.getUserId(), "ALL_REQUISION_SCREEN");
    if (appsettings == null)
    {
      appsettings = new ApplicantScreenSettings();
      appsettings.setUserId(user1.getUserId());
      appsettings.setScreenName("ALL_REQUISION_SCREEN");
    }
    if ((!StringUtils.isNullOrEmpty(selecteddata)) && (!StringUtils.isNullOrEmpty(selecteddata)))
    {
      selecteddata = selecteddata.substring(0, selecteddata.length() - 1);
      
      appsettings.setToList(selecteddata);
    }
    else
    {
      appsettings.setToList(null);
    }
    if ((!StringUtils.isNullOrEmpty(fromsd)) && (!StringUtils.isNullOrEmpty(fromsd)))
    {
      fromsd = fromsd.substring(0, fromsd.length() - 1);
      
      appsettings.setFromList(fromsd);
    }
    else
    {
      appsettings.setFromList(null);
    }
    appsettings = ScreenSettingsDAO.updateApplicantScreenSettings(appsettings);
    List fromColumnsList = new ArrayList();
    List toColumnsList = new ArrayList();
    setFromToCoulmnList(appsettings, fromColumnsList, toColumnsList, user1, "ALL_REQUISION_SCREEN");
    confform.setFromColumnsList(fromColumnsList);
    confform.setToColumnsList(toColumnsList);
    request.setAttribute("configurecolumns", "yes");
    return mapping.findForward("configureColumnsAllRequision");
  }
  
  public ActionForward configureColumnsVendorSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside configureColumnsVendorSave method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ConfigureColumnsForm confform = (ConfigureColumnsForm)form;
    logger.info(confform.getTodata());
    String selecteddata = confform.getTodata();
    String fromsd = confform.getFromdata();
    ApplicantScreenSettings appsettings = ScreenSettingsDAO.getApplicationScreenSettings(user1.getUserId(), "VENDOR_SCREEN");
    if (appsettings == null)
    {
      appsettings = new ApplicantScreenSettings();
      appsettings.setUserId(user1.getUserId());
      appsettings.setScreenName("VENDOR_SCREEN");
    }
    if ((!StringUtils.isNullOrEmpty(selecteddata)) && (!StringUtils.isNullOrEmpty(selecteddata)))
    {
      selecteddata = selecteddata.substring(0, selecteddata.length() - 1);
      
      appsettings.setToList(selecteddata);
    }
    else
    {
      appsettings.setToList(null);
    }
    if ((!StringUtils.isNullOrEmpty(fromsd)) && (!StringUtils.isNullOrEmpty(fromsd)))
    {
      fromsd = fromsd.substring(0, fromsd.length() - 1);
      
      appsettings.setFromList(fromsd);
    }
    else
    {
      appsettings.setFromList(null);
    }
    appsettings = ScreenSettingsDAO.updateApplicantScreenSettings(appsettings);
    List fromColumnsList = new ArrayList();
    List toColumnsList = new ArrayList();
    setFromToCoulmnList(appsettings, fromColumnsList, toColumnsList, user1, "VENDOR_SCREEN");
    confform.setFromColumnsList(fromColumnsList);
    confform.setToColumnsList(toColumnsList);
    request.setAttribute("configurecolumns", "yes");
    return mapping.findForward("configureColumnsVendor");
  }
  
  public ActionForward configureColumnsReferralSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside configureColumnsReferralSave method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ConfigureColumnsForm confform = (ConfigureColumnsForm)form;
    logger.info(confform.getTodata());
    String selecteddata = confform.getTodata();
    String fromsd = confform.getFromdata();
    ApplicantScreenSettings appsettings = ScreenSettingsDAO.getApplicationScreenSettings(user1.getUserId(), "REFERRAL_SCREEN");
    if (appsettings == null)
    {
      appsettings = new ApplicantScreenSettings();
      appsettings.setUserId(user1.getUserId());
      appsettings.setScreenName("REFERRAL_SCREEN");
    }
    if ((!StringUtils.isNullOrEmpty(selecteddata)) && (!StringUtils.isNullOrEmpty(selecteddata)))
    {
      selecteddata = selecteddata.substring(0, selecteddata.length() - 1);
      
      appsettings.setToList(selecteddata);
    }
    else
    {
      appsettings.setToList(null);
    }
    if ((!StringUtils.isNullOrEmpty(fromsd)) && (!StringUtils.isNullOrEmpty(fromsd)))
    {
      fromsd = fromsd.substring(0, fromsd.length() - 1);
      
      appsettings.setFromList(fromsd);
    }
    else
    {
      appsettings.setFromList(null);
    }
    appsettings = ScreenSettingsDAO.updateApplicantScreenSettings(appsettings);
    List fromColumnsList = new ArrayList();
    List toColumnsList = new ArrayList();
    setFromToCoulmnList(appsettings, fromColumnsList, toColumnsList, user1, "REFERRAL_SCREEN");
    confform.setFromColumnsList(fromColumnsList);
    confform.setToColumnsList(toColumnsList);
    request.setAttribute("configurecolumns", "yes");
    return mapping.findForward("configureColumnsReferral");
  }
  
  public ActionForward configureColumnsUserSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside configureColumnsUserSave method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ConfigureColumnsForm confform = (ConfigureColumnsForm)form;
    logger.info(confform.getTodata());
    String selecteddata = confform.getTodata();
    String fromsd = confform.getFromdata();
    ApplicantScreenSettings appsettings = ScreenSettingsDAO.getApplicationScreenSettings(user1.getUserId(), "USER_SCREEN");
    if (appsettings == null)
    {
      appsettings = new ApplicantScreenSettings();
      appsettings.setUserId(user1.getUserId());
      appsettings.setScreenName("USER_SCREEN");
    }
    if ((!StringUtils.isNullOrEmpty(selecteddata)) && (!StringUtils.isNullOrEmpty(selecteddata)))
    {
      selecteddata = selecteddata.substring(0, selecteddata.length() - 1);
      
      appsettings.setToList(selecteddata);
    }
    else
    {
      appsettings.setToList(null);
    }
    if ((!StringUtils.isNullOrEmpty(fromsd)) && (!StringUtils.isNullOrEmpty(fromsd)))
    {
      fromsd = fromsd.substring(0, fromsd.length() - 1);
      
      appsettings.setFromList(fromsd);
    }
    else
    {
      appsettings.setFromList(null);
    }
    appsettings = ScreenSettingsDAO.updateApplicantScreenSettings(appsettings);
    List fromColumnsList = new ArrayList();
    List toColumnsList = new ArrayList();
    setFromToCoulmnList(appsettings, fromColumnsList, toColumnsList, user1, "USER_SCREEN");
    confform.setFromColumnsList(fromColumnsList);
    confform.setToColumnsList(toColumnsList);
    request.setAttribute("configurecolumns", "yes");
    return mapping.findForward("configureColumnsUser");
  }
  
  public ActionForward configureColumnsMyRequisitionSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside configureColumnsMyRequisitionSave method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ConfigureColumnsForm confform = (ConfigureColumnsForm)form;
    logger.info(confform.getTodata());
    String selecteddata = confform.getTodata();
    String fromsd = confform.getFromdata();
    ApplicantScreenSettings appsettings = ScreenSettingsDAO.getApplicationScreenSettings(user1.getUserId(), "MY_REQUISITION_SCREEN");
    if (appsettings == null)
    {
      appsettings = new ApplicantScreenSettings();
      appsettings.setUserId(user1.getUserId());
      appsettings.setScreenName("MY_REQUISITION_SCREEN");
    }
    if ((!StringUtils.isNullOrEmpty(selecteddata)) && (!StringUtils.isNullOrEmpty(selecteddata)))
    {
      selecteddata = selecteddata.substring(0, selecteddata.length() - 1);
      
      appsettings.setToList(selecteddata);
    }
    else
    {
      appsettings.setToList(null);
    }
    if ((!StringUtils.isNullOrEmpty(fromsd)) && (!StringUtils.isNullOrEmpty(fromsd)))
    {
      fromsd = fromsd.substring(0, fromsd.length() - 1);
      
      appsettings.setFromList(fromsd);
    }
    else
    {
      appsettings.setFromList(null);
    }
    appsettings = ScreenSettingsDAO.updateApplicantScreenSettings(appsettings);
    List fromColumnsList = new ArrayList();
    List toColumnsList = new ArrayList();
    setFromToCoulmnList(appsettings, fromColumnsList, toColumnsList, user1, "MY_REQUISITION_SCREEN");
    confform.setFromColumnsList(fromColumnsList);
    confform.setToColumnsList(toColumnsList);
    request.setAttribute("configurecolumns", "yes");
    return mapping.findForward("configureColumnsMyRequision");
  }
  
  public ActionForward configureColumnsRecruiter(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside configureColumnsRecruiter method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ConfigureColumnsForm confform = (ConfigureColumnsForm)form;
    
    ApplicantScreenSettings appsettings = ScreenSettingsDAO.getApplicationScreenSettings(user1.getUserId(), "APP_SEARCH_SCREEN_RECRUITER");
    List fromColumnsList = new ArrayList();
    List toColumnsList = new ArrayList();
    setFromToCoulmnList(appsettings, fromColumnsList, toColumnsList, user1, "APP_SEARCH_SCREEN_RECRUITER");
    confform.setFromColumnsList(fromColumnsList);
    confform.setToColumnsList(toColumnsList);
    
    return mapping.findForward("configureColumnsRecruiter");
  }
  
  public ActionForward configureColumnsRecruiterSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside configureColumnsRecruiterSave method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ConfigureColumnsForm confform = (ConfigureColumnsForm)form;
    logger.info(confform.getTodata());
    String selecteddata = confform.getTodata();
    String fromsd = confform.getFromdata();
    ApplicantScreenSettings appsettings = ScreenSettingsDAO.getApplicationScreenSettings(user1.getUserId(), "APP_SEARCH_SCREEN_RECRUITER");
    if (appsettings == null)
    {
      appsettings = new ApplicantScreenSettings();
      appsettings.setUserId(user1.getUserId());
      appsettings.setScreenName("APP_SEARCH_SCREEN_RECRUITER");
    }
    if ((!StringUtils.isNullOrEmpty(selecteddata)) && (!StringUtils.isNullOrEmpty(selecteddata)))
    {
      selecteddata = selecteddata.substring(0, selecteddata.length() - 1);
      
      appsettings.setToList(selecteddata);
    }
    else
    {
      appsettings.setToList(null);
    }
    if ((!StringUtils.isNullOrEmpty(fromsd)) && (!StringUtils.isNullOrEmpty(fromsd)))
    {
      fromsd = fromsd.substring(0, fromsd.length() - 1);
      
      appsettings.setFromList(fromsd);
    }
    else
    {
      appsettings.setFromList(null);
    }
    appsettings = ScreenSettingsDAO.updateApplicantScreenSettings(appsettings);
    List fromColumnsList = new ArrayList();
    List toColumnsList = new ArrayList();
    setFromToCoulmnList(appsettings, fromColumnsList, toColumnsList, user1, "APP_SEARCH_SCREEN_RECRUITER");
    confform.setFromColumnsList(fromColumnsList);
    confform.setToColumnsList(toColumnsList);
    request.setAttribute("configurecolumns", "yes");
    return mapping.findForward("configureColumnsRecruiter");
  }
  
  public ActionForward configureColumnsHriringMgrSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside configureColumnsHriringMgrSave method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ConfigureColumnsForm confform = (ConfigureColumnsForm)form;
    logger.info(confform.getTodata());
    String selecteddata = confform.getTodata();
    String fromsd = confform.getFromdata();
    ApplicantScreenSettings appsettings = ScreenSettingsDAO.getApplicationScreenSettings(user1.getUserId(), "APP_SEARCH_SCREEN_HIRING_MGR");
    if (appsettings == null)
    {
      appsettings = new ApplicantScreenSettings();
      appsettings.setUserId(user1.getUserId());
      appsettings.setScreenName("APP_SEARCH_SCREEN_HIRING_MGR");
    }
    if ((!StringUtils.isNullOrEmpty(selecteddata)) && (!StringUtils.isNullOrEmpty(selecteddata)))
    {
      selecteddata = selecteddata.substring(0, selecteddata.length() - 1);
      
      appsettings.setToList(selecteddata);
    }
    else
    {
      appsettings.setToList(null);
    }
    if ((!StringUtils.isNullOrEmpty(fromsd)) && (!StringUtils.isNullOrEmpty(fromsd)))
    {
      fromsd = fromsd.substring(0, fromsd.length() - 1);
      
      appsettings.setFromList(fromsd);
    }
    else
    {
      appsettings.setFromList(null);
    }
    appsettings = ScreenSettingsDAO.updateApplicantScreenSettings(appsettings);
    List fromColumnsList = new ArrayList();
    List toColumnsList = new ArrayList();
    setFromToCoulmnList(appsettings, fromColumnsList, toColumnsList, user1, "APP_SEARCH_SCREEN_HIRING_MGR");
    confform.setFromColumnsList(fromColumnsList);
    confform.setToColumnsList(toColumnsList);
    request.setAttribute("configurecolumns", "yes");
    return mapping.findForward("configureColumnsHriringMgr");
  }
  
  public ActionForward configureColumnsApplicantSummarySave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside configureColumnsApplicantSummarySave method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ConfigureColumnsForm confform = (ConfigureColumnsForm)form;
    logger.info(confform.getTodata());
    String selecteddata = confform.getTodata();
    String fromsd = confform.getFromdata();
    ApplicantScreenSettings appsettings = ScreenSettingsDAO.getApplicationScreenSettings(user1.getUserId(), "APPLICANT_SUMMARY_SCREEN");
    if (appsettings == null)
    {
      appsettings = new ApplicantScreenSettings();
      appsettings.setUserId(user1.getUserId());
      appsettings.setScreenName("APPLICANT_SUMMARY_SCREEN");
    }
    if ((!StringUtils.isNullOrEmpty(selecteddata)) && (!StringUtils.isNullOrEmpty(selecteddata)))
    {
      selecteddata = selecteddata.substring(0, selecteddata.length() - 1);
      
      appsettings.setToList(selecteddata);
    }
    else
    {
      appsettings.setToList(null);
    }
    if ((!StringUtils.isNullOrEmpty(fromsd)) && (!StringUtils.isNullOrEmpty(fromsd)))
    {
      fromsd = fromsd.substring(0, fromsd.length() - 1);
      
      appsettings.setFromList(fromsd);
    }
    else
    {
      appsettings.setFromList(null);
    }
    appsettings = ScreenSettingsDAO.updateApplicantScreenSettings(appsettings);
    List fromColumnsList = new ArrayList();
    List toColumnsList = new ArrayList();
    setFromToCoulmnList(appsettings, fromColumnsList, toColumnsList, user1, "APPLICANT_SUMMARY_SCREEN");
    confform.setFromColumnsList(fromColumnsList);
    confform.setToColumnsList(toColumnsList);
    request.setAttribute("configurecolumns", "yes");
    return mapping.findForward("configureColumnsApplicantSummary");
  }
  
  public ActionForward configureColumnsUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside configureColumnsUser method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ConfigureColumnsForm confform = (ConfigureColumnsForm)form;
    ApplicantScreenSettings appsettings = ScreenSettingsDAO.getApplicationScreenSettings(user1.getUserId(), "USER_SCREEN");
    List fromColumnsList = new ArrayList();
    List toColumnsList = new ArrayList();
    setFromToCoulmnList(appsettings, fromColumnsList, toColumnsList, user1, "USER_SCREEN");
    confform.setFromColumnsList(fromColumnsList);
    confform.setToColumnsList(toColumnsList);
    return mapping.findForward("configureColumnsUser");
  }
  
  public ActionForward configureColumnsVendor(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside configureColumnsVendor method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ConfigureColumnsForm confform = (ConfigureColumnsForm)form;
    ApplicantScreenSettings appsettings = ScreenSettingsDAO.getApplicationScreenSettings(user1.getUserId(), "VENDOR_SCREEN");
    List fromColumnsList = new ArrayList();
    List toColumnsList = new ArrayList();
    setFromToCoulmnList(appsettings, fromColumnsList, toColumnsList, user1, "VENDOR_SCREEN");
    confform.setFromColumnsList(fromColumnsList);
    confform.setToColumnsList(toColumnsList);
    return mapping.findForward("configureColumnsVendor");
  }
  
  public ActionForward configureColumnsReferral(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside configureColumnsReferral method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ConfigureColumnsForm confform = (ConfigureColumnsForm)form;
    ApplicantScreenSettings appsettings = ScreenSettingsDAO.getApplicationScreenSettings(user1.getUserId(), "REFERRAL_SCREEN");
    List fromColumnsList = new ArrayList();
    List toColumnsList = new ArrayList();
    setFromToCoulmnList(appsettings, fromColumnsList, toColumnsList, user1, "REFERRAL_SCREEN");
    confform.setFromColumnsList(fromColumnsList);
    confform.setToColumnsList(toColumnsList);
    return mapping.findForward("configureColumnsReferral");
  }
  
  public ActionForward configureColumnsAllRequision(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside configureColumnsAllRequision method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ConfigureColumnsForm confform = (ConfigureColumnsForm)form;
    ApplicantScreenSettings appsettings = ScreenSettingsDAO.getApplicationScreenSettings(user1.getUserId(), "ALL_REQUISION_SCREEN");
    List fromColumnsList = new ArrayList();
    List toColumnsList = new ArrayList();
    setFromToCoulmnList(appsettings, fromColumnsList, toColumnsList, user1, "ALL_REQUISION_SCREEN");
    confform.setFromColumnsList(fromColumnsList);
    confform.setToColumnsList(toColumnsList);
    return mapping.findForward("configureColumnsAllRequision");
  }
  
  public ActionForward configureColumnsRequisionTemplate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside configureColumnsRequisionTemplate method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ConfigureColumnsForm confform = (ConfigureColumnsForm)form;
    ApplicantScreenSettings appsettings = ScreenSettingsDAO.getApplicationScreenSettings(user1.getUserId(), "REQUISION_TEMPLATE_SCREEN");
    List fromColumnsList = new ArrayList();
    List toColumnsList = new ArrayList();
    setFromToCoulmnList(appsettings, fromColumnsList, toColumnsList, user1, "REQUISION_TEMPLATE_SCREEN");
    confform.setFromColumnsList(fromColumnsList);
    confform.setToColumnsList(toColumnsList);
    return mapping.findForward("configureColumnsRequisionTemplate");
  }
  
  public ActionForward configureColumnsAllRequisitionTemplateSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside configureColumnsAllRequisitionTemplateSave method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ConfigureColumnsForm confform = (ConfigureColumnsForm)form;
    logger.info(confform.getTodata());
    String selecteddata = confform.getTodata();
    String fromsd = confform.getFromdata();
    ApplicantScreenSettings appsettings = ScreenSettingsDAO.getApplicationScreenSettings(user1.getUserId(), "REQUISION_TEMPLATE_SCREEN");
    if (appsettings == null)
    {
      appsettings = new ApplicantScreenSettings();
      appsettings.setUserId(user1.getUserId());
      appsettings.setScreenName("REQUISION_TEMPLATE_SCREEN");
    }
    if ((!StringUtils.isNullOrEmpty(selecteddata)) && (!StringUtils.isNullOrEmpty(selecteddata)))
    {
      selecteddata = selecteddata.substring(0, selecteddata.length() - 1);
      
      appsettings.setToList(selecteddata);
    }
    else
    {
      appsettings.setToList(null);
    }
    if ((!StringUtils.isNullOrEmpty(fromsd)) && (!StringUtils.isNullOrEmpty(fromsd)))
    {
      fromsd = fromsd.substring(0, fromsd.length() - 1);
      
      appsettings.setFromList(fromsd);
    }
    else
    {
      appsettings.setFromList(null);
    }
    appsettings = ScreenSettingsDAO.updateApplicantScreenSettings(appsettings);
    List fromColumnsList = new ArrayList();
    List toColumnsList = new ArrayList();
    setFromToCoulmnList(appsettings, fromColumnsList, toColumnsList, user1, "REQUISION_TEMPLATE_SCREEN");
    confform.setFromColumnsList(fromColumnsList);
    confform.setToColumnsList(toColumnsList);
    request.setAttribute("configurecolumns", "yes");
    return mapping.findForward("configureColumnsRequisionTemplate");
  }
  
  public ActionForward configureColumnsMyRequision(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside configureColumnsMyRequision method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ConfigureColumnsForm confform = (ConfigureColumnsForm)form;
    ApplicantScreenSettings appsettings = ScreenSettingsDAO.getApplicationScreenSettings(user1.getUserId(), "MY_REQUISITION_SCREEN");
    List fromColumnsList = new ArrayList();
    List toColumnsList = new ArrayList();
    setFromToCoulmnList(appsettings, fromColumnsList, toColumnsList, user1, "MY_REQUISITION_SCREEN");
    confform.setFromColumnsList(fromColumnsList);
    confform.setToColumnsList(toColumnsList);
    return mapping.findForward("configureColumnsMyRequision");
  }
  
  public ActionForward configureColumnsAgencyRedumption(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside configureColumnsAgencyRedumption method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ConfigureColumnsForm confform = (ConfigureColumnsForm)form;
    ApplicantScreenSettings appsettings = ScreenSettingsDAO.getApplicationScreenSettings(user1.getUserId(), "AGENCY_REDEMPTION__SCREEN");
    List fromColumnsList = new ArrayList();
    List toColumnsList = new ArrayList();
    setFromToCoulmnList(appsettings, fromColumnsList, toColumnsList, user1, "AGENCY_REDEMPTION__SCREEN");
    confform.setFromColumnsList(fromColumnsList);
    confform.setToColumnsList(toColumnsList);
    return mapping.findForward("configureColumnsAgencyRedemption");
  }
  
  public ActionForward configureColumnsReferralRedumption(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside configureColumnsReferralRedumption method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ConfigureColumnsForm confform = (ConfigureColumnsForm)form;
    ApplicantScreenSettings appsettings = ScreenSettingsDAO.getApplicationScreenSettings(user1.getUserId(), "REFERRAL_REDEMPTION_SCREEN");
    List fromColumnsList = new ArrayList();
    List toColumnsList = new ArrayList();
    setFromToCoulmnList(appsettings, fromColumnsList, toColumnsList, user1, "REFERRAL_REDEMPTION_SCREEN");
    confform.setFromColumnsList(fromColumnsList);
    confform.setToColumnsList(toColumnsList);
    return mapping.findForward("configureColumnsReferralRedumption");
  }
  
  public ActionForward configureColumnsOnboardTaskDefination(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside configureColumnsOnboardTaskDefination method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ConfigureColumnsForm confform = (ConfigureColumnsForm)form;
    ApplicantScreenSettings appsettings = ScreenSettingsDAO.getApplicationScreenSettings(user1.getUserId(), "ONBOARD_TASK_DEF_SCREEN");
    List fromColumnsList = new ArrayList();
    List toColumnsList = new ArrayList();
    setFromToCoulmnList(appsettings, fromColumnsList, toColumnsList, user1, "ONBOARD_TASK_DEF_SCREEN");
    confform.setFromColumnsList(fromColumnsList);
    confform.setToColumnsList(toColumnsList);
    return mapping.findForward("configureColumnsOnboardTask");
  }
  
  public ActionForward configureColumnsTalentPool(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside configureColumnsTalentPool method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ConfigureColumnsForm confform = (ConfigureColumnsForm)form;
    ApplicantScreenSettings appsettings = ScreenSettingsDAO.getApplicationScreenSettings(user1.getUserId(), "TALENT_POOL_SCREEN");
    List fromColumnsList = new ArrayList();
    List toColumnsList = new ArrayList();
    setFromToCoulmnList(appsettings, fromColumnsList, toColumnsList, user1, "TALENT_POOL_SCREEN");
    confform.setFromColumnsList(fromColumnsList);
    confform.setToColumnsList(toColumnsList);
    return mapping.findForward("configureColumnsTalentPool");
  }
  
  public ActionForward configureColumnsOnboardTaskDefSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside configureColumnsOnboardTaskDefSave method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ConfigureColumnsForm confform = (ConfigureColumnsForm)form;
    logger.info(confform.getTodata());
    String selecteddata = confform.getTodata();
    String fromsd = confform.getFromdata();
    ApplicantScreenSettings appsettings = ScreenSettingsDAO.getApplicationScreenSettings(user1.getUserId(), "ONBOARD_TASK_DEF_SCREEN");
    if (appsettings == null)
    {
      appsettings = new ApplicantScreenSettings();
      appsettings.setUserId(user1.getUserId());
      appsettings.setScreenName("ONBOARD_TASK_DEF_SCREEN");
    }
    if ((!StringUtils.isNullOrEmpty(selecteddata)) && (!StringUtils.isNullOrEmpty(selecteddata)))
    {
      selecteddata = selecteddata.substring(0, selecteddata.length() - 1);
      
      appsettings.setToList(selecteddata);
    }
    else
    {
      appsettings.setToList(null);
    }
    if ((!StringUtils.isNullOrEmpty(fromsd)) && (!StringUtils.isNullOrEmpty(fromsd)))
    {
      fromsd = fromsd.substring(0, fromsd.length() - 1);
      
      appsettings.setFromList(fromsd);
    }
    else
    {
      appsettings.setFromList(null);
    }
    appsettings = ScreenSettingsDAO.updateApplicantScreenSettings(appsettings);
    List fromColumnsList = new ArrayList();
    List toColumnsList = new ArrayList();
    setFromToCoulmnList(appsettings, fromColumnsList, toColumnsList, user1, "ONBOARD_TASK_DEF_SCREEN");
    confform.setFromColumnsList(fromColumnsList);
    confform.setToColumnsList(toColumnsList);
    request.setAttribute("configurecolumns", "yes");
    return mapping.findForward("configureColumnsTalentPool");
  }
  
  public ActionForward configureColumnsTalentPoolSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside configureColumnsTalentPoolSave method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ConfigureColumnsForm confform = (ConfigureColumnsForm)form;
    logger.info(confform.getTodata());
    String selecteddata = confform.getTodata();
    String fromsd = confform.getFromdata();
    ApplicantScreenSettings appsettings = ScreenSettingsDAO.getApplicationScreenSettings(user1.getUserId(), "TALENT_POOL_SCREEN");
    if (appsettings == null)
    {
      appsettings = new ApplicantScreenSettings();
      appsettings.setUserId(user1.getUserId());
      appsettings.setScreenName("TALENT_POOL_SCREEN");
    }
    if ((!StringUtils.isNullOrEmpty(selecteddata)) && (!StringUtils.isNullOrEmpty(selecteddata)))
    {
      selecteddata = selecteddata.substring(0, selecteddata.length() - 1);
      
      appsettings.setToList(selecteddata);
    }
    else
    {
      appsettings.setToList(null);
    }
    if ((!StringUtils.isNullOrEmpty(fromsd)) && (!StringUtils.isNullOrEmpty(fromsd)))
    {
      fromsd = fromsd.substring(0, fromsd.length() - 1);
      
      appsettings.setFromList(fromsd);
    }
    else
    {
      appsettings.setFromList(null);
    }
    appsettings = ScreenSettingsDAO.updateApplicantScreenSettings(appsettings);
    List fromColumnsList = new ArrayList();
    List toColumnsList = new ArrayList();
    setFromToCoulmnList(appsettings, fromColumnsList, toColumnsList, user1, "TALENT_POOL_SCREEN");
    confform.setFromColumnsList(fromColumnsList);
    confform.setToColumnsList(toColumnsList);
    request.setAttribute("configurecolumns", "yes");
    return mapping.findForward("configureColumnsTalentPool");
  }
  
  public ActionForward configureColumnsAgencyRedumptionSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside configureColumnsAgencyRedumptionSave method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ConfigureColumnsForm confform = (ConfigureColumnsForm)form;
    logger.info(confform.getTodata());
    String selecteddata = confform.getTodata();
    String fromsd = confform.getFromdata();
    ApplicantScreenSettings appsettings = ScreenSettingsDAO.getApplicationScreenSettings(user1.getUserId(), "AGENCY_REDEMPTION__SCREEN");
    if (appsettings == null)
    {
      appsettings = new ApplicantScreenSettings();
      appsettings.setUserId(user1.getUserId());
      appsettings.setScreenName("AGENCY_REDEMPTION__SCREEN");
    }
    if ((!StringUtils.isNullOrEmpty(selecteddata)) && (!StringUtils.isNullOrEmpty(selecteddata)))
    {
      selecteddata = selecteddata.substring(0, selecteddata.length() - 1);
      
      appsettings.setToList(selecteddata);
    }
    else
    {
      appsettings.setToList(null);
    }
    if ((!StringUtils.isNullOrEmpty(fromsd)) && (!StringUtils.isNullOrEmpty(fromsd)))
    {
      fromsd = fromsd.substring(0, fromsd.length() - 1);
      
      appsettings.setFromList(fromsd);
    }
    else
    {
      appsettings.setFromList(null);
    }
    appsettings = ScreenSettingsDAO.updateApplicantScreenSettings(appsettings);
    List fromColumnsList = new ArrayList();
    List toColumnsList = new ArrayList();
    setFromToCoulmnList(appsettings, fromColumnsList, toColumnsList, user1, "AGENCY_REDEMPTION__SCREEN");
    confform.setFromColumnsList(fromColumnsList);
    confform.setToColumnsList(toColumnsList);
    request.setAttribute("configurecolumns", "yes");
    return mapping.findForward("configureColumnsAgencyRedemption");
  }
  
  public ActionForward configureColumnsReferralRedumptionSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside configureColumnsReferralRedumptionSave method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ConfigureColumnsForm confform = (ConfigureColumnsForm)form;
    logger.info(confform.getTodata());
    String selecteddata = confform.getTodata();
    String fromsd = confform.getFromdata();
    ApplicantScreenSettings appsettings = ScreenSettingsDAO.getApplicationScreenSettings(user1.getUserId(), "REFERRAL_REDEMPTION_SCREEN");
    if (appsettings == null)
    {
      appsettings = new ApplicantScreenSettings();
      appsettings.setUserId(user1.getUserId());
      appsettings.setScreenName("REFERRAL_REDEMPTION_SCREEN");
    }
    if ((!StringUtils.isNullOrEmpty(selecteddata)) && (!StringUtils.isNullOrEmpty(selecteddata)))
    {
      selecteddata = selecteddata.substring(0, selecteddata.length() - 1);
      
      appsettings.setToList(selecteddata);
    }
    else
    {
      appsettings.setToList(null);
    }
    if ((!StringUtils.isNullOrEmpty(fromsd)) && (!StringUtils.isNullOrEmpty(fromsd)))
    {
      fromsd = fromsd.substring(0, fromsd.length() - 1);
      
      appsettings.setFromList(fromsd);
    }
    else
    {
      appsettings.setFromList(null);
    }
    appsettings = ScreenSettingsDAO.updateApplicantScreenSettings(appsettings);
    List fromColumnsList = new ArrayList();
    List toColumnsList = new ArrayList();
    setFromToCoulmnList(appsettings, fromColumnsList, toColumnsList, user1, "REFERRAL_REDEMPTION_SCREEN");
    confform.setFromColumnsList(fromColumnsList);
    confform.setToColumnsList(toColumnsList);
    request.setAttribute("configurecolumns", "yes");
    return mapping.findForward("configureColumnsReferralRedumption");
  }
  
  private void setFromToCoulmnList(ApplicantScreenSettings appsettings, List fromColumnsList, List toColumnsList, User user1, String screenName)
  {
    if (appsettings == null)
    {
      if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("USER_SCREEN")))
      {
        String strtemp = Constant.getValue("user.page.default.columns.firstpage");
        String[] columns = StringUtils.tokenize(strtemp, ",");
        List tocolumnonly = new ArrayList();
        for (int i = 0; i < columns.length; i++)
        {
          String resoucekey = "user.page.table.configutaion." + columns[i];
          KeyValue k = new KeyValue();
          k.setKey(columns[i]);
          k.setValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
          toColumnsList.add(k);
          tocolumnonly.add(columns[i]);
        }
        String strtempnew = Constant.getValue("user.page.default.columns.firstpage");
        String[] columnsnew = StringUtils.tokenize(strtempnew, ",");
        List allcolumnsList = new ArrayList();
        if (columnsnew != null) {
          Collections.addAll(allcolumnsList, columnsnew);
        }
        allcolumnsList.removeAll(tocolumnonly);
        for (int j = 0; j < allcolumnsList.size(); j++)
        {
          String resoucekey = "user.page.table.configutaion." + (String)allcolumnsList.get(j);
          KeyValue k = new KeyValue();
          k.setKey((String)allcolumnsList.get(j));
          k.setValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
          fromColumnsList.add(k);
        }
      }
      if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("VENDOR_SCREEN")))
      {
        String strtemp = Constant.getValue("vendor.page.default.columns.firstpage");
        String[] columns = StringUtils.tokenize(strtemp, ",");
        List tocolumnonly = new ArrayList();
        for (int i = 0; i < columns.length; i++)
        {
          String resoucekey = "vendor.page.table.configutaion." + columns[i];
          KeyValue k = new KeyValue();
          k.setKey(columns[i]);
          k.setValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
          toColumnsList.add(k);
          tocolumnonly.add(columns[i]);
        }
        String strtempnew = Constant.getValue("vendor.page.default.columns.firstpage");
        String[] columnsnew = StringUtils.tokenize(strtempnew, ",");
        List allcolumnsList = new ArrayList();
        if (columnsnew != null) {
          Collections.addAll(allcolumnsList, columnsnew);
        }
        allcolumnsList.removeAll(tocolumnonly);
        for (int j = 0; j < allcolumnsList.size(); j++)
        {
          String resoucekey = "vendor.page.table.configutaion." + (String)allcolumnsList.get(j);
          KeyValue k = new KeyValue();
          k.setKey((String)allcolumnsList.get(j));
          k.setValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
          fromColumnsList.add(k);
        }
      }
      if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("REFERRAL_SCREEN")))
      {
        String strtemp = Constant.getValue("referral.page.default.columns");
        String[] columns = StringUtils.tokenize(strtemp, ",");
        List tocolumnonly = new ArrayList();
        for (int i = 0; i < columns.length; i++)
        {
          String resoucekey = "referral.page.table.configutaion." + columns[i];
          KeyValue k = new KeyValue();
          k.setKey(columns[i]);
          k.setValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
          toColumnsList.add(k);
          tocolumnonly.add(columns[i]);
        }
        String strtempnew = Constant.getValue("referral.page.default.columns");
        String[] columnsnew = StringUtils.tokenize(strtempnew, ",");
        List allcolumnsList = new ArrayList();
        if (columnsnew != null) {
          Collections.addAll(allcolumnsList, columnsnew);
        }
        allcolumnsList.removeAll(tocolumnonly);
        for (int j = 0; j < allcolumnsList.size(); j++)
        {
          String resoucekey = "referral.page.table.configutaion." + (String)allcolumnsList.get(j);
          KeyValue k = new KeyValue();
          k.setKey((String)allcolumnsList.get(j));
          k.setValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
          fromColumnsList.add(k);
        }
      }
      if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("ALL_REQUISION_SCREEN")))
      {
        String strtemp = Constant.getValue("requisition.page.default.columns.firstpage");
        String[] columns = StringUtils.tokenize(strtemp, ",");
        List tocolumnonly = new ArrayList();
        for (int i = 0; i < columns.length; i++)
        {
          String resoucekey = "requisition.page.table.configutaion." + columns[i];
          KeyValue k = new KeyValue();
          k.setKey(columns[i]);
          k.setValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
          toColumnsList.add(k);
          tocolumnonly.add(columns[i]);
        }
        String strtempnew = Constant.getValue("requisition.page.default.columns");
        String[] columnsnew = StringUtils.tokenize(strtempnew, ",");
        List allcolumnsList = new ArrayList();
        if (columnsnew != null) {
          Collections.addAll(allcolumnsList, columnsnew);
        }
        allcolumnsList.removeAll(tocolumnonly);
        for (int j = 0; j < allcolumnsList.size(); j++)
        {
          String resoucekey = "requisition.page.table.configutaion." + (String)allcolumnsList.get(j);
          KeyValue k = new KeyValue();
          k.setKey((String)allcolumnsList.get(j));
          k.setValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
          fromColumnsList.add(k);
        }
      }
      if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("MY_REQUISITION_SCREEN")))
      {
        String strtemp = Constant.getValue("myrequisition.page.default.columns.firstpage");
        String[] columns = StringUtils.tokenize(strtemp, ",");
        List tocolumnonly = new ArrayList();
        for (int i = 0; i < columns.length; i++)
        {
          String resoucekey = "requisition.page.table.configutaion." + columns[i];
          KeyValue k = new KeyValue();
          k.setKey(columns[i]);
          k.setValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
          toColumnsList.add(k);
          tocolumnonly.add(columns[i]);
        }
        String strtempnew = Constant.getValue("myrequisition.page.default.columns");
        String[] columnsnew = StringUtils.tokenize(strtempnew, ",");
        List allcolumnsList = new ArrayList();
        if (columnsnew != null) {
          Collections.addAll(allcolumnsList, columnsnew);
        }
        allcolumnsList.removeAll(tocolumnonly);
        for (int j = 0; j < allcolumnsList.size(); j++)
        {
          String resoucekey = "requisition.page.table.configutaion." + (String)allcolumnsList.get(j);
          KeyValue k = new KeyValue();
          k.setKey((String)allcolumnsList.get(j));
          k.setValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
          fromColumnsList.add(k);
        }
      }
      if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("TALENT_POOL_SCREEN")))
      {
        String strtemp = Constant.getValue("talentpool.page.default.columns");
        String[] columns = StringUtils.tokenize(strtemp, ",");
        for (int i = 0; i < columns.length; i++)
        {
          logger.info("1234" + columns[i]);
          String resoucekey = "talentpool.page.table.configuration." + columns[i];
          KeyValue k = new KeyValue();
          k.setKey(columns[i]);
          k.setValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
          toColumnsList.add(k);
        }
      }
      if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("APP_SEARCH_SCREEN_HIRING_MGR")))
      {
        String strtemp = Constant.getValue("applicant.columns.hrmgr.firstpage");
        String[] columns = StringUtils.tokenize(strtemp, ",");
        List tocolumnonly = new ArrayList();
        for (int i = 0; i < columns.length; i++)
        {
          String resoucekey = "aquisition.applicant.configutaion." + columns[i];
          KeyValue k = new KeyValue();
          k.setKey(columns[i]);
          k.setValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
          toColumnsList.add(k);
          tocolumnonly.add(columns[i]);
        }
        String strtempnew = Constant.getValue("applicant.page.hiring.mgr.default.columns");
        String[] columnsnew = StringUtils.tokenize(strtempnew, ",");
        List allcolumnsList = new ArrayList();
        if (columnsnew != null) {
          Collections.addAll(allcolumnsList, columnsnew);
        }
        allcolumnsList.removeAll(tocolumnonly);
        for (int j = 0; j < allcolumnsList.size(); j++)
        {
          String resoucekey = "aquisition.applicant.configutaion." + (String)allcolumnsList.get(j);
          KeyValue k = new KeyValue();
          k.setKey((String)allcolumnsList.get(j));
          k.setValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
          fromColumnsList.add(k);
        }
      }
      if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("APPLICANT_SUMMARY_SCREEN")))
      {
        String strtemp = Constant.getValue("applicant.columns.applicant.summary.firstpage");
        String[] columns = StringUtils.tokenize(strtemp, ",");
        List tocolumnonly = new ArrayList();
        for (int i = 0; i < columns.length; i++)
        {
          String resoucekey = "aquisition.applicant.configutaion." + columns[i];
          KeyValue k = new KeyValue();
          k.setKey(columns[i]);
          k.setValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
          toColumnsList.add(k);
          tocolumnonly.add(columns[i]);
        }
        String strtempnew = Constant.getValue("applicant.page.applicant.summary.default.columns");
        String[] columnsnew = StringUtils.tokenize(strtempnew, ",");
        List allcolumnsList = new ArrayList();
        if (columnsnew != null) {
          Collections.addAll(allcolumnsList, columnsnew);
        }
        allcolumnsList.removeAll(tocolumnonly);
        for (int j = 0; j < allcolumnsList.size(); j++)
        {
          String resoucekey = "aquisition.applicant.configutaion." + (String)allcolumnsList.get(j);
          KeyValue k = new KeyValue();
          k.setKey((String)allcolumnsList.get(j));
          k.setValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
          fromColumnsList.add(k);
        }
      }
      if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("APP_SEARCH_SCREEN_RECRUITER")))
      {
        String strtemp = Constant.getValue("applicant.columns.recruiter.firstpage");
        String[] columns = StringUtils.tokenize(strtemp, ",");
        List tocolumnonly = new ArrayList();
        for (int i = 0; i < columns.length; i++)
        {
          String resoucekey = "aquisition.applicant.configutaion." + columns[i];
          KeyValue k = new KeyValue();
          k.setKey(columns[i]);
          k.setValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
          toColumnsList.add(k);
          tocolumnonly.add(columns[i]);
        }
        String strtempnew = Constant.getValue("applicant.page.recruiter.default.columns");
        String[] columnsnew = StringUtils.tokenize(strtempnew, ",");
        List allcolumnsList = new ArrayList();
        if (columnsnew != null) {
          Collections.addAll(allcolumnsList, columnsnew);
        }
        allcolumnsList.removeAll(tocolumnonly);
        for (int j = 0; j < allcolumnsList.size(); j++)
        {
          String resoucekey = "aquisition.applicant.configutaion." + (String)allcolumnsList.get(j);
          KeyValue k = new KeyValue();
          k.setKey((String)allcolumnsList.get(j));
          k.setValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
          fromColumnsList.add(k);
        }
      }
      if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("ALL_APP_SEARCH_SCREEN")))
      {
        String strtemp = Constant.getValue("applicant.columns.all.firstpage");
        String[] columns = StringUtils.tokenize(strtemp, ",");
        List tocolumnonly = new ArrayList();
        for (int i = 0; i < columns.length; i++)
        {
          String resoucekey = "aquisition.applicant.configutaion." + columns[i];
          KeyValue k = new KeyValue();
          k.setKey(columns[i]);
          k.setValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
          toColumnsList.add(k);
          tocolumnonly.add(columns[i]);
        }
        String strtempnew = Constant.getValue("applicant.page.all.default.columns");
        String[] columnsnew = StringUtils.tokenize(strtempnew, ",");
        List allcolumnsList = new ArrayList();
        if (columnsnew != null) {
          Collections.addAll(allcolumnsList, columnsnew);
        }
        allcolumnsList.removeAll(tocolumnonly);
        for (int j = 0; j < allcolumnsList.size(); j++)
        {
          String resoucekey = "aquisition.applicant.configutaion." + (String)allcolumnsList.get(j);
          KeyValue k = new KeyValue();
          k.setKey((String)allcolumnsList.get(j));
          k.setValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
          fromColumnsList.add(k);
        }
      }
      if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("DASHBOARD_RECRUITER_ASSIGNED_REQUISTIONS")))
      {
        String strtemp = Constant.getValue("dashboard.recruiter.assigned.requistions.firstpage");
        String[] columns = StringUtils.tokenize(strtemp, ",");
        List tocolumnonly = new ArrayList();
        for (int i = 0; i < columns.length; i++)
        {
          String resoucekey = "requisition.page.table.configutaion." + columns[i];
          KeyValue k = new KeyValue();
          k.setKey(columns[i]);
          k.setValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
          toColumnsList.add(k);
          tocolumnonly.add(columns[i]);
        }
        String strtempnew = Constant.getValue("dashboard.recruiter.assigned.requistions");
        String[] columnsnew = StringUtils.tokenize(strtempnew, ",");
        List allcolumnsList = new ArrayList();
        if (columnsnew != null) {
          Collections.addAll(allcolumnsList, columnsnew);
        }
        allcolumnsList.removeAll(tocolumnonly);
        for (int j = 0; j < allcolumnsList.size(); j++)
        {
          String resoucekey = "requisition.page.table.configutaion." + (String)allcolumnsList.get(j);
          KeyValue k = new KeyValue();
          k.setKey((String)allcolumnsList.get(j));
          k.setValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
          fromColumnsList.add(k);
        }
      }
      if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("DASHBOARD_RECRUITER_IN_OFFER_PROCESS")))
      {
        String strtemp = Constant.getValue("dashboard.recruiter.applicants.offer.process.firstpage");
        String[] columns = StringUtils.tokenize(strtemp, ",");
        List tocolumnonly = new ArrayList();
        for (int i = 0; i < columns.length; i++)
        {
          String resoucekey = "aquisition.applicant.configutaion." + columns[i];
          KeyValue k = new KeyValue();
          k.setKey(columns[i]);
          k.setValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
          toColumnsList.add(k);
          tocolumnonly.add(columns[i]);
        }
        String strtempnew = Constant.getValue("dashboard.recruiter.applicants.offer.process");
        String[] columnsnew = StringUtils.tokenize(strtempnew, ",");
        List allcolumnsList = new ArrayList();
        if (columnsnew != null) {
          Collections.addAll(allcolumnsList, columnsnew);
        }
        allcolumnsList.removeAll(tocolumnonly);
        for (int j = 0; j < allcolumnsList.size(); j++)
        {
          String resoucekey = "aquisition.applicant.configutaion." + (String)allcolumnsList.get(j);
          KeyValue k = new KeyValue();
          k.setKey((String)allcolumnsList.get(j));
          k.setValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
          fromColumnsList.add(k);
        }
      }
      if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("DASHBOARD_RECRUITER_IN_JOINED_PROCESS")))
      {
        String strtemp = Constant.getValue("dashboard.recruiter.applicants.joining.process.firstpage");
        String[] columns = StringUtils.tokenize(strtemp, ",");
        List tocolumnonly = new ArrayList();
        for (int i = 0; i < columns.length; i++)
        {
          String resoucekey = "aquisition.applicant.configutaion." + columns[i];
          KeyValue k = new KeyValue();
          k.setKey(columns[i]);
          k.setValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
          toColumnsList.add(k);
          tocolumnonly.add(columns[i]);
        }
        String strtempnew = Constant.getValue("dashboard.recruiter.applicants.joining.process");
        String[] columnsnew = StringUtils.tokenize(strtempnew, ",");
        List allcolumnsList = new ArrayList();
        if (columnsnew != null) {
          Collections.addAll(allcolumnsList, columnsnew);
        }
        allcolumnsList.removeAll(tocolumnonly);
        for (int j = 0; j < allcolumnsList.size(); j++)
        {
          String resoucekey = "aquisition.applicant.configutaion." + (String)allcolumnsList.get(j);
          KeyValue k = new KeyValue();
          k.setKey((String)allcolumnsList.get(j));
          k.setValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
          fromColumnsList.add(k);
        }
      }
      if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("ON_BOARDING_SEARCH_SCREEN")))
      {
        String strtemp = Constant.getValue("applicant.columns.onboard.firstpage");
        String[] columns = StringUtils.tokenize(strtemp, ",");
        List tocolumnonly = new ArrayList();
        for (int i = 0; i < columns.length; i++)
        {
          String resoucekey = "aquisition.applicant.configutaion." + columns[i];
          KeyValue k = new KeyValue();
          k.setKey(columns[i]);
          k.setValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
          toColumnsList.add(k);
          tocolumnonly.add(columns[i]);
        }
        String strtempnew = Constant.getValue("onboarding.page.default.columns");
        String[] columnsnew = StringUtils.tokenize(strtempnew, ",");
        List allcolumnsList = new ArrayList();
        if (columnsnew != null) {
          Collections.addAll(allcolumnsList, columnsnew);
        }
        allcolumnsList.removeAll(tocolumnonly);
        for (int j = 0; j < allcolumnsList.size(); j++)
        {
          String resoucekey = "aquisition.applicant.configutaion." + (String)allcolumnsList.get(j);
          KeyValue k = new KeyValue();
          k.setKey((String)allcolumnsList.get(j));
          k.setValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
          fromColumnsList.add(k);
        }
      }
      if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("AGENCY_REDEMPTION__SCREEN")))
      {
        String strtemp = Constant.getValue("agencyredumption.default.columns");
        String[] columns = StringUtils.tokenize(strtemp, ",");
        for (int i = 0; i < columns.length; i++)
        {
          String resoucekey = "agencyredumption.page.table.configuration." + columns[i];
          KeyValue k = new KeyValue();
          k.setKey(columns[i]);
          k.setValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
          toColumnsList.add(k);
        }
      }
      if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("REFERRAL_REDEMPTION_SCREEN")))
      {
        String strtemp = Constant.getValue("referralredumption.default.columns");
        String[] columns = StringUtils.tokenize(strtemp, ",");
        for (int i = 0; i < columns.length; i++)
        {
          String resoucekey = "referralredumption.page.table.configuration." + columns[i];
          KeyValue k = new KeyValue();
          k.setKey(columns[i]);
          k.setValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
          toColumnsList.add(k);
        }
      }
      if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("ONBOARD_TASK_DEF_SCREEN")))
      {
        String strtemp = Constant.getValue("onboarding.taskdef.default.columns");
        String[] columns = StringUtils.tokenize(strtemp, ",");
        for (int i = 0; i < columns.length; i++)
        {
          String resoucekey = "onboarding.taskdef.page.table.configuration." + columns[i];
          KeyValue k = new KeyValue();
          k.setKey(columns[i]);
          k.setValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
          toColumnsList.add(k);
        }
      }
      if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("REQUISION_TEMPLATE_SCREEN")))
      {
        String strtemp = Constant.getValue("requisition.template.default.columns.firstpage");
        String[] columns = StringUtils.tokenize(strtemp, ",");
        List tocolumnonly = new ArrayList();
        for (int i = 0; i < columns.length; i++)
        {
          String resoucekey = "requisition.template.table.configutaion." + columns[i];
          KeyValue k = new KeyValue();
          k.setKey(columns[i]);
          k.setValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
          toColumnsList.add(k);
          tocolumnonly.add(columns[i]);
        }
        String strtempnew = Constant.getValue("requisition.template.default.columns");
        String[] columnsnew = StringUtils.tokenize(strtempnew, ",");
        List allcolumnsList = new ArrayList();
        if (columnsnew != null) {
          Collections.addAll(allcolumnsList, columnsnew);
        }
        allcolumnsList.removeAll(tocolumnonly);
        for (int j = 0; j < allcolumnsList.size(); j++)
        {
          String resoucekey = "requisition.template.table.configutaion." + (String)allcolumnsList.get(j);
          KeyValue k = new KeyValue();
          k.setKey((String)allcolumnsList.get(j));
          k.setValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
          fromColumnsList.add(k);
        }
      }
    }
    else if (((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("ALL_REQUISION_SCREEN"))) || (screenName.equals("MY_REQUISITION_SCREEN")) || (screenName.equals("DASHBOARD_RECRUITER_ASSIGNED_REQUISTIONS")) || (screenName.equals("TALENT_POOL_SCREEN")) || (screenName.equals("ONBOARD_TASK_DEF_SCREEN")) || (screenName.equals("USER_SCREEN")) || (screenName.equals("VENDOR_SCREEN")) || (screenName.equals("REFERRAL_SCREEN")) || (screenName.equals("AGENCY_REDEMPTION__SCREEN")) || (screenName.equals("REFERRAL_REDEMPTION_SCREEN")) || (screenName.equals("REQUISION_TEMPLATE_SCREEN")))
    {
      if (((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("ALL_REQUISION_SCREEN"))) || (screenName.equals("DASHBOARD_RECRUITER_ASSIGNED_REQUISTIONS")) || (screenName.equals("MY_REQUISITION_SCREEN")))
      {
        List toColumnsListOnlyAttribute = new ArrayList();
        List fromColumnsListOnlyAttribute = new ArrayList();
        String strtemp = appsettings.getToList();
        String[] columns = StringUtils.tokenize(strtemp, ",");
        if (columns != null) {
          for (int i = 0; i < columns.length; i++)
          {
            String resoucekey = "requisition.page.table.configutaion." + columns[i];
            KeyValue k = new KeyValue();
            k.setKey(columns[i]);
            k.setValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
            toColumnsList.add(k);
            toColumnsListOnlyAttribute.add(columns[i]);
          }
        }
        strtemp = appsettings.getFromList();
        columns = StringUtils.tokenize(strtemp, ",");
        if (columns != null) {
          for (int i = 0; i < columns.length; i++)
          {
            String resoucekey = "requisition.page.table.configutaion." + columns[i];
            KeyValue k = new KeyValue();
            k.setKey(columns[i]);
            k.setValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
            fromColumnsList.add(k);
            fromColumnsListOnlyAttribute.add(columns[i]);
          }
        }
      }
      if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("USER_SCREEN")))
      {
        List toColumnsListOnlyAttribute = new ArrayList();
        List fromColumnsListOnlyAttribute = new ArrayList();
        String strtemp = appsettings.getToList();
        String[] columns = StringUtils.tokenize(strtemp, ",");
        if (columns != null) {
          for (int i = 0; i < columns.length; i++)
          {
            String resoucekey = "user.page.table.configutaion." + columns[i];
            KeyValue k = new KeyValue();
            k.setKey(columns[i]);
            k.setValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
            toColumnsList.add(k);
            toColumnsListOnlyAttribute.add(columns[i]);
          }
        }
        strtemp = appsettings.getFromList();
        columns = StringUtils.tokenize(strtemp, ",");
        if (columns != null) {
          for (int i = 0; i < columns.length; i++)
          {
            String resoucekey = "user.page.table.configutaion." + columns[i];
            KeyValue k = new KeyValue();
            k.setKey(columns[i]);
            k.setValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
            fromColumnsList.add(k);
            fromColumnsListOnlyAttribute.add(columns[i]);
          }
        }
      }
      if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("VENDOR_SCREEN")))
      {
        List toColumnsListOnlyAttribute = new ArrayList();
        List fromColumnsListOnlyAttribute = new ArrayList();
        String strtemp = appsettings.getToList();
        String[] columns = StringUtils.tokenize(strtemp, ",");
        if (columns != null) {
          for (int i = 0; i < columns.length; i++)
          {
            String resoucekey = "vendor.page.table.configutaion." + columns[i];
            KeyValue k = new KeyValue();
            k.setKey(columns[i]);
            k.setValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
            toColumnsList.add(k);
            toColumnsListOnlyAttribute.add(columns[i]);
          }
        }
        strtemp = appsettings.getFromList();
        columns = StringUtils.tokenize(strtemp, ",");
        if (columns != null) {
          for (int i = 0; i < columns.length; i++)
          {
            String resoucekey = "vendor.page.table.configutaion." + columns[i];
            KeyValue k = new KeyValue();
            k.setKey(columns[i]);
            k.setValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
            fromColumnsList.add(k);
            fromColumnsListOnlyAttribute.add(columns[i]);
          }
        }
      }
      if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("REFERRAL_SCREEN")))
      {
        List toColumnsListOnlyAttribute = new ArrayList();
        List fromColumnsListOnlyAttribute = new ArrayList();
        String strtemp = appsettings.getToList();
        String[] columns = StringUtils.tokenize(strtemp, ",");
        if (columns != null) {
          for (int i = 0; i < columns.length; i++)
          {
            String resoucekey = "referral.page.table.configutaion." + columns[i];
            KeyValue k = new KeyValue();
            k.setKey(columns[i]);
            k.setValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
            toColumnsList.add(k);
            toColumnsListOnlyAttribute.add(columns[i]);
          }
        }
        strtemp = appsettings.getFromList();
        columns = StringUtils.tokenize(strtemp, ",");
        if (columns != null) {
          for (int i = 0; i < columns.length; i++)
          {
            String resoucekey = "referral.page.table.configutaion." + columns[i];
            KeyValue k = new KeyValue();
            k.setKey(columns[i]);
            k.setValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
            fromColumnsList.add(k);
            fromColumnsListOnlyAttribute.add(columns[i]);
          }
        }
      }
      if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("TALENT_POOL_SCREEN")))
      {
        List toColumnsListOnlyAttribute = new ArrayList();
        List fromColumnsListOnlyAttribute = new ArrayList();
        String strtemp = appsettings.getToList();
        String[] columns = StringUtils.tokenize(strtemp, ",");
        if (columns != null) {
          for (int i = 0; i < columns.length; i++)
          {
            String resoucekey = "talentpool.page.table.configuration." + columns[i];
            KeyValue k = new KeyValue();
            k.setKey(columns[i]);
            k.setValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
            toColumnsList.add(k);
            toColumnsListOnlyAttribute.add(columns[i]);
          }
        }
        strtemp = appsettings.getFromList();
        columns = StringUtils.tokenize(strtemp, ",");
        if (columns != null) {
          for (int i = 0; i < columns.length; i++)
          {
            String resoucekey = "talentpool.page.table.configuration." + columns[i];
            KeyValue k = new KeyValue();
            k.setKey(columns[i]);
            k.setValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
            fromColumnsList.add(k);
            fromColumnsListOnlyAttribute.add(columns[i]);
          }
        }
      }
      if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("ONBOARD_TASK_DEF_SCREEN")))
      {
        List toColumnsListOnlyAttribute = new ArrayList();
        List fromColumnsListOnlyAttribute = new ArrayList();
        String strtemp = appsettings.getToList();
        String[] columns = StringUtils.tokenize(strtemp, ",");
        if (columns != null) {
          for (int i = 0; i < columns.length; i++)
          {
            String resoucekey = "onboarding.taskdef.page.table.configuration." + columns[i];
            KeyValue k = new KeyValue();
            k.setKey(columns[i]);
            k.setValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
            toColumnsList.add(k);
            toColumnsListOnlyAttribute.add(columns[i]);
          }
        }
        strtemp = appsettings.getFromList();
        columns = StringUtils.tokenize(strtemp, ",");
        if (columns != null) {
          for (int i = 0; i < columns.length; i++)
          {
            String resoucekey = "onboarding.taskdef.page.table.configuration." + columns[i];
            KeyValue k = new KeyValue();
            k.setKey(columns[i]);
            k.setValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
            fromColumnsList.add(k);
            fromColumnsListOnlyAttribute.add(columns[i]);
          }
        }
      }
      if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("AGENCY_REDEMPTION__SCREEN")))
      {
        List toColumnsListOnlyAttribute = new ArrayList();
        List fromColumnsListOnlyAttribute = new ArrayList();
        String strtemp = appsettings.getToList();
        String[] columns = StringUtils.tokenize(strtemp, ",");
        if (columns != null) {
          for (int i = 0; i < columns.length; i++)
          {
            String resoucekey = "agencyredumption.page.table.configuration." + columns[i];
            KeyValue k = new KeyValue();
            k.setKey(columns[i]);
            k.setValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
            toColumnsList.add(k);
            toColumnsListOnlyAttribute.add(columns[i]);
          }
        }
        strtemp = appsettings.getFromList();
        columns = StringUtils.tokenize(strtemp, ",");
        if (columns != null) {
          for (int i = 0; i < columns.length; i++)
          {
            String resoucekey = "agencyredumption.page.table.configuration." + columns[i];
            KeyValue k = new KeyValue();
            k.setKey(columns[i]);
            k.setValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
            fromColumnsList.add(k);
            fromColumnsListOnlyAttribute.add(columns[i]);
          }
        }
      }
      if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("REFERRAL_REDEMPTION_SCREEN")))
      {
        List toColumnsListOnlyAttribute = new ArrayList();
        List fromColumnsListOnlyAttribute = new ArrayList();
        String strtemp = appsettings.getToList();
        String[] columns = StringUtils.tokenize(strtemp, ",");
        if (columns != null) {
          for (int i = 0; i < columns.length; i++)
          {
            String resoucekey = "referralredumption.page.table.configuration." + columns[i];
            KeyValue k = new KeyValue();
            k.setKey(columns[i]);
            k.setValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
            toColumnsList.add(k);
            toColumnsListOnlyAttribute.add(columns[i]);
          }
        }
        strtemp = appsettings.getFromList();
        columns = StringUtils.tokenize(strtemp, ",");
        if (columns != null) {
          for (int i = 0; i < columns.length; i++)
          {
            String resoucekey = "referralredumption.page.table.configuration." + columns[i];
            KeyValue k = new KeyValue();
            k.setKey(columns[i]);
            k.setValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
            fromColumnsList.add(k);
            fromColumnsListOnlyAttribute.add(columns[i]);
          }
        }
      }
      if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("REQUISION_TEMPLATE_SCREEN")))
      {
        List toColumnsListOnlyAttribute = new ArrayList();
        List fromColumnsListOnlyAttribute = new ArrayList();
        String strtemp = appsettings.getToList();
        String[] columns = StringUtils.tokenize(strtemp, ",");
        if (columns != null) {
          for (int i = 0; i < columns.length; i++)
          {
            String resoucekey = "requisition.page.table.configutaion." + columns[i];
            KeyValue k = new KeyValue();
            k.setKey(columns[i]);
            k.setValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
            toColumnsList.add(k);
            toColumnsListOnlyAttribute.add(columns[i]);
          }
        }
        strtemp = appsettings.getFromList();
        columns = StringUtils.tokenize(strtemp, ",");
        if (columns != null) {
          for (int i = 0; i < columns.length; i++)
          {
            String resoucekey = "requisition.page.table.configutaion." + columns[i];
            KeyValue k = new KeyValue();
            k.setKey(columns[i]);
            k.setValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
            fromColumnsList.add(k);
            fromColumnsListOnlyAttribute.add(columns[i]);
          }
        }
      }
    }
    else
    {
      List toColumnsListOnlyAttribute = new ArrayList();
      List fromColumnsListOnlyAttribute = new ArrayList();
      String strtemp = appsettings.getToList();
      String[] columns = StringUtils.tokenize(strtemp, ",");
      if (columns != null) {
        for (int i = 0; i < columns.length; i++)
        {
          String resoucekey = "aquisition.applicant.configutaion." + columns[i];
          KeyValue k = new KeyValue();
          k.setKey(columns[i]);
          k.setValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
          toColumnsList.add(k);
          toColumnsListOnlyAttribute.add(columns[i]);
        }
      }
      strtemp = appsettings.getFromList();
      columns = StringUtils.tokenize(strtemp, ",");
      if (columns != null) {
        for (int i = 0; i < columns.length; i++)
        {
          String resoucekey = "aquisition.applicant.configutaion." + columns[i];
          KeyValue k = new KeyValue();
          k.setKey(columns[i]);
          k.setValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
          fromColumnsList.add(k);
          fromColumnsListOnlyAttribute.add(columns[i]);
        }
      }
      if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("APP_SEARCH_SCREEN_HIRING_MGR")))
      {
        String strtempnew = Constant.getValue("applicant.page.hiring.mgr.default.columns");
        String[] columnsnew = StringUtils.tokenize(strtempnew, ",");
        List allcolumnsList = new ArrayList();
        if (columnsnew != null) {
          Collections.addAll(allcolumnsList, columnsnew);
        }
        List dbcolumnsListOnlyAttribute = new ArrayList();
        dbcolumnsListOnlyAttribute.addAll(toColumnsListOnlyAttribute);
        dbcolumnsListOnlyAttribute.addAll(fromColumnsListOnlyAttribute);
        List allcolumnsListTemp = new ArrayList();
        allcolumnsListTemp.addAll(allcolumnsList);
        allcolumnsList.retainAll(dbcolumnsListOnlyAttribute);
        allcolumnsListTemp.removeAll(allcolumnsList);
        for (int j = 0; j < allcolumnsListTemp.size(); j++)
        {
          String resoucekey = "aquisition.applicant.configutaion." + (String)allcolumnsListTemp.get(j);
          KeyValue k = new KeyValue();
          k.setKey((String)allcolumnsListTemp.get(j));
          k.setValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
          fromColumnsList.add(k);
        }
      }
      if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("APPLICANT_SUMMARY_SCREEN")))
      {
        String strtempnew = Constant.getValue("applicant.page.applicant.summary.default.columns");
        String[] columnsnew = StringUtils.tokenize(strtempnew, ",");
        List allcolumnsList = new ArrayList();
        if (columnsnew != null) {
          Collections.addAll(allcolumnsList, columnsnew);
        }
        List dbcolumnsListOnlyAttribute = new ArrayList();
        dbcolumnsListOnlyAttribute.addAll(toColumnsListOnlyAttribute);
        dbcolumnsListOnlyAttribute.addAll(fromColumnsListOnlyAttribute);
        List allcolumnsListTemp = new ArrayList();
        allcolumnsListTemp.addAll(allcolumnsList);
        allcolumnsList.retainAll(dbcolumnsListOnlyAttribute);
        allcolumnsListTemp.removeAll(allcolumnsList);
        for (int j = 0; j < allcolumnsListTemp.size(); j++)
        {
          String resoucekey = "aquisition.applicant.configutaion." + (String)allcolumnsListTemp.get(j);
          KeyValue k = new KeyValue();
          k.setKey((String)allcolumnsListTemp.get(j));
          k.setValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
          fromColumnsList.add(k);
        }
      }
      if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("APP_SEARCH_SCREEN_RECRUITER")))
      {
        String strtempnew = Constant.getValue("applicant.page.recruiter.default.columns");
        String[] columnsnew = StringUtils.tokenize(strtempnew, ",");
        List allcolumnsList = new ArrayList();
        if (columnsnew != null) {
          Collections.addAll(allcolumnsList, columnsnew);
        }
        List dbcolumnsListOnlyAttribute = new ArrayList();
        dbcolumnsListOnlyAttribute.addAll(toColumnsListOnlyAttribute);
        dbcolumnsListOnlyAttribute.addAll(fromColumnsListOnlyAttribute);
        List allcolumnsListTemp = new ArrayList();
        allcolumnsListTemp.addAll(allcolumnsList);
        allcolumnsList.retainAll(dbcolumnsListOnlyAttribute);
        allcolumnsListTemp.removeAll(allcolumnsList);
        for (int j = 0; j < allcolumnsListTemp.size(); j++)
        {
          String resoucekey = "aquisition.applicant.configutaion." + (String)allcolumnsListTemp.get(j);
          KeyValue k = new KeyValue();
          k.setKey((String)allcolumnsListTemp.get(j));
          k.setValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
          fromColumnsList.add(k);
        }
      }
      if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("ALL_APP_SEARCH_SCREEN")))
      {
        String strtempnew = Constant.getValue("applicant.page.all.default.columns");
        String[] columnsnew = StringUtils.tokenize(strtempnew, ",");
        List allcolumnsList = new ArrayList();
        if (columnsnew != null) {
          Collections.addAll(allcolumnsList, columnsnew);
        }
        List dbcolumnsListOnlyAttribute = new ArrayList();
        dbcolumnsListOnlyAttribute.addAll(toColumnsListOnlyAttribute);
        dbcolumnsListOnlyAttribute.addAll(fromColumnsListOnlyAttribute);
        List allcolumnsListTemp = new ArrayList();
        allcolumnsListTemp.addAll(allcolumnsList);
        allcolumnsList.retainAll(dbcolumnsListOnlyAttribute);
        allcolumnsListTemp.removeAll(allcolumnsList);
        for (int j = 0; j < allcolumnsListTemp.size(); j++)
        {
          String resoucekey = "aquisition.applicant.configutaion." + (String)allcolumnsListTemp.get(j);
          KeyValue k = new KeyValue();
          k.setKey((String)allcolumnsListTemp.get(j));
          k.setValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
          fromColumnsList.add(k);
        }
      }
      if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("MY_REQUISITION_SCREEN")))
      {
        String strtempnew = Constant.getValue("myrequisition.page.default.columns");
        String[] columnsnew = StringUtils.tokenize(strtempnew, ",");
        List allcolumnsList = new ArrayList();
        if (columnsnew != null) {
          Collections.addAll(allcolumnsList, columnsnew);
        }
        List dbcolumnsListOnlyAttribute = new ArrayList();
        dbcolumnsListOnlyAttribute.addAll(toColumnsListOnlyAttribute);
        dbcolumnsListOnlyAttribute.addAll(fromColumnsListOnlyAttribute);
        List allcolumnsListTemp = new ArrayList();
        allcolumnsListTemp.addAll(allcolumnsList);
        allcolumnsList.retainAll(dbcolumnsListOnlyAttribute);
        allcolumnsListTemp.removeAll(allcolumnsList);
        for (int j = 0; j < allcolumnsListTemp.size(); j++)
        {
          String resoucekey = "requisition.page.table.configutaion." + (String)allcolumnsListTemp.get(j);
          KeyValue k = new KeyValue();
          k.setKey((String)allcolumnsListTemp.get(j));
          k.setValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
          fromColumnsList.add(k);
        }
      }
      if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("ALL_REQUISION_SCREEN")))
      {
        String strtempnew = Constant.getValue("requisition.page.default.columns");
        String[] columnsnew = StringUtils.tokenize(strtempnew, ",");
        List allcolumnsList = new ArrayList();
        if (columnsnew != null) {
          Collections.addAll(allcolumnsList, columnsnew);
        }
        List dbcolumnsListOnlyAttribute = new ArrayList();
        dbcolumnsListOnlyAttribute.addAll(toColumnsListOnlyAttribute);
        dbcolumnsListOnlyAttribute.addAll(fromColumnsListOnlyAttribute);
        List allcolumnsListTemp = new ArrayList();
        allcolumnsListTemp.addAll(allcolumnsList);
        allcolumnsList.retainAll(dbcolumnsListOnlyAttribute);
        allcolumnsListTemp.removeAll(allcolumnsList);
        for (int j = 0; j < allcolumnsListTemp.size(); j++)
        {
          String resoucekey = "requisition.page.table.configutaion." + (String)allcolumnsListTemp.get(j);
          KeyValue k = new KeyValue();
          k.setKey((String)allcolumnsListTemp.get(j));
          k.setValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
          fromColumnsList.add(k);
        }
      }
      if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("DASHBOARD_RECRUITER_ASSIGNED_REQUISTIONS")))
      {
        String strtempnew = Constant.getValue("dashboard.recruiter.assigned.requistions");
        String[] columnsnew = StringUtils.tokenize(strtempnew, ",");
        List allcolumnsList = new ArrayList();
        if (columnsnew != null) {
          Collections.addAll(allcolumnsList, columnsnew);
        }
        List dbcolumnsListOnlyAttribute = new ArrayList();
        dbcolumnsListOnlyAttribute.addAll(toColumnsListOnlyAttribute);
        dbcolumnsListOnlyAttribute.addAll(fromColumnsListOnlyAttribute);
        List allcolumnsListTemp = new ArrayList();
        allcolumnsListTemp.addAll(allcolumnsList);
        allcolumnsList.retainAll(dbcolumnsListOnlyAttribute);
        allcolumnsListTemp.removeAll(allcolumnsList);
        for (int j = 0; j < allcolumnsListTemp.size(); j++)
        {
          String resoucekey = "requisition.page.table.configutaion." + (String)allcolumnsListTemp.get(j);
          KeyValue k = new KeyValue();
          k.setKey((String)allcolumnsListTemp.get(j));
          k.setValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
          fromColumnsList.add(k);
        }
      }
      if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("DASHBOARD_RECRUITER_IN_OFFER_PROCESS")))
      {
        String strtempnew = Constant.getValue("dashboard.recruiter.applicants.offer.process");
        String[] columnsnew = StringUtils.tokenize(strtempnew, ",");
        List allcolumnsList = new ArrayList();
        if (columnsnew != null) {
          Collections.addAll(allcolumnsList, columnsnew);
        }
        List dbcolumnsListOnlyAttribute = new ArrayList();
        dbcolumnsListOnlyAttribute.addAll(toColumnsListOnlyAttribute);
        dbcolumnsListOnlyAttribute.addAll(fromColumnsListOnlyAttribute);
        List allcolumnsListTemp = new ArrayList();
        allcolumnsListTemp.addAll(allcolumnsList);
        allcolumnsList.retainAll(dbcolumnsListOnlyAttribute);
        allcolumnsListTemp.removeAll(allcolumnsList);
        for (int j = 0; j < allcolumnsListTemp.size(); j++)
        {
          String resoucekey = "aquisition.applicant.configutaion." + (String)allcolumnsListTemp.get(j);
          KeyValue k = new KeyValue();
          k.setKey((String)allcolumnsListTemp.get(j));
          k.setValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
          fromColumnsList.add(k);
        }
      }
      if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("DASHBOARD_RECRUITER_IN_JOINED_PROCESS")))
      {
        String strtempnew = Constant.getValue("dashboard.recruiter.applicants.joining.process");
        String[] columnsnew = StringUtils.tokenize(strtempnew, ",");
        List allcolumnsList = new ArrayList();
        if (columnsnew != null) {
          Collections.addAll(allcolumnsList, columnsnew);
        }
        List dbcolumnsListOnlyAttribute = new ArrayList();
        dbcolumnsListOnlyAttribute.addAll(toColumnsListOnlyAttribute);
        dbcolumnsListOnlyAttribute.addAll(fromColumnsListOnlyAttribute);
        List allcolumnsListTemp = new ArrayList();
        allcolumnsListTemp.addAll(allcolumnsList);
        allcolumnsList.retainAll(dbcolumnsListOnlyAttribute);
        allcolumnsListTemp.removeAll(allcolumnsList);
        for (int j = 0; j < allcolumnsListTemp.size(); j++)
        {
          String resoucekey = "aquisition.applicant.configutaion." + (String)allcolumnsListTemp.get(j);
          KeyValue k = new KeyValue();
          k.setKey((String)allcolumnsListTemp.get(j));
          k.setValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
          fromColumnsList.add(k);
        }
      }
      if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("ON_BOARDING_SEARCH_SCREEN")))
      {
        String strtempnew = Constant.getValue("onboarding.page.default.columns");
        String[] columnsnew = StringUtils.tokenize(strtempnew, ",");
        List allcolumnsList = new ArrayList();
        if (columnsnew != null) {
          Collections.addAll(allcolumnsList, columnsnew);
        }
        List dbcolumnsListOnlyAttribute = new ArrayList();
        dbcolumnsListOnlyAttribute.addAll(toColumnsListOnlyAttribute);
        dbcolumnsListOnlyAttribute.addAll(fromColumnsListOnlyAttribute);
        List allcolumnsListTemp = new ArrayList();
        allcolumnsListTemp.addAll(allcolumnsList);
        allcolumnsList.retainAll(dbcolumnsListOnlyAttribute);
        allcolumnsListTemp.removeAll(allcolumnsList);
        for (int j = 0; j < allcolumnsListTemp.size(); j++)
        {
          String resoucekey = "aquisition.applicant.configutaion." + (String)allcolumnsListTemp.get(j);
          KeyValue k = new KeyValue();
          k.setKey((String)allcolumnsListTemp.get(j));
          k.setValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
          fromColumnsList.add(k);
        }
      }
    }
  }
}
