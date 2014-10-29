package com.performance.bean;

import com.bean.Department;
import com.bean.Designations;
import com.bean.Organization;
import com.util.DateUtil;
import java.util.Date;

public class Goal
{
  public long goalId;
  public String goalName;
  public String goalDesc;
  public TimePeriod timeperiod;
  public Organization organization;
  public Department department;
  public Designations designation;
  public String goalType;
  public String modifiable;
  public String createdBy;
  public Date createdDate;
  public Date updatedDate;
  public String updatedBy;
  public String departmentName;
  public String designationName;
  public String timeperiodName;
  
  public long getGoalId()
  {
    return this.goalId;
  }
  
  public void setGoalId(long goalId)
  {
    this.goalId = goalId;
  }
  
  public String getGoalName()
  {
    return this.goalName;
  }
  
  public void setGoalName(String goalName)
  {
    this.goalName = goalName;
  }
  
  public String getGoalDesc()
  {
    return this.goalDesc;
  }
  
  public void setGoalDesc(String goalDesc)
  {
    this.goalDesc = goalDesc;
  }
  
  public TimePeriod getTimeperiod()
  {
    return this.timeperiod;
  }
  
  public void setTimeperiod(TimePeriod timeperiod)
  {
    this.timeperiod = timeperiod;
    if (timeperiod != null)
    {
      String ret = "";
      try
      {
        String dt1 = DateUtil.convertDateToStringDate(timeperiod.getStartDate(), DateUtil.dateformatstandard);
        String dt2 = DateUtil.convertDateToStringDate(timeperiod.getEndDate(), DateUtil.dateformatstandard);
        ret = dt1 + " - " + dt2;
        this.timeperiodName = ret;
      }
      catch (Exception e) {}
    }
  }
  
  public String getGoalType()
  {
    return this.goalType;
  }
  
  public void setGoalType(String goalType)
  {
    this.goalType = goalType;
  }
  
  public String getModifiable()
  {
    return this.modifiable;
  }
  
  public void setModifiable(String modifiable)
  {
    this.modifiable = modifiable;
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
    if (department != null) {
      this.departmentName = department.getDepartmentName();
    }
  }
  
  public Designations getDesignation()
  {
    return this.designation;
  }
  
  public void setDesignation(Designations designation)
  {
    this.designation = designation;
    if (designation != null) {
      this.designationName = designation.getDesignationName();
    }
  }
}
