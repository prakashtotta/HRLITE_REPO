package com.scheduler;

import com.bean.JobRequisition;
import com.bean.PublishJobBoards;
import com.bo.BOFactory;
import com.bo.JobRequistionBO;
import com.util.XMLFeed;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class JobPublishToJobBoardsSchedular
  implements Job
{
  protected static final Logger logger = Logger.getLogger(JobPublishToJobBoardsSchedular.class);
  
  public void execute(JobExecutionContext context)
    throws JobExecutionException
  {
    logger.info("start JobPublishToJobBoardsSchedular run");
    try
    {
      List jujuJobBoardlist = BOFactory.getJobRequistionBO().getJobBoardsListByJobBoardCode("JUJU");
      logger.info("Total job publish to JUJU >>>> " + jujuJobBoardlist.size());
      List<JobRequisition> jobsListforJuju = new ArrayList();
      if (jujuJobBoardlist.size() > 0)
      {
        logger.info("only for test");
        for (int i = 0; i < jujuJobBoardlist.size(); i++)
        {
          PublishJobBoards publishJobBoards = (PublishJobBoards)jujuJobBoardlist.get(i);
          JobRequisition jb = publishJobBoards.getJobRequisition();
          
          jobsListforJuju.add(jb);
        }
        XMLFeed xmlfeed = new XMLFeed();
        xmlfeed.createJujuJobFeed(jobsListforJuju);
      }
      List indeedJobBoardlist = BOFactory.getJobRequistionBO().getJobBoardsListByJobBoardCode("INDEED");
      logger.info("Total job publish to INDEED >>>> " + indeedJobBoardlist.size());
      List<JobRequisition> jobsListforIndeed = new ArrayList();
      if (indeedJobBoardlist.size() > 0)
      {
        logger.info("only for test");
        for (int i = 0; i < indeedJobBoardlist.size(); i++)
        {
          PublishJobBoards publishJobBoards = (PublishJobBoards)indeedJobBoardlist.get(i);
          JobRequisition jb = publishJobBoards.getJobRequisition();
          jobsListforIndeed.add(jb);
        }
        XMLFeed xmlfeed = new XMLFeed();
        xmlfeed.createInddedJobFeed(jobsListforIndeed);
      }
      List jobDieselJobBoardlist = BOFactory.getJobRequistionBO().getJobBoardsListByJobBoardCode("JOBDIESEL");
      logger.info("Total job publish to JOBDIESEL >>>> " + jobDieselJobBoardlist.size());
      List<JobRequisition> jobsListforJobDiesel = new ArrayList();
      if (jobDieselJobBoardlist.size() > 0)
      {
        logger.info("only for test");
        for (int i = 0; i < jobDieselJobBoardlist.size(); i++)
        {
          PublishJobBoards publishJobBoards = (PublishJobBoards)jobDieselJobBoardlist.get(i);
          JobRequisition jb = publishJobBoards.getJobRequisition();
          jobsListforJobDiesel.add(jb);
        }
        XMLFeed xmlfeed = new XMLFeed();
        xmlfeed.createJobDieselJobFeed(jobsListforJobDiesel);
      }
      List simplyHiredJobBoardlist = BOFactory.getJobRequistionBO().getJobBoardsListByJobBoardCode("SIMP");
      logger.info("Total job publish to SIMP >>>> " + simplyHiredJobBoardlist.size());
      List<JobRequisition> jobsListforSimplyHired = new ArrayList();
      if (simplyHiredJobBoardlist.size() > 0)
      {
        logger.info("only for test");
        for (int i = 0; i < simplyHiredJobBoardlist.size(); i++)
        {
          PublishJobBoards publishJobBoards = (PublishJobBoards)simplyHiredJobBoardlist.get(i);
          JobRequisition jb = publishJobBoards.getJobRequisition();
          jobsListforSimplyHired.add(jb);
        }
        XMLFeed xmlfeed = new XMLFeed();
        xmlfeed.createSimplyHiredJobFeed(jobsListforSimplyHired);
      }
      List olxJobBoardlist = BOFactory.getJobRequistionBO().getJobBoardsListByJobBoardCode("OLX");
      logger.info("Total job publish to OLX >>>> " + olxJobBoardlist.size());
      List<JobRequisition> jobsListforOLX = new ArrayList();
      if (olxJobBoardlist.size() > 0)
      {
        logger.info("only for test");
        for (int i = 0; i < olxJobBoardlist.size(); i++)
        {
          PublishJobBoards publishJobBoards = (PublishJobBoards)olxJobBoardlist.get(i);
          JobRequisition jb = publishJobBoards.getJobRequisition();
          jobsListforOLX.add(jb);
        }
        XMLFeed xmlfeed = new XMLFeed();
        xmlfeed.createOlxJobFeed(jobsListforOLX);
      }
      List oodleJobBoardlist = BOFactory.getJobRequistionBO().getJobBoardsListByJobBoardCode("OODLE");
      logger.info("Total job publish to OODLE >>>> " + oodleJobBoardlist.size());
      List<JobRequisition> jobsListforOODLE = new ArrayList();
      if (oodleJobBoardlist.size() > 0)
      {
        logger.info("only for test");
        for (int i = 0; i < oodleJobBoardlist.size(); i++)
        {
          PublishJobBoards publishJobBoards = (PublishJobBoards)oodleJobBoardlist.get(i);
          JobRequisition jb = publishJobBoards.getJobRequisition();
          jobsListforOODLE.add(jb);
        }
        XMLFeed xmlfeed = new XMLFeed();
        xmlfeed.createOodleJobFeed(jobsListforOODLE);
      }
      List trovitJobBoardlist = BOFactory.getJobRequistionBO().getJobBoardsListByJobBoardCode("TROVIT");
      logger.info("Total job publish to TROVIT >>>> " + trovitJobBoardlist.size());
      List<JobRequisition> jobsListforTrovit = new ArrayList();
      if (trovitJobBoardlist.size() > 0)
      {
        logger.info("only for test");
        for (int i = 0; i < trovitJobBoardlist.size(); i++)
        {
          PublishJobBoards publishJobBoards = (PublishJobBoards)trovitJobBoardlist.get(i);
          JobRequisition jb = publishJobBoards.getJobRequisition();
          jobsListforTrovit.add(jb);
        }
        XMLFeed xmlfeed = new XMLFeed();
        xmlfeed.createTrovitJobFeed(jobsListforTrovit);
      }
      List twitJobSearchJobBoardlist = BOFactory.getJobRequistionBO().getJobBoardsListByJobBoardCode("TWITJOBSEARCH");
      logger.info("Total job publish to TWITJOBSEARCH >>>> " + twitJobSearchJobBoardlist.size());
      List<JobRequisition> jobsListforTwitJobSearch = new ArrayList();
      if (twitJobSearchJobBoardlist.size() > 0)
      {
        logger.info("only for test");
        for (int i = 0; i < twitJobSearchJobBoardlist.size(); i++)
        {
          PublishJobBoards publishJobBoards = (PublishJobBoards)twitJobSearchJobBoardlist.get(i);
          JobRequisition jb = publishJobBoards.getJobRequisition();
          jobsListforTwitJobSearch.add(jb);
        }
        XMLFeed xmlfeed = new XMLFeed();
        xmlfeed.createTwitJobSearchJobFeed(jobsListforTwitJobSearch);
      }
      List workhoundJobBoardlist = BOFactory.getJobRequistionBO().getJobBoardsListByJobBoardCode("WORKHOUND");
      logger.info("Total job publish to WORKHOUND >>>> " + workhoundJobBoardlist.size());
      List<JobRequisition> jobsListforWorkhound = new ArrayList();
      if (workhoundJobBoardlist.size() > 0)
      {
        logger.info("only for test");
        for (int i = 0; i < workhoundJobBoardlist.size(); i++)
        {
          PublishJobBoards publishJobBoards = (PublishJobBoards)workhoundJobBoardlist.get(i);
          JobRequisition jb = publishJobBoards.getJobRequisition();
          jobsListforWorkhound.add(jb);
        }
        XMLFeed xmlfeed = new XMLFeed();
        xmlfeed.createWorkHoundJobFeed(jobsListforWorkhound);
      }
      List yakazJobBoardlist = BOFactory.getJobRequistionBO().getJobBoardsListByJobBoardCode("YAKAZ");
      logger.info("Total job publish to YAKAZ >>>> " + yakazJobBoardlist.size());
      List<JobRequisition> jobsListforYakaz = new ArrayList();
      if (yakazJobBoardlist.size() > 0)
      {
        logger.info("only for test");
        for (int i = 0; i < yakazJobBoardlist.size(); i++)
        {
          PublishJobBoards publishJobBoards = (PublishJobBoards)yakazJobBoardlist.get(i);
          JobRequisition jb = publishJobBoards.getJobRequisition();
          jobsListforYakaz.add(jb);
        }
        XMLFeed xmlfeed = new XMLFeed();
        xmlfeed.createYakazJobFeed(jobsListforYakaz);
      }
      List joobleJobBoardlist = BOFactory.getJobRequistionBO().getJobBoardsListByJobBoardCode("JOOBLE");
      logger.info("Total job publish to JOOBLE >>>> " + joobleJobBoardlist.size());
      List<JobRequisition> jobsListforJooble = new ArrayList();
      if (joobleJobBoardlist.size() > 0)
      {
        logger.info("only for test");
        for (int i = 0; i < joobleJobBoardlist.size(); i++)
        {
          PublishJobBoards publishJobBoards = (PublishJobBoards)joobleJobBoardlist.get(i);
          JobRequisition jb = publishJobBoards.getJobRequisition();
          jobsListforJooble.add(jb);
        }
        XMLFeed xmlfeed = new XMLFeed();
        xmlfeed.createJoobleJobFeed(jobsListforJooble);
      }
      List careerJobBoardlist = BOFactory.getJobRequistionBO().getJobBoardsListByJobBoardCode("CAREERVITAL");
      logger.info("Total job publish to CAREERVITAL >>>> " + careerJobBoardlist.size());
      List<JobRequisition> jobsListforCareer = new ArrayList();
      if (careerJobBoardlist.size() > 0)
      {
        logger.info("only for test");
        for (int i = 0; i < careerJobBoardlist.size(); i++)
        {
          PublishJobBoards publishJobBoards = (PublishJobBoards)careerJobBoardlist.get(i);
          JobRequisition jb = publishJobBoards.getJobRequisition();
          jobsListforCareer.add(jb);
        }
        XMLFeed xmlfeed = new XMLFeed();
        xmlfeed.createCareerVitalsJobFeed(jobsListforCareer);
      }
    }
    catch (Exception e)
    {
      logger.info(e);
    }
  }
}
