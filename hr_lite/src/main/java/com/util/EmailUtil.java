package com.util;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

import com.resources.Constant;

public class EmailUtil
{
  protected static final Logger logger = Logger.getLogger(EmailUtil.class);
  
  public synchronized boolean sendMessage(String from, String[] to, String[] cc, String[] bcc, String replyTo, String subject, String textBody, String htmlBody, String[] attachments, int langId)
    throws Exception
  {
    logger.info("inside sendMessageOnBehalf method start");
    boolean sentResult = false;
    try
    {
      Properties mailProps = new Properties();
      
      mailProps.put("mail.smtp.host", Constant.getValue("email.smtp.server"));
      if ((Constant.getValue("mail.smtp.auth") != null) && (Constant.getValue("mail.smtp.auth").equals("true")))
      {
        mailProps.put("mail.smtp.auth", "true");
        mailProps.put("mail.smtp.user", Constant.getValue("email.smtp.user"));
      }
      mailProps.put("mail.smtp.port", Constant.getValue("email.smtp.port"));
      
      mailProps.put("mail.mime.charset", "ISO-8859-1");
      
      Session session = Session.getDefaultInstance(mailProps, null);
      

      MimeMessage message = new MimeMessage(session);
      

      InternetAddress fromAdd = new InternetAddress(from);
      message.setFrom(fromAdd);
      

      InternetAddress[] toAdd = null;
      if (to != null)
      {
        toAdd = new InternetAddress[to.length];
        for (int i = 0; i < to.length; i++) {
          toAdd[i] = new InternetAddress(to[i]);
        }
        message.setRecipients(Message.RecipientType.TO, toAdd);
      }
      if (cc != null)
      {
        InternetAddress[] ccAdd = new InternetAddress[cc.length];
        for (int i = 0; i < cc.length; i++) {
          ccAdd[i] = new InternetAddress(cc[i]);
        }
        message.setRecipients(Message.RecipientType.CC, ccAdd);
      }
      if (bcc != null)
      {
        InternetAddress[] bccAdd = new InternetAddress[bcc.length];
        for (int i = 0; i < bcc.length; i++) {
          bccAdd[i] = new InternetAddress(bcc[i]);
        }
        message.setRecipients(Message.RecipientType.BCC, bccAdd);
      }
      if (replyTo != null)
      {
        InternetAddress[] repTo = { new InternetAddress(replyTo) };
        message.setReplyTo(repTo);
      }
      if (!StringUtils.isNullOrEmpty(Constant.getValue("email.subject.append"))) {
        subject = Constant.getValue("email.subject.append") + " " + subject;
      }
      message.setSubject(subject);
      

      Multipart mp = makeMultiPart(htmlBody, textBody);
      if (attachments != null) {
        mp = addAttachments(mp, attachments);
      }
      message.setContent(mp);
      

      message.setSentDate(new Date());
      if ((Constant.getValue("mail.smtp.auth") != null) && (Constant.getValue("mail.smtp.auth").equals("true")))
      {
        Transport tr = session.getTransport("smtp");
        tr.connect(Constant.getValue("email.smtp.server"), Constant.getValue("email.smtp.user"), Constant.getValue("email.smtp.password"));
        message.saveChanges();
        tr.sendMessage(message, message.getAllRecipients());
        tr.close();
        
        sentResult = true;
        logger.info("inside sendMessageOnBehalf method end");
      }
      else
      {
        Transport.send(message);
      }
    }
    catch (Exception exp)
    {
      exp.printStackTrace();
      sentResult = false;
      throw exp;
    }
    return sentResult;
  }
  
