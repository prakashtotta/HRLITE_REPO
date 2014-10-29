package com.bean.lov;

public class VeteranDisability
{
  public long vdId;
  public String name;
  public String status;
  public long super_user_key;
  
  public long getVdId()
  {
    return this.vdId;
  }
  
  public void setVdId(long vdId)
  {
    this.vdId = vdId;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
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
