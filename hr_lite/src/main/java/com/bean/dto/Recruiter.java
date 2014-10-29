package com.bean.dto;

public class Recruiter
{
  public long recruiterId;
  public String recruiterName;
  public String isgrouprecruiter = "N";
  
  public long getRecruiterId()
  {
    return this.recruiterId;
  }
  
  public void setRecruiterId(long recruiterId)
  {
    this.recruiterId = recruiterId;
  }
  
  public String getRecruiterName()
  {
    return this.recruiterName;
  }
  
  public void setRecruiterName(String recruiterName)
  {
    this.recruiterName = recruiterName;
  }
  
  public String getIsgrouprecruiter()
  {
    return this.isgrouprecruiter;
  }
  
  public void setIsgrouprecruiter(String isgrouprecruiter)
  {
    this.isgrouprecruiter = isgrouprecruiter;
  }
}
