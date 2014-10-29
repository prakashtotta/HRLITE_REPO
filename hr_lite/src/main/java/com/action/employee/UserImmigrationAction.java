package com.action.employee;

import com.action.CommonAction;
import com.bean.Timezone;
import com.bean.User;
import com.bean.employee.Imigrations;
import com.bo.BOFactory;
import com.bo.LovBO;
import com.bo.UserBO;
import com.form.employee.UserImigrationsForm;
import com.util.DateUtil;
import com.util.StringUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class UserImmigrationAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(UserImmigrationAction.class);
  
  public ActionForward userImmigrationdetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside userImmigrationdetails method");
    UserImigrationsForm userImigrationsForm = (UserImigrationsForm)form;
    String userId = request.getParameter("userId");
    logger.info("userId >> " + userId);
    
    List imigrationslist = new ArrayList();
    imigrationslist = BOFactory.getUserBO().getUserImmigrationlistByUserId(new Long(userId).longValue());
    logger.info("imigrationslist.size() >> " + imigrationslist.size());
    userImigrationsForm.setUserImigrationList(imigrationslist);
    


    return mapping.findForward("userImmigrationdetails");
  }
  
  public ActionForward addUserImigration(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    UserImigrationsForm userImigrationsForm = (UserImigrationsForm)form;
    List countryList = BOFactory.getLovBO().getAllCountries();
    logger.info("countryList.size() >>> " + countryList.size());
    userImigrationsForm.setCountryList(countryList);
    return mapping.findForward("addUserImigration");
  }
  
  public ActionForward saveUserImmigration(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside saveUserImmigration method ");
    User user1 = (User)request.getSession().getAttribute("user_data");
    UserImigrationsForm userImigrationsForm = (UserImigrationsForm)form;
    Imigrations imigrations = new Imigrations();
    String passportIssueDate = request.getParameter("passportIssueDate");
    String passportExpiryDate = request.getParameter("passportExpiryDate");
    String eligibleReviewDate = request.getParameter("eligibleReviewDate");
    if (!StringUtils.isNullOrEmpty(passportIssueDate))
    {
      String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
      
      String converteddate = DateUtil.convertFromTimezoneToTimezoneDate(passportIssueDate, datepattern, user1.getTimezone().getTimezoneCode(), TimeZone.getDefault().getID());
      




      logger.info("converteddate" + converteddate);
      
      Calendar cal = DateUtil.convertStringDateToCalendar(converteddate, datepattern);
      

      imigrations.setPassportIssueDate(cal.getTime());
    }
    if (!StringUtils.isNullOrEmpty(passportExpiryDate))
    {
      String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
      
      String converteddate = DateUtil.convertFromTimezoneToTimezoneDate(passportExpiryDate, datepattern, user1.getTimezone().getTimezoneCode(), TimeZone.getDefault().getID());
      




      logger.info("converteddate" + converteddate);
      
      Calendar cal = DateUtil.convertStringDateToCalendar(converteddate, datepattern);
      

      imigrations.setPassportExpiryDate(cal.getTime());
    }
    if (!StringUtils.isNullOrEmpty(eligibleReviewDate))
    {
      String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
      
      String converteddate = DateUtil.convertFromTimezoneToTimezoneDate(eligibleReviewDate, datepattern, user1.getTimezone().getTimezoneCode(), TimeZone.getDefault().getID());
      




      logger.info("converteddate" + converteddate);
      
      Calendar cal = DateUtil.convertStringDateToCalendar(converteddate, datepattern);
      

      imigrations.setEligibleReviewDate(cal.getTime());
    }
    userImigrationsForm.toValue(imigrations, request);
    imigrations = BOFactory.getUserBO().saveUserImmigration(imigrations);
    
    userImigrationsForm.fromValue(imigrations, request);
    
    List countryList = BOFactory.getLovBO().getAllCountries();
    logger.info("countryList.size() >>> " + countryList.size());
    userImigrationsForm.setCountryList(countryList);
    request.setAttribute("userImmigrationSaved", "yes");
    return mapping.findForward("addUserImigration");
  }
  
  public ActionForward editUserImmigration(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside editUserImmigration method ");
    UserImigrationsForm userImigrationsForm = (UserImigrationsForm)form;
    String imigrationId = request.getParameter("imigrationId");
    
    Imigrations imigrations = BOFactory.getUserBO().getUserImmigrationInfo(new Long(imigrationId).longValue());
    
    userImigrationsForm.fromValue(imigrations, request);
    
    List countryList = BOFactory.getLovBO().getAllCountries();
    userImigrationsForm.setCountryList(countryList);
    
    return mapping.findForward("addUserImigration");
  }
  
  public ActionForward updateUserImmigration(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside updateUserImmigration method ");
    User user1 = (User)request.getSession().getAttribute("user_data");
    UserImigrationsForm userImigrationsForm = (UserImigrationsForm)form;
    String imigrationId = request.getParameter("imigrationId");
    
    Imigrations imigrations = BOFactory.getUserBO().getUserImmigrationInfo(new Long(imigrationId).longValue());
    
    String passportIssueDate = request.getParameter("passportIssueDate");
    String passportExpiryDate = request.getParameter("passportExpiryDate");
    String eligibleReviewDate = request.getParameter("eligibleReviewDate");
    if (!StringUtils.isNullOrEmpty(passportIssueDate))
    {
      String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
      
      String converteddate = DateUtil.convertFromTimezoneToTimezoneDate(passportIssueDate, datepattern, user1.getTimezone().getTimezoneCode(), TimeZone.getDefault().getID());
      




      logger.info("converteddate" + converteddate);
      
      Calendar cal = DateUtil.convertStringDateToCalendar(converteddate, datepattern);
      

      imigrations.setPassportIssueDate(cal.getTime());
    }
    if (!StringUtils.isNullOrEmpty(passportExpiryDate))
    {
      String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
      
      String converteddate = DateUtil.convertFromTimezoneToTimezoneDate(passportExpiryDate, datepattern, user1.getTimezone().getTimezoneCode(), TimeZone.getDefault().getID());
      




      logger.info("converteddate" + converteddate);
      
      Calendar cal = DateUtil.convertStringDateToCalendar(converteddate, datepattern);
      

      imigrations.setPassportExpiryDate(cal.getTime());
    }
    if (!StringUtils.isNullOrEmpty(eligibleReviewDate))
    {
      String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
      
      String converteddate = DateUtil.convertFromTimezoneToTimezoneDate(eligibleReviewDate, datepattern, user1.getTimezone().getTimezoneCode(), TimeZone.getDefault().getID());
      




      logger.info("converteddate" + converteddate);
      
      Calendar cal = DateUtil.convertStringDateToCalendar(converteddate, datepattern);
      

      imigrations.setEligibleReviewDate(cal.getTime());
    }
    userImigrationsForm.toValue(imigrations, request);
    imigrations = BOFactory.getUserBO().updateUserImmigration(imigrations);
    
    userImigrationsForm.fromValue(imigrations, request);
    
    List countryList = BOFactory.getLovBO().getAllCountries();
    userImigrationsForm.setCountryList(countryList);
    request.setAttribute("userImmigrationUpdated", "yes");
    return mapping.findForward("addUserImigration");
  }
  
  public ActionForward deleteUserImmigration(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside deleteUserImmigration method ");
    User user1 = (User)request.getSession().getAttribute("user_data");
    UserImigrationsForm userImigrationsForm = (UserImigrationsForm)form;
    String imigrationId = request.getParameter("imigrationId");
    
    Imigrations imigrations = BOFactory.getUserBO().getUserImmigrationInfo(new Long(imigrationId).longValue());
    
    imigrations = BOFactory.getUserBO().deleteUserImmigration(imigrations);
    
    userImigrationsForm.fromValue(imigrations, request);
    
    List countryList = BOFactory.getLovBO().getAllCountries();
    userImigrationsForm.setCountryList(countryList);
    request.setAttribute("userImmigrationDeleted", "yes");
    return mapping.findForward("addUserImigration");
  }
  
  public ActionForward deleteUserImmigrationMultiple(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside deleteUserImmigrationMultiple method");
    UserImigrationsForm userImigrationsForm = (UserImigrationsForm)form;
    String userId = request.getParameter("userId");
    logger.info("userId >> " + userId);
    
    String checkedItems = request.getParameter("checkedItems");
    
    BOFactory.getUserBO().deleteUserImmigrationMultiple(checkedItems);
    List imigrationslist = new ArrayList();
    imigrationslist = BOFactory.getUserBO().getUserImmigrationlistByUserId(new Long(userId).longValue());
    logger.info("imigrationslist.size() >> " + imigrationslist.size());
    request.setAttribute("userImmigrationmultipledeleted", "yes");
    userImigrationsForm.setUserImigrationList(imigrationslist);
    


    return mapping.findForward("userImmigrationdetails");
  }
}
