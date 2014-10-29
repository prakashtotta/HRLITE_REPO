package com.form;

import java.util.Date;
import org.apache.struts.action.ActionForm;

public class CatagoryForm
  extends ActionForm
{
  public long catId;
  public String catName;
  public String catDesc;
  public String status;
  public String createdBy;
  public Date createdDate;
  
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
}
