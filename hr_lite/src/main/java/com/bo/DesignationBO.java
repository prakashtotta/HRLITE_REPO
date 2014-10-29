package com.bo;

import com.bean.User;
import com.dao.LovTXDAO;
import java.util.List;

public class DesignationBO
{
  LovTXDAO lovtxdao;
  
  public List getAllLocationNameList(long superUserKey)
  {
    return this.lovtxdao.getLocationList(superUserKey);
  }
  
  public List getAllDesignationDetailsForPagination(int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.lovtxdao.getAllDesignationDetailsForPagination(pageSize, startIndex, dir_str, sort_str);
  }
  
  public List getAllDesignationDetailsForPagination(User user, String designationName, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.lovtxdao.getAllDesignationDetailsForPagination(user, designationName, pageSize, startIndex, dir_str, sort_str);
  }
  
  public List getAllLocationDetails(long superUserKey)
    throws Exception
  {
    return this.lovtxdao.getAllLocationDetails(superUserKey);
  }
  
  public int getCountOfAllDesignations()
  {
    return this.lovtxdao.getCountOfAllDesignations();
  }
  
  public int getCountOfAllDesignations(User user, String designationName)
  {
    return this.lovtxdao.getCountOfAllDesignations(user, designationName);
  }
  
  public LovTXDAO getLovtxdao()
  {
    return this.lovtxdao;
  }
  
  public void setLovtxdao(LovTXDAO lovtxdao)
  {
    this.lovtxdao = lovtxdao;
  }
  
  public String isDesignationCodeExist(String designationCode, long superUserKey)
  {
    return this.lovtxdao.isDesignationCodeExist(designationCode, superUserKey);
  }
}
