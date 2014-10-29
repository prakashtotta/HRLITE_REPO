package com.form;

import com.bean.DeclinedResons;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;

public class DeclinedResonsForm
  extends ActionForm
{
  public int offerdeclinedreasonId;
  public String offerdecliedreasonName;
  public String offerdecliedreasonDesc;
  public String resonType;
  public String status;
  public long super_user_key;
  private List reasonTypeList;
  
  public List getReasonTypeList()
  {
    return this.reasonTypeList;
  }
  
  public void setReasonTypeList(List reasonTypeList)
  {
    this.reasonTypeList = reasonTypeList;
  }
  
  public long getSuper_user_key()
  {
    return this.super_user_key;
  }
  
  public void setSuper_user_key(long superUserKey)
  {
    this.super_user_key = superUserKey;
  }
  
  public int getOfferdeclinedreasonId()
  {
    return this.offerdeclinedreasonId;
  }
  
  public void setOfferdeclinedreasonId(int offerdeclinedreasonId)
  {
    this.offerdeclinedreasonId = offerdeclinedreasonId;
  }
  
  public String getOfferdecliedreasonName()
  {
    return this.offerdecliedreasonName;
  }
  
  public void setOfferdecliedreasonName(String offerdecliedreasonName)
  {
    this.offerdecliedreasonName = offerdecliedreasonName;
  }
  
  public String getOfferdecliedreasonDesc()
  {
    return this.offerdecliedreasonDesc;
  }
  
  public void setOfferdecliedreasonDesc(String offerdecliedreasonDesc)
  {
    this.offerdecliedreasonDesc = offerdecliedreasonDesc;
  }
  
  public String getResonType()
  {
    return this.resonType;
  }
  
  public void setResonType(String resonType)
  {
    this.resonType = resonType;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public DeclinedResons toValue(DeclinedResons declinedResons, HttpServletRequest request)
    throws Exception
  {
    declinedResons.setOfferdecliedreasonName(this.offerdecliedreasonName);
    declinedResons.setOfferdecliedreasonDesc(this.offerdecliedreasonDesc);
    
    return declinedResons;
  }
  
  public void fromValue(DeclinedResons declinedResons, HttpServletRequest request)
    throws Exception
  {
    this.offerdeclinedreasonId = declinedResons.getOfferdeclinedreasonId();
    this.offerdecliedreasonName = declinedResons.getOfferdecliedreasonName();
    this.offerdecliedreasonDesc = declinedResons.getOfferdecliedreasonDesc();
    
    this.status = declinedResons.getStatus();
  }
}
