package com.form;

import com.bean.Organization;
import java.util.List;
import org.apache.struts.action.ActionForm;

public class RefferalRedemptionRuleForm
  extends ActionForm
{
  public int ruleId;
  public String ruleName;
  public String ruleDesc;
  public int creditAfterdays;
  public String readPreview;
  public List criteriaList;
  public String criteria;
  public long orgId;
  private List orgnizationList;
  private String orgName;
  public Organization organization;
  
  public Organization getOrganization()
  {
    return this.organization;
  }
  
  public void setOrganization(Organization organization)
  {
    this.organization = organization;
    this.orgName = organization.getOrgName();
  }
  
  public String getOrgName()
  {
    return this.orgName;
  }
  
  public void setOrgName(String orgName)
  {
    this.orgName = orgName;
  }
  
  public List getOrgnizationList()
  {
    return this.orgnizationList;
  }
  
  public void setOrgnizationList(List orgnizationList)
  {
    this.orgnizationList = orgnizationList;
  }
  
  public long getOrgId()
  {
    return this.orgId;
  }
  
  public void setOrgId(long orgId)
  {
    this.orgId = orgId;
  }
  
  public String getCriteria()
  {
    return this.criteria;
  }
  
  public void setCriteria(String criteria)
  {
    this.criteria = criteria;
  }
  
  public List getCriteriaList()
  {
    return this.criteriaList;
  }
  
  public void setCriteriaList(List criteriaList)
  {
    this.criteriaList = criteriaList;
  }
  
  public String getRuleName()
  {
    return this.ruleName;
  }
  
  public void setRuleName(String ruleName)
  {
    this.ruleName = ruleName;
  }
  
  public String getRuleDesc()
  {
    return this.ruleDesc;
  }
  
  public void setRuleDesc(String ruleDesc)
  {
    this.ruleDesc = ruleDesc;
  }
  
  public int getCreditAfterdays()
  {
    return this.creditAfterdays;
  }
  
  public void setCreditAfterdays(int creditAfterdays)
  {
    this.creditAfterdays = creditAfterdays;
  }
  
  public int getRuleId()
  {
    return this.ruleId;
  }
  
  public void setRuleId(int ruleId)
  {
    this.ruleId = ruleId;
  }
  
  public String getReadPreview()
  {
    return this.readPreview;
  }
  
  public void setReadPreview(String readPreview)
  {
    this.readPreview = readPreview;
  }
}
