package com.action;

import com.bean.User;
import com.bean.lov.MembershipType;
import com.bo.BOFactory;
import com.bo.LovBO;
import com.common.Common;
import com.common.ValidationException;
import com.form.MembershipTypeForm;
import com.resources.Constant;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class MembershipTypeAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(MembershipTypeAction.class);
  
  public ActionForward membershipTypesList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside membershipTypesList method");
    
    return mapping.findForward("membershipTypesList");
  }
  
  public ActionForward createMembershipType(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside membershipTypesList method");
    MembershipTypeForm membershipTypeForm = (MembershipTypeForm)form;
    
    return mapping.findForward("createMembershipType");
  }
  
  public ActionForward saveMembershipType(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveMembershipType method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    MembershipTypeForm membershipTypeForm = (MembershipTypeForm)form;
    MembershipType membershipType = new MembershipType();
    membershipTypeForm.toValue(membershipType, request);
    membershipType.setStatus("A");
    membershipType.setSuper_user_key(user1.getSuper_user_key());
    membershipType = BOFactory.getLovBO().saveMembershipTypeDetails(membershipType);
    
    membershipTypeForm.fromValue(membershipType, request);
    request.setAttribute("membershipTypeSaved", "yes");
    return mapping.findForward("createMembershipType");
  }
  
  public ActionForward editMembershipType(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside editMembershipType method");
    MembershipTypeForm membershipTypeForm = (MembershipTypeForm)form;
    
    String membershipTypeId = request.getParameter("membershipTypeId");
    logger.info("jobCategoryId >> " + membershipTypeId);
    MembershipType membershipType = BOFactory.getLovBO().getMembershipTypeDetails(new Long(membershipTypeId).longValue());
    membershipTypeForm.fromValue(membershipType, request);
    
    return mapping.findForward("createMembershipType");
  }
  
  public ActionForward updateMembershipType(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updateMembershipType method");
    MembershipTypeForm membershipTypeForm = (MembershipTypeForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    String membershipTypeId = request.getParameter("membershipTypeId");
    logger.info("jobCategoryId >> " + membershipTypeId);
    MembershipType membershipType = BOFactory.getLovBO().getMembershipTypeDetails(new Long(membershipTypeId).longValue());
    
    membershipTypeForm.toValue(membershipType, request);
    membershipType.setSuper_user_key(user1.getSuper_user_key());
    membershipType = BOFactory.getLovBO().updateMembershipTypeDetails(membershipType);
    
    membershipTypeForm.fromValue(membershipType, request);
    request.setAttribute("membershipTypeupdated", "yes");
    return mapping.findForward("createMembershipType");
  }
  
  public ActionForward deleteMembershipType(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteMembershipType method");
    MembershipTypeForm membershipTypeForm = (MembershipTypeForm)form;
    User user = (User)request.getSession().getAttribute("user_data");
    
    String membershipTypeId = request.getParameter("membershipTypeId");
    try
    {
      BOFactory.getLovBO().deleteMemberShipType(new Long(membershipTypeId).longValue());
      
      request.setAttribute("membershipTypedeleted", "yes");
      return mapping.findForward("createMembershipType");
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
