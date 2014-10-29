package com.test;

import com.bean.ApplicantActivity;
import com.bean.JobApplicant;
import com.bean.JobApplicationEvent;
import com.bean.QuestionGroupApplicants;
import com.bean.QuestionGroups;
import com.bean.User;
import com.bean.testengine.MockQuestionSet;
import com.bean.testengine.MockTest;
import com.bean.testengine.MockTestQuestion;
import com.bean.testengine.MockTestResult;
import com.bo.ApplicantTXBO;
import com.bo.BOFactory;
import com.common.Common;
import com.dao.ApplicantDAO;
import com.dao.MockTestDAO;
import com.form.ApplicantForm;
import com.resources.Constant;
import com.util.StringUtils;
import java.util.ArrayList;
import java.util.List;
import org.apache.struts.upload.FormFile;

public class MockTestBO
{
  MockTestDAO mocktestdao;
  ApplicantDAO applicantdao;
  
  public void updateMockTestResult(MockTestResult mockResult)
  {
    this.mocktestdao.updateMockTestResult(mockResult);
  }
  
  public void finishMockTest(String testId, int correctCount, int totalQns)
  {
    MockTest mocktest = this.mocktestdao.getMockTest(testId);
    mocktest.setCorrectNo(correctCount);
    mocktest.setTotalQns(totalQns);
    double ll = correctCount / totalQns;
    String result = "";
    boolean isFail = false;
    if (ll * 100.0D >= mocktest.getPassPercentage())
    {
      mocktest.setResult("Pass");
    }
    else
    {
      mocktest.setResult("Fail");
      isFail = true;
    }
    mocktest.setPercentage(ll * 100.0D);
    mocktest.setEndTime(new java.util.Date());
    this.mocktestdao.updateMockTest(mocktest);
    
    JobApplicant applicant = this.applicantdao.getApplicantDetailswithUUID(String.valueOf(mocktest.getApplicantId()), mocktest.getUuid());
    String activityname = "";
    if (isFail)
    {
      JobApplicationEvent event = this.applicantdao.getApplicationEvent(mocktest.getApplicantId(), Common.getEventType("Exam Screening"), mocktest.getTestId());
      event.setUpdatedBy("APPLICANT");
      event.setUpdatedDate(new java.util.Date());
      event.setStatus(2);
      event.setInterviewDate(mocktest.getStartTime());
      

      this.applicantdao.updateApplicationEvent(event);
      if ((!StringUtils.isNullOrEmpty(Constant.getValue("applicant.exam.workflow"))) && (Constant.getValue("applicant.exam.workflow").equalsIgnoreCase("yes")))
      {
        applicant.setInterviewState("Exam Screening Fail");
        this.applicantdao.updateApplicant(applicant);
      }
      activityname = "Exam Screening Fail";
    }
    else
    {
      JobApplicationEvent event = this.applicantdao.getApplicationEvent(mocktest.getApplicantId(), Common.getEventType("Exam Screening"), mocktest.getTestId());
      event.setUpdatedBy("APPLICANT");
      event.setUpdatedDate(new java.util.Date());
      event.setStatus(1);
      event.setInterviewDate(mocktest.getStartTime());
      

      this.applicantdao.updateApplicationEvent(event);
      if ((!StringUtils.isNullOrEmpty(Constant.getValue("applicant.exam.workflow"))) && (Constant.getValue("applicant.exam.workflow").equalsIgnoreCase("yes")))
      {
        applicant.setInterviewState("Exam Screening Passed");
        this.applicantdao.updateApplicant(applicant);
      }
      activityname = "Exam Screening Passed";
    }
    saveApplicantActivityFromApplicant(applicant, "External", activityname);
  }
  
  public void startMockTest(long testId)
  {
    MockTest mocktest = this.mocktestdao.getMockTest(String.valueOf(testId));
    mocktest.setResult(null);
    mocktest.setEndTime(null);
    mocktest.setStartTime(new java.util.Date());
    this.mocktestdao.updateMockTest(mocktest);
  }
  
