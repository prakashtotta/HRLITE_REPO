package com.bo;

import com.dao.LovDAO;
import java.util.List;

public class ProjectCodeBO
{
  LovDAO lovdao;
  
  public List getAllProjectCodeDetailsForPagination(int pageSize, int startIndex, String dir_str, String sort_str, long orgId, long departmentId)
  {
    return this.lovdao.getAllProjectCodeDetailsForPagination(pageSize, startIndex, dir_str, sort_str, orgId, departmentId);
  }
  
  public List getAllProjectCodeDetailsforpagination(long orgId, long departmentId)
  {
    return this.lovdao.getAllProjectCodeDetailsforpagination(orgId, departmentId);
  }
  
  public List getAllProjectCodeDetails()
  {
    return this.lovdao.getAllProjectCodeDetails();
  }
  
  public LovDAO getLovdao()
  {
    return this.lovdao;
  }
  
  public void setLovdao(LovDAO lovdao)
  {
    this.lovdao = lovdao;
  }
  
  public String isProjectCodeExist(String projectcode)
  {
    return this.lovdao.isProjectCodeExist(projectcode);
  }
}
