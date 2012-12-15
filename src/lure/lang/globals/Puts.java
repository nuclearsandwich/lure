package lure.lang.globals;

import static lure.lang.globals.Str.nullStringWrap;

public class Puts extends lure.lang.Function {
  public Object call(Object arg1) {
    System.out.println(arg1);
    return null;
  }

  public Object call(Object arg1, Object arg2) {
    System.out.println(nullStringWrap(arg1) + " " + nullStringWrap(arg2));
    return null;
  }

  public Object call(Object arg1, Object arg2, Object arg3) {
    System.out.println(nullStringWrap(arg1) + " " + nullStringWrap(arg2) + " " +
        arg3.toString());
    return null;
  }

  public Object call(Object arg1, Object arg2, Object arg3, Object arg4) {
    System.out.println(nullStringWrap(arg1) + " " + nullStringWrap(arg2) + " " +
        nullStringWrap(arg3) + " " + nullStringWrap(arg4));
    return null;
  }

  public Object call(Object arg1, Object arg2, Object arg3, Object arg4,
      Object arg5)
  {
    System.out.println(nullStringWrap(arg1) + " " + nullStringWrap(arg2) + " " +
        nullStringWrap(arg3) + " " + nullStringWrap(arg4) + " " +
        nullStringWrap(arg5));
    return null;
  }
}
