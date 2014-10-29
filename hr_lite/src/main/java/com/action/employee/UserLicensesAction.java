package com.action.employee;

import com.action.CommonAction;
import com.bean.Timezone;
import com.bean.User;
import com.bean.employee.UserLicenses;
import com.bean.lov.LicenseType;
import com.bo.BOFactory;
import com.bo.LovBO;
import com.bo.UserBO;
import com.form.employee.UserLicensesForm;
import com.util.DateUtil;
import com.util.StringUtils;
import java.util.Calendar;
import java.util.TimeZone;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class UserLicensesAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(UserLicensesAction.class);
  
  public ActionForward addUserLicenseDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside addUserLicenseDetails method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    UserLicensesForm userLicensesForm = (UserLicensesForm)form;
    String userId = request.getParameter("userId");
    logger.info("userId >> " + userId);
    userLicensesForm.setUserId(new Long(userId).longValue());
    userLicensesForm.setLicenseTypeList(BOFactory.getLovBO().getAllLicense());
    
    return mapping.findForward("addUserLicenseDetails");
  }
  
  public ActionForward saveUserLicenseDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside saveUserLicenseDetails method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    String userId = request.getParameter("userId");
    logger.info("userId >> " + userId);
    
    UserLicensesForm userLicensesForm = (UserLicensesForm)form;
    UserLicenses userLicenses = new UserLicenses();
    userLicensesForm.setUserId(new Long(userId).longValue());
    
    String issueDate = request.getParameter("issueDate");
    String expireDate = request.getParameter("expireDate");
    long licenseTypeId = userLicensesForm.getLicenseTypeId();
    logger.info("licenseTypeId >> " + licenseTypeId);
    if (!StringUtils.isNullOrEmpty(issueDate))
    {
      String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
      
      String converteddate = DateUtil.convertFromTimezoneToTimezoneDate(issueDate, datepattern, user1.getTimezone().getTimezoneCode(), TimeZone.getDefault().getID());
      




      logger.info("converteddate" + converteddate);
      
      Calendar cal = DateUtil.convertStringDateToCalendar(converteddate, datepattern);
      

      userLicenses.setIssueDate(cal.getTime());
    }
    if (!StringUtils.isNullOrEmpty(expireDate))
    {
      String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
      
      String converteddate = DateUtil.convertFromTimezoneToTimezoneDate(expireDate, datepattern, user1.getTimezone().getTimezoneCode(), TimeZone.getDefault().getID());
      




      logger.info("converteddate" + converteddate);
      
      Calendar cal = DateUtil.convertStringDateToCalendar(converteddate, datepattern);
      

      userLicenses.setExpireDate(cal.getTime());
    }
    UserLicenses userLicenses1 = null;
    boolean isError = false;
    userLicenses1 = BOFactory.getUserBO().getUserLicenseDetailsByUserIdandLicenseTypeId(new Long(userId).longValue(), licenseTypeId);
    
    userLicensesForm.toValue(userLicenses, request);
    if (userLicenses1 != null)
    {
      isError = true;
      ActionErrors errors = new ActionErrors();
      
      errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError("error.licensetype.alreadyexist"));
      saveErrors(request, errors);
    }
    if (!isError)
    {
      userLicenses = BOFactory.getUserBO().saveUserLicenseDetails(userLicenses);
      request.setAttribute("userLicenseDetailsSaved", "yes");
    }
    userLicensesForm.fromValue(userLicenses, request);
    userLicensesForm.setLicenseTypeList(BOFactory.getLovBO().getAllLicense());
    

    return mapping.findForward("addUserLicenseDetails");
  }
  
  public ActionForward editUserLicenseDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside editUserLicenseDetails method");
    
    String userId = request.getParameter("userId");
    logger.info("userId >> " + userId);
    UserLicensesForm userLicensesForm = (UserLicensesForm)form;
    
    userLicensesForm.setUserId(new Long(userId).longValue());
    
    String userLicenseId = request.getParameter("userLicenseId");
    
    UserLicenses userLicenses = BOFactory.getUserBO().getUserLicenseDetails(new Long(userLicenseId).longValue());
    
    userLicensesForm.fromValue(userLicenses, request);
    userLicensesForm.setLicenseTypeList(BOFactory.getLovBO().getAllLicense());
    
    return mapping.findForward("addUserLicenseDetails");
  }
  
  public ActionForward updateUserLicenseDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside updateUserLicenseDetails method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    String userId = request.getParameter("userId");
    logger.info("userId >> " + userId);
    UserLicensesForm userLicensesForm = (UserLicensesForm)form;
    
    userLicensesForm.setUserId(new Long(userId).longValue());
    
    String issueDate = request.getParameter("issueDate");
    String expireDate = request.getParameter("expireDate");
    
    String userLicenseId = request.getParameter("userLicenseId");
    long licenseTypeId = userLicensesForm.getLicenseTypeId();
    logger.info("licenseTypeId >> " + licenseTypeId);
    UserLicenses userLicenses = BOFactory.getUserBO().getUserLicenseDetails(new Long(userLicenseId).longValue());
    if (!StringUtils.isNullOrEmpty(issueDate))
    {
      String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
      
      String converteddate = DateUtil.convertFromTimezoneToTimezoneDate(issueDate, datepattern, user1.getTimezone().getTimezoneCode(), TimeZone.getDefault().getID());
      




      logger.info("converteddate" + converteddate);
      
      Calendar cal = DateUtil.convertStringDateToCalendar(converteddate, datepattern);
      

      userLicenses.setIssueDate(cal.getTime());
    }
    if (!StringUtils.isNullOrEmpty(expireDate))
    {
      String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
      
      String converteddate = DateUtil.convertFromTimezoneToTimezoneDate(expireDate, datepattern, user1.getTimezone().getTimezoneCode(), TimeZone.getDefault().getID());
      




      logger.info("converteddate" + converteddate);
      
      Calendar cal = DateUtil.convertStringDateToCalendar(converteddate, datepattern);
      

      userLicenses.setExpireDate(cal.getTime());
    }
    UserLicenses userLicenses1 = null;
    boolean isError = false;
    if (licenseTypeId == userLicenses.getLicensetype().getLicenseTypeId()) {
      userLicenses1 = null;
    } else {
      userLicenses1 = BOFactory.getUserBO().getUserLicenseDetailsByUserIdandLicenseTypeId(new Long(userId).longValue(), licenseTypeId);
    }
    userLicensesForm.toValue(userLicenses, request);
    if (userLicenses1 != null)
    {
      isError = true;
      ActionErrors errors = new ActionErrors();
      
      errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError("error.licensetype.alreadyexist"));
      saveErrors(request, errors);
    }
    if (!isError)
    {
      userLicenses = BOFactory.getUserBO().updateUserLicenseDetails(userLicenses);
      request.setAttribute("userLicenseDetailsUpdated", "yes");
    }
    userLicensesForm.fromValue(userLicenses, request);
    userLicensesForm.setLicenseTypeList(BOFactory.getLovBO().getAllLicense());
    

    return mapping.findForward("addUserLicenseDetails");
  }
  
  public ActionForward deleteUserLicenseDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside deleteUserLicenseDetails method");
    
    String userId = request.getParameter("userId");
    logger.info("userId >> " + userId);
    UserLicensesForm userLicensesForm = (UserLicensesForm)form;
    
    userLicensesForm.setUserId(new Long(userId).longValue());
    


    String userLicenseId = request.getParameter("userLicenseId");
    
    UserLicenses userLicenses = BOFactory.getUserBO().getUserLicenseDetails(new Long(userLicenseId).longValue());
    
    userLicensesForm.toValue(userLicenses, request);
    
    userLicenses = BOFactory.getUserBO().deleteUserLicenseDetails(userLicenses);
    

    userLicensesForm.fromValue(userLicenses, request);
    userLicensesForm.setLicenseTypeList(BOFactory.getLovBO().getAllLicense());
    
    request.setAttribute("userLicenseDetailsDeleted", "yes");
    return mapping.findForward("addUserLicenseDetails");
  }
}
