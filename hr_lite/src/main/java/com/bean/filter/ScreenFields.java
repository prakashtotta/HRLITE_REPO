package com.bean.filter;

public class ScreenFields
  implements Comparable<ScreenFields>
{
  public long screenfieldId;
  public String screenCode;
  public String fieldcode;
  public String fieldtype;
  public String isvisible = "Y";
  public String issystem = "Y";
  public String isMandatory = "Y";
  public String status = "Y";
  public String groupCode;
  public int sequenceId;
  public long super_user_key;
  
  public long getScreenfieldId()
  {
    return this.screenfieldId;
  }
  
  public void setScreenfieldId(long screenfieldId)
  {
    this.screenfieldId = screenfieldId;
  }
  
  public String getScreenCode()
  {
    return this.screenCode;
  }
  
  public void setScreenCode(String screenCode)
  {
    this.screenCode = screenCode;
  }
  
  public String getFieldcode()
  {
    return this.fieldcode;
  }
  
  public void setFieldcode(String fieldcode)
  {
    this.fieldcode = fieldcode;
  }
  
  public String getFieldtype()
  {
    return this.fieldtype;
  }
  
  public void setFieldtype(String fieldtype)
  {
    this.fieldtype = fieldtype;
  }
  
  public String getIsvisible()
  {
    return this.isvisible;
  }
  
  public void setIsvisible(String isvisible)
  {
    this.isvisible = isvisible;
  }
  
  public String getIssystem()
  {
    return this.issystem;
  }
  
  public void setIssystem(String issystem)
  {
    this.issystem = issystem;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public String getIsMandatory()
  {
    return this.isMandatory;
  }
  
  public void setIsMandatory(String isMandatory)
  {
    this.isMandatory = isMandatory;
  }
  
  public int getSequenceId()
  {
    return this.sequenceId;
  }
  
  public void setSequenceId(int sequenceId)
  {
    this.sequenceId = sequenceId;
  }
  
  public String getGroupCode()
  {
    return this.groupCode;
  }
  
  public void setGroupCode(String groupCode)
  {
    this.groupCode = groupCode;
  }
  
  public int compareTo(ScreenFields sceen)
  {
    int result = 0;
    if (this.sequenceId > sceen.sequenceId) {
      result = 1;
    }
    if (this.sequenceId < sceen.sequenceId) {
      result = -1;
    }
    return result;
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
