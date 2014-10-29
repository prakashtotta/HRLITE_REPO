package com.form;

import com.bean.ApplicantUser;
import com.bean.JobApplicant;
import com.bean.Locale;
import com.bean.Questions;
import com.bean.RefferalEmployee;
import com.bean.SearchApplicantCustomFields;
import com.bean.Timezone;
import com.bean.User;
import com.bean.criteria.SearchCustomFields;
import com.bean.lov.FormVariablesMap;
import com.bean.lov.Variables;
import com.bean.lov.VariablesValues;
import com.bo.ApplicantBO;
import com.bo.BOFactory;
import com.bo.BusinessFilterBO;
import com.bo.RefBO;
import com.bo.VariableBO;
import com.resources.Constant;
import com.util.DateUtil;
import com.util.StringUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

public class VariableDataCaptureUtil
{
  protected static final Logger logger = Logger.getLogger(VariableDataCaptureUtil.class);
  static List reqFormList = new ArrayList();
  
  static
  {
    reqFormList.add("REQUISITION_TMPL_FORM");
    reqFormList.add("REQUISITION_FORM");
  }
  
  public static Map captureCustomVariables(HttpServletRequest request, long idValue, String formcode, long superUserKey)
    throws Exception
  {
    logger.info("Inside captureCustomVariables" + idValue);
    if ((!StringUtils.isNullOrEmpty(formcode)) && (Constant.screenCodeListAppScreenOtherPackage.contains(formcode))) {
      formcode = BOFactory.getBusinessFilterBO().checkScreenCodeBasedOnPackage(superUserKey, formcode);
    }
    if ((StringUtils.isNullOrEmpty(formcode)) || (!reqFormList.contains(formcode))) {
      if (idValue == 0L)
      {
        if (StringUtils.isNullOrEmpty(formcode)) {
          formcode = getScreenCodeWithoutApplicant(request);
        }
      }
      else
      {
        JobApplicant applicant = BOFactory.getApplicantBO().getApplicantStatusAndRequitionDetails(idValue);
        formcode = applicant.getScreenCode();
      }
    }
    logger.info("Inside captureCustomVariables formcode" + formcode);
    Map m = new HashMap();
    
    Locale locale = getLocale(request);
    Timezone timezone = getTimeZone(request);
    List formVariablesList = BOFactory.getVariableBO().getFormVariablesList(formcode, idValue, superUserKey);
    logger.info("Inside captureCustomVariables formVariablesList" + formVariablesList);
    List formvariabledataList = new ArrayList();
    List errorList = new ArrayList();
    if ((formVariablesList != null) && (formVariablesList.size() > 0)) {
      for (int i = 0; i < formVariablesList.size(); i++)
      {
        FormVariablesMap fvaiablemap = (FormVariablesMap)formVariablesList.get(i);
        if (fvaiablemap != null)
        {
          Variables variable = fvaiablemap.getVariable();
          Variables variablenew = new Variables();
          toNewVariable(variable, variablenew);
          if (variable != null)
          {
            String value = request.getParameter(variable.getVariableCode());
            logger.info("Inside captureCustomVariables variable.getVariableCode()" + variable.getVariableCode());
            logger.info("Inside captureCustomVariables value" + value);
            if ((!StringUtils.isNullOrEmpty(fvaiablemap.getIsMandatory())) && (fvaiablemap.getIsMandatory().equalsIgnoreCase("Y")) && 
              (StringUtils.isNullOrEmpty(value))) {
              errorList.add(variable.getVariableName() + " is mandatory");
            }
            if ((!StringUtils.isNullOrEmpty(variable.getVariableType())) && (variable.getVariableType().equals("numeric")))
            {
              if (StringUtils.isNullOrEmpty(value)) {
                value = String.valueOf(0);
              }
              try
              {
                Integer.parseInt(value);
                Double.parseDouble(value);
              }
              catch (Exception e)
              {
                errorList.add(variable.getVariableName() + " need to be numeric value");
              }
            }
            variablenew.setDefaultValue(value);
            

            VariablesValues vvalue = new VariablesValues();
            if ((!StringUtils.isNullOrEmpty(variable.getVariableType())) && (variable.getVariableType().equals("date")))
            {
              if (!StringUtils.isNullOrEmpty(value)) {
                try
                {
                  String datepattern = DateUtil.getDatePatternFormat(locale);
                  String converteddate = DateUtil.convertFromTimezoneToTimezoneDateWithException(value, datepattern, timezone.getTimezoneCode(), TimeZone.getDefault().getID());
                  
                  logger.info("converteddate" + converteddate);
                  
                  Calendar cal = DateUtil.convertStringDateToCalendarWithException(converteddate, datepattern);
                  
                  vvalue.setValueDate(cal.getTime());
                }
                catch (Exception e)
                {
                  errorList.add(variable.getVariableName() + " need to be date value");
                }
              } else {
                vvalue.setValueDate(null);
              }
            }
            else {
              vvalue.setValueText(value);
            }
            vvalue.setIdvalue(idValue);
            vvalue.setFormCode(formcode);
            vvalue.setVariableType(variable.getVariableType());
            vvalue.setVariableId(variable.getVariableId());
            vvalue.setCreatedBy(getUserName(request));
            vvalue.setCreatedDate(new Date());
            vvalue.setVariableCode(variable.getVariableCode());
            

            formvariabledataList.add(vvalue);
          }
          fvaiablemap.setVariable(variablenew);
        }
      }
    }
    m.put("formVariablesList", formVariablesList);
    m.put("errorList", errorList);
    m.put("formVariableDataList", formvariabledataList);
    

    return m;
  }
  
  public static Map captureCustomVariablesMoveToRequistion(HttpServletRequest request, long idValue, String formcode, long superUserKey)
    throws Exception
  {
    logger.info("Inside captureCustomVariablesMoveToRequistion");
    Map m = new HashMap();
    try
    {
      Locale locale = getLocale(request);
      Timezone timezone = getTimeZone(request);
      List formVariablesList = BOFactory.getVariableBO().getFormVariablesList(formcode, idValue, superUserKey);
      
      List variableValueList = BOFactory.getVariableBO().getVariablesValues(idValue, formcode);
      logger.info("Inside toValueCustomVariables" + variableValueList);
      Map valueMap = new HashMap();
      if ((variableValueList != null) && (variableValueList.size() > 0)) {
        for (int i = 0; i < variableValueList.size(); i++)
        {
          VariablesValues vvalue = (VariablesValues)variableValueList.get(i);
          if (vvalue != null) {
            if ((!StringUtils.isNullOrEmpty(vvalue.getVariableType())) && (vvalue.getVariableType().equals("date")))
            {
              valueMap.put(Long.valueOf(vvalue.getVariableId()), vvalue.getValueDate());
            }
            else
            {
              valueMap.put(Long.valueOf(vvalue.getVariableId()), vvalue.getValueText());
              logger.info("Inside vvalue.getValueText()" + vvalue.getVariableId() + vvalue.getValueText());
            }
          }
        }
      }
      List formvariabledataList = new ArrayList();
      List errorList = new ArrayList();
      if ((formVariablesList != null) && (formVariablesList.size() > 0)) {
        for (int i = 0; i < formVariablesList.size(); i++)
        {
          FormVariablesMap fvaiablemap = (FormVariablesMap)formVariablesList.get(i);
          if (fvaiablemap != null)
          {
            Variables variable = fvaiablemap.getVariable();
            if (variable != null) {
              if ((!StringUtils.isNullOrEmpty(fvaiablemap.getIsMandatory())) && (fvaiablemap.getIsMandatory().equalsIgnoreCase("Y"))) {
                if ((!StringUtils.isNullOrEmpty(variable.getVariableType())) && (variable.getVariableType().equals("date")))
                {
                  Date value = (Date)valueMap.get(Long.valueOf(variable.getVariableId()));
                  if (value == null) {
                    errorList.add(variable.getVariableName() + " is mandatory");
                  }
                }
                else
                {
                  String value = (String)valueMap.get(Long.valueOf(variable.getVariableId()));
                  if (StringUtils.isNullOrEmpty(value)) {
                    errorList.add(variable.getVariableName() + " is mandatory");
                  }
                }
              }
            }
          }
        }
      }
      m.put("errorList", errorList);
    }
    catch (Exception e)
    {
      logger.info("Exception on captureCustomVariablesMoveToRequistion", e);
      throw e;
    }
    return m;
  }
  
