package com.form.employee;

import com.bean.User;
import com.bean.employee.UserReportTo;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;

public class UserReportToForm
  extends ActionForm
{
  public long reportToId;
  public long userId;
  private String reportToType;
  private String reportToMethod;
  private String reportToMethodOther;
  public long reportToUserId;
  private List reportingMethodsList;
  private String reportToUserName;
  private String reportToUserNamehidden;
  
  public String getReportToUserName()
  {
    return this.reportToUserName;
  }
  
  public void setReportToUserName(String reportToUserName)
  {
    this.reportToUserName = reportToUserName;
  }
  
  public String getReportToUserNamehidden()
  {
    return this.reportToUserNamehidden;
  }
  
  public void setReportToUserNamehidden(String reportToUserNamehidden)
  {
    this.reportToUserNamehidden = reportToUserNamehidden;
  }
  
  public List getReportingMethodsList()
  {
    return this.reportingMethodsList;
  }
  
  public void setReportingMethodsList(List reportingMethodsList)
  {
    this.reportingMethodsList = reportingMethodsList;
  }
  
  public long getReportToId()
  {
    return this.reportToId;
  }
  
  public void setReportToId(long reportToId)
  {
    this.reportToId = reportToId;
  }
  
  public long getUserId()
  {
    return this.userId;
  }
  
  public void setUserId(long userId)
  {
    this.userId = userId;
  }
  
  public String getReportToType()
  {
    return this.reportToType;
  }
  
  public void setReportToType(String reportToType)
  {
    this.reportToType = reportToType;
  }
  
  public String getReportToMethod()
  {
    return this.reportToMethod;
  }
  
  public void setReportToMethod(String reportToMethod)
  {
    this.reportToMethod = reportToMethod;
  }
  
  public String getReportToMethodOther()
  {
    return this.reportToMethodOther;
  }
  
  public void setReportToMethodOther(String reportToMethodOther)
  {
    this.reportToMethodOther = reportToMethodOther;
  }
  
  public long getReportToUserId()
  {
    return this.reportToUserId;
  }
  
  public void setReportToUserId(long reportToUserId)
  {
    this.reportToUserId = reportToUserId;
  }
  
  public void fromValue(UserReportTo userReportTo, HttpServletRequest request)
  {
    User user1 = (User)request.getSession().getAttribute("user_data");
    this.reportToId = userReportTo.getReportToId();
    this.userId = userReportTo.getUserId();
    this.reportToMethod = userReportTo.getReportToMethod();
    this.reportToType = userReportTo.getReportToType();
    
    this.reportToMethodOther = userReportTo.getReportToMethodOther();
  }
  
  public UserReportTo toValue(UserReportTo userReportTo, HttpServletRequest request)
    throws Exception
  {
    userReportTo.setUserId(this.userId);
    userReportTo.setReportToMethod(this.reportToMethod);
    userReportTo.setReportToType(this.reportToType);
    userReportTo.setReportToMethodOther(this.reportToMethodOther);
    

    return userReportTo;
  }
}
