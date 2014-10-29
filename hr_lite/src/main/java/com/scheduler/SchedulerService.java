package com.scheduler;

import com.resources.Constant;
import com.scheduler.report.ReviewerPerformanceCalculator;
import com.util.StringUtils;
import java.util.Date;
import org.apache.log4j.Logger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;

public class SchedulerService
{
  protected static final Logger logger = Logger.getLogger(SchedulerService.class);
  
  public void createSchedule()
  {
    logger.info("start createSchedule");
    try
    {
      if ((!StringUtils.isNullOrEmpty(Constant.getValue("Scheduler.run.on.this.server"))) && (Constant.getValue("Scheduler.run.on.this.server").equals("yes")))
      {
        logger.info("Scheduler sunning on this server");
        



        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        if ((!StringUtils.isNullOrEmpty(Constant.getValue("TalentPool.Email.Reader"))) && (Constant.getValue("TalentPool.Email.Reader").equals("yes"))) {
          scheduleTalentPoolEmailReader(schedulerFactory);
        }
        if ((!StringUtils.isNullOrEmpty(Constant.getValue("Requisition.silent.filter.checker.scheduer"))) && (Constant.getValue("Requisition.silent.filter.checker.scheduer").equals("yes"))) {
          scheduleSilentFilterChecker(schedulerFactory);
        }
        if ((!StringUtils.isNullOrEmpty(Constant.getValue("Index.Create.Scheduler"))) && (Constant.getValue("Index.Create.Scheduler").equals("yes"))) {
          scheduleIndexCreateScheduler(schedulerFactory);
        }
        if ((!StringUtils.isNullOrEmpty(Constant.getValue("Applicant.Delete.Scheduler"))) && (Constant.getValue("Applicant.Delete.Scheduler").equals("yes"))) {
          scheduleApplicantDelete(schedulerFactory);
        }
        if ((!StringUtils.isNullOrEmpty(Constant.getValue("Resignation.Process.Screduler"))) && (Constant.getValue("Resignation.Process.Screduler").equals("yes"))) {
          scheduleResignationProcess(schedulerFactory);
        }
        if ((!StringUtils.isNullOrEmpty(Constant.getValue("Agency.Redemption.calculate.Scheduler"))) && (Constant.getValue("Agency.Redemption.calculate.Scheduler").equals("yes")))
        {
          scheduleAgencyRedemptionRuleExecuter(schedulerFactory);
          scheduleAgencyRedemption(schedulerFactory);
        }
        if ((!StringUtils.isNullOrEmpty(Constant.getValue("Referral.Redemption.Calculator.Scheduler"))) && (Constant.getValue("Referral.Redemption.Calculator.Scheduler").equals("yes")))
        {
          scheduleReferralRuleExecuter(schedulerFactory);
          scheduleReferralRedemption(schedulerFactory);
        }
        if ((!StringUtils.isNullOrEmpty(Constant.getValue("Applicant.Resume.Reader.from.mailbox.with.jobcode"))) && (Constant.getValue("Applicant.Resume.Reader.from.mailbox.with.jobcode").equals("yes"))) {
          scheduleEmailResumeReader(schedulerFactory);
        }
        if (((StringUtils.isNullOrEmpty(Constant.getValue("Applicant.Offer.Email.Sender"))) || (!Constant.getValue("Applicant.Offer.Email.Sender").equals("yes"))) || (
        


          (!StringUtils.isNullOrEmpty(Constant.getValue("Applicant.Rejection.Email.Sender"))) && (Constant.getValue("Applicant.Rejection.Email.Sender").equals("yes")))) {
          scheduleRejectionEmailSender(schedulerFactory);
        }
        if ((!StringUtils.isNullOrEmpty(Constant.getValue("Reviewer.Performance.Calculator"))) && (Constant.getValue("Reviewer.Performance.Calculator").equals("yes"))) {
          scheduleReviewerPerformanceCalculator(schedulerFactory);
        }
        if ((!StringUtils.isNullOrEmpty(Constant.getValue("job.publishto.jobboards"))) && (Constant.getValue("job.publishto.jobboards").equals("yes"))) {
          scheduleJobPublishtoJobBoards(schedulerFactory);
        }
      }
    }
    catch (Exception e)
    {
      logger.info("Exception createSchedule", e);
    }
    logger.info("end createSchedule");
  }
  
