package com.bo;

import com.bean.ApplicantOtherDetails;
import com.bean.Country;
import com.bean.DashBoardConfig;
import com.bean.Department;
import com.bean.Designations;
import com.bean.JobApplicant;
import com.bean.JobRequisition;
import com.bean.Locale;
import com.bean.Location;
import com.bean.Organization;
import com.bean.ProjectCodes;
import com.bean.Role;
import com.bean.State;
import com.bean.Timezone;
import com.bean.User;
import com.bean.UserRegData;
import com.bean.employee.EmergencyContact;
import com.bean.employee.Imigrations;
import com.bean.employee.OrganizationDetails;
import com.bean.employee.UserContactInfo;
import com.bean.employee.UserDependents;
import com.bean.employee.UserEducationDetails;
import com.bean.employee.UserJobDetails;
import com.bean.employee.UserLanguages;
import com.bean.employee.UserLicenses;
import com.bean.employee.UserMemberShip;
import com.bean.employee.UserReportTo;
import com.bean.employee.UserSalary;
import com.bean.employee.UserSkills;
import com.bean.pool.TalentPool;
import com.common.Common;
import com.common.CommonException;
import com.common.WSException;
import com.dao.LovOpsDAO;
import com.dao.UserDAO;
import com.form.CreateUserForm;
import com.form.UserRegForm;
import com.manager.EmailTaskManager;
import com.resources.Constant;
import com.util.DateUtil;
import com.util.EmailTask;
import com.util.EncryptDecrypt;
import com.util.PasswordGenerator;
import com.util.PermissionChecker;
import com.util.StringUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

public class UserBO
{
  protected static final Logger logger = Logger.getLogger(UserBO.class);
  UserDAO userdao;
  
  public static byte[] getUserProfilePhoto(long photoId)
  {
    return UserDAO.getUserProfilePhoto(photoId);
  }
  
  public UserRegData updateUserRegData(UserRegData usr)
  {
    return this.userdao.updateUserRegData(usr);
  }
  
  public static List getAllUsers()
  {
    return UserDAO.getAllUsers();
  }
  
  public UserContactInfo getUserContactInfo(long userId)
  {
    return this.userdao.getUserContactInfo(userId);
  }
  
  public EmergencyContact getUserEmergencyContactInfo(long emergencyContactId)
  {
    return this.userdao.getUserEmergencyContactInfo(emergencyContactId);
  }
  
  public UserContactInfo getUserContactInfobyUsrContactId(long userContactId)
  {
    return this.userdao.getUserContactInfobyUsrContactId(userContactId);
  }
  
  public UserContactInfo updateUserContactInfo(UserContactInfo usercontact)
  {
    return this.userdao.updateUserContactInfo(usercontact);
  }
  
  public UserContactInfo saveUserContactInfo(UserContactInfo usercontact)
  {
    return this.userdao.saveUserContactInfo(usercontact);
  }
  
  public EmergencyContact saveUserEmergencyContactInfo(EmergencyContact emergencyContact)
  {
    return this.userdao.saveUserEmergencyContactInfo(emergencyContact);
  }
  
  public EmergencyContact updateUserEmergencyContactInfo(EmergencyContact emergencyContact)
  {
    return this.userdao.updateUserEmergencyContactInfo(emergencyContact);
  }
  
  public EmergencyContact deleteEmergencyContact(EmergencyContact emergencyContact)
  {
    return this.userdao.deleteEmergencyContact(emergencyContact);
  }
  
  public List getUserEmergencyContactlist(long userId)
  {
    return this.userdao.getUserEmergencyContactlist(userId);
  }
  
  public UserDependents saveDependents(UserDependents userDependents)
  {
    return this.userdao.saveDependents(userDependents);
  }
  
  public UserDependents updateDependents(UserDependents userDependents)
  {
    return this.userdao.updateDependents(userDependents);
  }
  
  public UserDependents deleteDependents(UserDependents userDependents)
  {
    return this.userdao.deleteDependents(userDependents);
  }
  
  public List getUserDependentslistByUserId(long userId)
  {
    return this.userdao.getUserDependentslistByUserId(userId);
  }
  
  public UserDependents getUserDependentInfo(long userDependentId)
  {
    return this.userdao.getUserDependentInfo(userDependentId);
  }
  
  public Imigrations saveUserImmigration(Imigrations imigrations)
  {
    return this.userdao.saveUserImmigration(imigrations);
  }
  
  public Imigrations updateUserImmigration(Imigrations imigrations)
  {
    return this.userdao.updateUserImmigration(imigrations);
  }
  
  public Imigrations deleteUserImmigration(Imigrations imigrations)
  {
    return this.userdao.deleteUserImmigration(imigrations);
  }
  
  public List getUserImmigrationlistByUserId(long userId)
  {
    return this.userdao.getUserImmigrationlistByUserId(userId);
  }
  
  public Imigrations getUserImmigrationInfo(long imigrationId)
  {
    return this.userdao.getUserImmigrationInfo(imigrationId);
  }
  
  public List getUserSalaryComponentListByUserId(long userId)
  {
    return this.userdao.getUserSalaryComponentListByUserId(userId);
  }
  
  public UserSalary saveUserSalaryComponent(UserSalary userSalary)
  {
    return this.userdao.saveUserSalaryComponent(userSalary);
  }
  
  public UserSalary updateUserSalaryComponent(UserSalary userSalary)
  {
    return this.userdao.updateUserSalaryComponent(userSalary);
  }
  
  public UserSalary deleteUserSalaryComponent(UserSalary userSalary)
  {
    return this.userdao.deleteUserSalaryComponent(userSalary);
  }
  
  public UserSalary getUserSalaryInfo(long userSalaryId)
  {
    return this.userdao.getUserSalaryInfo(userSalaryId);
  }
  
  public void deleteUserSalarydetailsMultiple(String checkedItems)
  {
    this.userdao.deleteUserSalarydetailsMultiple(checkedItems);
  }
  
  public void deleteUserEducationdetailsMultiple(String checkedItems)
  {
    this.userdao.deleteUserEducationdetailsMultiple(checkedItems);
  }
  
  public void deleteUserSkillsdetailsMultiple(String checkedItems)
  {
    this.userdao.deleteUserSkillsdetailsMultiple(checkedItems);
  }
  
  public void deleteUserLanguagesdetailsMultiple(String checkedItems)
  {
    this.userdao.deleteUserLanguagesdetailsMultiple(checkedItems);
  }
  
  public void deleteUserLicensesdetailsMultiple(String checkedItems)
  {
    this.userdao.deleteUserLicensesdetailsMultiple(checkedItems);
  }
  
  public void deleteUserExperienceMultiple(String checkedItems)
  {
    this.userdao.deleteUserExperienceMultiple(checkedItems);
  }
  
  public void deleteUserDependentsMultiple(String checkedItems)
  {
    this.userdao.deleteUserDependentsMultiple(checkedItems);
  }
  
  public void deleteUserMembershipMultiple(String checkedItems)
  {
    this.userdao.deleteUserMembershipMultiple(checkedItems);
  }
  
  public void deleteUserImmigrationMultiple(String checkedItems)
  {
    this.userdao.deleteUserImmigrationMultiple(checkedItems);
  }
  
  public void deleteEmergencyContactMultiple(String checkedItems)
  {
    this.userdao.deleteEmergencyContactMultiple(checkedItems);
  }
  
  public UserJobDetails getUserJobdetailsByUserId(long userId)
  {
    return this.userdao.getUserJobdetailsByUserId(userId);
  }
  
