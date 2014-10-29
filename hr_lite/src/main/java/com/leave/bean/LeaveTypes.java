package com.leave.bean;

public class LeaveTypes
{
  public long leaveTypeId;
  public String leaveTypeName;
  public String leaveTypeDesc;
  public String status;
  
  public long getLeaveTypeId()
  {
    return this.leaveTypeId;
  }
  
  public void setLeaveTypeId(long leaveTypeId)
  {
    this.leaveTypeId = leaveTypeId;
  }
  
  public String getLeaveTypeName()
  {
    return this.leaveTypeName;
  }
  
  public void setLeaveTypeName(String leaveTypeName)
  {
    this.leaveTypeName = leaveTypeName;
  }
  
  public String getLeaveTypeDesc()
  {
    return this.leaveTypeDesc;
  }
  
  public void setLeaveTypeDesc(String leaveTypeDesc)
  {
    this.leaveTypeDesc = leaveTypeDesc;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
}
