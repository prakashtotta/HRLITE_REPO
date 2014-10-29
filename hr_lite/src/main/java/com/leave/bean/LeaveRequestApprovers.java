package com.leave.bean;

public class LeaveRequestApprovers
{
  public long leaveReqApproverId;
  public long leaveReqId;
  public long userId;
  public int level;
  public String approverComment;
  
  public long getLeaveReqApproverId()
  {
    return this.leaveReqApproverId;
  }
  
  public void setLeaveReqApproverId(long leaveReqApproverId)
  {
    this.leaveReqApproverId = leaveReqApproverId;
  }
  
  public long getLeaveReqId()
  {
    return this.leaveReqId;
  }
  
  public void setLeaveReqId(long leaveReqId)
  {
    this.leaveReqId = leaveReqId;
  }
  
  public long getUserId()
  {
    return this.userId;
  }
  
  public void setUserId(long userId)
  {
    this.userId = userId;
  }
  
  public int getLevel()
  {
    return this.level;
  }
  
  public void setLevel(int level)
  {
    this.level = level;
  }
  
  public String getApproverComment()
  {
    return this.approverComment;
  }
  
  public void setApproverComment(String approverComment)
  {
    this.approverComment = approverComment;
  }
}
