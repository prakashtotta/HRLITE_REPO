package com.action;

import com.bean.JobRequisition;
import com.bean.ReferAFriend;
import com.bean.RefferalEmployee;
import com.bo.BOFactory;
import com.bo.JobRequistionBO;
import com.bo.RefBO;
import com.dao.RefferalDAO;
import com.form.ReferAFriendForm;
import com.util.StringUtils;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ReferFriendAction
  extends CommonRefAction
{
  protected static final Logger logger = Logger.getLogger(ReferFriendAction.class);
  
  public ActionForward createreferfriend(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside createreferfriend method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String jobreqid = request.getParameter("jobreqid");
    String fromjobpage = request.getParameter("fromjobpage");
    ReferAFriendForm friendform = (ReferAFriendForm)form;
    JobRequisition jobreq = null;
    if (jobreqid != null) {
      jobreq = BOFactory.getJobRequistionBO().getActiveOpenJobRequision(jobreqid);
    }
    if (jobreq != null)
    {
      friendform.setJobRequisitionId(jobreq.getJobreqId());
      friendform.setJobTitle(jobreq.getJobTitle());
    }
    else if (!StringUtils.isNullOrEmpty(fromjobpage))
    {
      request.setAttribute("requistionclosed", "yes");
    }
    return mapping.findForward("createreferfriend");
  }
  
  public ActionForward savefriendsdata(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside savefriendsdata method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ReferAFriend reffriend = new ReferAFriend();
    ReferAFriendForm friendform = (ReferAFriendForm)form;
    logger.info("friendform.getJobRequisitionId()" + friendform.getJobRequisitionId());
    RefferalEmployee user1 = (RefferalEmployee)request.getSession().getAttribute("employee_refferal_data");
    
    String emailId = null;
    if (friendform.getJobRequisitionId() > 0L) {
      emailId = RefferalDAO.isfriendExists(friendform.getEmailId(), friendform.getJobRequisitionId());
    }
    if (!StringUtils.isNullOrEmpty(emailId))
    {
      request.setAttribute("isFriendexist", "yes");
    }
    else
    {
      friendform.toValue(reffriend, request);
      
      reffriend.setEmp_ref_id(user1.getEmployeeReferalId());
      RefferalDAO.savereferfriend(reffriend);
      request.setAttribute("isfriendadded", "yes");
      request.setAttribute("editfriends", "yes");
    }
    friendform.fromValue(reffriend, request);
    
    RefBO.sendReferAFriendEmail(user1, reffriend);
    
    return mapping.findForward("createreferfriend");
  }
  
  public ActionForward updatefriendsdata(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updatefriendsdata method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ReferAFriendForm friendform = (ReferAFriendForm)form;
    RefferalEmployee user1 = (RefferalEmployee)request.getSession().getAttribute("employee_refferal_data");
    
    String referafriendId = request.getParameter("referafriendId");
    logger.info("referafriendId : " + referafriendId);
    ReferAFriend refFriend = RefferalDAO.getReferFriend(referafriendId);
    

    refFriend.setName(friendform.getName());
    refFriend.setEmailId(friendform.getEmailId());
    refFriend.setNote(friendform.getNote());
    refFriend.setUpdatedBy(user1.getEmployeename());
    refFriend.setUpdatedDate(new Date());
    
    RefferalDAO.updatereferfriend(refFriend);
    request.setAttribute("isfriendupdated", "yes");
    

    friendform.fromValue(refFriend, request);
    
    RefBO.sendReferAFriendEmail(user1, refFriend);
    
    request.setAttribute("editfriends", "yes");
    return mapping.findForward("createreferfriend");
  }
  
  public ActionForward searchreferfriends(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchreferfriends method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ReferAFriend reffriend = new ReferAFriend();
    ReferAFriendForm friendform = (ReferAFriendForm)form;
    RefferalEmployee user1 = (RefferalEmployee)request.getSession().getAttribute("employee_refferal_data");
    request.setAttribute("searchpagedisplay", "no");
    
    return mapping.findForward("searchreferfriends");
  }
  
  public ActionForward editfriend(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside editfriend method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ReferAFriendForm friendform = (ReferAFriendForm)form;
    RefferalEmployee user1 = (RefferalEmployee)request.getSession().getAttribute("employee_refferal_data");
    
    String referafriendId = request.getParameter("referafriendId");
    ReferAFriend refFriend = RefferalDAO.getReferFriend(referafriendId);
    
    friendform.fromValue(refFriend, request);
    request.setAttribute("editfriends", "yes");
    return mapping.findForward("createreferfriend");
  }
  
  public ActionForward deletefriendsdata(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deletefriendsdata method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ReferAFriendForm friendform = (ReferAFriendForm)form;
    
    String referafriendId = request.getParameter("referafriendId");
    ReferAFriend refFriend = RefferalDAO.getReferFriend(referafriendId);
    
    RefferalDAO.deletefriend(new Long(referafriendId).longValue());
    

    request.setAttribute("deletefriend", "yes");
    return mapping.findForward("createreferfriend");
  }
  
  public ActionForward searchmyfriends(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchmyfriends method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ReferAFriend reffriend = new ReferAFriend();
    ReferAFriendForm friendform = (ReferAFriendForm)form;
    RefferalEmployee user1 = (RefferalEmployee)request.getSession().getAttribute("employee_refferal_data");
    request.setAttribute("searchpagedisplay", "yes");
    
    return mapping.findForward("searchreferfriends");
  }
}
