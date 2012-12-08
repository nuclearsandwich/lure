package forkbomb.backend.bytemarks;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JasminInstructor implements Instructor {
  /* The name of the class being generated. */
  private String className;
  private String superClass = null;
  private FileWriter outputWriter;

  public JasminInstructor(String className) {
    this.className = className;
    try {
      outputWriter = new FileWriter(fileName());
    } catch (IOException e) {
      e.printStackTrace();
    }
    writeClassHeader();
  }

  public void _new(String classSpec) {
    write("new", classSpec);
  }

  public void _return() {
    write("return");
  }

  public void aload(int i) {
    write("aload", ((Integer)i).toString());
  }

  public void areturn() {
    write("areturn");
  }

  public void astore(int i) {
    write("astore", ((Integer)i).toString());
  }

  public void end_method() {
    write(".end method");
  }

  public void getstatic(String descriptor, String classSpec) {
    write("getstatic", descriptor, classSpec);
  }

  public void invokespecial(String methodSpec) {
    write("invokespecial", methodSpec);
  }

  public void invokestatic(String methodSpec) {
    write("invokestatic", methodSpec);
  }

  public void invokevirtual(String methodSpec) {
    write("invokevirtual", methodSpec);
  }

  public void ldc(String s) {
    write("ldc", "\"" + s + "\"");
  }

  public void ldc(float f) {
    write("ldc", ((Float)f).toString());
  }

  public void ldc(int i) {
    write("ldc", ((Integer)i).toString());
  }

  public void limit_locals(int i) {
    write(".limit locals", ((Integer)i).toString());
  }

  public void limit_stack(int i) {
    write(".limit stack", ((Integer)i).toString());
  }

  public void private_method(String methodSpec) {
    write(".method private", methodSpec);
  }

  public void public_method(String methodSpec) {
    write(".method public", methodSpec);
  }

  public void static_method(String methodSpec) {
    write(".method public static", methodSpec);
  }

  public void write(String instruction, String... args) {
    StringBuilder s = new StringBuilder();
    s.append(instruction);
    for (String str : args) {
      s.append(TAB);
      s.append(str);
    }
    s.append(EOL);
    try {
      outputWriter.write(s.toString());
    } catch (IOException e) { e.printStackTrace(); }
  }

  public void finish() {
    try {
      outputWriter.close();
    } catch (IOException e) { e.printStackTrace(); }
  }

  private String fileName() {
    return outputDirectory + File.separator + className + EXT;
  }

  private void writeClassHeader() {
    write(".class", "public " + className);
    if (superClass != null) {
      write(".super", superClass);
    } else {
      write(".super", "java/lang/Object");
    }
  }

  private static final String TAB = "\t";
  private static final String EXT = ".j";
  private static final String EOL = "\n";
  private static String outputDirectory = ".";

  public static void setOutputDirectory(String anOutputDirectory) {
    outputDirectory = anOutputDirectory;
  }
}
