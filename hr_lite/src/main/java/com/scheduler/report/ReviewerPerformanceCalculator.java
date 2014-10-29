package com.scheduler.report;

import com.bo.BOFactory;
import com.bo.DashBoardBO;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class ReviewerPerformanceCalculator
  implements Job
{
  protected static final Logger logger = Logger.getLogger(ReviewerPerformanceCalculator.class);
  
  public void execute(JobExecutionContext context)
    throws JobExecutionException
  {
    logger.info("start ReviewerPerformanceCalculator run");
    try
    {
      List reviewerIdList = BOFactory.getDashBoardBO().getAllReviewerIdsFromJobApplicantionsEvent();
      for (int i = 0; i < reviewerIdList.size(); i++)
      {
        BigInteger revid = (BigInteger)reviewerIdList.get(i);
        
        logger.info("started for reviewer id" + revid.longValue());
        doReviewerPerformance(revid.longValue());
        doScreenerPerformance(revid.longValue());
        logger.info("end for reviewer id" + revid.longValue());
      }
    }
    catch (Exception e)
    {
      logger.info(e);
    }
    logger.info("end ReviewerPerformanceCalculator run");
  }
  
  private void doReviewerPerformance(long reviewerId)
  {
    logger.info("start doReviewerPerformance ");
    

    List plist = BOFactory.getDashBoardBO().getPerformanceDataByReviewerIdAndType(reviewerId, "REVIEW");
    logger.info("plist size" + plist);
    if ((plist != null) && (plist.size() > 0))
    {
      Calendar cal = Calendar.getInstance();
      int currentYear = cal.get(1);
      
      BOFactory.getDashBoardBO().calculatePerformanceByReviewerAndYear(reviewerId, currentYear);
    }
    else
    {
      Set years = BOFactory.getDashBoardBO().getApplicantsReviewedDistinctYearsName(reviewerId);
      Iterator itr = years.iterator();
      while (itr.hasNext())
      {
        Integer year = (Integer)itr.next();
        BOFactory.getDashBoardBO().calculatePerformanceByReviewerAndYear(reviewerId, year.intValue());
      }
    }
  }
  
  private void doScreenerPerformance(long reviewerId)
  {
    logger.info("start doReviewerPerformance ");
    

    List plist = BOFactory.getDashBoardBO().getPerformanceDataByReviewerIdAndType(reviewerId, "SCREENING");
    logger.info("plist size" + plist);
    if ((plist != null) && (plist.size() > 0))
    {
      Calendar cal = Calendar.getInstance();
      int currentYear = cal.get(1);
      
      BOFactory.getDashBoardBO().calculatePerformanceByScreenerIdAndYear(reviewerId, currentYear);
    }
    else
    {
      Set years = BOFactory.getDashBoardBO().getApplicantsScreeninedDistinctYearsName(reviewerId);
      Iterator itr = years.iterator();
      while (itr.hasNext())
      {
        Integer year = (Integer)itr.next();
        BOFactory.getDashBoardBO().calculatePerformanceByScreenerIdAndYear(reviewerId, year.intValue());
      }
    }
  }
}
