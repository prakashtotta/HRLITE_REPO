package com.action;

import com.bean.EvaluationTemplate;
import com.bean.GroupCharacteristic;
import com.bean.User;
import com.dao.LovOpsDAO;
import com.form.EvaluationTemplateForm;
import com.util.StringUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class EvaluationTemplateAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(EvaluationTemplateAction.class);
  
  public ActionForward evtmpllist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside evtmpllist method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    return mapping.findForward("evtmpllist");
  }
  
  public ActionForward createevtmpl(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside createevtmpl method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    return mapping.findForward("createevtmpl");
  }
  
  public ActionForward saveEvTmpl(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveEvTmpl method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String charids = request.getParameter("charids");
    EvaluationTemplate evtmpl = new EvaluationTemplate();
    EvaluationTemplateForm intform = (EvaluationTemplateForm)form;
    logger.info("Group Characterictics ids" + charids);
    if (!StringUtils.isNullOrEmpty(charids))
    {
      charids = charids.substring(0, charids.length() - 1);
      logger.info("Group Characterictics ids ids next" + charids);
      String[] ids = StringUtils.tokenize(charids, ",");
      List grpcharList = new ArrayList();
      if (ids != null) {
        for (int i = 0; i < ids.length; i++)
        {
          GroupCharacteristic ch = new GroupCharacteristic();
          ch.setGroupCharId(new Long(ids[i]).longValue());
          grpcharList.add(ch);
        }
      }
      evtmpl.setCharGroupList(grpcharList);
    }
    evtmpl.setEvTmplName(intform.getEvTmplName());
    evtmpl.setEvTmplDesc(intform.getEvTmplDesc());
    evtmpl.setCreatedBy(user1.getUserName());
    evtmpl.setCreatedDate(new Date());
    evtmpl.setStatus("A");
    
    LovOpsDAO.saveEvaluationTmpl(evtmpl);
    evtmpl = LovOpsDAO.getEvaluationCriteria(String.valueOf(evtmpl.getEvtmplId()));
    intform.setGroupcharList(evtmpl.getCharGroupList());
    
    request.setAttribute("templatesaved", "yes");
    return mapping.findForward("createevtmpl");
  }
  
  public ActionForward selectgroupchar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside selectgroupchar method");
    
    return mapping.findForward("selectgroupchar");
  }
  
  public ActionForward searchgroupchars(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchgroupchars method");
    String criteria = request.getParameter("criteria");
    
    List groupcharList = LovOpsDAO.getGroupCharacteristicsByCritera(criteria);
    EvaluationTemplateForm intform = (EvaluationTemplateForm)form;
    intform.setGroupcharList(groupcharList);
    return mapping.findForward("searchgroupchars");
  }
  
  public ActionForward evtmplpreview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchgroupchars method");
    String evtmplid = request.getParameter("evtmplid");
    logger.info("evtmplid" + evtmplid);
    EvaluationTemplate evtmpl = LovOpsDAO.getEvaluationCriteria(evtmplid);
    EvaluationTemplateForm intform = (EvaluationTemplateForm)form;
    
    intform.setEvtmplId(evtmpl.getEvtmplId());
    intform.setEvTmplName(evtmpl.getEvTmplName());
    intform.setEvTmplDesc(evtmpl.getEvTmplDesc());
    intform.setGroupcharList(evtmpl.getCharGroupList());
    
    return mapping.findForward("evtmplpreview");
  }
  
  public ActionForward editEvTemplate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside editEvTemplate method");
    String evtmplid = request.getParameter("evtmplid");
    logger.info("evtmplid" + evtmplid);
    EvaluationTemplate evtmpl = LovOpsDAO.getEvaluationCriteria(evtmplid);
    EvaluationTemplateForm intform = (EvaluationTemplateForm)form;
    
    intform.setEvtmplId(evtmpl.getEvtmplId());
    intform.setEvTmplName(evtmpl.getEvTmplName());
    intform.setEvTmplDesc(evtmpl.getEvTmplDesc());
    intform.setGroupcharList(evtmpl.getCharGroupList());
    request.setAttribute("templatesaved", "no");
    return mapping.findForward("createevtmpl");
  }
  
  public ActionForward updateEvTemplate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updateEvTemplate method");
    String evtmplid = request.getParameter("evtmplid");
    logger.info("evtmplid" + evtmplid);
    String charids = request.getParameter("charids");
    User user1 = (User)request.getSession().getAttribute("user_data");
    EvaluationTemplateForm intform = (EvaluationTemplateForm)form;
    EvaluationTemplate evtmpl = LovOpsDAO.getEvaluationCriteria(evtmplid);
    logger.info("Group Characterictics ids" + charids);
    if (!StringUtils.isNullOrEmpty(charids))
    {
      charids = charids.substring(0, charids.length() - 1);
      logger.info("Group Characterictics ids ids next" + charids);
      String[] ids = StringUtils.tokenize(charids, ",");
      List grpcharList = new ArrayList();
      if (ids != null) {
        for (int i = 0; i < ids.length; i++)
        {
          GroupCharacteristic ch = new GroupCharacteristic();
          ch.setGroupCharId(new Long(ids[i]).longValue());
          grpcharList.add(ch);
        }
      }
      evtmpl.setCharGroupList(grpcharList);
    }
    evtmpl.setEvTmplName(intform.getEvTmplName());
    evtmpl.setEvTmplDesc(intform.getEvTmplDesc());
    evtmpl.setUpdatedBy(user1.getUserName());
    evtmpl.setUpdatedDate(new Date());
    
    LovOpsDAO.updateEvTemplate(evtmpl);
    
    intform.setEvtmplId(evtmpl.getEvtmplId());
    intform.setEvTmplName(evtmpl.getEvTmplName());
    intform.setEvTmplDesc(evtmpl.getEvTmplDesc());
    evtmpl = LovOpsDAO.getEvaluationCriteria(String.valueOf(evtmpl.getEvtmplId()));
    intform.setGroupcharList(evtmpl.getCharGroupList());
    request.setAttribute("UpdateTemplate", "yes");
    return mapping.findForward("createevtmpl");
  }
  
  public ActionForward deleteEvTemplate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteEvTemplate method");
    String evtmplid = request.getParameter("evtmplid");
    logger.info("evtmplid" + evtmplid);
    EvaluationTemplate evtmpl = LovOpsDAO.getEvaluationCriteria(evtmplid);
    evtmpl.setStatus("D");
    LovOpsDAO.updateEvTemplate(evtmpl);
    request.setAttribute("templatedeleted", "yes");
    return mapping.findForward("createevtmpl");
  }
  
  public ActionForward selectevtemplates(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside selectevtemplates method");
    
    return mapping.findForward("selectevtemplates");
  }
  
  public ActionForward searchevaluationtemplates(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchevaluationtemplates method");
    String criteria = request.getParameter("criteria");
    
    List evtmpllist = LovOpsDAO.getEvaluationTemplatesByCritera(criteria);
    EvaluationTemplateForm evform = (EvaluationTemplateForm)form;
    evform.setEvaluationTmplList(evtmpllist);
    return mapping.findForward("searchevaluationtemplates");
  }
}
