package com.form;

import com.bean.lov.MembershipType;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;

public class MembershipTypeForm
  extends ActionForm
{
  protected static final Logger logger = Logger.getLogger(MembershipTypeForm.class);
  public long membershipTypeId;
  public String membershipTypeName;
  public String membershipTypeDesc;
  public String status;
  
  public long getMembershipTypeId()
  {
    return this.membershipTypeId;
  }
  
  public void setMembershipTypeId(long membershipTypeId)
  {
    this.membershipTypeId = membershipTypeId;
  }
  
  public String getMembershipTypeName()
  {
    return this.membershipTypeName;
  }
  
  public void setMembershipTypeName(String membershipTypeName)
  {
    this.membershipTypeName = membershipTypeName;
  }
  
  public String getMembershipTypeDesc()
  {
    return this.membershipTypeDesc;
  }
  
  public void setMembershipTypeDesc(String membershipTypeDesc)
  {
    this.membershipTypeDesc = membershipTypeDesc;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public MembershipType toValue(MembershipType membershipType, HttpServletRequest request)
    throws Exception
  {
    membershipType.setMembershipTypeName(this.membershipTypeName);
    membershipType.setMembershipTypeDesc(this.membershipTypeDesc);
    return membershipType;
  }
  
  public void fromValue(MembershipType membershipType, HttpServletRequest request)
  {
    this.membershipTypeId = membershipType.getMembershipTypeId();
    this.membershipTypeName = membershipType.getMembershipTypeName();
    this.membershipTypeDesc = membershipType.getMembershipTypeDesc();
  }
}
