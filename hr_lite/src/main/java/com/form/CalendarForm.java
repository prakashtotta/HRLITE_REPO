package com.form;

import java.util.List;
import org.apache.struts.action.ActionForm;

public class CalendarForm
  extends ActionForm
{
  public List taskList;
  
  public List getTaskList()
  {
    return this.taskList;
  }
  
  public void setTaskList(List taskList)
  {
    this.taskList = taskList;
  }
}
