package com.action;

import com.bean.ApplicantActivity;
import com.bean.BulkUploadTask;
import com.bean.JobApplicant;
import com.bean.Organization;
import com.bean.ReferralRedemption;
import com.bean.User;
import com.bean.criteria.ReferalRedumptionSearchCriteria;
import com.bo.ApplicantBO;
import com.bo.BOFactory;
import com.bo.JobRequistionBO;
import com.bo.LovBO;
import com.bo.RefBO;
import com.common.Common;
import com.dao.RefferalDAO;
import com.form.ReferralRedemptionSummaryForm;
import com.manager.BulkTaskManager;
import com.resources.Constant;
import com.util.ExportTaskUtil;
import com.util.StringUtils;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ReferralRedemptionSummaryAction
  extends CommonAction
{
  public ActionForward loadDeptlistWithProjectcode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside loadDeptlistWithProjectcode method");
    String orgId = request.getParameter("orgId");
    System.out.println("orgId" + orgId);
    ReferralRedemptionSummaryForm summaryform = (ReferralRedemptionSummaryForm)form;
    summaryform.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(orgId));
    
    return mapping.findForward("deptlistwithprojectcode");
  }
  
  public ActionForward referralredemptionlist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside referralredemptionlist method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ReferralRedemptionSummaryForm summaryform = (ReferralRedemptionSummaryForm)form;
    User user = (User)request.getSession().getAttribute("user_data");
    String usertype = "Employee";
    

    List orgList = BOFactory.getLovBO().getAllOrganization(user.getSuper_user_key());
    summaryform.setOrganizationList(orgList);
    
    List deptlist = new ArrayList();
    summaryform.setDepartmentList(deptlist);
    if (orgList.size() > 0)
    {
      Organization org = (Organization)orgList.get(0);
      summaryform.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(org.getOrgId())));
    }
    List jobtitleList = BOFactory.getJobRequistionBO().getJobTitlesActiveListForForm(user.getSuper_user_key());
    
    summaryform.setJobtitleList(jobtitleList);
    List refferalBudgetCodeList = BOFactory.getLovBO().getAllRefferalBudgetCode(user.getSuper_user_key());
    List refferalSchemeTypeList = BOFactory.getLovBO().getAllRefferalSchemeType(user.getSuper_user_key());
    List refferalSchemeList = BOFactory.getLovBO().getAllRefferalSchemes(usertype, user.getSuper_user_key());
    List ruleList = BOFactory.getLovBO().getAllRefferalRules(user.getSuper_user_key());
    summaryform.setReferralBudgetCodeList(refferalBudgetCodeList);
    summaryform.setRefferalSchemeTypeList(refferalSchemeTypeList);
    summaryform.setRuleList(ruleList);
    summaryform.setReferralSchemeList(refferalSchemeList);
    summaryform.setReleaseTypeList(Constant.getReleasedTypesList());
    summaryform.setPaidTypeList(Constant.getYesNoList());
    
    logger.info("Inside referralredemptionlist method end");
    return mapping.findForward("referralredemptionlistpage");
  }
  
  public ActionForward exporttoexcelAllSearchReferalRedemption(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside exporttoexcelAllSearchReferalRedemption method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    

    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String releasedate = request.getParameter("releasedate");
    String cri = request.getParameter("cri");
    ReferralRedemptionSummaryForm summaryform = (ReferralRedemptionSummaryForm)form;
    summaryform.setReleasecri(cri);
    summaryform.setReleasedate(releasedate);
    BOFactory.getRefBO().referalredemptionlistLOVs(summaryform, user1);
    

    BulkUploadTask task = new BulkUploadTask();
    ExportTaskUtil exp = new ExportTaskUtil();
    exp.setUser(user1);
    exp.setTask(task);
    exp.setSearcherrole(Common.EXPORT_REFERAL_REDEMPTION_SEARCH_RESULTS);
    exp.setTasktype(Common.EXPORT_REFERAL_REDEMPTION_SEARCH_RESULTS);
    

    ReferalRedumptionSearchCriteria searchcriteria = new ReferalRedumptionSearchCriteria();
    searchcriteria.setReleasecri(summaryform.getReleasecri());
    searchcriteria.setReleasedate(summaryform.getReleasedate());
    searchcriteria.setUom(summaryform.getUom());
    searchcriteria.setEmpcode(summaryform.getEmpcode());
    searchcriteria.setRequitionId(summaryform.getRequitionId());
    searchcriteria.setRefBudgetId(summaryform.getRefBudgetId());
    searchcriteria.setRefferalSchemeId(summaryform.getRefferalSchemeId());
    searchcriteria.setRefferalSchemeTypeId(summaryform.getRefferalSchemeTypeId());
    searchcriteria.setRuleId(summaryform.getRuleId());
    searchcriteria.setState(summaryform.getState());
    searchcriteria.setIsPaid(summaryform.getIsPaid());
    searchcriteria.setApplicantNo(summaryform.getApplicantNo());
    searchcriteria.setApplicantName(summaryform.getApplicantName());
    searchcriteria.setEmpname(summaryform.getEmpname());
    searchcriteria.setEmpemail(summaryform.getEmpemail());
    searchcriteria.setOrgId(summaryform.getOrgId());
    searchcriteria.setDepartmentId(summaryform.getDepartmentId());
    exp.setReferalRedumptionSearchCriteria(searchcriteria);
    BulkTaskManager.export(exp);
    request.setAttribute("exporttoexcel", "yes");
    
    return mapping.findForward("referralredemptionlistpage");
  }
  
  public ActionForward searchreferralredemptionssubmit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchreferralredemptionssubmit method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String releasedate = request.getParameter("releasedate");
    String cri = request.getParameter("cri");
    ReferralRedemptionSummaryForm summaryform = (ReferralRedemptionSummaryForm)form;
    summaryform.setReleasecri(cri);
    summaryform.setReleasedate(releasedate);
    String usertype = "Employee";
    

    List orgList = BOFactory.getLovBO().getAllOrganization(user1.getSuper_user_key());
    summaryform.setOrganizationList(orgList);
    
    List deptlist = new ArrayList();
    summaryform.setDepartmentList(deptlist);
    if (orgList.size() > 0)
    {
      Organization org = (Organization)orgList.get(0);
      summaryform.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(org.getOrgId())));
    }
    List jobtitleList = BOFactory.getJobRequistionBO().getJobTitlesActiveListForForm(user1.getSuper_user_key());
    
    summaryform.setJobtitleList(jobtitleList);
    List refferalBudgetCodeList = BOFactory.getLovBO().getAllRefferalBudgetCode(user1.getSuper_user_key());
    List refferalSchemeTypeList = BOFactory.getLovBO().getAllRefferalSchemeType(user1.getSuper_user_key());
    List refferalSchemeList = BOFactory.getLovBO().getAllRefferalSchemes(usertype, user1.getSuper_user_key());
    List ruleList = BOFactory.getLovBO().getAllRefferalRules(user1.getSuper_user_key());
    summaryform.setReferralBudgetCodeList(refferalBudgetCodeList);
    summaryform.setRefferalSchemeTypeList(refferalSchemeTypeList);
    summaryform.setRuleList(ruleList);
    summaryform.setReferralSchemeList(refferalSchemeList);
    summaryform.setReleaseTypeList(Constant.getReleasedTypesList());
    summaryform.setPaidTypeList(Constant.getYesNoList());
    request.setAttribute("searchpagedisplay", "yes");
    logger.info("Inside searchreferralredemptionssubmit method end");
    return mapping.findForward("referralredemptionlistpage");
  }
  
  public ActionForward markpaid(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside markpaid method");
    User user = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String refredid = request.getParameter("refredid");
    String uuid = request.getParameter("uuid");
    String isPaid = request.getParameter("isPaid");
    ReferralRedemptionSummaryForm summaryform = (ReferralRedemptionSummaryForm)form;
    ReferralRedemption refRed = RefferalDAO.getReferralRedmptionDetails(refredid, uuid);
    if ((isPaid != null) && (isPaid.equals("Y")))
    {
      refRed.setIsPaid(isPaid);
      refRed.setPaiddate(new Date());
      refRed.setPaidby(user.getUserName());
      refRed.setUpdatedBy(user.getUserName());
      refRed.setUpdatedDate(new Date());
    }
    else
    {
      refRed.setIsPaid(isPaid);
      refRed.setPaiddate(null);
      refRed.setPaidby(null);
      refRed.setUpdatedBy(user.getUserName());
      refRed.setUpdatedDate(new Date());
    }
    RefferalDAO.updateRefferalRededmption(refRed);
    summaryform.setIsPaid(refRed.getIsPaid());
    return mapping.findForward("markpaid");
  }
  
  public ActionForward markpaidforapplicants(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside markpaidforapplicants method");
    User user = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String refredids = request.getParameter("refredids");
    logger.info("refredids... " + refredids);
    ReferralRedemptionSummaryForm summaryform = (ReferralRedemptionSummaryForm)form;
    




    request.setAttribute("refredids", refredids);
    
    return mapping.findForward("markpaidforapplicants");
  }
  
  public ActionForward markpaidforapplicantsSubmit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside markpaidforapplicantsSubmit method");
    User user = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String refredids = request.getParameter("refredids");
    String comment = request.getParameter("comment");
    logger.info("refredids... " + refredids);
    ReferralRedemptionSummaryForm summaryform = (ReferralRedemptionSummaryForm)form;
    
    List redList = new ArrayList();
    List redListdetails = new ArrayList();
    if (!StringUtils.isNullOrEmpty(refredids))
    {
      refredids = refredids.substring(0, refredids.length() - 1);
      
      redList = StringUtils.tokenizeString(refredids, ",");
    }
    logger.info("redList" + redList);
    for (int i = 0; i < redList.size(); i++)
    {
      String refId = (String)redList.get(i);
      ReferralRedemption refRed = RefferalDAO.getReferralRedmptionDetails(refId);
      if (refRed.getState().equals("Released")) {
        refRed.setIsPaid("Y");
      }
      refRed.setPaiddate(new Date());
      refRed.setPaidby(user.getUserName());
      refRed.setUpdatedBy(user.getUserName());
      refRed.setUpdatedDate(new Date());
      RefferalDAO.updateRefferalRededmption(refRed);
      saveApplicantActivity(refRed, user, comment);
    }
    request.setAttribute("markforpaid", "yes");
    





    return mapping.findForward("markpaidforapplicants");
  }
  
  private void saveApplicantActivity(ReferralRedemption refRed, User user1, String comment)
  {
    ApplicantActivity activity = new ApplicantActivity();
    activity.setApplicantId(refRed.getApplicantId());
    activity.setFullName(refRed.getApplicantName());
    activity.setUuid(BOFactory.getApplicantBO().getUUIDNameEmailByApplicantId(refRed.getApplicantId()).getUuid());
    activity.setCreatedBy(user1.getUserName());
    activity.setUserId(user1.getUserId());
    activity.setUserFullName(user1.getFirstName() + " " + user1.getLastName());
    activity.setCreatedDate(new Date());
    activity.setComment(comment);
    activity.setCreatedByType(Common.APPLICANT_CREATED_BY_TYPE_INTERNAL);
    activity.setActivityName("REDEMPTION_PAID");
    activity.setIsDisplayAppPage("Y");
    BOFactory.getApplicantBO().saveApplicantActivity(activity);
  }
  
  public ActionForward summarydetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside summarydetails method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String refredid = request.getParameter("refredid");
    String uuid = request.getParameter("secureid");
    String backtolisturl = request.getParameter("backtolisturl");
    ReferralRedemptionSummaryForm summaryform = (ReferralRedemptionSummaryForm)form;
    ReferralRedemption refRed = RefferalDAO.getReferralRedmptionDetails(refredid, uuid);
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetails(String.valueOf(refRed.getApplicantId()));
    summaryform.setReferralRedemption(refRed);
    summaryform.setApplicantUuid(applicant.getUuid());
    summaryform.setBackurltolist(backtolisturl);
    return mapping.findForward("summarydetails");
  }
}
