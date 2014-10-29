package com.bean;

import java.util.Date;

public class ApplicantReferencee
{
  public long applicantReferenceId;
  public long applicantId;
  public long assignedTo;
  public long questiongroupid;
  public String referenceeName;
  public String referenceeOrganization;
  public String referenceeAddress;
  public String referenceeDesignation;
  public String referenceePhone;
  public String referenceeEmail;
  public String referenceeRelation;
  public String isVerificationDone = "N";
  public String referenceeFeedback;
  private String createdBy;
  private Date createdDate;
  private Date updatedDate;
  private String updatedBy;
  private String note;
  private String assignedToName;
  public String questiongroupName;
  public String status;
  
  public long getApplicantReferenceId()
  {
    return this.applicantReferenceId;
  }
  
  public void setApplicantReferenceId(long applicantReferenceId)
  {
    this.applicantReferenceId = applicantReferenceId;
  }
  
  public long getApplicantId()
  {
    return this.applicantId;
  }
  
  public void setApplicantId(long applicantId)
  {
    this.applicantId = applicantId;
  }
  
  public long getAssignedTo()
  {
    return this.assignedTo;
  }
  
  public void setAssignedTo(long assignedTo)
  {
    this.assignedTo = assignedTo;
  }
  
  public long getQuestiongroupid()
  {
    return this.questiongroupid;
  }
  
  public void setQuestiongroupid(long questiongroupid)
  {
    this.questiongroupid = questiongroupid;
  }
  
  public String getReferenceeName()
  {
    return this.referenceeName;
  }
  
  public void setReferenceeName(String referenceeName)
  {
    this.referenceeName = referenceeName;
  }
  
  public String getReferenceeOrganization()
  {
    return this.referenceeOrganization;
  }
  
  public void setReferenceeOrganization(String referenceeOrganization)
  {
    this.referenceeOrganization = referenceeOrganization;
  }
  
  public String getReferenceeAddress()
  {
    return this.referenceeAddress;
  }
  
  public void setReferenceeAddress(String referenceeAddress)
  {
    this.referenceeAddress = referenceeAddress;
  }
  
  public String getReferenceePhone()
  {
    return this.referenceePhone;
  }
  
  public void setReferenceePhone(String referenceePhone)
  {
    this.referenceePhone = referenceePhone;
  }
  
  public String getReferenceeEmail()
  {
    return this.referenceeEmail;
  }
  
  public void setReferenceeEmail(String referenceeEmail)
  {
    this.referenceeEmail = referenceeEmail;
  }
  
  public String getReferenceeRelation()
  {
    return this.referenceeRelation;
  }
  
  public void setReferenceeRelation(String referenceeRelation)
  {
    this.referenceeRelation = referenceeRelation;
  }
  
  public String getIsVerificationDone()
  {
    return this.isVerificationDone;
  }
  
  public void setIsVerificationDone(String isVerificationDone)
  {
    this.isVerificationDone = isVerificationDone;
  }
  
  public String getReferenceeFeedback()
  {
    return this.referenceeFeedback;
  }
  
  public void setReferenceeFeedback(String referenceeFeedback)
  {
    this.referenceeFeedback = referenceeFeedback;
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
  
  public String getReferenceeDesignation()
  {
    return this.referenceeDesignation;
  }
  
  public void setReferenceeDesignation(String referenceeDesignation)
  {
    this.referenceeDesignation = referenceeDesignation;
  }
  
  public String getNote()
  {
    return this.note;
  }
  
  public void setNote(String note)
  {
    this.note = note;
  }
  
  public String getAssignedToName()
  {
    return this.assignedToName;
  }
  
  public void setAssignedToName(String assignedToName)
  {
    this.assignedToName = assignedToName;
  }
  
  public String getQuestiongroupName()
  {
    return this.questiongroupName;
  }
  
  public void setQuestiongroupName(String questiongroupName)
  {
    this.questiongroupName = questiongroupName;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
}
