package com.form.employee;

import com.bean.CompFrequency;
import com.bean.SalaryPlan;
import com.bean.employee.UserSalary;
import com.util.StringUtils;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;

public class UserSalaryForm
  extends ActionForm
{
  public long userSalaryId;
  public long userId;
  private SalaryPlan salaryplan;
  private String salaryComonents;
  private CompFrequency compfrequency;
  private String amount;
  private String ddamount;
  private String isBankAcountDetails;
  private String bankAcountNumber;
  private String accountType;
  private String accountType2;
  private String accountTypeOther;
  private String bankName;
  private String bankAddress;
  private String bankRoutingNumber;
  private List salaryplanList;
  private List compfrequencyList;
  public long salaryplanId;
  public long compfrequencyId;
  private String compFrequencyName;
  private String salaryPlanName;
  private List userSalaryComponentList;
  private List accountTypeList;
  
  public List getAccountTypeList()
  {
    return this.accountTypeList;
  }
  
  public void setAccountTypeList(List accountTypeList)
  {
    this.accountTypeList = accountTypeList;
  }
  
  public String getAccountType2()
  {
    return this.accountType2;
  }
  
  public void setAccountType2(String accountType2)
  {
    this.accountType2 = accountType2;
  }
  
  public String getDdamount()
  {
    return this.ddamount;
  }
  
  public void setDdamount(String ddamount)
  {
    this.ddamount = ddamount;
  }
  
  public List getUserSalaryComponentList()
  {
    return this.userSalaryComponentList;
  }
  
  public void setUserSalaryComponentList(List userSalaryComponentList)
  {
    this.userSalaryComponentList = userSalaryComponentList;
  }
  
  public String getCompFrequencyName()
  {
    return this.compFrequencyName;
  }
  
  public void setCompFrequencyName(String compFrequencyName)
  {
    this.compFrequencyName = compFrequencyName;
  }
  
  public String getSalaryPlanName()
  {
    return this.salaryPlanName;
  }
  
  public void setSalaryPlanName(String salaryPlanName)
  {
    this.salaryPlanName = salaryPlanName;
  }
  
  public long getSalaryplanId()
  {
    return this.salaryplanId;
  }
  
  public void setSalaryplanId(long salaryplanId)
  {
    this.salaryplanId = salaryplanId;
  }
  
  public long getCompfrequencyId()
  {
    return this.compfrequencyId;
  }
  
  public void setCompfrequencyId(long compfrequencyId)
  {
    this.compfrequencyId = compfrequencyId;
  }
  
  public List getSalaryplanList()
  {
    return this.salaryplanList;
  }
  
  public void setSalaryplanList(List salaryplanList)
  {
    this.salaryplanList = salaryplanList;
  }
  
  public List getCompfrequencyList()
  {
    return this.compfrequencyList;
  }
  
  public void setCompfrequencyList(List compfrequencyList)
  {
    this.compfrequencyList = compfrequencyList;
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
  
  public void fromValue(UserSalary userSalary, HttpServletRequest request)
  {
    this.userSalaryId = userSalary.getUserSalaryId();
    this.amount = userSalary.getAmount();
    this.isBankAcountDetails = userSalary.getIsBankAcountDetails();
    this.salaryComonents = userSalary.getSalaryComonents();
    if (userSalary.getCompfrequency() != null)
    {
      this.compfrequencyId = userSalary.getCompfrequency().getCompFrequencyId();
      this.compFrequencyName = userSalary.getCompfrequency().getCompFrequencyName();
    }
    if (userSalary.getSalaryplan() != null)
    {
      this.salaryplanId = userSalary.getSalaryplan().getSalaryplanId();
      this.salaryPlanName = userSalary.getSalaryplan().getSalaryPlanName();
    }
    if ((!StringUtils.isNullOrEmpty(userSalary.getIsBankAcountDetails())) && (userSalary.getIsBankAcountDetails().equals("Y")))
    {
      this.bankAcountNumber = userSalary.getBankAcountNumber();
      this.accountType = userSalary.getAccountType();
      this.bankName = userSalary.getBankName();
      this.bankAddress = userSalary.getBankAddress();
      this.bankRoutingNumber = userSalary.getBankRoutingNumber();
      
      this.ddamount = userSalary.getDdamount();
      this.accountTypeOther = userSalary.getAccountTypeOther();
    }
  }
  
  public UserSalary toValue(UserSalary userSalary, HttpServletRequest request)
    throws Exception
  {
    userSalary.setUserId(this.userId);
    userSalary.setAmount(this.amount);
    userSalary.setSalaryComonents(this.salaryComonents);
    userSalary.setIsBankAcountDetails(this.isBankAcountDetails);
    if ((!StringUtils.isNullOrEmpty(this.isBankAcountDetails)) && (this.isBankAcountDetails.equals("Y")))
    {
      userSalary.setBankAcountNumber(this.bankAcountNumber);
      userSalary.setAccountType(this.accountType);
      userSalary.setBankName(this.bankName);
      userSalary.setBankAddress(this.bankAddress);
      userSalary.setBankRoutingNumber(this.bankRoutingNumber);
      userSalary.setDdamount(this.ddamount);
      userSalary.setAccountTypeOther(this.accountTypeOther);
    }
    if (this.salaryplanId != 0L)
    {
      SalaryPlan splan = new SalaryPlan();
      splan.setSalaryplanId(this.salaryplanId);
      userSalary.setSalaryplan(splan);
    }
    if (this.compfrequencyId != 0L)
    {
      CompFrequency cmp = new CompFrequency();
      cmp.setCompFrequencyId(this.compfrequencyId);
      userSalary.setCompfrequency(cmp);
    }
    return userSalary;
  }
}
