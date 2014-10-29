package com.util;

import com.bean.BudgetCode;
import com.bean.BulkUploadTask;
import com.bean.Country;
import com.bean.DeclinedResons;
import com.bean.Department;
import com.bean.EducationDetails;
import com.bean.JobApplicant;
import com.bean.JobGrade;
import com.bean.JobRequisition;
import com.bean.JobType;
import com.bean.Location;
import com.bean.Organization;
import com.bean.PreviousOrgDetails;
import com.bean.ProjectCodes;
import com.bean.SalaryPlan;
import com.bean.State;
import com.bean.User;
import com.bean.WorkShift;
import com.bean.criteria.AgencyRedumptionSearchCriteria;
import com.bean.criteria.ApplicantSearchCriteria;
import com.bean.criteria.ReferalRedumptionSearchCriteria;
import com.bean.criteria.RequisitionSearchCriteriaMultiple;
import com.bean.criteria.ResuistionSearchCriteria;
import com.bo.ApplicantBO;
import com.bo.BOFactory;
import com.bo.JobRequistionBO;
import com.bo.RefBO;
import com.common.Common;
import com.dao.LovOpsDAO;
import com.dao.TaskDAO;
import com.resources.Constant;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;

public class ExportTaskUtil
  implements Runnable
{
  protected static final Logger logger = Logger.getLogger(ExportTaskUtil.class);
  String tasktype;
  User user;
  BulkUploadTask task;
  List reqList;
  String cri;
  String experience;
  String education;
  String certification;
  String targetjoiningdate;
  String interviewstate;
  ResuistionSearchCriteria reqCriteria;
  String searcherrole;
  ApplicantSearchCriteria applicantSearchCriteria;
  AgencyRedumptionSearchCriteria agencyRedumptionSearchCriteria;
  ReferalRedumptionSearchCriteria referalRedumptionSearchCriteria;
  RequisitionSearchCriteriaMultiple reqserachCriteria;
  
  public void run()
  {
    try
    {
      if ((!StringUtils.isNullOrEmpty(this.tasktype)) && (this.tasktype.equals(Common.EXPORT_APPLICANTS)))
      {
        this.task.setStatus(Common.BULK_UPLOAD_STATUS_RUNNING);
        this.task.setCreatedBy(this.user.getFirstName() + " " + this.user.getLastName());
        this.task.setCreatedDate(new Date());
        this.task.setTasktype(this.tasktype);
        this.task.setCreatedById(this.user.getUserId());
        this.task = TaskDAO.saveBulkUploadTask(this.task);
        logger.info("task.getBulkuploadtaskid()" + this.task.getBulkuploadtaskid());
        
        this.task = exportApplicants(this.task);
        
        this.task.setUpdatedBy(this.user.getFirstName() + " " + this.user.getLastName());
        this.task.setUpdatedDate(new Date());
        this.task = TaskDAO.updateBulkUploadTask(this.task);
      }
      if ((!StringUtils.isNullOrEmpty(this.tasktype)) && (this.tasktype.equals(Common.EXPORT_APPLICANTS_SEARCH)) && (!StringUtils.isNullOrEmpty(this.searcherrole)) && (this.searcherrole.equals("HIRING_MGR")))
      {
        logger.info("EXPORT_APPLICANTS_SEARCH Hiring Mgr started");
        this.task.setStatus(Common.BULK_UPLOAD_STATUS_RUNNING);
        this.task.setCreatedBy(this.user.getFirstName() + " " + this.user.getLastName());
        this.task.setCreatedDate(new Date());
        this.task.setTasktype(this.tasktype);
        this.task.setCreatedById(this.user.getUserId());
        this.task = TaskDAO.saveBulkUploadTask(this.task);
        

        this.task = exportApplicantsSearchHiringMgr(this.task);
        
        this.task.setUpdatedBy(this.user.getFirstName() + " " + this.user.getLastName());
        this.task.setUpdatedDate(new Date());
        this.task = TaskDAO.updateBulkUploadTask(this.task);
        logger.info("EXPORT_APPLICANTS_SEARCH Hiring Mgr end");
      }
      if ((!StringUtils.isNullOrEmpty(this.tasktype)) && (this.tasktype.equals(Common.EXPORT_APPLICANTS_SEARCH)) && (!StringUtils.isNullOrEmpty(this.searcherrole)) && (this.searcherrole.equals("RECRUITER")))
      {
        logger.info("EXPORT_APPLICANTS_SEARCH Recruiter started");
        this.task.setStatus(Common.BULK_UPLOAD_STATUS_RUNNING);
        this.task.setCreatedBy(this.user.getFirstName() + " " + this.user.getLastName());
        this.task.setCreatedDate(new Date());
        this.task.setTasktype(this.tasktype);
        this.task.setCreatedById(this.user.getUserId());
        this.task = TaskDAO.saveBulkUploadTask(this.task);
        

        this.task = exportApplicantsSearchRecruiter(this.task);
        
        this.task.setUpdatedBy(this.user.getFirstName() + " " + this.user.getLastName());
        this.task.setUpdatedDate(new Date());
        this.task = TaskDAO.updateBulkUploadTask(this.task);
        logger.info("EXPORT_APPLICANTS_SEARCH Recruiter end");
      }
      if ((!StringUtils.isNullOrEmpty(this.tasktype)) && (this.tasktype.equals(Common.EXPORT_APPLICANTS_SEARCH)) && (!StringUtils.isNullOrEmpty(this.searcherrole)) && (this.searcherrole.equals("ALL_APP_EXP")))
      {
        logger.info("EXPORT_APPLICANTS_SEARCH All search started");
        this.task.setStatus(Common.BULK_UPLOAD_STATUS_RUNNING);
        this.task.setCreatedBy(this.user.getFirstName() + " " + this.user.getLastName());
        this.task.setCreatedDate(new Date());
        this.task.setTasktype(this.tasktype);
        this.task.setCreatedById(this.user.getUserId());
        this.task = TaskDAO.saveBulkUploadTask(this.task);
        

        this.task = exportApplicantsSearchAllSearch(this.task);
        
        this.task.setUpdatedBy(this.user.getFirstName() + " " + this.user.getLastName());
        this.task.setUpdatedDate(new Date());
        this.task = TaskDAO.updateBulkUploadTask(this.task);
        logger.info("EXPORT_APPLICANTS_SEARCH All search end");
      }
      if ((!StringUtils.isNullOrEmpty(this.tasktype)) && (this.tasktype.equals(Common.EXPORT_APPLICANTS_SEARCH)) && (!StringUtils.isNullOrEmpty(this.searcherrole)) && (this.searcherrole.equals("ONBOARD_APP_EXP")))
      {
        logger.info("ON_BOARD_APPLICANTS_SEARCH_ROLE  search started");
        this.task.setStatus(Common.BULK_UPLOAD_STATUS_RUNNING);
        this.task.setCreatedBy(this.user.getFirstName() + " " + this.user.getLastName());
        this.task.setCreatedDate(new Date());
        this.task.setTasktype(this.tasktype);
        this.task.setCreatedById(this.user.getUserId());
        this.task = TaskDAO.saveBulkUploadTask(this.task);
        

        this.task = exportOnBoardApplicantsSearch(this.task);
        
        this.task.setUpdatedBy(this.user.getFirstName() + " " + this.user.getLastName());
        this.task.setUpdatedDate(new Date());
        this.task = TaskDAO.updateBulkUploadTask(this.task);
        logger.info("EXPORT_APPLICANTS_SEARCH All search end");
      }
      if (((!StringUtils.isNullOrEmpty(this.tasktype)) && (this.tasktype.equals(Common.EXPORT_MYREQUISTIONS_SEARCH_RESULTS))) || (this.tasktype.equals(Common.EXPORT_REQUISTIONS_SEARCH_RESULTS)) || (this.tasktype.equals(Common.EXPORT_MYREQUISTIONS_HIRING_MANAGER_SEARCH_RESULTS)) || (this.tasktype.equals(Common.EXPORT_MYREQUISTIONS_RECRUITER_SEARCH_RESULTS)))
      {
        this.task.setStatus(Common.BULK_UPLOAD_STATUS_RUNNING);
        this.task.setCreatedBy(this.user.getFirstName() + " " + this.user.getLastName());
        this.task.setCreatedDate(new Date());
        this.task.setTasktype(this.tasktype);
        this.task.setCreatedById(this.user.getUserId());
        this.task = TaskDAO.saveBulkUploadTask(this.task);
        logger.info("task.getBulkuploadtaskid()" + this.task.getBulkuploadtaskid());
        
        this.task = exportMyRequistionSearch(this.task);
        
        this.task.setUpdatedBy(this.user.getFirstName() + " " + this.user.getLastName());
        this.task.setUpdatedDate(new Date());
        this.task = TaskDAO.updateBulkUploadTask(this.task);
      }
      if ((!StringUtils.isNullOrEmpty(this.tasktype)) && (this.tasktype.equals(Common.EXPORT_AGENCY_REDEMPTION_SEARCH_RESULTS)))
      {
        this.task.setStatus(Common.BULK_UPLOAD_STATUS_RUNNING);
        this.task.setCreatedBy(this.user.getFirstName() + " " + this.user.getLastName());
        this.task.setCreatedDate(new Date());
        this.task.setTasktype(this.tasktype);
        this.task.setCreatedById(this.user.getUserId());
        this.task = TaskDAO.saveBulkUploadTask(this.task);
        logger.info("task.getBulkuploadtaskid()" + this.task.getBulkuploadtaskid());
        
        this.task = exportAgencyRedumptionSearch(this.task);
        
        this.task.setUpdatedBy(this.user.getFirstName() + " " + this.user.getLastName());
        this.task.setUpdatedDate(new Date());
        this.task = TaskDAO.updateBulkUploadTask(this.task);
      }
      if ((!StringUtils.isNullOrEmpty(this.tasktype)) && (this.tasktype.equals(Common.EXPORT_REFERAL_REDEMPTION_SEARCH_RESULTS)))
      {
        this.task.setStatus(Common.BULK_UPLOAD_STATUS_RUNNING);
        this.task.setCreatedBy(this.user.getFirstName() + " " + this.user.getLastName());
        this.task.setCreatedDate(new Date());
        this.task.setTasktype(this.tasktype);
        this.task.setCreatedById(this.user.getUserId());
        this.task = TaskDAO.saveBulkUploadTask(this.task);
        logger.info("task.getBulkuploadtaskid()" + this.task.getBulkuploadtaskid());
        
        this.task = exportReferalRedumptionSearch(this.task);
        
        this.task.setUpdatedBy(this.user.getFirstName() + " " + this.user.getLastName());
        this.task.setUpdatedDate(new Date());
        this.task = TaskDAO.updateBulkUploadTask(this.task);
      }
    }
    catch (Exception e)
    {
      logger.info("Error on export" + e);
    }
  }
  
  private BulkUploadTask exportApplicants(BulkUploadTask task)
  {
    logger.info("exportApplicants start");
    try
    {
      String datepattern = Constant.getValue("defaultdateformat");
      if (this.user != null) {
        datepattern = DateUtil.getDatePatternFormat(this.user.getLocale());
      }
      HSSFWorkbook wb = new HSSFWorkbook();
      for (int i = 0; i < this.reqList.size(); i++)
      {
        String reqid = (String)this.reqList.get(i);
        JobRequisition jb = BOFactory.getJobRequistionBO().getJobRequision(reqid);
        List applist = BOFactory.getApplicantBO().exportApplicants(this.user, this.cri, this.targetjoiningdate, this.interviewstate, new Long(reqid).longValue());
        logger.info("applist.size()" + applist.size());
        

        HSSFSheet sheet = wb.createSheet(jb.getJobreqName());
        

        CellStyle style = wb.createCellStyle();
        style.setFillForegroundColor(IndexedColors.TEAL.getIndex());
        style.setFillPattern((short)1);
        
        HSSFRow row = sheet.createRow(0);
        
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("Applicant Id");
        
        row.createCell(1).setCellValue("Applicant Name");
        row.createCell(2).setCellValue("Email Id");
        row.createCell(3).setCellValue("City");
        row.createCell(4).setCellValue("State");
        row.createCell(5).setCellValue("Country");
        row.createCell(6).setCellValue("Phone");
        row.createCell(7).setCellValue("Highest Qualification");
        row.createCell(8).setCellValue("Qualification");
        row.createCell(9).setCellValue("Gender");
        row.createCell(10).setCellValue("Current Organization");
        row.createCell(11).setCellValue("Status");
        row.createCell(12).setCellValue("Profile added by");
        row.createCell(13).setCellValue("Applied Date");
        row.createCell(14).setCellValue("Referer Name");
        row.createCell(15).setCellValue("Referer Email");
        row.createCell(16).setCellValue("Current CTC");
        row.createCell(17).setCellValue("Offered CTC");
        row.createCell(18).setCellValue("Expected CTC");
        row.createCell(19).setCellValue("Currency Code");
        row.createCell(20).setCellValue("Target Joining Date");
        row.createCell(21).setCellValue("Joined Date");
        row.createCell(22).setCellValue("Offer Released Date");
        row.createCell(23).setCellValue("Offer Cancelled Date");
        row.createCell(24).setCellValue("Offer Cancelled Reason");
        row.createCell(25).setCellValue("Offer Declined Date");
        row.createCell(26).setCellValue("Offer Declined Reason");
        



        int k = 1;
        for (int j = 0; j < applist.size(); j++)
        {
          JobApplicant applicant = (JobApplicant)applist.get(j);
          row = sheet.createRow(k);
          row.createCell(0).setCellValue(applicant.getApplicantId());
          row.createCell(1).setCellValue(applicant.getFullName());
          row.createCell(2).setCellValue(applicant.getEmail());
          row.createCell(3).setCellValue(applicant.getCity() == null ? "" : applicant.getCity());
          if (applicant.getState() != null) {
            row.createCell(4).setCellValue(applicant.getState().getStateName());
          } else {
            row.createCell(4).setCellValue("");
          }
          if (applicant.getCountry() != null) {
            row.createCell(5).setCellValue(applicant.getCountry().getCountryName());
          } else {
            row.createCell(5).setCellValue("");
          }
          row.createCell(6).setCellValue(applicant.getPhone() == null ? "" : applicant.getPhone());
          row.createCell(7).setCellValue(applicant.getHeighestQualification() == null ? "" : applicant.getHeighestQualification());
          row.createCell(8).setCellValue(applicant.getQualifications() == null ? "" : applicant.getQualifications());
          if (applicant.getGender() != null) {
            row.createCell(9).setCellValue(applicant.getGender().equals("M") ? "Male" : "Female");
          } else {
            row.createCell(9).setCellValue("");
          }
          row.createCell(10).setCellValue(applicant.getPreviousOrganization() == null ? "" : applicant.getPreviousOrganization());
          row.createCell(11).setCellValue(applicant.getInterviewState());
          row.createCell(12).setCellValue(applicant.getCreatedBy());
          if (applicant.getAppliedDate() != null) {
            row.createCell(13).setCellValue(DateUtil.convertDateToStringDate(applicant.getAppliedDate(), datepattern));
          } else {
            row.createCell(13).setCellValue("");
          }
          row.createCell(14).setCellValue(applicant.getReferrerName() == null ? "" : applicant.getReferrerName());
          row.createCell(15).setCellValue(applicant.getReferrerEmail() == null ? "" : applicant.getReferrerEmail());
          String currecnycode = applicant.getCurrectctccurrencycode() == null ? "" : applicant.getCurrectctccurrencycode();
          String currentctc = applicant.getCurrectctc() == null ? "" : applicant.getCurrectctc();
          row.createCell(16).setCellValue(currentctc);
          String offeredctc = applicant.getOfferedctc() == null ? "" : applicant.getOfferedctc();
          row.createCell(17).setCellValue(offeredctc);
          String expectedctc = applicant.getExpectedctc() == null ? "" : applicant.getExpectedctc();
          row.createCell(18).setCellValue(expectedctc);
          row.createCell(19).setCellValue(currecnycode);
          if (applicant.getTargetjoiningdate() != null) {
            row.createCell(20).setCellValue(DateUtil.convertDateToStringDate(applicant.getTargetjoiningdate(), datepattern));
          } else {
            row.createCell(20).setCellValue("");
          }
          if (applicant.getJoineddate() != null) {
            row.createCell(21).setCellValue(DateUtil.convertDateToStringDate(applicant.getJoineddate(), datepattern));
          } else {
            row.createCell(21).setCellValue("");
          }
          if (applicant.getOfferreleasedate() != null) {
            row.createCell(22).setCellValue(DateUtil.convertDateToStringDate(applicant.getOfferreleasedate(), datepattern));
          } else {
            row.createCell(22).setCellValue("");
          }
          if (applicant.getOffercancelleddate() != null) {
            row.createCell(23).setCellValue(DateUtil.convertDateToStringDate(applicant.getOffercancelleddate(), datepattern));
          } else {
            row.createCell(23).setCellValue("");
          }
          if (applicant.getOffercancelledresonid() != 0)
          {
            DeclinedResons rn = LovOpsDAO.getDeclinedReasonById(applicant.getOffercancelledresonid());
            row.createCell(24).setCellValue(rn.getOfferdecliedreasonName());
          }
          else
          {
            row.createCell(24).setCellValue("");
          }
          if (applicant.getOfferdeclineddate() != null) {
            row.createCell(25).setCellValue(DateUtil.convertDateToStringDate(applicant.getOfferdeclineddate(), datepattern));
          } else {
            row.createCell(25).setCellValue("");
          }
          if (applicant.getOfferdeclinedresonid() != 0)
          {
            DeclinedResons rn = LovOpsDAO.getDeclinedReasonById(applicant.getOfferdeclinedresonid());
            row.createCell(26).setCellValue(rn.getOfferdecliedreasonName());
          }
          else
          {
            row.createCell(26).setCellValue("");
          }
          if ((!StringUtils.isNullOrEmpty(this.experience)) && (this.experience.equals("on")))
          {
            List experinceList = BOFactory.getApplicantBO().getPreviousOrgDetailsByApplicant(applicant.getApplicantId());
            if ((experinceList != null) && (experinceList.size() > 0))
            {
              k++;
              row = sheet.createRow(k);
              style = wb.createCellStyle();
              style.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
              style.setFillPattern((short)1);
              
              cell = row.createCell(0);
              cell.setCellValue("");
              cell = row.createCell(1);
              cell.setCellValue("Organization");
              cell.setCellStyle(style);
              cell = row.createCell(2);
              cell.setCellValue("Last role");
              cell.setCellStyle(style);
              cell = row.createCell(3);
              cell.setCellValue("Joining date");
              cell.setCellStyle(style);
              cell = row.createCell(4);
              cell.setCellValue("Relieving date");
              cell.setCellStyle(style);
              cell = row.createCell(5);
              cell.setCellValue("Reason for leave");
              cell.setCellStyle(style);
              cell = row.createCell(6);
              cell.setCellValue("Reporting to Manager");
              cell.setCellStyle(style);
              for (int p = 0; p < experinceList.size(); p++)
              {
                k++;
                PreviousOrgDetails prevorg = (PreviousOrgDetails)experinceList.get(p);
                row = sheet.createRow(k);
                row.createCell(0).setCellValue("");
                row.createCell(1).setCellValue(prevorg.getPrevOrgName() == null ? "" : prevorg.getPrevOrgName());
                row.createCell(2).setCellValue(prevorg.getRole() == null ? "" : prevorg.getRole());
                row.createCell(3).setCellValue(prevorg.getStartdate() == null ? "" : prevorg.getStartdate());
                row.createCell(4).setCellValue(prevorg.getEnddate() == null ? "" : prevorg.getEnddate());
                row.createCell(5).setCellValue(prevorg.getReasonforleave() == null ? "" : prevorg.getReasonforleave());
                row.createCell(6).setCellValue(prevorg.getReportingToName() == null ? "" : prevorg.getReportingToName());
              }
            }
          }
          if ((!StringUtils.isNullOrEmpty(this.education)) && (this.education.equals("on")))
          {
            List eduList = BOFactory.getApplicantBO().getEducationsByApplicantAndType(applicant.getApplicantId(), Common.EDUCATION_COLLEGE);
            if ((eduList != null) && (eduList.size() > 0))
            {
              k++;
              row = sheet.createRow(k);
              style = wb.createCellStyle();
              style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
              style.setFillPattern((short)1);
              
              cell = row.createCell(0);
              cell.setCellValue("");
              cell = row.createCell(1);
              cell.setCellValue("Education");
              cell.setCellStyle(style);
              cell = row.createCell(2);
              cell.setCellValue("Specialization");
              cell.setCellStyle(style);
              cell = row.createCell(3);
              cell.setCellValue("College/University");
              cell.setCellStyle(style);
              cell = row.createCell(4);
              cell.setCellValue("Percentage/Grade");
              cell.setCellStyle(style);
              cell = row.createCell(5);
              cell.setCellValue("Passing year");
              cell.setCellStyle(style);
              for (int p = 0; p < eduList.size(); p++)
              {
                k++;
                EducationDetails edu = (EducationDetails)eduList.get(p);
                row = sheet.createRow(k);
                row.createCell(0).setCellValue("");
                row.createCell(1).setCellValue(edu.getEducationName() == null ? "" : edu.getEducationName());
                row.createCell(2).setCellValue(edu.getSpecialization() == null ? "" : edu.getSpecialization());
                row.createCell(3).setCellValue(edu.getInstituteName() == null ? "" : edu.getInstituteName());
                row.createCell(4).setCellValue(edu.getPercentile() == null ? "" : edu.getPercentile());
                row.createCell(5).setCellValue(edu.getPassingYear() == null ? "" : edu.getPassingYear());
              }
            }
          }
          if ((!StringUtils.isNullOrEmpty(this.certification)) && (this.certification.equals("on")))
          {
            List eduList = BOFactory.getApplicantBO().getEducationsByApplicantAndType(applicant.getApplicantId(), Common.EDUCATION_CERTIFICATION);
            if ((eduList != null) && (eduList.size() > 0))
            {
              k++;
              row = sheet.createRow(k);
              style = wb.createCellStyle();
              style.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
              style.setFillPattern((short)1);
              
              cell = row.createCell(0);
              cell.setCellValue("");
              cell = row.createCell(1);
              cell.setCellValue("Certification Name");
              cell.setCellStyle(style);
              cell = row.createCell(2);
              cell.setCellValue("Certified Organization");
              cell.setCellStyle(style);
              cell = row.createCell(3);
              cell.setCellValue("Percentage/Grade");
              cell.setCellStyle(style);
              cell = row.createCell(4);
              cell.setCellValue("Passing year");
              cell.setCellStyle(style);
              for (int p = 0; p < eduList.size(); p++)
              {
                k++;
                EducationDetails edu = (EducationDetails)eduList.get(p);
                row = sheet.createRow(k);
                row.createCell(0).setCellValue("");
                row.createCell(1).setCellValue(edu.getEducationName() == null ? "" : edu.getEducationName());
                row.createCell(2).setCellValue(edu.getInstituteName() == null ? "" : edu.getInstituteName());
                row.createCell(3).setCellValue(edu.getPercentile() == null ? "" : edu.getPercentile());
                row.createCell(4).setCellValue(edu.getPassingYear() == null ? "" : edu.getPassingYear());
              }
            }
          }
          k++;
        }
      }
      FileOutputStream fileOut = new FileOutputStream(Constant.getValue("ATTACHMENT_PATH") + task.getBulkuploadtaskid() + "_" + this.user.getUserId() + "_" + "applicants.xls");
      wb.write(fileOut);
      fileOut.close();
      

      task.setUploadedFileName(task.getBulkuploadtaskid() + "_" + this.user.getUserId() + "_" + "applicants.xls");
      task.setStatus(Common.BULK_UPLOAD_STATUS_COMPLETED);
      logger.info("exportApplicants end");
    }
    catch (Exception e)
    {
      logger.info("Error on exportApplicants" + e);
      String error = "Error on exportApplicants:" + e.getMessage();
      if (error.length() > 999) {
        error = error.substring(0, 999);
      }
      task.setErrorDesc(error);
      task.setStatus(Common.BULK_UPLOAD_STATUS_FAIL);
    }
    return task;
  }
  
  private BulkUploadTask exportApplicantsSearchHiringMgr(BulkUploadTask task)
  {
    logger.info("exportApplicantsSearchHiringMgr start");
    try
    {
      String datepattern = Constant.getValue("defaultdateformat");
      if (this.user != null) {
        datepattern = DateUtil.getDatePatternFormat(this.user.getLocale());
      }
      HSSFWorkbook wb = new HSSFWorkbook();
      



      List applist = BOFactory.getApplicantBO().exportApplicantsSearchHiringMgr(this.user, this.applicantSearchCriteria);
      logger.info("applist.size()" + applist.size());
      


      HSSFSheet sheet = wb.createSheet("Applicants");
      

      CellStyle style = wb.createCellStyle();
      style.setFillForegroundColor(IndexedColors.TEAL.getIndex());
      style.setFillPattern((short)1);
      
      HSSFRow row = sheet.createRow(0);
      
      row = ScreenSettingUtils.buildHeaderforExportToExcel(row, this.user, "APP_SEARCH_SCREEN_HIRING_MGR", style);
      sheet = ScreenSettingUtils.buildDataforExportToExcel(sheet, this.user, "APP_SEARCH_SCREEN_HIRING_MGR", applist);
      










































































































































      FileOutputStream fileOut = new FileOutputStream(Constant.getValue("ATTACHMENT_PATH") + task.getBulkuploadtaskid() + "_" + this.user.getUserId() + "_" + "applicants.xls");
      wb.write(fileOut);
      fileOut.close();
      

      task.setUploadedFileName(task.getBulkuploadtaskid() + "_" + this.user.getUserId() + "_" + "applicants.xls");
      task.setStatus(Common.BULK_UPLOAD_STATUS_COMPLETED);
      logger.info("exportApplicantsSearchHiringMgr end");
    }
    catch (Exception e)
    {
      logger.info("Error on exportApplicantsSearchHiringMgr" + e);
      String error = "Error on exportApplicantsSearchHiringMgr:" + e.getMessage();
      if (error.length() > 999) {
        error = error.substring(0, 999);
      }
      task.setErrorDesc(error);
      task.setStatus(Common.BULK_UPLOAD_STATUS_FAIL);
    }
    return task;
  }
  
  private BulkUploadTask exportApplicantsSearchRecruiter(BulkUploadTask task)
  {
    logger.info("exportApplicantsSearchRecruiter start");
    try
    {
      String datepattern = Constant.getValue("defaultdateformat");
      if (this.user != null) {
        datepattern = DateUtil.getDatePatternFormat(this.user.getLocale());
      }
      HSSFWorkbook wb = new HSSFWorkbook();
      



      List applist = BOFactory.getApplicantBO().exportApplicantsSearchRecruiter(this.user, this.applicantSearchCriteria);
      logger.info("applist.size()" + applist.size());
      

      HSSFSheet sheet = wb.createSheet("Applicants");
      

      CellStyle style = wb.createCellStyle();
      style.setFillForegroundColor(IndexedColors.TEAL.getIndex());
      style.setFillPattern((short)1);
      
      HSSFRow row = sheet.createRow(0);
      
      row = ScreenSettingUtils.buildHeaderforExportToExcel(row, this.user, "APP_SEARCH_SCREEN_RECRUITER", style);
      sheet = ScreenSettingUtils.buildDataforExportToExcel(sheet, this.user, "APP_SEARCH_SCREEN_RECRUITER", applist);
      

      FileOutputStream fileOut = new FileOutputStream(Constant.getValue("ATTACHMENT_PATH") + task.getBulkuploadtaskid() + "_" + this.user.getUserId() + "_" + "applicants.xls");
      wb.write(fileOut);
      fileOut.close();
      

      task.setUploadedFileName(task.getBulkuploadtaskid() + "_" + this.user.getUserId() + "_" + "applicants.xls");
      task.setStatus(Common.BULK_UPLOAD_STATUS_COMPLETED);
      logger.info("exportApplicantsSearchRecruiter end");
    }
    catch (Exception e)
    {
      logger.info("Error on exportApplicantsSearchRecruiter" + e);
      String error = "Error on exportApplicantsSearchRecruiter:" + e.getMessage();
      if (error.length() > 999) {
        error = error.substring(0, 999);
      }
      task.setErrorDesc(error);
      task.setStatus(Common.BULK_UPLOAD_STATUS_FAIL);
    }
    return task;
  }
  
  private BulkUploadTask exportApplicantsSearchAllSearch(BulkUploadTask task)
  {
    logger.info("exportApplicantsSearchAllSearch start");
    try
    {
      String datepattern = Constant.getValue("defaultdateformat");
      if (this.user != null) {
        datepattern = DateUtil.getDatePatternFormat(this.user.getLocale());
      }
      HSSFWorkbook wb = new HSSFWorkbook();
      



      List applist = BOFactory.getApplicantBO().exportApplicantsSearchAllSearch(this.user, this.applicantSearchCriteria);
      logger.info("applist.size()" + applist.size());
      

      HSSFSheet sheet = wb.createSheet("Applicants");
      

      CellStyle style = wb.createCellStyle();
      style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
      style.setFillPattern((short)1);
      Font f = wb.createFont();
      f.setBoldweight((short)700);
      style.setFont(f);
      
      HSSFRow row = sheet.createRow(0);
      
      row = ScreenSettingUtils.buildHeaderforExportToExcel(row, this.user, "ALL_APP_SEARCH_SCREEN", style);
      sheet = ScreenSettingUtils.buildDataforExportToExcel(sheet, this.user, "ALL_APP_SEARCH_SCREEN", applist);
      

      FileOutputStream fileOut = new FileOutputStream(Constant.getValue("ATTACHMENT_PATH") + task.getBulkuploadtaskid() + "_" + this.user.getUserId() + "_" + "applicants.xls");
      wb.write(fileOut);
      fileOut.close();
      

      task.setUploadedFileName(task.getBulkuploadtaskid() + "_" + this.user.getUserId() + "_" + "applicants.xls");
      task.setStatus(Common.BULK_UPLOAD_STATUS_COMPLETED);
      logger.info("exportApplicantsSearchAllSearch end");
    }
    catch (Exception e)
    {
      logger.info("Error on exportApplicantsSearchAllSeach" + e);
      String error = "Error on exportApplicantsSearchAllSearch:" + e.getMessage();
      if (error.length() > 999) {
        error = error.substring(0, 999);
      }
      task.setErrorDesc(error);
      task.setStatus(Common.BULK_UPLOAD_STATUS_FAIL);
    }
    return task;
  }
  
  private BulkUploadTask exportOnBoardApplicantsSearch(BulkUploadTask task)
  {
    logger.info("exportOnBoardApplicantsSearch start");
    try
    {
      String datepattern = Constant.getValue("defaultdateformat");
      if (this.user != null) {
        datepattern = DateUtil.getDatePatternFormat(this.user.getLocale());
      }
      HSSFWorkbook wb = new HSSFWorkbook();
      



      List applist = BOFactory.getApplicantBO().exportOnBoardApplicantsSearch(this.user, this.applicantSearchCriteria);
      logger.info("applist.size()" + applist.size());
      

      HSSFSheet sheet = wb.createSheet("Applicants");
      

      CellStyle style = wb.createCellStyle();
      style.setFillForegroundColor(IndexedColors.TEAL.getIndex());
      style.setFillPattern((short)1);
      
      HSSFRow row = sheet.createRow(0);
      
      row = ScreenSettingUtils.buildHeaderforExportToExcel(row, this.user, "ON_BOARDING_SEARCH_SCREEN", style);
      sheet = ScreenSettingUtils.buildDataforExportToExcel(sheet, this.user, "ON_BOARDING_SEARCH_SCREEN", applist);
      

      FileOutputStream fileOut = new FileOutputStream(Constant.getValue("ATTACHMENT_PATH") + task.getBulkuploadtaskid() + "_" + this.user.getUserId() + "_" + "applicants.xls");
      wb.write(fileOut);
      fileOut.close();
      

      task.setUploadedFileName(task.getBulkuploadtaskid() + "_" + this.user.getUserId() + "_" + "applicants.xls");
      task.setStatus(Common.BULK_UPLOAD_STATUS_COMPLETED);
      logger.info("exportApplicantsSearchAllSearch end");
    }
    catch (Exception e)
    {
      logger.info("Error on exportApplicantsSearchAllSeach" + e);
      String error = "Error on exportApplicantsSearchAllSearch:" + e.getMessage();
      if (error.length() > 999) {
        error = error.substring(0, 999);
      }
      task.setErrorDesc(error);
      task.setStatus(Common.BULK_UPLOAD_STATUS_FAIL);
    }
    return task;
  }
  
  private BulkUploadTask exportAgencyRedumptionSearch(BulkUploadTask task)
  {
    logger.info("exportAgencyRedumptionSearch start");
    try
    {
      String datepattern = Constant.getValue("defaultdateformat");
      if (this.user != null) {
        datepattern = DateUtil.getDatePatternFormat(this.user.getLocale());
      }
      HSSFWorkbook wb = new HSSFWorkbook();
      List agencyRedList = new ArrayList();
      
      agencyRedList = BOFactory.getRefBO().exportAgencyRedumptionSearch(this.user, this.agencyRedumptionSearchCriteria);
      
      HSSFSheet sheet = wb.createSheet("Agency redemptions");
      

      CellStyle style = wb.createCellStyle();
      style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
      style.setFillPattern((short)1);
      Font f = wb.createFont();
      f.setBoldweight((short)700);
      style.setFont(f);
      
      HSSFRow row = sheet.createRow(0);
      
      row = ScreenSettingUtils.buildHeaderforExportToExcelAgencyRedemption(row, this.user, "AGENCY_REDEMPTION__SCREEN", style);
      sheet = ScreenSettingUtils.buildDataforExportToExcelAgencyRedemption(sheet, this.user, "AGENCY_REDEMPTION__SCREEN", agencyRedList);
      
      FileOutputStream fileOut = new FileOutputStream(Constant.getValue("ATTACHMENT_PATH") + task.getBulkuploadtaskid() + "_" + this.user.getUserId() + "_" + "agencyredumption.xls");
      wb.write(fileOut);
      fileOut.close();
      task.setUploadedFileName(task.getBulkuploadtaskid() + "_" + this.user.getUserId() + "_" + "agencyredumption.xls");
      task.setStatus(Common.BULK_UPLOAD_STATUS_COMPLETED);
      logger.info("exportAgencyRedumptionSearch end");
    }
    catch (Exception e)
    {
      logger.info("Error on exportAgencyRedumptionSearch" + e);
      String error = "Error on exportAgencyRedumptionSearch:" + e.getMessage();
      if (error.length() > 999) {
        error = error.substring(0, 999);
      }
      task.setErrorDesc(error);
      task.setStatus(Common.BULK_UPLOAD_STATUS_FAIL);
    }
    return task;
  }
  
  private BulkUploadTask exportReferalRedumptionSearch(BulkUploadTask task)
  {
    logger.info("exportReferalRedumptionSearch start");
    try
    {
      String datepattern = Constant.getValue("defaultdateformat");
      if (this.user != null) {
        datepattern = DateUtil.getDatePatternFormat(this.user.getLocale());
      }
      HSSFWorkbook wb = new HSSFWorkbook();
      List referalRedList = new ArrayList();
      
      referalRedList = BOFactory.getRefBO().exportReferalRedumptionSearch(this.user, this.referalRedumptionSearchCriteria);
      

      HSSFSheet sheet = wb.createSheet("Referal redemptions");
      

      CellStyle style = wb.createCellStyle();
      style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
      style.setFillPattern((short)1);
      Font f = wb.createFont();
      f.setBoldweight((short)700);
      style.setFont(f);
      
      HSSFRow row = sheet.createRow(0);
      
      row = ScreenSettingUtils.buildHeaderforExportToExcelReferralRedemption(row, this.user, "REFERRAL_REDEMPTION_SCREEN", style);
      sheet = ScreenSettingUtils.buildDataforExportToExcelReferralRedemption(sheet, this.user, "REFERRAL_REDEMPTION_SCREEN", referalRedList);
      


      FileOutputStream fileOut = new FileOutputStream(Constant.getValue("ATTACHMENT_PATH") + task.getBulkuploadtaskid() + "_" + this.user.getUserId() + "_" + "referalredumption.xls");
      wb.write(fileOut);
      fileOut.close();
      task.setUploadedFileName(task.getBulkuploadtaskid() + "_" + this.user.getUserId() + "_" + "referalredumption.xls");
      task.setStatus(Common.BULK_UPLOAD_STATUS_COMPLETED);
      logger.info("exportReferalRedumptionSearch end");
    }
    catch (Exception e)
    {
      logger.info("Error on exportReferalRedumptionSearch" + e);
      String error = "Error on exportReferalRedumptionSearch:" + e.getMessage();
      if (error.length() > 999) {
        error = error.substring(0, 999);
      }
      task.setErrorDesc(error);
      task.setStatus(Common.BULK_UPLOAD_STATUS_FAIL);
    }
    return task;
  }
  
  private BulkUploadTask exportMyRequistionSearch(BulkUploadTask task)
  {
    logger.info("exportMyRequistionSearch start");
    try
    {
      String datepattern = Constant.getValue("defaultdateformat");
      if (this.user != null) {
        datepattern = DateUtil.getDatePatternFormat(this.user.getLocale());
      }
      HSSFWorkbook wb = new HSSFWorkbook();
      List jobreqList = new ArrayList();
      if (task.getTasktype().equals(Common.EXPORT_MYREQUISTIONS_HIRING_MANAGER_SEARCH_RESULTS))
      {
        jobreqList = BOFactory.getJobRequistionBO().getSearchJobMyRequisitionHigiringManagerforExport(getUser().getUserId(), getUser().getUserName(), getReqserachCriteria());
      }
      else if (task.getTasktype().equals(Common.EXPORT_MYREQUISTIONS_RECRUITER_SEARCH_RESULTS))
      {
        jobreqList = BOFactory.getJobRequistionBO().getSearchJobMyRequisitionRecruiterforExport(getUser().getUserId(), getReqserachCriteria());
      }
      else if (task.getTasktype().equals(Common.EXPORT_REQUISTIONS_SEARCH_RESULTS))
      {
        jobreqList = BOFactory.getJobRequistionBO().getSearchJobRequisitionforExport(getReqserachCriteria());
      }
      else if (task.getTasktype().equals(Common.EXPORT_MYREQUISTIONS_SEARCH_RESULTS))
      {
        RequisitionSearchCriteriaMultiple searchCriterian = getReqserachCriteria();
        List<Long> orgIdList = new ArrayList();
        orgIdList.add(Long.valueOf(getUser().getOrganization().getOrgId()));
        searchCriterian.setOrgIdList(orgIdList);
        logger.info("searchCriterian" + searchCriterian);
        jobreqList = BOFactory.getJobRequistionBO().getSearchJobRequisitionforExport(searchCriterian);
      }
      logger.info("jobreqList size()" + jobreqList.size());
      

      HSSFSheet sheet = wb.createSheet("Requistions");
      

      CellStyle style = wb.createCellStyle();
      style.setFillForegroundColor(IndexedColors.TEAL.getIndex());
      style.setFillPattern((short)1);
      
      HSSFRow row = sheet.createRow(0);
      
      HSSFCell cell = row.createCell(0);
      cell.setCellValue("Requisition Id");
      
      row.createCell(1).setCellValue("Requisition Name");
      row.createCell(2).setCellValue("Job Title");
      row.createCell(3).setCellValue("Job Code");
      row.createCell(4).setCellValue("Organization");
      row.createCell(5).setCellValue("Department");
      row.createCell(6).setCellValue("Location");
      row.createCell(7).setCellValue("Project Code");
      row.createCell(8).setCellValue("Hiring manager");
      row.createCell(9).setCellValue("Job Grade");
      row.createCell(10).setCellValue("Salary Plan");
      row.createCell(11).setCellValue("Budget code");
      row.createCell(12).setCellValue("Is New Position");
      row.createCell(13).setCellValue("Publish Date");
      row.createCell(14).setCellValue("Target Finish Date");
      row.createCell(15).setCellValue("No of Opening");
      row.createCell(16).setCellValue("No of Remaining");
      row.createCell(17).setCellValue("Job Type");
      row.createCell(18).setCellValue("Work Shift");
      row.createCell(19).setCellValue("Status");
      row.createCell(20).setCellValue("State");
      row.createCell(21).setCellValue("Recruiter");
      









      int k = 1;
      for (int j = 0; j < jobreqList.size(); j++)
      {
        JobRequisition jb = (JobRequisition)jobreqList.get(j);
        row = sheet.createRow(k);
        row.createCell(0).setCellValue(jb.getJobreqId());
        row.createCell(1).setCellValue(jb.getJobreqName());
        row.createCell(2).setCellValue(jb.getJobTitle());
        row.createCell(3).setCellValue(jb.getJobreqcode());
        row.createCell(4).setCellValue(jb.getOrganization().getOrgName());
        if (jb.getOrganization() != null) {
          row.createCell(4).setCellValue(jb.getOrganization().getOrgName());
        } else {
          row.createCell(4).setCellValue("");
        }
        if (jb.getDepartment() != null) {
          row.createCell(5).setCellValue(jb.getDepartment().getDepartmentName());
        } else {
          row.createCell(5).setCellValue("");
        }
        if (jb.getLocation() != null) {
          row.createCell(6).setCellValue(jb.getLocation().getLocationName());
        } else {
          row.createCell(6).setCellValue("");
        }
        if (jb.getProjectcode() != null) {
          row.createCell(7).setCellValue(jb.getProjectcode().getProjCode());
        } else {
          row.createCell(7).setCellValue("");
        }
        if (jb.getHiringmgr() != null) {
          row.createCell(8).setCellValue(jb.getHiringmgr().getFirstName() + " " + jb.getHiringmgr().getLastName());
        } else {
          row.createCell(8).setCellValue("");
        }
        if (jb.getJobgrade() != null) {
          row.createCell(9).setCellValue(jb.getJobgrade().getJobGradeName());
        } else {
          row.createCell(9).setCellValue("");
        }
        if (jb.getSalaryplan() != null) {
          row.createCell(10).setCellValue(jb.getSalaryplan().getSalaryPlanName());
        } else {
          row.createCell(10).setCellValue("");
        }
        if (jb.getBudgetcode() != null) {
          row.createCell(11).setCellValue(jb.getBudgetcode().getBudgetCentreName());
        } else {
          row.createCell(11).setCellValue("");
        }
        if (jb.getIsnewPositions() != null) {
          row.createCell(12).setCellValue(jb.getIsnewPositions().equals("Y") ? "Yes" : "No");
        } else {
          row.createCell(12).setCellValue("");
        }
        if (jb.getPublishedDate() != null) {
          row.createCell(13).setCellValue(DateUtil.convertDateToStringDate(jb.getPublishedDate(), datepattern));
        } else {
          row.createCell(13).setCellValue("");
        }
        if (jb.getTargetfinishdate() != null) {
          row.createCell(14).setCellValue(DateUtil.convertDateToStringDate(jb.getTargetfinishdate(), datepattern));
        } else {
          row.createCell(14).setCellValue("");
        }
        row.createCell(15).setCellValue(jb.getNumberOfOpening());
        row.createCell(16).setCellValue(jb.getNumberOfOpeningRemain());
        if (jb.getJobtype() != null) {
          row.createCell(17).setCellValue(jb.getJobtype().getJobTypeName());
        } else {
          row.createCell(17).setCellValue("");
        }
        if (jb.getWorkshift() != null) {
          row.createCell(18).setCellValue(jb.getWorkshift().getShiftName());
        } else {
          row.createCell(18).setCellValue("");
        }
        row.createCell(19).setCellValue(jb.getStatus());
        row.createCell(20).setCellValue(jb.getState());
        if (jb.getRecruiterName() != null) {
          row.createCell(21).setCellValue(jb.getRecruiterName());
        } else {
          row.createCell(21).setCellValue("");
        }
        k++;
      }
      FileOutputStream fileOut = new FileOutputStream(Constant.getValue("ATTACHMENT_PATH") + task.getBulkuploadtaskid() + "_" + this.user.getUserId() + "_" + "myrequistions.xls");
      wb.write(fileOut);
      fileOut.close();
      

      task.setUploadedFileName(task.getBulkuploadtaskid() + "_" + this.user.getUserId() + "_" + "myrequistions.xls");
      task.setStatus(Common.BULK_UPLOAD_STATUS_COMPLETED);
      logger.info("exportRequistionSearch end");
    }
    catch (Exception e)
    {
      logger.info("Error on exportMyRequistionSearch" + e);
      String error = "Error on exportMyRequistionSearch:" + e.getMessage();
      if (error.length() > 999) {
        error = error.substring(0, 999);
      }
      task.setErrorDesc(error);
      task.setStatus(Common.BULK_UPLOAD_STATUS_FAIL);
    }
    return task;
  }
  
  public String getTasktype()
  {
    return this.tasktype;
  }
  
  public void setTasktype(String tasktype)
  {
    this.tasktype = tasktype;
  }
  
  public User getUser()
  {
    return this.user;
  }
  
  public void setUser(User user)
  {
    this.user = user;
  }
  
  public BulkUploadTask getTask()
  {
    return this.task;
  }
  
  public void setTask(BulkUploadTask task)
  {
    this.task = task;
  }
  
  public List getReqList()
  {
    return this.reqList;
  }
  
  public void setReqList(List reqList)
  {
    this.reqList = reqList;
  }
  
  public String getCri()
  {
    return this.cri;
  }
  
  public void setCri(String cri)
  {
    this.cri = cri;
  }
  
  public String getExperience()
  {
    return this.experience;
  }
  
  public void setExperience(String experience)
  {
    this.experience = experience;
  }
  
  public String getEducation()
  {
    return this.education;
  }
  
  public void setEducation(String education)
  {
    this.education = education;
  }
  
  public String getCertification()
  {
    return this.certification;
  }
  
  public void setCertification(String certification)
  {
    this.certification = certification;
  }
  
  public String getTargetjoiningdate()
  {
    return this.targetjoiningdate;
  }
  
  public void setTargetjoiningdate(String targetjoiningdate)
  {
    this.targetjoiningdate = targetjoiningdate;
  }
  
  public String getInterviewstate()
  {
    return this.interviewstate;
  }
  
  public void setInterviewstate(String interviewstate)
  {
    this.interviewstate = interviewstate;
  }
  
  public ResuistionSearchCriteria getReqCriteria()
  {
    return this.reqCriteria;
  }
  
  public void setReqCriteria(ResuistionSearchCriteria reqCriteria)
  {
    this.reqCriteria = reqCriteria;
  }
  
  public ApplicantSearchCriteria getApplicantSearchCriteria()
  {
    return this.applicantSearchCriteria;
  }
  
  public void setApplicantSearchCriteria(ApplicantSearchCriteria applicantSearchCriteria)
  {
    this.applicantSearchCriteria = applicantSearchCriteria;
  }
  
  public String getSearcherrole()
  {
    return this.searcherrole;
  }
  
  public void setSearcherrole(String searcherrole)
  {
    this.searcherrole = searcherrole;
  }
  
  public AgencyRedumptionSearchCriteria getAgencyRedumptionSearchCriteria()
  {
    return this.agencyRedumptionSearchCriteria;
  }
  
  public void setAgencyRedumptionSearchCriteria(AgencyRedumptionSearchCriteria agencyRedumptionSearchCriteria)
  {
    this.agencyRedumptionSearchCriteria = agencyRedumptionSearchCriteria;
  }
  
  public ReferalRedumptionSearchCriteria getReferalRedumptionSearchCriteria()
  {
    return this.referalRedumptionSearchCriteria;
  }
  
  public void setReferalRedumptionSearchCriteria(ReferalRedumptionSearchCriteria referalRedumptionSearchCriteria)
  {
    this.referalRedumptionSearchCriteria = referalRedumptionSearchCriteria;
  }
  
  public RequisitionSearchCriteriaMultiple getReqserachCriteria()
  {
    return this.reqserachCriteria;
  }
  
  public void setReqserachCriteria(RequisitionSearchCriteriaMultiple reqserachCriteria)
  {
    this.reqserachCriteria = reqserachCriteria;
  }
}
