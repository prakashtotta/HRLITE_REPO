package com.action;

import com.bean.ApplicantAttachments;
import com.bean.ApplicantSkills;
import com.bean.ApplicantUser;
import com.bean.EducationDetails;
import com.bean.JobApplicant;
import com.bean.JobRequisition;
import com.bean.Organization;
import com.bean.PreviousOrgDetails;
import com.bean.Timezone;
import com.bo.ApplicantBO;
import com.bo.ApplicantTXBO;
import com.bo.BOFactory;
import com.bo.JobRequistionBO;
import com.bo.LovBO;
import com.common.AppContextUtil;
import com.common.Common;
import com.dao.ApplicantUserDAO;
import com.dao.UserDAO;
import com.form.ApplicantForm;
import com.resources.Constant;
import com.util.DateUtil;
import com.util.EncryptDecrypt;
import com.util.StringUtils;
import java.io.File;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.context.ApplicationContext;

public class EditApplicantAction
  extends CommonAppAction
{
  protected static final Logger logger = Logger.getLogger(EditApplicantAction.class);
  
  public ActionForward editApplicantprofile(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside editApplicantprofile method ..");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    



    String saveAndNext = request.getParameter("saveAndNext");
    ApplicantUser user1 = (ApplicantUser)request.getSession().getAttribute("applicant_user_data");
    
    String appId = String.valueOf(user1.getApplicant().applicantId);
    logger.info(" aplicantId .. : " + appId);
    ApplicantForm applicantform = (ApplicantForm)form;
    

    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetails(appId);
    
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
    applicantform.fromValueApplicantlogin(applicant, request);
    String requitionId = request.getParameter("requistionId");
    if (!StringUtils.isNullOrEmpty(requitionId))
    {
      JobRequisition jobreq = BOFactory.getJobRequistionBO().getActiveOpenJobRequision(requitionId);
      if (jobreq != null)
      {
        applicantform.setRequitionId(jobreq.getJobreqId());
        applicantform.setJobTitle(jobreq.getJobTitle());
      }
    }
    BOFactory.getApplicantBO().commonLovPopulate(request, applicantform, applicant, user1.getApplicant().getSuper_user_key());
    


    request.setAttribute("saveAndNext", saveAndNext);
    
    return mapping.findForward("editApplicantprofile");
  }
  
  public ActionForward myprofilemainpage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside myprofilemainpage method ..");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    ApplicantUser user1 = (ApplicantUser)request.getSession().getAttribute("applicant_user_data");
    
    String appId = String.valueOf(user1.getApplicant().applicantId);
    logger.info(" aplicantId .. : " + appId);
    ApplicantForm applicantform = (ApplicantForm)form;
    

    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetails(appId);
    

    applicantform.fromValueApplicantlogin(applicant, request);
    String requitionId = request.getParameter("requistionId");
    if (!StringUtils.isNullOrEmpty(requitionId))
    {
      JobRequisition jobreq = BOFactory.getJobRequistionBO().getActiveOpenJobRequision(requitionId);
      if (jobreq != null)
      {
        applicantform.setRequitionId(jobreq.getJobreqId());
        applicantform.setJobTitle(jobreq.getJobTitle());
      }
    }
    BOFactory.getApplicantBO().commonLovPopulate(request, applicantform, applicant, user1.getApplicant().getSuper_user_key());
    
    logger.info(" aplicantId ..satya : " + appId);
    return mapping.findForward("myprofilemainpage");
  }
  
  public ActionForward changePassword(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    logger.info("Inside changepasswordscr method");
    ApplicantForm applicantform = (ApplicantForm)form;
    String applicantId = request.getParameter("applicantId");
    applicantform.setApplicantId(new Long(applicantId).longValue());
    applicantform.setCurrentpassword("");
    request.setAttribute("ispasswordchanged", "no");
    return mapping.findForward("changepassword");
  }
  
  public ActionForward changepasswordsubmit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside changepasswordsubmit method");
    ApplicantUser user1 = (ApplicantUser)request.getSession().getAttribute("applicant_user_data");
    ApplicantForm applicantform = (ApplicantForm)form;
    String applicantId = request.getParameter("applicantId");
    logger.info("applicantId  : " + applicantId);
    
    ApplicantUser appuser = ApplicantUserDAO.getApplicantUser(new Long(applicantId).longValue());
    String currentpasswordenc = EncryptDecrypt.encrypt(applicantform.getCurrentpassword());
    
    String newPassword = EncryptDecrypt.encrypt(applicantform.getPassword());
    String confirmPassword = EncryptDecrypt.encrypt(applicantform.getCpassword());
    
    String currentpassword = "true";
    String nwpassword = "true";
    ActionErrors errors = new ActionErrors();
    if (!currentpasswordenc.equals(appuser.getPassword()))
    {
      errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError("current.password.wrong"));
      saveErrors(request, errors);
      currentpassword = "false";
    }
    if (!newPassword.equals(confirmPassword))
    {
      errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError("hr.user.Pass_does_not_match"));
      saveErrors(request, errors);
      nwpassword = "false";
    }
    if ((nwpassword.equals("true")) && (currentpassword.equals("true")))
    {
      appuser.setPassword(EncryptDecrypt.encrypt(applicantform.getPassword()));
      ApplicationContext appContext = AppContextUtil.getAppcontext();
      ApplicantTXBO apptxbo = (ApplicantTXBO)appContext.getBean("apptxBoProxy");
      apptxbo.updateApplicantUserForPassword(appuser);
      


      ApplicantUserDAO.updateApplicantUser(appuser);
      request.setAttribute("ispasswordchanged", "yes");
    }
    return mapping.findForward("changepassword");
  }
  
  public ActionForward nexttoEducationtab(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside editApplicantprofile method ..");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    


    ApplicantUser user1 = (ApplicantUser)request.getSession().getAttribute("applicant_user_data");
    
    String appId = String.valueOf(user1.getApplicant().applicantId);
    logger.info(" aplicantId .. : " + appId);
    ApplicantForm applicantform = (ApplicantForm)form;
    

    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetails(appId);
    
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
    applicantform.fromValueApplicantlogin(applicant, request);
    String requitionId = request.getParameter("requistionId");
    if (!StringUtils.isNullOrEmpty(requitionId))
    {
      JobRequisition jobreq = BOFactory.getJobRequistionBO().getActiveOpenJobRequision(requitionId);
      if (jobreq != null)
      {
        applicantform.setRequitionId(jobreq.getJobreqId());
        applicantform.setJobTitle(jobreq.getJobTitle());
      }
    }
    BOFactory.getApplicantBO().commonLovPopulate(request, applicantform, applicant, user1.getApplicant().getSuper_user_key());
    

    request.setAttribute("saveAndNext", "2");
    
    return mapping.findForward("editApplicantprofile");
  }
  
  public ActionForward backtoWorkExpSkillstab(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside editApplicantprofile method ..");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    


    ApplicantUser user1 = (ApplicantUser)request.getSession().getAttribute("applicant_user_data");
    
    String appId = String.valueOf(user1.getApplicant().applicantId);
    logger.info(" aplicantId .. : " + appId);
    ApplicantForm applicantform = (ApplicantForm)form;
    

    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetails(appId);
    
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
    applicantform.fromValueApplicantlogin(applicant, request);
    String requitionId = request.getParameter("requistionId");
    if (!StringUtils.isNullOrEmpty(requitionId))
    {
      JobRequisition jobreq = BOFactory.getJobRequistionBO().getActiveOpenJobRequision(requitionId);
      if (jobreq != null)
      {
        applicantform.setRequitionId(jobreq.getJobreqId());
        applicantform.setJobTitle(jobreq.getJobTitle());
      }
    }
    BOFactory.getApplicantBO().commonLovPopulate(request, applicantform, applicant, user1.getApplicant().getSuper_user_key());
    


    request.setAttribute("saveAndNext", "1");
    
    return mapping.findForward("editApplicantprofile");
  }
  
  public ActionForward updateapplicantData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updateapplicantData method ....");
    ApplicantUser appuser = (ApplicantUser)request.getSession().getAttribute("applicant_user_data");
    ApplicantForm applicantform = (ApplicantForm)form;
    

    String dateofbirth = request.getParameter("dateofbirth");
    String previousOrganization = request.getParameter("previousOrganization");
    String applicantId = request.getParameter("applicantId");
    String reqId = request.getParameter("reqId");
    long rId = Long.parseLong(reqId);
    logger.info("reqId .... " + reqId);
    String jobtitle = request.getParameter("jobtitle");
    logger.info("jobtitle .... " + jobtitle);
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetails(applicantId);
    
    String rname = applicant.getResumename();
    applicantform.toValue(applicant, request);
    logger.info("request.getParameter request.getParameter");
    
    String gender = request.getParameter("gender");
    logger.info(gender);
    if (!StringUtils.isNullOrEmpty(dateofbirth))
    {
      String datepattern = DateUtil.getDatePatternFormat(appuser.getLocale());
      String converteddate = DateUtil.convertFromTimezoneToTimezoneDate(dateofbirth, datepattern, appuser.getTimezone().getTimezoneCode(), TimeZone.getDefault().getID());
      
      logger.info("converteddate" + converteddate);
      
      Calendar cal = DateUtil.convertStringDateToCalendar(converteddate, datepattern);
      
      applicant.setDateofbirth(cal.getTime());
    }
    applicant.setReqId(rId);
    logger.info("applicant.getReqId(): " + applicant.getReqId());
    JobRequisition jbr = BOFactory.getJobRequistionBO().getJobRequision(String.valueOf(applicant.getReqId()));
    
    applicant.setJobTitle(jobtitle);
    
    applicant.setGender(gender);
    
    applicant.setPreviousOrganization(previousOrganization);
    logger.info("rname : " + applicant.getResumename());
    logger.info("applicantform.getResumename() : " + applicant.getResumename());
    boolean isAttachmentupdata = true;
    if ((rname != null) && (applicant.getResumename() == null))
    {
      applicant.setResumename(rname);
      isAttachmentupdata = false;
    }
    applicant.setCurrectctc(applicantform.getCurrectctc());
    applicant.setExpectedctc(applicantform.getExpectedctc());
    String currencycode = jbr.getOrganization().getCurrencyCode();
    applicant.setCurrectctccurrencycode(currencycode);
    applicant.setExpectedctccurrencycode(currencycode);
    boolean isSuccess = true;
    List errorList = new ArrayList();
    applicant = BOFactory.getApplicantBO().updateApplicant(applicant, null, null, null, appuser, null, errorList, applicantform.getErrorListCustomVariable(), applicantform.getFormVariableDataList(), isAttachmentupdata);
    

    errorList = applicant.getErrorList();
    if ((errorList != null) && (errorList.size() > 0)) {
      isSuccess = false;
    }
    if (isSuccess)
    {
      request.setAttribute("applicantupdated", "yes");
      request.setAttribute("applicantdatacustom", "yes");
      

      request.setAttribute("saveAndNext", "0");
    }
    else
    {
      logger.info("in error condition");
      request.setAttribute("error_list", errorList);
      request.setAttribute("saveAndNext", "0");
    }
    applicantform.fromValue(applicant, request);
    
    BOFactory.getApplicantBO().commonLovPopulate(request, applicantform, applicant, appuser.getApplicant().getSuper_user_key());
    



    return mapping.findForward("editApplicantprofile");
  }
  
  public ActionForward loadResumeSources(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside loadResumeSources method");
    String typeid = request.getParameter("typeid");
    logger.info("typeid : " + typeid);
    String applicantId = request.getParameter("applicantId");
    ApplicantForm applicantform = (ApplicantForm)form;
    if ((typeid != null) && (new Integer(typeid).intValue() == 5))
    {
      List vendorList = UserDAO.getAllActiveVendors();
      applicantform.setVendorsList(vendorList);
    }
    else if ((typeid == null) || (new Integer(typeid).intValue() != 1))
    {
      List sourceList = BOFactory.getLovBO().getAllResumeSource(new Integer(typeid).intValue());
      applicantform.setSourceList(sourceList);
    }
    request.setAttribute("soucetypeid", typeid);
    if ((applicantId != null) && (applicantId.length() > 0))
    {
      JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetails(applicantId);
      
      applicantform.setReferrerName(applicant.getReferrerName());
      applicantform.setReferrerEmail(applicant.getReferrerEmail());
    }
    return mapping.findForward("resumeSources");
  }
  
  public ActionForward loadState(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside loadState method");
    String cid = request.getParameter("cid");
    System.out.println(" **** cid :" + cid);
    
    ApplicantForm applicantform = (ApplicantForm)form;
    applicantform.setStateList(BOFactory.getLovBO().getStateListByCountry(new Long(cid).longValue()));
    


    System.out.println(" **** test :");
    return mapping.findForward("stateList");
  }
  
  public ActionForward prevOrgloadState(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside prevOrgloadStateR method ...");
    String cid = request.getParameter("cid");
    logger.info(" **** cid :" + cid);
    ApplicantForm applicantform = (ApplicantForm)form;
    

    applicantform.setStateList(BOFactory.getLovBO().getStateListByCountry(new Long(cid).longValue()));
    

    return mapping.findForward("prevOrgstateList");
  }
  
  public ActionForward savepreviousOrgdetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside savepreviousOrgdetails method ....");
    ApplicantUser user1 = (ApplicantUser)request.getSession().getAttribute("applicant_user_data");
    ApplicantForm applicantform = (ApplicantForm)form;
    String applicantid = request.getParameter("applicantid");
    String prevOrgName = request.getParameter("prevOrgName");
    String role = request.getParameter("role");
    String startDate = request.getParameter("startDate");
    String endDate = request.getParameter("endDate");
    String reasonforleave = request.getParameter("reasonforleave");
    String reportingto = request.getParameter("reportingto");
    
    PreviousOrgDetails prevorg = new PreviousOrgDetails();
    prevorg.setApplicantId(new Long(applicantid).longValue());
    prevorg.setPrevOrgName(prevOrgName);
    prevorg.setRole(role);
    prevorg.setStartdate(startDate);
    prevorg.setEnddate(endDate);
    prevorg.setReasonforleave(reasonforleave);
    prevorg.setReportingToName(reportingto);
    if (user1 != null) {
      prevorg.setCreatedBy(user1.getEmailId());
    }
    prevorg.setCreatedDate(new Date());
    
    BOFactory.getApplicantBO().savePreviousOrgDetails(prevorg);
    


    return null;
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
  
  public ActionForward getpreviousOrgdetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside getpreviousOrgdetails method");
    ApplicantForm applicantform = (ApplicantForm)form;
    String applicantid = request.getParameter("applicantid");
    
    List orgDetailsList = BOFactory.getApplicantBO().getPreviousOrgDetailsByApplicant(new Long(applicantid).longValue());
    applicantform.setPreviousOrgList(orgDetailsList);
    
    return mapping.findForward("getpreviousOrgdetails");
  }
  
  public ActionForward saveapplicantSkills(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveapplicantSkills method");
    ApplicantUser user1 = (ApplicantUser)request.getSession().getAttribute("applicant_user_data");
    ApplicantForm applicantform = (ApplicantForm)form;
    String applicantid = request.getParameter("applicantid");
    String skillname = request.getParameter("skillname");
    String yearsofexpskill = request.getParameter("yearsofexpskill");
    String ratingskill = request.getParameter("ratingskill");
    

    ApplicantSkills edu = new ApplicantSkills();
    edu.setApplicantId(new Long(applicantid).longValue());
    edu.setSkillname(skillname);
    edu.setYearsofexp(yearsofexpskill);
    edu.setRating(ratingskill);
    



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
    
    List skillsList = BOFactory.getApplicantBO().getSkillsByApplicant(new Long(applicantid).longValue());
    applicantform.setApplicantSkillList(skillsList);
    
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
    ApplicantUser user1 = (ApplicantUser)request.getSession().getAttribute("applicant_user_data");
    ApplicantForm applicantform = (ApplicantForm)form;
    String applicantid = request.getParameter("applicantid");
    String type = request.getParameter("type");
    String educationName = request.getParameter("educationName");
    String specialization = request.getParameter("specialization");
    String institute = request.getParameter("institute");
    String percentile = request.getParameter("percentile");
    String passingyear = request.getParameter("passingyear");
    String startingYear = request.getParameter("startingYear");
    
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
      edu.setCreatedBy(user1.getEmailId());
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
    
    List eduList = BOFactory.getApplicantBO().getEducationsByApplicantAndType(new Long(applicantid).longValue(), type);
    if ((!StringUtils.isNullOrEmpty(type)) && (type.equals(Common.EDUCATION_COLLEGE))) {
      applicantform.setEducationsList(eduList);
    }
    if ((!StringUtils.isNullOrEmpty(type)) && (type.equals(Common.EDUCATION_CERTIFICATION))) {
      applicantform.setCertificationsList(eduList);
    }
    applicantform.setEduType(type);
    applicantform.setUuid(uuid);
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
  
  public ActionForward applicantReferenceDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside applicantReferenceDetails method");
    ApplicantForm applicantform = (ApplicantForm)form;
    
    String applicantId = request.getParameter("applicantId");
    String secureid = request.getParameter("secureid");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, secureid);
    if (applicant != null)
    {
      applicantform.fromValue(applicant, request);
      
      List refList = BOFactory.getApplicantBO().getAllReferences(applicant.getApplicantId());
      applicantform.setRefrenceslist(refList);
    }
    return mapping.findForward("applicantReferenceDetails");
  }
  
  public ActionForward deleteResume(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteResume method .. applicant portal");
    
    ApplicantUser user1 = (ApplicantUser)request.getSession().getAttribute("applicant_user_data");
    
    ApplicantForm applicantform = (ApplicantForm)form;
    
    String applicantId = request.getParameter("applicantId");
    String reqId = request.getParameter("reqId");
    
    JobRequisition jb = BOFactory.getJobRequistionBO().getJobRequision(reqId);
    String jobTitle = jb.getJobTitle();
    applicantform.setRequitionId(new Long(reqId).longValue());
    applicantform.setJobTitle(jobTitle);
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetails(applicantId);
    

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
    BOFactory.getApplicantBO().commonLovPopulate(request, applicantform, applicant, user1.getApplicant().getSuper_user_key());
    


    applicantform.setCurrectctc(applicant.getCurrectctc());
    applicantform.setExpectedctc(applicant.getExpectedctc());
    applicantform.setCurrectctccurrencycode(applicant.getCurrectctccurrencycode());
    
    applicantform.setExpectedctccurrencycode(applicant.getExpectedctccurrencycode());
    

    BOFactory.getApplicantBO().commonSourceLovPopulate(applicantform, applicant);
    

    request.setAttribute("applicantsaved", "no");
    request.setAttribute("saveAndNext", "0");
    return mapping.findForward("editApplicantprofile");
  }
  
  public ActionForward downloadApplicantDetailPdf(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside downloadApplicantDetailPdf method .. in EditApplicantAction");
    

    String applicantid = request.getParameter("applicantid");
    String uuid = request.getParameter("secureid");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantForm applicantForm = (ApplicantForm)form;
    applicantForm.setUuid(uuid);
    
    ApplicantUser user1 = (ApplicantUser)request.getSession().getAttribute("applicant_user_data");
    try
    {
      applicantForm.setApplicantId(new Long(applicantid).longValue());
      logger.info(".. applicant id : " + applicantid);
      
      BOFactory.getApplicantBO().downloadpdf(applicantForm, request);
      return mapping.findForward("downloadPdf");
    }
    catch (Exception e)
    {
      String msg = Constant.getResourceStringValue("server.error", user1.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
    }
    return mapping.findForward("exception");
  }
}
