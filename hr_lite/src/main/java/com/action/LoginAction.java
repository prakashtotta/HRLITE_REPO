package com.action;

import com.bean.Menu;
import com.bean.RefferalEmployee;
import com.bean.User;
import com.bo.BOFactory;
import com.bo.UserBO;
import com.common.Common;
import com.dao.UserDAO;
import com.form.LoginForm;
import com.manager.EmailTaskManager;
import com.resources.Constant;
import com.util.EmailTask;
import com.util.EncryptDecrypt;
import com.util.LDAPAuthenticator;
import com.util.StringUtils;
import java.util.Date;
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

public class LoginAction
  extends CommonNoLoginAction
{
  protected static final Logger logger = Logger.getLogger(LoginAction.class);
  
  public ActionForward backtotalent(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside backtotalent method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    RefferalEmployee refuser = (RefferalEmployee)request.getSession().getAttribute("employee_refferal_data");
    if (refuser != null)
    {
      User user = UserDAO.getUserByUserNameTypeEmployee(refuser.getUserName());
      if (user != null)
      {
        request.getSession().removeAttribute("employee_refferal_data");
        if (user.getStatus().equals("A"))
        {
          request.getSession().setAttribute("user_data", user);
          
          Menu menu = new Menu();
          menu.setupMenuAndPackage(user);
          user.setMenu(menu);
          request.getSession().setAttribute("user_data", user);
          String dashboardurl = "dashboard.do?method=dashboardlist";
          if ((!StringUtils.isNullOrEmpty(user.getDefaultPage())) && (user.getDefaultPage().equals("DASHBOARD"))) {
            dashboardurl = "dashboard.do?method=dashboardlist&ddd=" + new Date().getTime();
          } else {
            dashboardurl = "dashboard.do?method=dashboardlist&ddd=" + new Date().getTime();
          }
          forward.setRedirect(true);
          forward.setPath(dashboardurl);
          return forward;
        }
        errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError("error.login.suspended"));
        saveErrors(request, errors);
        return mapping.findForward("failure");
      }
      String msg = "Not a valid user";
      
      request.setAttribute(Common.ERROR_MSG, msg);
      
      return mapping.findForward("exception");
    }
    return null;
  }
  
  public ActionForward backtomydetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside backtomydetails method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    RefferalEmployee refuser = (RefferalEmployee)request.getSession().getAttribute("employee_refferal_data");
    if (refuser != null)
    {
      User user = UserDAO.getUserByUserNameTypeEmployee(refuser.getUserName());
      if (user != null)
      {
        request.getSession().removeAttribute("employee_refferal_data");
        if (user.getStatus().equals("A"))
        {
          request.getSession().setAttribute("user_data", user);
          
          Menu menu = new Menu();
          menu.setupMenuAndPackage(user);
          user.setMenu(menu);
          request.getSession().setAttribute("user_data", user);
          String dashboardurl = "user.do?method=mydetails";
          

          forward.setRedirect(true);
          forward.setPath(dashboardurl);
          return forward;
        }
        errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError("error.login.suspended"));
        saveErrors(request, errors);
        return mapping.findForward("failure");
      }
      String msg = "Not a valid user";
      
      request.setAttribute(Common.ERROR_MSG, msg);
      
      return mapping.findForward("exception");
    }
    return null;
  }
  
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
      User user = UserDAO.getUserByUserNameTypeEmployee(username);
      if (user == null) {
        return mapping.findForward("failure");
      }
      if ((user != null) && (BOFactory.getUserBO().isPackageExpired(user.getSuper_user_key()))) {
        return mapping.findForward("packageexpired");
      }
      if ((user != null) && (user.getStatus().equals("A")))
      {
        request.getSession().setAttribute("user_data", user);
        
        Menu menu = new Menu();
        menu.setupMenuAndPackage(user);
        user.setMenu(menu);
        request.getSession().setAttribute("user_data", user);
        String dashboardurl = "dashboard.do?method=dashboardlist&ddd=" + new Date().getTime();
        if ((!StringUtils.isNullOrEmpty(user.getDefaultPage())) && (user.getDefaultPage().equals("DASHBOARD"))) {
          dashboardurl = "dashboard.do?method=dashboardlist&ddd=" + new Date().getTime();
        } else {
          dashboardurl = "dashboard.do?method=dashboardlist&ddd=" + new Date().getTime();
        }
        forward.setRedirect(true);
        forward.setPath(dashboardurl);
        return forward;
      }
      errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError("error.login.suspended"));
      saveErrors(request, errors);
      return mapping.findForward("failure");
    }
    String rurl = request.getParameter("redirecturl");
    if ((StringUtils.isNullOrEmpty(loginForm.getUsername())) && (StringUtils.isNullOrEmpty(loginForm.getPassword()))) {
      errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError("error.emailpassword.required"));
    } else if ((StringUtils.isNullOrEmpty(loginForm.getUsername())) && (!StringUtils.isNullOrEmpty(loginForm.getPassword()))) {
      errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError("error.email.required.login"));
    } else if ((StringUtils.isNullOrEmpty(loginForm.getPassword())) && (!StringUtils.isNullOrEmpty(loginForm.getUsername()))) {
      errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError("error.password.required"));
    }
    User user = null;
    if ((!StringUtils.isNullOrEmpty(Constant.getValue("ldap.authonticate"))) && (Constant.getValue("ldap.authonticate").equals("yes")))
    {
      try
      {
        LDAPAuthenticator l = new LDAPAuthenticator();
        boolean isSucces = l.authenticate(loginForm.getUsername(), loginForm.getPassword());
        logger.info("isSucces LDAP" + isSucces);
        if (isSucces)
        {
          user = UserDAO.getUserByUserNameTypeEmployee(loginForm.getUsername());
        }
        else
        {
          logger.info("Logon Fail");
          errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError(Common.LOGIN_ERROR_MESSAGE));
          saveErrors(request, errors);
          return mapping.findForward("failure");
        }
      }
      catch (Exception e)
      {
        logger.info("Logon Fail");
        errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError(Common.LOGIN_ERROR_MESSAGE));
        saveErrors(request, errors);
        return mapping.findForward("failure");
      }
    }
    else
    {
      user = UserDAO.isLoginSuccessTypeEmployee(loginForm.getUsername(), EncryptDecrypt.encrypt(loginForm.getPassword()));
      if ((user != null) && (BOFactory.getUserBO().isPackageExpired(user.getSuper_user_key()))) {
        return mapping.findForward("packageexpired");
      }
    }
    if ((user != null) && (user.getStatus().equals("A")))
    {
      request.getSession().setAttribute("user_data", user);
      
      Menu menu = new Menu();
      menu.setupMenuAndPackage(user);
      user.setMenu(menu);
      request.getSession().setAttribute("user_data", user);
      logger.info("Logon Success");
      if ((loginForm.getRemme() != null) && (loginForm.getRemme().equals("on")))
      {
        Cookie userCookie = new Cookie(Constant.getValue("cookie.name"), user.getUserName());
        userCookie.setPath("/");
        if (Constant.getValue("cookie.age") != null) {
          userCookie.setMaxAge(new Integer(Constant.getValue("cookie.age")).intValue());
        }
        response.addCookie(userCookie);
      }
      if ((rurl != null) && (!rurl.equals("null")))
      {
        forward.setRedirect(true);
        forward.setPath(rurl);
        return forward;
      }
      String dashboardurl = "dashboard.do?method=dashboardlist&ddd=" + new Date().getTime();
      if ((!StringUtils.isNullOrEmpty(user.getDefaultPage())) && (user.getDefaultPage().equals("DASHBOARD"))) {
        dashboardurl = "dashboard.do?method=dashboardlist&ddd=" + new Date().getTime();
      } else {
        dashboardurl = "dashboard.do?method=dashboardlist&ddd=" + new Date().getTime();
      }
      forward.setRedirect(true);
      forward.setPath(dashboardurl);
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
  
  public ActionForward ssologon(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside ssologon method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    LoginForm loginForm = (LoginForm)form;
    
    String username = request.getParameter("username");
    String page = request.getParameter("page");
    String securekey = request.getParameter("securekey");
    


    boolean issecuretrue = false;
    if ((!StringUtils.isNullOrEmpty(securekey)) && 
      (EncryptDecrypt.decrypt(securekey).equals(Constant.getValue("_SHARED_SECRET_")))) {
      issecuretrue = true;
    }
    if (!issecuretrue)
    {
      logger.info("Shared secret Fail");
      errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError(Common.LOGIN_ERROR_MESSAGE));
      saveErrors(request, errors);
      forward = mapping.findForward("failure");
      return forward;
    }
    User user = UserDAO.getUserByUserNameTypeEmployee(EncryptDecrypt.decrypt(username));
    if ((user != null) && (BOFactory.getUserBO().isPackageExpired(user.getSuper_user_key()))) {
      return mapping.findForward("packageexpired");
    }
    if ((user != null) && (user.getStatus().equals("A")))
    {
      request.getSession().setAttribute("user_data", user);
      
      Menu menu = new Menu();
      menu.setupMenuAndPackage(user);
      user.setMenu(menu);
      request.getSession().setAttribute("user_data", user);
      logger.info("Logon Success");
      if ((!StringUtils.isNullOrEmpty(page)) && (page.equals("homePage")))
      {
        String dashboardurl = "dashboard.do?method=dashboardlist&ddd=" + new Date().getTime();
        if ((!StringUtils.isNullOrEmpty(user.getDefaultPage())) && (user.getDefaultPage().equals("DASHBOARD"))) {
          dashboardurl = "dashboard.do?method=dashboardlist&ddd=" + new Date().getTime();
        } else {
          dashboardurl = "dashboard.do?method=dashboardlist&ddd=" + new Date().getTime();
        }
        forward.setRedirect(true);
        forward.setPath(dashboardurl);
        return forward;
      }
      return null;
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
      User user = UserDAO.getUserByUserNameTypeEmployee(username);
      if (user == null) {
        return mapping.findForward("failure");
      }
      if ((user != null) && (BOFactory.getUserBO().isPackageExpired(user.getSuper_user_key()))) {
        return mapping.findForward("packageexpired");
      }
      if (user.getStatus().equals("A"))
      {
        request.getSession().setAttribute("user_data", user);
        
        Menu menu = new Menu();
        menu.setupMenuAndPackage(user);
        user.setMenu(menu);
        request.getSession().setAttribute("user_data", user);
        String dashboardurl = "dashboard.do?method=dashboardlist&ddd=" + new Date().getTime();
        if ((!StringUtils.isNullOrEmpty(user.getDefaultPage())) && (user.getDefaultPage().equals("DASHBOARD"))) {
          dashboardurl = "dashboard.do?method=dashboardlist&ddd=" + new Date().getTime();
        } else {
          dashboardurl = "dashboard.do?method=dashboardlist&ddd=" + new Date().getTime();
        }
        forward.setRedirect(true);
        forward.setPath(dashboardurl);
        return forward;
      }
      invalidateSession(request);
      errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError("error.login.suspended"));
      saveErrors(request, errors);
      return mapping.findForward("failure");
    }
    invalidateSession(request);
    String rurl = (String)request.getAttribute("rurl");
    request.setAttribute("rurl", rurl);
    return mapping.findForward("failure");
  }
  
  private void invalidateSession(HttpServletRequest request)
  {
    if (request.getSession() != null)
    {
      User user = (User)request.getSession().getAttribute("user_data");
      if (user != null) {
        request.getSession().removeAttribute("user_data");
      }
      RefferalEmployee refuser = (RefferalEmployee)request.getSession().getAttribute("employee_refferal_data");
      if (refuser != null) {
        request.getSession().removeAttribute("employee_refferal_data");
      }
      request.getSession().invalidate();
    }
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
      Cookie userCookie = new Cookie(Constant.getValue("cookie.name"), username);
      userCookie.setPath("/");
      userCookie.setMaxAge(0);
      response.addCookie(userCookie);
    }
    invalidateSession(request);
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
        if (Constant.getValue("cookie.name").equals(cookie.getName())) {
          return cookie.getValue();
        }
      }
    }
    return usrname;
  }
  
  public ActionForward forgotpassword(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside forgotpasswordscr method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    return mapping.findForward("forgotpassword");
  }
  
  public ActionForward forgotpasswordsubmit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside forgotpasswordagency method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    LoginForm cForm = (LoginForm)form;
    

    User user = UserBO.getUserByUserName(cForm.getUsername());
    String password = "";
    if (user == null)
    {
      request.setAttribute("wrongemailid", "yes");
    }
    else
    {
      String[] tonew = { user.getEmailId() };
      logger.info("vendor.getEmailId() : " + user.getEmailId());
      
      String[] ccnew = null;
      String from = Constant.getValue("email.fromemail");
      String replyto = Constant.getValue("email.replytoemail");
      

      EmailTask emailtasknew = new EmailTask(from, tonew, ccnew, null, replyto, "dummysubject", null, "dummybody", null, 0, null);
      emailtasknew.setFunctionType(Common.FORGOT_PASSWORD);
      emailtasknew.setSubFunctionType(Common.FORGOT_PASSWORD_USER);
      emailtasknew.setUser(user);
      EmailTaskManager.sendEmail(emailtasknew);
      
      request.setAttribute("forgotpasswordsagsent", "yes");
    }
    return mapping.findForward("forgotpassword");
  }
}
