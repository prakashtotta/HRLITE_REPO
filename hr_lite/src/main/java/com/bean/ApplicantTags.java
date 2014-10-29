package com.bean;

public class ApplicantTags
{
  public long apptagId;
  public long applicantId;
  public String tagname;
  
  public long getApptagId()
  {
    return this.apptagId;
  }
  
  public void setApptagId(long apptagId)
  {
    this.apptagId = apptagId;
  }
  
  public long getApplicantId()
  {
    return this.applicantId;
  }
  
  public void setApplicantId(long applicantId)
  {
    this.applicantId = applicantId;
  }
  
  public String getTagname()
  {
    return this.tagname;
  }
  
  public void setTagname(String tagname)
  {
    this.tagname = tagname;
  }
}
