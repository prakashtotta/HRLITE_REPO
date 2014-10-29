package com.util;

import com.bean.JobApplicant;
import com.bean.User;
import com.bo.ApplicantBO;
import com.bo.BOFactory;
import com.bo.UserBO;
import com.common.AppContextUtil;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.ApplicationContext;

public class Test3
{
  public void copyApplicant()
  {
    JobApplicant app = BOFactory.getApplicantBO().getApplicantDetails("5046");
    
    User user1 = UserBO.getUserByUserId(1L);
    List errorList = new ArrayList();
    try
    {
      for (int i = 0; i < 1; i++)
      {
        app.setApplicantId(0L);
        app.setFullName("seqA" + i);
        BOFactory.getApplicantBO().saveApplicant(app, user1, null, null, null, errorList, null, null);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public void getApptest()
  {
    ApplicationContext appContext = AppContextUtil.getAppcontext();
    System.out.println(appContext.getBean("sessionFactory"));
  }
}
