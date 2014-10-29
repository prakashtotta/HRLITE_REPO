package com.bean;

public class JobGrade
{
  public long jobgradeId;
  public String jobGradeName;
  public String jobGradeDesc;
  public String jobGradeResponsibility;
  public String status;
  public long super_user_key;
  
  public long getJobgradeId()
  {
    return this.jobgradeId;
  }
  
  public void setJobgradeId(long jobgradeId)
  {
    this.jobgradeId = jobgradeId;
  }
  
  public String getJobGradeName()
  {
    return this.jobGradeName;
  }
  
  public void setJobGradeName(String jobGradeName)
  {
    this.jobGradeName = jobGradeName;
  }
  
  public String getJobGradeDesc()
  {
    return this.jobGradeDesc;
  }
  
  public void setJobGradeDesc(String jobGradeDesc)
  {
    this.jobGradeDesc = jobGradeDesc;
  }
  
  public String getJobGradeResponsibility()
  {
    return this.jobGradeResponsibility;
  }
  
  public void setJobGradeResponsibility(String jobGradeResponsibility)
  {
    this.jobGradeResponsibility = jobGradeResponsibility;
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
