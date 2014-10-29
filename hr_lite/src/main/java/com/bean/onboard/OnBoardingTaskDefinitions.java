package com.bean.onboard;

import com.bean.User;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class OnBoardingTaskDefinitions
{
  public long taskdefid;
  public String taskName;
  public String taskDesc;
  public User primaryOwner;
  public long primaryOwnerId;
  public String isGroup;
  public String primaryOwnerName;
  public String status;
  public int noofdays;
  public String eventType;
  public String createdBy;
  public Date createdDate;
  public Date updatedDate;
  public String updatedBy;
  public Set otherowners;
  public List atrributes;
  public String primaryOwnerValue = "";
  public long super_user_key;
  
  public long getTaskdefid()
  {
    return this.taskdefid;
  }
  
  public void setTaskdefid(long taskdefid)
  {
    this.taskdefid = taskdefid;
  }
  
  public String getTaskName()
  {
    return this.taskName;
  }
  
  public void setTaskName(String taskName)
  {
    this.taskName = taskName;
  }
  
  public String getTaskDesc()
  {
    return this.taskDesc;
  }
  
  public void setTaskDesc(String taskDesc)
  {
    this.taskDesc = taskDesc;
  }
  
  public User getPrimaryOwner()
  {
    return this.primaryOwner;
  }
  
  public void setPrimaryOwner(User primaryOwner)
  {
    this.primaryOwner = primaryOwner;
    if (primaryOwner != null) {
      this.primaryOwnerValue = (primaryOwner.getFirstName() + " " + primaryOwner.getLastName());
    }
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public int getNoofdays()
  {
    return this.noofdays;
  }
  
  public void setNoofdays(int noofdays)
  {
    this.noofdays = noofdays;
  }
  
  public String getEventType()
  {
    return this.eventType;
  }
  
  public void setEventType(String eventType)
  {
    this.eventType = eventType;
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
  
  public Set getOtherowners()
  {
    return this.otherowners;
  }
  
  public void setOtherowners(Set otherowners)
  {
    this.otherowners = otherowners;
  }
  
  public List getAtrributes()
  {
    return this.atrributes;
  }
  
  public void setAtrributes(List atrributes)
  {
    this.atrributes = atrributes;
  }
  
  public long getPrimaryOwnerId()
  {
    return this.primaryOwnerId;
  }
  
  public void setPrimaryOwnerId(long primaryOwnerId)
  {
    this.primaryOwnerId = primaryOwnerId;
  }
  
  public String getIsGroup()
  {
    return this.isGroup;
  }
  
  public void setIsGroup(String isGroup)
  {
    this.isGroup = isGroup;
  }
  
  public String getPrimaryOwnerName()
  {
    return this.primaryOwnerName;
  }
  
  public void setPrimaryOwnerName(String primaryOwnerName)
  {
    this.primaryOwnerName = primaryOwnerName;
  }
  
  public long getSuper_user_key()
  {
    return this.super_user_key;
  }
  
  public void setSuper_user_key(long superUserKey)
  {
    this.super_user_key = superUserKey;
  }
}
