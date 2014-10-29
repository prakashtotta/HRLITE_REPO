package com.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class JobApplicant
  implements Serializable
{
  public long applicantId;
  public long applicant_number;
  public String uuid;
  public String fullName;
  public String email;
  public String phone;
  public Country country;
  public State state;
  public String city;
  public String street1;
  public String street2;
  public String zip;
  public String heighestQualification;
  public String qualifications;
  public String status;
  public String interviewState;
  public Date appliedDate;
  public String primarySkill;
  public String primarySkillOther;
  public String resumename;
  public String resumeHeader;
  public String createdBy;
  public Date createdDate;
  public Date updatedDate;
  public String updatedBy;
  public long reqId;
  public String referrerName;
  public String referrerEmail;
  public String employeecode;
  public User owner;
  private UserGroup ownerGroup;
  public String isGroup = "N";
  public Date dateofbirth;
  public String gender;
  public String previousOrganization;
  public double noofyearsexp;
  public double relyearsofexp;
  public String jobTitle;
  public String reqName;
  public String countryName = "";
  public String stateName = "";
  public String ownername;
  public String ownernamegroup;
  public long ownerId;
  public long ownergroupId;
  boolean isemailrequiretosend = true;
  public String locale_code;
  public long talentpoolid;
  public String talentPoolName;
  public Date targerofferdate;
  public Date offerinitiationdate;
  public String offerinitiatedby;
  public String offerInitiationComment;
  public String currectctc;
  public String expectedctc;
  public String currectctccurrencycode;
  public String expectedctccurrencycode;
  public long offerownerId;
  public String offerownerName;
  public String isofferownerGroup;
  public String offerReleaseComment;
  public Date offerreleasedate;
  public String offerreleaseddby;
  public String isOfferEmailSent = "N";
  public String isRejectionEmailSent = "N";
  public Date offeremailsentdate;
  public Date rejectionemailsentdate;
  public Date targetjoiningdate;
  public Date joiningdate;
  public Date joineddate;
  public String joinedcomment;
  public String offeredctc;
  public String offeredctccurrencycode;
  public Date resigneddate;
  public String resignedcomment;
  public String resignedactionby;
  public Date resignedactionDate;
  public String isRegignationSchedularProcessed;
  public long emailtemplateid;
  public String emailtemplate;
  public String offeracceptedcomment;
  public Date offeraccepteddate;
  public String offerdeclinedcomment;
  public Date offerdeclineddate;
  public int offerdeclinedresonid;
  public String offerDeclinedResonName;
  public String offerCancelResonName;
  public String rejectionreasonname;
  public String changeofferedctc;
  public String changeofferedctccurrencycode;
  public Date chagetargetjoiningdate;
  public String changeofferComment;
  public String offercancelledcomment;
  public Date offercancelleddate;
  public int offercancelledresonid;
  public String offercancelledby;
  public String isoffercancelemailsent = "N";
  public Date offercancelemailsentdate;
  public long interview_organizer_id;
  public int rejectionreasonId;
  public int isreferenceadded;
  public int isindexSearchApplied;
  public String indexSearchContent;
  public String sourceTypeName;
  public String sourceName;
  public String alternateemail;
  public String alternatephone;
  public String preferedlocation;
  public String currentdesignation;
  public String note;
  public String noticeperiod;
  public String passportno;
  public String ssnno;
  public String taxidno;
  public String screenCode;
  public double scoreAve;
  public int filterError;
  public String juuid;
  public long super_user_key;
  public boolean isCaptchaValidationRequired;
  public String captchaEnteredValue;
  public String captchaRandomValue;
  public ApplicantOtherDetails otherdetails;
  public ResumeSourceType resumesourcetype;
  public long resumeSourcesId;
  public long vendorId;
  public String resumeSourceTypeStr = "";
  public int validationOnlyresumeSourceId = 0;
  public long validationOnlyCountryId;
  public long validationOnlyStateId;
  public String offerinitiatedbyName;
  private ApplicantAttachments applicantAttachment;
  public String initiateJoiningProcess = "Not started";
  public long tagId;
  public String tagName;
  public String targetjoiningdateStr;
  public String offerreleasedateStr;
  public String orgName;
  public String department;
  public String jobCode;
  public String projectcode;
  public String hiringManager;
  public String recruiter;
  public String recruitergroup;
  public long recruiterId;
  public String applicantUrl;
  public String interviewerComments;
  public String schedulerComment;
  public String applicantReviewerName;
  public String modeOfInterview;
  public String offerinitiationdateConverted;
  public String offertargetdateConverted;
  public String mypendingTaskUrl;
  public String offerApproverComment;
  public String offerApproverName;
  public String offerApprovedStatus;
  public String interviewDate = "";
  private String filePath;
  private List errorList;
  public String isInitiateOfferAttachmentCopied;
  public String offerEmailSentError;
  private int isViewed = 1;
  private long profilePhotoId;
  public String tagsName;
  public String percentage;
  
  public String getPercentage()
  {
    return this.percentage;
  }
  
  public void setPercentage(String percentage)
  {
    this.percentage = percentage;
  }
  
  public String getTagsName()
  {
    return this.tagsName;
  }
  
  public void setTagsName(String tagsName)
  {
    this.tagsName = tagsName;
  }
  
  public User getOwner()
  {
    return this.owner;
  }
  
  public void setOwner(User owner)
  {
    if (owner != null)
    {
      this.owner = owner;
      this.ownerId = owner.getUserId();
      this.ownername = (owner.getFirstName() + " " + owner.getLastName());
    }
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
  
  public Country getCountry()
  {
    return this.country;
  }
  
  public void setCountry(Country country)
  {
    this.country = country;
    if (country != null)
    {
      this.validationOnlyCountryId = country.getCountryId();
      this.countryName = country.getCountryName();
    }
  }
  
  public State getState()
  {
    return this.state;
  }
  
  public void setState(State state)
  {
    this.state = state;
    if (state != null)
    {
      this.validationOnlyStateId = state.getStateId();
      this.stateName = state.getStateName();
    }
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
  
  public long getReqId()
  {
    return this.reqId;
  }
  
  public void setReqId(long reqId)
  {
    this.reqId = reqId;
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
  
  public Date getDateofbirth()
  {
    return this.dateofbirth;
  }
  
  public void setDateofbirth(Date dateofbirth)
  {
    this.dateofbirth = dateofbirth;
  }
  
  public Date getTargerofferdate()
  {
    return this.targerofferdate;
  }
  
  public void setTargerofferdate(Date targerofferdate)
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
  
  public String getIsOfferEmailSent()
  {
    return this.isOfferEmailSent;
  }
  
  public void setIsOfferEmailSent(String isOfferEmailSent)
  {
    this.isOfferEmailSent = isOfferEmailSent;
  }
  
  public String getIsRejectionEmailSent()
  {
    return this.isRejectionEmailSent;
  }
  
  public void setIsRejectionEmailSent(String isRejectionEmailSent)
  {
    this.isRejectionEmailSent = isRejectionEmailSent;
  }
  
  public Date getOfferemailsentdate()
  {
    return this.offeremailsentdate;
  }
  
  public void setOfferemailsentdate(Date offeremailsentdate)
  {
    this.offeremailsentdate = offeremailsentdate;
  }
  
  public Date getRejectionemailsentdate()
  {
    return this.rejectionemailsentdate;
  }
  
  public void setRejectionemailsentdate(Date rejectionemailsentdate)
  {
    this.rejectionemailsentdate = rejectionemailsentdate;
  }
  
  public Date getTargetjoiningdate()
  {
    return this.targetjoiningdate;
  }
  
  public void setTargetjoiningdate(Date targetjoiningdate)
  {
    this.targetjoiningdate = targetjoiningdate;
  }
  
  public Date getJoiningdate()
  {
    return this.joiningdate;
  }
  
  public void setJoiningdate(Date joiningdate)
  {
    this.joiningdate = joiningdate;
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
  
  public Date getChagetargetjoiningdate()
  {
    return this.chagetargetjoiningdate;
  }
  
  public void setChagetargetjoiningdate(Date chagetargetjoiningdate)
  {
    this.chagetargetjoiningdate = chagetargetjoiningdate;
  }
  
  public String getChangeofferComment()
  {
    return this.changeofferComment;
  }
  
  public void setChangeofferComment(String changeofferComment)
  {
    this.changeofferComment = changeofferComment;
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
  
  public long getInterview_organizer_id()
  {
    return this.interview_organizer_id;
  }
  
  public void setInterview_organizer_id(long interviewOrganizerId)
  {
    this.interview_organizer_id = interviewOrganizerId;
  }
  
  public Date getJoineddate()
  {
    return this.joineddate;
  }
  
  public void setJoineddate(Date joineddate)
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
  
  public String getUuid()
  {
    return this.uuid;
  }
  
  public void setUuid(String uuid)
  {
    this.uuid = uuid;
  }
  
  public int getRejectionreasonId()
  {
    return this.rejectionreasonId;
  }
  
  public void setRejectionreasonId(int rejectionreasonId)
  {
    this.rejectionreasonId = rejectionreasonId;
  }
  
  public String getRejectionreasonname()
  {
    return this.rejectionreasonname;
  }
  
  public void setRejectionreasonname(String rejectionreasonname)
  {
    this.rejectionreasonname = rejectionreasonname;
  }
  
  public String getApplicantUrl()
  {
    return this.applicantUrl;
  }
  
  public void setApplicantUrl(String applicantUrl)
  {
    this.applicantUrl = applicantUrl;
  }
  
  public String getInterviewerComments()
  {
    return this.interviewerComments;
  }
  
  public void setInterviewerComments(String interviewerComments)
  {
    this.interviewerComments = interviewerComments;
  }
  
  public String getApplicantReviewerName()
  {
    return this.applicantReviewerName;
  }
  
  public void setApplicantReviewerName(String applicantReviewerName)
  {
    this.applicantReviewerName = applicantReviewerName;
  }
  
  public String getSchedulerComment()
  {
    return this.schedulerComment;
  }
  
  public void setSchedulerComment(String schedulerComment)
  {
    this.schedulerComment = schedulerComment;
  }
  
  public String getModeOfInterview()
  {
    return this.modeOfInterview;
  }
  
  public void setModeOfInterview(String modeOfInterview)
  {
    this.modeOfInterview = modeOfInterview;
  }
  
  public String getOfferinitiationdateConverted()
  {
    return this.offerinitiationdateConverted;
  }
  
  public void setOfferinitiationdateConverted(String offerinitiationdateConverted)
  {
    this.offerinitiationdateConverted = offerinitiationdateConverted;
  }
  
  public String getOffertargetdateConverted()
  {
    return this.offertargetdateConverted;
  }
  
  public void setOffertargetdateConverted(String offertargetdateConverted)
  {
    this.offertargetdateConverted = offertargetdateConverted;
  }
  
  public String getMypendingTaskUrl()
  {
    return this.mypendingTaskUrl;
  }
  
  public void setMypendingTaskUrl(String mypendingTaskUrl)
  {
    this.mypendingTaskUrl = mypendingTaskUrl;
  }
  
  public String getOfferApproverComment()
  {
    return this.offerApproverComment;
  }
  
  public void setOfferApproverComment(String offerApproverComment)
  {
    this.offerApproverComment = offerApproverComment;
  }
  
  public String getOfferApproverName()
  {
    return this.offerApproverName;
  }
  
  public void setOfferApproverName(String offerApproverName)
  {
    this.offerApproverName = offerApproverName;
  }
  
  public String getOfferApprovedStatus()
  {
    return this.offerApprovedStatus;
  }
  
  public void setOfferApprovedStatus(String offerApprovedStatus)
  {
    this.offerApprovedStatus = offerApprovedStatus;
  }
  
  public int getIsreferenceadded()
  {
    return this.isreferenceadded;
  }
  
  public void setIsreferenceadded(int isreferenceadded)
  {
    this.isreferenceadded = isreferenceadded;
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
  
  public ResumeSourceType getResumesourcetype()
  {
    return this.resumesourcetype;
  }
  
  public void setResumesourcetype(ResumeSourceType resumesourcetype)
  {
    this.resumesourcetype = resumesourcetype;
    if (resumesourcetype != null)
    {
      this.validationOnlyresumeSourceId = resumesourcetype.getResumeSourceTypeId();
      this.resumeSourceTypeStr = resumesourcetype.getResumeSourceTypeName();
    }
  }
  
  public String getResumeSourceTypeStr()
  {
    return this.resumeSourceTypeStr;
  }
  
  public void setResumeSourceTypeStr(String resumeSourceTypeStr)
  {
    this.resumeSourceTypeStr = resumeSourceTypeStr;
  }
  
  public long getResumeSourcesId()
  {
    return this.resumeSourcesId;
  }
  
  public void setResumeSourcesId(long resumeSourcesId)
  {
    this.resumeSourcesId = resumeSourcesId;
  }
  
  public long getVendorId()
  {
    return this.vendorId;
  }
  
  public void setVendorId(long vendorId)
  {
    this.vendorId = vendorId;
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
  
  public Date getResigneddate()
  {
    return this.resigneddate;
  }
  
  public void setResigneddate(Date resigneddate)
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
  
  public String getResignedactionby()
  {
    return this.resignedactionby;
  }
  
  public void setResignedactionby(String resignedactionby)
  {
    this.resignedactionby = resignedactionby;
  }
  
  public Date getResignedactionDate()
  {
    return this.resignedactionDate;
  }
  
  public void setResignedactionDate(Date resignedactionDate)
  {
    this.resignedactionDate = resignedactionDate;
  }
  
  public String getIsRegignationSchedularProcessed()
  {
    return this.isRegignationSchedularProcessed;
  }
  
  public void setIsRegignationSchedularProcessed(String isRegignationSchedularProcessed)
  {
    this.isRegignationSchedularProcessed = isRegignationSchedularProcessed;
  }
  
  public String getTargetjoiningdateStr()
  {
    return this.targetjoiningdateStr;
  }
  
  public void setTargetjoiningdateStr(String targetjoiningdateStr)
  {
    this.targetjoiningdateStr = targetjoiningdateStr;
  }
  
  public String getOfferreleasedateStr()
  {
    return this.offerreleasedateStr;
  }
  
  public void setOfferreleasedateStr(String offerreleasedateStr)
  {
    this.offerreleasedateStr = offerreleasedateStr;
  }
  
  public String getOrgName()
  {
    return this.orgName;
  }
  
  public void setOrgName(String orgName)
  {
    this.orgName = orgName;
  }
  
  public String getDepartment()
  {
    return this.department;
  }
  
  public void setDepartment(String department)
  {
    this.department = department;
  }
  
  public String getJobCode()
  {
    return this.jobCode;
  }
  
  public void setJobCode(String jobCode)
  {
    this.jobCode = jobCode;
  }
  
  public String getProjectcode()
  {
    return this.projectcode;
  }
  
  public void setProjectcode(String projectcode)
  {
    this.projectcode = projectcode;
  }
  
  public String getHiringManager()
  {
    return this.hiringManager;
  }
  
  public void setHiringManager(String hiringManager)
  {
    this.hiringManager = hiringManager;
  }
  
  public String getRecruiter()
  {
    return this.recruiter;
  }
  
  public void setRecruiter(String recruiter)
  {
    this.recruiter = recruiter;
  }
  
  public String getInitiateJoiningProcess()
  {
    return this.initiateJoiningProcess;
  }
  
  public void setInitiateJoiningProcess(String initiateJoiningProcess)
  {
    this.initiateJoiningProcess = initiateJoiningProcess;
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
  
  public ApplicantAttachments getApplicantAttachment()
  {
    return this.applicantAttachment;
  }
  
  public void setApplicantAttachment(ApplicantAttachments applicantAttachment)
  {
    this.applicantAttachment = applicantAttachment;
  }
  
  public String getIsInitiateOfferAttachmentCopied()
  {
    return this.isInitiateOfferAttachmentCopied;
  }
  
  public void setIsInitiateOfferAttachmentCopied(String isInitiateOfferAttachmentCopied)
  {
    this.isInitiateOfferAttachmentCopied = isInitiateOfferAttachmentCopied;
  }
  
  public String getOfferEmailSentError()
  {
    return this.offerEmailSentError;
  }
  
  public void setOfferEmailSentError(String offerEmailSentError)
  {
    this.offerEmailSentError = offerEmailSentError;
  }
  
  public String getInterviewDate()
  {
    return this.interviewDate;
  }
  
  public void setInterviewDate(String interviewDate)
  {
    this.interviewDate = interviewDate;
  }
  
  public UserGroup getOwnerGroup()
  {
    return this.ownerGroup;
  }
  
  public void setOwnerGroup(UserGroup ownerGroup)
  {
    this.ownerGroup = ownerGroup;
    if (ownerGroup != null)
    {
      this.ownergroupId = ownerGroup.getUsergrpId();
      this.ownernamegroup = ownerGroup.getUsergrpName();
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
  
  public long getProfilePhotoId()
  {
    return this.profilePhotoId;
  }
  
  public void setProfilePhotoId(long profilePhotoId)
  {
    this.profilePhotoId = profilePhotoId;
  }
  
  public String getIsofferownerGroup()
  {
    return this.isofferownerGroup;
  }
  
  public void setIsofferownerGroup(String isofferownerGroup)
  {
    this.isofferownerGroup = isofferownerGroup;
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
  
  public String getPrimarySkillOther()
  {
    return this.primarySkillOther;
  }
  
  public void setPrimarySkillOther(String primarySkillOther)
  {
    this.primarySkillOther = primarySkillOther;
  }
  
  public String getFilePath()
  {
    return this.filePath;
  }
  
  public void setFilePath(String filePath)
  {
    this.filePath = filePath;
  }
  
  public List getErrorList()
  {
    return this.errorList;
  }
  
  public void setErrorList(List errorList)
  {
    this.errorList = errorList;
  }
  
  public int getIsindexSearchApplied()
  {
    return this.isindexSearchApplied;
  }
  
  public void setIsindexSearchApplied(int isindexSearchApplied)
  {
    this.isindexSearchApplied = isindexSearchApplied;
  }
  
  public String getIndexSearchContent()
  {
    return this.indexSearchContent;
  }
  
  public void setIndexSearchContent(String indexSearchContent)
  {
    this.indexSearchContent = indexSearchContent;
  }
  
  public long getTalentpoolid()
  {
    return this.talentpoolid;
  }
  
  public void setTalentpoolid(long talentpoolid)
  {
    this.talentpoolid = talentpoolid;
  }
  
  public String getTalentPoolName()
  {
    return this.talentPoolName;
  }
  
  public void setTalentPoolName(String talentPoolName)
  {
    this.talentPoolName = talentPoolName;
  }
  
  public String getOfferinitiatedbyName()
  {
    return this.offerinitiatedbyName;
  }
  
  public void setOfferinitiatedbyName(String offerinitiatedbyName)
  {
    this.offerinitiatedbyName = offerinitiatedbyName;
  }
  
  public ApplicantOtherDetails getOtherdetails()
  {
    return this.otherdetails;
  }
  
  public void setOtherdetails(ApplicantOtherDetails otherdetails)
  {
    this.otherdetails = otherdetails;
  }
  
  public boolean isIsemailrequiretosend()
  {
    return this.isemailrequiretosend;
  }
  
  public void setIsemailrequiretosend(boolean isemailrequiretosend)
  {
    this.isemailrequiretosend = isemailrequiretosend;
  }
  
  public String getLocale_code()
  {
    return this.locale_code;
  }
  
  public void setLocale_code(String localeCode)
  {
    this.locale_code = localeCode;
  }
  
  public double getNoofyearsexp()
  {
    return this.noofyearsexp;
  }
  
  public void setNoofyearsexp(double noofyearsexp)
  {
    this.noofyearsexp = noofyearsexp;
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
  
  public String getJuuid()
  {
    return this.juuid;
  }
  
  public void setJuuid(String juuid)
  {
    this.juuid = juuid;
  }
  
  public boolean isCaptchaValidationRequired()
  {
    return this.isCaptchaValidationRequired;
  }
  
  public void setCaptchaValidationRequired(boolean isCaptchaValidationRequired)
  {
    this.isCaptchaValidationRequired = isCaptchaValidationRequired;
  }
  
  public String getCaptchaEnteredValue()
  {
    return this.captchaEnteredValue;
  }
  
  public void setCaptchaEnteredValue(String captchaEnteredValue)
  {
    this.captchaEnteredValue = captchaEnteredValue;
  }
  
  public String getCaptchaRandomValue()
  {
    return this.captchaRandomValue;
  }
  
  public void setCaptchaRandomValue(String captchaRandomValue)
  {
    this.captchaRandomValue = captchaRandomValue;
  }
  
  public long getSuper_user_key()
  {
    return this.super_user_key;
  }
  
  public void setSuper_user_key(long superUserKey)
  {
    this.super_user_key = superUserKey;
  }
  
  public long getApplicant_number()
  {
    return this.applicant_number;
  }
  
  public void setApplicant_number(long applicantNumber)
  {
    this.applicant_number = applicantNumber;
  }
  
  public String getSourceTypeName()
  {
    return this.sourceTypeName;
  }
  
  public void setSourceTypeName(String sourceTypeName)
  {
    this.sourceTypeName = sourceTypeName;
  }
  
  public String getSourceName()
  {
    return this.sourceName;
  }
  
  public void setSourceName(String sourceName)
  {
    this.sourceName = sourceName;
  }
  
  public double getRelyearsofexp()
  {
    return this.relyearsofexp;
  }
  
  public void setRelyearsofexp(double relyearsofexp)
  {
    this.relyearsofexp = relyearsofexp;
  }
  
  public String getReqName()
  {
    return this.reqName;
  }
  
  public void setReqName(String reqName)
  {
    this.reqName = reqName;
  }
  
  public int getIsViewed()
  {
    return this.isViewed;
  }
  
  public void setIsViewed(int isViewed)
  {
    this.isViewed = isViewed;
  }
  
  public String getOfferDeclinedResonName()
  {
    return this.offerDeclinedResonName;
  }
  
  public void setOfferDeclinedResonName(String offerDeclinedResonName)
  {
    this.offerDeclinedResonName = offerDeclinedResonName;
  }
  
  public String getOfferCancelResonName()
  {
    return this.offerCancelResonName;
  }
  
  public void setOfferCancelResonName(String offerCancelResonName)
  {
    this.offerCancelResonName = offerCancelResonName;
  }
}
