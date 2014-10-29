package com.bean;

import java.util.Date;

public class FavoriteJob
{
  public long favjobid;
  private String empemail;
  public long reqId;
  public long agencyId;
  public String jobTitle;
  public String comment;
  public String createdBy;
  public Date createdDate;
  public String delete = "Delete";
  public String uuid;
  
  public String getDelete()
  {
    return this.delete;
  }
  
  public void setDelete(String delete)
  {
    this.delete = delete;
  }
  
  public long getFavjobid()
  {
    return this.favjobid;
  }
  
  public void setFavjobid(long favjobid)
  {
    this.favjobid = favjobid;
  }
  
  public long getReqId()
  {
    return this.reqId;
  }
  
  public void setReqId(long reqId)
  {
    this.reqId = reqId;
  }
  
  public String getJobTitle()
  {
    return this.jobTitle;
  }
  
  public void setJobTitle(String jobTitle)
  {
    this.jobTitle = jobTitle;
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
  
  public String getComment()
  {
    return this.comment;
  }
  
  public void setComment(String comment)
  {
    this.comment = comment;
  }
  
  public String getEmpemail()
  {
    return this.empemail;
  }
  
  public void setEmpemail(String empemail)
  {
    this.empemail = empemail;
  }
  
  public long getAgencyId()
  {
    return this.agencyId;
  }
  
  public void setAgencyId(long agencyId)
  {
    this.agencyId = agencyId;
  }
  
  public String getUuid()
  {
    return this.uuid;
  }
  
  public void setUuid(String uuid)
  {
    this.uuid = uuid;
  }
}
