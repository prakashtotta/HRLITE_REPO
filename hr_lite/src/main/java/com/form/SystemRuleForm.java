package com.form;

import java.util.Date;
import java.util.List;
import org.apache.struts.action.ActionForm;

public class SystemRuleForm
  extends ActionForm
{
  public long systemRuleId;
  public String ruleName;
  public String ruleDesc;
  public long orgId;
  public long deptId;
  public String deptName;
  private String createdBy;
  private Date createdDate;
  private Date updatedDate;
  private String updatedBy;
  private String ruleType;
  private String status;
  private String publishToEmpRef;
  private String publishToExternal;
  private String eeoInfoIncluded;
  private List ruleUsers;
  public List organizationList;
  public List departmentList;
  public List ruletypeList;
  public String displaycheckbox;
  public String idlistvaluser;
  
  public String getIdlistvaluser()
  {
    return this.idlistvaluser;
  }
  
  public void setIdlistvaluser(String idlistvaluser)
  {
    this.idlistvaluser = idlistvaluser;
  }
  
  public long getSystemRuleId()
  {
    return this.systemRuleId;
  }
  
  public void setSystemRuleId(long systemRuleId)
  {
    this.systemRuleId = systemRuleId;
  }
  
  public String getRuleName()
  {
    return this.ruleName;
  }
  
  public void setRuleName(String ruleName)
  {
    this.ruleName = ruleName;
  }
  
  public String getRuleDesc()
  {
    return this.ruleDesc;
  }
  
  public void setRuleDesc(String ruleDesc)
  {
    this.ruleDesc = ruleDesc;
  }
  
  public long getOrgId()
  {
    return this.orgId;
  }
  
  public void setOrgId(long orgId)
  {
    this.orgId = orgId;
  }
  
  public long getDeptId()
  {
    return this.deptId;
  }
  
  public void setDeptId(long deptId)
  {
    this.deptId = deptId;
  }
  
  public String getDeptName()
  {
    return this.deptName;
  }
  
  public void setDeptName(String deptName)
  {
    this.deptName = deptName;
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
  
  public String getRuleType()
  {
    return this.ruleType;
  }
  
  public void setRuleType(String ruleType)
  {
    this.ruleType = ruleType;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public String getPublishToEmpRef()
  {
    return this.publishToEmpRef;
  }
  
  public void setPublishToEmpRef(String publishToEmpRef)
  {
    this.publishToEmpRef = publishToEmpRef;
  }
  
  public String getPublishToExternal()
  {
    return this.publishToExternal;
  }
  
  public void setPublishToExternal(String publishToExternal)
  {
    this.publishToExternal = publishToExternal;
  }
  
  public List getRuleUsers()
  {
    return this.ruleUsers;
  }
  
  public void setRuleUsers(List ruleUsers)
  {
    this.ruleUsers = ruleUsers;
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
  
  public List getRuletypeList()
  {
    return this.ruletypeList;
  }
  
  public void setRuletypeList(List ruletypeList)
  {
    this.ruletypeList = ruletypeList;
  }
  
  public String getDisplaycheckbox()
  {
    return this.displaycheckbox;
  }
  
  public void setDisplaycheckbox(String displaycheckbox)
  {
    this.displaycheckbox = displaycheckbox;
  }
  
  public String getEeoInfoIncluded()
  {
    return this.eeoInfoIncluded;
  }
  
  public void setEeoInfoIncluded(String eeoInfoIncluded)
  {
    this.eeoInfoIncluded = eeoInfoIncluded;
  }
}
