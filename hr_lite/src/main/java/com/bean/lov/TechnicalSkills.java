package com.bean.lov;

public class TechnicalSkills
{
  public long technialSkillId;
  public String technialSkillName;
  public String technialSkillDesc;
  public String status;
  public long super_user_key;
  
  public long getTechnialSkillId()
  {
    return this.technialSkillId;
  }
  
  public void setTechnialSkillId(long technialSkillId)
  {
    this.technialSkillId = technialSkillId;
  }
  
  public String getTechnialSkillName()
  {
    return this.technialSkillName;
  }
  
  public void setTechnialSkillName(String technialSkillName)
  {
    this.technialSkillName = technialSkillName;
  }
  
  public String getTechnialSkillDesc()
  {
    return this.technialSkillDesc;
  }
  
  public void setTechnialSkillDesc(String technialSkillDesc)
  {
    this.technialSkillDesc = technialSkillDesc;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
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
