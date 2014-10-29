package com.bean.lov;

import java.util.Date;
import java.util.Set;

public class Variables
{
  public long variableId;
  public String variableName;
  public String variableCode;
  public String variableDesc;
  public String variableType;
  public String defaultValue;
  public Date defaultValueDate;
  public String status;
  public String createdBy;
  public Date createdDate;
  public Date updatedDate;
  public String updatedBy;
  private Set listData;
  public long super_user_key;
  private String caljavascript;
  
  public long getVariableId()
  {
    return this.variableId;
  }
  
  public void setVariableId(long variableId)
  {
    this.variableId = variableId;
  }
  
  public String getVariableName()
  {
    return this.variableName;
  }
  
  public void setVariableName(String variableName)
  {
    this.variableName = variableName;
  }
  
  public String getVariableCode()
  {
    return this.variableCode;
  }
  
  public void setVariableCode(String variableCode)
  {
    this.variableCode = variableCode;
  }
  
  public String getVariableDesc()
  {
    return this.variableDesc;
  }
  
  public void setVariableDesc(String variableDesc)
  {
    this.variableDesc = variableDesc;
  }
  
  public String getDefaultValue()
  {
    return this.defaultValue;
  }
  
  public void setDefaultValue(String defaultValue)
  {
    this.defaultValue = defaultValue;
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
  
  public Date getUpdatedDate()
  {
    return this.updatedDate;
  }
  
  public void setUpdatedDate(Date updatedDate)
  {
    this.updatedDate = updatedDate;
  }
  
  public String getUpdatedBy()
  {
    return this.updatedBy;
  }
  
  public void setUpdatedBy(String updatedBy)
  {
    this.updatedBy = updatedBy;
  }
  
  public String getVariableType()
  {
    return this.variableType;
  }
  
  public void setVariableType(String variableType)
  {
    this.variableType = variableType;
  }
  
  public Set getListData()
  {
    return this.listData;
  }
  
  public void setListData(Set listData)
  {
    this.listData = listData;
  }
  
  public String getCaljavascript()
  {
    return this.caljavascript;
  }
  
  public void setCaljavascript(String caljavascript)
  {
    this.caljavascript = caljavascript;
  }
  
  public Date getDefaultValueDate()
  {
    return this.defaultValueDate;
  }
  
  public void setDefaultValueDate(Date defaultValueDate)
  {
    this.defaultValueDate = defaultValueDate;
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
