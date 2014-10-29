package com.bean.lov;

import java.util.Date;

public class FormVariablesMap
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
  public long super_user_key;
  
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
  
  public long getSuper_user_key()
  {
    return this.super_user_key;
  }
  
  public void setSuper_user_key(long superUserKey)
  {
    this.super_user_key = superUserKey;
  }
}
