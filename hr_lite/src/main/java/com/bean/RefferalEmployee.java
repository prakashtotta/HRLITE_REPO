package com.bean;

import java.util.Date;

public class RefferalEmployee
{
  public long employeeReferalId;
  public String employeecode;
  public String employeename;
  public String employeeemail;
  public String password;
  public String status;
  public String varificationcode;
  public String createdBy;
  public Date createdDate;
  public Date updatedDate;
  public String updatedBy;
  public String mobileNo;
  public Locale locale;
  public Timezone timezone;
  public String phoneHome;
  public String phoneOffice;
  public Country country;
  public State state;
  public String city;
  public long designationId;
  public long profilePhotoId;
  public String userName;
  private long localeId;
  public Department department;
  public ProjectCodes projectcode;
  public JobGrade jobgrade;
  public Organization organization;
  public String orgName;
  public String departmentName;
  public String projectcodeName;
  public Country nationality;
  public long super_user_key;
  
  public long getLocaleId()
  {
    return this.localeId;
  }
  
  public void setLocaleId(long localeId)
  {
    this.localeId = localeId;
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
  
  public String getMobileNo()
  {
    return this.mobileNo;
  }
  
  public void setMobileNo(String mobileNo)
  {
    this.mobileNo = mobileNo;
  }
  
  public Locale getLocale()
  {
    return this.locale;
  }
  
  public void setLocale(Locale locale)
  {
    this.locale = locale;
  }
  
  public Timezone getTimezone()
  {
    return this.timezone;
  }
  
  public void setTimezone(Timezone timezone)
  {
    this.timezone = timezone;
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
  }
  
  public State getState()
  {
    return this.state;
  }
  
  public void setState(State state)
  {
    this.state = state;
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
  
  public String getUserName()
  {
    return this.userName;
  }
  
  public void setUserName(String userName)
  {
    this.userName = userName;
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
  
  public String getDepartmentName()
  {
    return this.departmentName;
  }
  
  public void setDepartmentName(String departmentName)
  {
    this.departmentName = departmentName;
  }
  
  public String getProjectcodeName()
  {
    return this.projectcodeName;
  }
  
  public void setProjectcodeName(String projectcodeName)
  {
    this.projectcodeName = projectcodeName;
  }
  
  public JobGrade getJobgrade()
  {
    return this.jobgrade;
  }
  
  public void setJobgrade(JobGrade jobgrade)
  {
    this.jobgrade = jobgrade;
  }
  
  public Country getNationality()
  {
    return this.nationality;
  }
  
  public void setNationality(Country nationality)
  {
    this.nationality = nationality;
  }
  
  public long getSuper_user_key()
  {
    return this.super_user_key;
  }
  
  public void setSuper_user_key(long superUserKey)
  {
    this.super_user_key = superUserKey;
  }
}