  public void scheduleReviewerPerformanceCalculator(SchedulerFactory schedulerFactory)
    throws Exception
  {
    Scheduler scheduler = schedulerFactory.getScheduler();
    logger.info("scheduling for scheduleReviewerPerformanceCalculator");
    int frequency = new Integer(Constant.getValue("Reviewer.Performance.Calculator.frequency")).intValue();
    frequency = frequency * 60 * 1000;
    


    long ctime = System.currentTimeMillis() + 1200L;
    

    JobDetail reviewerPerformanceCalculatorjobDetail = new JobDetail("reviewerPerformanceCalculatorjobDetail", "jobDetailGroup-s1", ReviewerPerformanceCalculator.class);
    

    SimpleTrigger reviewerPerformanceCalculatorTrigger = new SimpleTrigger("reviewerPerformanceCalculatorTrigger", "triggerGroup-s1");
    

    reviewerPerformanceCalculatorTrigger.setStartTime(new Date(ctime));
    
    reviewerPerformanceCalculatorTrigger.setRepeatInterval(frequency);
    

    reviewerPerformanceCalculatorTrigger.setRepeatCount(-1);
    
    scheduler.scheduleJob(reviewerPerformanceCalculatorjobDetail, reviewerPerformanceCalculatorTrigger);
    
    scheduler.start();
  }
  
  public void scheduleRejectionEmailSender(SchedulerFactory schedulerFactory)
    throws Exception
  {
    Scheduler scheduler = schedulerFactory.getScheduler();
    logger.info("scheduling for scheduleRejectionEmailSender");
    int frequency = new Integer(Constant.getValue("Applicant.Rejection.Email.Sender.frequency")).intValue();
    frequency = frequency * 60 * 1000;
    


    long ctime = System.currentTimeMillis() + 1300L;
    

    JobDetail rejectionEmailSenderjobDetail = new JobDetail("rejectionEmailSenderjobDetail", "jobDetailGroup-s1", RejectionEmailSender.class);
    

    SimpleTrigger rejectionEmailSenderTrigger = new SimpleTrigger("rejectionEmailSenderTrigger", "triggerGroup-s1");
    

    rejectionEmailSenderTrigger.setStartTime(new Date(ctime));
    
    rejectionEmailSenderTrigger.setRepeatInterval(frequency);
    

    rejectionEmailSenderTrigger.setRepeatCount(-1);
    
    scheduler.scheduleJob(rejectionEmailSenderjobDetail, rejectionEmailSenderTrigger);
    
    scheduler.start();
  }
  
  public void scheduleOfferEmailSender(SchedulerFactory schedulerFactory)
    throws Exception
  {
    Scheduler scheduler = schedulerFactory.getScheduler();
    logger.info("scheduling for scheduleOfferEmailSender");
    int frequency = new Integer(Constant.getValue("Applicant.Offer.Email.Sender.frequency")).intValue();
    frequency = frequency * 60 * 1000;
    


    long ctime = System.currentTimeMillis() + 100L;
    

    JobDetail offerEmailSenderjobDetail = new JobDetail("offerEmailSenderjobDetail", "jobDetailGroup-s1", OfferEmailSender.class);
    

    SimpleTrigger offerEmailSenderTrigger = new SimpleTrigger("offerEmailSenderTrigger", "triggerGroup-s1");
    

    offerEmailSenderTrigger.setStartTime(new Date(ctime));
    
    offerEmailSenderTrigger.setRepeatInterval(frequency);
    

    offerEmailSenderTrigger.setRepeatCount(-1);
    
    scheduler.scheduleJob(offerEmailSenderjobDetail, offerEmailSenderTrigger);
    
    scheduler.start();
  }
  
  public void scheduleEmailResumeReader(SchedulerFactory schedulerFactory)
    throws Exception
  {
    Scheduler scheduler = schedulerFactory.getScheduler();
    logger.info("scheduling for scheduleEmailResumeReader");
    int frequency = new Integer(Constant.getValue("Applicant.Resume.Reader.from.mailbox.with.jobcode.frequency")).intValue();
    frequency = frequency * 60 * 1000;
    


    long ctime = System.currentTimeMillis() + 200L;
    

    JobDetail emailResumeReaderjobDetail = new JobDetail("emailResumeReaderjobjobDetail", "jobDetailGroup-s1", EmailResumeReader.class);
    

    SimpleTrigger emailResumeReaderjobTrigger = new SimpleTrigger("emailResumeReaderjobTrigger", "triggerGroup-s1");
    

    emailResumeReaderjobTrigger.setStartTime(new Date(ctime));
    
    emailResumeReaderjobTrigger.setRepeatInterval(frequency);
    

    emailResumeReaderjobTrigger.setRepeatCount(-1);
    
    scheduler.scheduleJob(emailResumeReaderjobDetail, emailResumeReaderjobTrigger);
    
    scheduler.start();
  }
  
