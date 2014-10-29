package network.bean;

import java.util.Date;
import java.util.List;

public class FaceBookUser
{
  public long userId;
  public String firstName;
  public String lastName;
  public String fullName;
  public String emailId;
  public String mobileNo;
  private String locale;
  private String timezone;
  public String fuserName;
  public String facebookid;
  public String gender;
  public String city;
  public String state;
  public String country;
  public String zip;
  public String bio;
  public String status;
  public String link;
  public String topLine;
  public Date lastFriendupdatedDate;
  public String sessionKey;
  private int indexdone = 0;
  public Date updatedDate;
  private List<FbUserEmployer> employerList;
  private List<FbUserSchools> educationList;
  private List<FbUserSkills> skillsList;
  private List<FbUserSpecialities> specialitiesList;
  private List<FbUserAchivements> achivementList;
  private List<FbUserCertifications> certList;
  
  public long getUserId()
  {
    return this.userId;
  }
  
  public void setUserId(long userId)
  {
    this.userId = userId;
  }
  
  public String getFirstName()
  {
    return this.firstName;
  }
  
  public void setFirstName(String firstName)
  {
    this.firstName = firstName;
  }
  
  public String getLastName()
  {
    return this.lastName;
  }
  
  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }
  
  public String getFullName()
  {
    return this.fullName;
  }
  
  public void setFullName(String fullName)
  {
    this.fullName = fullName;
  }
  
  public String getEmailId()
  {
    return this.emailId;
  }
  
  public void setEmailId(String emailId)
  {
    this.emailId = emailId;
  }
  
  public String getMobileNo()
  {
    return this.mobileNo;
  }
  
  public void setMobileNo(String mobileNo)
  {
    this.mobileNo = mobileNo;
  }
  
  public String getFuserName()
  {
    return this.fuserName;
  }
  
  public void setFuserName(String fuserName)
  {
    this.fuserName = fuserName;
  }
  
  public String getFacebookid()
  {
    return this.facebookid;
  }
  
  public void setFacebookid(String facebookid)
  {
    this.facebookid = facebookid;
  }
  
  public String getGender()
  {
    return this.gender;
  }
  
  public void setGender(String gender)
  {
    this.gender = gender;
  }
  
  public String getCity()
  {
    return this.city;
  }
  
  public void setCity(String city)
  {
    this.city = city;
  }
  
  public String getState()
  {
    return this.state;
  }
  
  public void setState(String state)
  {
    this.state = state;
  }
  
  public String getCountry()
  {
    return this.country;
  }
  
  public void setCountry(String country)
  {
    this.country = country;
  }
  
  public String getBio()
  {
    return this.bio;
  }
  
  public void setBio(String bio)
  {
    this.bio = bio;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public String getLocale()
  {
    return this.locale;
  }
  
  public void setLocale(String locale)
  {
    this.locale = locale;
  }
  
  public String getTimezone()
  {
    return this.timezone;
  }
  
  public void setTimezone(String timezone)
  {
    this.timezone = timezone;
  }
  
  public String getLink()
  {
    return this.link;
  }
  
  public void setLink(String link)
  {
    this.link = link;
  }
  
  public String getZip()
  {
    return this.zip;
  }
  
  public void setZip(String zip)
  {
    this.zip = zip;
  }
  
  public Date getLastFriendupdatedDate()
  {
    return this.lastFriendupdatedDate;
  }
  
  public void setLastFriendupdatedDate(Date lastFriendupdatedDate)
  {
    this.lastFriendupdatedDate = lastFriendupdatedDate;
  }
  
  public String getSessionKey()
  {
    return this.sessionKey;
  }
  
  public void setSessionKey(String sessionKey)
  {
    this.sessionKey = sessionKey;
  }
  
  public List<FbUserEmployer> getEmployerList()
  {
    return this.employerList;
  }
  
  public void setEmployerList(List<FbUserEmployer> employerList)
  {
    this.employerList = employerList;
  }
  
  public List<FbUserSchools> getEducationList()
  {
    return this.educationList;
  }
  
  public void setEducationList(List<FbUserSchools> educationList)
  {
    this.educationList = educationList;
  }
  
  public String getTopLine()
  {
    return this.topLine;
  }
  
  public void setTopLine(String topLine)
  {
    this.topLine = topLine;
  }
  
  public int getIndexdone()
  {
    return this.indexdone;
  }
  
  public void setIndexdone(int indexdone)
  {
    this.indexdone = indexdone;
  }
  
  public List<FbUserSkills> getSkillsList()
  {
    return this.skillsList;
  }
  
  public void setSkillsList(List<FbUserSkills> skillsList)
  {
    this.skillsList = skillsList;
  }
  
  public List<FbUserSpecialities> getSpecialitiesList()
  {
    return this.specialitiesList;
  }
  
  public void setSpecialitiesList(List<FbUserSpecialities> specialitiesList)
  {
    this.specialitiesList = specialitiesList;
  }
  
  public List<FbUserAchivements> getAchivementList()
  {
    return this.achivementList;
  }
  
  public void setAchivementList(List<FbUserAchivements> achivementList)
  {
    this.achivementList = achivementList;
  }
  
  public List<FbUserCertifications> getCertList()
  {
    return this.certList;
  }
  
  public void setCertList(List<FbUserCertifications> certList)
  {
    this.certList = certList;
  }
  
  public Date getUpdatedDate()
  {
    return this.updatedDate;
  }
  
  public void setUpdatedDate(Date updatedDate)
  {
    this.updatedDate = updatedDate;
  }
}
