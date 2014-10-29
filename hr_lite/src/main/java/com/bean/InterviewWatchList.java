package com.bean;

import java.util.Date;

public class InterviewWatchList
{
  public long interviewcclistId;
  public long applicantId;
  public long applicationeventId;
  public long userId;
  public String username;
  private String createdBy;
  private Date createdDate;
  public Date updatedDate;
  public String updatedBy;
  public String isemailsent = "N";
  
  public long getInterviewcclistId()
  {
    return this.interviewcclistId;
  }
  
  public void setInterviewcclistId(long interviewcclistId)
  {
    this.interviewcclistId = interviewcclistId;
  }
  
  public long getApplicantId()
  {
    return this.applicantId;
  }
  
  public void setApplicantId(long applicantId)
  {
    this.applicantId = applicantId;
  }
  
  public long getApplicationeventId()
  {
    return this.applicationeventId;
  }
  
  public void setApplicationeventId(long applicationeventId)
  {
    this.applicationeventId = applicationeventId;
  }
  
  public long getUserId()
  {
    return this.userId;
  }
  
  public void setUserId(long userId)
  {
    this.userId = userId;
  }
  
  public String getUsername()
  {
    return this.username;
  }
  
  public void setUsername(String username)
  {
    this.username = username;
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
  
  public String getIsemailsent()
  {
    return this.isemailsent;
  }
  
  public void setIsemailsent(String isemailsent)
  {
    this.isemailsent = isemailsent;
  }
}
