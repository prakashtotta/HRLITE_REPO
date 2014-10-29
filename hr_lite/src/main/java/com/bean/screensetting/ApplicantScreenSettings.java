package com.bean.screensetting;

public class ApplicantScreenSettings
{
  private long appscreenId;
  private long userId;
  private String screenName;
  private String fullName;
  private String fromList;
  private String toList;
  
  public long getAppscreenId()
  {
    return this.appscreenId;
  }
  
  public void setAppscreenId(long appscreenId)
  {
    this.appscreenId = appscreenId;
  }
  
  public long getUserId()
  {
    return this.userId;
  }
  
  public void setUserId(long userId)
  {
    this.userId = userId;
  }
  
  public String getScreenName()
  {
    return this.screenName;
  }
  
  public void setScreenName(String screenName)
  {
    this.screenName = screenName;
  }
  
  public String getFullName()
  {
    return this.fullName;
  }
  
  public void setFullName(String fullName)
  {
    this.fullName = fullName;
  }
  
  public String getFromList()
  {
    return this.fromList;
  }
  
  public void setFromList(String fromList)
  {
    this.fromList = fromList;
  }
  
  public String getToList()
  {
    return this.toList;
  }
  
  public void setToList(String toList)
  {
    this.toList = toList;
  }
}
