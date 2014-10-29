package com.bean;

import java.util.Date;

public class Characteristic
{
  public long charId;
  public String charName;
  public String charDesc;
  public int weight;
  String status;
  public String createdBy;
  public Date createdDate;
  public long super_user_key;
  
  public long getCharId()
  {
    return this.charId;
  }
  
  public void setCharId(long charId)
  {
    this.charId = charId;
  }
  
  public String getCharName()
  {
    return this.charName;
  }
  
  public void setCharName(String charName)
  {
    this.charName = charName;
  }
  
  public String getCharDesc()
  {
    return this.charDesc;
  }
  
  public void setCharDesc(String charDesc)
  {
    this.charDesc = charDesc;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public int getWeight()
  {
    return this.weight;
  }
  
  public void setWeight(int weight)
  {
    this.weight = weight;
  }
  
  public String getCreatedBy()
  {
    return this.createdBy;
  }
  
  public void setCreatedBy(String createdBy)
  {
    this.createdBy = createdBy;
  }
  
  public Date getCreatedDate()
  {
    return this.createdDate;
  }
  
  public void setCreatedDate(Date createdDate)
  {
    this.createdDate = createdDate;
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
