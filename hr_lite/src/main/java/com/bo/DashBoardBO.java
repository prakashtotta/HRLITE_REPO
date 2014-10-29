package com.bo;

import com.bean.User;
import com.bean.lov.KeyValue;
import com.bean.report.ReviewerPerformance;
import com.dao.DashBoardDAO;
import com.resources.Constant;
import com.util.StringUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.log4j.Logger;

public class DashBoardBO
{
  DashBoardDAO dashboarddao;
  protected static final Logger logger = Logger.getLogger(DashBoardBO.class);
  
  public List getAllReviewerIdsFromJobApplicantionsEvent()
  {
    return this.dashboarddao.getAllReviewerIdsFromJobApplicantionsEvent();
  }
  
  public List getPerformanceDataByReviewerIdAndType(long reviewerId, String type)
  {
    return this.dashboarddao.getPerformanceDataByReviewerIdAndType(reviewerId, type);
  }
  
  public ReviewerPerformance calculatePerformanceByReviewerAndYear(long reviewerId, int year)
  {
    return this.dashboarddao.calculatePerformanceByReviewerAndYear(reviewerId, year);
  }
  
  public Set getApplicantsReviewedDistinctYearsName(long reviewerId)
  {
    return this.dashboarddao.getApplicantsReviewedDistinctYearsName(reviewerId);
  }
  
  public ReviewerPerformance calculatePerformanceByScreenerIdAndYear(long reviewerId, int year)
  {
    return this.dashboarddao.calculatePerformanceByScreenerIdAndYear(reviewerId, year);
  }
  
  public Set getApplicantsScreeninedDistinctYearsName(long reviewerId)
  {
    return this.dashboarddao.getApplicantsScreeninedDistinctYearsName(reviewerId);
  }
  
  public Map createJSONGraphReviewerApplicantSummary(long reviewerId, User user)
  {
    logger.info("inside createJSONGraphReviewerApplicantSummary");
    Map m = new HashMap();
    String str = "";
    String fields = "[ \"reviewerapplicantsummary\"";
    String seriesdef = "";
    
    Set years = this.dashboarddao.getApplicantsReviewedDistinctYearsName(reviewerId);
    


    int k = 0;
    Iterator itr = years.iterator();
    while (itr.hasNext())
    {
      Integer year = (Integer)itr.next();
      String yearstr = String.valueOf(year);
      List statewithcountlist = this.dashboarddao.getApplicantsStateAndCountByReviewerAndYear(reviewerId, year.intValue());
      if (!StringUtils.isNullOrEmpty(yearstr)) {
        yearstr = yearstr.replace("\"", "");
      }
      String tempstr = "{ reviewerapplicantsummary: \"" + yearstr + "\"";
      for (int j = 0; j < statewithcountlist.size(); j++)
      {
        KeyValue keyv = (KeyValue)statewithcountlist.get(j);
        String intstate = keyv.getKey();
        logger.info("key" + intstate);
        if (!StringUtils.isNullOrEmpty(intstate))
        {
          intstate = intstate.replace("-", "_");
          intstate = intstate.replace(" ", "_");
        }
        tempstr = tempstr + ", " + intstate + ":" + keyv.getValue();
        if (k == 0)
        {
          fields = fields + ", " + "\"" + intstate + "\"";
          seriesdef = seriesdef + "{ displayName: " + "\"" + Constant.getResourceStringValueWithDefault(intstate, user.getLocale()) + "\"" + ", " + "yField: " + "\"" + intstate + "\"" + " }" + ",";
        }
      }
      tempstr = tempstr + " },";
      str = str + tempstr;
      k++;
    }
    if (!StringUtils.isNullOrEmpty(str)) {
      str = str.substring(0, str.length() - 1);
    }
    fields = fields + " ]";
    if (!StringUtils.isNullOrEmpty(seriesdef)) {
      seriesdef = seriesdef.substring(0, seriesdef.length() - 1);
    }
    m.put("detail", str);
    m.put("fields", fields);
    m.put("seriesdef", seriesdef);
    
    return m;
  }
  
