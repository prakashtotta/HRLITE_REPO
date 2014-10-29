package com.bean;

public class Tags
{
  public long tagId;
  public String tagName;
  public String tagType;
  public String status;
  public long super_user_key;
  
  public long getTagId()
  {
    return this.tagId;
  }
  
  public void setTagId(long tagId)
  {
    this.tagId = tagId;
  }
  
  public String getTagName()
  {
    return this.tagName;
  }
  
  public void setTagName(String tagName)
  {
    this.tagName = tagName;
  }
  
  public String getTagType()
  {
    return this.tagType;
  }
  
  public void setTagType(String tagType)
  {
    this.tagType = tagType;
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
