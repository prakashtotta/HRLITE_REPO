package com.form;

import com.bean.lov.JobCategory;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;

public class JobCategoryForm
  extends ActionForm
{
  protected static final Logger logger = Logger.getLogger(JobCategoryForm.class);
  public long jobCategoryId;
  public String jobCategoryName;
  public String jobCategoryDesc;
  public String status;
  
  public long getJobCategoryId()
  {
    return this.jobCategoryId;
  }
  
  public void setJobCategoryId(long jobCategoryId)
  {
    this.jobCategoryId = jobCategoryId;
  }
  
  public String getJobCategoryName()
  {
    return this.jobCategoryName;
  }
  
  public void setJobCategoryName(String jobCategoryName)
  {
    this.jobCategoryName = jobCategoryName;
  }
  
  public String getJobCategoryDesc()
  {
    return this.jobCategoryDesc;
  }
  
  public void setJobCategoryDesc(String jobCategoryDesc)
  {
    this.jobCategoryDesc = jobCategoryDesc;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public JobCategory toValue(JobCategory jobCategory, HttpServletRequest request)
    throws Exception
  {
    jobCategory.setJobCategoryName(this.jobCategoryName);
    jobCategory.setJobCategoryDesc(this.jobCategoryDesc);
    return jobCategory;
  }
  
  public void fromValue(JobCategory jobCategory, HttpServletRequest request)
  {
    this.jobCategoryId = jobCategory.getJobCategoryId();
    this.jobCategoryName = jobCategory.getJobCategoryName();
    this.jobCategoryDesc = jobCategory.getJobCategoryDesc();
  }
}
