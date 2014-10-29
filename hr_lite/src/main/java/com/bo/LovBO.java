package com.bo;

import com.bean.ApplicantReferencee;
import com.bean.Characteristic;
import com.bean.Country;
import com.bean.DeclinedResons;
import com.bean.Department;
import com.bean.Designations;
import com.bean.EmailEvents;
import com.bean.EmailNotificationSetting;
import com.bean.EmailTemplates;
import com.bean.EvaluationTemplate;
import com.bean.Feedbacks;
import com.bean.GroupCharacteristic;
import com.bean.JobApplicant;
import com.bean.JobRequisition;
import com.bean.JobType;
import com.bean.Locale;
import com.bean.Organization;
import com.bean.OrganizationEmailTemplate;
import com.bean.ProjectCodes;
import com.bean.QuestionGroups;
import com.bean.Questions;
import com.bean.RefferalRedemptionRule;
import com.bean.RequistionExpenses;
import com.bean.Role;
import com.bean.State;
import com.bean.Tags;
import com.bean.Timezone;
import com.bean.UrlEncodeData;
import com.bean.User;
import com.bean.UserGroup;
import com.bean.WatchList;
import com.bean.WorkShift;
import com.bean.lov.Education;
import com.bean.lov.EthnicRace;
import com.bean.lov.JobCategory;
import com.bean.lov.Languages;
import com.bean.lov.LicenseType;
import com.bean.lov.LovList;
import com.bean.lov.MembershipType;
import com.bean.lov.TechnicalSkills;
import com.common.ValidationException;
import com.dao.LovDAO;
import com.dao.LovOpsDAO;
import com.form.CreateUserForm;
import com.form.JobRequisitionForm;
import com.form.LovForm;
import com.form.LovListForm;
import com.form.RequistionExpenseForm;
import com.form.UserRegForm;
import com.util.StringUtils;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.log4j.Logger;

public class LovBO
{
  protected static final Logger logger = Logger.getLogger(LovBO.class);
  LovDAO lovdao;
  LovOpsDAO lovopsdao;
  
  public List getAllOrganization(long superUserKey)
  {
    return this.lovdao.getAllOrganization(superUserKey);
  }
  
  public User getUserFullNameEmailAndUserIdById(long userId)
    throws Exception
  {
    return this.lovdao.getUserFullNameEmailAndUserIdById(userId);
  }
  
  public List getLOVListValues(String listcode, long localeId, long superUserKey)
  {
    return this.lovdao.getLOVListValues(listcode, localeId, superUserKey);
  }
  
  public List getAllCurrencies()
  {
    return this.lovdao.getAllCurrencies();
  }
  
  public List getWatchList(long reqid, long applicantId, String type)
  {
    return this.lovdao.getWatchList(reqid, applicantId, type);
  }
  
  public List getWatchersEmails(JobApplicant applicant, String type)
    throws Exception
  {
    return getWatchersEmails(applicant.getApplicantId(), applicant.getReqId(), type);
  }
  
  public RefferalRedemptionRule getRefferalRuleDetails(String id)
  {
    return this.lovopsdao.getRefferalRuleDetails(id);
  }
  
  public RefferalRedemptionRule getRefferalRuleDetails(int creditAfterdays, String critera, long superUserKey)
  {
    return this.lovopsdao.getRefferalRuleDetails(creditAfterdays, critera, superUserKey);
  }
  
  public boolean isWatcherEmailSettingFunctionActive(String functionName, long super_user_key)
  {
    EmailNotificationSetting ems = this.lovdao.getEmailNotificationSetting(functionName, super_user_key);
    if ((ems != null) && (ems.getIsWatcher().equals("A"))) {
      return true;
    }
    return false;
  }
  
  public EmailNotificationSetting getEmailNotificationSetting(String notificationFunction, long super_user_key)
  {
    return this.lovdao.getEmailNotificationSetting(notificationFunction, super_user_key);
  }
  
  public List getWatchersEmails(long reqid, long applicantId, String type)
    throws Exception
  {
    logger.info("inside getWatchListEmails");
    List emailList = new ArrayList();
    List wlist = this.lovdao.getWatchList(reqid, applicantId, type);
    if ((wlist != null) && (wlist.size() > 0)) {
      for (int i = 0; i < wlist.size(); i++)
      {
        WatchList watcher = (WatchList)wlist.get(i);
        if ((watcher.getIsGroup() != null) && (watcher.getIsGroup().equals("Y")))
        {
          UserGroup usrgrp = getUserGroup(watcher.getUserUserGrpId());
          Set users = usrgrp.getUsers();
          if ((users != null) && (users.size() > 0))
          {
            Iterator itr = users.iterator();
            while (itr.hasNext())
            {
              User touser = (User)itr.next();
              emailList.add(touser.getEmailId());
            }
          }
        }
        else
        {
          User usr = getUserFullNameEmailAndUserIdById(watcher.getUserUserGrpId());
          emailList.add(usr.getEmailId());
        }
      }
    }
    return emailList;
  }
  
  public boolean isWatcherExist(long reqid, long applicantId, String type, long userUserGrpId, String isGroup)
  {
    WatchList watcher = this.lovdao.getWatcher(reqid, applicantId, type, userUserGrpId, isGroup);
    if (watcher != null) {
      return true;
    }
    return false;
  }
  
  public void deleteWatcher(List<Long> idList)
  {
    this.lovdao.deleteWatcher(idList);
  }
  
  public void deleteExpenses(List<Long> idList)
  {
    this.lovdao.deleteExpenses(idList);
  }
  
  public void saveValuesForRequistionExpenses(User user, RequistionExpenseForm expform, RequistionExpenses reqExp)
  {
    JobRequisition jb = BOFactory.getJobRequistionBO().getJobRequision(String.valueOf(expform.getJobReqId()));
    reqExp.setCurrencyCode(jb.getOrganization().getCurrencyCode());
    reqExp.setJobRequision(jb);
    
    reqExp = this.lovdao.saveRequistionExpenses(reqExp);
    
    List expensetypes = this.lovdao.getAllExpenseTypesList();
    expform.setExpenseTypeList(expensetypes);
    expform.setCurrencyCode(jb.getOrganization().getCurrencyCode());
    

    expform.setExpensesList(getAllRequistionExpenses(expform.getJobReqId()));
  }
  
  public void setLovValuesForRequistionExpenses(User user, RequistionExpenseForm expform)
  {
    JobRequisition jb = BOFactory.getJobRequistionBO().getJobRequision(String.valueOf(expform.getJobReqId()));
    List expensetypes = this.lovdao.getAllExpenseTypesList();
    expform.setExpenseTypeList(expensetypes);
    expform.setCurrencyCode(jb.getOrganization().getCurrencyCode());
    

    expform.setExpensesList(getAllRequistionExpenses(expform.getJobReqId()));
  }
  
