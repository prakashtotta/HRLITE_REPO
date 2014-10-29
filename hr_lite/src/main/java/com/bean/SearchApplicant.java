package com.bean;

import java.util.Date;
import java.util.List;

public class SearchApplicant
{
  public long searchappId;
  public int noofrows;
  public String savedsearchname;
  public String appliedcri;
  public String fromdate;
  public String todate;
  private String vendorId;
  private String reqId;
  public String fullName;
  public String email;
  public String interviewState;
  private String createdBy;
  private Date createdDate;
  private Date updatedDate;
  private String updatedBy;
  private String previousOrganization;
  private long saveduserid;
  private String saveshare;
  private String type;
  private String orgId;
  private String departmentId;
  private String projectcodeId;
  private String tagId;
  public String searchuuid;
  public String keyword;
  private long applicantId;
  private long applicantId1;
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
  private int sourceTypeId;
  private long sourceId;
  private String employeecode;
  private String referrerName;
  private String referrerEmail;
  private String gender;
  private String dateofbirth;
  private String dateofbirth1;
  private String passportno;
  private String ssnno;
  private String taxidno;
  private long countryId;
  private long stateId;
  private String city;
  private String felony_conviction;
  public double currectctc;
  public double expectedctc;
  public double currectctc1;
  public double expectedctc1;
  public double noticeperiod;
  public double noticeperiod1;
  public String currentdesignation;
  public double noofyearsexp;
  public double noofyearsexp1;
  public String resumeHeader;
  public String earliest_start_date;
  public String earliest_start_date1;
  public String work_on_weekends;
  public String work_on_evenings;
  public String work_on_overtime;
  public String want_to_relocate;
  public String languages_spoken;
  public String preferedlocation;
  public String primarySkill;
  public String heighestQualification;
  public String qualifications;
  public String college_name;
  public double college_GPA;
  public double college_GPA1;
  private int mockQuestionSetId;
  private double mockquestionsetValue1;
  private double mockquestionsetValue2;
  public long zipCode;
  public double milesWithin;
  List<SearchApplicantQuestions> questionCriList;
  List<SearchApplicantCustomFields> customFieldCriList;
  
  public double getNoticeperiod1()
  {
    return this.noticeperiod1;
  }
  
  public void setNoticeperiod1(double noticeperiod1)
  {
    this.noticeperiod1 = noticeperiod1;
  }
  
  public long getSearchappId()
  {
    return this.searchappId;
  }
  
  public void setSearchappId(long searchappId)
  {
    this.searchappId = searchappId;
  }
  
  public String getSavedsearchname()
  {
    return this.savedsearchname;
  }
  
  public void setSavedsearchname(String savedsearchname)
  {
    this.savedsearchname = savedsearchname;
  }
  
  public String getAppliedcri()
  {
    return this.appliedcri;
  }
  
  public void setAppliedcri(String appliedcri)
  {
    this.appliedcri = appliedcri;
  }
  
  public String getFromdate()
  {
    return this.fromdate;
  }
  
  public void setFromdate(String fromdate)
  {
    this.fromdate = fromdate;
  }
  
  public String getTodate()
  {
    return this.todate;
  }
  
  public void setTodate(String todate)
  {
    this.todate = todate;
  }
  
  public String getVendorId()
  {
    return this.vendorId;
  }
  
  public void setVendorId(String vendorId)
  {
    this.vendorId = vendorId;
  }
  
  public String getReqId()
  {
    return this.reqId;
  }
  
  public void setReqId(String reqId)
  {
    this.reqId = reqId;
  }
  
  public String getFullName()
  {
    return this.fullName;
  }
  
  public void setFullName(String fullName)
  {
    this.fullName = fullName;
  }
  
  public String getEmail()
  {
    return this.email;
  }
  
  public void setEmail(String email)
  {
    this.email = email;
  }
  
  public String getInterviewState()
  {
    return this.interviewState;
  }
  
