package test;

import forkbomb.backend.bytemarks.JasminInstructor;

public class TestJasminInstructor {
  public static void main(String[] args) {
    JasminInstructor.setOutputDirectory(System.getProperty("user.dir"));
    JasminInstructor ji = new JasminInstructor("FristTest");
    ji.write("Hello world");
    ji.write("Genie");
    ji.finish();
  }
}
