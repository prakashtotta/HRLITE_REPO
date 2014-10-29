package com.lucene;

import com.bean.ApplicantScoring;
import com.bean.JobApplicant;
import com.bean.JobRequisition;
import com.bo.ApplicantBO;
import com.bo.BOFactory;
import com.util.StringUtils;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

public class ApplicantScoreManager
{
  protected static final Logger logger = Logger.getLogger(ApplicantScoreManager.class);
  
  public static void doApplicantScoring(JobRequisition requisition)
    throws Exception
  {
    logger.info("doApplicantScoring for requisition id:" + requisition.getJobreqId());
    try
    {
      List keywordLlist = StringUtils.tokenizeString(requisition.getScoringKeyword(), " ");
      if (keywordLlist != null)
      {
        List jobappList = BOFactory.getApplicantBO().getApplicantsByRequistionId(requisition.getJobreqId());
        for (int i = 0; i < jobappList.size(); i++)
        {
          JobApplicant applicant = (JobApplicant)jobappList.get(i);
          try
          {
            BOFactory.getApplicantBO().deleteIndexApplicantScoringByApplicantId(applicant.getApplicantId());
            double avscore = 0.0D;
            for (int j = 0; j < keywordLlist.size(); j++)
            {
              String keyword = (String)keywordLlist.get(j);
              ApplicantScoring score = new ApplicantScoring();
              score.setApplicantId(applicant.getApplicantId());
              score.setRequistionId(requisition.getJobreqId());
              score.setKeyword(keyword);
              score.setCreatedDate(new Date());
              score = IndexSearch.searchApplicantScoreKeywords(score);
              
              BOFactory.getApplicantBO().saveApplicantScoring(score);
              
              avscore += score.getScore();
            }
            avscore /= keywordLlist.size();
            if (Double.isNaN(avscore)) {
              avscore = 0.0D;
            }
            applicant.setScoreAve(avscore);
            BOFactory.getApplicantBO().updateApplicant(applicant);
          }
          catch (Exception e)
          {
            logger.info("error doApplicantScoring applicant id" + applicant.getApplicantId());
          }
        }
      }
    }
    catch (Exception e)
    {
      logger.info("error doApplicantScoring" + e.getMessage());
      throw e;
    }
  }
}
