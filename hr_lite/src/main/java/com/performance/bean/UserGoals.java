package com.performance.bean;

import java.util.Date;
import java.util.List;

public class UserGoals
{
  public long userGoalId;
  public long goalInitiationId;
  public TimePeriod timeperiod;
  public long userId;
  public Goal goal;
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
  public String modifiable;
  public String kramodifiable;
  public String status;
  public String achivementDetails;
  public String superiorComment;
  public List<UserGoalsKpi> kpiList;
  
  public long getUserGoalId()
  {
    return this.userGoalId;
  }
  
  public void setUserGoalId(long userGoalId)
  {
    this.userGoalId = userGoalId;
  }
  
  public long getGoalInitiationId()
  {
    return this.goalInitiationId;
  }
  
  public void setGoalInitiationId(long goalInitiationId)
  {
    this.goalInitiationId = goalInitiationId;
  }
  
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
  
  public String getModifiable()
  {
    return this.modifiable;
  }
  
  public void setModifiable(String modifiable)
  {
    this.modifiable = modifiable;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public String getAchivementDetails()
  {
    return this.achivementDetails;
  }
  
  public void setAchivementDetails(String achivementDetails)
  {
    this.achivementDetails = achivementDetails;
  }
  
  public String getSuperiorComment()
  {
    return this.superiorComment;
  }
  
  public void setSuperiorComment(String superiorComment)
  {
    this.superiorComment = superiorComment;
  }
  
  public long getUserId()
  {
    return this.userId;
  }
  
  public void setUserId(long userId)
  {
    this.userId = userId;
  }
  
  public List<UserGoalsKpi> getKpiList()
  {
    return this.kpiList;
  }
  
  public void setKpiList(List<UserGoalsKpi> kpiList)
  {
    this.kpiList = kpiList;
  }
  
  public String getKramodifiable()
  {
    return this.kramodifiable;
  }
  
  public void setKramodifiable(String kramodifiable)
  {
    this.kramodifiable = kramodifiable;
  }
  
  public Goal getGoal()
  {
    return this.goal;
  }
  
  public void setGoal(Goal goal)
  {
    this.goal = goal;
  }
  
  public TimePeriod getTimeperiod()
  {
    return this.timeperiod;
  }
  
  public void setTimeperiod(TimePeriod timeperiod)
  {
    this.timeperiod = timeperiod;
  }
}
