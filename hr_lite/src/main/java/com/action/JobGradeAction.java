package com.action;

import com.bean.JobGrade;
import com.bean.User;
import com.bo.BOFactory;
import com.bo.LovBO;
import com.common.Common;
import com.common.ValidationException;
import com.dao.LovOpsDAO;
import com.form.JobGradeForm;
import com.resources.Constant;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class JobGradeAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(JobCodeAction.class);
  
  public ActionForward jobGradelist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    JobGradeForm jobGradeForm = (JobGradeForm)form;
    request.setAttribute("searchpage", "no");
    return mapping.findForward("jobGradelist");
  }
  
  public ActionForward createJobGrade(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    JobGradeForm jobGradeForm = (JobGradeForm)form;
    String redpreview = request.getParameter("readPreview");
    jobGradeForm.setReadPreview(redpreview);
    return mapping.findForward("createJobGrade");
  }
  
  public ActionForward saveJobGrade(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveJobGrade method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user = (User)request.getSession().getAttribute("user_data");
    JobGradeForm jobGradeForm = (JobGradeForm)form;
    JobGrade jobGrade = new JobGrade();
    toValue(jobGrade, jobGradeForm);
    jobGrade.setSuper_user_key(user.getSuper_user_key());
    LovOpsDAO.saveJobGrade(jobGrade);
    String redpreview = request.getParameter("readPreview");
    jobGradeForm.setReadPreview(redpreview);
    jobGradeForm.setJobgradeId(jobGrade.getJobgradeId());
    request.setAttribute("saveJobGrade", "yes");
    
    return mapping.findForward("createJobGrade");
  }
  
  public ActionForward updateJobGrade(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updateJobGrade method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    JobGradeForm jobGradeForm = (JobGradeForm)form;
    
    String id = request.getParameter("id");
    
    JobGrade jobGrade = LovOpsDAO.getJobGradeDetails(id);
    toValue(jobGrade, jobGradeForm);
    
    jobGrade = LovOpsDAO.updateJobGrade(jobGrade);
    String redpreview = request.getParameter("readPreview");
    jobGradeForm.setReadPreview(redpreview);
    jobGradeForm.setJobgradeId(jobGrade.getJobgradeId());
    request.setAttribute("updateJobGrade", "yes");
    
    return mapping.findForward("createJobGrade");
  }
  
  public ActionForward DeleteJobGrade(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside DeleteJobGrade method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user = (User)request.getSession().getAttribute("user_data");
    JobGradeForm jobGradeForm = (JobGradeForm)form;
    
    String id = request.getParameter("id");
    try
    {
      BOFactory.getLovBO().deleteJobGrade(new Long(id).longValue());
      

      request.setAttribute("deleteJobGrade", "yes");
      return mapping.findForward("createJobGrade");
    }
    catch (ValidationException e)
    {
      String msg = Constant.getResourceStringValue("errorcode." + e.getErrorCode(), user.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
      return mapping.findForward("exception");
    }
    catch (Exception e)
    {
      String msg = Constant.getResourceStringValue("server.error", user.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
    }
    return mapping.findForward("exception");
  }
  
  public void toDelete(JobGrade jobGrade, JobGradeForm jobGradeForm)
  {
    jobGrade.setStatus("D");
  }
  
  public ActionForward jobGradeDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside jobGradeDetails method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    String jobGradeId = request.getParameter("id");
    
    JobGradeForm jobGradeForm = (JobGradeForm)form;
    
    JobGrade jobGrade = LovOpsDAO.getJobGradeDetails(jobGradeId);
    
    toForm(jobGradeForm, jobGrade);
    String redpreview = request.getParameter("readPreview");
    jobGradeForm.setReadPreview(redpreview);
    
    return mapping.findForward("createJobGrade");
  }
  
  public ActionForward searchJobGradeListpage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchJobGradeListpage method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    JobGradeForm jobGradeForm = (JobGradeForm)form;
    jobGradeForm.setJobGradeName(jobGradeForm.getJobGradeName());
    request.setAttribute("searchpage", "yes");
    return mapping.findForward("jobGradelist");
  }
  
  public void toForm(JobGradeForm jobGradeForm, JobGrade jobGrade)
  {
    jobGradeForm.setJobgradeId(jobGrade.getJobgradeId());
    jobGradeForm.setJobGradeName(jobGrade.getJobGradeName());
    jobGradeForm.setJobGradeDesc(jobGrade.getJobGradeDesc());
    jobGradeForm.setJobGradeResponsibility(jobGrade.getJobGradeResponsibility());
    jobGradeForm.setStatus(jobGrade.getStatus());
  }
  
  public void toValue(JobGrade jobGrade, JobGradeForm jobGradeForm)
  {
    jobGrade.setJobGradeName(jobGradeForm.getJobGradeName());
    jobGrade.setJobGradeDesc(jobGradeForm.getJobGradeDesc());
    jobGrade.setJobGradeResponsibility(jobGradeForm.getJobGradeResponsibility());
    jobGrade.setStatus("A");
  }
}