  public static List fromValueCustomVariables(HttpServletRequest request, long idValue, String formcode, long superUserKey)
    throws Exception
  {
    logger.info("Inside fromValueCustomVariables");
    logger.info("formcode =>> " + formcode);
    if ((!StringUtils.isNullOrEmpty(formcode)) && (Constant.screenCodeListAppScreenOtherPackage.contains(formcode))) {
      formcode = BOFactory.getBusinessFilterBO().checkScreenCodeBasedOnPackage(superUserKey, formcode);
    }
    List formVariablesListNew = new ArrayList();
    try
    {
      if ((StringUtils.isNullOrEmpty(formcode)) || (!reqFormList.contains(formcode))) {
        if (idValue == 0L)
        {
          if (StringUtils.isNullOrEmpty(formcode)) {
            formcode = getScreenCodeWithoutApplicant(request);
          }
        }
        else
        {
          JobApplicant applicant = BOFactory.getApplicantBO().getApplicantStatusAndRequitionDetails(idValue);
          if (StringUtils.isNullOrEmpty(applicant.getScreenCode())) {
            formcode = getScreenCode(request, applicant);
          } else {
            formcode = applicant.getScreenCode();
          }
        }
      }
      logger.info("satyaaaaaaaaaa - " + formcode + " - idValue" + idValue);
      if ((!StringUtils.isNullOrEmpty(formcode)) && (formcode.equals("APPLICANT_SCREEN_TALENTPOOL")))
      {
        formcode = "APPLICANT_SCREEN";
        logger.info("formcode =>> " + formcode);
      }
      Locale locale = getLocale(request);
      List formVariablesList = BOFactory.getVariableBO().getFormVariablesList(formcode, idValue, superUserKey);
      List variableValueList = BOFactory.getVariableBO().getVariablesValues(idValue, formcode);
      logger.info("Inside toValueCustomVariables" + variableValueList);
      logger.info("Inside formVariablesList" + formVariablesList);
      Map valueMap = new HashMap();
      if ((variableValueList != null) && (variableValueList.size() > 0)) {
        for (int i = 0; i < variableValueList.size(); i++)
        {
          VariablesValues vvalue = (VariablesValues)variableValueList.get(i);
          if (vvalue != null) {
            if ((!StringUtils.isNullOrEmpty(vvalue.getVariableType())) && (vvalue.getVariableType().equals("date")))
            {
              valueMap.put(Long.valueOf(vvalue.getVariableId()), vvalue.getValueDate());
            }
            else
            {
              valueMap.put(Long.valueOf(vvalue.getVariableId()), vvalue.getValueText());
              logger.info("Inside vvalue.getValueText()" + vvalue.getVariableId() + vvalue.getValueText());
            }
          }
        }
      }
      if ((formVariablesList != null) && (formVariablesList.size() > 0)) {
        for (int i = 0; i < formVariablesList.size(); i++)
        {
          FormVariablesMap fvaiablemap = (FormVariablesMap)formVariablesList.get(i);
          if (fvaiablemap != null)
          {
            Variables variable = fvaiablemap.getVariable();
            if (variable != null)
            {
              Variables variablenew = new Variables();
              variablenew.setVariableId(variable.getVariableId());
              variablenew.setVariableName(variable.getVariableName());
              variablenew.setVariableType(variable.getVariableType());
              variablenew.setVariableCode(variable.getVariableCode());
              variablenew.setListData(variable.getListData());
              variablenew.setVariableDesc(variable.getVariableDesc());
              if ((idValue != 0L) && (valueMap.containsKey(Long.valueOf(variable.getVariableId()))))
              {
                if ((!StringUtils.isNullOrEmpty(variable.getVariableType())) && (variable.getVariableType().equals("date")))
                {
                  Date dt = (Date)valueMap.get(Long.valueOf(variable.getVariableId()));
                  logger.info("dt" + dt);
                  if (dt != null) {
                    if (locale != null)
                    {
                      String datepattern = DateUtil.getDatePatternFormat(locale);
                      
                      variablenew.setDefaultValue(DateUtil.convertDateToStringDate(dt, datepattern));
                      logger.info("defaultvalue" + variable.getDefaultValue());
                    }
                    else
                    {
                      String datepattern = Constant.getValue("defaultdateformat");
                      
                      variablenew.setDefaultValue(DateUtil.convertDateToStringDate(dt, datepattern));
                      logger.info("defaultvalue" + variable.getDefaultValue());
                    }
                  }
                }
                else
                {
                  logger.info("defaultvalue" + String.valueOf(valueMap.get(Long.valueOf(variable.getVariableId()))));
                  variablenew.setDefaultValue(String.valueOf(valueMap.get(Long.valueOf(variable.getVariableId()))));
                }
              }
              else if ((!StringUtils.isNullOrEmpty(variable.getVariableType())) && (variable.getVariableType().equals("date")))
              {
                Date dt = variable.getDefaultValueDate();
                logger.info("dt" + dt);
                if (dt != null) {
                  if (locale != null)
                  {
                    String datepattern = DateUtil.getDatePatternFormat(locale);
                    
                    variablenew.setDefaultValue(DateUtil.convertDateToStringDate(dt, datepattern));
                    logger.info("defaultvalue" + variable.getDefaultValue());
                  }
                  else
                  {
                    String datepattern = Constant.getValue("defaultdateformat");
                    
                    variablenew.setDefaultValue(DateUtil.convertDateToStringDate(dt, datepattern));
                    logger.info("defaultvalue" + variable.getDefaultValue());
                  }
                }
              }
              else
              {
                variablenew.setDefaultValue(variable.getDefaultValue());
              }
              fvaiablemap.setVariable(variablenew);
              formVariablesListNew.add(fvaiablemap);
            }
          }
        }
      }
    }
    catch (Exception e)
    {
      logger.info("fromValueCustomVariables", e);
      throw e;
    }
    return formVariablesListNew;
  }
  
