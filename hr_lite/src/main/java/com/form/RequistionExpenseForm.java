package com.form;

import java.util.Date;
import java.util.List;
import org.apache.struts.action.ActionForm;

public class RequistionExpenseForm
  extends ActionForm
{
  public long expenseId;
  public long jobReqId;
  public String jobTitle;
  public long applicantId;
  public String applicantName;
  public String voucherId;
  public long voucherOwnerId;
  public String voucherOwnerName;
  public String expenseDate = "";
  public double totalamount;
  public String currencyCode;
  public long expenseTypesId;
  public String expenseTypesName;
  public String createdBy;
  private Date createdDate;
  public String note;
  List expenseTypeList;
  List expensesList;
  
  public long getExpenseId()
  {
    return this.expenseId;
  }
  
  public void setExpenseId(long expenseId)
  {
    this.expenseId = expenseId;
  }
  
  public long getJobReqId()
  {
    return this.jobReqId;
  }
  
  public void setJobReqId(long jobReqId)
  {
    this.jobReqId = jobReqId;
  }
  
  public String getJobTitle()
  {
    return this.jobTitle;
  }
  
  public void setJobTitle(String jobTitle)
  {
    this.jobTitle = jobTitle;
  }
  
  public long getApplicantId()
  {
    return this.applicantId;
  }
  
  public void setApplicantId(long applicantId)
  {
    this.applicantId = applicantId;
  }
  
  public String getApplicantName()
  {
    return this.applicantName;
  }
  
  public void setApplicantName(String applicantName)
  {
    this.applicantName = applicantName;
  }
  
  public String getVoucherId()
  {
    return this.voucherId;
  }
  
  public void setVoucherId(String voucherId)
  {
    this.voucherId = voucherId;
  }
  
  public long getVoucherOwnerId()
  {
    return this.voucherOwnerId;
  }
  
  public void setVoucherOwnerId(long voucherOwnerId)
  {
    this.voucherOwnerId = voucherOwnerId;
  }
  
  public String getVoucherOwnerName()
  {
    return this.voucherOwnerName;
  }
  
  public void setVoucherOwnerName(String voucherOwnerName)
  {
    this.voucherOwnerName = voucherOwnerName;
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
  
  public long getExpenseTypesId()
  {
    return this.expenseTypesId;
  }
  
  public void setExpenseTypesId(long expenseTypesId)
  {
    this.expenseTypesId = expenseTypesId;
  }
  
  public String getExpenseTypesName()
  {
    return this.expenseTypesName;
  }
  
  public void setExpenseTypesName(String expenseTypesName)
  {
    this.expenseTypesName = expenseTypesName;
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
  
  public List getExpenseTypeList()
  {
    return this.expenseTypeList;
  }
  
  public void setExpenseTypeList(List expenseTypeList)
  {
    this.expenseTypeList = expenseTypeList;
  }
  
  public List getExpensesList()
  {
    return this.expensesList;
  }
  
  public void setExpensesList(List expensesList)
  {
    this.expensesList = expensesList;
  }
  
  public String getExpenseDate()
  {
    return this.expenseDate;
  }
  
  public void setExpenseDate(String expenseDate)
  {
    this.expenseDate = expenseDate;
  }
}
