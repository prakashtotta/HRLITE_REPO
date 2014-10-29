package network.bean;

public class FbLocation
{
  private long locationId;
  private String facebookid;
  private String name;
  
  public long getLocationId()
  {
    return this.locationId;
  }
  
  public void setLocationId(long locationId)
  {
    this.locationId = locationId;
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
