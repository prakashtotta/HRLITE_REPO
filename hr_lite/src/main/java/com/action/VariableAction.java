package com.action;

import com.bean.User;
import com.bean.lov.VariableListData;
import com.bean.lov.Variables;
import com.bo.BOFactory;
import com.bo.VariableBO;
import com.common.Common;
import com.common.ValidationException;
import com.form.VariablesForm;
import com.resources.Constant;
import com.util.DateUtil;
import com.util.StringUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

public class VariableAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(VariableAction.class);
  
  public ActionForward variablelist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    VariablesForm variablesform = (VariablesForm)form;
    
    return mapping.findForward("variablelist");
  }
  
  public ActionForward loadAddButton(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    VariablesForm variablesform = (VariablesForm)form;
    return mapping.findForward("addbuttonvariable");
  }
  
  public ActionForward createVariable(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    User user = (User)request.getSession().getAttribute("user_data");
    

    VariablesForm variablesform = (VariablesForm)form;
    
    List allvariables = BOFactory.getVariableBO().getAllVariableDetails(user.getSuper_user_key());
    variablesform.setAllVariables(allvariables);
    return mapping.findForward("createVariable");
  }
  
  public ActionForward saveVariable(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveVariable method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user = (User)request.getSession().getAttribute("user_data");
    String listdata1 = request.getParameter("selectedlist");
    String defaultselect = request.getParameter("defaultselectedlist");
    logger.info("defaultselectedlistdefaultselectedlist" + defaultselect);
    String checkVariableCode = "no";
    VariablesForm variablesform = (VariablesForm)form;
    String variablecode = variablesform.getVariableName().replaceAll(" ", "_");
    List allvariables = BOFactory.getVariableBO().getAllVariableDetails(user.getSuper_user_key());
    if ((allvariables.size() != 0) && (allvariables.size() > 0))
    {
      Iterator itr1 = allvariables.iterator();
      int i = 1;
      while (itr1.hasNext())
      {
        Variables variable = (Variables)itr1.next();
        if (variable.getVariableCode().equals(variablecode))
        {
          variablesform.setCompareVariableCode("exist");
          checkVariableCode = "yes";
          request.setAttribute("checkVariableCode", "yes");
        }
      }
    }
    String[] column = null;
    if ((listdata1 != null) && (listdata1.length() > 0)) {
      column = StringUtils.tokenize(listdata1, ",");
    }
    String[] defaultcolum = null;
    String[] defaultcolum1 = null;
    









    Variables variables = new Variables();
    toValue(variables, variablesform);
    variables.setVariableCode(variablecode);
    variables.setCreatedBy(user.getUserName());
    variables.setCreatedDate(new Date());
    variables.setSuper_user_key(user.getSuper_user_key());
    if (((variablesform.getVariableType() != null) && (variablesform.getVariableType().equals("text"))) || (variablesform.getVariableType().equals("numeric")) || (variablesform.getVariableType().equals("textarea")) || (variablesform.getVariableType().equals("list")))
    {
      variables.setDefaultValueDate(null);
    }
    else if (!StringUtils.isNullOrEmpty(variablesform.getDefaultValueDate()))
    {
      String datepattern = DateUtil.getDatePatternFormat(user.getLocale());
      
      Calendar cal = DateUtil.convertStringDateToCalendar(variablesform.getDefaultValueDate(), datepattern);
      variables.setDefaultValueDate(cal.getTime());
    }
    if (checkVariableCode.equals("no"))
    {
      BOFactory.getVariableBO().saveVariable(variables, column, defaultselect);
      
      variablesform.setVariableId(variables.getVariableId());
    }
    variablesform.setSelectedData(column);
    variablesform.setIsMandatory(defaultselect);
    if (checkVariableCode.equals("no")) {
      request.setAttribute("saveVariable", "yes");
    }
    return mapping.findForward("createVariable");
  }
  
  public ActionForward updatevariable(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updatevariable method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user = (User)request.getSession().getAttribute("user_data");
    String listdata1 = request.getParameter("selectedlist");
    String defaultselect = request.getParameter("defaultselectedlist");
    String[] column = null;
    if ((listdata1 != null) && (listdata1.length() > 0)) {
      column = StringUtils.tokenize(listdata1, ",");
    }
    VariablesForm variablesform = (VariablesForm)form;
    
    String variableid = request.getParameter("variableid");
    Variables variables = BOFactory.getVariableBO().getVariablesDetails(variableid);
    toValue(variables, variablesform);
    variables.setUpdatedBy(user.getUserName());
    variables.setUpdatedDate(new Date());
    BOFactory.getVariableBO().deleteVariableListData(new Long(variableid).longValue());
    if (((variablesform.getVariableType() != null) && (variablesform.getVariableType().equals("text"))) || (variablesform.getVariableType().equals("numeric")) || (variablesform.getVariableType().equals("textarea")) || (variablesform.getVariableType().equals("list")))
    {
      variables.setDefaultValueDate(null);
    }
    else if (!StringUtils.isNullOrEmpty(variablesform.getDefaultValueDate()))
    {
      String datepattern = DateUtil.getDatePatternFormat(user.getLocale());
      



      Calendar cal = DateUtil.convertStringDateToCalendar(variablesform.getDefaultValueDate(), datepattern);
      
      variables.setDefaultValueDate(cal.getTime());
    }
    variables = BOFactory.getVariableBO().updateVariable(variables, column, defaultselect);
    
    variablesform.setVariableId(variables.getVariableId());
    variablesform.setSelectedData(column);
    variablesform.setIsMandatory(defaultselect);
    request.setAttribute("updateVariable", "yes");
    return mapping.findForward("createVariable");
  }
  
  public ActionForward editVariable(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside editVariable method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    User user1 = (User)request.getSession().getAttribute("user_data");
    String variableid = request.getParameter("variableId");
    String readPreview = request.getParameter("readPreview");
    VariablesForm variablesform = (VariablesForm)form;
    
    Variables variables = BOFactory.getVariableBO().getVariablesDetails(variableid);
    Set variablListedValue = variables.getListData();
    toForm(variablesform, variables);
    List variablListedValueList = new ArrayList();
    String[] column = null;
    String defaultselect = null;
    if (variablListedValue.size() > 0)
    {
      column = new String[variablListedValue.size()];
      Iterator itr = variablListedValue.iterator();
      int i = 0;
      int defaultcount = 0;
      while (itr.hasNext())
      {
        VariableListData vlistdata = (VariableListData)itr.next();
        variablListedValueList.add(vlistdata.getVariableValue());
        column[i] = vlistdata.getVariableValue();
        if (vlistdata.getIsDefault().equals("Y")) {
          defaultselect = vlistdata.getVariableValue();
        }
        i++;
      }
      logger.info("mmmjjjjjjjjjjjjjjjj" + defaultcount);
    }
    variablesform.setSelectedData(column);
    variablesform.setIsMandatory(defaultselect);
    

    String convertListToString = variablListedValueList.toString();
    convertListToString = StringUtils.delete(convertListToString, ",");
    convertListToString = StringUtils.delete(convertListToString, "[");
    convertListToString = StringUtils.delete(convertListToString, "]");
    convertListToString = StringUtils.delete(convertListToString, " ");
    


    variablesform.setVariablename(convertListToString);
    if (variables.getDefaultValueDate() != null)
    {
      String datepattern = Constant.getValue("defaultdateformat");
      if (user1 != null) {
        datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
      }
      variablesform.setDefaultValueDate(DateUtil.convertDateToStringDate(variables.getDefaultValueDate(), datepattern));
    }
    variablesform.setReadPreview(readPreview);
    return mapping.findForward("createVariable");
  }
  
  public ActionForward deleteVariable(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteVariable method");
    User user = (User)request.getSession().getAttribute("user_data");
    try
    {
      ActionErrors errors = new ActionErrors();
      ActionForward forward = new ActionForward();
      VariablesForm variablesform = (VariablesForm)form;
      String variableid = request.getParameter("variableid");
      BOFactory.getVariableBO().deleteVariable(new Long(variableid).longValue());
      
      request.setAttribute("deleteVariable", "yes");
      return mapping.findForward("createVariable");
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
  
  public void toDelete(Variables variables, VariablesForm variablesform)
  {
    variables.setStatus("D");
  }
  
  public void toValue(Variables variables, VariablesForm variablesform)
  {
    variables.setVariableName(variablesform.getVariableName());
    variables.setVariableCode(variables.getVariableCode());
    variables.setVariableDesc(variablesform.getVariableDesc());
    variables.setVariableType(variablesform.getVariableType());
    if (((variablesform.getVariableType() != null) && (variablesform.getVariableType().equals("date"))) || (variablesform.getVariableType().equals("list"))) {
      variables.setDefaultValue(null);
    } else if ((variablesform.getVariableType() != null) && (variablesform.getVariableType().equals("textarea"))) {
      variables.setDefaultValue(variablesform.getDefaultValuearea());
    } else if ((variablesform.getVariableType() != null) && (variablesform.getVariableType().equals("numeric"))) {
      variables.setDefaultValue(variablesform.getNeumeric());
    } else {
      variables.setDefaultValue(variablesform.getDefaultValue());
    }
    variables.setStatus("A");
  }
  
  public void toForm(VariablesForm variablesform, Variables variables)
  {
    variablesform.setVariableId(variables.getVariableId());
    variablesform.setVariableName(variables.getVariableName());
    variablesform.setVariableCode(variables.getVariableCode());
    variablesform.setVariableDesc(variables.getVariableDesc());
    variablesform.setVariableType(variables.getVariableType());
    if ((variables.getVariableType() != null) && (variables.getVariableType().equals("textarea"))) {
      variablesform.setDefaultValuearea(variables.getDefaultValue());
    } else if ((variables.getVariableType() != null) && (variables.getVariableType().equals("numeric"))) {
      variablesform.setNeumeric(variables.getDefaultValue());
    } else {
      variablesform.setDefaultValue(variables.getDefaultValue());
    }
    variables.setStatus("A");
  }
}
