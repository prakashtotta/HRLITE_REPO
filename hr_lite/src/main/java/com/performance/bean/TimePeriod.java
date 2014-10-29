package com.performance.bean;

import com.util.DateUtil;
import java.util.Date;

public class TimePeriod
{
  public long timePeriodId;
  public Date startDate;
  public Date endDate;
  public String startenddate;
  
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
  
  public String getStartenddate()
  {
    String ret = "";
    try
    {
      String dt1 = DateUtil.convertDateToStringDate(this.startDate, DateUtil.dateformatstandard);
      String dt2 = DateUtil.convertDateToStringDate(this.endDate, DateUtil.dateformatstandard);
      ret = dt1 + " - " + dt2;
    }
    catch (Exception e) {}
    return ret;
  }
}
