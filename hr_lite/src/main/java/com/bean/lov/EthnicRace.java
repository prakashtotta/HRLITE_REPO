package com.bean.lov;

public class EthnicRace
{
  public long ethnicRaceId;
  public String ethnicRaceName;
  public String ethnicRaceDesc;
  public String status;
  public long super_user_key;
  
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
  
  public long getSuper_user_key()
  {
    return this.super_user_key;
  }
  
  public void setSuper_user_key(long superUserKey)
  {
    this.super_user_key = superUserKey;
  }
}
