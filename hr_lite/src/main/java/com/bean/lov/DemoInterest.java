package com.bean.lov;

import java.util.Date;

public class DemoInterest
{
  public long demoId;
  public String name;
  public String email;
  public String phone;
  public String company;
  public Date createdDate;
  
  public long getDemoId()
  {
    return this.demoId;
  }
  
  public void setDemoId(long demoId)
  {
    this.demoId = demoId;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public String getEmail()
  {
    return this.email;
  }
  
  public void setEmail(String email)
  {
    this.email = email;
  }
  
  public String getPhone()
  {
    return this.phone;
  }
  
  public void setPhone(String phone)
  {
    this.phone = phone;
  }
  
  public String getCompany()
  {
    return this.company;
  }
  
  public void setCompany(String company)
  {
    this.company = company;
  }
  
  public Date getCreatedDate()
  {
    return this.createdDate;
  }
  
  public void setCreatedDate(Date createdDate)
  {
    this.createdDate = createdDate;
  }
}
