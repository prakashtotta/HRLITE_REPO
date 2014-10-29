package com.form;

import com.bean.Designations;
import com.bean.User;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;

public class DesignationForm
  extends ActionForm
{
  public long designationId;
  public String designationCode;
  public String designationName;
  public String designationDesc;
  public String createdBy;
  public Date createdDate;
  private Date updatedDate;
  private String updatedBy;
  public String status;
  
  public long getDesignationId()
  {
    return this.designationId;
  }
  
  public void setDesignationId(long designationId)
  {
    this.designationId = designationId;
  }
  
  public String getDesignationCode()
  {
    return this.designationCode;
  }
  
  public void setDesignationCode(String designationCode)
  {
    this.designationCode = designationCode;
  }
  
  public String getDesignationName()
  {
    return this.designationName;
  }
  
  public void setDesignationName(String designationName)
  {
    this.designationName = designationName;
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
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public String getDesignationDesc()
  {
    return this.designationDesc;
  }
  
  public void setDesignationDesc(String designationDesc)
  {
    this.designationDesc = designationDesc;
  }
  
  public Designations toValue(Designations designations, HttpServletRequest request)
    throws Exception
  {
    User user = (User)request.getSession().getAttribute("user_data");
    designations.setDesignationCode(this.designationCode);
    designations.setDesignationName(this.designationName);
    designations.setDesignationDesc(this.designationDesc);
    

    designations.setCreatedBy(user.getUserName());
    designations.setCreatedDate(new Date());
    

    designations.setStatus("A");
    
    return designations;
  }
  
  public void fromValue(Designations designations, HttpServletRequest request)
    throws Exception
  {
    User user1 = (User)request.getSession().getAttribute("user_data");
    
    this.designationId = designations.getDesignationId();
    this.designationCode = designations.getDesignationCode();
    this.designationName = designations.getDesignationName();
    this.designationDesc = designations.getDesignationDesc();
    
    this.createdBy = designations.getCreatedBy();
    this.createdDate = designations.getCreatedDate();
    this.status = designations.getStatus();
  }
}
