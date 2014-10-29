package com.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.bean.BulkUploadTask;
import com.bean.DataTableBean;
import com.bean.Department;
import com.bean.JobRequisition;
import com.bean.JobTemplateAccomplishment;
import com.bean.JobTemplateApprovers;
import com.bean.JobTemplateCompetency;
import com.bean.Organization;
import com.bean.PublishJobBoards;
import com.bean.RefferalScheme;
import com.bean.RequisitionComments;
import com.bean.RequistionAttachments;
import com.bean.RequistionExamQnsAssign;
import com.bean.SocialUrlMapping;
import com.bean.User;
import com.bean.criteria.RequisitionSearchCriteriaMultiple;
import com.bean.criteria.RequistionSearchUtil;
import com.bean.criteria.ResuistionSearchCriteria;
import com.bean.criteria.SearchCustomFields;
import com.bo.BOFactory;
import com.bo.PermissionBO;
import com.common.Common;
import com.common.EmailNotificationSettingFunction;
import com.common.ObjectNotFoundException;
import com.common.ValidationException;
import com.form.JobRequisitionForm;
import com.form.VariableDataCaptureUtil;
import com.manager.BulkTaskManager;
import com.resources.Constant;
import com.util.EncryptDecrypt;
import com.util.ExportTaskUtil;
import com.util.ScreenSettingUtils;
import com.util.StringUtils;

