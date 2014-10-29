package com.action;

import com.bean.ApplicantUser;
import com.common.Common;
import com.security.CSRFTokenGenerator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class CommonAppAction
  extends DispatchAction
{
  protected static final Logger logger = Logger.getLogger(CommonAction.class);
  
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    ApplicantUser user_check = (ApplicantUser)request.getSession().getAttribute("applicant_user_data");
    
    logger.info(" request.getContextPath()" + request.getContextPath());
    logger.info(" request.getRequestURI()" + request.getRequestURI());
    logger.info(" request.getHeader(referer)" + request.getQueryString());
    if ((request.getMethod() != null) && (request.getMethod().equalsIgnoreCase("POST")) && 
      (!CSRFTokenGenerator.isValidateToken(request)))
    {
      request.setAttribute(Common.ERROR_MSG, "Not a valid request");
      return mapping.findForward("aplogonfail");
    }
    String rurl = request.getRequestURI().substring(request.getContextPath().length(), request.getRequestURI().length());
    logger.info(" rurl" + rurl);
    String redirectUrl = rurl + "?" + request.getQueryString();
    if (user_check == null)
    {
      request.setAttribute("rurl", redirectUrl);
      return mapping.findForward("aplogonfail");
    }
    return super.execute(mapping, form, request, response);
  }
}
