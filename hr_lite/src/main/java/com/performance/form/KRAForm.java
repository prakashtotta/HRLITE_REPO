package com.performance.form;

import com.performance.bean.Kra;
import com.util.StringUtils;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;

public class KRAForm
  extends ActionForm
{
  public long kraId;
  public String kraName;
  public String kradesc;
  public double kraWeight;
  public long parentKraId;
  public String isTrack;
  public Date trackStartDate;
  public Date trackEndDate;
  public String trackingFreqency;
  public String createdBy;
  public Date createdDate;
  public Date updatedDate;
  public String updatedBy;
  public List kraList;
  public List trackingFrequencyList;
  public List kpiList;
  public long goalId;
  public long userGoalId;
  public String modifiable;
  
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
  
  public long getParentKraId()
  {
    return this.parentKraId;
  }
  
  public void setParentKraId(long parentKraId)
  {
    this.parentKraId = parentKraId;
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
  
  public List getKraList()
  {
    return this.kraList;
  }
  
  public void setKraList(List kraList)
  {
    this.kraList = kraList;
  }
  
  public long getGoalId()
  {
    return this.goalId;
  }
  
  public void setGoalId(long goalId)
  {
    this.goalId = goalId;
  }
  
  public List getTrackingFrequencyList()
  {
    return this.trackingFrequencyList;
  }
  
  public void setTrackingFrequencyList(List trackingFrequencyList)
  {
    this.trackingFrequencyList = trackingFrequencyList;
  }
  
  public void toValue(Kra kra, HttpServletRequest request)
  {
    kra.kraId = getKraId();
    kra.kraName = getKraName();
    kra.kradesc = getKradesc();
    kra.isTrack = getIsTrack();
    kra.trackingFreqency = getTrackingFreqency();
    kra.trackStartDate = getTrackStartDate();
    kra.trackEndDate = getTrackEndDate();
    kra.kraWeight = getKraWeight();
    if (this.parentKraId != 0L)
    {
      Kra pkra = new Kra();
      pkra.setKraId(this.parentKraId);
      kra.setParentKra(pkra);
    }
    else
    {
      kra.setParentKra(null);
    }
    if ((!StringUtils.isNullOrEmpty(this.modifiable)) && (this.modifiable.equalsIgnoreCase("On"))) {
      kra.setModifiable("Y");
    } else {
      kra.setModifiable("N");
    }
  }
  
  public void fromValue(Kra kra, HttpServletRequest request)
  {
    this.kraId = kra.getKraId();
    this.kraName = kra.getKraName();
    this.kradesc = kra.getKradesc();
    this.isTrack = kra.getIsTrack();
    this.trackingFreqency = kra.getTrackingFreqency();
    this.trackStartDate = kra.getTrackStartDate();
    this.trackEndDate = kra.getTrackEndDate();
    this.kraWeight = kra.getKraWeight();
  }
  
  public void clear()
  {
    this.kraId = 0L;
    this.kraName = "";
    this.kradesc = "";
    this.isTrack = "";
    this.trackingFreqency = "";
    this.trackStartDate = null;
    this.trackEndDate = null;
    this.kraWeight = 0.0D;
  }
  
  public List getKpiList()
  {
    return this.kpiList;
  }
  
  public void setKpiList(List kpiList)
  {
    this.kpiList = kpiList;
  }
  
  public String getModifiable()
  {
    return this.modifiable;
  }
  
  public void setModifiable(String modifiable)
  {
    this.modifiable = modifiable;
  }
  
  public long getUserGoalId()
  {
    return this.userGoalId;
  }
  
  public void setUserGoalId(long userGoalId)
  {
    this.userGoalId = userGoalId;
  }
}
