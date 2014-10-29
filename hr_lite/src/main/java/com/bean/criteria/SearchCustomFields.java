package com.bean.criteria;

public class SearchCustomFields
{
  public long variable_id;
  public String varibale_type;
  public String variableCode;
  public String filtercri;
  public String answerOption;
  public String filterValue1;
  public String filterValue2;
  
  public long getVariable_id()
  {
    return this.variable_id;
  }
  
  public void setVariable_id(long variableId)
  {
    this.variable_id = variableId;
  }
  
  public String getVaribale_type()
  {
    return this.varibale_type;
  }
  
  public void setVaribale_type(String varibaleType)
  {
    this.varibale_type = varibaleType;
  }
  
  public String getVariableCode()
  {
    return this.variableCode;
  }
  
  public void setVariableCode(String variableCode)
  {
    this.variableCode = variableCode;
  }
  
  public String getFiltercri()
  {
    return this.filtercri;
  }
  
  public void setFiltercri(String filtercri)
  {
    this.filtercri = filtercri;
  }
  
  public String getAnswerOption()
  {
    return this.answerOption;
  }
  
  public void setAnswerOption(String answerOption)
  {
    this.answerOption = answerOption;
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
}
