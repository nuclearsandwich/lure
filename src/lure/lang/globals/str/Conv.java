package lure.lang.globals.str;

import static lure.lang.globals.Str.nullStringWrap;

public class Conv extends lure.lang.Function {
  public Object call(Object arg1) {
    return nullStringWrap(arg1);
  }
}
