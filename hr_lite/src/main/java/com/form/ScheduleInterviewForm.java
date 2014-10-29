package com.form;

import com.bean.User;
import java.util.Date;
import java.util.List;
import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class ScheduleInterviewForm
  extends ActionForm
{
  private long jobAppEventId;
  private long applicantId;
  private long userId;
  private String createdBy;
  private Date createdDate;
  private int eventType = 0;
  private String notes;
  private List userList;
  private String interviewDate;
  private String fromTime;
  private String toTime;
  private String status;
  private List charList;
  private List modeofInterviewList;
  private int modofinterviewid;
  private String evtmplFileName;
  private FormFile evtmplFile;
  private String interviewercomments;
  private List applicantList;
  private String currentowner;
  private String reschedule;
  public long evtmplId;
  public String evTmplName;
  public String evTmplfeedback;
  private List rejectionreasonList;
  private int rejectionreasonId;
  List comptetencyList;
  List accomplishmentList;
  private String date;
  private User hiringMgr;
  
  public String getDate()
  {
    return this.date;
  }
  
  public void setDate(String date)
  {
    this.date = date;
  }
  
  public List getCharList()
  {
    return this.charList;
  }
  
  public void setCharList(List charList)
  {
    this.charList = charList;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public String getFromTime()
  {
    return this.fromTime;
  }
  
  public void setFromTime(String fromTime)
  {
    this.fromTime = fromTime;
  }
  
  public String getToTime()
  {
    return this.toTime;
  }
  
  public void setToTime(String toTime)
  {
    this.toTime = toTime;
  }
  
  public List getUserList()
  {
    return this.userList;
  }
  
  public void setUserList(List userList)
  {
    this.userList = userList;
  }
  
  public long getUserId()
  {
    return this.userId;
  }
  
  public void setUserId(long userId)
  {
    this.userId = userId;
  }
  
  public long getJobAppEventId()
  {
    return this.jobAppEventId;
  }
  
  public void setJobAppEventId(long jobAppEventId)
  {
    this.jobAppEventId = jobAppEventId;
  }
  
  public long getApplicantId()
  {
    return this.applicantId;
  }
  
  public void setApplicantId(long applicantId)
  {
    this.applicantId = applicantId;
  }
  
  public String getCreatedBy()
  {
    return this.createdBy;
  }
  
  public void setCreatedBy(String createdBy)
  {
    this.createdBy = createdBy;
  }
  
  public Date getCreatedDate()
  {
    return this.createdDate;
  }
  
  public void setCreatedDate(Date createdDate)
  {
    this.createdDate = createdDate;
  }
  
  public int getEventType()
  {
    return this.eventType;
  }
  
  public void setEventType(int eventType)
  {
    this.eventType = eventType;
  }
  
  public String getNotes()
  {
    return this.notes;
  }
  
  public void setNotes(String notes)
  {
    this.notes = notes;
  }
  
  public String getInterviewDate()
  {
    return this.interviewDate;
  }
  
  public void setInterviewDate(String interviewDate)
  {
    this.interviewDate = interviewDate;
  }
  
  public List getModeofInterviewList()
  {
    return this.modeofInterviewList;
  }
  
  public void setModeofInterviewList(List modeofInterviewList)
  {
    this.modeofInterviewList = modeofInterviewList;
  }
  
  public int getModofinterviewid()
  {
    return this.modofinterviewid;
  }
  
  public void setModofinterviewid(int modofinterviewid)
  {
    this.modofinterviewid = modofinterviewid;
  }
  
  public String getEvtmplFileName()
  {
    return this.evtmplFileName;
  }
  
  public void setEvtmplFileName(String evtmplFileName)
  {
    this.evtmplFileName = evtmplFileName;
  }
  
  public FormFile getEvtmplFile()
  {
    return this.evtmplFile;
  }
  
  public void setEvtmplFile(FormFile evtmplFile)
  {
    this.evtmplFile = evtmplFile;
  }
  
  public String getInterviewercomments()
  {
    return this.interviewercomments;
  }
  
  public void setInterviewercomments(String interviewercomments)
  {
    this.interviewercomments = interviewercomments;
  }
  
  public List getApplicantList()
  {
    return this.applicantList;
  }
  
  public void setApplicantList(List applicantList)
  {
    this.applicantList = applicantList;
  }
  
  public String getCurrentowner()
  {
    return this.currentowner;
  }
  
  public void setCurrentowner(String currentowner)
  {
    this.currentowner = currentowner;
  }
  
  public String getReschedule()
  {
    return this.reschedule;
  }
  
  public void setReschedule(String reschedule)
  {
    this.reschedule = reschedule;
  }
  
  public long getEvtmplId()
  {
    return this.evtmplId;
  }
  
  public void setEvtmplId(long evtmplId)
  {
    this.evtmplId = evtmplId;
  }
  
  public String getEvTmplName()
  {
    return this.evTmplName;
  }
  
  public void setEvTmplName(String evTmplName)
  {
    this.evTmplName = evTmplName;
  }
  
  public String getEvTmplfeedback()
  {
    return this.evTmplfeedback;
  }
  
  public void setEvTmplfeedback(String evTmplfeedback)
  {
    this.evTmplfeedback = evTmplfeedback;
  }
  
  public List getRejectionreasonList()
  {
    return this.rejectionreasonList;
  }
  
  public void setRejectionreasonList(List rejectionreasonList)
  {
    this.rejectionreasonList = rejectionreasonList;
  }
  
  public int getRejectionreasonId()
  {
    return this.rejectionreasonId;
  }
  
  public void setRejectionreasonId(int rejectionreasonId)
  {
    this.rejectionreasonId = rejectionreasonId;
  }
  
  public List getComptetencyList()
  {
    return this.comptetencyList;
  }
  
  public void setComptetencyList(List comptetencyList)
  {
    this.comptetencyList = comptetencyList;
  }
  
  public List getAccomplishmentList()
  {
    return this.accomplishmentList;
  }
  
  public void setAccomplishmentList(List accomplishmentList)
  {
    this.accomplishmentList = accomplishmentList;
  }
  
  public User getHiringMgr()
  {
    return this.hiringMgr;
  }
  
  public void setHiringMgr(User hiringMgr)
  {
    this.hiringMgr = hiringMgr;
  }
}
