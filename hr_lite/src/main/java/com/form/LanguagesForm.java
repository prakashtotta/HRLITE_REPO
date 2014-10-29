package com.form;

import com.bean.lov.Languages;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;

public class LanguagesForm
  extends ActionForm
{
  public long languageId;
  public String languageName;
  public String languageDesc;
  public String status;
  
  public long getLanguageId()
  {
    return this.languageId;
  }
  
  public void setLanguageId(long languageId)
  {
    this.languageId = languageId;
  }
  
  public String getLanguageName()
  {
    return this.languageName;
  }
  
  public void setLanguageName(String languageName)
  {
    this.languageName = languageName;
  }
  
  public String getLanguageDesc()
  {
    return this.languageDesc;
  }
  
  public void setLanguageDesc(String languageDesc)
  {
    this.languageDesc = languageDesc;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public Languages toValue(Languages languages, HttpServletRequest request)
    throws Exception
  {
    languages.setLanguageName(this.languageName);
    languages.setLanguageDesc(this.languageDesc);
    
    return languages;
  }
  
  public void fromValue(Languages languages, HttpServletRequest request)
    throws Exception
  {
    this.languageId = languages.getLanguageId();
    this.languageName = languages.getLanguageName();
    this.languageDesc = languages.getLanguageDesc();
  }
}
