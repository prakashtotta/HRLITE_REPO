package com.bean;

import java.util.Date;
import java.util.List;

public class Questions
{
  public long questionId;
  public String questionName;
  public String questionType;
  public String optionValue1;
  public String optionValue2;
  public String optionValue3;
  public String optionValue4;
  public String optionValue5;
  public String status;
  public String typeVal;
  public String qnsType;
  public String correctAns;
  public Date correctAnsDate;
  public List<QuestionOptions> optionList;
  private String questionNameType;
  public long super_user_key;
  
  public long getQuestionId()
  {
    return this.questionId;
  }
  
  public void setQuestionId(long questionId)
  {
    this.questionId = questionId;
  }
  
  public String getQuestionName()
  {
    return this.questionName;
  }
  
  public void setQuestionName(String questionName)
  {
    this.questionName = questionName;
  }
  
  public String getQuestionType()
  {
    return this.questionType;
  }
  
  public void setQuestionType(String questionType)
  {
    this.questionType = questionType;
  }
  
  public String getOptionValue1()
  {
    return this.optionValue1;
  }
  
  public void setOptionValue1(String optionValue1)
  {
    this.optionValue1 = optionValue1;
  }
  
  public String getOptionValue2()
  {
    return this.optionValue2;
  }
  
  public void setOptionValue2(String optionValue2)
  {
    this.optionValue2 = optionValue2;
  }
  
  public String getOptionValue3()
  {
    return this.optionValue3;
  }
  
  public void setOptionValue3(String optionValue3)
  {
    this.optionValue3 = optionValue3;
  }
  
  public String getOptionValue4()
  {
    return this.optionValue4;
  }
  
  public void setOptionValue4(String optionValue4)
  {
    this.optionValue4 = optionValue4;
  }
  
  public String getOptionValue5()
  {
    return this.optionValue5;
  }
  
  public void setOptionValue5(String optionValue5)
  {
    this.optionValue5 = optionValue5;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public String getTypeVal()
  {
    return this.typeVal;
  }
  
  public void setTypeVal(String typeVal)
  {
    this.typeVal = typeVal;
  }
  
  public String getQnsType()
  {
    return this.qnsType;
  }
  
  public void setQnsType(String qnsType)
  {
    this.qnsType = qnsType;
  }
  
  public String getCorrectAns()
  {
    return this.correctAns;
  }
  
  public void setCorrectAns(String correctAns)
  {
    this.correctAns = correctAns;
  }
  
  public Date getCorrectAnsDate()
  {
    return this.correctAnsDate;
  }
  
  public void setCorrectAnsDate(Date correctAnsDate)
  {
    this.correctAnsDate = correctAnsDate;
  }
  
  public List<QuestionOptions> getOptionList()
  {
    return this.optionList;
  }
  
  public void setOptionList(List<QuestionOptions> optionList)
  {
    this.optionList = optionList;
  }
  
  public String getQuestionNameType()
  {
    return this.questionName + "   (" + this.typeVal + ")";
  }
  
  public long getSuper_user_key()
  {
    return this.super_user_key;
  }
  
  public void setSuper_user_key(long superUserKey)
  {
    this.super_user_key = superUserKey;
  }
}
