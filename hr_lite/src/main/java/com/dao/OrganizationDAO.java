package com.dao;

import com.bean.Department;
import com.bean.Location;
import com.bean.Organization;
import com.bean.OrganizationType;
import com.bean.User;
import com.common.ValidationException;
import com.util.StringUtils;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class OrganizationDAO
  extends HibernateDaoSupport
{
  protected static final Logger logger = Logger.getLogger(OrganizationDAO.class);
  
  public Organization getParentOrganizationInfo(long superUserKey)
  {
    logger.info("Inside getParentOrganizationInfo method");
    Organization org = null;
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      org = (Organization)session.createCriteria(Organization.class).add(Restrictions.isNull("parentOrg.orgId")).add(Restrictions.eq("isRoot", "Y")).add(Restrictions.eq("super_user_key", Long.valueOf(superUserKey))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getParentOrganizationInfo()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return org;
  }
  
  public List getChildOrganizations(long parentOrgId)
  {
    logger.info("Inside getChildOrganizations method");
    Organization org = null;
    Session session = null;
    List orgList = new ArrayList();
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      orgList = session.createCriteria(Organization.class).add(Restrictions.eq("parentOrg.orgId", new Long(parentOrgId))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getChildOrganizations()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return orgList;
  }
  
  public int getChildOrganizationsCount(long superUserKey, long parentOrgId)
  {
    logger.info("Inside getChildOrganizationsCount method");
    int totalchildorg = 0;
    Session session = null;
    List orgList = new ArrayList();
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      Criteria criteria = session.createCriteria(Organization.class).add(Restrictions.eq("status", "A"));
      if (parentOrgId != 0L) {
        criteria.add(Restrictions.disjunction().add(Restrictions.eq("parentOrg.orgId", new Long(parentOrgId))).add(Restrictions.eq("orgId", new Long(parentOrgId))));
      }
      criteria.add(Restrictions.eq("super_user_key", Long.valueOf(superUserKey)));
      criteria.setProjection(Projections.rowCount());
      totalchildorg = ((Integer)criteria.list().get(0)).intValue();
    }
    catch (Exception e)
    {
      logger.error("Exception on getChildOrganizationsCount()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totalchildorg;
  }
  
  public List getChildOrganizationsForPagination(long superUserKey, long parentOrgId, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getChildOrganizationsForPagination method");
    List orgList = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = session.createCriteria(Organization.class).add(Restrictions.eq("status", "A"));
      if (parentOrgId != 0L) {
        outer.add(Restrictions.disjunction().add(Restrictions.eq("parentOrg.orgId", new Long(parentOrgId))).add(Restrictions.eq("orgId", new Long(parentOrgId))));
      }
      outer.add(Restrictions.eq("super_user_key", Long.valueOf(superUserKey)));
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      orgList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getChildOrganizationsForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return orgList;
  }
  
  public List getDeptsByOrgForPagination(long orgId, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    logger.info("Inside getDeptsByOrgForPagination method");
    List deptList = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      


      Criteria outer = session.createCriteria(Department.class).createAlias("organization", "organization");
      
      outer.add(Restrictions.eq("organization.orgId", Long.valueOf(orgId)));
      outer.add(Restrictions.eq("status", "A"));
      if (dir_str.equalsIgnoreCase("asc")) {
        outer.addOrder(Order.asc(sort_str));
      } else {
        outer.addOrder(Order.desc(sort_str));
      }
      outer.setFirstResult(startIndex);
      outer.setMaxResults(pageSize);
      deptList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getDeptsByOrgForPagination()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return deptList;
  }
  
  public List getDeptsByOrg(long orgId)
  {
    logger.info("Inside getDeptsByOrg method");
    List deptList = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Query query = session.createQuery("from Department where org_id=" + orgId + " and status='A'");
      deptList = query.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getDeptsByOrg()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return deptList;
  }
  
  public int getCountOfDeptsByOrg(long orgId)
  {
    logger.info("Inside getCountOfDeptsByOrg method");
    int totalchildorg = 0;
    Session session = null;
    List orgList = new ArrayList();
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      Criteria criteria = session.createCriteria(Department.class).createAlias("organization", "organization").add(Restrictions.eq("organization.orgId", new Long(orgId)));
      criteria.setProjection(Projections.rowCount());
      totalchildorg = ((Integer)criteria.list().get(0)).intValue();
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfDeptsByOrg()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totalchildorg;
  }
  
  public List getAllOrganizationsWithoutParent(long parentOrgId, long superUserKey)
  {
    logger.info("Inside getAllOrganizationsWithoutParent method");
    Organization org = null;
    Session session = null;
    List orgList = new ArrayList();
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      orgList = session.createCriteria(Organization.class).add(Restrictions.ne("orgId", new Long(parentOrgId))).add(Restrictions.eq("super_user_key", Long.valueOf(superUserKey))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllOrganizationsWithoutParent()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return orgList;
  }
  
  public Organization getRootOrganization(long superUserKey)
  {
    logger.info("Inside getRootOrganization method");
    Organization org = null;
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      org = (Organization)session.createCriteria(Organization.class).add(Restrictions.eq("super_user_key", Long.valueOf(superUserKey))).add(Restrictions.eq("isRoot", "Y")).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getRootOrganization()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return org;
  }
  
  public List getAllOrganization()
  {
    logger.info("Inside getAllOrganization method");
    List orgList = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      orgList = session.createCriteria(Organization.class).add(Restrictions.eq("status", "A")).add(Restrictions.isNotNull("parentOrg.orgId")).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getAllOrganization()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return orgList;
  }
  
  public Location getLocation(String id)
  {
    logger.info("Inside getLocation method");
    Location loc = null;
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      loc = (Location)session.createCriteria(Location.class).add(Restrictions.eq("locationId", new Long(id))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getLocation()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return loc;
  }
  
  public Organization getOrganization(String id)
  {
    logger.info("Inside getOrganization method");
    Organization org = null;
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      org = (Organization)session.createCriteria(Organization.class).add(Restrictions.eq("orgId", new Long(id))).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getOrganization()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return org;
  }
  
  public Organization getOrganizationByCode(String code)
  {
    logger.info("Inside getOrganizationByCode method");
    Organization org = null;
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      org = (Organization)session.createCriteria(Organization.class).add(Restrictions.eq("orgCode", code)).uniqueResult();
    }
    catch (Exception e)
    {
      logger.error("Exception on getOrganizationByCode()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return org;
  }
  
  public void deleteOrganization(long orgId)
    throws ValidationException, Exception
  {
    logger.info("Inside deleteOrganization method");
    
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String hql = "delete from Organization where orgId = :id";
      Query query = session.createQuery(hql);
      query.setLong("id", orgId);
      int rowCount = query.executeUpdate();
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteOrganization()", e);
      if ((e instanceof ConstraintViolationException)) {
        throw new ValidationException("ER100", e.getMessage());
      }
      throw e;
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void deleteDepartment(long departmentId)
    throws ValidationException, Exception
  {
    logger.info("Inside deleteDepartment method");
    
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String hql = "delete from Department where departmentId = :departmentId";
      Query query = session.createQuery(hql);
      query.setLong("departmentId", departmentId);
      int rowCount = query.executeUpdate();
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteDepartment()", e);
      if ((e instanceof ConstraintViolationException)) {
        throw new ValidationException("ER103", e.getMessage());
      }
      throw e;
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public void deleteProjectCode(long projectId)
    throws ValidationException, Exception
  {
    logger.info("Inside deleteProjectCode method");
    
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      String hql = "delete from ProjectCodes where projectId = :projectId";
      Query query = session.createQuery(hql);
      query.setLong("projectId", projectId);
      int rowCount = query.executeUpdate();
    }
    catch (Exception e)
    {
      logger.error("Exception on deleteProjectCode()", e);
      if ((e instanceof ConstraintViolationException)) {
        throw new ValidationException("ER104", e.getMessage());
      }
      throw e;
    }
    finally
    {
      logger.info("Inside finally method");
    }
  }
  
  public List getOrganizationsByCritera(User user, String orgname, String orgcode, String isLegal, long locationId, long orgtypeId, int start, int range)
  {
    logger.info("Inside getOrganizationsByCritera method");
    List charList = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      Criteria outer = session.createCriteria(Organization.class).add(Restrictions.eq("status", "A"));
      


      outer.add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      if (!StringUtils.isNullOrEmpty(orgname)) {
        outer.add(Restrictions.like("orgName", "%" + orgname + "%"));
      }
      if (!StringUtils.isNullOrEmpty(orgcode)) {
        outer.add(Restrictions.like("orgCode", orgcode));
      }
      if (!StringUtils.isNullOrEmpty(isLegal)) {
        outer.add(Restrictions.like("isLegal", isLegal));
      }
      if (locationId != 0L)
      {
        outer.createAlias("location", "location");
        outer.add(Restrictions.eq("location.locationId", new Long(locationId)));
      }
      if (orgtypeId != 0L)
      {
        outer.createAlias("organizationtype", "organizationtype");
        outer.add(Restrictions.eq("organizationtype.organizationTypeId", new Long(orgtypeId)));
      }
      outer.setFirstResult(start);
      outer.setMaxResults(range);
      charList = outer.list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getOrganizationsByCritera()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return charList;
  }
  
  public int getCountOfOrganizationByCritera(User user, String orgname, String orgcode, String isLegal, long locationId, long orgtypeId)
  {
    logger.info("Inside getCountOfOrganizationByCritera method");
    int totalcount = 0;
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      

      Criteria outer = session.createCriteria(Organization.class).add(Restrictions.eq("status", "A"));
      


      outer.add(Restrictions.eq("super_user_key", Long.valueOf(user.getSuper_user_key())));
      if (!StringUtils.isNullOrEmpty(orgname)) {
        outer.add(Restrictions.like("orgName", "%" + orgname + "%"));
      }
      if (!StringUtils.isNullOrEmpty(orgcode)) {
        outer.add(Restrictions.like("orgCode", orgcode));
      }
      if (!StringUtils.isNullOrEmpty(isLegal)) {
        outer.add(Restrictions.like("isLegal", isLegal));
      }
      if (locationId != 0L)
      {
        outer.createAlias("location", "location");
        outer.add(Restrictions.eq("location.locationId", new Long(locationId)));
      }
      if (orgtypeId != 0L)
      {
        outer.createAlias("organizationtype", "organizationtype");
        outer.add(Restrictions.eq("organizationtype.organizationTypeId", new Long(orgtypeId)));
      }
      outer.setProjection(Projections.rowCount());
      totalcount = ((Integer)outer.list().get(0)).intValue();
    }
    catch (Exception e)
    {
      logger.error("Exception on getCountOfOrganizationByCritera()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return totalcount;
  }
  
  public Organization saveOrganization(Organization org)
  {
    logger.info("Inside saveOrganization method");
    
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.save(org);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveOrganization()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return org;
  }
  
  public Location saveLocation(Location location)
  {
    logger.info("Inside saveLocation method");
    
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      session.save(location);
    }
    catch (Exception e)
    {
      logger.error("Exception on saveLocation()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return location;
  }
  
  public Organization updateOrganization(Organization org)
  {
    logger.info("Inside updateOrganization method");
    
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      session.update(org);
    }
    catch (Exception e)
    {
      logger.error("Exception on updateOrganization()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return org;
  }
  
  public List getOrganizationTypeList()
  {
    logger.info("Inside getOrganizationTypeList method");
    List selList = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      selList = session.createCriteria(OrganizationType.class).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getOrganizationTypeList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return selList;
  }
  
  public List getLocationList(long superUserKey)
  {
    logger.info("Inside getLocationList method");
    List locList = new ArrayList();
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      locList = session.createCriteria(Location.class).add(Restrictions.eq("status", "A")).add(Restrictions.eq("super_user_key", Long.valueOf(superUserKey))).list();
    }
    catch (Exception e)
    {
      logger.error("Exception on getLocationList()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return locList;
  }
  
  public String isOrgCodeExist(String orgcode, long super_user_key)
  {
    logger.info("Inside isOrgCodeExist method");
    String organizationcode = null;
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      String hql = "select orgCode from Organization a where a.orgCode = :orgCode and a.status != :status and a.super_user_key=:super_user_key";
      Query query = session.createQuery(hql);
      query.setString("orgCode", orgcode);
      query.setString("status", "D");
      query.setLong("super_user_key", super_user_key);
      Object ob = query.uniqueResult();
      if (ob != null) {
        organizationcode = (String)ob;
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on isOrgCodeExist()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return organizationcode;
  }
  
  public String isDepartmentCodeExist(String departmentCode, long orgid)
  {
    logger.info("Inside isDepartmentCodeExist method");
    logger.info("org Id >> " + orgid);
    String deptcode = null;
    Session session = null;
    try
    {
      session = getHibernateTemplate().getSessionFactory().getCurrentSession();
      
      String hql = "select departmentCode from Department a where a.departmentCode= :departmentCode and a.status != :status and a.organization.orgId = :orgid";
      
      Query query = session.createQuery(hql);
      query.setString("departmentCode", departmentCode);
      query.setString("status", "D");
      query.setLong("orgid", orgid);
      

      Object ob = query.uniqueResult();
      if (ob != null) {
        deptcode = (String)ob;
      }
    }
    catch (Exception e)
    {
      logger.error("Exception on isDepartmentCodeExist()", e);
    }
    finally
    {
      logger.info("Inside finally method");
    }
    return deptcode;
  }
}
