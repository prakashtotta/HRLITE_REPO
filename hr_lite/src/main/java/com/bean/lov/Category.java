package com.bean.lov;

import java.util.Date;

public class Category
{
  public long catId;
  public String catName;
  public String catDesc;
  public String status;
  public String createdBy;
  public Date createdDate;
  public long super_user_key;
  
  public long getCatId()
  {
    return this.catId;
  }
  
  public void setCatId(long catId)
  {
    this.catId = catId;
  }
  
  public String getCatName()
  {
    return this.catName;
  }
  
  public void setCatName(String catName)
  {
    this.catName = catName;
  }
  
  public String getCatDesc()
  {
    return this.catDesc;
  }
  
  public void setCatDesc(String catDesc)
  {
    this.catDesc = catDesc;
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
  
  public long getSuper_user_key()
  {
    return this.super_user_key;
  }
  
  public void setSuper_user_key(long superUserKey)
  {
    this.super_user_key = superUserKey;
  }
}