  public UserJobDetails getUserJobdetails(long jobdetailsId)
  {
    return this.userdao.getUserJobdetails(jobdetailsId);
  }
  
  public UserJobDetails saveUserJobdata(UserJobDetails userJobDetails)
  {
    return this.userdao.saveUserJobdata(userJobDetails);
  }
  
  public UserJobDetails updateUserJobdata(UserJobDetails userJobDetails)
  {
    return this.userdao.updateUserJobdata(userJobDetails);
  }
  
  public void updateDesignationByUserId(long userId, long designationId)
  {
    this.userdao.updateDesignationByUserId(userId, designationId);
  }
  
  public UserReportTo getUserReportTodetailsByUserId(long userId)
  {
    return this.userdao.getUserReportTodetailsByUserId(userId);
  }
  
  public UserReportTo getUserReportTodetailsById(long reportToId)
  {
    return this.userdao.getUserReportTodetailsById(reportToId);
  }
  
  public UserReportTo saveUserReportTodata(UserReportTo userReportTo)
  {
    return this.userdao.saveUserReportTodata(userReportTo);
  }
  
  public UserReportTo updateUserReportTodata(UserReportTo userReportTo)
  {
    return this.userdao.updateUserReportTodata(userReportTo);
  }
  
  public UserMemberShip saveUserMembershipDetails(UserMemberShip userMemberShip)
  {
    return this.userdao.saveUserMembershipDetails(userMemberShip);
  }
  
  public UserMemberShip updateUserMembershipDetails(UserMemberShip userMemberShip)
  {
    return this.userdao.updateUserMembershipDetails(userMemberShip);
  }
  
  public UserMemberShip deleteUserMembershipDetails(UserMemberShip userMemberShip)
  {
    return this.userdao.deleteUserMembershipDetails(userMemberShip);
  }
  
  public List getUserMembershiplistByUserId(long userId)
  {
    return this.userdao.getUserMembershiplistByUserId(userId);
  }
  
  public UserMemberShip getUserMembershipDetails(long userMemberShipId)
  {
    return this.userdao.getUserMembershipDetails(userMemberShipId);
  }
  
  public UserMemberShip getUserMembershipDetailsByUserIdandMembershipTypeId(long userId, long membershipTypeId)
  {
    return this.userdao.getUserMembershipDetailsByUserIdandMembershipTypeId(userId, membershipTypeId);
  }
  
  public UserEducationDetails saveUserEducationDetails(UserEducationDetails userEducationDetails)
  {
    return this.userdao.saveUserEducationDetails(userEducationDetails);
  }
  
  public UserEducationDetails updateUserEducationDetails(UserEducationDetails userEducationDetails)
  {
    return this.userdao.updateUserEducationDetails(userEducationDetails);
  }
  
  public UserEducationDetails deleteUserEducationDetails(UserEducationDetails userEducationDetails)
  {
    return this.userdao.deleteUserEducationDetails(userEducationDetails);
  }
  
  public List getUserEducationListByUserId(long userId)
  {
    return this.userdao.getUserEducationListByUserId(userId);
  }
  
  public List getUserSkillsListByUserId(long userId)
  {
    return this.userdao.getUserSkillsListByUserId(userId);
  }
  
  public List getUserLanguagesListByUserId(long userId)
  {
    return this.userdao.getUserLanguagesListByUserId(userId);
  }
  
  public List getUserLicensesListByUserId(long userId)
  {
    return this.userdao.getUserLicensesListByUserId(userId);
  }
  
  public Set getUsers(long orgId, long departmentId, long designationId)
  {
    List users = this.userdao.getListUsers(orgId, departmentId, designationId);
    Set set = new HashSet(users);
    return set;
  }
  
  public UserEducationDetails getUserEducationDetails(long educationId)
  {
    return this.userdao.getUserEducationDetails(educationId);
  }
  
  public UserEducationDetails getUserEducationDetailsByUserIdandEducationName(long userId, String educationName)
  {
    return this.userdao.getUserEducationDetailsByUserIdandEducationName(userId, educationName);
  }
  
  public UserSkills saveUserSkillsDetails(UserSkills userSkills)
  {
    return this.userdao.saveUserSkillsDetails(userSkills);
  }
  
  public UserSkills updateUserSkillsDetails(UserSkills userSkills)
  {
    return this.userdao.updateUserSkillsDetails(userSkills);
  }
  
  public UserSkills deleteUserSkillsDetails(UserSkills userSkills)
  {
    return this.userdao.deleteUserSkillsDetails(userSkills);
  }
  
  public UserSkills getUserSkillDetails(long skillId)
  {
    return this.userdao.getUserSkillDetails(skillId);
  }
  
  public UserSkills getUserSkillDetailsByUserIdandSkillName(long userId, String skillName)
  {
    return this.userdao.getUserSkillDetailsByUserIdandSkillName(userId, skillName);
  }
  
  public UserLanguages saveUserLanguageDetails(UserLanguages userLanguages)
  {
    return this.userdao.saveUserLanguageDetails(userLanguages);
  }
  
  public UserLanguages updateUserLanguageDetails(UserLanguages userLanguages)
  {
    return this.userdao.updateUserLanguageDetails(userLanguages);
  }
  
  public UserLanguages deleteUserLanguageDetails(UserLanguages userLanguages)
  {
    return this.userdao.deleteUserLanguageDetails(userLanguages);
  }
  
  public UserLanguages getUserLanguagesDetails(long userLangId)
  {
    return this.userdao.getUserLanguagesDetails(userLangId);
  }
  
  public UserLanguages getUserLanguagesDetailsByUserIdLanguageIdandFluency(long userLangId, long languageId, String fluency)
  {
    return this.userdao.getUserLanguagesDetailsByUserIdLanguageIdandFluency(userLangId, languageId, fluency);
  }
  
  public UserLicenses saveUserLicenseDetails(UserLicenses userLicenses)
  {
    return this.userdao.saveUserLicenseDetails(userLicenses);
  }
  
  public UserLicenses updateUserLicenseDetails(UserLicenses userLicenses)
  {
    return this.userdao.updateUserLicenseDetails(userLicenses);
  }
  
  public UserLicenses deleteUserLicenseDetails(UserLicenses userLicenses)
  {
    return this.userdao.deleteUserLicenseDetails(userLicenses);
  }
  
  public UserLicenses getUserLicenseDetails(long userLicenseId)
  {
    return this.userdao.getUserLicenseDetails(userLicenseId);
  }
  
  public UserLicenses getUserLicenseDetailsByUserIdandLicenseTypeId(long userId, long licenseTypeId)
  {
    return this.userdao.getUserLicenseDetailsByUserIdandLicenseTypeId(userId, licenseTypeId);
  }
  
  public OrganizationDetails saveUserExperienceDetails(OrganizationDetails organizationDetails)
  {
    return this.userdao.saveUserExperienceDetails(organizationDetails);
  }
  
  public OrganizationDetails updateUserExperienceDetails(OrganizationDetails organizationDetails)
  {
    return this.userdao.updateUserExperienceDetails(organizationDetails);
  }
  
  public OrganizationDetails deleteUserExperienceDetails(OrganizationDetails organizationDetails)
  {
    return this.userdao.deleteUserExperienceDetails(organizationDetails);
  }
  
  public OrganizationDetails getUserExperienceDetails(long orgDetailsId)
  {
    return this.userdao.getUserExperienceDetails(orgDetailsId);
  }
  
