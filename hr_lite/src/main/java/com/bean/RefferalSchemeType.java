package com.bean;

import java.util.Date;

public class RefferalSchemeType
{
  public long refferalSchemeTypeId;
  public String refferalSchemeName;
  public String refferalSchemeDesc;
  public String createdBy;
  public Date createdDate;
  public String status;
  public String uom = "";
  public long super_user_key;
  
  public long getRefferalSchemeTypeId()
  {
    return this.refferalSchemeTypeId;
  }
  
  public void setRefferalSchemeTypeId(long refferalSchemeTypeId)
  {
    this.refferalSchemeTypeId = refferalSchemeTypeId;
  }
  
  public String getRefferalSchemeName()
  {
    return this.refferalSchemeName;
  }
  
  public void setRefferalSchemeName(String refferalSchemeName)
  {
    this.refferalSchemeName = refferalSchemeName;
  }
  
  public String getRefferalSchemeDesc()
  {
    return this.refferalSchemeDesc;
  }
  
  public void setRefferalSchemeDesc(String refferalSchemeDesc)
  {
    this.refferalSchemeDesc = refferalSchemeDesc;
  }
  
  public String getCreatedBy()
  {
    return this.createdBy;
  }
  
  public void setCreatedBy(String createdBy)
  {
    this.createdBy = createdBy;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public Date getCreatedDate()
  {
    return this.createdDate;
  }
  
  public void setCreatedDate(Date createdDate)
  {
    this.createdDate = createdDate;
  }
  
  public String getUom()
  {
    return this.uom;
  }
  
  public void setUom(String uom)
  {
    this.uom = uom;
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
