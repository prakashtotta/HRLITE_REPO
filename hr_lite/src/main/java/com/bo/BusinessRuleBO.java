package com.bo;

import com.bean.JobRequisition;
import com.bean.User;
import com.bean.filter.BusinessCriteria;
import com.bean.filter.BusinessFilterConditions;
import com.bean.lov.KeyValue;
import com.common.ValidationException;
import com.dao.BusinessRuleDAO;
import com.util.StringUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.log4j.Logger;

public class BusinessRuleBO
{
  protected static final Logger logger = Logger.getLogger(BusinessRuleBO.class);
  BusinessRuleDAO businessruledao;
  
  public void setBusinessruledao(BusinessRuleDAO businessruledao)
  {
    this.businessruledao = businessruledao;
  }
  
  public void saveBusinessCriteria(BusinessCriteria businessCriteria)
  {
    this.businessruledao.saveBusinessCriteria(businessCriteria);
  }
  
  public void updateBusinessCriteria(BusinessCriteria businessCriteria)
  {
    this.businessruledao.updateBusinessCriteria(businessCriteria);
  }
  
  public void saveFilterCondition(BusinessFilterConditions filter)
  {
    this.businessruledao.saveFilterCondition(filter);
  }
  
  public void updateFilterCondition(BusinessFilterConditions filter)
  {
    this.businessruledao.updateFilterCondition(filter);
  }
  
  public void deleteFilterCondition(BusinessFilterConditions filter)
  {
    this.businessruledao.deleteFilterCondition(filter);
  }
  
  public BusinessFilterConditions getFilterCondition(long filterId)
  {
    return this.businessruledao.getFilterCondition(filterId);
  }
  
  public void deleteFilterCondition(long filterId)
    throws ValidationException, Exception
  {
    this.businessruledao.deleteFilterCondition(filterId);
  }
  
  public BusinessCriteria getApplicantGroupFilterCondition(long filterId)
  {
    return this.businessruledao.getApplicantGroupFilterCondition(filterId);
  }
  
  public BusinessCriteria getBusinessCriteria(long bussinesCriteriaId)
  {
    return this.businessruledao.getBusinessCriteria(bussinesCriteriaId);
  }
  
  public List getAllFilterConditions()
  {
    return this.businessruledao.getAllFilterConditions();
  }
  
  public List searchFilters(User user, String name, String variableType, String variablename, long superUserKey, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.businessruledao.searchFilters(name, variableType, variablename, superUserKey, pageSize, startIndex, dir_str, sort_str);
  }
  
  public int countSearchFilters(String name, String variableType, String variablename, long superUserKey)
  {
    return this.businessruledao.countSearchFilters(name, variableType, variablename, superUserKey);
  }
  
  public List searchApplicantFilters(User user, String name, String variableType, String variablename, long superUserKey, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.businessruledao.searchApplicantFilters(name, variableType, variablename, superUserKey, pageSize, startIndex, dir_str, sort_str);
  }
  
  public int countsearchApplicantFilters(String name, String variableType, String variablename, long superUserKey)
  {
    return this.businessruledao.countsearchApplicantFilters(name, variableType, variablename, superUserKey);
  }
  
  public List getFiltersByCritera(String filterName, String filterType, long superUserKey, int start, int range)
  {
    return this.businessruledao.getFiltersByCritera(filterName, filterType, superUserKey, start, range);
  }
  
  public List getBusinessCriteriaByCritera(String filterName, String filterType, long superUserKey, int start, int range)
  {
    return this.businessruledao.getBusinessCriteriaByCritera(filterName, filterType, superUserKey, start, range);
  }
  
  public int getCountOfFiltersByCritera(String filterName, String filterType, long superUserKey)
  {
    return this.businessruledao.getCountOfFiltersByCritera(filterName, filterType, superUserKey);
  }
  
  public int getCountOfBusinessCriteriaByCritera(String filterName, String filterType, long superUserKey)
  {
    return this.businessruledao.getCountOfBusinessCriteriaByCritera(filterName, filterType, superUserKey);
  }
  
