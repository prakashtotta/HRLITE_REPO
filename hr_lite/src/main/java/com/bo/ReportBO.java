package com.bo;

import com.dao.ReportDAO;
import java.util.List;

public class ReportBO
{
  public static List getAllDashBoardReportByUserId(long userId)
  {
    return ReportDAO.getAllDashBoardReportByUserId(userId);
  }
}
