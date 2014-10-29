package network.bean;

public class FbUserAchivements
{
  private long userAchId;
  public long userId;
  private String achivement;
  
  public long getUserAchId()
  {
    return this.userAchId;
  }
  
  public void setUserAchId(long userAchId)
  {
    this.userAchId = userAchId;
  }
  
  public long getUserId()
  {
    return this.userId;
  }
  
  public void setUserId(long userId)
  {
    this.userId = userId;
  }
  
  public String getAchivement()
  {
    return this.achivement;
  }
  
  public void setAchivement(String achivement)
  {
    this.achivement = achivement;
  }
}
