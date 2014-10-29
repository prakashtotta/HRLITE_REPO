package com.bean;

public class Currency
{
  private int currencyId;
  private String currencyCode;
  private String currencyName;
  private String status;
  
  public int getCurrencyId()
  {
    return this.currencyId;
  }
  
  public void setCurrencyId(int currencyId)
  {
    this.currencyId = currencyId;
  }
  
  public String getCurrencyCode()
  {
    return this.currencyCode;
  }
  
  public void setCurrencyCode(String currencyCode)
  {
    this.currencyCode = currencyCode;
  }
  
  public String getCurrencyName()
  {
    return this.currencyName;
  }
  
  public void setCurrencyName(String currencyName)
  {
    this.currencyName = currencyName;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
}
