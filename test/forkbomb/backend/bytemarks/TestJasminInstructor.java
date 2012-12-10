package test.forkbomb.backend.bytemarks;

import forkbomb.backend.bytemarks.JasminInstructor;

public class TestJasminInstructor {
  public static void main(String[] args) {
    JasminInstructor.setOutputDirectory(System.getProperty("user.dir"));
    JasminInstructor ji = new JasminInstructor("FristTest");
    ji.static_method("main([Ljava/lang/String;)V");
    ji.limit_stack(16);
    ji.getstatic("java/lang/System/out", "Ljava/io/PrintStream;");
    ji.ldc("Hello, World");
    ji.invokevirtual("java/io/PrintStream/println(Ljava/lang/String;)V");
    ji._return();
    ji.end_method();
    ji.finish();
  }
}
