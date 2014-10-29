package com.util;

import java.util.Comparator;

class NumberComparator
  implements Comparator
{
  public int compare(Object o1, Object o2)
  {
    if (((o1 instanceof String)) && ((o2 instanceof String)))
    {
      String s1 = (String)o1;
      String s2 = (String)o2;
      Integer i1 = new Integer(s1);
      Integer i2 = new Integer(s2);
      return i1.compareTo(i2);
    }
    return 0;
  }
}
