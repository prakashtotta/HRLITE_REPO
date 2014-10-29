package com.scheduler;

import com.bean.EmailTemplates;
import com.bean.JobApplicant;
import com.bean.Organization;
import com.bean.OrganizationEmailTemplate;
import com.bo.ApplicantBO;
import com.bo.BOFactory;
import com.bo.JobRequistionBO;
import com.bo.OrganizationBO;
import com.common.Common;
import com.dao.TaskDAO;
import com.resources.Constant;
import com.util.DateUtil;
import com.util.EmailUtil;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class RejectionEmailSender
  implements Job
{
  protected static final Logger logger = Logger.getLogger(RejectionEmailSender.class);
  
  public void execute(JobExecutionContext context)
    throws JobExecutionException
  {
    try
    {
      logger.info("start RejectionEmailSender run");
      if ((Constant.getValue("rejection.email.sent") != null) && (Constant.getValue("rejection.email.sent").equals("yes")))
      {
        logger.info("Rejection Email set Y");
        
        List applicantList = BOFactory.getApplicantBO().getAllApplicantsForRejectionEmail();
        for (int i = 0; i < applicantList.size(); i++)
        {
          JobApplicant applicant = (JobApplicant)applicantList.get(i);
          
          Organization org = null;
          if (applicant.getReqId() != 0L) {
            org = BOFactory.getJobRequistionBO().getOrganizationByReqId(applicant.getReqId());
          } else {
            org = BOFactory.getOrganizationBO().getRootOrganization(applicant.getSuper_user_key());
          }
          OrganizationEmailTemplate orgemtmpl = null;
          

          orgemtmpl = TaskDAO.getOrganizationEmailTemplate(org.getOrgId(), Common.REJECTION_EMAIL_COMPONENT);
          if (orgemtmpl == null) {
            throw new Exception("Email template not found for function " + Common.REJECTION_EMAIL_COMPONENT + " organization" + org.getOrgName());
          }
          if ((orgemtmpl.getStatus() != null) && (orgemtmpl.getStatus().equals("N"))) {
            throw new Exception("Email template is disabled for function " + Common.REJECTION_EMAIL_COMPONENT + "  organization" + org.getOrgName());
          }
          EmailTemplates emptl = orgemtmpl.getEmailtemplate();
          
          String content = emptl.getEmailtemplateData();
          String subjectmp = emptl.getEmailSubject();
          
          content = content.replaceAll("##applicant_name##", applicant.getFullName());
          content = content.replaceAll("##organization_name##", org.getOrgName());
          content = content.replaceAll("##job_title##", applicant.getJobTitle());
          content = content.replaceAll("##todays_date##", DateUtil.convertDateToStringDate(new Date(), DateUtil.dateformatstandard));
          
          subjectmp = subjectmp.replaceAll("##applicant_name##", applicant.getFullName());
          subjectmp = subjectmp.replaceAll("##organization_name##", org.getOrgName());
          subjectmp = subjectmp.replaceAll("##job_title##", applicant.getJobTitle());
          subjectmp = subjectmp.replaceAll("##todays_date##", DateUtil.convertDateToStringDate(new Date(), DateUtil.dateformatstandard));
          


          EmailUtil emutil = new EmailUtil();
          String[] to = { applicant.getEmail() };
          String[] cc = { Constant.getValue("email.rejection.cc") };
          
          emutil.sendMessage(Constant.getValue("email.fromemail"), to, cc, null, Constant.getValue("email.replytoemail"), subjectmp, null, content, null, 0);
          
          applicant.setIsRejectionEmailSent("Y");
          applicant.setRejectionemailsentdate(new Date());
          BOFactory.getApplicantBO().updateApplicant(applicant);
        }
      }
      logger.info("finish RejectionEmailSender run");
    }
    catch (Exception e)
    {
      logger.error("error on rejection email sending", e);
    }
  }
  
  public static void main(String[] args) {}
}
