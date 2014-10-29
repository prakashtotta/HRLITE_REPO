package com.bo;

import com.bean.JobApplicationEvent;
import com.bean.QuestionGroupApplicants;
import com.bean.QuestionGroups;
import com.bean.QuestionOptions;
import com.bean.QuestionnaireAnswers;
import com.bean.Questions;
import com.bean.User;
import com.common.Common;
import com.dao.LovDAO;
import com.util.DateUtil;
import com.util.StringUtils;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

public class QuestionBO
{
  protected static final Logger logger = Logger.getLogger(QuestionBO.class);
  LovDAO lovdao;
  
  public List getAllquestionsDetailsForPagination(User user, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.lovdao.getAllquestionsDetailsForPagination(user, pageSize, startIndex, dir_str, sort_str);
  }
  
  public List getAllquestionsDetails(User user)
  {
    return this.lovdao.getAllquestionsDetails(user);
  }
  
  public List getAllquestionsGroupDetailsForPagination(User user, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.lovdao.getAllquestionsGroupDetailsForPagination(user, pageSize, startIndex, dir_str, sort_str);
  }
  
  public List getAllquestionsGroupDetails(User user)
  {
    return this.lovdao.getAllquestionsGroupDetails(user);
  }
  
  public List getAllquestionsGroupDetailsByApplicantId(long applicantId)
  {
    return this.lovdao.getAssignedQuestionGroupsToApplicant(applicantId);
  }
  
  public List getAllQuestions(long super_user_key)
  {
    return this.lovdao.getAllQuestions(super_user_key);
  }
  
  public QuestionGroups getQuestionsByQuestionGroup(long questiongrId)
  {
    QuestionGroups quesgroup = LovDAO.getQuestionsByQuestionGroup(questiongrId);
    return quesgroup;
  }
  
  public Questions getQuestiondetails(String quesId)
  {
    Questions ques = LovDAO.getQuestiondetails(quesId);
    return ques;
  }
  
  public QuestionGroups updateQuestionsGroup(QuestionGroups quesgroup)
  {
    return LovDAO.updateQuestionsGroup(quesgroup);
  }
  
  public List getQuestionsoptionDetails(String quesId)
  {
    return LovDAO.getQuestionsoptionDetails(quesId);
  }
  
  public List getQuestionnaireAnsList(long qnsgrpappid)
  {
    return this.lovdao.getQuestionnaireAnsList(qnsgrpappid);
  }
  
  public QuestionGroups saveQuestionGroup(QuestionGroups quesgroup)
  {
    return LovDAO.saveQuestionGroup(quesgroup);
  }
  
  public Questions saveQuestionwithGroup(Questions ques, List selecteoption, String correctans)
  {
    return LovDAO.saveQuestionwithGroup(ques, selecteoption, correctans);
  }
  
  public QuestionGroups getQuestionsGroupDetails(String questiongrId)
  {
    QuestionGroups quesgroup = LovDAO.getQuestionsGroupDetails(questiongrId);
    return quesgroup;
  }
  
  public QuestionGroupApplicants getQuestionGroupApplicants(long qnsGrpAppId, long applicantId, String uuid)
  {
    return LovDAO.getQuestionGroupApplicants(qnsGrpAppId, applicantId, uuid);
  }
  
