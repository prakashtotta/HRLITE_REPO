package network.form;

import java.util.Date;
import java.util.List;
import org.apache.struts.action.ActionForm;

public class FbJobsForm
  extends ActionForm
{
  private long jobId;
  private String jobTitle;
  private String headline;
  private String companyName;
  private String city;
  private String postalcode;
  private String country;
  private String state;
  private String description;
  private String jobcategory;
  private String experience;
  private String tenure;
  private String perks;
  private String applyauto;
  private String appyurl;
  private String appyemail;
  private String veterans;
  private String referencecode;
  private long createdBy;
  private Date createdDate;
  private long countryId;
  private long stateId;
  private List countryList;
  private List stateList;
  private List jobCategoryList;
  private long jobCategoryId;
  private String expName;
  private List fbexpList;
  private String tenureName;
  private List fbtenureList;
  private List jobsList;
  private List applicantList;
  
  public List getApplicantList()
  {
    return this.applicantList;
  }
  
  public void setApplicantList(List applicantList)
  {
    this.applicantList = applicantList;
  }
  
  public List getJobsList()
  {
    return this.jobsList;
  }
  
  public void setJobsList(List jobsList)
  {
    this.jobsList = jobsList;
  }
  
  public String getExpName()
  {
    return this.expName;
  }
  
  public void setExpName(String expName)
  {
    this.expName = expName;
  }
  
  public List getFbexpList()
  {
    return this.fbexpList;
  }
  
  public void setFbexpList(List fbexpList)
  {
    this.fbexpList = fbexpList;
  }
  
  public String getTenureName()
  {
    return this.tenureName;
  }
  
  public void setTenureName(String tenureName)
  {
    this.tenureName = tenureName;
  }
  
  public List getFbtenureList()
  {
    return this.fbtenureList;
  }
  
  public void setFbtenureList(List fbtenureList)
  {
    this.fbtenureList = fbtenureList;
  }
  
  public List getJobCategoryList()
  {
    return this.jobCategoryList;
  }
  
  public void setJobCategoryList(List jobCategoryList)
  {
    this.jobCategoryList = jobCategoryList;
  }
  
  public long getJobCategoryId()
  {
    return this.jobCategoryId;
  }
  
  public void setJobCategoryId(long jobCategoryId)
  {
    this.jobCategoryId = jobCategoryId;
  }
  
  public long getCountryId()
  {
    return this.countryId;
  }
  
  public void setCountryId(long countryId)
  {
    this.countryId = countryId;
  }
  
  public long getStateId()
  {
    return this.stateId;
  }
  
  public void setStateId(long stateId)
  {
    this.stateId = stateId;
  }
  
  public List getCountryList()
  {
    return this.countryList;
  }
  
  public void setCountryList(List countryList)
  {
    this.countryList = countryList;
  }
  
  public List getStateList()
  {
    return this.stateList;
  }
  
  public void setStateList(List stateList)
  {
    this.stateList = stateList;
  }
  
  public long getJobId()
  {
    return this.jobId;
  }
  
  public void setJobId(long jobId)
  {
    this.jobId = jobId;
  }
  
  public String getJobTitle()
  {
    return this.jobTitle;
  }
  
  public void setJobTitle(String jobTitle)
  {
    this.jobTitle = jobTitle;
  }
  
  public String getHeadline()
  {
    return this.headline;
  }
  
  public void setHeadline(String headline)
  {
    this.headline = headline;
  }
  
  public String getCompanyName()
  {
    return this.companyName;
  }
  
  public void setCompanyName(String companyName)
  {
    this.companyName = companyName;
  }
  
  public String getCity()
  {
    return this.city;
  }
  
  public void setCity(String city)
  {
    this.city = city;
  }
  
  public String getPostalcode()
  {
    return this.postalcode;
  }
  
  public void setPostalcode(String postalcode)
  {
    this.postalcode = postalcode;
  }
  
  public String getCountry()
  {
    return this.country;
  }
  
  public void setCountry(String country)
  {
    this.country = country;
  }
  
  public String getState()
  {
    return this.state;
  }
  
  public void setState(String state)
  {
    this.state = state;
  }
  
  public String getDescription()
  {
    return this.description;
  }
  
  public void setDescription(String description)
  {
    this.description = description;
  }
  
  public String getJobcategory()
  {
    return this.jobcategory;
  }
  
  public void setJobcategory(String jobcategory)
  {
    this.jobcategory = jobcategory;
  }
  
  public String getExperience()
  {
    return this.experience;
  }
  
  public void setExperience(String experience)
  {
    this.experience = experience;
  }
  
  public String getTenure()
  {
    return this.tenure;
  }
  
  public void setTenure(String tenure)
  {
    this.tenure = tenure;
  }
  
  public String getPerks()
  {
    return this.perks;
  }
  
  public void setPerks(String perks)
  {
    this.perks = perks;
  }
  
  public String getApplyauto()
  {
    return this.applyauto;
  }
  
  public void setApplyauto(String applyauto)
  {
    this.applyauto = applyauto;
  }
  
  public String getAppyurl()
  {
    return this.appyurl;
  }
  
  public void setAppyurl(String appyurl)
  {
    this.appyurl = appyurl;
  }
  
  public String getAppyemail()
  {
    return this.appyemail;
  }
  
  public void setAppyemail(String appyemail)
  {
    this.appyemail = appyemail;
  }
  
  public String getVeterans()
  {
    return this.veterans;
  }
  
  public void setVeterans(String veterans)
  {
    this.veterans = veterans;
  }
  
  public String getReferencecode()
  {
    return this.referencecode;
  }
  
  public void setReferencecode(String referencecode)
  {
    this.referencecode = referencecode;
  }
  
  public long getCreatedBy()
  {
    return this.createdBy;
  }
  
  public void setCreatedBy(long createdBy)
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
}
