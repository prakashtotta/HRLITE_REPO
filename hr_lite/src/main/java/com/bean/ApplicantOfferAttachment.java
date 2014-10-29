package com.bean;

import java.util.Date;

public class ApplicantOfferAttachment
{
  public long applicantattachmentId;
  public long applicantId;
  private String attahmentdetails;
  private String attahmentname;
  private String createdBy;
  private Date createdDate;
  private String type = "";
  private String uuid;
  
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
  
  public String getAttahmentdetails()
  {
    return this.attahmentdetails;
  }
  
  public void setAttahmentdetails(String attahmentdetails)
  {
    this.attahmentdetails = attahmentdetails;
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
