package com.form.employee;

import com.bean.employee.UserSkills;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;

public class UserSkillsForm
  extends ActionForm
{
  private long skillId;
  public long userId;
  private String skillname;
  private String yearsofexp;
  private String rating;
  private List userSkillList;
  private List skillRatingList;
  private List skillYearsList;
  
  public List getSkillYearsList()
  {
    return this.skillYearsList;
  }
  
  public void setSkillYearsList(List skillYearsList)
  {
    this.skillYearsList = skillYearsList;
  }
  
  public List getSkillRatingList()
  {
    return this.skillRatingList;
  }
  
  public void setSkillRatingList(List skillRatingList)
  {
    this.skillRatingList = skillRatingList;
  }
  
  public List getUserSkillList()
  {
    return this.userSkillList;
  }
  
  public void setUserSkillList(List userSkillList)
  {
    this.userSkillList = userSkillList;
  }
  
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
  
  public void fromValue(UserSkills userSkills, HttpServletRequest request)
  {
    this.skillId = userSkills.getSkillId();
    this.userId = userSkills.getUserId();
    this.skillname = userSkills.getSkillname();
    this.yearsofexp = userSkills.getYearsofexp();
    this.rating = userSkills.getRating();
  }
  
  public UserSkills toValue(UserSkills userSkills, HttpServletRequest request)
    throws Exception
  {
    userSkills.setUserId(this.userId);
    userSkills.setSkillname(this.skillname);
    userSkills.setYearsofexp(this.yearsofexp);
    userSkills.setRating(this.rating);
    return userSkills;
  }
}
