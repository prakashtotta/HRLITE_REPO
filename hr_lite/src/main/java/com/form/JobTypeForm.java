package com.form;

import com.bean.JobType;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;

public class JobTypeForm
  extends ActionForm
{
  public long jobTypeId;
  public String jobTypeName;
  public String jobTypeDesc;
  public String status;
  
  public long getJobTypeId()
  {
    return this.jobTypeId;
  }
  
  public void setJobTypeId(long jobTypeId)
  {
    this.jobTypeId = jobTypeId;
  }
  
  public String getJobTypeName()
  {
    return this.jobTypeName;
  }
  
  public void setJobTypeName(String jobTypeName)
  {
    this.jobTypeName = jobTypeName;
  }
  
  public String getJobTypeDesc()
  {
    return this.jobTypeDesc;
  }
  
  public void setJobTypeDesc(String jobTypeDesc)
  {
    this.jobTypeDesc = jobTypeDesc;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public JobType toValue(JobType jobType, HttpServletRequest request)
    throws Exception
  {
    jobType.setJobTypeName(this.jobTypeName);
    jobType.setJobTypeDesc(this.jobTypeDesc);
    return jobType;
  }
  
  public void fromValue(JobType jobType, HttpServletRequest request)
    throws Exception
  {
    this.jobTypeId = jobType.getJobTypeId();
    this.jobTypeName = jobType.getJobTypeName();
    this.jobTypeDesc = jobType.getJobTypeDesc();
  }
}
