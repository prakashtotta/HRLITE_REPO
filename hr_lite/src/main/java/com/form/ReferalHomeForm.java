package com.form;

import org.apache.struts.action.ActionForm;

public class ReferalHomeForm
  extends ActionForm
{
  public String refferalactioncode;
  
  public String getRefferalactioncode()
  {
    return this.refferalactioncode;
  }
  
  public void setRefferalactioncode(String refferalactioncode)
  {
    this.refferalactioncode = refferalactioncode;
  }
}
