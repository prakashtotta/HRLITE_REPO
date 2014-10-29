package com.form;

import com.bean.Country;
import com.bean.Department;
import com.bean.Designations;
import com.bean.Locale;
import com.bean.Organization;
import com.bean.ProjectCodes;
import com.bean.Role;
import com.bean.State;
import com.bean.Timezone;
import com.bean.User;
import com.util.DateUtil;
import com.util.StringUtils;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class CreateUserForm
  extends ActionForm
{
  private long userId;
  public String employeecode;
  private String firstName;
  private String middleName;
  private String lastName;
  private String emailId;
  private String mobileNo;
  private List localeList;
  private List timezoneList;
  private String phoneHome;
  private String phoneOffice;
  private List countryList;
  private List stateList;
  private String address;
  private String userName;
  private String password;
  private String cpassword;
  private String currentpassword;
  private List roleList;
  private String createdBy;
  private Date createdDate;
  private Date updatedDate;
  private String updatedBy;
  private String status;
  private long localeId;
  private long timezoneId;
  public String parentOrgName;
  private long departmentId;
  public String department;
  private long projectcodeId;
  public String projectcode;
  public long countryId;
  public long stateId;
  public String city;
  private long roleId;
  private List userList;
  public String start;
  public String range;
  public String results;
  public String readPreview;
  private String orgName;
  public long orgId;
  private List organizationList;
  private List projectcodeList;
  private String departmentName;
  private List departmentList;
  public String roleName;
  public String localeName;
  public String timezoneName;
  public String type;
  public String countryName;
  public String stateName;
  public Country country;
  public State state;
  public String statuscri;
  public ProjectCodes projectcodes;
  private FormFile profilePhoto;
  private long profilePhotoId;
  public String projCode;
  public int projectId;
  public String backurl;
  public String isUserGroupSearch;
  public String groupName;
  public String taxId;
  public String drivingLicenseId;
  public String drivingLicenseExpDate;
  public String dateofbirth;
  public String ssnNumber;
  private long nationalityId;
  private String nationalityName;
  private long designationId;
  public String designationName;
  private List designationList;
  private FormFile fileData;
  private List userEducationDetailsList;
  private List userSkillsDetailsList;
  private List userLanguagesDetailsList;
  private List userLicenseDetailsList;
  private long applicantId;
  
  public List getUserSkillsDetailsList()
  {
    return this.userSkillsDetailsList;
  }
  
  public void setUserSkillsDetailsList(List userSkillsDetailsList)
  {
    this.userSkillsDetailsList = userSkillsDetailsList;
  }
  
  public List getUserLanguagesDetailsList()
  {
    return this.userLanguagesDetailsList;
  }
  
  public void setUserLanguagesDetailsList(List userLanguagesDetailsList)
  {
    this.userLanguagesDetailsList = userLanguagesDetailsList;
  }
  
  public List getUserLicenseDetailsList()
  {
    return this.userLicenseDetailsList;
  }
  
  public void setUserLicenseDetailsList(List userLicenseDetailsList)
  {
    this.userLicenseDetailsList = userLicenseDetailsList;
  }
  
  public List getUserEducationDetailsList()
  {
    return this.userEducationDetailsList;
  }
  
  public void setUserEducationDetailsList(List userEducationDetailsList)
  {
    this.userEducationDetailsList = userEducationDetailsList;
  }
  
  public int getProjectId()
  {
    return this.projectId;
  }
  
  public void setProjectId(int projectId)
  {
    this.projectId = projectId;
  }
  
  public String getProjCode()
  {
    return this.projCode;
  }
  
  public void setProjCode(String projCode)
  {
    this.projCode = projCode;
  }
  
  public String getAddress()
  {
    return this.address;
  }
  
  public void setAddress(String address)
  {
    this.address = address;
  }
  
  public long getCountryId()
  {
    return this.countryId;
  }
  
  public void setCountryId(long countryId)
  {
    this.countryId = countryId;
  }
  
  public List getCountryList()
  {
    return this.countryList;
  }
  
  public void setCountryList(List countryList)
  {
    this.countryList = countryList;
  }
  
  public String getCreatedBy()
  {
    return this.createdBy;
  }
  
  public void setCreatedBy(String createdBy)
  {
    this.createdBy = createdBy;
  }
  
  public Date getCreatedDate()
  {
    return this.createdDate;
  }
  
  public void setCreatedDate(Date createdDate)
  {
    this.createdDate = createdDate;
  }
  
  public String getEmailId()
  {
    return this.emailId;
  }
  
  public void setEmailId(String emailId)
  {
    this.emailId = emailId;
  }
  
  public String getFirstName()
  {
    return this.firstName;
  }
  
  public void setFirstName(String firstName)
  {
    this.firstName = firstName;
  }
  
  public String getLastName()
  {
    return this.lastName;
  }
  
  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }
  
  public String getMiddleName()
  {
    return this.middleName;
  }
  
  public void setMiddleName(String middleName)
  {
    this.middleName = middleName;
  }
  
  public String getPassword()
  {
    return this.password;
  }
  
  public void setPassword(String password)
  {
    this.password = password;
  }
  
  public String getPhoneHome()
  {
    return this.phoneHome;
  }
  
  public void setPhoneHome(String phoneHome)
  {
    this.phoneHome = phoneHome;
  }
  
  public String getPhoneOffice()
  {
    return this.phoneOffice;
  }
  
  public void setPhoneOffice(String phoneOffice)
  {
    this.phoneOffice = phoneOffice;
  }
  
  public long getRoleId()
  {
    return this.roleId;
  }
  
  public void setRoleId(long roleId)
  {
    this.roleId = roleId;
  }
  
  public List getRoleList()
  {
    return this.roleList;
  }
  
  public void setRoleList(List roleList)
  {
    this.roleList = roleList;
  }
  
  public long getStateId()
  {
    return this.stateId;
  }
  
  public void setStateId(long stateId)
  {
    this.stateId = stateId;
  }
  
  public List getStateList()
  {
    return this.stateList;
  }
  
  public void setStateList(List stateList)
  {
    this.stateList = stateList;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public String getUpdatedBy()
  {
    return this.updatedBy;
  }
  
  public void setUpdatedBy(String updatedBy)
  {
    this.updatedBy = updatedBy;
  }
  
  public Date getUpdatedDate()
  {
    return this.updatedDate;
  }
  
  public void setUpdatedDate(Date updatedDate)
  {
    this.updatedDate = updatedDate;
  }
  
  public long getUserId()
  {
    return this.userId;
  }
  
  public void setUserId(long userId)
  {
    this.userId = userId;
  }
  
  public String getUserName()
  {
    return this.userName;
  }
  
  public void setUserName(String userName)
  {
    this.userName = userName;
  }
  
  public User toValue(User user, HttpServletRequest request)
    throws Exception
  {
    user.setEmployeecode(this.employeecode);
    user.setFirstName(this.firstName);
    user.setMiddleName(this.middleName);
    user.setLastName(this.lastName);
    user.setEmailId(this.emailId);
    user.setPhoneHome(this.phoneHome);
    user.setPhoneOffice(this.phoneOffice);
    user.setAddress(this.address);
    


    User user1 = (User)request.getSession().getAttribute("user_data");
    user.setCreatedBy(user1.getUserName());
    user.setCreatedDate(new Date());
    user.setUpdatedBy(null);
    user.setUpdatedDate(null);
    
    Locale lo = new Locale();
    lo.setLocaleId(this.localeId);
    user.setLocale(lo);
    
    Timezone tz = new Timezone();
    tz.setTimezoneId(this.timezoneId);
    user.setTimezone(tz);
    if (this.orgId != 0L)
    {
      Organization organization = new Organization();
      organization.setOrgId(this.orgId);
      user.setOrganization(organization);
    }
    if (this.departmentId != 0L)
    {
      Department dept = new Department();
      dept.setDepartmentId(this.departmentId);
      user.setDepartment(dept);
    }
    if (this.projectId != 0)
    {
      ProjectCodes pjcode = new ProjectCodes();
      pjcode.setProjectId(this.projectId);
      user.setProjectcode(pjcode);
    }
    else
    {
      user.setProjectcode(null);
    }
    if (this.countryId != 0L)
    {
      Country country = new Country();
      country.setCountryId(this.countryId);
      user.setCountry(country);
    }
    if (this.stateId != 0L)
    {
      State state = new State();
      state.setStateId(this.stateId);
      user.setState(state);
    }
    user.setCity(this.city);
    
    user.setTaxId(this.taxId);
    user.setDrivingLicenseId(this.drivingLicenseId);
    if (this.nationalityId != 0L)
    {
      Country nationality = new Country();
      nationality.setCountryId(this.nationalityId);
      user.setNationality(nationality);
    }
    if (this.designationId != 0L)
    {
      Designations designation = new Designations();
      designation.setDesignationId(this.designationId);
      user.setDesignation(designation);
    }
    if (!StringUtils.isNullOrEmpty(this.dateofbirth))
    {
      String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
      
      String converteddate = DateUtil.convertFromTimezoneToTimezoneDate(this.dateofbirth, datepattern, user1.getTimezone().getTimezoneCode(), TimeZone.getDefault().getID());
      
      Calendar cal = DateUtil.convertStringDateToCalendar(converteddate, datepattern);
      
      user.setDateofbirth(cal.getTime());
    }
    if (!StringUtils.isNullOrEmpty(this.drivingLicenseExpDate))
    {
      String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
      
      String converteddate = DateUtil.convertFromTimezoneToTimezoneDate(this.drivingLicenseExpDate, datepattern, user1.getTimezone().getTimezoneCode(), TimeZone.getDefault().getID());
      
      Calendar cal = DateUtil.convertStringDateToCalendar(converteddate, datepattern);
      
      user.setDrivingLicenseExpDate(cal.getTime());
    }
    user.setSsnNumber(this.ssnNumber);
    Role role = new Role();
    role.setRoleId(this.roleId);
    user.setRole(role);
    

    user.setMobileNo(getMobileNo());
    user.setApplicantId(this.applicantId);
    return user;
  }
  
  public void fromValue(User user, HttpServletRequest request)
    throws Exception
  {
    User user1 = (User)request.getSession().getAttribute("user_data");
    this.userId = user.getUserId();
    this.employeecode = user.getEmployeecode();
    setFirstName(user.getFirstName());
    setMiddleName(user.getMiddleName());
    setLastName(user.getLastName());
    setEmailId(user.getEmailId());
    setPhoneHome(user.getPhoneHome());
    setPhoneOffice(user.getPhoneOffice());
    setAddress(user.getAddress());
    setUserName(user.getUserName());
    


    setCreatedBy(user.getCreatedBy());
    setCreatedDate(user.getCreatedDate());
    setUpdatedBy(user.getUpdatedBy());
    setUpdatedDate(user.getUpdatedDate());
    
    this.localeId = user.getLocale().getLocaleId();
    this.timezoneId = user.getTimezone().getTimezoneId();
    if (user.getOrganization() != null)
    {
      setOrgId(user.getOrganization().getOrgId());
      
      setParentOrgName(user.getOrganization().getOrgName());
    }
    if (user.getDepartment() != null)
    {
      setDepartment(user.getDepartment().getDepartmentName());
      setDepartmentId(user.getDepartment().getDepartmentId());
    }
    if (user.getProjectcode() != null)
    {
      setProjectcode(user.getProjectcode().getProjCode());
      setProjectcodeId(user.getProjectcode().getProjectId());
    }
    if (user.getCountry() != null)
    {
      this.countryId = user.getCountry().getCountryId();
      setCountryName(user.getCountry().getCountryName());
    }
    if (user.getState() != null)
    {
      this.stateId = user.getState().getStateId();
      setStateName(user.getState().getStateName());
    }
    this.city = user.getCity();
    if (user.getRole() != null)
    {
      this.roleId = user.getRole().getRoleId();
      setRoleName(user.getRole().getRoleName());
    }
    this.password = user.getPassword();
    this.cpassword = user.getPassword();
    
    setStatus(user.getStatus());
    
    this.type = user.getType();
    

    setLocaleName(user.getLocale().getLocaleName());
    setTimezoneName(user.getTimezone().getTimezoneName());
    

    this.mobileNo = user.getMobileNo();
    this.profilePhotoId = user.getProfilePhotoId();
    if (user.getDateofbirth() != null)
    {
      String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
      this.dateofbirth = DateUtil.convertDateToStringDate(user.getDateofbirth(), datepattern);
    }
    if (user.getDrivingLicenseExpDate() != null)
    {
      String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
      this.drivingLicenseExpDate = DateUtil.convertDateToStringDate(user.getDrivingLicenseExpDate(), datepattern);
    }
    setTaxId(user.taxId);
    setDrivingLicenseId(user.drivingLicenseId);
    if (user.getNationality() != null)
    {
      this.nationalityId = user.getNationality().getCountryId();
      this.nationalityName = user.getNationality().getCountryName();
    }
    if (user.getDesignation() != null)
    {
      this.designationId = user.getDesignation().getDesignationId();
      this.designationName = user.getDesignation().getDesignationName();
    }
    setSsnNumber(user.ssnNumber);
    this.applicantId = user.getApplicantId();
  }
  
  public List getUserList()
  {
    return this.userList;
  }
  
  public void setUserList(List userList)
  {
    this.userList = userList;
  }
  
  public String getStart()
  {
    return this.start;
  }
  
  public void setStart(String start)
  {
    this.start = start;
  }
  
  public String getRange()
  {
    return this.range;
  }
  
  public void setRange(String range)
  {
    this.range = range;
  }
  
  public String getResults()
  {
    return this.results;
  }
  
  public void setResults(String results)
  {
    this.results = results;
  }
  
  public String getDepartment()
  {
    return this.department;
  }
  
  public void setDepartment(String department)
  {
    this.department = department;
  }
  
  public String getProjectcode()
  {
    return this.projectcode;
  }
  
  public void setProjectcode(String projectcode)
  {
    this.projectcode = projectcode;
  }
  
  public String getCity()
  {
    return this.city;
  }
  
  public void setCity(String city)
  {
    this.city = city;
  }
  
  public String getCpassword()
  {
    return this.cpassword;
  }
  
  public void setCpassword(String cpassword)
  {
    this.cpassword = cpassword;
  }
  
  public String getParentOrgName()
  {
    return this.parentOrgName;
  }
  
  public void setParentOrgName(String parentOrgName)
  {
    this.parentOrgName = parentOrgName;
  }
  
  public long getDepartmentId()
  {
    return this.departmentId;
  }
  
  public void setDepartmentId(long departmentId)
  {
    this.departmentId = departmentId;
  }
  
  public List getLocaleList()
  {
    return this.localeList;
  }
  
  public void setLocaleList(List localeList)
  {
    this.localeList = localeList;
  }
  
  public long getLocaleId()
  {
    return this.localeId;
  }
  
  public void setLocaleId(long localeId)
  {
    this.localeId = localeId;
  }
  
  public List getTimezoneList()
  {
    return this.timezoneList;
  }
  
  public void setTimezoneList(List timezoneList)
  {
    this.timezoneList = timezoneList;
  }
  
  public long getTimezoneId()
  {
    return this.timezoneId;
  }
  
  public void setTimezoneId(long timezoneId)
  {
    this.timezoneId = timezoneId;
  }
  
  public String getReadPreview()
  {
    return this.readPreview;
  }
  
  public void setReadPreview(String readPreview)
  {
    this.readPreview = readPreview;
  }
  
  public String getDepartmentName()
  {
    return this.departmentName;
  }
  
  public void setDepartmentName(String departmentName)
  {
    this.departmentName = departmentName;
  }
  
  public List getDepartmentList()
  {
    return this.departmentList;
  }
  
  public void setDepartmentList(List departmentList)
  {
    this.departmentList = departmentList;
  }
  
  public String getOrgName()
  {
    return this.orgName;
  }
  
  public void setOrgName(String orgName)
  {
    this.orgName = orgName;
  }
  
  public long getOrgId()
  {
    return this.orgId;
  }
  
  public void setOrgId(long orgId)
  {
    this.orgId = orgId;
  }
  
  public List getOrganizationList()
  {
    return this.organizationList;
  }
  
  public void setOrganizationList(List organizationList)
  {
    this.organizationList = organizationList;
  }
  
  public String getStatuscri()
  {
    return this.statuscri;
  }
  
  public void setStatuscri(String statuscri)
  {
    this.statuscri = statuscri;
  }
  
  public String getRoleName()
  {
    return this.roleName;
  }
  
  public void setRoleName(String roleName)
  {
    this.roleName = roleName;
  }
  
  public String getLocaleName()
  {
    return this.localeName;
  }
  
  public void setLocaleName(String localeName)
  {
    this.localeName = localeName;
  }
  
  public String getTimezoneName()
  {
    return this.timezoneName;
  }
  
  public void setTimezoneName(String timezoneName)
  {
    this.timezoneName = timezoneName;
  }
  
  public String getType()
  {
    return this.type;
  }
  
  public void setType(String type)
  {
    this.type = type;
  }
  
  public String getCountryName()
  {
    return this.countryName;
  }
  
  public void setCountryName(String countryName)
  {
    this.countryName = countryName;
  }
  
  public String getStateName()
  {
    return this.stateName;
  }
  
  public void setStateName(String stateName)
  {
    this.stateName = stateName;
  }
  
  public String getCurrentpassword()
  {
    return this.currentpassword;
  }
  
  public void setCurrentpassword(String currentpassword)
  {
    this.currentpassword = currentpassword;
  }
  
  public Country getCountry()
  {
    return this.country;
  }
  
  public void setCountry(Country country)
  {
    this.country = country;
    if (country != null) {
      this.countryName = country.getCountryName();
    }
  }
  
  public State getState()
  {
    return this.state;
  }
  
  public void setState(State state)
  {
    this.state = state;
    if (state != null) {
      this.stateName = state.getStateName();
    }
  }
  
  public List getProjectcodeList()
  {
    return this.projectcodeList;
  }
  
  public void setProjectcodeList(List projectcodeList)
  {
    this.projectcodeList = projectcodeList;
  }
  
  public ProjectCodes getProjectcodes()
  {
    return this.projectcodes;
  }
  
  public void setProjectcodes(ProjectCodes projectcodes)
  {
    this.projectcodes = projectcodes;
    if (projectcodes != null) {
      this.projectcode = projectcodes.getProjCode();
    }
  }
  
  public FormFile getProfilePhoto()
  {
    return this.profilePhoto;
  }
  
  public void setProfilePhoto(FormFile profilePhoto)
  {
    this.profilePhoto = profilePhoto;
  }
  
  public long getProfilePhotoId()
  {
    return this.profilePhotoId;
  }
  
  public void setProfilePhotoId(long profilePhotoId)
  {
    this.profilePhotoId = profilePhotoId;
  }
  
  public String getMobileNo()
  {
    return this.mobileNo;
  }
  
  public void setMobileNo(String mobileNo)
  {
    this.mobileNo = mobileNo;
  }
  
  public String getEmployeecode()
  {
    return this.employeecode;
  }
  
  public void setEmployeecode(String employeecode)
  {
    this.employeecode = employeecode;
  }
  
  public long getProjectcodeId()
  {
    return this.projectcodeId;
  }
  
  public void setProjectcodeId(long projectcodeId)
  {
    this.projectcodeId = projectcodeId;
  }
  
  public String getBackurl()
  {
    return this.backurl;
  }
  
  public void setBackurl(String backurl)
  {
    this.backurl = backurl;
  }
  
  public String getGroupName()
  {
    return this.groupName;
  }
  
  public void setGroupName(String groupName)
  {
    this.groupName = groupName;
  }
  
  public String getIsUserGroupSearch()
  {
    return this.isUserGroupSearch;
  }
  
  public void setIsUserGroupSearch(String isUserGroupSearch)
  {
    this.isUserGroupSearch = isUserGroupSearch;
  }
  
  public String getTaxId()
  {
    return this.taxId;
  }
  
  public void setTaxId(String taxId)
  {
    this.taxId = taxId;
  }
  
  public String getDrivingLicenseId()
  {
    return this.drivingLicenseId;
  }
  
  public void setDrivingLicenseId(String drivingLicenseId)
  {
    this.drivingLicenseId = drivingLicenseId;
  }
  
  public long getNationalityId()
  {
    return this.nationalityId;
  }
  
  public void setNationalityId(long nationalityId)
  {
    this.nationalityId = nationalityId;
  }
  
  public String getDateofbirth()
  {
    return this.dateofbirth;
  }
  
  public void setDateofbirth(String dateofbirth)
  {
    this.dateofbirth = dateofbirth;
  }
  
  public String getNationalityName()
  {
    return this.nationalityName;
  }
  
  public void setNationalityName(String nationalityName)
  {
    this.nationalityName = nationalityName;
  }
  
  public String getDrivingLicenseExpDate()
  {
    return this.drivingLicenseExpDate;
  }
  
  public void setDrivingLicenseExpDate(String drivingLicenseExpDate)
  {
    this.drivingLicenseExpDate = drivingLicenseExpDate;
  }
  
  public List getDesignationList()
  {
    return this.designationList;
  }
  
  public void setDesignationList(List designationList)
  {
    this.designationList = designationList;
  }
  
  public long getDesignationId()
  {
    return this.designationId;
  }
  
  public void setDesignationId(long designationId)
  {
    this.designationId = designationId;
  }
  
  public String getDesignationName()
  {
    return this.designationName;
  }
  
  public void setDesignationName(String designationName)
  {
    this.designationName = designationName;
  }
  
  public String getSsnNumber()
  {
    return this.ssnNumber;
  }
  
  public void setSsnNumber(String ssnNumber)
  {
    this.ssnNumber = ssnNumber;
  }
  
  public FormFile getFileData()
  {
    return this.fileData;
  }
  
  public void setFileData(FormFile fileData)
  {
    this.fileData = fileData;
  }
  
  public long getApplicantId()
  {
    return this.applicantId;
  }
  
  public void setApplicantId(long applicantId)
  {
    this.applicantId = applicantId;
  }
}
