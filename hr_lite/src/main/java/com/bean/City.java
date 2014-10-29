package com.bean;

public class City
{
  long cityId;
  String cityName;
  State state;
  String status;
  
  public long getCityId()
  {
    return this.cityId;
  }
  
  public void setCityId(long cityId)
  {
    this.cityId = cityId;
  }
  
  public String getCityName()
  {
    return this.cityName;
  }
  
  public void setCityName(String cityName)
  {
    this.cityName = cityName;
  }
  
  public State getState()
  {
    return this.state;
  }
  
  public void setState(State state)
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
}
