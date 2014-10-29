package com.action;

import com.bean.RefferalEmployee;
import com.dao.RefferalDAO;
import com.form.RefferalForm;
import com.util.EmailValidateUtils;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AgencyAction
  extends CommonNoLoginAction
{
  protected static final Logger logger = Logger.getLogger(AgencyAction.class);
  
  public ActionForward reg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside reg method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    return mapping.findForward("regscreen");
  }
  
  public ActionForward home(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside home method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    return mapping.findForward("success");
  }
  
  public ActionForward regsubmit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside regsubmit method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    RefferalForm refForm = (RefferalForm)form;
    RefferalEmployee refemp = new RefferalEmployee();
    if (!EmailValidateUtils.isEmailIdBelongsToDomainsList(refForm.getEmployeeemail()))
    {
      request.setAttribute("emailidnotcorrect", "yes");
      return mapping.findForward("regscreen");
    }
    refForm.toValue(refemp, request);
    refemp.setStatus("I");
    refemp.setVarificationcode(UUID.randomUUID().toString());
    RefferalDAO.saveeRefferalEmployee(refemp);
    
    refForm.fromValue(refemp, request);
    request.setAttribute("employeerefsaved", "yes");
    return mapping.findForward("regscreen");
  }
  
  public ActionForward forgotpassword(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside forgotpassword method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    RefferalForm refForm = (RefferalForm)form;
    
    RefferalEmployee refemp = RefferalDAO.getRefferalEmployeeByEmail(refForm.getEmployeeemail());
    if (refemp == null)
    {
      request.setAttribute("wrongemailid", "yes");
      return mapping.findForward("forgotpasswordscr");
    }
    request.setAttribute("forgotpasswordsent", "yes");
    

    refForm.fromValue(refemp, request);
    return mapping.findForward("forgotpasswordscr");
  }
}
