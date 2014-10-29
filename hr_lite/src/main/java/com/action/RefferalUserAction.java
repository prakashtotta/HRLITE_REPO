package com.action;

import com.bean.ActionsAttachment;
import com.bean.ApplicantAttachments;
import com.bean.ApplicantSkills;
import com.bean.EducationDetails;
import com.bean.JobApplicant;
import com.bean.JobRequisition;
import com.bean.Locale;
import com.bean.Organization;
import com.bean.PreviousOrgDetails;
import com.bean.RefferalEmployee;
import com.bean.ResumeSourceType;
import com.bean.ResumeSources;
import com.bean.Timezone;
import com.bean.User;
import com.bo.ApplicantBO;
import com.bo.ApplicantTXBO;
import com.bo.BOFactory;
import com.bo.JobRequistionBO;
import com.bo.RefBO;
import com.common.Common;
import com.dao.RefferalDAO;
import com.form.ApplicantForm;
import com.form.VariableDataCaptureUtil;
import com.resources.Constant;
import com.util.DateUtil;
import com.util.EncryptDecrypt;
import com.util.StringUtils;
import java.io.File;
import java.io.RandomAccessFile;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

public class RefferalUserAction
  extends CommonNoLoginAction
{
  protected static final Logger logger = Logger.getLogger(RefferalUserAction.class);
  
  public ActionForward refferaluserdetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside refferaluserdetails method ..");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantForm applicantform = (ApplicantForm)form;
    

    String useremail = request.getParameter("useremail");
    String refuserid = request.getParameter("refuserid");
    String jobreqid = request.getParameter("jobreqid");
    
    String ensuperUserKey = request.getParameter("superUserKey");
    if (StringUtils.isNullOrEmpty(ensuperUserKey)) {
      ensuperUserKey = (String)request.getSession().getAttribute("superUserKey");
    }
    request.getSession().setAttribute("superUserKey", ensuperUserKey);
    String superUserKey = EncryptDecrypt.decrypt(ensuperUserKey);
    


    String source = request.getParameter("source");
    String from = request.getParameter("from");
    String locale_code = request.getParameter("locale_code");
    String uuid = request.getParameter("secureid");
    logger.info("jobreqid value.." + jobreqid);
    


    String currecncycode = "";
    if ((!StringUtils.isNullOrEmpty(from)) && (from.equals("preapplicant")))
    {
      String applicantId = request.getParameter("applicantId");
      applicantId = EncryptDecrypt.decrypt(applicantId);
      jobreqid = EncryptDecrypt.decrypt(jobreqid);
      
      JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetails(applicantId);
      applicantform.setResumeHeader(applicant.getResumeHeader());
      applicantform.setNote(applicant.getNote());
      applicantform.setApplicantId(applicant.getApplicantId());
      if ((applicant != null) && (applicant.getInterviewState() != null) && (applicant.getInterviewState().equals("Pre Application Submitted")))
      {
        applicantform.setResumename(applicant.getResumename());
        try
        {
          List attachlist = BOFactory.getApplicantBO().getApplicantAttachments(applicant.getApplicantId(), applicant.getUuid(), Common.APPLICANT_ATTACHMENT_TYPE_RESUME);
          if ((attachlist != null) && (attachlist.size() > 0)) {
            applicant.setApplicantAttachment((ApplicantAttachments)attachlist.get(0));
          }
          if ((applicant.getApplicantAttachment() != null) && (applicant.getApplicantAttachment().getAttachmentdata() != null) && (applicant.getResumename().length() > 0))
          {
            String filePath = Constant.getValue("ATTACHMENT_PATH");
            Blob blob = applicant.getApplicantAttachment().getAttachmentdata();
            File file = new File(filePath);
            if (!file.exists()) {
              file.mkdirs();
            }
            RandomAccessFile raf = new RandomAccessFile(filePath + applicant.getResumename(), "rw");
            
            int length = (int)blob.length();
            byte[] _blob = blob.getBytes(1L, length);
            raf.write(_blob);
            raf.close();
            
            filePath = applicant.getResumename();
            request.setAttribute("filePath", filePath);
            applicantform.setFilePath(filePath);
          }
        }
        catch (Exception e)
        {
          logger.info("error on attachment" + e);
        }
      }
      else
      {
        request.setAttribute("applicant_proccessed", "yes");
      }
      applicantform.setEmail(applicant.getEmail());
      applicantform.setApplicantId(applicant.getApplicantId());
      applicantform.setUuid(applicant.getUuid());
      applicantform.setFullName(applicant.getFullName());
      
      request.getSession().setAttribute("source", source);
      request.getSession().setAttribute("locale_code", locale_code);
      request.getSession().setAttribute("jobreqid", jobreqid);
      JobRequisition jb = BOFactory.getJobRequistionBO().getActiveOpenJobRequision(jobreqid);
      request.getSession().setAttribute("jobtitle", jb.getJobTitle());
      if (jb != null)
      {
        applicantform.setJobTitle(jb.getJobTitle());
        applicantform.setRequitionId(jb.getJobreqId());
        

        currecncycode = jb.getOrganization().getCurrencyCode();
        logger.info("currecncycode from requistion.." + currecncycode);
        applicantform.setExpectedctccurrencycode(currecncycode);
        applicantform.setCurrectctccurrencycode(currecncycode);
      }
      else
      {
        request.setAttribute("requistionclosed", "yes");
      }
    }
    else if ((!StringUtils.isNullOrEmpty(source)) && (!source.equals("null")) && (from != null) && (from.equals("applicantexternal")))
    {
      request.getSession().setAttribute("source", source);
      request.getSession().setAttribute("locale_code", locale_code);
      request.getSession().setAttribute("jobreqid", jobreqid);
      JobRequisition jb = BOFactory.getJobRequistionBO().getActiveOpenJobRequision(jobreqid, uuid);
      request.getSession().setAttribute("jobtitle", jb.getJobTitle());
      if (jb != null)
      {
        superUserKey = String.valueOf(jb.getSuper_user_key());
        applicantform.setJobTitle(jb.getJobTitle());
        applicantform.setRequitionId(jb.getJobreqId());
        

        currecncycode = jb.getOrganization().getCurrencyCode();
        logger.info("currecncycode from requistion.." + currecncycode);
        applicantform.setExpectedctccurrencycode(currecncycode);
        applicantform.setCurrectctccurrencycode(currecncycode);
      }
      else
      {
        request.setAttribute("requistionclosed", "yes");
      }
      applicantform.setScreenCode("APPLICANT_SCREEN_EXTERNAL");
    }
    else
    {
      JobApplicant applicant = null;
      if ((!StringUtils.isNullOrEmpty(from)) && (from.equals("pretalentpool")))
      {
        String applicantId = request.getParameter("applicantId");
        applicantId = EncryptDecrypt.decrypt(applicantId.trim());
        logger.info("inside..pretalentpool applicantId" + applicantId);
        applicant = BOFactory.getApplicantBO().getApplicantDetails(applicantId);
        applicantform.setResumeHeader(applicant.getResumeHeader());
        applicantform.setNote(applicant.getNote());
        applicantform.setApplicantId(applicant.getApplicantId());
        applicantform.setExpectedctccurrencycode(applicant.getExpectedctccurrencycode());
        applicantform.setCurrectctccurrencycode(applicant.getCurrectctccurrencycode());
        applicantform.setEmail(applicant.getEmail());
        request.getSession().setAttribute("source", source);
        request.getSession().setAttribute("locale_code", locale_code);
      }
      else
      {
        useremail = EncryptDecrypt.decrypt(useremail);
        refuserid = EncryptDecrypt.decrypt(refuserid);
        
        RefferalEmployee emp = RefBO.getRefferalEmployee(refuserid);
        currecncycode = emp.getOrganization().getCurrencyCode();
        logger.info("currecncycode .." + currecncycode);
        applicantform.setExpectedctccurrencycode(currecncycode);
        applicantform.setCurrectctccurrencycode(currecncycode);
        if (StringUtils.isNullOrEmpty(jobreqid)) {
          applicantform.setScreenCode("APPLICANT_SCREEN_TALENTPOOL_EXTERNAL");
        }
        applicant = BOFactory.getApplicantBO().getApplicantDetailsByEmailId(useremail);
        if (applicant != null) {
          applicantform.setApplicantId(applicant.getApplicantId());
        }
        applicantform.setEmail(useremail);
      }
      if ((applicant != null) && (applicant.getInterviewState() != null) && (applicant.getInterviewState().equals("Talent Pool Temp")))
      {
        applicantform.fromValue(applicant, request);
        request.setAttribute("applicantId", String.valueOf(applicant.getApplicantId()));
        request.setAttribute("uuid", applicant.getUuid());
      }
      if ((applicant != null) && (applicant.getInterviewState() != null) && (!applicant.getInterviewState().equals("Talent Pool Temp")))
      {
        request.setAttribute("applicant_proccessed", "yes");
        applicantform.fromValue(applicant, request);
      }
      if ((!StringUtils.isNullOrEmpty(jobreqid)) && (!jobreqid.equals("null")))
      {
        jobreqid = EncryptDecrypt.decrypt(jobreqid);
        request.getSession().setAttribute("jobreqid", jobreqid);
        JobRequisition jb = BOFactory.getJobRequistionBO().getActiveOpenJobRequision(jobreqid);
        request.getSession().setAttribute("jobtitle", jb.getJobTitle());
        if (jb != null)
        {
          applicantform.setJobTitle(jb.getJobTitle());
          applicantform.setRequitionId(jb.getJobreqId());
          

          currecncycode = jb.getOrganization().getCurrencyCode();
          logger.info("currecncycode from requistion.." + currecncycode);
          applicantform.setExpectedctccurrencycode(currecncycode);
          applicantform.setCurrectctccurrencycode(currecncycode);
        }
        else
        {
          request.setAttribute("requistionclosed", "yes");
        }
      }
      else
      {
        String reqtemp = (String)request.getSession().getAttribute("jobreqid");
        if (reqtemp != null) {
          request.getSession().removeAttribute("jobreqid");
        }
      }
    }
    logger.info("satyaaaaaaaaaaaaaaaaa form code" + applicantform.getScreenCode());
    BOFactory.getApplicantBO().commonLovPopulateOnCreate(applicantform, request, new Long(superUserKey).longValue());
    if ((!StringUtils.isNullOrEmpty(source)) && (!source.equals("null")))
    {
      request.setAttribute("source", source);
      request.setAttribute("locale_code", locale_code);
    }
    else
    {
      request.setAttribute("refuserid", refuserid);
    }
    request.setAttribute("applicantsaved", "no");
    request.setAttribute("saveAndNext", "0");
    request.setAttribute("backtodetails", "no");
    

    return mapping.findForward("refferaluserdetails");
  }
  
  public ActionForward saveapplicantData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveapplicantData method ..1");
    
    ApplicantForm applicantform = (ApplicantForm)form;
    
    JobApplicant applicant = new JobApplicant();
    String fromattachmentpage = request.getParameter("fromattachmentpage");
    String applicantId = request.getParameter("applicantId");
    String dateofbirth = request.getParameter("dateofbirth");
    String previousOrganization = request.getParameter("previousOrganization");
    String refuserid = request.getParameter("refuserid");
    String gender = request.getParameter("gender");
    String uuid = request.getParameter("uuid");
    String captcharan = request.getParameter("captcharan");
    boolean isduplicateApplicant = false;
    boolean isAttachmentSizeExceed = false;
    String source = request.getParameter("source");
    String locale_code = request.getParameter("locale_code");
    
    String ensuperUserKey = request.getParameter("superUserKey");
    if (StringUtils.isNullOrEmpty(ensuperUserKey)) {
      ensuperUserKey = (String)request.getSession().getAttribute("superUserKey");
    }
    String superUserKey = EncryptDecrypt.decrypt(ensuperUserKey);
    if (!StringUtils.isNullOrEmpty(superUserKey)) {
      applicant.setSuper_user_key(new Long(superUserKey).longValue());
    }
    Locale locale = null;
    if ((!StringUtils.isNullOrEmpty(source)) && (!source.equals("null")))
    {
      locale = new Locale();
      locale.setLocaleCode(locale_code);
      ResumeSources rc = BOFactory.getApplicantBO().getResumeSourcesByCode(source);
      
      ResumeSourceType resumesourcetype = new ResumeSourceType();
      resumesourcetype.setResumeSourceTypeId(rc.resumeSourceTypeId);
      applicant.setResumesourcetype(resumesourcetype);
      applicant.setResumeSourcesId(rc.resumeSourcesId);
      refuserid = "external-jobpost";
      applicant.setLocale_code(locale_code);
      applicantform.setSourceId(rc.resumeSourcesId);
      applicantform.setSourceTypeId(rc.resumeSourceTypeId);
      
      long reqid = applicantform.getRequitionId();
      String jobTitle = applicantform.getJobTitle();
      logger.info("reqidreqidreqidreqidreqidreqid" + reqid);
      logger.info("jobTitlejobTitlejobTitlejobTitle" + jobTitle);
      if (reqid == 0L)
      {
        String req_id = (String)request.getSession().getAttribute("jobreqid");
        if ((req_id != null) && (!req_id.equals("0")))
        {
          reqid = new Long(req_id).longValue();
          jobTitle = (String)request.getSession().getAttribute("jobtitle");
        }
      }
      applicantform.setRequitionId(reqid);
      applicantform.setJobTitle(jobTitle);
    }
    else
    {
      RefferalEmployee emp = RefBO.getRefferalEmployee(refuserid);
      locale = emp.getLocale();
    }
    if ((applicantId != null) && (!applicantId.equals("null")) && (uuid != null) && (!uuid.equals("null")))
    {
      applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    }
    else
    {
      applicant.setUuid(UUID.randomUUID().toString());
      applicant.setCreatedBy("external");
      applicant.setCreatedDate(new Date());
    }
    logger.info("refuserid : " + refuserid);
    logger.info("applicantId satya : " + applicantId);
    if ((!StringUtils.isNullOrEmpty(fromattachmentpage)) && (fromattachmentpage.equals("yes")))
    {
      if ((applicantId == null) || (applicantId.equals("null")) || (uuid == null) || (uuid.equals("null")))
      {
        String appid = (String)request.getSession().getAttribute("applicantId_refresh");
        logger.info("applicantId satya1: " + appid);
        String uuidapp = (String)request.getSession().getAttribute("uuid_refresh");
        if ((appid == null) || 
        

          (uuidapp != null)) {}
        logger.info("refuserid appid: " + appid);
        applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(appid, uuidapp);
      }
      long reqid = applicantform.getRequitionId();
      String jobTitle = applicantform.getJobTitle();
      logger.info("reqidreqidreqidreqidreqidreqid" + reqid);
      logger.info("jobTitlejobTitlejobTitlejobTitle" + jobTitle);
      if (reqid == 0L)
      {
        String req_id = (String)request.getSession().getAttribute("jobreqid");
        if ((req_id != null) && (!req_id.equals("0")))
        {
          reqid = new Long(req_id).longValue();
          jobTitle = (String)request.getSession().getAttribute("jobtitle");
        }
      }
      applicantform.fromValue(applicant, request);
      applicantform.setRequitionId(reqid);
      applicantform.setJobTitle(jobTitle);
      
      BOFactory.getApplicantBO().commonLovPopulate(request, applicantform, applicant, new Long(superUserKey).longValue());
      

      request.setAttribute("applicantsaved", "yes");
      request.setAttribute("saveAndNext", "1");
      if ((!StringUtils.isNullOrEmpty(source)) && (!source.equals("null")))
      {
        request.setAttribute("source", source);
        request.setAttribute("locale_code", locale_code);
      }
      else
      {
        request.setAttribute("refuserid", refuserid);
      }
      request.setAttribute("applicantId", String.valueOf(applicant.getApplicantId()));
      request.setAttribute("uuid", applicant.getUuid());
      

      return mapping.findForward("refferaluserdetails");
    }
    applicantform.toValue(applicant, request);
    String attachmentsizeexceed = (String)request.getAttribute("attachmentsizeexceed");
    if ((!StringUtils.isNullOrEmpty(attachmentsizeexceed)) && (attachmentsizeexceed.equals("yes")))
    {
      request.setAttribute("attachmentsizeexceed", "yes");
      isAttachmentSizeExceed = true;
    }
    List formVariablesList = applicantform.getFormVariablesList();
    List formVariableDataList = applicantform.getFormVariableDataList();
    if ((!StringUtils.isNullOrEmpty(source)) && (!source.equals("null")))
    {
      long reqid = applicantform.getRequitionId();
      String jobTitle = applicantform.getJobTitle();
      logger.info("reqidreqidreqidreqidreqidreqid" + reqid);
      logger.info("jobTitlejobTitlejobTitlejobTitle" + jobTitle);
      if (reqid == 0L)
      {
        String req_id = (String)request.getSession().getAttribute("jobreqid");
        if ((req_id != null) && (!req_id.equals("0")))
        {
          reqid = new Long(req_id).longValue();
          jobTitle = (String)request.getSession().getAttribute("jobtitle");
        }
      }
      applicant.setReqId(reqid);
      applicant.setJobTitle(jobTitle);
    }
    if (applicantform.getRequitionId() == 0L)
    {
      applicant.setStatus("T");
      applicant.setInterviewState("Talent Pool Temp");
    }
    if (!StringUtils.isNullOrEmpty(dateofbirth))
    {
      String datepattern = DateUtil.getDatePatternFormat(locale);
      Calendar cal = DateUtil.convertStringDateToCalendar(dateofbirth, datepattern);
      
      applicant.setDateofbirth(cal.getTime());
    }
    applicant.setGender(gender);
    applicant.setPreviousOrganization(previousOrganization);
    
    boolean isSuccess = true;
    List errorList = new ArrayList();
    if ((applicantId != null) && (!applicantId.equals("null")) && (uuid != null) && (!uuid.equals("null")))
    {
      logger.info("updating applicant with id" + applicantId);
      logger.info("updating applicant status" + applicant.getInterviewState());
      









      logger.info("applicant.getLocale_code()" + applicant.getLocale_code());
      if ((!StringUtils.isNullOrEmpty(source)) && (!source.equals("null"))) {
        applicant.setLocale_code(locale_code);
      }
      applicant.setUpdatedBy(applicantform.getEmail());
      applicant.setIsemailrequiretosend(false);
      


      logger.info("inside satya dasaaaaaaaaaaaaaaaaaaaaaaaaaaa" + applicant.getReqId());
      if (applicant.getReqId() == 0L)
      {
        applicant.setStatus("T");
        applicant.setInterviewState("Talent Pool Temp");
      }
      applicant.setCaptchaValidationRequired(true);
      applicant.setCaptchaEnteredValue(captcharan);
      String randomcaptcha = (String)request.getSession().getAttribute("randomcaptcha");
      applicant.setCaptchaRandomValue(randomcaptcha);
      


      applicant.setSuper_user_key(new Long(superUserKey).longValue());
      JobApplicant oldapplicant = BOFactory.getApplicantBO().isApplicantDuplicate(applicant, new Long(superUserKey).longValue(), applicant.getApplicantId());
      if (oldapplicant != null)
      {
        request.setAttribute("oldapplicant", oldapplicant);
        isduplicateApplicant = true;
      }
      else if (!isAttachmentSizeExceed)
      {
        applicant = BOFactory.getApplicantBO().updateApplicant(applicant, null, null, null, null, refuserid, errorList, applicantform.getErrorListCustomVariable(), applicantform.getFormVariableDataList(), true);
      }
      errorList = applicant.getErrorList();
    }
    else
    {
      logger.info("applicant.getLocale_code()" + applicant.getLocale_code());
      if ((!StringUtils.isNullOrEmpty(source)) && (!source.equals("null"))) {
        applicant.setLocale_code(locale_code);
      }
      applicant.setCreatedBy(applicantform.getEmail());
      applicant.setIsemailrequiretosend(false);
      logger.info("refuserid" + refuserid);
      logger.info("applicantform.getRequitionId()" + applicantform.getRequitionId());
      logger.info("applicantform.getJobTitle()()" + applicantform.getJobTitle());
      if (applicantform.getRequitionId() > 0L)
      {
        applicant.setJobTitle(applicantform.getJobTitle());
        applicant.setReqId(applicantform.getRequitionId());
      }
      applicant.setCaptchaValidationRequired(true);
      applicant.setCaptchaEnteredValue(captcharan);
      String randomcaptcha = (String)request.getSession().getAttribute("randomcaptcha");
      applicant.setCaptchaRandomValue(randomcaptcha);
      


      applicant.setSuper_user_key(new Long(superUserKey).longValue());
      JobApplicant oldapplicant = BOFactory.getApplicantBO().isApplicantDuplicate(applicant, new Long(superUserKey).longValue());
      if (oldapplicant != null)
      {
        request.setAttribute("oldapplicant", oldapplicant);
        isduplicateApplicant = true;
      }
      else if (!isAttachmentSizeExceed)
      {
        applicant = BOFactory.getApplicantBO().saveApplicant(applicant, null, null, null, refuserid, errorList, applicantform.getErrorListCustomVariable(), applicantform.getFormVariableDataList());
      }
      errorList = applicant.getErrorList();
    }
    if ((errorList != null) && (errorList.size() > 0)) {
      isSuccess = false;
    }
    if ((isSuccess) && (!isduplicateApplicant) && (!isAttachmentSizeExceed))
    {
      request.setAttribute("applicantdatacustom", "yes");
      request.setAttribute("applicantsaved", "yes");
      request.setAttribute("saveAndNext", "1");
      if ((!StringUtils.isNullOrEmpty(source)) && (!source.equals("null")))
      {
        request.setAttribute("source", source);
        request.setAttribute("locale_code", locale_code);
      }
      else
      {
        request.setAttribute("refuserid", refuserid);
      }
      request.setAttribute("applicantId", String.valueOf(applicant.getApplicantId()));
      request.setAttribute("uuid", applicant.getUuid());
    }
    else
    {
      logger.info("in error condition");
      request.setAttribute("error_list", errorList);
      if ((!StringUtils.isNullOrEmpty(source)) && (!source.equals("null")))
      {
        request.setAttribute("source", source);
        request.setAttribute("locale_code", locale_code);
      }
      else
      {
        request.setAttribute("refuserid", refuserid);
      }
      request.setAttribute("applicantsaved", "no");
      request.setAttribute("saveAndNext", "0");
      request.setAttribute("backtodetails", "no");
    }
    BOFactory.getApplicantBO().commonLovPopulate(request, applicantform, applicant, new Long(superUserKey).longValue());
    if (!isSuccess) {
      applicantform.setFormVariablesList(VariableDataCaptureUtil.mergeCustomVariableValues(applicantform.getFormVariablesList(), formVariableDataList, request));
    }
    applicantform.setApplicantId(applicant.getApplicantId());
    
    long reqid = applicantform.getRequitionId();
    String jobTitle = applicantform.getJobTitle();
    logger.info("reqidreqidreqidreqidreqidreqid" + reqid);
    logger.info("jobTitlejobTitlejobTitlejobTitle" + jobTitle);
    if (reqid == 0L)
    {
      String req_id = (String)request.getSession().getAttribute("jobreqid");
      if ((req_id != null) && (!req_id.equals("0")))
      {
        reqid = new Long(req_id).longValue();
        jobTitle = (String)request.getSession().getAttribute("jobtitle");
      }
    }
    applicantform.fromValue(applicant, request);
    applicantform.setRequitionId(reqid);
    applicantform.setJobTitle(jobTitle);
    



    logger.info("applicantId satya2: " + applicant.getApplicantId());
    request.getSession().setAttribute("applicantId_refresh", String.valueOf(applicant.getApplicantId()));
    request.getSession().setAttribute("uuid_refresh", applicant.getUuid());
    

    applicant.setJobTitle(applicantform.getJobTitle());
    applicant.setReqId(applicantform.getRequitionId());
    
    applicant = BOFactory.getApplicantTXBO().acceptandsubmitRefUserData(applicant, Common.APPLICANT_CREATED_BY_TYPE_REFERRAL, refuserid);
    applicantform.setFullName(applicant.getFullName());
    applicantform.setApplicantId(applicant.getApplicantId());
    applicantform.setApplicant_number(applicant.getApplicant_number());
    logger.info("Inside acceptandsubmit method ..refuserid" + refuserid);
    
    User userContext = new User();
    if (!StringUtils.isNullOrEmpty(refuserid)) {
      if (refuserid.equals("external-jobpost"))
      {
        logger.info("Inside acceptandsubmit method ..locale_code" + locale_code);
        Locale locale1 = new Locale();
        locale1.setLocaleCode(locale_code);
        userContext.setLocale(locale1);
        Timezone tz = new Timezone();
        tz.setTimezoneCode(TimeZone.getDefault().getID());
        userContext.setTimezone(tz);
      }
      else
      {
        RefferalEmployee refEmp = RefBO.getRefferalEmployee(refuserid);
        
        userContext.setLocale(refEmp.getLocale());
        userContext.setTimezone(refEmp.getTimezone());
      }
    }
    if ((!StringUtils.isNullOrEmpty(Constant.getValue("applicant.submit.email.send"))) && (Constant.getValue("applicant.submit.email.send").equals("yes")))
    {
      String[] to = new String[1];
      to[0] = applicant.getEmail();
      if (applicant.isIsemailrequiretosend()) {
        BOFactory.getApplicantBO().sendApplicationSubmitionEmail(applicant, userContext, null);
      }
    }
    return mapping.findForward("acceptandsubmit");
  }
  
  public ActionForward acceptandsubmit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside acceptandsubmit method ..1");
    
    ApplicantForm applicantform = (ApplicantForm)form;
    JobApplicant applicant = null;
    
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("uuid");
    String refuserid = request.getParameter("refuserid");
    if ((applicantId != null) && (uuid != null)) {
      applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    }
    applicant.setInterviewState("Talent Pool");
    
    logger.info("applicantform.getRequitionId()" + applicantform.getRequitionId());
    applicant.setJobTitle(applicantform.getJobTitle());
    applicant.setReqId(applicantform.getRequitionId());
    
    applicant = BOFactory.getApplicantTXBO().acceptandsubmitRefUserData(applicant, Common.APPLICANT_CREATED_BY_TYPE_REFERRAL, refuserid);
    applicantform.setFullName(applicant.getFullName());
    applicantform.setApplicantId(applicant.getApplicantId());
    applicantform.setApplicant_number(applicant.getApplicant_number());
    logger.info("Inside acceptandsubmit method ..refuserid" + refuserid);
    
    User userContext = new User();
    if (!StringUtils.isNullOrEmpty(refuserid)) {
      if (refuserid.equals("external-jobpost"))
      {
        String locale_code = request.getParameter("locale_code");
        logger.info("Inside acceptandsubmit method ..locale_code" + locale_code);
        Locale locale = new Locale();
        locale.setLocaleCode(locale_code);
        userContext.setLocale(locale);
        Timezone tz = new Timezone();
        tz.setTimezoneCode(TimeZone.getDefault().getID());
        userContext.setTimezone(tz);
      }
      else
      {
        RefferalEmployee refEmp = RefBO.getRefferalEmployee(refuserid);
        
        userContext.setLocale(refEmp.getLocale());
        userContext.setTimezone(refEmp.getTimezone());
      }
    }
    if ((!StringUtils.isNullOrEmpty(Constant.getValue("applicant.submit.email.send"))) && (Constant.getValue("applicant.submit.email.send").equals("yes")))
    {
      String[] to = new String[1];
      to[0] = applicant.getEmail();
      if (applicant.isIsemailrequiretosend()) {
        BOFactory.getApplicantBO().sendApplicationSubmitionEmail(applicant, userContext, null);
      }
    }
    return mapping.findForward("acceptandsubmit");
  }
  
  public ActionForward updateapplicantData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updateapplicantData method ..1");
    
    ApplicantForm applicantform = (ApplicantForm)form;
    

    JobApplicant applicant = new JobApplicant();
    
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("uuid");
    String fromattachmentpage = request.getParameter("fromattachmentpage");
    
    String dateofbirth = request.getParameter("dateofbirth");
    String previousOrganization = request.getParameter("previousOrganization");
    String refuserid = request.getParameter("refuserid");
    String captcharan = request.getParameter("captcharan");
    
    String source = request.getParameter("source");
    String locale_code = request.getParameter("locale_code");
    
    String ensuperUserKey = request.getParameter("superUserKey");
    if (StringUtils.isNullOrEmpty(ensuperUserKey)) {
      ensuperUserKey = (String)request.getSession().getAttribute("superUserKey");
    }
    String superUserKey = EncryptDecrypt.decrypt(ensuperUserKey);
    if (!StringUtils.isNullOrEmpty(superUserKey)) {
      applicant.setSuper_user_key(new Long(superUserKey).longValue());
    }
    if ((!StringUtils.isNullOrEmpty(source)) && (!source.equals("null"))) {
      refuserid = "external-jobpost";
    }
    logger.info("refuserid : " + refuserid);
    boolean isAttachmentSizeExceed = false;
    if ((applicantId != null) && (uuid != null)) {
      applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    }
    if ((!StringUtils.isNullOrEmpty(fromattachmentpage)) && (fromattachmentpage.equals("yes")))
    {
      applicantform.fromValue(applicant, request);
      
      BOFactory.getApplicantBO().commonLovPopulate(request, applicantform, applicant, new Long(superUserKey).longValue());
      

      request.setAttribute("applicantsaved", "yes");
      request.setAttribute("saveAndNext", "1");
      request.setAttribute("refuserid", refuserid);
      

      request.setAttribute("applicantId", String.valueOf(applicant.getApplicantId()));
      request.setAttribute("uuid", applicant.getUuid());
      

      return mapping.findForward("refferaluserdetails");
    }
    applicantform.toValue(applicant, request);
    String attachmentsizeexceed = (String)request.getAttribute("attachmentsizeexceed");
    if ((!StringUtils.isNullOrEmpty(attachmentsizeexceed)) && (attachmentsizeexceed.equals("yes")))
    {
      request.setAttribute("attachmentsizeexceed", "yes");
      isAttachmentSizeExceed = true;
    }
    List formVariablesList = applicantform.getFormVariablesList();
    List formVariableDataList = applicantform.getFormVariableDataList();
    
    String gender = request.getParameter("gender");
    logger.info("gender : " + gender);
    applicant.setGender(gender);
    if (!StringUtils.isNullOrEmpty(dateofbirth))
    {
      String datepattern = Constant.getValue("defaultdateformat");
      Calendar cal = DateUtil.convertStringDateToCalendar(dateofbirth, datepattern);
      
      applicant.setDateofbirth(cal.getTime());
    }
    applicant.setPreviousOrganization(previousOrganization);
    

    applicant.setCurrectctc(applicantform.getCurrectctc());
    applicant.setExpectedctc(applicantform.getExpectedctc());
    

    logger.info("Inside saveapplicantData method .. 5");
    


    logger.info("applicant.getLocale_code()" + applicant.getLocale_code());
    if ((!StringUtils.isNullOrEmpty(source)) && (!source.equals("null"))) {
      applicant.setLocale_code(locale_code);
    }
    boolean isSuccess = true;
    List errorList = new ArrayList();
    applicant.setCaptchaValidationRequired(true);
    applicant.setCaptchaEnteredValue(captcharan);
    String randomcaptcha = (String)request.getSession().getAttribute("randomcaptcha");
    applicant.setCaptchaRandomValue(randomcaptcha);
    if (!isAttachmentSizeExceed) {
      applicant = BOFactory.getApplicantBO().updateApplicant(applicant, null, null, null, null, refuserid, errorList, applicantform.getErrorListCustomVariable(), applicantform.getFormVariableDataList(), true);
    }
    errorList = applicant.getErrorList();
    if ((errorList != null) && (errorList.size() > 0)) {
      isSuccess = false;
    }
    if (isSuccess)
    {
      request.setAttribute("applicantupdated", "yes");
      request.setAttribute("applicantdatacustom", "yes");
    }
    else
    {
      logger.info("in error condition");
      request.setAttribute("error_list", errorList);
    }
    long reqid = applicantform.getRequitionId();
    String jobTitle = applicantform.getJobTitle();
    logger.info("reqidreqidreqidreqidreqidreqid" + reqid);
    logger.info("jobTitlejobTitlejobTitlejobTitle" + jobTitle);
    if (reqid == 0L)
    {
      String req_id = (String)request.getSession().getAttribute("jobreqid");
      if ((req_id != null) && (!req_id.equals("0")))
      {
        reqid = new Long(req_id).longValue();
        jobTitle = (String)request.getSession().getAttribute("jobtitle");
      }
    }
    applicantform.fromValue(applicant, request);
    applicantform.setRequitionId(reqid);
    applicantform.setJobTitle(jobTitle);
    
    BOFactory.getApplicantBO().commonLovPopulate(request, applicantform, applicant, new Long(superUserKey).longValue());
    if (!isSuccess) {
      applicantform.setFormVariablesList(VariableDataCaptureUtil.mergeCustomVariableValues(applicantform.getFormVariablesList(), formVariableDataList, request));
    }
    request.setAttribute("applicantsaved", "yes");
    if ((isSuccess) && (!isAttachmentSizeExceed)) {
      request.setAttribute("saveAndNext", "1");
    } else {
      request.setAttribute("saveAndNext", "0");
    }
    request.setAttribute("refuserid", refuserid);
    

    request.setAttribute("applicantId", String.valueOf(applicant.getApplicantId()));
    request.setAttribute("uuid", applicant.getUuid());
    

    return mapping.findForward("refferaluserdetails");
  }
  
  public ActionForward nexttoEducationtab(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside nexttoEducationtab method ..");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    String uuid = request.getParameter("uuid");
    String appId = request.getParameter("applicantId");
    String refuserid = request.getParameter("refuserid");
    logger.info(" refuserid .. : " + refuserid);
    logger.info(" aplicantId .. : " + appId);
    logger.info(" uuid .. : " + uuid);
    String source = request.getParameter("source");
    String locale_code = request.getParameter("locale_code");
    
    String ensuperUserKey = request.getParameter("superUserKey");
    if (StringUtils.isNullOrEmpty(ensuperUserKey)) {
      ensuperUserKey = (String)request.getSession().getAttribute("superUserKey");
    }
    String superUserKey = EncryptDecrypt.decrypt(ensuperUserKey);
    if ((!StringUtils.isNullOrEmpty(source)) && (!source.equals("null"))) {
      refuserid = "external-jobpost";
    }
    ApplicantForm applicantform = (ApplicantForm)form;
    
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(appId, uuid);
    
    List attachlist = BOFactory.getApplicantBO().getApplicantAttachments(applicant.getApplicantId(), applicant.getUuid(), Common.APPLICANT_ATTACHMENT_TYPE_RESUME);
    if ((attachlist != null) && (attachlist.size() > 0)) {
      applicant.setApplicantAttachment((ApplicantAttachments)attachlist.get(0));
    }
    if ((applicant.getApplicantAttachment() != null) && (applicant.getApplicantAttachment().getAttachmentdata() != null) && (applicant.getResumename().length() > 0))
    {
      String filePath = Constant.getValue("ATTACHMENT_PATH");
      Blob blob = applicant.getApplicantAttachment().getAttachmentdata();
      File file = new File(filePath);
      if (!file.exists()) {
        file.mkdirs();
      }
      RandomAccessFile raf = new RandomAccessFile(filePath + applicant.getResumename(), "rw");
      
      int length = (int)blob.length();
      byte[] _blob = blob.getBytes(1L, length);
      raf.write(_blob);
      raf.close();
      
      filePath = applicant.getResumename();
      request.setAttribute("filePath", filePath);
    }
    long reqid = applicantform.getRequitionId();
    String jobTitle = applicantform.getJobTitle();
    logger.info("reqidreqidreqidreqidreqidreqid" + reqid);
    logger.info("jobTitlejobTitlejobTitlejobTitle" + jobTitle);
    if (reqid == 0L)
    {
      String req_id = (String)request.getSession().getAttribute("jobreqid");
      if ((req_id != null) && (!req_id.equals("0")))
      {
        reqid = new Long(req_id).longValue();
        jobTitle = (String)request.getSession().getAttribute("jobtitle");
      }
    }
    applicantform.fromValue(applicant, request);
    applicantform.setRequitionId(reqid);
    applicantform.setJobTitle(jobTitle);
    BOFactory.getApplicantBO().commonLovPopulate(request, applicantform, applicant, new Long(superUserKey).longValue());
    
    request.setAttribute("applicantId", String.valueOf(applicant.getApplicantId()));
    request.setAttribute("uuid", applicant.getUuid());
    request.setAttribute("refuserid", refuserid);
    
    request.setAttribute("saveAndNext", "2");
    request.setAttribute("applicantsaved", "no");
    
    return mapping.findForward("refferaluserdetails");
  }
  
  public ActionForward backtoWorkExpSkillstab(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside nexttoEducationtab method ..");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    String uuid = request.getParameter("uuid");
    String appId = request.getParameter("applicantId");
    String refuserid = request.getParameter("refuserid");
    logger.info(" refuserid .. : " + refuserid);
    logger.info(" aplicantId .. : " + appId);
    logger.info(" uuid .. : " + uuid);
    ApplicantForm applicantform = (ApplicantForm)form;
    
    String source = request.getParameter("source");
    String locale_code = request.getParameter("locale_code");
    
    String ensuperUserKey = request.getParameter("superUserKey");
    if (StringUtils.isNullOrEmpty(ensuperUserKey)) {
      ensuperUserKey = (String)request.getSession().getAttribute("superUserKey");
    }
    String superUserKey = EncryptDecrypt.decrypt(ensuperUserKey);
    if ((!StringUtils.isNullOrEmpty(source)) && (!source.equals("null"))) {
      refuserid = "external-jobpost";
    }
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(appId, uuid);
    
    List attachlist = BOFactory.getApplicantBO().getApplicantAttachments(applicant.getApplicantId(), applicant.getUuid(), Common.APPLICANT_ATTACHMENT_TYPE_RESUME);
    if ((attachlist != null) && (attachlist.size() > 0)) {
      applicant.setApplicantAttachment((ApplicantAttachments)attachlist.get(0));
    }
    if ((applicant.getApplicantAttachment() != null) && (applicant.getApplicantAttachment().getAttachmentdata() != null) && (applicant.getResumename().length() > 0))
    {
      String filePath = Constant.getValue("ATTACHMENT_PATH");
      Blob blob = applicant.getApplicantAttachment().getAttachmentdata();
      File file = new File(filePath);
      if (!file.exists()) {
        file.mkdirs();
      }
      RandomAccessFile raf = new RandomAccessFile(filePath + applicant.getResumename(), "rw");
      
      int length = (int)blob.length();
      byte[] _blob = blob.getBytes(1L, length);
      raf.write(_blob);
      raf.close();
      
      filePath = applicant.getResumename();
      request.setAttribute("filePath", filePath);
    }
    long reqid = applicantform.getRequitionId();
    String jobTitle = applicantform.getJobTitle();
    logger.info("reqidreqidreqidreqidreqidreqid" + reqid);
    logger.info("jobTitlejobTitlejobTitlejobTitle" + jobTitle);
    if (reqid == 0L)
    {
      String req_id = (String)request.getSession().getAttribute("jobreqid");
      if ((req_id != null) && (!req_id.equals("0")))
      {
        reqid = new Long(req_id).longValue();
        jobTitle = (String)request.getSession().getAttribute("jobtitle");
      }
    }
    applicantform.fromValue(applicant, request);
    applicantform.setRequitionId(reqid);
    applicantform.setJobTitle(jobTitle);
    

    BOFactory.getApplicantBO().commonLovPopulate(request, applicantform, applicant, new Long(superUserKey).longValue());
    

    request.setAttribute("applicantId", String.valueOf(applicant.getApplicantId()));
    request.setAttribute("uuid", applicant.getUuid());
    request.setAttribute("refuserid", refuserid);
    
    request.setAttribute("saveAndNext", "1");
    request.setAttribute("applicantsaved", "no");
    
    return mapping.findForward("refferaluserdetails");
  }
  
  public ActionForward backtodetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside nexttoEducationtab method ..");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    String uuid = request.getParameter("uuid");
    String appId = request.getParameter("applicantId");
    String refuserid = request.getParameter("refuserid");
    logger.info(" refuserid .. : " + refuserid);
    logger.info(" aplicantId .. : " + appId);
    logger.info(" uuid .. : " + uuid);
    ApplicantForm applicantform = (ApplicantForm)form;
    
    String source = request.getParameter("source");
    String locale_code = request.getParameter("locale_code");
    
    String ensuperUserKey = request.getParameter("superUserKey");
    if (StringUtils.isNullOrEmpty(ensuperUserKey)) {
      ensuperUserKey = (String)request.getSession().getAttribute("superUserKey");
    }
    String superUserKey = EncryptDecrypt.decrypt(ensuperUserKey);
    if ((!StringUtils.isNullOrEmpty(source)) && (!source.equals("null"))) {
      refuserid = "external-jobpost";
    }
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(appId, uuid);
    
    List attachlist = BOFactory.getApplicantBO().getApplicantAttachments(applicant.getApplicantId(), applicant.getUuid(), Common.APPLICANT_ATTACHMENT_TYPE_RESUME);
    if ((attachlist != null) && (attachlist.size() > 0)) {
      applicant.setApplicantAttachment((ApplicantAttachments)attachlist.get(0));
    }
    if ((applicant.getApplicantAttachment() != null) && (applicant.getApplicantAttachment().getAttachmentdata() != null) && (applicant.getResumename().length() > 0))
    {
      String filePath = Constant.getValue("ATTACHMENT_PATH");
      Blob blob = applicant.getApplicantAttachment().getAttachmentdata();
      File file = new File(filePath);
      if (!file.exists()) {
        file.mkdirs();
      }
      RandomAccessFile raf = new RandomAccessFile(filePath + applicant.getResumename(), "rw");
      
      int length = (int)blob.length();
      byte[] _blob = blob.getBytes(1L, length);
      raf.write(_blob);
      raf.close();
      
      filePath = applicant.getResumename();
      request.setAttribute("filePath", filePath);
    }
    long reqid = applicantform.getRequitionId();
    String jobTitle = applicantform.getJobTitle();
    logger.info("reqidreqidreqidreqidreqidreqid" + reqid);
    logger.info("jobTitlejobTitlejobTitlejobTitle" + jobTitle);
    if (reqid == 0L)
    {
      String req_id = (String)request.getSession().getAttribute("jobreqid");
      if ((req_id != null) && (!req_id.equals("0")))
      {
        reqid = new Long(req_id).longValue();
        jobTitle = (String)request.getSession().getAttribute("jobtitle");
      }
    }
    applicantform.fromValue(applicant, request);
    applicantform.setRequitionId(reqid);
    applicantform.setJobTitle(jobTitle);
    

    BOFactory.getApplicantBO().commonLovPopulate(request, applicantform, applicant, new Long(superUserKey).longValue());
    

    request.setAttribute("applicantId", String.valueOf(applicant.getApplicantId()));
    request.setAttribute("uuid", applicant.getUuid());
    request.setAttribute("refuserid", refuserid);
    
    request.setAttribute("saveAndNext", "0");
    request.setAttribute("applicantsaved", "no");
    request.setAttribute("backtodetails", "yes");
    
    return mapping.findForward("refferaluserdetails");
  }
  
  public ActionForward savepreviousOrgdetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside savepreviousOrgdetails method .... 1");
    
    ApplicantForm applicantform = (ApplicantForm)form;
    String applicantid = request.getParameter("applicantid");
    String uuid = request.getParameter("uuid");
    String prevOrgName = request.getParameter("prevOrgName");
    String role = request.getParameter("role");
    String startDate = request.getParameter("startDate");
    String endDate = request.getParameter("endDate");
    String reasonforleave = request.getParameter("reasonforleave");
    String reportingto = request.getParameter("reportingto");
    logger.info("applicantid : " + applicantid);
    logger.info("uuid : " + uuid);
    
    JobApplicant user1 = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantid, uuid);
    
    PreviousOrgDetails prevorg = new PreviousOrgDetails();
    prevorg.setApplicantId(new Long(applicantid).longValue());
    prevorg.setPrevOrgName(prevOrgName);
    prevorg.setRole(role);
    prevorg.setStartdate(startDate);
    prevorg.setEnddate(endDate);
    prevorg.setReasonforleave(reasonforleave);
    prevorg.setReportingToName(reportingto);
    if (user1 != null) {
      prevorg.setCreatedBy(user1.getEmail());
    }
    prevorg.setCreatedDate(new Date());
    

    BOFactory.getApplicantBO().savePreviousOrgDetails(prevorg);
    

    return null;
  }
  
  public ActionForward getpreviousOrgdetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside getpreviousOrgdetails method ... 1 ");
    ApplicantForm applicantform = (ApplicantForm)form;
    String applicantid = request.getParameter("applicantid");
    String uuid = request.getParameter("uuid");
    String refuserid = request.getParameter("refuserid");
    
    List orgDetailsList = BOFactory.getApplicantBO().getPreviousOrgDetailsByApplicant(new Long(applicantid).longValue());
    applicantform.setPreviousOrgList(orgDetailsList);
    request.setAttribute("applicantid", applicantid);
    request.setAttribute("uuid", uuid);
    request.setAttribute("refuserid", refuserid);
    return mapping.findForward("getpreviousOrgdetails");
  }
  
  public ActionForward deletePrevOg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deletePrevOg method ....");
    
    ApplicantForm applicantform = (ApplicantForm)form;
    String prevorgid = request.getParameter("prevorgid");
    
    BOFactory.getApplicantBO().deletePreviousOrg(new Long(prevorgid).longValue());
    return null;
  }
  
  public ActionForward actionattachmentaddscr(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside actionattachmentaddscr method");
    String applicantId = request.getParameter("applicantId");
    String refuserid = request.getParameter("refuserid");
    logger.info("refuserid : " + refuserid);
    
    String appuuid = request.getParameter("secureid");
    logger.info("*** uuid ..." + appuuid);
    String idvalue = request.getParameter("idvalue");
    String actionname = request.getParameter("action");
    
    ApplicantForm applicantform = (ApplicantForm)form;
    
    List attachmentList = BOFactory.getApplicantBO().getActionAttachmentList(new Long(idvalue).longValue(), actionname);
    applicantform.setActionAttachmentList(attachmentList);
    request.setAttribute("action_name", actionname);
    request.setAttribute("idvalue", idvalue);
    request.setAttribute("refuserid", refuserid);
    request.setAttribute("uuid", appuuid);
    return mapping.findForward("actionattachmentaddscr");
  }
  
  public ActionForward actionattachmentadd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside actionattachmentadd method");
    String applicantId = request.getParameter("applicantId");
    String filedetails = request.getParameter("filedetails");
    String appuuid = request.getParameter("secureid");
    String uuid = request.getParameter("uuid");
    logger.info("appuuid : " + appuuid);
    String idvalue = request.getParameter("idvalue");
    String actionname = request.getParameter("action");
    String refuserid = request.getParameter("refuserid");
    RefferalEmployee refEmp = RefferalDAO.getRefferalEmployee(refuserid);
    
    ActionErrors errors = new ActionErrors();
    
    ApplicantForm applicantform = (ApplicantForm)form;
    if ((StringUtils.isNullOrEmpty(appuuid)) || (appuuid.equals("null"))) {
      appuuid = uuid;
    }
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, appuuid);
    
    FormFile myFile = applicantform.getAttachmentdata();
    if (myFile != null)
    {
      String contentType = myFile.getContentType();
      String fileName = myFile.getFileName();
      int fileSize = myFile.getFileSize();
      byte[] fileData = myFile.getFileData();
      if (!StringUtils.isNullOrEmpty(fileName))
      {
        ActionsAttachment appattach = new ActionsAttachment();
        
        appattach.setAttahmentname(fileName);
        Blob blob = null;
        
        blob = new SerialBlob(fileData);
        

        appattach.setIdvalue(new Long(idvalue).longValue());
        appattach.setAction(actionname);
        appattach.setAttahmentdetails(filedetails);
        appattach.setApplicantId(new Long(applicantId).longValue());
        if (refEmp != null) {
          appattach.setCreatedBy(refEmp.getEmployeename());
        } else {
          appattach.setCreatedBy(applicant.getFullName());
        }
        appattach.setCreatedDate(new Date());
        appattach.setUuid(UUID.randomUUID().toString());
        appattach.setAppuuid(appuuid);
        
        BOFactory.getApplicantBO().saveActionAttachment(appattach);
        
        String filePath = Constant.getValue("ATTACHMENT_PATH");
        filePath = filePath + File.separator + "actionAttachment" + File.separator + appattach.getUuid() + File.separator;
        File file = new File(filePath);
        if (!file.exists()) {
          file.mkdirs();
        }
        RandomAccessFile raf = new RandomAccessFile(filePath + fileName, "rw");
        

        int length = (int)blob.length();
        byte[] _blob = blob.getBytes(1L, length);
        raf.write(_blob);
        raf.close();
      }
    }
    List attachmentList = BOFactory.getApplicantBO().getActionAttachmentList(new Long(idvalue).longValue(), actionname);
    applicantform.setActionAttachmentList(attachmentList);
    applicantform.setApplicantId(new Long(applicantId).longValue());
    applicantform.setUuid(appuuid);
    request.setAttribute("action_name", actionname);
    request.setAttribute("idvalue", idvalue);
    request.setAttribute("refuserid", refuserid);
    request.setAttribute("uuid", uuid);
    request.setAttribute("applicantId", applicantId);
    
    return mapping.findForward("actionattachmentaddscr");
  }
  
  public ActionForward deleteattachmentpopup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteattachmentpopup method");
    String applicantId = request.getParameter("applicantId");
    String idvalue = request.getParameter("idvalue");
    String actioname = request.getParameter("actionname");
    String attachmentuuid = request.getParameter("uuid");
    String refuserid = request.getParameter("refuserid");
    ApplicantForm applicantform = (ApplicantForm)form;
    
    BOFactory.getApplicantBO().deleteActionAttachment(attachmentuuid);
    List attachmentList = BOFactory.getApplicantBO().getActionAttachmentList(new Long(idvalue).longValue(), actioname);
    applicantform.setActionAttachmentList(attachmentList);
    request.setAttribute("action_name", actioname);
    request.setAttribute("idvalue", idvalue);
    request.setAttribute("refuserid", refuserid);
    return mapping.findForward("actionattachmentaddscr");
  }
  
  public ActionForward deleteattachment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteattachment method");
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    String actioname = request.getParameter("actionname");
    String attachmentuuid = request.getParameter("uuid");
    String refuserid = request.getParameter("refuserid");
    ApplicantForm applicantform = (ApplicantForm)form;
    
    BOFactory.getApplicantBO().deleteActionAttachment(attachmentuuid);
    
    request.setAttribute("attachmentdeleted", "yes");
    request.setAttribute("refuserid", refuserid);
    return mapping.findForward("deleteattachment");
  }
  
  public ActionForward saveapplicantSkills(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveapplicantSkills method");
    
    ApplicantForm applicantform = (ApplicantForm)form;
    String applicantid = request.getParameter("applicantid");
    String uuid = request.getParameter("uuid");
    logger.info("uuid : " + uuid);
    JobApplicant user1 = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantid, uuid);
    
    String skillname = request.getParameter("skillname");
    String yearsofexpskill = request.getParameter("yearsofexpskill");
    String ratingskill = request.getParameter("ratingskill");
    

    ApplicantSkills edu = new ApplicantSkills();
    edu.setApplicantId(new Long(applicantid).longValue());
    edu.setSkillname(skillname);
    edu.setYearsofexp(yearsofexpskill);
    edu.setRating(ratingskill);
    if (user1 != null) {
      edu.setCreatedBy(user1.getFullName());
    }
    edu.setCreatedDate(new Date());
    BOFactory.getApplicantBO().saveSkillsDetails(edu);
    

    return null;
  }
  
  public ActionForward getApplicantSkills(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside getApplicantSkills method");
    ApplicantForm applicantform = (ApplicantForm)form;
    String applicantid = request.getParameter("applicantid");
    String refuserid = request.getParameter("refuserid");
    

    List skillsList = BOFactory.getApplicantBO().getSkillsByApplicant(new Long(applicantid).longValue());
    applicantform.setApplicantSkillList(skillsList);
    request.setAttribute("refuserid", refuserid);
    return mapping.findForward("getApplicantSkills");
  }
  
  public ActionForward deleteSkill(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteSkill method");
    
    ApplicantForm applicantform = (ApplicantForm)form;
    String skillid = request.getParameter("skillid");
    BOFactory.getApplicantBO().deleteSkill(new Long(skillid).longValue());
    return null;
  }
  
  public ActionForward saveeducationdetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveeducationdetails method");
    

    ApplicantForm applicantform = (ApplicantForm)form;
    String applicantid = request.getParameter("applicantid");
    String uuid = request.getParameter("uuid");
    String refuserid = request.getParameter("refuserid");
    String type = request.getParameter("type");
    String educationName = request.getParameter("educationName");
    String specialization = request.getParameter("specialization");
    String institute = request.getParameter("institute");
    String percentile = request.getParameter("percentile");
    String passingyear = request.getParameter("passingyear");
    String startingYear = request.getParameter("startingYear");
    
    RefferalEmployee user1 = RefferalDAO.getRefferalEmployee(refuserid);
    EducationDetails edu = new EducationDetails();
    edu.setApplicantId(new Long(applicantid).longValue());
    edu.setEducationName(educationName);
    edu.setSpecialization(specialization);
    edu.setInstituteName(institute);
    edu.setPercentile(percentile);
    edu.setPassingYear(passingyear);
    edu.setStartingYear(startingYear);
    edu.setEduType(type);
    if (user1 != null) {
      edu.setCreatedBy(user1.getEmployeeemail());
    }
    edu.setCreatedDate(new Date());
    BOFactory.getApplicantBO().saveEducationDetails(edu);
    


    return null;
  }
  
  public ActionForward geteducationdetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside geteducationdetails method");
    ApplicantForm applicantform = (ApplicantForm)form;
    String applicantid = request.getParameter("applicantid");
    String type = request.getParameter("type");
    String uuid = request.getParameter("uuid");
    String refuserid = request.getParameter("refuserid");
    
    List eduList = BOFactory.getApplicantBO().getEducationsByApplicantAndType(new Long(applicantid).longValue(), type);
    if ((!StringUtils.isNullOrEmpty(type)) && (type.equals(Common.EDUCATION_COLLEGE))) {
      applicantform.setEducationsList(eduList);
    }
    if ((!StringUtils.isNullOrEmpty(type)) && (type.equals(Common.EDUCATION_CERTIFICATION))) {
      applicantform.setCertificationsList(eduList);
    }
    applicantform.setEduType(type);
    applicantform.setUuid(uuid);
    applicantform.setApplicantId(new Long(applicantid).longValue());
    request.setAttribute("applicantid", applicantid);
    request.setAttribute("uuid", uuid);
    request.setAttribute("refuserid", refuserid);
    
    return mapping.findForward("geteducationdetails");
  }
  
  public ActionForward deleteEducation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteEducation method");
    
    ApplicantForm applicantform = (ApplicantForm)form;
    String eduid = request.getParameter("eduid");
    BOFactory.getApplicantBO().deleteEducation(new Long(eduid).longValue());
    return null;
  }
  
  public ActionForward deleteResume(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteResume method");
    

    ApplicantForm applicantform = (ApplicantForm)form;
    
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("uuid");
    
    String refuserid = request.getParameter("refuserid");
    
    String ensuperUserKey = request.getParameter("superUserKey");
    if (StringUtils.isNullOrEmpty(ensuperUserKey)) {
      ensuperUserKey = (String)request.getSession().getAttribute("superUserKey");
    }
    String superUserKey = EncryptDecrypt.decrypt(ensuperUserKey);
    
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    if (applicant == null) {
      return null;
    }
    List attachlist = BOFactory.getApplicantBO().getApplicantAttachments(applicant.getApplicantId(), applicant.getUuid(), Common.APPLICANT_ATTACHMENT_TYPE_RESUME);
    if ((attachlist != null) && (attachlist.size() > 0)) {
      applicant.setApplicantAttachment((ApplicantAttachments)attachlist.get(0));
    }
    if ((applicant.getApplicantAttachment() != null) && (applicant.getApplicantAttachment().getAttachmentdata() != null) && (applicant.getResumename().length() > 0))
    {
      BOFactory.getApplicantBO().deleteResume(new Long(applicantId).longValue(), Common.APPLICANT_ATTACHMENT_TYPE_RESUME);
      
      applicant.setResumename(null);
      applicantform.setResumedetails("");
      BOFactory.getApplicantBO().updateApplicant(applicant);
    }
    applicantform.fromValue(applicant, request);
    
    BOFactory.getApplicantBO().commonLovPopulate(request, applicantform, applicant, new Long(superUserKey).longValue());
    


    applicantform.setCurrectctc(applicant.getCurrectctc());
    applicantform.setExpectedctc(applicant.getExpectedctc());
    applicantform.setCurrectctccurrencycode(applicant.getCurrectctccurrencycode());
    
    applicantform.setExpectedctccurrencycode(applicant.getExpectedctccurrencycode());
    

    BOFactory.getApplicantBO().commonSourceLovPopulate(applicantform, applicant);
    
    request.setAttribute("applicantId", String.valueOf(applicant.getApplicantId()));
    request.setAttribute("uuid", applicant.getUuid());
    request.setAttribute("refuserid", refuserid);
    
    request.setAttribute("applicantsaved", "no");
    request.setAttribute("saveAndNext", "0");
    request.setAttribute("backtodetails", "no");
    return mapping.findForward("refferaluserdetails");
  }
}
