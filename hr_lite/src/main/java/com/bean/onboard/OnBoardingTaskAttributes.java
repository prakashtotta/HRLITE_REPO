package com.bean.onboard;

public class OnBoardingTaskAttributes
{
  public long taskattid;
  public OnBoardingTaskDefinitions taskdefinition;
  public long taskdefinitionid;
  public String attribute;
  public String isMandatory;
  public String type;
  
  public long getTaskattid()
  {
    return this.taskattid;
  }
  
  public void setTaskattid(long taskattid)
  {
    this.taskattid = taskattid;
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
  
  public long getTaskdefinitionid()
  {
    return this.taskdefinitionid;
  }
  
  public void setTaskdefinitionid(long taskdefinitionid)
  {
    this.taskdefinitionid = taskdefinitionid;
  }
  
  public void setTaskdefinition(long taskdefid) {}
  
  public String getType()
  {
    return this.type;
  }
  
  public void setType(String type)
  {
    this.type = type;
  }
  
  public OnBoardingTaskDefinitions getTaskdefinition()
  {
    return this.taskdefinition;
  }
  
  public void setTaskdefinition(OnBoardingTaskDefinitions taskdefinition)
  {
    this.taskdefinition = taskdefinition;
  }
}
