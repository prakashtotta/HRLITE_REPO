package com.bean.employee;

import com.bean.lov.LicenseType;
import java.util.Date;

public class UserLicenses
{
  private long userLicenseId;
  public long userId;
  private LicenseType licensetype;
  private String licenseNumber;
  private String licenseIssueAuthority;
  private Date issueDate;
  private Date expireDate;
  private String comment;
  
  public long getUserLicenseId()
  {
    return this.userLicenseId;
  }
  
  public void setUserLicenseId(long userLicenseId)
  {
    this.userLicenseId = userLicenseId;
  }
  
  public long getUserId()
  {
    return this.userId;
  }
  
  public void setUserId(long userId)
  {
    this.userId = userId;
  }
  
  public LicenseType getLicensetype()
  {
    return this.licensetype;
  }
  
  public void setLicensetype(LicenseType licensetype)
  {
    this.licensetype = licensetype;
  }
  
  public String getLicenseNumber()
  {
    return this.licenseNumber;
  }
  
  public void setLicenseNumber(String licenseNumber)
  {
    this.licenseNumber = licenseNumber;
  }
  
  public String getLicenseIssueAuthority()
  {
    return this.licenseIssueAuthority;
  }
  
  public void setLicenseIssueAuthority(String licenseIssueAuthority)
  {
    this.licenseIssueAuthority = licenseIssueAuthority;
  }
  
  public Date getIssueDate()
  {
    return this.issueDate;
  }
  
  public void setIssueDate(Date issueDate)
  {
    this.issueDate = issueDate;
  }
  
  public Date getExpireDate()
  {
    return this.expireDate;
  }
  
  public void setExpireDate(Date expireDate)
  {
    this.expireDate = expireDate;
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
