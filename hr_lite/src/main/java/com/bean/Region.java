package com.bean;

import java.util.List;

public class Region
{
  public long regionId;
  public String regionName;
  public String regionDesc;
  public String status;
  List country;
  
  public long getRegionId()
  {
    return this.regionId;
  }
  
  public void setRegionId(long regionId)
  {
    this.regionId = regionId;
  }
  
  public String getRegionName()
  {
    return this.regionName;
  }
  
  public void setRegionName(String regionName)
  {
    this.regionName = regionName;
  }
  
  public String getRegionDesc()
  {
    return this.regionDesc;
  }
  
  public void setRegionDesc(String regionDesc)
  {
    this.regionDesc = regionDesc;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public List getCountry()
  {
    return this.country;
  }
  
  public void setCountry(List country)
  {
    this.country = country;
  }
}
