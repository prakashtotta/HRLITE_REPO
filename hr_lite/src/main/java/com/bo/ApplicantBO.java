package com.bo;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
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

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;

import com.bean.ActionsAttachment;
import com.bean.ApplicantActivity;
import com.bean.ApplicantAttachments;
import com.bean.ApplicantOfferAttachment;
import com.bean.ApplicantOtherDetails;
import com.bean.ApplicantProfilePhoto;
import com.bean.ApplicantRating;
import com.bean.ApplicantScoring;
import com.bean.ApplicantSkills;
import com.bean.ApplicantUser;
import com.bean.BudgetCode;
import com.bean.Country;
import com.bean.DataTableBean;
import com.bean.DeclinedResons;
import com.bean.EducationDetails;
import com.bean.EmailNotificationSetting;
import com.bean.INineFormBean;
import com.bean.JobApplicant;
import com.bean.JobApplicationEvent;
import com.bean.JobRequisition;
import com.bean.Locale;
import com.bean.OfferApprover;
import com.bean.Organization;
import com.bean.PreviousOrgDetails;
import com.bean.QuestionGroupApplicants;
import com.bean.QuestionGroups;
import com.bean.RefferalEmployee;
import com.bean.RequistionExamQnsAssign;
import com.bean.ResumeSourceType;
import com.bean.ResumeSources;
import com.bean.SalaryPlan;
import com.bean.SearchApplicant;
import com.bean.SearchApplicantCustomFields;
import com.bean.SearchApplicantQuestions;
import com.bean.Timezone;
import com.bean.User;
import com.bean.UserGroup;
import com.bean.UserRegData;
import com.bean.criteria.ApplicantSearchCriteria;
import com.bean.criteria.ApplicantSearchUtil;
import com.bean.dto.Recruiter;
import com.bean.filter.ScreenFields;
import com.bean.pool.TalentPool;
import com.bean.pool.TalentPoolElements;
import com.bean.testengine.MockQuestionSet;
import com.bean.testengine.MockTest;
import com.common.Common;
import com.common.EmailNotificationSettingFunction;
import com.common.PluginException;
import com.dao.ApplicantDAO;
import com.dao.JobRequistionDAO;
import com.dao.TalentPoolTXDAO;
import com.dao.UserDAO;
import com.dao.VariablesTXDAO;
import com.form.ApplicantForm;
import com.form.VariableDataCaptureUtil;
import com.lowagie.text.Document;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.manager.EmailTaskManager;
import com.plugin.IntercepterImpl;
import com.plugin.PluginScripts;
import com.resources.Constant;
import com.util.DateUtil;
import com.util.EmailTask;
import com.util.ScreenSettingUtils;
import com.util.ScribdUtil;
import com.util.StringUtils;
import com.util.ValidationUtil;

public class ApplicantBO
{
  protected static final Logger logger = Logger.getLogger(ApplicantBO.class);
  ApplicantDAO applicantdao;
  JobRequistionDAO jobrequisitiondao;
  LovBO lovbo;
  JobRequistionBO jobrequistionbo;
  VariablesTXDAO variabletxdao;
  TalentPoolTXDAO talentpooltxdao;
  
  public static void main(String[] args) {}
  
  public byte[] getApplicantProfilePhoto(long photoId)
  {
    return this.applicantdao.getApplicantProfilePhoto(photoId);
  }
  
  public JobApplicant updateApplicantData(ApplicantForm applicantform, HttpServletRequest request, JobApplicant applicant, List errorList, String rname, boolean isReqChanged, long oldReqId, String oldJobTitle, User user1)
    throws Exception
  {
    try
    {
      boolean isSuccess = true;
      



      applicantform.toValue(applicant, request);
      String attachmentsizeexceed = (String)request.getAttribute("attachmentsizeexceed");
      
      applicant.setReqId(applicantform.getRequitionId());
      JobRequisition jbr = this.jobrequisitiondao.getJobRequision(String.valueOf(applicantform.getRequitionId()));
      
      applicant.setJobTitle(jbr.getJobTitle());
      




      logger.info("rname" + rname);
      logger.info("applicantform.getResumename()" + applicantform.getResumename());
      boolean isAttachmentupdata = true;
      if ((rname != null) && (StringUtils.isNullOrEmpty(applicantform.getResumename())))
      {
        logger.info("in if ... ");
        applicant.setResumename(rname);
        isAttachmentupdata = false;
      }
      applicant.setCurrectctc(applicantform.getCurrectctc());
      applicant.setExpectedctc(applicantform.getExpectedctc());
      String currencycode = jbr.getOrganization().getCurrencyCode();
      applicant.setCurrectctccurrencycode(currencycode);
      applicant.setExpectedctccurrencycode(currencycode);
      
      List formVariablesList = applicantform.getFormVariablesList();
      List formVariableDataList = applicantform.getFormVariableDataList();
      if ((!StringUtils.isNullOrEmpty(attachmentsizeexceed)) && (attachmentsizeexceed.equals("yes")))
      {
        request.setAttribute("attachmentsizeexceed", "yes");
        isSuccess = false;
      }
      if (isSuccess)
      {
        applicant = updateApplicant(applicant, user1, null, null, null, null, errorList, applicantform.getErrorListCustomVariable(), applicantform.getFormVariableDataList(), isAttachmentupdata);
        

        errorList = applicant.getErrorList();
        if ((errorList != null) && (errorList.size() > 0)) {
          isSuccess = false;
        }
      }
      if (isSuccess)
      {
        if (isReqChanged) {
          BOFactory.getApplicantTXBO().requistionChangedUpdate(applicant, user1, isReqChanged, oldReqId, oldJobTitle, jbr);
        }
        request.setAttribute("applicantupdated", "yes");
        request.setAttribute("applicantdatacustom", "yes");
      }
      else
      {
        logger.info("in error condition");
        request.setAttribute("error_list", errorList);
      }
      List jobtitleList = BOFactory.getJobRequistionBO().getJobTitlesActiveAndOpenListAssigedToUser(user1);
      
      applicantform.setJobtitleList(jobtitleList);
      



      commonLovPopulate(request, applicantform, applicant, user1.getSuper_user_key());
      commonSourceLovPopulate(applicantform, applicant);
      if (!isSuccess) {
        applicantform.setFormVariablesList(VariableDataCaptureUtil.mergeCustomVariableValues(formVariablesList, formVariableDataList, request));
      }
    }
    catch (Exception e)
    {
      throw e;
    }
    return applicant;
  }
  
  public void setLovsForApplicantSearch(ApplicantForm applicantform, User user)
  {
    List interviewstatelist = Constant.getInterviewStatesWithOutBlank();
    applicantform.setInterviewstateList(interviewstatelist);
    
    List<Long> orgidList = new ArrayList();
    if ((applicantform.getOrgIds() != null) && (applicantform.getOrgIds().length > 0)) {
      for (long s : applicantform.getOrgIds()) {
        orgidList.add(Long.valueOf(s));
      }
    }
    List<Long> depidtList = new ArrayList();
    if ((applicantform.getDepartmentIds() != null) && (applicantform.getDepartmentIds().length > 0)) {
      for (long s : applicantform.getDepartmentIds()) {
        depidtList.add(Long.valueOf(s));
      }
    }
    List<Long> projList = new ArrayList();
    if ((applicantform.getProjectcodeIds() != null) && (applicantform.getProjectcodeIds().length > 0)) {
      for (long s : applicantform.getProjectcodeIds()) {
        projList.add(Long.valueOf(s));
      }
    }
    List jobtitleList = BOFactory.getJobRequistionBO().getRequisitionsActiveListByOrgDeptProjCode(orgidList, depidtList, projList);
    applicantform.setJobtitleList(jobtitleList);
    


    List savedsearcheslist = BOFactory.getApplicantBO().getSavedApplicantSearchesByUserid(user.getUserId());
    applicantform.setSavedsearchesList(savedsearcheslist);
    


    List orgList = this.lovbo.getAllOrganization(user.getSuper_user_key());
    applicantform.setOrganizationList(orgList);
    if ((applicantform.getOrgIds() != null) && (applicantform.getOrgIds().length > 0))
    {
      List<Long> orgIdsList = new ArrayList();
      for (long s : applicantform.getOrgIds()) {
        orgIdsList.add(Long.valueOf(s));
      }
      applicantform.setDepartmentList(this.lovbo.getDepartmentListByOrg(orgIdsList));
    }
    else if (orgList.size() > 0)
    {
      Organization org = (Organization)orgList.get(0);
      List deptList = this.lovbo.getDepartmentListByOrg(String.valueOf(org.getOrgId()));
      applicantform.setDepartmentList(deptList);
    }
    if ((applicantform.getDepartmentIds() != null) && (applicantform.getDepartmentIds().length > 0))
    {
      List<Long> deptIdsList = new ArrayList();
      for (long s : applicantform.getDepartmentIds()) {
        deptIdsList.add(Long.valueOf(s));
      }
      applicantform.setProjectcodeList(this.lovbo.getProjectCodesByDept(deptIdsList));
    }
    else
    {
      applicantform.setProjectcodeList(new ArrayList());
    }
    if (orgList.size() < 1)
    {
      List deptList = new ArrayList();
      applicantform.setDepartmentList(deptList);
    }
    applicantform.setSearchCriDateList(Constant.getSearchCriteraDateFields());
    
    applicantform.setPaginationRowsList(Constant.paginationRowsList);
    List tagList = BOFactory.getLovBO().getTagsList("APPLICANT", user.getSuper_user_key());
    applicantform.setTagList(tagList);
    
    List countryList = this.lovbo.getAllCountries();
    applicantform.setCountryList(countryList);
    Country ct = (Country)countryList.get(0);
    
    List stateList = null;
    if (applicantform.getCountryId() != 0L) {
      stateList = this.lovbo.getStateListByCountry(applicantform.getCountryId());
    } else {
      stateList = this.lovbo.getStateListByCountry(ct.getCountryId());
    }
    applicantform.setStateList(stateList);
    
    List soucetypeList = this.lovbo.getAllResumeSourceTypes();
    applicantform.setSourceTypeList(soucetypeList);
    if (applicantform.getSourceTypeId() == 5)
    {
      List vendorList = UserDAO.getAllActiveVendors();
      applicantform.setVendorsList(vendorList);
    }
    else if (applicantform.getSourceTypeId() != 1)
    {
      if (applicantform.getSourceTypeId() > 0)
      {
        List sourceList = this.lovbo.getAllResumeSource(applicantform.getSourceTypeId());
        applicantform.setSourceList(sourceList);
      }
      else
      {
        List sourceList = new ArrayList();
        applicantform.setSourceList(sourceList);
      }
    }
    applicantform.setCriteriaStringList(Constant.getSearchCriterisString(user));
    applicantform.setCriteriaNumericList(Constant.getSearchCriterisNumeric(user));
    applicantform.setCriteriaDateList(Constant.getSearchCriterisDate(user));
    applicantform.setYesnofulllist(Constant.getYesNoFullList());
    
    List skilllist = BOFactory.getLovTXBO().getTechnicalSkillList(user.getSuper_user_key());
    applicantform.setLovList(skilllist);
    
    List quetionnaireList = BOFactory.getQuestionBO().getAllquestionsGroupDetails(user);
    applicantform.setQuetionnaireList(quetionnaireList);
    
    List mockquestionsetList = BOFactory.getMockTestBO().getAllMockQuestionSetsListForLov(user.getSuper_user_key());
    applicantform.setMockQuestionSetList(mockquestionsetList);
    
    List formVariablesList = BOFactory.getVariableBO().getAllFormVariablesListForApplicant(user.getSuper_user_key());
    applicantform.setFormVariablesList(formVariablesList);
  }
  
  public boolean isExamWorkflowActive(String interviewState)
  {
    boolean success = false;
    if ((StringUtils.isNullOrEmpty(Constant.getValue("applicant.exam.workflow"))) || (!Constant.getValue("applicant.exam.workflow").equalsIgnoreCase("yes"))) {
      if (interviewState.startsWith("Exam Screening")) {
        success = true;
      }
    }
    return success;
  }
  
  public void commonLovPopulateOnCreate(ApplicantForm applicantform, HttpServletRequest request, long superUserKey)
  {
    List countryList = this.lovbo.getAllCountries();
    applicantform.setCountryList(countryList);
    Country ct = (Country)countryList.get(0);
    List stateList = this.lovbo.getStateListByCountry(ct.getCountryId());
    applicantform.setStateList(stateList);
    

    applicantform.setEducationNamesList(BOFactory.getLovTXBO().getEducationListKeyValue(superUserKey));
    

    applicantform.setSkillList(BOFactory.getLovTXBO().getTechnicalSkillListKeyValue(superUserKey));
    applicantform.setSkillYearsList(Constant.skillsYearsList);
    applicantform.setSkillRatingList(Constant.skillsRatingsList);
    
    List soucetypeList = this.lovbo.getAllResumeSourceTypes();
    applicantform.setSourceTypeList(soucetypeList);
    if ((soucetypeList != null) && (soucetypeList.size() > 0))
    {
      ResumeSourceType resumesourcetype = (ResumeSourceType)soucetypeList.get(0);
      List sourceList = this.lovbo.getAllResumeSource(resumesourcetype.getResumeSourceTypeId());
      applicantform.setSourceList(sourceList);
    }
    List currencyList = this.lovbo.getAllCurrencies();
    applicantform.setCurrencyList(currencyList);
    
    List skilllist = BOFactory.getLovTXBO().getTechnicalSkillList(superUserKey);
    applicantform.setLovList(skilllist);
    
    List ethnicRaceList = this.lovbo.getAllEthnicRaceList();
    applicantform.setEthnicRaceList(ethnicRaceList);
    
    List veteranDisabilityList = this.lovbo.getAllVeteranDisabilityList();
    applicantform.setVeteranDisabilityList(veteranDisabilityList);
    
    String screenCode = BOFactory.getBusinessFilterBO().checkScreenCodeBasedOnPackage(superUserKey, applicantform.getScreenCode());
    applicantform.setScreenCode(screenCode);
    try
    {
      List formVariablesList = VariableDataCaptureUtil.fromValueCustomVariables(request, applicantform.getApplicantId(), screenCode, superUserKey);
      applicantform.setFormVariablesList(formVariablesList);
    }
    catch (Exception e)
    {
      logger.info("error on commonLovPopulateOnCreate custom variables", e);
    }
  }
  
  public void commonLovPopulate(HttpServletRequest request, ApplicantForm applicantform, JobApplicant applicant, long superUserKey)
  {
    applicantform.setApplicantId(applicant.getApplicantId());
    List countryList = this.lovbo.getAllCountries();
    applicantform.setCountryList(countryList);
    Country ct = (Country)countryList.get(0);
    
    List stateList = null;
    if (applicantform.getCountryId() != 0L) {
      stateList = this.lovbo.getStateListByCountry(applicantform.getCountryId());
    } else {
      stateList = this.lovbo.getStateListByCountry(ct.getCountryId());
    }
    applicantform.setStateList(stateList);
    
    List eduList = this.applicantdao.getEducationsByApplicantAndType(applicant.getApplicantId(), Common.EDUCATION_COLLEGE);
    applicantform.setEducationsList(eduList);
    List certList = this.applicantdao.getEducationsByApplicantAndType(applicant.getApplicantId(), Common.EDUCATION_CERTIFICATION);
    applicantform.setCertificationsList(certList);
    List orgList = this.applicantdao.getPreviousOrgDetailsByApplicant(applicant.getApplicantId());
    applicantform.setPreviousOrgList(orgList);
    applicantform.setEducationNamesList(BOFactory.getLovTXBO().getEducationListKeyValue(superUserKey));
    applicantform.setSkillList(BOFactory.getLovTXBO().getTechnicalSkillListKeyValue(superUserKey));
    applicantform.setSkillYearsList(Constant.skillsYearsList);
    applicantform.setSkillRatingList(Constant.skillsRatingsList);
    List appskillList = this.applicantdao.getSkillsByApplicant(applicant.getApplicantId());
    applicantform.setApplicantSkillList(appskillList);
    
    List currencyList = this.lovbo.getAllCurrencies();
    applicantform.setCurrencyList(currencyList);
    applicantform.setUuid(applicant.getUuid());
    
    List skilllist = BOFactory.getLovTXBO().getTechnicalSkillList(superUserKey);
    applicantform.setLovList(skilllist);
    List ethnicRaceList = this.lovbo.getAllEthnicRaceList();
    applicantform.setEthnicRaceList(ethnicRaceList);
    
    List veteranDisabilityList = this.lovbo.getAllVeteranDisabilityList();
    applicantform.setVeteranDisabilityList(veteranDisabilityList);
    try
    {
      List formVariablesList = VariableDataCaptureUtil.fromValueCustomVariables(request, applicantform.getApplicantId(), applicantform.getScreenCode(), superUserKey);
      applicantform.setFormVariablesList(formVariablesList);
    }
    catch (Exception e)
    {
      logger.info("error on commonLovPopulateOnCreate custom variables", e);
    }
  }
  
  public void fromValueCustomVariablesAllForApplicant(HttpServletRequest request, ApplicantForm applicantform)
  {
    try
    {
      List formVariablesList = VariableDataCaptureUtil.fromValueCustomVariablesAllForApplicant(request, applicantform.getApplicantId(), applicantform.getScreenCode());
      applicantform.setFormVariablesList(formVariablesList);
    }
    catch (Exception e)
    {
      logger.info("error on commonLovPopulateOnCreate custom variables", e);
    }
  }
  
  public void commonSourceLovPopulate(ApplicantForm applicantform, JobApplicant applicant)
  {
    List soucetypeList = this.lovbo.getAllResumeSourceTypes();
    applicantform.setSourceTypeList(soucetypeList);
    if ((applicant.getResumesourcetype() != null) && (applicant.getResumesourcetype().getResumeSourceTypeId() == 5))
    {
      List vendorList = UserDAO.getAllActiveVendors();
      applicantform.setVendorsList(vendorList);
    }
    else if ((applicant.getResumesourcetype() != null) && (applicant.getResumesourcetype().getResumeSourceTypeId() == 1))
    {
      applicantform.setReferrerEmail(applicant.getReferrerEmail());
      applicantform.setReferrerName(applicant.getReferrerName());
    }
    else if ((applicant.getResumesourcetype() != null) && (applicant.getResumesourcetype().getResumeSourceTypeId() > 0))
    {
      List sourceList = this.lovbo.getAllResumeSource(applicant.getResumesourcetype().getResumeSourceTypeId());
      applicantform.setSourceList(sourceList);
    }
    else
    {
      List sourceList = new ArrayList();
      applicantform.setSourceList(sourceList);
    }
  }
  
