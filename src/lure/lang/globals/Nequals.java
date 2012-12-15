package lure.lang.globals;

/* The plus function. In Lure +(). */
public class Nequals extends lure.lang.Function {
  public Object call(Object arg1, Object arg2) {
    return !arg1.equals(arg2);
  }
}
