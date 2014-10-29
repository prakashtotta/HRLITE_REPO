package com.form;

import java.util.Date;
import java.util.List;
import org.apache.struts.action.ActionForm;

public class SalaryForm
  extends ActionForm
{
  public long salaryplanId;
  public long toRangeAmount;
  public String currencyCode;
  public Date effectiveEndDate;
  public String status;
  public Date effectiveStartDate;
  public String salaryPlanName;
  public String salaryPlanDesc;
  public long fromRangeAmount;
  public String salaryPlanYear;
  public String readPreview;
  private List currencyCodeList;
  List users;
  List permissionsList;
  String[] permission;
  
  public long getSalaryplanId()
  {
    return this.salaryplanId;
  }
  
  public void setSalaryplanId(long salaryplanId)
  {
    this.salaryplanId = salaryplanId;
  }
  
  public long getToRangeAmount()
  {
    return this.toRangeAmount;
  }
  
  public void setToRangeAmount(long toRangeAmount)
  {
    this.toRangeAmount = toRangeAmount;
  }
  
  public String getCurrencyCode()
  {
    return this.currencyCode;
  }
  
  public void setCurrencyCode(String currencyCode)
  {
    this.currencyCode = currencyCode;
  }
  
  public Date getEffectiveEndDate()
  {
    return this.effectiveEndDate;
  }
  
  public void setEffectiveEndDate(Date effectiveEndDate)
  {
    this.effectiveEndDate = effectiveEndDate;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public Date getEffectiveStartDate()
  {
    return this.effectiveStartDate;
  }
  
  public void setEffectiveStartDate(Date effectiveStartDate)
  {
    this.effectiveStartDate = effectiveStartDate;
  }
  
  public String getSalaryPlanName()
  {
    return this.salaryPlanName;
  }
  
  public void setSalaryPlanName(String salaryPlanName)
  {
    this.salaryPlanName = salaryPlanName;
  }
  
  public String getSalaryPlanDesc()
  {
    return this.salaryPlanDesc;
  }
  
  public void setSalaryPlanDesc(String salaryPlanDesc)
  {
    this.salaryPlanDesc = salaryPlanDesc;
  }
  
  public long getFromRangeAmount()
  {
    return this.fromRangeAmount;
  }
  
  public void setFromRangeAmount(long fromRangeAmount)
  {
    this.fromRangeAmount = fromRangeAmount;
  }
  
  public String getSalaryPlanYear()
  {
    return this.salaryPlanYear;
  }
  
  public void setSalaryPlanYear(String salaryPlanYear)
  {
    this.salaryPlanYear = salaryPlanYear;
  }
  
  public List getUsers()
  {
    return this.users;
  }
  
  public void setUsers(List users)
  {
    this.users = users;
  }
  
  public List getPermissionsList()
  {
    return this.permissionsList;
  }
  
  public void setPermissionsList(List permissionsList)
  {
    this.permissionsList = permissionsList;
  }
  
  public String[] getPermission()
  {
    return this.permission;
  }
  
  public void setPermission(String[] permission)
  {
    this.permission = permission;
  }
  
  public String getReadPreview()
  {
    return this.readPreview;
  }
  
  public void setReadPreview(String readPreview)
  {
    this.readPreview = readPreview;
  }
  
  public List getCurrencyCodeList()
  {
    return this.currencyCodeList;
  }
  
  public void setCurrencyCodeList(List currencyCodeList)
  {
    this.currencyCodeList = currencyCodeList;
  }
}
