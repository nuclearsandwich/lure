package lure.lang.globals.str;

import static lure.lang.globals.Str.nullStringWrap;

public class Cat extends lure.lang.Function {
  public Object call(lure.lang.List args) {
    java.util.ArrayList a = args.getArrayList();
    StringBuilder s = new StringBuilder();
    for (Object str : a) {
      s.append((String)str);
    }
    return s.toString();
  }

  public Object call(Object arg1, Object arg2) {
    StringBuilder s = new StringBuilder();
    s.append((String)arg1);
    s.append((String)arg2);
    return s.toString();
  }


  public Object call(Object arg1, Object arg2, Object arg3) {
    StringBuilder s = new StringBuilder();
    s.append((String)arg1);
    s.append((String)arg2);
    s.append((String)arg3);
    return s.toString();
  }

  public Object call(Object arg1, Object arg2, Object arg3, Object arg4) {
    StringBuilder s = new StringBuilder();
    s.append((String)arg1);
    s.append((String)arg2);
    s.append((String)arg3);
    s.append((String)arg4);
    return s.toString();
  }

  public Object call(Object arg1, Object arg2, Object arg3, Object arg4,
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

}

