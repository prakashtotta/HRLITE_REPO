package com.form;

import com.bean.WorkShift;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;

public class WorkShiftForm
  extends ActionForm
{
  public long shiftId;
  public String shiftName;
  public String shiftDesc;
  public String status;
  
  public long getShiftId()
  {
    return this.shiftId;
  }
  
  public void setShiftId(long shiftId)
  {
    this.shiftId = shiftId;
  }
  
  public String getShiftName()
  {
    return this.shiftName;
  }
  
  public void setShiftName(String shiftName)
  {
    this.shiftName = shiftName;
  }
  
  public String getShiftDesc()
  {
    return this.shiftDesc;
  }
  
  public void setShiftDesc(String shiftDesc)
  {
    this.shiftDesc = shiftDesc;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public WorkShift toValue(WorkShift workShift, HttpServletRequest request)
    throws Exception
  {
    workShift.setShiftName(this.shiftName);
    workShift.setShiftDesc(this.shiftDesc);
    
    return workShift;
  }
  
  public void fromValue(WorkShift workShift, HttpServletRequest request)
    throws Exception
  {
    this.shiftId = workShift.getShiftId();
    this.shiftName = workShift.getShiftName();
    this.shiftDesc = workShift.getShiftDesc();
    this.status = workShift.getStatus();
  }
}
