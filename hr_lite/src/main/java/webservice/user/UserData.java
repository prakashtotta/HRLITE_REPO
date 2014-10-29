package webservice.user;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="userdata", propOrder={"employeecode", "firstName", "middleName", "lastName", "email", "officePhone", "organizationCode", "departmentCode", "projectCode", "nationality", "designationCode", "roleCode", "ssnNumber", "localeCode", "timezoneCode", "userName", "password"})
public class UserData
  implements Serializable
{
  @XmlAttribute(required=true)
  public String employeecode;
  @XmlAttribute(required=true)
  public String firstName;
  @XmlAttribute
  public String middleName;
  @XmlAttribute(required=true)
  public String lastName;
  @XmlAttribute(required=true)
  public String email;
  @XmlAttribute
  public String officePhone;
  @XmlAttribute(required=true)
  public String organizationCode;
  @XmlAttribute(required=true)
  public String departmentCode;
  @XmlAttribute
  public String projectCode;
  @XmlAttribute(required=true)
  public String nationality;
  @XmlAttribute
  public String designationCode;
  @XmlAttribute(required=true)
  public String roleCode;
  @XmlAttribute
  public String ssnNumber;
  @XmlAttribute(required=true)
  public String localeCode;
  @XmlAttribute(required=true)
  public String timezoneCode;
  @XmlAttribute
  public String userName;
  @XmlAttribute
  public String password;
  
  public String getEmployeecode()
  {
    return this.employeecode;
  }
  
  public void setEmployeecode(String employeecode)
  {
    this.employeecode = employeecode;
  }
  
  public String getFirstName()
  {
    return this.firstName;
  }
  
  public void setFirstName(String firstName)
  {
    this.firstName = firstName;
  }
  
  public String getMiddleName()
  {
    return this.middleName;
  }
  
  public void setMiddleName(String middleName)
  {
    this.middleName = middleName;
  }
  
  public String getLastName()
  {
    return this.lastName;
  }
  
  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }
  
  public String getEmail()
  {
    return this.email;
  }
  
  public void setEmail(String email)
  {
    this.email = email;
  }
  
  public String getOfficePhone()
  {
    return this.officePhone;
  }
  
  public void setOfficePhone(String officePhone)
  {
    this.officePhone = officePhone;
  }
  
  public String getOrganizationCode()
  {
    return this.organizationCode;
  }
  
  public void setOrganizationCode(String organizationCode)
  {
    this.organizationCode = organizationCode;
  }
  
  public String getDepartmentCode()
  {
    return this.departmentCode;
  }
  
  public void setDepartmentCode(String departmentCode)
  {
    this.departmentCode = departmentCode;
  }
  
  public String getProjectCode()
  {
    return this.projectCode;
  }
  
  public void setProjectCode(String projectCode)
  {
    this.projectCode = projectCode;
  }
  
  public String getNationality()
  {
    return this.nationality;
  }
  
  public void setNationality(String nationality)
  {
    this.nationality = nationality;
  }
  
  public String getSsnNumber()
  {
    return this.ssnNumber;
  }
  
  public void setSsnNumber(String ssnNumber)
  {
    this.ssnNumber = ssnNumber;
  }
  
  public String getLocaleCode()
  {
    return this.localeCode;
  }
  
  public void setLocaleCode(String localeCode)
  {
    this.localeCode = localeCode;
  }
  
  public String getTimezoneCode()
  {
    return this.timezoneCode;
  }
  
  public void setTimezoneCode(String timezoneCode)
  {
    this.timezoneCode = timezoneCode;
  }
  
  public String getUserName()
  {
    return this.userName;
  }
  
  public void setUserName(String userName)
  {
    this.userName = userName;
  }
  
  public String getPassword()
  {
    return this.password;
  }
  
  public void setPassword(String password)
  {
    this.password = password;
  }
  
  public String getDesignationCode()
  {
    return this.designationCode;
  }
  
  public void setDesignationCode(String designationCode)
  {
    this.designationCode = designationCode;
  }
  
  public String getRoleCode()
  {
    return this.roleCode;
  }
  
  public void setRoleCode(String roleCode)
  {
    this.roleCode = roleCode;
  }
}
