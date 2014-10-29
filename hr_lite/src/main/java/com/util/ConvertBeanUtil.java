package com.util;

import com.bean.RefferalEmployee;
import com.bean.User;

public class ConvertBeanUtil
{
  public static RefferalEmployee convertUserToEmployeeReferral(User user, RefferalEmployee refemp)
  {
    refemp.setOrganization(user.getOrganization());
    refemp.setDepartment(user.getDepartment());
    refemp.setProjectcode(user.getProjectcode());
    refemp.setLocale(user.getLocale());
    refemp.setTimezone(user.getTimezone());
    

    refemp.profilePhotoId = user.getProfilePhotoId();
    
    refemp.phoneOffice = user.getPhoneOffice();
    
    refemp.employeecode = user.getEmployeecode();
    refemp.employeename = (user.getFirstName() + " " + user.getLastName());
    refemp.employeeemail = user.getEmailId();
    refemp.userName = user.getUserName();
    refemp.password = user.getPassword();
    refemp.employeecode = user.getEmployeecode();
    refemp.setEmployeeReferalId(user.getUserId());
    refemp.setStatus(user.getStatus());
    refemp.setNationality(user.getNationality());
    refemp.setSuper_user_key(user.getSuper_user_key());
    return refemp;
  }
  
  public static User convertEmployeeReferralToUser(User user, RefferalEmployee refemp)
  {
    user.setUserId(refemp.getEmployeeReferalId());
    user.setLocale(refemp.getLocale());
    user.setTimezone(refemp.getTimezone());
    


    user.phoneOffice = refemp.getPhoneOffice();
    
    user.password = refemp.getPassword();
    
    user.setOrganization(refemp.getOrganization());
    user.setDepartment(refemp.getDepartment());
    user.setProjectcode(refemp.getProjectcode());
    user.organization = refemp.getOrganization();
    user.department = refemp.getDepartment();
    user.projectcode = refemp.getProjectcode();
    user.setLocale(refemp.getLocale());
    user.setTimezone(refemp.getTimezone());
    


    user.setNationality(refemp.getNationality());
    user.setSuper_user_key(refemp.getSuper_user_key());
    
    return user;
  }
}
