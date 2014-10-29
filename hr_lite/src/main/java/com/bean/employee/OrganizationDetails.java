package com.bean.employee;

import com.bean.Country;
import com.bean.Currency;
import com.bean.State;
import java.util.Date;

public class OrganizationDetails
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
}
