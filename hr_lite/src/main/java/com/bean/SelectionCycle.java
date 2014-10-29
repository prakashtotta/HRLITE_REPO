package com.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SelectionCycle
{
  public long selId;
  public String selName;
  public String selDesc;
  String status;
  public String createdBy;
  public Date createdDate;
  private Date updatedDate;
  private String updatedBy;
  private Set rounds = new HashSet();
  
  public long getSelId()
  {
    return this.selId;
  }
  
  public void setSelId(long selId)
  {
    this.selId = selId;
  }
  
  public String getSelName()
  {
    return this.selName;
  }
  
  public void setSelName(String selName)
  {
    this.selName = selName;
  }
  
  public String getSelDesc()
  {
    return this.selDesc;
  }
  
  public void setSelDesc(String selDesc)
  {
    this.selDesc = selDesc;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public Set getRounds()
  {
    return this.rounds;
  }
  
  public void setRounds(Set rounds)
  {
    this.rounds = rounds;
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
