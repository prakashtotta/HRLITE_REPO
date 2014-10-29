package com.action;

import com.bean.Department;
import com.bean.JobRequisionTemplate;
import com.bean.JobRequisition;
import com.bean.JobTemplateAccomplishment;
import com.bean.JobTemplateApprovers;
import com.bean.JobTemplateCompetency;
import com.bean.Organization;
import com.bean.User;
import com.bean.criteria.RequisitionTemplateSearchCriteriaMultiple;
import com.bean.criteria.RequistionSearchUtil;
import com.bean.system.SystemRule;
import com.bean.system.SystemRuleUser;
import com.bo.BOFactory;
import com.bo.JobRequistionBO;
import com.bo.LovBO;
import com.common.Common;
import com.dao.RuleDAO;
import com.form.JobRequisitionTemplateForm;
import com.resources.Constant;
import com.util.ScreenSettingUtils;
import com.util.StringUtils;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class JobRequisitionTemplateAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(JobRequisitionTemplateAction.class);
  
  public ActionForward loadDeptlistWithProjectcode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String orgId = request.getParameter("orgId");
    
    System.out.println("orgId" + orgId);
    JobRequisitionTemplateForm tmplForm = (JobRequisitionTemplateForm)form;
    


    tmplForm.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(orgId));
    

    return mapping.findForward("deptlistwithprojectcode");
  }
  
  public ActionForward loadProjectCode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String departmentId = request.getParameter("departmentId");
    System.out.println("departmentId" + departmentId);
    JobRequisitionTemplateForm tmplForm = (JobRequisitionTemplateForm)form;
    tmplForm.setProjectcodeList(BOFactory.getLovBO().getProjectCodesByDept(departmentId));
    

    return mapping.findForward("projectcodelist");
  }
  
  public ActionForward loadBudgetCode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String orgId = request.getParameter("orgId");
    String departmentId = request.getParameter("departmentId");
    logger.info(".....................................orgId......" + orgId);
    logger.info(".....................................departmentId......" + departmentId);
    System.out.println("orgId" + orgId);
    System.out.println("departmentId" + departmentId);
    JobRequisitionTemplateForm tmplForm = (JobRequisitionTemplateForm)form;
    
    tmplForm.setBudgetCodeList(BOFactory.getLovBO().getAllBudgetCodeDetailsbyorganizationanddepartment(orgId, departmentId));
    
    return mapping.findForward("budgetcodelist");
  }
  
  public ActionForward loadprojectcodebyOrganization(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String orgid = request.getParameter("parentOrgId");
    String departmentid = request.getParameter("departmentId");
    JobRequisitionTemplateForm tmplForm = (JobRequisitionTemplateForm)form;
    List projectcodeList = new ArrayList();
    if (departmentid != null) {
      projectcodeList = BOFactory.getLovBO().getProjectCodesByOrgAndDept(orgid, departmentid);
    }
    tmplForm.setProjectcodeList(projectcodeList);
    return mapping.findForward("projectcodelistbyOrg");
  }
  
  public ActionForward loadBudgetCodebyOrganization(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String orgid = request.getParameter("parentOrgId");
    String departmentid = request.getParameter("departmentId");
    JobRequisitionTemplateForm tmplForm = (JobRequisitionTemplateForm)form;
    List bugetlist = new ArrayList();
    if ((departmentid != null) || (departmentid != "0"))
    {
      bugetlist = BOFactory.getLovBO().getAllBudgetCodeDetailsbyorganizationanddepartment(orgid, departmentid);
      tmplForm.setProjectcodeList(bugetlist);
    }
    else
    {
      tmplForm.setProjectcodeList(bugetlist);
    }
    tmplForm.setBudgetCodeList(bugetlist);
    return mapping.findForward("budgetcodelistbyOrgfortemplate");
  }
  
  public ActionForward templateList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside templateList method");
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    JobRequisitionTemplateForm tmplForm = (JobRequisitionTemplateForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    try
    {
      BOFactory.getJobRequistionBO().loadTemplateSearchLov(tmplForm, user1);
      RequisitionTemplateSearchCriteriaMultiple serachCriteria = new RequisitionTemplateSearchCriteriaMultiple();
      tmplForm.setSerachCriteria(serachCriteria);
      request.setAttribute("searchpagedisplay", "no");
    }
    catch (Exception e)
    {
      String msg = Constant.getResourceStringValue("server.error", user1.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
      return mapping.findForward("exception");
    }
    return mapping.findForward("templateList");
  }
  
  public ActionForward searchtemplatelistpage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchtemplatelistpage method");
    
    String statuscri = request.getParameter("statuscri");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    JobRequisitionTemplateForm tmplForm = (JobRequisitionTemplateForm)form;
    tmplForm.setStatuscri(statuscri);
    tmplForm.setJobgradeId(tmplForm.getJobgradeId());
    tmplForm.setJobtypeId(tmplForm.getJobtypeId());
    tmplForm.setParentOrgId(tmplForm.getParentOrgId());
    tmplForm.setDepartmentId(tmplForm.getDepartmentId());
    tmplForm.setTemplateName(tmplForm.getTemplateName());
    User user1 = (User)request.getSession().getAttribute("user_data");
    try
    {
      RequisitionTemplateSearchCriteriaMultiple serachCriteria = new RequisitionTemplateSearchCriteriaMultiple();
      
      RequistionSearchUtil.setRequisitionTemplateSearchCriteriaMultiple(tmplForm, serachCriteria);
      BOFactory.getJobRequistionBO().loadTemplateSearchLov(tmplForm, user1);
    }
    catch (Exception e)
    {
      String msg = Constant.getResourceStringValue("server.error", user1.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
      return mapping.findForward("exception");
    }
    request.setAttribute("searchpagedisplay", "yes");
    return mapping.findForward("templateList");
  }
  
  public ActionForward createtemplate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside createtemplate method");
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    JobRequisitionTemplateForm tmplForm = (JobRequisitionTemplateForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    
    tmplForm.setState(Constant.getUIValue("status.open"));
    tmplForm.setStatus(Constant.getUIValue("status.submitted"));
    try
    {
      BOFactory.getJobRequistionBO().commonLovPopulateForCreate(tmplForm, user1);
    }
    catch (Exception e)
    {
      String msg = Constant.getResourceStringValue("server.error", user1.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
      return mapping.findForward("exception");
    }
    return mapping.findForward("createtemplate");
  }
  
  public ActionForward createText(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside createText method");
    

    return mapping.findForward("createtextReqtemplate");
  }
  
  public ActionForward edittemplate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside edittemplate method");
    String templateId = request.getParameter("templateId");
    String tabno = request.getParameter("tabno");
    JobRequisitionTemplateForm tmplForm = (JobRequisitionTemplateForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    try
    {
      tmplForm.setTemplateId(new Long(templateId).longValue());
      JobRequisionTemplate jb = BOFactory.getJobRequistionBO().edittemplate(tmplForm, user1);
      tmplForm.fromValue(jb, request);
    }
    catch (Exception e)
    {
      String msg = Constant.getResourceStringValue("server.error", user1.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
      return mapping.findForward("exception");
    }
    if (!StringUtils.isNullOrEmpty(tabno)) {
      tmplForm.setTabselected(new Integer(tabno).intValue());
    }
    request.setAttribute("edittemplate", "yes");
    return mapping.findForward("edittemplate");
  }
  
  public ActionForward edittemplateselector(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside edittemplateselector method");
    String templateId = request.getParameter("templateId");
    JobRequisitionTemplateForm tmplForm = (JobRequisitionTemplateForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    try
    {
      tmplForm.setTemplateId(new Long(templateId).longValue());
      JobRequisionTemplate jb = BOFactory.getJobRequistionBO().edittemplateselector(tmplForm, user1);
      tmplForm.fromValue(jb, request);
      
      request.setAttribute("viewforselector", "yes");
      return mapping.findForward("edittemplate");
    }
    catch (Exception e)
    {
      String msg = Constant.getResourceStringValue("server.error", user1.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
    }
    return mapping.findForward("exception");
  }
  
  public ActionForward saveastemplate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveastemplate method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    String templateId = request.getParameter("templateId");
    JobRequisitionTemplateForm tmplForm = (JobRequisitionTemplateForm)form;
    try
    {
      tmplForm.setTemplateId(new Long(templateId).longValue());
      JobRequisionTemplate jb = BOFactory.getJobRequistionBO().saveastemplate(tmplForm, request, user1);
      tmplForm.fromValue(jb, request);
    }
    catch (Exception e)
    {
      String msg = Constant.getResourceStringValue("server.error", user1.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
      return mapping.findForward("exception");
    }
    request.setAttribute("saveas", "true");
    return mapping.findForward("edittemplate");
  }
  
  public ActionForward makeactive(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside makeactive method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    String templateId = request.getParameter("templateId");
    JobRequisitionTemplateForm tmplForm = (JobRequisitionTemplateForm)form;
    try
    {
      tmplForm.setTemplateId(new Long(templateId).longValue());
      JobRequisionTemplate jb = BOFactory.getJobRequistionBO().activatetemplate(tmplForm, user1);
      tmplForm.fromValue(jb, request);
    }
    catch (Exception e)
    {
      String msg = Constant.getResourceStringValue("server.error", user1.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
      return mapping.findForward("exception");
    }
    request.setAttribute("makeactive", "true");
    return mapping.findForward("edittemplate");
  }
  
  public ActionForward closetemplate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside closetemplate method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    String templateId = request.getParameter("templateId");
    JobRequisitionTemplateForm tmplForm = (JobRequisitionTemplateForm)form;
    try
    {
      tmplForm.setTemplateId(new Long(templateId).longValue());
      JobRequisionTemplate jb = BOFactory.getJobRequistionBO().closetemplate(tmplForm, user1);
      tmplForm.fromValue(jb, request);
    }
    catch (Exception e)
    {
      String msg = Constant.getResourceStringValue("server.error", user1.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
      return mapping.findForward("exception");
    }
    return mapping.findForward("edittemplate");
  }
  
  public ActionForward deletetemplate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deletetemplate method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    String templateId = request.getParameter("templateId");
    JobRequisitionTemplateForm tmplForm = (JobRequisitionTemplateForm)form;
    try
    {
      tmplForm.setTemplateId(new Long(templateId).longValue());
      JobRequisionTemplate jb = BOFactory.getJobRequistionBO().deletetemplate(tmplForm, user1);
      tmplForm.fromValue(jb, request);
    }
    catch (Exception e)
    {
      String msg = Constant.getResourceStringValue("server.error", user1.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
      return mapping.findForward("exception");
    }
    return mapping.findForward("edittemplate");
  }
  
  public ActionForward updatejobtemplate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updatejobtemplate method");
    String templateId = request.getParameter("templateId");
    String isInternal = request.getParameter("isInternal");
    String hiringMgrId = request.getParameter("hiringMgrId");
    String recruiterId = request.getParameter("recruiterId");
    logger.info("Recruiter Id : " + recruiterId);
    

    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    JobRequisitionTemplateForm jbForm = (JobRequisitionTemplateForm)form;
    try
    {
      jbForm.setTemplateId(new Long(templateId).longValue());
      JobRequisionTemplate jb = BOFactory.getJobRequistionBO().updatejobtemplate(jbForm, request, isInternal, hiringMgrId, recruiterId, user1);
      jbForm.fromValue(jb, request);
    }
    catch (Exception e)
    {
      logger.info("exception on updatejobtemplate", e);
      String msg = Constant.getResourceStringValue("server.error", user1.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
      return mapping.findForward("exception");
    }
    request.setAttribute("updatejobtemplatecreated", "yes");
    logger.info("Inside updatejobtemplate method end");
    return mapping.findForward("edittemplate");
  }
  
  public ActionForward savejobtemplate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside savejobtemplate method");
    String isInternal = request.getParameter("isInternal");
    
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    JobRequisitionTemplateForm tmplForm = (JobRequisitionTemplateForm)form;
    
    String hiringManagerId = request.getParameter("hiringManagerId");
    String recruiterId = request.getParameter("recruiterId");
    logger.info("Recruiter Id : " + recruiterId);
    logger.info("hiringManagerId : " + hiringManagerId);
    try
    {
      JobRequisionTemplate jb = BOFactory.getJobRequistionBO().savejobtemplate(tmplForm, request, hiringManagerId, recruiterId, isInternal, user1);
      tmplForm.fromValue(jb, request);
    }
    catch (Exception e)
    {
      String msg = Constant.getResourceStringValue("server.error", user1.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
      return mapping.findForward("exception");
    }
    request.setAttribute("jobtemplatecreated", "yes");
    
    return mapping.findForward("edittemplate");
  }
  
  public ActionForward saveCompetency(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveCompetency method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    
    String templateid = request.getParameter("templateId");
    String competencyname = request.getParameter("competencyname");
    String iscompmandatory = request.getParameter("iscompmandatory");
    String minimumrating = request.getParameter("minimumrating");
    String type = request.getParameter("type");
    String isVisible = request.getParameter("isVisible");
    logger.info("templateid" + templateid);
    logger.info("competencyname" + competencyname);
    logger.info("minimumrating" + minimumrating);
    logger.info("type" + type);
    try
    {
      JobTemplateCompetency jbtc = new JobTemplateCompetency();
      jbtc.setJbTmplId(new Long(templateid).longValue());
      jbtc.setCharName(competencyname);
      if (minimumrating != null)
      {
        String rating = minimumrating.substring(0, 1);
        logger.info("rating" + rating);
        jbtc.setImportance(new Integer(rating).intValue());
      }
      else
      {
        jbtc.setImportance(0);
      }
      jbtc.setType(type);
      jbtc.setMandatory((iscompmandatory != null) && (iscompmandatory.equals("on")) ? "on" : "off");
      jbtc.setIsVisible((isVisible != null) && (isVisible.equals("Y")) ? "Y" : "N");
      
      BOFactory.getJobRequistionBO().saveJobReqCompetency(jbtc);
    }
    catch (Exception e)
    {
      logger.info(e.getMessage());
      e.printStackTrace();
    }
    logger.info("end saveCompetency method");
    
    return null;
  }
  
  public ActionForward saveAccompleshment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveAccompleshment method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    
    String templateid = request.getParameter("templateId");
    String accomplishmentname = request.getParameter("accomplishmentname");
    String isaccommandatory = request.getParameter("isaccommandatory");
    
    String type = request.getParameter("type");
    try
    {
      JobTemplateAccomplishment jbtc = new JobTemplateAccomplishment();
      jbtc.setJbTmplId(new Long(templateid).longValue());
      jbtc.setAccName(accomplishmentname);
      






      jbtc.setType(type);
      jbtc.setMandatory((isaccommandatory != null) && (isaccommandatory.equals("on")) ? "on" : "off");
      
      BOFactory.getJobRequistionBO().saveJobReqAccomplieshment(jbtc);
    }
    catch (Exception e)
    {
      logger.info(e.getMessage());
      e.printStackTrace();
    }
    logger.info("end saveAccompleshment method");
    
    return null;
  }
  
  public ActionForward saveApprover(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveApprover method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionForward forward = new ActionForward();
    JobRequisitionTemplateForm tmplForm = (JobRequisitionTemplateForm)form;
    JobTemplateApprovers jbtapprover = null;
    JobRequisionTemplate jb = null;
    String templateid = request.getParameter("templateId");
    String approvername = request.getParameter("approvername");
    String approverid = request.getParameter("approverid");
    String type = request.getParameter("type");
    String isgroup = request.getParameter("isgroup");
    try
    {
      tmplForm.setTemplateId(new Long(templateid).longValue());
      jb = BOFactory.getJobRequistionBO().edittemplate(tmplForm, user1);
      
      List approversList = BOFactory.getJobRequistionBO().getJobRequisionTemplateApproversList(new Long(templateid).longValue(), type);
      
      logger.info("template approver list : " + approversList.size());
      Iterator itr = approversList.iterator();
      while (itr.hasNext())
      {
        jbtapprover = (JobTemplateApprovers)itr.next();
        logger.info("approver name from database :" + jbtapprover.getApproverName());
        if (approvername.equals(jbtapprover.getApproverName()))
        {
          tmplForm.setTemplateId(new Long(templateid).longValue());
          

          request.setAttribute("approveralreadyadded", "yes");
          tmplForm.fromValue(jb, request);
          BOFactory.getJobRequistionBO().commonLovPopulateForCreate(tmplForm, user1);
          approversList = BOFactory.getJobRequistionBO().getJobRequisionTemplateApproversList(new Long(templateid).longValue(), type);
          tmplForm.setApproversList(approversList);
          tmplForm.setTabselected(4);
          
          return mapping.findForward("createtemplate");
        }
      }
      jbtapprover = new JobTemplateApprovers();
      jbtapprover.setJbTmplId(new Long(templateid).longValue());
      jbtapprover.setApproverName(approvername);
      jbtapprover.setIsFromSystemRule("N");
      if ((!StringUtils.isNullOrEmpty(isgroup)) && (isgroup.equals("Y"))) {
        jbtapprover.setIsGroup("Y");
      } else {
        jbtapprover.setIsGroup("N");
      }
      jbtapprover.setType(type);
      jbtapprover.setUserId(new Long(approverid).longValue());
      
      BOFactory.getJobRequistionBO().saveTemplateApprover(jbtapprover, templateid, type, user1);
      
      tmplForm.fromValue(jb, request);
      BOFactory.getJobRequistionBO().commonLovPopulateForCreate(tmplForm, user1);
      approversList = BOFactory.getJobRequistionBO().getJobRequisionTemplateApproversList(new Long(templateid).longValue(), type);
      tmplForm.setApproversList(approversList);
      tmplForm.setTabselected(4);
    }
    catch (Exception e)
    {
      logger.info(e.getMessage());
      e.printStackTrace();
    }
    logger.info("end saveApprover method");
    



    return mapping.findForward("createtemplate");
  }
  
  public ActionForward deleteComptecy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteComptecy method");
    
    JobRequisitionTemplateForm tmplForm = (JobRequisitionTemplateForm)form;
    String id = request.getParameter("id");
    BOFactory.getJobRequistionBO().deleteCompetency(new Long(id).longValue());
    return null;
  }
  
  public ActionForward deleteAccomplishment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteAccomplishment method");
    
    JobRequisitionTemplateForm tmplForm = (JobRequisitionTemplateForm)form;
    String id = request.getParameter("id");
    BOFactory.getJobRequistionBO().deleteAccomplishment(new Long(id).longValue());
    return null;
  }
  
  public ActionForward getCompetencies(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside getCompetencies method");
    JobRequisitionTemplateForm tmplForm = (JobRequisitionTemplateForm)form;
    String id = request.getParameter("id");
    String type = request.getParameter("type");
    List competecyList = BOFactory.getJobRequistionBO().getJobRequisionTemplateComptetencyList(new Long(id).longValue(), type);
    tmplForm.setComptetencyList(competecyList);
    return mapping.findForward("getCompetencies");
  }
  
  public ActionForward getAccomplishments(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside getAccomplishments method");
    JobRequisitionTemplateForm tmplForm = (JobRequisitionTemplateForm)form;
    String id = request.getParameter("id");
    String type = request.getParameter("type");
    List accomplishmentList = BOFactory.getJobRequistionBO().getJobRequisionTemplateAccomplishmentList(new Long(id).longValue(), type);
    tmplForm.setAccomplishmentList(accomplishmentList);
    return mapping.findForward("getAccomplishments");
  }
  
  public ActionForward getApprovers(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside getApprovers method");
    
    JobRequisitionTemplateForm tmplForm = (JobRequisitionTemplateForm)form;
    String id = request.getParameter("id");
    String type = request.getParameter("type");
    
    List approversList = BOFactory.getJobRequistionBO().getJobRequisionTemplateApproversList(new Long(id).longValue(), type);
    tmplForm.setApproversList(approversList);
    
    return mapping.findForward("getApprovers");
  }
  
  public ActionForward getApproversForInitiate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside getApproversForInitiate method");
    JobRequisitionTemplateForm tmplForm = (JobRequisitionTemplateForm)form;
    String id = request.getParameter("id");
    String type = request.getParameter("type");
    
    JobRequisition jb = BOFactory.getJobRequistionBO().getJobRequision(id);
    List newapproverList = new ArrayList();
    


    long orgId = jb.getOrganization().getOrgId();
    long deptId = 0L;
    if (jb.getDepartment() != null) {
      deptId = jb.getDepartment().getDepartmentId();
    }
    SystemRule sysrule = RuleDAO.getOrganizationRuleByType(orgId, deptId, Common.SYSTEM_RULE_REQUISTION_APPROVER);
    int levelorder = 0;
    if (sysrule != null)
    {
      List approvers = sysrule.getRuleUsers();
      for (int i = 0; i < approvers.size(); i++)
      {
        SystemRuleUser sysuser = (SystemRuleUser)approvers.get(i);
        if ((sysuser.getUser() != null) && (sysuser.getUser().getStatus().equals("A")))
        {
          JobTemplateApprovers jbapp = new JobTemplateApprovers();
          jbapp.setUserId(sysuser.getUser().getUserId());
          jbapp.setApproverName(sysuser.getUser().getFirstName() + " " + sysuser.getUser().getLastName());
          jbapp.setIsFromSystemRule("Y");
          jbapp.setIsapprover("Y");
          jbapp.setType("job");
          levelorder += 1;
          jbapp.setLevelorder(levelorder);
          newapproverList.add(jbapp);
        }
      }
    }
    List approversList = BOFactory.getJobRequistionBO().getJobRequisionTemplateApproversList(jb.getJobreqId(), "job");
    if ((approversList != null) && (approversList.size() > 0)) {
      for (int i = 0; i < approversList.size(); i++)
      {
        levelorder += 1;
        JobTemplateApprovers jbapp = (JobTemplateApprovers)approversList.get(i);
        jbapp.setIsFromSystemRule("N");
        jbapp.setLevelorder(levelorder);
        newapproverList.add(jbapp);
      }
    }
    tmplForm.setApproversList(newapproverList);
    logger.info("newapproverList size" + newapproverList.size());
    return mapping.findForward("getApproversForInitiate");
  }
  
  public ActionForward deleteApprovers(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteApprovers method");
    
    JobRequisitionTemplateForm tmplForm = (JobRequisitionTemplateForm)form;
    String id = request.getParameter("id");
    User user1 = (User)request.getSession().getAttribute("user_data");
    


    BOFactory.getJobRequistionBO().deleteApprovers(new Long(id).longValue());
    return null;
  }
  
  public ActionForward editjobtemplateselector(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside edittemplate method");
    String templateId = request.getParameter("templateId");
    JobRequisitionTemplateForm tmplForm = (JobRequisitionTemplateForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    try
    {
      tmplForm.setTemplateId(new Long(templateId).longValue());
      JobRequisionTemplate jb = BOFactory.getJobRequistionBO().editjobtemplateselector(tmplForm, user1);
      tmplForm.fromValue(jb, request);
    }
    catch (Exception e)
    {
      String msg = Constant.getResourceStringValue("server.error", user1.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
      return mapping.findForward("exception");
    }
    return mapping.findForward("editjobtemplateselector");
  }
  
  public ActionForward updatestatus(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("*** Inside updatestatus method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String templateId = request.getParameter("templateId");
    String status = request.getParameter("status");
    JobRequisitionTemplateForm tmplForm = (JobRequisitionTemplateForm)form;
    logger.info("**** Inside updatestatus method : " + templateId);
    logger.info("**** Inside updatestatus method : " + status);
    try
    {
      tmplForm.setTemplateId(new Long(templateId).longValue());
      JobRequisionTemplate jb = BOFactory.getJobRequistionBO().updateTemplatestatus(tmplForm, status, user1);
    }
    catch (Exception e)
    {
      String msg = Constant.getResourceStringValue("server.error", user1.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
      return mapping.findForward("exception");
    }
    return mapping.findForward("updatestatus");
  }
  
  public ActionForward jobtemplatelistpage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside jobtemplatelistpage method");
    int results = -1;
    int startIndex = 0;
    String sort = "username";
    String dir = "asc";
    
    User user1 = (User)request.getSession().getAttribute("user_data");
    

    String results_str = request.getParameter("results");
    String startIndex_str = request.getParameter("startIndex");
    String sort_str = request.getParameter("sort");
    String dir_str = request.getParameter("dir");
    
    RequisitionTemplateSearchCriteriaMultiple searchCriteria = new RequisitionTemplateSearchCriteriaMultiple();
    RequistionSearchUtil.setReqTemplateCriteriaFromRequest(request, searchCriteria);
    if ((startIndex_str != null) && (startIndex_str.length() > 0)) {
      startIndex = Integer.parseInt(startIndex_str);
    }
    if ((sort_str != null) && (sort_str.length() > 0)) {
      sort = sort_str;
    }
    if ((dir_str != null) && (dir_str.length() > 0)) {
      dir = dir_str;
    }
    if ((results_str != null) && (results_str.length() > 0)) {
      results = Integer.parseInt(results_str);
    }
    List dataList = new ArrayList();
    int totalSize = 0;
    

    Map m = BOFactory.getJobRequistionBO().searchJobRequisitionsTemplate(user1, searchCriteria, results, startIndex, dir_str, sort_str);
    dataList = (List)m.get(Common.REQUISTION_TEMPLATE_LIST);
    totalSize = ((Integer)m.get(Common.REQUISTION_TEMPLATE_COUNT)).intValue();
    





    String[] fields = ScreenSettingUtils.getArrayOfApplicationScreenSettingsKeyListPage("REQUISION_TEMPLATE_SCREEN");
    


    String data = "{\n\"recordsReturned\":" + dataList.size() + "," + "\n" + "\"" + "totalRecords" + "\"" + ":" + totalSize + "," + "\n" + "\"" + "startIndex" + "\"" + ":" + startIndex + "," + "\n" + "\"" + "sort" + "\"" + ":" + "\"" + sort + "\"" + "," + "\n" + "\"" + "dir" + "\"" + ":" + "\"" + dir + "\"" + "," + "\n" + "\"" + "pageSize" + "\"" + ":" + results + "," + "\n" + "\"" + "records" + "\"" + ":" + StringUtils.createJasonDataTable(dataList, fields, user1) + "}";
    







    request.setAttribute("data", data);
    return mapping.findForward("jobtemplatelistpage");
  }
}
