try {
com.ApplicantValueBean vbean = new com.ApplicantValueBean((com.bean.JobApplicant) ApplicantObject,(com.bean.User) UserObject );
com.AppUETest uetest = new com.AppUETest();
uetest.startRun(vbean);
} catch ( Exception ex ) {
  ex.printStackTrace();
  System.out.println("satyaaaaaaaaaaaaaaaa"+ex.getMessage());
  throw new com.common.PluginException(ex.getMessage(), ex);
}