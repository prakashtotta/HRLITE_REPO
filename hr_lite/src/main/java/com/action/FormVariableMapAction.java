package com.action;

import com.bean.User;
import com.bean.lov.FormVariablesMap;
import com.bean.lov.Variables;
import com.bo.BOFactory;
import com.bo.VariableBO;
import com.form.FormVariablesMapForm;
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

public class FormVariableMapAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(FormVariableMapAction.class);
  
  public ActionForward formVariablemapList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    FormVariablesMapForm formVariablesMapForm = (FormVariablesMapForm)form;
    
    return mapping.findForward("variableMaplist");
  }
  
  public ActionForward requisionFormMap(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    User user1 = (User)request.getSession().getAttribute("user_data");
    String formname = request.getParameter("formname");
    String formcode = request.getParameter("formcode");
    FormVariablesMapForm formVariablesMapForm = (FormVariablesMapForm)form;
    List formVariablesMapList = BOFactory.getVariableBO().getFormVariablesMapByFormCode(formcode, user1.getSuper_user_key());
    
    formVariablesMapForm.setVariableList(formVariablesMapList);
    formVariablesMapForm.setFormName(formname);
    formVariablesMapForm.setFormCode(formcode);
    return mapping.findForward("createVariableMapping");
  }
  
  public ActionForward manageVariableMappingscr(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside manageVariableMappingscr method");
    String formname = request.getParameter("formname");
    String formcode = request.getParameter("formcode");
    String id = request.getParameter("id");
    FormVariablesMapForm formVariablesMapForm = (FormVariablesMapForm)form;
    List formVariablesMapList = BOFactory.getVariableBO().getFormVariablesMapByFormCodeAndId(formcode, new Long(id).longValue());
    

    formVariablesMapForm.setVariableList(formVariablesMapList);
    formVariablesMapForm.setFormName(formname);
    formVariablesMapForm.setFormCode(formcode);
    formVariablesMapForm.setIdValue(new Long(id).longValue());
    return mapping.findForward("manageVariableMapping");
  }
  
  public ActionForward manageVariableMapping(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside manageVariableMapping method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user = (User)request.getSession().getAttribute("user_data");
    FormVariablesMapForm formVariablesMapForm = (FormVariablesMapForm)form;
    String formcode = request.getParameter("form_code");
    String formname = request.getParameter("form_name");
    String id = request.getParameter("id");
    List systemvariablesMapList = BOFactory.getVariableBO().getFormVariablesMapByFormCode(formcode, user.getSuper_user_key());
    
    List systemVaribleIdList = new ArrayList();
    for (int k = 0; k < systemvariablesMapList.size(); k++)
    {
      FormVariablesMap vb = (FormVariablesMap)systemvariablesMapList.get(k);
      
      systemVaribleIdList.add(String.valueOf(vb.getVariable().getVariableId()));
    }
    BOFactory.getVariableBO().deleteFormVariableMapByFormCodeAndId(formcode, new Long(id).longValue(), user.getSuper_user_key());
    for (int i = 1; i < 50; i++)
    {
      String variableid = request.getParameter("variableid_" + i);
      
      String Ismandatory = request.getParameter("mandatory_" + i);
      if ((!StringUtils.isNullOrEmpty(variableid)) && 
        (!systemVaribleIdList.contains(variableid)))
      {
        systemVaribleIdList.add(variableid);
        Variables variable = new Variables();
        variable.setVariableId(new Long(variableid).longValue());
        FormVariablesMap formVariablesMap = new FormVariablesMap();
        formVariablesMap.setFormName(formname);
        formVariablesMap.setFormCode(formcode);
        formVariablesMap.setIdValue(new Long(id).longValue());
        formVariablesMap.setVariable(variable);
        formVariablesMap.setSequence(i);
        if ((!StringUtils.isNullOrEmpty(Ismandatory)) && (Ismandatory.equals("on"))) {
          formVariablesMap.setIsMandatory("Y");
        } else {
          formVariablesMap.setIsMandatory("N");
        }
        formVariablesMap.setUpdatedBy(user.getUserName());
        formVariablesMap.setUpdatedDate(new Date());
        formVariablesMap.setSuper_user_key(user.getSuper_user_key());
        BOFactory.getVariableBO().updateFormVariableMap(formVariablesMap);
      }
    }
    List formVariablesMapList = BOFactory.getVariableBO().getFormVariablesMapByFormCodeAndId(formcode, new Long(id).longValue());
    

    formVariablesMapForm.setVariableList(formVariablesMapList);
    formVariablesMapForm.setFormName(formname);
    formVariablesMapForm.setFormCode(formcode);
    formVariablesMapForm.setIdValue(new Long(id).longValue());
    request.setAttribute("manageVariableMapping", "yes");
    return mapping.findForward("manageVariableMapping");
  }
  
  public ActionForward updateFormVariableMap(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updateFormVariableMap method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user = (User)request.getSession().getAttribute("user_data");
    FormVariablesMapForm formVariablesMapForm = (FormVariablesMapForm)form;
    String formcode = request.getParameter("form_code");
    String formname = request.getParameter("form_name");
    List systemVaribleIdList = new ArrayList();
    BOFactory.getVariableBO().deleteFormVariableMap(formcode, user.getSuper_user_key());
    for (int i = 1; i < 50; i++)
    {
      String variableid = request.getParameter("variableid_" + i);
      
      String Ismandatory = request.getParameter("mandatory_" + i);
      if ((!StringUtils.isNullOrEmpty(variableid)) && 
        (!systemVaribleIdList.contains(variableid)))
      {
        systemVaribleIdList.add(variableid);
        Variables variable = new Variables();
        variable.setVariableId(new Long(variableid).longValue());
        FormVariablesMap formVariablesMap = new FormVariablesMap();
        formVariablesMap.setFormName(formname);
        formVariablesMap.setFormCode(formcode);
        formVariablesMap.setIdValue(0L);
        formVariablesMap.setVariable(variable);
        formVariablesMap.setSequence(i);
        if ((!StringUtils.isNullOrEmpty(Ismandatory)) && (Ismandatory.equals("on"))) {
          formVariablesMap.setIsMandatory("Y");
        } else {
          formVariablesMap.setIsMandatory("N");
        }
        formVariablesMap.setUpdatedBy(user.getUserName());
        formVariablesMap.setUpdatedDate(new Date());
        formVariablesMap.setSuper_user_key(user.getSuper_user_key());
        BOFactory.getVariableBO().updateFormVariableMap(formVariablesMap);
      }
    }
    List formVariablesMapList = BOFactory.getVariableBO().getFormVariablesMapByFormCode(formcode, user.getSuper_user_key());
    
    formVariablesMapForm.setVariableList(formVariablesMapList);
    formVariablesMapForm.setFormName(formname);
    formVariablesMapForm.setFormCode(formcode);
    request.setAttribute("manageVariableMapping", "yes");
    return mapping.findForward("createVariableMapping");
  }
  
  public ActionForward variableselector(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside variableselector method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    String variablevalue = request.getParameter("variablevalue");
    String variableidval = request.getParameter("variableidval");
    
    String variablespanvalue = request.getParameter("variablespanvalue");
    
    String idval = request.getParameter("idval");
    if (idval != null) {
      request.setAttribute("boxnumber", idval);
    }
    FormVariablesMapForm formVariablesMapForm = (FormVariablesMapForm)form;
    List variableList = BOFactory.getVariableBO().getAllVariableDetails(user1.getSuper_user_key());
    formVariablesMapForm.setVariableList(variableList);
    if (variableList != null) {
      formVariablesMapForm.setResults(String.valueOf(variableList.size()));
    } else {
      formVariablesMapForm.setResults("0");
    }
    formVariablesMapForm.setVariablevalue("variable_" + idval);
    formVariablesMapForm.setVariableidval("variableid_" + idval);
    formVariablesMapForm.setVariablespanvalue("compspan_" + idval);
    formVariablesMapForm.setIdval(idval);
    formVariablesMapForm.setVariabletypeList(Constant.getVariableTypeList(user1));
    
    return mapping.findForward("selectorVariable");
  }
  
  public ActionForward searchVariables(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchVariables method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    
    String boxnu = request.getParameter("boxnu");
    String idval = request.getParameter("idval");
    String start = request.getParameter("start");
    String range = request.getParameter("range");
    String results = request.getParameter("results");
    int start1 = StringUtils.stringToInt(start, 0);
    int range1 = StringUtils.stringToInt(range, 15);
    
    FormVariablesMapForm formVariablesMapForm = (FormVariablesMapForm)form;
    
    int totaluser = 0;
    if (StringUtils.isNullOrEmpty(results)) {
      totaluser = BOFactory.getVariableBO().getVariableByCriteraCount(formVariablesMapForm.getVariablename(), formVariablesMapForm.getVariabletype(), user1.getSuper_user_key());
    } else {
      totaluser = new Integer(results).intValue();
    }
    logger.info("results" + totaluser);
    List variableList = BOFactory.getVariableBO().getVariableByCritera(formVariablesMapForm.getVariablename(), formVariablesMapForm.getVariabletype(), user1.getSuper_user_key(), start1, range1);
    

    formVariablesMapForm.setVariableList(variableList);
    formVariablesMapForm.setStart(String.valueOf(start1));
    formVariablesMapForm.setRange(String.valueOf(range1));
    formVariablesMapForm.setResults(String.valueOf(totaluser));
    formVariablesMapForm.setVariablename(formVariablesMapForm.getVariablename());
    
    formVariablesMapForm.setVariabletype(formVariablesMapForm.getVariabletype());
    
    formVariablesMapForm.setVariablevalue("variable_" + idval);
    formVariablesMapForm.setVariableidval("variableid_" + idval);
    formVariablesMapForm.setVariablespanvalue("compspan_" + idval);
    formVariablesMapForm.setVariabletypeList(Constant.getVariableTypeList(user1));
    

    request.setAttribute("boxnumber", boxnu);
    return mapping.findForward("selectorVariable");
  }
  
  public void toForm(FormVariablesMapForm formVariablesMapForm, FormVariablesMap formVariablesMap)
  {
    formVariablesMapForm.setFormName(formVariablesMap.getFormName());
    formVariablesMapForm.setFormCode(formVariablesMap.getFormCode());
    formVariablesMapForm.setVariable(formVariablesMap.getVariable());
  }
}
