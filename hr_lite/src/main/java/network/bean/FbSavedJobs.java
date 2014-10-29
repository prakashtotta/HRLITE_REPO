package network.bean;

public class FbSavedJobs
{
  private long fbsaveJobId;
  private long userId;
  private long jobId;
  
  public long getFbsaveJobId()
  {
    return this.fbsaveJobId;
  }
  
  public void setFbsaveJobId(long fbsaveJobId)
  {
    this.fbsaveJobId = fbsaveJobId;
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
