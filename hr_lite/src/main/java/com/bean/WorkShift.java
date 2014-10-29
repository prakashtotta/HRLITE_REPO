package com.bean;

public class WorkShift
{
  public long shiftId;
  public String shiftName;
  public String shiftDesc;
  public String status;
  public long super_user_key;
  
  public long getShiftId()
  {
    return this.shiftId;
  }
  
  public void setShiftId(long shiftId)
  {
    this.shiftId = shiftId;
  }
  
  public String getShiftName()
  {
    return this.shiftName;
  }
  
  public void setShiftName(String shiftName)
  {
    this.shiftName = shiftName;
  }
  
  public String getShiftDesc()
  {
    return this.shiftDesc;
  }
  
  public void setShiftDesc(String shiftDesc)
  {
    this.shiftDesc = shiftDesc;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public long getSuper_user_key()
  {
    return this.super_user_key;
  }
  
  public void setSuper_user_key(long superUserKey)
  {
    this.super_user_key = superUserKey;
  }
}
