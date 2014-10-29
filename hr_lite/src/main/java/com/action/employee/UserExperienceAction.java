package com.action.employee;

import com.action.CommonAction;
import com.bean.User;
import com.bean.employee.OrganizationDetails;
import com.bo.BOFactory;
import com.bo.LovBO;
import com.bo.UserBO;
import com.form.employee.UserExperienceForm;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class UserExperienceAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(UserExperienceAction.class);
  
  public ActionForward userExperienceDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside userExperienceDetails method");
    UserExperienceForm userExperienceForm = (UserExperienceForm)form;
    String userId = request.getParameter("userId");
    logger.info("userId >> " + userId);
    userExperienceForm.setUserId(new Long(userId).longValue());
    
    List userExpList = BOFactory.getUserBO().getUserExperienceListByUserId(new Long(userId).longValue());
    userExperienceForm.setUserExperiencesList(userExpList);
    return mapping.findForward("userExperienceDetails");
  }
  
  public ActionForward addUserExperienceDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside addUserExperienceDetails method");
    UserExperienceForm userExperienceForm = (UserExperienceForm)form;
    String userId = request.getParameter("userId");
    logger.info("userId >> " + userId);
    userExperienceForm.setUserId(new Long(userId).longValue());
    
    userExperienceForm.setCurrencyList(BOFactory.getLovBO().getAllCurrencies());
    userExperienceForm.setStateList(BOFactory.getLovBO().getStateList());
    userExperienceForm.setCountryList(BOFactory.getLovBO().getAllCountries());
    return mapping.findForward("addUserExperienceDetails");
  }
  
  public ActionForward saveUserExperienceDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside saveUserExperienceDetails method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    UserExperienceForm userExperienceForm = (UserExperienceForm)form;
    OrganizationDetails organizationDetails = new OrganizationDetails();
    String userId = request.getParameter("userId");
    String startdate = request.getParameter("startdate");
    String enddate = request.getParameter("enddate");
    logger.info("userId >> " + userId);
    logger.info("startdate >> " + startdate);
    logger.info("enddate >> " + enddate);
    userExperienceForm.setUserId(new Long(userId).longValue());
    organizationDetails.setStartdate(startdate);
    organizationDetails.setEnddate(enddate);
    userExperienceForm.toValue(organizationDetails, request);
    if (user1 != null) {
      organizationDetails.setCreatedBy(user1.getUserName());
    }
    organizationDetails.setCreatedDate(new Date());
    
    organizationDetails = BOFactory.getUserBO().saveUserExperienceDetails(organizationDetails);
    

    userExperienceForm.fromValue(organizationDetails, request);
    userExperienceForm.setCurrencyList(BOFactory.getLovBO().getAllCurrencies());
    userExperienceForm.setStateList(BOFactory.getLovBO().getStateList());
    userExperienceForm.setCountryList(BOFactory.getLovBO().getAllCountries());
    
    request.setAttribute("userExperienceDetailsSaved", "yes");
    return mapping.findForward("addUserExperienceDetails");
  }
  
  public ActionForward updateUserExperienceDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside updateUserExperienceDetails method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    UserExperienceForm userExperienceForm = (UserExperienceForm)form;
    
    String orgDetailsId = request.getParameter("orgDetailsId");
    String userId = request.getParameter("userId");
    
    String startdate = request.getParameter("startdate");
    String enddate = request.getParameter("enddate");
    logger.info("userId >> " + userId);
    logger.info("startdate >> " + startdate);
    logger.info("enddate >> " + enddate);
    userExperienceForm.setUserId(new Long(userId).longValue());
    
    OrganizationDetails organizationDetails = BOFactory.getUserBO().getUserExperienceDetails(new Long(orgDetailsId).longValue());
    


    organizationDetails.setStartdate(startdate);
    organizationDetails.setEnddate(enddate);
    userExperienceForm.toValue(organizationDetails, request);
    

    organizationDetails = BOFactory.getUserBO().updateUserExperienceDetails(organizationDetails);
    

    userExperienceForm.fromValue(organizationDetails, request);
    userExperienceForm.setCurrencyList(BOFactory.getLovBO().getAllCurrencies());
    userExperienceForm.setStateList(BOFactory.getLovBO().getStateList());
    userExperienceForm.setCountryList(BOFactory.getLovBO().getAllCountries());
    
    request.setAttribute("userExperienceDetailsUpdated", "yes");
    return mapping.findForward("addUserExperienceDetails");
  }
  
  public ActionForward deleteUserExperienceDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside deleteUserExperienceDetails method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    UserExperienceForm userExperienceForm = (UserExperienceForm)form;
    
    String orgDetailsId = request.getParameter("orgDetailsId");
    String userId = request.getParameter("userId");
    

    userExperienceForm.setUserId(new Long(userId).longValue());
    
    OrganizationDetails organizationDetails = BOFactory.getUserBO().getUserExperienceDetails(new Long(orgDetailsId).longValue());
    

    organizationDetails = BOFactory.getUserBO().deleteUserExperienceDetails(organizationDetails);
    

    userExperienceForm.setCurrencyList(BOFactory.getLovBO().getAllCurrencies());
    userExperienceForm.setStateList(BOFactory.getLovBO().getStateList());
    userExperienceForm.setCountryList(BOFactory.getLovBO().getAllCountries());
    
    request.setAttribute("userExperienceDetailsDeleted", "yes");
    return mapping.findForward("addUserExperienceDetails");
  }
  
  public ActionForward editUserExperienceDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside editUserExperienceDetails method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    UserExperienceForm userExperienceForm = (UserExperienceForm)form;
    
    String orgDetailsId = request.getParameter("orgDetailsId");
    String userId = request.getParameter("userId");
    
    userExperienceForm.setUserId(new Long(userId).longValue());
    
    OrganizationDetails organizationDetails = BOFactory.getUserBO().getUserExperienceDetails(new Long(orgDetailsId).longValue());
    
    userExperienceForm.fromValue(organizationDetails, request);
    
    userExperienceForm.setCurrencyList(BOFactory.getLovBO().getAllCurrencies());
    userExperienceForm.setStateList(BOFactory.getLovBO().getStateList());
    userExperienceForm.setCountryList(BOFactory.getLovBO().getAllCountries());
    
    return mapping.findForward("addUserExperienceDetails");
  }
  
  public ActionForward deleteUserExperienceMultiple(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside deleteUserExperienceMultiple method");
    UserExperienceForm userExperienceForm = (UserExperienceForm)form;
    String userId = request.getParameter("userId");
    logger.info("userId >> " + userId);
    userExperienceForm.setUserId(new Long(userId).longValue());
    
    String checkedItems = request.getParameter("checkedItems");
    

    BOFactory.getUserBO().deleteUserExperienceMultiple(checkedItems);
    
    List userExpList = BOFactory.getUserBO().getUserExperienceListByUserId(new Long(userId).longValue());
    userExperienceForm.setUserExperiencesList(userExpList);
    request.setAttribute("deleteUserExperienceMultiple", "yes");
    return mapping.findForward("userExperienceDetails");
  }
}
