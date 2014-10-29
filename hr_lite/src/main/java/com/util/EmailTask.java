package com.util;

import com.bean.ApplicantUser;
import com.bean.EmailTemplates;
import com.bean.JobApplicant;
import com.bean.JobRequisition;
import com.bean.Locale;
import com.bean.Organization;
import com.bean.OrganizationEmailTemplate;
import com.bean.QuestionGroupApplicants;
import com.bean.ReferAFriend;
import com.bean.RefferalEmployee;
import com.bean.TaskData;
import com.bean.User;
import com.bean.lov.KeyValue;
import com.bean.testengine.MockQuestionSet;
import com.bean.testengine.MockTest;
import com.bo.BOFactory;
import com.bo.JobRequistionBO;
import com.bo.OrganizationBO;
import com.common.Common;
import com.dao.TaskDAO;
import com.resources.Constant;
import java.util.Date;
import org.apache.log4j.Logger;

public class EmailTask
  implements Runnable
{
  protected static final Logger logger = Logger.getLogger(EmailTask.class);
  String from;
  String[] to;
  String[] cc;
  String[] bcc;
  String replyTo;
  String subject;
  String textBody;
  String htmlBody;
  String[] attachments;
  int langid;
  String type;
  String functionType;
  String subFunctionType;
  User user;
  JobApplicant applicant;
  JobApplicant massemailapplicant;
  User newCreatedUser;
  RefferalEmployee employeeReferral;
  ApplicantUser appuser;
  TaskData taskdata;
  String comment;
  String event;
  ReferAFriend referafriend;
  MockTest mocktest;
  QuestionGroupApplicants questionnaire;
  String exam_questionnaire_content;
  Object object;
  String subjectmassemailapplicant;
  String Messagemassemailapplicant;
  JobRequisition jobreq;
  
  public EmailTask(String from, String[] to, String[] cc, String[] bcc, String replyTo, String subject, String textBody, String htmlBody, String[] attachments, int langId, String type)
  {
    this.from = from;
    this.to = to;
    this.cc = cc;
    this.bcc = bcc;
    this.replyTo = replyTo;
    this.subject = subject;
    this.textBody = textBody;
    this.htmlBody = htmlBody;
    this.attachments = attachments;
    this.langid = this.langid;
    this.type = type;
  }
  
  public void run()
  {
    logger.info("run method start");
    try
    {
      EmailUtil emutil = new EmailUtil();
      if ((!StringUtils.isNullOrEmpty(getFunctionType())) && (getFunctionType().equals(Common.ONLY_EMAIL_ONLY)))
      {
        emutil = new EmailUtil();
        emutil.sendMessageOnBehalf(this.from, this.to, this.cc, this.bcc, this.replyTo, this.subject, null, this.htmlBody, null, 0);
      }
      if ((!StringUtils.isNullOrEmpty(getFunctionType())) && (getFunctionType().equals(Common.INTERVIEW_SCHEDULE_EMAIL_COMPONENT)))
      {
        logger.info("email component  " + getFunctionType());
        initialiseBodyAndSubjectForReviewInterviewSchedule(getUser(), getApplicant(), getFunctionType());
        if (this.attachments != null) {
          emutil.sendCalenderMessage(this.from, this.to, this.cc, this.bcc, this.replyTo, this.subject, null, this.htmlBody, this.attachments[0], 0);
        } else {
          emutil.sendCalenderMessage(this.from, this.to, this.cc, this.bcc, this.replyTo, this.subject, null, this.htmlBody, null, 0);
        }
      }
      if ((!StringUtils.isNullOrEmpty(getFunctionType())) && (getFunctionType().equals(Common.REVIEW_SCHEDULE_EMAIL_COMPONENT)))
      {
        logger.info("email component  " + getFunctionType());
        initialiseBodyAndSubjectForReviewInterviewSchedule(getUser(), getApplicant(), getFunctionType());
        if (this.attachments != null) {
          emutil.sendCalenderMessage(this.from, this.to, this.cc, this.bcc, this.replyTo, this.subject, null, this.htmlBody, this.attachments[0], 0);
        } else {
          emutil.sendCalenderMessage(this.from, this.to, this.cc, this.bcc, this.replyTo, this.subject, null, this.htmlBody, null, 0);
        }
      }
      if ((!StringUtils.isNullOrEmpty(getFunctionType())) && (getFunctionType().equals(Common.REVIEW_FEEDBACK_EMAIL_COMPONENT)))
      {
        logger.info("email component  " + getFunctionType());
        initialiseBodyAndSubjectForReviewFeedback(getUser(), getApplicant(), getFunctionType());
        logger.info("email htmlBody  " + this.htmlBody);
        emutil.sendMessageOnBehalf(this.from, this.to, this.cc, this.bcc, this.replyTo, this.subject, null, this.htmlBody, null, 0);
      }
      if ((!StringUtils.isNullOrEmpty(getFunctionType())) && (getFunctionType().equals(Common.APPLICANT_MOVE)))
      {
        logger.info("email component  " + getFunctionType());
        initialiseBodyForApplicantMove(getUser(), getApplicant(), getFunctionType());
        logger.info("email htmlBody  " + this.htmlBody);
        emutil.sendMessageOnBehalf(this.from, this.to, this.cc, this.bcc, this.replyTo, this.subject, null, this.htmlBody, null, 0);
      }
      if ((!StringUtils.isNullOrEmpty(getFunctionType())) && (getFunctionType().equals(Common.APPLICANT_MARK_DELETE)))
      {
        logger.info("email component  " + getFunctionType());
        initialiseBodyForApplicantDelete(getUser(), getApplicant(), getFunctionType());
        logger.info("email htmlBody  " + this.htmlBody);
        emutil.sendMessageOnBehalf(this.from, this.to, this.cc, this.bcc, this.replyTo, this.subject, null, this.htmlBody, null, 0);
      }
      if ((!StringUtils.isNullOrEmpty(getFunctionType())) && (getFunctionType().equals(Common.APPLICANT_MARK_UNDO_DELETE)))
      {
        logger.info("email component  " + getFunctionType());
        initialiseBodyForApplicantUndoDelete(getUser(), getApplicant(), getFunctionType());
        logger.info("email htmlBody  " + this.htmlBody);
        emutil.sendMessageOnBehalf(this.from, this.to, this.cc, this.bcc, this.replyTo, this.subject, null, this.htmlBody, null, 0);
      }
      if ((!StringUtils.isNullOrEmpty(getFunctionType())) && (getFunctionType().equals(Common.REQUISITION_CREATION)))
      {
        logger.info("email component  " + getFunctionType());
        initialiseBodyAndSubjectForRequisitionCreate(getUser(), getJobreq(), getFunctionType());
        logger.info("email htmlBody  " + this.htmlBody);
        emutil.sendMessageOnBehalf(this.from, this.to, this.cc, this.bcc, this.replyTo, this.subject, null, this.htmlBody, null, 0);
      }
      if ((!StringUtils.isNullOrEmpty(getFunctionType())) && (getFunctionType().equals(Common.REASSIGN_REQUISITION_APPROVAL)))
      {
        logger.info("email component  " + getFunctionType());
        initialiseBodyAndSubjectForRequisitionApprovalReassign(getUser(), getJobreq(), getFunctionType(), getComment());
        logger.info("email htmlBody  " + this.htmlBody);
        emutil.sendMessageOnBehalf(this.from, this.to, this.cc, this.bcc, this.replyTo, this.subject, null, this.htmlBody, null, 0);
      }
      if ((!StringUtils.isNullOrEmpty(getFunctionType())) && ((getFunctionType().equals(Common.INITIATE_APPROVAL_REQUISITION)) || (getFunctionType().equals(Common.CLOSE_REQUISITION)) || (getFunctionType().equals(Common.DELETE_REQUISITION)) || (getFunctionType().equals(Common.RECALL_REQUISITION)) || (getFunctionType().equals(Common.APPROVE_REQUISITION)) || (getFunctionType().equals(Common.REJECT_REQUISITION))))
      {
        logger.info("email component  " + getFunctionType());
        initialiseBodyAndSubjectForRequisitionEmails(getUser(), getJobreq(), getFunctionType(), getComment());
        logger.info("email htmlBody  " + this.htmlBody);
        emutil.sendMessageOnBehalf(this.from, this.to, this.cc, this.bcc, this.replyTo, this.subject, null, this.htmlBody, null, 0);
      }
      if ((!StringUtils.isNullOrEmpty(getFunctionType())) && (getFunctionType().equals(Common.INTERVIEW_FEEDBACK_EMAIL_COMPONENT)))
      {
        logger.info("email component  " + getFunctionType());
        initialiseBodyAndSubjectForReviewFeedback(getUser(), getApplicant(), getFunctionType());
        logger.info("email htmlBody  " + this.htmlBody);
        emutil.sendMessageOnBehalf(this.from, this.to, this.cc, this.bcc, this.replyTo, this.subject, null, this.htmlBody, null, 0);
      }
      if ((!StringUtils.isNullOrEmpty(getFunctionType())) && (getFunctionType().equals(Common.OFFER_APPROVAL_EMAIL_COMPONENT)))
      {
        logger.info("email component  " + getFunctionType());
        initialiseBodyAndSubjectForOfferApproval(getUser(), getApplicant(), getFunctionType());
        emutil.sendMessageOnBehalf(this.from, this.to, this.cc, this.bcc, this.replyTo, this.subject, null, this.htmlBody, null, 0);
      }
      if ((!StringUtils.isNullOrEmpty(getFunctionType())) && (getFunctionType().equals(Common.OFFER_APPROVE_REJECT_EMAIL_COMPONENT)))
      {
        logger.info("email component  " + getFunctionType());
        initialiseBodyAndSubjectForOfferApproveRejectEmail(getUser(), getApplicant(), getFunctionType());
        emutil.sendMessageOnBehalf(this.from, this.to, this.cc, this.bcc, this.replyTo, this.subject, null, this.htmlBody, null, 0);
      }
      if ((!StringUtils.isNullOrEmpty(getFunctionType())) && (getFunctionType().equals(Common.USER_CREATION_EMAIL_COMPONENT)))
      {
        logger.info("email component  " + getFunctionType());
        initialiseBodyAndSubjectForUserCreationEmail(getUser(), getNewCreatedUser(), getFunctionType());
        emutil.sendMessageOnBehalf(this.from, this.to, this.cc, this.bcc, this.replyTo, this.subject, null, this.htmlBody, null, 0);
      }
      if ((!StringUtils.isNullOrEmpty(getFunctionType())) && (getFunctionType().equals(Common.MASS_EMAIL_TO_APPLICANT)))
      {
        logger.info("email component  " + getFunctionType());
        initialiseBodyAndSubjectForMassEmailToALLApplicants(getUser(), getMassemailapplicant(), getJobreq(), getFunctionType());
        emutil.sendMessageOnBehalf(this.from, this.to, this.cc, this.bcc, this.replyTo, this.subject, null, this.htmlBody, null, 0);
      }
      if ((!StringUtils.isNullOrEmpty(getFunctionType())) && (getFunctionType().equals(Common.REFERRAL_USER_REGISTRATION)))
      {
        logger.info("email component  " + getFunctionType());
        initialiseBodyAndSubjectForReferralRegistrationEmail(getEmployeeReferral(), getFunctionType());
        logger.info("email htmlBody  " + this.htmlBody);
        emutil.sendMessageOnBehalf(this.from, this.to, this.cc, this.bcc, this.replyTo, this.subject, null, this.htmlBody, null, 0);
      }
      if ((!StringUtils.isNullOrEmpty(getFunctionType())) && (getFunctionType().equals(Common.REFER_FRIEND)))
      {
        logger.info("email component  " + getFunctionType());
        initialiseBodyAndSubjectForReferAFriend(getEmployeeReferral(), getReferafriend(), getFunctionType());
        logger.info("email htmlBody  " + this.htmlBody);
        emutil.sendMessageOnBehalf(this.from, this.to, this.cc, this.bcc, this.replyTo, this.subject, null, this.htmlBody, null, 0);
      }
      if ((!StringUtils.isNullOrEmpty(getFunctionType())) && (getFunctionType().equals(Common.APPLICANT_USER_CREATION)))
      {
        logger.info("email component  " + getFunctionType());
        initialiseBodyAndSubjectForApplicantUserCreationEmail(getUser(), getAppuser(), getFunctionType());
        logger.info("email htmlBody  " + this.htmlBody);
        emutil.sendMessageOnBehalf(this.from, this.to, this.cc, this.bcc, this.replyTo, this.subject, null, this.htmlBody, null, 0);
      }
      if ((!StringUtils.isNullOrEmpty(getFunctionType())) && (getFunctionType().equals(Common.TASK_ASSIGN)))
      {
        logger.info("email component  " + getFunctionType());
        initialiseBodyAndSubjectForTaskAssignEmail(getUser(), getTaskdata(), getFunctionType(), getComment());
        logger.info("email htmlBody  " + this.htmlBody);
        emutil.sendMessageOnBehalf(this.from, this.to, this.cc, this.bcc, this.replyTo, this.subject, null, this.htmlBody, null, 0);
      }
      if ((!StringUtils.isNullOrEmpty(getFunctionType())) && (getFunctionType().equals(Common.TASK_COMPLETE)))
      {
        logger.info("email component  " + getFunctionType());
        initialiseBodyAndSubjectForTaskCompleteEmail(getUser(), getTaskdata(), getFunctionType(), getComment());
        logger.info("email htmlBody  " + this.htmlBody);
        emutil.sendMessageOnBehalf(this.from, this.to, this.cc, this.bcc, this.replyTo, this.subject, null, this.htmlBody, null, 0);
      }
      if ((!StringUtils.isNullOrEmpty(getFunctionType())) && (getFunctionType().equals(Common.EXAM_SCHEDULE)))
      {
        logger.info("email component  " + getFunctionType());
        initialiseBodyAndSubjectForExamScheduleEmail(getUser(), getMocktest(), getFunctionType(), getComment());
        logger.info("email htmlBody  " + this.htmlBody);
        emutil.sendMessageOnBehalf(this.from, this.to, this.cc, this.bcc, this.replyTo, this.subject, null, this.htmlBody, null, 0);
      }
      if ((!StringUtils.isNullOrEmpty(getFunctionType())) && (getFunctionType().equals(Common.COMMENT_TO_APPLICANT)))
      {
        logger.info("email component  " + getFunctionType());
        initialiseBodyAndSubjectForApplicantCommentEmail(getUser(), getApplicant(), getFunctionType(), getComment());
        logger.info("email htmlBody  " + this.htmlBody);
        emutil.sendMessageOnBehalf(this.from, this.to, this.cc, this.bcc, this.replyTo, this.subject, null, this.htmlBody, null, 0);
      }
      if ((!StringUtils.isNullOrEmpty(getFunctionType())) && (getFunctionType().equals(Common.COMMENT_TO_ALL)))
      {
        logger.info("email component  " + getFunctionType());
        initialiseBodyAndSubjectForCommentEmail(getApplicant(), getFunctionType(), getComment());
        logger.info("email htmlBody  " + this.htmlBody);
        emutil.sendMessageOnBehalf(this.from, this.to, this.cc, this.bcc, this.replyTo, this.subject, null, this.htmlBody, null, 0);
      }
      if ((!StringUtils.isNullOrEmpty(getFunctionType())) && (getFunctionType().equals(Common.EVENT_TO_ALL)))
      {
        logger.info("email component  " + getFunctionType());
        initialiseBodyAndSubjectForEventEmail(getApplicant(), getUser(), getFunctionType(), getComment(), getEvent());
        logger.info("email htmlBody  " + this.htmlBody);
        emutil.sendMessageOnBehalf(this.from, this.to, this.cc, this.bcc, this.replyTo, this.subject, null, this.htmlBody, null, 0);
      }
      if ((!StringUtils.isNullOrEmpty(getFunctionType())) && (getFunctionType().equals(Common.QUESTIONNAIRE_SCHEDULE)))
      {
        logger.info("email component  " + getFunctionType());
        initialiseBodyAndSubjectForQuestionnaireEmail(getUser(), getQuestionnaire(), getFunctionType(), getComment());
        logger.info("email htmlBody  " + this.htmlBody);
        emutil.sendMessageOnBehalf(this.from, this.to, this.cc, this.bcc, this.replyTo, this.subject, null, this.htmlBody, null, 0);
      }
      if ((!StringUtils.isNullOrEmpty(getFunctionType())) && (getFunctionType().equals(Common.APPLICANT_SUBMIT)))
      {
        logger.info("email component  " + getFunctionType());
        initialiseBodyAndSubjectForApplicationSubmit(getUser(), getApplicant(), getFunctionType());
        logger.info("email htmlBody  " + this.htmlBody);
        emutil.sendMessageOnBehalf(this.from, this.to, this.cc, this.bcc, this.replyTo, this.subject, null, this.htmlBody, null, 0);
      }
      if ((!StringUtils.isNullOrEmpty(getFunctionType())) && (getFunctionType().equals(Common.PRE_APPLICATION_SUBMIT)))
      {
        logger.info("email component  " + getFunctionType());
        initialiseBodyAndSubjectForPreApplicationSubmit(getUser(), getApplicant(), getFunctionType());
        logger.info("email htmlBody  " + this.htmlBody);
        emutil.sendMessageOnBehalf(this.from, this.to, this.cc, this.bcc, this.replyTo, this.subject, null, this.htmlBody, null, 0);
      }
      if ((!StringUtils.isNullOrEmpty(getFunctionType())) && (getFunctionType().equals(Common.PRE_TALENT_POOL)))
      {
        logger.info("email component  " + getFunctionType());
        initialiseBodyAndSubjectForPreTalentPoolSubmit(getUser(), getApplicant(), getFunctionType());
        logger.info("email htmlBody  " + this.htmlBody);
        emutil.sendMessageOnBehalf(this.from, this.to, this.cc, this.bcc, this.replyTo, this.subject, null, this.htmlBody, null, 0);
      }
      if ((!StringUtils.isNullOrEmpty(getFunctionType())) && (getFunctionType().equals(Common.FORGOT_PASSWORD)))
      {
        logger.info("email component  " + getFunctionType());
        logger.info("email component  " + getSubFunctionType());
        if ((!StringUtils.isNullOrEmpty(getSubFunctionType())) && (getSubFunctionType().equals(Common.FORGOT_PASSWORD_REFERRAL)))
        {
          initialiseBodyAndSubjectForForgotPasswordEmail(getEmployeeReferral(), getSubFunctionType(), getFunctionType());
          logger.info("email htmlBody  " + this.htmlBody);
          emutil.sendMessageOnBehalf(this.from, this.to, this.cc, this.bcc, this.replyTo, this.subject, null, this.htmlBody, null, 0);
        }
        if ((!StringUtils.isNullOrEmpty(getSubFunctionType())) && (getSubFunctionType().equals(Common.FORGOT_PASSWORD_AGENCY)))
        {
          initialiseBodyAndSubjectForForgotPasswordEmailVendor(getUser(), getSubFunctionType(), getFunctionType());
          logger.info("email htmlBody  " + this.htmlBody);
          emutil.sendMessageOnBehalf(this.from, this.to, this.cc, this.bcc, this.replyTo, this.subject, null, this.htmlBody, null, 0);
        }
        if ((!StringUtils.isNullOrEmpty(getSubFunctionType())) && (getSubFunctionType().equals(Common.FORGOT_PASSWORD_USER)))
        {
          initialiseBodyAndSubjectForForgotPasswordEmail(getUser(), getSubFunctionType(), getFunctionType());
          logger.info("email htmlBody  " + this.htmlBody);
          emutil.sendMessageOnBehalf(this.from, this.to, this.cc, this.bcc, this.replyTo, this.subject, null, this.htmlBody, null, 0);
        }
        if ((!StringUtils.isNullOrEmpty(getSubFunctionType())) && (getSubFunctionType().equals(Common.FORGOT_PASSWORD_APPLICANT)))
        {
          initialiseBodyAndSubjectForApplicantUserForgotEmail(getAppuser(), getSubFunctionType(), getFunctionType());
          logger.info("email htmlBody  " + this.htmlBody);
          emutil.sendMessageOnBehalf(this.from, this.to, this.cc, this.bcc, this.replyTo, this.subject, null, this.htmlBody, null, 0);
        }
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      logger.info("Error on sending email", e);
    }
    logger.info("run method end");
  }
  
  private void initialiseBodyAndSubjectForOfferApproveRejectEmail(User user, JobApplicant applicant, String functionType)
  {
    logger.info("Indise initialiseBodyAndSubjectForOfferApproveRejectEmail start");
    try
    {
      OrganizationEmailTemplate orgemtmpl = TaskDAO.getOrganizationEmailTemplate(user.getOrganization().getOrgId(), functionType);
      if (orgemtmpl == null) {
        throw new Exception("Email template not found for function " + functionType + " organization" + user.getOrganization().getOrgName());
      }
      if ((orgemtmpl.getStatus() != null) && (orgemtmpl.getStatus().equals("N"))) {
        throw new Exception("Email template is disabled for function " + functionType + "  organization" + user.getOrganization().getOrgName());
      }
      EmailTemplates emptl = orgemtmpl.getEmailtemplate();
      
      String content = emptl.getEmailtemplateData();
      String subjectmp = emptl.getEmailSubject();
      

      content = MergeUtil.mergeApplicantData(applicant, user, content, subjectmp, functionType).getValue();
      subjectmp = MergeUtil.mergeApplicantData(applicant, user, content, subjectmp, functionType).getKey();
      content = MergeUtil.mergeUserData(user, content, subjectmp, functionType).getValue();
      subjectmp = MergeUtil.mergeUserData(user, content, subjectmp, functionType).getKey();
      this.htmlBody = content;
      
      this.subject = subjectmp;
      
      logger.info("htmlBody" + this.htmlBody);
      logger.info("subject" + this.subject);
      logger.info("Indise initialiseBodyAndSubjectForOfferApproveRejectEmail end");
    }
    catch (Exception e)
    {
      logger.info("Exception on setting subject and body" + e);
    }
  }
  
  private void initialiseBodyAndSubjectForUserCreationEmail(User user, User newCreatedUser, String functionType)
    throws Exception
  {
    try
    {
      OrganizationEmailTemplate orgemtmpl = TaskDAO.getOrganizationEmailTemplate(user.getOrganization().getOrgId(), functionType);
      if (orgemtmpl == null) {
        throw new Exception("Email template not found for function " + Common.USER_CREATION_EMAIL_COMPONENT + " organization" + user.getOrganization().getOrgName());
      }
      if ((orgemtmpl.getStatus() != null) && (orgemtmpl.getStatus().equals("N"))) {
        throw new Exception("Email template is disabled for function " + Common.USER_CREATION_EMAIL_COMPONENT + " organization" + user.getOrganization().getOrgName());
      }
      EmailTemplates emptl = orgemtmpl.getEmailtemplate();
      String content = emptl.getEmailtemplateData();
      
      content = content.replaceAll("##first_name##", newCreatedUser.getFirstName());
      content = content.replaceAll("##last_name##", newCreatedUser.getFirstName());
      content = content.replaceAll("##todays_date##", DateUtil.convertDateToStringDate(new Date(), DateUtil.getDatePatternFormat(newCreatedUser.getLocale())));
      String app_url = Constant.getValue("external.url") + "login.do?method=login";
      String app_urla_replace = "<a href='" + app_url + "'" + "target='new'>" + app_url + "</a>";
      content = content.replaceAll("##login_url##", app_urla_replace);
      content = content.replaceAll("##user_name##", newCreatedUser.getUserName());
      content = content.replaceAll("##password##", EncryptDecrypt.decrypt(newCreatedUser.getPassword()));
      content = content.replaceAll("##my_email##", user.getEmailId());
      content = content.replaceAll("##my_phone##", user.getPhoneOffice());
      content = content.replaceAll("##my_name##", user.getFirstName() + " " + user.getLastName());
      content = content.replaceAll("##organization_name##", user.getOrganization().getOrgName());
      

      this.htmlBody = content;
      this.subject = emptl.getEmailSubject();
    }
    catch (Exception e)
    {
      logger.info("Exception on setting subject and body in initialiseBodyAndSubjectForUserCreationEmail" + e);
      throw e;
    }
  }
  
  private void initialiseBodyAndSubjectForMassEmailToALLApplicants(User user, JobApplicant bathemailapplicant, JobRequisition jb, String functionType)
    throws Exception
  {
    try
    {
      String content = getMessagemassemailapplicant();
      content = content.replaceAll("##applicant_name##", bathemailapplicant.getFullName());
      content = content.replaceAll("##comment##", "");
      content = content.replaceAll("##requisition_name##", jb.getJobTitle());
      content = content.replaceAll("##my_first_name##", user.getFirstName());
      content = content.replaceAll("##my_last_name##", user.getLastName());
      content = content.replaceAll("##my_office_phone##", user.getPhoneOffice());
      content = content.replaceAll("##my_email_id##", user.getEmailId());
      content = content.replaceAll("##organization_name##", jb.getOrganization().getOrgName());
      String app_url = Constant.getValue("external.url") + "login.do?method=login";
      String app_urla_replace = "<a href='" + app_url + "'" + "target='new'>" + app_url + "</a>";
      content = content.replaceAll("##applicant_login_url##", app_urla_replace);
      


      this.htmlBody = content;
      this.subject = getSubjectmassemailapplicant();
    }
    catch (Exception e)
    {
      logger.info("Exception on setting subject and body in initialiseBodyAndSubjectForMassEmailToALLApplicants" + e);
      throw e;
    }
  }
  
  private void initialiseBodyAndSubjectForReferralRegistrationEmail(RefferalEmployee refemp, String functionType)
    throws Exception
  {
    try
    {
      OrganizationEmailTemplate orgemtmpl = TaskDAO.getOrganizationEmailTemplate(refemp.getOrganization().getOrgId(), functionType);
      if (orgemtmpl == null) {
        throw new Exception("Email template not found for function " + Common.REFERRAL_USER_REGISTRATION + " organization" + refemp.getOrganization().getOrgName());
      }
      if ((orgemtmpl.getStatus() != null) && (orgemtmpl.getStatus().equals("N"))) {
        throw new Exception("Email template is disabled for function " + Common.REFERRAL_USER_REGISTRATION + " organization" + refemp.getOrganization().getOrgName());
      }
      EmailTemplates emptl = orgemtmpl.getEmailtemplate();
      String content = emptl.getEmailtemplateData();
      
      content = content.replaceAll("##employee_name##", refemp.getEmployeename());
      content = content.replaceAll("##varification_code##", refemp.getVarificationcode());
      content = content.replaceAll("##todays_date##", DateUtil.convertDateToStringDate(new Date(), DateUtil.getDatePatternFormat(refemp.getLocale())));
      String app_url = Constant.getValue("external.url") + "refferal.do?method=verificationcheckdirect";
      app_url = app_url + "&id=" + EncryptDecrypt.encrypt(String.valueOf(refemp.getEmployeeReferalId())) + "&code=" + EncryptDecrypt.encrypt(refemp.getVarificationcode());
      String app_urla_replace = "<a href='" + app_url + "'" + "target='new'>" + Constant.getResourceStringValue("referral.activate", refemp.getLocale()) + "</a>";
      content = content.replaceAll("##verification_url##", app_urla_replace);
      content = content.replaceAll("##organization_name##", refemp.getOrganization().getOrgName());
      

      this.htmlBody = content;
      this.subject = emptl.getEmailSubject();
    }
    catch (Exception e)
    {
      logger.info("Exception on setting subject and body in initialiseBodyAndSubjectForReferralRegistrationEmail" + e);
      throw e;
    }
  }
  
  private void initialiseBodyAndSubjectForReferAFriend(RefferalEmployee refemp, ReferAFriend reffriend, String functionType)
    throws Exception
  {
    try
    {
      OrganizationEmailTemplate orgemtmpl = TaskDAO.getOrganizationEmailTemplate(refemp.getOrganization().getOrgId(), functionType);
      if (orgemtmpl == null) {
        throw new Exception("Email template not found for function " + Common.REFER_FRIEND + " organization" + refemp.getOrganization().getOrgName());
      }
      if ((orgemtmpl.getStatus() != null) && (orgemtmpl.getStatus().equals("N"))) {
        throw new Exception("Email template is disabled for function " + Common.REFER_FRIEND + " organization" + refemp.getOrganization().getOrgName());
      }
      EmailTemplates emptl = orgemtmpl.getEmailtemplate();
      String content = emptl.getEmailtemplateData();
      
      content = content.replaceAll("##employee_name##", refemp.getEmployeename());
      content = content.replaceAll("##friend_name##", reffriend.getName());
      content = content.replaceAll("##comment##", reffriend.getNote());
      String app_url = Constant.getValue("external.url") + "refferaluser.do?method=refferaluserdetails";
      app_url = app_url + "&useremail=" + EncryptDecrypt.encrypt(String.valueOf(reffriend.getEmailId())) + "&refuserid=" + EncryptDecrypt.encrypt(String.valueOf(refemp.getEmployeeReferalId()));
      if (reffriend.getJobRequisitionId() > 0L) {
        app_url = app_url + "&jobreqid=" + EncryptDecrypt.encrypt(String.valueOf(reffriend.getJobRequisitionId()));
      }
      if (refemp.getSuper_user_key() > 0L) {
        app_url = app_url + "&superUserKey=" + EncryptDecrypt.encrypt(String.valueOf(refemp.getSuper_user_key()));
      }
      String app_urla_replace = "<a href='" + app_url + "'" + "target='new'>" + Constant.getResourceStringValue("refferal.referafriend.url", refemp.getLocale()) + "</a>";
      content = content.replaceAll("##referral_url##", app_urla_replace);
      content = content.replaceAll("##organization_name##", refemp.getOrganization().getOrgName());
      

      this.htmlBody = content;
      String subjecttemp = emptl.getEmailSubject();
      subjecttemp = subjecttemp.replaceAll("##employee_name##", refemp.getEmployeename());
      this.subject = subjecttemp;
    }
    catch (Exception e)
    {
      logger.info("Exception on setting subject and body in initialiseBodyAndSubjectForReferralRegistrationEmail" + e);
      throw e;
    }
  }
  
  private void initialiseBodyAndSubjectForTaskAssignEmail(User user, TaskData task, String functionType, String comment)
    throws Exception
  {
    logger.info("inside initialiseBodyAndSubjectForTaskAssignEmail");
    try
    {
      OrganizationEmailTemplate orgemtmpl = TaskDAO.getOrganizationEmailTemplate(user.getOrganization().getOrgId(), functionType);
      if (orgemtmpl == null) {
        throw new Exception("Email template not found for function " + functionType + " organization" + user.getOrganization().getOrgName());
      }
      if ((orgemtmpl.getStatus() != null) && (orgemtmpl.getStatus().equals("N"))) {
        throw new Exception("Email template is disabled for function " + functionType + " organization" + user.getOrganization().getOrgName());
      }
      EmailTemplates emptl = orgemtmpl.getEmailtemplate();
      String content = emptl.getEmailtemplateData();
      
      content = content.replaceAll("##task_assignee_name##", task.getAssignedtoUserName());
      content = content.replaceAll("##task_type##", Constant.getResourceStringValue(task.getTasktype(), user.getLocale()));
      if (task.getEventdate() != null) {
        content = content.replaceAll("##task_date##", DateUtil.convertDateToStringDate(task.getEventdate(), DateUtil.getDatePatternFormat(user.getLocale())));
      }
      if (!StringUtils.isNullOrEmpty(comment)) {
        content = content.replaceAll("##comment##", comment);
      } else {
        content = content.replaceAll("##comment##", "");
      }
      String task_url = Constant.getValue("external.url") + "task.do?method=mypendingtasks";
      String task_url_href = "<a href='" + task_url + "'" + "target='new'>" + task_url + "</a>";
      
      content = content.replaceAll("##task_initiator_name##", task.getAssignedbyUserName());
      content = content.replaceAll("##task_url##", task_url_href);
      


      String subjecttemp = emptl.getEmailSubject();
      subjecttemp = subjecttemp.replaceAll("##task_type##", Constant.getResourceStringValue(task.getTasktype(), user.getLocale()));
      
      this.htmlBody = content;
      this.subject = subjecttemp;
    }
    catch (Exception e)
    {
      logger.info("Exception on setting subject and body in initialiseBodyAndSubjectForTaskAssignEmail" + e);
      throw e;
    }
  }
  
  private void initialiseBodyAndSubjectForTaskCompleteEmail(User user, TaskData task, String functionType, String comment)
    throws Exception
  {
    logger.info("inside initialiseBodyAndSubjectForTaskAssignEmail");
    try
    {
      String content = task.getTaskname() + " completed on " + DateUtil.convertDateToStringDate(new Date(), DateUtil.getDatePatternFormat(user.getLocale())) + " by " + user.getFirstName() + " " + user.getLastName() + "<br>" + "Note :" + comment;
      






      this.htmlBody = content;
      this.subject = (task.getTaskname() + " completed");
    }
    catch (Exception e)
    {
      logger.info("Exception on setting subject and body in initialiseBodyAndSubjectForTaskCompleteEmail" + e);
      throw e;
    }
  }
  
  private void initialiseBodyAndSubjectForExamScheduleEmail(User user, MockTest mocktest, String functionType, String comment)
    throws Exception
  {
    logger.info("inside initialiseBodyAndSubjectForExamScheduleEmail");
    try
    {
      OrganizationEmailTemplate orgemtmpl = TaskDAO.getOrganizationEmailTemplate(user.getOrganization().getOrgId(), functionType);
      if (orgemtmpl == null) {
        throw new Exception("Email template not found for function " + functionType + " organization" + user.getOrganization().getOrgName());
      }
      if ((orgemtmpl.getStatus() != null) && (orgemtmpl.getStatus().equals("N"))) {
        throw new Exception("Email template is disabled for function " + functionType + " organization" + user.getOrganization().getOrgName());
      }
      EmailTemplates emptl = orgemtmpl.getEmailtemplate();
      String content = emptl.getEmailtemplateData();
      
      content = content.replaceAll("##applicant_name##", mocktest.getApplicantName());
      if (!StringUtils.isNullOrEmpty(comment)) {
        content = content.replaceAll("##comment##", comment);
      } else {
        content = content.replaceAll("##comment##", "");
      }
      String exam_url = Constant.getValue("external.url") + "jsp/testengine/pmptech20.jsp?catId=" + mocktest.getCat().getCatId() + "&applicantId=" + mocktest.getApplicantId() + "&uuid=" + mocktest.getUuid();
      String exam_url_href = "<a href='" + exam_url + "'" + "target='new'>" + exam_url + "</a>";
      
      content = content.replaceAll("##my_first_name##", user.getFirstName());
      content = content.replaceAll("##my_last_name##", user.getLastName());
      content = content.replaceAll("##my_office_phone##", user.getPhoneOffice());
      content = content.replaceAll("##my_email_id##", user.getEmailId());
      content = content.replaceAll("##organization_name##", user.getOrganization().getOrgName());
      content = content.replaceAll("##exam_url##", exam_url_href);
      


      String subjecttemp = emptl.getEmailSubject();
      subjecttemp = subjecttemp.replaceAll("##organization_name##", user.getOrganization().getOrgName());
      
      this.htmlBody = content;
      this.subject = subjecttemp;
    }
    catch (Exception e)
    {
      logger.info("Exception on setting subject and body in initialiseBodyAndSubjectForTaskAssignEmail" + e);
      throw e;
    }
  }
  
  private void initialiseBodyAndSubjectForApplicantCommentEmail(User user, JobApplicant applicant, String functionType, String comment)
    throws Exception
  {
    logger.info("inside initialiseBodyAndSubjectForApplicantCommentEmail");
    try
    {
      OrganizationEmailTemplate orgemtmpl = TaskDAO.getOrganizationEmailTemplate(user.getOrganization().getOrgId(), functionType);
      if (orgemtmpl == null) {
        throw new Exception("Email template not found for function " + functionType + " organization" + user.getOrganization().getOrgName());
      }
      if ((orgemtmpl.getStatus() != null) && (orgemtmpl.getStatus().equals("N"))) {
        throw new Exception("Email template is disabled for function " + functionType + " organization" + user.getOrganization().getOrgName());
      }
      EmailTemplates emptl = orgemtmpl.getEmailtemplate();
      String content = emptl.getEmailtemplateData();
      
      content = content.replaceAll("##applicant_name##", applicant.getFullName());
      if (!StringUtils.isNullOrEmpty(comment)) {
        content = content.replaceAll("##comment##", comment);
      } else {
        content = content.replaceAll("##comment##", "");
      }
      String app_login_url = Constant.getValue("external.url") + "applicantlogin.do?method=login";
      String app_login_url_href = "<a href='" + app_login_url + "'" + "target='new'>" + app_login_url + "</a>";
      content = content.replaceAll("##my_first_name##", user.getFirstName());
      content = content.replaceAll("##my_last_name##", user.getLastName());
      content = content.replaceAll("##my_office_phone##", user.getPhoneOffice());
      content = content.replaceAll("##my_email_id##", user.getEmailId());
      content = content.replaceAll("##organization_name##", user.getOrganization().getOrgName());
      content = content.replaceAll("##applicant_login_url##", app_login_url_href);
      


      String subjecttemp = emptl.getEmailSubject();
      subjecttemp = subjecttemp.replaceAll("##organization_name##", user.getOrganization().getOrgName());
      
      this.htmlBody = content;
      this.subject = subjecttemp;
    }
    catch (Exception e)
    {
      logger.info("Exception on setting subject and body in initialiseBodyAndSubjectForApplicantCommentEmail" + e);
      throw e;
    }
  }
  
  private void initialiseBodyAndSubjectForCommentEmail(JobApplicant applicant, String functionType, String comment)
    throws Exception
  {
    logger.info("inside initialiseBodyAndSubjectForCommentEmail");
    try
    {
      String content = comment;
      
      String subjecttemp = "Comment on " + applicant.getFullName() + " # " + applicant.getApplicant_number();
      
      this.htmlBody = content;
      this.subject = subjecttemp;
    }
    catch (Exception e)
    {
      logger.info("Exception on setting subject and body in initialiseBodyAndSubjectForCommentEmail" + e);
      throw e;
    }
  }
  
  private void initialiseBodyAndSubjectForEventEmail(JobApplicant applicant, User user, String functionType, String comment, String event)
    throws Exception
  {
    logger.info("inside initialiseBodyAndSubjectForEventEmail");
    try
    {
      String content = comment;
      
      String subjecttemp = event + " " + applicant.getFullName() + " # " + applicant.getApplicant_number();
      
      content = subjecttemp + "<br>" + "<br>" + content + "<br>" + "";
      if (user != null) {
        content = content + "<br>" + user.getFirstName() + " " + user.getLastName() + "<br>" + user.getEmailId() + "<br>" + user.getPhoneOffice();
      }
      this.htmlBody = content;
      this.subject = subjecttemp;
    }
    catch (Exception e)
    {
      logger.info("Exception on setting subject and body in initialiseBodyAndSubjectForCommentEmail" + e);
      throw e;
    }
  }
  
  private void initialiseBodyAndSubjectForQuestionnaireEmail(User user, QuestionGroupApplicants qnsgrpaction, String functionType, String comment)
    throws Exception
  {
    logger.info("inside initialiseBodyAndSubjectForQuestionnaireEmail");
    try
    {
      OrganizationEmailTemplate orgemtmpl = TaskDAO.getOrganizationEmailTemplate(user.getOrganization().getOrgId(), functionType);
      if (orgemtmpl == null) {
        throw new Exception("Email template not found for function " + functionType + " organization" + user.getOrganization().getOrgName());
      }
      if ((orgemtmpl.getStatus() != null) && (orgemtmpl.getStatus().equals("N"))) {
        throw new Exception("Email template is disabled for function " + functionType + " organization" + user.getOrganization().getOrgName());
      }
      EmailTemplates emptl = orgemtmpl.getEmailtemplate();
      String content = emptl.getEmailtemplateData();
      
      content = content.replaceAll("##applicant_name##", qnsgrpaction.getApplicantName());
      if (!StringUtils.isNullOrEmpty(comment)) {
        content = content.replaceAll("##comment##", comment);
      } else {
        content = content.replaceAll("##comment##", "");
      }
      String exam_url = Constant.getValue("external.url") + "questionnaire.do?method=questionnaire&gpid=" + qnsgrpaction.getQnsGrpAppId() + "&applicantid=" + qnsgrpaction.getApplicantId() + "&uuid=" + qnsgrpaction.getUuid();
      String exam_url_href = "<a href='" + exam_url + "'" + "target='new'>" + exam_url + "</a>";
      
      content = content.replaceAll("##my_first_name##", user.getFirstName());
      content = content.replaceAll("##my_last_name##", user.getLastName());
      content = content.replaceAll("##my_office_phone##", user.getPhoneOffice());
      content = content.replaceAll("##my_email_id##", user.getEmailId());
      content = content.replaceAll("##organization_name##", user.getOrganization().getOrgName());
      content = content.replaceAll("##questionnaire_url##", exam_url_href);
      


      String subjecttemp = emptl.getEmailSubject();
      subjecttemp = subjecttemp.replaceAll("##organization_name##", user.getOrganization().getOrgName());
      
      this.htmlBody = content;
      this.subject = subjecttemp;
    }
    catch (Exception e)
    {
      logger.info("Exception on setting subject and body in initialiseBodyAndSubjectForQuestionnaireEmail" + e);
      throw e;
    }
  }
  
  private void initialiseBodyAndSubjectForRequisitionCreate(User user, JobRequisition jb, String functionType)
    throws Exception
  {
    logger.info("inside initialiseBodyAndSubjectForRequisitionCreate");
    try
    {
      OrganizationEmailTemplate orgemtmpl = TaskDAO.getOrganizationEmailTemplate(user.getOrganization().getOrgId(), functionType);
      if (orgemtmpl == null) {
        throw new Exception("Email template not found for function " + functionType + " organization" + user.getOrganization().getOrgName());
      }
      if ((orgemtmpl.getStatus() != null) && (orgemtmpl.getStatus().equals("N"))) {
        throw new Exception("Email template is disabled for function " + functionType + " organization" + user.getOrganization().getOrgName());
      }
      EmailTemplates emptl = orgemtmpl.getEmailtemplate();
      String content = emptl.getEmailtemplateData();
      
      content = content.replaceAll("##requisition_no##", String.valueOf(jb.getJobreqId()));
      content = content.replaceAll("##requisition_name##", jb.getJobreqName());
      content = content.replaceAll("##todays_date##", DateUtil.convertDateToStringDate(new Date(), DateUtil.getDatePatternFormat(user.getLocale())));
      String app_url = Constant.getValue("external.url") + "login.do?method=login";
      String app_urla_replace = "<a href='" + app_url + "'" + "target='new'>" + app_url + "</a>";
      content = content.replaceAll("##login_url##", app_urla_replace);
      
      content = content.replaceAll("##my_first_name##", user.getFirstName());
      content = content.replaceAll("##my_last_name##", user.getLastName());
      content = content.replaceAll("##my_office_phone##", user.getPhoneOffice());
      content = content.replaceAll("##my_email_id##", user.getEmailId());
      content = content.replaceAll("##organization_name##", user.getOrganization().getOrgName());
      


      String subjecttemp = emptl.getEmailSubject();
      subjecttemp = subjecttemp.replaceAll("##requisition_no##", String.valueOf(jb.getJobreqId()));
      
      this.htmlBody = content;
      this.subject = subjecttemp;
    }
    catch (Exception e)
    {
      logger.info("Exception on setting subject and body in initialiseBodyAndSubjectForRequisitionCreate" + e);
      throw e;
    }
  }
  
  private void initialiseBodyAndSubjectForRequisitionEmails(User user, JobRequisition jb, String functionType, String comment)
    throws Exception
  {
    logger.info("inside initialiseBodyAndSubjectForRequisitionEmails");
    try
    {
      OrganizationEmailTemplate orgemtmpl = TaskDAO.getOrganizationEmailTemplate(user.getOrganization().getOrgId(), functionType);
      if (orgemtmpl == null) {
        throw new Exception("Email template not found for function " + functionType + " organization" + user.getOrganization().getOrgName());
      }
      if ((orgemtmpl.getStatus() != null) && (orgemtmpl.getStatus().equals("N"))) {
        throw new Exception("Email template is disabled for function " + functionType + " organization" + user.getOrganization().getOrgName());
      }
      EmailTemplates emptl = orgemtmpl.getEmailtemplate();
      String content = emptl.getEmailtemplateData();
      
      content = content.replaceAll("##requisition_no##", String.valueOf(jb.getJobreqId()));
      content = content.replaceAll("##requisition_name##", jb.getJobreqName());
      if (!StringUtils.isNullOrEmpty(comment)) {
        content = content.replaceAll("##note##", comment);
      }
      content = content.replaceAll("##todays_date##", DateUtil.convertDateToStringDate(new Date(), DateUtil.getDatePatternFormat(user.getLocale())));
      String app_url = Constant.getValue("external.url") + "login.do?method=login";
      String app_urla_replace = "<a href='" + app_url + "'" + "target='new'>" + app_url + "</a>";
      content = content.replaceAll("##login_url##", app_urla_replace);
      
      content = content.replaceAll("##my_first_name##", user.getFirstName());
      content = content.replaceAll("##my_last_name##", user.getLastName());
      content = content.replaceAll("##my_office_phone##", user.getPhoneOffice());
      content = content.replaceAll("##my_email_id##", user.getEmailId());
      content = content.replaceAll("##organization_name##", user.getOrganization().getOrgName());
      


      String subjecttemp = emptl.getEmailSubject();
      subjecttemp = subjecttemp.replaceAll("##requisition_no##", String.valueOf(jb.getJobreqId()));
      
      this.htmlBody = content;
      this.subject = subjecttemp;
    }
    catch (Exception e)
    {
      logger.info("Exception on setting subject and body in initialiseBodyAndSubjectForRequisitionEmails" + e);
      throw e;
    }
  }
  
  private void initialiseBodyAndSubjectForRequisitionApprovalReassign(User user, JobRequisition jb, String functionType, String comment)
    throws Exception
  {
    logger.info("inside initialiseBodyAndSubjectForRequisitionApprovalReassign");
    try
    {
      OrganizationEmailTemplate orgemtmpl = TaskDAO.getOrganizationEmailTemplate(user.getOrganization().getOrgId(), functionType);
      if (orgemtmpl == null) {
        throw new Exception("Email template not found for function " + functionType + " organization" + user.getOrganization().getOrgName());
      }
      if ((orgemtmpl.getStatus() != null) && (orgemtmpl.getStatus().equals("N"))) {
        throw new Exception("Email template is disabled for function " + functionType + " organization" + user.getOrganization().getOrgName());
      }
      EmailTemplates emptl = orgemtmpl.getEmailtemplate();
      String content = emptl.getEmailtemplateData();
      
      content = content.replaceAll("##requisition_no##", String.valueOf(jb.getJobreqId()));
      content = content.replaceAll("##requisition_name##", jb.getJobreqName());
      content = content.replaceAll("##to_user##", jb.getCurrentOwnerName());
      if (!StringUtils.isNullOrEmpty(comment)) {
        content = content.replaceAll("##note##", comment);
      }
      content = content.replaceAll("##todays_date##", DateUtil.convertDateToStringDate(new Date(), DateUtil.getDatePatternFormat(user.getLocale())));
      String app_url = Constant.getValue("external.url") + "login.do?method=login";
      String app_urla_replace = "<a href='" + app_url + "'" + "target='new'>" + app_url + "</a>";
      content = content.replaceAll("##login_url##", app_urla_replace);
      
      content = content.replaceAll("##my_first_name##", user.getFirstName());
      content = content.replaceAll("##my_last_name##", user.getLastName());
      content = content.replaceAll("##my_office_phone##", user.getPhoneOffice());
      content = content.replaceAll("##my_email_id##", user.getEmailId());
      content = content.replaceAll("##organization_name##", user.getOrganization().getOrgName());
      


      String subjecttemp = emptl.getEmailSubject();
      subjecttemp = subjecttemp.replaceAll("##requisition_no##", String.valueOf(jb.getJobreqId()));
      subjecttemp = subjecttemp.replaceAll("##to_user##", jb.getCurrentOwnerName());
      
      this.htmlBody = content;
      this.subject = subjecttemp;
    }
    catch (Exception e)
    {
      logger.info("Exception on setting subject and body in initialiseBodyAndSubjectForRequisitionApprovalReassign" + e);
      throw e;
    }
  }
  
  private void initialiseBodyAndSubjectForApplicantUserCreationEmail(User user, ApplicantUser appuser, String functionType)
    throws Exception
  {
    logger.info("inside initialiseBodyAndSubjectForApplicantUserCreationEmail");
    try
    {
      OrganizationEmailTemplate orgemtmpl = TaskDAO.getOrganizationEmailTemplate(user.getOrganization().getOrgId(), functionType);
      if (orgemtmpl == null) {
        throw new Exception("Email template not found for function " + functionType + " organization" + user.getOrganization().getOrgName());
      }
      if ((orgemtmpl.getStatus() != null) && (orgemtmpl.getStatus().equals("N"))) {
        throw new Exception("Email template is disabled for function " + functionType + " organization" + user.getOrganization().getOrgName());
      }
      EmailTemplates emptl = orgemtmpl.getEmailtemplate();
      String content = emptl.getEmailtemplateData();
      
      content = content.replaceAll("##applicant_name##", appuser.getApplicant().getFullName());
      content = content.replaceAll("##job_title##", appuser.getApplicant().getJobTitle());
      content = content.replaceAll("##todays_date##", DateUtil.convertDateToStringDate(new Date(), DateUtil.getDatePatternFormat(appuser.getLocale())));
      content = content.replaceAll("##organization_name##", user.getOrganization().getOrgName());
      String offer_acceptance_url = Constant.getValue("external.url") + "applicantlogin.do?method=login";
      String offer_acceptance_url_href = "<a href='" + offer_acceptance_url + "'" + "target='new'>" + offer_acceptance_url + "</a>";
      
      content = content.replaceAll("##user_name##", appuser.getEmailId());
      content = content.replaceAll("##password##", EncryptDecrypt.decrypt(appuser.getPassword()));
      content = content.replaceAll("##applicant_login_code##", appuser.getApplicantCode());
      
      content = content.replaceAll("##applicant_login_url##", offer_acceptance_url_href);
      


      String subjecttemp = emptl.getEmailSubject();
      subjecttemp = subjecttemp.replaceAll("##applicant_name##", appuser.getApplicant().getFullName());
      
      this.htmlBody = content;
      this.subject = subjecttemp;
    }
    catch (Exception e)
    {
      logger.info("Exception on setting subject and body in initialiseBodyAndSubjectForApplicantUserCreationEmail" + e);
      throw e;
    }
  }
  
  private void initialiseBodyAndSubjectForApplicantUserForgotEmail(ApplicantUser appuser, String subfunctiontype, String functionType)
    throws Exception
  {
    logger.info("inside initialiseBodyAndSubjectForApplicantUserCreationEmail");
    try
    {
      Organization org = null;
      if ((appuser.getApplicant() != null) && (appuser.getApplicant().getReqId() != 0L)) {
        org = BOFactory.getJobRequistionBO().getOrganizationByReqId(appuser.getApplicant().getReqId());
      } else if (appuser.getApplicant() != null) {
        org = BOFactory.getOrganizationBO().getRootOrganization(appuser.getApplicant().getSuper_user_key());
      }
      OrganizationEmailTemplate orgemtmpl = TaskDAO.getOrganizationEmailTemplate(org.getOrgId(), functionType);
      if (orgemtmpl == null) {
        throw new Exception("Email template not found for function " + functionType + " organization");
      }
      if ((orgemtmpl.getStatus() != null) && (orgemtmpl.getStatus().equals("N"))) {
        throw new Exception("Email template is disabled for function " + functionType + " organization");
      }
      EmailTemplates emptl = orgemtmpl.getEmailtemplate();
      String content = emptl.getEmailtemplateData();
      logger.info("emptl" + emptl.getEmailtemplateData());
      
      content = content.replaceAll("##full_name##", appuser.getApplicant().getFullName());
      content = content.replaceAll("##todays_date##", DateUtil.convertDateToStringDate(new Date(), DateUtil.getDatePatternFormat(appuser.getLocale())));
      content = content.replaceAll("##organization_name##", org.getOrgName());
      String offer_acceptance_url = Constant.getValue("external.url") + "applicantlogin.do?method=login";
      String offer_acceptance_url_href = "<a href='" + offer_acceptance_url + "'" + "target='new'>" + offer_acceptance_url + "</a>";
      
      content = content.replaceAll("##user_name##", appuser.getEmailId());
      content = content.replaceAll("##password##", EncryptDecrypt.decrypt(appuser.getPassword()));
      content = content.replaceAll("##login_url##", offer_acceptance_url_href);
      


      String subjecttemp = emptl.getEmailSubject();
      subjecttemp = subjecttemp.replaceAll("##applicant_name##", appuser.getApplicant().getFullName());
      
      this.htmlBody = content;
      this.subject = subjecttemp;
    }
    catch (Exception e)
    {
      logger.info("Exception on setting subject and body in initialiseBodyAndSubjectForApplicantUserCreationEmail" + e);
      throw e;
    }
  }
  
  private void initialiseBodyAndSubjectForForgotPasswordEmailVendor(User vendor, String subfunctionType, String functionType)
    throws Exception
  {
    logger.info("inside initialiseBodyAndSubjectForForgotPasswordEmailVendor ..  vendor ");
    try
    {
      Organization org = BOFactory.getOrganizationBO().getRootOrganization(vendor.getSuper_user_key());
      OrganizationEmailTemplate orgemtmpl = TaskDAO.getOrganizationEmailTemplate(org.getOrgId(), functionType);
      if (orgemtmpl == null) {
        throw new Exception("Email template not found for function " + functionType + " and subfunction " + subfunctionType);
      }
      if ((orgemtmpl.getStatus() != null) && (orgemtmpl.getStatus().equals("N"))) {
        throw new Exception("Email template is disabled for function " + functionType + " and subfunction " + subfunctionType);
      }
      EmailTemplates emptl = orgemtmpl.getEmailtemplate();
      String content = emptl.getEmailtemplateData();
      
      content = content.replaceAll("##full_name##", vendor.getFirstName());
      content = content.replaceAll("##user_name##", vendor.getUserName());
      content = content.replaceAll("##password##", EncryptDecrypt.decrypt(vendor.getPassword()));
      content = content.replaceAll("##todays_date##", DateUtil.convertDateToStringDate(new Date(), DateUtil.getDatePatternFormat(vendor.getLocale())));
      String app_url = Constant.getValue("external.url") + "alogin.do?method=login";
      
      String app_urla_replace = "<a href='" + app_url + "'" + "target='new'>" + app_url + "</a>";
      content = content.replaceAll("##login_url##", app_urla_replace);
      

      content = content.replaceAll("##organization_name##", org.getOrgName());
      



      this.htmlBody = content;
      this.subject = emptl.getEmailSubject();
    }
    catch (Exception e)
    {
      logger.info("Exception on setting subject and body in initialiseBodyAndSubjectForForgotPasswordEmailVendor" + e);
      throw e;
    }
  }
  
  private void initialiseBodyAndSubjectForForgotPasswordEmail(User user, String subfunctionType, String functionType)
    throws Exception
  {
    logger.info("inside initialiseBodyAndSubjectForForgotPasswordEmail ..  vendor ");
    try
    {
      OrganizationEmailTemplate orgemtmpl = TaskDAO.getOrganizationEmailTemplate(user.getOrganization().getOrgId(), functionType);
      if (orgemtmpl == null) {
        throw new Exception("Email template not found for function " + functionType + " and subfunction " + subfunctionType + " organization" + user.getOrganization().getOrgName());
      }
      if ((orgemtmpl.getStatus() != null) && (orgemtmpl.getStatus().equals("N"))) {
        throw new Exception("Email template is disabled for function " + functionType + " and subfunction " + subfunctionType + " organization" + user.getOrganization().getOrgName());
      }
      EmailTemplates emptl = orgemtmpl.getEmailtemplate();
      String content = emptl.getEmailtemplateData();
      
      content = content.replaceAll("##full_name##", user.getFirstName() + " " + user.getLastName());
      content = content.replaceAll("##user_name##", user.getUserName());
      content = content.replaceAll("##password##", EncryptDecrypt.decrypt(user.getPassword()));
      content = content.replaceAll("##todays_date##", DateUtil.convertDateToStringDate(new Date(), DateUtil.getDatePatternFormat(user.getLocale())));
      String app_url = Constant.getValue("external.url") + "login.do?method=login";
      
      String app_urla_replace = "<a href='" + app_url + "'" + "target='new'>" + app_url + "</a>";
      content = content.replaceAll("##login_url##", app_urla_replace);
      

      content = content.replaceAll("##organization_name##", user.getOrganization().getOrgName());
      



      this.htmlBody = content;
      this.subject = emptl.getEmailSubject();
    }
    catch (Exception e)
    {
      logger.info("Exception on setting subject and body in initialiseBodyAndSubjectForForgotPasswordEmail" + e);
      throw e;
    }
  }
  
  private void initialiseBodyAndSubjectForForgotPasswordEmail(RefferalEmployee refemp, String subfunctionType, String functionType)
    throws Exception
  {
    logger.info("inside initialiseBodyAndSubjectForForgotPasswordEmail");
    try
    {
      OrganizationEmailTemplate orgemtmpl = TaskDAO.getOrganizationEmailTemplate(refemp.getOrganization().getOrgId(), functionType);
      if (orgemtmpl == null) {
        throw new Exception("Email template not found for function " + functionType + " and subfunction " + subfunctionType + " organization" + refemp.getOrganization().getOrgName());
      }
      if ((orgemtmpl.getStatus() != null) && (orgemtmpl.getStatus().equals("N"))) {
        throw new Exception("Email template is disabled for function " + functionType + " and subfunction " + subfunctionType + " organization" + refemp.getOrganization().getOrgName());
      }
      EmailTemplates emptl = orgemtmpl.getEmailtemplate();
      String content = emptl.getEmailtemplateData();
      
      content = content.replaceAll("##full_name##", refemp.getEmployeename());
      content = content.replaceAll("##user_name##", refemp.getEmployeeemail());
      content = content.replaceAll("##password##", EncryptDecrypt.decrypt(refemp.getPassword()));
      content = content.replaceAll("##todays_date##", DateUtil.convertDateToStringDate(new Date(), DateUtil.getDatePatternFormat(refemp.getLocale())));
      String app_url = Constant.getValue("external.url") + "reflogin.do?method=login";
      
      String app_urla_replace = "<a href='" + app_url + "'" + "target='new'>" + app_url + "</a>";
      content = content.replaceAll("##login_url##", app_urla_replace);
      content = content.replaceAll("##organization_name##", refemp.getOrganization().getOrgName());
      



      this.htmlBody = content;
      this.subject = emptl.getEmailSubject();
    }
    catch (Exception e)
    {
      logger.info("Exception on setting subject and body in initialiseBodyAndSubjectForForgotPasswordEmail" + e);
      throw e;
    }
  }
  
  private void initialiseBodyAndSubjectForApplicationSubmit(User user, JobApplicant applicant, String functionType)
  {
    logger.info("Indise initialiseBodyAndSubjectForApplicationSubmit start");
    try
    {
      OrganizationEmailTemplate orgemtmpl = null;
      if (user != null) {
        orgemtmpl = TaskDAO.getOrganizationEmailTemplate(user.getOrganization().getOrgId(), functionType);
      } else {
        orgemtmpl = TaskDAO.getOrganizationEmailTemplate(BOFactory.getOrganizationBO().getRootOrganization(applicant.getSuper_user_key()).getOrgId(), functionType);
      }
      if (orgemtmpl == null) {
        throw new Exception("Email template not found for function " + functionType + " organization" + user.getOrganization().getOrgName());
      }
      if ((orgemtmpl.getStatus() != null) && (orgemtmpl.getStatus().equals("N"))) {
        throw new Exception("Email template is disabled for function " + functionType + "  organization" + user.getOrganization().getOrgName());
      }
      EmailTemplates emptl = orgemtmpl.getEmailtemplate();
      
      String content = emptl.getEmailtemplateData();
      

      content = content.replaceAll("##applicant_name##", applicant.getFullName());
      content = content.replaceAll("##application_no##", String.valueOf(applicant.getApplicant_number()));
      
      content = content.replaceAll("##exam_questionnaire_content##", getExam_questionnaire_content());
      
      this.htmlBody = content;
      this.subject = emptl.getEmailSubject();
      logger.info("Indise initialiseBodyAndSubjectForApplicationSubmit end");
    }
    catch (Exception e)
    {
      logger.info("Exception on setting subject and body in initialiseBodyAndSubjectForApplicationSubmit" + e);
    }
  }
  
  private void initialiseBodyAndSubjectForPreApplicationSubmit(User user, JobApplicant applicant, String functionType)
  {
    logger.info("Indise initialiseBodyAndSubjectForPreApplicationSubmit start");
    try
    {
      OrganizationEmailTemplate orgemtmpl = null;
      if (user != null) {
        orgemtmpl = TaskDAO.getOrganizationEmailTemplate(user.getOrganization().getOrgId(), functionType);
      } else {
        orgemtmpl = TaskDAO.getOrganizationEmailTemplate(BOFactory.getOrganizationBO().getRootOrganization(applicant.getSuper_user_key()).getOrgId(), functionType);
      }
      if (orgemtmpl == null) {
        throw new Exception("Email template not found for function " + functionType + " organization" + user.getOrganization().getOrgName());
      }
      if ((orgemtmpl.getStatus() != null) && (orgemtmpl.getStatus().equals("N"))) {
        throw new Exception("Email template is disabled for function " + functionType + "  organization" + user.getOrganization().getOrgName());
      }
      EmailTemplates emptl = orgemtmpl.getEmailtemplate();
      
      String content = emptl.getEmailtemplateData();
      

      content = content.replaceAll("##applicant_name##", applicant.getFullName());
      

      String app_url = Constant.getValue("external.url") + "refferaluser.do?method=refferaluserdetails";
      
      app_url = app_url + "&jobreqid=" + EncryptDecrypt.encrypt(String.valueOf(applicant.getReqId())) + "&from=preapplicant&source=OTHERJOB" + "&locale_code=" + applicant.getLocale_code();
      
      app_url = app_url + "&applicantId=" + EncryptDecrypt.encrypt(String.valueOf(applicant.getApplicantId()));
      
      Locale locale = new Locale();
      locale.setLocaleCode(applicant.getLocale_code());
      
      String app_urla_replace = "<a href='" + app_url + "'" + "target='new'>" + Constant.getResourceStringValue("submit.details", locale) + "</a>";
      content = content.replaceAll("##application_submit_url##", app_urla_replace);
      
      this.htmlBody = content;
      this.subject = emptl.getEmailSubject();
      logger.info("Indise initialiseBodyAndSubjectForPreApplicationSubmit end");
    }
    catch (Exception e)
    {
      logger.info("Exception on setting subject and body in initialiseBodyAndSubjectForPreApplicationSubmit" + e);
    }
  }
  
  private void initialiseBodyAndSubjectForPreTalentPoolSubmit(User user, JobApplicant applicant, String functionType)
  {
    logger.info("Indise initialiseBodyAndSubjectForPreTalentPoolSubmit start");
    try
    {
      OrganizationEmailTemplate orgemtmpl = null;
      if (user != null) {
        orgemtmpl = TaskDAO.getOrganizationEmailTemplate(user.getOrganization().getOrgId(), functionType);
      } else {
        orgemtmpl = TaskDAO.getOrganizationEmailTemplate(BOFactory.getOrganizationBO().getRootOrganization(applicant.getSuper_user_key()).getOrgId(), functionType);
      }
      if (orgemtmpl == null) {
        throw new Exception("Email template not found for function " + functionType + " organization" + user.getOrganization().getOrgName());
      }
      if ((orgemtmpl.getStatus() != null) && (orgemtmpl.getStatus().equals("N"))) {
        throw new Exception("Email template is disabled for function " + functionType + "  organization" + user.getOrganization().getOrgName());
      }
      EmailTemplates emptl = orgemtmpl.getEmailtemplate();
      
      String content = emptl.getEmailtemplateData();
      

      content = content.replaceAll("##applicant_name##", applicant.getFullName());
      

      String app_url = Constant.getValue("external.url") + "refferaluser.do?method=refferaluserdetails";
      
      app_url = app_url + "&from=pretalentpool&source=OURCOMPANY" + "&locale_code=" + applicant.getLocale_code();
      
      app_url = app_url + "&applicantId=" + EncryptDecrypt.encrypt(String.valueOf(applicant.getApplicantId()));
      
      Locale locale = new Locale();
      locale.setLocaleCode(applicant.getLocale_code());
      
      String app_urla_replace = "<a href='" + app_url + "'" + "target='new'>" + Constant.getResourceStringValue("submit.details", locale) + "</a>";
      content = content.replaceAll("##application_submit_url##", app_urla_replace);
      
      this.htmlBody = content;
      this.subject = emptl.getEmailSubject();
      logger.info("Indise initialiseBodyAndSubjectForPreTalentPoolSubmit end");
    }
    catch (Exception e)
    {
      logger.info("Exception on setting subject and body in initialiseBodyAndSubjectForPreTalentPoolSubmit" + e);
    }
  }
  
  private void initialiseBodyAndSubjectForOfferApproval(User user, JobApplicant applicant, String functionType)
  {
    logger.info("Indise initialiseBodyAndSubjectForOfferApproval start");
    try
    {
      OrganizationEmailTemplate orgemtmpl = TaskDAO.getOrganizationEmailTemplate(user.getOrganization().getOrgId(), functionType);
      if (orgemtmpl == null) {
        throw new Exception("Email template not found for function " + functionType + " organization" + user.getOrganization().getOrgName());
      }
      if ((orgemtmpl.getStatus() != null) && (orgemtmpl.getStatus().equals("N"))) {
        throw new Exception("Email template is disabled for function " + functionType + "  organization" + user.getOrganization().getOrgName());
      }
      EmailTemplates emptl = orgemtmpl.getEmailtemplate();
      
      String content = emptl.getEmailtemplateData();
      String subjectmp = emptl.getEmailSubject();
      

      content = MergeUtil.mergeApplicantData(applicant, user, content, subjectmp, functionType).getValue();
      subjectmp = MergeUtil.mergeApplicantData(applicant, user, content, subjectmp, functionType).getKey();
      content = MergeUtil.mergeUserData(user, content, subjectmp, functionType).getValue();
      subjectmp = MergeUtil.mergeUserData(user, content, subjectmp, functionType).getKey();
      this.htmlBody = content;
      
      this.subject = subjectmp;
      
      logger.info("htmlBody" + this.htmlBody);
      logger.info("subject" + this.subject);
      

      logger.info("Indise initialiseBodyAndSubjectForOfferApproval end");
    }
    catch (Exception e)
    {
      logger.info("Exception on setting subject and body in initialiseBodyAndSubjectForOfferApproval" + e);
    }
  }
  
  private void initialiseBodyAndSubjectForReviewFeedback(User user, JobApplicant applicant, String functionType)
  {
    logger.info("Indise initialiseBodyAndSubjectForReviewFeedback start");
    try
    {
      OrganizationEmailTemplate orgemtmpl = TaskDAO.getOrganizationEmailTemplate(user.getOrganization().getOrgId(), functionType);
      if (orgemtmpl == null) {
        throw new Exception("Email template not found for function " + functionType + " organization" + user.getOrganization().getOrgName());
      }
      if ((orgemtmpl.getStatus() != null) && (orgemtmpl.getStatus().equals("N"))) {
        throw new Exception("Email template is disabled for function " + functionType + "  organization" + user.getOrganization().getOrgName());
      }
      EmailTemplates emptl = orgemtmpl.getEmailtemplate();
      
      String content = emptl.getEmailtemplateData();
      String subjectmp = emptl.getEmailSubject();
      


      content = MergeUtil.mergeApplicantData(applicant, user, content, subjectmp, functionType).getValue();
      subjectmp = MergeUtil.mergeApplicantData(applicant, user, content, subjectmp, functionType).getKey();
      content = MergeUtil.mergeUserData(user, content, subjectmp, functionType).getValue();
      subjectmp = MergeUtil.mergeUserData(user, content, subjectmp, functionType).getKey();
      this.htmlBody = content;
      
      this.subject = subjectmp;
      
      logger.info("htmlBody" + this.htmlBody);
      logger.info("subject" + this.subject);
      
      logger.info("Indise initialiseBodyAndSubjectForReviewFeedback end");
    }
    catch (Exception e)
    {
      logger.info("Exception on setting subject and body initialiseBodyAndSubjectForReviewFeedback" + e);
    }
  }
  
  private void initialiseBodyForApplicantMove(User user, JobApplicant applicant, String functionType)
  {
    logger.info("Indise initialiseBodyForApplicantMove start");
    try
    {
      String content = "";
      String[] ags = { applicant.getFullName() };
      content = Constant.getResourceStringValue("aquisition.applicant.moved.from", user.getLocale(), ags) + " " + getComment();
      content = content + "\n" + Constant.getResourceStringValue("aquisition.applicant.applicantno", user.getLocale()) + " : " + applicant.getApplicantId() + "\n";
      content = content + "\n" + Constant.getResourceStringValue("aquisition.applicant.moved.by", user.getLocale()) + user.getFirstName() + " " + user.getLastName();
      this.htmlBody = content;
      this.subject = (Constant.getResourceStringValue("aquisition.applicant.moved.from", user.getLocale(), ags) + " " + this.subject);
      
      logger.info("Indise initialiseBodyForApplicantMove end");
    }
    catch (Exception e)
    {
      logger.info("Exception on setting subject and body initialiseBodyForApplicantMove" + e);
    }
  }
  
  private void initialiseBodyForApplicantDelete(User user, JobApplicant applicant, String functionType)
  {
    logger.info("Indise initialiseBodyForApplicantDelete start");
    try
    {
      String content = "";
      String[] ags = { applicant.getFullName(), String.valueOf(applicant.getApplicantId()), String.valueOf(applicant.getReqId()), applicant.getJobTitle(), user.getFirstName() + " " + user.getLastName() };
      content = Constant.getResourceStringValue("aquisition.applicant.mark.for.delete.content", user.getLocale(), ags) + "\n" + getComment();
      
      this.htmlBody = content;
      this.subject = Constant.getResourceStringValue("aquisition.applicant.mark.for.delete.subject", user.getLocale(), ags);
      
      logger.info("Indise initialiseBodyForApplicantDelete end");
    }
    catch (Exception e)
    {
      logger.info("Exception on setting subject and body initialiseBodyForApplicantDelete" + e);
    }
  }
  
  private void initialiseBodyForApplicantUndoDelete(User user, JobApplicant applicant, String functionType)
  {
    logger.info("Indise initialiseBodyForApplicantUndoDelete start");
    try
    {
      String content = "";
      String[] ags = { applicant.getFullName(), String.valueOf(applicant.getApplicantId()), String.valueOf(applicant.getReqId()), applicant.getJobTitle(), user.getFirstName() + " " + user.getLastName() };
      content = Constant.getResourceStringValue("aquisition.applicant.mark.for.undo.delete.content", user.getLocale(), ags) + "\n" + getComment();
      
      this.htmlBody = content;
      this.subject = Constant.getResourceStringValue("aquisition.applicant.mark.for.undo.delete.subject", user.getLocale(), ags);
      
      logger.info("Indise initialiseBodyForApplicantUndoDelete end");
    }
    catch (Exception e)
    {
      logger.info("Exception on setting subject and body initialiseBodyForApplicantUndoDelete" + e);
    }
  }
  
  private void initialiseBodyAndSubjectForReviewInterviewSchedule(User user, JobApplicant applicant, String functionType)
  {
    logger.info("Indise initialiseBodyAndSubjectForReviewInterviewSchedule start");
    try
    {
      OrganizationEmailTemplate orgemtmpl = TaskDAO.getOrganizationEmailTemplate(user.getOrganization().getOrgId(), functionType);
      if (orgemtmpl == null) {
        throw new Exception("Email template not found for function " + functionType + " organization" + user.getOrganization().getOrgName());
      }
      if ((orgemtmpl.getStatus() != null) && (orgemtmpl.getStatus().equals("N"))) {
        throw new Exception("Email template is disabled for function " + functionType + "  organization" + user.getOrganization().getOrgName());
      }
      EmailTemplates emptl = orgemtmpl.getEmailtemplate();
      
      String content = emptl.getEmailtemplateData();
      String subjectmp = emptl.getEmailSubject();
      




      content = MergeUtil.mergeApplicantData(applicant, user, content, subjectmp, functionType).getValue();
      subjectmp = MergeUtil.mergeApplicantData(applicant, user, content, subjectmp, functionType).getKey();
      content = MergeUtil.mergeUserData(user, content, subjectmp, functionType).getValue();
      subjectmp = MergeUtil.mergeUserData(user, content, subjectmp, functionType).getKey();
      this.htmlBody = content;
      
      this.subject = subjectmp;
      
      logger.info("htmlBody" + this.htmlBody);
      logger.info("subject" + this.subject);
      
      logger.info("Indise initialiseBodyAndSubjectForReviewInterviewSchedule end");
    }
    catch (Exception e)
    {
      logger.info("Exception on setting subject and body initialiseBodyAndSubjectForReviewInterviewSchedule" + e);
    }
  }
  
  public String getFunctionType()
  {
    return this.functionType;
  }
  
  public void setFunctionType(String functionType)
  {
    this.functionType = functionType;
  }
  
  public User getUser()
  {
    return this.user;
  }
  
  public void setUser(User user)
  {
    this.user = user;
  }
  
  public JobApplicant getApplicant()
  {
    return this.applicant;
  }
  
  public void setApplicant(JobApplicant applicant)
  {
    this.applicant = applicant;
  }
  
  public User getNewCreatedUser()
  {
    return this.newCreatedUser;
  }
  
  public void setNewCreatedUser(User newCreatedUser)
  {
    this.newCreatedUser = newCreatedUser;
  }
  
  public RefferalEmployee getEmployeeReferral()
  {
    return this.employeeReferral;
  }
  
  public void setEmployeeReferral(RefferalEmployee employeeReferral)
  {
    this.employeeReferral = employeeReferral;
  }
  
  public ApplicantUser getAppuser()
  {
    return this.appuser;
  }
  
  public void setAppuser(ApplicantUser appuser)
  {
    this.appuser = appuser;
  }
  
  public String getSubFunctionType()
  {
    return this.subFunctionType;
  }
  
  public void setSubFunctionType(String subFunctionType)
  {
    this.subFunctionType = subFunctionType;
  }
  
  public TaskData getTaskdata()
  {
    return this.taskdata;
  }
  
  public void setTaskdata(TaskData taskdata)
  {
    this.taskdata = taskdata;
  }
  
  public String getComment()
  {
    return this.comment;
  }
  
  public void setComment(String comment)
  {
    this.comment = comment;
  }
  
  public ReferAFriend getReferafriend()
  {
    return this.referafriend;
  }
  
  public void setReferafriend(ReferAFriend referafriend)
  {
    this.referafriend = referafriend;
  }
  
  public MockTest getMocktest()
  {
    return this.mocktest;
  }
  
  public void setMocktest(MockTest mocktest)
  {
    this.mocktest = mocktest;
  }
  
  public QuestionGroupApplicants getQuestionnaire()
  {
    return this.questionnaire;
  }
  
  public void setQuestionnaire(QuestionGroupApplicants questionnaire)
  {
    this.questionnaire = questionnaire;
  }
  
  public String getExam_questionnaire_content()
  {
    return this.exam_questionnaire_content;
  }
  
  public void setExam_questionnaire_content(String examQuestionnaireContent)
  {
    this.exam_questionnaire_content = examQuestionnaireContent;
  }
  
  public Object getObject()
  {
    return this.object;
  }
  
  public void setObject(Object object)
  {
    this.object = object;
  }
  
  public JobApplicant getMassemailapplicant()
  {
    return this.massemailapplicant;
  }
  
  public void setMassemailapplicant(JobApplicant massemailapplicant)
  {
    this.massemailapplicant = massemailapplicant;
  }
  
  public String getSubjectmassemailapplicant()
  {
    return this.subjectmassemailapplicant;
  }
  
  public void setSubjectmassemailapplicant(String subjectmassemailapplicant)
  {
    this.subjectmassemailapplicant = subjectmassemailapplicant;
  }
  
  public String getMessagemassemailapplicant()
  {
    return this.Messagemassemailapplicant;
  }
  
  public void setMessagemassemailapplicant(String messagemassemailapplicant)
  {
    this.Messagemassemailapplicant = messagemassemailapplicant;
  }
  
  public JobRequisition getJobreq()
  {
    return this.jobreq;
  }
  
  public void setJobreq(JobRequisition jobreq)
  {
    this.jobreq = jobreq;
  }
  
  public String getEvent()
  {
    return this.event;
  }
  
  public void setEvent(String event)
  {
    this.event = event;
  }
}
