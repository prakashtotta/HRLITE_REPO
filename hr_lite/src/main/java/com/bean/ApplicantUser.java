package com.bean;

import java.util.Date;

public class ApplicantUser
{
  public long applicantUserId;
  public String emailId;
  public String password;
  public String status;
  private Locale locale;
  private Timezone timezone;
  public String createdBy;
  private Date createdDate;
  private Date updatedDate;
  private String updatedBy;
  public String applicantCode;
  public JobApplicant applicant;
  public long applicantId;
  public String fullName;
  public String jobTitle;
  public String interviewState;
  public String uuid;
  public long applicant_number;
  
  public String getUuid()
  {
    return this.uuid;
  }
  
  public void setUuid(String uuid)
  {
    this.uuid = uuid;
  }
  
  public long getApplicantId()
  {
    return this.applicantId;
  }
  
  public void setApplicantId(long applicantId)
  {
    this.applicantId = applicantId;
  }
  
  public String getFullName()
  {
    return this.fullName;
  }
  
  public void setFullName(String fullName)
  {
    this.fullName = fullName;
  }
  
  public String getJobTitle()
  {
    return this.jobTitle;
  }
  
  public void setJobTitle(String jobTitle)
  {
    this.jobTitle = jobTitle;
  }
  
  public String getInterviewState()
  {
    return this.interviewState;
  }
  
  public void setInterviewState(String interviewState)
  {
    this.interviewState = interviewState;
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
  
  public JobApplicant getApplicant()
  {
    return this.applicant;
  }
  
  public void setApplicant(JobApplicant applicant)
  {
    this.applicant = applicant;
    if (applicant != null)
    {
      this.fullName = applicant.getFullName();
      this.applicantId = applicant.getApplicantId();
      this.jobTitle = applicant.getReqName();
      this.interviewState = applicant.getInterviewState();
      this.uuid = applicant.getUuid();
      this.applicant_number = applicant.getApplicant_number();
    }
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
  
  public String getApplicantCode()
  {
    return this.applicantCode;
  }
  
  public void setApplicantCode(String applicantCode)
  {
    this.applicantCode = applicantCode;
  }
}
