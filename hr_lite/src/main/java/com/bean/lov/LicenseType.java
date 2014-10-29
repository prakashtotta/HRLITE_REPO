package com.bean.lov;

public class LicenseType
{
  public long licenseTypeId;
  public String licenseTypeName;
  public String licenseTypeDesc;
  public String status;
  public long super_user_key;
  
  public long getLicenseTypeId()
  {
    return this.licenseTypeId;
  }
  
  public void setLicenseTypeId(long licenseTypeId)
  {
    this.licenseTypeId = licenseTypeId;
  }
  
  public String getLicenseTypeName()
  {
    return this.licenseTypeName;
  }
  
  public void setLicenseTypeName(String licenseTypeName)
  {
    this.licenseTypeName = licenseTypeName;
  }
  
  public String getLicenseTypeDesc()
  {
    return this.licenseTypeDesc;
  }
  
  public void setLicenseTypeDesc(String licenseTypeDesc)
  {
    this.licenseTypeDesc = licenseTypeDesc;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
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
