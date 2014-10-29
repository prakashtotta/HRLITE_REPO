package com.form;

import java.util.Date;
import java.util.List;
import org.apache.struts.action.ActionForm;

public class SelectionForm
  extends ActionForm
{
  public long selId;
  public String selName;
  String selDesc;
  public String createdBy;
  public Date createdDate;
  private Date updatedDate;
  private String updatedBy;
  List rounds;
  String[] round;
  
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
  
  public List getRounds()
  {
    return this.rounds;
  }
  
  public void setRounds(List rounds)
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
  
  public String[] getRound()
  {
    return this.round;
  }
  
  public void setRound(String[] round)
  {
    this.round = round;
  }
}
