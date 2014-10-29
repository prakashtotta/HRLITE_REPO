package com.bean.lov;

public class VariableListData
{
  public long variableListDataId;
  public long variableId;
  public String variableValue;
  public int sequence;
  public String isDefault;
  
  public long getVariableId()
  {
    return this.variableId;
  }
  
  public void setVariableId(long variableId)
  {
    this.variableId = variableId;
  }
  
  public String getVariableValue()
  {
    return this.variableValue;
  }
  
  public void setVariableValue(String variableValue)
  {
    this.variableValue = variableValue;
  }
  
  public int getSequence()
  {
    return this.sequence;
  }
  
  public void setSequence(int sequence)
  {
    this.sequence = sequence;
  }
  
  public String getIsDefault()
  {
    return this.isDefault;
  }
  
  public void setIsDefault(String isDefault)
  {
    this.isDefault = isDefault;
  }
  
  public long getVariableListDataId()
  {
    return this.variableListDataId;
  }
  
  public void setVariableListDataId(long variableListDataId)
  {
    this.variableListDataId = variableListDataId;
  }
}
