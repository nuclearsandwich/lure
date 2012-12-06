package lure.lang;

import java.util.ArrayList;

public class Str {
  public static Object cat(ArrayList args) {
    StringBuilder s = new StringBuilder();
    for (Object str : args) {
      s.append((String)str);
    }
    return s.toString();
  }

  public static Object cat(Object arg1, Object arg2) {
    StringBuilder s = new StringBuilder();
    s.append((String)arg1);
    s.append((String)arg2);
    return s.toString();
  }


  public static Object cat(Object arg1, Object arg2, Object arg3) {
    StringBuilder s = new StringBuilder();
    s.append((String)arg1);
    s.append((String)arg2);
    s.append((String)arg3);
    return s.toString();
  }

  public static Object cat(Object arg1, Object arg2, Object arg3, Object arg4) {
    StringBuilder s = new StringBuilder();
    s.append((String)arg1);
    s.append((String)arg2);
    s.append((String)arg3);
    s.append((String)arg4);
    return s.toString();
  }

  public static Object cat(Object arg1, Object arg2, Object arg3, Object arg4,
      Object arg5)
  {
    StringBuilder s = new StringBuilder();
    s.append((String)arg1);
    s.append((String)arg2);
    s.append((String)arg3);
    s.append((String)arg4);
    s.append((String)arg5);
    return s.toString();
  }

  public static Object conv(Object arg1) {
    return arg1.toString();
  }
}
