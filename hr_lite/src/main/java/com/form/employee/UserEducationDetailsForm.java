package com.form.employee;

import com.bean.User;
import com.bean.employee.UserEducationDetails;
import com.common.Common;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;

public class UserEducationDetailsForm
  extends ActionForm
{
  private long educationId;
  public long userId;
  public String educationName;
  public String specialization;
  public String instituteName;
  public String percentile;
  public String passingYear;
  public String startingYear;
  private String eduType;
  private List educationNamesList;
  
  public List getEducationNamesList()
  {
    return this.educationNamesList;
  }
  
  public void setEducationNamesList(List educationNamesList)
  {
    this.educationNamesList = educationNamesList;
  }
  
  public long getEducationId()
  {
    return this.educationId;
  }
  
  public void setEducationId(long educationId)
  {
    this.educationId = educationId;
  }
  
  public long getUserId()
  {
    return this.userId;
  }
  
  public void setUserId(long userId)
  {
    this.userId = userId;
  }
  
  public String getEducationName()
  {
    return this.educationName;
  }
  
  public void setEducationName(String educationName)
  {
    this.educationName = educationName;
  }
  
  public String getSpecialization()
  {
    return this.specialization;
  }
  
  public void setSpecialization(String specialization)
  {
    this.specialization = specialization;
  }
  
  public String getInstituteName()
  {
    return this.instituteName;
  }
  
  public void setInstituteName(String instituteName)
  {
    this.instituteName = instituteName;
  }
  
  public String getPercentile()
  {
    return this.percentile;
  }
  
  public void setPercentile(String percentile)
  {
    this.percentile = percentile;
  }
  
  public String getPassingYear()
  {
    return this.passingYear;
  }
  
  public void setPassingYear(String passingYear)
  {
    this.passingYear = passingYear;
  }
  
  public String getStartingYear()
  {
    return this.startingYear;
  }
  
  public void setStartingYear(String startingYear)
  {
    this.startingYear = startingYear;
  }
  
  public String getEduType()
  {
    return this.eduType;
  }
  
  public void setEduType(String eduType)
  {
    this.eduType = eduType;
  }
  
  public void fromValue(UserEducationDetails userEducationDetails, HttpServletRequest request)
  {
    User user1 = (User)request.getSession().getAttribute("user_data");
    this.educationId = userEducationDetails.getEducationId();
    this.userId = userEducationDetails.getUserId();
    this.educationName = userEducationDetails.getEducationName();
    this.specialization = userEducationDetails.getSpecialization();
    this.instituteName = userEducationDetails.getInstituteName();
    this.percentile = userEducationDetails.getPercentile();
    this.passingYear = userEducationDetails.getPassingYear();
    this.startingYear = userEducationDetails.getStartingYear();
  }
  
  public UserEducationDetails toValue(UserEducationDetails userEducationDetails, HttpServletRequest request)
    throws Exception
  {
    userEducationDetails.setUserId(this.userId);
    userEducationDetails.setEducationName(this.educationName);
    userEducationDetails.setSpecialization(this.specialization);
    userEducationDetails.setInstituteName(this.instituteName);
    userEducationDetails.setPercentile(this.percentile);
    userEducationDetails.setPassingYear(this.passingYear);
    userEducationDetails.setStartingYear(this.startingYear);
    userEducationDetails.setEduType(Common.EDUCATION_COLLEGE);
    
    return userEducationDetails;
  }
}