  public List getAllRequistionExpenses(long reqId)
  {
    return this.lovdao.getAllRequistionExpenses(reqId);
  }
  
  public RequistionExpenses saveRequistionExpenses(RequistionExpenses expense)
  {
    return this.lovdao.saveRequistionExpenses(expense);
  }
  
  public WatchList saveWatchList(WatchList watch)
  {
    return this.lovdao.saveWatchList(watch);
  }
  
  public void copyWatchListTemplateToReq(List WatchList, long reqId)
  {
    this.lovdao.copyWatchListTemplateToReq(WatchList, reqId);
  }
  
  public List getDepartmentListByOrg(String orgid)
  {
    return this.lovdao.getDepartmentListByOrg(orgid);
  }
  
  public List getDepartmentListByOrg(List<Long> orgIdsList)
  {
    return this.lovdao.getDepartmentListByOrg(orgIdsList);
  }
  
  public List getProjectCodesByDept(String deptId)
  {
    return this.lovdao.getProjectCodesByDept(deptId);
  }
  
  public List retrieveSalaryPlanByOrganization(String orgId, long superUserKey)
  {
    Organization org = BOFactory.getOrganizationBO().getOrganization(orgId);
    
    return this.lovdao.getAllSalaryPlanDetailsByCurrency(org.getCurrencyCode(), superUserKey);
  }
  
  public List getProjectCodesByDept(List<Long> deptIdsList)
  {
    return this.lovdao.getProjectCodesByDept(deptIdsList);
  }
  
  public List getProjectCodesByOrgAndDept(String orgid, String deptId)
  {
    return this.lovdao.getAllProjectCodeDetailsbyorganizationanddepartment(orgid, deptId);
  }
  
  public List getAllBudgetCodeDetailsbyorganizationanddepartment(String orgid, String departmentid)
  {
    return this.lovdao.getAllBudgetCodeDetailsbyorganizationanddepartment(orgid, departmentid);
  }
  
  public List getAllBudgetCodeDetails(long superUserKey)
  {
    return this.lovdao.getAllBudgetCodeDetails(superUserKey);
  }
  
  public List getAllLocales()
  {
    return this.lovdao.getAllLocales();
  }
  
  public List getAllTimezones()
  {
    return this.lovdao.getAllTimezones();
  }
  
  public List getAllCountries()
  {
    return this.lovdao.getAllCountries();
  }
  
  public List getStateListByCountry(long countryId)
  {
    return this.lovdao.getStateListByCountry(countryId);
  }
  
  public List getTagsList(String tagtype, long superUserKey)
  {
    return this.lovdao.getTagsList(tagtype, superUserKey);
  }
  
  public List getAllProjectCodeDetails()
  {
    return this.lovdao.getAllProjectCodeDetails();
  }
  
  public List getAllOfferCancelResons()
  {
    return this.lovdao.getAllOfferCancelResons();
  }
  
  public UserGroup getUserGroup(long usrgrpid)
  {
    return this.lovdao.getUserGroup(usrgrpid);
  }
  
  public List getAllResumeSourceTypes()
  {
    return this.lovdao.getAllResumeSourceTypes();
  }
  
  public List getAllResumeSource(int typeId)
  {
    return this.lovdao.getAllResumeSource(typeId);
  }
  
  public Tags getTagById(long tagId)
  {
    return this.lovdao.getTagById(tagId);
  }
  
  public List getAllDesignations(long superUserKey)
  {
    return this.lovdao.getAllDesignations(superUserKey);
  }
  
  public List getJobCategoryList(long superUserKey)
  {
    return this.lovdao.getJobCategoryList(superUserKey);
  }
  
  public List getAllDepartments()
  {
    return this.lovdao.getAllDepartments();
  }
  
  public List getAllOfferDeclinedResons()
  {
    return this.lovdao.getAllOfferDeclinedResons();
  }
  
  public UrlEncodeData getEncodeUrl(String encodekey)
  {
    return this.lovdao.getEncodeUrl(encodekey);
  }
  
  public UrlEncodeData getEncodeUrlByUrl(String url)
  {
    return this.lovdao.getEncodeUrlByUrl(url);
  }
  
  public List getAllorganizationemailtemplate(long orgid)
  {
    return this.lovdao.getAllorganizationemailtemplate(orgid);
  }
  
  public List getAllEmailEvents()
  {
    return this.lovdao.getAllEmailEvents();
  }
  
  public OrganizationEmailTemplate getOrganizationEmailTemplateById(long id)
  {
    return this.lovdao.getOrganizationEmailTemplateById(id);
  }
  
  public OrganizationEmailTemplate getOrganizationEmailTemplateBYEventcodeOrgid(String event, long id)
  {
    return this.lovdao.getOrganizationEmailTemplateBYEventcodeOrgid(event, id);
  }
  
  public OrganizationEmailTemplate getOrganizationEmailTemplateBYEventIdOrgId(long eventId, long orgid)
  {
    return this.lovdao.getOrganizationEmailTemplateBYEventIdOrgId(eventId, orgid);
  }
  
  public List getEmailTemplateByCritera(User user, String emailtemplatename, int start, int range)
  {
    return this.lovdao.getEmailTemplateByCritera(user, emailtemplatename, start, range);
  }
  
  public int getCountOfEmailTemplateByCriteria(User user, String emailtemplatename)
  {
    return this.lovdao.getCountOfEmailTemplateByCriteria(user, emailtemplatename);
  }
  
  public List getRegions()
  {
    return this.lovdao.getRegions();
  }
  
  public List getAllCountriesByRegion(long regionId)
  {
    return this.lovdao.getAllCountriesByRegion(regionId);
  }
  
  public List getStateList()
  {
    return this.lovdao.getStateList();
  }
  
  public List getAllRefferalBudgetCode(long superUserKey)
  {
    return this.lovdao.getAllRefferalBudgetCode(superUserKey);
  }
  
  public List getAllRefferalSchemeType(long superUserKey)
  {
    return this.lovdao.getAllRefferalSchemeType(superUserKey);
  }
  
  public List getAllRefferalRules(long superUserKey)
  {
    return this.lovdao.getAllRefferalRules(superUserKey);
  }
  
  public List getAllRefferalRules()
  {
    return this.lovdao.getAllRefferalRules();
  }
  
  public List getAllApplicantRejectionResons()
  {
    return this.lovdao.getAllApplicantRejectionResons();
  }
  
  public Boolean isTagExist(String tagName, long super_user_key)
  {
    return this.lovdao.isTagExist(tagName, super_user_key);
  }
  
  public Boolean isTagExist(long id, String tagName, long super_user_key)
  {
    return this.lovdao.isTagExist(id, tagName, super_user_key);
  }
  
