package com.action;

import com.bean.User;
import com.common.Common;
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

public class CommonAgencyAction
  extends DispatchAction
{
  protected static final Logger logger = Logger.getLogger(CommonAction.class);
  
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    User user_check = (User)request.getSession().getAttribute("agency_data");
    
    logger.info(" request.getContextPath()" + request.getContextPath());
    logger.info(" request.getRequestURI()" + request.getRequestURI());
    logger.info(" request.getHeader(referer)" + request.getQueryString());
    if ((request.getMethod() != null) && (request.getMethod().equalsIgnoreCase("POST")) && 
      (!CSRFTokenGenerator.isValidateToken(request)))
    {
      request.setAttribute(Common.ERROR_MSG, "Not a valid request");
      return mapping.findForward("alogonfail");
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
        User user = UserDAO.getUserByUserNameTypeVendor(username);
        request.getSession().setAttribute("agency_data", user);
        ActionForward forward = new ActionForward();
        forward.setRedirect(true);
        forward.setPath(redirectUrl);
        return forward;
      }
      request.setAttribute("rurl", redirectUrl);
      return mapping.findForward("alogonfail");
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
        if (Constant.getValue("agency.cookie.name").equals(cookie.getName())) {
          return cookie.getValue();
        }
      }
    }
    return usrname;
  }
}
