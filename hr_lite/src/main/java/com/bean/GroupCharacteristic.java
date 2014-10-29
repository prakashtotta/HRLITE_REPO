package com.bean;

import java.util.Date;
import java.util.List;

public class GroupCharacteristic
{
  public long groupCharId;
  public String groupCharName;
  public String groupCharDesc;
  public String strChars;
  public String createdBy;
  public Date createdDate;
  private Date updatedDate;
  private String updatedBy;
  private String status;
  private List charList;
  
  public long getGroupCharId()
  {
    return this.groupCharId;
  }
  
  public void setGroupCharId(long groupCharId)
  {
    this.groupCharId = groupCharId;
  }
  
  public String getGroupCharName()
  {
    return this.groupCharName;
  }
  
  public void setGroupCharName(String groupCharName)
  {
    this.groupCharName = groupCharName;
  }
  
  public String getGroupCharDesc()
  {
    return this.groupCharDesc;
  }
  
  public void setGroupCharDesc(String groupCharDesc)
  {
    this.groupCharDesc = groupCharDesc;
  }
  
  public String getStrChars()
  {
    return this.strChars;
  }
  
  public void setStrChars(String strChars)
  {
    this.strChars = strChars;
  }
  
  public List getCharList()
  {
    return this.charList;
  }
  
  public void setCharList(List charList)
  {
    this.charList = charList;
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
  
  public Date getUpdatedDate()
  {
    return this.updatedDate;
  }
  
  public void setUpdatedDate(Date updatedDate)
  {
    this.updatedDate = updatedDate;
  }
  
  public String getUpdatedBy()
  {
    return this.updatedBy;
  }
  
  public void setUpdatedBy(String updatedBy)
  {
    this.updatedBy = updatedBy;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
}
