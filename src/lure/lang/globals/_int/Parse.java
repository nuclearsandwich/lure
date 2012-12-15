package lure.lang.globals._int;

public class Parse extends lure.lang.Function {
  public Object call(Object arg1) {
    return Integer.parseInt((String)arg1);
  }
}
