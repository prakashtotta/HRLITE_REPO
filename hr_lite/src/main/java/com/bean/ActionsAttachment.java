package com.bean;

import java.util.Date;

public class ActionsAttachment
{
  public long actionattachmentId;
  public long applicantId;
  public long idvalue;
  private String attahmentdetails;
  private String attahmentname;
  private String createdBy;
  private Date createdDate;
  private String action = "";
  private String uuid;
  private String appuuid;
  
  public long getApplicantId()
  {
    return this.applicantId;
  }
  
  public void setApplicantId(long applicantId)
  {
    this.applicantId = applicantId;
  }
  
  public long getIdvalue()
  {
    return this.idvalue;
  }
  
  public void setIdvalue(long idvalue)
  {
    this.idvalue = idvalue;
  }
  
  public String getAttahmentdetails()
  {
    return this.attahmentdetails;
  }
  
  public void setAttahmentdetails(String attahmentdetails)
  {
    this.attahmentdetails = attahmentdetails;
  }
  
  public String getAttahmentname()
  {
    return this.attahmentname;
  }
  
  public void setAttahmentname(String attahmentname)
  {
    this.attahmentname = attahmentname;
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
  
  public String getAction()
  {
    return this.action;
  }
  
  public void setAction(String action)
  {
    this.action = action;
  }
  
  public String getUuid()
  {
    return this.uuid;
  }
  
  public void setUuid(String uuid)
  {
    this.uuid = uuid;
  }
  
  public String getAppuuid()
  {
    return this.appuuid;
  }
  
  public void setAppuuid(String appuuid)
  {
    this.appuuid = appuuid;
  }
  
  public long getActionattachmentId()
  {
    return this.actionattachmentId;
  }
  
  public void setActionattachmentId(long actionattachmentId)
  {
    this.actionattachmentId = actionattachmentId;
  }
}
