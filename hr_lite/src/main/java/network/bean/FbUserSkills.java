package network.bean;

public class FbUserSkills
{
  private long userSkId;
  public long userId;
  private int level;
  private String skill;
  private String yearsExp;
  
  public long getUserId()
  {
    return this.userId;
  }
  
  public void setUserId(long userId)
  {
    this.userId = userId;
  }
  
  public int getLevel()
  {
    return this.level;
  }
  
  public void setLevel(int level)
  {
    this.level = level;
  }
  
  public String getYearsExp()
  {
    return this.yearsExp;
  }
  
  public void setYearsExp(String yearsExp)
  {
    this.yearsExp = yearsExp;
  }
  
  public long getUserSkId()
  {
    return this.userSkId;
  }
  
  public void setUserSkId(long userSkId)
  {
    this.userSkId = userSkId;
  }
  
  public String getSkill()
  {
    return this.skill;
  }
  
  public void setSkill(String skill)
  {
    this.skill = skill;
  }
}
