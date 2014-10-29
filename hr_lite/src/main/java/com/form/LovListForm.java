package com.form;

import com.bean.lov.LovList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;

public class LovListForm
  extends ActionForm
{
  public long lovListId;
  public String lovListCode;
  public String lovListValueCode;
  public String status;
  public String lovListValueName;
  public long localeId;
  private List localeList;
  public List lovlistvaluecodeList;
  
  public List getLovlistvaluecodeList()
  {
    return this.lovlistvaluecodeList;
  }
  
  public void setLovlistvaluecodeList(List lovlistvaluecodeList)
  {
    this.lovlistvaluecodeList = lovlistvaluecodeList;
  }
  
  public List getLocaleList()
  {
    return this.localeList;
  }
  
  public void setLocaleList(List localeList)
  {
    this.localeList = localeList;
  }
  
  public long getLovListId()
  {
    return this.lovListId;
  }
  
  public void setLovListId(long lovListId)
  {
    this.lovListId = lovListId;
  }
  
  public String getLovListCode()
  {
    return this.lovListCode;
  }
  
  public void setLovListCode(String lovListCode)
  {
    this.lovListCode = lovListCode;
  }
  
  public String getLovListValueCode()
  {
    return this.lovListValueCode;
  }
  
  public void setLovListValueCode(String lovListValueCode)
  {
    this.lovListValueCode = lovListValueCode;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public String getLovListValueName()
  {
    return this.lovListValueName;
  }
  
  public void setLovListValueName(String lovListValueName)
  {
    this.lovListValueName = lovListValueName;
  }
  
  public long getLocaleId()
  {
    return this.localeId;
  }
  
  public void setLocaleId(long localeId)
  {
    this.localeId = localeId;
  }
  
  public LovList toValue(LovList lovList, HttpServletRequest request)
    throws Exception
  {
    lovList.setLovListCode(this.lovListCode);
    lovList.setLocaleId(this.localeId);
    lovList.setStatus("A");
    lovList.setLovListValueCode(this.lovListValueCode);
    lovList.setLovListValueName(this.lovListValueName);
    return lovList;
  }
  
  public void fromValue(LovList lovList, HttpServletRequest request)
    throws Exception
  {
    this.lovListId = lovList.getLovListId();
    this.lovListCode = lovList.getLovListCode();
    this.localeId = lovList.getLocaleId();
    this.status = lovList.getStatus();
    this.lovListValueCode = lovList.getLovListValueCode();
    this.lovListValueName = lovList.getLovListValueName();
  }
}
