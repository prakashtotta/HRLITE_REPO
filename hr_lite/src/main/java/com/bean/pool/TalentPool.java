package com.bean.pool;

import com.bean.Organization;
import java.util.Date;

public class TalentPool
{
  public long talentPoolId;
  public String talentPoolName;
  public String talentPoolDesc;
  public String talentPoolemail;
  public String status;
  public long assignedtoid;
  public String assigntouserName;
  public String isGroup;
  public String smtpserver;
  public String smtpuser;
  public String smptoport;
  public String smtppassword;
  public String createdBy;
  public Date createdDate;
  public Date updatedDate;
  public String updatedBy;
  public Organization organization;
  public String orgName;
  public long super_user_key;
  
  public long getTalentPoolId()
  {
    return this.talentPoolId;
  }
  
  public void setTalentPoolId(long talentPoolId)
  {
    this.talentPoolId = talentPoolId;
  }
  
  public String getTalentPoolName()
  {
    return this.talentPoolName;
  }
  
  public void setTalentPoolName(String talentPoolName)
  {
    this.talentPoolName = talentPoolName;
  }
  
  public String getTalentPoolDesc()
  {
    return this.talentPoolDesc;
  }
  
  public void setTalentPoolDesc(String talentPoolDesc)
  {
    this.talentPoolDesc = talentPoolDesc;
  }
  
  public String getTalentPoolemail()
  {
    return this.talentPoolemail;
  }
  
  public void setTalentPoolemail(String talentPoolemail)
  {
    this.talentPoolemail = talentPoolemail;
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
  
  public long getAssignedtoid()
  {
    return this.assignedtoid;
  }
  
  public void setAssignedtoid(long assignedtoid)
  {
    this.assignedtoid = assignedtoid;
  }
  
  public String getIsGroup()
  {
    return this.isGroup;
  }
  
  public void setIsGroup(String isGroup)
  {
    this.isGroup = isGroup;
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
  
  public Organization getOrganization()
  {
    return this.organization;
  }
  
  public void setOrganization(Organization organization)
  {
    this.organization = organization;
    this.orgName = organization.getOrgName();
  }
  
  public String getOrgName()
  {
    return this.orgName;
  }
  
  public void setOrgName(String orgName)
  {
    this.orgName = orgName;
  }
  
  public String getAssigntouserName()
  {
    return this.assigntouserName;
  }
  
  public void setAssigntouserName(String assigntouserName)
  {
    this.assigntouserName = assigntouserName;
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
