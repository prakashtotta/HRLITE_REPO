package com.form;

import com.bean.Organization;
import java.util.Date;
import java.util.List;
import org.apache.struts.action.ActionForm;

public class DepartmentForm
  extends ActionForm
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
  public List departmentList;
  private Date effectiveStartDate;
  private Date effectiveEndDate;
  public String notes;
  public Organization organization;
  public long orgId;
  public String readPreview;
  private List orgnizationList;
  private String orgName;
  public String start;
  public String range;
  public String results;
  
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
  
  public String getDepartmentCode()
  {
    return this.departmentCode;
  }
  
  public void setDepartmentCode(String departmentCode)
  {
    this.departmentCode = departmentCode;
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
  
  public long getOrgId()
  {
    return this.orgId;
  }
  
  public void setOrgId(long orgId)
  {
    this.orgId = orgId;
  }
  
  public String getReadPreview()
  {
    return this.readPreview;
  }
  
  public void setReadPreview(String readPreview)
  {
    this.readPreview = readPreview;
  }
  
  public List getOrgnizationList()
  {
    return this.orgnizationList;
  }
  
  public void setOrgnizationList(List orgnizationList)
  {
    this.orgnizationList = orgnizationList;
  }
  
  public List getDepartmentList()
  {
    return this.departmentList;
  }
  
  public void setDepartmentList(List departmentList)
  {
    this.departmentList = departmentList;
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
}
