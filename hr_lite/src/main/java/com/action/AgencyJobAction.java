package com.action;

import com.bean.FavoriteJob;
import com.bean.JobRequisition;
import com.bean.User;
import com.bo.BOFactory;
import com.bo.JobRequistionBO;
import com.bo.LovBO;
import com.dao.RefferalDAO;
import com.form.JobRequisitionForm;
import com.util.EncryptDecrypt;
import java.io.PrintStream;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AgencyJobAction
  extends CommonAgencyAction
{
  protected static final Logger logger = Logger.getLogger(AgencyJobAction.class);
  
  public ActionForward jobsearch(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    User agency = (User)request.getSession().getAttribute("agency_data");
    
    logger.info("Inside jobsearch method");
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    BOFactory.getLovBO().jobsearchAgencyLov(jbForm, agency);
    request.setAttribute("searchpagedisplay", "no");
    return mapping.findForward("jobsearch");
  }
  
  public ActionForward jobsearchsubmit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside jobsearchsubmit method");
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    User agency = (User)request.getSession().getAttribute("agency_data");
    String searchposteddate = request.getParameter("searchposteddate");
    String cri = request.getParameter("cri");
    jbForm.setAppliedcri(cri);
    jbForm.setSearchposteddate(searchposteddate);
    jbForm.setJobTitle(jbForm.getJobTitle());
    BOFactory.getLovBO().jobsearchAgencyLov(jbForm, agency);
    request.setAttribute("searchpagedisplay", "yes");
    return mapping.findForward("jobsearch");
  }
  
  public ActionForward jobdetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside jobdetails method");
    User user1 = (User)request.getSession().getAttribute("agency_data");
    String reqid = request.getParameter("reqid");
    String secureid = request.getParameter("secureid");
    String backurl = request.getParameter("backurl");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    jbForm.setJobreqId(new Long(reqid).longValue());
    jbForm.setUuid(secureid);
    JobRequisition jb = BOFactory.getJobRequistionBO().jobdetailsForAgencyWithUUID(jbForm, user1);
    jbForm.fromValue(jb, request);
    if ((backurl != null) && (backurl.length() > 0)) {
      jbForm.setBackurl(backurl);
    }
    request.setAttribute("jobapply", "yes");
    request.setAttribute("backbutton", "yes");
    return mapping.findForward("jobdetails");
  }
  
  public ActionForward jobdetailsforAgency(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside jobdetailsforAgency method");
    String reqid = request.getParameter("reqid");
    reqid = EncryptDecrypt.decrypt(reqid);
    User user1 = (User)request.getSession().getAttribute("agency_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    jbForm.setJobreqId(new Long(reqid).longValue());
    JobRequisition jb = BOFactory.getJobRequistionBO().jobdetailsForAgency(jbForm, user1);
    jbForm.fromValue(jb, request);
    
    request.setAttribute("backbutton", "no");
    request.setAttribute("jobapply", "no");
    return mapping.findForward("jobdetailsforAgency");
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
  
  public ActionForward addbookmark(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside addbookmark method");
    String reqid = request.getParameter("requistionId");
    String empemail = request.getParameter("empemail");
    String comment = request.getParameter("comment");
    String uuid = request.getParameter("uuid");
    String backurl = request.getParameter("backurl");
    
    User user1 = (User)request.getSession().getAttribute("agency_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    jbForm.setJobreqId(new Long(reqid).longValue());
    FavoriteJob fav = new FavoriteJob();
    fav.setReqId(new Long(reqid).longValue());
    fav.setAgencyId(user1.getUserId());
    fav.setComment(comment);
    fav.setCreatedBy(user1.getEmailId());
    fav.setCreatedDate(new Date());
    fav.setUuid(uuid);
    JobRequisition jb = BOFactory.getJobRequistionBO().addbookmark(jbForm, fav, request, user1);
    jbForm.setJobreqId(jb.getJobreqId());
    jbForm.fromValue(jb, request);
    jbForm.setPublishedDate(jb.getPublishedDate());
    if ((backurl != null) && (backurl.length() > 0)) {
      jbForm.setBackurl(backurl);
    }
    request.setAttribute("backbutton", "yes");
    return mapping.findForward("jobdetails");
  }
  
  public ActionForward mybookmarks(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside mybookmarks method");
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    String reqid = request.getParameter("requistionId");
    String uuid = request.getParameter("secureid");
    

    request.setAttribute("requistionId", reqid);
    request.setAttribute("uuid", uuid);
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
  
  public ActionForward requistionapplicantlist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside requistionapplicantlist method");
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    return mapping.findForward("jobrequistiontreelist");
  }
  
  public ActionForward recruiterapplicantlist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside recruiterapplicantlist method");
    
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    return mapping.findForward("recruiterjobreqtreelist");
  }
  
  public ActionForward editjobreqselector(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside editjobreqselector method");
    String jobreqId = request.getParameter("jobreqId");
    User user1 = (User)request.getSession().getAttribute("agency_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String approverstatus = request.getParameter("approverstatus");
    String offerapplicant = request.getParameter("offerapplicant");
    JobRequisitionForm jbForm = (JobRequisitionForm)form;
    jbForm.setJobreqId(new Long(jobreqId).longValue());
    JobRequisition jb = BOFactory.getJobRequistionBO().getSummaryDataForAgency(jbForm, approverstatus, offerapplicant, user1);
    jbForm.fromValue(jb, request);
    return mapping.findForward("summary");
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
}