  public Tags getTagsDetails(String id)
  {
    return this.lovdao.getTagsDetails(id);
  }
  
  public Tags updateTags(Tags tag)
  {
    return this.lovdao.updateTags(tag);
  }
  
  public Tags saveTags(Tags tag)
  {
    return this.lovdao.saveTags(tag);
  }
  
  public DeclinedResons getOfferDecliendReson(int id)
  {
    return this.lovdao.getOfferDecliendReson(id);
  }
  
  public List getAllPermissions()
  {
    return this.lovdao.getAllPermissions();
  }
  
  public List getAllRefferalSchemes(String usertype, long superUserKey)
  {
    return this.lovdao.getAllRefferalSchemes(usertype, superUserKey);
  }
  
  public ApplicantReferencee getReferencedetails(String id)
  {
    return this.lovdao.getReferencedetails(id);
  }
  
  public List getReferenceListByApplicantId(long applicantId)
  {
    return this.lovdao.getReferenceListByApplicantId(applicantId);
  }
  
  public QuestionGroups getQuestionGroup(String id)
  {
    return this.lovdao.getQuestionGroup(id);
  }
  
  public List getQuestionanswerListByApplicantRefId(String apprefid)
  {
    return this.lovdao.getQuestionanswerListByApplicantRefId(apprefid);
  }
  
  public EmailTemplates getEmailTemplateDetailsByDefaultComponent(String component)
  {
    return this.lovdao.getEmailTemplateDetailsByDefaultComponent(component);
  }
  
  public Questions getQuestion(String id)
  {
    return this.lovdao.getQuestion(id);
  }
  
  public List getLocationList()
  {
    return this.lovdao.getLocationList();
  }
  
  public List getAllSalaryDetails(User user)
  {
    return this.lovdao.getAllSalaryDetails(user);
  }
  
  public List getAllRefferalSchemeDetails()
  {
    return this.lovdao.getAllRefferalSchemeDetails();
  }
  
  public EmailTemplates saveeEmailTemplate(EmailTemplates emtmpl)
  {
    return this.lovdao.saveeEmailTemplate(emtmpl);
  }
  
  public EmailTemplates getEmailTemplateDetails(String id)
  {
    return this.lovdao.getEmailTemplateDetails(id);
  }
  
  public void deleteEmailTemplateDetails(String id)
  {
    this.lovdao.deleteEmailTemplateDetails(id);
  }
  
  public EmailTemplates updateEmailTemplate(EmailTemplates emtmpl)
  {
    return this.lovdao.updateEmailTemplate(emtmpl);
  }
  
  public EmailTemplates saveEmailTemplate(EmailTemplates emtmpl)
  {
    return this.lovdao.saveEmailTemplate(emtmpl);
  }
  
  public OrganizationEmailTemplate updateOrgEmailTemplate(OrganizationEmailTemplate orgEmailTemplate)
  {
    return this.lovdao.updateOrgEmailTemplate(orgEmailTemplate);
  }
  
  public OrganizationEmailTemplate saveOrgEmailTemplate(OrganizationEmailTemplate orgEmailTemplate)
  {
    return this.lovdao.saveOrgEmailTemplate(orgEmailTemplate);
  }
  
  public EmailEvents getEmailEventsDetails(String id)
  {
    return this.lovdao.getEmailEventsDetails(id);
  }
  
  public void saveanswers(List answerlist)
  {
    this.lovdao.saveanswers(answerlist);
  }
  
  public List getQuestionsByCritera(String questionName, int start, int range)
  {
    return this.lovdao.getQuestionsByCritera(questionName, start, range);
  }
  
  public int getCountOfQuestionsByCriteria(String questionName)
  {
    return this.lovdao.getCountOfQuestionsByCriteria(questionName);
  }
  
  public void vendorcreateLovs(CreateUserForm userform)
  {
    userform.setLocaleList(this.lovdao.getAllLocales());
    userform.setTimezoneList(this.lovdao.getAllTimezones());
    List countryList = this.lovdao.getAllCountries();
    userform.setCountryList(countryList);
    Country ct = (Country)countryList.get(0);
    List stateList = this.lovdao.getStateListByCountry(ct.getCountryId());
    userform.setStateList(stateList);
  }
  
  public void vendoreditLovs(CreateUserForm userform, User usr)
  {
    userform.setLocaleList(this.lovdao.getAllLocales());
    userform.setTimezoneList(this.lovdao.getAllTimezones());
    

    List countryList = this.lovdao.getAllCountries();
    userform.setCountryList(countryList);
    userform.setCountryId(usr.getCountry().getCountryId());
    Country ct = (Country)countryList.get(0);
    List stateList = new ArrayList();
    if (userform.getCountryId() != 0L) {
      stateList = this.lovdao.getStateListByCountry(userform.getCountryId());
    } else {
      stateList = this.lovdao.getStateListByCountry(ct.getCountryId());
    }
    userform.setStateList(stateList);
  }
  
  public void updateMyDetailsLovs(CreateUserForm userform, User usr)
  {
    userform.setLocaleList(this.lovdao.getAllLocales());
    userform.setTimezoneList(this.lovdao.getAllTimezones());
    List countryList = this.lovdao.getAllCountries();
    userform.setCountryList(countryList);
    
    List stateList = new ArrayList();
    if (usr.getNationality().getCountryId() > 0L)
    {
      stateList = this.lovdao.getStateListByCountry(usr.getNationality().getCountryId());
    }
    else
    {
      Country ct = (Country)countryList.get(0);
      stateList = this.lovdao.getStateListByCountry(ct.getCountryId());
    }
    userform.setStateList(stateList);
  }
  
  public void editUsrLovs(CreateUserForm userform, User usr)
  {
    userform.setLocaleList(this.lovdao.getAllLocales());
    userform.setTimezoneList(this.lovdao.getAllTimezones());
    List countryList = this.lovdao.getAllCountries();
    userform.setCountryList(countryList);
    
    List stateList = new ArrayList();
    if (usr.getNationality().getCountryId() > 0L)
    {
      stateList = this.lovdao.getStateListByCountry(usr.getNationality().getCountryId());
    }
    else
    {
      Country ct = (Country)countryList.get(0);
      stateList = this.lovdao.getStateListByCountry(ct.getCountryId());
    }
    userform.setStateList(stateList);
  }
  