  public List<BusinessCriteria> getBusinessCriteriasByRequistion(long jobRequistionId)
  {
    return this.businessruledao.getBusinessCriteriasByRequistion(jobRequistionId);
  }
  
  public List<BusinessCriteria> getBusinessCriteriasByType(String type)
  {
    return this.businessruledao.getBusinessCriteriasByType(type);
  }
  
  public List<BusinessCriteria> getBusinessCriteriasByTemplate(long tmplId)
  {
    return this.businessruledao.getBusinessCriteriasByTemplate(tmplId);
  }
  
  public List getApplicantFiltersList(long id)
  {
    logger.info("Inside getApplicantFiltersList method");
    return this.businessruledao.getApplicantFiltersList(id);
  }
  
  public List getAllActiveApplicantFiltersGroupList()
  {
    logger.info("Inside getAllActiveApplicantFiltersGroupList method");
    return this.businessruledao.getAllActiveApplicantFiltersGroupList();
  }
  
  public void copyApplicantFiltersFromTemplateToRequisition(long templateId, long reqid, User user1)
  {
    logger.info("copyApplicantFiltersFromTemplateToRequisition from template id:" + templateId + " to jobreqid:" + reqid);
    List<BusinessCriteria> bclist = getBusinessCriteriasByTemplate(new Long(templateId).longValue());
    for (int i = 0; i < bclist.size(); i++)
    {
      BusinessCriteria bc = (BusinessCriteria)bclist.get(i);
      BusinessCriteria bcnew = new BusinessCriteria();
      
      bcnew.setName(bc.getName());
      bcnew.setDesc(bc.getDesc());
      bcnew.setVariableType(bc.getVariableType());
      bcnew.setVariableId(bc.getVariableId());
      bcnew.setVariableName(bc.getVariableName());
      bcnew.setVariableOrigin(bc.getVariableOrigin());
      bcnew.setVariableCriteria(bc.getVariableCriteria());
      bcnew.setFilterValue1(bc.getFilterValue1());
      bcnew.setFilterValue2(bc.getFilterValue2());
      bcnew.setFilterValueDate1(bc.getFilterValueDate1());
      bcnew.setFilterValueDate2(bc.getFilterValueDate2());
      bcnew.setCreatedBy(bc.getCreatedBy());
      bcnew.setCreatedDate(bc.getCreatedDate());
      bcnew.setIsSilent(bc.getIsSilent());
      bcnew.setStatus("A");
      
      bcnew.setIdvalue(reqid);
      bcnew.setType("REQUISITION_ONLY");
      
      Set<BusinessFilterConditions> filters = bc.getBusinessConditions();
      Set<BusinessFilterConditions> filtersnew = new HashSet();
      if (filters != null)
      {
        Iterator<BusinessFilterConditions> itr = filters.iterator();
        while ((itr != null) && (itr.hasNext())) {
          filtersnew.add(itr.next());
        }
      }
      bcnew.setBusinessConditions(filtersnew);
      
      this.businessruledao.saveBusinessCriteria(bcnew);
    }
  }
  
