package com.action;

import com.bean.EmailTemplates;
import com.bean.JobApplicant;
import com.bean.JobRequisition;
import com.bean.Organization;
import com.bean.OrganizationEmailTemplate;
import com.bean.User;
import com.bo.ApplicantBO;
import com.bo.BOFactory;
import com.bo.JobRequistionBO;
import com.common.Common;
import com.dao.TaskDAO;
import com.form.MassEmailForm;
import com.manager.EmailTaskManager;
import com.util.EmailTask;
import com.util.StringUtils;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class MassEmailAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(MassEmailAction.class);
  private Object Enumeration;
  
  public ActionForward massEmailListpage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside resumatorListpage method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    MassEmailForm massemailform = (MassEmailForm)form;
    List jobList = BOFactory.getJobRequistionBO().getActiveNotDeletedRequisitionsList(user1.getSuper_user_key());
    try
    {
      OrganizationEmailTemplate orgemtmpl = TaskDAO.getOrganizationEmailTemplate(user1.getOrganization().getOrgId(), Common.MASS_EMAIL_TO_APPLICANT);
      logger.info("44444444444444444444" + orgemtmpl);
      
      EmailTemplates emptl = orgemtmpl.getEmailtemplate();
      
      String content = emptl.getEmailtemplateData();
      massemailform.setEmailtemplateData(content);
      massemailform.setSubjectemail(emptl.getEmailSubject());
      request.setAttribute("emailtemplateid", Long.valueOf(emptl.getEmailtemplateId()));
    }
    catch (Exception e)
    {
      logger.info("exception on applicantlistformassemail", e);
    }
    massemailform.setJobList(jobList);
    return mapping.findForward("massemailListpage");
  }
  
  public ActionForward previewMessage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside previewMessage method");
    
    MassEmailForm massemailform = (MassEmailForm)form;
    
    String emailtemplateid = request.getParameter("emailtemplateid");
    logger.info("emailtemplateid " + emailtemplateid);
    logger.info("Message to preview : " + emailtemplateid);
    request.setAttribute("emailtemplateid", emailtemplateid);
    
    return mapping.findForward("previewMessage");
  }
  
  public ActionForward refressmassEmailListpage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside refressresumatorListpage method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    MassEmailForm massemailform = (MassEmailForm)form;
    List jobList = BOFactory.getJobRequistionBO().getActiveNotDeletedRequisitionsList(user1.getSuper_user_key());
    massemailform.setJobList(jobList);
    List applicantList = new ArrayList();
    massemailform.setApplicantList(applicantList);
    try
    {
      OrganizationEmailTemplate orgemtmpl = TaskDAO.getOrganizationEmailTemplate(user1.getOrganization().getOrgId(), Common.MASS_EMAIL_TO_APPLICANT);
      logger.info("44444444444444444444" + orgemtmpl);
      
      EmailTemplates emptl = orgemtmpl.getEmailtemplate();
      
      String content = emptl.getEmailtemplateData();
      massemailform.setEmailtemplateData(content);
      massemailform.setSubjectemail(emptl.getEmailSubject());
      request.setAttribute("emailtemplateid", Long.valueOf(emptl.getEmailtemplateId()));
    }
    catch (Exception e)
    {
      logger.info("exception on applicantlistformassemail", e);
    }
    return mapping.findForward("applicantlistformassemailrefress");
  }
  
  public ActionForward searchapplicantForMassEmail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchapplicantForBathEmail method");
    String jobId = request.getParameter("jobId");
    String interviewstates = request.getParameter("interviewstates");
    String checkornot = request.getParameter("checkornot");
    User user1 = (User)request.getSession().getAttribute("user_data");
    
    List<String> selecteinterviewstate = new ArrayList();
    if ((interviewstates != null) && (interviewstates.length() > 0))
    {
      String[] column = StringUtils.tokenize(interviewstates, ",");
      for (int i = 0; i < column.length; i++) {
        selecteinterviewstate.add(column[i]);
      }
    }
    MassEmailForm massemailform = (MassEmailForm)form;
    massemailform.setApplicantList(BOFactory.getApplicantBO().getApplicantsForBathEmail(jobId, selecteinterviewstate));
    if (checkornot.equals("unchecked")) {
      request.setAttribute("unchecked", "yes");
    }
    JobRequisition jb = BOFactory.getJobRequistionBO().getJobRequision(jobId);
    try
    {
      OrganizationEmailTemplate orgemtmpl = TaskDAO.getOrganizationEmailTemplate(user1.getOrganization().getOrgId(), Common.MASS_EMAIL_TO_APPLICANT);
      logger.info("44444444444444444444" + orgemtmpl);
      
      EmailTemplates emptl = orgemtmpl.getEmailtemplate();
      
      String content = emptl.getEmailtemplateData();
      massemailform.setEmailtemplateData(content);
      massemailform.setSubjectemail(emptl.getEmailSubject());
      request.setAttribute("emailtemplateid", Long.valueOf(emptl.getEmailtemplateId()));
    }
    catch (Exception e)
    {
      logger.info("exception on applicantlistformassemail", e);
    }
    massemailform.setJobId(new Long(jobId).longValue());
    
    return mapping.findForward("applicantlistformassemail");
  }
  
  public ActionForward sentEmailToAllApplicants(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside sentEmailToAllApplicants method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    MassEmailForm massemailform = (MassEmailForm)form;
    String[] selectedapplicantsid = null;
    String applicantids = request.getParameter("applicantids");
    String subjectemail = request.getParameter("subjectemail");
    String messageemail = request.getParameter("messageemail");
    String jobId = request.getParameter("jobId");
    String interviewstates = request.getParameter("interviewstates");
    logger.info("Inside sentEmailToAllApplicants method messageemail" + messageemail);
    String newmessagemail = messageemail.replace("HashHashsymbol", "##");
    logger.info("Inside sentEmailToAllApplicants method newmessagemail" + newmessagemail);
    JobRequisition jb = BOFactory.getJobRequistionBO().getJobRequision(jobId);
    

































    List<String> selecteinterviewstate = new ArrayList();
    if ((interviewstates != null) && (interviewstates.length() > 0))
    {
      String[] column = StringUtils.tokenize(interviewstates, ",");
      for (int i = 0; i < column.length; i++) {
        selecteinterviewstate.add(column[i]);
      }
    }
    massemailform.setEmailtemplateData(newmessagemail);
    massemailform.setSubjectemail(subjectemail);
    massemailform.setJobId(new Long(jobId).longValue());
    massemailform.setApplicantList(BOFactory.getApplicantBO().getApplicantsForBathEmail(jobId, selecteinterviewstate));
    if (applicantids.length() > 0)
    {
      String[] column = StringUtils.tokenize(applicantids, ",");
      selectedapplicantsid = new String[column.length];
      for (int i = 0; i < column.length; i++) {
        selectedapplicantsid[i] = column[i];
      }
    }
    for (int i = 0; i < selectedapplicantsid.length; i++)
    {
      JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetails(selectedapplicantsid[i]);
      


      String[] tonew = { applicant.getEmail() };
      String[] ccnew = { applicant.getEmail() };
      String from = "\"" + user1.getFirstName() + " " + user1.getLastName() + "\"" + " " + "<" + user1.getEmailId() + ">";
      
      EmailTask emailtasknew = new EmailTask(from, tonew, ccnew, null, from, "dummysubject", null, "dummybody", null, 0, null);
      emailtasknew.setFunctionType(Common.MASS_EMAIL_TO_APPLICANT);
      emailtasknew.setUser(user1);
      emailtasknew.setMassemailapplicant(applicant);
      emailtasknew.setJobreq(jb);
      emailtasknew.setMessagemassemailapplicant(newmessagemail);
      emailtasknew.setSubjectmassemailapplicant(subjectemail);
      EmailTaskManager.sendEmail(emailtasknew);
    }
    return mapping.findForward("successfulmessageforemailsending");
  }
}
