package com.performance.form;

import java.util.Date;
import java.util.List;
import java.util.Set;
import org.apache.struts.action.ActionForm;

public class GoalForm
  extends ActionForm
{
  public long goalId;
  public String goalName;
  public String goalDesc;
  public long timePeriodId;
  public long orgId;
  public long departmentId;
  public long designationId;
  public String goalType;
  public String modifiable;
  public String createdBy;
  public Date createdDate;
  public Date updatedDate;
  public String updatedBy;
  private List timePeriodList;
  private List organizationList;
  private List departmentList;
  private List designationList;
  public String initiationNote;
  public Set usersset;
  public List useridListVal;
  
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
  
  public long getOrgId()
  {
    return this.orgId;
  }
  
  public void setOrgId(long orgId)
  {
    this.orgId = orgId;
  }
  
  public long getDepartmentId()
  {
    return this.departmentId;
  }
  
  public void setDepartmentId(long departmentId)
  {
    this.departmentId = departmentId;
  }
  
  public long getDesignationId()
  {
    return this.designationId;
  }
  
  public void setDesignationId(long designationId)
  {
    this.designationId = designationId;
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
  
  public long getTimePeriodId()
  {
    return this.timePeriodId;
  }
  
  public void setTimePeriodId(long timePeriodId)
  {
    this.timePeriodId = timePeriodId;
  }
  
  public List getTimePeriodList()
  {
    return this.timePeriodList;
  }
  
  public void setTimePeriodList(List timePeriodList)
  {
    this.timePeriodList = timePeriodList;
  }
  
  public List getOrganizationList()
  {
    return this.organizationList;
  }
  
  public void setOrganizationList(List organizationList)
  {
    this.organizationList = organizationList;
  }
  
  public List getDepartmentList()
  {
    return this.departmentList;
  }
  
  public void setDepartmentList(List departmentList)
  {
    this.departmentList = departmentList;
  }
  
  public List getDesignationList()
  {
    return this.designationList;
  }
  
  public void setDesignationList(List designationList)
  {
    this.designationList = designationList;
  }
  
  public String getInitiationNote()
  {
    return this.initiationNote;
  }
  
  public void setInitiationNote(String initiationNote)
  {
    this.initiationNote = initiationNote;
  }
  
  public Set getUsersset()
  {
    return this.usersset;
  }
  
  public void setUsersset(Set usersset)
  {
    this.usersset = usersset;
  }
  
  public List getUseridListVal()
  {
    return this.useridListVal;
  }
  
  public void setUseridListVal(List useridListVal)
  {
    this.useridListVal = useridListVal;
  }
}
