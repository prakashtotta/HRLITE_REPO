package com.form;

import com.bean.Location;
import com.bean.Organization;
import com.bean.OrganizationType;
import com.bo.BOFactory;
import com.bo.LovBO;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;

public class OrganizationForm
  extends ActionForm
{
  public long orgId;
  public String orgCode;
  public String orgName;
  public String taxid;
  public String naics;
  public String isLegal = "";
  public String notes;
  private String createdBy;
  private Date createdDate;
  private Date updatedDate;
  private String updatedBy;
  private Date effectiveStartDate;
  private Date effectiveEndDate;
  private List orgnizationTypeList;
  private String locationName;
  private String parentOrgName;
  private long locationId;
  private long parentOrgId;
  private long organizationTypeId;
  public String readPreview;
  private List locationList;
  public String start;
  public String range;
  public String results;
  private String currencyCode;
  private List currencyCodeList;
  public String organizationtypeStr;
  public String parentOrgStr;
  public String isRoot = "N";
  private Organization organization;
  private List orgList;
  
  public void fromValue(Organization org, HttpServletRequest request)
    throws Exception
  {
    this.orgId = org.getOrgId();
    this.orgCode = org.getOrgCode();
    this.orgName = org.getOrgName();
    this.taxid = org.getTaxid();
    this.naics = org.getNaics();
    this.isLegal = org.getIsLegal();
    this.notes = org.getNotes();
    this.createdBy = org.getCreatedBy();
    this.createdDate = org.getCreatedDate();
    this.updatedBy = org.getUpdatedBy();
    this.updatedDate = org.getUpdatedDate();
    this.effectiveStartDate = org.getEffectiveStartDate();
    this.effectiveEndDate = org.getEffectiveEndDate();
    if (org.getOrganizationtype() != null)
    {
      this.organizationTypeId = org.getOrganizationtype().getOrganizationTypeId();
      this.organizationtypeStr = org.getOrganizationtype().getLorganizationTypeName();
    }
    this.orgnizationTypeList = BOFactory.getLovBO().getOrganizationTypeList();
    if (org.getLocation() != null)
    {
      this.locationId = org.getLocation().getLocationId();
      this.locationName = org.getLocation().getLocationName();
    }
    if (org.getParentOrg() != null)
    {
      this.parentOrgId = org.getParentOrg().getOrgId();
      this.parentOrgName = org.getParentOrg().getOrgName();
    }
    this.currencyCode = org.getCurrencyCode();
    List currencyList = BOFactory.getLovBO().getAllCurrencies();
    setCurrencyCodeList(currencyList);
    
    this.parentOrgStr = org.getParentOrgName();
    this.isRoot = org.getIsRoot();
  }
  
  public void toValue(Organization org, HttpServletRequest request)
    throws Exception
  {
    org.orgId = getOrgId();
    org.orgCode = getOrgCode();
    org.orgName = getOrgName();
    org.taxid = getTaxid();
    org.naics = getNaics();
    
    org.notes = getNotes();
    org.effectiveStartDate = getEffectiveStartDate();
    org.effectiveEndDate = getEffectiveEndDate();
    OrganizationType orgtype = new OrganizationType();
    orgtype.setOrganizationTypeId(getOrganizationTypeId());
    org.setOrganizationtype(orgtype);
    
    Location location1 = new Location();
    location1.setLocationId(this.locationId);
    org.setLocation(location1);
    if (getParentOrgId() != 0L)
    {
      Organization parentorg = new Organization();
      parentorg.setOrgId(getParentOrgId());
      this.organization.setParentOrg(parentorg);
    }
    org.setCurrencyCode(this.currencyCode);
  }
  
  public long getOrgId()
  {
    return this.orgId;
  }
  
  public void setOrgId(long orgId)
  {
    this.orgId = orgId;
  }
  
  public String getOrgCode()
  {
    return this.orgCode;
  }
  
  public void setOrgCode(String orgCode)
  {
    this.orgCode = orgCode;
  }
  
  public String getOrgName()
  {
    return this.orgName;
  }
  
  public void setOrgName(String orgName)
  {
    this.orgName = orgName;
  }
  
  public String getTaxid()
  {
    return this.taxid;
  }
  
  public void setTaxid(String taxid)
  {
    this.taxid = taxid;
  }
  
  public String getNaics()
  {
    return this.naics;
  }
  
  public void setNaics(String naics)
  {
    this.naics = naics;
  }
  
  public String getIsLegal()
  {
    return this.isLegal;
  }
  
  public void setIsLegal(String isLegal)
  {
    this.isLegal = isLegal;
  }
  
  public String getNotes()
  {
    return this.notes;
  }
  
  public void setNotes(String notes)
  {
    this.notes = notes;
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
  
  public Date getEffectiveStartDate()
  {
    return this.effectiveStartDate;
  }
  
  public void setEffectiveStartDate(Date effectiveStartDate)
  {
    this.effectiveStartDate = effectiveStartDate;
  }
  
  public Date getEffectiveEndDate()
  {
    return this.effectiveEndDate;
  }
  
  public void setEffectiveEndDate(Date effectiveEndDate)
  {
    this.effectiveEndDate = effectiveEndDate;
  }
  
  public List getOrgnizationTypeList()
  {
    return this.orgnizationTypeList;
  }
  
  public void setOrgnizationTypeList(List orgnizationTypeList)
  {
    this.orgnizationTypeList = orgnizationTypeList;
  }
  
  public String getLocationName()
  {
    return this.locationName;
  }
  
  public void setLocationName(String locationName)
  {
    this.locationName = locationName;
  }
  
  public String getParentOrgName()
  {
    return this.parentOrgName;
  }
  
  public void setParentOrgName(String parentOrgName)
  {
    this.parentOrgName = parentOrgName;
  }
  
  public long getLocationId()
  {
    return this.locationId;
  }
  
  public void setLocationId(long locationId)
  {
    this.locationId = locationId;
  }
  
  public long getParentOrgId()
  {
    return this.parentOrgId;
  }
  
  public void setParentOrgId(long parentOrgId)
  {
    this.parentOrgId = parentOrgId;
  }
  
  public long getOrganizationTypeId()
  {
    return this.organizationTypeId;
  }
  
  public void setOrganizationTypeId(long organizationTypeId)
  {
    this.organizationTypeId = organizationTypeId;
  }
  
  public List getOrgList()
  {
    return this.orgList;
  }
  
  public void setOrgList(List orgList)
  {
    this.orgList = orgList;
  }
  
  public Organization getOrganization()
  {
    return this.organization;
  }
  
  public void setOrganization(Organization organization)
  {
    this.organization = organization;
  }
  
  public String getReadPreview()
  {
    return this.readPreview;
  }
  
  public void setReadPreview(String readPreview)
  {
    this.readPreview = readPreview;
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
  
  public String getCurrencyCode()
  {
    return this.currencyCode;
  }
  
  public void setCurrencyCode(String currencyCode)
  {
    this.currencyCode = currencyCode;
  }
  
  public List getCurrencyCodeList()
  {
    return this.currencyCodeList;
  }
  
  public void setCurrencyCodeList(List currencyCodeList)
  {
    this.currencyCodeList = currencyCodeList;
  }
  
  public String getOrganizationtypeStr()
  {
    return this.organizationtypeStr;
  }
  
  public void setOrganizationtypeStr(String organizationtypeStr)
  {
    this.organizationtypeStr = organizationtypeStr;
  }
  
  public String getParentOrgStr()
  {
    return this.parentOrgStr;
  }
  
  public void setParentOrgStr(String parentOrgStr)
  {
    this.parentOrgStr = parentOrgStr;
  }
  
  public String getIsRoot()
  {
    return this.isRoot;
  }
  
  public void setIsRoot(String isRoot)
  {
    this.isRoot = isRoot;
  }
  
  public void toValue(Organization organization, OrganizationForm organizationForm)
  {
    if (organizationForm.getOrgId() != 0L) {
      organization.setOrgId(organizationForm.getOrgId());
    }
    organization.setOrgCode(organizationForm.getOrgCode());
    organization.setOrgName(organizationForm.getOrgName());
    organization.setTaxid(organizationForm.getTaxid());
    organization.setNaics(organizationForm.getNaics());
    organization.setIsLegal(organizationForm.getIsLegal());
    organization.setNotes(organizationForm.getNotes());
    if (organizationForm.getLocationId() != 0L)
    {
      Location location = new Location();
      location.setLocationId(organizationForm.getLocationId());
      organization.setLocation(location);
    }
    if (organizationForm.getParentOrgId() != 0L)
    {
      Organization parentorg = new Organization();
      parentorg.setOrgId(organizationForm.getParentOrgId());
      organization.setParentOrg(parentorg);
    }
    OrganizationType orgtype = new OrganizationType();
    orgtype.setOrganizationTypeId(organizationForm.getOrganizationTypeId());
    organization.setOrganizationtype(orgtype);
    organization.setStatus("A");
    
    organization.setCurrencyCode(organizationForm.getCurrencyCode());
  }
}
