package com.form;

import com.bean.Country;
import com.bean.Timezone;
import com.bean.UserRegData;
import com.resources.Constant;
import com.util.EncryptDecrypt;
import com.util.StringUtils;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class UserRegForm
  extends ActionForm
{
  public long userRegId;
  public String firstName;
  private String middleName;
  public String lastName;
  public String emailId;
  public String password;
  public String cpassword;
  public String otherEmailId;
  private long localeId;
  private long timezoneId;
  public String orgName;
  public String mobileNo;
  public String phoneHome;
  public String phoneOffice;
  private long nationalityId;
  public String city;
  private String createdBy;
  private Date createdDate;
  private Date expireDate;
  private String packageName;
  public String status;
  public String ipaddress;
  private List localeList;
  private List timezoneList;
  private List countryList;
  public String packagetaken;
  public String subdomain;
  public String companyInfo;
  private FormFile logoPhoto;
  public long logoPhotoId;
  private List userRegDataList;
  public String months;
  public String packages;
  private List packagesList;
  
  public List getPackagesList()
  {
    return this.packagesList;
  }
  
  public void setPackagesList(List packagesList)
  {
    this.packagesList = packagesList;
  }
  
  public String getMonths()
  {
    return this.months;
  }
  
  public void setMonths(String months)
  {
    this.months = months;
  }
  
  public String getPackages()
  {
    return this.packages;
  }
  
  public void setPackages(String packages)
  {
    this.packages = packages;
  }
  
  public List getUserRegDataList()
  {
    return this.userRegDataList;
  }
  
  public void setUserRegDataList(List userRegDataList)
  {
    this.userRegDataList = userRegDataList;
  }
  
  public long getUserRegId()
  {
    return this.userRegId;
  }
  
  public void setUserRegId(long userRegId)
  {
    this.userRegId = userRegId;
  }
  
  public String getFirstName()
  {
    return this.firstName;
  }
  
  public void setFirstName(String firstName)
  {
    this.firstName = firstName;
  }
  
  public String getMiddleName()
  {
    return this.middleName;
  }
  
  public void setMiddleName(String middleName)
  {
    this.middleName = middleName;
  }
  
  public String getLastName()
  {
    return this.lastName;
  }
  
  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }
  
  public String getEmailId()
  {
    return this.emailId;
  }
  
  public void setEmailId(String emailId)
  {
    this.emailId = emailId;
  }
  
  public String getPassword()
  {
    return this.password;
  }
  
  public void setPassword(String password)
  {
    this.password = password;
  }
  
  public String getOtherEmailId()
  {
    return this.otherEmailId;
  }
  
  public void setOtherEmailId(String otherEmailId)
  {
    this.otherEmailId = otherEmailId;
  }
  
  public long getLocaleId()
  {
    return this.localeId;
  }
  
  public void setLocaleId(long localeId)
  {
    this.localeId = localeId;
  }
  
  public long getTimezoneId()
  {
    return this.timezoneId;
  }
  
  public void setTimezoneId(long timezoneId)
  {
    this.timezoneId = timezoneId;
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
  
  public long getNationalityId()
  {
    return this.nationalityId;
  }
  
  public void setNationalityId(long nationalityId)
  {
    this.nationalityId = nationalityId;
  }
  
  public String getCity()
  {
    return this.city;
  }
  
  public void setCity(String city)
  {
    this.city = city;
  }
  
  public String getCreatedBy()
  {
    return this.createdBy;
  }
  
  public void setCreatedBy(String createdBy)
  {
    this.createdBy = createdBy;
  }
  
  public Date getCreatedDate()
  {
    return this.createdDate;
  }
  
  public void setCreatedDate(Date createdDate)
  {
    this.createdDate = createdDate;
  }
  
  public Date getExpireDate()
  {
    return this.expireDate;
  }
  
  public void setExpireDate(Date expireDate)
  {
    this.expireDate = expireDate;
  }
  
  public String getPackageName()
  {
    return this.packageName;
  }
  
  public void setPackageName(String packageName)
  {
    this.packageName = packageName;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public List getLocaleList()
  {
    return this.localeList;
  }
  
  public void setLocaleList(List localeList)
  {
    this.localeList = localeList;
  }
  
  public List getTimezoneList()
  {
    return this.timezoneList;
  }
  
  public void setTimezoneList(List timezoneList)
  {
    this.timezoneList = timezoneList;
  }
  
  public List getCountryList()
  {
    return this.countryList;
  }
  
  public void setCountryList(List countryList)
  {
    this.countryList = countryList;
  }
  
  public String getCpassword()
  {
    return this.cpassword;
  }
  
  public void setCpassword(String cpassword)
  {
    this.cpassword = cpassword;
  }
  
  public UserRegData toValue(UserRegData userreg, HttpServletRequest request)
    throws Exception
  {
    userreg.setFirstName(this.firstName);
    userreg.setMiddleName(this.middleName);
    userreg.setLastName(this.lastName);
    userreg.setEmailId(this.emailId);
    userreg.setPhoneHome(this.phoneHome);
    userreg.setPhoneOffice(this.phoneOffice);
    userreg.setPassword(EncryptDecrypt.encrypt(this.password));
    userreg.setCreatedBy(this.emailId);
    userreg.setCreatedDate(new Date());
    
    userreg.setOrgName(this.orgName);
    userreg.setIpaddress(this.ipaddress);
    


    Timezone tz = new Timezone();
    tz.setTimezoneId(this.timezoneId);
    userreg.setTimezone(tz);
    

    userreg.setPackagetaken(EncryptDecrypt.decrypt(this.packagetaken));
    if ((!StringUtils.isNullOrEmpty(userreg.getPackagetaken())) && (userreg.getPackagetaken().equals("FREE")))
    {
      Date dt = new Date();
      dt.setDate(dt.getDate() + 15);
      if ((StringUtils.isNullOrEmpty(Constant.getValue("is.open.source"))) || (!Constant.getValue("is.open.source").equals("yes"))) {
        userreg.setExpireDate(dt);
      }
    }
    userreg.setCity(this.city);
    String subd = "";
    if (!StringUtils.isNullOrEmpty(this.subdomain))
    {
      subd = this.subdomain;
      subd = subd.replace(".", "");
      subd = subd.replace(" ", "");
    }
    userreg.setSubdomain(subd);
    if (this.nationalityId != 0L)
    {
      Country nationality = new Country();
      nationality.setCountryId(this.nationalityId);
      userreg.setNationality(nationality);
    }
    return userreg;
  }
  
  public String getOrgName()
  {
    return this.orgName;
  }
  
  public void setOrgName(String orgName)
  {
    this.orgName = orgName;
  }
  
  public String getIpaddress()
  {
    return this.ipaddress;
  }
  
  public void setIpaddress(String ipaddress)
  {
    this.ipaddress = ipaddress;
  }
  
  public String getPackagetaken()
  {
    return this.packagetaken;
  }
  
  public void setPackagetaken(String packagetaken)
  {
    this.packagetaken = packagetaken;
  }
  
  public String getSubdomain()
  {
    return this.subdomain;
  }
  
  public void setSubdomain(String subdomain)
  {
    this.subdomain = subdomain;
  }
  
  public String getCompanyInfo()
  {
    return this.companyInfo;
  }
  
  public void setCompanyInfo(String companyInfo)
  {
    this.companyInfo = companyInfo;
  }
  
  public long getLogoPhotoId()
  {
    return this.logoPhotoId;
  }
  
  public void setLogoPhotoId(long logoPhotoId)
  {
    this.logoPhotoId = logoPhotoId;
  }
  
  public FormFile getLogoPhoto()
  {
    return this.logoPhoto;
  }
  
  public void setLogoPhoto(FormFile logoPhoto)
  {
    this.logoPhoto = logoPhoto;
  }
}
