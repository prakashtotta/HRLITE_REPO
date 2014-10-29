package com.action;

import com.bean.User;
import com.bean.onboard.OnBoardingTaskDefinitions;
import com.bean.onboard.OnBoardingTemplate;
import com.bo.BOFactory;
import com.bo.LovBO;
import com.common.Common;
import com.common.ValidationException;
import com.dao.TaskDAO;
import com.form.OnBoardingTemplateForm;
import com.resources.Constant;
import com.util.StringUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class OnBoardingTemplateAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(OnBoardingTaskDefiAction.class);
  
  public ActionForward OnBoardMainTemplatelistPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    request.setAttribute("onboardingHome", "template");
    return mapping.findForward("OnBoardingMainTemplatePage");
  }
  
  public ActionForward OnBoardingTemplatelist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    OnBoardingTemplateForm onboardingtemplateForm = (OnBoardingTemplateForm)form;
    request.setAttribute("searchpagedisplay", "no");
    return mapping.findForward("OnBoardingTemplatelist");
  }
  
  public ActionForward createOnBoardingTemplate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String readPreview = request.getParameter("readPreview");
    OnBoardingTemplateForm onboardingtemplateForm = (OnBoardingTemplateForm)form;
    onboardingtemplateForm.setListsizeval(1);
    onboardingtemplateForm.setReadPreview(readPreview);
    return mapping.findForward("createOnBoardingTemplate");
  }
  
  public ActionForward saveOnBoardTemplate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveOnBoardTemplate method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    OnBoardingTemplateForm onboardingtemplateForm = (OnBoardingTemplateForm)form;
    OnBoardingTemplate onboardingtemplate = new OnBoardingTemplate();
    toValue(onboardingtemplate, onboardingtemplateForm);
    onboardingtemplate.setCreatedBy(user1.getUserName());
    onboardingtemplate.setCreatedDate(new Date());
    Set onboardtaskdefSet = new HashSet();
    List onboardtaskidList = new ArrayList();
    for (int i = 1; i < 50; i++)
    {
      String taskdefid = request.getParameter("taskdefid_" + i);
      if (!StringUtils.isNullOrEmpty(taskdefid))
      {
        OnBoardingTaskDefinitions ch = new OnBoardingTaskDefinitions();
        ch.setTaskdefid(new Long(taskdefid).longValue());
        ch = TaskDAO.getOnBoardingTaskDefinitionsDetails(String.valueOf(ch.getTaskdefid()));
        onboardtaskdefSet.add(ch);
        onboardtaskidList.add(Long.valueOf(ch.getTaskdefid()));
      }
    }
    onboardingtemplate.setTaskdefinitions(onboardtaskdefSet);
    onboardingtemplate.setSuper_user_key(user1.getSuper_user_key());
    TaskDAO.SaveOnBoardingTemplate(onboardingtemplate);
    onboardingtemplateForm.setTemplateid(onboardingtemplate.getTemplateid());
    onboardingtemplateForm.setTaskdefinitions(onboardingtemplate.getTaskdefinitions());
    onboardingtemplateForm.setListsizeval(onboardingtemplate.getTaskdefinitions().size() + 1);
    onboardingtemplateForm.setTaskdefinationStringvalue(onboardingtemplateForm.getTaskdefinationStringvalue());
    onboardingtemplateForm.setOnboardtaskidListVal(onboardtaskidList);
    
    String readpreview = request.getParameter("readPreview");
    onboardingtemplateForm.setReadPreview(readpreview);
    request.setAttribute("saveOnBoardtemplate", "yes");
    return mapping.findForward("createOnBoardingTemplate");
  }
  
  public void setTaskdefList(long templateid, List taskdeflist, HttpServletRequest request)
  {
    OnBoardingTemplate onboardingtemplate = new OnBoardingTemplate();
    Set onboardtaskdefSet = new HashSet();
    for (int i = 1; i < 50; i++)
    {
      String taskdefid = request.getParameter("taskdefid_" + i);
      logger.info("attributes_" + taskdefid);
      OnBoardingTaskDefinitions ch = new OnBoardingTaskDefinitions();
      onboardtaskdefSet.add(taskdefid);
    }
    onboardingtemplate.setTaskdefinitions(onboardtaskdefSet);
  }
  
  public ActionForward updateOnBoardingTemplate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updateOnBoardingTemplate method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user1 = (User)request.getSession().getAttribute("user_data");
    OnBoardingTemplateForm onboardingtemplateForm = (OnBoardingTemplateForm)form;
    String id = request.getParameter("id");
    
    OnBoardingTemplate onboardingtemplate = TaskDAO.getOnBoardingTemplateDetails(id);
    toValue(onboardingtemplate, onboardingtemplateForm);
    
    onboardingtemplate.setUpdatedBy(user1.getUserName());
    onboardingtemplate.setUpdatedDate(new Date());
    Set onboardtaskdefSet = new HashSet();
    List onboardtaskidList = new ArrayList();
    for (int i = 1; i < 50; i++)
    {
      String taskdefid = request.getParameter("taskdefid_" + i);
      if (!StringUtils.isNullOrEmpty(taskdefid))
      {
        OnBoardingTaskDefinitions ch = new OnBoardingTaskDefinitions();
        ch.setTaskdefid(new Long(taskdefid).longValue());
        ch = TaskDAO.getOnBoardingTaskDefinitionsDetails(String.valueOf(ch.getTaskdefid()));
        onboardtaskdefSet.add(ch);
        onboardtaskidList.add(Long.valueOf(ch.getTaskdefid()));
      }
    }
    onboardingtemplate.setTaskdefinitions(onboardtaskdefSet);
    onboardingtemplate = TaskDAO.updateOnBoardingTemplate(onboardingtemplate);
    onboardingtemplateForm.setTemplateid(onboardingtemplate.getTemplateid());
    onboardingtemplateForm.setTaskdefinitions(onboardingtemplate.getTaskdefinitions());
    onboardingtemplateForm.setListsizeval(onboardingtemplate.getTaskdefinitions().size() + 1);
    String readpreview = request.getParameter("readPreview");
    onboardingtemplateForm.setReadPreview(readpreview);
    onboardingtemplateForm.setTaskdefinationStringvalue(onboardingtemplateForm.getTaskdefinationStringvalue());
    onboardingtemplateForm.setOnboardtaskidListVal(onboardtaskidList);
    request.setAttribute("updateOnBoardtemplate", "yes");
    return mapping.findForward("createOnBoardingTemplate");
  }
  
  public ActionForward setOnboardtaskid(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside setOnboardtaskid method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String id = request.getParameter("id");
    OnBoardingTemplateForm onboardingtemplateForm = (OnBoardingTemplateForm)form;
    

    onboardingtemplateForm.setTaskdefid(Long.valueOf(id).longValue());
    
    return mapping.findForward("");
  }
  
  public ActionForward OnBoardingTemplateDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside OnBoardingTemplateDetails method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String templateid = request.getParameter("id");
    OnBoardingTemplateForm onboardingtemplateForm = (OnBoardingTemplateForm)form;
    OnBoardingTemplate onboardingtemplate = TaskDAO.getOnBoardingTtemplate(templateid);
    
    toForm(onboardingtemplateForm, onboardingtemplate);
    
    String redpreview = request.getParameter("readPreview");
    onboardingtemplateForm.setReadPreview(redpreview);
    
    onboardingtemplateForm.setTaskdefinitions(onboardingtemplate.getTaskdefinitions());
    onboardingtemplateForm.setListsizeval(onboardingtemplate.getTaskdefinitions().size() + 1);
    List onboardtaskidList = new ArrayList();
    Set set1 = onboardingtemplate.getTaskdefinitions();
    Iterator itr = set1.iterator();
    while (itr.hasNext())
    {
      OnBoardingTaskDefinitions onbtask = (OnBoardingTaskDefinitions)itr.next();
      onboardtaskidList.add(Long.valueOf(onbtask.getTaskdefid()));
    }
    onboardingtemplateForm.setOnboardtaskidListVal(onboardtaskidList);
    logger.info("onboardingtemplateForm.setOnboardtaskidListVal(onboardtaskidList);" + onboardtaskidList);
    return mapping.findForward("createOnBoardingTemplate");
  }
  
  public ActionForward addIdlistforTaskdefination(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside addIdlistforTaskdefination method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String listtaskidval = request.getParameter("aaa");
    OnBoardingTemplateForm onboardingtemplateForm = (OnBoardingTemplateForm)form;
    
    List onboardtaskidList = new ArrayList();
    if (listtaskidval.length() > 0)
    {
      String[] column = StringUtils.tokenize(listtaskidval, "[,,,]");
      for (int i = 0; i < column.length; i++)
      {
        onboardtaskidList.add(column[i]);
        logger.info("listvallistval" + column[i]);
      }
    }
    logger.info("listvallistval55555" + onboardtaskidList);
    onboardingtemplateForm.setOnboardtaskidListVal(onboardtaskidList);
    logger.info("onboardingtemplateForm.setOnboardtaskidListVal(onboardtaskidList);" + onboardtaskidList);
    return mapping.findForward("");
  }
  
  public ActionForward deleteOnBoardingTemplate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteOnBoardingTemplate method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user = (User)request.getSession().getAttribute("user_data");
    OnBoardingTemplateForm onboardingtemplateForm = (OnBoardingTemplateForm)form;
    String templateid = request.getParameter("id");
    try
    {
      BOFactory.getLovBO().deleteOnBoardingTemplate(new Long(templateid).longValue());
      

      request.setAttribute("deleteOnBoardtemplate", "yes");
      return mapping.findForward("createOnBoardingTemplate");
    }
    catch (ValidationException e)
    {
      String msg = Constant.getResourceStringValue("errorcode." + e.getErrorCode(), user.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
      return mapping.findForward("exception");
    }
    catch (Exception e)
    {
      String msg = Constant.getResourceStringValue("server.error", user.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
    }
    return mapping.findForward("exception");
  }
  
  public ActionForward selectOnboardTaskDefination(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside selectOnboardTaskDefination method");
    String countnumberforselector = request.getParameter("numcount");
    logger.info("countnumberforselector" + countnumberforselector);
    OnBoardingTemplateForm onboardingtemplateForm = (OnBoardingTemplateForm)form;
    onboardingtemplateForm.setCountOnboardingTask("onboardtaskdefination_" + countnumberforselector);
    onboardingtemplateForm.setCounttaskdefid("taskdefid_" + countnumberforselector);
    onboardingtemplateForm.setCounttaskName("taskname_" + countnumberforselector);
    List onboardtaskidList = new ArrayList();
    if (countnumberforselector != null) {
      request.setAttribute("boxnumber", countnumberforselector);
    }
    return mapping.findForward("selectonboardTaskDefination");
  }
  
  public ActionForward savetoOnboardTemplate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside savetoOnboardTemplate method");
    OnBoardingTemplateForm onboardingtemplateForm = (OnBoardingTemplateForm)form;
    
    return mapping.findForward("selectonboardTaskDefination");
  }
  
  public ActionForward searchOnboaedtemplateDefination(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchOnboaedtemplateDefination method");
    String boxnumber = request.getParameter("boxnumber");
    String start = request.getParameter("start");
    String range = request.getParameter("range");
    String results = request.getParameter("results");
    
    OnBoardingTemplateForm onboardingtemplateForm = (OnBoardingTemplateForm)form;
    int start1 = StringUtils.stringToInt(start, 0);
    int range1 = StringUtils.stringToInt(range, 15);
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user = (User)request.getSession().getAttribute("user_data");
    
    int totaluser = 0;
    if (StringUtils.isNullOrEmpty(results)) {
      totaluser = TaskDAO.getOnboardTemplateDefinationByCriteraCount(user.getSuper_user_key(), onboardingtemplateForm.getCriteria(), onboardingtemplateForm.getPrimaryOwnerId(), onboardingtemplateForm.getPrimaryOwnerName());
    } else {
      totaluser = new Integer(results).intValue();
    }
    logger.info("results" + totaluser);
    List taskdefList = TaskDAO.getOnboardTemplateDefinationByCritera(user.getSuper_user_key(), onboardingtemplateForm.getCriteria(), onboardingtemplateForm.getPrimaryOwnerId(), onboardingtemplateForm.getPrimaryOwnerName(), start1, range1);
    logger.info("*** results ***" + taskdefList);
    
    onboardingtemplateForm.setOnboardtaskList(taskdefList);
    onboardingtemplateForm.setStart(String.valueOf(start1));
    onboardingtemplateForm.setRange(String.valueOf(range1));
    onboardingtemplateForm.setResults(String.valueOf(totaluser));
    onboardingtemplateForm.setCriteria(onboardingtemplateForm.getCriteria());
    if (boxnumber != null) {
      request.setAttribute("boxnumber", boxnumber);
    }
    return mapping.findForward("selectonboardTaskDefination");
  }
  
  public ActionForward searchOnboardTemplateListpage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchOnboardTemplateListpage method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    OnBoardingTemplateForm onboardingtemplateForm = (OnBoardingTemplateForm)form;
    onboardingtemplateForm.setTemplateName(onboardingtemplateForm.getTemplateName());
    onboardingtemplateForm.setPrimaryOwnerId(onboardingtemplateForm.getPrimaryOwnerId());
    request.setAttribute("searchpagedisplay", "yes");
    request.setAttribute("onboardingHome", "template");
    return mapping.findForward("OnBoardingTemplatelist");
  }
  
  public void toDelete(OnBoardingTemplate onboardingtemplate, OnBoardingTemplateForm onboardingtemplateForm)
  {
    onboardingtemplate.setStatus("D");
  }
  
  public void toForm(OnBoardingTemplateForm onboardingtemplateForm, OnBoardingTemplate onboardingtemplate)
  {
    onboardingtemplateForm.setTemplateid(onboardingtemplate.getTemplateid());
    onboardingtemplateForm.setTemplateName(onboardingtemplate.getTemplateName());
    onboardingtemplateForm.setTemplateDesc(onboardingtemplate.getTemplateDesc());
    onboardingtemplateForm.setTaskdefinitions(onboardingtemplate.getTaskdefinitions());
  }
  
  public void toValue(OnBoardingTemplate onboardingtemplate, OnBoardingTemplateForm onboardingtemplateForm)
  {
    onboardingtemplate.setTemplateName(onboardingtemplateForm.getTemplateName());
    onboardingtemplate.setTemplateDesc(onboardingtemplateForm.getTemplateDesc());
    
    onboardingtemplate.setStatus("A");
  }
}