  public void saveuserLovs(CreateUserForm userform, User usr)
  {
    userform.setRoleList(this.lovdao.getAllRoles(usr.getSuper_user_key()));
    userform.setDesignationList(this.lovdao.getAllDesignations(usr.getSuper_user_key()));
    
    userform.setLocaleList(this.lovdao.getAllLocales());
    userform.setTimezoneList(this.lovdao.getAllTimezones());
    List countryList = this.lovdao.getAllCountries();
    userform.setCountryList(countryList);
    
    List stateList = this.lovdao.getStateListByCountry(usr.getNationality().getCountryId());
    userform.setStateList(stateList);
    
    List orgList = this.lovdao.getAllOrganization(usr.getSuper_user_key());
    userform.setOrganizationList(orgList);
    List deptlist = new ArrayList();
    userform.setDepartmentList(deptlist);
    if (usr.getOrganization() != null) {
      userform.setDepartmentList(this.lovdao.getDepartmentListByOrg(String.valueOf(usr.getOrganization().getOrgId())));
    }
    List projectcodeList = new ArrayList();
    userform.setProjectcodeList(projectcodeList);
    if (usr.getDepartment() != null)
    {
      projectcodeList = this.lovdao.getProjectCodesByDept(String.valueOf(usr.getDepartment().getDepartmentId()));
      userform.setProjectcodeList(projectcodeList);
    }
  }
  
  public void edituserLovs(CreateUserForm userform, User usr)
  {
    userform.setRoleList(this.lovdao.getAllRoles(usr.getSuper_user_key()));
    userform.setDesignationList(this.lovdao.getAllDesignations(usr.getSuper_user_key()));
    
    List orgList = this.lovdao.getAllOrganization(usr.getSuper_user_key());
    userform.setOrganizationList(orgList);
    List deptlist = new ArrayList();
    userform.setDepartmentList(deptlist);
    userform.setDepartmentList(this.lovdao.getDepartmentListByOrg(String.valueOf(usr.getOrganization().getOrgId())));
    
    List projectcodeList = new ArrayList();
    userform.setProjectcodeList(projectcodeList);
    if (usr.getDepartment() != null)
    {
      projectcodeList = this.lovdao.getProjectCodesByDept(String.valueOf(usr.getDepartment().getDepartmentId()));
      userform.setProjectcodeList(projectcodeList);
    }
    if (usr.getProjectcode() != null)
    {
      String procId = String.valueOf(usr.getProjectcode().getProjectId());
      userform.setProjectId(Integer.parseInt(procId));
    }
    userform.setLocaleList(this.lovdao.getAllLocales());
    userform.setTimezoneList(this.lovdao.getAllTimezones());
    List countryList = this.lovdao.getAllCountries();
    userform.setCountryList(countryList);
    if (usr.getNationality() != null)
    {
      List stateList = this.lovdao.getStateListByCountry(usr.getNationality().getCountryId());
      userform.setStateList(stateList);
    }
    else
    {
      Country ct = (Country)countryList.get(0);
      List stateList = this.lovdao.getStateListByCountry(ct.getCountryId());
      userform.setStateList(stateList);
    }
  }
  
  public void callListFormSet(LovForm lovForm, User user)
  {
    List orgList = this.lovdao.getAllOrganization(user.getSuper_user_key());
    lovForm.setOrgnizationList(orgList);
    
    List deptlist = new ArrayList();
    if (lovForm.getOrgId() > 0L)
    {
      deptlist = this.lovdao.getDepartmentListByOrg(String.valueOf(lovForm.getOrgId()));
      lovForm.setDepartmentList(deptlist);
    }
    else
    {
      lovForm.setDepartmentList(deptlist);
    }
  }
  
  public void createUserLovs(CreateUserForm userform, User user1)
  {
    userform.setRoleList(this.lovdao.getAllRoles(user1.getSuper_user_key()));
    userform.setDesignationList(this.lovdao.getAllDesignations(user1.getSuper_user_key()));
    
    List orgList = this.lovdao.getAllOrganization(user1.getSuper_user_key());
    userform.setOrganizationList(orgList);
    if (orgList.size() > 0)
    {
      Organization org = (Organization)orgList.get(0);
      userform.setOrgId(org.getOrgId());
    }
    List deptlist = new ArrayList();
    userform.setDepartmentList(deptlist);
    if (orgList.size() > 0)
    {
      Organization org = (Organization)orgList.get(0);
      deptlist = this.lovdao.getDepartmentListByOrg(String.valueOf(org.getOrgId()));
      userform.setDepartmentList(deptlist);
    }
    if (deptlist.size() > 0)
    {
      Department dept = (Department)deptlist.get(0);
      userform.setDepartmentId(dept.getDepartmentId());
    }
    List projectcodeList = new ArrayList();
    userform.setProjectcodeList(projectcodeList);
    if ((orgList.size() > 0) && (deptlist.size() > 0))
    {
      Organization org = (Organization)orgList.get(0);
      Department dept = (Department)deptlist.get(0);
      
      projectcodeList = getProjectCodesByOrgAndDept(String.valueOf(org.getOrgId()), String.valueOf(dept.getDepartmentId()));
      userform.setProjectcodeList(projectcodeList);
    }
    userform.setLocaleList(this.lovdao.getAllLocales());
    userform.setTimezoneList(this.lovdao.getAllTimezones());
    List countryList = this.lovdao.getAllCountries();
    userform.setCountryList(countryList);
    Country ct = (Country)countryList.get(0);
    List stateList = this.lovdao.getStateListByCountry(ct.getCountryId());
    userform.setStateList(stateList);
  }
  
  public void regUserLovs(UserRegForm userform)
  {
    userform.setLocaleList(this.lovdao.getAllLocales());
    userform.setTimezoneList(this.lovdao.getAllTimezones());
    List countryList = this.lovdao.getAllCountries();
    userform.setCountryList(countryList);
  }
  
  public void createLovs(LovListForm lovlistform)
  {
    lovlistform.setLocaleList(this.lovdao.getAllLocales());
  }
  
  public void editLovs(LovListForm lovlistform)
  {
    lovlistform.setLocaleList(this.lovdao.getAllLocales());
  }
  
  public LovList getLovDetails(LovListForm lovlistform, long lovid)
  {
    LovList lovList = null;
    lovList = this.lovdao.getLovDetails(lovid);
    lovlistform.setLocaleList(this.lovdao.getAllLocales());
    return lovList;
  }
  
  public List getAllEmailNotificationFunctionList(User user)
  {
    return this.lovdao.getAllEmailNotificationFunctionList(user);
  }
  
  public EmailNotificationSetting getEmailNotificationSettingDetails(String weid, User user)
  {
    return this.lovdao.getEmailNotificationSettingDetails(weid, user);
  }
  
  public void updateEmailNotificationStatus(EmailNotificationSetting emailNotificationSetting)
  {
    this.lovdao.updateEmailNotificationStatus(emailNotificationSetting);
  }
  
  public Locale getLocaleName(long localeId)
  {
    Locale locale = null;
    locale = this.lovdao.getLocaleName(localeId);
    return locale;
  }
  
  public List getLovDetailsForPagination(User user, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.lovdao.getLovDetailsForPagination(user, pageSize, startIndex, dir_str, sort_str);
  }
  
