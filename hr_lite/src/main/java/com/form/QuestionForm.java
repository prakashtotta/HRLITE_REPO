package com.form;

import com.bean.QuestionOptions;
import java.util.List;
import org.apache.struts.action.ActionForm;

public class QuestionForm
  extends ActionForm
{
  public long questionId;
  public String correctAns;
  public String correctAnsDate;
  public String qnsType;
  public String answerOption;
  public List<QuestionOptions> optionList;
  public String optionssetList;
  public long questiongroupId;
  public String filtercri;
  public List criteriaStringList;
  public List criteriaNumericList;
  public String questionName;
  public String questionType;
  public String optionValue1;
  public String optionValue2;
  public String optionValue3;
  public String optionValue4;
  public String optionValue5;
  public String status;
  public String readPreview;
  public String valuDisplay;
  public String typeVal;
  public String editCreate;
  
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
  
  public String getReadPreview()
  {
    return this.readPreview;
  }
  
  public void setReadPreview(String readPreview)
  {
    this.readPreview = readPreview;
  }
  
  public String getValuDisplay()
  {
    return this.valuDisplay;
  }
  
  public void setValuDisplay(String valuDisplay)
  {
    this.valuDisplay = valuDisplay;
  }
  
  public String getTypeVal()
  {
    return this.typeVal;
  }
  
  public void setTypeVal(String typeVal)
  {
    this.typeVal = typeVal;
  }
  
  public String getEditCreate()
  {
    return this.editCreate;
  }
  
  public void setEditCreate(String editCreate)
  {
    this.editCreate = editCreate;
  }
  
  public String getCorrectAns()
  {
    return this.correctAns;
  }
  
  public void setCorrectAns(String correctAns)
  {
    this.correctAns = correctAns;
  }
  
  public String getQnsType()
  {
    return this.qnsType;
  }
  
  public void setQnsType(String qnsType)
  {
    this.qnsType = qnsType;
  }
  
  public String getAnswerOption()
  {
    return this.answerOption;
  }
  
  public void setAnswerOption(String answerOption)
  {
    this.answerOption = answerOption;
  }
  
  public List<QuestionOptions> getOptionList()
  {
    return this.optionList;
  }
  
  public void setOptionList(List<QuestionOptions> optionList)
  {
    this.optionList = optionList;
  }
  
  public String getCorrectAnsDate()
  {
    return this.correctAnsDate;
  }
  
  public void setCorrectAnsDate(String correctAnsDate)
  {
    this.correctAnsDate = correctAnsDate;
  }
  
  public String getOptionssetList()
  {
    return this.optionssetList;
  }
  
  public void setOptionssetList(String optionssetList)
  {
    this.optionssetList = optionssetList;
  }
  
  public long getQuestiongroupId()
  {
    return this.questiongroupId;
  }
  
  public void setQuestiongroupId(long questiongroupId)
  {
    this.questiongroupId = questiongroupId;
  }
  
  public List getCriteriaStringList()
  {
    return this.criteriaStringList;
  }
  
  public void setCriteriaStringList(List criteriaStringList)
  {
    this.criteriaStringList = criteriaStringList;
  }
  
  public List getCriteriaNumericList()
  {
    return this.criteriaNumericList;
  }
  
  public void setCriteriaNumericList(List criteriaNumericList)
  {
    this.criteriaNumericList = criteriaNumericList;
  }
  
  public String getFiltercri()
  {
    return this.filtercri;
  }
  
  public void setFiltercri(String filtercri)
  {
    this.filtercri = filtercri;
  }
}
