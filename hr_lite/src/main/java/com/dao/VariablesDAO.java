package com.dao;

import com.bean.lov.FormVariablesMap;
import com.bean.lov.VariableListData;
import com.bean.lov.Variables;
import com.bean.lov.VariablesValues;
import com.common.ValidationException;
import com.resources.Constant;
import com.util.StringUtils;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class VariablesDAO
  extends HibernateDaoSupport
{
  protected static final Logger logger = Logger.getLogger(VariablesDAO.class);
  
  public List getFormVariablesListWithoutIdValue(String formcode, long super_user_key)
  {
    logger.info("Inside getFormVariablesList method");
    List formvariablesList = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      formvariablesList = session.createCriteria(FormVariablesMap.class).add(Restrictions.eq("formCode", formcode)).add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key))).addOrder(Order.asc("sequence")).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getFormVariablesList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return formvariablesList;
  }
  
  public List getFormVariablesList(String formcode, long idvalue, long super_user_key)
  {
    logger.info("Inside getFormVariablesList method");
    List formvariablesList = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      Criteria outer = session.createCriteria(FormVariablesMap.class).add(Restrictions.eq("formCode", formcode)).add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key)));
      
      Criterion id1 = Restrictions.eq("idValue", Long.valueOf(idvalue));
      Criterion id2 = Restrictions.eq("idValue", Long.valueOf(new Long(0L).longValue()));
      LogicalExpression orExp = Restrictions.or(id1, id2);
      outer.add(orExp);
      formvariablesList = outer.addOrder(Order.asc("sequence")).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getFormVariablesList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return formvariablesList;
  }
  
  public List getAllFormVariablesListForApplicant(long super_user_key)
  {
    logger.info("Inside getAllFormVariablesListForApplicant method");
    List formvariablesList = new ArrayList();
    List formvariablesListNew = new ArrayList();
    List vidlist = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      List appScreenList = Constant.getAllApplicantScreenList();
      

      Criteria outer = session.createCriteria(FormVariablesMap.class).add(Restrictions.in("formCode", appScreenList)).add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key)));
      outer.add(Restrictions.eq("idValue", Long.valueOf(new Long(0L).longValue())));
      
      formvariablesList = outer.addOrder(Order.asc("sequence")).list();
      for (int i = 0; i < formvariablesList.size(); i++)
      {
        FormVariablesMap fvaiablemap = (FormVariablesMap)formvariablesList.get(i);
        if (fvaiablemap != null)
        {
          Variables variable = fvaiablemap.getVariable();
          logger.info("String.valueOf(variable.getVariableId())" + String.valueOf(variable.getVariableId()));
          if (!vidlist.contains(String.valueOf(variable.getVariableId())))
          {
            formvariablesListNew.add(fvaiablemap);
            vidlist.add(String.valueOf(variable.getVariableId()));
            logger.info("String.valueOf(variable.getVariableId()) not contain" + String.valueOf(variable.getVariableId()));
          }
        }
      }
      logger.info("formvariablesListNew size" + formvariablesListNew.size());
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllFormVariablesListForApplicant()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return formvariablesListNew;
  }
  
  public List getAllFormVariablesListForRequistion(long super_user_key)
  {
    logger.info("Inside getAllFormVariablesListForRequistion method");
    List formvariablesList = new ArrayList();
    List formvariablesListNew = new ArrayList();
    List vidlist = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      List appScreenList = Constant.getAllApplicantScreenList();
      

      Criteria outer = session.createCriteria(FormVariablesMap.class).add(Restrictions.eq("formCode", "REQUISITION_FORM")).add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key)));
      outer.add(Restrictions.eq("idValue", Long.valueOf(new Long(0L).longValue())));
      
      formvariablesList = outer.addOrder(Order.asc("sequence")).list();
      for (int i = 0; i < formvariablesList.size(); i++)
      {
        FormVariablesMap fvaiablemap = (FormVariablesMap)formvariablesList.get(i);
        if (fvaiablemap != null)
        {
          Variables variable = fvaiablemap.getVariable();
          logger.info("String.valueOf(variable.getVariableId())" + String.valueOf(variable.getVariableId()));
          if (!vidlist.contains(String.valueOf(variable.getVariableId())))
          {
            formvariablesListNew.add(fvaiablemap);
            vidlist.add(String.valueOf(variable.getVariableId()));
            logger.info("String.valueOf(variable.getVariableId()) not contain" + String.valueOf(variable.getVariableId()));
          }
        }
      }
      logger.info("formvariablesListNew size" + formvariablesListNew.size());
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllFormVariablesListForRequistion()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return formvariablesListNew;
  }
  
  public List getVariablesValues(long idvalue, String formcode)
  {
    logger.info("Inside getVariablesValues method");
    List valueList = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      valueList = session.createCriteria(VariablesValues.class).add(Restrictions.eq("idvalue", Long.valueOf(idvalue))).add(Restrictions.eq("formCode", formcode)).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getVariablesValues()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return valueList;
  }
  
  public List getVariablesValuesForApplicant(long idvalue)
  {
    logger.info("Inside getVariablesValuesForApplicant method");
    List valueList = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      List appScreenList = Constant.getAllApplicantScreenList();
      valueList = session.createCriteria(VariablesValues.class).add(Restrictions.eq("idvalue", Long.valueOf(idvalue))).add(Restrictions.in("formCode", appScreenList)).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getVariablesValuesForApplicant()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return valueList;
  }
  
  public List getAllVariableDetailsForPagination(int pageSize, int startIndex, long super_user_key, String dir_str, String sort_str)
  {
    logger.info("Inside getAllVariableDetailsForPagination method");
    List variableList = new ArrayList();
    List newVariableList = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      Criteria query = session.createCriteria(Variables.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key)));
      if (dir_str.equalsIgnoreCase("asc")) {
        query.addOrder(Order.asc(sort_str));
      } else {
        query.addOrder(Order.desc(sort_str));
      }
      query = query.setFirstResult(startIndex);
      query.setMaxResults(pageSize);
      variableList = query.list();
      for (int i = 0; i < variableList.size(); i++)
      {
        Variables fc = (Variables)variableList.get(i);
        if ((!StringUtils.isNullOrEmpty(fc.getVariableType())) && (fc.getVariableType().equals("list")))
        {
          Set variablListedValue = fc.getListData();
          
          List variablListedValueList = new ArrayList();
          String[] column = null;
          String defaultselect = null;
          StringBuffer sb = new StringBuffer();
          if (variablListedValue.size() > 0)
          {
            column = new String[variablListedValue.size()];
            
            Iterator itr = variablListedValue.iterator();
            int j = 0;
            while (itr.hasNext())
            {
              VariableListData vlistdata = (VariableListData)itr.next();
              variablListedValueList.add(vlistdata.getVariableValue());
              column[j] = vlistdata.getVariableValue();
              if ((!StringUtils.isNullOrEmpty(vlistdata.getIsDefault())) && (vlistdata.getIsDefault().equals("Y"))) {
                sb.append(vlistdata.getVariableValue());
              }
              j++;
            }
          }
          defaultselect = sb.toString();
          logger.info(" variable list data >>>> " + defaultselect);
          fc.setDefaultValue(defaultselect);
          newVariableList.add(fc);
        }
        else
        {
          newVariableList.add(fc);
        }
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllVariableDetailsForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return newVariableList;
  }
  
  public List getAllVariableDetails(long super_user_key)
  {
    logger.info("Inside getAllVariableDetails method");
    List variableList = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      variableList = session.createCriteria(Variables.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllVariableDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return variableList;
  }
  
  public Variables saveVariable(Variables variables, String[] listdata, String defaultselect)
  {
    logger.info("Inside saveVariable method");
    
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      session.save(variables);
      String compare = "no";
      if ((listdata != null) && (listdata.length > 0)) {
        for (int i = 0; i < listdata.length; i++)
        {
          VariableListData vlistdata = new VariableListData();
          vlistdata.setVariableId(variables.getVariableId());
          vlistdata.setVariableValue(listdata[i]);
          vlistdata.setSequence(i + 1);
          if ((defaultselect != null) && (defaultselect.length() > 0))
          {
            if (StringUtils.compare(String.valueOf(listdata[i]), String.valueOf(defaultselect))) {
              vlistdata.setIsDefault("Y");
            } else {
              vlistdata.setIsDefault("N");
            }
          }
          else {
            vlistdata.setIsDefault("N");
          }
          session.save(vlistdata);
        }
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on saveVariable()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return variables;
  }
  
  public Variables updateVariable(Variables variables, String[] listdata, String defaultselect)
  {
    logger.info("Inside saveVariable method");
    
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      session.update(variables);
      String compare = "no";
      if ((listdata != null) && (listdata.length > 0)) {
        for (int i = 0; i < listdata.length; i++)
        {
          VariableListData vlistdata = new VariableListData();
          vlistdata.setVariableId(variables.getVariableId());
          vlistdata.setVariableValue(listdata[i]);
          vlistdata.setSequence(i + 1);
          if ((defaultselect != null) && (defaultselect.length() > 0))
          {
            if (StringUtils.compare(String.valueOf(listdata[i]), String.valueOf(defaultselect))) {
              vlistdata.setIsDefault("Y");
            } else {
              vlistdata.setIsDefault("N");
            }
          }
          else {
            vlistdata.setIsDefault("N");
          }
          session.save(vlistdata);
        }
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on saveVariable()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return variables;
  }
  
  public Variables getVariablesDetailsByisMandatory(String id)
  {
    logger.info("Inside getVariablesDetailsByisMandatory method");
    Variables variable = null;
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      variable = (Variables)session.createCriteria(Variables.class).add(Restrictions.eq("variableId", new Long(id))).add(Restrictions.eq("listData.isDefault", "Y")).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getVariablesDetailsByisMandatory()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return variable;
  }
  
  public Variables getVariablesDetails(String id)
  {
    logger.info("Inside getVariablesDetails method");
    Variables variable = null;
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      variable = (Variables)session.createCriteria(Variables.class).add(Restrictions.eq("variableId", new Long(id))).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getVariablesDetails()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return variable;
  }
  
  public void deleteVariable(long id)
    throws ValidationException, Exception
  {
    logger.info("Inside deleteVariable method");
    
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String hql = "delete from Variables where variableId = :id";
      Query query = session.createQuery(hql);
      query.setLong("id", id);
      int rowCount = query.executeUpdate();
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteVariable()", e);
      if ((e instanceof ConstraintViolationException)) {
        throw new ValidationException("ER101", e.getMessage());
      }
      throw e;
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public Variables getVariableByVaribleCode(String variableCode, long super_user_key)
  {
    logger.info("Inside getVariableByVaribleCode method");
    Variables variable = null;
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      variable = (Variables)session.createCriteria(Variables.class).add(Restrictions.eq("variableCode", variableCode)).add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key))).uniqueResult();
      
      HibernateUtil.commitTransaction();
    }
    catch (Exception e)
    {
      logger.error("Exception on getVariableByVaribleCode()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return variable;
  }
  
  public void deleteVariableListData(long variableid)
  {
    logger.info("Inside deleteVariableListData method");
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      List variableListDataLIst = session.createCriteria(VariableListData.class).add(Restrictions.eq("variableId", Long.valueOf(variableid))).list();
      for (int i = 0; i < variableListDataLIst.size(); i++)
      {
        VariableListData variablelist = (VariableListData)variableListDataLIst.get(i);
        session.delete(variablelist);
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteVariableListData()", e);
      HibernateUtil.rollbackTransaction();
    }
    finally
    {
      logger.info("Inside finally method");
      HibernateUtil.closeSession();
    }
  }
  
  public List getVariableListData(String id)
  {
    logger.info("Inside getVariableListData method");
    List variablelisdata = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      variablelisdata = session.createCriteria(VariableListData.class).add(Restrictions.eq("variableId", new Long(id))).list();
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
  
  public List getFormVariablesMapByFormCode(String code, long super_user_key)
  {
    logger.info("Inside getFormVariablesMapByFormCode method");
    List formVariablesMap = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      formVariablesMap = session.createCriteria(FormVariablesMap.class).add(Restrictions.eq("formCode", code)).add(Restrictions.eq("idValue", new Long(0L))).add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getFormVariablesMapByFormCode()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return formVariablesMap;
  }
  
  public List getFormVariablesMapByFormCodeAndId(String code, long id)
  {
    logger.info("Inside getFormVariablesMapByFormCodeAndId method");
    List formVariablesMap = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      formVariablesMap = session.createCriteria(FormVariablesMap.class).add(Restrictions.eq("formCode", code)).add(Restrictions.eq("idValue", Long.valueOf(id))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getFormVariablesMapByFormCodeAndId()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return formVariablesMap;
  }
  
  public boolean isVariableIdAttachedToScreenCode(String code, long variableid, long super_user_key)
  {
    logger.info("Inside isVariableIdAttachedToScreenCode method");
    List formVariablesMap = new ArrayList();
    boolean success = false;
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      formVariablesMap = session.createCriteria(FormVariablesMap.class).add(Restrictions.eq("formCode", code)).add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key))).add(Restrictions.eq("idValue", Long.valueOf(new Long(0L).longValue()))).add(Restrictions.eq("variable.variableId", Long.valueOf(variableid))).list();
      if ((formVariablesMap != null) && (formVariablesMap.size() > 0)) {
        success = true;
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on isVariableIdAttachedToScreenCode()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return success;
  }
  
  public void deleteFormVariableMap(String formCode, long super_user_key)
  {
    logger.info("Inside deleteFormVariableMap method");
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      String hql = "delete from FormVariablesMap where formCode = :formCode and idValue=:idValue and super_user_key = :super_user_key";
      Query query = session.createQuery(hql);
      query.setString("formCode", formCode);
      query.setLong("idValue", 0L);
      query.setLong("super_user_key", super_user_key);
      int rowCount = query.executeUpdate();
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteFormVariableMap()", e);
    }
    finally
    {
      logger.info("Inside finally method");
      
      System.out.println("update");
    }
  }
  
  public void deleteFormVariableMapByFormCodeAndId(String formCode, long id, long super_user_key)
  {
    logger.info("Inside deleteFormVariableMapByFormCodeAndId method");
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      String hql = "delete from FormVariablesMap where formCode = :formCode and idValue=:idValue and super_user_key=:super_user_key";
      Query query = session.createQuery(hql);
      query.setString("formCode", formCode);
      query.setLong("idValue", id);
      query.setLong("super_user_key", super_user_key);
      int rowCount = query.executeUpdate();
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteFormVariableMapByFormCodeAndId()", e);
    }
    finally
    {
      logger.info("Inside finally method");
      
      System.out.println("update");
    }
  }
  
  public FormVariablesMap updateFormVariableMap(FormVariablesMap formvariableMap)
  {
    logger.info("Inside updateFormVariableMap method");
    
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      session.save(formvariableMap);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveVariable()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return formvariableMap;
  }
  
  public int getVariableByCriteraCount(String vname, String vtype, long super_user_key)
  {
    logger.info("Inside getVariableByCriteraCount method");
    int totalvariable = 0;
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      Criteria crit = session.createCriteria(Variables.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key)));
      if ((!StringUtils.isNullOrEmpty(vname)) && (!vname.equals("null"))) {
        crit.add(Restrictions.like("variableName", "%" + vname + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(vtype)) && (!vtype.equals("null"))) {
        crit.add(Restrictions.like("variableType", "%" + vtype + "%"));
      }
      crit.setProjection(Projections.rowCount());
      totalvariable = ((Integer)crit.list().get(0)).intValue();
    }
    catch (Exception e)
    {
      logger.error("Exception on getVariableByCriteraCount()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totalvariable;
  }
  
  public List getVariableByCritera(String vname, String vtype, long super_user_key, int start, int range)
  {
    logger.info("Inside getVariableByCritera method");
    List charList = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      Criteria crit = session.createCriteria(Variables.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(super_user_key)));
      if ((!StringUtils.isNullOrEmpty(vname)) && (!vname.equals("null"))) {
        crit.add(Restrictions.like("variableName", "%" + vname + "%"));
      }
      if ((!StringUtils.isNullOrEmpty(vtype)) && (!vtype.equals("null"))) {
        crit.add(Restrictions.like("variableType", "%" + vtype + "%"));
      }
      crit.setFirstResult(start);
      crit.setMaxResults(range);
      charList = crit.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getVariableByCritera()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return charList;
  }
}
