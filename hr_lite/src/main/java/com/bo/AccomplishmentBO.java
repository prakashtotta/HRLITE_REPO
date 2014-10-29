package com.bo;

import com.bean.User;
import com.dao.LovDAO;
import java.util.List;

public class AccomplishmentBO
{
  LovDAO lovdao;
  
  public List getAllAccopmlishmentDetailsForPagination(User user, int pageSize, int startIndex, String dir_str, String sort_str, String accomplishmentName)
  {
    return this.lovdao.getAllAccopmlishmentDetailsForPagination(user, pageSize, startIndex, dir_str, sort_str, accomplishmentName);
  }
  
  public List getAllAccopmlishmentDetails(User user, String accomplishmentName)
  {
    return this.lovdao.getAllAccopmlishmentDetails(user, accomplishmentName);
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
