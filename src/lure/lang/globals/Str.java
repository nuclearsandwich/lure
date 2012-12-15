package lure.lang.globals;

import java.util.ArrayList;
import lure.lang.globals.str.*;

public class Str extends lure.lang.Record {

  public Str() {
    _fieldSet("cat", new Cat());
    _fieldSet("conv", new Conv());
    _fieldSet("split", new Split());
  }

  public static String nullStringWrap(Object o) {
    if (o == null) {
      return "nil";
    } else {
      return o.toString();
    }
  }
}
