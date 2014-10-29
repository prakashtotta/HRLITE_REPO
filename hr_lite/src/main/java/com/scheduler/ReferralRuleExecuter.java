package com.scheduler;

import com.bean.ReferralRedemption;
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

public class ReferralRuleExecuter
  implements Job
{
  protected static final Logger logger = Logger.getLogger(ReferralRuleExecuter.class);
  
  public void execute(JobExecutionContext context)
    throws JobExecutionException
  {
    logger.info("start ReferralRuleExecuter run");
    try
    {
      List ruleList = BOFactory.getLovBO().getAllRefferalRules();
      logger.info("ruleList.size" + ruleList.size());
      for (int i = 0; i < ruleList.size(); i++)
      {
        RefferalRedemptionRule rule = (RefferalRedemptionRule)ruleList.get(i);
        


        List redemptionList = RefferalDAO.getAllReferralRedemptionsWithNotReleased(rule);
        for (int j = 0; j < redemptionList.size(); j++)
        {
          ReferralRedemption redemption = (ReferralRedemption)redemptionList.get(j);
          
          Date eventdate = redemption.getEventdate();
          Calendar cal1 = Calendar.getInstance();
          cal1.setTime(eventdate);
          cal1.add(5, rule.getCreditAfterdays());
          
          logger.info("event date" + cal1);
          Calendar calnow = Calendar.getInstance();
          logger.info("calnow date" + calnow);
          if (cal1.compareTo(calnow) <= 0)
          {
            redemption.setStatus("D");
            redemption.setState("Released");
            redemption.setUpdatedBy("rulescheduler");
            redemption.setUpdatedDate(new Date());
            redemption.setReleaseddate(new Date());
            RefferalDAO.updateRefferalRededmption(redemption);
          }
        }
      }
    }
    catch (Exception e)
    {
      logger.info("ReferralRuleExecuter", e);
    }
    logger.info("end ReferralRuleExecuter run");
  }
}
