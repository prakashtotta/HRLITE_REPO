package com.bean;

import java.util.Date;

public class ApplicantScoring
{
  public long appscoreId;
  public long requistionId;
  public long applicantId;
  public String keyword;
  public double score;
  public Date createdDate;
  
  public long getAppscoreId()
  {
    return this.appscoreId;
  }
  
  public void setAppscoreId(long appscoreId)
  {
    this.appscoreId = appscoreId;
  }
  
  public long getRequistionId()
  {
    return this.requistionId;
  }
  
  public void setRequistionId(long requistionId)
  {
    this.requistionId = requistionId;
  }
  
  public long getApplicantId()
  {
    return this.applicantId;
  }
  
  public void setApplicantId(long applicantId)
  {
    this.applicantId = applicantId;
  }
  
  public String getKeyword()
  {
    return this.keyword;
  }
  
  public void setKeyword(String keyword)
  {
    this.keyword = keyword;
  }
  
  public double getScore()
  {
    return this.score;
  }
  
  public void setScore(double score)
  {
    this.score = score;
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
