package com.bean;

public class RefferalScheme
{
  public long refferalScheme_Id;
  public String refferalScheme_Name;
  public String refferalScheme_Desc;
  public long refferalScheme_Amount;
  public RefferalBudgetCode refferalBudgetCode;
  public RefferalSchemeType refferalSchemeType;
  public String refferalBudgetCodeStr = "";
  public String refferalSchemeName;
  public String status;
  public String ruleStr;
  public RefferalRedemptionRule rule;
  public String uom = "";
  public String schemeType = "E";
  public long super_user_key;
  
  public long getRefferalScheme_Id()
  {
    return this.refferalScheme_Id;
  }
  
  public void setRefferalScheme_Id(long refferalSchemeId)
  {
    this.refferalScheme_Id = refferalSchemeId;
  }
  
  public String getRefferalScheme_Name()
  {
    return this.refferalScheme_Name;
  }
  
  public void setRefferalScheme_Name(String refferalSchemeName)
  {
    this.refferalScheme_Name = refferalSchemeName;
  }
  
  public String getRefferalScheme_Desc()
  {
    return this.refferalScheme_Desc;
  }
  
  public void setRefferalScheme_Desc(String refferalSchemeDesc)
  {
    this.refferalScheme_Desc = refferalSchemeDesc;
  }
  
  public long getRefferalScheme_Amount()
  {
    return this.refferalScheme_Amount;
  }
  
  public void setRefferalScheme_Amount(long refferalSchemeAmount)
  {
    this.refferalScheme_Amount = refferalSchemeAmount;
  }
  
  public RefferalBudgetCode getRefferalBudgetCode()
  {
    return this.refferalBudgetCode;
  }
  
  public void setRefferalBudgetCode(RefferalBudgetCode refferalBudgetCode)
  {
    this.refferalBudgetCode = refferalBudgetCode;
    if (refferalBudgetCode != null) {
      this.refferalBudgetCodeStr = refferalBudgetCode.getRef_budgetCentreName();
    }
  }
  
  public RefferalSchemeType getRefferalSchemeType()
  {
    return this.refferalSchemeType;
  }
  
  public void setRefferalSchemeType(RefferalSchemeType refferalSchemeType)
  {
    this.refferalSchemeType = refferalSchemeType;
    this.refferalSchemeName = refferalSchemeType.getUom();
  }
  
  public String getRefferalSchemeName()
  {
    return this.refferalSchemeName;
  }
  
  public void setRefferalSchemeName(String refferalSchemeName)
  {
    this.refferalSchemeName = refferalSchemeName;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public String getRefferalBudgetCodeStr()
  {
    return this.refferalBudgetCodeStr;
  }
  
  public void setRefferalBudgetCodeStr(String refferalBudgetCodeStr)
  {
    this.refferalBudgetCodeStr = refferalBudgetCodeStr;
  }
  
  public String getRuleStr()
  {
    return this.ruleStr;
  }
  
  public void setRuleStr(String ruleStr)
  {
    this.ruleStr = ruleStr;
  }
  
  public RefferalRedemptionRule getRule()
  {
    return this.rule;
  }
  
  public void setRule(RefferalRedemptionRule rule)
  {
    this.rule = rule;
    if (rule != null) {
      this.ruleStr = rule.getRuleDesc();
    }
  }
  
  public String getUom()
  {
    return this.uom;
  }
  
  public void setUom(String uom)
  {
    this.uom = uom;
  }
  
  public String getSchemeType()
  {
    return this.schemeType;
  }
  
  public void setSchemeType(String schemeType)
  {
    this.schemeType = schemeType;
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
