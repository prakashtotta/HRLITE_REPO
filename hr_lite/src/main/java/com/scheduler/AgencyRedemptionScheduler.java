package com.scheduler;

import com.bean.ReferralAgencyApplicants;
import com.bo.BOFactory;
import com.bo.ReferralTXBO;
import com.dao.RefferalDAO;
import java.util.List;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class AgencyRedemptionScheduler
  implements Job
{
  protected static final Logger logger = Logger.getLogger(AgencyRedemptionScheduler.class);
  
  public void execute(JobExecutionContext context)
    throws JobExecutionException
  {
    logger.info("start AgencyRedemptionScheduler run");
    try
    {
      List applicantList = RefferalDAO.getAllAgencyApplicants();
      logger.info("Total applicants:" + applicantList.size());
      for (int i = 0; i < applicantList.size(); i++)
      {
        ReferralAgencyApplicants refagapp = (ReferralAgencyApplicants)applicantList.get(i);
        

        BOFactory.getReferralTXBO().doAgencyRedemption(refagapp);
      }
    }
    catch (Exception e)
    {
      logger.info(e);
    }
    logger.info("end AgencyRedemptionScheduler run");
  }
}
