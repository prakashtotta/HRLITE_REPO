package com.bean;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class SalaryPlan
{
  public long salaryplanId;
  public String salaryPlanName;
  public String salaryPlanDesc;
  public long fromRangeAmount;
  public long toRangeAmount;
  public String currencyCode;
  public String status;
  public Date effectiveStartDate;
  public Date effectiveEndDate;
  public String salaryPlanYear;
  public String readPreview;
  public long super_user_key;
  List users;
  Set permissions;
  
  public long getSalaryplanId()
  {
    return this.salaryplanId;
  }
  
  public void setSalaryplanId(long salaryplanId)
  {
    this.salaryplanId = salaryplanId;
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
  
  public Date getEffectiveEndDate()
  {
    return this.effectiveEndDate;
  }
  
  public void setEffectiveEndDate(Date effectiveEndDate)
  {
    this.effectiveEndDate = effectiveEndDate;
  }
  
  public String getSalaryPlanYear()
  {
    return this.salaryPlanYear;
  }
  
  public void setSalaryPlanYear(String salaryPlanYear)
  {
    this.salaryPlanYear = salaryPlanYear;
  }
  
  public String getReadPreview()
  {
    return this.readPreview;
  }
  
  public void setReadPreview(String readPreview)
  {
    this.readPreview = readPreview;
  }
  
  public List getUsers()
  {
    return this.users;
  }
  
  public void setUsers(List users)
  {
    this.users = users;
  }
  
  public Set getPermissions()
  {
    return this.permissions;
  }
  
  public void setPermissions(Set permissions)
  {
    this.permissions = permissions;
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
