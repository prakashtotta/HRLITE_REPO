package com.action;

import com.bean.Country;
import com.bean.Locale;
import com.bean.ProfilePhoto;
import com.bean.Role;
import com.bean.State;
import com.bean.Timezone;
import com.bean.User;
import com.bo.BOFactory;
import com.bo.LovBO;
import com.dao.LovOpsDAO;
import com.dao.UserDAO;
import com.form.CreateUserForm;
import com.util.EncryptDecrypt;
import java.sql.Blob;
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

public class AgencyDetails
  extends CommonAgencyAction
{
  protected static final Logger logger = Logger.getLogger(AgencyDetails.class);
  
  public ActionForward mydetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside mydetails method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user1 = (User)request.getSession().getAttribute("agency_data");
    
    CreateUserForm userform = (CreateUserForm)form;
    
    User usr = UserDAO.getUser(user1.getUserId());
    
    userform.fromValue(usr, request);
    
    return mapping.findForward("mydetails");
  }
  
  public ActionForward uploadUserPhotoscr(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside uploadUserPhoto method ... ");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String userId = request.getParameter("userId");
    
    User usr = UserDAO.getUser(new Long(userId).longValue());
    CreateUserForm userform = (CreateUserForm)form;
    userform.fromValue(usr, request);
    return mapping.findForward("uploadUserPhoto");
  }
  
  public ActionForward uploadUserPhoto(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside uploadUserPhoto method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String userId = request.getParameter("userId");
    User user1 = (User)request.getSession().getAttribute("agency_data");
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
  
  public ActionForward editmyvendor(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside editmyvendor method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    String userId = request.getParameter("userId");
    CreateUserForm userform = (CreateUserForm)form;
    
    User usr = UserDAO.getUser(new Long(userId).longValue());
    
    userform.fromValue(usr, request);
    


    userform.setLocaleList(BOFactory.getLovBO().getAllLocales());
    userform.setTimezoneList(BOFactory.getLovBO().getAllTimezones());
    List countryList = BOFactory.getLovBO().getAllCountries();
    userform.setCountryList(countryList);
    
    List stateList = BOFactory.getLovBO().getStateListByCountry(usr.getCountry().getCountryId());
    userform.setStateList(stateList);
    
    String readpreview = request.getParameter("readPreview");
    userform.setReadPreview(readpreview);
    

    return mapping.findForward("editmyvendor");
  }
  
  public ActionForward updatemydetailsvendor(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updatemydetailsvendor method");
    String userId = request.getParameter("userId");
    CreateUserForm userform = (CreateUserForm)form;
    User user1 = (User)request.getSession().getAttribute("agency_data");
    
    User usr = UserDAO.getUser(new Long(userId).longValue());
    



    usr.setFirstName(userform.getFirstName());
    usr.setMiddleName(userform.getMiddleName());
    usr.setLastName(userform.getLastName());
    usr.setEmailId(userform.getEmailId());
    usr.setPhoneHome(userform.getPhoneHome());
    usr.setPhoneOffice(userform.getPhoneOffice());
    
    Country country = new Country();
    country.setCountryId(userform.getCountryId());
    usr.setCountry(country);
    if (userform.getStateId() != 0L)
    {
      State state = new State();
      state.setStateId(userform.getStateId());
      usr.setState(state);
    }
    usr.setCity(userform.getCity());
    
    Locale lo = new Locale();
    lo.setLocaleId(userform.getLocaleId());
    usr.setLocale(lo);
    
    Timezone tz = new Timezone();
    tz.setTimezoneId(userform.getTimezoneId());
    usr.setTimezone(tz);
    


    Role rl = LovOpsDAO.getRoleByCode("VENDOR", user1.getSuper_user_key());
    Role role = new Role();
    role.setRoleId(rl.getRoleId());
    usr.setRole(role);
    usr.setType("Vendor");
    
    usr.setPassword(usr.getPassword());
    usr = UserDAO.updateUser(usr);
    




    userform.fromValue(usr, request);
    request.setAttribute("isuseradded", "yes");
    

    userform.setLocaleList(BOFactory.getLovBO().getAllLocales());
    userform.setTimezoneList(BOFactory.getLovBO().getAllTimezones());
    List countryList = BOFactory.getLovBO().getAllCountries();
    userform.setCountryList(countryList);
    
    List stateList = BOFactory.getLovBO().getStateListByCountry(usr.getCountry().getCountryId());
    userform.setStateList(stateList);
    


    return mapping.findForward("editmyvendor");
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
  
  public ActionForward changepasswordsubmit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside changepasswordsubmit method");
    CreateUserForm userform = (CreateUserForm)form;
    String userId = request.getParameter("userId");
    User usr = UserDAO.getUser(new Long(userId).longValue());
    String currentpasswordenc = EncryptDecrypt.encrypt(userform.getCurrentpassword());
    if (!currentpasswordenc.equals(usr.getPassword()))
    {
      ActionErrors errors = new ActionErrors();
      
      errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError("current.password.wrong"));
      saveErrors(request, errors);
    }
    else
    {
      usr.setPassword(EncryptDecrypt.encrypt(userform.getPassword()));
      UserDAO.updateUser(usr);
      request.setAttribute("isuseradded", "yes");
    }
    return mapping.findForward("changepasswordscr");
  }
}
