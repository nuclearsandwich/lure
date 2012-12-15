package lure.lang.globals;
import java.util.ArrayList;

/* The plus function. In Lure +(). */
public class Plus extends lure.lang.Function {

  /* Lure's +(listOfNumbers) */
  public Object call(ArrayList args) {
    int total = 0;
    for (Object i : args) {
      total += ((Integer)i);
    }
    return total;
  }

  /* +(i, j) in Lure. */
  public Object call(Object arg1, Object arg2) {
    return ((Integer) arg1) + ((Integer) arg2);
  }

  /* +(i, j, k) in Lure. */
  public Object call(Object arg1, Object arg2, Object arg3) {
    return ((Integer) arg1) + ((Integer) arg2) + ((Integer) arg3);
  }

  /* you get the idea... */
  public Object call(Object arg1, Object arg2, Object arg3,
      Object arg4)
  {
    return ((Integer) arg1) + ((Integer) arg2) + ((Integer) arg3) +
      ((Integer) arg4);
  }
  public Object call(Object arg1, Object arg2, Object arg3,
      Object arg4, Object arg5)
  {
    return ((Integer) arg1) + ((Integer) arg2) + ((Integer) arg3) +
      ((Integer) arg4) + ((Integer) arg5);
  }
}
