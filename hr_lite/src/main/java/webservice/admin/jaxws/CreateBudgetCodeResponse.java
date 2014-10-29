package webservice.admin.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="createBudgetCodeResponse", namespace="http://admin.webservice/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="createBudgetCodeResponse", namespace="http://admin.webservice/")
public class CreateBudgetCodeResponse
{
  @XmlElement(name="return", namespace="")
  private boolean _return;
  
  public boolean isReturn()
  {
    return this._return;
  }
  
  public void setReturn(boolean paramBoolean)
  {
    this._return = paramBoolean;
  }
}
