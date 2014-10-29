package network.bo;

import network.util.FaceBookReader;
import org.apache.log4j.Logger;

public class FbUserTask
  implements Runnable
{
  protected static final Logger logger = Logger.getLogger(FbUserTask.class);
  private String facebookid;
  private String sessionkey;
  private String type;
  
  public void run()
  {
    logger.info("run method start");
    try
    {
      if ((getType() != null) && (getType().equalsIgnoreCase("OWN")))
      {
        FaceBookReader freader = new FaceBookReader();
        freader.getOwnUserData(this.facebookid, this.sessionkey);
      }
      else
      {
        FaceBookReader freader = new FaceBookReader();
        freader.getFriendsData(this.facebookid, this.sessionkey);
      }
    }
    catch (Exception e)
    {
      logger.info("Exception on FbUserTask", e);
    }
    logger.info("run method start");
  }
  
  public String getFacebookid()
  {
    return this.facebookid;
  }
  
  public void setFacebookid(String facebookid)
  {
    this.facebookid = facebookid;
  }
  
  public String getSessionkey()
  {
    return this.sessionkey;
  }
  
  public void setSessionkey(String sessionkey)
  {
    this.sessionkey = sessionkey;
  }
  
  public String getType()
  {
    return this.type;
  }
  
  public void setType(String type)
  {
    this.type = type;
  }
}
