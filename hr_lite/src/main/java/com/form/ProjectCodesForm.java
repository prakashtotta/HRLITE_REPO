package com.form;

import com.bean.Department;
import com.bean.Organization;
import java.util.Date;
import java.util.List;
import org.apache.struts.action.ActionForm;

public class ProjectCodesForm
  extends ActionForm
{
  public long projectId;
  public String projCode;
  public String projName;
  public String projDesc;
  public String status;
  public String notes;
  public String createdBy;
  public Date createdDate;
  public Date updatedDate;
  public String updatedBy;
  public long departmentId;
  public long orgId;
  public List organizationList;
  public List departmentList;
  public String readPreview;
  public String projectCodeCurrency;
  public String effectiveStartDate = "";
  public String effectiveEndDate = "";
  public Organization organization;
  public Department department;
  public String departmentName;
  private String orgName;
  
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
  
  public String getReadPreview()
  {
    return this.readPreview;
  }
  
  public void setReadPreview(String readPreview)
  {
    this.readPreview = readPreview;
  }
  
  public long getDepartmentId()
  {
    return this.departmentId;
  }
  
  public void setDepartmentId(long departmentId)
  {
    this.departmentId = departmentId;
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
  
  public List getDepartmentList()
  {
    return this.departmentList;
  }
  
  public void setDepartmentList(List departmentList)
  {
    this.departmentList = departmentList;
  }
  
  public String getProjectCodeCurrency()
  {
    return this.projectCodeCurrency;
  }
  
  public void setProjectCodeCurrency(String projectCodeCurrency)
  {
    this.projectCodeCurrency = projectCodeCurrency;
  }
  
  public String getEffectiveStartDate()
  {
    return this.effectiveStartDate;
  }
  
  public void setEffectiveStartDate(String effectiveStartDate)
  {
    this.effectiveStartDate = effectiveStartDate;
  }
  
  public String getEffectiveEndDate()
  {
    return this.effectiveEndDate;
  }
  
  public void setEffectiveEndDate(String effectiveEndDate)
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
  
  public String getOrgName()
  {
    return this.orgName;
  }
  
  public void setOrgName(String orgName)
  {
    this.orgName = orgName;
  }
  
  public String getDepartmentName()
  {
    return this.departmentName;
  }
  
  public void setDepartmentName(String departmentName)
  {
    this.departmentName = departmentName;
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
}
