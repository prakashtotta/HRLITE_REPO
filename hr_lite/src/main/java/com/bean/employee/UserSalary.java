package com.bean.employee;

import com.bean.CompFrequency;
import com.bean.SalaryPlan;

public class UserSalary
{
  public long userSalaryId;
  public long userId;
  private SalaryPlan salaryplan;
  private String salaryComonents;
  private CompFrequency compfrequency;
  private String amount;
  private String isBankAcountDetails;
  private String bankAcountNumber;
  private String accountType;
  private String accountTypeOther;
  private String bankName;
  private String bankAddress;
  private String bankRoutingNumber;
  private String ddamount;
  
  public String getDdamount()
  {
    return this.ddamount;
  }
  
  public void setDdamount(String ddamount)
  {
    this.ddamount = ddamount;
  }
  
  public long getUserSalaryId()
  {
    return this.userSalaryId;
  }
  
  public void setUserSalaryId(long userSalaryId)
  {
    this.userSalaryId = userSalaryId;
  }
  
  public long getUserId()
  {
    return this.userId;
  }
  
  public void setUserId(long userId)
  {
    this.userId = userId;
  }
  
  public SalaryPlan getSalaryplan()
  {
    return this.salaryplan;
  }
  
  public void setSalaryplan(SalaryPlan salaryplan)
  {
    this.salaryplan = salaryplan;
  }
  
  public String getSalaryComonents()
  {
    return this.salaryComonents;
  }
  
  public void setSalaryComonents(String salaryComonents)
  {
    this.salaryComonents = salaryComonents;
  }
  
  public CompFrequency getCompfrequency()
  {
    return this.compfrequency;
  }
  
  public void setCompfrequency(CompFrequency compfrequency)
  {
    this.compfrequency = compfrequency;
  }
  
  public String getIsBankAcountDetails()
  {
    return this.isBankAcountDetails;
  }
  
  public void setIsBankAcountDetails(String isBankAcountDetails)
  {
    this.isBankAcountDetails = isBankAcountDetails;
  }
  
  public String getBankAcountNumber()
  {
    return this.bankAcountNumber;
  }
  
  public void setBankAcountNumber(String bankAcountNumber)
  {
    this.bankAcountNumber = bankAcountNumber;
  }
  
  public String getAccountType()
  {
    return this.accountType;
  }
  
  public void setAccountType(String accountType)
  {
    this.accountType = accountType;
  }
  
  public String getAccountTypeOther()
  {
    return this.accountTypeOther;
  }
  
  public void setAccountTypeOther(String accountTypeOther)
  {
    this.accountTypeOther = accountTypeOther;
  }
  
  public String getBankName()
  {
    return this.bankName;
  }
  
  public void setBankName(String bankName)
  {
    this.bankName = bankName;
  }
  
  public String getBankAddress()
  {
    return this.bankAddress;
  }
  
  public void setBankAddress(String bankAddress)
  {
    this.bankAddress = bankAddress;
  }
  
  public String getBankRoutingNumber()
  {
    return this.bankRoutingNumber;
  }
  
  public void setBankRoutingNumber(String bankRoutingNumber)
  {
    this.bankRoutingNumber = bankRoutingNumber;
  }
  
  public String getAmount()
  {
    return this.amount;
  }
  
  public void setAmount(String amount)
  {
    this.amount = amount;
  }
}
