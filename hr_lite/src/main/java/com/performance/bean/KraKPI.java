package com.performance.bean;

public class KraKPI
{
  public long kpiId;
  public long kraId;
  public String kpiName;
  public String kraMeasure;
  
  public long getKpiId()
  {
    return this.kpiId;
  }
  
  public void setKpiId(long kpiId)
  {
    this.kpiId = kpiId;
  }
  
  public long getKraId()
  {
    return this.kraId;
  }
  
  public void setKraId(long kraId)
  {
    this.kraId = kraId;
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
