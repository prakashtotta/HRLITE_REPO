package com.form;

import java.util.List;
import org.apache.struts.action.ActionForm;

public class GroupCharForm
  extends ActionForm
{
  private long groupCharId;
  private String groupCharName;
  private String groupCharDesc;
  private String charids;
  private List charList;
  
  public long getGroupCharId()
  {
    return this.groupCharId;
  }
  
  public void setGroupCharId(long groupCharId)
  {
    this.groupCharId = groupCharId;
  }
  
  public String getGroupCharName()
  {
    return this.groupCharName;
  }
  
  public void setGroupCharName(String groupCharName)
  {
    this.groupCharName = groupCharName;
  }
  
  public String getGroupCharDesc()
  {
    return this.groupCharDesc;
  }
  
  public void setGroupCharDesc(String groupCharDesc)
  {
    this.groupCharDesc = groupCharDesc;
  }
  
  public List getCharList()
  {
    return this.charList;
  }
  
  public void setCharList(List charList)
  {
    this.charList = charList;
  }
  
  public String getCharids()
  {
    return this.charids;
  }
  
  public void setCharids(String charids)
  {
    this.charids = charids;
  }
}
