package com.action;

import com.bean.ApplicantReferencee;
import com.bean.ApplicantUser;
import com.bean.JobApplicant;
import com.bean.QuestionAnswers;
import com.bean.QuestionGroups;
import com.bean.Questions;
import com.bean.User;
import com.bo.ApplicantBO;
import com.bo.BOFactory;
import com.bo.LovBO;
import com.bo.LovTXBO;
import com.common.AppContextUtil;
import com.form.ReferenceForm;
import com.util.StringUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.context.ApplicationContext;

public class ReferenceAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(ReferenceAction.class);
  
  public ActionForward addreferencescr(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside addreferencescr method");
    String applicantId = request.getParameter("applicantId");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    logger.info("Inside addreferencescr method ..1");
    ReferenceForm applicantform = (ReferenceForm)form;
    logger.info("Inside addreferencescr method ..2");
    applicantform.setApplicantId(new Long(applicantId).longValue());
    logger.info("Inside addreferencescr method ..3");
    return mapping.findForward("addreferencescr");
  }
  
  public ActionForward startrefcheck(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside startrefcheck method");
    String applicantId = request.getParameter("applicantId");
    String questiongroupid = request.getParameter("questiongroupid");
    String referenceId = request.getParameter("referenceId");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ReferenceForm applicantform = (ReferenceForm)form;
    applicantform.setApplicantId(new Long(applicantId).longValue());
    applicantform.setQuestiongroupid(new Long(questiongroupid).longValue());
    QuestionGroups qnsgroup = BOFactory.getLovBO().getQuestionGroup(questiongroupid);
    
    applicantform.setQnsgroup(qnsgroup);
    applicantform.setApplicantReferenceId(new Long(referenceId).longValue());
    
    return mapping.findForward("startrefcheck");
  }
  
  public ActionForward referencecheckfeedbacks(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside referencecheckfeedbacks method");
    String applicantId = request.getParameter("applicantId");
    String applicantreferenceId = request.getParameter("referenceId");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ReferenceForm applicantform = (ReferenceForm)form;
    applicantform.setApplicantId(new Long(applicantId).longValue());
    applicantform.setApplicantReferenceId(new Long(applicantreferenceId).longValue());
    
    List questionanswerList = BOFactory.getLovBO().getQuestionanswerListByApplicantRefId(applicantreferenceId);
    
    applicantform.setQuestionanswersList(questionanswerList);
    
    ApplicantReferencee applicantref = BOFactory.getLovBO().getReferencedetails(applicantreferenceId);
    
    applicantform.fromValue(applicantref, request);
    
    return mapping.findForward("referencecheckfeedbacks");
  }
  
  public ActionForward saverefcheck(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saverefcheck method");
    String applicantId = request.getParameter("applicantId");
    String questiongroupid = request.getParameter("questiongroupid");
    String referenceId = request.getParameter("referenceId");
    String yourfeedback = request.getParameter("yourfeedback");
    String status = request.getParameter("status");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ReferenceForm applicantform = (ReferenceForm)form;
    

    Map reqkeys = request.getParameterMap();
    
    Set ketset = reqkeys.keySet();
    Iterator itr = ketset.iterator();
    
    List qnsanswers = new ArrayList();
    while (itr.hasNext())
    {
      String key = (String)itr.next();
      
      String value = request.getParameter(key);
      if ((!StringUtils.isNullOrEmpty(key)) && (key.startsWith("qns_")))
      {
        key = key.substring(4, key.length());
        Questions qns = BOFactory.getLovBO().getQuestion(key);
        QuestionAnswers qanswer = new QuestionAnswers();
        qanswer.setAnswer(value);
        qanswer.setAnswerType("ref");
        qanswer.setApplicantId(new Long(applicantId).longValue());
        qanswer.setQuestiongroupid(new Long(questiongroupid).longValue());
        qanswer.setApplicantrefId(new Long(referenceId).longValue());
        qanswer.setQuestionId(qns.getQuestionId());
        qanswer.setQuestionName(qns.getQuestionName());
        qanswer.setQuestionType(qns.getQuestionType());
        qanswer.setOptionValue1(qns.getOptionValue1());
        qanswer.setOptionValue2(qns.getOptionValue2());
        qanswer.setOptionValue3(qns.getOptionValue3());
        qanswer.setOptionValue4(qns.getOptionValue4());
        qanswer.setOptionValue5(qns.getOptionValue5());
        
        qnsanswers.add(qanswer);
      }
    }
    BOFactory.getLovBO().saveanswers(qnsanswers);
    ApplicantReferencee appref = BOFactory.getLovBO().getReferencedetails(referenceId);
    appref.setIsVerificationDone("Y");
    appref.setUpdatedBy(user1.getUserName());
    appref.setUpdatedDate(new Date());
    appref.setReferenceeFeedback(yourfeedback);
    appref.setStatus(status);
    


    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetails(String.valueOf(appref.getApplicantId()));
    

    ApplicationContext appContext = AppContextUtil.getAppcontext();
    LovTXBO lovtxbo = (LovTXBO)appContext.getBean("lovtxbo");
    appref = lovtxbo.saverefcheck(appref, applicant, user1);
    

    applicantform.fromValue(appref, request);
    
    applicantform.setApplicantId(new Long(applicantId).longValue());
    applicantform.setQuestiongroupid(new Long(questiongroupid).longValue());
    
    request.setAttribute("refanswersaved", "yes");
    return mapping.findForward("startrefcheck");
  }
  
  public ActionForward editreference(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside editreference method");
    String referenceId = request.getParameter("referenceId");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ReferenceForm applicantform = (ReferenceForm)form;
    
    ApplicantReferencee applicantref = BOFactory.getLovBO().getReferencedetails(referenceId);
    applicantform.fromValue(applicantref, request);
    return mapping.findForward("addreferencescr");
  }
  
  public ActionForward savereference(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside savereference method");
    String applicantId = request.getParameter("applicantId");
    String assignedtoid = request.getParameter("assignedtoid");
    String assignedtoname = request.getParameter("assignedtoname");
    String questiongroupid = request.getParameter("questiongroupId");
    
    String questiongroupName = request.getParameter("questiongroupName");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ReferenceForm refform = (ReferenceForm)form;
    ApplicantReferencee applicantref = new ApplicantReferencee();
    if (refform.getReferenceeName() == null) {
      return null;
    }
    refform.toValue(applicantref, request);
    applicantref.setCreatedBy(user1.getCreatedBy());
    applicantref.setCreatedDate(new Date());
    applicantref.setApplicantId(new Long(applicantId).longValue());
    if (!StringUtils.isNullOrEmpty(assignedtoid)) {
      applicantref.setAssignedTo(new Long(assignedtoid).longValue());
    }
    applicantref.setAssignedToName(assignedtoname);
    if (!StringUtils.isNullOrEmpty(questiongroupid)) {
      applicantref.setQuestiongroupid(new Long(questiongroupid).longValue());
    }
    applicantref.setQuestiongroupName(questiongroupName);
    
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetails(applicantId);
    

    applicantref = BOFactory.getLovTXBO().savereference(applicantref, applicant, user1);
    
    refform.fromValue(applicantref, request);
    
    refform.setAssignedTo(applicantref.getAssignedTo());
    refform.setAssignedToName(applicantref.getAssignedToName());
    request.setAttribute("applicantrefsaved", "yes");
    return mapping.findForward("addreferencescr");
  }
  
  public ActionForward updatereference(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updatereference method");
    String referenceId = request.getParameter("referenceId");
    logger.info("Inside updatereference method555555555" + referenceId);
    String assignedtoid = request.getParameter("assignedtoid");
    String assignedtoname = request.getParameter("assignedtoname");
    String questiongroupId = request.getParameter("questiongroupId");
    String questiongroupName = request.getParameter("questiongroupName");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ReferenceForm refform = (ReferenceForm)form;
    ApplicantReferencee applicantref = BOFactory.getLovBO().getReferencedetails(referenceId);
    refform.toValue(applicantref, request);
    applicantref.setUpdatedBy(user1.getCreatedBy());
    applicantref.setUpdatedDate(new Date());
    if (!StringUtils.isNullOrEmpty(assignedtoid)) {
      applicantref.setAssignedTo(new Long(assignedtoid).longValue());
    }
    applicantref.setAssignedToName(assignedtoname);
    if (!StringUtils.isNullOrEmpty(questiongroupId)) {
      applicantref.setQuestiongroupid(new Long(questiongroupId).longValue());
    }
    applicantref.setQuestiongroupName(questiongroupName);
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetails(String.valueOf(applicantref.getApplicantId()));
    
    applicantref = BOFactory.getLovTXBO().updatereference(applicantref, applicant, user1, assignedtoid, assignedtoname);
    refform.fromValue(applicantref, request);
    refform.setAssignedTo(applicantref.getAssignedTo());
    refform.setAssignedToName(applicantref.getAssignedToName());
    request.setAttribute("applicantrefupdated", "yes");
    return mapping.findForward("addreferencescr");
  }
  
  public ActionForward deletereference(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deletereference method");
    String referenceId = request.getParameter("referenceId");
    User user1 = (User)request.getSession().getAttribute("user_data");
    
    ReferenceForm referenceform = (ReferenceForm)form;
    ApplicantReferencee applicantref = BOFactory.getLovBO().getReferencedetails(referenceId);
    
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetails(String.valueOf(applicantref.getApplicantId()));
    

    BOFactory.getLovTXBO().deletereference(applicantref, applicant, user1);
    
    List refList = BOFactory.getLovBO().getReferenceListByApplicantId(applicant.getApplicantId());
    if ((refList != null) && (refList.size() < 1))
    {
      applicant.setIsreferenceadded(0);
      BOFactory.getApplicantBO().updateApplicant(applicant);
    }
    referenceform.fromValue(applicantref, request);
    request.setAttribute("applicantrefdeleted", "yes");
    return mapping.findForward("addreferencescr");
  }
  
  public ActionForward addreferencescreen(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside addreferencescreen method");
    String applicantId = request.getParameter("applicantId");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    ReferenceForm referenceform = (ReferenceForm)form;
    
    referenceform.setApplicantId(new Long(applicantId).longValue());
    
    return mapping.findForward("addreferencescreen");
  }
  
  public ActionForward savereferenceData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside savereferenceData method");
    String applicantId = request.getParameter("applicantId");
    

    ApplicantUser user1 = (ApplicantUser)request.getSession().getAttribute("applicant_user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ReferenceForm refform = (ReferenceForm)form;
    ApplicantReferencee applicantref = new ApplicantReferencee();
    if (refform.getReferenceeName() == null) {
      return null;
    }
    refform.toValue(applicantref, request);
    applicantref.setCreatedBy(user1.getCreatedBy());
    applicantref.setCreatedDate(new Date());
    applicantref.setApplicantId(new Long(applicantId).longValue());
    



    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetails(applicantId);
    

    ApplicationContext appContext = AppContextUtil.getAppcontext();
    LovTXBO lovtxbo = (LovTXBO)appContext.getBean("lovtxbo");
    applicantref = lovtxbo.savereferenceData(applicantref, applicant, user1);
    
    refform.fromValue(applicantref, request);
    
    refform.setAssignedTo(applicantref.getAssignedTo());
    refform.setAssignedToName(applicantref.getAssignedToName());
    request.setAttribute("applicantrefsaved", "yes");
    return mapping.findForward("addreferencescreen");
  }
  
  public ActionForward editreferencescreen(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside editreferencescreen method");
    String referenceId = request.getParameter("referenceId");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ReferenceForm referenceform = (ReferenceForm)form;
    
    ApplicantReferencee applicantref = BOFactory.getLovBO().getReferencedetails(referenceId);
    referenceform.fromValue(applicantref, request);
    return mapping.findForward("addreferencescreen");
  }
  
  public ActionForward updatereferencescreen(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updatereferencescreen method");
    String referenceId = request.getParameter("referenceId");
    
    ApplicantUser user1 = (ApplicantUser)request.getSession().getAttribute("applicant_user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ReferenceForm refform = (ReferenceForm)form;
    ApplicantReferencee applicantref = BOFactory.getLovBO().getReferencedetails(referenceId);
    refform.toValue(applicantref, request);
    applicantref.setUpdatedBy(user1.getCreatedBy());
    applicantref.setUpdatedDate(new Date());
    


    logger.info("Inside updatereferencescreen method .. 1");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetails(String.valueOf(applicantref.getApplicantId()));
    
    ApplicationContext appContext = AppContextUtil.getAppcontext();
    LovTXBO lovtxbo = (LovTXBO)appContext.getBean("lovtxbo");
    applicantref = lovtxbo.updatereferencescreen(applicantref, applicant, user1);
    
    logger.info("Inside updatereferencescreen method .. 2");
    refform.fromValue(applicantref, request);
    logger.info("Inside updatereferencescreen method .. 3");
    
    request.setAttribute("applicantrefsaved", "yes");
    return mapping.findForward("addreferencescreen");
  }
}
