package com.lucene;

import com.bean.ApplicantOtherDetails;
import com.bean.JobApplicant;
import com.bean.JobRequisition;
import com.bean.Locale;
import com.bean.Timezone;
import com.bean.User;
import com.bo.ApplicantBO;
import com.bo.BOFactory;
import com.bo.BusinessFilterBO;
import com.bo.VariableBO;
import com.resources.Constant;
import java.util.List;
import org.apache.log4j.Logger;

public class ApplicantFilterManager
{
  protected static final Logger logger = Logger.getLogger(ApplicantFilterManager.class);
  
  public static void doApplicantFilteringSilent(JobApplicant applicant)
  {
    logger.info("inside doApplicantFilteringSient");
    if (applicant.getReqId() > 0L)
    {
      logger.info("doApplicantFilteringSient for applicant id:" + applicant.getApplicantId());
      List customVariableDataList = BOFactory.getVariableBO().getVariablesValues(applicant.getApplicantId(), applicant.getScreenCode());
      User userContext = new User();
      Locale locale = new Locale();
      locale.setLocaleCode(Constant.getValue("default.locale.code"));
      userContext.setLocale(locale);
      Timezone tz = new Timezone();
      tz.setTimezoneCode(Constant.getValue("default.timezone.code"));
      userContext.setTimezone(tz);
      try
      {
        List errorList = BOFactory.getBusinessFilterBO().isApplicantValidationSuccessedWithSilentFilters(applicant, applicant.getReqId(), customVariableDataList, userContext);
        if ((errorList != null) && (errorList.size() > 0)) {
          applicant.setFilterError(errorList.size());
        } else {
          applicant.setFilterError(0);
        }
      }
      catch (Exception e)
      {
        logger.info("exception on doApplicantFilteringSient" + e.getMessage());
      }
    }
  }
  
  public static void doApplicantFilteringWithReqSilent(JobRequisition jobreq)
  {
    List jobappList = BOFactory.getApplicantBO().getApplicantsByRequistionId(jobreq.getJobreqId());
    for (int i = 0; i < jobappList.size(); i++)
    {
      JobApplicant applicant = (JobApplicant)jobappList.get(i);
      ApplicantOtherDetails otherdetails = BOFactory.getApplicantBO().getApplicantOtherDetails(applicant.getApplicantId());
      applicant.setOtherdetails(otherdetails);
      doApplicantFilteringSilent(applicant);
      
      BOFactory.getApplicantBO().updateApplicant(applicant);
    }
  }
}