  public void scheduleReferralRuleExecuter(SchedulerFactory schedulerFactory)
    throws Exception
  {
    Scheduler scheduler = schedulerFactory.getScheduler();
    logger.info("scheduling for scheduleReferralRuleExecuter");
    int frequency = new Integer(Constant.getValue("Referral.Redemption.Calculator.Scheduler.frequency")).intValue();
    frequency = frequency * 60 * 1000;
    


    long ctime = System.currentTimeMillis() + 300L;
    

    JobDetail referralRuleExecuterjobDetail = new JobDetail("referralRuleExecuterjobDetail", "jobDetailGroup-s1", ReferralRuleExecuter.class);
    

    SimpleTrigger referralRuleExecuterTrigger = new SimpleTrigger("referralRuleExecuterTrigger", "triggerGroup-s1");
    

    referralRuleExecuterTrigger.setStartTime(new Date(ctime));
    
    referralRuleExecuterTrigger.setRepeatInterval(frequency);
    

    referralRuleExecuterTrigger.setRepeatCount(-1);
    
    scheduler.scheduleJob(referralRuleExecuterjobDetail, referralRuleExecuterTrigger);
    
    scheduler.start();
  }
  
  public void scheduleReferralRedemption(SchedulerFactory schedulerFactory)
    throws Exception
  {
    Scheduler scheduler = schedulerFactory.getScheduler();
    logger.info("scheduling for scheduleReferralRedemption");
    int frequency = new Integer(Constant.getValue("Referral.Redemption.Calculator.Scheduler.frequency")).intValue();
    frequency += 2;
    frequency = frequency * 60 * 1000;
    


    long ctime = System.currentTimeMillis() + 400L;
    

    JobDetail referralRedemptionjobDetail = new JobDetail("referralRedemptionjobDetail", "jobDetailGroup-s1", ReferralRedemptionScheduler.class);
    

    SimpleTrigger referralRedemptionTrigger = new SimpleTrigger("referralRedemptionTrigger", "triggerGroup-s1");
    

    referralRedemptionTrigger.setStartTime(new Date(ctime));
    
    referralRedemptionTrigger.setRepeatInterval(frequency);
    

    referralRedemptionTrigger.setRepeatCount(-1);
    
    scheduler.scheduleJob(referralRedemptionjobDetail, referralRedemptionTrigger);
    
    scheduler.start();
  }
  
  public void scheduleAgencyRedemption(SchedulerFactory schedulerFactory)
    throws Exception
  {
    Scheduler scheduler = schedulerFactory.getScheduler();
    logger.info("scheduling for scheduleAgencyRedemption");
    int frequency = new Integer(Constant.getValue("Agency.Redemption.calculate.Scheduler.frequency")).intValue();
    frequency += 2;
    frequency = frequency * 60 * 1000;
    


    long ctime = System.currentTimeMillis() + 500L;
    

    JobDetail agencyRedemptionjobDetail = new JobDetail("agencyRedemptionjobDetail", "jobDetailGroup-s1", AgencyRedemptionScheduler.class);
    

    SimpleTrigger agencyRedemptionTrigger = new SimpleTrigger("agencyRedemptionTrigger", "triggerGroup-s1");
    

    agencyRedemptionTrigger.setStartTime(new Date(ctime));
    
    agencyRedemptionTrigger.setRepeatInterval(frequency);
    

    agencyRedemptionTrigger.setRepeatCount(-1);
    
    scheduler.scheduleJob(agencyRedemptionjobDetail, agencyRedemptionTrigger);
    
    scheduler.start();
  }
  
  public void scheduleAgencyRedemptionRuleExecuter(SchedulerFactory schedulerFactory)
    throws Exception
  {
    Scheduler scheduler = schedulerFactory.getScheduler();
    logger.info("scheduling for scheduleAgencyRedemptionRuleExecuter");
    int frequency = new Integer(Constant.getValue("Agency.Redemption.calculate.Scheduler.frequency")).intValue();
    frequency = frequency * 60 * 1000;
    


    long ctime = System.currentTimeMillis() + 600L;
    

    JobDetail agencyRedemptionRuleExecuterjobDetail = new JobDetail("agencyRedemptionRuleExecuterjobDetail", "jobDetailGroup-s1", AgencyRedemptionRuleExecuter.class);
    

    SimpleTrigger agencyRedemptionRuleExecuterTrigger = new SimpleTrigger("agencyRedemptionRuleExecuterTrigger", "triggerGroup-s1");
    

    agencyRedemptionRuleExecuterTrigger.setStartTime(new Date(ctime));
    
    agencyRedemptionRuleExecuterTrigger.setRepeatInterval(frequency);
    

    agencyRedemptionRuleExecuterTrigger.setRepeatCount(-1);
    
    scheduler.scheduleJob(agencyRedemptionRuleExecuterjobDetail, agencyRedemptionRuleExecuterTrigger);
    
    scheduler.start();
  }
  
