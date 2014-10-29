package network.bean;

public class FbUserSchools
{
  private long userScId;
  public long userId;
  private FbSchool school;
  private int level;
  private String year;
  private String type;
  private FbConcentration concentration;
  private FbLocation location;
  
  public long getUserScId()
  {
    return this.userScId;
  }
  
  public void setUserScId(long userScId)
  {
    this.userScId = userScId;
  }
  
  public long getUserId()
  {
    return this.userId;
  }
  
  public void setUserId(long userId)
  {
    this.userId = userId;
  }
  
  public FbSchool getSchool()
  {
    return this.school;
  }
  
  public void setSchool(FbSchool school)
  {
    this.school = school;
  }
  
  public int getLevel()
  {
    return this.level;
  }
  
  public void setLevel(int level)
  {
    this.level = level;
  }
  
  public String getType()
  {
    return this.type;
  }
  
  public void setType(String type)
  {
    this.type = type;
  }
  
  public FbConcentration getConcentration()
  {
    return this.concentration;
  }
  
  public void setConcentration(FbConcentration concentration)
  {
    this.concentration = concentration;
  }
  
  public String getYear()
  {
    return this.year;
  }
  
  public void setYear(String year)
  {
    this.year = year;
  }
  
  public FbLocation getLocation()
  {
    return this.location;
  }
  
  public void setLocation(FbLocation location)
  {
    this.location = location;
  }
}