  public boolean savequestionnaire(HttpServletRequest request, QuestionGroupApplicants qngrpapp)
  {
    logger.info("inside savequestionnaire start");
    boolean isucessess = true;
    try
    {
      QuestionGroups que = qngrpapp.getQuestiongroup();
      
      List questionbygroupList = que.getQuestions();
      if ((questionbygroupList != null) && (questionbygroupList.size() > 0)) {
        for (int i = 0; i < questionbygroupList.size(); i++)
        {
          Questions qns = (Questions)questionbygroupList.get(i);
          
          QuestionnaireAnswers qanswer = new QuestionnaireAnswers();
          qanswer.setQuestionGrpAppId(qngrpapp.getQnsGrpAppId());
          qanswer.setQuestionId(qns.getQuestionId());
          if ((qns.getQnsType() != null) && (qns.getTypeVal().equals("date")))
          {
            String temp = "qns_" + qns.getQuestionId() + "_qns";
            String dval = request.getParameter(temp);
            logger.info("dvaldval" + dval);
            if (!StringUtils.isNullOrEmpty(dval))
            {
              Calendar cal = DateUtil.convertStringDateToCalendar(dval, DateUtil.dateformatstandard);
              qanswer.setAnswerDate(cal.getTime());
            }
          }
          else if ((qns.getQnsType() != null) && (qns.getTypeVal().equals("checkbox")))
          {
            String[] values_chk = request.getParameterValues("inp_" + qns.getQuestionId());
            logger.info("values_chk" + values_chk.length);
            String tmp = "";
            int tot_len = 0;
            if (values_chk != null) {
              tot_len = values_chk.length;
            }
            for (int j = 0; j < tot_len; j++) {
              tmp = tmp + (values_chk[j] == null ? "" : values_chk[j].trim()) + ",";
            }
            if (!StringUtils.isNullOrEmpty(tmp)) {
              tmp = tmp.substring(0, tmp.length() - 1);
            }
            qanswer.setAnswer(tmp);
          }
          else
          {
            String val = request.getParameter("inp_" + qns.getQuestionId());
            qanswer.setAnswer(val);
          }
          this.lovdao.saveQuestionnaireAns(qanswer);
        }
      }
      qngrpapp.setResult("DONE");
      qngrpapp.setEndTime(new Date());
      this.lovdao.updateQuestionGroupApplicants(qngrpapp);
      
      JobApplicationEvent event = BOFactory.getApplicantBO().getApplicationEvent(qngrpapp.getApplicantId(), Common.getEventType("Questionnaire Assigned"), qngrpapp.getQnsGrpAppId());
      event.setUpdatedBy("APPLICANT");
      event.setUpdatedDate(new Date());
      event.setStatus(1);
      event.setInterviewDate(qngrpapp.getEndTime());
      
      BOFactory.getApplicantBO().updateApplicationEvent(event);
    }
    catch (Exception e)
    {
      logger.info("error on savequestionnaire" + e);
      isucessess = false;
    }
    return isucessess;
  }
  
  public QuestionOptions deleteQuestionOption(QuestionOptions quesopt)
  {
    return LovDAO.deleteQuestionOption(quesopt);
  }
  
  public Questions updateQuestions(Questions ques, List selecteoption, String correctans)
  {
    return LovDAO.updateQuestions(ques, selecteoption, correctans);
  }
  
  public Questions saveQuestion(Questions ques, List selecteoption, String correctans)
  {
    return LovDAO.saveQuestion(ques, selecteoption, correctans);
  }
  
  public Questions updateQuestionsforDelete(Questions ques)
  {
    return LovDAO.updateQuestionsforDelete(ques);
  }
  
  public LovDAO getLovdao()
  {
    return this.lovdao;
  }
  
  public void setLovdao(LovDAO lovdao)
  {
    this.lovdao = lovdao;
  }
  
  public List getAllquestionOptionByQuestion(long queid)
  {
    return this.lovdao.getAllquestionOptionByQuestion(queid);
  }
  
  public void arrangeQuestionInGroup(String qnsgroupid, String arrangestr)
  {
    logger.info("inside arrangeQuestionInGroup");
    QuestionGroups queGroup = getQuestionsGroupDetails(qnsgroupid);
    logger.info("inside arrangeQuestionInGroup" + qnsgroupid);
    logger.info("inside arrangeQuestionInGroup arrangestr" + arrangestr);
    if (!StringUtils.isNullOrEmpty(arrangestr))
    {
      arrangestr = arrangestr.substring(0, arrangestr.length() - 1);
      StringTokenizer strtoken = new StringTokenizer(arrangestr, ",");
      String newval = "";
      while (strtoken.hasMoreTokens())
      {
        String abc = strtoken.nextToken();
        
        abc = abc.substring(abc.lastIndexOf(".") + 1, abc.length());
        
        logger.info("inside arrangeQuestionInGroup abc" + abc);
        newval = newval + abc + ",";
        
        logger.info("inside arrangeQuestionInGroup newval" + newval);
      }
      if (!StringUtils.isNullOrEmpty(newval)) {
        newval = newval.substring(0, newval.length() - 1);
      }
      queGroup.setQuestionsSeq(newval);
    }
    updateQuestionsGroup(queGroup);
  }
}