  public void assignExamSubmit(ApplicantForm applicantform, MockTest mocktest, String comment, User user1)
  {
    this.mocktestdao.saveMockTest(mocktest);
    
    List mockCatList = this.mocktestdao.getAllMockCategoryList();
    applicantform.setMockCatList(mockCatList);
    
    JobApplicant applicant = this.applicantdao.getApplicantDetailswithUUID(String.valueOf(mocktest.getApplicantId()), mocktest.getUuid());
    if ((!StringUtils.isNullOrEmpty(Constant.getValue("applicant.exam.workflow"))) && (Constant.getValue("applicant.exam.workflow").equalsIgnoreCase("yes")))
    {
      applicant.setInterviewState("Exam Screening");
      this.applicantdao.updateApplicant(applicant);
      BOFactory.getApplicantTXBO().setCurrentOwner(applicant);
    }
    MockQuestionSet qnsset = this.mocktestdao.getMockQuestionSet(mocktest.getCat().getCatId());
    JobApplicationEvent event = new JobApplicationEvent();
    event.setCreatedBy(user1.getUserName());
    event.setCreatedByName(user1.getFirstName() + " " + user1.getLastName());
    event.setCreatedDate(new java.util.Date());
    event.setApplicant(applicant);
    event.setOwner(applicant.getOwner());
    event.setIsGroup(applicant.getIsGroup());
    event.setOwnerGroup(applicant.getOwnerGroup());
    event.setEventType(Common.getEventType("Exam Screening"));
    event.setStatus(0);
    event.setNotes(comment);
    event.setInterviewState("Exam Screening ^^ " + qnsset.getName());
    event.setTestId(mocktest.getTestId());
    

    this.applicantdao.saveApplicationEvent(event);
    

    saveApplicantActivity(applicant, user1, Common.APPLICANT_CREATED_BY_TYPE_INTERNAL, comment, "Exam Screening");
  }
  
  public void assignQuestionnaireSubmit(ApplicantForm applicantform, QuestionGroupApplicants questinanire, String comment, User user1)
  {
    this.mocktestdao.saveQuestionnaire(questinanire);
    

    JobApplicant applicant = this.applicantdao.getApplicantDetailswithUUID(String.valueOf(questinanire.getApplicantId()), questinanire.getUuid());
    if ((!StringUtils.isNullOrEmpty(Constant.getValue("applicant.exam.workflow"))) && (Constant.getValue("applicant.exam.workflow").equalsIgnoreCase("yes")))
    {
      applicant.setInterviewState("Questionnaire Assigned");
      this.applicantdao.updateApplicant(applicant);
      BOFactory.getApplicantTXBO().setCurrentOwner(applicant);
    }
    QuestionGroups qnsgroup = MockTestDAO.getQuestionsGroupDetails(questinanire.getQuestiongroup().getQuestiongroupId());
    JobApplicationEvent event = new JobApplicationEvent();
    event.setCreatedBy(questinanire.getCreatedBy());
    event.setCreatedByName(user1.getFirstName() + " " + user1.getLastName());
    event.setCreatedDate(new java.util.Date());
    event.setApplicant(applicant);
    event.setOwner(applicant.getOwner());
    event.setIsGroup(applicant.getIsGroup());
    event.setOwnerGroup(applicant.getOwnerGroup());
    event.setEventType(Common.getEventType("Questionnaire Assigned"));
    event.setStatus(0);
    event.setNotes(comment);
    event.setInterviewState("Questionnaire Assigned ^^ " + qnsgroup.getQuestiongroupName());
    event.setTestId(questinanire.getQnsGrpAppId());
    

    this.applicantdao.saveApplicationEvent(event);
    

    saveApplicantActivity(applicant, user1, Common.APPLICANT_CREATED_BY_TYPE_INTERNAL, comment, "Questionnaire Assigned");
  }
  