  public Map createJSONGraphReviewerApplicantScreeningSummary(long reviewerId, User user)
  {
    logger.info("inside createJSONGraphReviewerApplicantScreeningSummary");
    Map m = new HashMap();
    String str = "";
    String fields = "[ \"screenerapplicantsummary\"";
    String seriesdef = "";
    
    Set years = this.dashboarddao.getApplicantsScreeninedDistinctYearsName(reviewerId);
    


    int k = 0;
    Iterator itr = years.iterator();
    while (itr.hasNext())
    {
      Integer year = (Integer)itr.next();
      String yearstr = String.valueOf(year);
      List statewithcountlist = this.dashboarddao.getApplicantsStateAndCountByScreenerAndYear(reviewerId, year.intValue());
      if (!StringUtils.isNullOrEmpty(yearstr)) {
        yearstr = yearstr.replace("\"", "");
      }
      String tempstr = "{ screenerapplicantsummary: \"" + yearstr + "\"";
      for (int j = 0; j < statewithcountlist.size(); j++)
      {
        KeyValue keyv = (KeyValue)statewithcountlist.get(j);
        String intstate = keyv.getKey();
        logger.info("key" + intstate);
        if (!StringUtils.isNullOrEmpty(intstate))
        {
          intstate = intstate.replace("-", "_");
          intstate = intstate.replace(" ", "_");
        }
        tempstr = tempstr + ", " + intstate + ":" + keyv.getValue();
        if (k == 0)
        {
          fields = fields + ", " + "\"" + intstate + "\"";
          seriesdef = seriesdef + "{ displayName: " + "\"" + Constant.getResourceStringValueWithDefault(intstate, user.getLocale()) + "\"" + ", " + "yField: " + "\"" + intstate + "\"" + " }" + ",";
        }
      }
      tempstr = tempstr + " },";
      str = str + tempstr;
      k++;
    }
    if (!StringUtils.isNullOrEmpty(str)) {
      str = str.substring(0, str.length() - 1);
    }
    fields = fields + " ]";
    if (!StringUtils.isNullOrEmpty(seriesdef)) {
      seriesdef = seriesdef.substring(0, seriesdef.length() - 1);
    }
    m.put("detail", str);
    m.put("fields", fields);
    m.put("seriesdef", seriesdef);
    
    return m;
  }
  
  public Map createJSONGraphReviewerPerformanceSummary(long reviewerId, User user)
  {
    logger.info("inside createJSONGraphReviewerPerformanceSummary");
    Map m = new HashMap();
    String str = "";
    String fields = "[ \"reviewerperformancesummary\"";
    String seriesdef = "";
    
    Set years = this.dashboarddao.getApplicantsPerformanceDistinctYearsName(reviewerId, "REVIEW");
    


    int k = 0;
    Iterator itr = years.iterator();
    while (itr.hasNext())
    {
      Integer year = (Integer)itr.next();
      String yearstr = String.valueOf(year);
      List statewithcountlist = this.dashboarddao.getPerformanceByReviewerAndYear(reviewerId, year.intValue(), "REVIEW");
      if (!StringUtils.isNullOrEmpty(yearstr)) {
        yearstr = yearstr.replace("\"", "");
      }
      String tempstr = "{ reviewerperformancesummary: \"" + yearstr + "\"";
      for (int j = 0; j < statewithcountlist.size(); j++)
      {
        KeyValue keyv = (KeyValue)statewithcountlist.get(j);
        String intstate = keyv.getKey();
        logger.info("key" + intstate);
        if (!StringUtils.isNullOrEmpty(intstate))
        {
          intstate = intstate.replace("-", "_");
          intstate = intstate.replace(" ", "_");
        }
        tempstr = tempstr + ", " + intstate + ":" + keyv.getValue();
        if (k == 0)
        {
          fields = fields + ", " + "\"" + intstate + "\"";
          seriesdef = seriesdef + "{ displayName: " + "\"" + Constant.getResourceStringValueWithDefault(intstate, user.getLocale()) + "\"" + ", " + "yField: " + "\"" + intstate + "\"" + " }" + ",";
        }
      }
      tempstr = tempstr + " },";
      str = str + tempstr;
      k++;
    }
    if (!StringUtils.isNullOrEmpty(str)) {
      str = str.substring(0, str.length() - 1);
    }
    fields = fields + " ]";
    if (!StringUtils.isNullOrEmpty(seriesdef)) {
      seriesdef = seriesdef.substring(0, seriesdef.length() - 1);
    }
    m.put("detail", str);
    m.put("fields", fields);
    m.put("seriesdef", seriesdef);
    
    return m;
  }
  
