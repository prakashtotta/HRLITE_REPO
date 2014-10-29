package com.scheduler;

import com.bean.JobRequisition;
import com.bo.BOFactory;
import com.bo.JobRequistionBO;
import com.lucene.ApplicantFilterManager;
import java.util.List;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SilentFilterChecker
  implements Job
{
  protected static final Logger logger = Logger.getLogger(SilentFilterChecker.class);
  
  public void execute(JobExecutionContext context)
    throws JobExecutionException
  {
    try
    {
      logger.info("start SilentFilterChecker run");
      
      List reqList = BOFactory.getJobRequistionBO().getNonFilteredJobRequistions();
      if ((reqList != null) && (reqList.size() > 0)) {
        for (int i = 0; i < reqList.size(); i++)
        {
          JobRequisition requistion = (JobRequisition)reqList.get(i);
          


          ApplicantFilterManager.doApplicantFilteringWithReqSilent(requistion);
          

          requistion.setIsFilterApplied(1);
          BOFactory.getJobRequistionBO().updateJobRequistion(requistion);
        }
      }
      logger.info("end SilentFilterChecker run");
    }
    catch (Exception e)
    {
      logger.info(e);
    }
  }
}
