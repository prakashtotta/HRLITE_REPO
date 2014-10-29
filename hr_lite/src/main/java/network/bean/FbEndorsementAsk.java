package network.bean;

public class FbEndorsementAsk
{
  public long endaskId;
  private String fromFbId;
  private String toFbId;
  private String status;
  
  public long getEndaskId()
  {
    return this.endaskId;
  }
  
  public void setEndaskId(long endaskId)
  {
    this.endaskId = endaskId;
  }
  
  public String getFromFbId()
  {
    return this.fromFbId;
  }
  
  public void setFromFbId(String fromFbId)
  {
    this.fromFbId = fromFbId;
  }
  
  public String getToFbId()
  {
    return this.toFbId;
  }
  
  public void setToFbId(String toFbId)
  {
    this.toFbId = toFbId;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
}
