package com.bo;

import com.bean.User;
import com.bean.system.SystemRule;
import com.bean.system.SystemRuleUser;
import com.dao.RuleTXDAO;
import java.util.List;
import org.apache.log4j.Logger;

public class RuleTXBO
{
  protected static final Logger logger = Logger.getLogger(RuleTXBO.class);
  RuleTXDAO ruletxdao;
  
  public void updateSystemRule(SystemRule sysrule, User user)
  {
    logger.info("inside updateSystemRule");
    logger.info("inside updateSystemRule" + sysrule.getRuleUsers().size());
    List sysruleusers = sysrule.getRuleUsers();
    this.ruletxdao.updateSystemRule(sysrule);
    this.ruletxdao.deleteSystemRule(sysrule.getSystemRuleId());
    if (sysruleusers != null) {
      for (int i = 0; i < sysruleusers.size(); i++)
      {
        SystemRuleUser sysuser = (SystemRuleUser)sysruleusers.get(i);
        this.ruletxdao.saveSystemRuleUsers(sysuser);
      }
    }
  }
  
  public void saveSystemRule(SystemRule sysrule, User user)
  {
    logger.info("inside saveSystemRule");
    
    List sysruleusers = sysrule.getRuleUsers();
    this.ruletxdao.saveSystemRule(sysrule);
    this.ruletxdao.deleteSystemRule(sysrule.getSystemRuleId());
    if (sysruleusers != null) {
      for (int i = 0; i < sysruleusers.size(); i++)
      {
        SystemRuleUser sysuser = (SystemRuleUser)sysruleusers.get(i);
        sysuser.setSystemRuleId(sysrule.getSystemRuleId());
        this.ruletxdao.saveSystemRuleUsers(sysuser);
      }
    }
  }
  
  public RuleTXDAO getRuletxdao()
  {
    return this.ruletxdao;
  }
  
  public void setRuletxdao(RuleTXDAO ruletxdao)
  {
    this.ruletxdao = ruletxdao;
  }
}
