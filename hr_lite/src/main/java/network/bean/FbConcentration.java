package network.bean;

public class FbConcentration
{
  private long concentrationId;
  private String facebookid;
  private String name;
  
  public long getConcentrationId()
  {
    return this.concentrationId;
  }
  
  public void setConcentrationId(long concentrationId)
  {
    this.concentrationId = concentrationId;
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
