package webservice.admin.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import webservice.common.Authontication;

@XmlRootElement(name="getEncURL", namespace="http://admin.webservice/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="getEncURL", namespace="http://admin.webservice/", propOrder={"auth", "userName", "sharedSecret", "page"})
public class GetEncURL
{
  @XmlElement(name="auth", namespace="")
  private Authontication auth;
  @XmlElement(name="userName", namespace="")
  private String userName;
  @XmlElement(name="sharedSecret", namespace="")
  private String sharedSecret;
  @XmlElement(name="page", namespace="")
  private String page;
  
  public Authontication getAuth()
  {
    return this.auth;
  }
  
  public void setAuth(Authontication paramAuthontication)
  {
    this.auth = paramAuthontication;
  }
  
  public String getUserName()
  {
    return this.userName;
  }
  
  public void setUserName(String paramString)
  {
    this.userName = paramString;
  }
  
  public String getSharedSecret()
  {
    return this.sharedSecret;
  }
  
  public void setSharedSecret(String paramString)
  {
    this.sharedSecret = paramString;
  }
  
  public String getPage()
  {
    return this.page;
  }
  
  public void setPage(String paramString)
  {
    this.page = paramString;
  }
}
