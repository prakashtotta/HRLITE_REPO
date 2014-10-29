package com.bean.employee;

import com.bean.lov.Languages;

public class UserLanguages
{
  private long userLangId;
  public long userId;
  private Languages language;
  private String fluency;
  private String rating;
  private String comment;
  
  public long getUserLangId()
  {
    return this.userLangId;
  }
  
  public void setUserLangId(long userLangId)
  {
    this.userLangId = userLangId;
  }
  
  public long getUserId()
  {
    return this.userId;
  }
  
  public void setUserId(long userId)
  {
    this.userId = userId;
  }
  
  public Languages getLanguage()
  {
    return this.language;
  }
  
  public void setLanguage(Languages language)
  {
    this.language = language;
  }
  
  public String getFluency()
  {
    return this.fluency;
  }
  
  public void setFluency(String fluency)
  {
    this.fluency = fluency;
  }
  
  public String getComment()
  {
    return this.comment;
  }
  
  public void setComment(String comment)
  {
    this.comment = comment;
  }
  
  public String getRating()
  {
    return this.rating;
  }
  
  public void setRating(String rating)
  {
    this.rating = rating;
  }
}
