package com.action;

import com.bean.User;
import com.bean.lov.Education;
import com.bo.BOFactory;
import com.bo.LovBO;
import com.form.EducationForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class EducationAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(CharAction.class);
  
  public ActionForward educationlist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside educationlist method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    EducationForm educationForm = (EducationForm)form;
    request.setAttribute("competenciesHome", "education");
    return mapping.findForward("educationlist");
  }
  
  public ActionForward educationlistsearch(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside educationlistsearch method");
    EducationForm educationForm = (EducationForm)form;
    String educationName = request.getParameter("educationName");
    educationForm.setEducationName(educationName);
    
    request.setAttribute("competenciesHome", "education");
    return mapping.findForward("educationlist");
  }
  
  public ActionForward addEducation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside addEducation method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    EducationForm educationForm = (EducationForm)form;
    
    return mapping.findForward("addEducation");
  }
  
  public ActionForward editEducation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside editEducation method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    EducationForm educationForm = (EducationForm)form;
    String educationId = request.getParameter("educationId");
    Education education = BOFactory.getLovBO().getEducationDetails(new Long(educationId).longValue());
    educationForm.fromValue(education, request);
    
    return mapping.findForward("addEducation");
  }
  
  public ActionForward saveEducation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveEducation method");
    
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    EducationForm educationForm = (EducationForm)form;
    Education education = new Education();
    educationForm.toValue(education, request);
    education.setStatus("A");
    education.setSuper_user_key(user1.getSuper_user_key());
    education = BOFactory.getLovBO().saveEducation(education);
    educationForm.fromValue(education, request);
    educationForm.setEducationId(education.getEducationId());
    request.setAttribute("educationsaved", "yes");
    return mapping.findForward("addEducation");
  }
  
  public ActionForward updateEducation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updateEducation method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    EducationForm educationForm = (EducationForm)form;
    String educationId = request.getParameter("educationId");
    Education education = BOFactory.getLovBO().getEducationDetails(new Long(educationId).longValue());
    
    educationForm.toValue(education, request);
    
    education = BOFactory.getLovBO().updateEducation(education);
    
    educationForm.fromValue(education, request);
    educationForm.setEducationId(education.getEducationId());
    request.setAttribute("educationupdated", "yes");
    
    return mapping.findForward("addEducation");
  }
  
  public ActionForward deleteEducation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteEducation method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    EducationForm educationForm = (EducationForm)form;
    String educationId = request.getParameter("educationId");
    Education education = BOFactory.getLovBO().getEducationDetails(new Long(educationId).longValue());
    

    education = BOFactory.getLovBO().deleteEducation(education);
    
    request.setAttribute("educationdeleted", "yes");
    
    return mapping.findForward("addEducation");
  }
}
