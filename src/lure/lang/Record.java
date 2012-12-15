package lure.lang;

import java.util.HashMap;

public class Record implements LureObject {
  private HashMap<String, Object> map;
  public Record() {
    map = new HashMap<String, Object>();
  }

  public Object _fieldGet(Object key) {
    return map.get((String)key);
  }

  public Object _fieldSet(Object key, Object val) {
    map.put((String)key, val);
    return val;
  }
}

