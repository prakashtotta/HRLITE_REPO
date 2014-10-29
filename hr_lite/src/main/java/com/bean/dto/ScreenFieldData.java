package com.bean.dto;

import java.util.List;
import java.util.Map;

public class ScreenFieldData
{
  Map<String, List<String>> screenMap;
  List screenfieldList;
  List screenfieldListWorkTab;
  Map<String, List<String>> screenMapWorkExp;
  List screenfieldListEduTab;
  Map<String, List<String>> screenMapEducationDetials;
  
  public Map<String, List<String>> getScreenMap()
  {
    return this.screenMap;
  }
  
  public void setScreenMap(Map<String, List<String>> screenMap)
  {
    this.screenMap = screenMap;
  }
  
  public List getScreenfieldList()
  {
    return this.screenfieldList;
  }
  
  public void setScreenfieldList(List screenfieldList)
  {
    this.screenfieldList = screenfieldList;
  }
  
  public List getScreenfieldListWorkTab()
  {
    return this.screenfieldListWorkTab;
  }
  
  public void setScreenfieldListWorkTab(List screenfieldListWorkTab)
  {
    this.screenfieldListWorkTab = screenfieldListWorkTab;
  }
  
  public Map<String, List<String>> getScreenMapWorkExp()
  {
    return this.screenMapWorkExp;
  }
  
  public void setScreenMapWorkExp(Map<String, List<String>> screenMapWorkExp)
  {
    this.screenMapWorkExp = screenMapWorkExp;
  }
  
  public List getScreenfieldListEduTab()
  {
    return this.screenfieldListEduTab;
  }
  
  public void setScreenfieldListEduTab(List screenfieldListEduTab)
  {
    this.screenfieldListEduTab = screenfieldListEduTab;
  }
  
  public Map<String, List<String>> getScreenMapEducationDetials()
  {
    return this.screenMapEducationDetials;
  }
  
  public void setScreenMapEducationDetials(Map<String, List<String>> screenMapEducationDetials)
  {
    this.screenMapEducationDetials = screenMapEducationDetials;
  }
}
