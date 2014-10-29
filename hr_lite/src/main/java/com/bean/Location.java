package com.bean;

import java.util.Date;

public class Location
{
  public long locationId;
  public String locationCode;
  public String locationName;
  public String address;
  public String zip;
  public String phone;
  public String fax;
  public String city;
  public String createdBy;
  public Date createdDate;
  public String status;
  public String notes;
  public State state;
  public Country country;
  public Region region;
  public String countryName;
  public String stateName = "";
  public String regionName;
  public long super_user_key;
  
  public long getLocationId()
  {
    return this.locationId;
  }
  
  public void setLocationId(long locationId)
  {
    this.locationId = locationId;
  }
  
  public String getLocationCode()
  {
    return this.locationCode;
  }
  
  public void setLocationCode(String locationCode)
  {
    this.locationCode = locationCode;
  }
  
  public String getLocationName()
  {
    return this.locationName;
  }
  
  public void setLocationName(String locationName)
  {
    this.locationName = locationName;
  }
  
  public String getAddress()
  {
    return this.address;
  }
  
  public void setAddress(String address)
  {
    this.address = address;
  }
  
  public String getZip()
  {
    return this.zip;
  }
  
  public void setZip(String zip)
  {
    this.zip = zip;
  }
  
  public String getPhone()
  {
    return this.phone;
  }
  
  public void setPhone(String phone)
  {
    this.phone = phone;
  }
  
  public String getFax()
  {
    return this.fax;
  }
  
  public void setFax(String fax)
  {
    this.fax = fax;
  }
  
  public String getCity()
  {
    return this.city;
  }
  
  public void setCity(String city)
  {
    this.city = city;
  }
  
  public String getCreatedBy()
  {
    return this.createdBy;
  }
  
  public void setCreatedBy(String createdBy)
  {
    this.createdBy = createdBy;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public String getNotes()
  {
    return this.notes;
  }
  
  public void setNotes(String notes)
  {
    this.notes = notes;
  }
  
  public Country getCountry()
  {
    return this.country;
  }
  
  public void setCountry(Country country)
  {
    this.country = country;
    if (country != null) {
      this.countryName = country.getCountryName();
    }
  }
  
  public State getState()
  {
    return this.state;
  }
  
  public void setState(State state)
  {
    this.state = state;
    if (state != null) {
      this.stateName = state.getStateName();
    }
  }
  
  public Date getCreatedDate()
  {
    return this.createdDate;
  }
  
  public void setCreatedDate(Date createdDate)
  {
    this.createdDate = createdDate;
  }
  
  public Region getRegion()
  {
    return this.region;
  }
  
  public void setRegion(Region region)
  {
    this.region = region;
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
