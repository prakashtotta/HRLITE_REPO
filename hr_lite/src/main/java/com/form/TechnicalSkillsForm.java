package com.form;

import com.bean.lov.TechnicalSkills;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;

public class TechnicalSkillsForm
  extends ActionForm
{
  public long technialSkillId;
  public String technialSkillName;
  public String technialSkillDesc;
  public String status;
  
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
  
  public TechnicalSkills toValue(TechnicalSkills technicalSkills, HttpServletRequest request)
    throws Exception
  {
    technicalSkills.setTechnialSkillName(this.technialSkillName);
    technicalSkills.setTechnialSkillDesc(getTechnialSkillDesc());
    
    return technicalSkills;
  }
  
  public void fromValue(TechnicalSkills technicalSkills, HttpServletRequest request)
    throws Exception
  {
    this.technialSkillId = technicalSkills.getTechnialSkillId();
    this.technialSkillName = technicalSkills.getTechnialSkillName();
    this.technialSkillDesc = technicalSkills.getTechnialSkillDesc();
  }
}
