package com.action;

import com.common.Common;
import com.security.CSRFTokenGenerator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class CommonNoLoginAction
  extends DispatchAction
{
  protected static final Logger logger = Logger.getLogger(CommonNoLoginAction.class);
  
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    if ((request.getMethod() != null) && (request.getMethod().equalsIgnoreCase("POST")) && 
      (!CSRFTokenGenerator.isValidateToken(request)))
    {
      request.setAttribute(Common.ERROR_MSG, "Not a valid request");
      return mapping.findForward("exception");
    }
    try
    {
      return super.execute(mapping, form, request, response);
    }
    catch (Exception e) {}
    return mapping.findForward("exception");
  }
}
