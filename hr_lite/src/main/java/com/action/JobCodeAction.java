package com.action;

import com.bean.JobCode;
import com.dao.LovOpsDAO;
import com.form.JobCodeForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class JobCodeAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(JobCodeAction.class);
  
  public ActionForward jobCodelist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    JobCodeForm jobCodeForm = (JobCodeForm)form;
    
    return mapping.findForward("jobCodelist");
  }
  
  public ActionForward createJobCode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    JobCodeForm jobCodeForm = (JobCodeForm)form;
    String redpreview = request.getParameter("readPreview");
    jobCodeForm.setReadPreview(redpreview);
    
    return mapping.findForward("createJobCode");
  }
  
  public ActionForward saveJobCode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveJobCode method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    JobCodeForm jobCodeForm = (JobCodeForm)form;
    JobCode jobCode = new JobCode();
    toValue(jobCode, jobCodeForm);
    
    LovOpsDAO.saveJobCode(jobCode);
    String readpreview = request.getParameter("readPreview");
    jobCodeForm.setReadPreview(readpreview);
    request.setAttribute("saveJobcode", "yes");
    
    return mapping.findForward("createJobCode");
  }
  
  public ActionForward updateJobCode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updateJobCode method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    JobCodeForm jobCodeForm = (JobCodeForm)form;
    
    String id = request.getParameter("id");
    
    JobCode jobCode = LovOpsDAO.getJobCodeDetails(id);
    toValue(jobCode, jobCodeForm);
    
    jobCode = LovOpsDAO.updateJobCode(jobCode);
    
    String readpreview = request.getParameter("readPreview");
    jobCodeForm.setReadPreview(readpreview);
    request.setAttribute("updateJobcode", "yes");
    return mapping.findForward("createJobCode");
  }
  
  public ActionForward DeleteJobCode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside DeleteJobCode method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    JobCodeForm JobCodeForm = (JobCodeForm)form;
    
    String id = request.getParameter("id");
    JobCode JobCode = LovOpsDAO.getJobCodeDetails(id);
    toDelete(JobCode, JobCodeForm);
    
    JobCode = LovOpsDAO.updateJobCode(JobCode);
    request.setAttribute("deleteJobcode", "yes");
    return mapping.findForward("createJobCode");
  }
  
  public void toDelete(JobCode JobCode, JobCodeForm JobCodeForm)
  {
    JobCode.setStatus("D");
  }
  
  public ActionForward jobCodeDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside jobCodeDetails method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    String jobCodeId = request.getParameter("id");
    
    JobCodeForm jobCodeForm = (JobCodeForm)form;
    
    JobCode jobCode = LovOpsDAO.getJobCodeDetails(jobCodeId);
    
    toForm(jobCodeForm, jobCode);
    String redpreview = request.getParameter("readPreview");
    jobCodeForm.setReadPreview(redpreview);
    
    return mapping.findForward("createJobCode");
  }
  
  public void toForm(JobCodeForm jobCodeForm, JobCode jobCode)
  {
    jobCodeForm.setJobcodeId(jobCode.getJobcodeId());
    jobCodeForm.setJobCode(jobCode.getJobCode());
    jobCodeForm.setJobName(jobCode.getJobName());
    jobCodeForm.setJobDesc(jobCode.getJobDesc());
    jobCodeForm.setJobResponsibility(jobCode.getJobResponsibility());
    jobCodeForm.setStatus(jobCode.getStatus());
  }
  
  public void toValue(JobCode jobCode, JobCodeForm jobCodeForm)
  {
    jobCode.setJobCode(jobCodeForm.getJobCode());
    jobCode.setJobName(jobCodeForm.getJobName());
    jobCode.setJobDesc(jobCodeForm.getJobDesc());
    jobCode.setJobResponsibility(jobCodeForm.getJobResponsibility());
    jobCode.setStatus("A");
  }
}
