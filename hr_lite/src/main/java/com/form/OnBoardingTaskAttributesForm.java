package com.form;

import com.bean.onboard.OnBoardingTaskDefinitions;

public class OnBoardingTaskAttributesForm
{
  public long taskattid;
  public OnBoardingTaskDefinitions taskdefinition;
  public String attribute;
  public String isMandatory;
  
  public long getTaskattid()
  {
    return this.taskattid;
  }
  
  public void setTaskattid(long taskattid)
  {
    this.taskattid = taskattid;
  }
  
  public OnBoardingTaskDefinitions getTaskdefinition()
  {
    return this.taskdefinition;
  }
  
  public void setTaskdefinition(OnBoardingTaskDefinitions taskdefinition)
  {
    this.taskdefinition = taskdefinition;
  }
  
  public String getAttribute()
  {
    return this.attribute;
  }
  
  public void setAttribute(String attribute)
  {
    this.attribute = attribute;
  }
  
  public String getIsMandatory()
  {
    return this.isMandatory;
  }
  
  public void setIsMandatory(String isMandatory)
  {
    this.isMandatory = isMandatory;
  }
}
