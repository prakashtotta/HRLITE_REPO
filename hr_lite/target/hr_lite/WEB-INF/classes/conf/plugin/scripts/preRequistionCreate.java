try {
com.ValueBean vbean = new com.ValueBean((com.bean.JobRequisition) JobRequisitionObject,(com.bean.User) UserObject );
com.UETest uetest = new com.UETest();
uetest.startRun(vbean);
} catch ( Exception ex ) {
  ex.printStackTrace();
  throw new com.common.PluginException(ex.getMessage(), ex);
}