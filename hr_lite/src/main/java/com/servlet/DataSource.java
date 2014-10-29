package com.servlet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class DataSource
  implements JRDataSource
{
  private String[] columns;
  private List<Map<String, Object>> values;
  private Iterator<Map<String, Object>> iterator;
  private Map<String, Object> currentRecord;
  
  public DataSource(String... columns)
  {
    this.columns = columns;
    this.values = new ArrayList();
  }
  
  public void add(Object... values)
  {
    Map<String, Object> row = new HashMap();
    for (int i = 0; i < values.length; i++) {
      row.put(this.columns[i], values[i]);
    }
    this.values.add(row);
  }
  
  public Object getFieldValue(JRField field)
    throws JRException
  {
    return this.currentRecord.get(field.getName());
  }
  
  public boolean next()
    throws JRException
  {
    if (this.iterator == null) {
      this.iterator = this.values.iterator();
    }
    boolean hasNext = this.iterator.hasNext();
    if (hasNext) {
      this.currentRecord = ((Map)this.iterator.next());
    }
    return hasNext;
  }
}
