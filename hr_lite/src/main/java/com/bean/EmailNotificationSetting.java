package com.bean;

import java.util.Date;

public class EmailNotificationSetting
{
  public int weid;
  public String functionName;
  public String isWatcher;
  public String isHiringMgr;
  public String isRecruiter;
  public String isCurrentOwner;
  public String updatedBy;
  public Date updatedDate;
  public long super_user_key;
  
  public long getSuper_user_key()
  {
    return this.super_user_key;
  }
  
  public void setSuper_user_key(long superUserKey)
  {
    this.super_user_key = superUserKey;
  }
  
  public int getWeid()
  {
    return this.weid;
  }
  
  public void setWeid(int weid)
  {
    this.weid = weid;
  }
  
  public String getFunctionName()
  {
    return this.functionName;
  }
  
  public void setFunctionName(String functionName)
  {
    this.functionName = functionName;
  }
  
  public String getUpdatedBy()
  {
    return this.updatedBy;
  }
  
  public void setUpdatedBy(String updatedBy)
  {
    this.updatedBy = updatedBy;
  }
  
  public Date getUpdatedDate()
  {
    return this.updatedDate;
  }
  
  public void setUpdatedDate(Date updatedDate)
  {
    this.updatedDate = updatedDate;
  }
  
  public String getIsWatcher()
  {
    return this.isWatcher;
  }
  
  public void setIsWatcher(String isWatcher)
  {
    this.isWatcher = isWatcher;
  }
  
  public String getIsHiringMgr()
  {
    return this.isHiringMgr;
  }
  
  public void setIsHiringMgr(String isHiringMgr)
  {
    this.isHiringMgr = isHiringMgr;
  }
  
  public String getIsRecruiter()
  {
    return this.isRecruiter;
  }
  
  public void setIsRecruiter(String isRecruiter)
  {
    this.isRecruiter = isRecruiter;
  }
  
  public String getIsCurrentOwner()
  {
    return this.isCurrentOwner;
  }
  
  public void setIsCurrentOwner(String isCurrentOwner)
  {
    this.isCurrentOwner = isCurrentOwner;
  }
}
