package lure.lang.globals.str;

import static lure.lang.globals.Str.nullStringWrap;

public class Split extends lure.lang.Function {
  public Object call(Object base, Object regex) {
    lure.lang.List result = new lure.lang.List();
    java.util.ArrayList a = result.getArrayList();

    for (String s : ((String)base).split((String)regex)) {
      a.add(s);
    }
    return result;
  }
}
