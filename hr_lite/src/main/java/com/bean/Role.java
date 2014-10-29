package com.bean;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class Role
{
  public long roleId;
  public String roleCode;
  public String roleName;
  public String roleDesc;
  String status;
  public String createdBy;
  public Date createdDate;
  private Date updatedDate;
  private String updatedBy;
  public long super_user_key;
  List users;
  Set permissions;
  
  public long getRoleId()
  {
    return this.roleId;
  }
  
  public void setRoleId(long roleId)
  {
    this.roleId = roleId;
  }
  
  public String getRoleCode()
  {
    return this.roleCode;
  }
  
  public void setRoleCode(String roleCode)
  {
    this.roleCode = roleCode;
  }
  
  public String getRoleName()
  {
    return this.roleName;
  }
  
  public void setRoleName(String roleName)
  {
    this.roleName = roleName;
  }
  
  public String getRoleDesc()
  {
    return this.roleDesc;
  }
  
  public void setRoleDesc(String roleDesc)
  {
    this.roleDesc = roleDesc;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public List getUsers()
  {
    return this.users;
  }
  
  public void setUsers(List users)
  {
    this.users = users;
  }
  
  public Set getPermissions()
  {
    return this.permissions;
  }
  
  public void setPermissions(Set permissions)
  {
    this.permissions = permissions;
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
  
  public long getSuper_user_key()
  {
    return this.super_user_key;
  }
  
  public void setSuper_user_key(long superUserKey)
  {
    this.super_user_key = superUserKey;
  }
}
