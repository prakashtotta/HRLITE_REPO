package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.sql.Blob;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Address;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.sql.rowset.serial.SerialBlob;

import org.apache.log4j.Logger;

import com.bean.ApplicantAttachments;
import com.bean.JobApplicant;
import com.bean.JobRequisition;
import com.bean.Locale;
import com.bean.Timezone;
import com.bean.User;
import com.bean.UserGroup;
import com.bean.pool.EmailCampaign;
import com.bean.pool.TalentPool;
import com.bo.BOFactory;
import com.common.Common;
import com.manager.EmailTaskManager;
import com.resources.Constant;

public class EmailResumeReaderUtil
{
  protected static final Logger logger = Logger.getLogger(EmailResumeReaderUtil.class);
  
  public static void emailReadAndResumeSubmit(TalentPool pool)
  {
    logger.info("inside emailReadAndResumeSubmit");
    try
    {
      String host = pool.getSmtpserver();
      if (!StringUtils.isNullOrEmpty(pool.getSmptoport())) {
        host = host + ":" + pool.getSmptoport();
      }
      String username = pool.getSmtpuser();
      String password = pool.getSmtppassword();
      

      Properties props = new Properties();
      
      logger.info("host" + host);
      logger.info("host" + username);
      logger.info("host" + password);
      
      Session session = Session.getDefaultInstance(props, null);
      

      Store store = session.getStore(Constant.getValue("email.resume.reader.protocol"));
      

      store.connect(host, username, password);
      


      Folder folder = store.getFolder(Constant.getValue("email.resume.reader.folder"));
      

      folder.open(2);
      



      Message[] message = folder.getMessages();
      
      int i = 0;
      for (int n = message.length; i < n; i++)
      {
        logger.info("start email reading from email" + message[i].getFrom());
        
        String applicantName = "";
        

        Address[] from = message[i].getFrom();
        

        String fromemail = "";
        if (from[0] != null)
        {
          logger.info("from[0].toString()" + from[0].toString());
          if (from[0].toString().contains("<"))
          {
            fromemail = from[0].toString().substring(from[0].toString().indexOf("<") + 1, from[0].toString().lastIndexOf(">"));
            applicantName = from[0].toString().substring(0, from[0].toString().indexOf("<"));
          }
          else if (from[0].toString().contains("["))
          {
            fromemail = from[0].toString().substring(from[0].toString().indexOf("[") + 1, from[0].toString().lastIndexOf("]"));
            applicantName = from[0].toString().substring(0, from[0].toString().indexOf("["));
          }
          else
          {
            fromemail = from[0].toString();
            applicantName = fromemail;
          }
        }
        JobApplicant applicant = new JobApplicant();
        applicant.setCreatedBy("emailreader");
        applicant.setCreatedDate(new Date());
        applicant.setFullName(applicantName);
        
        applicant.setEmail(fromemail);
        applicant.setResumeHeader(message[i].getSubject());
        applicant.setScreenCode("APPLICANT_SCREEN");
        if ((message[i].isMimeType("multipart/*")) || (message[i].isMimeType("message/rfc822")))
        {
          System.out.println(message[i].getContentType().toString());
          Multipart mp = (Multipart)message[i].getContent();
          int count = mp.getCount();
          boolean isdone = false;
          for (int j = 0; j < count; j++)
          {
            Object o = mp.getBodyPart(j).getContent();
            if ((o instanceof String)) {
              applicant.setNote((String)o);
            }
            if (((o instanceof InputStream)) && (!isdone)) {
              isdone = dumpPartTalentPool(mp.getBodyPart(j), applicant);
            }
          }
          saveApplicantData(applicant, pool);
        }
        message[i].setFlag(Flags.Flag.DELETED, true);
        logger.info("end email reading from email" + message[i].getFrom());
      }
      folder.close(true);
      store.close();
    }
    catch (Exception e)
    {
      e.printStackTrace();
      logger.info("exception on emailReadAndResumeSubmit" + e.getMessage());
    }
  }
  
