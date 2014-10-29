package com.bean;

import java.util.Date;

public class RequistionExpenses
{
  public long expenseId;
  public JobRequisition jobRequision;
  public JobApplicant applicant;
  public String voucherId;
  public User voucherOwner;
  public Date expenseDate;
  public double totalamount;
  public String currencyCode;
  public ExpenseTypes expenseType;
  public String createdBy;
  private Date createdDate;
  public String note;
  
  public long getExpenseId()
  {
    return this.expenseId;
  }
  
  public void setExpenseId(long expenseId)
  {
    this.expenseId = expenseId;
  }
  
  public JobRequisition getJobRequision()
  {
    return this.jobRequision;
  }
  
  public void setJobRequision(JobRequisition jobRequision)
  {
    this.jobRequision = jobRequision;
  }
  
  public JobApplicant getApplicant()
  {
    return this.applicant;
  }
  
  public void setApplicant(JobApplicant applicant)
  {
    this.applicant = applicant;
  }
  
  public String getVoucherId()
  {
    return this.voucherId;
  }
  
  public void setVoucherId(String voucherId)
  {
    this.voucherId = voucherId;
  }
  
  public User getVoucherOwner()
  {
    return this.voucherOwner;
  }
  
  public void setVoucherOwner(User voucherOwner)
  {
    this.voucherOwner = voucherOwner;
  }
  
  public Date getExpenseDate()
  {
    return this.expenseDate;
  }
  
  public void setExpenseDate(Date expenseDate)
  {
    this.expenseDate = expenseDate;
  }
  
  public double getTotalamount()
  {
    return this.totalamount;
  }
  
  public void setTotalamount(double totalamount)
  {
    this.totalamount = totalamount;
  }
  
  public String getCurrencyCode()
  {
    return this.currencyCode;
  }
  
  public void setCurrencyCode(String currencyCode)
  {
    this.currencyCode = currencyCode;
  }
  
  public ExpenseTypes getExpenseType()
  {
    return this.expenseType;
  }
  
  public void setExpenseType(ExpenseTypes expenseType)
  {
    this.expenseType = expenseType;
  }
  
  public String getCreatedBy()
  {
    return this.createdBy;
  }
  
  public void setCreatedBy(String createdBy)
  {
    this.createdBy = createdBy;
  }
  
  public Date getCreatedDate()
  {
    return this.createdDate;
  }
  
  public void setCreatedDate(Date createdDate)
  {
    this.createdDate = createdDate;
  }
  
  public String getNote()
  {
    return this.note;
  }
  
  public void setNote(String note)
  {
    this.note = note;
  }
}
