package com.bo;

import com.bean.DashBoardReport;
import com.bean.ReportNames;
import com.bean.User;
import com.dao.ReportTXDAO;
import java.util.Date;
import org.apache.log4j.Logger;

public class ReportTXBO
{
  protected static final Logger logger = Logger.getLogger(ReportTXBO.class);
  ReportTXDAO reporttxdao;
  
  public ReportTXDAO getReporttxdao()
  {
    return this.reporttxdao;
  }
  
  public void setReporttxdao(ReportTXDAO reporttxdao)
  {
    this.reporttxdao = reporttxdao;
  }
  
  public void updateDashboardReports(String[] reports, User user, long userId)
  {
    logger.info("Inside updateDashboardReports method start");
    
    getReporttxdao().deleteReportsFromDashboard(userId);
    if ((reports != null) && (reports.length > 0)) {
      for (int i = 0; i < reports.length; i++)
      {
        DashBoardReport dash = new DashBoardReport();
        dash.setUserid(userId);
        ReportNames report = new ReportNames();
        report.setReportId(new Integer(reports[i]).intValue());
        dash.setReport(report);
        dash.setCreatedBy(user.getUserName());
        dash.setCreatedDate(new Date());
        dash.setUpdatedBy(user.getUserName());
        dash.setUpdatedDate(new Date());
        
        getReporttxdao().saveDashBoardReport(dash);
      }
    }
    logger.info("Inside updateDashboardReports method end");
  }
}
