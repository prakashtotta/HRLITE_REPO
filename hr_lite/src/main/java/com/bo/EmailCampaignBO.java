package com.bo;

import com.bean.User;
import com.bean.pool.EmailCampaign;
import com.dao.EmailCampaignDAO;
import java.util.List;

public class EmailCampaignBO
{
  EmailCampaignDAO emailcampaigndao;
  
  public List getAllEmailCampaign()
  {
    return this.emailcampaigndao.getAllEmailCampaign();
  }
  
  public List getAllEmailCampaignForPagination(User user, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return this.emailcampaigndao.getAllEmailCampaignForPagination(user, pageSize, startIndex, dir_str, sort_str);
  }
  
  public List getAllEmailCampaignDetails(User user)
  {
    return this.emailcampaigndao.getAllEmailCampaignDetails(user);
  }
  
  public EmailCampaign getEmailCampaignDetails(long emailCampaignId)
  {
    return this.emailcampaigndao.getEmailCampaignDetails(emailCampaignId);
  }
  
  public EmailCampaign saveEmailCampaign(EmailCampaign emailCampaign)
  {
    return this.emailcampaigndao.saveEmailCampaign(emailCampaign);
  }
  
  public EmailCampaign updateEmailCampaign(EmailCampaign emailCampaign)
  {
    return this.emailcampaigndao.updateEmailCampaign(emailCampaign);
  }
  
  public EmailCampaign deleteEmailCampaign(EmailCampaign emailCampaign)
  {
    return this.emailcampaigndao.deleteEmailCampaign(emailCampaign);
  }
  
  public EmailCampaignDAO getEmailcampaigndao()
  {
    return this.emailcampaigndao;
  }
  
  public void setEmailcampaigndao(EmailCampaignDAO emailcampaigndao)
  {
    this.emailcampaigndao = emailcampaigndao;
  }
}
