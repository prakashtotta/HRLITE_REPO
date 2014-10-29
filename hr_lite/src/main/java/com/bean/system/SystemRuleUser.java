package com.bean.system;

import com.bean.User;
import com.bean.UserGroup;

public class SystemRuleUser
{
  public long systemRuleUserId;
  public User user;
  public int levelorder;
  public long systemRuleId;
  public UserGroup userGroup;
  public String isGroup;
  
  public long getSystemRuleUserId()
  {
    return this.systemRuleUserId;
  }
  
  public void setSystemRuleUserId(long systemRuleUserId)
  {
    this.systemRuleUserId = systemRuleUserId;
  }
  
  public int getLevelorder()
  {
    return this.levelorder;
  }
  
  public void setLevelorder(int levelorder)
  {
    this.levelorder = levelorder;
  }
  
  public long getSystemRuleId()
  {
    return this.systemRuleId;
  }
  
  public void setSystemRuleId(long systemRuleId)
  {
    this.systemRuleId = systemRuleId;
  }
  
  public User getUser()
  {
    return this.user;
  }
  
  public void setUser(User user)
  {
    this.user = user;
  }
  
  public String getIsGroup()
  {
    return this.isGroup;
  }
  
  public void setIsGroup(String isGroup)
  {
    this.isGroup = isGroup;
  }
  
  public UserGroup getUserGroup()
  {
    return this.userGroup;
  }
  
  public void setUserGroup(UserGroup userGroup)
  {
    this.userGroup = userGroup;
  }
}
