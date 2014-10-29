package com.performance.bean;

public class GoalKraKpi
{
  public long goalkrakpiId;
  public long goalKraId;
  public String kpiName;
  public String kraMeasure;
  
  public long getGoalkrakpiId()
  {
    return this.goalkrakpiId;
  }
  
  public void setGoalkrakpiId(long goalkrakpiId)
  {
    this.goalkrakpiId = goalkrakpiId;
  }
  
  public long getGoalKraId()
  {
    return this.goalKraId;
  }
  
  public void setGoalKraId(long goalKraId)
  {
    this.goalKraId = goalKraId;
  }
  
  public String getKpiName()
  {
    return this.kpiName;
  }
  
  public void setKpiName(String kpiName)
  {
    this.kpiName = kpiName;
  }
  
  public String getKraMeasure()
  {
    return this.kraMeasure;
  }
  
  public void setKraMeasure(String kraMeasure)
  {
    this.kraMeasure = kraMeasure;
  }
}
