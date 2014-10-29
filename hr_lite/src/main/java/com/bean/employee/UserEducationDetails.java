package com.bean.employee;

public class UserEducationDetails
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
}
