package com.dao;

import com.bean.filter.BusinessCriteria;
import com.bean.filter.ScreenFields;
import com.util.StringUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class BusinessFilterDAO
  extends HibernateDaoSupport
{
  protected static final Logger logger = Logger.getLogger(BusinessFilterDAO.class);
  
  public List<BusinessCriteria> getBusinessCriteriasForApplicantValidationWithoutSilent(long jobRequistionId)
  {
    logger.info("Inside getBusinessCriteriasForApplicantValidationWithoutSilent method");
    List<BusinessCriteria> criteriaList = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      String[] types = { "REQUISITION_ONLY", "ALL_APPLICANTS" };
      String hqlquery = "select j from BusinessCriteria j where j.status = 'A' and j.isSilent != 'Y' and  j.type in (:types) and j.idvalue in (:reqlist)) ";
      

      List<Long> reqList = new ArrayList();
      reqList.add(Long.valueOf(jobRequistionId));
      reqList.add(new Long(0L));
      
      Query query = session.createQuery(hqlquery);
      query.setParameterList("types", types);
      query.setParameterList("reqlist", reqList);
      
      logger.info(query.getQueryString());
      criteriaList = query.list();
      
      logger.info("criteriaList size" + criteriaList.size());
    }
    catch (Exception e)
    {
      logger.error("Exception on getBusinessCriteriasForApplicantValidationWithoutSilent()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return criteriaList;
  }
  
  public List<BusinessCriteria> getBusinessCriteriasForApplicantValidationWithSilent(long jobRequistionId)
  {
    logger.info("Inside getBusinessCriteriaForApplicantValidation method");
    List<BusinessCriteria> criteriaList = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      String[] types = { "REQUISITION_ONLY", "ALL_APPLICANTS" };
      String hqlquery = "select j from BusinessCriteria j where j.status = 'A' and j.isSilent = 'Y' and  j.type in (:types) and j.idvalue in (:reqlist)) ";
      

      List<Long> reqList = new ArrayList();
      reqList.add(Long.valueOf(jobRequistionId));
      reqList.add(new Long(0L));
      
      Query query = session.createQuery(hqlquery);
      query.setParameterList("types", types);
      query.setParameterList("reqlist", reqList);
      
      logger.info(query.getQueryString());
      criteriaList = query.list();
      
      logger.info("criteriaList size" + criteriaList.size());
    }
    catch (Exception e)
    {
      logger.error("Exception on getBusinessCriteriaForApplicantValidation()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return criteriaList;
  }
  
  public Map<String, List<String>> getVisibleAndMandatoryScreenFiledsByScreenCode(String screencode, long superUserKey)
  {
    logger.info("Inside getVisibleAndMandatoryScreenFiledsByScreenCode method" + screencode + superUserKey);
    Map<String, List<String>> m = new HashMap();
    List<String> filedlist = new ArrayList();
    List<String> mandatorylist = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = null;
      List filedlisttemp = new ArrayList();
      if (isScreenFieldPresent(screencode, superUserKey))
      {
        outer = session.createCriteria(ScreenFields.class).add(Restrictions.eq("screenCode", screencode)).add(Restrictions.eq("super_user_key", Long.valueOf(superUserKey))).add(Restrictions.eq("isvisible", "Y"));
        

        filedlisttemp = outer.list();
      }
      else
      {
        outer = session.createCriteria(ScreenFields.class).add(Restrictions.eq("screenCode", screencode)).add(Restrictions.eq("super_user_key", new Long(0L))).add(Restrictions.eq("isvisible", "Y"));
        

        filedlisttemp = outer.list();
      }
      if ((filedlisttemp != null) && (filedlisttemp.size() > 0)) {
        for (int i = 0; i < filedlisttemp.size(); i++)
        {
          ScreenFields s = (ScreenFields)filedlisttemp.get(i);
          filedlist.add(s.getFieldcode());
          if ((!StringUtils.isNullOrEmpty(s.getIsMandatory())) && (s.getIsMandatory().equals("Y"))) {
            mandatorylist.add(s.getFieldcode());
          }
        }
      }
      m.put("VISIBLE_FIELDS_LIST", filedlist);
      m.put("MANDATORY_FIELDS_LIST", mandatorylist);
    }
    catch (Exception e)
    {
      logger.error("Exception on getVisibleAndMandatoryScreenFiledsByScreenCode()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return m;
  }
  
  private boolean isScreenFieldPresent(String screenCode, long superUserKey)
  {
    logger.info("Inside isScreenFieldNotPresent method");
    boolean ispresnt = false;
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      List screenfieldList = session.createCriteria(ScreenFields.class).add(Restrictions.eq("screenCode", screenCode)).add(Restrictions.eq("super_user_key", Long.valueOf(superUserKey))).list();
      if ((screenfieldList != null) && (screenfieldList.size() > 0)) {
        ispresnt = true;
      }
      logger.info("Inside isScreenFieldNotPresent method" + screenCode + " " + ispresnt);
    }
    catch (Exception e)
    {
      logger.error("Exception on isScreenFieldNotPresent()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return ispresnt;
  }
  
  public List getVisibleScreenFieldList(String screencode, long superUserKey)
  {
    logger.info("Inside getVisibleScreenFieldList method");
    logger.info("*** screen Name : " + screencode);
    List screenfieldList = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      if (isScreenFieldPresent(screencode, superUserKey)) {
        screenfieldList = session.createCriteria(ScreenFields.class).add(Restrictions.eq("screenCode", screencode)).add(Restrictions.eq("super_user_key", Long.valueOf(superUserKey))).add(Restrictions.eq("status", "A")).add(Restrictions.eq("isvisible", "Y")).list();
      } else {
        screenfieldList = session.createCriteria(ScreenFields.class).add(Restrictions.eq("screenCode", screencode)).add(Restrictions.eq("super_user_key", new Long(0L))).add(Restrictions.eq("status", "A")).add(Restrictions.eq("isvisible", "Y")).list();
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getVisibleScreenFieldList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return screenfieldList;
  }
  
  public List<ScreenFields> getVisibleScreenFiledsByScreenCode(String screencode)
  {
    logger.info("Inside getVisibleScreenFiledsByScreenCode method");
    
    List<ScreenFields> filedlist = new ArrayList();
    
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = null;
      
      outer = session.createCriteria(ScreenFields.class).add(Restrictions.eq("screenCode", screencode)).add(Restrictions.eq("isvisible", "Y"));
      
      filedlist = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getVisibleScreenFiledsByScreenCode()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return filedlist;
  }
  
  public List<ScreenFields> getVisibleMandatoryScreenFiledsByScreenCode(String screencode, long superUserKey)
  {
    logger.info("Inside getVisibleMandatoryScreenFiledsByScreenCode method");
    
    List<ScreenFields> filedlist = new ArrayList();
    
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = null;
      if (isScreenFieldPresent(screencode, superUserKey))
      {
        outer = session.createCriteria(ScreenFields.class).add(Restrictions.eq("screenCode", screencode)).add(Restrictions.eq("super_user_key", Long.valueOf(superUserKey))).add(Restrictions.eq("isMandatory", "Y")).add(Restrictions.eq("isvisible", "Y"));
        


        filedlist = outer.list();
      }
      else
      {
        outer = session.createCriteria(ScreenFields.class).add(Restrictions.eq("screenCode", screencode)).add(Restrictions.eq("super_user_key", new Long(0L))).add(Restrictions.eq("isMandatory", "Y")).add(Restrictions.eq("isvisible", "Y"));
        


        filedlist = outer.list();
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on getVisibleMandatoryScreenFiledsByScreenCode()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return filedlist;
  }
  
  public List<ScreenFields> getVisibleMandatoryScreenFiledsByScreenCode(String screencode, String groupCode)
  {
    logger.info("Inside getVisibleMandatoryScreenFiledsByScreenCode method");
    
    List<ScreenFields> filedlist = new ArrayList();
    
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = null;
      
      outer = session.createCriteria(ScreenFields.class).add(Restrictions.eq("screenCode", screencode)).add(Restrictions.eq("groupCode", groupCode)).add(Restrictions.eq("isMandatory", "Y")).add(Restrictions.eq("isvisible", "Y"));
      
      filedlist = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getVisibleMandatoryScreenFiledsByScreenCode()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return filedlist;
  }
}
