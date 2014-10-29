package com.scheduler;

import com.lucene.IndexCreator;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class IndexCreateScheduler
  implements Job
{
  protected static final Logger logger = Logger.getLogger(IndexCreateScheduler.class);
  
  public void execute(JobExecutionContext context)
    throws JobExecutionException
  {
    try
    {
      logger.info("start IndexCreateScheduler run");
      
      IndexCreator idxcreator = new IndexCreator();
      
      idxcreator.createIndex();
      
      idxcreator.createRequistionIndex();
      
      idxcreator.createFacebookUsersIndex();
      
      idxcreator.createFacebookJobsIndex();
    }
    catch (Exception e)
    {
      logger.error("error on IndexCreateScheduler", e);
    }
  }
}
