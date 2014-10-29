package com.bean;

public class Timezone
{
  public long timezoneId;
  public String timezoneCode;
  public String timezoneName;
  public String timezoneoffset;
  
  public long getTimezoneId()
  {
    return this.timezoneId;
  }
  
  public void setTimezoneId(long timezoneId)
  {
    this.timezoneId = timezoneId;
  }
  
  public String getTimezoneCode()
  {
    return this.timezoneCode;
  }
  
  public void setTimezoneCode(String timezoneCode)
  {
    this.timezoneCode = timezoneCode;
  }
  
  public String getTimezoneName()
  {
    return this.timezoneName;
  }
  
  public void setTimezoneName(String timezoneName)
  {
    this.timezoneName = timezoneName;
  }
  
  public String getTimezoneoffset()
  {
    return this.timezoneoffset;
  }
  
  public void setTimezoneoffset(String timezoneoffset)
  {
    this.timezoneoffset = timezoneoffset;
  }
}