  public static void checkEmailCampaign(EmailCampaign ecmp)
    throws Exception
  {
    logger.info("inside checkEmailCampaign");
    try
    {
      String host = ecmp.getSmtpserver();
      if (!StringUtils.isNullOrEmpty(ecmp.getSmptoport())) {
        host = host + ":" + ecmp.getSmptoport();
      }
      String username = ecmp.getSmtpuser();
      String password = ecmp.getSmtppassword();
      if (!StringUtils.isNullOrEmpty(password)) {
        password = EncryptDecrypt.decrypt(password);
      }
      Properties props = new Properties();
      

      Session session = Session.getDefaultInstance(props, null);
      

      Store store = session.getStore(Constant.getValue("email.resume.reader.protocol"));
      

      store.connect(host, username, password);
      


      Folder folder = store.getFolder(Constant.getValue("email.resume.reader.folder"));
      

      folder.open(2);
      

      Message[] message = folder.getMessages();
    }
    catch (Exception e)
    {
      
      logger.info("checkEmailCampaign" + e);
      throw e;
    }
  }
  
  public static void emailReadAndResumeSubmit(EmailCampaign ecmp)
  {
    try
    {
      String host = ecmp.getSmtpserver();
      if (!StringUtils.isNullOrEmpty(ecmp.getSmptoport())) {
        host = host + ":" + ecmp.getSmptoport();
      }
      String username = ecmp.getSmtpuser();
      String password = ecmp.getSmtppassword();
      if (!StringUtils.isNullOrEmpty(password)) {
        password = EncryptDecrypt.decrypt(password);
      }
      Properties props = new Properties();
      

      Session session = Session.getDefaultInstance(props, null);
      

      Store store = session.getStore(Constant.getValue("email.resume.reader.protocol"));
      

      store.connect(host, username, password);
      


      Folder folder = store.getFolder(Constant.getValue("email.resume.reader.folder"));
      

      folder.open(2);
      



      Message[] message = folder.getMessages();
      
      int i = 0;
      for (int n = message.length; i < n; i++)
      {
        logger.info("start email reading subject" + message[i].getSubject());
        
        String applicantName = "";
        String jobcode = "";
        String subject = message[i].getSubject();
        if (!StringUtils.isNullOrEmpty(subject))
        {
          subject = subject.trim();
          if (subject.contains(Constant.getValue("email.resume.reader.separator")))
          {
            applicantName = subject.substring(0, subject.indexOf(Constant.getValue("email.resume.reader.separator")));
            jobcode = subject.substring(subject.indexOf(Constant.getValue("email.resume.reader.separator")) + 1, subject.length());
          }
        }
        else
        {
          logger.info("Wrong subject line" + subject + " for applicant" + applicantName);
          message[i].setFlag(Flags.Flag.DELETED, true);
          continue;
        }
        logger.info("applicantName" + applicantName);
        logger.info("jobcode" + jobcode);
        JobRequisition jobreq = null;
        jobreq = BOFactory.getJobRequistionBO().getActiveJobRequisionByJobCode(jobcode);
        if (jobreq == null)
        {
          logger.info("Wrong jobcode" + jobcode);
          message[i].setFlag(Flags.Flag.DELETED, true);
        }
        else
        {
          Address[] from = message[i].getFrom();
          
          String fromemail = "";
          if (from[0] != null) {
            if (from[0].toString().contains("<")) {
              fromemail = from[0].toString().substring(from[0].toString().indexOf("<") + 1, from[0].toString().lastIndexOf(">"));
            } else {
              fromemail = from[0].toString();
            }
          }
          JobApplicant applicant = new JobApplicant();
          applicant.setSuper_user_key(ecmp.getSuper_user_key());
          applicant.setCreatedBy("emailreader");
          applicant.setCreatedDate(new Date());
          applicant.setFullName(applicantName);
          applicant.setReqId(jobreq.getJobreqId());
          applicant.setJobTitle(jobreq.getJobreqName());
          applicant.setEmail(fromemail);
          applicant.setScreenCode("APPLICANT_SCREEN_EXTERNAL");
          if (jobreq.getRecruiterId() == 0L)
          {
            applicant.setOwner(jobreq.getHiringmgr());
          }
          else if ((!StringUtils.isNullOrEmpty(jobreq.getIsgrouprecruiter())) && (jobreq.getIsgrouprecruiter().equals("Y")))
          {
            UserGroup uggroup = new UserGroup();
            uggroup.setUsergrpId(jobreq.getRecruiterId());
            applicant.setOwnerGroup(uggroup);
            applicant.setIsGroup("Y");
          }
          else
          {
            User recruiter = new User();
            recruiter.setUserId(jobreq.getRecruiterId());
            applicant.setOwner(recruiter);
            applicant.setIsGroup("N");
          }
          logger.info("mime type" + message[i].isMimeType("message/rfc822"));
          if ((message[i].isMimeType("multipart/*")) || (message[i].isMimeType("message/rfc822")))
          {
            System.out.println(message[i].getContentType().toString());
            Multipart mp = (Multipart)message[i].getContent();
            int count = mp.getCount();
            for (int j = 0; j < count; j++)
            {
              boolean isdone = dumpPart(mp.getBodyPart(j), applicant);
              if (isdone) {
                break;
              }
            }
          }
          message[i].setFlag(Flags.Flag.DELETED, true);
          logger.info("end email reading subject" + message[i].getSubject());
        }
      }
      folder.close(true);
      store.close();
    }
    catch (Exception e)
    {
      logger.info("error on email reading", e);
      logger.info(e);
    }
  }
  