  public List getAllLovDetails(User user)
  {
    return this.lovdao.getAllLovDetails(user);
  }
  
  public List getAllJobCategoryDetailsForPagination(User user, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.lovdao.getAllJobCategoryDetailsForPagination(user, pageSize, startIndex, dir_str, sort_str);
  }
  
  public List getAllJobCategoryDetails(User user)
  {
    return this.lovdao.getAllJobCategoryDetails(user);
  }
  
  public JobCategory getJobCategoryDetails(long jobCategoryId)
  {
    return this.lovdao.getJobCategoryDetails(jobCategoryId);
  }
  
  public JobCategory updateJobCategoryDetails(JobCategory jobCategory)
  {
    return this.lovdao.updateJobCategoryDetails(jobCategory);
  }
  
  public JobCategory saveJobCategoryDetails(JobCategory jobCategory)
  {
    return this.lovdao.saveJobCategoryDetails(jobCategory);
  }
  
  public JobCategory deleteJobCategoryDetails(JobCategory jobCategory)
  {
    return this.lovdao.deleteJobCategoryDetails(jobCategory);
  }
  
  public MembershipType getMembershipTypeDetails(long membershipTypeId)
  {
    return this.lovdao.getMembershipTypeDetails(membershipTypeId);
  }
  
  public MembershipType saveMembershipTypeDetails(MembershipType membershipType)
  {
    return this.lovdao.saveMembershipTypeDetails(membershipType);
  }
  
  public MembershipType updateMembershipTypeDetails(MembershipType membershipType)
  {
    return this.lovdao.updateMembershipTypeDetails(membershipType);
  }
  
  public MembershipType deleteMembershipTypeDetails(MembershipType membershipType)
  {
    return this.lovdao.deleteMembershipTypeDetails(membershipType);
  }
  
  public List getAllMembershipTypeDetailsForPagination(User user1, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.lovdao.getAllMembershipTypeDetailsForPagination(user1, pageSize, startIndex, dir_str, sort_str);
  }
  
  public List getAllMembershipTypeDetails(User user1)
  {
    return this.lovdao.getAllMembershipTypeDetails(user1);
  }
  
  public List getLovlistvaluecodeList(String lovListCode)
  {
    return this.lovdao.getLovlistvaluecodeList(lovListCode);
  }
  
  public void jobsearchAgencyLov(JobRequisitionForm jbForm, User agency)
  {
    List locationlist = this.lovdao.getLocationList();
    jbForm.setLocationList(locationlist);
    List jobtypeList = this.lovdao.getJobTypeList(agency.getSuper_user_key());
    jbForm.setJobtypeList(jobtypeList);
    List orgList = this.lovdao.getAllOrganization(agency.getSuper_user_key());
    jbForm.setOrganizationList(orgList);
    if (jbForm.getParentOrgId() != 0L)
    {
      List deptList = this.lovdao.getDepartmentListByOrg(String.valueOf(jbForm.getParentOrgId()));
      jbForm.setDepartmentList(deptList);
    }
    else if (orgList.size() > 0)
    {
      Organization org = (Organization)orgList.get(0);
      List deptList = this.lovdao.getDepartmentListByOrg(String.valueOf(org.getOrgId()));
      jbForm.setDepartmentList(deptList);
    }
    List jobgradeList = this.lovdao.getJobGradeList();
    jbForm.setJobgradeList(jobgradeList);
  }
  
  public void searchreqtemplates(LovForm lovForm, User user, String results, int start1, int range1)
    throws Exception
  {
    int totaluser = 0;
    if (StringUtils.isNullOrEmpty(results)) {
      totaluser = this.lovopsdao.getReqTemplatesByCriteraCount(user, lovForm.getCriteria(), lovForm.getOrgId(), lovForm.getDepartmentId(), lovForm.getProjectId(), lovForm.getBudgetId(), lovForm.getStatus());
    } else {
      totaluser = new Integer(results).intValue();
    }
    List reqtmplList = this.lovopsdao.getRequisitionTemplatesByCritera(user, lovForm.getCriteria(), lovForm.getOrgId(), lovForm.getDepartmentId(), lovForm.getProjectId(), lovForm.getBudgetId(), lovForm.getStatus(), start1, range1);
    


    searchreqtemplatesLovs(lovForm, user);
    
    lovForm.setReqtemplateList(reqtmplList);
    lovForm.setStart(String.valueOf(start1));
    lovForm.setRange(String.valueOf(range1));
    lovForm.setResults(String.valueOf(totaluser));
    lovForm.setCriteria(lovForm.getCriteria());
  }
  
  public void searchreqtemplatesLovs(LovForm lovForm, User user)
    throws Exception
  {
    List orgList = this.lovdao.getAllOrganization(user.getSuper_user_key());
    lovForm.setOrgnizationList(orgList);
    
    List deptlist = new ArrayList();
    lovForm.setDepartmentList(deptlist);
    List projectcodeList = new ArrayList();
    lovForm.setProjectcodeList(projectcodeList);
    if (lovForm.getOrgId() != 0L)
    {
      lovForm.setDepartmentList(this.lovdao.getDepartmentListByOrg(String.valueOf(lovForm.getOrgId())));
    }
    else if (orgList.size() > 0)
    {
      Organization org = (Organization)orgList.get(0);
      lovForm.setDepartmentList(this.lovdao.getDepartmentListByOrg(String.valueOf(org.getOrgId())));
    }
    if (lovForm.getDepartmentList().size() > 0)
    {
      Department dept = (Department)lovForm.getDepartmentList().get(0);
      projectcodeList = BOFactory.getLovTXBO().getProjectCodesByDept(String.valueOf(dept.getDepartmentId()));
      lovForm.setProjectcodeList(projectcodeList);
    }
    List locationList = this.lovdao.getLocationList();
    lovForm.setLocationList(locationList);
    

    List budgetcodeList = this.lovdao.getAllBudgetCodeDetails(user.getSuper_user_key());
    lovForm.setBudgetCodeList(budgetcodeList);
  }
  
  public void requisitionselector(LovForm lovForm, User user)
  {
    List orgList = this.lovdao.getAllOrganization(user.getSuper_user_key());
    lovForm.setOrgnizationList(orgList);
    List deptlist = new ArrayList();
    lovForm.setDepartmentList(deptlist);
    if (lovForm.getOrgId() > 0L)
    {
      lovForm.setDepartmentList(this.lovdao.getDepartmentListByOrg(String.valueOf(lovForm.getOrgId())));
    }
    else if (orgList.size() > 0)
    {
      Organization org = (Organization)orgList.get(0);
      lovForm.setDepartmentList(this.lovdao.getDepartmentListByOrg(String.valueOf(org.getOrgId())));
    }
    List locationList = this.lovdao.getLocationList();
    lovForm.setLocationList(locationList);
  }
  
