package lure.lang.globals;

import lure.lang.List;

public class Gt extends lure.lang.Function {
  public Object call(Object arg1, Object arg2) {
    return ((Integer)arg1) >= ((Integer)arg2);
  }
}
