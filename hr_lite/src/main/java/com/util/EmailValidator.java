package com.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator
{
  private Pattern pattern;
  private Matcher matcher;
  private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
  
  public EmailValidator()
  {
    this.pattern = Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
  }
  
  public boolean validate(String hex)
  {
    this.matcher = this.pattern.matcher(hex);
    return this.matcher.matches();
  }
}
