package com.util;

import com.bean.ApplicantAttachments;
import com.bean.ApplicantOtherDetails;
import com.bean.BulkUploadTask;
import com.bean.JobApplicant;
import com.bean.JobRequisition;
import com.bean.User;
import com.bean.testengine.MockTestQuestion;
import com.bo.ApplicantBO;
import com.bo.ApplicantTXBO;
import com.bo.BOFactory;
import com.bo.JobRequistionBO;
import com.common.Common;
import com.dao.TaskDAO;
import com.resources.Constant;
import com.test.MockTestBO;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.io.Writer;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.sql.rowset.serial.SerialBlob;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.log4j.Logger;
import org.apache.poi.POITextExtractor;
import org.apache.poi.extractor.ExtractorFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class BulkUploadTaskUtil
  implements Runnable
{
  protected static final Logger logger = Logger.getLogger(BulkUploadTaskUtil.class);
  String tasktype;
  User user;
  BulkUploadTask task;
  private int examId;
  
  public void run()
  {
    logger.info("run method bulk upload start");
    try
    {
      if ((!StringUtils.isNullOrEmpty(this.tasktype)) && (this.tasktype.equals(Common.BULK_UPLOAD_RESUME)))
      {
        logger.info("bulk upload for " + this.tasktype);
        this.task.setStatus(Common.BULK_UPLOAD_STATUS_RUNNING);
        this.task.setCreatedBy(this.user.getFirstName() + " " + this.user.getLastName());
        this.task.setCreatedDate(new Date());
        this.task.setTasktype(this.tasktype);
        this.task.setCreatedById(this.user.getUserId());
        JobRequisition jb = BOFactory.getJobRequistionBO().getJobRequision(String.valueOf(this.task.getJobreqId()));
        this.task.setJobreqId(jb.getJobreqId());
        this.task.setJobTitle(jb.getJobTitle());
        
        this.task = TaskDAO.saveBulkUploadTask(this.task);
        logger.info("task.getBulkuploadtaskid()" + this.task.getBulkuploadtaskid());
        this.task = uploadApplicantsProfiles(this.task, getUser(), jb);
        
        this.task.setUpdatedBy(this.user.getFirstName() + " " + this.user.getLastName());
        this.task.setUpdatedDate(new Date());
        this.task = TaskDAO.updateBulkUploadTask(this.task);
      }
      if ((!StringUtils.isNullOrEmpty(this.tasktype)) && (this.tasktype.equals(Common.BULK_UPLOAD_USERS)))
      {
        logger.info("bulk upload for " + this.tasktype);
        this.task.setStatus(Common.BULK_UPLOAD_STATUS_RUNNING);
        this.task.setCreatedBy(this.user.getFirstName() + " " + this.user.getLastName());
        this.task.setCreatedDate(new Date());
        this.task.setTasktype(this.tasktype);
        this.task.setCreatedById(this.user.getUserId());
        
        this.task = TaskDAO.saveBulkUploadTask(this.task);
        logger.info("task.getBulkuploadtaskid()" + this.task.getBulkuploadtaskid());
        this.task = uploadUserProfiles(this.task, getUser());
        
        this.task.setUpdatedBy(this.user.getFirstName() + " " + this.user.getLastName());
        this.task.setUpdatedDate(new Date());
        



        createTaskErrorFile(this.task);
        this.task.setSuccessfilesList("");
        this.task.setFailfilesList("");
        this.task = TaskDAO.updateBulkUploadTask(this.task);
      }
      if ((!StringUtils.isNullOrEmpty(this.tasktype)) && (this.tasktype.equals(Common.BULK_UPLOAD_EXAM)))
      {
        logger.info("bulk upload for " + this.tasktype);
        try
        {
          this.task.setStatus(Common.BULK_UPLOAD_STATUS_RUNNING);
          this.task.setCreatedBy(this.user.getFirstName() + " " + this.user.getLastName());
          this.task.setCreatedDate(new Date());
          this.task.setTasktype(this.tasktype);
          this.task.setCreatedById(this.user.getUserId());
          
          this.task = TaskDAO.saveBulkUploadTask(this.task);
          logger.info("task.getBulkuploadtaskid()" + this.task.getBulkuploadtaskid());
          this.task = uploadExam(this.task, getUser());
          
          this.task.setUpdatedBy(this.user.getFirstName() + " " + this.user.getLastName());
          this.task.setUpdatedDate(new Date());
          




          this.task.setSuccessfilesList("");
          this.task.setFailfilesList("");
          this.task = TaskDAO.updateBulkUploadTask(this.task);
        }
        catch (Exception e)
        {
          createTaskErrorFileForExam(this.task, this.task.getErrorDesc() + " " + e.toString());
        }
      }
    }
    catch (Exception e)
    {
      logger.info("Error on bulk upload" + e);
    }
    logger.info("run method bulk upload end");
  }
  
  private void createTaskErrorFile(BulkUploadTask task)
  {
    try
    {
      Writer output = null;
      String filePath = Constant.getValue("ATTACHMENT_PATH");
      
      File file = new File(filePath + task.getBulkuploadtaskid() + "_error.txt");
      file.createNewFile();
      
      output = new BufferedWriter(new FileWriter(file));
      output.write(task.getFailfilesList());
      output.close();
      

      output = null;
      

      File file1 = new File(filePath + task.getBulkuploadtaskid() + "_success.txt");
      file1.createNewFile();
      
      output = new BufferedWriter(new FileWriter(file1));
      output.write(task.getSuccessfilesList());
      output.close();
    }
    catch (Exception e)
    {
      logger.info("exception on createTaskErrorFile", e);
    }
  }
  
  private void createTaskErrorFileForExam(BulkUploadTask task, String error)
  {
    try
    {
      Writer output = null;
      String filePath = Constant.getValue("ATTACHMENT_PATH");
      
      File file = new File(filePath + task.getBulkuploadtaskid() + "_error.txt");
      file.createNewFile();
      
      output = new BufferedWriter(new FileWriter(file));
      output.write(error);
      output.close();
    }
    catch (Exception e)
    {
      logger.info("exception on createTaskErrorFileForExam", e);
    }
  }
  
  private BulkUploadTask uploadApplicantsProfiles(BulkUploadTask task, User user, JobRequisition jb)
  {
    logger.info("uploadApplicantsProfiles start");
    

    String successFilelist = "";
    String failFilelist = "";
    String filePath = Constant.getValue("ATTACHMENT_PATH") + user.getUserName() + File.separator;
    try
    {
      String filepathfinal = filePath + task.getUploadedFileName();
      logger.info("filepathfinal" + filepathfinal);
      ZipFile zipFile = new ZipFile(filepathfinal);
      
      Enumeration entries = zipFile.entries();
      while (entries.hasMoreElements())
      {
        ZipEntry entry = (ZipEntry)entries.nextElement();
        logger.info("entry.getName(): " + entry.getName());
        if (entry.isDirectory())
        {
          logger.info("Extracting directory: " + entry.getName());
          
          File dir = new File(filePath + entry.getName());
          dir.mkdir();
        }
        else if ((entry.getName() != null) && (entry.getName().lastIndexOf(".docx") > 0))
        {
          logger.info("entry.getName(): " + entry.getName());
          try
          {
            File f = new File(filePath + entry.getName());
            

            FileOutputStream fout = new FileOutputStream(f);
            InputStream in = zipFile.getInputStream(entry);
            for (int c = in.read(); c != -1; c = in.read()) {
              fout.write(c);
            }
            in.close();
            fout.close();
            





            JobApplicant applicant = new JobApplicant();
            
            applicant = parsedocx(applicant, filePath + entry.getName());
            String resumename = f.getName().substring(f.getName().indexOf("_") + 1);
            applicant.setResumename(resumename);
            applicant.setReqId(jb.getJobreqId());
            applicant.setReqName(jb.getJobreqName());
            applicant.setJobTitle(jb.getJobTitle());
            applicant.setScreenCode("APPLICANT_SCREEN");
            applicant.setSuper_user_key(jb.getSuper_user_key());
            POITextExtractor extractor = ExtractorFactory.createExtractor(f);
            

            String fulltext = extractor.getText();
            
            FileInputStream fin = new FileInputStream(f);
            RandomAccessFile r = new RandomAccessFile(filePath + entry.getName(), "rw");
            String ll = String.valueOf(f.length());
            
            byte[] sizefile = new byte[Integer.parseInt(ll)];
            fin.read(sizefile);
            
            r.write(sizefile);
            
            r.close();
            fin.close();
            

            Blob blob1 = null;
            
            blob1 = new SerialBlob(sizefile);
            
            ApplicantAttachments attach = new ApplicantAttachments();
            attach.setAttachmentdata(blob1);
            attach.setAttahmentname(resumename);
            attach.setUuid(UUID.randomUUID().toString());
            applicant.setApplicantAttachment(attach);
            String fname = f.getName().substring(0, f.getName().lastIndexOf("."));
            fname = fname.substring(fname.indexOf("_") + 1);
            fname = fname.replace("_", " ");
            applicant.setFullName(fname);
            if (applicant.getApplicantAttachment() != null)
            {
              String filePathr = Constant.getValue("ATTACHMENT_PATH");
              filePathr = filePathr + File.separator + "applicantAttachment" + File.separator + applicant.getApplicantAttachment().getUuid() + File.separator;
              File file = new File(filePathr);
              if (!file.exists()) {
                file.mkdirs();
              }
              Blob blob = applicant.getApplicantAttachment().getAttachmentdata();
              RandomAccessFile raf = new RandomAccessFile(filePathr + applicant.getResumename(), "rw");
              

              int length = (int)blob.length();
              byte[] _blob = blob.getBytes(1L, length);
              raf.write(_blob);
              raf.close();
              applicant.setFilePath(filePathr);
              if ((!StringUtils.isNullOrEmpty(Constant.getValue("scribd.enabled"))) && (Constant.getValue("scribd.enabled").equalsIgnoreCase("yes")) && 
                (applicant.getApplicantAttachment().getAttahmentname() != null)) {
                ScribdUtil.uploadFile(applicant.getApplicantAttachment());
              }
            }
            applicant.setOwner(jb.getHiringmgr());
            

            applicant.setCreatedBy(user.getUserName());
            applicant.setCreatedDate(new Date());
            applicant.setUpdatedBy(null);
            applicant.setUpdatedDate(null);
            applicant.setStatus("A");
            applicant.setInterviewState("Application Submitted");
            applicant.setAppliedDate(new Date());
            applicant.setHeighestQualification("");
            applicant.setResumeHeader("");
            applicant.setPhone("");
            applicant.setReferrerName("");
            









            applicant.setUuid(UUID.randomUUID().toString());
            
            JobApplicant oldapplicant = BOFactory.getApplicantBO().isApplicantDuplicate(applicant, applicant.getSuper_user_key());
            logger.info("oldapplicant" + oldapplicant);
            if (oldapplicant == null)
            {
              ApplicantOtherDetails otherdetails = new ApplicantOtherDetails();
              applicant.setOtherdetails(otherdetails);
              applicant = BOFactory.getApplicantTXBO().saveApplicant(applicant, user, null, null, null, Common.APPLICANT_CREATED_BY_TYPE_INTERNAL);
              BOFactory.getApplicantBO().assignApplicantNumberAndJobTitle(applicant);
              BOFactory.getApplicantBO().sendApplicationSubmitionEmail(applicant, user, null);
              successFilelist = successFilelist + entry.getName() + ",";
            }
          }
          catch (Exception e)
          {
            failFilelist = failFilelist + entry.getName() + ",";
            logger.info("Error readinf file:" + entry.getName() + " " + e);
            String error = "Error readinf file:" + entry.getName() + " " + e;
            if (error.length() > 999) {
              error = error.substring(0, 999);
            }
            task.setErrorDesc(error);
          }
        }
        else
        {
          failFilelist = failFilelist + entry.getName() + ",";
        }
      }
      zipFile.close();
      if (!StringUtils.isNullOrEmpty(successFilelist)) {
        successFilelist = successFilelist.substring(0, successFilelist.length() - 1);
      }
      if (!StringUtils.isNullOrEmpty(failFilelist)) {
        failFilelist = failFilelist.substring(0, failFilelist.length() - 1);
      }
      task.setSuccessfilesList(successFilelist);
      task.setFailfilesList(failFilelist);
      task.setFilesList(successFilelist + "," + failFilelist);
      task.setStatus(Common.BULK_UPLOAD_STATUS_COMPLETED);
    }
    catch (IOException ioe)
    {
      logger.info("error on bulk upload applicants profile:" + ioe);
      String error = "error on bulk upload applicants profile:" + ioe.getMessage();
      if (error.length() > 999) {
        error = error.substring(0, 999);
      }
      task.setErrorDesc(error);
      task.setStatus(Common.BULK_UPLOAD_STATUS_FAIL);
    }
    logger.info("uploadApplicantsProfiles end");
    return task;
  }
  
  private BulkUploadTask uploadUserProfiles(BulkUploadTask task, User user)
  {
    logger.info("uploadUserProfiles start");
    

    String successFilelist = "";
    String failFilelist = "";
    String filePath = Constant.getValue("ATTACHMENT_PATH") + user.getUserName() + File.separator;
    try
    {
      String filepathfinal = filePath + task.getUploadedFileName();
      logger.info("filepathfinal" + filepathfinal);
      XlxsUtil xlxutil = new XlxsUtil();
      xlxutil.readSheet(filepathfinal, user, task);
      

      task.setStatus(Common.BULK_UPLOAD_STATUS_COMPLETED);
    }
    catch (Exception ioe)
    {
      logger.info("error on bulk upload user profile:" + ioe);
      String error = "error on bulk upload user profile:" + ioe.getMessage();
      if (error.length() > 999) {
        error = error.substring(0, 999);
      }
      task.setErrorDesc(error);
      task.setStatus(Common.BULK_UPLOAD_STATUS_FAIL);
    }
    logger.info("uploadUserProfiles end");
    return task;
  }
  
  private BulkUploadTask uploadExam(BulkUploadTask task, User user)
  {
    logger.info("uploadExam start");
    

    String successFilelist = "";
    String failFilelist = "";
    String filePath = Constant.getValue("ATTACHMENT_PATH") + user.getUserName() + File.separator;
    try
    {
      String filepathfinal = filePath + task.getUploadedFileName();
      logger.info("filepathfinal" + filepathfinal);
      XlxsUtil xlxutil = new XlxsUtil();
      List<MockTestQuestion> qnslist = xlxutil.readExamSheet(filepathfinal, user, task);
      if (getExamId() != 0)
      {
        BOFactory.getMockTestBO().deleteMockQuestions(getExamId());
        BOFactory.getMockTestBO().addMockQuestion(qnslist, getExamId());
      }
      task.setStatus(Common.BULK_UPLOAD_STATUS_COMPLETED);
    }
    catch (Exception ioe)
    {
      logger.info("error on bulk upload exam:" + ioe);
      String error = "error on bulk upload exam:" + ioe.getMessage();
      if (error.length() > 999) {
        error = error.substring(0, 999);
      }
      task.setErrorDesc(error);
      task.setStatus(Common.BULK_UPLOAD_STATUS_FAIL);
    }
    logger.info("uploadUserProfiles end");
    return task;
  }
  
  private JobApplicant parsedocx(JobApplicant applicant, String filepath)
  {
    ZipFile docxfile = null;
    try
    {
      docxfile = new ZipFile(filepath);
      
      InputStream in = null;
      
      ZipEntry ze = docxfile.getEntry("word/document.xml");
      
      in = docxfile.getInputStream(ze);
      

      Document document = null;
      
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      
      DocumentBuilder builder = factory.newDocumentBuilder();
      
      document = builder.parse(in);
      

      NodeList list = document.getElementsByTagName("w:t");
      
      List<String> content = new ArrayList();
      for (int i = 0; i < list.getLength(); i++)
      {
        Node aNode = list.item(i);
        content.add(aNode.getFirstChild().getNodeValue());
        String temp = aNode.getFirstChild().getNodeValue();
        if (temp.indexOf("@") > 0)
        {
          System.err.println(temp);
          if (temp != null) {
            applicant.setEmail(temp.trim());
          }
        }
      }
      return applicant;
    }
    catch (Exception e)
    {
      e.printStackTrace();
      logger.info("Error reading file." + e);
      return null;
    }
    finally
    {
      try
      {
        docxfile.close();
      }
      catch (IOException ioe)
      {
        logger.info("Exception closing file." + ioe);
      }
    }
  }
  
  public String parsedocxfileReturnString(JobApplicant applicant, String filepath)
  {
    String content = "";
    try
    {
      File f = new File(filepath);
      
      applicant = parsedocxOnlycontent(applicant, filepath);
      POITextExtractor extractor = ExtractorFactory.createExtractor(f);
      

      content = extractor.getText();
    }
    catch (Exception e)
    {
      logger.info("Exception reading file." + e);
    }
    return content;
  }
  
  private JobApplicant parsedocxOnlycontent(JobApplicant applicant, String filepath)
  {
    ZipFile docxfile = null;
    try
    {
      docxfile = new ZipFile(filepath);
      
      InputStream in = null;
      
      ZipEntry ze = docxfile.getEntry("word/document.xml");
      
      in = docxfile.getInputStream(ze);
      

      Document document = null;
      
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      
      DocumentBuilder builder = factory.newDocumentBuilder();
      
      document = builder.parse(in);
      

















      return applicant;
    }
    catch (Exception e)
    {
      e.printStackTrace();
      logger.info("Error reading file." + e);
      return null;
    }
    finally
    {
      try
      {
        docxfile.close();
      }
      catch (IOException ioe)
      {
        logger.info("Exception closing file." + ioe);
      }
    }
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
  
  public int getExamId()
  {
    return this.examId;
  }
  
  public void setExamId(int examId)
  {
    this.examId = examId;
  }
}
