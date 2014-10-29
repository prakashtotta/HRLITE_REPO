package com.bean;

public class SearchApplicantQuestions
{
  public long searchappqnsId;
  public long searchappid;
  public Questions question;
  public String qnstype;
  public String filtercri;
  public String answerOption;
  public String filterValue1;
  public String filterValue2;
  public String searchuuid;
  public String uuid;
  
  public long getSearchappqnsId()
  {
    return this.searchappqnsId;
  }
  
  public void setSearchappqnsId(long searchappqnsId)
  {
    this.searchappqnsId = searchappqnsId;
  }
  
  public String getFiltercri()
  {
    return this.filtercri;
  }
  
  public void setFiltercri(String filtercri)
  {
    this.filtercri = filtercri;
  }
  
  public String getAnswerOption()
  {
    return this.answerOption;
  }
  
  public void setAnswerOption(String answerOption)
  {
    this.answerOption = answerOption;
  }
  
  public String getFilterValue1()
  {
    return this.filterValue1;
  }
  
  public void setFilterValue1(String filterValue1)
  {
    this.filterValue1 = filterValue1;
  }
  
  public String getFilterValue2()
  {
    return this.filterValue2;
  }
  
  public void setFilterValue2(String filterValue2)
  {
    this.filterValue2 = filterValue2;
  }
  
  public String getSearchuuid()
  {
    return this.searchuuid;
  }
  
  public void setSearchuuid(String searchuuid)
  {
    this.searchuuid = searchuuid;
  }
  
  public String getUuid()
  {
    return this.uuid;
  }
  
  public void setUuid(String uuid)
  {
    this.uuid = uuid;
  }
  
  public Questions getQuestion()
  {
    return this.question;
  }
  
  public void setQuestion(Questions question)
  {
    this.question = question;
  }
  
  public long getSearchappid()
  {
    return this.searchappid;
  }
  
  public void setSearchappid(long searchappid)
  {
    this.searchappid = searchappid;
  }
  
  public String getQnstype()
  {
    return this.qnstype;
  }
  
  public void setQnstype(String qnstype)
  {
    this.qnstype = qnstype;
  }
}
