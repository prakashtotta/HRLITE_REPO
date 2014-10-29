package com.action;

import com.bean.Country;
import com.bean.Department;
import com.bean.Organization;
import com.bean.RefferalEmployee;
import com.bean.User;
import com.bo.BOFactory;
import com.bo.LovBO;
import com.common.Common;
import com.dao.RefferalDAO;
import com.form.RefferalForm;
import com.manager.EmailTaskManager;
import com.resources.Constant;
import com.util.ConvertBeanUtil;
import com.util.EmailTask;
import com.util.EncryptDecrypt;
import com.util.StringUtils;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

public class RefferalLoginAction
  extends CommonNoLoginAction
{
  long tempSuperUserKey = 0L;
  protected static final Logger logger = Logger.getLogger(RefferalLoginAction.class);
  
  public ActionForward switchtorefferal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside switchtorefferal method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    User user1 = (User)request.getSession().getAttribute("user_data");
    if (user1 != null)
    {
      RefferalEmployee refuser = new RefferalEmployee();
      refuser = ConvertBeanUtil.convertUserToEmployeeReferral(user1, refuser);
      if ((refuser != null) && 
        (refuser != null) && (refuser.getStatus().equals("A")))
      {
        request.getSession().removeAttribute("user_data");
        request.getSession().setAttribute("employee_refferal_data", refuser);
        return mapping.findForward("success");
      }
    }
    else
    {
      return mapping.findForward("failure");
    }
    return null;
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
      RefferalEmployee user = RefferalDAO.getRefferalEmployeeByEmail(username);
      if (user == null) {
        user = RefferalDAO.getRefferalEmployeeByUserName(username);
      }
      if ((user != null) && (user.getStatus().equals("A")))
      {
        request.getSession().setAttribute("employee_refferal_data", user);
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
  
  public ActionForward logon(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside logon method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    RefferalForm refForm = (RefferalForm)form;
    
    String username = isCookieExist(request);
    logger.info("username from cookie" + username);
    if (username != null)
    {
      RefferalEmployee user = RefferalDAO.getRefferalEmployeeByEmail(username);
      if (user == null) {
        user = RefferalDAO.getRefferalEmployeeByUserName(username);
      }
      if ((user != null) && (user.getStatus().equals("A")))
      {
        request.getSession().setAttribute("employee_refferal_data", user);
        return mapping.findForward("success");
      }
      errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError("error.login.suspended"));
      saveErrors(request, errors);
      return mapping.findForward("failure");
    }
    String rurl = request.getParameter("redirecturl");
    logger.info("rurl in logon" + rurl);
    if ((StringUtils.isNullOrEmpty(refForm.getEmployeeemail())) && (StringUtils.isNullOrEmpty(refForm.getPassword())))
    {
      errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError("ref.error.emailpassword.required"));
    }
    else if ((StringUtils.isNullOrEmpty(refForm.getEmployeeemail())) && (!StringUtils.isNullOrEmpty(refForm.getPassword())))
    {
      errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError("ref.error.email.required"));
    }
    else if ((StringUtils.isNullOrEmpty(refForm.getPassword())) && (!StringUtils.isNullOrEmpty(refForm.getEmployeeemail())))
    {
      errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError("error.password.required"));
    }
    else if ((!StringUtils.isNullOrEmpty(refForm.getPassword())) && (!StringUtils.isNullOrEmpty(refForm.getEmployeeemail())))
    {
      RefferalEmployee user = RefferalDAO.isLoginSuccess(refForm.getEmployeeemail(), EncryptDecrypt.encrypt(refForm.getPassword()));
      if (user == null) {
        user = RefferalDAO.isLoginSuccessByUserName(refForm.getEmployeeemail(), EncryptDecrypt.encrypt(refForm.getPassword()));
      }
      if ((user != null) && (user.getStatus().equals("A")))
      {
        request.getSession().setAttribute("employee_refferal_data", user);
        logger.info("Logon Success111111");
        if ((refForm.getRemme() != null) && (refForm.getRemme().equals("on")))
        {
          Cookie userCookie = new Cookie(Constant.getValue("ref.cookie.name"), user.getEmployeeemail());
          userCookie.setPath("/");
          if (Constant.getValue("ref.cookie.age") != null) {
            userCookie.setMaxAge(new Integer(Constant.getValue("ref.cookie.age")).intValue());
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
        logger.info("User account is not activated");
        

        request.setAttribute("notactivated", "yes");
        forward = mapping.findForward("failure");
        return forward;
      }
      logger.info("Logon Fail");
      errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError("ref.error.login"));
      saveErrors(request, errors);
      forward = mapping.findForward("failure");
      return forward;
    }
    saveErrors(request, errors);
    forward = mapping.findForward("failure");
    return forward;
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
      Cookie userCookie = new Cookie(Constant.getValue("ref.cookie.name"), username);
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
        if (Constant.getValue("ref.cookie.name").equals(cookie.getName())) {
          return cookie.getValue();
        }
      }
    }
    return usrname;
  }
  
  public ActionForward referrallist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside referrallist method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    RefferalForm refForm = (RefferalForm)form;
    
    List orgList = BOFactory.getLovBO().getAllOrganization(this.tempSuperUserKey);
    refForm.setOrganizationList(orgList);
    
    List deptlist = new ArrayList();
    refForm.setDepartmentList(deptlist);
    if (refForm.getOrgId() != 0L) {
      refForm.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(refForm.getOrgId())));
    } else if (orgList.size() > 0) {
      refForm.setDepartmentList(deptlist);
    }
    List projectcodeList = new ArrayList();
    refForm.setProjectcodeList(projectcodeList);
    request.setAttribute("searchpagedisplay", "no");
    
    return mapping.findForward("referrallist");
  }
  
  public ActionForward searchrefferallistpage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchrefferallistpage method ....");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    RefferalForm refForm = (RefferalForm)form;
    
    String departmentId = request.getParameter("departmentId");
    refForm.setEmployeecode(refForm.getEmployeecode());
    refForm.setEmployeename(refForm.getEmployeename());
    refForm.setEmployeeemail(refForm.getEmployeeemail());
    logger.info("status from form " + refForm.getStatus());
    refForm.setStatus(refForm.getStatus());
    logger.info("orgid12345678" + refForm.getOrgId());
    refForm.setOrgId(refForm.getOrgId());
    refForm.setDepartmentId(refForm.getDepartmentId());
    logger.info("department12345678" + refForm.getDepartmentId());
    refForm.setProjectcodeId(refForm.getProjectcodeId());
    

    List orgList = BOFactory.getLovBO().getAllOrganization(this.tempSuperUserKey);
    
    refForm.setOrganizationList(orgList);
    
    List deptlist = new ArrayList();
    refForm.setDepartmentList(deptlist);
    if (orgList.size() > 0)
    {
      Organization org = (Organization)orgList.get(0);
      refForm.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(org.getOrgId())));
    }
    if (refForm.getDepartmentId() != 0L)
    {
      refForm.setProjectcodeList(BOFactory.getLovBO().getProjectCodesByDept(departmentId));
    }
    else
    {
      List projectcodeList = new ArrayList();
      refForm.setProjectcodeList(projectcodeList);
    }
    request.setAttribute("searchpagedisplay", "yes");
    
    return mapping.findForward("referrallist");
  }
  
  public ActionForward editRefferalemployee(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside editRefferalEmp method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    String userId = request.getParameter("userId");
    RefferalForm refForm = (RefferalForm)form;
    RefferalEmployee ref = RefferalDAO.getRefferalEmployee(userId);
    
    refForm.fromValue(ref, request);
    refForm.setStatus(ref.getStatus());
    logger.info(" refferalform.getStatus() " + refForm.getStatus());
    
    List orgList = new ArrayList();
    refForm.setOrganizationList(orgList);
    if (refForm.getOrgId() != 0L)
    {
      orgList = BOFactory.getLovBO().getAllOrganization(this.tempSuperUserKey);
      refForm.setOrganizationList(orgList);
    }
    List deptlist = new ArrayList();
    refForm.setDepartmentList(deptlist);
    if ((refForm.getDepartmentId() != 0L) || (ref.getDepartment() != null)) {
      refForm.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(ref.getOrganization().getOrgId())));
    }
    List projectcodeList = new ArrayList();
    refForm.setProjectcodeList(projectcodeList);
    if ((ref.getDepartment() != null) || (refForm.getProjectcodeId() != 0L))
    {
      projectcodeList = BOFactory.getLovBO().getProjectCodesByDept(String.valueOf(ref.getDepartment().getDepartmentId()));
      refForm.setProjectcodeList(projectcodeList);
    }
    List jobgradeList1 = new ArrayList();
    











    List desgList = new ArrayList();
    refForm.setDesignationList(desgList);
    if (refForm.getDesignationId() != 0L) {}
    List localeList = new ArrayList();
    refForm.setLocaleList(localeList);
    if (refForm.getLocaleId() != 0L) {
      refForm.setLocaleList(BOFactory.getLovBO().getAllLocales());
    }
    List timeZoneList = new ArrayList();
    refForm.setTimezoneList(timeZoneList);
    if (refForm.getTimezoneId() != 0L) {
      refForm.setTimezoneList(BOFactory.getLovBO().getAllTimezones());
    }
    List countryList = new ArrayList();
    refForm.setCountryList(countryList);
    if (refForm.getCountryId() != 0L)
    {
      countryList = BOFactory.getLovBO().getAllCountries();
      refForm.setCountryList(countryList);
    }
    List stateList = new ArrayList();
    refForm.setStateList(stateList);
    if (refForm.getStateId() != 0L)
    {
      stateList = BOFactory.getLovBO().getStateListByCountry(ref.getCountry().getCountryId());
      refForm.setStateList(stateList);
    }
    request.setAttribute("isUserdeleted", "no");
    request.setAttribute("editpage", "yes");
    return mapping.findForward("editRefferalemployee");
  }
  
  public ActionForward editRefferalEmp(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside editRefferalEmp method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    String userId = request.getParameter("userId");
    RefferalForm refForm = (RefferalForm)form;
    RefferalEmployee ref = RefferalDAO.getRefferalEmployee(userId);
    refForm.fromValue(ref, request);
    refForm.setStatus(ref.getStatus());
    logger.info(" refferalform.getStatus() " + refForm.getStatus());
    
    List orgList = new ArrayList();
    refForm.setOrganizationList(orgList);
    if (refForm.getOrgId() != 0L)
    {
      orgList = BOFactory.getLovBO().getAllOrganization(this.tempSuperUserKey);
      refForm.setOrganizationList(orgList);
    }
    List deptlist = new ArrayList();
    refForm.setDepartmentList(deptlist);
    if (refForm.getDepartmentId() != 0L) {
      refForm.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(ref.getOrganization().getOrgId())));
    }
    List projectcodeList = new ArrayList();
    refForm.setProjectcodeList(projectcodeList);
    if (ref.getDepartment() != null)
    {
      projectcodeList = BOFactory.getLovBO().getProjectCodesByDept(String.valueOf(ref.getDepartment().getDepartmentId()));
      refForm.setProjectcodeList(projectcodeList);
    }
    List desgList = new ArrayList();
    refForm.setDesignationList(desgList);
    if (refForm.getDesignationId() != 0L) {}
    List localeList = new ArrayList();
    refForm.setLocaleList(localeList);
    if (refForm.getLocaleId() != 0L) {
      refForm.setLocaleList(BOFactory.getLovBO().getAllLocales());
    }
    List timeZoneList = new ArrayList();
    refForm.setTimezoneList(timeZoneList);
    if (refForm.getTimezoneId() != 0L) {
      refForm.setTimezoneList(BOFactory.getLovBO().getAllTimezones());
    }
    List countryList = new ArrayList();
    refForm.setCountryList(countryList);
    if (refForm.getCountryId() != 0L)
    {
      countryList = BOFactory.getLovBO().getAllCountries();
      refForm.setCountryList(countryList);
    }
    List stateList = new ArrayList();
    refForm.setStateList(stateList);
    if (refForm.getStateId() != 0L)
    {
      stateList = BOFactory.getLovBO().getStateListByCountry(ref.getCountry().getCountryId());
      refForm.setStateList(stateList);
    }
    request.setAttribute("isUserdeleted", "no");
    request.setAttribute("editpage", "yes");
    return mapping.findForward("editPage");
  }
  
  public ActionForward loadState(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String cid = request.getParameter("cid");
    System.out.println(" **** cid :" + cid);
    
    RefferalForm refForm = (RefferalForm)form;
    refForm.setStateList(BOFactory.getLovBO().getStateListByCountry(new Long(cid).longValue()));
    

    System.out.println("size of state : " + refForm.getStateList().size());
    System.out.println(" **** test :");
    return mapping.findForward("stateList");
  }
  
  public ActionForward forgotpasswordscr(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside forgotpasswordscr method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    return mapping.findForward("forgotpasswordscr");
  }
  
  public ActionForward forgotpassword(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside forgotpassword method ... ");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    RefferalForm refForm = (RefferalForm)form;
    
    RefferalEmployee refemp = RefferalDAO.getRefferalEmployeeByEmail(refForm.getEmployeeemail());
    if (refemp == null)
    {
      request.setAttribute("wrongemailid", "yes");
    }
    else
    {
      request.setAttribute("forgotpasswordsent", "yes");
      



      String[] tonew = { refemp.getEmployeeemail() };
      logger.info("refemp.getEmployeeemail()" + refemp.getEmployeeemail());
      String[] ccnew = null;
      String from = Constant.getValue("email.fromemail");
      String replyto = Constant.getValue("email.replytoemail");
      
      EmailTask emailtasknew = new EmailTask(from, tonew, ccnew, null, replyto, "dummysubject", null, "dummybody", null, 0, null);
      emailtasknew.setFunctionType(Common.FORGOT_PASSWORD);
      emailtasknew.setSubFunctionType(Common.FORGOT_PASSWORD_REFERRAL);
      emailtasknew.setEmployeeReferral(refemp);
      EmailTaskManager.sendEmail(emailtasknew);
    }
    return mapping.findForward("forgotpasswordscr");
  }
  
  public ActionForward updatestatus(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updatestatus method");
    
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String userId = request.getParameter("employeeReferalId");
    String status = request.getParameter("status");
    RefferalForm refform = (RefferalForm)form;
    logger.info("Inside updatestatus method" + userId);
    logger.info("Inside updatestatus method" + status);
    
    RefferalEmployee ref = RefferalDAO.getRefferalEmployee(userId);
    
    ref.setStatus(status);
    ref.setUpdatedBy(user1.getUserName());
    ref.setUpdatedDate(new Date());
    
    RefferalDAO.updateRefferalEmployee(ref);
    

    return mapping.findForward("updatestatus");
  }
}
