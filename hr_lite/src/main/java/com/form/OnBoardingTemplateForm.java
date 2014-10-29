package com.form;

import com.bean.onboard.OnBoardingTaskDefinitions;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.apache.struts.action.ActionForm;

public class OnBoardingTemplateForm
  extends ActionForm
{
  public long templateid;
  public String templateName;
  public String templateDesc;
  public String status;
  public String createdBy;
  public Date createdDate;
  public Date updatedDate;
  public String updatedBy;
  public String readPreview;
  private Set taskdefinitions;
  public List onboardtaskList;
  public String start;
  public String range;
  public String results;
  public String criteria;
  public String criteriaowner;
  public OnBoardingTaskDefinitions onboardtaskdef;
  public long taskdefid;
  public String taskName;
  public String primaryOwnerName;
  public long primaryOwnerId;
  public String taskdefinationStringvalue;
  public String countOnboardingTask;
  public String counttaskdefid;
  public String counttaskName;
  public int listsizeval;
  public List OnboardtaskidListVal;
  
  public long getTemplateid()
  {
    return this.templateid;
  }
  
  public void setTemplateid(long templateid)
  {
    this.templateid = templateid;
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
  
  public Set getTaskdefinitions()
  {
    return this.taskdefinitions;
  }
  
  public void setTaskdefinitions(Set taskdefinitions)
  {
    this.taskdefinitions = taskdefinitions;
  }
  
  public String getReadPreview()
  {
    return this.readPreview;
  }
  
  public void setReadPreview(String readPreview)
  {
    this.readPreview = readPreview;
  }
  
  public List getOnboardtaskList()
  {
    return this.onboardtaskList;
  }
  
  public void setOnboardtaskList(List onboardtaskList)
  {
    this.onboardtaskList = onboardtaskList;
  }
  
  public String getStart()
  {
    return this.start;
  }
  
  public void setStart(String start)
  {
    this.start = start;
  }
  
  public String getRange()
  {
    return this.range;
  }
  
  public void setRange(String range)
  {
    this.range = range;
  }
  
  public String getResults()
  {
    return this.results;
  }
  
  public void setResults(String results)
  {
    this.results = results;
  }
  
  public String getCriteria()
  {
    return this.criteria;
  }
  
  public void setCriteria(String criteria)
  {
    this.criteria = criteria;
  }
  
  public long getTaskdefid()
  {
    return this.taskdefid;
  }
  
  public void setTaskdefid(long taskdefid)
  {
    this.taskdefid = taskdefid;
  }
  
  public String getTaskName()
  {
    return this.taskName;
  }
  
  public void setTaskName(String taskName)
  {
    this.taskName = taskName;
  }
  
  public OnBoardingTaskDefinitions getOnboardtaskdef()
  {
    return this.onboardtaskdef;
  }
  
  public void setOnboardtaskdef(OnBoardingTaskDefinitions onboardtaskdef)
  {
    this.onboardtaskdef = onboardtaskdef;
  }
  
  public String getCriteriaowner()
  {
    return this.criteriaowner;
  }
  
  public void setCriteriaowner(String criteriaowner)
  {
    this.criteriaowner = criteriaowner;
  }
  
  public String getPrimaryOwnerName()
  {
    return this.primaryOwnerName;
  }
  
  public void setPrimaryOwnerName(String primaryOwnerName)
  {
    this.primaryOwnerName = primaryOwnerName;
  }
  
  public long getPrimaryOwnerId()
  {
    return this.primaryOwnerId;
  }
  
  public void setPrimaryOwnerId(long primaryOwnerId)
  {
    this.primaryOwnerId = primaryOwnerId;
  }
  
  public String getTaskdefinationStringvalue()
  {
    return this.taskdefinationStringvalue;
  }
  
  public void setTaskdefinationStringvalue(String taskdefinationStringvalue)
  {
    this.taskdefinationStringvalue = taskdefinationStringvalue;
  }
  
  public String getCountOnboardingTask()
  {
    return this.countOnboardingTask;
  }
  
  public void setCountOnboardingTask(String countOnboardingTask)
  {
    this.countOnboardingTask = countOnboardingTask;
  }
  
  public String getCounttaskdefid()
  {
    return this.counttaskdefid;
  }
  
  public void setCounttaskdefid(String counttaskdefid)
  {
    this.counttaskdefid = counttaskdefid;
  }
  
  public String getCounttaskName()
  {
    return this.counttaskName;
  }
  
  public void setCounttaskName(String counttaskName)
  {
    this.counttaskName = counttaskName;
  }
  
  public int getListsizeval()
  {
    return this.listsizeval;
  }
  
  public void setListsizeval(int listsizeval)
  {
    this.listsizeval = listsizeval;
  }
  
  public List getOnboardtaskidListVal()
  {
    return this.OnboardtaskidListVal;
  }
  
  public void setOnboardtaskidListVal(List onboardtaskidListVal)
  {
    this.OnboardtaskidListVal = onboardtaskidListVal;
  }
}
