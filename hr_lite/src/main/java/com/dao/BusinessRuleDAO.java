package com.dao;

import com.bean.Country;
import com.bean.ResumeSourceType;
import com.bean.State;
import com.bean.filter.BusinessCriteria;
import com.bean.filter.BusinessFilterConditions;
import com.bean.lov.VariableListData;
import com.common.ValidationException;
import com.util.StringUtils;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class BusinessRuleDAO
  extends HibernateDaoSupport
{
  protected static final Logger logger = Logger.getLogger(BusinessRuleDAO.class);
  
  public void saveBusinessCriteria(BusinessCriteria businessCriteria)
  {
    logger.info("*** Inside saveBusinessCriteria method ...");
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      session.save(businessCriteria);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveBusinessCriteria()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void updateBusinessCriteria(BusinessCriteria businessCriteria)
  {
    logger.info("*** Inside updateBusinessCriteria method ...");
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      session.update(businessCriteria);
    }
    catch (Exception e)
    {
      logger.error("Exception on updateBusinessCriteria()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void saveFilterCondition(BusinessFilterConditions filter)
  {
    logger.info("*** Inside saveFilterCondition method ...");
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      session.save(filter);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveFilterCondition()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void updateFilterCondition(BusinessFilterConditions filter)
  {
    logger.info("*** Inside updateFilterCondition method ...");
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      session.update(filter);
    }
    catch (Exception e)
    {
      logger.error("Exception on updateFilterCondition()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void deleteFilterCondition(BusinessFilterConditions filter)
  {
    logger.info("*** Inside deleteFilterCondition method ...");
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      session.delete(filter);
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteFilterCondition()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public BusinessFilterConditions getFilterCondition(long filterId)
  {
    logger.info("*** Inside getFilterCondition method ...");
    BusinessFilterConditions flt = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      flt = (BusinessFilterConditions)session.createCriteria(BusinessFilterConditions.class).add(Restrictions.eq("filterConditionId", Long.valueOf(filterId))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getFilterCondition()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return flt;
  }
  
  public void deleteFilterCondition(long id)
    throws ValidationException, Exception
  {
    logger.info("Inside deleteFilterCondition method");
    
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String hql = "delete from BusinessFilterConditions where filterConditionId = :id";
      Query query = session.createQuery(hql);
      query.setLong("id", id);
      int rowCount = query.executeUpdate();
    }
    catch (Exception e)
    {
      int rowCount;
      logger.error("Exception on deleteFilterCondition()", e);
      if ((e instanceof ConstraintViolationException)) {
        throw new ValidationException("ER102", e.getMessage());
      }
      throw e;
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public BusinessCriteria getApplicantGroupFilterCondition(long filterId)
  {
    logger.info("*** Inside getApplicantGroupFilterCondition method ...");
    BusinessCriteria flt = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      flt = (BusinessCriteria)session.createCriteria(BusinessCriteria.class).add(Restrictions.eq("businessCriteraId", Long.valueOf(filterId))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantGroupFilterCondition()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return flt;
  }
  
  public BusinessCriteria getBusinessCriteria(long bussinesCriteriaId)
  {
    logger.info("*** Inside getBusinessCriteria method ...");
    BusinessCriteria flt = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      flt = (BusinessCriteria)session.createCriteria(BusinessCriteria.class).add(Restrictions.eq("businessCriteraId", Long.valueOf(bussinesCriteriaId))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getBusinessCriteria()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return flt;
  }
  
  public List getAllFilterConditions()
  {
    logger.info("*** Inside getAllFilterConditions method ...");
    List fltList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      fltList = session.createCriteria(BusinessFilterConditions.class).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllFilterConditions()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return fltList;
  }
  
  public List getFiltersByCritera(String filterName, String filterType, long super_user_key, int start, int range)
  {
    logger.info("*** Inside getFiltersByCritera method ...");
    List fltList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      Criteria crit = session.createCriteria(BusinessFilterConditions.class);
      if (!StringUtils.isNullOrEmpty(filterName)) {
        crit.add(Restrictions.like("name", "%" + filterName + "%"));
      }
      if (!StringUtils.isNullOrEmpty(filterType)) {
        crit.add(Restrictions.eq("variableType", filterType));
      }
      crit.add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key)));
      crit.setFirstResult(start);
      crit.setMaxResults(range);
      crit.addOrder(Order.desc("filterConditionId"));
      fltList = crit.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getFiltersByCritera()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return fltList;
  }
  
  public List searchFilters(String name, String variableType, String variablename, long super_user_key, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("*** Inside searchFilters method ...");
    List fltList = new ArrayList();
    List newFilterList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      Criteria crit = session.createCriteria(BusinessFilterConditions.class);
      if ((!StringUtils.isNullOrEmpty(name)) && (!name.equals("null"))) {
        crit.add(Restrictions.like("name", "%" + name + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(variableType)) && (!variableType.equals("null"))) {
        crit.add(Restrictions.eq("variableType", variableType));
      }
      if ((!StringUtils.isNullOrEmpty(variablename)) && (!variablename.equals("null"))) {
        crit.add(Restrictions.like("variableName", variablename));
      }
      crit.add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key)));
      if (dir_str.equalsIgnoreCase("asc")) {
        crit.addOrder(Order.asc(sort_str));
      } else {
        crit.addOrder(Order.desc(sort_str));
      }
      crit.setFirstResult(startIndex);
      crit.setMaxResults(pageSize);
      fltList = crit.list();
      for (int i = 0; i < fltList.size(); i++)
      {
        BusinessFilterConditions fc = (BusinessFilterConditions)fltList.get(i);
        if ((!StringUtils.isNullOrEmpty(fc.getVariableType())) && (fc.getVariableType().equals("list")))
        {
          if ((!StringUtils.isNullOrEmpty(fc.getVariableName())) && (fc.getVariableName().equals("COUNTRY")))
          {
            Country ct = getCountry(new Long(fc.getFilterValue1()).longValue());
            fc.setValueName(ct.getCountryName());
            newFilterList.add(fc);
          }
          else if ((!StringUtils.isNullOrEmpty(fc.getVariableName())) && (fc.getVariableName().equals("STATE")))
          {
            State st = getState(new Long(fc.getFilterValue1()).longValue());
            fc.setValueName(st.getStateName());
            newFilterList.add(fc);
          }
          else if ((!StringUtils.isNullOrEmpty(fc.getVariableName())) && (fc.getVariableName().equals("SOURCE_SUBSOURCE")))
          {
            ResumeSourceType rst = getResumeSource(new Integer(fc.getFilterValue1()).intValue());
            fc.setValueName(rst.getResumeSourceTypeName());
            newFilterList.add(fc);
          }
          else if ((!StringUtils.isNullOrEmpty(fc.getVariableOrigin())) && (fc.getVariableOrigin().equals("CUSTOM")))
          {
            VariableListData variablelistdata = getVariableListData(fc.getFilterValue1());
            fc.setValueName(variablelistdata.getVariableValue());
            newFilterList.add(fc);
          }
          else
          {
            fc.setValueName(fc.getFilterValue1());
            newFilterList.add(fc);
          }
        }
        else {
          newFilterList.add(fc);
        }
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on searchFilters()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return newFilterList;
  }
  
  public VariableListData getVariableListData(String id)
  {
    logger.info("Inside getVariableListData method");
    VariableListData variablelisdata = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      variablelisdata = (VariableListData)session.createCriteria(VariableListData.class).add(Restrictions.eq("variableListDataId", new Long(id))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getVariableListData()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return variablelisdata;
  }
  
  public Country getCountry(long id)
  {
    logger.info("Inside getCountry method");
    Country ct = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      ct = (Country)session.createCriteria(Country.class).add(Restrictions.eq("countryId", Long.valueOf(id))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountry()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return ct;
  }
  
  public State getState(long id)
  {
    logger.info("Inside getState method");
    State st = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      st = (State)session.createCriteria(State.class).add(Restrictions.eq("stateId", Long.valueOf(id))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getState()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return st;
  }
  
  public ResumeSourceType getResumeSource(int id)
  {
    logger.info("Inside getState method");
    ResumeSourceType rst = null;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      rst = (ResumeSourceType)session.createCriteria(ResumeSourceType.class).add(Restrictions.eq("resumeSourceTypeId", Integer.valueOf(id))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getResumeSource()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return rst;
  }
  
  public List searchApplicantFilters(String name, String variableType, String variablename, long super_user_key, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("*** Inside searchApplicantFilters method ...");
    List fltList = new ArrayList();
    List newFilterList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      Criteria crit = session.createCriteria(BusinessCriteria.class).add(Restrictions.eq("type", "DRAFT"));
      if ((!StringUtils.isNullOrEmpty(name)) && (!name.equals("null"))) {
        crit.add(Restrictions.like("name", "%" + name + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(variableType)) && (!variableType.equals("null"))) {
        crit.add(Restrictions.eq("variableType", variableType));
      }
      if ((!StringUtils.isNullOrEmpty(variablename)) && (!variablename.equals("null"))) {
        crit.add(Restrictions.like("variableName", variablename));
      }
      crit.add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key)));
      if (dir_str.equalsIgnoreCase("asc")) {
        crit.addOrder(Order.asc(sort_str));
      } else {
        crit.addOrder(Order.desc(sort_str));
      }
      crit.setFirstResult(startIndex);
      crit.setMaxResults(pageSize);
      fltList = crit.list();
      for (int i = 0; i < fltList.size(); i++)
      {
        BusinessCriteria fc = (BusinessCriteria)fltList.get(i);
        if ((!StringUtils.isNullOrEmpty(fc.getVariableType())) && (fc.getVariableType().equals("list")))
        {
          if ((!StringUtils.isNullOrEmpty(fc.getVariableName())) && (fc.getVariableName().equals("COUNTRY")))
          {
            Country ct = getCountry(new Long(fc.getFilterValue1()).longValue());
            fc.setValueName(ct.getCountryName());
            newFilterList.add(fc);
          }
          else if ((!StringUtils.isNullOrEmpty(fc.getVariableName())) && (fc.getVariableName().equals("STATE")))
          {
            State st = getState(new Long(fc.getFilterValue1()).longValue());
            fc.setValueName(st.getStateName());
            newFilterList.add(fc);
          }
          else if ((!StringUtils.isNullOrEmpty(fc.getVariableName())) && (fc.getVariableName().equals("SOURCE_SUBSOURCE")))
          {
            ResumeSourceType rst = getResumeSource(new Integer(fc.getFilterValue1()).intValue());
            fc.setValueName(rst.getResumeSourceTypeName());
            newFilterList.add(fc);
          }
          else
          {
            fc.setValueName(fc.getFilterValue1());
            newFilterList.add(fc);
          }
        }
        else {
          newFilterList.add(fc);
        }
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on searchApplicantFilters()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return newFilterList;
  }
  
  public int countSearchFilters(String name, String variableType, String variablename, long super_user_key)
  {
    logger.info("*** Inside countSearchFilters method ...");
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      Criteria crit = session.createCriteria(BusinessFilterConditions.class);
      if ((!StringUtils.isNullOrEmpty(name)) && (!name.equals("null"))) {
        crit.add(Restrictions.like("name", "%" + name + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(variableType)) && (!variableType.equals("null"))) {
        crit.add(Restrictions.eq("variableType", variableType));
      }
      if ((!StringUtils.isNullOrEmpty(variablename)) && (!variablename.equals("null"))) {
        crit.add(Restrictions.like("variableName", variablename));
      }
      crit.add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key)));
      crit.setProjection(Projections.rowCount());
      totaluser = ((Integer)crit.list().get(0)).intValue();
    }
    catch (Exception e)
    {
      logger.error("Exception on countSearchFilters()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totaluser;
  }
  
  public int countsearchApplicantFilters(String name, String variableType, String variablename, long super_user_key)
  {
    logger.info("*** Inside countsearchApplicantFilters method ...");
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      Criteria crit = session.createCriteria(BusinessCriteria.class).add(Restrictions.eq("type", "DRAFT"));
      if ((!StringUtils.isNullOrEmpty(name)) && (!name.equals("null"))) {
        crit.add(Restrictions.like("name", "%" + name + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(variableType)) && (!variableType.equals("null"))) {
        crit.add(Restrictions.eq("variableType", variableType));
      }
      if ((!StringUtils.isNullOrEmpty(variablename)) && (!variablename.equals("null"))) {
        crit.add(Restrictions.like("variableName", variablename));
      }
      crit.add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key)));
      crit.setProjection(Projections.rowCount());
      totaluser = ((Integer)crit.list().get(0)).intValue();
    }
    catch (Exception e)
    {
      logger.error("Exception on countsearchApplicantFilters()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totaluser;
  }
  
  public List getBusinessCriteriaByCritera(String filterName, String filterType, long super_user_key, int start, int range)
  {
    logger.info("*** Inside getBusinessCriteriaByCritera method ...");
    List fltList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      Criteria crit = session.createCriteria(BusinessCriteria.class);
      if (!StringUtils.isNullOrEmpty(filterName)) {
        crit.add(Restrictions.like("name", "%" + filterName + "%"));
      }
      if (!StringUtils.isNullOrEmpty(filterType)) {
        crit.add(Restrictions.eq("variableType", filterType));
      }
      crit.add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key)));
      crit.add(Restrictions.like("type", "DRAFT"));
      crit.setFirstResult(start);
      crit.setMaxResults(range);
      crit.addOrder(Order.desc("businessCriteraId"));
      fltList = crit.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getBusinessCriteriaByCritera()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return fltList;
  }
  
  public int getCountOfFiltersByCritera(String filterName, String filterType, long super_user_key)
  {
    logger.info("*** Inside getCountOfFiltersByCritera method ...");
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      Criteria crit = session.createCriteria(BusinessFilterConditions.class);
      if (!StringUtils.isNullOrEmpty(filterName)) {
        crit.add(Restrictions.like("name", "%" + filterName + "%"));
      }
      if (!StringUtils.isNullOrEmpty(filterType)) {
        crit.add(Restrictions.eq("variableType", filterType));
      }
      crit.add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key)));
      crit.setProjection(Projections.rowCount());
      totaluser = ((Integer)crit.list().get(0)).intValue();
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfFiltersByCritera()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totaluser;
  }
  
  public int getCountOfBusinessCriteriaByCritera(String filterName, String filterType, long super_user_key)
  {
    logger.info("*** Inside getCountOfBusinessCriteriaByCritera method ...");
    int totaluser = 0;
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      Criteria crit = session.createCriteria(BusinessCriteria.class);
      if (!StringUtils.isNullOrEmpty(filterName)) {
        crit.add(Restrictions.like("name", "%" + filterName + "%"));
      }
      if (!StringUtils.isNullOrEmpty(filterType)) {
        crit.add(Restrictions.eq("variableType", filterType));
      }
      crit.add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key)));
      crit.add(Restrictions.like("type", "DRAFT"));
      crit.setProjection(Projections.rowCount());
      totaluser = ((Integer)crit.list().get(0)).intValue();
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfBusinessCriteriaByCritera()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totaluser;
  }
  
  public List<BusinessCriteria> getBusinessCriteriasByRequistion(long jobRequistionId)
  {
    logger.info("Inside getBusinessCriteriasByRequistion method" + jobRequistionId);
    List<BusinessCriteria> criteriaList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      String hqlquery = "select j from BusinessCriteria j where j.status = 'A' and  j.type = :type and j.idvalue in (:reqlist) ";
      

      List<Long> reqList = new ArrayList();
      reqList.add(Long.valueOf(jobRequistionId));
      

      Query query = session.createQuery(hqlquery);
      query.setString("type", "REQUISITION_ONLY");
      query.setParameterList("reqlist", reqList);
      
      logger.info(query.getQueryString());
      criteriaList = query.list();
      
      logger.info("criteriaList size" + criteriaList.size());
    }
    catch (Exception e)
    {
      logger.error("Exception on getBusinessCriteriasByRequistion()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return criteriaList;
  }
  
  public List<BusinessCriteria> getBusinessCriteriasByType(String type)
  {
    logger.info("Inside getBusinessCriteriasByType method");
    List<BusinessCriteria> criteriaList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      String hqlquery = "select j from BusinessCriteria j where j.status = 'A' and  j.type = :type and j.idvalue in (:reqlist) ";
      

      List<Long> reqList = new ArrayList();
      reqList.add(new Long(0L));
      
      Query query = session.createQuery(hqlquery);
      query.setString("type", type);
      query.setParameterList("reqlist", reqList);
      
      logger.info(query.getQueryString());
      criteriaList = query.list();
      
      logger.info("criteriaList size" + criteriaList.size());
    }
    catch (Exception e)
    {
      logger.error("Exception on getBusinessCriteriasByType()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return criteriaList;
  }
  
  public List<BusinessCriteria> getBusinessCriteriasByTemplate(long tmplId)
  {
    logger.info("Inside getBusinessCriteriasByTemplate method" + tmplId);
    List<BusinessCriteria> criteriaList = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      String hqlquery = "select j from BusinessCriteria j where j.status = 'A' and  j.type = :type and j.idvalue in (:reqlist) ";
      

      List<Long> reqList = new ArrayList();
      reqList.add(Long.valueOf(tmplId));
      

      Query query = session.createQuery(hqlquery);
      query.setString("type", "TEMPLATE_ONLY");
      query.setParameterList("reqlist", reqList);
      
      logger.info(query.getQueryString());
      criteriaList = query.list();
      
      logger.info("criteriaList size" + criteriaList.size());
    }
    catch (Exception e)
    {
      logger.error("Exception on getBusinessCriteriasByTemplate()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return criteriaList;
  }
  
  public void deleteBusinessCriteriaByRequition(long reqId)
    throws Exception
  {
    logger.info("*** Inside deleteBusinessCriteriaByRequition method ...");
    try
    {
      String hql = "delete from BusinessCriteria where idvalue = :idvalue and type=:type";
      Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
      query.setLong("idvalue", reqId);
      query.setString("type", "REQUISITION_ONLY");
      int rowCount = query.executeUpdate();
    }
    catch (Exception e)
    {
      int rowCount;
      logger.error("Exception on deleteBusinessCriteriaByRequition()", e);
      throw e;
    }
  }
  
  public void deleteBusinessCriterias(List<Long> bcriteriaList)
    throws Exception
  {
    logger.info("*** Inside deleteBusinessCriterias method ...");
    try
    {
      String hql = "delete from BusinessCriteria where  businessCriteraId in (:bcriteriaList)";
      Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
      query.setParameterList("bcriteriaList", bcriteriaList);
      int rowCount = query.executeUpdate();
    }
    catch (Exception e)
    {
      int rowCount;
      logger.error("Exception on deleteBusinessCriterias()", e);
      throw e;
    }
  }
  
  public List getApplicantFiltersList(long id)
  {
    logger.info("Inside getApplicantFiltersList method");
    logger.info("*** Business Criteria Id : " + id);
    List filterlist = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      filterlist = session.createCriteria(BusinessCriteria.class).add(Restrictions.eq("businessCriteraId", Long.valueOf(id))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getApplicantFiltersList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return filterlist;
  }
  
  public List getAllActiveApplicantFiltersGroupList()
  {
    logger.info("Inside getAllActiveApplicantFiltersGroupList method");
    
    List filterlist = new ArrayList();
    org.hibernate.Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      filterlist = session.createCriteria(BusinessCriteria.class).add(Restrictions.eq("status", "A")).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllActiveApplicantFiltersGroupList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return filterlist;
  }
}
