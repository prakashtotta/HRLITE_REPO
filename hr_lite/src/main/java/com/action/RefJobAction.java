package com.action;

import com.bean.JobRequisition;
import com.bean.Organization;
import com.bean.RefferalEmployee;
import com.bo.BOFactory;
import com.bo.JobRequistionBO;
import com.bo.LovBO;
import com.bo.LovTXBO;
import com.common.Common;
import com.dao.RefferalDAO;
import com.form.JobRequisitionForm;
import com.resources.Constant;
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

public class RefJobAction
  extends CommonRefAction
{
  protected static final Logger logger = Logger.getLogger(RefJobAction.class);
  
  public ActionForward jobsearch(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside jobsearch method");
    
    RefferalEmployee empref = (RefferalEmployee)request.getSession().getAttribute("employee_refferal_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    List locationlist = BOFactory.getJobRequistionBO().getLocationList(empref.getSuper_user_key());
    jbForm.setLocationList(locationlist);
    List jobtypeList = BOFactory.getLovBO().getJobTypeList(empref.getSuper_user_key());
    jbForm.setJobtypeList(jobtypeList);
    List orgList = BOFactory.getLovBO().getAllOrganization(empref.getSuper_user_key());
    jbForm.setOrganizationList(orgList);
    if (jbForm.getParentOrgId() != 0L)
    {
      jbForm.setDepartmentList(BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(jbForm.getParentOrgId())));
    }
    else
    {
      List deptList = new ArrayList();
      jbForm.setDepartmentList(deptList);
    }
    List jobgradeList = BOFactory.getLovTXBO().getAllJobGradeDetails(empref.getSuper_user_key());
    jbForm.setJobgradeList(jobgradeList);
    return mapping.findForward("jobsearch");
  }
  
  public ActionForward jobsearchsubmit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside jobsearchsubmit method");
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    RefferalEmployee empref = (RefferalEmployee)request.getSession().getAttribute("employee_refferal_data");
    String searchposteddate = request.getParameter("searchposteddate");
    String cri = request.getParameter("cri");
    jbForm.setAppliedcri(cri);
    jbForm.setSearchposteddate(searchposteddate);
    jbForm.setJobTitle(jbForm.getJobTitle());
    List locationlist = BOFactory.getJobRequistionBO().getLocationList(empref.getSuper_user_key());
    jbForm.setLocationList(locationlist);
    List jobtypeList = BOFactory.getLovBO().getJobTypeList(empref.getSuper_user_key());
    jbForm.setJobtypeList(jobtypeList);
    List orgList = BOFactory.getLovBO().getAllOrganization(empref.getSuper_user_key());
    jbForm.setOrganizationList(orgList);
    if (jbForm.getParentOrgId() != 0L)
    {
      List deptList = BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(jbForm.getParentOrgId()));
      jbForm.setDepartmentList(deptList);
    }
    else if (orgList.size() > 0)
    {
      Organization org = (Organization)orgList.get(0);
      List deptList = BOFactory.getLovBO().getDepartmentListByOrg(String.valueOf(org.getOrgId()));
      jbForm.setDepartmentList(deptList);
    }
    List jobgradeList = BOFactory.getLovTXBO().getAllJobGradeDetails(empref.getSuper_user_key());
    jbForm.setJobgradeList(jobgradeList);
    request.setAttribute("searchpagedisplay", "yes");
    return mapping.findForward("jobsearch");
  }
  
  public ActionForward jobdetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside jobdetails method");
    String uuid = request.getParameter("uuid");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    RefferalEmployee empref = (RefferalEmployee)request.getSession().getAttribute("employee_refferal_data");
    try
    {
      JobRequisition jb = BOFactory.getJobRequistionBO().refjobdetails(jbForm, uuid);
      jbForm.fromValue(jb, request);
    }
    catch (Exception e)
    {
      String msg = Constant.getResourceStringValue("server.error", empref.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
      return mapping.findForward("exception");
    }
    request.setAttribute("jobapply", "yes");
    request.setAttribute("backbutton", "yes");
    return mapping.findForward("jobdetails");
  }
  
  public ActionForward jobdetailsforReferral(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside jobdetailsforReferral method");
    


    String uuid = request.getParameter("uuid");
    logger.info("uuid" + uuid);
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    
    RefferalEmployee empref = (RefferalEmployee)request.getSession().getAttribute("employee_refferal_data");
    try
    {
      jbForm.setUuid(uuid);
      JobRequisition jb = BOFactory.getJobRequistionBO().jobdetails(jbForm);
      jbForm.fromValue(jb, request);
    }
    catch (Exception e)
    {
      String msg = Constant.getResourceStringValue("server.error", empref.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
      return mapping.findForward("exception");
    }
    request.setAttribute("backbutton", "no");
    request.setAttribute("jobapply", "no");
    return mapping.findForward("jobdetailsforReferral");
  }
  
  public ActionForward addbookmark(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside addbookmark method");
    String reqid = request.getParameter("requistionId");
    String uuid = request.getParameter("uuid");
    String empemail = request.getParameter("empemail");
    String comment = request.getParameter("comment");
    RefferalEmployee empref = (RefferalEmployee)request.getSession().getAttribute("employee_refferal_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    jbForm.setJobreqId(new Long(reqid).longValue());
    try
    {
      jbForm.setJobreqId(new Long(reqid).longValue());
      JobRequisition jb = BOFactory.getJobRequistionBO().addbookmarkForEmployeeRef(jbForm, comment, empref, uuid, request);
      jbForm.fromValue(jb, request);
    }
    catch (Exception e)
    {
      String msg = Constant.getResourceStringValue("server.error", empref.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
      return mapping.findForward("exception");
    }
    request.setAttribute("jobapply", "yes");
    request.setAttribute("backbutton", "yes");
    return mapping.findForward("jobdetails");
  }
  
  public ActionForward mybookmarks(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside mybookmarks method");
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String reqid = request.getParameter("requistionId");
    String uuid = request.getParameter("secureid");
    
    request.setAttribute("backbutton", "yes");
    request.setAttribute("requistionId", reqid);
    request.setAttribute("uuid", uuid);
    return mapping.findForward("mybookmarks");
  }
  
  public ActionForward deleteMyBookmark(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteMyBookmark method");
    String favjobid = request.getParameter("favjobid");
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    RefferalDAO.deleteBookMark(new Long(favjobid).longValue());
    
    return mapping.findForward("mybookmarks");
  }
  
  public ActionForward newjobs(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside newjobs method");
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    return mapping.findForward("newjobs");
  }
}
