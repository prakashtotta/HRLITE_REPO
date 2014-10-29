package com.form;

import com.bean.RefferalBudgetCode;
import com.bean.RefferalRedemptionRule;
import com.bean.RefferalSchemeType;
import java.util.List;
import org.apache.struts.action.ActionForm;

public class RefferalSchemeForm
  extends ActionForm
{
  public long refferalScheme_Id;
  public String refferalScheme_Name;
  public String refferalScheme_Desc;
  public long refferalScheme_Amount;
  public String status;
  public long ref_budgetId;
  public long refferalSchemeTypeId;
  public List refferalBudgetCodeList;
  public List refferalSchemeTypeList;
  public int ruleId;
  public List ruleList;
  public String uom = "";
  public String start;
  public String range;
  public String results;
  public String readPreview;
  public RefferalBudgetCode refferalbudgetcode;
  public String ref_budgetCode;
  public RefferalSchemeType refferalSchemeType;
  public String refferalSchemeName;
  public RefferalRedemptionRule rule;
  public String rulestr;
  private List orgnizationList;
  private List departmentList;
  public long orgId;
  public long deptId;
  public String schemeType = "E";
  public String uomscheme;
  private List uomList;
  public int creditAfterdays;
  public List criteriaList;
  public String criteria;
  private List referralSchemeList;
  
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
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public long getRef_budgetId()
  {
    return this.ref_budgetId;
  }
  
  public void setRef_budgetId(long refBudgetId)
  {
    this.ref_budgetId = refBudgetId;
  }
  
  public long getRefferalSchemeTypeId()
  {
    return this.refferalSchemeTypeId;
  }
  
  public void setRefferalSchemeTypeId(long refferalSchemeTypeId)
  {
    this.refferalSchemeTypeId = refferalSchemeTypeId;
  }
  
  public List getRefferalBudgetCodeList()
  {
    return this.refferalBudgetCodeList;
  }
  
  public void setRefferalBudgetCodeList(List refferalBudgetCodeList)
  {
    this.refferalBudgetCodeList = refferalBudgetCodeList;
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
  
  public String getUom()
  {
    return this.uom;
  }
  
  public void setUom(String uom)
  {
    this.uom = uom;
  }
  
  public String getStart()
  {
    return this.start;
  }
  
  public void setStart(String start)
  {
    this.start = start;
  }
  
  public String getRange()
  {
    return this.range;
  }
  
  public void setRange(String range)
  {
    this.range = range;
  }
  
  public String getResults()
  {
    return this.results;
  }
  
  public void setResults(String results)
  {
    this.results = results;
  }
  
  public List getOrgnizationList()
  {
    return this.orgnizationList;
  }
  
  public void setOrgnizationList(List orgnizationList)
  {
    this.orgnizationList = orgnizationList;
  }
  
  public List getDepartmentList()
  {
    return this.departmentList;
  }
  
  public void setDepartmentList(List departmentList)
  {
    this.departmentList = departmentList;
  }
  
  public long getOrgId()
  {
    return this.orgId;
  }
  
  public void setOrgId(long orgId)
  {
    this.orgId = orgId;
  }
  
  public long getDeptId()
  {
    return this.deptId;
  }
  
  public void setDeptId(long deptId)
  {
    this.deptId = deptId;
  }
  
  public List getReferralSchemeList()
  {
    return this.referralSchemeList;
  }
  
  public void setReferralSchemeList(List referralSchemeList)
  {
    this.referralSchemeList = referralSchemeList;
  }
  
  public RefferalBudgetCode getRefferalbudgetcode()
  {
    return this.refferalbudgetcode;
  }
  
  public void setRefferalbudgetcode(RefferalBudgetCode refferalbudgetcode)
  {
    this.refferalbudgetcode = refferalbudgetcode;
    this.ref_budgetCode = refferalbudgetcode.getRef_budgetCode();
  }
  
  public String getRef_budgetCode()
  {
    return this.ref_budgetCode;
  }
  
  public void setRef_budgetCode(String refBudgetCode)
  {
    this.ref_budgetCode = refBudgetCode;
  }
  
  public RefferalSchemeType getRefferalSchemeType()
  {
    return this.refferalSchemeType;
  }
  
  public void setRefferalSchemeType(RefferalSchemeType refferalSchemeType)
  {
    this.refferalSchemeType = refferalSchemeType;
    this.refferalSchemeName = refferalSchemeType.getRefferalSchemeName();
  }
  
  public String getRefferalSchemeName()
  {
    return this.refferalSchemeName;
  }
  
  public void setRefferalSchemeName(String refferalSchemeName)
  {
    this.refferalSchemeName = refferalSchemeName;
  }
  
  public RefferalRedemptionRule getRule()
  {
    return this.rule;
  }
  
  public void setRule(RefferalRedemptionRule rule)
  {
    this.rule = rule;
    if (rule != null) {
      this.rulestr = rule.getRuleName();
    }
  }
  
  public String getRulestr()
  {
    return this.rulestr;
  }
  
  public void setRulestr(String rulestr)
  {
    this.rulestr = rulestr;
  }
  
  public String getReadPreview()
  {
    return this.readPreview;
  }
  
  public void setReadPreview(String readPreview)
  {
    this.readPreview = readPreview;
  }
  
  public String getSchemeType()
  {
    return this.schemeType;
  }
  
  public void setSchemeType(String schemeType)
  {
    this.schemeType = schemeType;
  }
  
  public List getUomList()
  {
    return this.uomList;
  }
  
  public void setUomList(List uomList)
  {
    this.uomList = uomList;
  }
  
  public String getUomscheme()
  {
    return this.uomscheme;
  }
  
  public void setUomscheme(String uomscheme)
  {
    this.uomscheme = uomscheme;
  }
  
  public int getCreditAfterdays()
  {
    return this.creditAfterdays;
  }
  
  public void setCreditAfterdays(int creditAfterdays)
  {
    this.creditAfterdays = creditAfterdays;
  }
  
  public List getCriteriaList()
  {
    return this.criteriaList;
  }
  
  public void setCriteriaList(List criteriaList)
  {
    this.criteriaList = criteriaList;
  }
  
  public String getCriteria()
  {
    return this.criteria;
  }
  
  public void setCriteria(String criteria)
  {
    this.criteria = criteria;
  }
}
