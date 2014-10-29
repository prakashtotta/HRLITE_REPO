package com.scheduler;

import com.bo.TaskBO;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class DeleteCompleteTasks
  implements Job
{
  protected static final Logger logger = Logger.getLogger(DeleteCompleteTasks.class);
  
  public void execute(JobExecutionContext context)
    throws JobExecutionException
  {
    logger.info("start DeleteCompleteTasks run");
    try
    {
      TaskBO.deleteCompletedTasks();
    }
    catch (Exception e)
    {
      logger.info("Exception DeleteCompleteTasks " + e.getMessage());
    }
  }
}