  public Map createJSONGraphScreenerPerformanceSummary(long reviewerId, User user)
  {
    logger.info("inside createJSONGraphScreenerPerformanceSummary");
    Map m = new HashMap();
    String str = "";
    String fields = "[ \"screenerperformancesummary\"";
    String seriesdef = "";
    
    Set years = this.dashboarddao.getApplicantsPerformanceDistinctYearsName(reviewerId, "SCREENING");
    


    int k = 0;
    Iterator itr = years.iterator();
    while (itr.hasNext())
    {
      Integer year = (Integer)itr.next();
      String yearstr = String.valueOf(year);
      List statewithcountlist = this.dashboarddao.getPerformanceByReviewerAndYear(reviewerId, year.intValue(), "SCREENING");
      if (!StringUtils.isNullOrEmpty(yearstr)) {
        yearstr = yearstr.replace("\"", "");
      }
      String tempstr = "{ screenerperformancesummary: \"" + yearstr + "\"";
      for (int j = 0; j < statewithcountlist.size(); j++)
      {
        KeyValue keyv = (KeyValue)statewithcountlist.get(j);
        String intstate = keyv.getKey();
        logger.info("key" + intstate);
        if (!StringUtils.isNullOrEmpty(intstate))
        {
          intstate = intstate.replace("-", "_");
          intstate = intstate.replace(" ", "_");
        }
        tempstr = tempstr + ", " + intstate + ":" + keyv.getValue();
        if (k == 0)
        {
          fields = fields + ", " + "\"" + intstate + "\"";
          seriesdef = seriesdef + "{ displayName: " + "\"" + Constant.getResourceStringValueWithDefault(intstate, user.getLocale()) + "\"" + ", " + "yField: " + "\"" + intstate + "\"" + " }" + ",";
        }
      }
      tempstr = tempstr + " },";
      str = str + tempstr;
      k++;
    }
    if (!StringUtils.isNullOrEmpty(str)) {
      str = str.substring(0, str.length() - 1);
    }
    fields = fields + " ]";
    if (!StringUtils.isNullOrEmpty(seriesdef)) {
      seriesdef = seriesdef.substring(0, seriesdef.length() - 1);
    }
    m.put("detail", str);
    m.put("fields", fields);
    m.put("seriesdef", seriesdef);
    
    return m;
  }
  
  public Map createJSONGraphForHiringManagerRequistionSummary(long hiringMgrId, User user)
  {
    logger.info("inside createJSONGraphForHiringManagerRequistionSummary");
    Map m = new HashMap();
    String str = "";
    String fields = "[ \"hiringmanagerrequistionummary\"";
    String seriesdef = "";
    
    Set years = this.dashboarddao.getDistinctYearsNameOfRequistionsByHiringMgr(hiringMgrId);
    


    int k = 0;
    Iterator itr = years.iterator();
    while (itr.hasNext())
    {
      Integer year = (Integer)itr.next();
      String yearstr = String.valueOf(year);
      List statewithcountlist = this.dashboarddao.getRequistionsStateAndCountByHiringMgrAndYear(hiringMgrId, year.intValue());
      if (!StringUtils.isNullOrEmpty(yearstr)) {
        yearstr = yearstr.replace("\"", "");
      }
      String tempstr = "{ hiringmanagerrequistionummary: \"" + yearstr + "\"";
      for (int j = 0; j < statewithcountlist.size(); j++)
      {
        KeyValue keyv = (KeyValue)statewithcountlist.get(j);
        String intstate = keyv.getKey();
        logger.info("key" + intstate);
        if (!StringUtils.isNullOrEmpty(intstate))
        {
          intstate = intstate.replace("-", "_");
          intstate = intstate.replace(" ", "_");
        }
        tempstr = tempstr + ", " + intstate + ":" + keyv.getValue();
        if (k == 0)
        {
          fields = fields + ", " + "\"" + intstate + "\"";
          seriesdef = seriesdef + "{ displayName: " + "\"" + Constant.getResourceStringValueWithDefault(intstate, user.getLocale()) + "\"" + ", " + "yField: " + "\"" + intstate + "\"" + " }" + ",";
        }
      }
      tempstr = tempstr + " },";
      str = str + tempstr;
      k++;
    }
    if (!StringUtils.isNullOrEmpty(str)) {
      str = str.substring(0, str.length() - 1);
    }
    fields = fields + " ]";
    if (!StringUtils.isNullOrEmpty(seriesdef)) {
      seriesdef = seriesdef.substring(0, seriesdef.length() - 1);
    }
    m.put("detail", str);
    m.put("fields", fields);
    m.put("seriesdef", seriesdef);
    
    return m;
  }
  
