package com.action;

import com.bean.ApplicantUser;
import com.bean.INineFormBean;
import com.bean.JobApplicant;
import com.bo.ApplicantBO;
import com.bo.BOFactory;
import com.form.INineForm;
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

public class INineFormAction
  extends CommonNoLoginAction
{
  protected static final Logger logger = Logger.getLogger(EditApplicantAction.class);
  
  public ActionForward inineformscr(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    logger.info("Inside inineformscr method");
    
    String applicantId = request.getParameter("applicantId");
    
    request.setAttribute("applicantId", applicantId);
    INineForm iNineForm = (INineForm)form;
    INineFormBean iNineFormBean = BOFactory.getApplicantBO().getINineFormDetails(applicantId);
    if (iNineFormBean == null)
    {
      request.setAttribute("showSaveBtn", "yes");
    }
    else
    {
      request.setAttribute("showSaveBtn", "no");
      iNineForm.fromValue(iNineFormBean, request);
    }
    return mapping.findForward("inineformscr");
  }
  
  public ActionForward inineformSubmit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    logger.info("Inside inineformSubmit method");
    String employeetype = request.getParameter("employeetype");
    String employeeattest = request.getParameter("employeeattest");
    String applicantId = request.getParameter("applicantId");
    
    INineFormBean iNineFormBean = new INineFormBean();
    INineForm iNineForm = (INineForm)form;
    ApplicantUser user1 = (ApplicantUser)request.getSession().getAttribute("applicant_user_data");
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetails(applicantId);
    
    iNineForm.toValue(iNineFormBean, request);
    
    iNineFormBean.setApplicant(applicant);
    if ((!StringUtils.isNullOrEmpty(employeetype)) && (employeetype.equals("true"))) {
      iNineFormBean.setEmployeetype("Y");
    } else {
      iNineFormBean.setEmployeetype("N");
    }
    iNineFormBean.setEmployeeattest(employeeattest);
    iNineFormBean.setCreatedBy(user1.getApplicant().getFullName());
    iNineFormBean.setCreatedDate(new Date());
    BOFactory.getApplicantBO().saveINineFormData(iNineFormBean);
    iNineForm.fromValue(iNineFormBean, request);
    
    request.setAttribute("showSaveBtn", "no");
    request.setAttribute("iNineDatasave", "yes");
    return mapping.findForward("inineformscr");
  }
  
  public ActionForward updateinineform(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    INineForm iNineForm = (INineForm)form;
    logger.info("Inside updateinineform method");
    String employeetype = request.getParameter("employeetype");
    String employeeattest = request.getParameter("employeeattest");
    
    String iNineFormId = request.getParameter("iNineFormId");
    

    ApplicantUser user1 = (ApplicantUser)request.getSession().getAttribute("applicant_user_data");
    
    INineFormBean iNineFormBean = BOFactory.getApplicantBO().getINineFormDetailsById(iNineFormId);
    
    logger.info("iNineFormBean : " + iNineFormBean);
    iNineForm.toValue(iNineFormBean, request);
    if ((!StringUtils.isNullOrEmpty(employeetype)) && (employeetype.equals("true"))) {
      iNineFormBean.setEmployeetype("Y");
    } else {
      iNineFormBean.setEmployeetype("N");
    }
    iNineFormBean.setEmployeeattest(employeeattest);
    iNineFormBean.setUpdatedBy(user1.getApplicant().getFullName());
    iNineFormBean.setUpdatedDate(new Date());
    logger.info(".... 1");
    BOFactory.getApplicantBO().updateINineFormData(iNineFormBean);
    
    iNineForm.fromValue(iNineFormBean, request);
    
    request.setAttribute("showSaveBtn", "no");
    request.setAttribute("iNineDatasave", "yes");
    return mapping.findForward("inineformscr");
  }
}