  public void reqtmplselector(LovForm lovForm, User user)
    throws Exception
  {
    List orgList = this.lovdao.getAllOrganization(user.getSuper_user_key());
    lovForm.setOrgnizationList(orgList);
    
    List deptlist = new ArrayList();
    lovForm.setDepartmentList(deptlist);
    List projectcodeList = new ArrayList();
    lovForm.setProjectcodeList(projectcodeList);
    if (orgList.size() > 0)
    {
      Organization org = (Organization)orgList.get(0);
      lovForm.setDepartmentList(this.lovdao.getDepartmentListByOrg(String.valueOf(org.getOrgId())));
      if (lovForm.getDepartmentList().size() > 0)
      {
        Department dept = (Department)lovForm.getDepartmentList().get(0);
        projectcodeList = BOFactory.getLovTXBO().getProjectCodesByDept(String.valueOf(dept.getDepartmentId()));
        lovForm.setProjectcodeList(projectcodeList);
      }
    }
    List locationList = this.lovdao.getLocationList();
    lovForm.setLocationList(locationList);
    


    List budgetcodeList = this.lovdao.getAllBudgetCodeDetails(user.getSuper_user_key());
    
    lovForm.setBudgetCodeList(budgetcodeList);
  }
  
  public Designations getDesignation(long id)
  {
    return this.lovdao.getDesignation(id);
  }
  
  public Designations getDesignationByCode(String code)
  {
    return this.lovdao.getDesignationByCode(code);
  }
  
  public Locale getLocaleByCode(String code)
  {
    return this.lovdao.getLocaleByCode(code);
  }
  
  public void saveTimeZone(Timezone tz)
  {
    this.lovdao.saveTimeZone(tz);
  }
  
  public Timezone getTimeZoneByCode(String code)
  {
    return this.lovdao.getTimeZoneByCode(code);
  }
  
  public State getStateByStateIdAndCountryId(long stateid, long countryId)
  {
    return this.lovdao.getStateByStateIdAndCountryId(stateid, countryId);
  }
  
  public Country getCountry(long id)
  {
    return this.lovdao.getCountry(id);
  }
  
  public State getState(long id)
  {
    return this.lovdao.getState(id);
  }
  
  public Country getCountryByName(String name)
  {
    return this.lovdao.getCountryByName(name);
  }
  
  public List getRoundListForPagination(int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.lovdao.getRoundListForPagination(pageSize, startIndex, dir_str, sort_str);
  }
  
  public List getAllRoundList()
  {
    return this.lovdao.getAllRoundList();
  }
  
  public List getAllJobGradeDetailsForPagination(int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.lovdao.getAllJobGradeDetailsForPagination(pageSize, startIndex, dir_str, sort_str);
  }
  
  public List getAllJobGradeDetails(long superUserKey)
  {
    return this.lovdao.getAllJobGradeDetails(superUserKey);
  }
  
  public List getAllCategoriesDetails(long superUserKey)
  {
    return this.lovdao.getAllCategoriesDetails(superUserKey);
  }
  
  public List getAllJobGradeDetailsForPaginationByJobgradeName(User user, String name, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.lovdao.getAllJobGradeDetailsForPaginationByJobgradeName(user, name, pageSize, startIndex, dir_str, sort_str);
  }
  
  public List getAllJobGradeDetailsByJoggradeName(User user, String name)
  {
    return this.lovdao.getAllJobGradeDetailsByJoggradeName(user, name);
  }
  
  public List getAllJobCodeDetailsForPagination(int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.lovdao.getAllJobCodeDetailsForPagination(pageSize, startIndex, dir_str, sort_str);
  }
  
  public List getAllJobCodeDetails()
  {
    return this.lovdao.getAllJobCodeDetails();
  }
  
  public List getJobCodeDetails(String query)
  {
    List<String> jobCodeList = this.lovdao.getJobCodeDetails(query);
    return jobCodeList;
  }
  
  public List getJobtitle(String query)
  {
    List<String> jobtitleList = this.lovdao.getJobtitle(query);
    return jobtitleList;
  }
  
  public List getJobtitlerefferal(String query)
  {
    List<String> jobtitleList = this.lovdao.getJobtitleforrefferal(query);
    return jobtitleList;
  }
  
  public List getOrganizationTypeList()
  {
    return this.lovdao.getOrganizationTypeList();
  }
  
  public String getRejectedReasonName(long applicantId)
  {
    return this.lovdao.getRejectedReasonName(applicantId);
  }
  
  public List getAllCountriesList()
  {
    return this.lovdao.getAllCountries();
  }
  
  public List getAllStatesBasedByCountry(long countryId)
  {
    return this.lovdao.getStateListByCountry(countryId);
  }
  
  public List getJobTypeList(long superUserKey)
  {
    return this.lovdao.getJobTypeList(superUserKey);
  }
  
  public List getMembershipTypesList()
  {
    return this.lovdao.getMembershipTypesList();
  }
  
  public List getJobGradeList()
  {
    return this.lovdao.getJobGradeList();
  }
  
  public List getWorkShiftList()
  {
    return this.lovdao.getWorkShiftList();
  }
  
  public List getFlsaStatusList()
  {
    return this.lovdao.getFlsaStatusList();
  }
  
  public List getCompFrequencyList()
  {
    return this.lovdao.getCompFrequencyList();
  }
  
  public List getEmailTempltesForPagination(User user, String emailtemplatename, String emailSubject, String createdBy, String dir_str, String sort_str, int pageSize, int startIndex)
  {
    return this.lovdao.getEmailTempltesForPagination(user, emailtemplatename, emailSubject, createdBy, dir_str, sort_str, pageSize, startIndex);
  }
  
  public int getCountOfAllEmailTemplates(User user, String emailtemplatename, String emailSubject, String createdBy)
  {
    return this.lovdao.getCountOfAllEmailTemplates(user, emailtemplatename, emailSubject, createdBy);
  }
  
  public List getEmailTempltesForPagination(int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.lovdao.getEmailTempltesForPagination(pageSize, startIndex, dir_str, sort_str);
  }
  
  public int getCountOfAllEmailTemplates()
  {
    return this.lovdao.getCountOfAllEmailTemplates();
  }
  
  public List getEvaluationTmplListForPagination(int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.lovdao.getEvaluationTmplListForPagination(pageSize, startIndex, dir_str, sort_str);
  }
  
  public int getCountOfAllEvaluationTemplates()
  {
    return this.lovdao.getCountOfAllEvaluationTemplates();
  }
  
  public List getAllLanguages()
  {
    return this.lovdao.getAllLanguages();
  }
  
  public List getAllLicense()
  {
    return this.lovdao.getAllLicense();
  }
  
