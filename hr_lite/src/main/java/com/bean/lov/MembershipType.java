package com.bean.lov;

public class MembershipType
{
  public long membershipTypeId;
  public String membershipTypeName;
  public String membershipTypeDesc;
  public String status;
  public long super_user_key;
  
  public long getMembershipTypeId()
  {
    return this.membershipTypeId;
  }
  
  public void setMembershipTypeId(long membershipTypeId)
  {
    this.membershipTypeId = membershipTypeId;
  }
  
  public String getMembershipTypeName()
  {
    return this.membershipTypeName;
  }
  
  public void setMembershipTypeName(String membershipTypeName)
  {
    this.membershipTypeName = membershipTypeName;
  }
  
  public String getMembershipTypeDesc()
  {
    return this.membershipTypeDesc;
  }
  
  public void setMembershipTypeDesc(String membershipTypeDesc)
  {
    this.membershipTypeDesc = membershipTypeDesc;
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
