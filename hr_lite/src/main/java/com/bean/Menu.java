package com.bean;

import com.bo.BOFactory;
import com.bo.UserBO;
import com.util.PermissionChecker;
import java.io.Serializable;

public class Menu
  implements Serializable
{
  private boolean isOrganizations;
  private boolean isOrganizationHeader;
  private boolean isProjectCodes;
  private boolean isLocations;
  private boolean isJobTitle;
  private boolean isRererral;
  private boolean isRererralBudgetCode;
  private boolean isRererralScheme;
  private boolean isRererralSchemeType;
  private boolean isSecurity;
  private boolean isUser;
  private boolean isRoles;
  private boolean isJobHeader;
  private boolean isSalaryPlan;
  private boolean isBudgetCode;
  private boolean isJobGrade;
  private boolean isJobCode;
  private boolean isNotificationHeader;
  private boolean isCompetencyHeader;
  private boolean isCompetenciesQuestions;
  private boolean isAdmin;
  private boolean isRequistionMenu;
  private boolean isAllRequisitions;
  private boolean isAllRequisitionsRead;
  private boolean isReqTemplates;
  private boolean isOwnOrganizationRequistions;
  private boolean isApplicantHeader;
  private boolean isAllApplicants;
  private boolean isBulkUploadApplicants;
  private boolean isTalentPoolCrud;
  private boolean isEmailCampaignCrud;
  private boolean isMassEmail;
  private boolean isSystemRule;
  private boolean isRegisterUser;
  private boolean isLovs;
  private boolean isRedemption;
  private boolean isAgencyList;
  private boolean isAgencyRedemption;
  private boolean isReferralRedemption;
  private boolean isAllPendingTasks;
  private boolean isAllExportUploadTasks;
  private boolean isOnBoardMgmt;
  private boolean isApplicantUsers;
  private boolean isNew;
  private boolean isRequisitionCreate;
  private boolean isApplicantCreate;
  private boolean isRequisitionTemplateCreate;
  private boolean isConfigurations;
  private boolean isReports;
  private boolean isCalendarAccess;
  
  public boolean isApplicantUsers()
  {
    return this.isApplicantUsers;
  }
  
  public void setApplicantUsers(boolean isApplicantUsers)
  {
    this.isApplicantUsers = isApplicantUsers;
  }
  
  private void setATaskMenu(User user)
  {
    if (PermissionChecker.isPermissionApplied("ALL_PENDING_TASKS", user)) {
      setAllPendingTasks(true);
    }
    if (PermissionChecker.isPermissionApplied("ALL_EXPORT_UPLOAD_TASK", user)) {
      setAllExportUploadTasks(true);
    }
  }
  
  private void setReportMenu(User user)
  {
    if (PermissionChecker.isPermissionApplied("REPORTS_VIEW", user)) {
      setReports(true);
    }
  }
  
  private void setRedemptionMgmtMenu(User user)
  {
    if (PermissionChecker.isPermissionApplied("AGENCY_CRUD", user)) {
      setAgencyList(true);
    }
    if (PermissionChecker.isPermissionApplied("AGENCY_REDEMPTIONS", user)) {
      setAgencyRedemption(true);
    }
    if (PermissionChecker.isPermissionApplied("REFERRAL_REDEMPTIONS", user)) {
      setReferralRedemption(true);
    }
    if ((isAgencyRedemption()) || (isReferralRedemption())) {
      setRedemption(true);
    }
  }
  
  private void setUpAquistionsMenu(User user)
  {
    if (PermissionChecker.isPermissionApplied("ALL_APPLICANTS", user)) {
      setAllApplicants(true);
    }
    if (PermissionChecker.isPermissionApplied("BULK_UPLOAD_APPLICANTS", user)) {
      setBulkUploadApplicants(true);
    }
    if (PermissionChecker.isPermissionApplied("MANAGE_APPLICANT_USER", user)) {
      setApplicantUsers(true);
    }
    if (PermissionChecker.isPermissionApplied("ON_BOARD_MANAGEMENT", user)) {
      setOnBoardMgmt(true);
    }
    if ((isAllApplicants()) || (isApplicantUsers()) || (isOnBoardMgmt())) {
      setApplicantHeader(true);
    }
  }
  
  private void setIsNewMenu(User user)
  {
    if ((PermissionChecker.isPermissionApplied("ALL_REQUISITIONS", user)) || (PermissionChecker.isPermissionApplied("REQUISITION_CRUD", user))) {
      setRequisitionCreate(true);
    }
    if (PermissionChecker.isPermissionApplied("REQ_TEMPLATE_CRUD", user)) {
      setRequisitionTemplateCreate(true);
    }
    if ((PermissionChecker.isPermissionApplied("ALL_APPLICANTS", user)) || (PermissionChecker.isPermissionApplied("APPLICANTS_CRUD", user))) {
      setApplicantCreate(true);
    }
    if ((isRequisitionCreate()) || (isRequisitionTemplateCreate()) || (isApplicantCreate())) {
      setNew(true);
    }
  }
  
  private void setUpRequisitionsMenu(User user)
  {
    if (PermissionChecker.isPermissionApplied("ALL_REQUISITIONS", user)) {
      setAllRequisitions(true);
    }
    if (PermissionChecker.isPermissionApplied("ALL_REQUISITIONS_READ", user)) {
      setAllRequisitionsRead(true);
    }
    if (PermissionChecker.isPermissionApplied("OWN_ORGANIZATION_REQUISTIONS", user)) {
      setOwnOrganizationRequistions(true);
    }
    if (PermissionChecker.isPermissionApplied("REQ_TEMPLATE_CRUD", user)) {
      setReqTemplates(true);
    }
    if ((isAllRequisitions()) || (isAllRequisitionsRead()) || (isOwnOrganizationRequistions()) || (isReqTemplates())) {
      setRequistionMenu(true);
    }
  }
  
  private void setUpAdminMenu(User user)
  {
    if (PermissionChecker.isPermissionApplied("USER_CRUD", user)) {
      setUser(true);
    }
    if (PermissionChecker.isPermissionApplied("ROLES_CRUD", user)) {
      setRoles(true);
    }
    if ((isUser()) || (isRoles())) {
      setSecurity(true);
    }
    if (PermissionChecker.isPermissionApplied("REFERRAL_BUDGET_CODE_CRUD", user)) {
      setRererralBudgetCode(true);
    }
    if (PermissionChecker.isPermissionApplied("REFERRAL_SCHEME_CRUD", user)) {
      setRererralScheme(true);
    }
    if (PermissionChecker.isPermissionApplied("REFERRAL_SCHEME_TYPE_CRUD", user)) {
      setRererralSchemeType(true);
    }
    if ((isRererralBudgetCode()) || (isRererralScheme())) {
      setRererral(true);
    }
    if (PermissionChecker.isPermissionApplied("ORGANIZATION_CRUD", user)) {
      setOrganizations(true);
    }
    if (PermissionChecker.isPermissionApplied("PROJECT_CODES_CRUD", user)) {
      setProjectCodes(true);
    }
    if (PermissionChecker.isPermissionApplied("NOTIFICATION_MGMT", user)) {
      setNotificationHeader(true);
    }
    if (PermissionChecker.isPermissionApplied("LOCATIONS_CRUD", user)) {
      setLocations(true);
    }
    if ((isOrganizations()) || (isProjectCodes()) || (isLocations())) {
      setOrganizationHeader(true);
    }
    if (PermissionChecker.isPermissionApplied("QUESTIONNAIRE_EXAMS", user)) {
      setCompetenciesQuestions(true);
    }
    if (isCompetenciesQuestions()) {
      setCompetencyHeader(true);
    }
    if (PermissionChecker.isPermissionApplied("ADMIN_CONFIGURATIONS", user)) {
      setConfigurations(true);
    }
    if (PermissionChecker.isPermissionApplied("SALARY_PLAN_CRUD", user)) {
      setSalaryPlan(true);
    }
    if (PermissionChecker.isPermissionApplied("BUDGET_CODE_CRUD", user)) {
      setBudgetCode(true);
    }
    if (PermissionChecker.isPermissionApplied("JOB_CODE_CRUD", user)) {
      setJobCode(true);
    }
    if (PermissionChecker.isPermissionApplied("JOB_GRADE_CRUD", user)) {
      setJobGrade(true);
    }
    if (PermissionChecker.isPermissionApplied("JOB_TITLES_CRUD", user)) {
      setJobTitle(true);
    }
    if ((isSalaryPlan()) || (isBudgetCode()) || (isJobTitle()) || (isJobGrade())) {
      setJobHeader(true);
    }
    if (PermissionChecker.isPermissionApplied("EMAIL_CAMPAIGN_CRUD", user)) {
      setEmailCampaignCrud(true);
    }
    if (PermissionChecker.isPermissionApplied("TALENT_POOL_CRUD", user)) {
      setTalentPoolCrud(true);
    }
    if (PermissionChecker.isPermissionApplied("MASS_EMAIL", user)) {
      setMassEmail(true);
    }
    if (PermissionChecker.isPermissionApplied("SYSTEM_RULE_MANAGE", user)) {
      setSystemRule(true);
    }
    if (PermissionChecker.isPermissionApplied("LOV_MANAGEMENT", user)) {
      setLovs(true);
    }
    if (PermissionChecker.isPermissionApplied("ALL_CALENDAR_ACEESS", user)) {
      setCalendarAccess(true);
    }
    if ((isSecurity()) || (isOrganizationHeader()) || (isCompetencyHeader()) || (isJobHeader()) || (isRererral()) || (isConfigurations()) || (isNotificationHeader()) || (isEmailCampaignCrud()) || (isTalentPoolCrud()) || (isMassEmail()) || (isSystemRule()) || (isLovs())) {
      setAdmin(true);
    }
  }
  
  public void setupMenuAndPackage(User user)
  {
    UserRegData userreg = BOFactory.getUserBO().getUserRegDataById(user.getSuper_user_key());
    if (userreg != null) {
      user.setPackagetaken(userreg.getPackagetaken());
    }
    setUpAdminMenu(user);
    
    setUpRequisitionsMenu(user);
    

    setUpAquistionsMenu(user);
    

    setRedemptionMgmtMenu(user);
    


    setATaskMenu(user);
    


    setIsNewMenu(user);
    

    setReportMenu(user);
  }
  
  public boolean isUser()
  {
    return this.isUser;
  }
  
  public void setUser(boolean isUser)
  {
    this.isUser = isUser;
  }
  
  public boolean isOrganizations()
  {
    return this.isOrganizations;
  }
  
  public void setOrganizations(boolean isOrganizations)
  {
    this.isOrganizations = isOrganizations;
  }
  
  public boolean isRererral()
  {
    return this.isRererral;
  }
  
  public void setRererral(boolean isRererral)
  {
    this.isRererral = isRererral;
  }
  
  public boolean isRererralBudgetCode()
  {
    return this.isRererralBudgetCode;
  }
  
  public void setRererralBudgetCode(boolean isRererralBudgetCode)
  {
    this.isRererralBudgetCode = isRererralBudgetCode;
  }
  
  public boolean isRererralScheme()
  {
    return this.isRererralScheme;
  }
  
  public void setRererralScheme(boolean isRererralScheme)
  {
    this.isRererralScheme = isRererralScheme;
  }
  
  public boolean isRererralSchemeType()
  {
    return this.isRererralSchemeType;
  }
  
  public void setRererralSchemeType(boolean isRererralSchemeType)
  {
    this.isRererralSchemeType = isRererralSchemeType;
  }
  
  public boolean isOrganizationHeader()
  {
    return this.isOrganizationHeader;
  }
  
  public void setOrganizationHeader(boolean isOrganizationHeader)
  {
    this.isOrganizationHeader = isOrganizationHeader;
  }
  
  public boolean isProjectCodes()
  {
    return this.isProjectCodes;
  }
  
  public void setProjectCodes(boolean isProjectCodes)
  {
    this.isProjectCodes = isProjectCodes;
  }
  
  public boolean isSecurity()
  {
    return this.isSecurity;
  }
  
  public void setSecurity(boolean isSecurity)
  {
    this.isSecurity = isSecurity;
  }
  
  public boolean isRoles()
  {
    return this.isRoles;
  }
  
  public void setRoles(boolean isRoles)
  {
    this.isRoles = isRoles;
  }
  
  public boolean isCompetenciesQuestions()
  {
    return this.isCompetenciesQuestions;
  }
  
  public void setCompetenciesQuestions(boolean isCompetenciesQuestions)
  {
    this.isCompetenciesQuestions = isCompetenciesQuestions;
  }
  
  public boolean isCompetencyHeader()
  {
    return this.isCompetencyHeader;
  }
  
  public void setCompetencyHeader(boolean isCompetencyHeader)
  {
    this.isCompetencyHeader = isCompetencyHeader;
  }
  
  public boolean isLocations()
  {
    return this.isLocations;
  }
  
  public void setLocations(boolean isLocations)
  {
    this.isLocations = isLocations;
  }
  
  public boolean isSalaryPlan()
  {
    return this.isSalaryPlan;
  }
  
  public void setSalaryPlan(boolean isSalaryPlan)
  {
    this.isSalaryPlan = isSalaryPlan;
  }
  
  public boolean isBudgetCode()
  {
    return this.isBudgetCode;
  }
  
  public void setBudgetCode(boolean isBudgetCode)
  {
    this.isBudgetCode = isBudgetCode;
  }
  
  public boolean isJobGrade()
  {
    return this.isJobGrade;
  }
  
  public void setJobGrade(boolean isJobGrade)
  {
    this.isJobGrade = isJobGrade;
  }
  
  public boolean isJobCode()
  {
    return this.isJobCode;
  }
  
  public void setJobCode(boolean isJobCode)
  {
    this.isJobCode = isJobCode;
  }
  
  public boolean isAdmin()
  {
    return this.isAdmin;
  }
  
  public void setAdmin(boolean isAdmin)
  {
    this.isAdmin = isAdmin;
  }
  
  public boolean isRequistionMenu()
  {
    return this.isRequistionMenu;
  }
  
  public void setRequistionMenu(boolean isRequistionMenu)
  {
    this.isRequistionMenu = isRequistionMenu;
  }
  
  public boolean isAllRequisitions()
  {
    return this.isAllRequisitions;
  }
  
  public void setAllRequisitions(boolean isAllRequisitions)
  {
    this.isAllRequisitions = isAllRequisitions;
  }
  
  public boolean isReqTemplates()
  {
    return this.isReqTemplates;
  }
  
  public void setReqTemplates(boolean isReqTemplates)
  {
    this.isReqTemplates = isReqTemplates;
  }
  
  public boolean isAllApplicants()
  {
    return this.isAllApplicants;
  }
  
  public void setAllApplicants(boolean isAllApplicants)
  {
    this.isAllApplicants = isAllApplicants;
  }
  
  public boolean isBulkUploadApplicants()
  {
    return this.isBulkUploadApplicants;
  }
  
  public void setBulkUploadApplicants(boolean isBulkUploadApplicants)
  {
    this.isBulkUploadApplicants = isBulkUploadApplicants;
  }
  
  public boolean isReferralRedemption()
  {
    return this.isReferralRedemption;
  }
  
  public void setReferralRedemption(boolean isReferralRedemption)
  {
    this.isReferralRedemption = isReferralRedemption;
  }
  
  public boolean isAgencyList()
  {
    return this.isAgencyList;
  }
  
  public void setAgencyList(boolean isAgencyList)
  {
    this.isAgencyList = isAgencyList;
  }
  
  public boolean isAgencyRedemption()
  {
    return this.isAgencyRedemption;
  }
  
  public void setAgencyRedemption(boolean isAgencyRedemption)
  {
    this.isAgencyRedemption = isAgencyRedemption;
  }
  
  public boolean isAllExportUploadTasks()
  {
    return this.isAllExportUploadTasks;
  }
  
  public void setAllExportUploadTasks(boolean isAllExportUploadTasks)
  {
    this.isAllExportUploadTasks = isAllExportUploadTasks;
  }
  
  public boolean isAllPendingTasks()
  {
    return this.isAllPendingTasks;
  }
  
  public void setAllPendingTasks(boolean isAllPendingTasks)
  {
    this.isAllPendingTasks = isAllPendingTasks;
  }
  
  public boolean isOnBoardMgmt()
  {
    return this.isOnBoardMgmt;
  }
  
  public void setOnBoardMgmt(boolean isOnBoardMgmt)
  {
    this.isOnBoardMgmt = isOnBoardMgmt;
  }
  
  public boolean isOwnOrganizationRequistions()
  {
    return this.isOwnOrganizationRequistions;
  }
  
  public void setOwnOrganizationRequistions(boolean isOwnOrganizationRequistions)
  {
    this.isOwnOrganizationRequistions = isOwnOrganizationRequistions;
  }
  
  public boolean isAllRequisitionsRead()
  {
    return this.isAllRequisitionsRead;
  }
  
  public void setAllRequisitionsRead(boolean isAllRequisitionsRead)
  {
    this.isAllRequisitionsRead = isAllRequisitionsRead;
  }
  
  public boolean isNew()
  {
    return this.isNew;
  }
  
  public void setNew(boolean isNew)
  {
    this.isNew = isNew;
  }
  
  public boolean isRequisitionCreate()
  {
    return this.isRequisitionCreate;
  }
  
  public void setRequisitionCreate(boolean isRequisitionCreate)
  {
    this.isRequisitionCreate = isRequisitionCreate;
  }
  
  public boolean isApplicantCreate()
  {
    return this.isApplicantCreate;
  }
  
  public void setApplicantCreate(boolean isApplicantCreate)
  {
    this.isApplicantCreate = isApplicantCreate;
  }
  
  public boolean isRequisitionTemplateCreate()
  {
    return this.isRequisitionTemplateCreate;
  }
  
  public void setRequisitionTemplateCreate(boolean isRequisitionTemplateCreate)
  {
    this.isRequisitionTemplateCreate = isRequisitionTemplateCreate;
  }
  
  public boolean isRedemption()
  {
    return this.isRedemption;
  }
  
  public void setRedemption(boolean isRedemption)
  {
    this.isRedemption = isRedemption;
  }
  
  public boolean isJobTitle()
  {
    return this.isJobTitle;
  }
  
  public void setJobTitle(boolean isJobTitle)
  {
    this.isJobTitle = isJobTitle;
  }
  
  public boolean isJobHeader()
  {
    return this.isJobHeader;
  }
  
  public void setJobHeader(boolean isJobHeader)
  {
    this.isJobHeader = isJobHeader;
  }
  
  public boolean isConfigurations()
  {
    return this.isConfigurations;
  }
  
  public void setConfigurations(boolean isConfigurations)
  {
    this.isConfigurations = isConfigurations;
  }
  
  public boolean isNotificationHeader()
  {
    return this.isNotificationHeader;
  }
  
  public void setNotificationHeader(boolean isNotificationHeader)
  {
    this.isNotificationHeader = isNotificationHeader;
  }
  
  public boolean isReports()
  {
    return this.isReports;
  }
  
  public void setReports(boolean isReports)
  {
    this.isReports = isReports;
  }
  
  public boolean isApplicantHeader()
  {
    return this.isApplicantHeader;
  }
  
  public void setApplicantHeader(boolean isApplicantHeader)
  {
    this.isApplicantHeader = isApplicantHeader;
  }
  
  public boolean isTalentPoolCrud()
  {
    return this.isTalentPoolCrud;
  }
  
  public void setTalentPoolCrud(boolean isTalentPoolCrud)
  {
    this.isTalentPoolCrud = isTalentPoolCrud;
  }
  
  public boolean isEmailCampaignCrud()
  {
    return this.isEmailCampaignCrud;
  }
  
  public void setEmailCampaignCrud(boolean isEmailCampaignCrud)
  {
    this.isEmailCampaignCrud = isEmailCampaignCrud;
  }
  
  public boolean isMassEmail()
  {
    return this.isMassEmail;
  }
  
  public void setMassEmail(boolean isMassEmail)
  {
    this.isMassEmail = isMassEmail;
  }
  
  public boolean isSystemRule()
  {
    return this.isSystemRule;
  }
  
  public void setSystemRule(boolean isSystemRule)
  {
    this.isSystemRule = isSystemRule;
  }
  
  public boolean isLovs()
  {
    return this.isLovs;
  }
  
  public void setLovs(boolean isLovs)
  {
    this.isLovs = isLovs;
  }
  
  public boolean isCalendarAccess()
  {
    return this.isCalendarAccess;
  }
  
  public void setCalendarAccess(boolean isCalendarAccess)
  {
    this.isCalendarAccess = isCalendarAccess;
  }
  
  public boolean isRegisterUser()
  {
    return this.isRegisterUser;
  }
  
  public void setRegisterUser(boolean isRegisterUser)
  {
    this.isRegisterUser = isRegisterUser;
  }
}
