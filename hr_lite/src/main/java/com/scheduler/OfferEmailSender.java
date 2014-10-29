package com.scheduler;

import com.bean.JobApplicant;
import com.bo.ApplicantBO;
import com.bo.ApplicantTXBO;
import com.bo.BOFactory;
import com.resources.Constant;
import java.util.List;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class OfferEmailSender
  implements Job
{
  protected static final Logger logger = Logger.getLogger(OfferEmailSender.class);
  
  public void execute(JobExecutionContext context)
    throws JobExecutionException
  {
    try
    {
      logger.info("start OfferEmailSender run");
      if ((Constant.getValue("offer.email.sent") != null) && (Constant.getValue("offer.email.sent").equals("yes")))
      {
        logger.info("Offer Email set Y");
        
        List applicantList = BOFactory.getApplicantBO().getAllApplicantsForOfferEmail();
        JobApplicant applicant = null;
        for (int i = 0; i < applicantList.size(); i++) {
          try
          {
            applicant = (JobApplicant)applicantList.get(i);
            
            logger.info("Offer Email sending started for applicant id" + applicant.getApplicantId());
            


            BOFactory.getApplicantTXBO().sendOfferEmail(applicant);
            

            logger.info("Offer Email sending end for applicant id" + applicant.getApplicantId());
          }
          catch (Exception e)
          {
            logger.info("Error on offer Email sending : applicant id" + applicant.getApplicantId());
            logger.info(e);
            applicant.setIsOfferEmailSent("N");
            applicant.setOfferEmailSentError(e.getMessage());
            BOFactory.getApplicantBO().updateApplicant(applicant);
          }
        }
      }
      logger.info("finish OfferEmailSender run");
    }
    catch (Exception e)
    {
      logger.error("error on OfferEmailSender email sending", e);
    }
  }
  
  public static void main(String[] args) {}
}
