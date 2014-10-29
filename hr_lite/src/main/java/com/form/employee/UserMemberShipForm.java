package com.form.employee;

import com.bean.Currency;
import com.bean.User;
import com.bean.employee.UserMemberShip;
import com.bean.lov.MembershipType;
import com.resources.Constant;
import com.util.DateUtil;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;

public class UserMemberShipForm
  extends ActionForm
{
  public long userMemberShipId;
  public long userId;
  private MembershipType membershiptype;
  private String subscriptionPaidBy;
  private String amount;
  private Currency currency;
  private String subscriptionCommenceDate;
  private String subscriptionRenewalDate;
  private List membershipTypesList;
  public long membershipTypeId = 0L;
  public String membershipTypeName;
  private List currencyCodeList;
  private List subscriptionPaidByList;
  private int currencyId = 0;
  private List usermembershipList;
  private String currencyName;
  
  public List getUsermembershipList()
  {
    return this.usermembershipList;
  }
  
  public void setUsermembershipList(List usermembershipList)
  {
    this.usermembershipList = usermembershipList;
  }
  
  public List getSubscriptionPaidByList()
  {
    return this.subscriptionPaidByList;
  }
  
  public void setSubscriptionPaidByList(List subscriptionPaidByList)
  {
    this.subscriptionPaidByList = subscriptionPaidByList;
  }
  
  public List getCurrencyCodeList()
  {
    return this.currencyCodeList;
  }
  
  public void setCurrencyCodeList(List currencyCodeList)
  {
    this.currencyCodeList = currencyCodeList;
  }
  
  public int getCurrencyId()
  {
    return this.currencyId;
  }
  
  public void setCurrencyId(int currencyId)
  {
    this.currencyId = currencyId;
  }
  
  public String getCurrencyName()
  {
    return this.currencyName;
  }
  
  public void setCurrencyName(String currencyName)
  {
    this.currencyName = currencyName;
  }
  
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
  
  public List getMembershipTypesList()
  {
    return this.membershipTypesList;
  }
  
  public void setMembershipTypesList(List membershipTypesList)
  {
    this.membershipTypesList = membershipTypesList;
  }
  
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
  
  public Currency getCurrency()
  {
    return this.currency;
  }
  
  public void setCurrency(Currency currency)
  {
    this.currency = currency;
  }
  
  public String getSubscriptionCommenceDate()
  {
    return this.subscriptionCommenceDate;
  }
  
  public void setSubscriptionCommenceDate(String subscriptionCommenceDate)
  {
    this.subscriptionCommenceDate = subscriptionCommenceDate;
  }
  
  public String getSubscriptionRenewalDate()
  {
    return this.subscriptionRenewalDate;
  }
  
  public void setSubscriptionRenewalDate(String subscriptionRenewalDate)
  {
    this.subscriptionRenewalDate = subscriptionRenewalDate;
  }
  
  public void fromValue(UserMemberShip userMemberShip, HttpServletRequest request)
  {
    User user1 = (User)request.getSession().getAttribute("user_data");
    this.userMemberShipId = userMemberShip.getUserMemberShipId();
    this.userId = userMemberShip.getUserId();
    this.amount = userMemberShip.getAmount();
    this.subscriptionPaidBy = userMemberShip.getSubscriptionPaidBy();
    if (userMemberShip.getMembershiptype() != null)
    {
      this.membershipTypeId = userMemberShip.getMembershiptype().getMembershipTypeId();
      this.membershipTypeName = userMemberShip.getMembershiptype().getMembershipTypeName();
    }
    if (userMemberShip.getCurrency() != null)
    {
      this.currencyId = userMemberShip.getCurrency().getCurrencyId();
      this.currencyName = userMemberShip.getCurrency().getCurrencyName();
    }
    if (userMemberShip.getSubscriptionCommenceDate() != null)
    {
      String datepattern = Constant.getValue("defaultdateformat");
      if (user1 != null) {
        datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
      }
      this.subscriptionCommenceDate = DateUtil.convertDateToStringDate(userMemberShip.getSubscriptionCommenceDate(), datepattern);
    }
    if (userMemberShip.getSubscriptionRenewalDate() != null)
    {
      String datepattern = Constant.getValue("defaultdateformat");
      if (user1 != null) {
        datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
      }
      this.subscriptionRenewalDate = DateUtil.convertDateToStringDate(userMemberShip.getSubscriptionRenewalDate(), datepattern);
    }
  }
  
  public UserMemberShip toValue(UserMemberShip userMemberShip, HttpServletRequest request)
    throws Exception
  {
    User user1 = (User)request.getSession().getAttribute("user_data");
    userMemberShip.setUserId(this.userId);
    userMemberShip.setSubscriptionPaidBy(this.subscriptionPaidBy);
    userMemberShip.setAmount(this.amount);
    if (this.membershipTypeId != 0L)
    {
      MembershipType membershipType = new MembershipType();
      membershipType.setMembershipTypeId(this.membershipTypeId);
      userMemberShip.setMembershiptype(membershipType);
    }
    if (this.currencyId != 0)
    {
      Currency currency2 = new Currency();
      currency2.setCurrencyId(this.currencyId);
      userMemberShip.setCurrency(currency2);
    }
    return userMemberShip;
  }
}
