package com.action;

import com.form.AgencyRedemptionForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AgencyRedemptionAction
  extends CommonAgencyAction
{
  protected static final Logger logger = Logger.getLogger(AgencyRedemptionAction.class);
  
  public ActionForward myredemptionlist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside myredemptionlist method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    return mapping.findForward("myredemptionlist");
  }
  
  public ActionForward searchmyredeptions(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchmyredeptions method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    AgencyRedemptionForm refRedForm = (AgencyRedemptionForm)form;
    refRedForm.setApplicantName(refRedForm.getApplicantName());
    return mapping.findForward("myredemptionlist");
  }
}
