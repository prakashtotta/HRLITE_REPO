package com.util;

import com.bean.Permissions;
import com.bean.Role;
import com.bean.Timezone;
import com.bean.User;
import com.resources.Constant;

import java.io.PrintStream;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;

public class StringUtils
{
  private static SimpleDateFormat dateInLongFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  
  public static int stringToInt(String str, int defaultValue)
  {
    if (str != null) {
      return Integer.parseInt(str);
    }
    return defaultValue;
  }
  
  private static boolean isDigits(String s)
  {
    boolean b = true;
    char[] chs = s.toCharArray();
    for (int i = 0; i < chs.length; i++)
    {
      b = Character.isDigit(chs[i]);
      if (!b) {
        return b;
      }
    }
    return b;
  }
  
  public static String formatDigits(String s, boolean required)
    throws ParseException
  {
    boolean b = true;
    s = s.trim();
    if ((required) && (s.length() == 0)) {
      throw new ParseException("Field value require", 0);
    }
    char[] chs = s.toCharArray();
    for (int i = 0; i < chs.length; i++)
    {
      b = Character.isDigit(chs[i]);
      if (!b) {
        throw new ParseException("Digits value only!", 0);
      }
    }
    return s;
  }
  
  public static String[] splitStringAtCharacter(String s, char c)
  {
    String[] as = new String[numOccurrences(s, c) + 1];
    splitStringAtCharacter(s, c, as, 0);
    ArrayList _al = new ArrayList();
    for (int i = 0; i < as.length; i++)
    {
      String _s = as[i];
      if (_s != null) {
        _s = _s.trim();
      }
      if (_s.length() == 0) {
        _s = null;
      }
      if (_s != null) {
        _al.add(_s);
      }
    }
    int _size = _al.size();
    as = new String[_size];
    for (int j = 0; j < _size; j++) {
      as[j] = ((String)_al.get(j));
    }
    return as;
  }
  
  public static int numOccurrences(String s, char c)
  {
    int i = 0;
    int j = 0;
    int l;
    for (int k = s.length(); j < k; j = l + 1)
    {
      l = s.indexOf(c, j);
      if (l < 0) {
        break;
      }
      i++;
    }
    return i;
  }
  
  public static int splitStringAtCharacter(String s, char c, String[] as, int i)
  {
    int j = 0;
    int k = i;
    int l = 0;
    int j1;
    for (int i1 = s.length(); (l <= i1) && (k < as.length); l = j1 + 1)
    {
      j1 = s.indexOf(c, l);
      if (j1 < 0) {
        j1 = i1;
      }
      as[k] = s.substring(l, j1);
      j++;
      k++;
    }
    return j;
  }
  
  public static String centralAlignment(String s, int totalLength)
  {
    StringBuffer output = new StringBuffer();
    if (s == null)
    {
      output.append("");
    }
    else
    {
      int len = s.length();
      if (len >= totalLength)
      {
        output.append(s);
      }
      else
      {
        int left = (totalLength - len) / 2;
        int right = totalLength - left - len;
        while (left-- > 0) {
          output.append(' ');
        }
        output.append(s);
        while (right-- > 0) {
          output.append(' ');
        }
      }
    }
    return output.toString();
  }
  
  public static String leftAlignment(String s, int totalLength)
  {
    StringBuffer output = new StringBuffer();
    if (s == null)
    {
      output.append("");
    }
    else
    {
      int len = s.length();
      if (len >= totalLength)
      {
        output.append(s);
      }
      else
      {
        output.append(s);
        int right = totalLength - len;
        while (right-- > 0) {
          output.append(' ');
        }
      }
    }
    return output.toString();
  }
  
  public static String concatStringArray(String[] as, char c)
  {
    if (as == null) {
      return null;
    }
    StringBuffer _sb = new StringBuffer();
    for (int i = 0; i < as.length; i++)
    {
      _sb.append(as[i]);
      if (i != as.length - 1) {
        _sb.append(c);
      }
    }
    return _sb.toString();
  }
  
  public static String isNullOrEmptyString(String aStringToTest, String aDefault)
  {
    return (aStringToTest == null) || (aStringToTest.equals("")) ? aDefault : aStringToTest;
  }
  
  public static String replaceWildChar(String pStr)
  {
    System.out.println("In replacewildChar....." + pStr);
    pStr = isNullOrEmptyString(pStr, "%");
    if ((pStr != null) && (pStr.length() == 0)) {
      pStr = "%";
    }
    if (pStr.equalsIgnoreCase("%")) {
      return pStr;
    }
    if (pStr.endsWith("*"))
    {
      pStr = pStr.replace('*', '%');
      return pStr;
    }
    pStr = "%" + pStr + "%";
    if (pStr.indexOf("'") > 0) {
      pStr = pStr.substring(0, pStr.indexOf("'")) + "'" + pStr.substring(pStr.indexOf("'"), pStr.length());
    }
    return pStr;
  }
  
  public static Long stringToNumber(String number)
  {
    Long retNum = null;
    try
    {
      if ((!isNullOrEmpty(number)) && 
        (isDigits(number.trim()))) {
        retNum = Long.valueOf(number);
      }
    }
    catch (NumberFormatException exp) {}
    return retNum;
  }
  
  public static String[] addPrefix(String s, String[] as)
  {
    synchronized (as)
    {
      String[] as3 = new String[as.length];
      for (int i = 0; i < as3.length; i++) {
        as3[i] = (s + as[i]);
      }
      String[] as2 = as3;
      return as2;
    }
  }
  
  public static String[] addSuffix(String s, String[] as)
  {
    synchronized (as)
    {
      String[] as3 = new String[as.length];
      for (int i = 0; i < as3.length; i++) {
        as3[i] = (as[i] + s);
      }
      String[] as2 = as3;
      return as2;
    }
  }
  
