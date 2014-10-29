package com.bean.criteria;

import java.util.List;

public class RequisitionSearchCriteriaMultiple
{
  long jobreqId;
  long requisition_number;
  String jobreqName;
  String jobreqcode;
  String status;
  String state;
  String jobgradeId;
  String jobtypeId;
  String orgId;
  String departmentIds;
  String budgetcodeId;
  String appliedCri;
  String searchposteddate;
  String jobTitle;
  String locationId;
  public int minyearsofExpRequired;
  String primarySkill;
  String categoryId;
  String workshiftId;
  String searchType;
  String keyword;
  public String isinternal;
  public String isnewposition;
  public String ispublishredtoexternal;
  public String ispublishredtoemployee;
  List<String> statusList;
  List<String> stateList;
  List<Long> jobgradeIdList;
  List<Long> jobtypeIdList;
  List<Long> orgIdList;
  List<Long> departmentIdsList;
  List<Long> budgetcodeIdList;
  List<Long> locationIdList;
  List<Long> categoryIdList;
  List<Long> workshiftIdList;
  List<String> primarySkillList;
  List<SearchCustomFields> customFieldCriList;
  
  public long getJobreqId()
  {
    return this.jobreqId;
  }
  
  public void setJobreqId(long jobreqId)
  {
    this.jobreqId = jobreqId;
  }
  
  public String getJobreqName()
  {
    return this.jobreqName;
  }
  
  public void setJobreqName(String jobreqName)
  {
    this.jobreqName = jobreqName;
  }
  
  public String getJobreqcode()
  {
    return this.jobreqcode;
  }
  
  public void setJobreqcode(String jobreqcode)
  {
    this.jobreqcode = jobreqcode;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public String getState()
  {
    return this.state;
  }
  
  public void setState(String state)
  {
    this.state = state;
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
  
  public String getBudgetcodeId()
  {
    return this.budgetcodeId;
  }
  
  public void setBudgetcodeId(String budgetcodeId)
  {
    this.budgetcodeId = budgetcodeId;
  }
  
  public List<String> getStatusList()
  {
    return this.statusList;
  }
  
  public void setStatusList(List<String> statusList)
  {
    this.statusList = statusList;
  }
  
  public List<String> getStateList()
  {
    return this.stateList;
  }
  
  public void setStateList(List<String> stateList)
  {
    this.stateList = stateList;
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
  
  public List<Long> getBudgetcodeIdList()
  {
    return this.budgetcodeIdList;
  }
  
  public void setBudgetcodeIdList(List<Long> budgetcodeIdList)
  {
    this.budgetcodeIdList = budgetcodeIdList;
  }
  
  public String getAppliedCri()
  {
    return this.appliedCri;
  }
  
  public void setAppliedCri(String appliedCri)
  {
    this.appliedCri = appliedCri;
  }
  
  public String getSearchposteddate()
  {
    return this.searchposteddate;
  }
  
  public void setSearchposteddate(String searchposteddate)
  {
    this.searchposteddate = searchposteddate;
  }
  
  public List<Long> getLocationIdList()
  {
    return this.locationIdList;
  }
  
  public void setLocationIdList(List<Long> locationIdList)
  {
    this.locationIdList = locationIdList;
  }
  
  public String getJobTitle()
  {
    return this.jobTitle;
  }
  
  public void setJobTitle(String jobTitle)
  {
    this.jobTitle = jobTitle;
  }
  
  public String getLocationId()
  {
    return this.locationId;
  }
  
  public void setLocationId(String locationId)
  {
    this.locationId = locationId;
  }
  
  public int getMinyearsofExpRequired()
  {
    return this.minyearsofExpRequired;
  }
  
  public void setMinyearsofExpRequired(int minyearsofExpRequired)
  {
    this.minyearsofExpRequired = minyearsofExpRequired;
  }
  
  public String getPrimarySkill()
  {
    return this.primarySkill;
  }
  
  public void setPrimarySkill(String primarySkill)
  {
    this.primarySkill = primarySkill;
  }
  
  public String getCategoryId()
  {
    return this.categoryId;
  }
  
  public void setCategoryId(String categoryId)
  {
    this.categoryId = categoryId;
  }
  
  public String getWorkshiftId()
  {
    return this.workshiftId;
  }
  
  public void setWorkshiftId(String workshiftId)
  {
    this.workshiftId = workshiftId;
  }
  
  public List<Long> getCategoryIdList()
  {
    return this.categoryIdList;
  }
  
  public void setCategoryIdList(List<Long> categoryIdList)
  {
    this.categoryIdList = categoryIdList;
  }
  
  public List<Long> getWorkshiftIdList()
  {
    return this.workshiftIdList;
  }
  
  public void setWorkshiftIdList(List<Long> workshiftIdList)
  {
    this.workshiftIdList = workshiftIdList;
  }
  
  public List<String> getPrimarySkillList()
  {
    return this.primarySkillList;
  }
  
  public void setPrimarySkillList(List<String> primarySkillList)
  {
    this.primarySkillList = primarySkillList;
  }
  
  public String getSearchType()
  {
    return this.searchType;
  }
  
  public void setSearchType(String searchType)
  {
    this.searchType = searchType;
  }
  
  public String getKeyword()
  {
    return this.keyword;
  }
  
  public void setKeyword(String keyword)
  {
    this.keyword = keyword;
  }
  
  public long getRequisition_number()
  {
    return this.requisition_number;
  }
  
  public void setRequisition_number(long requisitionNumber)
  {
    this.requisition_number = requisitionNumber;
  }
  
  public String getIsinternal()
  {
    return this.isinternal;
  }
  
  public void setIsinternal(String isinternal)
  {
    this.isinternal = isinternal;
  }
  
  public String getIsnewposition()
  {
    return this.isnewposition;
  }
  
  public void setIsnewposition(String isnewposition)
  {
    this.isnewposition = isnewposition;
  }
  
  public String getIspublishredtoexternal()
  {
    return this.ispublishredtoexternal;
  }
  
  public void setIspublishredtoexternal(String ispublishredtoexternal)
  {
    this.ispublishredtoexternal = ispublishredtoexternal;
  }
  
  public String getIspublishredtoemployee()
  {
    return this.ispublishredtoemployee;
  }
  
  public void setIspublishredtoemployee(String ispublishredtoemployee)
  {
    this.ispublishredtoemployee = ispublishredtoemployee;
  }
  
  public List<SearchCustomFields> getCustomFieldCriList()
  {
    return this.customFieldCriList;
  }
  
  public void setCustomFieldCriList(List<SearchCustomFields> customFieldCriList)
  {
    this.customFieldCriList = customFieldCriList;
  }
}
