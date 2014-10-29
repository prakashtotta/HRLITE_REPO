package com.bo;

import com.bean.User;
import com.dao.LovDAO;
import java.util.List;

public class RoleBO
{
  LovDAO lovdao;
  
  public List getAllRolesForPagination(User user, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.lovdao.getAllRolesForPagination(user, pageSize, startIndex, dir_str, sort_str);
  }
  
  public List getAllRoles(long superUserKey)
  {
    return this.lovdao.getAllRoles(superUserKey);
  }
  
  public LovDAO getLovdao()
  {
    return this.lovdao;
  }
  
  public void setLovdao(LovDAO lovdao)
  {
    this.lovdao = lovdao;
  }
  
  public String isRoleCodeExist(String projectcode, long superUserKey)
  {
    return this.lovdao.isRoleCodeExist(projectcode, superUserKey);
  }
}
