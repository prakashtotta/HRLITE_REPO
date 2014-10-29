package com.dao;

import com.bean.lov.FormVariablesMap;
import com.bean.lov.VariablesValues;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class VariablesTXDAO
  extends HibernateDaoSupport
{
  protected static final Logger logger = Logger.getLogger(VariablesTXDAO.class);
  
  public void saveVariableValues(List formVariableDataList)
  {
    logger.info("Inside saveVariableDataList method");
    for (int i = 0; i < formVariableDataList.size(); i++)
    {
      VariablesValues jbc = (VariablesValues)formVariableDataList.get(i);
      getHibernateTemplate().save(jbc);
    }
  }
  
  public void saveVariableValues(List formVariableDataList, long idvalue)
  {
    logger.info("Inside saveVariableDataList method");
    for (int i = 0; i < formVariableDataList.size(); i++)
    {
      VariablesValues jbc = (VariablesValues)formVariableDataList.get(i);
      jbc.setIdvalue(idvalue);
      getHibernateTemplate().save(jbc);
    }
  }
  
  public void saveVariableValues(List formVariableDataList, String formcode, long idvalue)
  {
    logger.info("Inside saveVariableDataList method");
    for (int i = 0; i < formVariableDataList.size(); i++)
    {
      VariablesValues jbc = (VariablesValues)formVariableDataList.get(i);
      jbc.setFormCode(formcode);
      jbc.setIdvalue(idvalue);
      getHibernateTemplate().save(jbc);
    }
  }
  
  public void saveFormVariableMap(List formVariablesList, String formcode, long idvalue)
  {
    logger.info("Inside saveFormVariableMap method");
    for (int i = 0; i < formVariablesList.size(); i++)
    {
      FormVariablesMap jbc = (FormVariablesMap)formVariablesList.get(i);
      jbc.setFormCode(formcode);
      jbc.setIdValue(idvalue);
      getHibernateTemplate().save(jbc);
    }
  }
  
  public void deleteVariableValues(long idvalue, String formcode)
  {
    logger.info("Inside deleteVariableValues method");
    String hql = "delete from VariablesValues where idvalue = :idvalue and formCode =:formCode";
    Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
    query.setLong("idvalue", idvalue);
    query.setString("formCode", formcode);
    int rowCount = query.executeUpdate();
  }
  
  public void deleteFormVariableMap(long idValue, String formCode)
  {
    logger.info("Inside deleteFormVariableMap method");
    String hql = "delete from FormVariablesMap where idValue = :idValue and formCode =:formCode";
    Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
    query.setLong("idValue", idValue);
    query.setString("formCode", formCode);
    int rowCount = query.executeUpdate();
  }
}
