package com.form;

import org.apache.struts.action.ActionForm;

public class RoundForm
  extends ActionForm
{
  public long roundId;
  public String roundName;
  String roundDesc;
  String status;
  public int avweight;
  
  public long getRoundId()
  {
    return this.roundId;
  }
  
  public void setRoundId(long roundId)
  {
    this.roundId = roundId;
  }
  
  public String getRoundName()
  {
    return this.roundName;
  }
  
  public void setRoundName(String roundName)
  {
    this.roundName = roundName;
  }
  
  public String getRoundDesc()
  {
    return this.roundDesc;
  }
  
  public void setRoundDesc(String roundDesc)
  {
    this.roundDesc = roundDesc;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public int getAvweight()
  {
    return this.avweight;
  }
  
  public void setAvweight(int avweight)
  {
    this.avweight = avweight;
  }
}
