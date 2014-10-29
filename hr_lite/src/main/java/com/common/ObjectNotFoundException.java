package com.common;

public class ObjectNotFoundException
  extends Exception
{
  private String errorCode;
  private String errorDescription;
  
  public ObjectNotFoundException(String errorCode, String errorDescription)
  {
    this.errorCode = errorCode;
    this.errorDescription = errorDescription;
  }
  
  public ObjectNotFoundException(String errorDescription)
  {
    this.errorDescription = errorDescription;
  }
  
  public String getErrorCode()
  {
    return this.errorCode;
  }
  
  public void setErrorCode(String errorCode)
  {
    this.errorCode = errorCode;
  }
  
  public String getErrorDescription()
  {
    return this.errorDescription;
  }
  
  public void setErrorDescription(String errorDescription)
  {
    this.errorDescription = errorDescription;
  }
}
