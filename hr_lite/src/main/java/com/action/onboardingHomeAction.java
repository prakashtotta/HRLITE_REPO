package com.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class onboardingHomeAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(OnBoardingTaskDefiAction.class);
  
  public ActionForward OnBoardHomeTemplatelistPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    request.setAttribute("onboardingHome", "onboardtaskdefination");
    return mapping.findForward("onboardingHomeList");
  }
}
