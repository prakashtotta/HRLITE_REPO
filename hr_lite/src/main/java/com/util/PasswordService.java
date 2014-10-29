package com.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.common.SystemUnavailableException;

public final class PasswordService
{
  private static PasswordService instance;
  
  public synchronized String encrypt(String plaintext)
    throws SystemUnavailableException
  {
    MessageDigest md = null;
    try
    {
      md = MessageDigest.getInstance("SHA");
    }
    catch (NoSuchAlgorithmException e)
    {
      throw new SystemUnavailableException(e.getMessage());
    }
    try
    {
      md.update(plaintext.getBytes("UTF-8"));
    }
    catch (UnsupportedEncodingException e)
    {
      throw new SystemUnavailableException(e.getMessage());
    }
    byte[] raw = md.digest();
    String hash = Base64.encodeBytes(raw);
    return hash;
  }
  
  public static synchronized PasswordService getInstance()
  {
    if (instance == null) {
      return new PasswordService();
    }
    return instance;
  }
  
  public static void main(String[] args)
    throws SystemUnavailableException
  {
    System.out.println(getInstance().encrypt("admin"));
    
    System.out.println(Math.random());
  }
}