  public List getAllJobTypeForPagination(User user, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.lovdao.getAllJobTypeForPagination(user, pageSize, startIndex, dir_str, sort_str);
  }
  
  public List getAllJobTypeDetails(User user)
  {
    return this.lovdao.getAllJobTypeDetails(user);
  }
  
  public JobType saveJobTypeDetails(JobType jobType)
  {
    return this.lovdao.saveJobTypeDetails(jobType);
  }
  
  public JobType updateJobTypeDetails(JobType jobType)
  {
    return this.lovdao.updateJobTypeDetails(jobType);
  }
  
  public JobType getJobTypeDetails(long jobTypeId)
  {
    return this.lovdao.getJobTypeDetails(jobTypeId);
  }
  
  public List getAllWorkShiftForPagination(User user, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.lovdao.getAllWorkShiftForPagination(user, pageSize, startIndex, dir_str, sort_str);
  }
  
  public List getAllWorkShiftDetails(User user)
  {
    return this.lovdao.getAllWorkShiftDetails(user);
  }
  
  public WorkShift getWorkShiftDetails(long shiftId)
  {
    return this.lovdao.getWorkShiftDetails(shiftId);
  }
  
  public WorkShift saveWorkShift(WorkShift workShift)
  {
    return this.lovdao.saveWorkShift(workShift);
  }
  
  public WorkShift updateWorkShift(WorkShift workShift)
  {
    return this.lovdao.updateWorkShift(workShift);
  }
  
  public List getAllLanguagesForPagination(User user, int pageSize, int startIndex, String dir_str, String sort_str, String languageName)
  {
    return this.lovdao.getAllLanguagesForPagination(user, pageSize, startIndex, dir_str, sort_str, languageName);
  }
  
  public List getAllLanguagesDetails(User user, String languageName)
  {
    return this.lovdao.getAllLanguagesDetails(user, languageName);
  }
  
  public Languages getLanguagesDetails(long languageId)
  {
    return this.lovdao.getLanguagesDetails(languageId);
  }
  
  public Languages saveLanguage(Languages languages)
  {
    return this.lovdao.saveLanguage(languages);
  }
  
  public Languages updateLanguage(Languages languages)
  {
    return this.lovdao.updateLanguage(languages);
  }
  
  public Languages deleteLanguage(Languages languages)
  {
    return this.lovdao.deleteLanguage(languages);
  }
  
  public List getAllLicenseTypeForPagination(User user, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.lovdao.getAllLicenseTypeForPagination(user, pageSize, startIndex, dir_str, sort_str);
  }
  
  public List getAllLicenseTypeDetails(User user)
  {
    return this.lovdao.getAllLicenseTypeDetails(user);
  }
  
  public LicenseType getLicenseTypeDetails(long licenseTypeId)
  {
    return this.lovdao.getLicenseTypeDetails(licenseTypeId);
  }
  
  public LicenseType saveLicenseType(LicenseType licenseType)
  {
    return this.lovdao.saveLicenseType(licenseType);
  }
  
  public LicenseType updateLicenseType(LicenseType licenseType)
  {
    return this.lovdao.updateLicenseType(licenseType);
  }
  
  public List getAllEthnicRaceForPagination(User user, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.lovdao.getAllEthnicRaceForPagination(user, pageSize, startIndex, dir_str, sort_str);
  }
  
  public List getAllEthnicRaceDetails(User user)
  {
    return this.lovdao.getAllEthnicRaceDetails(user);
  }
  
  public EthnicRace getEthnicRaceDetails(long ethnicRaceId)
  {
    return this.lovdao.getEthnicRaceDetails(ethnicRaceId);
  }
  
  public EthnicRace saveEthnicRaceDetails(EthnicRace ethnicRace)
  {
    return this.lovdao.saveEthnicRaceDetails(ethnicRace);
  }
  
  public EthnicRace updateEthnicRaceDetails(EthnicRace ethnicRace)
  {
    return this.lovdao.updateEthnicRaceDetails(ethnicRace);
  }
  
  public EthnicRace deleteEthnicRaceDetails(EthnicRace ethnicRace)
  {
    return this.lovdao.deleteEthnicRaceDetails(ethnicRace);
  }
  
  public List getAllEthnicRaceList()
  {
    return this.lovdao.getAllEthnicRaceList();
  }
  
  public List getAllVeteranDisabilityList()
  {
    return this.lovdao.getAllVeteranDisabilityList();
  }
  
  public List getAllTechnicalSkillDetailsForPagination(User user, int pageSize, int startIndex, String dir_str, String sort_str, String techSkillName)
  {
    return this.lovdao.getAllTechnicalSkillDetailsForPagination(user, pageSize, startIndex, dir_str, sort_str, techSkillName);
  }
  
  public List getAllTechnicalSkillDetails(User user, String techSkillName)
  {
    return this.lovdao.getAllTechnicalSkillDetails(user, techSkillName);
  }
  
  public TechnicalSkills getTechnicalSkillsDetails(long technialSkillId)
  {
    return this.lovdao.getTechnicalSkillsDetails(technialSkillId);
  }
  
  public TechnicalSkills saveTechnicalSkillsDetails(TechnicalSkills technicalSkills)
  {
    return this.lovdao.saveTechnicalSkillsDetails(technicalSkills);
  }
  
  public TechnicalSkills updateTechnicalSkillsDetails(TechnicalSkills technicalSkills)
  {
    return this.lovdao.updateTechnicalSkillsDetails(technicalSkills);
  }
  
  public TechnicalSkills deleteTechnicalSkillsDetails(TechnicalSkills technicalSkills)
  {
    return this.lovdao.deleteTechnicalSkillsDetails(technicalSkills);
  }
  
  public List getAllEducationDetailsForPagination(User user, int pageSize, int startIndex, String dir_str, String sort_str, String educationName)
  {
    return this.lovdao.getAllEducationDetailsForPagination(user, pageSize, startIndex, dir_str, sort_str, educationName);
  }
  
  public List getAllEducationDetails(User user, String educationName)
  {
    return this.lovdao.getAllEducationDetails(user, educationName);
  }
  
  public Education getEducationDetails(long educationId)
  {
    return this.lovdao.getEducationDetails(educationId);
  }
  
  public Education saveEducation(Education education)
  {
    return this.lovdao.saveEducation(education);
  }
  
  public Education updateEducation(Education education)
  {
    return this.lovdao.updateEducation(education);
  }
  
  public Education deleteEducation(Education education)
  {
    return this.lovdao.deleteEducation(education);
  }
  
  public Role getRoleByRoleCode(User user, String rolecode)
  {
    return this.lovdao.getRoleByRoleCode(user, rolecode);
  }
  
  public Role getRoleByRoleCode(long super_user_key, String rolecode)
  {
    return this.lovdao.getRoleByRoleCode(super_user_key, rolecode);
  }
  
