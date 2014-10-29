package com.bean;

import java.util.Date;

public class QuestionnaireAnswers
{
  public long questionsanswerId;
  public long questionId;
  public long applicantrefId;
  public long questionGrpAppId;
  public String answer;
  public Date answerDate;
  
  public long getQuestionsanswerId()
  {
    return this.questionsanswerId;
  }
  
  public void setQuestionsanswerId(long questionsanswerId)
  {
    this.questionsanswerId = questionsanswerId;
  }
  
  public long getQuestionId()
  {
    return this.questionId;
  }
  
  public void setQuestionId(long questionId)
  {
    this.questionId = questionId;
  }
  
  public long getApplicantrefId()
  {
    return this.applicantrefId;
  }
  
  public void setApplicantrefId(long applicantrefId)
  {
    this.applicantrefId = applicantrefId;
  }
  
  public long getQuestionGrpAppId()
  {
    return this.questionGrpAppId;
  }
  
  public void setQuestionGrpAppId(long questionGrpAppId)
  {
    this.questionGrpAppId = questionGrpAppId;
  }
  
  public String getAnswer()
  {
    return this.answer;
  }
  
  public void setAnswer(String answer)
  {
    this.answer = answer;
  }
  
  public Date getAnswerDate()
  {
    return this.answerDate;
  }
  
  public void setAnswerDate(Date answerDate)
  {
    this.answerDate = answerDate;
  }
}
