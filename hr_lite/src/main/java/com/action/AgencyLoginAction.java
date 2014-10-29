package com.action;

import com.bean.User;
import com.bo.UserBO;
import com.common.Common;
import com.dao.UserDAO;
import com.form.LoginForm;
import com.manager.EmailTaskManager;
import com.resources.Constant;
import com.util.EmailTask;
import com.util.EncryptDecrypt;
import com.util.StringUtils;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AgencyLoginAction
  extends CommonNoLoginAction
{
  protected static final Logger logger = Logger.getLogger(AgencyLoginAction.class);
  
  public ActionForward logon(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside logon method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    LoginForm loginForm = (LoginForm)form;
    
    String username = isCookieExist(request);
    logger.info("username from cookie" + username);
    if (username != null)
    {
      User user = UserDAO.getUserByUserNameTypeVendor(username);
      if (user == null) {
        return mapping.findForward("failure");
      }
      if (user.getStatus().equals("A"))
      {
        request.getSession().setAttribute("agency_data", user);
        
        return mapping.findForward("success");
      }
      errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError("error.login.suspended"));
      saveErrors(request, errors);
      return mapping.findForward("failure");
    }
    String rurl = request.getParameter("redirecturl");
    logger.info("rurl in logon" + rurl);
    if ((StringUtils.isNullOrEmpty(loginForm.getUsername())) && (StringUtils.isNullOrEmpty(loginForm.getPassword())))
    {
      errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError("error.emailpassword.required"));
    }
    else if ((StringUtils.isNullOrEmpty(loginForm.getUsername())) && (!StringUtils.isNullOrEmpty(loginForm.getPassword())))
    {
      errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError("error.email.required.login"));
    }
    else if ((StringUtils.isNullOrEmpty(loginForm.getPassword())) && (!StringUtils.isNullOrEmpty(loginForm.getUsername())))
    {
      errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError("error.password.required"));
    }
    else if ((!StringUtils.isNullOrEmpty(loginForm.getUsername())) && (!StringUtils.isNullOrEmpty(loginForm.getPassword())))
    {
      User user = UserDAO.isLoginSuccessTypeAgency(loginForm.getUsername(), EncryptDecrypt.encrypt(loginForm.getPassword()));
      if ((user != null) && (user.getStatus().equals("A")))
      {
        request.getSession().setAttribute("agency_data", user);
        logger.info("Logon Success");
        if ((loginForm.getRemme() != null) && (loginForm.getRemme().equals("on")))
        {
          Cookie userCookie = new Cookie(Constant.getValue("agency.cookie.name"), user.getUserName());
          userCookie.setPath("/");
          if (Constant.getValue("agency.cookie.age") != null) {
            userCookie.setMaxAge(new Integer(Constant.getValue("agency.cookie.age")).intValue());
          }
          response.addCookie(userCookie);
        }
        if ((rurl != null) && (!rurl.equals("null")))
        {
          forward.setRedirect(true);
          forward.setPath(rurl);
          return forward;
        }
        forward = mapping.findForward("success");
        
        return forward;
      }
      if ((user != null) && (user.getStatus().equals("I")))
      {
        logger.info("User account suspended");
        errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError("error.login.suspended"));
        saveErrors(request, errors);
        forward = mapping.findForward("failure");
        return forward;
      }
      logger.info("Logon Fail");
      errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError(Common.LOGIN_ERROR_MESSAGE));
      saveErrors(request, errors);
      forward = mapping.findForward("failure");
      return forward;
    }
    saveErrors(request, errors);
    forward = mapping.findForward("failure");
    return forward;
  }
  
  public ActionForward login(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside login method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    String username = isCookieExist(request);
    logger.info("username from cookie" + username);
    if (username != null)
    {
      User user = UserDAO.getUserByUserNameTypeVendor(username);
      if (user == null) {
        return mapping.findForward("failure");
      }
      if (user.getStatus().equals("A"))
      {
        request.getSession().setAttribute("agency_data", user);
        return mapping.findForward("success");
      }
      errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError("error.login.suspended"));
      saveErrors(request, errors);
      return mapping.findForward("failure");
    }
    String rurl = (String)request.getAttribute("rurl");
    request.setAttribute("rurl", rurl);
    return mapping.findForward("failure");
  }
  
  public ActionForward logout(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside logout method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    String username = isCookieExist(request);
    logger.info("username from cookie" + username);
    if (username != null)
    {
      Cookie userCookie = new Cookie(Constant.getValue("agency.cookie.name"), username);
      userCookie.setPath("/");
      userCookie.setMaxAge(0);
      response.addCookie(userCookie);
    }
    request.getSession().invalidate();
    return mapping.findForward("failure");
  }
  
  private String isCookieExist(HttpServletRequest request)
  {
    String usrname = null;
    
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (int i = 0; i < cookies.length; i++)
      {
        Cookie cookie = cookies[i];
        if (Constant.getValue("agency.cookie.name").equals(cookie.getName())) {
          return cookie.getValue();
        }
      }
    }
    return usrname;
  }
  
  public ActionForward forgotpasswordsag(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside forgotpasswordscr method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    return mapping.findForward("forgotpasswordsag");
  }
  
  public ActionForward forgotpasswordagency(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside forgotpasswordagency method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    LoginForm cForm = (LoginForm)form;
    

    User vendor = UserBO.getVendorsByUsername(cForm.getUsername());
    String password = "";
    if (vendor == null)
    {
      request.setAttribute("wrongemailid", "yes");
    }
    else
    {
      String[] tonew = { vendor.getEmailId() };
      logger.info("vendor.getEmailId() : " + vendor.getEmailId());
      
      String[] ccnew = null;
      String from = Constant.getValue("email.fromemail");
      String replyto = Constant.getValue("email.replytoemail");
      

      EmailTask emailtasknew = new EmailTask(from, tonew, ccnew, null, replyto, "dummysubject", null, "dummybody", null, 0, null);
      emailtasknew.setFunctionType(Common.FORGOT_PASSWORD);
      emailtasknew.setSubFunctionType(Common.FORGOT_PASSWORD_AGENCY);
      emailtasknew.setUser(vendor);
      EmailTaskManager.sendEmail(emailtasknew);
      
      request.setAttribute("forgotpasswordsagsent", "yes");
    }
    return mapping.findForward("forgotpasswordsag");
  }
}
