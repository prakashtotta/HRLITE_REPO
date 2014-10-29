package com.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Organization
  implements Serializable
{
  public long orgId;
  public String orgCode;
  public String orgName;
  public String taxid;
  public String naics;
  public Location location;
  public String isLegal;
  public String notes;
  public String createdBy;
  public Date createdDate;
  public Date updatedDate;
  public String updatedBy;
  public String status;
  public Date effectiveStartDate;
  public Date effectiveEndDate;
  private OrganizationType organizationtype;
  public String locationValue;
  public String locationCodeValue;
  public String organizationTypeValue;
  public long locationId;
  public long parent_org_id;
  private Organization parentOrg;
  public String parentOrgName;
  private List deptlist;
  public String currencyCode;
  public String isRoot = "N";
  public long super_user_key;
  
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
  
  public Location getLocation()
  {
    return this.location;
  }
  
  public void setLocation(Location location)
  {
    this.location = location;
    if (location != null)
    {
      this.locationValue = location.getLocationName();
      this.locationCodeValue = location.getLocationCode();
    }
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
  
  public OrganizationType getOrganizationtype()
  {
    return this.organizationtype;
  }
  
  public void setOrganizationtype(OrganizationType organizationtype)
  {
    this.organizationtype = organizationtype;
    if (organizationtype != null) {
      this.organizationTypeValue = organizationtype.getLorganizationTypeName();
    }
  }
  
  public List getDeptlist()
  {
    return this.deptlist;
  }
  
  public void setDeptlist(List deptlist)
  {
    this.deptlist = deptlist;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public String getParentOrgName()
  {
    return this.parentOrgName;
  }
  
  public long getLocationId()
  {
    return this.locationId;
  }
  
  public void setLocationId(long locationId)
  {
    this.locationId = locationId;
  }
  
  public String getCurrencyCode()
  {
    return this.currencyCode;
  }
  
  public void setCurrencyCode(String currencyCode)
  {
    this.currencyCode = currencyCode;
  }
  
  public Organization getParentOrg()
  {
    return this.parentOrg;
  }
  
  public void setParentOrg(Organization parentOrg)
  {
    this.parentOrg = parentOrg;
    if (parentOrg != null)
    {
      this.parent_org_id = parentOrg.getOrgId();
      this.parentOrgName = parentOrg.getOrgName();
    }
  }
  
  public long getParent_org_id()
  {
    return this.parent_org_id;
  }
  
  public String getIsRoot()
  {
    return this.isRoot;
  }
  
  public void setIsRoot(String isRoot)
  {
    this.isRoot = isRoot;
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
