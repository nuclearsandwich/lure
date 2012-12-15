package forkbomb.backend.bytemarks;

public interface Instructor {
  public void _goto(String label);
  public void _new(String classSpec);
  public void _return();
  public void aload(int i);
  public void aload_null();
  public void areturn();
  public void astore(int i);
  public void dup();
  public void end_method();
  public void finish();
  public void getstatic(String descriptor, String classSpec);
  public void ifeq(String label);
  public void ifne(String label);
  public void invokeinterface(String methodSpec, int arity);
  public void invokenonvirtual(String methodSpec);
  public void invokespecial(String methodSpec);
  public void invokestatic(String methodSpec);
  public void invokevirtual(String methodSpec);
  public void label(String label);
  public void ldc(String s);
  public void ldc(float f);
  public void ldc(int i);
  public void limit_locals(int i);
  public void limit_stack(int i);
  public void private_method(String methodSpec);
  public void public_method(String methodSpec);
  public void static_method(String methodSpec);
}
