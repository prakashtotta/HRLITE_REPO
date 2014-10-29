package com.form;

import java.util.List;
import org.apache.struts.action.ActionForm;

public class RefferalBudgetCodeForm
  extends ActionForm
{
  public long ref_budgetId;
  public String ref_budgetCode;
  public String ref_budgetCentreName;
  public String ref_budgetCentreDesc;
  public String ref_budgetMonth;
  public String ref_budgetQuarter;
  public String ref_budgetYear;
  public String status;
  public String readPreview;
  public List yearsList;
  public long orgId;
  public long departmentId;
  public List organizationList;
  public List departmentList;
  public long ref_budgetamount;
  public String ref_budgetCurrency;
  public String rule;
  
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
  
  public String getRef_budgetYear()
  {
    return this.ref_budgetYear;
  }
  
  public void setRef_budgetYear(String refBudgetYear)
  {
    this.ref_budgetYear = refBudgetYear;
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
  
  public String getRule()
  {
    return this.rule;
  }
  
  public void setRule(String rule)
  {
    this.rule = rule;
  }
}