  public Map createJSONGraphForRecruiterRequistionSummary(long hiringMgrId, User user)
  {
    logger.info("inside createJSONGraphForRecruiterRequistionSummary");
    Map m = new HashMap();
    String str = "";
    String fields = "[ \"recruiterrequistionummary\"";
    String seriesdef = "";
    
    Set years = this.dashboarddao.getDistinctYearsNameOfRequistionsByRecruiter(hiringMgrId);
    


    int k = 0;
    Iterator itr = years.iterator();
    while (itr.hasNext())
    {
      Integer year = (Integer)itr.next();
      String yearstr = String.valueOf(year);
      List statewithcountlist = this.dashboarddao.getRequistionsStateAndCountByRecruiterAndYear(hiringMgrId, year.intValue());
      if (!StringUtils.isNullOrEmpty(yearstr)) {
        yearstr = yearstr.replace("\"", "");
      }
      String tempstr = "{ recruiterrequistionummary: \"" + yearstr + "\"";
      for (int j = 0; j < statewithcountlist.size(); j++)
      {
        KeyValue keyv = (KeyValue)statewithcountlist.get(j);
        String intstate = keyv.getKey();
        logger.info("key" + intstate);
        if (!StringUtils.isNullOrEmpty(intstate))
        {
          intstate = intstate.replace("-", "_");
          intstate = intstate.replace(" ", "_");
        }
        tempstr = tempstr + ", " + intstate + ":" + keyv.getValue();
        if (k == 0)
        {
          fields = fields + ", " + "\"" + intstate + "\"";
          seriesdef = seriesdef + "{ displayName: " + "\"" + Constant.getResourceStringValueWithDefault(intstate, user.getLocale()) + "\"" + ", " + "yField: " + "\"" + intstate + "\"" + " }" + ",";
        }
      }
      tempstr = tempstr + " },";
      str = str + tempstr;
      k++;
    }
    if (!StringUtils.isNullOrEmpty(str)) {
      str = str.substring(0, str.length() - 1);
    }
    fields = fields + " ]";
    if (!StringUtils.isNullOrEmpty(seriesdef)) {
      seriesdef = seriesdef.substring(0, seriesdef.length() - 1);
    }
    m.put("detail", str);
    m.put("fields", fields);
    m.put("seriesdef", seriesdef);
    
    return m;
  }
  
