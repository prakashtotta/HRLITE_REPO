package com.form;

import java.util.List;
import org.apache.struts.action.ActionForm;

public class EvaluationTemplateForm
  extends ActionForm
{
  public long evtmplId;
  public String evTmplName;
  public String evTmplDesc;
  private String groupcharids;
  private List groupcharList;
  private List evaluationTmplList;
  
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
  
  public String getEvTmplDesc()
  {
    return this.evTmplDesc;
  }
  
  public void setEvTmplDesc(String evTmplDesc)
  {
    this.evTmplDesc = evTmplDesc;
  }
  
  public String getGroupcharids()
  {
    return this.groupcharids;
  }
  
  public void setGroupcharids(String groupcharids)
  {
    this.groupcharids = groupcharids;
  }
  
  public List getGroupcharList()
  {
    return this.groupcharList;
  }
  
  public void setGroupcharList(List groupcharList)
  {
    this.groupcharList = groupcharList;
  }
  
  public List getEvaluationTmplList()
  {
    return this.evaluationTmplList;
  }
  
  public void setEvaluationTmplList(List evaluationTmplList)
  {
    this.evaluationTmplList = evaluationTmplList;
  }
}
