package com.bean.lov;

public class ZipCode
{
  private long id;
  private String zip_code;
  private String city;
  private String county;
  private String state_name;
  private String state_prefix;
  private String area_code;
  private double lat;
  private double lon;
  private long country_id;
  
  public long getId()
  {
    return this.id;
  }
  
  public void setId(long id)
  {
    this.id = id;
  }
  
  public String getZip_code()
  {
    return this.zip_code;
  }
  
  public void setZip_code(String zipCode)
  {
    this.zip_code = zipCode;
  }
  
  public String getCity()
  {
    return this.city;
  }
  
  public void setCity(String city)
  {
    this.city = city;
  }
  
  public String getCounty()
  {
    return this.county;
  }
  
  public void setCounty(String county)
  {
    this.county = county;
  }
  
  public String getState_name()
  {
    return this.state_name;
  }
  
  public void setState_name(String stateName)
  {
    this.state_name = stateName;
  }
  
  public String getState_prefix()
  {
    return this.state_prefix;
  }
  
  public void setState_prefix(String statePrefix)
  {
    this.state_prefix = statePrefix;
  }
  
  public String getArea_code()
  {
    return this.area_code;
  }
  
  public void setArea_code(String areaCode)
  {
    this.area_code = areaCode;
  }
  
  public double getLat()
  {
    return this.lat;
  }
  
  public void setLat(double lat)
  {
    this.lat = lat;
  }
  
  public double getLon()
  {
    return this.lon;
  }
  
  public void setLon(double lon)
  {
    this.lon = lon;
  }
  
  public long getCountry_id()
  {
    return this.country_id;
  }
  
  public void setCountry_id(long countryId)
  {
    this.country_id = countryId;
  }
}
