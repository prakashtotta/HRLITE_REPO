package com.bean;

import java.util.Date;

public class TaskData
{
  public long taskId;
  public long idvalue;
  public String uuid;
  public String tasktype;
  public String taskname;
  public long assignedbyUserId;
  public String assignedbyUserName;
  public long assignedtoUserId;
  public String assignedtoUserName;
  public String assignedtoUserName1;
  public String status;
  public String createdBy;
  public Date createdDate;
  public Date updatedDate;
  public String updatedBy;
  public Date eventdate;
  public long super_user_key;
  public String notes;
  public int dayid;
  
  public long getTaskId()
  {
    return this.taskId;
  }
  
  public void setTaskId(long taskId)
  {
    this.taskId = taskId;
  }
  
  public String getTasktype()
  {
    return this.tasktype;
  }
  
  public void setTasktype(String tasktype)
  {
    this.tasktype = tasktype;
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
    this.assignedtoUserName1 = assignedtoUserName;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
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
  
  public long getIdvalue()
  {
    return this.idvalue;
  }
  
  public void setIdvalue(long idvalue)
  {
    this.idvalue = idvalue;
  }
  
  public String getUuid()
  {
    return this.uuid;
  }
  
  public void setUuid(String uuid)
  {
    this.uuid = uuid;
  }
  
  public String getAssignedtoUserName1()
  {
    return this.assignedtoUserName1;
  }
  
  public void setAssignedtoUserName1(String assignedtoUserName1)
  {
    this.assignedtoUserName1 = assignedtoUserName1;
  }
  
  public Date getEventdate()
  {
    return this.eventdate;
  }
  
  public void setEventdate(Date eventdate)
  {
    this.eventdate = eventdate;
  }
  
  public String getTaskname()
  {
    return this.taskname;
  }
  
  public void setTaskname(String taskname)
  {
    this.taskname = taskname;
  }
  
  public long getSuper_user_key()
  {
    return this.super_user_key;
  }
  
  public void setSuper_user_key(long superUserKey)
  {
    this.super_user_key = superUserKey;
  }
  
  public int getDayid()
  {
    return this.dayid;
  }
  
  public void setDayid(int dayid)
  {
    this.dayid = dayid;
  }
  
  public String getNotes()
  {
    return this.notes;
  }
  
  public void setNotes(String notes)
  {
    this.notes = notes;
  }
}
