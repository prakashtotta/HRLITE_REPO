package com.bean.filter;

import java.util.Date;

public class BusinessFilterConditions
{
  public long filterConditionId;
  public String name;
  public String desc;
  public String variableName;
  public long variableId;
  public String variableType;
  public String variableOrigin;
  public String filterCriteria;
  public String filterValue1;
  public String filterValue2;
  public Date filterValueDate1;
  public Date filterValueDate2;
  public String valueName;
  public long super_user_key;
  
  public String getValueName()
  {
    return this.valueName;
  }
  
  public void setValueName(String valueName)
  {
    this.valueName = valueName;
  }
  
  public long getFilterConditionId()
  {
    return this.filterConditionId;
  }
  
  public void setFilterConditionId(long filterConditionId)
  {
    this.filterConditionId = filterConditionId;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public String getDesc()
  {
    return this.desc;
  }
  
  public void setDesc(String desc)
  {
    this.desc = desc;
  }
  
  public String getVariableName()
  {
    return this.variableName;
  }
  
  public void setVariableName(String variableName)
  {
    this.variableName = variableName;
  }
  
  public String getVariableType()
  {
    return this.variableType;
  }
  
  public void setVariableType(String variableType)
  {
    this.variableType = variableType;
  }
  
  public String getFilterCriteria()
  {
    return this.filterCriteria;
  }
  
  public void setFilterCriteria(String filterCriteria)
  {
    this.filterCriteria = filterCriteria;
  }
  
  public String getFilterValue1()
  {
    return this.filterValue1;
  }
  
  public void setFilterValue1(String filterValue1)
  {
    this.filterValue1 = filterValue1;
  }
  
  public String getFilterValue2()
  {
    return this.filterValue2;
  }
  
  public void setFilterValue2(String filterValue2)
  {
    this.filterValue2 = filterValue2;
  }
  
  public Date getFilterValueDate1()
  {
    return this.filterValueDate1;
  }
  
  public void setFilterValueDate1(Date filterValueDate1)
  {
    this.filterValueDate1 = filterValueDate1;
  }
  
  public Date getFilterValueDate2()
  {
    return this.filterValueDate2;
  }
  
  public void setFilterValueDate2(Date filterValueDate2)
  {
    this.filterValueDate2 = filterValueDate2;
  }
  
  public String getVariableOrigin()
  {
    return this.variableOrigin;
  }
  
  public void setVariableOrigin(String variableOrigin)
  {
    this.variableOrigin = variableOrigin;
  }
  
  public long getVariableId()
  {
    return this.variableId;
  }
  
  public void setVariableId(long variableId)
  {
    this.variableId = variableId;
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
