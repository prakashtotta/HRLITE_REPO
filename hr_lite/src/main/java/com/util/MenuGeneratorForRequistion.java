package com.util;

import com.bean.JobTemplateApprovers;
import com.bean.User;
import com.bo.BOFactory;
import com.bo.JobRequistionBO;
import com.form.JobRequisitionForm;
import java.util.List;

public class MenuGeneratorForRequistion
{
  public static String getRequistionMenu(User user, JobRequisitionForm form)
  {
    String str = "";
    if (isRequistionApproverLoggedIn(user, form))
    {
      str = "{text:'Approve',classname:'approve',onclick:{fn:clickHandler}},";
      str = str + "{text:'Reject',classname:'reject',onclick:{fn:clickHandler}},";
      str = str + "{text:'Re-Assign',classname:'reassign',onclick:{fn:clickHandler}},";
    }
    if (StringUtils.isNullOrEmpty(str)) {
      str = str.substring(0, str.length() - 1);
    }
    return str;
  }
  
  private static boolean isRequistionApproverLoggedIn(User user, JobRequisitionForm jobreqform)
  {
    List approverList = jobreqform.getApproversList();
    
    boolean isapproverloggedin = false;
    if ((jobreqform.getIsapprovalInitiated() == 1) && (jobreqform.getIsrejected() != 1)) {
      for (int j = 0; j < approverList.size(); j++)
      {
        isapproverloggedin = false;
        
        JobTemplateApprovers lastjapp1 = null;
        if (j != 0) {
          lastjapp1 = (JobTemplateApprovers)approverList.get(j - 1);
        }
        JobTemplateApprovers japp1 = (JobTemplateApprovers)approverList.get(j);
        if ((BOFactory.getJobRequistionBO().isLoggedInUserIsApprover(user.getUserId(), japp1.getUserId(), japp1.getIsGroup())) && (!japp1.getApproved().equals("Y")))
        {
          if (lastjapp1 != null)
          {
            if ((!lastjapp1.getApproved().equals("Y")) || (japp1.getApproved().equals("Y"))) {
              break;
            }
            isapproverloggedin = true; break;
          }
          if (japp1.getApproved().equals("Y")) {
            break;
          }
          isapproverloggedin = true; break;
        }
      }
    }
    return isapproverloggedin;
  }
}
