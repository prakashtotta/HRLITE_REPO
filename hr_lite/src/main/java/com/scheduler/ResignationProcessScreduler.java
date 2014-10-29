package com.scheduler;

import com.bean.AgencyRedemption;
import com.bean.JobApplicant;
import com.bean.ReferralAgencyApplicants;
import com.bean.ReferralRedemption;
import com.bean.ResumeSourceType;
import com.bo.ApplicantBO;
import com.bo.BOFactory;
import com.dao.RefferalDAO;
import com.resources.Constant;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class ResignationProcessScreduler
  implements Job
{
  protected static final Logger logger = Logger.getLogger(ResignationProcessScreduler.class);
  
  public void execute(JobExecutionContext context)
    throws JobExecutionException
  {
    logger.info("start ResignationProcessScreduler run");
    try
    {
      if ((Constant.getValue("redemption.blocked.on.applicant.resignation") != null) && (Constant.getValue("redemption.blocked.on.applicant.resignation").equalsIgnoreCase("yes")))
      {
        List resignedApplicantList = BOFactory.getApplicantBO().getAllResignedApplicantsNotProcessed();
        for (int i = 0; i < resignedApplicantList.size(); i++)
        {
          JobApplicant applicant = (JobApplicant)resignedApplicantList.get(i);
          
          ReferralAgencyApplicants refaggapp = RefferalDAO.getReferralAgencyApplicantByApplicantId(applicant.getApplicantId());
          if (refaggapp != null)
          {
            refaggapp.setStatus("D");
            refaggapp.setState("RESIGNED");
            refaggapp.setUpdatedDate(new Date());
            refaggapp.setEventdate(applicant.getResigneddate());
            

            RefferalDAO.updateReferralAgencyApplicant(refaggapp);
          }
          if ((applicant.getResumesourcetype() != null) && (applicant.getResumesourcetype().getResumeSourceTypeId() == 1))
          {
            ReferralRedemption refred = RefferalDAO.getReferralRedmptionDetailsByApplicantId(applicant.getApplicantId());
            refred.setStatus("D");
            refred.setApplicantstate("RESIGNED");
            refred.setUpdatedDate(new Date());
            refred.setEventdate(applicant.getResigneddate());
            refred.setUpdatedBy("resignedsch");
            

            RefferalDAO.updateRefferalRededmption(refred);
          }
          if ((applicant.getResumesourcetype() != null) && (applicant.getResumesourcetype().getResumeSourceTypeId() == 5))
          {
            AgencyRedemption agred = RefferalDAO.getAgencyRedmptionDetailsByApplicantId(applicant.getApplicantId());
            agred.setStatus("D");
            agred.setApplicantstate("RESIGNED");
            agred.setUpdatedDate(new Date());
            agred.setEventdate(applicant.getResigneddate());
            agred.setUpdatedBy("resignedsch");
            
            RefferalDAO.updateAgencyRededmption(agred);
          }
          applicant.setIsRegignationSchedularProcessed("Y");
          BOFactory.getApplicantBO().updateApplicant(applicant);
        }
      }
    }
    catch (Exception e)
    {
      logger.info(e);
    }
    logger.info("end ResignationProcessScreduler run");
  }
}
