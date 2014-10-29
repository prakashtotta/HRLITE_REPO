package com.action;

import com.bean.ApplicantUser;
import com.bean.JobApplicant;
import com.common.Common;
import com.dao.ApplicantUserDAO;
import com.form.LoginForm;
import com.util.EncryptDecrypt;
import com.util.StringUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ApplicantLoginAction
  extends CommonNoLoginAction
{
  protected static final Logger logger = Logger.getLogger(ApplicantLoginAction.class);
  
  public ActionForward home(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside home method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    return mapping.findForward("success");
  }
  
  public ActionForward login(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside login method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
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
    
    request.getSession().invalidate();
    return mapping.findForward("failure");
  }
  
  public ActionForward logon(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside logon method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    LoginForm loginForm = (LoginForm)form;
    


    String rurl = request.getParameter("redirecturl");
    logger.info("rurl in logon" + rurl);
    if ((StringUtils.isNullOrEmpty(loginForm.getUsername())) && (StringUtils.isNullOrEmpty(loginForm.getPassword()))) {
      errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError("error.emailid.password.required"));
    } else if (StringUtils.isNullOrEmpty(loginForm.getUsername())) {
      errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError("error.emailid.required"));
    } else if (StringUtils.isNullOrEmpty(loginForm.getPassword())) {
      errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError("error.password.required"));
    } else if (StringUtils.isNullOrEmpty(loginForm.getApplicantCode())) {
      errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError("error.logincode.required"));
    }
    logger.info("loginForm.getUsername()" + loginForm.getUsername());
    logger.info("loginForm.getApplicantCode()" + loginForm.getApplicantCode());
    ApplicantUser appuser = ApplicantUserDAO.isLoginSuccess(loginForm.getUsername(), EncryptDecrypt.encrypt(loginForm.getPassword()), loginForm.getApplicantCode());
    if ((appuser != null) && (appuser.getStatus().equals("A")))
    {
      request.getSession().setAttribute("applicant_user_data", appuser);
      logger.info("Logon Success");
      if ((rurl != null) && (!rurl.equals("null")))
      {
        forward.setRedirect(true);
        forward.setPath(rurl);
        return forward;
      }
      if (((appuser.getApplicant() != null) && (appuser.getApplicant().getInterviewState().equals("Offer Released"))) || (appuser.getApplicant().getInterviewState().equals("Offer Accepted")))
      {
        forward.setRedirect(true);
        String redirecturl = "applicantuserops.do?method=applicantofferdetails&applicantId=" + appuser.getApplicant().getApplicantId() + "&secureid=" + appuser.getApplicant().getUuid();
        forward.setPath(redirecturl);
      }
      else
      {
        forward = mapping.findForward("success");
      }
      return forward;
    }
    if ((appuser != null) && (appuser.getStatus().equals("I")))
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
}
