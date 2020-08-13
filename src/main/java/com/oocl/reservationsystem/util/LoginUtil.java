package com.oocl.reservationsystem.util;

import java.security.MessageDigest;

public class LoginUtil {
  public static String stringMD5(String inStr) {
    MessageDigest md5 = null;
    try {
      md5 = MessageDigest.getInstance("MD5");
    } catch (Exception e) {
      System.out.println(e.toString());
      e.printStackTrace();
      return "";
    }
    char[] charArray = inStr.toCharArray();
    byte[] byteArray = new byte[charArray.length];

    for (int i = 0; i < charArray.length; i++) {
      byteArray[i] = (byte) charArray[i];
    }
    byte[] md5Bytes = md5.digest(byteArray);
    StringBuilder hexValue = new StringBuilder();
    for (byte md5Byte : md5Bytes) {
      int val = ((int) md5Byte) & 0xff;
      if (val < 16) {
        hexValue.append("0");
      }
      hexValue.append(Integer.toHexString(val));
    }
    return hexValue.toString().toUpperCase();
  }

}
