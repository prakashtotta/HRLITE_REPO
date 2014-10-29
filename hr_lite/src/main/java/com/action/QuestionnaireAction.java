package com.action;

import com.bean.QuestionGroupApplicants;
import com.bean.QuestionGroups;
import com.bo.BOFactory;
import com.bo.QuestionBO;
import com.form.QuestionsGroupForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class QuestionnaireAction
  extends CommonNoLoginAction
{
  protected static final Logger logger = Logger.getLogger(QuestionnaireAction.class);
  
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
    QuestionGroups que = qngrpapp.getQuestiongroup();
    toForm(questiongroupForm, que);
    questiongroupForm.setQuestionGroup(que);
    questiongroupForm.setHeadingComment(qngrpapp.getHeadingcomments());
    questiongroupForm.setQuestiongroupapplicantId(qngrpapp.getQnsGrpAppId());
    questiongroupForm.setApplicantId(new Long(applicantId).longValue());
    questiongroupForm.setUuid(uuid);
    if ((qngrpapp.getResult() != null) && (qngrpapp.getResult().equals("DONE"))) {
      request.setAttribute("questionnairesone", "yes");
    }
    return mapping.findForward("questionnaire");
  }
  
  public ActionForward savequestionnaire(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside savequestionnaire method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    QuestionsGroupForm questiongroupForm = (QuestionsGroupForm)form;
    String qnsGrpAppId = request.getParameter("gpid");
    String applicantId = request.getParameter("applicantid");
    String uuid = request.getParameter("uuid");
    QuestionGroupApplicants qngrpapp = BOFactory.getQuestionBO().getQuestionGroupApplicants(new Long(qnsGrpAppId).longValue(), new Long(applicantId).longValue(), uuid);
    
    boolean success = BOFactory.getQuestionBO().savequestionnaire(request, qngrpapp);
    if (success) {
      request.setAttribute("questionnairesone", "yes");
    }
    return mapping.findForward("questionnaire");
  }
  
  public void toForm(QuestionsGroupForm questiongroupForm, QuestionGroups que)
  {
    questiongroupForm.setQuestiongroupId(que.getQuestiongroupId());
    questiongroupForm.setQuestiongroupName(que.getQuestiongroupName());
    questiongroupForm.setQuestiongroupDesc(que.getQuestiongroupDesc());
    questiongroupForm.setStatus(que.getStatus());
    questiongroupForm.setQuestionsSeq(que.getQuestionsSeq());
  }
}
