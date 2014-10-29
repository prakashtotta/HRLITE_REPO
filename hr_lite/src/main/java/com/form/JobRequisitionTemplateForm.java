package com.form;

import com.bean.BudgetCode;
import com.bean.CompFrequency;
import com.bean.Department;
import com.bean.Designations;
import com.bean.FlsaStatus;
import com.bean.JobGrade;
import com.bean.JobRequisionTemplate;
import com.bean.JobType;
import com.bean.Location;
import com.bean.Organization;
import com.bean.ProjectCodes;
import com.bean.SalaryPlan;
import com.bean.User;
import com.bean.WorkShift;
import com.bean.criteria.RequisitionTemplateSearchCriteriaMultiple;
import com.bean.filter.BusinessCriteria;
import com.bean.lov.JobCategory;
import com.bo.BOFactory;
import com.bo.OrganizationBO;
import com.util.StringUtils;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;

public class JobRequisitionTemplateForm
  extends ActionForm
{
  protected static final Logger logger = Logger.getLogger(JobRequisitionTemplateForm.class);
  public long templateId;
  public String templateName;
  public String templateDesc;
  public long hiringMgrId;
  public String hiringMgrName;
  public String hiringMgrLname;
  public long departmentId;
  public String departmentName;
  public long projectcodeId;
  public String projectcodeName;
  public String projCode;
  public long locationId;
  public String locationName;
  public long jobtypeId;
  public List jobtypeList;
  public long jobgradeId;
  public String jobgradeName;
  public long salaryplanId;
  public String salaryplanName;
  public long workshiftId;
  public List workshiftList;
  public int defaultStandardHours;
  public long flsastatusId;
  public List flsastatusList;
  public long compfrequencyId;
  public List compfrequencyList;
  public int durationinmonths;
  public int minyearsofExpRequired;
  public int maxyearsofExpRequired;
  public String minimumLevelOfEducation;
  public String otherExperience;
  public long budgetcodeId;
  public String budgetcodeName;
  public String internal = "N";
  public long parentOrgId;
  public String parentOrgName;
  public long evaluationTmplId;
  public String evaluationTmplName;
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
  public List comptetencyList;
  public List accomplishmentList;
  public List approversList;
  public List skillRatingList;
  public int minimumrating;
  public String iscompmandatory;
  public String competencyname;
  public String accomplishmentname;
  public int minimumratingaccom;
  public long approverId;
  public String approverName;
  public String statuscri;
  public List jobgradeList;
  public List organizationList;
  public List departmentList;
  public List projectcodeList;
  public List salaryplanList;
  public List budgetCodeList;
  public long refferalSchemeId;
  public long agencyRefferalSchemeId;
  public String refferalSchemeName;
  public String agencyRefferalSchemeName;
  public List refferalSchemeList;
  public List agencyRefferalSchemeList;
  public String compfrequency;
  public String jobTypeName;
  public String shiftName;
  public String flsaName;
  public String approverStatus;
  public String stdworkinghoursunitName;
  public List stdworkinghoursunitList;
  private List educationNamesList;
  private String educationName;
  public String otherMinimumLevelOfEducation;
  public List formVariablesList;
  public List formVariableDataList;
  public List errorList;
  public int tabselected = 1;
  public long recruiterId;
  public String recruiterName;
  public long jobCategoryId;
  private List jobCategoryList;
  public long designationId;
  public List designationList;
  private List<BusinessCriteria> filters;
  private RequisitionTemplateSearchCriteriaMultiple serachCriteria;
  private String keyword;
  public long[] orgIds;
  public long[] departmentIds;
  public long[] jobtypeIds;
  public long[] jobgradeIds;
  public String[] statuses;
  private List statusList;
  
  public RequisitionTemplateSearchCriteriaMultiple getSerachCriteria()
  {
    return this.serachCriteria;
  }
  
  public void setSerachCriteria(RequisitionTemplateSearchCriteriaMultiple serachCriteria)
  {
    this.serachCriteria = serachCriteria;
  }
  
  public String getKeyword()
  {
    return this.keyword;
  }
  
  public void setKeyword(String keyword)
  {
    this.keyword = keyword;
  }
  
  public long[] getOrgIds()
  {
    return this.orgIds;
  }
  
  public void setOrgIds(long[] orgIds)
  {
    this.orgIds = orgIds;
  }
  
  public long[] getDepartmentIds()
  {
    return this.departmentIds;
  }
  
  public void setDepartmentIds(long[] departmentIds)
  {
    this.departmentIds = departmentIds;
  }
  
  public long[] getJobtypeIds()
  {
    return this.jobtypeIds;
  }
  
  public void setJobtypeIds(long[] jobtypeIds)
  {
    this.jobtypeIds = jobtypeIds;
  }
  
  public long[] getJobgradeIds()
  {
    return this.jobgradeIds;
  }
  
  public void setJobgradeIds(long[] jobgradeIds)
  {
    this.jobgradeIds = jobgradeIds;
  }
  
  public String[] getStatuses()
  {
    return this.statuses;
  }
  
  public void setStatuses(String[] statuses)
  {
    this.statuses = statuses;
  }
  
  public List getStatusList()
  {
    return this.statusList;
  }
  
  public void setStatusList(List statusList)
  {
    this.statusList = statusList;
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
  
  public String getOtherMinimumLevelOfEducation()
  {
    return this.otherMinimumLevelOfEducation;
  }
  
  public void setOtherMinimumLevelOfEducation(String otherMinimumLevelOfEducation)
  {
    this.otherMinimumLevelOfEducation = otherMinimumLevelOfEducation;
  }
  
  public List getEducationNamesList()
  {
    return this.educationNamesList;
  }
  
  public void setEducationNamesList(List educationNamesList)
  {
    this.educationNamesList = educationNamesList;
  }
  
  public String getEducationName()
  {
    return this.educationName;
  }
  
  public void setEducationName(String educationName)
  {
    this.educationName = educationName;
  }
  
  public List getStdworkinghoursunitList()
  {
    return this.stdworkinghoursunitList;
  }
  
  public void setStdworkinghoursunitList(List stdworkinghoursunitList)
  {
    this.stdworkinghoursunitList = stdworkinghoursunitList;
  }
  
  public String getStdworkinghoursunitName()
  {
    return this.stdworkinghoursunitName;
  }
  
  public void setStdworkinghoursunitName(String stdworkinghoursunitName)
  {
    this.stdworkinghoursunitName = stdworkinghoursunitName;
  }
  
  public String getApproverStatus()
  {
    return this.approverStatus;
  }
  
  public void setApproverStatus(String approverStatus)
  {
    this.approverStatus = approverStatus;
  }
  
  public String getCompfrequency()
  {
    return this.compfrequency;
  }
  
  public void setCompfrequency(String compfrequency)
  {
    this.compfrequency = compfrequency;
  }
  
  public String getJobTypeName()
  {
    return this.jobTypeName;
  }
  
  public void setJobTypeName(String jobTypeName)
  {
    this.jobTypeName = jobTypeName;
  }
  
  public String getShiftName()
  {
    return this.shiftName;
  }
  
  public void setShiftName(String shiftName)
  {
    this.shiftName = shiftName;
  }
  
  public String getFlsaName()
  {
    return this.flsaName;
  }
  
  public void setFlsaName(String flsaName)
  {
    this.flsaName = flsaName;
  }
  
  public void toValue(JobRequisionTemplate jb, HttpServletRequest request)
    throws Exception
  {
    jb.setTemplateName(this.templateName);
    jb.setTemplateDesc(this.templateDesc);
    jb.setJobRoles(this.jobRoles);
    
    jb.setJobDetails(this.jobDetails);
    jb.setJobInstructions(this.jobInstructions);
    jb.setInternal(this.internal);
    jb.setDurationinmonths(this.durationinmonths);
    jb.setMinyearsofExpRequired(this.minyearsofExpRequired);
    jb.setMaxyearsofExpRequired(this.maxyearsofExpRequired);
    jb.setMinimumLevelOfEducation(this.minimumLevelOfEducation);
    jb.setOtherMinimumLevelOfEducation(this.otherMinimumLevelOfEducation);
    jb.setMinimumLevelOfEducation(this.educationName);
    jb.setOtherExperience(this.otherExperience);
    jb.setDefaultStandardHours(this.defaultStandardHours);
    jb.setRefferalSchemeId(this.refferalSchemeId);
    jb.setAgencyRefferalSchemeId(this.agencyRefferalSchemeId);
    jb.setRefferalSchemeName(getRefferalSchemeName());
    jb.setAgencyRefferalSchemeName(getAgencyRefferalSchemeName());
    jb.setStdworkinghoursunitName(getStdworkinghoursunitName());
    
    Organization org = new Organization();
    org.setOrgId(this.parentOrgId);
    jb.setOrganization(org);
    jb.setRecruiterId(this.recruiterId);
    jb.setRecruiterName(this.recruiterName);
    
    org = BOFactory.getOrganizationBO().getOrganization(String.valueOf(this.parentOrgId));
    if (this.departmentId != 0L)
    {
      Department dept = new Department();
      dept.setDepartmentId(this.departmentId);
      jb.setDepartment(dept);
    }
    else
    {
      jb.setDepartment(null);
    }
    if (this.projectcodeId != 0L)
    {
      ProjectCodes pjcode = new ProjectCodes();
      pjcode.setProjectId(this.projectcodeId);
      jb.setProjectcode(pjcode);
    }
    else
    {
      jb.setProjectcode(null);
    }
    Location location1 = org.getLocation();
    jb.setLocation(location1);
    if (this.compfrequencyId != 0L)
    {
      CompFrequency cmp = new CompFrequency();
      cmp.setCompFrequencyId(this.compfrequencyId);
      jb.setCompfrequency(cmp);
    }
    else
    {
      jb.setCompfrequency(null);
    }
    if (this.jobtypeId != 0L)
    {
      JobType jobtype = new JobType();
      jobtype.setJobTypeId(this.jobtypeId);
      jb.setJobtype(jobtype);
    }
    else
    {
      jb.setJobtype(null);
    }
    if (this.workshiftId != 0L)
    {
      WorkShift ws = new WorkShift();
      ws.setShiftId(this.workshiftId);
      jb.setWorkshift(ws);
    }
    else
    {
      jb.setWorkshift(null);
    }
    if (this.flsastatusId != 0L)
    {
      FlsaStatus fl = new FlsaStatus();
      fl.setFlsaId(this.flsastatusId);
      jb.setFlsa(fl);
    }
    else
    {
      jb.setFlsa(null);
    }
    if (this.hiringMgrId != 0L)
    {
      User hiringmgr = new User();
      hiringmgr.setUserId(this.hiringMgrId);
      jb.setHiringmgr(hiringmgr);
    }
    else
    {
      jb.setHiringmgr(null);
    }
    if (this.projectcodeId != 0L)
    {
      ProjectCodes pcode = new ProjectCodes();
      pcode.setProjectId(this.projectcodeId);
      jb.setProjectcode(pcode);
    }
    else
    {
      jb.setProjectcode(null);
    }
    if (this.jobgradeId != 0L)
    {
      JobGrade jgrade = new JobGrade();
      jgrade.setJobgradeId(this.jobgradeId);
      jb.setJobgrade(jgrade);
    }
    else
    {
      jb.setJobgrade(null);
    }
    if (this.salaryplanId != 0L)
    {
      SalaryPlan splan = new SalaryPlan();
      splan.setSalaryplanId(this.salaryplanId);
      jb.setSalaryplan(splan);
    }
    else
    {
      jb.setSalaryplan(null);
    }
    if (this.budgetcodeId != 0L)
    {
      BudgetCode bcode = new BudgetCode();
      bcode.setBudgetId(this.budgetcodeId);
      jb.setBudgetcode(bcode);
    }
    else
    {
      jb.setBudgetcode(null);
    }
    if (this.jobCategoryId != 0L)
    {
      JobCategory jcat = new JobCategory();
      jcat.setJobCategoryId(this.jobCategoryId);
      jb.setJobcategory(jcat);
    }
    if (this.designationId != 0L)
    {
      Designations des = new Designations();
      des.setDesignationId(this.designationId);
      jb.setDesignation(des);
    }
    User user1 = (User)request.getSession().getAttribute("user_data");
    
    Map m = VariableDataCaptureUtil.captureCustomVariables(request, this.templateId, "REQUISITION_TMPL_FORM", user1.getSuper_user_key());
    
    this.formVariablesList = ((List)m.get("formVariablesList"));
    logger.info("this.formVariablesList" + this.formVariablesList);
    this.errorList = ((List)m.get("errorList"));
    this.formVariableDataList = ((List)m.get("formVariableDataList"));
  }
  
  public void fromValue(JobRequisionTemplate jb, HttpServletRequest request)
    throws Exception
  {
    User user1 = (User)request.getSession().getAttribute("user_data");
    setTemplateId(jb.getTemplateId());
    setTemplateName(jb.getTemplateName());
    setTemplateDesc(jb.getTemplateDesc());
    setJobRoles(jb.getJobRoles());
    
    setJobDetails(jb.getJobDetails());
    setJobInstructions(jb.getJobInstructions());
    setInternal(jb.getInternal());
    setDurationinmonths(jb.getDurationinmonths());
    setMinyearsofExpRequired(jb.getMinyearsofExpRequired());
    setMaxyearsofExpRequired(jb.getMaxyearsofExpRequired());
    setMinimumLevelOfEducation(jb.getMinimumLevelOfEducation());
    setOtherMinimumLevelOfEducation(jb.getOtherMinimumLevelOfEducation());
    setEducationName(jb.getMinimumLevelOfEducation());
    setOtherExperience(jb.getOtherExperience());
    setDefaultStandardHours(jb.getDefaultStandardHours());
    setRefferalSchemeId(jb.refferalSchemeId);
    setRefferalSchemeName(jb.getRefferalSchemeName());
    setAgencyRefferalSchemeId(jb.agencyRefferalSchemeId);
    setAgencyRefferalSchemeName(jb.getAgencyRefferalSchemeName());
    setStdworkinghoursunitName(jb.getStdworkinghoursunitName());
    this.recruiterId = jb.getRecruiterId();
    this.recruiterName = jb.getRecruiterName();
    if (jb.getOrganization() != null)
    {
      setParentOrgId(jb.getOrganization().getOrgId());
      setParentOrgName(jb.getOrganization().getOrgName());
    }
    if (jb.getLocation() != null)
    {
      setLocationId(jb.getLocation().getLocationId());
      setLocationName(jb.getLocation().getLocationName());
    }
    if (jb.getCompfrequency() != null)
    {
      this.compfrequencyId = jb.getCompfrequency().getCompFrequencyId();
      this.compfrequency = jb.getCompfrequency().getCompFrequencyName();
    }
    if (jb.getJobtype() != null)
    {
      this.jobtypeId = jb.getJobtype().getJobTypeId();
      this.jobTypeName = jb.getJobtype().getJobTypeName();
    }
    if (jb.getWorkshift() != null)
    {
      this.workshiftId = jb.getWorkshift().getShiftId();
      this.shiftName = jb.getWorkshift().getShiftName();
    }
    if (jb.getFlsa() != null)
    {
      this.flsastatusId = jb.getFlsa().getFlsaId();
      this.flsaName = jb.getFlsa().getFlsaName();
    }
    if (jb.getDepartment() != null)
    {
      this.departmentId = jb.getDepartment().getDepartmentId();
      this.departmentName = jb.getDepartment().getDepartmentName();
    }
    if (jb.getHiringmgr() != null)
    {
      this.hiringMgrId = jb.getHiringmgr().getUserId();
      this.hiringMgrName = (jb.getHiringmgr().getFirstName() + " " + jb.getHiringmgr().getLastName());
    }
    if (jb.getProjectcode() != null)
    {
      this.projectcodeId = jb.getProjectcode().getProjectId();
      this.projectcodeName = jb.getProjectcode().getProjCode();
    }
    if (jb.getJobgrade() != null)
    {
      this.jobgradeId = jb.getJobgrade().getJobgradeId();
      this.jobgradeName = jb.getJobgrade().getJobGradeName();
    }
    if (jb.getSalaryplan() != null)
    {
      this.salaryplanId = jb.getSalaryplan().getSalaryplanId();
      this.salaryplanName = jb.getSalaryplan().getSalaryPlanName();
    }
    if (jb.getBudgetcode() != null)
    {
      this.budgetcodeId = jb.getBudgetcode().getBudgetId();
      this.budgetcodeName = jb.getBudgetcode().getBudgetCode();
    }
    if (jb.getDesignation() != null) {
      this.designationId = jb.getDesignation().getDesignationId();
    }
    if (jb.getJobcategory() != null) {
      this.jobCategoryId = jb.getJobcategory().getJobCategoryId();
    }
    this.status = jb.getStatus();
    this.state = jb.getState();
    

    String saverequisition = (String)request.getAttribute("savetemplate");
    if ((StringUtils.isNullOrEmpty(saverequisition)) || (!saverequisition.equalsIgnoreCase("no"))) {
      this.formVariablesList = VariableDataCaptureUtil.fromValueCustomVariables(request, this.templateId, "REQUISITION_TMPL_FORM", user1.getSuper_user_key());
    }
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
  
  public long getHiringMgrId()
  {
    return this.hiringMgrId;
  }
  
  public void setHiringMgrId(long hiringMgrId)
  {
    this.hiringMgrId = hiringMgrId;
  }
  
  public String getHiringMgrName()
  {
    return this.hiringMgrName;
  }
  
  public void setHiringMgrName(String hiringMgrName)
  {
    this.hiringMgrName = hiringMgrName;
  }
  
  public long getDepartmentId()
  {
    return this.departmentId;
  }
  
  public void setDepartmentId(long departmentId)
  {
    this.departmentId = departmentId;
  }
  
  public String getDepartmentName()
  {
    return this.departmentName;
  }
  
  public void setDepartmentName(String departmentName)
  {
    this.departmentName = departmentName;
  }
  
  public long getProjectcodeId()
  {
    return this.projectcodeId;
  }
  
  public void setProjectcodeId(long projectcodeId)
  {
    this.projectcodeId = projectcodeId;
  }
  
  public String getProjectcodeName()
  {
    return this.projectcodeName;
  }
  
  public void setProjectcodeName(String projectcodeName)
  {
    this.projectcodeName = projectcodeName;
  }
  
  public long getLocationId()
  {
    return this.locationId;
  }
  
  public void setLocationId(long locationId)
  {
    this.locationId = locationId;
  }
  
  public String getLocationName()
  {
    return this.locationName;
  }
  
  public void setLocationName(String locationName)
  {
    this.locationName = locationName;
  }
  
  public long getJobtypeId()
  {
    return this.jobtypeId;
  }
  
  public void setJobtypeId(long jobtypeId)
  {
    this.jobtypeId = jobtypeId;
  }
  
  public List getJobtypeList()
  {
    return this.jobtypeList;
  }
  
  public void setJobtypeList(List jobtypeList)
  {
    this.jobtypeList = jobtypeList;
  }
  
  public long getJobgradeId()
  {
    return this.jobgradeId;
  }
  
  public void setJobgradeId(long jobgradeId)
  {
    this.jobgradeId = jobgradeId;
  }
  
  public String getJobgradeName()
  {
    return this.jobgradeName;
  }
  
  public void setJobgradeName(String jobgradeName)
  {
    this.jobgradeName = jobgradeName;
  }
  
  public long getSalaryplanId()
  {
    return this.salaryplanId;
  }
  
  public void setSalaryplanId(long salaryplanId)
  {
    this.salaryplanId = salaryplanId;
  }
  
  public String getSalaryplanName()
  {
    return this.salaryplanName;
  }
  
  public void setSalaryplanName(String salaryplanName)
  {
    this.salaryplanName = salaryplanName;
  }
  
  public long getWorkshiftId()
  {
    return this.workshiftId;
  }
  
  public void setWorkshiftId(long workshiftId)
  {
    this.workshiftId = workshiftId;
  }
  
  public List getWorkshiftList()
  {
    return this.workshiftList;
  }
  
  public void setWorkshiftList(List workshiftList)
  {
    this.workshiftList = workshiftList;
  }
  
  public int getDefaultStandardHours()
  {
    return this.defaultStandardHours;
  }
  
  public void setDefaultStandardHours(int defaultStandardHours)
  {
    this.defaultStandardHours = defaultStandardHours;
  }
  
  public long getFlsastatusId()
  {
    return this.flsastatusId;
  }
  
  public void setFlsastatusId(long flsastatusId)
  {
    this.flsastatusId = flsastatusId;
  }
  
  public List getFlsastatusList()
  {
    return this.flsastatusList;
  }
  
  public void setFlsastatusList(List flsastatusList)
  {
    this.flsastatusList = flsastatusList;
  }
  
  public long getCompfrequencyId()
  {
    return this.compfrequencyId;
  }
  
  public void setCompfrequencyId(long compfrequencyId)
  {
    this.compfrequencyId = compfrequencyId;
  }
  
  public List getCompfrequencyList()
  {
    return this.compfrequencyList;
  }
  
  public void setCompfrequencyList(List compfrequencyList)
  {
    this.compfrequencyList = compfrequencyList;
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
  
  public long getBudgetcodeId()
  {
    return this.budgetcodeId;
  }
  
  public void setBudgetcodeId(long budgetcodeId)
  {
    this.budgetcodeId = budgetcodeId;
  }
  
  public String getBudgetcodeName()
  {
    return this.budgetcodeName;
  }
  
  public void setBudgetcodeName(String budgetcodeName)
  {
    this.budgetcodeName = budgetcodeName;
  }
  
  public String getInternal()
  {
    return this.internal;
  }
  
  public void setInternal(String internal)
  {
    this.internal = internal;
  }
  
  public long getParentOrgId()
  {
    return this.parentOrgId;
  }
  
  public void setParentOrgId(long parentOrgId)
  {
    this.parentOrgId = parentOrgId;
  }
  
  public String getParentOrgName()
  {
    return this.parentOrgName;
  }
  
  public void setParentOrgName(String parentOrgName)
  {
    this.parentOrgName = parentOrgName;
  }
  
  public long getEvaluationTmplId()
  {
    return this.evaluationTmplId;
  }
  
  public void setEvaluationTmplId(long evaluationTmplId)
  {
    this.evaluationTmplId = evaluationTmplId;
  }
  
  public String getEvaluationTmplName()
  {
    return this.evaluationTmplName;
  }
  
  public void setEvaluationTmplName(String evaluationTmplName)
  {
    this.evaluationTmplName = evaluationTmplName;
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
  
  public String getStatuscri()
  {
    return this.statuscri;
  }
  
  public void setStatuscri(String statuscri)
  {
    this.statuscri = statuscri;
  }
  
  public List getJobgradeList()
  {
    return this.jobgradeList;
  }
  
  public void setJobgradeList(List jobgradeList)
  {
    this.jobgradeList = jobgradeList;
  }
  
  public List getOrganizationList()
  {
    return this.organizationList;
  }
  
  public void setOrganizationList(List organizationList)
  {
    this.organizationList = organizationList;
  }
  
  public List getDepartmentList()
  {
    return this.departmentList;
  }
  
  public void setDepartmentList(List departmentList)
  {
    this.departmentList = departmentList;
  }
  
  public List getSkillRatingList()
  {
    return this.skillRatingList;
  }
  
  public void setSkillRatingList(List skillRatingList)
  {
    this.skillRatingList = skillRatingList;
  }
  
  public int getMinimumrating()
  {
    return this.minimumrating;
  }
  
  public void setMinimumrating(int minimumrating)
  {
    this.minimumrating = minimumrating;
  }
  
  public String getIscompmandatory()
  {
    return this.iscompmandatory;
  }
  
  public void setIscompmandatory(String iscompmandatory)
  {
    this.iscompmandatory = iscompmandatory;
  }
  
  public String getCompetencyname()
  {
    return this.competencyname;
  }
  
  public void setCompetencyname(String competencyname)
  {
    this.competencyname = competencyname;
  }
  
  public String getAccomplishmentname()
  {
    return this.accomplishmentname;
  }
  
  public void setAccomplishmentname(String accomplishmentname)
  {
    this.accomplishmentname = accomplishmentname;
  }
  
  public int getMinimumratingaccom()
  {
    return this.minimumratingaccom;
  }
  
  public void setMinimumratingaccom(int minimumratingaccom)
  {
    this.minimumratingaccom = minimumratingaccom;
  }
  
  public long getApproverId()
  {
    return this.approverId;
  }
  
  public void setApproverId(long approverId)
  {
    this.approverId = approverId;
  }
  
  public String getApproverName()
  {
    return this.approverName;
  }
  
  public void setApproverName(String approverName)
  {
    this.approverName = approverName;
  }
  
  public List getProjectcodeList()
  {
    return this.projectcodeList;
  }
  
  public void setProjectcodeList(List projectcodeList)
  {
    this.projectcodeList = projectcodeList;
  }
  
  public String getProjCode()
  {
    return this.projCode;
  }
  
  public void setProjCode(String projCode)
  {
    this.projCode = projCode;
  }
  
  public String getHiringMgrLname()
  {
    return this.hiringMgrLname;
  }
  
  public void setHiringMgrLname(String hiringMgrLname)
  {
    this.hiringMgrLname = hiringMgrLname;
  }
  
  public List getBudgetCodeList()
  {
    return this.budgetCodeList;
  }
  
  public void setBudgetCodeList(List budgetCodeList)
  {
    this.budgetCodeList = budgetCodeList;
  }
  
  public List getSalaryplanList()
  {
    return this.salaryplanList;
  }
  
  public void setSalaryplanList(List salaryplanList)
  {
    this.salaryplanList = salaryplanList;
  }
  
  public long getRefferalSchemeId()
  {
    return this.refferalSchemeId;
  }
  
  public void setRefferalSchemeId(long refferalSchemeId)
  {
    this.refferalSchemeId = refferalSchemeId;
  }
  
  public long getAgencyRefferalSchemeId()
  {
    return this.agencyRefferalSchemeId;
  }
  
  public void setAgencyRefferalSchemeId(long agencyRefferalSchemeId)
  {
    this.agencyRefferalSchemeId = agencyRefferalSchemeId;
  }
  
  public List getRefferalSchemeList()
  {
    return this.refferalSchemeList;
  }
  
  public void setRefferalSchemeList(List refferalSchemeList)
  {
    this.refferalSchemeList = refferalSchemeList;
  }
  
  public List getAgencyRefferalSchemeList()
  {
    return this.agencyRefferalSchemeList;
  }
  
  public void setAgencyRefferalSchemeList(List agencyRefferalSchemeList)
  {
    this.agencyRefferalSchemeList = agencyRefferalSchemeList;
  }
  
  public String getRefferalSchemeName()
  {
    return this.refferalSchemeName;
  }
  
  public void setRefferalSchemeName(String refferalSchemeName)
  {
    this.refferalSchemeName = refferalSchemeName;
  }
  
  public String getAgencyRefferalSchemeName()
  {
    return this.agencyRefferalSchemeName;
  }
  
  public void setAgencyRefferalSchemeName(String agencyRefferalSchemeName)
  {
    this.agencyRefferalSchemeName = agencyRefferalSchemeName;
  }
  
  public List getFormVariablesList()
  {
    return this.formVariablesList;
  }
  
  public void setFormVariablesList(List formVariablesList)
  {
    this.formVariablesList = formVariablesList;
  }
  
  public List getFormVariableDataList()
  {
    return this.formVariableDataList;
  }
  
  public void setFormVariableDataList(List formVariableDataList)
  {
    this.formVariableDataList = formVariableDataList;
  }
  
  public List getErrorList()
  {
    return this.errorList;
  }
  
  public void setErrorList(List errorList)
  {
    this.errorList = errorList;
  }
  
  public int getTabselected()
  {
    return this.tabselected;
  }
  
  public void setTabselected(int tabselected)
  {
    this.tabselected = tabselected;
  }
  
  public List<BusinessCriteria> getFilters()
  {
    return this.filters;
  }
  
  public void setFilters(List<BusinessCriteria> filters)
  {
    this.filters = filters;
  }
  
  public long getJobCategoryId()
  {
    return this.jobCategoryId;
  }
  
  public void setJobCategoryId(long jobCategoryId)
  {
    this.jobCategoryId = jobCategoryId;
  }
  
  public List getJobCategoryList()
  {
    return this.jobCategoryList;
  }
  
  public void setJobCategoryList(List jobCategoryList)
  {
    this.jobCategoryList = jobCategoryList;
  }
  
  public long getDesignationId()
  {
    return this.designationId;
  }
  
  public void setDesignationId(long designationId)
  {
    this.designationId = designationId;
  }
  
  public List getDesignationList()
  {
    return this.designationList;
  }
  
  public void setDesignationList(List designationList)
  {
    this.designationList = designationList;
  }
}
