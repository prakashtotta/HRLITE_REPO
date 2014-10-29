package com.form;

import com.bean.EmailTemplates;
import com.bean.Organization;
import java.util.Date;
import java.util.List;
import org.apache.struts.action.ActionForm;

public class OrganizationEmailTemplateForm
  extends ActionForm
{
  public long orgemailtemplid;
  private Organization organisation;
  private EmailTemplates emailtemplate;
  public String functiontype;
  public String createdBy;
  public Date createdDate;
  public Date updatedDate;
  public String updatedBy;
  public String orgEmailName;
  public String orgEmailDesc;
  public long orgId;
  public long emailtemplateId;
  public List organizationList;
  public List emailtemplateList;
  public String emailtemplatename;
  public String start;
  public String range;
  public String results;
  public String readPreview;
  private List orgemailtempList;
  private List functionTypeList;
  public String emailvaluespan;
  public String emailtemplateidval;
  public String emailtemplatenameval;
  public String eventCode;
  public List OrgemailtempListfordisplay;
  public List emailEventCodeList;
  public long emailEventId;
  public String emailSubject;
  public String emailmessage;
  public String emailtemplateData = "";
  
  public String getEmailtemplateData()
  {
    return this.emailtemplateData;
  }
  
  public void setEmailtemplateData(String emailtemplateData)
  {
    this.emailtemplateData = emailtemplateData;
  }
  
  public String getEmailSubject()
  {
    return this.emailSubject;
  }
  
  public void setEmailSubject(String emailSubject)
  {
    this.emailSubject = emailSubject;
  }
  
  public String getEmailmessage()
  {
    return this.emailmessage;
  }
  
  public void setEmailmessage(String emailmessage)
  {
    this.emailmessage = emailmessage;
  }
  
  public long getEmailEventId()
  {
    return this.emailEventId;
  }
  
  public void setEmailEventId(long emailEventId)
  {
    this.emailEventId = emailEventId;
  }
  
  public List getEmailEventCodeList()
  {
    return this.emailEventCodeList;
  }
  
  public void setEmailEventCodeList(List emailEventCodeList)
  {
    this.emailEventCodeList = emailEventCodeList;
  }
  
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
  }
  
  public EmailTemplates getEmailtemplate()
  {
    return this.emailtemplate;
  }
  
  public void setEmailtemplate(EmailTemplates emailtemplate)
  {
    this.emailtemplate = emailtemplate;
  }
  
  public String getFunctiontype()
  {
    return this.functiontype;
  }
  
  public void setFunctiontype(String functiontype)
  {
    this.functiontype = functiontype;
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
  
  public String getReadPreview()
  {
    return this.readPreview;
  }
  
  public void setReadPreview(String readPreview)
  {
    this.readPreview = readPreview;
  }
  
  public long getOrgId()
  {
    return this.orgId;
  }
  
  public void setOrgId(long orgId)
  {
    this.orgId = orgId;
  }
  
  public List getOrganizationList()
  {
    return this.organizationList;
  }
  
  public void setOrganizationList(List organizationList)
  {
    this.organizationList = organizationList;
  }
  
  public long getEmailtemplateId()
  {
    return this.emailtemplateId;
  }
  
  public void setEmailtemplateId(long emailtemplateId)
  {
    this.emailtemplateId = emailtemplateId;
  }
  
  public List getEmailtemplateList()
  {
    return this.emailtemplateList;
  }
  
  public void setEmailtemplateList(List emailtemplateList)
  {
    this.emailtemplateList = emailtemplateList;
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
  
  public String getEmailtemplatename()
  {
    return this.emailtemplatename;
  }
  
  public void setEmailtemplatename(String emailtemplatename)
  {
    this.emailtemplatename = emailtemplatename;
  }
  
  public List getOrgemailtempList()
  {
    return this.orgemailtempList;
  }
  
  public void setOrgemailtempList(List orgemailtempList)
  {
    this.orgemailtempList = orgemailtempList;
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
  
  public List getFunctionTypeList()
  {
    return this.functionTypeList;
  }
  
  public void setFunctionTypeList(List functionTypeList)
  {
    this.functionTypeList = functionTypeList;
  }
  
  public String getEmailvaluespan()
  {
    return this.emailvaluespan;
  }
  
  public void setEmailvaluespan(String emailvaluespan)
  {
    this.emailvaluespan = emailvaluespan;
  }
  
  public String getEmailtemplateidval()
  {
    return this.emailtemplateidval;
  }
  
  public void setEmailtemplateidval(String emailtemplateidval)
  {
    this.emailtemplateidval = emailtemplateidval;
  }
  
  public String getEventCode()
  {
    return this.eventCode;
  }
  
  public void setEventCode(String eventCode)
  {
    this.eventCode = eventCode;
  }
  
  public List getOrgemailtempListfordisplay()
  {
    return this.OrgemailtempListfordisplay;
  }
  
  public void setOrgemailtempListfordisplay(List orgemailtempListfordisplay)
  {
    this.OrgemailtempListfordisplay = orgemailtempListfordisplay;
  }
  
  public String getEmailtemplatenameval()
  {
    return this.emailtemplatenameval;
  }
  
  public void setEmailtemplatenameval(String emailtemplatenameval)
  {
    this.emailtemplatenameval = emailtemplatenameval;
  }
}
