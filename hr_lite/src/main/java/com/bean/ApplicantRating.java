package com.bean;

public class ApplicantRating
{
  public long appratingId;
  public long applicantId;
  public long applicantEventId;
  public String name;
  public String interviewState;
  public int minimumRatingRequired;
  public String isMandatory;
  public float yourrating;
  public String status;
  public String type;
  
  public long getAppratingId()
  {
    return this.appratingId;
  }
  
  public void setAppratingId(long appratingId)
  {
    this.appratingId = appratingId;
  }
  
  public long getApplicantId()
  {
    return this.applicantId;
  }
  
  public void setApplicantId(long applicantId)
  {
    this.applicantId = applicantId;
  }
  
  public long getApplicantEventId()
  {
    return this.applicantEventId;
  }
  
  public void setApplicantEventId(long applicantEventId)
  {
    this.applicantEventId = applicantEventId;
  }
  
  public String getInterviewState()
  {
    return this.interviewState;
  }
  
  public void setInterviewState(String interviewState)
  {
    this.interviewState = interviewState;
  }
  
  public String getIsMandatory()
  {
    return this.isMandatory;
  }
  
  public void setIsMandatory(String isMandatory)
  {
    this.isMandatory = isMandatory;
  }
  
  public float getYourrating()
  {
    return this.yourrating;
  }
  
  public void setYourrating(float yourrating)
  {
    this.yourrating = yourrating;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public int getMinimumRatingRequired()
  {
    return this.minimumRatingRequired;
  }
  
  public void setMinimumRatingRequired(int minimumRatingRequired)
  {
    this.minimumRatingRequired = minimumRatingRequired;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public String getType()
  {
    return this.type;
  }
  
  public void setType(String type)
  {
    this.type = type;
  }
}
