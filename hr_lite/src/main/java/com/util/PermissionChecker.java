package com.util;

import com.bean.Permissions;
import com.bean.Role;
import com.bean.User;
import java.util.Iterator;
import java.util.Set;

public class PermissionChecker
{
  public static boolean isPermissionApplied(String permissionCode, User user)
  {
    boolean isPerapplied = false;
    Role role = user.getRole();
    if (role != null)
    {
      Set permissions = role.getPermissions();
      if (permissions != null)
      {
        Iterator itr = permissions.iterator();
        while (itr.hasNext())
        {
          Permissions permission = (Permissions)itr.next();
          if ((permission.getPerCode() != null) && (permission.getPerCode().equals(permissionCode)))
          {
            isPerapplied = true;
            break;
          }
        }
      }
    }
    return isPerapplied;
  }
  
  public static boolean isPermissionApplied(String permissionCode, Role role)
  {
    boolean isPerapplied = false;
    if (role != null)
    {
      Set permissions = role.getPermissions();
      if (permissions != null)
      {
        Iterator itr = permissions.iterator();
        while (itr.hasNext())
        {
          Permissions permission = (Permissions)itr.next();
          if ((permission.getPerCode() != null) && (permission.getPerCode().equals(permissionCode)))
          {
            isPerapplied = true;
            break;
          }
        }
      }
    }
    return isPerapplied;
  }
}
