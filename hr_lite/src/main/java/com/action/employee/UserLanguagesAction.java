package com.action.employee;

import com.action.CommonAction;
import com.bean.User;
import com.bean.employee.UserLanguages;
import com.bean.lov.Languages;
import com.bo.BOFactory;
import com.bo.LovBO;
import com.bo.UserBO;
import com.form.employee.UserLanguagesForm;
import com.resources.Constant;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class UserLanguagesAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(UserLanguagesAction.class);
  
  public ActionForward addUserLanguages(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside addUserLanguages method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    UserLanguagesForm userLanguagesForm = (UserLanguagesForm)form;
    
    String userId = request.getParameter("userId");
    logger.info("userId >> " + userId);
    userLanguagesForm.setUserId(new Long(userId).longValue());
    userLanguagesForm.setLanguagesList(BOFactory.getLovBO().getAllLanguages());
    userLanguagesForm.setLanguageFluencyList(Constant.getLanguageFluencyList(user1));
    userLanguagesForm.setCompetencyList(Constant.getSkillsRatingsList());
    
    return mapping.findForward("addUserLanguages");
  }
  
  public ActionForward saveUserLanguageDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside saveUserLanguageDetails method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    UserLanguagesForm userLanguagesForm = (UserLanguagesForm)form;
    UserLanguages userLanguages = new UserLanguages();
    String userId = request.getParameter("userId");
    logger.info("userId >> " + userId);
    userLanguagesForm.setUserId(new Long(userId).longValue());
    
    long languageId = userLanguagesForm.getLanguageId();
    logger.info("languageId >> " + languageId);
    String fluency = userLanguagesForm.getFluency();
    logger.info("fluency >> " + fluency);
    UserLanguages userLanguages1 = null;
    boolean isError = false;
    userLanguages1 = BOFactory.getUserBO().getUserLanguagesDetailsByUserIdLanguageIdandFluency(new Long(userId).longValue(), new Long(languageId).longValue(), fluency);
    
    userLanguagesForm.toValue(userLanguages, request);
    if (userLanguages1 != null)
    {
      isError = true;
      ActionErrors errors = new ActionErrors();
      
      errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError("error.languagedetails.alreadyexist"));
      saveErrors(request, errors);
    }
    if (!isError)
    {
      userLanguages = BOFactory.getUserBO().saveUserLanguageDetails(userLanguages);
      request.setAttribute("userLanguageDetailsSaved", "yes");
    }
    userLanguagesForm.fromValue(userLanguages, request);
    userLanguagesForm.setLanguagesList(BOFactory.getLovBO().getAllLanguages());
    userLanguagesForm.setLanguageFluencyList(Constant.getLanguageFluencyList(user1));
    userLanguagesForm.setCompetencyList(Constant.getSkillsRatingsList());
    
    return mapping.findForward("addUserLanguages");
  }
  
  public ActionForward editUserLanguageDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside editUserLanguageDetails method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    UserLanguagesForm userLanguagesForm = (UserLanguagesForm)form;
    
    String userId = request.getParameter("userId");
    logger.info("userId >> " + userId);
    userLanguagesForm.setUserId(new Long(userId).longValue());
    String userLangId = request.getParameter("userLangId");
    UserLanguages userLanguages = BOFactory.getUserBO().getUserLanguagesDetails(new Long(userLangId).longValue());
    userLanguagesForm.setUserLangId(new Long(userLangId).longValue());
    userLanguagesForm.fromValue(userLanguages, request);
    userLanguagesForm.setLanguagesList(BOFactory.getLovBO().getAllLanguages());
    userLanguagesForm.setLanguageFluencyList(Constant.getLanguageFluencyList(user1));
    userLanguagesForm.setCompetencyList(Constant.getSkillsRatingsList());
    
    return mapping.findForward("addUserLanguages");
  }
  
  public ActionForward updateUserLanguageDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside updateUserLanguageDetails method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    UserLanguagesForm userLanguagesForm = (UserLanguagesForm)form;
    
    String userId = request.getParameter("userId");
    logger.info("userId >> " + userId);
    userLanguagesForm.setUserId(new Long(userId).longValue());
    String userLangId = request.getParameter("userLangId");
    UserLanguages userLanguages = BOFactory.getUserBO().getUserLanguagesDetails(new Long(userLangId).longValue());
    

    long languageId = userLanguagesForm.getLanguageId();
    logger.info("languageId >> " + languageId);
    String fluency = userLanguagesForm.getFluency();
    logger.info("fluency >> " + fluency);
    UserLanguages userLanguages1 = null;
    boolean isError = false;
    if ((languageId == userLanguages.getLanguage().getLanguageId()) && (fluency.equals(userLanguages.getFluency()))) {
      userLanguages1 = null;
    } else {
      userLanguages1 = BOFactory.getUserBO().getUserLanguagesDetailsByUserIdLanguageIdandFluency(new Long(userId).longValue(), new Long(languageId).longValue(), fluency);
    }
    userLanguagesForm.toValue(userLanguages, request);
    if (userLanguages1 != null)
    {
      isError = true;
      ActionErrors errors = new ActionErrors();
      
      errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError("error.languagedetails.alreadyexist"));
      saveErrors(request, errors);
    }
    if (!isError)
    {
      userLanguages = BOFactory.getUserBO().updateUserLanguageDetails(userLanguages);
      request.setAttribute("userLanguageDetailsUpdated", "yes");
    }
    userLanguagesForm.fromValue(userLanguages, request);
    userLanguagesForm.setLanguagesList(BOFactory.getLovBO().getAllLanguages());
    userLanguagesForm.setLanguageFluencyList(Constant.getLanguageFluencyList(user1));
    userLanguagesForm.setCompetencyList(Constant.getSkillsRatingsList());
    
    return mapping.findForward("addUserLanguages");
  }
  
  public ActionForward deleteUserLanguageDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside deleteUserLanguageDetails method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    UserLanguagesForm userLanguagesForm = (UserLanguagesForm)form;
    
    String userId = request.getParameter("userId");
    logger.info("userId >> " + userId);
    userLanguagesForm.setUserId(new Long(userId).longValue());
    String userLangId = request.getParameter("userLangId");
    UserLanguages userLanguages = BOFactory.getUserBO().getUserLanguagesDetails(new Long(userLangId).longValue());
    
    userLanguagesForm.toValue(userLanguages, request);
    userLanguages = BOFactory.getUserBO().deleteUserLanguageDetails(userLanguages);
    
    userLanguagesForm.fromValue(userLanguages, request);
    userLanguagesForm.setLanguagesList(BOFactory.getLovBO().getAllLanguages());
    userLanguagesForm.setLanguageFluencyList(Constant.getLanguageFluencyList(user1));
    userLanguagesForm.setCompetencyList(Constant.getSkillsRatingsList());
    request.setAttribute("userLanguageDetailsDeleted", "yes");
    return mapping.findForward("addUserLanguages");
  }
}
