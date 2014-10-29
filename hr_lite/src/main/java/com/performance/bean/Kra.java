package com.performance.bean;

import java.util.Date;

public class Kra
{
  public long kraId;
  public String kraName;
  public String kradesc;
  public double kraWeight;
  public Kra parentKra;
  public String isTrack;
  public Date trackStartDate;
  public Date trackEndDate;
  public String trackingFreqency;
  public String createdBy;
  public Date createdDate;
  public Date updatedDate;
  public String updatedBy;
  public String modifiable;
  public long parentKraId;
  public String parentKraName;
  
  public long getKraId()
  {
    return this.kraId;
  }
  
  public void setKraId(long kraId)
  {
    this.kraId = kraId;
  }
  
  public String getKraName()
  {
    return this.kraName;
  }
  
  public void setKraName(String kraName)
  {
    this.kraName = kraName;
  }
  
  public String getKradesc()
  {
    return this.kradesc;
  }
  
  public void setKradesc(String kradesc)
  {
    this.kradesc = kradesc;
  }
  
  public double getKraWeight()
  {
    return this.kraWeight;
  }
  
  public void setKraWeight(double kraWeight)
  {
    this.kraWeight = kraWeight;
  }
  
  public String getIsTrack()
  {
    return this.isTrack;
  }
  
  public void setIsTrack(String isTrack)
  {
    this.isTrack = isTrack;
  }
  
  public Date getTrackStartDate()
  {
    return this.trackStartDate;
  }
  
  public void setTrackStartDate(Date trackStartDate)
  {
    this.trackStartDate = trackStartDate;
  }
  
  public Date getTrackEndDate()
  {
    return this.trackEndDate;
  }
  
  public void setTrackEndDate(Date trackEndDate)
  {
    this.trackEndDate = trackEndDate;
  }
  
  public String getTrackingFreqency()
  {
    return this.trackingFreqency;
  }
  
  public void setTrackingFreqency(String trackingFreqency)
  {
    this.trackingFreqency = trackingFreqency;
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
  
  public Kra getParentKra()
  {
    return this.parentKra;
  }
  
  public void setParentKra(Kra parentKra)
  {
    this.parentKra = parentKra;
    if (parentKra != null)
    {
      this.parentKraId = parentKra.getKraId();
      this.parentKraName = parentKra.getKraName();
    }
  }
  
  public String getModifiable()
  {
    return this.modifiable;
  }
  
  public void setModifiable(String modifiable)
  {
    this.modifiable = modifiable;
  }
}