  public Map createJSONGraphForHiringManagerOpenRequistionSummary(long hiringMgrId, User user)
  {
    logger.info("inside createJSONGraphForHiringManagerOpenRequistionSummary");
    Map m = new HashMap();
    String str = "";
    String fields = "[ \"hiringmanagerrequistionummaryopen\"";
    String seriesdef = "";
    
    List reqList = this.dashboarddao.getDistinctRequistionIdsOpenByHiringMgr(hiringMgrId);
    


    int k = 0;
    for (int i = 0; i < reqList.size(); i++)
    {
      Object[] obj = (Object[])reqList.get(i);
      Long reqId = (Long)obj[0];
      String reqName = (String)obj[1];
      
      List statewithcountlist = this.dashboarddao.getRequistionsSummary(reqId.longValue());
      if (!StringUtils.isNullOrEmpty(reqName)) {
        reqName = reqName.replace("\"", "");
      }
      String tempstr = "{ hiringmanagerrequistionummaryopen: \"" + reqName + "\"";
      for (int j = 0; j < statewithcountlist.size(); j++)
      {
        KeyValue keyv = (KeyValue)statewithcountlist.get(j);
        String intstate = keyv.getKey();
        logger.info("key" + intstate);
        if (!StringUtils.isNullOrEmpty(intstate))
        {
          intstate = intstate.replace("-", "_");
          intstate = intstate.replace(" ", "_");
        }
        tempstr = tempstr + ", " + intstate + ":" + keyv.getValue();
        if (k == 0)
        {
          fields = fields + ", " + "\"" + intstate + "\"";
          seriesdef = seriesdef + "{ displayName: " + "\"" + Constant.getResourceStringValueWithDefault(intstate, user.getLocale()) + "\"" + ", " + "yField: " + "\"" + intstate + "\"" + " }" + ",";
        }
      }
      tempstr = tempstr + " },";
      str = str + tempstr;
      k++;
    }
    if (!StringUtils.isNullOrEmpty(str)) {
      str = str.substring(0, str.length() - 1);
    }
    fields = fields + " ]";
    if (!StringUtils.isNullOrEmpty(seriesdef)) {
      seriesdef = seriesdef.substring(0, seriesdef.length() - 1);
    }
    m.put("detail", str);
    m.put("fields", fields);
    m.put("seriesdef", seriesdef);
    
    return m;
  }
  
  public Map createJSONGraphForRecruiterOpenRequistionSummary(long recruiterId, User user)
  {
    logger.info("inside createJSONGraphForRecruiterOpenRequistionSummary");
    Map m = new HashMap();
    String str = "";
    String fields = "[ \"recruiterrequistionummaryopen\"";
    String seriesdef = "";
    
    List reqList = this.dashboarddao.getDistinctRequistionIdsOpenByRecruiter(recruiterId);
    


    int k = 0;
    for (int i = 0; i < reqList.size(); i++)
    {
      Object[] obj = (Object[])reqList.get(i);
      Long reqId = (Long)obj[0];
      String reqName = (String)obj[1];
      
      List statewithcountlist = this.dashboarddao.getRequistionsSummary(reqId.longValue());
      if (!StringUtils.isNullOrEmpty(reqName)) {
        reqName = reqName.replace("\"", "");
      }
      String tempstr = "{ recruiterrequistionummaryopen: \"" + reqName + "\"";
      for (int j = 0; j < statewithcountlist.size(); j++)
      {
        KeyValue keyv = (KeyValue)statewithcountlist.get(j);
        String intstate = keyv.getKey();
        logger.info("key" + intstate);
        if (!StringUtils.isNullOrEmpty(intstate))
        {
          intstate = intstate.replace("-", "_");
          intstate = intstate.replace(" ", "_");
        }
        tempstr = tempstr + ", " + intstate + ":" + keyv.getValue();
        if (k == 0)
        {
          fields = fields + ", " + "\"" + intstate + "\"";
          seriesdef = seriesdef + "{ displayName: " + "\"" + Constant.getResourceStringValueWithDefault(intstate, user.getLocale()) + "\"" + ", " + "yField: " + "\"" + intstate + "\"" + " }" + ",";
        }
      }
      tempstr = tempstr + " },";
      str = str + tempstr;
      k++;
    }
    if (!StringUtils.isNullOrEmpty(str)) {
      str = str.substring(0, str.length() - 1);
    }
    fields = fields + " ]";
    if (!StringUtils.isNullOrEmpty(seriesdef)) {
      seriesdef = seriesdef.substring(0, seriesdef.length() - 1);
    }
    m.put("detail", str);
    m.put("fields", fields);
    m.put("seriesdef", seriesdef);
    
    return m;
  }
  
  public DashBoardDAO getdashboarddao()
  {
    return this.dashboarddao;
  }
  
  public void setdashboarddao(DashBoardDAO dashboarddao)
  {
    this.dashboarddao = dashboarddao;
  }
}
