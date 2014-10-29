package com.bean;

import java.util.List;

public class DataTableBean
{
  public List valueList;
  public int totalcount = 0;
  
  public List getValueList()
  {
    return this.valueList;
  }
  
  public void setValueList(List valueList)
  {
    this.valueList = valueList;
  }
  
  public int getTotalcount()
  {
    return this.totalcount;
  }
  
  public void setTotalcount(int totalcount)
  {
    this.totalcount = totalcount;
  }
}
