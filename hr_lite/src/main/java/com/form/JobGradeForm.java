package com.form;

import org.apache.struts.action.ActionForm;

public class JobGradeForm
  extends ActionForm
{
  public long jobgradeId;
  public String jobGradeName;
  public String jobGradeDesc;
  public String jobGradeResponsibility;
  public String status;
  public String readPreview;
  
  public long getJobgradeId()
  {
    return this.jobgradeId;
  }
  
  public void setJobgradeId(long jobgradeId)
  {
    this.jobgradeId = jobgradeId;
  }
  
  public String getJobGradeName()
  {
    return this.jobGradeName;
  }
  
  public void setJobGradeName(String jobGradeName)
  {
    this.jobGradeName = jobGradeName;
  }
  
  public String getJobGradeDesc()
  {
    return this.jobGradeDesc;
  }
  
  public void setJobGradeDesc(String jobGradeDesc)
  {
    this.jobGradeDesc = jobGradeDesc;
  }
  
  public String getJobGradeResponsibility()
  {
    return this.jobGradeResponsibility;
  }
  
  public void setJobGradeResponsibility(String jobGradeResponsibility)
  {
    this.jobGradeResponsibility = jobGradeResponsibility;
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
