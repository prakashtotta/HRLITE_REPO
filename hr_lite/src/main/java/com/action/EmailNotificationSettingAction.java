package com.action;

import com.bean.EmailNotificationSetting;
import com.bean.User;
import com.bo.BOFactory;
import com.bo.LovBO;
import com.form.EmailNotificationSettingForm;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class EmailNotificationSettingAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(EmailNotificationSettingAction.class);
  
  public ActionForward emailnotificationsetting(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside emailnotificationsetting method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    EmailNotificationSettingForm emailNotificationSettingForm = (EmailNotificationSettingForm)form;
    
    List emailfunctionlist = BOFactory.getLovBO().getAllEmailNotificationFunctionList(user1);
    emailNotificationSettingForm.setEmailFunctionNotificationList(emailfunctionlist);
    logger.info("emailfunctionlist size >> " + emailfunctionlist.size());
    
    return mapping.findForward("emailnotificationsetting");
  }
  
  public ActionForward updateEmailNotificationStatus(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updateEmailNotificationStatus method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String weid = request.getParameter("weid");
    String user = request.getParameter("user");
    String status = request.getParameter("status");
    EmailNotificationSettingForm emailNotificationSettingForm = (EmailNotificationSettingForm)form;
    logger.info("weid >>>" + weid);
    logger.info("user   >>>" + user);
    logger.info("isactive   >>>" + status);
    EmailNotificationSetting emailNotificationSetting = BOFactory.getLovBO().getEmailNotificationSettingDetails(weid, user1);
    if ((user != null) && (user.equals("Is_Hiring_Manager"))) {
      emailNotificationSetting.setIsHiringMgr(status);
    } else if ((user != null) && (user.equals("Is_Recruiter"))) {
      emailNotificationSetting.setIsRecruiter(status);
    } else if ((user != null) && (user.equals("Is_Watcher"))) {
      emailNotificationSetting.setIsWatcher(status);
    } else if ((user != null) && (user.equals("Is_Current_Owner"))) {
      emailNotificationSetting.setIsCurrentOwner(status);
    }
    emailNotificationSetting.setUpdatedBy(user1.getFirstName() + " " + user1.getLastName());
    emailNotificationSetting.setUpdatedDate(new Date());
    
    BOFactory.getLovBO().updateEmailNotificationStatus(emailNotificationSetting);
    
    List emailfunctionlist = BOFactory.getLovBO().getAllEmailNotificationFunctionList(user1);
    emailNotificationSettingForm.setEmailFunctionNotificationList(emailfunctionlist);
    
    request.setAttribute("updatedWeid", weid);
    request.setAttribute("user", user);
    
    return mapping.findForward("emailnotificationsetting");
  }
}
