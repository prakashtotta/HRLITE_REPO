package com.bean.employee;

import com.bean.JobGrade;
import com.bean.JobType;
import com.bean.lov.JobCategory;
import java.util.Date;

public class UserJobDetails
{
  public long jobdetailsId;
  public long userId;
  public JobType jobtype;
  public JobCategory jobcategory;
  public Date joinedDate;
  public Date contractStartDate;
  public Date contractEndDate;
  public String contractDetails;
  JobGrade jobGrade;
  
  public long getUserId()
  {
    return this.userId;
  }
  
  public void setUserId(long userId)
  {
    this.userId = userId;
  }
  
  public JobType getJobtype()
  {
    return this.jobtype;
  }
  
  public void setJobtype(JobType jobtype)
  {
    this.jobtype = jobtype;
  }
  
  public JobGrade getJobGrade()
  {
    return this.jobGrade;
  }
  
  public void setJobGrade(JobGrade jobGrade)
  {
    this.jobGrade = jobGrade;
  }
  
  public JobCategory getJobcategory()
  {
    return this.jobcategory;
  }
  
  public void setJobcategory(JobCategory jobcategory)
  {
    this.jobcategory = jobcategory;
  }
  
  public Date getJoinedDate()
  {
    return this.joinedDate;
  }
  
  public void setJoinedDate(Date joinedDate)
  {
    this.joinedDate = joinedDate;
  }
  
  public Date getContractStartDate()
  {
    return this.contractStartDate;
  }
  
  public void setContractStartDate(Date contractStartDate)
  {
    this.contractStartDate = contractStartDate;
  }
  
  public Date getContractEndDate()
  {
    return this.contractEndDate;
  }
  
  public void setContractEndDate(Date contractEndDate)
  {
    this.contractEndDate = contractEndDate;
  }
  
  public String getContractDetails()
  {
    return this.contractDetails;
  }
  
  public void setContractDetails(String contractDetails)
  {
    this.contractDetails = contractDetails;
  }
  
  public long getJobdetailsId()
  {
    return this.jobdetailsId;
  }
  
  public void setJobdetailsId(long jobdetailsId)
  {
    this.jobdetailsId = jobdetailsId;
  }
}
