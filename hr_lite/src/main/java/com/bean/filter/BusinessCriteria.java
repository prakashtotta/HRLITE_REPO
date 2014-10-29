package com.bean.filter;

import java.util.Date;
import java.util.Set;

public class BusinessCriteria
{
  public long businessCriteraId;
  public String name;
  public String desc;
  public String type;
  public long idvalue;
  public long variableId;
  public String variableName;
  public String variableType;
  public String variableOrigin;
  public String variableCriteria;
  public String filterValue1;
  public String filterValue2;
  public Date filterValueDate1;
  public Date filterValueDate2;
  public String status;
  public String createdBy;
  private Date createdDate;
  private Date updatedDate;
  private String updatedBy;
  private String isSilent = "N";
  public String valueName;
  public long super_user_key;
  private Set<BusinessFilterConditions> businessConditions;
  
  public String getValueName()
  {
    return this.valueName;
  }
  
  public void setValueName(String valueName)
  {
    this.valueName = valueName;
  }
  
  public long getBusinessCriteraId()
  {
    return this.businessCriteraId;
  }
  
  public void setBusinessCriteraId(long businessCriteraId)
  {
    this.businessCriteraId = businessCriteraId;
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
  
  public long getIdvalue()
  {
    return this.idvalue;
  }
  
  public void setIdvalue(long idvalue)
  {
    this.idvalue = idvalue;
  }
  
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
  
  public String getVariableType()
  {
    return this.variableType;
  }
  
  public void setVariableType(String variableType)
  {
    this.variableType = variableType;
  }
  
  public String getVariableCriteria()
  {
    return this.variableCriteria;
  }
  
  public void setVariableCriteria(String variableCriteria)
  {
    this.variableCriteria = variableCriteria;
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
  
  public Set<BusinessFilterConditions> getBusinessConditions()
  {
    return this.businessConditions;
  }
  
  public void setBusinessConditions(Set<BusinessFilterConditions> businessConditions)
  {
    this.businessConditions = businessConditions;
  }
  
  public String getType()
  {
    return this.type;
  }
  
  public void setType(String type)
  {
    this.type = type;
  }
  
  public String getVariableOrigin()
  {
    return this.variableOrigin;
  }
  
  public void setVariableOrigin(String variableOrigin)
  {
    this.variableOrigin = variableOrigin;
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
  
  public String getIsSilent()
  {
    return this.isSilent;
  }
  
  public void setIsSilent(String isSilent)
  {
    this.isSilent = isSilent;
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
