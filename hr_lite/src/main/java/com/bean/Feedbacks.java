package com.bean;

import java.util.Date;

public class Feedbacks
{
  public long feedbackId;
  private String emailId;
  private String comment;
  private Date createdDate;
  
  public long getFeedbackId()
  {
    return this.feedbackId;
  }
  
  public void setFeedbackId(long feedbackId)
  {
    this.feedbackId = feedbackId;
  }
  
  public String getEmailId()
  {
    return this.emailId;
  }
  
  public void setEmailId(String emailId)
  {
    this.emailId = emailId;
  }
  
  public String getComment()
  {
    return this.comment;
  }
  
  public void setComment(String comment)
  {
    this.comment = comment;
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