  public List getUserExperienceListByUserId(long userId)
  {
    return this.userdao.getUserExperienceListByUserId(userId);
  }
  
  public static List getUsersForPagination(String dir_str, String sort_str, int pageSize, int startIndex, String type)
  {
    return UserDAO.getUsersForPagination(dir_str, sort_str, pageSize, startIndex, type);
  }
  
  public static Map getUserGroupsForPagination(User user, String grpname, String description, String userId, String dir_str, String sort_str, int pageSize, int startIndex)
  {
    return UserDAO.getUserGroupsForPagination(user, grpname, description, userId, dir_str, sort_str, pageSize, startIndex);
  }
  
  public static int getCountOfAllUsers(String type)
  {
    return UserDAO.getCountOfAllUsers(type);
  }
  
  public static List getListwithFullName(List userList)
  {
    List newFullNameList = new ArrayList();
    for (int i = 0; i < userList.size(); i++)
    {
      User usr = (User)userList.get(i);
      usr.setFullName(usr.getFirstName() + " " + usr.getLastName() + " " + usr.getEmailId());
      newFullNameList.add(usr);
    }
    return newFullNameList;
  }
  
  public static User getUserByEmailId(String email)
  {
    return UserDAO.getUserByEmailId(email);
  }
  
  public static User getUserByUserName(String username)
  {
    return UserDAO.getUserByUserName(username);
  }
  
  public static User getVendorsByUsername(String username)
  {
    return UserDAO.getVendorsByUsername(username);
  }
  
  public static User getUserByUserId(long userId)
  {
    return UserDAO.getUser(userId);
  }
  
  public User getUserByUserid(long userId)
  {
    return this.userdao.getUserByUserid(userId);
  }
  
  public List getAllHiringMgr(User user, long roleId)
  {
    return this.userdao.getAllHiringMgr(user, roleId);
  }
  
  public List getAllRecruiter(User user, long roleId)
  {
    return this.userdao.getAllRecruiter(user, roleId);
  }
  
  public static User getUserFullNameEmailAndUserId(String username)
  {
    return UserDAO.getUserFullNameEmailAndUserId(username);
  }
  
  public static User getUserFullNameEmailAndUserId(long userId)
  {
    return UserDAO.getUserFullNameEmailAndUserId(userId);
  }
  
  public static List searchUsersForPagination(User user, String statuscri, String orgId, String departmentId, String projectId, String designationId, String firstname, String lastname, String userName, String emailid, String dir_str, String sort_str, int pageSize, int startIndex, String type)
  {
    List userList = UserDAO.searchUsersForPagination(user, statuscri, orgId, departmentId, projectId, designationId, firstname, lastname, userName, emailid, dir_str, sort_str, pageSize, startIndex, type);
    

    return userList;
  }
  
  public static List searchUsersForPagination(String dir_str, String sort_str, int pageSize, int startIndex, String type)
  {
    List userList = UserDAO.searchUsersForPagination(dir_str, sort_str, pageSize, startIndex, type);
    List newUserList = new ArrayList();
    

    return userList;
  }
  
  public static List searchVendorsForPagination(User user, String searchpagedisplay, String statuscri, String countryId, String stateId, String city, String firstname, String lastname, String emailid, String dir_str, String sort_str, int pageSize, int startIndex, String type)
  {
    return UserDAO.searchVendorsForPagination(user, searchpagedisplay, statuscri, countryId, stateId, city, firstname, lastname, emailid, dir_str, sort_str, pageSize, startIndex, type);
  }
  
  public static int searchUsersCount(User user1, String statuscri, String orgId, String departmentId, String projectId, String designationId, String firstname, String lastname, String userName, String type, String emailid)
  {
    return UserDAO.searchUsersCount(user1, statuscri, orgId, departmentId, projectId, designationId, firstname, lastname, userName, type, emailid);
  }
  
  public static int searchUsersCount()
  {
    return UserDAO.searchUsersCount();
  }
  
  public static int searchVendorsCount(User user, String searchpagedisplay, String statuscri, String countryId, String stateId, String city, String firstname, String lastname, String type, String emailid)
  {
    return UserDAO.searchVendorsCount(user, searchpagedisplay, statuscri, countryId, stateId, city, firstname, lastname, type, emailid);
  }
  
  public static List<String> getUserData(String query)
  {
    List<String> users = UserDAO.getUserDataByQuery(query);
    return users;
  }
  
  public static List<String> getUserDataByQueryAutoSuggest(User user, String query)
  {
    List<String> users = UserDAO.getUserDataByQueryAutoSuggest(user, query);
    return users;
  }
  
  public String getUserDataByQueryAutoSuggestJSON(User user, String query)
  {
    String data = "";
    List<User> users = this.userdao.getUserDataByQueryAutoSuggestJSON(user, query);
    for (int i = 0; i < users.size(); i++)
    {
      User usertemp = (User)users.get(i);
      data = data + "{ " + "\"" + "id" + "\"" + ":" + "\"" + usertemp.getUserId() + "\"," + "  " + "\"" + "label" + "\"" + ":" + "\"" + usertemp.getFirstName() + " " + usertemp.getLastName() + "\"," + "\"" + "value" + "\"" + ":" + "\"" + usertemp.getFirstName() + " " + usertemp.getLastName() + "\"" + "}" + ",";
    }
    if (!StringUtils.isNullOrEmpty(data)) {
      data = data.substring(0, data.length() - 1);
    }
    return data;
  }
  
  public String getUserDataByQueryAutoSuggestJSONMulti(User user, String query)
  {
    String data = "";
    List<User> users = this.userdao.getUserDataByQueryAutoSuggestJSON(user, query);
    for (int i = 0; i < users.size(); i++)
    {
      User usertemp = (User)users.get(i);
      data = data + "{ " + "\"" + "id" + "\"" + ":" + "\"" + usertemp.getUserId() + "\"," + "  " + "\"" + "name" + "\"" + ":" + "\"" + usertemp.getFirstName() + " " + usertemp.getLastName() + "\"" + "}" + ",";
    }
    if (!StringUtils.isNullOrEmpty(data)) {
      data = data.substring(0, data.length() - 1);
    }
    return data;
  }
  
  public String getUserUserGroupDataByQueryAutoSuggestJSONMulti(User user, String query)
  {
    String data = "";
    List users = this.userdao.getUserUserGroupDataByQueryAutoSuggestJSONMulti(user, query);
    for (int i = 0; i < users.size(); i++)
    {
      User usertemp = (User)users.get(i);
      data = data + "{ " + "\"" + "id" + "\"" + ":" + "\"" + usertemp.getUserName() + "\"," + "  " + "\"" + "name" + "\"" + ":" + "\"" + usertemp.getFullName() + "\"" + "}" + ",";
    }
    if (!StringUtils.isNullOrEmpty(data)) {
      data = data.substring(0, data.length() - 1);
    }
    return data;
  }
  
  public static List<String> getUserUserGroupDataByQueryAutoSuggest(User user, String query)
  {
    List<String> users = UserDAO.getUserUserGroupDataByQueryAutoSuggest(user, query);
    return users;
  }
  
  public UserRegData getUserRegDataByipaddress(String ipaddress)
  {
    return this.userdao.getUserRegDataByipaddress(ipaddress);
  }
  
  public UserRegData getUserRegDataBySubdomain(String subdomain)
  {
    return this.userdao.getUserRegDataBySubdomain(subdomain);
  }
  
  public UserRegData getUserRegDataByid(String userregid)
  {
    return this.userdao.getUserRegDataByid(userregid);
  }
  
