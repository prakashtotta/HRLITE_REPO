package com.form;

import com.bean.EmailTemplates;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;

public class EmailTemplatesForm
  extends ActionForm
{
  public long emailtemplateId;
  public String emailtemplatename;
  public String emailtemplateVmname;
  public String emailtemplateData = "";
  public String createdBy;
  public Date createdDate;
  private Date updatedDate;
  private String updatedBy;
  public String emailSubject;
  public String emailmessage;
  public String readPreview;
  
  public String getEmailmessage()
  {
    return this.emailmessage;
  }
  
  public void setEmailmessage(String emailmessage)
  {
    this.emailmessage = emailmessage;
  }
  
  public long getEmailtemplateId()
  {
    return this.emailtemplateId;
  }
  
  public void setEmailtemplateId(long emailtemplateId)
  {
    this.emailtemplateId = emailtemplateId;
  }
  
  public String getEmailtemplatename()
  {
    return this.emailtemplatename;
  }
  
  public void setEmailtemplatename(String emailtemplatename)
  {
    this.emailtemplatename = emailtemplatename;
  }
  
  public String getEmailtemplateVmname()
  {
    return this.emailtemplateVmname;
  }
  
  public void setEmailtemplateVmname(String emailtemplateVmname)
  {
    this.emailtemplateVmname = emailtemplateVmname;
  }
  
  public String getEmailtemplateData()
  {
    return this.emailtemplateData;
  }
  
  public void setEmailtemplateData(String emailtemplateData)
  {
    this.emailtemplateData = emailtemplateData;
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
  
  public void fromValue(EmailTemplates empt, HttpServletRequest request)
    throws Exception
  {
    this.emailtemplateId = empt.getEmailtemplateId();
    this.emailtemplatename = empt.getEmailtemplatename();
    this.emailtemplateVmname = empt.getEmailtemplateVmname();
    this.emailtemplateData = empt.getEmailtemplateData();
    this.createdBy = empt.getCreatedBy();
    this.createdDate = empt.getCreatedDate();
    this.updatedBy = empt.getUpdatedBy();
    this.updatedDate = empt.getUpdatedDate();
    this.emailSubject = empt.getEmailSubject();
  }
  
  public void toValue(EmailTemplates empt, HttpServletRequest request)
    throws Exception
  {
    empt.setEmailtemplatename(request.getParameter("templatename"));
    empt.setEmailtemplateData(request.getParameter("editor_data"));
    empt.setEmailSubject(request.getParameter("emailsubject"));
  }
  
  public String getEmailSubject()
  {
    return this.emailSubject;
  }
  
  public void setEmailSubject(String emailSubject)
  {
    this.emailSubject = emailSubject;
  }
  
  public String getReadPreview()
  {
    return this.readPreview;
  }
  
  public void setReadPreview(String readPreview)
  {
    this.readPreview = readPreview;
  }
}
