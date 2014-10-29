package com.form;

import com.bean.Country;
import com.bean.Department;
import com.bean.JobGrade;
import com.bean.Locale;
import com.bean.Organization;
import com.bean.ProjectCodes;
import com.bean.RefferalEmployee;
import com.bean.State;
import com.bean.Timezone;
import com.bean.User;
import com.util.EncryptDecrypt;
import com.util.StringUtils;
import java.io.PrintStream;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class RefferalForm
  extends ActionForm
{
  public long employeeReferalId;
  public String employeecode;
  public String employeename;
  public String employeeemail;
  String password;
  String cpassword;
  String currentpassword;
  String remme;
  public String status;
  String varificationcode;
  public String createdBy;
  public Date createdDate;
  public Date updatedDate;
  public String updatedBy;
  public long orgId;
  private long departmentId;
  private long projectcodeId;
  private long localeId;
  private long timezoneId;
  private long countryId;
  private long stateId;
  public String city;
  public long designationId;
  private long profilePhotoId;
  public String phoneHome;
  public String phoneOffice;
  public String mobileNo;
  private String userName;
  private List designationList;
  private List departmentList;
  private List countryList;
  private List stateList;
  private List localeList;
  private List timezoneList;
  private List organizationList;
  private List projectcodeList;
  private List jobgradeList;
  private String orgName;
  private String departmentName;
  public String department;
  public String projectcode;
  public String designationName;
  public String localeName;
  public String timezoneName;
  public String countryName;
  public String stateName;
  public Country country;
  public State state;
  private List orgnizationList;
  public String projCode;
  private FormFile profilePhoto;
  public int projectId;
  public String jobgradeName;
  public long jobgradeId;
  
  public int getProjectId()
  {
    return this.projectId;
  }
  
  public void setProjectId(int projectId)
  {
    this.projectId = projectId;
  }
  
  public FormFile getProfilePhoto()
  {
    return this.profilePhoto;
  }
  
  public void setProfilePhoto(FormFile profilePhoto)
  {
    this.profilePhoto = profilePhoto;
  }
  
  public String getProjCode()
  {
    return this.projCode;
  }
  
  public void setProjCode(String projCode)
  {
    this.projCode = projCode;
  }
  
  public List getOrgnizationList()
  {
    return this.orgnizationList;
  }
  
  public void setOrgnizationList(List orgnizationList)
  {
    this.orgnizationList = orgnizationList;
  }
  
  public State getState()
  {
    return this.state;
  }
  
  public void setState(State state)
  {
    this.state = state;
    this.stateName = state.getStateName();
  }
  
  public Country getCountry()
  {
    return this.country;
  }
  
  public void setCountry(Country country)
  {
    this.country = country;
    this.countryName = country.getCountryName();
  }
  
  public String getStateName()
  {
    return this.stateName;
  }
  
  public void setStateName(String stateName)
  {
    this.stateName = stateName;
  }
  
  public String getCountryName()
  {
    return this.countryName;
  }
  
  public void setCountryName(String countryName)
  {
    this.countryName = countryName;
  }
  
  public String getTimezoneName()
  {
    return this.timezoneName;
  }
  
  public void setTimezoneName(String timezoneName)
  {
    this.timezoneName = timezoneName;
  }
  
  public String getLocaleName()
  {
    return this.localeName;
  }
  
  public void setLocaleName(String localeName)
  {
    this.localeName = localeName;
  }
  
  public String getDesignationName()
  {
    return this.designationName;
  }
  
  public void setDesignationName(String designationName)
  {
    this.designationName = designationName;
  }
  
  public String getProjectcode()
  {
    return this.projectcode;
  }
  
  public void setProjectcode(String projectcode)
  {
    this.projectcode = projectcode;
  }
  
  public String getDepartmentName()
  {
    return this.departmentName;
  }
  
  public void setDepartmentName(String departmentName)
  {
    this.departmentName = departmentName;
  }
  
  public String getDepartment()
  {
    return this.department;
  }
  
  public void setDepartment(String department)
  {
    this.department = department;
  }
  
  public String getOrgName()
  {
    return this.orgName;
  }
  
  public void setOrgName(String orgName)
  {
    this.orgName = orgName;
  }
  
  public String getEmployeecode()
  {
    return this.employeecode;
  }
  
  public void setEmployeecode(String employeecode)
  {
    this.employeecode = employeecode;
  }
  
  public String getEmployeename()
  {
    return this.employeename;
  }
  
  public void setEmployeename(String employeename)
  {
    this.employeename = employeename;
  }
  
  public String getEmployeeemail()
  {
    return this.employeeemail;
  }
  
  public void setEmployeeemail(String employeeemail)
  {
    this.employeeemail = employeeemail;
  }
  
  public String getPassword()
  {
    return this.password;
  }
  
  public void setPassword(String password)
  {
    this.password = password;
  }
  
  public String getRemme()
  {
    return this.remme;
  }
  
  public void setRemme(String remme)
  {
    this.remme = remme;
  }
  
  public String getCpassword()
  {
    return this.cpassword;
  }
  
  public void setCpassword(String cpassword)
  {
    this.cpassword = cpassword;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public String getVarificationcode()
  {
    return this.varificationcode;
  }
  
  public void setVarificationcode(String varificationcode)
  {
    this.varificationcode = varificationcode;
  }
  
  public long getEmployeeReferalId()
  {
    return this.employeeReferalId;
  }
  
  public void setEmployeeReferalId(long employeeReferalId)
  {
    this.employeeReferalId = employeeReferalId;
  }
  
  public RefferalEmployee toValue(RefferalEmployee refemp, HttpServletRequest request)
    throws Exception
  {
    System.out.println("toValue :");
    refemp.setEmployeecode(this.employeecode);
    refemp.setEmployeename(this.employeename);
    refemp.setEmployeeemail(this.employeeemail);
    if (this.password != null) {
      refemp.setPassword(EncryptDecrypt.encrypt(this.password));
    }
    refemp.setMobileNo(this.mobileNo);
    refemp.setPhoneHome(this.phoneHome);
    refemp.setPhoneOffice(this.phoneOffice);
    
    System.out.println("getMobileNo " + refemp.getMobileNo());
    System.out.println("getPhoneHome " + refemp.getPhoneHome());
    


    Timezone tz = new Timezone();
    tz.setTimezoneId(this.timezoneId);
    refemp.setTimezone(tz);
    if (this.orgId != 0L)
    {
      Organization organization = new Organization();
      organization.setOrgId(this.orgId);
      refemp.setOrganization(organization);
    }
    else if (this.orgId == 0L)
    {
      refemp.setOrganization(null);
    }
    if (this.departmentId != 0L)
    {
      Department dept = new Department();
      dept.setDepartmentId(this.departmentId);
      refemp.setDepartment(dept);
    }
    else if (this.departmentId == 0L)
    {
      refemp.setDepartment(null);
    }
    if (this.projectcodeId != 0L)
    {
      ProjectCodes pjcode = new ProjectCodes();
      
      pjcode.setProjectId(this.projectcodeId);
      refemp.setProjectcode(pjcode);
    }
    else if (this.projectcodeId == 0L)
    {
      refemp.setProjectcode(null);
    }
    if (this.jobgradeId != 0L)
    {
      JobGrade jobgrade = new JobGrade();
      jobgrade.setJobgradeId(this.jobgradeId);
      refemp.setJobgrade(jobgrade);
    }
    else if (this.jobgradeId == 0L)
    {
      refemp.setJobgrade(null);
    }
    refemp.setDesignationId(this.designationId);
    
    Locale lo = new Locale();
    lo.setLocaleId(this.localeId);
    refemp.setLocale(lo);
    
    Country country = new Country();
    country.setCountryId(this.countryId);
    refemp.setCountry(country);
    if (this.stateId != 0L)
    {
      State state = new State();
      state.setStateId(this.stateId);
      refemp.setState(state);
    }
    refemp.setCity(this.city);
    



    return refemp;
  }
  
  public void convertUserToReferral(User user)
  {
    this.orgId = user.getOrganization().getOrgId();
    this.departmentId = user.getDepartment().getDepartmentId();
    if (user.getProjectcode() != null) {
      this.projectcodeId = user.getProjectcode().getProjectId();
    }
    this.localeId = user.getLocale().getLocaleId();
    this.timezoneId = user.getTimezone().getTimezoneId();
    this.countryId = user.getCountry().getCountryId();
    this.stateId = user.getState().getStateId();
    this.city = user.getCity();
    this.designationId = user.getDesignationId();
    this.profilePhotoId = user.getProfilePhotoId();
    this.phoneHome = user.getPhoneHome();
    this.phoneOffice = user.getPhoneOffice();
    this.mobileNo = user.getMobileNo();
    this.employeecode = user.getEmployeecode();
    if (!StringUtils.isNullOrEmpty(user.getMiddleName())) {
      this.employeename = (user.getFirstName() + " " + user.getMiddleName() + " " + user.getLastName());
    } else {
      this.employeename = (user.getFirstName() + " " + user.getLastName());
    }
    this.employeeemail = user.getEmailId();
    this.userName = user.getUserName();
    this.password = user.getPassword();
    this.cpassword = user.getPassword();
    this.employeecode = user.getEmployeecode();
  }
  
  public void fromValue(RefferalEmployee refemp, HttpServletRequest request)
    throws Exception
  {
    System.out.println("in fromValue : ");
    this.employeeReferalId = refemp.getEmployeeReferalId();
    this.employeecode = refemp.getEmployeecode();
    this.employeename = refemp.getEmployeename();
    this.employeeemail = refemp.getEmployeeemail();
    this.mobileNo = refemp.getMobileNo();
    System.out.println("this.mobileNo : " + this.mobileNo);
    this.phoneHome = refemp.getPhoneHome();
    System.out.println("this.phoneHome " + this.phoneHome);
    this.phoneOffice = refemp.getPhoneOffice();
    
    this.createdBy = refemp.getCreatedBy();
    this.createdDate = refemp.getCreatedDate();
    this.updatedDate = refemp.getUpdatedDate();
    this.updatedBy = refemp.getUpdatedBy();
    this.password = refemp.getPassword();
    if (refemp.getOrganization().getOrgId() != 0L)
    {
      setOrgId(refemp.getOrganization().getOrgId());
      this.orgName = refemp.getOrganization().getOrgName();
    }
    if (refemp.getDepartment().getDepartmentId() != 0L)
    {
      setDepartmentId(refemp.getDepartment().getDepartmentId());
      this.departmentName = refemp.getDepartment().getDepartmentName();
    }
    if (refemp.getProjectcode() != null)
    {
      setProjectcodeId(refemp.getProjectcode().getProjectId());
      setProjectcode(refemp.getProjectcode().getProjName());
    }
    if (refemp.getJobgrade().getJobgradeId() != 0L)
    {
      setJobgradeId(refemp.getJobgrade().getJobgradeId());
      setJobgradeName(refemp.getJobgrade().getJobGradeName());
    }
    this.designationId = refemp.getDesignationId();
    if (refemp.getLocale() != null)
    {
      this.localeId = refemp.getLocale().getLocaleId();
      setLocaleName(refemp.getLocale().getLocaleName());
    }
    if (refemp.getTimezone() != null)
    {
      this.timezoneId = refemp.getTimezone().getTimezoneId();
      setTimezoneName(refemp.getTimezone().getTimezoneName());
    }
    if (refemp.getCountry() != null)
    {
      this.countryId = refemp.getCountry().getCountryId();
      setCountryName(refemp.getCountry().getCountryName());
    }
    if (refemp.getState() != null)
    {
      this.stateId = refemp.getState().getStateId();
      setStateName(refemp.getState().getStateName());
    }
    this.city = refemp.getCity();
    this.profilePhotoId = refemp.getProfilePhotoId();
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
  
  public Date getUpdatedDate()
  {
    return this.updatedDate;
  }
  
  public void setUpdatedDate(Date updatedDate)
  {
    this.updatedDate = updatedDate;
  }
  
  public String getUpdatedBy()
  {
    return this.updatedBy;
  }
  
  public void setUpdatedBy(String updatedBy)
  {
    this.updatedBy = updatedBy;
  }
  
  public String getCurrentpassword()
  {
    return this.currentpassword;
  }
  
  public void setCurrentpassword(String currentpassword)
  {
    this.currentpassword = currentpassword;
  }
  
  public long getOrgId()
  {
    return this.orgId;
  }
  
  public void setOrgId(long orgId)
  {
    this.orgId = orgId;
  }
  
  public long getDepartmentId()
  {
    return this.departmentId;
  }
  
  public void setDepartmentId(long departmentId)
  {
    this.departmentId = departmentId;
  }
  
  public long getLocaleId()
  {
    return this.localeId;
  }
  
  public void setLocaleId(long localeId)
  {
    this.localeId = localeId;
  }
  
  public long getTimezoneId()
  {
    return this.timezoneId;
  }
  
  public void setTimezoneId(long timezoneId)
  {
    this.timezoneId = timezoneId;
  }
  
  public long getCountryId()
  {
    return this.countryId;
  }
  
  public void setCountryId(long countryId)
  {
    this.countryId = countryId;
  }
  
  public long getStateId()
  {
    return this.stateId;
  }
  
  public void setStateId(long stateId)
  {
    this.stateId = stateId;
  }
  
  public String getCity()
  {
    return this.city;
  }
  
  public void setCity(String city)
  {
    this.city = city;
  }
  
  public long getDesignationId()
  {
    return this.designationId;
  }
  
  public void setDesignationId(long designationId)
  {
    this.designationId = designationId;
  }
  
  public long getProfilePhotoId()
  {
    return this.profilePhotoId;
  }
  
  public void setProfilePhotoId(long profilePhotoId)
  {
    this.profilePhotoId = profilePhotoId;
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
  
  public String getMobileNo()
  {
    return this.mobileNo;
  }
  
  public void setMobileNo(String mobileNo)
  {
    this.mobileNo = mobileNo;
  }
  
  public List getDesignationList()
  {
    return this.designationList;
  }
  
  public void setDesignationList(List designationList)
  {
    this.designationList = designationList;
  }
  
  public List getDepartmentList()
  {
    return this.departmentList;
  }
  
  public void setDepartmentList(List departmentList)
  {
    this.departmentList = departmentList;
  }
  
  public List getCountryList()
  {
    return this.countryList;
  }
  
  public void setCountryList(List countryList)
  {
    this.countryList = countryList;
  }
  
  public List getStateList()
  {
    return this.stateList;
  }
  
  public void setStateList(List stateList)
  {
    this.stateList = stateList;
  }
  
  public List getLocaleList()
  {
    return this.localeList;
  }
  
  public void setLocaleList(List localeList)
  {
    this.localeList = localeList;
  }
  
  public List getTimezoneList()
  {
    return this.timezoneList;
  }
  
  public void setTimezoneList(List timezoneList)
  {
    this.timezoneList = timezoneList;
  }
  
  public List getOrganizationList()
  {
    return this.organizationList;
  }
  
  public void setOrganizationList(List organizationList)
  {
    this.organizationList = organizationList;
  }
  
  public List getProjectcodeList()
  {
    return this.projectcodeList;
  }
  
  public void setProjectcodeList(List projectcodeList)
  {
    this.projectcodeList = projectcodeList;
  }
  
  public String getUserName()
  {
    return this.userName;
  }
  
  public void setUserName(String userName)
  {
    this.userName = userName;
  }
  
  public void setProjectcodeId(long projectcodeId)
  {
    this.projectcodeId = projectcodeId;
  }
  
  public long getProjectcodeId()
  {
    return this.projectcodeId;
  }
  
  public List getJobgradeList()
  {
    return this.jobgradeList;
  }
  
  public void setJobgradeList(List jobgradeList)
  {
    this.jobgradeList = jobgradeList;
  }
  
  public String getJobgradeName()
  {
    return this.jobgradeName;
  }
  
  public void setJobgradeName(String jobgradeName)
  {
    this.jobgradeName = jobgradeName;
  }
  
  public long getJobgradeId()
  {
    return this.jobgradeId;
  }
  
  public void setJobgradeId(long jobgradeId)
  {
    this.jobgradeId = jobgradeId;
  }
}
