package com.bean;

import java.util.Date;

public class QuestionGroupApplicants
{
  public long qnsGrpAppId;
  public QuestionGroups questiongroup;
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
  public String headingcomments;
  
  public long getQnsGrpAppId()
  {
    return this.qnsGrpAppId;
  }
  
  public void setQnsGrpAppId(long qnsGrpAppId)
  {
    this.qnsGrpAppId = qnsGrpAppId;
  }
  
  public QuestionGroups getQuestiongroup()
  {
    return this.questiongroup;
  }
  
  public void setQuestiongroup(QuestionGroups questiongroup)
  {
    this.questiongroup = questiongroup;
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
  
  public double getPercentage()
  {
    return this.percentage;
  }
  
  public void setPercentage(double percentage)
  {
    this.percentage = percentage;
  }
  
  public double getPassPercentage()
  {
    return this.passPercentage;
  }
  
  public void setPassPercentage(double passPercentage)
  {
    this.passPercentage = passPercentage;
  }
  
  public String getResult()
  {
    return this.result;
  }
  
  public void setResult(String result)
  {
    this.result = result;
  }
  
  public Date getStartTime()
  {
    return this.startTime;
  }
  
  public void setStartTime(Date startTime)
  {
    this.startTime = startTime;
  }
  
  public Date getEndTime()
  {
    return this.endTime;
  }
  
  public void setEndTime(Date endTime)
  {
    this.endTime = endTime;
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
  
  public String getHeadingcomments()
  {
    return this.headingcomments;
  }
  
  public void setHeadingcomments(String headingcomments)
  {
    this.headingcomments = headingcomments;
  }
}
