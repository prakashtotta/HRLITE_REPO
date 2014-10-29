package com.bean;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;
import java.util.List;

public class JobApplicationEvent
  implements Serializable
{
  private long jobAppEventId;
  private JobApplicant applicant;
  private User owner;
  private UserGroup ownerGroup;
  private String isGroup = "N";
  private String createdBy;
  private String createdByName;
  private Date createdDate;
  private Date interviewDate;
  private String durationhr = "0";
  private String durationminute = "00";
  private int eventType = 0;
  private String notes;
  private String interviewerComments;
  private int status;
  private Date updatedDate;
  private String updatedBy;
  private String updatedByName;
  private int modofinterviewid;
  private String evtmplFileName;
  private Blob evtmplFile;
  private long testId;
  public String interviewState;
  public String reassignedGraph;
  public String reassignedCommentGraph;
  private Date lastreassignedDate;
  private String lastreasignedBy;
  public long evtmplId;
  public String evTmplName;
  public String evTmplfeedback;
  private List competencyRatingList;
  private List accomplishmentRatingList;
  
  public String getUpdatedByName()
  {
    return this.updatedByName;
  }
  
  public void setUpdatedByName(String updatedByName)
  {
    this.updatedByName = updatedByName;
  }
  
  public Date getUpdatedDate()
  {
    return this.updatedDate;
  }
  
  public void setUpdatedDate(Date updatedDate)
  {
    this.updatedDate = updatedDate;
  }
  
  public String getUpdatedBy()
  {
    return this.updatedBy;
  }
  
  public void setUpdatedBy(String updatedBy)
  {
    this.updatedBy = updatedBy;
  }
  
  public long getJobAppEventId()
  {
    return this.jobAppEventId;
  }
  
  public void setJobAppEventId(long jobAppEventId)
  {
    this.jobAppEventId = jobAppEventId;
  }
  
  public JobApplicant getApplicant()
  {
    return this.applicant;
  }
  
  public void setApplicant(JobApplicant applicant)
  {
    this.applicant = applicant;
  }
  
  public User getOwner()
  {
    return this.owner;
  }
  
  public void setOwner(User owner)
  {
    this.owner = owner;
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
  
  public String getInterviewerComments()
  {
    return this.interviewerComments;
  }
  
  public void setInterviewerComments(String interviewerComments)
  {
    this.interviewerComments = interviewerComments;
  }
  
  public int getStatus()
  {
    return this.status;
  }
  
  public void setStatus(int status)
  {
    this.status = status;
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
  
  public Blob getEvtmplFile()
  {
    return this.evtmplFile;
  }
  
  public void setEvtmplFile(Blob evtmplFile)
  {
    this.evtmplFile = evtmplFile;
  }
  
  public Date getInterviewDate()
  {
    return this.interviewDate;
  }
  
  public void setInterviewDate(Date interviewDate)
  {
    this.interviewDate = interviewDate;
  }
  
  public String getDurationhr()
  {
    return this.durationhr;
  }
  
  public void setDurationhr(String durationhr)
  {
    this.durationhr = durationhr;
  }
  
  public String getDurationminute()
  {
    return this.durationminute;
  }
  
  public void setDurationminute(String durationminute)
  {
    this.durationminute = durationminute;
  }
  
  public String getInterviewState()
  {
    return this.interviewState;
  }
  
  public void setInterviewState(String interviewState)
  {
    this.interviewState = interviewState;
  }
  
  public String getReassignedGraph()
  {
    return this.reassignedGraph;
  }
  
  public void setReassignedGraph(String reassignedGraph)
  {
    this.reassignedGraph = reassignedGraph;
  }
  
  public String getReassignedCommentGraph()
  {
    return this.reassignedCommentGraph;
  }
  
  public void setReassignedCommentGraph(String reassignedCommentGraph)
  {
    this.reassignedCommentGraph = reassignedCommentGraph;
  }
  
  public Date getLastreassignedDate()
  {
    return this.lastreassignedDate;
  }
  
  public void setLastreassignedDate(Date lastreassignedDate)
  {
    this.lastreassignedDate = lastreassignedDate;
  }
  
  public String getLastreasignedBy()
  {
    return this.lastreasignedBy;
  }
  
  public void setLastreasignedBy(String lastreasignedBy)
  {
    this.lastreasignedBy = lastreasignedBy;
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
  
  public List getCompetencyRatingList()
  {
    return this.competencyRatingList;
  }
  
  public void setCompetencyRatingList(List competencyRatingList)
  {
    this.competencyRatingList = competencyRatingList;
  }
  
  public List getAccomplishmentRatingList()
  {
    return this.accomplishmentRatingList;
  }
  
  public void setAccomplishmentRatingList(List accomplishmentRatingList)
  {
    this.accomplishmentRatingList = accomplishmentRatingList;
  }
  
  public UserGroup getOwnerGroup()
  {
    return this.ownerGroup;
  }
  
  public void setOwnerGroup(UserGroup ownerGroup)
  {
    this.ownerGroup = ownerGroup;
  }
  
  public String getIsGroup()
  {
    return this.isGroup;
  }
  
  public void setIsGroup(String isGroup)
  {
    this.isGroup = isGroup;
  }
  
  public long getTestId()
  {
    return this.testId;
  }
  
  public void setTestId(long testId)
  {
    this.testId = testId;
  }
  
  public String getCreatedByName()
  {
    return this.createdByName;
  }
  
  public void setCreatedByName(String createdByName)
  {
    this.createdByName = createdByName;
  }
}
