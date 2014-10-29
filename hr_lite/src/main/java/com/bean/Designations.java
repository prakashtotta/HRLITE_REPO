package com.bean;

import java.util.Date;

public class Designations
{
  public long designationId;
  public String designationCode;
  public String designationName;
  public String designationDesc;
  public String createdBy;
  public Date createdDate;
  private Date updatedDate;
  private String updatedBy;
  public long super_user_key;
  public String status;
  
  public long getDesignationId()
  {
    return this.designationId;
  }
  
  public void setDesignationId(long designationId)
  {
    this.designationId = designationId;
  }
  
  public String getDesignationCode()
  {
    return this.designationCode;
  }
  
  public void setDesignationCode(String designationCode)
  {
    this.designationCode = designationCode;
  }
  
  public String getDesignationName()
  {
    return this.designationName;
  }
  
  public void setDesignationName(String designationName)
  {
    this.designationName = designationName;
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
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public String getDesignationDesc()
  {
    return this.designationDesc;
  }
  
  public void setDesignationDesc(String designationDesc)
  {
    this.designationDesc = designationDesc;
  }
  
  public long getSuper_user_key()
  {
    return this.super_user_key;
  }
  
  public void setSuper_user_key(long superUserKey)
  {
    this.super_user_key = superUserKey;
  }
}