  public static List mergeCustomVariableValues(List formVariablesList, List variableValueList, HttpServletRequest request)
  {
    Locale locale = getLocale(request);
    List formVariablesListNew = new ArrayList();
    Map valueMap = new HashMap();
    if ((variableValueList != null) && (variableValueList.size() > 0)) {
      for (int i = 0; i < variableValueList.size(); i++)
      {
        VariablesValues vvalue = (VariablesValues)variableValueList.get(i);
        if (vvalue != null) {
          if ((!StringUtils.isNullOrEmpty(vvalue.getVariableType())) && (vvalue.getVariableType().equals("date")))
          {
            valueMap.put(Long.valueOf(vvalue.getVariableId()), vvalue.getValueDate());
          }
          else
          {
            valueMap.put(Long.valueOf(vvalue.getVariableId()), vvalue.getValueText());
            logger.info("Inside vvalue.getValueText()" + vvalue.getVariableId() + vvalue.getValueText());
          }
        }
      }
    }
    if ((formVariablesList != null) && (formVariablesList.size() > 0)) {
      for (int i = 0; i < formVariablesList.size(); i++)
      {
        FormVariablesMap fvaiablemap = (FormVariablesMap)formVariablesList.get(i);
        if (fvaiablemap != null)
        {
          Variables variable = fvaiablemap.getVariable();
          if (variable != null)
          {
            Variables variablenew = new Variables();
            variablenew.setVariableId(variable.getVariableId());
            variablenew.setVariableName(variable.getVariableName());
            variablenew.setVariableType(variable.getVariableType());
            variablenew.setVariableCode(variable.getVariableCode());
            variablenew.setListData(variable.getListData());
            variablenew.setVariableDesc(variable.getVariableDesc());
            if (valueMap.containsKey(Long.valueOf(variable.getVariableId()))) {
              if ((!StringUtils.isNullOrEmpty(variable.getVariableType())) && (variable.getVariableType().equals("date")))
              {
                Date dt = (Date)valueMap.get(Long.valueOf(variable.getVariableId()));
                logger.info("dt" + dt);
                if (dt != null) {
                  if (locale != null)
                  {
                    String datepattern = DateUtil.getDatePatternFormat(locale);
                    
                    variablenew.setDefaultValue(DateUtil.convertDateToStringDate(dt, datepattern));
                    logger.info("defaultvalue" + variable.getDefaultValue());
                  }
                  else
                  {
                    String datepattern = Constant.getValue("defaultdateformat");
                    
                    variablenew.setDefaultValue(DateUtil.convertDateToStringDate(dt, datepattern));
                    logger.info("defaultvalue" + variable.getDefaultValue());
                  }
                }
              }
              else
              {
                logger.info("defaultvalue" + String.valueOf(valueMap.get(Long.valueOf(variable.getVariableId()))));
                variablenew.setDefaultValue(String.valueOf(valueMap.get(Long.valueOf(variable.getVariableId()))));
              }
            }
            fvaiablemap.setVariable(variablenew);
            formVariablesListNew.add(fvaiablemap);
          }
        }
      }
    }
    return formVariablesListNew;
  }
  
  public static List fromValueCustomVariablesAllForApplicant(HttpServletRequest request, long idValue, String formcode)
    throws Exception
  {
    logger.info("Inside fromValueCustomVariablesAllForApplicant");
    

    List formVariablesListNew = new ArrayList();
    try
    {
      JobApplicant applicant = BOFactory.getApplicantBO().getApplicantStatusAndRequitionDetails(idValue);
      if (StringUtils.isNullOrEmpty(applicant.getScreenCode())) {
        formcode = getScreenCode(request, applicant);
      }
      if ((!StringUtils.isNullOrEmpty(formcode)) && (Constant.screenCodeListAppScreenOtherPackage.contains(formcode))) {
        formcode = BOFactory.getBusinessFilterBO().checkScreenCodeBasedOnPackage(applicant.getSuper_user_key(), formcode);
      }
      Locale locale = getLocale(request);
      


      List formVariablesList = BOFactory.getVariableBO().getFormVariablesList(formcode, idValue, applicant.getSuper_user_key());
      List variableValueList = BOFactory.getVariableBO().getVariablesValues(idValue, formcode);
      
      logger.info("Inside toValueCustomVariables" + variableValueList);
      Map valueMap = new HashMap();
      if ((variableValueList != null) && (variableValueList.size() > 0)) {
        for (int i = 0; i < variableValueList.size(); i++)
        {
          VariablesValues vvalue = (VariablesValues)variableValueList.get(i);
          if (vvalue != null) {
            if ((!StringUtils.isNullOrEmpty(vvalue.getVariableType())) && (vvalue.getVariableType().equals("date")))
            {
              valueMap.put(vvalue.getVariableCode(), vvalue.getValueDate());
            }
            else
            {
              valueMap.put(vvalue.getVariableCode(), vvalue.getValueText());
              logger.info("Inside vvalue.getValueText()" + vvalue.getVariableCode() + vvalue.getValueText());
            }
          }
        }
      }
      if ((formVariablesList != null) && (formVariablesList.size() > 0)) {
        for (int i = 0; i < formVariablesList.size(); i++)
        {
          FormVariablesMap fvaiablemap = (FormVariablesMap)formVariablesList.get(i);
          if (fvaiablemap != null)
          {
            Variables variable = fvaiablemap.getVariable();
            if (variable != null)
            {
              Variables variablenew = new Variables();
              variablenew.setVariableId(variable.getVariableId());
              variablenew.setVariableName(variable.getVariableName());
              variablenew.setVariableType(variable.getVariableType());
              variablenew.setVariableCode(variable.getVariableCode());
              variablenew.setListData(variable.getListData());
              variablenew.setVariableDesc(variable.getVariableDesc());
              if (valueMap.containsKey(variable.getVariableCode())) {
                if ((!StringUtils.isNullOrEmpty(variable.getVariableType())) && (variable.getVariableType().equals("date")))
                {
                  Date dt = (Date)valueMap.get(variable.getVariableCode());
                  logger.info("dt" + dt);
                  if (dt != null) {
                    if (locale != null)
                    {
                      String datepattern = DateUtil.getDatePatternFormat(locale);
                      
                      variablenew.setDefaultValue(DateUtil.convertDateToStringDate(dt, datepattern));
                      logger.info("defaultvalue" + variablenew.getDefaultValue());
                    }
                    else
                    {
                      String datepattern = Constant.getValue("defaultdateformat");
                      
                      variablenew.setDefaultValue(DateUtil.convertDateToStringDate(dt, datepattern));
                      logger.info("defaultvalue" + variablenew.getDefaultValue());
                    }
                  }
                }
                else
                {
                  logger.info("defaultvalue satyaaaaaaaaaaaaaaa" + String.valueOf(valueMap.get(variable.getVariableCode())));
                  variablenew.setDefaultValue(String.valueOf(valueMap.get(variable.getVariableCode())));
                  logger.info("defaultvalue satyaaaaaaaaaaaaaaa" + variablenew.getDefaultValue());
                }
              }
              fvaiablemap.setVariable(variablenew);
              formVariablesListNew.add(fvaiablemap);
            }
          }
        }
      }
    }
    catch (Exception e)
    {
      logger.info("fromValueCustomVariablesAllForApplicant", e);
      throw e;
    }
    return formVariablesListNew;
  }
  
