package com.form;

import java.util.List;
import org.apache.struts.action.ActionForm;

public class BudgetCodeForm
  extends ActionForm
{
  public long budgetId;
  public String budgetCode;
  public String budgetCentreName;
  public String budgetCentreDesc;
  public String budgetMonth;
  public String budgetQuarter;
  public String budgetYear;
  public String status;
  public String readPreview;
  public List yearsList;
  public long orgId;
  public long departmentId;
  public List organizationList;
  public List departmentList;
  public long budgetamount;
  public String budgetCurrency;
  public String departmentStr;
  public String organizationStr;
  
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
  
  public String getBudgetYear()
  {
    return this.budgetYear;
  }
  
  public void setBudgetYear(String budgetYear)
  {
    this.budgetYear = budgetYear;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public String getReadPreview()
  {
    return this.readPreview;
  }
  
  public void setReadPreview(String readPreview)
  {
    this.readPreview = readPreview;
  }
  
  public List getYearsList()
  {
    return this.yearsList;
  }
  
  public void setYearsList(List yearsList)
  {
    this.yearsList = yearsList;
  }
  
  public long getOrgId()
  {
    return this.orgId;
  }
  
  public void setOrgId(long orgId)
  {
    this.orgId = orgId;
  }
  
  public long getDepartmentId()
  {
    return this.departmentId;
  }
  
  public void setDepartmentId(long departmentId)
  {
    this.departmentId = departmentId;
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
  
  public String getDepartmentStr()
  {
    return this.departmentStr;
  }
  
  public void setDepartmentStr(String departmentStr)
  {
    this.departmentStr = departmentStr;
  }
  
  public String getOrganizationStr()
  {
    return this.organizationStr;
  }
  
  public void setOrganizationStr(String organizationStr)
  {
    this.organizationStr = organizationStr;
  }
}