  public void scheduleResignationProcess(SchedulerFactory schedulerFactory)
    throws Exception
  {
    Scheduler scheduler = schedulerFactory.getScheduler();
    logger.info("scheduling for scheduleResignationProcess");
    int frequency = new Integer(Constant.getValue("Resignation.Process.Screduler.frequency")).intValue();
    frequency += 1;
    frequency = frequency * 60 * 1000;
    


    long ctime = System.currentTimeMillis() + 700L;
    

    JobDetail resignationProcessjobDetail = new JobDetail("resignationProcessjobDetail", "jobDetailGroup-s1", ResignationProcessScreduler.class);
    

    SimpleTrigger resignationProcessTrigger = new SimpleTrigger("resignationProcessTrigger", "triggerGroup-s1");
    

    resignationProcessTrigger.setStartTime(new Date(ctime));
    
    resignationProcessTrigger.setRepeatInterval(frequency);
    

    resignationProcessTrigger.setRepeatCount(-1);
    
    scheduler.scheduleJob(resignationProcessjobDetail, resignationProcessTrigger);
    
    scheduler.start();
  }
  
  public void scheduleApplicantDelete(SchedulerFactory schedulerFactory)
    throws Exception
  {
    Scheduler scheduler = schedulerFactory.getScheduler();
    logger.info("scheduling for scheduleApplicantDelete");
    int frequency = new Integer(Constant.getValue("Applicant.Delete.Scheduler.frequency")).intValue();
    frequency += 1;
    frequency = frequency * 60 * 1000;
    


    long ctime = System.currentTimeMillis() + 800L;
    

    JobDetail applicantDeletejobDetail = new JobDetail("applicantDeletejobDetail", "jobDetailGroup-s1", ApplicantDeleteScheduler.class);
    

    SimpleTrigger applicantDeleteTrigger = new SimpleTrigger("applicantDeleteSTrigger", "triggerGroup-s1");
    

    applicantDeleteTrigger.setStartTime(new Date(ctime));
    
    applicantDeleteTrigger.setRepeatInterval(frequency);
    

    applicantDeleteTrigger.setRepeatCount(-1);
    
    scheduler.scheduleJob(applicantDeletejobDetail, applicantDeleteTrigger);
    
    scheduler.start();
  }
  
  public void scheduleTalentPoolEmailReader(SchedulerFactory schedulerFactory)
    throws Exception
  {
    Scheduler scheduler = schedulerFactory.getScheduler();
    logger.info("scheduling for TalentPoolEmailReader");
    int frequency = new Integer(Constant.getValue("TalentPool.Email.Reader.frequency")).intValue();
    frequency += 1;
    frequency = frequency * 60 * 1000;
    


    long ctime = System.currentTimeMillis() + 900L;
    

    JobDetail talentPoolReaderjobDetail = new JobDetail("talentPoolReaderjobDetail", "jobDetailGroup-s1", TalentPoolEmailReader.class);
    

    SimpleTrigger talentPoolReaderSTrigger = new SimpleTrigger("talentPoolReaderSTrigger", "triggerGroup-s1");
    

    talentPoolReaderSTrigger.setStartTime(new Date(ctime));
    
    talentPoolReaderSTrigger.setRepeatInterval(frequency);
    

    talentPoolReaderSTrigger.setRepeatCount(-1);
    
    scheduler.scheduleJob(talentPoolReaderjobDetail, talentPoolReaderSTrigger);
    
    scheduler.start();
  }
  
