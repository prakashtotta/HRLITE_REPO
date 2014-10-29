package com.bean.onboard;

import java.util.Date;
import java.util.Set;

public class OnBoardingTemplate
{
  public long templateid;
  public String templateName;
  public String templateDesc;
  public String status;
  public String createdBy;
  public Date createdDate;
  public Date updatedDate;
  public String updatedBy;
  private Set taskdefinitions;
  public long super_user_key;
  
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
  
  public Set getTaskdefinitions()
  {
    return this.taskdefinitions;
  }
  
  public void setTaskdefinitions(Set taskdefinitions)
  {
    this.taskdefinitions = taskdefinitions;
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
  
  public long getSuper_user_key()
  {
    return this.super_user_key;
  }
  
  public void setSuper_user_key(long superUserKey)
  {
    this.super_user_key = superUserKey;
  }
}
