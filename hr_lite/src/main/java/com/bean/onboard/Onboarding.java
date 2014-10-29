package com.bean.onboard;

import java.util.Date;

public class Onboarding
{
  public long onboardingid;
  public long onboardingTemplateId;
  public long applicantId;
  public String uuid;
  public Date onBoardingDate;
  public String status;
  public String createdBy;
  public Date createdDate;
  public Date updatedDate;
  public String updatedBy;
  
  public long getOnboardingid()
  {
    return this.onboardingid;
  }
  
  public void setOnboardingid(long onboardingid)
  {
    this.onboardingid = onboardingid;
  }
  
  public long getOnboardingTemplateId()
  {
    return this.onboardingTemplateId;
  }
  
  public void setOnboardingTemplateId(long onboardingTemplateId)
  {
    this.onboardingTemplateId = onboardingTemplateId;
  }
  
  public long getApplicantId()
  {
    return this.applicantId;
  }
  
  public void setApplicantId(long applicantId)
  {
    this.applicantId = applicantId;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
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
  
  public String getUuid()
  {
    return this.uuid;
  }
  
  public void setUuid(String uuid)
  {
    this.uuid = uuid;
  }
  
  public Date getOnBoardingDate()
  {
    return this.onBoardingDate;
  }
  
  public void setOnBoardingDate(Date onBoardingDate)
  {
    this.onBoardingDate = onBoardingDate;
  }
}