  public void setInterviewState(String interviewState)
  {
    this.interviewState = interviewState;
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
  
  public String getPreviousOrganization()
  {
    return this.previousOrganization;
  }
  
  public void setPreviousOrganization(String previousOrganization)
  {
    this.previousOrganization = previousOrganization;
  }
  
  public long getSaveduserid()
  {
    return this.saveduserid;
  }
  
  public void setSaveduserid(long saveduserid)
  {
    this.saveduserid = saveduserid;
  }
  
  public String getSaveshare()
  {
    return this.saveshare;
  }
  
  public void setSaveshare(String saveshare)
  {
    this.saveshare = saveshare;
  }
  
  public String getType()
  {
    return this.type;
  }
  
  public void setType(String type)
  {
    this.type = type;
  }
  
  public String getOrgId()
  {
    return this.orgId;
  }
  
  public void setOrgId(String orgId)
  {
    this.orgId = orgId;
  }
  
  public String getDepartmentId()
  {
    return this.departmentId;
  }
  
  public void setDepartmentId(String departmentId)
  {
    this.departmentId = departmentId;
  }
  
  public String getProjectcodeId()
  {
    return this.projectcodeId;
  }
  
  public void setProjectcodeId(String projectcodeId)
  {
    this.projectcodeId = projectcodeId;
  }
  
  public String getTagId()
  {
    return this.tagId;
  }
  
  public void setTagId(String tagId)
  {
    this.tagId = tagId;
  }
  
  public String getSearchuuid()
  {
    return this.searchuuid;
  }
  
  public void setSearchuuid(String searchuuid)
  {
    this.searchuuid = searchuuid;
  }
  
  public String getKeyword()
  {
    return this.keyword;
  }
  
  public void setKeyword(String keyword)
  {
    this.keyword = keyword;
  }
  
  public long getApplicantId()
  {
    return this.applicantId;
  }
  
  public void setApplicantId(long applicantId)
  {
    this.applicantId = applicantId;
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
  
  public String getEmployeecode()
  {
    return this.employeecode;
  }
  
  public void setEmployeecode(String employeecode)
  {
    this.employeecode = employeecode;
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
  
  public String getGender()
  {
    return this.gender;
  }
  
  public void setGender(String gender)
  {
    this.gender = gender;
  }
  
  public String getPassportno()
  {
    return this.passportno;
  }
  
  public void setPassportno(String passportno)
  {
    this.passportno = passportno;
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
  
  public String getCity()
  {
    return this.city;
  }
  
  public void setCity(String city)
  {
    this.city = city;
  }
  
  public String getFelony_conviction()
  {
    return this.felony_conviction;
  }
  
  public void setFelony_conviction(String felonyConviction)
  {
    this.felony_conviction = felonyConviction;
  }
  
  public String getOrg_criteria()
  {
    return this.org_criteria;
  }
  
  public void setOrg_criteria(String orgCriteria)
  {
    this.org_criteria = orgCriteria;
  }
  
  public int getSourceTypeId()
  {
    return this.sourceTypeId;
  }
  
  public void setSourceTypeId(int sourceTypeId)
  {
    this.sourceTypeId = sourceTypeId;
  }
  
  public long getSourceId()
  {
    return this.sourceId;
  }
  
  public void setSourceId(long sourceId)
  {
    this.sourceId = sourceId;
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
  
  public String getDateofbirth()
  {
    return this.dateofbirth;
  }
  
  public void setDateofbirth(String dateofbirth)
  {
    this.dateofbirth = dateofbirth;
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
  
  public String getCurrentdesignation()
  {
    return this.currentdesignation;
  }
  
  public void setCurrentdesignation(String currentdesignation)
  {
    this.currentdesignation = currentdesignation;
  }
  
  public String getResumeHeader()
  {
    return this.resumeHeader;
  }
  
  public void setResumeHeader(String resumeHeader)
  {
    this.resumeHeader = resumeHeader;
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
  
  public String getLanguages_spoken_criteria()
  {
    return this.languages_spoken_criteria;
  }
  
  public void setLanguages_spoken_criteria(String languagesSpokenCriteria)
  {
    this.languages_spoken_criteria = languagesSpokenCriteria;
  }
  
  public String getLanguages_spoken()
  {
    return this.languages_spoken;
  }
  
  public void setLanguages_spoken(String languagesSpoken)
  {
    this.languages_spoken = languagesSpoken;
  }
  
  public double getCurrectctc()
  {
    return this.currectctc;
  }
  
  public void setCurrectctc(double currectctc)
  {
    this.currectctc = currectctc;
  }
  
  public double getExpectedctc()
  {
    return this.expectedctc;
  }
  
  public void setExpectedctc(double expectedctc)
  {
    this.expectedctc = expectedctc;
  }
  
  public double getNoticeperiod()
  {
    return this.noticeperiod;
  }
  
  public void setNoticeperiod(double noticeperiod)
  {
    this.noticeperiod = noticeperiod;
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
  
  public String getPreferedlocation()
  {
    return this.preferedlocation;
  }
  
  public void setPreferedlocation(String preferedlocation)
  {
    this.preferedlocation = preferedlocation;
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
  
  public String getPrimarySkill()
  {
    return this.primarySkill;
  }
  
  public void setPrimarySkill(String primarySkill)
  {
    this.primarySkill = primarySkill;
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
  
  public String getCollege_name()
  {
    return this.college_name;
  }
  
  public void setCollege_name(String collegeName)
  {
    this.college_name = collegeName;
  }
  
  public double getCollege_GPA()
  {
    return this.college_GPA;
  }
  
  public void setCollege_GPA(double collegeGPA)
  {
    this.college_GPA = collegeGPA;
  }
  
  public double getCollege_GPA1()
  {
    return this.college_GPA1;
  }
  
  public void setCollege_GPA1(double collegeGPA1)
  {
    this.college_GPA1 = collegeGPA1;
  }
  
  public long getZipCode()
  {
    return this.zipCode;
  }
  
  public void setZipCode(long zipCode)
  {
    this.zipCode = zipCode;
  }
  
  public double getMilesWithin()
  {
    return this.milesWithin;
  }
  
  public void setMilesWithin(double milesWithin)
  {
    this.milesWithin = milesWithin;
  }
  
  public List<SearchApplicantQuestions> getQuestionCriList()
  {
    return this.questionCriList;
  }
  
  public void setQuestionCriList(List<SearchApplicantQuestions> questionCriList)
  {
    this.questionCriList = questionCriList;
  }
  
  public List<SearchApplicantCustomFields> getCustomFieldCriList()
  {
    return this.customFieldCriList;
  }
  
  public void setCustomFieldCriList(List<SearchApplicantCustomFields> customFieldCriList)
  {
    this.customFieldCriList = customFieldCriList;
  }
  
  public int getNoofrows()
  {
    return this.noofrows;
  }
  
  public void setNoofrows(int noofrows)
  {
    this.noofrows = noofrows;
  }
  
  public double getCurrectctc1()
  {
    return this.currectctc1;
  }
  
  public void setCurrectctc1(double currectctc1)
  {
    this.currectctc1 = currectctc1;
  }
  
  public double getExpectedctc1()
  {
    return this.expectedctc1;
  }
  
  public void setExpectedctc1(double expectedctc1)
  {
    this.expectedctc1 = expectedctc1;
  }
  
  public String getMockquestionset_criteria()
  {
    return this.mockquestionset_criteria;
  }
  
  public void setMockquestionset_criteria(String mockquestionsetCriteria)
  {
    this.mockquestionset_criteria = mockquestionsetCriteria;
  }
  
  public double getMockquestionsetValue1()
  {
    return this.mockquestionsetValue1;
  }
  
  public void setMockquestionsetValue1(double mockquestionsetValue1)
  {
    this.mockquestionsetValue1 = mockquestionsetValue1;
  }
  
  public double getMockquestionsetValue2()
  {
    return this.mockquestionsetValue2;
  }
  
  public void setMockquestionsetValue2(double mockquestionsetValue2)
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
}
