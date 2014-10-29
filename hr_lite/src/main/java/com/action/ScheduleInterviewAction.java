package com.action;

import java.sql.Blob;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.bean.ApplicantRating;
import com.bean.JobApplicant;
import com.bean.JobApplicationEvent;
import com.bean.JobTemplateAccomplishment;
import com.bean.JobTemplateCompetency;
import com.bean.User;
import com.bean.UserGroup;
import com.bo.BOFactory;
import com.common.Common;
import com.common.EmailNotificationSettingFunction;
import com.dao.LovOpsDAO;
import com.dao.UserDAO;
import com.form.ScheduleInterviewForm;
import com.manager.EmailTaskManager;
import com.resources.Constant;
import com.util.DateUtil;
import com.util.EmailTask;
import com.util.EmailUtil;
import com.util.StringUtils;
import com.util.VcalUtil;

public class ScheduleInterviewAction
  extends CommonAction
{
  protected static final Logger logger = Logger.getLogger(ScheduleInterviewAction.class);
  
  public ActionForward interviewschedulescr(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside interviewschedulescr method");
    String applicantId = request.getParameter("applicantId");
    logger.info("applicantId" + applicantId);
    String eventype = request.getParameter("eventype");
    String reschedule = request.getParameter("reschedule");
    ScheduleInterviewForm intform = (ScheduleInterviewForm)form;
    intform.setApplicantId(new Long(applicantId.trim()).longValue());
    
    intform.setModeofInterviewList(Constant.getModeOfInterviews());
    


    String frompage = request.getParameter("frompage");
    String uuid = request.getParameter("uuid");
    if ((!StringUtils.isNullOrEmpty(frompage)) && (frompage.equals("task")) && 
      (!StringUtils.isNullOrEmpty(uuid)))
    {
      JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
      if (applicant.getInterviewState().equals("Cleared-In Screening"))
      {
        eventype = "Interview Round-1";
      }
      else
      {
        String stw = "Cleared-Interview Round";
        if ((applicant.getInterviewState() != null) && (applicant.getInterviewState().startsWith(stw)))
        {
          String evt = applicant.getInterviewState().substring(applicant.getInterviewState().lastIndexOf("-") + 1);
          int et = new Integer(evt).intValue();
          et += 1;
          eventype = "Interview Round-" + String.valueOf(et);
        }
      }
    }
    logger.info("eventype" + eventype);
    if (!StringUtils.isNullOrEmpty(eventype)) {
      intform.setEventType(Common.getEventType(eventype));
    }
    if (!StringUtils.isNullOrEmpty(reschedule)) {
      intform.setReschedule(reschedule);
    }
    request.setAttribute("isinterviewscheduled", "no");
    return mapping.findForward("interviewschedulescr");
  }
  
  public ActionForward sendbulkresumeforscreening(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside sendbulkresumeforscreening method");
    String applicantids = request.getParameter("applicantids");
    String reschedule = request.getParameter("reschedule");
    String requistionId = request.getParameter("requistionId");
    logger.info("requistionId" + requistionId);
    List applicantList = new ArrayList();
    List applicantListdetails = new ArrayList();
    if (!StringUtils.isNullOrEmpty(applicantids))
    {
      applicantids = applicantids.substring(0, applicantids.length() - 1);
      
      applicantList = StringUtils.tokenizeString(applicantids, ",");
    }
    logger.info("applicantList" + applicantList.size());
    for (int i = 0; i < applicantList.size(); i++)
    {
      String appliantId = (String)applicantList.get(i);
      JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetails(appliantId);
      logger.info("appliantId state" + applicant.getInterviewState());
      if ((applicant.getInterviewState() != null) && ((applicant.getInterviewState().equals("Application Submitted")) || (applicant.getInterviewState().equals("In Screening")))) {
        applicantListdetails.add(applicant);
      }
    }
    logger.info("applicantListdetails" + applicantListdetails.size());
    
    String eventype = request.getParameter("eventype");
    ScheduleInterviewForm intform = (ScheduleInterviewForm)form;
    intform.setApplicantList(applicantListdetails);
    logger.info("eventype" + eventype);
    intform.setModeofInterviewList(Constant.getModeOfInterviews());
    



    Calendar calendar = Calendar.getInstance();
    
    calendar.add(5, 1);
    Date date = calendar.getTime();
    Format formatter = new SimpleDateFormat("MMMM dd, yyyy");
    String s = formatter.format(date);
    intform.setDate(s);
    logger.info("Tomorrow Date : " + s);
    if ((!StringUtils.isNullOrEmpty(requistionId)) && (!requistionId.equals("0")))
    {
      User hiringmgr = BOFactory.getJobRequistionBO().getHiringManagerByReqId(new Long(requistionId).longValue());
      intform.setHiringMgr(hiringmgr);
    }
    if (!StringUtils.isNullOrEmpty(eventype)) {
      intform.setEventType(Common.getEventType(eventype));
    }
    if (!StringUtils.isNullOrEmpty(reschedule)) {
      intform.setReschedule(reschedule);
    }
    request.setAttribute("isinterviewscheduled", "no");
    return mapping.findForward("interviewschedulebulkscr");
  }
  
  public ActionForward scheduleScreeningInterviewbulk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside scheduleScreeningInterviewbulk method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    String eventype = request.getParameter("eventype");
    
    String interviewer = request.getParameter("reviewerid");
    String hr = request.getParameter("hr");
    String minute = request.getParameter("minute");
    String ampm = request.getParameter("ampm");
    logger.info("Inside scheduleScreeningInterviewbulk ampmampmampmampm" + ampm);
    String isgroup = request.getParameter("isgroup");
    logger.info("Inside scheduleInterview method isgroup" + isgroup);
    String dhr = request.getParameter("dhr");
    String dminute = request.getParameter("dminute");
    ScheduleInterviewForm intform = (ScheduleInterviewForm)form;
    List applicantList = (List)request.getSession().getAttribute("applicantList");
    
    String reschedule = request.getParameter("reschedule");
    String evid = request.getParameter("evid");
    String evname = request.getParameter("evname");
    if (applicantList != null) {
      request.getSession().removeAttribute("applicantList");
    }
    List interviewerlist = new ArrayList();
    String interviewerName = "";
    if ((!StringUtils.isNullOrEmpty(isgroup)) && (isgroup.equals("Y")))
    {
      UserGroup usrgrp = BOFactory.getLovBO().getUserGroup(new Long(interviewer).longValue());
      interviewerName = usrgrp.getUsergrpName();
      
      Set users = usrgrp.getUsers();
      if ((users != null) && (users.size() > 0))
      {
        Iterator itr = users.iterator();
        while (itr.hasNext())
        {
          User usr = (User)itr.next();
          String toemailid = "\"" + usr.getFirstName() + " " + usr.getLastName() + "\"" + " " + "<" + usr.getEmailId() + ">";
          interviewerlist.add(toemailid);
        }
      }
    }
    else
    {
      User usr = BOFactory.getLovBO().getUserFullNameEmailAndUserIdById(new Long(interviewer).longValue());
      interviewerName = usr.getFirstName() + " " + usr.getLastName();
      String toemailid = "\"" + usr.getFirstName() + " " + usr.getLastName() + "\"" + " " + "<" + usr.getEmailId() + ">";
      interviewerlist.add(toemailid);
    }
    String[] to = new String[interviewerlist.size()];
    interviewerlist.toArray(to);
    
    String[] bcc = null;
    for (int kk = 0; kk < applicantList.size(); kk++)
    {
      JobApplicant applicant2 = (JobApplicant)applicantList.get(kk);
      String applicantId = String.valueOf(applicant2.getApplicantId());
      logger.info("applicantId" + applicantId);
      logger.info("interviewer" + interviewer);
      JobApplicant applicant1 = BOFactory.getApplicantBO().getApplicantDetails(applicantId);
      
      int event_type = new Integer(eventype).intValue();
      JobApplicationEvent event = new JobApplicationEvent();
      event.setCreatedBy(user1.getUserName());
      event.setCreatedByName(user1.getFirstName() + " " + user1.getLastName());
      event.setCreatedDate(new Date());
      JobApplicant applicant = new JobApplicant();
      applicant.setApplicantId(new Long(applicantId).longValue());
      event.setApplicant(applicant);
      if (!StringUtils.isNullOrEmpty(evid))
      {
        event.setEvtmplId(new Long(evid).longValue());
        event.setEvTmplName(evname);
      }
      if ((!StringUtils.isNullOrEmpty(isgroup)) && (isgroup.equals("Y")))
      {
        UserGroup usergroup = new UserGroup();
        usergroup.setUsergrpId(new Long(interviewer).longValue());
        event.setOwnerGroup(usergroup);
        event.setIsGroup("Y");
        applicant1.setOwnerGroup(usergroup);
        applicant1.setIsGroup("Y");
      }
      else
      {
        User user = new User();
        user.setUserId(new Long(interviewer).longValue());
        event.setOwner(user);
        applicant1.setOwner(user);
        applicant1.setOwnerGroup(null);
        applicant1.setIsGroup("N");
        event.setOwnerGroup(null);
        event.setIsGroup("N");
      }
      List cclist = BOFactory.getApplicantBO().getCCListForApplicant(user1, applicant1, null, EmailNotificationSettingFunction.FunctionNames.APPLICANT_REVIEW.toString(), true);
      
      String[] cc = new String[cclist.size()];
      cclist.toArray(cc);
      




      applicant1.setInterview_organizer_id(user1.getUserId());
      if (ampm == null) {
        ampm = "pm";
      }
      String date = request.getParameter("date");
      logger.info("date :" + date);
      String hour = hr == null ? "12" : hr;
      String mt = minute == null ? "00" : minute;
      date = date + " " + hour + ":" + mt + " " + ampm;
      logger.info("date :" + date);
      String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
      datepattern = datepattern + " hh:mm aaa";
      String converteddate = DateUtil.convertFromTimezoneToTimezoneDate(date, datepattern, user1.getTimezone().getTimezoneCode(), TimeZone.getDefault().getID());
      
      logger.info("converteddate" + converteddate);
      
      Calendar cal = DateUtil.convertStringDateToCalendar(converteddate, datepattern);
      event.setInterviewDate(cal.getTime());
      


      event.setDurationhr(dhr);
      event.setDurationminute(dminute);
      
      String convertedGMTdate = DateUtil.convertFromTimezoneToTimezoneDate(date, datepattern, user1.getTimezone().getTimezoneCode(), "GMT");
      Calendar calGMT = DateUtil.convertStringDateToCalendar(convertedGMTdate, datepattern);
      logger.info("calGMT" + calGMT);
      


      event.setNotes(intform.getNotes());
      

      event.setEventType(event_type);
      logger.info("event type" + event.getEventType());
      event.setStatus(0);
      event.setModofinterviewid(intform.getModofinterviewid());
      
      FormFile myFile = intform.getEvtmplFile();
      if (myFile != null)
      {
        String contentType = myFile.getContentType();
        String fileName = myFile.getFileName();
        int fileSize = myFile.getFileSize();
        byte[] fileData = myFile.getFileData();
        
        event.setEvtmplFileName(fileName);
        Blob blob = null;
        
        blob = new SerialBlob(fileData);
        event.setEvtmplFile(blob);
      }
      if (event.getEventType() == 0)
      {
        applicant1.setInterviewState("In Screening");
        event.setInterviewState("In Screening");
      }
      else
      {
        applicant1.setInterviewState("Interview Round-" + event.getEventType());
        event.setInterviewState("Interview Round-" + event.getEventType());
      }
      BOFactory.getApplicantTXBO().scheduleScreeningInterview(user1, applicant1, event, reschedule);
      



      applicant1.setApplicantReviewerName(interviewerName);
      try
      {
        String modeofint = "";
        if (event.getModofinterviewid() == 0) {
          modeofint = "Review";
        } else {
          modeofint = Constant.getModeOfInterviewName(event.getModofinterviewid());
        }
        String subject = applicant1.getInterviewState() + " " + modeofint + " " + applicant1.getFullName() + " " + "-" + applicant1.getJobTitle();
        logger.info("subject" + subject);
        

        String applicantcalurl = Constant.getValue("external.url") + "applicant.do?method=3DapplicantDetails&applicantId=3D" + applicant1.getApplicantId() + "&secureid=3D" + applicant1.getUuid();
        
        String[] attachements = null;
        if ((!StringUtils.isNullOrEmpty(Constant.getValue("outlook.calendar.file.required"))) && (Constant.getValue("outlook.calendar.file.required").equals("yes")))
        {
          String attachment = VcalUtil.getVcalformatForInterview(calGMT, "12", "00", applicantcalurl, subject, user1.getFirstName() + " " + user1.getLastName(), user1.getEmailId());
          


          attachements = new String[1];
          attachements[0] = attachment;
        }
        String replyTo = user1.getFirstName() + " " + user1.getLastName() + " " + "<" + user1.getEmailId() + ">";
        String applicanturl = Constant.getValue("external.url") + "applicant.do?method=applicantDetails&applicantId=" + applicant1.getApplicantId() + "&secureid=" + applicant1.getUuid();
        applicanturl = "<a href='" + applicanturl + "'" + "target='new'>" + Constant.getResourceStringValue("details", user1.getLocale()) + "</a>";
        
        applicant1.setApplicantUrl(applicanturl);
        String comment = event.getNotes() == null ? "" : event.getNotes();
        applicant1.setSchedulerComment(comment);
        applicant1.setModeOfInterview(modeofint);
        String reviewdate = DateUtil.convertDateToStringDate(cal.getTime(), DateUtil.getDatePatternFormat(user1.getLocale()));
        reviewdate = reviewdate + " " + user1.getTimezone().getTimezoneCode();
        applicant1.setInterviewDate(reviewdate);
        
        EmailTask emailtask = new EmailTask(replyTo, to, cc, bcc, replyTo, "dummysubject", null, "dummybody", attachements, 0, "scheduleInterview");
        emailtask.setFunctionType(Common.REVIEW_SCHEDULE_EMAIL_COMPONENT);
        emailtask.setUser(user1);
        emailtask.setApplicant(applicant1);
        
        EmailTaskManager.sendEmail(emailtask);
      }
      catch (Exception e)
      {
        logger.info("Error sending email" + e);
        logger.error(e);
        request.setAttribute("errorsendingemail", "yes");
      }
    }
    request.setAttribute("isinterviewscheduled", "yes");
    return mapping.findForward("interviewschedulebulkscr");
  }
  
  public ActionForward scheduleInterview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside scheduleInterview method");
    User user1 = (User)request.getSession().getAttribute("user_data");
    String eventype = request.getParameter("eventype");
    String applicantId = request.getParameter("applicantId");
    String interviewer = request.getParameter("reviewerid");
    String isgroup = request.getParameter("isgroup");
    logger.info("Inside scheduleInterview method isgroup" + isgroup);
    String hr = request.getParameter("hr");
    String minute = request.getParameter("minute");
    String ampm = request.getParameter("ampm");
    logger.info("Inside scheduleInterview method ampm" + ampm);
    String dhr = request.getParameter("dhr");
    String dminute = request.getParameter("dminute");
    String reschedule = request.getParameter("reschedule");
    String evid = request.getParameter("evid");
    String evname = request.getParameter("evname");
    logger.info("applicantId" + applicantId);
    logger.info("interviewer" + interviewer);
    

    JobApplicant applicant1 = BOFactory.getApplicantBO().getApplicantDetails(applicantId);
    logger.info("applicant1" + applicant1);
    



    ScheduleInterviewForm intform = (ScheduleInterviewForm)form;
    logger.info("intform.getReschedule()" + intform.getReschedule());
    int event_type = new Integer(eventype).intValue();
    JobApplicationEvent event = new JobApplicationEvent();
    event.setCreatedBy(user1.getUserName());
    event.setCreatedByName(user1.getFirstName() + " " + user1.getLastName());
    event.setCreatedDate(new Date());
    JobApplicant applicant = new JobApplicant();
    applicant.setApplicantId(new Long(applicantId).longValue());
    event.setApplicant(applicant);
    if (!StringUtils.isNullOrEmpty(evid))
    {
      event.setEvtmplId(new Long(evid).longValue());
      event.setEvTmplName(evname);
    }
    logger.info("interviewer" + interviewer);
    if ((!StringUtils.isNullOrEmpty(isgroup)) && (isgroup.equals("Y")))
    {
      UserGroup usergroup = new UserGroup();
      usergroup.setUsergrpId(new Long(interviewer).longValue());
      event.setOwnerGroup(usergroup);
      event.setIsGroup("Y");
      applicant1.setOwnerGroup(usergroup);
      applicant1.setIsGroup("Y");
    }
    else
    {
      User user = new User();
      user.setUserId(new Long(interviewer).longValue());
      event.setOwner(user);
      applicant1.setOwner(user);
      applicant1.setOwnerGroup(null);
      applicant1.setIsGroup("N");
      event.setOwnerGroup(null);
      event.setIsGroup("N");
    }
    logger.info("interviewer" + interviewer);
    applicant1.setInterview_organizer_id(user1.getUserId());
    

    String date = request.getParameter("date");
    String hour = hr == null ? "00" : hr;
    String mt = minute == null ? "00" : minute;
    date = date + " " + hour + ":" + mt + " " + ampm;
    logger.info("date" + date);
    String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
    datepattern = datepattern + " hh:mm aaa";
    
    logger.info("datepattern" + datepattern);
    logger.info("user1.getTimezone().getTimezoneCode()" + user1.getTimezone().getTimezoneCode());
    logger.info("TimeZone.getDefault().getID()" + TimeZone.getDefault().getID());
    
    String converteddate = DateUtil.convertFromTimezoneToTimezoneDate(date, datepattern, user1.getTimezone().getTimezoneCode(), TimeZone.getDefault().getID());
    
    logger.info("converteddate" + converteddate);
    
    Calendar cal = DateUtil.convertStringDateToCalendar(converteddate, datepattern);
    logger.info("cal.getTime()" + cal.getTime());
    event.setInterviewDate(cal.getTime());
    


    event.setDurationhr(dhr);
    event.setDurationminute(dminute);
    

    String convertedGMTdate = DateUtil.convertFromTimezoneToTimezoneDate(date, datepattern, user1.getTimezone().getTimezoneCode(), "GMT");
    Calendar calGMT = DateUtil.convertStringDateToCalendar(convertedGMTdate, datepattern);
    logger.info("calGMT" + calGMT);
    if (StringUtils.isNullOrEmpty(intform.getNotes())) {
      event.setNotes("");
    } else {
      event.setNotes(intform.getNotes());
    }
    event.setEventType(event_type);
    logger.info("event type" + event.getEventType());
    event.setStatus(0);
    event.setModofinterviewid(intform.getModofinterviewid());
    
    FormFile myFile = intform.getEvtmplFile();
    if (myFile != null)
    {
      String contentType = myFile.getContentType();
      String fileName = myFile.getFileName();
      int fileSize = myFile.getFileSize();
      byte[] fileData = myFile.getFileData();
      
      event.setEvtmplFileName(fileName);
      Blob blob = null;
      
      blob = new SerialBlob(fileData);
      event.setEvtmplFile(blob);
    }
    if (event.getEventType() == 0)
    {
      applicant1.setInterviewState("In Screening");
      event.setInterviewState("In Screening");
    }
    else
    {
      applicant1.setInterviewState("Interview Round-" + event.getEventType());
      event.setInterviewState("Interview Round-" + event.getEventType());
    }
    try
    {
      BOFactory.getApplicantTXBO().scheduleScreeningInterview(user1, applicant1, event, reschedule);
      




      List interviewerlist = new ArrayList();
      if ((!StringUtils.isNullOrEmpty(isgroup)) && (isgroup.equals("Y")))
      {
        UserGroup usrgrp = BOFactory.getLovBO().getUserGroup(new Long(interviewer).longValue());
        applicant1.setApplicantReviewerName(usrgrp.getUsergrpName());
        Set users = usrgrp.getUsers();
        if ((users != null) && (users.size() > 0))
        {
          Iterator itr = users.iterator();
          while (itr.hasNext())
          {
            User usr = (User)itr.next();
            String toemailid = "\"" + usr.getFirstName() + " " + usr.getLastName() + "\"" + " " + "<" + usr.getEmailId() + ">";
            interviewerlist.add(toemailid);
          }
        }
      }
      else
      {
        User usr = BOFactory.getLovBO().getUserFullNameEmailAndUserIdById(new Long(interviewer).longValue());
        
        String toemailid = "\"" + usr.getFirstName() + " " + usr.getLastName() + "\"" + " " + "<" + usr.getEmailId() + ">";
        interviewerlist.add(toemailid);
        applicant1.setApplicantReviewerName(usr.getFirstName() + " " + usr.getLastName());
      }
      String[] to = new String[interviewerlist.size()];
      interviewerlist.toArray(to);
      




      List cclist = BOFactory.getApplicantBO().getCCListForApplicant(user1, applicant1, null, EmailNotificationSettingFunction.FunctionNames.APPLICANT_REVIEW.toString(), true);
      
      String[] cc = new String[cclist.size()];
      cclist.toArray(cc);
      
      String[] bcc = null;
      

      String modeofint = "";
      if (event.getModofinterviewid() == 0) {
        modeofint = "Review";
      } else {
        modeofint = Constant.getModeOfInterviewName(event.getModofinterviewid());
      }
      String subject = applicant1.getInterviewState() + " " + modeofint + " " + applicant1.getFullName() + " " + "-" + applicant1.getJobTitle();
      

      logger.info("subject" + subject);
      String applicantcalurl = Constant.getValue("external.url") + "applicant.do?method=3DapplicantDetails&applicantId=3D" + applicant1.getApplicantId() + "&secureid=3D" + applicant1.getUuid();
      logger.info("applicantcalurl" + applicantcalurl);
      
      String[] attachements = null;
      if ((!StringUtils.isNullOrEmpty(Constant.getValue("outlook.calendar.file.required"))) && (Constant.getValue("outlook.calendar.file.required").equals("yes")))
      {
        String attachment = VcalUtil.getVcalformatForInterview(calGMT, dhr, dminute, applicantcalurl, subject, user1.getFirstName() + " " + user1.getLastName(), user1.getEmailId());
        logger.info("attachment" + attachment);
        
        attachements = new String[1];
        attachements[0] = attachment;
      }
      String replyTo = "\"" + user1.getFirstName() + " " + user1.getLastName() + "\"" + " " + "<" + user1.getEmailId() + ">";
      
      String applicanturl = Constant.getValue("external.url") + "applicant.do?method=applicantDetails&applicantId=" + applicant1.getApplicantId() + "&secureid=" + applicant1.getUuid();
      applicanturl = "<a href='" + applicanturl + "'" + "target='new'>" + Constant.getResourceStringValue("details", user1.getLocale()) + "</a>";
      
      applicant1.setApplicantUrl(applicanturl);
      String comment = event.getNotes() == null ? "" : event.getNotes();
      applicant1.setSchedulerComment(comment);
      applicant1.setModeOfInterview(modeofint);
      
      String reviewdate = DateUtil.convertSourceToTargetTimezone(event.getInterviewDate(), user1.getTimezone().getTimezoneCode(), user1.getLocale());
      reviewdate = reviewdate + " " + user1.getTimezone().getTimezoneCode();
      reviewdate = reviewdate + " " + Constant.getResourceStringValue("aquisition.applicant.intervieewlog.for", user1.getLocale()) + " " + event.getDurationhr() + Constant.getResourceStringValue("aquisition.applicant.intervieewlog.hr", user1.getLocale()) + " " + event.getDurationminute() + Constant.getResourceStringValue("aquisition.applicant.intervieewlog.minute", user1.getLocale());
      

      applicant1.setInterviewDate(reviewdate);
      
      EmailTask emailtask = new EmailTask(replyTo, to, cc, bcc, replyTo, "dummysubject", null, "dummybody", attachements, 0, null);
      emailtask.setFunctionType(Common.INTERVIEW_SCHEDULE_EMAIL_COMPONENT);
      emailtask.setUser(user1);
      emailtask.setApplicant(applicant1);
      EmailTaskManager.sendEmail(emailtask);
      


      request.setAttribute("isinterviewscheduled", "yes");
    }
    catch (Exception e)
    {
      logger.info("Error sending email" + e.getMessage());
      logger.error("Error sending email" + e);
      request.setAttribute("errorsendingemail", "yes");
      
      intform.setModeofInterviewList(Constant.getModeOfInterviews());
    }
    return mapping.findForward("interviewschedulescr");
  }
  
  public ActionForward holdsubmit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside holdsubmit method");
    
    String applicantId = request.getParameter("applicantId");
    String eventype = request.getParameter("eventype");
    

    ScheduleInterviewForm intform = (ScheduleInterviewForm)form;
    int event_type = 0;
    User user1 = (User)request.getSession().getAttribute("user_data");
    JobApplicant applicant1 = BOFactory.getApplicantBO().getApplicantDetails(applicantId);
    





    JobApplicationEvent event = new JobApplicationEvent();
    event.setInterviewerComments(intform.getInterviewercomments());
    if (StringUtils.isNullOrEmpty(intform.getInterviewercomments())) {
      return null;
    }
    event.setApplicant(applicant1);
    event.setEventType(event_type);
    event.setStatus(3);
    event.setUpdatedBy(user1.getUserName());
    event.setUpdatedDate(new Date());
    event.setUpdatedByName(user1.getFirstName() + " " + user1.getLastName());
    

    logger.info("intform.getEvTmplfeedback()" + intform.getEvTmplfeedback());
    event.setEvTmplfeedback(intform.getEvTmplfeedback());
    
    String interviewstate = "";
    String statusmsg = "";
    

    statusmsg = "OnHold";
    applicant1.setStatus("H");
    


    applicant1.setInterviewState("OnHold");
    
    User owner = UserDAO.getUserFullNameEmailAndUserId(applicant1.getInterview_organizer_id());
    

    event.setOwner(owner);
    event.setIsGroup("N");
    

    event.setInterviewState(interviewstate);
    


    applicant1.setOwner(owner);
    applicant1.setIsGroup("N");
    

    BOFactory.getApplicantTXBO().interviwercommmentonhold(event, applicant1, user1);
    try
    {
      String[] to = { owner.getEmailId() };
      


      List cclist = BOFactory.getApplicantBO().getCCListForApplicant(user1, applicant1, null, EmailNotificationSettingFunction.FunctionNames.APPLICANT_ON_HOLD.toString(), true);
      
      String[] cc = new String[cclist.size()];
      cclist.toArray(cc);
      
      String[] bcc = null;
      


      String subject = "Applicant on hold : " + applicant1.getFullName() + "-" + applicant1.getJobTitle();
      String applicanturl = Constant.getValue("external.url") + "applicant.do?method=applicantDetails&applicantId=" + applicant1.getApplicantId() + "&secureid=" + applicant1.getUuid();
      
      StringBuffer body = new StringBuffer();
      body.append("Applicant on hold: " + applicant1.getFullName());
      body.append("<BR>");
      body.append("Interview state : " + interviewstate);
      body.append("<BR>");
      body.append("<BR>");
      body.append("Details:");
      body.append("<BR>");
      body.append(applicanturl);
      body.append("<BR>");
      body.append("<BR>");
      body.append("Regards,");
      body.append("<BR>");
      body.append(user1.getPhoneOffice() + "  ");
      body.append(user1.getEmailId());
      body.append("<BR>");
      body.append(user1.getFirstName() + " " + user1.getLastName());
      EmailUtil emutil = new EmailUtil();
      String replyTo = user1.getFirstName() + " " + user1.getLastName() + " " + "<" + user1.getEmailId() + ">";
      
      EmailTask emailtask = new EmailTask(replyTo, to, cc, bcc, replyTo, subject, null, body.toString(), null, 0, "emailtypeonbehalf");
      EmailTaskManager.sendEmail(emailtask);
    }
    catch (Exception e)
    {
      logger.info("error in email sending" + e);
    }
    request.setAttribute("interviwercommmented", "yes");
    return mapping.findForward("holdpage");
  }
  
  public ActionForward interviwercommment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info(" .... Inside interviwercommment method .....");
    String feedback = request.getParameter("feedback");
    String applicantId = request.getParameter("applicantId");
    String eventype = request.getParameter("eventype");
    
    String otherrootcause = request.getParameter("otherrootcause");
    
    ScheduleInterviewForm intform = (ScheduleInterviewForm)form;
    int event_type = new Integer(eventype).intValue();
    User user1 = (User)request.getSession().getAttribute("user_data");
    
    JobApplicant applicant1 = BOFactory.getApplicantBO().getApplicantDetails(applicantId);
    
    logger.info(" .... Inside interviwercommment method ....feedback." + feedback);
    logger.info(" .... Inside interviwercommment method ....eventype." + eventype);
    JobApplicationEvent event1 = BOFactory.getApplicantBO().getApplicationEvent(applicant1.getApplicantId(), event_type);
    

    JobApplicationEvent event = new JobApplicationEvent();
    
    event.setInterviewerComments(intform.getInterviewercomments());
    
    event.setApplicant(applicant1);
    event.setEventType(event_type);
    event.setStatus(new Integer(feedback).intValue());
    event.setUpdatedBy(user1.getUserName());
    event.setUpdatedDate(new Date());
    event.setUpdatedByName(user1.getFirstName() + " " + user1.getLastName());
    

    logger.info("intform.getEvTmplfeedback()" + intform.getEvTmplfeedback());
    event.setEvTmplfeedback(intform.getEvTmplfeedback());
    
    String interviewstate = "";
    String statusmsg = "";
    if (new Integer(feedback).intValue() == 1)
    {
      statusmsg = "Cleared";
      applicant1.setStatus("A");
    }
    else if (new Integer(feedback).intValue() == 2)
    {
      statusmsg = "Rejected";
      applicant1.setStatus("R");
      applicant1.setRejectionreasonId(intform.getRejectionreasonId());
      applicant1.setRejectionreasonname(otherrootcause);
    }
    else if (new Integer(feedback).intValue() == 3)
    {
      statusmsg = "OnHold";
      applicant1.setStatus("H");
    }
    if (event_type == 0) {
      interviewstate = statusmsg + "-" + "In Screening";
    } else {
      interviewstate = statusmsg + "-" + "Interview Round" + "-" + event_type;
    }
    applicant1.setInterviewState(interviewstate);
    
    User owner = UserDAO.getUser(applicant1.getInterview_organizer_id());
    
    event.setOwner(owner);
    if (new Integer(feedback).intValue() == 3) {
      event.setOwner(user1);
    }
    if ((!StringUtils.isEmpty(applicant1.getIsGroup())) && (applicant1.getIsGroup().equals("Y")))
    {
      applicant1.setIsGroup("N");
      event.setIsGroup("N");
    }
    FormFile myFile = intform.getEvtmplFile();
    if (myFile != null)
    {
      String contentType = myFile.getContentType();
      String fileName = myFile.getFileName();
      int fileSize = myFile.getFileSize();
      byte[] fileData = myFile.getFileData();
      
      event.setEvtmplFileName(fileName);
      Blob blob = null;
      
      blob = new SerialBlob(fileData);
      event.setEvtmplFile(blob);
    }
    event.setInterviewState(interviewstate);
    



    applicant1.setOwner(owner);
    if (new Integer(feedback).intValue() == 3) {
      applicant1.setOwner(user1);
    }
    List appratingList = new ArrayList();
    List comptetencyList = BOFactory.getJobRequistionBO().getJobRequisionTemplateComptetencyList(applicant1.getReqId(), "job");
    if ((comptetencyList != null) && (comptetencyList.size() > 0)) {
      for (int i = 0; i < comptetencyList.size(); i++)
      {
        JobTemplateCompetency comp = (JobTemplateCompetency)comptetencyList.get(i);
        ApplicantRating rating = new ApplicantRating();
        
        rating.setApplicantId(applicant1.getApplicantId());
        rating.setApplicantEventId(event1.getJobAppEventId());
        rating.setIsMandatory(comp.getMandatory());
        rating.setMinimumRatingRequired(comp.getImportance());
        rating.setInterviewState(interviewstate);
        rating.setName(comp.getCharName());
        rating.setStatus("A");
        rating.setType(Common.COMPETENCY_TYPE);
        
        String yourrating = request.getParameter("comp_" + comp.getJbTmplcompId());
        if (!StringUtils.isNullOrEmpty(yourrating)) {
          rating.setYourrating(new Float(yourrating).floatValue());
        }
        appratingList.add(rating);
      }
    }
    if (event_type == 0)
    {
      List accList = BOFactory.getJobRequistionBO().getJobRequisionTemplateAccomplishmentList(applicant1.getReqId(), "job");
      if ((accList != null) && (accList.size() > 0)) {
        for (int i = 0; i < accList.size(); i++)
        {
          JobTemplateAccomplishment acc = (JobTemplateAccomplishment)accList.get(i);
          
          ApplicantRating rating = new ApplicantRating();
          
          rating.setApplicantId(applicant1.getApplicantId());
          rating.setApplicantEventId(event1.getJobAppEventId());
          rating.setIsMandatory(acc.getMandatory());
          rating.setMinimumRatingRequired(acc.getImportance());
          rating.setInterviewState(interviewstate);
          rating.setName(acc.getAccName());
          rating.setStatus("A");
          rating.setType(Common.ACCOMPLISHMENT_TYPE);
          
          String yourrating = request.getParameter("accom_" + acc.getJbTmplAccId());
          if (!StringUtils.isNullOrEmpty(yourrating)) {
            rating.setYourrating(new Float(yourrating).floatValue());
          }
          appratingList.add(rating);
        }
      }
    }
    BOFactory.getApplicantTXBO().interviwercommment(user1, event, applicant1, appratingList);
    

    logger.info(" .... Inside interviwercommment method ....feedback." + feedback);
    try
    {
      String[] to = { owner.getEmailId() };
      

      String[] bcc = null;
      





      List cclist = BOFactory.getApplicantBO().getCCListForApplicant(user1, applicant1, null, EmailNotificationSettingFunction.FunctionNames.APPLICANT_REVIEW_FEEDBACK.toString(), true);
      
      String[] cc = new String[cclist.size()];
      cclist.toArray(cc);
      

      String replyTo = "\"" + user1.getFirstName() + " " + user1.getLastName() + "\"" + " " + "<" + user1.getEmailId() + ">";
      
      String applicanturl = Constant.getValue("external.url") + "applicant.do?method=applicantDetails&applicantId=" + applicant1.getApplicantId() + "&secureid=" + applicant1.getUuid();
      applicanturl = "<a href='" + applicanturl + "'" + "target='new'>" + Constant.getResourceStringValue("details", owner.getLocale()) + "</a>";
      
      applicant1.setApplicantUrl(applicanturl);
      
      String comment = event.getInterviewerComments() == null ? "" : event.getInterviewerComments();
      applicant1.setInterviewerComments(comment);
      String reviewdate = DateUtil.convertDateToStringDate(event.getInterviewDate(), DateUtil.getDatePatternFormat(owner.getLocale()));
      applicant1.setInterviewDate(reviewdate);
      applicant1.setRecruiter(owner.getFirstName() + " " + owner.getLastName());
      EmailTask emailtask = new EmailTask(replyTo, to, cc, bcc, replyTo, "dummysubject", null, "dummybody", null, 0, null);
      if (event.getEventType() == 0) {
        emailtask.setFunctionType(Common.REVIEW_FEEDBACK_EMAIL_COMPONENT);
      } else {
        emailtask.setFunctionType(Common.INTERVIEW_FEEDBACK_EMAIL_COMPONENT);
      }
      emailtask.setUser(user1);
      emailtask.setApplicant(applicant1);
      EmailTaskManager.sendEmail(emailtask);
    }
    catch (Exception e)
    {
      logger.info("error in email sending" + e);
    }
    request.setAttribute("interviwercommmented", "yes");
    return mapping.findForward("interviwercommmentpage");
  }
  
  public ActionForward interviwercommmentpage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside interviwercommmentpage method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    String status = request.getParameter("status");
    String applicantId = request.getParameter("applicantId");
    String eventype = request.getParameter("eventype");
    String frompage = request.getParameter("frompage");
    String uuid = request.getParameter("uuid");
    if ((!StringUtils.isNullOrEmpty(frompage)) && (frompage.equals("task")) && 
      (!StringUtils.isNullOrEmpty(uuid)))
    {
      JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
      eventype = applicant.getInterviewState();
    }
    logger.info("eventype" + eventype);
    ScheduleInterviewForm intform = (ScheduleInterviewForm)form;
    intform.setApplicantId(new Long(applicantId).longValue());
    intform.setStatus(status);
    int eventtype = Common.getEventType(eventype);
    intform.setEventType(eventtype);
    









    intform.setRejectionreasonList(BOFactory.getLovBO().getAllApplicantRejectionResons());
    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetails(applicantId);
    List comptetencyList = BOFactory.getJobRequistionBO().getJobRequisionTemplateComptetencyList(applicant.getReqId(), "job");
    List accomplishmentList = BOFactory.getJobRequistionBO().getJobRequisionTemplateAccomplishmentList(applicant.getReqId(), "job");
    intform.setApplicantId(applicant.getApplicantId());
    intform.setAccomplishmentList(accomplishmentList);
    intform.setComptetencyList(comptetencyList);
    
    request.setAttribute("interviwercommmented", "no");
    return mapping.findForward("interviwercommmentpage");
  }
  
  public ActionForward holdpage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside holdpage method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    
    String status = request.getParameter("status");
    String applicantId = request.getParameter("applicantId");
    String eventype = request.getParameter("eventype");
    logger.info("eventype" + eventype);
    ScheduleInterviewForm intform = (ScheduleInterviewForm)form;
    intform.setApplicantId(new Long(applicantId).longValue());
    intform.setStatus(status);
    int eventtype = Common.getEventTypeCleared(eventype);
    logger.info("eventype" + eventype);
    intform.setEventType(eventtype);
    










    JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetails(applicantId);
    
    intform.setApplicantId(applicant.getApplicantId());
    

    request.setAttribute("interviwercommmented", "no");
    return mapping.findForward("holdpage");
  }
  
  public ActionForward interviwreassign(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside interviwreassign method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String applicantId = request.getParameter("applicantId");
    String status = request.getParameter("status");
    String eventype = request.getParameter("eventype");
    String frompage = request.getParameter("frompage");
    String uuid = request.getParameter("uuid");
    if ((!StringUtils.isNullOrEmpty(frompage)) && (frompage.equals("task")) && 
      (!StringUtils.isNullOrEmpty(uuid)))
    {
      JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
      eventype = applicant.getInterviewState();
    }
    logger.info("eventype" + eventype);
    
    ScheduleInterviewForm intform = (ScheduleInterviewForm)form;
    intform.setApplicantId(new Long(applicantId).longValue());
    intform.setStatus(status);
    intform.setEventType(Common.getEventType(eventype));
    request.setAttribute("interviwerreassigned", "no");
    return mapping.findForward("interviwreassign");
  }
  
  public ActionForward declineinterview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside declineinterview method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String applicantId = request.getParameter("applicantId");
    String status = request.getParameter("status");
    String eventype = request.getParameter("eventype");
    String frompage = request.getParameter("frompage");
    String uuid = request.getParameter("uuid");
    if ((!StringUtils.isNullOrEmpty(frompage)) && (frompage.equals("task")) && 
      (!StringUtils.isNullOrEmpty(uuid)))
    {
      JobApplicant applicant = BOFactory.getApplicantBO().getApplicantDetailswithUUID(applicantId, uuid);
      eventype = applicant.getInterviewState();
    }
    logger.info("eventype" + eventype);
    
    ScheduleInterviewForm intform = (ScheduleInterviewForm)form;
    intform.setApplicantId(new Long(applicantId).longValue());
    intform.setStatus(status);
    intform.setEventType(Common.getEventType(eventype));
    request.setAttribute("declineinterview", "no");
    return mapping.findForward("declineinterview");
  }
  
  public ActionForward declineinterviewsubmit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside declineinterviewsubmit method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    String applicantId = request.getParameter("applicantId");
    String eventype = request.getParameter("eventtype");
    User user1 = (User)request.getSession().getAttribute("user_data");
    ScheduleInterviewForm intform = (ScheduleInterviewForm)form;
    logger.info("eventype" + eventype);
    int eventypeint = new Integer(eventype).intValue();
    JobApplicant applicant1 = BOFactory.getApplicantBO().getApplicantDetails(applicantId);
    JobApplicationEvent event = BOFactory.getApplicantBO().getApplicationEvent(applicant1.getApplicantId(), eventypeint);
    User owner = UserDAO.getUserFullNameEmailAndUserId(applicant1.getInterview_organizer_id());
    applicant1.setOwner(owner);
    applicant1.setIsGroup("N");
    String interviewstate = "";
    if ((applicant1.getInterviewState() != null) && (applicant1.getInterviewState().equals("In Screening")))
    {
      interviewstate = "Application Submitted";
    }
    else if (eventypeint == 1)
    {
      interviewstate = "Cleared-In Screening";
    }
    else if (eventypeint > 1)
    {
      int tempeventtpe = eventypeint - 1;
      interviewstate = "Cleared-Interview Round-" + tempeventtpe;
    }
    else
    {
      interviewstate = applicant1.getInterviewState();
    }
    applicant1.setInterviewState(interviewstate);
    event.setInterviewState(interviewstate);
    event.setUpdatedBy(user1.getUserName());
    event.setUpdatedDate(new Date());
    event.setUpdatedByName(user1.getFirstName() + " " + user1.getLastName());
    
    event.setStatus(4);
    event.setInterviewerComments(intform.getNotes());
    



    BOFactory.getApplicantTXBO().declineInterview(applicant1, event, user1, intform.getNotes());
    








    intform.setApplicantId(new Long(applicantId).longValue());
    intform.setEventType(Common.getEventType(eventype));
    intform.setCurrentowner(owner.getFirstName() + " " + owner.getLastName());
    request.setAttribute("declineinterview", "yes");
    return mapping.findForward("declineinterview");
  }
  
  public ActionForward interviwreassignsubmit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside interviwreassignsubmit method");
    ActionErrors errors = new ActionErrors();
    ActionForward forward = new ActionForward();
    ScheduleInterviewForm intform = (ScheduleInterviewForm)form;
    String applicantId = request.getParameter("applicantId");
    String reviewerid = request.getParameter("reviewerid");
    String eventtype = request.getParameter("eventtype");
    String isgroup = request.getParameter("isgroup");
    String reviewername = request.getParameter("reviewername");
    logger.info("Inside interviwreassignsubmit method reviewername" + reviewername);
    User user1 = (User)request.getSession().getAttribute("user_data");
    
    JobApplicant applicant1 = BOFactory.getApplicantBO().getApplicantDetails(applicantId);
    




    String toownername = "";
    JobApplicationEvent jb = BOFactory.getApplicantBO().getApplicationEvent(applicant1.getApplicantId(), new Integer(eventtype).intValue());
    if ((!StringUtils.isNullOrEmpty(isgroup)) && (isgroup.equals("Y")))
    {
      UserGroup usergroup = new UserGroup();
      usergroup.setUsergrpId(new Long(reviewerid).longValue());
      jb.setOwnerGroup(usergroup);
      jb.setIsGroup("Y");
      applicant1.setOwnerGroup(usergroup);
      applicant1.setIsGroup("Y");
    }
    else
    {
      User user = new User();
      user.setUserId(new Long(reviewerid).longValue());
      jb.setOwner(user);
      applicant1.setOwner(user);
      applicant1.setOwnerGroup(null);
      applicant1.setIsGroup("N");
      jb.setOwnerGroup(null);
      jb.setIsGroup("N");
    }
    if (StringUtils.isNullOrEmpty(jb.getReassignedGraph()))
    {
      jb.setReassignedGraph(user1.getFirstName() + " " + user1.getLastName() + "<b>--></b>" + reviewername);
      jb.setReassignedCommentGraph(intform.getNotes());
    }
    else
    {
      jb.setReassignedGraph(jb.getReassignedGraph() + "<b>--></b>" + reviewername);
      jb.setReassignedCommentGraph(jb.getReassignedCommentGraph() + "---->" + intform.getNotes());
    }
    jb.setLastreassignedDate(new Date());
    jb.setLastreasignedBy(user1.getUserName());
    





    BOFactory.getApplicantTXBO().reassignInterview(user1, applicant1, jb, intform.getNotes());
    





    intform.setCurrentowner(reviewername);
    intform.setApplicantId(new Long(applicantId).longValue());
    request.setAttribute("interviwerreassigned", "yes");
    return mapping.findForward("interviwreassign");
  }
  
  public ActionForward addevaluationcriteria(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside addevaluationcriteria method");
    
    return mapping.findForward("addevaluationcriteria");
  }
  
  public ActionForward searchevaluationcriterias(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    logger.info("Inside searchevaluationcriterias method");
    String criteria = request.getParameter("criteria");
    
    List charList = LovOpsDAO.getCharacteristicsByCritera(criteria);
    ScheduleInterviewForm intform = (ScheduleInterviewForm)form;
    intform.setCharList(charList);
    return mapping.findForward("searchevaluationcriterias");
  }
}
