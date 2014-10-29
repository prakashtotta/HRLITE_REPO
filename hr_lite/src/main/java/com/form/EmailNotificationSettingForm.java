package com.form;

import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;

public class EmailNotificationSettingForm
  extends ActionForm
{
  protected static final Logger logger = Logger.getLogger(EmailNotificationSettingForm.class);
  public int weid;
  public String functionName;
  public String isWatcher;
  public String isHiringMgr;
  public String isRecruiter;
  public String isCurrentOwner;
  public String updatedBy;
  public Date updatedDate;
  public List emailFunctionNotificationList;
  
  public List getEmailFunctionNotificationList()
  {
    return this.emailFunctionNotificationList;
  }
  
  public void setEmailFunctionNotificationList(List emailFunctionNotificationList)
  {
    this.emailFunctionNotificationList = emailFunctionNotificationList;
  }
  
  public int getWeid()
  {
    return this.weid;
  }
  
  public void setWeid(int weid)
  {
    this.weid = weid;
  }
  
  public String getFunctionName()
  {
    return this.functionName;
  }
  
  public void setFunctionName(String functionName)
  {
    this.functionName = functionName;
  }
  
  public String getUpdatedBy()
  {
    return this.updatedBy;
  }
  
  public void setUpdatedBy(String updatedBy)
  {
    this.updatedBy = updatedBy;
  }
  
  public Date getUpdatedDate()
  {
    return this.updatedDate;
  }
  
  public void setUpdatedDate(Date updatedDate)
  {
    this.updatedDate = updatedDate;
  }
  
  public String getIsWatcher()
  {
    return this.isWatcher;
  }
  
  public void setIsWatcher(String isWatcher)
  {
    this.isWatcher = isWatcher;
  }
  
  public String getIsHiringMgr()
  {
    return this.isHiringMgr;
  }
  
  public void setIsHiringMgr(String isHiringMgr)
  {
    this.isHiringMgr = isHiringMgr;
  }
  
  public String getIsRecruiter()
  {
    return this.isRecruiter;
  }
  
  public void setIsRecruiter(String isRecruiter)
  {
    this.isRecruiter = isRecruiter;
  }
  
  public String getIsCurrentOwner()
  {
    return this.isCurrentOwner;
  }
  
  public void setIsCurrentOwner(String isCurrentOwner)
  {
    this.isCurrentOwner = isCurrentOwner;
  }
}