  public static List captureCustomVariablesForApplicantSearch(HttpServletRequest request, long idValue, String formcode, long searchappid, long super_user_key)
    throws Exception
  {
    logger.info("Inside captureCustomVariablesForApplicantSearch");
    
    List formvariabledataList = new ArrayList();
    try
    {
      Locale locale = getLocale(request);
      Timezone timezone = getTimeZone(request);
      
      List formVariablesList = BOFactory.getVariableBO().getAllFormVariablesListForApplicant(super_user_key);
      if ((formVariablesList != null) && (formVariablesList.size() > 0)) {
        for (int i = 0; i < formVariablesList.size(); i++)
        {
          FormVariablesMap fvaiablemap = (FormVariablesMap)formVariablesList.get(i);
          if (fvaiablemap != null)
          {
            Variables variable = fvaiablemap.getVariable();
            if (variable != null)
            {
              SearchApplicantCustomFields sacf = new SearchApplicantCustomFields();
              sacf.setSearchappid(searchappid);
              sacf.setVariable_id(variable.getVariableId());
              sacf.setVaribale_type(variable.getVariableType());
              sacf.setVariableCode(variable.getVariableCode());
              if ((!StringUtils.isNullOrEmpty(variable.getVariableType())) && (variable.getVariableType().equals("numeric"))) {
                try
                {
                  String valuecri = request.getParameter(variable.getVariableCode() + "_numericcri");
                  sacf.setFiltercri(valuecri);
                  String value = request.getParameter(variable.getVariableCode());
                  sacf.setFilterValue1(value);
                  if ((!StringUtils.isNullOrEmpty(valuecri)) && (valuecri.equals("BETWEEN")))
                  {
                    String value1 = request.getParameter(variable.getVariableCode() + "_1");
                    sacf.setFilterValue2(value1);
                  }
                }
                catch (Exception e) {}
              } else if ((!StringUtils.isNullOrEmpty(variable.getVariableType())) && (variable.getVariableType().equals("date"))) {
                try
                {
                  String valuecri = request.getParameter(variable.getVariableCode() + "_datecri");
                  sacf.setFiltercri(valuecri);
                  String value = request.getParameter(variable.getVariableCode());
                  sacf.setFilterValue1(value);
                  if ((!StringUtils.isNullOrEmpty(valuecri)) && (valuecri.equals("BETWEEN")))
                  {
                    String value1 = request.getParameter(variable.getVariableCode() + "_1");
                    sacf.setFilterValue2(value1);
                  }
                }
                catch (Exception e) {}
              } else if ((!StringUtils.isNullOrEmpty(variable.getVariableType())) && (variable.getVariableType().equals("list"))) {
                try
                {
                  String value = request.getParameter(variable.getVariableCode());
                  sacf.setAnswerOption(value);
                }
                catch (Exception e) {}
              } else {
                try
                {
                  String valuecri = request.getParameter(variable.getVariableCode() + "_textcri");
                  sacf.setFiltercri(valuecri);
                  String value = request.getParameter(variable.getVariableCode());
                  sacf.setFilterValue1(value);
                }
                catch (Exception e) {}
              }
              formvariabledataList.add(sacf);
            }
          }
        }
      }
    }
    catch (Exception e)
    {
      logger.info("captureCustomVariablesForApplicantSearch", e);
      throw e;
    }
    return formvariabledataList;
  }
  
  public static List captureCustomVariablesForRequistionSearch(HttpServletRequest request, long idValue, String formcode, long super_user_key)
    throws Exception
  {
    logger.info("Inside captureCustomVariablesForRequistionSearch");
    
    List formvariabledataList = new ArrayList();
    try
    {
      Locale locale = getLocale(request);
      Timezone timezone = getTimeZone(request);
      
      List formVariablesList = BOFactory.getVariableBO().getAllFormVariablesListForRequistion(super_user_key);
      if ((formVariablesList != null) && (formVariablesList.size() > 0)) {
        for (int i = 0; i < formVariablesList.size(); i++)
        {
          FormVariablesMap fvaiablemap = (FormVariablesMap)formVariablesList.get(i);
          if (fvaiablemap != null)
          {
            Variables variable = fvaiablemap.getVariable();
            if (variable != null)
            {
              SearchCustomFields sacf = new SearchCustomFields();
              
              sacf.setVariable_id(variable.getVariableId());
              sacf.setVaribale_type(variable.getVariableType());
              sacf.setVariableCode(variable.getVariableCode());
              if ((!StringUtils.isNullOrEmpty(variable.getVariableType())) && (variable.getVariableType().equals("numeric"))) {
                try
                {
                  String valuecri = request.getParameter(variable.getVariableCode() + "_numericcri");
                  sacf.setFiltercri(valuecri);
                  String value = request.getParameter(variable.getVariableCode());
                  sacf.setFilterValue1(value);
                  if ((!StringUtils.isNullOrEmpty(valuecri)) && (valuecri.equals("BETWEEN")))
                  {
                    String value1 = request.getParameter(variable.getVariableCode() + "_1");
                    sacf.setFilterValue2(value1);
                  }
                }
                catch (Exception e) {}
              } else if ((!StringUtils.isNullOrEmpty(variable.getVariableType())) && (variable.getVariableType().equals("date"))) {
                try
                {
                  String valuecri = request.getParameter(variable.getVariableCode() + "_datecri");
                  sacf.setFiltercri(valuecri);
                  String value = request.getParameter(variable.getVariableCode());
                  sacf.setFilterValue1(value);
                  if ((!StringUtils.isNullOrEmpty(valuecri)) && (valuecri.equals("BETWEEN")))
                  {
                    String value1 = request.getParameter(variable.getVariableCode() + "_1");
                    sacf.setFilterValue2(value1);
                  }
                }
                catch (Exception e) {}
              } else if ((!StringUtils.isNullOrEmpty(variable.getVariableType())) && (variable.getVariableType().equals("list"))) {
                try
                {
                  String value = request.getParameter(variable.getVariableCode());
                  sacf.setAnswerOption(value);
                }
                catch (Exception e) {}
              } else {
                try
                {
                  String valuecri = request.getParameter(variable.getVariableCode() + "_textcri");
                  sacf.setFiltercri(valuecri);
                  String value = request.getParameter(variable.getVariableCode());
                  sacf.setFilterValue1(value);
                }
                catch (Exception e) {}
              }
              formvariabledataList.add(sacf);
            }
          }
        }
      }
    }
    catch (Exception e)
    {
      logger.info("captureCustomVariablesForRequistionSearch", e);
      throw e;
    }
    return formvariabledataList;
  }
  
