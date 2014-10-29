package com.bo;

import com.dao.TaskDAO;
import java.util.List;

public class OnBoardingTemplateBO
{
  public static List getAllOnBoardingTemplateForPagination(long super_user_key, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return TaskDAO.getAllOnBoardingTemplateForPagination(super_user_key, pageSize, startIndex, dir_str, sort_str);
  }
  
  public static int getAllOnBoardingTemplateDetails(long super_user_key)
  {
    return TaskDAO.getAllOnBoardingTemplateDetails(super_user_key);
  }
  
  public static List getAllOnBoardingTemplateForPaginationSearch(long super_user_key, String onboardtemname, String primaryownerid, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return TaskDAO.getAllOnBoardingTemplateForPaginationSearch(super_user_key, onboardtemname, primaryownerid, pageSize, startIndex, dir_str, sort_str);
  }
  
  public static int getAllOnBoardingTemplateDetailssearch(long super_user_key, String onboardtemname, String primaryownerid)
  {
    return TaskDAO.getAllOnBoardingTemplateDetailssearch(super_user_key, onboardtemname, primaryownerid);
  }
}
