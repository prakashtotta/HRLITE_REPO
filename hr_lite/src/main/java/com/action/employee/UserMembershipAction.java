package com.action.employee;

import com.action.CommonAction;
import com.bean.Timezone;
import com.bean.User;
import com.bean.employee.UserMemberShip;
import com.bean.lov.MembershipType;
import com.bo.BOFactory;
import com.bo.LovBO;
import com.bo.UserBO;
import com.form.employee.UserMemberShipForm;
import com.resources.Constant;
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
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class UserMembershipAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(UserMembershipAction.class);
  
  public ActionForward userMembershipDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside userMembershipDetails method");
    UserMemberShipForm userMemberShipForm = (UserMemberShipForm)form;
    String userId = request.getParameter("userId");
    logger.info("userId >> " + userId);
    userMemberShipForm.setUserId(new Long(userId).longValue());
    List usermembershipList = new ArrayList();
    usermembershipList = BOFactory.getUserBO().getUserMembershiplistByUserId(new Long(userId).longValue());
    logger.info("usermembershipList >> " + usermembershipList.size());
    userMemberShipForm.setUsermembershipList(usermembershipList);
    

    return mapping.findForward("userMembershipDetails");
  }
  
  public ActionForward addUserMembershipDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside addUserMembershipDetails method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    UserMemberShipForm userMemberShipForm = (UserMemberShipForm)form;
    String userId = request.getParameter("userId");
    logger.info("userId >> " + userId);
    userMemberShipForm.setUserId(new Long(userId).longValue());
    
    userMemberShipForm.setMembershipTypesList(BOFactory.getLovBO().getMembershipTypesList());
    userMemberShipForm.setCurrencyCodeList(BOFactory.getLovBO().getAllCurrencies());
    userMemberShipForm.setSubscriptionPaidByList(Constant.getSubscriptionPaidByList(user1));
    


    return mapping.findForward("addUserMembershipDetails");
  }
  
  public ActionForward saveUserMembershipDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside addUserMembershipDetails method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    UserMemberShipForm userMemberShipForm = (UserMemberShipForm)form;
    String userId = request.getParameter("userId");
    logger.info("userId >> " + userId);
    String subscriptionCommenceDate = request.getParameter("subscriptionCommenceDate");
    String subscriptionRenewalDate = request.getParameter("subscriptionRenewalDate");
    userMemberShipForm.setUserId(new Long(userId).longValue());
    long membershipTypeId = userMemberShipForm.getMembershipTypeId();
    
    UserMemberShip userMemberShip = new UserMemberShip();
    if (!StringUtils.isNullOrEmpty(subscriptionCommenceDate))
    {
      String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
      
      String converteddate = DateUtil.convertFromTimezoneToTimezoneDate(subscriptionCommenceDate, datepattern, user1.getTimezone().getTimezoneCode(), TimeZone.getDefault().getID());
      






      Calendar cal = DateUtil.convertStringDateToCalendar(converteddate, datepattern);
      

      userMemberShip.setSubscriptionCommenceDate(cal.getTime());
    }
    if (!StringUtils.isNullOrEmpty(subscriptionRenewalDate))
    {
      String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
      
      String converteddate = DateUtil.convertFromTimezoneToTimezoneDate(subscriptionRenewalDate, datepattern, user1.getTimezone().getTimezoneCode(), TimeZone.getDefault().getID());
      






      Calendar cal = DateUtil.convertStringDateToCalendar(converteddate, datepattern);
      

      userMemberShip.setSubscriptionRenewalDate(cal.getTime());
    }
    userMemberShipForm.toValue(userMemberShip, request);
    UserMemberShip userMemberShip1 = null;
    boolean isError = false;
    userMemberShip1 = BOFactory.getUserBO().getUserMembershipDetailsByUserIdandMembershipTypeId(new Long(userId).longValue(), membershipTypeId);
    if (userMemberShip1 != null)
    {
      isError = true;
      ActionErrors errors = new ActionErrors();
      
      errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError("error.membership.alreadyexist"));
      saveErrors(request, errors);
    }
    if (!isError)
    {
      userMemberShip = BOFactory.getUserBO().saveUserMembershipDetails(userMemberShip);
      request.setAttribute("membershipDetailsSaved", "yes");
    }
    userMemberShipForm.setMembershipTypesList(BOFactory.getLovBO().getMembershipTypesList());
    userMemberShipForm.setCurrencyCodeList(BOFactory.getLovBO().getAllCurrencies());
    userMemberShipForm.setSubscriptionPaidByList(Constant.getSubscriptionPaidByList(user1));
    
    userMemberShipForm.fromValue(userMemberShip, request);
    
    return mapping.findForward("addUserMembershipDetails");
  }
  
  public ActionForward editUserMembershipDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside editUserMembershipDetails method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    UserMemberShipForm userMemberShipForm = (UserMemberShipForm)form;
    String userId = request.getParameter("userId");
    logger.info("userId >> " + userId);
    String userMemberShipId = request.getParameter("userMemberShipId");
    
    UserMemberShip userMemberShip = BOFactory.getUserBO().getUserMembershipDetails(new Long(userMemberShipId).longValue());
    
    userMemberShipForm.setUserId(new Long(userId).longValue());
    
    userMemberShipForm.fromValue(userMemberShip, request);
    
    userMemberShipForm.setMembershipTypesList(BOFactory.getLovBO().getMembershipTypesList());
    userMemberShipForm.setCurrencyCodeList(BOFactory.getLovBO().getAllCurrencies());
    userMemberShipForm.setSubscriptionPaidByList(Constant.getSubscriptionPaidByList(user1));
    



    return mapping.findForward("addUserMembershipDetails");
  }
  
  public ActionForward updateUserMembershipDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside updateUserMembershipDetails method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    UserMemberShipForm userMemberShipForm = (UserMemberShipForm)form;
    String userId = request.getParameter("userId");
    logger.info("userId >> " + userId);
    String userMemberShipId = request.getParameter("userMemberShipId");
    long membershipTypeId = userMemberShipForm.getMembershipTypeId();
    
    UserMemberShip userMemberShip = BOFactory.getUserBO().getUserMembershipDetails(new Long(userMemberShipId).longValue());
    
    userMemberShipForm.setUserId(new Long(userId).longValue());
    

    String subscriptionCommenceDate = request.getParameter("subscriptionCommenceDate");
    String subscriptionRenewalDate = request.getParameter("subscriptionRenewalDate");
    if (!StringUtils.isNullOrEmpty(subscriptionCommenceDate))
    {
      String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
      
      String converteddate = DateUtil.convertFromTimezoneToTimezoneDate(subscriptionCommenceDate, datepattern, user1.getTimezone().getTimezoneCode(), TimeZone.getDefault().getID());
      






      Calendar cal = DateUtil.convertStringDateToCalendar(converteddate, datepattern);
      

      userMemberShip.setSubscriptionCommenceDate(cal.getTime());
    }
    if (!StringUtils.isNullOrEmpty(subscriptionRenewalDate))
    {
      String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
      
      String converteddate = DateUtil.convertFromTimezoneToTimezoneDate(subscriptionRenewalDate, datepattern, user1.getTimezone().getTimezoneCode(), TimeZone.getDefault().getID());
      






      Calendar cal = DateUtil.convertStringDateToCalendar(converteddate, datepattern);
      

      userMemberShip.setSubscriptionRenewalDate(cal.getTime());
    }
    UserMemberShip userMemberShip1 = null;
    boolean isError = false;
    logger.info(" membershipTypeId " + membershipTypeId);
    logger.info(" membershipTypeId 2" + userMemberShip.getMembershiptype().getMembershipTypeId());
    if (membershipTypeId == userMemberShip.getMembershiptype().getMembershipTypeId()) {
      userMemberShip1 = null;
    } else {
      userMemberShip1 = BOFactory.getUserBO().getUserMembershipDetailsByUserIdandMembershipTypeId(new Long(userId).longValue(), membershipTypeId);
    }
    userMemberShipForm.toValue(userMemberShip, request);
    if (userMemberShip1 != null)
    {
      isError = true;
      ActionErrors errors = new ActionErrors();
      
      errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError("error.membership.alreadyexist"));
      saveErrors(request, errors);
    }
    if (!isError)
    {
      userMemberShip = BOFactory.getUserBO().updateUserMembershipDetails(userMemberShip);
      request.setAttribute("membershipDetailsUpdated", "yes");
    }
    userMemberShipForm.fromValue(userMemberShip, request);
    
    userMemberShipForm.setMembershipTypesList(BOFactory.getLovBO().getMembershipTypesList());
    userMemberShipForm.setCurrencyCodeList(BOFactory.getLovBO().getAllCurrencies());
    userMemberShipForm.setSubscriptionPaidByList(Constant.getSubscriptionPaidByList(user1));
    



    return mapping.findForward("addUserMembershipDetails");
  }
  
  public ActionForward deleteUserMembershipDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside deleteUserMembershipDetails method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    UserMemberShipForm userMemberShipForm = (UserMemberShipForm)form;
    String userId = request.getParameter("userId");
    logger.info("userId >> " + userId);
    String userMemberShipId = request.getParameter("userMemberShipId");
    
    UserMemberShip userMemberShip = BOFactory.getUserBO().getUserMembershipDetails(new Long(userMemberShipId).longValue());
    
    userMemberShipForm.setUserId(new Long(userId).longValue());
    



    userMemberShip = BOFactory.getUserBO().deleteUserMembershipDetails(userMemberShip);
    


    userMemberShipForm.setMembershipTypesList(BOFactory.getLovBO().getMembershipTypesList());
    userMemberShipForm.setCurrencyCodeList(BOFactory.getLovBO().getAllCurrencies());
    userMemberShipForm.setSubscriptionPaidByList(Constant.getSubscriptionPaidByList(user1));
    


    request.setAttribute("membershipDetailsdeleted", "yes");
    return mapping.findForward("addUserMembershipDetails");
  }
  
  public ActionForward deleteUserMembershipMultiple(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside deleteUserMembershipMultiple method");
    UserMemberShipForm userMemberShipForm = (UserMemberShipForm)form;
    String userId = request.getParameter("userId");
    logger.info("userId >> " + userId);
    userMemberShipForm.setUserId(new Long(userId).longValue());
    String checkedItems = request.getParameter("checkedItems");
    
    BOFactory.getUserBO().deleteUserMembershipMultiple(checkedItems);
    
    List usermembershipList = new ArrayList();
    usermembershipList = BOFactory.getUserBO().getUserMembershiplistByUserId(new Long(userId).longValue());
    logger.info("usermembershipList >> " + usermembershipList.size());
    userMemberShipForm.setUsermembershipList(usermembershipList);
    
    request.setAttribute("deleteUserMembershipMultiple", "yes");
    return mapping.findForward("userMembershipDetails");
  }
}
