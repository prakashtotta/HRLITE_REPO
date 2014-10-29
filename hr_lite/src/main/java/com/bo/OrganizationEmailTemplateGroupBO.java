package com.bo;

import com.dao.LovDAO;
import java.util.List;

public class OrganizationEmailTemplateGroupBO
{
  LovDAO lovdao;
  
  public List getAllOrgEmailTemplateGroupDetailsForPagination(int pageSize, int startIndex)
  {
    return this.lovdao.getAllorganizationemailtemplateForPagination(pageSize, startIndex);
  }
  
  public List getAllOrgEmailTemplateGroupDetails()
  {
    return this.lovdao.getAllorganizationemailtemplate();
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
