package com.common;

public class SystemUnavailableException
  extends Exception
{
  String msg;
  
  public SystemUnavailableException(String msg)
  {
    this.msg = msg;
  }
  
  public String getMessage()
  {
    return this.msg;
  }
}
