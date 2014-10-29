package com.bean;

import java.util.Date;

public class BulkUploadTask
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
  public long createdById;
  
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
  
  public String getErrorDesc()
  {
    return this.errorDesc;
  }
  
  public void setErrorDesc(String errorDesc)
  {
    this.errorDesc = errorDesc;
  }
  
  public long getCreatedById()
  {
    return this.createdById;
  }
  
  public void setCreatedById(long createdById)
  {
    this.createdById = createdById;
  }
}
