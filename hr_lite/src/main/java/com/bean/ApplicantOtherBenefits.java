package com.bean;

public class ApplicantOtherBenefits
{
  public long otherBenefitId;
  public long applicantId;
  public String name;
  public double amount;
  public String currencyCode;
  
  public long getOtherBenefitId()
  {
    return this.otherBenefitId;
  }
  
  public void setOtherBenefitId(long otherBenefitId)
  {
    this.otherBenefitId = otherBenefitId;
  }
  
  public long getApplicantId()
  {
    return this.applicantId;
  }
  
  public void setApplicantId(long applicantId)
  {
    this.applicantId = applicantId;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public double getAmount()
  {
    return this.amount;
  }
  
  public void setAmount(double amount)
  {
    this.amount = amount;
  }
  
  public String getCurrencyCode()
  {
    return this.currencyCode;
  }
  
  public void setCurrencyCode(String currencyCode)
  {
    this.currencyCode = currencyCode;
  }
}
