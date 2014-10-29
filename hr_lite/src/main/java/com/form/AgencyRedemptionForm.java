package com.form;

import java.util.List;
import org.apache.struts.action.ActionForm;

public class AgencyRedemptionForm
  extends ActionForm
{
  public String applicantName;
  public List applicantRedList;
  
  public String getApplicantName()
  {
    return this.applicantName;
  }
  
  public void setApplicantName(String applicantName)
  {
    this.applicantName = applicantName;
  }
  
  public List getApplicantRedList()
  {
    return this.applicantRedList;
  }
  
  public void setApplicantRedList(List applicantRedList)
  {
    this.applicantRedList = applicantRedList;
  }
}
