package com.bean;

import java.util.Date;

public class ReferAFriend
{
  public long referafriendId;
  public String name;
  public String emailId;
  public String note;
  public String status;
  public String createdBy;
  private Date createdDate;
  private Date updatedDate;
  private String updatedBy;
  public long jobRequisitionId;
  public String jobTitle;
  public long emp_ref_id;
  
  public long getReferafriendId()
  {
    return this.referafriendId;
  }
  
  public void setReferafriendId(long referafriendId)
  {
    this.referafriendId = referafriendId;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public String getEmailId()
  {
    return this.emailId;
  }
  
  public void setEmailId(String emailId)
  {
    this.emailId = emailId;
  }
  
  public String getNote()
  {
    return this.note;
  }
  
  public void setNote(String note)
  {
    this.note = note;
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
  
  public long getJobRequisitionId()
  {
    return this.jobRequisitionId;
  }
  
  public void setJobRequisitionId(long jobRequisitionId)
  {
    this.jobRequisitionId = jobRequisitionId;
  }
  
  public String getJobTitle()
  {
    return this.jobTitle;
  }
  
  public void setJobTitle(String jobTitle)
  {
    this.jobTitle = jobTitle;
  }
  
  public long getEmp_ref_id()
  {
    return this.emp_ref_id;
  }
  
  public void setEmp_ref_id(long empRefId)
  {
    this.emp_ref_id = empRefId;
  }
}
