package com.bean.pool;

import java.util.Date;

public class EmailCampaign
{
  public long emailCampaignId;
  public String emailCampaignName;
  public String emailCampaignDesc;
  public String emailCampaignemail;
  public String emailCampaignFormat;
  public String status;
  public String smtpserver;
  public String smtpuser;
  public String smptoport;
  public String smtppassword;
  public String createdBy;
  public Date createdDate;
  public Date updatedDate;
  public String updatedBy;
  public long super_user_key;
  
  public long getEmailCampaignId()
  {
    return this.emailCampaignId;
  }
  
  public void setEmailCampaignId(long emailCampaignId)
  {
    this.emailCampaignId = emailCampaignId;
  }
  
  public String getEmailCampaignName()
  {
    return this.emailCampaignName;
  }
  
  public void setEmailCampaignName(String emailCampaignName)
  {
    this.emailCampaignName = emailCampaignName;
  }
  
  public String getEmailCampaignDesc()
  {
    return this.emailCampaignDesc;
  }
  
  public void setEmailCampaignDesc(String emailCampaignDesc)
  {
    this.emailCampaignDesc = emailCampaignDesc;
  }
  
  public String getEmailCampaignemail()
  {
    return this.emailCampaignemail;
  }
  
  public void setEmailCampaignemail(String emailCampaignemail)
  {
    this.emailCampaignemail = emailCampaignemail;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public String getSmtpserver()
  {
    return this.smtpserver;
  }
  
  public void setSmtpserver(String smtpserver)
  {
    this.smtpserver = smtpserver;
  }
  
  public String getSmtpuser()
  {
    return this.smtpuser;
  }
  
  public void setSmtpuser(String smtpuser)
  {
    this.smtpuser = smtpuser;
  }
  
  public String getSmptoport()
  {
    return this.smptoport;
  }
  
  public void setSmptoport(String smptoport)
  {
    this.smptoport = smptoport;
  }
  
  public String getSmtppassword()
  {
    return this.smtppassword;
  }
  
  public void setSmtppassword(String smtppassword)
  {
    this.smtppassword = smtppassword;
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
  
  public String getEmailCampaignFormat()
  {
    return this.emailCampaignFormat;
  }
  
  public void setEmailCampaignFormat(String emailCampaignFormat)
  {
    this.emailCampaignFormat = emailCampaignFormat;
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