  public synchronized boolean sendMessageOnBehalf(String from, String[] to, String[] cc, String[] bcc, String replyTo, String subject, String textBody, String htmlBody, String[] attachments, int langId)
    throws Exception
  {
    boolean sentResult = false;
    logger.info("inside sendMessageOnBehalf method start");
    try
    {
      Properties mailProps = new Properties();
      mailProps.put("mail.smtp.host", Constant.getValue("email.smtp.server"));
      if ((Constant.getValue("mail.smtp.auth") != null) && (Constant.getValue("mail.smtp.auth").equals("true")))
      {
        mailProps.put("mail.smtp.auth", "true");
        mailProps.put("mail.smtp.user", Constant.getValue("email.smtp.user"));
      }
      mailProps.put("mail.smtp.port", Constant.getValue("email.smtp.port"));
      
      mailProps.put("mail.mime.charset", "ISO-8859-1");
      
      Session session = Session.getDefaultInstance(mailProps, null);
      

      MimeMessage message = new MimeMessage(session);
      



      message.setFrom(new InternetAddress(from));
      


      InternetAddress[] toAdd = null;
      if (to != null)
      {
        toAdd = new InternetAddress[to.length];
        for (int i = 0; i < to.length; i++) {
          toAdd[i] = new InternetAddress(to[i]);
        }
        message.setRecipients(Message.RecipientType.TO, toAdd);
      }
      if (cc != null)
      {
        InternetAddress[] ccAdd = new InternetAddress[cc.length];
        for (int i = 0; i < cc.length; i++) {
          ccAdd[i] = new InternetAddress(cc[i]);
        }
        message.setRecipients(Message.RecipientType.CC, ccAdd);
      }
      if (bcc != null)
      {
        InternetAddress[] bccAdd = new InternetAddress[bcc.length];
        for (int i = 0; i < bcc.length; i++) {
          bccAdd[i] = new InternetAddress(bcc[i]);
        }
        message.setRecipients(Message.RecipientType.BCC, bccAdd);
      }
      if (replyTo != null)
      {
        InternetAddress[] repTo = { new InternetAddress(replyTo) };
        message.setReplyTo(repTo);
      }
      if (!StringUtils.isNullOrEmpty(Constant.getValue("email.subject.append"))) {
        subject = Constant.getValue("email.subject.append") + " " + subject;
      }
      message.setSubject(subject);
      

      Multipart mp = makeMultiPart(htmlBody, textBody);
      if (attachments != null) {
        mp = addAttachments(mp, attachments);
      }
      message.setContent(mp);
      

      message.setSentDate(new Date());
      if ((Constant.getValue("mail.smtp.auth") != null) && (Constant.getValue("mail.smtp.auth").equals("true")))
      {
        Transport tr = session.getTransport("smtp");
        tr.connect(Constant.getValue("email.smtp.server"), Constant.getValue("email.smtp.user"), Constant.getValue("email.smtp.password"));
        message.saveChanges();
        tr.sendMessage(message, message.getAllRecipients());
        tr.close();
        
        sentResult = true;
        logger.info("inside sendMessageOnBehalf method end");
      }
      else
      {
        Transport.send(message);
      }
    }
    catch (Exception exp)
    {
      exp.printStackTrace();
      sentResult = false;
      throw exp;
    }
    return sentResult;
  }
  
