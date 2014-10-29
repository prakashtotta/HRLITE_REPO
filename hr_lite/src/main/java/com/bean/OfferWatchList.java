package com.bean;

import java.util.Date;

public class OfferWatchList
{
  public long offerWatchListId;
  public long applicantId;
  public long requisitionId;
  public long userId;
  public String username;
  private String createdBy;
  private Date createdDate;
  public Date updatedDate;
  public String updatedBy;
  public String isemailsent = "N";
  public String functionType;
  
  public long getOfferWatchListId()
  {
    return this.offerWatchListId;
  }
  
  public void setOfferWatchListId(long offerWatchListId)
  {
    this.offerWatchListId = offerWatchListId;
  }
  
  public long getApplicantId()
  {
    return this.applicantId;
  }
  
  public void setApplicantId(long applicantId)
  {
    this.applicantId = applicantId;
  }
  
  public long getRequisitionId()
  {
    return this.requisitionId;
  }
  
  public void setRequisitionId(long requisitionId)
  {
    this.requisitionId = requisitionId;
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
  
  public String getFunctionType()
  {
    return this.functionType;
  }
  
  public void setFunctionType(String functionType)
  {
    this.functionType = functionType;
  }
}
