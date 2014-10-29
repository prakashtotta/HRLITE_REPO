package com.bean;

public class QuestionOptions
{
  public long qnsoptId;
  public String questionOptValue;
  int iscorrect;
  public long questionId;
  
  public long getQnsoptId()
  {
    return this.qnsoptId;
  }
  
  public void setQnsoptId(long qnsoptId)
  {
    this.qnsoptId = qnsoptId;
  }
  
  public String getQuestionOptValue()
  {
    return this.questionOptValue;
  }
  
  public void setQuestionOptValue(String questionOptValue)
  {
    this.questionOptValue = questionOptValue;
  }
  
  public int getIscorrect()
  {
    return this.iscorrect;
  }
  
  public void setIscorrect(int iscorrect)
  {
    this.iscorrect = iscorrect;
  }
  
  public long getQuestionId()
  {
    return this.questionId;
  }
  
  public void setQuestionId(long questionId)
  {
    this.questionId = questionId;
  }
}
