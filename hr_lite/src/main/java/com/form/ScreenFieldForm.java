package com.form;

import com.bean.filter.ScreenFields;
import com.util.HTMLInputFilter;
import com.util.XSSKeyUtil;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;

public class ScreenFieldForm
  extends ActionForm
{
  public long screenfieldId;
  public String screenCode;
  public String fieldcode;
  public String fieldtype;
  public String isvisible = "Y";
  public String issystem = "Y";
  public String isMandatory = "Y";
  public String status = "Y";
  public List screenCodesList;
  public List screenfieldsList;
  
  public List getScreenfieldsList()
  {
    return this.screenfieldsList;
  }
  
  public void setScreenfieldsList(List screenfieldsList)
  {
    this.screenfieldsList = screenfieldsList;
  }
  
  public List getScreenCodesList()
  {
    return this.screenCodesList;
  }
  
  public void setScreenCodesList(List screenCodesList)
  {
    this.screenCodesList = screenCodesList;
  }
  
  public long getScreenfieldId()
  {
    return this.screenfieldId;
  }
  
  public void setScreenfieldId(long screenfieldId)
  {
    this.screenfieldId = screenfieldId;
  }
  
  public String getScreenCode()
  {
    return this.screenCode;
  }
  
  public void setScreenCode(String screenCode)
  {
    this.screenCode = screenCode;
  }
  
  public String getFieldcode()
  {
    return this.fieldcode;
  }
  
  public void setFieldcode(String fieldcode)
  {
    this.fieldcode = fieldcode;
  }
  
  public String getFieldtype()
  {
    return this.fieldtype;
  }
  
  public void setFieldtype(String fieldtype)
  {
    this.fieldtype = fieldtype;
  }
  
  public String getIsvisible()
  {
    return this.isvisible;
  }
  
  public void setIsvisible(String isvisible)
  {
    this.isvisible = isvisible;
  }
  
  public String getIssystem()
  {
    return this.issystem;
  }
  
  public void setIssystem(String issystem)
  {
    this.issystem = issystem;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public String getIsMandatory()
  {
    return this.isMandatory;
  }
  
  public void setIsMandatory(String isMandatory)
  {
    this.isMandatory = isMandatory;
  }
  
  public void fromValue(ScreenFields screenFields, HttpServletRequest request)
    throws Exception
  {
    this.isvisible = screenFields.getIsvisible();
    this.isMandatory = screenFields.getIsMandatory();
    this.screenCode = screenFields.getScreenCode();
    this.fieldcode = screenFields.getFieldcode();
    this.status = screenFields.getStatus();
  }
  
  public ScreenFields toValue(ScreenFields screenFields, HttpServletRequest request)
    throws Exception
  {
    HTMLInputFilter filter = new HTMLInputFilter();
    
    List xxsList = XSSKeyUtil.getListOfValuesByKey("ScreenFieldForm");
    
    screenFields.setIsvisible(this.isvisible);
    screenFields.setIsMandatory(this.isMandatory);
    
    return screenFields;
  }
}
