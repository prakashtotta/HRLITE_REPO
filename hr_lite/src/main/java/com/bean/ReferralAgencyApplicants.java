package com.bean;

import java.util.Date;

public class ReferralAgencyApplicants
{
  private long refagid;
  private long applicantId;
  public String applicantName;
  public int resumeSourceId;
  private long jobReqId;
  public String JobTitle;
  public String status;
  public String state;
  private Date createdDate;
  private Date updatedDate;
  private Date eventdate;
  
  public long getRefagid()
  {
    return this.refagid;
  }
  
  public void setRefagid(long refagid)
  {
    this.refagid = refagid;
  }
  
  public long getApplicantId()
  {
    return this.applicantId;
  }
  
  public void setApplicantId(long applicantId)
  {
    this.applicantId = applicantId;
  }
  
  public String getApplicantName()
  {
    return this.applicantName;
  }
  
  public void setApplicantName(String applicantName)
  {
    this.applicantName = applicantName;
  }
  
  public long getJobReqId()
  {
    return this.jobReqId;
  }
  
  public void setJobReqId(long jobReqId)
  {
    this.jobReqId = jobReqId;
  }
  
  public String getJobTitle()
  {
    return this.JobTitle;
  }
  
  public void setJobTitle(String jobTitle)
  {
    this.JobTitle = jobTitle;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public String getState()
  {
    return this.state;
  }
  
  public void setState(String state)
  {
    this.state = state;
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
  
  public int getResumeSourceId()
  {
    return this.resumeSourceId;
  }
  
  public void setResumeSourceId(int resumeSourceId)
  {
    this.resumeSourceId = resumeSourceId;
  }
  
  public Date getEventdate()
  {
    return this.eventdate;
  }
  
  public void setEventdate(Date eventdate)
  {
    this.eventdate = eventdate;
  }
}
