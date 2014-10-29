package com.bo;

import com.dao.RuleDAO;
import java.util.Map;

public class SystemRuleBO
{
  public static Map getSystemRulesForPagination(long orgId, int pageSize, int startIndex, String dir_str, String sort_str)
  {
    return RuleDAO.getSystemRulesForPagination(orgId, pageSize, startIndex, dir_str, sort_str);
  }
}
