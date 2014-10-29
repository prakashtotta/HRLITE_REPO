package com.bean;

import com.bean.lov.JobCategory;
import java.util.Date;
import java.util.List;

public class JobRequisionTemplate
{
  public long templateId;
  public String templateName;
  public String templateDesc;
  public User hiringmgr;
  public Department department;
  public ProjectCodes projectcode;
  public Location location;
  public JobType jobtype;
  public JobGrade jobgrade;
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
  public String jobRoles;
  public String departmentValue = "";
  public String projectcodeValue = "";
  public String organizationValue = "";
  public String hiringMgrValue = "";
  public String jobtypeValue = "";
  public String workshiftValue = "";
  public String locationValue = "";
  public List comptetencyList;
  public List accomplishmentList;
  public List approversList;
  public long refferalSchemeId;
  public String refferalSchemeName;
  public long agencyRefferalSchemeId;
  public String agencyRefferalSchemeName;
  public String stdworkinghoursunitName;
  public String otherMinimumLevelOfEducation;
  public long recruiterId;
  public String recruiterName;
  public long super_user_key;
  public JobCategory jobcategory;
  public Designations designation;
  
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
  
  public String getTemplateDesc()
  {
    return this.templateDesc;
  }
  
  public void setTemplateDesc(String templateDesc)
  {
    this.templateDesc = templateDesc;
  }
  
  public User getHiringmgr()
  {
    return this.hiringmgr;
  }
  
  public void setHiringmgr(User hiringmgr)
  {
    this.hiringmgr = hiringmgr;
    if (hiringmgr != null) {
      this.hiringMgrValue = (hiringmgr.getFirstName() + " " + hiringmgr.getLastName());
    }
  }
  
  public Department getDepartment()
  {
    return this.department;
  }
  
  public void setDepartment(Department department)
  {
    this.department = department;
    if (department != null) {
      this.departmentValue = department.getDepartmentName();
    }
  }
  
  public ProjectCodes getProjectcode()
  {
    return this.projectcode;
  }
  
  public void setProjectcode(ProjectCodes projectcode)
  {
    this.projectcode = projectcode;
    if (projectcode != null) {
      this.projectcodeValue = projectcode.getProjCode();
    }
  }
  
  public Location getLocation()
  {
    return this.location;
  }
  
  public void setLocation(Location location)
  {
    this.location = location;
    if (location != null) {
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
    if (jobtype != null) {
      this.jobtypeValue = jobtype.getJobTypeName();
    }
  }
  
  public JobGrade getJobgrade()
  {
    return this.jobgrade;
  }
  
  public void setJobgrade(JobGrade jobgrade)
  {
    this.jobgrade = jobgrade;
  }
  
  public SalaryPlan getSalaryplan()
  {
    return this.salaryplan;
  }
  
  public void setSalaryplan(SalaryPlan salaryplan)
  {
    this.salaryplan = salaryplan;
  }
  
  public WorkShift getWorkshift()
  {
    return this.workshift;
  }
  
  public void setWorkshift(WorkShift workshift)
  {
    this.workshift = workshift;
    if (workshift != null) {
      this.workshiftValue = workshift.getShiftName();
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
  }
  
  public CompFrequency getCompfrequency()
  {
    return this.compfrequency;
  }
  
  public void setCompfrequency(CompFrequency compfrequency)
  {
    this.compfrequency = compfrequency;
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
    if (organization != null) {
      this.organizationValue = organization.getOrgName();
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
  }
  
  public String getState()
  {
    return this.state;
  }
  
  public void setState(String state)
  {
    this.state = state;
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
  
  public String getJobRoles()
  {
    return this.jobRoles;
  }
  
  public void setJobRoles(String jobRoles)
  {
    this.jobRoles = jobRoles;
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
  }
  
  public Designations getDesignation()
  {
    return this.designation;
  }
  
  public void setDesignation(Designations designation)
  {
    this.designation = designation;
  }
}
