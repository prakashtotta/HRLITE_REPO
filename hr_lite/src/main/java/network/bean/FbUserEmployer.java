package network.bean;

public class FbUserEmployer
{
  private long userEmId;
  public long userId;
  private FbEmployer employer;
  private int level;
  private String startDate;
  private String endDate;
  private FbPositions position;
  private FbLocation location;
  private String description;
  
  public long getUserEmId()
  {
    return this.userEmId;
  }
  
  public void setUserEmId(long userEmId)
  {
    this.userEmId = userEmId;
  }
  
  public long getUserId()
  {
    return this.userId;
  }
  
  public void setUserId(long userId)
  {
    this.userId = userId;
  }
  
  public FbEmployer getEmployer()
  {
    return this.employer;
  }
  
  public void setEmployer(FbEmployer employer)
  {
    this.employer = employer;
  }
  
  public int getLevel()
  {
    return this.level;
  }
  
  public void setLevel(int level)
  {
    this.level = level;
  }
  
  public String getStartDate()
  {
    return this.startDate;
  }
  
  public void setStartDate(String startDate)
  {
    this.startDate = startDate;
  }
  
  public String getEndDate()
  {
    return this.endDate;
  }
  
  public void setEndDate(String endDate)
  {
    this.endDate = endDate;
  }
  
  public FbPositions getPosition()
  {
    return this.position;
  }
  
  public void setPosition(FbPositions position)
  {
    this.position = position;
  }
  
  public FbLocation getLocation()
  {
    return this.location;
  }
  
  public void setLocation(FbLocation location)
  {
    this.location = location;
  }
  
  public String getDescription()
  {
    return this.description;
  }
  
  public void setDescription(String description)
  {
    this.description = description;
  }
}
