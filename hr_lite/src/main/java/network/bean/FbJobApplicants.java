package network.bean;

public class FbJobApplicants
{
  private long fbjobApplicantsId;
  private long userId;
  private long jobId;
  
  public long getFbjobApplicantsId()
  {
    return this.fbjobApplicantsId;
  }
  
  public void setFbjobApplicantsId(long fbjobApplicantsId)
  {
    this.fbjobApplicantsId = fbjobApplicantsId;
  }
  
  public long getUserId()
  {
    return this.userId;
  }
  
  public void setUserId(long userId)
  {
    this.userId = userId;
  }
  
  public long getJobId()
  {
    return this.jobId;
  }
  
  public void setJobId(long jobId)
  {
    this.jobId = jobId;
  }
}
