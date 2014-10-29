package com.bean.pkey;

import java.io.Serializable;

public class ActionsAttachmentPK
  implements Serializable
{
  public long idvalue;
  private String action = "";
  
  public ActionsAttachmentPK(long idvalue, String action)
  {
    this.idvalue = idvalue;
    this.action = action;
  }
  
  public long getIdvalue()
  {
    return this.idvalue;
  }
  
  public String getAction()
  {
    return this.action;
  }
  
  public void setIdvalue(long idvalue)
  {
    this.idvalue = idvalue;
  }
  
  public void setAction(String action)
  {
    this.action = action;
  }
}
