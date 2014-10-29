package network.bean;

import java.util.List;

public class FbDTO
{
  private int count;
  private List lst;
  private boolean isSaved;
  private int jobCount;
  
  public int getCount()
  {
    return this.count;
  }
  
  public void setCount(int count)
  {
    this.count = count;
  }
  
  public boolean isSaved()
  {
    return this.isSaved;
  }
  
  public void setSaved(boolean isSaved)
  {
    this.isSaved = isSaved;
  }
  
  public List getLst()
  {
    return this.lst;
  }
  
  public void setLst(List lst)
  {
    this.lst = lst;
  }
  
  public int getJobCount()
  {
    return this.jobCount;
  }
  
  public void setJobCount(int jobCount)
  {
    this.jobCount = jobCount;
  }
}
