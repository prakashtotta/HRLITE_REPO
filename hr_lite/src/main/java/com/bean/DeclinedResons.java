package com.bean;

public class DeclinedResons
{
  public int offerdeclinedreasonId;
  public String offerdecliedreasonName;
  public String offerdecliedreasonDesc;
  public String resonType;
  public String status;
  public long super_user_key;
  
  public long getSuper_user_key()
  {
    return this.super_user_key;
  }
  
  public void setSuper_user_key(long superUserKey)
  {
    this.super_user_key = superUserKey;
  }
  
  public int getOfferdeclinedreasonId()
  {
    return this.offerdeclinedreasonId;
  }
  
  public void setOfferdeclinedreasonId(int offerdeclinedreasonId)
  {
    this.offerdeclinedreasonId = offerdeclinedreasonId;
  }
  
  public String getOfferdecliedreasonName()
  {
    return this.offerdecliedreasonName;
  }
  
  public void setOfferdecliedreasonName(String offerdecliedreasonName)
  {
    this.offerdecliedreasonName = offerdecliedreasonName;
  }
  
  public String getOfferdecliedreasonDesc()
  {
    return this.offerdecliedreasonDesc;
  }
  
  public void setOfferdecliedreasonDesc(String offerdecliedreasonDesc)
  {
    this.offerdecliedreasonDesc = offerdecliedreasonDesc;
  }
  
  public String getResonType()
  {
    return this.resonType;
  }
  
  public void setResonType(String resonType)
  {
    this.resonType = resonType;
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
