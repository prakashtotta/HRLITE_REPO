package webservice.common;

import com.bean.Country;
import com.bean.Department;
import com.bean.Designations;
import com.bean.Locale;
import com.bean.Organization;
import com.bean.ProjectCodes;
import com.bean.Role;
import com.bean.Timezone;
import com.bean.User;
import com.common.WSException;
import com.dao.UserDAO;
import com.util.EncryptDecrypt;
import com.util.StringUtils;
import org.apache.log4j.Logger;
import webservice.user.UserData;

public class Transform
{
  protected static final Logger logger = Logger.getLogger(Transform.class);
  
  public static User convertWsUserDataToUser(UserData fuser, User user)
    throws WSException
  {
    logger.info("inside convertWsUserDataToUser");
    try
    {
      user.setEmployeecode(fuser.employeecode);
      user.setFirstName(fuser.firstName);
      user.setMiddleName(fuser.middleName);
      user.setLastName(fuser.lastName);
      user.setEmailId(fuser.email);
      user.setPhoneOffice(fuser.officePhone);
      user.setSsnNumber(fuser.ssnNumber);
      Locale lo = new Locale();
      lo.setLocaleCode(fuser.localeCode);
      user.setLocale(lo);
      





      Timezone tz = new Timezone();
      tz.setTimezoneCode(fuser.timezoneCode);
      user.setTimezone(tz);
      

      Organization organization = new Organization();
      organization.setOrgCode(fuser.organizationCode);
      user.setOrganization(organization);
      
      Department dept = new Department();
      dept.setDepartmentCode(fuser.departmentCode);
      user.setDepartment(dept);
      if (!StringUtils.isNullOrEmpty(fuser.projectCode))
      {
        ProjectCodes pjcode = new ProjectCodes();
        pjcode.setProjCode(fuser.projectCode);
        user.setProjectcode(pjcode);
      }
      if (!StringUtils.isNullOrEmpty(fuser.designationCode))
      {
        Designations designation = new Designations();
        designation.setDesignationCode(fuser.designationCode);
        user.setDesignation(designation);
      }
      if (!StringUtils.isNullOrEmpty(fuser.roleCode))
      {
        Role role = new Role();
        role.setRoleCode(fuser.roleCode);
        user.setRole(role);
      }
      if (!StringUtils.isNullOrEmpty(fuser.nationality))
      {
        Country nationality = new Country();
        nationality.setCountryName(fuser.nationality);
        user.setNationality(nationality);
      }
      user.setStatus("A");
      if (!StringUtils.isNullOrEmpty(fuser.getUserName()))
      {
        String username = UserDAO.isUserNameExist(fuser.getUserName());
        if (username != null) {
          throw new WSException("1006", "UserName already exist.");
        }
        user.setUserName(fuser.getUserName());
      }
      if (!StringUtils.isNullOrEmpty(fuser.getPassword())) {
        user.setPassword(EncryptDecrypt.encrypt(fuser.getPassword()));
      }
      return user;
    }
    catch (Exception e)
    {
      throw new WSException("99999999", "System Error.");
    }
  }
}
