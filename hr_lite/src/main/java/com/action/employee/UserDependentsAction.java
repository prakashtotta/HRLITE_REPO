package com.action.employee;

import com.action.CommonAction;
import com.bean.Timezone;
import com.bean.User;
import com.bean.employee.UserDependents;
import com.bo.BOFactory;
import com.bo.UserBO;
import com.form.employee.UserDependentsForm;
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
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class UserDependentsAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(UserDependentsAction.class);
  
  public ActionForward userDependentsdetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside userDependentsdetails method");
    UserDependentsForm userDependentsForm = (UserDependentsForm)form;
    String userId = request.getParameter("userId");
    logger.info("userId >> " + userId);
    userDependentsForm.setUserId(new Long(userId).longValue());
    
    List dependentslist = new ArrayList();
    dependentslist = BOFactory.getUserBO().getUserDependentslistByUserId(new Long(userId).longValue());
    logger.info("dependentslist.size() >> " + dependentslist.size());
    

    userDependentsForm.setUserDependentsList(dependentslist);
    return mapping.findForward("userDependentsdetails");
  }
  
  public ActionForward addUserdependents(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside addUserdependents method");
    UserDependentsForm userDependentsForm = (UserDependentsForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    String userId = request.getParameter("userId");
    logger.info("userId >> " + userId);
    userDependentsForm.setUserId(new Long(userId).longValue());
    userDependentsForm.setRelationsipList(Constant.getRelationshipList(user1));
    return mapping.findForward("addUserdependents");
  }
  
  public ActionForward saveDependents(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside saveDependents method >>>>> ");
    UserDependentsForm userDependentsForm = (UserDependentsForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    UserDependents userDependents = new UserDependents();
    String dateofbirth = request.getParameter("dateofbirth");
    String userId = request.getParameter("userId");
    logger.info("userId >> " + userId);
    userDependentsForm.setUserId(new Long(userId).longValue());
    if (!StringUtils.isNullOrEmpty(dateofbirth))
    {
      String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
      
      String converteddate = DateUtil.convertFromTimezoneToTimezoneDate(dateofbirth, datepattern, user1.getTimezone().getTimezoneCode(), TimeZone.getDefault().getID());
      




      logger.info("converteddate" + converteddate);
      
      Calendar cal = DateUtil.convertStringDateToCalendar(converteddate, datepattern);
      

      userDependents.setDateofbirth(cal.getTime());
    }
    userDependentsForm.toValue(userDependents, request);
    userDependents = BOFactory.getUserBO().saveDependents(userDependents);
    
    userDependentsForm.fromValue(userDependents, request);
    userDependentsForm.setRelationsipList(Constant.getRelationshipList(user1));
    
    request.setAttribute("dependentsaved", "yes");
    return mapping.findForward("addUserdependents");
  }
  
  public ActionForward editUserDependent(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside editUserDependent method >>>>> ");
    UserDependentsForm userDependentsForm = (UserDependentsForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    
    String userdependentId = request.getParameter("userdependentId");
    logger.info("userdependentId >>> " + userdependentId);
    UserDependents userDependents = BOFactory.getUserBO().getUserDependentInfo(new Long(userdependentId).longValue());
    

    userDependentsForm.fromValue(userDependents, request);
    userDependentsForm.setRelationsipList(Constant.getRelationshipList(user1));
    return mapping.findForward("addUserdependents");
  }
  
  public ActionForward updateDependents(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside updateDependents method >>>>> ");
    UserDependentsForm userDependentsForm = (UserDependentsForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    
    String userdependentId = request.getParameter("userdependentId");
    logger.info("userdependentId >>> " + userdependentId);
    String relationship = request.getParameter("relationship");
    logger.info("relationship >>> " + relationship);
    String dateofbirth = request.getParameter("dateofbirth");
    UserDependents userDependents = BOFactory.getUserBO().getUserDependentInfo(new Long(userdependentId).longValue());
    if (!StringUtils.isNullOrEmpty(dateofbirth))
    {
      String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
      
      String converteddate = DateUtil.convertFromTimezoneToTimezoneDate(dateofbirth, datepattern, user1.getTimezone().getTimezoneCode(), TimeZone.getDefault().getID());
      




      logger.info("converteddate" + converteddate);
      
      Calendar cal = DateUtil.convertStringDateToCalendar(converteddate, datepattern);
      

      userDependents.setDateofbirth(cal.getTime());
    }
    userDependentsForm.toValue(userDependents, request);
    if ((!StringUtils.isNullOrEmpty(relationship)) && (relationship.equals("Child"))) {
      userDependents.setRelationshipOther("");
    }
    userDependents = BOFactory.getUserBO().updateDependents(userDependents);
    
    userDependentsForm.fromValue(userDependents, request);
    userDependentsForm.setRelationsipList(Constant.getRelationshipList(user1));
    request.setAttribute("dependentupdated", "yes");
    return mapping.findForward("addUserdependents");
  }
  
  public ActionForward deleteDependents(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside deleteDependents method >>>>> ");
    UserDependentsForm userDependentsForm = (UserDependentsForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    
    String userdependentId = request.getParameter("userdependentId");
    logger.info("userdependentId >>> " + userdependentId);
    UserDependents userDependents = BOFactory.getUserBO().getUserDependentInfo(new Long(userdependentId).longValue());
    
    userDependents = BOFactory.getUserBO().deleteDependents(userDependents);
    
    userDependentsForm.fromValue(userDependents, request);
    userDependentsForm.setRelationsipList(Constant.getRelationshipList(user1));
    request.setAttribute("dependentdeleted", "yes");
    return mapping.findForward("addUserdependents");
  }
  
  public ActionForward showOtherRelationshipTextbox(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside showOtherRelationshipTextbox method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    UserDependentsForm userDependentsForm = (UserDependentsForm)form;
    

    return mapping.findForward("showOtherRelationshipTextbox");
  }
  
  public ActionForward deleteUserDependentsMultiple(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside deleteUserDependentsMultiple method");
    UserDependentsForm userDependentsForm = (UserDependentsForm)form;
    String userId = request.getParameter("userId");
    userDependentsForm.setUserId(new Long(userId).longValue());
    

    String checkedItems = request.getParameter("checkedItems");
    
    BOFactory.getUserBO().deleteUserDependentsMultiple(checkedItems);
    
    List dependentslist = new ArrayList();
    dependentslist = BOFactory.getUserBO().getUserDependentslistByUserId(new Long(userId).longValue());
    
    userDependentsForm.setUserDependentsList(dependentslist);
    request.setAttribute("userdependentsmultipledeleted", "yes");
    return mapping.findForward("userDependentsdetails");
  }
}
