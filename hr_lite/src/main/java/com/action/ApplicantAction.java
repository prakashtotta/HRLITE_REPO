package com.action;

import java.io.File;
import java.io.RandomAccessFile;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.springframework.context.ApplicationContext;

import com.bean.ApplicantActivity;
import com.bean.ApplicantAttachments;
import com.bean.ApplicantComment;
import com.bean.ApplicantOfferAttachment;
import com.bean.ApplicantOtherBenefits;
import com.bean.ApplicantProfilePhoto;
import com.bean.ApplicantSkills;
import com.bean.ApplicantTags;
import com.bean.BulkUploadTask;
import com.bean.EducationDetails;
import com.bean.JobApplicant;
import com.bean.JobApplicationEvent;
import com.bean.JobRequisition;
import com.bean.OfferApprover;
import com.bean.OfferWatchList;
import com.bean.Organization;
import com.bean.PreviousOrgDetails;
import com.bean.QuestionGroupApplicants;
import com.bean.QuestionGroups;
import com.bean.Questions;
import com.bean.SalaryPlan;
import com.bean.SearchApplicant;
import com.bean.SearchApplicantCustomFields;
import com.bean.SearchApplicantQuestions;
import com.bean.User;
import com.bean.UserGroup;
import com.bean.criteria.ApplicantSearchCriteria;
import com.bean.criteria.ApplicantSearchUtil;
import com.bean.dto.Recruiter;
import com.bean.pool.TalentPool;
import com.bean.pool.TalentPoolElements;
import com.bean.system.SystemRule;
import com.bean.system.SystemRuleUser;
import com.bean.testengine.MockQuestionSet;
import com.bean.testengine.MockTest;
import com.bo.ApplicantTXBO;
import com.bo.BOFactory;
import com.bo.PermissionBO;
import com.bo.UserBO;
import com.common.AppContextUtil;
import com.common.Common;
import com.common.EmailNotificationSettingFunction;
import com.dao.ApplicantUserDAO;
import com.dao.RuleDAO;
import com.dao.TalentPoolDAO;
import com.dao.UserDAO;
import com.form.ApplicantForm;
import com.form.VariableDataCaptureUtil;
import com.manager.BulkTaskManager;
import com.manager.EmailTaskManager;
import com.resources.Constant;
import com.util.BulkUploadTaskUtil;
import com.util.DateUtil;
import com.util.EmailTask;
import com.util.ExportTaskUtil;
import com.util.FileExtractor;
import com.util.StringUtils;

