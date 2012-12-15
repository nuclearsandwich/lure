package lure.lang.globals;

public class Equals extends lure.lang.Function {
  public Object call(Object arg1, Object arg2) {
    return arg1.equals(arg2);
  }
}
