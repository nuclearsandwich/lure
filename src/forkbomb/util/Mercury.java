package forkbomb.util;

public class Mercury {

  public enum Level {
    FATAL, WARN, INFO, DEBUG;
  }

  public static void fatal(String message) {
    errOut("FATAL: ", message);
    System.exit(1);
  }

  public static void warn(String message) {
    if (level.ordinal() >= Level.WARN.ordinal()) {
      errOut("WARN: ", message);
    }
  }

  public static void info(String message) {
    if (level.ordinal() >= Level.INFO.ordinal()) {
      errOut("INFO: ", message);
    }
  }

  public static void debug(String message) {
    if (level.ordinal() >= Level.DEBUG.ordinal()) {
      errOut("DEBUG: ", message);
    }
  }

  public static void setLevel(Level aLevel) {
    level = aLevel;
  }

  public static Level getLevel() {
    return level;
  }

  private static void errOut(String prefix, String message) {
    System.err.println(prefix + message);
  }

  private static Level level = Level.WARN;
}
