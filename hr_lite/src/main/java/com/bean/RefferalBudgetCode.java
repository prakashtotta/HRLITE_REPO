package com.bean;

public class RefferalBudgetCode
{
  public long ref_budgetId;
  public String ref_budgetCode;
  public String ref_budgetCentreName;
  public String ref_budgetCentreDesc;
  public String ref_budgetMonth;
  public String ref_budgetQuarter;
  public int ref_budgetYear;
  public String status;
  public Organization org;
  public Department department;
  public String orgName;
  public String deptName = "";
  public long ref_budgetamount;
  public String ref_budgetCurrency = "";
  public long super_user_key;
  
  public long getRef_budgetId()
  {
    return this.ref_budgetId;
  }
  
  public void setRef_budgetId(long refBudgetId)
  {
    this.ref_budgetId = refBudgetId;
  }
  
  public String getRef_budgetCode()
  {
    return this.ref_budgetCode;
  }
  
  public void setRef_budgetCode(String refBudgetCode)
  {
    this.ref_budgetCode = refBudgetCode;
  }
  
  public String getRef_budgetCentreName()
  {
    return this.ref_budgetCentreName;
  }
  
  public void setRef_budgetCentreName(String refBudgetCentreName)
  {
    this.ref_budgetCentreName = refBudgetCentreName;
  }
  
  public String getRef_budgetCentreDesc()
  {
    return this.ref_budgetCentreDesc;
  }
  
  public void setRef_budgetCentreDesc(String refBudgetCentreDesc)
  {
    this.ref_budgetCentreDesc = refBudgetCentreDesc;
  }
  
  public String getRef_budgetMonth()
  {
    return this.ref_budgetMonth;
  }
  
  public void setRef_budgetMonth(String refBudgetMonth)
  {
    this.ref_budgetMonth = refBudgetMonth;
  }
  
  public String getRef_budgetQuarter()
  {
    return this.ref_budgetQuarter;
  }
  
  public void setRef_budgetQuarter(String refBudgetQuarter)
  {
    this.ref_budgetQuarter = refBudgetQuarter;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public Organization getOrg()
  {
    return this.org;
  }
  
  public void setOrg(Organization org)
  {
    this.org = org;
    this.orgName = org.getOrgName();
  }
  
  public Department getDepartment()
  {
    return this.department;
  }
  
  public void setDepartment(Department department)
  {
    this.department = department;
    if (department != null) {
      this.deptName = department.getDepartmentName();
    }
  }
  
  public long getRef_budgetamount()
  {
    return this.ref_budgetamount;
  }
  
  public void setRef_budgetamount(long refBudgetamount)
  {
    this.ref_budgetamount = refBudgetamount;
  }
  
  public String getRef_budgetCurrency()
  {
    return this.ref_budgetCurrency;
  }
  
  public void setRef_budgetCurrency(String refBudgetCurrency)
  {
    this.ref_budgetCurrency = refBudgetCurrency;
  }
  
  public String getOrgName()
  {
    return this.orgName;
  }
  
  public void setOrgName(String orgName)
  {
    this.orgName = orgName;
  }
  
  public String getDeptName()
  {
    return this.deptName;
  }
  
  public void setDeptName(String deptName)
  {
    this.deptName = deptName;
  }
  
  public int getRef_budgetYear()
  {
    return this.ref_budgetYear;
  }
  
  public void setRef_budgetYear(int refBudgetYear)
  {
    this.ref_budgetYear = refBudgetYear;
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
