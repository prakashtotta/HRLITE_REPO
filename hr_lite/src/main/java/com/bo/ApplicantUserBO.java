package com.bo;

import com.dao.ApplicantUserDAO;
import java.util.List;

public class ApplicantUserBO
{
  public static List getApplicantActionsList(long applicantId, String uuid)
  {
    return ApplicantUserDAO.getApplicantActionsList(applicantId, uuid);
  }
  
  public static List getApplicantUsersForPagination(String fullName, long applicant_number, String emailId, String createdBy, long super_user_key, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return ApplicantUserDAO.getApplicantUsersForPagination(fullName, applicant_number, emailId, createdBy, super_user_key, pageSize, startIndex, dir_str, sort_str);
  }
  
  public static int getCountOfAllApplicantUsers(String fullName, long applicant_number, String emailId, String createdBy, long super_user_key)
  {
    return ApplicantUserDAO.getCountOfAllApplicantUsers(fullName, applicant_number, emailId, createdBy, super_user_key);
  }
  
  public static List getApplicantUsersForPagination(int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return ApplicantUserDAO.getApplicantUsersForPagination(pageSize, startIndex, dir_str, sort_str);
  }
  
  public static int getCountOfAllApplicantUsers()
  {
    return ApplicantUserDAO.getCountOfAllApplicantUsers();
  }
}
