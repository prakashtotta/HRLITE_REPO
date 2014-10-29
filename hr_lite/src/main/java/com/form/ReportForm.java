package com.form;

import java.util.List;
import java.util.Set;
import org.apache.struts.action.ActionForm;

public class ReportForm
  extends ActionForm
{
  public long orgId;
  public String orgName;
  public long requitionId;
  public String jobTitle;
  public long designationId;
  public List designationList;
  public List jobGradeList;
  public long jobgradeId;
  public List categoryList;
  public long catId;
  public long jobCategoryId;
  public List hiringMgrList;
  public long userId;
  public long[] orgIds;
  public List organizationList;
  public long departmentId;
  public List departmentList;
  public String departmentName;
  public long projectId;
  public String projName;
  public List projectCodeList;
  public String[] year;
  public List yearList;
  public long[] departmentIds;
  public long[] designationIds;
  public long[] jobgradeIds;
  public long[] catIds;
  public long[] hiringMgrIds;
  public long[] recruiterIds;
  public List vendorList;
  public long[] userIds;
  public Set usersset;
  public long[] requitionIds;
  public long jobreqId;
  public List jobtitleList;
  public long[] budgetIds;
  public long budgetId;
  public String budgetCode;
  public List budgetCodeList;
  public List recruiterList;
  private String fullName;
  
  public long[] getBudgetIds()
  {
    return this.budgetIds;
  }
  
  public void setBudgetIds(long[] budgetIds)
  {
    this.budgetIds = budgetIds;
  }
  
  public long getBudgetId()
  {
    return this.budgetId;
  }
  
  public void setBudgetId(long budgetId)
  {
    this.budgetId = budgetId;
  }
  
  public String getBudgetCode()
  {
    return this.budgetCode;
  }
  
  public void setBudgetCode(String budgetCode)
  {
    this.budgetCode = budgetCode;
  }
  
  public List getBudgetCodeList()
  {
    return this.budgetCodeList;
  }
  
  public void setBudgetCodeList(List budgetCodeList)
  {
    this.budgetCodeList = budgetCodeList;
  }
  
  public long[] getRequitionIds()
  {
    return this.requitionIds;
  }
  
  public void setRequitionIds(long[] requitionIds)
  {
    this.requitionIds = requitionIds;
  }
  
  public long getJobreqId()
  {
    return this.jobreqId;
  }
  
  public void setJobreqId(long jobreqId)
  {
    this.jobreqId = jobreqId;
  }
  
  public List getJobtitleList()
  {
    return this.jobtitleList;
  }
  
  public void setJobtitleList(List jobtitleList)
  {
    this.jobtitleList = jobtitleList;
  }
  
  public long getJobCategoryId()
  {
    return this.jobCategoryId;
  }
  
  public void setJobCategoryId(long jobCategoryId)
  {
    this.jobCategoryId = jobCategoryId;
  }
  
  public Set getUsersset()
  {
    return this.usersset;
  }
  
  public void setUsersset(Set usersset)
  {
    this.usersset = usersset;
  }
  
  public long[] getUserIds()
  {
    return this.userIds;
  }
  
  public void setUserIds(long[] userIds)
  {
    this.userIds = userIds;
  }
  
  public List getVendorList()
  {
    return this.vendorList;
  }
  
  public void setVendorList(List vendorList)
  {
    this.vendorList = vendorList;
  }
  
  public String[] getYear()
  {
    return this.year;
  }
  
  public void setYear(String[] year)
  {
    this.year = year;
  }
  
  public long[] getDesignationIds()
  {
    return this.designationIds;
  }
  
  public void setDesignationIds(long[] designationIds)
  {
    this.designationIds = designationIds;
  }
  
  public long[] getJobgradeIds()
  {
    return this.jobgradeIds;
  }
  
  public void setJobgradeIds(long[] jobgradeIds)
  {
    this.jobgradeIds = jobgradeIds;
  }
  
  public long[] getCatIds()
  {
    return this.catIds;
  }
  
  public void setCatIds(long[] catIds)
  {
    this.catIds = catIds;
  }
  
  public long[] getHiringMgrIds()
  {
    return this.hiringMgrIds;
  }
  
  public void setHiringMgrIds(long[] hiringMgrIds)
  {
    this.hiringMgrIds = hiringMgrIds;
  }
  
  public long[] getRecruiterIds()
  {
    return this.recruiterIds;
  }
  
  public void setRecruiterIds(long[] recruiterIds)
  {
    this.recruiterIds = recruiterIds;
  }
  
  public long[] getDepartmentIds()
  {
    return this.departmentIds;
  }
  
  public void setDepartmentIds(long[] departmentIds)
  {
    this.departmentIds = departmentIds;
  }
  
  public long[] getOrgIds()
  {
    return this.orgIds;
  }
  
  public void setOrgIds(long[] orgIds)
  {
    this.orgIds = orgIds;
  }
  
  public List getYearList()
  {
    return this.yearList;
  }
  
  public void setYearList(List yearList)
  {
    this.yearList = yearList;
  }
  
  public long getProjectId()
  {
    return this.projectId;
  }
  
  public void setProjectId(long projectId)
  {
    this.projectId = projectId;
  }
  
  public String getProjName()
  {
    return this.projName;
  }
  
  public void setProjName(String projName)
  {
    this.projName = projName;
  }
  
  public List getProjectCodeList()
  {
    return this.projectCodeList;
  }
  
  public void setProjectCodeList(List projectCodeList)
  {
    this.projectCodeList = projectCodeList;
  }
  
  public long getDepartmentId()
  {
    return this.departmentId;
  }
  
  public void setDepartmentId(long departmentId)
  {
    this.departmentId = departmentId;
  }
  
  public List getDepartmentList()
  {
    return this.departmentList;
  }
  
  public void setDepartmentList(List departmentList)
  {
    this.departmentList = departmentList;
  }
  
  public String getDepartmentName()
  {
    return this.departmentName;
  }
  
  public void setDepartmentName(String departmentName)
  {
    this.departmentName = departmentName;
  }
  
  public List getOrganizationList()
  {
    return this.organizationList;
  }
  
  public void setOrganizationList(List organizationList)
  {
    this.organizationList = organizationList;
  }
  
  public List getRecruiterList()
  {
    return this.recruiterList;
  }
  
  public void setRecruiterList(List recruiterList)
  {
    this.recruiterList = recruiterList;
  }
  
  public long getUserId()
  {
    return this.userId;
  }
  
  public void setUserId(long userId)
  {
    this.userId = userId;
  }
  
  public String getFullName()
  {
    return this.fullName;
  }
  
  public void setFullName(String fullName)
  {
    this.fullName = fullName;
  }
  
  public List getHiringMgrList()
  {
    return this.hiringMgrList;
  }
  
  public void setHiringMgrList(List hiringMgrList)
  {
    this.hiringMgrList = hiringMgrList;
  }
  
  public List getCategoryList()
  {
    return this.categoryList;
  }
  
  public void setCategoryList(List categoryList)
  {
    this.categoryList = categoryList;
  }
  
  public long getCatId()
  {
    return this.catId;
  }
  
  public void setCatId(long catId)
  {
    this.catId = catId;
  }
  
  public List getJobGradeList()
  {
    return this.jobGradeList;
  }
  
  public void setJobGradeList(List jobGradeList)
  {
    this.jobGradeList = jobGradeList;
  }
  
  public long getJobgradeId()
  {
    return this.jobgradeId;
  }
  
  public void setJobgradeId(long jobgradeId)
  {
    this.jobgradeId = jobgradeId;
  }
  
  public long getDesignationId()
  {
    return this.designationId;
  }
  
  public void setDesignationId(long designationId)
  {
    this.designationId = designationId;
  }
  
  public long getOrgId()
  {
    return this.orgId;
  }
  
  public void setOrgId(long orgId)
  {
    this.orgId = orgId;
  }
  
  public String getOrgName()
  {
    return this.orgName;
  }
  
  public void setOrgName(String orgName)
  {
    this.orgName = orgName;
  }
  
  public long getRequitionId()
  {
    return this.requitionId;
  }
  
  public void setRequitionId(long requitionId)
  {
    this.requitionId = requitionId;
  }
  
  public String getJobTitle()
  {
    return this.jobTitle;
  }
  
  public void setJobTitle(String jobTitle)
  {
    this.jobTitle = jobTitle;
  }
  
  public List getDesignationList()
  {
    return this.designationList;
  }
  
  public void setDesignationList(List designationList)
  {
    this.designationList = designationList;
  }
}