  public static boolean dumpPart(Part p, JobApplicant applicant)
    throws Exception
  {
    logger.info("inside dumpPart start");
    try
    {
      boolean isdone = false;
      String filePath = Constant.getValue("ATTACHMENT_PATH");
      Object o = p.getContent();
      if ((o instanceof String))
      {
        applicant.setNote((String)o);
      }
      else if ((o instanceof InputStream))
      {
        System.out.println(p.getFileName());
        

        File f = new File(filePath + p.getFileName());
        

        FileOutputStream fout = new FileOutputStream(f);
        InputStream in = (InputStream)o;
        for (int c = in.read(); c != -1; c = in.read()) {
          fout.write(c);
        }
        in.close();
        fout.close();
        
        String resumename = p.getFileName();
        applicant.setResumename(resumename);
        
        FileInputStream fin = new FileInputStream(f);
        RandomAccessFile r = new RandomAccessFile(filePath + p.getFileName(), "rw");
        String ll = String.valueOf(f.length());
        
        byte[] sizefile = new byte[Integer.parseInt(ll)];
        fin.read(sizefile);
        
        r.write(sizefile);
        
        r.close();
        fin.close();
        
        int limitfileSize = new Integer(Constant.getValue("attachment.file.size.limit")).intValue();
        Blob blob1 = null;
        if ((sizefile != null) && (sizefile.length > limitfileSize))
        {
          String fnote = " Attachment removed because attachment size is exceeded size limit " + applicant.getNote() == null ? "" : applicant.getNote();
          
          applicant.setNote(fnote);
        }
        else
        {
          blob1 = new SerialBlob(sizefile);
          ApplicantAttachments attach = new ApplicantAttachments();
          attach.setAttachmentdata(blob1);
          attach.setUuid(UUID.randomUUID().toString());
          attach.setAttahmentname(resumename);
          applicant.setApplicantAttachment(attach);
        }
        applicant.setCreatedBy("emailreader");
        applicant.setCreatedDate(new Date());
        applicant.setUpdatedBy(null);
        applicant.setUpdatedDate(null);
        applicant.setStatus("A");
        if ((!StringUtils.isNullOrEmpty(Constant.getValue("email.resume.reader.pre.application.submit.state"))) && (Constant.getValue("email.resume.reader.pre.application.submit.state").equals("yes"))) {
          applicant.setInterviewState("Pre Application Submitted");
        } else {
          applicant.setInterviewState("Application Submitted");
        }
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
          applicant = BOFactory.getApplicantTXBO().saveApplicantByEmaderailReader(applicant, Common.APPLICANT_CREATED_BY_TYPE_EMAIL_READER);
          
          BOFactory.getApplicantBO().assignApplicantNumberAndJobTitle(applicant);
          ApplicantAttachments attach = applicant.getApplicantAttachment();
          if (attach != null)
          {
            attach.setAppuuid(applicant.getUuid());
            String filePathr1 = Constant.getValue("ATTACHMENT_PATH");
            String filePathr = filePathr1 + File.separator + "applicantAttachment" + File.separator + attach.getUuid() + File.separator;
            File file = new File(filePathr);
            if (!file.exists()) {
              file.mkdirs();
            }
            Blob blob = attach.getAttachmentdata();
            RandomAccessFile raf = new RandomAccessFile(filePathr + applicant.getResumename(), "rw");
            

            int length = (int)blob.length();
            byte[] _blob = blob.getBytes(1L, length);
            raf.write(_blob);
            raf.close();
            applicant.setFilePath(filePathr);
            






            BOFactory.getApplicantBO().updateApplicantAttachment(applicant.getApplicantAttachment(), "emailreader");
          }
          if ((!StringUtils.isNullOrEmpty(Constant.getValue("email.resume.reader.pre.application.submit.state"))) && (Constant.getValue("email.resume.reader.pre.application.submit.state").equals("yes")))
          {
            applicant.setLocale_code(Constant.getValue("default.locale.code"));
            String[] to = new String[1];
            to[0] = applicant.getEmail();
            EmailTask emailtask = new EmailTask(Constant.getValue("email.fromemail"), to, null, null, null, "dummysubject", null, "dummybody", null, 0, null);
            emailtask.setFunctionType(Common.PRE_APPLICATION_SUBMIT);
            emailtask.setUser(null);
            emailtask.setApplicant(applicant);
            EmailTaskManager.sendEmail(emailtask);
          }
          else
          {
            User userContext = new User();
            Locale locale = new Locale();
            locale.setLocaleCode(Constant.getValue("default.locale.code"));
            userContext.setLocale(locale);
            Timezone tz = new Timezone();
            tz.setTimezoneCode(Constant.getValue("default.timezone.code"));
            userContext.setTimezone(tz);
            
            BOFactory.getApplicantBO().sendApplicationSubmitionEmail(applicant, userContext, null);
          }
          isdone = true;
        }
      }
      else
      {
        System.out.println("This is an unknown type");
        System.out.println("---------------------------");
        System.out.println(o.toString());
      }
      return isdone;
    }
    catch (Exception e)
    {
      logger.info("Exception on dumppart", e);
      throw e;
    }
  }
  
  public static boolean dumpPartTalentPool(Part p, JobApplicant applicant)
  {
    logger.info("inside dumpPartTalentPool");
    boolean isdone = false;
    try
    {
      String filePath = Constant.getValue("ATTACHMENT_PATH");
      Object o = p.getContent();
      if ((o instanceof InputStream))
      {
        System.out.println(p.getFileName());
        

        File f = new File(filePath + p.getFileName());
        

        FileOutputStream fout = new FileOutputStream(f);
        InputStream in = (InputStream)o;
        for (int c = in.read(); c != -1; c = in.read()) {
          fout.write(c);
        }
        in.close();
        fout.close();
        
        String resumename = p.getFileName();
        applicant.setResumename(resumename);
        
        FileInputStream fin = new FileInputStream(f);
        RandomAccessFile r = new RandomAccessFile(filePath + p.getFileName(), "rw");
        String ll = String.valueOf(f.length());
        
        byte[] sizefile = new byte[Integer.parseInt(ll)];
        fin.read(sizefile);
        
        r.write(sizefile);
        
        r.close();
        fin.close();
        
        int limitfileSize = new Integer(Constant.getValue("attachment.file.size.limit")).intValue();
        Blob blob1 = null;
        if ((sizefile != null) && (sizefile.length > limitfileSize))
        {
          String fnote = " Attachment removed because attachment size is exceeded size limit " + applicant.getNote() == null ? "" : applicant.getNote();
          
          applicant.setNote(fnote);
        }
        else
        {
          blob1 = new SerialBlob(sizefile);
          ApplicantAttachments attach = new ApplicantAttachments();
          attach.setAttachmentdata(blob1);
          attach.setUuid(UUID.randomUUID().toString());
          attach.setAttahmentname(resumename);
          applicant.setApplicantAttachment(attach);
        }
        isdone = true;
      }
    }
    catch (Exception e)
    {
      logger.info("error in dumpPartTalentPool " + e);
    }
    return isdone;
  }
  
  public static void saveApplicantData(JobApplicant applicant, TalentPool pool)
  {
    logger.info("inside saveApplicantData");
    try
    {
      String filePath = Constant.getValue("ATTACHMENT_PATH");
      
      applicant.setCreatedBy("emailreader");
      applicant.setCreatedDate(new Date());
      applicant.setUpdatedBy(null);
      applicant.setUpdatedDate(null);
      applicant.setStatus("T");
      if ((!StringUtils.isNullOrEmpty(Constant.getValue("talent.pool.resume.reader.pre.talent.pool.state"))) && (Constant.getValue("talent.pool.resume.reader.pre.talent.pool.state").equals("yes"))) {
        applicant.setInterviewState("Talent Pool Temp");
      } else {
        applicant.setInterviewState("Talent Pool");
      }
      applicant.setAppliedDate(new Date());
      applicant.setHeighestQualification("");
      applicant.setPhone("");
      applicant.setReferrerName("");
      applicant.setExpectedctccurrencycode(pool.getOrganization().getCurrencyCode());
      applicant.setCurrectctccurrencycode(pool.getOrganization().getCurrencyCode());
      
      applicant.setUuid(UUID.randomUUID().toString());
      
      User user = new User();
      user.setUserId(0L);
      user.setUserName(Common.APPLICANT_CREATED_BY_TYPE_EMAIL_READER);
      if ((pool.getIsGroup() != null) && (pool.getIsGroup().equals("Y")))
      {
        UserGroup ownerGroup = new UserGroup();
        ownerGroup.setUsergrpId(pool.getAssignedtoid());
        applicant.setOwnerGroup(ownerGroup);
      }
      else
      {
        User owner = new User();
        owner.setUserId(pool.getAssignedtoid());
        applicant.setOwner(owner);
      }
      applicant.setSuper_user_key(pool.getSuper_user_key());
      applicant = BOFactory.getApplicantTXBO().saveApplicantToTalentPool(applicant, user, Common.APPLICANT_CREATED_BY_TYPE_EMAIL_READER, pool.getTalentPoolId());
      BOFactory.getApplicantBO().assignApplicantNumberAndJobTitle(applicant);
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
        






        BOFactory.getApplicantBO().updateApplicantAttachment(applicant.getApplicantAttachment(), "emailreader");
      }
      if ((!StringUtils.isNullOrEmpty(Constant.getValue("talent.pool.resume.reader.pre.talent.pool.state"))) && (Constant.getValue("talent.pool.resume.reader.pre.talent.pool.state").equals("yes")))
      {
        applicant.setLocale_code(Constant.getValue("default.locale.code"));
        String[] to = new String[1];
        to[0] = applicant.getEmail();
        EmailTask emailtask = new EmailTask(Constant.getValue("email.fromemail"), to, null, null, null, "dummysubject", null, "dummybody", null, 0, null);
        emailtask.setFunctionType(Common.PRE_TALENT_POOL);
        emailtask.setUser(null);
        emailtask.setApplicant(applicant);
        EmailTaskManager.sendEmail(emailtask);
      }
      else
      {
        User userContext = new User();
        Locale locale = new Locale();
        locale.setLocaleCode(Constant.getValue("default.locale.code"));
        userContext.setLocale(locale);
        Timezone tz = new Timezone();
        tz.setTimezoneCode(Constant.getValue("default.timezone.code"));
        userContext.setTimezone(tz);
        
        BOFactory.getApplicantBO().sendApplicationSubmitionEmail(applicant, userContext, null);
      }
    }
    catch (Exception e)
    {
      logger.info("error in saveApplicantData " + e);
    }
  }
}
