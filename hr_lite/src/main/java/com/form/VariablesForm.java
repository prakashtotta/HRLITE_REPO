package com.form;

import java.util.Date;
import java.util.List;
import java.util.Set;
import org.apache.struts.action.ActionForm;

public class VariablesForm
  extends ActionForm
{
  public long variableId;
  public String variableName;
  public String variableCode;
  public String variableDesc;
  public String variableType;
  public String defaultValue;
  public String defaultValueDate = "";
  public String status;
  public String createdBy;
  public Date createdDate;
  public Date updatedDate;
  public String updatedBy;
  Set listData;
  List selectedListData;
  public String[] selectedData;
  public String variablename;
  public String isMandatory;
  List allVariables;
  public String compareVariableCode;
  public String defaultValuearea;
  public String neumeric;
  public String readPreview;
  
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
  
  public String getVariableType()
  {
    return this.variableType;
  }
  
  public void setVariableType(String variableType)
  {
    this.variableType = variableType;
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
  
  public Set getListData()
  {
    return this.listData;
  }
  
  public void setListData(Set listData)
  {
    this.listData = listData;
  }
  
  public List getSelectedListData()
  {
    return this.selectedListData;
  }
  
  public void setSelectedListData(List selectedListData)
  {
    this.selectedListData = selectedListData;
  }
  
  public String[] getSelectedData()
  {
    return this.selectedData;
  }
  
  public void setSelectedData(String[] selectedData)
  {
    this.selectedData = selectedData;
  }
  
  public String getVariablename()
  {
    return this.variablename;
  }
  
  public void setVariablename(String variablename)
  {
    this.variablename = variablename;
  }
  
  public String getDefaultValueDate()
  {
    return this.defaultValueDate;
  }
  
  public void setDefaultValueDate(String defaultValueDate)
  {
    this.defaultValueDate = defaultValueDate;
  }
  
  public List getAllVariables()
  {
    return this.allVariables;
  }
  
  public void setAllVariables(List allVariables)
  {
    this.allVariables = allVariables;
  }
  
  public String getCompareVariableCode()
  {
    return this.compareVariableCode;
  }
  
  public void setCompareVariableCode(String compareVariableCode)
  {
    this.compareVariableCode = compareVariableCode;
  }
  
  public String getDefaultValuearea()
  {
    return this.defaultValuearea;
  }
  
  public void setDefaultValuearea(String defaultValuearea)
  {
    this.defaultValuearea = defaultValuearea;
  }
  
  public String getIsMandatory()
  {
    return this.isMandatory;
  }
  
  public void setIsMandatory(String isMandatory)
  {
    this.isMandatory = isMandatory;
  }
  
  public String getNeumeric()
  {
    return this.neumeric;
  }
  
  public void setNeumeric(String neumeric)
  {
    this.neumeric = neumeric;
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
