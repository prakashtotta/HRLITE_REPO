package com.action;

import com.bean.User;
import com.bean.onboard.OnBoardingTaskAttributes;
import com.bean.onboard.OnBoardingTaskDefinitions;
import com.bo.BOFactory;
import com.bo.LovBO;
import com.common.Common;
import com.common.ValidationException;
import com.dao.LovOpsDAO;
import com.dao.TaskDAO;
import com.form.OnBoardingTaskDefiForm;
import com.resources.Constant;
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

public class OnBoardingTaskDefiAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(OnBoardingTaskDefiAction.class);
  
  public ActionForward saveOnBoardTaskDefi(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveOnBoardTaskDefi method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String primaryownername = request.getParameter("primaryOwnername");
    String primaryOwnerId = request.getParameter("primaryOwnerId");
    String isGroup = request.getParameter("isGroup");
    OnBoardingTaskDefiForm OnBoardingTaskDefiForm = (OnBoardingTaskDefiForm)form;
    OnBoardingTaskDefinitions OnBoardingTaskDefinitions = new OnBoardingTaskDefinitions();
    OnBoardingTaskDefiForm.setPrimaryOwnerId(Long.valueOf(new Long(primaryOwnerId.trim()).longValue()));
    OnBoardingTaskDefiForm.setIsGroup(isGroup.trim());
    toValue(OnBoardingTaskDefinitions, OnBoardingTaskDefiForm);
    
    OnBoardingTaskDefinitions.setCreatedBy(user1.getUserName());
    OnBoardingTaskDefinitions.setCreatedDate(new Date());
    OnBoardingTaskDefinitions.setSuper_user_key(user1.getSuper_user_key());
    TaskDAO.SaveOnBoardingTaskDefi(OnBoardingTaskDefinitions);
    
    Long id = Long.valueOf(OnBoardingTaskDefinitions.getTaskdefid());
    List attributelist = new ArrayList();
    setAttributeList(OnBoardingTaskDefinitions.getTaskdefid(), attributelist, request);
    logger.info("Competency Size" + attributelist.size());
    logger.info("-----------------------------attributelist--------------------------");
    TaskDAO.saveOnBoardTaskAttributes(attributelist);
    List OnboardingtaskAttributes = TaskDAO.getOnboardTaskDefiAttributeList(String.valueOf(id));
    OnBoardingTaskDefiForm.setAttributeList(OnboardingtaskAttributes);
    OnBoardingTaskDefiForm.setTaskdefid(OnBoardingTaskDefinitions.getTaskdefid());
    OnBoardingTaskDefiForm.setPrimaryOwnername(primaryownername);
    OnBoardingTaskDefiForm.setAppliedcri(OnBoardingTaskDefinitions.getEventType());
    
    String readpreview = request.getParameter("readPreview");
    OnBoardingTaskDefiForm.setReadPreview(readpreview);
    request.setAttribute("saveOnBoardtaskDefination", "yes");
    return mapping.findForward("createOnBoardingTaskDefi");
  }
  
  public void setAttributeList(long taskdefid, List AttributeList, HttpServletRequest request)
  {
    for (int i = 1; i < 50; i++)
    {
      String attributename = request.getParameter("attributes_" + i);
      logger.info("attributes_" + attributename);
      String Attributeid = request.getParameter("attribteid1" + i);
      String Ismandatory = request.getParameter("mandatory_" + i);
      logger.info("Ismandatory_" + Ismandatory);
      
      OnBoardingTaskAttributes attributes = new OnBoardingTaskAttributes();
      OnBoardingTaskDefinitions taskdef = new OnBoardingTaskDefinitions();
      
      taskdef.setTaskdefid(taskdefid);
      attributes.setTaskdefinitionid(taskdefid);
      attributes.setAttribute(attributename);
      if ((!StringUtils.isNullOrEmpty(Ismandatory)) && (Ismandatory.equals("on"))) {
        attributes.setIsMandatory("Y");
      } else {
        attributes.setIsMandatory("N");
      }
      attributes.setTaskdefinition(taskdef);
      if (!StringUtils.isNullOrEmpty(attributename)) {
        AttributeList.add(attributes);
      }
    }
  }
  
  public ActionForward createOnBoardingTaskDefi(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    OnBoardingTaskDefiForm OnBoardingTaskDefiForm = (OnBoardingTaskDefiForm)form;
    return mapping.findForward("createOnBoardingTaskDefi");
  }
  
  public ActionForward OnBoardingTaskDefiDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside OnBoardingTaskDefiDetails method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    String taskdefiId = request.getParameter("id");
    OnBoardingTaskDefiForm OnBoardingTaskDefiForm = (OnBoardingTaskDefiForm)form;
    
    OnBoardingTaskDefinitions OnBoardingTaskDefinitions = TaskDAO.getOnBoardingTaskDefinitionsDetails(taskdefiId);
    List OnboardingtaskAttributes = TaskDAO.getOnboardTaskDefiAttributeList(taskdefiId);
    OnBoardingTaskDefiForm.setAttributeList(OnboardingtaskAttributes);
    toForm(OnBoardingTaskDefiForm, OnBoardingTaskDefinitions);
    OnBoardingTaskDefiForm.setAppliedcri(OnBoardingTaskDefinitions.getEventType());
    
    String redpreview = request.getParameter("readPreview");
    OnBoardingTaskDefiForm.setReadPreview(redpreview);
    
    return mapping.findForward("createOnBoardingTaskDefi");
  }
  
  public ActionForward updateOnBoardingTaskDefi(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updateOnBoardingTaskDefi method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user1 = (User)request.getSession().getAttribute("user_data");
    OnBoardingTaskDefiForm OnBoardingTaskDefiForm = (OnBoardingTaskDefiForm)form;
    String id = request.getParameter("id");
    String primaryownerName = request.getParameter("primaryOwnername");
    String primaryOwnerId = request.getParameter("primaryOwnerId");
    String isGroup = request.getParameter("isGroup");
    OnBoardingTaskDefiForm.setPrimaryOwnerId(Long.valueOf(new Long(primaryOwnerId.trim()).longValue()));
    OnBoardingTaskDefiForm.setIsGroup(isGroup.trim());
    
    OnBoardingTaskDefinitions OnBoardingTaskDefinitions = TaskDAO.getOnBoardingTaskDefinitionsDetails(id);
    toValue(OnBoardingTaskDefinitions, OnBoardingTaskDefiForm);
    
    OnBoardingTaskDefinitions.setUpdatedBy(user1.getUserName());
    OnBoardingTaskDefinitions.setUpdatedDate(new Date());
    OnBoardingTaskDefinitions = TaskDAO.updateOnBoardingTaskDefinitions(OnBoardingTaskDefinitions);
    
    TaskDAO.deleteTaskDefAttributes(OnBoardingTaskDefinitions);
    List attributelist = new ArrayList();
    setAttributeList(OnBoardingTaskDefinitions.getTaskdefid(), attributelist, request);
    logger.info("Competency Size" + attributelist.size());
    logger.info("-----------------------------attributelist--------------------------");
    TaskDAO.saveOnBoardTaskAttributes(attributelist);
    List OnboardingtaskAttributes = TaskDAO.getOnboardTaskDefiAttributeList(id);
    OnBoardingTaskDefiForm.setAttributeList(OnboardingtaskAttributes);
    OnBoardingTaskDefiForm.setTaskdefid(OnBoardingTaskDefinitions.getTaskdefid());
    OnBoardingTaskDefiForm.setPrimaryOwnername(primaryownerName);
    OnBoardingTaskDefiForm.setAppliedcri(OnBoardingTaskDefinitions.getEventType());
    String readpreview = request.getParameter("readPreview");
    OnBoardingTaskDefiForm.setReadPreview(readpreview);
    request.setAttribute("updateOnBoardtaskDefination", "yes");
    return mapping.findForward("createOnBoardingTaskDefi");
  }
  
  public ActionForward saveAttributedetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveAttributedetails method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    OnBoardingTaskDefiForm OnBoardingTaskDefiForm = (OnBoardingTaskDefiForm)form;
    String taskdefiId = request.getParameter("taskdefiId");
    String Attribute_name = request.getParameter("Attribute_name");
    
    OnBoardingTaskAttributes OnBoardingTaskAttributes = new OnBoardingTaskAttributes();
    OnBoardingTaskAttributes.setTaskattid(new Long(taskdefiId).longValue());
    OnBoardingTaskAttributes.setAttribute(Attribute_name);
    LovOpsDAO.saveAttributesDetails(OnBoardingTaskAttributes);
    


    return null;
  }
  
  public ActionForward deleteAttribute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteAttribute method");
    
    OnBoardingTaskDefiForm OnBoardingTaskDefiForm = (OnBoardingTaskDefiForm)form;
    String Attributeid = request.getParameter("Attributeid");
    LovOpsDAO.deleteAttributes(new Long(Attributeid).longValue());
    return null;
  }
  
  public ActionForward OnBoardingTaskDefilist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    OnBoardingTaskDefiForm OnBoardingTaskDefiForm = (OnBoardingTaskDefiForm)form;
    OnBoardingTaskDefiForm.setPrimaryOwnerId(new Long(0L));
    request.setAttribute("onboardingHome", "onboardtaskdefination");
    request.setAttribute("searchpagedisplay", "no");
    return mapping.findForward("OnBoardingTaskDefilist");
  }
  
  public ActionForward getAttributedetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside getAttributedetails method");
    OnBoardingTaskDefiForm OnBoardingTaskDefiForm = (OnBoardingTaskDefiForm)form;
    String attid = request.getParameter("attid");
    
    List orgDetailsList = LovOpsDAO.getAttributeDetailsByTask(new Long(attid).longValue());
    OnBoardingTaskDefiForm.setAttributeList(orgDetailsList);
    
    return mapping.findForward("getAttributedetails");
  }
  
  public ActionForward deleteOnBoardingTaskDefi(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteOnBoardingTaskDefi method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user = (User)request.getSession().getAttribute("user_data");
    OnBoardingTaskDefiForm OnBoardingTaskDefiForm = (OnBoardingTaskDefiForm)form;
    String id = request.getParameter("id");
    try
    {
      BOFactory.getLovBO().deleteOnBoardingTaskDefinition(new Long(id).longValue());
      

      request.setAttribute("deleteOnBoardtaskDefination", "yes");
      return mapping.findForward("createOnBoardingTaskDefi");
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
  
  public ActionForward searchOnboardTaskDefListpage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchOnboardTaskDefListpage method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String primaryOwnerId = request.getParameter("primaryOwnerId");
    OnBoardingTaskDefiForm OnBoardingTaskDefiForm = (OnBoardingTaskDefiForm)form;
    

    OnBoardingTaskDefiForm.setTaskName(OnBoardingTaskDefiForm.getTaskName());
    if ((!StringUtils.isNullOrEmpty(primaryOwnerId)) || (!StringUtils.compare(primaryOwnerId, String.valueOf(0)))) {
      OnBoardingTaskDefiForm.setPrimaryOwnerId(new Long(primaryOwnerId));
    }
    request.setAttribute("searchpagedisplay", "yes");
    request.setAttribute("onboardingHome", "onboardtaskdefination");
    return mapping.findForward("OnBoardingTaskDefilist");
  }
  
  public void toDelete(OnBoardingTaskDefinitions OnBoardingTaskDefinitions, OnBoardingTaskDefiForm OnBoardingTaskDefiForm)
  {
    OnBoardingTaskDefinitions.setStatus("D");
  }
  
  public void toForm(OnBoardingTaskDefiForm OnBoardingTaskDefiForm, OnBoardingTaskDefinitions OnBoardingTaskDefinitions)
  {
    OnBoardingTaskDefiForm.setTaskdefid(OnBoardingTaskDefinitions.getTaskdefid());
    OnBoardingTaskDefiForm.setTaskName(OnBoardingTaskDefinitions.getTaskName());
    OnBoardingTaskDefiForm.setTaskDesc(OnBoardingTaskDefinitions.getTaskDesc());
    OnBoardingTaskDefiForm.setEventType(OnBoardingTaskDefinitions.getEventType());
    OnBoardingTaskDefiForm.setPrimaryOwnername(OnBoardingTaskDefinitions.getPrimaryOwnerName());
    OnBoardingTaskDefiForm.setIsGroup(OnBoardingTaskDefinitions.getIsGroup());
    OnBoardingTaskDefiForm.setNoofdays(OnBoardingTaskDefinitions.getNoofdays());
    OnBoardingTaskDefiForm.setPrimaryOwnerId(Long.valueOf(OnBoardingTaskDefinitions.getPrimaryOwnerId()));
  }
  
  public void toValue(OnBoardingTaskDefinitions OnBoardingTaskDefinitions, OnBoardingTaskDefiForm OnBoardingTaskDefiForm)
  {
    OnBoardingTaskDefinitions.setTaskName(OnBoardingTaskDefiForm.getTaskName());
    OnBoardingTaskDefinitions.setTaskDesc(OnBoardingTaskDefiForm.getTaskDesc());
    OnBoardingTaskDefinitions.setEventType(OnBoardingTaskDefiForm.getEventType());
    
    OnBoardingTaskDefinitions.setPrimaryOwnerId(OnBoardingTaskDefiForm.getPrimaryOwnerId().longValue());
    OnBoardingTaskDefinitions.setPrimaryOwnerName(OnBoardingTaskDefiForm.getPrimaryOwnername());
    OnBoardingTaskDefinitions.setIsGroup(OnBoardingTaskDefiForm.getIsGroup());
    
    OnBoardingTaskDefinitions.setNoofdays(OnBoardingTaskDefiForm.getNoofdays());
    OnBoardingTaskDefinitions.setStatus("A");
  }
}
