package com.scheduler;

import com.bean.AgencyRedemption;
import com.bean.RefferalRedemptionRule;
import com.bo.BOFactory;
import com.bo.LovBO;
import com.dao.RefferalDAO;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class AgencyRedemptionRuleExecuter
  implements Job
{
  protected static final Logger logger = Logger.getLogger(AgencyRedemptionRuleExecuter.class);
  
  public void execute(JobExecutionContext context)
    throws JobExecutionException
  {
    logger.info("start AgencyRedemptionRuleExecuter run");
    try
    {
      List ruleList = BOFactory.getLovBO().getAllRefferalRules();
      for (int i = 0; i < ruleList.size(); i++)
      {
        RefferalRedemptionRule rule = (RefferalRedemptionRule)ruleList.get(i);
        





        List redemptionList = RefferalDAO.getAllAgencyRedemptionsWithNotReleased(rule);
        for (int j = 0; j < redemptionList.size(); j++)
        {
          AgencyRedemption redemption = (AgencyRedemption)redemptionList.get(j);
          
          Date eventdate = redemption.getEventdate();
          Calendar cal1 = Calendar.getInstance();
          cal1.setTime(eventdate);
          cal1.add(5, rule.getCreditAfterdays());
          
          Calendar calnow = Calendar.getInstance();
          if (cal1.compareTo(calnow) <= 0)
          {
            redemption.setStatus("D");
            redemption.setState("Released");
            redemption.setUpdatedBy("rulescheduler");
            redemption.setUpdatedDate(new Date());
            redemption.setReleaseddate(new Date());
            RefferalDAO.updateAgencyRededmption(redemption);
          }
        }
      }
    }
    catch (Exception e)
    {
      logger.info(e);
    }
    logger.info("end AgencyRedemptionRuleExecuter run");
  }
}
