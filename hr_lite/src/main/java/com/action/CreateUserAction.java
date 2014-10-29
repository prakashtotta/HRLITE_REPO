package com.action;

import com.bean.BulkUploadTask;
import com.bean.Country;
import com.bean.Locale;
import com.bean.Organization;
import com.bean.ProfilePhoto;
import com.bean.ProjectCodes;
import com.bean.Role;
import com.bean.State;
import com.bean.Timezone;
import com.bean.User;
import com.bo.ApplicantTXBO;
import com.bo.BOFactory;
import com.bo.LovBO;
import com.bo.UserBO;
import com.common.Common;
import com.common.CommonException;
import com.dao.LovOpsDAO;
import com.dao.UserDAO;
import com.form.CreateUserForm;
import com.manager.BulkTaskManager;
import com.resources.Constant;
import com.util.BulkUploadTaskUtil;
import com.util.EncryptDecrypt;
import com.util.StringUtils;
import java.io.File;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

public class CreateUserAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(CreateUserAction.class);
  
  public ActionForward firstpage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside firstpage method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    CreateUserForm userform = (CreateUserForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    BOFactory.getLovBO().createUserLovs(userform, user1);
    if (user1.getNationality() != null) {
      userform.setNationalityId(user1.getNationality().getCountryId());
    }
    if (user1.getLocale() != null) {
      userform.setLocaleId(user1.getLocale().getLocaleId());
    }
    if (user1.getTimezone() != null) {
      userform.setTimezoneId(user1.getTimezone().getTimezoneId());
    }
    return mapping.findForward("createPage");
  }
  
  public ActionForward convertToUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside firstpage method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String applicantId = request.getParameter("applicantId");
    String secureid = request.getParameter("secureid");
    User user1 = (User)request.getSession().getAttribute("user_data");
    CreateUserForm userform = (CreateUserForm)form;
    
    BOFactory.getUserBO().convertToUser(userform, applicantId, secureid, user1);
    
    return mapping.findForward("createPage");
  }
  
  public ActionForward loadprojectcodebyOrganization(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String orgid = request.getParameter("orgid");
    String departmentid = request.getParameter("departmentid");
    CreateUserForm userform = (CreateUserForm)form;
    
    List projectcodeList = new ArrayList();
    userform.setProjectcodeList(projectcodeList);
    if ((!StringUtils.isNullOrEmpty(departmentid)) && (departmentid != "0"))
    {
      projectcodeList = BOFactory.getLovBO().getProjectCodesByOrgAndDept(orgid, departmentid);
      userform.setProjectcodeList(projectcodeList);
    }
    logger.info("...............................................22222333" + projectcodeList);
    return mapping.findForward("projectcodelist");
  }
  
  public ActionForward updatestatus(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updatestatus method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String userId = request.getParameter("userId");
    String status = request.getParameter("status");
    CreateUserForm userform = (CreateUserForm)form;
    logger.info("Inside updatestatus method" + userId);
    logger.info("Inside updatestatus method" + status);
    User usr = UserDAO.getUser(new Long(userId).longValue());
    




    usr.setStatus(status);
    usr.setUpdatedBy(user1.getUserName());
    usr.setUpdatedDate(new Date());
    UserDAO.updateUser(usr);
    





    return mapping.findForward("updatestatus");
  }
  
  public ActionForward vendorcreatepage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside vendorcreatepage method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    CreateUserForm userform = (CreateUserForm)form;
    


    BOFactory.getLovBO().vendorcreateLovs(userform);
    User user1 = (User)request.getSession().getAttribute("user_data");
    if (user1.getNationality() != null)
    {
      userform.setCountryId(user1.getNationality().getCountryId());
      List stateList = BOFactory.getLovBO().getStateListByCountry(user1.getNationality().getCountryId());
      userform.setStateList(stateList);
    }
    if (user1.getLocale() != null) {
      userform.setLocaleId(user1.getLocale().getLocaleId());
    }
    if (user1.getTimezone() != null) {
      userform.setTimezoneId(user1.getTimezone().getTimezoneId());
    }
    return mapping.findForward("vendorcreatepage");
  }
  
  public ActionForward editmyuser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside editmyuser method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    String userId = request.getParameter("userId");
    CreateUserForm userform = (CreateUserForm)form;
    
    User usr = UserDAO.getUser(new Long(userId).longValue());
    try
    {
      userform.fromValue(usr, request);
      

      BOFactory.getLovBO().editUsrLovs(userform, usr);
      

      String readpreview = request.getParameter("readPreview");
      userform.setReadPreview(readpreview);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return mapping.findForward("editmyuser");
  }
  
  public ActionForward edituser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside edituser method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user1 = (User)request.getSession().getAttribute("user_data");
    String userId = request.getParameter("userId");
    CreateUserForm userform = (CreateUserForm)form;
    
    User usr = UserDAO.getUser(new Long(userId).longValue());
    if (usr.getSuper_user_key() == user1.getSuper_user_key())
    {
      userform.fromValue(usr, request);
      
      BOFactory.getLovBO().edituserLovs(userform, usr);
      
      String readpreview = request.getParameter("readPreview");
      userform.setReadPreview(readpreview);
    }
    return mapping.findForward("createPage");
  }
  
  public ActionForward uploademployee(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside uploademployee method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    return mapping.findForward("uploademployee");
  }
  
  public ActionForward uploademployeesubmit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside uploademployeesubmit method");
    List successFilelist = new ArrayList();
    List failFilelist = new ArrayList();
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    CreateUserForm aform = (CreateUserForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    
    String filePath = Constant.getValue("ATTACHMENT_PATH") + user1.getUserName() + File.separator;
    

    FormFile myFile = aform.getFileData();
    
    String fileName = "";
    if (myFile != null)
    {
      String contentType = myFile.getContentType();
      fileName = myFile.getFileName();
      int fileSize = myFile.getFileSize();
      logger.info(Integer.valueOf(fileSize));
      if (fileSize > 10240000)
      {
        request.setAttribute("filesizeexceed", "yes");
        request.setAttribute("uploadsucceed", "no");
      }
      else
      {
        byte[] fileData = myFile.getFileData();
        Blob blob = null;
        
        blob = new SerialBlob(fileData);
        
        File file = new File(filePath);
        if (!file.exists()) {
          file.mkdirs();
        }
        RandomAccessFile raf = new RandomAccessFile(filePath + fileName, "rw");
        

        int length = (int)blob.length();
        byte[] _blob = blob.getBytes(1L, length);
        raf.write(_blob);
        raf.close();
        

        BulkUploadTask task = new BulkUploadTask();
        task.setUploadedFileName(fileName);
        BulkUploadTaskUtil bulkupload = new BulkUploadTaskUtil();
        bulkupload.setTasktype(Common.BULK_UPLOAD_USERS);
        bulkupload.setUser(user1);
        bulkupload.setTask(task);
        BulkTaskManager.bulkupload(bulkupload);
        
        request.setAttribute("uploadsucceed", "yes");
      }
    }
    return mapping.findForward("uploademployee");
  }
  
  public ActionForward edituserbyUsername(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside edituserbyUsername method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    String username = request.getParameter("username");
    CreateUserForm userform = (CreateUserForm)form;
    
    User usr = UserDAO.getUserByUserName(username);
    
    userform.fromValue(usr, request);
    
    BOFactory.getLovBO().edituserLovs(userform, usr);
    
    String readpreview = request.getParameter("readPreview");
    userform.setReadPreview(readpreview);
    

    return mapping.findForward("createPage");
  }
  
  public ActionForward mydetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside mydetails method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user1 = (User)request.getSession().getAttribute("user_data");
    
    CreateUserForm userform = (CreateUserForm)form;
    
    User usr = UserDAO.getUser(user1.getUserId());
    
    userform.fromValue(usr, request);
    
    return mapping.findForward("mydetails");
  }
  
  public ActionForward userprofile(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside userprofile method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String backurl = request.getParameter("url");
    String userId = request.getParameter("userId");
    userId = EncryptDecrypt.decrypt(userId);
    User user1 = (User)request.getSession().getAttribute("user_data");
    
    CreateUserForm userform = (CreateUserForm)form;
    
    User usr = UserDAO.getUser(new Long(userId).longValue());
    
    userform.fromValue(usr, request);
    if (backurl != null) {
      userform.setBackurl(backurl);
    }
    return mapping.findForward("mydetails");
  }
  
  public ActionForward uploadUserPhotoscr(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside uploadUserPhoto method ... ");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String userId = request.getParameter("userId");
    User user1 = (User)request.getSession().getAttribute("user_data");
    User usr = UserDAO.getUser(new Long(userId).longValue());
    CreateUserForm userform = (CreateUserForm)form;
    userform.fromValue(usr, request);
    return mapping.findForward("uploadUserPhoto");
  }
  
  public ActionForward uploadUserPhoto(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside uploadUserPhoto method ... ");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user1 = (User)request.getSession().getAttribute("user_data");
    String userId = request.getParameter("userId");
    User usr = UserDAO.getUser(new Long(userId).longValue());
    CreateUserForm userform = (CreateUserForm)form;
    
    FormFile myFile = userform.getProfilePhoto();
    if (myFile != null)
    {
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
      if (usr.getProfilePhotoId() > 0L) {
        pp.setProfilePhotoId(usr.getProfilePhotoId());
      }
      pp = UserDAO.saveUserProfilePhoto(pp);
      
      usr.setProfilePhotoId(pp.getProfilePhotoId());
      
      UserDAO.updateUser(usr);
    }
    userform.fromValue(usr, request);
    

    request.setAttribute("uploadUserPhoto", "yes");
    return mapping.findForward("uploadUserPhoto");
  }
  
  public ActionForward uploadUserPhotoUserProfile(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside uploadUserPhotoUserProfile method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String userId = request.getParameter("userId");
    User user1 = (User)request.getSession().getAttribute("user_data");
    User usr = UserDAO.getUser(new Long(userId).longValue());
    CreateUserForm userform = (CreateUserForm)form;
    
    FormFile myFile = userform.getProfilePhoto();
    if (myFile != null)
    {
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
      
      pp = UserDAO.saveUserProfilePhoto(pp);
      
      usr.setProfilePhotoId(pp.getProfilePhotoId());
      
      UserDAO.updateUser(usr);
    }
    userform.fromValue(usr, request);
    
    return mapping.findForward("userprofile");
  }
  
  public ActionForward editvendor(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside editvendor method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    String userId = request.getParameter("userId");
    CreateUserForm userform = (CreateUserForm)form;
    
    User usr = UserDAO.getUser(new Long(userId).longValue());
    
    BOFactory.getLovBO().vendoreditLovs(userform, usr);
    toForm(userform, usr);
    
    String readpreview = request.getParameter("readPreview");
    logger.info("Inside editvendor method" + readpreview);
    userform.setReadPreview(readpreview);
    
    logger.info("vendorsudhvfoisdhf");
    return mapping.findForward("vendorcreatepage");
  }
  
  public void toForm(CreateUserForm userform, User usr)
  {
    userform.setUserId(usr.getUserId());
    userform.setFirstName(usr.getFirstName());
    userform.setLastName(usr.getLastName());
    userform.setEmailId(usr.getEmailId());
    userform.setPhoneHome(usr.getPhoneHome());
    userform.setPhoneOffice(usr.getPhoneOffice());
    if (usr.getCountry() != null) {
      userform.setCountryName(usr.getCountry().getCountryName());
    }
    if (usr.getState() != null) {
      userform.setStateName(usr.getState().getStateName());
    }
    userform.setCity(usr.getCity());
    
    userform.setLocaleName(usr.getLocale().getLocaleName());
    userform.setTimezoneName(usr.getTimezone().getTimezoneName());
    userform.setStatus(usr.getStatus());
    if (usr.getCountry() != null)
    {
      userform.countryName = usr.getCountry().getCountryName();
      userform.countryId = usr.getCountry().getCountryId();
    }
    if (usr.getState() != null) {
      userform.stateId = usr.getState().getStateId();
    }
    logger.info("Inside toForm method");
  }
  
  public ActionForward loadState(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String cid = request.getParameter("cid");
    System.out.println(" **** cid :" + cid);
    CreateUserForm userform = (CreateUserForm)form;
    
    userform.setStateList(BOFactory.getLovBO().getStateListByCountry(new Long(cid).longValue()));
    


    System.out.println(" **** test :");
    return mapping.findForward("stateList");
  }
  
  public ActionForward loadDepartments(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String orgId = request.getParameter("orgId");
    System.out.println("orgId" + orgId);
    CreateUserForm userform = (CreateUserForm)form;
    userform.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(orgId));
    
    return mapping.findForward("deptlist");
  }
  
  public ActionForward loadProjectCode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String departmentId = request.getParameter("departmentId");
    System.out.println("departmentId" + departmentId);
    CreateUserForm userform = (CreateUserForm)form;
    userform.setProjectcodeList(BOFactory.getLovBO().getProjectCodesByDept(departmentId));
    
    return mapping.findForward("projectcodelist");
  }
  
  public ActionForward loadDeptlistWithProjectcode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String orgId = request.getParameter("orgId");
    System.out.println("orgId" + orgId);
    CreateUserForm userform = (CreateUserForm)form;
    userform.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(orgId));
    
    return mapping.findForward("deptlistwithprojectcode");
  }
  
  public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside save user method");
    try
    {
      CreateUserForm userform = (CreateUserForm)form;
      logger.info("userform.getProjectId() : " + userform.getProjectId());
      logger.info("userform.getProjectcodeId() : " + userform.getProjectcodeId());
      User user1 = (User)request.getSession().getAttribute("user_data");
      User usr = new User();
      userform.toValue(usr, request);
      usr.setStatus("A");
      usr.setSuper_user_key(user1.getSuper_user_key());
      if (userform.getProjectId() != 0)
      {
        ProjectCodes pjcode = new ProjectCodes();
        pjcode.setProjectId(userform.getProjectId());
        
        usr.setProjectcode(pjcode);
      }
      usr.setType("Employee");
      


      boolean isError = false;
      String employeecode = UserDAO.isEmployeeCodeExist(userform.getEmployeecode(), user1.getSuper_user_key());
      if (employeecode != null)
      {
        isError = true;
        ActionErrors errors = new ActionErrors();
        
        errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError("error.employeecode.alreadyexist"));
        saveErrors(request, errors);
      }
      String emailid = UserDAO.isEmailIdExist(userform.getEmailId());
      if (emailid != null)
      {
        isError = true;
        ActionErrors errors = new ActionErrors();
        
        errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError("error.emailid.alreadyexist"));
        saveErrors(request, errors);
      }
      if (!isError)
      {
        usr = BOFactory.getUserBO().addUser(usr);
        request.setAttribute("isuseradded", "yes");
        if (usr.getApplicantId() > 0L) {
          BOFactory.getApplicantTXBO().setConvertToUserApplicantActivity(usr.getApplicantId(), user1);
        }
      }
      BOFactory.getLovBO().saveuserLovs(userform, usr);
      
      userform.fromValue(usr, request);
    }
    catch (Exception e)
    {
      throw new CommonException("99999999", "System error.");
    }
    return mapping.findForward("createPage");
  }
  
  public ActionForward assignusernamescr(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside assignusernamescr method");
    CreateUserForm userform = (CreateUserForm)form;
    String userId = EncryptDecrypt.decrypt(request.getParameter("userId"));
    
    User usr = UserDAO.getUser(new Long(userId).longValue());
    userform.setUserName(usr.getEmailId());
    

    userform.setUserId(new Long(userId).longValue());
    return mapping.findForward("assignusernamescr");
  }
  
  public ActionForward changepasswordscr(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside changepasswordscr method");
    CreateUserForm userform = (CreateUserForm)form;
    String userId = request.getParameter("userId");
    



    userform.setUserId(new Long(userId).longValue());
    return mapping.findForward("changepasswordscr");
  }
  
  public ActionForward assignusernamesubmit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside assignusernamesubmit method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    CreateUserForm userform = (CreateUserForm)form;
    
    String userId = EncryptDecrypt.decrypt(request.getParameter("userId"));
    
    logger.info("userform.getUserName()" + userform.getUserName());
    User usr = UserDAO.getUser(new Long(userId).longValue());
    if (userform.getPassword() != null) {
      usr.setPassword(EncryptDecrypt.encrypt(userform.getPassword()));
    }
    User usr1 = UserDAO.getUserByUserNameNotDeleted(userform.getUserName());
    if ((usr1 != null) && (usr.getUserId() != usr1.getUserId()))
    {
      ActionErrors errors = new ActionErrors();
      
      errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError("error.username.alreadyexist"));
      saveErrors(request, errors);
    }
    else
    {
      usr.setUserName(userform.getUserName());
      UserDAO.updateUser(usr);
      request.setAttribute("isuseradded", "yes");
    }
    try
    {
      BOFactory.getUserBO().assignUserNamePasswordEmail(usr, user1);
    }
    catch (Exception e)
    {
      logger.info("exception on sending email", e);
    }
    return mapping.findForward("assignusernamescr");
  }
  
  public ActionForward changepasswordsubmit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside changepasswordsubmit method");
    CreateUserForm userform = (CreateUserForm)form;
    String userId = request.getParameter("userId");
    User usr = UserDAO.getUser(new Long(userId).longValue());
    String currentpasswordenc = EncryptDecrypt.encrypt(userform.getCurrentpassword());
    String newPassword = EncryptDecrypt.encrypt(userform.getPassword());
    String confirmPassword = EncryptDecrypt.encrypt(userform.getCpassword());
    String currentpassword = "true";
    String nwpassword = "true";
    ActionErrors errors = new ActionErrors();
    if (!currentpasswordenc.equals(usr.getPassword()))
    {
      errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError("current.password.wrong"));
      saveErrors(request, errors);
      currentpassword = "false";
    }
    if (!newPassword.equals(confirmPassword))
    {
      errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError("hr.user.Pass_does_not_match"));
      saveErrors(request, errors);
      nwpassword = "false";
    }
    if ((nwpassword.equals("true")) && (currentpassword.equals("true")))
    {
      usr.setPassword(EncryptDecrypt.encrypt(userform.getPassword()));
      UserDAO.updateUser(usr);
      request.setAttribute("isuseradded", "yes");
    }
    return mapping.findForward("changepasswordscr");
  }
  
  public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside delete user method");
    CreateUserForm userform = (CreateUserForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    String userId = request.getParameter("userId");
    User user = UserDAO.getUser(new Long(userId).longValue());
    user.setStatus("D");
    user.setUpdatedBy(user1.getUserName());
    user.setUpdatedDate(new Date());
    UserDAO.updateUser(user);
    logger.info(" user.getType() :" + user.getType());
    if ((!StringUtils.isNullOrEmpty(user.getType())) && (user.getType().equals("Employee"))) {
      request.setAttribute("isuserdeleted", "yes");
    }
    if ((!StringUtils.isNullOrEmpty(user.getType())) && (user.getType().equals("Vendor"))) {
      request.setAttribute("isVendordeleted", "yes");
    }
    return mapping.findForward("success");
  }
  
  public ActionForward suspend(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside suspend user method");
    CreateUserForm userform = (CreateUserForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    String userId = request.getParameter("userId");
    User user = UserDAO.getUser(new Long(userId).longValue());
    user.setStatus("I");
    user.setUpdatedBy(user1.getUserName());
    user.setUpdatedDate(new Date());
    UserDAO.updateUser(user);
    if ((!StringUtils.isNullOrEmpty(user.getType())) && (user.getType().equals("Employee"))) {
      request.setAttribute("isusersuspended", "yes");
    }
    if ((!StringUtils.isNullOrEmpty(user.getType())) && (user.getType().equals("Vendor"))) {
      request.setAttribute("isVendorsuspended", "yes");
    }
    return mapping.findForward("success");
  }
  
  public ActionForward activate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside activate user method");
    CreateUserForm userform = (CreateUserForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    String userId = request.getParameter("userId");
    User user = UserDAO.getUser(new Long(userId).longValue());
    user.setStatus("A");
    user.setUpdatedBy(user1.getUserName());
    user.setUpdatedDate(new Date());
    UserDAO.updateUser(user);
    if ((!StringUtils.isNullOrEmpty(user.getType())) && (user.getType().equals("Employee"))) {
      request.setAttribute("isuseractivated", "yes");
    }
    if ((!StringUtils.isNullOrEmpty(user.getType())) && (user.getType().equals("Vendor"))) {
      request.setAttribute("isVendoractivated", "yes");
    }
    return mapping.findForward("success");
  }
  
  public ActionForward savevendor(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside savevendor method");
    CreateUserForm userform = (CreateUserForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    User usr = new User();
    userform.toValue(usr, request);
    
    usr.setStatus("A");
    usr.setSuper_user_key(user1.getSuper_user_key());
    

    Role rl = LovOpsDAO.getRoleByCode("VENDOR", user1.getSuper_user_key());
    Role role = new Role();
    role.setRoleId(rl.getRoleId());
    usr.setRole(role);
    usr.setType("Vendor");
    if (userform.getPassword() != null) {
      usr.setPassword(EncryptDecrypt.encrypt(userform.getPassword()));
    }
    boolean isError = false;
    
    String emailid = UserDAO.isEmailIdExist(userform.getEmailId());
    if (emailid != null)
    {
      isError = true;
      ActionErrors errors = new ActionErrors();
      
      errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError("error.emailid.alreadyexist"));
      saveErrors(request, errors);
    }
    else
    {
      usr = UserDAO.saveUser(usr);
      userform.setUserId(usr.getUserId());
      userform.setStatus(usr.getStatus());
      request.setAttribute("isuseradded", "yes");
    }
    BOFactory.getLovBO().vendoreditLovs(userform, usr);
    
    logger.info("usr.getuserid" + usr.getUserId());
    
    logger.info("usr.getuserid" + usr.getUserId());
    



    return mapping.findForward("vendorcreatepage");
  }
  
  public ActionForward updatemydetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updatemydetails method");
    String userId = request.getParameter("userId");
    CreateUserForm userform = (CreateUserForm)form;
    
    User usr = UserDAO.getUser(new Long(userId).longValue());
    

    User usr2 = UserDAO.getUserByEmailIdWithNotDeleted(userform.getEmailId());
    if ((usr2 != null) && (!usr2.getEmailId().equals(usr.getEmailId())))
    {
      ActionErrors errors = new ActionErrors();
      
      errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError("error.emailid.alreadyexist"));
      saveErrors(request, errors);
    }
    else
    {
      usr.setFirstName(userform.getFirstName());
      usr.setMiddleName(userform.getMiddleName());
      usr.setLastName(userform.getLastName());
      usr.setPhoneHome(userform.getPhoneHome());
      usr.setPhoneOffice(userform.getPhoneOffice());
      usr.setMobileNo(userform.getMobileNo());
      usr.setSsnNumber(userform.getSsnNumber());
      Locale lo = new Locale();
      lo.setLocaleId(userform.getLocaleId());
      usr.setLocale(lo);
      
      Timezone tz = new Timezone();
      tz.setTimezoneId(userform.getTimezoneId());
      usr.setTimezone(tz);
      
      Country nationality = new Country();
      nationality.setCountryId(userform.getNationalityId());
      usr.setNationality(nationality);
      if (userform.getStateId() != 0L)
      {
        State state = new State();
        state.setStateId(userform.getStateId());
        usr.setState(state);
      }
      usr.setCity(userform.getCity());
      

      UserDAO.updateUser(usr);
      request.setAttribute("isuseradded", "yes");
    }
    usr = UserDAO.getUser(new Long(userId).longValue());
    


    userform.fromValue(usr, request);
    

    BOFactory.getLovBO().updateMyDetailsLovs(userform, usr);
    

    return mapping.findForward("editmyuser");
  }
  
  public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside update user method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    try
    {
      String userId = request.getParameter("userId");
      CreateUserForm userform = (CreateUserForm)form;
      logger.info("update .. userform.getProjectId() : " + userform.getProjectId());
      logger.info("update .. userform.getProjectcodeId() : " + userform.getProjectcodeId());
      
      User usr = UserDAO.getUser(new Long(userId).longValue());
      
      String fname = usr.getFirstName();
      if (!StringUtils.isNullOrEmpty(fname)) {
        fname = fname.replace("'", "\\'");
      }
      String lname = usr.getLastName();
      if (!StringUtils.isNullOrEmpty(lname)) {
        lname = lname.replace("'", "\\'");
      }
      String email = usr.getEmailId();
      if (!StringUtils.isNullOrEmpty(email)) {
        email = email.replace("'", "\\'");
      }
      String temp = "'\"" + fname + " " + lname + "\"" + " " + "<" + email + ">" + "'";
      

      boolean isError = false;
      String employeecode = UserDAO.isEmployeeCodeExist(userform.getEmployeecode(), user1.getSuper_user_key());
      if ((employeecode != null) && (!employeecode.equals(usr.getEmployeecode())))
      {
        isError = true;
        ActionErrors errors = new ActionErrors();
        
        errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError("error.employeecode.alreadyexist"));
        saveErrors(request, errors);
      }
      String emailid = UserDAO.isEmailIdExist(userform.getEmailId());
      if ((emailid != null) && (!emailid.equals(usr.getEmailId())))
      {
        isError = true;
        ActionErrors errors = new ActionErrors();
        
        errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError("error.emailid.alreadyexist"));
        saveErrors(request, errors);
      }
      if (!isError)
      {
        boolean isDashboardUpdate = false;
        if (usr.getRole().getRoleId() != userform.getRoleId()) {
          isDashboardUpdate = true;
        }
        userform.toValue(usr, request);
        usr.setUserName(userform.getEmailId());
        

        usr = BOFactory.getUserBO().updateUserData(usr, isDashboardUpdate);
        request.setAttribute("isuserupdated", "yes");
      }
      userform.fromValue(usr, request);
      BOFactory.getLovBO().edituserLovs(userform, usr);
      
      String readpreview = request.getParameter("readPreview");
      userform.setReadPreview(readpreview);
      

      logger.info("Inside update user method end");
      return mapping.findForward("createPage");
    }
    catch (Exception e)
    {
      logger.info("exception in update user", e);
      String msg = Constant.getResourceStringValue("server.error", user1.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
    }
    return mapping.findForward("exception");
  }
  
  public ActionForward updatevendor(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updatevendor method");
    String userId = request.getParameter("userId");
    CreateUserForm userform = (CreateUserForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    User usr = UserDAO.getUser(new Long(userId).longValue());
    String oldemailid = usr.getEmailId();
    userform.toValue(usr, request);
    


    Role rl = LovOpsDAO.getRoleByCode("VENDOR", user1.getSuper_user_key());
    Role role = new Role();
    role.setRoleId(rl.getRoleId());
    usr.setRole(role);
    usr.setType("Vendor");
    
    usr.setPassword(usr.getPassword());
    



    String emailid = UserDAO.isEmailIdExist(userform.getEmailId());
    if ((emailid != null) && (!emailid.equals(oldemailid)))
    {
      ActionErrors errors = new ActionErrors();
      
      errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError("error.emailid.alreadyexist"));
      saveErrors(request, errors);
    }
    else
    {
      usr = UserDAO.updateUser(usr);
      request.setAttribute("vendorUpdated", "yes");
    }
    userform.fromValue(usr, request);
    


    BOFactory.getLovBO().vendoreditLovs(userform, usr);
    

    return mapping.findForward("vendorcreatepage");
  }
  
  public ActionForward userList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside userList method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    int pageSize = 3;
    String pageNumber = request.getParameter("page");
    if ((pageNumber != null) && (pageNumber.length() > 0)) {
      request.setAttribute("page", pageNumber);
    } else {
      pageNumber = "1";
    }
    List userList = UserDAO.getAllUsers(pageSize, Integer.parseInt(pageNumber));
    String[] header = { "firstName", "lastName", "username", "department", "role", "language" };
    String[] fields = { "firstName", "lastName", "username", "departmentValue", "roleValue", "languageValue" };
    

    request.setAttribute("data", StringUtils.createBasicYahooDataTable(userList, fields));
    
    return mapping.findForward("userList");
  }
  
  public ActionForward userListwithPag(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside userListwithPag method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    CreateUserForm userform = (CreateUserForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    userform.setDesignationList(BOFactory.getLovBO().getAllDesignations(user1.getSuper_user_key()));
    List orgList = BOFactory.getLovBO().getAllOrganization(user1.getSuper_user_key());
    userform.setOrganizationList(orgList);
    List deptlist = new ArrayList();
    userform.setDepartmentList(deptlist);
    if (orgList.size() > 0)
    {
      Organization org = (Organization)orgList.get(0);
      userform.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(org.getOrgId())));
    }
    List projectcodeList = new ArrayList();
    userform.setProjectcodeList(projectcodeList);
    request.setAttribute("userHome", "user");
    request.setAttribute("searchpagedisplay", "no");
    return mapping.findForward("userListyahoo");
  }
  
  public ActionForward searchuserlistpage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchuserlistpage method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    CreateUserForm userform = (CreateUserForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    String statuscri = request.getParameter("statuscri");
    userform.setStatuscri(statuscri);
    userform.setFirstName(userform.getFirstName());
    userform.setLastName(userform.getLastName());
    userform.setOrgId(userform.getOrgId());
    userform.setDepartmentId(userform.getDepartmentId());
    userform.setDesignationId(userform.getDesignationId());
    userform.setUserName(userform.getUserName());
    
    userform.setDesignationList(BOFactory.getLovBO().getAllDesignations(user1.getSuper_user_key()));
    List orgList = BOFactory.getLovBO().getAllOrganization(user1.getSuper_user_key());
    userform.setOrganizationList(orgList);
    List deptlist = new ArrayList();
    userform.setDepartmentList(deptlist);
    if (userform.getOrgId() != 0L)
    {
      List deptList = BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(userform.getOrgId()));
      userform.setDepartmentList(deptList);
    }
    else if (orgList.size() > 0)
    {
      Organization org = (Organization)orgList.get(0);
      List deptList = BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(org.getOrgId()));
      userform.setDepartmentList(deptList);
    }
    if (userform.getDepartmentId() > 0L) {
      userform.setProjectcodeList(BOFactory.getLovBO().getProjectCodesByDept(String.valueOf(userform.getDepartmentId())));
    } else {
      userform.setProjectcodeList(new ArrayList());
    }
    request.setAttribute("userHome", "user");
    request.setAttribute("searchpagedisplay", "yes");
    return mapping.findForward("userListyahoo");
  }
  
  public ActionForward vendorlist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside vendorlist method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    CreateUserForm userform = (CreateUserForm)form;
    List countryList = BOFactory.getLovBO().getAllCountries();
    userform.setCountryList(countryList);
    Country ct = (Country)countryList.get(0);
    List stateList = BOFactory.getLovBO().getStateListByCountry(ct.getCountryId());
    userform.setStateList(stateList);
    userform.setCountryId(0L);
    userform.setStateId(0L);
    request.setAttribute("searchpagedisplay", "no");
    return mapping.findForward("vendorlist");
  }
  
  public ActionForward searchvendorlistpage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchvendorlistpage method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    CreateUserForm userform = (CreateUserForm)form;
    
    String statuscri = request.getParameter("statuscri");
    String countryid = request.getParameter("countryId");
    if (StringUtils.isNullOrEmpty(countryid)) {
      countryid = "0";
    }
    userform.setStatuscri(statuscri);
    userform.setFirstName(userform.getFirstName());
    userform.setLastName(userform.getLastName());
    userform.setCountryId(userform.getCountryId());
    userform.setStateId(userform.getStateId());
    userform.setCity(userform.getCity());
    
    List countryList = BOFactory.getLovBO().getAllCountries();
    userform.setCountryList(countryList);
    Country ct = (Country)countryList.get(0);
    List stateList = new ArrayList();
    if ((!StringUtils.compare(String.valueOf(countryid), String.valueOf(0))) || (!StringUtils.compare(String.valueOf(countryid), String.valueOf(""))) || (!StringUtils.compare(String.valueOf(countryid), String.valueOf(null)))) {
      stateList = BOFactory.getLovBO().getStateListByCountry(new Long(countryid).longValue());
    } else {
      stateList = BOFactory.getLovBO().getStateListByCountry(ct.getCountryId());
    }
    userform.setStateList(stateList);
    
    request.setAttribute("searchpagedisplay", "yes");
    return mapping.findForward("vendorlist");
  }
  
  public ActionForward userselector(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside userselector method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    String boxnumber = request.getParameter("boxnumber");
    String idlistval = request.getParameter("idlistval");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    logger.info("boxnumber" + boxnumber);
    if (boxnumber != null) {
      request.setAttribute("boxnumber", boxnumber);
    }
    request.setAttribute("idlistval", idlistval);
    CreateUserForm userform = (CreateUserForm)form;
    


    String readpreview = request.getParameter("readPreview");
    userform.setReadPreview(readpreview);
    List orgList = BOFactory.getLovBO().getAllOrganization(user1.getSuper_user_key());
    userform.setOrganizationList(orgList);
    List deptlist = new ArrayList();
    userform.setDepartmentList(deptlist);
    if (orgList.size() > 0)
    {
      Organization org = (Organization)orgList.get(0);
      userform.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(org.getOrgId())));
    }
    return mapping.findForward("userselector");
  }
  
  public ActionForward approverselector(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside approverselector method");
    String boxnumber = request.getParameter("boxnumber");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    logger.info("boxnumber" + boxnumber);
    if (boxnumber != null) {
      request.setAttribute("boxnumber", boxnumber);
    }
    CreateUserForm userform = (CreateUserForm)form;
    


    String readpreview = request.getParameter("readPreview");
    userform.setReadPreview(readpreview);
    List orgList = BOFactory.getLovBO().getAllOrganization(user1.getSuper_user_key());
    userform.setOrganizationList(orgList);
    List deptlist = new ArrayList();
    userform.setDepartmentList(deptlist);
    





    return mapping.findForward("approverselectorapplicant");
  }
  
  public ActionForward onboardTaskUserSelector(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside onboardTaskUserSelector method");
    String boxnumber = request.getParameter("boxnumber");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    logger.info("boxnumber" + boxnumber);
    if (boxnumber != null) {
      request.setAttribute("boxnumber", boxnumber);
    }
    CreateUserForm userform = (CreateUserForm)form;
    


    String readpreview = request.getParameter("readPreview");
    userform.setReadPreview(readpreview);
    List orgList = BOFactory.getLovBO().getAllOrganization(user1.getSuper_user_key());
    userform.setOrganizationList(orgList);
    List deptlist = new ArrayList();
    userform.setDepartmentList(deptlist);
    





    return mapping.findForward("onboardTaskUserSelector");
  }
  
  public ActionForward recruiterselector(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside recruiterselector method");
    String boxnumber = request.getParameter("boxnumber");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    if (boxnumber != null) {
      request.setAttribute("boxnumber", boxnumber);
    }
    CreateUserForm userform = (CreateUserForm)form;
    


    String readpreview = request.getParameter("readPreview");
    userform.setReadPreview(readpreview);
    List orgList = BOFactory.getLovBO().getAllOrganization(user1.getSuper_user_key());
    userform.setOrganizationList(orgList);
    List deptlist = new ArrayList();
    userform.setDepartmentList(deptlist);
    if (orgList.size() > 0)
    {
      Organization org = (Organization)orgList.get(0);
      userform.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(org.getOrgId())));
    }
    return mapping.findForward("recruiterselector");
  }
  
  public ActionForward assignedtoselector(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside assignedtoselector method");
    String boxnumber = request.getParameter("boxnumber");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    if (boxnumber != null) {
      request.setAttribute("boxnumber", boxnumber);
    }
    CreateUserForm userform = (CreateUserForm)form;
    


    String readpreview = request.getParameter("readPreview");
    userform.setReadPreview(readpreview);
    List orgList = BOFactory.getLovBO().getAllOrganization(user1.getSuper_user_key());
    userform.setOrganizationList(orgList);
    List deptlist = new ArrayList();
    userform.setDepartmentList(deptlist);
    




    return mapping.findForward("assignedtoselector");
  }
  
  public ActionForward watchlistselector(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside watchlistselector method");
    String boxnumber = request.getParameter("boxnumber");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user1 = (User)request.getSession().getAttribute("user_data");
    if (boxnumber != null) {
      request.setAttribute("boxnumber", boxnumber);
    }
    if (request.getSession().getAttribute("checkeduserids") != null) {
      request.getSession().removeAttribute("checkeduserids");
    }
    if (request.getSession().getAttribute("checkedusernames") != null) {
      request.getSession().removeAttribute("checkedusernames");
    }
    CreateUserForm userform = (CreateUserForm)form;
    


    String readpreview = request.getParameter("readPreview");
    userform.setReadPreview(readpreview);
    List orgList = BOFactory.getLovBO().getAllOrganization(user1.getSuper_user_key());
    userform.setOrganizationList(orgList);
    List deptlist = new ArrayList();
    userform.setDepartmentList(deptlist);
    





    return mapping.findForward("watchlistselector");
  }
  
  public ActionForward vendorlistselector(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside vendorlistselector method");
    String boxnumber = request.getParameter("boxnumber");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    if (boxnumber != null) {
      request.setAttribute("boxnumber", boxnumber);
    }
    if (request.getSession().getAttribute("checkeduserids") != null) {
      request.getSession().removeAttribute("checkeduserids");
    }
    if (request.getSession().getAttribute("checkedusernames") != null) {
      request.getSession().removeAttribute("checkedusernames");
    }
    CreateUserForm userform = (CreateUserForm)form;
    


    String readpreview = request.getParameter("readPreview");
    userform.setReadPreview(readpreview);
    List countryList = BOFactory.getLovBO().getAllCountries();
    userform.setCountryList(countryList);
    Country ct = (Country)countryList.get(0);
    List stateList = BOFactory.getLovBO().getStateListByCountry(ct.getCountryId());
    userform.setStateList(stateList);
    

    return mapping.findForward("vendorlistselector");
  }
  
  public ActionForward vendorlistselectorpublishwindow(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside vendorlistselector method");
    String boxnumber = request.getParameter("boxnumber");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    if (boxnumber != null) {
      request.setAttribute("boxnumber", boxnumber);
    }
    if (request.getSession().getAttribute("checkeduserids") != null) {
      request.getSession().removeAttribute("checkeduserids");
    }
    if (request.getSession().getAttribute("checkedusernames") != null) {
      request.getSession().removeAttribute("checkedusernames");
    }
    CreateUserForm userform = (CreateUserForm)form;
    


    String readpreview = request.getParameter("readPreview");
    userform.setReadPreview(readpreview);
    List countryList = BOFactory.getLovBO().getAllCountries();
    userform.setCountryList(countryList);
    Country ct = (Country)countryList.get(0);
    List stateList = BOFactory.getLovBO().getStateListByCountry(ct.getCountryId());
    userform.setStateList(stateList);
    

    return mapping.findForward("vendorlistselectorpublishwindow");
  }
  
  public ActionForward vendorlistsearch(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside vendorlistsearch method");
    String boxnumber = request.getParameter("boxnumber");
    
    User user1 = (User)request.getSession().getAttribute("user_data");
    String start = request.getParameter("start");
    String range = request.getParameter("range");
    String results = request.getParameter("results");
    String count = request.getParameter("count");
    CreateUserForm userform = (CreateUserForm)form;
    int start1 = StringUtils.stringToInt(start, 0);
    int range1 = StringUtils.stringToInt(range, 15);
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    

    String readpreview = request.getParameter("readPreview");
    userform.setReadPreview(readpreview);
    List countryList = BOFactory.getLovBO().getAllCountries();
    userform.setCountryList(countryList);
    if (userform.getCountryId() != 0L)
    {
      List stateList = BOFactory.getLovBO().getStateListByCountry(userform.getCountryId());
      userform.setStateList(stateList);
    }
    else
    {
      Country ct = (Country)countryList.get(0);
      List stateList = BOFactory.getLovBO().getStateListByCountry(ct.getCountryId());
      userform.setStateList(stateList);
    }
    List userList = UserDAO.getVendorByCritera(user1.getSuper_user_key(), userform.getFirstName(), userform.getLastName(), new Long(userform.getCountryId()).longValue(), new Long(userform.getStateId()).longValue(), userform.getCity(), start1, range1);
    int totaluser = 0;
    if (StringUtils.isNullOrEmpty(results)) {
      totaluser = UserDAO.getCountOfVendorByCriteria(user1.getSuper_user_key(), userform.getFirstName(), userform.getLastName(), new Long(userform.getCountryId()).longValue(), new Long(userform.getStateId()).longValue(), userform.getCity());
    } else {
      totaluser = new Integer(results).intValue();
    }
    logger.info("Inside vendorlistsearch method" + userList.size());
    userform.setStart(String.valueOf(start1));
    userform.setRange(String.valueOf(range1));
    userform.setResults(String.valueOf(totaluser));
    userform.setFirstName(userform.getFirstName());
    userform.setLastName(userform.getLastName());
    userform.setUserList(userList);
    logger.info("Inside vendorlistsearch method");
    if (boxnumber != null) {
      request.setAttribute("boxnumber", boxnumber);
    }
    if (count != null) {
      request.setAttribute("count", count);
    }
    return mapping.findForward("vendorlistselector");
  }
  
  public ActionForward vendorlistsearchpublishwindow(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside vendorlistsearch method");
    String boxnumber = request.getParameter("boxnumber");
    User user1 = (User)request.getSession().getAttribute("user_data");
    
    String start = request.getParameter("start");
    String range = request.getParameter("range");
    String results = request.getParameter("results");
    String count = request.getParameter("count");
    CreateUserForm userform = (CreateUserForm)form;
    int start1 = StringUtils.stringToInt(start, 0);
    int range1 = StringUtils.stringToInt(range, 15);
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    

    String readpreview = request.getParameter("readPreview");
    userform.setReadPreview(readpreview);
    List countryList = BOFactory.getLovBO().getAllCountries();
    userform.setCountryList(countryList);
    if (userform.getCountryId() != 0L)
    {
      List stateList = BOFactory.getLovBO().getStateListByCountry(userform.getCountryId());
      userform.setStateList(stateList);
    }
    else
    {
      Country ct = (Country)countryList.get(0);
      List stateList = BOFactory.getLovBO().getStateListByCountry(ct.getCountryId());
      userform.setStateList(stateList);
    }
    List userList = UserDAO.getVendorByCritera(user1.getSuper_user_key(), userform.getFirstName(), userform.getLastName(), new Long(userform.getCountryId()).longValue(), new Long(userform.getStateId()).longValue(), userform.getCity(), start1, range1);
    int totaluser = 0;
    if (StringUtils.isNullOrEmpty(results)) {
      totaluser = UserDAO.getCountOfVendorByCriteria(user1.getSuper_user_key(), userform.getFirstName(), userform.getLastName(), new Long(userform.getCountryId()).longValue(), new Long(userform.getStateId()).longValue(), userform.getCity());
    } else {
      totaluser = new Integer(results).intValue();
    }
    logger.info("Inside vendorlistsearch method" + userList.size());
    userform.setStart(String.valueOf(start1));
    userform.setRange(String.valueOf(range1));
    userform.setResults(String.valueOf(totaluser));
    userform.setFirstName(userform.getFirstName());
    userform.setLastName(userform.getLastName());
    userform.setUserList(userList);
    logger.info("Inside vendorlistsearch method");
    if (boxnumber != null) {
      request.setAttribute("boxnumber", boxnumber);
    }
    if (count != null) {
      request.setAttribute("count", count);
    }
    return mapping.findForward("vendorlistselectorpublishwindow");
  }
  
  public ActionForward searchusers(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchusers method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    String boxnumber = request.getParameter("boxnumber");
    String idlistval = request.getParameter("idlistval");
    request.setAttribute("idlistval", idlistval);
    String start = request.getParameter("start");
    String range = request.getParameter("range");
    String results = request.getParameter("results");
    CreateUserForm userform = (CreateUserForm)form;
    int start1 = StringUtils.stringToInt(start, 0);
    int range1 = StringUtils.stringToInt(range, 15);
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    

    String readpreview = request.getParameter("readPreview");
    userform.setReadPreview(readpreview);
    List orgList = BOFactory.getLovBO().getAllOrganization(user1.getSuper_user_key());
    userform.setOrganizationList(orgList);
    List deptList = BOFactory.getLovBO().getAllDepartments();
    userform.setDepartmentList(deptList);
    
    List userList = new ArrayList();
    if ((userform.getIsUserGroupSearch() != null) && (userform.getIsUserGroupSearch().equals("Y"))) {
      userList = UserDAO.getUserGroupsByCritera(user1, userform.getGroupName(), start1, range1);
    } else {
      userList = UserDAO.getUsersByCritera(user1, userform.getFirstName(), userform.getLastName(), new Long(userform.getOrgId()).longValue(), new Long(userform.getDepartmentId()).longValue(), start1, range1);
    }
    int totaluser = 0;
    if (StringUtils.isNullOrEmpty(results))
    {
      if ((userform.getIsUserGroupSearch() != null) && (userform.getIsUserGroupSearch().equals("Y"))) {
        totaluser = UserDAO.getCountOfUserGroupsByCriteria(user1, userform.getGroupName());
      } else {
        totaluser = UserDAO.getCountOfUsersByCriteria(user1, userform.getFirstName(), userform.getLastName(), new Long(userform.getOrgId()).longValue(), new Long(userform.getDepartmentId()).longValue());
      }
    }
    else {
      totaluser = new Integer(results).intValue();
    }
    userform.setStart(String.valueOf(start1));
    userform.setRange(String.valueOf(range1));
    userform.setResults(String.valueOf(totaluser));
    userform.setFirstName(userform.getFirstName());
    userform.setLastName(userform.getLastName());
    userform.setUserList(userList);
    if (boxnumber != null) {
      request.setAttribute("boxnumber", boxnumber);
    }
    return mapping.findForward("userselector");
  }
  
  public ActionForward searchapprover(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchapprover method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    String boxnumber = request.getParameter("boxnumber");
    
    String start = request.getParameter("start");
    String range = request.getParameter("range");
    String results = request.getParameter("results");
    CreateUserForm userform = (CreateUserForm)form;
    int start1 = StringUtils.stringToInt(start, 0);
    int range1 = StringUtils.stringToInt(range, 15);
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    

    String readpreview = request.getParameter("readPreview");
    userform.setReadPreview(readpreview);
    List orgList = BOFactory.getLovBO().getAllOrganization(user1.getSuper_user_key());
    userform.setOrganizationList(orgList);
    List deptList = BOFactory.getLovBO().getAllDepartments();
    userform.setDepartmentList(deptList);
    
    List userList = new ArrayList();
    if ((userform.getIsUserGroupSearch() != null) && (userform.getIsUserGroupSearch().equals("Y"))) {
      userList = UserDAO.getUserGroupsByCritera(user1, userform.getGroupName(), start1, range1);
    } else {
      userList = UserDAO.getUsersByCritera(user1, userform.getFirstName(), userform.getLastName(), new Long(userform.getOrgId()).longValue(), new Long(userform.getDepartmentId()).longValue(), start1, range1);
    }
    int totaluser = 0;
    if (StringUtils.isNullOrEmpty(results))
    {
      if ((userform.getIsUserGroupSearch() != null) && (userform.getIsUserGroupSearch().equals("Y"))) {
        totaluser = UserDAO.getCountOfUserGroupsByCriteria(user1, userform.getGroupName());
      } else {
        totaluser = UserDAO.getCountOfUsersByCriteria(user1, userform.getFirstName(), userform.getLastName(), new Long(userform.getOrgId()).longValue(), new Long(userform.getDepartmentId()).longValue());
      }
    }
    else {
      totaluser = new Integer(results).intValue();
    }
    userform.setStart(String.valueOf(start1));
    userform.setRange(String.valueOf(range1));
    userform.setResults(String.valueOf(totaluser));
    userform.setFirstName(userform.getFirstName());
    userform.setLastName(userform.getLastName());
    userform.setUserList(userList);
    if (boxnumber != null) {
      request.setAttribute("boxnumber", boxnumber);
    }
    return mapping.findForward("approverselectorapplicant");
  }
  
  public ActionForward searchOnboardTaskUserSelector(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchOnboardTaskUserSelector method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    String boxnumber = request.getParameter("boxnumber");
    
    String start = request.getParameter("start");
    String range = request.getParameter("range");
    String results = request.getParameter("results");
    CreateUserForm userform = (CreateUserForm)form;
    int start1 = StringUtils.stringToInt(start, 0);
    int range1 = StringUtils.stringToInt(range, 15);
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    

    String readpreview = request.getParameter("readPreview");
    userform.setReadPreview(readpreview);
    List orgList = BOFactory.getLovBO().getAllOrganization(user1.getSuper_user_key());
    userform.setOrganizationList(orgList);
    List deptList = BOFactory.getLovBO().getAllDepartments();
    userform.setDepartmentList(deptList);
    
    List userList = new ArrayList();
    if ((userform.getIsUserGroupSearch() != null) && (userform.getIsUserGroupSearch().equals("Y"))) {
      userList = UserDAO.getUserGroupsByCritera(user1, userform.getGroupName(), start1, range1);
    } else {
      userList = UserDAO.getUsersByCritera(user1, userform.getFirstName(), userform.getLastName(), new Long(userform.getOrgId()).longValue(), new Long(userform.getDepartmentId()).longValue(), start1, range1);
    }
    int totaluser = 0;
    if (StringUtils.isNullOrEmpty(results))
    {
      if ((userform.getIsUserGroupSearch() != null) && (userform.getIsUserGroupSearch().equals("Y"))) {
        totaluser = UserDAO.getCountOfUserGroupsByCriteria(user1, userform.getGroupName());
      } else {
        totaluser = UserDAO.getCountOfUsersByCriteria(user1, userform.getFirstName(), userform.getLastName(), new Long(userform.getOrgId()).longValue(), new Long(userform.getDepartmentId()).longValue());
      }
    }
    else {
      totaluser = new Integer(results).intValue();
    }
    userform.setStart(String.valueOf(start1));
    userform.setRange(String.valueOf(range1));
    userform.setResults(String.valueOf(totaluser));
    userform.setFirstName(userform.getFirstName());
    userform.setLastName(userform.getLastName());
    userform.setUserList(userList);
    if (boxnumber != null) {
      request.setAttribute("boxnumber", boxnumber);
    }
    return mapping.findForward("onboardTaskUserSelector");
  }
  
  public ActionForward searchrecruiters(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchrecruiters method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    String boxnumber = request.getParameter("boxnumber");
    
    String start = request.getParameter("start");
    String range = request.getParameter("range");
    String results = request.getParameter("results");
    CreateUserForm userform = (CreateUserForm)form;
    int start1 = StringUtils.stringToInt(start, 0);
    int range1 = StringUtils.stringToInt(range, 15);
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    

    String readpreview = request.getParameter("readPreview");
    userform.setReadPreview(readpreview);
    List orgList = BOFactory.getLovBO().getAllOrganization(user1.getSuper_user_key());
    userform.setOrganizationList(orgList);
    List deptList = BOFactory.getLovBO().getAllDepartments();
    userform.setDepartmentList(deptList);
    List userList = UserDAO.getUsersByCritera(user1, userform.getFirstName(), userform.getLastName(), new Long(userform.getOrgId()).longValue(), new Long(userform.getDepartmentId()).longValue(), start1, range1);
    int totaluser = 0;
    if (StringUtils.isNullOrEmpty(results)) {
      totaluser = UserDAO.getCountOfUsersByCriteria(user1, userform.getFirstName(), userform.getLastName(), new Long(userform.getOrgId()).longValue(), new Long(userform.getDepartmentId()).longValue());
    } else {
      totaluser = new Integer(results).intValue();
    }
    userform.setStart(String.valueOf(start1));
    userform.setRange(String.valueOf(range1));
    userform.setResults(String.valueOf(totaluser));
    userform.setFirstName(userform.getFirstName());
    userform.setLastName(userform.getLastName());
    userform.setUserList(userList);
    if (boxnumber != null) {
      request.setAttribute("boxnumber", boxnumber);
    }
    return mapping.findForward("recruiterselector");
  }
  
  public ActionForward searchassigneduser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchassigneduser method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    String boxnumber = request.getParameter("boxnumber");
    
    String start = request.getParameter("start");
    String range = request.getParameter("range");
    String results = request.getParameter("results");
    CreateUserForm userform = (CreateUserForm)form;
    int start1 = StringUtils.stringToInt(start, 0);
    int range1 = StringUtils.stringToInt(range, 15);
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    

    String readpreview = request.getParameter("readPreview");
    userform.setReadPreview(readpreview);
    List orgList = BOFactory.getLovBO().getAllOrganization(user1.getSuper_user_key());
    userform.setOrganizationList(orgList);
    List deptList = BOFactory.getLovBO().getAllDepartments();
    userform.setDepartmentList(deptList);
    List userList = new ArrayList();
    if ((userform.getIsUserGroupSearch() != null) && (userform.getIsUserGroupSearch().equals("Y"))) {
      userList = UserDAO.getUserGroupsByCritera(user1, userform.getGroupName(), start1, range1);
    } else {
      userList = UserDAO.getUsersByCritera(user1, userform.getFirstName(), userform.getLastName(), new Long(userform.getOrgId()).longValue(), new Long(userform.getDepartmentId()).longValue(), start1, range1);
    }
    int totaluser = 0;
    if (StringUtils.isNullOrEmpty(results))
    {
      if ((userform.getIsUserGroupSearch() != null) && (userform.getIsUserGroupSearch().equals("Y"))) {
        totaluser = UserDAO.getCountOfUserGroupsByCriteria(user1, userform.getGroupName());
      } else {
        totaluser = UserDAO.getCountOfUsersByCriteria(user1, userform.getFirstName(), userform.getLastName(), new Long(userform.getOrgId()).longValue(), new Long(userform.getDepartmentId()).longValue());
      }
    }
    else {
      totaluser = new Integer(results).intValue();
    }
    userform.setStart(String.valueOf(start1));
    userform.setRange(String.valueOf(range1));
    userform.setResults(String.valueOf(totaluser));
    userform.setFirstName(userform.getFirstName());
    userform.setLastName(userform.getLastName());
    userform.setUserList(userList);
    if (boxnumber != null) {
      request.setAttribute("boxnumber", boxnumber);
    }
    return mapping.findForward("assignedtoselector");
  }
  
  public ActionForward setuseridsinsession(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside setuseridsinsession method");
    String userid = request.getParameter("userid");
    String username = request.getParameter("username");
    List userids = new ArrayList();
    List usernames = new ArrayList();
    if (request.getSession().getAttribute("checkeduserids") != null)
    {
      userids = (List)request.getSession().getAttribute("checkeduserids");
      usernames = (List)request.getSession().getAttribute("checkedusernames");
      if (userids.contains(userid))
      {
        userids.remove(userid);
        usernames.remove(username);
      }
      else
      {
        userids.add(userid);
        usernames.add(username);
      }
      request.getSession().setAttribute("checkeduserids", userids);
      request.getSession().setAttribute("checkedusernames", usernames);
    }
    else
    {
      userids.add(userid);
      usernames.add(username);
      request.getSession().setAttribute("checkeduserids", userids);
      request.getSession().setAttribute("checkedusernames", usernames);
    }
    return null;
  }
  
  public ActionForward setuseridsinsessionallselect(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside setuseridsinsessionallselect method");
    String userid = request.getParameter("userids");
    String username = request.getParameter("usernames");
    List userids = new ArrayList();
    List usernames = new ArrayList();
    String[] columnid = null;
    String[] columnusername = null;
    if ((userid != null) && (userid.length() > 0)) {
      columnid = StringUtils.tokenize(userid, ",");
    }
    if ((username != null) && (username.length() > 0)) {
      columnusername = StringUtils.tokenize(username, ",");
    }
    if (request.getSession().getAttribute("checkeduserids") != null)
    {
      userids = (List)request.getSession().getAttribute("checkeduserids");
      usernames = (List)request.getSession().getAttribute("checkedusernames");
      for (int i = 0; i < columnid.length; i++) {
        if (userids.contains(columnid[i]))
        {
          userids.remove(columnid[i]);
          usernames.remove(columnusername[i]);
        }
        else
        {
          for (int j = 0; j < columnid.length; j++)
          {
            userids.add(columnid[j]);
            usernames.add(columnusername[j]);
          }
        }
      }
      request.getSession().setAttribute("checkeduserids", userids);
      request.getSession().setAttribute("checkedusernames", usernames);
    }
    else
    {
      for (int i = 0; i < columnid.length; i++)
      {
        userids.add(columnid[i]);
        usernames.add(columnusername[i]);
      }
      request.getSession().setAttribute("checkeduserids", userids);
      request.getSession().setAttribute("checkedusernames", usernames);
    }
    return null;
  }
  
  public ActionForward removeuseridsinsession(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside removeuseridsinsession method");
    if (request.getSession().getAttribute("checkeduserids") != null) {
      request.getSession().removeAttribute("checkeduserids");
    }
    if (request.getSession().getAttribute("checkedusernames") != null) {
      request.getSession().removeAttribute("checkedusernames");
    }
    return null;
  }
  
  public ActionForward watchlistsearch(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside watchlistsearch method");
    String boxnumber = request.getParameter("boxnumber");
    User user1 = (User)request.getSession().getAttribute("user_data");
    
    String start = request.getParameter("start");
    String range = request.getParameter("range");
    String results = request.getParameter("results");
    CreateUserForm userform = (CreateUserForm)form;
    int start1 = StringUtils.stringToInt(start, 0);
    int range1 = StringUtils.stringToInt(range, 15);
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    

    String readpreview = request.getParameter("readPreview");
    userform.setReadPreview(readpreview);
    List orgList = BOFactory.getLovBO().getAllOrganization(user1.getSuper_user_key());
    userform.setOrganizationList(orgList);
    List deptlist = new ArrayList();
    userform.setDepartmentList(deptlist);
    logger.info("Inside orgList method" + orgList.size());
    if (orgList.size() > 0)
    {
      Organization org = (Organization)orgList.get(0);
      logger.info("Inside orgList method" + org.getOrgId());
      userform.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(org.getOrgId())));
    }
    logger.info("Inside deptlist method" + deptlist.size());
    List userList = UserDAO.getUsersByCritera(user1, userform.getFirstName(), userform.getLastName(), new Long(userform.getOrgId()).longValue(), new Long(userform.getDepartmentId()).longValue(), start1, range1);
    int totaluser = 0;
    if (StringUtils.isNullOrEmpty(results)) {
      totaluser = UserDAO.getCountOfUsersByCriteria(user1, userform.getFirstName(), userform.getLastName(), new Long(userform.getOrgId()).longValue(), new Long(userform.getDepartmentId()).longValue());
    } else {
      totaluser = new Integer(results).intValue();
    }
    logger.info("Inside userList method" + userList.size());
    userform.setStart(String.valueOf(start1));
    userform.setRange(String.valueOf(range1));
    userform.setResults(String.valueOf(totaluser));
    userform.setFirstName(userform.getFirstName());
    userform.setLastName(userform.getLastName());
    userform.setUserList(userList);
    logger.info("Inside watchlistsearch method");
    if (boxnumber != null) {
      request.setAttribute("boxnumber", boxnumber);
    }
    return mapping.findForward("watchlistselector");
  }
  
  public ActionForward userQualificationDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside userQualificationDetails method");
    CreateUserForm userform = (CreateUserForm)form;
    String userId = request.getParameter("userId");
    logger.info("userId >> " + userId);
    userform.setUserId(new Long(userId).longValue());
    List userEducationList = new ArrayList();
    userEducationList = BOFactory.getUserBO().getUserEducationListByUserId(new Long(userId).longValue());
    logger.info("userEducationList size >>> " + userEducationList.size());
    userform.setUserEducationDetailsList(userEducationList);
    
    List userSkillslist = BOFactory.getUserBO().getUserSkillsListByUserId(new Long(userId).longValue());
    userform.setUserSkillsDetailsList(userSkillslist);
    
    List userLanguageslist = BOFactory.getUserBO().getUserLanguagesListByUserId(new Long(userId).longValue());
    userform.setUserLanguagesDetailsList(userLanguageslist);
    
    List userLicenseslist = BOFactory.getUserBO().getUserLicensesListByUserId(new Long(userId).longValue());
    userform.setUserLicenseDetailsList(userLicenseslist);
    


    return mapping.findForward("userQualificationDetails");
  }
  
  public ActionForward deleteUserQualificationMultiple(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteUserQualificationMultiple method");
    CreateUserForm userform = (CreateUserForm)form;
    String userId = request.getParameter("userId");
    logger.info("userId >> " + userId);
    userform.setUserId(new Long(userId).longValue());
    String checkedItems = request.getParameter("checkedItems");
    String screenName = request.getParameter("screenName");
    logger.info("screenName >> " + screenName);
    if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("Education")))
    {
      BOFactory.getUserBO().deleteUserEducationdetailsMultiple(checkedItems);
      request.setAttribute("deleteUserEducationMultiple", "yes");
    }
    else if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("Skills")))
    {
      BOFactory.getUserBO().deleteUserSkillsdetailsMultiple(checkedItems);
      request.setAttribute("deleteUserSkillsMultiple", "yes");
    }
    else if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("Languages")))
    {
      BOFactory.getUserBO().deleteUserLanguagesdetailsMultiple(checkedItems);
      request.setAttribute("deleteUserLanguagesMultiple", "yes");
    }
    else if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("License")))
    {
      BOFactory.getUserBO().deleteUserLicensesdetailsMultiple(checkedItems);
      request.setAttribute("deleteUserLicenseMultiple", "yes");
    }
    List userEducationList = new ArrayList();
    userEducationList = BOFactory.getUserBO().getUserEducationListByUserId(new Long(userId).longValue());
    logger.info("userEducationList size >>> " + userEducationList.size());
    userform.setUserEducationDetailsList(userEducationList);
    
    List userSkillslist = BOFactory.getUserBO().getUserSkillsListByUserId(new Long(userId).longValue());
    userform.setUserSkillsDetailsList(userSkillslist);
    
    List userLanguageslist = BOFactory.getUserBO().getUserLanguagesListByUserId(new Long(userId).longValue());
    userform.setUserLanguagesDetailsList(userLanguageslist);
    
    List userLicenseslist = BOFactory.getUserBO().getUserLicensesListByUserId(new Long(userId).longValue());
    userform.setUserLicenseDetailsList(userLicenseslist);
    


    return mapping.findForward("userQualificationDetails");
  }
}
