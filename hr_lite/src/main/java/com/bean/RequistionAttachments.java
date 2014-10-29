package com.bean;

import java.util.Date;

public class RequistionAttachments
{
  public long attachmentId;
  public long reqId;
  private String attahmentdetails;
  private String attahmentname;
  private String createdBy;
  private Date createdDate;
  private String type = "";
  private String uuid;
  private String visibleInJobDetails;
  
  public long getAttachmentId()
  {
    return this.attachmentId;
  }
  
  public void setAttachmentId(long attachmentId)
  {
    this.attachmentId = attachmentId;
  }
  
  public long getReqId()
  {
    return this.reqId;
  }
  
  public void setReqId(long reqId)
  {
    this.reqId = reqId;
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
  
  public String getVisibleInJobDetails()
  {
    return this.visibleInJobDetails;
  }
  
  public void setVisibleInJobDetails(String visibleInJobDetails)
  {
    this.visibleInJobDetails = visibleInJobDetails;
  }
}
