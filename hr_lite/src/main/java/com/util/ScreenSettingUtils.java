package com.util;

import com.bean.AgencyRedemption;
import com.bean.JobApplicant;
import com.bean.ReferralRedemption;
import com.bean.User;
import com.bean.screensetting.ApplicantScreenSettings;
import com.dao.ScreenSettingsDAO;
import com.resources.Constant;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.CellStyle;

public class ScreenSettingUtils
{
  protected static final Logger logger = Logger.getLogger(ScreenSettingUtils.class);
  
  public static String getApplicationScreenSettings(User user1, String screenName)
  {
    logger.info("inside getApplicationScreenSettings screenName" + screenName);
    

    String strcontents = "";
    try
    {
      ApplicantScreenSettings appsettings = ScreenSettingsDAO.getApplicationScreenSettings(user1.getUserId(), screenName);
      if (appsettings == null)
      {
        if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("APP_SEARCH_SCREEN_HIRING_MGR")))
        {
          String strtemp = Constant.getValue("applicant.columns.hrmgr.firstpage");
          String[] columns = StringUtils.tokenize(strtemp, ",");
          for (int i = 0; i < columns.length; i++)
          {
            String resoucekey = "aquisition.applicant.configutaion." + columns[i];
            if (columns[i].equals("ownername")) {
              strcontents = strcontents + "{key:\"ownername\", label:\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:false,formatter:formatApplicantOwner,resizeable:true},";
            } else if (columns[i].equals("offerownerName")) {
              strcontents = strcontents + "{key:\"offerownerName\", label:\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:true,formatter:formatUrlofferowner,resizeable:true},";
            } else if (columns[i].equals("recruiter")) {
              strcontents = strcontents + "{key:\"recruiter\", label:\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:true,formatter:formatUrlForRecruiter,resizeable:true},";
            } else if (columns[i].equals("jobTitle")) {
              strcontents = strcontents + "{key:\"jobTitle\", label:\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:true,formatter:formatUrljobreq,resizeable:true},";
            } else {
              strcontents = strcontents + "{key:\"" + columns[i] + "\"" + ", label:" + "\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:true, resizeable:true},";
            }
          }
        }
        if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("USER_SCREEN")))
        {
          String strtemp = Constant.getValue("user.page.default.columns.firstpage");
          String[] columns = StringUtils.tokenize(strtemp, ",");
          for (int i = 0; i < columns.length; i++)
          {
            String resoucekey = "user.page.table.configutaion." + columns[i];
            if (columns[i].equals("departmentName")) {
              strcontents = strcontents + "{key:\"departmentName\", label:\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:false,resizeable:true},";
            } else if (columns[i].equals("roleValue")) {
              strcontents = strcontents + "{key:\"roleValue\", label:\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:false,resizeable:true},";
            } else if (columns[i].equals("orgName")) {
              strcontents = strcontents + "{key:\"orgName\", label:\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:false,resizeable:true},";
            } else if (columns[i].equals("designationValue")) {
              strcontents = strcontents + "{key:\"designationValue\", label:\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:false,resizeable:true},";
            } else if (columns[i].equals("localeValue")) {
              strcontents = strcontents + "{key:\"localeValue\", label:\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:false,resizeable:true},";
            } else if (columns[i].equals("projectcodeName")) {
              strcontents = strcontents + "{key:\"projectcodeName\", label:\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:false,resizeable:true},";
            } else {
              strcontents = strcontents + "{key:\"" + columns[i] + "\"" + ", label:" + "\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:false, resizeable:true},";
            }
          }
        }
        if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("VENDOR_SCREEN")))
        {
          String strtemp = Constant.getValue("vendor.page.default.columns.firstpage");
          String[] columns = StringUtils.tokenize(strtemp, ",");
          for (int i = 0; i < columns.length; i++)
          {
            String resoucekey = "vendor.page.table.configutaion." + columns[i];
            if (columns[i].equals("stateValue")) {
              strcontents = strcontents + "{key:\"stateValue\", label:\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:false,resizeable:true},";
            } else if (columns[i].equals("countryValue")) {
              strcontents = strcontents + "{key:\"countryValue\", label:\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:false,resizeable:true},";
            } else {
              strcontents = strcontents + "{key:\"" + columns[i] + "\"" + ", label:" + "\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:true, resizeable:true},";
            }
          }
        }
        if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("REFERRAL_SCREEN")))
        {
          String strtemp = Constant.getValue("referral.page.default.columns");
          String[] columns = StringUtils.tokenize(strtemp, ",");
          for (int i = 0; i < columns.length; i++)
          {
            String resoucekey = "referral.page.table.configutaion." + columns[i];
            

            strcontents = strcontents + "{key:\"" + columns[i] + "\"" + ", label:" + "\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:true, resizeable:true},";
          }
        }
        if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("APP_SEARCH_SCREEN_RECRUITER")))
        {
          String strtemp = Constant.getValue("applicant.columns.recruiter.firstpage");
          String[] columns = StringUtils.tokenize(strtemp, ",");
          for (int i = 0; i < columns.length; i++)
          {
            String resoucekey = "aquisition.applicant.configutaion." + columns[i];
            if (columns[i].equals("ownername")) {
              strcontents = strcontents + "{key:\"ownername\", label:\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:false,formatter:formatApplicantOwner,resizeable:true},";
            } else if (columns[i].equals("offerownerName")) {
              strcontents = strcontents + "{key:\"offerownerName\", label:\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:true,formatter:formatUrlofferowner,resizeable:true},";
            } else if (columns[i].equals("recruiter")) {
              strcontents = strcontents + "{key:\"recruiter\", label:\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:true,formatter:formatUrlForRecruiter,resizeable:true},";
            } else if (columns[i].equals("jobTitle")) {
              strcontents = strcontents + "{key:\"jobTitle\", label:\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:true,formatter:formatUrljobreq,resizeable:true},";
            } else {
              strcontents = strcontents + "{key:\"" + columns[i] + "\"" + ", label:" + "\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:true, resizeable:true},";
            }
          }
        }
        if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("APPLICANT_SUMMARY_SCREEN")))
        {
          String strtemp = Constant.getValue("applicant.columns.applicant.summary.firstpage");
          String[] columns = StringUtils.tokenize(strtemp, ",");
          for (int i = 0; i < columns.length; i++)
          {
            String resoucekey = "aquisition.applicant.configutaion." + columns[i];
            if (columns[i].equals("ownername")) {
              strcontents = strcontents + "{key:\"ownername\", label:\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:false,formatter:formatApplicantOwner,resizeable:true},";
            } else if (columns[i].equals("offerownerName")) {
              strcontents = strcontents + "{key:\"offerownerName\", label:\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:true,formatter:formatUrlofferowner,resizeable:true},";
            } else if (columns[i].equals("recruiter")) {
              strcontents = strcontents + "{key:\"recruiter\", label:\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:true,formatter:formatUrlForRecruiter,resizeable:true},";
            } else if (columns[i].equals("jobTitle")) {
              strcontents = strcontents + "{key:\"jobTitle\", label:\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:true,formatter:formatUrljobreq,resizeable:true},";
            } else {
              strcontents = strcontents + "{key:\"" + columns[i] + "\"" + ", label:" + "\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:true, resizeable:true},";
            }
          }
        }
        if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("ALL_APP_SEARCH_SCREEN")))
        {
          String strtemp = Constant.getValue("applicant.columns.all.firstpage");
          String[] columns = StringUtils.tokenize(strtemp, ",");
          for (int i = 0; i < columns.length; i++)
          {
            String resoucekey = "aquisition.applicant.configutaion." + columns[i];
            if (columns[i].equals("ownername")) {
              strcontents = strcontents + "{key:\"ownername\", label:\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:false,formatter:formatApplicantOwner,resizeable:true},";
            } else if (columns[i].equals("jobTitle")) {
              strcontents = strcontents + "{key:\"jobTitle\", label:\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:true,formatter:formatUrljobreq,resizeable:true},";
            } else if (columns[i].equals("recruiter")) {
              strcontents = strcontents + "{key:\"recruiter\", label:\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:true,formatter:formatUrlForRecruiter,resizeable:true},";
            } else if (columns[i].equals("offerownerName")) {
              strcontents = strcontents + "{key:\"offerownerName\", label:\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:true,formatter:formatUrlofferowner,resizeable:true},";
            } else {
              strcontents = strcontents + "{key:\"" + columns[i] + "\"" + ", label:" + "\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:true, resizeable:true},";
            }
          }
        }
        if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("DASHBOARD_RECRUITER_ASSIGNED_REQUISTIONS")))
        {
          String strtemp = Constant.getValue("dashboard.recruiter.assigned.requistions.firstpage");
          String[] columns = StringUtils.tokenize(strtemp, ",");
          for (int i = 0; i < columns.length; i++)
          {
            String resoucekey = "requisition.page.table.configutaion." + columns[i];
            if (columns[i].equals("currentOwnerName")) {
              strcontents = strcontents + "{key:\"currentOwnerName\", label:\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:false,formatter:formatOwner,resizeable:true},";
            } else if (columns[i].equals("hiringMgrValue")) {
              strcontents = strcontents + "{key:\"hiringMgrValue\", label:\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:false,formatter:formatHiringMgr,resizeable:true},";
            } else {
              strcontents = strcontents + "{key:\"" + columns[i] + "\"" + ", label:" + "\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:false, resizeable:true},";
            }
          }
        }
        if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("DASHBOARD_RECRUITER_IN_OFFER_PROCESS")))
        {
          String strtemp = Constant.getValue("dashboard.recruiter.applicants.offer.process.firstpage");
          String[] columns = StringUtils.tokenize(strtemp, ",");
          for (int i = 0; i < columns.length; i++)
          {
            String resoucekey = "aquisition.applicant.configutaion." + columns[i];
            if (columns[i].equals("ownername")) {
              strcontents = strcontents + "{key:\"ownername\", label:\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:false,formatter:formatApplicantOwner,resizeable:true},";
            } else if (columns[i].equals("jobTitle")) {
              strcontents = strcontents + "{key:\"jobTitle\", label:\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:true,formatter:formatUrljobreq,resizeable:true},";
            } else if (columns[i].equals("recruiter")) {
              strcontents = strcontents + "{key:\"recruiter\", label:\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:true,formatter:formatUrlForRecruiter,resizeable:true},";
            } else if (columns[i].equals("offerownerName")) {
              strcontents = strcontents + "{key:\"offerownerName\", label:\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:true,formatter:formatUrlofferowner,resizeable:true},";
            } else {
              strcontents = strcontents + "{key:\"" + columns[i] + "\"" + ", label:" + "\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:true, resizeable:true},";
            }
          }
        }
        if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("DASHBOARD_RECRUITER_IN_JOINED_PROCESS")))
        {
          String strtemp = Constant.getValue("dashboard.recruiter.applicants.joining.process.firstpage");
          String[] columns = StringUtils.tokenize(strtemp, ",");
          for (int i = 0; i < columns.length; i++)
          {
            String resoucekey = "aquisition.applicant.configutaion." + columns[i];
            if (columns[i].equals("ownername")) {
              strcontents = strcontents + "{key:\"ownername\", label:\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:false,formatter:formatApplicantOwner,resizeable:true},";
            } else if (columns[i].equals("jobTitle")) {
              strcontents = strcontents + "{key:\"jobTitle\", label:\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:true,formatter:formatUrljobreq,resizeable:true},";
            } else if (columns[i].equals("recruiter")) {
              strcontents = strcontents + "{key:\"recruiter\", label:\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:true,formatter:formatUrlForRecruiter,resizeable:true},";
            } else if (columns[i].equals("offerownerName")) {
              strcontents = strcontents + "{key:\"offerownerName\", label:\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:true,formatter:formatUrlofferowner,resizeable:true},";
            } else {
              strcontents = strcontents + "{key:\"" + columns[i] + "\"" + ", label:" + "\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:true, resizeable:true},";
            }
          }
        }
        if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("ON_BOARDING_SEARCH_SCREEN")))
        {
          String strtemp = Constant.getValue("applicant.columns.onboard.firstpage");
          String[] columns = StringUtils.tokenize(strtemp, ",");
          for (int i = 0; i < columns.length; i++)
          {
            String resoucekey = "aquisition.applicant.configutaion." + columns[i];
            if (columns[i].equals("ownername")) {
              strcontents = strcontents + "{key:\"ownername\", label:\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:false,formatter:formatApplicantOwner,resizeable:true},";
            } else if (columns[i].equals("jobTitle")) {
              strcontents = strcontents + "{key:\"jobTitle\", label:\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:true,formatter:formatUrljobreq,resizeable:true},";
            } else if (columns[i].equals("recruiter")) {
              strcontents = strcontents + "{key:\"recruiter\", label:\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:true,formatter:formatUrlForRecruiter,resizeable:true},";
            } else if (columns[i].equals("offerownerName")) {
              strcontents = strcontents + "{key:\"offerownerName\", label:\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:true,formatter:formatUrlofferowner,resizeable:true},";
            } else {
              strcontents = strcontents + "{key:\"" + columns[i] + "\"" + ", label:" + "\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:true, resizeable:true},";
            }
          }
        }
        if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("ONBOARD_TASK_DEF_SCREEN")))
        {
          String strtemp = Constant.getValue("onboarding.taskdef.default.columns");
          String[] columns = StringUtils.tokenize(strtemp, ",");
          for (int i = 0; i < columns.length; i++)
          {
            String resoucekey = "onboarding.taskdef.page.table.configuration." + columns[i];
            if (columns[i].equals("primaryOwnerName")) {
              strcontents = strcontents + "{key:\"primaryOwnerName\", label:\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:false,formatter:formatPrimaryOwner,resizeable:true},";
            } else {
              strcontents = strcontents + "{key:\"" + columns[i] + "\"" + ", label:" + "\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:true, resizeable:true},";
            }
          }
        }
        if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("ALL_REQUISION_SCREEN")))
        {
          String strtemp = Constant.getValue("requisition.page.default.columns.firstpage");
          String[] columns = StringUtils.tokenize(strtemp, ",");
          for (int i = 0; i < columns.length; i++)
          {
            String resoucekey = "requisition.page.table.configutaion." + columns[i];
            if (columns[i].equals("currentOwnerName")) {
              strcontents = strcontents + "{key:\"currentOwnerName\", label:\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:false,formatter:formatOwner,resizeable:true},";
            } else {
              strcontents = strcontents + "{key:\"" + columns[i] + "\"" + ", label:" + "\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:false, resizeable:true},";
            }
          }
        }
        if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("MY_REQUISITION_SCREEN")))
        {
          String strtemp = Constant.getValue("myrequisition.page.default.columns.firstpage");
          String[] columns = StringUtils.tokenize(strtemp, ",");
          for (int i = 0; i < columns.length; i++)
          {
            String resoucekey = "requisition.page.table.configutaion." + columns[i];
            if (columns[i].equals("currentOwnerName")) {
              strcontents = strcontents + "{key:\"currentOwnerName\", label:\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:false,formatter:formatOwner,resizeable:true},";
            } else {
              strcontents = strcontents + "{key:\"" + columns[i] + "\"" + ", label:" + "\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:false, resizeable:true},";
            }
          }
        }
        if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("TALENT_POOL_SCREEN")))
        {
          String strtemp = Constant.getValue("talentpool.page.default.columns");
          String[] columns = StringUtils.tokenize(strtemp, ",");
          for (int i = 0; i < columns.length; i++)
          {
            String resoucekey = "talentpool.page.table.configuration." + columns[i];
            if (columns[i].equals("orgName")) {
              strcontents = strcontents + "{key:\"orgName\", label:\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:false,resizeable:true},";
            } else {
              strcontents = strcontents + "{key:\"" + columns[i] + "\"" + ", label:" + "\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:true, resizeable:true},";
            }
          }
        }
        if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("AGENCY_REDEMPTION__SCREEN")))
        {
          String strtemp = Constant.getValue("agencyredumption.default.columns");
          String[] columns = StringUtils.tokenize(strtemp, ",");
          for (int i = 0; i < columns.length; i++)
          {
            String resoucekey = "agencyredumption.page.table.configuration." + columns[i];
            if (columns[i].equals("isPaid")) {
              strcontents = strcontents + "{key:\"isPaid\", label:\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",editor: new YAHOO.widget.RadioCellEditor({radioOptions:['Y','N'],disableBtns:true,asyncSubmitter: submitter}),sortable:false,resizeable:true},";
            } else {
              strcontents = strcontents + "{key:\"" + columns[i] + "\"" + ", label:" + "\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:true, resizeable:true},";
            }
          }
        }
        if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("REFERRAL_REDEMPTION_SCREEN")))
        {
          String strtemp = Constant.getValue("referralredumption.default.columns");
          String[] columns = StringUtils.tokenize(strtemp, ",");
          for (int i = 0; i < columns.length; i++)
          {
            String resoucekey = "referralredumption.page.table.configuration." + columns[i];
            if (columns[i].equals("isPaid")) {
              strcontents = strcontents + "{key:\"isPaid\", label:\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",editor: new YAHOO.widget.RadioCellEditor({radioOptions:['Y','N'],disableBtns:true,asyncSubmitter: submitter}),sortable:false,resizeable:true},";
            } else {
              strcontents = strcontents + "{key:\"" + columns[i] + "\"" + ", label:" + "\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:true, resizeable:true},";
            }
          }
        }
        if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("REQUISION_TEMPLATE_SCREEN")))
        {
          String strtemp = Constant.getValue("requisition.template.default.columns");
          String[] columns = StringUtils.tokenize(strtemp, ",");
          for (int i = 0; i < columns.length; i++)
          {
            String resoucekey = "requisition.template.table.configutaion." + columns[i];
            

            strcontents = strcontents + "{key:\"" + columns[i] + "\"" + ", label:" + "\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:false, resizeable:true},";
          }
        }
      }
      else if (((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("ALL_REQUISION_SCREEN"))) || (screenName.equals("MY_REQUISITION_SCREEN")) || (screenName.equals("DASHBOARD_RECRUITER_ASSIGNED_REQUISTIONS")) || (screenName.equals("TALENT_POOL_SCREEN")) || (screenName.equals("ONBOARD_TASK_DEF_SCREEN")) || (screenName.equals("AGENCY_REDEMPTION__SCREEN")) || (screenName.equals("REFERRAL_REDEMPTION_SCREEN")) || (screenName.equals("USER_SCREEN")) || (screenName.equals("VENDOR_SCREEN")) || (screenName.equals("REFERRAL_SCREEN")) || (screenName.equals("REQUISION_TEMPLATE_SCREEN")))
      {
        if (((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("ALL_REQUISION_SCREEN"))) || (screenName.equals("DASHBOARD_RECRUITER_ASSIGNED_REQUISTIONS")) || (screenName.equals("MY_REQUISITION_SCREEN")))
        {
          String[] columns = StringUtils.tokenize(appsettings.getToList(), ",");
          for (int i = 0; i < columns.length; i++)
          {
            String resoucekey = "requisition.page.table.configutaion." + columns[i];
            if (columns[i].equals("currentOwnerName")) {
              strcontents = strcontents + "{key:\"currentOwnerName\", label:\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:false,formatter:formatOwner,resizeable:true},";
            } else {
              strcontents = strcontents + "{key:\"" + columns[i] + "\"" + ", label:" + "\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:false, resizeable:true},";
            }
          }
        }
        if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("REQUISION_TEMPLATE_SCREEN")))
        {
          String strtemp = Constant.getValue("requisition.template.default.columns");
          String[] columns = StringUtils.tokenize(appsettings.getToList(), ",");
          for (int i = 0; i < columns.length; i++)
          {
            String resoucekey = "requisition.template.table.configutaion." + columns[i];
            

            strcontents = strcontents + "{key:\"" + columns[i] + "\"" + ", label:" + "\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:false, resizeable:true},";
          }
        }
        if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("USER_SCREEN")))
        {
          String[] columns = StringUtils.tokenize(appsettings.getToList(), ",");
          for (int i = 0; i < columns.length; i++)
          {
            String resoucekey = "user.page.table.configutaion." + columns[i];
            if (columns[i].equals("departmentName")) {
              strcontents = strcontents + "{key:\"departmentName\", label:\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:false,resizeable:true},";
            } else if (columns[i].equals("roleValue")) {
              strcontents = strcontents + "{key:\"roleValue\", label:\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:false,resizeable:true},";
            } else if (columns[i].equals("orgName")) {
              strcontents = strcontents + "{key:\"orgName\", label:\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:false,resizeable:true},";
            } else if (columns[i].equals("designationValue")) {
              strcontents = strcontents + "{key:\"designationValue\", label:\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:false,resizeable:true},";
            } else if (columns[i].equals("localeValue")) {
              strcontents = strcontents + "{key:\"localeValue\", label:\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:false,resizeable:true},";
            } else if (columns[i].equals("projectcodeName")) {
              strcontents = strcontents + "{key:\"projectcodeName\", label:\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:false,resizeable:true},";
            } else {
              strcontents = strcontents + "{key:\"" + columns[i] + "\"" + ", label:" + "\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:true, resizeable:true},";
            }
          }
        }
        if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("VENDOR_SCREEN")))
        {
          String[] columns = StringUtils.tokenize(appsettings.getToList(), ",");
          for (int i = 0; i < columns.length; i++)
          {
            String resoucekey = "vendor.page.table.configutaion." + columns[i];
            if (columns[i].equals("stateValue")) {
              strcontents = strcontents + "{key:\"stateValue\", label:\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:false,resizeable:true},";
            } else if (columns[i].equals("countryValue")) {
              strcontents = strcontents + "{key:\"countryValue\", label:\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:false,resizeable:true},";
            } else {
              strcontents = strcontents + "{key:\"" + columns[i] + "\"" + ", label:" + "\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:true, resizeable:true},";
            }
          }
        }
        if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("REFERRAL_SCREEN")))
        {
          String[] columns = StringUtils.tokenize(appsettings.getToList(), ",");
          if (columns != null) {
            for (int i = 0; i < columns.length; i++)
            {
              String resoucekey = "referral.page.table.configutaion." + columns[i];
              strcontents = strcontents + "{key:\"" + columns[i] + "\"" + ", label:" + "\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:true, resizeable:true},";
            }
          }
        }
        if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("TALENT_POOL_SCREEN")))
        {
          String[] columns = StringUtils.tokenize(appsettings.getToList(), ",");
          for (int i = 0; i < columns.length; i++)
          {
            String resoucekey = "talentpool.page.table.configuration." + columns[i];
            if (columns[i].equals("orgName")) {
              strcontents = strcontents + "{key:\"orgName\", label:\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:false,resizeable:true},";
            } else {
              strcontents = strcontents + "{key:\"" + columns[i] + "\"" + ", label:" + "\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:true, resizeable:true},";
            }
          }
        }
        if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("ONBOARD_TASK_DEF_SCREEN")))
        {
          String[] columns = StringUtils.tokenize(appsettings.getToList(), ",");
          for (int i = 0; i < columns.length; i++)
          {
            String resoucekey = "onboarding.taskdef.page.table.configuration." + columns[i];
            if (columns[i].equals("primaryOwnerName")) {
              strcontents = strcontents + "{key:\"primaryOwnerName\", label:\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:false,formatter:formatPrimaryOwner,resizeable:true},";
            } else {
              strcontents = strcontents + "{key:\"" + columns[i] + "\"" + ", label:" + "\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:true, resizeable:true},";
            }
          }
        }
        if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("AGENCY_REDEMPTION__SCREEN")))
        {
          String[] columns = StringUtils.tokenize(appsettings.getToList(), ",");
          for (int i = 0; i < columns.length; i++)
          {
            String resoucekey = "agencyredumption.page.table.configuration." + columns[i];
            
            strcontents = strcontents + "{key:\"" + columns[i] + "\"" + ", label:" + "\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:true, resizeable:true},";
          }
        }
        if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("REFERRAL_REDEMPTION_SCREEN")))
        {
          String[] columns = StringUtils.tokenize(appsettings.getToList(), ",");
          for (int i = 0; i < columns.length; i++)
          {
            String resoucekey = "referralredumption.page.table.configuration." + columns[i];
            
            strcontents = strcontents + "{key:\"" + columns[i] + "\"" + ", label:" + "\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:true, resizeable:true},";
          }
        }
      }
      else
      {
        String[] columns = StringUtils.tokenize(appsettings.getToList(), ",");
        if (((!StringUtils.isNullOrEmpty(screenName)) && ((screenName.equals("ALL_APP_SEARCH_SCREEN")) || (screenName.equals("APP_SEARCH_SCREEN_HIRING_MGR")))) || (screenName.equals("APP_SEARCH_SCREEN_RECRUITER")) || (screenName.equals("APPLICANT_SUMMARY_SCREEN")) || (screenName.equals("ON_BOARDING_SEARCH_SCREEN"))) {
          for (int i = 0; i < columns.length; i++)
          {
            String resoucekey = "aquisition.applicant.configutaion." + columns[i];
            if (columns[i].equals("ownername")) {
              strcontents = strcontents + "{key:\"ownername\", label:\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:false,formatter:formatApplicantOwner,resizeable:true},";
            } else if (columns[i].equals("jobTitle")) {
              strcontents = strcontents + "{key:\"jobTitle\", label:\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:true,formatter:formatUrljobreq,resizeable:true},";
            } else if (columns[i].equals("recruiter")) {
              strcontents = strcontents + "{key:\"recruiter\", label:\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:true,formatter:formatUrlForRecruiter,resizeable:true},";
            } else if (columns[i].equals("offerownerName")) {
              strcontents = strcontents + "{key:\"offerownerName\", label:\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:true,formatter:formatUrlofferowner,resizeable:true},";
            } else {
              strcontents = strcontents + "{key:\"" + columns[i] + "\"" + ", label:" + "\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:true, resizeable:true},";
            }
          }
        } else {
          for (int i = 0; i < columns.length; i++)
          {
            String resoucekey = "aquisition.applicant.configutaion." + columns[i];
            if (columns[i].equals("ownername")) {
              strcontents = strcontents + "{key:\"ownername\", label:\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:false,resizeable:true},";
            } else if (columns[i].equals("jobTitle")) {
              strcontents = strcontents + "{key:\"jobTitle\", label:\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:true,formatter:formatUrljobreq,resizeable:true},";
            } else if (columns[i].equals("offerownerName")) {
              strcontents = strcontents + "{key:\"offerownerName\", label:\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:true,formatter:formatUrlofferowner,resizeable:true},";
            } else {
              strcontents = strcontents + "{key:\"" + columns[i] + "\"" + ", label:" + "\"" + Constant.getResourceStringValue(resoucekey, user1.getLocale()) + "\"" + ",sortable:true, resizeable:true},";
            }
          }
        }
      }
    }
    catch (Exception e)
    {
      logger.info("exception getApplicationScreenSettings" + e.getMessage());
      e.printStackTrace();
    }
    logger.info(strcontents);
    return strcontents;
  }
  
  public static String[] getArrayOfApplicationScreenSettingsKeyListPage(String screenName)
  {
    logger.info("inside getArrayOfApplicationScreenSettingsKeyListPage");
    String[] fields = null;
    String[] rfields = null;
    List clist = new ArrayList();
    clist.add("applicantId");clist.add("fullName");clist.add("uuid");clist.add("reqId");clist.add("offerownerId");clist.add("isofferownerGroup");
    clist.add("ownernamegroup");clist.add("ownerId");clist.add("ownergroupId");clist.add("isGroup");clist.add("recruiterId");clist.add("recruitergroup");clist.add("filterError");
    clist.add("juuid");
    List reqlist = new ArrayList();
    List reqtempllist = new ArrayList();
    reqtempllist.add("templateId");
    reqtempllist.add("templateName");
    reqlist.add("allApplicantViewed");
    reqlist.add("jobreqId");reqlist.add("jobreqName");reqlist.add("templateId");reqlist.add("currentOwnerId");reqlist.add("isGroup");reqlist.add("uuid");
    if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("APP_SEARCH_SCREEN_HIRING_MGR")))
    {
      String strtemp = Constant.getValue("applicant.page.hiring.mgr.default.columns");
      String[] columns = StringUtils.tokenize(strtemp, ",");
      for (int i = 0; i < columns.length; i++) {
        clist.add(columns[i]);
      }
      fields = new String[clist.size()];
      clist.toArray(fields);
      logger.info(fields.toString());
    }
    if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("APP_SEARCH_SCREEN_RECRUITER")))
    {
      String strtemp = Constant.getValue("applicant.page.recruiter.default.columns");
      String[] columns = StringUtils.tokenize(strtemp, ",");
      for (int i = 0; i < columns.length; i++) {
        clist.add(columns[i]);
      }
      fields = new String[clist.size()];
      clist.toArray(fields);
      logger.info(fields);
    }
    if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("APPLICANT_SUMMARY_SCREEN")))
    {
      String strtemp = Constant.getValue("applicant.page.applicant.summary.default.columns");
      String[] columns = StringUtils.tokenize(strtemp, ",");
      for (int i = 0; i < columns.length; i++) {
        clist.add(columns[i]);
      }
      fields = new String[clist.size()];
      clist.toArray(fields);
      logger.info(fields);
    }
    if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("ALL_APP_SEARCH_SCREEN")))
    {
      String strtemp = Constant.getValue("applicant.page.all.default.columns");
      String[] columns = StringUtils.tokenize(strtemp, ",");
      for (int i = 0; i < columns.length; i++) {
        clist.add(columns[i]);
      }
      fields = new String[clist.size()];
      clist.toArray(fields);
      logger.info(fields);
    }
    if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("ON_BOARDING_SEARCH_SCREEN")))
    {
      String strtemp = Constant.getValue("onboarding.page.default.columns");
      String[] columns = StringUtils.tokenize(strtemp, ",");
      for (int i = 0; i < columns.length; i++) {
        clist.add(columns[i]);
      }
      fields = new String[clist.size()];
      clist.toArray(fields);
      logger.info(fields);
    }
    if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("DASHBOARD_RECRUITER_ASSIGNED_REQUISTIONS")))
    {
      String strtemp = Constant.getValue("dashboard.recruiter.assigned.requistions");
      String[] columns = StringUtils.tokenize(strtemp, ",");
      reqlist.add("hiringMgrIdValue");
      for (int i = 0; i < columns.length; i++) {
        reqlist.add(columns[i]);
      }
      fields = new String[reqlist.size()];
      reqlist.toArray(fields);
      
      logger.info(fields);
    }
    if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("MY_REQUISITION_SCREEN")))
    {
      String strtemp = Constant.getValue("myrequisition.page.default.columns");
      String[] columns = StringUtils.tokenize(strtemp, ",");
      for (int i = 0; i < columns.length; i++) {
        reqlist.add(columns[i]);
      }
      fields = new String[reqlist.size()];
      reqlist.toArray(fields);
      logger.info(fields);
    }
    if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("ALL_REQUISION_SCREEN")))
    {
      String strtemp = Constant.getValue("requisition.page.default.columns");
      String[] columns = StringUtils.tokenize(strtemp, ",");
      for (int i = 0; i < columns.length; i++) {
        reqlist.add(columns[i]);
      }
      fields = new String[reqlist.size()];
      reqlist.toArray(fields);
      logger.info(fields);
    }
    if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("REQUISION_TEMPLATE_SCREEN")))
    {
      String strtemp = Constant.getValue("requisition.template.default.columns");
      String[] columns = StringUtils.tokenize(strtemp, ",");
      for (int i = 0; i < columns.length; i++) {
        reqtempllist.add(columns[i]);
      }
      reqtempllist.add("status");
      fields = new String[reqtempllist.size()];
      reqtempllist.toArray(fields);
      logger.info(fields);
    }
    if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("DASHBOARD_RECRUITER_IN_OFFER_PROCESS")))
    {
      String strtemp = Constant.getValue("dashboard.recruiter.applicants.offer.process");
      String[] columns = StringUtils.tokenize(strtemp, ",");
      for (int i = 0; i < columns.length; i++) {
        clist.add(columns[i]);
      }
      fields = new String[clist.size()];
      clist.toArray(fields);
      logger.info(fields);
    }
    if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("DASHBOARD_RECRUITER_IN_JOINED_PROCESS")))
    {
      String strtemp = Constant.getValue("dashboard.recruiter.applicants.joining.process");
      String[] columns = StringUtils.tokenize(strtemp, ",");
      for (int i = 0; i < columns.length; i++) {
        clist.add(columns[i]);
      }
      fields = new String[clist.size()];
      clist.toArray(fields);
      logger.info(fields);
    }
    return fields;
  }
  
  public static String getApplicationScreenSettingsKeys(String screenName)
  {
    String strcontents = " {key:\"applicantId\"},\n{key:\"fullName\"},\n{key:\"offerownerId\"},\n{key:\"isofferownerGroup\"},\n{key:\"ownerId\"},\n{key:\"ownergroupId\"},\n{key:\"ownernamegroup\"},\n{key:\"isGroup\"},\n{key:\"recruitergroup\"},\n{key:\"recruiterId\"},\n{key:\"filterError\"},\n{key:\"juuid\"},\n";
    


    String strcontentsrequistion = " {key:\"jobreqId\"},\n{key:\"jobreqName\"},\n{key:\"uuid\"},\n";
    if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("APP_SEARCH_SCREEN_HIRING_MGR")))
    {
      String strtemp = Constant.getValue("applicant.page.hiring.mgr.default.columns");
      String[] columns = StringUtils.tokenize(strtemp, ",");
      for (int i = 0; i < columns.length; i++) {
        strcontents = strcontents + "{key:" + "\"" + columns[i] + "\"" + "}" + "," + "\n";
      }
    }
    if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("USER_SCREEN")))
    {
      String strtemp = Constant.getValue("user.page.default.columns.firstpage");
      String[] columns = StringUtils.tokenize(strtemp, ",");
      for (int i = 0; i < columns.length; i++) {
        strcontents = strcontents + "{key:" + "\"" + columns[i] + "\"" + "}" + "," + "\n";
      }
    }
    if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("VENDOR_SCREEN")))
    {
      String strtemp = Constant.getValue("vendor.page.default.columns.firstpage");
      String[] columns = StringUtils.tokenize(strtemp, ",");
      for (int i = 0; i < columns.length; i++) {
        strcontents = strcontents + "{key:" + "\"" + columns[i] + "\"" + "}" + "," + "\n";
      }
    }
    if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("REFERRAL_SCREEN")))
    {
      String strtemp = Constant.getValue("referral.page.default.columns");
      String[] columns = StringUtils.tokenize(strtemp, ",");
      for (int i = 0; i < columns.length; i++) {
        strcontents = strcontents + "{key:" + "\"" + columns[i] + "\"" + "}" + "," + "\n";
      }
    }
    if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("APP_SEARCH_SCREEN_RECRUITER")))
    {
      String strtemp = Constant.getValue("applicant.page.recruiter.default.columns");
      String[] columns = StringUtils.tokenize(strtemp, ",");
      for (int i = 0; i < columns.length; i++) {
        strcontents = strcontents + "{key:" + "\"" + columns[i] + "\"" + "}" + "," + "\n";
      }
    }
    if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("APPLICANT_SUMMARY_SCREEN")))
    {
      String strtemp = Constant.getValue("applicant.page.applicant.summary.default.columns");
      String[] columns = StringUtils.tokenize(strtemp, ",");
      for (int i = 0; i < columns.length; i++) {
        strcontents = strcontents + "{key:" + "\"" + columns[i] + "\"" + "}" + "," + "\n";
      }
    }
    if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("ALL_APP_SEARCH_SCREEN")))
    {
      String strtemp = Constant.getValue("applicant.page.all.default.columns");
      String[] columns = StringUtils.tokenize(strtemp, ",");
      for (int i = 0; i < columns.length; i++) {
        strcontents = strcontents + "{key:" + "\"" + columns[i] + "\"" + "}" + "," + "\n";
      }
    }
    if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("ON_BOARDING_SEARCH_SCREEN")))
    {
      String strtemp = Constant.getValue("onboarding.page.default.columns");
      String[] columns = StringUtils.tokenize(strtemp, ",");
      for (int i = 0; i < columns.length; i++) {
        strcontents = strcontents + "{key:" + "\"" + columns[i] + "\"" + "}" + "," + "\n";
      }
    }
    if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("ONBOARD_TASK_DEF_SCREEN")))
    {
      String strtemp = Constant.getValue("onboarding.taskdef.default.columns");
      String[] columns = StringUtils.tokenize(strtemp, ",");
      strcontents = strcontents + "{key:" + "\"" + "primaryOwnerId" + "\"" + "}," + "\n" + "{key:" + "\"" + "isGroup" + "\"" + "}," + "\n";
      for (int i = 0; i < columns.length; i++) {
        strcontents = strcontents + "{key:" + "\"" + columns[i] + "\"" + "}" + "," + "\n";
      }
    }
    if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("ALL_REQUISION_SCREEN")))
    {
      String strtemp = Constant.getValue("requisition.page.default.columns");
      String[] columns = StringUtils.tokenize(strtemp, ",");
      for (int i = 0; i < columns.length; i++) {
        strcontentsrequistion = strcontentsrequistion + "{key:" + "\"" + columns[i] + "\"" + "}" + "," + "\n";
      }
      strcontentsrequistion = strcontentsrequistion + "{key:" + "\"" + "edit" + "\"" + "}," + "\n" + " {key:" + "\"" + "summary" + "\"" + "}";
      return strcontentsrequistion;
    }
    if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("REQUISION_TEMPLATE_SCREEN")))
    {
      String strtemp = Constant.getValue("requisition.template.default.columns");
      String[] columns = StringUtils.tokenize(strtemp, ",");
      for (int i = 0; i < columns.length; i++) {
        strcontents = strcontents + "{key:" + "\"" + columns[i] + "\"" + "}" + "," + "\n";
      }
    }
    if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("MY_REQUISITION_SCREEN")))
    {
      String strtemp = Constant.getValue("myrequisition.page.default.columns");
      String[] columns = StringUtils.tokenize(strtemp, ",");
      for (int i = 0; i < columns.length; i++) {
        strcontentsrequistion = strcontentsrequistion + "{key:" + "\"" + columns[i] + "\"" + "}" + "," + "\n";
      }
      strcontentsrequistion = strcontentsrequistion + "{key:" + "\"" + "edit" + "\"" + "}," + "\n" + " {key:" + "\"" + "summary" + "\"" + "}";
      

      return strcontentsrequistion;
    }
    if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("DASHBOARD_RECRUITER_ASSIGNED_REQUISTIONS")))
    {
      String strtemp = Constant.getValue("dashboard.recruiter.assigned.requistions");
      String[] columns = StringUtils.tokenize(strtemp, ",");
      for (int i = 0; i < columns.length; i++) {
        strcontentsrequistion = strcontentsrequistion + "{key:" + "\"" + columns[i] + "\"" + "}" + "," + "\n";
      }
      strcontentsrequistion = strcontentsrequistion + "{key:" + "\"" + "edit" + "\"" + "}," + "\n" + " {key:" + "\"" + "summary" + "\"" + "}";
      
      return strcontentsrequistion;
    }
    if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("DASHBOARD_RECRUITER_IN_OFFER_PROCESS")))
    {
      String strtemp = Constant.getValue("dashboard.recruiter.applicants.offer.process");
      String[] columns = StringUtils.tokenize(strtemp, ",");
      for (int i = 0; i < columns.length; i++) {
        strcontents = strcontents + "{key:" + "\"" + columns[i] + "\"" + "}" + "," + "\n";
      }
    }
    if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("DASHBOARD_RECRUITER_IN_JOINED_PROCESS")))
    {
      String strtemp = Constant.getValue("dashboard.recruiter.applicants.joining.process");
      String[] columns = StringUtils.tokenize(strtemp, ",");
      for (int i = 0; i < columns.length; i++) {
        strcontents = strcontents + "{key:" + "\"" + columns[i] + "\"" + "}" + "," + "\n";
      }
    }
    if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("TALENT_POOL_SCREEN")))
    {
      String strtemp = Constant.getValue("talentpool.page.default.columns");
      String[] columns = StringUtils.tokenize(strtemp, ",");
      for (int i = 0; i < columns.length; i++) {
        strcontents = strcontents + "{key:" + "\"" + columns[i] + "\"" + "}" + "," + "\n";
      }
    }
    if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("AGENCY_REDEMPTION__SCREEN")))
    {
      String strtemp = Constant.getValue("agencyredumption.default.columns");
      String[] columns = StringUtils.tokenize(strtemp, ",");
      for (int i = 0; i < columns.length; i++) {
        strcontents = strcontents + "{key:" + "\"" + columns[i] + "\"" + "}" + "," + "\n";
      }
    }
    if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("REFERRAL_REDEMPTION_SCREEN")))
    {
      String strtemp = Constant.getValue("referralredumption.default.columns");
      String[] columns = StringUtils.tokenize(strtemp, ",");
      for (int i = 0; i < columns.length; i++) {
        strcontents = strcontents + "{key:" + "\"" + columns[i] + "\"" + "}" + "," + "\n";
      }
    }
    strcontents = strcontents + "{key:" + "\"" + "uuid" + "\"" + "}," + "\n" + " {key:" + "\"" + "reqId" + "\"" + "}," + "\n" + " {key:" + "\"" + "edit" + "\"" + "}";
    
    return strcontents;
  }
  
  public static HSSFRow buildHeaderforExportToExcel(HSSFRow row, User user1, String screenName, CellStyle style)
  {
    String strtemp = "";
    HSSFCell cell = row.createCell(0);
    cell.setCellValue(Constant.getResourceStringValue("aquisition.applicant.applicantno", user1.getLocale()));
    cell.setCellStyle(style);
    HSSFCell cell1 = row.createCell(1);
    cell1.setCellValue(Constant.getResourceStringValue("aquisition.applicant.configutaion.fullName", user1.getLocale()));
    cell1.setCellStyle(style);
    
    ApplicantScreenSettings appsettings = ScreenSettingsDAO.getApplicationScreenSettings(user1.getUserId(), screenName);
    if (appsettings == null)
    {
      if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("ALL_APP_SEARCH_SCREEN"))) {
        strtemp = Constant.getValue("applicant.columns.all.firstpage");
      }
      if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("APP_SEARCH_SCREEN_HIRING_MGR"))) {
        strtemp = Constant.getValue("applicant.columns.hrmgr.firstpage");
      }
      if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("APP_SEARCH_SCREEN_RECRUITER"))) {
        strtemp = Constant.getValue("applicant.columns.recruiter.firstpage");
      }
      if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("APPLICANT_SUMMARY_SCREEN"))) {
        strtemp = Constant.getValue("applicant.columns.applicant.summary.firstpage");
      }
      if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("ON_BOARDING_SEARCH_SCREEN"))) {
        strtemp = Constant.getValue("applicant.columns.onboard.firstpage");
      }
      String[] columns = StringUtils.tokenize(strtemp, ",");
      int k = 2;
      for (int i = 0; i < columns.length; i++)
      {
        String resoucekey = "aquisition.applicant.configutaion." + columns[i];
        
        HSSFCell celltemp = row.createCell(k);
        celltemp.setCellValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
        celltemp.setCellStyle(style);
        k++;
      }
    }
    else if (!StringUtils.isNullOrEmpty(appsettings.getToList()))
    {
      String[] columns = StringUtils.tokenize(appsettings.getToList(), ",");
      int k = 2;
      for (int i = 0; i < columns.length; i++)
      {
        String resoucekey = "aquisition.applicant.configutaion." + columns[i];
        
        HSSFCell celltemp = row.createCell(k);
        celltemp.setCellValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
        celltemp.setCellStyle(style);
        k++;
      }
    }
    else
    {
      if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("ALL_APP_SEARCH_SCREEN"))) {
        strtemp = Constant.getValue("applicant.columns.all.firstpage");
      }
      if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("APP_SEARCH_SCREEN_HIRING_MGR"))) {
        strtemp = Constant.getValue("applicant.columns.hrmgr.firstpage");
      }
      if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("APP_SEARCH_SCREEN_RECRUITER"))) {
        strtemp = Constant.getValue("applicant.columns.recruiter.firstpage");
      }
      if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("APPLICANT_SUMMARY_SCREEN"))) {
        strtemp = Constant.getValue("applicant.columns.applicant.summary.firstpage");
      }
      if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("ON_BOARDING_SEARCH_SCREEN"))) {
        strtemp = Constant.getValue("applicant.columns.onboard.firstpage");
      }
      String[] columns = StringUtils.tokenize(strtemp, ",");
      int k = 2;
      for (int i = 0; i < columns.length; i++)
      {
        String resoucekey = "aquisition.applicant.configutaion." + columns[i];
        
        HSSFCell celltemp = row.createCell(k);
        celltemp.setCellValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
        celltemp.setCellStyle(style);
        k++;
      }
    }
    return row;
  }
  
  public static HSSFRow buildHeaderforExportToExcelReferralRedemption(HSSFRow row, User user1, String screenName, CellStyle style)
  {
    String strtemp = "";
    HSSFCell cell = row.createCell(0);
    cell.setCellValue(Constant.getResourceStringValue("aquisition.applicant.applicantno", user1.getLocale()));
    cell.setCellStyle(style);
    HSSFCell cell1 = row.createCell(1);
    cell1.setCellValue(Constant.getResourceStringValue("aquisition.applicant.configutaion.fullName", user1.getLocale()));
    cell1.setCellStyle(style);
    
    ApplicantScreenSettings appsettings = ScreenSettingsDAO.getApplicationScreenSettings(user1.getUserId(), screenName);
    if (appsettings == null)
    {
      if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("REFERRAL_REDEMPTION_SCREEN"))) {
        strtemp = Constant.getValue("referralredumption.default.columns");
      }
      String[] columns = StringUtils.tokenize(strtemp, ",");
      int k = 2;
      for (int i = 0; i < columns.length; i++)
      {
        String resoucekey = "referralredumption.page.table.configuration." + columns[i];
        
        HSSFCell celltemp = row.createCell(k);
        celltemp.setCellValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
        celltemp.setCellStyle(style);
        k++;
      }
    }
    else if (!StringUtils.isNullOrEmpty(appsettings.getToList()))
    {
      String[] columns = StringUtils.tokenize(appsettings.getToList(), ",");
      int k = 2;
      for (int i = 0; i < columns.length; i++)
      {
        String resoucekey = "referralredumption.page.table.configuration." + columns[i];
        
        HSSFCell celltemp = row.createCell(k);
        celltemp.setCellValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
        celltemp.setCellStyle(style);
        k++;
      }
    }
    else
    {
      if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("REFERRAL_REDEMPTION_SCREEN"))) {
        strtemp = Constant.getValue("referralredumption.default.columns");
      }
      String[] columns = StringUtils.tokenize(strtemp, ",");
      int k = 2;
      for (int i = 0; i < columns.length; i++)
      {
        String resoucekey = "referralredumption.page.table.configuration." + columns[i];
        
        HSSFCell celltemp = row.createCell(k);
        celltemp.setCellValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
        celltemp.setCellStyle(style);
        k++;
      }
    }
    return row;
  }
  
  public static HSSFRow buildHeaderforExportToExcelAgencyRedemption(HSSFRow row, User user1, String screenName, CellStyle style)
  {
    String strtemp = "";
    HSSFCell cell = row.createCell(0);
    cell.setCellValue(Constant.getResourceStringValue("aquisition.applicant.applicantno", user1.getLocale()));
    cell.setCellStyle(style);
    HSSFCell cell1 = row.createCell(1);
    cell1.setCellValue(Constant.getResourceStringValue("aquisition.applicant.configutaion.fullName", user1.getLocale()));
    cell1.setCellStyle(style);
    
    ApplicantScreenSettings appsettings = ScreenSettingsDAO.getApplicationScreenSettings(user1.getUserId(), screenName);
    if (appsettings == null)
    {
      if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("AGENCY_REDEMPTION__SCREEN"))) {
        strtemp = Constant.getValue("agencyredumption.default.columns");
      }
      String[] columns = StringUtils.tokenize(strtemp, ",");
      int k = 2;
      for (int i = 0; i < columns.length; i++)
      {
        String resoucekey = "agencyredumption.page.table.configuration." + columns[i];
        
        HSSFCell celltemp = row.createCell(k);
        celltemp.setCellValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
        celltemp.setCellStyle(style);
        k++;
      }
    }
    else if (!StringUtils.isNullOrEmpty(appsettings.getToList()))
    {
      String[] columns = StringUtils.tokenize(appsettings.getToList(), ",");
      int k = 2;
      for (int i = 0; i < columns.length; i++)
      {
        String resoucekey = "agencyredumption.page.table.configuration." + columns[i];
        
        HSSFCell celltemp = row.createCell(k);
        celltemp.setCellValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
        celltemp.setCellStyle(style);
        k++;
      }
    }
    else
    {
      if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("AGENCY_REDEMPTION__SCREEN"))) {
        strtemp = Constant.getValue("agencyredumption.default.columns");
      }
      String[] columns = StringUtils.tokenize(strtemp, ",");
      int k = 2;
      for (int i = 0; i < columns.length; i++)
      {
        String resoucekey = "agencyredumption.page.table.configuration." + columns[i];
        
        HSSFCell celltemp = row.createCell(k);
        celltemp.setCellValue(Constant.getResourceStringValue(resoucekey, user1.getLocale()));
        celltemp.setCellStyle(style);
        k++;
      }
    }
    return row;
  }
  
  public static HSSFSheet buildDataforExportToExcel(HSSFSheet sheet, User user1, String screenName, List applicantList)
  {
    String datepattern = Constant.getValue("defaultdateformat");
    if (user1 != null) {
      datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
    }
    String strtemp = "";
    String[] columns = null;
    ApplicantScreenSettings appsettings = ScreenSettingsDAO.getApplicationScreenSettings(user1.getUserId(), screenName);
    if (appsettings == null)
    {
      if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("ALL_APP_SEARCH_SCREEN"))) {
        strtemp = Constant.getValue("applicant.columns.all.firstpage");
      }
      if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("APP_SEARCH_SCREEN_HIRING_MGR"))) {
        strtemp = Constant.getValue("applicant.columns.hrmgr.firstpage");
      }
      if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("APP_SEARCH_SCREEN_RECRUITER"))) {
        strtemp = Constant.getValue("applicant.columns.recruiter.firstpage");
      }
      if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("APPLICANT_SUMMARY_SCREEN"))) {
        strtemp = Constant.getValue("applicant.columns.applicant.summary.firstpage");
      }
      if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("ON_BOARDING_SEARCH_SCREEN"))) {
        strtemp = Constant.getValue("applicant.columns.onboard.firstpage");
      }
      columns = StringUtils.tokenize(strtemp, ",");
    }
    else if (!StringUtils.isNullOrEmpty(appsettings.getToList()))
    {
      columns = StringUtils.tokenize(appsettings.getToList(), ",");
      int k = 1;
      for (int i = 0; i < columns.length; i++)
      {
        String resoucekey = "aquisition.applicant.configutaion." + columns[i];
        

        k++;
      }
    }
    else
    {
      if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("ALL_APP_SEARCH_SCREEN"))) {
        strtemp = Constant.getValue("applicant.columns.all.firstpage");
      }
      if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("APP_SEARCH_SCREEN_HIRING_MGR"))) {
        strtemp = Constant.getValue("applicant.columns.hrmgr.firstpage");
      }
      if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("APP_SEARCH_SCREEN_RECRUITER"))) {
        strtemp = Constant.getValue("applicant.columns.recruiter.firstpage");
      }
      if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("APPLICANT_SUMMARY_SCREEN"))) {
        strtemp = Constant.getValue("applicant.columns.applicant.summary.firstpage");
      }
      if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("ON_BOARDING_SEARCH_SCREEN"))) {
        strtemp = Constant.getValue("applicant.columns.onboard.firstpage");
      }
      columns = StringUtils.tokenize(strtemp, ",");
    }
    int k = 1;
    for (int j = 0; j < applicantList.size(); j++)
    {
      try
      {
        JobApplicant applicant = (JobApplicant)applicantList.get(j);
        
        Class applicantClass = applicant.getClass();
        
        HSSFRow row = sheet.createRow(k);
        row.createCell(0).setCellValue(applicant.getApplicant_number());
        row.createCell(1).setCellValue(applicant.getFullName());
        int z = 2;
        for (int p = 0; p < columns.length; p++)
        {
          String type = (String)Constant.applicantAttributeTypeMap.get(columns[p]);
          Field field = applicantClass.getField(columns[p]);
          if (type.equals("java.util.Date"))
          {
            if (field.get(applicant) != null) {
              row.createCell(z).setCellValue(DateUtil.convertDateToStringDate((Date)field.get(applicant), datepattern));
            }
          }
          else if (type.equals("java.lang.String"))
          {
            if (field.get(applicant) != null) {
              row.createCell(z).setCellValue((String)field.get(applicant));
            }
          }
          else if (type.equals("long"))
          {
            if (field.get(applicant) != null) {
              row.createCell(z).setCellValue(((Long)field.get(applicant)).longValue());
            }
          }
          else if (type.equals("int"))
          {
            if (field.get(applicant) != null) {
              row.createCell(z).setCellValue(((Integer)field.get(applicant)).intValue());
            }
          }
          else if ((type.equals("double")) && 
            (field.get(applicant) != null)) {
            row.createCell(z).setCellValue(((Double)field.get(applicant)).doubleValue());
          }
          z++;
        }
      }
      catch (Exception e)
      {
        logger.info(e);
        e.printStackTrace();
      }
      k++;
    }
    return sheet;
  }
  
  public static HSSFSheet buildDataforExportToExcelReferralRedemption(HSSFSheet sheet, User user1, String screenName, List redList)
  {
    String datepattern = Constant.getValue("defaultdateformat");
    if (user1 != null) {
      datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
    }
    String strtemp = "";
    String[] columns = null;
    ApplicantScreenSettings appsettings = ScreenSettingsDAO.getApplicationScreenSettings(user1.getUserId(), screenName);
    if (appsettings == null)
    {
      if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("REFERRAL_REDEMPTION_SCREEN"))) {
        strtemp = Constant.getValue("referralredumption.default.columns");
      }
      columns = StringUtils.tokenize(strtemp, ",");
    }
    else if (!StringUtils.isNullOrEmpty(appsettings.getToList()))
    {
      columns = StringUtils.tokenize(appsettings.getToList(), ",");
      int k = 1;
      for (int i = 0; i < columns.length; i++)
      {
        String resoucekey = "referralredumption.page.table.configuration." + columns[i];
        

        k++;
      }
    }
    else
    {
      if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("REFERRAL_REDEMPTION_SCREEN"))) {
        strtemp = Constant.getValue("referralredumption.default.columns");
      }
      columns = StringUtils.tokenize(strtemp, ",");
    }
    ReferralRedemption rr = new ReferralRedemption();
    Class redemptionclass = rr.getClass();
    Field[] f = redemptionclass.getDeclaredFields();
    Map redAttributeTypeMap = new HashMap();
    for (int p = 0; p < f.length; p++) {
      redAttributeTypeMap.put(f[p].getName(), f[p].getType().getName());
    }
    int k = 1;
    for (int j = 0; j < redList.size(); j++)
    {
      try
      {
        ReferralRedemption red = (ReferralRedemption)redList.get(j);
        
        Class redClass = red.getClass();
        
        HSSFRow row = sheet.createRow(k);
        row.createCell(0).setCellValue(red.getApplicant_number());
        row.createCell(1).setCellValue(red.getApplicantName());
        int z = 2;
        for (int p = 0; p < columns.length; p++)
        {
          String type = (String)redAttributeTypeMap.get(columns[p]);
          Field field = redClass.getField(columns[p]);
          if (type.equals("java.util.Date"))
          {
            if (field.get(red) != null) {
              row.createCell(z).setCellValue(DateUtil.convertDateToStringDate((Date)field.get(red), datepattern));
            }
          }
          else if (type.equals("java.lang.String"))
          {
            if (field.get(red) != null) {
              row.createCell(z).setCellValue((String)field.get(red));
            }
          }
          else if (type.equals("long"))
          {
            if (field.get(red) != null) {
              row.createCell(z).setCellValue(((Long)field.get(red)).longValue());
            }
          }
          else if (type.equals("int"))
          {
            if (field.get(red) != null) {
              row.createCell(z).setCellValue(((Integer)field.get(red)).intValue());
            }
          }
          else if ((type.equals("double")) && 
            (field.get(red) != null)) {
            row.createCell(z).setCellValue(((Double)field.get(red)).doubleValue());
          }
          z++;
        }
      }
      catch (Exception e)
      {
        logger.info(e);
        e.printStackTrace();
      }
      k++;
    }
    return sheet;
  }
  
  public static HSSFSheet buildDataforExportToExcelAgencyRedemption(HSSFSheet sheet, User user1, String screenName, List redList)
  {
    String datepattern = Constant.getValue("defaultdateformat");
    if (user1 != null) {
      datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
    }
    String strtemp = "";
    String[] columns = null;
    ApplicantScreenSettings appsettings = ScreenSettingsDAO.getApplicationScreenSettings(user1.getUserId(), screenName);
    if (appsettings == null)
    {
      if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("AGENCY_REDEMPTION__SCREEN"))) {
        strtemp = Constant.getValue("agencyredumption.default.columns");
      }
      columns = StringUtils.tokenize(strtemp, ",");
    }
    else if (!StringUtils.isNullOrEmpty(appsettings.getToList()))
    {
      columns = StringUtils.tokenize(appsettings.getToList(), ",");
      int k = 1;
      for (int i = 0; i < columns.length; i++)
      {
        String resoucekey = "agencyredumption.page.table.configuration." + columns[i];
        

        k++;
      }
    }
    else
    {
      if ((!StringUtils.isNullOrEmpty(screenName)) && (screenName.equals("AGENCY_REDEMPTION__SCREEN"))) {
        strtemp = Constant.getValue("agencyredumption.default.columns");
      }
      columns = StringUtils.tokenize(strtemp, ",");
    }
    AgencyRedemption rr = new AgencyRedemption();
    Class redemptionclass = rr.getClass();
    Field[] f = redemptionclass.getDeclaredFields();
    Map redAttributeTypeMap = new HashMap();
    for (int p = 0; p < f.length; p++) {
      redAttributeTypeMap.put(f[p].getName(), f[p].getType().getName());
    }
    int k = 1;
    for (int j = 0; j < redList.size(); j++)
    {
      try
      {
        AgencyRedemption red = (AgencyRedemption)redList.get(j);
        
        Class redClass = red.getClass();
        
        HSSFRow row = sheet.createRow(k);
        row.createCell(0).setCellValue(red.getApplicant_number());
        row.createCell(1).setCellValue(red.getApplicantName());
        int z = 2;
        for (int p = 0; p < columns.length; p++)
        {
          String type = (String)redAttributeTypeMap.get(columns[p]);
          Field field = redClass.getField(columns[p]);
          if (type.equals("java.util.Date"))
          {
            if (field.get(red) != null) {
              row.createCell(z).setCellValue(DateUtil.convertDateToStringDate((Date)field.get(red), datepattern));
            }
          }
          else if (type.equals("java.lang.String"))
          {
            if (field.get(red) != null) {
              row.createCell(z).setCellValue((String)field.get(red));
            }
          }
          else if (type.equals("long"))
          {
            if (field.get(red) != null) {
              row.createCell(z).setCellValue(((Long)field.get(red)).longValue());
            }
          }
          else if (type.equals("int"))
          {
            if (field.get(red) != null) {
              row.createCell(z).setCellValue(((Integer)field.get(red)).intValue());
            }
          }
          else if ((type.equals("double")) && 
            (field.get(red) != null)) {
            row.createCell(z).setCellValue(((Double)field.get(red)).doubleValue());
          }
          z++;
        }
      }
      catch (Exception e)
      {
        logger.info(e);
        e.printStackTrace();
      }
      k++;
    }
    return sheet;
  }
}
