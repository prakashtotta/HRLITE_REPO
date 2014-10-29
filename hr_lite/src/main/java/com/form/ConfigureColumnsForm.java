package com.form;

import java.util.List;
import org.apache.struts.action.ActionForm;

public class ConfigureColumnsForm
  extends ActionForm
{
  private long appscreenId;
  private long userId;
  private String screenName;
  private List fromColumnsList;
  private List toColumnsList;
  private String todata;
  private String fromdata;
  private String type;
  
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
  
  public List getFromColumnsList()
  {
    return this.fromColumnsList;
  }
  
  public void setFromColumnsList(List fromColumnsList)
  {
    this.fromColumnsList = fromColumnsList;
  }
  
  public List getToColumnsList()
  {
    return this.toColumnsList;
  }
  
  public void setToColumnsList(List toColumnsList)
  {
    this.toColumnsList = toColumnsList;
  }
  
  public String getTodata()
  {
    return this.todata;
  }
  
  public void setTodata(String todata)
  {
    this.todata = todata;
  }
  
  public String getType()
  {
    return this.type;
  }
  
  public void setType(String type)
  {
    this.type = type;
  }
  
  public String getFromdata()
  {
    return this.fromdata;
  }
  
  public void setFromdata(String fromdata)
  {
    this.fromdata = fromdata;
  }
}
