package com.bean;

public class OrganizationType
{
  public long organizationTypeId;
  public String lorganizationTypeName;
  public String lorganizationTypeDesc;
  
  public long getOrganizationTypeId()
  {
    return this.organizationTypeId;
  }
  
  public void setOrganizationTypeId(long organizationTypeId)
  {
    this.organizationTypeId = organizationTypeId;
  }
  
  public String getLorganizationTypeName()
  {
    return this.lorganizationTypeName;
  }
  
  public void setLorganizationTypeName(String lorganizationTypeName)
  {
    this.lorganizationTypeName = lorganizationTypeName;
  }
  
  public String getLorganizationTypeDesc()
  {
    return this.lorganizationTypeDesc;
  }
  
  public void setLorganizationTypeDesc(String lorganizationTypeDesc)
  {
    this.lorganizationTypeDesc = lorganizationTypeDesc;
  }
}
