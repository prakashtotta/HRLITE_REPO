package com.bean;

import java.sql.Blob;
import java.util.Date;

public class ApplicantAttachments
{
  public long applicantattachmentId;
  public long applicantId;
  private String attahmentdetails;
  private String attahmentname;
  private Blob attachmentdata;
  private String createdBy;
  private Date createdDate;
  private String type;
  private String uuid;
  private String appuuid;
  public int scribddocumentid;
  private String scribddocumentkey;
  
  public long getApplicantattachmentId()
  {
    return this.applicantattachmentId;
  }
  
  public void setApplicantattachmentId(long applicantattachmentId)
  {
    this.applicantattachmentId = applicantattachmentId;
  }
  
  public long getApplicantId()
  {
    return this.applicantId;
  }
  
  public void setApplicantId(long applicantId)
  {
    this.applicantId = applicantId;
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
  
  public String getType()
  {
    return this.type;
  }
  
  public void setType(String type)
  {
    this.type = type;
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
  
  public int getScribddocumentid()
  {
    return this.scribddocumentid;
  }
  
  public void setScribddocumentid(int scribddocumentid)
  {
    this.scribddocumentid = scribddocumentid;
  }
  
  public String getScribddocumentkey()
  {
    return this.scribddocumentkey;
  }
  
  public void setScribddocumentkey(String scribddocumentkey)
  {
    this.scribddocumentkey = scribddocumentkey;
  }
  
  public Blob getAttachmentdata()
  {
    return this.attachmentdata;
  }
  
  public void setAttachmentdata(Blob attachmentdata)
  {
    this.attachmentdata = attachmentdata;
  }
}
