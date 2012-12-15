package lure.lang;

import java.util.ArrayList;

import lure.lang.globals.*;
/**
 * <h1>Globals</h1>
 * <p>The Lure functions that are defined everywhere.</p>
 * <p>Copyright (c) 2012 Steven! Ragnarök</p>
 */
public class Globals {
  public static final Object gt = new Gt();
  public static final Object gte = new Gte();
  public static final Object lt = new Lt();
  public static final Object lte = new Lte();
  public static final Object minus = new Minus();
  public static final Object plus = new Plus();
  public static final Object puts = new Puts();
  public static final Object slash = new Slash();
  public static final Object star = new Star();
  public static final Object equals = new Equals();
  public static final Object nequals = new Nequals();
  public static final Object _true = Boolean.TRUE;
  public static final Object _false = Boolean.FALSE;
  public static final Object _null = null;
  public static final Object str = new Str();
  public static final Object _int = new Int();

  /** This function isn't defined in Lure, it's used by the code generator as
   * the truthiness test. Any value not null or false is truthy and tests
   * successful.
   * @param arg the result of the tested expression.
   * @return 0 if the argument is truthy and 1 if falsy.
   */
  public static int test(Object arg) {
    if (arg == null || arg == Boolean.FALSE) {
      return 1;
    } else {
      return 0;
    }
  }
}