  public void scheduleDeleteCompleteTasks(SchedulerFactory schedulerFactory)
    throws Exception
  {
    Scheduler scheduler = schedulerFactory.getScheduler();
    logger.info("scheduling for scheduleDeleteCompleteTasks");
    int frequency = 1440;
    frequency += 1;
    frequency = frequency * 60 * 1000;
    


    long ctime = System.currentTimeMillis() + 900L;
    

    JobDetail scheduleDeleteCompleteTasksjobDetail = new JobDetail("scheduleDeleteCompleteTasks", "scheduleDeleteCompleteTasks-s1", DeleteCompleteTasks.class);
    

    SimpleTrigger deleteCompleteTasksTrigger = new SimpleTrigger("scheduleDeleteCompleteTasksTrigger", "triggerGroup-s1");
    

    deleteCompleteTasksTrigger.setStartTime(new Date(ctime));
    
    deleteCompleteTasksTrigger.setRepeatInterval(frequency);
    

    deleteCompleteTasksTrigger.setRepeatCount(-1);
    
    scheduler.scheduleJob(scheduleDeleteCompleteTasksjobDetail, deleteCompleteTasksTrigger);
    
    scheduler.start();
  }
  
  public void scheduleSilentFilterChecker(SchedulerFactory schedulerFactory)
    throws Exception
  {
    Scheduler scheduler = schedulerFactory.getScheduler();
    logger.info("scheduling for scheduleSilentFilterChecker");
    int frequency = new Integer(Constant.getValue("Requisition.silent.filter.checker.scheduer.frequency")).intValue();
    frequency += 1;
    frequency = frequency * 60 * 1000;
    


    long ctime = System.currentTimeMillis() + 1000L;
    

    JobDetail silentFilterCheckerjobDetail = new JobDetail("silentFilterCheckerjobDetail", "jobDetailGroup-s1", SilentFilterChecker.class);
    

    SimpleTrigger silentFilterCheckerTrigger = new SimpleTrigger("silentFilterCheckerTrigger", "triggerGroup-s1");
    

    silentFilterCheckerTrigger.setStartTime(new Date(ctime));
    
    silentFilterCheckerTrigger.setRepeatInterval(frequency);
    

    silentFilterCheckerTrigger.setRepeatCount(-1);
    
    scheduler.scheduleJob(silentFilterCheckerjobDetail, silentFilterCheckerTrigger);
    
    scheduler.start();
  }
  
  public void scheduleIndexCreateScheduler(SchedulerFactory schedulerFactory)
    throws Exception
  {
    Scheduler scheduler = schedulerFactory.getScheduler();
    logger.info("scheduling for scheduleIndexCreateScheduler");
    int frequency = new Integer(Constant.getValue("Index.Create.Scheduler.frequency")).intValue();
    frequency += 1;
    frequency = frequency * 60 * 1000;
    


    long ctime = System.currentTimeMillis() + 1100L;
    

    JobDetail indexCreateSchedulerjobDetail = new JobDetail("indexCreateSchedulerjobDetail", "jobDetailGroup-s1", IndexCreateScheduler.class);
    

    SimpleTrigger indexCreateSchedulerTrigger = new SimpleTrigger("indexCreateSchedulerTrigger", "triggerGroup-s1");
    

    indexCreateSchedulerTrigger.setStartTime(new Date(ctime));
    
    indexCreateSchedulerTrigger.setRepeatInterval(frequency);
    

    indexCreateSchedulerTrigger.setRepeatCount(-1);
    
    scheduler.scheduleJob(indexCreateSchedulerjobDetail, indexCreateSchedulerTrigger);
    
    scheduler.start();
  }
  
  public void scheduleJobPublishtoJobBoards(SchedulerFactory schedulerFactory)
    throws Exception
  {
    Scheduler scheduler = schedulerFactory.getScheduler();
    logger.info("scheduling for scheduleJobPublishtoJobBoards");
    int frequency = new Integer(Constant.getValue("job.publishto.jobboards.frequency")).intValue();
    frequency = frequency * 60 * 1000;
    


    long ctime = System.currentTimeMillis() + 1200L;
    

    JobDetail jobPublishtoJobBoardsjobDetail = new JobDetail("jobPublishtoJobBoardsjobDetail", "jobDetailGroup-s1", JobPublishToJobBoardsSchedular.class);
    

    SimpleTrigger jobPublishtoJobBoardsTrigger = new SimpleTrigger("jobPublishtoJobBoardsTrigger", "triggerGroup-s1");
    



    jobPublishtoJobBoardsTrigger.setStartTime(new Date(ctime));
    
    jobPublishtoJobBoardsTrigger.setRepeatInterval(frequency);
    

    jobPublishtoJobBoardsTrigger.setRepeatCount(-1);
    
    scheduler.scheduleJob(jobPublishtoJobBoardsjobDetail, jobPublishtoJobBoardsTrigger);
    
    scheduler.start();
  }
}
