package com.common;

public class ValidationException
  extends Exception
{
  private String errorCode;
  private String errorDescription;
  
  public ValidationException(String errorCode, String errorDescription)
  {
    this.errorCode = errorCode;
    this.errorDescription = errorDescription;
  }
  
  public ValidationException(String errorDescription)
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
