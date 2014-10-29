package com.action;

import com.form.ReferalHomeForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class RefferalHomeAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(OnBoardingTaskDefiAction.class);
  
  public ActionForward refferalHomeListPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    ReferalHomeForm referalHomeForm = (ReferalHomeForm)form;
    request.setAttribute("refferalHome", "budgetcode");
    return mapping.findForward("refferalHomeListPage");
  }
}