  public User saveUserReg(UserRegData userreg, UserRegForm regform)
  {
    User user = new User();
    try
    {
      String localecode = Constant.getValue("default.locale.code");
      Locale lo = BOFactory.getLovBO().getLocaleByCode(localecode);
      userreg.setLocale(lo);
      userreg = this.userdao.saveUserRegData(userreg);
      String locname = "";
      Location location = new Location();
      if ((userreg.getNationality() != null) && (userreg.getNationality().getCountryId() != 0L))
      {
        Country country = BOFactory.getLovBO().getCountry(userreg.getNationality().getCountryId());
        country.setCountryId(userreg.getNationality().getCountryId());
        location.setCountry(country);
        locname = country.getCountryName();
      }
      if (!StringUtils.isNullOrEmpty(userreg.getCity())) {
        locname = locname + " ," + userreg.getCity();
      }
      location.setLocationName(locname);
      location.setLocationCode(locname);
      location.setSuper_user_key(userreg.getUserRegId());
      location.setStatus("A");
      location = BOFactory.getOrganizationBO().saveLocation(location);
      
      Organization org = new Organization();
      org.setOrgCode(userreg.getOrgName());
      org.setOrgName(userreg.getOrgName());
      org.setIsRoot("Y");
      org.setIsLegal("Y");
      org.setParentOrg(null);
      org.setOrganizationtype(null);
      org.setLocation(location);
      org.setSuper_user_key(userreg.getUserRegId());
      org.setStatus("A");
      org.setCurrencyCode(Constant.getValue("default.currency.code"));
      BOFactory.getOrganizationBO().saveOrganization(org);
      

      user.setFirstName(userreg.getFirstName());
      user.setMiddleName(userreg.getMiddleName());
      user.setLastName(userreg.getLastName());
      user.setEmailId(userreg.getEmailId());
      user.setUserName(userreg.getEmailId());
      user.setPhoneHome(userreg.getPhoneHome());
      user.setPhoneOffice(userreg.getPhoneOffice());
      user.setPassword(userreg.getPassword());
      user.setCreatedBy(userreg.getCreatedBy());
      user.setCreatedDate(new Date());
      user.setLocale(userreg.getLocale());
      user.setTimezone(userreg.getTimezone());
      user.setCity(userreg.getCity());
      user.setNationality(userreg.getNationality());
      user.setSuper_user_key(userreg.getUserRegId());
      user.setOrganization(org);
      



      copyRolesToSuperUser(userreg.getUserRegId());
      Role role = getRoleByRoleCode(userreg.getUserRegId(), "Admin");
      




      user.setRole(role);
      user.setStatus("A");
      addUser(user);
      



      copyEthicityToSuperUser(userreg.getUserRegId());
      copyWorkShift(userreg.getUserRegId());
      copyJobType(userreg.getUserRegId());
      copyJobCategory(userreg.getUserRegId());
      copyCategoryLOV(userreg.getUserRegId());
      copyJobTitles(userreg.getUserRegId());
      copyNotificationSettings(userreg.getUserRegId());
      if ((!StringUtils.isNullOrEmpty(userreg.getPackagetaken())) && (Constant.isPackageContainFunction(userreg.getPackagetaken(), "screenSetting")))
      {
        copyScreenFieldsToSuperUser(userreg.getUserRegId());
      }
      else
      {
        List<String> screenCodeList = new ArrayList();
        screenCodeList.add("APPLICANT_SCREEN");
        screenCodeList.add("REQUISITION_SCREEN");
        
        copyScreenFieldsToSuperUser(screenCodeList, userreg.getUserRegId());
      }
      if ((!StringUtils.isNullOrEmpty(userreg.getPackagetaken())) && (Constant.isPackageContainFunction(userreg.getPackagetaken(), "talentPool"))) {
        saveTalentpool(user, org);
      }
    }
    catch (Exception e)
    {
      logger.info("Exception in saveUserReg", e);
    }
    return user;
  }
  
  public void upgradePackages(String fromPkg, String toPkg, long superUserKey)
    throws Exception
  {
    UserRegData userreg = getUserRegDataById(superUserKey);
    if ((StringUtils.isNullOrEmpty(fromPkg)) || (StringUtils.isNullOrEmpty(toPkg)) || (!fromPkg.equals(toPkg)))
    {
      if ((!StringUtils.isNullOrEmpty(toPkg)) && (Constant.isPackageContainFunction(toPkg, "screenSetting")))
      {
        List<String> screenCodeList = new ArrayList();
        screenCodeList.add("APPLICANT_SCREEN_AGENCY");
        screenCodeList.add("APPLICANT_SCREEN_REFERRAL");
        screenCodeList.add("APPLICANT_SCREEN_TALENTPOOL");
        screenCodeList.add("APPLICANT_SCREEN_EXTERNAL");
        screenCodeList.add("APPLICANT_SCREEN_TALENTPOOL_EXTERNAL");
        screenCodeList.add("APPLICANT_SCREEN_PROFILE");
        
        screenCodeList.add("APPLICANT_SCREEN_AGENCY");
        screenCodeList.add("APPLICANT_SCREEN_REFERRAL");
        screenCodeList.add("APPLICANT_SCREEN_TALENTPOOL");
        screenCodeList.add("APPLICANT_SCREEN_EXTERNAL");
        screenCodeList.add("APPLICANT_SCREEN_TALENTPOOL_EXTERNAL");
        screenCodeList.add("APPLICANT_SCREEN_PROFILE");
        

        screenCodeList.add("APPLICANT_SCREEN_WORK_EXP");
        screenCodeList.add("APPLICANT_SCREEN_EDUCATION");
        screenCodeList.add("APPLICANT_SCREEN_WORK_EXP_REFERRAL");
        screenCodeList.add("APPLICANT_SCREEN_WORK_EXP_AGENCY");
        screenCodeList.add("APPLICANT_SCREEN_WORK_EXP_TALENTPOOL");
        screenCodeList.add("APPLICANT_SCREEN_WORK_EXP_EXTERNAL");
        screenCodeList.add("APPLICANT_SCREEN_WORK_EXP_TALENTPOOL_EXTERNAL");
        
        screenCodeList.add("APPLICANT_SCREEN_WORK_EXP");
        screenCodeList.add("APPLICANT_SCREEN_WORK_EXP_PROFILE");
        screenCodeList.add("APPLICANT_SCREEN_WORK_EXP_REFERRAL");
        screenCodeList.add("APPLICANT_SCREEN_WORK_EXP_AGENCY");
        screenCodeList.add("APPLICANT_SCREEN_WORK_EXP_TALENTPOOL");
        screenCodeList.add("APPLICANT_SCREEN_WORK_EXP_EXTERNAL");
        screenCodeList.add("APPLICANT_SCREEN_WORK_EXP_TALENTPOOL_EXTERNAL");
        


        screenCodeList.add("APPLICANT_SCREEN_EDUCATION");
        screenCodeList.add("APPLICANT_SCREEN_EDUCATION_REFERRAL");
        screenCodeList.add("APPLICANT_SCREEN_EDUCATION_AGENCY");
        screenCodeList.add("APPLICANT_SCREEN_EDUCATION_TALENTPOOL");
        screenCodeList.add("APPLICANT_SCREEN_EDUCATION_EXTERNAL");
        screenCodeList.add("APPLICANT_SCREEN_EDUCATION_TALENTPOOL_EXTERNAL");
        screenCodeList.add("APPLICANT_SCREEN_EDUCATION_PROFILE");
        

        copyScreenFieldsToSuperUser(screenCodeList, userreg.getUserRegId());
      }
      if ((!StringUtils.isNullOrEmpty(userreg.getPackagetaken())) && (Constant.isPackageContainFunction(userreg.getPackagetaken(), "talentPool")))
      {
        User user = getUserByEmailId(userreg.getEmailId());
        if (user == null) {
          user = getRootUserBySuperUserKey(superUserKey);
        }
        saveTalentpool(user, user.getOrganization());
      }
    }
  }
  
