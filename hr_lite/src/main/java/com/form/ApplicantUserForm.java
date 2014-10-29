package com.form;

import java.util.Date;
import java.util.List;
import org.apache.struts.action.ActionForm;

public class ApplicantUserForm
  extends ActionForm
{
  public long applicantUserId;
  public String emailId;
  public String password;
  public String status;
  private long localeId;
  private long timezoneId;
  public long applicantId;
  public String createdBy;
  private Date createdDate;
  private Date updatedDate;
  private String updatedBy;
  private String uuid;
  public String fullName;
  public String applicantCode;
  public long applicant_number;
  private List localeList;
  private List timezoneList;
  
  public String getFullName()
  {
    return this.fullName;
  }
  
  public void setFullName(String fullName)
  {
    this.fullName = fullName;
  }
  
  public long getApplicantUserId()
  {
    return this.applicantUserId;
  }
  
  public void setApplicantUserId(long applicantUserId)
  {
    this.applicantUserId = applicantUserId;
  }
  
  public String getEmailId()
  {
    return this.emailId;
  }
  
  public void setEmailId(String emailId)
  {
    this.emailId = emailId;
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
  
  public long getApplicantId()
  {
    return this.applicantId;
  }
  
  public void setApplicantId(long applicantId)
  {
    this.applicantId = applicantId;
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
  
  public String getUuid()
  {
    return this.uuid;
  }
  
  public void setUuid(String uuid)
  {
    this.uuid = uuid;
  }
  
  public String getApplicantCode()
  {
    return this.applicantCode;
  }
  
  public void setApplicantCode(String applicantCode)
  {
    this.applicantCode = applicantCode;
  }
  
  public long getApplicant_number()
  {
    return this.applicant_number;
  }
  
  public void setApplicant_number(long applicantNumber)
  {
    this.applicant_number = applicantNumber;
  }
}
