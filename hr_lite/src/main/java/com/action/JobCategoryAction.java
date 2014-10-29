package com.action;

import com.bean.User;
import com.bean.lov.JobCategory;
import com.bo.BOFactory;
import com.bo.LovBO;
import com.common.Common;
import com.common.ValidationException;
import com.form.JobCategoryForm;
import com.resources.Constant;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class JobCategoryAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(JobCategoryAction.class);
  
  public ActionForward jobCategoryList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside jobCategoryList method");
    JobCategoryForm jobCategoryForm = (JobCategoryForm)form;
    
    return mapping.findForward("jobCategoryList");
  }
  
  public ActionForward createJobCategory(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside createJobCategory method");
    JobCategoryForm jobCategoryForm = (JobCategoryForm)form;
    
    return mapping.findForward("createJobCategory");
  }
  
  public ActionForward editJobCategory(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside editJobCategory method");
    JobCategoryForm jobCategoryForm = (JobCategoryForm)form;
    
    String jobCategoryId = request.getParameter("jobCategoryId");
    logger.info("jobCategoryId >> " + jobCategoryId);
    
    JobCategory jobCategory = BOFactory.getLovBO().getJobCategoryDetails(new Long(jobCategoryId).longValue());
    
    jobCategoryForm.fromValue(jobCategory, request);
    
    return mapping.findForward("createJobCategory");
  }
  
  public ActionForward saveJobCategory(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveJobCategory method");
    JobCategoryForm jobCategoryForm = (JobCategoryForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    

    JobCategory jobCategory = new JobCategory();
    
    jobCategoryForm.toValue(jobCategory, request);
    jobCategory.setStatus("A");
    jobCategory.setSuper_user_key(user1.getSuper_user_key());
    jobCategory = BOFactory.getLovBO().saveJobCategoryDetails(jobCategory);
    jobCategoryForm.fromValue(jobCategory, request);
    request.setAttribute("JobCategorysaved", "yes");
    return mapping.findForward("createJobCategory");
  }
  
  public ActionForward updateJobCategory(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updateJobCategory method");
    JobCategoryForm jobCategoryForm = (JobCategoryForm)form;
    
    String jobCategoryId = request.getParameter("jobCategoryId");
    logger.info("jobCategoryId >> " + jobCategoryId);
    
    JobCategory jobCategory = BOFactory.getLovBO().getJobCategoryDetails(new Long(jobCategoryId).longValue());
    
    jobCategoryForm.toValue(jobCategory, request);
    jobCategory = BOFactory.getLovBO().updateJobCategoryDetails(jobCategory);
    jobCategoryForm.fromValue(jobCategory, request);
    request.setAttribute("JobCategoryupdated", "yes");
    return mapping.findForward("createJobCategory");
  }
  
  public ActionForward deleteJobCategory(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteJobCategory method");
    JobCategoryForm jobCategoryForm = (JobCategoryForm)form;
    User user = (User)request.getSession().getAttribute("user_data");
    String jobCategoryId = request.getParameter("jobCategoryId");
    try
    {
      BOFactory.getLovBO().deleteJobCategory(new Long(jobCategoryId).longValue());
      

      request.setAttribute("JobCategorydeleted", "yes");
      return mapping.findForward("createJobCategory");
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
