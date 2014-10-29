package com.bo;

import com.bean.Organization;
import com.bean.RefferalBudgetCode;
import com.bean.RefferalScheme;
import com.bean.User;
import com.dao.LovDAO;
import com.dao.LovOpsTXDAO;
import com.dao.RefferalDAO;
import java.util.List;

public class RefferalSchemeBO
{
  LovDAO lovdao;
  LovOpsTXDAO lovopstxdao;
  RefferalDAO refferaldao;
  
  public List getRefferalSchemeDetailsForPagination(User user, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.lovdao.getRefferalSchemeDetailsForPagination(user, pageSize, startIndex, dir_str, sort_str);
  }
  
  public int getCountOfAllRefferalSchemes(User user)
  {
    return this.lovdao.getCountOfAllRefferalSchemes(user);
  }
  
  public List getRefferalSchemeDetailsForPagination(User user, int pageSize, int startIndex)
  {
    return this.lovdao.getRefferalSchemeTypeDetailsForPagination(user, pageSize, startIndex);
  }
  
  public List getAllRefferalSchemeDetails(User user)
  {
    return this.lovdao.getAllRefferalSchemeTypeDetails(user);
  }
  
  public RefferalScheme getRefferalSchemeDetails(long id)
  {
    return this.refferaldao.getRefferalSchemeDetails(id);
  }
  
  public String getUOMWithCurrency(long refferalBudgetcodeId, String uomscheme)
  {
    String uom = "";
    

    RefferalBudgetCode refferalbudgetCode = this.lovdao.getRefferalBudgetCode(String.valueOf(refferalBudgetcodeId));
    uom = refferalbudgetCode.getOrg().getCurrencyCode();
    

    return uom;
  }
  
  public LovDAO getLovdao()
  {
    return this.lovdao;
  }
  
  public void setLovdao(LovDAO lovdao)
  {
    this.lovdao = lovdao;
  }
  
  public LovOpsTXDAO getLovopstxdao()
  {
    return this.lovopstxdao;
  }
  
  public void setLovopstxdao(LovOpsTXDAO lovopstxdao)
  {
    this.lovopstxdao = lovopstxdao;
  }
  
  public RefferalDAO getRefferaldao()
  {
    return this.refferaldao;
  }
  
  public void setRefferaldao(RefferalDAO refferaldao)
  {
    this.refferaldao = refferaldao;
  }
}
