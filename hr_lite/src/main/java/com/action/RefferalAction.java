package com.action;

import com.bean.Country;
import com.bean.Department;
import com.bean.Organization;
import com.bean.ProfilePhoto;
import com.bean.RefferalEmployee;
import com.bean.User;
import com.bo.BOFactory;
import com.bo.LovBO;
import com.bo.UserBO;
import com.common.Common;
import com.dao.RefferalDAO;
import com.dao.UserDAO;
import com.form.RefferalForm;
import com.manager.EmailTaskManager;
import com.resources.Constant;
import com.util.ConvertBeanUtil;
import com.util.EmailTask;
import com.util.EmailValidateUtils;
import com.util.EncryptDecrypt;
import com.util.StringUtils;
import java.io.PrintStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

public class RefferalAction
  extends CommonNoLoginAction
{
  long tempSuperUserKey = 0L;
  protected static final Logger logger = Logger.getLogger(RefferalAction.class);
  
  public ActionForward reg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside reg method");
    try
    {
      ActionErrors errors = new ActionErrors();
      ActionForward forward = new ActionForward();
      RefferalForm refForm = (RefferalForm)form;
      String emailid = request.getParameter("emailid");
      if (!StringUtils.isNullOrEmpty(emailid))
      {
        User user = UserDAO.getUserByEmailId(emailid);
        if (user != null)
        {
          refForm.convertUserToReferral(user);
          request.setAttribute("userdataexist", "yes");
        }
        else
        {
          request.setAttribute("userdataexist", "no");
        }
      }
      refForm.setEmployeeemail(emailid);
      




      List orgList = BOFactory.getLovBO().getAllOrganization(this.tempSuperUserKey);
      refForm.setOrganizationList(orgList);
      List deptlist = new ArrayList();
      refForm.setDepartmentList(deptlist);
      if (orgList.size() > 0)
      {
        Organization org = (Organization)orgList.get(0);
        deptlist = BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(org.getOrgId()));
        refForm.setDepartmentList(deptlist);
      }
      List projectCodeList = new ArrayList();
      if (deptlist.size() > 0)
      {
        Department dept = (Department)deptlist.get(0);
        projectCodeList = BOFactory.getLovBO().getProjectCodesByDept(String.valueOf(dept.getDepartmentId()));
      }
      refForm.setProjectcodeList(projectCodeList);
      







      refForm.setLocaleList(BOFactory.getLovBO().getAllLocales());
      refForm.setTimezoneList(BOFactory.getLovBO().getAllTimezones());
      List countryList = BOFactory.getLovBO().getAllCountries();
      refForm.setCountryList(countryList);
      Country ct = (Country)countryList.get(0);
      List stateList = BOFactory.getLovBO().getStateListByCountry(ct.getCountryId());
      refForm.setStateList(stateList);
      

      return mapping.findForward("regscreen");
    }
    catch (Exception e)
    {
      logger.info("exception in reg method", e);
      String msg = "Not a valid user";
      
      request.setAttribute(Common.ERROR_MSG, msg);
    }
    return mapping.findForward("exception");
  }
  
  public ActionForward emailscr(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside emailscr method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    return mapping.findForward("emailscr");
  }
  
  public ActionForward getdetailbyemail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside getdetailbyemail method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    RefferalForm refForm = (RefferalForm)form;
    refForm.setEmployeeemail(refForm.getEmployeeemail());
    if (!EmailValidateUtils.isEmailIdBelongsToDomainsList(refForm.getEmployeeemail()))
    {
      request.setAttribute("emailidnotcorrect", "yes");
    }
    else
    {
      String emailid = RefferalDAO.isEmailIdExist(refForm.getEmployeeemail());
      if (!StringUtils.isNullOrEmpty(emailid)) {
        request.setAttribute("isUseralreadyexist", "yes");
      } else {
        request.setAttribute("backfromgetemail", "yes");
      }
    }
    return mapping.findForward("emailscr");
  }
  
  public ActionForward home(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside home method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    return mapping.findForward("success");
  }
  
  public ActionForward referralprofile(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside home method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String userId = request.getParameter("userId");
    userId = EncryptDecrypt.decrypt(userId);
    User userprofile = BOFactory.getUserBO().getUserByUserid(new Long(userId).longValue());
    if (userprofile != null)
    {
      RefferalEmployee refuser = new RefferalEmployee();
      refuser = ConvertBeanUtil.convertUserToEmployeeReferral(userprofile, refuser);
      request.getSession().setAttribute("employee_refferal_data", refuser);
    }
    return mapping.findForward("referralprofile");
  }
  
  public ActionForward regsubmit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside regsubmit method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    RefferalForm refForm = (RefferalForm)form;
    RefferalEmployee refemp = new RefferalEmployee();
    String userdataexist = request.getParameter("userdataexist");
    String emailid = request.getParameter("emailid");
    refForm.setEmployeeemail(emailid);
    logger.info("disabled email id : " + refForm.getEmployeeemail());
    refForm.toValue(refemp, request);
    refemp.setStatus("I");
    refemp.setVarificationcode(UUID.randomUUID().toString());
    if ((!StringUtils.isNullOrEmpty(userdataexist)) && (!userdataexist.equals("yes")))
    {
      if (!EmailValidateUtils.isEmailIdBelongsToDomainsList(refForm.getEmployeeemail()))
      {
        request.setAttribute("emailidnotcorrect", "yes");
        




        List orgList = BOFactory.getLovBO().getAllOrganization(this.tempSuperUserKey);
        refForm.setOrganizationList(orgList);
        List deptlist = new ArrayList();
        refForm.setDepartmentList(deptlist);
        if (orgList.size() > 0)
        {
          Organization org = (Organization)orgList.get(0);
          deptlist = BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(org.getOrgId()));
          refForm.setDepartmentList(deptlist);
        }
        List projectCodeList = new ArrayList();
        if (deptlist.size() > 0)
        {
          Department dept = (Department)deptlist.get(0);
          projectCodeList = BOFactory.getLovBO().getProjectCodesByDept(String.valueOf(dept.getDepartmentId()));
        }
        refForm.setProjectcodeList(projectCodeList);
        
        refForm.setLocaleList(BOFactory.getLovBO().getAllLocales());
        refForm.setTimezoneList(BOFactory.getLovBO().getAllTimezones());
        List countryList = BOFactory.getLovBO().getAllCountries();
        refForm.setCountryList(countryList);
        Country ct = (Country)countryList.get(0);
        List stateList = BOFactory.getLovBO().getStateListByCountry(ct.getCountryId());
        refForm.setStateList(stateList);
        

        return mapping.findForward("regscreen");
      }
    }
    else if (!StringUtils.isNullOrEmpty(emailid))
    {
      User user = UserDAO.getUserByEmailId(emailid);
      if (user != null)
      {
        refemp = ConvertBeanUtil.convertUserToEmployeeReferral(user, refemp);
        if ((refemp.getPassword() == null) && 
          (refForm.getPassword() != null)) {
          refemp.setPassword(EncryptDecrypt.encrypt(refForm.getPassword()));
        }
      }
    }
    RefferalEmployee refempold = RefferalDAO.getRefferalEmployeeByEmail(refemp.getEmployeeemail());
    if (refempold != null)
    {
      refemp.setEmployeeReferalId(refempold.getEmployeeReferalId());
      
      RefferalDAO.updateRefferalEmployee(refemp);
    }
    else
    {
      RefferalDAO.saveeRefferalEmployee(refemp);
    }
    refForm.fromValue(refemp, request);
    refForm.setVarificationcode("");
    request.setAttribute("employeerefsaved", "yes");
    
    refemp = RefferalDAO.getRefferalEmployee(String.valueOf(refemp.getEmployeeReferalId()));
    String[] tonew = { refemp.getEmployeeemail() };
    String[] ccnew = null;
    String from = Constant.getValue("email.fromemail");
    String replyto = Constant.getValue("email.replytoemail");
    
    EmailTask emailtasknew = new EmailTask(from, tonew, ccnew, null, replyto, "dummysubject", null, "dummybody", null, 0, null);
    emailtasknew.setFunctionType(Common.REFERRAL_USER_REGISTRATION);
    emailtasknew.setEmployeeReferral(refemp);
    EmailTaskManager.sendEmail(emailtasknew);
    
    return mapping.findForward("regscreen");
  }
  
  public ActionForward verificationcheck(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside verificationcheck method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    RefferalForm refForm = (RefferalForm)form;
    
    String employeeReferalId = request.getParameter("employeeReferalId");
    RefferalEmployee refemp = RefferalDAO.getRefferalEmployee(employeeReferalId);
    if (refemp.getVarificationcode().equals(refForm.getVarificationcode()))
    {
      refemp.setStatus("A");
      RefferalDAO.updateRefferalEmployee(refemp);
      request.getSession().setAttribute("employee_refferal_data", refemp);
      request.setAttribute("successvarificationcode", "yes");
    }
    else
    {
      request.setAttribute("wrongvarificationcode", "yes");
      request.setAttribute("employeerefsaved", "yes");
    }
    return mapping.findForward("regscreen");
  }
  
  public ActionForward verificationcheckdirect(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside verificationcheckdirect method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    RefferalForm refForm = (RefferalForm)form;
    
    String employeeReferalId = request.getParameter("id");
    if (!StringUtils.isNullOrEmpty(employeeReferalId)) {
      employeeReferalId = EncryptDecrypt.decrypt(employeeReferalId);
    }
    String vcode = request.getParameter("code");
    if (!StringUtils.isNullOrEmpty(employeeReferalId)) {
      vcode = EncryptDecrypt.decrypt(vcode);
    }
    RefferalEmployee refemp = RefferalDAO.getRefferalEmployee(employeeReferalId);
    if (refemp.getVarificationcode().equals(vcode))
    {
      refemp.setStatus("A");
      RefferalDAO.updateRefferalEmployee(refemp);
      request.getSession().setAttribute("employee_refferal_data", refemp);
      request.setAttribute("successvarificationcode", "yes");
    }
    else
    {
      request.setAttribute("wrongvarificationcode", "yes");
      request.setAttribute("employeerefsaved", "yes");
    }
    return mapping.findForward("regscreen");
  }
  
  public ActionForward startverificationcheck(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside startverificationcheck method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    RefferalForm refForm = (RefferalForm)form;
    String isCorrectDomain = "no";
    if (!EmailValidateUtils.isEmailIdBelongsToDomainsList(refForm.getEmployeeemail()))
    {
      request.setAttribute("emailidnotcorrect", "yes");
      return mapping.findForward("verfificationstartscr");
    }
    RefferalEmployee refemp = RefferalDAO.getRefferalEmployeeByEmail(refForm.getEmployeeemail());
    if (refemp == null)
    {
      request.setAttribute("wrongemailid", "yes");
      return mapping.findForward("verfificationstartscr");
    }
    request.setAttribute("employeerefsaved", "yes");
    






    String[] tonew = { refemp.getEmployeeemail() };
    logger.info("refemp.getEmployeeemail()" + refemp.getEmployeeemail());
    String[] ccnew = null;
    String from = Constant.getValue("email.fromemail");
    String replyto = Constant.getValue("email.replytoemail");
    logger.info("emailtasknew");
    EmailTask emailtasknew = new EmailTask(from, tonew, ccnew, null, replyto, "dummysubject", null, "dummybody", null, 0, null);
    emailtasknew.setFunctionType(Common.REFERRAL_USER_REGISTRATION);
    emailtasknew.setEmployeeReferral(refemp);
    EmailTaskManager.sendEmail(emailtasknew);
    logger.info("emailtasknew1" + refemp);
    refForm.fromValue(refemp, request);
    return mapping.findForward("regscreen");
  }
  
  public ActionForward verfificationstartscr(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside verfificationstartscr method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    return mapping.findForward("verfificationstartscr");
  }
  
  public ActionForward forgotpasswordsag(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside forgotpasswordscr method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    return mapping.findForward("forgotpasswordsag");
  }
  
  public ActionForward mydetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside mydetails method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    String refferalid = request.getParameter("refferalid");
    RefferalForm refferalform = (RefferalForm)form;
    RefferalEmployee refferalemployee = RefferalDAO.getRefferalEmployee(refferalid);
    


    refferalform.fromValue(refferalemployee, request);
    
    return mapping.findForward("mydetails");
  }
  
  public ActionForward editRefferal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside editRefferal method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    String refferalid = request.getParameter("refferalid");
    
    RefferalForm refferalform = (RefferalForm)form;
    
    RefferalEmployee refferalemployee = RefferalDAO.getRefferalEmployee(refferalid);
    




    refferalform.fromValue(refferalemployee, request);
    
    request.setAttribute("updatesuccessful", "no");
    return mapping.findForward("editrefferal");
  }
  
  public ActionForward updateRefferal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside editRefferal method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    String refferalid = request.getParameter("refferalid");
    logger.info("refferalid :" + refferalid);
    
    RefferalForm refferalform = (RefferalForm)form;
    
    RefferalEmployee refferalemployee = RefferalDAO.getRefferalEmployee(refferalid);
    logger.info("** email : " + refferalemployee.getEmployeeemail());
    
    refferalform.toValue(refferalemployee, request);
    

    RefferalDAO.updateRefferalEmployee(refferalemployee);
    refferalform.fromValue(refferalemployee, request);
    List orgList = BOFactory.getLovBO().getAllOrganization(this.tempSuperUserKey);
    refferalform.setOrganizationList(orgList);
    
    List deptlist = new ArrayList();
    refferalform.setDepartmentList(deptlist);
    if (refferalemployee.getOrganization() != null) {
      refferalform.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(refferalemployee.getOrganization().getOrgId())));
    }
    List projectcodeList = new ArrayList();
    refferalform.setProjectcodeList(projectcodeList);
    if (refferalemployee.getDepartment() != null)
    {
      projectcodeList = BOFactory.getLovBO().getProjectCodesByDept(String.valueOf(refferalemployee.getDepartment().getDepartmentId()));
      refferalform.setProjectcodeList(projectcodeList);
    }
    refferalform.setLocaleList(BOFactory.getLovBO().getAllLocales());
    refferalform.setTimezoneList(BOFactory.getLovBO().getAllTimezones());
    



    List countryList = BOFactory.getLovBO().getAllCountries();
    refferalform.setCountryList(countryList);
    
    List stateList = BOFactory.getLovBO().getStateListByCountry(refferalemployee.getCountry().getCountryId());
    refferalform.setStateList(stateList);
    
    refferalform.setEmployeeReferalId(refferalemployee.getEmployeeReferalId());
    request.setAttribute("updatesuccessful", "yes");
    return mapping.findForward("editrefferal");
  }
  
  public ActionForward updateRefferalEmployee(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside editRefferal method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    String refferalid = request.getParameter("refferalid");
    logger.info("refferalid :" + refferalid);
    
    RefferalForm refferalform = (RefferalForm)form;
    
    RefferalEmployee refferalemployee = RefferalDAO.getRefferalEmployee(refferalid);
    logger.info("** email : " + refferalemployee.getEmployeeemail());
    
    refferalform.toValue(refferalemployee, request);
    

    RefferalDAO.updateRefferalEmployee(refferalemployee);
    refferalform.fromValue(refferalemployee, request);
    List orgList = BOFactory.getLovBO().getAllOrganization(this.tempSuperUserKey);
    refferalform.setOrganizationList(orgList);
    
    List deptlist = new ArrayList();
    refferalform.setDepartmentList(deptlist);
    refferalform.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(refferalemployee.getOrganization().getOrgId())));
    
    List projectcodeList = new ArrayList();
    refferalform.setProjectcodeList(projectcodeList);
    if (refferalemployee.getDepartment() != null)
    {
      projectcodeList = BOFactory.getLovBO().getProjectCodesByDept(String.valueOf(refferalemployee.getDepartment().getDepartmentId()));
      refferalform.setProjectcodeList(projectcodeList);
    }
    refferalform.setLocaleList(BOFactory.getLovBO().getAllLocales());
    refferalform.setTimezoneList(BOFactory.getLovBO().getAllTimezones());
    



    List countryList = BOFactory.getLovBO().getAllCountries();
    refferalform.setCountryList(countryList);
    
    List stateList = BOFactory.getLovBO().getStateListByCountry(refferalemployee.getCountry().getCountryId());
    refferalform.setStateList(stateList);
    

    request.setAttribute("updatesuccessful", "yes");
    return mapping.findForward("editPage");
  }
  
  public ActionForward changePassword(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside changepasswordscr method");
    RefferalForm refferalform = (RefferalForm)form;
    String refferalid = request.getParameter("refferalid");
    
    refferalform.setEmployeeReferalId(new Long(refferalid).longValue());
    refferalform.setCurrentpassword(" ");
    request.setAttribute("ispasswordchanged", "no");
    return mapping.findForward("changepassword");
  }
  
  public ActionForward changepasswordsubmit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside changepasswordsubmit method");
    RefferalForm refferalform = (RefferalForm)form;
    String refferalid = request.getParameter("refferalid");
    logger.info("refferalid  : " + refferalid);
    RefferalEmployee refferalemployee = RefferalDAO.getRefferalEmployee(refferalid);
    
    String currentpasswordenc = EncryptDecrypt.encrypt(refferalform.getCurrentpassword());
    if (!currentpasswordenc.equals(refferalemployee.getPassword()))
    {
      refferalform.setCurrentpassword("");
      refferalform.setPassword("");
      refferalform.setCpassword("");
      refferalform.setEmployeeReferalId(new Long(refferalid).longValue());
      
      request.setAttribute("ispasswordchanged", "no");
      request.setAttribute("currentpasswordwrong", "yes");
    }
    else
    {
      refferalemployee.setPassword(EncryptDecrypt.encrypt(refferalform.getPassword()));
      RefferalDAO.updateRefferalEmployee(refferalemployee);
      
      request.setAttribute("ispasswordchanged", "yes");
    }
    return mapping.findForward("changepassword");
  }
  
  public ActionForward uploadUserPhoto(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside uploadUserPhoto method ... ");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    RefferalEmployee user1 = (RefferalEmployee)request.getSession().getAttribute("employee_refferal_data");
    logger.info("user1 : " + user1);
    logger.info("Inside uploadUserPhoto method ... 1 ");
    RefferalEmployee usr = RefferalDAO.getRefferalEmp(user1.getEmployeeReferalId());
    RefferalForm userform = (RefferalForm)form;
    logger.info("Inside uploadUserPhoto method ... 2 ");
    FormFile myFile = userform.getProfilePhoto();
    if (myFile != null)
    {
      logger.info("Inside uploadUserPhoto method ... 3 ");
      String contentType = myFile.getContentType();
      String fileName = myFile.getFileName();
      int fileSize = myFile.getFileSize();
      byte[] fileData = myFile.getFileData();
      
      ProfilePhoto pp = new ProfilePhoto();
      Blob blob = null;
      
      blob = new SerialBlob(fileData);
      pp.setProfilePhoto(blob);
      
      pp.setUpdatedBy(user1.getUserName());
      pp.setUpdatedDate(new Date());
      
      pp = RefferalDAO.saveUserProfilePhoto(pp);
      
      usr.setProfilePhotoId(pp.getProfilePhotoId());
      
      RefferalDAO.updateRefferalEmployee(usr);
      logger.info("Inside uploadUserPhoto method ... 4 ");
    }
    userform.fromValue(usr, request);
    logger.info("Inside uploadUserPhoto method ... 5 ");
    return mapping.findForward("mydetails");
  }
  
  public ActionForward loadState(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside loadState method .... refferal ...");
    String cid = request.getParameter("countryId");
    System.out.println("cid : " + cid);
    RefferalForm tmplForm = (RefferalForm)form;
    
    tmplForm.setStateList(BOFactory.getLovBO().getStateListByCountry(new Long(cid).longValue()));
    
    return mapping.findForward("stateList");
  }
  
  public ActionForward deleteRefferalEmployee(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside delete Refferal Employee method");
    RefferalForm refferalform = (RefferalForm)form;
    RefferalEmployee user1 = (RefferalEmployee)request.getSession().getAttribute("employee_refferal_data");
    String employeeReferalId = request.getParameter("employeeReferalId");
    
    RefferalEmployee ref = RefferalDAO.getRefferalEmployee(employeeReferalId);
    ref.setStatus("D");
    ref.setUpdatedBy(user1.getUserName());
    ref.setUpdatedDate(new Date());
    
    RefferalDAO.updateRefferalEmployee(ref);
    refferalform.fromValue(ref, request);
    List orgList = BOFactory.getLovBO().getAllOrganization(this.tempSuperUserKey);
    refferalform.setOrganizationList(orgList);
    
    List deptlist = new ArrayList();
    refferalform.setDepartmentList(deptlist);
    refferalform.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(ref.getOrganization().getOrgId())));
    
    List projectcodeList = new ArrayList();
    refferalform.setProjectcodeList(projectcodeList);
    if (ref.getDepartment() != null)
    {
      projectcodeList = BOFactory.getLovBO().getProjectCodesByDept(String.valueOf(ref.getDepartment().getDepartmentId()));
      refferalform.setProjectcodeList(projectcodeList);
    }
    refferalform.setLocaleList(BOFactory.getLovBO().getAllLocales());
    refferalform.setTimezoneList(BOFactory.getLovBO().getAllTimezones());
    



    List countryList = BOFactory.getLovBO().getAllCountries();
    refferalform.setCountryList(countryList);
    
    List stateList = BOFactory.getLovBO().getStateListByCountry(ref.getCountry().getCountryId());
    refferalform.setStateList(stateList);
    
    request.setAttribute("isUserdeleted", "yes");
    
    return mapping.findForward("editPage");
  }
  
  public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside delete Refferal Employee method");
    RefferalForm refferalform = (RefferalForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    String employeeReferalId = request.getParameter("employeeReferalId");
    
    RefferalEmployee ref = RefferalDAO.getRefferalEmployee(employeeReferalId);
    ref.setStatus("D");
    ref.setUpdatedBy(user1.getUserName());
    ref.setUpdatedDate(new Date());
    
    RefferalDAO.updateRefferalEmployee(ref);
    refferalform.fromValue(ref, request);
    List orgList = BOFactory.getLovBO().getAllOrganization(this.tempSuperUserKey);
    refferalform.setOrganizationList(orgList);
    
    List deptlist = new ArrayList();
    refferalform.setDepartmentList(deptlist);
    refferalform.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(ref.getOrganization().getOrgId())));
    
    List projectcodeList = new ArrayList();
    refferalform.setProjectcodeList(projectcodeList);
    if (ref.getDepartment() != null)
    {
      projectcodeList = BOFactory.getLovBO().getProjectCodesByDept(String.valueOf(ref.getDepartment().getDepartmentId()));
      refferalform.setProjectcodeList(projectcodeList);
    }
    refferalform.setLocaleList(BOFactory.getLovBO().getAllLocales());
    refferalform.setTimezoneList(BOFactory.getLovBO().getAllTimezones());
    



    List countryList = BOFactory.getLovBO().getAllCountries();
    refferalform.setCountryList(countryList);
    
    List stateList = BOFactory.getLovBO().getStateListByCountry(ref.getCountry().getCountryId());
    refferalform.setStateList(stateList);
    
    request.setAttribute("isUserdeleted", "yes");
    
    return mapping.findForward("editrefferal");
  }
  
  public ActionForward suspendRefferalEmployee(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside suspend Refferal Employee method");
    RefferalForm refferalform = (RefferalForm)form;
    RefferalEmployee user1 = (RefferalEmployee)request.getSession().getAttribute("employee_refferal_data");
    String employeeReferalId = request.getParameter("employeeReferalId");
    
    RefferalEmployee ref = RefferalDAO.getRefferalEmployee(employeeReferalId);
    ref.setStatus("I");
    ref.setUpdatedBy(user1.getUserName());
    ref.setUpdatedDate(new Date());
    
    RefferalDAO.updateRefferalEmployee(ref);
    refferalform.fromValue(ref, request);
    refferalform.setStatus(ref.getStatus());
    List orgList = BOFactory.getLovBO().getAllOrganization(this.tempSuperUserKey);
    refferalform.setOrganizationList(orgList);
    
    List deptlist = new ArrayList();
    refferalform.setDepartmentList(deptlist);
    refferalform.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(ref.getOrganization().getOrgId())));
    
    List projectcodeList = new ArrayList();
    refferalform.setProjectcodeList(projectcodeList);
    if (ref.getDepartment() != null)
    {
      projectcodeList = BOFactory.getLovBO().getProjectCodesByDept(String.valueOf(ref.getDepartment().getDepartmentId()));
      refferalform.setProjectcodeList(projectcodeList);
    }
    refferalform.setLocaleList(BOFactory.getLovBO().getAllLocales());
    refferalform.setTimezoneList(BOFactory.getLovBO().getAllTimezones());
    



    List countryList = BOFactory.getLovBO().getAllCountries();
    refferalform.setCountryList(countryList);
    
    List stateList = BOFactory.getLovBO().getStateListByCountry(ref.getCountry().getCountryId());
    refferalform.setStateList(stateList);
    
    request.setAttribute("isUsersuspended", "yes");
    
    return mapping.findForward("editPage");
  }
  
  public ActionForward suspend(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside suspend Refferal Employee method");
    RefferalForm refferalform = (RefferalForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    String employeeReferalId = request.getParameter("employeeReferalId");
    
    RefferalEmployee ref = RefferalDAO.getRefferalEmployee(employeeReferalId);
    ref.setStatus("I");
    ref.setUpdatedBy(user1.getUserName());
    ref.setUpdatedDate(new Date());
    
    RefferalDAO.updateRefferalEmployee(ref);
    refferalform.fromValue(ref, request);
    refferalform.setStatus(ref.getStatus());
    List orgList = BOFactory.getLovBO().getAllOrganization(this.tempSuperUserKey);
    refferalform.setOrganizationList(orgList);
    
    List deptlist = new ArrayList();
    refferalform.setDepartmentList(deptlist);
    refferalform.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(ref.getOrganization().getOrgId())));
    
    List projectcodeList = new ArrayList();
    refferalform.setProjectcodeList(projectcodeList);
    if (ref.getDepartment() != null)
    {
      projectcodeList = BOFactory.getLovBO().getProjectCodesByDept(String.valueOf(ref.getDepartment().getDepartmentId()));
      refferalform.setProjectcodeList(projectcodeList);
    }
    refferalform.setLocaleList(BOFactory.getLovBO().getAllLocales());
    refferalform.setTimezoneList(BOFactory.getLovBO().getAllTimezones());
    



    List countryList = BOFactory.getLovBO().getAllCountries();
    refferalform.setCountryList(countryList);
    
    List stateList = BOFactory.getLovBO().getStateListByCountry(ref.getCountry().getCountryId());
    refferalform.setStateList(stateList);
    
    request.setAttribute("isUsersuspended", "yes");
    
    return mapping.findForward("editrefferal");
  }
  
  public ActionForward activateRefferalEmployee(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside activate Refferal Employee method");
    RefferalForm refferalform = (RefferalForm)form;
    RefferalEmployee user1 = (RefferalEmployee)request.getSession().getAttribute("employee_refferal_data");
    String employeeReferalId = request.getParameter("employeeReferalId");
    
    RefferalEmployee ref = RefferalDAO.getRefferalEmployee(employeeReferalId);
    ref.setStatus("A");
    ref.setUpdatedBy(user1.getUserName());
    ref.setUpdatedDate(new Date());
    
    RefferalDAO.updateRefferalEmployee(ref);
    refferalform.fromValue(ref, request);
    List orgList = BOFactory.getLovBO().getAllOrganization(this.tempSuperUserKey);
    refferalform.setOrganizationList(orgList);
    
    List deptlist = new ArrayList();
    refferalform.setDepartmentList(deptlist);
    refferalform.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(ref.getOrganization().getOrgId())));
    
    List projectcodeList = new ArrayList();
    refferalform.setProjectcodeList(projectcodeList);
    if (ref.getDepartment() != null)
    {
      projectcodeList = BOFactory.getLovBO().getProjectCodesByDept(String.valueOf(ref.getDepartment().getDepartmentId()));
      refferalform.setProjectcodeList(projectcodeList);
    }
    refferalform.setLocaleList(BOFactory.getLovBO().getAllLocales());
    refferalform.setTimezoneList(BOFactory.getLovBO().getAllTimezones());
    



    List countryList = BOFactory.getLovBO().getAllCountries();
    refferalform.setCountryList(countryList);
    
    List stateList = BOFactory.getLovBO().getStateListByCountry(ref.getCountry().getCountryId());
    refferalform.setStateList(stateList);
    
    request.setAttribute("isuseractivated", "yes");
    
    return mapping.findForward("editPage");
  }
  
  public ActionForward activate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside activate Refferal Employee method");
    RefferalForm refferalform = (RefferalForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    String employeeReferalId = request.getParameter("employeeReferalId");
    
    RefferalEmployee ref = RefferalDAO.getRefferalEmployee(employeeReferalId);
    ref.setStatus("A");
    ref.setUpdatedBy(user1.getUserName());
    ref.setUpdatedDate(new Date());
    
    RefferalDAO.updateRefferalEmployee(ref);
    refferalform.fromValue(ref, request);
    List orgList = BOFactory.getLovBO().getAllOrganization(this.tempSuperUserKey);
    refferalform.setOrganizationList(orgList);
    
    List deptlist = new ArrayList();
    refferalform.setDepartmentList(deptlist);
    refferalform.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(ref.getOrganization().getOrgId())));
    
    List projectcodeList = new ArrayList();
    refferalform.setProjectcodeList(projectcodeList);
    if (ref.getDepartment() != null)
    {
      projectcodeList = BOFactory.getLovBO().getProjectCodesByDept(String.valueOf(ref.getDepartment().getDepartmentId()));
      refferalform.setProjectcodeList(projectcodeList);
    }
    refferalform.setLocaleList(BOFactory.getLovBO().getAllLocales());
    refferalform.setTimezoneList(BOFactory.getLovBO().getAllTimezones());
    



    List countryList = BOFactory.getLovBO().getAllCountries();
    refferalform.setCountryList(countryList);
    
    List stateList = BOFactory.getLovBO().getStateListByCountry(ref.getCountry().getCountryId());
    refferalform.setStateList(stateList);
    
    request.setAttribute("isuseractivated", "yes");
    
    return mapping.findForward("editrefferal");
  }
}
