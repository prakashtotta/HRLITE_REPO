package com.bo;

import com.dao.SelectionDAO;
import java.util.List;

public class SelectionBO
{
  public static List getSelectionsListForPagination(int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return SelectionDAO.getSelectionsListForPagination(pageSize, startIndex, dir_str, sort_str);
  }
  
  public static List getAllSelectionsList()
  {
    return SelectionDAO.getAllSelectionsList();
  }
}