  public TalentPool saveTalentpool(User user, Organization org)
    throws Exception
  {
    TalentPool talent = new TalentPool();
    talent.setTalentPoolName("Talent pool");
    talent.setTalentPoolDesc("Talent pool");
    talent.setTalentPoolemail(user.getEmailId());
    
    talent.setIsGroup("N");
    
    talent.setStatus("A");
    talent.setOrganization(org);
    talent.setAssignedtoid(user.getUserId());
    talent.setAssigntouserName(user.getFirstName() + " " + user.getLastName());
    talent.setSuper_user_key(user.getSuper_user_key());
    talent.setCreatedDate(new Date());
    talent.setCreatedBy(user.getUserName());
    return this.userdao.saveTalentpool(talent);
  }
  
  public User getRootUserBySuperUserKey(long super_user_key)
  {
    return this.userdao.getRootUserBySuperUserKey(super_user_key);
  }
  
  public UserRegData updateRegUserData(UserRegData userreg, String oldpkg)
    throws Exception
  {
    this.userdao.updateRegUserData(userreg);
    
    upgradePackages(oldpkg, userreg.getPackagetaken(), userreg.getUserRegId());
    
    return userreg;
  }
  
  public List getUserRegDataList()
  {
    return this.userdao.getUserRegDataList();
  }
  
  public void copyJobTitles(long superuserkey)
    throws Exception
  {
    this.userdao.copyJobTitles(superuserkey);
  }
  
  public void copyJobCategory(long superuserkey)
    throws Exception
  {
    this.userdao.copyJobCategory(superuserkey);
  }
  
  public void copyCategoryLOV(long superuserkey)
    throws Exception
  {
    this.userdao.copyCategoryLOV(superuserkey);
  }
  
  public void copyJobType(long superuserkey)
    throws Exception
  {
    this.userdao.copyJobType(superuserkey);
  }
  
  public void copyWorkShift(long superuserkey)
    throws Exception
  {
    this.userdao.copyWorkShift(superuserkey);
  }
  
  public void copyVeteranDisability(long superuserkey)
    throws Exception
  {
    this.userdao.copyVeteranDisability(superuserkey);
  }
  
  private void copyEthicityToSuperUser(long superuserkey)
    throws Exception
  {
    this.userdao.copyEthicityToSuperUser(superuserkey);
  }
  
  private void copyNotificationSettings(long superuserkey)
    throws Exception
  {
    this.userdao.copyNotificationSettings(superuserkey);
  }
  
  private void copyRolesToSuperUser(long superuserkey)
    throws Exception
  {
    this.userdao.copyRolesToSuperUser(superuserkey);
  }
  
  public Role getRoleByRoleCode(long superuserkey, String rolecode)
  {
    return BOFactory.getLovBO().getRoleByRoleCode(superuserkey, rolecode);
  }
  
  private void copyScreenFieldsToSuperUser(long superuserkey)
    throws Exception
  {
    this.userdao.copyScreenFieldsToSuperUser(superuserkey);
  }
  
  private void copyScreenFieldsToSuperUser(List<String> screenCodeList, long superuserkey)
    throws Exception
  {
    this.userdao.copyScreenFieldsToSuperUser(screenCodeList, superuserkey);
  }
  
  public UserRegData getUserRegDataById(long id)
  {
    return this.userdao.getUserRegDataById(id);
  }
  
  public boolean isPackageExpired(long super_user_key)
  {
    boolean isexpired = false;
    UserRegData userreg = getUserRegDataById(super_user_key);
    if ((userreg.getPackagetaken() != null) && (userreg.getPackagetaken().equals("BASIC-FREE"))) {
      return false;
    }
    Date dt = new Date();
    if ((userreg.getExpireDate() != null) && (userreg.getExpireDate().compareTo(dt) < 0)) {
      isexpired = true;
    }
    return false;
  }
  
  public boolean isPackageExpiredSoon(long super_user_key)
  {
    boolean isexpired = false;
    UserRegData userreg = getUserRegDataById(super_user_key);
    if ((userreg.getPackagetaken() != null) && (userreg.getPackagetaken().equals("BASIC-FREE"))) {
      return false;
    }
    Date dt = new Date();
    dt.setDate(dt.getDate() + 4);
    if ((userreg.getExpireDate() != null) && (userreg.getExpireDate().compareTo(dt) < 0)) {
      isexpired = true;
    }
    return isexpired;
  }
  
