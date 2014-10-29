package com.bo;

import com.bean.Questions;
import com.bean.SearchApplicantCustomFields;
import com.bean.SearchApplicantQuestions;
import com.bean.User;
import com.bean.criteria.ApplicantSearchCriteria;
import com.common.Common;
import com.resources.Constant;
import com.util.DateUtil;
import com.util.StringUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

public class QueryBuilderApplicant
{
  protected static final Logger logger = Logger.getLogger(QueryBuilderApplicant.class);
  
  public static String buildSQLQueryforSearch(User user, ApplicantSearchCriteria criteria)
  {
    String sql = "";
    if ((!StringUtils.isNullOrEmpty(criteria.getScreenName())) && (criteria.getScreenName().equals("ON_BOARDING_SEARCH_SCREEN")))
    {
      String insql = "'Offer Accepted','On Board - Joined'";
      sql = sql + " and ja.interview_state in (" + insql + ") ";
    }
    else if ((criteria.getInterviewStateList() != null) && (criteria.getInterviewStateList().size() > 0))
    {
      sql = sql + buildInterviewStates(criteria.getInterviewStateList());
    }
    if ((!StringUtils.isNullOrEmpty(criteria.getFullname())) && (!criteria.getFullname().equals("null")))
    {
      if ((criteria.getApplicantName_criteria() != null) && (criteria.getApplicantName_criteria().equals("STARTS_WITH"))) {
        sql = sql + " and ja.full_name like '" + criteria.getFullname() + "%' ";
      } else if ((criteria.getApplicantName_criteria() != null) && (criteria.getApplicantName_criteria().equals("ENDS_WITH"))) {
        sql = sql + " and ja.full_name like '%" + criteria.getFullname() + "' ";
      } else if ((criteria.getApplicantName_criteria() != null) && (criteria.getApplicantName_criteria().equals("CONTAINS"))) {
        sql = sql + " and ja.full_name like '%" + criteria.getFullname() + "%' ";
      } else if ((criteria.getApplicantName_criteria() != null) && (criteria.getApplicantName_criteria().equals("EQUALS"))) {
        sql = sql + " and ja.full_name = '" + criteria.getFullname() + "' ";
      }
    }
    else if ((criteria.getApplicantName_criteria() != null) && (criteria.getApplicantName_criteria().equals("EMPTY"))) {
      sql = sql + " and ja.full_name is NULL ";
    } else if ((criteria.getApplicantName_criteria() != null) && (criteria.getApplicantName_criteria().equals("NOT_EMPTY"))) {
      sql = sql + " and ja.full_name is NOT NULL ";
    }
    if (criteria.getApplicantId() != 0L) {
      if ((criteria.getApplicantNo_criteria() != null) && (criteria.getApplicantNo_criteria().equals("EQUALS"))) {
        sql = sql + " and ja.applicant_number = " + criteria.getApplicantId();
      } else if ((criteria.getApplicantNo_criteria() != null) && (criteria.getApplicantNo_criteria().equals("GREATER_THAN"))) {
        sql = sql + " and ja.applicant_number > " + criteria.getApplicantId();
      } else if ((criteria.getApplicantNo_criteria() != null) && (criteria.getApplicantNo_criteria().equals("GREATER_THAN_EQUAL"))) {
        sql = sql + " and ja.applicant_number >= " + criteria.getApplicantId();
      } else if ((criteria.getApplicantNo_criteria() != null) && (criteria.getApplicantNo_criteria().equals("LESS_THAN"))) {
        sql = sql + " and ja.applicant_number < " + criteria.getApplicantId();
      } else if ((criteria.getApplicantNo_criteria() != null) && (criteria.getApplicantNo_criteria().equals("LESS_THAN_EQUAL"))) {
        sql = sql + " and ja.applicant_number <= " + criteria.getApplicantId();
      } else if ((criteria.getApplicantNo_criteria() != null) && (criteria.getApplicantNo_criteria().equals("NOT_EQUALS"))) {
        sql = sql + " and ja.applicant_number <> " + criteria.getApplicantId();
      } else if ((criteria.getApplicantNo_criteria() != null) && (criteria.getApplicantNo_criteria().equals("BETWEEN"))) {
        sql = sql + " and ja.applicant_number  >= " + criteria.getApplicantId() + " and ja.applicant_number <= " + criteria.getApplicantId1();
      }
    }
    if (criteria.getSourceTypeId() != 0)
    {
      sql = sql + " and ja.resume_source_type_id = " + criteria.getSourceTypeId();
      logger.info("criteria.getSourceId()" + criteria.getSourceId());
      if (criteria.getSourceTypeId() == 5)
      {
        if (criteria.getSourceId() != 0L) {
          sql = sql + " and ja.vendor_id = " + criteria.getSourceId();
        }
      }
      else if (criteria.getSourceTypeId() == 1)
      {
        if ((!StringUtils.isNullOrEmpty(criteria.getEmployeecode())) && (!criteria.getEmployeecode().equals("null"))) {
          sql = sql + " and ja.employee_code = '" + criteria.getEmployeecode() + "' ";
        } else if ((!StringUtils.isNullOrEmpty(criteria.getReferrerName())) && (!criteria.getReferrerName().equals("null"))) {
          sql = sql + " and ja.referer_name = '%" + criteria.getReferrerName() + "%' ";
        } else if ((!StringUtils.isNullOrEmpty(criteria.getEmployeecode())) && (!criteria.getEmployeecode().equals("null"))) {
          sql = sql + " and ja.referer_email = '" + criteria.getReferrerEmail() + "' ";
        }
      }
      else if (criteria.getSourceId() != 0L) {
        sql = sql + " and ja.resume_source_id = " + criteria.getSourceId();
      }
    }
    if ((!StringUtils.isNullOrEmpty(criteria.getGender())) && (!criteria.getGender().equals("N"))) {
      sql = sql + " and ja.gender = '" + criteria.getGender() + "' ";
    }
    if ((!StringUtils.isNullOrEmpty(criteria.getDateofbirth())) && (!criteria.getDateofbirth().equals("null")))
    {
      String datepattern = DateUtil.defaultdateformatforSQL;
      String datepatternuser = DateUtil.getDatePatternFormat(user.getLocale());
      
      Date dobt = DateUtil.convertStringDateToDate(criteria.getDateofbirth(), datepatternuser);
      String dob = DateUtil.convertDateToStringDate(dobt, datepattern);
      if ((criteria.getDob_criteria() != null) && (criteria.getDob_criteria().equals("EQUALS")))
      {
        sql = sql + " and DATE_FORMAT( ja.dob, '%M %e, %Y')  = " + "'" + dob + "'";
      }
      else if ((criteria.getDob_criteria() != null) && (criteria.getDob_criteria().equals("GREATER_THAN")))
      {
        sql = sql + " and DATE_FORMAT( ja.dob, '%M %e, %Y')  > " + "'" + dob + "'";
      }
      else if ((criteria.getDob_criteria() != null) && (criteria.getDob_criteria().equals("GREATER_THAN_EQUAL")))
      {
        sql = sql + " and DATE_FORMAT( ja.dob, '%M %e, %Y')  >= " + "'" + dob + "'";
      }
      else if ((criteria.getDob_criteria() != null) && (criteria.getDob_criteria().equals("LESS_THAN")))
      {
        sql = sql + " and DATE_FORMAT( ja.dob, '%M %e, %Y')  < " + "'" + dob + "'";
      }
      else if ((criteria.getDob_criteria() != null) && (criteria.getDob_criteria().equals("LESS_THAN_EQUAL")))
      {
        sql = sql + " and DATE_FORMAT( ja.dob, '%M %e, %Y')  <= " + "'" + dob + "'";
      }
      else if ((criteria.getDob_criteria() != null) && (criteria.getDob_criteria().equals("NOT_EQUALS")))
      {
        sql = sql + " and DATE_FORMAT( ja.dob, '%M %e, %Y')  <> " + "'" + dob + "'";
      }
      else if ((criteria.getDob_criteria() != null) && (criteria.getDob_criteria().equals("BETWEEN")) && 
        (!StringUtils.isNullOrEmpty(criteria.getDateofbirth1())) && (!criteria.getDateofbirth1().equals("null")))
      {
        Date dobt1 = DateUtil.convertStringDateToDate(criteria.getDateofbirth1(), datepatternuser);
        String dob1 = DateUtil.convertDateToStringDate(dobt1, datepattern);
        
        sql = sql + " and DATE_FORMAT( ja.dob, '%M %e, %Y')  >= " + "'" + dob + "'" + " and DATE_FORMAT( ja.dob, '%M %e, %Y')  <= " + "'" + dob1 + "'";
      }
    }
    else if ((criteria.getDob_criteria() != null) && (criteria.getDob_criteria().equals("EMPTY")))
    {
      sql = sql + " and  ja.dob is NULL ";
    }
    else if ((criteria.getDob_criteria() != null) && (criteria.getDob_criteria().equals("NOT_EMPTY")))
    {
      sql = sql + " and  ja.dob is NOT NULL ";
    }
    if ((!StringUtils.isNullOrEmpty(criteria.getPassportno())) && (!criteria.getPassportno().equals("null")))
    {
      if ((criteria.getPassport_criteria() != null) && (criteria.getPassport_criteria().equals("STARTS_WITH"))) {
        sql = sql + " and ja.passportno like '" + criteria.getPassportno() + "%' ";
      } else if ((criteria.getPassport_criteria() != null) && (criteria.getPassport_criteria().equals("ENDS_WITH"))) {
        sql = sql + " and ja.passportno like '%" + criteria.getPassportno() + "' ";
      } else if ((criteria.getPassport_criteria() != null) && (criteria.getPassport_criteria().equals("CONTAINS"))) {
        sql = sql + " and ja.passportno like '%" + criteria.getPassportno() + "%' ";
      } else if ((criteria.getPassport_criteria() != null) && (criteria.getPassport_criteria().equals("EQUALS"))) {
        sql = sql + " and ja.passportno = '" + criteria.getPassportno() + "' ";
      }
    }
    else if ((criteria.getPassport_criteria() != null) && (criteria.getPassport_criteria().equals("EMPTY"))) {
      sql = sql + " and ja.passportno is NULL ";
    } else if ((criteria.getPassport_criteria() != null) && (criteria.getPassport_criteria().equals("NOT_EMPTY"))) {
      sql = sql + " and ja.passportno is not NULL ";
    }
    if ((!StringUtils.isNullOrEmpty(criteria.getSsnno())) && (!criteria.getSsnno().equals("null")))
    {
      if ((criteria.getSsn_criteria() != null) && (criteria.getSsn_criteria().equals("STARTS_WITH"))) {
        sql = sql + " and ja.sssno like '" + criteria.getSsnno() + "%' ";
      } else if ((criteria.getSsn_criteria() != null) && (criteria.getSsn_criteria().equals("ENDS_WITH"))) {
        sql = sql + " and ja.sssno like '%" + criteria.getSsnno() + "' ";
      } else if ((criteria.getSsn_criteria() != null) && (criteria.getSsn_criteria().equals("CONTAINS"))) {
        sql = sql + " and ja.sssno like '%" + criteria.getSsnno() + "%' ";
      } else if ((criteria.getSsn_criteria() != null) && (criteria.getSsn_criteria().equals("EQUALS"))) {
        sql = sql + " and ja.sssno = '" + criteria.getSsnno() + "' ";
      }
    }
    else if ((criteria.getSsn_criteria() != null) && (criteria.getSsn_criteria().equals("EMPTY"))) {
      sql = sql + " and ja.sssno is NULL ";
    } else if ((criteria.getSsn_criteria() != null) && (criteria.getSsn_criteria().equals("NOT_EMPTY"))) {
      sql = sql + " and ja.sssno is NOT NULL ";
    }
    if ((!StringUtils.isNullOrEmpty(criteria.getTaxidno())) && (!criteria.getTaxidno().equals("null")))
    {
      if ((criteria.getTaxno_criteria() != null) && (criteria.getTaxno_criteria().equals("STARTS_WITH"))) {
        sql = sql + " and ja.taxidno like '" + criteria.getTaxidno() + "%' ";
      } else if ((criteria.getTaxno_criteria() != null) && (criteria.getTaxno_criteria().equals("ENDS_WITH"))) {
        sql = sql + " and ja.taxidno like '%" + criteria.getTaxidno() + "' ";
      } else if ((criteria.getTaxno_criteria() != null) && (criteria.getTaxno_criteria().equals("CONTAINS"))) {
        sql = sql + " and ja.taxidno like '%" + criteria.getTaxidno() + "%' ";
      } else if ((criteria.getTaxno_criteria() != null) && (criteria.getTaxno_criteria().equals("EQUALS"))) {
        sql = sql + " and ja.taxidno = '" + criteria.getTaxidno() + "' ";
      }
    }
    else if ((criteria.getTaxno_criteria() != null) && (criteria.getTaxno_criteria().equals("EMPTY"))) {
      sql = sql + " and ja.taxidno is NULL ";
    } else if ((criteria.getTaxno_criteria() != null) && (criteria.getTaxno_criteria().equals("NOT_EMPTY"))) {
      sql = sql + " and ja.taxidno is NOT NULL ";
    }
    if ((!StringUtils.isNullOrEmpty(criteria.getEmail())) && (!criteria.getEmail().equals("null")))
    {
      if ((criteria.getEmail_criteria() != null) && (criteria.getEmail_criteria().equals("STARTS_WITH"))) {
        sql = sql + " and ja.email_id like '" + criteria.getEmail() + "%' ";
      } else if ((criteria.getEmail_criteria() != null) && (criteria.getEmail_criteria().equals("ENDS_WITH"))) {
        sql = sql + " and ja.email_id like '%" + criteria.getEmail() + "' ";
      } else if ((criteria.getEmail_criteria() != null) && (criteria.getEmail_criteria().equals("CONTAINS"))) {
        sql = sql + " and ja.email_id like '%" + criteria.getEmail() + "%' ";
      } else if ((criteria.getEmail_criteria() != null) && (criteria.getEmail_criteria().equals("EQUALS"))) {
        sql = sql + " and ja.email_id = '" + criteria.getEmail() + "' ";
      }
    }
    else if ((criteria.getEmail_criteria() != null) && (criteria.getEmail_criteria().equals("EMPTY"))) {
      sql = sql + " and ja.email_id is NULL ";
    } else if ((criteria.getEmail_criteria() != null) && (criteria.getEmail_criteria().equals("NOT_EMPTY"))) {
      sql = sql + " and ja.email_id is NOT NULL ";
    }
    if (criteria.getCountryId() > 0L) {
      sql = sql + " and ja.country_id = " + criteria.getCountryId();
    }
    if (criteria.getStateId() > 0L) {
      sql = sql + " and ja.state_id = " + criteria.getStateId();
    }
    if ((!StringUtils.isNullOrEmpty(criteria.getCity())) && (!criteria.getCity().equals("null")))
    {
      if ((criteria.getCity_criteria() != null) && (criteria.getCity_criteria().equals("STARTS_WITH"))) {
        sql = sql + " and ja.city like '" + criteria.getCity() + "%' ";
      } else if ((criteria.getCity_criteria() != null) && (criteria.getCity_criteria().equals("ENDS_WITH"))) {
        sql = sql + " and ja.city like '%" + criteria.getCity() + "' ";
      } else if ((criteria.getCity_criteria() != null) && (criteria.getCity_criteria().equals("CONTAINS"))) {
        sql = sql + " and ja.city like '%" + criteria.getCity() + "%' ";
      } else if ((criteria.getCity_criteria() != null) && (criteria.getCity_criteria().equals("EQUALS"))) {
        sql = sql + " and ja.city = '" + criteria.getCity() + "' ";
      }
    }
    else if ((criteria.getCity_criteria() != null) && (criteria.getCity_criteria().equals("EMPTY"))) {
      sql = sql + " and ja.city is NULL ";
    } else if ((criteria.getCity_criteria() != null) && (criteria.getCity_criteria().equals("NOT_EMPTY"))) {
      sql = sql + " and ja.city is NOT NULL ";
    }
    if ((!StringUtils.isNullOrEmpty(criteria.getFelony_conviction())) && (!criteria.getFelony_conviction().equals("null"))) {
      sql = sql + " and jao.felony_conviction = '" + criteria.getFelony_conviction() + "' ";
    }
    if (criteria.getCurrectctc() != 0.0D) {
      if ((criteria.getCurrectctc_criteria() != null) && (criteria.getCurrectctc_criteria().equals("EQUALS"))) {
        sql = sql + " and CONVERT(ja.currectctc, DECIMAL) = " + criteria.getCurrectctc();
      } else if ((criteria.getCurrectctc_criteria() != null) && (criteria.getCurrectctc_criteria().equals("GREATER_THAN"))) {
        sql = sql + " and CONVERT(ja.currectctc, DECIMAL) > " + criteria.getCurrectctc();
      } else if ((criteria.getCurrectctc_criteria() != null) && (criteria.getCurrectctc_criteria().equals("GREATER_THAN_EQUAL"))) {
        sql = sql + " and CONVERT(ja.currectctc, DECIMAL) >= " + criteria.getCurrectctc();
      } else if ((criteria.getCurrectctc_criteria() != null) && (criteria.getCurrectctc_criteria().equals("LESS_THAN"))) {
        sql = sql + " and CONVERT(ja.currectctc, DECIMAL) < " + criteria.getCurrectctc();
      } else if ((criteria.getCurrectctc_criteria() != null) && (criteria.getCurrectctc_criteria().equals("LESS_THAN_EQUAL"))) {
        sql = sql + " and CONVERT(ja.currectctc, DECIMAL) <= " + criteria.getCurrectctc();
      } else if ((criteria.getCurrectctc_criteria() != null) && (criteria.getCurrectctc_criteria().equals("NOT_EQUALS"))) {
        sql = sql + " and CONVERT(ja.currectctc, DECIMAL) <> " + criteria.getCurrectctc();
      } else if ((criteria.getCurrectctc_criteria() != null) && (criteria.getCurrectctc_criteria().equals("BETWEEN"))) {
        sql = sql + " and CONVERT(ja.currectctc, DECIMAL) >= " + criteria.getCurrectctc() + " and CONVERT(ja.currectctc, DECIMAL) <= " + criteria.getCurrectctc1();
      }
    }
    if (criteria.getExpectedctc() != 0.0D) {
      if ((criteria.getExpectedctc_criteria() != null) && (criteria.getExpectedctc_criteria().equals("EQUALS"))) {
        sql = sql + " and CONVERT(ja.expectedctc, DECIMAL) = " + criteria.getExpectedctc();
      } else if ((criteria.getExpectedctc_criteria() != null) && (criteria.getExpectedctc_criteria().equals("GREATER_THAN"))) {
        sql = sql + " and CONVERT(ja.expectedctc, DECIMAL) > " + criteria.getExpectedctc();
      } else if ((criteria.getExpectedctc_criteria() != null) && (criteria.getExpectedctc_criteria().equals("GREATER_THAN_EQUAL"))) {
        sql = sql + " and CONVERT(ja.expectedctc, DECIMAL) >= " + criteria.getExpectedctc();
      } else if ((criteria.getExpectedctc_criteria() != null) && (criteria.getExpectedctc_criteria().equals("LESS_THAN"))) {
        sql = sql + " and CONVERT(ja.expectedctc, DECIMAL) < " + criteria.getExpectedctc();
      } else if ((criteria.getExpectedctc_criteria() != null) && (criteria.getExpectedctc_criteria().equals("LESS_THAN_EQUAL"))) {
        sql = sql + " and CONVERT(ja.expectedctc, DECIMAL) <= " + criteria.getExpectedctc();
      } else if ((criteria.getExpectedctc_criteria() != null) && (criteria.getExpectedctc_criteria().equals("NOT_EQUALS"))) {
        sql = sql + " and CONVERT(ja.expectedctc, DECIMAL) <> " + criteria.getExpectedctc();
      } else if ((criteria.getExpectedctc_criteria() != null) && (criteria.getExpectedctc_criteria().equals("BETWEEN"))) {
        sql = sql + " and CONVERT(ja.expectedctc, DECIMAL) >= " + criteria.getExpectedctc() + " and CONVERT(ja.expectedctc, DECIMAL) <= " + criteria.getExpectedctc1();
      }
    }
    if (criteria.getNoticeperiod() != 0.0D) {
      if ((criteria.getNoticeperiod_criteria() != null) && (criteria.getNoticeperiod_criteria().equals("EQUALS"))) {
        sql = sql + " and CONVERT(ja.noticeperiod, DECIMAL) = " + criteria.getNoticeperiod();
      } else if ((criteria.getNoticeperiod_criteria() != null) && (criteria.getNoticeperiod_criteria().equals("GREATER_THAN"))) {
        sql = sql + " and CONVERT(ja.noticeperiod, DECIMAL) > " + criteria.getNoticeperiod();
      } else if ((criteria.getNoticeperiod_criteria() != null) && (criteria.getNoticeperiod_criteria().equals("GREATER_THAN_EQUAL"))) {
        sql = sql + " and CONVERT(ja.noticeperiod, DECIMAL) >= " + criteria.getNoticeperiod();
      } else if ((criteria.getNoticeperiod_criteria() != null) && (criteria.getNoticeperiod_criteria().equals("LESS_THAN"))) {
        sql = sql + " and CONVERT(ja.noticeperiod, DECIMAL) < " + criteria.getNoticeperiod();
      } else if ((criteria.getNoticeperiod_criteria() != null) && (criteria.getNoticeperiod_criteria().equals("LESS_THAN_EQUAL"))) {
        sql = sql + " and CONVERT(ja.noticeperiod, DECIMAL) <= " + criteria.getNoticeperiod();
      } else if ((criteria.getNoticeperiod_criteria() != null) && (criteria.getNoticeperiod_criteria().equals("NOT_EQUALS"))) {
        sql = sql + " and CONVERT(ja.noticeperiod, DECIMAL) <> " + criteria.getNoticeperiod();
      } else if ((criteria.getNoticeperiod_criteria() != null) && (criteria.getNoticeperiod_criteria().equals("BETWEEN"))) {
        sql = sql + " and CONVERT(ja.noticeperiod, DECIMAL) >= " + criteria.getNoticeperiod() + " and CONVERT(ja.noticeperiod, DECIMAL) <= " + criteria.getNoticeperiod1();
      }
    }
    if (criteria.getNoofyearsexp() != 2147483647.0D) {
      if ((criteria.getNoofyearsexp_criteria() != null) && (criteria.getNoofyearsexp_criteria().equals("EQUALS"))) {
        sql = sql + " and ja.noofyearsexp = " + criteria.getNoofyearsexp();
      } else if ((criteria.getNoofyearsexp_criteria() != null) && (criteria.getNoofyearsexp_criteria().equals("GREATER_THAN"))) {
        sql = sql + " and ja.noofyearsexp > " + criteria.getNoofyearsexp();
      } else if ((criteria.getNoofyearsexp_criteria() != null) && (criteria.getNoofyearsexp_criteria().equals("GREATER_THAN_EQUAL"))) {
        sql = sql + " and ja.noofyearsexp >= " + criteria.getNoofyearsexp();
      } else if ((criteria.getNoofyearsexp_criteria() != null) && (criteria.getNoofyearsexp_criteria().equals("LESS_THAN"))) {
        sql = sql + " and ja.noofyearsexp < " + criteria.getNoofyearsexp();
      } else if ((criteria.getNoofyearsexp_criteria() != null) && (criteria.getNoofyearsexp_criteria().equals("LESS_THAN_EQUAL"))) {
        sql = sql + " and ja.noofyearsexp <= " + criteria.getNoofyearsexp();
      } else if ((criteria.getNoofyearsexp_criteria() != null) && (criteria.getNoofyearsexp_criteria().equals("NOT_EQUALS"))) {
        sql = sql + " and ja.noofyearsexp <> " + criteria.getNoofyearsexp();
      } else if ((criteria.getNoofyearsexp_criteria() != null) && (criteria.getNoofyearsexp_criteria().equals("BETWEEN"))) {
        sql = sql + " and CONVERT(ja.noofyearsexp, DECIMAL) >= " + criteria.getNoofyearsexp() + " and CONVERT(ja.noofyearsexp, DECIMAL) <= " + criteria.getNoofyearsexp1();
      }
    }
    if ((!StringUtils.isNullOrEmpty(criteria.getCurrentdesignation())) && (!criteria.getCurrentdesignation().equals("null")))
    {
      if ((criteria.getCurrentdesignation_criteria() != null) && (criteria.getCurrentdesignation_criteria().equals("STARTS_WITH"))) {
        sql = sql + " and ja.currentdesignation like '" + criteria.getCurrentdesignation() + "%' ";
      } else if ((criteria.getCurrentdesignation_criteria() != null) && (criteria.getCurrentdesignation_criteria().equals("ENDS_WITH"))) {
        sql = sql + " and ja.currentdesignation like '%" + criteria.getCurrentdesignation() + "' ";
      } else if ((criteria.getCurrentdesignation_criteria() != null) && (criteria.getCurrentdesignation_criteria().equals("CONTAINS"))) {
        sql = sql + " and ja.currentdesignation like '%" + criteria.getCurrentdesignation() + "%' ";
      } else if ((criteria.getCurrentdesignation_criteria() != null) && (criteria.getCurrentdesignation_criteria().equals("EQUALS"))) {
        sql = sql + " and ja.currentdesignation = '" + criteria.getCurrentdesignation() + "' ";
      }
    }
    else if ((criteria.getCurrentdesignation_criteria() != null) && (criteria.getCurrentdesignation_criteria().equals("EMPTY"))) {
      sql = sql + " and ja.currentdesignation is NULL ";
    } else if ((criteria.getCurrentdesignation_criteria() != null) && (criteria.getCurrentdesignation_criteria().equals("NOT_EMPTY"))) {
      sql = sql + " and ja.currentdesignation is NOT NULL ";
    }
    if ((!StringUtils.isNullOrEmpty(criteria.getResumeHeader())) && (!criteria.getResumeHeader().equals("null")))
    {
      if ((criteria.getResumeHeader_criteria() != null) && (criteria.getResumeHeader_criteria().equals("STARTS_WITH"))) {
        sql = sql + " and ja.resume_header like '" + criteria.getResumeHeader() + "%' ";
      } else if ((criteria.getResumeHeader_criteria() != null) && (criteria.getResumeHeader_criteria().equals("ENDS_WITH"))) {
        sql = sql + " and ja.resume_header like '%" + criteria.getResumeHeader() + "' ";
      } else if ((criteria.getResumeHeader_criteria() != null) && (criteria.getResumeHeader_criteria().equals("CONTAINS"))) {
        sql = sql + " and ja.resume_header like '%" + criteria.getResumeHeader() + "%' ";
      } else if ((criteria.getResumeHeader_criteria() != null) && (criteria.getResumeHeader_criteria().equals("EQUALS"))) {
        sql = sql + " and ja.resume_header = '" + criteria.getResumeHeader() + "' ";
      }
    }
    else if ((criteria.getResumeHeader_criteria() != null) && (criteria.getResumeHeader_criteria().equals("EMPTY"))) {
      sql = sql + " and ja.resume_header is NULL ";
    } else if ((criteria.getResumeHeader_criteria() != null) && (criteria.getResumeHeader_criteria().equals("NOT_EMPTY"))) {
      sql = sql + " and ja.resume_header is NOT NULL ";
    }
    if ((!StringUtils.isNullOrEmpty(criteria.getEarliest_start_date())) && (!criteria.getEarliest_start_date().equals("null")))
    {
      String datepattern = DateUtil.defaultdateformatforSQL;
      String datepatternuser = DateUtil.getDatePatternFormat(user.getLocale());
      Date eart = DateUtil.convertStringDateToDate(criteria.getEarliest_start_date(), datepatternuser);
      String ear = DateUtil.convertDateToStringDate(eart, datepattern);
      if ((criteria.getEarliest_start_date_criteria() != null) && (criteria.getEarliest_start_date_criteria().equals("EQUALS")))
      {
        sql = sql + " and DATE_FORMAT( jao.earliest_start_date, '%M %e, %Y')  = " + "'" + ear + "'";
      }
      else if ((criteria.getEarliest_start_date_criteria() != null) && (criteria.getEarliest_start_date_criteria().equals("GREATER_THAN")))
      {
        sql = sql + " and DATE_FORMAT( jao.earliest_start_date, '%M %e, %Y')  > " + "'" + ear + "'";
      }
      else if ((criteria.getEarliest_start_date_criteria() != null) && (criteria.getEarliest_start_date_criteria().equals("GREATER_THAN_EQUAL")))
      {
        sql = sql + " and DATE_FORMAT( jao.earliest_start_date, '%M %e, %Y')  >= " + "'" + ear + "'";
      }
      else if ((criteria.getEarliest_start_date_criteria() != null) && (criteria.getEarliest_start_date_criteria().equals("LESS_THAN")))
      {
        sql = sql + " and DATE_FORMAT( jao.earliest_start_date, '%M %e, %Y')  < " + "'" + ear + "'";
      }
      else if ((criteria.getEarliest_start_date_criteria() != null) && (criteria.getEarliest_start_date_criteria().equals("LESS_THAN_EQUAL")))
      {
        sql = sql + " and DATE_FORMAT( jao.earliest_start_date, '%M %e, %Y') <= " + "'" + ear + "'";
      }
      else if ((criteria.getEarliest_start_date_criteria() != null) && (criteria.getEarliest_start_date_criteria().equals("NOT_EQUALS")))
      {
        sql = sql + " and DATE_FORMAT( jao.earliest_start_date, '%M %e, %Y')  <> " + "'" + ear + "'";
      }
      else if ((criteria.getEarliest_start_date_criteria() != null) && (criteria.getEarliest_start_date_criteria().equals("BETWEEN")))
      {
        logger.info("criteria.getEarliest_start_date1() >> " + criteria.getEarliest_start_date1());
        if ((!StringUtils.isNullOrEmpty(criteria.getEarliest_start_date1())) && (!criteria.getEarliest_start_date1().equals("null")))
        {
          Date eart1 = DateUtil.convertStringDateToDate(criteria.getEarliest_start_date1(), datepatternuser);
          String ear1 = DateUtil.convertDateToStringDate(eart1, datepattern);
          
          sql = sql + " and DATE_FORMAT( jao.earliest_start_date, '%M %e, %Y')  >= " + "'" + ear + "'" + " and DATE_FORMAT( jao.earliest_start_date, '%M %e, %Y')  <= " + "'" + ear1 + "'";
        }
      }
    }
    else if ((criteria.getEarliest_start_date_criteria() != null) && (criteria.getEarliest_start_date_criteria().equals("EMPTY")))
    {
      sql = sql + " and  jao.earliest_start_date is NULL ";
    }
    else if ((criteria.getEarliest_start_date_criteria() != null) && (criteria.getEarliest_start_date_criteria().equals("NOT_EMPTY")))
    {
      sql = sql + " and  jao.earliest_start_date is NOT NULL ";
    }
    if ((!StringUtils.isNullOrEmpty(criteria.getWork_on_weekends())) && (!criteria.getWork_on_weekends().equals("null"))) {
      sql = sql + " and jao.work_on_weekends = '" + criteria.getWork_on_weekends() + "' ";
    }
    if ((!StringUtils.isNullOrEmpty(criteria.getWork_on_evenings())) && (!criteria.getWork_on_evenings().equals("null"))) {
      sql = sql + " and jao.work_on_evenings = '" + criteria.getWork_on_evenings() + "' ";
    }
    if ((!StringUtils.isNullOrEmpty(criteria.getWork_on_overtime())) && (!criteria.getWork_on_overtime().equals("null"))) {
      sql = sql + " and jao.work_on_overtime = '" + criteria.getWork_on_overtime() + "' ";
    }
    if ((!StringUtils.isNullOrEmpty(criteria.getWant_to_relocate())) && (!criteria.getWant_to_relocate().equals("null"))) {
      sql = sql + " and jao.want_to_relocate = '" + criteria.getWant_to_relocate() + "' ";
    }
    if ((!StringUtils.isNullOrEmpty(criteria.getPreferedlocation())) && (!criteria.getPreferedlocation().equals("null")))
    {
      if ((criteria.getPreferedlocation_criteria() != null) && (criteria.getPreferedlocation_criteria().equals("STARTS_WITH"))) {
        sql = sql + " and ja.preferedlocation like '" + criteria.getPreferedlocation() + "%' ";
      } else if ((criteria.getPreferedlocation_criteria() != null) && (criteria.getPreferedlocation_criteria().equals("ENDS_WITH"))) {
        sql = sql + " and ja.preferedlocation like '%" + criteria.getPreferedlocation() + "' ";
      } else if ((criteria.getPreferedlocation_criteria() != null) && (criteria.getPreferedlocation_criteria().equals("CONTAINS"))) {
        sql = sql + " and ja.preferedlocation like '%" + criteria.getPreferedlocation() + "%' ";
      } else if ((criteria.getPreferedlocation_criteria() != null) && (criteria.getPreferedlocation_criteria().equals("EQUALS"))) {
        sql = sql + " and ja.preferedlocation = '" + criteria.getPreferedlocation() + "' ";
      }
    }
    else if ((criteria.getPreferedlocation_criteria() != null) && (criteria.getPreferedlocation_criteria().equals("EMPTY"))) {
      sql = sql + " and ja.preferedlocation is NULL ";
    } else if ((criteria.getPreferedlocation_criteria() != null) && (criteria.getPreferedlocation_criteria().equals("NOT_EMPTY"))) {
      sql = sql + " and ja.preferedlocation is NOT NULL ";
    }
    if ((criteria.getPrimarySkillList() != null) && (criteria.getPrimarySkillList().size() > 0)) {
      sql = sql + " and ja.primary_skill in (" + buildCommaseparated(criteria.getPrimarySkillList()) + ") ";
    }
    if ((!StringUtils.isNullOrEmpty(criteria.getHeighestQualification())) && (!criteria.getHeighestQualification().equals("null")))
    {
      if ((criteria.getHeighestQualification_criteria() != null) && (criteria.getHeighestQualification_criteria().equals("STARTS_WITH"))) {
        sql = sql + " and ja.heighest_qualification like '" + criteria.getHeighestQualification() + "%' ";
      } else if ((criteria.getHeighestQualification_criteria() != null) && (criteria.getHeighestQualification_criteria().equals("ENDS_WITH"))) {
        sql = sql + " and ja.heighest_qualification like '%" + criteria.getHeighestQualification() + "' ";
      } else if ((criteria.getHeighestQualification_criteria() != null) && (criteria.getHeighestQualification_criteria().equals("CONTAINS"))) {
        sql = sql + " and ja.heighest_qualification like '%" + criteria.getHeighestQualification() + "%' ";
      } else if ((criteria.getHeighestQualification_criteria() != null) && (criteria.getHeighestQualification_criteria().equals("EQUALS"))) {
        sql = sql + " and ja.heighest_qualification = '" + criteria.getHeighestQualification() + "' ";
      }
    }
    else if ((criteria.getHeighestQualification_criteria() != null) && (criteria.getHeighestQualification_criteria().equals("EMPTY"))) {
      sql = sql + " and ja.heighest_qualification is NULL ";
    } else if ((criteria.getHeighestQualification_criteria() != null) && (criteria.getHeighestQualification_criteria().equals("NOT_EMPTY"))) {
      sql = sql + " and ja.heighest_qualification is NOT NULL ";
    }
    if ((!StringUtils.isNullOrEmpty(criteria.getQualifications())) && (!criteria.getQualifications().equals("null")))
    {
      if ((criteria.getQualifications_criteria() != null) && (criteria.getQualifications_criteria().equals("STARTS_WITH"))) {
        sql = sql + " and ja.qualifications like '" + criteria.getQualifications() + "%' ";
      } else if ((criteria.getQualifications_criteria() != null) && (criteria.getQualifications_criteria().equals("ENDS_WITH"))) {
        sql = sql + " and ja.qualifications like '%" + criteria.getQualifications() + "' ";
      } else if ((criteria.getQualifications_criteria() != null) && (criteria.getQualifications_criteria().equals("CONTAINS"))) {
        sql = sql + " and ja.qualifications like '%" + criteria.getQualifications() + "%' ";
      } else if ((criteria.getQualifications_criteria() != null) && (criteria.getQualifications_criteria().equals("EQUALS"))) {
        sql = sql + " and ja.qualifications = '" + criteria.getQualifications() + "' ";
      }
    }
    else if ((criteria.getQualifications_criteria() != null) && (criteria.getQualifications_criteria().equals("EMPTY"))) {
      sql = sql + " and ja.qualifications is NULL ";
    } else if ((criteria.getQualifications_criteria() != null) && (criteria.getQualifications_criteria().equals("NOT_EMPTY"))) {
      sql = sql + " and ja.qualifications is NOT NULL ";
    }
    if ((!StringUtils.isNullOrEmpty(criteria.getCollege_name())) && (!criteria.getCollege_name().equals("null")))
    {
      if ((criteria.getCollege_name_criteria() != null) && (criteria.getCollege_name_criteria().equals("STARTS_WITH"))) {
        sql = sql + " and jao.college_name like '" + criteria.getCollege_name() + "%' ";
      } else if ((criteria.getCollege_name_criteria() != null) && (criteria.getCollege_name_criteria().equals("ENDS_WITH"))) {
        sql = sql + " and jao.college_name like '%" + criteria.getCollege_name() + "' ";
      } else if ((criteria.getCollege_name_criteria() != null) && (criteria.getCollege_name_criteria().equals("CONTAINS"))) {
        sql = sql + " and jao.college_name like '%" + criteria.getCollege_name() + "%' ";
      } else if ((criteria.getCollege_name_criteria() != null) && (criteria.getCollege_name_criteria().equals("EQUALS"))) {
        sql = sql + " and jao.college_name = '" + criteria.getCollege_name() + "' ";
      }
    }
    else if ((criteria.getCollege_name_criteria() != null) && (criteria.getCollege_name_criteria().equals("EMPTY"))) {
      sql = sql + " and jao.college_name is NULL ";
    } else if ((criteria.getCollege_name_criteria() != null) && (criteria.getCollege_name_criteria().equals("NOT_EMPTY"))) {
      sql = sql + " and jao.college_name is NOT NULL ";
    }
    if (criteria.getCollege_GPA() != 0.0D) {
      if ((criteria.getCollege_GPA_criteria() != null) && (criteria.getCollege_GPA_criteria().equals("EQUALS"))) {
        sql = sql + " and CONVERT(jao.college_GPA, DECIMAL(10,2)) = " + criteria.getCollege_GPA();
      } else if ((criteria.getCollege_GPA_criteria() != null) && (criteria.getCollege_GPA_criteria().equals("GREATER_THAN"))) {
        sql = sql + " and CONVERT(jao.college_GPA, DECIMAL(10,2)) > " + criteria.getCollege_GPA();
      } else if ((criteria.getCollege_GPA_criteria() != null) && (criteria.getCollege_GPA_criteria().equals("GREATER_THAN_EQUAL"))) {
        sql = sql + " and CONVERT(jao.college_GPA, DECIMAL(10,2)) >= " + criteria.getCollege_GPA();
      } else if ((criteria.getCollege_GPA_criteria() != null) && (criteria.getCollege_GPA_criteria().equals("LESS_THAN"))) {
        sql = sql + " and CONVERT(jao.college_GPA, DECIMAL(10,2)) < " + criteria.getCollege_GPA();
      } else if ((criteria.getCollege_GPA_criteria() != null) && (criteria.getCollege_GPA_criteria().equals("LESS_THAN_EQUAL"))) {
        sql = sql + " and CONVERT(jao.college_GPA, DECIMAL(10,2)) <= " + criteria.getCollege_GPA();
      } else if ((criteria.getCollege_GPA_criteria() != null) && (criteria.getCollege_GPA_criteria().equals("NOT_EQUALS"))) {
        sql = sql + " and CONVERT(jao.college_GPA, DECIMAL(10,2)) <> " + criteria.getCollege_GPA();
      } else if ((criteria.getCollege_GPA_criteria() != null) && (criteria.getCollege_GPA_criteria().equals("BETWEEN"))) {
        sql = sql + " and CONVERT(jao.college_GPA, DECIMAL(10,2))  >= " + criteria.getCollege_GPA() + " and CONVERT(jao.college_GPA, DECIMAL(10,2)) <= " + criteria.getCollege_GPA1();
      }
    }
    if ((criteria.getZipCodeList() != null) && (criteria.getZipCodeList().size() > 0)) {
      sql = sql + " and CONVERT(jao.zipcode, DECIMAL) in (" + buildCommaseparatedLong(criteria.getZipCodeList()) + ") ";
    } else if (criteria.getZipCodeSearchSize() == 9223372036854775807L) {
      sql = sql + " and CONVERT(jao.zipcode, DECIMAL) in (" + 9223372036854775807L + ") ";
    }
    if ((!StringUtils.isNullOrEmpty(criteria.getLanguages_spoken())) && (!criteria.getLanguages_spoken().equals("null")))
    {
      if ((criteria.getLanguages_spoken_criteria() != null) && (criteria.getLanguages_spoken_criteria().equals("STARTS_WITH"))) {
        sql = sql + " and jao.languages_spoken like '" + criteria.getLanguages_spoken() + "%' ";
      } else if ((criteria.getLanguages_spoken_criteria() != null) && (criteria.getLanguages_spoken_criteria().equals("ENDS_WITH"))) {
        sql = sql + " and jao.languages_spoken like '%" + criteria.getLanguages_spoken() + "' ";
      } else if ((criteria.getLanguages_spoken_criteria() != null) && (criteria.getLanguages_spoken_criteria().equals("CONTAINS"))) {
        sql = sql + " and jao.languages_spoken like '%" + criteria.getLanguages_spoken() + "%' ";
      } else if ((criteria.getLanguages_spoken_criteria() != null) && (criteria.getLanguages_spoken_criteria().equals("EQUALS"))) {
        sql = sql + " and jao.languages_spoken = '" + criteria.getLanguages_spoken() + "' ";
      }
    }
    else if ((criteria.getLanguages_spoken_criteria() != null) && (criteria.getLanguages_spoken_criteria().equals("EMPTY"))) {
      sql = sql + " and jao.languages_spoken is NULL ";
    } else if ((criteria.getLanguages_spoken_criteria() != null) && (criteria.getLanguages_spoken_criteria().equals("NOT_EMPTY"))) {
      sql = sql + " and jao.languages_spoken is NOT NULL ";
    }
    if ((!StringUtils.isNullOrEmpty(criteria.getPrevorg())) && (!criteria.getPrevorg().equals("null")))
    {
      if ((criteria.getOrg_criteria() != null) && (criteria.getOrg_criteria().equals("STARTS_WITH"))) {
        sql = sql + " and ja.previousOrganization like '" + criteria.getPrevorg() + "%' ";
      } else if ((criteria.getOrg_criteria() != null) && (criteria.getOrg_criteria().equals("ENDS_WITH"))) {
        sql = sql + " and ja.previousOrganization like '%" + criteria.getPrevorg() + "' ";
      } else if ((criteria.getOrg_criteria() != null) && (criteria.getOrg_criteria().equals("CONTAINS"))) {
        sql = sql + " and ja.previousOrganization like '%" + criteria.getPrevorg() + "%' ";
      } else if ((criteria.getOrg_criteria() != null) && (criteria.getOrg_criteria().equals("EQUALS"))) {
        sql = sql + " and ja.previousOrganization = '" + criteria.getPrevorg() + "' ";
      }
    }
    else if ((criteria.getOrg_criteria() != null) && (criteria.getOrg_criteria().equals("EMPTY"))) {
      sql = sql + " and ja.previousOrganization is NULL ";
    } else if ((criteria.getOrg_criteria() != null) && (criteria.getOrg_criteria().equals("NOT_EMPTY"))) {
      sql = sql + " and ja.previousOrganization is NOT NULL ";
    }
    if ((!StringUtils.isNullOrEmpty(criteria.getEmail())) && (!criteria.getEmail().equals("null")))
    {
      if ((criteria.getEmail_criteria() != null) && (criteria.getEmail_criteria().equals("STARTS_WITH"))) {
        sql = sql + " and ja.email_id like '" + criteria.getEmail() + "%' ";
      } else if ((criteria.getEmail_criteria() != null) && (criteria.getEmail_criteria().equals("ENDS_WITH"))) {
        sql = sql + " and ja.email_id like '%" + criteria.getEmail() + "' ";
      } else if ((criteria.getEmail_criteria() != null) && (criteria.getEmail_criteria().equals("CONTAINS"))) {
        sql = sql + " and ja.email_id like '%" + criteria.getEmail() + "%' ";
      } else if ((criteria.getEmail_criteria() != null) && (criteria.getEmail_criteria().equals("EQUALS"))) {
        sql = sql + " and ja.email_id = '" + criteria.getEmail() + "' ";
      }
    }
    else if ((criteria.getEmail_criteria() != null) && (criteria.getEmail_criteria().equals("EMPTY"))) {
      sql = sql + " and ja.email_id is NULL ";
    } else if ((criteria.getEmail_criteria() != null) && (criteria.getEmail_criteria().equals("NOT_EMPTY"))) {
      sql = sql + " and ja.email_id is NOT NULL ";
    }
    if ((!StringUtils.isNullOrEmpty(criteria.getOnboardingProcessStatus())) && (!criteria.getOnboardingProcessStatus().equals("null"))) {
      sql = sql + " and ja.isInitiateJoiningProcess = '" + criteria.getOnboardingProcessStatus() + "' ";
    }
    if ((criteria.getOrgIdList() != null) && (criteria.getOrgIdList().size() > 0)) {
      sql = sql + " and jr.org_id in (" + buildCommaseparatedLong(criteria.getOrgIdList()) + ") ";
    }
    if ((criteria.getDepartmentIdsList() != null) && (criteria.getDepartmentIdsList().size() > 0)) {
      sql = sql + " and jr.department_id in (" + buildCommaseparatedLong(criteria.getDepartmentIdsList()) + ") ";
    }
    if ((criteria.getProjectIdList() != null) && (criteria.getProjectIdList().size() > 0)) {
      sql = sql + " and jr.project_id in (" + buildCommaseparatedLong(criteria.getProjectIdList()) + ") ";
    }
    if ((criteria.getRequitionList() != null) && (criteria.getRequitionList().size() > 0)) {
      sql = sql + " and ja.jb_req_id in (" + buildCommaseparatedLong(criteria.getRequitionList()) + ") ";
    }
    if ((criteria.getTagIdList() != null) && (criteria.getTagIdList().size() > 0)) {
      sql = sql + " and ja.application_id in ( select applicant_id from applicant_tags where tag_name in (select tag_name from tags_data  where tag_id in(" + buildCommaseparatedLong(criteria.getTagIdList()) + ")))";
    }
    if ((criteria.getRequitionList() != null) && (criteria.getRequitionList().size() > 0)) {
      sql = sql + " and ja.jb_req_id in (" + buildCommaseparatedLong(criteria.getRequitionList()) + ") ";
    }
    if (criteria.getMockQuestionSetId() != 0)
    {
      sql = sql + " and ja.application_id in ( ";
      if ((criteria.getMockquestionset_criteria() != null) && (criteria.getMockquestionset_criteria().equals("EQUALS"))) {
        sql = sql + " select applicant_id from mocktest where cat_id=" + criteria.getMockQuestionSetId() + " and percentage = " + criteria.getMockquestionsetValue1();
      } else if ((criteria.getMockquestionset_criteria() != null) && (criteria.getMockquestionset_criteria().equals("GREATER_THAN"))) {
        sql = sql + " select applicant_id from mocktest where cat_id=" + criteria.getMockQuestionSetId() + " and  percentage > " + criteria.getMockquestionsetValue1();
      } else if ((criteria.getMockquestionset_criteria() != null) && (criteria.getMockquestionset_criteria().equals("GREATER_THAN_EQUAL"))) {
        sql = sql + " select applicant_id from mocktest where cat_id=" + criteria.getMockQuestionSetId() + " and  percentage >= " + criteria.getMockquestionsetValue1();
      } else if ((criteria.getMockquestionset_criteria() != null) && (criteria.getMockquestionset_criteria().equals("LESS_THAN"))) {
        sql = sql + " select applicant_id from mocktest where cat_id=" + criteria.getMockQuestionSetId() + " and  percentage < " + criteria.getMockquestionsetValue1();
      } else if ((criteria.getMockquestionset_criteria() != null) && (criteria.getMockquestionset_criteria().equals("LESS_THAN_EQUAL"))) {
        sql = sql + " select applicant_id from mocktest where cat_id=" + criteria.getMockQuestionSetId() + " and  percentage <= " + criteria.getMockquestionsetValue1();
      } else if ((criteria.getMockquestionset_criteria() != null) && (criteria.getMockquestionset_criteria().equals("NOT_EQUALS"))) {
        sql = sql + " select applicant_id from mocktest where cat_id=" + criteria.getMockQuestionSetId() + " and  percentage <> " + criteria.getMockquestionsetValue1();
      } else if ((criteria.getMockquestionset_criteria() != null) && (criteria.getMockquestionset_criteria().equals("BETWEEN"))) {
        sql = sql + " select applicant_id from mocktest where cat_id=" + criteria.getMockQuestionSetId() + " and  percentage >= " + criteria.getMockquestionsetValue1() + " and percentage <= " + criteria.getMockquestionsetValue2();
      }
      sql = sql + " )";
    }
    logger.info("criteria.getCustomFieldCriList()" + criteria.getCustomFieldCriList());
    if ((criteria.getCustomFieldCriList() != null) && (criteria.getCustomFieldCriList().size() > 0))
    {
      logger.info("criteria.getCustomFieldCriList().size()" + criteria.getCustomFieldCriList().size());
      
      List<String> sqlList = new ArrayList();
      for (int i = 0; i < criteria.getCustomFieldCriList().size(); i++)
      {
        String sqltemp = "";
        
        SearchApplicantCustomFields customdata = (SearchApplicantCustomFields)criteria.getCustomFieldCriList().get(i);
        if ((customdata.getVaribale_type() != null) && (customdata.getVaribale_type().equals("numeric")))
        {
          if (!StringUtils.isNullOrEmpty(customdata.getFilterValue1()))
          {
            if ((customdata.getFiltercri() != null) && (customdata.getFiltercri().equals("EQUALS"))) {
              sqltemp = sqltemp + " select idvalue from variable_data_values where form_code in  (" + buildCommaseparated(Constant.getAllApplicantScreenList()) + ") and variable_id=" + customdata.getVariable_id() + " and CONVERT(value_text, DECIMAL(10,2)) = " + customdata.getFilterValue1();
            } else if ((customdata.getFiltercri() != null) && (customdata.getFiltercri().equals("GREATER_THAN"))) {
              sqltemp = sqltemp + " select idvalue from variable_data_values where form_code in  (" + buildCommaseparated(Constant.getAllApplicantScreenList()) + ") and variable_id=" + customdata.getVariable_id() + " and CONVERT(value_text, DECIMAL(10,2)) > " + customdata.getFilterValue1();
            } else if ((customdata.getFiltercri() != null) && (customdata.getFiltercri().equals("GREATER_THAN_EQUAL"))) {
              sqltemp = sqltemp + " select idvalue from variable_data_values where form_code in  (" + buildCommaseparated(Constant.getAllApplicantScreenList()) + ") and variable_id=" + customdata.getVariable_id() + " and CONVERT(value_text, DECIMAL(10,2)) >= " + customdata.getFilterValue1();
            } else if ((customdata.getFiltercri() != null) && (customdata.getFiltercri().equals("LESS_THAN"))) {
              sqltemp = sqltemp + " select idvalue from variable_data_values where form_code in  (" + buildCommaseparated(Constant.getAllApplicantScreenList()) + ") and variable_id=" + customdata.getVariable_id() + " and CONVERT(value_text, DECIMAL(10,2)) < " + customdata.getFilterValue1();
            } else if ((customdata.getFiltercri() != null) && (customdata.getFiltercri().equals("LESS_THAN_EQUAL"))) {
              sqltemp = sqltemp + " select idvalue from variable_data_values where form_code in  (" + buildCommaseparated(Constant.getAllApplicantScreenList()) + ") and variable_id=" + customdata.getVariable_id() + " and CONVERT(value_text, DECIMAL(10,2)) <= " + customdata.getFilterValue1();
            } else if ((customdata.getFiltercri() != null) && (customdata.getFiltercri().equals("NOT_EQUALS"))) {
              sqltemp = sqltemp + " select idvalue from variable_data_values where form_code in  (" + buildCommaseparated(Constant.getAllApplicantScreenList()) + ") and variable_id=" + customdata.getVariable_id() + " and CONVERT(value_text, DECIMAL(10,2)) <> " + customdata.getFilterValue1();
            } else if ((customdata.getFiltercri() != null) && (customdata.getFiltercri().equals("BETWEEN"))) {
              sqltemp = sqltemp + " select idvalue from variable_data_values where form_code in  (" + buildCommaseparated(Constant.getAllApplicantScreenList()) + ") and variable_id=" + customdata.getVariable_id() + " and CONVERT(value_text, DECIMAL(10,2)) >= " + customdata.getFilterValue1() + " and and CONVERT(value_text, DECIMAL(10,2)) <= " + customdata.getFilterValue2();
            } else if ((customdata.getFiltercri() != null) && (customdata.getFiltercri().equals("EMPTY"))) {
              sqltemp = sqltemp + " select idvalue from variable_data_values where form_code in  (" + buildCommaseparated(Constant.getAllApplicantScreenList()) + ") and variable_id=" + customdata.getVariable_id() + " and value_text is NULL";
            } else if ((customdata.getFiltercri() != null) && (customdata.getFiltercri().equals("NOT_EMPTY"))) {
              sqltemp = sqltemp + " select idvalue from variable_data_values where form_code in  (" + buildCommaseparated(Constant.getAllApplicantScreenList()) + ") and variable_id=" + customdata.getVariable_id() + " and value_text is NOT NULL";
            }
            sqlList.add(sqltemp);
          }
        }
        else if ((customdata.getVaribale_type() != null) && (customdata.getVaribale_type().equals("date")))
        {
          if (!StringUtils.isNullOrEmpty(customdata.getFilterValue1()))
          {
            String datepattern = DateUtil.defaultdateformatforSQL;
            String datepatternuser = DateUtil.getDatePatternFormat(user.getLocale());
            
            Date fdate = DateUtil.convertStringDateToDate(customdata.getFilterValue1(), datepatternuser);
            String date1 = DateUtil.convertDateToStringDate(fdate, datepattern);
            if ((customdata.getFiltercri() != null) && (customdata.getFiltercri().equals("EQUALS"))) {
              sqltemp = sqltemp + " select idvalue from variable_data_values where form_code in  (" + buildCommaseparated(Constant.getAllApplicantScreenList()) + ") and variable_id=" + customdata.getVariable_id() + " and DATE_FORMAT( value_text, '%M %e, %Y') = '" + date1 + "'";
            } else if ((customdata.getFiltercri() != null) && (customdata.getFiltercri().equals("GREATER_THAN"))) {
              sqltemp = sqltemp + " select idvalue from variable_data_values where form_code in  (" + buildCommaseparated(Constant.getAllApplicantScreenList()) + ") and variable_id=" + customdata.getVariable_id() + " and DATE_FORMAT( value_text, '%M %e, %Y') > '" + date1 + "'";
            } else if ((customdata.getFiltercri() != null) && (customdata.getFiltercri().equals("GREATER_THAN_EQUAL"))) {
              sqltemp = sqltemp + " select idvalue from variable_data_values where form_code in  (" + buildCommaseparated(Constant.getAllApplicantScreenList()) + ") and variable_id=" + customdata.getVariable_id() + " and DATE_FORMAT( value_text, '%M %e, %Y') >= '" + date1 + "'";
            } else if ((customdata.getFiltercri() != null) && (customdata.getFiltercri().equals("LESS_THAN"))) {
              sqltemp = sqltemp + " select idvalue from variable_data_values where form_code in  (" + buildCommaseparated(Constant.getAllApplicantScreenList()) + ") and variable_id=" + customdata.getVariable_id() + " and DATE_FORMAT( value_text, '%M %e, %Y') < '" + date1 + "'";
            } else if ((customdata.getFiltercri() != null) && (customdata.getFiltercri().equals("LESS_THAN_EQUAL"))) {
              sqltemp = sqltemp + " select idvalue from variable_data_values where form_code in  (" + buildCommaseparated(Constant.getAllApplicantScreenList()) + ") and variable_id=" + customdata.getVariable_id() + " and DATE_FORMAT( value_text, '%M %e, %Y') <= '" + date1 + "'";
            } else if ((customdata.getFiltercri() != null) && (customdata.getFiltercri().equals("NOT_EQUALS"))) {
              sqltemp = sqltemp + " select idvalue from variable_data_values where form_code in  (" + buildCommaseparated(Constant.getAllApplicantScreenList()) + ") and variable_id=" + customdata.getVariable_id() + " and DATE_FORMAT( value_text, '%M %e, %Y') <> '" + date1 + "'";
            } else if ((customdata.getFiltercri() != null) && (customdata.getFiltercri().equals("BETWEEN")))
            {
              if (!StringUtils.isNullOrEmpty(customdata.getFilterValue2()))
              {
                Date fdate2 = DateUtil.convertStringDateToDate(customdata.getFilterValue2(), datepatternuser);
                String date2 = DateUtil.convertDateToStringDate(fdate, datepattern);
                sqltemp = sqltemp + " select idvalue from variable_data_values where form_code in  (" + buildCommaseparated(Constant.getAllApplicantScreenList()) + ") and variable_id=" + customdata.getVariable_id() + " and DATE_FORMAT( value_text, '%M %e, %Y') >= '" + date1 + "'" + " and DATE_FORMAT( value_text, '%M %e, %Y') <= '" + date2 + "'";
              }
            }
            else if ((customdata.getFiltercri() != null) && (customdata.getFiltercri().equals("EMPTY"))) {
              sqltemp = sqltemp + " select idvalue from variable_data_values where form_code in  (" + buildCommaseparated(Constant.getAllApplicantScreenList()) + ") and variable_id=" + customdata.getVariable_id() + " and value_text is NULL";
            } else if ((customdata.getFiltercri() != null) && (customdata.getFiltercri().equals("NOT_EMPTY"))) {
              sqltemp = sqltemp + " select idvalue from variable_data_values where form_code in  (" + buildCommaseparated(Constant.getAllApplicantScreenList()) + ") and variable_id=" + customdata.getVariable_id() + " and value_text is NOT NULL";
            }
            sqlList.add(sqltemp);
          }
        }
        else if ((customdata.getVaribale_type() != null) && ((customdata.getVaribale_type().equals("text")) || (customdata.getVaribale_type().equals("textarea"))))
        {
          if (!StringUtils.isNullOrEmpty(customdata.getFilterValue1()))
          {
            if ((customdata.getFiltercri() != null) && (customdata.getFiltercri().equals("STARTS_WITH"))) {
              sqltemp = sqltemp + " select idvalue from variable_data_values where form_code in  (" + buildCommaseparated(Constant.getAllApplicantScreenList()) + ") and variable_id=" + customdata.getVariable_id() + " and value_text like '" + StringUtils.parseQuoteForSQL(customdata.getFilterValue1()) + "%'";
            } else if ((customdata.getFiltercri() != null) && (customdata.getFiltercri().equals("ENDS_WITH"))) {
              sqltemp = sqltemp + " select idvalue from variable_data_values where form_code in  (" + buildCommaseparated(Constant.getAllApplicantScreenList()) + ") and variable_id=" + customdata.getVariable_id() + " and value_text like '%" + customdata.getFilterValue1() + "'";
            } else if ((customdata.getFiltercri() != null) && (customdata.getFiltercri().equals("CONTAINS"))) {
              sqltemp = sqltemp + " select idvalue from variable_data_values where form_code in  (" + buildCommaseparated(Constant.getAllApplicantScreenList()) + ") and variable_id=" + customdata.getVariable_id() + " and value_text like '%" + customdata.getFilterValue1() + "%'";
            } else if ((customdata.getFiltercri() != null) && (customdata.getFiltercri().equals("EQUALS"))) {
              sqltemp = sqltemp + " select idvalue from variable_data_values where form_code in  (" + buildCommaseparated(Constant.getAllApplicantScreenList()) + ") and variable_id=" + customdata.getVariable_id() + " and value_text like '" + customdata.getFilterValue1() + "'";
            } else if ((customdata.getFiltercri() != null) && (customdata.getFiltercri().equals("EMPTY"))) {
              sqltemp = sqltemp + " select idvalue from variable_data_values where form_code in  (" + buildCommaseparated(Constant.getAllApplicantScreenList()) + ") and variable_id=" + customdata.getVariable_id() + " and value_text is NULL";
            } else if ((customdata.getFiltercri() != null) && (customdata.getFiltercri().equals("NOT_EMPTY"))) {
              sqltemp = sqltemp + " select idvalue from variable_data_values where form_code in  (" + buildCommaseparated(Constant.getAllApplicantScreenList()) + ") and variable_id=" + customdata.getVariable_id() + " and value_text is NOT NULL";
            }
            if (!StringUtils.isNullOrEmpty(sqltemp)) {
              sqlList.add(sqltemp);
            }
          }
          else
          {
            if ((customdata.getFiltercri() != null) && (customdata.getFiltercri().equals("EMPTY"))) {
              sqltemp = sqltemp + " select idvalue from variable_data_values where form_code in  (" + buildCommaseparated(Constant.getAllApplicantScreenList()) + ") and variable_id=" + customdata.getVariable_id() + " and value_text is NULL";
            } else if ((customdata.getFiltercri() != null) && (customdata.getFiltercri().equals("NOT_EMPTY"))) {
              sqltemp = sqltemp + " select idvalue from variable_data_values where form_code in  (" + buildCommaseparated(Constant.getAllApplicantScreenList()) + ") and variable_id=" + customdata.getVariable_id() + " and value_text is not NULL";
            }
            if (!StringUtils.isNullOrEmpty(sqltemp)) {
              sqlList.add(sqltemp);
            }
          }
        }
        else if ((customdata.getVaribale_type() != null) && (customdata.getVaribale_type().equals("list"))) {
          if (!StringUtils.isNullOrEmpty(customdata.getAnswerOption()))
          {
            sqltemp = sqltemp + " select idvalue from variable_data_values where form_code in  (" + buildCommaseparated(Constant.getAllApplicantScreenList()) + ") and variable_id=" + customdata.getVariable_id() + " and value_text = '" + customdata.getAnswerOption() + "'";
            
            sqlList.add(sqltemp);
          }
        }
      }
      String finalsql = "";
      if (sqlList.size() == 1)
      {
        finalsql = finalsql + (String)sqlList.get(0);
      }
      else if (sqlList.size() > 1)
      {
        finalsql = (String)sqlList.get(0);
        for (int i = 1; i < sqlList.size(); i++) {
          finalsql = finalsql + " and idvalue  in ( " + (String)sqlList.get(i) + " ) ";
        }
      }
      if (sqlList.size() > 0) {
        sql = " and ja.application_id in ( " + finalsql + ") ";
      }
    }
    if ((criteria.getQuestionCriList() != null) && (criteria.getQuestionCriList().size() > 0))
    {
      sql = sql + " and ja.application_id in (";
      
      List<String> sqlList = new ArrayList();
      for (int i = 0; i < criteria.getQuestionCriList().size(); i++)
      {
        String sqltemp = "";
        SearchApplicantQuestions sappqns = (SearchApplicantQuestions)criteria.getQuestionCriList().get(i);
        if ((sappqns.getQnstype() != null) && (sappqns.getQnstype().equals("number")))
        {
          if ((sappqns.getFiltercri() != null) && (sappqns.getFiltercri().equals("EQUALS"))) {
            sqltemp = sqltemp + " select qga.applicant_id from questionnier_answers qa,question_group_applicants qga " + " where  qa.question_id= " + sappqns.getQuestion().getQuestionId() + " and CONVERT(qa.answer, DECIMAL) = " + sappqns.getFilterValue1() + " and qga.qns_grp_app_id=qa.question_group_applicant_id ";
          } else if ((sappqns.getFiltercri() != null) && (sappqns.getFiltercri().equals("GREATER_THAN"))) {
            sqltemp = sqltemp + " select qga.applicant_id from questionnier_answers qa,question_group_applicants qga " + " where  qa.question_id= " + sappqns.getQuestion().getQuestionId() + " and CONVERT(qa.answer, DECIMAL) > " + sappqns.getFilterValue1() + " and qga.qns_grp_app_id=qa.question_group_applicant_id ";
          } else if ((sappqns.getFiltercri() != null) && (sappqns.getFiltercri().equals("GREATER_THAN_EQUAL"))) {
            sqltemp = sqltemp + " select qga.applicant_id from questionnier_answers qa,question_group_applicants qga " + " where  qa.question_id= " + sappqns.getQuestion().getQuestionId() + " and CONVERT(qa.answer, DECIMAL) >= " + sappqns.getFilterValue1() + " and qga.qns_grp_app_id=qa.question_group_applicant_id ";
          } else if ((sappqns.getFiltercri() != null) && (sappqns.getFiltercri().equals("LESS_THAN"))) {
            sqltemp = sqltemp + " select qga.applicant_id from questionnier_answers qa,question_group_applicants qga " + " where  qa.question_id= " + sappqns.getQuestion().getQuestionId() + " and CONVERT(qa.answer, DECIMAL) < " + sappqns.getFilterValue1() + " and qga.qns_grp_app_id=qa.question_group_applicant_id ";
          } else if ((sappqns.getFiltercri() != null) && (sappqns.getFiltercri().equals("LESS_THAN_EQUAL"))) {
            sqltemp = sqltemp + " select qga.applicant_id from questionnier_answers qa,question_group_applicants qga " + " where  qa.question_id= " + sappqns.getQuestion().getQuestionId() + " and CONVERT(qa.answer, DECIMAL) <= " + sappqns.getFilterValue1() + " and qga.qns_grp_app_id=qa.question_group_applicant_id ";
          } else if ((sappqns.getFiltercri() != null) && (sappqns.getFiltercri().equals("NOT_EQUALS"))) {
            sqltemp = sqltemp + " select qga.applicant_id from questionnier_answers qa,question_group_applicants qga " + " where  qa.question_id= " + sappqns.getQuestion().getQuestionId() + " and CONVERT(qa.answer, DECIMAL) <> " + sappqns.getFilterValue1() + " and qga.qns_grp_app_id=qa.question_group_applicant_id ";
          } else if ((sappqns.getFiltercri() != null) && (sappqns.getFiltercri().equals("BETWEEN"))) {
            sqltemp = sqltemp + " select qga.applicant_id from questionnier_answers qa,question_group_applicants qga " + " where  qa.question_id= " + sappqns.getQuestion().getQuestionId() + " and CONVERT(qa.answer, DECIMAL) >= " + sappqns.getFilterValue1() + " and CONVERT(qa.answer, DECIMAL) <= " + sappqns.getFilterValue2() + " and qga.qns_grp_app_id=qa.question_group_applicant_id ";
          } else if ((sappqns.getFiltercri() != null) && (sappqns.getFiltercri().equals("EMPTY"))) {
            sqltemp = sqltemp + " select qga.applicant_id from questionnier_answers qa,question_group_applicants qga " + " where  qa.question_id= " + sappqns.getQuestion().getQuestionId() + " and qa.answer IS NULL and qga.qns_grp_app_id=qa.question_group_applicant_id ";
          } else if ((sappqns.getFiltercri() != null) && (sappqns.getFiltercri().equals("NOT_EMPTY"))) {
            sqltemp = sqltemp + " select qga.applicant_id from questionnier_answers qa,question_group_applicants qga " + " where  qa.question_id= " + sappqns.getQuestion().getQuestionId() + " and qa.answer IS NOT NULL and qga.qns_grp_app_id=qa.question_group_applicant_id ";
          }
          sqlList.add(sqltemp);
        }
        else if ((sappqns.getQnstype() != null) && (sappqns.getQnstype().equals("date")))
        {
          String datepattern = DateUtil.defaultdateformatforSQL;
          String datepatternuser = DateUtil.getDatePatternFormat(user.getLocale());
          
          Date fdate = DateUtil.convertStringDateToDate(sappqns.getFilterValue1(), datepatternuser);
          String date1 = DateUtil.convertDateToStringDate(fdate, datepattern);
          if ((sappqns.getFiltercri() != null) && (sappqns.getFiltercri().equals("EQUALS"))) {
            sqltemp = sqltemp + " select qga.applicant_id from questionnier_answers qa,question_group_applicants qga " + " where  qa.question_id= " + sappqns.getQuestion().getQuestionId() + " and DATE_FORMAT( jao.earliest_start_date, '%M %e, %Y') = '" + date1 + "' and qga.qns_grp_app_id=qa.question_group_applicant_id ";
          } else if ((sappqns.getFiltercri() != null) && (sappqns.getFiltercri().equals("GREATER_THAN"))) {
            sqltemp = sqltemp + " select qga.applicant_id from questionnier_answers qa,question_group_applicants qga " + " where  qa.question_id= " + sappqns.getQuestion().getQuestionId() + " and DATE_FORMAT( jao.earliest_start_date, '%M %e, %Y') > '" + date1 + "' and qga.qns_grp_app_id=qa.question_group_applicant_id ";
          } else if ((sappqns.getFiltercri() != null) && (sappqns.getFiltercri().equals("GREATER_THAN_EQUAL"))) {
            sqltemp = sqltemp + " select qga.applicant_id from questionnier_answers qa,question_group_applicants qga " + " where  qa.question_id= " + sappqns.getQuestion().getQuestionId() + " and DATE_FORMAT( jao.earliest_start_date, '%M %e, %Y') >= '" + date1 + "' and qga.qns_grp_app_id=qa.question_group_applicant_id ";
          } else if ((sappqns.getFiltercri() != null) && (sappqns.getFiltercri().equals("LESS_THAN"))) {
            sqltemp = sqltemp + " select qga.applicant_id from questionnier_answers qa,question_group_applicants qga " + " where  qa.question_id= " + sappqns.getQuestion().getQuestionId() + " and DATE_FORMAT( jao.earliest_start_date, '%M %e, %Y') < '" + date1 + "' and qga.qns_grp_app_id=qa.question_group_applicant_id ";
          } else if ((sappqns.getFiltercri() != null) && (sappqns.getFiltercri().equals("LESS_THAN_EQUAL"))) {
            sqltemp = sqltemp + " select qga.applicant_id from questionnier_answers qa,question_group_applicants qga " + " where  qa.question_id= " + sappqns.getQuestion().getQuestionId() + " and DATE_FORMAT( jao.earliest_start_date, '%M %e, %Y') <= '" + date1 + "' and qga.qns_grp_app_id=qa.question_group_applicant_id ";
          } else if ((sappqns.getFiltercri() != null) && (sappqns.getFiltercri().equals("NOT_EQUALS"))) {
            sqltemp = sqltemp + " select qga.applicant_id from questionnier_answers qa,question_group_applicants qga " + " where  qa.question_id= " + sappqns.getQuestion().getQuestionId() + " and DATE_FORMAT( jao.earliest_start_date, '%M %e, %Y') <> '" + date1 + "' and qga.qns_grp_app_id=qa.question_group_applicant_id ";
          } else if ((sappqns.getFiltercri() != null) && (sappqns.getFiltercri().equals("BETWEEN")))
          {
            if (!StringUtils.isNullOrEmpty(sappqns.getFilterValue2()))
            {
              Date fdate2 = DateUtil.convertStringDateToDate(sappqns.getFilterValue2(), datepatternuser);
              String date2 = DateUtil.convertDateToStringDate(fdate, datepattern);
              sqltemp = sqltemp + " select qga.applicant_id from questionnier_answers qa,question_group_applicants qga " + " where  qa.question_id= " + sappqns.getQuestion().getQuestionId() + " and DATE_FORMAT( jao.earliest_start_date, '%M %e, %Y') >= '" + date1 + "'" + " and DATE_FORMAT( jao.earliest_start_date, '%M %e, %Y') <= '" + date1 + "' and qga.qns_grp_app_id=qa.question_group_applicant_id ";
            }
          }
          else if ((sappqns.getFiltercri() != null) && (sappqns.getFiltercri().equals("EMPTY"))) {
            sqltemp = sqltemp + " select qga.applicant_id from questionnier_answers qa,question_group_applicants qga " + " where  qa.question_id= " + sappqns.getQuestion().getQuestionId() + " and jao.earliest_start_date IS NULL and qga.qns_grp_app_id=qa.question_group_applicant_id ";
          } else if ((sappqns.getFiltercri() != null) && (sappqns.getFiltercri().equals("NOT_EMPTY"))) {
            sqltemp = sqltemp + " select qga.applicant_id from questionnier_answers qa,question_group_applicants qga " + " where  qa.question_id= " + sappqns.getQuestion().getQuestionId() + " and jao.earliest_start_date IS NOT NULL and qga.qns_grp_app_id=qa.question_group_applicant_id ";
          }
          sqlList.add(sqltemp);
        }
        else if ((sappqns.getQnstype() != null) && ((sappqns.getQnstype().equals("text")) || (sappqns.getQnstype().equals("checkbox"))))
        {
          if ((sappqns.getFiltercri() != null) && (sappqns.getFiltercri().equals("STARTS_WITH"))) {
            sqltemp = sqltemp + " select qga.applicant_id from questionnier_answers qa,question_group_applicants qga " + " where  qa.question_id= " + sappqns.getQuestion().getQuestionId() + " and qa.answer like '" + sappqns.getFilterValue1() + "%'" + " and qga.qns_grp_app_id=qa.question_group_applicant_id ";
          } else if ((sappqns.getFiltercri() != null) && (sappqns.getFiltercri().equals("ENDS_WITH"))) {
            sqltemp = sqltemp + " select qga.applicant_id from questionnier_answers qa,question_group_applicants qga " + " where  qa.question_id= " + sappqns.getQuestion().getQuestionId() + " and qa.answer like '%" + sappqns.getFilterValue1() + "'" + " and qga.qns_grp_app_id=qa.question_group_applicant_id ";
          } else if ((sappqns.getFiltercri() != null) && (sappqns.getFiltercri().equals("CONTAINS"))) {
            sqltemp = sqltemp + " select qga.applicant_id from questionnier_answers qa,question_group_applicants qga " + " where  qa.question_id= " + sappqns.getQuestion().getQuestionId() + " and qa.answer like '%" + sappqns.getFilterValue1() + "%'" + " and qga.qns_grp_app_id=qa.question_group_applicant_id ";
          } else if ((sappqns.getFiltercri() != null) && (sappqns.getFiltercri().equals("EQUALS"))) {
            sqltemp = sqltemp + " select qga.applicant_id from questionnier_answers qa,question_group_applicants qga " + " where  qa.question_id= " + sappqns.getQuestion().getQuestionId() + " and qa.answer = '" + sappqns.getFilterValue1() + "'" + " and qga.qns_grp_app_id=qa.question_group_applicant_id ";
          } else if ((sappqns.getFiltercri() != null) && (sappqns.getFiltercri().equals("EMPTY"))) {
            sqltemp = sqltemp + " select qga.applicant_id from questionnier_answers qa,question_group_applicants qga " + " where  qa.question_id= " + sappqns.getQuestion().getQuestionId() + " and qa.answer IS NULL and qga.qns_grp_app_id=qa.question_group_applicant_id ";
          } else if ((sappqns.getFiltercri() != null) && (sappqns.getFiltercri().equals("NOT_EMPTY"))) {
            sqltemp = sqltemp + " select qga.applicant_id from questionnier_answers qa,question_group_applicants qga " + " where  qa.question_id= " + sappqns.getQuestion().getQuestionId() + " and qa.answer IS NOT NULL and qga.qns_grp_app_id=qa.question_group_applicant_id ";
          }
          sqlList.add(sqltemp);
        }
        else if ((sappqns.getQnstype() != null) && ((sappqns.getQnstype().equals("radio")) || (sappqns.getQnstype().equals("dropdown"))))
        {
          sqltemp = sqltemp + " select qga.applicant_id from questionnier_answers qa,question_group_applicants qga " + " where  qa.question_id= " + sappqns.getQuestion().getQuestionId() + " and qa.answer = '" + sappqns.getAnswerOption() + "'" + " and qga.qns_grp_app_id=qa.question_group_applicant_id ";
          

          sqlList.add(sqltemp);
        }
      }
      String finalsql = "";
      if (sqlList.size() == 1)
      {
        finalsql = finalsql + (String)sqlList.get(0);
      }
      else if (sqlList.size() > 1)
      {
        finalsql = (String)sqlList.get(0);
        for (int i = 1; i < sqlList.size(); i++) {
          finalsql = finalsql + " and qga.applicant_id  in ( " + (String)sqlList.get(i) + " ) ";
        }
      }
      sql = sql + finalsql + ") ";
    }
    if ((!StringUtils.isNullOrEmpty(criteria.getAppliedcri())) && (!criteria.getAppliedcri().equals("NoValue")))
    {
      String datepattern = DateUtil.defaultdateformatforSQL;
      String datepatternuser = DateUtil.getDatePatternFormat(user.getLocale());
      
      String fromdate = criteria.getFromdate();
      String todate = criteria.getTodate();
      if (criteria.getAppliedcri().equals(Common.APPLIED_DATE))
      {
        logger.info("inside applied date");
        if (((StringUtils.isNullOrEmpty(fromdate)) || (fromdate.equals("null"))) && (StringUtils.isNullOrEmpty(todate)) && (todate.equals("null")))
        {
          logger.info("fromdate and todate is null" + fromdate + todate);
        }
        else
        {
          if ((!StringUtils.isNullOrEmpty(fromdate)) && (!fromdate.equals("null")))
          {
            Date fromdt1 = DateUtil.convertStringDateToDate(fromdate, datepatternuser);
            logger.info("fromdt1" + fromdt1);
            fromdate = DateUtil.convertDateToStringDate(fromdt1, datepattern);
          }
          else
          {
            fromdate = DateUtil.convertDateToStringDate(new Date(), datepattern);
          }
          if ((!StringUtils.isNullOrEmpty(todate)) && (!todate.equals("null")))
          {
            Date todt1 = DateUtil.convertStringDateToDate(todate, datepatternuser);
            logger.info("todt1" + todt1);
            todate = DateUtil.convertDateToStringDate(todt1, datepattern);
          }
          else
          {
            todate = DateUtil.convertDateToStringDate(new Date(), datepattern);
          }
          sql = sql + " and DATE_FORMAT( ja.applied_datetime, '%M %e, %Y')  >= " + "'" + fromdate + "'" + " and DATE_FORMAT( ja.applied_datetime, '%M %e, %Y')  <= " + "'" + todate + "'";
        }
      }
      if (criteria.getAppliedcri().equals(Common.TARGET_ON_BOARD_DATE))
      {
        logger.info("inside target on board");
        if (((StringUtils.isNullOrEmpty(fromdate)) || (fromdate.equals("null"))) && (StringUtils.isNullOrEmpty(todate)) && (todate.equals("null")))
        {
          logger.info("fromdate and todate is null" + fromdate + todate);
        }
        else
        {
          if ((!StringUtils.isNullOrEmpty(fromdate)) && (!fromdate.equals("null")))
          {
            Date fromdt1 = DateUtil.convertStringDateToDate(fromdate, datepatternuser);
            logger.info("fromdt1" + fromdt1);
            fromdate = DateUtil.convertDateToStringDate(fromdt1, datepattern);
          }
          else
          {
            fromdate = DateUtil.convertDateToStringDate(new Date(), datepattern);
          }
          if ((!StringUtils.isNullOrEmpty(todate)) && (!todate.equals("null")))
          {
            Date todt1 = DateUtil.convertStringDateToDate(todate, datepatternuser);
            logger.info("todt1" + todt1);
            todate = DateUtil.convertDateToStringDate(todt1, datepattern);
          }
          else
          {
            todate = DateUtil.convertDateToStringDate(new Date(), datepattern);
          }
          sql = sql + " and DATE_FORMAT( ja.targetjoining_date, '%M %e, %Y')  >= " + "'" + fromdate + "'" + " and DATE_FORMAT( ja.targetjoining_date, '%M %e, %Y')  <= " + "'" + todate + "'";
        }
      }
      if (criteria.getAppliedcri().equals(Common.TARGET_OFFER_RELEASE_DATE))
      {
        logger.info("inside target offer release date");
        if (((StringUtils.isNullOrEmpty(fromdate)) || (fromdate.equals("null"))) && (StringUtils.isNullOrEmpty(todate)) && (todate.equals("null")))
        {
          logger.info("fromdate and todate is null" + fromdate + todate);
        }
        else
        {
          if ((!StringUtils.isNullOrEmpty(fromdate)) && (!fromdate.equals("null")))
          {
            Date fromdt1 = DateUtil.convertStringDateToDate(fromdate, datepatternuser);
            
            logger.info("fromdt1" + fromdt1);
            fromdate = DateUtil.convertDateToStringDate(fromdt1, datepattern);
          }
          else
          {
            fromdate = DateUtil.convertDateToStringDate(new Date(), datepattern);
          }
          if ((!StringUtils.isNullOrEmpty(todate)) && (!todate.equals("null")))
          {
            Date todt1 = DateUtil.convertStringDateToDate(todate, datepatternuser);
            
            logger.info("todt1" + todt1);
            todate = DateUtil.convertDateToStringDate(todt1, datepattern);
          }
          else
          {
            todate = DateUtil.convertDateToStringDate(new Date(), datepattern);
          }
          sql = sql + " and DATE_FORMAT( ja.targerofferdate, '%M %e, %Y')  >= " + "'" + fromdate + "'" + " and DATE_FORMAT( ja.targerofferdate, '%M %e, %Y')  <= " + "'" + todate + "'";
        }
      }
      if (criteria.getAppliedcri().equals(Common.OFFER_RELEASED_DATE))
      {
        logger.info("inside offer released date");
        if (((StringUtils.isNullOrEmpty(fromdate)) || (fromdate.equals("null"))) && (StringUtils.isNullOrEmpty(todate)) && (todate.equals("null")))
        {
          logger.info("fromdate and todate is null" + fromdate + todate);
        }
        else
        {
          if ((!StringUtils.isNullOrEmpty(fromdate)) && (!fromdate.equals("null")))
          {
            Date fromdt1 = DateUtil.convertStringDateToDate(fromdate, datepatternuser);
            
            logger.info("fromdt1" + fromdt1);
            fromdate = DateUtil.convertDateToStringDate(fromdt1, datepattern);
          }
          else
          {
            fromdate = DateUtil.convertDateToStringDate(new Date(), datepattern);
          }
          if ((!StringUtils.isNullOrEmpty(todate)) && (!todate.equals("null")))
          {
            Date todt1 = DateUtil.convertStringDateToDate(todate, datepatternuser);
            
            logger.info("todt1" + todt1);
            todate = DateUtil.convertDateToStringDate(todt1, datepattern);
          }
          else
          {
            todate = DateUtil.convertDateToStringDate(new Date(), datepattern);
          }
          sql = sql + " and DATE_FORMAT( ja.offerreleasedate, '%M %e, %Y')  >= " + "'" + fromdate + "'" + " and DATE_FORMAT( ja.offerreleasedate, '%M %e, %Y')  <= " + "'" + todate + "'";
        }
      }
      if (criteria.getAppliedcri().equals(Common.ON_BOARDED_DATE))
      {
        logger.info("inside on boarded date");
        if (((StringUtils.isNullOrEmpty(fromdate)) || (fromdate.equals("null"))) && (StringUtils.isNullOrEmpty(todate)) && (todate.equals("null")))
        {
          logger.info("fromdate and todate is null" + fromdate + todate);
        }
        else
        {
          if ((!StringUtils.isNullOrEmpty(fromdate)) && (!fromdate.equals("null")))
          {
            Date fromdt1 = DateUtil.convertStringDateToDate(fromdate, datepatternuser);
            
            logger.info("fromdt1" + fromdt1);
            fromdate = DateUtil.convertDateToStringDate(fromdt1, datepattern);
          }
          else
          {
            fromdate = DateUtil.convertDateToStringDate(new Date(), datepattern);
          }
          if ((!StringUtils.isNullOrEmpty(todate)) && (!todate.equals("null")))
          {
            Date todt1 = DateUtil.convertStringDateToDate(todate, datepatternuser);
            
            logger.info("todt1" + todt1);
            todate = DateUtil.convertDateToStringDate(todt1, datepattern);
          }
          else
          {
            todate = DateUtil.convertDateToStringDate(new Date(), datepattern);
          }
          sql = sql + " and DATE_FORMAT( ja.joined_date, '%M %e, %Y')  >= " + "'" + fromdate + "'" + " and DATE_FORMAT( ja.joined_date, '%M %e, %Y')  <= " + "'" + todate + "'";
        }
      }
    }
    if (((!StringUtils.isNullOrEmpty(criteria.getScreenName())) && ((criteria.getScreenName().equals("ALL_APP_SEARCH_SCREEN")) || (criteria.getScreenName().equals("APP_SEARCH_SCREEN_HIRING_MGR")))) || (criteria.getScreenName().equals("APP_SEARCH_SCREEN_RECRUITER")) || (criteria.getScreenName().equals("ON_BOARDING_SEARCH_SCREEN")))
    {
      if ((!StringUtils.isNullOrEmpty(criteria.getInterviewstate())) && (!criteria.getInterviewstate().equals("NoValue")) && (!criteria.getInterviewstate().equals("null")))
      {
        if (criteria.getInterviewstate().startsWith("Rejected")) {
          sql = sql + " and ja.status='R'  ";
        } else if (criteria.getInterviewstate().startsWith("OnHold")) {
          sql = sql + " and ja.status='H'  ";
        } else if (criteria.getInterviewstate().startsWith("Mark deleted")) {
          sql = sql + " and ja.status='D'  ";
        } else {
          sql = sql + " and ja.status='A'  ";
        }
      }
      else {
        sql = sql + " and ja.status='A'  ";
      }
    }
    else if ((!StringUtils.isNullOrEmpty(criteria.getInterviewstate())) && (!criteria.getInterviewstate().equals("NoValue")) && (!criteria.getInterviewstate().equals("null")))
    {
      if (criteria.getInterviewstate().startsWith("Rejected")) {
        sql = sql + " and ja.status='R' and u.user_id = ja.owner ";
      } else if (criteria.getInterviewstate().startsWith("OnHold")) {
        sql = sql + " and ja.status='H' and u.user_id = ja.owner ";
      } else if (criteria.getInterviewstate().startsWith("Mark deleted")) {
        sql = sql + " and ja.status='D' and u.user_id = ja.owner ";
      } else {
        sql = sql + " and ja.status='A' and u.user_id = ja.owner ";
      }
    }
    else {
      sql = sql + " and ja.status='A' and u.user_id = ja.owner ";
    }
    return sql;
  }
  
  public static String buildCommaseparated(List<String> lst)
  {
    String str = "";
    for (int i = 0; i < lst.size(); i++) {
      str = str + "'" + (String)lst.get(i) + "'" + ",";
    }
    if (!StringUtils.isNullOrEmpty(str)) {
      str = str.substring(0, str.length() - 1);
    }
    return str;
  }
  
  public static String buildCommaseparatedLong(List<Long> lst)
  {
    String str = "";
    for (int i = 0; i < lst.size(); i++) {
      str = str + lst.get(i) + ",";
    }
    if (!StringUtils.isNullOrEmpty(str)) {
      str = str.substring(0, str.length() - 1);
    }
    return str;
  }
  
  private static String buildInterviewStates(List<String> lst)
  {
    String str = " and ( ";
    for (int i = 0; i < lst.size(); i++) {
      str = str + "  ja.interview_state like '%" + (String)lst.get(i) + "%' OR";
    }
    str = str.substring(0, str.length() - 2) + ")";
    
    return str;
  }
}
