package com.form;

import com.bean.User;
import com.bean.onboard.OnBoardingTaskDefinitions;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;

public class OnBoardingTaskDefiForm
  extends ActionForm
{
  public long taskdefid;
  public String taskName;
  public String taskDesc;
  public Long primaryOwnerId;
  public String primaryOwnername;
  public String isGroup = "N";
  public String status;
  public int noofdays;
  public String eventType;
  public String createdBy;
  public Date createdDate;
  public Date updatedDate;
  public String updatedBy;
  public Set otherowners;
  public List atrributes;
  public String readPreview;
  public String appliedcri;
  public List AttributeList;
  public String attribute;
  public String isMandatory;
  public long taskattid;
  public List onboardtaskList;
  public String start;
  public String range;
  public String results;
  public String primaryownernamehidden;
  
  public void toValue(OnBoardingTaskDefinitions OnBoardingTaskDefinitions, HttpServletRequest request)
    throws Exception
  {
    if (this.primaryOwnerId.longValue() != 0L)
    {
      User primaryOwner = new User();
      primaryOwner.setUserId(this.primaryOwnerId.longValue());
      OnBoardingTaskDefinitions.setPrimaryOwner(primaryOwner);
    }
  }
  
  public void fromValue(OnBoardingTaskDefinitions OnBoardingTaskDefinitions, HttpServletRequest request)
    throws Exception
  {
    User user1 = (User)request.getSession().getAttribute("user_data");
    if (OnBoardingTaskDefinitions.getPrimaryOwner() != null)
    {
      this.primaryOwnerId = Long.valueOf(OnBoardingTaskDefinitions.getPrimaryOwner().getUserId());
      this.primaryOwnername = (OnBoardingTaskDefinitions.getPrimaryOwner().getFirstName() + " " + OnBoardingTaskDefinitions.getPrimaryOwner().getLastName());
    }
  }
  
  public long getTaskdefid()
  {
    return this.taskdefid;
  }
  
  public void setTaskdefid(long taskdefid)
  {
    this.taskdefid = taskdefid;
  }
  
  public String getTaskName()
  {
    return this.taskName;
  }
  
  public void setTaskName(String taskName)
  {
    this.taskName = taskName;
  }
  
  public String getTaskDesc()
  {
    return this.taskDesc;
  }
  
  public void setTaskDesc(String taskDesc)
  {
    this.taskDesc = taskDesc;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public int getNoofdays()
  {
    return this.noofdays;
  }
  
  public void setNoofdays(int noofdays)
  {
    this.noofdays = noofdays;
  }
  
  public String getEventType()
  {
    return this.eventType;
  }
  
  public void setEventType(String eventType)
  {
    this.eventType = eventType;
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
  
  public Set getOtherowners()
  {
    return this.otherowners;
  }
  
  public void setOtherowners(Set otherowners)
  {
    this.otherowners = otherowners;
  }
  
  public List getAtrributes()
  {
    return this.atrributes;
  }
  
  public void setAtrributes(List atrributes)
  {
    this.atrributes = atrributes;
  }
  
  public String getReadPreview()
  {
    return this.readPreview;
  }
  
  public void setReadPreview(String readPreview)
  {
    this.readPreview = readPreview;
  }
  
  public String getAppliedcri()
  {
    return this.appliedcri;
  }
  
  public void setAppliedcri(String appliedcri)
  {
    this.appliedcri = appliedcri;
  }
  
  public Long getPrimaryOwnerId()
  {
    return this.primaryOwnerId;
  }
  
  public void setPrimaryOwnerId(Long primaryOwnerId)
  {
    this.primaryOwnerId = primaryOwnerId;
  }
  
  public String getPrimaryOwnername()
  {
    return this.primaryOwnername;
  }
  
  public void setPrimaryOwnername(String primaryOwnername)
  {
    this.primaryOwnername = primaryOwnername;
  }
  
  public List getAttributeList()
  {
    return this.AttributeList;
  }
  
  public void setAttributeList(List attributeList)
  {
    this.AttributeList = attributeList;
  }
  
  public String getAttribute()
  {
    return this.attribute;
  }
  
  public void setAttribute(String attribute)
  {
    this.attribute = attribute;
  }
  
  public String getIsMandatory()
  {
    return this.isMandatory;
  }
  
  public void setIsMandatory(String isMandatory)
  {
    this.isMandatory = isMandatory;
  }
  
  public long getTaskattid()
  {
    return this.taskattid;
  }
  
  public void setTaskattid(long taskattid)
  {
    this.taskattid = taskattid;
  }
  
  public List getOnboardtaskList()
  {
    return this.onboardtaskList;
  }
  
  public void setOnboardtaskList(List onboardtaskList)
  {
    this.onboardtaskList = onboardtaskList;
  }
  
  public String getStart()
  {
    return this.start;
  }
  
  public void setStart(String start)
  {
    this.start = start;
  }
  
  public String getRange()
  {
    return this.range;
  }
  
  public void setRange(String range)
  {
    this.range = range;
  }
  
  public String getResults()
  {
    return this.results;
  }
  
  public void setResults(String results)
  {
    this.results = results;
  }
  
  public String getPrimaryownernamehidden()
  {
    return this.primaryownernamehidden;
  }
  
  public void setPrimaryownernamehidden(String primaryownernamehidden)
  {
    this.primaryownernamehidden = primaryownernamehidden;
  }
  
  public String getIsGroup()
  {
    return this.isGroup;
  }
  
  public void setIsGroup(String isGroup)
  {
    this.isGroup = isGroup;
  }
}
