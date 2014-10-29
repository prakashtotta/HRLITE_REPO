package com.form;

import com.bean.ReferAFriend;
import com.bean.RefferalEmployee;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;

public class ReferAFriendForm
  extends ActionForm
{
  public long referafriendId;
  public String name;
  public String emailId;
  public String note;
  public String status;
  public String createdBy;
  private Date createdDate;
  private Date updatedDate;
  private String updatedBy;
  public long jobRequisitionId;
  private String jobTitle;
  
  public long getReferafriendId()
  {
    return this.referafriendId;
  }
  
  public void setReferafriendId(long referafriendId)
  {
    this.referafriendId = referafriendId;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public String getEmailId()
  {
    return this.emailId;
  }
  
  public void setEmailId(String emailId)
  {
    this.emailId = emailId;
  }
  
  public String getNote()
  {
    return this.note;
  }
  
  public void setNote(String note)
  {
    this.note = note;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
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
  
  public ReferAFriend toValue(ReferAFriend reffriend, HttpServletRequest request)
    throws Exception
  {
    reffriend.setName(this.name);
    reffriend.setEmailId(this.emailId);
    reffriend.setNote(this.note);
    reffriend.setStatus("A");
    RefferalEmployee user1 = (RefferalEmployee)request.getSession().getAttribute("employee_refferal_data");
    reffriend.setCreatedBy(user1.getEmployeename());
    reffriend.setCreatedDate(new Date());
    reffriend.setUpdatedBy(null);
    reffriend.setUpdatedDate(null);
    reffriend.setJobRequisitionId(this.jobRequisitionId);
    reffriend.setJobTitle(this.jobTitle);
    return reffriend;
  }
  
  public void fromValue(ReferAFriend reffriend, HttpServletRequest request)
    throws Exception
  {
    this.referafriendId = reffriend.getReferafriendId();
    this.name = reffriend.getName();
    this.emailId = reffriend.getEmailId();
    this.note = reffriend.getNote();
    setJobRequisitionId(reffriend.jobRequisitionId);
    setJobTitle(reffriend.jobTitle);
  }
  
  public long getJobRequisitionId()
  {
    return this.jobRequisitionId;
  }
  
  public void setJobRequisitionId(long jobRequisitionId)
  {
    this.jobRequisitionId = jobRequisitionId;
  }
  
  public String getJobTitle()
  {
    return this.jobTitle;
  }
  
  public void setJobTitle(String jobTitle)
  {
    this.jobTitle = jobTitle;
  }
}
