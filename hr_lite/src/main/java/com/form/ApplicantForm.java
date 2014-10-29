package com.form;

import com.bean.ApplicantAttachments;
import com.bean.ApplicantOtherDetails;
import com.bean.ApplicantUser;
import com.bean.ApplicantUserActions;
import com.bean.Country;
import com.bean.DeclinedResons;
import com.bean.EvaluationTemplate;
import com.bean.JobApplicant;
import com.bean.ResumeSourceType;
import com.bean.SalaryPlan;
import com.bean.SearchApplicantCustomFields;
import com.bean.SearchApplicantQuestions;
import com.bean.State;
import com.bean.Timezone;
import com.bean.User;
import com.bean.UserGroup;
import com.bean.criteria.ApplicantSearchCriteria;
import com.bean.dto.Recruiter;
import com.bean.lov.EthnicRace;
import com.bean.lov.VeteranDisability;
import com.bo.ApplicantBO;
import com.bo.BOFactory;
import com.bo.LovBO;
import com.common.Common;
import com.resources.Constant;
import com.util.DateUtil;
import com.util.HTMLInputFilter;
import com.util.ScribdUtil;
import com.util.StringUtils;
import com.util.XSSKeyUtil;
import java.io.File;
import java.io.RandomAccessFile;
import java.sql.Blob;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

