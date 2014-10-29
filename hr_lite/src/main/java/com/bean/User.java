package com.bean;

import com.util.EncryptDecrypt;
import java.io.Serializable;
import java.util.Date;

public class User
  implements Serializable
{
  public long userId;
  public String employeecode;
  public String firstName;
  private String middleName;
  public String lastName;
  public String emailId;
  public String mobileNo;
  private Locale locale;
  private Timezone timezone;
  public Department department;
  public ProjectCodes projectcode;
  public Organization organization;
  public String phoneHome;
  public String phoneOffice;
  private Country country;
  private State state;
  public String city;
  private String address;
  public String userName;
  public String password;
  private Role role;
  private String createdBy;
  private Date createdDate;
  private Date updatedDate;
  private String updatedBy;
  public String status;
  public String defaultPage;
  public Location location;
  public String locationValue;
  public String locationCodeValue;
  public String orgName;
  public String departmentName;
  public String projectcodeName;
  public String roleValue;
  public String localeValue;
  public String countryValue;
  
  public void setFullName(String fullName)
  {
    this.fullName = fullName;
  }
  
  public String getFullName()
  {
    return this.fullName;
  }
  
  public String stateValue = "";
  public String fullName;
  public String departmentValue;
  public String departmentCodeValue;
  public String type = "Employee";
  public long profilePhotoId;
  public String taxId;
  public String drivingLicenseId;
  public Date drivingLicenseExpDate;
  public Date dateofbirth;
  public String ssnNumber;
  private Country nationality;
  private long designationId;
  private Designations designation;
  public String designationValue;
  private long applicantId;
  public long super_user_key;
  public String userIdEnc;
  public String packagetaken;
  private Menu menu;
  
  public long getUserId()
  {
    return this.userId;
  }
  
  public void setUserId(long userId)
  {
    this.userId = userId;
    this.userIdEnc = EncryptDecrypt.encrypt(String.valueOf(userId));
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
  
  public String getEmailId()
  {
    return this.emailId;
  }
  
  public void setEmailId(String emailId)
  {
    this.emailId = emailId;
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
  
  public Country getCountry()
  {
    return this.country;
  }
  
  public void setCountry(Country country)
  {
    this.country = country;
    if (country != null) {
      this.countryValue = country.getCountryName();
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
      this.stateValue = state.getStateName();
    }
  }
  
  public String getAddress()
  {
    return this.address;
  }
  
  public void setAddress(String address)
  {
    this.address = address;
  }
  
  public String getUserName()
  {
    return this.userName;
  }
  
  public void setUserName(String userName)
  {
    this.userName = userName;
  }
  
  public String getPassword()
  {
    return this.password;
  }
  
  public void setPassword(String password)
  {
    this.password = password;
  }
  
  public Role getRole()
  {
    return this.role;
  }
  
  public void setRole(Role role)
  {
    this.role = role;
    this.roleValue = role.getRoleName();
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
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public String getMiddleName()
  {
    return this.middleName;
  }
  
  public void setMiddleName(String middleName)
  {
    this.middleName = middleName;
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
  
  public String getCity()
  {
    return this.city;
  }
  
  public void setCity(String city)
  {
    this.city = city;
  }
  
  public Locale getLocale()
  {
    return this.locale;
  }
  
  public void setLocale(Locale locale)
  {
    this.locale = locale;
    this.localeValue = locale.getLocaleName();
  }
  
  public Timezone getTimezone()
  {
    return this.timezone;
  }
  
  public void setTimezone(Timezone timezone)
  {
    this.timezone = timezone;
  }
  
  public Location getLocation()
  {
    return this.location;
  }
  
  public void setLocation(Location location)
  {
    this.location = location;
    this.locationValue = location.getCity();
    if (location.getCountry() != null) {
      this.locationValue = (location.getCity() + " " + location.getCountry().getCountryName());
    }
    this.locationCodeValue = location.getLocationCode();
  }
  
  public String getDepartmentCodeValue()
  {
    return this.departmentCodeValue;
  }
  
  public void setDepartmentCodeValue(String departmentCodeValue)
  {
    this.departmentCodeValue = departmentCodeValue;
  }
  
  public String getType()
  {
    return this.type;
  }
  
  public void setType(String type)
  {
    this.type = type;
  }
  
  public String getOrgName()
  {
    return this.orgName;
  }
  
  public void setOrgName(String orgName)
  {
    this.orgName = orgName;
  }
  
  public long getProfilePhotoId()
  {
    return this.profilePhotoId;
  }
  
  public void setProfilePhotoId(long profilePhotoId)
  {
    this.profilePhotoId = profilePhotoId;
  }
  
  public Menu getMenu()
  {
    return this.menu;
  }
  
  public void setMenu(Menu menu)
  {
    this.menu = menu;
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
  
  public Department getDepartment()
  {
    return this.department;
  }
  
  public void setDepartment(Department department)
  {
    this.department = department;
    if (department != null) {
      this.departmentName = department.getDepartmentName();
    }
  }
  
  public ProjectCodes getProjectcode()
  {
    return this.projectcode;
  }
  
  public void setProjectcode(ProjectCodes projectcode)
  {
    this.projectcode = projectcode;
    if (projectcode != null) {
      this.projectcodeName = projectcode.getProjName();
    }
  }
  
  public Organization getOrganization()
  {
    return this.organization;
  }
  
  public void setOrganization(Organization organization)
  {
    this.organization = organization;
    if (organization != null) {
      this.orgName = organization.getOrgName();
    }
  }
  
  public String getDefaultPage()
  {
    return this.defaultPage;
  }
  
  public void setDefaultPage(String defaultPage)
  {
    this.defaultPage = defaultPage;
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
  
  public Date getDateofbirth()
  {
    return this.dateofbirth;
  }
  
  public void setDateofbirth(Date dateofbirth)
  {
    this.dateofbirth = dateofbirth;
  }
  
  public Country getNationality()
  {
    return this.nationality;
  }
  
  public void setNationality(Country nationality)
  {
    this.nationality = nationality;
  }
  
  public Date getDrivingLicenseExpDate()
  {
    return this.drivingLicenseExpDate;
  }
  
  public void setDrivingLicenseExpDate(Date drivingLicenseExpDate)
  {
    this.drivingLicenseExpDate = drivingLicenseExpDate;
  }
  
  public String getDesignationValue()
  {
    return this.designationValue;
  }
  
  public void setDesignationValue(String designationValue)
  {
    this.designationValue = designationValue;
  }
  
  public Designations getDesignation()
  {
    return this.designation;
  }
  
  public void setDesignation(Designations designation)
  {
    this.designation = designation;
    if (designation != null) {
      this.designationValue = designation.getDesignationName();
    }
  }
  
  public long getDesignationId()
  {
    return this.designationId;
  }
  
  public void setDesignationId(long designationId)
  {
    this.designationId = designationId;
  }
  
  public String getSsnNumber()
  {
    return this.ssnNumber;
  }
  
  public void setSsnNumber(String ssnNumber)
  {
    this.ssnNumber = ssnNumber;
  }
  
  public long getApplicantId()
  {
    return this.applicantId;
  }
  
  public void setApplicantId(long applicantId)
  {
    this.applicantId = applicantId;
  }
  
  public long getSuper_user_key()
  {
    return this.super_user_key;
  }
  
  public void setSuper_user_key(long superUserKey)
  {
    this.super_user_key = superUserKey;
  }
  
  public String getUserIdEnc()
  {
    return this.userIdEnc;
  }
  
  public String getPackagetaken()
  {
    return this.packagetaken;
  }
  
  public void setPackagetaken(String packagetaken)
  {
    this.packagetaken = packagetaken;
  }
}
