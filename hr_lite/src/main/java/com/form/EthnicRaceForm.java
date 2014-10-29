package com.form;

import com.bean.lov.EthnicRace;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;

public class EthnicRaceForm
  extends ActionForm
{
  public long ethnicRaceId;
  public String ethnicRaceName;
  public String ethnicRaceDesc;
  public String status;
  
  public long getEthnicRaceId()
  {
    return this.ethnicRaceId;
  }
  
  public void setEthnicRaceId(long ethnicRaceId)
  {
    this.ethnicRaceId = ethnicRaceId;
  }
  
  public String getEthnicRaceName()
  {
    return this.ethnicRaceName;
  }
  
  public void setEthnicRaceName(String ethnicRaceName)
  {
    this.ethnicRaceName = ethnicRaceName;
  }
  
  public String getEthnicRaceDesc()
  {
    return this.ethnicRaceDesc;
  }
  
  public void setEthnicRaceDesc(String ethnicRaceDesc)
  {
    this.ethnicRaceDesc = ethnicRaceDesc;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public EthnicRace toValue(EthnicRace ethnicRace, HttpServletRequest request)
    throws Exception
  {
    ethnicRace.setEthnicRaceName(this.ethnicRaceName);
    ethnicRace.setEthnicRaceDesc(this.ethnicRaceDesc);
    
    return ethnicRace;
  }
  
  public void fromValue(EthnicRace ethnicRace, HttpServletRequest request)
    throws Exception
  {
    this.ethnicRaceId = ethnicRace.getEthnicRaceId();
    this.ethnicRaceName = ethnicRace.getEthnicRaceName();
    this.ethnicRaceDesc = ethnicRace.getEthnicRaceDesc();
  }
}
