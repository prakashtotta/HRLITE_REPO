package com.form.employee;

import com.bean.employee.UserLanguages;
import com.bean.lov.Languages;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;

public class UserLanguagesForm
  extends ActionForm
{
  private long userLangId;
  public long userId;
  private String fluency;
  private String rating;
  private String comment;
  private List languagesList;
  public long languageId = 0L;
  public String languageName;
  private List languageFluencyList;
  private List competencyList;
  
  public List getCompetencyList()
  {
    return this.competencyList;
  }
  
  public void setCompetencyList(List competencyList)
  {
    this.competencyList = competencyList;
  }
  
  public long getLanguageId()
  {
    return this.languageId;
  }
  
  public void setLanguageId(long languageId)
  {
    this.languageId = languageId;
  }
  
  public List getLanguageFluencyList()
  {
    return this.languageFluencyList;
  }
  
  public void setLanguageFluencyList(List languageFluencyList)
  {
    this.languageFluencyList = languageFluencyList;
  }
  
  public String getLanguageName()
  {
    return this.languageName;
  }
  
  public void setLanguageName(String languageName)
  {
    this.languageName = languageName;
  }
  
  public List getLanguagesList()
  {
    return this.languagesList;
  }
  
  public void setLanguagesList(List languagesList)
  {
    this.languagesList = languagesList;
  }
  
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
  
  public void fromValue(UserLanguages userLanguages, HttpServletRequest request)
  {
    this.userLangId = userLanguages.getUserLangId();
    this.userId = userLanguages.getUserId();
    this.languageId = userLanguages.getLanguage().getLanguageId();
    this.fluency = userLanguages.getFluency();
    this.rating = userLanguages.getRating();
    this.comment = userLanguages.getComment();
  }
  
  public UserLanguages toValue(UserLanguages userLanguages, HttpServletRequest request)
    throws Exception
  {
    userLanguages.setUserId(this.userId);
    if (this.languageId != 0L)
    {
      Languages languages = new Languages();
      languages.setLanguageId(this.languageId);
      userLanguages.setLanguage(languages);
    }
    userLanguages.setFluency(this.fluency);
    userLanguages.setRating(this.rating);
    userLanguages.setComment(this.comment);
    

    return userLanguages;
  }
}
