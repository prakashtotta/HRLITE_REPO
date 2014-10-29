package com.bean;

import java.util.Date;

public class PublishJobBoards
{
  private long publishJobBoardId;
  private JobRequisition jobRequisition;
  public String createdBy;
  public Date createdDate;
  public String updatedBy;
  public Date updatedDate;
  public String status;
  public String jobBoardCode;
  
  public long getPublishJobBoardId()
  {
    return this.publishJobBoardId;
  }
  
  public void setPublishJobBoardId(long publishJobBoardId)
  {
    this.publishJobBoardId = publishJobBoardId;
  }
  
  public JobRequisition getJobRequisition()
  {
    return this.jobRequisition;
  }
  
  public void setJobRequisition(JobRequisition jobRequisition)
  {
    this.jobRequisition = jobRequisition;
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
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public String getJobBoardCode()
  {
    return this.jobBoardCode;
  }
  
  public void setJobBoardCode(String jobBoardCode)
  {
    this.jobBoardCode = jobBoardCode;
  }
}
