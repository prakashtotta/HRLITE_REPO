package com.bean;

import java.util.List;

public class QuestionGroups
{
  public long questiongroupId;
  public String questiongroupName;
  public String questiongroupDesc;
  public String status;
  public List questions;
  public String questionsSeq;
  public long super_user_key;
  
  public long getQuestiongroupId()
  {
    return this.questiongroupId;
  }
  
  public void setQuestiongroupId(long questiongroupId)
  {
    this.questiongroupId = questiongroupId;
  }
  
  public String getQuestiongroupName()
  {
    return this.questiongroupName;
  }
  
  public void setQuestiongroupName(String questiongroupName)
  {
    this.questiongroupName = questiongroupName;
  }
  
  public String getQuestiongroupDesc()
  {
    return this.questiongroupDesc;
  }
  
  public void setQuestiongroupDesc(String questiongroupDesc)
  {
    this.questiongroupDesc = questiongroupDesc;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public List getQuestions()
  {
    return this.questions;
  }
  
  public void setQuestions(List questions)
  {
    this.questions = questions;
  }
  
  public String getQuestionsSeq()
  {
    return this.questionsSeq;
  }
  
  public void setQuestionsSeq(String questionsSeq)
  {
    this.questionsSeq = questionsSeq;
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
