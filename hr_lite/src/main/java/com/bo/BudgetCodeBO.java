package com.bo;

import com.bean.User;
import com.dao.LovDAO;
import java.util.List;

public class BudgetCodeBO
{
  LovDAO lovdao;
  
  public List getAllBudgetDetailsForPagination(User user, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.lovdao.getAllBudgetCodeDetailsForPagination(user, pageSize, startIndex, dir_str, sort_str);
  }
  
  public int getCountOfBudgetCodes(User user)
  {
    return this.lovdao.getCountOfBudgetCodes(user);
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
