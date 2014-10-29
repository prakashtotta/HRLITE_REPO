package com.form.employee;

import com.bean.JobGrade;
import com.bean.JobType;
import com.bean.User;
import com.bean.employee.UserJobDetails;
import com.bean.lov.JobCategory;
import com.resources.Constant;
import com.util.DateUtil;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class UserJobForm
  extends ActionForm
{
  public long jobdetailsId;
  public long userId;
  public JobType jobtype;
  public JobCategory jobcategory;
  public String joinedDate;
  public String contractStartDate;
  public String contractEndDate;
  public String contractDetails;
  private List jobtypeList;
  private List designationList;
  private List jobCategoryList;
  public long jobCategoryId;
  public String jobCategoryName;
  public long jobTypeId;
  public String jobTypeName;
  public long designationId;
  public String designationName;
  private FormFile contractdetailsData;
  private List jobgradeList;
  public long jobgradeId;
  public String jobGradeName;
  JobGrade jobGrade;
  
  public JobGrade getJobGrade()
  {
    return this.jobGrade;
  }
  
  public void setJobGrade(JobGrade jobGrade)
  {
    this.jobGrade = jobGrade;
  }
  
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
  
  public List getJobgradeList()
  {
    return this.jobgradeList;
  }
  
  public void setJobgradeList(List jobgradeList)
  {
    this.jobgradeList = jobgradeList;
  }
  
  public FormFile getContractdetailsData()
  {
    return this.contractdetailsData;
  }
  
  public void setContractdetailsData(FormFile contractdetailsData)
  {
    this.contractdetailsData = contractdetailsData;
  }
  
  public long getDesignationId()
  {
    return this.designationId;
  }
  
  public void setDesignationId(long designationId)
  {
    this.designationId = designationId;
  }
  
  public String getDesignationName()
  {
    return this.designationName;
  }
  
  public void setDesignationName(String designationName)
  {
    this.designationName = designationName;
  }
  
  public long getJobTypeId()
  {
    return this.jobTypeId;
  }
  
  public void setJobTypeId(long jobTypeId)
  {
    this.jobTypeId = jobTypeId;
  }
  
  public String getJobTypeName()
  {
    return this.jobTypeName;
  }
  
  public void setJobTypeName(String jobTypeName)
  {
    this.jobTypeName = jobTypeName;
  }
  
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
  
  public List getJobtypeList()
  {
    return this.jobtypeList;
  }
  
  public void setJobtypeList(List jobtypeList)
  {
    this.jobtypeList = jobtypeList;
  }
  
  public List getDesignationList()
  {
    return this.designationList;
  }
  
  public void setDesignationList(List designationList)
  {
    this.designationList = designationList;
  }
  
  public List getJobCategoryList()
  {
    return this.jobCategoryList;
  }
  
  public void setJobCategoryList(List jobCategoryList)
  {
    this.jobCategoryList = jobCategoryList;
  }
  
  public long getUserId()
  {
    return this.userId;
  }
  
  public void setUserId(long userId)
  {
    this.userId = userId;
  }
  
  public JobType getJobtype()
  {
    return this.jobtype;
  }
  
  public void setJobtype(JobType jobtype)
  {
    this.jobtype = jobtype;
  }
  
  public JobCategory getJobcategory()
  {
    return this.jobcategory;
  }
  
  public void setJobcategory(JobCategory jobcategory)
  {
    this.jobcategory = jobcategory;
  }
  
  public String getJoinedDate()
  {
    return this.joinedDate;
  }
  
  public void setJoinedDate(String joinedDate)
  {
    this.joinedDate = joinedDate;
  }
  
  public String getContractStartDate()
  {
    return this.contractStartDate;
  }
  
  public void setContractStartDate(String contractStartDate)
  {
    this.contractStartDate = contractStartDate;
  }
  
  public String getContractEndDate()
  {
    return this.contractEndDate;
  }
  
  public void setContractEndDate(String contractEndDate)
  {
    this.contractEndDate = contractEndDate;
  }
  
  public String getContractDetails()
  {
    return this.contractDetails;
  }
  
  public void setContractDetails(String contractDetails)
  {
    this.contractDetails = contractDetails;
  }
  
  public long getJobdetailsId()
  {
    return this.jobdetailsId;
  }
  
  public void setJobdetailsId(long jobdetailsId)
  {
    this.jobdetailsId = jobdetailsId;
  }
  
  public void fromValue(UserJobDetails userJobDetails, HttpServletRequest request)
  {
    User user1 = (User)request.getSession().getAttribute("user_data");
    this.jobdetailsId = userJobDetails.getJobdetailsId();
    this.userId = userJobDetails.getUserId();
    if (userJobDetails.getJoinedDate() != null)
    {
      String datepattern = Constant.getValue("defaultdateformat");
      if (user1 != null) {
        datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
      }
      this.joinedDate = DateUtil.convertDateToStringDate(userJobDetails.getJoinedDate(), datepattern);
    }
    if (userJobDetails.getContractStartDate() != null)
    {
      String datepattern = Constant.getValue("defaultdateformat");
      if (user1 != null) {
        datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
      }
      this.contractStartDate = DateUtil.convertDateToStringDate(userJobDetails.getContractStartDate(), datepattern);
    }
    if (userJobDetails.getContractEndDate() != null)
    {
      String datepattern = Constant.getValue("defaultdateformat");
      if (user1 != null) {
        datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
      }
      this.contractEndDate = DateUtil.convertDateToStringDate(userJobDetails.getContractEndDate(), datepattern);
    }
    if (userJobDetails.getJobtype() != null)
    {
      setJobtype(userJobDetails.getJobtype());
      this.jobTypeId = userJobDetails.getJobtype().getJobTypeId();
      this.jobTypeName = userJobDetails.getJobtype().getJobTypeName();
    }
    if (userJobDetails.getJobcategory() != null)
    {
      setJobcategory(userJobDetails.getJobcategory());
      this.jobCategoryId = userJobDetails.getJobcategory().getJobCategoryId();
      this.jobCategoryName = userJobDetails.getJobcategory().getJobCategoryName();
    }
    if (userJobDetails.getJobGrade() != null)
    {
      setJobGrade(userJobDetails.getJobGrade());
      this.jobgradeId = userJobDetails.getJobGrade().getJobgradeId();
      this.jobGradeName = userJobDetails.getJobGrade().getJobGradeName();
    }
  }
  
  public UserJobDetails toValue(UserJobDetails userJobDetails, HttpServletRequest request)
    throws Exception
  {
    User user1 = (User)request.getSession().getAttribute("user_data");
    userJobDetails.setUserId(this.userId);
    if (this.jobTypeId != 0L)
    {
      JobType jobType = new JobType();
      jobType.setJobTypeId(this.jobTypeId);
      userJobDetails.setJobtype(jobType);
    }
    if (this.jobCategoryId != 0L)
    {
      JobCategory jobCategory = new JobCategory();
      jobCategory.setJobCategoryId(this.jobCategoryId);
      userJobDetails.setJobcategory(jobCategory);
    }
    if (this.jobgradeId != 0L)
    {
      JobGrade jobGrade = new JobGrade();
      jobGrade.setJobgradeId(this.jobgradeId);
      userJobDetails.setJobGrade(jobGrade);
    }
    return userJobDetails;
  }
}
