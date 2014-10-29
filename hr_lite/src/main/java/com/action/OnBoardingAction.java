package com.action;

import com.bean.JobApplicant;
import com.bean.JobRequisition;
import com.bean.User;
import com.bean.onboard.OnBoardingTask;
import com.bean.onboard.OnBoardingTaskAttributeValues;
import com.bean.onboard.OnBoardingTaskDefinitions;
import com.bean.onboard.OnBoardingTemplate;
import com.bean.onboard.Onboarding;
import com.bo.ApplicantBO;
import com.bo.BOFactory;
import com.bo.JobRequistionBO;
import com.bo.OnBoardingTXBO;
import com.common.AppContextUtil;
import com.dao.OnBoardingDAO;
import com.form.OnBoardingForm;
import com.resources.Constant;
import com.util.StringUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.context.ApplicationContext;

public class OnBoardingAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(OnBoardingAction.class);
  
  public ActionForward initiateOnBoarding(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside initiateOnBoarding method");
    String applicantids = request.getParameter("applicantids");
    User user1 = (User)request.getSession().getAttribute("user_data");
    List applicantList = new ArrayList();
    List applicantListdetails = new ArrayList();
    if (!StringUtils.isNullOrEmpty(applicantids))
    {
      applicantids = applicantids.substring(0, applicantids.length() - 1);
      
      applicantList = StringUtils.tokenizeString(applicantids, ",");
    }
    logger.info("applicantids" + applicantids);
    for (int i = 0; i < applicantList.size(); i++)
    {
      String appliantId = (String)applicantList.get(i);
      JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetails(appliantId);
      applicantListdetails.add(applicant);
    }
    OnBoardingForm intform = (OnBoardingForm)form;
    intform.setApplicantList(applicantListdetails);
    
    List templateList = OnBoardingDAO.getOnBoardingTemplateList(user1.getSuper_user_key());
    intform.setOnboardingTemplateList(templateList);
    if (templateList.size() > 0)
    {
      OnBoardingTemplate template = (OnBoardingTemplate)templateList.get(0);
      intform.setTemplate(template);
      String js = generateJavaScript(template);
      request.setAttribute("jsscriptdata", js);
    }
    request.setAttribute("initiatedOBoarding", "no");
    return mapping.findForward("initiateOnBoarding");
  }
  
  public ActionForward loadingOnboardTaskDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside loadingOnboardTaskDetails method");
    String templateId = request.getParameter("templateId");
    
    OnBoardingForm intform = (OnBoardingForm)form;
    

    OnBoardingTemplate template = OnBoardingDAO.getOnBoardingTemplate(new Long(templateId).longValue());
    
    intform.setTemplate(template);
    String js = generateJavaScript(template);
    request.setAttribute("jsscriptdata", js);
    


    return mapping.findForward("loadingOnboardTaskDetails");
  }
  
  public ActionForward loadTask(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside loadTask method");
    String taskid = request.getParameter("taskid");
    String secureid = request.getParameter("secureid");
    

    OnBoardingForm intform = (OnBoardingForm)form;
    
    OnBoardingTask task = OnBoardingDAO.getOnBoardingTask(new Long(taskid).longValue(), secureid);
    
    intform.setOnboardingTask(task);
    
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(String.valueOf(task.getApplicantId()), task.getApplicantuuid());
    
    intform.setJobapplicant(applicant);
    
    return mapping.findForward("loadTask");
  }
  
  public ActionForward onboardCheckList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside onboardCheckList method");
    String applicantid = request.getParameter("applicantid");
    String uuid = request.getParameter("secureid");
    logger.info("applicantid" + applicantid);
    logger.info("uuid" + uuid);
    OnBoardingForm intform = (OnBoardingForm)form;
    


    Onboarding onboard = OnBoardingDAO.getOnBoarding(new Long(applicantid).longValue(), uuid);
    logger.info("onboard" + onboard);
    logger.info("onboard" + onboard.getOnboardingid());
    List onBoradTaskList = OnBoardingDAO.getOnBoardingTaskList(onboard.getOnboardingid());
    logger.info("onBoradTaskList.size()" + onBoradTaskList.size());
    



    intform.setOnboarding(onboard);
    intform.setOnboardingTaskList(onBoradTaskList);
    


    logger.info("onBoradTaskList.size()" + onBoradTaskList.size());
    
    return mapping.findForward("onboardCheckList");
  }
  
  public ActionForward onboardprintview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside onboardprintview method");
    String applicantid = request.getParameter("applicantid");
    String uuid = request.getParameter("secureid");
    logger.info("applicantid" + applicantid);
    logger.info("uuid" + uuid);
    OnBoardingForm intform = (OnBoardingForm)form;
    
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantid, uuid);
    intform.setJobapplicant(applicant);
    JobRequisition requistion = BOFactory.getJobRequistionBO().getJobRequision(String.valueOf(applicant.getReqId()));
    intform.setRequisition(requistion);
    Onboarding onboard = OnBoardingDAO.getOnBoarding(new Long(applicantid).longValue(), uuid);
    logger.info("onboard" + onboard);
    logger.info("onboard" + onboard.getOnboardingid());
    List onBoradTaskList = OnBoardingDAO.getOnBoardingTaskList(onboard.getOnboardingid());
    logger.info("onBoradTaskList.size()" + onBoradTaskList.size());
    



    intform.setOnboarding(onboard);
    intform.setOnboardingTaskList(onBoradTaskList);
    


    logger.info("onBoradTaskList.size()" + onBoradTaskList.size());
    
    return mapping.findForward("onboardprintview");
  }
  
  public ActionForward deleteonboardingtask(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteonboardingtask method");
    String taskid = request.getParameter("taskid");
    String taskuuid = request.getParameter("taskuuid");
    


    OnBoardingTask onboradtask = OnBoardingDAO.getOnBoardingTask(new Long(taskid).longValue(), taskuuid);
    
    String url = "/applicant.do?method=applicantDetails&applicantId=" + onboradtask.getApplicantId() + "&secureid=" + onboradtask.getApplicantuuid();
    logger.info("taskid" + taskid);
    

    ApplicationContext appContext = AppContextUtil.getAppcontext();
    OnBoardingTXBO ontxbo = (OnBoardingTXBO)appContext.getBean("onboardingtxboProxy");
    ontxbo.deleteOnBoardingTask(onboradtask);
    
    OnBoardingForm intform = (OnBoardingForm)form;
    
    ActionForward forward = new ActionForward(url);
    forward.setRedirect(true);
    return forward;
  }
  
  public ActionForward saveOnBoarding(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveOnBoarding method");
    OnBoardingForm intform = (OnBoardingForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    
    List applicantList = (List)request.getSession().getAttribute("applicantList");
    if (applicantList != null) {
      request.getSession().removeAttribute("applicantList");
    }
    List selectedTaskIdList = new ArrayList();
    List selectedTaskList = new ArrayList();
    String[] tasks = request.getParameterValues("taskchk");
    logger.info("tasks" + tasks);
    if (tasks != null) {
      for (int i = 0; i < tasks.length; i++)
      {
        logger.info(tasks[i]);
        selectedTaskIdList.add(tasks[i]);
      }
    }
    for (int kk = 0; kk < applicantList.size(); kk++)
    {
      JobApplicant applicant2 = (JobApplicant)applicantList.get(kk);
      if ((applicant2.getInitiateJoiningProcess() != null) && (applicant2.getInitiateJoiningProcess().equals("Not started")))
      {
        Onboarding onboarding = new Onboarding();
        onboarding.setOnboardingTemplateId(intform.getOnboardingTemplateId());
        onboarding.setApplicantId(applicant2.getApplicantId());
        onboarding.setCreatedBy(user1.getUserName());
        onboarding.setCreatedDate(new Date());
        onboarding.setStatus("A");
        onboarding.setUuid(applicant2.getUuid());
        for (int p = 0; p < selectedTaskIdList.size(); p++)
        {
          String taskdefidstr = (String)selectedTaskIdList.get(p);
          
          OnBoardingTask onTask = new OnBoardingTask();
          onTask.setApplicantId(applicant2.getApplicantId());
          onTask.setTaskdefid(new Long(taskdefidstr).longValue());
          onTask.setStatus("A");
          onTask.setCreatedBy(user1.getUserName());
          onTask.setCreatedDate(new Date());
          


          String owneridstr = request.getParameter("taskownerid_" + taskdefidstr);
          String ownernamestr = request.getParameter("taskownername_" + taskdefidstr);
          String groupstr = request.getParameter("isGroup_" + taskdefidstr);
          logger.info(owneridstr);
          logger.info("owneridstr" + owneridstr.trim());
          onTask.setOwnerId(new Long(owneridstr.trim()).longValue());
          

          onTask.setAssignedtoUserId(new Long(owneridstr.trim()).longValue());
          onTask.setAssignedtoUserName(ownernamestr.trim());
          onTask.setIsGroup(groupstr.trim());
          
          onTask.setAssignedbyUserId(user1.getUserId());
          onTask.setAssignedbyUserName(user1.getFirstName() + " " + user1.getLastName());
          
          String nofodays = request.getParameter("tasknofodays_" + taskdefidstr);
          onTask.setNoofdays(new Integer(nofodays.trim()).intValue());
          




          selectedTaskList.add(onTask);
        }
        BOFactory.getOnBoardingTXBO().saveOnBoarding(applicant2, selectedTaskList, onboarding, user1);
      }
    }
    request.setAttribute("initiatedOBoarding", "yes");
    return mapping.findForward("initiateOnBoarding");
  }
  
  public ActionForward submitOnboardingTask(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside submitOnboardingTask method");
    String taskid = request.getParameter("taskid");
    String secureid = request.getParameter("secureid");
    String comments = request.getParameter("comments");
    User user1 = (User)request.getSession().getAttribute("user_data");
    

    OnBoardingForm intform = (OnBoardingForm)form;
    

    OnBoardingTask task = OnBoardingDAO.getOnBoardingTask(new Long(taskid).longValue(), secureid);
    
    task.setTaskOwnerComment(comments);
    task.setStatus("C");
    task.setUpdatedBy(user1.getUserName());
    task.setUpdatedDate(new Date());
    
    List attributeValueList = task.getAttributeValues();
    List newAttributeValueList = new ArrayList();
    boolean isMandatoryValuesPassed = true;
    for (int i = 0; i < attributeValueList.size(); i++)
    {
      OnBoardingTaskAttributeValues taskattrvalue = (OnBoardingTaskAttributeValues)attributeValueList.get(i);
      String value = request.getParameter("taskattvalue_" + taskattrvalue.getAttid());
      taskattrvalue.setAttributeValue(value);
      newAttributeValueList.add(taskattrvalue);
      if ((taskattrvalue.getIsMandatory().equals("Y")) && 
        (StringUtils.isNullOrEmpty(value))) {
        isMandatoryValuesPassed = false;
      }
    }
    task.setAttributeValues(newAttributeValueList);
    
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(String.valueOf(task.getApplicantId()), task.getApplicantuuid());
    if (isMandatoryValuesPassed)
    {
      BOFactory.getOnBoardingTXBO().submitOnboardingTask(task, applicant, user1);
      request.setAttribute("submittedOBoardingTask", "yes");
    }
    else
    {
      request.setAttribute("mandatoryValuesNotAdded", "yes");
    }
    intform.setOnboardingTask(task);
    


    intform.setJobapplicant(applicant);
    

    return mapping.findForward("loadTask");
  }
  
  private String generateJavaScriptOld(OnBoardingTemplate template)
  {
    String js = "";
    Set taskdefset = template.getTaskdefinitions();
    Iterator itr = taskdefset.iterator();
    while (itr.hasNext())
    {
      OnBoardingTaskDefinitions taskdef = (OnBoardingTaskDefinitions)itr.next();
      
      js = js + "$(\"#taskowner_" + taskdef.getTaskdefid() + "\"" + ").autocomplete({" + "\n" + "url: 'jsp/talent/getUserData.jsp'," + "\n" + "onItemSelect: function(item) {" + "\n" + " var text = 'You selected <b>' + item.value + '</b>';" + "\n" + " if (item.data.length) { " + "\n" + "    text += ' <i>' + item.data.join(', ') + '</i>';" + "\n" + " }" + "\n" + "document.onBoardingForm.taskownerid_" + taskdef.getTaskdefid() + ".value=item.data;" + "\n" + "document.onBoardingForm.taskownerhiddenname_" + taskdef.getTaskdefid() + ".value=item.value;" + "\n" + "}" + "\n" + "});" + "\n" + "\n" + "\n";
    }
    return js;
  }
  
  private String generateJavaScript(OnBoardingTemplate template)
  {
    String js = "";
    Set taskdefset = template.getTaskdefinitions();
    Iterator itr = taskdefset.iterator();
    while (itr.hasNext())
    {
      OnBoardingTaskDefinitions taskdef = (OnBoardingTaskDefinitions)itr.next();
      
      js = js + "$(\"#taskowner_" + taskdef.getTaskdefid() + "\"" + ").autocomplete({" + "\n" + "url: 'jsp/talent/getUserUserGroupData.jsp'," + "\n" + "minChars: '" + Constant.getValue("autocomplete.min.chars") + "'," + "\n" + "onItemSelect: function(item) {" + "\n" + "var itemvaluedata = \"\"+item.data;" + "\n" + "var itemvaluedataname = \"\"+item.value;" + "\n" + "if(strStartsWith(itemvaluedata,\"g\")){" + "\n" + "\t document.onBoardingForm.taskownername_" + taskdef.getTaskdefid() + ".value=itemvaluedataname.substring(44,itemvaluedataname.length);" + "\n" + "\t var idval=itemvaluedata;" + "\n" + "\t document.onBoardingForm.taskownerid_" + taskdef.getTaskdefid() + ".value=idval.substring(1,idval.length);" + "\n" + "\t document.onBoardingForm.isGroup_" + taskdef.getTaskdefid() + ".value='Y';" + "\n" + "\t document.onBoardingForm.taskownerhiddenname_" + taskdef.getTaskdefid() + ".value=itemvaluedataname.substring(44,itemvaluedataname.length);" + "\n" + " }else{" + "\n" + " document.onBoardingForm.taskownerid_" + taskdef.getTaskdefid() + ".value=item.data;" + "\n" + " document.onBoardingForm.isGroup_" + taskdef.getTaskdefid() + ".value='N';" + "\n" + " document.onBoardingForm.taskownername_" + taskdef.getTaskdefid() + ".value=itemvaluedataname.substring(33,itemvaluedataname.length);" + "\n" + " document.onBoardingForm.taskownerhiddenname_" + taskdef.getTaskdefid() + ".value=itemvaluedataname.substring(33,itemvaluedataname.length);" + "\n" + " }" + "\n" + "}" + "\n" + "});" + "\n" + "\n" + "\n";
    }
    return js;
  }
  
  private String generateJavaScriptAfterOnboardInitiation(List onBoradTaskList)
  {
    String js = "";
    for (int i = 0; i < onBoradTaskList.size(); i++)
    {
      OnBoardingTask task = (OnBoardingTask)onBoradTaskList.get(i);
      
      js = js + "$(\"#taskowner_" + task.getOnboardingTaskId() + "\"" + ").autocomplete({" + "\n" + "url: 'jsp/talent/getUserData.jsp'," + "\n" + "onItemSelect: function(item) {" + "\n" + " var text = 'You selected <b>' + item.value + '</b>';" + "\n" + " if (item.data.length) { " + "\n" + "    text += ' <i>' + item.data.join(', ') + '</i>';" + "\n" + " }" + "\n" + "document.onBoardingForm.taskownerid_" + task.getOnboardingTaskId() + ".value=item.data;" + "\n" + "document.onBoardingForm.taskownerhiddenname_" + task.getOnboardingTaskId() + ".value=item.value;" + "\n" + "}" + "\n" + "});" + "\n" + "\n" + "\n";
    }
    return js;
  }
}