  public static String getUserName(HttpServletRequest request)
  {
    String uname = "";
    User userAg = (User)request.getSession().getAttribute("agency_data");
    User user = (User)request.getSession().getAttribute("user_data");
    RefferalEmployee userEmp = (RefferalEmployee)request.getSession().getAttribute("employee_refferal_data");
    ApplicantUser appuser = (ApplicantUser)request.getSession().getAttribute("applicant_user_data");
    if (user != null)
    {
      uname = user.getUserName();
      logger.info("getting UserName from user");
    }
    else if (userAg != null)
    {
      uname = userAg.getLastName();
      logger.info("getting UserName from agency");
    }
    else if (userEmp != null)
    {
      uname = userEmp.getEmployeeemail();
      logger.info("getting UserName from user employee");
    }
    else if (appuser != null)
    {
      uname = appuser.getFullName();
      logger.info("getting UserName from user applicant user");
    }
    else
    {
      String refuserid = (String)request.getSession().getAttribute("refuserid");
      logger.info("getting UserName from user referral user id" + refuserid);
      if (!StringUtils.isNullOrEmpty(refuserid))
      {
        uname = "external";
        logger.info("getting UserName from user referral user id" + refuserid);
      }
    }
    return uname;
  }
  
  public static Locale getLocale(HttpServletRequest request)
  {
    Locale locale = null;
    try
    {
      User userAg = (User)request.getSession().getAttribute("agency_data");
      User user = (User)request.getSession().getAttribute("user_data");
      RefferalEmployee userEmp = (RefferalEmployee)request.getSession().getAttribute("employee_refferal_data");
      ApplicantUser appuser = (ApplicantUser)request.getSession().getAttribute("applicant_user_data");
      if (user != null)
      {
        locale = user.getLocale();
        logger.info("getting locale from user");
      }
      else if (userAg != null)
      {
        locale = userAg.getLocale();
        logger.info("getting locale from agency");
      }
      else if (userEmp != null)
      {
        locale = userEmp.getLocale();
        logger.info("getting locale from user employee");
      }
      else if (appuser != null)
      {
        locale = appuser.getLocale();
        logger.info("getting locale from user applicant user");
      }
      else
      {
        String refuserid = (String)request.getSession().getAttribute("refuserid");
        logger.info("getting locale from user referral user id" + refuserid);
        if (StringUtils.isNullOrEmpty(refuserid))
        {
          locale = new Locale();
          locale.setLocaleCode(Constant.getValue("default.locale.code"));
        }
        else if ((!StringUtils.isNullOrEmpty(refuserid)) && (refuserid.equals("external-jobpost")))
        {
          locale = new Locale();
          locale.setLocaleCode(Constant.getValue("default.locale.code"));
        }
        else if ((!StringUtils.isNullOrEmpty(refuserid)) && (refuserid.equals("null")))
        {
          locale = new Locale();
          locale.setLocaleCode(Constant.getValue("default.locale.code"));
        }
        else
        {
          try
          {
            new Long(refuserid).longValue();
            RefferalEmployee refEmp = RefBO.getRefferalEmployee(refuserid);
            locale = refEmp.getLocale();
          }
          catch (NumberFormatException nu)
          {
            locale = new Locale();
            locale.setLocaleCode(Constant.getValue("default.locale.code"));
          }
        }
      }
      logger.info("locale" + locale);
    }
    catch (Exception e)
    {
      logger.info("exception in getLocale" + e.getMessage());
    }
    return locale;
  }
  
  public static String getScreenCode(HttpServletRequest request, JobApplicant applicant)
  {
    String screenCode = "APPLICANT_SCREEN";
    User userAg = (User)request.getSession().getAttribute("agency_data");
    User user = (User)request.getSession().getAttribute("user_data");
    RefferalEmployee userEmp = (RefferalEmployee)request.getSession().getAttribute("employee_refferal_data");
    ApplicantUser appuser = (ApplicantUser)request.getSession().getAttribute("applicant_user_data");
    if (user != null)
    {
      screenCode = "APPLICANT_SCREEN";
      if ((!StringUtils.isNullOrEmpty(applicant.getStatus())) && (applicant.getStatus().equals("T"))) {
        screenCode = "APPLICANT_SCREEN_TALENTPOOL";
      }
    }
    else if (userAg != null)
    {
      screenCode = "APPLICANT_SCREEN_AGENCY";
    }
    else if (userEmp != null)
    {
      screenCode = "APPLICANT_SCREEN_REFERRAL";
    }
    else if (appuser != null)
    {
      screenCode = "APPLICANT_SCREEN_PROFILE";
    }
    else if (applicant.getReqId() > 0L)
    {
      screenCode = "APPLICANT_SCREEN_EXTERNAL";
    }
    else
    {
      screenCode = "APPLICANT_SCREEN_TALENTPOOL_EXTERNAL";
    }
    logger.info("getting screenCode from variableCapture" + screenCode);
    return screenCode;
  }
  
  public static String getScreenCodeWithoutApplicant(HttpServletRequest request)
  {
    String screenCode = "APPLICANT_SCREEN";
    User userAg = (User)request.getSession().getAttribute("agency_data");
    User user = (User)request.getSession().getAttribute("user_data");
    RefferalEmployee userEmp = (RefferalEmployee)request.getSession().getAttribute("employee_refferal_data");
    ApplicantUser appuser = (ApplicantUser)request.getSession().getAttribute("applicant_user_data");
    if (user != null) {
      screenCode = "APPLICANT_SCREEN";
    } else if (userAg != null) {
      screenCode = "APPLICANT_SCREEN_AGENCY";
    } else if (userEmp != null) {
      screenCode = "APPLICANT_SCREEN_REFERRAL";
    } else if (appuser != null) {
      screenCode = "APPLICANT_SCREEN_PROFILE";
    } else {
      screenCode = "APPLICANT_SCREEN_EXTERNAL";
    }
    logger.info("getting screenCode from variableCapture" + screenCode);
    return screenCode;
  }
  
  public static String getScreenCodeWorkExp(HttpServletRequest request, JobApplicant applicant)
  {
    String screenCode = "APPLICANT_SCREEN_WORK_EXP";
    User userAg = (User)request.getSession().getAttribute("agency_data");
    User user = (User)request.getSession().getAttribute("user_data");
    RefferalEmployee userEmp = (RefferalEmployee)request.getSession().getAttribute("employee_refferal_data");
    ApplicantUser appuser = (ApplicantUser)request.getSession().getAttribute("applicant_user_data");
    if (user != null)
    {
      screenCode = "APPLICANT_SCREEN_WORK_EXP";
      if ((!StringUtils.isNullOrEmpty(applicant.getStatus())) && (applicant.getStatus().equals("T"))) {
        screenCode = "APPLICANT_SCREEN_WORK_EXP_TALENTPOOL";
      }
    }
    else if (userAg != null)
    {
      screenCode = "APPLICANT_SCREEN_WORK_EXP_AGENCY";
    }
    else if (userEmp != null)
    {
      screenCode = "APPLICANT_SCREEN_WORK_EXP_REFERRAL";
    }
    else if (appuser != null)
    {
      screenCode = "APPLICANT_SCREEN_WORK_EXP_PROFILE";
    }
    else if (applicant.getReqId() > 0L)
    {
      screenCode = "APPLICANT_SCREEN_WORK_EXP_EXTERNAL";
    }
    else
    {
      screenCode = "APPLICANT_SCREEN_WORK_EXP_TALENTPOOL_EXTERNAL";
    }
    logger.info("getting screenCode from getScreenCodeWorkExpTab" + screenCode);
    return screenCode;
  }
  
