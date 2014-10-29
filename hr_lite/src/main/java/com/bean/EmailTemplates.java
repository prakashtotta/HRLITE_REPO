package com.bean;

import java.util.Date;

public class EmailTemplates
{
  public long emailtemplateId;
  public String emailtemplatename;
  public String emailtemplateVmname;
  public String emailtemplateData;
  public String createdBy;
  public Date createdDate;
  public Date updatedDate;
  public String updatedBy;
  public String emailSubject;
  public String defaultcomponent;
  public long super_user_key;
  
  public long getEmailtemplateId()
  {
    return this.emailtemplateId;
  }
  
  public void setEmailtemplateId(long emailtemplateId)
  {
    this.emailtemplateId = emailtemplateId;
  }
  
  public String getEmailtemplatename()
  {
    return this.emailtemplatename;
  }
  
  public void setEmailtemplatename(String emailtemplatename)
  {
    this.emailtemplatename = emailtemplatename;
  }
  
  public String getEmailtemplateVmname()
  {
    return this.emailtemplateVmname;
  }
  
  public void setEmailtemplateVmname(String emailtemplateVmname)
  {
    this.emailtemplateVmname = emailtemplateVmname;
  }
  
  public String getEmailtemplateData()
  {
    return this.emailtemplateData;
  }
  
  public void setEmailtemplateData(String emailtemplateData)
  {
    this.emailtemplateData = emailtemplateData;
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
  
  public String getEmailSubject()
  {
    return this.emailSubject;
  }
  
  public void setEmailSubject(String emailSubject)
  {
    this.emailSubject = emailSubject;
  }
  
  public String getDefaultcomponent()
  {
    return this.defaultcomponent;
  }
  
  public void setDefaultcomponent(String defaultcomponent)
  {
    this.defaultcomponent = defaultcomponent;
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
