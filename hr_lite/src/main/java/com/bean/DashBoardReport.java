package com.bean;

import java.util.Date;

public class DashBoardReport
{
  public int dashboardreportId;
  public long userid;
  public ReportNames report;
  public String reportKey;
  public String reportName;
  private String createdBy;
  private Date createdDate;
  private Date updatedDate;
  private String updatedBy;
  
  public int getDashboardreportId()
  {
    return this.dashboardreportId;
  }
  
  public void setDashboardreportId(int dashboardreportId)
  {
    this.dashboardreportId = dashboardreportId;
  }
  
  public long getUserid()
  {
    return this.userid;
  }
  
  public void setUserid(long userid)
  {
    this.userid = userid;
  }
  
  public ReportNames getReport()
  {
    return this.report;
  }
  
  public void setReport(ReportNames report)
  {
    this.report = report;
  }
  
  public String getReportKey()
  {
    return this.reportKey;
  }
  
  public void setReportKey(String reportKey)
  {
    this.reportKey = reportKey;
  }
  
  public String getReportName()
  {
    return this.reportName;
  }
  
  public void setReportName(String reportName)
  {
    this.reportName = reportName;
  }
  
  public String getCreatedBy()
  {
    return this.createdBy;
  }
  
  public void setCreatedBy(String createdBy)
  {
    this.createdBy = createdBy;
  }
  
  public Date getCreatedDate()
  {
    return this.createdDate;
  }
  
  public void setCreatedDate(Date createdDate)
  {
    this.createdDate = createdDate;
  }
  
  public Date getUpdatedDate()
  {
    return this.updatedDate;
  }
  
  public void setUpdatedDate(Date updatedDate)
  {
    this.updatedDate = updatedDate;
  }
  
  public String getUpdatedBy()
  {
    return this.updatedBy;
  }
  
  public void setUpdatedBy(String updatedBy)
  {
    this.updatedBy = updatedBy;
  }
}
