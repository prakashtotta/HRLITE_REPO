package com.form;

import com.bean.BudgetCode;
import com.bean.CompFrequency;
import com.bean.Department;
import com.bean.Designations;
import com.bean.FlsaStatus;
import com.bean.JobGrade;
import com.bean.JobRequisition;
import com.bean.JobType;
import com.bean.Location;
import com.bean.Organization;
import com.bean.ProjectCodes;
import com.bean.RefferalScheme;
import com.bean.SalaryPlan;
import com.bean.User;
import com.bean.WorkShift;
import com.bean.criteria.RequisitionSearchCriteriaMultiple;
import com.bean.criteria.SearchCustomFields;
import com.bean.filter.BusinessCriteria;
import com.bean.lov.Category;
import com.bean.lov.JobCategory;
import com.resources.Constant;
import com.util.DateUtil;
import com.util.HTMLInputFilter;
import com.util.StringUtils;
import com.util.XSSKeyUtil;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class JobRequisitionForm
  extends ActionForm
{
  protected static final Logger logger = Logger.getLogger(JobRequisitionForm.class);
  public long jobreqId;
  public long requisition_number;
  public String uuid;
  public String jobreqName;
  public String jobTitle;
  public String jobreqDesc;
  public long hiringMgrId;
  public String hiringMgrName;
  public long departmentId;
  public String departmentName;
  public long projectcodeId;
  public String projectcodeName;
  public long locationId;
  public String locationName;
  public long jobtypeId;
  public String jobtype;
  public List jobtypeList;
  public long jobgradeId;
  public String jobgradeName;
  public long jobcodeId;
  public String jobcodeName;
  public long salaryplanId;
  public String salaryplanName;
  public long workshiftId;
  public List workshiftList;
  public String workshift;
  public int defaultStandardHours;
  public long flsastatusId;
  public List flsastatusList;
  public String flsastatus;
  public long compfrequencyId;
  public List compfrequencyList;
  public String compfrequency;
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
  public String jobPosition;
  public String jobRoles;
  public String jobreqcode;
  public long recruiterId;
  public String recruiterName;
  public String isgrouprecruiter = "N";
  private String targetfinishdate = "";
  private String primarySkill;
  public int numberOfOpening;
  public int numberOfOpeningRemain;
  public int isapprovalInitiated;
  public String isnewPositions = "N";
  public long templateId;
  public String templateName;
  public long categoryId;
  public String categoryName;
  private List categoryList;
  public long jobCategoryId;
  private List jobCategoryList;
  public long designationId;
  public List designationList;
  public List comptetencyList;
  public List accomplishmentList;
  public List approversList;
  public int isrejected = 0;
  public int tabselected = 1;
  public long approvalIniById;
  public String approvalIniByName;
  public Date approvalIniDate;
  public String approvalInitiationComment;
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
  private String scoringKeyword;
  private String searchposteddate = "";
  private String appliedcri;
  private List locationList;
  public List projectcodeList;
  public String statuscri;
  public String statecri;
  public List jobgradeList;
  public List organizationList;
  public List departmentList;
  public List budgetcodeList;
  public List applicantList;
  public List salaryplanList;
  public List primarySkillList;
  private User recruiter;
  private String attahmentdetails;
  private FormFile attachmentdata;
  private List attachmentList;
  private String visibleInJobDetails;
  public long refferalSchemeId;
  public String refferalSchemeName;
  public List refferalSchemeList;
  public long agencyRefferalSchemeId;
  public String agencyRefferalSchemeName;
  public List agencyRefferalSchemeList;
  Set<RefferalScheme> employeeReferralSchemeList;
  Set<RefferalScheme> agencyReferralSchemeList;
  private List yesNoList;
  public String currecyCode;
  public List skillRatingList;
  public int minimumrating;
  public String iscompmandatory;
  public String competencyname;
  public String accomplishmentname;
  public int minimumratingaccom;
  public long approverId;
  public String approverName;
  public String jobTypeName;
  public String shiftName;
  public String flsaName;
  public String approverStatus;
  public String offerapplicantStatus;
  public List jobTemplateList;
  public String stdworkinghoursunitName;
  public List stdworkinghoursunitList;
  private List educationNamesList;
  public String educationName;
  public String otherMinimumLevelOfEducation;
  public List publishToVendorList;
  private String publishToEmpRef;
  private String publishToExternal;
  private String eeoInfoIncluded = "Y";
  public List formVariablesList;
  public List formVariableDataList;
  public List errorList;
  public String isnewpositionno;
  public List activityList;
  public long currentOwnerId;
  public String isGroup = "N";
  public String currentOwnerName;
  private List resumeSourceList;
  private List commentList;
  public String comment;
  public int mockexamsetId;
  private double passPercentage;
  public String mcomckexamsetcomment;
  public long questiongroupId;
  public String questiongroupcomment;
  private List questionGroupList;
  private List mockexamsList;
  public String examName;
  public String questiongroupName;
  Map<Long, SearchCustomFields> customFieldData = new HashMap();
  List<SearchCustomFields> customFieldCriList;
  public String backurl;
  private List<BusinessCriteria> filters;
  private RequisitionSearchCriteriaMultiple serachCriteria;
  public long[] departmentIds;
  public long[] jobtypeIds;
  public long[] jobgradeIds;
  public long[] budgetcodeIds;
  public long[] orgIds;
  public String[] statuses;
  public String[] states;
  public String[] primarySkills;
  public long[] locationIds;
  private List statusList;
  private List stateList;
  public long[] categoryIds;
  public long[] workshiftIds;
  private String keyword;
  private String keyword1;
  public String isinternal;
  public String isnewposition;
  public String ispublishredtoexternal;
  public String ispublishredtoemployee;
  public long reqexqnid;
  public List publishjobboardlist;
  
  public List getPublishjobboardlist()
  {
    return this.publishjobboardlist;
  }
  
  public void setPublishjobboardlist(List publishjobboardlist)
  {
    this.publishjobboardlist = publishjobboardlist;
  }
  
  public String getQuestiongroupName()
  {
    return this.questiongroupName;
  }
  
  public void setQuestiongroupName(String questiongroupName)
  {
    this.questiongroupName = questiongroupName;
  }
  
  public String getExamName()
  {
    return this.examName;
  }
  
  public void setExamName(String examName)
  {
    this.examName = examName;
  }
  
  public long getReqexqnid()
  {
    return this.reqexqnid;
  }
  
  public void setReqexqnid(long reqexqnid)
  {
    this.reqexqnid = reqexqnid;
  }
  
  public String getIsnewpositionno()
  {
    return this.isnewpositionno;
  }
  
  public void setIsnewpositionno(String isnewpositionno)
  {
    this.isnewpositionno = isnewpositionno;
  }
  
  public List getPublishToVendorList()
  {
    return this.publishToVendorList;
  }
  
  public void setPublishToVendorList(List publishToVendorList)
  {
    this.publishToVendorList = publishToVendorList;
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
  
  public List getJobTemplateList()
  {
    return this.jobTemplateList;
  }
  
  public void setJobTemplateList(List jobTemplateList)
  {
    this.jobTemplateList = jobTemplateList;
  }
  
  public void toValue(JobRequisition jb, HttpServletRequest request)
    throws Exception
  {
    HTMLInputFilter filter = new HTMLInputFilter();
    
    List xxsList = XSSKeyUtil.getListOfValuesByKey("JobRequisitionForm");
    if (xxsList.contains("jobreqName")) {
      jb.setJobreqName(filter.filter(this.jobreqName));
    } else {
      jb.setJobreqName(this.jobreqName);
    }
    jb.setJobreqDesc(this.jobreqDesc);
    jb.setJobRoles(this.jobRoles);
    jb.setJobPosition(this.jobPosition);
    jb.setJobDetails(this.jobDetails);
    jb.setJobInstructions(this.jobInstructions);
    if (StringUtils.isNullOrEmpty(this.internal)) {
      jb.setInternal("N");
    } else {
      jb.setInternal(this.internal);
    }
    jb.setDurationinmonths(this.durationinmonths);
    jb.setMinyearsofExpRequired(this.minyearsofExpRequired);
    jb.setMaxyearsofExpRequired(this.maxyearsofExpRequired);
    jb.setMinimumLevelOfEducation(this.minimumLevelOfEducation);
    jb.setOtherMinimumLevelOfEducation(this.otherMinimumLevelOfEducation);
    jb.setMinimumLevelOfEducation(this.educationName);
    jb.setOtherExperience(this.otherExperience);
    jb.setDefaultStandardHours(this.defaultStandardHours);
    jb.setStdworkinghoursunitName(getStdworkinghoursunitName());
    jb.setNumberOfOpening(this.numberOfOpening);
    jb.setNumberOfOpeningRemain(jb.getNumberOfOpeningRemain());
    if (StringUtils.isNullOrEmpty(this.isnewPositions)) {
      jb.setIsnewPositions("N");
    } else {
      jb.setIsnewPositions(this.isnewPositions);
    }
    jb.setPrimarySkill(this.primarySkill);
    jb.setRecruiterId(this.recruiterId);
    jb.setRecruiterName(this.recruiterName);
    jb.setIsgrouprecruiter(this.isgrouprecruiter);
    jb.setIsnewpositionno(this.isnewpositionno);
    
    Organization org = new Organization();
    org.setOrgId(this.parentOrgId);
    jb.setOrganization(org);
    jb.setScoringKeyword(this.scoringKeyword);
    if (this.locationId != 0L)
    {
      Location location = new Location();
      location.setLocationId(this.locationId);
      jb.setLocation(location);
    }
    if (this.departmentId != 0L)
    {
      Department dept = new Department();
      dept.setDepartmentId(this.departmentId);
      jb.setDepartment(dept);
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
    if (this.categoryId != 0L)
    {
      Category cat = new Category();
      cat.setCatId(this.categoryId);
      jb.setCatagory(cat);
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
    
    Map m = VariableDataCaptureUtil.captureCustomVariables(request, this.jobreqId, "REQUISITION_FORM", user1.getSuper_user_key());
    
    this.formVariablesList = ((List)m.get("formVariablesList"));
    logger.info("this.formVariablesList" + this.formVariablesList);
    this.errorList = ((List)m.get("errorList"));
    this.formVariableDataList = ((List)m.get("formVariableDataList"));
  }
  
  public void fromValue(JobRequisition jb, HttpServletRequest request)
    throws Exception
  {
    User user1 = (User)request.getSession().getAttribute("user_data");
    this.jobreqId = jb.getJobreqId();
    this.requisition_number = jb.getRequisition_number();
    setUuid(jb.getUuid());
    setTemplateId(jb.getTemplateId());
    setTemplateName(jb.getTemplateName());
    setJobreqName(jb.getJobreqName());
    setJobTitle(jb.getJobTitle());
    setJobreqDesc(jb.getJobreqDesc());
    setJobRoles(jb.getJobRoles());
    setJobPosition(jb.getJobPosition());
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
    setNumberOfOpening(jb.getNumberOfOpening());
    setNumberOfOpeningRemain(jb.getNumberOfOpeningRemain());
    setIsnewPositions(jb.getIsnewPositions());
    setIsapprovalInitiated(jb.getIsapprovalInitiated());
    setRefferalSchemeId(jb.refferalSchemeId);
    setRefferalSchemeName(jb.refferalSchemeName);
    setAgencyRefferalSchemeId(jb.agencyRefferalSchemeId);
    setAgencyRefferalSchemeName(jb.agencyRefferalSchemeName);
    setState(jb.getState());
    setStatus(jb.getStatus());
    setIsrejected(jb.getIsrejected());
    setApprovalIniById(jb.getApprovalIniById());
    setApprovalIniByName(jb.getApprovalIniByName());
    setApprovalIniDate(jb.getApprovalIniDate());
    setApprovalInitiationComment(jb.getApprovalInitiationComment());
    setCurrentOwnerId(jb.getCurrentOwnerId());
    setCurrentOwnerName(jb.getCurrentOwnerName());
    this.jobreqcode = jb.getJobreqcode();
    this.recruiterId = jb.getRecruiterId();
    this.recruiterName = jb.getRecruiterName();
    this.isgrouprecruiter = jb.getIsgrouprecruiter();
    setStdworkinghoursunitName(jb.getStdworkinghoursunitName());
    setIsnewpositionno(jb.getIsnewpositionno());
    this.createdBy = jb.getCreatedBy();
    this.primarySkill = jb.getPrimarySkill();
    if (jb.getTargetfinishdate() != null)
    {
      String datepattern = Constant.getValue("defaultdateformat");
      if (user1 != null) {
        datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
      }
      this.targetfinishdate = DateUtil.convertDateToStringDate(jb.getTargetfinishdate(), datepattern);
    }
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
      this.jobtype = jb.getJobtype().getJobTypeName();
    }
    if (jb.getWorkshift() != null)
    {
      this.workshiftId = jb.getWorkshift().getShiftId();
      this.workshift = jb.getWorkshift().getShiftName();
    }
    if (jb.getFlsa() != null)
    {
      this.flsastatusId = jb.getFlsa().getFlsaId();
      this.flsastatus = jb.getFlsa().getFlsaName();
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
      logger.info("hiringMgrName in form" + this.hiringMgrName);
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
    this.closedById = jb.getClosedById();
    this.closedByName = jb.getClosedByName();
    this.closedDate = jb.getClosedDate();
    this.closedComment = jb.getClosedComment();
    
    this.deleteById = jb.getDeleteById();
    this.deleteByName = jb.getDeleteByName();
    this.deleteDate = jb.getDeleteDate();
    this.deleteComment = jb.getDeleteComment();
    
    this.publishedById = jb.getPublishedById();
    this.publishedByName = jb.getPublishedByName();
    this.publishedDate = jb.getPublishedDate();
    this.publishedComment = jb.getPublishedComment();
    this.publishToEmpRef = jb.getPublishToEmpRef();
    this.publishToExternal = jb.getPublishToExternal();
    this.eeoInfoIncluded = jb.getEeoInfoIncluded();
    if (jb.getCatagory() != null)
    {
      this.categoryId = jb.getCatagory().getCatId();
      this.categoryName = jb.getCatagory().getCatName();
    }
    if (jb.getDesignation() != null)
    {
      this.designationId = jb.getDesignation().getDesignationId();
      this.jobTitle = jb.getDesignation().getDesignationName();
    }
    if (jb.getJobcategory() != null) {
      this.jobCategoryId = jb.getJobcategory().getJobCategoryId();
    }
    this.isGroup = jb.getIsGroup();
    

    this.employeeReferralSchemeList = jb.getEmployeeReferralSchemeList();
    this.agencyReferralSchemeList = jb.getAgencyReferralSchemeList();
    this.scoringKeyword = jb.getScoringKeyword();
    
    long superUserKey = 0L;
    if (user1 != null) {
      superUserKey = user1.getSuper_user_key();
    } else {
      superUserKey = jb.getSuper_user_key();
    }
    String saverequisition = (String)request.getAttribute("saverequisition");
    if ((StringUtils.isNullOrEmpty(saverequisition)) || (!saverequisition.equalsIgnoreCase("no"))) {
      this.formVariablesList = VariableDataCaptureUtil.fromValueCustomVariables(request, this.jobreqId, "REQUISITION_FORM", superUserKey);
    }
    logger.info("every thing fine here");
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
  
  public long getJobcodeId()
  {
    return this.jobcodeId;
  }
  
  public void setJobcodeId(long jobcodeId)
  {
    this.jobcodeId = jobcodeId;
  }
  
  public String getJobcodeName()
  {
    return this.jobcodeName;
  }
  
  public void setJobcodeName(String jobcodeName)
  {
    this.jobcodeName = jobcodeName;
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
  
  public int getIsrejected()
  {
    return this.isrejected;
  }
  
  public void setIsrejected(int isrejected)
  {
    this.isrejected = isrejected;
  }
  
  public int getTabselected()
  {
    return this.tabselected;
  }
  
  public void setTabselected(int tabselected)
  {
    this.tabselected = tabselected;
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
  
  public String getApprovalInitiationComment()
  {
    return this.approvalInitiationComment;
  }
  
  public void setApprovalInitiationComment(String approvalInitiationComment)
  {
    this.approvalInitiationComment = approvalInitiationComment;
  }
  
  public long getCurrentOwnerId()
  {
    return this.currentOwnerId;
  }
  
  public void setCurrentOwnerId(long currentOwnerId)
  {
    this.currentOwnerId = currentOwnerId;
  }
  
  public String getTargetfinishdate()
  {
    return this.targetfinishdate;
  }
  
  public void setTargetfinishdate(String targetfinishdate)
  {
    this.targetfinishdate = targetfinishdate;
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
  
  public String getSearchposteddate()
  {
    return this.searchposteddate;
  }
  
  public void setSearchposteddate(String searchposteddate)
  {
    this.searchposteddate = searchposteddate;
  }
  
  public String getAppliedcri()
  {
    return this.appliedcri;
  }
  
  public void setAppliedcri(String appliedcri)
  {
    this.appliedcri = appliedcri;
  }
  
  public List getLocationList()
  {
    return this.locationList;
  }
  
  public void setLocationList(List locationList)
  {
    this.locationList = locationList;
  }
  
  public String getJobtype()
  {
    return this.jobtype;
  }
  
  public void setJobtype(String jobtype)
  {
    this.jobtype = jobtype;
  }
  
  public String getWorkshift()
  {
    return this.workshift;
  }
  
  public void setWorkshift(String workshift)
  {
    this.workshift = workshift;
  }
  
  public String getFlsastatus()
  {
    return this.flsastatus;
  }
  
  public void setFlsastatus(String flsastatus)
  {
    this.flsastatus = flsastatus;
  }
  
  public String getCompfrequency()
  {
    return this.compfrequency;
  }
  
  public void setCompfrequency(String compfrequency)
  {
    this.compfrequency = compfrequency;
  }
  
  public String getStatuscri()
  {
    return this.statuscri;
  }
  
  public void setStatuscri(String statuscri)
  {
    this.statuscri = statuscri;
  }
  
  public String getStatecri()
  {
    return this.statecri;
  }
  
  public void setStatecri(String statecri)
  {
    this.statecri = statecri;
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
  
  public List getBudgetcodeList()
  {
    return this.budgetcodeList;
  }
  
  public void setBudgetcodeList(List budgetcodeList)
  {
    this.budgetcodeList = budgetcodeList;
  }
  
  public List getApplicantList()
  {
    return this.applicantList;
  }
  
  public void setApplicantList(List applicantList)
  {
    this.applicantList = applicantList;
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
  
  public User getRecruiter()
  {
    return this.recruiter;
  }
  
  public void setRecruiter(User recruiter)
  {
    this.recruiter = recruiter;
  }
  
  public FormFile getAttachmentdata()
  {
    return this.attachmentdata;
  }
  
  public void setAttachmentdata(FormFile attachmentdata)
  {
    this.attachmentdata = attachmentdata;
  }
  
  public List getAttachmentList()
  {
    return this.attachmentList;
  }
  
  public void setAttachmentList(List attachmentList)
  {
    this.attachmentList = attachmentList;
  }
  
  public String getAttahmentdetails()
  {
    return this.attahmentdetails;
  }
  
  public void setAttahmentdetails(String attahmentdetails)
  {
    this.attahmentdetails = attahmentdetails;
  }
  
  public String getVisibleInJobDetails()
  {
    return this.visibleInJobDetails;
  }
  
  public void setVisibleInJobDetails(String visibleInJobDetails)
  {
    this.visibleInJobDetails = visibleInJobDetails;
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
  
  public String getCurrecyCode()
  {
    return this.currecyCode;
  }
  
  public void setCurrecyCode(String currecyCode)
  {
    this.currecyCode = currecyCode;
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
  
  public String getUuid()
  {
    return this.uuid;
  }
  
  public void setUuid(String uuid)
  {
    this.uuid = uuid;
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
  
  public String getApproverStatus()
  {
    return this.approverStatus;
  }
  
  public void setApproverStatus(String approverStatus)
  {
    this.approverStatus = approverStatus;
  }
  
  public String getOfferapplicantStatus()
  {
    return this.offerapplicantStatus;
  }
  
  public void setOfferapplicantStatus(String offerapplicantStatus)
  {
    this.offerapplicantStatus = offerapplicantStatus;
  }
  
  public List getProjectcodeList()
  {
    return this.projectcodeList;
  }
  
  public void setProjectcodeList(List projectcodeList)
  {
    this.projectcodeList = projectcodeList;
  }
  
  public List getSalaryplanList()
  {
    return this.salaryplanList;
  }
  
  public void setSalaryplanList(List salaryplanList)
  {
    this.salaryplanList = salaryplanList;
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
  
  public long getCategoryId()
  {
    return this.categoryId;
  }
  
  public void setCategoryId(long categoryId)
  {
    this.categoryId = categoryId;
  }
  
  public String getCategoryName()
  {
    return this.categoryName;
  }
  
  public void setCategoryName(String categoryName)
  {
    this.categoryName = categoryName;
  }
  
  public List getCategoryList()
  {
    return this.categoryList;
  }
  
  public void setCategoryList(List categoryList)
  {
    this.categoryList = categoryList;
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
  
  public List getActivityList()
  {
    return this.activityList;
  }
  
  public void setActivityList(List activityList)
  {
    this.activityList = activityList;
  }
  
  public String getIsGroup()
  {
    return this.isGroup;
  }
  
  public void setIsGroup(String isGroup)
  {
    this.isGroup = isGroup;
  }
  
  public String getCurrentOwnerName()
  {
    return this.currentOwnerName;
  }
  
  public void setCurrentOwnerName(String currentOwnerName)
  {
    this.currentOwnerName = currentOwnerName;
  }
  
  public String getIsgrouprecruiter()
  {
    return this.isgrouprecruiter;
  }
  
  public void setIsgrouprecruiter(String isgrouprecruiter)
  {
    this.isgrouprecruiter = isgrouprecruiter;
  }
  
  public List<BusinessCriteria> getFilters()
  {
    return this.filters;
  }
  
  public void setFilters(List<BusinessCriteria> filters)
  {
    this.filters = filters;
  }
  
  public List getResumeSourceList()
  {
    return this.resumeSourceList;
  }
  
  public void setResumeSourceList(List resumeSourceList)
  {
    this.resumeSourceList = resumeSourceList;
  }
  
  public int getMockexamsetId()
  {
    return this.mockexamsetId;
  }
  
  public String getBackurl()
  {
    return this.backurl;
  }
  
  public void setMockexamsetId(int mockexamsetId)
  {
    this.mockexamsetId = mockexamsetId;
  }
  
  public void setBackurl(String backurl)
  {
    this.backurl = backurl;
  }
  
  public String getMcomckexamsetcomment()
  {
    return this.mcomckexamsetcomment;
  }
  
  public void setMcomckexamsetcomment(String mcomckexamsetcomment)
  {
    this.mcomckexamsetcomment = mcomckexamsetcomment;
  }
  
  public long getQuestiongroupId()
  {
    return this.questiongroupId;
  }
  
  public void setQuestiongroupId(long questiongroupId)
  {
    this.questiongroupId = questiongroupId;
  }
  
  public String getQuestiongroupcomment()
  {
    return this.questiongroupcomment;
  }
  
  public void setQuestiongroupcomment(String questiongroupcomment)
  {
    this.questiongroupcomment = questiongroupcomment;
  }
  
  public List getQuestionGroupList()
  {
    return this.questionGroupList;
  }
  
  public void setQuestionGroupList(List questionGroupList)
  {
    this.questionGroupList = questionGroupList;
  }
  
  public List getMockexamsList()
  {
    return this.mockexamsList;
  }
  
  public void setMockexamsList(List mockexamsList)
  {
    this.mockexamsList = mockexamsList;
  }
  
  public double getPassPercentage()
  {
    return this.passPercentage;
  }
  
  public void setPassPercentage(double passPercentage)
  {
    this.passPercentage = passPercentage;
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
  
  public long[] getDepartmentIds()
  {
    return this.departmentIds;
  }
  
  public void setDepartmentIds(long[] departmentIds)
  {
    this.departmentIds = departmentIds;
  }
  
  public RequisitionSearchCriteriaMultiple getSerachCriteria()
  {
    return this.serachCriteria;
  }
  
  public void setSerachCriteria(RequisitionSearchCriteriaMultiple serachCriteria)
  {
    this.serachCriteria = serachCriteria;
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
  
  public long[] getBudgetcodeIds()
  {
    return this.budgetcodeIds;
  }
  
  public void setBudgetcodeIds(long[] budgetcodeIds)
  {
    this.budgetcodeIds = budgetcodeIds;
  }
  
  public long[] getOrgIds()
  {
    return this.orgIds;
  }
  
  public void setOrgIds(long[] orgIds)
  {
    this.orgIds = orgIds;
  }
  
  public String[] getStatuses()
  {
    return this.statuses;
  }
  
  public void setStatuses(String[] statuses)
  {
    this.statuses = statuses;
  }
  
  public String[] getStates()
  {
    return this.states;
  }
  
  public void setStates(String[] states)
  {
    this.states = states;
  }
  
  public List getStatusList()
  {
    return this.statusList;
  }
  
  public void setStatusList(List statusList)
  {
    this.statusList = statusList;
  }
  
  public List getStateList()
  {
    return this.stateList;
  }
  
  public void setStateList(List stateList)
  {
    this.stateList = stateList;
  }
  
  public long[] getLocationIds()
  {
    return this.locationIds;
  }
  
  public void setLocationIds(long[] locationIds)
  {
    this.locationIds = locationIds;
  }
  
  public long[] getCategoryIds()
  {
    return this.categoryIds;
  }
  
  public void setCategoryIds(long[] categoryIds)
  {
    this.categoryIds = categoryIds;
  }
  
  public long[] getWorkshiftIds()
  {
    return this.workshiftIds;
  }
  
  public void setWorkshiftIds(long[] workshiftIds)
  {
    this.workshiftIds = workshiftIds;
  }
  
  public String getPrimarySkill()
  {
    return this.primarySkill;
  }
  
  public void setPrimarySkill(String primarySkill)
  {
    this.primarySkill = primarySkill;
  }
  
  public List getPrimarySkillList()
  {
    return this.primarySkillList;
  }
  
  public void setPrimarySkillList(List primarySkillList)
  {
    this.primarySkillList = primarySkillList;
  }
  
  public String[] getPrimarySkills()
  {
    return this.primarySkills;
  }
  
  public void setPrimarySkills(String[] primarySkills)
  {
    this.primarySkills = primarySkills;
  }
  
  public String getKeyword()
  {
    return this.keyword;
  }
  
  public void setKeyword(String keyword)
  {
    this.keyword = keyword;
  }
  
  public String getKeyword1()
  {
    return this.keyword1;
  }
  
  public void setKeyword1(String keyword1)
  {
    this.keyword1 = keyword1;
  }
  
  public String getScoringKeyword()
  {
    return this.scoringKeyword;
  }
  
  public void setScoringKeyword(String scoringKeyword)
  {
    this.scoringKeyword = scoringKeyword;
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
  
  public long getRequisition_number()
  {
    return this.requisition_number;
  }
  
  public void setRequisition_number(long requisitionNumber)
  {
    this.requisition_number = requisitionNumber;
  }
  
  public List getYesNoList()
  {
    return this.yesNoList;
  }
  
  public void setYesNoList(List yesNoList)
  {
    this.yesNoList = yesNoList;
  }
  
  public String getIsinternal()
  {
    return this.isinternal;
  }
  
  public void setIsinternal(String isinternal)
  {
    this.isinternal = isinternal;
  }
  
  public String getIsnewposition()
  {
    return this.isnewposition;
  }
  
  public void setIsnewposition(String isnewposition)
  {
    this.isnewposition = isnewposition;
  }
  
  public String getIspublishredtoexternal()
  {
    return this.ispublishredtoexternal;
  }
  
  public void setIspublishredtoexternal(String ispublishredtoexternal)
  {
    this.ispublishredtoexternal = ispublishredtoexternal;
  }
  
  public String getIspublishredtoemployee()
  {
    return this.ispublishredtoemployee;
  }
  
  public void setIspublishredtoemployee(String ispublishredtoemployee)
  {
    this.ispublishredtoemployee = ispublishredtoemployee;
  }
  
  public List getCommentList()
  {
    return this.commentList;
  }
  
  public void setCommentList(List commentList)
  {
    this.commentList = commentList;
  }
  
  public String getComment()
  {
    return this.comment;
  }
  
  public void setComment(String comment)
  {
    this.comment = comment;
  }
  
  public String getEeoInfoIncluded()
  {
    return this.eeoInfoIncluded;
  }
  
  public void setEeoInfoIncluded(String eeoInfoIncluded)
  {
    this.eeoInfoIncluded = eeoInfoIncluded;
  }
  
  public Map<Long, SearchCustomFields> getCustomFieldData()
  {
    return this.customFieldData;
  }
  
  public void setCustomFieldData(Map<Long, SearchCustomFields> customFieldData)
  {
    this.customFieldData = customFieldData;
  }
  
  public List<SearchCustomFields> getCustomFieldCriList()
  {
    return this.customFieldCriList;
  }
  
  public void setCustomFieldCriList(List<SearchCustomFields> customFieldCriList)
  {
    this.customFieldCriList = customFieldCriList;
  }
}