  public JobApplicant saveapplicantData(ApplicantForm applicantform, HttpServletRequest request, JobApplicant applicant, List errorList, User user1)
    throws Exception
  {
    logger.info("inside saveapplicantData");
    try
    {
      boolean isSuccess = true;
      
      applicantform.toValue(applicant, request);
      String attachmentsizeexceed = (String)request.getAttribute("attachmentsizeexceed");
      
      applicant.setReqId(applicantform.getRequitionId());
      String currencycode = this.jobrequisitiondao.getCurrencyCodeByReqId(applicantform.getRequitionId());
      applicant.setCurrectctc(applicantform.getCurrectctc());
      applicant.setExpectedctc(applicantform.getExpectedctc());
      

      applicantform.setCurrectctccurrencycode(currencycode);
      applicantform.setExpectedctccurrencycode(currencycode);
      applicant.setCurrectctccurrencycode(currencycode);
      applicant.setExpectedctccurrencycode(currencycode);
      



      applicant.setOwner(user1);
      applicant.setUuid(UUID.randomUUID().toString());
      
      List formVariablesList = applicantform.getFormVariablesList();
      List formVariableDataList = applicantform.getFormVariableDataList();
      JobApplicant oldapplicant = BOFactory.getApplicantBO().isApplicantDuplicate(applicant, user1.getSuper_user_key());
      if ((!StringUtils.isNullOrEmpty(attachmentsizeexceed)) && (attachmentsizeexceed.equals("yes")))
      {
        request.setAttribute("attachmentsizeexceed", "yes");
        isSuccess = false;
      }
      else if (oldapplicant != null)
      {
        request.setAttribute("oldapplicant", oldapplicant);
      }
      else
      {
        applicant = saveApplicant(applicant, user1, null, null, null, errorList, applicantform.getErrorListCustomVariable(), applicantform.getFormVariableDataList());
        
        errorList = applicant.getErrorList();
        if ((errorList != null) && (errorList.size() > 0)) {
          isSuccess = false;
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
      }
      List jobtitleList = BOFactory.getJobRequistionBO().getJobTitlesActiveAndOpenListAssigedToUser(user1);
      
      applicantform.setJobtitleList(jobtitleList);
      

      commonLovPopulate(request, applicantform, applicant, user1.getSuper_user_key());
      commonSourceLovPopulate(applicantform, applicant);
      if (!isSuccess) {
        applicantform.setFormVariablesList(VariableDataCaptureUtil.mergeCustomVariableValues(formVariablesList, formVariableDataList, request));
      }
    }
    catch (Exception e)
    {
      logger.info("exeption saveapplicantData ...", e);
      throw new Exception(e.getMessage());
    }
    return applicant;
  }
  
  public JobApplicationEvent getApplicationEventByApplicantIdSortByCreatedDate(long applicantId)
  {
    return this.applicantdao.getApplicationEventByApplicantIdSortByCreatedDate(applicantId);
  }
  
  private void preApplicantSubmit(JobApplicant applicant, User user)
    throws PluginException
  {
    if (PluginScripts.preAPPLICANTSUBMIT != null) {
      try
      {
        IntercepterImpl ue = new IntercepterImpl();
        ue.setObject("ApplicantObject", applicant);
        ue.setObject("UserObject", user);
        ue.firePreApplicantSubmit();
      }
      catch (PluginException ex)
      {
        logger.info("plugin exception" + ex.getMessage());
        logger.info("plugin exception", ex);
        throw new PluginException(ex.getMessage(), ex);
      }
    }
  }
  
  private void postApplicantSubmit(JobApplicant applicant, User user)
    throws PluginException
  {
    if (PluginScripts.postAPPLICANTSUBMIT != null) {
      try
      {
        IntercepterImpl ue = new IntercepterImpl();
        ue.setObject("ApplicantObject", applicant);
        ue.setObject("UserObject", user);
        ue.firePostApplicantSubmit();
      }
      catch (PluginException ex)
      {
        logger.info("plugin exception", ex);
        throw new PluginException(ex.getMessage());
      }
    }
  }
  
  public JobApplicant saveApplicant(JobApplicant applicant, User user, User agency, RefferalEmployee refEmployee, String refuserid, List errorList, List customVariableErrorList, List customVaribaleDataList)
    throws Exception
  {
    logger.info("inside saveApplicant");
    try
    {
      boolean isSuccess = true;
      String screenCode = "APPLICANT_SCREEN";
      User userContext = new User();
      if (user != null)
      {
        userContext.setLocale(user.getLocale());
        userContext.setTimezone(user.getTimezone());
        screenCode = "APPLICANT_SCREEN";
        if ((!StringUtils.isNullOrEmpty(applicant.getStatus())) && (applicant.getStatus().equals("T"))) {
          screenCode = "APPLICANT_SCREEN_TALENTPOOL";
        }
        applicant.setSuper_user_key(user.getSuper_user_key());
        userContext.setSuper_user_key(user.getSuper_user_key());
      }
      else if (agency != null)
      {
        userContext.setLocale(agency.getLocale());
        userContext.setTimezone(agency.getTimezone());
        screenCode = "APPLICANT_SCREEN_AGENCY";
        applicant.setSuper_user_key(agency.getSuper_user_key());
        userContext.setSuper_user_key(agency.getSuper_user_key());
      }
      else if (refEmployee != null)
      {
        userContext.setLocale(refEmployee.getLocale());
        userContext.setTimezone(refEmployee.getTimezone());
        screenCode = "APPLICANT_SCREEN_REFERRAL";
        applicant.setSuper_user_key(refEmployee.getSuper_user_key());
        userContext.setSuper_user_key(refEmployee.getSuper_user_key());
      }
      else if (refuserid != null)
      {
        if (applicant.getReqId() > 0L) {
          screenCode = "APPLICANT_SCREEN_EXTERNAL";
        } else {
          screenCode = "APPLICANT_SCREEN_TALENTPOOL_EXTERNAL";
        }
        if (refuserid.equals("external-jobpost"))
        {
          Locale locale = new Locale();
          locale.setLocaleCode(applicant.getLocale_code());
          userContext.setLocale(locale);
          Timezone tz = new Timezone();
          tz.setTimezoneCode(Constant.getValue("default.timezone.code"));
          userContext.setTimezone(tz);
        }
        else
        {
          RefferalEmployee refEmp = RefBO.getRefferalEmployee(refuserid);
          
          userContext.setLocale(refEmp.getLocale());
          userContext.setTimezone(refEmp.getTimezone());
          

          ResumeSourceType sourcetype = new ResumeSourceType();
          sourcetype.setResumeSourceTypeId(1);
          
          applicant.setReferrerEmail(refEmp.getEmployeeemail());
          applicant.setReferrerName(refEmp.getEmployeename());
          applicant.setEmployeecode(refEmp.getEmployeecode());
          
          applicant.setResumesourcetype(sourcetype);
          
          applicant.setSuper_user_key(refEmp.getSuper_user_key());
          userContext.setSuper_user_key(refEmp.getSuper_user_key());
        }
      }
      applicant.setScreenCode(screenCode);
      userContext.setSuper_user_key(applicant.getSuper_user_key());
      errorList = BOFactory.getBusinessFilterBO().isApplicantMandatoryValueSucceeed(applicant, applicant.getReqId(), userContext, screenCode);
      if ((!StringUtils.isNullOrEmpty(applicant.getReferrerEmail())) && (!ValidationUtil.isEmailValid(applicant.getReferrerEmail())))
      {
        String resoucekey = "validation.refferal.Invalid_Email_ID";
        String msg = Constant.getResourceStringValue(resoucekey, userContext.getLocale());
        errorList.add(msg);
      }
      if ((!StringUtils.isNullOrEmpty(applicant.getEmail())) && (!ValidationUtil.isEmailValid(applicant.getEmail())))
      {
        String resoucekey = "validation.Invalid_Email_ID";
        String msg = Constant.getResourceStringValue(resoucekey, userContext.getLocale());
        errorList.add(msg);
      }
      if ((!StringUtils.isNullOrEmpty(applicant.getAlternateemail())) && (!ValidationUtil.isEmailValid(applicant.getAlternateemail())))
      {
        String resoucekey = "validation.alternate.Invalid_Email_ID";
        String msg = Constant.getResourceStringValue(resoucekey, userContext.getLocale());
        errorList.add(msg);
      }
      if ((customVariableErrorList != null) && (customVariableErrorList.size() > 0))
      {
        logger.info("customVariableErrorList size" + customVariableErrorList.size());
        errorList.addAll(customVariableErrorList);
      }
      captchaValidation(applicant, userContext.getLocale(), errorList);
      if ((errorList.size() == 0) && 
        (!StringUtils.isNullOrEmpty(applicant.getStatus())) && (!applicant.getStatus().equals("T")))
      {
        logger.info("before filter requition id:" + applicant.getReqId());
        errorList = BOFactory.getBusinessFilterBO().isApplicantValidationSuccessed(applicant, applicant.getReqId(), customVaribaleDataList, userContext);
      }
      logger.info("applicant.getStatus()applicant.getStatus()applicant.getStatus()" + applicant.getStatus());
      logger.info("isSuccess" + isSuccess);
      if ((errorList != null) && (errorList.size() > 0)) {
        isSuccess = false;
      }
      if (isSuccess)
      {
        applicant.setCreatedDate(new Date());
        applicant.setUpdatedBy(null);
        applicant.setUpdatedDate(null);
        


        preApplicantSubmit(applicant, userContext);
        if (user != null)
        {
          applicant.setCreatedBy(user.getUserName());
          if ((!StringUtils.isNullOrEmpty(applicant.getStatus())) && (applicant.getStatus().equals("T"))) {
            applicant = BOFactory.getApplicantTXBO().saveApplicantToTalentPool(applicant, user, Common.APPLICANT_CREATED_BY_TYPE_INTERNAL, applicant.getTalentpoolid());
          } else {
            applicant = BOFactory.getApplicantTXBO().saveApplicant(applicant, user, null, null, null, Common.APPLICANT_CREATED_BY_TYPE_INTERNAL);
          }
        }
        else if (agency != null)
        {
          applicant.setCreatedBy(agency.getUserName());
          applicant = BOFactory.getApplicantTXBO().saveApplicant(applicant, null, agency, null, null, Common.APPLICANT_CREATED_BY_TYPE_AGENCY);
        }
        else if (refEmployee != null)
        {
          applicant.setCreatedBy(refEmployee.getEmployeename());
          applicant.setReferrerName(refEmployee.getEmployeename());
          applicant.setEmployeecode(refEmployee.getEmployeecode());
          applicant.setReferrerEmail(refEmployee.getEmployeeemail());
          applicant = BOFactory.getApplicantTXBO().saveApplicant(applicant, null, null, refEmployee, null, Common.APPLICANT_CREATED_BY_TYPE_REFERRAL);
        }
        else if (refuserid != null)
        {
          if (refuserid.equals("external-jobpost")) {
            applicant = BOFactory.getApplicantTXBO().saveApplicant(applicant, null, null, null, refuserid, Common.APPLICANT_CREATED_BY_TYPE_OUT_SIDE_JOB);
          } else {
            applicant = BOFactory.getApplicantTXBO().saveApplicant(applicant, null, null, null, refuserid, Common.APPLICANT_CREATED_BY_TYPE_REFERRAL);
          }
        }
        if ((customVaribaleDataList != null) && (customVaribaleDataList.size() > 0))
        {
          this.variabletxdao.deleteVariableValues(applicant.getApplicantId(), applicant.getScreenCode());
          this.variabletxdao.saveVariableValues(customVaribaleDataList, applicant.getApplicantId());
        }
        assignApplicantNumberAndJobTitle(applicant);
        

        sendApplicationSubmitionEmail(applicant, userContext, user);
        



        postApplicantSubmit(applicant, userContext);
      }
      else
      {
        logger.info("in error condition ");
        
        logger.info("errorList.size()" + errorList.size());
        applicant.setErrorList(errorList);
      }
    }
    catch (PluginException e)
    {
      logger.info("plugin exception1" + e.getMessage());
      throw new Exception(e.getMessage());
    }
    catch (Exception e)
    {
      logger.info("error on saveapplicant", e);
      throw e;
    }
    return applicant;
  }
  
  public void assignApplicantNumberAndJobTitle(JobApplicant applicant)
  {
    long maxValue = this.applicantdao.getMaxValueFromApplicant(applicant.getSuper_user_key());
    applicant.setApplicant_number(maxValue + 1L);
    if (applicant.getReqId() > 0L)
    {
      JobRequisition jb = BOFactory.getJobRequistionBO().getJobRequision(String.valueOf(applicant.getReqId()));
      applicant.setJobTitle(jb.getJobTitle());
      applicant.setReqName(jb.getJobreqName());
    }
    this.applicantdao.updateApplicant(applicant);
  }
  
  public void captchaValidation(JobApplicant applicant, Locale locale, List errorList)
  {
    if (applicant.isCaptchaValidationRequired())
    {
      try
      {
        if (!StringUtils.isNullOrEmpty(applicant.getCaptchaEnteredValue()))
        {
          logger.info("enetered captcha" + applicant.getCaptchaEnteredValue());
          
          long captaentered = new Long(applicant.getCaptchaEnteredValue()).longValue();
          String randomcaptcha = applicant.getCaptchaRandomValue();
          logger.info("randomcaptcha captcha" + randomcaptcha);
          if (new Long(randomcaptcha).longValue() != captaentered)
          {
            logger.info("captcha not equal");
            errorList.add(Constant.getResourceStringValue("admin.captcha.display.error", locale));
          }
        }
        else
        {
          errorList.add(Constant.getResourceStringValue("admin.captcha.display.error", locale));
        }
      }
      catch (NumberFormatException nu)
      {
        logger.info("exception on captchaValidation" + nu.getMessage());
        errorList.add(Constant.getResourceStringValue("admin.captcha.display.error", locale));
      }
      catch (Exception nu)
      {
        logger.info("exception on captchaValidation" + nu.getMessage());
        errorList.add(Constant.getResourceStringValue("admin.captcha.display.error", locale));
      }
      applicant.setCaptchaEnteredValue("0");
    }
  }
  
  public void sendApplicationSubmitionEmail(JobApplicant applicant, User userContext, User user)
    throws Exception
  {
    if ((!StringUtils.isNullOrEmpty(Constant.getValue("applicant.submit.email.send"))) && (Constant.getValue("applicant.submit.email.send").equals("yes")))
    {
      String[] to = new String[1];
      to[0] = applicant.getEmail();
      if (applicant.isIsemailrequiretosend())
      {
        String exam_questionnaire_content = assignExamAndQuestionnaire(applicant, userContext, user);
        
        UserRegData userreg = BOFactory.getUserBO().getUserRegDataById(applicant.getSuper_user_key());
        String fromemail = "";
        if (userreg != null) {
          fromemail = userreg.getEmailId();
        } else {
          fromemail = Constant.getValue("email.fromemail");
        }
        List cclist = BOFactory.getApplicantBO().getCCListForApplicant(user, applicant, null, EmailNotificationSettingFunction.FunctionNames.APPLICANT_SUBMIT.toString(), false);
        
        String[] cc = new String[cclist.size()];
        cclist.toArray(cc);
        

        EmailTask emailtask = new EmailTask(fromemail, to, cc, null, null, "dummysubject", null, "dummybody", null, 0, null);
        emailtask.setFunctionType(Common.APPLICANT_SUBMIT);
        emailtask.setUser(user);
        emailtask.setApplicant(applicant);
        emailtask.setExam_questionnaire_content(exam_questionnaire_content);
        EmailTaskManager.sendEmail(emailtask);
      }
    }
  }
  
  public String assignExamAndQuestionnaire(JobApplicant applicant, User userContext, User user)
  {
    String exam_questionnaire_content = "";
    if (applicant.getReqId() != 0L)
    {
      if (user == null)
      {
        JobRequisition jb = BOFactory.getJobRequistionBO().getJobRequision(String.valueOf(applicant.getReqId()));
        user = jb.getHiringmgr();
      }
      RequistionExamQnsAssign jbrqnse = BOFactory.getJobRequistionTXBO().getRequistionExamQnsAssign(applicant.getReqId());
      if (jbrqnse != null)
      {
        if (jbrqnse.getExamId() != 0)
        {
          MockTest mocktest = new MockTest();
          mocktest.setApplicantId(applicant.getApplicantId());
          mocktest.setApplicantName(applicant.getFullName());
          MockQuestionSet cat = new MockQuestionSet();
          cat.setCatId(jbrqnse.getExamId());
          mocktest.setCat(cat);
          mocktest.setUuid(applicant.getUuid());
          mocktest.setCreatedBy(jbrqnse.getUpdatedBy());
          mocktest.setCreatedDate(new Date());
          mocktest.setPassPercentage(jbrqnse.getPassPercentage());
          mocktest.setResult("ASSIGNED");
          
          BOFactory.getMockTestBO().saveMockTest(mocktest);
          

          MockQuestionSet qnsset = BOFactory.getMockTestBO().getMockQuestionSet(jbrqnse.getExamId());
          JobApplicationEvent event = new JobApplicationEvent();
          
          event.setCreatedBy(mocktest.getCreatedBy());
          event.setCreatedByName(user.getFirstName() + " " + user.getLastName());
          event.setCreatedDate(new Date());
          event.setApplicant(applicant);
          event.setOwner(applicant.getOwner());
          event.setIsGroup(applicant.getIsGroup());
          event.setOwnerGroup(applicant.getOwnerGroup());
          event.setEventType(Common.getEventType("Exam Screening"));
          event.setStatus(0);
          event.setNotes(jbrqnse.getMcomckexamsetcomment());
          event.setInterviewState("Exam Screening ^^ " + qnsset.getName());
          event.setTestId(mocktest.getTestId());
          

          this.applicantdao.saveApplicationEvent(event);
          

          String exam_url = Constant.getValue("external.url") + "jsp/testengine/pmptech20.jsp?catId=" + jbrqnse.getExamId() + "&applicantId=" + mocktest.getApplicantId() + "&uuid=" + mocktest.getUuid();
          
          exam_questionnaire_content = Constant.getResourceStringValue("exam.assigned.msg", userContext.getLocale()) + "<br>" + (jbrqnse.getMcomckexamsetcomment() == null ? "" : jbrqnse.getMcomckexamsetcomment());
          
          exam_questionnaire_content = exam_questionnaire_content + "<br>" + "<a href='" + exam_url + "'" + "target='new'>" + exam_url + "</a>";
        }
        if (jbrqnse.getQnsgrpId() != 0L)
        {
          QuestionGroupApplicants questionanire = new QuestionGroupApplicants();
          questionanire.setApplicantId(applicant.getApplicantId());
          questionanire.setApplicantName(applicant.getFullName());
          QuestionGroups qgroup = new QuestionGroups();
          qgroup.setQuestiongroupId(jbrqnse.getQnsgrpId());
          questionanire.setQuestiongroup(qgroup);
          questionanire.setUuid(applicant.getUuid());
          questionanire.setCreatedBy(jbrqnse.getUpdatedBy());
          questionanire.setCreatedDate(new Date());
          questionanire.setResult("ASSIGNED");
          questionanire.setHeadingcomments(jbrqnse.getQuestiongroupcomment());
          
          BOFactory.getMockTestBO().saveQuestionnaire(questionanire);
          

          QuestionGroups qnsgroup = BOFactory.getMockTestBO().getQuestionsGroup(jbrqnse.getQnsgrpId());
          JobApplicationEvent event1 = new JobApplicationEvent();
          event1.setCreatedBy(questionanire.getCreatedBy());
          
          event1.setCreatedByName(user.getFirstName() + " " + user.getLastName());
          event1.setCreatedDate(new Date());
          event1.setApplicant(applicant);
          event1.setOwner(applicant.getOwner());
          event1.setIsGroup(applicant.getIsGroup());
          event1.setOwnerGroup(applicant.getOwnerGroup());
          event1.setEventType(Common.getEventType("Questionnaire Assigned"));
          event1.setStatus(0);
          event1.setNotes(jbrqnse.getQuestiongroupcomment());
          event1.setInterviewState("Questionnaire Assigned ^^ " + qnsgroup.getQuestiongroupName());
          event1.setTestId(questionanire.getQnsGrpAppId());
          
          this.applicantdao.saveApplicationEvent(event1);
          
          String qnsninare_url = Constant.getValue("external.url") + "questionnaire.do?method=questionnaire&gpid=" + questionanire.getQnsGrpAppId() + "&applicantid=" + questionanire.getApplicantId() + "&uuid=" + questionanire.getUuid();
          String exam_url_href = "<a href='" + qnsninare_url + "'" + "target='new'>" + qnsninare_url + "</a>";
          
          exam_questionnaire_content = exam_questionnaire_content + "<br>" + Constant.getResourceStringValue("questionire.assigned.msg", userContext.getLocale()) + "<br>" + (jbrqnse.getQuestiongroupcomment() == null ? "" : jbrqnse.getQuestiongroupcomment());
          
          exam_questionnaire_content = exam_questionnaire_content + "<br>" + exam_url_href;
        }
      }
    }
    return exam_questionnaire_content;
  }
  
  public List validateApplicantDataBeforeMoveToRequistion(HttpServletRequest request, JobApplicant applicant, User user)
    throws Exception
  {
    logger.info("inside validateApplicantDataBeforeMoveToRequistion");
    List errorList = new ArrayList();
    List errorListCustomVariable = new ArrayList();
    try
    {
      User userContext = new User();
      userContext = user;
      errorList = BOFactory.getBusinessFilterBO().isApplicantMandatoryValueSucceeed(applicant, applicant.getReqId(), userContext, applicant.getScreenCode());
      

      Map m = VariableDataCaptureUtil.captureCustomVariablesMoveToRequistion(request, applicant.getApplicantId(), applicant.getScreenCode(), applicant.getSuper_user_key());
      
      errorListCustomVariable = (List)m.get("errorList");
      List customVariableDataList = (List)m.get("formVariableDataList");
      if ((errorListCustomVariable != null) && (errorListCustomVariable.size() > 0))
      {
        logger.info("customVariableErrorList size" + errorListCustomVariable.size());
        errorList.addAll(errorListCustomVariable);
      }
      if (errorList.size() == 0) {
        errorList = BOFactory.getBusinessFilterBO().isApplicantValidationSuccessed(applicant, applicant.getReqId(), customVariableDataList, userContext);
      }
    }
    catch (Exception e)
    {
      logger.info("error in validateApplicantDataBeforeMoveToRequistion", e);
      throw e;
    }
    return errorList;
  }
  
  public JobApplicant updateApplicant(JobApplicant applicant, User user, User agency, RefferalEmployee refEmployee, ApplicantUser appuser, String refuserid, List errorList, List customVariableErrorList, List customVaribaleDataList, boolean isAttachmentUpdated)
    throws Exception
  {
    logger.info("inside updateApplicant");
    try
    {
      boolean isSuccess = true;
      String screenCode = "APPLICANT_SCREEN";
      User userContext = new User();
      userContext.setSuper_user_key(applicant.getSuper_user_key());
      if (user != null)
      {
        userContext.setLocale(user.getLocale());
        userContext.setTimezone(user.getTimezone());
        screenCode = "APPLICANT_SCREEN";
        if ((!StringUtils.isNullOrEmpty(applicant.getStatus())) && (applicant.getStatus().equals("T"))) {
          screenCode = "APPLICANT_SCREEN_TALENTPOOL";
        }
      }
      else if (agency != null)
      {
        userContext.setLocale(agency.getLocale());
        userContext.setTimezone(agency.getTimezone());
        screenCode = "APPLICANT_SCREEN_AGENCY";
      }
      else if (refEmployee != null)
      {
        userContext.setLocale(refEmployee.getLocale());
        userContext.setTimezone(refEmployee.getTimezone());
        screenCode = "APPLICANT_SCREEN_REFERRAL";
      }
      else if (appuser != null)
      {
        userContext.setLocale(appuser.getLocale());
        userContext.setTimezone(appuser.getTimezone());
        screenCode = "APPLICANT_SCREEN_PROFILE";
      }
      else if (refuserid != null)
      {
        if (applicant.getReqId() > 0L) {
          screenCode = "APPLICANT_SCREEN_EXTERNAL";
        } else {
          screenCode = "APPLICANT_SCREEN_TALENTPOOL_EXTERNAL";
        }
        if (refuserid.equals("external-jobpost"))
        {
          Locale locale = new Locale();
          locale.setLocaleCode(applicant.getLocale_code());
          userContext.setLocale(locale);
          Timezone tz = new Timezone();
          tz.setTimezoneCode(Constant.getValue("default.timezone.code"));
          userContext.setTimezone(tz);
        }
        else
        {
          RefferalEmployee refEmp = RefBO.getRefferalEmployee(refuserid);
          
          userContext.setLocale(refEmp.getLocale());
          userContext.setTimezone(refEmp.getTimezone());
          

          ResumeSourceType sourcetype = new ResumeSourceType();
          sourcetype.setResumeSourceTypeId(1);
          
          applicant.setReferrerEmail(refEmp.getEmployeeemail());
          applicant.setReferrerName(refEmp.getEmployeename());
          applicant.setEmployeecode(refEmp.getEmployeecode());
          
          applicant.setResumesourcetype(sourcetype);
        }
      }
      if (!StringUtils.isNullOrEmpty(applicant.getScreenCode())) {
        screenCode = applicant.getScreenCode();
      }
      if (appuser != null) {
        errorList = BOFactory.getBusinessFilterBO().isApplicantProfileMandatoryValueSucceeed(applicant, applicant.getReqId(), userContext);
      } else {
        errorList = BOFactory.getBusinessFilterBO().isApplicantMandatoryValueSucceeed(applicant, applicant.getReqId(), userContext, screenCode);
      }
      if ((!StringUtils.isNullOrEmpty(applicant.getReferrerEmail())) && (!ValidationUtil.isEmailValid(applicant.getReferrerEmail())))
      {
        String resoucekey = "validation.refferal.Invalid_Email_ID";
        String msg = Constant.getResourceStringValue(resoucekey, userContext.getLocale());
        errorList.add(msg);
      }
      if ((!StringUtils.isNullOrEmpty(applicant.getEmail())) && (!ValidationUtil.isEmailValid(applicant.getEmail())))
      {
        String resoucekey = "validation.Invalid_Email_ID";
        String msg = Constant.getResourceStringValue(resoucekey, userContext.getLocale());
        errorList.add(msg);
      }
      if ((!StringUtils.isNullOrEmpty(applicant.getAlternateemail())) && (!ValidationUtil.isEmailValid(applicant.getAlternateemail())))
      {
        String resoucekey = "validation.alternate.Invalid_Email_ID";
        String msg = Constant.getResourceStringValue(resoucekey, userContext.getLocale());
        errorList.add(msg);
      }
      if ((customVariableErrorList != null) && (customVariableErrorList.size() > 0))
      {
        logger.info("customVariableErrorList size" + customVariableErrorList.size());
        errorList.addAll(customVariableErrorList);
      }
      captchaValidation(applicant, userContext.getLocale(), errorList);
      if (errorList.size() == 0) {
        if ((!StringUtils.isNullOrEmpty(applicant.getStatus())) && (applicant.getStatus().equals("T")) && (applicant.getReqId() == 0L)) {
          logger.info("applicant.getStatus() dasaaaaaaaaaaaaaaaaaaa" + applicant.getStatus() + applicant.getReqId());
        } else {
          errorList = BOFactory.getBusinessFilterBO().isApplicantValidationSuccessed(applicant, applicant.getReqId(), customVaribaleDataList, userContext);
        }
      }
      if ((errorList != null) && (errorList.size() > 0)) {
        isSuccess = false;
      }
      if (isSuccess)
      {
        ApplicantAttachments oldattcahment = null;
        try
        {
          List attachlist = this.applicantdao.getApplicantAttachments(applicant.getApplicantId(), applicant.getUuid(), Common.APPLICANT_ATTACHMENT_TYPE_RESUME);
          if ((attachlist != null) && (attachlist.size() > 0)) {
            oldattcahment = (ApplicantAttachments)attachlist.get(0);
          }
        }
        catch (Exception e)
        {
          logger.info("error on saveapplicant", e);
        }
        logger.info("inside updateApplicant status" + applicant.getStatus());
        if (applicant.getReqId() > 0L)
        {
          JobRequisition jb = BOFactory.getJobRequistionBO().getJobRequision(String.valueOf(applicant.getReqId()));
          applicant.setJobTitle(jb.getJobTitle());
          applicant.setReqName(jb.getJobreqName());
        }
        applicant.setUpdatedDate(new Date());
        applicant.setIsindexSearchApplied(0);
        if (user != null)
        {
          applicant.setUpdatedBy(user.getUserName());
          if ((!StringUtils.isNullOrEmpty(applicant.getStatus())) && (applicant.getStatus().equals("T")))
          {
            logger.info("update call to talenttool");
            applicant = BOFactory.getApplicantTXBO().updateApplicantTalentPool(applicant, user, isAttachmentUpdated);
          }
          else
          {
            logger.info("update call to applicant");
            applicant = BOFactory.getApplicantTXBO().updateApplicant(applicant, isAttachmentUpdated);
          }
        }
        else if (agency != null)
        {
          logger.info("update call to applicant by agency");
          applicant.setUpdatedBy(agency.getUserName());
          applicant = BOFactory.getApplicantTXBO().updateApplicant(applicant, isAttachmentUpdated);
        }
        else if (refEmployee != null)
        {
          logger.info("update call to applicant by refEmployee");
          applicant.setUpdatedBy(refEmployee.getEmployeename());
          applicant.setCreatedBy(refEmployee.getEmployeename());
          applicant.setEmployeecode(refEmployee.getEmployeecode());
          applicant.setReferrerEmail(refEmployee.getEmployeeemail());
          applicant = BOFactory.getApplicantTXBO().updateApplicant(applicant, isAttachmentUpdated);
        }
        else if (refuserid != null)
        {
          logger.info("update call to applicant by refuserid");
          applicant = BOFactory.getApplicantTXBO().updateApplicant(applicant, isAttachmentUpdated);
        }
        else if (appuser != null)
        {
          logger.info("update call to applicant by appuser");
          applicant.setUpdatedBy(appuser.getEmailId());
          applicant = BOFactory.getApplicantTXBO().updateApplicant(applicant, isAttachmentUpdated);
        }
        if ((customVaribaleDataList != null) && (customVaribaleDataList.size() > 0))
        {
          logger.info("applicant.getApplicantId()" + applicant.getApplicantId());
          this.variabletxdao.deleteVariableValues(applicant.getApplicantId(), "APPLICANT_FORM");
          this.variabletxdao.saveVariableValues(customVaribaleDataList, applicant.getApplicantId());
        }
        if ((isAttachmentUpdated) && (oldattcahment != null))
        {
          String filePath = Constant.getValue("ATTACHMENT_PATH");
          filePath = filePath + File.separator + "applicantAttachment" + File.separator + oldattcahment.getUuid() + File.separator;
          deleteAttachmentDirFromFileServer(filePath, oldattcahment.getUuid());
        }
      }
      else
      {
        logger.info("in error condition ");
        applicant.setErrorList(errorList);
      }
    }
    catch (Exception e)
    {
      logger.info("error on saveapplicant", e);
      throw e;
    }
    return applicant;
  }
  
  public void updateIsViewFlag(long applicantId)
  {
    this.applicantdao.updateIsViewFlag(applicantId);
  }
  
  public boolean isAllapplicantViewed(long reqId)
  {
    return this.applicantdao.isAllapplicantViewed(reqId);
  }
  
  public List isAllapplicantViewed(List reqList)
  {
    return this.applicantdao.isAllapplicantViewed(reqList);
  }
  
  public void deleteAttachmentDirFromFileServer(String filePath, String uuid)
  {
    try
    {
      File file = new File(filePath);
      if (file.isDirectory())
      {
        String[] files = file.list();
        if ((files != null) && (files.length > 0)) {
          for (int i = 0; i < files.length; i++)
          {
            String f1name = files[i];
            File f1 = new File(filePath + f1name);
            if (f1.exists()) {
              f1.delete();
            }
          }
        } else if (file.exists()) {
          file.delete();
        }
      }
      if (file.exists()) {
        file.delete();
      }
    }
    catch (Exception e)
    {
      logger.info("Exception on delete attachment file" + e.getMessage());
    }
  }
  
  public JobApplicant requistionApplicantDetailstree(ApplicantForm applicantform, HttpServletRequest request, String applicantId, String uuid)
  {
    logger.info("inside requistionApplicantDetailstree");
    JobApplicant applicant = getApplicantDetailswithUUID(applicantId, uuid);
    applicantform.setRequitionId(applicant.getReqId());
    if (applicant != null) {
      try
      {
        List attachlist = this.applicantdao.getApplicantAttachments(applicant.getApplicantId(), applicant.getUuid(), Common.APPLICANT_ATTACHMENT_TYPE_RESUME);
        if ((attachlist != null) && (attachlist.size() > 0)) {
          applicant.setApplicantAttachment((ApplicantAttachments)attachlist.get(0));
        }
        if ((applicant.getApplicantAttachment() != null) && (applicant.getApplicantAttachment().getAttachmentdata() != null) && (applicant.getResumename().length() > 0))
        {
          String filePath = Constant.getValue("ATTACHMENT_PATH");
          filePath = "reqattachment" + File.separator + applicant.getApplicantAttachment().getUuid() + File.separator + applicant.getApplicantAttachment().getAttahmentname();
          
          request.setAttribute("filePath", filePath);
        }
      }
      catch (Exception e)
      {
        logger.info("error on attachment" + e);
      }
    }
    if (applicant != null)
    {
      List eventList = this.applicantdao.getApplicationEventList(applicantId);
      logger.info("interview events size" + eventList.size());
      
      applicantform.setApplicantuser(this.applicantdao.getApplicantUserByApplicantId(applicant.getApplicantId()));
      if ((applicant.getGender() != null) && (applicant.getGender().equals("M"))) {
        applicantform.setGender("Male");
      } else if ((applicant.getGender() != null) && (applicant.getGender().equals("F"))) {
        applicantform.setGender("Female");
      }
      applicantform.setEventList(eventList);
    }
    User hiringmgr = this.jobrequisitiondao.getHiringManagerByReqId(applicantform.getRequitionId());
    Recruiter recruiter = this.jobrequisitiondao.getRecruiterByReqId(applicantform.getRequitionId());
    
    applicantform.setHiringmgr(hiringmgr);
    applicantform.setRecruiter(recruiter);
    
    List eduList = this.applicantdao.getEducationsByApplicantAndType(new Long(applicantId).longValue(), Common.EDUCATION_COLLEGE);
    applicantform.setEducationsList(eduList);
    List certList = this.applicantdao.getEducationsByApplicantAndType(new Long(applicantId).longValue(), Common.EDUCATION_CERTIFICATION);
    applicantform.setCertificationsList(certList);
    List orgList = this.applicantdao.getPreviousOrgDetailsByApplicant(new Long(applicantId).longValue());
    applicantform.setPreviousOrgList(orgList);
    List appskillList = this.applicantdao.getSkillsByApplicant(new Long(applicantId).longValue());
    applicantform.setApplicantSkillList(appskillList);
    
    List comptetencyList = this.jobrequisitiondao.getJobRequisionTemplateComptetencyList(applicant.getReqId(), "job");
    List accomplishmentList = this.jobrequisitiondao.getJobRequisionTemplateAccomplishmentList(applicant.getReqId(), "job");
    applicantform.setAccomplishmentList(accomplishmentList);
    applicantform.setComptetencyList(comptetencyList);
    

    TalentPoolElements te = this.talentpooltxdao.getTalentPoolElementByApplicantId(applicant.getApplicantId());
    if (te != null)
    {
      TalentPool tpool = this.talentpooltxdao.getTalentpoolDetails(te.getApplicantpoolidInitial());
      if (tpool != null)
      {
        applicant.setTalentpoolid(tpool.getTalentPoolId());
        applicant.setTalentPoolName(tpool.getTalentPoolName());
        applicantform.setTalentPoolId(tpool.getTalentPoolId());
        applicantform.setTalentPoolName(tpool.getTalentPoolName());
      }
    }
    request.getSession().setAttribute("app_id", applicantId);
    return applicant;
  }
  
  public JobApplicant applicantDetails(ApplicantForm applicantform, HttpServletRequest request, String applicantId, String uuid)
  {
    logger.info("inside applicantDetails");
    JobApplicant applicant = this.applicantdao.getApplicantDetailswithUUID(applicantId, uuid);
    
    ApplicantOtherDetails otherdetails = this.applicantdao.getApplicantOtherDetails(applicant.getApplicantId());
    
    applicant.setOtherdetails(otherdetails);
    
    applicantform.setRequitionId(applicant.getReqId());
    if (applicant != null) {
      try
      {
        List attachlist = this.applicantdao.getApplicantAttachments(applicant.getApplicantId(), applicant.getUuid(), Common.APPLICANT_ATTACHMENT_TYPE_RESUME);
        if ((attachlist != null) && (attachlist.size() > 0)) {
          applicant.setApplicantAttachment((ApplicantAttachments)attachlist.get(0));
        }
        if (applicant.getApplicantAttachment() != null)
        {
          String filePath = Constant.getValue("ATTACHMENT_PATH");
          filePath = "applicantAttachment" + File.separator + applicant.getApplicantAttachment().getUuid() + File.separator + applicant.getApplicantAttachment().getAttahmentname();
          
          request.setAttribute("filePath", filePath);
        }
      }
      catch (Exception e)
      {
        logger.info("error on attachment" + e);
      }
    }
    if (applicant != null)
    {
      applicantform.setApplicantuser(this.applicantdao.getApplicantUserByApplicantId(applicant.getApplicantId()));
      if ((applicant.getGender() != null) && (applicant.getGender().equals("M"))) {
        applicantform.setGender("Male");
      } else if ((applicant.getGender() != null) && (applicant.getGender().equals("F"))) {
        applicantform.setGender("Female");
      }
    }
    User hiringmgr = this.jobrequisitiondao.getHiringManagerByReqId(applicant.getReqId());
    Recruiter recruiter = this.jobrequisitiondao.getRecruiterByReqId(applicant.getReqId());
    
    applicantform.setHiringmgr(hiringmgr);
    applicantform.setRecruiter(recruiter);
    
    List eduList = this.applicantdao.getEducationsByApplicantAndType(new Long(applicantId).longValue(), Common.EDUCATION_COLLEGE);
    applicantform.setEducationsList(eduList);
    List certList = this.applicantdao.getEducationsByApplicantAndType(new Long(applicantId).longValue(), Common.EDUCATION_CERTIFICATION);
    applicantform.setCertificationsList(certList);
    List orgList = this.applicantdao.getPreviousOrgDetailsByApplicant(new Long(applicantId).longValue());
    applicantform.setPreviousOrgList(orgList);
    List appskillList = this.applicantdao.getSkillsByApplicant(new Long(applicantId).longValue());
    applicantform.setApplicantSkillList(appskillList);
    
    List comptetencyList = this.jobrequisitiondao.getJobRequisionTemplateComptetencyList(applicant.getReqId(), "job");
    List accomplishmentList = this.jobrequisitiondao.getJobRequisionTemplateAccomplishmentList(applicant.getReqId(), "job");
    applicantform.setAccomplishmentList(accomplishmentList);
    applicantform.setComptetencyList(comptetencyList);
    List approverlist = this.applicantdao.getOfferApproverList(applicant.getApplicantId());
    logger.info("approverlist" + approverlist);
    applicantform.setOfferApproverList(approverlist);
    
    List declinedresonlist = this.applicantdao.getAllOfferDeclinedResons();
    applicantform.setOfferdeclinedreasonslist(declinedresonlist);
    logger.info("inside applicantDetails end");
    

    TalentPoolElements te = this.talentpooltxdao.getTalentPoolElementByApplicantId(applicant.getApplicantId());
    if (te != null)
    {
      TalentPool tpool = this.talentpooltxdao.getTalentpoolDetails(te.getApplicantpoolidInitial());
      if (tpool != null)
      {
        applicant.setTalentpoolid(tpool.getTalentPoolId());
        applicant.setTalentPoolName(tpool.getTalentPoolName());
        applicantform.setTalentPoolId(tpool.getTalentPoolId());
        applicantform.setTalentPoolName(tpool.getTalentPoolName());
      }
    }
    return applicant;
  }
  
  public long getApplicantIdByUuid(String uuid)
    throws Exception
  {
    return this.applicantdao.getApplicantIdByUuid(uuid);
  }
  
  public String getInterviewStateById(String uuid)
  {
    return this.applicantdao.getInterviewStateById(uuid);
  }
  
  public JobApplicant applicantevaluationcriteraajax(ApplicantForm applicantform, String applicantId, String uuid)
  {
    JobApplicant applicant = this.applicantdao.getApplicantDetailswithUUID(applicantId, uuid);
    List comptetencyList = this.jobrequisitiondao.getJobRequisionTemplateComptetencyList(applicant.getReqId(), "job");
    List accomplishmentList = this.jobrequisitiondao.getJobRequisionTemplateAccomplishmentList(applicant.getReqId(), "job");
    applicantform.setApplicantId(applicant.getApplicantId());
    applicantform.setUuid(applicant.getUuid());
    applicantform.setAccomplishmentList(accomplishmentList);
    applicantform.setComptetencyList(comptetencyList);
    
    return applicant;
  }
  
  public void additionalDetailsajax(ApplicantForm applicantform, String applicantId, String uuid)
  {
    List eduList = this.applicantdao.getEducationsByApplicantAndType(new Long(applicantId).longValue(), Common.EDUCATION_COLLEGE);
    applicantform.setEducationsList(eduList);
    List certList = this.applicantdao.getEducationsByApplicantAndType(new Long(applicantId).longValue(), Common.EDUCATION_CERTIFICATION);
    applicantform.setCertificationsList(certList);
    List orgList = this.applicantdao.getPreviousOrgDetailsByApplicant(new Long(applicantId).longValue());
    applicantform.setPreviousOrgList(orgList);
    List appskillList = this.applicantdao.getSkillsByApplicant(new Long(applicantId).longValue());
    applicantform.setApplicantSkillList(appskillList);
  }
  
  public void offerDetailsajax(ApplicantForm applicantform, String applicantId, String uuid, JobApplicant applicant)
  {
    List approverlist = this.applicantdao.getOfferApproverList(new Long(applicantId).longValue());
    
    applicantform.setOfferApproverList(approverlist);
    
    List attachmentList = this.applicantdao.getOfferAttachmentList(new Long(applicantId).longValue(), "initoffer");
    applicantform.setOfferAttachmentList(attachmentList);
    
    List offerreleaseattachmentList = this.applicantdao.getOfferAttachmentList(new Long(applicantId).longValue(), "reloffer");
    applicantform.setOfferReleaseAttachmentList(offerreleaseattachmentList);
    
    List appliocantattachmentList = this.applicantdao.getOfferAttachmentList(new Long(applicantId).longValue(), "applicantdoc");
    applicantform.setApplicantAttachmentList(appliocantattachmentList);
    
    List offerwatchlist = this.applicantdao.getOfferwatchListByApplicantId(new Long(applicantId).longValue(), "release_offer");
    
    List initiateofferwatchlist = this.applicantdao.getOfferwatchListByApplicantId(new Long(applicantId).longValue(), "initiate_offer");
    
    applicantform.setOfferwatchList(offerwatchlist);
    
    applicantform.setInitiateOfferwatchList(initiateofferwatchlist);
    
    List declinedresonlist = this.applicantdao.getAllOfferDeclinedResons();
    applicantform.setOfferdeclinedreasonslist(declinedresonlist);
    
    DeclinedResons desclinereason = BOFactory.getLovBO().getOfferDecliendReson(applicant.getOfferdeclinedresonid());
    if (desclinereason != null) {
      applicantform.setOfferdeclinedreason(desclinereason.getOfferdecliedreasonName());
    }
    List otherBenefitsList = this.applicantdao.getOtherBenefitsList(new Long(applicantId).longValue());
    applicantform.setOtherBenefitsList(otherBenefitsList);
  }
  
  public JobApplicant applicantlogdetailsajax(ApplicantForm applicantform, String applicantId, String uuid)
  {
    JobApplicant applicant = this.applicantdao.getApplicantDetailswithUUID(applicantId, uuid);
    if (applicant != null)
    {
      List eventList = this.applicantdao.getApplicationEventList(applicantId);
      logger.info("interview events size" + eventList.size());
      
      applicantform.setEventList(eventList);
    }
    return applicant;
  }
  
  public void downloadpdf(ApplicantForm applicantForm, HttpServletRequest request)
    throws Exception
  {
    JobApplicant applicant = null;
    ApplicantOtherDetails otherdetails = null;
    logger.info("Applicant Id : " + applicantForm.getApplicantId());
    logger.info("UUID : " + applicantForm.getUuid());
    String id = String.valueOf(applicantForm.getApplicantId());
    try
    {
      applicant = this.applicantdao.getApplicantDetailswithUUID(id, applicantForm.getUuid());
      otherdetails = this.applicantdao.getApplicantOtherDetails(applicant.getApplicantId());
      
      String filePath = "";
      String fileName = "";
      User user1 = (User)request.getSession().getAttribute("user_data");
      RefferalEmployee user2 = (RefferalEmployee)request.getSession().getAttribute("employee_refferal_data");
      User user3 = (User)request.getSession().getAttribute("agency_data");
      ApplicantUser user4 = (ApplicantUser)request.getSession().getAttribute("applicant_user_data");
      Locale locale = new Locale();
      if (user1 != null) {
        locale = user1.getLocale();
      }
      if (user2 != null) {
        locale = user2.getLocale();
      }
      if (user3 != null) {
        locale = user3.getLocale();
      }
      if (user4 != null) {
        locale = user4.getLocale();
      }
      String datepattern = DateUtil.getDatePatternFormat(locale);
      

      logger.info("***** path ***** : " + request.getContextPath());
      Document document = new Document(PageSize.A4, 25.0F, 25.0F, 25.0F, 25.0F);
      

      fileName = applicant.getFullName() + "'s_Details.pdf";
      filePath = Constant.getValue("ATTACHMENT_PATH") + fileName;
      PdfWriter.getInstance(document, new FileOutputStream(filePath));
      document.open();
      Paragraph title = new Paragraph("Applicant Details", FontFactory.getFont("Helvetica", 14.0F, 1, new Color(0, 0, 255)));
      

      title.setAlignment(1);
      document.add(title);
      
      PdfPTable table1 = new PdfPTable(2);
      table1.setSpacingBefore(15.0F);
      PdfPCell cell1 = new PdfPCell(new Paragraph("Applicant Name :  " + applicant.getFullName(), FontFactory.getFont("Helvetica", 10.0F, 1)));
      cell1.setColspan(2);
      cell1.setBorder(0);
      table1.addCell(cell1);
      document.add(table1);
      

      PdfPTable table2 = new PdfPTable(1);
      table2.getDefaultCell().setBorder(0);
      table2.setSpacingBefore(15.0F);
      table2.addCell(new Paragraph("Status           :           " + (StringUtils.isNullOrEmpty(applicant.getInterviewState()) ? "" : applicant.getInterviewState()), FontFactory.getFont("Helvetica", 8.0F, 0)));
      
      table2.addCell(new Paragraph("Added by      :           " + (StringUtils.isNullOrEmpty(applicant.getCreatedBy()) ? "" : applicant.getCreatedBy()), FontFactory.getFont("Helvetica", 8.0F, 0)));
      
      table2.addCell(new Paragraph("Applied date :           " + DateUtil.convertDateToStringDate(applicant.getCreatedDate(), datepattern), FontFactory.getFont("Helvetica", 8.0F, 0)));
      
      document.add(table2);
      
      PdfPTable table3 = new PdfPTable(2);
      table3.setSpacingBefore(15.0F);
      PdfPCell cell3 = new PdfPCell(new Paragraph("Personal Information", FontFactory.getFont("Helvetica", 9.0F, 1)));
      cell3.setColspan(2);
      cell3.setBorder(0);
      table3.addCell(cell3);
      
      table3.addCell(new Paragraph("Gender", FontFactory.getFont("Helvetica", 8.0F, 0)));
      table3.addCell(new Paragraph(StringUtils.isNullOrEmpty(applicant.getGender()) ? "" : applicant.getGender(), FontFactory.getFont("Helvetica", 8.0F, 1)));
      

      table3.addCell(new Paragraph("Date of Birth ", FontFactory.getFont("Helvetica", 8.0F, 0)));
      table3.addCell(new Paragraph(DateUtil.convertDateToStringDate(applicant.getCreatedDate(), datepattern), FontFactory.getFont("Helvetica", 8.0F, 1)));
      

      table3.addCell(new Paragraph("Email Id ", FontFactory.getFont("Helvetica", 8.0F, 0)));
      table3.addCell(new Paragraph(StringUtils.isNullOrEmpty(applicant.getEmail()) ? "" : applicant.getEmail(), FontFactory.getFont("Helvetica", 8.0F, 1)));
      

      table3.addCell(new Paragraph("Alternate email ", FontFactory.getFont("Helvetica", 8.0F, 0)));
      table3.addCell(new Paragraph(StringUtils.isNullOrEmpty(applicant.getAlternateemail()) ? "" : applicant.getAlternateemail(), FontFactory.getFont("Helvetica", 8.0F, 1)));
      

      table3.addCell(new Paragraph("Passport no ", FontFactory.getFont("Helvetica", 8.0F, 0)));
      table3.addCell(new Paragraph(StringUtils.isNullOrEmpty(applicant.getPassportno()) ? "" : applicant.getPassportno(), FontFactory.getFont("Helvetica", 8.0F, 1)));
      

      table3.addCell(new Paragraph("SSN no ", FontFactory.getFont("Helvetica", 8.0F, 0)));
      table3.addCell(new Paragraph(StringUtils.isNullOrEmpty(applicant.getSsnno()) ? "" : applicant.getSsnno(), FontFactory.getFont("Helvetica", 8.0F, 1)));
      

      table3.addCell(new Paragraph("Tax id no ", FontFactory.getFont("Helvetica", 8.0F, 0)));
      table3.addCell(new Paragraph(StringUtils.isNullOrEmpty(applicant.getTaxidno()) ? "" : applicant.getTaxidno(), FontFactory.getFont("Helvetica", 8.0F, 1)));
      

      table3.addCell(new Paragraph("Address ", FontFactory.getFont("Helvetica", 8.0F, 0)));
      table3.addCell(new Paragraph(StringUtils.isNullOrEmpty(otherdetails.getAddress()) ? "" : otherdetails.getAddress(), FontFactory.getFont("Helvetica", 8.0F, 1)));
      

      table3.addCell(new Paragraph("Phone ", FontFactory.getFont("Helvetica", 8.0F, 0)));
      table3.addCell(new Paragraph(StringUtils.isNullOrEmpty(applicant.getPhone()) ? "" : applicant.getPhone(), FontFactory.getFont("Helvetica", 8.0F, 1)));
      

      table3.addCell(new Paragraph("Alternate phone ", FontFactory.getFont("Helvetica", 8.0F, 0)));
      table3.addCell(new Paragraph(StringUtils.isNullOrEmpty(applicant.getAlternatephone()) ? "" : applicant.getAlternatephone(), FontFactory.getFont("Helvetica", 8.0F, 1)));
      

      table3.addCell(new Paragraph("City ", FontFactory.getFont("Helvetica", 8.0F, 0)));
      table3.addCell(new Paragraph(StringUtils.isNullOrEmpty(applicant.getCity()) ? "" : applicant.getCity(), FontFactory.getFont("Helvetica", 8.0F, 1)));
      

      table3.addCell(new Paragraph("Zipcode ", FontFactory.getFont("Helvetica", 8.0F, 0)));
      table3.addCell(new Paragraph(StringUtils.isNullOrEmpty(otherdetails.getZipcode()) ? "" : otherdetails.getZipcode(), FontFactory.getFont("Helvetica", 8.0F, 1)));
      

      table3.addCell(new Paragraph("State ", FontFactory.getFont("Helvetica", 8.0F, 0)));
      table3.addCell(new Paragraph(applicant.getState() == null ? "" : applicant.getState().getStateName(), FontFactory.getFont("Helvetica", 8.0F, 1)));
      

      table3.addCell(new Paragraph("Country ", FontFactory.getFont("Helvetica", 8.0F, 0)));
      table3.addCell(new Paragraph(applicant.getCountry() == null ? "" : applicant.getCountry().getCountryName(), FontFactory.getFont("Helvetica", 8.0F, 1)));
      

      document.add(table3);
      

      PdfPTable table4 = new PdfPTable(2);
      table4.setSpacingBefore(15.0F);
      PdfPCell cell4 = new PdfPCell(new Paragraph("Work Preference", FontFactory.getFont("Helvetica", 9.0F, 1)));
      cell4.setColspan(2);
      cell4.setBorder(0);
      table4.addCell(cell4);
      
      table4.addCell(new Paragraph("Current CTC", FontFactory.getFont("Helvetica", 8.0F, 0)));
      table4.addCell(new Paragraph(StringUtils.isNullOrEmpty(applicant.getCurrectctc()) ? "" : applicant.getCurrectctc(), FontFactory.getFont("Helvetica", 8.0F, 1)));
      

      table4.addCell(new Paragraph("Expected CTC", FontFactory.getFont("Helvetica", 8.0F, 0)));
      table4.addCell(new Paragraph(StringUtils.isNullOrEmpty(applicant.getExpectedctc()) ? "" : applicant.getExpectedctc(), FontFactory.getFont("Helvetica", 8.0F, 1)));
      

      table4.addCell(new Paragraph("Profile header", FontFactory.getFont("Helvetica", 8.0F, 0)));
      table4.addCell(new Paragraph(StringUtils.isNullOrEmpty(applicant.getResumeHeader()) ? "" : applicant.getResumeHeader(), FontFactory.getFont("Helvetica", 8.0F, 1)));
      

      table4.addCell(new Paragraph("Notice period", FontFactory.getFont("Helvetica", 8.0F, 0)));
      table4.addCell(new Paragraph(StringUtils.isNullOrEmpty(applicant.getNoticeperiod()) ? "" : applicant.getNoticeperiod(), FontFactory.getFont("Helvetica", 8.0F, 1)));
      

      table4.addCell(new Paragraph("Years of experience", FontFactory.getFont("Helvetica", 8.0F, 0)));
      table4.addCell(new Paragraph(String.valueOf(applicant.getNoofyearsexp()), FontFactory.getFont("Helvetica", 8.0F, 1)));
      

      table4.addCell(new Paragraph("Current designation", FontFactory.getFont("Helvetica", 8.0F, 0)));
      table4.addCell(new Paragraph(StringUtils.isNullOrEmpty(applicant.getCurrentdesignation()) ? "" : applicant.getCurrentdesignation(), FontFactory.getFont("Helvetica", 8.0F, 1)));
      

      table4.addCell(new Paragraph("Current organization", FontFactory.getFont("Helvetica", 8.0F, 0)));
      table4.addCell(new Paragraph(StringUtils.isNullOrEmpty(applicant.getPreviousOrganization()) ? "" : applicant.getPreviousOrganization(), FontFactory.getFont("Helvetica", 8.0F, 1)));
      

      table4.addCell(new Paragraph("Preferred Location", FontFactory.getFont("Helvetica", 8.0F, 0)));
      table4.addCell(new Paragraph(StringUtils.isNullOrEmpty(applicant.getPreferedlocation()) ? "" : applicant.getPreferedlocation(), FontFactory.getFont("Helvetica", 8.0F, 1)));
      

      table4.addCell(new Paragraph("Earliest start date", FontFactory.getFont("Helvetica", 8.0F, 0)));
      table4.addCell(new Paragraph(DateUtil.convertDateToStringDate(otherdetails.getEarliest_start_date(), datepattern), FontFactory.getFont("Helvetica", 8.0F, 1)));
      

      table4.addCell(new Paragraph("Work on weekends?", FontFactory.getFont("Helvetica", 8.0F, 0)));
      table4.addCell(new Paragraph(StringUtils.isNullOrEmpty(otherdetails.getWork_on_weekends()) ? "" : otherdetails.getWork_on_weekends(), FontFactory.getFont("Helvetica", 8.0F, 1)));
      

      table4.addCell(new Paragraph("Work in evenings?", FontFactory.getFont("Helvetica", 8.0F, 0)));
      table4.addCell(new Paragraph(StringUtils.isNullOrEmpty(otherdetails.getWork_on_evenings()) ? "" : otherdetails.getWork_on_evenings(), FontFactory.getFont("Helvetica", 8.0F, 1)));
      

      table4.addCell(new Paragraph("Work overtime?", FontFactory.getFont("Helvetica", 8.0F, 0)));
      table4.addCell(new Paragraph(StringUtils.isNullOrEmpty(otherdetails.getWork_on_overtime()) ? "" : otherdetails.getWork_on_overtime(), FontFactory.getFont("Helvetica", 8.0F, 1)));
      

      table4.addCell(new Paragraph("Ready to relocate?", FontFactory.getFont("Helvetica", 8.0F, 0)));
      table4.addCell(new Paragraph(StringUtils.isNullOrEmpty(otherdetails.getWant_to_relocate()) ? "" : otherdetails.getWant_to_relocate(), FontFactory.getFont("Helvetica", 8.0F, 1)));
      

      table4.addCell(new Paragraph("Language spoken", FontFactory.getFont("Helvetica", 8.0F, 0)));
      table4.addCell(new Paragraph(StringUtils.isNullOrEmpty(otherdetails.getLanguages_spoken()) ? "" : otherdetails.getLanguages_spoken(), FontFactory.getFont("Helvetica", 8.0F, 1)));
      


      document.add(table4);
      

      PdfPTable table5 = new PdfPTable(2);
      table5.setSpacingBefore(15.0F);
      PdfPCell cell5 = new PdfPCell(new Paragraph("Education", FontFactory.getFont("Helvetica", 9.0F, 1)));
      cell5.setColspan(2);
      cell5.setBorder(0);
      table5.addCell(cell5);
      
      table5.addCell(new Paragraph("Primary skill", FontFactory.getFont("Helvetica", 8.0F, 0)));
      table5.addCell(new Paragraph(StringUtils.isNullOrEmpty(applicant.getPrimarySkill()) ? "" : applicant.getPrimarySkill(), FontFactory.getFont("Helvetica", 8.0F, 1)));
      

      table5.addCell(new Paragraph("Other qualifications", FontFactory.getFont("Helvetica", 8.0F, 0)));
      table5.addCell(new Paragraph(StringUtils.isNullOrEmpty(applicant.getQualifications()) ? "" : applicant.getQualifications(), FontFactory.getFont("Helvetica", 8.0F, 1)));
      

      table5.addCell(new Paragraph("Highest qualification", FontFactory.getFont("Helvetica", 8.0F, 0)));
      table5.addCell(new Paragraph(StringUtils.isNullOrEmpty(applicant.getHeighestQualification()) ? "" : applicant.getHeighestQualification(), FontFactory.getFont("Helvetica", 8.0F, 1)));
      

      table5.addCell(new Paragraph("College", FontFactory.getFont("Helvetica", 8.0F, 0)));
      table5.addCell(new Paragraph(StringUtils.isNullOrEmpty(otherdetails.getCollege_name()) ? "" : otherdetails.getCollege_name(), FontFactory.getFont("Helvetica", 8.0F, 1)));
      

      table5.addCell(new Paragraph("GPA", FontFactory.getFont("Helvetica", 8.0F, 0)));
      table5.addCell(new Paragraph(StringUtils.isNullOrEmpty(otherdetails.getCollege_GPA()) ? "" : otherdetails.getCollege_GPA(), FontFactory.getFont("Helvetica", 8.0F, 1)));
      

      document.add(table5);
      

      PdfPTable table6 = new PdfPTable(2);
      table6.setSpacingBefore(15.0F);
      PdfPCell cell6 = new PdfPCell(new Paragraph("Social", FontFactory.getFont("Helvetica", 9.0F, 1)));
      cell6.setColspan(2);
      cell6.setBorder(0);
      table6.addCell(cell6);
      
      table6.addCell(new Paragraph("Facebook", FontFactory.getFont("Helvetica", 8.0F, 0)));
      table6.addCell(new Paragraph(StringUtils.isNullOrEmpty(otherdetails.getFacebook_url()) ? "" : otherdetails.getFacebook_url(), FontFactory.getFont("Helvetica", 8.0F, 1)));
      

      table6.addCell(new Paragraph("Website", FontFactory.getFont("Helvetica", 8.0F, 0)));
      table6.addCell(new Paragraph(StringUtils.isNullOrEmpty(otherdetails.getWebsite_url()) ? "" : otherdetails.getWebsite_url(), FontFactory.getFont("Helvetica", 8.0F, 1)));
      

      table6.addCell(new Paragraph("Linkedin", FontFactory.getFont("Helvetica", 8.0F, 0)));
      table6.addCell(new Paragraph(StringUtils.isNullOrEmpty(otherdetails.getLinkedin_url()) ? "" : otherdetails.getLinkedin_url(), FontFactory.getFont("Helvetica", 8.0F, 1)));
      

      document.add(table6);
      

      PdfPTable table7 = new PdfPTable(2);
      table7.setSpacingBefore(15.0F);
      PdfPCell cell7 = new PdfPCell(new Paragraph("Background", FontFactory.getFont("Helvetica", 9.0F, 1)));
      cell7.setColspan(2);
      cell7.setBorder(0);
      table7.addCell(cell7);
      
      table7.addCell(new Paragraph("Felony Conviction?", FontFactory.getFont("Helvetica", 8.0F, 0)));
      table7.addCell(new Paragraph(StringUtils.isNullOrEmpty(otherdetails.getFelony_conviction()) ? "" : otherdetails.getFelony_conviction(), FontFactory.getFont("Helvetica", 8.0F, 1)));
      

      table7.addCell(new Paragraph("Felony conviction description", FontFactory.getFont("Helvetica", 8.0F, 0)));
      table7.addCell(new Paragraph(StringUtils.isNullOrEmpty(otherdetails.getFelony_conviction_desc()) ? "" : otherdetails.getFelony_conviction_desc(), FontFactory.getFont("Helvetica", 8.0F, 1)));
      

      table7.addCell(new Paragraph("Work status", FontFactory.getFont("Helvetica", 8.0F, 0)));
      table7.addCell(new Paragraph(StringUtils.isNullOrEmpty(otherdetails.getWork_status()) ? "" : otherdetails.getWork_status(), FontFactory.getFont("Helvetica", 8.0F, 1)));
      

      document.add(table7);
      
      PdfPTable table8 = new PdfPTable(2);
      table8.setSpacingBefore(15.0F);
      PdfPCell cell8 = new PdfPCell(new Paragraph("Profile", FontFactory.getFont("Helvetica", 9.0F, 1)));
      cell8.setColspan(2);
      cell8.setBorder(0);
      table8.addCell(cell8);
      
      table8.addCell(new Paragraph("Resume", FontFactory.getFont("Helvetica", 8.0F, 0)));
      table8.addCell(new Paragraph(StringUtils.isNullOrEmpty(applicant.getResumename()) ? "" : applicant.getResumename(), FontFactory.getFont("Helvetica", 8.0F, 1)));
      

      document.add(table8);
      
      document.newPage();
      

      PdfPTable table9 = new PdfPTable(6);
      
      PdfPCell cell9 = new PdfPCell(new Paragraph("Education details", FontFactory.getFont("Helvetica", 9.0F, 1)));
      cell9.setColspan(6);
      cell9.setBorder(0);
      table9.addCell(cell9);
      
      table9.addCell(new Paragraph("Education", FontFactory.getFont("Helvetica", 8.0F, 0)));
      table9.addCell(new Paragraph("Specialization", FontFactory.getFont("Helvetica", 8.0F, 0)));
      table9.addCell(new Paragraph("College/University", FontFactory.getFont("Helvetica", 8.0F, 0)));
      table9.addCell(new Paragraph("Percentage/Grade", FontFactory.getFont("Helvetica", 8.0F, 0)));
      table9.addCell(new Paragraph("Start month-year", FontFactory.getFont("Helvetica", 8.0F, 0)));
      table9.addCell(new Paragraph("Passing month-year", FontFactory.getFont("Helvetica", 8.0F, 0)));
      



      List eduList = this.applicantdao.getEducationsByApplicantAndType(applicant.getApplicantId(), Common.EDUCATION_COLLEGE);
      if ((eduList != null) && (eduList.size() > 0))
      {
        for (int i = 0; i < eduList.size(); i++)
        {
          EducationDetails edu = (EducationDetails)eduList.get(i);
          table9.addCell(new Paragraph(StringUtils.isNullOrEmpty(edu.getEducationName()) ? "" : edu.getEducationName(), FontFactory.getFont("Helvetica", 8.0F, 0)));
          table9.addCell(new Paragraph(StringUtils.isNullOrEmpty(edu.getSpecialization()) ? "" : edu.getSpecialization(), FontFactory.getFont("Helvetica", 8.0F, 0)));
          table9.addCell(new Paragraph(StringUtils.isNullOrEmpty(edu.getInstituteName()) ? "" : edu.getInstituteName(), FontFactory.getFont("Helvetica", 8.0F, 0)));
          table9.addCell(new Paragraph(StringUtils.isNullOrEmpty(edu.getPercentile()) ? "" : edu.getPercentile(), FontFactory.getFont("Helvetica", 8.0F, 0)));
          table9.addCell(new Paragraph(StringUtils.isNullOrEmpty(edu.getStartingYear()) ? "" : edu.getStartingYear(), FontFactory.getFont("Helvetica", 8.0F, 0)));
          table9.addCell(new Paragraph(StringUtils.isNullOrEmpty(edu.getPassingYear()) ? "" : edu.getPassingYear(), FontFactory.getFont("Helvetica", 8.0F, 0)));
        }
      }
      else
      {
        PdfPCell cell91 = new PdfPCell(new Paragraph("Education details not added.", FontFactory.getFont("Helvetica", 8.0F, 0)));
        cell91.setColspan(6);
        table9.addCell(cell91);
      }
      document.add(table9);
      

      PdfPTable table10 = new PdfPTable(4);
      table10.setSpacingBefore(15.0F);
      PdfPCell cell10 = new PdfPCell(new Paragraph("Certification details", FontFactory.getFont("Helvetica", 9.0F, 1)));
      cell10.setColspan(4);
      cell10.setBorder(0);
      table10.addCell(cell10);
      
      table10.addCell(new Paragraph("Name", FontFactory.getFont("Helvetica", 8.0F, 0)));
      table10.addCell(new Paragraph("Certified organization", FontFactory.getFont("Helvetica", 8.0F, 0)));
      table10.addCell(new Paragraph("Percentage/Grade", FontFactory.getFont("Helvetica", 8.0F, 0)));
      table10.addCell(new Paragraph("\tPassing month-year", FontFactory.getFont("Helvetica", 8.0F, 0)));
      
      List certList = this.applicantdao.getEducationsByApplicantAndType(applicant.getApplicantId(), Common.EDUCATION_CERTIFICATION);
      if ((certList != null) && (certList.size() > 0))
      {
        for (int i = 0; i < certList.size(); i++)
        {
          EducationDetails edu = (EducationDetails)certList.get(i);
          table10.addCell(new Paragraph(StringUtils.isNullOrEmpty(edu.getEducationName()) ? "" : edu.getEducationName(), FontFactory.getFont("Helvetica", 8.0F, 0)));
          table10.addCell(new Paragraph(StringUtils.isNullOrEmpty(edu.getInstituteName()) ? "" : edu.getInstituteName(), FontFactory.getFont("Helvetica", 8.0F, 0)));
          table10.addCell(new Paragraph(StringUtils.isNullOrEmpty(edu.getPercentile()) ? "" : edu.getPercentile(), FontFactory.getFont("Helvetica", 8.0F, 0)));
          table10.addCell(new Paragraph(StringUtils.isNullOrEmpty(edu.getPassingYear()) ? "" : edu.getPassingYear(), FontFactory.getFont("Helvetica", 8.0F, 0)));
        }
      }
      else
      {
        PdfPCell cell101 = new PdfPCell(new Paragraph("Certification details not added.", FontFactory.getFont("Helvetica", 8.0F, 0)));
        cell101.setColspan(6);
        table10.addCell(cell101);
      }
      document.add(table10);
      

      PdfPTable table11 = new PdfPTable(6);
      table11.setSpacingBefore(15.0F);
      PdfPCell cell11 = new PdfPCell(new Paragraph("Experience details", FontFactory.getFont("Helvetica", 9.0F, 1)));
      cell11.setColspan(6);
      cell11.setBorder(0);
      table11.addCell(cell11);
      
      table11.addCell(new Paragraph("Organization", FontFactory.getFont("Helvetica", 8.0F, 0)));
      table11.addCell(new Paragraph("Last role", FontFactory.getFont("Helvetica", 8.0F, 0)));
      table11.addCell(new Paragraph("Reporting Manager", FontFactory.getFont("Helvetica", 8.0F, 0)));
      table11.addCell(new Paragraph("Joining date", FontFactory.getFont("Helvetica", 8.0F, 0)));
      table11.addCell(new Paragraph("Relieving date", FontFactory.getFont("Helvetica", 8.0F, 0)));
      table11.addCell(new Paragraph("Reason for Leaving", FontFactory.getFont("Helvetica", 8.0F, 0)));
      
      List orgList = this.applicantdao.getPreviousOrgDetailsByApplicant(applicant.getApplicantId());
      if ((orgList != null) && (orgList.size() > 0))
      {
        for (int i = 0; i < orgList.size(); i++)
        {
          PreviousOrgDetails prevexp = (PreviousOrgDetails)orgList.get(i);
          
          table11.addCell(new Paragraph(StringUtils.isNullOrEmpty(prevexp.getPrevOrgName()) ? "" : prevexp.getPrevOrgName(), FontFactory.getFont("Helvetica", 8.0F, 0)));
          table11.addCell(new Paragraph(StringUtils.isNullOrEmpty(prevexp.getRole()) ? "" : prevexp.getRole(), FontFactory.getFont("Helvetica", 8.0F, 0)));
          table11.addCell(new Paragraph(StringUtils.isNullOrEmpty(prevexp.getReportingToName()) ? "" : prevexp.getReportingToName(), FontFactory.getFont("Helvetica", 8.0F, 0)));
          table11.addCell(new Paragraph(StringUtils.isNullOrEmpty(prevexp.getStartdate()) ? "" : prevexp.getStartdate(), FontFactory.getFont("Helvetica", 8.0F, 0)));
          table11.addCell(new Paragraph(StringUtils.isNullOrEmpty(prevexp.getEnddate()) ? "" : prevexp.getEnddate(), FontFactory.getFont("Helvetica", 8.0F, 0)));
          table11.addCell(new Paragraph(StringUtils.isNullOrEmpty(prevexp.getReasonforleave()) ? "" : prevexp.getReasonforleave(), FontFactory.getFont("Helvetica", 8.0F, 0)));
        }
      }
      else
      {
        PdfPCell cell111 = new PdfPCell(new Paragraph("Experience details not added.", FontFactory.getFont("Helvetica", 8.0F, 0)));
        cell111.setColspan(6);
        table11.addCell(cell111);
      }
      document.add(table11);
      
      PdfPTable table12 = new PdfPTable(3);
      table12.setSpacingBefore(15.0F);
      PdfPCell cell12 = new PdfPCell(new Paragraph("Skills", FontFactory.getFont("Helvetica", 9.0F, 1)));
      cell12.setColspan(3);
      cell12.setBorder(0);
      table12.addCell(cell12);
      
      table12.addCell(new Paragraph("Skill", FontFactory.getFont("Helvetica", 8.0F, 0)));
      table12.addCell(new Paragraph("Years of experience", FontFactory.getFont("Helvetica", 8.0F, 0)));
      table12.addCell(new Paragraph("Rating", FontFactory.getFont("Helvetica", 8.0F, 0)));
      
      List appskillList = this.applicantdao.getSkillsByApplicant(applicant.getApplicantId());
      if ((appskillList != null) && (appskillList.size() > 0))
      {
        for (int i = 0; i < appskillList.size(); i++)
        {
          ApplicantSkills skills = (ApplicantSkills)appskillList.get(i);
          
          table12.addCell(new Paragraph(StringUtils.isNullOrEmpty(skills.getSkillname()) ? "" : skills.getSkillname(), FontFactory.getFont("Helvetica", 8.0F, 0)));
          table12.addCell(new Paragraph(StringUtils.isNullOrEmpty(skills.getYearsofexp()) ? "" : skills.getYearsofexp(), FontFactory.getFont("Helvetica", 8.0F, 0)));
          table12.addCell(new Paragraph(StringUtils.isNullOrEmpty(skills.getRating()) ? "" : skills.getRating(), FontFactory.getFont("Helvetica", 8.0F, 0)));
        }
      }
      else
      {
        PdfPCell cell121 = new PdfPCell(new Paragraph("Skills not added.", FontFactory.getFont("Helvetica", 8.0F, 0)));
        cell121.setColspan(6);
        table12.addCell(cell121);
      }
      document.add(table12);
      

      document.close();
      
      logger.info("filepath : " + filePath);
      request.setAttribute("filePath", fileName);
    }
    catch (Exception e)
    {
      logger.info("error on download pdf" + e.getMessage());
      throw e;
    }
  }
  
  public ApplicantAttachments getApplicantAttachment(long applicantId, String uuid, String type)
  {
    ApplicantAttachments attach = null;
    List attachlist = BOFactory.getApplicantBO().getApplicantAttachments(applicantId, uuid, type);
    if ((attachlist != null) && (attachlist.size() > 0)) {
      attach = (ApplicantAttachments)attachlist.get(0);
    }
    return attach;
  }
  
  public JobApplicant getApplicantDetailswithUUID(String id, String uuid)
  {
    logger.info("Inside getApplicantDetailswithUUID method");
    
    JobApplicant applicant = this.applicantdao.getApplicantDetailswithUUID(id, uuid);
    
    ApplicantOtherDetails otherdetails = this.applicantdao.getApplicantOtherDetails(applicant.getApplicantId());
    
    applicant.setOtherdetails(otherdetails);
    
    return applicant;
  }
  
  public JobApplicant getApplicantDetailsbyEmailId(String emailId)
  {
    logger.info("Inside getApplicantDetailsbyEmailId method");
    
    JobApplicant applicant = this.applicantdao.getApplicantDetailsbyEmailId(emailId);
    
    return applicant;
  }
  
  public JobApplicant getApplicantDetailsByEmailId(String email)
  {
    return this.applicantdao.getApplicantDetailsByEmailId(email);
  }
  
  public ResumeSources getResumeSourcesByCode(String code)
  {
    return this.applicantdao.getResumeSourcesByCode(code);
  }
  
  public ResumeSources getResumeSourcesById(String sourceId)
  {
    return this.applicantdao.getResumeSourcesById(sourceId);
  }
  
  public List getSavedApplicantSearchesByUserid(long id)
  {
    logger.info("Inside getSavedApplicantSearchesByUserid method");
    return this.applicantdao.getSavedApplicantSearchesByUserid(id);
  }
  
  public JobApplicant getApplicantDetails(String id)
  {
    logger.info("Inside getApplicantDetails method");
    JobApplicant applicant = this.applicantdao.getApplicantDetails(id);
    ApplicantOtherDetails otherdetails = this.applicantdao.getApplicantOtherDetails(applicant.getApplicantId());
    
    applicant.setOtherdetails(otherdetails);
    
    return applicant;
  }
  
  public JobApplicant getApplicantByApplicantNumber(long applicant_number, long superUserKey)
  {
    logger.info("Inside getApplicantByApplicantNumber method");
    JobApplicant applicant = this.applicantdao.getApplicantByApplicantNumber(applicant_number, superUserKey);
    ApplicantOtherDetails otherdetails = this.applicantdao.getApplicantOtherDetails(applicant.getApplicantId());
    
    applicant.setOtherdetails(otherdetails);
    
    return applicant;
  }
  
  public List getApplicantByApplicantName(String name, long superUserKey)
  {
    logger.info("Inside getApplicantByApplicantName method");
    return this.applicantdao.getApplicantByApplicantName(name, superUserKey);
  }
  
  public long getReqIdByApplicantNumber(long applicant_number, long superUserKey)
  {
    return this.applicantdao.getReqIdByApplicantNumber(applicant_number, superUserKey);
  }
  
  public void rollBack()
  {
    if (this.applicantdao.getSessionFactory().getCurrentSession().getTransaction() != null) {
      this.applicantdao.getSessionFactory().getCurrentSession().getTransaction().rollback();
    }
  }
  
  public JobApplicant getApplicantStatusAndRequitionDetails(long applicantId)
  {
    logger.info("Inside getApplicantStatusAndRequitionDetails method");
    return this.applicantdao.getApplicantStatusAndRequitionDetails(applicantId);
  }
  
  public ApplicantOtherDetails getApplicantOtherDetails(long applicantId)
  {
    return this.applicantdao.getApplicantOtherDetails(applicantId);
  }
  
  public INineFormBean getINineFormDetails(String id)
  {
    logger.info("Inside getApplicantDetails method");
    return this.applicantdao.getINineFormDetails(id);
  }
  
  public INineFormBean getINineFormDetailsById(String id)
  {
    logger.info("Inside getApplicantDetails method");
    return this.applicantdao.getINineFormDetailsById(id);
  }
  
  public List getScreenFieldsDetails(String screencode, long superUserKey)
  {
    logger.info("Inside getScreenFieldsDetails method");
    return this.applicantdao.getScreenFieldsDetails(screencode, superUserKey);
  }
  
  public boolean isFieldAttachedToScreenCode(String screencode, String fieldCode, long super_user_key)
  {
    return this.applicantdao.isFieldAttachedToScreenCode(screencode, fieldCode, super_user_key);
  }
  
  public boolean isTabVisible(String screencode, long superUserKey)
  {
    List visibleList = this.applicantdao.getVisibleScreenFieldList(screencode, superUserKey);
    if ((visibleList != null) && (visibleList.size() > 0)) {
      return true;
    }
    return false;
  }
  
  public List getVisibleScreenFieldList(String screencode, long superUserKey)
  {
    logger.info("Inside getVisibleScreenFieldList method");
    if ((!StringUtils.isNullOrEmpty(screencode)) && (Constant.screenCodeListAppScreenOtherPackage.contains(screencode))) {
      screencode = BOFactory.getBusinessFilterBO().checkScreenCodeBasedOnPackage(superUserKey, screencode);
    }
    return this.applicantdao.getVisibleScreenFieldList(screencode, superUserKey);
  }
  
  public void arrangeScreenFields(String arrangestr)
  {
    logger.info("Inside arrangeScreenFields method" + arrangestr);
    if (!StringUtils.isNullOrEmpty(arrangestr))
    {
      arrangestr = arrangestr.substring(0, arrangestr.length() - 1);
      StringTokenizer strtoken = new StringTokenizer(arrangestr, ",");
      int k = 1;
      while (strtoken.hasMoreTokens())
      {
        String abc = strtoken.nextToken();
        logger.info("Inside arrangeScreenFields abc" + abc);
        abc = abc.substring(abc.lastIndexOf(".") + 1, abc.length());
        
        logger.info("Inside arrangeScreenFields abc" + abc);
        
        ScreenFields scf = this.applicantdao.getScreenField(abc);
        scf.setSequenceId(k);
        
        this.applicantdao.updateScreenField(scf);
        
        k++;
      }
    }
  }
  
  public void copyOfferAttachmentInitiateToRelease(JobApplicant applicant, long applicantId, String initiatetype, String releaseType)
  {
    logger.info("Inside getOfferAttachmentList method");
    this.applicantdao.copyOfferAttachmentInitiateToRelease(applicant, applicantId, initiatetype, releaseType);
  }
  
  public List getOfferAttachmentList(long applicantId, String type)
  {
    logger.info("Inside getOfferAttachmentList method");
    return this.applicantdao.getOfferAttachmentList(applicantId, type);
  }
  
  public List getOtherBenefitsList(long applicantId)
  {
    logger.info("Inside getOtherBenefitsList method");
    return this.applicantdao.getOtherBenefitsList(applicantId);
  }
  
  public int getTotalNoOfFilledPositionByReqId(long requisitionId)
  {
    logger.info("Inside getTotalNoOfFilledPositionByReqId method");
    return this.applicantdao.getTotalNoOfFilledPositionByReqId(requisitionId);
  }
  
  public long getTotalOfferedCTCByReqId(long requisitionId)
  {
    logger.info("Inside getTotalOfferedCTCByReqId method");
    return this.applicantdao.getTotalOfferedCTCByReqId(requisitionId);
  }
  
  public JobApplicant updateApplicant(JobApplicant applicant)
  {
    return this.applicantdao.updateApplicant(applicant);
  }
  
  public void updateScreenFields(ScreenFields screenFields)
  {
    this.applicantdao.updateScreenFields(screenFields);
  }
  
  public void saveScreenFields(ScreenFields screenFields)
  {
    this.applicantdao.saveScreenFields(screenFields);
  }
  
  public void deleteScreenFields(List screenfieldList)
  {
    this.applicantdao.deleteScreenFields(screenfieldList);
  }
  
  public List getAllOfferDeclinedResons()
  {
    return this.applicantdao.getAllOfferDeclinedResons();
  }
  
  public void deleteSavedApplicantSearch(String id, long userid)
  {
    logger.info("Inside deleteSavedApplicantSearch method");
    this.applicantdao.deleteSavedApplicantSearch(id, userid);
  }
  
  public void saveOfferAttachment(ApplicantOfferAttachment attachment, String applicantuuid, long addedById, String addedByName, String createdbytype)
  {
    logger.info("Inside saveOfferAttachment method");
    this.applicantdao.saveOfferAttachment(attachment);
    ApplicantActivity activity = new ApplicantActivity();
    activity.setApplicantId(attachment.getApplicantId());
    activity.setUuid(applicantuuid);
    activity.setCreatedBy(addedByName);
    activity.setUserId(addedById);
    activity.setUserFullName(addedByName);
    activity.setCreatedDate(new Date());
    activity.setComment(attachment.getAttahmentname());
    activity.setCreatedByType(createdbytype);
    activity.setActivityName("ADD_OFFER_ATTACHMENT");
    activity.setIsDisplayAppPage("Y");
    this.applicantdao.saveApplicantActivity(activity);
  }
  
  public void saveActionAttachment(ActionsAttachment attachment)
  {
    logger.info("Inside saveOfferAttachment method");
    this.applicantdao.saveActionAttachment(attachment);
  }
  
  private ApplicantActivity populateApplicantActivity(JobApplicant applicant, User user1, String createdbytype)
  {
    ApplicantActivity activity = new ApplicantActivity();
    activity.setApplicantId(applicant.getApplicantId());
    activity.setFullName(applicant.getFullName());
    activity.setCreatedBy(user1.getUserName());
    activity.setUserId(user1.getUserId());
    activity.setUserFullName(user1.getFirstName() + " " + user1.getLastName());
    activity.setUuid(applicant.getUuid());
    activity.setCreatedDate(new Date());
    activity.setComment("");
    activity.setCreatedByType(createdbytype);
    activity.setActivityName(applicant.getInterviewState());
    
    return activity;
  }
  
  public void updateApplicantAttachment(ApplicantAttachments attachment, String createdBy)
  {
    logger.info("Inside updateApplicantAttachment method");
    if (attachment != null)
    {
      ApplicantAttachments attachment1 = new ApplicantAttachments();
      attachment1.setApplicantId(attachment.getApplicantId());
      attachment1.setAppuuid(attachment.getAppuuid());
      attachment1.setAttachmentdata(attachment.getAttachmentdata());
      attachment1.setType(Common.APPLICANT_ATTACHMENT_TYPE_RESUME);
      attachment1.setAttahmentname(attachment.getAttahmentname());
      attachment1.setCreatedDate(new Date());
      attachment1.setCreatedBy(createdBy);
      attachment1.setUuid(attachment.getUuid());
      
      this.applicantdao.deleteApplicantAttachment(attachment.getApplicantId(), Common.APPLICANT_ATTACHMENT_TYPE_RESUME);
      if ((!StringUtils.isNullOrEmpty(Constant.getValue("scribd.enabled"))) && (Constant.getValue("scribd.enabled").equalsIgnoreCase("yes")) && 
        (attachment.getAttahmentname() != null)) {
        ScribdUtil.uploadFile(attachment1);
      }
      this.applicantdao.saveApplicantAttachment(attachment1);
    }
  }
  
  public void deleteActionAttachment(String offerAttachmentId)
  {
    logger.info("Inside deleteActionAttachment method");
    this.applicantdao.deleteActionAttachment(offerAttachmentId);
  }
  
  public List getOfferwatchListByApplicantId(long applicantId, String type)
  {
    logger.info("Inside getOfferwatchListByApplicantId method");
    return this.applicantdao.getOfferwatchListByApplicantId(applicantId, type);
  }
  
  public List getOfferApproverList(long applicantid)
  {
    logger.info("Inside getOfferApproverList method");
    return this.applicantdao.getOfferApproverList(applicantid);
  }
  
  public OfferApprover getNextOfferApprover(long id, int level)
  {
    logger.info("Inside getNextOfferApprover method");
    return this.applicantdao.getNextOfferApprover(id, level);
  }
  
  public void deleteOfferAttachment(long offerAttachmentId)
  {
    logger.info("Inside deleteOfferAttachment method");
    this.applicantdao.deleteOfferAttachment(offerAttachmentId);
  }
  
  public void deleteOfferAttachment(String uuid, String applicantuuid, User user1)
  {
    logger.info("Inside deleteOfferAttachment method");
    ApplicantOfferAttachment attachment = this.applicantdao.getOfferAttachmentDetailsByUuid(uuid);
    this.applicantdao.deleteOfferAttachment(uuid);
    try
    {
      String filePath = Constant.getValue("ATTACHMENT_PATH");
      filePath = filePath + File.separator + "reqattachment" + File.separator + uuid + File.separator;
      File file = new File(filePath);
      if (file.isDirectory())
      {
        String[] files = file.list();
        if ((files != null) && (files.length > 0)) {
          for (int i = 0; i < files.length; i++)
          {
            String f1name = files[i];
            File f1 = new File(filePath + f1name);
            if (f1.exists()) {
              f1.delete();
            }
          }
        } else if (file.exists()) {
          file.delete();
        }
      }
      if (file.exists()) {
        file.delete();
      }
    }
    catch (Exception e)
    {
      logger.info("Exception on delete attachment file" + e.getMessage());
    }
    ApplicantActivity activity = new ApplicantActivity();
    activity.setApplicantId(attachment.getApplicantId());
    activity.setUuid(applicantuuid);
    activity.setCreatedBy(user1.getUserName());
    activity.setUserId(user1.getUserId());
    activity.setUserFullName(user1.getFirstName() + " " + user1.getLastName());
    activity.setCreatedDate(new Date());
    activity.setComment(attachment.getAttahmentname());
    activity.setCreatedByType(Common.APPLICANT_CREATED_BY_TYPE_INTERNAL);
    activity.setActivityName("DELETE_OFFER_ATTACHMENT");
    activity.setIsDisplayAppPage("Y");
    this.applicantdao.saveApplicantActivity(activity);
  }
  
  public void saveApplicantActivity(ApplicantActivity activity)
  {
    this.applicantdao.saveApplicantActivity(activity);
  }
  
  public ApplicantOfferAttachment getOfferAttachmentDetails(long offerAttachmentId)
  {
    logger.info("Inside getOfferAttachmentDetails method");
    return this.applicantdao.getOfferAttachmentDetails(offerAttachmentId);
  }
  
  public OfferApprover getOfferApprover(String id)
  {
    logger.info("Inside getOfferApprover method");
    return this.applicantdao.getOfferApprover(id);
  }
  
  public SearchApplicant getSavedApplicantSearchesBySearchId(String id)
  {
    logger.info("Inside getSavedApplicantSearchesBySearchId method");
    return this.applicantdao.getSavedApplicantSearchesBySearchId(id);
  }
  
  public SearchApplicant getSavedApplicantSearchesBySearchUUID(String uuid)
  {
    logger.info("Inside getSavedApplicantSearchesBySearchUUID method");
    return this.applicantdao.getSavedApplicantSearchesBySearchUUID(uuid);
  }
  
  public SearchApplicant saveApplicantSearch(User user1, String savedsearchname, String recentSearchUuid)
  {
    logger.info("Inside saveApplicantSearch method");
    
    SearchApplicant searchrec = getSavedApplicantSearchesBySearchUUID(recentSearchUuid);
    
    searchrec.setSavedsearchname(savedsearchname);
    searchrec.setSaveshare("private");
    searchrec.setUpdatedBy(user1.getUserName());
    searchrec.setUpdatedDate(new Date());
    searchrec.setCreatedBy(user1.getUserName());
    searchrec.setCreatedDate(new Date());
    SearchApplicant searchapp = this.applicantdao.updateApplicantSearch(searchrec);
    





















































    return searchapp;
  }
  
  public SearchApplicant updateApplicantSearch(SearchApplicant search)
  {
    logger.info("Inside updateApplicantSearch method");
    return this.applicantdao.updateApplicantSearch(search);
  }
  
  public JobApplicant getApplicantIdNameEmailByUUID(String uuid)
  {
    return this.applicantdao.getApplicantIdNameEmailByUUID(uuid);
  }
  
  public JobApplicant getApplicantIdNameEmailByID(long application_id)
  {
    return this.applicantdao.getApplicantIdNameEmailByID(application_id);
  }
  
  public void saveCustomVariableListForSearch(List variabledatalist, long savesearchid)
  {
    logger.info("Inside saveCustomVariableListForSearch method");
    this.applicantdao.deleteCustomVariableListForSearch(savesearchid);
    this.applicantdao.saveCustomVariableListForSearch(variabledatalist);
  }
  
  public List getCustomVariableListDataForSearchList(long savesearchid)
  {
    List<SearchApplicantCustomFields> lst = this.applicantdao.getCustomVariableListDataForSearch(savesearchid);
    
    return lst;
  }
  
  public String searchapplicantListPage(String searchuuid, User user1, String results_str, String startIndex_str, String dir_str, String sort_str)
  {
    int results = -1;
    int startIndex = 0;
    String sort = "username";
    String dir = "asc";
    
    ApplicantSearchCriteria criteria = new ApplicantSearchCriteria();
    if ((!StringUtils.isNullOrEmpty(searchuuid)) && (!searchuuid.equals("null")))
    {
      criteria.setSearchuuid(searchuuid);
      




      SearchApplicant search = getSavedApplicantSearchesBySearchUUID(searchuuid);
      List<SearchApplicantQuestions> questionCriList = getSearchApplicantQuestions(search.getSearchappId());
      search.setQuestionCriList(questionCriList);
      List<SearchApplicantCustomFields> customFieldCriList = getCustomVariableListDataForSearchList(search.getSearchappId());
      search.setCustomFieldCriList(customFieldCriList);
      if (search != null) {
        ApplicantSearchUtil.setSearchCriteria(criteria, search);
      }
    }
    if ((startIndex_str != null) && (startIndex_str.length() > 0)) {
      startIndex = Integer.parseInt(startIndex_str);
    }
    if ((sort_str != null) && (sort_str.length() > 0)) {
      sort = sort_str;
    }
    if ((dir_str != null) && (dir_str.length() > 0)) {
      dir = dir_str;
    }
    if ((results_str != null) && (results_str.length() > 0)) {
      results = Integer.parseInt(results_str);
    }
    List dataList = new ArrayList();
    int totalSize = 0;
    





    Map m = searchApplicantsByVendorForPagination(user1, criteria, results, startIndex, dir_str, sort_str);
    dataList = (List)m.get(Common.APPLICANT_LIST);
    totalSize = ((Integer)m.get(Common.APPLICANT_COUNT)).intValue();
    




    String[] fields = ScreenSettingUtils.getArrayOfApplicationScreenSettingsKeyListPage("ALL_APP_SEARCH_SCREEN");
    

    String data = "{\n\"recordsReturned\":" + dataList.size() + "," + "\n" + "\"" + "totalRecords" + "\"" + ":" + totalSize + "," + "\n" + "\"" + "startIndex" + "\"" + ":" + startIndex + "," + "\n" + "\"" + "sort" + "\"" + ":" + "\"" + sort + "\"" + "," + "\n" + "\"" + "dir" + "\"" + ":" + "\"" + dir + "\"" + "," + "\n" + "\"" + "pageSize" + "\"" + ":" + results + "," + "\n" + "\"" + "records" + "\"" + ":" + StringUtils.createJasonDataTable(dataList, fields, user1) + "}";
    








    return data;
  }
  
  public Map getCustomVariableListDataForSearch(long savesearchid)
  {
    logger.info("Inside getCustomVariableListDataForSearch method");
    List<SearchApplicantCustomFields> lst = this.applicantdao.getCustomVariableListDataForSearch(savesearchid);
    
    Map<Long, SearchApplicantCustomFields> m = new HashMap();
    for (int i = 0; i < lst.size(); i++)
    {
      SearchApplicantCustomFields sacf = (SearchApplicantCustomFields)lst.get(i);
      m.put(Long.valueOf(sacf.getVariable_id()), sacf);
    }
    return m;
  }
  
  public void searchapplicantbyvendor(HttpServletRequest request, ApplicantForm applicantform, SearchApplicant searchsave, String searchappId, String fromdate, String todate, User user1)
    throws Exception
  {
    Map<Long, SearchApplicantCustomFields> m = new HashMap();
    if ((!StringUtils.isNullOrEmpty(searchappId)) && (!searchappId.equals("null")))
    {
      SearchApplicant search = getSavedApplicantSearchesBySearchUUID(searchappId);
      if (search != null)
      {
        ApplicantSearchUtil.setApplicantSearchParameter(applicantform, fromdate, todate, user1, searchsave);
        searchsave.setSearchappId(search.getSearchappId());
        searchsave.setSavedsearchname(search.getSavedsearchname());
        searchsave.setSearchuuid(search.getSearchuuid());
        searchsave.setCreatedBy(search.getCreatedBy());
        searchsave.setCreatedDate(search.getCreatedDate());
        searchsave.setNoofrows(new Integer(applicantform.getNoofrows()).intValue());
        updateApplicantSearch(searchsave);
      }
      applicantform.setQuestionCriList(getSearchApplicantQuestions(searchsave.getSearchappId()));
      



      List variabledatalist = VariableDataCaptureUtil.captureCustomVariablesForApplicantSearch(request, 0L, "APPLICANT_FORM", searchsave.getSearchappId(), user1.getSuper_user_key());
      if ((variabledatalist != null) && (variabledatalist.size() > 0))
      {
        saveCustomVariableListForSearch(variabledatalist, searchsave.getSearchappId());
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
      saveApplicantSearchRecent(searchsave);
      
      SearchApplicant search = getSavedApplicantSearchesBySearchUUID(searchappId);
      applicantform.setQuestionCriList(getSearchApplicantQuestions(searchsave.getSearchappId()));
      



      List variabledatalist = VariableDataCaptureUtil.captureCustomVariablesForApplicantSearch(request, 0L, "APPLICANT_FORM", searchsave.getSearchappId(), user1.getSuper_user_key());
      if ((variabledatalist != null) && (variabledatalist.size() > 0))
      {
        saveCustomVariableListForSearch(variabledatalist, searchsave.getSearchappId());
        for (int i = 0; i < variabledatalist.size(); i++)
        {
          SearchApplicantCustomFields sacf = (SearchApplicantCustomFields)variabledatalist.get(i);
          m.put(Long.valueOf(sacf.getVariable_id()), sacf);
        }
      }
    }
    applicantform.setCustomFieldData(m);
  }
  
  public SearchApplicant saveApplicantSearchRecent(SearchApplicant search)
  {
    logger.info("Inside saveApplicantSearch method");
    List searchList = this.applicantdao.getSavedApplicantSearchesByUser(search.getSaveduserid(), "recent");
    int limitsize = 12;
    if (!StringUtils.isNullOrEmpty(Constant.getValue("recent.searches.default.limit"))) {
      limitsize = new Integer(Constant.getValue("recent.searches.default.limit")).intValue();
    }
    if ((searchList != null) && (searchList.size() >= limitsize))
    {
      SearchApplicant searchold = (SearchApplicant)searchList.get(0);
      this.applicantdao.deleteApplicantSearch(searchold);
    }
    return this.applicantdao.saveApplicantSearch(search);
  }
  
  public ApplicantSearchCriteria getSearchCriteriaForexporttoexcelAllSearch(ApplicantSearchCriteria criteria, String searchuuid)
  {
    if ((!StringUtils.isNullOrEmpty(searchuuid)) && (!searchuuid.equals("null")))
    {
      SearchApplicant search = getSavedApplicantSearchesBySearchUUID(searchuuid);
      if (search != null)
      {
        List<SearchApplicantQuestions> questionCriList = getSearchApplicantQuestions(search.getSearchappId());
        search.setQuestionCriList(questionCriList);
        List<SearchApplicantCustomFields> customFieldCriList = getCustomVariableListDataForSearchList(search.getSearchappId());
        search.setCustomFieldCriList(customFieldCriList);
        
        ApplicantSearchUtil.setSearchCriteria(criteria, search);
      }
    }
    return criteria;
  }
  
  public void deleteSearchApplicantQuestions(String uuid)
  {
    this.applicantdao.deleteSearchApplicantQuestions(uuid);
  }
  
  public SearchApplicantQuestions saveSearchApplicantQuestions(SearchApplicantQuestions sq)
  {
    return this.applicantdao.saveSearchApplicantQuestions(sq);
  }
  
  public List<SearchApplicantQuestions> getSearchApplicantQuestions(long searchid)
  {
    return this.applicantdao.getSearchApplicantQuestions(searchid);
  }
  
  public List getEducationsByApplicantAndType(long applicantId, String type)
  {
    logger.info("Inside getEducationsByApplicantAndType method");
    return this.applicantdao.getEducationsByApplicantAndType(applicantId, type);
  }
  
  public List getPreviousOrgDetailsByApplicant(long applicantId)
  {
    logger.info("Inside getPreviousOrgDetailsByApplicant method");
    return this.applicantdao.getPreviousOrgDetailsByApplicant(applicantId);
  }
  
  public List getSkillsByApplicant(long applicantId)
  {
    logger.info("Inside getSkillsByApplicant method");
    return this.applicantdao.getSkillsByApplicant(applicantId);
  }
  
  public List getApplicantAttachments(long applicationId, String appuuid, String type)
  {
    logger.info("Inside getApplicantAttachments method");
    return this.applicantdao.getApplicantAttachments(applicationId, appuuid, type);
  }
  
  public ApplicantAttachments getApplicantAttachments(long applicationId, String type)
  {
    logger.info("Inside getApplicantAttachments method");
    return this.applicantdao.getApplicantAttachments(applicationId, type);
  }
  
  public ApplicantAttachments deleteResume(long applicationId, String type)
  {
    logger.info("Inside deleteResume method");
    return this.applicantdao.deleteResume(applicationId, type);
  }
  
  public JobApplicationEvent getLastEvent(long applicantId)
  {
    logger.info("Inside getLastEvent method");
    return this.applicantdao.getLastEvent(applicantId);
  }
  
  public ApplicantActivity getLastActivity(long applicantId, String uuid)
  {
    logger.info("Inside getLastApplicantActivity method");
    return this.applicantdao.getLastApplicantActivity(applicantId, uuid);
  }
  
  public List getAllReferences(long applicantid)
  {
    logger.info("Inside getAllReferences method");
    return this.applicantdao.getAllReferences(applicantid);
  }
  
  public void markDeleteApplicantsBySqlQuery(List applicaantList, User user1, String comment)
  {
    this.applicantdao.markDeleteApplicantsBySqlQuery(applicaantList, user1, comment);
    for (int i = 0; i < applicaantList.size(); i++)
    {
      String uuid = (String)applicaantList.get(i);
      
      JobApplicant applicant = this.applicantdao.getApplicantDetailsByUUID(uuid);
      try
      {
        markForDelete(applicant, user1, comment);
      }
      catch (Exception e)
      {
        logger.info("error on Exception", e);
      }
    }
  }
  
  public JobApplicant isApplicantDuplicate(JobApplicant applicant, long superUserKey)
  {
    JobApplicant oldapplicant = null;
    if ((!StringUtils.isNullOrEmpty(Constant.getValue("applicant.duplicate.not.allow.same.requisition"))) && (Constant.getValue("applicant.duplicate.not.allow.same.requisition").equalsIgnoreCase("yes")))
    {
      oldapplicant = this.applicantdao.getApplicantForDuplicateCheck(applicant.getReqId(), applicant.getEmail());
      if (oldapplicant != null) {
        return oldapplicant;
      }
    }
    if (!StringUtils.isNullOrEmpty(Constant.getValue("applicant.duplicate.not.allow.timeframe")))
    {
      int days = new Integer(Constant.getValue("applicant.duplicate.not.allow.timeframe")).intValue();
      oldapplicant = this.applicantdao.getApplicantForDuplicateCheckWithTimeSpan(applicant.getEmail(), days, superUserKey);
      if (oldapplicant != null) {
        return oldapplicant;
      }
    }
    return oldapplicant;
  }
  
  public JobApplicant isApplicantDuplicate(JobApplicant applicant, long superUserKey, long applicantId)
  {
    JobApplicant oldapplicant = null;
    if ((!StringUtils.isNullOrEmpty(Constant.getValue("applicant.duplicate.not.allow.same.requisition"))) && (Constant.getValue("applicant.duplicate.not.allow.same.requisition").equalsIgnoreCase("yes")))
    {
      oldapplicant = this.applicantdao.getApplicantForDuplicateCheck(applicant.getReqId(), applicant.getEmail(), applicantId);
      if (oldapplicant != null) {
        return oldapplicant;
      }
    }
    if (!StringUtils.isNullOrEmpty(Constant.getValue("applicant.duplicate.not.allow.timeframe")))
    {
      int days = new Integer(Constant.getValue("applicant.duplicate.not.allow.timeframe")).intValue();
      oldapplicant = this.applicantdao.getApplicantForDuplicateCheckWithTimeSpan(applicant.getEmail(), days, superUserKey, applicantId);
      if (oldapplicant != null) {
        return oldapplicant;
      }
    }
    return oldapplicant;
  }
  
  public void markForDelete(JobApplicant applicant, User user, String comment)
    throws Exception
  {
    List tolist = new ArrayList();
    


    String[] to = { user.getEmailId() };
    



    List cclist = BOFactory.getApplicantBO().getCCListForApplicant(user, applicant, null, EmailNotificationSettingFunction.FunctionNames.APPLICANT_MARK_DELETE.toString(), false);
    
    String[] cc = new String[cclist.size()];
    cclist.toArray(cc);
    

    EmailTask emailtask = new EmailTask(user.getEmailId(), to, cc, null, null, "dummysubject", null, "dummybody", null, 0, null);
    emailtask.setFunctionType(Common.APPLICANT_MARK_DELETE);
    emailtask.setUser(user);
    emailtask.setApplicant(applicant);
    emailtask.setComment(comment);
    EmailTaskManager.sendEmail(emailtask);
  }
  
  public List getApplicationEventListWithRatings(String applicationId)
  {
    return this.applicantdao.getApplicationEventListWithRatings(applicationId);
  }
  
  public ApplicantProfilePhoto saveApplicantProfilePhoto(ApplicantProfilePhoto pphoto)
  {
    logger.info("Inside saveApplicantProfilePhoto method");
    return this.applicantdao.saveApplicantProfilePhoto(pphoto);
  }
  
  public EducationDetails saveEducationDetails(EducationDetails edu)
  {
    logger.info("Inside saveEducationDetails method");
    return this.applicantdao.saveEducationDetails(edu);
  }
  
  public void deleteEducation(long eduId)
  {
    logger.info("Inside deleteEducation method");
    this.applicantdao.deleteEducation(eduId);
  }
  
  public void deleteEducationByUUID(String uuiid)
  {
    logger.info("Inside deleteEducationByUUID method");
    this.applicantdao.deleteEducationByUUID(uuiid);
  }
  
  public void deleteSkill(long skillid)
  {
    logger.info("Inside deleteSkill method");
    this.applicantdao.deleteSkill(skillid);
  }
  
  public void deleteSkillByUUID(String uuid)
  {
    logger.info("Inside deleteSkillByUUID method");
    this.applicantdao.deleteSkillByUUID(uuid);
  }
  
  public ApplicantSkills saveSkillsDetails(ApplicantSkills edu)
  {
    logger.info("Inside saveSkillsDetails method");
    return this.applicantdao.saveSkillsDetails(edu);
  }
  
  public void deletePreviousOrg(long prevorgid)
  {
    logger.info("Inside deletePreviousOrg method");
    this.applicantdao.deletePreviousOrg(prevorgid);
  }
  
  public List getActionAttachmentList(long idvalue, String actionName)
  {
    logger.info("Inside getActionAttachmentList method");
    return this.applicantdao.getActionAttachmentList(idvalue, actionName);
  }
  
  public PreviousOrgDetails savePreviousOrgDetails(PreviousOrgDetails org)
  {
    logger.info("Inside PreviousOrgDetails method");
    return this.applicantdao.savePreviousOrgDetails(org);
  }
  
  public List getOfferedApplicants(long reqId)
  {
    logger.info("Inside getOfferedApplicants method");
    return this.applicantdao.getOfferedApplicants(reqId);
  }
  
  public List getApplicantsByRequitionIdVendorIdAndStatus(long reqId, long userid, String state, String dir_str, String sort_str)
  {
    return this.applicantdao.getApplicantsByRequitionIdVendorIdAndStatus(reqId, userid, state, dir_str, sort_str);
  }
  
  public List getApplicantsByRequitionIdVendorIdAndStatusLike(long reqId, long userid, String status, String dir_str, String sort_str)
  {
    return this.applicantdao.getApplicantsByRequitionIdVendorIdAndStatusLike(reqId, userid, status, dir_str, sort_str);
  }
  
  public List getDistictApplicantState(long reqId)
  {
    return this.applicantdao.getDistictApplicantState(reqId);
  }
  
  public List getReferenceCheckApplicantsByRequitionIdandVendorId(long reqId, long userid, String dir_str, String sort_str)
  {
    return this.applicantdao.getReferenceCheckApplicantsByRequitionIdandVendorId(reqId, userid, dir_str, sort_str);
  }
  
  public List getReferenceCheckApplicantsByRequitionId(long reqId, String dir_str, String sort_str)
  {
    return this.applicantdao.getReferenceCheckApplicantsByRequitionId(reqId, dir_str, sort_str);
  }
  
  public int getCountOfAllApplicantsByRequisitionId(long reqid)
  {
    return this.applicantdao.getCountOfAllApplicantsByRequisitionId(reqid);
  }
  
  public List getApplicantsByRequitionIdAndStatus(long reqId, String status, String dir_str, String sort_str)
  {
    return this.applicantdao.getApplicantsByRequitionIdAndStatus(reqId, status, dir_str, sort_str);
  }
  
  public List getApplicantsByRequitionIdAndStatusLike(long reqId, String status, String dir_str, String sort_str)
  {
    return this.applicantdao.getApplicantsByRequitionIdAndStatusLike(reqId, status, dir_str, sort_str);
  }
  
  public ApplicantOfferAttachment getOfferAttachmentDetailsByUuid(String uuid)
  {
    logger.info("Inside getOfferAttachmentDetailsByUuid method");
    return this.applicantdao.getOfferAttachmentDetailsByUuid(uuid);
  }
  
  public ApplicantUser getApplicantUser(long applicantUserId)
  {
    return this.applicantdao.getApplicantUser(applicantUserId);
  }
  
  public ApplicantUser updateApplicantUser(ApplicantUser applicant)
  {
    return this.applicantdao.updateApplicantUser(applicant);
  }
  
  public ApplicantUser deleteApplicantUser(ApplicantUser applicant)
  {
    return this.applicantdao.deleteApplicantUser(applicant);
  }
  
  public ApplicantUser getApplicantByEmail(String emailid, String applicantCode)
  {
    return this.applicantdao.getApplicantByEmail(emailid, applicantCode);
  }
  
  public JobApplicationEvent getApplicationEvent(long applicantId, int eventtype)
  {
    return this.applicantdao.getApplicationEvent(applicantId, eventtype);
  }
  
  public List getAllResignedApplicantsNotProcessed()
  {
    logger.info("Inside getAllResignedApplicantsNotProcessed method");
    return this.applicantdao.getAllResignedApplicantsNotProcessed();
  }
  
  public List getAllApplicantsForRejectionEmail()
  {
    logger.info("Inside getAllApplicantsForRejectionEmail method");
    return this.applicantdao.getAllApplicantsForRejectionEmail();
  }
  
  public List getAllApplicantsForOfferEmail()
  {
    return this.applicantdao.getAllApplicantsForOfferEmail();
  }
  
  public List getAllApplicantsForIndexCreation()
  {
    return this.applicantdao.getAllApplicantsForIndexCreation();
  }
  
  public List getAllApplicants()
  {
    return this.applicantdao.getAllApplicants();
  }
  
  public List getAllApplicantsForDeletion()
  {
    logger.info("Inside getAllApplicantsForDeletion method");
    return this.applicantdao.getAllApplicantsForDeletion();
  }
  
  public List exportApplicants(User user, String cri, String targetjoiningdate, String interviewstate, long reqId)
  {
    return this.applicantdao.exportApplicants(user, cri, targetjoiningdate, interviewstate, reqId);
  }
  
  public List exportOnBoardApplicantsSearch(User user, ApplicantSearchCriteria criteria)
  {
    logger.info("Inside exportOnBoardApplicantsSearch method");
    return this.applicantdao.exportOnBoardApplicantsSearch(user, criteria);
  }
  
  public List exportApplicantsSearchHiringMgr(User user, ApplicantSearchCriteria criteria)
  {
    logger.info("Inside exportApplicantsSearchHiringMgr method");
    return this.applicantdao.exportApplicantsSearchHiringMgr(user, criteria);
  }
  
  public List exportApplicantsSearchRecruiter(User user, ApplicantSearchCriteria criteria)
  {
    logger.info("Inside exportApplicantsSearchRecruiter method");
    return this.applicantdao.exportApplicantsSearchRecruiter(user, criteria);
  }
  
  public List exportApplicantsSearchAllSearch(User user, ApplicantSearchCriteria criteria)
  {
    logger.info("Inside exportApplicantsSearchAllSearch method");
    return this.applicantdao.exportApplicantsSearchAllSearch(user, criteria);
  }
  
  public int getCountOfCommentsByLoginUser(long applicantId, long userId)
  {
    return this.applicantdao.getCountOfCommentsByLoginUser(applicantId, userId);
  }
  
  public boolean isReInitiateOfferButton(long userid, long formownerid, String isGroup, UserGroup usrgrp, long offerOwnerId)
  {
    boolean issucess = false;
    if (isLoggedInUserIsOwner(userid, formownerid, isGroup, usrgrp)) {
      issucess = true;
    } else if (userid == offerOwnerId) {
      issucess = true;
    }
    return issucess;
  }
  
  public boolean isLoggedInUserIsOwner(long userid, long formownerid, String isGroup, UserGroup usrgrp)
  {
    boolean isowner = false;
    if ((!StringUtils.isNullOrEmpty(isGroup)) && (isGroup.equals("Y")))
    {
      if (usrgrp != null)
      {
        Set users = usrgrp.getUsers();
        if ((users != null) && (users.size() > 0))
        {
          Iterator itr = users.iterator();
          while (itr.hasNext())
          {
            User touser = (User)itr.next();
            if ((touser != null) && (touser.getUserId() == userid))
            {
              isowner = true;
              break;
            }
          }
        }
      }
    }
    else if (formownerid == userid) {
      isowner = true;
    }
    return isowner;
  }
  
  public void test()
  {
    System.out.println("test");
    JobApplicant j = this.applicantdao.getApplicantDetails(String.valueOf(115));
    User user = new User();
    user.setUserId(1L);
    for (int i = 49000; i < 100000; i++)
    {
      JobApplicant jobapp1 = new JobApplicant();
      jobapp1.setFullName(i + "");
      jobapp1.setEmail(i + "@" + i + ".com");
      jobapp1.setDateofbirth(j.getDateofbirth());
      jobapp1.setReqId(j.getReqId());
      jobapp1.setJobTitle(j.getJobTitle());
      jobapp1.setHiringManager(j.getHiringManager());
      
      jobapp1.setResumename(j.getResumename());
      jobapp1.setUuid(UUID.randomUUID().toString());
      jobapp1.setOwner(user);
      jobapp1.setStatus("A");
      jobapp1.setInterviewState("Application Submitted");
      jobapp1.setCreatedBy("admin");
      jobapp1.setCreatedDate(new Date());
      this.applicantdao.saveApplicant(jobapp1);
      logger.info("appid" + jobapp1.getApplicantId());
    }
  }
  
  public List getApplicantsForPagination(int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.applicantdao.getApplicantsForPagination(pageSize, startIndex, dir_str, sort_str);
  }
  
  public int getCountOfAllApplicants()
  {
    return this.applicantdao.getCountOfAllApplicants();
  }
  
  public List getApplicantsForPaginationByUser(User user, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.applicantdao.getApplicantsForPaginationByUser(user, pageSize, startIndex, dir_str, sort_str);
  }
  
  public int getCountOfMyApplicants(User user)
  {
    return this.applicantdao.getCountOfMyApplicants(user);
  }
  
  public List getAllInterviewsByUser(User user, String interviewdate)
  {
    return this.applicantdao.getAllInterviewsByUser(user, interviewdate);
  }
  
  public List getAllInterviewsByUser(User user, String reviewerId, String interviewdate)
  {
    return this.applicantdao.getAllInterviewsByUser(user, reviewerId, interviewdate);
  }
  
  public List getAllInterviewsByUserOrgDept(User user, String reviewerId, String interviewdate, String orgid, String deptartmentid)
  {
    return this.applicantdao.getAllInterviewsByUserOrgDept(user, reviewerId, interviewdate, orgid, deptartmentid);
  }
  
  public List getAllInterviewsByDateAdmin(User user, String interviewdate)
  {
    return this.applicantdao.getAllInterviewsByDateAdmin(user, interviewdate);
  }
  
  public List getAllInterviewsByDateByRecruiter(User user, long recruiter_id, String interviewdate)
  {
    return this.applicantdao.getAllInterviewsByDateByRecruiter(user, recruiter_id, interviewdate);
  }
  
  public List getApplicantsForPaginationReqIdAndState(String reqId, String state, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.applicantdao.getApplicantsForPaginationReqIdAndState(reqId, state, pageSize, startIndex, dir_str, sort_str);
  }
  
  public Map getSummaryApplicantsForPaginationByReqIdAndState(String reqId, String state, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.applicantdao.getSummaryApplicantsForPaginationByReqIdAndState(reqId, state, pageSize, startIndex, dir_str, sort_str);
  }
  
  public int getCountOfApplicantsForPaginationReqIdAndState(String reqId, String state)
  {
    return this.applicantdao.getCountOfApplicantsForPaginationReqIdAndState(reqId, state);
  }
  
  public List getAllInterviewsByDateAndHiringManager(User user, long hiringMgrId, String interviewdate)
  {
    return this.applicantdao.getAllInterviewsByDateAndHiringManager(user, hiringMgrId, interviewdate);
  }
  
  public List searchOwnApplicantsForPagination(User user, String fullname, String reqid, String prevorg, String email, String appdate, String cri, String applicantNo, String interviewstate, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.applicantdao.searchOwnApplicantsForPagination(user, fullname, reqid, prevorg, email, appdate, cri, applicantNo, interviewstate, pageSize, startIndex, dir_str, sort_str);
  }
  
  public List searchOwnRefferalApplicantsForPagination(RefferalEmployee user, String fullname, String reqid, String prevorg, String email, String appdate, String cri, String applicantNo, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.applicantdao.searchOwnRefferalApplicantsForPagination(user, fullname, reqid, prevorg, email, appdate, cri, applicantNo, pageSize, startIndex, dir_str, sort_str);
  }
  
  public List searchOwnAgencyApplicantsForPagination(User user, String fullname, String reqid, String prevorg, String email, String appdate, String cri, String applicantNo, String interviewState, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.applicantdao.searchOwnAgencyApplicantsForPagination(user, fullname, reqid, prevorg, email, appdate, cri, applicantNo, interviewState, pageSize, startIndex, dir_str, sort_str);
  }
  
  public List searchOwnApplicantsForPagination(User user, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.applicantdao.searchOwnApplicantsForPagination(user, pageSize, startIndex, dir_str, sort_str);
  }
  
  public List searchOwnRefferalApplicantsForPagination(RefferalEmployee user, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.applicantdao.searchOwnRefferalApplicantsForPagination(user, pageSize, startIndex, dir_str, sort_str);
  }
  
  public List searchOwnAgencyApplicantsForPagination(User user, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.applicantdao.searchOwnAgencyApplicantsForPagination(user, pageSize, startIndex, dir_str, sort_str);
  }
  
  public Map searchApplicantsByVendorForPagination(User user, ApplicantSearchCriteria criteria, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    if ((criteria.getMilesWithin() != 0.0D) && (criteria.getZipCode() != 0L))
    {
      List<Long> zipcodeList = this.applicantdao.getListOfZipCodes(criteria.getZipCode(), criteria.getMilesWithin());
      logger.info("zipcodeList size" + zipcodeList.size() + zipcodeList.toString());
      criteria.setZipCodeList(zipcodeList);
      if ((zipcodeList != null) && (zipcodeList.size() == 0)) {
        criteria.setZipCodeSearchSize(9223372036854775807L);
      }
    }
    else if (criteria.getZipCode() != 0L)
    {
      List<Long> zipcodeList = new ArrayList();
      zipcodeList.add(Long.valueOf(criteria.getZipCode()));
      criteria.setZipCodeList(zipcodeList);
    }
    return this.applicantdao.searchApplicantsByVendorForPagination(user, criteria, pageSize, startIndex, dir_str, sort_str);
  }
  
  public List getApplicantsByRequistionId(long reqId)
  {
    return this.applicantdao.getApplicantsByRequistionId(reqId);
  }
  
  public ApplicantScoring saveApplicantScoring(ApplicantScoring appscore)
  {
    return this.applicantdao.saveApplicantScoring(appscore);
  }
  
  public List getApplicantScoringsByApplicantId(long appplicantId)
  {
    return this.applicantdao.getApplicantScoringsByApplicantId(appplicantId);
  }
  
  public void deleteIndexApplicantScoringByApplicantId(long applicantId)
  {
    this.applicantdao.deleteIndexApplicantScoringByApplicantId(applicantId);
  }
  
  public int getCountOfsearchApplicantsByVendor(User user, ApplicantSearchCriteria criteria)
  {
    return this.applicantdao.getCountOfsearchApplicantsByVendor(user, criteria);
  }
  
  public List searchOnBoardingApplicantsForPagination(User user, ApplicantSearchCriteria criteria, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.applicantdao.searchOnBoardingApplicantsForPagination(user, criteria, pageSize, startIndex, dir_str, sort_str);
  }
  
  public int getCountOfsearchOnBoardApplicants(User user, ApplicantSearchCriteria criteria)
  {
    return this.applicantdao.getCountOfsearchOnBoardApplicants(user, criteria);
  }
  
  public List searchApplicantsByVendorForPagination(int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.applicantdao.searchApplicantsByVendorForPagination(pageSize, startIndex, dir_str, sort_str);
  }
  
  public int getCountOfsearchOwnApplicant(User user, String fullname, String reqid, String prevorg, String email, String appdate, String cri, String applicantNo, String interviewState)
  {
    return this.applicantdao.getCountOfsearchOwnApplicant(user, fullname, reqid, prevorg, email, appdate, cri, applicantNo, interviewState);
  }
  
  public int getCountOfsearchOwnRefferalApplicant(RefferalEmployee user, String fullname, String reqid, String prevorg, String email, String appdate, String cri, String applicantNo)
  {
    return this.applicantdao.getCountOfsearchOwnRefferalApplicant(user, fullname, reqid, prevorg, email, appdate, cri, applicantNo);
  }
  
  public int getCountOfsearchOwnAgencyApplicant(User user, String fullname, String reqid, String prevorg, String email, String appdate, String cri, String applicantNo, String interviewState)
  {
    return this.applicantdao.getCountOfsearchOwnAgencyApplicant(user, fullname, reqid, prevorg, email, appdate, cri, applicantNo, interviewState);
  }
  
  public int getCountOfsearchOwnApplicant(User user)
  {
    return this.applicantdao.getCountOfsearchOwnApplicant(user);
  }
  
  public int getCountOfsearchOwnRefferalApplicant(RefferalEmployee user)
  {
    return this.applicantdao.getCountOfsearchOwnRefferalApplicant(user);
  }
  
  public int getCountOfsearchOwnAgencyApplicant(User user)
  {
    return this.applicantdao.getCountOfsearchOwnAgencyApplicant(user);
  }
  
  public List getApplicantsTrackingByStatusPaginationByUser(User user, String status, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.applicantdao.getApplicantsTrackingByStatusPaginationByUser(user, status, pageSize, startIndex, dir_str, sort_str);
  }
  
  public int getCountOfApplicantsTrackingByStatus(User user, String status)
  {
    return this.applicantdao.getCountOfApplicantsTrackingByStatus(user, status);
  }
  
  public int getCountOfsearchApplicantsByVendor()
  {
    return this.applicantdao.getCountOfsearchApplicantsByVendor();
  }
  
  public List getInterviewWatchList(long eventId)
  {
    return this.applicantdao.getInterviewWatchlist(eventId);
  }
  
  public List getApplicantsByInterviewStateAndTargetJoiningDate(User user, String interviewState, int noofdays, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.applicantdao.getApplicantsByInterviewStateAndTargetJoiningDate(user, interviewState, noofdays, pageSize, startIndex, dir_str, sort_str);
  }
  
  public List getApplicantsByInterviewStateAndTargetJoiningDate(User user, String interviewState, int noofdays)
  {
    return this.applicantdao.getApplicantsByInterviewStateAndTargetJoiningDate(user, interviewState, noofdays);
  }
  
  public List getApplicantsByInterviewStateAndOfferDeclinedDate(User user, String interviewState, int noofdays)
  {
    return this.applicantdao.getApplicantsByInterviewStateAndOfferDeclinedDate(user, interviewState, noofdays);
  }
  
  public List getApplicantsByInterviewStateAndJoiningDateAdmin(User user, String interviewState, int noofdays)
  {
    return this.applicantdao.getApplicantsByInterviewStateAndJoiningDateAdmin(user, interviewState, noofdays);
  }
  
  public List getApplicantsByInterviewStateAndJoiningDate(User user, String interviewState, int noofdays)
  {
    return this.applicantdao.getApplicantsByInterviewStateAndJoiningDate(user, interviewState, noofdays);
  }
  
  public List getApplicantsByInterviewStateAndJoiningDateRecruiter(User user, String interviewState, int noofdays)
  {
    return this.applicantdao.getApplicantsByInterviewStateAndJoiningDateRecruiter(user, interviewState, noofdays);
  }
  
  public List getApplicantsByInterviewStateAndTargetJoiningDateAdmin(User user, String interviewState, int noofdays)
  {
    return this.applicantdao.getApplicantsByInterviewStateAndTargetJoiningDateAdmin(user, interviewState, noofdays);
  }
  
  public List getApplicantsByInterviewStateAndOfferDeclinedDateAdmin(User user, String interviewState, int noofdays)
  {
    return this.applicantdao.getApplicantsByInterviewStateAndOfferDeclinedDateAdmin(user, interviewState, noofdays);
  }
  
  public DataTableBean applicantsByInterviewStateAndTargetJoiningDate(User user, String interviewState, int noofdays, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    List valueList = this.applicantdao.getApplicantsByInterviewStateAndTargetJoiningDate(user, interviewState, noofdays, pageSize, startIndex, dir_str, sort_str);
    int totalcount = this.applicantdao.getCountOfApplicantsByInterviewStateAndTargetJoiningDate(user, interviewState, noofdays);
    DataTableBean dtbean = new DataTableBean();
    dtbean.setValueList(valueList);
    dtbean.setTotalcount(totalcount);
    return dtbean;
  }
  
  public DataTableBean applicantsByInterviewStateAndTargetJoiningDateAdmin(User user, String interviewState, int noofdays, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    List valueList = this.applicantdao.getApplicantsByInterviewStateAndTargetJoiningDateAdmin(user, interviewState, noofdays, pageSize, startIndex, dir_str, sort_str);
    int totalcount = this.applicantdao.getCountOfApplicantsByInterviewStateAndTargetJoiningDateAdmin(user, interviewState, noofdays);
    DataTableBean dtbean = new DataTableBean();
    dtbean.setValueList(valueList);
    dtbean.setTotalcount(totalcount);
    return dtbean;
  }
  
  public int getCountOfApplicantsByInterviewStateAndTargetJoiningDate(User user, String interviewState, int noofdays)
  {
    return this.applicantdao.getCountOfApplicantsByInterviewStateAndTargetJoiningDate(user, interviewState, noofdays);
  }
  
  public List getApplicantsInOfferProcessByHiringMgr(User user, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.applicantdao.getApplicantsInOfferProcessByHiringMgr(user, pageSize, startIndex, dir_str, sort_str);
  }
  
  public JobApplicationEvent getApplicationEvent(long applicantId, int eventtype, long testId)
  {
    return this.applicantdao.getApplicationEvent(applicantId, eventtype, testId);
  }
  
  public void updateApplicationEvent(JobApplicationEvent event)
  {
    this.applicantdao.updateApplicationEvent(event);
  }
  
  public DataTableBean applicantsInOfferProcessByHiringMgr(User user, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    List valueList = this.applicantdao.getApplicantsInOfferProcessByHiringMgr(user, pageSize, startIndex, dir_str, sort_str);
    int totalcount = this.applicantdao.getCountOfApplicantsInOfferProcessByHiringMgr(user);
    DataTableBean dtbean = new DataTableBean();
    dtbean.setValueList(valueList);
    dtbean.setTotalcount(totalcount);
    return dtbean;
  }
  
  public DataTableBean applicantsInOfferProcessAdmin(User user, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    List valueList = this.applicantdao.getApplicantsInOfferProcessAdmin(user, pageSize, startIndex, dir_str, sort_str);
    int totalcount = this.applicantdao.getCountOfApplicantsInOfferProcessAdmin(user);
    DataTableBean dtbean = new DataTableBean();
    dtbean.setValueList(valueList);
    dtbean.setTotalcount(totalcount);
    return dtbean;
  }
  
  public int getCountOfApplicantsInOfferProcessByHiringMgr(User user)
  {
    return this.applicantdao.getCountOfApplicantsInOfferProcessByHiringMgr(user);
  }
  
  public SalaryPlan getSalaryPlanByApplicantId(long applicantId)
  {
    return this.applicantdao.getSalaryPlanByApplicantId(applicantId);
  }
  
  public DataTableBean getApplicantsByInterviewStateAndTargetJoiningDateRecruiter(User user, String interviewState, int noofdays, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.applicantdao.getApplicantsByInterviewStateAndTargetJoiningDateRecruiter(user, interviewState, noofdays, pageSize, startIndex, dir_str, sort_str);
  }
  
  public List getApplicantsByInterviewStateAndTargetJoiningDateRecruiter(User user, String interviewState, int noofdays)
  {
    return this.applicantdao.getApplicantsByInterviewStateAndTargetJoiningDateRecruiter(user, interviewState, noofdays);
  }
  
  public List getApplicantsByInterviewStateAndOfferDeclinedDateRecruiter(User user, String interviewState, int noofdays)
  {
    return this.applicantdao.getApplicantsByInterviewStateAndOfferDeclinedDateRecruiter(user, interviewState, noofdays);
  }
  
  public int getCountOfApplicantsByInterviewStateAndTargetJoiningDateRecruiter(User user, String interviewState, int noofdays)
  {
    return this.applicantdao.getCountOfApplicantsByInterviewStateAndTargetJoiningDateRecruiter(user, interviewState, noofdays);
  }
  
  public DataTableBean getApplicantsInOfferProcessByRecruiter(User user, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.applicantdao.getApplicantsInOfferProcessByRecruiter(user, pageSize, startIndex, dir_str, sort_str);
  }
  
  public int getCountOfApplicantsInOfferProcessByRecruiter(User user)
  {
    return this.applicantdao.getCountOfApplicantsInOfferProcessByRecruiter(user);
  }
  
  public Map searchOwnApplicantsForPaginationHiringMgr(User user, ApplicantSearchCriteria criteria, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.applicantdao.searchOwnApplicantsForPaginationHiringMgr(user, criteria, pageSize, startIndex, dir_str, sort_str);
  }
  
  public Map searchTalentPoolApplicants(User user, String id, String applicantNo, String applicantName, String primarySkill, String keywords, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.applicantdao.searchTalentPoolApplicants(user, id, applicantNo, applicantName, primarySkill, keywords, pageSize, startIndex, dir_str, sort_str);
  }
  
  public Map searchApplicantsInTalentPool(User user, String applicantNo, String applicantName, String primarySkill, String keywords, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.applicantdao.searchApplicantsInTalentPool(user, applicantNo, applicantName, primarySkill, keywords, pageSize, startIndex, dir_str, sort_str);
  }
  
  public Map searchOwnApplicantsForPaginationRecruiter(User user, ApplicantSearchCriteria criteria, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.applicantdao.searchOwnApplicantsForPaginationRecruiter(user, criteria, pageSize, startIndex, dir_str, sort_str);
  }
  
  public int getCountOfsearchOwnApplicantsForPaginationHiringMgr(User user, ApplicantSearchCriteria criteria)
  {
    return this.applicantdao.getCountOfsearchOwnApplicantsForPaginationHiringMgr(user, criteria);
  }
  
  public int getCountOfsearchOwnApplicantsForPaginationRecruiter(User user, ApplicantSearchCriteria criteria)
  {
    return this.applicantdao.getCountOfsearchOwnApplicantsForPaginationRecruiter(user, criteria);
  }
  
  public List searchOwnApplicantsForPaginationHiringMgr(User user, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.applicantdao.searchOwnApplicantsForPaginationHiringMgr(user, pageSize, startIndex, dir_str, sort_str);
  }
  
  public int getCountOfsearchOwnApplicantsForPaginationHiringMgr(User user)
  {
    return this.applicantdao.getCountOfsearchOwnApplicantsForPaginationHiringMgr(user);
  }
  
  public List getActivityList(long applicantId, String uuid)
  {
    return this.applicantdao.getActivityList(applicantId, uuid);
  }
  
  public List getApplicantListByEmailIdWithoutCurrent(String emailId, long applicantId, long superUserKey)
  {
    return this.applicantdao.getApplicantListByEmailIdWithoutCurrent(emailId, applicantId, superUserKey);
  }
  
  public JobApplicant getUUIDNameEmailByApplicantId(long applicantId)
  {
    return this.applicantdao.getUUIDNameEmailByApplicantId(applicantId);
  }
  
  public List getApplicantsByOfferReleaseAndAccepted(long reqid)
  {
    return this.applicantdao.getApplicantsByOfferReleaseAndAccepted(reqid);
  }
  
  public Set getUniqueActivitySet(long applicantId, String uuid)
  {
    return this.applicantdao.getUniqueActivityList(applicantId, uuid);
  }
  
  public Set getUniqueDisplaybleAppPageActivityList(long applicantId, String uuid)
  {
    return this.applicantdao.getUniqueDisplaybleAppPageActivityList(applicantId, uuid);
  }
  
  public List getApplicationEventList(String applicantId)
  {
    return this.applicantdao.getApplicationEventList(applicantId);
  }
  
  public Map createJSONGraphForApplicantKeywordScoring(long applicantId)
  {
    Map m = new HashMap();
    String str = "";
    String fields = "[ \"keyword\"";
    String seriesdef = "";
    
    List appscoreList = this.applicantdao.getApplicantScoringsByApplicantId(applicantId);
    


    int k = 0;
    for (int i = 0; i < appscoreList.size(); i++)
    {
      ApplicantScoring appscore = (ApplicantScoring)appscoreList.get(i);
      String name = appscore.getKeyword();
      if (!StringUtils.isNullOrEmpty(name)) {
        name = name.replace("\"", "");
      }
      String tempstr = "{ keyword: \"" + name + "\"";
      

      tempstr = tempstr + ", " + "score" + ":" + appscore.getScore();
      if (k == 0)
      {
        fields = fields + ", " + "\"" + "score" + "\"";
        seriesdef = seriesdef + "{ displayName: " + "\"" + "Score" + "\"" + ", " + "yField: " + "\"" + "score" + "\"" + " }" + ",";
      }
      tempstr = tempstr + " },";
      str = str + tempstr;
      k++;
    }
    if (!StringUtils.isNullOrEmpty(str)) {
      str = str.substring(0, str.length() - 1);
    }
    fields = fields + " ]";
    if (!StringUtils.isNullOrEmpty(seriesdef)) {
      seriesdef = seriesdef.substring(0, seriesdef.length() - 1);
    }
    m.put("detail", str);
    m.put("fields", fields);
    m.put("seriesdef", seriesdef);
    
    return m;
  }
  
  public Map createJSONGraphForApplicant(long applicantId)
  {
    Map m = new HashMap();
    String str = "";
    String fields = "[ \"competency\"";
    String seriesdef = "";
    
    List distinctratinglist = this.applicantdao.getDistinctRatingName(applicantId, Common.COMPETENCY_TYPE);
    


    int k = 0;
    for (int i = 0; i < distinctratinglist.size(); i++)
    {
      String name = (String)distinctratinglist.get(i);
      List ratinglist = this.applicantdao.getApplicantRatingsByNameAndType(applicantId, name, Common.COMPETENCY_TYPE);
      if (!StringUtils.isNullOrEmpty(name)) {
        name = name.replace("\"", "");
      }
      String tempstr = "{ competency: \"" + name + "\"";
      for (int j = 0; j < ratinglist.size(); j++)
      {
        ApplicantRating apr = (ApplicantRating)ratinglist.get(j);
        String intstate = "";
        if (!StringUtils.isNullOrEmpty(apr.getInterviewState()))
        {
          intstate = apr.getInterviewState().replace("Cleared", "");
          intstate = intstate.replace("-", "");
          intstate = intstate.replace(" ", "");
        }
        tempstr = tempstr + ", " + intstate + ":" + apr.getYourrating();
        if (k == 0)
        {
          fields = fields + ", " + "\"" + intstate + "\"";
          seriesdef = seriesdef + "{ displayName: " + "\"" + Constant.getUIValue(intstate) + "\"" + ", " + "yField: " + "\"" + intstate + "\"" + " }" + ",";
        }
      }
      tempstr = tempstr + " },";
      str = str + tempstr;
      k++;
    }
    if (!StringUtils.isNullOrEmpty(str)) {
      str = str.substring(0, str.length() - 1);
    }
    fields = fields + " ]";
    if (!StringUtils.isNullOrEmpty(seriesdef)) {
      seriesdef = seriesdef.substring(0, seriesdef.length() - 1);
    }
    m.put("detail", str);
    m.put("fields", fields);
    m.put("seriesdef", seriesdef);
    
    return m;
  }
  
  public Map createJSONGraphForApplicantAccomplishments(long applicantId)
  {
    Map m = new HashMap();
    String str = "";
    String fields = "[ \"accomplishment\"";
    String seriesdef = "";
    
    List distinctratinglist = this.applicantdao.getDistinctRatingName(applicantId, Common.ACCOMPLISHMENT_TYPE);
    


    int k = 0;
    for (int i = 0; i < distinctratinglist.size(); i++)
    {
      String name = (String)distinctratinglist.get(i);
      List ratinglist = this.applicantdao.getApplicantRatingsByNameAndType(applicantId, name, Common.ACCOMPLISHMENT_TYPE);
      if (!StringUtils.isNullOrEmpty(name)) {
        name = name.replace("\"", "");
      }
      String tempstr = "{ accomplishment: \"" + name + "\"";
      for (int j = 0; j < ratinglist.size(); j++)
      {
        ApplicantRating apr = (ApplicantRating)ratinglist.get(j);
        String intstate = "";
        if (!StringUtils.isNullOrEmpty(apr.getInterviewState()))
        {
          intstate = apr.getInterviewState().replace("Cleared", "");
          intstate = intstate.replace("-", "");
          intstate = intstate.replace(" ", "");
        }
        tempstr = tempstr + ", " + intstate + ":" + apr.getYourrating();
        if (k == 0)
        {
          fields = fields + ", " + "\"" + intstate + "\"";
          seriesdef = seriesdef + "{ displayName: " + "\"" + Constant.getUIValue(intstate) + "\"" + ", " + "yField: " + "\"" + intstate + "\"" + " }" + ",";
        }
      }
      tempstr = tempstr + " },";
      str = str + tempstr;
      k++;
    }
    if (!StringUtils.isNullOrEmpty(str)) {
      str = str.substring(0, str.length() - 1);
    }
    fields = fields + " ]";
    if (!StringUtils.isNullOrEmpty(seriesdef)) {
      seriesdef = seriesdef.substring(0, seriesdef.length() - 1);
    }
    m.put("detail", str);
    m.put("fields", fields);
    m.put("seriesdef", seriesdef);
    
    return m;
  }
  
  public Map createJSONGraphForApplicantsList(List applicantList)
  {
    Map m = new HashMap();
    String str = "";
    String fields = "[ \"appname\"";
    String seriesdef = "";
    




    int k = 0;
    for (int i = 0; i < applicantList.size(); i++)
    {
      JobApplicant applicant = (JobApplicant)applicantList.get(i);
      String appname = applicant.getFullName();
      List ratinglist = this.applicantdao.getAverageApplicantRatingsByApplicantAndType(applicant.getApplicantId(), Common.COMPETENCY_TYPE);
      if (!StringUtils.isNullOrEmpty(appname)) {
        appname = appname.replace("\"", "");
      }
      String tempstr = "{ appname: \"" + appname + "\"";
      for (int j = 0; j < ratinglist.size(); j++)
      {
        ApplicantRating apr = (ApplicantRating)ratinglist.get(j);
        String name = apr.getName();
        String namenew = "";
        if (!StringUtils.isNullOrEmpty(name)) {
          namenew = name.replace("\"", "");
        }
        if (!StringUtils.isNullOrEmpty(name))
        {
          name = name.replace("\"", "");
          name = name.replace("-", "");
          name = name.replace(" ", "");
        }
        tempstr = tempstr + ", " + name + ":" + apr.getYourrating();
        if (k == 0)
        {
          fields = fields + ", " + "\"" + name + "\"";
          seriesdef = seriesdef + "{ displayName: " + "\"" + namenew + "\"" + ", " + "yField: " + "\"" + name + "\"" + " }" + ",";
        }
      }
      tempstr = tempstr + " },";
      str = str + tempstr;
      k++;
    }
    if (!StringUtils.isNullOrEmpty(str)) {
      str = str.substring(0, str.length() - 1);
    }
    fields = fields + " ]";
    if (!StringUtils.isNullOrEmpty(seriesdef)) {
      seriesdef = seriesdef.substring(0, seriesdef.length() - 1);
    }
    m.put("detail", str);
    m.put("fields", fields);
    m.put("seriesdef", seriesdef);
    
    return m;
  }
  
  public void commentEmail(String fromemailid, String[] to, JobApplicant applicant, String comment)
    throws Exception
  {
    String[] bcc = null;
    



    List cclist = new ArrayList();
    if (applicant.getReqId() > 0L)
    {
      JobRequisition jb = BOFactory.getJobRequistionBO().getJobRequision(String.valueOf(applicant.getReqId()));
      cclist = BOFactory.getApplicantBO().getCCListForApplicant(applicant, jb, true, true, true, true);
    }
    String[] cc = new String[cclist.size()];
    cclist.toArray(cc);
    
    String replyTo = fromemailid;
    
    EmailTask emailtask = new EmailTask(replyTo, to, cc, bcc, replyTo, "dummysubject", null, "dummybody", null, 0, null);
    



    emailtask.setComment(comment);
    emailtask.setFunctionType(Common.COMMENT_TO_ALL);
    emailtask.setApplicant(applicant);
    EmailTaskManager.sendEmail(emailtask);
  }
  
  public Map createLineChartJSONGraphForApplicantsList(List applicantList, String type)
  {
    Map m = new HashMap();
    String str = "";
    String fields = "[ \"competency\"";
    String seriesdef = "";
    
    JobApplicant applicant1 = (JobApplicant)applicantList.get(0);
    List distinctratinglist = this.applicantdao.getDistinctRatingName(applicant1.getApplicantId(), type);
    

    int k = 0;
    for (int i = 0; i < distinctratinglist.size(); i++)
    {
      String ratingname = (String)distinctratinglist.get(i);
      
      String tempstr = "{ competency: \"" + ratingname + "\"";
      for (int ii = 0; ii < applicantList.size(); ii++)
      {
        JobApplicant applicant = (JobApplicant)applicantList.get(ii);
        
        String dispalyname = applicant.getFullName();
        if (!StringUtils.isNullOrEmpty(dispalyname)) {
          dispalyname = dispalyname.replace("\"", "");
        }
        ApplicantRating rate = this.applicantdao.getAverageApplicantRatingsByApplicantAndTypeAndName(applicant.getApplicantId(), ratingname, type);
        

        tempstr = tempstr + ", " + applicant.getApplicantId() + ":" + rate.getYourrating();
        if (k == 0)
        {
          fields = fields + ", " + "\"" + applicant.getApplicantId() + "\"";
          seriesdef = seriesdef + "{ displayName: " + "\"" + dispalyname + "\"" + ", " + "yField: " + "\"" + applicant.getApplicantId() + "\"" + " }" + ",";
        }
      }
      tempstr = tempstr + " },";
      str = str + tempstr;
      k++;
    }
    if (!StringUtils.isNullOrEmpty(str)) {
      str = str.substring(0, str.length() - 1);
    }
    fields = fields + " ]";
    if (!StringUtils.isNullOrEmpty(seriesdef)) {
      seriesdef = seriesdef.substring(0, seriesdef.length() - 1);
    }
    m.put("detail", str);
    m.put("fields", fields);
    m.put("seriesdef", seriesdef);
    
    return m;
  }
  
  public Map searchApplicantPaginationByReqIdAndVendorId(User user, String requistionId, String state, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.applicantdao.searchApplicantPaginationByReqIdAndVendorId(user, requistionId, state, pageSize, startIndex, dir_str, sort_str);
  }
  
  public Map createColumnChartJSONGraphForApplicantsList(List applicantList, String type)
  {
    Map m = new HashMap();
    String str = "";
    String fields = "[ \"competency1\"";
    String seriesdef = "";
    
    JobApplicant applicant1 = (JobApplicant)applicantList.get(0);
    List distinctratinglist = this.applicantdao.getDistinctRatingName(applicant1.getApplicantId(), type);
    

    int k = 0;
    for (int i = 0; i < distinctratinglist.size(); i++)
    {
      String ratingname = (String)distinctratinglist.get(i);
      
      String tempstr = "{ competency1: \"" + ratingname + "\"";
      for (int ii = 0; ii < applicantList.size(); ii++)
      {
        JobApplicant applicant = (JobApplicant)applicantList.get(ii);
        
        String dispalyname = applicant.getFullName();
        if (!StringUtils.isNullOrEmpty(dispalyname)) {
          dispalyname = dispalyname.replace("\"", "");
        }
        ApplicantRating rate = this.applicantdao.getAverageApplicantRatingsByApplicantAndTypeAndName(applicant.getApplicantId(), ratingname, type);
        

        tempstr = tempstr + ", " + applicant.getApplicantId() + ":" + rate.getYourrating();
        if (k == 0)
        {
          fields = fields + ", " + "\"" + applicant.getApplicantId() + "\"";
          seriesdef = seriesdef + "{ displayName: " + "\"" + dispalyname + "\"" + ", " + "yField: " + "\"" + applicant.getApplicantId() + "\"" + " }" + ",";
        }
      }
      tempstr = tempstr + " },";
      str = str + tempstr;
      k++;
    }
    if (!StringUtils.isNullOrEmpty(str)) {
      str = str.substring(0, str.length() - 1);
    }
    fields = fields + " ]";
    if (!StringUtils.isNullOrEmpty(seriesdef)) {
      seriesdef = seriesdef.substring(0, seriesdef.length() - 1);
    }
    m.put("detail", str);
    m.put("fields", fields);
    m.put("seriesdef", seriesdef);
    
    return m;
  }
  
  public List<String> getAgencyApplicantNames(String query, long agencyId)
  {
    List<String> applicantList = this.applicantdao.getAgencyApplicantNames(query, agencyId);
    






    return applicantList;
  }
  
  public List<String> getcurrentOrganizationNames(String query, long agencyId)
  {
    List<String> currentOrgList = this.applicantdao.getcurrentOrganizationNames(query, agencyId);
    






    return currentOrgList;
  }
  
  public List<String> getReferralApplicantNames(RefferalEmployee emp, String query)
  {
    List<String> applicantList = this.applicantdao.getReferralApplicantNames(emp, query);
    
    return applicantList;
  }
  
  public boolean validateBudget(JobRequisition jb, HttpServletRequest request, ActionErrors errors)
  {
    boolean isErrror = false;
    BudgetCode budgetCode = jb.getBudgetcode();
    List jobreqList = this.jobrequisitiondao.getTotalAllRequistionsByBudgetCodeId(budgetCode.getBudgetId());
    long totalamountused = 0L;
    for (int i = 0; i < jobreqList.size(); i++)
    {
      JobRequisition jobreq = (JobRequisition)jobreqList.get(i);
      long totalctc = getTotalOfferedCTCByReqId(jobreq.getJobreqId());
      totalamountused += totalctc;
    }
    logger.info("totalamountused for budget code:" + budgetCode.getBudgetamount() + ":" + totalamountused);
    if (totalamountused > budgetCode.getBudgetamount())
    {
      errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError("error.budgetrange.exceed"));
      
      isErrror = true;
    }
    return isErrror;
  }
  
  public void sendEmailForApplicant(User user1, JobApplicant app, String comment, String functionType, String notificationFunction, List toList, boolean isApplicant)
    throws Exception
  {
    boolean isHiringMgr = false;
    boolean isRecruiter = false;
    boolean isCurrentOwner = false;
    boolean isWatcher = false;
    
    JobRequisition jb = BOFactory.getJobRequistionBO().getJobRequision(String.valueOf(app.getReqId()));
    
    EmailNotificationSetting ems = BOFactory.getLovBO().getEmailNotificationSetting(notificationFunction, app.getSuper_user_key());
    if (ems != null)
    {
      if (ems.getIsHiringMgr().equals("A")) {
        isHiringMgr = true;
      }
      if (ems.getIsRecruiter().equals("A")) {
        isRecruiter = true;
      }
      if (ems.getIsWatcher().equals("A")) {
        isWatcher = true;
      }
      if (ems.getIsCurrentOwner().equals("A")) {
        isCurrentOwner = true;
      }
    }
    List cclist = new ArrayList();
    String[] to = null;
    if ((toList != null) && (toList.size() > 0))
    {
      toList.add(user1.getEmailId());
      to = new String[toList.size()];
      cclist.toArray(to);
    }
    else
    {
      toList = new ArrayList();
      toList.add(user1.getEmailId());
      to = new String[toList.size()];
      cclist.toArray(to);
    }
    cclist = getCCListForApplicant(app, jb, isWatcher, isHiringMgr, isRecruiter, isCurrentOwner);
    if (!StringUtils.isNullOrEmpty(Constant.getValue("all.applicant.activities.cc"))) {
      cclist.add(Constant.getValue("all.applicant.activities.cc"));
    }
    if (isApplicant) {
      cclist.add(app.getEmail());
    }
    String[] cc = new String[cclist.size()];
    cclist.toArray(cc);
    
    String[] bcc = null;
    
    String replyTo = "\"" + user1.getFirstName() + " " + user1.getLastName() + "\"" + " " + "<" + user1.getEmailId() + ">";
    


    EmailTask emailtask = new EmailTask(replyTo, to, cc, bcc, replyTo, "dummysubject", null, "dummybody", null, 0, null);
    


    emailtask.setUser(user1);
    emailtask.setFunctionType(functionType);
    emailtask.setJobreq(jb);
    emailtask.setApplicant(app);
    emailtask.setComment(comment);
    EmailTaskManager.sendEmail(emailtask);
  }
  
  public List getCCListForApplicant(User user1, JobApplicant app, JobRequisition jb, String notificationFunction, boolean isUser)
    throws Exception
  {
    boolean isHiringMgr = false;
    boolean isRecruiter = false;
    boolean isCurrentOwner = false;
    boolean isWatcher = false;
    if (jb == null) {
      jb = BOFactory.getJobRequistionBO().getJobRequision(String.valueOf(app.getReqId()));
    }
    EmailNotificationSetting ems = BOFactory.getLovBO().getEmailNotificationSetting(notificationFunction, app.getSuper_user_key());
    if (ems != null)
    {
      if (ems.getIsHiringMgr().equals("A")) {
        isHiringMgr = true;
      }
      if (ems.getIsRecruiter().equals("A")) {
        isRecruiter = true;
      }
      if (ems.getIsWatcher().equals("A")) {
        isWatcher = true;
      }
      if (ems.getIsCurrentOwner().equals("A")) {
        isCurrentOwner = true;
      }
    }
    List cclist = new ArrayList();
    if (jb != null) {
      cclist = getCCListForApplicant(app, jb, isWatcher, isHiringMgr, isRecruiter, isCurrentOwner);
    }
    if (!StringUtils.isNullOrEmpty(Constant.getValue("all.applicant.activities.cc"))) {
      cclist.add(Constant.getValue("all.applicant.activities.cc"));
    }
    if (isUser) {
      cclist.add(user1.getEmailId());
    }
    return cclist;
  }
  
  public List getCCListForApplicant(JobApplicant app, JobRequisition jb, boolean isWatcher, boolean isHiringMgr, boolean isRecruiter, boolean isCurrentOwner)
    throws Exception
  {
    List cclist = new ArrayList();
    if (isWatcher) {
      cclist = BOFactory.getLovBO().getWatchersEmails(jb.getJobreqId(), app.getApplicantId(), "APPLICANT");
    }
    if (isHiringMgr) {
      cclist.add(jb.getHiringmgr().getEmailId());
    }
    if (isCurrentOwner) {
      if ((!StringUtils.isEmpty(app.getIsGroup())) && (app.getIsGroup().equals("Y")))
      {
        long groupId = 0L;
        
        UserGroup usrgrp = BOFactory.getLovBO().getUserGroup(app.getOwnerGroup().getUsergrpId());
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
        User usr = BOFactory.getLovBO().getUserFullNameEmailAndUserIdById(app.getOwner().getUserId());
        if (usr != null) {
          cclist.add(usr.getEmailId());
        }
      }
    }
    if (isRecruiter) {
      if (jb.getRecruiterId() > 0L) {
        if ((!StringUtils.isEmpty(jb.getIsgrouprecruiter())) && (jb.getIsgrouprecruiter().equals("Y")))
        {
          long groupId = 0L;
          
          UserGroup usrgrp = BOFactory.getLovBO().getUserGroup(jb.getRecruiterId());
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
          User usr = BOFactory.getLovBO().getUserFullNameEmailAndUserIdById(jb.getRecruiterId());
          if (usr != null) {
            cclist.add(usr.getEmailId());
          }
        }
      }
    }
    return cclist;
  }
  
  public ApplicantDAO getapplicantdao()
  {
    return this.applicantdao;
  }
  
  public void setapplicantdao(ApplicantDAO applicantdao)
  {
    this.applicantdao = applicantdao;
  }
  
  public ApplicantDAO getApplicantdao()
  {
    return this.applicantdao;
  }
  
  public void setApplicantdao(ApplicantDAO applicantdao)
  {
    this.applicantdao = applicantdao;
  }
  
  public JobRequistionDAO getJobrequisitiondao()
  {
    return this.jobrequisitiondao;
  }
  
  public void setJobrequisitiondao(JobRequistionDAO jobrequisitiondao)
  {
    this.jobrequisitiondao = jobrequisitiondao;
  }
  
  public LovBO getLovbo()
  {
    return this.lovbo;
  }
  
  public void setLovbo(LovBO lovbo)
  {
    this.lovbo = lovbo;
  }
  
  public JobRequistionBO getJobrequistionbo()
  {
    return this.jobrequistionbo;
  }
  
  public void setJobrequistionbo(JobRequistionBO jobrequistionbo)
  {
    this.jobrequistionbo = jobrequistionbo;
  }
  
  public VariablesTXDAO getVariabletxdao()
  {
    return this.variabletxdao;
  }
  
  public void setVariabletxdao(VariablesTXDAO variabletxdao)
  {
    this.variabletxdao = variabletxdao;
  }
  
  public TalentPoolTXDAO getTalentpooltxdao()
  {
    return this.talentpooltxdao;
  }
  
  public void setTalentpooltxdao(TalentPoolTXDAO talentpooltxdao)
  {
    this.talentpooltxdao = talentpooltxdao;
  }
  
  public INineFormBean saveINineFormData(INineFormBean iNineFormBean)
  {
    logger.info("Inside saveINineFormData method");
    return this.applicantdao.saveINineFormData(iNineFormBean);
  }
  
  public INineFormBean updateINineFormData(INineFormBean iNineFormBean)
  {
    logger.info("Inside updateINineFormData method");
    return this.applicantdao.updateINineFormData(iNineFormBean);
  }
  
  public List getApplicantsForBathEmail(String jobId, String[] selecteinterviewstate)
  {
    return this.applicantdao.getApplicantsForBathEmail(jobId, selecteinterviewstate);
  }
  
  public List getApplicantsForBathEmail(String jobId, List<String> selecteinterviewstate)
  {
    return this.applicantdao.getApplicantsForBathEmail(jobId, selecteinterviewstate);
  }
  
  public void updatestatusMultiple(String applicantUserIds, String status)
  {
    this.applicantdao.updatestatusMultiple(applicantUserIds, status);
  }
  
  public void deleteApplicantUserMultiple(String applicantUserIds)
  {
    this.applicantdao.deleteApplicantUserMultiple(applicantUserIds);
  }
}
