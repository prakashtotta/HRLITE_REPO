package com.action.employee;

import com.action.CommonAction;
import com.bean.User;
import com.bean.employee.UserEducationDetails;
import com.bo.BOFactory;
import com.bo.LovTXBO;
import com.bo.UserBO;
import com.form.employee.UserEducationDetailsForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class UserEducationDetailsAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(UserEducationDetailsAction.class);
  
  public ActionForward addUserEducation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside addUserEducation method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    UserEducationDetailsForm userEducationDetailsForm = (UserEducationDetailsForm)form;
    String userId = request.getParameter("userId");
    logger.info("userId >> " + userId);
    userEducationDetailsForm.setUserId(new Long(userId).longValue());
    userEducationDetailsForm.setEducationNamesList(BOFactory.getLovTXBO().getEducationListKeyValue(user1.getSuper_user_key()));
    
    return mapping.findForward("addUserEducation");
  }
  
  public ActionForward saveUserEducationDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside saveUserEducationDetails method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    UserEducationDetailsForm userEducationDetailsForm = (UserEducationDetailsForm)form;
    UserEducationDetails userEducationDetails = new UserEducationDetails();
    String userId = request.getParameter("userId");
    logger.info("userId >> " + userId);
    userEducationDetailsForm.setUserId(new Long(userId).longValue());
    String educationName = userEducationDetailsForm.getEducationName();
    logger.info("educationName >> " + educationName);
    UserEducationDetails userEducationDetails1 = null;
    boolean isError = false;
    userEducationDetails1 = BOFactory.getUserBO().getUserEducationDetailsByUserIdandEducationName(new Long(userId).longValue(), educationName);
    userEducationDetailsForm.toValue(userEducationDetails, request);
    if (userEducationDetails1 != null)
    {
      isError = true;
      ActionErrors errors = new ActionErrors();
      
      errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError("error.educationname.alreadyexist"));
      saveErrors(request, errors);
    }
    if (!isError)
    {
      userEducationDetails = BOFactory.getUserBO().saveUserEducationDetails(userEducationDetails);
      request.setAttribute("userEducationDetailsSaved", "yes");
    }
    userEducationDetailsForm.fromValue(userEducationDetails, request);
    userEducationDetailsForm.setEducationNamesList(BOFactory.getLovTXBO().getEducationListKeyValue(user1.getSuper_user_key()));
    
    return mapping.findForward("addUserEducation");
  }
  
  public ActionForward editUserQualificationDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside saveUserEducationDetails method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    UserEducationDetailsForm userEducationDetailsForm = (UserEducationDetailsForm)form;
    
    String educationId = request.getParameter("educationId");
    String userId = request.getParameter("userId");
    logger.info("userId >> " + userId);
    logger.info("educationId >> " + educationId);
    userEducationDetailsForm.setUserId(new Long(userId).longValue());
    UserEducationDetails userEducationDetails = BOFactory.getUserBO().getUserEducationDetails(new Long(educationId).longValue());
    
    userEducationDetailsForm.fromValue(userEducationDetails, request);
    userEducationDetailsForm.setEducationNamesList(BOFactory.getLovTXBO().getEducationListKeyValue(user1.getSuper_user_key()));
    
    return mapping.findForward("addUserEducation");
  }
  
  public ActionForward updateUserEducationDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside updateUserEducationDetails method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    UserEducationDetailsForm userEducationDetailsForm = (UserEducationDetailsForm)form;
    
    String educationId = request.getParameter("educationId");
    String userId = request.getParameter("userId");
    logger.info("... userId >> " + userId);
    logger.info("... educationId >> " + educationId);
    userEducationDetailsForm.setUserId(new Long(userId).longValue());
    UserEducationDetails userEducationDetails = BOFactory.getUserBO().getUserEducationDetails(new Long(educationId).longValue());
    

    String educationName = userEducationDetailsForm.getEducationName().trim();
    logger.info("educationName >> " + educationName);
    logger.info("educationName  > >> " + userEducationDetails.getEducationName());
    
    UserEducationDetails userEducationDetails1 = null;
    boolean isError = false;
    if (educationName.equals(userEducationDetails.getEducationName().trim())) {
      userEducationDetails1 = null;
    } else {
      userEducationDetails1 = BOFactory.getUserBO().getUserEducationDetailsByUserIdandEducationName(new Long(userId).longValue(), educationName);
    }
    logger.info("userEducationDetails1 >> " + userEducationDetails1);
    userEducationDetailsForm.toValue(userEducationDetails, request);
    if (userEducationDetails1 != null)
    {
      isError = true;
      ActionErrors errors = new ActionErrors();
      
      errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError("error.educationname.alreadyexist"));
      saveErrors(request, errors);
    }
    if (!isError)
    {
      userEducationDetails = BOFactory.getUserBO().updateUserEducationDetails(userEducationDetails);
      request.setAttribute("userEducationDetailsUpdated", "yes");
    }
    userEducationDetailsForm.fromValue(userEducationDetails, request);
    userEducationDetailsForm.setEducationNamesList(BOFactory.getLovTXBO().getEducationListKeyValue(user1.getSuper_user_key()));
    
    return mapping.findForward("addUserEducation");
  }
  
  public ActionForward deleteUserEducationDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside deleteUserEducationDetails method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    UserEducationDetailsForm userEducationDetailsForm = (UserEducationDetailsForm)form;
    
    String educationId = request.getParameter("educationId");
    String userId = request.getParameter("userId");
    logger.info("... userId >> " + userId);
    logger.info("... educationId >> " + educationId);
    userEducationDetailsForm.setUserId(new Long(userId).longValue());
    UserEducationDetails userEducationDetails = BOFactory.getUserBO().getUserEducationDetails(new Long(educationId).longValue());
    
    userEducationDetailsForm.toValue(userEducationDetails, request);
    userEducationDetails = BOFactory.getUserBO().deleteUserEducationDetails(userEducationDetails);
    
    userEducationDetailsForm.fromValue(userEducationDetails, request);
    userEducationDetailsForm.setEducationNamesList(BOFactory.getLovTXBO().getEducationListKeyValue(user1.getSuper_user_key()));
    request.setAttribute("userEducationDetailsDeleted", "yes");
    return mapping.findForward("addUserEducation");
  }
}
