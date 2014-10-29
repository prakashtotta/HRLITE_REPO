package com.bean;

import java.util.Date;

public class ReportNames
{
  public int reportId;
  public String reportKey;
  public String reportName;
  public String reportDesc;
  public String reportFilePath;
  public String reportFileName;
  private String createdBy;
  private Date createdDate;
  private Date updatedDate;
  private String updatedBy;
  
  public int getReportId()
  {
    return this.reportId;
  }
  
  public void setReportId(int reportId)
  {
    this.reportId = reportId;
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
  
  public String getReportDesc()
  {
    return this.reportDesc;
  }
  
  public void setReportDesc(String reportDesc)
  {
    this.reportDesc = reportDesc;
  }
  
  public String getReportFilePath()
  {
    return this.reportFilePath;
  }
  
  public void setReportFilePath(String reportFilePath)
  {
    this.reportFilePath = reportFilePath;
  }
  
  public String getReportFileName()
  {
    return this.reportFileName;
  }
  
  public void setReportFileName(String reportFileName)
  {
    this.reportFileName = reportFileName;
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
