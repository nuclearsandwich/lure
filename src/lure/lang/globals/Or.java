package lure.lang.globals;

import static lure.lang.Globals.test;

public class Or extends lure.lang.Function {
  public Object call(Object arg1, Object arg2) {
    if (test(arg1) == 0 || test(arg2) == 0) {
      return true;
    } else {
      return false;
    }
  }

  public Object call(Object arg1, Object arg2, Object arg3) {
    if (test(arg1) == 0 || test(arg2) == 0 || test(arg3) == 0) {
      return true;
    } else {
      return false;
    }
  }

  public Object call(Object arg1, Object arg2, Object arg3, Object arg4) {
    if (test(arg1) == 0 || test(arg2) == 0 || test(arg3) == 0 ||
        test(arg4) == 0) {
      return true;
    } else {
      return false;
    }
  }

  public Object call(Object arg1, Object arg2, Object arg3, Object arg4,
      Object arg5)
  {
    if (test(arg1) == 0 || test(arg2) == 0 || test(arg3) == 0 ||
        test(arg4) == 0 || test(arg5) == 0) {
      return true;
    } else {
      return false;
    }
  }
}
