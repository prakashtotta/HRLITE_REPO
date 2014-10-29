package com.form;

import com.bean.JobApplicant;
import com.bean.JobRequisition;
import com.bean.onboard.OnBoardingTask;
import com.bean.onboard.OnBoardingTemplate;
import com.bean.onboard.Onboarding;
import java.util.Date;
import java.util.List;
import org.apache.struts.action.ActionForm;

public class OnBoardingForm
  extends ActionForm
{
  public long onboardingid;
  public long onboardingTemplateId;
  public long applicantId;
  public String status;
  public String createdBy;
  public Date createdDate;
  public Date updatedDate;
  public String updatedBy;
  private OnBoardingTemplate template;
  private List applicantList;
  private List onboardingTemplateList;
  private List onboardingTaskList;
  private Onboarding onboarding;
  private OnBoardingTask onboardingTask;
  private JobApplicant jobapplicant;
  private JobRequisition requisition;
  
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
  
  public OnBoardingTemplate getTemplate()
  {
    return this.template;
  }
  
  public void setTemplate(OnBoardingTemplate template)
  {
    this.template = template;
  }
  
  public List getApplicantList()
  {
    return this.applicantList;
  }
  
  public void setApplicantList(List applicantList)
  {
    this.applicantList = applicantList;
  }
  
  public List getOnboardingTemplateList()
  {
    return this.onboardingTemplateList;
  }
  
  public void setOnboardingTemplateList(List onboardingTemplateList)
  {
    this.onboardingTemplateList = onboardingTemplateList;
  }
  
  public List getOnboardingTaskList()
  {
    return this.onboardingTaskList;
  }
  
  public void setOnboardingTaskList(List onboardingTaskList)
  {
    this.onboardingTaskList = onboardingTaskList;
  }
  
  public Onboarding getOnboarding()
  {
    return this.onboarding;
  }
  
  public void setOnboarding(Onboarding onboarding)
  {
    this.onboarding = onboarding;
  }
  
  public OnBoardingTask getOnboardingTask()
  {
    return this.onboardingTask;
  }
  
  public void setOnboardingTask(OnBoardingTask onboardingTask)
  {
    this.onboardingTask = onboardingTask;
  }
  
  public JobApplicant getJobapplicant()
  {
    return this.jobapplicant;
  }
  
  public void setJobapplicant(JobApplicant jobapplicant)
  {
    this.jobapplicant = jobapplicant;
  }
  
  public JobRequisition getRequisition()
  {
    return this.requisition;
  }
  
  public void setRequisition(JobRequisition requisition)
  {
    this.requisition = requisition;
  }
}
