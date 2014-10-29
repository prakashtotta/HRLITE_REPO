package com.bean;

public class BudgetCode
{
  public long budgetId;
  public String budgetCode;
  public String budgetCentreName;
  public String budgetCentreDesc;
  public String budgetMonth;
  public String budgetQuarter;
  public int budgetYear;
  public String status;
  public Organization org;
  public Department department;
  public String orgName;
  public String deptName;
  public long budgetamount;
  public String budgetCurrency;
  public long super_user_key;
  
  public String getBudgetCode()
  {
    return this.budgetCode;
  }
  
  public void setBudgetCode(String budgetCode)
  {
    this.budgetCode = budgetCode;
  }
  
  public String getBudgetCentreName()
  {
    return this.budgetCentreName;
  }
  
  public void setBudgetCentreName(String budgetCentreName)
  {
    this.budgetCentreName = budgetCentreName;
  }
  
  public String getBudgetCentreDesc()
  {
    return this.budgetCentreDesc;
  }
  
  public void setBudgetCentreDesc(String budgetCentreDesc)
  {
    this.budgetCentreDesc = budgetCentreDesc;
  }
  
  public String getBudgetMonth()
  {
    return this.budgetMonth;
  }
  
  public void setBudgetMonth(String budgetMonth)
  {
    this.budgetMonth = budgetMonth;
  }
  
  public String getBudgetQuarter()
  {
    return this.budgetQuarter;
  }
  
  public void setBudgetQuarter(String budgetQuarter)
  {
    this.budgetQuarter = budgetQuarter;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public long getBudgetId()
  {
    return this.budgetId;
  }
  
  public void setBudgetId(long budgetId)
  {
    this.budgetId = budgetId;
  }
  
  public int getBudgetYear()
  {
    return this.budgetYear;
  }
  
  public void setBudgetYear(int budgetYear)
  {
    this.budgetYear = budgetYear;
  }
  
  public String getOrgName()
  {
    return this.orgName;
  }
  
  public String getDeptName()
  {
    return this.deptName;
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
    } else {
      this.deptName = "";
    }
  }
  
  public long getBudgetamount()
  {
    return this.budgetamount;
  }
  
  public void setBudgetamount(long budgetamount)
  {
    this.budgetamount = budgetamount;
  }
  
  public String getBudgetCurrency()
  {
    return this.budgetCurrency;
  }
  
  public void setBudgetCurrency(String budgetCurrency)
  {
    this.budgetCurrency = budgetCurrency;
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
