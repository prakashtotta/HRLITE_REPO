package com.action;

import com.bean.RefferalEmployee;
import com.common.Common;
import com.dao.RefferalDAO;
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

public class CommonRefAction
  extends DispatchAction
{
  protected static final Logger logger = Logger.getLogger(CommonAction.class);
  
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    RefferalEmployee user_check = (RefferalEmployee)request.getSession().getAttribute("employee_refferal_data");
    
    logger.info(" request.getContextPath()" + request.getContextPath());
    logger.info(" request.getRequestURI()" + request.getRequestURI());
    logger.info(" request.getHeader(referer)" + request.getQueryString());
    if ((request.getMethod() != null) && (request.getMethod().equalsIgnoreCase("POST")) && 
      (!CSRFTokenGenerator.isValidateToken(request)))
    {
      request.setAttribute(Common.ERROR_MSG, "Not a valid request");
      return mapping.findForward("reflogonfail");
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
        RefferalEmployee user = RefferalDAO.getRefferalEmployeeByEmail(username);
        if (user == null) {
          user = RefferalDAO.getRefferalEmployeeByUserName(username);
        }
        request.getSession().setAttribute("employee_refferal_data", user);
        ActionForward forward = new ActionForward();
        forward.setRedirect(true);
        forward.setPath(redirectUrl);
        return forward;
      }
      request.setAttribute("rurl", redirectUrl);
      return mapping.findForward("reflogonfail");
    }
    return super.execute(mapping, form, request, response);
  }
  
  private String isCookieExist(HttpServletRequest request)
  {
    String usrname = null;
    
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (int i = 0; i < cookies.length; i++)
      {
        Cookie cookie = cookies[i];
        if (Constant.getValue("ref.cookie.name").equals(cookie.getName())) {
          return cookie.getValue();
        }
      }
    }
    return usrname;
  }
}
