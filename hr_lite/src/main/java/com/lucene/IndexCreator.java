package com.lucene;

import com.bean.ApplicantAttachments;
import com.bean.ApplicantOtherDetails;
import com.bean.CompFrequency;
import com.bean.Department;
import com.bean.FlsaStatus;
import com.bean.JobApplicant;
import com.bean.JobGrade;
import com.bean.JobRequisition;
import com.bean.JobTemplateAccomplishment;
import com.bean.JobTemplateCompetency;
import com.bean.JobType;
import com.bean.Organization;
import com.bean.WorkShift;
import com.bean.lov.Category;
import com.bo.ApplicantBO;
import com.bo.BOFactory;
import com.bo.JobRequistionBO;
import com.common.Common;
import com.resources.Constant;
import com.util.FileExtractor;
import com.util.StringUtils;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.util.List;
import network.bean.FaceBookUser;
import network.bean.FbJobs;
import network.bo.FbUserBO;
import org.apache.log4j.Logger;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriter.MaxFieldLength;
import org.apache.lucene.store.AlreadyClosedException;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;

public class IndexCreator
{
  protected static final Logger logger = Logger.getLogger(IndexCreator.class);
  public static String indexfileDir = Constant.getValue("ATTACHMENT_PATH") + File.separator + "ta_index";
  public static String indexfileDir_lu = Constant.getValue("ATTACHMENT_PATH") + File.separator + "ta_index_done";
  public static String indexfileDirRequistion = Constant.getValue("ATTACHMENT_PATH") + File.separator + "req_ta_index";
  public static String indexfileDirRequistion_lu = Constant.getValue("ATTACHMENT_PATH") + File.separator + "req_ta_index_done";
  public static String indexfileDirFacebookUsers = Constant.getValue("ATTACHMENT_PATH") + File.separator + "fb_ta_index";
  public static String indexfileDirFacebookUsers_lu = Constant.getValue("ATTACHMENT_PATH") + File.separator + "fb_ta_index_done";
  public static String indexfileDirFacebookJobs = Constant.getValue("ATTACHMENT_PATH") + File.separator + "fb_jb_index";
  public static String indexfileDirFacebookJobs_lu = Constant.getValue("ATTACHMENT_PATH") + File.separator + "fb_jb_index_done";
  StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_34);
  StandardAnalyzer analyzer_req = new StandardAnalyzer(Version.LUCENE_34);
  StandardAnalyzer analyzer_fbusers = new StandardAnalyzer(Version.LUCENE_34);
  StandardAnalyzer analyzer_fbjobs = new StandardAnalyzer(Version.LUCENE_34);
  static IndexWriter writer = null;
  static IndexWriter writer_req = null;
  static IndexWriter writer_fbusers = null;
  static IndexWriter writer_fbjobs = null;
  
  public void createIndex()
    throws Exception
  {
    logger.info("inside createIndex");
    

    File INDEX_DIRECTORY = new File(indexfileDir_lu);
    if (!INDEX_DIRECTORY.exists()) {
      new File(indexfileDir_lu).mkdir();
    }
    SimpleFSDirectory d = new SimpleFSDirectory(INDEX_DIRECTORY);
    
    List applicantList = null;
    if (writer == null)
    {
      writer = new IndexWriter(d, this.analyzer, IndexWriter.MaxFieldLength.LIMITED);
      


      applicantList = BOFactory.getApplicantBO().getAllApplicantsForIndexCreation();
    }
    else
    {
      applicantList = BOFactory.getApplicantBO().getAllApplicantsForIndexCreation();
    }
    FileExtractor fileExt = new FileExtractor();
    if ((applicantList != null) && (applicantList.size() > 0)) {
      for (int i = 0; i < applicantList.size(); i++) {
        try
        {
          JobApplicant applicant = (JobApplicant)applicantList.get(i);
          ApplicantOtherDetails otherdetails = BOFactory.getApplicantBO().getApplicantOtherDetails(applicant.getApplicantId());
          applicant.setOtherdetails(otherdetails);
          String inxstring = "";
          

          inxstring = inxstring + " " + (applicant.getCity() == null ? "" : applicant.getCity()) + " " + (applicant.getPrimarySkillOther() == null ? "" : applicant.getPrimarySkillOther()) + " " + (applicant.getHeighestQualification() == null ? "" : applicant.getHeighestQualification()) + " " + (applicant.getPreviousOrganization() == null ? "" : applicant.getPreviousOrganization());
          



          ApplicantAttachments appattach = BOFactory.getApplicantBO().getApplicantAttachments(applicant.getApplicantId(), Common.APPLICANT_ATTACHMENT_TYPE_RESUME);
          if (appattach != null)
          {
            String filePath = Constant.getValue("ATTACHMENT_PATH");
            filePath = filePath + File.separator + "applicantAttachment" + File.separator + appattach.getUuid() + File.separator;
            String content = fileExt.getFileContent(filePath + applicant.getResumename());
            
            inxstring = inxstring + " " + content;
          }
          if (otherdetails != null) {
            inxstring = inxstring + " " + (otherdetails.getResumeDetails() == null ? "" : otherdetails.getResumeDetails());
          }
          if (inxstring.length() > 2000) {}
          inxstring = inxstring.replace(",", " ");
          

























          createFile(applicant.getApplicantId(), inxstring);
          






          ApplicantFilterManager.doApplicantFilteringSilent(applicant);
          

          applicant.setIsindexSearchApplied(1);
          BOFactory.getApplicantBO().updateApplicant(applicant);
        }
        catch (Exception e)
        {
          logger.info("indexing error for applicant", e);
        }
      }
    }
    try
    {
      File FILES_TO_INDEX_DIRECTORY = new File(indexfileDir);
      File[] files = FILES_TO_INDEX_DIRECTORY.listFiles();
      if (files != null) {
        for (File file : files)
        {
          Document document = new Document();
          
          String path = file.getCanonicalPath();
          

          String id = "0";
          if (file.getName() != null) {
            id = file.getName().substring(0, file.getName().indexOf(".txt"));
          }
          byte[] bytes = path.getBytes();
          document.add(new Field("title", id, Field.Store.YES, Field.Index.NO));
          

          Reader reader = new FileReader(file);
          document.add(new Field("content", reader));
          try
          {
            writer.addDocument(document);
          }
          catch (AlreadyClosedException e)
          {
            writer = new IndexWriter(d, this.analyzer, IndexWriter.MaxFieldLength.LIMITED);
            writer.addDocument(document);
          }
        }
      }
      writer.optimize();
      writer.close();
    }
    catch (Exception e)
    {
      logger.info("indexing error", e);
      writer.optimize();
      writer.close();
    }
  }
  
  public void createFile(long appid, String content)
    throws Exception
  {
    File dir = new File(indexfileDir);
    if (!dir.exists()) {
      new File(indexfileDir).mkdir();
    }
    logger.info("directory created" + indexfileDir);
    
    String filename = indexfileDir + File.separator + appid + ".txt";
    File f1 = new File(filename);
    f1.createNewFile();
    FileWriter fstream = new FileWriter(f1);
    BufferedWriter out = new BufferedWriter(fstream);
    out.write(content);
    out.close();
  }
  
  public void createFileRequistion(long id, String content)
    throws Exception
  {
    File dir = new File(indexfileDirRequistion);
    if (!dir.exists()) {
      new File(indexfileDirRequistion).mkdir();
    }
    logger.info("directory created" + indexfileDirRequistion);
    
    String filename = indexfileDirRequistion + File.separator + id + ".txt";
    File f1 = new File(filename);
    f1.createNewFile();
    FileWriter fstream = new FileWriter(f1);
    BufferedWriter out = new BufferedWriter(fstream);
    out.write(content);
    out.close();
  }
  
  public void createFileFaceBookUser(long id, String content)
    throws Exception
  {
    File dir = new File(indexfileDirFacebookUsers);
    if (!dir.exists()) {
      new File(indexfileDirFacebookUsers).mkdir();
    }
    logger.info("directory created" + indexfileDirFacebookUsers);
    
    String filename = indexfileDirFacebookUsers + File.separator + id + ".txt";
    File f1 = new File(filename);
    f1.createNewFile();
    FileWriter fstream = new FileWriter(f1);
    BufferedWriter out = new BufferedWriter(fstream);
    out.write(content);
    out.close();
  }
  
  public void createFileFaceBookJobs(long id, String content)
    throws Exception
  {
    File dir = new File(indexfileDirFacebookJobs);
    if (!dir.exists()) {
      new File(indexfileDirFacebookJobs).mkdir();
    }
    logger.info("directory created" + indexfileDirFacebookJobs);
    
    String filename = indexfileDirFacebookJobs + File.separator + id + ".txt";
    File f1 = new File(filename);
    f1.createNewFile();
    FileWriter fstream = new FileWriter(f1);
    BufferedWriter out = new BufferedWriter(fstream);
    out.write(content);
    out.close();
  }
  
  public void createRequistionIndex()
    throws Exception
  {
    logger.info("inside createRequistionIndex");
    

    File INDEX_DIRECTORY = new File(indexfileDirRequistion_lu);
    if (!INDEX_DIRECTORY.exists()) {
      new File(indexfileDirRequistion_lu).mkdir();
    }
    SimpleFSDirectory d = new SimpleFSDirectory(INDEX_DIRECTORY);
    
    List reqList = null;
    if (writer_req == null)
    {
      writer_req = new IndexWriter(d, this.analyzer_req, IndexWriter.MaxFieldLength.LIMITED);
      
      reqList = BOFactory.getJobRequistionBO().getNonIndexedJobRequistions();
    }
    else
    {
      reqList = BOFactory.getJobRequistionBO().getNonIndexedJobRequistions();
    }
    if ((reqList != null) && (reqList.size() > 0)) {
      for (int i = 0; i < reqList.size(); i++)
      {
        JobRequisition requistion = (JobRequisition)reqList.get(i);
        try
        {
          String inxstring = "";
          


          inxstring = inxstring + requistion.getJobTitle() + " ";
          inxstring = inxstring + requistion.locationValue + " ";
          inxstring = inxstring + requistion.getOrganization().getOrgName() + " ";
          if (requistion.getDepartment() != null) {
            inxstring = inxstring + requistion.getDepartment().getDepartmentName() + " ";
          }
          inxstring = inxstring + requistion.getJobDetails() + " ";
          inxstring = inxstring + requistion.getJobreqcode() + " ";
          if (requistion.getJobgrade() != null) {
            inxstring = inxstring + requistion.getJobgrade().getJobGradeName() + " ";
          }
          if (requistion.getJobtype() != null) {
            inxstring = inxstring + requistion.getJobtype().getJobTypeName() + " ";
          }
          if (requistion.getCatagory() != null) {
            inxstring = inxstring + requistion.getCatagory().getCatName() + " ";
          }
          inxstring = inxstring + requistion.getPrimarySkill() + " ";
          if (!StringUtils.isNullOrEmpty(requistion.getJobreqDesc())) {
            inxstring = inxstring + requistion.getJobreqDesc() + " ";
          }
          if (!StringUtils.isNullOrEmpty(requistion.getJobRoles())) {
            inxstring = inxstring + requistion.getJobRoles() + " ";
          }
          if (!StringUtils.isNullOrEmpty(requistion.getJobInstructions())) {
            inxstring = inxstring + requistion.getJobInstructions() + " ";
          }
          if (!StringUtils.isNullOrEmpty(requistion.getJobDetails())) {
            inxstring = inxstring + requistion.getJobDetails() + " ";
          }
          if (requistion.getWorkshift() != null) {
            inxstring = inxstring + requistion.getWorkshift().getShiftName() + " ";
          }
          if (requistion.getFlsa() != null) {
            inxstring = inxstring + requistion.getFlsa().getFlsaName() + " ";
          }
          if (requistion.getCompfrequency() != null) {
            inxstring = inxstring + requistion.getCompfrequency().getCompFrequencyName() + " ";
          }
          List comptetencyList = BOFactory.getJobRequistionBO().getJobRequisionTemplateComptetencyList(requistion.getJobreqId(), "job");
          if (comptetencyList != null) {
            for (int j = 0; j < comptetencyList.size(); j++)
            {
              JobTemplateCompetency jt = (JobTemplateCompetency)comptetencyList.get(j);
              inxstring = inxstring + jt.getCharName() + " ";
            }
          }
          List accomplishmentList = BOFactory.getJobRequistionBO().getJobRequisionTemplateAccomplishmentList(requistion.getJobreqId(), "job");
          if (accomplishmentList != null) {
            for (int j = 0; j < accomplishmentList.size(); j++)
            {
              JobTemplateAccomplishment jt = (JobTemplateAccomplishment)accomplishmentList.get(j);
              inxstring = inxstring + jt.getAccName() + " ";
            }
          }
          createFileRequistion(requistion.getJobreqId(), inxstring);
          










          ApplicantScoreManager.doApplicantScoring(requistion);
          




          requistion.setIsindexSearchApplied(1);
          BOFactory.getJobRequistionBO().updateJobRequistion(requistion);
        }
        catch (Exception e)
        {
          logger.info("error createRequistionIndex for requistion id:" + requistion.getJobreqId());
          logger.info("error createRequistionIndex for requistition", e);
        }
      }
    }
    try
    {
      File FILES_TO_INDEX_DIRECTORY = new File(indexfileDirRequistion);
      File[] files = FILES_TO_INDEX_DIRECTORY.listFiles();
      if (files != null) {
        for (File file : files)
        {
          Document document = new Document();
          
          String path = file.getCanonicalPath();
          

          String id = "0";
          if (file.getName() != null) {
            id = file.getName().substring(0, file.getName().indexOf(".txt"));
          }
          byte[] bytes = path.getBytes();
          document.add(new Field("title_req", id, Field.Store.YES, Field.Index.NO));
          

          Reader reader = new FileReader(file);
          document.add(new Field("content_req", reader));
          try
          {
            writer_req.addDocument(document);
          }
          catch (AlreadyClosedException e)
          {
            writer_req = new IndexWriter(d, this.analyzer_req, IndexWriter.MaxFieldLength.LIMITED);
            writer_req.addDocument(document);
          }
        }
      }
      writer_req.optimize();
      writer_req.close();
    }
    catch (Exception e)
    {
      writer_req.optimize();
      writer_req.close();
      logger.info("Error on requition index", e);
    }
  }
  
  public void createFacebookUsersIndex()
    throws Exception
  {
    logger.info("inside createFacebookUsersIndex");
    

    File INDEX_DIRECTORY = new File(indexfileDirFacebookUsers_lu);
    if (!INDEX_DIRECTORY.exists()) {
      new File(indexfileDirFacebookUsers_lu).mkdir();
    }
    SimpleFSDirectory d = new SimpleFSDirectory(INDEX_DIRECTORY);
    
    List usersList = null;
    if (writer_fbusers == null)
    {
      writer_fbusers = new IndexWriter(d, this.analyzer_fbusers, IndexWriter.MaxFieldLength.LIMITED);
      
      usersList = BOFactory.getFbUserBO().getAllFbUsers(0);
    }
    else
    {
      usersList = BOFactory.getFbUserBO().getAllFbUsers(0);
    }
    if ((usersList != null) && (usersList.size() > 0)) {
      for (int i = 0; i < usersList.size(); i++)
      {
        FaceBookUser fuser = (FaceBookUser)usersList.get(i);
        try
        {
          String inxstring = "";
          


          inxstring = inxstring + fuser.getFullName() + " ";
          inxstring = inxstring + fuser.getFuserName() + " ";
          inxstring = inxstring + fuser.getCity() + " ";
          if (!StringUtils.isNullOrEmpty(fuser.getTopLine())) {
            inxstring = inxstring + fuser.getTopLine() + " ";
          }
          if (!StringUtils.isNullOrEmpty(fuser.getBio())) {
            inxstring = inxstring + fuser.getBio() + " ";
          }
          createFileFaceBookUser(fuser.getUserId(), inxstring);
          

          fuser.setIndexdone(1);
          BOFactory.getFbUserBO().updateFbUser(fuser);
        }
        catch (Exception e)
        {
          logger.info("error createFbUserIndex for user id:" + fuser.getUserId());
          logger.info("error createFbUserIndex for fbuser", e);
        }
      }
    }
    try
    {
      File FILES_TO_INDEX_DIRECTORY = new File(indexfileDirFacebookUsers);
      File[] files = FILES_TO_INDEX_DIRECTORY.listFiles();
      if (files != null) {
        for (File file : files)
        {
          Document document = new Document();
          
          String path = file.getCanonicalPath();
          

          String id = "0";
          if (file.getName() != null) {
            id = file.getName().substring(0, file.getName().indexOf(".txt"));
          }
          byte[] bytes = path.getBytes();
          document.add(new Field("title_fbuser", id, Field.Store.YES, Field.Index.NO));
          

          Reader reader = new FileReader(file);
          document.add(new Field("content_fbuser", reader));
          try
          {
            writer_fbusers.addDocument(document);
          }
          catch (AlreadyClosedException e)
          {
            writer_fbusers = new IndexWriter(d, this.analyzer_fbusers, IndexWriter.MaxFieldLength.LIMITED);
            writer_fbusers.addDocument(document);
          }
        }
      }
      writer_fbusers.optimize();
      writer_fbusers.close();
    }
    catch (Exception e)
    {
      writer_fbusers.optimize();
      writer_fbusers.close();
      logger.info("Error on Fbuser index", e);
    }
  }
  
  public void createFacebookJobsIndex()
    throws Exception
  {
    logger.info("inside createFacebookJobsIndex");
    

    File INDEX_DIRECTORY = new File(indexfileDirFacebookJobs_lu);
    if (!INDEX_DIRECTORY.exists()) {
      new File(indexfileDirFacebookJobs_lu).mkdir();
    }
    SimpleFSDirectory d = new SimpleFSDirectory(INDEX_DIRECTORY);
    
    List jobsList = null;
    if (writer_fbjobs == null)
    {
      writer_fbjobs = new IndexWriter(d, this.analyzer_fbjobs, IndexWriter.MaxFieldLength.LIMITED);
      
      jobsList = BOFactory.getFbUserBO().getAllFbJobs(0);
    }
    else
    {
      jobsList = BOFactory.getFbUserBO().getAllFbJobs(0);
    }
    if ((jobsList != null) && (jobsList.size() > 0)) {
      for (int i = 0; i < jobsList.size(); i++)
      {
        FbJobs fjob = (FbJobs)jobsList.get(i);
        try
        {
          String inxstring = "";
          


          inxstring = inxstring + fjob.getJobTitle() + " ";
          if (!StringUtils.isNullOrEmpty(fjob.getHeadline())) {
            inxstring = inxstring + fjob.getHeadline() + " ";
          }
          inxstring = inxstring + fjob.getCompanyName() + " ";
          if (!StringUtils.isNullOrEmpty(fjob.getCity())) {
            inxstring = inxstring + fjob.getCity() + " ";
          }
          if (!StringUtils.isNullOrEmpty(fjob.getPostalcode())) {
            inxstring = inxstring + fjob.getPostalcode() + " ";
          }
          if (!StringUtils.isNullOrEmpty(fjob.getCountry())) {
            inxstring = inxstring + fjob.getCountry() + " ";
          }
          if (!StringUtils.isNullOrEmpty(fjob.getState())) {
            inxstring = inxstring + fjob.getState() + " ";
          }
          if (!StringUtils.isNullOrEmpty(fjob.getExperience())) {
            inxstring = inxstring + fjob.getExperience() + " ";
          }
          if (!StringUtils.isNullOrEmpty(fjob.getDescription())) {
            inxstring = inxstring + fjob.getDescription() + " ";
          }
          if (!StringUtils.isNullOrEmpty(fjob.getPerks())) {
            inxstring = inxstring + fjob.getPerks() + " ";
          }
          if (!StringUtils.isNullOrEmpty(fjob.getJobcategory())) {
            inxstring = inxstring + fjob.getJobcategory() + " ";
          }
          if (!StringUtils.isNullOrEmpty(fjob.getTenure())) {
            inxstring = inxstring + fjob.getTenure() + " ";
          }
          createFileFaceBookJobs(fjob.getJobId(), inxstring);
          

          fjob.setIndexdone(1);
          BOFactory.getFbUserBO().updateFbJob(fjob);
        }
        catch (Exception e)
        {
          logger.info("error createFbUserIndex for Job id:" + fjob.getJobId());
          logger.info("error createFbUserIndex for Jobs", e);
        }
      }
    }
    try
    {
      File FILES_TO_INDEX_DIRECTORY = new File(indexfileDirFacebookJobs);
      File[] files = FILES_TO_INDEX_DIRECTORY.listFiles();
      if (files != null) {
        for (File file : files)
        {
          Document document = new Document();
          
          String path = file.getCanonicalPath();
          

          String id = "0";
          if (file.getName() != null) {
            id = file.getName().substring(0, file.getName().indexOf(".txt"));
          }
          byte[] bytes = path.getBytes();
          document.add(new Field("title_fbjob", id, Field.Store.YES, Field.Index.NO));
          

          Reader reader = new FileReader(file);
          document.add(new Field("content_fbjob", reader));
          try
          {
            writer_fbjobs.addDocument(document);
          }
          catch (AlreadyClosedException e)
          {
            writer_fbjobs = new IndexWriter(d, this.analyzer_fbjobs, IndexWriter.MaxFieldLength.LIMITED);
            writer_fbjobs.addDocument(document);
          }
        }
      }
      writer_fbjobs.optimize();
      writer_fbjobs.close();
    }
    catch (Exception e)
    {
      writer_fbusers.optimize();
      writer_fbusers.close();
      logger.info("Error on createFacebookJobsIndex index", e);
    }
  }
}
