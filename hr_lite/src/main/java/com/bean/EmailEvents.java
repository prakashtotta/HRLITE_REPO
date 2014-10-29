package com.bean;

public class EmailEvents
{
  public long emailEventId;
  public String eventName;
  public String eventCode;
  public String eventDesc;
  
  public long getEmailEventId()
  {
    return this.emailEventId;
  }
  
  public void setEmailEventId(long emailEventId)
  {
    this.emailEventId = emailEventId;
  }
  
  public String getEventName()
  {
    return this.eventName;
  }
  
  public void setEventName(String eventName)
  {
    this.eventName = eventName;
  }
  
  public String getEventCode()
  {
    return this.eventCode;
  }
  
  public void setEventCode(String eventCode)
  {
    this.eventCode = eventCode;
  }
  
  public String getEventDesc()
  {
    return this.eventDesc;
  }
  
  public void setEventDesc(String eventDesc)
  {
    this.eventDesc = eventDesc;
  }
}
