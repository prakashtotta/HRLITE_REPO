package com.bean;

public class Accomplishments
{
  public long accId;
  public String accName;
  public String accDesc;
  public String status;
  public String readPreview;
  public long super_user_key;
  
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
  
  public long getSuper_user_key()
  {
    return this.super_user_key;
  }
  
  public void setSuper_user_key(long superUserKey)
  {
    this.super_user_key = superUserKey;
  }
}
