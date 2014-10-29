package com.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Department
  implements Serializable
{
  public long departmentId;
  public String departmentName;
  List users;
  List projectCodes;
  String status;
  public String departmentDesc;
  public String departmentCode;
  private String createdBy;
  private Date createdDate;
  private Date updatedDate;
  private String updatedBy;
  private Date effectiveStartDate;
  private Date effectiveEndDate;
  public String notes;
  private Organization organization;
  public String organizationname;
  
  public long getDepartmentId()
  {
    return this.departmentId;
  }
  
  public void setDepartmentId(long departmentId)
  {
    this.departmentId = departmentId;
  }
  
  public String getDepartmentName()
  {
    return this.departmentName;
  }
  
  public void setDepartmentName(String departmentName)
  {
    this.departmentName = departmentName;
  }
  
  public List getUsers()
  {
    return this.users;
  }
  
  public void setUsers(List users)
  {
    this.users = users;
  }
  
  public List getProjectCodes()
  {
    return this.projectCodes;
  }
  
  public void setProjectCodes(List projectCodes)
  {
    this.projectCodes = projectCodes;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public String getDepartmentDesc()
  {
    return this.departmentDesc;
  }
  
  public void setDepartmentDesc(String departmentDesc)
  {
    this.departmentDesc = departmentDesc;
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
  
  public String getNotes()
  {
    return this.notes;
  }
  
  public void setNotes(String notes)
  {
    this.notes = notes;
  }
  
  public Organization getOrganization()
  {
    return this.organization;
  }
  
  public void setOrganization(Organization organization)
  {
    this.organization = organization;
    if (organization != null) {
      this.organizationname = organization.getOrgName();
    }
  }
  
  public String getDepartmentCode()
  {
    return this.departmentCode;
  }
  
  public void setDepartmentCode(String departmentCode)
  {
    this.departmentCode = departmentCode;
  }
  
  public String getOrganizationname()
  {
    return this.organizationname;
  }
}
