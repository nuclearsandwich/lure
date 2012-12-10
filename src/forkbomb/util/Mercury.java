package forkbomb.util;

public class Mercury {

  public static void fatal(String message) {
    errOut("FATAL: ", message);
    System.exit(1);
  }

  public static void warn(String message) {
    errOut("WARN: ", message);
  }

  public static void info(String message) {
    errOut("INFO: ", message);
  }

  public static void debug(String message) {
    errOut("DEBUB: ", message);
  }

  private static void errOut(String prefix, String message) {
    System.err.println(prefix + message);
  }
}
