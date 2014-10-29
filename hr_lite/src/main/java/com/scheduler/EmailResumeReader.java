package com.scheduler;

import com.bean.pool.EmailCampaign;
import com.bo.BOFactory;
import com.bo.EmailCampaignBO;
import com.util.EmailResumeReaderUtil;
import java.util.List;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class EmailResumeReader
  implements Job
{
  protected static final Logger logger = Logger.getLogger(EmailResumeReader.class);
  
  public void execute(JobExecutionContext context)
    throws JobExecutionException
  {
    try
    {
      logger.info("start EmailResumeReader run");
      



      List emailCampList = BOFactory.getEmailCampaignBO().getAllEmailCampaign();
      if ((emailCampList != null) && (emailCampList.size() > 0)) {
        for (int i = 0; i < emailCampList.size(); i++)
        {
          EmailCampaign ecmp = (EmailCampaign)emailCampList.get(i);
          EmailResumeReaderUtil.emailReadAndResumeSubmit(ecmp);
        }
      }
      logger.info("end EmailResumeReader run");
    }
    catch (Exception e)
    {
      logger.info(e);
    }
  }
}
