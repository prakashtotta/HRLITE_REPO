package com.bean;

public class JobTemplateCompetency
{
  public long jbTmplcompId;
  public long jbTmplId;
  public int importance;
  public String charName;
  public String mandatory;
  public String type;
  public String isVisible;
  
  public String getIsVisible()
  {
    return this.isVisible;
  }
  
  public void setIsVisible(String isVisible)
  {
    this.isVisible = isVisible;
  }
  
  public long getJbTmplcompId()
  {
    return this.jbTmplcompId;
  }
  
  public void setJbTmplcompId(long jbTmplcompId)
  {
    this.jbTmplcompId = jbTmplcompId;
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
  
  public String getCharName()
  {
    return this.charName;
  }
  
  public void setCharName(String charName)
  {
    this.charName = charName;
  }
  
  public String getMandatory()
  {
    return this.mandatory;
  }
  
  public void setMandatory(String mandatory)
  {
    this.mandatory = mandatory;
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