  public static String getScreenCodeEducation(HttpServletRequest request, JobApplicant applicant)
  {
    String screenCode = "APPLICANT_SCREEN_EDUCATION";
    User userAg = (User)request.getSession().getAttribute("agency_data");
    User user = (User)request.getSession().getAttribute("user_data");
    RefferalEmployee userEmp = (RefferalEmployee)request.getSession().getAttribute("employee_refferal_data");
    ApplicantUser appuser = (ApplicantUser)request.getSession().getAttribute("applicant_user_data");
    if (user != null)
    {
      screenCode = "APPLICANT_SCREEN_EDUCATION";
      if ((!StringUtils.isNullOrEmpty(applicant.getStatus())) && (applicant.getStatus().equals("T"))) {
        screenCode = "APPLICANT_SCREEN_EDUCATION_TALENTPOOL";
      }
    }
    else if (userAg != null)
    {
      screenCode = "APPLICANT_SCREEN_EDUCATION_AGENCY";
    }
    else if (userEmp != null)
    {
      screenCode = "APPLICANT_SCREEN_EDUCATION_REFERRAL";
    }
    else if (appuser != null)
    {
      screenCode = "APPLICANT_SCREEN_EDUCATION_PROFILE";
    }
    else if (applicant.getReqId() > 0L)
    {
      screenCode = "APPLICANT_SCREEN_EDUCATION_EXTERNAL";
    }
    else
    {
      screenCode = "APPLICANT_SCREEN_EDUCATION_TALENTPOOL_EXTERNAL";
    }
    logger.info("getting screenCode from getScreenCodeEducation" + screenCode);
    return screenCode;
  }
  
  public static Timezone getTimeZone(HttpServletRequest request)
  {
    Timezone tz = null;
    User userAg = (User)request.getSession().getAttribute("agency_data");
    User user = (User)request.getSession().getAttribute("user_data");
    RefferalEmployee userEmp = (RefferalEmployee)request.getSession().getAttribute("employee_refferal_data");
    ApplicantUser appuser = (ApplicantUser)request.getSession().getAttribute("applicant_user_data");
    if (user != null)
    {
      tz = user.getTimezone();
      logger.info("getting timezone from user");
    }
    else if (userAg != null)
    {
      tz = userAg.getTimezone();
      logger.info("getting timezone from agency");
    }
    else if (userEmp != null)
    {
      tz = userEmp.getTimezone();
      logger.info("getting timezone from user employee");
    }
    else if (appuser != null)
    {
      tz = appuser.getTimezone();
      logger.info("getting timezone from user applicant user");
    }
    else
    {
      String refuserid = (String)request.getSession().getAttribute("refuserid");
      logger.info("getting timezone from user referral user id" + refuserid);
      if ((!StringUtils.isNullOrEmpty(refuserid)) && (!refuserid.equals("external-jobpost")))
      {
        RefferalEmployee refEmp = RefBO.getRefferalEmployee(refuserid);
        tz = refEmp.getTimezone();
        logger.info("getting timezone from user referral user id" + refuserid);
      }
      else
      {
        tz = new Timezone();
        tz.setTimezoneCode(Constant.getValue("default.timezone.code"));
      }
    }
    return tz;
  }
  
  public static User getUser(HttpServletRequest request)
  {
    User userContext = new User();
    userContext.setTimezone(getTimeZone(request));
    userContext.setLocale(getLocale(request));
    return userContext;
  }
  
  public static String generateJavaScript(Variables variable, HttpServletRequest request)
  {
    StringBuffer buffer = new StringBuffer();
    String calselecstr = "document.forms[0]." + variable.getVariableCode();
    String anchortext = variable.getVariableCode() + "x";
    String idtextstr = variable.getVariableCode() + "xx";
    String varid = variable.getVariableCode() + "yy";
    Locale locale = getLocale(request);
    String datepattern = DateUtil.getDatePatternFormat(locale);
    Date dt = variable.getDefaultValueDate();
    if ((dt != null) && (variable.getDefaultValue() == null)) {
      variable.setDefaultValue(DateUtil.convertDateToStringDate(dt, datepattern));
    }
    String value = variable.getDefaultValue() == null ? "" : variable.getDefaultValue();
    String desc = variable.getVariableDesc() == null ? "" : variable.getVariableDesc();
    String tmm = "<SCRIPT LANGUAGE=\"JavaScript\" ID=\"" + idtextstr + "\"" + ">" + "\n" + "var " + varid + " = new CalendarPopup(" + "\"" + variable.getVariableCode() + 1 + "\"" + ");" + "\n" + varid + ".showNavigationDropdowns();" + "\n" + "</SCRIPT>" + "\n" + "<INPUT TYPE=\"text\" class=\"textdynamic titleHintBox\" title=" + "\"" + desc + "\"" + " readonly=\"true\" NAME=" + "\"" + variable.getVariableCode() + "\"" + " value=" + "\"" + value + "\"" + " SIZE=25/>" + "<A HREF=\"#\" onClick=" + "\"" + varid + ".select(" + calselecstr + "," + "'" + anchortext + "'" + "," + "'" + datepattern + "'" + "); return false;" + "\"" + " " + " TITLE=" + "\"select date" + "\"" + " NAME=" + "\"" + anchortext + "\"" + " ID=" + "\"" + anchortext + "\"" + ">" + "<img src='jsp/images/calbtn.gif' width='18' height='18' alt='Calendar' ></A>" + "\n" + "<DIV ID=" + "\"" + variable.getVariableCode() + 1 + "\"" + " STYLE='position:absolute;visibility:hidden;background-color:white;layer-background-color:white;'></DIV>";
    












    logger.info(tmm);
    return tmm;
  }
  
  public static String generateJavaScriptDisabled(Variables variable, HttpServletRequest request)
  {
    StringBuffer buffer = new StringBuffer();
    String calselecstr = "document.forms[0]." + variable.getVariableCode();
    String anchortext = variable.getVariableCode() + "x";
    String idtextstr = variable.getVariableCode() + "xx";
    String varid = variable.getVariableCode() + "yy";
    Locale locale = getLocale(request);
    String datepattern = DateUtil.getDatePatternFormat(locale);
    Date dt = variable.getDefaultValueDate();
    if ((dt != null) && (variable.getDefaultValue() == null)) {
      variable.setDefaultValue(DateUtil.convertDateToStringDate(dt, datepattern));
    }
    String value = variable.getDefaultValue() == null ? "" : variable.getDefaultValue();
    
    String tmm = "<SCRIPT LANGUAGE=\"JavaScript\" ID=\"" + idtextstr + "\"" + ">" + "\n" + "var " + varid + " = new CalendarPopup(" + "\"" + variable.getVariableCode() + 1 + "\"" + ");" + "\n" + varid + ".showNavigationDropdowns();" + "\n" + "</SCRIPT>" + "\n" + "<INPUT TYPE=\"text\" disabled=\"disabled\" NAME=" + "\"" + variable.getVariableCode() + "\"" + " value=" + "\"" + value + "\"" + " SIZE=25/>" + "<A HREF=\"#\" onClick=return false;" + "\"" + " " + " TITLE=" + "\"select date" + "\"" + " NAME=" + "\"" + anchortext + "\"" + " ID=" + "\"" + anchortext + "\"" + ">" + "<img src='jsp/images/calbtn.gif' width='18' height='18' alt='Calendar' ></A>" + "\n" + "<DIV ID=" + "\"" + variable.getVariableCode() + 1 + "\"" + " STYLE='position:absolute;visibility:hidden;background-color:white;layer-background-color:white;'></DIV>";
    












    logger.info(tmm);
    return tmm;
  }
  