  private ApplicantActivity saveApplicantActivity(JobApplicant applicant, User user1, String createdbytype, String comment, String interviewstate)
  {
    ApplicantActivity activity = new ApplicantActivity();
    activity.setApplicantId(applicant.getApplicantId());
    activity.setFullName(applicant.getFullName());
    activity.setCreatedBy(user1.getUserName());
    activity.setUserId(user1.getUserId());
    activity.setUserFullName(user1.getFirstName() + " " + user1.getLastName());
    activity.setUuid(applicant.getUuid());
    activity.setCreatedDate(new java.util.Date());
    activity.setComment(comment);
    activity.setCreatedByType(createdbytype);
    activity.setActivityName(interviewstate);
    activity.setIsDisplayAppPage("Y");
    

    activity = this.applicantdao.saveApplicantActivity(activity);
    
    return activity;
  }
  
  public MockQuestionSet saveMockQuestionSet(MockQuestionSet mockquestionSet)
  {
    mockquestionSet = MockTestDAO.saveMockQuestionSet(mockquestionSet);
    
    return mockquestionSet;
  }
  
  public void saveMockTest(MockTest test)
  {
    this.mocktestdao.saveMockTest(test);
  }
  
  public void saveQuestionnaire(QuestionGroupApplicants quitionanire)
  {
    this.mocktestdao.saveQuestionnaire(quitionanire);
  }
  
  public QuestionGroups getQuestionsGroup(long id)
  {
    return this.mocktestdao.getQuestionsGroup(id);
  }
  
  public MockQuestionSet saveMockQuestionSetWithQuestions(MockQuestionSet mockquestionSet)
    throws Exception
  {
    try
    {
      MockUtil t = new MockUtil();
      List mockqList = t.getMockQuestionList(mockquestionSet.getCatId(), mockquestionSet.getAttachmentName());
      
      MockTestDAO.saveMockQuestionSet(mockquestionSet);
      
      addMockQuestion(mockqList, mockquestionSet.getCatId());
    }
    catch (Exception e)
    {
      throw e;
    }
    return mockquestionSet;
  }
  
  public MockQuestionSet updateMockQuestionSetWithQuestions(MockQuestionSet mockquestionSet, FormFile myFile)
    throws Exception
  {
    try
    {
      MockUtil t = new MockUtil();
      if ((myFile != null) && (myFile.getFileSize() != 0))
      {
        List mockqList = t.getMockQuestionList(mockquestionSet.getCatId(), mockquestionSet.getAttachmentName());
        
        MockTestDAO.updateMockQuestionSet(mockquestionSet);
        
        this.mocktestdao.deleteMockQuestions(mockquestionSet.getCatId());
        
        addMockQuestion(mockqList, mockquestionSet.getCatId());
      }
      else
      {
        MockTestDAO.updateMockQuestionSet(mockquestionSet);
      }
    }
    catch (Exception e)
    {
      throw e;
    }
    return mockquestionSet;
  }
  
  public void deleteMockQuestions(long catId)
  {
    this.mocktestdao.deleteMockQuestions(catId);
  }
  
  public MockQuestionSet updateMockQuestionSet(MockQuestionSet mockquestionSet)
  {
    mockquestionSet = MockTestDAO.updateMockQuestionSet(mockquestionSet);
    
    return mockquestionSet;
  }
  
  public MockQuestionSet getMockQuestionSeDetails(String id)
  {
    MockQuestionSet mockquestionSet = new MockQuestionSet();
    mockquestionSet = MockTestDAO.getMockQuestionSeDetails(id);
    
    return mockquestionSet;
  }
  
  public List getAllMockQuestionSetsListForLov(long super_user_key)
  {
    return this.mocktestdao.getAllMockQuestionSetsListForLov(super_user_key);
  }
  
