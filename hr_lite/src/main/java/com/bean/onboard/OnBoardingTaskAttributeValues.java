package com.bean.onboard;

public class OnBoardingTaskAttributeValues
{
  public long attid;
  public long onBoardingTaskId;
  public String attribute;
  public String attributeValue;
  public String isMandatory;
  
  public long getOnBoardingTaskId()
  {
    return this.onBoardingTaskId;
  }
  
  public void setOnBoardingTaskId(long onBoardingTaskId)
  {
    this.onBoardingTaskId = onBoardingTaskId;
  }
  
  public String getAttribute()
  {
    return this.attribute;
  }
  
  public void setAttribute(String attribute)
  {
    this.attribute = attribute;
  }
  
  public String getAttributeValue()
  {
    return this.attributeValue;
  }
  
  public void setAttributeValue(String attributeValue)
  {
    this.attributeValue = attributeValue;
  }
  
  public long getAttid()
  {
    return this.attid;
  }
  
  public void setAttid(long attid)
  {
    this.attid = attid;
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
