package com.bean.employee;

import com.bean.Country;
import java.util.Date;

public class Imigrations
{
  public long imigrationId;
  public long userId;
  public String passportVisaType;
  public String passportNo;
  public String passportIssuePlace;
  private Country issueCountry;
  public Date passportIssueDate;
  public Date passportExpiryDate;
  public Date eligibleReviewDate;
  public String comment;
  public String eligibleStatus;
  
  public String getEligibleStatus()
  {
    return this.eligibleStatus;
  }
  
  public void setEligibleStatus(String eligibleStatus)
  {
    this.eligibleStatus = eligibleStatus;
  }
  
  public long getImigrationId()
  {
    return this.imigrationId;
  }
  
  public void setImigrationId(long imigrationId)
  {
    this.imigrationId = imigrationId;
  }
  
  public long getUserId()
  {
    return this.userId;
  }
  
  public void setUserId(long userId)
  {
    this.userId = userId;
  }
  
  public String getPassportVisaType()
  {
    return this.passportVisaType;
  }
  
  public void setPassportVisaType(String passportVisaType)
  {
    this.passportVisaType = passportVisaType;
  }
  
  public String getPassportNo()
  {
    return this.passportNo;
  }
  
  public void setPassportNo(String passportNo)
  {
    this.passportNo = passportNo;
  }
  
  public String getPassportIssuePlace()
  {
    return this.passportIssuePlace;
  }
  
  public void setPassportIssuePlace(String passportIssuePlace)
  {
    this.passportIssuePlace = passportIssuePlace;
  }
  
  public Country getIssueCountry()
  {
    return this.issueCountry;
  }
  
  public void setIssueCountry(Country issueCountry)
  {
    this.issueCountry = issueCountry;
  }
  
  public Date getPassportIssueDate()
  {
    return this.passportIssueDate;
  }
  
  public void setPassportIssueDate(Date passportIssueDate)
  {
    this.passportIssueDate = passportIssueDate;
  }
  
  public Date getPassportExpiryDate()
  {
    return this.passportExpiryDate;
  }
  
  public void setPassportExpiryDate(Date passportExpiryDate)
  {
    this.passportExpiryDate = passportExpiryDate;
  }
  
  public Date getEligibleReviewDate()
  {
    return this.eligibleReviewDate;
  }
  
  public void setEligibleReviewDate(Date eligibleReviewDate)
  {
    this.eligibleReviewDate = eligibleReviewDate;
  }
  
  public String getComment()
  {
    return this.comment;
  }
  
  public void setComment(String comment)
  {
    this.comment = comment;
  }
}