  private ApplicantActivity saveApplicantActivityFromApplicant(JobApplicant applicant, String createdbytype, String interviewstate)
  {
    ApplicantActivity activity = new ApplicantActivity();
    activity.setApplicantId(applicant.getApplicantId());
    activity.setFullName(applicant.getFullName());
    activity.setCreatedBy(applicant.getFullName());
    activity.setUserId(0L);
    activity.setUserFullName(applicant.getFullName());
    activity.setUuid(applicant.getUuid());
    activity.setCreatedDate(new java.util.Date());
    activity.setComment("");
    activity.setCreatedByType(createdbytype);
    activity.setActivityName(interviewstate);
    activity.setIsDisplayAppPage("Y");
    

    activity = this.applicantdao.saveApplicantActivity(activity);
    
    return activity;
  }
  
  public List getAllMockTestResult(long testId)
  {
    return this.mocktestdao.getAllMockTestResult(testId);
  }
  
  public List getAllMockTestsByApplicant(long applicantId, String uuid)
  {
    return this.mocktestdao.getAllMockTestsByApplicant(applicantId, uuid);
  }
  
  public List getAllMockCategoryList()
  {
    return this.mocktestdao.getAllMockCategoryList();
  }
  
  public List getQuestionsIdListMockTestResult(MockTest mock1, String catId, long testId)
  {
    List qnsList = new ArrayList();
    List result = this.mocktestdao.getAllMockTestResult(testId);
    if ((result != null) && (result.size() == 0)) {
      CopyQuestions(mock1, catId);
    }
    result = this.mocktestdao.getAllMockTestResult(testId);
    if ((result != null) && (result.size() > 0)) {
      for (int i = 0; i < result.size(); i++)
      {
        MockTestResult mk = (MockTestResult)result.get(i);
        qnsList.add(Long.valueOf(mk.getMockQuestionId()));
      }
    }
    return qnsList;
  }
  
  public List getMockQuestionSet()
  {
    return this.mocktestdao.getMockQuestionSet();
  }
  
  public List getAllMockQuestionSetBySearchCriteria(User user, String name, String displayname, String description)
  {
    return this.mocktestdao.getAllMockQuestionSetBySearchCriteria(user, name, displayname, description);
  }
  
  public List getMockQuestionSetForPagination(int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.mocktestdao.getMockQuestionSetForPagination(pageSize, startIndex, dir_str, sort_str);
  }
  
  public List getMockQuestionSetBySearchCriteria(User user, String name, String displayname, String description, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.mocktestdao.getMockQuestionSetBySearchCriteria(user, name, displayname, description, pageSize, startIndex, dir_str, sort_str);
  }
  
  public MockTestResult getMockTestResult(long testId, long qid)
  {
    return this.mocktestdao.getMockTestResult(testId, qid);
  }
  
  public List getAllSkipedMockTestResult(long testId)
  {
    return this.mocktestdao.getAllSkipedMockTestResult(testId);
  }
  
  public MockQuestionSet getMockQuestionSet(int catId)
  {
    return this.mocktestdao.getMockQuestionSet(catId);
  }
  
  public List getAllMarkedMockTestResult(long testId)
  {
    return this.mocktestdao.getAllMarkedMockTestResult(testId);
  }
  
  public MockTestQuestion getMockQuestionById(long qid)
  {
    return this.mocktestdao.getMockQuestionById(qid);
  }
  
  public MockTest getMockTest(String testId)
  {
    return this.mocktestdao.getMockTest(testId);
  }
  
  public List getAllMockQuestionByCatId(int cid)
  {
    return this.mocktestdao.getAllMockQuestionByCatId(cid);
  }
  
  public MockTest getMockTest(String catId, String applicantId, String uuid)
  {
    return this.mocktestdao.getMockTest(catId, applicantId, uuid);
  }
  
  public MockTest getMockTestByTestDetails(String testid, String applicantId, String uuid)
  {
    return this.mocktestdao.getMockTestByTestDetails(new Long(testid).longValue(), new Long(applicantId).longValue(), uuid);
  }
  
  public QuestionGroupApplicants getQuestionNaire(long qnsgrpid, String applicantId, String uuid)
  {
    return this.mocktestdao.getQuestionNaire(qnsgrpid, applicantId, uuid);
  }
  
