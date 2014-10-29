package com.form;

import com.bean.ApplicantReferencee;
import com.bean.QuestionGroups;
import java.io.PrintStream;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;

public class ReferenceForm
  extends ActionForm
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
  public String isVerificationDone;
  public String referenceeFeedback;
  private String createdBy;
  private Date createdDate;
  private Date updatedDate;
  private String updatedBy;
  private String note;
  private String assignedToName;
  public String questiongroupName;
  public String status;
  public QuestionGroups qnsgroup;
  public List questionanswersList;
  
  public ApplicantReferencee toValue(ApplicantReferencee applicantref, HttpServletRequest request)
    throws Exception
  {
    applicantref.setQuestiongroupid(this.questiongroupid);
    applicantref.setReferenceeName(this.referenceeName);
    applicantref.setReferenceeAddress(this.referenceeAddress);
    applicantref.setReferenceeDesignation(this.referenceeDesignation);
    applicantref.setReferenceeOrganization(this.referenceeOrganization);
    applicantref.setReferenceeEmail(this.referenceeEmail);
    applicantref.setReferenceePhone(this.referenceePhone);
    applicantref.setReferenceeRelation(this.referenceeRelation);
    applicantref.setNote(this.note);
    applicantref.setQuestiongroupName(this.questiongroupName);
    
    return applicantref;
  }
  
  public void fromValue(ApplicantReferencee applicantref, HttpServletRequest request)
    throws Exception
  {
    System.out.println("................................................");
    System.out.println("..." + applicantref.getApplicantReferenceId());
    this.applicantReferenceId = applicantref.getApplicantReferenceId();
    this.applicantId = applicantref.getApplicantId();
    this.assignedTo = applicantref.getAssignedTo();
    this.questiongroupid = applicantref.getQuestiongroupid();
    this.referenceeName = applicantref.getReferenceeName();
    this.referenceeAddress = applicantref.getReferenceeAddress();
    this.referenceeDesignation = applicantref.getReferenceeDesignation();
    this.referenceeEmail = applicantref.getReferenceeEmail();
    this.referenceePhone = applicantref.getReferenceePhone();
    this.referenceeRelation = applicantref.getReferenceeRelation();
    this.referenceeOrganization = applicantref.getReferenceeOrganization();
    this.referenceeFeedback = applicantref.getReferenceeFeedback();
    this.note = applicantref.getNote();
    this.createdBy = applicantref.getCreatedBy();
    this.updatedBy = applicantref.getUpdatedBy();
    this.createdDate = applicantref.getCreatedDate();
    this.updatedDate = applicantref.getUpdatedDate();
    this.isVerificationDone = applicantref.getIsVerificationDone();
    this.assignedToName = applicantref.getAssignedToName();
    this.questiongroupName = applicantref.getQuestiongroupName();
    this.status = applicantref.getStatus();
  }
  
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
  
  public String getReferenceeDesignation()
  {
    return this.referenceeDesignation;
  }
  
  public void setReferenceeDesignation(String referenceeDesignation)
  {
    this.referenceeDesignation = referenceeDesignation;
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
  
  public QuestionGroups getQnsgroup()
  {
    return this.qnsgroup;
  }
  
  public void setQnsgroup(QuestionGroups qnsgroup)
  {
    this.qnsgroup = qnsgroup;
  }
  
  public List getQuestionanswersList()
  {
    return this.questionanswersList;
  }
  
  public void setQuestionanswersList(List questionanswersList)
  {
    this.questionanswersList = questionanswersList;
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
