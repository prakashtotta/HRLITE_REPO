package com.bean.lov;

public class Languages
{
  public long languageId;
  public String languageName;
  public String languageDesc;
  public String status;
  public long super_user_key;
  
  public long getLanguageId()
  {
    return this.languageId;
  }
  
  public void setLanguageId(long languageId)
  {
    this.languageId = languageId;
  }
  
  public String getLanguageName()
  {
    return this.languageName;
  }
  
  public void setLanguageName(String languageName)
  {
    this.languageName = languageName;
  }
  
  public String getLanguageDesc()
  {
    return this.languageDesc;
  }
  
  public void setLanguageDesc(String languageDesc)
  {
    this.languageDesc = languageDesc;
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
