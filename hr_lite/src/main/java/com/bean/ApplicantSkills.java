package com.bean;

import java.util.Date;

public class ApplicantSkills
{
  private long skillId;
  private long applicantId;
  private String skillname;
  private String yearsofexp;
  private String rating;
  private String createdBy;
  private Date createdDate;
  private String uuid;
  
  public long getSkillId()
  {
    return this.skillId;
  }
  
  public void setSkillId(long skillId)
  {
    this.skillId = skillId;
  }
  
  public long getApplicantId()
  {
    return this.applicantId;
  }
  
  public void setApplicantId(long applicantId)
  {
    this.applicantId = applicantId;
  }
  
  public String getSkillname()
  {
    return this.skillname;
  }
  
  public void setSkillname(String skillname)
  {
    this.skillname = skillname;
  }
  
  public String getYearsofexp()
  {
    return this.yearsofexp;
  }
  
  public void setYearsofexp(String yearsofexp)
  {
    this.yearsofexp = yearsofexp;
  }
  
  public String getRating()
  {
    return this.rating;
  }
  
  public void setRating(String rating)
  {
    this.rating = rating;
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
  
  public String getUuid()
  {
    return this.uuid;
  }
  
  public void setUuid(String uuid)
  {
    this.uuid = uuid;
  }
}
