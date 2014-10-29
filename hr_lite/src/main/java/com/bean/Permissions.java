package com.bean;

import java.util.Set;

public class Permissions
{
  long perId;
  String perCode;
  String perName;
  String perDesc;
  String status;
  Set roles;
  
  public long getPerId()
  {
    return this.perId;
  }
  
  public void setPerId(long perId)
  {
    this.perId = perId;
  }
  
  public String getPerCode()
  {
    return this.perCode;
  }
  
  public void setPerCode(String perCode)
  {
    this.perCode = perCode;
  }
  
  public String getPerName()
  {
    return this.perName;
  }
  
  public void setPerName(String perName)
  {
    this.perName = perName;
  }
  
  public String getPerDesc()
  {
    return this.perDesc;
  }
  
  public void setPerDesc(String perDesc)
  {
    this.perDesc = perDesc;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public Set getRoles()
  {
    return this.roles;
  }
  
  public void setRoles(Set roles)
  {
    this.roles = roles;
  }
}
