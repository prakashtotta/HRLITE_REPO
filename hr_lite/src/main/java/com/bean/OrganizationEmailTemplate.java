package com.bean;

import java.util.Date;

public class OrganizationEmailTemplate
{
  public long orgemailtemplid;
  private Organization organisation;
  public EmailTemplates emailtemplate;
  public EmailEvents emailevent;
  public String eventCode;
  public String createdBy;
  public Date createdDate;
  public Date updatedDate;
  public String updatedBy;
  public String orgEmailName;
  public String orgEmailDesc;
  public String orgName;
  public String emailtemplatename;
  public String status;
  
  public long getOrgemailtemplid()
  {
    return this.orgemailtemplid;
  }
  
  public void setOrgemailtemplid(long orgemailtemplid)
  {
    this.orgemailtemplid = orgemailtemplid;
  }
  
  public Organization getOrganisation()
  {
    return this.organisation;
  }
  
  public void setOrganisation(Organization organisation)
  {
    this.organisation = organisation;
    if (organisation != null) {
      this.orgName = organisation.getOrgName();
    }
  }
  
  public EmailTemplates getEmailtemplate()
  {
    return this.emailtemplate;
  }
  
  public void setEmailtemplate(EmailTemplates emailtemplate)
  {
    this.emailtemplate = emailtemplate;
    if (emailtemplate != null) {
      this.emailtemplatename = emailtemplate.getEmailtemplatename();
    }
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
  
  public Date getUpdatedDate()
  {
    return this.updatedDate;
  }
  
  public void setUpdatedDate(Date updatedDate)
  {
    this.updatedDate = updatedDate;
  }
  
  public String getUpdatedBy()
  {
    return this.updatedBy;
  }
  
  public void setUpdatedBy(String updatedBy)
  {
    this.updatedBy = updatedBy;
  }
  
  public String getOrgName()
  {
    return this.orgName;
  }
  
  public void setOrgName(String orgName)
  {
    this.orgName = orgName;
  }
  
  public String getEmailtemplatename()
  {
    return this.emailtemplatename;
  }
  
  public void setEmailtemplatename(String emailtemplatename)
  {
    this.emailtemplatename = emailtemplatename;
  }
  
  public String getOrgEmailName()
  {
    return this.orgEmailName;
  }
  
  public void setOrgEmailName(String orgEmailName)
  {
    this.orgEmailName = orgEmailName;
  }
  
  public String getOrgEmailDesc()
  {
    return this.orgEmailDesc;
  }
  
  public void setOrgEmailDesc(String orgEmailDesc)
  {
    this.orgEmailDesc = orgEmailDesc;
  }
  
  public EmailEvents getEmailevent()
  {
    return this.emailevent;
  }
  
  public void setEmailevent(EmailEvents emailevent)
  {
    this.emailevent = emailevent;
  }
  
  public String getEventCode()
  {
    return this.eventCode;
  }
  
  public void setEventCode(String eventCode)
  {
    this.eventCode = eventCode;
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
