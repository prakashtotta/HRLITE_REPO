package com.form.employee;

import com.bean.employee.EmergencyContact;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;

public class UserEmergencyContactForm
  extends ActionForm
{
  public long emergencyContactId;
  public long userId;
  public String name;
  public String relationship;
  public String mobileNo;
  public String phoneHome;
  public String phoneOffice;
  private List userEmergencyContactList;
  
  public List getUserEmergencyContactList()
  {
    return this.userEmergencyContactList;
  }
  
  public void setUserEmergencyContactList(List userEmergencyContactList)
  {
    this.userEmergencyContactList = userEmergencyContactList;
  }
  
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
  
  public void fromValue(EmergencyContact uci, HttpServletRequest request)
  {
    this.emergencyContactId = uci.getEmergencyContactId();
    this.userId = uci.getUserId();
    this.name = uci.getName();
    this.relationship = uci.getRelationship();
    this.mobileNo = uci.getMobileNo();
    this.phoneHome = uci.getPhoneHome();
    this.phoneOffice = uci.getPhoneOffice();
  }
  
  public EmergencyContact toValue(EmergencyContact uci, HttpServletRequest request)
    throws Exception
  {
    uci.setUserId(this.userId);
    uci.setName(this.name);
    uci.setRelationship(this.relationship);
    uci.setPhoneHome(this.phoneHome);
    uci.setMobileNo(this.mobileNo);
    uci.setPhoneOffice(this.phoneOffice);
    return uci;
  }
}