  public static String stripChar(char c, String s)
  {
    char[] ac = s.toCharArray();
    int i = 0;
    for (int j = 0; j < ac.length; j++) {
      if (ac[j] == c) {
        i++;
      }
    }
    char[] ac1 = new char[ac.length - i];
    int k = 0;
    for (int l = 0; l < ac.length; l++) {
      if (ac[l] != c) {
        ac1[(k++)] = ac[l];
      }
    }
    return new String(ac1);
  }
  
  public static int numBeforeLocation(char c, String s, int i)
  {
    char[] ac = s.toCharArray();
    int j = 0;
    for (int k = 0; k < Math.min(i, ac.length); k++) {
      if (ac[k] == c) {
        j++;
      }
    }
    return j;
  }
  
  public static int[] positions(char c, String s)
  {
    char[] ac = s.toCharArray();
    int i = 0;
    for (int j = 0; j < ac.length; j++) {
      if (ac[j] == c) {
        i++;
      }
    }
    int[] ai = new int[i];
    i = 0;
    for (int k = 0; k < ac.length; k++) {
      if (ac[k] == c) {
        ai[(i++)] = k;
      }
    }
    return ai;
  }
  
  public static String removeWhites(String s)
  {
    StringBuffer stringbuffer = new StringBuffer();
    char[] ac = s.toCharArray();
    for (int i = 0; i < ac.length; i++) {
      if (ac[i] > ' ') {
        stringbuffer.append(ac[i]);
      }
    }
    return new String(stringbuffer);
  }
  
  public static String replaceWhiteSpacesWithDots(String s)
  {
    String str = "";
    int ctr = 0;
    StringTokenizer st = new StringTokenizer(s);
    while (st.hasMoreTokens())
    {
      if (ctr == 0) {
        str = str + st.nextToken();
      } else {
        str = str + "." + st.nextToken();
      }
      ctr++;
    }
    return str;
  }
  
  public static String replaceMultipleWhiteSpacesWithOneSpace(String s)
  {
    String str = "";
    int ctr = 0;
    StringTokenizer st = new StringTokenizer(s);
    while (st.hasMoreTokens())
    {
      if (ctr == 0) {
        str = str + st.nextToken();
      } else {
        str = str + " " + st.nextToken();
      }
      ctr++;
    }
    return str;
  }
  
  public static String join(String[] as, String s)
  {
    int i = as.length;
    StringBuffer stringbuffer = new StringBuffer(i * 20);
    synchronized (as)
    {
      for (int j = 0; j < i - 1; j++)
      {
        stringbuffer.append(as[j]);
        stringbuffer.append(s);
      }
      if (i > 0) {
        stringbuffer.append(as[(i - 1)]);
      }
    }
    return stringbuffer.toString();
  }
  
  public static String join(String[] as)
  {
    return join(as, " ");
  }
  
  public static String join(Vector vector, String s)
  {
    int i = vector.size();
    StringBuffer stringbuffer = new StringBuffer(i * 20);
    synchronized (vector)
    {
      for (int j = 0; j < i - 1; j++)
      {
        stringbuffer.append((String)vector.elementAt(j));
        stringbuffer.append(s);
      }
      if (i > 0) {
        stringbuffer.append((String)vector.elementAt(i - 1));
      }
    }
    return stringbuffer.toString();
  }
  
  public static String join(Vector vector)
  {
    return join(vector, " ");
  }
  
  public static String replace(String s, String s1, String s2)
  {
    if (s1.equals("")) {
      return s;
    }
    StringBuffer stringbuffer = new StringBuffer(2 * s.length());
    int i = 0;
    boolean flag = false;
    int k = s1.length();
    for (;;)
    {
      int j = s.indexOf(s1, i);
      if (j == -1)
      {
        stringbuffer.append(s.substring(i));
        break;
      }
      stringbuffer.append(s.substring(i, j) + s2);
      i = j + k;
    }
    return stringbuffer.toString();
  }
  
  public static String delete(String s, String s1)
  {
    return replace(s, s1, "");
  }
  
  public static String removePositions(String s, int[] ai)
  {
    char[] ac = new char[s.length() - ai.length];
    char[] ac1 = s.toCharArray();
    int i = 0;
    for (int j = 0; j < ac1.length; j++) {
      if ((i < ai.length) && (j == ai[i])) {
        i++;
      } else {
        ac[(j - i)] = ac1[j];
      }
    }
    return new String(ac);
  }
  
  public static int nthIndexOf(String s, String s1, int i)
  {
    return nthIndexOf(s, s1, i, 0);
  }
  
  public static int nthIndexOf(String s, String s1, int i, int j)
  {
    int k = 0;
    int l = 0;
    int i1 = s1.length();
    for (k = s.indexOf(s1, k + j); (l < i) && (k != -1); k = s.indexOf(s1, k + i1)) {
      l++;
    }
    return k;
  }
  
  public static String[] toStringArray(Vector vector)
  {
    String[] as = new String[vector.size()];
    vector.copyInto(as);
    return as;
  }
  
  public static String capitalize(String s)
  {
    if ((s == null) || (s.equals(""))) {
      return s;
    }
    return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
  }
  
  public static String[] customizeMsg(String s, int i)
  {
    return customizeMsg(s, i, -1);
  }
  
  public static boolean isEmpty(String str)
  {
    if (str == null) {
      return true;
    }
    return false;
  }
  
  public static String[] customizeMsg(String s, int i, int j)
  {
    String[] as = tokenize(s, "\n");
    if (as.length > i)
    {
      String[] as1 = new String[i];
      System.arraycopy(as, 0, as1, 0, as1.length);
      as = as1;
    }
    if (j > 0) {
      for (int k = 0; k < as.length; k++) {
        if (as[k].length() > j) {
          as[k] = (as[k].substring(0, j) + "...");
        }
      }
    }
    return as;
  }
  
  public static String[] tokenize(String a, String b)
  {
    String[] ar = null;
    int i = 0;
    if ((a == null) || (b == null)) {
      return null;
    }
    StringTokenizer st = new StringTokenizer(a, b);
    if ((st != null) && (st.countTokens() > 0)) {
      ar = new String[st.countTokens()];
    }
    while ((st != null) && (st.hasMoreTokens()))
    {
      ar[i] = st.nextToken();
      i++;
    }
    return ar;
  }
  
