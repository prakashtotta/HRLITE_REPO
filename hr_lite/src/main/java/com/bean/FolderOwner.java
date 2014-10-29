package com.bean;

public class FolderOwner
{
  public long folderOwnerId;
  public String folderName;
  public long assigneeId;
  public String assigneeName;
  public String isGroup;
  public long super_user_key;
  
  public long getFolderOwnerId()
  {
    return this.folderOwnerId;
  }
  
  public void setFolderOwnerId(long folderOwnerId)
  {
    this.folderOwnerId = folderOwnerId;
  }
  
  public String getFolderName()
  {
    return this.folderName;
  }
  
  public void setFolderName(String folderName)
  {
    this.folderName = folderName;
  }
  
  public long getAssigneeId()
  {
    return this.assigneeId;
  }
  
  public void setAssigneeId(long assigneeId)
  {
    this.assigneeId = assigneeId;
  }
  
  public String getAssigneeName()
  {
    return this.assigneeName;
  }
  
  public void setAssigneeName(String assigneeName)
  {
    this.assigneeName = assigneeName;
  }
  
  public String getIsGroup()
  {
    return this.isGroup;
  }
  
  public void setIsGroup(String isGroup)
  {
    this.isGroup = isGroup;
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
