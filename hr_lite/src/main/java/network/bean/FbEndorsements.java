package network.bean;

import java.util.Date;

public class FbEndorsements
{
  public long endId;
  private String fromFbId;
  private String toFbId;
  private String endorse;
  private Date createdDate;
  
  public long getEndId()
  {
    return this.endId;
  }
  
  public void setEndId(long endId)
  {
    this.endId = endId;
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
  
  public String getEndorse()
  {
    return this.endorse;
  }
  
  public void setEndorse(String endorse)
  {
    this.endorse = endorse;
  }
  
  public Date getCreatedDate()
  {
    return this.createdDate;
  }
  
  public void setCreatedDate(Date createdDate)
  {
    this.createdDate = createdDate;
  }
}
