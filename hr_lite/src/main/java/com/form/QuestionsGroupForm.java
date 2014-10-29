package com.form;

import com.bean.QuestionGroups;
import com.bean.QuestionnaireAnswers;
import java.util.List;
import java.util.Map;
import org.apache.struts.action.ActionForm;

public class QuestionsGroupForm
  extends ActionForm
{
  public long questiongroupId;
  public long questionId;
  public String questionName;
  public String questionsSeq;
  public QuestionGroups questionGroup;
  public String headingComment;
  public long questiongroupapplicantId;
  public long applicantId;
  public String uuid;
  public Map<Long, QuestionnaireAnswers> answerMap;
  public String questionnaireStatus;
  public List questionsList;
  public String questiongroupName;
  public String questiongroupDesc;
  public String status;
  public String start;
  public String range;
  public String results;
  public String readPreview;
  public String typeVal;
  public String qnsType;
  public String answerOption;
  public String correctAns;
  public String correctAnsDate;
  public List questionbygroup;
  public List optionssetList;
  public List questions;
  
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
  
  public String getReadPreview()
  {
    return this.readPreview;
  }
  
  public void setReadPreview(String readPreview)
  {
    this.readPreview = readPreview;
  }
  
  public String getStart()
  {
    return this.start;
  }
  
  public void setStart(String start)
  {
    this.start = start;
  }
  
  public String getRange()
  {
    return this.range;
  }
  
  public void setRange(String range)
  {
    this.range = range;
  }
  
  public String getResults()
  {
    return this.results;
  }
  
  public void setResults(String results)
  {
    this.results = results;
  }
  
  public List getQuestions()
  {
    return this.questions;
  }
  
  public void setQuestions(List questions)
  {
    this.questions = questions;
  }
  
  public String getQuestionName()
  {
    return this.questionName;
  }
  
  public void setQuestionName(String questionName)
  {
    this.questionName = questionName;
  }
  
  public long getQuestionId()
  {
    return this.questionId;
  }
  
  public void setQuestionId(long questionId)
  {
    this.questionId = questionId;
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
  
  public String getAnswerOption()
  {
    return this.answerOption;
  }
  
  public void setAnswerOption(String answerOption)
  {
    this.answerOption = answerOption;
  }
  
  public String getCorrectAns()
  {
    return this.correctAns;
  }
  
  public void setCorrectAns(String correctAns)
  {
    this.correctAns = correctAns;
  }
  
  public String getCorrectAnsDate()
  {
    return this.correctAnsDate;
  }
  
  public void setCorrectAnsDate(String correctAnsDate)
  {
    this.correctAnsDate = correctAnsDate;
  }
  
  public List getQuestionbygroup()
  {
    return this.questionbygroup;
  }
  
  public void setQuestionbygroup(List questionbygroup)
  {
    this.questionbygroup = questionbygroup;
  }
  
  public List getOptionssetList()
  {
    return this.optionssetList;
  }
  
  public void setOptionssetList(List optionssetList)
  {
    this.optionssetList = optionssetList;
  }
  
  public String getQuestionsSeq()
  {
    return this.questionsSeq;
  }
  
  public void setQuestionsSeq(String questionsSeq)
  {
    this.questionsSeq = questionsSeq;
  }
  
  public QuestionGroups getQuestionGroup()
  {
    return this.questionGroup;
  }
  
  public void setQuestionGroup(QuestionGroups questionGroup)
  {
    this.questionGroup = questionGroup;
  }
  
  public String getHeadingComment()
  {
    return this.headingComment;
  }
  
  public void setHeadingComment(String headingComment)
  {
    this.headingComment = headingComment;
  }
  
  public long getQuestiongroupapplicantId()
  {
    return this.questiongroupapplicantId;
  }
  
  public void setQuestiongroupapplicantId(long questiongroupapplicantId)
  {
    this.questiongroupapplicantId = questiongroupapplicantId;
  }
  
  public long getApplicantId()
  {
    return this.applicantId;
  }
  
  public void setApplicantId(long applicantId)
  {
    this.applicantId = applicantId;
  }
  
  public String getUuid()
  {
    return this.uuid;
  }
  
  public void setUuid(String uuid)
  {
    this.uuid = uuid;
  }
  
  public Map<Long, QuestionnaireAnswers> getAnswerMap()
  {
    return this.answerMap;
  }
  
  public void setAnswerMap(Map<Long, QuestionnaireAnswers> answerMap)
  {
    this.answerMap = answerMap;
  }
  
  public String getQuestionnaireStatus()
  {
    return this.questionnaireStatus;
  }
  
  public void setQuestionnaireStatus(String questionnaireStatus)
  {
    this.questionnaireStatus = questionnaireStatus;
  }
  
  public List getQuestionsList()
  {
    return this.questionsList;
  }
  
  public void setQuestionsList(List questionsList)
  {
    this.questionsList = questionsList;
  }
}
