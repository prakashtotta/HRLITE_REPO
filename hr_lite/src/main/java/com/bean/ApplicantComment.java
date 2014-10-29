package com.bean;

import java.util.Date;

public class ApplicantComment
{
  public long applicantcommenttId;
  public long applicantId;
  private String comment;
  public long byUserId;
  private String createdBy;
  private Date createdDate;
  private String bytype;
  private String isVisibleToApplicant;
  private String appuuid;
  private Date updatedDate;
  
  public Date getUpdatedDate()
  {
    return this.updatedDate;
  }
  
  public void setUpdatedDate(Date updatedDate)
  {
    this.updatedDate = updatedDate;
  }
  
  public long getApplicantcommenttId()
  {
    return this.applicantcommenttId;
  }
  
  public void setApplicantcommenttId(long applicantcommenttId)
  {
    this.applicantcommenttId = applicantcommenttId;
  }
  
  public long getApplicantId()
  {
    return this.applicantId;
  }
  
  public void setApplicantId(long applicantId)
  {
    this.applicantId = applicantId;
  }
  
  public String getComment()
  {
    return this.comment;
  }
  
  public void setComment(String comment)
  {
    this.comment = comment;
  }
  
  public long getByUserId()
  {
    return this.byUserId;
  }
  
  public void setByUserId(long byUserId)
  {
    this.byUserId = byUserId;
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
  
  public String getBytype()
  {
    return this.bytype;
  }
  
  public void setBytype(String bytype)
  {
    this.bytype = bytype;
  }
  
  public String getAppuuid()
  {
    return this.appuuid;
  }
  
  public void setAppuuid(String appuuid)
  {
    this.appuuid = appuuid;
  }
  
  public String getIsVisibleToApplicant()
  {
    return this.isVisibleToApplicant;
  }
  
  public void setIsVisibleToApplicant(String isVisibleToApplicant)
  {
    this.isVisibleToApplicant = isVisibleToApplicant;
  }
}
