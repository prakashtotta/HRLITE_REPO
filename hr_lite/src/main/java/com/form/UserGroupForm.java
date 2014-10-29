package com.form;

import java.util.Date;
import java.util.List;
import java.util.Set;
import org.apache.struts.action.ActionForm;

public class UserGroupForm
  extends ActionForm
{
  public long usergrpId;
  public String usergrpName;
  public String usergrpDesc;
  public String status;
  public String createdBy;
  public Date createdDate;
  public Date updatedDate;
  public String updatedBy;
  public Set usersset;
  public String readPreview;
  public String userhidden;
  public String UserName;
  public long userId;
  public List useridListVal;
  
  public String getReadPreview()
  {
    return this.readPreview;
  }
  
  public void setReadPreview(String readPreview)
  {
    this.readPreview = readPreview;
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
  
  public Set getUsersset()
  {
    return this.usersset;
  }
  
  public void setUsersset(Set usersset)
  {
    this.usersset = usersset;
  }
  
  public String getUserName()
  {
    return this.UserName;
  }
  
  public void setUserName(String userName)
  {
    this.UserName = userName;
  }
  
  public long getUserId()
  {
    return this.userId;
  }
  
  public void setUserId(long userId)
  {
    this.userId = userId;
  }
  
  public String getUserhidden()
  {
    return this.userhidden;
  }
  
  public void setUserhidden(String userhidden)
  {
    this.userhidden = userhidden;
  }
  
  public List getUseridListVal()
  {
    return this.useridListVal;
  }
  
  public void setUseridListVal(List useridListVal)
  {
    this.useridListVal = useridListVal;
  }
}
