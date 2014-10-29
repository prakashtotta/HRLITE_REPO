package com.form;

import org.apache.struts.action.ActionForm;

public class AccomplishmentForm
  extends ActionForm
{
  public long accId;
  public String accName;
  public String accDesc;
  public String status;
  public String readPreview;
  
  public long getAccId()
  {
    return this.accId;
  }
  
  public void setAccId(long accId)
  {
    this.accId = accId;
  }
  
  public String getAccName()
  {
    return this.accName;
  }
  
  public void setAccName(String accName)
  {
    this.accName = accName;
  }
  
  public String getAccDesc()
  {
    return this.accDesc;
  }
  
  public void setAccDesc(String accDesc)
  {
    this.accDesc = accDesc;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public String getReadPreview()
  {
    return this.readPreview;
  }
  
  public void setReadPreview(String readPreview)
  {
    this.readPreview = readPreview;
  }
}
