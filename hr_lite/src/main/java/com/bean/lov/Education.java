package com.bean.lov;

public class Education
{
  public long educationId;
  public String educationName;
  public String educationDesc;
  public String status;
  public long super_user_key;
  
  public long getEducationId()
  {
    return this.educationId;
  }
  
  public void setEducationId(long educationId)
  {
    this.educationId = educationId;
  }
  
  public String getEducationName()
  {
    return this.educationName;
  }
  
  public void setEducationName(String educationName)
  {
    this.educationName = educationName;
  }
  
  public String getEducationDesc()
  {
    return this.educationDesc;
  }
  
  public void setEducationDesc(String educationDesc)
  {
    this.educationDesc = educationDesc;
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
