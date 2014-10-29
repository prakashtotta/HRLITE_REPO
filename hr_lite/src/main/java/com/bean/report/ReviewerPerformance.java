package com.bean.report;

import java.util.Date;

public class ReviewerPerformance
{
  private long reviewerPerfId;
  private long reviewerId;
  private int yearValue;
  private int totalApplicants;
  private int totalCleared;
  private int totalClearedNextRound;
  private int totalOffered;
  private int totalOnBoard;
  private String applicantIdsOffered;
  private String applicantIdsOnBorad;
  private String status;
  private String reviewerType;
  private Date createdDate;
  private Date updatedDate;
  
  public long getReviewerPerfId()
  {
    return this.reviewerPerfId;
  }
  
  public void setReviewerPerfId(long reviewerPerfId)
  {
    this.reviewerPerfId = reviewerPerfId;
  }
  
  public long getReviewerId()
  {
    return this.reviewerId;
  }
  
  public void setReviewerId(long reviewerId)
  {
    this.reviewerId = reviewerId;
  }
  
  public int getTotalApplicants()
  {
    return this.totalApplicants;
  }
  
  public void setTotalApplicants(int totalApplicants)
  {
    this.totalApplicants = totalApplicants;
  }
  
  public int getTotalCleared()
  {
    return this.totalCleared;
  }
  
  public void setTotalCleared(int totalCleared)
  {
    this.totalCleared = totalCleared;
  }
  
  public int getTotalClearedNextRound()
  {
    return this.totalClearedNextRound;
  }
  
  public void setTotalClearedNextRound(int totalClearedNextRound)
  {
    this.totalClearedNextRound = totalClearedNextRound;
  }
  
  public int getTotalOffered()
  {
    return this.totalOffered;
  }
  
  public void setTotalOffered(int totalOffered)
  {
    this.totalOffered = totalOffered;
  }
  
  public int getTotalOnBoard()
  {
    return this.totalOnBoard;
  }
  
  public void setTotalOnBoard(int totalOnBoard)
  {
    this.totalOnBoard = totalOnBoard;
  }
  
  public String getApplicantIdsOffered()
  {
    return this.applicantIdsOffered;
  }
  
  public void setApplicantIdsOffered(String applicantIdsOffered)
  {
    this.applicantIdsOffered = applicantIdsOffered;
  }
  
  public String getApplicantIdsOnBorad()
  {
    return this.applicantIdsOnBorad;
  }
  
  public void setApplicantIdsOnBorad(String applicantIdsOnBorad)
  {
    this.applicantIdsOnBorad = applicantIdsOnBorad;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public String getReviewerType()
  {
    return this.reviewerType;
  }
  
  public void setReviewerType(String reviewerType)
  {
    this.reviewerType = reviewerType;
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
  
  public int getYearValue()
  {
    return this.yearValue;
  }
  
  public void setYearValue(int yearValue)
  {
    this.yearValue = yearValue;
  }
}
