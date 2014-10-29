package com.form;

import com.bean.lov.Variables;
import java.util.Date;
import java.util.List;
import org.apache.struts.action.ActionForm;

public class FormVariablesMapForm
  extends ActionForm
{
  public long formVariablemapId;
  public String formName;
  public String formCode;
  public Variables variable;
  public int sequence;
  public String isMandatory;
  public String createdBy;
  public Date createdDate;
  public Date updatedDate;
  public String updatedBy;
  public long idValue;
  public List variableList;
  public String start;
  public String range;
  public String results;
  public String variablename;
  public String variabletype;
  public String variablevalue;
  public String variableidval;
  public String variablespanvalue;
  public String idval;
  public List variabletypeList;
  
  public long getFormVariablemapId()
  {
    return this.formVariablemapId;
  }
  
  public void setFormVariablemapId(long formVariablemapId)
  {
    this.formVariablemapId = formVariablemapId;
  }
  
  public String getFormName()
  {
    return this.formName;
  }
  
  public void setFormName(String formName)
  {
    this.formName = formName;
  }
  
  public String getFormCode()
  {
    return this.formCode;
  }
  
  public void setFormCode(String formCode)
  {
    this.formCode = formCode;
  }
  
  public Variables getVariable()
  {
    return this.variable;
  }
  
  public void setVariable(Variables variable)
  {
    this.variable = variable;
  }
  
  public int getSequence()
  {
    return this.sequence;
  }
  
  public void setSequence(int sequence)
  {
    this.sequence = sequence;
  }
  
  public String getIsMandatory()
  {
    return this.isMandatory;
  }
  
  public void setIsMandatory(String isMandatory)
  {
    this.isMandatory = isMandatory;
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
  
  public long getIdValue()
  {
    return this.idValue;
  }
  
  public void setIdValue(long idValue)
  {
    this.idValue = idValue;
  }
  
  public List getVariableList()
  {
    return this.variableList;
  }
  
  public void setVariableList(List variableList)
  {
    this.variableList = variableList;
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
  
  public String getVariablevalue()
  {
    return this.variablevalue;
  }
  
  public void setVariablevalue(String variablevalue)
  {
    this.variablevalue = variablevalue;
  }
  
  public String getVariableidval()
  {
    return this.variableidval;
  }
  
  public void setVariableidval(String variableidval)
  {
    this.variableidval = variableidval;
  }
  
  public String getVariablespanvalue()
  {
    return this.variablespanvalue;
  }
  
  public void setVariablespanvalue(String variablespanvalue)
  {
    this.variablespanvalue = variablespanvalue;
  }
  
  public String getVariablename()
  {
    return this.variablename;
  }
  
  public void setVariablename(String variablename)
  {
    this.variablename = variablename;
  }
  
  public String getVariabletype()
  {
    return this.variabletype;
  }
  
  public void setVariabletype(String variabletype)
  {
    this.variabletype = variabletype;
  }
  
  public String getIdval()
  {
    return this.idval;
  }
  
  public void setIdval(String idval)
  {
    this.idval = idval;
  }
  
  public List getVariabletypeList()
  {
    return this.variabletypeList;
  }
  
  public void setVariabletypeList(List variabletypeList)
  {
    this.variabletypeList = variabletypeList;
  }
}
