package com.performance.bean;

import com.bean.Department;
import com.bean.Designations;
import com.bean.Organization;
import java.util.Date;

public class GoalInitiation
{
  public long goalInitiationId;
  public TimePeriod timeperiod;
  public Organization organization;
  public Department department;
  public Designations designation;
  public String allEmployee;
  public String note;
  public String createdBy;
  public Date createdDate;
  public Date updatedDate;
  public String updatedBy;
  
  public long getGoalInitiationId()
  {
    return this.goalInitiationId;
  }
  
  public void setGoalInitiationId(long goalInitiationId)
  {
    this.goalInitiationId = goalInitiationId;
  }
  
  public TimePeriod getTimeperiod()
  {
    return this.timeperiod;
  }
  
  public void setTimeperiod(TimePeriod timeperiod)
  {
    this.timeperiod = timeperiod;
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
  
  public Designations getDesignation()
  {
    return this.designation;
  }
  
  public void setDesignation(Designations designation)
  {
    this.designation = designation;
  }
  
  public String getAllEmployee()
  {
    return this.allEmployee;
  }
  
  public void setAllEmployee(String allEmployee)
  {
    this.allEmployee = allEmployee;
  }
  
  public String getNote()
  {
    return this.note;
  }
  
  public void setNote(String note)
  {
    this.note = note;
  }
  
  public String getCreatedBy()
  {
    return this.createdBy;
  }
  
  public void setCreatedBy(String createdBy)
  {
    this.createdBy = createdBy;
  }
  
  public Date getCreatedDate()
  {
    return this.createdDate;
  }
  
  public void setCreatedDate(Date createdDate)
  {
    this.createdDate = createdDate;
  }
  
  public Date getUpdatedDate()
  {
    return this.updatedDate;
  }
  
  public void setUpdatedDate(Date updatedDate)
  {
    this.updatedDate = updatedDate;
  }
  
  public String getUpdatedBy()
  {
    return this.updatedBy;
  }
  
  public void setUpdatedBy(String updatedBy)
  {
    this.updatedBy = updatedBy;
  }
}
