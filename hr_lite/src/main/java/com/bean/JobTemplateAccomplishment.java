package com.bean;

public class JobTemplateAccomplishment
{
  public long jbTmplAccId;
  public long jbTmplId;
  public int importance;
  public String accName;
  public String mandatory;
  public String type;
  
  public long getJbTmplAccId()
  {
    return this.jbTmplAccId;
  }
  
  public void setJbTmplAccId(long jbTmplAccId)
  {
    this.jbTmplAccId = jbTmplAccId;
  }
  
  public long getJbTmplId()
  {
    return this.jbTmplId;
  }
  
  public void setJbTmplId(long jbTmplId)
  {
    this.jbTmplId = jbTmplId;
  }
  
  public int getImportance()
  {
    return this.importance;
  }
  
  public void setImportance(int importance)
  {
    this.importance = importance;
  }
  
  public String getMandatory()
  {
    return this.mandatory;
  }
  
  public void setMandatory(String mandatory)
  {
    this.mandatory = mandatory;
  }
  
  public String getAccName()
  {
    return this.accName;
  }
  
  public void setAccName(String accName)
  {
    this.accName = accName;
  }
  
  public String getType()
  {
    return this.type;
  }
  
  public void setType(String type)
  {
    this.type = type;
  }
}
