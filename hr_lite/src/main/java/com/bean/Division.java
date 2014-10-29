package com.bean;

import java.util.Date;
import java.util.List;

public class Division
{
  public long divisionId;
  public String divisionName;
  public String divisionDesc;
  private String status;
  private String createdBy;
  private Date createdDate;
  private List deptlist;
  
  public long getDivisionId()
  {
    return this.divisionId;
  }
  
  public void setDivisionId(long divisionId)
  {
    this.divisionId = divisionId;
  }
  
  public String getDivisionName()
  {
    return this.divisionName;
  }
  
  public void setDivisionName(String divisionName)
  {
    this.divisionName = divisionName;
  }
  
  public String getDivisionDesc()
  {
    return this.divisionDesc;
  }
  
  public void setDivisionDesc(String divisionDesc)
  {
    this.divisionDesc = divisionDesc;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
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
  
  public List getDeptlist()
  {
    return this.deptlist;
  }
  
  public void setDeptlist(List deptlist)
  {
    this.deptlist = deptlist;
  }
}
