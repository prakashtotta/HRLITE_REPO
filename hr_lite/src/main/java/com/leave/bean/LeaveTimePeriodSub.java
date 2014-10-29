package com.leave.bean;

import java.util.Date;

public class LeaveTimePeriodSub
{
  public long timePeriodSubId;
  public long timePeriodId;
  public Date startDate;
  public Date endDate;
  
  public long getTimePeriodSubId()
  {
    return this.timePeriodSubId;
  }
  
  public void setTimePeriodSubId(long timePeriodSubId)
  {
    this.timePeriodSubId = timePeriodSubId;
  }
  
  public long getTimePeriodId()
  {
    return this.timePeriodId;
  }
  
  public void setTimePeriodId(long timePeriodId)
  {
    this.timePeriodId = timePeriodId;
  }
  
  public Date getStartDate()
  {
    return this.startDate;
  }
  
  public void setStartDate(Date startDate)
  {
    this.startDate = startDate;
  }
  
  public Date getEndDate()
  {
    return this.endDate;
  }
  
  public void setEndDate(Date endDate)
  {
    this.endDate = endDate;
  }
}
