package com.bo;

import com.bean.User;
import com.dao.LovDAO;
import java.util.List;

public class RefferalBudgetCodeBO
{
  LovDAO lovdao;
  
  public List getAllRefferalBudgetDetailsForPagination(User user, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.lovdao.getAllRefferalBudgetDetailsForPagination(user, pageSize, startIndex, dir_str, sort_str);
  }
  
  public List getAllRefferalBudgetDetails(User user)
  {
    return this.lovdao.getAllRefferalBudgetCodeDetails(user);
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
