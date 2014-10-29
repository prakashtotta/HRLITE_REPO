package webservice.user.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import webservice.common.Authontication;
import webservice.user.UserData;

@XmlRootElement(name="addUser", namespace="http://user.webservice/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="addUser", namespace="http://user.webservice/", propOrder={"auth", "userdata"})
public class AddUser
{
  @XmlElement(name="auth", namespace="")
  private Authontication auth;
  @XmlElement(name="userdata", namespace="")
  private UserData userdata;
  
  public Authontication getAuth()
  {
    return this.auth;
  }
  
  public void setAuth(Authontication paramAuthontication)
  {
    this.auth = paramAuthontication;
  }
  
  public UserData getUserdata()
  {
    return this.userdata;
  }
  
  public void setUserdata(UserData paramUserData)
  {
    this.userdata = paramUserData;
  }
}