public class ApplicantForm
  extends ActionForm
{
  protected static final Logger logger = Logger.getLogger(ApplicantForm.class);
  private long applicantId;
  public long applicant_number;
  public String uuid;
  private String fullName;
  private String email;
  private String phone;
  private List countryList;
  private List stateList;
  private String city;
  private String street1;
  private String street2;
  private String zip;
  private String heighestQualification;
  private String qualifications;
  private String status;
  private String interviewState;
  private Date appliedDate;
  private User refererEmployee;
  private String resumename;
  private String resumeHeader;
  private FormFile resumeData;
  private String createdBy;
  private Date createdDate;
  private Date updatedDate;
  private String updatedBy;
  public String primarySkill;
  public String primarySkillOther;
  private String dateofbirth = "";
  public String dateofbirth1 = "";
  private String gender = "N";
  public List ethnicRaceList;
  private long ethnicRaceId;
  private String ethnicRaceName;
  public List veteranDisabilityList;
  private long veteranDisabilityId;
  private String veteranDisabilityName;
  private String referrerName;
  private String referrerEmail;
  public String employeecode;
  public String hidesource = "no";
  private long countryId = 0L;
  private long stateId = 0L;
  private long nationalityId;
  private String nationalityName;
  public long requitionId;
  private String previousOrganization = "";
  private double noofyearsexp;
  private double noofyearsexp1;
  public double relyearsofexp;
  private String requisitionName;
  private String countryName;
  private String stateName;
  private String jobTitle;
  private long ownerid;
  private String ownerName;
  public double scoreAve;
  public int filterError;
  private List eventList;
  private EvaluationTemplate evaluationTmpl;
  private List evaluationTmplList;
  private String resumehtmlfilename;
  private List lovList;
  public List formVariablesList;
  public List formVariableDataList;
  public List errorListCustomVariable;
  public String screenCode;
  private String searchapplieddate = "";
  private String appliedcri;
  private List searchCriDateList;
  private String searchfromdate = "";
  private String searchtodate = "";
  private String targerofferdate = "";
  private String offerInitiationComment = "";
  private String currectctc = "";
  private String expectedctc = "";
  private List currencycodeList;
  private String currectctccurrencycode = "";
  private String expectedctccurrencycode = "";
  private long offerownerId;
  private String offerownerName;
  public String isofferownerGroup;
  private String offerReleaseComment;
  private Date offerreleasedate;
  private String offerreleaseddby;
  private Date offerinitiationdate;
  private String offerinitiatedby;
  private List offerApproverList;
  private FormFile attachmentdata;
  private List otherBenefitsList;
  private List offerAttachmentList;
  private List offerReleaseAttachmentList;
  private List applicantAttachmentList;
  private List actionAttachmentList;
  private List offerwatchList;
  private List initiateOfferwatchList;
  private String isOfferEmailSent = "N";
  private String targetjoiningdate = "";
  private String joiningdate = "";
  private String offeredctc = "";
  private String offeredctccurrencycode;
  private long emailtemplateid;
  private String emailtemplate;
  private String offeracceptedcomment;
  private Date offeraccepteddate;
  private String offerdeclinedcomment;
  private Date offerdeclineddate;
  private int offerdeclinedresonid;
  private String offerdeclinedreason;
  private List offerdeclinedreasonslist;
  public String changeofferedctc;
  private String changeofferedctccurrencycode;
  private String chagetargetjoiningdate;
  private String changeofferComment = "";
  private String offercancelledcomment = "";
  private Date offercancelleddate;
  private int offercancelledresonid;
  private String offercancelledby;
  private String offercancelledreason;
  private String isoffercancelemailsent = "N";
  private Date offercancelemailsentdate;
  private List refrenceslist;
  private String resumedetails;
  private String joineddate = "";
  private String joinedcomment;
  private String resigneddate = "";
  private String resignedcomment;
  private String comment;
  private List commentList;
  private List savedsearchesList;
  private String vendorName = "";
  private List vendorList;
  private List interviewstateList;
  private List jobtitleList;
  private String applicantNo;
  private List paginationRowsList;
  private String noofrows;
  private long interviewOrganizerId;
  private String[] interviewstatecodes;
  public String initiateJoiningProcess;
  public String sourcetypename;
  List comptetencyList;
  List accomplishmentList;
  List applicantList;
  List alreayappliedlist;
  private List educationNamesList;
  private List educationsList;
  private String educationName;
  private String specialization;
  private String instituteName;
  private String percentile;
  private String startingYear;
  private String passingYear;
  private List certificationsList;
  private String eduType;
  private String certName;
  private String certorgName;
  private String certpercentile;
  private String certpassingYear;
  public String alternateemail;
  public String alternatephone;
  public String preferedlocation;
  public String currentdesignation;
  public String note;
  public String noticeperiod;
  public String passportno;
  public String ssnno;
  public String taxidno;
  public String earliest_start_date = "";
  public String earliest_start_date1 = "";
  public String work_on_weekends;
  public String work_on_evenings;
  public String work_on_overtime;
  public String want_to_relocate;
  public String languages_spoken;
  public String work_status;
  public String felony_conviction;
  public String felony_conviction_desc;
  public String website_url;
  public String linkedin_url;
  public String facebook_url;
  public String college_GPA;
  public String college_name;
  public String address;
  public String zipcode;
  private List previousOrgList;
  private String prevOrgName;
  private String role;
  private String startDate;
  private String endDate;
  private String reasonforleave;
  private String reportingToName;
  private String city_Previousorg;
  private long country_id_prevorg = 0L;
  private long state_id_prevorg = 0L;
  private String lastSalary;
  private String bonus;
  private int currency_id_prevorg = 0;
  private String responsibilities;
  private String employercontactName;
  private String employercontactPhone;
  private List currencyList;
  private SalaryPlan salaryPlan;
  private List mocktestList;
  private List mockCatList;
  private int mockcatId;
  private double passPercentage;
  private List questionGroupList;
  private List quetionnaireList;
  private long quetionnaireId;
  private String headingComment;
  private List mockQuestionSetList;
  private int mockQuestionSetId;
  private List applicantSkillList;
  private List skillList;
  private List skillYearsList;
  private List skillRatingList;
  private String skillname;
  private String yearsofexpskill;
  private String ratingskill;
  private int sourceTypeId;
  private List sourceTypeList;
  private long sourceId;
  private List sourceList;
  private long vendorId;
  private List vendorsList;
  private ApplicantUser applicantuser;
  private ApplicantUserActions applicantuseraction;
  private List organizationList;
  private List departmentList;
  private List projectcodeList;
  private long orgId;
  private long departmentId;
  private long projectcodeId;
  private String onboardingProcessStatus;
  private List onboardingProcessStatusList;
  private List activityList;
  public long tagId;
  public String tagName;
  public String tagNames;
  private List tagList;
  public String offerEmailSentError;
  private UserGroup ownerGroup;
  private String isGroup = "N";
  private String filePath;
  private FormFile profilePhoto;
  private long profilePhotoId;
  public String backUrl;
  private long talentPoolId;
  private List talentPoolList;
  public List iduserlistVal;
  public String talentPoolName;
  String password;
  String cpassword;
  String currentpassword;
  private long applicantcommenttId;
  public String commentVisible;
  public List commentVisibleList;
  public int scribddocumentid;
  private String scribddocumentkey;
  public String backurltoapplicantlist;
  public List yesnofulllist = Constant.getYesNoFullList();
  public String attachmentuuid;
  public long[] orgIds;
  public long[] departmentIds;
  public long[] projectcodeIds;
  public String[] interviewStates;
  public long[] vendorIds;
  public long[] requitionIds;
  public String keywords;
  public long[] tagIds;
  public String[] primarySkills;
  public String college_GPA1;
  public String milesWithin;
  ApplicantSearchCriteria searchcriteria;
  public List criteriaStringList;
  public List criteriaNumericList;
  public List criteriaDateList;
  public String applicantNo_criteria;
  public String applicantName_criteria;
  public String dob_criteria;
  public String passport_criteria;
  public String ssn_criteria;
  public String taxno_criteria;
  public String email_criteria;
  public String city_criteria;
  public String org_criteria;
  public String currectctc_criteria;
  public String expectedctc_criteria;
  public String noticeperiod_criteria;
  public String currentdesignation_criteria;
  public String noofyearsexp_criteria;
  public String resumeHeader_criteria;
  public String earliest_start_date_criteria;
  public String languages_spoken_criteria;
  public String preferedlocation_criteria;
  public String mockquestionset_criteria;
  public String heighestQualification_criteria;
  public String qualifications_criteria;
  public String college_name_criteria;
  public String college_GPA_criteria;
  private String currectctc1 = "";
  private String expectedctc1 = "";
  private String mockquestionsetValue1;
  private String mockquestionsetValue2;
  public String filterValue1;
  public String filterValue2;
  List<SearchApplicantQuestions> questionCriList;
  Map<Long, SearchApplicantCustomFields> customFieldData = new HashMap();
  public String searchuuid;
  public long applicantId1 = 2147483647L;
  public String noticeperiod1;
  private User hiringmgr;
  private Recruiter recruiter;
  
  public String getNoticeperiod1()
  {
    return this.noticeperiod1;
  }
  
  public void setNoticeperiod1(String noticeperiod1)
  {
    this.noticeperiod1 = noticeperiod1;
  }
  
  public long getApplicantcommenttId()
  {
    return this.applicantcommenttId;
  }
  
  public void setApplicantcommenttId(long applicantcommenttId)
  {
    this.applicantcommenttId = applicantcommenttId;
  }
  
  public User getHiringmgr()
  {
    return this.hiringmgr;
  }
  
  public void setHiringmgr(User hiringmgr)
  {
    this.hiringmgr = hiringmgr;
  }
  
  public List getEthnicRaceList()
  {
    return this.ethnicRaceList;
  }
  
  public void setEthnicRaceList(List ethnicRaceList)
  {
    this.ethnicRaceList = ethnicRaceList;
  }
  
  public long getEthnicRaceId()
  {
    return this.ethnicRaceId;
  }
  
  public void setEthnicRaceId(long ethnicRaceId)
  {
    this.ethnicRaceId = ethnicRaceId;
  }
  
  public List getEventList()
  {
    return this.eventList;
  }
  
  public void setEventList(List eventList)
  {
    this.eventList = eventList;
  }
  
  public String getCountryName()
  {
    return this.countryName;
  }
  
  public void setCountryName(String countryName)
  {
    this.countryName = countryName;
  }
  
  public String getStateName()
  {
    return this.stateName;
  }
  
  public void setStateName(String stateName)
  {
    this.stateName = stateName;
  }
  
  public long getOwnerid()
  {
    return this.ownerid;
  }
  
  public void setOwnerid(long ownerid)
  {
    this.ownerid = ownerid;
  }
  
  public FormFile getResumeData()
  {
    return this.resumeData;
  }
  
  public void setResumeData(FormFile resumeData)
  {
    this.resumeData = resumeData;
  }
  
  public long getApplicantId()
  {
    return this.applicantId;
  }
  
  public void setApplicantId(long applicantId)
  {
    this.applicantId = applicantId;
  }
  
  public String getEmail()
  {
    return this.email;
  }
  
  public void setEmail(String email)
  {
    this.email = email;
  }
  
  public String getPhone()
  {
    return this.phone;
  }
  
  public void setPhone(String phone)
  {
    this.phone = phone;
  }
  
  public List getCountryList()
  {
    return this.countryList;
  }
  
  public void setCountryList(List countryList)
  {
    this.countryList = countryList;
  }
  
  public List getStateList()
  {
    return this.stateList;
  }
  
  public void setStateList(List stateList)
  {
    this.stateList = stateList;
  }
  
  public String getCity()
  {
    return this.city;
  }
  
  public void setCity(String city)
  {
    this.city = city;
  }
  
  public String getStreet1()
  {
    return this.street1;
  }
  
  public void setStreet1(String street1)
  {
    this.street1 = street1;
  }
  
  public String getStreet2()
  {
    return this.street2;
  }
  
  public void setStreet2(String street2)
  {
    this.street2 = street2;
  }
  
  public String getZip()
  {
    return this.zip;
  }
  
  public void setZip(String zip)
  {
    this.zip = zip;
  }
  
  public String getHeighestQualification()
  {
    return this.heighestQualification;
  }
  
  public void setHeighestQualification(String heighestQualification)
  {
    this.heighestQualification = heighestQualification;
  }
  
  public String getQualifications()
  {
    return this.qualifications;
  }
  
  public void setQualifications(String qualifications)
  {
    this.qualifications = qualifications;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public String getInterviewState()
  {
    return this.interviewState;
  }
  
  public void setInterviewState(String interviewState)
  {
    this.interviewState = interviewState;
  }
  
  public Date getAppliedDate()
  {
    return this.appliedDate;
  }
  
  public void setAppliedDate(Date appliedDate)
  {
    this.appliedDate = appliedDate;
  }
  
  public User getRefererEmployee()
  {
    return this.refererEmployee;
  }
  
  public void setRefererEmployee(User refererEmployee)
  {
    this.refererEmployee = refererEmployee;
  }
  
  public String getResumename()
  {
    return this.resumename;
  }
  
  public void setResumename(String resumename)
  {
    this.resumename = resumename;
  }
  
  public String getResumeHeader()
  {
    return this.resumeHeader;
  }
  
  public void setResumeHeader(String resumeHeader)
  {
    this.resumeHeader = resumeHeader;
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
  
  public long getCountryId()
  {
    return this.countryId;
  }
  
  public void setCountryId(long countryId)
  {
    this.countryId = countryId;
  }
  
  public long getStateId()
  {
    return this.stateId;
  }
  
  public void setStateId(long stateId)
  {
    this.stateId = stateId;
  }
  
  public JobApplicant toValue(JobApplicant applicant, HttpServletRequest request)
    throws Exception
  {
    HTMLInputFilter filter = new HTMLInputFilter();
    
    List xxsList = XSSKeyUtil.getListOfValuesByKey("ApplicantForm");
    
    logger.info("xxsList.size()" + xxsList.size());
    if (xxsList.contains("fullName"))
    {
      logger.info("xxsList.size()");
      
      applicant.setFullName(filter.filter(this.fullName));
    }
    else
    {
      applicant.setFullName(this.fullName);
    }
    applicant.setEmail(this.email);
    applicant.setPhone(this.phone);
    applicant.setStreet1(this.street1);
    applicant.setStreet2(this.street2);
    applicant.setCity(this.city);
    

    applicant.setQualifications(this.qualifications);
    applicant.setHeighestQualification(this.heighestQualification);
    applicant.setResumeHeader(this.resumeHeader);
    applicant.setReferrerEmail(this.referrerEmail);
    applicant.setReferrerName(this.referrerName);
    applicant.setEmployeecode(this.employeecode);
    applicant.setAppliedDate(new Date());
    applicant.setNoofyearsexp(this.noofyearsexp);
    applicant.setRelyearsofexp(this.relyearsofexp);
    applicant.setCurrectctc(this.currectctc);
    applicant.setExpectedctc(this.expectedctc);
    applicant.setPrimarySkill(this.primarySkill);
    applicant.setPrimarySkillOther(this.primarySkillOther);
    applicant.setPreviousOrganization(this.previousOrganization);
    FormFile myFile = getResumeData();
    
    int limitfileSize = new Integer(Constant.getValue("attachment.file.size.limit")).intValue();
    if ((myFile != null) && (myFile.getFileSize() != 0))
    {
      logger.info("attachment present true .... ");
      String contentType = myFile.getContentType();
      String fileName = myFile.getFileName();
      int fileSize = myFile.getFileSize();
      byte[] fileData = myFile.getFileData();
      if (fileSize > limitfileSize)
      {
        request.setAttribute("attachmentsizeexceed", "yes");
      }
      else
      {
        setResumename(fileName);
        applicant.setResumename(fileName);
        Blob blob = null;
        
        blob = new SerialBlob(fileData);
        ApplicantAttachments attach = new ApplicantAttachments();
        attach.setAttachmentdata(blob);
        attach.setAttahmentname(fileName);
        attach.setUuid(UUID.randomUUID().toString());
        applicant.setApplicantAttachment(attach);
        this.attachmentuuid = attach.getUuid();
        if ((applicant.getApplicantAttachment() != null) && (applicant.getApplicantAttachment().getAttachmentdata() != null) && (applicant.getResumename().length() > 0))
        {
          String filePath = Constant.getValue("ATTACHMENT_PATH");
          filePath = filePath + File.separator + "applicantAttachment" + File.separator + applicant.getApplicantAttachment().getUuid() + File.separator;
          File file = new File(filePath);
          if (!file.exists()) {
            file.mkdirs();
          }
          RandomAccessFile raf = new RandomAccessFile(filePath + applicant.getResumename(), "rw");
          

          int length = (int)blob.length();
          byte[] _blob = blob.getBytes(1L, length);
          raf.write(_blob);
          raf.close();
          applicant.setFilePath(filePath);
          if ((!StringUtils.isNullOrEmpty(Constant.getValue("scribd.enabled"))) && (Constant.getValue("scribd.enabled").equalsIgnoreCase("yes")) && 
            (applicant.getApplicantAttachment().getAttahmentname() != null)) {
            ScribdUtil.uploadFile(applicant.getApplicantAttachment());
          }
        }
      }
    }
    if (this.countryId != 0L)
    {
      Country country = new Country();
      country.setCountryId(this.countryId);
      applicant.setCountry(country);
    }
    else
    {
      applicant.setCountry(null);
    }
    if (this.stateId != 0L)
    {
      State state = new State();
      state.setStateId(this.stateId);
      applicant.setState(state);
    }
    else
    {
      applicant.setState(null);
    }
    logger.info("applicant.getInterviewState() inside form" + applicant.getInterviewState());
    logger.info("applicant.getStatus() inside form" + applicant.getStatus());
    if (!StringUtils.isNullOrEmpty(applicant.getInterviewState())) {
      applicant.setInterviewState(applicant.getInterviewState());
    } else {
      applicant.setInterviewState("Application Submitted");
    }
    if (!StringUtils.isNullOrEmpty(applicant.getStatus())) {
      applicant.setStatus(applicant.getStatus());
    } else {
      applicant.setStatus("A");
    }
    applicant.setAlternateemail(this.alternateemail);
    applicant.setAlternatephone(this.alternatephone);
    applicant.setPreferedlocation(this.preferedlocation);
    applicant.setCurrentdesignation(this.currentdesignation);
    applicant.setNote(this.note);
    applicant.setNoticeperiod(this.noticeperiod);
    applicant.setPassportno(this.passportno);
    applicant.setSsnno(this.ssnno);
    applicant.setTaxidno(this.taxidno);
    if (this.sourceTypeId != 0)
    {
      ResumeSourceType resumesourcetype = new ResumeSourceType();
      resumesourcetype.setResumeSourceTypeId(this.sourceTypeId);
      applicant.setResumesourcetype(resumesourcetype);
      
      applicant.setResumeSourcesId(this.sourceId);
    }
    if ((this.sourceTypeId == 5) && (this.sourceId != 0L))
    {
      applicant.setVendorId(this.sourceId);
      applicant.setResumeSourcesId(0L);
    }
    else
    {
      applicant.setVendorId(this.vendorId);
    }
    setOtherDetails(applicant, request);
    
    long superUserKey = 0L;
    superUserKey = VariableDataCaptureUtil.getSuperUserKey(request, applicant);
    


    Map m = VariableDataCaptureUtil.captureCustomVariables(request, this.applicantId, this.screenCode, superUserKey);
    
    this.formVariablesList = ((List)m.get("formVariablesList"));
    this.errorListCustomVariable = ((List)m.get("errorList"));
    this.formVariableDataList = ((List)m.get("formVariableDataList"));
    
    return applicant;
  }
  
  public void setOtherDetails(JobApplicant applicant, HttpServletRequest request)
    throws Exception
  {
    String earlieststartdate = this.earliest_start_date;
    ApplicantOtherDetails otherdetails = new ApplicantOtherDetails();
    if (!StringUtils.isNullOrEmpty(earlieststartdate))
    {
      String datepattern = DateUtil.getDatePatternFormat(VariableDataCaptureUtil.getLocale(request));
      String converteddate = DateUtil.convertFromTimezoneToTimezoneDateWithException(earlieststartdate, datepattern, VariableDataCaptureUtil.getTimeZone(request).getTimezoneCode(), TimeZone.getDefault().getID());
      
      logger.info("earlieststartdate : " + converteddate);
      
      Calendar cal = DateUtil.convertStringDateToCalendarWithException(converteddate, datepattern);
      
      otherdetails.setEarliest_start_date(cal.getTime());
    }
    otherdetails.work_on_weekends = this.work_on_weekends;
    otherdetails.work_on_evenings = this.work_on_evenings;
    otherdetails.work_on_overtime = this.work_on_overtime;
    otherdetails.want_to_relocate = this.want_to_relocate;
    otherdetails.languages_spoken = this.languages_spoken;
    otherdetails.work_status = this.work_status;
    otherdetails.felony_conviction = this.felony_conviction;
    otherdetails.felony_conviction_desc = this.felony_conviction_desc;
    otherdetails.website_url = this.website_url;
    otherdetails.linkedin_url = this.linkedin_url;
    otherdetails.facebook_url = this.facebook_url;
    otherdetails.college_GPA = this.college_GPA;
    otherdetails.college_name = this.college_name;
    otherdetails.resumeDetails = this.resumedetails;
    otherdetails.setAddress(this.address);
    otherdetails.setZipcode(this.zipcode);
    if (this.nationalityId != 0L)
    {
      Country natinality = new Country();
      natinality.setCountryId(this.nationalityId);
      otherdetails.setNationality(natinality);
    }
    if (this.ethnicRaceId != 0L)
    {
      EthnicRace ethnicRace = new EthnicRace();
      ethnicRace.setEthnicRaceId(this.ethnicRaceId);
      
      otherdetails.setEthnicRace(ethnicRace);
    }
    if (this.veteranDisabilityId != 0L)
    {
      VeteranDisability vd = new VeteranDisability();
      vd.setVdId(this.veteranDisabilityId);
      
      otherdetails.setVeteranDisability(vd);
    }
    applicant.setOtherdetails(otherdetails);
  }
  
  public void getOtherDetails(JobApplicant applicant, HttpServletRequest request)
    throws Exception
  {
    ApplicantOtherDetails otherdetails = applicant.getOtherdetails();
    if (otherdetails != null)
    {
      if (otherdetails.getEarliest_start_date() != null)
      {
        String datepattern = DateUtil.getDatePatternFormat(VariableDataCaptureUtil.getLocale(request));
        
        setEarliest_start_date(DateUtil.convertDateToStringDate(otherdetails.getEarliest_start_date(), datepattern));
      }
      setWork_on_weekends(otherdetails.getWork_on_weekends());
      setWork_on_evenings(otherdetails.getWork_on_evenings());
      this.work_on_overtime = otherdetails.getWork_on_overtime();
      this.want_to_relocate = otherdetails.getWant_to_relocate();
      this.languages_spoken = otherdetails.getLanguages_spoken();
      this.work_status = otherdetails.getWork_status();
      this.felony_conviction = otherdetails.getFelony_conviction();
      this.felony_conviction_desc = otherdetails.getFelony_conviction_desc();
      this.website_url = otherdetails.getWebsite_url();
      this.linkedin_url = otherdetails.getLinkedin_url();
      this.facebook_url = otherdetails.getFacebook_url();
      this.college_GPA = otherdetails.getCollege_GPA();
      this.college_name = otherdetails.getCollege_name();
      this.resumedetails = otherdetails.getResumeDetails();
      this.address = otherdetails.getAddress();
      this.zipcode = otherdetails.getZipcode();
      if (otherdetails.getNationality() != null)
      {
        this.nationalityId = otherdetails.getNationality().getCountryId();
        this.nationalityName = otherdetails.getNationality().getCountryName();
      }
      if (otherdetails.getEthnicRace() != null)
      {
        this.ethnicRaceId = otherdetails.getEthnicRace().getEthnicRaceId();
        this.ethnicRaceName = otherdetails.getEthnicRace().getEthnicRaceName();
      }
      if (otherdetails.getVeteranDisability() != null)
      {
        this.veteranDisabilityId = otherdetails.getVeteranDisability().getVdId();
        this.veteranDisabilityName = otherdetails.getVeteranDisability().getName();
      }
    }
  }
  
  public void fromValue(JobApplicant applicant, HttpServletRequest request)
    throws Exception
  {
    User user1 = (User)request.getSession().getAttribute("user_data");
    
    this.applicantId = applicant.getApplicantId();
    this.applicant_number = applicant.getApplicant_number();
    this.uuid = applicant.getUuid();
    this.email = applicant.getEmail();
    this.phone = applicant.getPhone();
    setResumename(applicant.getResumename());
    this.applicantId = applicant.getApplicantId();
    this.interviewState = applicant.getInterviewState();
    if (applicant.getOwner() != null)
    {
      this.ownerid = applicant.getOwner().getUserId();
      this.ownerName = (applicant.getOwner().getFirstName() + " " + applicant.getOwner().getLastName());
    }
    this.street1 = applicant.getStreet1();
    this.street2 = applicant.getStreet2();
    this.city = applicant.getCity();
    if (applicant.getCountry() != null)
    {
      this.countryName = applicant.getCountry().getCountryName();
      this.countryId = applicant.getCountry().getCountryId();
    }
    if (applicant.getState() != null)
    {
      this.stateName = applicant.getState().getStateName();
      this.stateId = applicant.getState().getStateId();
    }
    this.jobTitle = applicant.getJobTitle();
    this.requisitionName = applicant.getReqName();
    this.heighestQualification = applicant.getHeighestQualification();
    this.qualifications = applicant.getQualifications();
    this.resumeHeader = applicant.getResumeHeader();
    this.createdBy = applicant.getCreatedBy();
    this.createdDate = applicant.getCreatedDate();
    this.fullName = applicant.getFullName();
    this.gender = applicant.getGender();
    this.scoreAve = applicant.getScoreAve();
    this.filterError = applicant.getFilterError();
    this.primarySkill = applicant.getPrimarySkill();
    this.primarySkillOther = applicant.getPrimarySkillOther();
    this.screenCode = applicant.getScreenCode();
    if (applicant.getDateofbirth() != null)
    {
      String datepattern = Constant.getValue("defaultdateformat");
      if (user1 != null) {
        datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
      }
      this.dateofbirth = DateUtil.convertDateToStringDate(applicant.getDateofbirth(), datepattern);
    }
    this.currectctc = applicant.getCurrectctc();
    this.expectedctc = applicant.getExpectedctc();
    
    this.requitionId = applicant.getReqId();
    this.previousOrganization = applicant.getPreviousOrganization();
    this.noofyearsexp = applicant.getNoofyearsexp();
    this.relyearsofexp = applicant.getRelyearsofexp();
    this.offerownerId = applicant.getOfferownerId();
    this.isofferownerGroup = applicant.getIsofferownerGroup();
    this.isOfferEmailSent = applicant.getIsOfferEmailSent();
    this.offerreleasedate = applicant.getOfferreleasedate();
    if (applicant.getOfferReleaseComment() != null) {
      this.offerReleaseComment = applicant.getOfferReleaseComment();
    }
    this.offerreleaseddby = applicant.getOfferreleaseddby();
    String datepattern = Constant.getValue("defaultdateformat");
    if (user1 != null) {
      datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
    }
    if (applicant.getTargetjoiningdate() != null) {
      this.targetjoiningdate = DateUtil.convertDateToStringDate(applicant.getTargetjoiningdate(), datepattern);
    }
    if (applicant.getTargerofferdate() != null) {
      this.targerofferdate = DateUtil.convertDateToStringDate(applicant.getTargerofferdate(), datepattern);
    }
    if (applicant.getJoiningdate() != null) {
      this.joiningdate = DateUtil.convertDateToStringDate(applicant.getJoiningdate(), datepattern);
    }
    if (applicant.getJoineddate() != null) {
      this.joineddate = DateUtil.convertDateToStringDate(applicant.getJoineddate(), datepattern);
    }
    setOfferacceptedcomment(applicant.getOfferacceptedcomment());
    setOfferaccepteddate(applicant.getOfferaccepteddate());
    setOfferdeclinedcomment(applicant.getOfferdeclinedcomment());
    setOfferdeclineddate(applicant.getOfferdeclineddate());
    if (applicant.getChagetargetjoiningdate() != null) {
      this.chagetargetjoiningdate = DateUtil.convertDateToStringDate(applicant.getChagetargetjoiningdate(), datepattern);
    }
    this.changeofferedctc = applicant.getChangeofferedctc();
    this.changeofferedctccurrencycode = applicant.getChangeofferedctccurrencycode();
    this.changeofferComment = applicant.getChangeofferComment();
    this.offeredctc = applicant.getOfferedctc();
    this.offeredctccurrencycode = applicant.getOfferedctccurrencycode();
    this.offercancelledby = applicant.getOffercancelledby();
    this.offercancelleddate = applicant.getOffercancelleddate();
    if (applicant.getOffercancelledresonid() != 0)
    {
      DeclinedResons offerdl = BOFactory.getLovBO().getOfferDecliendReson(applicant.getOffercancelledresonid());
      this.offercancelledreason = offerdl.getOfferdecliedreasonName();
    }
    this.offercancelledcomment = applicant.getOffercancelledcomment();
    this.isoffercancelemailsent = applicant.getIsoffercancelemailsent();
    this.offerEmailSentError = applicant.getOfferEmailSentError();
    this.offercancelemailsentdate = applicant.getOffercancelemailsentdate();
    this.joinedcomment = applicant.getJoinedcomment();
    this.interviewOrganizerId = applicant.getInterview_organizer_id();
    
    setAlternateemail(applicant.alternateemail);
    setAlternatephone(applicant.alternatephone);
    setPreferedlocation(applicant.preferedlocation);
    setCurrentdesignation(applicant.currentdesignation);
    setNote(applicant.note);
    setNoticeperiod(applicant.noticeperiod);
    setPassportno(applicant.passportno);
    setSsnno(applicant.ssnno);
    setTaxidno(applicant.taxidno);
    if (applicant.getResumesourcetype() != null)
    {
      this.sourceTypeId = applicant.getResumesourcetype().getResumeSourceTypeId();
      this.sourcetypename = applicant.getResumesourcetype().getResumeSourceTypeName();
    }
    else
    {
      this.sourcetypename = "";
    }
    this.sourceId = applicant.getResumeSourcesId();
    

    this.vendorId = applicant.getVendorId();
    this.employeecode = applicant.getEmployeecode();
    this.referrerEmail = applicant.getReferrerEmail();
    this.referrerName = applicant.getReferrerName();
    
    this.initiateJoiningProcess = applicant.getInitiateJoiningProcess();
    
    this.tagId = applicant.getTagId();
    this.tagName = applicant.getTagName();
    this.expectedctccurrencycode = applicant.getExpectedctccurrencycode();
    this.currectctccurrencycode = applicant.getCurrectctccurrencycode();
    this.status = applicant.getStatus();
    this.isGroup = applicant.getIsGroup();
    this.ownerGroup = applicant.getOwnerGroup();
    this.profilePhotoId = applicant.getProfilePhotoId();
    
    this.filePath = applicant.getFilePath();
    this.talentPoolId = applicant.getTalentpoolid();
    this.talentPoolName = applicant.getTalentPoolName();
    
    getOtherDetails(applicant, request);
    
    logger.info("for custom properties this.applicantId" + this.applicantId);
    if (this.applicantId > 0L)
    {
      ApplicantAttachments appattach = BOFactory.getApplicantBO().getApplicantAttachments(this.applicantId, Common.APPLICANT_ATTACHMENT_TYPE_RESUME);
      if (appattach != null)
      {
        this.scribddocumentid = appattach.getScribddocumentid();
        this.scribddocumentkey = appattach.getScribddocumentkey();
      }
    }
  }
  
  public void fromValueNologin(JobApplicant applicant, HttpServletRequest request)
    throws Exception
  {
    this.applicantId = applicant.getApplicantId();
    this.applicant_number = applicant.getApplicant_number();
    this.email = applicant.getEmail();
    this.phone = applicant.getPhone();
    setResumename(applicant.getResumename());
    this.applicantId = applicant.getApplicantId();
    this.interviewState = applicant.getInterviewState();
    if (applicant.getOwner() != null)
    {
      this.ownerid = applicant.getOwner().getUserId();
      this.ownerName = (applicant.getOwner().getFirstName() + " " + applicant.getOwner().getLastName());
    }
    this.street1 = applicant.getStreet1();
    this.street2 = applicant.getStreet2();
    this.city = applicant.getCity();
    if (applicant.getCountry() != null)
    {
      this.countryName = applicant.getCountry().getCountryName();
      this.countryId = applicant.getCountry().getCountryId();
    }
    if (applicant.getState() != null) {
      this.stateId = applicant.getState().getStateId();
    }
    this.jobTitle = applicant.getJobTitle();
    this.heighestQualification = applicant.getHeighestQualification();
    this.qualifications = applicant.getQualifications();
    this.resumeHeader = applicant.getResumeHeader();
    this.createdBy = applicant.getCreatedBy();
    this.createdDate = applicant.getCreatedDate();
    this.fullName = applicant.getFullName();
    this.gender = applicant.getGender();
    this.screenCode = applicant.getScreenCode();
    this.scoreAve = applicant.getScoreAve();
    this.filterError = applicant.getFilterError();
    if (applicant.getDateofbirth() != null) {
      this.dateofbirth = DateUtil.convertDateToStringDate(applicant.getDateofbirth(), Constant.getValue("email.defaultdateformat"));
    }
    this.currentdesignation = applicant.currentdesignation;
    
    this.requitionId = applicant.getReqId();
    this.previousOrganization = applicant.getPreviousOrganization();
    this.noofyearsexp = applicant.getNoofyearsexp();
    this.relyearsofexp = applicant.getRelyearsofexp();
    this.offerownerId = applicant.getOfferownerId();
    this.isOfferEmailSent = applicant.getIsOfferEmailSent();
    this.offerreleasedate = applicant.getOfferreleasedate();
    if (applicant.getOfferReleaseComment() != null) {
      this.offerReleaseComment = applicant.getOfferReleaseComment();
    }
    this.offerreleaseddby = applicant.getOfferreleaseddby();
    String datepattern = Constant.getValue("email.defaultdateformat");
    if (applicant.getOfferedctc() != null) {
      this.offeredctc = applicant.getOfferedctc();
    }
    if (applicant.getOfferedctccurrencycode() != null) {
      this.offeredctccurrencycode = applicant.getOfferedctccurrencycode();
    }
    if (applicant.getTargetjoiningdate() != null) {
      this.targetjoiningdate = DateUtil.convertDateToStringDate(applicant.getTargetjoiningdate(), datepattern);
    }
    if (applicant.getTargerofferdate() != null) {
      this.targerofferdate = DateUtil.convertDateToStringDate(applicant.getTargerofferdate(), datepattern);
    }
    if (applicant.getJoiningdate() != null) {
      this.joiningdate = DateUtil.convertDateToStringDate(applicant.getJoiningdate(), datepattern);
    }
    this.expectedctccurrencycode = applicant.getExpectedctccurrencycode();
    this.currectctccurrencycode = applicant.getCurrectctccurrencycode();
    
    this.filePath = applicant.getFilePath();
    this.talentPoolId = applicant.getTalentpoolid();
    this.talentPoolName = applicant.getTalentPoolName();
    
    getOtherDetails(applicant, request);
  }
  
  public void fromValueApplicantlogin(JobApplicant applicant, HttpServletRequest request)
    throws Exception
  {
    ApplicantUser user1 = (ApplicantUser)request.getSession().getAttribute("applicant_user_data");
    

    this.applicantId = applicant.getApplicantId();
    this.applicant_number = applicant.getApplicant_number();
    this.email = applicant.getEmail();
    this.phone = applicant.getPhone();
    setResumename(applicant.getResumename());
    this.applicantId = applicant.getApplicantId();
    this.interviewState = applicant.getInterviewState();
    this.ownerid = applicant.getOwner().getUserId();
    this.ownerName = (applicant.getOwner().getFirstName() + " " + applicant.getOwner().getLastName());
    this.street1 = applicant.getStreet1();
    this.street2 = applicant.getStreet2();
    this.city = applicant.getCity();
    if (applicant.getCountry() != null)
    {
      this.countryName = applicant.getCountry().getCountryName();
      this.countryId = applicant.getCountry().getCountryId();
    }
    if (applicant.getState() != null) {
      this.stateId = applicant.getState().getStateId();
    }
    this.jobTitle = applicant.getJobTitle();
    this.heighestQualification = applicant.getHeighestQualification();
    this.qualifications = applicant.getQualifications();
    this.resumeHeader = applicant.getResumeHeader();
    this.createdBy = applicant.getCreatedBy();
    this.createdDate = applicant.getCreatedDate();
    this.fullName = applicant.getFullName();
    this.gender = applicant.getGender();
    this.primarySkill = applicant.getPrimarySkill();
    this.primarySkillOther = applicant.getPrimarySkillOther();
    this.scoreAve = applicant.getScoreAve();
    this.screenCode = applicant.getScreenCode();
    this.filterError = applicant.getFilterError();
    if (applicant.getDateofbirth() != null) {
      this.dateofbirth = DateUtil.convertDateToStringDate(applicant.getDateofbirth(), Constant.getValue("email.defaultdateformat"));
    }
    this.currentdesignation = applicant.currentdesignation;
    
    this.requitionId = applicant.getReqId();
    this.previousOrganization = applicant.getPreviousOrganization();
    this.noofyearsexp = applicant.getNoofyearsexp();
    this.relyearsofexp = applicant.getRelyearsofexp();
    this.offerownerId = applicant.getOfferownerId();
    this.isOfferEmailSent = applicant.getIsOfferEmailSent();
    this.offerreleasedate = applicant.getOfferreleasedate();
    if (applicant.getOfferReleaseComment() != null) {
      this.offerReleaseComment = applicant.getOfferReleaseComment();
    }
    this.offerreleaseddby = applicant.getOfferreleaseddby();
    String datepattern = Constant.getValue("email.defaultdateformat");
    if (applicant.getOfferedctc() != null) {
      this.offeredctc = applicant.getOfferedctc();
    }
    if (applicant.getOfferedctccurrencycode() != null) {
      this.offeredctccurrencycode = applicant.getOfferedctccurrencycode();
    }
    if (applicant.getTargetjoiningdate() != null) {
      this.targetjoiningdate = DateUtil.convertDateToStringDate(applicant.getTargetjoiningdate(), datepattern);
    }
    if (applicant.getTargerofferdate() != null) {
      this.targerofferdate = DateUtil.convertDateToStringDate(applicant.getTargerofferdate(), datepattern);
    }
    if (applicant.getJoiningdate() != null) {
      this.joiningdate = DateUtil.convertDateToStringDate(applicant.getJoiningdate(), datepattern);
    }
    this.alternateemail = applicant.getAlternateemail();
    this.alternatephone = applicant.getAlternatephone();
    this.preferedlocation = applicant.getPreferedlocation();
    this.currentdesignation = applicant.getCurrentdesignation();
    this.note = applicant.getNote();
    this.noticeperiod = applicant.getNoticeperiod();
    this.passportno = applicant.getPassportno();
    setSsnno(applicant.ssnno);
    setTaxidno(applicant.taxidno);
    this.primarySkill = applicant.getPrimarySkill();
    this.primarySkillOther = applicant.getPrimarySkillOther();
    
    this.currectctc = applicant.getCurrectctc();
    this.expectedctc = applicant.getExpectedctc();
    
    this.currectctccurrencycode = applicant.getCurrectctccurrencycode();
    this.expectedctccurrencycode = applicant.getExpectedctccurrencycode();
    
    this.filePath = applicant.getFilePath();
    this.talentPoolId = applicant.getTalentpoolid();
    this.talentPoolName = applicant.getTalentPoolName();
    
    getOtherDetails(applicant, request);
    if (this.applicantId > 0L)
    {
      ApplicantAttachments appattach = BOFactory.getApplicantBO().getApplicantAttachments(this.applicantId, Common.APPLICANT_ATTACHMENT_TYPE_RESUME);
      if (appattach == null) {}
    }
  }
  
  public JobApplicant toValueApplicantLogin(JobApplicant applicant, HttpServletRequest request)
    throws Exception
  {
    applicant.setFullName(this.fullName);
    
    applicant.setPhone(this.phone);
    applicant.setStreet1(this.street1);
    applicant.setStreet2(this.street2);
    applicant.setCity(this.city);
    

    applicant.setQualifications(this.qualifications);
    applicant.setHeighestQualification(this.heighestQualification);
    applicant.setResumeHeader(this.resumeHeader);
    applicant.setReferrerEmail(this.referrerEmail);
    applicant.setReferrerName(this.referrerName);
    applicant.setEmployeecode(this.employeecode);
    applicant.setAppliedDate(new Date());
    applicant.setNoofyearsexp(this.noofyearsexp);
    applicant.setRelyearsofexp(this.relyearsofexp);
    applicant.setPrimarySkill(this.primarySkill);
    applicant.setPrimarySkillOther(this.primarySkillOther);
    applicant.setCurrectctc(this.currectctc);
    applicant.setExpectedctc(this.expectedctc);
    FormFile myFile = getResumeData();
    if (myFile != null)
    {
      String contentType = myFile.getContentType();
      String fileName = myFile.getFileName();
      int fileSize = myFile.getFileSize();
      byte[] fileData = myFile.getFileData();
      int limitfileSize = new Integer(Constant.getValue("attachment.file.size.limit")).intValue();
      if (fileSize > limitfileSize)
      {
        request.setAttribute("attachmentsizeexceed", "yes");
      }
      else
      {
        applicant.setResumename(fileName);
        Blob blob = null;
        
        blob = new SerialBlob(fileData);
        ApplicantAttachments attach = new ApplicantAttachments();
        attach.setAttachmentdata(blob);
        attach.setUuid(UUID.randomUUID().toString());
        attach.setAttahmentname(fileName);
        applicant.setApplicantAttachment(attach);
        this.attachmentuuid = attach.getUuid();
      }
    }
    if (this.countryId != 0L)
    {
      Country country = new Country();
      country.setCountryId(this.countryId);
      applicant.setCountry(country);
    }
    else
    {
      applicant.setCountry(null);
    }
    if (this.stateId != 0L)
    {
      State state = new State();
      state.setStateId(this.stateId);
      applicant.setState(state);
    }
    else
    {
      applicant.setState(null);
    }
    applicant.setStatus("A");
    if (!StringUtils.isNullOrEmpty(applicant.getInterviewState())) {
      applicant.setInterviewState(applicant.getInterviewState());
    } else {
      applicant.setInterviewState("Application Submitted");
    }
    applicant.setAlternateemail(this.alternateemail);
    applicant.setAlternatephone(this.alternatephone);
    applicant.setPreferedlocation(this.preferedlocation);
    applicant.setCurrentdesignation(this.currentdesignation);
    applicant.setNote(this.note);
    applicant.setNoticeperiod(this.noticeperiod);
    applicant.setPassportno(this.passportno);
    applicant.setSsnno(this.ssnno);
    applicant.setTaxidno(this.taxidno);
    
    setOtherDetails(applicant, request);
    
    long superUserKey = 0L;
    User user1 = (User)request.getSession().getAttribute("user_data");
    if (user1 != null) {
      superUserKey = user1.getSuper_user_key();
    } else {
      superUserKey = applicant.getSuper_user_key();
    }
    Map m = VariableDataCaptureUtil.captureCustomVariables(request, this.applicantId, this.screenCode, superUserKey);
    
    this.formVariablesList = ((List)m.get("formVariablesList"));
    this.errorListCustomVariable = ((List)m.get("errorList"));
    this.formVariableDataList = ((List)m.get("formVariableDataList"));
    
    return applicant;
  }
  
  public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)
  {
    ActionErrors errors = new ActionErrors();
    if (StringUtils.isNullOrEmpty(this.email)) {}
    return errors;
  }
  
  public String getReferrerName()
  {
    return this.referrerName;
  }
  
  public void setReferrerName(String referrerName)
  {
    this.referrerName = referrerName;
  }
  
  public String getReferrerEmail()
  {
    return this.referrerEmail;
  }
  
  public void setReferrerEmail(String referrerEmail)
  {
    this.referrerEmail = referrerEmail;
  }
  
  public EvaluationTemplate getEvaluationTmpl()
  {
    return this.evaluationTmpl;
  }
  
  public void setEvaluationTmpl(EvaluationTemplate evaluationTmpl)
  {
    this.evaluationTmpl = evaluationTmpl;
  }
  
  public List getEvaluationTmplList()
  {
    return this.evaluationTmplList;
  }
  
  public void setEvaluationTmplList(List evaluationTmplList)
  {
    this.evaluationTmplList = evaluationTmplList;
  }
  
  public String getFullName()
  {
    return this.fullName;
  }
  
  public void setFullName(String fullName)
  {
    this.fullName = fullName;
  }
  
  public String getGender()
  {
    return this.gender;
  }
  
  public void setGender(String gender)
  {
    this.gender = gender;
  }
  
  public String getDateofbirth()
  {
    return this.dateofbirth;
  }
  
  public void setDateofbirth(String dateofbirth)
  {
    this.dateofbirth = dateofbirth;
  }
  
  public long getRequitionId()
  {
    return this.requitionId;
  }
  
  public void setRequitionId(long requitionId)
  {
    this.requitionId = requitionId;
  }
  
  public String getJobTitle()
  {
    return this.jobTitle;
  }
  
  public void setJobTitle(String jobTitle)
  {
    this.jobTitle = jobTitle;
  }
  
  public String getPreviousOrganization()
  {
    return this.previousOrganization;
  }
  
  public void setPreviousOrganization(String previousOrganization)
  {
    this.previousOrganization = previousOrganization;
  }
  
  public String getResumehtmlfilename()
  {
    return this.resumehtmlfilename;
  }
  
  public void setResumehtmlfilename(String resumehtmlfilename)
  {
    this.resumehtmlfilename = resumehtmlfilename;
  }
  
  public String getRequisitionName()
  {
    return this.requisitionName;
  }
  
  public void setRequisitionName(String requisitionName)
  {
    this.requisitionName = requisitionName;
  }
  
  public String getAppliedcri()
  {
    return this.appliedcri;
  }
  
  public void setAppliedcri(String appliedcri)
  {
    this.appliedcri = appliedcri;
  }
  
  public String getSearchapplieddate()
  {
    return this.searchapplieddate;
  }
  
  public void setSearchapplieddate(String searchapplieddate)
  {
    this.searchapplieddate = searchapplieddate;
  }
  
  public String getTargerofferdate()
  {
    return this.targerofferdate;
  }
  
  public void setTargerofferdate(String targerofferdate)
  {
    this.targerofferdate = targerofferdate;
  }
  
  public String getOfferInitiationComment()
  {
    return this.offerInitiationComment;
  }
  
  public void setOfferInitiationComment(String offerInitiationComment)
  {
    this.offerInitiationComment = offerInitiationComment;
  }
  
  public List getCurrencycodeList()
  {
    return this.currencycodeList;
  }
  
  public void setCurrencycodeList(List currencycodeList)
  {
    this.currencycodeList = currencycodeList;
  }
  
  public String getCurrectctccurrencycode()
  {
    return this.currectctccurrencycode;
  }
  
  public void setCurrectctccurrencycode(String currectctccurrencycode)
  {
    this.currectctccurrencycode = currectctccurrencycode;
  }
  
  public String getExpectedctccurrencycode()
  {
    return this.expectedctccurrencycode;
  }
  
  public void setExpectedctccurrencycode(String expectedctccurrencycode)
  {
    this.expectedctccurrencycode = expectedctccurrencycode;
  }
  
  public long getOfferownerId()
  {
    return this.offerownerId;
  }
  
  public void setOfferownerId(long offerownerId)
  {
    this.offerownerId = offerownerId;
  }
  
  public String getOfferownerName()
  {
    return this.offerownerName;
  }
  
  public void setOfferownerName(String offerownerName)
  {
    this.offerownerName = offerownerName;
  }
  
  public String getOfferReleaseComment()
  {
    return this.offerReleaseComment;
  }
  
  public void setOfferReleaseComment(String offerReleaseComment)
  {
    this.offerReleaseComment = offerReleaseComment;
  }
  
  public Date getOfferreleasedate()
  {
    return this.offerreleasedate;
  }
  
  public void setOfferreleasedate(Date offerreleasedate)
  {
    this.offerreleasedate = offerreleasedate;
  }
  
  public String getOfferreleaseddby()
  {
    return this.offerreleaseddby;
  }
  
  public void setOfferreleaseddby(String offerreleaseddby)
  {
    this.offerreleaseddby = offerreleaseddby;
  }
  
  public Date getOfferinitiationdate()
  {
    return this.offerinitiationdate;
  }
  
  public void setOfferinitiationdate(Date offerinitiationdate)
  {
    this.offerinitiationdate = offerinitiationdate;
  }
  
  public String getOfferinitiatedby()
  {
    return this.offerinitiatedby;
  }
  
  public void setOfferinitiatedby(String offerinitiatedby)
  {
    this.offerinitiatedby = offerinitiatedby;
  }
  
  public List getOfferApproverList()
  {
    return this.offerApproverList;
  }
  
  public void setOfferApproverList(List offerApproverList)
  {
    this.offerApproverList = offerApproverList;
  }
  
  public FormFile getAttachmentdata()
  {
    return this.attachmentdata;
  }
  
  public void setAttachmentdata(FormFile attachmentdata)
  {
    this.attachmentdata = attachmentdata;
  }
  
  public List getOfferAttachmentList()
  {
    return this.offerAttachmentList;
  }
  
  public void setOfferAttachmentList(List offerAttachmentList)
  {
    this.offerAttachmentList = offerAttachmentList;
  }
  
  public String getCurrectctc()
  {
    return this.currectctc;
  }
  
  public void setCurrectctc(String currectctc)
  {
    this.currectctc = currectctc;
  }
  
  public String getExpectedctc()
  {
    return this.expectedctc;
  }
  
  public void setExpectedctc(String expectedctc)
  {
    this.expectedctc = expectedctc;
  }
  
  public List getOfferwatchList()
  {
    return this.offerwatchList;
  }
  
  public void setOfferwatchList(List offerwatchList)
  {
    this.offerwatchList = offerwatchList;
  }
  
  public String getIsOfferEmailSent()
  {
    return this.isOfferEmailSent;
  }
  
  public void setIsOfferEmailSent(String isOfferEmailSent)
  {
    this.isOfferEmailSent = isOfferEmailSent;
  }
  
  public List getOfferReleaseAttachmentList()
  {
    return this.offerReleaseAttachmentList;
  }
  
  public void setOfferReleaseAttachmentList(List offerReleaseAttachmentList)
  {
    this.offerReleaseAttachmentList = offerReleaseAttachmentList;
  }
  
  public String getOfferedctc()
  {
    return this.offeredctc;
  }
  
  public void setOfferedctc(String offeredctc)
  {
    this.offeredctc = offeredctc;
  }
  
  public String getOfferedctccurrencycode()
  {
    return this.offeredctccurrencycode;
  }
  
  public void setOfferedctccurrencycode(String offeredctccurrencycode)
  {
    this.offeredctccurrencycode = offeredctccurrencycode;
  }
  
  public String getTargetjoiningdate()
  {
    return this.targetjoiningdate;
  }
  
  public void setTargetjoiningdate(String targetjoiningdate)
  {
    this.targetjoiningdate = targetjoiningdate;
  }
  
  public String getJoiningdate()
  {
    return this.joiningdate;
  }
  
  public void setJoiningdate(String joiningdate)
  {
    this.joiningdate = joiningdate;
  }
  
  public long getEmailtemplateid()
  {
    return this.emailtemplateid;
  }
  
  public void setEmailtemplateid(long emailtemplateid)
  {
    this.emailtemplateid = emailtemplateid;
  }
  
  public String getEmailtemplate()
  {
    return this.emailtemplate;
  }
  
  public void setEmailtemplate(String emailtemplate)
  {
    this.emailtemplate = emailtemplate;
  }
  
  public String getOfferacceptedcomment()
  {
    return this.offeracceptedcomment;
  }
  
  public void setOfferacceptedcomment(String offeracceptedcomment)
  {
    this.offeracceptedcomment = offeracceptedcomment;
  }
  
  public Date getOfferaccepteddate()
  {
    return this.offeraccepteddate;
  }
  
  public void setOfferaccepteddate(Date offeraccepteddate)
  {
    this.offeraccepteddate = offeraccepteddate;
  }
  
  public String getOfferdeclinedcomment()
  {
    return this.offerdeclinedcomment;
  }
  
  public void setOfferdeclinedcomment(String offerdeclinedcomment)
  {
    this.offerdeclinedcomment = offerdeclinedcomment;
  }
  
  public Date getOfferdeclineddate()
  {
    return this.offerdeclineddate;
  }
  
  public void setOfferdeclineddate(Date offerdeclineddate)
  {
    this.offerdeclineddate = offerdeclineddate;
  }
  
  public int getOfferdeclinedresonid()
  {
    return this.offerdeclinedresonid;
  }
  
  public void setOfferdeclinedresonid(int offerdeclinedresonid)
  {
    this.offerdeclinedresonid = offerdeclinedresonid;
  }
  
  public String getOfferdeclinedreason()
  {
    return this.offerdeclinedreason;
  }
  
  public void setOfferdeclinedreason(String offerdeclinedreason)
  {
    this.offerdeclinedreason = offerdeclinedreason;
  }
  
  public List getOfferdeclinedreasonslist()
  {
    return this.offerdeclinedreasonslist;
  }
  
  public void setOfferdeclinedreasonslist(List offerdeclinedreasonslist)
  {
    this.offerdeclinedreasonslist = offerdeclinedreasonslist;
  }
  
  public String getChangeofferedctc()
  {
    return this.changeofferedctc;
  }
  
  public void setChangeofferedctc(String changeofferedctc)
  {
    this.changeofferedctc = changeofferedctc;
  }
  
  public String getChangeofferedctccurrencycode()
  {
    return this.changeofferedctccurrencycode;
  }
  
  public void setChangeofferedctccurrencycode(String changeofferedctccurrencycode)
  {
    this.changeofferedctccurrencycode = changeofferedctccurrencycode;
  }
  
  public String getChangeofferComment()
  {
    return this.changeofferComment;
  }
  
  public void setChangeofferComment(String changeofferComment)
  {
    this.changeofferComment = changeofferComment;
  }
  
  public String getChagetargetjoiningdate()
  {
    return this.chagetargetjoiningdate;
  }
  
  public void setChagetargetjoiningdate(String chagetargetjoiningdate)
  {
    this.chagetargetjoiningdate = chagetargetjoiningdate;
  }
  
  public String getOffercancelledcomment()
  {
    return this.offercancelledcomment;
  }
  
  public void setOffercancelledcomment(String offercancelledcomment)
  {
    this.offercancelledcomment = offercancelledcomment;
  }
  
  public Date getOffercancelleddate()
  {
    return this.offercancelleddate;
  }
  
  public void setOffercancelleddate(Date offercancelleddate)
  {
    this.offercancelleddate = offercancelleddate;
  }
  
  public int getOffercancelledresonid()
  {
    return this.offercancelledresonid;
  }
  
  public void setOffercancelledresonid(int offercancelledresonid)
  {
    this.offercancelledresonid = offercancelledresonid;
  }
  
  public String getOffercancelledby()
  {
    return this.offercancelledby;
  }
  
  public void setOffercancelledby(String offercancelledby)
  {
    this.offercancelledby = offercancelledby;
  }
  
  public String getOffercancelledreason()
  {
    return this.offercancelledreason;
  }
  
  public void setOffercancelledreason(String offercancelledreason)
  {
    this.offercancelledreason = offercancelledreason;
  }
  
  public String getIsoffercancelemailsent()
  {
    return this.isoffercancelemailsent;
  }
  
  public void setIsoffercancelemailsent(String isoffercancelemailsent)
  {
    this.isoffercancelemailsent = isoffercancelemailsent;
  }
  
  public Date getOffercancelemailsentdate()
  {
    return this.offercancelemailsentdate;
  }
  
  public void setOffercancelemailsentdate(Date offercancelemailsentdate)
  {
    this.offercancelemailsentdate = offercancelemailsentdate;
  }
  
  public List getRefrenceslist()
  {
    return this.refrenceslist;
  }
  
  public void setRefrenceslist(List refrenceslist)
  {
    this.refrenceslist = refrenceslist;
  }
  
  public String getResumedetails()
  {
    return this.resumedetails;
  }
  
  public void setResumedetails(String resumedetails)
  {
    this.resumedetails = resumedetails;
  }
  
  public String getJoineddate()
  {
    return this.joineddate;
  }
  
  public void setJoineddate(String joineddate)
  {
    this.joineddate = joineddate;
  }
  
  public String getJoinedcomment()
  {
    return this.joinedcomment;
  }
  
  public void setJoinedcomment(String joinedcomment)
  {
    this.joinedcomment = joinedcomment;
  }
  
  public String getVendorName()
  {
    return this.vendorName;
  }
  
  public void setVendorName(String vendorName)
  {
    this.vendorName = vendorName;
  }
  
  public List getVendorList()
  {
    return this.vendorList;
  }
  
  public void setVendorList(List vendorList)
  {
    this.vendorList = vendorList;
  }
  
  public List getInterviewstateList()
  {
    return this.interviewstateList;
  }
  
  public void setInterviewstateList(List interviewstateList)
  {
    this.interviewstateList = interviewstateList;
  }
  
  public String getUuid()
  {
    return this.uuid;
  }
  
  public void setUuid(String uuid)
  {
    this.uuid = uuid;
  }
  
  public List getJobtitleList()
  {
    return this.jobtitleList;
  }
  
  public void setJobtitleList(List jobtitleList)
  {
    this.jobtitleList = jobtitleList;
  }
  
  public List getSavedsearchesList()
  {
    return this.savedsearchesList;
  }
  
  public void setSavedsearchesList(List savedsearchesList)
  {
    this.savedsearchesList = savedsearchesList;
  }
  
  public long getInterviewOrganizerId()
  {
    return this.interviewOrganizerId;
  }
  
  public void setInterviewOrganizerId(long interviewOrganizerId)
  {
    this.interviewOrganizerId = interviewOrganizerId;
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
  
  public List getApplicantList()
  {
    return this.applicantList;
  }
  
  public void setApplicantList(List applicantList)
  {
    this.applicantList = applicantList;
  }
  
  public List getInitiateOfferwatchList()
  {
    return this.initiateOfferwatchList;
  }
  
  public void setInitiateOfferwatchList(List initiateOfferwatchList)
  {
    this.initiateOfferwatchList = initiateOfferwatchList;
  }
  
  public String[] getInterviewstatecodes()
  {
    return this.interviewstatecodes;
  }
  
  public void setInterviewstatecodes(String[] interviewstatecodes)
  {
    this.interviewstatecodes = interviewstatecodes;
  }
  
  public List getEducationNamesList()
  {
    return this.educationNamesList;
  }
  
  public void setEducationNamesList(List educationNamesList)
  {
    this.educationNamesList = educationNamesList;
  }
  
  public List getEducationsList()
  {
    return this.educationsList;
  }
  
  public void setEducationsList(List educationsList)
  {
    this.educationsList = educationsList;
  }
  
  public String getEducationName()
  {
    return this.educationName;
  }
  
  public void setEducationName(String educationName)
  {
    this.educationName = educationName;
  }
  
  public String getSpecialization()
  {
    return this.specialization;
  }
  
  public void setSpecialization(String specialization)
  {
    this.specialization = specialization;
  }
  
  public String getInstituteName()
  {
    return this.instituteName;
  }
  
  public void setInstituteName(String instituteName)
  {
    this.instituteName = instituteName;
  }
  
  public String getPercentile()
  {
    return this.percentile;
  }
  
  public void setPercentile(String percentile)
  {
    this.percentile = percentile;
  }
  
  public String getPassingYear()
  {
    return this.passingYear;
  }
  
  public void setPassingYear(String passingYear)
  {
    this.passingYear = passingYear;
  }
  
  public List getCertificationsList()
  {
    return this.certificationsList;
  }
  
  public void setCertificationsList(List certificationsList)
  {
    this.certificationsList = certificationsList;
  }
  
  public String getEduType()
  {
    return this.eduType;
  }
  
  public void setEduType(String eduType)
  {
    this.eduType = eduType;
  }
  
  public String getCertName()
  {
    return this.certName;
  }
  
  public void setCertName(String certName)
  {
    this.certName = certName;
  }
  
  public String getCertorgName()
  {
    return this.certorgName;
  }
  
  public void setCertorgName(String certorgName)
  {
    this.certorgName = certorgName;
  }
  
  public String getCertpercentile()
  {
    return this.certpercentile;
  }
  
  public void setCertpercentile(String certpercentile)
  {
    this.certpercentile = certpercentile;
  }
  
  public String getCertpassingYear()
  {
    return this.certpassingYear;
  }
  
  public void setCertpassingYear(String certpassingYear)
  {
    this.certpassingYear = certpassingYear;
  }
  
  public List getPreviousOrgList()
  {
    return this.previousOrgList;
  }
  
  public void setPreviousOrgList(List previousOrgList)
  {
    this.previousOrgList = previousOrgList;
  }
  
  public String getPrevOrgName()
  {
    return this.prevOrgName;
  }
  
  public void setPrevOrgName(String prevOrgName)
  {
    this.prevOrgName = prevOrgName;
  }
  
  public String getRole()
  {
    return this.role;
  }
  
  public void setRole(String role)
  {
    this.role = role;
  }
  
  public String getStartDate()
  {
    return this.startDate;
  }
  
  public void setStartDate(String startDate)
  {
    this.startDate = startDate;
  }
  
  public String getEndDate()
  {
    return this.endDate;
  }
  
  public void setEndDate(String endDate)
  {
    this.endDate = endDate;
  }
  
  public String getReasonforleave()
  {
    return this.reasonforleave;
  }
  
  public void setReasonforleave(String reasonforleave)
  {
    this.reasonforleave = reasonforleave;
  }
  
  public List getApplicantAttachmentList()
  {
    return this.applicantAttachmentList;
  }
  
  public void setApplicantAttachmentList(List applicantAttachmentList)
  {
    this.applicantAttachmentList = applicantAttachmentList;
  }
  
  public String getApplicantNo()
  {
    return this.applicantNo;
  }
  
  public void setApplicantNo(String applicantNo)
  {
    this.applicantNo = applicantNo;
  }
  
  public String getAlternateemail()
  {
    return this.alternateemail;
  }
  
  public void setAlternateemail(String alternateemail)
  {
    this.alternateemail = alternateemail;
  }
  
  public String getAlternatephone()
  {
    return this.alternatephone;
  }
  
  public void setAlternatephone(String alternatephone)
  {
    this.alternatephone = alternatephone;
  }
  
  public String getPreferedlocation()
  {
    return this.preferedlocation;
  }
  
  public void setPreferedlocation(String preferedlocation)
  {
    this.preferedlocation = preferedlocation;
  }
  
  public String getCurrentdesignation()
  {
    return this.currentdesignation;
  }
  
  public void setCurrentdesignation(String currentdesignation)
  {
    this.currentdesignation = currentdesignation;
  }
  
  public String getNote()
  {
    return this.note;
  }
  
  public void setNote(String note)
  {
    this.note = note;
  }
  
  public String getNoticeperiod()
  {
    return this.noticeperiod;
  }
  
  public void setNoticeperiod(String noticeperiod)
  {
    this.noticeperiod = noticeperiod;
  }
  
  public String getPassportno()
  {
    return this.passportno;
  }
  
  public void setPassportno(String passportno)
  {
    this.passportno = passportno;
  }
  
  public List getSkillList()
  {
    return this.skillList;
  }
  
  public void setSkillList(List skillList)
  {
    this.skillList = skillList;
  }
  
  public List getSkillYearsList()
  {
    return this.skillYearsList;
  }
  
  public void setSkillYearsList(List skillYearsList)
  {
    this.skillYearsList = skillYearsList;
  }
  
  public List getSkillRatingList()
  {
    return this.skillRatingList;
  }
  
  public void setSkillRatingList(List skillRatingList)
  {
    this.skillRatingList = skillRatingList;
  }
  
  public String getSkillname()
  {
    return this.skillname;
  }
  
  public void setSkillname(String skillname)
  {
    this.skillname = skillname;
  }
  
  public String getYearsofexpskill()
  {
    return this.yearsofexpskill;
  }
  
  public void setYearsofexpskill(String yearsofexpskill)
  {
    this.yearsofexpskill = yearsofexpskill;
  }
  
  public String getRatingskill()
  {
    return this.ratingskill;
  }
  
  public void setRatingskill(String ratingskill)
  {
    this.ratingskill = ratingskill;
  }
  
  public List getApplicantSkillList()
  {
    return this.applicantSkillList;
  }
  
  public void setApplicantSkillList(List applicantSkillList)
  {
    this.applicantSkillList = applicantSkillList;
  }
  
  public int getSourceTypeId()
  {
    return this.sourceTypeId;
  }
  
  public void setSourceTypeId(int sourceTypeId)
  {
    this.sourceTypeId = sourceTypeId;
  }
  
  public List getSourceTypeList()
  {
    return this.sourceTypeList;
  }
  
  public void setSourceTypeList(List sourceTypeList)
  {
    this.sourceTypeList = sourceTypeList;
  }
  
  public List getSourceList()
  {
    return this.sourceList;
  }
  
  public void setSourceList(List sourceList)
  {
    this.sourceList = sourceList;
  }
  
  public long getVendorId()
  {
    return this.vendorId;
  }
  
  public void setVendorId(long vendorId)
  {
    this.vendorId = vendorId;
  }
  
  public List getVendorsList()
  {
    return this.vendorsList;
  }
  
  public void setVendorsList(List vendorsList)
  {
    this.vendorsList = vendorsList;
  }
  
  public void setSourceId(long sourceId)
  {
    this.sourceId = sourceId;
  }
  
  public long getSourceId()
  {
    return this.sourceId;
  }
  
  public String getPrimarySkill()
  {
    return this.primarySkill;
  }
  
  public void setPrimarySkill(String primarySkill)
  {
    this.primarySkill = primarySkill;
  }
  
  public String getEmployeecode()
  {
    return this.employeecode;
  }
  
  public void setEmployeecode(String employeecode)
  {
    this.employeecode = employeecode;
  }
  
  public String getHidesource()
  {
    return this.hidesource;
  }
  
  public void setHidesource(String hidesource)
  {
    this.hidesource = hidesource;
  }
  
  public String getReportingToName()
  {
    return this.reportingToName;
  }
  
  public void setReportingToName(String reportingToName)
  {
    this.reportingToName = reportingToName;
  }
  
  public String getNoofrows()
  {
    return this.noofrows;
  }
  
  public void setNoofrows(String noofrows)
  {
    this.noofrows = noofrows;
  }
  
  public List getPaginationRowsList()
  {
    return this.paginationRowsList;
  }
  
  public void setPaginationRowsList(List paginationRowsList)
  {
    this.paginationRowsList = paginationRowsList;
  }
  
  public String getResigneddate()
  {
    return this.resigneddate;
  }
  
  public void setResigneddate(String resigneddate)
  {
    this.resigneddate = resigneddate;
  }
  
  public String getResignedcomment()
  {
    return this.resignedcomment;
  }
  
  public void setResignedcomment(String resignedcomment)
  {
    this.resignedcomment = resignedcomment;
  }
  
  public ApplicantUser getApplicantuser()
  {
    return this.applicantuser;
  }
  
  public void setApplicantuser(ApplicantUser applicantuser)
  {
    this.applicantuser = applicantuser;
  }
  
  public List getSearchCriDateList()
  {
    return this.searchCriDateList;
  }
  
  public void setSearchCriDateList(List searchCriDateList)
  {
    this.searchCriDateList = searchCriDateList;
  }
  
  public String getSearchfromdate()
  {
    return this.searchfromdate;
  }
  
  public void setSearchfromdate(String searchfromdate)
  {
    this.searchfromdate = searchfromdate;
  }
  
  public String getSearchtodate()
  {
    return this.searchtodate;
  }
  
  public void setSearchtodate(String searchtodate)
  {
    this.searchtodate = searchtodate;
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
  
  public List getProjectcodeList()
  {
    return this.projectcodeList;
  }
  
  public void setProjectcodeList(List projectcodeList)
  {
    this.projectcodeList = projectcodeList;
  }
  
  public long getOrgId()
  {
    return this.orgId;
  }
  
  public void setOrgId(long orgId)
  {
    this.orgId = orgId;
  }
  
  public long getDepartmentId()
  {
    return this.departmentId;
  }
  
  public void setDepartmentId(long departmentId)
  {
    this.departmentId = departmentId;
  }
  
  public long getProjectcodeId()
  {
    return this.projectcodeId;
  }
  
  public void setProjectcodeId(long projectcodeId)
  {
    this.projectcodeId = projectcodeId;
  }
  
  public String getInitiateJoiningProcess()
  {
    return this.initiateJoiningProcess;
  }
  
  public void setInitiateJoiningProcess(String initiateJoiningProcess)
  {
    this.initiateJoiningProcess = initiateJoiningProcess;
  }
  
  public List getOnboardingProcessStatusList()
  {
    return this.onboardingProcessStatusList;
  }
  
  public void setOnboardingProcessStatusList(List onboardingProcessStatusList)
  {
    this.onboardingProcessStatusList = onboardingProcessStatusList;
  }
  
  public String getOnboardingProcessStatus()
  {
    return this.onboardingProcessStatus;
  }
  
  public void setOnboardingProcessStatus(String onboardingProcessStatus)
  {
    this.onboardingProcessStatus = onboardingProcessStatus;
  }
  
  public String getOwnerName()
  {
    return this.ownerName;
  }
  
  public void setOwnerName(String ownerName)
  {
    this.ownerName = ownerName;
  }
  
  public List getActivityList()
  {
    return this.activityList;
  }
  
  public void setActivityList(List activityList)
  {
    this.activityList = activityList;
  }
  
  public String getComment()
  {
    return this.comment;
  }
  
  public void setComment(String comment)
  {
    this.comment = comment;
  }
  
  public long getTagId()
  {
    return this.tagId;
  }
  
  public void setTagId(long tagId)
  {
    this.tagId = tagId;
  }
  
  public String getTagName()
  {
    return this.tagName;
  }
  
  public void setTagName(String tagName)
  {
    this.tagName = tagName;
  }
  
  public List getTagList()
  {
    return this.tagList;
  }
  
  public void setTagList(List tagList)
  {
    this.tagList = tagList;
  }
  
  public List getActionAttachmentList()
  {
    return this.actionAttachmentList;
  }
  
  public void setActionAttachmentList(List actionAttachmentList)
  {
    this.actionAttachmentList = actionAttachmentList;
  }
  
  public String getStartingYear()
  {
    return this.startingYear;
  }
  
  public void setStartingYear(String startingYear)
  {
    this.startingYear = startingYear;
  }
  
  public ApplicantUserActions getApplicantuseraction()
  {
    return this.applicantuseraction;
  }
  
  public void setApplicantuseraction(ApplicantUserActions applicantuseraction)
  {
    this.applicantuseraction = applicantuseraction;
  }
  
  public List getCommentList()
  {
    return this.commentList;
  }
  
  public void setCommentList(List commentList)
  {
    this.commentList = commentList;
  }
  
  public String getOfferEmailSentError()
  {
    return this.offerEmailSentError;
  }
  
  public void setOfferEmailSentError(String offerEmailSentError)
  {
    this.offerEmailSentError = offerEmailSentError;
  }
  
  public UserGroup getOwnerGroup()
  {
    return this.ownerGroup;
  }
  
  public void setOwnerGroup(UserGroup ownerGroup)
  {
    this.ownerGroup = ownerGroup;
  }
  
  public String getIsGroup()
  {
    return this.isGroup;
  }
  
  public void setIsGroup(String isGroup)
  {
    this.isGroup = isGroup;
  }
  
  public FormFile getProfilePhoto()
  {
    return this.profilePhoto;
  }
  
  public void setProfilePhoto(FormFile profilePhoto)
  {
    this.profilePhoto = profilePhoto;
  }
  
  public long getProfilePhotoId()
  {
    return this.profilePhotoId;
  }
  
  public void setProfilePhotoId(long profilePhotoId)
  {
    this.profilePhotoId = profilePhotoId;
  }
  
  public long getTalentPoolId()
  {
    return this.talentPoolId;
  }
  
  public String getBackUrl()
  {
    return this.backUrl;
  }
  
  public void setTalentPoolId(long talentPoolId)
  {
    this.talentPoolId = talentPoolId;
  }
  
  public void setBackUrl(String backUrl)
  {
    this.backUrl = backUrl;
  }
  
  public List getTalentPoolList()
  {
    return this.talentPoolList;
  }
  
  public void setTalentPoolList(List talentPoolList)
  {
    this.talentPoolList = talentPoolList;
  }
  
  public String getIsofferownerGroup()
  {
    return this.isofferownerGroup;
  }
  
  public void setIsofferownerGroup(String isofferownerGroup)
  {
    this.isofferownerGroup = isofferownerGroup;
  }
  
  public Recruiter getRecruiter()
  {
    return this.recruiter;
  }
  
  public void setRecruiter(Recruiter recruiter)
  {
    this.recruiter = recruiter;
  }
  
  public String getSsnno()
  {
    return this.ssnno;
  }
  
  public void setSsnno(String ssnno)
  {
    this.ssnno = ssnno;
  }
  
  public String getTaxidno()
  {
    return this.taxidno;
  }
  
  public void setTaxidno(String taxidno)
  {
    this.taxidno = taxidno;
  }
  
  public String getCity_Previousorg()
  {
    return this.city_Previousorg;
  }
  
  public List getIduserlistVal()
  {
    return this.iduserlistVal;
  }
  
  public void setCity_Previousorg(String cityPreviousorg)
  {
    this.city_Previousorg = cityPreviousorg;
  }
  
  public long getCountry_id_prevorg()
  {
    return this.country_id_prevorg;
  }
  
  public void setCountry_id_prevorg(long countryIdPrevorg)
  {
    this.country_id_prevorg = countryIdPrevorg;
  }
  
  public long getState_id_prevorg()
  {
    return this.state_id_prevorg;
  }
  
  public void setState_id_prevorg(long stateIdPrevorg)
  {
    this.state_id_prevorg = stateIdPrevorg;
  }
  
  public String getLastSalary()
  {
    return this.lastSalary;
  }
  
  public void setLastSalary(String lastSalary)
  {
    this.lastSalary = lastSalary;
  }
  
  public String getBonus()
  {
    return this.bonus;
  }
  
  public void setBonus(String bonus)
  {
    this.bonus = bonus;
  }
  
  public int getCurrency_id_prevorg()
  {
    return this.currency_id_prevorg;
  }
  
  public void setCurrency_id_prevorg(int currencyIdPrevorg)
  {
    this.currency_id_prevorg = currencyIdPrevorg;
  }
  
  public String getResponsibilities()
  {
    return this.responsibilities;
  }
  
  public void setResponsibilities(String responsibilities)
  {
    this.responsibilities = responsibilities;
  }
  
  public String getEmployercontactName()
  {
    return this.employercontactName;
  }
  
  public void setEmployercontactName(String employercontactName)
  {
    this.employercontactName = employercontactName;
  }
  
  public String getEmployercontactPhone()
  {
    return this.employercontactPhone;
  }
  
  public void setEmployercontactPhone(String employercontactPhone)
  {
    this.employercontactPhone = employercontactPhone;
  }
  
  public List getCurrencyList()
  {
    return this.currencyList;
  }
  
  public void setCurrencyList(List currencyList)
  {
    this.currencyList = currencyList;
  }
  
  public void setIduserlistVal(List iduserlistVal)
  {
    this.iduserlistVal = iduserlistVal;
  }
  
  public String getPassword()
  {
    return this.password;
  }
  
  public void setPassword(String password)
  {
    this.password = password;
  }
  
  public String getCpassword()
  {
    return this.cpassword;
  }
  
  public void setCpassword(String cpassword)
  {
    this.cpassword = cpassword;
  }
  
  public String getCurrentpassword()
  {
    return this.currentpassword;
  }
  
  public void setCurrentpassword(String currentpassword)
  {
    this.currentpassword = currentpassword;
  }
  
  public List getLovList()
  {
    return this.lovList;
  }
  
  public void setLovList(List lovList)
  {
    this.lovList = lovList;
  }
  
  public String getPrimarySkillOther()
  {
    return this.primarySkillOther;
  }
  
  public void setPrimarySkillOther(String primarySkillOther)
  {
    this.primarySkillOther = primarySkillOther;
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
  
  public List getErrorListCustomVariable()
  {
    return this.errorListCustomVariable;
  }
  
  public void setErrorListCustomVariable(List errorListCustomVariable)
  {
    this.errorListCustomVariable = errorListCustomVariable;
  }
  
  public String getFilePath()
  {
    return this.filePath;
  }
  
  public void setFilePath(String filePath)
  {
    this.filePath = filePath;
  }
  
  public String getKeywords()
  {
    return this.keywords;
  }
  
  public void setKeywords(String keywords)
  {
    this.keywords = keywords;
  }
  
  public String getTalentPoolName()
  {
    return this.talentPoolName;
  }
  
  public void setTalentPoolName(String talentPoolName)
  {
    this.talentPoolName = talentPoolName;
  }
  
  public List getOtherBenefitsList()
  {
    return this.otherBenefitsList;
  }
  
  public void setOtherBenefitsList(List otherBenefitsList)
  {
    this.otherBenefitsList = otherBenefitsList;
  }
  
  public List getMocktestList()
  {
    return this.mocktestList;
  }
  
  public void setMocktestList(List mocktestList)
  {
    this.mocktestList = mocktestList;
  }
  
  public List getMockCatList()
  {
    return this.mockCatList;
  }
  
  public void setMockCatList(List mockCatList)
  {
    this.mockCatList = mockCatList;
  }
  
  public int getMockcatId()
  {
    return this.mockcatId;
  }
  
  public void setMockcatId(int mockcatId)
  {
    this.mockcatId = mockcatId;
  }
  
  public double getPassPercentage()
  {
    return this.passPercentage;
  }
  
  public void setPassPercentage(double passPercentage)
  {
    this.passPercentage = passPercentage;
  }
  
  public int getScribddocumentid()
  {
    return this.scribddocumentid;
  }
  
  public void setScribddocumentid(int scribddocumentid)
  {
    this.scribddocumentid = scribddocumentid;
  }
  
  public String getScribddocumentkey()
  {
    return this.scribddocumentkey;
  }
  
  public void setScribddocumentkey(String scribddocumentkey)
  {
    this.scribddocumentkey = scribddocumentkey;
  }
  
  public String getBackurltoapplicantlist()
  {
    return this.backurltoapplicantlist;
  }
  
  public void setBackurltoapplicantlist(String backurltoapplicantlist)
  {
    this.backurltoapplicantlist = backurltoapplicantlist;
  }
  
  public String getEarliest_start_date()
  {
    return this.earliest_start_date;
  }
  
  public void setEarliest_start_date(String earliestStartDate)
  {
    this.earliest_start_date = earliestStartDate;
  }
  
  public String getEarliest_start_date1()
  {
    return this.earliest_start_date1;
  }
  
  public void setEarliest_start_date1(String earliestStartDate1)
  {
    this.earliest_start_date1 = earliestStartDate1;
  }
  
  public String getWork_on_weekends()
  {
    return this.work_on_weekends;
  }
  
  public void setWork_on_weekends(String workOnWeekends)
  {
    this.work_on_weekends = workOnWeekends;
  }
  
  public String getWork_on_evenings()
  {
    return this.work_on_evenings;
  }
  
  public void setWork_on_evenings(String workOnEvenings)
  {
    this.work_on_evenings = workOnEvenings;
  }
  
  public String getWork_on_overtime()
  {
    return this.work_on_overtime;
  }
  
  public void setWork_on_overtime(String workOnOvertime)
  {
    this.work_on_overtime = workOnOvertime;
  }
  
  public String getWant_to_relocate()
  {
    return this.want_to_relocate;
  }
  
  public void setWant_to_relocate(String wantToRelocate)
  {
    this.want_to_relocate = wantToRelocate;
  }
  
  public String getLanguages_spoken()
  {
    return this.languages_spoken;
  }
  
  public void setLanguages_spoken(String languagesSpoken)
  {
    this.languages_spoken = languagesSpoken;
  }
  
  public String getWork_status()
  {
    return this.work_status;
  }
  
  public void setWork_status(String workStatus)
  {
    this.work_status = workStatus;
  }
  
  public String getFelony_conviction()
  {
    return this.felony_conviction;
  }
  
  public void setFelony_conviction(String felonyConviction)
  {
    this.felony_conviction = felonyConviction;
  }
  
  public String getFelony_conviction_desc()
  {
    return this.felony_conviction_desc;
  }
  
  public void setFelony_conviction_desc(String felonyConvictionDesc)
  {
    this.felony_conviction_desc = felonyConvictionDesc;
  }
  
  public String getWebsite_url()
  {
    return this.website_url;
  }
  
  public void setWebsite_url(String websiteUrl)
  {
    this.website_url = websiteUrl;
  }
  
  public String getLinkedin_url()
  {
    return this.linkedin_url;
  }
  
  public void setLinkedin_url(String linkedinUrl)
  {
    this.linkedin_url = linkedinUrl;
  }
  
  public String getFacebook_url()
  {
    return this.facebook_url;
  }
  
  public void setFacebook_url(String facebookUrl)
  {
    this.facebook_url = facebookUrl;
  }
  
  public String getCollege_GPA()
  {
    return this.college_GPA;
  }
  
  public void setCollege_GPA(String collegeGPA)
  {
    this.college_GPA = collegeGPA;
  }
  
  public String getCollege_name()
  {
    return this.college_name;
  }
  
  public void setCollege_name(String collegeName)
  {
    this.college_name = collegeName;
  }
  
  public List getYesnofulllist()
  {
    return this.yesnofulllist;
  }
  
  public void setYesnofulllist(List yesnofulllist)
  {
    this.yesnofulllist = yesnofulllist;
  }
  
  public List getQuestionGroupList()
  {
    return this.questionGroupList;
  }
  
  public void setQuestionGroupList(List questionGroupList)
  {
    this.questionGroupList = questionGroupList;
  }
  
  public List getQuetionnaireList()
  {
    return this.quetionnaireList;
  }
  
  public void setQuetionnaireList(List quetionnaireList)
  {
    this.quetionnaireList = quetionnaireList;
  }
  
  public long getQuetionnaireId()
  {
    return this.quetionnaireId;
  }
  
  public void setQuetionnaireId(long quetionnaireId)
  {
    this.quetionnaireId = quetionnaireId;
  }
  
  public String getHeadingComment()
  {
    return this.headingComment;
  }
  
  public void setHeadingComment(String headingComment)
  {
    this.headingComment = headingComment;
  }
  
  public String getSourcetypename()
  {
    return this.sourcetypename;
  }
  
  public void setSourcetypename(String sourcetypename)
  {
    this.sourcetypename = sourcetypename;
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
  
  public String[] getInterviewStates()
  {
    return this.interviewStates;
  }
  
  public void setInterviewStates(String[] interviewStates)
  {
    this.interviewStates = interviewStates;
  }
  
  public long[] getVendorIds()
  {
    return this.vendorIds;
  }
  
  public void setVendorIds(long[] vendorIds)
  {
    this.vendorIds = vendorIds;
  }
  
  public long[] getTagIds()
  {
    return this.tagIds;
  }
  
  public void setTagIds(long[] tagIds)
  {
    this.tagIds = tagIds;
  }
  
  public long[] getProjectcodeIds()
  {
    return this.projectcodeIds;
  }
  
  public void setProjectcodeIds(long[] projectcodeIds)
  {
    this.projectcodeIds = projectcodeIds;
  }
  
  public long[] getRequitionIds()
  {
    return this.requitionIds;
  }
  
  public void setRequitionIds(long[] requitionIds)
  {
    this.requitionIds = requitionIds;
  }
  
  public ApplicantSearchCriteria getSearchcriteria()
  {
    return this.searchcriteria;
  }
  
  public void setSearchcriteria(ApplicantSearchCriteria searchcriteria)
  {
    this.searchcriteria = searchcriteria;
  }
  
  public List getCriteriaStringList()
  {
    return this.criteriaStringList;
  }
  
  public void setCriteriaStringList(List criteriaStringList)
  {
    this.criteriaStringList = criteriaStringList;
  }
  
  public List getCriteriaNumericList()
  {
    return this.criteriaNumericList;
  }
  
  public void setCriteriaNumericList(List criteriaNumericList)
  {
    this.criteriaNumericList = criteriaNumericList;
  }
  
  public String getApplicantNo_criteria()
  {
    return this.applicantNo_criteria;
  }
  
  public void setApplicantNo_criteria(String applicantNoCriteria)
  {
    this.applicantNo_criteria = applicantNoCriteria;
  }
  
  public String getApplicantName_criteria()
  {
    return this.applicantName_criteria;
  }
  
  public void setApplicantName_criteria(String applicantNameCriteria)
  {
    this.applicantName_criteria = applicantNameCriteria;
  }
  
  public String getDob_criteria()
  {
    return this.dob_criteria;
  }
  
  public void setDob_criteria(String dobCriteria)
  {
    this.dob_criteria = dobCriteria;
  }
  
  public String getPassport_criteria()
  {
    return this.passport_criteria;
  }
  
  public void setPassport_criteria(String passportCriteria)
  {
    this.passport_criteria = passportCriteria;
  }
  
  public String getSsn_criteria()
  {
    return this.ssn_criteria;
  }
  
  public void setSsn_criteria(String ssnCriteria)
  {
    this.ssn_criteria = ssnCriteria;
  }
  
  public String getTaxno_criteria()
  {
    return this.taxno_criteria;
  }
  
  public void setTaxno_criteria(String taxnoCriteria)
  {
    this.taxno_criteria = taxnoCriteria;
  }
  
  public String getEmail_criteria()
  {
    return this.email_criteria;
  }
  
  public void setEmail_criteria(String emailCriteria)
  {
    this.email_criteria = emailCriteria;
  }
  
  public String getCity_criteria()
  {
    return this.city_criteria;
  }
  
  public void setCity_criteria(String cityCriteria)
  {
    this.city_criteria = cityCriteria;
  }
  
  public String getOrg_criteria()
  {
    return this.org_criteria;
  }
  
  public void setOrg_criteria(String orgCriteria)
  {
    this.org_criteria = orgCriteria;
  }
  
  public long getApplicantId1()
  {
    return this.applicantId1;
  }
  
  public void setApplicantId1(long applicantId1)
  {
    this.applicantId1 = applicantId1;
  }
  
  public String getDateofbirth1()
  {
    return this.dateofbirth1;
  }
  
  public void setDateofbirth1(String dateofbirth1)
  {
    this.dateofbirth1 = dateofbirth1;
  }
  
  public String getCurrectctc_criteria()
  {
    return this.currectctc_criteria;
  }
  
  public void setCurrectctc_criteria(String currectctcCriteria)
  {
    this.currectctc_criteria = currectctcCriteria;
  }
  
  public String getExpectedctc_criteria()
  {
    return this.expectedctc_criteria;
  }
  
  public void setExpectedctc_criteria(String expectedctcCriteria)
  {
    this.expectedctc_criteria = expectedctcCriteria;
  }
  
  public String getNoticeperiod_criteria()
  {
    return this.noticeperiod_criteria;
  }
  
  public void setNoticeperiod_criteria(String noticeperiodCriteria)
  {
    this.noticeperiod_criteria = noticeperiodCriteria;
  }
  
  public String getCurrentdesignation_criteria()
  {
    return this.currentdesignation_criteria;
  }
  
  public void setCurrentdesignation_criteria(String currentdesignationCriteria)
  {
    this.currentdesignation_criteria = currentdesignationCriteria;
  }
  
  public String getNoofyearsexp_criteria()
  {
    return this.noofyearsexp_criteria;
  }
  
  public void setNoofyearsexp_criteria(String noofyearsexpCriteria)
  {
    this.noofyearsexp_criteria = noofyearsexpCriteria;
  }
  
  public String getResumeHeader_criteria()
  {
    return this.resumeHeader_criteria;
  }
  
  public void setResumeHeader_criteria(String resumeHeaderCriteria)
  {
    this.resumeHeader_criteria = resumeHeaderCriteria;
  }
  
  public String getEarliest_start_date_criteria()
  {
    return this.earliest_start_date_criteria;
  }
  
  public void setEarliest_start_date_criteria(String earliestStartDateCriteria)
  {
    this.earliest_start_date_criteria = earliestStartDateCriteria;
  }
  
  public String getLanguages_spoken_criteria()
  {
    return this.languages_spoken_criteria;
  }
  
  public void setLanguages_spoken_criteria(String languagesSpokenCriteria)
  {
    this.languages_spoken_criteria = languagesSpokenCriteria;
  }
  
  public double getNoofyearsexp()
  {
    return this.noofyearsexp;
  }
  
  public void setNoofyearsexp(double noofyearsexp)
  {
    this.noofyearsexp = noofyearsexp;
  }
  
  public double getNoofyearsexp1()
  {
    return this.noofyearsexp1;
  }
  
  public void setNoofyearsexp1(double noofyearsexp1)
  {
    this.noofyearsexp1 = noofyearsexp1;
  }
  
  public String getPreferedlocation_criteria()
  {
    return this.preferedlocation_criteria;
  }
  
  public void setPreferedlocation_criteria(String preferedlocationCriteria)
  {
    this.preferedlocation_criteria = preferedlocationCriteria;
  }
  
  public String[] getPrimarySkills()
  {
    return this.primarySkills;
  }
  
  public void setPrimarySkills(String[] primarySkills)
  {
    this.primarySkills = primarySkills;
  }
  
  public String getHeighestQualification_criteria()
  {
    return this.heighestQualification_criteria;
  }
  
  public void setHeighestQualification_criteria(String heighestQualificationCriteria)
  {
    this.heighestQualification_criteria = heighestQualificationCriteria;
  }
  
  public String getQualifications_criteria()
  {
    return this.qualifications_criteria;
  }
  
  public void setQualifications_criteria(String qualificationsCriteria)
  {
    this.qualifications_criteria = qualificationsCriteria;
  }
  
  public String getCollege_name_criteria()
  {
    return this.college_name_criteria;
  }
  
  public void setCollege_name_criteria(String collegeNameCriteria)
  {
    this.college_name_criteria = collegeNameCriteria;
  }
  
  public String getCollege_GPA_criteria()
  {
    return this.college_GPA_criteria;
  }
  
  public void setCollege_GPA_criteria(String collegeGPACriteria)
  {
    this.college_GPA_criteria = collegeGPACriteria;
  }
  
  public String getCollege_GPA1()
  {
    return this.college_GPA1;
  }
  
  public void setCollege_GPA1(String collegeGPA1)
  {
    this.college_GPA1 = collegeGPA1;
  }
  
  public String getAddress()
  {
    return this.address;
  }
  
  public void setAddress(String address)
  {
    this.address = address;
  }
  
  public String getZipcode()
  {
    return this.zipcode;
  }
  
  public void setZipcode(String zipcode)
  {
    this.zipcode = zipcode;
  }
  
  public String getMilesWithin()
  {
    return this.milesWithin;
  }
  
  public void setMilesWithin(String milesWithin)
  {
    this.milesWithin = milesWithin;
  }
  
  public String getFilterValue1()
  {
    return this.filterValue1;
  }
  
  public void setFilterValue1(String filterValue1)
  {
    this.filterValue1 = filterValue1;
  }
  
  public String getFilterValue2()
  {
    return this.filterValue2;
  }
  
  public void setFilterValue2(String filterValue2)
  {
    this.filterValue2 = filterValue2;
  }
  
  public List<SearchApplicantQuestions> getQuestionCriList()
  {
    return this.questionCriList;
  }
  
  public void setQuestionCriList(List<SearchApplicantQuestions> questionCriList)
  {
    this.questionCriList = questionCriList;
  }
  
  public String getSearchuuid()
  {
    return this.searchuuid;
  }
  
  public void setSearchuuid(String searchuuid)
  {
    this.searchuuid = searchuuid;
  }
  
  public Map<Long, SearchApplicantCustomFields> getCustomFieldData()
  {
    return this.customFieldData;
  }
  
  public void setCustomFieldData(Map<Long, SearchApplicantCustomFields> customFieldData)
  {
    this.customFieldData = customFieldData;
  }
  
  public SalaryPlan getSalaryPlan()
  {
    return this.salaryPlan;
  }
  
  public void setSalaryPlan(SalaryPlan salaryPlan)
  {
    this.salaryPlan = salaryPlan;
  }
  
  public List getCriteriaDateList()
  {
    return this.criteriaDateList;
  }
  
  public void setCriteriaDateList(List criteriaDateList)
  {
    this.criteriaDateList = criteriaDateList;
  }
  
  public String getCurrectctc1()
  {
    return this.currectctc1;
  }
  
  public void setCurrectctc1(String currectctc1)
  {
    this.currectctc1 = currectctc1;
  }
  
  public String getExpectedctc1()
  {
    return this.expectedctc1;
  }
  
  public void setExpectedctc1(String expectedctc1)
  {
    this.expectedctc1 = expectedctc1;
  }
  
  public String getScreenCode()
  {
    return this.screenCode;
  }
  
  public void setScreenCode(String screenCode)
  {
    this.screenCode = screenCode;
  }
  
  public double getScoreAve()
  {
    return this.scoreAve;
  }
  
  public void setScoreAve(double scoreAve)
  {
    this.scoreAve = scoreAve;
  }
  
  public int getFilterError()
  {
    return this.filterError;
  }
  
  public void setFilterError(int filterError)
  {
    this.filterError = filterError;
  }
  
  public long getNationalityId()
  {
    return this.nationalityId;
  }
  
  public void setNationalityId(long nationalityId)
  {
    this.nationalityId = nationalityId;
  }
  
  public String getNationalityName()
  {
    return this.nationalityName;
  }
  
  public void setNationalityName(String nationalityName)
  {
    this.nationalityName = nationalityName;
  }
  
  public long getApplicant_number()
  {
    return this.applicant_number;
  }
  
  public void setApplicant_number(long applicantNumber)
  {
    this.applicant_number = applicantNumber;
  }
  
  public List getAlreayappliedlist()
  {
    return this.alreayappliedlist;
  }
  
  public void setAlreayappliedlist(List alreayappliedlist)
  {
    this.alreayappliedlist = alreayappliedlist;
  }
  
  public String getEthnicRaceName()
  {
    return this.ethnicRaceName;
  }
  
  public void setEthnicRaceName(String ethnicRaceName)
  {
    this.ethnicRaceName = ethnicRaceName;
  }
  
  public String getTagNames()
  {
    return this.tagNames;
  }
  
  public void setTagNames(String tagNames)
  {
    this.tagNames = tagNames;
  }
  
  public List getMockQuestionSetList()
  {
    return this.mockQuestionSetList;
  }
  
  public void setMockQuestionSetList(List mockQuestionSetList)
  {
    this.mockQuestionSetList = mockQuestionSetList;
  }
  
  public String getMockquestionset_criteria()
  {
    return this.mockquestionset_criteria;
  }
  
  public void setMockquestionset_criteria(String mockquestionsetCriteria)
  {
    this.mockquestionset_criteria = mockquestionsetCriteria;
  }
  
  public String getMockquestionsetValue1()
  {
    return this.mockquestionsetValue1;
  }
  
  public void setMockquestionsetValue1(String mockquestionsetValue1)
  {
    this.mockquestionsetValue1 = mockquestionsetValue1;
  }
  
  public String getMockquestionsetValue2()
  {
    return this.mockquestionsetValue2;
  }
  
  public void setMockquestionsetValue2(String mockquestionsetValue2)
  {
    this.mockquestionsetValue2 = mockquestionsetValue2;
  }
  
  public int getMockQuestionSetId()
  {
    return this.mockQuestionSetId;
  }
  
  public void setMockQuestionSetId(int mockQuestionSetId)
  {
    this.mockQuestionSetId = mockQuestionSetId;
  }
  
  public double getRelyearsofexp()
  {
    return this.relyearsofexp;
  }
  
  public void setRelyearsofexp(double relyearsofexp)
  {
    this.relyearsofexp = relyearsofexp;
  }
  
  public String getAttachmentuuid()
  {
    return this.attachmentuuid;
  }
  
  public void setAttachmentuuid(String attachmentuuid)
  {
    this.attachmentuuid = attachmentuuid;
  }
  
  public long getVeteranDisabilityId()
  {
    return this.veteranDisabilityId;
  }
  
  public void setVeteranDisabilityId(long veteranDisabilityId)
  {
    this.veteranDisabilityId = veteranDisabilityId;
  }
  
  public String getVeteranDisabilityName()
  {
    return this.veteranDisabilityName;
  }
  
  public void setVeteranDisabilityName(String veteranDisabilityName)
  {
    this.veteranDisabilityName = veteranDisabilityName;
  }
  
  public List getVeteranDisabilityList()
  {
    return this.veteranDisabilityList;
  }
  
  public void setVeteranDisabilityList(List veteranDisabilityList)
  {
    this.veteranDisabilityList = veteranDisabilityList;
  }
  
  public List getCommentVisibleList()
  {
    return this.commentVisibleList;
  }
  
  public void setCommentVisibleList(List commentVisibleList)
  {
    this.commentVisibleList = commentVisibleList;
  }
  
  public String getCommentVisible()
  {
    return this.commentVisible;
  }
  
  public void setCommentVisible(String commentVisible)
  {
    this.commentVisible = commentVisible;
  }
}
