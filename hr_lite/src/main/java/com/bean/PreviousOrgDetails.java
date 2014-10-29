package com.bean;

import java.util.Date;
import java.util.List;

public class PreviousOrgDetails
{
  private long prevOrgDetailsId;
  private long applicantId;
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
  public long countryIdScreenField;
  public long stateIdScreenField;
  public int currencyIdScreenField;
  List attachmentList;
  
  public long getPrevOrgDetailsId()
  {
    return this.prevOrgDetailsId;
  }
  
  public void setPrevOrgDetailsId(long prevOrgDetailsId)
  {
    this.prevOrgDetailsId = prevOrgDetailsId;
  }
  
  public long getApplicantId()
  {
    return this.applicantId;
  }
  
  public void setApplicantId(long applicantId)
  {
    this.applicantId = applicantId;
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
  
  public List getAttachmentList()
  {
    return this.attachmentList;
  }
  
  public void setAttachmentList(List attachmentList)
  {
    this.attachmentList = attachmentList;
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
    if (country != null) {
      this.countryIdScreenField = country.getCountryId();
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
      this.stateIdScreenField = state.getStateId();
    }
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
    if (currency != null) {
      this.currencyIdScreenField = currency.getCurrencyId();
    }
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
  
  public long getCountryIdScreenField()
  {
    return this.countryIdScreenField;
  }
  
  public long getStateIdScreenField()
  {
    return this.stateIdScreenField;
  }
  
  public int getCurrencyIdScreenField()
  {
    return this.currencyIdScreenField;
  }
}
