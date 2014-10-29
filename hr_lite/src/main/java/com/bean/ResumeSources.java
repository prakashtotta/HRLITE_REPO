package com.bean;

import java.util.Date;

public class ResumeSources
{
  public int resumeSourcesId;
  public String resumeSourcesName;
  public String resumeSourcesDesc;
  public String createdBy;
  public Date createdDate;
  public int resumeSourceTypeId;
  public String resumeSourceCode;
  
  public int getResumeSourcesId()
  {
    return this.resumeSourcesId;
  }
  
  public void setResumeSourcesId(int resumeSourcesId)
  {
    this.resumeSourcesId = resumeSourcesId;
  }
  
  public String getResumeSourcesName()
  {
    return this.resumeSourcesName;
  }
  
  public void setResumeSourcesName(String resumeSourcesName)
  {
    this.resumeSourcesName = resumeSourcesName;
  }
  
  public String getResumeSourcesDesc()
  {
    return this.resumeSourcesDesc;
  }
  
  public void setResumeSourcesDesc(String resumeSourcesDesc)
  {
    this.resumeSourcesDesc = resumeSourcesDesc;
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
  
  public int getResumeSourceTypeId()
  {
    return this.resumeSourceTypeId;
  }
  
  public void setResumeSourceTypeId(int resumeSourceTypeId)
  {
    this.resumeSourceTypeId = resumeSourceTypeId;
  }
  
  public String getResumeSourceCode()
  {
    return this.resumeSourceCode;
  }
  
  public void setResumeSourceCode(String resumeSourceCode)
  {
    this.resumeSourceCode = resumeSourceCode;
  }
}
