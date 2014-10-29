package com.bean;

import java.util.Date;

public class ProjectCodes
{
  public long projectId;
  public String projCode;
  public String projName;
  public String projDesc;
  public Department department;
  public String departmentName;
  public Organization organization;
  public String orgName;
  public String status;
  public String notes;
  public String createdBy;
  public Date createdDate;
  public Date updatedDate;
  public String updatedBy;
  public String projectCodeCurrency;
  public Date effectiveStartDate;
  public Date effectiveEndDate;
  
  public long getProjectId()
  {
    return this.projectId;
  }
  
  public void setProjectId(long projectId)
  {
    this.projectId = projectId;
  }
  
  public String getProjCode()
  {
    return this.projCode;
  }
  
  public void setProjCode(String projCode)
  {
    this.projCode = projCode;
  }
  
  public String getProjName()
  {
    return this.projName;
  }
  
  public void setProjName(String projName)
  {
    this.projName = projName;
  }
  
  public String getProjDesc()
  {
    return this.projDesc;
  }
  
  public void setProjDesc(String projDesc)
  {
    this.projDesc = projDesc;
  }
  
  public Department getDepartment()
  {
    return this.department;
  }
  
  public void setDepartment(Department department)
  {
    this.department = department;
    
    this.departmentName = department.getDepartmentName();
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
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
  
  public Organization getOrganization()
  {
    return this.organization;
  }
  
  public void setOrganization(Organization organization)
  {
    this.organization = organization;
    
    this.orgName = organization.getOrgName();
  }
  
  public String getDepartmentName()
  {
    return this.departmentName;
  }
  
  public void setDepartmentName(String departmentName)
  {
    this.departmentName = departmentName;
  }
  
  public String getOrgName()
  {
    return this.orgName;
  }
  
  public void setOrgName(String orgName)
  {
    this.orgName = orgName;
  }
  
  public String getProjectCodeCurrency()
  {
    return this.projectCodeCurrency;
  }
  
  public void setProjectCodeCurrency(String projectCodeCurrency)
  {
    this.projectCodeCurrency = projectCodeCurrency;
  }
}
