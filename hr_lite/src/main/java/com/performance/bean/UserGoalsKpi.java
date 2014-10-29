package com.performance.bean;

public class UserGoalsKpi
{
  public long usergoalkpiId;
  public long userGoalId;
  public String kpiName;
  public String kraMeasure;
  
  public long getUsergoalkpiId()
  {
    return this.usergoalkpiId;
  }
  
  public void setUsergoalkpiId(long usergoalkpiId)
  {
    this.usergoalkpiId = usergoalkpiId;
  }
  
  public long getUserGoalId()
  {
    return this.userGoalId;
  }
  
  public void setUserGoalId(long userGoalId)
  {
    this.userGoalId = userGoalId;
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
