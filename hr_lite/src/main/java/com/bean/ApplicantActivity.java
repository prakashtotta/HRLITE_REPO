package com.bean;

import java.util.Date;

public class ApplicantActivity
  implements Comparable
{
  public long activityId;
  public long applicantId;
  public String uuid;
  public String fullName;
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
  
  public long getApplicantId()
  {
    return this.applicantId;
  }
  
  public void setApplicantId(long applicantId)
  {
    this.applicantId = applicantId;
  }
  
  public String getUuid()
  {
    return this.uuid;
  }
  
  public void setUuid(String uuid)
  {
    this.uuid = uuid;
  }
  
  public String getFullName()
  {
    return this.fullName;
  }
  
  public void setFullName(String fullName)
  {
    this.fullName = fullName;
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
  
  public String getCreatedByType()
  {
    return this.createdByType;
  }
  
  public void setCreatedByType(String createdByType)
  {
    this.createdByType = createdByType;
  }
  
  public boolean equals(Object obj)
  {
    if (obj == null) {
      return false;
    }
    if (!getClass().equals(obj.getClass())) {
      return false;
    }
    ApplicantActivity obj2 = (ApplicantActivity)obj;
    if (this.activityName.equals(obj2.activityName)) {
      return true;
    }
    return false;
  }
  
  public int compareTo(Object obj)
  {
    ApplicantActivity act = (ApplicantActivity)obj;
    if (this.activityId == act.getActivityId()) {
      return 0;
    }
    if (this.activityId > act.getActivityId()) {
      return 1;
    }
    return -1;
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