  public long addUser(User authUser, User user)
    throws WSException
  {
    if (StringUtils.isNullOrEmpty(user.getEmployeecode())) {
      throw new WSException("U100", "Employee code is required.");
    }
    if (StringUtils.isNullOrEmpty(user.getFirstName())) {
      throw new WSException("U101", "First name is required.");
    }
    if (StringUtils.isNullOrEmpty(user.getLastName())) {
      throw new WSException("U102", "Last name is required.");
    }
    if (StringUtils.isNullOrEmpty(user.getEmailId())) {
      throw new WSException("U103", "Email id is required.");
    }
    if (StringUtils.isNullOrEmpty(user.getLocale().getLocaleCode())) {
      throw new WSException("U104", "Locale code is required.");
    }
    if (StringUtils.isNullOrEmpty(user.getTimezone().getTimezoneCode())) {
      throw new WSException("U105", "Timezone code is required.");
    }
    if (StringUtils.isNullOrEmpty(user.getOrganization().getOrgCode())) {
      throw new WSException("U106", "Organization code is required.");
    }
    if (StringUtils.isNullOrEmpty(user.getDepartment().getDepartmentCode())) {
      throw new WSException("U107", "Department code is required.");
    }
    if (StringUtils.isNullOrEmpty(user.getNationality().getCountryName())) {
      throw new WSException("U112", "Nationality is required.");
    }
    if (StringUtils.isNullOrEmpty(user.getRole().getRoleCode())) {
      throw new WSException("U109", "Role code is required.");
    }
    if (!StringUtils.isNullOrEmpty(user.getOrganization().getOrgCode()))
    {
      Organization organization = BOFactory.getOrganizationBO().getOrganizationByCode(String.valueOf(user.getOrganization().getOrgCode()));
      if (organization == null) {
        throw new WSException("U113", "Organization code is incorrect.");
      }
      user.setOrganization(organization);
    }
    if (!StringUtils.isNullOrEmpty(user.getDepartment().getDepartmentCode()))
    {
      Department dept = LovOpsDAO.getDepartmentDetailsByCode(user.getDepartment().getDepartmentCode(), user.getOrganization().getOrgId());
      if (dept == null) {
        throw new WSException("U114", "Department code is incorrect.");
      }
      user.setDepartment(dept);
    }
    if ((user.getProjectcode() != null) && (!StringUtils.isNullOrEmpty(user.getProjectcode().getProjCode())))
    {
      ProjectCodes projcode = LovOpsDAO.getProjectCodeByProjectCodeAndDeptIdAndOrdId(user.getProjectcode().getProjCode(), user.getDepartment().getDepartmentId(), user.getOrganization().getOrgId());
      if (projcode == null) {
        throw new WSException("U115", "Project Code code is incorrect.");
      }
      user.setProjectcode(projcode);
    }
    if ((user.getDesignation() != null) && (!StringUtils.isNullOrEmpty(user.getDesignation().getDesignationCode())))
    {
      Designations designation = BOFactory.getLovBO().getDesignationByCode(user.getDesignation().getDesignationCode());
      if (designation == null) {
        throw new WSException("U116", "Designation code is incorrect.");
      }
      user.setDesignation(designation);
    }
    if (!StringUtils.isNullOrEmpty(user.getRole().getRoleCode()))
    {
      Role role = LovOpsDAO.getRoleByCode(user.getRole().getRoleCode(), authUser.getSuper_user_key());
      if (role == null) {
        throw new WSException("U119", "Role code is incorrect.");
      }
      user.setRole(role);
    }
    if (!StringUtils.isNullOrEmpty(user.getNationality().getCountryName()))
    {
      Country ct = BOFactory.getLovBO().getCountryByName(user.getNationality().getCountryName());
      if (ct == null) {
        throw new WSException("U117", "Nationality  is incorrect.");
      }
      user.setNationality(ct);
    }
    if (!StringUtils.isNullOrEmpty(user.getLocale().getLocaleCode()))
    {
      Locale locale = BOFactory.getLovBO().getLocaleByCode(user.getLocale().getLocaleCode());
      if (locale == null) {
        throw new WSException("U110", "Locale code  is incorrect.");
      }
      user.setLocale(locale);
    }
    if (!StringUtils.isNullOrEmpty(user.getTimezone().getTimezoneCode()))
    {
      Timezone timezone = BOFactory.getLovBO().getTimeZoneByCode(user.getTimezone().getTimezoneCode());
      if (timezone == null) {
        throw new WSException("U111", "Timezone code  is incorrect.");
      }
      user.setTimezone(timezone);
    }
    if (!PermissionChecker.isPermissionApplied("USER_CRUD", authUser)) {
      throw new WSException("1002", "No Permission to do this operation.");
    }
    if (!StringUtils.isNullOrEmpty(user.getUserName()))
    {
      String username = UserDAO.isUserNameExist(user.getUserName());
      if (username != null) {
        throw new WSException("1006", "UserName already exist.");
      }
    }
    String employeecode = UserDAO.isEmployeeCodeExist(user.getEmployeecode(), authUser.getSuper_user_key());
    if (employeecode != null) {
      throw new WSException("1005", "Employee code already exist.");
    }
    String emailid = UserDAO.isEmailIdExist(user.getEmailId());
    if (emailid != null) {
      throw new WSException("1004", "Email id already exist.");
    }
    user.setCreatedBy(authUser.getUserName());
    user.setCreatedDate(new Date());
    user.setUpdatedBy(null);
    user.setUpdatedDate(null);
    user.setType("Employee");
    user.setSuper_user_key(authUser.getSuper_user_key());
    if ((!StringUtils.isNullOrEmpty(user.getUserName())) && (StringUtils.isNullOrEmpty(user.getPassword()))) {
      user.setPassword(EncryptDecrypt.encrypt(PasswordGenerator.getPassword(8)));
    }
    user.setStatus("A");
    try
    {
      user = addUser(user);
      if ((!StringUtils.isNullOrEmpty(user.getUserName())) && 
        (!StringUtils.isNullOrEmpty(Constant.getValue("bulk.user.upload.user.creation.email.send"))) && (Constant.getValue("bulk.user.upload.user.creation.email.send").equalsIgnoreCase("yes"))) {
        assignUserNamePasswordEmail(user, authUser);
      }
    }
    catch (CommonException e)
    {
      throw new WSException(e.getErrorCode(), e.getErrorDescription());
    }
    return user.getUserId();
  }
  
  public CreateUserForm convertToUser(CreateUserForm userform, String applicantId, String uuid, User user1)
  {
    BOFactory.getLovBO().createUserLovs(userform, user1);
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    JobRequisition jobreq = BOFactory.getJobRequistionBO().getJobRequision(String.valueOf(applicant.getReqId()));
    userform.setApplicantId(new Long(applicantId).longValue());
    userform.setFirstName(applicant.getFullName());
    

    userform.setEmailId(applicant.getEmail());
    userform.setPhoneHome(applicant.getPhone());
    
    userform.setCreatedBy(user1.getCreatedBy());
    userform.setCreatedDate(user1.getCreatedDate());
    userform.setUpdatedBy(user1.getUpdatedBy());
    userform.setUpdatedDate(user1.getUpdatedDate());
    
    userform.setAddress(applicant.getOtherdetails().getAddress());
    
    userform.setLocaleId(user1.getLocale().getLocaleId());
    userform.setTimezoneId(user1.getTimezone().getTimezoneId());
    

    userform.setOrgId(jobreq.getOrganization().getOrgId());
    if (jobreq.getDepartment() != null) {
      userform.setDepartmentId(jobreq.getDepartment().getDepartmentId());
    }
    if (jobreq.getProjectcode() != null) {
      userform.setProjectcodeId(jobreq.getProjectcode().getProjectId());
    }
    if (applicant.getCountry() != null) {
      userform.countryId = applicant.getCountry().getCountryId();
    } else if ((jobreq.getOrganization().getLocation() != null) && 
      (jobreq.getOrganization().getLocation().getCountry() != null)) {
      userform.countryId = jobreq.getOrganization().getLocation().getCountry().getCountryId();
    }
    if (applicant.getState() != null) {
      userform.stateId = applicant.getState().getStateId();
    } else if ((jobreq.getOrganization().getLocation() != null) && 
      (jobreq.getOrganization().getLocation().getState() != null)) {
      userform.stateId = jobreq.getOrganization().getLocation().getState().getStateId();
    }
    if (StringUtils.isNullOrEmpty(applicant.getCity())) {
      userform.city = applicant.getCity();
    } else if (jobreq.getOrganization().getLocation() != null) {
      userform.city = jobreq.getOrganization().getLocation().getCity();
    }
    if (applicant.getDateofbirth() != null)
    {
      String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
      userform.dateofbirth = DateUtil.convertDateToStringDate(applicant.getDateofbirth(), datepattern);
    }
    if (applicant.getOtherdetails().getNationality() != null) {
      userform.setNationalityId(applicant.getOtherdetails().getNationality().getCountryId());
    }
    return userform;
  }
  
  public User getUserCreatedFromApplicant(long applicantId)
  {
    return this.userdao.getUserCreatedFromApplicant(applicantId);
  }
  
  public List getWidgets(long userId)
  {
    return this.userdao.getWidgets(userId);
  }
  
  public Map getWidgetsMap(long userId)
  {
    List widgetList = this.userdao.getWidgets(userId);
    
    Map widgetmap = new HashMap();
    for (int i = 0; i < widgetList.size(); i++)
    {
      DashBoardConfig dc = (DashBoardConfig)widgetList.get(i);
      widgetmap.put(dc.getWidgetCode(), dc.getStatus());
    }
    return widgetmap;
  }
  
  public void assignUserNamePasswordEmail(User toUser, User user1)
  {
    String[] tonew = { toUser.getEmailId() };
    String[] ccnew = { user1.getEmailId() };
    String from = "\"" + user1.getFirstName() + " " + user1.getLastName() + "\"" + " " + "<" + user1.getEmailId() + ">";
    
    EmailTask emailtasknew = new EmailTask(from, tonew, ccnew, null, from, "dummysubject", null, "dummybody", null, 0, null);
    emailtasknew.setFunctionType(Common.USER_CREATION_EMAIL_COMPONENT);
    emailtasknew.setUser(user1);
    emailtasknew.setNewCreatedUser(toUser);
    EmailTaskManager.sendEmail(emailtasknew);
  }
  
