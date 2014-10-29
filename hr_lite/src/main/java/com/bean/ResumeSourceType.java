package com.bean;

import java.util.Date;

public class ResumeSourceType
{
  public int resumeSourceTypeId;
  public String resumeSourceTypeName;
  public String resumeSourceTypeDesc;
  public String createdBy;
  public Date createdDate;
  
  public int getResumeSourceTypeId()
  {
    return this.resumeSourceTypeId;
  }
  
  public void setResumeSourceTypeId(int resumeSourceTypeId)
  {
    this.resumeSourceTypeId = resumeSourceTypeId;
  }
  
  public String getResumeSourceTypeName()
  {
    return this.resumeSourceTypeName;
  }
  
  public void setResumeSourceTypeName(String resumeSourceTypeName)
  {
    this.resumeSourceTypeName = resumeSourceTypeName;
  }
  
  public String getResumeSourceTypeDesc()
  {
    return this.resumeSourceTypeDesc;
  }
  
  public void setResumeSourceTypeDesc(String resumeSourceTypeDesc)
  {
    this.resumeSourceTypeDesc = resumeSourceTypeDesc;
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
}
