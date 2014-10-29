package com.util;

import com.resources.Constant;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class EmailValidateUtils
{
  public static boolean isEmailIdBelongsToDomainsList(String emailId)
  {
    boolean success = false;
    String domainnames = Constant.getValue("employee.refferal.domain.names.validate");
    StringTokenizer dtokens = new StringTokenizer(domainnames, ",");
    List domainList = new ArrayList();
    while (dtokens.hasMoreTokens())
    {
      String keyname = dtokens.nextToken();
      domainList.add(keyname);
    }
    if (emailId != null)
    {
      emailId = emailId.substring(emailId.indexOf("@") + 1);
      if (domainList.contains(emailId)) {
        success = true;
      }
    }
    return success;
  }
}
