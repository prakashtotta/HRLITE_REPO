package com.action;

import com.bean.Menu;
import com.bean.User;
import com.bo.BOFactory;
import com.bo.UserBO;
import com.dao.UserDAO;
import com.resources.Constant;
import com.security.CSRFTokenGenerator;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class CommonAction
  extends DispatchAction
{
  protected static final Logger logger = Logger.getLogger(CommonAction.class);
  
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    User user_check = (User)request.getSession().getAttribute("user_data");
    logger.info(" CSRF tocken :" + request.getParameter("csrfcode"));
    logger.info("user_check" + user_check);
    
    logger.info(" request.getRequestURI()" + request.getRequestURI());
    logger.info(" request.getHeader(referer)" + request.getQueryString());
    logger.info("request.getMethod()" + request.getMethod());
    if ((request.getMethod() != null) && (request.getMethod().equalsIgnoreCase("POST")) && 
      (!CSRFTokenGenerator.isValidateToken(request))) {
      return mapping.findForward("logonfail");
    }
    String rurl = request.getRequestURI().substring(request.getContextPath().length(), request.getRequestURI().length());
    logger.info(" rurl" + rurl);
    String redirectUrl = rurl + "?" + request.getQueryString();
    if (user_check == null)
    {
      String username = isCookieExist(request);
      logger.info("username from cookie" + username);
      if (username != null)
      {
        User user = UserDAO.getUserByUserNameTypeEmployee(username);
        if ((user != null) && (BOFactory.getUserBO().isPackageExpired(user.getSuper_user_key()))) {
          return mapping.findForward("packageexpired");
        }
        request.getSession().setAttribute("user_data", user);
        
        Menu menu = new Menu();
        menu.setupMenuAndPackage(user);
        user.setMenu(menu);
        request.getSession().setAttribute("user_data", user);
        ActionForward forward = new ActionForward();
        forward.setRedirect(true);
        forward.setPath(redirectUrl);
        return forward;
      }
      request.setAttribute("rurl", redirectUrl);
      
      return mapping.findForward("logonfail");
    }
    try
    {
      return super.execute(mapping, form, request, response);
    }
    catch (Exception e) {}
    return mapping.findForward("exception");
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
}
