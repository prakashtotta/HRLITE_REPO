package com.bean.pool;

public class TalentPoolElements
{
  public long id;
  public String name;
  public long position;
  public long ownerId;
  public int slave;
  public long applicantId;
  public long applicantpoolidInitial;
  
  public long getId()
  {
    return this.id;
  }
  
  public void setId(long id)
  {
    this.id = id;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public long getPosition()
  {
    return this.position;
  }
  
  public void setPosition(long position)
  {
    this.position = position;
  }
  
  public long getOwnerId()
  {
    return this.ownerId;
  }
  
  public void setOwnerId(long ownerId)
  {
    this.ownerId = ownerId;
  }
  
  public int getSlave()
  {
    return this.slave;
  }
  
  public void setSlave(int slave)
  {
    this.slave = slave;
  }
  
  public long getApplicantId()
  {
    return this.applicantId;
  }
  
  public void setApplicantId(long applicantId)
  {
    this.applicantId = applicantId;
  }
  
  public long getApplicantpoolidInitial()
  {
    return this.applicantpoolidInitial;
  }
  
  public void setApplicantpoolidInitial(long applicantpoolidInitial)
  {
    this.applicantpoolidInitial = applicantpoolidInitial;
  }
}
