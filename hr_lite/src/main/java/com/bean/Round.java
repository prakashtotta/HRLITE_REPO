package com.bean;

import java.util.HashSet;
import java.util.Set;

public class Round
{
  public long roundId;
  public String roundName;
  String roundDesc;
  String status;
  public int avweight = 0;
  private Set selections = new HashSet();
  
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
  
  public Set getSelections()
  {
    return this.selections;
  }
  
  public void setSelections(Set selections)
  {
    this.selections = selections;
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
