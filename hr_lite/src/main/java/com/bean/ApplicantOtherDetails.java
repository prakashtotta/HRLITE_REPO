package com.bean;

import com.bean.lov.EthnicRace;
import com.bean.lov.VeteranDisability;
import java.util.Date;

public class ApplicantOtherDetails
{
  public long applicantOtherDetailsId;
  public long applicantId;
  public Date earliest_start_date;
  public String work_on_weekends;
  public String work_on_evenings;
  public String work_on_overtime;
  public String want_to_relocate;
  public String languages_spoken;
  public String work_status;
  public String felony_conviction;
  public String felony_conviction_desc;
  public String website_url;
  public String linkedin_url;
  public String facebook_url;
  public String college_name;
  public String college_GPA;
  public String resumeDetails;
  public String address;
  public String zipcode;
  private Country nationality;
  public long validationOnlyNationalityId;
  private EthnicRace ethnicRace;
  private VeteranDisability veteranDisability;
  public long validationOnlyEthnicRaceId;
  
  public long getApplicantOtherDetailsId()
  {
    return this.applicantOtherDetailsId;
  }
  
  public void setApplicantOtherDetailsId(long applicantOtherDetailsId)
  {
    this.applicantOtherDetailsId = applicantOtherDetailsId;
  }
  
  public Date getEarliest_start_date()
  {
    return this.earliest_start_date;
  }
  
  public void setEarliest_start_date(Date earliestStartDate)
  {
    this.earliest_start_date = earliestStartDate;
  }
  
  public String getWork_on_weekends()
  {
    return this.work_on_weekends;
  }
  
  public void setWork_on_weekends(String workOnWeekends)
  {
    this.work_on_weekends = workOnWeekends;
  }
  
  public String getWork_on_evenings()
  {
    return this.work_on_evenings;
  }
  
  public void setWork_on_evenings(String workOnEvenings)
  {
    this.work_on_evenings = workOnEvenings;
  }
  
  public String getWork_on_overtime()
  {
    return this.work_on_overtime;
  }
  
  public void setWork_on_overtime(String workOnOvertime)
  {
    this.work_on_overtime = workOnOvertime;
  }
  
  public String getWant_to_relocate()
  {
    return this.want_to_relocate;
  }
  
  public void setWant_to_relocate(String wantToRelocate)
  {
    this.want_to_relocate = wantToRelocate;
  }
  
  public String getLanguages_spoken()
  {
    return this.languages_spoken;
  }
  
  public void setLanguages_spoken(String languagesSpoken)
  {
    this.languages_spoken = languagesSpoken;
  }
  
  public String getWork_status()
  {
    return this.work_status;
  }
  
  public void setWork_status(String workStatus)
  {
    this.work_status = workStatus;
  }
  
  public String getFelony_conviction()
  {
    return this.felony_conviction;
  }
  
  public void setFelony_conviction(String felonyConviction)
  {
    this.felony_conviction = felonyConviction;
  }
  
  public String getFelony_conviction_desc()
  {
    return this.felony_conviction_desc;
  }
  
  public void setFelony_conviction_desc(String felonyConvictionDesc)
  {
    this.felony_conviction_desc = felonyConvictionDesc;
  }
  
  public String getWebsite_url()
  {
    return this.website_url;
  }
  
  public void setWebsite_url(String websiteUrl)
  {
    this.website_url = websiteUrl;
  }
  
  public String getLinkedin_url()
  {
    return this.linkedin_url;
  }
  
  public void setLinkedin_url(String linkedinUrl)
  {
    this.linkedin_url = linkedinUrl;
  }
  
  public String getFacebook_url()
  {
    return this.facebook_url;
  }
  
  public void setFacebook_url(String facebookUrl)
  {
    this.facebook_url = facebookUrl;
  }
  
  public String getCollege_GPA()
  {
    return this.college_GPA;
  }
  
  public void setCollege_GPA(String collegeGPA)
  {
    this.college_GPA = collegeGPA;
  }
  
  public String getCollege_name()
  {
    return this.college_name;
  }
  
  public void setCollege_name(String collegeName)
  {
    this.college_name = collegeName;
  }
  
  public long getApplicantId()
  {
    return this.applicantId;
  }
  
  public void setApplicantId(long applicantId)
  {
    this.applicantId = applicantId;
  }
  
  public String getResumeDetails()
  {
    return this.resumeDetails;
  }
  
  public void setResumeDetails(String resumeDetails)
  {
    this.resumeDetails = resumeDetails;
  }
  
  public String getAddress()
  {
    return this.address;
  }
  
  public void setAddress(String address)
  {
    this.address = address;
  }
  
  public String getZipcode()
  {
    return this.zipcode;
  }
  
  public void setZipcode(String zipcode)
  {
    this.zipcode = zipcode;
  }
  
  public Country getNationality()
  {
    return this.nationality;
  }
  
  public void setNationality(Country nationality)
  {
    this.nationality = nationality;
    if (nationality != null) {
      this.validationOnlyNationalityId = nationality.getCountryId();
    }
  }
  
  public long getValidationOnlyNationalityId()
  {
    return this.validationOnlyNationalityId;
  }
  
  public EthnicRace getEthnicRace()
  {
    return this.ethnicRace;
  }
  
  public void setEthnicRace(EthnicRace ethnicRace)
  {
    this.ethnicRace = ethnicRace;
    if (ethnicRace != null) {
      this.validationOnlyEthnicRaceId = ethnicRace.getEthnicRaceId();
    }
  }
  
  public long getValidationOnlyEthnicRaceId()
  {
    return this.validationOnlyEthnicRaceId;
  }
  
  public VeteranDisability getVeteranDisability()
  {
    return this.veteranDisability;
  }
  
  public void setVeteranDisability(VeteranDisability veteranDisability)
  {
    this.veteranDisability = veteranDisability;
  }
}
