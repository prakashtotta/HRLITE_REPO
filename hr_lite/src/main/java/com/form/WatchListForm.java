package com.form;

import java.util.Date;
import java.util.List;
import org.apache.struts.action.ActionForm;

public class WatchListForm
  extends ActionForm
{
  public long watchListId;
  public long reqId;
  public long applicantId;
  public String type;
  public long userUserGrpId;
  public String isGroup = "N";
  public String userUserGrpName;
  public String createdBy;
  public Date createdDate;
  List watchList;
  
  public long getWatchListId()
  {
    return this.watchListId;
  }
  
  public void setWatchListId(long watchListId)
  {
    this.watchListId = watchListId;
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
  
  public List getWatchList()
  {
    return this.watchList;
  }
  
  public void setWatchList(List watchList)
  {
    this.watchList = watchList;
  }
  
  public long getReqId()
  {
    return this.reqId;
  }
  
  public void setReqId(long reqId)
  {
    this.reqId = reqId;
  }
  
  public long getApplicantId()
  {
    return this.applicantId;
  }
  
  public void setApplicantId(long applicantId)
  {
    this.applicantId = applicantId;
  }
}
