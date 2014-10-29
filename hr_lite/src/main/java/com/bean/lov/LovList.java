package com.bean.lov;

public class LovList
{
  public long lovListId;
  public String lovListCode;
  public String lovListValueCode;
  public String status;
  public String lovListValueName;
  public long localeId;
  public long super_user_key;
  
  public long getLovListId()
  {
    return this.lovListId;
  }
  
  public void setLovListId(long lovListId)
  {
    this.lovListId = lovListId;
  }
  
  public String getLovListCode()
  {
    return this.lovListCode;
  }
  
  public void setLovListCode(String lovListCode)
  {
    this.lovListCode = lovListCode;
  }
  
  public String getLovListValueCode()
  {
    return this.lovListValueCode;
  }
  
  public void setLovListValueCode(String lovListValueCode)
  {
    this.lovListValueCode = lovListValueCode;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public String getLovListValueName()
  {
    return this.lovListValueName;
  }
  
  public void setLovListValueName(String lovListValueName)
  {
    this.lovListValueName = lovListValueName;
  }
  
  public long getLocaleId()
  {
    return this.localeId;
  }
  
  public void setLocaleId(long localeId)
  {
    this.localeId = localeId;
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
