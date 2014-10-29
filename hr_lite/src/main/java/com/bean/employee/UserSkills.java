package com.bean.employee;

public class UserSkills
{
  private long skillId;
  public long userId;
  private String skillname;
  private String yearsofexp;
  private String rating;
  
  public long getSkillId()
  {
    return this.skillId;
  }
  
  public void setSkillId(long skillId)
  {
    this.skillId = skillId;
  }
  
  public long getUserId()
  {
    return this.userId;
  }
  
  public void setUserId(long userId)
  {
    this.userId = userId;
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
  
  public String getSkillname()
  {
    return this.skillname;
  }
  
  public void setSkillname(String skillname)
  {
    this.skillname = skillname;
  }
}
