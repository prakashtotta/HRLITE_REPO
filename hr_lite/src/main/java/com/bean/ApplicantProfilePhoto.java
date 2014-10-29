package com.bean;

import java.sql.Blob;
import java.util.Date;

public class ApplicantProfilePhoto
{
  private long profilePhotoId;
  private Blob profilePhoto;
  private String createdBy;
  private Date createdDate;
  private Date updatedDate;
  private String updatedBy;
  
  public long getProfilePhotoId()
  {
    return this.profilePhotoId;
  }
  
  public void setProfilePhotoId(long profilePhotoId)
  {
    this.profilePhotoId = profilePhotoId;
  }
  
  public Blob getProfilePhoto()
  {
    return this.profilePhoto;
  }
  
  public void setProfilePhoto(Blob profilePhoto)
  {
    this.profilePhoto = profilePhoto;
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
  
  public Date getUpdatedDate()
  {
    return this.updatedDate;
  }
  
  public void setUpdatedDate(Date updatedDate)
  {
    this.updatedDate = updatedDate;
  }
  
  public String getUpdatedBy()
  {
    return this.updatedBy;
  }
  
  public void setUpdatedBy(String updatedBy)
  {
    this.updatedBy = updatedBy;
  }
}
