package network.bean;

public class FbEmployer
{
  private long emId;
  private String facebookid;
  private String name;
  
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
  
  public long getEmId()
  {
    return this.emId;
  }
  
  public void setEmId(long emId)
  {
    this.emId = emId;
  }
}