  public void deleteLocation(long locationId)
    throws ValidationException, Exception
  {
    this.lovdao.deleteLocation(locationId);
  }
  
  public void deleteDesignation(long designationId)
    throws ValidationException, Exception
  {
    this.lovdao.deleteDesignation(designationId);
  }
  
  public void deleteJobCategory(long jobCategoryId)
    throws ValidationException, Exception
  {
    this.lovdao.deleteJobCategory(jobCategoryId);
  }
  
  public void deleteCategory(long catId)
    throws ValidationException, Exception
  {
    this.lovdao.deleteCategory(catId);
  }
  
  public void deleteJobType(long jobTypeId)
    throws ValidationException, Exception
  {
    this.lovdao.deleteJobType(jobTypeId);
  }
  
  public void deleteJobGrade(long jobgradeId)
    throws ValidationException, Exception
  {
    this.lovdao.deleteJobGrade(jobgradeId);
  }
  
  public void deleteWorkShift(long shiftId)
    throws ValidationException, Exception
  {
    this.lovdao.deleteWorkShift(shiftId);
  }
  
  public void deleteSalaryPlan(long salaryplanId)
    throws ValidationException, Exception
  {
    this.lovdao.deleteSalaryPlan(salaryplanId);
  }
  
  public void deleteBudgetCode(long budgetId)
    throws ValidationException, Exception
  {
    this.lovdao.deleteBudgetCode(budgetId);
  }
  
  public void deleteReferralBudgetCode(long ref_budgetId)
    throws ValidationException, Exception
  {
    this.lovdao.deleteReferralBudgetCode(ref_budgetId);
  }
  
  public void deleteReferralScheme(long refferalScheme_Id)
    throws ValidationException, Exception
  {
    this.lovdao.deleteReferralScheme(refferalScheme_Id);
  }
  
  public void deleteOnBoardingTaskDefinition(long taskdefid)
    throws ValidationException, Exception
  {
    this.lovdao.deleteOnBoardingTaskDefinition(taskdefid);
  }
  
  public void deleteOnBoardingTemplate(long templateid)
    throws ValidationException, Exception
  {
    this.lovdao.deleteOnBoardingTemplate(templateid);
  }
  
  public void deleteApplicantFilter(long businessCriteraId)
    throws ValidationException, Exception
  {
    this.lovdao.deleteApplicantFilter(businessCriteraId);
  }
  
  public void deleteMemberShipType(long membershipTypeId)
    throws ValidationException, Exception
  {
    this.lovdao.deleteMemberShipType(membershipTypeId);
  }
  
  public void deleteLanguage(long languageId)
    throws ValidationException, Exception
  {
    this.lovdao.deleteLanguage(languageId);
  }
  
  public void deleteLicenseType(long licenseTypeId)
    throws ValidationException, Exception
  {
    this.lovdao.deleteLicenseType(licenseTypeId);
  }
  
  public void deleteEthnicRace(long ethnicRaceId)
    throws ValidationException, Exception
  {
    this.lovdao.deleteEthnicRace(ethnicRaceId);
  }
  
  public List getAllDeclinedReasonsDetailsForPagination(User user, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.lovdao.getAllDeclinedReasonsDetailsForPagination(user, pageSize, startIndex, dir_str, sort_str);
  }
  
  public List getAllDeclinedReasonsDetails(User user)
  {
    return this.lovdao.getAllDeclinedReasonsDetails(user);
  }
  
  public DeclinedResons saveDeclinedReasons(DeclinedResons declinedResons)
  {
    return this.lovdao.saveDeclinedReasons(declinedResons);
  }
  
  public DeclinedResons getDeclinedReasons(int offerdeclinedreasonId)
  {
    return this.lovdao.getDeclinedReasons(offerdeclinedreasonId);
  }
  
  public DeclinedResons updateDeclinedReasons(DeclinedResons declinedResons)
  {
    return this.lovdao.updateDeclinedReasons(declinedResons);
  }
  
  public Feedbacks saveFeedbacks(Feedbacks feedbacks)
  {
    return this.lovdao.saveFeedbacks(feedbacks);
  }
  
  public String createEvaluationTemplateTable(EvaluationTemplate evtmpl)
  {
    StringBuffer bfr = new StringBuffer();
    bfr.append("<b>Evaluation Sheet</b>");
    bfr.append("\n");
    
    List groupcharList = evtmpl.getCharGroupList();
    if (groupcharList != null)
    {
      System.out.println("groupcharList" + groupcharList.size());
      for (int i = 0; i < groupcharList.size(); i++)
      {
        GroupCharacteristic grpcharacteristic = (GroupCharacteristic)groupcharList.get(i);
        
        List charList = grpcharacteristic.getCharList();
        if ((charList != null) && (charList.size() > 0))
        {
          bfr.append("<table border=0 width=100%>");
          bfr.append("\n");
          bfr.append("<tr>");
          bfr.append("\n");
          bfr.append("<td bgcolor=gray width=60%>");
          bfr.append(grpcharacteristic.getGroupCharName());
          bfr.append("</td>");
          bfr.append("<td bgcolor=gray width=25%>minimum weight</td><td bgcolor=gray width=35%>evaluated weight</td>");
          bfr.append("\n");
          bfr.append("</tr>");
          bfr.append("\n");
          bfr.append("</table>");
          bfr.append("\n");
        }
        if (charList != null) {
          for (int j = 0; j < charList.size(); j++)
          {
            Characteristic characteristic = (Characteristic)charList.get(j);
            
            bfr.append("<table border=1 width=100%>");
            bfr.append("\n");
            bfr.append("<tr>");
            bfr.append("\n");
            bfr.append("<td width=60%>");
            bfr.append(characteristic.getCharName());
            bfr.append("</td>");
            bfr.append("<td width=25%>");
            bfr.append(characteristic.getWeight());
            bfr.append("</td>");
            bfr.append("<td width=35%>0</td>");
            bfr.append("\n");
            bfr.append("</tr>");
            bfr.append("\n");
            bfr.append("</table>");
            bfr.append("\n");
          }
        }
      }
    }
    return bfr.toString();
  }
  
  public LovDAO getlovdao()
  {
    return this.lovdao;
  }
  
  public void setlovdao(LovDAO lovdao)
  {
    this.lovdao = lovdao;
  }
  
  public LovDAO getLovdao()
  {
    return this.lovdao;
  }
  
  public void setLovdao(LovDAO lovdao)
  {
    this.lovdao = lovdao;
  }
  
  public LovOpsDAO getLovopsdao()
  {
    return this.lovopsdao;
  }
  
  public void setLovopsdao(LovOpsDAO lovopsdao)
  {
    this.lovopsdao = lovopsdao;
  }
}
