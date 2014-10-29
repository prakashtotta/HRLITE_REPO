package com.leave.bean;

import com.bean.Department;
import com.bean.Organization;
import java.util.Date;

public class Holidays
{
  public long holidaysId;
  public String description;
  public LeaveTimePeriod leavetimeperiod;
  public Organization organization;
  public Department department;
  public Date leaveDate;
  public int recurring;
  public int hours;
  
  public long getHolidaysId()
  {
    return this.holidaysId;
  }
  
  public void setHolidaysId(long holidaysId)
  {
    this.holidaysId = holidaysId;
  }
  
  public String getDescription()
  {
    return this.description;
  }
  
  public void setDescription(String description)
  {
    this.description = description;
  }
  
  public LeaveTimePeriod getLeavetimeperiod()
  {
    return this.leavetimeperiod;
  }
  
  public void setLeavetimeperiod(LeaveTimePeriod leavetimeperiod)
  {
    this.leavetimeperiod = leavetimeperiod;
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
  
  public Date getLeaveDate()
  {
    return this.leaveDate;
  }
  
  public void setLeaveDate(Date leaveDate)
  {
    this.leaveDate = leaveDate;
  }
  
  public int getRecurring()
  {
    return this.recurring;
  }
  
  public void setRecurring(int recurring)
  {
    this.recurring = recurring;
  }
  
  public int getHours()
  {
    return this.hours;
  }
  
  public void setHours(int hours)
  {
    this.hours = hours;
  }
}
