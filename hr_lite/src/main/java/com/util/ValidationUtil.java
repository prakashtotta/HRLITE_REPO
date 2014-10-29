package com.util;

import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtil
{
  public static boolean isEmailValid(String email)
  {
    boolean isValid = false;
    

    String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
    CharSequence inputStr = email;
    
    Pattern pattern = Pattern.compile(expression, 2);
    Matcher matcher = pattern.matcher(inputStr);
    if (matcher.matches()) {
      isValid = true;
    }
    return isValid;
  }
  
  public static boolean isPhoneNumberValid(String phoneNumber)
  {
    boolean isValid = false;
    













    String expression = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$";
    CharSequence inputStr = phoneNumber;
    Pattern pattern = Pattern.compile(expression);
    Matcher matcher = pattern.matcher(inputStr);
    if (matcher.matches()) {
      isValid = true;
    }
    return isValid;
  }
  
  public static boolean isSSNValid(String ssn)
  {
    boolean isValid = false;
    










    String expression = "^\\d{3}[- ]?\\d{2}[- ]?\\d{4}$";
    CharSequence inputStr = ssn;
    Pattern pattern = Pattern.compile(expression);
    Matcher matcher = pattern.matcher(inputStr);
    if (matcher.matches()) {
      isValid = true;
    }
    return isValid;
  }
  
  public static boolean isNumeric(String number)
  {
    boolean isValid = false;
    








    String expression = "^[-+]?[0-9]*\\.?[0-9]+$";
    CharSequence inputStr = number;
    Pattern pattern = Pattern.compile(expression);
    Matcher matcher = pattern.matcher(inputStr);
    if (matcher.matches()) {
      isValid = true;
    }
    return isValid;
  }
  
  public static void main(String[] args)
  {
    System.out.println(isEmailValid("satya@sa.com"));
  }
}
