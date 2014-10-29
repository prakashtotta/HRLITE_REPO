package com.form.employee;

import com.bean.User;
import com.bean.employee.UserLicenses;
import com.bean.lov.LicenseType;
import com.resources.Constant;
import com.util.DateUtil;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;

public class UserLicensesForm
  extends ActionForm
{
  private long userLicenseId;
  public long userId;
  private LicenseType licensetype;
  private String licenseNumber;
  private String licenseIssueAuthority;
  private String issueDate;
  private String expireDate;
  private String comment;
  private List licenseTypeList;
  public long licenseTypeId = 0L;
  private String licenseTypeName;
  
  public long getLicenseTypeId()
  {
    return this.licenseTypeId;
  }
  
  public void setLicenseTypeId(long licenseTypeId)
  {
    this.licenseTypeId = licenseTypeId;
  }
  
  public String getLicenseTypeName()
  {
    return this.licenseTypeName;
  }
  
  public void setLicenseTypeName(String licenseTypeName)
  {
    this.licenseTypeName = licenseTypeName;
  }
  
  public List getLicenseTypeList()
  {
    return this.licenseTypeList;
  }
  
  public void setLicenseTypeList(List licenseTypeList)
  {
    this.licenseTypeList = licenseTypeList;
  }
  
  public long getUserLicenseId()
  {
    return this.userLicenseId;
  }
  
  public void setUserLicenseId(long userLicenseId)
  {
    this.userLicenseId = userLicenseId;
  }
  
  public long getUserId()
  {
    return this.userId;
  }
  
  public void setUserId(long userId)
  {
    this.userId = userId;
  }
  
  public LicenseType getLicensetype()
  {
    return this.licensetype;
  }
  
  public void setLicensetype(LicenseType licensetype)
  {
    this.licensetype = licensetype;
  }
  
  public String getLicenseNumber()
  {
    return this.licenseNumber;
  }
  
  public void setLicenseNumber(String licenseNumber)
  {
    this.licenseNumber = licenseNumber;
  }
  
  public String getLicenseIssueAuthority()
  {
    return this.licenseIssueAuthority;
  }
  
  public void setLicenseIssueAuthority(String licenseIssueAuthority)
  {
    this.licenseIssueAuthority = licenseIssueAuthority;
  }
  
  public String getIssueDate()
  {
    return this.issueDate;
  }
  
  public void setIssueDate(String issueDate)
  {
    this.issueDate = issueDate;
  }
  
  public String getExpireDate()
  {
    return this.expireDate;
  }
  
  public void setExpireDate(String expireDate)
  {
    this.expireDate = expireDate;
  }
  
  public String getComment()
  {
    return this.comment;
  }
  
  public void setComment(String comment)
  {
    this.comment = comment;
  }
  
  public void fromValue(UserLicenses userLicenses, HttpServletRequest request)
  {
    User user1 = (User)request.getSession().getAttribute("user_data");
    this.userLicenseId = userLicenses.getUserLicenseId();
    this.userId = userLicenses.getUserId();
    this.licenseTypeId = userLicenses.getLicensetype().getLicenseTypeId();
    this.licenseTypeName = userLicenses.getLicensetype().getLicenseTypeName();
    this.licenseNumber = userLicenses.getLicenseNumber();
    this.licenseIssueAuthority = userLicenses.getLicenseIssueAuthority();
    if ((userLicenses.getIssueDate() != null) || (userLicenses.getExpireDate() != null))
    {
      String datepattern = Constant.getValue("defaultdateformat");
      if (user1 != null) {
        datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
      }
      this.issueDate = DateUtil.convertDateToStringDate(userLicenses.getIssueDate(), datepattern);
      this.expireDate = DateUtil.convertDateToStringDate(userLicenses.getExpireDate(), datepattern);
    }
    this.comment = userLicenses.getComment();
  }
  
  public UserLicenses toValue(UserLicenses userLicenses, HttpServletRequest request)
    throws Exception
  {
    userLicenses.setUserId(this.userId);
    if (this.licenseTypeId != 0L)
    {
      LicenseType licenseType = new LicenseType();
      licenseType.setLicenseTypeId(this.licenseTypeId);
      userLicenses.setLicensetype(licenseType);
    }
    userLicenses.setLicenseNumber(this.licenseNumber);
    userLicenses.setLicenseIssueAuthority(this.licenseIssueAuthority);
    userLicenses.setComment(this.comment);
    return userLicenses;
  }
}
