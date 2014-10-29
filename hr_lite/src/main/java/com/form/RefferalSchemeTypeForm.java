package com.form;

import java.util.Date;
import java.util.List;
import org.apache.struts.action.ActionForm;

public class RefferalSchemeTypeForm
  extends ActionForm
{
  public long refferalSchemeTypeId;
  public String refferalSchemeName;
  public String refferalSchemeDesc;
  public String createdBy;
  public Date createdDate;
  public String status;
  public String uom;
  private List uomList;
  
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
  
  public List getUomList()
  {
    return this.uomList;
  }
  
  public void setUomList(List uomList)
  {
    this.uomList = uomList;
  }
}
