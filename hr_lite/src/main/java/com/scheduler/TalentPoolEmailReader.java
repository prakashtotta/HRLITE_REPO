package com.scheduler;

import com.bean.pool.TalentPool;
import com.bo.TalentPoolBO;
import com.util.EmailResumeReaderUtil;
import java.util.List;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class TalentPoolEmailReader
  implements Job
{
  protected static final Logger logger = Logger.getLogger(TalentPoolEmailReader.class);
  
  public void execute(JobExecutionContext context)
    throws JobExecutionException
  {
    logger.info("start TalentPoolEmailReader run");
    


    List talentPollList = TalentPoolBO.getAllTalentpool();
    if ((talentPollList != null) && (talentPollList.size() > 0)) {
      for (int i = 0; i < talentPollList.size(); i++)
      {
        TalentPool tpool = (TalentPool)talentPollList.get(i);
        //EmailResumeReaderUtil.emailReadAndResumeSubmit(tpool);
      }
    }
    logger.info("end TalentPoolEmailReader run");
  }
}
