package com.dao;

import com.bean.AgencyRedemption;
import com.bean.ReferralAgencyApplicants;
import com.bean.ReferralRedemption;
import com.bean.RefferalScheme;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class ReferralTXDAO
  extends HibernateDaoSupport
{
  protected static final Logger logger = Logger.getLogger(ReferralTXDAO.class);
  
  public void updateReferralAgencyApplicants(ReferralAgencyApplicants refagencyApp)
  {
    logger.info("Inside updateReferralAgencyApplicants method");
    getHibernateTemplate().update(refagencyApp);
  }
  
  public void saveReferralRedemptin(ReferralRedemption refred)
  {
    logger.info("Inside saveReferralRedemptin method");
    getHibernateTemplate().save(refred);
  }
  
  public void saveAgencyRedemptin(AgencyRedemption agred)
  {
    logger.info("Inside saveAgencyRedemptin method");
    getHibernateTemplate().save(agred);
  }
  
  public RefferalScheme getReferralScheme(long refschemeId)
  {
    logger.info("Inside getReferralScheme method");
    
    RefferalScheme refscheme = (RefferalScheme)getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(RefferalScheme.class).add(Restrictions.eq("refferalScheme_Id", Long.valueOf(refschemeId))).uniqueResult();
    

    return refscheme;
  }
}
