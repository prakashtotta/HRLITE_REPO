package com.bean.criteria;

public class AgencyRedumptionSearchCriteria
{
  private String releasecri;
  private String releasedate = "";
  public String uom = "";
  public long agencyId;
  public long requitionId;
  public long refBudgetId;
  public long refferalSchemeId;
  public long refferalSchemeTypeId;
  public int ruleId;
  public String state;
  public String isPaid;
  public String applicantNo;
  public String applicantName;
  
  public String getReleasecri()
  {
    return this.releasecri;
  }
  
  public void setReleasecri(String releasecri)
  {
    this.releasecri = releasecri;
  }
  
  public String getReleasedate()
  {
    return this.releasedate;
  }
  
  public void setReleasedate(String releasedate)
  {
    this.releasedate = releasedate;
  }
  
  public String getUom()
  {
    return this.uom;
  }
  
  public void setUom(String uom)
  {
    this.uom = uom;
  }
  
  public long getAgencyId()
  {
    return this.agencyId;
  }
  
  public void setAgencyId(long agencyId)
  {
    this.agencyId = agencyId;
  }
  
  public long getRequitionId()
  {
    return this.requitionId;
  }
  
  public void setRequitionId(long requitionId)
  {
    this.requitionId = requitionId;
  }
  
  public long getRefBudgetId()
  {
    return this.refBudgetId;
  }
  
  public void setRefBudgetId(long refBudgetId)
  {
    this.refBudgetId = refBudgetId;
  }
  
  public long getRefferalSchemeId()
  {
    return this.refferalSchemeId;
  }
  
  public void setRefferalSchemeId(long refferalSchemeId)
  {
    this.refferalSchemeId = refferalSchemeId;
  }
  
  public long getRefferalSchemeTypeId()
  {
    return this.refferalSchemeTypeId;
  }
  
  public void setRefferalSchemeTypeId(long refferalSchemeTypeId)
  {
    this.refferalSchemeTypeId = refferalSchemeTypeId;
  }
  
  public int getRuleId()
  {
    return this.ruleId;
  }
  
  public void setRuleId(int ruleId)
  {
    this.ruleId = ruleId;
  }
  
  public String getState()
  {
    return this.state;
  }
  
  public void setState(String state)
  {
    this.state = state;
  }
  
  public String getIsPaid()
  {
    return this.isPaid;
  }
  
  public void setIsPaid(String isPaid)
  {
    this.isPaid = isPaid;
  }
  
  public String getApplicantNo()
  {
    return this.applicantNo;
  }
  
  public void setApplicantNo(String applicantNo)
  {
    this.applicantNo = applicantNo;
  }
  
  public String getApplicantName()
  {
    return this.applicantName;
  }
  
  public void setApplicantName(String applicantName)
  {
    this.applicantName = applicantName;
  }
}
