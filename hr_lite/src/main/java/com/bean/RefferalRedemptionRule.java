package com.bean;

import java.util.Date;

public class RefferalRedemptionRule
{
  public int ruleId;
  public String ruleName;
  public String ruleDesc;
  public int creditAfterdays;
  public String criteria;
  public String status;
  public String createdBy;
  private Date createdDate;
  public long super_user_key;
  
  public int getRuleId()
  {
    return this.ruleId;
  }
  
  public void setRuleId(int ruleId)
  {
    this.ruleId = ruleId;
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
  
  public String getCriteria()
  {
    return this.criteria;
  }
  
  public void setCriteria(String criteria)
  {
    this.criteria = criteria;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
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
  
  public long getSuper_user_key()
  {
    return this.super_user_key;
  }
  
  public void setSuper_user_key(long superUserKey)
  {
    this.super_user_key = superUserKey;
  }
}