  public User updateUserData(User usr, boolean isDashboardUpdate)
  {
    try
    {
      usr = this.userdao.updateUserData(usr);
      if (isDashboardUpdate) {
        setupDashBoard(usr);
      }
    }
    catch (Exception e)
    {
      logger.info("exception on updateUserData", e);
    }
    return usr;
  }
  
  public User addUser(User user)
    throws CommonException
  {
    try
    {
      user = this.userdao.saveUserData(user);
      




      setupDashBoard(user);
    }
    catch (Exception e)
    {
      throw new CommonException("99999999", "System error " + e.getMessage());
    }
    return user;
  }
  
  public void setupDashBoard(User user)
    throws Exception
  {
    logger.info("setupDashBoard" + user.getRole().getRoleId());
    Role rolenew = this.userdao.getRole(user.getRole().getRoleId());
    if (PermissionChecker.isPermissionApplied("HIRING_MANAGER_DASHBOARD", rolenew))
    {
      String strtemp = Constant.getValue("HIRING_MANAGER_DASHBOARD");
      String[] columns = StringUtils.tokenize(strtemp, ",");
      if (columns != null)
      {
        List widgetList = new ArrayList();
        for (int i = 0; i < columns.length; i++)
        {
          DashBoardConfig dc = new DashBoardConfig();
          dc.setWidgetCode(columns[i]);
          dc.setStatus("A");
          dc.setUserId(user.getUserId());
          
          widgetList.add(dc);
        }
        this.userdao.saveDashBoardWidgetList(widgetList, user.getUserId());
      }
    }
    else if (PermissionChecker.isPermissionApplied("RECRUITER_DASHBOARD", rolenew))
    {
      String strtemp = Constant.getValue("RECRUITER_DASHBOARD");
      String[] columns = StringUtils.tokenize(strtemp, ",");
      if (columns != null)
      {
        List widgetList = new ArrayList();
        for (int i = 0; i < columns.length; i++)
        {
          DashBoardConfig dc = new DashBoardConfig();
          dc.setWidgetCode(columns[i]);
          dc.setStatus("A");
          dc.setUserId(user.getUserId());
          
          widgetList.add(dc);
        }
        this.userdao.saveDashBoardWidgetList(widgetList, user.getUserId());
      }
    }
    else if (PermissionChecker.isPermissionApplied("REVIEWER_DASHBOARD", rolenew))
    {
      String strtemp = Constant.getValue("REVIEWER_DASHBOARD");
      String[] columns = StringUtils.tokenize(strtemp, ",");
      if (columns != null)
      {
        List widgetList = new ArrayList();
        for (int i = 0; i < columns.length; i++)
        {
          DashBoardConfig dc = new DashBoardConfig();
          dc.setWidgetCode(columns[i]);
          dc.setStatus("A");
          dc.setUserId(user.getUserId());
          
          widgetList.add(dc);
        }
        this.userdao.saveDashBoardWidgetList(widgetList, user.getUserId());
      }
    }
    else if (PermissionChecker.isPermissionApplied("ADMIN_DASHBOARD", rolenew))
    {
      String strtemp = Constant.getValue("ADMIN_DASHBOARD");
      String[] columns = StringUtils.tokenize(strtemp, ",");
      if (columns != null)
      {
        List widgetList = new ArrayList();
        for (int i = 0; i < columns.length; i++)
        {
          DashBoardConfig dc = new DashBoardConfig();
          dc.setWidgetCode(columns[i]);
          dc.setStatus("A");
          dc.setUserId(user.getUserId());
          
          widgetList.add(dc);
        }
        this.userdao.saveDashBoardWidgetList(widgetList, user.getUserId());
      }
    }
  }
  
  public void setupDashBoard(User user, HttpServletRequest request)
    throws Exception
  {
    Role role = LovOpsDAO.getRole(String.valueOf(user.getRole().getRoleId()));
    if (PermissionChecker.isPermissionApplied("HIRING_MANAGER_DASHBOARD", role))
    {
      String strtemp = Constant.getValue("HIRING_MANAGER_DASHBOARD");
      String[] columns = StringUtils.tokenize(strtemp, ",");
      if (columns != null)
      {
        List widgetList = new ArrayList();
        for (int i = 0; i < columns.length; i++)
        {
          DashBoardConfig dc = new DashBoardConfig();
          dc.setWidgetCode(columns[i]);
          
          String value = request.getParameter(columns[i]);
          if ((!StringUtils.isNullOrEmpty(value)) && (value.equals("on"))) {
            dc.setStatus("A");
          } else {
            dc.setStatus("I");
          }
          if (columns[i].equals("MY_ACTIVE_REQ")) {
            dc.setStatus("A");
          }
          dc.setUserId(user.getUserId());
          
          widgetList.add(dc);
        }
        this.userdao.saveDashBoardWidgetList(widgetList, user.getUserId());
      }
    }
    else if (PermissionChecker.isPermissionApplied("RECRUITER_DASHBOARD", role))
    {
      String strtemp = Constant.getValue("RECRUITER_DASHBOARD");
      String[] columns = StringUtils.tokenize(strtemp, ",");
      if (columns != null)
      {
        List widgetList = new ArrayList();
        for (int i = 0; i < columns.length; i++)
        {
          DashBoardConfig dc = new DashBoardConfig();
          dc.setWidgetCode(columns[i]);
          String value = request.getParameter(columns[i]);
          if ((!StringUtils.isNullOrEmpty(value)) && (value.equals("on"))) {
            dc.setStatus("A");
          } else {
            dc.setStatus("I");
          }
          dc.setUserId(user.getUserId());
          if (columns[i].equals("MY_ACTIVE_REQ")) {
            dc.setStatus("A");
          }
          widgetList.add(dc);
        }
        this.userdao.saveDashBoardWidgetList(widgetList, user.getUserId());
      }
    }
    else if (PermissionChecker.isPermissionApplied("REVIEWER_DASHBOARD", role))
    {
      String strtemp = Constant.getValue("REVIEWER_DASHBOARD");
      String[] columns = StringUtils.tokenize(strtemp, ",");
      if (columns != null)
      {
        List widgetList = new ArrayList();
        for (int i = 0; i < columns.length; i++)
        {
          DashBoardConfig dc = new DashBoardConfig();
          dc.setWidgetCode(columns[i]);
          String value = request.getParameter(columns[i]);
          if ((!StringUtils.isNullOrEmpty(value)) && (value.equals("on"))) {
            dc.setStatus("A");
          } else {
            dc.setStatus("I");
          }
          dc.setUserId(user.getUserId());
          if (columns[i].equals("MY_ACTIVE_REQ")) {
            dc.setStatus("A");
          }
          widgetList.add(dc);
        }
        this.userdao.saveDashBoardWidgetList(widgetList, user.getUserId());
      }
    }
    else if (PermissionChecker.isPermissionApplied("ADMIN_DASHBOARD", role))
    {
      String strtemp = Constant.getValue("ADMIN_DASHBOARD");
      String[] columns = StringUtils.tokenize(strtemp, ",");
      if (columns != null)
      {
        List widgetList = new ArrayList();
        for (int i = 0; i < columns.length; i++)
        {
          DashBoardConfig dc = new DashBoardConfig();
          dc.setWidgetCode(columns[i]);
          String value = request.getParameter(columns[i]);
          if ((!StringUtils.isNullOrEmpty(value)) && (value.equals("on"))) {
            dc.setStatus("A");
          } else {
            dc.setStatus("I");
          }
          dc.setUserId(user.getUserId());
          if (columns[i].equals("MY_ACTIVE_REQ")) {
            dc.setStatus("A");
          }
          widgetList.add(dc);
        }
        this.userdao.saveDashBoardWidgetList(widgetList, user.getUserId());
      }
    }
  }
  
