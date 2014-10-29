package com.action;

import com.bean.User;
import com.bean.WorkShift;
import com.bo.BOFactory;
import com.bo.LovBO;
import com.common.Common;
import com.common.ValidationException;
import com.form.WorkShiftForm;
import com.resources.Constant;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class WorkShiftAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(WorkShiftAction.class);
  
  public ActionForward workshiftlist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside workshiftlist method");
    WorkShiftForm workShiftForm = (WorkShiftForm)form;
    
    return mapping.findForward("workshiftlist");
  }
  
  public ActionForward createWorkShift(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside workshiftlist method");
    WorkShiftForm workShiftForm = (WorkShiftForm)form;
    
    return mapping.findForward("createWorkShift");
  }
  
  public ActionForward editWorkShift(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside workshiftlist method");
    WorkShiftForm workShiftForm = (WorkShiftForm)form;
    String shiftId = request.getParameter("shiftId");
    WorkShift workShift = BOFactory.getLovBO().getWorkShiftDetails(new Long(shiftId).longValue());
    
    workShiftForm.fromValue(workShift, request);
    return mapping.findForward("createWorkShift");
  }
  
  public ActionForward saveWorkShift(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveWorkShift method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    WorkShiftForm workShiftForm = (WorkShiftForm)form;
    WorkShift workShift = new WorkShift();
    
    workShiftForm.toValue(workShift, request);
    workShift.setStatus("A");
    workShift.setSuper_user_key(user1.getSuper_user_key());
    workShift = BOFactory.getLovBO().saveWorkShift(workShift);
    workShiftForm.fromValue(workShift, request);
    workShiftForm.setShiftId(workShift.getShiftId());
    request.setAttribute("workshiftsaved", "yes");
    return mapping.findForward("createWorkShift");
  }
  
  public ActionForward updateWorkShift(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updateWorkShift method");
    WorkShiftForm workShiftForm = (WorkShiftForm)form;
    String shiftId = request.getParameter("shiftId");
    WorkShift workShift = BOFactory.getLovBO().getWorkShiftDetails(new Long(shiftId).longValue());
    workShiftForm.toValue(workShift, request);
    
    workShift = BOFactory.getLovBO().updateWorkShift(workShift);
    
    workShiftForm.fromValue(workShift, request);
    workShiftForm.setShiftId(workShift.getShiftId());
    request.setAttribute("workshiftupdated", "yes");
    return mapping.findForward("createWorkShift");
  }
  
  public ActionForward deleteWorkShift(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteWorkShift method");
    WorkShiftForm workShiftForm = (WorkShiftForm)form;
    User user = (User)request.getSession().getAttribute("user_data");
    String shiftId = request.getParameter("shiftId");
    String fromwhere = request.getParameter("fromwhere");
    try
    {
      BOFactory.getLovBO().deleteWorkShift(new Long(shiftId).longValue());
      

      request.setAttribute("workshiftdeleted", "yes");
      return mapping.findForward("createWorkShift");
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
