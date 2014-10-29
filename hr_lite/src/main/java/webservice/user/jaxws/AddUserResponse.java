package webservice.user.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="addUserResponse", namespace="http://user.webservice/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="addUserResponse", namespace="http://user.webservice/")
public class AddUserResponse
{
  @XmlElement(name="return", namespace="")
  private long _return;
  
  public long getReturn()
  {
    return this._return;
  }
  
  public void setReturn(long paramLong)
  {
    this._return = paramLong;
  }
}
