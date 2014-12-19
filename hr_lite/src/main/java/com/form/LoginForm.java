package com.form;

import com.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class LoginForm
  extends ActionForm
{
  String username;
  String password;
  String remme;
  String applicantCode;
  
  public String getUsername()
  {
    return this.username;
  }
  
  public void setUsername(String username)
  {
    this.username = username;
  }
  
  public String getPassword()
  {
    return this.password;
  }
  
  public void setPassword(String password)
  {
    this.password = password;
  }
  
  public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)
  {
	  System.out.println("asdaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    ActionErrors errors = new ActionErrors();
    String loginFrom = request.getParameter("loginFrom");
    System.out.println("loginFrom::"+loginFrom);
    if(!"facebook".equals(loginFrom)){
    	if ((StringUtils.isNullOrEmpty(this.username)) && (StringUtils.isNullOrEmpty(this.password))) {
    	      errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError("error.emailpassword.required"));
    	    } else if (StringUtils.isNullOrEmpty(this.username)) {
    	      errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError("error.email.required"));
    	    } else if (StringUtils.isNullOrEmpty(this.password)) {
    	      errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError("error.password.required"));
    	    }	
    }
    
    return errors;
  }
  
  public String getRemme()
  {
    return this.remme;
  }
  
  public void setRemme(String remme)
  {
    this.remme = remme;
  }
  
  public String getApplicantCode()
  {
    return this.applicantCode;
  }
  
  public void setApplicantCode(String applicantCode)
  {
    this.applicantCode = applicantCode;
  }
}
