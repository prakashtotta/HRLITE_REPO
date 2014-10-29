package com.util;

import java.util.Comparator;

public class LongComparator
  implements Comparator
{
  public int compare(Object o1, Object o2)
  {
    if (((o1 instanceof String)) && ((o2 instanceof String)))
    {
      String s1 = (String)o1;
      String s2 = (String)o2;
      Long i1 = new Long(s1);
      Long i2 = new Long(s2);
      return i1.compareTo(i2);
    }
    return 0;
  }
}
