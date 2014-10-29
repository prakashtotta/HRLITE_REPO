package com.dao;

import org.hibernate.SessionFactory;

public class BaseDAO
{
  private SessionFactory sessionFactory;
  
  public SessionFactory getSessionFactory()
  {
    return this.sessionFactory;
  }
  
  public void setSessionFactory(SessionFactory sessionFactory)
  {
    this.sessionFactory = sessionFactory;
  }
}