  public synchronized boolean sendCalenderMessage(String from, String[] to, String[] cc, String[] bcc, String replyTo, String subject, String textBody, String htmlBody, String attachments, int langId)
    throws Exception
  {
    logger.info("inside sendCalenderMessage method start");
    boolean sentResult = false;
    try
    {
      Properties mailProps = new Properties();
      mailProps.put("mail.smtp.host", Constant.getValue("email.smtp.server"));
      if ((Constant.getValue("mail.smtp.auth") != null) && (Constant.getValue("mail.smtp.auth").equals("true")))
      {
        mailProps.put("mail.smtp.auth", "true");
        mailProps.put("mail.smtp.user", Constant.getValue("email.smtp.user"));
      }
      mailProps.put("mail.smtp.port", Constant.getValue("email.smtp.port"));
      mailProps.put("mail.mime.charset", "ISO-8859-1");
      
      Session session = Session.getDefaultInstance(mailProps, null);
      

      MimeMessage message = new MimeMessage(session);
      





      message.setFrom(new InternetAddress(from));
      

      InternetAddress[] toAdd = null;
      if (to != null)
      {
        toAdd = new InternetAddress[to.length];
        for (int i = 0; i < to.length; i++) {
          toAdd[i] = new InternetAddress(to[i]);
        }
        message.setRecipients(Message.RecipientType.TO, toAdd);
      }
      if (cc != null)
      {
        InternetAddress[] ccAdd = new InternetAddress[cc.length];
        for (int i = 0; i < cc.length; i++) {
          ccAdd[i] = new InternetAddress(cc[i]);
        }
        message.setRecipients(Message.RecipientType.CC, ccAdd);
      }
      if (bcc != null)
      {
        InternetAddress[] bccAdd = new InternetAddress[bcc.length];
        for (int i = 0; i < bcc.length; i++) {
          bccAdd[i] = new InternetAddress(bcc[i]);
        }
        message.setRecipients(Message.RecipientType.BCC, bccAdd);
      }
      if (replyTo != null)
      {
        InternetAddress[] repTo = { new InternetAddress(replyTo) };
        message.setReplyTo(repTo);
      }
      if (!StringUtils.isNullOrEmpty(Constant.getValue("email.subject.append"))) {
        subject = Constant.getValue("email.subject.append") + " " + subject;
      }
      message.setSubject(subject);
      



































      Multipart mp = makeMultiPart(htmlBody, textBody);
      if (attachments != null)
      {
        logger.info("attachments" + attachments);
        BodyPart messageBodyPart = new MimeBodyPart();
        String filename = "calendar.vcs";
        messageBodyPart.setFileName(filename);
        messageBodyPart.setContent(attachments, "text/html");
        mp.addBodyPart(messageBodyPart);
      }
      message.setContent(mp);
      




      message.setSentDate(new Date());
      if ((Constant.getValue("mail.smtp.auth") != null) && (Constant.getValue("mail.smtp.auth").equals("true")))
      {
        Transport tr = session.getTransport("smtp");
        tr.connect(Constant.getValue("email.smtp.server"), Constant.getValue("email.smtp.user"), Constant.getValue("email.smtp.password"));
        message.saveChanges();
        tr.sendMessage(message, message.getAllRecipients());
        tr.close();
        
        sentResult = true;
        logger.info("inside sendMessageOnBehalf method end");
      }
      else
      {
        Transport.send(message);
      }
    }
    catch (Exception exp)
    {
      exp.printStackTrace();
      sentResult = false;
      throw exp;
    }
    return sentResult;
  }
  
  private Multipart makeMultiPart(String htmlBody, String textBody)
    throws Exception
  {
    Multipart mp = new MimeMultipart("alternative");
    if (textBody != null)
    {
      MimeBodyPart tbp = new MimeBodyPart();
      tbp.setText(textBody, "ISO-8859-1");
      tbp.setHeader("Content-Type", "text/html; charset=ISO-8859-1");
      mp.addBodyPart(tbp);
    }
    if (htmlBody != null)
    {
      MimeBodyPart tbp = new MimeBodyPart();
      tbp.setText(htmlBody, "ISO-8859-1");
      tbp.setHeader("Content-Type", "text/html; charset=ISO-8859-1");
      mp.addBodyPart(tbp);
    }
    return mp;
  }
  
  private Multipart addAttachments(Multipart mp, String[] attachments)
    throws Exception
  {
    for (int i = 0; i < attachments.length; i++)
    {
      MimeBodyPart mbp = new MimeBodyPart();
      FileDataSource fds = new FileDataSource(attachments[i]);
      mbp.setDataHandler(new DataHandler(fds));
      mbp.setFileName(fds.getName());
      mp.addBodyPart(mbp);
    }
    return mp;
  }
  
  private Multipart addAttachment(Multipart mp, String attachments, String fileName)
    throws Exception
  {
    MimeBodyPart mbp = new MimeBodyPart();
    FileDataSource fds = new FileDataSource(attachments);
    mbp.setDataHandler(new DataHandler(fds));
    mbp.setFileName(fileName);
    mp.addBodyPart(mbp);
    
    return mp;
  }
}
