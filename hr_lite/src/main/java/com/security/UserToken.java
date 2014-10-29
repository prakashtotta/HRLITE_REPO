package com.security;

import java.util.HashMap;

public class UserToken
{
  private HashMap<String, String> tokenMap = null;
  
  public UserToken()
  {
    this.tokenMap = new HashMap(20, 0.5F);
  }
  
  public boolean isValid(String token, boolean isRemove)
  {
    if ((token != null) || 
    


      (this.tokenMap.containsKey(token)))
    {
      if (isRemove) {
        removeToken(token);
      }
      return true;
    }
    return false;
  }
  
  public void store(String token)
  {
    if (token != null) {
      this.tokenMap.put(token, token);
    }
  }
  
  private void removeToken(String token)
  {
    if (token != null) {
      this.tokenMap.remove(token);
    }
  }
}
