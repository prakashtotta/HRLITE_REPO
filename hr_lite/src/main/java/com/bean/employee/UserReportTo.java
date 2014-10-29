package com.bean.employee;

import com.bean.User;

public class UserReportTo
{
  public long reportToId;
  public long userId;
  private String reportToType;
  private String reportToMethod;
  private String reportToMethodOther;
  public User userReportTo;
  
  public long getReportToId()
  {
    return this.reportToId;
  }
  
  public void setReportToId(long reportToId)
  {
    this.reportToId = reportToId;
  }
  
  public long getUserId()
  {
    return this.userId;
  }
  
  public void setUserId(long userId)
  {
    this.userId = userId;
  }
  
  public String getReportToType()
  {
    return this.reportToType;
  }
  
  public void setReportToType(String reportToType)
  {
    this.reportToType = reportToType;
  }
  
  public String getReportToMethod()
  {
    return this.reportToMethod;
  }
  
  public void setReportToMethod(String reportToMethod)
  {
    this.reportToMethod = reportToMethod;
  }
  
  public String getReportToMethodOther()
  {
    return this.reportToMethodOther;
  }
  
  public void setReportToMethodOther(String reportToMethodOther)
  {
    this.reportToMethodOther = reportToMethodOther;
  }
  
  public User getUserReportTo()
  {
    return this.userReportTo;
  }
  
  public void setUserReportTo(User userReportTo)
  {
    this.userReportTo = userReportTo;
  }
}
