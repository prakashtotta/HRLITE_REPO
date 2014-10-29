package network.bean;

public class FbPositions
{
  private long positionId;
  private String facebookid;
  private String name;
  
  public long getPositionId()
  {
    return this.positionId;
  }
  
  public void setPositionId(long positionId)
  {
    this.positionId = positionId;
  }
  
  public String getFacebookid()
  {
    return this.facebookid;
  }
  
  public void setFacebookid(String facebookid)
  {
    this.facebookid = facebookid;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
}
