package com.bean;

import java.util.Date;
import java.util.Set;

public class UserGroup
{
  public long usergrpId;
  public String usergrpName;
  public String usergrpDesc;
  public String status;
  public String createdBy;
  public Date createdDate;
  public Date updatedDate;
  public String updatedBy;
  public User primaryOwner;
  private Set users;
  public long super_user_key;
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
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
  
  public Set getUsers()
  {
    return this.users;
  }
  
  public void setUsers(Set users)
  {
    this.users = users;
  }
  
  public long getUsergrpId()
  {
    return this.usergrpId;
  }
  
  public void setUsergrpId(long usergrpId)
  {
    this.usergrpId = usergrpId;
  }
  
  public String getUsergrpName()
  {
    return this.usergrpName;
  }
  
  public void setUsergrpName(String usergrpName)
  {
    this.usergrpName = usergrpName;
  }
  
  public String getUsergrpDesc()
  {
    return this.usergrpDesc;
  }
  
  public void setUsergrpDesc(String usergrpDesc)
  {
    this.usergrpDesc = usergrpDesc;
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
