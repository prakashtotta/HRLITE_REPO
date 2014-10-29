package com.util;

import java.io.PrintStream;

public class PasswordGenerator
{
  public static String getPassword(int n)
  {
    char[] pw = new char[n];
    int c = 65;
    int r1 = 0;
    for (int i = 0; i < n; i++)
    {
      r1 = (int)(Math.random() * 3.0D);
      switch (r1)
      {
      case 0: 
        c = 48 + (int)(Math.random() * 10.0D); break;
      case 1: 
        c = 97 + (int)(Math.random() * 26.0D); break;
      case 2: 
        c = 65 + (int)(Math.random() * 26.0D);
      }
      pw[i] = ((char)c);
    }
    return new String(pw);
  }
  
  public static void main(String[] args)
  {
    System.out.println(getPassword(8));
  }
}
