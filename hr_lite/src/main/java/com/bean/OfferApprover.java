package com.bean;

import java.util.Date;

public class OfferApprover
{
  public long offerApproverId;
  public long applicantId;
  public long approverId;
  public String approverName;
  public long onbehalfApproverId;
  public String onbehalfApproverName;
  public Date approvedDate;
  public String approved = "N";
  public String isapprover = "Y";
  public String isreassigned = "N";
  public long reassignedBy;
  public String reassignedbyName;
  public String comment;
  public String reassignedcomment;
  public String reassignedgraph = "";
  public String reassignedgraphname = "";
  public Date reassignedDate;
  public int levelorder;
  public String isFromSystemRule = "N";
  public String isGroup = "N";
  
  public String getIsFromSystemRule()
  {
    return this.isFromSystemRule;
  }
  
  public void setIsFromSystemRule(String isFromSystemRule)
  {
    this.isFromSystemRule = isFromSystemRule;
  }
  
  public long getOfferApproverId()
  {
    return this.offerApproverId;
  }
  
  public void setOfferApproverId(long offerApproverId)
  {
    this.offerApproverId = offerApproverId;
  }
  
  public long getApplicantId()
  {
    return this.applicantId;
  }
  
  public void setApplicantId(long applicantId)
  {
    this.applicantId = applicantId;
  }
  
  public long getApproverId()
  {
    return this.approverId;
  }
  
  public void setApproverId(long approverId)
  {
    this.approverId = approverId;
  }
  
  public String getApproverName()
  {
    return this.approverName;
  }
  
  public void setApproverName(String approverName)
  {
    this.approverName = approverName;
  }
  
  public Date getApprovedDate()
  {
    return this.approvedDate;
  }
  
  public void setApprovedDate(Date approvedDate)
  {
    this.approvedDate = approvedDate;
  }
  
  public String getApproved()
  {
    return this.approved;
  }
  
  public void setApproved(String approved)
  {
    this.approved = approved;
  }
  
  public String getIsapprover()
  {
    return this.isapprover;
  }
  
  public void setIsapprover(String isapprover)
  {
    this.isapprover = isapprover;
  }
  
  public String getIsreassigned()
  {
    return this.isreassigned;
  }
  
  public void setIsreassigned(String isreassigned)
  {
    this.isreassigned = isreassigned;
  }
  
  public long getReassignedBy()
  {
    return this.reassignedBy;
  }
  
  public void setReassignedBy(long reassignedBy)
  {
    this.reassignedBy = reassignedBy;
  }
  
  public String getReassignedbyName()
  {
    return this.reassignedbyName;
  }
  
  public void setReassignedbyName(String reassignedbyName)
  {
    this.reassignedbyName = reassignedbyName;
  }
  
  public String getComment()
  {
    return this.comment;
  }
  
  public void setComment(String comment)
  {
    this.comment = comment;
  }
  
  public String getReassignedcomment()
  {
    return this.reassignedcomment;
  }
  
  public void setReassignedcomment(String reassignedcomment)
  {
    this.reassignedcomment = reassignedcomment;
  }
  
  public String getReassignedgraph()
  {
    return this.reassignedgraph;
  }
  
  public void setReassignedgraph(String reassignedgraph)
  {
    this.reassignedgraph = reassignedgraph;
  }
  
  public String getReassignedgraphname()
  {
    return this.reassignedgraphname;
  }
  
  public void setReassignedgraphname(String reassignedgraphname)
  {
    this.reassignedgraphname = reassignedgraphname;
  }
  
  public Date getReassignedDate()
  {
    return this.reassignedDate;
  }
  
  public void setReassignedDate(Date reassignedDate)
  {
    this.reassignedDate = reassignedDate;
  }
  
  public int getLevelorder()
  {
    return this.levelorder;
  }
  
  public void setLevelorder(int levelorder)
  {
    this.levelorder = levelorder;
  }
  
  public String getIsGroup()
  {
    return this.isGroup;
  }
  
  public void setIsGroup(String isGroup)
  {
    this.isGroup = isGroup;
  }
  
  public long getOnbehalfApproverId()
  {
    return this.onbehalfApproverId;
  }
  
  public void setOnbehalfApproverId(long onbehalfApproverId)
  {
    this.onbehalfApproverId = onbehalfApproverId;
  }
  
  public String getOnbehalfApproverName()
  {
    return this.onbehalfApproverName;
  }
  
  public void setOnbehalfApproverName(String onbehalfApproverName)
  {
    this.onbehalfApproverName = onbehalfApproverName;
  }
}
