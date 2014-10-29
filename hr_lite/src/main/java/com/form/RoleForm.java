package com.form;

import java.util.Date;
import java.util.List;
import org.apache.struts.action.ActionForm;

public class RoleForm
  extends ActionForm
{
  long roleId;
  String roleCode;
  String roleName;
  String roleDesc;
  String status;
  public String readPreview;
  public String createdBy;
  public Date createdDate;
  private Date updatedDate;
  private String updatedBy;
  List users;
  List permissionsList;
  List fromColumnsList;
  List toColumnsList;
  String[] permission;
  
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
  
  public List getPermissionsList()
  {
    return this.permissionsList;
  }
  
  public void setPermissionsList(List permissionsList)
  {
    this.permissionsList = permissionsList;
  }
  
  public String[] getPermission()
  {
    return this.permission;
  }
  
  public void setPermission(String[] permission)
  {
    this.permission = permission;
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
  
  public String getReadPreview()
  {
    return this.readPreview;
  }
  
  public void setReadPreview(String readPreview)
  {
    this.readPreview = readPreview;
  }
  
  public List getFromColumnsList()
  {
    return this.fromColumnsList;
  }
  
  public void setFromColumnsList(List fromColumnsList)
  {
    this.fromColumnsList = fromColumnsList;
  }
  
  public List getToColumnsList()
  {
    return this.toColumnsList;
  }
  
  public void setToColumnsList(List toColumnsList)
  {
    this.toColumnsList = toColumnsList;
  }
}
