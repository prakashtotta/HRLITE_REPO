package com.action;

import com.bean.QuestionGroupApplicants;
import com.bean.QuestionGroups;
import com.bean.QuestionOptions;
import com.bean.QuestionnaireAnswers;
import com.bean.Questions;
import com.bean.Timezone;
import com.bean.User;
import com.bo.BOFactory;
import com.bo.QuestionBO;
import com.form.QuestionsGroupForm;
import com.resources.Constant;
import com.util.DateUtil;
import com.util.StringUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TimeZone;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class QuestionGroupAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(QuestionAction.class);
  
  public ActionForward createQuestionpage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside createQuestionpage method");
    String quesgroupId = request.getParameter("quesgroupId");
    QuestionsGroupForm questionForm = (QuestionsGroupForm)form;
    questionForm.setQuestiongroupId(new Long(quesgroupId).longValue());
    questionForm.setTypeVal("no");
    return mapping.findForward("createQuestionpage");
  }
  
  public ActionForward loadQuestions(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside loadQuestions method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String groupid = request.getParameter("groupid");
    QuestionsGroupForm questionForm = (QuestionsGroupForm)form;
    
    QuestionGroups queGroup = BOFactory.getQuestionBO().getQuestionsByQuestionGroup(new Long(groupid).longValue());
    questionForm.setQuestionGroup(queGroup);
    questionForm.setQuestionsList(queGroup.getQuestions());
    return mapping.findForward("loadQuestions");
  }
  
  public ActionForward createnewQuestion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    User user = (User)request.getSession().getAttribute("user_data");
    String name = request.getParameter("name");
    String description = request.getParameter("description");
    String quesgroupId = request.getParameter("quesgroupId");
    QuestionsGroupForm questionForm = (QuestionsGroupForm)form;
    QuestionGroups queGroup = new QuestionGroups();
    questionForm.setQuestiongroupName(name);
    questionForm.setQuestiongroupDesc(description);
    List questionList = new ArrayList();
    questionList = BOFactory.getQuestionBO().getAllQuestions(user.getSuper_user_key());
    
    queGroup = BOFactory.getQuestionBO().getQuestionsByQuestionGroup(new Long(quesgroupId).longValue());
    questionForm.setQuestions(questionList);
    questionForm.setQuestionbygroup(queGroup.getQuestions());
    questionForm.setQuestiongroupId(new Long(quesgroupId).longValue());
    request.setAttribute("createnewquestion", "yes");
    request.setAttribute("questionHome", "questiongroup");
    request.setAttribute("saveQuestionsGroup", "yes");
    return mapping.findForward("createQuestionGroup");
  }
  
  public ActionForward questionsgrouplist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    QuestionsGroupForm questionForm = (QuestionsGroupForm)form;
    request.setAttribute("questionHome", "questiongroup");
    return mapping.findForward("questionsgrouplist");
  }
  
  public ActionForward addQuestion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside addQuestion method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user = (User)request.getSession().getAttribute("user_data");
    String questiongroupid = request.getParameter("questiongroupid");
    String questionid = request.getParameter("questionid");
    QuestionsGroupForm questionForm = (QuestionsGroupForm)form;
    QuestionGroups queGroup = new QuestionGroups();
    Questions question = new Questions();
    question = BOFactory.getQuestionBO().getQuestiondetails(questionid);
    List questionList = new ArrayList();
    questionList = BOFactory.getQuestionBO().getAllQuestions(user.getSuper_user_key());
    
    queGroup = BOFactory.getQuestionBO().getQuestionsByQuestionGroup(new Long(questiongroupid).longValue());
    List questionList1 = queGroup.getQuestions();
    List queLIst = new ArrayList();
    String checked = "false";
    if ((questionList1 != null) && (questionList1.size() > 0)) {
      for (int i = 0; i < questionList1.size(); i++)
      {
        Questions questions1 = (Questions)questionList1.get(i);
        Questions questions2 = new Questions();
        if ((checked == "false") && 
          (questions1.getQuestionId() == new Long(questionid).longValue())) {
          checked = "true";
        }
        questions2.setQuestionId(questions1.getQuestionId());
        
        queLIst.add(questions2);
      }
    }
    if (checked == "false")
    {
      queLIst.add(question);
      if (StringUtils.isNullOrEmpty(queGroup.getQuestionsSeq())) {
        queGroup.setQuestionsSeq(String.valueOf(question.getQuestionId()));
      } else {
        queGroup.setQuestionsSeq(queGroup.getQuestionsSeq() + "," + String.valueOf(question.getQuestionId()));
      }
    }
    queGroup.setQuestions(queLIst);
    
    queGroup = BOFactory.getQuestionBO().updateQuestionsGroup(queGroup);
    toForm(questionForm, queGroup);
    queGroup = BOFactory.getQuestionBO().getQuestionsByQuestionGroup(new Long(questiongroupid).longValue());
    List questionList2 = queGroup.getQuestions();
    questionForm.setQuestionbygroup(questionList2);
    questionForm.setQuestions(questionList);
    questionForm.setQuestionbygroup(queGroup.getQuestions());
    String readpreview = request.getParameter("readPreview");
    questionForm.setReadPreview(readpreview);
    request.setAttribute("questionnaireupdated", "yes");
    request.setAttribute("saveQuestionsGroup", "yes");
    request.setAttribute("questionHome", "questiongroup");
    return mapping.findForward("createQuestionGroup");
  }
  
  public ActionForward editQuestion1(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside editQuestion1 method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String questionId = request.getParameter("questionid");
    String questiongroupId = request.getParameter("questiongroupid");
    String questiongroupname = request.getParameter("questiongroupname");
    QuestionsGroupForm questionForm = (QuestionsGroupForm)form;
    questionForm.setQuestionId(new Long(questionId).longValue());
    questionForm.setQuestiongroupId(new Long(questiongroupId).longValue());
    questionForm.setQuestiongroupName(questiongroupname);
    request.setAttribute("editquestion", "yes");
    request.setAttribute("questionHome", "questiongroup");
    request.setAttribute("saveQuestionsGroup", "yes");
    return mapping.findForward("createQuestionGroup");
  }
  
  public ActionForward editQuestion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside editQuestion method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user = (User)request.getSession().getAttribute("user_data");
    String questionId = request.getParameter("questionid");
    String questiongroupId = request.getParameter("questiongroupid");
    String questiongroupname = request.getParameter("questiongroupname");
    QuestionsGroupForm questionForm = (QuestionsGroupForm)form;
    Questions que = BOFactory.getQuestionBO().getQuestiondetails(questionId);
    
    QuestionGroups queGroup = BOFactory.getQuestionBO().getQuestionsByQuestionGroup(new Long(questiongroupId).longValue());
    List questionList2 = queGroup.getQuestions();
    List queoptList = new ArrayList();
    queoptList = BOFactory.getQuestionBO().getQuestionsoptionDetails(questionId);
    questionForm.setQuestionId(new Long(questionId).longValue());
    questionForm.setQuestionName(que.getQuestionName());
    questionForm.setCorrectAns(que.getCorrectAns());
    questionForm.setTypeVal(que.getTypeVal());
    questionForm.setCorrectAnsDate(String.valueOf(que.getCorrectAnsDate()));
    questionForm.setQuestiongroupId(new Long(questiongroupId).longValue());
    
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
      questionForm.setAnswerOption(optionval);
    }
    if (que.getCorrectAnsDate() != null)
    {
      String datepattern = Constant.getValue("defaultdateformat");
      if (user != null) {
        datepattern = DateUtil.getDatePatternFormat(user.getLocale());
      }
      questionForm.setCorrectAnsDate(DateUtil.convertDateToStringDate(que.getCorrectAnsDate(), datepattern));
    }
    questionForm.setQuestiongroupName("aa");
    questionForm.setQuestiongroupDesc(queGroup.getQuestiongroupDesc());
    questionForm.setQuestionbygroup(questionList2);
    request.setAttribute("editquestion", "yes");
    request.setAttribute("questionHome", "questiongroup");
    request.setAttribute("saveQuestionsGroup", "yes");
    request.setAttribute("updatequestion", "yes");
    return mapping.findForward("createQuestionpage");
  }
  
  public ActionForward saveQuestionsGroup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveQuestionsGroup method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user = (User)request.getSession().getAttribute("user_data");
    QuestionsGroupForm questionForm = (QuestionsGroupForm)form;
    QuestionGroups queGroup = new QuestionGroups();
    toValue(queGroup, questionForm);
    
    queGroup.setSuper_user_key(user.getSuper_user_key());
    queGroup = BOFactory.getQuestionBO().saveQuestionGroup(queGroup);
    List questionList = new ArrayList();
    questionList = BOFactory.getQuestionBO().getAllQuestions(user.getSuper_user_key());
    List questionList1 = new ArrayList();
    queGroup = BOFactory.getQuestionBO().getQuestionsByQuestionGroup(queGroup.getQuestiongroupId());
    toForm(questionForm, queGroup);
    if (questionList.size() > 0) {
      for (int i = 0; i < questionList.size(); i++)
      {
        Questions question = (Questions)questionList.get(i);
        Questions question1 = new Questions();
        question1.setQuestionId(question.getQuestionId());
        question1.setQuestionName(question.getQuestionName());
        questionList1.add(question1);
      }
    }
    questionForm.setQuestions(questionList1);
    questionForm.setQuestionbygroup(queGroup.getQuestions());
    questionForm.setQuestions(questionList);
    String readpreview = request.getParameter("readPreview");
    questionForm.setReadPreview(readpreview);
    request.setAttribute("saveQuestionsGroup", "yes");
    request.setAttribute("questionHome", "questiongroup");
    return mapping.findForward("createQuestionGroup");
  }
  
  public ActionForward backAddQuestionpage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside backAddQuestionpage method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user = (User)request.getSession().getAttribute("user_data");
    String questiongroupid = request.getParameter("questiongroupId");
    QuestionsGroupForm questionForm = (QuestionsGroupForm)form;
    QuestionGroups queGroup1 = BOFactory.getQuestionBO().getQuestionsByQuestionGroup(new Long(questiongroupid).longValue());
    List questionList = queGroup1.getQuestions();
    toForm(questionForm, queGroup1);
    List questionList1 = BOFactory.getQuestionBO().getAllQuestions(user.getSuper_user_key());
    questionForm.setQuestions(questionList1);
    questionForm.setQuestionbygroup(queGroup1.getQuestions());
    String readpreview = request.getParameter("readPreview");
    questionForm.setReadPreview(readpreview);
    request.setAttribute("saveQuestionsGroup", "yes");
    request.setAttribute("questionHome", "questiongroup");
    return mapping.findForward("createQuestionGroup");
  }
  
  public ActionForward questionnaireDetailspage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside backAddQuestionpage method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user = (User)request.getSession().getAttribute("user_data");
    String questiongroupid = request.getParameter("questiongroupId");
    QuestionsGroupForm questionForm = (QuestionsGroupForm)form;
    QuestionGroups queGroup1 = BOFactory.getQuestionBO().getQuestionsByQuestionGroup(new Long(questiongroupid).longValue());
    List questionList = queGroup1.getQuestions();
    toForm(questionForm, queGroup1);
    List questionList1 = BOFactory.getQuestionBO().getAllQuestions(user.getSuper_user_key());
    questionForm.setQuestions(questionList1);
    questionForm.setQuestionbygroup(queGroup1.getQuestions());
    
    return mapping.findForward("questionnaireDetailspage");
  }
  
  public ActionForward editQuestiongroupNameDesc1(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside backAddQuestionpage method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user = (User)request.getSession().getAttribute("user_data");
    String questiongroupid = request.getParameter("questiongroupId");
    QuestionsGroupForm questionForm = (QuestionsGroupForm)form;
    QuestionGroups queGroup1 = BOFactory.getQuestionBO().getQuestionsByQuestionGroup(new Long(questiongroupid).longValue());
    List questionList = queGroup1.getQuestions();
    toForm(questionForm, queGroup1);
    
    List questionList1 = BOFactory.getQuestionBO().getAllQuestions(user.getSuper_user_key());
    questionForm.setQuestions(questionList1);
    questionForm.setQuestionbygroup(queGroup1.getQuestions());
    String readpreview = request.getParameter("readPreview");
    questionForm.setReadPreview(readpreview);
    request.setAttribute("editnamedesc", "yes");
    request.setAttribute("saveQuestionsGroup", "yes");
    request.setAttribute("questionHome", "questiongroup");
    return mapping.findForward("createQuestionGroup");
  }
  
  public ActionForward editQuestiongroupNameDesc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside backAddQuestionpage method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user = (User)request.getSession().getAttribute("user_data");
    String questiongroupid = request.getParameter("questiongroupId");
    QuestionsGroupForm questionForm = (QuestionsGroupForm)form;
    QuestionGroups queGroup1 = BOFactory.getQuestionBO().getQuestionsByQuestionGroup(new Long(questiongroupid).longValue());
    List questionList = queGroup1.getQuestions();
    toForm(questionForm, queGroup1);
    
    List questionList1 = BOFactory.getQuestionBO().getAllQuestions(user.getSuper_user_key());
    questionForm.setQuestions(questionList1);
    questionForm.setQuestionbygroup(queGroup1.getQuestions());
    request.setAttribute("quesgroupedit", "yes");
    request.setAttribute("saveQuestionsGroup", "yes");
    request.setAttribute("questionHome", "questiongroup");
    return mapping.findForward("editQuestionGroup");
  }
  
  public ActionForward createQuestiongroup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside createQuestiongroup method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user = (User)request.getSession().getAttribute("user_data");
    
    QuestionsGroupForm questionForm = (QuestionsGroupForm)form;
    request.setAttribute("questionHome", "questiongroup");
    return mapping.findForward("editnameforquesgroup");
  }
  
  public ActionForward updategroupNameDesc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updategroupNameDesc method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user = (User)request.getSession().getAttribute("user_data");
    String questiongroupid = request.getParameter("questiongroupid");
    String questiongroupname = request.getParameter("questiongroupname");
    String questiongroupdesc = request.getParameter("questiongroupdesc");
    QuestionsGroupForm questionForm = (QuestionsGroupForm)form;
    QuestionGroups queGroup1 = BOFactory.getQuestionBO().getQuestionsByQuestionGroup(new Long(questiongroupid).longValue());
    queGroup1.setQuestiongroupName(questiongroupname);
    queGroup1.setQuestiongroupDesc(questiongroupdesc);
    queGroup1 = BOFactory.getQuestionBO().updateQuestionsGroup(queGroup1);
    List questionList = queGroup1.getQuestions();
    toForm(questionForm, queGroup1);
    List questionList1 = BOFactory.getQuestionBO().getAllQuestions(user.getSuper_user_key());
    questionForm.setQuestions(questionList1);
    questionForm.setQuestionbygroup(queGroup1.getQuestions());
    request.setAttribute("saveQuestionsGroup", "yes");
    request.setAttribute("questionHome", "questiongroup");
    return mapping.findForward("readonlyNameDescQoestionGroup");
  }
  
  public ActionForward backAddQuestionpage1(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside backAddQuestionpage method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user = (User)request.getSession().getAttribute("user_data");
    String questiongroupid = request.getParameter("questiongroupId");
    QuestionsGroupForm questionForm = (QuestionsGroupForm)form;
    QuestionGroups queGroup1 = BOFactory.getQuestionBO().getQuestionsByQuestionGroup(new Long(questiongroupid).longValue());
    List questionList = queGroup1.getQuestions();
    toForm(questionForm, queGroup1);
    
    List questionList1 = BOFactory.getQuestionBO().getAllQuestions(user.getSuper_user_key());
    questionForm.setQuestions(questionList1);
    questionForm.setQuestionbygroup(queGroup1.getQuestions());
    String readpreview = request.getParameter("readPreview");
    questionForm.setReadPreview(readpreview);
    request.setAttribute("saveQuestionsGroup", "yes");
    request.setAttribute("questionHome", "questiongroup");
    return mapping.findForward("addquestionpage");
  }
  
  public ActionForward saveQuestionwithgroup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveQuestionwithgroup method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user = (User)request.getSession().getAttribute("user_data");
    String answeroption = request.getParameter("answerOption");
    String correctanswer = request.getParameter("correctAns");
    String questiongroupid = request.getParameter("questiongroupId");
    QuestionsGroupForm questionForm = (QuestionsGroupForm)form;
    QuestionGroups queGroup = new QuestionGroups();
    Questions question = new Questions();
    question.setQuestionName(questionForm.getQuestionName());
    question.setTypeVal(questionForm.getTypeVal());
    question.setQnsType("SLAVE");
    question.setStatus("A");
    
    question.setCorrectAns(questionForm.getCorrectAns());
    question.setSuper_user_key(user.getSuper_user_key());
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
    if (StringUtils.compare(questionForm.getTypeVal(), "date"))
    {
      if (!StringUtils.isNullOrEmpty(questionForm.getCorrectAnsDate()))
      {
        String datepattern = DateUtil.getDatePatternFormat(user.getLocale());
        String converteddate = DateUtil.convertFromTimezoneToTimezoneDate(questionForm.getCorrectAnsDate(), datepattern, user.getTimezone().getTimezoneCode(), TimeZone.getDefault().getID());
        
        logger.info("converteddate" + converteddate);
        
        Calendar cal = DateUtil.convertStringDateToCalendar(converteddate, datepattern);
        
        question.setCorrectAnsDate(cal.getTime());
      }
    }
    else {
      question.setCorrectAnsDate(null);
    }
    List questionList = new ArrayList();
    questionList = BOFactory.getQuestionBO().getAllQuestions(user.getSuper_user_key());
    queGroup = BOFactory.getQuestionBO().getQuestionsByQuestionGroup(new Long(questiongroupid).longValue());
    List questionList1 = queGroup.getQuestions();
    List queLIst = new ArrayList();
    boolean isQnsNameSame = false;
    if ((questionList1 != null) && (questionList1.size() > 0)) {
      for (int i = 0; i < questionList1.size(); i++)
      {
        Questions questions1 = (Questions)questionList1.get(i);
        Questions questions2 = new Questions();
        if (StringUtils.compare(String.valueOf(questionForm.getQuestionName()), String.valueOf(questions1.getQuestionName()))) {
          isQnsNameSame = true;
        }
        questions2.setQuestionId(questions1.getQuestionId());
        queLIst.add(questions2);
      }
    }
    queLIst.add(question);
    queGroup.setQuestions(queLIst);
    if (!isQnsNameSame)
    {
      BOFactory.getQuestionBO().saveQuestionwithGroup(question, selectedoption, correctanswer);
      if (StringUtils.isNullOrEmpty(queGroup.getQuestionsSeq())) {
        queGroup.setQuestionsSeq(String.valueOf(question.getQuestionId()));
      } else {
        queGroup.setQuestionsSeq(queGroup.getQuestionsSeq() + "," + String.valueOf(question.getQuestionId()));
      }
      queGroup = BOFactory.getQuestionBO().updateQuestionsGroup(queGroup);
    }
    QuestionGroups queGroup1 = BOFactory.getQuestionBO().getQuestionsByQuestionGroup(new Long(questiongroupid).longValue());
    toForm(questionForm, queGroup);
    questionForm.setQuestions(queGroup1.getQuestions());
    questionForm.setQuestionbygroup(queGroup1.getQuestions());
    questionForm.setQuestions(queGroup1.getQuestions());
    String readpreview = request.getParameter("readPreview");
    questionForm.setReadPreview(readpreview);
    request.setAttribute("saveQuestionsGroup", "yes");
    request.setAttribute("questionHome", "questiongroup");
    return mapping.findForward("createQuestionGroup");
  }
  
  public ActionForward deletequestionfromgroup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deletequestionfromgroup method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user = (User)request.getSession().getAttribute("user_data");
    String questionid = request.getParameter("questionid");
    String questiongroupid = request.getParameter("questiongroupid");
    QuestionsGroupForm questionForm = (QuestionsGroupForm)form;
    List queoptList = new ArrayList();
    List questionList = new ArrayList();
    questionList = BOFactory.getQuestionBO().getAllQuestions(user.getSuper_user_key());
    QuestionGroups queGroup = BOFactory.getQuestionBO().getQuestionsByQuestionGroup(new Long(questiongroupid).longValue());
    List questionList1 = queGroup.getQuestions();
    List queLIst = new ArrayList();
    if ((questionList1 != null) && (questionList1.size() > 0)) {
      for (int i = 0; i < questionList1.size(); i++)
      {
        Questions questions1 = (Questions)questionList1.get(i);
        Questions questions2 = new Questions();
        if (!StringUtils.compare(String.valueOf(questionid), String.valueOf(questions1.getQuestionId())))
        {
          questions2.setQuestionId(questions1.getQuestionId());
          queLIst.add(questions2);
        }
      }
    }
    queGroup.setQuestions(queLIst);
    if (!StringUtils.isNullOrEmpty(queGroup.getQuestionsSeq()))
    {
      StringTokenizer tockens = new StringTokenizer(queGroup.getQuestionsSeq(), ",");
      
      String newval = "";
      while (tockens.hasMoreTokens())
      {
        String tmp = tockens.nextToken();
        if (!tmp.equals(questionid)) {
          newval = newval + tmp + ",";
        }
      }
      if (!StringUtils.isNullOrEmpty(newval)) {
        newval = newval.substring(0, newval.length() - 1);
      }
      queGroup.setQuestionsSeq(newval);
    }
    queGroup = BOFactory.getQuestionBO().updateQuestionsGroup(queGroup);
    QuestionGroups queGroup1 = BOFactory.getQuestionBO().getQuestionsByQuestionGroup(new Long(questiongroupid).longValue());
    toForm(questionForm, queGroup1);
    questionForm.setQuestions(questionList);
    questionForm.setQuestionbygroup(queGroup1.getQuestions());
    questionForm.setQuestions(questionList);
    request.setAttribute("questionnaireupdated", "yes");
    request.setAttribute("saveQuestionsGroup", "yes");
    request.setAttribute("questionHome", "questiongroup");
    return mapping.findForward("createQuestionGroup");
  }
  
  public ActionForward updateQuestion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updateQuestion method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user = (User)request.getSession().getAttribute("user_data");
    String answeroption = request.getParameter("answerOption");
    String correctanswer = request.getParameter("correctAns");
    String questionid = request.getParameter("questionid");
    String questiongroupid = request.getParameter("questiongroupid");
    
    Questions que = BOFactory.getQuestionBO().getQuestiondetails(questionid);
    QuestionsGroupForm questionForm = (QuestionsGroupForm)form;
    QuestionGroups queGroup = BOFactory.getQuestionBO().getQuestionsGroupDetails(questiongroupid);
    que.setQuestionName(questionForm.getQuestionName());
    que.setTypeVal(questionForm.getTypeVal());
    que.setCorrectAns(questionForm.getCorrectAns());
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
    queoptList = BOFactory.getQuestionBO().getQuestionsoptionDetails(questionid);
    if ((queoptList != null) && (queoptList.size() > 0)) {
      for (int k = 0; k < queoptList.size(); k++)
      {
        QuestionOptions queoption = (QuestionOptions)queoptList.get(k);
        BOFactory.getQuestionBO().deleteQuestionOption(queoption);
      }
    }
    BOFactory.getQuestionBO().updateQuestions(que, selectedoption, correctanswer);
    List questionList = new ArrayList();
    questionList = BOFactory.getQuestionBO().getAllQuestions(user.getSuper_user_key());
    
    QuestionGroups queGroup1 = BOFactory.getQuestionBO().getQuestionsByQuestionGroup(new Long(questiongroupid).longValue());
    toForm(questionForm, queGroup);
    questionForm.setQuestions(questionList);
    questionForm.setQuestionbygroup(queGroup1.getQuestions());
    questionForm.setQuestions(questionList);
    
    request.setAttribute("saveQuestionsGroup", "yes");
    request.setAttribute("questionHome", "questiongroup");
    return mapping.findForward("createQuestionGroup");
  }
  
  public ActionForward questionnaire(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside questionnaire method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    QuestionsGroupForm questiongroupForm = (QuestionsGroupForm)form;
    String qnsGrpAppId = request.getParameter("gpid");
    String applicantId = request.getParameter("applicantid");
    String uuid = request.getParameter("uuid");
    QuestionGroupApplicants qngrpapp = BOFactory.getQuestionBO().getQuestionGroupApplicants(new Long(qnsGrpAppId).longValue(), new Long(applicantId).longValue(), uuid);
    if (qngrpapp != null)
    {
      QuestionGroups que = qngrpapp.getQuestiongroup();
      toForm(questiongroupForm, que);
      questiongroupForm.setQuestionGroup(que);
      questiongroupForm.setHeadingComment(qngrpapp.getHeadingcomments());
      
      List answersList = BOFactory.getQuestionBO().getQuestionnaireAnsList(qngrpapp.getQnsGrpAppId());
      Map<Long, QuestionnaireAnswers> answerMap = new HashMap();
      for (int i = 0; i < answersList.size(); i++)
      {
        QuestionnaireAnswers qnsa = (QuestionnaireAnswers)answersList.get(i);
        answerMap.put(Long.valueOf(qnsa.getQuestionId()), qnsa);
      }
      questiongroupForm.setAnswerMap(answerMap);
      questiongroupForm.setQuestionnaireStatus(qngrpapp.getResult());
    }
    else
    {
      request.setAttribute("questionnairedeleted", "yes");
      return mapping.findForward("questionnaireDelete");
    }
    return mapping.findForward("questionnaire");
  }
  
  public ActionForward questionsGroupdelete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside questionsGroupdelete method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    String questiongroupId = request.getParameter("questiongroupId");
    QuestionGroups que = BOFactory.getQuestionBO().getQuestionsGroupDetails(questiongroupId);
    que.setStatus("D");
    que = BOFactory.getQuestionBO().updateQuestionsGroup(que);
    
    QuestionsGroupForm questionForm = (QuestionsGroupForm)form;
    questionForm.setQuestiongroupId(0L);
    request.setAttribute("deletequestiongroup", "yes");
    request.setAttribute("questionHome", "questiongroup");
    return mapping.findForward("questionsgrouplist");
  }
  
  public ActionForward questionsGroupsummary(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside questionsGroupsummary method");
    String quesgroupId = request.getParameter("questiongroupId");
    QuestionsGroupForm questionForm = (QuestionsGroupForm)form;
    questionForm.setQuestiongroupId(new Long(quesgroupId).longValue());
    logger.info("<<< quesgroupId >>> " + quesgroupId);
    QuestionGroups queGroup1 = BOFactory.getQuestionBO().getQuestionsByQuestionGroup(new Long(quesgroupId).longValue());
    List questionList = queGroup1.getQuestions();
    toForm(questionForm, queGroup1);
    questionForm.setQuestions(questionList);
    
    return mapping.findForward("questionsGroupsummary");
  }
  
  public void toForm(QuestionsGroupForm questiongroupForm, QuestionGroups que)
  {
    questiongroupForm.setQuestiongroupId(que.getQuestiongroupId());
    questiongroupForm.setQuestiongroupName(que.getQuestiongroupName());
    questiongroupForm.setQuestiongroupDesc(que.getQuestiongroupDesc());
    questiongroupForm.setStatus(que.getStatus());
    questiongroupForm.setQuestionsSeq(que.getQuestionsSeq());
  }
  
  public void toValue(QuestionGroups que, QuestionsGroupForm questiongroupForm)
  {
    que.setQuestiongroupName(questiongroupForm.getQuestiongroupName());
    que.setQuestiongroupDesc(questiongroupForm.getQuestiongroupDesc());
    
    que.setStatus("A");
  }
}
