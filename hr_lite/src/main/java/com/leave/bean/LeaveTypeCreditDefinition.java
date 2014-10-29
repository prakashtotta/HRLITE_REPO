package com.leave.bean;

import com.bean.Department;
import com.bean.Designations;
import com.bean.Organization;

public class LeaveTypeCreditDefinition
{
  public long leaveCreditTypeDefId;
  public String name;
  public Organization organization;
  public Department department;
  public LeaveTimePeriod leavetimeperiod;
  public LeaveTimePeriodSub leavetimeperiodsub;
  public LeaveTypes leavetype;
  public Designations designation;
  public double noofleaves;
  public String status;
  
  public long getLeaveCreditTypeDefId()
  {
    return this.leaveCreditTypeDefId;
  }
  
  public void setLeaveCreditTypeDefId(long leaveCreditTypeDefId)
  {
    this.leaveCreditTypeDefId = leaveCreditTypeDefId;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public Organization getOrganization()
  {
    return this.organization;
  }
  
  public void setOrganization(Organization organization)
  {
    this.organization = organization;
  }
  
  public LeaveTimePeriod getLeavetimeperiod()
  {
    return this.leavetimeperiod;
  }
  
  public void setLeavetimeperiod(LeaveTimePeriod leavetimeperiod)
  {
    this.leavetimeperiod = leavetimeperiod;
  }
  
  public LeaveTimePeriodSub getLeavetimeperiodsub()
  {
    return this.leavetimeperiodsub;
  }
  
  public void setLeavetimeperiodsub(LeaveTimePeriodSub leavetimeperiodsub)
  {
    this.leavetimeperiodsub = leavetimeperiodsub;
  }
  
  public LeaveTypes getLeavetype()
  {
    return this.leavetype;
  }
  
  public void setLeavetype(LeaveTypes leavetype)
  {
    this.leavetype = leavetype;
  }
  
  public Designations getDesignation()
  {
    return this.designation;
  }
  
  public void setDesignation(Designations designation)
  {
    this.designation = designation;
  }
  
  public double getNoofleaves()
  {
    return this.noofleaves;
  }
  
  public void setNoofleaves(double noofleaves)
  {
    this.noofleaves = noofleaves;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public Department getDepartment()
  {
    return this.department;
  }
  
  public void setDepartment(Department department)
  {
    this.department = department;
  }
}
