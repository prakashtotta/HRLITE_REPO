package com.bean;

import java.util.Date;

public class RequistionExamQnsAssign
{
  public long reqexqnid;
  public long jobreqid;
  public int examId;
  public long qnsgrpId;
  public String mcomckexamsetcomment;
  public String questiongroupcomment;
  private double passPercentage;
  public Date updatedDate;
  public String updatedBy;
  private String examname;
  private String questionairename;
  
  public long getReqexqnid()
  {
    return this.reqexqnid;
  }
  
  public void setReqexqnid(long reqexqnid)
  {
    this.reqexqnid = reqexqnid;
  }
  
  public long getJobreqid()
  {
    return this.jobreqid;
  }
  
  public void setJobreqid(long jobreqid)
  {
    this.jobreqid = jobreqid;
  }
  
  public int getExamId()
  {
    return this.examId;
  }
  
  public void setExamId(int examId)
  {
    this.examId = examId;
  }
  
  public long getQnsgrpId()
  {
    return this.qnsgrpId;
  }
  
  public void setQnsgrpId(long qnsgrpId)
  {
    this.qnsgrpId = qnsgrpId;
  }
  
  public String getMcomckexamsetcomment()
  {
    return this.mcomckexamsetcomment;
  }
  
  public void setMcomckexamsetcomment(String mcomckexamsetcomment)
  {
    this.mcomckexamsetcomment = mcomckexamsetcomment;
  }
  
  public String getQuestiongroupcomment()
  {
    return this.questiongroupcomment;
  }
  
  public void setQuestiongroupcomment(String questiongroupcomment)
  {
    this.questiongroupcomment = questiongroupcomment;
  }
  
  public double getPassPercentage()
  {
    return this.passPercentage;
  }
  
  public void setPassPercentage(double passPercentage)
  {
    this.passPercentage = passPercentage;
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
  
  public String getExamname()
  {
    return this.examname;
  }
  
  public void setExamname(String examname)
  {
    this.examname = examname;
  }
  
  public String getQuestionairename()
  {
    return this.questionairename;
  }
  
  public void setQuestionairename(String questionairename)
  {
    this.questionairename = questionairename;
  }
}
