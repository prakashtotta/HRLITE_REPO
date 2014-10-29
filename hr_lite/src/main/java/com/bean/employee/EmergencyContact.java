package com.bean.employee;

public class EmergencyContact
{
  public long emergencyContactId;
  public long userId;
  public String name;
  public String relationship;
  public String mobileNo;
  public String phoneHome;
  public String phoneOffice;
  
  public long getEmergencyContactId()
  {
    return this.emergencyContactId;
  }
  
  public void setEmergencyContactId(long emergencyContactId)
  {
    this.emergencyContactId = emergencyContactId;
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
  
  public String getMobileNo()
  {
    return this.mobileNo;
  }
  
  public void setMobileNo(String mobileNo)
  {
    this.mobileNo = mobileNo;
  }
  
  public String getPhoneHome()
  {
    return this.phoneHome;
  }
  
  public void setPhoneHome(String phoneHome)
  {
    this.phoneHome = phoneHome;
  }
  
  public String getPhoneOffice()
  {
    return this.phoneOffice;
  }
  
  public void setPhoneOffice(String phoneOffice)
  {
    this.phoneOffice = phoneOffice;
  }
}
