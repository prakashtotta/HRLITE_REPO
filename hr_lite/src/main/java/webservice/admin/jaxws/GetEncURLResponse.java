package webservice.admin.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="getEncURLResponse", namespace="http://admin.webservice/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="getEncURLResponse", namespace="http://admin.webservice/")
public class GetEncURLResponse
{
  @XmlElement(name="return", namespace="")
  private String _return;
  
  public String getReturn()
  {
    return this._return;
  }
  
  public void setReturn(String paramString)
  {
    this._return = paramString;
  }
}
