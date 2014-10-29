package com.bean;

import com.bean.lov.Category;
import com.bean.lov.JobCategory;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class JobRequisition
{
  public long jobreqId;
  public long requisition_number;
  public String uuid;
  public String jobreqName;
  public String jobTitle;
  public String jobreqDesc;
  public User hiringmgr;
  public Department department;
  public ProjectCodes projectcode;
  public Location location;
  public JobType jobtype;
  public JobGrade jobgrade;
  public JobCode jobcode;
  public String jobreqcode;
  public SalaryPlan salaryplan;
  public WorkShift workshift;
  public int defaultStandardHours;
  public FlsaStatus flsa;
  public CompFrequency compfrequency;
  public int durationinmonths;
  public int minyearsofExpRequired;
  public int maxyearsofExpRequired;
  public String minimumLevelOfEducation;
  public String otherExperience;
  public BudgetCode budgetcode;
  public String internal;
  public Organization organization;
  public EvaluationTemplate evtemplate;
  public String status;
  public String state;
  public String createdBy;
  public Date createdDate;
  public Date updatedDate;
  public String updatedBy;
  public Date effectiveStartDate;
  public Date effectiveEndDate;
  public String jobInstructions;
  public String jobDetails;
  public String jobPosition;
  public String jobRoles;
  public int numberOfOpening;
  public int numberOfOpeningRemain;
  public int isapprovalInitiated;
  public String isnewPositions;
  public String primarySkill;
  private String scoringKeyword;
  public String departmentValue = "";
  public String projectcodeValue = "";
  public String projectcodeName = "";
  public String organizationValue = "";
  public String hiringMgrValue = "";
  public String jobtypeValue = "";
  public String workshiftValue = "";
  public String locationValue = "";
  public String jobGradeValue = "";
  public String categoryValue = "";
  public long templateId;
  public String templateName;
  public long approvalIniById;
  public String approvalIniByName;
  public Date approvalIniDate;
  public String approvalInitiationComment;
  public long currentOwnerId;
  public String isGroup = "N";
  public String currentOwnerName;
  public List comptetencyList;
  public List accomplishmentList;
  public List approversList;
  public int isrejected = 0;
  public Date targetfinishdate;
  private Date targetstartdate;
  public long publishedById;
  public String publishedByName;
  public Date publishedDate;
  public String publishedComment;
  public long closedById;
  public String closedByName;
  public Date closedDate;
  public String closedComment;
  public long deleteById;
  public String deleteByName;
  public Date deleteDate;
  public String deleteComment;
  public long recruiterId;
  public String recruiterName;
  public String isgrouprecruiter = "N";
  public long refferalSchemeId;
  public String refferalSchemeName;
  public long agencyRefferalSchemeId;
  public String agencyRefferalSchemeName;
  Set<RefferalScheme> employeeReferralSchemeList;
  Set<RefferalScheme> agencyReferralSchemeList;
  public String stdworkinghoursunitName;
  public String otherMinimumLevelOfEducation;
  private String publishToEmpRef;
  private String publishToExternal;
  private String eeoInfoIncluded;
  private Category catagory;
  public JobCategory jobcategory;
  public Designations designation;
  public String isnewpositionno;
  private int isindexSearchApplied = 0;
  private int isFilterApplied = 0;
  private int isScoringApplied = 0;
  public long super_user_key;
  public int totalAppcount;
  public long catIdValue;
  public long orgIdValue;
  public long deptIdValue;
  public long projIdValue;
  public long hiringMgrIdValue;
  public long jobgradeIdValue;
  public long salaryPlanIdValue;
  public long budgetCodeIdValue;
  public long jobtypeIdValue;
  public long workshiftIdValue;
  public long flsaStatusIdValue;
  public long compFreqIdValue;
  public long designationIdValue;
  public long jobcatIdValue;
  public long locationIdValue;
  public String statusUiValue;
  public String allApplicantViewed;
  
  public String getIsnewpositionno()
  {
    return this.isnewpositionno;
  }
  
  public void setIsnewpositionno(String isnewpositionno)
  {
    this.isnewpositionno = isnewpositionno;
  }
  
  public String getOtherMinimumLevelOfEducation()
  {
    return this.otherMinimumLevelOfEducation;
  }
  
  public void setOtherMinimumLevelOfEducation(String otherMinimumLevelOfEducation)
  {
    this.otherMinimumLevelOfEducation = otherMinimumLevelOfEducation;
  }
  
  public String getStdworkinghoursunitName()
  {
    return this.stdworkinghoursunitName;
  }
  
  public void setStdworkinghoursunitName(String stdworkinghoursunitName)
  {
    this.stdworkinghoursunitName = stdworkinghoursunitName;
  }
  
  public long getJobreqId()
  {
    return this.jobreqId;
  }
  
  public void setJobreqId(long jobreqId)
  {
    this.jobreqId = jobreqId;
  }
  
  public String getJobreqName()
  {
    return this.jobreqName;
  }
  
  public void setJobreqName(String jobreqName)
  {
    this.jobreqName = jobreqName;
  }
  
  public String getJobTitle()
  {
    return this.jobTitle;
  }
  
  public void setJobTitle(String jobTitle)
  {
    this.jobTitle = jobTitle;
  }
  
  public String getJobreqDesc()
  {
    return this.jobreqDesc;
  }
  
  public void setJobreqDesc(String jobreqDesc)
  {
    this.jobreqDesc = jobreqDesc;
  }
  
  public User getHiringmgr()
  {
    return this.hiringmgr;
  }
  
  public void setHiringmgr(User hiringmgr)
  {
    this.hiringmgr = hiringmgr;
    if (hiringmgr != null)
    {
      this.hiringMgrValue = (hiringmgr.getFirstName() + " " + hiringmgr.getLastName());
      this.hiringMgrIdValue = hiringmgr.getUserId();
    }
  }
  
  public Department getDepartment()
  {
    return this.department;
  }
  
  public void setDepartment(Department department)
  {
    this.department = department;
    if (department != null)
    {
      this.departmentValue = department.getDepartmentName();
      this.deptIdValue = department.getDepartmentId();
    }
  }
  
  public ProjectCodes getProjectcode()
  {
    return this.projectcode;
  }
  
  public void setProjectcode(ProjectCodes projectcode)
  {
    this.projectcode = projectcode;
    if (projectcode != null)
    {
      this.projectcodeValue = projectcode.getProjCode();
      this.projectcodeName = projectcode.getProjName();
      this.projIdValue = projectcode.getProjectId();
    }
  }
  
  public Location getLocation()
  {
    return this.location;
  }
  
  public void setLocation(Location location)
  {
    this.location = location;
    if (location != null)
    {
      this.locationIdValue = location.getLocationId();
      this.locationValue = location.getLocationName();
    }
  }
  
  public JobType getJobtype()
  {
    return this.jobtype;
  }
  
  public void setJobtype(JobType jobtype)
  {
    this.jobtype = jobtype;
    if (jobtype != null)
    {
      this.jobtypeValue = jobtype.getJobTypeName();
      this.jobtypeIdValue = jobtype.getJobTypeId();
    }
  }
  
  public JobGrade getJobgrade()
  {
    return this.jobgrade;
  }
  
  public void setJobgrade(JobGrade jobgrade)
  {
    this.jobgrade = jobgrade;
    if (jobgrade != null)
    {
      this.jobGradeValue = jobgrade.getJobGradeName();
      this.jobgradeIdValue = jobgrade.getJobgradeId();
    }
  }
  
  public JobCode getJobcode()
  {
    return this.jobcode;
  }
  
  public void setJobcode(JobCode jobcode)
  {
    this.jobcode = jobcode;
  }
  
  public SalaryPlan getSalaryplan()
  {
    return this.salaryplan;
  }
  
  public void setSalaryplan(SalaryPlan salaryplan)
  {
    this.salaryplan = salaryplan;
    if (salaryplan != null) {
      this.salaryPlanIdValue = salaryplan.getSalaryplanId();
    }
  }
  
  public WorkShift getWorkshift()
  {
    return this.workshift;
  }
  
  public void setWorkshift(WorkShift workshift)
  {
    this.workshift = workshift;
    if (workshift != null)
    {
      this.workshiftValue = workshift.getShiftName();
      this.workshiftIdValue = workshift.getShiftId();
    }
  }
  
  public int getDefaultStandardHours()
  {
    return this.defaultStandardHours;
  }
  
  public void setDefaultStandardHours(int defaultStandardHours)
  {
    this.defaultStandardHours = defaultStandardHours;
  }
  
  public FlsaStatus getFlsa()
  {
    return this.flsa;
  }
  
  public void setFlsa(FlsaStatus flsa)
  {
    this.flsa = flsa;
    if (flsa != null) {
      this.flsaStatusIdValue = flsa.getFlsaId();
    }
  }
  
  public CompFrequency getCompfrequency()
  {
    return this.compfrequency;
  }
  
  public void setCompfrequency(CompFrequency compfrequency)
  {
    this.compfrequency = compfrequency;
    if (compfrequency != null) {
      this.compFreqIdValue = compfrequency.getCompFrequencyId();
    }
  }
  
  public int getDurationinmonths()
  {
    return this.durationinmonths;
  }
  
  public void setDurationinmonths(int durationinmonths)
  {
    this.durationinmonths = durationinmonths;
  }
  
  public int getMinyearsofExpRequired()
  {
    return this.minyearsofExpRequired;
  }
  
  public void setMinyearsofExpRequired(int minyearsofExpRequired)
  {
    this.minyearsofExpRequired = minyearsofExpRequired;
  }
  
  public int getMaxyearsofExpRequired()
  {
    return this.maxyearsofExpRequired;
  }
  
  public void setMaxyearsofExpRequired(int maxyearsofExpRequired)
  {
    this.maxyearsofExpRequired = maxyearsofExpRequired;
  }
  
  public String getMinimumLevelOfEducation()
  {
    return this.minimumLevelOfEducation;
  }
  
  public void setMinimumLevelOfEducation(String minimumLevelOfEducation)
  {
    this.minimumLevelOfEducation = minimumLevelOfEducation;
  }
  
  public String getOtherExperience()
  {
    return this.otherExperience;
  }
  
  public void setOtherExperience(String otherExperience)
  {
    this.otherExperience = otherExperience;
  }
  
  public BudgetCode getBudgetcode()
  {
    return this.budgetcode;
  }
  
  public void setBudgetcode(BudgetCode budgetcode)
  {
    this.budgetcode = budgetcode;
    if (budgetcode != null) {
      this.budgetCodeIdValue = budgetcode.getBudgetId();
    }
  }
  
  public String getInternal()
  {
    return this.internal;
  }
  
  public void setInternal(String internal)
  {
    this.internal = internal;
  }
  
  public Organization getOrganization()
  {
    return this.organization;
  }
  
  public void setOrganization(Organization organization)
  {
    this.organization = organization;
    if (organization != null)
    {
      this.organizationValue = organization.getOrgName();
      this.orgIdValue = organization.getOrgId();
    }
  }
  
  public EvaluationTemplate getEvtemplate()
  {
    return this.evtemplate;
  }
  
  public void setEvtemplate(EvaluationTemplate evtemplate)
  {
    this.evtemplate = evtemplate;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
    this.statusUiValue = (this.state + " > " + this.status);
  }
  
  public String getState()
  {
    return this.state;
  }
  
  public void setState(String state)
  {
    this.state = state;
    this.statusUiValue = (this.state + " > " + this.status);
  }
  
  public String getCreatedBy()
  {
    return this.createdBy;
  }
  
  public void setCreatedBy(String createdBy)
  {
    this.createdBy = createdBy;
  }
  
  public Date getCreatedDate()
  {
    return this.createdDate;
  }
  
  public void setCreatedDate(Date createdDate)
  {
    this.createdDate = createdDate;
  }
  
  public Date getUpdatedDate()
  {
    return this.updatedDate;
  }
  
  public void setUpdatedDate(Date updatedDate)
  {
    this.updatedDate = updatedDate;
  }
  
  public String getUpdatedBy()
  {
    return this.updatedBy;
  }
  
  public void setUpdatedBy(String updatedBy)
  {
    this.updatedBy = updatedBy;
  }
  
  public Date getEffectiveStartDate()
  {
    return this.effectiveStartDate;
  }
  
  public void setEffectiveStartDate(Date effectiveStartDate)
  {
    this.effectiveStartDate = effectiveStartDate;
  }
  
  public Date getEffectiveEndDate()
  {
    return this.effectiveEndDate;
  }
  
  public void setEffectiveEndDate(Date effectiveEndDate)
  {
    this.effectiveEndDate = effectiveEndDate;
  }
  
  public String getJobInstructions()
  {
    return this.jobInstructions;
  }
  
  public void setJobInstructions(String jobInstructions)
  {
    this.jobInstructions = jobInstructions;
  }
  
  public String getJobDetails()
  {
    return this.jobDetails;
  }
  
  public void setJobDetails(String jobDetails)
  {
    this.jobDetails = jobDetails;
  }
  
  public String getJobPosition()
  {
    return this.jobPosition;
  }
  
  public void setJobPosition(String jobPosition)
  {
    this.jobPosition = jobPosition;
  }
  
  public String getJobRoles()
  {
    return this.jobRoles;
  }
  
  public void setJobRoles(String jobRoles)
  {
    this.jobRoles = jobRoles;
  }
  
  public int getNumberOfOpening()
  {
    return this.numberOfOpening;
  }
  
  public void setNumberOfOpening(int numberOfOpening)
  {
    this.numberOfOpening = numberOfOpening;
  }
  
  public int getNumberOfOpeningRemain()
  {
    return this.numberOfOpeningRemain;
  }
  
  public void setNumberOfOpeningRemain(int numberOfOpeningRemain)
  {
    this.numberOfOpeningRemain = numberOfOpeningRemain;
  }
  
  public int getIsapprovalInitiated()
  {
    return this.isapprovalInitiated;
  }
  
  public void setIsapprovalInitiated(int isapprovalInitiated)
  {
    this.isapprovalInitiated = isapprovalInitiated;
  }
  
  public String getIsnewPositions()
  {
    return this.isnewPositions;
  }
  
  public void setIsnewPositions(String isnewPositions)
  {
    this.isnewPositions = isnewPositions;
  }
  
  public long getTemplateId()
  {
    return this.templateId;
  }
  
  public void setTemplateId(long templateId)
  {
    this.templateId = templateId;
  }
  
  public String getTemplateName()
  {
    return this.templateName;
  }
  
  public void setTemplateName(String templateName)
  {
    this.templateName = templateName;
  }
  
  public List getComptetencyList()
  {
    return this.comptetencyList;
  }
  
  public void setComptetencyList(List comptetencyList)
  {
    this.comptetencyList = comptetencyList;
  }
  
  public List getAccomplishmentList()
  {
    return this.accomplishmentList;
  }
  
  public void setAccomplishmentList(List accomplishmentList)
  {
    this.accomplishmentList = accomplishmentList;
  }
  
  public List getApproversList()
  {
    return this.approversList;
  }
  
  public void setApproversList(List approversList)
  {
    this.approversList = approversList;
  }
  
  public String getApprovalInitiationComment()
  {
    return this.approvalInitiationComment;
  }
  
  public void setApprovalInitiationComment(String approvalInitiationComment)
  {
    this.approvalInitiationComment = approvalInitiationComment;
  }
  
  public int getIsrejected()
  {
    return this.isrejected;
  }
  
  public void setIsrejected(int isrejected)
  {
    this.isrejected = isrejected;
  }
  
  public long getApprovalIniById()
  {
    return this.approvalIniById;
  }
  
  public void setApprovalIniById(long approvalIniById)
  {
    this.approvalIniById = approvalIniById;
  }
  
  public String getApprovalIniByName()
  {
    return this.approvalIniByName;
  }
  
  public void setApprovalIniByName(String approvalIniByName)
  {
    this.approvalIniByName = approvalIniByName;
  }
  
  public Date getApprovalIniDate()
  {
    return this.approvalIniDate;
  }
  
  public void setApprovalIniDate(Date approvalIniDate)
  {
    this.approvalIniDate = approvalIniDate;
  }
  
  public long getCurrentOwnerId()
  {
    return this.currentOwnerId;
  }
  
  public void setCurrentOwnerId(long currentOwnerId)
  {
    this.currentOwnerId = currentOwnerId;
  }
  
  public Date getTargetfinishdate()
  {
    return this.targetfinishdate;
  }
  
  public void setTargetfinishdate(Date targetfinishdate)
  {
    this.targetfinishdate = targetfinishdate;
  }
  
  public Date getTargetstartdate()
  {
    return this.targetstartdate;
  }
  
  public void setTargetstartdate(Date targetstartdate)
  {
    this.targetstartdate = targetstartdate;
  }
  
  public long getPublishedById()
  {
    return this.publishedById;
  }
  
  public void setPublishedById(long publishedById)
  {
    this.publishedById = publishedById;
  }
  
  public String getPublishedByName()
  {
    return this.publishedByName;
  }
  
  public void setPublishedByName(String publishedByName)
  {
    this.publishedByName = publishedByName;
  }
  
  public Date getPublishedDate()
  {
    return this.publishedDate;
  }
  
  public void setPublishedDate(Date publishedDate)
  {
    this.publishedDate = publishedDate;
  }
  
  public String getPublishedComment()
  {
    return this.publishedComment;
  }
  
  public void setPublishedComment(String publishedComment)
  {
    this.publishedComment = publishedComment;
  }
  
  public long getClosedById()
  {
    return this.closedById;
  }
  
  public void setClosedById(long closedById)
  {
    this.closedById = closedById;
  }
  
  public String getClosedByName()
  {
    return this.closedByName;
  }
  
  public void setClosedByName(String closedByName)
  {
    this.closedByName = closedByName;
  }
  
  public Date getClosedDate()
  {
    return this.closedDate;
  }
  
  public void setClosedDate(Date closedDate)
  {
    this.closedDate = closedDate;
  }
  
  public String getClosedComment()
  {
    return this.closedComment;
  }
  
  public void setClosedComment(String closedComment)
  {
    this.closedComment = closedComment;
  }
  
  public long getDeleteById()
  {
    return this.deleteById;
  }
  
  public void setDeleteById(long deleteById)
  {
    this.deleteById = deleteById;
  }
  
  public String getDeleteByName()
  {
    return this.deleteByName;
  }
  
  public void setDeleteByName(String deleteByName)
  {
    this.deleteByName = deleteByName;
  }
  
  public Date getDeleteDate()
  {
    return this.deleteDate;
  }
  
  public void setDeleteDate(Date deleteDate)
  {
    this.deleteDate = deleteDate;
  }
  
  public String getDeleteComment()
  {
    return this.deleteComment;
  }
  
  public void setDeleteComment(String deleteComment)
  {
    this.deleteComment = deleteComment;
  }
  
  public String getJobreqcode()
  {
    return this.jobreqcode;
  }
  
  public void setJobreqcode(String jobreqcode)
  {
    this.jobreqcode = jobreqcode;
  }
  
  public long getRecruiterId()
  {
    return this.recruiterId;
  }
  
  public void setRecruiterId(long recruiterId)
  {
    this.recruiterId = recruiterId;
  }
  
  public String getRecruiterName()
  {
    return this.recruiterName;
  }
  
  public void setRecruiterName(String recruiterName)
  {
    this.recruiterName = recruiterName;
  }
  
  public long getRefferalSchemeId()
  {
    return this.refferalSchemeId;
  }
  
  public void setRefferalSchemeId(long refferalSchemeId)
  {
    this.refferalSchemeId = refferalSchemeId;
  }
  
  public String getRefferalSchemeName()
  {
    return this.refferalSchemeName;
  }
  
  public void setRefferalSchemeName(String refferalSchemeName)
  {
    this.refferalSchemeName = refferalSchemeName;
  }
  
  public long getAgencyRefferalSchemeId()
  {
    return this.agencyRefferalSchemeId;
  }
  
  public void setAgencyRefferalSchemeId(long agencyRefferalSchemeId)
  {
    this.agencyRefferalSchemeId = agencyRefferalSchemeId;
  }
  
  public String getAgencyRefferalSchemeName()
  {
    return this.agencyRefferalSchemeName;
  }
  
  public void setAgencyRefferalSchemeName(String agencyRefferalSchemeName)
  {
    this.agencyRefferalSchemeName = agencyRefferalSchemeName;
  }
  
  public String getUuid()
  {
    return this.uuid;
  }
  
  public void setUuid(String uuid)
  {
    this.uuid = uuid;
  }
  
  public String getPublishToEmpRef()
  {
    return this.publishToEmpRef;
  }
  
  public void setPublishToEmpRef(String publishToEmpRef)
  {
    this.publishToEmpRef = publishToEmpRef;
  }
  
  public String getPublishToExternal()
  {
    return this.publishToExternal;
  }
  
  public void setPublishToExternal(String publishToExternal)
  {
    this.publishToExternal = publishToExternal;
  }
  
  public Category getCatagory()
  {
    return this.catagory;
  }
  
  public void setCatagory(Category catagory)
  {
    this.catagory = catagory;
    if (catagory != null)
    {
      this.categoryValue = catagory.getCatName();
      this.catIdValue = catagory.getCatId();
    }
  }
  
  public String getIsGroup()
  {
    return this.isGroup;
  }
  
  public void setIsGroup(String isGroup)
  {
    this.isGroup = isGroup;
  }
  
  public String getIsgrouprecruiter()
  {
    return this.isgrouprecruiter;
  }
  
  public void setIsgrouprecruiter(String isgrouprecruiter)
  {
    this.isgrouprecruiter = isgrouprecruiter;
  }
  
  public Set<RefferalScheme> getEmployeeReferralSchemeList()
  {
    return this.employeeReferralSchemeList;
  }
  
  public void setEmployeeReferralSchemeList(Set<RefferalScheme> employeeReferralSchemeList)
  {
    this.employeeReferralSchemeList = employeeReferralSchemeList;
  }
  
  public Set<RefferalScheme> getAgencyReferralSchemeList()
  {
    return this.agencyReferralSchemeList;
  }
  
  public void setAgencyReferralSchemeList(Set<RefferalScheme> agencyReferralSchemeList)
  {
    this.agencyReferralSchemeList = agencyReferralSchemeList;
  }
  
  public String getCurrentOwnerName()
  {
    return this.currentOwnerName;
  }
  
  public void setCurrentOwnerName(String currentOwnerName)
  {
    this.currentOwnerName = currentOwnerName;
  }
  
  public String getPrimarySkill()
  {
    return this.primarySkill;
  }
  
  public void setPrimarySkill(String primarySkill)
  {
    this.primarySkill = primarySkill;
  }
  
  public int getIsindexSearchApplied()
  {
    return this.isindexSearchApplied;
  }
  
  public void setIsindexSearchApplied(int isindexSearchApplied)
  {
    this.isindexSearchApplied = isindexSearchApplied;
  }
  
  public String getScoringKeyword()
  {
    return this.scoringKeyword;
  }
  
  public void setScoringKeyword(String scoringKeyword)
  {
    this.scoringKeyword = scoringKeyword;
  }
  
  public int getIsFilterApplied()
  {
    return this.isFilterApplied;
  }
  
  public void setIsFilterApplied(int isFilterApplied)
  {
    this.isFilterApplied = isFilterApplied;
  }
  
  public int getIsScoringApplied()
  {
    return this.isScoringApplied;
  }
  
  public void setIsScoringApplied(int isScoringApplied)
  {
    this.isScoringApplied = isScoringApplied;
  }
  
  public long getSuper_user_key()
  {
    return this.super_user_key;
  }
  
  public void setSuper_user_key(long superUserKey)
  {
    this.super_user_key = superUserKey;
  }
  
  public JobCategory getJobcategory()
  {
    return this.jobcategory;
  }
  
  public void setJobcategory(JobCategory jobcategory)
  {
    this.jobcategory = jobcategory;
    if (this.jobcategory != null) {
      this.jobcatIdValue = jobcategory.getJobCategoryId();
    }
  }
  
  public Designations getDesignation()
  {
    return this.designation;
  }
  
  public void setDesignation(Designations designation)
  {
    this.designation = designation;
    if (this.designation != null)
    {
      this.jobTitle = designation.getDesignationName();
      this.designationIdValue = designation.getDesignationId();
    }
    else
    {
      this.jobTitle = "";
    }
  }
  
  public long getRequisition_number()
  {
    return this.requisition_number;
  }
  
  public void setRequisition_number(long requisitionNumber)
  {
    this.requisition_number = requisitionNumber;
  }
  
  public int getTotalAppcount()
  {
    return this.totalAppcount;
  }
  
  public void setTotalAppcount(int totalAppcount)
  {
    this.totalAppcount = totalAppcount;
  }
  
  public String getStatusUiValue()
  {
    this.statusUiValue = (this.state + " > " + this.status);
    return this.statusUiValue;
  }
  
  public long getLocationIdValue()
  {
    return this.locationIdValue;
  }
  
  public void setLocationIdValue(long locationIdValue)
  {
    this.locationIdValue = locationIdValue;
  }
  
  public String getEeoInfoIncluded()
  {
    return this.eeoInfoIncluded;
  }
  
  public void setEeoInfoIncluded(String eeoInfoIncluded)
  {
    this.eeoInfoIncluded = eeoInfoIncluded;
  }
  
  public String getAllApplicantViewed()
  {
    return this.allApplicantViewed;
  }
  
  public void setAllApplicantViewed(String allApplicantViewed)
  {
    this.allApplicantViewed = allApplicantViewed;
  }
}
