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

public class ReferralRedemptionScheduler
  implements Job
{
  protected static final Logger logger = Logger.getLogger(ReferralRedemptionScheduler.class);
  
  public void execute(JobExecutionContext context)
    throws JobExecutionException
  {
    logger.info("start ReferralRedemptionScheduler run");
    try
    {
      List applicantList = RefferalDAO.getAllReferralApplicants();
      logger.info("Total applicants:" + applicantList.size());
      for (int i = 0; i < applicantList.size(); i++)
      {
        ReferralAgencyApplicants refagapp = (ReferralAgencyApplicants)applicantList.get(i);
        


        BOFactory.getReferralTXBO().doReferralRedemption(refagapp);
      }
    }
    catch (Exception e)
    {
      logger.info(e);
    }
  }
}
