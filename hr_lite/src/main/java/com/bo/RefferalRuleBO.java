package com.bo;

import com.bean.User;
import com.dao.LovDAO;
import java.util.List;

public class RefferalRuleBO
{
  LovDAO lovdao;
  
  public List getAllRefferalRuleDetailsForPagination(User user, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.lovdao.getAllRefferalRuleDetailsForPagination(user, pageSize, startIndex, dir_str, sort_str);
  }
  
  public List getAllRefferalRuleDetails(User user)
  {
    return this.lovdao.getAllRefferalRuleDetails(user);
  }
  
  public LovDAO getLovdao()
  {
    return this.lovdao;
  }
  
  public void setLovdao(LovDAO lovdao)
  {
    this.lovdao = lovdao;
  }
}
