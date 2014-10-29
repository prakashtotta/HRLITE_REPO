package com.bean.lov;

public class JobCategory
{
  public long jobCategoryId;
  public String jobCategoryName;
  public String jobCategoryDesc;
  public String status;
  public long super_user_key;
  public String olx_job_code;
  public String oodle_job_code;
  
  public long getJobCategoryId()
  {
    return this.jobCategoryId;
  }
  
  public void setJobCategoryId(long jobCategoryId)
  {
    this.jobCategoryId = jobCategoryId;
  }
  
  public String getJobCategoryName()
  {
    return this.jobCategoryName;
  }
  
  public void setJobCategoryName(String jobCategoryName)
  {
    this.jobCategoryName = jobCategoryName;
  }
  
  public String getJobCategoryDesc()
  {
    return this.jobCategoryDesc;
  }
  
  public void setJobCategoryDesc(String jobCategoryDesc)
  {
    this.jobCategoryDesc = jobCategoryDesc;
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
  
  public String getOlx_job_code()
  {
    return this.olx_job_code;
  }
  
  public void setOlx_job_code(String olxJobCode)
  {
    this.olx_job_code = olxJobCode;
  }
  
  public String getOodle_job_code()
  {
    return this.oodle_job_code;
  }
  
  public void setOodle_job_code(String oodleJobCode)
  {
    this.oodle_job_code = oodleJobCode;
  }
}
