package com.form;

import com.bean.Country;
import com.bean.Location;
import com.bean.Region;
import com.bean.State;
import com.bean.User;
import com.bo.BOFactory;
import com.bo.LocationBO;
import com.bo.LovBO;
import com.util.StringUtils;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;

public class LocationForm
  extends ActionForm
{
  private List locationsList;
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
  public String readPreview;
  private long countryId;
  private long stateId;
  private long regionId;
  private List countryList;
  private List stateList;
  private List regionList;
  public State state;
  public Country country;
  public Region region;
  private String countryName;
  private String stateName;
  private String regionName;
  public String start;
  public String range;
  public String results;
  private List locationList;
  
  public long getLocationId()
  {
    return this.locationId;
  }
  
  public void setLocationId(long locationId)
  {
    this.locationId = locationId;
  }
  
  public String getCountryName()
  {
    return this.countryName;
  }
  
  public void setCountryName(String countryName)
  {
    this.countryName = countryName;
  }
  
  public void setCountryList(List countryList)
  {
    this.countryList = countryList;
  }
  
  public List getCountryList()
  {
    return this.countryList;
  }
  
  public List getStateList()
  {
    return this.stateList;
  }
  
  public void setStateList(List stateList)
  {
    this.stateList = stateList;
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
  
  public State getState()
  {
    return this.state;
  }
  
  public void setState(State state)
  {
    this.state = state;
    this.stateName = state.getStateName();
  }
  
  public Country getCountry()
  {
    return this.country;
  }
  
  public void setCountry(Country country)
  {
    this.country = country;
    this.countryName = country.getCountryName();
  }
  
  public List getLocationsList()
  {
    return this.locationsList;
  }
  
  public void setLocationsList(List locationsList)
  {
    this.locationsList = locationsList;
    this.locationList = BOFactory.getLocationBO().getAllLocationNameList();
  }
  
  public Date getCreatedDate()
  {
    return this.createdDate;
  }
  
  public void setCreatedDate(Date createdDate)
  {
    this.createdDate = createdDate;
  }
  
  public Location toValue(Location location, HttpServletRequest request)
    throws Exception
  {
    User user = (User)request.getSession().getAttribute("user_data");
    String locname = "";
    if (!StringUtils.isNullOrEmpty(this.city)) {
      locname = this.city;
    }
    if (this.stateId != 0L)
    {
      State state = BOFactory.getLovBO().getState(this.stateId);
      state.setStateId(this.stateId);
      location.setState(state);
      if (!StringUtils.isNullOrEmpty(locname)) {
        locname = locname + " ," + state.getStateName();
      } else {
        locname = state.getStateName();
      }
    }
    if (this.countryId != 0L)
    {
      Country country = BOFactory.getLovBO().getCountry(this.countryId);
      country.setCountryId(this.countryId);
      location.setCountry(country);
      if (!StringUtils.isNullOrEmpty(locname)) {
        locname = locname + " ," + country.getCountryName();
      } else {
        locname = country.getCountryName();
      }
    }
    location.setAddress(this.address);
    location.setZip(this.zip);
    location.setPhone(this.phone);
    
    location.setFax(this.fax);
    location.setCity(this.city);
    

    location.setCreatedBy(user.getUserName());
    location.setCreatedDate(new Date());
    location.setNotes(this.notes);
    

    location.setLocationCode(locname);
    location.setLocationName(locname);
    



    location.setStatus("A");
    
    return location;
  }
  
  public void fromValue(Location location, HttpServletRequest request)
    throws Exception
  {
    User user1 = (User)request.getSession().getAttribute("user_data");
    
    this.locationId = location.getLocationId();
    this.locationCode = location.getLocationCode();
    this.locationName = location.getLocationName();
    this.address = location.getAddress();
    this.zip = location.getZip();
    this.phone = location.getPhone();
    this.fax = location.getFax();
    this.city = location.getCity();
    if (location.getCountry() != null)
    {
      this.countryName = location.getCountry().getCountryName();
      this.countryId = location.getCountry().getCountryId();
    }
    if (location.getState() != null)
    {
      this.stateName = location.getState().getStateName();
      this.stateId = location.getState().getStateId();
    }
    if (location.getRegion() != null)
    {
      this.regionName = location.getRegion().getRegionName();
      this.regionId = location.getRegion().getRegionId();
    }
    this.createdBy = location.getCreatedBy();
    this.createdDate = location.getCreatedDate();
    this.status = location.getStatus();
    this.notes = location.getNotes();
  }
  
  public String getReadPreview()
  {
    return this.readPreview;
  }
  
  public void setReadPreview(String readPreview)
  {
    this.readPreview = readPreview;
  }
  
  public Region getRegion()
  {
    return this.region;
  }
  
  public void setRegion(Region region)
  {
    this.region = region;
    this.regionName = region.getRegionName();
  }
  
  public String getRegionName()
  {
    return this.regionName;
  }
  
  public void setRegionName(String regionName)
  {
    this.regionName = regionName;
  }
  
  public long getRegionId()
  {
    return this.regionId;
  }
  
  public void setRegionId(long regionId)
  {
    this.regionId = regionId;
  }
  
  public List getRegionList()
  {
    return this.regionList;
  }
  
  public void setRegionList(List regionList)
  {
    this.regionList = regionList;
  }
  
  public List getLocationList()
  {
    return this.locationList;
  }
  
  public void setLocationList(List locationList)
  {
    this.locationList = locationList;
  }
  
  public String getStart()
  {
    return this.start;
  }
  
  public void setStart(String start)
  {
    this.start = start;
  }
  
  public String getRange()
  {
    return this.range;
  }
  
  public void setRange(String range)
  {
    this.range = range;
  }
  
  public String getResults()
  {
    return this.results;
  }
  
  public void setResults(String results)
  {
    this.results = results;
  }
  
  public String getStateName()
  {
    return this.stateName;
  }
  
  public void setStateName(String stateName)
  {
    this.stateName = stateName;
  }
}