  public List getAllVendorList(User user)
  {
    return this.userdao.getAllVendorList(user);
  }
  
  public List<String> getJavaScriptTreeSubOrdinates(long userId, User user, String selectNodeUserId, long timeperiodId)
    throws Exception
  {
    boolean isUserIdInList = false;
    long selectNodeUserIdlong = 0L;
    if (!StringUtils.isNullOrEmpty(selectNodeUserId)) {
      selectNodeUserIdlong = new Long(selectNodeUserId).longValue();
    }
    List<String> lst = new ArrayList();
    List userIniList = getUserRepotingToUserAndGoals(userId, "I", timeperiodId);
    int nodeid = 0;
    StringBuffer strbfr = new StringBuffer();
    StringBuffer strbfrblockjs = new StringBuffer();
    strbfr.append("<script type=\"text/javascript\">");
    strbfr.append("\n");
    strbfr.append("d = new dTree('d');");
    strbfr.append("\n");
    

    String root = "d.add(0,-1,'" + Constant.getResourceStringValue("subordinate.goals", user.getLocale()) + "')";
    
    strbfr.append(root);
    strbfr.append("\n");
    String temp = "d.add(99999999,0,'" + Constant.getResourceStringValue("initiated.users.for", user.getLocale()) + "','','','','jsp/dtree/img/folder.gif');";
    nodeid++;
    strbfr.append(temp);
    for (int i = 0; i < userIniList.size(); i++)
    {
      User intuser = (User)userIniList.get(i);
      String name = intuser.getFirstName() + " " + intuser.getLastName();
      if (!StringUtils.isNullOrEmpty(name)) {
        name = name.replace("'", "\\'");
      }
      temp = "d.add(" + intuser.getUserId() + "," + 99999999 + "," + "'" + name + "'" + "," + "'" + "#" + "'" + ");";
      strbfr.append(temp);
      strbfr.append("\n");
      nodeid++;
      String url = "goal.do?method=viewinitiatedgoalsajax&userId=" + intuser.getUserId() + "&empName=" + name;
      String blcstr = "$('#sd" + nodeid + "').click(function() {  $.blockUI({ message: '<h1><img src=\"jsp/images/loading_circle.gif\" />" + Constant.getResourceStringValue("aquisition.applicant.Please_wait", user.getLocale()) + "...</h1>' }); retriveData('" + url + "') });" + "\n";
      strbfrblockjs.append(blcstr);
      if (selectNodeUserIdlong == intuser.getUserId()) {
        isUserIdInList = true;
      }
    }
    strbfr.append("\n");
    String temp1 = "d.add(99999998,0,'" + Constant.getResourceStringValue("send.for.review.goals.to.user", user.getLocale()) + "','','','','jsp/dtree/img/folder.gif');";
    strbfr.append(temp1);
    nodeid++;
    userIniList = getUserRepotingToUserAndGoals(userId, "R", timeperiodId);
    for (int i = 0; i < userIniList.size(); i++)
    {
      User intuser = (User)userIniList.get(i);
      String name = intuser.getFirstName() + " " + intuser.getLastName();
      if (!StringUtils.isNullOrEmpty(name)) {
        name = name.replace("'", "\\'");
      }
      temp = "d.add(" + intuser.getUserId() + "," + 99999998 + "," + "'" + name + "'" + "," + "'" + "#" + "'" + ");";
      strbfr.append(temp);
      strbfr.append("\n");
      
      nodeid++;
      String url = "goal.do?method=viewinitiatedgoalsajax&userId=" + intuser.getUserId() + "&empName=" + name;
      String blcstr = "$('#sd" + nodeid + "').click(function() {  $.blockUI({ message: '<h1><img src=\"jsp/images/loading_circle.gif\" />" + Constant.getResourceStringValue("aquisition.applicant.Please_wait", user.getLocale()) + "...</h1>' }); retriveData('" + url + "') });" + "\n";
      strbfrblockjs.append(blcstr);
      if (selectNodeUserIdlong == intuser.getUserId()) {
        isUserIdInList = true;
      }
    }
    strbfr.append("\n");
    String temp2 = "d.add(99999997,0,'" + Constant.getResourceStringValue("active.goals.to.user", user.getLocale()) + "','','','','jsp/dtree/img/folder.gif');";
    strbfr.append(temp2);
    nodeid++;
    userIniList = getUserRepotingToUserAndGoals(userId, "R", timeperiodId);
    for (int i = 0; i < userIniList.size(); i++)
    {
      User intuser = (User)userIniList.get(i);
      String name = intuser.getFirstName() + " " + intuser.getLastName();
      if (!StringUtils.isNullOrEmpty(name)) {
        name = name.replace("'", "\\'");
      }
      temp = "d.add(" + intuser.getUserId() + "," + 99999997 + "," + "'" + name + "'" + "," + "'" + "#" + "'" + ");";
      strbfr.append(temp);
      strbfr.append("\n");
      
      nodeid++;
      String url = "goal.do?method=viewinitiatedgoalsajax&userId=" + intuser.getUserId() + "&empName=" + name;
      String blcstr = "$('#sd" + nodeid + "').click(function() {  $.blockUI({ message: '<h1><img src=\"jsp/images/loading_circle.gif\" />" + Constant.getResourceStringValue("aquisition.applicant.Please_wait", user.getLocale()) + "...</h1>' }); retriveData('" + url + "') });" + "\n";
      strbfrblockjs.append(blcstr);
      if (selectNodeUserIdlong == intuser.getUserId()) {
        isUserIdInList = true;
      }
    }
    strbfr.append("document.write(d)");
    

    strbfr.append("</script>");
    lst.add(strbfr.toString());
    lst.add(strbfrblockjs.toString());
    lst.add(getJavaScriptTreeForSelectNodeSubOrdinates(selectNodeUserId, isUserIdInList));
    


    return lst;
  }
  
  public String getJavaScriptTreeForSelectNodeSubOrdinates(String userId, boolean isUserIdInList)
  {
    StringBuffer strbfr = new StringBuffer();
    if ((!StringUtils.isNullOrEmpty(userId)) && (isUserIdInList))
    {
      strbfr.append("<script type=\"text/javascript\">");
      strbfr.append("\n");
      strbfr.append("d.openTo(" + userId + ", true);");
      strbfr.append("\n");
      strbfr.append("</script>");
    }
    else
    {
      strbfr.append("<script type=\"text/javascript\">");
      strbfr.append("\n");
      strbfr.append("d.openTo(99999999, true);");
      strbfr.append("\n");
      strbfr.append("</script>");
    }
    return strbfr.toString();
  }
  
  public List<User> getUserRepotingToUserAndGoals(long userId, String status, long timePeriodId)
    throws Exception
  {
    return this.userdao.getUserRepotingToUserAndGoals(userId, status, timePeriodId);
  }
  
  public UserDAO getUserdao()
  {
    return this.userdao;
  }
  
  public void setUserdao(UserDAO userdao)
  {
    this.userdao = userdao;
  }
}
