package com.form;

import java.util.List;
import org.apache.struts.action.ActionForm;

public class MassEmailForm
  extends ActionForm
{
  public long jobId;
  List jobList;
  List applicantList;
  public String subjectemail;
  public String messageemail;
  public String emailtemplateData;
  
  public List getJobList()
  {
    return this.jobList;
  }
  
  public void setJobList(List jobList)
  {
    this.jobList = jobList;
  }
  
  public long getJobId()
  {
    return this.jobId;
  }
  
  public void setJobId(long jobId)
  {
    this.jobId = jobId;
  }
  
  public List getApplicantList()
  {
    return this.applicantList;
  }
  
  public void setApplicantList(List applicantList)
  {
    this.applicantList = applicantList;
  }
  
  public String getSubjectemail()
  {
    return this.subjectemail;
  }
  
  public void setSubjectemail(String subjectemail)
  {
    this.subjectemail = subjectemail;
  }
  
  public String getMessageemail()
  {
    return this.messageemail;
  }
  
  public void setMessageemail(String messageemail)
  {
    this.messageemail = messageemail;
  }
  
  public String getEmailtemplateData()
  {
    return this.emailtemplateData;
  }
  
  public void setEmailtemplateData(String emailtemplateData)
  {
    this.emailtemplateData = emailtemplateData;
  }
}
