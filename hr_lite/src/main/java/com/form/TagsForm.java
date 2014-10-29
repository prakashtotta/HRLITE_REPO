package com.form;

import java.util.List;
import org.apache.struts.action.ActionForm;

public class TagsForm
  extends ActionForm
{
  public long tagId;
  public String tagName;
  public String tagType;
  public String status;
  public List tagtypeList;
  
  public long getTagId()
  {
    return this.tagId;
  }
  
  public void setTagId(long tagId)
  {
    this.tagId = tagId;
  }
  
  public String getTagName()
  {
    return this.tagName;
  }
  
  public void setTagName(String tagName)
  {
    this.tagName = tagName;
  }
  
  public String getTagType()
  {
    return this.tagType;
  }
  
  public void setTagType(String tagType)
  {
    this.tagType = tagType;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public List getTagtypeList()
  {
    return this.tagtypeList;
  }
  
  public void setTagtypeList(List tagtypeList)
  {
    this.tagtypeList = tagtypeList;
  }
}
