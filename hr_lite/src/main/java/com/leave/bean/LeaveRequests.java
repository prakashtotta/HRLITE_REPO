package com.leave.bean;

import com.bean.Department;
import com.bean.Organization;
import java.util.Date;

public class LeaveRequests
{
  public long leaveReqId;
  public LeaveTypes leavetype;
  public Organization organization;
  public Department department;
  public LeaveTimePeriod leavetimeperiod;
  public Date appliedDate;
  public long userId;
  public String userFullName;
  public String leaveReqComment;
  public Date leaveStartDate;
  public Date leaveEndDate;
  public double leaveLengthDays;
  public double leaveLengthHours;
  public int leaveStatus;
  public double startTime;
  public double endTime;
  public String createdBy;
  public Date createdDate;
  public Date updatedDate;
  public String updatedBy;
  
  public long getLeaveReqId()
  {
    return this.leaveReqId;
  }
  
  public void setLeaveReqId(long leaveReqId)
  {
    this.leaveReqId = leaveReqId;
  }
  
  public LeaveTypes getLeavetype()
  {
    return this.leavetype;
  }
  
  public void setLeavetype(LeaveTypes leavetype)
  {
    this.leavetype = leavetype;
  }
  
  public Organization getOrganization()
  {
    return this.organization;
  }
  
  public void setOrganization(Organization organization)
  {
    this.organization = organization;
  }
  
  public Department getDepartment()
  {
    return this.department;
  }
  
  public void setDepartment(Department department)
  {
    this.department = department;
  }
  
  public LeaveTimePeriod getLeavetimeperiod()
  {
    return this.leavetimeperiod;
  }
  
  public void setLeavetimeperiod(LeaveTimePeriod leavetimeperiod)
  {
    this.leavetimeperiod = leavetimeperiod;
  }
  
  public Date getAppliedDate()
  {
    return this.appliedDate;
  }
  
  public void setAppliedDate(Date appliedDate)
  {
    this.appliedDate = appliedDate;
  }
  
  public long getUserId()
  {
    return this.userId;
  }
  
  public void setUserId(long userId)
  {
    this.userId = userId;
  }
  
  public String getUserFullName()
  {
    return this.userFullName;
  }
  
  public void setUserFullName(String userFullName)
  {
    this.userFullName = userFullName;
  }
  
  public String getLeaveReqComment()
  {
    return this.leaveReqComment;
  }
  
  public void setLeaveReqComment(String leaveReqComment)
  {
    this.leaveReqComment = leaveReqComment;
  }
  
  public Date getLeaveStartDate()
  {
    return this.leaveStartDate;
  }
  
  public void setLeaveStartDate(Date leaveStartDate)
  {
    this.leaveStartDate = leaveStartDate;
  }
  
  public Date getLeaveEndDate()
  {
    return this.leaveEndDate;
  }
  
  public void setLeaveEndDate(Date leaveEndDate)
  {
    this.leaveEndDate = leaveEndDate;
  }
  
  public double getLeaveLengthDays()
  {
    return this.leaveLengthDays;
  }
  
  public void setLeaveLengthDays(double leaveLengthDays)
  {
    this.leaveLengthDays = leaveLengthDays;
  }
  
  public double getLeaveLengthHours()
  {
    return this.leaveLengthHours;
  }
  
  public void setLeaveLengthHours(double leaveLengthHours)
  {
    this.leaveLengthHours = leaveLengthHours;
  }
  
  public int getLeaveStatus()
  {
    return this.leaveStatus;
  }
  
  public void setLeaveStatus(int leaveStatus)
  {
    this.leaveStatus = leaveStatus;
  }
  
  public double getStartTime()
  {
    return this.startTime;
  }
  
  public void setStartTime(double startTime)
  {
    this.startTime = startTime;
  }
  
  public double getEndTime()
  {
    return this.endTime;
  }
  
  public void setEndTime(double endTime)
  {
    this.endTime = endTime;
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
