package com.form;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class CharForm
  extends ActionForm
{
  public long charId;
  public String charName;
  public String charDesc;
  public int weight;
  
  public long getCharId()
  {
    return this.charId;
  }
  
  public void setCharId(long charId)
  {
    this.charId = charId;
  }
  
  public String getCharName()
  {
    return this.charName;
  }
  
  public void setCharName(String charName)
  {
    this.charName = charName;
  }
  
  public String getCharDesc()
  {
    return this.charDesc;
  }
  
  public void setCharDesc(String charDesc)
  {
    this.charDesc = charDesc;
  }
  
  public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)
  {
    ActionErrors actionErrors = new ActionErrors();
    if (this.charName.length() < 1) {
      actionErrors.add("charName", new ActionMessage("error.char.name.required"));
    }
    if (this.charDesc.length() < 1) {
      actionErrors.add("age", new ActionMessage("error.char.desc.required"));
    }
    return actionErrors;
  }
  
  public void reset(ActionMapping mapping, HttpServletRequest request)
  {
    this.charName = "";
    this.charDesc = "";
  }
  
  public int getWeight()
  {
    return this.weight;
  }
  
  public void setWeight(int weight)
  {
    this.weight = weight;
  }
}
