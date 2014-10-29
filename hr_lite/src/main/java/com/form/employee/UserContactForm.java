package com.form.employee;

import com.bean.Country;
import com.bean.State;
import com.bean.employee.UserContactInfo;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;

public class UserContactForm
  extends ActionForm
{
  public long userContactId;
  public long userId;
  public String otherEmailId;
  public long countryId;
  public long stateId;
  public String countryName;
  public String stateName;
  private String address;
  public String address2;
  public String zip_code;
  public String city;
  public String mobileNo;
  public String phoneHome;
  private List countryList;
  private List stateList;
  
  public void fromValue(UserContactInfo uci, HttpServletRequest request)
  {
    this.userContactId = uci.getUserContactId();
    this.userId = uci.getUserId();
    this.otherEmailId = uci.getOtherEmailId();
    if (uci.getCountry() != null)
    {
      this.countryId = uci.getCountry().getCountryId();
      this.countryName = uci.getCountry().getCountryName();
    }
    if (uci.getState() != null)
    {
      this.stateId = uci.getState().getStateId();
      this.stateName = uci.getState().getStateName();
    }
    this.city = uci.getCity();
    this.address = uci.getAddress();
    this.address2 = uci.getAddress2();
    this.zip_code = uci.getZip_code();
    this.mobileNo = uci.getMobileNo();
    this.phoneHome = uci.getPhoneHome();
  }
  
  public UserContactInfo toValue(UserContactInfo uci, HttpServletRequest request)
    throws Exception
  {
    uci.setOtherEmailId(this.otherEmailId);
    uci.setUserId(this.userId);
    if (this.countryId != 0L)
    {
      Country country = new Country();
      country.setCountryId(this.countryId);
      uci.setCountry(country);
    }
    else
    {
      uci.setCountry(null);
    }
    if (this.stateId != 0L)
    {
      State state = new State();
      state.setStateId(this.stateId);
      uci.setState(state);
    }
    else
    {
      uci.setState(null);
    }
    uci.setCity(this.city);
    uci.setAddress(this.address);
    uci.setAddress2(this.address2);
    uci.setZip_code(this.zip_code);
    uci.setMobileNo(this.mobileNo);
    uci.setPhoneHome(this.phoneHome);
    return uci;
  }
  
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
  
  public long getCountryId()
  {
    return this.countryId;
  }
  
  public void setCountryId(long countryId)
  {
    this.countryId = countryId;
  }
  
  public long getStateId()
  {
    return this.stateId;
  }
  
  public void setStateId(long stateId)
  {
    this.stateId = stateId;
  }
  
  public String getCountryName()
  {
    return this.countryName;
  }
  
  public void setCountryName(String countryName)
  {
    this.countryName = countryName;
  }
  
  public String getStateName()
  {
    return this.stateName;
  }
  
  public void setStateName(String stateName)
  {
    this.stateName = stateName;
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
  
  public List getCountryList()
  {
    return this.countryList;
  }
  
  public void setCountryList(List countryList)
  {
    this.countryList = countryList;
  }
  
  public List getStateList()
  {
    return this.stateList;
  }
  
  public void setStateList(List stateList)
  {
    this.stateList = stateList;
  }
}
