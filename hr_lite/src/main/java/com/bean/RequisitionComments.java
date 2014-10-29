package com.bean;

import java.util.Date;

public class RequisitionComments
{
  public long requisitionCommentId;
  private String uuid;
  private String comment;
  private String commentType;
  public long createdById;
  private String createdByName;
  private Date createdDate;
  
  public long getRequisitionCommentId()
  {
    return this.requisitionCommentId;
  }
  
  public void setRequisitionCommentId(long requisitionCommentId)
  {
    this.requisitionCommentId = requisitionCommentId;
  }
  
  public String getComment()
  {
    return this.comment;
  }
  
  public void setComment(String comment)
  {
    this.comment = comment;
  }
  
  public long getCreatedById()
  {
    return this.createdById;
  }
  
  public void setCreatedById(long createdById)
  {
    this.createdById = createdById;
  }
  
  public String getCreatedByName()
  {
    return this.createdByName;
  }
  
  public void setCreatedByName(String createdByName)
  {
    this.createdByName = createdByName;
  }
  
  public Date getCreatedDate()
  {
    return this.createdDate;
  }
  
  public void setCreatedDate(Date createdDate)
  {
    this.createdDate = createdDate;
  }
  
  public String getUuid()
  {
    return this.uuid;
  }
  
  public void setUuid(String uuid)
  {
    this.uuid = uuid;
  }
  
  public String getCommentType()
  {
    return this.commentType;
  }
  
  public void setCommentType(String commentType)
  {
    this.commentType = commentType;
  }
}
