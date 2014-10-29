package com.bean.employee;

import com.bean.Country;
import com.bean.State;

public class UserContactInfo
{
  public long userContactId;
  public long userId;
  public String otherEmailId;
  public Country country;
  private State state;
  private String address;
  public String address2;
  public String zip_code;
  public String city;
  public String mobileNo;
  public String phoneHome;
  
  public long getUserContactId()
  {
    return this.userContactId;
  }
  
  public void setUserContactId(long userContactId)
  {
    this.userContactId = userContactId;
  }
  
  public long getUserId()
  {
    return this.userId;
  }
  
  public void setUserId(long userId)
  {
    this.userId = userId;
  }
  
  public String getOtherEmailId()
  {
    return this.otherEmailId;
  }
  
  public void setOtherEmailId(String otherEmailId)
  {
    this.otherEmailId = otherEmailId;
  }
  
  public Country getCountry()
  {
    return this.country;
  }
  
  public void setCountry(Country country)
  {
    this.country = country;
  }
  
  public State getState()
  {
    return this.state;
  }
  
  public void setState(State state)
  {
    this.state = state;
  }
  
  public String getAddress()
  {
    return this.address;
  }
  
  public void setAddress(String address)
  {
    this.address = address;
  }
  
  public String getAddress2()
  {
    return this.address2;
  }
  
  public void setAddress2(String address2)
  {
    this.address2 = address2;
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
  
  public String getMobileNo()
  {
    return this.mobileNo;
  }
  
  public void setMobileNo(String mobileNo)
  {
    this.mobileNo = mobileNo;
  }
  
  public String getPhoneHome()
  {
    return this.phoneHome;
  }
  
  public void setPhoneHome(String phoneHome)
  {
    this.phoneHome = phoneHome;
  }
}
