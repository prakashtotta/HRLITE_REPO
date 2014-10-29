package com.bean.system;

import java.util.Date;

public class PublishToVendor
{
  public long publishToVendorId;
  public long jobreqId;
  public long userId;
  public String userName;
  private String createdBy;
  private Date createdDate;
  public int levelorder;
  public String isFromSystemRule;
  public long systemRuleId;
  
  public long getPublishToVendorId()
  {
    return this.publishToVendorId;
  }
  
  public void setPublishToVendorId(long publishToVendorId)
  {
    this.publishToVendorId = publishToVendorId;
  }
  
  public long getJobreqId()
  {
    return this.jobreqId;
  }
  
  public void setJobreqId(long jobreqId)
  {
    this.jobreqId = jobreqId;
  }
  
  public long getUserId()
  {
    return this.userId;
  }
  
  public void setUserId(long userId)
  {
    this.userId = userId;
  }
  
  public String getUserName()
  {
    return this.userName;
  }
  
  public void setUserName(String userName)
  {
    this.userName = userName;
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
  
  public int getLevelorder()
  {
    return this.levelorder;
  }
  
  public void setLevelorder(int levelorder)
  {
    this.levelorder = levelorder;
  }
  
  public String getIsFromSystemRule()
  {
    return this.isFromSystemRule;
  }
  
  public void setIsFromSystemRule(String isFromSystemRule)
  {
    this.isFromSystemRule = isFromSystemRule;
  }
  
  public long getSystemRuleId()
  {
    return this.systemRuleId;
  }
  
  public void setSystemRuleId(long systemRuleId)
  {
    this.systemRuleId = systemRuleId;
  }
}
