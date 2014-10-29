package com.bean.lov;

import java.util.Comparator;

public class KeyValueComparator
  implements Comparator
{
  public int compare(Object kv1, Object kv2)
  {
    String v1 = ((KeyValue)kv1).getValue().toUpperCase();
    if (v1 == null) {
      v1 = "";
    }
    String v2 = ((KeyValue)kv2).getValue().toUpperCase();
    
    return v1.compareTo(v2);
  }
}
