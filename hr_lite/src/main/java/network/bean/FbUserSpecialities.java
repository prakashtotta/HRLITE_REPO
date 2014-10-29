package network.bean;

public class FbUserSpecialities
{
  private long userSpeId;
  public long userId;
  private String specialities;
  
  public long getUserSpeId()
  {
    return this.userSpeId;
  }
  
  public void setUserSpeId(long userSpeId)
  {
    this.userSpeId = userSpeId;
  }
  
  public long getUserId()
  {
    return this.userId;
  }
  
  public void setUserId(long userId)
  {
    this.userId = userId;
  }
  
  public String getSpecialities()
  {
    return this.specialities;
  }
  
  public void setSpecialities(String specialities)
  {
    this.specialities = specialities;
  }
}
