package com.util;

import java.io.PrintStream;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class EncryptDecrypt
{
  private static final String ALGORITHM = "DES";
  private static final String MODE = "/ECB/NoPadding";
  private static final String ENCRYPTION_OPTION = "-e";
  private static final String DECRYPTION_OPTION = "-d";
  private static final String KEY_OPTION = "-k";
  private static final String KEYGENERATION_OPTION = "-genkey";
  private static final String SECRETKEY = "7C713FC1DB2BF5B0";
  private static final int KEYLENGTH = 16;
  private static final byte[] hexByte = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70 };
  
  private static byte[] hexToBinArray(byte[] hex)
  {
    byte[] bin = new byte[(short)(hex.length / 2)];
    int b;
    for (int h = b = 0; b < hex.length / 2; b++) {
      bin[b] = ((byte)((hexToBin((char)hex[(h++)]) << 4) + hexToBin((char)hex[(h++)])));
    }
    return bin;
  }
  
  private static byte[] binToHexArray(byte[] bin)
  {
    short asciiLength = (short)(bin.length * 2);
    byte[] ascii = new byte[asciiLength];
    int b;
    for (int a = b = 0; b < bin.length; b++)
    {
      ascii[(a++)] = hexByte[(bin[b] >> 4 & 0xF)];
      ascii[(a++)] = hexByte[(bin[b] & 0xF)];
    }
    return ascii;
  }
  
  private static byte hexToBin(char c)
  {
    if ((c >= '0') && (c <= '9')) {
      return (byte)(c - '0');
    }
    if ((c >= 'A') && (c <= 'F')) {
      return (byte)(c - 'A' + 10);
    }
    if ((c >= 'a') && (c <= 'f')) {
      return (byte)(c - 'a' + 10);
    }
    throw new IllegalArgumentException("hexToBin(): Non-hex digit");
  }
  
  private static Cipher getCipher(String key, int cryptMode)
  {
    byte[] keyData = new byte[key.length() / 2];
    for (int i = 0; i < key.length(); i += 2) {
      keyData[(i / 2)] = ((byte)((hexToBin(key.charAt(i)) & 0xF) << 4 | hexToBin(key.charAt(i + 1)) & 0xF));
    }
    SecretKeySpec secretKey = new SecretKeySpec(keyData, "DES");
    
    Cipher cipher = null;
    try
    {
      cipher = Cipher.getInstance("DES/ECB/NoPadding");
      cipher.init(cryptMode, secretKey);
    }
    catch (Throwable e)
    {
      e.printStackTrace();
    }
    return cipher;
  }
  
  public static String encrypt(String plainText)
  {
    return encrypt("7C713FC1DB2BF5B0", plainText);
  }
  
  public static String encrypt(String encryptionKey, String plainText)
  {
    byte[] asciiEncryption = null;
    try
    {
      if ((plainText == null) || ("".equals(plainText))) {
        return "";
      }
      Cipher encryptCipher = getCipher(encryptionKey, 1);
      
      String rawString = new String(plainText);
      if (rawString.length() % 8 != 0)
      {
        int padLength = 8 - rawString.length() % 8;
        for (int i = 0; i < padLength; i++) {
          rawString = rawString + " ";
        }
      }
      byte[] dataBytes = rawString.getBytes();
      int dataLength = dataBytes.length;
      int paddedLength;
      if (dataLength % 8 != 0) {
        paddedLength = (dataLength / 8 + 1) * 8;
      } else {
        paddedLength = dataLength;
      }
      byte[] paddedBuffer = new byte[paddedLength];
      for (int b = 0; b < dataLength; b++) {
        paddedBuffer[b] = dataBytes[b];
      }
     
      byte[] binaryData = encryptCipher.doFinal(paddedBuffer, 0, paddedLength);
      asciiEncryption = binToHexArray(binaryData);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return new String(asciiEncryption);
  }
  
  public static String decrypt(String encryptedText)
  {
    return decrypt("7C713FC1DB2BF5B0", encryptedText);
  }
  
  public static String decrypt(String key, String encryptedText)
  {
    byte[] decryptedBytes = null;
    try
    {
      if ((encryptedText == null) || ("".equals(encryptedText))) {
        return "";
      }
      byte[] binaryBytes = hexToBinArray(encryptedText.getBytes());
      Cipher decryptCipher = getCipher(key, 2);
      decryptedBytes = decryptCipher.doFinal(binaryBytes);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return new String(decryptedBytes).trim();
  }
  
  public static String generateRandomKey(int length)
  {
    byte[] key = new byte[length];
    Random r = new Random(System.currentTimeMillis());
    for (int d = 0; d < length; d++) {
      key[d] = hexByte[r.nextInt(16)];
    }
    return new String(key);
  }
  
  public static void main(String[] args)
  {
    System.out.println(encrypt("s"));
    System.out.println(decrypt("53A63B32A5908E62"));
  }
}