  public static String generateJavaScriptForReqScreen(Variables variable, HttpServletRequest request)
  {
    StringBuffer buffer = new StringBuffer();
    String calselecstr = "document.forms[0]." + variable.getVariableCode();
    String anchortext = variable.getVariableCode() + "x";
    String idtextstr = variable.getVariableCode() + "xx";
    String varid = variable.getVariableCode() + "yy";
    Locale locale = getLocale(request);
    String datepattern = DateUtil.getDatePatternFormat(locale);
    Date dt = variable.getDefaultValueDate();
    if ((dt != null) && (variable.getDefaultValue() == null)) {
      variable.setDefaultValue(DateUtil.convertDateToStringDate(dt, datepattern));
    }
    String value = variable.getDefaultValue() == null ? "" : variable.getDefaultValue();
    
    String tmm = "<SCRIPT LANGUAGE=\"JavaScript\" ID=\"" + idtextstr + "\"" + ">" + "\n" + "var " + varid + " = new CalendarPopup(" + "\"" + variable.getVariableCode() + 1 + "\"" + ");" + "\n" + varid + ".showNavigationDropdowns();" + "\n" + "</SCRIPT>" + "\n" + "<INPUT TYPE=\"text\" readonly=\"true\" NAME=" + "\"" + variable.getVariableCode() + "\"" + " value=" + "\"" + value + "\"" + " SIZE=25/>" + "<A HREF=\"#\" onClick=" + "\"" + varid + ".select(" + calselecstr + "," + "'" + anchortext + "'" + "," + "'" + datepattern + "'" + "); return false;" + "\"" + " " + " TITLE=" + "\"select date" + "\"" + " NAME=" + "\"" + anchortext + "\"" + " ID=" + "\"" + anchortext + "\"" + ">" + "<img src='jsp/images/calbtn.gif' width='18' height='18' alt='Calendar' ></A>" + "\n" + "<DIV ID=" + "\"" + variable.getVariableCode() + 1 + "\"" + " class='datepatern'></DIV>";
    












    logger.info(tmm);
    return tmm;
  }
  
  public static String generateJavaScriptForSearchScreen(Variables variable, HttpServletRequest request)
  {
    StringBuffer buffer = new StringBuffer();
    String calselecstr = "document.applicantForm." + variable.getVariableCode();
    String anchortext = variable.getVariableCode() + "x";
    String idtextstr = variable.getVariableCode() + "xx";
    String varid = variable.getVariableCode() + "yy";
    Locale locale = getLocale(request);
    String datepattern = DateUtil.getDatePatternFormat(locale);
    String value = "";
    if (!StringUtils.isNullOrEmpty(variable.getDefaultValue()))
    {
      Date dt = DateUtil.convertStringDateToDate(value, datepattern);
      value = DateUtil.convertDateToStringDate(dt, datepattern);
    }
    String tmm = "<SCRIPT LANGUAGE=\"JavaScript\" ID=\"" + idtextstr + "\"" + ">" + "\n" + "var " + varid + " = new CalendarPopup(" + "\"" + variable.getVariableCode() + 1 + "\"" + ");" + "\n" + varid + ".showNavigationDropdowns();" + "\n" + "</SCRIPT>" + "\n" + "<INPUT TYPE=\"text\" class=\"textdynamic\" readonly=\"true\" NAME=" + "\"" + variable.getVariableCode() + "\"" + " value=" + "\"" + value + "\"" + " SIZE=25/>" + "<A HREF=\"#\" onClick=" + "\"" + varid + ".select(" + calselecstr + "," + "'" + anchortext + "'" + "," + "'" + datepattern + "'" + "); return false;" + "\"" + " " + " TITLE=" + "\"select date" + "\"" + " NAME=" + "\"" + anchortext + "\"" + " ID=" + "\"" + anchortext + "\"" + ">" + "<img src='jsp/images/calbtn.gif' width='18' height='18' alt='Calendar' ></A>" + "\n" + "<DIV ID=" + "\"" + variable.getVariableCode() + 1 + "\"" + " STYLE='position:absolute;visibility:hidden;background-color:white;layer-background-color:white;'></DIV>";
    













    logger.info(tmm);
    return tmm;
  }
  
  public static String generateJavaScriptForReqSearchScreen(Variables variable, HttpServletRequest request)
  {
    StringBuffer buffer = new StringBuffer();
    String calselecstr = "document.jobRequisitionForm." + variable.getVariableCode();
    String anchortext = variable.getVariableCode() + "x";
    String idtextstr = variable.getVariableCode() + "xx";
    String varid = variable.getVariableCode() + "yy";
    Locale locale = getLocale(request);
    String datepattern = DateUtil.getDatePatternFormat(locale);
    String value = "";
    if (!StringUtils.isNullOrEmpty(variable.getDefaultValue()))
    {
      Date dt = DateUtil.convertStringDateToDate(value, datepattern);
      value = DateUtil.convertDateToStringDate(dt, datepattern);
    }
    String tmm = "<SCRIPT LANGUAGE=\"JavaScript\" ID=\"" + idtextstr + "\"" + ">" + "\n" + "var " + varid + " = new CalendarPopup(" + "\"" + variable.getVariableCode() + 1 + "\"" + ");" + "\n" + varid + ".showNavigationDropdowns();" + "\n" + "</SCRIPT>" + "\n" + "<INPUT TYPE=\"text\" class=\"textdynamic\" readonly=\"true\" NAME=" + "\"" + variable.getVariableCode() + "\"" + " value=" + "\"" + value + "\"" + " SIZE=25/>" + "<A HREF=\"#\" onClick=" + "\"" + varid + ".select(" + calselecstr + "," + "'" + anchortext + "'" + "," + "'" + datepattern + "'" + "); return false;" + "\"" + " " + " TITLE=" + "\"select date" + "\"" + " NAME=" + "\"" + anchortext + "\"" + " ID=" + "\"" + anchortext + "\"" + ">" + "<img src='jsp/images/calbtn.gif' width='18' height='18' alt='Calendar' ></A>" + "\n" + "<DIV ID=" + "\"" + variable.getVariableCode() + 1 + "\"" + " STYLE='position:absolute;visibility:hidden;background-color:white;layer-background-color:white;'></DIV>";
    













    logger.info(tmm);
    return tmm;
  }
  
