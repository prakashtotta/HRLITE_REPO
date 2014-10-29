package com.util;

import com.bean.BulkUploadTask;
import com.bean.Country;
import com.bean.Department;
import com.bean.Designations;
import com.bean.Locale;
import com.bean.Organization;
import com.bean.ProjectCodes;
import com.bean.Role;
import com.bean.Timezone;
import com.bean.User;
import com.bean.testengine.MockTestQuestion;
import com.bo.BOFactory;
import com.bo.UserBO;
import com.common.WSException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XlxsUtil
{
  protected static final Logger logger = Logger.getLogger(XlxsUtil.class);
  
  public static enum HeaderNames
  {
    Employee_Code,  First_Name,  Middle_Name,  Last_Name,  Email,  Office_Phone,  Organization_Code,  Department_Code,  Project_code,  Nationality,  Designation_Code,  Role_Code,  SSN_Number,  Locale_Code,  Timezone_Code,  User_Name,  Password;
    
    private HeaderNames() {}
  }
  
  public static enum Exam_HeaderNames
  {
    question_text,  option_1,  option_2,  option_3,  option_4,  explanation,  answer;
    
    private Exam_HeaderNames() {}
  }
  
  public List<MockTestQuestion> readExamSheet(String filename, User user, BulkUploadTask task)
    throws Exception
  {
    logger.info("inside ReadSheet" + filename);
    String successList = "";
    String failList = "";
    FileInputStream fis = null;
    List<MockTestQuestion> qnsList = new ArrayList();
    try
    {
      fis = new FileInputStream(filename);
      


      XSSFWorkbook workbook = new XSSFWorkbook(fis);
      
      XSSFSheet sheet = workbook.getSheetAt(0);
      
      Iterator rows = sheet.rowIterator();
      
      int number = sheet.getLastRowNum();
      
      System.out.println(" number of rows" + number);
      
      Map<Integer, String> headerMap = new HashMap();
      int qnsnumber = 1;
      while (rows.hasNext())
      {
        XSSFRow row = (XSSFRow)rows.next();
        if (row.getRowNum() == 0)
        {
          logger.info("row 0 here");
          


          Iterator cells = row.cellIterator();
          while (cells.hasNext())
          {
            XSSFCell cell = (XSSFCell)cells.next();
            
            String Value = cell.getStringCellValue();
            
            setHeaderNameColumnIndexMap(headerMap, cell.getColumnIndex(), Value);
          }
        }
        else
        {
          logger.info("row other here");
          Iterator cells = row.cellIterator();
          MockTestQuestion question = new MockTestQuestion();
          while (cells.hasNext())
          {
            XSSFCell cell = (XSSFCell)cells.next();
            


            question = setQuestionData(question, cell, headerMap);
          }
          qnsList.add(question);
          question.setQuestionno(qnsnumber);
          qnsnumber++;
        }
      }
    }
    catch (IOException e)
    {
      logger.info("exception on readSheet", e);
      failList = e.getMessage();
    }
    finally
    {
      if (fis != null) {
        fis.close();
      }
    }
    task.setErrorDesc(failList);
    return qnsList;
  }
  
  public void readSheet(String filename, User user, BulkUploadTask task)
    throws Exception
  {
    logger.info("inside ReadSheet" + filename);
    String successList = "";
    String failList = "";
    FileInputStream fis = null;
    try
    {
      fis = new FileInputStream(filename);
      


      XSSFWorkbook workbook = new XSSFWorkbook(fis);
      
      XSSFSheet sheet = workbook.getSheetAt(0);
      
      Iterator rows = sheet.rowIterator();
      
      int number = sheet.getLastRowNum();
      
      System.out.println(" number of rows" + number);
      
      Map<Integer, String> headerMap = new HashMap();
      while (rows.hasNext())
      {
        XSSFRow row = (XSSFRow)rows.next();
        if (row.getRowNum() == 0)
        {
          logger.info("row 0 here");
          


          Iterator cells = row.cellIterator();
          while (cells.hasNext())
          {
            XSSFCell cell = (XSSFCell)cells.next();
            
            String Value = cell.getStringCellValue();
            
            setHeaderNameColumnIndexMap(headerMap, cell.getColumnIndex(), Value);
          }
        }
        else
        {
          logger.info("row other here");
          Iterator cells = row.cellIterator();
          User userData = new User();
          while (cells.hasNext())
          {
            XSSFCell cell = (XSSFCell)cells.next();
            


            userData = setUserData(userData, cell, headerMap);
          }
          try
          {
            BOFactory.getUserBO().addUser(user, userData);
            successList = successList + userData.getEmployeecode() + " | " + "\n";
          }
          catch (WSException e)
          {
            logger.info("exception on add user" + e.getErrorCode() + e.getErrorDescription());
            failList = failList + userData.getEmployeecode() + " : " + e.getErrorCode() + " " + e.getErrorDescription() + " | " + "\n";
          }
        }
      }
      task.setSuccessfilesList(successList);
      task.setFailfilesList(failList);
    }
    catch (IOException e)
    {
      logger.info("exception on readSheet", e);
    }
    finally
    {
      if (fis != null) {
        fis.close();
      }
    }
  }
  
  private MockTestQuestion setQuestionData(MockTestQuestion question, XSSFCell cell, Map<Integer, String> headerMap)
  {
    logger.info(Integer.valueOf(cell.getColumnIndex()));
    
    String headerName = getHeaderNameColumnIndexMap(headerMap, cell.getColumnIndex());
    logger.info("column:" + cell.getColumnIndex() + " header:" + headerName);
    if ((headerName != null) && (headerName.equalsIgnoreCase(Exam_HeaderNames.question_text.toString())))
    {
      String Value = "";
      if (cell.getCellType() == 1) {
        Value = cell.getStringCellValue();
      }
      if (cell.getCellType() == 0)
      {
        double value = cell.getNumericCellValue();
        
        int val1 = (int)value;Value = String.valueOf(val1);
      }
      question.setQuestion(Value);
    }
    else if ((headerName != null) && (headerName.equalsIgnoreCase(Exam_HeaderNames.option_1.toString())))
    {
      String Value = "";
      if (cell.getCellType() == 1) {
        Value = cell.getStringCellValue();
      }
      if (cell.getCellType() == 0)
      {
        double value = cell.getNumericCellValue();
        int val1 = (int)value;Value = String.valueOf(val1);
      }
      question.setAns1(Value);
    }
    else if ((headerName != null) && (headerName.equalsIgnoreCase(Exam_HeaderNames.option_2.toString())))
    {
      String Value = "";
      if (cell.getCellType() == 1) {
        Value = cell.getStringCellValue();
      }
      if (cell.getCellType() == 0)
      {
        double value = cell.getNumericCellValue();
        
        int val1 = (int)value;Value = String.valueOf(val1);
      }
      question.setAns2(Value);
    }
    else if ((headerName != null) && (headerName.equalsIgnoreCase(Exam_HeaderNames.option_3.toString())))
    {
      String Value = "";
      if (cell.getCellType() == 1) {
        Value = cell.getStringCellValue();
      }
      if (cell.getCellType() == 0)
      {
        double value = cell.getNumericCellValue();
        
        int val1 = (int)value;Value = String.valueOf(val1);
      }
      question.setAns3(Value);
    }
    else if ((headerName != null) && (headerName.equalsIgnoreCase(Exam_HeaderNames.option_4.toString())))
    {
      String Value = "";
      if (cell.getCellType() == 1) {
        Value = cell.getStringCellValue();
      }
      if (cell.getCellType() == 0)
      {
        double value = cell.getNumericCellValue();
        
        int val1 = (int)value;Value = String.valueOf(val1);
      }
      question.setAns4(Value);
    }
    else if ((headerName != null) && (headerName.equalsIgnoreCase(Exam_HeaderNames.explanation.toString())))
    {
      String Value = "";
      if (cell.getCellType() == 1) {
        Value = cell.getStringCellValue();
      }
      if (cell.getCellType() == 0)
      {
        double value = cell.getNumericCellValue();
        
        int val1 = (int)value;Value = String.valueOf(val1);
      }
      question.setDesc(Value);
    }
    else if ((headerName != null) && (headerName.equalsIgnoreCase(Exam_HeaderNames.answer.toString())))
    {
      String Value = "";
      if (cell.getCellType() == 1) {
        Value = cell.getStringCellValue();
      }
      if (cell.getCellType() == 0)
      {
        double value = cell.getNumericCellValue();
        
        int val1 = (int)value;Value = String.valueOf(val1);
      }
      question.setCorrect(new Integer(Value).intValue());
    }
    return question;
  }
  
  private User setUserData(User user, XSSFCell cell, Map<Integer, String> headerMap)
  {
    logger.info(Integer.valueOf(cell.getColumnIndex()));
    
    String headerName = getHeaderNameColumnIndexMap(headerMap, cell.getColumnIndex());
    logger.info("column:" + cell.getColumnIndex() + " header:" + headerName);
    if ((headerName != null) && (headerName.equalsIgnoreCase(HeaderNames.Employee_Code.toString())))
    {
      String Value = "";
      if (cell.getCellType() == 1) {
        Value = cell.getStringCellValue();
      }
      if (cell.getCellType() == 0)
      {
        double value = cell.getNumericCellValue();
        int val1 = (int)value;Value = String.valueOf(val1);
      }
      user.setEmployeecode(Value);
    }
    else if ((headerName != null) && (headerName.equalsIgnoreCase(HeaderNames.First_Name.toString())))
    {
      String Value = "";
      if (cell.getCellType() == 1) {
        Value = cell.getStringCellValue();
      }
      if (cell.getCellType() == 0)
      {
        double value = cell.getNumericCellValue();
        int val1 = (int)value;Value = String.valueOf(val1);
      }
      user.setFirstName(Value);
    }
    else if ((headerName != null) && (headerName.equalsIgnoreCase(HeaderNames.Middle_Name.toString())))
    {
      String Value = "";
      if (cell.getCellType() == 1) {
        Value = cell.getStringCellValue();
      }
      if (cell.getCellType() == 0)
      {
        double value = cell.getNumericCellValue();
        
        int val1 = (int)value;Value = String.valueOf(val1);
      }
      user.setMiddleName(Value);
    }
    else if ((headerName != null) && (headerName.equalsIgnoreCase(HeaderNames.Last_Name.toString())))
    {
      String Value = "";
      if (cell.getCellType() == 1) {
        Value = cell.getStringCellValue();
      }
      if (cell.getCellType() == 0)
      {
        double value = cell.getNumericCellValue();
        
        int val1 = (int)value;Value = String.valueOf(val1);
      }
      user.setLastName(Value);
    }
    else if ((headerName != null) && (headerName.equalsIgnoreCase(HeaderNames.Email.toString())))
    {
      String Value = "";
      if (cell.getCellType() == 1) {
        Value = cell.getStringCellValue();
      }
      if (cell.getCellType() == 0)
      {
        double value = cell.getNumericCellValue();
        
        int val1 = (int)value;Value = String.valueOf(val1);
      }
      user.setEmailId(Value);
    }
    else if ((headerName != null) && (headerName.equalsIgnoreCase(HeaderNames.Office_Phone.toString())))
    {
      String Value = "";
      if (cell.getCellType() == 1) {
        Value = cell.getStringCellValue();
      }
      if (cell.getCellType() == 0)
      {
        double value = cell.getNumericCellValue();
        
        int val1 = (int)value;Value = String.valueOf(val1);
      }
      user.setPhoneOffice(Value);
    }
    else if ((headerName != null) && (headerName.equalsIgnoreCase(HeaderNames.Organization_Code.toString())))
    {
      String Value = "";
      if (cell.getCellType() == 1) {
        Value = cell.getStringCellValue();
      }
      if (cell.getCellType() == 0)
      {
        double value = cell.getNumericCellValue();
        
        int val1 = (int)value;Value = String.valueOf(val1);
      }
      if (!StringUtils.isNullOrEmpty(Value))
      {
        Organization organization = new Organization();
        organization.setOrgCode(Value);
        user.setOrganization(organization);
      }
    }
    else if ((headerName != null) && (headerName.equalsIgnoreCase(HeaderNames.Department_Code.toString())))
    {
      String Value = "";
      if (cell.getCellType() == 1) {
        Value = cell.getStringCellValue();
      }
      if (cell.getCellType() == 0)
      {
        double value = cell.getNumericCellValue();
        
        int val1 = (int)value;Value = String.valueOf(val1);
      }
      if (!StringUtils.isNullOrEmpty(Value))
      {
        Department dept = new Department();
        dept.setDepartmentCode(Value);
        user.setDepartment(dept);
      }
    }
    else if ((headerName != null) && (headerName.equalsIgnoreCase(HeaderNames.Project_code.toString())))
    {
      String Value = "";
      if (cell.getCellType() == 1) {
        Value = cell.getStringCellValue();
      }
      if (cell.getCellType() == 0)
      {
        double value = cell.getNumericCellValue();
        
        int val1 = (int)value;Value = String.valueOf(val1);
      }
      if (!StringUtils.isNullOrEmpty(Value))
      {
        ProjectCodes pjcode = new ProjectCodes();
        pjcode.setProjCode(Value);
        user.setProjectcode(pjcode);
      }
    }
    else if ((headerName != null) && (headerName.equalsIgnoreCase(HeaderNames.Nationality.toString())))
    {
      String Value = "";
      if (cell.getCellType() == 1) {
        Value = cell.getStringCellValue();
      }
      if (cell.getCellType() == 0)
      {
        double value = cell.getNumericCellValue();
        
        int val1 = (int)value;Value = String.valueOf(val1);
      }
      if (!StringUtils.isNullOrEmpty(Value))
      {
        Country nationality = new Country();
        nationality.setCountryName(Value);
        user.setNationality(nationality);
      }
    }
    else if ((headerName != null) && (headerName.equalsIgnoreCase(HeaderNames.Designation_Code.toString())))
    {
      String Value = "";
      if (cell.getCellType() == 1) {
        Value = cell.getStringCellValue();
      }
      if (cell.getCellType() == 0)
      {
        double value = cell.getNumericCellValue();
        
        int val1 = (int)value;Value = String.valueOf(val1);
      }
      if (!StringUtils.isNullOrEmpty(Value))
      {
        Designations designation = new Designations();
        designation.setDesignationCode(Value);
        user.setDesignation(designation);
      }
    }
    else if ((headerName != null) && (headerName.equalsIgnoreCase(HeaderNames.Role_Code.toString())))
    {
      String Value = "";
      if (cell.getCellType() == 1) {
        Value = cell.getStringCellValue();
      }
      if (cell.getCellType() == 0)
      {
        double value = cell.getNumericCellValue();
        
        int val1 = (int)value;Value = String.valueOf(val1);
      }
      if (!StringUtils.isNullOrEmpty(Value))
      {
        Role role = new Role();
        role.setRoleCode(Value);
        user.setRole(role);
      }
    }
    else if ((headerName != null) && (headerName.equalsIgnoreCase(HeaderNames.SSN_Number.toString())))
    {
      String Value = "";
      if (cell.getCellType() == 1) {
        Value = cell.getStringCellValue();
      }
      if (cell.getCellType() == 0)
      {
        double value = cell.getNumericCellValue();
        
        int val1 = (int)value;Value = String.valueOf(val1);
      }
      user.setSsnNumber(Value);
    }
    else if ((headerName != null) && (headerName.equalsIgnoreCase(HeaderNames.Locale_Code.toString())))
    {
      String Value = "";
      if (cell.getCellType() == 1) {
        Value = cell.getStringCellValue();
      }
      if (cell.getCellType() == 0)
      {
        double value = cell.getNumericCellValue();
        
        int val1 = (int)value;Value = String.valueOf(val1);
      }
      if (!StringUtils.isNullOrEmpty(Value))
      {
        Locale lo = new Locale();
        lo.setLocaleCode(Value);
        user.setLocale(lo);
      }
    }
    else if ((headerName != null) && (headerName.equalsIgnoreCase(HeaderNames.Timezone_Code.toString())))
    {
      String Value = "";
      if (cell.getCellType() == 1) {
        Value = cell.getStringCellValue();
      }
      if (cell.getCellType() == 0)
      {
        double value = cell.getNumericCellValue();
        
        int val1 = (int)value;Value = String.valueOf(val1);
      }
      if (!StringUtils.isNullOrEmpty(Value))
      {
        Timezone tz = new Timezone();
        tz.setTimezoneCode(Value);
        user.setTimezone(tz);
      }
    }
    else if ((headerName != null) && (headerName.equalsIgnoreCase(HeaderNames.User_Name.toString())))
    {
      String Value = "";
      if (cell.getCellType() == 1) {
        Value = cell.getStringCellValue();
      }
      if (cell.getCellType() == 0)
      {
        double value = cell.getNumericCellValue();
        
        int val1 = (int)value;Value = String.valueOf(val1);
      }
      user.setUserName(Value);
    }
    else if ((headerName != null) && (headerName.equalsIgnoreCase(HeaderNames.Password.toString())))
    {
      String Value = "";
      if (cell.getCellType() == 1) {
        Value = cell.getStringCellValue();
      }
      if (cell.getCellType() == 0)
      {
        double value = cell.getNumericCellValue();
        
        int val1 = (int)value;Value = String.valueOf(val1);
      }
      if (!StringUtils.isNullOrEmpty(Value)) {
        user.setPassword(EncryptDecrypt.encrypt(Value));
      }
    }
    return user;
  }
  
  private void setHeaderNameColumnIndexMap(Map<Integer, String> headerMap, int index, String headerColumnName)
  {
    headerMap.put(Integer.valueOf(index), headerColumnName);
  }
  
  private String getHeaderNameColumnIndexMap(Map<Integer, String> headerMap, int index)
  {
    return (String)headerMap.get(Integer.valueOf(index));
  }
}
