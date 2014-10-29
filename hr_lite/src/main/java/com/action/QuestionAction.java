package com.action;

import com.bean.QuestionOptions;
import com.bean.Questions;
import com.bean.Timezone;
import com.bean.User;
import com.bo.BOFactory;
import com.bo.QuestionBO;
import com.form.QuestionForm;
import com.resources.Constant;
import com.util.DateUtil;
import com.util.StringUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class QuestionAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(QuestionAction.class);
  
  public ActionForward questionslist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    QuestionForm questionForm = (QuestionForm)form;
    request.setAttribute("questionHome", "question");
    return mapping.findForward("questionslist");
  }
  
  public ActionForward loadCriterias(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside loadCriterias method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String questionId = request.getParameter("questionId");
    QuestionForm questionForm = (QuestionForm)form;
    User user = (User)request.getSession().getAttribute("user_data");
    
    Questions qns = BOFactory.getQuestionBO().getQuestiondetails(questionId);
    if ((qns.getTypeVal() != null) && ((qns.getTypeVal().equals("dropdown")) || (qns.getTypeVal().equals("radio"))))
    {
      List queoptList = new ArrayList();
      queoptList = BOFactory.getQuestionBO().getQuestionsoptionDetails(questionId);
      questionForm.setOptionList(queoptList);
    }
    questionForm.setQnsType(qns.getTypeVal());
    questionForm.setQuestionId(qns.getQuestionId());
    questionForm.setCriteriaStringList(Constant.getSearchCriterisString(user));
    questionForm.setCriteriaNumericList(Constant.getSearchCriterisNumeric(user));
    
    return mapping.findForward("loadCriterias");
  }
  
  public ActionForward createQuestion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    QuestionForm questionForm = (QuestionForm)form;
    questionForm.setTypeVal("noVal");
    return mapping.findForward("createQuestion");
  }
  
  public ActionForward createQuestionforQuestiongroup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    QuestionForm questionForm = (QuestionForm)form;
    String valueDisplay = request.getParameter("value");
    String readPreview = request.getParameter("readPreview");
    
    String edit = request.getParameter("edit");
    questionForm.setValuDisplay(valueDisplay);
    questionForm.setEditCreate(edit);
    questionForm.setReadPreview(readPreview);
    questionForm.setTypeVal("eeee");
    
    return mapping.findForward("createQuestionforquestiongroup");
  }
  
  public ActionForward saveQuestion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveQuestion method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user = (User)request.getSession().getAttribute("user_data");
    QuestionForm questionForm = (QuestionForm)form;
    Questions que = new Questions();
    String answeroption = request.getParameter("answerOption");
    String correctanswer = request.getParameter("correctAns");
    String quntype = request.getParameter("quntype");
    
    logger.info("answeroption" + answeroption);
    List<QuestionOptions> selectedoption = new ArrayList();
    if ((!StringUtils.isNullOrEmpty(answeroption)) && (answeroption.length() > 0))
    {
      String[] column = StringUtils.tokenize(answeroption, "\n");
      for (int i = 0; i < column.length; i++)
      {
        QuestionOptions quetype = new QuestionOptions();
        quetype.setQnsoptId(1L);
        quetype.setQuestionOptValue(column[i]);
        quetype.setIscorrect(0);
        selectedoption.add(quetype);
      }
    }
    questionForm.setOptionList(selectedoption);
    toValue(que, questionForm);
    que.setQnsType(quntype);
    que.setSuper_user_key(user.getSuper_user_key());
    if (StringUtils.compare(questionForm.getTypeVal(), "date"))
    {
      if (!StringUtils.isNullOrEmpty(questionForm.getCorrectAnsDate()))
      {
        String datepattern = DateUtil.getDatePatternFormat(user.getLocale());
        String converteddate = DateUtil.convertFromTimezoneToTimezoneDate(questionForm.getCorrectAnsDate(), datepattern, user.getTimezone().getTimezoneCode(), TimeZone.getDefault().getID());
        
        logger.info("converteddate" + converteddate);
        
        Calendar cal = DateUtil.convertStringDateToCalendar(converteddate, datepattern);
        
        que.setCorrectAnsDate(cal.getTime());
      }
    }
    else {
      que.setCorrectAnsDate(null);
    }
    que = BOFactory.getQuestionBO().saveQuestion(que, selectedoption, correctanswer);
    List queoptList = new ArrayList();
    queoptList = BOFactory.getQuestionBO().getQuestionsoptionDetails(String.valueOf(que.getQuestionId()));
    String optionval = "";
    if (queoptList.size() > 0)
    {
      Iterator itr = queoptList.iterator();
      int i = 0;
      while (itr.hasNext())
      {
        QuestionOptions queoption = (QuestionOptions)itr.next();
        
        optionval = optionval + String.valueOf(queoption.getQuestionOptValue());
        i++;
      }
      questionForm.setOptionssetList(optionval);
    }
    String readpreview = request.getParameter("readPreview");
    questionForm.setReadPreview(readpreview);
    toForm(questionForm, que);
    request.setAttribute("saveQuestion", "yes");
    questionForm.setEditCreate("2");
    questionForm.setValuDisplay("2");
    questionForm.setQuestionId(que.getQuestionId());
    if (StringUtils.compare(quntype, "SLAVE")) {
      return mapping.findForward("createpagequestionquestiongroup");
    }
    return mapping.findForward("createQuestion");
  }
  
  public ActionForward saveQuestionwithgroup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveQuestion method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user = (User)request.getSession().getAttribute("user_data");
    QuestionForm questionForm = (QuestionForm)form;
    Questions que = new Questions();
    
    String answeroption = request.getParameter("answerOption");
    String correctanswer = request.getParameter("correctAns");
    String quntype = request.getParameter("quntype");
    
    logger.info("answeroption" + answeroption);
    List<QuestionOptions> selectedoption = new ArrayList();
    if (answeroption.length() > 0)
    {
      String[] column = StringUtils.tokenize(answeroption, "\n");
      for (int i = 0; i < column.length; i++)
      {
        QuestionOptions quetype = new QuestionOptions();
        quetype.setQnsoptId(1L);
        quetype.setQuestionOptValue(column[i]);
        quetype.setIscorrect(0);
        selectedoption.add(quetype);
      }
    }
    questionForm.setOptionList(selectedoption);
    toValue(que, questionForm);
    que.setQnsType(quntype);
    if (!StringUtils.isNullOrEmpty(questionForm.getCorrectAnsDate()))
    {
      String datepattern = DateUtil.getDatePatternFormat(user.getLocale());
      String converteddate = DateUtil.convertFromTimezoneToTimezoneDate(questionForm.getCorrectAnsDate(), datepattern, user.getTimezone().getTimezoneCode(), TimeZone.getDefault().getID());
      
      logger.info("converteddate" + converteddate);
      
      Calendar cal = DateUtil.convertStringDateToCalendar(converteddate, datepattern);
      
      que.setCorrectAnsDate(cal.getTime());
    }
    que = BOFactory.getQuestionBO().saveQuestion(que, selectedoption, correctanswer);
    List queoptList = new ArrayList();
    queoptList = BOFactory.getQuestionBO().getQuestionsoptionDetails(String.valueOf(que.getQuestionId()));
    String optionval = "";
    if (queoptList.size() > 0)
    {
      Iterator itr = queoptList.iterator();
      int i = 0;
      while (itr.hasNext())
      {
        QuestionOptions queoption = (QuestionOptions)itr.next();
        
        optionval = optionval + String.valueOf(queoption.getQuestionOptValue());
        i++;
      }
      questionForm.setOptionssetList(optionval);
    }
    String readpreview = request.getParameter("readPreview");
    questionForm.setReadPreview(readpreview);
    toForm(questionForm, que);
    request.setAttribute("saveQuestion", "yes");
    questionForm.setEditCreate("2");
    questionForm.setValuDisplay("2");
    questionForm.setQuestionId(que.getQuestionId());
    if (StringUtils.compare(quntype, "SLAVE")) {
      return mapping.findForward("createpagequestionquestiongroup");
    }
    return mapping.findForward("createQuestion");
  }
  
  public ActionForward updateQuestion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updateQuestion method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user = (User)request.getSession().getAttribute("user_data");
    QuestionForm questionForm = (QuestionForm)form;
    String id = request.getParameter("id");
    Questions que = BOFactory.getQuestionBO().getQuestiondetails(id);
    
    String answeroption = request.getParameter("answerOption");
    String correctanswer = request.getParameter("correctAns");
    List<QuestionOptions> selectedoption = new ArrayList();
    logger.info("111111111111" + answeroption);
    if ((!StringUtils.isNullOrEmpty(answeroption)) && (answeroption.length() > 0))
    {
      String[] column = StringUtils.tokenize(answeroption, "\n");
      for (int i = 0; i < column.length; i++)
      {
        QuestionOptions quetype = new QuestionOptions();
        quetype.setQnsoptId(4L);
        quetype.setQuestionOptValue(column[i]);
        quetype.setIscorrect(0);
        selectedoption.add(quetype);
      }
    }
    questionForm.setOptionList(selectedoption);
    toValue(que, questionForm);
    logger.info("55555555.................5555555555" + questionForm.getTypeVal());
    if (StringUtils.compare(questionForm.getTypeVal(), "date"))
    {
      if (!StringUtils.isNullOrEmpty(questionForm.getCorrectAnsDate()))
      {
        String datepattern = DateUtil.getDatePatternFormat(user.getLocale());
        String converteddate = DateUtil.convertFromTimezoneToTimezoneDate(questionForm.getCorrectAnsDate(), datepattern, user.getTimezone().getTimezoneCode(), TimeZone.getDefault().getID());
        
        logger.info("converteddate" + converteddate);
        
        Calendar cal = DateUtil.convertStringDateToCalendar(converteddate, datepattern);
        
        que.setCorrectAnsDate(cal.getTime());
      }
    }
    else {
      que.setCorrectAnsDate(null);
    }
    List queoptList = new ArrayList();
    queoptList = BOFactory.getQuestionBO().getQuestionsoptionDetails(id);
    if ((queoptList != null) && (queoptList.size() > 0)) {
      for (int k = 0; k < queoptList.size(); k++)
      {
        QuestionOptions queoption = (QuestionOptions)queoptList.get(k);
        BOFactory.getQuestionBO().deleteQuestionOption(queoption);
      }
    }
    que = BOFactory.getQuestionBO().updateQuestions(que, selectedoption, correctanswer);
    List queoptListnew = new ArrayList();
    queoptListnew = BOFactory.getQuestionBO().getQuestionsoptionDetails(id);
    String optionval = "";
    if (queoptListnew.size() > 0)
    {
      Iterator itr = queoptListnew.iterator();
      int i = 0;
      while (itr.hasNext())
      {
        QuestionOptions queoption = (QuestionOptions)itr.next();
        
        optionval = optionval + String.valueOf(queoption.getQuestionOptValue());
        i++;
      }
      questionForm.setOptionssetList(optionval);
    }
    String readpreview = request.getParameter("readPreview");
    questionForm.setReadPreview(readpreview);
    questionForm.setQuestionId(que.getQuestionId());
    questionForm.setQuestionId(que.getQuestionId());
    request.setAttribute("updateQuestion", "yes");
    return mapping.findForward("createQuestion");
  }
  
  public ActionForward editquestion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside editquestion method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user = (User)request.getSession().getAttribute("user_data");
    String questionId = request.getParameter("questionId");
    QuestionForm questionForm = (QuestionForm)form;
    Questions que = BOFactory.getQuestionBO().getQuestiondetails(questionId);
    List queoptList = new ArrayList();
    queoptList = BOFactory.getQuestionBO().getQuestionsoptionDetails(questionId);
    toForm(questionForm, que);
    String optionval = "";
    if (queoptList.size() > 0)
    {
      Iterator itr = queoptList.iterator();
      int i = 0;
      while (itr.hasNext())
      {
        QuestionOptions queoption = (QuestionOptions)itr.next();
        
        optionval = optionval + String.valueOf(queoption.getQuestionOptValue());
        i++;
      }
      questionForm.setOptionssetList(optionval);
    }
    if (que.getCorrectAnsDate() != null)
    {
      String datepattern = Constant.getValue("defaultdateformat");
      if (user != null) {
        datepattern = DateUtil.getDatePatternFormat(user.getLocale());
      }
      questionForm.setCorrectAnsDate(DateUtil.convertDateToStringDate(que.getCorrectAnsDate(), datepattern));
    }
    String redpreview = request.getParameter("readPreview");
    questionForm.setReadPreview(redpreview);
    String edit = request.getParameter("edit");
    questionForm.setEditCreate(edit);
    String valueDisplay = request.getParameter("value");
    questionForm.setValuDisplay(valueDisplay);
    return mapping.findForward("createQuestion");
  }
  
  public ActionForward editquestionForSelector(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside editquestion method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    String questionId = request.getParameter("questionId");
    QuestionForm questionForm = (QuestionForm)form;
    Questions que = BOFactory.getQuestionBO().getQuestiondetails(questionId);
    
    toForm(questionForm, que);
    
    String redpreview = request.getParameter("readPreview");
    questionForm.setReadPreview(redpreview);
    

    String edit = request.getParameter("edit");
    questionForm.setEditCreate(edit);
    String valueDisplay = request.getParameter("value");
    questionForm.setValuDisplay(valueDisplay);
    request.setAttribute("viewdetailsforselector", "yes");
    return mapping.findForward("createQuestion");
  }
  
  public ActionForward questiondelete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside questiondelete method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    QuestionForm questionForm = (QuestionForm)form;
    
    String id = request.getParameter("questionId");
    Questions que = BOFactory.getQuestionBO().getQuestiondetails(id);
    toDelete(que, questionForm);
    
    que = BOFactory.getQuestionBO().updateQuestionsforDelete(que);
    String readpreview = request.getParameter("readPreview");
    questionForm.setReadPreview(readpreview);
    request.setAttribute("deleteQuestion", "yes");
    request.setAttribute("questionHome", "question");
    return mapping.findForward("questionslist");
  }
  
  public ActionForward saveRadioCreate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveRadioCreate method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    QuestionForm questionForm = (QuestionForm)form;
    
    String valueDisplay = request.getParameter("value");
    questionForm.setValuDisplay(valueDisplay);
    String readpreview = request.getParameter("readPreview");
    questionForm.setReadPreview(readpreview);
    String edit = request.getParameter("edit");
    questionForm.setEditCreate(edit);
    


    return mapping.findForward("createQuestion");
  }
  
  public ActionForward saveradioEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveradioEdit method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    QuestionForm questionForm = (QuestionForm)form;
    String valueDisplay = request.getParameter("value");
    questionForm.setValuDisplay(valueDisplay);
    String readpreview = request.getParameter("readPreview");
    questionForm.setReadPreview(readpreview);
    String id = request.getParameter("id");
    Questions que = BOFactory.getQuestionBO().getQuestiondetails(id);
    
    String typeval = request.getParameter("typeval");
    toForm(questionForm, que);
    String edit = request.getParameter("edit");
    questionForm.setEditCreate(edit);
    questionForm.setTypeVal(typeval);
    


    return mapping.findForward("createQuestion");
  }
  
  public ActionForward saveDisplayValueTextCreate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveQuestion method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    QuestionForm questionForm = (QuestionForm)form;
    

    String valueDisplay = request.getParameter("value");
    questionForm.setValuDisplay(valueDisplay);
    String readpreview = request.getParameter("readPreview");
    questionForm.setReadPreview(readpreview);
    String edit = request.getParameter("edit");
    questionForm.setEditCreate(edit);
    



    return mapping.findForward("createQuestion");
  }
  
  public ActionForward saveDisplayValueTextEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveDisplayValueTextEdit method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    QuestionForm questionForm = (QuestionForm)form;
    String valueDisplay = request.getParameter("value");
    questionForm.setValuDisplay(valueDisplay);
    String readpreview = request.getParameter("readPreview");
    questionForm.setReadPreview(readpreview);
    String id = request.getParameter("id");
    Questions que = BOFactory.getQuestionBO().getQuestiondetails(id);
    String typeval = request.getParameter("typeval");
    toForm(questionForm, que);
    String edit = request.getParameter("edit");
    questionForm.setEditCreate(edit);
    questionForm.setTypeVal(typeval);
    
    return mapping.findForward("createQuestion");
  }
  
  public void toValue(Questions que, QuestionForm questionForm)
  {
    que.setQuestionName(questionForm.getQuestionName());
    que.setQuestionType(questionForm.getQuestionType());
    que.setCorrectAns(questionForm.getCorrectAns());
    que.setTypeVal(questionForm.getTypeVal());
    que.setQnsType("MASTER");
    que.setStatus("A");
  }
  
  public void toForm(QuestionForm questionForm, Questions question)
  {
    questionForm.setQuestionId(question.getQuestionId());
    questionForm.setQuestionName(question.getQuestionName());
    questionForm.setQuestionType(question.getQuestionType());
    
    questionForm.setTypeVal(question.getTypeVal());
    questionForm.setStatus(question.getStatus());
    
    questionForm.setCorrectAns(question.getCorrectAns());
  }
  
  public void toDelete(Questions que, QuestionForm questionForm)
  {
    que.setStatus("D");
  }
}
