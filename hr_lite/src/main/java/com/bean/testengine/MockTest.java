package com.bean.testengine;

import java.util.Date;

public class MockTest
{
  public long testId;
  public MockQuestionSet cat;
  public long applicantId;
  public String uuid;
  public String applicantName;
  public int correctNo;
  public int totalQns;
  public double percentage;
  public double passPercentage;
  public String result;
  public Date startTime;
  public Date endTime;
  public String createdBy;
  public Date createdDate;
  
  public long getTestId()
  {
    return this.testId;
  }
  
  public void setTestId(long testId)
  {
    this.testId = testId;
  }
  
  public long getApplicantId()
  {
    return this.applicantId;
  }
  
  public void setApplicantId(long applicantId)
  {
    this.applicantId = applicantId;
  }
  
  public String getUuid()
  {
    return this.uuid;
  }
  
  public void setUuid(String uuid)
  {
    this.uuid = uuid;
  }
  
  public Date getStartTime()
  {
    return this.startTime;
  }
  
  public void setStartTime(Date startTime)
  {
    this.startTime = startTime;
  }
  
  public String getApplicantName()
  {
    return this.applicantName;
  }
  
  public void setApplicantName(String applicantName)
  {
    this.applicantName = applicantName;
  }
  
  public int getCorrectNo()
  {
    return this.correctNo;
  }
  
  public void setCorrectNo(int correctNo)
  {
    this.correctNo = correctNo;
  }
  
  public int getTotalQns()
  {
    return this.totalQns;
  }
  
  public void setTotalQns(int totalQns)
  {
    this.totalQns = totalQns;
  }
  
  public String getResult()
  {
    return this.result;
  }
  
  public void setResult(String result)
  {
    this.result = result;
  }
  
  public double getPercentage()
  {
    return this.percentage;
  }
  
  public void setPercentage(double percentage)
  {
    this.percentage = percentage;
  }
  
  public Date getEndTime()
  {
    return this.endTime;
  }
  
  public void setEndTime(Date endTime)
  {
    this.endTime = endTime;
  }
  
  public MockQuestionSet getCat()
  {
    return this.cat;
  }
  
  public void setCat(MockQuestionSet cat)
  {
    this.cat = cat;
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
  
  public double getPassPercentage()
  {
    return this.passPercentage;
  }
  
  public void setPassPercentage(double passPercentage)
  {
    this.passPercentage = passPercentage;
  }
}
