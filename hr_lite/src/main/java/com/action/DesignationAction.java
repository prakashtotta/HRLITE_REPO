package com.action;

import com.bean.Designations;
import com.bean.User;
import com.bo.BOFactory;
import com.bo.DesignationBO;
import com.bo.LovBO;
import com.common.Common;
import com.common.ValidationException;
import com.dao.LovOpsDAO;
import com.form.DesignationForm;
import com.resources.Constant;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class DesignationAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(DesignationAction.class);
  
  public ActionForward designationslist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside designationslist method");
    DesignationForm designationForm = (DesignationForm)form;
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    request.setAttribute("searchpagedisplay", "no");
    return mapping.findForward("designationlist");
  }
  
  public ActionForward createDesignation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside createDesignation method");
    DesignationForm designationForm = (DesignationForm)form;
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    return mapping.findForward("createDesignation");
  }
  
  public ActionForward saveDesignation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveDesignation method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionForward forward = new ActionForward();
    DesignationForm designationForm = (DesignationForm)form;
    Designations designation = new Designations();
    boolean isError = false;
    String designationcode = BOFactory.getDesignationBO().isDesignationCodeExist(designationForm.getDesignationCode(), user1.getSuper_user_key());
    if (designationcode != null)
    {
      isError = true;
      ActionErrors errors = new ActionErrors();
      
      errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError("error.designationcode.alreadyexist"));
      saveErrors(request, errors);
    }
    designationForm.toValue(designation, request);
    if (!isError)
    {
      designation.setSuper_user_key(user1.getSuper_user_key());
      designation = LovOpsDAO.saveDesignation(designation);
      request.setAttribute("designationsaved", "yes");
    }
    designationForm.fromValue(designation, request);
    
    return mapping.findForward("createDesignation");
  }
  
  public ActionForward designationdetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveDesignation method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    DesignationForm designationForm = (DesignationForm)form;
    Designations designation = new Designations();
    String designationId = request.getParameter("id");
    logger.info("designationId : " + designationId);
    designation = LovOpsDAO.getDesignationDetails(designationId);
    
    designationForm.fromValue(designation, request);
    request.setAttribute("designationsaved", "no");
    return mapping.findForward("createDesignation");
  }
  
  public ActionForward updateDesignation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updateDesignation method .");
    
    ActionForward forward = new ActionForward();
    DesignationForm designationForm = (DesignationForm)form;
    
    User user1 = (User)request.getSession().getAttribute("user_data");
    String designationId = request.getParameter("id");
    logger.info("designationId : " + designationId);
    Designations designation = LovOpsDAO.getDesignationDetails(designationId);
    boolean isError = false;
    String designationcode = BOFactory.getDesignationBO().isDesignationCodeExist(designationForm.getDesignationCode(), user1.getSuper_user_key());
    if ((designationcode != null) && (!designationcode.equals(designation.getDesignationCode())))
    {
      isError = true;
      ActionErrors errors = new ActionErrors();
      
      errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError("error.designationcode.alreadyexist"));
      saveErrors(request, errors);
    }
    designationForm.toValue(designation, request);
    if (!isError)
    {
      designation = LovOpsDAO.updateDesignation(designation);
      request.setAttribute("designationupdated", "yes");
    }
    designationForm.fromValue(designation, request);
    
    return mapping.findForward("createDesignation");
  }
  
  public ActionForward deleteDesignation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteDesignation method ");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    DesignationForm designationForm = (DesignationForm)form;
    
    User user = (User)request.getSession().getAttribute("user_data");
    String designationId = request.getParameter("id");
    try
    {
      BOFactory.getLovBO().deleteDesignation(new Long(designationId).longValue());
      
      request.setAttribute("designationdeleted", "yes");
      return mapping.findForward("createDesignation");
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
  
  public ActionForward searchDesignationsList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchDesignationsList method ");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    DesignationForm designationForm = (DesignationForm)form;
    Designations designation = new Designations();
    
    request.setAttribute("searchpagedisplay", "yes");
    return mapping.findForward("designationlist");
  }
}
