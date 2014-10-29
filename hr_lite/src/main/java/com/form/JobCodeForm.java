package com.form;

import org.apache.struts.action.ActionForm;

public class JobCodeForm
  extends ActionForm
{
  public long jobcodeId;
  public String jobCode;
  public String jobName;
  public String jobDesc;
  public String jobResponsibility;
  public String status;
  public String readPreview;
  
  public long getJobcodeId()
  {
    return this.jobcodeId;
  }
  
  public void setJobcodeId(long jobcodeId)
  {
    this.jobcodeId = jobcodeId;
  }
  
  public String getJobCode()
  {
    return this.jobCode;
  }
  
  public void setJobCode(String jobCode)
  {
    this.jobCode = jobCode;
  }
  
  public String getJobName()
  {
    return this.jobName;
  }
  
  public void setJobName(String jobName)
  {
    this.jobName = jobName;
  }
  
  public String getJobDesc()
  {
    return this.jobDesc;
  }
  
  public void setJobDesc(String jobDesc)
  {
    this.jobDesc = jobDesc;
  }
  
  public String getJobResponsibility()
  {
    return this.jobResponsibility;
  }
  
  public void setJobResponsibility(String jobResponsibility)
  {
    this.jobResponsibility = jobResponsibility;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
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
