package com.bean.testengine;

public class MockTestResult
{
  long mocktestresultId;
  long testId;
  long mockQuestionId;
  int ansNo;
  int isCorrect = 0;
  int isdummy = 0;
  int isMarked = 0;
  int questionno;
  int correct;
  
  public long getMocktestresultId()
  {
    return this.mocktestresultId;
  }
  
  public void setMocktestresultId(long mocktestresultId)
  {
    this.mocktestresultId = mocktestresultId;
  }
  
  public long getTestId()
  {
    return this.testId;
  }
  
  public void setTestId(long testId)
  {
    this.testId = testId;
  }
  
  public long getMockQuestionId()
  {
    return this.mockQuestionId;
  }
  
  public void setMockQuestionId(long mockQuestionId)
  {
    this.mockQuestionId = mockQuestionId;
  }
  
  public int getAnsNo()
  {
    return this.ansNo;
  }
  
  public void setAnsNo(int ansNo)
  {
    this.ansNo = ansNo;
  }
  
  public int getIsCorrect()
  {
    return this.isCorrect;
  }
  
  public void setIsCorrect(int isCorrect)
  {
    this.isCorrect = isCorrect;
  }
  
  public int getIsdummy()
  {
    return this.isdummy;
  }
  
  public void setIsdummy(int isdummy)
  {
    this.isdummy = isdummy;
  }
  
  public int getIsMarked()
  {
    return this.isMarked;
  }
  
  public void setIsMarked(int isMarked)
  {
    this.isMarked = isMarked;
  }
  
  public int getQuestionno()
  {
    return this.questionno;
  }
  
  public void setQuestionno(int questionno)
  {
    this.questionno = questionno;
  }
  
  public int getCorrect()
  {
    return this.correct;
  }
  
  public void setCorrect(int correct)
  {
    this.correct = correct;
  }
}
