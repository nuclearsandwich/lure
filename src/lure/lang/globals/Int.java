package lure.lang.globals;

import lure.lang.globals._int.*;

public class Int extends lure.lang.Record {
  public Int() {
    _fieldSet("parse", new Parse());
  }
}
