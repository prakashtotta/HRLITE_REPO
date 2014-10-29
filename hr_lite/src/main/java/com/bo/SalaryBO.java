package com.bo;

import com.bean.User;
import com.dao.LovDAO;
import java.util.List;

public class SalaryBO
{
  LovDAO lovdao;
  
  public List getAllSalaryDetailsForPagination(User user, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.lovdao.getAllSalaryDetailsForPagination(user, pageSize, startIndex, dir_str, sort_str);
  }
  
  public List getAllSalaryDetails(User user)
  {
    return this.lovdao.getAllSalaryDetails(user);
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
