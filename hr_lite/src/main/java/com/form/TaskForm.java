package com.form;

import java.util.Date;
import java.util.List;
import org.apache.struts.action.ActionForm;

public class TaskForm
  extends ActionForm
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
  public String status;
  public String createdBy;
  public Date createdDate;
  public Date updatedDate;
  public String updatedBy;
  private String searchassigneddate = "";
  private String assignedcri;
  private List tasktypeList;
  private List assignedbyUsersList;
  private List assignedtoUsersList;
  public String notes;
  public String eventdate;
  
  public long getTaskId()
  {
    return this.taskId;
  }
  
  public void setTaskId(long taskId)
  {
    this.taskId = taskId;
  }
  
  public long getIdvalue()
  {
    return this.idvalue;
  }
  
  public void setIdvalue(long idvalue)
  {
    this.idvalue = idvalue;
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
  
  public String getUuid()
  {
    return this.uuid;
  }
  
  public void setUuid(String uuid)
  {
    this.uuid = uuid;
  }
  
  public String getSearchassigneddate()
  {
    return this.searchassigneddate;
  }
  
  public void setSearchassigneddate(String searchassigneddate)
  {
    this.searchassigneddate = searchassigneddate;
  }
  
  public String getAssignedcri()
  {
    return this.assignedcri;
  }
  
  public void setAssignedcri(String assignedcri)
  {
    this.assignedcri = assignedcri;
  }
  
  public List getTasktypeList()
  {
    return this.tasktypeList;
  }
  
  public void setTasktypeList(List tasktypeList)
  {
    this.tasktypeList = tasktypeList;
  }
  
  public List getAssignedbyUsersList()
  {
    return this.assignedbyUsersList;
  }
  
  public void setAssignedbyUsersList(List assignedbyUsersList)
  {
    this.assignedbyUsersList = assignedbyUsersList;
  }
  
  public List getAssignedtoUsersList()
  {
    return this.assignedtoUsersList;
  }
  
  public void setAssignedtoUsersList(List assignedtoUsersList)
  {
    this.assignedtoUsersList = assignedtoUsersList;
  }
  
  public String getTaskname()
  {
    return this.taskname;
  }
  
  public void setTaskname(String taskname)
  {
    this.taskname = taskname;
  }
  
  public String getNotes()
  {
    return this.notes;
  }
  
  public void setNotes(String notes)
  {
    this.notes = notes;
  }
  
  public String getEventdate()
  {
    return this.eventdate;
  }
  
  public void setEventdate(String eventdate)
  {
    this.eventdate = eventdate;
  }
}
