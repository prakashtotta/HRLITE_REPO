package com.form;

import com.bean.AgencyRedemption;
import com.bean.User;
import java.util.List;
import org.apache.struts.action.ActionForm;

public class AgencyRedemptionSummaryForm
  extends ActionForm
{
  public long agencyId;
  public String applicantuuid;
  public AgencyRedemption agencyredmption;
  public User agency;
  public List agencyList;
  public String applicantName;
  public String applicantNo;
  public List jobtitleList;
  public long requitionId;
  public List referralBudgetCodeList;
  public long refBudgetId;
  public long refferalSchemeId;
  public List referralSchemeList;
  public String uom = "";
  public long refferalSchemeTypeId;
  public List refferalSchemeTypeList;
  public int ruleId;
  public List ruleList;
  public String state;
  public String isPaid;
  private String releasedate = "";
  private String releasecri;
  private String eventdate = "";
  private String eventcri;
  public List releaseTypeList;
  public List paidTypeList;
  private String savedsearchname;
  public long orgId;
  public List organizationList;
  public long departmentId;
  public List departmentList;
  public String backurltolist;
  
  public long getAgencyId()
  {
    return this.agencyId;
  }
  
  public void setAgencyId(long agencyId)
  {
    this.agencyId = agencyId;
  }
  
  public AgencyRedemption getAgencyredmption()
  {
    return this.agencyredmption;
  }
  
  public void setAgencyredmption(AgencyRedemption agencyredmption)
  {
    this.agencyredmption = agencyredmption;
  }
  
  public String getApplicantuuid()
  {
    return this.applicantuuid;
  }
  
  public void setApplicantuuid(String applicantuuid)
  {
    this.applicantuuid = applicantuuid;
  }
  
  public List getAgencyList()
  {
    return this.agencyList;
  }
  
  public void setAgencyList(List agencyList)
  {
    this.agencyList = agencyList;
  }
  
  public String getApplicantName()
  {
    return this.applicantName;
  }
  
  public void setApplicantName(String applicantName)
  {
    this.applicantName = applicantName;
  }
  
  public List getJobtitleList()
  {
    return this.jobtitleList;
  }
  
  public void setJobtitleList(List jobtitleList)
  {
    this.jobtitleList = jobtitleList;
  }
  
  public long getRequitionId()
  {
    return this.requitionId;
  }
  
  public void setRequitionId(long requitionId)
  {
    this.requitionId = requitionId;
  }
  
  public List getReferralBudgetCodeList()
  {
    return this.referralBudgetCodeList;
  }
  
  public void setReferralBudgetCodeList(List referralBudgetCodeList)
  {
    this.referralBudgetCodeList = referralBudgetCodeList;
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
  
  public List getReferralSchemeList()
  {
    return this.referralSchemeList;
  }
  
  public void setReferralSchemeList(List referralSchemeList)
  {
    this.referralSchemeList = referralSchemeList;
  }
  
  public long getRefferalSchemeTypeId()
  {
    return this.refferalSchemeTypeId;
  }
  
  public void setRefferalSchemeTypeId(long refferalSchemeTypeId)
  {
    this.refferalSchemeTypeId = refferalSchemeTypeId;
  }
  
  public List getRefferalSchemeTypeList()
  {
    return this.refferalSchemeTypeList;
  }
  
  public void setRefferalSchemeTypeList(List refferalSchemeTypeList)
  {
    this.refferalSchemeTypeList = refferalSchemeTypeList;
  }
  
  public int getRuleId()
  {
    return this.ruleId;
  }
  
  public void setRuleId(int ruleId)
  {
    this.ruleId = ruleId;
  }
  
  public List getRuleList()
  {
    return this.ruleList;
  }
  
  public void setRuleList(List ruleList)
  {
    this.ruleList = ruleList;
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
  
  public String getReleasedate()
  {
    return this.releasedate;
  }
  
  public void setReleasedate(String releasedate)
  {
    this.releasedate = releasedate;
  }
  
  public String getReleasecri()
  {
    return this.releasecri;
  }
  
  public void setReleasecri(String releasecri)
  {
    this.releasecri = releasecri;
  }
  
  public String getEventdate()
  {
    return this.eventdate;
  }
  
  public void setEventdate(String eventdate)
  {
    this.eventdate = eventdate;
  }
  
  public String getEventcri()
  {
    return this.eventcri;
  }
  
  public void setEventcri(String eventcri)
  {
    this.eventcri = eventcri;
  }
  
  public String getUom()
  {
    return this.uom;
  }
  
  public void setUom(String uom)
  {
    this.uom = uom;
  }
  
  public String getSavedsearchname()
  {
    return this.savedsearchname;
  }
  
  public void setSavedsearchname(String savedsearchname)
  {
    this.savedsearchname = savedsearchname;
  }
  
  public List getReleaseTypeList()
  {
    return this.releaseTypeList;
  }
  
  public void setReleaseTypeList(List releaseTypeList)
  {
    this.releaseTypeList = releaseTypeList;
  }
  
  public List getPaidTypeList()
  {
    return this.paidTypeList;
  }
  
  public void setPaidTypeList(List paidTypeList)
  {
    this.paidTypeList = paidTypeList;
  }
  
  public String getApplicantNo()
  {
    return this.applicantNo;
  }
  
  public void setApplicantNo(String applicantNo)
  {
    this.applicantNo = applicantNo;
  }
  
  public User getAgency()
  {
    return this.agency;
  }
  
  public void setAgency(User agency)
  {
    this.agency = agency;
  }
  
  public long getOrgId()
  {
    return this.orgId;
  }
  
  public void setOrgId(long orgId)
  {
    this.orgId = orgId;
  }
  
  public List getOrganizationList()
  {
    return this.organizationList;
  }
  
  public void setOrganizationList(List organizationList)
  {
    this.organizationList = organizationList;
  }
  
  public long getDepartmentId()
  {
    return this.departmentId;
  }
  
  public void setDepartmentId(long departmentId)
  {
    this.departmentId = departmentId;
  }
  
  public List getDepartmentList()
  {
    return this.departmentList;
  }
  
  public void setDepartmentList(List departmentList)
  {
    this.departmentList = departmentList;
  }
  
  public String getBackurltolist()
  {
    return this.backurltolist;
  }
  
  public void setBackurltolist(String backurltolist)
  {
    this.backurltolist = backurltolist;
  }
}
