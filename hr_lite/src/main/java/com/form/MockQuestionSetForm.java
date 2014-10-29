package com.form;

import java.util.Date;
import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class MockQuestionSetForm
  extends ActionForm
{
  public int catId;
  public String name;
  public String displayName;
  public String description;
  public String status;
  public String createdBy;
  public Date createdDate;
  private Date updatedDate;
  private String updatedBy;
  public int timeLimit;
  public FormFile attachmentData;
  public String attachmentName;
  
  public int getCatId()
  {
    return this.catId;
  }
  
  public void setCatId(int catId)
  {
    this.catId = catId;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public String getDisplayName()
  {
    return this.displayName;
  }
  
  public void setDisplayName(String displayName)
  {
    this.displayName = displayName;
  }
  
  public String getDescription()
  {
    return this.description;
  }
  
  public void setDescription(String description)
  {
    this.description = description;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
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
  
  public Date getUpdatedDate()
  {
    return this.updatedDate;
  }
  
  public void setUpdatedDate(Date updatedDate)
  {
    this.updatedDate = updatedDate;
  }
  
  public String getUpdatedBy()
  {
    return this.updatedBy;
  }
  
  public void setUpdatedBy(String updatedBy)
  {
    this.updatedBy = updatedBy;
  }
  
  public int getTimeLimit()
  {
    return this.timeLimit;
  }
  
  public void setTimeLimit(int timeLimit)
  {
    this.timeLimit = timeLimit;
  }
  
  public String getAttachmentName()
  {
    return this.attachmentName;
  }
  
  public void setAttachmentName(String attachmentName)
  {
    this.attachmentName = attachmentName;
  }
  
  public FormFile getAttachmentData()
  {
    return this.attachmentData;
  }
  
  public void setAttachmentData(FormFile attachmentData)
  {
    this.attachmentData = attachmentData;
  }
}