public class ApplicantAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(ApplicantAction.class);
  
  public ActionForward loadDeptlistWithProjectcode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside loadDeptlistWithProjectcode method");
    String orgId = request.getParameter("orgId");
    System.out.println("orgId" + orgId);
    ApplicantForm applicantform = (ApplicantForm)form;
    applicantform.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(orgId));
    List projectcodeList = new ArrayList();
    
    return mapping.findForward("deptlistwithprojectcode");
  }
  
  public ActionForward loadprojectcodebyOrganization(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    ApplicantForm applicantform = (ApplicantForm)form;
    
    List projectcodeList = new ArrayList();
    applicantform.setProjectcodeList(projectcodeList);
    
    return mapping.findForward("loadProjectCode");
  }
  
  public ActionForward applicantList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside applicantList method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantForm applicantform = (ApplicantForm)form;
    
    return mapping.findForward("applicantList");
  }
  
  public ActionForward activityList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside activityList method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantForm applicantform = (ApplicantForm)form;
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    
    List activityList = BOFactory.getApplicantBO().getActivityList(new Long(applicantId).longValue(), uuid);
    

    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    

    applicantform.fromValue(applicant, request);
    
    applicantform.setActivityList(activityList);
    





    List alreayappliedlist = BOFactory.getApplicantBO().getApplicantListByEmailIdWithoutCurrent(applicant.getEmail(), applicant.getApplicantId(), applicant.getSuper_user_key());
    
    applicantform.setAlreayappliedlist(alreayappliedlist);
    
    return mapping.findForward("activityList");
  }
  
  public ActionForward bulkresume(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside bulkresume method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    return mapping.findForward("bulkresume");
  }
  
  public ActionForward bulkresumeupload(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside bulkresumeupload method");
    List successFilelist = new ArrayList();
    List failFilelist = new ArrayList();
    String requitionId = request.getParameter("reqid1");
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantForm applicantform = (ApplicantForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    
    String filePath = Constant.getValue("ATTACHMENT_PATH") + user1.getUserName() + File.separator;
    

    FormFile myFile = applicantform.getResumeData();
    
    String fileName = "";
    if (myFile != null)
    {
      String contentType = myFile.getContentType();
      fileName = myFile.getFileName();
      int fileSize = myFile.getFileSize();
      logger.info(Integer.valueOf(fileSize));
      if (fileSize > 10240000)
      {
        errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError("error.bulkupload.filesize"));
        
        request.setAttribute("resumeparsed", "no");
      }
      else
      {
        byte[] fileData = myFile.getFileData();
        Blob blob = null;
        
        blob = new SerialBlob(fileData);
        
        File file = new File(filePath);
        if (!file.exists()) {
          file.mkdirs();
        }
        RandomAccessFile raf = new RandomAccessFile(filePath + fileName, "rw");
        

        int length = (int)blob.length();
        byte[] _blob = blob.getBytes(1L, length);
        raf.write(_blob);
        raf.close();
        

        BulkUploadTask task = new BulkUploadTask();
        task.setUploadedFileName(fileName);
        task.setJobreqId(new Long(requitionId).longValue());
        BulkUploadTaskUtil bulkupload = new BulkUploadTaskUtil();
        bulkupload.setTasktype(Common.BULK_UPLOAD_RESUME);
        bulkupload.setUser(user1);
        bulkupload.setTask(task);
        BulkTaskManager.bulkupload(bulkupload);
      }
    }
    saveErrors(request, errors);
    

    request.setAttribute("resumeparsed", "yes");
    return mapping.findForward("bulkresume");
  }
  
  public ActionForward searchownapplicantinit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchownapplicantinit method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantForm applicantform = (ApplicantForm)form;
    
    List interviewstatelist = Constant.getInterviewStatesWithOutBlank();
    applicantform.setInterviewstateList(interviewstatelist);
    applicantform.setInterviewState("Application Submitted");
    if (request.getSession().getAttribute(Common.APPLICANT_COUNT) != null) {
      request.getSession().removeAttribute(Common.APPLICANT_COUNT);
    }
    return mapping.findForward("searchownapplicantinit");
  }
  
  public ActionForward searchownapplicantinitHiringMgr(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchownapplicantinitHiringMgr method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantForm applicantform = (ApplicantForm)form;
    List jobtitleList = BOFactory.getJobRequistionBO().getJobTitlesActiveListAssigedToUser(user1);
    applicantform.setJobtitleList(jobtitleList);
    if (request.getSession().getAttribute(Common.APPLICANT_COUNT) != null) {
      request.getSession().removeAttribute(Common.APPLICANT_COUNT);
    }
    List interviewstatelist = Constant.getInterviewStates();
    applicantform.setInterviewstateList(interviewstatelist);
    applicantform.setSearchCriDateList(Constant.getSearchCriteraDateFields());
    



    return mapping.findForward("searchownapplicantinitHiringMgr");
  }
  
  public ActionForward searchownapplicantinitRecruiter(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchownapplicantinitRecruiter method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantForm applicantform = (ApplicantForm)form;
    List jobtitleList = BOFactory.getJobRequistionBO().getJobTitlesActiveListAssigedToUser(user1);
    applicantform.setJobtitleList(jobtitleList);
    if (request.getSession().getAttribute(Common.APPLICANT_COUNT) != null) {
      request.getSession().removeAttribute(Common.APPLICANT_COUNT);
    }
    List interviewstatelist = Constant.getInterviewStatesWithOutBlank();
    applicantform.setInterviewstateList(interviewstatelist);
    applicantform.setSearchCriDateList(Constant.getSearchCriteraDateFields());
    



    return mapping.findForward("searchownapplicantinitRecruiter");
  }
  
  public ActionForward searchownapplicantRecruiter(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchownapplicantRecruiter method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantForm applicantform = (ApplicantForm)form;
    String previousOrganization = request.getParameter("previousOrganization");
    
    String applieddate = request.getParameter("applieddate");
    String tags = request.getParameter("tags");
    
    applicantform.setPreviousOrganization(previousOrganization);
    applicantform.setFullName(applicantform.getFullName());
    applicantform.setReferrerName(applicantform.getReferrerName());
    applicantform.setRequitionId(applicantform.getRequitionId());
    applicantform.setSearchapplieddate(applieddate);
    
    List jobtitleList = BOFactory.getJobRequistionBO().getJobTitlesActiveListAssigedToUser(user1);
    applicantform.setJobtitleList(jobtitleList);
    List interviewstatelist = Constant.getInterviewStatesWithOutBlank();
    applicantform.setInterviewstateList(interviewstatelist);
    applicantform.setSearchCriDateList(Constant.getSearchCriteraDateFields());
    
    applicantform.setTagName(tags);
    String tagdata = "";
    if (!StringUtils.isNullOrEmpty(tags))
    {
      List tagList = StringUtils.tokenizeString(tags, ",");
      for (int i = 0; i < tagList.size(); i++)
      {
        String tag = (String)tagList.get(i);
        tagdata = tagdata + "{ " + "\"" + "id" + "\"" + ":" + "\"" + tag + "\"," + "  " + "\"" + "name" + "\"" + ":" + "\"" + tag + "\"" + "}" + ",";
      }
    }
    if (!StringUtils.isNullOrEmpty(tagdata)) {
      tagdata = tagdata.substring(0, tagdata.length() - 1);
    }
    applicantform.setTagNames(tagdata);
    request.setAttribute("searchpagedisplay", "yes");
    return mapping.findForward("searchownapplicantinitRecruiter");
  }
  
  public ActionForward searchownapplicantHiringMgr(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchownapplicantHiringMgr method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantForm applicantform = (ApplicantForm)form;
    String previousOrganization = request.getParameter("previousOrganization");
    
    String applieddate = request.getParameter("applieddate");
    String appliedcri = request.getParameter("appliedcri");
    String searchfromdate = request.getParameter("searchfromdate");
    String searchtodate = request.getParameter("searchtodate");
    String orgId = request.getParameter("orgId");
    String departmentId = request.getParameter("departmentId");
    String projectcodeId = request.getParameter("projectcodeId");
    String tags = request.getParameter("tags");
    String vendorId = request.getParameter("vendorId");
    logger.info("appliedcri :" + appliedcri);
    logger.info("searchfromdate : " + searchfromdate);
    logger.info("searchtodate : " + searchtodate);
    logger.info("orgId : " + orgId);
    logger.info("departmentId : " + departmentId);
    logger.info("projectcodeId : " + projectcodeId);
    
    logger.info("vendorId : " + vendorId);
    if (((!StringUtils.isNullOrEmpty(appliedcri)) && (!appliedcri.equals("NoValue"))) || (!StringUtils.isNullOrEmpty(searchfromdate)) || (!StringUtils.isNullOrEmpty(searchtodate)) || (!StringUtils.isNullOrEmpty(orgId)) || (!StringUtils.isNullOrEmpty(departmentId)) || (!StringUtils.isNullOrEmpty(projectcodeId)) || (!StringUtils.isNullOrEmpty(tags)) || (!StringUtils.isNullOrEmpty(vendorId))) {
      request.setAttribute("toggelOpen", "yes");
    }
    applicantform.setPreviousOrganization(previousOrganization);
    applicantform.setFullName(applicantform.getFullName());
    applicantform.setReferrerName(applicantform.getReferrerName());
    applicantform.setRequitionId(applicantform.getRequitionId());
    applicantform.setSearchapplieddate(applieddate);
    
    List jobtitleList = BOFactory.getJobRequistionBO().getJobTitlesActiveListAssigedToUser(user1);
    applicantform.setJobtitleList(jobtitleList);
    List interviewstatelist = Constant.getInterviewStates();
    applicantform.setInterviewstateList(interviewstatelist);
    applicantform.setSearchCriDateList(Constant.getSearchCriteraDateFields());
    

    applicantform.setTagName(tags);
    String tagdata = "";
    if (!StringUtils.isNullOrEmpty(tags))
    {
      List tagList = StringUtils.tokenizeString(tags, ",");
      for (int i = 0; i < tagList.size(); i++)
      {
        String tag = (String)tagList.get(i);
        tagdata = tagdata + "{ " + "\"" + "id" + "\"" + ":" + "\"" + tag + "\"," + "  " + "\"" + "name" + "\"" + ":" + "\"" + tag + "\"" + "}" + ",";
      }
    }
    if (!StringUtils.isNullOrEmpty(tagdata)) {
      tagdata = tagdata.substring(0, tagdata.length() - 1);
    }
    applicantform.setTagNames(tagdata);
    
    return mapping.findForward("searchownapplicantinitHiringMgr");
  }
  
  public ActionForward onboardingapplicants(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside onboardingapplicants method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantForm applicantform = (ApplicantForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    List userlist = UserDAO.getAllUsersByType("Vendor");
    applicantform.setVendorList(userlist);
    List interviewstatelist = Constant.getInterviewStatesForOnBoarding();
    applicantform.setInterviewstateList(interviewstatelist);
    
    applicantform.setOnboardingProcessStatusList(Constant.getOnBoardingProcessStatus(user1));
    
    List jobtitleList = BOFactory.getJobRequistionBO().getJobTitlesActiveListForForm(user1.getSuper_user_key());
    
    applicantform.setJobtitleList(jobtitleList);
    



    List orgList = BOFactory.getLovBO().getAllOrganization(user1.getSuper_user_key());
    applicantform.setOrganizationList(orgList);
    List deptlist = new ArrayList();
    applicantform.setDepartmentList(deptlist);
    if (orgList.size() > 0)
    {
      Organization org = (Organization)orgList.get(0);
      applicantform.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(org.getOrgId())));
    }
    applicantform.setSearchCriDateList(Constant.getSearchCriteraDateFields());
    

    applicantform.setPaginationRowsList(Constant.paginationRowsList);
    

    List tagList = BOFactory.getLovBO().getTagsList("APPLICANT", user1.getSuper_user_key());
    
    applicantform.setTagList(tagList);
    return mapping.findForward("onboardingapplicants");
  }
  
  public ActionForward releaseofferscr(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside releaseofferscr method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantForm applicantform = (ApplicantForm)form;
    String applicantId = request.getParameter("applicantId");
    String resend = request.getParameter("resend");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetails(applicantId);
    if ((!StringUtils.isNullOrEmpty(applicant.getIsInitiateOfferAttachmentCopied())) && (applicant.getIsInitiateOfferAttachmentCopied().equals("N"))) {
      BOFactory.getApplicantBO().copyOfferAttachmentInitiateToRelease(applicant, new Long(applicantId).longValue(), "initoffer", "reloffer");
    }
    List attachmentList = BOFactory.getApplicantBO().getOfferAttachmentList(new Long(applicantId).longValue(), "reloffer");
    

    applicantform.setOfferAttachmentList(attachmentList);
    applicantform.fromValue(applicant, request);
    List watchList = new ArrayList();
    applicantform.setOfferwatchList(watchList);
    
    JobRequisition jb = BOFactory.getJobRequistionBO().getJobRequision(String.valueOf(applicant.getReqId()));
    applicantform.setSalaryPlan(jb.getSalaryplan());
    if ((StringUtils.isNullOrEmpty(resend)) || (!resend.equals("true")))
    {
      int totalfilledposition = BOFactory.getApplicantBO().getTotalNoOfFilledPositionByReqId(jb.getJobreqId());
      if (totalfilledposition >= jb.getNumberOfOpening()) {
        request.setAttribute("numberofpositionfilledexceeds", "yes");
      }
    }
    request.setAttribute("offerreleased", "no");
    return mapping.findForward("releaseofferscr");
  }
  
  public ActionForward canceloffer(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside canceloffer method");
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantForm applicantform = (ApplicantForm)form;
    String applicantId = request.getParameter("applicantId");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetails(applicantId);
    
    applicantform.fromValue(applicant, request);
    
    List cancelresonlist = BOFactory.getLovBO().getAllOfferCancelResons();
    applicantform.setOfferdeclinedreasonslist(cancelresonlist);
    
    request.setAttribute("offercancelled", "no");
    return mapping.findForward("canceloffer");
  }
  
  public ActionForward onboard(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside onboard method");
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantForm applicantform = (ApplicantForm)form;
    String applicantId = request.getParameter("applicantId");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetails(applicantId);
    
    applicantform.fromValue(applicant, request);
    




    request.setAttribute("onboarddone", "no");
    return mapping.findForward("onboard");
  }
  
  public ActionForward markresignedscr(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside markresignedscr method");
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantForm applicantform = (ApplicantForm)form;
    String applicantId = request.getParameter("applicantId");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetails(applicantId);
    
    applicantform.fromValue(applicant, request);
    
    request.setAttribute("markresigned", "no");
    return mapping.findForward("markresignedscr");
  }
  
  public ActionForward assignExams(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside assignExams method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantForm applicantform = (ApplicantForm)form;
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("uuid");
    List mocktests = BOFactory.getMockTestBO().getAllMockTestsByApplicant(new Long(applicantId).longValue(), uuid);
    List mockCatList = BOFactory.getMockTestBO().getAllMockCategoryList();
    
    applicantform.setMockCatList(mockCatList);
    applicantform.setMocktestList(mocktests);
    
    List questiongrpList = BOFactory.getQuestionBO().getAllquestionsGroupDetails(user1);
    List assignedquestionaireList = BOFactory.getQuestionBO().getAllquestionsGroupDetailsByApplicantId(new Long(applicantId).longValue());
    applicantform.setQuestionGroupList(questiongrpList);
    applicantform.setQuetionnaireList(assignedquestionaireList);
    
    applicantform.setApplicantId(new Long(applicantId).longValue());
    applicantform.setUuid(uuid);
    request.setAttribute("assignExam", "no");
    return mapping.findForward("assignExams");
  }
  
  public ActionForward deleteExam(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteExams method");
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantForm applicantform = (ApplicantForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("uuid");
    String testid = request.getParameter("testid");
    BOFactory.getMockTestBO().deleteMockTest(new Long(applicantId).longValue(), uuid, new Long(testid).longValue(), user1);
    
    List mocktests = BOFactory.getMockTestBO().getAllMockTestsByApplicant(new Long(applicantId).longValue(), uuid);
    
    List mockCatList = BOFactory.getMockTestBO().getAllMockCategoryList();
    applicantform.setMockCatList(mockCatList);
    applicantform.setMocktestList(mocktests);
    
    List questiongrpList = BOFactory.getQuestionBO().getAllquestionsGroupDetails(user1);
    List assignedquestionaireList = BOFactory.getQuestionBO().getAllquestionsGroupDetailsByApplicantId(new Long(applicantId).longValue());
    applicantform.setQuestionGroupList(questiongrpList);
    applicantform.setQuetionnaireList(assignedquestionaireList);
    
    applicantform.setApplicantId(new Long(applicantId).longValue());
    applicantform.setUuid(uuid);
    request.setAttribute("assignExam", "no");
    return mapping.findForward("assignExams");
  }
  
  public ActionForward deleteQuestionnaire(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteQuestionnaire method");
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantForm applicantform = (ApplicantForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("uuid");
    String testid = request.getParameter("testid");
    BOFactory.getMockTestBO().deleteQuestionnaire(new Long(applicantId).longValue(), uuid, new Long(testid).longValue(), user1);
    
    List mocktests = BOFactory.getMockTestBO().getAllMockTestsByApplicant(new Long(applicantId).longValue(), uuid);
    
    List mockCatList = BOFactory.getMockTestBO().getAllMockCategoryList();
    applicantform.setMockCatList(mockCatList);
    applicantform.setMocktestList(mocktests);
    
    List questiongrpList = BOFactory.getQuestionBO().getAllquestionsGroupDetails(user1);
    List assignedquestionaireList = BOFactory.getQuestionBO().getAllquestionsGroupDetailsByApplicantId(new Long(applicantId).longValue());
    applicantform.setQuestionGroupList(questiongrpList);
    applicantform.setQuetionnaireList(assignedquestionaireList);
    
    applicantform.setApplicantId(new Long(applicantId).longValue());
    applicantform.setUuid(uuid);
    request.setAttribute("tabselected2", "yes");
    request.setAttribute("assignExam", "no");
    return mapping.findForward("assignExams");
  }
  
  public ActionForward resendExam(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside resendExam method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantForm applicantform = (ApplicantForm)form;
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("uuid");
    String testid = request.getParameter("testid");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    

    MockTest mock1 = BOFactory.getMockTestBO().getMockTestByTestDetails(testid, applicantId, uuid);
    
    JobApplicationEvent event = BOFactory.getApplicantBO().getApplicationEvent(new Long(applicantId).longValue(), Common.getEventType("Exam Screening"), new Long(testid).longValue());
    


    String[] to = { applicant.getEmail() };
    




    List cclist = BOFactory.getApplicantBO().getCCListForApplicant(user1, applicant, null, EmailNotificationSettingFunction.FunctionNames.EXAM_SCHEDULE_TO_APPLICANT.toString(), true);
    
    String[] cc = new String[cclist.size()];
    cclist.toArray(cc);
    String[] bcc = null;
    

    String replyTo = "\"" + user1.getFirstName() + " " + user1.getLastName() + "\"" + " " + "<" + user1.getEmailId() + ">";
    


    EmailTask emailtask = new EmailTask(replyTo, to, cc, bcc, replyTo, "dummysubject", null, "dummybody", null, 0, null);
    

    emailtask.setMocktest(mock1);
    emailtask.setUser(user1);
    emailtask.setComment(event.getNotes());
    emailtask.setFunctionType(Common.EXAM_SCHEDULE);
    EmailTaskManager.sendEmail(emailtask);
    

    List mocktestList = BOFactory.getMockTestBO().getAllMockTestsByApplicant(applicantform.getApplicantId(), applicantform.getUuid());
    applicantform.setMocktestList(mocktestList);
    List mockCatList = BOFactory.getMockTestBO().getAllMockCategoryList();
    applicantform.setMockCatList(mockCatList);
    List questiongrpList = BOFactory.getQuestionBO().getAllquestionsGroupDetails(user1);
    List assignedquestionaireList = BOFactory.getQuestionBO().getAllquestionsGroupDetailsByApplicantId(new Long(applicantId).longValue());
    applicantform.setQuestionGroupList(questiongrpList);
    applicantform.setQuetionnaireList(assignedquestionaireList);
    applicantform.setApplicantId(new Long(applicantId).longValue());
    applicantform.setUuid(uuid);
    request.setAttribute("resendExam", "yes");
    return mapping.findForward("assignExams");
  }
  
  public ActionForward assignExamSubmit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside assignExamSubmit method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantForm applicantform = (ApplicantForm)form;
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("uuid");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    

    MockTest mock1 = BOFactory.getMockTestBO().getMockTest(String.valueOf(applicantform.getMockcatId()), applicantId, uuid);
    if (mock1 != null)
    {
      request.setAttribute("examalredyassigned", "yes");
      List mocktests = BOFactory.getMockTestBO().getAllMockTestsByApplicant(applicantform.getApplicantId(), applicantform.getUuid());
      
      List mockCatList = BOFactory.getMockTestBO().getAllMockCategoryList();
      applicantform.setMockCatList(mockCatList);
      applicantform.setMocktestList(mocktests);
    }
    else
    {
      MockTest mocktest = new MockTest();
      mocktest.setApplicantId(new Long(applicantId).longValue());
      mocktest.setApplicantName(applicant.getFullName());
      MockQuestionSet cat = new MockQuestionSet();
      cat.setCatId(applicantform.getMockcatId());
      mocktest.setCat(cat);
      mocktest.setUuid(uuid);
      mocktest.setCreatedBy(user1.getUserName());
      mocktest.setCreatedDate(new Date());
      mocktest.setPassPercentage(applicantform.getPassPercentage());
      mocktest.setResult("ASSIGNED");
      BOFactory.getMockTestBO().assignExamSubmit(applicantform, mocktest, applicantform.getComment(), user1);
      

      List mocktestList = BOFactory.getMockTestBO().getAllMockTestsByApplicant(applicantform.getApplicantId(), applicantform.getUuid());
      

      applicantform.setMocktestList(mocktestList);
      


      String[] to = { applicant.getEmail() };
      
      String[] bcc = null;
      



      List cclist = BOFactory.getApplicantBO().getCCListForApplicant(user1, applicant, null, EmailNotificationSettingFunction.FunctionNames.EXAM_SCHEDULE_TO_APPLICANT.toString(), true);
      
      String[] cc = new String[cclist.size()];
      cclist.toArray(cc);
      


      String replyTo = "\"" + user1.getFirstName() + " " + user1.getLastName() + "\"" + " " + "<" + user1.getEmailId() + ">";
      


      EmailTask emailtask = new EmailTask(replyTo, to, cc, bcc, replyTo, "dummysubject", null, "dummybody", null, 0, null);
      

      emailtask.setMocktest(mocktest);
      emailtask.setUser(user1);
      emailtask.setComment(applicantform.getComment());
      emailtask.setFunctionType(Common.EXAM_SCHEDULE);
      EmailTaskManager.sendEmail(emailtask);
      
      request.setAttribute("assignExam", "yes");
    }
    List questiongrpList = BOFactory.getQuestionBO().getAllquestionsGroupDetails(user1);
    List assignedquestionaireList = BOFactory.getQuestionBO().getAllquestionsGroupDetailsByApplicantId(new Long(applicantId).longValue());
    applicantform.setQuestionGroupList(questiongrpList);
    applicantform.setQuetionnaireList(assignedquestionaireList);
    applicantform.setApplicantId(new Long(applicantId).longValue());
    applicantform.setUuid(uuid);
    
    return mapping.findForward("assignExams");
  }
  
  public ActionForward resendQuestionnaire(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside resendQuestionnaire method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantForm applicantform = (ApplicantForm)form;
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("uuid");
    String qnsGrpAppId = request.getParameter("gpid");
    
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    QuestionGroupApplicants qngrpapp = BOFactory.getQuestionBO().getQuestionGroupApplicants(new Long(qnsGrpAppId).longValue(), new Long(applicantId).longValue(), uuid);
    



    String[] to = { applicant.getEmail() };
    



    List cclist = BOFactory.getApplicantBO().getCCListForApplicant(user1, applicant, null, EmailNotificationSettingFunction.FunctionNames.QUESTIONANIRE_SUBMIT_TO_APPLICANT.toString(), true);
    
    String[] cc = new String[cclist.size()];
    cclist.toArray(cc);
    
    String[] bcc = null;
    



    String replyTo = "\"" + user1.getFirstName() + " " + user1.getLastName() + "\"" + " " + "<" + user1.getEmailId() + ">";
    


    EmailTask emailtask = new EmailTask(replyTo, to, cc, bcc, replyTo, "dummysubject", null, "dummybody", null, 0, null);
    

    emailtask.setQuestionnaire(qngrpapp);
    emailtask.setUser(user1);
    emailtask.setComment(qngrpapp.getHeadingcomments());
    emailtask.setFunctionType(Common.QUESTIONNAIRE_SCHEDULE);
    EmailTaskManager.sendEmail(emailtask);
    
    List mocktestList = BOFactory.getMockTestBO().getAllMockTestsByApplicant(applicantform.getApplicantId(), applicantform.getUuid());
    applicantform.setMocktestList(mocktestList);
    List mockCatList = BOFactory.getMockTestBO().getAllMockCategoryList();
    applicantform.setMockCatList(mockCatList);
    List questiongrpList = BOFactory.getQuestionBO().getAllquestionsGroupDetails(user1);
    List assignedquestionaireList = BOFactory.getQuestionBO().getAllquestionsGroupDetailsByApplicantId(new Long(applicantId).longValue());
    applicantform.setQuestionGroupList(questiongrpList);
    applicantform.setQuetionnaireList(assignedquestionaireList);
    applicantform.setApplicantId(new Long(applicantId).longValue());
    applicantform.setUuid(uuid);
    request.setAttribute("tabselected2", "yes");
    request.setAttribute("resendQuestionnaire", "yes");
    
    return mapping.findForward("assignExams");
  }
  
  public ActionForward assignQuestionanireSubmit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside assignQuestionanireSubmit method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantForm applicantform = (ApplicantForm)form;
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("uuid");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    

    QuestionGroupApplicants questionanire1 = BOFactory.getMockTestBO().getQuestionNaire(applicantform.getQuetionnaireId(), applicantId, uuid);
    if (questionanire1 != null)
    {
      request.setAttribute("questionanirealredyassigned", "yes");
    }
    else
    {
      QuestionGroupApplicants questionanire = new QuestionGroupApplicants();
      questionanire.setApplicantId(new Long(applicantId).longValue());
      questionanire.setApplicantName(applicant.getFullName());
      QuestionGroups qgroup = new QuestionGroups();
      qgroup.setQuestiongroupId(applicantform.getQuetionnaireId());
      questionanire.setQuestiongroup(qgroup);
      questionanire.setUuid(uuid);
      questionanire.setCreatedBy(user1.getUserName());
      questionanire.setCreatedDate(new Date());
      questionanire.setResult("ASSIGNED");
      questionanire.setHeadingcomments(applicantform.getHeadingComment());
      BOFactory.getMockTestBO().assignQuestionnaireSubmit(applicantform, questionanire, applicantform.getHeadingComment(), user1);
      




      String[] to = { applicant.getEmail() };
      
      String[] bcc = null;
      



      List cclist = BOFactory.getApplicantBO().getCCListForApplicant(user1, applicant, null, EmailNotificationSettingFunction.FunctionNames.QUESTIONANIRE_SUBMIT_TO_APPLICANT.toString(), true);
      
      String[] cc = new String[cclist.size()];
      cclist.toArray(cc);
      
      String replyTo = "\"" + user1.getFirstName() + " " + user1.getLastName() + "\"" + " " + "<" + user1.getEmailId() + ">";
      


      EmailTask emailtask = new EmailTask(replyTo, to, cc, bcc, replyTo, "dummysubject", null, "dummybody", null, 0, null);
      

      emailtask.setQuestionnaire(questionanire);
      emailtask.setUser(user1);
      emailtask.setComment(applicantform.getHeadingComment());
      emailtask.setFunctionType(Common.QUESTIONNAIRE_SCHEDULE);
      EmailTaskManager.sendEmail(emailtask);
      
      request.setAttribute("assignquestionanire", "yes");
    }
    List mocktestList = BOFactory.getMockTestBO().getAllMockTestsByApplicant(applicantform.getApplicantId(), applicantform.getUuid());
    applicantform.setMocktestList(mocktestList);
    List mockCatList = BOFactory.getMockTestBO().getAllMockCategoryList();
    applicantform.setMockCatList(mockCatList);
    List questiongrpList = BOFactory.getQuestionBO().getAllquestionsGroupDetails(user1);
    List assignedquestionaireList = BOFactory.getQuestionBO().getAllquestionsGroupDetailsByApplicantId(new Long(applicantId).longValue());
    applicantform.setQuestionGroupList(questiongrpList);
    applicantform.setQuetionnaireList(assignedquestionaireList);
    applicantform.setApplicantId(new Long(applicantId).longValue());
    applicantform.setUuid(uuid);
    request.setAttribute("tabselected2", "yes");
    return mapping.findForward("assignExams");
  }
  
  public ActionForward markresignedsave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside markresignedsave method");
    String applicantId = request.getParameter("applicantId");
    String resigneddate = request.getParameter("resigneddate");
    User user1 = (User)request.getSession().getAttribute("user_data");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetails(applicantId);
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantForm applicantform = (ApplicantForm)form;
    
    applicant.setResignedcomment(applicantform.getResignedcomment());
    Calendar cal = null;
    if (!StringUtils.isNullOrEmpty(resigneddate))
    {
      String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
      
      String converteddate = DateUtil.convertFromTimezoneToTimezoneDate(resigneddate, datepattern, user1.getTimezone().getTimezoneCode(), TimeZone.getDefault().getID());
      


      logger.info("converteddate" + converteddate);
      
      cal = DateUtil.convertStringDateToCalendar(converteddate, datepattern);
      

      applicant.setResigneddate(cal.getTime());
    }
    applicant.setUpdatedBy(user1.getUserName());
    applicant.setUpdatedDate(new Date());
    applicant.setInterviewState("RESIGNED");
    applicant.setResignedactionby(user1.getUserName());
    applicant.setResignedactionDate(new Date());
    applicant.setIsRegignationSchedularProcessed("N");
    

    applicant = BOFactory.getApplicantTXBO().markresigned(applicant, user1);
    
    request.setAttribute("markresigned", "yes");
    return mapping.findForward("markresignedscr");
  }
  
  public ActionForward onboardsave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside onboardsave method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    try
    {
      String applicantId = request.getParameter("applicantId");
      String joineddate = request.getParameter("joineddate");
      

      ActionErrors errors = new ActionErrors();
      ActionForward forward = new ActionForward();
      ApplicantForm applicantform = (ApplicantForm)form;
      

      Calendar cal = null;
      if (!StringUtils.isNullOrEmpty(joineddate))
      {
        String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
        
        String converteddate = DateUtil.convertFromTimezoneToTimezoneDate(joineddate, datepattern, user1.getTimezone().getTimezoneCode(), TimeZone.getDefault().getID());
        


        logger.info("converteddate" + converteddate);
        
        cal = DateUtil.convertStringDateToCalendar(converteddate, datepattern);
      }
      BOFactory.getApplicantTXBO().onBoardJoined(applicantId, user1, applicantform.getJoinedcomment(), cal);
      



      request.setAttribute("onboarddone", "yes");
      
      return mapping.findForward("onboard");
    }
    catch (Exception e)
    {
      String msg = Constant.getResourceStringValue("server.error", user1.getLocale());
      if (e.getMessage() == null) {
        request.setAttribute(Common.ERROR_MSG, msg);
      } else {
        request.setAttribute(Common.ERROR_MSG, e.getMessage());
      }
    }
    return mapping.findForward("exception");
  }
  
  public ActionForward canceloffersubmit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside canceloffersubmit method");
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantForm applicantform = (ApplicantForm)form;
    String applicantId = request.getParameter("applicantId");
    User user1 = (User)request.getSession().getAttribute("user_data");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetails(applicantId);
    
    applicant.setOffercancelledby(user1.getUserName());
    applicant.setOffercancelleddate(new Date());
    applicant.setOffercancelledresonid(applicantform.getOffercancelledresonid());
    
    applicant.setOffercancelledcomment(applicantform.getOffercancelledcomment());
    
    applicant.setIsoffercancelemailsent("N");
    applicant.setInterviewState("Offer Canceled");
    
    applicantform.fromValue(applicant, request);
    

    JobRequisition jobreq = BOFactory.getJobRequistionBO().getJobRequision(String.valueOf(applicant.getReqId()));
    
    jobreq.setNumberOfOpeningRemain(jobreq.getNumberOfOpeningRemain() + 1);
    if ((jobreq.getStatus().equals("Closed")) && (jobreq.getNumberOfOpeningRemain() > 0)) {
      jobreq.setStatus("Open");
    }
    ApplicationContext appContext = AppContextUtil.getAppcontext();
    ApplicantTXBO apptxbo = (ApplicantTXBO)appContext.getBean("apptxBoProxy");
    
    apptxbo.cancelOffer(applicant, jobreq, user1);
    

    List cancelresonlist = BOFactory.getLovBO().getAllOfferCancelResons();
    applicantform.setOfferdeclinedreasonslist(cancelresonlist);
    
    request.setAttribute("offercancelled", "yes");
    return mapping.findForward("canceloffer");
  }
  
  public ActionForward releasepfferattachment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside pfferattachment method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    String applicantId = request.getParameter("applicantId");
    String watchlistids = request.getParameter("watchlistids");
    String watchlistnames = request.getParameter("watchlistnames");
    String emailtemplateid = request.getParameter("emailtemplateid");
    
    String emailtemplatename = request.getParameter("emailtemplatename");
    
    String filedetails = request.getParameter("filedetails");
    
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetails(applicantId);
    

    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantForm applicantform = (ApplicantForm)form;
    List watchList = new ArrayList();
    if ((!StringUtils.isNullOrEmpty(watchlistids)) && (!StringUtils.isNullOrEmpty(watchlistnames)))
    {
      watchlistids = watchlistids.substring(0, watchlistids.length() - 1);
      watchlistnames = watchlistnames.substring(0, watchlistnames.length() - 1);
      
      logger.info("Watch list ids ids next" + watchlistids);
      logger.info("Watch list name next" + watchlistnames);
      String[] ids = StringUtils.tokenize(watchlistids, ",");
      String[] usernames = StringUtils.tokenize(watchlistnames, ",");
      if (ids != null) {
        for (int i = 0; i < ids.length; i++)
        {
          OfferWatchList ch = new OfferWatchList();
          ch.setUserId(new Long(ids[i]).longValue());
          ch.setRequisitionId(applicant.getReqId());
          ch.setApplicantId(applicant.getApplicantId());
          ch.setUsername(usernames[i]);
          ch.setCreatedBy(user1.getUserName());
          ch.setCreatedDate(new Date());
          ch.setFunctionType("release_offer");
          watchList.add(ch);
        }
      }
    }
    applicantform.setOfferwatchList(watchList);
    
    applicantform.setOfferReleaseComment(applicantform.getOfferReleaseComment());
    



    FormFile myFile = applicantform.getAttachmentdata();
    int limitfileSize = new Integer(Constant.getValue("attachment.file.size.limit")).intValue();
    if (myFile != null)
    {
      ApplicantOfferAttachment appattach = new ApplicantOfferAttachment();
      
      String contentType = myFile.getContentType();
      String fileName = myFile.getFileName();
      int fileSize = myFile.getFileSize();
      byte[] fileData = myFile.getFileData();
      if (fileSize > limitfileSize)
      {
        request.setAttribute("attachmentsizeexceed", "yes");
      }
      else if (!StringUtils.isNullOrEmpty(fileName))
      {
        appattach.setAttahmentname(fileName);
        Blob blob = null;
        
        blob = new SerialBlob(fileData);
        



        appattach.setApplicantId(new Long(applicantId).longValue());
        appattach.setCreatedBy(user1.getUserName());
        appattach.setCreatedDate(new Date());
        appattach.setType("reloffer");
        appattach.setUuid(UUID.randomUUID().toString());
        appattach.setAttahmentdetails(filedetails);
        BOFactory.getApplicantBO().saveOfferAttachment(appattach, applicant.getUuid(), user1.getUserId(), user1.getFirstName() + " " + user1.getLastName(), Common.APPLICANT_CREATED_BY_TYPE_INTERNAL);
        
        String filePath = Constant.getValue("ATTACHMENT_PATH");
        filePath = filePath + File.separator + "offerAttachment" + File.separator + appattach.getUuid() + File.separator;
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
    List attachmentList = BOFactory.getApplicantBO().getOfferAttachmentList(new Long(applicantId).longValue(), "reloffer");
    

    applicantform.setOfferAttachmentList(attachmentList);
    
    applicantform.fromValue(applicant, request);
    
    applicantform.setOfferedctc(applicant.getOfferedctc());
    applicantform.setOfferedctccurrencycode(applicant.getOfferedctccurrencycode());
    if (!StringUtils.isNullOrEmpty(emailtemplateid)) {
      applicantform.setEmailtemplateid(new Long(emailtemplateid).longValue());
    }
    applicantform.setEmailtemplate(emailtemplatename);
    
    JobRequisition jb = BOFactory.getJobRequistionBO().getJobRequision(String.valueOf(applicant.getReqId()));
    
    int totalfilledposition = BOFactory.getApplicantBO().getTotalNoOfFilledPositionByReqId(jb.getJobreqId());
    if (totalfilledposition >= jb.getNumberOfOpening()) {
      request.setAttribute("numberofpositionfilledexceeds", "yes");
    }
    request.setAttribute("offerreleased", "no");
    return mapping.findForward("releaseofferscr");
  }
  
  public ActionForward releaseoffer(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside releaseoffer method");
    String applicantId = request.getParameter("applicantId");
    String watchlistids = request.getParameter("watchlistids");
    String watchlistnames = request.getParameter("watchlistnames");
    String emailtemplateid = request.getParameter("emailtemplateid");
    
    String emailtemplatename = request.getParameter("emailtemplatename");
    
    String filedetails = request.getParameter("filedetails");
    User user1 = (User)request.getSession().getAttribute("user_data");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetails(applicantId);
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantForm applicantform = (ApplicantForm)form;
    
    applicant.setOfferReleaseComment(applicantform.getOfferReleaseComment());
    
    applicant.setOfferreleasedate(new Date());
    applicant.setOfferreleaseddby(user1.getUserName());
    applicant.setInterviewState("Offer Released");
    applicant.setIsOfferEmailSent("N");
    if (!StringUtils.isNullOrEmpty(emailtemplateid)) {
      applicant.setEmailtemplateid(new Long(emailtemplateid).longValue());
    }
    applicant.setEmailtemplate(emailtemplatename);
    


    FormFile myFile = applicantform.getAttachmentdata();
    ApplicantOfferAttachment appattach = null;
    int limitfileSize = new Integer(Constant.getValue("attachment.file.size.limit")).intValue();
    boolean isattachmentsizeexceed = false;
    if (myFile != null)
    {
      appattach = new ApplicantOfferAttachment();
      
      String contentType = myFile.getContentType();
      String fileName = myFile.getFileName();
      int fileSize = myFile.getFileSize();
      byte[] fileData = myFile.getFileData();
      if (fileSize > limitfileSize)
      {
        request.setAttribute("attachmentsizeexceed", "yes");
        isattachmentsizeexceed = true;
      }
      else if (!StringUtils.isNullOrEmpty(fileName))
      {
        appattach.setAttahmentname(fileName);
        Blob blob = null;
        
        blob = new SerialBlob(fileData);
        



        appattach.setApplicantId(new Long(applicantId).longValue());
        appattach.setCreatedBy(user1.getUserName());
        appattach.setCreatedDate(new Date());
        appattach.setType("reloffer");
        appattach.setUuid(UUID.randomUUID().toString());
        appattach.setAttahmentdetails(filedetails);
        BOFactory.getApplicantBO().saveOfferAttachment(appattach, applicant.getUuid(), user1.getUserId(), user1.getFirstName() + " " + user1.getLastName(), Common.APPLICANT_CREATED_BY_TYPE_INTERNAL);
        

        String filePath = Constant.getValue("ATTACHMENT_PATH");
        filePath = filePath + File.separator + "offerAttachment" + File.separator + appattach.getUuid() + File.separator;
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
    JobRequisition jobreq = BOFactory.getJobRequistionBO().getJobRequision(String.valueOf(applicant.getReqId()));
    if (jobreq.getNumberOfOpeningRemain() - 1 > 0) {
      jobreq.setNumberOfOpeningRemain(jobreq.getNumberOfOpeningRemain() - 1);
    } else {
      jobreq.setNumberOfOpeningRemain(0);
    }
    if ((!StringUtils.isNullOrEmpty(Constant.getValue("auto.close.requisition.on.position.filled"))) && (Constant.getValue("auto.close.requisition.on.position.filled").equalsIgnoreCase("yes"))) {
      if (jobreq.getNumberOfOpeningRemain() == 0)
      {
        jobreq.setStatus("Closed");
        jobreq.setClosedDate(new Date());
        jobreq.setClosedByName("system");
      }
    }
    List watchList = new ArrayList();
    if ((!StringUtils.isNullOrEmpty(watchlistids)) && (!StringUtils.isNullOrEmpty(watchlistnames)))
    {
      watchlistids = watchlistids.substring(0, watchlistids.length() - 1);
      watchlistnames = watchlistnames.substring(0, watchlistnames.length() - 1);
      
      logger.info("Watch list ids ids next" + watchlistids);
      logger.info("Watch list name next" + watchlistnames);
      String[] ids = StringUtils.tokenize(watchlistids, ",");
      String[] usernames = StringUtils.tokenize(watchlistnames, ",");
      if (ids != null) {
        for (int i = 0; i < ids.length; i++)
        {
          OfferWatchList ch = new OfferWatchList();
          ch.setUserId(new Long(ids[i]).longValue());
          ch.setRequisitionId(applicant.getReqId());
          ch.setApplicantId(applicant.getApplicantId());
          ch.setUsername(usernames[i]);
          ch.setCreatedBy(user1.getUserName());
          ch.setCreatedDate(new Date());
          ch.setFunctionType("release_offer");
          watchList.add(ch);
        }
      }
    }
    if (!isattachmentsizeexceed)
    {
      try
      {
        BOFactory.getApplicantTXBO().releaseoffer(applicant, jobreq, watchList, appattach, user1);
        request.setAttribute("offerreleased", "yes");
      }
      catch (Exception e)
      {
        logger.info("Exception on relaseoffer", e);
        List attachmentList = BOFactory.getApplicantBO().getOfferAttachmentList(new Long(applicantId).longValue(), "reloffer");
        

        applicantform.setOfferAttachmentList(attachmentList);
        applicantform.fromValue(applicant, request);
        applicantform.setOfferwatchList(watchList);
        

        applicantform.setSalaryPlan(jobreq.getSalaryplan());
        
        request.setAttribute("offerreleased", "no");
        request.setAttribute("errormessage", e.getMessage());
      }
    }
    else
    {
      List attachmentList = BOFactory.getApplicantBO().getOfferAttachmentList(new Long(applicantId).longValue(), "reloffer");
      

      applicantform.setOfferAttachmentList(attachmentList);
      applicantform.fromValue(applicant, request);
      applicantform.setOfferwatchList(watchList);
      

      applicantform.setSalaryPlan(jobreq.getSalaryplan());
      
      request.setAttribute("offerreleased", "no");
    }
    return mapping.findForward("releaseofferscr");
  }
  
  public ActionForward initiateoffer(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside initiateoffer method");
    String applicantId = request.getParameter("applicantId");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetails(applicantId);
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantForm applicantform = (ApplicantForm)form;
    applicantform.fromValue(applicant, request);
    

    List attachmentList = BOFactory.getApplicantBO().getOfferAttachmentList(new Long(applicantId).longValue(), "initoffer");
    

    applicantform.setOfferAttachmentList(attachmentList);
    
    List otherBenefitsList = BOFactory.getApplicantBO().getOtherBenefitsList(new Long(applicantId).longValue());
    
    applicantform.setOtherBenefitsList(otherBenefitsList);
    


    JobRequisition jb = BOFactory.getJobRequistionBO().getJobRequision(String.valueOf(applicant.getReqId()));
    
    applicantform.setSalaryPlan(jb.getSalaryplan());
    






    long orgId = jb.getOrganization().getOrgId();
    long deptId = 0L;
    if (jb.getDepartment() != null) {
      deptId = jb.getDepartment().getDepartmentId();
    }
    SystemRule sysrule = RuleDAO.getOrganizationRuleByType(orgId, deptId, Common.SYSTEM_RULE_OFFER_APPROVER);
    
    int levelorder = 0;
    List newapproverList = new ArrayList();
    if (sysrule != null)
    {
      List approvers = sysrule.getRuleUsers();
      logger.info("Inside initiateoffer method approvers.size" + approvers.size());
      for (int i = 0; i < approvers.size(); i++)
      {
        SystemRuleUser sysuser = (SystemRuleUser)approvers.get(i);
        logger.info("Inside initiateoffer method" + sysuser.getIsGroup());
        if ((sysuser != null) && (sysuser.getIsGroup().equals("Y")))
        {
          if ((sysuser.getUserGroup() != null) && (sysuser.getUserGroup().getStatus().equals("A")))
          {
            OfferApprover jbapp = new OfferApprover();
            jbapp.setApproverId(sysuser.getUserGroup().getUsergrpId());
            
            jbapp.setApplicantId(applicant.getApplicantId());
            jbapp.setApproverName(sysuser.getUserGroup().getUsergrpName());
            
            jbapp.setIsFromSystemRule("Y");
            jbapp.setIsapprover("Y");
            jbapp.setIsGroup("Y");
            levelorder += 1;
            jbapp.setLevelorder(levelorder);
            newapproverList.add(jbapp);
          }
        }
        else if ((sysuser.getUser() != null) && (sysuser.getUser().getStatus().equals("A")))
        {
          OfferApprover jbapp = new OfferApprover();
          jbapp.setApproverId(sysuser.getUser().getUserId());
          jbapp.setApplicantId(applicant.getApplicantId());
          jbapp.setApproverName(sysuser.getUser().getFirstName() + " " + sysuser.getUser().getLastName());
          
          jbapp.setIsFromSystemRule("Y");
          jbapp.setIsapprover("Y");
          jbapp.setIsGroup("N");
          levelorder += 1;
          jbapp.setLevelorder(levelorder);
          newapproverList.add(jbapp);
        }
      }
    }
    applicantform.setOfferApproverList(newapproverList);
    
    applicantform.setOfferownerId(applicant.getOfferownerId());
    applicantform.setOfferownerName(applicant.getOfferownerName());
    String currencycode = jb.getOrganization().getCurrencyCode();
    
    applicantform.setCurrectctc(applicant.getCurrectctc());
    applicantform.setExpectedctc(applicant.getExpectedctc());
    applicantform.setCurrectctccurrencycode(currencycode);
    applicantform.setExpectedctccurrencycode(currencycode);
    applicantform.setOfferedctccurrencycode(currencycode);
    applicantform.setOfferInitiationComment(applicant.getOfferInitiationComment());
    if (applicant.getOfferedctc() != null) {
      applicantform.setOfferedctc(applicant.getOfferedctc());
    }
    if (applicant.getOfferedctccurrencycode() != null) {
      applicantform.setOfferedctccurrencycode(applicant.getOfferedctccurrencycode());
    }
    return mapping.findForward("initiateoffer");
  }
  
  public ActionForward applicantofferdetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside applicantofferdetails method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    String applicantId = request.getParameter("applicantId");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetails(applicantId);
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantForm applicantform = (ApplicantForm)form;
    applicantform.fromValue(applicant, request);
    
    applicantform.setOfferownerId(applicant.getOfferownerId());
    applicantform.setOfferownerName(applicant.getOfferownerName());
    
    applicantform.setCurrectctc(applicant.getCurrectctc());
    applicantform.setExpectedctc(applicant.getExpectedctc());
    applicantform.setCurrectctccurrencycode(applicant.getCurrectctccurrencycode());
    
    applicantform.setExpectedctccurrencycode(applicant.getExpectedctccurrencycode());
    

    applicantform.setOfferInitiationComment(applicant.getOfferInitiationComment());
    
    applicantform.setOfferinitiationdate(applicant.getOfferinitiationdate());
    
    applicantform.setOfferinitiatedby(applicant.getOfferinitiatedby());
    
    applicantform.setInterviewState(applicant.getInterviewState());
    
    List approverlist = BOFactory.getApplicantBO().getOfferApproverList(applicant.getApplicantId());
    

    applicantform.setOfferApproverList(approverlist);
    
    List attachmentList = BOFactory.getApplicantBO().getOfferAttachmentList(new Long(applicantId).longValue(), "initoffer");
    

    applicantform.setOfferAttachmentList(attachmentList);
    
    List offerreleaseattachmentList = BOFactory.getApplicantBO().getOfferAttachmentList(new Long(applicantId).longValue(), "reloffer");
    

    applicantform.setOfferReleaseAttachmentList(offerreleaseattachmentList);
    
    List offerwatchlist = BOFactory.getApplicantBO().getOfferwatchListByApplicantId(applicant.getApplicantId(), "release_offer");
    


    applicantform.setOfferwatchList(offerwatchlist);
    if (applicant.getOfferedctc() != null) {
      applicantform.setOfferedctc(applicant.getOfferedctc());
    }
    if (applicant.getOfferedctccurrencycode() != null) {
      applicantform.setOfferedctccurrencycode(applicant.getOfferedctccurrencycode());
    }
    logger.info("finish applicantofferdetails");
    
    return mapping.findForward("applicantofferdetails");
  }
  
  public ActionForward initiateoffersubmit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside initiateoffersubmit method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    String applicantId = request.getParameter("applicantId");
    String targerofferdate = request.getParameter("targerofferdate");
    
    String offerownerId = request.getParameter("offerownerId");
    String offerownerName = request.getParameter("offerownerName");
    String isgroup = request.getParameter("isgroup");
    String targetjoiningdate = request.getParameter("targetjoiningdate");
    
    String watchlistids = request.getParameter("watchlistids");
    String watchlistnames = request.getParameter("watchlistnames");
    
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetails(applicantId);
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantForm applicantform = (ApplicantForm)form;
    if (!StringUtils.isNullOrEmpty(targerofferdate))
    {
      String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
      
      String converteddate = DateUtil.convertFromTimezoneToTimezoneDate(targerofferdate, datepattern, user1.getTimezone().getTimezoneCode(), TimeZone.getDefault().getID());
      


      logger.info("converteddate ... " + converteddate);
      
      Calendar cal = DateUtil.convertStringDateToCalendar(converteddate, datepattern);
      

      applicant.setTargerofferdate(cal.getTime());
    }
    if (!StringUtils.isNullOrEmpty(targetjoiningdate))
    {
      String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
      
      String converteddate = DateUtil.convertFromTimezoneToTimezoneDate(targetjoiningdate, datepattern, user1.getTimezone().getTimezoneCode(), TimeZone.getDefault().getID());
      


      logger.info("converteddate" + converteddate);
      
      Calendar cal = DateUtil.convertStringDateToCalendar(converteddate, datepattern);
      

      applicant.setTargetjoiningdate(cal.getTime());
    }
    applicant.setOfferownerId(new Long(offerownerId).longValue());
    applicant.setOfferownerName(offerownerName);
    if ((!StringUtils.isNullOrEmpty(isgroup)) && (isgroup.equals("Y"))) {
      applicant.setIsofferownerGroup("Y");
    } else {
      applicant.setIsofferownerGroup("N");
    }
    String currencycode = BOFactory.getJobRequistionBO().getCurrencyCodeByReqId(applicant.getReqId());
    
    applicant.setCurrectctc(applicantform.getCurrectctc() == null ? "" : applicantform.getCurrectctc());
    
    applicant.setExpectedctc(applicantform.getExpectedctc() == null ? "" : applicantform.getExpectedctc());
    
    applicant.setCurrectctccurrencycode(currencycode == null ? "" : currencycode);
    
    applicant.setExpectedctccurrencycode(currencycode == null ? "" : currencycode);
    

    applicant.setOfferedctc(applicantform.getOfferedctc() == null ? "" : applicantform.getOfferedctc());
    
    applicant.setOfferedctccurrencycode(currencycode == null ? "" : currencycode);
    

    applicant.setOfferInitiationComment(applicantform.getOfferInitiationComment() == null ? "" : applicantform.getOfferInitiationComment());
    

    applicant.setOfferinitiationdate(new Date());
    applicant.setOfferinitiatedby(user1.getUserName());
    


    boolean isError = false;
    FormFile myFile = applicantform.getAttachmentdata();
    ApplicantOfferAttachment appattach = null;
    int limitfileSize = new Integer(Constant.getValue("attachment.file.size.limit")).intValue();
    boolean isattachmentsizeexceed = false;
    if (myFile != null)
    {
      String contentType = myFile.getContentType();
      String fileName = myFile.getFileName();
      int fileSize = myFile.getFileSize();
      byte[] fileData = myFile.getFileData();
      if (fileSize > limitfileSize)
      {
        request.setAttribute("attachmentsizeexceed", "yes");
        isattachmentsizeexceed = true;
      }
      else if (!StringUtils.isNullOrEmpty(fileName))
      {
        appattach = new ApplicantOfferAttachment();
        appattach.setAttahmentname(fileName);
        Blob blob = null;
        
        blob = new SerialBlob(fileData);
        



        appattach.setApplicantId(new Long(applicantId).longValue());
        appattach.setCreatedBy(user1.getUserName());
        appattach.setCreatedDate(new Date());
        appattach.setType("initoffer");
        appattach.setUuid(UUID.randomUUID().toString());
        


        String filePath = Constant.getValue("ATTACHMENT_PATH");
        filePath = filePath + File.separator + "offerAttachment" + File.separator + appattach.getUuid() + File.separator;
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
    List approverslist = new ArrayList();
    setApproversList(applicant.getApplicantId(), approverslist, request);
    
    List benefitList = new ArrayList();
    setOtherBenefitsList(applicant.getApplicantId(), benefitList, request, applicant.getOfferedctccurrencycode());
    



    String isCTCValidation = Constant.getValue("approval.required.ctc.morethan.salaryplan");
    if ((!StringUtils.isNullOrEmpty(isCTCValidation)) && (isCTCValidation.equalsIgnoreCase("yes")))
    {
      JobRequisition jb = BOFactory.getJobRequistionBO().getJobRequision(String.valueOf(applicant.getReqId()));
      

      float offeredctc = 0.0F;
      if (StringUtils.isNullOrEmpty(applicantform.getOfferedctc())) {
        offeredctc = 0.0F;
      } else {
        offeredctc = new Float(applicantform.getOfferedctc()).floatValue();
      }
      if ((jb.getSalaryplan() != null) && ((float)jb.getSalaryplan().getToRangeAmount() < offeredctc) && (approverslist.size() < 1)) {
        isError = true;
      }
      if (isError)
      {
        applicantform.setSalaryPlan(jb.getSalaryplan());
        request.setAttribute("ctcexeedssalaryplan", "yes");
      }
    }
    if ((isError) || (isattachmentsizeexceed))
    {
      List attachmentList = BOFactory.getApplicantBO().getOfferAttachmentList(new Long(applicantId).longValue(), "initoffer");
      

      applicantform.setOfferAttachmentList(attachmentList);
      
      applicantform.fromValue(applicant, request);
      applicantform.setOfferApproverList(approverslist);
      applicantform.setOtherBenefitsList(benefitList);
      

      applicantform.setOfferedctc(applicantform.getOfferedctc());
      applicantform.setOfferedctccurrencycode(currencycode);
      applicantform.setOfferownerId(new Long(offerownerId).longValue());
      applicantform.setOfferownerName(offerownerName);
      if ((!StringUtils.isNullOrEmpty(isgroup)) && (isgroup.equals("Y"))) {
        applicantform.setIsofferownerGroup("Y");
      } else {
        applicantform.setIsofferownerGroup("N");
      }
      applicantform.setCurrectctc(applicantform.getCurrectctc());
      applicantform.setExpectedctc(applicantform.getExpectedctc());
      applicantform.setCurrectctccurrencycode(currencycode);
      applicantform.setExpectedctccurrencycode(currencycode);
      
      applicantform.setOfferInitiationComment(applicantform.getOfferInitiationComment());
      
      applicantform.setTargerofferdate(targerofferdate);
      applicantform.setTargetjoiningdate(targetjoiningdate);
      
      request.setAttribute("isofferinitiated", "no");
      return mapping.findForward("initiateoffer");
    }
    applicant.setIsInitiateOfferAttachmentCopied("N");
    

    BOFactory.getApplicantTXBO().initiateoffer(applicant, approverslist, benefitList, new Long(offerownerId).longValue(), user1, appattach);
    try
    {
      OfferApprover offerapprover = BOFactory.getApplicantBO().getNextOfferApprover(applicant.getApplicantId(), 0);
      



      List interviewerlist = new ArrayList();
      if ((!StringUtils.isNullOrEmpty(offerapprover.getIsGroup())) && (offerapprover.getIsGroup().equals("Y")))
      {
        UserGroup usrgrp = BOFactory.getLovBO().getUserGroup(offerapprover.getApproverId());
        
        applicant.setApplicantReviewerName(usrgrp.getUsergrpName());
        Set users = usrgrp.getUsers();
        if ((users != null) && (users.size() > 0))
        {
          Iterator itr = users.iterator();
          while (itr.hasNext())
          {
            User usr = (User)itr.next();
            String toemailid = "\"" + usr.getFirstName() + " " + usr.getLastName() + "\"" + " " + "<" + usr.getEmailId() + ">";
            

            interviewerlist.add(toemailid);
          }
        }
      }
      else
      {
        User usr = UserDAO.getUserFullNameEmailAndUserId(offerapprover.getApproverId());
        
        String toemailid = "\"" + usr.getFirstName() + " " + usr.getLastName() + "\"" + " " + "<" + usr.getEmailId() + ">";
        

        interviewerlist.add(toemailid);
        applicant.setApplicantReviewerName(usr.getFirstName() + " " + usr.getLastName());
      }
      String[] to = new String[interviewerlist.size()];
      interviewerlist.toArray(to);
      



      List cclist = BOFactory.getApplicantBO().getCCListForApplicant(user1, applicant, null, EmailNotificationSettingFunction.FunctionNames.OFFER_APPROVE_REJECT.toString(), true);
      if ((!StringUtils.isEmpty(applicant.getIsofferownerGroup())) && (applicant.getIsofferownerGroup().equals("Y")))
      {
        long groupId = 0L;
        
        UserGroup usrgrp = BOFactory.getLovBO().getUserGroup(applicant.getOfferownerId());
        if (usrgrp != null)
        {
          Set users = usrgrp.getUsers();
          if ((users != null) && (users.size() > 0))
          {
            Iterator itr = users.iterator();
            while (itr.hasNext())
            {
              User touser = (User)itr.next();
              cclist.add(touser.getEmailId());
            }
          }
        }
      }
      else
      {
        User usr = BOFactory.getLovBO().getUserFullNameEmailAndUserIdById(applicant.getOfferownerId());
        if (usr != null) {
          cclist.add(usr.getEmailId());
        }
      }
      String[] cc = new String[cclist.size()];
      cclist.toArray(cc);
      
      String[] bcc = null;
      


      String subject = "";
      
      String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
      

      String targetofferdateconverted = DateUtil.convertDateToStringDate(applicant.getTargerofferdate(), datepattern);
      
      String offerinitiationdateConverted = DateUtil.convertSourceToTargetTimezone(applicant.getOfferinitiationdate(), user1.getTimezone().getTimezoneCode(), user1.getLocale());
      



      applicant.setOffertargetdateConverted(targetofferdateconverted + " " + user1.getTimezone().getTimezoneCode());
      
      applicant.setOfferinitiationdateConverted(offerinitiationdateConverted + " " + user1.getTimezone().getTimezoneCode());
      


      String pendingtaskurl = Constant.getValue("external.url") + "task.do?method=mypendingtasks";
      
      applicant.setMypendingTaskUrl(pendingtaskurl);
      
      String from = "\"" + user1.getFirstName() + " " + user1.getLastName() + "\"" + " " + "<" + user1.getEmailId() + ">";
      

      String applicanturl = Constant.getValue("external.url") + "applicant.do?method=applicantDetails&applicantId=" + applicant.getApplicantId() + "&secureid=" + applicant.getUuid();
      


      applicant.setApplicantUrl(applicanturl);
      String comment = applicant.getOfferInitiationComment() == null ? "" : applicant.getOfferInitiationComment();
      
      applicant.setOfferInitiationComment(comment);
      applicant.setOfferinitiatedbyName(user1.getFirstName() + " " + user1.getLastName());
      
      EmailTask emailtask = new EmailTask(from, to, cc, bcc, from, "dummysubject", null, "dummybody", null, 0, null);
      
      emailtask.setFunctionType(Common.OFFER_APPROVAL_EMAIL_COMPONENT);
      emailtask.setUser(user1);
      emailtask.setApplicant(applicant);
      EmailTaskManager.sendEmail(emailtask);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      logger.info("Error sending email offer approval" + e);
      logger.error(e);
      request.setAttribute("errorsendingemail", "yes");
    }
    applicantform.fromValue(applicant, request);
    

    request.setAttribute("isofferinitiated", "yes");
    return mapping.findForward("initiateoffer");
  }
  
  public ActionForward initiatepfferattachment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside initiatepfferattachment method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    String applicantId = request.getParameter("applicantId");
    String targerofferdate = request.getParameter("targerofferdate");
    
    String offerownerId = request.getParameter("offerownerId");
    String offerownerName = request.getParameter("offerownerName");
    String targetjoiningdate = request.getParameter("targetjoiningdate");
    
    String watchlistids = request.getParameter("watchlistids");
    String watchlistnames = request.getParameter("watchlistnames");
    String filedetails = request.getParameter("filedetails");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetails(applicantId);
    

    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantForm applicantform = (ApplicantForm)form;
    
    JobRequisition jb = BOFactory.getJobRequistionBO().getJobRequision(String.valueOf(applicant.getReqId()));
    
    applicantform.setSalaryPlan(jb.getSalaryplan());
    
    List watchList = new ArrayList();
    if ((!StringUtils.isNullOrEmpty(watchlistids)) && (!StringUtils.isNullOrEmpty(watchlistnames)))
    {
      watchlistids = watchlistids.substring(0, watchlistids.length() - 1);
      watchlistnames = watchlistnames.substring(0, watchlistnames.length() - 1);
      
      logger.info("Watch list ids ids next" + watchlistids);
      logger.info("Watch list name next" + watchlistnames);
      String[] ids = StringUtils.tokenize(watchlistids, ",");
      String[] usernames = StringUtils.tokenize(watchlistnames, ",");
      if (ids != null) {
        for (int i = 0; i < ids.length; i++)
        {
          OfferWatchList ch = new OfferWatchList();
          ch.setUserId(new Long(ids[i]).longValue());
          ch.setRequisitionId(applicant.getReqId());
          ch.setApplicantId(applicant.getApplicantId());
          ch.setUsername(usernames[i]);
          ch.setCreatedBy(user1.getUserName());
          ch.setCreatedDate(new Date());
          ch.setFunctionType("initiate_offer");
          watchList.add(ch);
        }
      }
    }
    applicantform.setInitiateOfferwatchList(watchList);
    
    FormFile myFile = applicantform.getAttachmentdata();
    int limitfileSize = new Integer(Constant.getValue("attachment.file.size.limit")).intValue();
    if (myFile != null)
    {
      String contentType = myFile.getContentType();
      String fileName = myFile.getFileName();
      int fileSize = myFile.getFileSize();
      byte[] fileData = myFile.getFileData();
      if (fileSize > limitfileSize)
      {
        request.setAttribute("attachmentsizeexceed", "yes");
      }
      else if (!StringUtils.isNullOrEmpty(fileName))
      {
        ApplicantOfferAttachment appattach = new ApplicantOfferAttachment();
        appattach.setAttahmentname(fileName);
        Blob blob = null;
        
        blob = new SerialBlob(fileData);
        



        appattach.setApplicantId(new Long(applicantId).longValue());
        appattach.setCreatedBy(user1.getUserName());
        appattach.setCreatedDate(new Date());
        appattach.setAttahmentdetails(filedetails);
        appattach.setType("initoffer");
        appattach.setUuid(UUID.randomUUID().toString());
        BOFactory.getApplicantBO().saveOfferAttachment(appattach, applicant.getUuid(), user1.getUserId(), user1.getFirstName() + " " + user1.getLastName(), Common.APPLICANT_CREATED_BY_TYPE_INTERNAL);
        

        String filePath = Constant.getValue("ATTACHMENT_PATH");
        filePath = filePath + File.separator + "offerAttachment" + File.separator + appattach.getUuid() + File.separator;
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
    List attachmentList = BOFactory.getApplicantBO().getOfferAttachmentList(new Long(applicantId).longValue(), "initoffer");
    

    applicantform.setOfferAttachmentList(attachmentList);
    
    List approverslist = new ArrayList();
    setApproversList(new Long(applicantId).longValue(), approverslist, request);
    

    String offeedctc = applicantform.getOfferedctc();
    String currentctc = applicantform.getCurrectctc();
    String expectedctc = applicantform.getExpectedctc();
    applicantform.fromValue(applicant, request);
    applicantform.setOfferApproverList(approverslist);
    
    String currencycode = jb.getOrganization().getCurrencyCode();
    applicantform.setOfferedctc(offeedctc);
    applicantform.setOfferedctccurrencycode(currencycode);
    applicantform.setOfferownerId(new Long(offerownerId).longValue());
    applicantform.setOfferownerName(offerownerName);
    
    applicantform.setCurrectctc(currentctc);
    applicantform.setExpectedctc(expectedctc);
    applicantform.setCurrectctccurrencycode(currencycode);
    applicantform.setExpectedctccurrencycode(currencycode);
    
    applicantform.setOfferInitiationComment(applicantform.getOfferInitiationComment());
    
    applicantform.setTargerofferdate(targerofferdate);
    applicantform.setTargetjoiningdate(targetjoiningdate);
    


    request.setAttribute("isofferinitiated", "no");
    return mapping.findForward("initiateoffer");
  }
  
  public ActionForward deleteattachmentrelatedtooffer(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteattachmentrelatedtooffer method");
    ApplicantForm applicantform = (ApplicantForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    String appuuid = request.getParameter("appuuid");
    String attachuuid = request.getParameter("attachuuid");
    
    BOFactory.getApplicantBO().deleteOfferAttachment(attachuuid, appuuid, user1);
    

    return null;
  }
  
  public ActionForward deleteattachment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteattachment method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("uuid");
    
    String targerofferdate = request.getParameter("targerofferdate");
    
    String offerownerId = request.getParameter("offerownerId");
    String offerownerName = request.getParameter("offerownerName");
    String targetjoiningdate = request.getParameter("targetjoiningdate");
    
    String watchlistids = request.getParameter("watchlistids");
    String watchlistnames = request.getParameter("watchlistnames");
    
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetails(applicantId);
    



    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantForm applicantform = (ApplicantForm)form;
    
    JobRequisition jb = BOFactory.getJobRequistionBO().getJobRequision(String.valueOf(applicant.getReqId()));
    
    applicantform.setSalaryPlan(jb.getSalaryplan());
    
    List watchList = new ArrayList();
    if ((!StringUtils.isNullOrEmpty(watchlistids)) && (!StringUtils.isNullOrEmpty(watchlistnames)))
    {
      watchlistids = watchlistids.substring(0, watchlistids.length() - 1);
      watchlistnames = watchlistnames.substring(0, watchlistnames.length() - 1);
      
      logger.info("Watch list ids ids next" + watchlistids);
      logger.info("Watch list name next" + watchlistnames);
      String[] ids = StringUtils.tokenize(watchlistids, ",");
      String[] usernames = StringUtils.tokenize(watchlistnames, ",");
      if (ids != null) {
        for (int i = 0; i < ids.length; i++)
        {
          OfferWatchList ch = new OfferWatchList();
          ch.setUserId(new Long(ids[i]).longValue());
          ch.setRequisitionId(applicant.getReqId());
          ch.setApplicantId(applicant.getApplicantId());
          ch.setUsername(usernames[i]);
          ch.setCreatedBy(user1.getUserName());
          ch.setCreatedDate(new Date());
          ch.setFunctionType("initiate_offer");
          watchList.add(ch);
        }
      }
    }
    applicantform.setInitiateOfferwatchList(watchList);
    


    BOFactory.getApplicantBO().deleteOfferAttachment(uuid, applicant.getUuid(), user1);
    
    List attachmentList = BOFactory.getApplicantBO().getOfferAttachmentList(new Long(applicantId).longValue(), "initoffer");
    

    applicantform.setOfferAttachmentList(attachmentList);
    
    List approverslist = new ArrayList();
    setApproversList(new Long(applicantId).longValue(), approverslist, request);
    

    applicantform.fromValue(applicant, request);
    applicantform.setOfferApproverList(approverslist);
    

    logger.info("applicantform.getOfferedctc()" + applicantform.getOfferedctc());
    
    logger.info("offerownerId" + offerownerId);
    
    applicantform.setOfferedctc(applicantform.getOfferedctc());
    applicantform.setOfferedctccurrencycode(jb.getOrganization().getCurrencyCode());
    applicantform.setOfferownerId(new Long(offerownerId).longValue());
    applicantform.setOfferownerName(offerownerName);
    
    applicantform.setCurrectctc(applicantform.getCurrectctc());
    applicantform.setExpectedctc(applicantform.getExpectedctc());
    applicantform.setCurrectctccurrencycode(jb.getOrganization().getCurrencyCode());
    applicantform.setExpectedctccurrencycode(jb.getOrganization().getCurrencyCode());
    
    applicantform.setOfferInitiationComment(applicantform.getOfferInitiationComment());
    
    applicantform.setTargerofferdate(targerofferdate);
    applicantform.setTargetjoiningdate(targetjoiningdate);
    applicantform.setOfferownerId(new Long(offerownerId).longValue());
    applicantform.setOfferownerName(offerownerName);
    logger.info("offerownerId last" + offerownerId);
    

    request.setAttribute("isofferinitiated", "no");
    return mapping.findForward("initiateoffer");
  }
  
  public ActionForward deleteattachmentofferrelease(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteattachmentofferrelease method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("uuid");
    
    String watchlistids = request.getParameter("watchlistids");
    String watchlistnames = request.getParameter("watchlistnames");
    String emailtemplateid = request.getParameter("emailtemplateid");
    
    String emailtemplatename = request.getParameter("emailtemplatename");
    

    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetails(applicantId);
    

    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantForm applicantform = (ApplicantForm)form;
    
    List watchList = new ArrayList();
    if ((!StringUtils.isNullOrEmpty(watchlistids)) && (!StringUtils.isNullOrEmpty(watchlistnames)))
    {
      watchlistids = watchlistids.substring(0, watchlistids.length() - 1);
      watchlistnames = watchlistnames.substring(0, watchlistnames.length() - 1);
      
      logger.info("Watch list ids ids next" + watchlistids);
      logger.info("Watch list name next" + watchlistnames);
      String[] ids = StringUtils.tokenize(watchlistids, ",");
      String[] usernames = StringUtils.tokenize(watchlistnames, ",");
      if (ids != null) {
        for (int i = 0; i < ids.length; i++)
        {
          OfferWatchList ch = new OfferWatchList();
          ch.setUserId(new Long(ids[i]).longValue());
          ch.setRequisitionId(applicant.getReqId());
          ch.setApplicantId(applicant.getApplicantId());
          ch.setUsername(usernames[i]);
          ch.setCreatedBy(user1.getUserName());
          ch.setCreatedDate(new Date());
          ch.setFunctionType("release_offer");
          watchList.add(ch);
        }
      }
    }
    applicantform.setOfferwatchList(watchList);
    
    applicantform.setOfferReleaseComment(applicantform.getOfferReleaseComment());
    



    BOFactory.getApplicantBO().deleteOfferAttachment(uuid, applicant.getUuid(), user1);
    
    List attachmentList = BOFactory.getApplicantBO().getOfferAttachmentList(new Long(applicantId).longValue(), "reloffer");
    





    applicantform.setOfferAttachmentList(attachmentList);
    
    applicantform.fromValue(applicant, request);
    
    applicantform.setOfferedctc(applicant.getOfferedctc());
    applicantform.setOfferedctccurrencycode(applicant.getOfferedctccurrencycode());
    if (!StringUtils.isNullOrEmpty(emailtemplateid)) {
      applicantform.setEmailtemplateid(new Long(emailtemplateid).longValue());
    }
    applicantform.setEmailtemplate(emailtemplatename);
    
    JobRequisition jb = BOFactory.getJobRequistionBO().getJobRequision(String.valueOf(applicant.getReqId()));
    
    int totalfilledposition = BOFactory.getApplicantBO().getTotalNoOfFilledPositionByReqId(jb.getJobreqId());
    if (totalfilledposition >= jb.getNumberOfOpening()) {
      request.setAttribute("numberofpositionfilledexceeds", "yes");
    }
    request.setAttribute("offerreleased", "no");
    return mapping.findForward("releaseofferscr");
  }
  
  public ActionForward offerattachmentdetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside offerattachmentdetails method");
    String uuid = request.getParameter("uuid");
    

    ApplicantOfferAttachment offerattachment = BOFactory.getApplicantBO().getOfferAttachmentDetailsByUuid(uuid);
    if ((offerattachment != null) && (offerattachment.getAttahmentname() != null) && (offerattachment.getAttahmentname().length() > 0))
    {
      String filePath = Constant.getValue("ATTACHMENT_PATH");
      filePath = "offerAttachment" + File.separator + offerattachment.getUuid() + File.separator + offerattachment.getAttahmentname();
      
      request.setAttribute("filePath", filePath);
    }
    return mapping.findForward("offerattachmentdetails");
  }
  
  public ActionForward offerapproverejectcomment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside offerapproverejectcomment method");
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    String offerapproverId = request.getParameter("offerapproverId");
    String type = request.getParameter("type");
    
    request.setAttribute("applicantId", applicantId);
    request.setAttribute("offerapproverId", offerapproverId);
    request.setAttribute("type", type);
    ApplicantForm applicantform = (ApplicantForm)form;
    JobApplicant app = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    SalaryPlan sl = BOFactory.getApplicantBO().getSalaryPlanByApplicantId(new Long(applicantId).longValue());
    applicantform.setSalaryPlan(sl);
    applicantform.setOfferedctc(app.getOfferedctc());
    applicantform.setOfferedctccurrencycode(app.getOfferedctccurrencycode());
    return mapping.findForward("offerapproverejectcomment");
  }
  
  public ActionForward offerapproverejectcommenttaskpage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside offerapproverejectcommenttaskpage method");
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    String type = request.getParameter("action");
    User user1 = (User)request.getSession().getAttribute("user_data");
    
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    List approverList = BOFactory.getApplicantBO().getOfferApproverList(applicant.getApplicantId());
    String offerapproverId = "";
    if ((approverList != null) && (approverList.size() > 0))
    {
      boolean isOfferRejected = false;
      for (int i = 0; i < approverList.size(); i++)
      {
        OfferApprover offerapprover_temp = (OfferApprover)approverList.get(i);
        if (offerapprover_temp.getApproved().equals("R"))
        {
          isOfferRejected = true;
          break;
        }
      }
      for (int i = 0; i < approverList.size(); i++)
      {
        OfferApprover offerapprover = (OfferApprover)approverList.get(i);
        if ((offerapprover.getApproved().equals("N")) && (!isOfferRejected) && 
          (BOFactory.getApplicantBO().isLoggedInUserIsOwner(user1.getUserId(), offerapprover.getApproverId(), applicant.getIsGroup(), applicant.getOwnerGroup())))
        {
          offerapproverId = String.valueOf(offerapprover.getOfferApproverId());
          

          break;
        }
      }
    }
    request.setAttribute("applicantId", applicantId);
    request.setAttribute("offerapproverId", offerapproverId);
    request.setAttribute("type", type);
    
    ApplicantForm applicantform = (ApplicantForm)form;
    
    SalaryPlan sl = BOFactory.getApplicantBO().getSalaryPlanByApplicantId(new Long(applicantId).longValue());
    applicantform.setSalaryPlan(sl);
    applicantform.setOfferedctc(applicant.getOfferedctc());
    applicantform.setOfferedctccurrencycode(applicant.getOfferedctccurrencycode());
    
    return mapping.findForward("offerapproverejectcomment");
  }
  
  public ActionForward approveoffer(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside approveoffer method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    String applicantId = request.getParameter("applicantId");
    String offerapproverId = request.getParameter("offerapproverId");
    String comment = request.getParameter("comment");
    

    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetails(applicantId);
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantForm applicantform = (ApplicantForm)form;
    
    OfferApprover offerapproverexist = BOFactory.getApplicantBO().getOfferApprover(offerapproverId);
    


    offerapproverexist.setApproved("Y");
    offerapproverexist.setApprovedDate(new Date());
    if ((!StringUtils.isEmpty(offerapproverexist.getIsGroup())) && (offerapproverexist.getIsGroup().equals("Y")))
    {
      offerapproverexist.setOnbehalfApproverId(user1.getUserId());
      offerapproverexist.setOnbehalfApproverName(user1.getFirstName() + " " + user1.getLastName());
    }
    else
    {
      offerapproverexist.setApproverId(user1.getUserId());
      offerapproverexist.setApproverName(user1.getFirstName() + " " + user1.getLastName());
    }
    offerapproverexist.setComment(comment);
    




    BOFactory.getApplicantTXBO().approveoffer(applicant, offerapproverexist, user1);
    



    applicantform.setOfferownerId(applicant.getOfferownerId());
    applicantform.setOfferownerName(applicant.getOfferownerName());
    

    applicantform.setCurrectctc(applicant.getCurrectctc());
    applicantform.setExpectedctc(applicant.getExpectedctc());
    applicantform.setCurrectctccurrencycode(applicant.getCurrectctccurrencycode());
    applicantform.setExpectedctccurrencycode(applicant.getExpectedctccurrencycode());
    
    applicantform.setOfferInitiationComment(applicant.getOfferInitiationComment());
    applicantform.setOfferinitiationdate(applicant.getOfferinitiationdate());
    applicantform.setOfferinitiatedby(applicant.getOfferinitiatedby());
    if (applicant.getOfferedctc() != null) {
      applicantform.setOfferedctc(applicant.getOfferedctc());
    }
    if (applicant.getOfferedctccurrencycode() != null) {
      applicantform.setOfferedctccurrencycode(applicant.getOfferedctccurrencycode());
    }
    applicantform.setInterviewState(applicant.getInterviewState());
    
    List approverlist = BOFactory.getApplicantBO().getOfferApproverList(applicant.getApplicantId());
    
    applicantform.setOfferApproverList(approverlist);
    

    List attachmentList = new ArrayList();
    applicantform.setOfferAttachmentList(attachmentList);
    applicantform.setOfferReleaseAttachmentList(attachmentList);
    applicantform.setOfferwatchList(attachmentList);
    
    applicantform.fromValue(applicant, request);
    



    offerapproverexist = BOFactory.getApplicantBO().getOfferApprover(offerapproverId);
    if ((offerapproverexist != null) && (offerapproverexist.getApproved().equals("Y")))
    {
      OfferApprover nextofferapprover = BOFactory.getApplicantBO().getNextOfferApprover(applicant.getApplicantId(), offerapproverexist.getLevelorder());
      if (nextofferapprover != null)
      {
        List tolist = new ArrayList();
        if ((!StringUtils.isNullOrEmpty(nextofferapprover.getIsGroup())) && (nextofferapprover.getIsGroup().equals("Y")))
        {
          UserGroup usrgrp = BOFactory.getLovBO().getUserGroup(nextofferapprover.getApproverId());
          applicant.setApplicantReviewerName(usrgrp.getUsergrpName());
          Set users = usrgrp.getUsers();
          if ((users != null) && (users.size() > 0))
          {
            Iterator itr = users.iterator();
            while (itr.hasNext())
            {
              User usr = (User)itr.next();
              String toemailid = "\"" + usr.getFirstName() + " " + usr.getLastName() + "\"" + " " + "<" + usr.getEmailId() + ">";
              tolist.add(toemailid);
            }
          }
        }
        else
        {
          User usr = UserDAO.getUserFullNameEmailAndUserId(nextofferapprover.getApproverId());
          String toemailid = "\"" + usr.getFirstName() + " " + usr.getLastName() + "\"" + " " + "<" + usr.getEmailId() + ">";
          tolist.add(toemailid);
          applicant.setApplicantReviewerName(usr.getFirstName() + " " + usr.getLastName());
        }
        String[] to = new String[tolist.size()];
        tolist.toArray(to);
        try
        {
          User offerInitiator = UserDAO.getUserByUserName(applicant.getOfferinitiatedby());
          



          List cclist = BOFactory.getApplicantBO().getCCListForApplicant(user1, applicant, null, EmailNotificationSettingFunction.FunctionNames.SEND_FOR_OFFER_APPROVAL.toString(), true);
          if ((!StringUtils.isEmpty(applicant.getIsofferownerGroup())) && (applicant.getIsofferownerGroup().equals("Y")))
          {
            long groupId = 0L;
            
            UserGroup usrgrp = BOFactory.getLovBO().getUserGroup(applicant.getOfferownerId());
            if (usrgrp != null)
            {
              Set users = usrgrp.getUsers();
              if ((users != null) && (users.size() > 0))
              {
                Iterator itr = users.iterator();
                while (itr.hasNext())
                {
                  User touser = (User)itr.next();
                  cclist.add(touser.getEmailId());
                }
              }
            }
          }
          else
          {
            User usr = BOFactory.getLovBO().getUserFullNameEmailAndUserIdById(applicant.getOfferownerId());
            if (usr != null) {
              cclist.add(usr.getEmailId());
            }
          }
          cclist.add(offerInitiator.getEmailId());
          
          String[] cc = new String[cclist.size()];
          cclist.toArray(cc);
          


          String[] bcc = null;
          


          String subject = "";
          String datepattern = Constant.getValue("defaultdateformat");
          if (offerInitiator != null) {
            datepattern = DateUtil.getDatePatternFormat(offerInitiator.getLocale());
          }
          String targetofferdateconverted = DateUtil.convertDateToStringDate(applicant.getTargerofferdate(), datepattern);
          String offerinitiationdateConverted = DateUtil.convertSourceToTargetTimezone(applicant.getOfferinitiationdate(), offerInitiator.getTimezone().getTimezoneCode(), offerInitiator.getLocale());
          
          applicant.setOffertargetdateConverted(targetofferdateconverted + " " + offerInitiator.getTimezone().getTimezoneCode());
          applicant.setOfferinitiationdateConverted(offerinitiationdateConverted + " " + offerInitiator.getTimezone().getTimezoneCode());
          
          String pendingtaskurl = Constant.getValue("external.url") + "task.do?method=mypendingtasks";
          applicant.setMypendingTaskUrl(pendingtaskurl);
          
          String from = "\"" + offerInitiator.getFirstName() + " " + offerInitiator.getLastName() + "\"" + " " + "<" + offerInitiator.getEmailId() + ">";
          String applicanturl = Constant.getValue("external.url") + "applicant.do?method=applicantDetails&applicantId=" + applicant.getApplicantId() + "&secureid=" + applicant.getUuid();
          applicant.setApplicantUrl(applicanturl);
          String offerinicomment = applicant.getOfferInitiationComment() == null ? "" : applicant.getOfferInitiationComment();
          applicant.setOfferInitiationComment(offerinicomment);
          applicant.setOfferinitiatedbyName(offerInitiator.getFirstName() + " " + offerInitiator.getLastName());
          
          EmailTask emailtask = new EmailTask(from, to, cc, bcc, from, "dummysubject", null, "dummybody", null, 0, null);
          emailtask.setFunctionType(Common.OFFER_APPROVAL_EMAIL_COMPONENT);
          emailtask.setUser(user1);
          emailtask.setApplicant(applicant);
          EmailTaskManager.sendEmail(emailtask);
        }
        catch (Exception e)
        {
          e.printStackTrace();
          logger.info("Error sending email offer approval" + e);
          logger.error(e);
          request.setAttribute("errorsendingemail", "yes");
        }
      }
      try
      {
        List tolist = new ArrayList();
        if ((!StringUtils.isNullOrEmpty(offerapproverexist.getIsGroup())) && (offerapproverexist.getIsGroup().equals("Y")))
        {
          UserGroup usrgrp = BOFactory.getLovBO().getUserGroup(offerapproverexist.getApproverId());
          Set users = usrgrp.getUsers();
          if ((users != null) && (users.size() > 0))
          {
            Iterator itr = users.iterator();
            while (itr.hasNext())
            {
              User usr = (User)itr.next();
              String toemailid = "\"" + usr.getFirstName() + " " + usr.getLastName() + "\"" + " " + "<" + usr.getEmailId() + ">";
              tolist.add(toemailid);
            }
          }
        }
        else
        {
          User usr = UserDAO.getUserFullNameEmailAndUserId(offerapproverexist.getApproverId());
          String toemailid = "\"" + usr.getFirstName() + " " + usr.getLastName() + "\"" + " " + "<" + usr.getEmailId() + ">";
          tolist.add(toemailid);
        }
        String[] to = new String[tolist.size()];
        tolist.toArray(to);
        
        User offerInitiator = UserDAO.getUserByUserName(applicant.getOfferinitiatedby());
        


        List cclist = BOFactory.getApplicantBO().getCCListForApplicant(user1, applicant, null, EmailNotificationSettingFunction.FunctionNames.OFFER_APPROVE_REJECT.toString(), true);
        if ((!StringUtils.isEmpty(applicant.getIsofferownerGroup())) && (applicant.getIsofferownerGroup().equals("Y")))
        {
          long groupId = 0L;
          
          UserGroup usrgrp = BOFactory.getLovBO().getUserGroup(applicant.getOfferownerId());
          if (usrgrp != null)
          {
            Set users = usrgrp.getUsers();
            if ((users != null) && (users.size() > 0))
            {
              Iterator itr = users.iterator();
              while (itr.hasNext())
              {
                User touser = (User)itr.next();
                cclist.add(touser.getEmailId());
              }
            }
          }
        }
        else
        {
          User usr = BOFactory.getLovBO().getUserFullNameEmailAndUserIdById(applicant.getOfferownerId());
          if (usr != null) {
            cclist.add(usr.getEmailId());
          }
        }
        cclist.add(offerInitiator.getEmailId());
        String[] cc = new String[cclist.size()];
        cclist.toArray(cc);
        

        applicant.setOfferApproverComment(comment);
        applicant.setOfferApproverName(offerapproverexist.getApproverName());
        applicant.setApplicantReviewerName(offerapproverexist.getApproverName());
        applicant.setOfferApprovedStatus("approved");
        
        String datepattern = Constant.getValue("defaultdateformat");
        if (offerInitiator != null) {
          datepattern = DateUtil.getDatePatternFormat(offerInitiator.getLocale());
        }
        String targetofferdateconverted = DateUtil.convertDateToStringDate(applicant.getTargerofferdate(), datepattern);
        String offerinitiationdateConverted = DateUtil.convertSourceToTargetTimezone(applicant.getOfferinitiationdate(), offerInitiator.getTimezone().getTimezoneCode(), offerInitiator.getLocale());
        
        applicant.setOffertargetdateConverted(targetofferdateconverted + " " + offerInitiator.getTimezone().getTimezoneCode());
        applicant.setOfferinitiationdateConverted(offerinitiationdateConverted + " " + offerInitiator.getTimezone().getTimezoneCode());
        
        String applicanturl = Constant.getValue("external.url") + "applicant.do?method=applicantDetails&applicantId=" + applicant.getApplicantId() + "&secureid=" + applicant.getUuid();
        applicant.setApplicantUrl(applicanturl);
        
        User offerApprovedUser = UserDAO.getUserFullNameEmailAndUserId(offerapproverexist.getApproverId());
        
        String from = "\"" + offerApprovedUser.getFirstName() + " " + offerApprovedUser.getLastName() + "\"" + " " + "<" + offerApprovedUser.getEmailId() + ">";
        



        String[] bccnew = null;
        applicant.setOfferinitiatedbyName(offerInitiator.getFirstName() + " " + offerInitiator.getLastName());
        EmailTask emailtasknew = new EmailTask(from, to, cc, bccnew, from, "dummysubject", null, "dummybody", null, 0, null);
        emailtasknew.setFunctionType(Common.OFFER_APPROVE_REJECT_EMAIL_COMPONENT);
        emailtasknew.setUser(user1);
        emailtasknew.setApplicant(applicant);
        EmailTaskManager.sendEmail(emailtasknew);
      }
      catch (Exception e)
      {
        e.printStackTrace();
        logger.info("Error sending email offer approval" + e);
        logger.error(e);
        request.setAttribute("errorsendingemail", "yes");
      }
    }
    request.setAttribute("commentsadded", "yes");
    return mapping.findForward("offerapproverejectcomment");
  }
  
  public ActionForward rejectoffer(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside rejectoffer method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    String applicantId = request.getParameter("applicantId");
    String offerapproverId = request.getParameter("offerapproverId");
    
    String comment = request.getParameter("comment");
    
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetails(applicantId);
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantForm applicantform = (ApplicantForm)form;
    
    OfferApprover offerapproverexist = BOFactory.getApplicantBO().getOfferApprover(offerapproverId);
    

    offerapproverexist.setApproved("R");
    offerapproverexist.setApprovedDate(new Date());
    if ((!StringUtils.isEmpty(offerapproverexist.getIsGroup())) && (offerapproverexist.getIsGroup().equals("Y")))
    {
      offerapproverexist.setOnbehalfApproverId(user1.getUserId());
      offerapproverexist.setOnbehalfApproverName(user1.getFirstName() + " " + user1.getLastName());
    }
    else
    {
      offerapproverexist.setApproverId(user1.getUserId());
      offerapproverexist.setApproverName(user1.getFirstName() + " " + user1.getLastName());
    }
    offerapproverexist.setComment(comment);
    


    BOFactory.getApplicantTXBO().rejectoffer(applicant, offerapproverexist, user1);
    



    applicantform.setOfferownerId(applicant.getOfferownerId());
    applicantform.setOfferownerName(applicant.getOfferownerName());
    
    applicantform.setCurrectctc(applicant.getCurrectctc());
    applicantform.setExpectedctc(applicant.getExpectedctc());
    applicantform.setCurrectctccurrencycode(applicant.getCurrectctccurrencycode());
    
    applicantform.setExpectedctccurrencycode(applicant.getExpectedctccurrencycode());
    

    applicantform.setOfferInitiationComment(applicant.getOfferInitiationComment());
    
    applicantform.setOfferinitiationdate(applicant.getOfferinitiationdate());
    
    applicantform.setOfferinitiatedby(applicant.getOfferinitiatedby());
    
    applicantform.setInterviewState(applicant.getInterviewState());
    
    List approverlist = BOFactory.getApplicantBO().getOfferApproverList(applicant.getApplicantId());
    

    applicantform.setOfferApproverList(approverlist);
    

    List attachmentList = new ArrayList();
    applicantform.setOfferAttachmentList(attachmentList);
    applicantform.setOfferReleaseAttachmentList(attachmentList);
    applicantform.setOfferwatchList(attachmentList);
    

    applicantform.fromValue(applicant, request);
    if (applicant.getOfferedctc() != null) {
      applicantform.setOfferedctc(applicant.getOfferedctc());
    }
    if (applicant.getOfferedctccurrencycode() != null) {
      applicantform.setOfferedctccurrencycode(applicant.getOfferedctccurrencycode());
    }
    offerapproverexist = BOFactory.getApplicantBO().getOfferApprover(offerapproverId);
    if ((offerapproverexist != null) && (offerapproverexist.getApproved().equals("R"))) {
      try
      {
        List tolist = new ArrayList();
        if ((!StringUtils.isNullOrEmpty(offerapproverexist.getIsGroup())) && (offerapproverexist.getIsGroup().equals("Y")))
        {
          UserGroup usrgrp = BOFactory.getLovBO().getUserGroup(offerapproverexist.getApproverId());
          
          Set users = usrgrp.getUsers();
          if ((users != null) && (users.size() > 0))
          {
            Iterator itr = users.iterator();
            while (itr.hasNext())
            {
              User usr = (User)itr.next();
              String toemailid = "\"" + usr.getFirstName() + " " + usr.getLastName() + "\"" + " " + "<" + usr.getEmailId() + ">";
              

              tolist.add(toemailid);
            }
          }
        }
        else
        {
          User usr = UserDAO.getUserFullNameEmailAndUserId(offerapproverexist.getApproverId());
          

          String toemailid = "\"" + usr.getFirstName() + " " + usr.getLastName() + "\"" + " " + "<" + usr.getEmailId() + ">";
          

          tolist.add(toemailid);
        }
        String[] to = new String[tolist.size()];
        tolist.toArray(to);
        
        User offerInitiator = UserDAO.getUserByUserName(applicant.getOfferinitiatedby());
        



        List cclist = BOFactory.getApplicantBO().getCCListForApplicant(user1, applicant, null, EmailNotificationSettingFunction.FunctionNames.OFFER_APPROVE_REJECT.toString(), true);
        if ((!StringUtils.isEmpty(applicant.getIsofferownerGroup())) && (applicant.getIsofferownerGroup().equals("Y")))
        {
          long groupId = 0L;
          
          UserGroup usrgrp = BOFactory.getLovBO().getUserGroup(applicant.getOfferownerId());
          if (usrgrp != null)
          {
            Set users = usrgrp.getUsers();
            if ((users != null) && (users.size() > 0))
            {
              Iterator itr = users.iterator();
              while (itr.hasNext())
              {
                User touser = (User)itr.next();
                cclist.add(touser.getEmailId());
              }
            }
          }
        }
        else
        {
          User usr = BOFactory.getLovBO().getUserFullNameEmailAndUserIdById(applicant.getOfferownerId());
          if (usr != null) {
            cclist.add(usr.getEmailId());
          }
        }
        cclist.add(offerInitiator.getEmailId());
        String[] cc = new String[cclist.size()];
        cclist.toArray(cc);
        
        String[] bcc = null;
        
        applicant.setOfferApproverComment(comment);
        applicant.setOfferApproverName(offerapproverexist.getApproverName());
        
        applicant.setApplicantReviewerName(offerapproverexist.getApproverName());
        
        applicant.setOfferApprovedStatus("rejected");
        User offerApprovedUser = UserDAO.getUser(offerapproverexist.getApproverId());
        
        String replyTonew = offerApprovedUser.getFirstName() + " " + offerApprovedUser.getLastName() + " " + "<" + offerApprovedUser.getEmailId() + ">";
        




        String datepattern = Constant.getValue("defaultdateformat");
        if (offerApprovedUser != null) {
          datepattern = DateUtil.getDatePatternFormat(offerInitiator.getLocale());
        }
        String targetofferdateconverted = DateUtil.convertDateToStringDate(applicant.getTargerofferdate(), datepattern);
        

        String offerinitiationdateConverted = DateUtil.convertSourceToTargetTimezone(applicant.getOfferinitiationdate(), offerInitiator.getTimezone().getTimezoneCode(), offerInitiator.getLocale());
        




        applicant.setOffertargetdateConverted(targetofferdateconverted + " " + offerInitiator.getTimezone().getTimezoneCode());
        
        applicant.setOfferinitiationdateConverted(offerinitiationdateConverted + " " + offerInitiator.getTimezone().getTimezoneCode());
        




        String pendingtaskurl = Constant.getValue("external.url") + "task.do?method=mypendingtasks";
        
        applicant.setMypendingTaskUrl(pendingtaskurl);
        String from = "\"" + offerApprovedUser.getFirstName() + " " + offerApprovedUser.getLastName() + "\"" + " " + "<" + offerApprovedUser.getEmailId() + ">";
        




        String applicanturl = Constant.getValue("external.url") + "applicant.do?method=applicantDetails&applicantId=" + applicant.getApplicantId() + "&secureid=" + applicant.getUuid();
        


        applicant.setApplicantUrl(applicanturl);
        
        EmailTask emailtasknew = new EmailTask(from, to, cc, bcc, from, "dummysubject", null, "dummybody", null, 0, null);
        

        emailtasknew.setFunctionType(Common.OFFER_APPROVE_REJECT_EMAIL_COMPONENT);
        
        emailtasknew.setUser(offerInitiator);
        emailtasknew.setApplicant(applicant);
        EmailTaskManager.sendEmail(emailtasknew);
      }
      catch (Exception e)
      {
        e.printStackTrace();
        logger.info("Error sending email offer approval" + e);
        logger.error(e);
        request.setAttribute("errorsendingemail", "yes");
      }
    }
    request.setAttribute("commentsrejected", "yes");
    return mapping.findForward("offerapproverejectcomment");
  }
  
  public void setApproversList(long applicantId, List approverList, HttpServletRequest request)
  {
    for (int i = 1; i < 50; i++)
    {
      String approvername = request.getParameter("username_" + i);
      

      String userid = request.getParameter("userid_" + i);
      String isGroup = request.getParameter("isGroup_" + i);
      
      OfferApprover approver = new OfferApprover();
      approver.setApplicantId(applicantId);
      approver.setApproverName(approvername);
      approver.setLevelorder(i);
      if (!StringUtils.isNullOrEmpty(userid)) {
        approver.setApproverId(new Long(userid).longValue());
      }
      if ((!StringUtils.isEmpty(isGroup)) && (isGroup.equals("Y"))) {
        approver.setIsGroup("Y");
      } else {
        approver.setIsGroup("N");
      }
      if (!StringUtils.isNullOrEmpty(approvername)) {
        approverList.add(approver);
      }
    }
  }
  
  public void setOtherBenefitsList(long applicantId, List benefitsList, HttpServletRequest request, String currencyCode)
  {
    for (int i = 1; i < 50; i++)
    {
      String name = request.getParameter("benefitname_" + i);
      String amount = request.getParameter("benefitamount_" + i);
      logger.info("amountamountamount" + amount);
      logger.info("name" + name);
      if (!StringUtils.isNullOrEmpty(name))
      {
        amount = StringUtils.isNullOrEmpty(amount) ? "0" : amount;
        ApplicantOtherBenefits otherbenefit = new ApplicantOtherBenefits();
        
        otherbenefit.setApplicantId(applicantId);
        otherbenefit.setName(name);
        otherbenefit.setAmount(new Double(amount).doubleValue());
        otherbenefit.setCurrencyCode(currencyCode);
        
        benefitsList.add(otherbenefit);
      }
    }
  }
  
  public ActionForward searchownapplicant(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchownapplicant method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantForm applicantform = (ApplicantForm)form;
    String previousOrganization = request.getParameter("previousOrganization");
    
    String applieddate = request.getParameter("applieddate");
    String cri = request.getParameter("cri");
    applicantform.setPreviousOrganization(previousOrganization);
    applicantform.setFullName(applicantform.getFullName());
    applicantform.setReferrerName(applicantform.getReferrerName());
    applicantform.setRequitionId(applicantform.getRequitionId());
    applicantform.setSearchapplieddate(applieddate);
    applicantform.setAppliedcri(cri);
    
    List interviewstatelist = Constant.getInterviewStatesWithOutBlank();
    applicantform.setInterviewstateList(interviewstatelist);
    
    request.setAttribute("searchpagedisplay", "yes");
    return mapping.findForward("searchownapplicantinit");
  }
  
  public ActionForward applicantsbyvendor(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside applicantsbyvendor method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantForm applicantform = (ApplicantForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    String applicant_name = request.getParameter("applicant_name");
    if (request.getSession().getAttribute(Common.APPLICANT_COUNT) != null) {
      request.getSession().removeAttribute(Common.APPLICANT_COUNT);
    }
    BOFactory.getApplicantBO().setLovsForApplicantSearch(applicantform, user1);
    ApplicantSearchCriteria criteria = new ApplicantSearchCriteria();
    if (!StringUtils.isNullOrEmpty(applicant_name))
    {
      criteria.setFullname(applicant_name);
      criteria.setApplicantName_criteria("EQUALS");
      SearchApplicant searchsave = new SearchApplicant();
      searchsave.setFullName(applicant_name);
      searchsave.setApplicantName_criteria("EQUALS");
      searchsave.setNoofrows(15);
      String timenow = DateUtil.convertFromTimezoneToTimezoneDate(new Date(), DateUtil.dateformatyahoocal, TimeZone.getDefault().getID(), user1.getTimezone().getTimezoneCode());
      searchsave.setSearchuuid(UUID.randomUUID().toString());
      searchsave.setSavedsearchname(timenow);
      BOFactory.getApplicantBO().saveApplicantSearchRecent(searchsave);
      criteria.setSearchuuid(searchsave.getSearchuuid());
      applicantform.setFullName(applicant_name);
      applicantform.setApplicantName_criteria("EQUALS");
    }
    applicantform.setSearchcriteria(criteria);
    


    applicantform.setGender("N");
    return mapping.findForward("applicantsbyvendor");
  }
  
  public ActionForward deletesavesearch(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deletesavesearch method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantForm applicantform = (ApplicantForm)form;
    String savesearchid = request.getParameter("searchuuid");
    BOFactory.getApplicantBO().deleteSavedApplicantSearch(savesearchid, user1.getUserId());
    








    BOFactory.getApplicantBO().setLovsForApplicantSearch(applicantform, user1);
    ApplicantSearchCriteria criteria = new ApplicantSearchCriteria();
    applicantform.setSearchcriteria(criteria);
    
    return mapping.findForward("applicantsbyvendor");
  }
  
  public ActionForward savesearchapplicantbyvendorscr(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside savesearchapplicantbyvendorscr method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantForm applicantform = (ApplicantForm)form;
    String searchuuid = request.getParameter("searchuuid");
    
    request.setAttribute("searchuuid", searchuuid);
    return mapping.findForward("savesearchapplicantbyvendorscr");
  }
  
  public ActionForward searchapplicantbyvendor(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchapplicantbyvendor method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantForm applicantform = (ApplicantForm)form;
    try
    {
      String searchappId = request.getParameter("searchuuid");
      String fromdate = request.getParameter("searchfromdate");
      String todate = request.getParameter("searchtodate");
      String dateofbirth = request.getParameter("dateofbirth");
      String dateofbirth1 = request.getParameter("dateofbirth1");
      String earliest_start_date = request.getParameter("earliest_start_date");
      String gender = request.getParameter("gender");
      

      SearchApplicant searchsave = new SearchApplicant();
      searchsave.setDateofbirth(dateofbirth);
      searchsave.setDateofbirth1(dateofbirth1);
      searchsave.setGender(gender);
      searchsave.setEarliest_start_date(earliest_start_date);
      if (StringUtils.isNullOrEmpty(applicantform.getNoofrows())) {}
      searchsave.setNoofrows(new Integer(applicantform.getNoofrows()).intValue());
      

      searchapplicantbyvendor(request, applicantform, searchsave, searchappId, fromdate, todate, user1);
      


      ApplicantSearchCriteria criteria = new ApplicantSearchCriteria();
      criteria.setSearchuuid(searchsave.getSearchuuid());
      applicantform.setSearchcriteria(criteria);
      



      BOFactory.getApplicantBO().setLovsForApplicantSearch(applicantform, user1);
      request.setAttribute("searchpagedisplay", "yes");
      logger.info("Inside searchapplicantbyvendor method end");
    }
    catch (Exception e)
    {
      logger.info("exception on searchapplicantbyvendor", e);
    }
    return mapping.findForward("searchapplicantbyvendor");
  }
  
  private void searchapplicantbyvendor(HttpServletRequest request, ApplicantForm applicantform, SearchApplicant searchsave, String searchappId, String fromdate, String todate, User user1)
    throws Exception
  {
    Map<Long, SearchApplicantCustomFields> m = new HashMap();
    if ((!StringUtils.isNullOrEmpty(searchappId)) && (!searchappId.equals("null")))
    {
      SearchApplicant search = BOFactory.getApplicantBO().getSavedApplicantSearchesBySearchUUID(searchappId);
      if (search != null)
      {
        ApplicantSearchUtil.setApplicantSearchParameter(applicantform, fromdate, todate, user1, searchsave);
        searchsave.setSearchappId(search.getSearchappId());
        searchsave.setSavedsearchname(search.getSavedsearchname());
        searchsave.setSearchuuid(search.getSearchuuid());
        searchsave.setCreatedBy(search.getCreatedBy());
        searchsave.setCreatedDate(search.getCreatedDate());
        searchsave.setNoofrows(new Integer(applicantform.getNoofrows()).intValue());
        searchsave.setSaveshare(search.getSaveshare());
        BOFactory.getApplicantBO().updateApplicantSearch(searchsave);
      }
      applicantform.setQuestionCriList(BOFactory.getApplicantBO().getSearchApplicantQuestions(searchsave.getSearchappId()));
      



      List variabledatalist = VariableDataCaptureUtil.captureCustomVariablesForApplicantSearch(request, 0L, "APPLICANT_FORM", searchsave.getSearchappId(), user1.getSuper_user_key());
      if ((variabledatalist != null) && (variabledatalist.size() > 0))
      {
        BOFactory.getApplicantBO().saveCustomVariableListForSearch(variabledatalist, searchsave.getSearchappId());
        for (int i = 0; i < variabledatalist.size(); i++)
        {
          SearchApplicantCustomFields sacf = (SearchApplicantCustomFields)variabledatalist.get(i);
          m.put(Long.valueOf(sacf.getVariable_id()), sacf);
        }
      }
    }
    else
    {
      ApplicantSearchUtil.setApplicantSearchParameter(applicantform, fromdate, todate, user1, searchsave);
      searchsave.setNoofrows(new Integer(applicantform.getNoofrows()).intValue());
      String timenow = DateUtil.convertFromTimezoneToTimezoneDate(new Date(), DateUtil.dateformatyahoocal, TimeZone.getDefault().getID(), user1.getTimezone().getTimezoneCode());
      searchsave.setSearchuuid(UUID.randomUUID().toString());
      searchsave.setSavedsearchname(timenow);
      BOFactory.getApplicantBO().saveApplicantSearchRecent(searchsave);
      
      SearchApplicant search = BOFactory.getApplicantBO().getSavedApplicantSearchesBySearchUUID(searchappId);
      applicantform.setQuestionCriList(BOFactory.getApplicantBO().getSearchApplicantQuestions(searchsave.getSearchappId()));
      



      List variabledatalist = VariableDataCaptureUtil.captureCustomVariablesForApplicantSearch(request, 0L, "APPLICANT_FORM", searchsave.getSearchappId(), user1.getSuper_user_key());
      if ((variabledatalist != null) && (variabledatalist.size() > 0))
      {
        BOFactory.getApplicantBO().saveCustomVariableListForSearch(variabledatalist, searchsave.getSearchappId());
        for (int i = 0; i < variabledatalist.size(); i++)
        {
          SearchApplicantCustomFields sacf = (SearchApplicantCustomFields)variabledatalist.get(i);
          m.put(Long.valueOf(sacf.getVariable_id()), sacf);
        }
      }
    }
    applicantform.setCustomFieldData(m);
  }
  
  public ActionForward viewSavedSearchResult(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside viewSavedSearchResult method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantForm applicantform = (ApplicantForm)form;
    String searchappId = request.getParameter("searchuuid");
    if ((!StringUtils.isNullOrEmpty(searchappId)) && (!searchappId.equals("null")))
    {
      SearchApplicant search = BOFactory.getApplicantBO().getSavedApplicantSearchesBySearchUUID(searchappId);
      if (search != null)
      {
        ApplicantSearchUtil.setFormData(applicantform, search);
        
        applicantform.setQuestionCriList(BOFactory.getApplicantBO().getSearchApplicantQuestions(search.getSearchappId()));
        
        Map<Long, SearchApplicantCustomFields> customFieldData = BOFactory.getApplicantBO().getCustomVariableListDataForSearch(search.getSearchappId());
        
        applicantform.setCustomFieldData(customFieldData);
      }
      ApplicantSearchCriteria criteria = new ApplicantSearchCriteria();
      criteria.setSearchuuid(searchappId);
      applicantform.setSearchcriteria(criteria);
    }
    BOFactory.getApplicantBO().setLovsForApplicantSearch(applicantform, user1);
    request.setAttribute("searchpagedisplay", "yes");
    return mapping.findForward("applicantsbyvendor");
  }
  
  public ActionForward savequestionfilters(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside savequestionfilters method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantForm applicantform = (ApplicantForm)form;
    String searchappId = request.getParameter("searchuuid");
    String questionId = request.getParameter("questionId");
    String filtercri = request.getParameter("filtercri");
    String answerOption = request.getParameter("answerOption");
    String filterValue1 = request.getParameter("filterValue1");
    String filterValue2 = request.getParameter("filterValue2");
    logger.info("Inside savequestionfilters method searchappId" + searchappId);
    logger.info("Inside savequestionfilters method questionId" + questionId);
    Questions qns = BOFactory.getQuestionBO().getQuestiondetails(questionId);
    if ((!StringUtils.isNullOrEmpty(searchappId)) && (!searchappId.equals("null")))
    {
      SearchApplicant search = BOFactory.getApplicantBO().getSavedApplicantSearchesBySearchUUID(searchappId);
      

      SearchApplicantQuestions saq = new SearchApplicantQuestions();
      saq.setSearchuuid(search.getSearchuuid());
      saq.setAnswerOption(answerOption);
      saq.setQnstype(qns.getTypeVal());
      saq.setFiltercri(filtercri);
      saq.setFilterValue1(filterValue1);
      saq.setFilterValue2(filterValue2);
      saq.setUuid(UUID.randomUUID().toString());
      

      saq.setQuestion(qns);
      saq.setSearchappid(search.getSearchappId());
      

      BOFactory.getApplicantBO().saveSearchApplicantQuestions(saq);
      


      applicantform.setQuestionCriList(BOFactory.getApplicantBO().getSearchApplicantQuestions(search.getSearchappId()));
      
      Map<Long, SearchApplicantCustomFields> customFieldData = BOFactory.getApplicantBO().getCustomVariableListDataForSearch(search.getSearchappId());
      
      applicantform.setCustomFieldData(customFieldData);
      
      applicantform.setSearchuuid(searchappId);
    }
    else
    {
      SearchApplicant searchsave = new SearchApplicant();
      String timenow = DateUtil.convertFromTimezoneToTimezoneDate(new Date(), DateUtil.dateformatyahoocal, TimeZone.getDefault().getID(), user1.getTimezone().getTimezoneCode());
      searchsave.setSearchuuid(UUID.randomUUID().toString());
      searchsave.setSavedsearchname(timenow);
      searchsave.setSaveduserid(user1.getUserId());
      searchsave.setCreatedBy(user1.getCreatedBy());
      searchsave.setSaveshare("recent");
      searchsave.setType("applicantsearch");
      
      BOFactory.getApplicantBO().saveApplicantSearchRecent(searchsave);
      
      SearchApplicantQuestions saq = new SearchApplicantQuestions();
      
      saq.setSearchuuid(searchsave.getSearchuuid());
      saq.setAnswerOption(answerOption);
      saq.setQnstype(qns.getTypeVal());
      saq.setFiltercri(filtercri);
      saq.setFilterValue1(filterValue1);
      saq.setFilterValue2(filterValue2);
      saq.setUuid(UUID.randomUUID().toString());
      

      saq.setQuestion(qns);
      saq.setSearchappid(searchsave.getSearchappId());
      


      BOFactory.getApplicantBO().saveSearchApplicantQuestions(saq);
      
      SearchApplicant search = BOFactory.getApplicantBO().getSavedApplicantSearchesBySearchUUID(searchsave.getSearchuuid());
      

      applicantform.setQuestionCriList(BOFactory.getApplicantBO().getSearchApplicantQuestions(search.getSearchappId()));
      
      Map<Long, SearchApplicantCustomFields> customFieldData = BOFactory.getApplicantBO().getCustomVariableListDataForSearch(search.getSearchappId());
      
      applicantform.setCustomFieldData(customFieldData);
      
      applicantform.setSearchuuid(searchsave.getSearchuuid());
    }
    return mapping.findForward("questionFilterList");
  }
  
  public ActionForward retrieveFilters(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside retrieveFilters method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantForm applicantform = (ApplicantForm)form;
    String searchappId = request.getParameter("searchuuid");
    if ((!StringUtils.isNullOrEmpty(searchappId)) && (!searchappId.equals("null")))
    {
      SearchApplicant search = BOFactory.getApplicantBO().getSavedApplicantSearchesBySearchUUID(searchappId);
      

      applicantform.setQuestionCriList(BOFactory.getApplicantBO().getSearchApplicantQuestions(search.getSearchappId()));
      
      applicantform.setSearchuuid(search.getSearchuuid());
    }
    return mapping.findForward("questionFilterList");
  }
  
  public ActionForward deletequestionsfilter(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deletequestionsfilter method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantForm applicantform = (ApplicantForm)form;
    String searchappId = request.getParameter("searchuuid");
    String uuid = request.getParameter("uuid");
    
    BOFactory.getApplicantBO().deleteSearchApplicantQuestions(uuid);
    if ((!StringUtils.isNullOrEmpty(searchappId)) && (!searchappId.equals("null")))
    {
      SearchApplicant search = BOFactory.getApplicantBO().getSavedApplicantSearchesBySearchUUID(searchappId);
      

      applicantform.setQuestionCriList(BOFactory.getApplicantBO().getSearchApplicantQuestions(search.getSearchappId()));
      
      applicantform.setSearchuuid(search.getSearchuuid());
    }
    return mapping.findForward("questionFilterList");
  }
  
  public ActionForward searchapplicantListPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchapplicantListPage method");
    

    User user1 = (User)request.getSession().getAttribute("user_data");
    

    String results_str = request.getParameter("results");
    String startIndex_str = request.getParameter("startIndex");
    String sort_str = request.getParameter("sort");
    String dir_str = request.getParameter("dir");
    String searchuuid = request.getParameter("searchuuid");
    

    String data = BOFactory.getApplicantBO().searchapplicantListPage(searchuuid, user1, results_str, startIndex_str, dir_str, sort_str);
    


    request.setAttribute("data", data);
    return mapping.findForward("searchapplicantListPage");
  }
  
  public ActionForward searchOnBoardingapplicants(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchOnBoardingapplicants method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantForm applicantform = (ApplicantForm)form;
    



    applicantform.setVendorName(applicantform.getVendorName());
    List userlist = UserDAO.getAllUsersByType("Vendor");
    applicantform.setVendorList(userlist);
    applicantform.setSearchCriDateList(Constant.getSearchCriteraDateFields());
    
    applicantform.setFullName(applicantform.getFullName());
    List interviewstatelist = Constant.getInterviewStatesForOnBoarding();
    applicantform.setInterviewstateList(interviewstatelist);
    applicantform.setOnboardingProcessStatusList(Constant.getOnBoardingProcessStatus(user1));
    
    applicantform.setInterviewState(applicantform.getInterviewState());
    List jobtitleList = BOFactory.getJobRequistionBO().getJobTitlesActiveListForForm(user1.getSuper_user_key());
    
    applicantform.setJobtitleList(jobtitleList);
    List orgList = BOFactory.getLovBO().getAllOrganization(user1.getSuper_user_key());
    applicantform.setOrganizationList(orgList);
    List deptlist = new ArrayList();
    applicantform.setDepartmentList(deptlist);
    if (applicantform.getOrgId() > 0L) {
      applicantform.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(applicantform.getOrgId())));
    }
    applicantform.setRequitionId(applicantform.getRequitionId());
    


    List tagList = BOFactory.getLovBO().getTagsList("APPLICANT", user1.getSuper_user_key());
    
    applicantform.setTagList(tagList);
    applicantform.setPaginationRowsList(Constant.paginationRowsList);
    request.setAttribute("searchpagedisplay", "yes");
    return mapping.findForward("onboardingapplicants");
  }
  
  public ActionForward savesearchapplicantbyvendor(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside savesearchapplicantbyvendor method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantForm applicantform = (ApplicantForm)form;
    
    String savedsearchname = request.getParameter("savedsearchname");
    String searchuuid = request.getParameter("searchuuid");
    

    BOFactory.getApplicantBO().saveApplicantSearch(user1, savedsearchname, searchuuid);
    



    request.setAttribute("savedsearch", "yes");
    return mapping.findForward("savesearchapplicantbyvendorscr");
  }
  
  public ActionForward myapplicantList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside applicantList method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    return mapping.findForward("myapplicantList");
  }
  
  public ActionForward createPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside createPage method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user1 = (User)request.getSession().getAttribute("user_data");
    ApplicantForm applicantform = (ApplicantForm)form;
    List jobtitleList = new ArrayList();
    logger.info("Login User Id : " + user1.getUserId());
    
    String requitionId = request.getParameter("requistionId");
    if (!StringUtils.isNullOrEmpty(requitionId))
    {
      JobRequisition jobreq = BOFactory.getJobRequistionBO().getActiveOpenJobRequision(requitionId);
      if (jobreq != null)
      {
        applicantform.setRequitionId(jobreq.getJobreqId());
        applicantform.setJobTitle(jobreq.getJobTitle());
        applicantform.setRequisitionName(jobreq.getJobreqName());
      }
    }
    logger.info("role code : " + user1.getRole().getRoleCode());
    
    jobtitleList = BOFactory.getJobRequistionBO().getJobTitlesActiveAndOpenListAssigedToUser(user1);
    

    applicantform.setJobtitleList(jobtitleList);
    
    BOFactory.getApplicantBO().commonLovPopulateOnCreate(applicantform, request, user1.getSuper_user_key());
    

    request.setAttribute("applicantsaved", "no");
    return mapping.findForward("createPage");
  }
  
  public ActionForward createPageApplicantPool(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside createPageApplicantPool method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user1 = (User)request.getSession().getAttribute("user_data");
    ApplicantForm applicantform = (ApplicantForm)form;
    applicantform.setScreenCode("APPLICANT_SCREEN_TALENTPOOL");
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
    BOFactory.getApplicantBO().commonLovPopulateOnCreate(applicantform, request, user1.getSuper_user_key());
    

    List talentPoolList = TalentPoolDAO.getAllTalentPoolsAssignedToUser(user1.getUserId());
    applicantform.setTalentPoolList(talentPoolList);
    request.setAttribute("applicantsaved", "no");
    return mapping.findForward("createPageApplicantPool");
  }
  
  public ActionForward saveapplicantPoolData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveapplicantPoolData method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ApplicantForm applicantform = (ApplicantForm)form;
    JobApplicant applicant = new JobApplicant();
    String dateofbirth = request.getParameter("dateofbirth");
    applicantform.toValue(applicant, request);
    String attachmentsizeexceed = (String)request.getAttribute("attachmentsizeexceed");
    
    applicant.setStatus("T");
    applicant.setInterviewState("Talent Pool");
    List errorList = new ArrayList();
    
    String gender = request.getParameter("gender");
    logger.info(gender);
    applicantform.setScreenCode("APPLICANT_SCREEN_TALENTPOOL");
    boolean isError = false;
    if (!StringUtils.isNullOrEmpty(dateofbirth))
    {
      String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
      
      String converteddate = DateUtil.convertFromTimezoneToTimezoneDate(dateofbirth, datepattern, user1.getTimezone().getTimezoneCode(), TimeZone.getDefault().getID());
      


      logger.info("converteddate" + converteddate);
      
      Calendar cal = DateUtil.convertStringDateToCalendar(converteddate, datepattern);
      

      applicant.setDateofbirth(cal.getTime());
    }
    applicant.setGender(gender);
    applicant.setOwner(user1);
    applicant.setUuid(UUID.randomUUID().toString());
    long talentpoolownerid = 0L;
    if (applicantform.getTalentPoolId() == 0L)
    {
      TalentPool tp = TalentPoolDAO.getDefaultTalentPools(applicant.getSuper_user_key());
      if (tp != null) {
        talentpoolownerid = tp.getTalentPoolId();
      } else {
        talentpoolownerid = 9999999L;
      }
    }
    else
    {
      talentpoolownerid = applicantform.getTalentPoolId();
    }
    applicant.setTalentpoolid(talentpoolownerid);
    logger.info("before saveData method");
    
    List formVariablesList = applicantform.getFormVariablesList();
    List formVariableDataList = applicantform.getFormVariableDataList();
    
    boolean isSuccess = true;
    if ((!StringUtils.isNullOrEmpty(attachmentsizeexceed)) && (attachmentsizeexceed.equals("yes")))
    {
      request.setAttribute("attachmentsizeexceed", "yes");
      isSuccess = false;
    }
    else
    {
      BOFactory.getApplicantBO().saveApplicant(applicant, user1, null, null, null, errorList, applicantform.getErrorListCustomVariable(), applicantform.getFormVariableDataList());
      


      errorList = applicant.getErrorList();
      if ((errorList != null) && (errorList.size() > 0)) {
        isSuccess = false;
      }
    }
    if (isSuccess)
    {
      request.setAttribute("applicantsaved", "yes");
      request.setAttribute("applicantdatacustom", "yes");
    }
    else
    {
      logger.info("in error condition");
      request.setAttribute("error_list", errorList);
    }
    applicantform.fromValue(applicant, request);
    
    List skilllist = BOFactory.getLovTXBO().getTechnicalSkillList(user1.getSuper_user_key());
    applicantform.setLovList(skilllist);
    
    List jobtitleList = BOFactory.getJobRequistionBO().getJobTitlesActiveAndOpenListAssigedToUser(user1);
    

    applicantform.setJobtitleList(jobtitleList);
    
    List talentPoolList = TalentPoolDAO.getAllTalentPools(user1.getSuper_user_key());
    applicantform.setTalentPoolList(talentPoolList);
    
    BOFactory.getApplicantBO().commonLovPopulate(request, applicantform, applicant, user1.getSuper_user_key());
    BOFactory.getApplicantBO().commonSourceLovPopulate(applicantform, applicant);
    if (!isSuccess) {
      applicantform.setFormVariablesList(VariableDataCaptureUtil.mergeCustomVariableValues(formVariablesList, formVariableDataList, request));
    }
    return mapping.findForward("createPageApplicantPool");
  }
  
  public ActionForward serachinapplicantpool(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside serachinapplicantpool method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    String primarySkill = request.getParameter("primarySkill");
    ApplicantForm applicantform = (ApplicantForm)form;
    ActionForward forward = new ActionForward();
    applicantform.setFullName(applicantform.getFullName());
    applicantform.setApplicantId(applicantform.getApplicantId());
    request.getSession().setAttribute("issearchresult", "yes");
    request.getSession().setAttribute("fullName", applicantform.getFullName());
    
    request.getSession().setAttribute("applicantIdvalue", String.valueOf(applicantform.getApplicantId()));
    
    request.getSession().setAttribute("primarySkill", primarySkill);
    request.getSession().setAttribute("keywords", applicantform.getKeywords());
    
    forward.setRedirect(true);
    forward.setPath("jsp/jtree/talentPool.jsp");
    
    return forward;
  }
  
  public ActionForward updateapplicantPoolData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updateapplicantPoolData method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ApplicantForm applicantform = (ApplicantForm)form;
    

    String dateofbirth = request.getParameter("dateofbirth");
    String applicantId = request.getParameter("applicantId");
    applicantform.setScreenCode("APPLICANT_SCREEN_TALENTPOOL");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetails(applicantId);
    

    String rname = applicant.getResumename();
    
    applicantform.toValue(applicant, request);
    String attachmentsizeexceed = (String)request.getAttribute("attachmentsizeexceed");
    applicant.setStatus("T");
    if (!StringUtils.isNullOrEmpty(applicant.getInterviewState())) {
      applicant.setInterviewState(applicant.getInterviewState());
    } else {
      applicant.setInterviewState("Talent Pool");
    }
    String gender = request.getParameter("gender");
    logger.info(gender);
    
    boolean isError = false;
    if (!StringUtils.isNullOrEmpty(dateofbirth))
    {
      String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
      
      String converteddate = DateUtil.convertFromTimezoneToTimezoneDate(dateofbirth, datepattern, user1.getTimezone().getTimezoneCode(), TimeZone.getDefault().getID());
      


      logger.info("converteddate" + converteddate);
      
      Calendar cal = DateUtil.convertStringDateToCalendar(converteddate, datepattern);
      

      applicant.setDateofbirth(cal.getTime());
    }
    if (applicantform.getRequitionId() > 0L) {
      applicant.setReqId(applicantform.getRequitionId());
    }
    applicant.setGender(gender);
    applicant.setOwner(user1);
    
    boolean isAttachmentupdata = true;
    if ((rname != null) && (StringUtils.isNullOrEmpty(applicantform.getResumename())))
    {
      applicant.setResumename(rname);
      isAttachmentupdata = false;
    }
    List formVariablesList = applicantform.getFormVariablesList();
    List formVariableDataList = applicantform.getFormVariableDataList();
    boolean isSuccess = true;
    List errorList = new ArrayList();
    if ((!StringUtils.isNullOrEmpty(attachmentsizeexceed)) && (attachmentsizeexceed.equals("yes")))
    {
      request.setAttribute("attachmentsizeexceed", "yes");
      isSuccess = false;
    }
    else
    {
      applicant = BOFactory.getApplicantBO().updateApplicant(applicant, user1, null, null, null, null, errorList, applicantform.getErrorListCustomVariable(), applicantform.getFormVariableDataList(), isAttachmentupdata);
      



      errorList = applicant.getErrorList();
      if ((errorList != null) && (errorList.size() > 0)) {
        isSuccess = false;
      }
    }
    if (isSuccess)
    {
      if (applicant.getReqId() > 0L) {
        request.setAttribute("applicantmoved", "yes");
      } else {
        request.setAttribute("applicantupdated", "yes");
      }
      request.setAttribute("applicantdatacustom", "yes");
    }
    else
    {
      logger.info("in error condition");
      request.setAttribute("error_list", errorList);
    }
    applicantform.fromValue(applicant, request);
    
    List jobtitleList = BOFactory.getJobRequistionBO().getJobTitlesActiveAndOpenListAssigedToUser(user1);
    

    applicantform.setJobtitleList(jobtitleList);
    

    List talentPoolList = TalentPoolDAO.getAllTalentPools(user1.getSuper_user_key());
    applicantform.setTalentPoolList(talentPoolList);
    
    applicantform.setTalentPoolId(applicant.getTalentpoolid());
    
    BOFactory.getApplicantBO().commonLovPopulate(request, applicantform, applicant, user1.getSuper_user_key());
    BOFactory.getApplicantBO().commonSourceLovPopulate(applicantform, applicant);
    if (!isSuccess) {
      applicantform.setFormVariablesList(VariableDataCaptureUtil.mergeCustomVariableValues(formVariablesList, formVariableDataList, request));
    }
    return mapping.findForward("createPageApplicantPool");
  }
  
  public ActionForward serachinapplicantpoolpopuppage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside serachinapplicantpoolpopuppage method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    String id = request.getParameter("id");
    ApplicantForm applicantform = (ApplicantForm)form;
    applicantform.setLovList(BOFactory.getLovTXBO().getTechnicalSkillList(user1.getSuper_user_key()));
    request.setAttribute("id", id);
    return mapping.findForward("applicantsinpool");
  }
  
  public ActionForward editapplicantPoolData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside editapplicantPoolData method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ApplicantForm applicantform = (ApplicantForm)form;
    
    String id = request.getParameter("id");
    String applicantid = request.getParameter("applicantid");
    applicantform.setScreenCode("APPLICANT_SCREEN_TALENTPOOL");
    TalentPoolElements te = null;
    if (!StringUtils.isNullOrEmpty(applicantid))
    {
      te = TalentPoolDAO.getTalentPoolElementByApplicantId(new Long(applicantid).longValue());
    }
    else
    {
      if ((id != null) && (id.indexOf("id=") > -1))
      {
        id = id.replaceAll("id=", "");
        id = id.replaceAll("'", "");
      }
      te = TalentPoolDAO.getTalentPoolElement(new Long(id).longValue());
    }
    if ((te == null) || (te.getSlave() == 0))
    {
      request.setAttribute("id", id);
      List skilllist = BOFactory.getLovTXBO().getTechnicalSkillList(user1.getSuper_user_key());
      applicantform.setLovList(skilllist);
      return mapping.findForward("applicantsinpool");
    }
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetails(String.valueOf(te.getApplicantId()));
    
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
    List jobtitleList = BOFactory.getJobRequistionBO().getJobTitlesActiveAndOpenListAssigedToUser(user1);
    
    applicantform.setJobtitleList(jobtitleList);
    applicantform.fromValue(applicant, request);
    applicantform.setTalentPoolId(te.getApplicantpoolidInitial());
    



    applicantform.setCurrectctc(applicant.getCurrectctc());
    applicantform.setExpectedctc(applicant.getExpectedctc());
    applicantform.setCurrectctccurrencycode(applicant.getCurrectctccurrencycode());
    
    applicantform.setExpectedctccurrencycode(applicant.getExpectedctccurrencycode());
    

    List talentPoolList = TalentPoolDAO.getAllTalentPoolsAssignedToUser(user1.getUserId());
    applicantform.setTalentPoolList(talentPoolList);
    
    BOFactory.getApplicantBO().commonLovPopulate(request, applicantform, applicant, user1.getSuper_user_key());
    BOFactory.getApplicantBO().commonSourceLovPopulate(applicantform, applicant);
    

    request.setAttribute("applicantsaved", "no");
    
    return mapping.findForward("createPageApplicantPool");
  }
  
  public ActionForward loadResumeSources(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside loadResumeSources method");
    String typeid = request.getParameter("typeid");
    logger.info("typeid" + typeid);
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
  
  public ActionForward saveapplicantData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveapplicantData method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    List errorList = new ArrayList();
    try
    {
      ApplicantForm applicantform = (ApplicantForm)form;
      JobApplicant applicant = new JobApplicant();
      String dateofbirth = request.getParameter("dateofbirth");
      String gender = request.getParameter("gender");
      if (!StringUtils.isNullOrEmpty(dateofbirth))
      {
        String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
        
        String converteddate = DateUtil.convertFromTimezoneToTimezoneDate(dateofbirth, datepattern, user1.getTimezone().getTimezoneCode(), TimeZone.getDefault().getID());
        




        logger.info("converteddate" + converteddate);
        
        Calendar cal = DateUtil.convertStringDateToCalendar(converteddate, datepattern);
        

        applicant.setDateofbirth(cal.getTime());
      }
      applicant.setGender(gender);
      

      applicant = BOFactory.getApplicantBO().saveapplicantData(applicantform, request, applicant, errorList, user1);
      

      applicantform.fromValue(applicant, request);
      if (applicantform.getRequitionId() != 0L)
      {
        JobRequisition jobreq = BOFactory.getJobRequistionBO().getActiveOpenJobRequision(String.valueOf(applicantform.getRequitionId()));
        if (jobreq != null)
        {
          applicantform.setRequitionId(jobreq.getJobreqId());
          applicantform.setJobTitle(jobreq.getJobTitle());
          applicantform.setRequisitionName(jobreq.getJobreqName());
        }
      }
    }
    catch (Exception e)
    {
      logger.info("in exception condition" + e.getMessage());
      
      String msg = Constant.getResourceStringValue("server.error", user1.getLocale());
      if (e.getMessage() == null) {
        request.setAttribute(Common.ERROR_MSG, msg);
      } else {
        request.setAttribute(Common.ERROR_MSG, e.getMessage());
      }
      request.setAttribute("error_list", errorList);
      return mapping.findForward("exception");
    }
    return mapping.findForward("createPage");
  }
  
  public ActionForward movetoreqtalentpoolsrc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside movetoreqtalentpoolsrc method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ApplicantForm applicantform = (ApplicantForm)form;
    
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("uuid");
    String istaneltpoollist = request.getParameter("istaneltpoollist");
    

    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    
    List jobtitleList = BOFactory.getJobRequistionBO().getJobTitlesActiveAndOpenListAssigedToUser(user1);
    
    applicantform.setJobtitleList(jobtitleList);
    List talentPoolList = TalentPoolDAO.getAllTalentPools(user1.getSuper_user_key());
    applicantform.setTalentPoolList(talentPoolList);
    applicantform.setApplicantId(new Long(applicantId).longValue());
    applicantform.setUuid(uuid);
    if ((!StringUtils.isNullOrEmpty(istaneltpoollist)) && (!istaneltpoollist.equals("true"))) {
      request.setAttribute("istaneltpoollist", "no");
    } else {
      request.setAttribute("istaneltpoollist", "yes");
    }
    return mapping.findForward("movetoreqtalentpool");
  }
  
  public ActionForward movetoreqtalentpool(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside movetoreqtalentpool method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ApplicantForm applicantform = (ApplicantForm)form;
    
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("uuid");
    String istaneltpoollist = request.getParameter("istaneltpoollist");
    
    logger.info("Inside movetoreqtalentpool method" + applicantId);
    logger.info("Inside movetoreqtalentpool method" + uuid);
    logger.info("Inside movetoreqtalentpool method" + istaneltpoollist);
    boolean isTalentPoolMove = false;
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    
    List jobtitleList = BOFactory.getJobRequistionBO().getJobTitlesActiveAndOpenListAssigedToUser(user1);
    
    applicantform.setJobtitleList(jobtitleList);
    List talentPoolList = TalentPoolDAO.getAllTalentPools(user1.getSuper_user_key());
    applicantform.setTalentPoolList(talentPoolList);
    String type = null;
    long oldReqId = applicant.getReqId();
    String oldJobTitle = applicant.getReqName();
    List errorList = new ArrayList();
    try
    {
      if ((!StringUtils.isNullOrEmpty(istaneltpoollist)) && (!istaneltpoollist.equals("true")))
      {
        if ((applicant.getReqId() != 0L) && (applicant.getReqId() != applicantform.getRequitionId())) {
          type = "REQ_TO_REQ_MOVE";
        } else if ((applicant.getReqId() == 0L) && (applicant.getReqId() != applicantform.getRequitionId())) {
          type = "TALENT_POOL_TO_REQ_MOVE";
        }
        applicant.setReqId(applicantform.getRequitionId());
        isTalentPoolMove = false;
        
        errorList = BOFactory.getApplicantBO().validateApplicantDataBeforeMoveToRequistion(request, applicant, user1);
        if (errorList.size() < 1) {
          request.setAttribute("applicantmovedtoreq", "yes");
        }
      }
      else
      {
        if (applicant.getReqId() != 0L) {
          type = "REQ_TO_TALENT_POOL_MOVE";
        } else if (applicant.getReqId() == 0L) {
          type = "TALENT_POOL_TO_TALENT_POOL_MOVE";
        }
        applicant.setTalentpoolid(applicantform.getTalentPoolId());
        isTalentPoolMove = true;
        request.setAttribute("applicantmovedtotalentpool", "yes");
      }
      if (errorList.size() < 1)
      {
        BOFactory.getApplicantTXBO().moveToTalentpoolORrequistion(applicant, user1, applicantform.getNote(), isTalentPoolMove, type, oldReqId, oldJobTitle);
      }
      else
      {
        logger.info("in error condition");
        request.setAttribute("error_list", errorList);
      }
    }
    catch (Exception e)
    {
      logger.info("in exception condition");
      request.setAttribute("error_list", errorList);
    }
    return mapping.findForward("movetoreqtalentpool");
  }
  
  public ActionForward editapplicant(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside editapplicant method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    String backurl = request.getParameter("backurl");
    logger.info("backurlbackurl" + backurl);
    ApplicantForm applicantform = (ApplicantForm)form;
    
    String applicantId = request.getParameter("applicantId");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetails(applicantId);
    
    List attachlist = BOFactory.getApplicantBO().getApplicantAttachments(applicant.getApplicantId(), applicant.getUuid(), Common.APPLICANT_ATTACHMENT_TYPE_RESUME);
    if ((attachlist != null) && (attachlist.size() > 0)) {
      applicant.setApplicantAttachment((ApplicantAttachments)attachlist.get(0));
    }
    List jobtitleList = BOFactory.getJobRequistionBO().getJobTitlesActiveAndOpenListAssigedToUser(user1);
    

    applicantform.setJobtitleList(jobtitleList);
    applicantform.fromValue(applicant, request);
    
    BOFactory.getApplicantBO().commonLovPopulate(request, applicantform, applicant, user1.getSuper_user_key());
    


    applicantform.setCurrectctc(applicant.getCurrectctc());
    applicantform.setExpectedctc(applicant.getExpectedctc());
    applicantform.setCurrectctccurrencycode(applicant.getCurrectctccurrencycode());
    
    applicantform.setExpectedctccurrencycode(applicant.getExpectedctccurrencycode());
    

    BOFactory.getApplicantBO().commonSourceLovPopulate(applicantform, applicant);
    



    request.setAttribute("applicantsaved", "no");
    
    return mapping.findForward("createPage");
  }
  
  public ActionForward deleteResume(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteResume method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    
    ApplicantForm applicantform = (ApplicantForm)form;
    
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("uuid");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    if (applicant == null) {
      return null;
    }
    List attachlist = BOFactory.getApplicantBO().getApplicantAttachments(applicant.getApplicantId(), applicant.getUuid(), Common.APPLICANT_ATTACHMENT_TYPE_RESUME);
    if ((attachlist != null) && (attachlist.size() > 0)) {
      applicant.setApplicantAttachment((ApplicantAttachments)attachlist.get(0));
    }
    if ((applicant.getApplicantAttachment() != null) && (applicant.getResumename() != null) && (applicant.getResumename().length() > 0))
    {
      BOFactory.getApplicantBO().deleteResume(new Long(applicantId).longValue(), Common.APPLICANT_ATTACHMENT_TYPE_RESUME);
      
      applicant.setResumename(null);
      applicantform.setResumedetails("");
      BOFactory.getApplicantBO().updateApplicant(applicant);
      
      String filePath = Constant.getValue("ATTACHMENT_PATH");
      filePath = filePath + File.separator + "applicantAttachment" + File.separator + applicant.getApplicantAttachment().getUuid() + File.separator;
      BOFactory.getApplicantBO().deleteAttachmentDirFromFileServer(filePath, applicant.getApplicantAttachment().getUuid());
    }
    List jobtitleList = BOFactory.getJobRequistionBO().getJobTitlesActiveAndOpenListAssigedToUser(user1);
    

    applicantform.setJobtitleList(jobtitleList);
    applicantform.fromValue(applicant, request);
    
    BOFactory.getApplicantBO().commonLovPopulate(request, applicantform, applicant, user1.getSuper_user_key());
    


    applicantform.setCurrectctc(applicant.getCurrectctc());
    applicantform.setExpectedctc(applicant.getExpectedctc());
    applicantform.setCurrectctccurrencycode(applicant.getCurrectctccurrencycode());
    
    applicantform.setExpectedctccurrencycode(applicant.getExpectedctccurrencycode());
    

    BOFactory.getApplicantBO().commonSourceLovPopulate(applicantform, applicant);
    

    request.setAttribute("applicantsaved", "no");
    
    return mapping.findForward("createPage");
  }
  
  public ActionForward updateapplicantData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updateapplicantData method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ApplicantForm applicantform = (ApplicantForm)form;
    
    List errorList = new ArrayList();
    try
    {
      String dateofbirth = request.getParameter("dateofbirth");
      String applicantId = request.getParameter("applicantId");
      JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetails(applicantId);
      
      String rname = applicant.getResumename();
      String gender = request.getParameter("gender");
      
      boolean isReqChanged = false;
      long oldReqId = applicant.getReqId();
      String oldJobTitle = applicant.getJobTitle();
      if ((applicant.getReqId() != 0L) && (applicant.getReqId() != applicantform.getRequitionId())) {
        if ((applicant.getInterviewState() != null) && (applicant.getInterviewState().equals("Application Submitted"))) {
          isReqChanged = true;
        }
      }
      if (!StringUtils.isNullOrEmpty(dateofbirth))
      {
        String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
        
        String converteddate = DateUtil.convertFromTimezoneToTimezoneDate(dateofbirth, datepattern, user1.getTimezone().getTimezoneCode(), TimeZone.getDefault().getID());
        




        logger.info("converteddate" + converteddate);
        
        Calendar cal = DateUtil.convertStringDateToCalendar(converteddate, datepattern);
        

        applicant.setDateofbirth(cal.getTime());
      }
      applicant.setGender(gender);
      applicant = BOFactory.getApplicantBO().updateApplicantData(applicantform, request, applicant, errorList, rname, isReqChanged, oldReqId, oldJobTitle, user1);
      
      applicantform.fromValue(applicant, request);
    }
    catch (Exception e)
    {
      logger.info("in exception condition");
      request.setAttribute("error_list", errorList);
    }
    return mapping.findForward("createPage");
  }
  
  public ActionForward applicantDetailsPopup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside applicantDetails method");
    ApplicantForm applicantform = (ApplicantForm)form;
    
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    JobApplicant applicant = BOFactory.getApplicantBO().applicantDetails(applicantform, request, applicantId, uuid);
    if (applicant != null) {
      applicantform.fromValue(applicant, request);
    }
    return mapping.findForward("applicantDetailsPopup");
  }
  
  public ActionForward applicantDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside applicantDetails method");
    ApplicantForm applicantform = (ApplicantForm)form;
    
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    String backtolisturl = request.getParameter("backtolisturl");
    JobApplicant applicant = BOFactory.getApplicantBO().applicantDetails(applicantform, request, applicantId, uuid);
    if (applicant != null)
    {
      applicantform.fromValue(applicant, request);
      applicantform.setScreenCode(applicant.getScreenCode());
      BOFactory.getApplicantBO().fromValueCustomVariablesAllForApplicant(request, applicantform);
    }
    if (backtolisturl != null) {
      applicantform.setBackurltoapplicantlist(backtolisturl);
    }
    if (backtolisturl != null) {
      applicantform.setBackurltoapplicantlist(backtolisturl);
    }
    return mapping.findForward("applicantDetails");
  }
  
  public ActionForward getEvaluationGraph(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside getEvaluationGraph method");
    String uuid = request.getParameter("uuid");
    
    long appid = BOFactory.getApplicantBO().getApplicantIdByUuid(uuid);
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(String.valueOf(appid), uuid);
    ApplicantForm applicantform = (ApplicantForm)form;
    applicantform.setApplicantId(applicant.getApplicantId());
    List comptetencyList = BOFactory.getJobRequistionBO().getJobRequisionTemplateComptetencyList(applicant.getReqId(), "job");
    List accomplishmentList = BOFactory.getJobRequistionBO().getJobRequisionTemplateAccomplishmentList(applicant.getReqId(), "job");
    applicantform.setAccomplishmentList(accomplishmentList);
    applicantform.setComptetencyList(comptetencyList);
    
    return mapping.findForward("getEvaluationGraph");
  }
  
  public ActionForward recallapplicantscr(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside recallapplicantscr method");
    ApplicantForm applicantform = (ApplicantForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    applicantform.setApplicantId(new Long(applicantId).longValue());
    applicantform.setUuid(uuid);
    
    return mapping.findForward("recallapplicantscr");
  }
  
  public ActionForward undodeletescr(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside undodeletescr method");
    ApplicantForm applicantform = (ApplicantForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    applicantform.setApplicantId(new Long(applicantId).longValue());
    applicantform.setUuid(uuid);
    
    return mapping.findForward("undodelete");
  }
  
  public ActionForward addapplicanttagscr(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside addapplicanttagscr method");
    ApplicantForm applicantform = (ApplicantForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    String tagid = request.getParameter("tagid");
    applicantform.setApplicantId(new Long(applicantId).longValue());
    applicantform.setUuid(uuid);
    
    List tagList = BOFactory.getLovBO().getTagsList("APPLICANT", user1.getSuper_user_key());
    if (!StringUtils.isNullOrEmpty(tagid)) {
      applicantform.setTagId(new Long(tagid).longValue());
    }
    applicantform.setTagList(tagList);
    
    return mapping.findForward("tagapplicant");
  }
  
  public ActionForward addapplicanttag(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside addapplicanttag method");
    ApplicantForm applicantform = (ApplicantForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    String applicantId = request.getParameter("applicantId");
    String tagname = request.getParameter("tagname");
    applicantform.setApplicantId(new Long(applicantId).longValue());
    ApplicantTags apptag = new ApplicantTags();
    apptag.setApplicantId(new Long(applicantId).longValue());
    apptag.setTagname(tagname);
    BOFactory.getTagsBO().saveApplicantTag(user1, apptag);
    

    return null;
  }
  
  public ActionForward deleteApplicanttag(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteApplicanttag method");
    ApplicantForm applicantform = (ApplicantForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    String applicantId = request.getParameter("applicantId");
    String tagname = request.getParameter("tagname");
    applicantform.setApplicantId(new Long(applicantId).longValue());
    
    BOFactory.getTagsBO().deleteApplicantTag(user1, new Long(applicantId).longValue(), tagname);
    

    return null;
  }
  
  public ActionForward recallapplicant(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside recallapplicant method");
    
    ApplicantForm applicantform = (ApplicantForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    String comment = request.getParameter("comment");
    logger.info("comment" + comment);
    


    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    
    JobApplicationEvent event = BOFactory.getApplicantBO().getLastEvent(new Long(applicantId).longValue());
    
    logger.info("event" + event);
    if (event != null)
    {
      String intstste = event.getInterviewState();
      logger.info("intstste" + intstste);
      String tmp = "Rejected-";
      if (intstste.indexOf("->") > 0) {
        intstste = intstste.substring(0, intstste.indexOf("->") - 1);
      }
      applicant.setInterviewState(intstste);
      applicant.setStatus("A");
      applicant.setRejectionreasonId(0);
      applicant.setRejectionreasonname(null);
      
      event.setInterviewState(intstste);
      event.setStatus(0);
      event.setInterviewerComments("");
      event.setUpdatedBy(null);
      event.setUpdatedByName(null);
      event.setUpdatedDate(null);
      

      applicant.setOwner(event.getOwner());
      applicant.setIsGroup("N");
      


      applicant = BOFactory.getApplicantTXBO().recallapplicant(applicant, event, user1, comment, null);
    }
    else
    {
      ApplicantActivity activity = BOFactory.getApplicantBO().getLastActivity(new Long(applicantId).longValue(), uuid);
      
      String intstste = activity.getActivityName();
      
      intstste = intstste.substring(0, intstste.indexOf("->") - 1);
      

      applicant.setInterviewState(intstste);
      applicant.setStatus("A");
      applicant.setRejectionreasonId(0);
      applicant.setRejectionreasonname(null);
      

      applicant = BOFactory.getApplicantTXBO().recallapplicantOnDragdrop(applicant, activity, user1, comment, null);
    }
    request.setAttribute("recalldone", "yes");
    
    return mapping.findForward("recallapplicantscr");
  }
  
  public ActionForward undodelete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside undodelete method");
    ApplicantForm applicantform = (ApplicantForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    String comment = request.getParameter("comment");
    
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    
    String intstste = applicant.getInterviewState();
    String tmp = "Mark deleted-";
    
    intstste = intstste.substring(tmp.length());
    
    applicant.setInterviewState(intstste);
    applicant.setStatus("A");
    applicant.setUpdatedBy(user1.getUserName());
    applicant.setUpdatedDate(new Date());
    
    applicantform.setApplicantId(new Long(applicantId).longValue());
    applicantform.setUuid(uuid);
    


    applicant = BOFactory.getApplicantTXBO().undodelete(applicant, user1, comment, null);
    

    request.setAttribute("undodelete", "yes");
    return mapping.findForward("undodelete");
  }
  
  public ActionForward applicantDetailsForCompare(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside applicantDetailsForCompare method");
    ApplicantForm applicantform = (ApplicantForm)form;
    
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    
    JobApplicant applicant = BOFactory.getApplicantBO().applicantDetails(applicantform, request, applicantId, uuid);
    if (applicant != null)
    {
      applicantform.fromValue(applicant, request);
      applicantform.setScreenCode(applicant.getScreenCode());
      BOFactory.getApplicantBO().fromValueCustomVariablesAllForApplicant(request, applicantform);
    }
    return mapping.findForward("applicantDetailsForCompare");
  }
  
  public ActionForward applicantevaluationcriteraajax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside applicantevaluationcriteraajax method");
    ApplicantForm applicantform = (ApplicantForm)form;
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    BOFactory.getApplicantBO().applicantevaluationcriteraajax(applicantform, applicantId, uuid);
    
    return mapping.findForward("applicantevaluationcriteraajax");
  }
  
  public ActionForward filtersExecuteSilents(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside filtersExecuteSilents method");
    ApplicantForm applicantform = (ApplicantForm)form;
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    User user1 = (User)request.getSession().getAttribute("user_data");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    List variableValueList = BOFactory.getVariableBO().getVariablesValues(applicant.getApplicantId(), applicant.getScreenCode());
    logger.info("value variableValueList" + variableValueList);
    List errorList = BOFactory.getBusinessFilterBO().isApplicantValidationSuccessedWithSilentFilters(applicant, applicant.getReqId(), variableValueList, user1);
    if ((errorList != null) && (errorList.size() > 0)) {
      request.setAttribute("error_list", errorList);
    }
    return mapping.findForward("filtersExecuteSilents");
  }
  
  public ActionForward applicantDetailsajax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside applicantDetailsajax method");
    ApplicantForm applicantform = (ApplicantForm)form;
    
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    if (applicant != null)
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
        
        filePath = Constant.getValue("FILE_UPLOAD") + applicant.getResumename();
        
        request.setAttribute("filePath", filePath);
      }
    }
    if (applicant != null)
    {
      applicantform.fromValue(applicant, request);
      if ((applicant.getGender() != null) && (applicant.getGender().equals("M"))) {
        applicantform.setGender("Male");
      } else if ((applicant.getGender() != null) && (applicant.getGender().equals("F"))) {
        applicantform.setGender("Female");
      }
    }
    return mapping.findForward("applicantDetailsajax");
  }
  
  public ActionForward resumedetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside resumedetails method");
    ApplicantForm applicantform = (ApplicantForm)form;
    
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    if (applicant != null)
    {
      applicantform.fromValue(applicant, request);
      if ((applicant.getGender() != null) && (applicant.getGender().equals("M"))) {
        applicantform.setGender("Male");
      } else if ((applicant.getGender() != null) && (applicant.getGender().equals("F"))) {
        applicantform.setGender("Female");
      }
    }
    return mapping.findForward("resumedetails");
  }
  
  public ActionForward applicantDetailstab(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside applicantDetailstab method");
    ApplicantForm applicantform = (ApplicantForm)form;
    
    String applicantId = request.getParameter("applicantId");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetails(applicantId);
    if (applicant != null)
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
        
        filePath = Constant.getValue("FILE_UPLOAD") + applicant.getResumename();
        
        request.setAttribute("filePath", filePath);
      }
    }
    applicantform.fromValue(applicant, request);
    if (applicant.getGender().equals("M")) {
      applicantform.setGender("Male");
    } else {
      applicantform.setGender("Female");
    }
    return mapping.findForward("applicantDetailstab");
  }
  
  public ActionForward applicantReferenceDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside applicantReferenceDetails method");
    ApplicantForm applicantform = (ApplicantForm)form;
    
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    if (applicant != null)
    {
      applicantform.fromValue(applicant, request);
      
      List refList = BOFactory.getApplicantBO().getAllReferences(applicant.getApplicantId());
      
      applicantform.setRefrenceslist(refList);
    }
    return mapping.findForward("applicantReferenceDetails");
  }
  
  public ActionForward applicantReferenceDetailstree(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside applicantReferenceDetailstree method");
    ApplicantForm applicantform = (ApplicantForm)form;
    
    String applicantId = request.getParameter("applicantId");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetails(applicantId);
    



    applicantform.fromValue(applicant, request);
    
    List refList = BOFactory.getApplicantBO().getAllReferences(applicant.getApplicantId());
    
    applicantform.setRefrenceslist(refList);
    

    return mapping.findForward("jobreqtreelist");
  }
  
  public ActionForward applicantReferenceDetailsajax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside applicantReferenceDetailsajax method");
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
    return mapping.findForward("applicantReferenceDetailsajax");
  }
  
  public ActionForward applicantlogdetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside applicantlogdetails method");
    ApplicantForm applicantform = (ApplicantForm)form;
    
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    if (applicant != null)
    {
      applicantform.fromValue(applicant, request);
      List eventList = BOFactory.getApplicantBO().getApplicationEventList(applicantId);
      
      logger.info("interview events size" + eventList.size());
      
      applicantform.setEventList(eventList);
    }
    return mapping.findForward("applicantlogdetails");
  }
  
  public ActionForward applicantresumedetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside applicantresumedetails method");
    ApplicantForm applicantform = (ApplicantForm)form;
    
    String applicantId = request.getParameter("applicantId");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetails(applicantId);
    if (applicant != null)
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
        RandomAccessFile raf = new RandomAccessFile(filePath + applicant.getApplicantId() + ".html", "rw");
        

        int length = (int)blob.length();
        byte[] _blob = blob.getBytes(1L, length);
        raf.write(_blob);
        raf.close();
        
        filePath = Constant.getValue("FILE_UPLOAD") + applicant.getApplicantId() + ".html";
        
        request.setAttribute("filePath", filePath);
      }
    }
    return mapping.findForward("applicantresumedetails");
  }
  
  public ActionForward applicantDetailstree(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside applicantDetailstree method");
    ApplicantForm applicantform = (ApplicantForm)form;
    
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    
    JobApplicant applicant = BOFactory.getApplicantBO().applicantDetails(applicantform, request, applicantId, uuid);
    if (applicant != null) {
      applicantform.fromValue(applicant, request);
    }
    request.getSession().setAttribute("app_id", applicantId);
    
    return mapping.findForward("jobreqtreelist");
  }
  
  public ActionForward recruiterApplicantDetailstree(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside recruiterApplicantDetailstree method");
    ApplicantForm applicantform = (ApplicantForm)form;
    
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    JobApplicant applicant = BOFactory.getApplicantBO().requistionApplicantDetailstree(applicantform, request, applicantId, uuid);
    if (applicant != null) {
      applicantform.fromValue(applicant, request);
    }
    request.getSession().setAttribute("app_id", applicantId);
    return mapping.findForward("recruiterjobreqtreelist");
  }
  
  public ActionForward requistionApplicantDetailstree(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside requistionApplicantDetailstree method");
    ApplicantForm applicantform = (ApplicantForm)form;
    
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    
    JobApplicant applicant = BOFactory.getApplicantBO().requistionApplicantDetailstree(applicantform, request, applicantId, uuid);
    if (applicant != null)
    {
      applicantform.fromValue(applicant, request);
      applicantform.setScreenCode(applicant.getScreenCode());
      BOFactory.getApplicantBO().fromValueCustomVariablesAllForApplicant(request, applicantform);
    }
    request.getSession().setAttribute("app_id", applicantId);
    return mapping.findForward("jobrequistiontreelist");
  }
  
  public ActionForward applicantDetailsForTree(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside applicantDetailsForTree method");
    ApplicantForm applicantform = (ApplicantForm)form;
    

    String uuid = request.getParameter("secureid");
    String backtolisturl = request.getParameter("backtolisturl");
    JobApplicant applicanttemp = BOFactory.getApplicantBO().getApplicantIdNameEmailByUUID(uuid);
    JobApplicant applicant = BOFactory.getApplicantBO().requistionApplicantDetailstree(applicantform, request, String.valueOf(applicanttemp.getApplicantId()), uuid);
    if (applicant != null)
    {
      applicantform.fromValue(applicant, request);
      applicantform.setScreenCode(applicant.getScreenCode());
      BOFactory.getApplicantBO().fromValueCustomVariablesAllForApplicant(request, applicantform);
    }
    if ((!StringUtils.isNullOrEmpty(backtolisturl)) && (!backtolisturl.equals("null")))
    {
      applicantform.setBackurltoapplicantlist(backtolisturl);
      applicantform.setBackUrl(backtolisturl);
    }
    return mapping.findForward("applicantDetailsForTree");
  }
  
  public ActionForward applicantDetailsForTreeRightTab(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside applicantDetailsForTreeRightTab method");
    ApplicantForm applicantform = (ApplicantForm)form;
    

    String uuid = request.getParameter("secureid");
    JobApplicant applicanttemp = BOFactory.getApplicantBO().getApplicantIdNameEmailByUUID(uuid);
    JobApplicant applicant = BOFactory.getApplicantBO().requistionApplicantDetailstree(applicantform, request, String.valueOf(applicanttemp.getApplicantId()), uuid);
    if (applicant != null)
    {
      applicantform.fromValue(applicant, request);
      applicantform.setScreenCode(applicant.getScreenCode());
    }
    return mapping.findForward("applicantDetailsForTreeRightTab");
  }
  
  public ActionForward applicantDetailstreeajax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside applicantDetailstreeajax method");
    ApplicantForm applicantform = (ApplicantForm)form;
    
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    JobApplicant applicant = BOFactory.getApplicantBO().requistionApplicantDetailstree(applicantform, request, applicantId, uuid);
    if (applicant != null) {
      applicantform.fromValue(applicant, request);
    }
    return mapping.findForward("jobreqtreelistbyorg");
  }
  
  public ActionForward resumeDetailstree(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside resumeDetailstree method");
    ApplicantForm applicantform = (ApplicantForm)form;
    
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    if (applicant != null)
    {
      applicantform.fromValue(applicant, request);
      if ((applicant.getGender() != null) && (applicant.getGender().equals("M"))) {
        applicantform.setGender("Male");
      } else if ((applicant.getGender() != null) && (applicant.getGender().equals("F"))) {
        applicantform.setGender("Female");
      }
    }
    return mapping.findForward("jobreqtreelist");
  }
  
  public ActionForward attachmentdetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside attachmentdetails method");
    
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    String attachmentuuid = request.getParameter("attachmentuuid");
    String fname = request.getParameter("fname");
    
    ApplicantAttachments appattach = BOFactory.getApplicantBO().getApplicantAttachments(new Long(applicantId).longValue(), Common.APPLICANT_ATTACHMENT_TYPE_RESUME);
    if (appattach != null)
    {
      String filePath = Constant.getValue("ATTACHMENT_PATH");
      filePath = "applicantAttachment" + File.separator + appattach.getUuid() + File.separator + appattach.getAttahmentname();
      
      request.setAttribute("filePath", filePath);
    }
    else
    {
      String filePath = "applicantAttachment" + File.separator + attachmentuuid + File.separator + fname;
      request.setAttribute("filePath", filePath);
    }
    return mapping.findForward("attachmentdetails");
  }
  
  public ActionForward resumeDetailsajax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside resumeDetailsajax method");
    ApplicantForm applicantform = (ApplicantForm)form;
    
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    if (applicant != null)
    {
      ApplicantAttachments appattach = BOFactory.getApplicantBO().getApplicantAttachments(applicant.getApplicantId(), Common.APPLICANT_ATTACHMENT_TYPE_RESUME);
      if (appattach != null)
      {
        FileExtractor fileExt = new FileExtractor();
        String filePath = Constant.getValue("ATTACHMENT_PATH");
        filePath = filePath + File.separator + "applicantAttachment" + File.separator + appattach.getUuid() + File.separator;
        String content = fileExt.getFileContent(filePath + applicant.getResumename());
        
        applicantform.setResumedetails(content);
        applicantform.scribddocumentid = appattach.getScribddocumentid();
        applicantform.setScribddocumentkey(appattach.getScribddocumentkey());
      }
      else
      {
        applicantform.setResumedetails(applicant.getOtherdetails().getResumeDetails());
      }
    }
    return mapping.findForward("resumeDetailsajax");
  }
  
  public ActionForward applicantlogdetailstree(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside applicantlogdetailstree method");
    ApplicantForm applicantform = (ApplicantForm)form;
    
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    if (applicant != null)
    {
      applicantform.fromValue(applicant, request);
      List eventList = BOFactory.getApplicantBO().getApplicationEventList(applicantId);
      
      logger.info("interview events size" + eventList.size());
      
      applicantform.setEventList(eventList);
    }
    return mapping.findForward("jobreqtreelist");
  }
  
  public ActionForward markfordeletion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside markfordeletion method");
    ApplicantForm applicantform = (ApplicantForm)form;
    
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    
    applicantform.fromValue(applicant, request);
    return mapping.findForward("markfordeletion");
  }
  
  public ActionForward markfordeletionbulk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside markfordeletionbulk method");
    ApplicantForm applicantform = (ApplicantForm)form;
    
    String applicantids = request.getParameter("applicantids");
    request.setAttribute("applicantids", applicantids);
    
    return mapping.findForward("markfordeletionbulk");
  }
  
  public ActionForward markfordeletionbulksubmit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside markfordeletionbulksubmit method");
    ApplicantForm applicantform = (ApplicantForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    String applicantids = request.getParameter("applicantids");
    String comment = request.getParameter("comment");
    List applicantList = new ArrayList();
    if (!StringUtils.isNullOrEmpty(applicantids))
    {
      applicantids = applicantids.substring(0, applicantids.length() - 1);
      
      applicantList = StringUtils.tokenizeString(applicantids, ",");
      
      BOFactory.getApplicantBO().markDeleteApplicantsBySqlQuery(applicantList, user1, comment);
      

      request.setAttribute("markfordeletion", "yes");
      request.setAttribute("applicantids", applicantids);
    }
    return mapping.findForward("markfordeletionbulk");
  }
  
  public ActionForward markfordeletionsubmit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside markfordeletionsubmit method");
    ApplicantForm applicantform = (ApplicantForm)form;
    
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    String comment = request.getParameter("comment");
    User user1 = (User)request.getSession().getAttribute("user_data");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    

    applicant.setInterviewState("Mark deleted-" + applicant.getInterviewState());
    
    applicant.setStatus("D");
    applicant.setUpdatedBy(user1.getUserName());
    applicant.setUpdatedDate(new Date());
    

    List applicantList = new ArrayList();
    applicantList.add(uuid);
    BOFactory.getApplicantBO().markDeleteApplicantsBySqlQuery(applicantList, user1, comment);
    
    applicantform.fromValue(applicant, request);
    request.setAttribute("markfordeletion", "yes");
    return mapping.findForward("markfordeletion");
  }
  
  public ActionForward applicantlogdetailsajax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside applicantlogdetailsajax method");
    ApplicantForm applicantform = (ApplicantForm)form;
    
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    if (applicant != null)
    {
      applicantform.fromValue(applicant, request);
      List eventList = BOFactory.getApplicantBO().getApplicationEventListWithRatings(applicantId);
      
      logger.info("interview events size" + eventList.size());
      
      applicantform.setEventList(eventList);
    }
    return mapping.findForward("applicantlogdetailsajax");
  }
  
  public ActionForward uploadApplicantPhotoscr(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside uploadApplicantPhotoscr method ... ");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user1 = (User)request.getSession().getAttribute("user_data");
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    ApplicantForm applicantform = (ApplicantForm)form;
    applicantform.setUuid(uuid);
    applicantform.setApplicantId(new Long(applicantId).longValue());
    return mapping.findForward("uploadApplicantPhoto");
  }
  
  public ActionForward uploadApplicantPhoto(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside uploadApplicantPhoto method ... ");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user1 = (User)request.getSession().getAttribute("user_data");
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    
    ApplicantForm applicantform = (ApplicantForm)form;
    
    FormFile myFile = applicantform.getProfilePhoto();
    if (myFile != null)
    {
      String contentType = myFile.getContentType();
      String fileName = myFile.getFileName();
      int fileSize = myFile.getFileSize();
      byte[] fileData = myFile.getFileData();
      
      ApplicantProfilePhoto pp = new ApplicantProfilePhoto();
      Blob blob = null;
      
      blob = new SerialBlob(fileData);
      pp.setProfilePhoto(blob);
      
      pp.setUpdatedBy(user1.getUserName());
      pp.setUpdatedDate(new Date());
      if (applicant.getProfilePhotoId() > 0L) {
        pp.setProfilePhotoId(applicant.getProfilePhotoId());
      }
      pp = BOFactory.getApplicantBO().saveApplicantProfilePhoto(pp);
      
      applicant.setProfilePhotoId(pp.getProfilePhotoId());
      
      BOFactory.getApplicantBO().updateApplicant(applicant);
    }
    applicantform.setUuid(uuid);
    request.setAttribute("uploadApplicantPhoto", "yes");
    return mapping.findForward("uploadApplicantPhoto");
  }
  
  public ActionForward additionalDetailsajax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside additionalDetailsajax method");
    ApplicantForm applicantform = (ApplicantForm)form;
    
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    
    List eduList = BOFactory.getApplicantBO().getEducationsByApplicantAndType(new Long(applicantId).longValue(), Common.EDUCATION_COLLEGE);
    


    applicantform.setEducationsList(eduList);
    List certList = BOFactory.getApplicantBO().getEducationsByApplicantAndType(new Long(applicantId).longValue(), Common.EDUCATION_CERTIFICATION);
    


    applicantform.setCertificationsList(certList);
    List orgList = BOFactory.getApplicantBO().getPreviousOrgDetailsByApplicant(new Long(applicantId).longValue());
    

    applicantform.setPreviousOrgList(orgList);
    List appskillList = BOFactory.getApplicantBO().getSkillsByApplicant(new Long(applicantId).longValue());
    
    applicantform.setApplicantSkillList(appskillList);
    
    return mapping.findForward("additionalDetailsajax");
  }
  
  public ActionForward applicantofferdetailstree(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside applicantofferdetailstree method");
    ApplicantForm applicantform = (ApplicantForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    if (applicant != null)
    {
      applicantform.fromValue(applicant, request);
      
      applicantform.setOfferownerId(applicant.getOfferownerId());
      applicantform.setOfferownerName(applicant.getOfferownerName());
      
      applicantform.setCurrectctc(applicant.getCurrectctc());
      applicantform.setExpectedctc(applicant.getExpectedctc());
      applicantform.setCurrectctccurrencycode(applicant.getCurrectctccurrencycode());
      
      applicantform.setExpectedctccurrencycode(applicant.getExpectedctccurrencycode());
      

      applicantform.setOfferInitiationComment(applicant.getOfferInitiationComment());
      
      applicantform.setOfferinitiationdate(applicant.getOfferinitiationdate());
      
      applicantform.setOfferinitiatedby(applicant.getOfferinitiatedby());
      
      applicantform.setInterviewState(applicant.getInterviewState());
      
      List approverlist = BOFactory.getApplicantBO().getOfferApproverList(applicant.getApplicantId());
      

      applicantform.setOfferApproverList(approverlist);
      
      List attachmentList = BOFactory.getApplicantBO().getOfferAttachmentList(new Long(applicantId).longValue(), "initoffer");
      

      applicantform.setOfferAttachmentList(attachmentList);
      
      List offerreleaseattachmentList = BOFactory.getApplicantBO().getOfferAttachmentList(new Long(applicantId).longValue(), "reloffer");
      

      applicantform.setOfferReleaseAttachmentList(offerreleaseattachmentList);
      

      List appliocantattachmentList = BOFactory.getApplicantBO().getOfferAttachmentList(new Long(applicantId).longValue(), "applicantdoc");
      

      applicantform.setApplicantAttachmentList(appliocantattachmentList);
      
      List offerwatchlist = BOFactory.getApplicantBO().getOfferwatchListByApplicantId(applicant.getApplicantId(), "release_offer");
      


      List initiateofferwatchlist = BOFactory.getApplicantBO().getOfferwatchListByApplicantId(applicant.getApplicantId(), "initiate_offer");
      


      applicantform.setOfferwatchList(offerwatchlist);
      
      applicantform.setInitiateOfferwatchList(initiateofferwatchlist);
      if (applicant.getOfferedctc() != null) {
        applicantform.setOfferedctc(applicant.getOfferedctc());
      }
      if (applicant.getOfferedctccurrencycode() != null) {
        applicantform.setOfferedctccurrencycode(applicant.getOfferedctccurrencycode());
      }
    }
    logger.info("Inside applicantofferdetailstree method finish");
    return mapping.findForward("jobreqtreelist");
  }
  
  public ActionForward applicantofferdetailsajax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside applicantofferdetailsajax method");
    ApplicantForm applicantform = (ApplicantForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    if (applicant != null)
    {
      applicantform.fromValue(applicant, request);
      
      applicantform.setOfferownerId(applicant.getOfferownerId());
      applicantform.setOfferownerName(applicant.getOfferownerName());
      
      applicantform.setCurrectctc(applicant.getCurrectctc());
      applicantform.setExpectedctc(applicant.getExpectedctc());
      applicantform.setCurrectctccurrencycode(applicant.getCurrectctccurrencycode());
      applicantform.setExpectedctccurrencycode(applicant.getExpectedctccurrencycode());
      
      applicantform.setOfferInitiationComment(applicant.getOfferInitiationComment());
      applicantform.setOfferinitiationdate(applicant.getOfferinitiationdate());
      applicantform.setOfferinitiatedby(applicant.getOfferinitiatedby());
      
      applicantform.setInterviewState(applicant.getInterviewState());
      

      BOFactory.getApplicantBO().offerDetailsajax(applicantform, applicantId, uuid, applicant);
      if (applicant.getOfferedctc() != null) {
        applicantform.setOfferedctc(applicant.getOfferedctc());
      }
      if (applicant.getOfferedctccurrencycode() != null) {
        applicantform.setOfferedctccurrencycode(applicant.getOfferedctccurrencycode());
      }
      JobRequisition jb = BOFactory.getJobRequistionBO().getJobRequision(String.valueOf(applicant.getReqId()));
      
      applicantform.setSalaryPlan(jb.getSalaryplan());
    }
    logger.info("Inside applicantofferdetailsajax method finish");
    return mapping.findForward("applicantofferdetailsajax");
  }
  
  public ActionForward compareapplicants(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside compareapplicants method");
    String applicantids = request.getParameter("applicantids");
    ApplicantForm applicantform = (ApplicantForm)form;
    List applicantList = new ArrayList();
    List applicantListdetails = new ArrayList();
    if (!StringUtils.isNullOrEmpty(applicantids))
    {
      applicantids = applicantids.substring(0, applicantids.length() - 1);
      
      applicantList = StringUtils.tokenizeString(applicantids, ",");
    }
    logger.info("applicantids" + applicantids);
    for (int i = 0; i < applicantList.size(); i++)
    {
      String appliantId = (String)applicantList.get(i);
      JobApplicant applicant = BOFactory.getApplicantBO().getApplicantStatusAndRequitionDetails(new Long(appliantId).longValue());
      
      applicantListdetails.add(applicant);
    }
    applicantform.setApplicantList(applicantListdetails);
    return mapping.findForward("compareapplicants");
  }
  
  public ActionForward exportapplicantsscr(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside exportapplicantsscr method");
    ApplicantForm applicantform = (ApplicantForm)form;
    List interviewstatelist = Constant.getAllInterviewStates();
    applicantform.setInterviewstateList(interviewstatelist);
    return mapping.findForward("exportapplicantsscr");
  }
  
  public ActionForward exportapplicants(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside exportapplicants method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ApplicantForm applicantform = (ApplicantForm)form;
    String targetjoiningdate = request.getParameter("targetjoiningdate");
    
    String reqid1s = request.getParameter("reqid1");
    String cri = request.getParameter("cri");
    String experience = request.getParameter("experience");
    String education = request.getParameter("education");
    String certification = request.getParameter("certification");
    List reqList = new ArrayList();
    if (!StringUtils.isNullOrEmpty(reqid1s))
    {
      reqid1s = reqid1s.substring(0, reqid1s.length() - 1);
      
      reqList = StringUtils.tokenizeString(reqid1s, ",");
    }
    BulkUploadTask task = new BulkUploadTask();
    ExportTaskUtil exp = new ExportTaskUtil();
    exp.setUser(user1);
    exp.setTask(task);
    exp.setTasktype(Common.EXPORT_APPLICANTS);
    exp.setCertification(certification);
    exp.setInterviewstate(applicantform.getInterviewState());
    exp.setTargetjoiningdate(targetjoiningdate);
    exp.setReqList(reqList);
    exp.setEducation(education);
    exp.setExperience(experience);
    exp.setCri(cri);
    BulkTaskManager.export(exp);
    
    request.setAttribute("exportdone", "yes");
    return mapping.findForward("exportapplicantsscr");
  }
  
  public ActionForward exporttoexcelHiringMgr(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside exporttoexcelHiringMgr method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantForm applicantform = (ApplicantForm)form;
    List jobtitleList = BOFactory.getJobRequistionBO().getRequisitionsActiveList();
    
    List newjobtitleList = new ArrayList();
    for (int i = 0; i < jobtitleList.size(); i++)
    {
      JobRequisition jreq = (JobRequisition)jobtitleList.get(i);
      jreq.setJobTitle(jreq.getJobTitle() + "-" + jreq.getJobreqcode());
      newjobtitleList.add(jreq);
    }
    applicantform.setJobtitleList(newjobtitleList);
    List interviewstatelist = Constant.getInterviewStates();
    applicantform.setInterviewstateList(interviewstatelist);
    applicantform.setSearchCriDateList(Constant.getSearchCriteraDateFields());
    

    BulkUploadTask task = new BulkUploadTask();
    ExportTaskUtil exp = new ExportTaskUtil();
    exp.setUser(user1);
    exp.setTask(task);
    exp.setSearcherrole("HIRING_MGR");
    exp.setTasktype(Common.EXPORT_APPLICANTS_SEARCH);
    ApplicantSearchCriteria searchcriteria = new ApplicantSearchCriteria();
    searchcriteria.setApplicantNo(applicantform.getApplicantNo());
    searchcriteria.setAppliedcri(applicantform.getAppliedcri());
    searchcriteria.setEmail(applicantform.getEmail());
    searchcriteria.setFromdate(applicantform.getSearchfromdate());
    searchcriteria.setTodate(applicantform.getSearchtodate());
    searchcriteria.setFullname(applicantform.getFullName());
    searchcriteria.setInterviewstate(applicantform.getInterviewState());
    searchcriteria.setPrevorg(applicantform.getPreviousOrganization());
    searchcriteria.setReqid(String.valueOf(applicantform.getRequitionId()));
    searchcriteria.setTagId(String.valueOf(applicantform.getTagId()));
    exp.setApplicantSearchCriteria(searchcriteria);
    BulkTaskManager.export(exp);
    List tagList = BOFactory.getLovBO().getTagsList("APPLICANT", user1.getSuper_user_key());
    
    applicantform.setTagList(tagList);
    request.setAttribute("exporttoexcel", "yes");
    
    return mapping.findForward("searchownapplicantinitHiringMgr");
  }
  
  public ActionForward exporttoexcelOnBoardSearch(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside exporttoexcelOnBoardSearch method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantForm applicantform = (ApplicantForm)form;
    List userlist = UserDAO.getAllUsersByType("Vendor");
    applicantform.setVendorList(userlist);
    List jobtitleList = BOFactory.getJobRequistionBO().getJobTitlesActiveListForForm(user1.getSuper_user_key());
    
    applicantform.setJobtitleList(jobtitleList);
    List interviewstatelist = Constant.getInterviewStates();
    applicantform.setInterviewstateList(interviewstatelist);
    applicantform.setSearchCriDateList(Constant.getSearchCriteraDateFields());
    

    applicantform.setPaginationRowsList(Constant.paginationRowsList);
    applicantform.setOnboardingProcessStatusList(Constant.getOnBoardingProcessStatus(user1));
    
    List orgList = BOFactory.getLovBO().getAllOrganization(user1.getSuper_user_key());
    applicantform.setOrganizationList(orgList);
    List deptlist = new ArrayList();
    applicantform.setDepartmentList(deptlist);
    if (applicantform.getOrgId() > 0L) {
      applicantform.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(applicantform.getOrgId())));
    }
    BulkUploadTask task = new BulkUploadTask();
    ExportTaskUtil exp = new ExportTaskUtil();
    exp.setUser(user1);
    exp.setTask(task);
    exp.setSearcherrole("ONBOARD_APP_EXP");
    exp.setTasktype(Common.EXPORT_APPLICANTS_SEARCH);
    ApplicantSearchCriteria searchcriteria = new ApplicantSearchCriteria();
    searchcriteria.setApplicantNo(applicantform.getApplicantNo());
    searchcriteria.setAppliedcri(applicantform.getAppliedcri());
    searchcriteria.setEmail(applicantform.getEmail());
    searchcriteria.setFromdate(applicantform.getSearchfromdate());
    searchcriteria.setTodate(applicantform.getSearchtodate());
    searchcriteria.setFullname(applicantform.getFullName());
    searchcriteria.setInterviewstate(applicantform.getInterviewState());
    searchcriteria.setPrevorg(applicantform.getPreviousOrganization());
    searchcriteria.setReqid(String.valueOf(applicantform.getRequitionId()));
    searchcriteria.setVendorId(String.valueOf(applicantform.getVendorId()));
    searchcriteria.setOrgId(String.valueOf(applicantform.getOrgId()));
    searchcriteria.setDepartmentId(String.valueOf(applicantform.getDepartmentId()));
    
    searchcriteria.setProjectcodeId(String.valueOf(applicantform.getProjectcodeId()));
    
    searchcriteria.setOnboardingProcessStatus(applicantform.getOnboardingProcessStatus());
    
    searchcriteria.setTagId(String.valueOf(applicantform.getTagId()));
    exp.setApplicantSearchCriteria(searchcriteria);
    BulkTaskManager.export(exp);
    List tagList = BOFactory.getLovBO().getTagsList("APPLICANT", user1.getSuper_user_key());
    
    applicantform.setTagList(tagList);
    
    request.setAttribute("exporttoexcel", "yes");
    
    return mapping.findForward("onboardingapplicants");
  }
  
  public ActionForward exporttoexcelAllSearch(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside exporttoexcelAllSearch method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantForm applicantform = (ApplicantForm)form;
    

    String searchuuid = request.getParameter("searchuuid");
    
    ApplicantSearchCriteria criteria = new ApplicantSearchCriteria();
    criteria.setSearchuuid(searchuuid);
    criteria = BOFactory.getApplicantBO().getSearchCriteriaForexporttoexcelAllSearch(criteria, searchuuid);
    
    BOFactory.getApplicantBO().setLovsForApplicantSearch(applicantform, user1);
    
    BulkUploadTask task = new BulkUploadTask();
    ExportTaskUtil exp = new ExportTaskUtil();
    exp.setUser(user1);
    exp.setTask(task);
    exp.setSearcherrole("ALL_APP_EXP");
    exp.setTasktype(Common.EXPORT_APPLICANTS_SEARCH);
    
    exp.setApplicantSearchCriteria(criteria);
    BulkTaskManager.export(exp);
    














    applicantform.setSearchcriteria(criteria);
    request.setAttribute("exporttoexcel", "yes");
    
    return mapping.findForward("applicantsbyvendor");
  }
  
  public ActionForward exporttoexcelRecruiter(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside exporttoexcelRecruiter method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantForm applicantform = (ApplicantForm)form;
    List jobtitleList = BOFactory.getJobRequistionBO().getRequisitionsActiveList();
    
    List newjobtitleList = new ArrayList();
    for (int i = 0; i < jobtitleList.size(); i++)
    {
      JobRequisition jreq = (JobRequisition)jobtitleList.get(i);
      jreq.setJobTitle(jreq.getJobTitle() + "-" + jreq.getJobreqcode());
      newjobtitleList.add(jreq);
    }
    applicantform.setJobtitleList(newjobtitleList);
    List interviewstatelist = Constant.getInterviewStates();
    applicantform.setInterviewstateList(interviewstatelist);
    applicantform.setSearchCriDateList(Constant.getSearchCriteraDateFields());
    

    BulkUploadTask task = new BulkUploadTask();
    ExportTaskUtil exp = new ExportTaskUtil();
    exp.setUser(user1);
    exp.setTask(task);
    exp.setSearcherrole("RECRUITER");
    exp.setTasktype(Common.EXPORT_APPLICANTS_SEARCH);
    ApplicantSearchCriteria searchcriteria = new ApplicantSearchCriteria();
    searchcriteria.setApplicantNo(applicantform.getApplicantNo());
    searchcriteria.setAppliedcri(applicantform.getAppliedcri());
    searchcriteria.setEmail(applicantform.getEmail());
    searchcriteria.setFromdate(applicantform.getSearchfromdate());
    searchcriteria.setTodate(applicantform.getSearchtodate());
    searchcriteria.setFullname(applicantform.getFullName());
    searchcriteria.setInterviewstate(applicantform.getInterviewState());
    searchcriteria.setPrevorg(applicantform.getPreviousOrganization());
    searchcriteria.setReqid(String.valueOf(applicantform.getRequitionId()));
    searchcriteria.setTagId(String.valueOf(applicantform.getTagId()));
    exp.setApplicantSearchCriteria(searchcriteria);
    BulkTaskManager.export(exp);
    List tagList = BOFactory.getLovBO().getTagsList("APPLICANT", user1.getSuper_user_key());
    
    applicantform.setTagList(tagList);
    request.setAttribute("exporttoexcel", "yes");
    
    return mapping.findForward("searchownapplicantinitRecruiter");
  }
  
  public ActionForward saveeducationdetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveeducationdetails method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ApplicantForm applicantform = (ApplicantForm)form;
    String applicantid = request.getParameter("applicantid");
    String type = request.getParameter("type");
    String educationName = request.getParameter("educationName");
    String specialization = request.getParameter("specialization");
    String institute = request.getParameter("institute");
    String percentile = request.getParameter("percentile");
    String passingyear = request.getParameter("passingyear");
    String startingYear = request.getParameter("startingYear");
    
    EducationDetails edu = null;
    if ((!StringUtils.isNullOrEmpty(type)) && (type.equals(Common.EDUCATION_COLLEGE)))
    {
      List eduList = BOFactory.getApplicantBO().getEducationsByApplicantAndType(new Long(applicantid).longValue(), type);
      


      Iterator itr = eduList.iterator();
      logger.info("Applicant Skills list : " + eduList);
      while (itr.hasNext())
      {
        edu = (EducationDetails)itr.next();
        System.out.println("skill name : " + edu.getEducationName());
        if (educationName.equals(edu.getEducationName()))
        {
          request.setAttribute("educationalreadyadded", "yes");
          return mapping.findForward("createPage");
        }
      }
    }
    edu = new EducationDetails();
    edu.setApplicantId(new Long(applicantid).longValue());
    edu.setEducationName(educationName);
    edu.setSpecialization(specialization);
    edu.setInstituteName(institute);
    edu.setPercentile(percentile);
    edu.setPassingYear(passingyear);
    edu.setEduType(type);
    edu.setStartingYear(startingYear);
    if (user1 != null) {
      edu.setCreatedBy(user1.getUserName());
    }
    edu.setCreatedDate(new Date());
    BOFactory.getApplicantBO().saveEducationDetails(edu);
    
    return null;
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
  
  public ActionForward deleteSkill(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteSkill method");
    

    ApplicantForm applicantform = (ApplicantForm)form;
    String skillid = request.getParameter("skillid");
    BOFactory.getApplicantBO().deleteSkill(new Long(skillid).longValue());
    return null;
  }
  
  public ActionForward saveapplicantSkills(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveapplicantSkills method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ApplicantForm applicantform = (ApplicantForm)form;
    String applicantid = request.getParameter("applicantid");
    String skillname = request.getParameter("skillname");
    String yearsofexpskill = request.getParameter("yearsofexpskill");
    
    String ratingskill = request.getParameter("ratingskill");
    
    List skillsList = BOFactory.getApplicantBO().getSkillsByApplicant(new Long(applicantid).longValue());
    
    logger.info("Applicant Skills list : " + skillsList);
    Iterator itr = skillsList.iterator();
    ApplicantSkills edu = null;
    while (itr.hasNext())
    {
      edu = (ApplicantSkills)itr.next();
      System.out.println("skill name : " + edu.getSkillname());
      if (skillname.equals(edu.getSkillname()))
      {
        request.setAttribute("skillalreadyadded", "yes");
        return mapping.findForward("createPage");
      }
    }
    edu = new ApplicantSkills();
    edu.setApplicantId(new Long(applicantid).longValue());
    edu.setSkillname(skillname);
    edu.setYearsofexp(yearsofexpskill);
    edu.setRating(ratingskill);
    if (user1 != null) {
      edu.setCreatedBy(user1.getUserName());
    }
    edu.setCreatedDate(new Date());
    BOFactory.getApplicantBO().saveSkillsDetails(edu);
    
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
    String educationalreadyadded = request.getParameter("educationalreadyadded");
    
    List eduList = BOFactory.getApplicantBO().getEducationsByApplicantAndType(new Long(applicantid).longValue(), type);
    if ((!StringUtils.isNullOrEmpty(type)) && (type.equals(Common.EDUCATION_COLLEGE))) {
      applicantform.setEducationsList(eduList);
    }
    if ((!StringUtils.isNullOrEmpty(type)) && (type.equals(Common.EDUCATION_CERTIFICATION))) {
      applicantform.setCertificationsList(eduList);
    }
    applicantform.setEduType(type);
    applicantform.setUuid(uuid);
    request.setAttribute("educationalreadyadded", educationalreadyadded);
    return mapping.findForward("geteducationdetails");
  }
  
  public ActionForward deletePrevOg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deletePrevOg method");
    

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
  
  public ActionForward getApplicantSkills(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside getApplicantSkills method");
    ApplicantForm applicantform = (ApplicantForm)form;
    String applicantid = request.getParameter("applicantid");
    String skillalreadyadded = request.getParameter("skillalreadyadded");
    

    List skillsList = BOFactory.getApplicantBO().getSkillsByApplicant(new Long(applicantid).longValue());
    
    applicantform.setApplicantSkillList(skillsList);
    request.setAttribute("skillalreadyadded", skillalreadyadded);
    return mapping.findForward("getApplicantSkills");
  }
  
  public ActionForward deleteApplicantattachment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteApplicantattachment method");
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    String actioname = request.getParameter("actionname");
    String attachmentuuid = request.getParameter("uuid");
    ApplicantForm applicantform = (ApplicantForm)form;
    
    ApplicantUserDAO.deleteActionAttachment(attachmentuuid);
    
    request.setAttribute("attachmentdeleted", "yes");
    
    return mapping.findForward("deleteApplicantattachment");
  }
  
  public ActionForward savepreviousOrgdetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside savepreviousOrgdetails method");
    User user1 = (User)request.getSession().getAttribute("user_data");
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
      prevorg.setCreatedBy(user1.getUserName());
    }
    prevorg.setCreatedDate(new Date());
    
    BOFactory.getApplicantBO().savePreviousOrgDetails(prevorg);
    
    return null;
  }
  
  public ActionForward applicanttracking(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside applicanttracking method");
    return mapping.findForward("applicanttracking");
  }
  
  public ActionForward applicanttrackingnext(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside applicanttrackingnext method");
    return mapping.findForward("applicanttrackingnext");
  }
  
  public ActionForward applicanttrackingbystatus(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside applicanttrackingbystatus method");
    String status = request.getParameter("status");
    ApplicantForm applicantform = (ApplicantForm)form;
    applicantform.setInterviewState(status);
    return mapping.findForward("applicanttrackingbystatus");
  }
  
  public ActionForward searchmenuheader(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchmenuheader method");
    String search = request.getParameter("search");
    String searchtype = request.getParameter("searchtypeval");
    ApplicantForm applicantform = (ApplicantForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    logger.info("Inside searchmenuheader method search" + search);
    logger.info("Inside searchmenuheader method searchtype" + searchtype);
    try
    {
      if ((!StringUtils.isNullOrEmpty(searchtype)) && (searchtype.equalsIgnoreCase("appno")) && (!StringUtils.isNullOrEmpty("search")))
      {
        JobApplicant applicant = BOFactory.getApplicantBO().getApplicantByApplicantNumber(new Long(search).longValue(), user1.getSuper_user_key());
        

        User hiringmgr = BOFactory.getJobRequistionBO().getHiringManagerByReqId(applicant.getReqId());
        Recruiter recruiter = BOFactory.getJobRequistionBO().getRecruiterByReqId(applicant.getReqId());
        
        logger.info("applicantform.getRequitionId()" + applicantform.getRequitionId());
        
        logger.info("recruiter group" + recruiter.getIsgrouprecruiter());
        if (PermissionBO.isApplicantEditAllowed(user1, hiringmgr.getUserId(), recruiter))
        {
          String url = "/applicant.do?method=applicantDetails&applicantId=" + applicant.getApplicantId() + "&secureid=" + applicant.getUuid();
          


          ActionForward forward = new ActionForward(url);
          forward.setRedirect(true);
          return forward;
        }
        return mapping.findForward("searchapplicantheadererror");
      }
      if ((!StringUtils.isNullOrEmpty(searchtype)) && (searchtype.equalsIgnoreCase("appname")) && (!StringUtils.isNullOrEmpty("search")))
      {
        List applist = BOFactory.getApplicantBO().getApplicantByApplicantName(search, user1.getSuper_user_key());
        if ((applist != null) && (applist.size() == 1))
        {
          JobApplicant applicant = (JobApplicant)applist.get(0);
          
          User hiringmgr = BOFactory.getJobRequistionBO().getHiringManagerByReqId(applicant.getReqId());
          Recruiter recruiter = BOFactory.getJobRequistionBO().getRecruiterByReqId(applicant.getReqId());
          if (PermissionBO.isApplicantEditAllowed(user1, hiringmgr.getUserId(), recruiter))
          {
            String url = "/applicant.do?method=applicantDetails&applicantId=" + applicant.getApplicantId() + "&secureid=" + applicant.getUuid();
            


            ActionForward forward = new ActionForward(url);
            forward.setRedirect(true);
            return forward;
          }
        }
        else if (PermissionBO.isPermissionApplied("ALL_APPLICANTS", user1))
        {
          String url = "/applicant.do?method=applicantsbyvendor&applicant_name=" + search;
          
          ActionForward forward = new ActionForward(url);
          forward.setRedirect(true);
          return forward;
        }
      }
      else if ((!StringUtils.isNullOrEmpty(searchtype)) && (searchtype.equalsIgnoreCase("appsum")) && (!StringUtils.isNullOrEmpty("search")))
      {
        JobRequisition jb = BOFactory.getJobRequistionBO().getJobRequisionByNumber(new Long(search).longValue(), user1.getSuper_user_key());
        if (jb == null) {
          return mapping.findForward("searchapplicantheadererror");
        }
        if ((jb != null) && (jb.getState().equalsIgnoreCase("Active")))
        {
          boolean isReqReadPermission = PermissionBO.isRequistionReadAllowed(user1, jb.getHiringmgr().getUserId(), jb.getRecruiterId(), jb.getIsgrouprecruiter());
          if (!isReqReadPermission) {
            return mapping.findForward("searchapplicantheadererror");
          }
          String url = "/jobreq.do?method=requistionapplicantlist&state=0&requistionId=" + jb.getJobreqId() + "&secureid=" + jb.getUuid();
          
          ActionForward forward = new ActionForward(url);
          forward.setRedirect(true);
          return forward;
        }
        return mapping.findForward("searchapplicantheadererror");
      }
      return mapping.findForward("searchapplicantheadererror");
    }
    catch (Exception e) {}
    return mapping.findForward("searchapplicantheadererror");
  }
  
  public ActionForward acceptofferOnBehalfsrc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside acceptofferOnBehalf method");
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    ApplicantForm applicantform = (ApplicantForm)form;
    applicantform.setUuid(uuid);
    applicantform.setApplicantId(new Long(applicantId).longValue());
    request.setAttribute("acceptoffer", "no");
    return mapping.findForward("acceptofferOnBehalf");
  }
  
  public ActionForward declineofferOnBehalfsrc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside declineofferOnBehalfsrc method");
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    ApplicantForm applicantform = (ApplicantForm)form;
    applicantform.setUuid(uuid);
    applicantform.setApplicantId(new Long(applicantId).longValue());
    List declinedresonlist = BOFactory.getApplicantBO().getAllOfferDeclinedResons();
    
    applicantform.setOfferdeclinedreasonslist(declinedresonlist);
    request.setAttribute("declineoffer", "no");
    return mapping.findForward("declineofferOnBehalf");
  }
  
  public ActionForward acceptofferOnBehalf(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside acceptoffer method");
    String applicantId = request.getParameter("applicantId");
    String comment = request.getParameter("comment");
    String uuid = request.getParameter("secureid");
    User user1 = (User)request.getSession().getAttribute("user_data");
    
    logger.info("applicantId" + applicantId);
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    
    applicant.setInterviewState("Offer Accepted");
    applicant.setOfferacceptedcomment(comment);
    applicant.setOfferaccepteddate(new Date());
    

    ApplicationContext appContext = AppContextUtil.getAppcontext();
    ApplicantTXBO apptxbo = (ApplicantTXBO)appContext.getBean("apptxBoProxy");
    
    applicant = apptxbo.acceptofferOnBehalf(applicant, user1);
    








    request.setAttribute("acceptoffer", "yes");
    return mapping.findForward("acceptofferOnBehalf");
  }
  
  public ActionForward declineofferOnBehalf(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside declineofferOnBehalf method");
    String applicantId = request.getParameter("applicantId");
    String comment = request.getParameter("comment");
    String offerdeclinedresonid = request.getParameter("offerdeclinedresonid");
    
    String uuid = request.getParameter("secureid");
    User user1 = (User)request.getSession().getAttribute("user_data");
    
    logger.info("applicantId" + applicantId);
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    
    applicant.setInterviewState("Offer Declined");
    applicant.setOfferdeclinedcomment(comment);
    applicant.setOfferdeclineddate(new Date());
    applicant.setOfferdeclinedresonid(new Integer(offerdeclinedresonid).intValue());
    


    JobRequisition jobreq = BOFactory.getJobRequistionBO().getJobRequision(String.valueOf(applicant.getReqId()));
    
    jobreq.setNumberOfOpeningRemain(jobreq.getNumberOfOpeningRemain() + 1);
    if ((!StringUtils.isNullOrEmpty(Constant.getValue("auto.close.requisition.on.position.filled"))) && (Constant.getValue("auto.close.requisition.on.position.filled").equalsIgnoreCase("yes"))) {
      if ((jobreq.getStatus().equals("Closed")) && (jobreq.getNumberOfOpeningRemain() > 0)) {
        jobreq.setStatus("Open");
      }
    }
    ApplicationContext appContext = AppContextUtil.getAppcontext();
    ApplicantTXBO apptxbo = (ApplicantTXBO)appContext.getBean("apptxBoProxy");
    
    apptxbo.declineofferOnBehalf(applicant, jobreq, user1);
    








    request.setAttribute("declineoffer", "yes");
    return mapping.findForward("declineofferOnBehalf");
  }
  
  public ActionForward offermodifficationrequestOnbehalfscr(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside offermodifficationrequestOnbehalfscr method");
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    User user1 = (User)request.getSession().getAttribute("user_data");
    
    logger.info("applicantId" + applicantId);
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    
    ActionErrors errors = new ActionErrors();
    
    ApplicantForm applicantform = (ApplicantForm)form;
    if (applicant.getTargetjoiningdate() != null) {
      applicantform.setChagetargetjoiningdate(DateUtil.convertDateToStringDate(applicant.getTargetjoiningdate(), DateUtil.getDatePatternFormat(user1.getLocale())));
    }
    applicantform.setChangeofferedctc(applicant.getOfferedctc());
    applicantform.setChangeofferedctccurrencycode(applicant.getOfferedctccurrencycode());
    
    applicantform.setOfferedctccurrencycode(applicant.getOfferedctccurrencycode());
    
    applicantform.setApplicantId(applicant.getApplicantId());
    
    applicantform.setUuid(uuid);
    applicantform.setApplicantId(new Long(applicantId).longValue());
    request.setAttribute("modificationrequestsaved", "no");
    return mapping.findForward("offermodifficationrequestonbehalf");
  }
  
  public ActionForward offermodifficationrequestOnBehalf(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside offermodifficationrequestOnBehalf method");
    String applicantId = request.getParameter("applicantId");
    String chagetargetjoiningdate = request.getParameter("chagetargetjoiningdate");
    
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    
    ApplicantForm applicantform = (ApplicantForm)form;
    String uuid = request.getParameter("secureid");
    
    logger.info("chagetargetjoiningdate" + chagetargetjoiningdate);
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    if (chagetargetjoiningdate != null) {
      applicant.setChagetargetjoiningdate(DateUtil.convertStringDateToDate(chagetargetjoiningdate, DateUtil.getDatePatternFormat(user1.getLocale())));
    }
    applicant.setChangeofferComment(applicantform.getChangeofferComment());
    
    applicant.setChangeofferedctc(applicantform.getChangeofferedctc());
    applicant.setChangeofferedctccurrencycode(applicant.getOfferedctccurrencycode());
    

    applicant.setInterviewState("Offer In Negotiation");
    


    BOFactory.getApplicantTXBO().offermodifficationrequestOnBehalf(applicant, user1);
    

    applicantform.setUuid(uuid);
    applicantform.setApplicantId(new Long(applicantId).longValue());
    
    request.setAttribute("modificationrequestsaved", "yes");
    return mapping.findForward("offermodifficationrequestonbehalf");
  }
  
  public ActionForward loadActiveJobsListMultiple(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside loadActiveJobsListMultiple method");
    String orgId = request.getParameter("orgId");
    String departmentId = request.getParameter("departmentId");
    String projectCodeId = request.getParameter("projectCodeId");
    

    List<Long> orgIdList = new ArrayList();
    if ((!StringUtils.isNullOrEmpty(orgId)) && (!orgId.equals("null")))
    {
      StringTokenizer token = new StringTokenizer(orgId, ",");
      while (token.hasMoreTokens()) {
        orgIdList.add(new Long(token.nextToken()));
      }
    }
    List<Long> departmentIdsList = new ArrayList();
    if ((!StringUtils.isNullOrEmpty(departmentId)) && (!departmentId.equals("null")))
    {
      StringTokenizer token = new StringTokenizer(departmentId, ",");
      while (token.hasMoreTokens()) {
        departmentIdsList.add(new Long(token.nextToken()));
      }
    }
    List<Long> projectcodeIdList = new ArrayList();
    if ((!StringUtils.isNullOrEmpty(projectCodeId)) && (!projectCodeId.equals("null")))
    {
      StringTokenizer token = new StringTokenizer(projectCodeId, ",");
      while (token.hasMoreTokens()) {
        projectcodeIdList.add(new Long(token.nextToken()));
      }
    }
    ApplicantForm applicantform = (ApplicantForm)form;
    List jobtitleList = BOFactory.getJobRequistionBO().getRequisitionsActiveListByOrgDeptProjCode(orgIdList, departmentIdsList, projectcodeIdList);
    applicantform.setJobtitleList(jobtitleList);
    
    return mapping.findForward("loadActiveJobsListMultiple");
  }
  
  public ActionForward deptlistmultiple(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deptlistmultiple method");
    String orgId = request.getParameter("orgId");
    
    ApplicantForm applicantform = (ApplicantForm)form;
    
    List<Long> orgIdsList = new ArrayList();
    if ((!StringUtils.isNullOrEmpty(orgId)) && (!orgId.equals("null")))
    {
      StringTokenizer token = new StringTokenizer(orgId, ",");
      while (token.hasMoreTokens()) {
        orgIdsList.add(new Long(token.nextToken()));
      }
    }
    applicantform.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(orgIdsList));
    return mapping.findForward("deptlistmultiple");
  }
  
  public ActionForward loadProjectCodeMultiple(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside loadProjectCodeMultiple method");
    String departmentId = request.getParameter("departmentId");
    
    List<Long> deptidsList = new ArrayList();
    if ((!StringUtils.isNullOrEmpty(departmentId)) && (!departmentId.equals("null")))
    {
      StringTokenizer token = new StringTokenizer(departmentId, ",");
      while (token.hasMoreTokens()) {
        deptidsList.add(new Long(token.nextToken()));
      }
    }
    ApplicantForm applicantform = (ApplicantForm)form;
    applicantform.setProjectcodeList(BOFactory.getLovBO().getProjectCodesByDept(deptidsList));
    
    return mapping.findForward("loadProjectCodeMultiple");
  }
  
  public ActionForward loadProjectCode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String departmentId = request.getParameter("departmentId");
    System.out.println("departmentId" + departmentId);
    ApplicantForm applicantform = (ApplicantForm)form;
    applicantform.setProjectcodeList(BOFactory.getLovBO().getProjectCodesByDept(departmentId));
    

    return mapping.findForward("loadProjectCode");
  }
  
  public ActionForward getcomments(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside getcomments method");
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    
    ApplicantForm applicantform = (ApplicantForm)form;
    
    List commentList = ApplicantUserDAO.getApplicantCommentsList(new Long(applicantId).longValue(), uuid);
    

    applicantform.setCommentList(commentList);
    
    applicantform.setApplicantId(new Long(applicantId).longValue());
    applicantform.setUuid(uuid);
    
    applicantform.setCommentVisibleList(Constant.getCommentVisibleList());
    
    return mapping.findForward("add_text");
  }
  
  public ActionForward addcomment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside addcomment method");
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    String isappvisible = request.getParameter("isappvisible");
    String applicantcomments = request.getParameter("comment");
    logger.info("applicantcomments : " + applicantcomments);
    User user1 = (User)request.getSession().getAttribute("user_data");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    ApplicantForm applicantform = (ApplicantForm)form;
    
    ApplicantComment comment = new ApplicantComment();
    comment.setApplicantId(new Long(applicantId).longValue());
    comment.setAppuuid(uuid);
    
    comment.setComment(StringUtils.doSpecialCharacters(applicantcomments));
    comment.setCreatedBy(user1.getFirstName() + " " + user1.getLastName());
    comment.setByUserId(user1.getUserId());
    comment.setCreatedDate(new Date());
    comment.setBytype(user1.getRole().getRoleName());
    comment.setIsVisibleToApplicant(isappvisible);
    
    ApplicantUserDAO.saveApplicantComment(comment);
    if ((!StringUtils.isNullOrEmpty(isappvisible)) && (isappvisible.equals("Y"))) {
      addCommentEmail(applicant, user1, applicantform.getComment());
    }
    List commentList = ApplicantUserDAO.getApplicantCommentsList(new Long(applicantId).longValue(), uuid);
    

    applicantform.setCommentList(commentList);
    
    applicantform.setComment("");
    applicantform.setApplicantId(new Long(applicantId).longValue());
    applicantform.setUuid(uuid);
    
    return mapping.findForward("add_text");
  }
  
  private void addCommentEmail(JobApplicant applicant, User user1, String comment)
    throws Exception
  {
    String[] to = { applicant.getEmail() };
    
    String[] bcc = null;
    



    List cclist = BOFactory.getApplicantBO().getCCListForApplicant(user1, applicant, null, EmailNotificationSettingFunction.FunctionNames.ADD_COMMENT_ON_APPLICANT.toString(), true);
    
    String[] cc = new String[cclist.size()];
    cclist.toArray(cc);
    
    String replyTo = "\"" + user1.getFirstName() + " " + user1.getLastName() + "\"" + " " + "<" + user1.getEmailId() + ">";
    


    EmailTask emailtask = new EmailTask(replyTo, to, cc, bcc, replyTo, "dummysubject", null, "dummybody", null, 0, null);
    


    emailtask.setUser(user1);
    emailtask.setComment(comment);
    emailtask.setFunctionType(Common.COMMENT_TO_APPLICANT);
    emailtask.setApplicant(applicant);
    EmailTaskManager.sendEmail(emailtask);
  }
  
  public ActionForward deletecomment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deletecomment method");
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    String commentId = request.getParameter("commentId");
    logger.info("applicantId : " + applicantId);
    logger.info("uuid : " + uuid);
    logger.info("commentId : " + commentId);
    String isappvisible = request.getParameter("isappvisible");
    User user1 = (User)request.getSession().getAttribute("user_data");
    
    ApplicantForm applicantform = (ApplicantForm)form;
    
    ApplicantComment comment = ApplicantUserDAO.getApplicantComment(new Long(applicantId).longValue(), uuid, new Long(commentId).longValue());
    


    ApplicantUserDAO.deleteApplicantComment(comment);
    
    List commentList = ApplicantUserDAO.getApplicantCommentsList(new Long(applicantId).longValue(), uuid);
    

    applicantform.setCommentList(commentList);
    
    applicantform.setComment("");
    applicantform.setApplicantId(new Long(applicantId).longValue());
    applicantform.setUuid(uuid);
    
    String url = "/applicant.do?method=applicantDetails&applicantId=" + applicantform.getApplicantId() + "&secureid=" + applicantform.getUuid();
    

    ActionForward forward = new ActionForward(url);
    forward.setRedirect(true);
    return forward;
  }
  
  public ActionForward editcommenttree(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside editcomment method");
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    String commentId = request.getParameter("commentId");
    logger.info("applicantId : " + applicantId);
    logger.info("uuid : " + uuid);
    logger.info("commentId : " + commentId);
    String applicantcomments = request.getParameter("comment");
    logger.info("applicantcomments : " + applicantcomments);
    
    String rurl = request.getParameter("rurl");
    
    User user1 = (User)request.getSession().getAttribute("user_data");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    
    ApplicantForm applicantform = (ApplicantForm)form;
    
    ApplicantComment comment = ApplicantUserDAO.getApplicantComment(new Long(applicantId).longValue(), uuid, new Long(commentId).longValue());
    


    comment.setApplicantId(new Long(applicantId).longValue());
    comment.setAppuuid(uuid);
    
    comment.setComment(applicantcomments);
    comment.setUpdatedDate(new Date());
    comment.setIsVisibleToApplicant(applicantform.getCommentVisible());
    
    ApplicantUserDAO.updateApplicantComment(comment);
    if ((!StringUtils.isNullOrEmpty(applicantform.getCommentVisible())) && (applicantform.getCommentVisible().equals("Y")))
    {
      addCommentEmail(applicant, user1, applicantform.getComment());
    }
    else if ((!StringUtils.isNullOrEmpty(applicantform.getCommentVisible())) && (applicantform.getCommentVisible().equals("R")))
    {
      String toemailid = "";
      if ((applicant.getResumesourcetype() != null) && (applicant.getResumesourcetype().getResumeSourceTypeId() == 5))
      {
        BOFactory.getUserBO();User agency = UserBO.getUserFullNameEmailAndUserId(applicant.getVendorId());
        if (agency != null) {
          toemailid = "\"" + agency.getFirstName() + "\"" + " " + "<" + agency.getEmailId() + ">";
        } else {
          toemailid = "\"" + user1.getFirstName() + " " + user1.getLastName() + "\"" + " " + "<" + user1.getEmailId() + ">";
        }
        String[] to = { toemailid };
        BOFactory.getApplicantBO().commentEmail(user1.getEmailId(), to, applicant, applicantform.getComment());
      }
      else if ((applicant.getResumesourcetype() != null) && (applicant.getResumesourcetype().getResumeSourceTypeId() == 1))
      {
        BOFactory.getUserBO();User refuser = UserBO.getUserByEmailId(applicant.getReferrerEmail());
        if (refuser != null) {
          toemailid = "\"" + refuser.getFirstName() + " " + refuser.getLastName() + "\"" + " " + "<" + refuser.getEmailId() + ">";
        } else {
          toemailid = "\"" + user1.getFirstName() + " " + user1.getLastName() + "\"" + " " + "<" + user1.getEmailId() + ">";
        }
        String[] to = { toemailid };
        BOFactory.getApplicantBO().commentEmail(user1.getEmailId(), to, applicant, applicantform.getComment());
      }
    }
    List commentList = ApplicantUserDAO.getApplicantCommentsList(new Long(applicantId).longValue(), uuid);
    

    applicantform.setCommentList(commentList);
    applicantform.setComment(applicantcomments);
    applicantform.setApplicantId(new Long(applicantId).longValue());
    applicantform.setUuid(uuid);
    applicantform.setCommentVisibleList(Constant.getCommentVisibleList());
    







    return mapping.findForward("add_text");
  }
  
  public ActionForward editcomment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside editcomment method");
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    String commentId = request.getParameter("commentId");
    logger.info("applicantId : " + applicantId);
    logger.info("uuid : " + uuid);
    logger.info("commentId : " + commentId);
    String applicantcomments = request.getParameter("comment");
    logger.info("applicantcomments : " + applicantcomments);
    String isappvisible = request.getParameter("isappvisible");
    User user1 = (User)request.getSession().getAttribute("user_data");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    
    ApplicantForm applicantform = (ApplicantForm)form;
    
    ApplicantComment comment = ApplicantUserDAO.getApplicantComment(new Long(applicantId).longValue(), uuid, new Long(commentId).longValue());
    


    comment.setApplicantId(new Long(applicantId).longValue());
    comment.setAppuuid(uuid);
    
    String newComment = applicantcomments.replace("andSymbol", "&");
    String newComment2 = newComment.replace("hashSymbol", "#");
    comment.setComment(newComment2);
    comment.setUpdatedDate(new Date());
    comment.setIsVisibleToApplicant(isappvisible);
    
    ApplicantUserDAO.updateApplicantComment(comment);
    if ((!StringUtils.isNullOrEmpty(isappvisible)) && (isappvisible.equals("Y"))) {
      addCommentEmail(applicant, user1, applicantform.getComment());
    }
    List commentList = ApplicantUserDAO.getApplicantCommentsList(new Long(applicantId).longValue(), uuid);
    

    applicantform.setCommentList(commentList);
    applicantform.setComment(applicantcomments);
    applicantform.setApplicantId(new Long(applicantId).longValue());
    applicantform.setUuid(uuid);
    
    String url = "/applicant.do?method=applicantDetails&applicantId=" + applicantform.getApplicantId() + "&secureid=" + applicantform.getUuid();
    

    ActionForward forward = new ActionForward(url);
    forward.setRedirect(true);
    
    return forward;
  }
  
  public ActionForward addcommentajax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside addcommentajax method");
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    String isappvisible = request.getParameter("isappvisible");
    String applicantcomments = request.getParameter("comment");
    logger.info("applicantcomments : " + applicantcomments);
    User user1 = (User)request.getSession().getAttribute("user_data");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    ApplicantForm applicantform = (ApplicantForm)form;
    
    ApplicantComment comment = new ApplicantComment();
    comment.setApplicantId(new Long(applicantId).longValue());
    comment.setAppuuid(uuid);
    
    String newComment = applicantcomments.replace("andSymbol", "&");
    String newComment2 = newComment.replace("hashSymbol", "#");
    comment.setComment(newComment2);
    

    comment.setCreatedBy(user1.getFirstName() + " " + user1.getLastName());
    comment.setByUserId(user1.getUserId());
    comment.setCreatedDate(new Date());
    comment.setBytype(user1.getRole().getRoleName());
    comment.setIsVisibleToApplicant(isappvisible);
    
    ApplicantUserDAO.saveApplicantComment(comment);
    if ((!StringUtils.isNullOrEmpty(isappvisible)) && (isappvisible.equals("Y"))) {
      addCommentEmail(applicant, user1, applicantform.getComment());
    }
    List commentList = ApplicantUserDAO.getApplicantCommentsList(new Long(applicantId).longValue(), uuid);
    

    applicantform.setCommentList(commentList);
    
    applicantform.setComment("");
    applicantform.setApplicantId(new Long(applicantId).longValue());
    applicantform.setUuid(uuid);
    
    String url = "/applicant.do?method=applicantDetails&applicantId=" + applicantform.getApplicantId() + "&secureid=" + applicantform.getUuid();
    

    ActionForward forward = new ActionForward(url);
    forward.setRedirect(true);
    return forward;
  }
  
  public ActionForward addcommenttreeajax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside addcommenttreeajax method");
    String applicantId = request.getParameter("applicantId");
    String uuid = request.getParameter("secureid");
    String rurl = request.getParameter("rurl");
    

    User user1 = (User)request.getSession().getAttribute("user_data");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
    
    ApplicantForm applicantform = (ApplicantForm)form;
    
    ApplicantComment comment = new ApplicantComment();
    comment.setApplicantId(new Long(applicantId).longValue());
    comment.setAppuuid(uuid);
    comment.setComment(applicantform.getComment());
    comment.setCreatedBy(user1.getFirstName() + " " + user1.getLastName());
    comment.setByUserId(user1.getUserId());
    comment.setCreatedDate(new Date());
    comment.setBytype(user1.getRole().getRoleName());
    comment.setIsVisibleToApplicant(applicantform.getCommentVisible());
    
    ApplicantUserDAO.saveApplicantComment(comment);
    if ((!StringUtils.isNullOrEmpty(applicantform.getCommentVisible())) && (applicantform.getCommentVisible().equals("Y")))
    {
      addCommentEmail(applicant, user1, applicantform.getComment());
    }
    else if ((!StringUtils.isNullOrEmpty(applicantform.getCommentVisible())) && (applicantform.getCommentVisible().equals("R")))
    {
      String toemailid = "";
      if ((applicant.getResumesourcetype() != null) && (applicant.getResumesourcetype().getResumeSourceTypeId() == 5))
      {
        BOFactory.getUserBO();User agency = UserBO.getUserFullNameEmailAndUserId(applicant.getVendorId());
        if (agency != null) {
          toemailid = "\"" + agency.getFirstName() + "\"" + " " + "<" + agency.getEmailId() + ">";
        } else {
          toemailid = "\"" + user1.getFirstName() + " " + user1.getLastName() + "\"" + " " + "<" + user1.getEmailId() + ">";
        }
        String[] to = { toemailid };
        BOFactory.getApplicantBO().commentEmail(user1.getEmailId(), to, applicant, applicantform.getComment());
      }
      else if ((applicant.getResumesourcetype() != null) && (applicant.getResumesourcetype().getResumeSourceTypeId() == 1))
      {
        BOFactory.getUserBO();User refuser = UserBO.getUserByEmailId(applicant.getReferrerEmail());
        if (refuser != null) {
          toemailid = "\"" + refuser.getFirstName() + " " + refuser.getLastName() + "\"" + " " + "<" + refuser.getEmailId() + ">";
        } else {
          toemailid = "\"" + user1.getFirstName() + " " + user1.getLastName() + "\"" + " " + "<" + user1.getEmailId() + ">";
        }
        String[] to = { toemailid };
        BOFactory.getApplicantBO().commentEmail(user1.getEmailId(), to, applicant, applicantform.getComment());
      }
    }
    List commentList = ApplicantUserDAO.getApplicantCommentsList(new Long(applicantId).longValue(), uuid);
    

    applicantform.setCommentList(commentList);
    
    applicantform.setComment("");
    applicantform.setApplicantId(new Long(applicantId).longValue());
    applicantform.setUuid(uuid);
    applicantform.setCommentVisibleList(Constant.getCommentVisibleList());
    







    return mapping.findForward("add_text");
  }
  
  public ActionForward downloadApplicantDetailPdf(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside downloadApplicantDetailPdf method .. in ApplicantAction");
    
    String applicantid = request.getParameter("applicantid");
    String uuid = request.getParameter("secureid");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ApplicantForm applicantForm = (ApplicantForm)form;
    applicantForm.setUuid(uuid);
    User user1 = (User)request.getSession().getAttribute("user_data");
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