  public void manageApplicantFilter(long reqId, List<KeyValue> appFilterList, String type, User user1)
    throws Exception
  {
    List<BusinessCriteria> bclist = new ArrayList();
    if ((!StringUtils.isNullOrEmpty(type)) && (type.equals("TEMPLATE"))) {
      bclist = getBusinessCriteriasByTemplate(new Long(reqId).longValue());
    } else if ((!StringUtils.isNullOrEmpty(type)) && (type.equals("ALL_APPLICANTS"))) {
      bclist = getBusinessCriteriasByType(type);
    } else {
      bclist = getBusinessCriteriasByRequistion(new Long(reqId).longValue());
    }
    List<Long> existingFlts = new ArrayList();
    for (int j = 0; j < bclist.size(); j++)
    {
      BusinessCriteria bcc = (BusinessCriteria)bclist.get(j);
      existingFlts.add(Long.valueOf(bcc.getBusinessCriteraId()));
    }
    List<Long> deleteList = new ArrayList();
    List<Long> newList = new ArrayList();
    for (int i = 0; i < appFilterList.size(); i++)
    {
      KeyValue kv = (KeyValue)appFilterList.get(i);
      logger.info("app filter id" + new Long(kv.getKey()).longValue());
      newList.add(Long.valueOf(new Long(kv.getKey()).longValue()));
      if (existingFlts.contains(Long.valueOf(new Long(kv.getKey()).longValue())))
      {
        BusinessCriteria bcexist = this.businessruledao.getBusinessCriteria(new Long(kv.getKey()).longValue());
        bcexist.setIsSilent(kv.getValue());
        this.businessruledao.updateBusinessCriteria(bcexist);
      }
      else
      {
        BusinessCriteria bc = this.businessruledao.getBusinessCriteria(new Long(kv.getKey()).longValue());
        logger.info("bc" + bc);
        BusinessCriteria bcnew = new BusinessCriteria();
        
        bcnew.setName(bc.getName());
        bcnew.setDesc(bc.getDesc());
        bcnew.setVariableType(bc.getVariableType());
        bcnew.setVariableId(bc.getVariableId());
        bcnew.setVariableName(bc.getVariableName());
        bcnew.setVariableOrigin(bc.getVariableOrigin());
        bcnew.setVariableCriteria(bc.getVariableCriteria());
        bcnew.setFilterValue1(bc.getFilterValue1());
        bcnew.setFilterValue2(bc.getFilterValue2());
        bcnew.setFilterValueDate1(bc.getFilterValueDate1());
        bcnew.setFilterValueDate2(bc.getFilterValueDate2());
        bcnew.setCreatedBy(bc.getCreatedBy());
        bcnew.setCreatedDate(bc.getCreatedDate());
        bcnew.setIsSilent(kv.getValue());
        bcnew.setStatus("A");
        bcnew.setSuper_user_key(user1.getSuper_user_key());
        if ((!StringUtils.isNullOrEmpty(type)) && (type.equals("TEMPLATE"))) {
          bcnew.setType("TEMPLATE_ONLY");
        } else if ((!StringUtils.isNullOrEmpty(type)) && (type.equals("ALL_APPLICANTS"))) {
          bcnew.setType("ALL_APPLICANTS");
        } else {
          bcnew.setType("REQUISITION_ONLY");
        }
        bcnew.setIdvalue(reqId);
        
        Set<BusinessFilterConditions> filters = bc.getBusinessConditions();
        Set<BusinessFilterConditions> filtersnew = new HashSet();
        if (filters != null)
        {
          Iterator<BusinessFilterConditions> itr = filters.iterator();
          while ((itr != null) && (itr.hasNext())) {
            filtersnew.add(itr.next());
          }
        }
        bcnew.setBusinessConditions(filtersnew);
        logger.info("manageApplicantFilter" + kv.getValue());
        
        this.businessruledao.saveBusinessCriteria(bcnew);
      }
    }
    for (int i = 0; i < existingFlts.size(); i++)
    {
      long id = ((Long)existingFlts.get(i)).longValue();
      if (!newList.contains(Long.valueOf(id))) {
        deleteList.add(Long.valueOf(id));
      }
    }
    if (deleteList.size() > 0) {
      this.businessruledao.deleteBusinessCriterias(deleteList);
    }
    if ((!StringUtils.isNullOrEmpty(type)) && (type.equals("REQUISITION")))
    {
      JobRequisition requistion = BOFactory.getJobRequistionBO().getJobRequision(String.valueOf(reqId));
      requistion.setIsFilterApplied(0);
      BOFactory.getJobRequistionBO().updateJobRequistion(requistion);
    }
  }
  
  public BusinessRuleDAO getBusinessruledao()
  {
    return this.businessruledao;
  }
}
