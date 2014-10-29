package com.bean.onboard;

import java.util.Date;
import java.util.List;

public class OnBoardingTask
{
  public long onboardingTaskId;
  public String taskuuid;
  public String applicantuuid;
  public long onboardingid;
  public long applicantId;
  public long taskdefid;
  public String taskName;
  private String taskDesc;
  private String taskOwnerComment;
  private long ownerId;
  public String status;
  public int noofdays;
  public String eventType;
  public Date eventDate;
  public long assignedbyUserId;
  public String assignedbyUserName;
  public long assignedtoUserId;
  public String assignedtoUserName;
  public String isGroup;
  public String createdBy;
  public Date createdDate;
  public Date updatedDate;
  public String updatedBy;
  private List attributeValues;
  
  public long getOnboardingTaskId()
  {
    return this.onboardingTaskId;
  }
  
  public void setOnboardingTaskId(long onboardingTaskId)
  {
    this.onboardingTaskId = onboardingTaskId;
  }
  
  public long getOnboardingid()
  {
    return this.onboardingid;
  }
  
  public void setOnboardingid(long onboardingid)
  {
    this.onboardingid = onboardingid;
  }
  
  public long getApplicantId()
  {
    return this.applicantId;
  }
  
  public void setApplicantId(long applicantId)
  {
    this.applicantId = applicantId;
  }
  
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
  
  public String getTaskOwnerComment()
  {
    return this.taskOwnerComment;
  }
  
  public void setTaskOwnerComment(String taskOwnerComment)
  {
    this.taskOwnerComment = taskOwnerComment;
  }
  
  public long getOwnerId()
  {
    return this.ownerId;
  }
  
  public void setOwnerId(long ownerId)
  {
    this.ownerId = ownerId;
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
  
  public Date getEventDate()
  {
    return this.eventDate;
  }
  
  public void setEventDate(Date eventDate)
  {
    this.eventDate = eventDate;
  }
  
  public long getAssignedbyUserId()
  {
    return this.assignedbyUserId;
  }
  
  public void setAssignedbyUserId(long assignedbyUserId)
  {
    this.assignedbyUserId = assignedbyUserId;
  }
  
  public String getAssignedbyUserName()
  {
    return this.assignedbyUserName;
  }
  
  public void setAssignedbyUserName(String assignedbyUserName)
  {
    this.assignedbyUserName = assignedbyUserName;
  }
  
  public long getAssignedtoUserId()
  {
    return this.assignedtoUserId;
  }
  
  public void setAssignedtoUserId(long assignedtoUserId)
  {
    this.assignedtoUserId = assignedtoUserId;
  }
  
  public String getAssignedtoUserName()
  {
    return this.assignedtoUserName;
  }
  
  public void setAssignedtoUserName(String assignedtoUserName)
  {
    this.assignedtoUserName = assignedtoUserName;
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
  
  public List getAttributeValues()
  {
    return this.attributeValues;
  }
  
  public void setAttributeValues(List attributeValues)
  {
    this.attributeValues = attributeValues;
  }
  
  public String getTaskuuid()
  {
    return this.taskuuid;
  }
  
  public void setTaskuuid(String taskuuid)
  {
    this.taskuuid = taskuuid;
  }
  
  public String getApplicantuuid()
  {
    return this.applicantuuid;
  }
  
  public void setApplicantuuid(String applicantuuid)
  {
    this.applicantuuid = applicantuuid;
  }
  
  public String getIsGroup()
  {
    return this.isGroup;
  }
  
  public void setIsGroup(String isGroup)
  {
    this.isGroup = isGroup;
  }
}
