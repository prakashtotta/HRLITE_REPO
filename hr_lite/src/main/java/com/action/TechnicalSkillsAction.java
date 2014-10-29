package com.action;

import com.bean.User;
import com.bean.lov.TechnicalSkills;
import com.bo.BOFactory;
import com.bo.LovBO;
import com.form.TechnicalSkillsForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class TechnicalSkillsAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(TechnicalSkillsAction.class);
  
  public ActionForward technicalskillslist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside technicalskillslist method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    TechnicalSkillsForm technicalSkillsForm = (TechnicalSkillsForm)form;
    request.setAttribute("competenciesHome", "technicalskills");
    return mapping.findForward("technicalskillslist");
  }
  
  public ActionForward createTechnicalSkill(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside createTechnicalSkill method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    TechnicalSkillsForm technicalSkillsForm = (TechnicalSkillsForm)form;
    request.setAttribute("competenciesHome", "technicalskills");
    return mapping.findForward("createTechnicalSkill");
  }
  
  public ActionForward editTechnicalSkill(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside editTechnicalSkill method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    TechnicalSkillsForm technicalSkillsForm = (TechnicalSkillsForm)form;
    
    String technialSkillId = request.getParameter("technialSkillId");
    TechnicalSkills technicalSkills = BOFactory.getLovBO().getTechnicalSkillsDetails(new Long(technialSkillId).longValue());
    technicalSkillsForm.fromValue(technicalSkills, request);
    
    return mapping.findForward("createTechnicalSkill");
  }
  
  public ActionForward technicallistlistsearch(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside technicallistlistsearch method");
    TechnicalSkillsForm technicalSkillsForm = (TechnicalSkillsForm)form;
    String technialSkillName = request.getParameter("technialSkillName");
    technicalSkillsForm.setTechnialSkillName(technialSkillName);
    
    request.setAttribute("competenciesHome", "technicalskills");
    return mapping.findForward("technicalskillslist");
  }
  
  public ActionForward saveTechnicalskills(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveTechnicalskills method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    TechnicalSkillsForm technicalSkillsForm = (TechnicalSkillsForm)form;
    TechnicalSkills technicalSkills = new TechnicalSkills();
    technicalSkillsForm.toValue(technicalSkills, request);
    technicalSkills.setStatus("A");
    technicalSkills.setSuper_user_key(user1.getSuper_user_key());
    technicalSkills = BOFactory.getLovBO().saveTechnicalSkillsDetails(technicalSkills);
    technicalSkillsForm.fromValue(technicalSkills, request);
    technicalSkillsForm.setTechnialSkillId(technicalSkills.getTechnialSkillId());
    

    request.setAttribute("technicalskillsaved", "yes");
    return mapping.findForward("createTechnicalSkill");
  }
  
  public ActionForward updateTechnicalskills(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updateTechnicalSkill method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    TechnicalSkillsForm technicalSkillsForm = (TechnicalSkillsForm)form;
    
    String technialSkillId = request.getParameter("technialSkillId");
    TechnicalSkills technicalSkills = BOFactory.getLovBO().getTechnicalSkillsDetails(new Long(technialSkillId).longValue());
    technicalSkillsForm.toValue(technicalSkills, request);
    
    technicalSkills = BOFactory.getLovBO().updateTechnicalSkillsDetails(technicalSkills);
    
    technicalSkillsForm.fromValue(technicalSkills, request);
    technicalSkillsForm.setTechnialSkillId(technicalSkills.getTechnialSkillId());
    request.setAttribute("technicalskillupdated", "yes");
    return mapping.findForward("createTechnicalSkill");
  }
  
  public ActionForward deleteTechnicalskills(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteTechnicalSkill method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    TechnicalSkillsForm technicalSkillsForm = (TechnicalSkillsForm)form;
    
    String technialSkillId = request.getParameter("technialSkillId");
    TechnicalSkills technicalSkills = BOFactory.getLovBO().getTechnicalSkillsDetails(new Long(technialSkillId).longValue());
    

    technicalSkills = BOFactory.getLovBO().deleteTechnicalSkillsDetails(technicalSkills);
    

    request.setAttribute("technicalskilldeleted", "yes");
    return mapping.findForward("createTechnicalSkill");
  }
}
