package com.form.employee;

import com.bean.Country;
import com.bean.Currency;
import com.bean.State;
import com.bean.employee.OrganizationDetails;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;

public class UserExperienceForm
  extends ActionForm
{
  private long orgDetailsId;
  public long userId;
  public String prevOrgName;
  public String reportingToName;
  public String role;
  public String startdate;
  public String enddate;
  public String reasonforleave;
  public String createdBy;
  public Date createdDate;
  public String city;
  private Country country;
  private State state;
  public String lastSalary;
  public String bonus;
  private Currency currency;
  public String responsibilities;
  public String employercontactName;
  public String employercontactPhone;
  public long countryId = 0L;
  public long stateId = 0L;
  private int currencyId = 0;
  private List countryList;
  private List stateList;
  private List currencyList;
  public String currencyName;
  public String stateName;
  public String countryName;
  private List userExperiencesList;
  
  public List getUserExperiencesList()
  {
    return this.userExperiencesList;
  }
  
  public void setUserExperiencesList(List userExperiencesList)
  {
    this.userExperiencesList = userExperiencesList;
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
  
  public int getCurrencyId()
  {
    return this.currencyId;
  }
  
  public void setCurrencyId(int currencyId)
  {
    this.currencyId = currencyId;
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
  
  public List getCurrencyList()
  {
    return this.currencyList;
  }
  
  public void setCurrencyList(List currencyList)
  {
    this.currencyList = currencyList;
  }
  
  public long getOrgDetailsId()
  {
    return this.orgDetailsId;
  }
  
  public void setOrgDetailsId(long orgDetailsId)
  {
    this.orgDetailsId = orgDetailsId;
  }
  
  public long getUserId()
  {
    return this.userId;
  }
  
  public void setUserId(long userId)
  {
    this.userId = userId;
  }
  
  public String getPrevOrgName()
  {
    return this.prevOrgName;
  }
  
  public void setPrevOrgName(String prevOrgName)
  {
    this.prevOrgName = prevOrgName;
  }
  
  public String getReportingToName()
  {
    return this.reportingToName;
  }
  
  public void setReportingToName(String reportingToName)
  {
    this.reportingToName = reportingToName;
  }
  
  public String getRole()
  {
    return this.role;
  }
  
  public void setRole(String role)
  {
    this.role = role;
  }
  
  public String getStartdate()
  {
    return this.startdate;
  }
  
  public void setStartdate(String startdate)
  {
    this.startdate = startdate;
  }
  
  public String getEnddate()
  {
    return this.enddate;
  }
  
  public void setEnddate(String enddate)
  {
    this.enddate = enddate;
  }
  
  public String getReasonforleave()
  {
    return this.reasonforleave;
  }
  
  public void setReasonforleave(String reasonforleave)
  {
    this.reasonforleave = reasonforleave;
  }
  
  public String getCreatedBy()
  {
    return this.createdBy;
  }
  
  public void setCreatedBy(String createdBy)
  {
    this.createdBy = createdBy;
  }
  
  public Date getCreatedDate()
  {
    return this.createdDate;
  }
  
  public void setCreatedDate(Date createdDate)
  {
    this.createdDate = createdDate;
  }
  
  public String getCity()
  {
    return this.city;
  }
  
  public void setCity(String city)
  {
    this.city = city;
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
  
  public String getLastSalary()
  {
    return this.lastSalary;
  }
  
  public void setLastSalary(String lastSalary)
  {
    this.lastSalary = lastSalary;
  }
  
  public String getBonus()
  {
    return this.bonus;
  }
  
  public void setBonus(String bonus)
  {
    this.bonus = bonus;
  }
  
  public Currency getCurrency()
  {
    return this.currency;
  }
  
  public void setCurrency(Currency currency)
  {
    this.currency = currency;
  }
  
  public String getResponsibilities()
  {
    return this.responsibilities;
  }
  
  public void setResponsibilities(String responsibilities)
  {
    this.responsibilities = responsibilities;
  }
  
  public String getEmployercontactName()
  {
    return this.employercontactName;
  }
  
  public void setEmployercontactName(String employercontactName)
  {
    this.employercontactName = employercontactName;
  }
  
  public String getEmployercontactPhone()
  {
    return this.employercontactPhone;
  }
  
  public void setEmployercontactPhone(String employercontactPhone)
  {
    this.employercontactPhone = employercontactPhone;
  }
  
  public OrganizationDetails toValue(OrganizationDetails organizationDetails, HttpServletRequest request)
    throws Exception
  {
    organizationDetails.setUserId(this.userId);
    organizationDetails.setPrevOrgName(this.prevOrgName);
    organizationDetails.setRole(this.role);
    organizationDetails.setReasonforleave(this.reasonforleave);
    organizationDetails.setReportingToName(this.reportingToName);
    organizationDetails.setCity(this.city);
    organizationDetails.setLastSalary(this.lastSalary);
    organizationDetails.setBonus(this.bonus);
    organizationDetails.setResponsibilities(this.responsibilities);
    organizationDetails.setEmployercontactName(this.employercontactName);
    organizationDetails.setEmployercontactPhone(this.employercontactPhone);
    if (this.countryId != 0L)
    {
      Country country = new Country();
      country.setCountryId(this.countryId);
      organizationDetails.setCountry(country);
    }
    if (this.stateId != 0L)
    {
      State state = new State();
      state.setStateId(this.stateId);
      organizationDetails.setState(state);
    }
    if (this.currencyId != 0)
    {
      Currency currency = new Currency();
      currency.setCurrencyId(this.currencyId);
      organizationDetails.setCurrency(currency);
    }
    return organizationDetails;
  }
  
  public void fromValue(OrganizationDetails organizationDetails, HttpServletRequest request)
  {
    setOrgDetailsId(organizationDetails.getOrgDetailsId());
    this.userId = organizationDetails.getUserId();
    this.prevOrgName = organizationDetails.getPrevOrgName();
    this.role = organizationDetails.getRole();
    this.startdate = organizationDetails.getStartdate();
    this.enddate = organizationDetails.getEnddate();
    this.reasonforleave = organizationDetails.getReasonforleave();
    this.reportingToName = organizationDetails.getReportingToName();
    this.city = organizationDetails.getCity();
    this.lastSalary = organizationDetails.getLastSalary();
    this.bonus = organizationDetails.getBonus();
    this.responsibilities = organizationDetails.getResponsibilities();
    this.employercontactName = organizationDetails.getEmployercontactName();
    this.employercontactPhone = organizationDetails.getEmployercontactPhone();
    if (organizationDetails.getCountry() != null)
    {
      this.countryName = organizationDetails.getCountry().getCountryName();
      this.countryId = organizationDetails.getCountry().getCountryId();
    }
    if (organizationDetails.getState() != null)
    {
      this.stateName = organizationDetails.getState().getStateName();
      this.stateId = organizationDetails.getState().getStateId();
    }
    if (organizationDetails.getCurrency() != null)
    {
      this.currencyName = organizationDetails.getCurrency().getCurrencyName();
      this.currencyId = organizationDetails.getCurrency().getCurrencyId();
    }
  }
}