public class JobRequistionAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(JobRequistionAction.class);
  
  public ActionForward exporttoexcelAllSearch(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside exporttoexcelAllSearch method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    String statuscri = request.getParameter("statuscri");
    String statecri = request.getParameter("statecri");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    jbForm.setStatuscri(statuscri);
    jbForm.setStatecri(statecri);
    jbForm.setJobgradeId(jbForm.getJobgradeId());
    jbForm.setJobtypeId(jbForm.getJobtypeId());
    jbForm.setParentOrgId(jbForm.getParentOrgId());
    jbForm.setDepartmentId(jbForm.getDepartmentId());
    jbForm.setJobreqName(jbForm.getJobreqName());
    jbForm.setJobreqId(jbForm.getJobreqId());
    jbForm.setJobreqcode(jbForm.getJobreqcode());
    jbForm.setBudgetcodeId(jbForm.getBudgetcodeId());
    BOFactory.getJobRequistionBO().searchreqlistpageLovs(jbForm, user1);
    

    BulkUploadTask task = new BulkUploadTask();
    ExportTaskUtil exp = new ExportTaskUtil();
    exp.setUser(user1);
    exp.setTask(task);
    exp.setSearcherrole(Common.EXPORT_REQUISTIONS_SEARCH_RESULTS);
    exp.setTasktype(Common.EXPORT_REQUISTIONS_SEARCH_RESULTS);
    ResuistionSearchCriteria searchcriteria = new ResuistionSearchCriteria();
    searchcriteria.setStatus(jbForm.getStatus());
    searchcriteria.setState(jbForm.getState());
    searchcriteria.setJobgradeId(jbForm.getJobgradeId());
    searchcriteria.setJobtypeId(jbForm.getJobtypeId());
    searchcriteria.setOrgId(jbForm.getParentOrgId());
    searchcriteria.setDepartmentId(jbForm.getDepartmentId());
    searchcriteria.setJobreqName(jbForm.getJobreqName());
    searchcriteria.setJobreqId(jbForm.getJobreqId());
    searchcriteria.setBudgetcodeId(jbForm.getBudgetcodeId());
    searchcriteria.setJobreqcode(String.valueOf(jbForm.getJobreqcode()));
    
    exp.setReqCriteria(searchcriteria);
    BulkTaskManager.export(exp);
    request.setAttribute("exporttoexcel", "yes");
    
    return mapping.findForward("jobreqList");
  }
  
  public ActionForward exporttoexcelAllSearchMyrequisition(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside exporttoexcelAllSearchMyrequisition method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    String type = request.getParameter("type");
    logger.info("Inside exporttoexcelAllSearchMyrequisition method type" + type);
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    
    BOFactory.getJobRequistionBO().searchreqlistpageLovs(jbForm, user1);
    
    RequisitionSearchCriteriaMultiple serachCriteria = new RequisitionSearchCriteriaMultiple();
    RequisitionSearchCriteriaMultiple serachCriteriaform = new RequisitionSearchCriteriaMultiple();
    
    RequistionSearchUtil.setRequisitionSearchCriteriaMultiple(jbForm, serachCriteriaform);
    
    RequistionSearchUtil.setRequisitionSearchCriteriaMultipleForExport(jbForm, serachCriteria);
    
    Map<Long, SearchCustomFields> customm = new HashMap();
    List variabledatalist = VariableDataCaptureUtil.captureCustomVariablesForRequistionSearch(request, 0L, "REQUISITION_FORM", user1.getSuper_user_key());
    
    serachCriteria.setCustomFieldCriList(variabledatalist);
    
    BulkUploadTask task = new BulkUploadTask();
    ExportTaskUtil exp = new ExportTaskUtil();
    exp.setUser(user1);
    exp.setTask(task);
    exp.setReqserachCriteria(serachCriteria);
    if ((!StringUtils.isNullOrEmpty(type)) && (type.equals("HIRING_MGR")))
    {
      exp.setSearcherrole(Common.EXPORT_MYREQUISTIONS_HIRING_MANAGER_SEARCH_RESULTS);
      exp.setTasktype(Common.EXPORT_MYREQUISTIONS_HIRING_MANAGER_SEARCH_RESULTS);
    }
    else if ((!StringUtils.isNullOrEmpty(type)) && (type.equals("RECRUITER")))
    {
      exp.setSearcherrole(Common.EXPORT_MYREQUISTIONS_RECRUITER_SEARCH_RESULTS);
      exp.setTasktype(Common.EXPORT_MYREQUISTIONS_RECRUITER_SEARCH_RESULTS);
    }
    else if ((!StringUtils.isNullOrEmpty(type)) && (type.equals("ALL_REQUISITION")))
    {
      exp.setSearcherrole(Common.EXPORT_REQUISTIONS_SEARCH_RESULTS);
      exp.setTasktype(Common.EXPORT_REQUISTIONS_SEARCH_RESULTS);
    }
    else if ((!StringUtils.isNullOrEmpty(type)) && (type.equals("MY_ORG_REQUISITION")))
    {
      exp.setSearcherrole(Common.EXPORT_MYREQUISTIONS_SEARCH_RESULTS);
      exp.setTasktype(Common.EXPORT_MYREQUISTIONS_SEARCH_RESULTS);
    }
    BulkTaskManager.export(exp);
    request.setAttribute("exporttoexcel", "yes");
    if ((!StringUtils.isNullOrEmpty(type)) && (type.equals("MY_ORG_REQUISITION"))) {
      return mapping.findForward("ownorgjobreqList");
    }
    if ((!StringUtils.isNullOrEmpty(type)) && (type.equals("ALL_REQUISITION"))) {
      return mapping.findForward("jobreqList");
    }
    if ((!StringUtils.isNullOrEmpty(type)) && (type.equals("RECRUITER"))) {
      return mapping.findForward("myjobreqListRecruiter");
    }
    if ((!StringUtils.isNullOrEmpty(type)) && (type.equals("HIRING_MGR"))) {
      return mapping.findForward("myjobreqList");
    }
    return mapping.findForward("myjobreqList");
  }
  
  public ActionForward loadDeptlistWithProjectcode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String orgId = request.getParameter("parentorgId");
    System.out.println("orgId" + orgId);
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    jbForm.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(orgId));
    return mapping.findForward("deptlistwithprojectcode");
  }
  
  public ActionForward loadProjectCode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String departmentId = request.getParameter("departmentId");
    
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    jbForm.setProjectcodeList(BOFactory.getLovBO().getProjectCodesByDept(departmentId));
    
    return mapping.findForward("projectcodelist");
  }
  
  public ActionForward targetSlipingdate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside targetSlipingdate");
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    
    return mapping.findForward("targetSlipingdate");
  }
  
  public ActionForward retrieveSalaryPlanByOrganization(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String parentOrgId = request.getParameter("parentOrgId");
    User user1 = (User)request.getSession().getAttribute("user_data");
    logger.info("parentOrgId" + parentOrgId);
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    jbForm.setSalaryplanList(BOFactory.getLovBO().retrieveSalaryPlanByOrganization(parentOrgId, user1.getSuper_user_key()));
    
    return mapping.findForward("retrieveSalaryPlanByOrganization");
  }
  
  public ActionForward loadprojectcodebyOrganization(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String orgid = request.getParameter("parentOrgId");
    String departmentid = request.getParameter("departmentId");
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    logger.info(".....................................departmentid" + departmentid);
    logger.info(".....................................orgid" + orgid);
    List projectcodeList = new ArrayList();
    if (departmentid != null) {
      projectcodeList = BOFactory.getLovBO().getProjectCodesByOrgAndDept(orgid, departmentid);
    }
    jbForm.setProjectcodeList(projectcodeList);
    return mapping.findForward("projectcodelistbyOrganization");
  }
  
  public ActionForward loadprojectcodebyOrganization1(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String orgid = request.getParameter("parentOrgId");
    String departmentid = request.getParameter("departmentId");
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    logger.info(".....................................departmentid" + departmentid);
    logger.info(".....................................orgid" + orgid);
    List projectcodeList = new ArrayList();
    if ((departmentid != null) || (departmentid != "0"))
    {
      projectcodeList = BOFactory.getLovBO().getProjectCodesByOrgAndDept(orgid, departmentid);
      jbForm.setProjectcodeList(projectcodeList);
    }
    else
    {
      jbForm.setProjectcodeList(projectcodeList);
    }
    logger.info(".....................................projectcodeList2222" + projectcodeList.size());
    return mapping.findForward("projectcodelistbyOrganization");
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
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    jbForm.setBudgetcodeList(BOFactory.getLovBO().getAllBudgetCodeDetailsbyorganizationanddepartment(orgId, departmentId));
    logger.info(".....................................projectcodeList2222......");
    return mapping.findForward("budgetcodelist");
  }
  
  public ActionForward loadBudgetCodebyOrganization(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String orgid = request.getParameter("parentOrgId");
    String departmentid = request.getParameter("departmentId");
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    List bugetlist = new ArrayList();
    if ((departmentid != null) || (departmentid != "0"))
    {
      bugetlist = BOFactory.getLovBO().getAllBudgetCodeDetailsbyorganizationanddepartment(orgid, departmentid);
      jbForm.setProjectcodeList(bugetlist);
    }
    else
    {
      jbForm.setProjectcodeList(bugetlist);
    }
    jbForm.setBudgetcodeList(bugetlist);
    return mapping.findForward("budgetcodelistbyorganization");
  }
  
  public ActionForward jobreqList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside jobreqList method");
    String state = request.getParameter("state");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    try
    {
      BOFactory.getJobRequistionBO().searchreqlistpageLovs(jbForm, user1);
      RequisitionSearchCriteriaMultiple serachCriteria = new RequisitionSearchCriteriaMultiple();
      if (!StringUtils.isNullOrEmpty(state))
      {
        serachCriteria.setState(state);
        String[] states = new String[1];
        states[0] = state;
        jbForm.setStates(states);
      }
      jbForm.setSerachCriteria(serachCriteria);
      request.setAttribute("searchpagedisplay", "no");
      
      return mapping.findForward("jobreqList");
    }
    catch (Exception e)
    {
      String msg = Constant.getResourceStringValue("server.error", user1.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
    }
    return mapping.findForward("exception");
  }
  
  public ActionForward searchreqlistpage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchreqlistpage method");
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    
    RequisitionSearchCriteriaMultiple serachCriteria = new RequisitionSearchCriteriaMultiple();
    
    RequistionSearchUtil.setRequisitionSearchCriteriaMultiple(jbForm, serachCriteria);
    


    Map<Long, SearchCustomFields> customm = new HashMap();
    List variabledatalist = VariableDataCaptureUtil.captureCustomVariablesForRequistionSearch(request, 0L, "REQUISITION_FORM", user1.getSuper_user_key());
    if ((variabledatalist != null) && (variabledatalist.size() > 0)) {
      for (int i = 0; i < variabledatalist.size(); i++)
      {
        SearchCustomFields sacf = (SearchCustomFields)variabledatalist.get(i);
        customm.put(Long.valueOf(sacf.getVariable_id()), sacf);
      }
    }
    jbForm.setCustomFieldData(customm);
    serachCriteria.setCustomFieldCriList(variabledatalist);
    logger.info("Inside searchreqlistpage method variabledatalist" + variabledatalist);
    request.getSession().setAttribute("variabledatalist", variabledatalist);
    

    BOFactory.getJobRequistionBO().searchreqlistpageLovs(jbForm, user1);
    jbForm.setSerachCriteria(serachCriteria);
    request.setAttribute("searchpagedisplay", "yes");
    return mapping.findForward("searchreqlistpage");
  }
  
  public ActionForward commentList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside commentList method");
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    String uuid = request.getParameter("uuid");
    User user1 = (User)request.getSession().getAttribute("user_data");
    List commentList = BOFactory.getJobRequistionBO().getAllRequisitionComments(uuid);
    jbForm.setCommentList(commentList);
    
    return mapping.findForward("commentList");
  }
  
  public ActionForward addcomment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside addcomment method");
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    String uuid = request.getParameter("uuid");
    User user1 = (User)request.getSession().getAttribute("user_data");
    if (!StringUtils.isNullOrEmpty(jbForm.getComment()))
    {
      RequisitionComments com = new RequisitionComments();
      com.setComment(jbForm.getComment());
      com.setCreatedById(user1.getUserId());
      com.setCreatedByName(user1.getFirstName() + " " + user1.getLastName());
      com.setCreatedDate(new Date());
      com.setUuid(uuid);
      BOFactory.getJobRequistionBO().saveRequisitionComment(com);
    }
    List commentList = BOFactory.getJobRequistionBO().getAllRequisitionComments(uuid);
    jbForm.setCommentList(commentList);
    jbForm.setComment("");
    return mapping.findForward("commentList");
  }
  
  public ActionForward jobreqListpage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside jobreqListpage method");
    int results = -1;
    int startIndex = 0;
    String sort = "username";
    String dir = "asc";
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    

    String results_str = request.getParameter("results");
    String startIndex_str = request.getParameter("startIndex");
    String sort_str = request.getParameter("sort");
    String dir_str = request.getParameter("dir");
    
    RequisitionSearchCriteriaMultiple searchCriteria = new RequisitionSearchCriteriaMultiple();
    RequistionSearchUtil.setCriteriaFromRequest(request, searchCriteria);
    
    List variabledatalist = (List)request.getSession().getAttribute("variabledatalist");
    logger.info("variabledatalist" + variabledatalist);
    searchCriteria.setCustomFieldCriList(variabledatalist);
    if (variabledatalist != null) {
      request.getSession().removeAttribute("variabledatalist");
    }
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
    

    Map m = BOFactory.getJobRequistionBO().searchJobRequisitions(user1, searchCriteria, results, startIndex, dir_str, sort_str);
    dataList = (List)m.get(Common.REQUISTION_LIST);
    totalSize = ((Integer)m.get(Common.REQUISTION_COUNT)).intValue();
    

    String[] fields = ScreenSettingUtils.getArrayOfApplicationScreenSettingsKeyListPage("ALL_REQUISION_SCREEN");
    


    String data = "{\n\"recordsReturned\":" + dataList.size() + "," + "\n" + "\"" + "totalRecords" + "\"" + ":" + totalSize + "," + "\n" + "\"" + "startIndex" + "\"" + ":" + startIndex + "," + "\n" + "\"" + "sort" + "\"" + ":" + "\"" + sort + "\"" + "," + "\n" + "\"" + "dir" + "\"" + ":" + "\"" + dir + "\"" + "," + "\n" + "\"" + "pageSize" + "\"" + ":" + results + "," + "\n" + "\"" + "records" + "\"" + ":" + StringUtils.createJasonDataTable(dataList, fields, user1) + "}";
    







    request.setAttribute("data", data);
    return mapping.findForward("jobreqListpage");
  }
  
  public ActionForward ownorgjobreqList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside ownorgjobreqList method");
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    try
    {
      jbForm.setParentOrgId(user1.getOrganization().getOrgId());
      BOFactory.getJobRequistionBO().searchreqlistpageLovs(jbForm, user1);
      RequisitionSearchCriteriaMultiple serachCriteria = new RequisitionSearchCriteriaMultiple();
      serachCriteria.setOrgId(String.valueOf(jbForm.getParentOrgId()));
      jbForm.setSerachCriteria(serachCriteria);
      request.setAttribute("searchpagedisplay", "no");
      
      return mapping.findForward("ownorgjobreqList");
    }
    catch (Exception e)
    {
      String msg = Constant.getResourceStringValue("server.error", user1.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
    }
    return mapping.findForward("exception");
  }
  
  public ActionForward searchownorgjobreqList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchownorgjobreqList method");
    

    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    

    RequisitionSearchCriteriaMultiple serachCriteria = new RequisitionSearchCriteriaMultiple();
    
    RequistionSearchUtil.setRequisitionSearchCriteriaMultiple(jbForm, serachCriteria);
    
    BOFactory.getJobRequistionBO().searchreqlistpageLovs(jbForm, user1);
    request.setAttribute("searchpagedisplay", "yes");
    return mapping.findForward("ownorgjobreqList");
  }
  
  public ActionForward ownorgjobreqListpage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchownorgreqs method");
    int results = -1;
    int startIndex = 0;
    String sort = "username";
    String dir = "asc";
    
    User user1 = (User)request.getSession().getAttribute("user_data");
    
    String results_str = request.getParameter("results");
    String startIndex_str = request.getParameter("startIndex");
    String sort_str = request.getParameter("sort");
    String dir_str = request.getParameter("dir");
    
    RequisitionSearchCriteriaMultiple searchCriteria = new RequisitionSearchCriteriaMultiple();
    RequistionSearchUtil.setCriteriaFromRequest(request, searchCriteria);
    
    List<Long> orgIdList = new ArrayList();
    orgIdList.add(Long.valueOf(user1.getOrganization().getOrgId()));
    searchCriteria.setOrgIdList(orgIdList);
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
    










    Map m = BOFactory.getJobRequistionBO().searchJobRequisitions(user1, searchCriteria, results, startIndex, dir_str, sort_str);
    dataList = (List)m.get(Common.REQUISTION_LIST);
    totalSize = ((Integer)m.get(Common.REQUISTION_COUNT)).intValue();
    





    String[] fields = ScreenSettingUtils.getArrayOfApplicationScreenSettingsKeyListPage("ALL_REQUISION_SCREEN");
    


    String data = "{\n\"recordsReturned\":" + dataList.size() + "," + "\n" + "\"" + "totalRecords" + "\"" + ":" + totalSize + "," + "\n" + "\"" + "startIndex" + "\"" + ":" + startIndex + "," + "\n" + "\"" + "sort" + "\"" + ":" + "\"" + sort + "\"" + "," + "\n" + "\"" + "dir" + "\"" + ":" + "\"" + dir + "\"" + "," + "\n" + "\"" + "pageSize" + "\"" + ":" + results + "," + "\n" + "\"" + "records" + "\"" + ":" + StringUtils.createJasonDataTable(dataList, fields, user1) + "}";
    







    request.setAttribute("data", data);
    return mapping.findForward("ownorgjobreqListpage");
  }
  
  public ActionForward myjobreqList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside myjobreqList method");
    
    String state = request.getParameter("state");
    

    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    RequisitionSearchCriteriaMultiple serachCriteria = new RequisitionSearchCriteriaMultiple();
    serachCriteria.setState(state);
    String[] states = new String[1];
    states[0] = state;
    jbForm.setStates(states);
    jbForm.setSerachCriteria(serachCriteria);
    try
    {
      BOFactory.getJobRequistionBO().searchreqlistpageLovs(jbForm, user1);
      request.setAttribute("searchpagedisplay", "no");
      
      return mapping.findForward("myjobreqList");
    }
    catch (Exception e)
    {
      String msg = Constant.getResourceStringValue("server.error", user1.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
    }
    return mapping.findForward("exception");
  }
  
  public ActionForward myjobreqListRecruiter(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside myjobreqListRecruiter method");
    
    String state = request.getParameter("state");
    

    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    RequisitionSearchCriteriaMultiple serachCriteria = new RequisitionSearchCriteriaMultiple();
    if (!StringUtils.isNullOrEmpty(state))
    {
      serachCriteria.setState(state);
      String[] states = new String[1];
      states[0] = state;
      jbForm.setStates(states);
    }
    jbForm.setSerachCriteria(serachCriteria);
    try
    {
      BOFactory.getJobRequistionBO().searchreqlistpageLovs(jbForm, user1);
      request.setAttribute("searchpagedisplay", "no");
      
      return mapping.findForward("myjobreqListRecruiter");
    }
    catch (Exception e)
    {
      String msg = Constant.getResourceStringValue("server.error", user1.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
    }
    return mapping.findForward("exception");
  }
  
  public ActionForward searchMyReqlistpageRecruiter(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchMyReqlistpage method");
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    RequisitionSearchCriteriaMultiple serachCriteria = new RequisitionSearchCriteriaMultiple();
    
    RequistionSearchUtil.setRequisitionSearchCriteriaMultiple(jbForm, serachCriteria);
    BOFactory.getJobRequistionBO().searchreqlistpageLovs(jbForm, user1);
    request.setAttribute("searchpagedisplay", "yes");
    return mapping.findForward("myjobreqListRecruiter");
  }
  
  public ActionForward jobreqListpageRecruiter(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside jobreqListpageRecruiter method");
    int results = -1;
    int startIndex = 0;
    String sort = "username";
    String dir = "asc";
    
    User user1 = (User)request.getSession().getAttribute("user_data");
    

    String results_str = request.getParameter("results");
    String startIndex_str = request.getParameter("startIndex");
    String sort_str = request.getParameter("sort");
    String dir_str = request.getParameter("dir");
    
    RequisitionSearchCriteriaMultiple searchCriteria = new RequisitionSearchCriteriaMultiple();
    RequistionSearchUtil.setCriteriaFromRequest(request, searchCriteria);
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
    DataTableBean dtbean = new DataTableBean();
    
    dtbean = BOFactory.getJobRequistionBO().getMyJobRequistionsForPaginationRecruiter(String.valueOf(user1.getUserId()), searchCriteria, results, startIndex, dir_str, sort_str);
    

    String[] fields = ScreenSettingUtils.getArrayOfApplicationScreenSettingsKeyListPage("MY_REQUISITION_SCREEN");
    


    String data = "{\n\"recordsReturned\":" + dtbean.getValueList().size() + "," + "\n" + "\"" + "totalRecords" + "\"" + ":" + dtbean.getTotalcount() + "," + "\n" + "\"" + "startIndex" + "\"" + ":" + startIndex + "," + "\n" + "\"" + "sort" + "\"" + ":" + "\"" + sort + "\"" + "," + "\n" + "\"" + "dir" + "\"" + ":" + "\"" + dir + "\"" + "," + "\n" + "\"" + "pageSize" + "\"" + ":" + results + "," + "\n" + "\"" + "records" + "\"" + ":" + StringUtils.createJasonDataTable(dtbean.getValueList(), fields, user1) + "}";
    








    request.setAttribute("data", data);
    return mapping.findForward("jobreqListpageRecruiter");
  }
  
  public ActionForward searchMyReqlistpage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchMyReqlistpage method");
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    RequisitionSearchCriteriaMultiple serachCriteria = new RequisitionSearchCriteriaMultiple();
    
    RequistionSearchUtil.setRequisitionSearchCriteriaMultiple(jbForm, serachCriteria);
    BOFactory.getJobRequistionBO().searchreqlistpageLovs(jbForm, user1);
    request.setAttribute("searchpagedisplay", "yes");
    return mapping.findForward("myjobreqList");
  }
  
  public ActionForward jobreqListpageHiringMgr(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside jobreqListpageHiringMgr method");
    int results = -1;
    int startIndex = 0;
    String sort = "username";
    String dir = "asc";
    
    User user1 = (User)request.getSession().getAttribute("user_data");
    

    String results_str = request.getParameter("results");
    String startIndex_str = request.getParameter("startIndex");
    String sort_str = request.getParameter("sort");
    String dir_str = request.getParameter("dir");
    
    RequisitionSearchCriteriaMultiple searchCriteria = new RequisitionSearchCriteriaMultiple();
    RequistionSearchUtil.setCriteriaFromRequest(request, searchCriteria);
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
    DataTableBean dtbean = new DataTableBean();
    
    dtbean = BOFactory.getJobRequistionBO().getMyJobRequistionsForPagination(String.valueOf(user1.getUserId()), user1.getUserName(), searchCriteria, results, startIndex, dir_str, sort_str);
    

    String[] fields = ScreenSettingUtils.getArrayOfApplicationScreenSettingsKeyListPage("MY_REQUISITION_SCREEN");
    


    String data = "{\n\"recordsReturned\":" + dtbean.getValueList().size() + "," + "\n" + "\"" + "totalRecords" + "\"" + ":" + dtbean.getTotalcount() + "," + "\n" + "\"" + "startIndex" + "\"" + ":" + startIndex + "," + "\n" + "\"" + "sort" + "\"" + ":" + "\"" + sort + "\"" + "," + "\n" + "\"" + "dir" + "\"" + ":" + "\"" + dir + "\"" + "," + "\n" + "\"" + "pageSize" + "\"" + ":" + results + "," + "\n" + "\"" + "records" + "\"" + ":" + StringUtils.createJasonDataTable(dtbean.getValueList(), fields, user1) + "}";
    








    request.setAttribute("data", data);
    return mapping.findForward("jobreqListpageHiringMgr");
  }
  
  public ActionForward exportsearchrequisitiondata(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside exportsearchrequisitiondata method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    
    String statuscri = request.getParameter("statuscri");
    String statecri = request.getParameter("statecri");
    logger.info("statuscri" + statuscri);
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    jbForm.setStatuscri(statuscri);
    jbForm.setStatecri(statecri);
    logger.info("jbForm.getParentOrgId()" + jbForm.getParentOrgId());
    jbForm.setJobgradeId(jbForm.getJobgradeId());
    jbForm.setJobtypeId(jbForm.getJobtypeId());
    jbForm.setParentOrgId(jbForm.getParentOrgId());
    jbForm.setDepartmentId(jbForm.getDepartmentId());
    jbForm.setJobreqName(jbForm.getJobreqName());
    jbForm.setJobreqId(jbForm.getJobreqId());
    jbForm.setJobreqcode(jbForm.getJobreqcode());
    jbForm.setBudgetcodeId(jbForm.getBudgetcodeId());
    BOFactory.getJobRequistionBO().searchreqlistpageLovs(jbForm, user1);
    
    ResuistionSearchCriteria citeria = new ResuistionSearchCriteria();
    citeria.setState(statecri);
    citeria.setStatus(statuscri);
    citeria.setJobgradeId(jbForm.getJobgradeId());
    citeria.setJobtypeId(jbForm.getJobtypeId());
    citeria.setJobreqcode(jbForm.getJobreqcode());
    citeria.setJobreqName(jbForm.getJobreqName());
    citeria.setJobreqId(jbForm.getJobreqId());
    citeria.setOrgId(jbForm.getParentOrgId());
    citeria.setDepartmentId(jbForm.getDepartmentId());
    citeria.setBudgetcodeId(jbForm.getBudgetcodeId());
    
    BulkUploadTask task = new BulkUploadTask();
    ExportTaskUtil exp = new ExportTaskUtil();
    exp.setUser(user1);
    exp.setTask(task);
    exp.setTasktype(Common.EXPORT_REQUISTIONS_SEARCH_RESULTS);
    exp.setReqCriteria(citeria);
    BulkTaskManager.export(exp);
    
    request.setAttribute("searchpagedisplay", "yes");
    request.setAttribute("exportreqisiontionstarted", "yes");
    return mapping.findForward("jobreqList");
  }
  
  public ActionForward jobsearch(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside jobsearch method");
    
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    List locationlist = BOFactory.getJobRequistionBO().getLocationList(user1.getSuper_user_key());
    jbForm.setLocationList(locationlist);
    return mapping.findForward("jobsearch");
  }
  
  public ActionForward jobsearchsubmit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside jobsearchsubmit method");
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    
    String searchposteddate = request.getParameter("searchposteddate");
    String cri = request.getParameter("cri");
    jbForm.setAppliedcri(cri);
    jbForm.setSearchposteddate(searchposteddate);
    jbForm.setJobTitle(jbForm.getJobTitle());
    List locationlist = BOFactory.getJobRequistionBO().getLocationList(user1.getSuper_user_key());
    jbForm.setLocationList(locationlist);
    request.setAttribute("searchpagedisplay", "yes");
    return mapping.findForward("jobsearch");
  }
  
  public ActionForward jobreqtreelist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside jobreqtreelist method");
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    return mapping.findForward("jobreqtreelist");
  }
  
  public ActionForward recruiterjobreqtreelist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside recruiterjobreqtreelist method");
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    return mapping.findForward("recruiterjobreqtreelist");
  }
  
  public ActionForward jobreqtreelistajax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside jobreqtreelistajax method");
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    return mapping.findForward("jobreqtreelist");
  }
  
  public ActionForward recruiterjobreqtreelistajax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside recruiterjobreqtreelistajax method");
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    return mapping.findForward("recruiterjobreqtreelist");
  }
  
  public ActionForward jobreqtreelistorg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside jobreqtreelist method");
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    return mapping.findForward("jobreqtreelistbyorg");
  }
  
  public ActionForward applicantlist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside applicantlist method");
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    return mapping.findForward("jobreqtreelist");
  }
  
  public ActionForward recruiterapplicantlist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside recruiterapplicantlist method");
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    return mapping.findForward("recruiterjobreqtreelist");
  }
  
  public ActionForward requistionapplicantlist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside requistionapplicantlist method");
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    JobRequisitionForm jbform = (JobRequisitionForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    String backurl = request.getParameter("backurl");
    
    String requistionId = request.getParameter("requistionId");
    String state = request.getParameter("state");
    


    JobRequisition jb = BOFactory.getJobRequistionBO().getJobRequision(requistionId);
    if (jb == null)
    {
      request.setAttribute("NO_PERMISSION", "yes");
    }
    else
    {
      boolean isReqReadPermission = PermissionBO.isRequistionHeaderTreeAllowed(user1, jb.getHiringmgr().getUserId(), jb.getRecruiterId(), jb.getIsgrouprecruiter());
      if (!isReqReadPermission) {
        request.setAttribute("NO_PERMISSION", "yes");
      }
    }
    request.setAttribute("requiId2", requistionId);
    request.setAttribute("state2", state);
    if (backurl != null) {
      request.setAttribute("backurl", backurl);
    }
    return mapping.findForward("jobrequistiontreelist");
  }
  
  public ActionForward searchmenuheader(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchmenuheader method");
    String search = request.getParameter("search");
    String searchtype = request.getParameter("searchtypeval");
    logger.info("Inside searchmenuheader method search" + search);
    logger.info("Inside searchmenuheader method searchtype" + searchtype);
    
    User user1 = (User)request.getSession().getAttribute("user_data");
    try
    {
      if ((!StringUtils.isNullOrEmpty(searchtype)) && (searchtype.equalsIgnoreCase("reqno")) && (!StringUtils.isNullOrEmpty("search")))
      {
        JobRequisition jb = BOFactory.getJobRequistionBO().getJobRequisionByNumber(new Long(search).longValue(), user1.getSuper_user_key());
        if (jb == null) {
          return mapping.findForward("searchmenuheadererror");
        }
        if (jb != null)
        {
          boolean isReqReadPermission = PermissionBO.isRequistionReadAllowed(user1, jb.getHiringmgr().getUserId(), jb.getRecruiterId(), jb.getIsgrouprecruiter());
          if (!isReqReadPermission) {
            return mapping.findForward("searchmenuheadererror");
          }
          request.setAttribute("reqid", search);
          return mapping.findForward("searchmenuheader");
        }
      }
      else if ((!StringUtils.isNullOrEmpty(searchtype)) && (searchtype.equalsIgnoreCase("reqcode")) && (!StringUtils.isNullOrEmpty("search")))
      {
        JobRequisition jb = BOFactory.getJobRequistionBO().getJobRequisionByJobCode(search, user1.getSuper_user_key());
        if (jb == null) {
          return mapping.findForward("searchmenuheadererror");
        }
        if (jb != null)
        {
          boolean isReqReadPermission = PermissionBO.isRequistionReadAllowed(user1, jb.getHiringmgr().getUserId(), jb.getRecruiterId(), jb.getIsgrouprecruiter());
          if (!isReqReadPermission) {
            return mapping.findForward("searchmenuheadererror");
          }
          request.setAttribute("reqid", String.valueOf(jb.getJobreqId()));
          return mapping.findForward("searchmenuheader");
        }
      }
      else if ((!StringUtils.isNullOrEmpty(searchtype)) && (searchtype.equalsIgnoreCase("reqname")) && (!StringUtils.isNullOrEmpty("search")))
      {
        List rlist = BOFactory.getJobRequistionBO().getJobRequisionByName(search, user1.getSuper_user_key());
        if ((rlist != null) && (rlist.size() == 1))
        {
          JobRequisition jb = (JobRequisition)rlist.get(0);
          boolean isReqReadPermission = PermissionBO.isRequistionReadAllowed(user1, jb.getHiringmgr().getUserId(), jb.getRecruiterId(), jb.getIsgrouprecruiter());
          if (!isReqReadPermission) {
            return mapping.findForward("searchmenuheadererror");
          }
          request.setAttribute("reqid", String.valueOf(jb.getJobreqId()));
          return mapping.findForward("searchmenuheader");
        }
        boolean isReqReadPermission = PermissionBO.isRequistionReadAllowed(user1);
        if (!isReqReadPermission) {
          return mapping.findForward("searchmenuheadererror");
        }
        request.setAttribute("reqid", "0");
        request.setAttribute("searchpage", search);
        return mapping.findForward("searchmenuheader");
      }
    }
    catch (Exception e)
    {
      return mapping.findForward("searchmenuheadererror");
    }
    return mapping.findForward("searchmenuheadererror");
  }
  
  public ActionForward applicantliststatustree(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside applicantlisttree method");
    

    String jobreqId = request.getParameter("requistionId");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    JobRequisition jb = BOFactory.getJobRequistionBO().getJobRequision(jobreqId);
    request.setAttribute("jobrequisitiondata", jb);
    return mapping.findForward("jobreqtreelistbyorg");
  }
  
  public ActionForward ajaxapplicantlist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside ajaxapplicantlist method");
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    return mapping.findForward("ajaxapplicantlist");
  }
  
  public ActionForward jobreqtreelistbyorg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside jobreqtreelistbyorg method");
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    User user1 = (User)request.getSession().getAttribute("user_data");
    logger.info("user1.getOrgId()" + user1.getOrganization().getOrgId());
    request.setAttribute("orgid", "" + user1.getOrganization().getOrgId());
    
    return mapping.findForward("jobreqtreelistbyorg");
  }
  
  public ActionForward jobRefUrl(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside jobRefUrl method");
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    User user1 = (User)request.getSession().getAttribute("user_data");
    
    String reqid = request.getParameter("reqid");
    String secureid = request.getParameter("secureid");
    String source = request.getParameter("source");
    
    String externalJobUrl = "jobs.do?method=jobdetailsn&reqid=" + reqid + "&secureid=" + secureid;
    
    String tempurl = externalJobUrl + "&source=" + source;
    
    SocialUrlMapping somap = BOFactory.getJobRequistionBO().createSocialUrlMappingKey(tempurl, secureid + "-" + source);
    
    return null;
  }
  
  public ActionForward myapprovaljobreqlist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside myapprovaljobreqlist method");
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    return mapping.findForward("myapprovaljobreqlist");
  }
  
  public ActionForward createjobreq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside jobreqList method");
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    List orgList = BOFactory.getLovBO().getAllOrganization(user1.getSuper_user_key());
    jbForm.setOrganizationList(orgList);
    List deptlist = new ArrayList();
    jbForm.setDepartmentList(deptlist);
    List projectCodelist = new ArrayList();
    jbForm.setProjectcodeList(projectCodelist);
    if (orgList.size() > 0)
    {
      Organization org = (Organization)orgList.get(0);
      jbForm.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(org.getOrgId())));
    }
    List budgetcodelist = new ArrayList();
    if ((orgList.size() > 0) && (jbForm.getDepartmentList().size() > 0))
    {
      Organization org = (Organization)orgList.get(0);
      Department dept = (Department)jbForm.getDepartmentList().get(0);
      budgetcodelist = BOFactory.getLovBO().getAllBudgetCodeDetailsbyorganizationanddepartment(String.valueOf(org.getOrgId()), String.valueOf(dept.getDepartmentId()));
      
      jbForm.setBudgetcodeList(budgetcodelist);
      


      projectCodelist = BOFactory.getLovTXBO().getProjectCodesByDept(String.valueOf(dept.getDepartmentId()));
      jbForm.setProjectcodeList(projectCodelist);
    }
    else if (orgList.size() > 0)
    {
      Organization org = (Organization)orgList.get(0);
      budgetcodelist = BOFactory.getLovBO().getAllBudgetCodeDetailsbyorganizationanddepartment(String.valueOf(org.getOrgId()), String.valueOf(0));
      
      jbForm.setBudgetcodeList(budgetcodelist);
    }
    List locationList = BOFactory.getLovBO().getLocationList();
    jbForm.setLocationList(locationList);
    List jobgradeList = BOFactory.getLovBO().getAllJobGradeDetails(user1.getSuper_user_key());
    jbForm.setJobgradeList(jobgradeList);
    List salaryplanList = BOFactory.getLovBO().getAllSalaryDetails(user1);
    jbForm.setSalaryplanList(salaryplanList);
    List refferalSchemeList = BOFactory.getLovBO().getAllRefferalSchemeDetails();
    jbForm.setRefferalSchemeList(refferalSchemeList);
    jbForm.setAgencyRefferalSchemeList(refferalSchemeList);
    List jobTemplateList = BOFactory.getJobRequistionBO().getRequisitionsTemplateActiveList(user1.getSuper_user_key());
    
    logger.info("*** size of jobTemplateList : " + jobTemplateList.size());
    jbForm.setJobTemplateList(jobTemplateList);
    
    jbForm.setStdworkinghoursunitList(Constant.getStdworkinghoursunitList());
    logger.info("*** size of getStdworkinghoursunitList : " + jbForm.getStdworkinghoursunitList().size());
    jbForm.setEducationNamesList(BOFactory.getLovTXBO().getEducationListKeyValue(user1.getSuper_user_key()));
    return mapping.findForward("createjobreq");
  }
  
  private String checkRequitionNoWithPackage(User user)
  {
    String msg = null;
    List reqList = BOFactory.getJobRequistionBO().getRequisitionsActiveAndOpenListForSuperUser(user.getSuper_user_key());
    int size = reqList.size();
    if ((user.getPackagetaken() != null) && (user.getPackagetaken().equals("BASIC-FREE")))
    {
      if (size >= 1) {
        msg = "Upgrade your package , with existing package you can open 1 job at a time";
      }
    }
    else if ((user.getPackagetaken() != null) && (user.getPackagetaken().equals("BASIC")))
    {
      if (size >= 6) {
        msg = "Upgrade your package , with existing package you can open 6 jobs at a time";
      }
    }
    else if ((user.getPackagetaken() != null) && (user.getPackagetaken().equals("ADVANCE")))
    {
      if (size >= 12) {
        msg = "Upgrade your package , with existing package you can open 12 jobs at a time";
      }
    }
    else if ((user.getPackagetaken() != null) && (user.getPackagetaken().equals("FREE")) && 
      (size >= 3)) {
      msg = "Upgrade your package , free trial account can open 3 jobs at a time";
    }
    return msg;
  }
  
  public ActionForward copytemplatetojob(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside copytemplatetojob method");
    String withtml = request.getParameter("withtml");
    logger.info("Inside copytemplatetojob method withtml" + withtml);
    if (StringUtils.isNullOrEmpty(withtml)) {
      withtml = "false";
    }
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    
    String errMsg = checkRequitionNoWithPackage(user1);
    if (!StringUtils.isNullOrEmpty(errMsg))
    {
      List jobTemplateList = BOFactory.getJobRequistionBO().getRequisitionsTemplateActiveList(user1.getSuper_user_key());
      jbForm.setJobTemplateList(jobTemplateList);
      request.setAttribute("errMsg", errMsg);
      return mapping.findForward("createjobreq");
    }
    try
    {
      JobRequisition jb = BOFactory.getJobRequistionBO().copytemplatetojob(user1, jbForm.getTemplateId(), jbForm.getJobreqName(), withtml);
      


      jbForm.setJobreqId(jb.getJobreqId());
      jbForm.setStdworkinghoursunitList(Constant.getStdworkinghoursunitList());
      jbForm.setEducationNamesList(BOFactory.getLovTXBO().getEducationListKeyValue(user1.getSuper_user_key()));
      List jobTemplateList = BOFactory.getJobRequistionBO().getRequisitionsTemplateActiveList(user1.getSuper_user_key());
      request.getSession().setAttribute("req_id_for_menu", String.valueOf(jb.getJobreqId()));
      try
      {
        BOFactory.getJobRequistionBO().sendEmailForRequisition(user1, jb, null, Common.REQUISITION_CREATION, EmailNotificationSettingFunction.ReqFunctionNames.REQUISITION_CREATION.toString());
      }
      catch (Exception e)
      {
        logger.info("error on sendEmailForRequisition", e);
      }
      logger.info("*** size of jobTemplateList : " + jobTemplateList.size());
      jbForm.setJobTemplateList(jobTemplateList);
      logger.info("Inside copytemplatetojob method end");
      return mapping.findForward("createjobreq");
    }
    catch (Exception e)
    {
      String msg = Constant.getResourceStringValue("server.error", user1.getLocale());
      if (e.getMessage() == null) {
        request.setAttribute(Common.ERROR_MSG, msg);
      } else {
        request.setAttribute(Common.ERROR_MSG, e.getMessage());
      }
    }
    return mapping.findForward("exception");
  }
  
  public ActionForward saveCompetency(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveCompetency method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    
    String reqId = request.getParameter("reqId");
    String competencyname = request.getParameter("competencyname");
    String iscompmandatory = request.getParameter("iscompmandatory");
    String minimumrating = request.getParameter("minimumrating");
    String type = request.getParameter("type");
    String isVisible = request.getParameter("isVisible");
    logger.info("reqId" + reqId);
    logger.info("competencyname" + competencyname);
    logger.info("minimumrating" + minimumrating);
    logger.info("type" + type);
    try
    {
      JobTemplateCompetency jbtc = new JobTemplateCompetency();
      jbtc.setJbTmplId(new Long(reqId).longValue());
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
  
  public ActionForward initiateApprovalscr(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside initiateApprovalscr method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    String jobreqId = request.getParameter("jobreqId");
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    try
    {
      jbForm.setJobreqId(new Long(jobreqId).longValue());
      BOFactory.getJobRequistionBO().initiateApprovalscr(jbForm, request, user1);
      List errorList = (List)request.getAttribute("error_list");
      if (((errorList == null) || (errorList.size() == 0)) && ((jbForm.getApproversList() == null) || (jbForm.getApproversList().size() == 0)))
      {
        String url = "/jobreq.do?method=publishJobReqscr&jobreqId=" + jbForm.getJobreqId();
        ActionForward forward = new ActionForward(url);
        forward.setRedirect(true);
        return forward;
      }
      return mapping.findForward("initiateApprovalscr");
    }
    catch (Exception e)
    {
      String msg = Constant.getResourceStringValue("server.error", user1.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
    }
    return mapping.findForward("exception");
  }
  
  public ActionForward publishJobReqscr(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside publishJobReqscr method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    String jobreqId = request.getParameter("jobreqId");
    List refferalSchemeList = BOFactory.getLovBO().getAllRefferalSchemeDetails();
    jbForm.setRefferalSchemeList(refferalSchemeList);
    jbForm.setAgencyRefferalSchemeList(refferalSchemeList);
    try
    {
      jbForm.setJobreqId(new Long(jobreqId).longValue());
      JobRequisition jb = BOFactory.getJobRequistionBO().publishJobReqscr(jbForm, request, user1);
      


      return mapping.findForward("publishJobReqscr");
    }
    catch (Exception e)
    {
      String msg = Constant.getResourceStringValue("server.error", user1.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
    }
    return mapping.findForward("exception");
  }
  
  public ActionForward initiateApproval(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside initiateApproval method");
    String jobreqId = request.getParameter("jobreqId");
    String comment = request.getParameter("comment");
    User user1 = (User)request.getSession().getAttribute("user_data");
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    try
    {
      ActionErrors errors = new ActionErrors();
      ActionForward forward = new ActionForward();
      
      jbForm.setJobreqId(new Long(jobreqId).intValue());
      




      JobRequisition jb = BOFactory.getJobRequistionTXBO().initiateapproval(jbForm, user1, comment);
      BOFactory.getJobRequistionBO().saveRequisitionComment(jb.getUuid(), user1, "Initiate Approval", comment);
      try
      {
        BOFactory.getJobRequistionBO().sendEmailForRequisition(user1, jb, comment, Common.INITIATE_APPROVAL_REQUISITION, EmailNotificationSettingFunction.ReqFunctionNames.INITIATE_APPROVAL_REQUISITION.toString());
      }
      catch (Exception e)
      {
        logger.info("error on sendEmailForRequisition", e);
      }
      request.setAttribute("initiateApproval", "yes");
      return mapping.findForward("initiateApprovalscr");
    }
    catch (ObjectNotFoundException e)
    {
      String[] args = { String.valueOf(jobreqId) };
      String msg = Constant.getResourceStringValue("requistion.notfound", user1.getLocale(), args);
      request.setAttribute(Common.ERROR_MSG, msg);
      jbForm.setTabselected(1);
      return mapping.findForward("exception");
    }
    catch (ValidationException e)
    {
      String msg = Constant.getResourceStringValue("server.error", user1.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
      jbForm.setTabselected(1);
      return mapping.findForward("exception");
    }
    catch (Exception e)
    {
      String msg = Constant.getResourceStringValue("server.error", user1.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
      jbForm.setTabselected(1);
    }
    return mapping.findForward("exception");
  }
  
  public ActionForward deletepublishtovendor(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deletepublishtovendor method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    String jobreqId = request.getParameter("jobreqId");
    String publishtovendorId = request.getParameter("publishtovendorId");
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    try
    {
      jbForm.setJobreqId(new Long(jobreqId).longValue());
      

      JobRequisition jb = BOFactory.getJobRequistionTXBO().deletepublishtovendor(jbForm, publishtovendorId, user1);
      



      jbForm.fromValue(jb, request);
      
      jbForm.setTabselected(8);
      
      request.setAttribute("deletevendor", "yes");
      return mapping.findForward("editjobreq");
    }
    catch (Exception e)
    {
      String msg = Constant.getResourceStringValue("server.error", user1.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
    }
    return mapping.findForward("exception");
  }
  
  public ActionForward savepublishinfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside savepublishinfo method **** ");
    User user1 = (User)request.getSession().getAttribute("user_data");
    String jobreqId = request.getParameter("jobreqId");
    String publishToExternal = request.getParameter("publishToExternal");
    String publishToEmpRef = request.getParameter("publishToEmpRef");
    String eeoInfoIncluded = request.getParameter("eeoInfoIncluded");
    String userids = request.getParameter("userids");
    

    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    List errorList = new ArrayList();
    Set<RefferalScheme> employeeReferralSchemes = new HashSet();
    Set<RefferalScheme> agencyReferralScheme = new HashSet();
    try
    {
      ActionErrors errors = new ActionErrors();
      ActionForward forward = new ActionForward();
      
      jbForm.setJobreqId(new Long(jobreqId).longValue());
      
      logger.info("setting employee ref schemes");
      
      setEmployeeReferralSchemes(employeeReferralSchemes, request);
      setAgencyReferralSchemes(agencyReferralScheme, request);
      
      jbForm.setEmployeeReferralSchemeList(employeeReferralSchemes);
      jbForm.setAgencyReferralSchemeList(agencyReferralScheme);
      
      Set uservendorList = new HashSet();
      

      JobRequisition jb = BOFactory.getJobRequistionTXBO().savepublishinfo(jbForm, user1, publishToExternal, publishToEmpRef, eeoInfoIncluded, userids, errorList);
      

      jb = BOFactory.getJobRequistionBO().getJobRequision(jobreqId);
      
      jbForm.fromValue(jb, request);
      logger.info(" before ***** ");
      
      saveUpdatePublishJobBoards(request, jobreqId, user1.getUserName());
      




      jbForm.setTabselected(8);
      request.setAttribute("savepublishinfo", "yes");
      return mapping.findForward("editjobreq");
    }
    catch (ObjectNotFoundException e)
    {
      String[] args = { String.valueOf(jobreqId) };
      String msg = Constant.getResourceStringValue("requistion.notfound", user1.getLocale(), args);
      request.setAttribute(Common.ERROR_MSG, msg);
      return mapping.findForward("exception");
    }
    catch (ValidationException e)
    {
      JobRequisition jb = BOFactory.getJobRequistionBO().getJobRequision(jobreqId);
      BOFactory.getJobRequistionTXBO().commonLovPopulate(jbForm, jb, user1);
      jbForm.fromValue(jb, request);
      BOFactory.getJobRequistionBO().setPublishSaveErrorCondition(jb, jbForm, userids, request, employeeReferralSchemes, agencyReferralScheme, user1);
      
      jbForm.setTabselected(8);
      request.setAttribute("error_list_publish", errorList);
      return mapping.findForward("editjobreq");
    }
    catch (Exception e)
    {
      String msg = Constant.getResourceStringValue("server.error", user1.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
    }
    return mapping.findForward("exception");
  }
  
  public void saveUpdatePublishJobBoards(HttpServletRequest request, String jobreqId, String username)
  {
    logger.info("Inside saveUpdatePublishJobBoards method");
    String juju = request.getParameter("juju");
    String olx = request.getParameter("olx");
    String oodle = request.getParameter("oodle");
    String trovit = request.getParameter("trovit");
    String twitjobsearch = request.getParameter("twitjobsearch");
    String workhound = request.getParameter("workhound");
    String yakaz = request.getParameter("yakaz");
    String indeed = request.getParameter("indeed");
    String simplyhired = request.getParameter("simplyhired");
    String jooble = request.getParameter("jooble");
    String careervital = request.getParameter("careervital");
    String jobdiesel = request.getParameter("jobdiesel");
    


    logger.info("juju >> " + juju);
    logger.info("olx >> " + olx);
    logger.info("oodle >> " + oodle);
    logger.info("trovit >> " + trovit);
    logger.info("twitjobsearch >> " + twitjobsearch);
    logger.info("workhound >> " + workhound);
    logger.info("yakaz >> " + yakaz);
    logger.info("indeed >> " + indeed);
    logger.info("simplyhired >> " + simplyhired);
    logger.info("jobdiesel >> " + jobdiesel);
    if ((!StringUtils.isNullOrEmpty(juju)) && (juju.equals("true")))
    {
      boolean flag = BOFactory.getJobRequistionBO().isjobBoardCodeExist(jobreqId, "JUJU");
      if (flag)
      {
        PublishJobBoards publishJobBoard = BOFactory.getJobRequistionBO().getPublishJobBoardsByReqIdandjobBoardCode(jobreqId, "JUJU");
        publishJobBoard.setUpdatedBy(username);
        publishJobBoard.setUpdatedDate(new Date());
        publishJobBoard.setStatus("P");
        BOFactory.getJobRequistionBO().updatePublishJobBoards(publishJobBoard);
      }
      else
      {
        PublishJobBoards publishJobBoards = new PublishJobBoards();
        JobRequisition jb2 = new JobRequisition();
        jb2.setJobreqId(new Long(jobreqId).longValue());
        publishJobBoards.setJobRequisition(jb2);
        publishJobBoards.setCreatedBy(username);
        publishJobBoards.setCreatedDate(new Date());
        publishJobBoards.setStatus("P");
        publishJobBoards.setJobBoardCode("JUJU");
        
        BOFactory.getJobRequistionBO().savePublishJobBoards(publishJobBoards);
      }
    }
    else
    {
      boolean flag = BOFactory.getJobRequistionBO().isjobBoardCodeExist(jobreqId, "JUJU");
      if (flag)
      {
        PublishJobBoards publishJobBoards = BOFactory.getJobRequistionBO().getPublishJobBoardsByReqIdandjobBoardCode(jobreqId, "JUJU");
        

        publishJobBoards.setStatus("I");
        publishJobBoards.setUpdatedBy(username);
        publishJobBoards.setUpdatedDate(new Date());
        BOFactory.getJobRequistionBO().updatePublishJobBoards(publishJobBoards);
      }
      else
      {
        PublishJobBoards publishJobBoards = new PublishJobBoards();
        JobRequisition jb2 = new JobRequisition();
        jb2.setJobreqId(new Long(jobreqId).longValue());
        publishJobBoards.setJobRequisition(jb2);
        publishJobBoards.setCreatedBy(username);
        publishJobBoards.setCreatedDate(new Date());
        publishJobBoards.setStatus("I");
        publishJobBoards.setJobBoardCode("JUJU");
        
        BOFactory.getJobRequistionBO().savePublishJobBoards(publishJobBoards);
      }
    }
    if ((!StringUtils.isNullOrEmpty(olx)) && (olx.equals("true")))
    {
      boolean flag = BOFactory.getJobRequistionBO().isjobBoardCodeExist(jobreqId, "OLX");
      if (flag)
      {
        PublishJobBoards publishJobBoard2 = BOFactory.getJobRequistionBO().getPublishJobBoardsByReqIdandjobBoardCode(jobreqId, "OLX");
        publishJobBoard2.setUpdatedBy(username);
        publishJobBoard2.setUpdatedDate(new Date());
        publishJobBoard2.setStatus("P");
        BOFactory.getJobRequistionBO().updatePublishJobBoards(publishJobBoard2);
      }
      else
      {
        PublishJobBoards publishJobBoards = new PublishJobBoards();
        JobRequisition jb2 = new JobRequisition();
        jb2.setJobreqId(new Long(jobreqId).longValue());
        publishJobBoards.setJobRequisition(jb2);
        publishJobBoards.setCreatedBy(username);
        publishJobBoards.setCreatedDate(new Date());
        publishJobBoards.setJobBoardCode("OLX");
        publishJobBoards.setStatus("P");
        BOFactory.getJobRequistionBO().savePublishJobBoards(publishJobBoards);
      }
    }
    else
    {
      boolean flag = BOFactory.getJobRequistionBO().isjobBoardCodeExist(jobreqId, "OLX");
      if (flag)
      {
        PublishJobBoards publishJobBoards = BOFactory.getJobRequistionBO().getPublishJobBoardsByReqIdandjobBoardCode(jobreqId, "OLX");
        
        publishJobBoards.setStatus("I");
        publishJobBoards.setUpdatedBy(username);
        publishJobBoards.setUpdatedDate(new Date());
        BOFactory.getJobRequistionBO().updatePublishJobBoards(publishJobBoards);
      }
      else
      {
        PublishJobBoards publishJobBoards = new PublishJobBoards();
        JobRequisition jb2 = new JobRequisition();
        jb2.setJobreqId(new Long(jobreqId).longValue());
        publishJobBoards.setJobRequisition(jb2);
        publishJobBoards.setCreatedBy(username);
        publishJobBoards.setCreatedDate(new Date());
        publishJobBoards.setStatus("I");
        publishJobBoards.setJobBoardCode("OLX");
        
        BOFactory.getJobRequistionBO().savePublishJobBoards(publishJobBoards);
      }
    }
    if ((!StringUtils.isNullOrEmpty(oodle)) && (oodle.equals("true")))
    {
      boolean flag = BOFactory.getJobRequistionBO().isjobBoardCodeExist(jobreqId, "OODLE");
      if (flag)
      {
        PublishJobBoards publishJobBoard3 = BOFactory.getJobRequistionBO().getPublishJobBoardsByReqIdandjobBoardCode(jobreqId, "OODLE");
        publishJobBoard3.setUpdatedBy(username);
        publishJobBoard3.setUpdatedDate(new Date());
        publishJobBoard3.setStatus("P");
        BOFactory.getJobRequistionBO().updatePublishJobBoards(publishJobBoard3);
      }
      else
      {
        PublishJobBoards publishJobBoards = new PublishJobBoards();
        JobRequisition jb2 = new JobRequisition();
        jb2.setJobreqId(new Long(jobreqId).longValue());
        publishJobBoards.setJobRequisition(jb2);
        publishJobBoards.setCreatedBy(username);
        publishJobBoards.setCreatedDate(new Date());
        publishJobBoards.setJobBoardCode("OODLE");
        publishJobBoards.setStatus("P");
        BOFactory.getJobRequistionBO().savePublishJobBoards(publishJobBoards);
      }
    }
    else
    {
      boolean flag = BOFactory.getJobRequistionBO().isjobBoardCodeExist(jobreqId, "OODLE");
      if (flag)
      {
        PublishJobBoards publishJobBoards = BOFactory.getJobRequistionBO().getPublishJobBoardsByReqIdandjobBoardCode(jobreqId, "OODLE");
        
        publishJobBoards.setStatus("I");
        publishJobBoards.setUpdatedBy(username);
        publishJobBoards.setUpdatedDate(new Date());
        BOFactory.getJobRequistionBO().updatePublishJobBoards(publishJobBoards);
      }
      else
      {
        PublishJobBoards publishJobBoards = new PublishJobBoards();
        JobRequisition jb2 = new JobRequisition();
        jb2.setJobreqId(new Long(jobreqId).longValue());
        publishJobBoards.setJobRequisition(jb2);
        publishJobBoards.setCreatedBy(username);
        publishJobBoards.setCreatedDate(new Date());
        publishJobBoards.setStatus("I");
        publishJobBoards.setJobBoardCode("OODLE");
        
        BOFactory.getJobRequistionBO().savePublishJobBoards(publishJobBoards);
      }
    }
    if ((!StringUtils.isNullOrEmpty(trovit)) && (trovit.equals("true")))
    {
      boolean flag = BOFactory.getJobRequistionBO().isjobBoardCodeExist(jobreqId, "TROVIT");
      if (flag)
      {
        PublishJobBoards publishJobBoard4 = BOFactory.getJobRequistionBO().getPublishJobBoardsByReqIdandjobBoardCode(jobreqId, "TROVIT");
        publishJobBoard4.setUpdatedBy(username);
        publishJobBoard4.setUpdatedDate(new Date());
        publishJobBoard4.setStatus("P");
        BOFactory.getJobRequistionBO().updatePublishJobBoards(publishJobBoard4);
      }
      else
      {
        PublishJobBoards publishJobBoards = new PublishJobBoards();
        JobRequisition jb2 = new JobRequisition();
        jb2.setJobreqId(new Long(jobreqId).longValue());
        publishJobBoards.setJobRequisition(jb2);
        publishJobBoards.setCreatedBy(username);
        publishJobBoards.setCreatedDate(new Date());
        publishJobBoards.setJobBoardCode("TROVIT");
        publishJobBoards.setStatus("P");
        BOFactory.getJobRequistionBO().savePublishJobBoards(publishJobBoards);
      }
    }
    else
    {
      boolean flag = BOFactory.getJobRequistionBO().isjobBoardCodeExist(jobreqId, "TROVIT");
      if (flag)
      {
        PublishJobBoards publishJobBoards = BOFactory.getJobRequistionBO().getPublishJobBoardsByReqIdandjobBoardCode(jobreqId, "TROVIT");
        
        publishJobBoards.setStatus("I");
        publishJobBoards.setUpdatedBy(username);
        publishJobBoards.setUpdatedDate(new Date());
        BOFactory.getJobRequistionBO().updatePublishJobBoards(publishJobBoards);
      }
      else
      {
        PublishJobBoards publishJobBoards = new PublishJobBoards();
        JobRequisition jb2 = new JobRequisition();
        jb2.setJobreqId(new Long(jobreqId).longValue());
        publishJobBoards.setJobRequisition(jb2);
        publishJobBoards.setCreatedBy(username);
        publishJobBoards.setCreatedDate(new Date());
        publishJobBoards.setStatus("I");
        publishJobBoards.setJobBoardCode("TROVIT");
        
        BOFactory.getJobRequistionBO().savePublishJobBoards(publishJobBoards);
      }
    }
    if ((!StringUtils.isNullOrEmpty(twitjobsearch)) && (twitjobsearch.equals("true")))
    {
      boolean flag = BOFactory.getJobRequistionBO().isjobBoardCodeExist(jobreqId, "TWITJOBSEARCH");
      if (flag)
      {
        PublishJobBoards publishJobBoard5 = BOFactory.getJobRequistionBO().getPublishJobBoardsByReqIdandjobBoardCode(jobreqId, "TWITJOBSEARCH");
        publishJobBoard5.setUpdatedBy(username);
        publishJobBoard5.setUpdatedDate(new Date());
        publishJobBoard5.setStatus("P");
        BOFactory.getJobRequistionBO().updatePublishJobBoards(publishJobBoard5);
      }
      else
      {
        PublishJobBoards publishJobBoards = new PublishJobBoards();
        JobRequisition jb2 = new JobRequisition();
        jb2.setJobreqId(new Long(jobreqId).longValue());
        publishJobBoards.setJobRequisition(jb2);
        publishJobBoards.setCreatedBy(username);
        publishJobBoards.setCreatedDate(new Date());
        publishJobBoards.setJobBoardCode("TWITJOBSEARCH");
        publishJobBoards.setStatus("P");
        BOFactory.getJobRequistionBO().savePublishJobBoards(publishJobBoards);
      }
    }
    else
    {
      boolean flag = BOFactory.getJobRequistionBO().isjobBoardCodeExist(jobreqId, "TWITJOBSEARCH");
      if (flag)
      {
        PublishJobBoards publishJobBoards = BOFactory.getJobRequistionBO().getPublishJobBoardsByReqIdandjobBoardCode(jobreqId, "TWITJOBSEARCH");
        
        publishJobBoards.setStatus("I");
        publishJobBoards.setUpdatedBy(username);
        publishJobBoards.setUpdatedDate(new Date());
        BOFactory.getJobRequistionBO().updatePublishJobBoards(publishJobBoards);
      }
      else
      {
        PublishJobBoards publishJobBoards = new PublishJobBoards();
        JobRequisition jb2 = new JobRequisition();
        jb2.setJobreqId(new Long(jobreqId).longValue());
        publishJobBoards.setJobRequisition(jb2);
        publishJobBoards.setCreatedBy(username);
        publishJobBoards.setCreatedDate(new Date());
        publishJobBoards.setStatus("I");
        publishJobBoards.setJobBoardCode("TWITJOBSEARCH");
        
        BOFactory.getJobRequistionBO().savePublishJobBoards(publishJobBoards);
      }
    }
    if ((!StringUtils.isNullOrEmpty(workhound)) && (workhound.equals("true")))
    {
      boolean flag = BOFactory.getJobRequistionBO().isjobBoardCodeExist(jobreqId, "WORKHOUND");
      if (flag)
      {
        PublishJobBoards publishJobBoard6 = BOFactory.getJobRequistionBO().getPublishJobBoardsByReqIdandjobBoardCode(jobreqId, "WORKHOUND");
        publishJobBoard6.setUpdatedBy(username);
        publishJobBoard6.setUpdatedDate(new Date());
        publishJobBoard6.setStatus("P");
        BOFactory.getJobRequistionBO().updatePublishJobBoards(publishJobBoard6);
      }
      else
      {
        PublishJobBoards publishJobBoards = new PublishJobBoards();
        JobRequisition jb2 = new JobRequisition();
        jb2.setJobreqId(new Long(jobreqId).longValue());
        publishJobBoards.setJobRequisition(jb2);
        publishJobBoards.setCreatedBy(username);
        publishJobBoards.setCreatedDate(new Date());
        publishJobBoards.setJobBoardCode("WORKHOUND");
        publishJobBoards.setStatus("P");
        BOFactory.getJobRequistionBO().savePublishJobBoards(publishJobBoards);
      }
    }
    else
    {
      boolean flag = BOFactory.getJobRequistionBO().isjobBoardCodeExist(jobreqId, "WORKHOUND");
      if (flag)
      {
        PublishJobBoards publishJobBoards = BOFactory.getJobRequistionBO().getPublishJobBoardsByReqIdandjobBoardCode(jobreqId, "WORKHOUND");
        
        publishJobBoards.setStatus("I");
        publishJobBoards.setUpdatedBy(username);
        publishJobBoards.setUpdatedDate(new Date());
        BOFactory.getJobRequistionBO().updatePublishJobBoards(publishJobBoards);
      }
      else
      {
        PublishJobBoards publishJobBoards = new PublishJobBoards();
        JobRequisition jb2 = new JobRequisition();
        jb2.setJobreqId(new Long(jobreqId).longValue());
        publishJobBoards.setJobRequisition(jb2);
        publishJobBoards.setCreatedBy(username);
        publishJobBoards.setCreatedDate(new Date());
        publishJobBoards.setStatus("I");
        publishJobBoards.setJobBoardCode("WORKHOUND");
        
        BOFactory.getJobRequistionBO().savePublishJobBoards(publishJobBoards);
      }
    }
    if ((!StringUtils.isNullOrEmpty(yakaz)) && (yakaz.equals("true")))
    {
      boolean flag = BOFactory.getJobRequistionBO().isjobBoardCodeExist(jobreqId, "YAKAZ");
      if (flag)
      {
        PublishJobBoards publishJobBoard7 = BOFactory.getJobRequistionBO().getPublishJobBoardsByReqIdandjobBoardCode(jobreqId, "YAKAZ");
        publishJobBoard7.setUpdatedBy(username);
        publishJobBoard7.setUpdatedDate(new Date());
        publishJobBoard7.setStatus("P");
        BOFactory.getJobRequistionBO().updatePublishJobBoards(publishJobBoard7);
      }
      else
      {
        PublishJobBoards publishJobBoards = new PublishJobBoards();
        JobRequisition jb2 = new JobRequisition();
        jb2.setJobreqId(new Long(jobreqId).longValue());
        publishJobBoards.setJobRequisition(jb2);
        publishJobBoards.setCreatedBy(username);
        publishJobBoards.setCreatedDate(new Date());
        publishJobBoards.setJobBoardCode("YAKAZ");
        publishJobBoards.setStatus("P");
        BOFactory.getJobRequistionBO().savePublishJobBoards(publishJobBoards);
      }
    }
    else
    {
      boolean flag = BOFactory.getJobRequistionBO().isjobBoardCodeExist(jobreqId, "YAKAZ");
      if (flag)
      {
        PublishJobBoards publishJobBoards = BOFactory.getJobRequistionBO().getPublishJobBoardsByReqIdandjobBoardCode(jobreqId, "YAKAZ");
        
        publishJobBoards.setStatus("I");
        publishJobBoards.setUpdatedBy(username);
        publishJobBoards.setUpdatedDate(new Date());
        BOFactory.getJobRequistionBO().updatePublishJobBoards(publishJobBoards);
      }
      else
      {
        PublishJobBoards publishJobBoards = new PublishJobBoards();
        JobRequisition jb2 = new JobRequisition();
        jb2.setJobreqId(new Long(jobreqId).longValue());
        publishJobBoards.setJobRequisition(jb2);
        publishJobBoards.setCreatedBy(username);
        publishJobBoards.setCreatedDate(new Date());
        publishJobBoards.setStatus("I");
        publishJobBoards.setJobBoardCode("YAKAZ");
        
        BOFactory.getJobRequistionBO().savePublishJobBoards(publishJobBoards);
      }
    }
    if ((!StringUtils.isNullOrEmpty(jobdiesel)) && (jobdiesel.equals("true")))
    {
      boolean flag = BOFactory.getJobRequistionBO().isjobBoardCodeExist(jobreqId, "JOBDIESEL");
      if (flag)
      {
        PublishJobBoards publishJobBoard7 = BOFactory.getJobRequistionBO().getPublishJobBoardsByReqIdandjobBoardCode(jobreqId, "JOBDIESEL");
        publishJobBoard7.setUpdatedBy(username);
        publishJobBoard7.setUpdatedDate(new Date());
        publishJobBoard7.setStatus("P");
        BOFactory.getJobRequistionBO().updatePublishJobBoards(publishJobBoard7);
      }
      else
      {
        PublishJobBoards publishJobBoards = new PublishJobBoards();
        JobRequisition jb2 = new JobRequisition();
        jb2.setJobreqId(new Long(jobreqId).longValue());
        publishJobBoards.setJobRequisition(jb2);
        publishJobBoards.setCreatedBy(username);
        publishJobBoards.setCreatedDate(new Date());
        publishJobBoards.setJobBoardCode("JOBDIESEL");
        publishJobBoards.setStatus("P");
        BOFactory.getJobRequistionBO().savePublishJobBoards(publishJobBoards);
      }
    }
    else
    {
      boolean flag = BOFactory.getJobRequistionBO().isjobBoardCodeExist(jobreqId, "JOBDIESEL");
      if (flag)
      {
        PublishJobBoards publishJobBoards = BOFactory.getJobRequistionBO().getPublishJobBoardsByReqIdandjobBoardCode(jobreqId, "JOBDIESEL");
        
        publishJobBoards.setStatus("I");
        publishJobBoards.setUpdatedBy(username);
        publishJobBoards.setUpdatedDate(new Date());
        BOFactory.getJobRequistionBO().updatePublishJobBoards(publishJobBoards);
      }
      else
      {
        PublishJobBoards publishJobBoards = new PublishJobBoards();
        JobRequisition jb2 = new JobRequisition();
        jb2.setJobreqId(new Long(jobreqId).longValue());
        publishJobBoards.setJobRequisition(jb2);
        publishJobBoards.setCreatedBy(username);
        publishJobBoards.setCreatedDate(new Date());
        publishJobBoards.setStatus("I");
        publishJobBoards.setJobBoardCode("JOBDIESEL");
        
        BOFactory.getJobRequistionBO().savePublishJobBoards(publishJobBoards);
      }
    }
    if ((!StringUtils.isNullOrEmpty(indeed)) && (indeed.equals("true")))
    {
      boolean flag = BOFactory.getJobRequistionBO().isjobBoardCodeExist(jobreqId, "INDEED");
      if (flag)
      {
        PublishJobBoards publishJobBoard8 = BOFactory.getJobRequistionBO().getPublishJobBoardsByReqIdandjobBoardCode(jobreqId, "INDEED");
        publishJobBoard8.setUpdatedBy(username);
        publishJobBoard8.setUpdatedDate(new Date());
        publishJobBoard8.setStatus("P");
        BOFactory.getJobRequistionBO().updatePublishJobBoards(publishJobBoard8);
      }
      else
      {
        PublishJobBoards publishJobBoards = new PublishJobBoards();
        JobRequisition jb2 = new JobRequisition();
        jb2.setJobreqId(new Long(jobreqId).longValue());
        publishJobBoards.setJobRequisition(jb2);
        publishJobBoards.setCreatedBy(username);
        publishJobBoards.setCreatedDate(new Date());
        publishJobBoards.setJobBoardCode("INDEED");
        publishJobBoards.setStatus("P");
        BOFactory.getJobRequistionBO().savePublishJobBoards(publishJobBoards);
      }
    }
    else
    {
      boolean flag = BOFactory.getJobRequistionBO().isjobBoardCodeExist(jobreqId, "INDEED");
      if (flag)
      {
        PublishJobBoards publishJobBoards = BOFactory.getJobRequistionBO().getPublishJobBoardsByReqIdandjobBoardCode(jobreqId, "INDEED");
        
        publishJobBoards.setStatus("I");
        publishJobBoards.setUpdatedBy(username);
        publishJobBoards.setUpdatedDate(new Date());
        BOFactory.getJobRequistionBO().updatePublishJobBoards(publishJobBoards);
      }
      else
      {
        PublishJobBoards publishJobBoards = new PublishJobBoards();
        JobRequisition jb2 = new JobRequisition();
        jb2.setJobreqId(new Long(jobreqId).longValue());
        publishJobBoards.setJobRequisition(jb2);
        publishJobBoards.setCreatedBy(username);
        publishJobBoards.setCreatedDate(new Date());
        publishJobBoards.setStatus("I");
        publishJobBoards.setJobBoardCode("INDEED");
        
        BOFactory.getJobRequistionBO().savePublishJobBoards(publishJobBoards);
      }
    }
    if ((!StringUtils.isNullOrEmpty(simplyhired)) && (simplyhired.equals("true")))
    {
      boolean flag = BOFactory.getJobRequistionBO().isjobBoardCodeExist(jobreqId, "SIMP");
      if (flag)
      {
        PublishJobBoards publishJobBoard9 = BOFactory.getJobRequistionBO().getPublishJobBoardsByReqIdandjobBoardCode(jobreqId, "SIMP");
        publishJobBoard9.setUpdatedBy(username);
        publishJobBoard9.setUpdatedDate(new Date());
        publishJobBoard9.setStatus("P");
        BOFactory.getJobRequistionBO().updatePublishJobBoards(publishJobBoard9);
      }
      else
      {
        PublishJobBoards publishJobBoards = new PublishJobBoards();
        JobRequisition jb2 = new JobRequisition();
        jb2.setJobreqId(new Long(jobreqId).longValue());
        publishJobBoards.setJobRequisition(jb2);
        publishJobBoards.setCreatedBy(username);
        publishJobBoards.setCreatedDate(new Date());
        publishJobBoards.setJobBoardCode("SIMP");
        publishJobBoards.setStatus("P");
        BOFactory.getJobRequistionBO().savePublishJobBoards(publishJobBoards);
      }
    }
    else
    {
      boolean flag = BOFactory.getJobRequistionBO().isjobBoardCodeExist(jobreqId, "SIMP");
      if (flag)
      {
        PublishJobBoards publishJobBoards = BOFactory.getJobRequistionBO().getPublishJobBoardsByReqIdandjobBoardCode(jobreqId, "SIMP");
        
        publishJobBoards.setStatus("I");
        publishJobBoards.setUpdatedBy(username);
        publishJobBoards.setUpdatedDate(new Date());
        BOFactory.getJobRequistionBO().updatePublishJobBoards(publishJobBoards);
      }
      else
      {
        PublishJobBoards publishJobBoards = new PublishJobBoards();
        JobRequisition jb2 = new JobRequisition();
        jb2.setJobreqId(new Long(jobreqId).longValue());
        publishJobBoards.setJobRequisition(jb2);
        publishJobBoards.setCreatedBy(username);
        publishJobBoards.setCreatedDate(new Date());
        publishJobBoards.setStatus("I");
        publishJobBoards.setJobBoardCode("SIMP");
        
        BOFactory.getJobRequistionBO().savePublishJobBoards(publishJobBoards);
      }
    }
    if ((!StringUtils.isNullOrEmpty(jooble)) && (jooble.equals("true")))
    {
      boolean flag = BOFactory.getJobRequistionBO().isjobBoardCodeExist(jobreqId, "JOOBLE");
      if (flag)
      {
        PublishJobBoards publishJobBoard9 = BOFactory.getJobRequistionBO().getPublishJobBoardsByReqIdandjobBoardCode(jobreqId, "JOOBLE");
        publishJobBoard9.setUpdatedBy(username);
        publishJobBoard9.setUpdatedDate(new Date());
        publishJobBoard9.setStatus("P");
        BOFactory.getJobRequistionBO().updatePublishJobBoards(publishJobBoard9);
      }
      else
      {
        PublishJobBoards publishJobBoards = new PublishJobBoards();
        JobRequisition jb2 = new JobRequisition();
        jb2.setJobreqId(new Long(jobreqId).longValue());
        publishJobBoards.setJobRequisition(jb2);
        publishJobBoards.setCreatedBy(username);
        publishJobBoards.setCreatedDate(new Date());
        publishJobBoards.setJobBoardCode("JOOBLE");
        publishJobBoards.setStatus("P");
        BOFactory.getJobRequistionBO().savePublishJobBoards(publishJobBoards);
      }
    }
    else
    {
      boolean flag = BOFactory.getJobRequistionBO().isjobBoardCodeExist(jobreqId, "JOOBLE");
      if (flag)
      {
        PublishJobBoards publishJobBoards = BOFactory.getJobRequistionBO().getPublishJobBoardsByReqIdandjobBoardCode(jobreqId, "JOOBLE");
        
        publishJobBoards.setStatus("I");
        publishJobBoards.setUpdatedBy(username);
        publishJobBoards.setUpdatedDate(new Date());
        BOFactory.getJobRequistionBO().updatePublishJobBoards(publishJobBoards);
      }
      else
      {
        PublishJobBoards publishJobBoards = new PublishJobBoards();
        JobRequisition jb2 = new JobRequisition();
        jb2.setJobreqId(new Long(jobreqId).longValue());
        publishJobBoards.setJobRequisition(jb2);
        publishJobBoards.setCreatedBy(username);
        publishJobBoards.setCreatedDate(new Date());
        publishJobBoards.setStatus("I");
        publishJobBoards.setJobBoardCode("JOOBLE");
        
        BOFactory.getJobRequistionBO().savePublishJobBoards(publishJobBoards);
      }
    }
    if ((!StringUtils.isNullOrEmpty(careervital)) && (careervital.equals("true")))
    {
      boolean flag = BOFactory.getJobRequistionBO().isjobBoardCodeExist(jobreqId, "CAREERVITAL");
      if (flag)
      {
        PublishJobBoards publishJobBoard9 = BOFactory.getJobRequistionBO().getPublishJobBoardsByReqIdandjobBoardCode(jobreqId, "CAREERVITAL");
        publishJobBoard9.setUpdatedBy(username);
        publishJobBoard9.setUpdatedDate(new Date());
        publishJobBoard9.setStatus("P");
        BOFactory.getJobRequistionBO().updatePublishJobBoards(publishJobBoard9);
      }
      else
      {
        PublishJobBoards publishJobBoards = new PublishJobBoards();
        JobRequisition jb2 = new JobRequisition();
        jb2.setJobreqId(new Long(jobreqId).longValue());
        publishJobBoards.setJobRequisition(jb2);
        publishJobBoards.setCreatedBy(username);
        publishJobBoards.setCreatedDate(new Date());
        publishJobBoards.setJobBoardCode("CAREERVITAL");
        publishJobBoards.setStatus("P");
        BOFactory.getJobRequistionBO().savePublishJobBoards(publishJobBoards);
      }
    }
    else
    {
      boolean flag = BOFactory.getJobRequistionBO().isjobBoardCodeExist(jobreqId, "CAREERVITAL");
      if (flag)
      {
        PublishJobBoards publishJobBoards = BOFactory.getJobRequistionBO().getPublishJobBoardsByReqIdandjobBoardCode(jobreqId, "CAREERVITAL");
        
        publishJobBoards.setStatus("I");
        publishJobBoards.setUpdatedBy(username);
        publishJobBoards.setUpdatedDate(new Date());
        BOFactory.getJobRequistionBO().updatePublishJobBoards(publishJobBoards);
      }
      else
      {
        PublishJobBoards publishJobBoards = new PublishJobBoards();
        JobRequisition jb2 = new JobRequisition();
        jb2.setJobreqId(new Long(jobreqId).longValue());
        publishJobBoards.setJobRequisition(jb2);
        publishJobBoards.setCreatedBy(username);
        publishJobBoards.setCreatedDate(new Date());
        publishJobBoards.setStatus("I");
        publishJobBoards.setJobBoardCode("CAREERVITAL");
        
        BOFactory.getJobRequistionBO().savePublishJobBoards(publishJobBoards);
      }
    }
    request.setAttribute("juju", juju);
    request.setAttribute("olx", olx);
    request.setAttribute("oodle", oodle);
    request.setAttribute("trovit", trovit);
    request.setAttribute("twitjobsearch", twitjobsearch);
    request.setAttribute("workhound", workhound);
    request.setAttribute("yakaz", yakaz);
    request.setAttribute("indeed", indeed);
    request.setAttribute("simplyhired", simplyhired);
    request.setAttribute("jooble", jooble);
    request.setAttribute("careervital", careervital);
    request.setAttribute("jobdiesel", jobdiesel);
  }
  
  private void setEmployeeReferralSchemes(Set<RefferalScheme> employeeReferralSchemes, HttpServletRequest request)
  {
    for (int i = 1; i < 50; i++)
    {
      String empschemeid = request.getParameter("empschemeid_" + i);
      if (!StringUtils.isNullOrEmpty(empschemeid))
      {
        logger.info("empschemeid" + empschemeid);
        
        RefferalScheme bfc = new RefferalScheme();
        bfc.setRefferalScheme_Id(new Long(empschemeid).longValue());
        

        employeeReferralSchemes.add(bfc);
      }
    }
  }
  
  private void setAgencyReferralSchemes(Set<RefferalScheme> agencyReferralScheme, HttpServletRequest request)
  {
    for (int i = 1; i < 50; i++)
    {
      String agencyschemeid = request.getParameter("agencyschemeid_" + i);
      if (!StringUtils.isNullOrEmpty(agencyschemeid))
      {
        logger.info("agencyschemeid" + agencyschemeid);
        
        RefferalScheme bfc = new RefferalScheme();
        bfc.setRefferalScheme_Id(new Long(agencyschemeid).longValue());
        

        agencyReferralScheme.add(bfc);
      }
    }
  }
  
  public ActionForward publishJobReq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside publishJobReq method");
    String jobreqId = request.getParameter("jobreqId");
    String publishToExternal = request.getParameter("publishToExternal");
    String publishToEmpRef = request.getParameter("publishToEmpRef");
    String eeoInfoIncluded = request.getParameter("eeoInfoIncluded");
    String comment = request.getParameter("comment");
    String userids = request.getParameter("userids");
    String countvendor = request.getParameter("countvendor");
    User user1 = (User)request.getSession().getAttribute("user_data");
    List errorList = new ArrayList();
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    JobRequisition jb = null;
    Set vendoridList = new HashSet();
    try
    {
      ActionErrors errors = new ActionErrors();
      ActionForward forward = new ActionForward();
      
      jbForm.setJobreqId(new Long(jobreqId).longValue());
      for (int i = 0; i <= Integer.parseInt(countvendor); i++)
      {
        String uservendorid = request.getParameter("publishToVendorId_" + i);
        if (!StringUtils.isNullOrEmpty(uservendorid)) {
          vendoridList.add(Long.valueOf(new Long(uservendorid).longValue()));
        }
      }
      logger.info("setting employee ref schemes");
      Set<RefferalScheme> employeeReferralSchemes = new HashSet();
      Set<RefferalScheme> agencyReferralScheme = new HashSet();
      setEmployeeReferralSchemes(employeeReferralSchemes, request);
      setAgencyReferralSchemes(agencyReferralScheme, request);
      
      jbForm.setEmployeeReferralSchemeList(employeeReferralSchemes);
      jbForm.setAgencyReferralSchemeList(agencyReferralScheme);
      


      jb = BOFactory.getJobRequistionTXBO().publishrequisitionwindow(jbForm, user1, vendoridList, jbForm.getPublishedComment(), publishToExternal, publishToEmpRef, eeoInfoIncluded, userids, errorList);
      request.setAttribute("publishJobReq", "yes");
      
      BOFactory.getJobRequistionBO().saveRequisitionComment(jb.getUuid(), user1, "Publish Requisition", jbForm.getPublishedComment());
      






      saveUpdatePublishJobBoards(request, jobreqId, user1.getUserName());
      

      List publishjobboardlist = new ArrayList();
      publishjobboardlist = BOFactory.getJobRequistionBO().getAllPublishJobBoardsListByReqId(new Long(jobreqId).longValue());
      logger.info("publishjobboardlist >> " + publishjobboardlist.size());
      

      jbForm.fromValue(jb, request);
      
      return mapping.findForward("publishJobReqscr");
    }
    catch (ObjectNotFoundException e)
    {
      String[] args = { String.valueOf(jobreqId) };
      String msg = Constant.getResourceStringValue("requistion.notfound", user1.getLocale(), args);
      request.setAttribute(Common.ERROR_MSG, msg);
      
      return mapping.findForward("exception");
    }
    catch (ValidationException e)
    {
      logger.info(e);
      
      request.setAttribute("error_list", errorList);
      jbForm.setJobreqId(new Long(jobreqId).longValue());
      BOFactory.getJobRequistionBO().publishJobReqscrErrorCondition(jbForm, vendoridList, request, user1);
      
      return mapping.findForward("publishJobReqscr");
    }
    catch (Exception e)
    {
      String msg = Constant.getResourceStringValue("server.error", user1.getLocale());
      if (e.getMessage() == null) {
        request.setAttribute(Common.ERROR_MSG, msg);
      } else {
        request.setAttribute(Common.ERROR_MSG, e.getMessage());
      }
    }
    return mapping.findForward("exception");
  }
  
  public ActionForward closerequisition(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside closerequisition method");
    String jobreqId = request.getParameter("jobreqId");
    String comment = request.getParameter("comment");
    User user1 = (User)request.getSession().getAttribute("user_data");
    try
    {
      ActionErrors errors = new ActionErrors();
      ActionForward forward = new ActionForward();
      JobRequisitionForm jbForm = (JobRequisitionForm)form;
      jbForm.setJobreqId(new Long(jobreqId).longValue());
      




      JobRequisition jb = BOFactory.getJobRequistionTXBO().closerequisition(jbForm, user1, comment);
      
      BOFactory.getJobRequistionBO().saveRequisitionComment(jb.getUuid(), user1, "Close Requisition", comment);
      try
      {
        BOFactory.getJobRequistionBO().sendEmailForRequisition(user1, jb, comment, Common.CLOSE_REQUISITION, EmailNotificationSettingFunction.ReqFunctionNames.CLOSE_REQUISITION.toString());
      }
      catch (Exception e)
      {
        logger.info("error on sendEmailForRequisition", e);
      }
      jbForm.fromValue(jb, request);
      jbForm.setTabselected(1);
      
      return mapping.findForward("editjobreq");
    }
    catch (ObjectNotFoundException e)
    {
      String[] args = { String.valueOf(jobreqId) };
      String msg = Constant.getResourceStringValue("requistion.notfound", user1.getLocale(), args);
      request.setAttribute(Common.ERROR_MSG, msg);
      return mapping.findForward("exception");
    }
    catch (ValidationException e)
    {
      String msg = Constant.getResourceStringValue("server.error", user1.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
      return mapping.findForward("exception");
    }
    catch (Exception e)
    {
      String msg = Constant.getResourceStringValue("server.error", user1.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
    }
    return mapping.findForward("exception");
  }
  
  public ActionForward saveasjobrequisition(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveasjobrequisition method .. ");
    String uuid = request.getParameter("uuid");
    
    String jobreqName = request.getParameter("jobreqName");
    String jobTitle = request.getParameter("jobTitle");
    



    String isCompetencyChecked = request.getParameter("isCompetencyChecked");
    String isAccomplishmentChecked = request.getParameter("isAccomplishmentChecked");
    String isApproversChecked = request.getParameter("isApproversChecked");
    String isAttachmentsChecked = request.getParameter("isAttachmentsChecked");
    



    logger.info("isCompetencyChecked : " + isCompetencyChecked);
    logger.info("isAccomplishmentChecked : " + isAccomplishmentChecked);
    logger.info("isApproversChecked : " + isApproversChecked);
    logger.info("isAttachmentsChecked : " + isAttachmentsChecked);
    
    User user1 = (User)request.getSession().getAttribute("user_data");
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    try
    {
      jbForm.setUuid(uuid);
      


      JobRequisition jb = BOFactory.getJobRequistionTXBO().saveasjobrequisition(jbForm, request, isCompetencyChecked, isAccomplishmentChecked, isApproversChecked, isAttachmentsChecked, jobreqName, jobTitle, user1);
      


      jbForm.fromValue(jb, request);
      
      jbForm.setTabselected(1);
      request.setAttribute("saveas", "true");
      return mapping.findForward("editjobreq");
    }
    catch (Exception e)
    {
      String msg = Constant.getResourceStringValue("server.error", user1.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
    }
    return mapping.findForward("exception");
  }
  
  public ActionForward saverecruiterajax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saverecruiterajax method");
    String jobreqId = request.getParameter("requisitionId");
    String recruiterId = request.getParameter("recruiterId");
    String recruiterName = request.getParameter("recruiterName");
    String isgrouprecruiter = request.getParameter("isgrouprecruiter");
    
    User user1 = (User)request.getSession().getAttribute("user_data");
    try
    {
      JobRequisitionForm jbForm = (JobRequisitionForm)form;
      jbForm.setJobreqId(new Long(jobreqId).longValue());
      




      JobRequisition jb = BOFactory.getJobRequistionTXBO().saverecruiterajax(jbForm, user1, recruiterId, recruiterName, isgrouprecruiter);
      
      jbForm.setRecruiterName(jb.getRecruiterName());
      request.setAttribute("saverecruiterajax", "yes");
      return mapping.findForward("saverecruiterajax");
    }
    catch (ObjectNotFoundException e)
    {
      String[] args = { String.valueOf(jobreqId) };
      String msg = Constant.getResourceStringValue("requistion.notfound", user1.getLocale(), args);
      request.setAttribute(Common.ERROR_MSG, msg);
      return mapping.findForward("exception");
    }
    catch (Exception e)
    {
      String msg = Constant.getResourceStringValue("server.error", user1.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
    }
    return mapping.findForward("exception");
  }
  
  public ActionForward deleterequisition(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleterequisition method");
    String jobreqId = request.getParameter("jobreqId");
    String comment = request.getParameter("comment");
    User user1 = (User)request.getSession().getAttribute("user_data");
    try
    {
      ActionErrors errors = new ActionErrors();
      ActionForward forward = new ActionForward();
      JobRequisitionForm jbForm = (JobRequisitionForm)form;
      jbForm.setJobreqId(new Long(jobreqId).longValue());
      


      JobRequisition jb = BOFactory.getJobRequistionTXBO().deleterequisition(jbForm, user1, comment);
      
      BOFactory.getJobRequistionBO().saveRequisitionComment(jb.getUuid(), user1, "Delete Requisition", comment);
      try
      {
        BOFactory.getJobRequistionBO().sendEmailForRequisition(user1, jb, comment, Common.DELETE_REQUISITION, EmailNotificationSettingFunction.ReqFunctionNames.DELETE_REQUISITION.toString());
      }
      catch (Exception e)
      {
        logger.info("error on sendEmailForRequisition", e);
      }
      jbForm.fromValue(jb, request);
      jbForm.setTabselected(1);
      return mapping.findForward("editjobreq");
    }
    catch (ObjectNotFoundException e)
    {
      String[] args = { String.valueOf(jobreqId) };
      String msg = Constant.getResourceStringValue("requistion.notfound", user1.getLocale(), args);
      request.setAttribute(Common.ERROR_MSG, msg);
      return mapping.findForward("exception");
    }
    catch (ValidationException e)
    {
      String msg = Constant.getResourceStringValue("server.error", user1.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
      return mapping.findForward("exception");
    }
    catch (Exception e)
    {
      String msg = Constant.getResourceStringValue("server.error", user1.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
    }
    return mapping.findForward("exception");
  }
  
  public ActionForward approverejectReqSrc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside approverejectReqSrc method");
    String jobreqId = request.getParameter("jobreqId");
    String uuid = request.getParameter("uuid");
    String type = request.getParameter("type");
    
    User user1 = (User)request.getSession().getAttribute("user_data");
    try
    {
      String jbTmplApproverId = "";
      JobRequisition jobreq = BOFactory.getJobRequistionBO().getJobRequision(jobreqId, uuid);
      List approverList = BOFactory.getJobRequistionTXBO().getApptoversListForRequistion(new Long(jobreqId).longValue());
      if ((jobreq.getIsapprovalInitiated() == 1) && (jobreq.getIsrejected() != 1)) {
        for (int j = 0; j < approverList.size(); j++)
        {
          JobTemplateApprovers lastjapp1 = null;
          if (j != 0) {
            lastjapp1 = (JobTemplateApprovers)approverList.get(j - 1);
          }
          JobTemplateApprovers japp1 = (JobTemplateApprovers)approverList.get(j);
          if ((BOFactory.getJobRequistionBO().isLoggedInUserIsApprover(user1.getUserId(), japp1.getUserId(), japp1.getIsGroup())) && (!japp1.getApproved().equals("Y")))
          {
            if (lastjapp1 != null)
            {
              if ((!lastjapp1.getApproved().equals("Y")) || (japp1.getApproved().equals("Y"))) {
                break;
              }
              jbTmplApproverId = String.valueOf(japp1.getJbTmplApproverId()); break;
            }
            if (japp1.getApproved().equals("Y")) {
              break;
            }
            jbTmplApproverId = String.valueOf(japp1.getJbTmplApproverId()); break;
          }
        }
      }
      request.setAttribute("jobreqId", jobreqId);
      request.setAttribute("jbTmplApproverId", jbTmplApproverId);
      request.setAttribute("type", type);
    }
    catch (Exception e) {}
    return mapping.findForward("approverejectReqSrc");
  }
  
  public ActionForward approveRejectReqTask(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside approveRejectReqTask method");
    String jobreqId = request.getParameter("jobreqId");
    String comment = request.getParameter("comment");
    String jbTmplApproverId = request.getParameter("jbTmplApproverId");
    String type = request.getParameter("type");
    User user1 = (User)request.getSession().getAttribute("user_data");
    try
    {
      ActionErrors errors = new ActionErrors();
      ActionForward forward = new ActionForward();
      JobRequisitionForm jbForm = (JobRequisitionForm)form;
      if ((!StringUtils.isNullOrEmpty(type)) && (type.equals("approve")))
      {
        JobRequisition jb = BOFactory.getJobRequistionTXBO().approvereq(jbForm, user1, jobreqId, jbTmplApproverId, comment);
        request.setAttribute("approvedcomment", "yes");
      }
      else
      {
        JobRequisition jb = BOFactory.getJobRequistionTXBO().rejectrequisition(jbForm, user1, jbTmplApproverId, comment);
        request.setAttribute("rejectedcomment", "yes");
      }
      request.setAttribute("jobreqId", jobreqId);
      request.setAttribute("jbTmplApproverId", jbTmplApproverId);
      request.setAttribute("type", type);
    }
    catch (Exception e) {}
    return mapping.findForward("approverejectReqSrc");
  }
  
  public ActionForward approvereq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside approvereq method");
    String jobreqId = request.getParameter("jobreqId");
    String comment = request.getParameter("comment");
    String jbTmplApproverId = request.getParameter("jbTmplApproverId");
    User user1 = (User)request.getSession().getAttribute("user_data");
    try
    {
      ActionErrors errors = new ActionErrors();
      ActionForward forward = new ActionForward();
      JobRequisitionForm jbForm = (JobRequisitionForm)form;
      jbForm.setJobreqId(new Long(jobreqId).longValue());
      



      JobRequisition jb = BOFactory.getJobRequistionTXBO().approvereq(jbForm, user1, jobreqId, jbTmplApproverId, comment);
      try
      {
        BOFactory.getJobRequistionBO().sendEmailForRequisition(user1, jb, comment, Common.APPROVE_REQUISITION, EmailNotificationSettingFunction.ReqFunctionNames.APPROVE_REQUISITION.toString());
      }
      catch (Exception e)
      {
        logger.info("error on sendEmailForRequisition", e);
      }
      jbForm.fromValue(jb, request);
      

      jbForm.setTabselected(4);
      
      BOFactory.getJobRequistionBO().saveRequisitionComment(jb.getUuid(), user1, "Approve Requisition", comment);
      return mapping.findForward("editjobreq");
    }
    catch (ObjectNotFoundException e)
    {
      String[] args = { String.valueOf(jobreqId) };
      String msg = Constant.getResourceStringValue("requistion.notfound", user1.getLocale(), args);
      request.setAttribute(Common.ERROR_MSG, msg);
      return mapping.findForward("exception");
    }
    catch (ValidationException e)
    {
      String msg = Constant.getResourceStringValue("server.error", user1.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
      return mapping.findForward("exception");
    }
    catch (Exception e)
    {
      String msg = Constant.getResourceStringValue("server.error", user1.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
    }
    return mapping.findForward("exception");
  }
  
  public ActionForward rejectreq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside rejectreq method");
    String jobreqId = request.getParameter("jobreqId");
    String comment = request.getParameter("comment");
    String jbTmplApproverId = request.getParameter("jbTmplApproverId");
    User user1 = (User)request.getSession().getAttribute("user_data");
    try
    {
      ActionErrors errors = new ActionErrors();
      ActionForward forward = new ActionForward();
      JobRequisitionForm jbForm = (JobRequisitionForm)form;
      jbForm.setJobreqId(new Long(jobreqId).longValue());
      

      JobRequisition jb = BOFactory.getJobRequistionTXBO().rejectrequisition(jbForm, user1, jbTmplApproverId, comment);
      try
      {
        BOFactory.getJobRequistionBO().sendEmailForRequisition(user1, jb, comment, Common.REJECT_REQUISITION, EmailNotificationSettingFunction.ReqFunctionNames.REJECT_REQUISITION.toString());
      }
      catch (Exception e)
      {
        logger.info("error on sendEmailForRequisition", e);
      }
      jbForm.fromValue(jb, request);
      jbForm.setTabselected(4);
      
      return mapping.findForward("editjobreq");
    }
    catch (ObjectNotFoundException e)
    {
      String[] args = { String.valueOf(jobreqId) };
      String msg = Constant.getResourceStringValue("requistion.notfound", user1.getLocale(), args);
      request.setAttribute(Common.ERROR_MSG, msg);
      return mapping.findForward("exception");
    }
    catch (ValidationException e)
    {
      String msg = Constant.getResourceStringValue("server.error", user1.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
      return mapping.findForward("exception");
    }
    catch (Exception e)
    {
      String msg = Constant.getResourceStringValue("server.error", user1.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
    }
    return mapping.findForward("exception");
  }
  
  public ActionForward resetjobreq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside resetjobreq method");
    String jobreqId = request.getParameter("jobreqId");
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    try
    {
      jbForm.setJobreqId(new Long(jobreqId).longValue());
      


      JobRequisition jb = BOFactory.getJobRequistionTXBO().resetjobrequistion(jbForm, user1);
      


      jbForm.fromValue(jb, request);
      jbForm.setTabselected(4);
      
      return mapping.findForward("editjobreq");
    }
    catch (Exception e)
    {
      String msg = Constant.getResourceStringValue("server.error", user1.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
    }
    return mapping.findForward("exception");
  }
  
  public ActionForward recalljobreq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside recalljobreq method");
    String jobreqId = request.getParameter("jobreqId");
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    String comment = request.getParameter("comment");
    User user1 = (User)request.getSession().getAttribute("user_data");
    try
    {
      jbForm.setJobreqId(new Long(jobreqId).longValue());
      



      JobRequisition jb = BOFactory.getJobRequistionTXBO().recalljobrequistion(jbForm, comment, user1);
      
      BOFactory.getJobRequistionBO().saveRequisitionComment(jb.getUuid(), user1, "Recall Requisition", comment);
      try
      {
        BOFactory.getJobRequistionBO().sendEmailForRequisition(user1, jb, comment, Common.RECALL_REQUISITION, EmailNotificationSettingFunction.ReqFunctionNames.RECALL_REQUISITION.toString());
      }
      catch (Exception e)
      {
        logger.info("error on sendEmailForRequisition", e);
      }
      jbForm.fromValue(jb, request);
      jbForm.setTabselected(4);
      
      return mapping.findForward("editjobreq");
    }
    catch (Exception e)
    {
      String msg = Constant.getResourceStringValue("server.error", user1.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
    }
    return mapping.findForward("exception");
  }
  
  public ActionForward reassignreq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside reassignreq method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    
    String jobreqId = request.getParameter("jobreqId");
    String comment = request.getParameter("comment");
    String jbTmplApproverId = request.getParameter("jbTmplApproverId");
    String reassignedto = request.getParameter("reassignedto");
    String reassignedtoname = request.getParameter("reassignedtoname");
    String isgroup = request.getParameter("isgroup");
    try
    {
      ActionErrors errors = new ActionErrors();
      ActionForward forward = new ActionForward();
      JobRequisitionForm jbForm = (JobRequisitionForm)form;
      jbForm.setJobreqId(new Long(jobreqId).longValue());
      


      JobRequisition jb = BOFactory.getJobRequistionTXBO().reassignreq(jbForm, user1, jbTmplApproverId, reassignedto, reassignedtoname, isgroup, comment);
      try
      {
        BOFactory.getJobRequistionBO().sendEmailForRequisition(user1, jb, comment, Common.REASSIGN_REQUISITION_APPROVAL, EmailNotificationSettingFunction.ReqFunctionNames.REASSIGN_REQUISITION_APPROVAL.toString());
      }
      catch (Exception e)
      {
        logger.info("error on sendEmailForRequisition", e);
      }
      jbForm.fromValue(jb, request);
      jbForm.setTabselected(4);
      return mapping.findForward("editjobreq");
    }
    catch (ObjectNotFoundException e)
    {
      String[] args = { String.valueOf(jobreqId) };
      String msg = Constant.getResourceStringValue("requistion.notfound", user1.getLocale(), args);
      request.setAttribute(Common.ERROR_MSG, msg);
      return mapping.findForward("exception");
    }
    catch (ValidationException e)
    {
      String msg = Constant.getResourceStringValue("server.error", user1.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
      return mapping.findForward("exception");
    }
    catch (Exception e)
    {
      String msg = Constant.getResourceStringValue("server.error", user1.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
    }
    return mapping.findForward("exception");
  }
  
  public ActionForward getcurrencycode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside getcurrencycode method");
    String requisitionId = request.getParameter("requisitionId");
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    JobRequisition jobreq = BOFactory.getJobRequistionBO().getJobRequision(requisitionId);
    jbForm.setCurrecyCode(jobreq.getOrganization().getCurrencyCode());
    jbForm.setJobreqId(new Long(requisitionId).longValue());
    return mapping.findForward("getcurrencycode");
  }
  
  public ActionForward getcurrencycodeexpected(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside getcurrencycodeexpected method");
    String requisitionId = request.getParameter("requisitionId");
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    JobRequisition jobreq = BOFactory.getJobRequistionBO().getJobRequision(requisitionId);
    jbForm.setCurrecyCode(jobreq.getOrganization().getCurrencyCode());
    return mapping.findForward("getcurrencycodeexpected");
  }
  
  public ActionForward addattachment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside addattachment method");
    String jobreqId = request.getParameter("jobreqId");
    String attahmentdetails = request.getParameter("attahmentdetails");
    String visibleInJobDetails = request.getParameter("visibleInJobDetails");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    JobRequisition jb = BOFactory.getJobRequistionBO().getJobRequision(jobreqId);
    jbForm.toValue(jb, request);
    try
    {
      jbForm.setJobreqId(new Long(jobreqId).longValue());
      


      jb = BOFactory.getJobRequistionTXBO().addattachment(jbForm, jb, attahmentdetails, visibleInJobDetails, user1);
      

      jbForm.fromValue(jb, request);
      jbForm.setTabselected(6);
      return mapping.findForward("editjobreq");
    }
    catch (Exception e)
    {
      String msg = Constant.getResourceStringValue("server.error", user1.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
    }
    return mapping.findForward("exception");
  }
  
  public ActionForward deleteattachment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteattachment method");
    String jobreqId = request.getParameter("jobreqId");
    String uuid = request.getParameter("uuid");
    
    User user1 = (User)request.getSession().getAttribute("user_data");
    try
    {
      ActionErrors errors = new ActionErrors();
      ActionForward forward = new ActionForward();
      JobRequisitionForm jbForm = (JobRequisitionForm)form;
      jbForm.setJobreqId(new Long(jobreqId).longValue());
      


      JobRequisition jb = BOFactory.getJobRequistionTXBO().deleteattachment(jbForm, user1, uuid);
      

      jbForm.fromValue(jb, request);
      jbForm.setTabselected(6);
      return mapping.findForward("editjobreq");
    }
    catch (ObjectNotFoundException e)
    {
      String[] args = { String.valueOf(jobreqId) };
      String msg = Constant.getResourceStringValue("requistion.notfound", user1.getLocale(), args);
      request.setAttribute(Common.ERROR_MSG, msg);
      return mapping.findForward("exception");
    }
    catch (ValidationException e)
    {
      String msg = Constant.getResourceStringValue("server.error", user1.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
      return mapping.findForward("exception");
    }
    catch (Exception e)
    {
      String msg = Constant.getResourceStringValue("server.error", user1.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
    }
    return mapping.findForward("exception");
  }
  
  public ActionForward assignExamToReq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside assignExamToReq method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    String jobreqid = request.getParameter("jobreqid");
    try
    {
      RequistionExamQnsAssign jb = BOFactory.getJobRequistionTXBO().assignExam(jobreqid, jbForm.getMockexamsetId(), jbForm.getPassPercentage(), jbForm.getMcomckexamsetcomment(), user1, jbForm);
      


      jbForm.setMcomckexamsetcomment(jb.getMcomckexamsetcomment());
      jbForm.setPassPercentage(jb.getPassPercentage());
      jbForm.setReqexqnid(jb.getReqexqnid());
      



      List mockexamList = BOFactory.getJobRequistionTXBO().getMockExamListForForm(user1.getSuper_user_key());
      jbForm.setMockexamsList(mockexamList);
      
      request.setAttribute("assignExam", "yes");
      return mapping.findForward("assignExamScreen");
    }
    catch (Exception e)
    {
      String msg = Constant.getResourceStringValue("server.error", user1.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
    }
    return mapping.findForward("exception");
  }
  
  public ActionForward assignQuestionnaire(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside assignQuestionnaire method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    String jobreqid = request.getParameter("jobreqid");
    try
    {
      RequistionExamQnsAssign jb = BOFactory.getJobRequistionTXBO().assignQuestionnaire(jobreqid, jbForm.getQuestiongroupId(), jbForm.getQuestiongroupcomment(), user1, jbForm);
      
      jbForm.setQuestiongroupId(jb.getQnsgrpId());
      jbForm.setQuestiongroupcomment(jb.getQuestiongroupcomment());
      jbForm.setReqexqnid(jb.getReqexqnid());
      





      List questiongroupList = BOFactory.getJobRequistionTXBO().getQuestionGroupListForForm(user1.getSuper_user_key());
      jbForm.setQuestionGroupList(questiongroupList);
      



      request.setAttribute("assignQuestionnaire", "yes");
      return mapping.findForward("assignQuestionnaireScreen");
    }
    catch (Exception e)
    {
      String msg = Constant.getResourceStringValue("server.error", user1.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
    }
    return mapping.findForward("exception");
  }
  
  public ActionForward saveApprover(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveApprover method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    
    String jobreqid = request.getParameter("jobreqid");
    String approvername = request.getParameter("approvername");
    String approverid = request.getParameter("approverid");
    String type = request.getParameter("type");
    String isgroup = request.getParameter("isgroup");
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    JobTemplateApprovers jbtapprover = null;
    JobRequisition jb = null;
    try
    {
      jb = BOFactory.getJobRequistionBO().getJobRequision(jobreqid);
      jbForm.toValue(jb, request);
      List approversList = BOFactory.getJobRequistionBO().getJobRequisionTemplateApproversList(new Long(jobreqid).longValue(), type);
      
      logger.info("approver list : " + approversList.size());
      Iterator itr = approversList.iterator();
      while (itr.hasNext())
      {
        jbtapprover = (JobTemplateApprovers)itr.next();
        logger.info("approver name from database :" + jbtapprover.getApproverName());
        if (approvername.equals(jbtapprover.getApproverName()))
        {
          jbForm.setJobreqId(new Long(jobreqid).longValue());
          



          jb = BOFactory.getJobRequistionTXBO().jobRequistionDetails(user1, jbForm, jb);
          

          jbForm.fromValue(jb, request);
          jbForm.setTabselected(4);
          request.setAttribute("approveralreadyadded", "yes");
          return mapping.findForward("editjobreq");
        }
      }
      jbForm.setJobreqId(new Long(jobreqid).longValue());
      jbtapprover = new JobTemplateApprovers();
      




      jbtapprover.setJbTmplId(new Long(jobreqid).longValue());
      jbtapprover.setApproverName(approvername);
      jbtapprover.setIsFromSystemRule("N");
      jbtapprover.setType(type);
      jbtapprover.setUserId(new Long(approverid).longValue());
      


      jb = BOFactory.getJobRequistionTXBO().addapprover(jbForm, jb, user1, jbtapprover, isgroup);
      

      jbForm.fromValue(jb, request);
      jbForm.setTabselected(4);
      

      return mapping.findForward("editjobreq");
    }
    catch (Exception e)
    {
      String msg = Constant.getResourceStringValue("server.error", user1.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
    }
    return mapping.findForward("exception");
  }
  
  public ActionForward getApprovers(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside getApprovers method");
    String approveralreadyadded = request.getParameter("approveralreadyadded");
    logger.info("approveralreadyadded : " + approveralreadyadded);
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    String id = request.getParameter("id");
    String type = request.getParameter("type");
    
    List approversList = BOFactory.getJobRequistionBO().getJobRequisionTemplateApproversList(new Long(id).longValue(), type);
    logger.info("approversList size : " + approversList.size());
    
    jbForm.setApproversList(approversList);
    request.setAttribute("approveralreadyadded", approveralreadyadded);
    return mapping.findForward("getApprovers");
  }
  
  public ActionForward deleteApprovers(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteApprovers method");
    
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    String id = request.getParameter("id");
    String reqid = request.getParameter("reqid");
    User user1 = (User)request.getSession().getAttribute("user_data");
    try
    {
      jbForm.setJobreqId(new Long(reqid).longValue());
      


      JobRequisition jb = BOFactory.getJobRequistionTXBO().deleteApprover(jbForm, user1, id);
      


      jbForm.fromValue(jb, request);
      jbForm.setTabselected(4);
      
      return mapping.findForward("editjobreq");
    }
    catch (Exception e)
    {
      String msg = Constant.getResourceStringValue("server.error", user1.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
    }
    return mapping.findForward("exception");
  }
  
  public ActionForward attachementdetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside attachementdetails method");
    String offerattachmentid = request.getParameter("attachmentid");
    
    RequistionAttachments offerattachment = BOFactory.getJobRequistionBO().getAttachmentDetailsByUuid(offerattachmentid);
    
    String filePath = Constant.getValue("ATTACHMENT_PATH");
    filePath = "reqattachment" + File.separator + offerattachment.getUuid() + File.separator + offerattachment.getAttahmentname();
    
    request.setAttribute("filePath", filePath);
    


    return mapping.findForward("attachementdetails");
  }
  
  public ActionForward getActivity(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside getActivity method");
    String requuid = request.getParameter("uuid");
    List activityList = BOFactory.getJobRequistionTXBO().getReqActivityByUuid(requuid);
    

    request.setAttribute("activityList", activityList);
    


    return mapping.findForward("getActivity");
  }
  
  public ActionForward getOfferedApplicants(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside getOfferedApplicants method");
    String requuid = request.getParameter("uuid");
    long reqid = BOFactory.getJobRequistionBO().getRequisitionIdByUuid(requuid);
    List offeredApplicantList = BOFactory.getApplicantBO().getApplicantsByOfferReleaseAndAccepted(reqid);
    

    request.setAttribute("offeredApplicantList", offeredApplicantList);
    


    return mapping.findForward("getOfferedApplicants");
  }
  
  public ActionForward editjobreq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside editjobreq method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    String jobreqId = request.getParameter("jobreqId");
    try
    {
      String req_id_for_menu = (String)request.getSession().getAttribute("req_id_for_menu");
      if (req_id_for_menu != null) {
        request.getSession().removeAttribute("req_id_for_menu");
      }
      String tabno = request.getParameter("tabno");
      ActionErrors errors = new ActionErrors();
      ActionForward forward = new ActionForward();
      JobRequisitionForm jbForm = (JobRequisitionForm)form;
      jbForm.setJobreqId(new Long(jobreqId).longValue());
      


      JobRequisition jb = BOFactory.getJobRequistionTXBO().editJobRequistion(user1, jbForm);
      

      jbForm.fromValue(jb, request);
      jbForm.setRecruiterName(jb.getRecruiterName());
      if (!StringUtils.isNullOrEmpty(tabno)) {
        jbForm.setTabselected(new Integer(tabno).intValue());
      }
      if ((jb.getStatus() != null) && (jb.getStatus().equals("Active"))) {
        editpublishJobBoard(request, jbForm);
      }
      request.setAttribute("editjobrequisition", "yes");
      return mapping.findForward("editjobreq");
    }
    catch (ObjectNotFoundException e)
    {
      String[] args = { String.valueOf(jobreqId) };
      String msg = Constant.getResourceStringValue("requistion.notfound", user1.getLocale(), args);
      request.setAttribute(Common.ERROR_MSG, msg);
      return mapping.findForward("exception");
    }
    catch (ValidationException e)
    {
      String msg = Constant.getResourceStringValue("server.error", user1.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
      return mapping.findForward("exception");
    }
    catch (Exception e)
    {
      String msg = Constant.getResourceStringValue("server.error", user1.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
    }
    return mapping.findForward("exception");
  }
  
  public void editpublishJobBoard(HttpServletRequest request, JobRequisitionForm jbForm)
  {
    String juju = "";
    String olx = "";
    String oodle = "";
    String trovit = "";
    String twitjobsearch = "";
    String workhound = "";
    String yakaz = "";
    String indeed = "";
    String simplyhired = "";
    String jooble = "";
    String careervital = "";
    
    List publishjobboardlist = new ArrayList();
    publishjobboardlist = BOFactory.getJobRequistionBO().getAllPublishJobBoardsListByReqId(jbForm.getJobreqId());
    logger.info("publishjobboardlist >> " + publishjobboardlist.size());
    if (publishjobboardlist.size() > 0) {
      jbForm.setPublishjobboardlist(publishjobboardlist);
    }
    for (int i = 0; i < publishjobboardlist.size(); i++)
    {
      PublishJobBoards publishJobBoards = (PublishJobBoards)publishjobboardlist.get(i);
      logger.info(" publishJobBoards.getJobBoardCode() >> " + publishJobBoards.getJobBoardCode());
      if (publishJobBoards.getJobBoardCode().equals("JUJU")) {
        juju = "true";
      }
      if (publishJobBoards.getJobBoardCode().equals("OLX")) {
        olx = "true";
      }
      if (publishJobBoards.getJobBoardCode().equals("OODLE")) {
        oodle = "true";
      }
      if (publishJobBoards.getJobBoardCode().equals("TROVIT")) {
        trovit = "true";
      }
      if (publishJobBoards.getJobBoardCode().equals("TWITJOBSEARCH")) {
        twitjobsearch = "true";
      }
      if (publishJobBoards.getJobBoardCode().equals("WORKHOUND")) {
        workhound = "true";
      }
      if (publishJobBoards.getJobBoardCode().equals("YAKAZ")) {
        yakaz = "true";
      }
      if (publishJobBoards.getJobBoardCode().equals("SIMP")) {
        simplyhired = "true";
      }
      if (publishJobBoards.getJobBoardCode().equals("INDEED")) {
        indeed = "true";
      }
      if (publishJobBoards.getJobBoardCode().equals("JOOBLE")) {
        jooble = "true";
      }
      if (publishJobBoards.getJobBoardCode().equals("CAREERVITAL")) {
        careervital = "true";
      }
    }
    logger.info(" juju >> " + juju);
    logger.info(" olx >> " + juju);
    
    request.setAttribute("juju", juju);
    request.setAttribute("olx", olx);
    request.setAttribute("oodle", oodle);
    request.setAttribute("trovit", trovit);
    request.setAttribute("twitjobsearch", twitjobsearch);
    request.setAttribute("workhound", workhound);
    request.setAttribute("yakaz", yakaz);
    request.setAttribute("indeed", indeed);
    request.setAttribute("simplyhired", simplyhired);
    request.setAttribute("jooble", jooble);
    request.setAttribute("careervital", careervital);
  }
  
  public ActionForward editjobreqselector(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside editjobreqselector method");
    String jobreqId = request.getParameter("jobreqId");
    jobreqId = EncryptDecrypt.decrypt(jobreqId);
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String approverstatus = request.getParameter("approverstatus");
    String offerapplicant = request.getParameter("offerapplicant");
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    try
    {
      jbForm.setJobreqId(new Long(jobreqId).longValue());
      JobRequisition jb = BOFactory.getJobRequistionBO().jobsummary(jbForm, approverstatus, offerapplicant, user1);
      jbForm.fromValue(jb, request);
      return mapping.findForward("summary");
    }
    catch (Exception e)
    {
      String msg = Constant.getResourceStringValue("server.error", user1.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
    }
    return mapping.findForward("exception");
  }
  
  public ActionForward jobreqdetailsid(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside jobreqdetailsid method");
    String jobreqId = request.getParameter("jobreqId");
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String approverstatus = request.getParameter("approverstatus");
    String offerapplicant = request.getParameter("offerapplicant");
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    try
    {
      jbForm.setJobreqId(new Long(jobreqId).longValue());
      JobRequisition jb = BOFactory.getJobRequistionBO().jobsummary(jbForm, approverstatus, offerapplicant, user1);
      jbForm.fromValue(jb, request);
      return mapping.findForward("summary");
    }
    catch (Exception e)
    {
      String msg = Constant.getResourceStringValue("server.error", user1.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
    }
    return mapping.findForward("exception");
  }
  
  public ActionForward jobreqdetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside editjobreqselector method");
    String jobreqId = request.getParameter("jobreqId");
    String uuid = request.getParameter("secureid");
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String approverstatus = request.getParameter("approverstatus");
    String offerapplicant = request.getParameter("offerapplicant");
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    try
    {
      jbForm.setJobreqId(new Long(jobreqId).longValue());
      jbForm.setUuid(uuid);
      JobRequisition jb = BOFactory.getJobRequistionBO().jobsummarywithuuid(jbForm, approverstatus, offerapplicant, user1);
      jbForm.fromValue(jb, request);
      return mapping.findForward("summary");
    }
    catch (Exception e)
    {
      String msg = Constant.getResourceStringValue("server.error", user1.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
    }
    return mapping.findForward("exception");
  }
  
  public ActionForward evaluationTab(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updatejobreq method");
    String reqId = request.getParameter("reqId");
    String isapprovalinitiated = request.getParameter("isapprovalinitiated");
    String state = request.getParameter("state");
    JobRequisitionForm jobreqform = (JobRequisitionForm)form;
    jobreqform.setJobreqId(new Long(reqId).longValue());
    jobreqform.setIsapprovalInitiated(new Integer(isapprovalinitiated).intValue());
    jobreqform.setState(state);
    User user1 = (User)request.getSession().getAttribute("user_data");
    
    jobreqform.setSkillRatingList(Constant.skillsRatingsList);
    BOFactory.getJobRequistionTXBO().setEvaluationTabData(user1, jobreqform);
    return mapping.findForward("evaluationTab");
  }
  
  public ActionForward updatejobreq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updatejobreq method");
    String jobreqId = request.getParameter("jobreqId");
    String isInternal = request.getParameter("isInternal");
    String isNewPosition = request.getParameter("isNewPosition");
    String targetfinishdate = request.getParameter("targetfinishdate");
    String recruiterId = request.getParameter("recruiterId");
    String hiringMgrId = request.getParameter("hiringMgrId");
    String isgrouprecruiter = request.getParameter("isgrouprecruiter");
    String isDirtySave = request.getParameter("isDirtySave");
    
    logger.info("Inside updatejobreq method isgrouprecruiter" + isgrouprecruiter);
    User user1 = (User)request.getSession().getAttribute("user_data");
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    ActionErrors errors = new ActionErrors();
    try
    {
      ActionForward forward = new ActionForward();
      jbForm.setJobreqId(new Long(jobreqId).longValue());
      
      JobRequisition jb = BOFactory.getJobRequistionBO().getJobRequision(jobreqId);
      int noofpositionOpenold = jb.getNumberOfOpening();
      int noofpositionRemainold = jb.getNumberOfOpeningRemain();
      int noofpositionFilled = noofpositionOpenold - noofpositionRemainold;
      if (noofpositionOpenold == jbForm.getNumberOfOpening()) {
        noofpositionFilled = 0;
      }
      jb = BOFactory.getJobRequistionTXBO().updatejobreq(request, errors, jbForm, jb, user1, isInternal, isNewPosition, targetfinishdate, recruiterId, isgrouprecruiter, hiringMgrId, noofpositionFilled);
      if (jbForm.getTabselected() == 0) {
        jbForm.setTabselected(1);
      }
      jbForm.fromValue(jb, request);
      if ((!StringUtils.isNullOrEmpty(isDirtySave)) && (isDirtySave.equals("true")))
      {
        List errorList = (List)request.getAttribute("error_list");
        if ((errorList != null) && (errorList.size() > 0)) {
          request.setAttribute("isDirtySaveSuccess", "false");
        } else {
          request.setAttribute("isDirtySaveSuccess", "true");
        }
      }
      request.setAttribute("editjobrequisition", "yes");
      return mapping.findForward("editjobreq");
    }
    catch (ObjectNotFoundException e)
    {
      logger.info("ObjectNotFoundException on update requistion", e);
      
      String[] args = { String.valueOf(jobreqId) };
      String msg = Constant.getResourceStringValue("requistion.notfound", user1.getLocale(), args);
      request.setAttribute(Common.ERROR_MSG, msg);
      return mapping.findForward("exception");
    }
    catch (ValidationException e)
    {
      logger.info("ValidationException on update requistion", e);
      
      jbForm.setTabselected(1);
      request.setAttribute("saverequisition", "no");
      return mapping.findForward("editjobreq");
    }
    catch (Exception e)
    {
      logger.info("exception on update requistion", e);
      String msg = Constant.getResourceStringValue("server.error", user1.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
    }
    return mapping.findForward("exception");
  }
  
  public ActionForward saveAccompleshment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveAccompleshment method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    
    String reqId = request.getParameter("reqId");
    String accomplishmentname = request.getParameter("accomplishmentname");
    String isaccommandatory = request.getParameter("isaccommandatory");
    
    String type = request.getParameter("type");
    try
    {
      JobTemplateAccomplishment jbtc = new JobTemplateAccomplishment();
      jbtc.setJbTmplId(new Long(reqId).longValue());
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
  
  public ActionForward createText(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside createText method .. in jobReq");
    

    return mapping.findForward("createtextjobReq");
  }
  
  public ActionForward downloadPdf(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside downloadPdf method .. in jobReq");
    String uuid = request.getParameter("uuid");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    try
    {
      jbForm.setUuid(uuid);
      BOFactory.getJobRequistionBO().downloadpdf(jbForm, request, user1);
      return mapping.findForward("downloadPdf");
    }
    catch (Exception e)
    {
      String msg = Constant.getResourceStringValue("server.error", user1.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
    }
    return mapping.findForward("exception");
  }
  
  public ActionForward loadDepartments(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String orgId = request.getParameter("orgId");
    System.out.println("orgId" + orgId);
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    jbForm.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(orgId));
    return mapping.findForward("deptlist");
  }
  
  public ActionForward deptlistmultiple(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deptlistmultiple method >>>");
    String orgId = request.getParameter("orgId");
    System.out.println("orgId" + orgId);
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    
    List<Long> orgIdsList = new ArrayList();
    if ((!StringUtils.isNullOrEmpty(orgId)) && (!orgId.equals("null")))
    {
      StringTokenizer token = new StringTokenizer(orgId, ",");
      while (token.hasMoreTokens()) {
        orgIdsList.add(new Long(token.nextToken()));
      }
    }
    jbForm.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(orgIdsList));
    return mapping.findForward("deptlistmultiple");
  }
  
  public ActionForward assignExamScreen(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside assignExamScreen method >>>");
    
    String jobreqid = request.getParameter("jobreqid");
    
    User user1 = (User)request.getSession().getAttribute("user_data");
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    jbForm.setJobreqId(new Long(jobreqid).longValue());
    



    List mockexamList = BOFactory.getJobRequistionTXBO().getMockExamListForForm(user1.getSuper_user_key());
    jbForm.setMockexamsList(mockexamList);
    
    return mapping.findForward("assignExamScreen");
  }
  
  public ActionForward editExamScreen(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside editExamScreen method >>>");
    User user1 = (User)request.getSession().getAttribute("user_data");
    String jobreqid = request.getParameter("jobreqid");
    
    JobRequisitionForm jbform = (JobRequisitionForm)form;
    jbform.setJobreqId(new Long(jobreqid).longValue());
    

    RequistionExamQnsAssign jbrqnse = BOFactory.getJobRequistionTXBO().getRequistionExamQnsAssign(jbform.getJobreqId());
    if (jbrqnse != null)
    {
      jbform.setMockexamsetId(jbrqnse.getExamId());
      jbform.setPassPercentage(jbrqnse.getPassPercentage());
      jbform.setMcomckexamsetcomment(jbrqnse.getMcomckexamsetcomment());
    }
    List mockexamList = BOFactory.getJobRequistionTXBO().getMockExamListForForm(user1.getSuper_user_key());
    jbform.setMockexamsList(mockexamList);
    
    return mapping.findForward("assignExamScreen");
  }
  
  public ActionForward assignQuestionnaireScreen(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside assignQuestionnaireScreen method >>>");
    
    String jobreqid = request.getParameter("jobreqid");
    
    User user1 = (User)request.getSession().getAttribute("user_data");
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    jbForm.setJobreqId(new Long(jobreqid).longValue());
    


    List questiongroupList = BOFactory.getJobRequistionTXBO().getQuestionGroupListForForm(user1.getSuper_user_key());
    jbForm.setQuestionGroupList(questiongroupList);
    

    return mapping.findForward("assignQuestionnaireScreen");
  }
  
  public ActionForward editQuestionnaireScreen(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside editQuestionnaireScreen method >>>");
    User user1 = (User)request.getSession().getAttribute("user_data");
    String jobreqid = request.getParameter("jobreqid");
    
    JobRequisitionForm jbform = (JobRequisitionForm)form;
    jbform.setJobreqId(new Long(jobreqid).longValue());
    

    RequistionExamQnsAssign jbrqnse = BOFactory.getJobRequistionTXBO().getRequistionExamQnsAssign(jbform.getJobreqId());
    if (jbrqnse != null)
    {
      jbform.setQuestiongroupId(jbrqnse.getQnsgrpId());
      jbform.setQuestiongroupcomment(jbrqnse.getQuestiongroupcomment());
    }
    List questiongroupList = BOFactory.getJobRequistionTXBO().getQuestionGroupListForForm(user1.getSuper_user_key());
    jbform.setQuestionGroupList(questiongroupList);
    
    return mapping.findForward("assignQuestionnaireScreen");
  }
}
