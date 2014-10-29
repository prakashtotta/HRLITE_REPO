package network.bean;

public class FbUserCertifications
{
  private long userCertId;
  public long userId;
  private String certName;
  private String certOrg;
  private String year;
  
  public long getUserCertId()
  {
    return this.userCertId;
  }
  
  public void setUserCertId(long userCertId)
  {
    this.userCertId = userCertId;
  }
  
  public long getUserId()
  {
    return this.userId;
  }
  
  public void setUserId(long userId)
  {
    this.userId = userId;
  }
  
  public String getCertName()
  {
    return this.certName;
  }
  
  public void setCertName(String certName)
  {
    this.certName = certName;
  }
  
  public String getCertOrg()
  {
    return this.certOrg;
  }
  
  public void setCertOrg(String certOrg)
  {
    this.certOrg = certOrg;
  }
  
  public String getYear()
  {
    return this.year;
  }
  
  public void setYear(String year)
  {
    this.year = year;
  }
}
