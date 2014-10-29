package com.bean;

import java.util.Date;

public class RequisitionActivity
{
  public long activityId;
  public long reqId;
  public String uuid;
  public String reqName;
  public String createdBy;
  public String createdByType;
  public Date createdDate;
  public long userId;
  public String userFullName;
  public String activityName;
  public String comment;
  public String isDisplayAppPage;
  public long assignedTouserId;
  public String assignedToUserName;
  public String isgroup = "N";
  
  public long getActivityId()
  {
    return this.activityId;
  }
  
  public void setActivityId(long activityId)
  {
    this.activityId = activityId;
  }
  
  public long getReqId()
  {
    return this.reqId;
  }
  
  public void setReqId(long reqId)
  {
    this.reqId = reqId;
  }
  
  public String getUuid()
  {
    return this.uuid;
  }
  
  public void setUuid(String uuid)
  {
    this.uuid = uuid;
  }
  
  public String getReqName()
  {
    return this.reqName;
  }
  
  public void setReqName(String reqName)
  {
    this.reqName = reqName;
  }
  
  public String getCreatedBy()
  {
    return this.createdBy;
  }
  
  public void setCreatedBy(String createdBy)
  {
    this.createdBy = createdBy;
  }
  
  public String getCreatedByType()
  {
    return this.createdByType;
  }
  
  public void setCreatedByType(String createdByType)
  {
    this.createdByType = createdByType;
  }
  
  public Date getCreatedDate()
  {
    return this.createdDate;
  }
  
  public void setCreatedDate(Date createdDate)
  {
    this.createdDate = createdDate;
  }
  
  public long getUserId()
  {
    return this.userId;
  }
  
  public void setUserId(long userId)
  {
    this.userId = userId;
  }
  
  public String getUserFullName()
  {
    return this.userFullName;
  }
  
  public void setUserFullName(String userFullName)
  {
    this.userFullName = userFullName;
  }
  
  public String getActivityName()
  {
    return this.activityName;
  }
  
  public void setActivityName(String activityName)
  {
    this.activityName = activityName;
  }
  
  public String getComment()
  {
    return this.comment;
  }
  
  public void setComment(String comment)
  {
    this.comment = comment;
  }
  
  public String getIsDisplayAppPage()
  {
    return this.isDisplayAppPage;
  }
  
  public void setIsDisplayAppPage(String isDisplayAppPage)
  {
    this.isDisplayAppPage = isDisplayAppPage;
  }
  
  public long getAssignedTouserId()
  {
    return this.assignedTouserId;
  }
  
  public void setAssignedTouserId(long assignedTouserId)
  {
    this.assignedTouserId = assignedTouserId;
  }
  
  public String getAssignedToUserName()
  {
    return this.assignedToUserName;
  }
  
  public void setAssignedToUserName(String assignedToUserName)
  {
    this.assignedToUserName = assignedToUserName;
  }
  
  public String getIsgroup()
  {
    return this.isgroup;
  }
  
  public void setIsgroup(String isgroup)
  {
    this.isgroup = isgroup;
  }
}
