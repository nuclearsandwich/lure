package lure.lang.globals;

public class Puts extends lure.lang.Function {
  public Object call(Object arg1) {
    System.out.println(arg1);
    return null;
  }

  public Object call(Object arg1, Object arg2) {
    System.out.println(arg1.toString() + " " + arg2.toString());
    return null;
  }

  public Object call(Object arg1, Object arg2, Object arg3) {
    System.out.println(arg1.toString() + " " + arg2.toString() + " " +
        arg3.toString());
    return null;
  }

  public Object call(Object arg1, Object arg2, Object arg3, Object arg4) {
    System.out.println(arg1.toString() + " " + arg2.toString() + " " +
        arg3.toString() + " " + arg4.toString());
    return null;
  }

  public Object call(Object arg1, Object arg2, Object arg3, Object arg4,
      Object arg5)
  {
    System.out.println(arg1.toString() + " " + arg2.toString() + " " +
        arg3.toString() + " " + arg4.toString() + " " + arg5.toString());
    return null;
  }
}