  public static String generateJavaScriptForSearchScreenBetween(Variables variable, HttpServletRequest request)
  {
    StringBuffer buffer = new StringBuffer();
    String calselecstr = "document.applicantForm." + variable.getVariableCode() + "_1";
    String anchortext = variable.getVariableCode() + "x";
    String idtextstr = variable.getVariableCode() + "xx";
    String varid = variable.getVariableCode() + "yy";
    Locale locale = getLocale(request);
    String datepattern = DateUtil.getDatePatternFormat(locale);
    

    String value = "";
    if (!StringUtils.isNullOrEmpty(variable.getDefaultValue()))
    {
      Date dt = DateUtil.convertStringDateToDate(value, datepattern);
      value = DateUtil.convertDateToStringDate(dt, datepattern);
    }
    String tmm = "";
    if (!StringUtils.isNullOrEmpty(value)) {
      tmm = "<SCRIPT LANGUAGE=\"JavaScript\" ID=\"" + idtextstr + "\"" + ">" + "\n" + "var " + varid + " = new CalendarPopup(" + "\"" + variable.getVariableCode() + "_1" + "\"" + ");" + "\n" + varid + ".showNavigationDropdowns();" + "\n" + "</SCRIPT>" + "\n" + "<INPUT style=\"visibility: visible;\" TYPE=\"text\" class=\"textdynamic\" readonly=\"true\" NAME=" + "\"" + variable.getVariableCode() + "_1" + "\"" + " value=" + "\"" + value + "\"" + " SIZE=25/>" + "<A  id=\"" + variable.getVariableCode() + "_2" + "\"" + "style=\"visibility: visible;\" HREF=\"#\" onClick=" + "\"" + varid + ".select(" + calselecstr + "," + "'" + anchortext + "'" + "," + "'" + datepattern + "'" + "); return false;" + "\"" + " " + " TITLE=" + "\"select date" + "\"" + " NAME=" + "\"" + anchortext + "\"" + " ID=" + "\"" + anchortext + "\"" + ">" + "<img src='jsp/images/calbtn.gif' width='18' height='18' alt='Calendar' ></A>" + "\n" + "<DIV ID=" + "\"" + variable.getVariableCode() + "_1" + "\"" + " STYLE='position:absolute;visibility:hidden;background-color:white;layer-background-color:white;'></DIV>";
    } else {
      tmm = "<SCRIPT LANGUAGE=\"JavaScript\" ID=\"" + idtextstr + "\"" + ">" + "\n" + "var " + varid + " = new CalendarPopup(" + "\"" + variable.getVariableCode() + "_1" + "\"" + ");" + "\n" + varid + ".showNavigationDropdowns();" + "\n" + "</SCRIPT>" + "\n" + "<INPUT style=\"visibility: hidden;\" TYPE=\"text\" readonly=\"true\" NAME=" + "\"" + variable.getVariableCode() + "_1" + "\"" + " value=" + "\"" + value + "\"" + " SIZE=25/>" + "<A  id=\"" + variable.getVariableCode() + "_2" + "\"" + "style=\"visibility: hidden;\" HREF=\"#\" onClick=" + "\"" + varid + ".select(" + calselecstr + "," + "'" + anchortext + "'" + "," + "'" + datepattern + "'" + "); return false;" + "\"" + " " + " TITLE=" + "\"select date" + "\"" + " NAME=" + "\"" + anchortext + "\"" + " ID=" + "\"" + anchortext + "\"" + ">" + "<img src='jsp/images/calbtn.gif' width='18' height='18' alt='Calendar' ></A>" + "\n" + "<DIV ID=" + "\"" + variable.getVariableCode() + "_1" + "\"" + " STYLE='position:absolute;visibility:hidden;background-color:white;layer-background-color:white;'></DIV>";
    }
    logger.info(tmm);
    return tmm;
  }
  
  public static String generateJavaScriptForQuestions(Questions questions, HttpServletRequest request)
  {
    StringBuffer buffer = new StringBuffer();
    String temp = "qns_" + questions.getQuestionId() + "_qns";
    String calselecstr = "document.forms[0]." + temp;
    String anchortext = temp + "x";
    String idtextstr = temp + "xx";
    String varid = temp + "yy";
    
    String datepattern = DateUtil.dateformatstandard;
    

    String value = "";
    
    String tmm = "<SCRIPT LANGUAGE=\"JavaScript\" ID=\"" + idtextstr + "\"" + ">" + "\n" + "var " + varid + " = new CalendarPopup(" + "\"" + temp + 1 + "\"" + ");" + "\n" + varid + ".showNavigationDropdowns();" + "\n" + "</SCRIPT>" + "\n" + "<INPUT TYPE=\"text\" readonly=\"true\" NAME=" + "\"" + temp + "\"" + " value=" + "\"" + value + "\"" + " SIZE=25/>" + "<A HREF=\"#\" onClick=" + "\"" + varid + ".select(" + calselecstr + "," + "'" + anchortext + "'" + "," + "'" + datepattern + "'" + "); return false;" + "\"" + " " + " TITLE=" + "\"" + varid + ".select(" + calselecstr + "," + "'" + anchortext + "'" + "," + "'" + datepattern + "'" + "); return false;" + "\"" + " NAME=" + "\"" + anchortext + "\"" + " ID=" + "\"" + anchortext + "\"" + ">" + "<img src='jsp/images/calbtn.gif' width='18' height='18' alt='Calendar' ></A>" + "\n" + "<DIV ID=" + "\"" + temp + 1 + "\"" + " STYLE='position:absolute;visibility:hidden;background-color:white;layer-background-color:white;'></DIV>";
    














    return tmm;
  }
  
  public static long getSuperUserKey(HttpServletRequest request, JobApplicant applicant)
  {
    long superUserKey = 0L;
    User user1 = (User)request.getSession().getAttribute("user_data");
    User agency = (User)request.getSession().getAttribute("agency_data");
    ApplicantUser appuser = (ApplicantUser)request.getSession().getAttribute("applicant_user_data");
    RefferalEmployee refemp = (RefferalEmployee)request.getSession().getAttribute("employee_refferal_data");
    if (user1 != null) {
      superUserKey = user1.getSuper_user_key();
    } else if (agency != null) {
      superUserKey = agency.getSuper_user_key();
    } else if (refemp != null) {
      superUserKey = refemp.getSuper_user_key();
    } else if (appuser != null) {
      superUserKey = appuser.getApplicant().getSuper_user_key();
    } else {
      superUserKey = applicant.getSuper_user_key();
    }
    return superUserKey;
  }
  
  private static void toNewVariable(Variables variable, Variables variablenew)
  {
    variablenew.setVariableId(variable.getVariableId());
    variablenew.setVariableName(variable.getVariableName());
    variablenew.setVariableCode(variable.getVariableCode());
    variablenew.setVariableDesc(variable.getVariableDesc());
    variablenew.setVariableType(variable.getVariableType());
    variablenew.setDefaultValue(variable.getDefaultValue());
    variablenew.setDefaultValueDate(variable.getDefaultValueDate());
    variablenew.setStatus(variable.getStatus());
    variablenew.setCreatedBy(variable.getCreatedBy());
    variablenew.setCreatedDate(variable.getCreatedDate());
    variablenew.setUpdatedBy(variable.getUpdatedBy());
    variablenew.setUpdatedDate(variable.getUpdatedDate());
    variablenew.setListData(variable.getListData());
    variablenew.setSuper_user_key(variable.getSuper_user_key());
  }
}
