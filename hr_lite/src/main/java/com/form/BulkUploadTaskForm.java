package com.form;

import com.bean.BulkUploadTask;
import java.util.Date;
import java.util.List;
import org.apache.struts.action.ActionForm;

public class BulkUploadTaskForm
  extends ActionForm
{
  public long bulkuploadtaskid;
  public long jobreqId;
  public String jobTitle;
  public String uploadedFileName;
  public String filesList;
  public String successfilesList;
  public String failfilesList;
  public String status;
  public String errorDesc;
  public String createdBy;
  public Date createdDate;
  public Date updatedDate;
  public String updatedBy;
  public String tasktype;
  private String searchassigneddate = "";
  private String assignedcri;
  private List tasktypeList;
  private List assignedbyUsersList;
  private List statusList;
  public long createdbyid;
  BulkUploadTask task;
  
  public long getBulkuploadtaskid()
  {
    return this.bulkuploadtaskid;
  }
  
  public void setBulkuploadtaskid(long bulkuploadtaskid)
  {
    this.bulkuploadtaskid = bulkuploadtaskid;
  }
  
  public long getJobreqId()
  {
    return this.jobreqId;
  }
  
  public void setJobreqId(long jobreqId)
  {
    this.jobreqId = jobreqId;
  }
  
  public String getJobTitle()
  {
    return this.jobTitle;
  }
  
  public void setJobTitle(String jobTitle)
  {
    this.jobTitle = jobTitle;
  }
  
  public String getUploadedFileName()
  {
    return this.uploadedFileName;
  }
  
  public void setUploadedFileName(String uploadedFileName)
  {
    this.uploadedFileName = uploadedFileName;
  }
  
  public String getFilesList()
  {
    return this.filesList;
  }
  
  public void setFilesList(String filesList)
  {
    this.filesList = filesList;
  }
  
  public String getSuccessfilesList()
  {
    return this.successfilesList;
  }
  
  public void setSuccessfilesList(String successfilesList)
  {
    this.successfilesList = successfilesList;
  }
  
  public String getFailfilesList()
  {
    return this.failfilesList;
  }
  
  public void setFailfilesList(String failfilesList)
  {
    this.failfilesList = failfilesList;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public String getErrorDesc()
  {
    return this.errorDesc;
  }
  
  public void setErrorDesc(String errorDesc)
  {
    this.errorDesc = errorDesc;
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
  
  public String getTasktype()
  {
    return this.tasktype;
  }
  
  public void setTasktype(String tasktype)
  {
    this.tasktype = tasktype;
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
  
  public List getStatusList()
  {
    return this.statusList;
  }
  
  public void setStatusList(List statusList)
  {
    this.statusList = statusList;
  }
  
  public BulkUploadTask getTask()
  {
    return this.task;
  }
  
  public void setTask(BulkUploadTask task)
  {
    this.task = task;
  }
  
  public long getCreatedbyid()
  {
    return this.createdbyid;
  }
  
  public void setCreatedbyid(long createdbyid)
  {
    this.createdbyid = createdbyid;
  }
}
