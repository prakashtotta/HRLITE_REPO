package com.action;

import com.bean.JobType;
import com.bean.User;
import com.bo.BOFactory;
import com.bo.LovBO;
import com.common.Common;
import com.common.ValidationException;
import com.form.JobTypeForm;
import com.resources.Constant;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class JobTypeAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(JobTypeAction.class);
  
  public ActionForward jobtypelist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside jobtypelist method");
    JobTypeForm jobTypeForm = (JobTypeForm)form;
    return mapping.findForward("jobtypelist");
  }
  
  public ActionForward createJobType(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside orgemailTemplatelist method");
    JobTypeForm jobTypeForm = (JobTypeForm)form;
    return mapping.findForward("createJobType");
  }
  
  public ActionForward saveJobType(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveJobType method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    JobTypeForm jobTypeForm = (JobTypeForm)form;
    JobType jobType = new JobType();
    jobTypeForm.toValue(jobType, request);
    jobType.setStatus("A");
    jobType.setSuper_user_key(user1.getSuper_user_key());
    jobType = BOFactory.getLovBO().saveJobTypeDetails(jobType);
    
    jobTypeForm.fromValue(jobType, request);
    jobTypeForm.setJobTypeId(jobType.getJobTypeId());
    request.setAttribute("savejobtype", "yes");
    return mapping.findForward("createJobType");
  }
  
  public ActionForward editJobTypeDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveJobType method");
    JobTypeForm jobTypeForm = (JobTypeForm)form;
    String jobTypeId = request.getParameter("jobTypeId");
    JobType jobType = BOFactory.getLovBO().getJobTypeDetails(new Long(jobTypeId).longValue());
    
    jobTypeForm.fromValue(jobType, request);
    
    return mapping.findForward("createJobType");
  }
  
  public ActionForward updateJobType(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updateJobType method");
    JobTypeForm jobTypeForm = (JobTypeForm)form;
    String jobTypeId = request.getParameter("jobTypeId");
    JobType jobType = BOFactory.getLovBO().getJobTypeDetails(new Long(jobTypeId).longValue());
    jobTypeForm.toValue(jobType, request);
    jobType = BOFactory.getLovBO().updateJobTypeDetails(jobType);
    
    jobTypeForm.fromValue(jobType, request);
    jobTypeForm.setJobTypeId(jobType.getJobTypeId());
    request.setAttribute("updatejobtype", "yes");
    return mapping.findForward("createJobType");
  }
  
  public ActionForward deleteJobType(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteJobType method");
    JobTypeForm jobTypeForm = (JobTypeForm)form;
    User user = (User)request.getSession().getAttribute("user_data");
    String jobTypeId = request.getParameter("jobTypeId");
    String fromwhere = request.getParameter("fromwhere");
    try
    {
      BOFactory.getLovBO().deleteJobType(new Long(jobTypeId).longValue());
      

      request.setAttribute("deletejobtype", "yes");
      return mapping.findForward("createJobType");
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
}
