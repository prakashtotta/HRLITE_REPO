package com.form;

import com.bean.lov.Education;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;

public class EducationForm
  extends ActionForm
{
  public long educationId;
  public String educationName;
  public String educationDesc;
  public String status;
  
  public long getEducationId()
  {
    return this.educationId;
  }
  
  public void setEducationId(long educationId)
  {
    this.educationId = educationId;
  }
  
  public String getEducationName()
  {
    return this.educationName;
  }
  
  public void setEducationName(String educationName)
  {
    this.educationName = educationName;
  }
  
  public String getEducationDesc()
  {
    return this.educationDesc;
  }
  
  public void setEducationDesc(String educationDesc)
  {
    this.educationDesc = educationDesc;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public Education toValue(Education education, HttpServletRequest request)
    throws Exception
  {
    education.setEducationName(this.educationName);
    education.setEducationDesc(this.educationDesc);
    
    return education;
  }
  
  public void fromValue(Education education, HttpServletRequest request)
    throws Exception
  {
    this.educationId = education.getEducationId();
    this.educationName = education.getEducationName();
    this.educationDesc = education.getEducationDesc();
  }
}
