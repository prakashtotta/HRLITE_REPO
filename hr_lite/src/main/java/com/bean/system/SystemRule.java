package com.bean.system;

import com.bean.Department;
import com.bean.Organization;
import com.bean.User;
import com.bean.UserGroup;
import com.util.StringUtils;
import java.util.Date;
import java.util.List;

public class SystemRule
{
  public long systemRuleId;
  public String ruleName;
  public String ruleDesc;
  public Organization organization;
  public Department department;
  public String createdBy;
  public Date createdDate;
  private Date updatedDate;
  private String updatedBy;
  public String ruleType;
  public String status;
  public String publishToEmpRef;
  public String publishToExternal;
  public String eeoInfoIncluded;
  private List ruleUsers;
  public String deptName;
  public String orgName;
  public String userNames;
  
  public String getPublishToExternal()
  {
    return this.publishToExternal;
  }
  
  public void setPublishToExternal(String publishToExternal)
  {
    this.publishToExternal = publishToExternal;
  }
  
  public List getRuleUsers()
  {
    return this.ruleUsers;
  }
  
  public void setRuleUsers(List ruleUsers)
  {
    if ((ruleUsers != null) && (ruleUsers.size() > 0))
    {
      String tmpdata = "";
      for (int i = 0; i < ruleUsers.size(); i++)
      {
        SystemRuleUser sysuser = (SystemRuleUser)ruleUsers.get(i);
        if ((sysuser != null) && (sysuser.getIsGroup().equals("Y"))) {
          tmpdata = tmpdata + sysuser.getUserGroup().getUsergrpName() + " |<br>";
        } else if (sysuser.getUser() != null) {
          if ((sysuser.getUser() != null) && (sysuser.getUser().getType().equals("Vendor"))) {
            tmpdata = tmpdata + sysuser.getUser().getFirstName() + "|<br>";
          } else {
            tmpdata = tmpdata + sysuser.getUser().getFirstName() + " " + sysuser.getUser().getLastName() + " |<br>";
          }
        }
      }
      if (!StringUtils.isNullOrEmpty(tmpdata)) {
        this.userNames = tmpdata.substring(0, tmpdata.length() - 5);
      }
    }
    this.ruleUsers = ruleUsers;
  }
  
  public long getSystemRuleId()
  {
    return this.systemRuleId;
  }
  
  public void setSystemRuleId(long systemRuleId)
  {
    this.systemRuleId = systemRuleId;
  }
  
  public String getRuleName()
  {
    return this.ruleName;
  }
  
  public void setRuleName(String ruleName)
  {
    this.ruleName = ruleName;
  }
  
  public String getRuleDesc()
  {
    return this.ruleDesc;
  }
  
  public void setRuleDesc(String ruleDesc)
  {
    this.ruleDesc = ruleDesc;
  }
  
  public String getDeptName()
  {
    return this.deptName;
  }
  
  public void setDeptName(String deptName)
  {
    this.deptName = deptName;
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
  
  public String getRuleType()
  {
    return this.ruleType;
  }
  
  public void setRuleType(String ruleType)
  {
    this.ruleType = ruleType;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public String getPublishToEmpRef()
  {
    return this.publishToEmpRef;
  }
  
  public void setPublishToEmpRef(String publishToEmpRef)
  {
    this.publishToEmpRef = publishToEmpRef;
  }
  
  public Organization getOrganization()
  {
    return this.organization;
  }
  
  public void setOrganization(Organization organization)
  {
    if (organization != null) {
      this.orgName = organization.getOrgName();
    }
    this.organization = organization;
  }
  
  public Department getDepartment()
  {
    return this.department;
  }
  
  public void setDepartment(Department department)
  {
    if (department != null) {
      this.deptName = department.getDepartmentName();
    }
    this.department = department;
  }
  
  public String getOrgName()
  {
    return this.orgName;
  }
  
  public void setOrgName(String orgName)
  {
    this.orgName = orgName;
  }
  
  public String getUserNames()
  {
    return this.userNames;
  }
  
  public void setUserNames(String userNames)
  {
    this.userNames = userNames;
  }
  
  public String getEeoInfoIncluded()
  {
    return this.eeoInfoIncluded;
  }
  
  public void setEeoInfoIncluded(String eeoInfoIncluded)
  {
    this.eeoInfoIncluded = eeoInfoIncluded;
  }
}
