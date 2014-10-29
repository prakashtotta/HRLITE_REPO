package com.bean;

public class DashBoardConfig
{
  public long dashBoardConfigId;
  public String widgetCode;
  public String status;
  public long userId;
  
  public long getDashBoardConfigId()
  {
    return this.dashBoardConfigId;
  }
  
  public void setDashBoardConfigId(long dashBoardConfigId)
  {
    this.dashBoardConfigId = dashBoardConfigId;
  }
  
  public String getWidgetCode()
  {
    return this.widgetCode;
  }
  
  public void setWidgetCode(String widgetCode)
  {
    this.widgetCode = widgetCode;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public long getUserId()
  {
    return this.userId;
  }
  
  public void setUserId(long userId)
  {
    this.userId = userId;
  }
}
