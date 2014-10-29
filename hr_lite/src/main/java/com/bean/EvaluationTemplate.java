package com.bean;

import java.util.Date;
import java.util.List;

public class EvaluationTemplate
{
  public long evtmplId;
  public String evTmplName;
  public String evTmplDesc;
  public String createdBy;
  public Date createdDate;
  private Date updatedDate;
  private String updatedBy;
  private String status;
  private List charGroupList;
  
  public long getEvtmplId()
  {
    return this.evtmplId;
  }
  
  public void setEvtmplId(long evtmplId)
  {
    this.evtmplId = evtmplId;
  }
  
  public String getEvTmplName()
  {
    return this.evTmplName;
  }
  
  public void setEvTmplName(String evTmplName)
  {
    this.evTmplName = evTmplName;
  }
  
  public String getEvTmplDesc()
  {
    return this.evTmplDesc;
  }
  
  public void setEvTmplDesc(String evTmplDesc)
  {
    this.evTmplDesc = evTmplDesc;
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
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public List getCharGroupList()
  {
    return this.charGroupList;
  }
  
  public void setCharGroupList(List charGroupList)
  {
    this.charGroupList = charGroupList;
  }
}
