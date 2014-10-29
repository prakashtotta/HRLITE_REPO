package com.bean.criteria;

import java.util.List;

public class RequisitionTemplateSearchCriteriaMultiple
{
  public long templateId;
  public String templateName;
  private String keyword;
  String jobgradeId;
  String jobtypeId;
  String orgId;
  String departmentIds;
  String status;
  List<String> statusList;
  List<Long> jobgradeIdList;
  List<Long> jobtypeIdList;
  List<Long> orgIdList;
  List<Long> departmentIdsList;
  
  public long getTemplateId()
  {
    return this.templateId;
  }
  
  public void setTemplateId(long templateId)
  {
    this.templateId = templateId;
  }
  
  public String getTemplateName()
  {
    return this.templateName;
  }
  
  public void setTemplateName(String templateName)
  {
    this.templateName = templateName;
  }
  
  public String getKeyword()
  {
    return this.keyword;
  }
  
  public void setKeyword(String keyword)
  {
    this.keyword = keyword;
  }
  
  public String getJobgradeId()
  {
    return this.jobgradeId;
  }
  
  public void setJobgradeId(String jobgradeId)
  {
    this.jobgradeId = jobgradeId;
  }
  
  public String getJobtypeId()
  {
    return this.jobtypeId;
  }
  
  public void setJobtypeId(String jobtypeId)
  {
    this.jobtypeId = jobtypeId;
  }
  
  public String getOrgId()
  {
    return this.orgId;
  }
  
  public void setOrgId(String orgId)
  {
    this.orgId = orgId;
  }
  
  public String getDepartmentIds()
  {
    return this.departmentIds;
  }
  
  public void setDepartmentIds(String departmentIds)
  {
    this.departmentIds = departmentIds;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public List<String> getStatusList()
  {
    return this.statusList;
  }
  
  public void setStatusList(List<String> statusList)
  {
    this.statusList = statusList;
  }
  
  public List<Long> getJobgradeIdList()
  {
    return this.jobgradeIdList;
  }
  
  public void setJobgradeIdList(List<Long> jobgradeIdList)
  {
    this.jobgradeIdList = jobgradeIdList;
  }
  
  public List<Long> getJobtypeIdList()
  {
    return this.jobtypeIdList;
  }
  
  public void setJobtypeIdList(List<Long> jobtypeIdList)
  {
    this.jobtypeIdList = jobtypeIdList;
  }
  
  public List<Long> getOrgIdList()
  {
    return this.orgIdList;
  }
  
  public void setOrgIdList(List<Long> orgIdList)
  {
    this.orgIdList = orgIdList;
  }
  
  public List<Long> getDepartmentIdsList()
  {
    return this.departmentIdsList;
  }
  
  public void setDepartmentIdsList(List<Long> departmentIdsList)
  {
    this.departmentIdsList = departmentIdsList;
  }
}
