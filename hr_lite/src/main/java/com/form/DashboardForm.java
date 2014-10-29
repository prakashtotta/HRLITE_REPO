package com.form;

import com.bean.ReportNames;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.struts.action.ActionForm;

public class DashboardForm
  extends ActionForm
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
  List reportList;
  String[] reports;
  List widgetList;
  Map widgetMap;
  List dashboardReportList;
  
  public List getDashboardReportList()
  {
    return this.dashboardReportList;
  }
  
  public void setDashboardReportList(List dashboardReportList)
  {
    this.dashboardReportList = dashboardReportList;
  }
  
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
  
  public List getReportList()
  {
    return this.reportList;
  }
  
  public void setReportList(List reportList)
  {
    this.reportList = reportList;
  }
  
  public String[] getReports()
  {
    return this.reports;
  }
  
  public void setReports(String[] reports)
  {
    this.reports = reports;
  }
  
  public List getWidgetList()
  {
    return this.widgetList;
  }
  
  public void setWidgetList(List widgetList)
  {
    this.widgetList = widgetList;
  }
  
  public Map getWidgetMap()
  {
    return this.widgetMap;
  }
  
  public void setWidgetMap(Map widgetMap)
  {
    this.widgetMap = widgetMap;
  }
}
