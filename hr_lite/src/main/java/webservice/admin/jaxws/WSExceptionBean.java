package webservice.admin.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="WSException", namespace="http://admin.webservice/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="WSException", namespace="http://admin.webservice/", propOrder={"errorCode", "errorDescription", "message"})
public class WSExceptionBean
{
  private String errorCode;
  private String errorDescription;
  private String message;
  
  public String getErrorCode()
  {
    return this.errorCode;
  }
  
  public void setErrorCode(String paramString)
  {
    this.errorCode = paramString;
  }
  
  public String getErrorDescription()
  {
    return this.errorDescription;
  }
  
  public void setErrorDescription(String paramString)
  {
    this.errorDescription = paramString;
  }
  
  public String getMessage()
  {
    return this.message;
  }
  
  public void setMessage(String paramString)
  {
    this.message = paramString;
  }
}