  public static List tokenizeString(String a, String b)
  {
    List arr = new ArrayList();
    int i = 0;
    if ((a == null) || (b == null)) {
      return null;
    }
    StringTokenizer st = new StringTokenizer(a, b);
    while ((st != null) && (st.hasMoreTokens()))
    {
      arr.add(st.nextToken());
      i++;
    }
    return arr;
  }
  
  public static String replaceString(String src, String fromStr, String withStr)
  {
    if (src == null) {
      return "";
    }
    if (withStr == null) {
      withStr = "";
    }
    int startIndex = 0;
    int endIndex = 0;
    while (src.indexOf(fromStr) != -1)
    {
      startIndex = endIndex;
      endIndex = src.indexOf(fromStr);
      src = src.substring(startIndex, endIndex) + withStr + src.substring(endIndex + fromStr.length(), src.length());
    }
    return src;
  }
  
  public static byte[] fromHexString(String s)
  {
    int stringLength = s.length();
    if ((stringLength & 0x1) != 0) {
      throw new IllegalArgumentException("fromHexString requires an even number of hex characters");
    }
    byte[] b = new byte[stringLength / 2];
    int i = 0;
    for (int j = 0; i < stringLength; j++)
    {
      int high = charToNibble(s.charAt(i));
      int low = charToNibble(s.charAt(i + 1));
      b[j] = ((byte)(high << 4 | low));i += 2;
    }
    return b;
  }
  
  public static String toHexString(byte[] b)
  {
    StringBuffer sb = new StringBuffer(b.length * 2);
    for (int i = 0; i < b.length; i++)
    {
      sb.append(hexChar[((b[i] & 0xF0) >>> 4)]);
      
      sb.append(hexChar[(b[i] & 0xF)]);
    }
    return sb.toString();
  }
  
  private static int charToNibble(char c)
  {
    if (('0' <= c) && (c <= '9')) {
      return c - '0';
    }
    if (('a' <= c) && (c <= 'f')) {
      return c - 'a' + 10;
    }
    if (('A' <= c) && (c <= 'F')) {
      return c - 'A' + 10;
    }
    throw new IllegalArgumentException("Invalid hex character: " + c);
  }
  
  static char[] hexChar = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
  
  public static String[] toStringArray(ArrayList list)
  {
    String[] array = null;
    if ((list != null) && (list.size() > 0))
    {
      array = new String[list.size()];
      for (int i = 0; i < list.size(); i++)
      {
        Object a = list.get(i);
        if ((a != null) && ((a instanceof String))) {
          array[i] = ((String)a);
        }
      }
    }
    return array;
  }
  
  public static Integer[] toIntegerArray(ArrayList list)
  {
    Integer[] array = null;
    if ((list != null) && (list.size() > 0))
    {
      array = new Integer[list.size()];
      for (int i = 0; i < list.size(); i++)
      {
        Object a = list.get(i);
        if ((a != null) && ((a instanceof Integer))) {
          array[i] = ((Integer)a);
        }
      }
    }
    return array;
  }
  
  public static String checkNull(String a)
  {
    if (a == null) {
      return "";
    }
    return a;
  }
  
  public static String getDateInLongFormat(java.sql.Date date)
  {
    if (date == null) {
      return "";
    }
    return dateInLongFormat.format(date);
  }
  
  public static String getEscapedString(String source)
  {
    StringBuffer result = new StringBuffer();
    for (int i = 0; i < source.length(); i++)
    {
      char c = source.charAt(i);
      if (c < '\\') {
        result.append(c);
      } else {
        result.append("&#" + Integer.toString(c) + ";");
      }
    }
    return result.toString();
  }
  
  public static String makeISOString(String source)
  {
    String isoStr = null;
    try
    {
      if (source != null) {
        isoStr = new String(source.getBytes("ISO8859_1"), "UTF-8");
      } else {
        isoStr = "";
      }
    }
    catch (Exception e) {}
    return isoStr;
  }
  
  public static String makeISO2UTFString(String source)
  {
    String utfStr = null;
    try
    {
      if (source != null) {
        utfStr = new String(source.getBytes("UTF-8"), "ISO-8859-1");
      } else {
        utfStr = "";
      }
    }
    catch (Exception e) {}
    return utfStr;
  }
  
  public static ArrayList createListFromCommaSeparatedValues(String commaSeparatedValues)
  {
    ArrayList values = new ArrayList();
    if ((commaSeparatedValues != null) && (!commaSeparatedValues.equals("")))
    {
      StringTokenizer strTok = new StringTokenizer(commaSeparatedValues, ",");
      while (strTok.hasMoreTokens())
      {
        String tok = strTok.nextToken();
        values.add(tok);
      }
    }
    else
    {
      values = null;
    }
    return values;
  }
  
  public static String[] objectArrayToStringArray(Object[] objArray)
  {
    if ((objArray == null) || (objArray.length == 0)) {
      return null;
    }
    String[] strArray = new String[objArray.length];
    for (int i = 0; i < objArray.length; i++) {
      strArray[i] = objArray[i].toString();
    }
    return strArray;
  }
  
  public static ArrayList eliminateDuplicates(ArrayList list)
  {
    HashSet noDuplicatesList = new HashSet();
    if (list.size() > 0)
    {
      for (int a = 0; a < list.size(); a++) {
        noDuplicatesList.add(list.get(a));
      }
      list.clear();
      Iterator noDuplicatesListItr = noDuplicatesList.iterator();
      while (noDuplicatesListItr.hasNext()) {
        list.add(noDuplicatesListItr.next());
      }
    }
    return list;
  }
  
