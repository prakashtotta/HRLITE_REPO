package com.form;

import com.bean.lov.LicenseType;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;

public class LicenseTypeForm
  extends ActionForm
{
  public long licenseTypeId;
  public String licenseTypeName;
  public String licenseTypeDesc;
  public String status;
  
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
  
  public LicenseType toValue(LicenseType licenseType, HttpServletRequest request)
    throws Exception
  {
    licenseType.setLicenseTypeName(this.licenseTypeName);
    licenseType.setLicenseTypeDesc(this.licenseTypeDesc);
    return licenseType;
  }
  
  public void fromValue(LicenseType licenseType, HttpServletRequest request)
    throws Exception
  {
    this.licenseTypeId = licenseType.getLicenseTypeId();
    this.licenseTypeName = licenseType.getLicenseTypeName();
    this.licenseTypeDesc = licenseType.getLicenseTypeDesc();
  }
}
