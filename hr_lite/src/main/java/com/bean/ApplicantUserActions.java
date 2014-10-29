package com.bean;

import java.util.Date;

public class ApplicantUserActions
{
  public long appuseractionId;
  public String uuid;
  public long applicantId;
  public String applicantName;
  public long userId;
  private String createdBy;
  private Date createdDate;
  private Date updatedDate;
  private String updatedBy;
  public String actionName;
  private String creatorComment;
  private String applicantComment;
  public String status;
  
  public long getAppuseractionId()
  {
    return this.appuseractionId;
  }
  
  public void setAppuseractionId(long appuseractionId)
  {
    this.appuseractionId = appuseractionId;
  }
  
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
  
  public String getApplicantName()
  {
    return this.applicantName;
  }
  
  public void setApplicantName(String applicantName)
  {
    this.applicantName = applicantName;
  }
  
  public long getUserId()
  {
    return this.userId;
  }
  
  public void setUserId(long userId)
  {
    this.userId = userId;
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
  
  public String getActionName()
  {
    return this.actionName;
  }
  
  public void setActionName(String actionName)
  {
    this.actionName = actionName;
  }
  
  public String getCreatorComment()
  {
    return this.creatorComment;
  }
  
  public void setCreatorComment(String creatorComment)
  {
    this.creatorComment = creatorComment;
  }
  
  public String getApplicantComment()
  {
    return this.applicantComment;
  }
  
  public void setApplicantComment(String applicantComment)
  {
    this.applicantComment = applicantComment;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
}
