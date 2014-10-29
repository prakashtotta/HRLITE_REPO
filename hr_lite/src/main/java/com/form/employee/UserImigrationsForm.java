package com.form.employee;

import com.bean.Country;
import com.bean.User;
import com.bean.employee.Imigrations;
import com.resources.Constant;
import com.util.DateUtil;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;

public class UserImigrationsForm
  extends ActionForm
{
  public long imigrationId;
  public long userId;
  public String passportVisaType;
  public String passportNo;
  public String passportIssuePlace;
  public String passportIssueDate;
  public String passportExpiryDate;
  public String eligibleReviewDate;
  public String comment;
  private List userImigrationList;
  private List countryList;
  private String countryName;
  private long countryId = 0L;
  public String eligibleStatus;
  
  public String getEligibleStatus()
  {
    return this.eligibleStatus;
  }
  
  public void setEligibleStatus(String eligibleStatus)
  {
    this.eligibleStatus = eligibleStatus;
  }
  
  public long getCountryId()
  {
    return this.countryId;
  }
  
  public void setCountryId(long countryId)
  {
    this.countryId = countryId;
  }
  
  public String getCountryName()
  {
    return this.countryName;
  }
  
  public void setCountryName(String countryName)
  {
    this.countryName = countryName;
  }
  
  public List getCountryList()
  {
    return this.countryList;
  }
  
  public void setCountryList(List countryList)
  {
    this.countryList = countryList;
  }
  
  public List getUserImigrationList()
  {
    return this.userImigrationList;
  }
  
  public void setUserImigrationList(List userImigrationList)
  {
    this.userImigrationList = userImigrationList;
  }
  
  public long getImigrationId()
  {
    return this.imigrationId;
  }
  
  public void setImigrationId(long imigrationId)
  {
    this.imigrationId = imigrationId;
  }
  
  public long getUserId()
  {
    return this.userId;
  }
  
  public void setUserId(long userId)
  {
    this.userId = userId;
  }
  
  public String getPassportVisaType()
  {
    return this.passportVisaType;
  }
  
  public void setPassportVisaType(String passportVisaType)
  {
    this.passportVisaType = passportVisaType;
  }
  
  public String getPassportNo()
  {
    return this.passportNo;
  }
  
  public void setPassportNo(String passportNo)
  {
    this.passportNo = passportNo;
  }
  
  public String getPassportIssuePlace()
  {
    return this.passportIssuePlace;
  }
  
  public void setPassportIssuePlace(String passportIssuePlace)
  {
    this.passportIssuePlace = passportIssuePlace;
  }
  
  public String getPassportIssueDate()
  {
    return this.passportIssueDate;
  }
  
  public void setPassportIssueDate(String passportIssueDate)
  {
    this.passportIssueDate = passportIssueDate;
  }
  
  public String getPassportExpiryDate()
  {
    return this.passportExpiryDate;
  }
  
  public void setPassportExpiryDate(String passportExpiryDate)
  {
    this.passportExpiryDate = passportExpiryDate;
  }
  
  public String getEligibleReviewDate()
  {
    return this.eligibleReviewDate;
  }
  
  public void setEligibleReviewDate(String eligibleReviewDate)
  {
    this.eligibleReviewDate = eligibleReviewDate;
  }
  
  public String getComment()
  {
    return this.comment;
  }
  
  public void setComment(String comment)
  {
    this.comment = comment;
  }
  
  public void fromValue(Imigrations imigrations, HttpServletRequest request)
  {
    User user1 = (User)request.getSession().getAttribute("user_data");
    this.imigrationId = imigrations.getImigrationId();
    this.userId = imigrations.getUserId();
    this.passportVisaType = imigrations.getPassportVisaType();
    this.passportNo = imigrations.getPassportNo();
    this.eligibleStatus = imigrations.getEligibleStatus();
    this.comment = imigrations.getComment();
    if ((imigrations.getPassportIssueDate() != null) || (imigrations.getPassportExpiryDate() != null) || (imigrations.getEligibleReviewDate() != null))
    {
      String datepattern = Constant.getValue("defaultdateformat");
      if (user1 != null) {
        datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
      }
      this.passportIssueDate = DateUtil.convertDateToStringDate(imigrations.getPassportIssueDate(), datepattern);
      this.passportExpiryDate = DateUtil.convertDateToStringDate(imigrations.getPassportExpiryDate(), datepattern);
      this.eligibleReviewDate = DateUtil.convertDateToStringDate(imigrations.getEligibleReviewDate(), datepattern);
    }
    if (imigrations.getIssueCountry() != null)
    {
      this.countryId = imigrations.getIssueCountry().getCountryId();
      this.countryName = imigrations.getIssueCountry().getCountryName();
    }
  }
  
  public Imigrations toValue(Imigrations imigrations, HttpServletRequest request)
    throws Exception
  {
    imigrations.setUserId(this.userId);
    imigrations.setPassportVisaType(this.passportVisaType);
    imigrations.setPassportNo(this.passportNo);
    imigrations.setEligibleStatus(this.eligibleStatus);
    imigrations.setComment(this.comment);
    if (this.countryId != 0L)
    {
      Country country = new Country();
      country.setCountryId(this.countryId);
      imigrations.setIssueCountry(country);
    }
    return imigrations;
  }
}
