package com.security;

import com.resources.Constant;
import com.util.StringUtils;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

public class CSRFTokenGenerator
{
  protected static final Logger logger = Logger.getLogger(CSRFTokenGenerator.class);
  public static long counter = 0L;
  
  public static String getToken(HttpServletRequest request)
  {
    String securecode = "";
    HttpSession session = request.getSession(false);
    if (session != null)
    {
      securecode = generateHashString(session);
      UserToken userToken = (UserToken)session.getAttribute("USER_TOKEN");
      if (userToken != null)
      {
        userToken.store(securecode);
      }
      else
      {
        UserToken newUserToken = new UserToken();
        newUserToken.store(securecode);
        session.setAttribute("USER_TOKEN", newUserToken);
      }
    }
    logger.info("generated securecode" + securecode);
    return securecode;
  }
  
  public static boolean isValidateToken(HttpServletRequest request)
  {
    boolean issuccess = false;
    if (isSecurityEnabled())
    {
      String passedfromForm = request.getParameter("csrfcode");
      String method = request.getParameter("method");
      if ((!StringUtils.isNullOrEmpty(method)) && (Constant.withoutTokenMethodList.contains(method))) {
        return true;
      }
      logger.info("passedfromForm" + passedfromForm);
      logger.info("method" + method);
      














      boolean isRemoveTocken = true;
      if ((!StringUtils.isNullOrEmpty(method)) && (Constant.notRemovedTokenMethodList.contains(method))) {
        isRemoveTocken = false;
      }
      UserToken userToken = (UserToken)request.getSession(false).getAttribute("USER_TOKEN");
      logger.info("userToken" + userToken);
      if (userToken == null) {
        return true;
      }
      issuccess = userToken.isValid(passedfromForm, isRemoveTocken);
      logger.info("issuccess" + issuccess);
    }
    else
    {
      issuccess = true;
    }
    return issuccess;
  }
  
  public static boolean isSecurityEnabled()
  {
    String senabled = Constant.getValue("csrf.security.enabled");
    if ((!StringUtils.isNullOrEmpty(senabled)) && (senabled.equalsIgnoreCase("yes"))) {
      return true;
    }
    return false;
  }
  
  private static String getTokenForMAC(HttpSession sessionObject)
  {
    String sessionId = sessionObject.getId();
    
    return sessionId.concat(Long.toString(System.currentTimeMillis() + ++counter));
  }
  
  private static String generateHashString(HttpSession sessionObject)
  {
    return Long.toString(System.currentTimeMillis()) + getTokenForMAC(sessionObject).hashCode();
  }
}
