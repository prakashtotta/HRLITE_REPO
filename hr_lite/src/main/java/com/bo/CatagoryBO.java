package com.bo;

import com.bean.User;
import com.dao.LovDAO;
import java.util.List;

public class CatagoryBO
{
  public static List getAllCatagoryForPagination(int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return LovDAO.getAllCatagoryForPagination(pageSize, startIndex, dir_str, sort_str);
  }
  
  public static List getAllCatagory()
  {
    return LovDAO.getAllCatagory();
  }
  
  public static List getAllCatagoryForPaginationBySearchCriteria(User user, String name, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return LovDAO.getAllCatagoryForPaginationBySearchCriteria(user, name, pageSize, startIndex, dir_str, sort_str);
  }
  
  public static List getAllCatagoryBySearchCriteria(User user, String name)
  {
    return LovDAO.getAllCatagoryBySearchCriteria(user, name);
  }
}
