package network.bean;

public class FbSchool
{
  private long schoolId;
  private String facebookid;
  private String name;
  
  public long getSchoolId()
  {
    return this.schoolId;
  }
  
  public void setSchoolId(long schoolId)
  {
    this.schoolId = schoolId;
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
