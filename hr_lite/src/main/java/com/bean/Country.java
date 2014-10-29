package com.bean;

import java.io.Serializable;
import java.util.List;

public class Country
  implements Serializable
{
  long countryId;
  String countryName;
  public String countryCode;
  List state;
  Region region;
  String status;
  
  public long getCountryId()
  {
    return this.countryId;
  }
  
  public void setCountryId(long countryId)
  {
    this.countryId = countryId;
  }
  
  public String getCountryName()
  {
    return this.countryName;
  }
  
  public void setCountryName(String countryName)
  {
    this.countryName = countryName;
  }
  
  public List getState()
  {
    return this.state;
  }
  
  public void setState(List state)
  {
    this.state = state;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public Region getRegion()
  {
    return this.region;
  }
  
  public void setRegion(Region region)
  {
    this.region = region;
  }
  
  public String getCountryCode()
  {
    return this.countryCode;
  }
  
  public void setCountryCode(String countryCode)
  {
    this.countryCode = countryCode;
  }
}