  public static String returnYesOrNo(String checkStr)
  {
    String retVal = "";
    if (isNullOrEmpty(checkStr)) {
      retVal = "No";
    } else if ((checkStr.trim().equalsIgnoreCase("ON")) || (checkStr.trim().equalsIgnoreCase("Yes")) || (checkStr.trim().equalsIgnoreCase("CHECKED")) || (checkStr.trim().equalsIgnoreCase("SELECTED")) || (checkStr.trim().equalsIgnoreCase("Y"))) {
      retVal = "Yes";
    } else {
      retVal = "No";
    }
    return retVal;
  }
  
  public static String returnYOrN(String checkStr)
  {
    String retVal = "";
    if (isNullOrEmpty(checkStr)) {
      retVal = "N";
    } else if ((checkStr.trim().equalsIgnoreCase("ON")) || (checkStr.trim().equalsIgnoreCase("Yes")) || (checkStr.trim().equalsIgnoreCase("CHECKED")) || (checkStr.trim().equalsIgnoreCase("SELECTED")) || (checkStr.trim().equalsIgnoreCase("Y"))) {
      retVal = "Y";
    } else {
      retVal = "N";
    }
    return retVal;
  }
  
  public static String replaceTM(String org)
  {
    System.out.println(" TM exists in this Str " + isSPCExists(org));
    return replace(org, "\\u0099", "&#8482");
  }
  
  public static boolean isSPCExists(String org)
  {
    return org.indexOf(new String("\\u0099")) > -1;
  }
  
  public static String escapeUnicodeString(String str, boolean escapeAscii)
  {
    String ostr = new String();
    for (int i = 0; i < str.length(); i++)
    {
      char ch = str.charAt(i);
      if ((!escapeAscii) && (ch >= ' ') && (ch <= '~'))
      {
        ostr = ostr + ch;
      }
      else
      {
        ostr = ostr + "\\u";
        String hex = Integer.toHexString(str.charAt(i) & 0xFFFF);
        if (hex.length() == 2) {
          ostr = ostr + "00";
        }
        ostr = ostr + hex.toUpperCase(Locale.ENGLISH);
      }
    }
    return ostr;
  }
  
  public static int[] toIntArray(String[] list)
  {
    int[] array = null;
    if ((list != null) && (list.length > 0))
    {
      array = new int[list.length];
      for (int i = 0; i < list.length; i++) {
        try
        {
          array[i] = Integer.parseInt(list[i]);
        }
        catch (NumberFormatException ne)
        {
          array[i] = 0;
        }
      }
    }
    return array;
  }
  
  public static String toStrWithSeparator(int[] list, String separator)
  {
    String retVal = null;
    if ((list != null) && (list.length > 0))
    {
      StringBuffer buf = new StringBuffer("");
      for (int i = 0; i < list.length; i++)
      {
        buf.append(list[i]);
        if (i < list.length - 1) {
          buf.append(separator);
        }
      }
      retVal = buf.toString();
    }
    return retVal;
  }
  
  public static String toStrWithSeparator(String[] list, String separator)
  {
    String retVal = null;
    if ((list != null) && (list.length > 0))
    {
      StringBuffer buf = new StringBuffer("");
      for (int i = 0; i < list.length; i++)
      {
        buf.append(list[i]);
        if (i < list.length - 1) {
          buf.append(separator);
        }
      }
      retVal = buf.toString();
    }
    return retVal;
  }
  
