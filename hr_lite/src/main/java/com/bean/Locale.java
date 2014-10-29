package com.bean;

import java.io.Serializable;

public class Locale
  implements Serializable
{
  public long localeId;
  public String localeCode;
  public String localeName;
  
  public long getLocaleId()
  {
    return this.localeId;
  }
  
  public void setLocaleId(long localeId)
  {
    this.localeId = localeId;
  }
  
  public String getLocaleCode()
  {
    return this.localeCode;
  }
  
  public void setLocaleCode(String localeCode)
  {
    this.localeCode = localeCode;
  }
  
  public String getLocaleName()
  {
    return this.localeName;
  }
  
  public void setLocaleName(String localeName)
  {
    this.localeName = localeName;
  }
}
