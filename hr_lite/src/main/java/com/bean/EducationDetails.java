package com.bean;

import java.util.Date;
import java.util.List;

public class EducationDetails
{
  private long educationId;
  private long applicantId;
  public String educationName;
  public String specialization;
  public String instituteName;
  public String percentile;
  public String passingYear;
  public String startingYear;
  private String eduType;
  private String createdBy;
  private Date createdDate;
  private String uuid;
  List attachmentList;
  
  public long getEducationId()
  {
    return this.educationId;
  }
  
  public void setEducationId(long educationId)
  {
    this.educationId = educationId;
  }
  
  public long getApplicantId()
  {
    return this.applicantId;
  }
  
  public void setApplicantId(long applicantId)
  {
    this.applicantId = applicantId;
  }
  
  public String getEducationName()
  {
    return this.educationName;
  }
  
  public void setEducationName(String educationName)
  {
    this.educationName = educationName;
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
  
  public String getEduType()
  {
    return this.eduType;
  }
  
  public void setEduType(String eduType)
  {
    this.eduType = eduType;
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
  
  public String getSpecialization()
  {
    return this.specialization;
  }
  
  public void setSpecialization(String specialization)
  {
    this.specialization = specialization;
  }
  
  public List getAttachmentList()
  {
    return this.attachmentList;
  }
  
  public void setAttachmentList(List attachmentList)
  {
    this.attachmentList = attachmentList;
  }
  
  public String getStartingYear()
  {
    return this.startingYear;
  }
  
  public void setStartingYear(String startingYear)
  {
    this.startingYear = startingYear;
  }
  
  public String getUuid()
  {
    return this.uuid;
  }
  
  public void setUuid(String uuid)
  {
    this.uuid = uuid;
  }
}
