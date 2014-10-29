package com.leave.bean;

import com.bean.Department;
import com.bean.Organization;

public class WorkDays
{
  public long workdaysId;
  public Organization organization;
  public Department department;
  public int hours;
  public String dayName;
  public int daysRank;
  
  public long getWorkdaysId()
  {
    return this.workdaysId;
  }
  
  public void setWorkdaysId(long workdaysId)
  {
    this.workdaysId = workdaysId;
  }
  
  public Organization getOrganization()
  {
    return this.organization;
  }
  
  public void setOrganization(Organization organization)
  {
    this.organization = organization;
  }
  
  public Department getDepartment()
  {
    return this.department;
  }
  
  public void setDepartment(Department department)
  {
    this.department = department;
  }
  
  public int getHours()
  {
    return this.hours;
  }
  
  public void setHours(int hours)
  {
    this.hours = hours;
  }
  
  public String getDayName()
  {
    return this.dayName;
  }
  
  public void setDayName(String dayName)
  {
    this.dayName = dayName;
  }
  
  public int getDaysRank()
  {
    return this.daysRank;
  }
  
  public void setDaysRank(int daysRank)
  {
    this.daysRank = daysRank;
  }
}
