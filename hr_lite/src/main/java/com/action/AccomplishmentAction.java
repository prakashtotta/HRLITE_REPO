package com.action;

import com.bean.Accomplishments;
import com.bean.User;
import com.dao.LovOpsDAO;
import com.form.AccomplishmentForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AccomplishmentAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(JobCodeAction.class);
  
  public ActionForward createAccomplishment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    AccomplishmentForm AccomplishmentForm = (AccomplishmentForm)form;
    
    return mapping.findForward("createAccomplishment");
  }
  
  public ActionForward editAccomplishment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside editAccomplishment method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    String id = request.getParameter("id");
    AccomplishmentForm accomplishmentForm = (AccomplishmentForm)form;
    
    Accomplishments Accomplishments = LovOpsDAO.getAccomplishmentDetails(id);
    toForm(accomplishmentForm, Accomplishments);
    


    request.setAttribute("updatebutton", "yes");
    return mapping.findForward("createAccomplishment");
  }
  
  public ActionForward saveAccomplishment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveAccomplishment method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user = (User)request.getSession().getAttribute("user_data");
    AccomplishmentForm accomplishmentform = (AccomplishmentForm)form;
    Accomplishments Accomplishments = new Accomplishments();
    toValue(Accomplishments, accomplishmentform);
    Accomplishments.setSuper_user_key(user.getSuper_user_key());
    LovOpsDAO.SaveAccomplishments(Accomplishments);
    String readpreview = request.getParameter("readPreview");
    accomplishmentform.setReadPreview(readpreview);
    request.setAttribute("saveaccomplishment", "yes");
    return mapping.findForward("createAccomplishment");
  }
  
  public ActionForward updateAccomplishment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updateAccomplishment method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    AccomplishmentForm accomplishmentForm = (AccomplishmentForm)form;
    
    String id = request.getParameter("id");
    
    Accomplishments Accomplishments = LovOpsDAO.getAccomplishmentDetails(id);
    toValue(Accomplishments, accomplishmentForm);
    
    Accomplishments = LovOpsDAO.updateAccomplishment(Accomplishments);
    
    String readpreview = request.getParameter("readPreview");
    accomplishmentForm.setReadPreview(readpreview);
    request.setAttribute("updateaccomplishment", "yes");
    return mapping.findForward("createAccomplishment");
  }
  
  public ActionForward deleteAccomplishment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteAccomplishment method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    AccomplishmentForm accomplishmentForm = (AccomplishmentForm)form;
    
    String id = request.getParameter("id");
    Accomplishments Accomplishments = LovOpsDAO.getAccomplishmentDetails(id);
    toDelete(Accomplishments, accomplishmentForm);
    
    Accomplishments = LovOpsDAO.updateAccomplishment(Accomplishments);
    request.setAttribute("deleteaccomplishment", "yes");
    return mapping.findForward("createAccomplishment");
  }
  
  public void toDelete(Accomplishments Accomplishments, AccomplishmentForm AccomplishmentForm)
  {
    Accomplishments.setStatus("D");
  }
  
  public ActionForward AccomplishmentDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside AccomplishmentDetails method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    String accomplishmentId = request.getParameter("id");
    
    AccomplishmentForm accomplishmentform = (AccomplishmentForm)form;
    
    Accomplishments Accomplishments = LovOpsDAO.getAccomplishmentDetails(accomplishmentId);
    
    toForm(accomplishmentform, Accomplishments);
    String redpreview = request.getParameter("readPreview");
    accomplishmentform.setReadPreview(redpreview);
    
    return mapping.findForward("createAccomplishment");
  }
  
  public ActionForward Accomplishmentlist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    AccomplishmentForm accomplishmentform = (AccomplishmentForm)form;
    request.setAttribute("competenciesHome", "accomplishmnet");
    return mapping.findForward("accomplishmentlist");
  }
  
  public void toForm(AccomplishmentForm AccomplishmentForm, Accomplishments Accomplishments)
  {
    AccomplishmentForm.setAccId(Accomplishments.getAccId());
    AccomplishmentForm.setAccName(Accomplishments.getAccName());
    AccomplishmentForm.setAccDesc(Accomplishments.getAccDesc());
    AccomplishmentForm.setStatus(Accomplishments.getStatus());
  }
  
  public void toValue(Accomplishments Accomplishments, AccomplishmentForm AccomplishmentForm)
  {
    Accomplishments.setAccName(AccomplishmentForm.getAccName());
    Accomplishments.setAccDesc(AccomplishmentForm.getAccDesc());
    Accomplishments.setStatus("A");
  }
  
  public ActionForward accomplishmentlistsearch(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    AccomplishmentForm accomplishmentform = (AccomplishmentForm)form;
    String accomplishmentName = request.getParameter("accomplishmentName");
    accomplishmentform.setAccName(accomplishmentName);
    request.setAttribute("competenciesHome", "accomplishmnet");
    return mapping.findForward("accomplishmentlist");
  }
}
