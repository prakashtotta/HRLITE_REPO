package com.action;

import com.bean.AgencyRedemption;
import com.bean.ApplicantActivity;
import com.bean.BulkUploadTask;
import com.bean.JobApplicant;
import com.bean.User;
import com.bean.criteria.AgencyRedumptionSearchCriteria;
import com.bo.ApplicantBO;
import com.bo.BOFactory;
import com.bo.JobRequistionBO;
import com.bo.UserBO;
import com.common.Common;
import com.dao.RefferalDAO;
import com.form.AgencyRedemptionSummaryForm;
import com.manager.BulkTaskManager;
import com.util.ExportTaskUtil;
import com.util.StringUtils;
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

public class AgencyRedemptionSummaryAction
  extends CommonAction
{
  public ActionForward agencyredemptionsummary(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside agencyredemptionsummary method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String agencyid = request.getParameter("agencyid");
    String backurl = request.getParameter("backurl");
    AgencyRedemptionSummaryForm summaryform = (AgencyRedemptionSummaryForm)form;
    if (backurl != null) {
      summaryform.setBackurltolist(backurl);
    }
    summaryform.setAgencyId(new Long(agencyid).longValue());
    User agency = UserBO.getUserByUserId(new Long(agencyid).longValue());
    summaryform.setAgency(agency);
    return mapping.findForward("agencyredemptionsummary");
  }
  
  public ActionForward agencyredemptionlist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside agencyredemptionlist method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    AgencyRedemptionSummaryForm summaryform = (AgencyRedemptionSummaryForm)form;
    


    User user = (User)request.getSession().getAttribute("user_data");
    BOFactory.getJobRequistionBO().agencyredemptionlistLOVs(summaryform, user);
    
    logger.info("Inside agencyredemptionlist method end");
    return mapping.findForward("agencyredemptionlistpage");
  }
  
  public ActionForward searchagencyredemptionssubmit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchagencyredemptionssubmit method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String releasedate = request.getParameter("releasedate");
    String cri = request.getParameter("cri");
    AgencyRedemptionSummaryForm summaryform = (AgencyRedemptionSummaryForm)form;
    summaryform.setReleasecri(cri);
    summaryform.setReleasedate(releasedate);
    


    User user = (User)request.getSession().getAttribute("user_data");
    BOFactory.getJobRequistionBO().agencyredemptionlistLOVs(summaryform, user);
    request.setAttribute("searchpagedisplay", "yes");
    logger.info("Inside searchagencyredemptionssubmit method end");
    return mapping.findForward("agencyredemptionlistpage");
  }
  
  public ActionForward summarydetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside summarydetails method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String agredid = request.getParameter("agredid");
    String uuid = request.getParameter("secureid");
    String backtolisturl = request.getParameter("backtolisturl");
    AgencyRedemptionSummaryForm summaryform = (AgencyRedemptionSummaryForm)form;
    AgencyRedemption agencyRed = RefferalDAO.getAgencyRedmptionDetails(agredid, uuid);
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetails(String.valueOf(agencyRed.getApplicantId()));
    summaryform.setAgencyredmption(agencyRed);
    summaryform.setApplicantuuid(applicant.getUuid());
    if (backtolisturl != null) {
      summaryform.setBackurltolist(backtolisturl);
    }
    logger.info("Inside summarydetails method1111" + summaryform.getBackurltolist());
    return mapping.findForward("summarydetails");
  }
  
  public ActionForward markpaid(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside markpaid method");
    User user = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String agredid = request.getParameter("agredid");
    String uuid = request.getParameter("uuid");
    String isPaid = request.getParameter("isPaid");
    AgencyRedemptionSummaryForm summaryform = (AgencyRedemptionSummaryForm)form;
    AgencyRedemption agencyRed = RefferalDAO.getAgencyRedmptionDetails(agredid, uuid);
    if ((isPaid != null) && (isPaid.equals("Y")))
    {
      agencyRed.setIsPaid(isPaid);
      agencyRed.setPaiddate(new Date());
      agencyRed.setPaidby(user.getUserName());
      agencyRed.setUpdatedBy(user.getUserName());
      agencyRed.setUpdatedDate(new Date());
    }
    else
    {
      agencyRed.setIsPaid(isPaid);
      agencyRed.setPaiddate(null);
      agencyRed.setPaidby(null);
      agencyRed.setUpdatedBy(user.getUserName());
      agencyRed.setUpdatedDate(new Date());
    }
    RefferalDAO.updateAgencyRededmption(agencyRed);
    summaryform.setIsPaid(agencyRed.getIsPaid());
    return mapping.findForward("markpaid");
  }
  
  public ActionForward markpaidforapplicants(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside markpaidforapplicants method");
    User user = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String agrefids = request.getParameter("agrefids");
    String comment = request.getParameter("comment");
    
    AgencyRedemptionSummaryForm summaryform = (AgencyRedemptionSummaryForm)form;
    
    List redList = new ArrayList();
    List redListdetails = new ArrayList();
    if (!StringUtils.isNullOrEmpty(agrefids))
    {
      agrefids = agrefids.substring(0, agrefids.length() - 1);
      
      redList = StringUtils.tokenizeString(agrefids, ",");
    }
    logger.info("redList" + redList);
    for (int i = 0; i < redList.size(); i++)
    {
      String agrefId = (String)redList.get(i);
      AgencyRedemption agencyRed = RefferalDAO.getAgencyRedmptionDetails(agrefId);
      agencyRed.setIsPaid("Y");
      agencyRed.setPaiddate(new Date());
      agencyRed.setPaidby(user.getUserName());
      agencyRed.setUpdatedBy(user.getUserName());
      agencyRed.setUpdatedDate(new Date());
      RefferalDAO.updateAgencyRededmption(agencyRed);
      saveApplicantActivity(agencyRed, user, comment);
    }
    forward.setRedirect(true);
    String redirecturl = "agencyredemptionsummary.do?method=agencyredemptionlist";
    forward.setPath(redirecturl);
    
    return forward;
  }
  
  private void saveApplicantActivity(AgencyRedemption refRed, User user1, String comment)
  {
    ApplicantActivity activity = new ApplicantActivity();
    activity.setApplicantId(refRed.getApplicantId());
    activity.setFullName(refRed.getApplicantName());
    activity.setUuid(BOFactory.getApplicantBO().getUUIDNameEmailByApplicantId(refRed.getApplicantId()).getUuid());
    activity.setCreatedBy(user1.getUserName());
    activity.setUserId(user1.getUserId());
    activity.setUserFullName(user1.getFirstName() + " " + user1.getLastName());
    activity.setCreatedDate(new Date());
    activity.setComment("");
    activity.setCreatedByType(Common.APPLICANT_CREATED_BY_TYPE_INTERNAL);
    activity.setActivityName("REDEMPTION_PAID");
    activity.setIsDisplayAppPage("Y");
    BOFactory.getApplicantBO().saveApplicantActivity(activity);
  }
  
  public ActionForward exporttoexcelAllSearchAgencyRedemption(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside exporttoexcelAllSearchAgencyRedemption method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    

    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String releasedate = request.getParameter("releasedate");
    String cri = request.getParameter("cri");
    AgencyRedemptionSummaryForm summaryform = (AgencyRedemptionSummaryForm)form;
    summaryform.setReleasecri(cri);
    summaryform.setReleasedate(releasedate);
    BOFactory.getJobRequistionBO().agencyredemptionlistLOVs(summaryform, user1);
    





















    BulkUploadTask task = new BulkUploadTask();
    ExportTaskUtil exp = new ExportTaskUtil();
    exp.setUser(user1);
    exp.setTask(task);
    exp.setSearcherrole(Common.EXPORT_AGENCY_REDEMPTION_SEARCH_RESULTS);
    exp.setTasktype(Common.EXPORT_AGENCY_REDEMPTION_SEARCH_RESULTS);
    














    AgencyRedumptionSearchCriteria searchcriteria = new AgencyRedumptionSearchCriteria();
    searchcriteria.setReleasecri(summaryform.getReleasecri());
    searchcriteria.setReleasedate(summaryform.getReleasedate());
    searchcriteria.setUom(summaryform.getUom());
    searchcriteria.setAgencyId(summaryform.getAgencyId());
    searchcriteria.setRequitionId(summaryform.getRequitionId());
    searchcriteria.setRefBudgetId(summaryform.getRefBudgetId());
    searchcriteria.setRefferalSchemeId(summaryform.getRefferalSchemeId());
    searchcriteria.setRefferalSchemeTypeId(summaryform.getRefferalSchemeTypeId());
    searchcriteria.setRuleId(summaryform.getRuleId());
    searchcriteria.setState(summaryform.getState());
    searchcriteria.setIsPaid(summaryform.getIsPaid());
    searchcriteria.setApplicantNo(summaryform.getApplicantNo());
    searchcriteria.setApplicantName(summaryform.getApplicantName());
    

    exp.setAgencyRedumptionSearchCriteria(searchcriteria);
    BulkTaskManager.export(exp);
    request.setAttribute("exporttoexcel", "yes");
    
    return mapping.findForward("agencyredemptionlistpage");
  }
}
