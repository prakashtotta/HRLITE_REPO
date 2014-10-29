package com.bean;

import java.util.List;

public class State
{
  long stateId;
  String stateName;
  Country country;
  long countryId;
  List city;
  String status;
  String olxStateCode;
  
  public String getOlxStateCode()
  {
    return this.olxStateCode;
  }
  
  public void setOlxStateCode(String olxStateCode)
  {
    this.olxStateCode = olxStateCode;
  }
  
  public long getStateId()
  {
    return this.stateId;
  }
  
  public void setStateId(long stateId)
  {
    this.stateId = stateId;
  }
  
  public String getStateName()
  {
    return this.stateName;
  }
  
  public void setStateName(String stateName)
  {
    this.stateName = stateName;
  }
  
  public Country getCountry()
  {
    return this.country;
  }
  
  public void setCountry(Country country)
  {
    this.country = country;
    this.countryId = country.getCountryId();
  }
  
  public List getCity()
  {
    return this.city;
  }
  
  public void setCity(List city)
  {
    this.city = city;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
}
