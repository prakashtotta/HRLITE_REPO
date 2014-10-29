package com.common;

public class PluginException
  extends RuntimeException
{
  private String errorCode;
  private String errorDescription;
  
  public PluginException(String errorCode, String errorDescription)
  {
    this.errorCode = errorCode;
    this.errorDescription = errorDescription;
  }
  
  public PluginException(String message, Throwable cause)
  {
    super(message, cause);
  }
  
  public PluginException(String errorDescription)
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