  public static String toStrWithSeparator(String[] list, String separator, String start, String end)
  {
    String retVal = null;
    if ((start != null) && (end != null))
    {
      if ((list != null) && (list.length > 0))
      {
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < list.length; i++)
        {
          buf.append(start).append(list[i]).append(end);
          if (i < list.length - 1) {
            buf.append(separator);
          }
        }
        retVal = buf.toString();
      }
    }
    else {
      retVal = toStrWithSeparator(list, separator);
    }
    return retVal;
  }
  
  public static int getIntFromString(String str)
  {
    if ((null != str) && (!"".equals(str))) {
      try
      {
        return Integer.parseInt(str);
      }
      catch (NumberFormatException e)
      {
        e.printStackTrace();
        System.out.println("Invalid String input " + str);
        throw e;
      }
    }
    return 0;
  }
  
  public static boolean isNullOrEmpty(String s)
  {
    if ((s == null) || (s.trim().length() <= 0)) {
      return true;
    }
    return false;
  }
  
  public static boolean compare(String s, String t)
  {
    if (s.trim().equalsIgnoreCase(t)) {
      return true;
    }
    return false;
  }
  
  public static String listToCommaseparated(List values)
  {
    String cv = "";
    if ((values == null) || (values.size() == 0)) {
      return "";
    }
    for (int i = 0; i < values.size(); i++)
    {
      String tmp = (String)values.get(i);
      cv = cv + tmp + ",";
    }
    cv = cv.substring(0, cv.length() - 1);
    return cv;
  }
  
  public static String createYahooDataTableForRoles(List roleList)
  {
    StringBuffer datatable = new StringBuffer();
    datatable.append("[");
    for (int i = 0; i < roleList.size(); i++)
    {
      Role role = (Role)roleList.get(i);
      
      Set permissions = role.getPermissions();
      Iterator itr = permissions.iterator();
      String per = "";
      while (itr.hasNext())
      {
        Permissions permission = (Permissions)itr.next();
        per = per + permission.getPerName() + "*";
      }
      if (per.length() < 1) {
        per = "Permissions Not Assigned";
      }
      String temp = "{id:" + role.getRoleId() + "," + "roleid:" + "\"" + "<a href=# onClick=dele(" + role.getRoleId() + ")>" + "delete" + "</a>" + "\"" + "," + "rolecode:" + "\"" + role.getRoleCode() + "\"" + "," + "rolename:" + "\"" + role.getRoleName() + "\"" + "," + "roledes:" + "\"" + role.getRoleDesc() + "\"" + "," + "per:" + "\"" + per + "\"" + "}";
      
      datatable.append(temp);
      if (i < roleList.size() - 1) {
        datatable.append(",");
      }
    }
    datatable.append("]");
    System.out.println(datatable.toString());
    
    return datatable.toString();
  }
  
  public static String createYahooDataTableForRolesText(List roleList)
  {
    StringBuffer datatable = new StringBuffer();
    for (int i = 0; i < roleList.size(); i++)
    {
      Role role = (Role)roleList.get(i);
      
      String temp = role.getRoleCode() + "|" + role.getRoleName() + "|" + role.getRoleDesc() + "|" + role.getRoleId() + "|" + "http://lov.do?method=editrole" + "\n";
      datatable.append(temp);
    }
    System.out.println(datatable.toString());
    
    return datatable.toString();
  }
  
  public static String createYahooDataTable(List list, String[] header, String[] fields)
  {
    StringBuffer datatable = new StringBuffer();
    if ((header != null) && (header.length > 0))
    {
      datatable.append("<thead>");
      datatable.append("\n");
      datatable.append("<tr>");
      datatable.append("\n");
      for (int i = 0; i < header.length; i++)
      {
        datatable.append("<th>" + header[i] + "</th>");
        datatable.append("\n");
      }
      datatable.append("</tr>");
      datatable.append("</thead>");
      datatable.append("\n");
    }
    datatable.append("<tbody>");
    datatable.append("\n");
    for (int i = 0; i < list.size(); i++)
    {
      Object obj = list.get(i);
      
      Class targetClass = obj.getClass();
      

      Field[] f = targetClass.getDeclaredFields();
      
      datatable.append("<tr>");
      datatable.append("\n");
      try
      {
        for (int j = 0; j < fields.length; j++) {
          for (int k = 0; k < f.length; k++) {
            if (fields[j].equalsIgnoreCase(f[k].getName()))
            {
              String value = (String)f[k].get(obj);
              System.out.println(value);
              
              datatable.append("<td>" + value + "</td>");
              datatable.append("\n");
            }
          }
        }
      }
      catch (IllegalAccessException e)
      {
        e.printStackTrace();
      }
      datatable.append("</tr>");
      datatable.append("\n");
    }
    datatable.append("</tbody>");
    datatable.append("\n");
    
    return datatable.toString();
  }
  
  public static String createYahooDataTable(List list, String[] header, String[] fields, String linkField, String url, String linkData)
  {
    StringBuffer datatable = new StringBuffer();
    if ((header != null) && (header.length > 0))
    {
      datatable.append("<thead>");
      datatable.append("\n");
      datatable.append("<tr>");
      datatable.append("\n");
      for (int i = 0; i < header.length; i++)
      {
        datatable.append("<th>" + header[i] + "</th>");
        datatable.append("\n");
      }
      datatable.append("</tr>");
      datatable.append("</thead>");
      datatable.append("\n");
    }
    datatable.append("<tbody>");
    datatable.append("\n");
    for (int i = 0; i < list.size(); i++)
    {
      Object obj = list.get(i);
      
      Class targetClass = obj.getClass();
      

      Field[] f = targetClass.getDeclaredFields();
      
      datatable.append("<tr>");
      datatable.append("\n");
      try
      {
        for (int j = 0; j < fields.length; j++) {
          for (int k = 0; k < f.length; k++)
          {
            long id = 0L;
            if (linkData.equalsIgnoreCase(f[k].getName())) {
              id = f[k].getLong(obj);
            }
            if (fields[j].equalsIgnoreCase(f[k].getName()))
            {
              String value = (String)f[k].get(obj);
              System.out.println(value);
              if (f[k].getName().equalsIgnoreCase(linkField)) {
                value = url + id + "'>" + value + "</a>";
              }
              datatable.append("<td>" + value + "</td>");
              datatable.append("\n");
            }
          }
        }
      }
      catch (IllegalAccessException e)
      {
        e.printStackTrace();
      }
      datatable.append("</tr>");
      datatable.append("\n");
    }
    datatable.append("</tbody>");
    datatable.append("\n");
    
    return datatable.toString();
  }
  
  public static String createBasicYahooDataTable(List list, String[] fields)
  {
    StringBuffer datatable = new StringBuffer();
    
    datatable.append("[");
    for (int i = 0; i < list.size(); i++)
    {
      Object obj = list.get(i);
      
      Class targetClass = obj.getClass();
      

      Field[] f = targetClass.getDeclaredFields();
      
      datatable.append("{");
      try
      {
        for (int j = 0; j < fields.length; j++)
        {
          for (int k = 0; k < f.length; k++) {
            if (fields[j].equalsIgnoreCase(f[k].getName()))
            {
              String value = (String)f[k].get(obj);
              System.out.println(value);
              String temp = fields[j] + ":" + "\"" + value + "\"";
              datatable.append(temp);
            }
          }
          if (j < fields.length - 1) {
            datatable.append(",");
          }
        }
      }
      catch (IllegalAccessException e)
      {
        e.printStackTrace();
      }
      datatable.append("}");
      if (i < list.size() - 1) {
        datatable.append(",");
      }
    }
    datatable.append("]");
    System.out.println(datatable.toString());
    return datatable.toString();
  }
  
  public static String createJasonDataTable(List list, String[] fields)
  {
    StringBuffer datatable = new StringBuffer();
    try
    {
      datatable.append("[");
      datatable.append("\n");
      System.out.println("data size" + list.size());
      for (int i = 0; i < list.size(); i++)
      {
        Object obj = list.get(i);
        
        Class targetClass = obj.getClass();
        

        Field[] f = targetClass.getDeclaredFields();
        
        datatable.append("{");
        datatable.append("\n");
        try
        {
          for (int j = 0; j < fields.length; j++)
          {
            for (int k = 0; k < f.length; k++) {
              if (fields[j].equalsIgnoreCase(f[k].getName()))
              {
                System.out.println(f[k].getType());
                

                String temp = "\"" + fields[j] + "\"" + ":" + "\"" + f[k].get(obj) + "\"";
                System.out.println("temp" + temp);
                datatable.append(temp);
                datatable.append("\n");
              }
            }
            if (j < fields.length - 1) {
              datatable.append(",");
            }
          }
        }
        catch (IllegalAccessException e)
        {
          e.printStackTrace();
        }
        datatable.append("}");
        if (i < list.size() - 1) {
          datatable.append(",");
        }
        datatable.append("\n");
      }
      datatable.append("\n");
      datatable.append("]");
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    System.out.println("datatable.toString()" + datatable.toString());
    return datatable.toString();
  }
  
  public static String createJasonDataTable(List list, String[] fields, User user1)
  {
    String datepattern = Constant.getValue("defaultdateformat");
    if (user1 != null) {
      datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
    }
    StringBuffer datatable = new StringBuffer();
    try
    {
      datatable.append("[");
      datatable.append("\n");
      System.out.println("data size" + list.size());
      for (int i = 0; i < list.size(); i++)
      {
        Object obj = list.get(i);
        
        Class targetClass = obj.getClass();
        

        Field[] f = targetClass.getDeclaredFields();
        
        datatable.append("{");
        datatable.append("\n");
        try
        {
          for (int j = 0; j < fields.length; j++)
          {
            for (int k = 0; k < f.length; k++) {
              if (fields[j].equalsIgnoreCase(f[k].getName()))
              {
                String temp = "";
                if ((f[k].getType().getName().equals("java.util.Date")) && (f[k].get(obj) != null))
                {
                  String condate = "";
                  if (Constant.dateTypeList.contains(fields[j])) {
                    condate = DateUtil.convertDateToStringDate((java.util.Date)f[k].get(obj), datepattern);
                  } else {
                    condate = DateUtil.convertSourceToTargetTimezoneWithoutTime((java.util.Date)f[k].get(obj), user1.getTimezone().getTimezoneCode(), user1.getLocale());
                  }
                  temp = "\"" + fields[j] + "\"" + ":" + "\"" + condate + "\"";
                }
                else
                {
                  Object tempobj = f[k].get(obj);
                  if ((f[k].getType().getName().equals("java.lang.String")) && (tempobj != null))
                  {
                    String tempstring = (String)f[k].get(obj);
                    tempstring = tempstring.replace("\"", "&#34;");
                    tempstring = tempstring.replace("\r\n", "<br>").replace("\n", "<br>");
                    temp = "\"" + fields[j] + "\"" + ":" + "\"" + tempstring + "\"";
                  }
                  else
                  {
                    Object nval = f[k].get(obj) == null ? "" : f[k].get(obj);
                    temp = "\"" + fields[j] + "\"" + ":" + "\"" + nval + "\"";
                  }
                }
                datatable.append(temp);
                datatable.append("\n");
              }
            }
            if (j < fields.length - 1) {
              datatable.append(",");
            }
          }
        }
        catch (IllegalAccessException e)
        {
          e.printStackTrace();
        }
        datatable.append("}");
        if (i < list.size() - 1) {
          datatable.append(",");
        }
        datatable.append("\n");
      }
      datatable.append("\n");
      datatable.append("]");
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return datatable.toString();
  }
  
  public static String createJasonDataTableWithoutTimeZoneConvert(List list, String[] fields, User user1)
  {
    String datepattern = Constant.getValue("defaultdateformat");
    if (user1 != null) {
      datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
    }
    StringBuffer datatable = new StringBuffer();
    try
    {
      datatable.append("[");
      datatable.append("\n");
      System.out.println("data size" + list.size());
      for (int i = 0; i < list.size(); i++)
      {
        Object obj = list.get(i);
        
        Class targetClass = obj.getClass();
        

        Field[] f = targetClass.getDeclaredFields();
        
        datatable.append("{");
        datatable.append("\n");
        try
        {
          for (int j = 0; j < fields.length; j++)
          {
            for (int k = 0; k < f.length; k++) {
              if (fields[j].equalsIgnoreCase(f[k].getName()))
              {
                String temp = "";
                if ((f[k].getType().getName().equals("java.util.Date")) && (f[k].get(obj) != null))
                {
                  String condate = "";
                  
                  condate = DateUtil.convertDateToStringDate((java.util.Date)f[k].get(obj), datepattern);
                  
                  temp = "\"" + fields[j] + "\"" + ":" + "\"" + condate + "\"";
                }
                else
                {
                  Object tempobj = f[k].get(obj);
                  if ((f[k].getType().getName().equals("java.lang.String")) && (tempobj != null))
                  {
                    String tempstring = (String)f[k].get(obj);
                    tempstring = tempstring.replace("\"", "&#34;");
                    tempstring = tempstring.replace("\r\n", "<br>").replace("\n", "<br>");
                    temp = "\"" + fields[j] + "\"" + ":" + "\"" + tempstring + "\"";
                  }
                  else
                  {
                    Object nval = f[k].get(obj) == null ? "" : f[k].get(obj);
                    temp = "\"" + fields[j] + "\"" + ":" + "\"" + nval + "\"";
                  }
                }
                datatable.append(temp);
                datatable.append("\n");
              }
            }
            if (j < fields.length - 1) {
              datatable.append(",");
            }
          }
        }
        catch (IllegalAccessException e)
        {
          e.printStackTrace();
        }
        datatable.append("}");
        if (i < list.size() - 1) {
          datatable.append(",");
        }
        datatable.append("\n");
      }
      datatable.append("\n");
      datatable.append("]");
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return datatable.toString();
  }
  
  public static String createJasonDataTableWithTime(List list, String[] fields, User user1)
  {
    StringBuffer datatable = new StringBuffer();
    try
    {
      datatable.append("[");
      datatable.append("\n");
      System.out.println("data size" + list.size());
      for (int i = 0; i < list.size(); i++)
      {
        Object obj = list.get(i);
        
        Class targetClass = obj.getClass();
        

        Field[] f = targetClass.getDeclaredFields();
        
        datatable.append("{");
        datatable.append("\n");
        try
        {
          for (int j = 0; j < fields.length; j++)
          {
            for (int k = 0; k < f.length; k++) {
              if (fields[j].equalsIgnoreCase(f[k].getName()))
              {
                System.out.println(f[k].getType().getName());
                

                String temp = "";
                if ((f[k].getType().getName().equals("java.util.Date")) && (f[k].get(obj) != null))
                {
                  String condate = DateUtil.convertSourceToTargetTimezone((java.util.Date)f[k].get(obj), user1.getTimezone().getTimezoneCode(), user1.getLocale());
                  temp = "\"" + fields[j] + "\"" + ":" + "\"" + condate + "\"";
                }
                else
                {
                  Object tempobj = f[k].get(obj);
                  if ((f[k].getType().getName().equals("java.lang.String")) && (tempobj != null))
                  {
                    String tempstring = (String)f[k].get(obj);
                    tempstring = tempstring.replace("\"", "&#34;");
                    tempstring = tempstring.replace("\r\n", "<br>").replace("\n", "<br>");
                    temp = "\"" + fields[j] + "\"" + ":" + "\"" + tempstring + "\"";
                  }
                  else
                  {
                    Object nval = f[k].get(obj) == null ? "" : f[k].get(obj);
                    temp = "\"" + fields[j] + "\"" + ":" + "\"" + nval + "\"";
                  }
                }
                datatable.append(temp);
                datatable.append("\n");
              }
            }
            if (j < fields.length - 1) {
              datatable.append(",");
            }
          }
        }
        catch (IllegalAccessException e)
        {
          e.printStackTrace();
        }
        datatable.append("}");
        if (i < list.size() - 1) {
          datatable.append(",");
        }
        datatable.append("\n");
      }
      datatable.append("\n");
      datatable.append("]");
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    System.out.println("datatable.toString()" + datatable.toString());
    return datatable.toString();
  }
  
  public static String createJasonDataTableWithDefaultDateFormat(List list, String[] fields)
  {
    String datepattern = Constant.getValue("defaultdateformat");
    StringBuffer datatable = new StringBuffer();
    try
    {
      datatable.append("[");
      datatable.append("\n");
      System.out.println("data size" + list.size());
      for (int i = 0; i < list.size(); i++)
      {
        Object obj = list.get(i);
        
        Class targetClass = obj.getClass();
        

        Field[] f = targetClass.getDeclaredFields();
        
        datatable.append("{");
        datatable.append("\n");
        try
        {
          for (int j = 0; j < fields.length; j++)
          {
            for (int k = 0; k < f.length; k++) {
              if (fields[j].equalsIgnoreCase(f[k].getName()))
              {
                System.out.println(f[k].getType().getName());
                

                String temp = "";
                if ((f[k].getType().getName().equals("java.util.Date")) && (f[k].get(obj) != null))
                {
                  String condate = DateUtil.convertDateToStringDate((java.util.Date)f[k].get(obj), datepattern);
                  temp = "\"" + fields[j] + "\"" + ":" + "\"" + condate + "\"";
                }
                else
                {
                  Object tempobj = f[k].get(obj);
                  if ((f[k].getType().getName().equals("java.lang.String")) && (tempobj != null))
                  {
                    String tempstring = (String)f[k].get(obj);
                    tempstring = tempstring.replace("\"", "&#34;");
                    tempstring = tempstring.replace("\r\n", "<br>").replace("\n", "<br>");
                    temp = "\"" + fields[j] + "\"" + ":" + "\"" + tempstring + "\"";
                  }
                  else
                  {
                    Object nval = f[k].get(obj) == null ? "" : f[k].get(obj);
                    temp = "\"" + fields[j] + "\"" + ":" + "\"" + nval + "\"";
                  }
                }
                datatable.append(temp);
                datatable.append("\n");
              }
            }
            if (j < fields.length - 1) {
              datatable.append(",");
            }
          }
        }
        catch (IllegalAccessException e)
        {
          e.printStackTrace();
        }
        datatable.append("}");
        if (i < list.size() - 1) {
          datatable.append(",");
        }
        datatable.append("\n");
      }
      datatable.append("\n");
      datatable.append("]");
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    System.out.println("datatable.toString()" + datatable.toString());
    return datatable.toString();
  }
  
  public static String createJasonDataTableLink(List list, String[] fields, String linkField, String linkValue)
  {
    StringBuffer datatable = new StringBuffer();
    try
    {
      datatable.append("[");
      datatable.append("\n");
      for (int i = 0; i < list.size(); i++)
      {
        Object obj = list.get(i);
        
        Class targetClass = obj.getClass();
        

        Field[] f = targetClass.getDeclaredFields();
        
        datatable.append("{");
        datatable.append("\n");
        
        Object linkv = null;
        try
        {
          for (int j = 0; j < fields.length; j++)
          {
            for (int t = 0; t < f.length; t++) {
              if (linkValue.equalsIgnoreCase(f[t].getName()))
              {
                linkv = f[t].get(obj);
                System.out.println("linkv" + linkv);
              }
            }
            for (int k = 0; k < f.length; k++) {
              if (fields[j].equalsIgnoreCase(f[k].getName()))
              {
                String urlpart1 = "<a class=\"submodal-600-500\" href='role.do?method=roledetails&roleid=";
                String temp;
                if (fields[j].equalsIgnoreCase(linkField))
                {
                  String urlpart2 = urlpart1 + linkv + "'" + ">" + f[k].get(obj) + "</a>";
                  temp = "\"" + fields[j] + "\"" + ":" + "\"" + urlpart2 + "\"";
                  System.out.println(temp);
                }
                else
                {
                  temp = "\"" + fields[j] + "\"" + ":" + "\"" + f[k].get(obj) + "\"";
                }
                datatable.append(temp);
                datatable.append("\n");
              }
            }
            if (j < fields.length - 1) {
              datatable.append(",");
            }
          }
        }
        catch (IllegalAccessException e)
        {
          e.printStackTrace();
        }
        datatable.append("}");
        if (i < list.size() - 1) {
          datatable.append(",");
        }
        datatable.append("\n");
      }
      datatable.append("\n");
      datatable.append("]");
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    System.out.println("datatable.toString()" + datatable.toString());
    return datatable.toString();
  }
  
  public static String createYahooStringArray(List lst)
  {
    StringBuffer datatable = new StringBuffer();
    datatable.append("[");
    datatable.append("\n");
    for (int i = 0; i < lst.size(); i++)
    {
      String tm = (String)lst.get(i);
      tm = "\"" + tm + "\"";
      datatable.append(tm);
      if (i < lst.size() - 1) {
        datatable.append(",");
      }
      datatable.append("\n");
    }
    datatable.append("]");
    System.out.println(datatable.toString());
    return datatable.toString();
  }
  
  public static String createYahooStringArray(List list, String[] fields)
  {
    StringBuffer datatable = new StringBuffer();
    try
    {
      datatable.append("[");
      datatable.append("\n");
      for (int i = 0; i < list.size(); i++)
      {
        Object obj = list.get(i);
        
        Class targetClass = obj.getClass();
        

        Field[] f = targetClass.getDeclaredFields();
        

        datatable.append("\n");
        try
        {
          for (int j = 0; j < fields.length; j++)
          {
            for (int k = 0; k < f.length; k++) {
              if (fields[j].equalsIgnoreCase(f[k].getName()))
              {
                System.out.println(f[k].getType());
                

                String temp = "\"" + f[k].get(obj) + "\"";
                datatable.append(temp);
                datatable.append("\n");
              }
            }
            if (j < fields.length - 1) {
              datatable.append(",");
            }
          }
        }
        catch (IllegalAccessException e)
        {
          e.printStackTrace();
        }
        if (i < list.size() - 1) {
          datatable.append(",");
        }
        datatable.append("\n");
      }
      datatable.append("\n");
      datatable.append("]");
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    System.out.println("datatable.toString()" + datatable.toString());
    return datatable.toString();
  }
  
  public static String doSpecialCharacters(String body)
  {
    StringBuffer buf = new StringBuffer();
    if (body != null)
    {
      int i = 0;
      for (int limit = body.length(); i < limit; i++)
      {
        char c = body.charAt(i);
        switch (c)
        {
        case '<': 
          buf.append("&lt;"); break;
        case '>': 
          buf.append("&gt;"); break;
        case '\n': 
          buf.append("<br/>"); break;
        case '\t': 
          buf.append("&nbsp;&nbsp;&nbsp;&nbsp;"); break;
        default: 
          buf.append(c);
        }
      }
    }
    return buf.toString();
  }
  
  public static String stringToHTMLString(String string)
  {
    StringBuffer sb = new StringBuffer(string.length());
    
    boolean lastWasBlankChar = false;
    int len = string.length();
    for (int i = 0; i < len; i++)
    {
      char c = string.charAt(i);
      if (c == ' ')
      {
        if (lastWasBlankChar)
        {
          lastWasBlankChar = false;
          sb.append("&nbsp;");
        }
        else
        {
          lastWasBlankChar = true;
          sb.append(' ');
        }
      }
      else
      {
        lastWasBlankChar = false;
        if (c == '"')
        {
          sb.append("&quot;");
        }
        else if (c == '&')
        {
          sb.append("&amp;");
        }
        else if (c == '<')
        {
          sb.append("&lt;");
        }
        else if (c == '>')
        {
          sb.append("&gt;");
        }
        else if (c == '\n')
        {
          sb.append("&lt;br/&gt;");
        }
        else if (c == '\t')
        {
          sb.append("&nbsp;&nbsp;&nbsp;&nbsp;");
        }
        else
        {
          int ci = 0xFFFF & c;
          if (ci < 160)
          {
            sb.append(c);
          }
          else
          {
            sb.append("&#");
            sb.append(new Integer(ci).toString());
            sb.append(';');
          }
        }
      }
    }
    return sb.toString();
  }
  
  public static String textAreaValuesDisplay(String string)
  {
    if (isNullOrEmpty(string)) {
      return "";
    }
    StringBuffer sb = new StringBuffer();
    
    boolean lastWasBlankChar = false;
    int len = string.length();
    for (int i = 0; i < len; i++)
    {
      char c = string.charAt(i);
      if (c == ' ')
      {
        if (lastWasBlankChar)
        {
          lastWasBlankChar = false;
          sb.append("&nbsp;");
        }
        else
        {
          lastWasBlankChar = true;
          sb.append(' ');
        }
      }
      else
      {
        lastWasBlankChar = false;
        if (c == '\n')
        {
          sb.append("<br/>");
        }
        else if (c == '\t')
        {
          sb.append("&nbsp;&nbsp;&nbsp;&nbsp;");
        }
        else
        {
          int ci = 0xFFFF & c;
          if (ci < 160)
          {
            sb.append(c);
          }
          else
          {
            sb.append("&#");
            sb.append(new Integer(ci).toString());
            sb.append(';');
          }
        }
      }
    }
    return sb.toString();
  }
  
  public static String encodeHtmlEntity(String input)
  {
    if (input == null) {
      return "";
    }
    StringBuffer resBuf = new StringBuffer();
    for (int i = 0; i < input.length(); i++)
    {
      char c = input.charAt(i);
      if (((c >= 'a') && (c <= 'z')) || ((c >= 'A') && (c <= 'Z')) || ((c >= '0') && (c <= '9'))) {
        resBuf.append(c);
      } else {
        resBuf.append("&#" + c + ";");
      }
    }
    return resBuf.toString();
  }
  
  public static String parseQuoteForSQL(String input)
  {
    if (input == null) {
      return "";
    }
    input = input.replaceAll("'", "''");
    input = input.replaceAll("\"", "\"");
    return input;
  }
  
  public static void main(String[] args)
  {
    System.out.println(parseQuoteForSQL("don't"));
    
    System.out.println(replace("satya'das", "'", "'"));
  }

public static boolean isNotEmpty(String styleClass) {
	
	return styleClass != null && !"".equals(styleClass.trim())?true:false;
}

public static Object trim(String title) {
	
	return title != null ?title.trim():title;
}
}
