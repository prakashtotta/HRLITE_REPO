package com.performance.bean;

import java.util.Date;

public class UserGoalActivity
{
  public long userGolaActivityId;
  public long userGoalId;
  public String actionName;
  public long fromUserId;
  public String fromUserName;
  public long toUserId;
  public String toUserName;
  public Date createdDate;
  
  public long getUserGolaActivityId()
  {
    return this.userGolaActivityId;
  }
  
  public void setUserGolaActivityId(long userGolaActivityId)
  {
    this.userGolaActivityId = userGolaActivityId;
  }
  
  public long getUserGoalId()
  {
    return this.userGoalId;
  }
  
  public void setUserGoalId(long userGoalId)
  {
    this.userGoalId = userGoalId;
  }
  
  public String getActionName()
  {
    return this.actionName;
  }
  
  public void setActionName(String actionName)
  {
    this.actionName = actionName;
  }
  
  public long getFromUserId()
  {
    return this.fromUserId;
  }
  
  public void setFromUserId(long fromUserId)
  {
    this.fromUserId = fromUserId;
  }
  
  public String getFromUserName()
  {
    return this.fromUserName;
  }
  
  public void setFromUserName(String fromUserName)
  {
    this.fromUserName = fromUserName;
  }
  
  public long getToUserId()
  {
    return this.toUserId;
  }
  
  public void setToUserId(long toUserId)
  {
    this.toUserId = toUserId;
  }
  
  public String getToUserName()
  {
    return this.toUserName;
  }
  
  public void setToUserName(String toUserName)
  {
    this.toUserName = toUserName;
  }
  
  public Date getCreatedDate()
  {
    return this.createdDate;
  }
  
  public void setCreatedDate(Date createdDate)
  {
    this.createdDate = createdDate;
  }
}
