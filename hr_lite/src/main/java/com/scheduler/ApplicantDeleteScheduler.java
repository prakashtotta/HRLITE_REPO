package com.scheduler;

import com.bean.JobApplicant;
import com.bo.ApplicantBO;
import com.bo.ApplicantTXBO;
import com.bo.BOFactory;
import java.util.List;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class ApplicantDeleteScheduler
  implements Job
{
  protected static final Logger logger = Logger.getLogger(ApplicantDeleteScheduler.class);
  
  public void execute(JobExecutionContext context)
    throws JobExecutionException
  {
    logger.info("start ApplicantDeleteScheduler run");
    try
    {
      List tobedeletedapplicants = BOFactory.getApplicantBO().getAllApplicantsForDeletion();
      if (tobedeletedapplicants != null)
      {
        logger.info("No of applicants to be deleted = " + tobedeletedapplicants.size());
        for (int i = 0; i < tobedeletedapplicants.size(); i++)
        {
          JobApplicant applicant = (JobApplicant)tobedeletedapplicants.get(i);
          logger.info("started deleting applicant id" + applicant.getApplicantId());
          


          BOFactory.getApplicantTXBO().deleteapplicant(applicant);
          


          logger.info("ended deleting applicant id" + applicant.getApplicantId());
        }
      }
      else
      {
        logger.info("No of applicants to be deleted = 0");
      }
    }
    catch (Exception e)
    {
      logger.info("Exception ApplicantDeleteScheduler " + e.getMessage());
    }
  }
}
