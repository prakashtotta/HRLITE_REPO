package com.bean;

import java.util.Date;

public class WatchList
{
  public long watchListId;
  public long idvalue;
  public String type;
  public long userUserGrpId;
  public String isGroup = "N";
  public String userUserGrpName;
  public String createdBy;
  public Date createdDate;
  
  public long getWatchListId()
  {
    return this.watchListId;
  }
  
  public void setWatchListId(long watchListId)
  {
    this.watchListId = watchListId;
  }
  
  public long getIdvalue()
  {
    return this.idvalue;
  }
  
  public void setIdvalue(long idvalue)
  {
    this.idvalue = idvalue;
  }
  
  public String getType()
  {
    return this.type;
  }
  
  public void setType(String type)
  {
    this.type = type;
  }
  
  public long getUserUserGrpId()
  {
    return this.userUserGrpId;
  }
  
  public void setUserUserGrpId(long userUserGrpId)
  {
    this.userUserGrpId = userUserGrpId;
  }
  
  public String getIsGroup()
  {
    return this.isGroup;
  }
  
  public void setIsGroup(String isGroup)
  {
    this.isGroup = isGroup;
  }
  
  public String getUserUserGrpName()
  {
    return this.userUserGrpName;
  }
  
  public void setUserUserGrpName(String userUserGrpName)
  {
    this.userUserGrpName = userUserGrpName;
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
}
