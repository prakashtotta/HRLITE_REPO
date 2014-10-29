package com.action.employee;

import com.action.CommonAction;
import com.bean.User;
import com.bean.employee.UserSkills;
import com.bo.BOFactory;
import com.bo.LovTXBO;
import com.bo.UserBO;
import com.form.employee.UserSkillsForm;
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

public class UserSkillsAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(UserSkillsAction.class);
  
  public ActionForward addUserSkills(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside addUserSkills method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    UserSkillsForm userSkillsForm = (UserSkillsForm)form;
    String userId = request.getParameter("userId");
    logger.info("userId >> " + userId);
    userSkillsForm.setUserId(new Long(userId).longValue());
    
    userSkillsForm.setUserSkillList(BOFactory.getLovTXBO().getTechnicalSkillListKeyValue(user1.getSuper_user_key()));
    userSkillsForm.setSkillRatingList(Constant.skillsRatingsList);
    userSkillsForm.setSkillYearsList(Constant.skillsYearsList);
    return mapping.findForward("addUserSkills");
  }
  
  public ActionForward saveUserSkillsDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside saveUserSkillsDetails method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    UserSkillsForm userSkillsForm = (UserSkillsForm)form;
    UserSkills userSkills = new UserSkills();
    String userId = request.getParameter("userId");
    logger.info("userId >> " + userId);
    userSkillsForm.setUserId(new Long(userId).longValue());
    String skillname = userSkillsForm.getSkillname().trim();
    UserSkills userSkills1 = null;
    userSkills1 = BOFactory.getUserBO().getUserSkillDetailsByUserIdandSkillName(new Long(userId).longValue(), skillname);
    boolean isError = false;
    userSkillsForm.toValue(userSkills, request);
    if (userSkills1 != null)
    {
      isError = true;
      ActionErrors errors = new ActionErrors();
      
      errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError("error.skillname.alreadyexist"));
      saveErrors(request, errors);
    }
    if (!isError)
    {
      userSkills = BOFactory.getUserBO().saveUserSkillsDetails(userSkills);
      request.setAttribute("userSkillsDetailsSaved", "yes");
    }
    userSkillsForm.fromValue(userSkills, request);
    userSkillsForm.setUserSkillList(BOFactory.getLovTXBO().getTechnicalSkillListKeyValue(user1.getSuper_user_key()));
    userSkillsForm.setSkillRatingList(Constant.skillsRatingsList);
    userSkillsForm.setSkillYearsList(Constant.skillsYearsList);
    
    return mapping.findForward("addUserSkills");
  }
  
  public ActionForward editUserSkillDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside editUserSkillDetails method");
    UserSkillsForm UserSkillsForm = (UserSkillsForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    
    String skillId = request.getParameter("skillId");
    String userId = request.getParameter("userId");
    logger.info("userId >> " + userId);
    UserSkillsForm.setUserId(new Long(userId).longValue());
    
    UserSkills userSkills = BOFactory.getUserBO().getUserSkillDetails(new Long(skillId).longValue());
    
    UserSkillsForm.fromValue(userSkills, request);
    UserSkillsForm.setUserSkillList(BOFactory.getLovTXBO().getTechnicalSkillListKeyValue(user1.getSuper_user_key()));
    UserSkillsForm.setSkillRatingList(Constant.skillsRatingsList);
    UserSkillsForm.setSkillYearsList(Constant.skillsYearsList);
    
    return mapping.findForward("addUserSkills");
  }
  
  public ActionForward updateUserSkillsDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside updateUserSkillsDetails method");
    UserSkillsForm userSkillsForm = (UserSkillsForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    
    String skillId = request.getParameter("skillId");
    String userId = request.getParameter("userId");
    logger.info("userId >> " + userId);
    userSkillsForm.setUserId(new Long(userId).longValue());
    
    UserSkills userSkills = BOFactory.getUserBO().getUserSkillDetails(new Long(skillId).longValue());
    

    String skillname = userSkillsForm.getSkillname().trim();
    UserSkills userSkills1 = null;
    
    boolean isError = false;
    if (skillname.equals(userSkills.getSkillname().trim())) {
      userSkills1 = null;
    } else {
      userSkills1 = BOFactory.getUserBO().getUserSkillDetailsByUserIdandSkillName(new Long(userId).longValue(), skillname);
    }
    userSkillsForm.toValue(userSkills, request);
    if (userSkills1 != null)
    {
      isError = true;
      ActionErrors errors = new ActionErrors();
      
      errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError("error.skillname.alreadyexist"));
      saveErrors(request, errors);
    }
    if (!isError)
    {
      userSkills = BOFactory.getUserBO().updateUserSkillsDetails(userSkills);
      request.setAttribute("userSkillsDetailsupdated", "yes");
    }
    userSkillsForm.fromValue(userSkills, request);
    userSkillsForm.setUserSkillList(BOFactory.getLovTXBO().getTechnicalSkillListKeyValue(user1.getSuper_user_key()));
    userSkillsForm.setSkillRatingList(Constant.skillsRatingsList);
    userSkillsForm.setSkillYearsList(Constant.skillsYearsList);
    
    return mapping.findForward("addUserSkills");
  }
  
  public ActionForward deleteUserSkillsDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside deleteUserSkillsDetails method");
    UserSkillsForm UserSkillsForm = (UserSkillsForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    String skillId = request.getParameter("skillId");
    String userId = request.getParameter("userId");
    logger.info("userId >> " + userId);
    UserSkillsForm.setUserId(new Long(userId).longValue());
    
    UserSkills userSkills = BOFactory.getUserBO().getUserSkillDetails(new Long(skillId).longValue());
    
    UserSkillsForm.toValue(userSkills, request);
    userSkills = BOFactory.getUserBO().deleteUserSkillsDetails(userSkills);
    
    UserSkillsForm.fromValue(userSkills, request);
    UserSkillsForm.setUserSkillList(BOFactory.getLovTXBO().getTechnicalSkillListKeyValue(user1.getSuper_user_key()));
    UserSkillsForm.setSkillRatingList(Constant.skillsRatingsList);
    UserSkillsForm.setSkillYearsList(Constant.skillsYearsList);
    request.setAttribute("userSkillsDetailsDeleted", "yes");
    return mapping.findForward("addUserSkills");
  }
}
