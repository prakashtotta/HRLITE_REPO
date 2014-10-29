package com.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import org.w3c.tools.codec.Base64Encoder;


public class DesEncrypter
{
  Cipher ecipher;
  Cipher dcipher;
  
  public DesEncrypter(SecretKey key)
  {
    try
    {
      this.ecipher = Cipher.getInstance("DES");
      this.dcipher = Cipher.getInstance("DES");
      this.ecipher.init(1, key);
      this.dcipher.init(2, key);
    }
    catch (NoSuchPaddingException e) {}catch (NoSuchAlgorithmException e) {}catch (InvalidKeyException e) {}
  }
  
  public String encrypt(String str)
  {
    try
    {
      byte[] utf8 = str.getBytes("UTF8");
      

      byte[] enc = this.ecipher.doFinal(utf8);
      

      return Base64.encodeBytes(enc);
    }
    catch (BadPaddingException e) {}catch (IllegalBlockSizeException e) {}catch (UnsupportedEncodingException e) {}catch (IOException e) {}
    return null;
  }
  
  public String decrypt(String str)
  {
    try
    {
      byte[] dec = Base64.decode(str);
      

      byte[] utf8 = this.dcipher.doFinal(dec);
      

      return new String(utf8, "UTF8");
    }
    catch (BadPaddingException e) {}catch (IllegalBlockSizeException e) {}catch (UnsupportedEncodingException e) {}catch (IOException e) {}
    return null;
  }
  
  public static void main(String[] args)
    throws Exception
  {
    SecretKey key = KeyGenerator.getInstance("DES").generateKey();
    

    DesEncrypter encrypter = new DesEncrypter(key);
    

    String encrypted = encrypter.encrypt("s");
    System.out.println(encrypted);
    
    String decrypted = encrypter.decrypt(encrypted);
    System.out.println(decrypted);
    
    String tt = "applicantofferdetails&applicantId=6";
    tt = tt.substring(tt.lastIndexOf("applicantId") + 12, tt.length());
    System.out.println(tt);
  }
}
