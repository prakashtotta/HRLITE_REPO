package com.bean.employee;

import java.util.Date;

public class UserDependents
{
  public long userDependentId;
  public long userId;
  public String name;
  public String relationship;
  public String relationshipOther;
  public Date dateofbirth;
  
  public String getRelationshipOther()
  {
    return this.relationshipOther;
  }
  
  public void setRelationshipOther(String relationshipOther)
  {
    this.relationshipOther = relationshipOther;
  }
  
  public long getUserId()
  {
    return this.userId;
  }
  
  public void setUserId(long userId)
  {
    this.userId = userId;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public String getRelationship()
  {
    return this.relationship;
  }
  
  public void setRelationship(String relationship)
  {
    this.relationship = relationship;
  }
  
  public Date getDateofbirth()
  {
    return this.dateofbirth;
  }
  
  public void setDateofbirth(Date dateofbirth)
  {
    this.dateofbirth = dateofbirth;
  }
  
  public long getUserDependentId()
  {
    return this.userDependentId;
  }
  
  public void setUserDependentId(long userDependentId)
  {
    this.userDependentId = userDependentId;
  }
}
