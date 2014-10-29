package com.action;

import com.bean.User;
import com.bean.filter.BusinessCriteria;
import com.bean.filter.BusinessFilterConditions;
import com.bean.filter.ScreenFields;
import com.bean.lov.FormVariablesMap;
import com.bean.lov.KeyValue;
import com.bean.lov.VariableListData;
import com.bean.lov.Variables;
import com.bo.ApplicantBO;
import com.bo.BOFactory;
import com.bo.BusinessRuleBO;
import com.bo.LovBO;
import com.bo.LovTXBO;
import com.bo.VariableBO;
import com.common.Common;
import com.common.ValidationException;
import com.form.BusinessRuleForm;
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
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class BusinessRuleAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(BusinessRuleAction.class);
  
  public ActionForward filterList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    User user1 = (User)request.getSession().getAttribute("user_data");
    BusinessRuleForm businessRuleForm = (BusinessRuleForm)form;
    List screenfieldList = new ArrayList();
    screenfieldList = BOFactory.getApplicantBO().getScreenFieldsDetails("APPLICANT_SCREEN", user1.getSuper_user_key());
    List screenfieldsList = getScreenFiledsKeyValue(screenfieldList, user1);
    

    businessRuleForm.setTypeList(Constant.getVariableTypeList(user1));
    businessRuleForm.setScreenfieldsList(screenfieldsList);
    request.setAttribute("applicantFilterHome", "applicantFilter");
    return mapping.findForward("filterList");
  }
  
  public ActionForward searchfilterList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    User user1 = (User)request.getSession().getAttribute("user_data");
    BusinessRuleForm businessRuleForm = (BusinessRuleForm)form;
    List screenfieldList = new ArrayList();
    screenfieldList = BOFactory.getApplicantBO().getScreenFieldsDetails("APPLICANT_SCREEN", user1.getSuper_user_key());
    List screenfieldsList = getScreenFiledsKeyValue(screenfieldList, user1);
    

    businessRuleForm.setTypeList(Constant.getVariableTypeList(user1));
    businessRuleForm.setScreenfieldsList(screenfieldsList);
    request.setAttribute("applicantFilterHome", "applicantFilter");
    return mapping.findForward("filterList");
  }
  
  public ActionForward searchApplicantGroupfilterList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    User user1 = (User)request.getSession().getAttribute("user_data");
    BusinessRuleForm businessRuleForm = (BusinessRuleForm)form;
    List screenfieldList = new ArrayList();
    screenfieldList = BOFactory.getApplicantBO().getScreenFieldsDetails("APPLICANT_SCREEN", user1.getSuper_user_key());
    List screenfieldsList = getScreenFiledsKeyValue(screenfieldList, user1);
    

    businessRuleForm.setTypeList(Constant.getVariableTypeList(user1));
    businessRuleForm.setScreenfieldsList(screenfieldsList);
    request.setAttribute("applicantFilterHome", "applicantFilterGroup");
    return mapping.findForward("applicantFilters");
  }
  
  public ActionForward applicantFilters(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside applicantFilters method ...");
    BusinessRuleForm businessRuleForm = (BusinessRuleForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    
    List screenfieldList = new ArrayList();
    screenfieldList = BOFactory.getApplicantBO().getScreenFieldsDetails("APPLICANT_SCREEN", user1.getSuper_user_key());
    List screenfieldsList = getScreenFiledsKeyValue(screenfieldList, user1);
    

    businessRuleForm.setTypeList(Constant.getVariableTypeList(user1));
    businessRuleForm.setScreenfieldsList(screenfieldsList);
    request.setAttribute("applicantFilterHome", "applicantFilterGroup");
    return mapping.findForward("applicantFilters");
  }
  
  public ActionForward createFilter(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside createFilter");
    BusinessRuleForm businessRuleForm = (BusinessRuleForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    List screenfieldList = new ArrayList();
    screenfieldList = BOFactory.getApplicantBO().getScreenFieldsDetails("APPLICANT_SCREEN", user1.getSuper_user_key());
    logger.info("screenFields size : " + screenfieldList.size());
    

    List criteriaList = Constant.getFilterCriterias(null);
    logger.info("criteriaList : " + criteriaList.size());
    businessRuleForm.setFilterCriteriasList(criteriaList);
    
    List screenfieldsList = getScreenFiledsKeyValue(screenfieldList, user1);
    
    List customFieldsList = getCustomVaribalesKeyValue(user1);
    

    businessRuleForm.setScreenfieldsList(screenfieldsList);
    businessRuleForm.setCustomVariablesList(customFieldsList);
    List soucetypeList = BOFactory.getLovBO().getAllResumeSourceTypes();
    logger.info("soucetypeList : " + soucetypeList);
    businessRuleForm.setSourceTypeList(soucetypeList);
    List countryList = BOFactory.getLovBO().getAllCountries();
    businessRuleForm.setCountryList(countryList);
    List stateList = BOFactory.getLovBO().getStateList();
    businessRuleForm.setStateList(stateList);
    List skilllist = BOFactory.getLovTXBO().getTechnicalSkillList(user1.getSuper_user_key());
    businessRuleForm.setLovList(skilllist);
    logger.info(">>>> 1");
    List newVariableListData = new ArrayList();
    businessRuleForm.setVaribalesListdata(newVariableListData);
    

    return mapping.findForward("createFilter");
  }
  
  private List getScreenFiledsKeyValue(List screenfieldList, User user1)
  {
    List screenfieldsList = new ArrayList();
    if (screenfieldList.size() > 0) {
      for (int i = 0; i < screenfieldList.size(); i++)
      {
        ScreenFields screenFields = (ScreenFields)screenfieldList.get(i);
        KeyValue keyvalue = new KeyValue();
        
        String resourcestr = "aquisition.applicant." + screenFields.getFieldcode();
        
        keyvalue.setKey(screenFields.getFieldcode());
        
        String value = Constant.getResourceStringValue(resourcestr, user1.getLocale());
        
        keyvalue.setValue(value);
        
        screenfieldsList.add(keyvalue);
      }
    }
    return screenfieldsList;
  }
  
  private List getCustomVaribalesKeyValue(User user1)
  {
    List customVList = BOFactory.getVariableBO().getAllFormVariablesListForApplicant(user1.getSuper_user_key());
    List customList = new ArrayList();
    if ((customVList != null) && (customVList.size() > 0)) {
      for (int i = 0; i < customVList.size(); i++)
      {
        FormVariablesMap fvaiablemap = (FormVariablesMap)customVList.get(i);
        if (fvaiablemap != null)
        {
          Variables variable = fvaiablemap.getVariable();
          if (variable != null)
          {
            KeyValue keyvalue = new KeyValue();
            
            keyvalue.setKey(variable.getVariableCode());
            
            keyvalue.setValue(variable.getVariableName());
            
            customList.add(keyvalue);
          }
        }
      }
    }
    return customList;
  }
  
  private List getVaribaleslistdata(User user1)
  {
    List customVList = BOFactory.getVariableBO().getAllFormVariablesListForApplicant(user1.getSuper_user_key());
    List variableListdata = new ArrayList();
    if ((customVList != null) && (customVList.size() > 0)) {
      for (int i = 0; i < customVList.size(); i++)
      {
        FormVariablesMap fvaiablemap = (FormVariablesMap)customVList.get(i);
        if (fvaiablemap != null)
        {
          Variables variable = fvaiablemap.getVariable();
          if (variable != null) {
            variableListdata.add(variable);
          }
        }
      }
    }
    return variableListdata;
  }
  
  private String getVariableType(List screenfieldList, String variableName, long super_user_key)
  {
    String variableType = "";
    logger.info("variableName" + variableName);
    for (int i = 0; i < screenfieldList.size(); i++)
    {
      ScreenFields screenFields = (ScreenFields)screenfieldList.get(i);
      if (screenFields.getFieldcode().equals(variableName)) {
        variableType = screenFields.getFieldtype();
      }
    }
    logger.info("variableType" + variableType);
    if (StringUtils.isNullOrEmpty(variableType))
    {
      Variables variable = BOFactory.getVariableBO().getVariableByVaribleCode(variableName, super_user_key);
      
      variableType = variable.getVariableType();
    }
    logger.info("variableType" + variableType);
    return variableType;
  }
  
  private String getVariableOrigin(List screenfieldList, String variableName)
  {
    String variableOrigin = "CUSTOM";
    for (int i = 0; i < screenfieldList.size(); i++)
    {
      ScreenFields screenFields = (ScreenFields)screenfieldList.get(i);
      if (screenFields.getFieldcode().equals(variableName)) {
        variableOrigin = "SCREEN";
      }
    }
    return variableOrigin;
  }
  
  public ActionForward editApplicantFilter(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside editApplicantFilter");
    BusinessRuleForm businessRuleForm = (BusinessRuleForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    String filterid = request.getParameter("fltid");
    List screenfieldList = new ArrayList();
    screenfieldList = BOFactory.getApplicantBO().getScreenFieldsDetails("APPLICANT_SCREEN", user1.getSuper_user_key());
    logger.info("screenFields size : " + screenfieldList.size());
    
    BusinessCriteria filtercondition1 = BOFactory.getBusinessRuleBO().getApplicantGroupFilterCondition(new Long(filterid).longValue());
    businessRuleForm.setName(filtercondition1.getName());
    businessRuleForm.setDesc(filtercondition1.getDesc());
    businessRuleForm.setVariableName(filtercondition1.getVariableName());
    businessRuleForm.setVariableType(filtercondition1.getVariableType());
    businessRuleForm.setVariableOrigin(filtercondition1.getVariableOrigin());
    businessRuleForm.setVariableCriteria(filtercondition1.getVariableCriteria());
    businessRuleForm.setFilterValue1(filtercondition1.getFilterValue1());
    businessRuleForm.setFilterValue2(filtercondition1.getFilterValue2());
    businessRuleForm.setBusinessCriteraId(filtercondition1.getBusinessCriteraId());
    


    List screenfieldsList = getScreenFiledsKeyValue(screenfieldList, user1);
    
    businessRuleForm.setScreenfieldsList(screenfieldsList);
    List customFieldsList = getCustomVaribalesKeyValue(user1);
    businessRuleForm.setCustomVariablesList(customFieldsList);
    
    logger.info("businessRuleForm.getVariableType() >> " + businessRuleForm.getVariableType());
    List criteriaList = Constant.getFilterCriterias(businessRuleForm.getVariableType());
    logger.info("criteriaList : " + criteriaList.size());
    businessRuleForm.setFilterCriteriasList(criteriaList);
    
    List filtersList = BOFactory.getBusinessRuleBO().getApplicantFiltersList(filtercondition1.getBusinessCriteraId());
    List applicantfilterlist = new ArrayList();
    
    BusinessCriteria BusinessCriteria1 = (BusinessCriteria)filtersList.get(0);
    Set<BusinessFilterConditions> bconditionList = new HashSet();
    bconditionList = BusinessCriteria1.getBusinessConditions();
    logger.info("bconditionList size  : " + bconditionList.size());
    Iterator itr = bconditionList.iterator();
    while (itr.hasNext())
    {
      BusinessFilterConditions bus1 = (BusinessFilterConditions)itr.next();
      applicantfilterlist.add(bus1);
    }
    businessRuleForm.fromValueBusinessCriteria(BusinessCriteria1, request);
    businessRuleForm.setFiltersList(applicantfilterlist);
    
    List soucetypeList = BOFactory.getLovBO().getAllResumeSourceTypes();
    logger.info("soucetypeList : " + soucetypeList);
    businessRuleForm.setSourceTypeList(soucetypeList);
    List countryList = BOFactory.getLovBO().getAllCountries();
    businessRuleForm.setCountryList(countryList);
    List stateList = BOFactory.getLovBO().getStateList();
    businessRuleForm.setStateList(stateList);
    List skilllist = BOFactory.getLovTXBO().getTechnicalSkillList(user1.getSuper_user_key());
    businessRuleForm.setLovList(skilllist);
    return mapping.findForward("createApplicantFilter");
  }
  
  public ActionForward editFilter(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside editFilter");
    BusinessRuleForm businessRuleForm = (BusinessRuleForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    List screenfieldList = new ArrayList();
    String filterid = request.getParameter("fltid");
    screenfieldList = BOFactory.getApplicantBO().getScreenFieldsDetails("APPLICANT_SCREEN", user1.getSuper_user_key());
    logger.info("screenFields size : " + screenfieldList.size());
    
    List screenfieldsList = getScreenFiledsKeyValue(screenfieldList, user1);
    
    businessRuleForm.setScreenfieldsList(screenfieldsList);
    
    BusinessFilterConditions filtercondition1 = BOFactory.getBusinessRuleBO().getFilterCondition(new Long(filterid).longValue());
    businessRuleForm.setName(filtercondition1.getName());
    businessRuleForm.setDesc(filtercondition1.getDesc());
    businessRuleForm.setVariableName(filtercondition1.getVariableName());
    businessRuleForm.setVariableType(filtercondition1.getVariableType());
    businessRuleForm.setVariableOrigin(filtercondition1.getVariableOrigin());
    businessRuleForm.setVariableCriteria(filtercondition1.getFilterCriteria());
    businessRuleForm.setFilterValue1(filtercondition1.getFilterValue1());
    businessRuleForm.setFilterValue2(filtercondition1.getFilterValue2());
    businessRuleForm.setBusinessCriteraId(filtercondition1.getFilterConditionId());
    
    List customFieldsList = getCustomVaribalesKeyValue(user1);
    businessRuleForm.setCustomVariablesList(customFieldsList);
    businessRuleForm.fromValue(filtercondition1, request);
    
    List criteriaList = Constant.getFilterCriterias(businessRuleForm.getVariableType());
    logger.info("criteriaList : " + criteriaList.size());
    businessRuleForm.setFilterCriteriasList(criteriaList);
    List soucetypeList = BOFactory.getLovBO().getAllResumeSourceTypes();
    logger.info("soucetypeList : " + soucetypeList);
    businessRuleForm.setSourceTypeList(soucetypeList);
    List countryList = BOFactory.getLovBO().getAllCountries();
    businessRuleForm.setCountryList(countryList);
    List stateList = BOFactory.getLovBO().getStateList();
    businessRuleForm.setStateList(stateList);
    List skilllist = BOFactory.getLovTXBO().getTechnicalSkillList(user1.getSuper_user_key());
    businessRuleForm.setLovList(skilllist);
    

    List newVariableListData = new ArrayList();
    Variables variable = BOFactory.getVariableBO().getVariablesDetails(String.valueOf(businessRuleForm.getVariableId()));
    if ((variable != null) && 
      (!StringUtils.isNullOrEmpty(variable.getVariableType())) && (variable.getVariableType().equals("list")))
    {
      Set lovset = variable.getListData();
      if ((lovset != null) && (lovset.size() > 0))
      {
        Iterator itr = lovset.iterator();
        while (itr.hasNext())
        {
          VariableListData vdata = (VariableListData)itr.next();
          newVariableListData.add(vdata);
        }
      }
    }
    logger.info("newVariableListData by code >>>> " + newVariableListData.size());
    businessRuleForm.setVaribalesListdata(newVariableListData);
    return mapping.findForward("createFilter");
  }
  
  public ActionForward createApplicantFilter(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside createApplicantFilter");
    BusinessRuleForm businessRuleForm = (BusinessRuleForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    List screenfieldList = new ArrayList();
    screenfieldList = BOFactory.getApplicantBO().getScreenFieldsDetails("APPLICANT_SCREEN", user1.getSuper_user_key());
    logger.info("screenFields size : " + screenfieldList.size());
    

    List criteriaList = Constant.getFilterCriterias(null);
    logger.info("criteriaList : " + criteriaList.size());
    businessRuleForm.setFilterCriteriasList(criteriaList);
    
    List screenfieldsList = getScreenFiledsKeyValue(screenfieldList, user1);
    
    List customFieldsList = getCustomVaribalesKeyValue(user1);
    
    businessRuleForm.setScreenfieldsList(screenfieldsList);
    
    businessRuleForm.setCustomVariablesList(customFieldsList);
    
    businessRuleForm.setFiltersList(new ArrayList());
    
    businessRuleForm.setScreenfieldsList(screenfieldsList);
    List soucetypeList = BOFactory.getLovBO().getAllResumeSourceTypes();
    logger.info("soucetypeList : " + soucetypeList);
    businessRuleForm.setSourceTypeList(soucetypeList);
    List countryList = BOFactory.getLovBO().getAllCountries();
    businessRuleForm.setCountryList(countryList);
    List stateList = BOFactory.getLovBO().getStateList();
    businessRuleForm.setStateList(stateList);
    List skilllist = BOFactory.getLovTXBO().getTechnicalSkillList(user1.getSuper_user_key());
    businessRuleForm.setLovList(skilllist);
    return mapping.findForward("createApplicantFilter");
  }
  
  public ActionForward saveApplicantFilter(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside saveApplicantFilter");
    BusinessRuleForm businessRuleForm = (BusinessRuleForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    List screenfieldList = new ArrayList();
    screenfieldList = BOFactory.getApplicantBO().getScreenFieldsDetails("APPLICANT_SCREEN", user1.getSuper_user_key());
    
    BusinessCriteria bcriteria = new BusinessCriteria();
    String variableOrigin = "";
    if (!StringUtils.isNullOrEmpty(businessRuleForm.getVariableName()))
    {
      String variableType = "";
      variableType = getVariableType(screenfieldList, businessRuleForm.getVariableName(), user1.getSuper_user_key());
      
      bcriteria.setVariableType(variableType);
      
      bcriteria.setVariableName(businessRuleForm.getVariableName());
      

      variableOrigin = getVariableOrigin(screenfieldList, businessRuleForm.getVariableName());
      bcriteria.setVariableOrigin(variableOrigin);
      bcriteria.setVariableCriteria(businessRuleForm.getVariableCriteria());
      bcriteria.setFilterValue1(businessRuleForm.getFilterValue1());
      bcriteria.setFilterValue2(businessRuleForm.getFilterValue2());
      

      List criteriaList = Constant.getFilterCriterias(variableType);
      logger.info("criteriaList : " + criteriaList.size());
      businessRuleForm.setFilterCriteriasList(criteriaList);
    }
    else
    {
      List criteriaList = Constant.getFilterCriterias(null);
      logger.info("criteriaList : " + criteriaList.size());
      businessRuleForm.setFilterCriteriasList(criteriaList);
    }
    bcriteria.setName(businessRuleForm.getName());
    bcriteria.setDesc(businessRuleForm.getDesc());
    bcriteria.setCreatedBy(user1.getUserName());
    bcriteria.setCreatedDate(new Date());
    bcriteria.setStatus("A");
    bcriteria.setType("DRAFT");
    bcriteria.setSuper_user_key(user1.getSuper_user_key());
    
    businessRuleForm.toValueBusinessCriteria(bcriteria, request);
    if ((!StringUtils.isNullOrEmpty(variableOrigin)) && (variableOrigin.equals("CUSTOM")))
    {
      Variables variable = BOFactory.getVariableBO().getVariableByVaribleCode(businessRuleForm.getVariableName(), user1.getSuper_user_key());
      bcriteria.setVariableId(variable.getVariableId());
    }
    logger.info("inside saveApplicantFilter1");
    Set<BusinessFilterConditions> filterset = new HashSet();
    setFiltersList(filterset, request);
    bcriteria.setBusinessConditions(filterset);
    
    BOFactory.getBusinessRuleBO().saveBusinessCriteria(bcriteria);
    logger.info("inside saveApplicantFilter2" + bcriteria.getBusinessCriteraId());
    businessRuleForm.setBusinessCriteraId(bcriteria.getBusinessCriteraId());
    
    bcriteria = BOFactory.getBusinessRuleBO().getBusinessCriteria(bcriteria.getBusinessCriteraId());
    businessRuleForm.setVariableCriteria(bcriteria.getVariableCriteria());
    
    Set<BusinessFilterConditions> filters = bcriteria.getBusinessConditions();
    List filterList = new ArrayList();
    if (filters != null)
    {
      Iterator<BusinessFilterConditions> itr = filters.iterator();
      while (itr.hasNext()) {
        filterList.add(itr.next());
      }
    }
    businessRuleForm.fromValueBusinessCriteria(bcriteria, request);
    businessRuleForm.setFiltersList(filterList);
    
    List screenfieldsList = getScreenFiledsKeyValue(screenfieldList, user1);
    
    businessRuleForm.setScreenfieldsList(screenfieldsList);
    List customFieldsList = getCustomVaribalesKeyValue(user1);
    businessRuleForm.setCustomVariablesList(customFieldsList);
    List soucetypeList = BOFactory.getLovBO().getAllResumeSourceTypes();
    logger.info("soucetypeList : " + soucetypeList);
    businessRuleForm.setSourceTypeList(soucetypeList);
    List countryList = BOFactory.getLovBO().getAllCountries();
    businessRuleForm.setCountryList(countryList);
    List stateList = BOFactory.getLovBO().getStateList();
    businessRuleForm.setStateList(stateList);
    List skilllist = BOFactory.getLovTXBO().getTechnicalSkillList(user1.getSuper_user_key());
    businessRuleForm.setLovList(skilllist);
    
    request.setAttribute("saveapplicationfilter", "yes");
    return mapping.findForward("createApplicantFilter");
  }
  
  public ActionForward updateApplicantFilter(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside updateApplicantFilter  .... ");
    BusinessRuleForm businessRuleForm = (BusinessRuleForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    String fltid = request.getParameter("fltid");
    List screenfieldList = new ArrayList();
    screenfieldList = BOFactory.getApplicantBO().getScreenFieldsDetails("APPLICANT_SCREEN", user1.getSuper_user_key());
    
    BusinessCriteria bcriteria = BOFactory.getBusinessRuleBO().getBusinessCriteria(new Long(fltid).longValue());
    String variableOrigin = "";
    if (!StringUtils.isNullOrEmpty(businessRuleForm.getVariableName()))
    {
      String variableType = "";
      
      variableType = getVariableType(screenfieldList, businessRuleForm.getVariableName(), user1.getSuper_user_key());
      
      bcriteria.setVariableType(variableType);
      bcriteria.setVariableName(businessRuleForm.getVariableName());
      
      variableOrigin = getVariableOrigin(screenfieldList, businessRuleForm.getVariableName());
      bcriteria.setVariableOrigin(variableOrigin);
      bcriteria.setVariableCriteria(businessRuleForm.getVariableCriteria());
      bcriteria.setFilterValue1(businessRuleForm.getFilterValue1());
      bcriteria.setFilterValue2(businessRuleForm.getFilterValue2());
      

      List criteriaList = Constant.getFilterCriterias(variableType);
      logger.info("criteriaList : " + criteriaList.size());
      businessRuleForm.setFilterCriteriasList(criteriaList);
    }
    else
    {
      logger.info("businessRuleForm.getVariableName() : " + businessRuleForm.getVariableName());
      bcriteria.setVariableName("");
      bcriteria.setFilterValue1("");
      bcriteria.setFilterValue2("");
      bcriteria.setVariableCriteria("");
      bcriteria.setVariableType("");
      List criteriaList = Constant.getFilterCriterias(null);
      logger.info("criteriaList : " + criteriaList.size());
      businessRuleForm.setFilterCriteriasList(criteriaList);
    }
    bcriteria.setName(businessRuleForm.getName());
    bcriteria.setDesc(businessRuleForm.getDesc());
    bcriteria.setCreatedBy(user1.getUserName());
    bcriteria.setCreatedDate(new Date());
    bcriteria.setStatus("A");
    bcriteria.setType("DRAFT");
    
    businessRuleForm.toValueBusinessCriteria(bcriteria, request);
    if ((!StringUtils.isNullOrEmpty(variableOrigin)) && (variableOrigin.equals("CUSTOM")))
    {
      Variables variable = BOFactory.getVariableBO().getVariableByVaribleCode(businessRuleForm.getVariableName(), user1.getSuper_user_key());
      bcriteria.setVariableId(variable.getVariableId());
    }
    if ((!StringUtils.isNullOrEmpty(businessRuleForm.getVariableCriteria())) && (businessRuleForm.getVariableCriteria().equals("NOT_NULL"))) {
      bcriteria.setFilterValue1("");
    }
    logger.info("inside saveApplicantFilter1");
    Set<BusinessFilterConditions> filterset = new HashSet();
    setFiltersList(filterset, request);
    bcriteria.setBusinessConditions(filterset);
    
    BOFactory.getBusinessRuleBO().updateBusinessCriteria(bcriteria);
    logger.info("inside saveApplicantFilter2" + bcriteria.getBusinessCriteraId());
    businessRuleForm.setBusinessCriteraId(bcriteria.getBusinessCriteraId());
    
    bcriteria = BOFactory.getBusinessRuleBO().getBusinessCriteria(bcriteria.getBusinessCriteraId());
    businessRuleForm.setVariableCriteria(bcriteria.getVariableCriteria());
    
    Set<BusinessFilterConditions> filters = bcriteria.getBusinessConditions();
    List filterList = new ArrayList();
    if (filters != null)
    {
      Iterator<BusinessFilterConditions> itr = filters.iterator();
      while (itr.hasNext()) {
        filterList.add(itr.next());
      }
    }
    businessRuleForm.fromValueBusinessCriteria(bcriteria, request);
    
    businessRuleForm.setFiltersList(filterList);
    
    List screenfieldsList = getScreenFiledsKeyValue(screenfieldList, user1);
    
    businessRuleForm.setScreenfieldsList(screenfieldsList);
    List customFieldsList = getCustomVaribalesKeyValue(user1);
    businessRuleForm.setCustomVariablesList(customFieldsList);
    List soucetypeList = BOFactory.getLovBO().getAllResumeSourceTypes();
    logger.info("soucetypeList : " + soucetypeList);
    businessRuleForm.setSourceTypeList(soucetypeList);
    List countryList = BOFactory.getLovBO().getAllCountries();
    businessRuleForm.setCountryList(countryList);
    List stateList = BOFactory.getLovBO().getStateList();
    businessRuleForm.setStateList(stateList);
    List skilllist = BOFactory.getLovTXBO().getTechnicalSkillList(user1.getSuper_user_key());
    businessRuleForm.setLovList(skilllist);
    request.setAttribute("updatebusinessrule", "yes");
    return mapping.findForward("createApplicantFilter");
  }
  
  public ActionForward manageApplicantFilterSrc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside manageApplicantFilterSrc");
    BusinessRuleForm businessRuleForm = (BusinessRuleForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    String reqId = request.getParameter("reqId");
    String type = request.getParameter("type");
    if ((!StringUtils.isNullOrEmpty(type)) && (type.equals("TEMPLATE")))
    {
      List<BusinessCriteria> bclist = BOFactory.getBusinessRuleBO().getBusinessCriteriasByTemplate(new Long(reqId).longValue());
      
      businessRuleForm.setFilterCriteriasList(bclist);
      businessRuleForm.setFrom_type("TEMPLATE");
    }
    else if ((!StringUtils.isNullOrEmpty(type)) && (type.equals("REQUISITION")))
    {
      List<BusinessCriteria> bclist = BOFactory.getBusinessRuleBO().getBusinessCriteriasByRequistion(new Long(reqId).longValue());
      
      businessRuleForm.setFilterCriteriasList(bclist);
      businessRuleForm.setFrom_type("REQUISITION");
    }
    else if ((!StringUtils.isNullOrEmpty(type)) && (type.equals("ALL_APPLICANTS")))
    {
      List<BusinessCriteria> bclist = BOFactory.getBusinessRuleBO().getBusinessCriteriasByType(type);
      
      businessRuleForm.setFilterCriteriasList(bclist);
      businessRuleForm.setFrom_type("ALL_APPLICANTS");
    }
    businessRuleForm.setIdvalue(new Long(reqId).longValue());
    
    return mapping.findForward("manageApplicantFilter");
  }
  
  public ActionForward manageApplicantFilter(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside manageApplicantFilter");
    BusinessRuleForm businessRuleForm = (BusinessRuleForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    String reqId = request.getParameter("reqId");
    String type = request.getParameter("type");
    

    logger.info("inside manageApplicantFilter1");
    List<KeyValue> appFilterList = new ArrayList();
    setApplicantFiltersList(appFilterList, request);
    try
    {
      BOFactory.getBusinessRuleBO().manageApplicantFilter(new Long(reqId).longValue(), appFilterList, type, user1);
      logger.info("inside manageApplicantFilter2");
      if ((!StringUtils.isNullOrEmpty(type)) && (type.equals("TEMPLATE")))
      {
        List<BusinessCriteria> bclist = BOFactory.getBusinessRuleBO().getBusinessCriteriasByTemplate(new Long(reqId).longValue());
        
        businessRuleForm.setFilterCriteriasList(bclist);
        businessRuleForm.setFrom_type("TEMPLATE");
      }
      else if ((!StringUtils.isNullOrEmpty(type)) && (type.equals("REQUISITION")))
      {
        List<BusinessCriteria> bclist = BOFactory.getBusinessRuleBO().getBusinessCriteriasByRequistion(new Long(reqId).longValue());
        
        businessRuleForm.setFilterCriteriasList(bclist);
        businessRuleForm.setFrom_type("REQUISITION");
      }
      else if ((!StringUtils.isNullOrEmpty(type)) && (type.equals("ALL_APPLICANTS")))
      {
        List<BusinessCriteria> bclist = BOFactory.getBusinessRuleBO().getBusinessCriteriasByType(type);
        
        businessRuleForm.setFilterCriteriasList(bclist);
        businessRuleForm.setFrom_type("ALL_APPLICANTS");
      }
      businessRuleForm.setIdvalue(new Long(reqId).longValue());
      request.setAttribute("updatedapplicantfilters", "yes");
    }
    catch (Exception e)
    {
      e.printStackTrace();
      logger.info("Error manageApplicantFilter", e);
      logger.error(e);
      String msg = Constant.getResourceStringValue("server.error", user1.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
      return mapping.findForward("exception");
    }
    return mapping.findForward("manageApplicantFilter");
  }
  
  public ActionForward saveFilter(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside saveFilter method");
    BusinessRuleForm businessRuleForm = (BusinessRuleForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    
    List screenfieldList = new ArrayList();
    screenfieldList = BOFactory.getApplicantBO().getScreenFieldsDetails("APPLICANT_SCREEN", user1.getSuper_user_key());
    logger.info("screenFields size : " + screenfieldList.size());
    

    String variableType = getVariableType(screenfieldList, businessRuleForm.getVariableName(), user1.getSuper_user_key());
    

    List screenfieldsList = getScreenFiledsKeyValue(screenfieldList, user1);
    
    businessRuleForm.setScreenfieldsList(screenfieldsList);
    
    BusinessFilterConditions filtercondition1 = new BusinessFilterConditions();
    filtercondition1.setName(businessRuleForm.getName());
    filtercondition1.setDesc(businessRuleForm.getDesc());
    filtercondition1.setVariableName(businessRuleForm.getVariableName());
    filtercondition1.setVariableType(variableType);
    String variableOrigin = getVariableOrigin(screenfieldList, businessRuleForm.getVariableName());
    filtercondition1.setVariableOrigin(variableOrigin);
    filtercondition1.setFilterCriteria(businessRuleForm.getVariableCriteria());
    filtercondition1.setFilterValue1(businessRuleForm.getFilterValue1());
    filtercondition1.setFilterValue2(businessRuleForm.getFilterValue2());
    
    businessRuleForm.toValue(filtercondition1, request);
    if ((!StringUtils.isNullOrEmpty(variableOrigin)) && (variableOrigin.equals("CUSTOM")))
    {
      Variables variable = BOFactory.getVariableBO().getVariableByVaribleCode(businessRuleForm.getVariableName(), user1.getSuper_user_key());
      filtercondition1.setVariableId(variable.getVariableId());
    }
    filtercondition1.setSuper_user_key(user1.getSuper_user_key());
    
    BOFactory.getBusinessRuleBO().saveFilterCondition(filtercondition1);
    
    List customFieldsList = getCustomVaribalesKeyValue(user1);
    businessRuleForm.setCustomVariablesList(customFieldsList);
    
    List criteriaList = Constant.getFilterCriterias(variableType);
    businessRuleForm.fromValue(filtercondition1, request);
    
    logger.info("criteriaList : " + criteriaList.size());
    businessRuleForm.setFilterCriteriasList(criteriaList);
    businessRuleForm.setBusinessCriteraId(filtercondition1.getFilterConditionId());
    List soucetypeList = BOFactory.getLovBO().getAllResumeSourceTypes();
    logger.info("soucetypeList : " + soucetypeList);
    businessRuleForm.setSourceTypeList(soucetypeList);
    List countryList = BOFactory.getLovBO().getAllCountries();
    businessRuleForm.setCountryList(countryList);
    List stateList = BOFactory.getLovBO().getStateList();
    businessRuleForm.setStateList(stateList);
    List skilllist = BOFactory.getLovTXBO().getTechnicalSkillList(user1.getSuper_user_key());
    businessRuleForm.setLovList(skilllist);
    List newVariableListData = new ArrayList();
    Variables variable = BOFactory.getVariableBO().getVariablesDetails(String.valueOf(businessRuleForm.getVariableId()));
    if ((variable != null) && 
      (!StringUtils.isNullOrEmpty(variable.getVariableType())) && (variable.getVariableType().equals("list")))
    {
      Set lovset = variable.getListData();
      if ((lovset != null) && (lovset.size() > 0))
      {
        Iterator itr = lovset.iterator();
        while (itr.hasNext())
        {
          VariableListData vdata = (VariableListData)itr.next();
          newVariableListData.add(vdata);
        }
      }
    }
    logger.info("newVariableListData by code >>>> " + newVariableListData.size());
    businessRuleForm.setVaribalesListdata(newVariableListData);
    
    request.setAttribute("savebusinessrule", "yes");
    return mapping.findForward("createFilter");
  }
  
  public ActionForward updateFilter(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside updateFilter method");
    BusinessRuleForm businessRuleForm = (BusinessRuleForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    String filterid = request.getParameter("fltid");
    List screenfieldList = new ArrayList();
    screenfieldList = BOFactory.getApplicantBO().getScreenFieldsDetails("APPLICANT_SCREEN", user1.getSuper_user_key());
    logger.info("screenFields size : " + screenfieldList.size());
    


    String variableType = getVariableType(screenfieldList, businessRuleForm.getVariableName(), user1.getSuper_user_key());
    

    List screenfieldsList = getScreenFiledsKeyValue(screenfieldList, user1);
    
    businessRuleForm.setScreenfieldsList(screenfieldsList);
    businessRuleForm.setVariableType(variableType);
    BusinessFilterConditions filtercondition1 = BOFactory.getBusinessRuleBO().getFilterCondition(new Long(filterid).longValue());
    filtercondition1.setName(businessRuleForm.getName());
    filtercondition1.setDesc(businessRuleForm.getDesc());
    filtercondition1.setVariableName(businessRuleForm.getVariableName());
    filtercondition1.setVariableType(variableType);
    String variableOrigin = getVariableOrigin(screenfieldList, businessRuleForm.getVariableName());
    filtercondition1.setVariableOrigin(variableOrigin);
    filtercondition1.setFilterCriteria(businessRuleForm.getVariableCriteria());
    filtercondition1.setFilterValue1(businessRuleForm.getFilterValue1());
    filtercondition1.setFilterValue2(businessRuleForm.getFilterValue2());
    
    businessRuleForm.toValue(filtercondition1, request);
    if ((!StringUtils.isNullOrEmpty(businessRuleForm.getVariableCriteria())) && (businessRuleForm.getVariableCriteria().equals("NOT_NULL"))) {
      filtercondition1.setFilterValue1("");
    }
    if ((!StringUtils.isNullOrEmpty(variableOrigin)) && (variableOrigin.equals("CUSTOM")))
    {
      Variables variable = BOFactory.getVariableBO().getVariableByVaribleCode(businessRuleForm.getVariableName(), user1.getSuper_user_key());
      filtercondition1.setVariableId(variable.getVariableId());
    }
    BOFactory.getBusinessRuleBO().updateFilterCondition(filtercondition1);
    
    List customFieldsList = getCustomVaribalesKeyValue(user1);
    businessRuleForm.setCustomVariablesList(customFieldsList);
    businessRuleForm.fromValue(filtercondition1, request);
    
    List criteriaList = Constant.getFilterCriterias(variableType);
    logger.info("criteriaList : " + criteriaList.size());
    businessRuleForm.setFilterCriteriasList(criteriaList);
    businessRuleForm.setBusinessCriteraId(filtercondition1.getFilterConditionId());
    List soucetypeList = BOFactory.getLovBO().getAllResumeSourceTypes();
    logger.info("soucetypeList : " + soucetypeList);
    businessRuleForm.setSourceTypeList(soucetypeList);
    List countryList = BOFactory.getLovBO().getAllCountries();
    businessRuleForm.setCountryList(countryList);
    List stateList = BOFactory.getLovBO().getStateList();
    businessRuleForm.setStateList(stateList);
    List skilllist = BOFactory.getLovTXBO().getTechnicalSkillList(user1.getSuper_user_key());
    businessRuleForm.setLovList(skilllist);
    
    List newVariableListData = new ArrayList();
    Variables variable = BOFactory.getVariableBO().getVariablesDetails(String.valueOf(businessRuleForm.getVariableId()));
    if ((variable != null) && 
      (!StringUtils.isNullOrEmpty(variable.getVariableType())) && (variable.getVariableType().equals("list")))
    {
      Set lovset = variable.getListData();
      if ((lovset != null) && (lovset.size() > 0))
      {
        Iterator itr = lovset.iterator();
        while (itr.hasNext())
        {
          VariableListData vdata = (VariableListData)itr.next();
          newVariableListData.add(vdata);
        }
      }
    }
    logger.info("newVariableListData by code >>>> " + newVariableListData.size());
    businessRuleForm.setVaribalesListdata(newVariableListData);
    
    request.setAttribute("updatebusinessrule", "yes");
    return mapping.findForward("createFilter");
  }
  
  public ActionForward deleteFilter(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteFilter method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    try
    {
      BusinessRuleForm businessRuleForm = (BusinessRuleForm)form;
      
      String filterid = request.getParameter("fltid");
      BOFactory.getBusinessRuleBO().deleteFilterCondition(new Long(filterid).longValue());
      
      request.setAttribute("deletefilter", "yes");
      
      return mapping.findForward("createFilter");
    }
    catch (ValidationException e)
    {
      String msg = Constant.getResourceStringValue("errorcode." + e.getErrorCode(), user1.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
      return mapping.findForward("exception");
    }
    catch (Exception e)
    {
      String msg = Constant.getResourceStringValue("server.error", user1.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
    }
    return mapping.findForward("exception");
  }
  
  public ActionForward deleteApplicantFilter(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteApplicantFilter method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    try
    {
      BusinessRuleForm businessRuleForm = (BusinessRuleForm)form;
      
      String id = request.getParameter("id");
      BOFactory.getLovBO().deleteApplicantFilter(new Long(id).longValue());
      
      request.setAttribute("deleteappfilter", "yes");
      
      return mapping.findForward("createApplicantFilter");
    }
    catch (ValidationException e)
    {
      String msg = Constant.getResourceStringValue("errorcode." + e.getErrorCode(), user1.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
      return mapping.findForward("exception");
    }
    catch (Exception e)
    {
      String msg = Constant.getResourceStringValue("server.error", user1.getLocale());
      request.setAttribute(Common.ERROR_MSG, msg);
    }
    return mapping.findForward("exception");
  }
  
  public ActionForward deleteFilter1(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside deleteFilter method");
    BusinessRuleForm businessRuleForm = (BusinessRuleForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    String filterid = request.getParameter("fltid");
    long applicantfilterId = new Long(filterid).longValue();
    boolean canDelete = true;
    List screenfieldList = new ArrayList();
    screenfieldList = BOFactory.getApplicantBO().getScreenFieldsDetails("APPLICANT_SCREEN", user1.getSuper_user_key());
    logger.info("screenFields size : " + screenfieldList.size());
    
    String variableType = getVariableType(screenfieldList, businessRuleForm.getVariableName(), user1.getSuper_user_key());
    

    List screenfieldsList = getScreenFiledsKeyValue(screenfieldList, user1);
    
    businessRuleForm.setScreenfieldsList(screenfieldsList);
    businessRuleForm.setVariableType(variableType);
    
    BusinessFilterConditions filtercondition1 = BOFactory.getBusinessRuleBO().getFilterCondition(new Long(filterid).longValue());
    




    List applicantfiltergrplist = BOFactory.getBusinessRuleBO().getAllActiveApplicantFiltersGroupList();
    logger.info("** Applicant filter group list size : " + applicantfiltergrplist.size());
    if (applicantfiltergrplist.size() != 0) {
      for (int i = 0; i < applicantfiltergrplist.size(); i++)
      {
        BusinessCriteria BusinessCriteria1 = (BusinessCriteria)applicantfiltergrplist.get(i);
        Set<BusinessFilterConditions> bconditionList = new HashSet();
        bconditionList = BusinessCriteria1.getBusinessConditions();
        logger.info(" *** bconditionList size .... : " + bconditionList.size() + " and  Loop " + i);
        Iterator itr = bconditionList.iterator();
        while (itr.hasNext())
        {
          BusinessFilterConditions bus1 = (BusinessFilterConditions)itr.next();
          logger.info("applicant filter id : " + bus1.getFilterConditionId());
          if (applicantfilterId == bus1.getFilterConditionId()) {
            canDelete = false;
          }
        }
      }
    }
    if (canDelete == true)
    {
      logger.info("Applicant filter is not used, you can delete");
      BOFactory.getBusinessRuleBO().deleteFilterCondition(filtercondition1);
      request.setAttribute("deletefilter", "yes");
    }
    else
    {
      ActionErrors errors = new ActionErrors();
      errors.add("org.apache.struts.action.GLOBAL_ERROR", new ActionError("error.applicantfilter.used"));
      saveErrors(request, errors);
      logger.info("Applicant filter is used, you can't delete");
    }
    List customFieldsList = getCustomVaribalesKeyValue(user1);
    businessRuleForm.setCustomVariablesList(customFieldsList);
    
    List criteriaList = Constant.getFilterCriterias(variableType);
    logger.info("criteriaList : " + criteriaList.size());
    businessRuleForm.setFilterCriteriasList(criteriaList);
    businessRuleForm.setBusinessCriteraId(filtercondition1.getFilterConditionId());
    
    return mapping.findForward("createFilter");
  }
  
  public ActionForward loadCriterias(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside loadCriterias");
    BusinessRuleForm businessRuleForm = (BusinessRuleForm)form;
    String variablename = request.getParameter("variablename");
    logger.info("inside variablename" + variablename);
    User user1 = (User)request.getSession().getAttribute("user_data");
    List screenfieldList = new ArrayList();
    screenfieldList = BOFactory.getApplicantBO().getScreenFieldsDetails("APPLICANT_SCREEN", user1.getSuper_user_key());
    
    String variableType = getVariableType(screenfieldList, variablename, user1.getSuper_user_key());
    logger.info("variableType : " + variableType);
    List criteriaList = Constant.getFilterCriterias(variableType);
    logger.info("criteriaList : " + criteriaList.size());
    businessRuleForm.setFilterCriteriasList(criteriaList);
    businessRuleForm.setVariableType(variableType);
    return mapping.findForward("loadCriterias");
  }
  
  public ActionForward getVariablelistdata(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside getVariablelistdata");
    User user1 = (User)request.getSession().getAttribute("user_data");
    BusinessRuleForm businessRuleForm = (BusinessRuleForm)form;
    String variablecode = request.getParameter("variablecode");
    logger.info("variablecode >> " + variablecode);
    List newVariableListData = new ArrayList();
    Variables variable = BOFactory.getVariableBO().getVariableByVaribleCode(variablecode, user1.getSuper_user_key());
    if ((variable != null) && 
      (!StringUtils.isNullOrEmpty(variable.getVariableType())) && (variable.getVariableType().equals("list")))
    {
      Set lovset = variable.getListData();
      if ((lovset != null) && (lovset.size() > 0))
      {
        Iterator itr = lovset.iterator();
        while (itr.hasNext())
        {
          VariableListData vdata = (VariableListData)itr.next();
          newVariableListData.add(vdata);
        }
      }
    }
    logger.info("newVariableListData by code >>>> " + newVariableListData.size());
    businessRuleForm.setVaribalesListdata(newVariableListData);
    return mapping.findForward("loadVariablelistdata");
  }
  
  public void setFiltersList(Set filterset, HttpServletRequest request)
  {
    for (int i = 1; i < 50; i++)
    {
      String filterid = request.getParameter("filterid_" + i);
      if (!StringUtils.isNullOrEmpty(filterid))
      {
        logger.info("filterid" + filterid);
        
        BusinessFilterConditions bfc = new BusinessFilterConditions();
        bfc.setFilterConditionId(new Long(filterid).longValue());
        

        filterset.add(bfc);
      }
    }
  }
  
  public void setApplicantFiltersList(List appfilters, HttpServletRequest request)
  {
    for (int i = 1; i < 50; i++)
    {
      String filterid = request.getParameter("filterid_" + i);
      String isSilent = request.getParameter("issilent_" + i);
      if (!StringUtils.isNullOrEmpty(filterid))
      {
        logger.info("filterid" + filterid);
        
        KeyValue kv = new KeyValue();
        kv.setKey(filterid.trim());
        if ((!StringUtils.isNullOrEmpty(isSilent)) && (isSilent.equals("on"))) {
          kv.setValue("Y");
        } else {
          kv.setValue("N");
        }
        appfilters.add(kv);
      }
    }
  }
  
  public ActionForward filterselector(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside filterselector method");
    BusinessRuleForm businessRuleForm = (BusinessRuleForm)form;
    String boxnumber = request.getParameter("boxnumber");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    logger.info("boxnumber" + boxnumber);
    if (boxnumber != null) {
      request.setAttribute("boxnumber", boxnumber);
    }
    String start = request.getParameter("start");
    String range = request.getParameter("range");
    String results = request.getParameter("results");
    int start1 = StringUtils.stringToInt(start, 0);
    int range1 = StringUtils.stringToInt(range, 15);
    

    List filterList = BOFactory.getBusinessRuleBO().getFiltersByCritera(businessRuleForm.getName(), businessRuleForm.getType(), user1.getSuper_user_key(), start1, range1);
    int totaluser = 0;
    if (StringUtils.isNullOrEmpty(results)) {
      totaluser = BOFactory.getBusinessRuleBO().getCountOfFiltersByCritera(businessRuleForm.getName(), businessRuleForm.getType(), user1.getSuper_user_key());
    } else {
      totaluser = new Integer(results).intValue();
    }
    logger.info("emailtemplateList : " + filterList.size());
    logger.info("totaluser : " + totaluser);
    businessRuleForm.setStart(String.valueOf(start1));
    businessRuleForm.setRange(String.valueOf(range1));
    businessRuleForm.setResults(String.valueOf(totaluser));
    businessRuleForm.setFiltersList(filterList);
    
    businessRuleForm.setTypeList(Constant.getVariableTypeList(user1));
    
    String readpreview = request.getParameter("readPreview");
    return mapping.findForward("filterselector");
  }
  
  public ActionForward applicantFilterselector(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside applicantFilterselector method");
    BusinessRuleForm businessRuleForm = (BusinessRuleForm)form;
    String boxnumber = request.getParameter("boxnumber");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    logger.info("boxnumber" + boxnumber);
    if (boxnumber != null) {
      request.setAttribute("boxnumber", boxnumber);
    }
    String start = request.getParameter("start");
    String range = request.getParameter("range");
    String results = request.getParameter("results");
    int start1 = StringUtils.stringToInt(start, 0);
    int range1 = StringUtils.stringToInt(range, 15);
    

    List filterList = BOFactory.getBusinessRuleBO().getBusinessCriteriaByCritera(businessRuleForm.getName(), businessRuleForm.getType(), user1.getSuper_user_key(), start1, range1);
    int totaluser = 0;
    if (StringUtils.isNullOrEmpty(results)) {
      totaluser = BOFactory.getBusinessRuleBO().getCountOfBusinessCriteriaByCritera(businessRuleForm.getName(), businessRuleForm.getType(), user1.getSuper_user_key());
    } else {
      totaluser = new Integer(results).intValue();
    }
    logger.info("emailtemplateList : " + filterList.size());
    logger.info("totaluser : " + totaluser);
    businessRuleForm.setStart(String.valueOf(start1));
    businessRuleForm.setRange(String.valueOf(range1));
    businessRuleForm.setResults(String.valueOf(totaluser));
    businessRuleForm.setFilterCriteriasList(filterList);
    
    businessRuleForm.setTypeList(Constant.getVariableTypeList(user1));
    
    String readpreview = request.getParameter("readPreview");
    return mapping.findForward("applicantfilterselector");
  }
  
  public ActionForward searchFilters(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside filterselector method");
    BusinessRuleForm businessRuleForm = (BusinessRuleForm)form;
    String boxnumber = request.getParameter("boxnumber");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    logger.info("boxnumber" + boxnumber);
    if (boxnumber != null) {
      request.setAttribute("boxnumber", boxnumber);
    }
    String start = request.getParameter("start");
    String range = request.getParameter("range");
    String results = request.getParameter("results");
    int start1 = StringUtils.stringToInt(start, 0);
    int range1 = StringUtils.stringToInt(range, 15);
    

    List filterList = BOFactory.getBusinessRuleBO().getFiltersByCritera(businessRuleForm.getName(), businessRuleForm.getType(), user1.getSuper_user_key(), start1, range1);
    int totaluser = 0;
    if (StringUtils.isNullOrEmpty(results)) {
      totaluser = BOFactory.getBusinessRuleBO().getCountOfFiltersByCritera(businessRuleForm.getName(), businessRuleForm.getType(), user1.getSuper_user_key());
    } else {
      totaluser = new Integer(results).intValue();
    }
    logger.info("emailtemplateList : " + filterList.size());
    logger.info("totaluser : " + totaluser);
    businessRuleForm.setStart(String.valueOf(start1));
    businessRuleForm.setRange(String.valueOf(range1));
    businessRuleForm.setResults(String.valueOf(totaluser));
    businessRuleForm.setFiltersList(filterList);
    
    businessRuleForm.setTypeList(Constant.getVariableTypeList(user1));
    
    String readpreview = request.getParameter("readPreview");
    
    return mapping.findForward("filterselector");
  }
  
  public ActionForward searchApplicantFilters(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchApplicantFilters method");
    BusinessRuleForm businessRuleForm = (BusinessRuleForm)form;
    String boxnumber = request.getParameter("boxnumber");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    logger.info("boxnumber" + boxnumber);
    if (boxnumber != null) {
      request.setAttribute("boxnumber", boxnumber);
    }
    String start = request.getParameter("start");
    String range = request.getParameter("range");
    String results = request.getParameter("results");
    int start1 = StringUtils.stringToInt(start, 0);
    int range1 = StringUtils.stringToInt(range, 15);
    

    List filterList = BOFactory.getBusinessRuleBO().getBusinessCriteriaByCritera(businessRuleForm.getName(), businessRuleForm.getType(), user1.getSuper_user_key(), start1, range1);
    int totaluser = 0;
    if (StringUtils.isNullOrEmpty(results)) {
      totaluser = BOFactory.getBusinessRuleBO().getCountOfBusinessCriteriaByCritera(businessRuleForm.getName(), businessRuleForm.getType(), user1.getSuper_user_key());
    } else {
      totaluser = new Integer(results).intValue();
    }
    logger.info("emailtemplateList : " + filterList.size());
    logger.info("totaluser : " + totaluser);
    businessRuleForm.setStart(String.valueOf(start1));
    businessRuleForm.setRange(String.valueOf(range1));
    businessRuleForm.setResults(String.valueOf(totaluser));
    businessRuleForm.setFilterCriteriasList(filterList);
    
    businessRuleForm.setTypeList(Constant.getVariableTypeList(user1));
    
    String readpreview = request.getParameter("readPreview");
    
    return mapping.findForward("applicantfilterselector");
  }
  
  public ActionForward viewFilter(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside viewFilter method");
    BusinessRuleForm businessRuleForm = (BusinessRuleForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    List screenfieldList = new ArrayList();
    String filterid = request.getParameter("filterid");
    screenfieldList = BOFactory.getApplicantBO().getScreenFieldsDetails("APPLICANT_SCREEN", user1.getSuper_user_key());
    logger.info("screenFields size : " + screenfieldList.size());
    
    List screenfieldsList = getScreenFiledsKeyValue(screenfieldList, user1);
    
    businessRuleForm.setScreenfieldsList(screenfieldsList);
    
    BusinessFilterConditions filtercondition1 = BOFactory.getBusinessRuleBO().getFilterCondition(new Long(filterid).longValue());
    businessRuleForm.setName(filtercondition1.getName());
    businessRuleForm.setDesc(filtercondition1.getDesc());
    businessRuleForm.setVariableName(filtercondition1.getVariableName());
    businessRuleForm.setVariableType(filtercondition1.getVariableType());
    businessRuleForm.setVariableOrigin(filtercondition1.getVariableOrigin());
    businessRuleForm.setVariableCriteria(filtercondition1.getFilterCriteria());
    businessRuleForm.setFilterValue1(filtercondition1.getFilterValue1());
    businessRuleForm.setFilterValue2(filtercondition1.getFilterValue2());
    businessRuleForm.setBusinessCriteraId(filtercondition1.getFilterConditionId());
    businessRuleForm.setFilterValueDate1(filtercondition1.getFilterValueDate1());
    businessRuleForm.setFilterValueDate2(filtercondition1.getFilterValueDate2());
    
    List customFieldsList = getCustomVaribalesKeyValue(user1);
    businessRuleForm.setCustomVariablesList(customFieldsList);
    
    List criteriaList = Constant.getFilterCriterias(businessRuleForm.getVariableType());
    logger.info("criteriaList : " + criteriaList.size());
    businessRuleForm.setFilterCriteriasList(criteriaList);
    
    return mapping.findForward("viewFilter");
  }
  
  public ActionForward viewApplicantFilter(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("inside viewApplicantFilter method");
    BusinessRuleForm businessRuleForm = (BusinessRuleForm)form;
    User user1 = (User)request.getSession().getAttribute("user_data");
    String filterid = request.getParameter("filterid");
    List screenfieldList = new ArrayList();
    screenfieldList = BOFactory.getApplicantBO().getScreenFieldsDetails("APPLICANT_SCREEN", user1.getSuper_user_key());
    logger.info("screenFields size : " + screenfieldList.size());
    
    BusinessCriteria filtercondition1 = BOFactory.getBusinessRuleBO().getApplicantGroupFilterCondition(new Long(filterid).longValue());
    businessRuleForm.setName(filtercondition1.getName());
    businessRuleForm.setDesc(filtercondition1.getDesc());
    businessRuleForm.setVariableName(filtercondition1.getVariableName());
    businessRuleForm.setVariableType(filtercondition1.getVariableType());
    businessRuleForm.setVariableOrigin(filtercondition1.getVariableOrigin());
    businessRuleForm.setVariableCriteria(filtercondition1.getVariableCriteria());
    businessRuleForm.setFilterValue1(filtercondition1.getFilterValue1());
    businessRuleForm.setFilterValue2(filtercondition1.getFilterValue2());
    businessRuleForm.setBusinessCriteraId(filtercondition1.getBusinessCriteraId());
    
    List criteriaList = Constant.getFilterCriterias(businessRuleForm.getVariableType());
    logger.info("criteriaList : " + criteriaList.size());
    businessRuleForm.setFilterCriteriasList(criteriaList);
    
    List screenfieldsList = getScreenFiledsKeyValue(screenfieldList, user1);
    
    businessRuleForm.setScreenfieldsList(screenfieldsList);
    List filtersList = BOFactory.getBusinessRuleBO().getApplicantFiltersList(filtercondition1.getBusinessCriteraId());
    List applicantfilterlist = new ArrayList();
    
    BusinessCriteria BusinessCriteria1 = (BusinessCriteria)filtersList.get(0);
    Set<BusinessFilterConditions> bconditionList = new HashSet();
    bconditionList = BusinessCriteria1.getBusinessConditions();
    logger.info("bconditionList size  : " + bconditionList.size());
    Iterator itr = bconditionList.iterator();
    while (itr.hasNext())
    {
      BusinessFilterConditions bus1 = (BusinessFilterConditions)itr.next();
      applicantfilterlist.add(bus1);
    }
    businessRuleForm.setFiltersList(applicantfilterlist);
    return mapping.findForward("viewApplicantGroupFilter");
  }
}
