package com.util;

import com.bean.JobRequisition;
import com.bean.User;
import com.common.PluginException;
import com.plugin.IntercepterImpl;

public class TestUE
{
  public void preJobRequitionCreate()
  {
    JobRequisition requisition = new JobRequisition();
    requisition.setJobTitle("testplugin");
    User user = new User();
    try
    {
      IntercepterImpl ue = new IntercepterImpl();
      ue.setObject("JobRequisitionObject", requisition);
      ue.setObject("UserObject", user);
      ue.firePreRequistionCreateScript();
    }
    catch (PluginException ex)
    {
      ex.printStackTrace();
    }
  }
  
  public void postJobRequitionCreate()
  {
    JobRequisition requisition = new JobRequisition();
    requisition.setJobTitle("testplugin");
    User user = new User();
    try
    {
      IntercepterImpl ue = new IntercepterImpl();
      ue.setObject("JobRequisitionObject", requisition);
      ue.setObject("UserObject", user);
      ue.firePostRequistionCreateScript();
    }
    catch (PluginException ex)
    {
      ex.printStackTrace();
    }
  }
}