  public QuestionGroupApplicants getQuestionNaire(long qnsGrpAppId)
  {
    return this.mocktestdao.getQuestionNaire(qnsGrpAppId);
  }
  
  public void deleteMockTest(long applicantId, String uuid, long testId, User user1)
  {
    this.mocktestdao.deleteMockTest(applicantId, uuid, testId);
    
    JobApplicationEvent event = this.applicantdao.getApplicationEvent(applicantId, Common.getEventType("Exam Screening"), testId);
    

    event.setStatus(3);
    
    this.applicantdao.updateApplicationEvent(event);
    
    JobApplicant applicant = this.applicantdao.getApplicantDetailswithUUID(String.valueOf(applicantId), uuid);
    
    saveApplicantActivity(applicant, user1, Common.APPLICANT_CREATED_BY_TYPE_INTERNAL, "", "Exam Screening deleted");
  }
  
  public void deleteQuestionnaire(long applicantId, String uuid, long testId, User user1)
  {
    this.mocktestdao.deleteQuestionnaire(applicantId, uuid, testId);
    
    JobApplicationEvent event = this.applicantdao.getApplicationEvent(applicantId, Common.getEventType("Questionnaire Assigned"), testId);
    

    event.setStatus(3);
    
    this.applicantdao.updateApplicationEvent(event);
    
    JobApplicant applicant = this.applicantdao.getApplicantDetailswithUUID(String.valueOf(applicantId), uuid);
    
    saveApplicantActivity(applicant, user1, Common.APPLICANT_CREATED_BY_TYPE_INTERNAL, "", "Questionnaire Assigned deleted");
  }
  
  public MockTest createMockTest(String catId, String applicantId, String uuid)
  {
    MockTest mocktest = new MockTest();
    MockQuestionSet cat = new MockQuestionSet();
    cat.setCatId(new Integer(catId).intValue());
    mocktest.setCat(cat);
    mocktest.setApplicantId(new Long(applicantId).longValue());
    mocktest.setUuid(uuid);
    mocktest.setStartTime(new java.sql.Date(new java.util.Date().getTime()));
    
    MockTest mock1 = this.mocktestdao.addMockTest(mocktest);
    
    return mock1;
  }
  
  public List CopyQuestions(MockTest mock1, String catId)
  {
    List questionslist = this.mocktestdao.getPMPMockQuestions(new Integer(catId).intValue());
    
    List questionsIdList = new ArrayList();
    for (int i = 0; i < questionslist.size(); i++)
    {
      MockTestQuestion pmpmock = (MockTestQuestion)questionslist.get(i);
      
      MockTestResult mktst = new MockTestResult();
      
      mktst.setTestId(mock1.getTestId());
      mktst.setMockQuestionId(pmpmock.getMockquestionId());
      mktst.setIsdummy(pmpmock.getIsdummy());
      mktst.setQuestionno(pmpmock.getQuestionno());
      mktst.setCorrect(pmpmock.getCorrect());
      
      this.mocktestdao.addPMPMockTestTemp(mktst);
      
      questionsIdList.add(new Long(pmpmock.getMockquestionId()));
    }
    return questionsIdList;
  }
  
  public void addMockQuestion(List mockqlist, int catid)
  {
    for (int i = 0; i < mockqlist.size(); i++)
    {
      MockTestQuestion mk = (MockTestQuestion)mockqlist.get(i);
      mk.setCatId(catid);
      mk.setIsdummy(0);
      this.mocktestdao.addMockQuestion(mk);
    }
  }
  
  public MockTestDAO getMocktestdao()
  {
    return this.mocktestdao;
  }
  
  public void setMocktestdao(MockTestDAO mocktestdao)
  {
    this.mocktestdao = mocktestdao;
  }
  
  public ApplicantDAO getApplicantdao()
  {
    return this.applicantdao;
  }
  
  public void setApplicantdao(ApplicantDAO applicantdao)
  {
    this.applicantdao = applicantdao;
  }
}
