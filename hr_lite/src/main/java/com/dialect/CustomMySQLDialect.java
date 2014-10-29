package com.dialect;

import org.hibernate.Hibernate;
import org.hibernate.dialect.MySQL5Dialect;
import org.hibernate.type.NullableType;

public class CustomMySQLDialect
  extends MySQL5Dialect
{
  public CustomMySQLDialect()
  {
    registerHibernateType(3, Hibernate.DOUBLE.getName());
  }
}
