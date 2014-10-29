package network.bean;

public class FbSavedCompanies
{
  private long fbsavecomId;
  private long userId;
  private String comFacebookid;
  
  public long getFbsavecomId()
  {
    return this.fbsavecomId;
  }
  
  public void setFbsavecomId(long fbsavecomId)
  {
    this.fbsavecomId = fbsavecomId;
  }
  
  public long getUserId()
  {
    return this.userId;
  }
  
  public void setUserId(long userId)
  {
    this.userId = userId;
  }
  
  public String getComFacebookid()
  {
    return this.comFacebookid;
  }
  
  public void setComFacebookid(String comFacebookid)
  {
    this.comFacebookid = comFacebookid;
  }
}
