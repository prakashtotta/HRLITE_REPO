package com.bean.employee;

import com.bean.Currency;
import com.bean.lov.MembershipType;
import java.util.Date;

public class UserMemberShip
{
  public long userMemberShipId;
  public long userId;
  private MembershipType membershiptype;
  private String subscriptionPaidBy;
  private String amount;
  private Currency currency;
  private Date subscriptionCommenceDate;
  private Date subscriptionRenewalDate;
  
  public long getUserMemberShipId()
  {
    return this.userMemberShipId;
  }
  
  public void setUserMemberShipId(long userMemberShipId)
  {
    this.userMemberShipId = userMemberShipId;
  }
  
  public long getUserId()
  {
    return this.userId;
  }
  
  public void setUserId(long userId)
  {
    this.userId = userId;
  }
  
  public MembershipType getMembershiptype()
  {
    return this.membershiptype;
  }
  
  public void setMembershiptype(MembershipType membershiptype)
  {
    this.membershiptype = membershiptype;
  }
  
  public String getSubscriptionPaidBy()
  {
    return this.subscriptionPaidBy;
  }
  
  public void setSubscriptionPaidBy(String subscriptionPaidBy)
  {
    this.subscriptionPaidBy = subscriptionPaidBy;
  }
  
  public String getAmount()
  {
    return this.amount;
  }
  
  public void setAmount(String amount)
  {
    this.amount = amount;
  }
  
  public Date getSubscriptionCommenceDate()
  {
    return this.subscriptionCommenceDate;
  }
  
  public void setSubscriptionCommenceDate(Date subscriptionCommenceDate)
  {
    this.subscriptionCommenceDate = subscriptionCommenceDate;
  }
  
  public Date getSubscriptionRenewalDate()
  {
    return this.subscriptionRenewalDate;
  }
  
  public void setSubscriptionRenewalDate(Date subscriptionRenewalDate)
  {
    this.subscriptionRenewalDate = subscriptionRenewalDate;
  }
  
  public Currency getCurrency()
  {
    return this.currency;
  }
  
  public void setCurrency(Currency currency)
  {
    this.currency = currency;
  }
}
