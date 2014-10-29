package com.bean;

public class JobType
{
  public long jobTypeId;
  public String jobTypeName;
  public String jobTypeDesc;
  public String status;
  public long super_user_key;
  
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
  
  public long getSuper_user_key()
  {
    return this.super_user_key;
  }
  
  public void setSuper_user_key(long superUserKey)
  {
    this.super_user_key = superUserKey;
  }
}
