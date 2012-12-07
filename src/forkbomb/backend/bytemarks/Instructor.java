package forkbomb.backend.bytemarks;

public interface Instructor {
  public void aload(int i);

  public void astore(int i);

  public void areturn();

  public void _new(String classSpec);

  public void invokestatic(String methodSpec);

  public void invokevirtual(String methodSpec);

  public void invokespecial(String methodSpec);

  public void public_method(String methodSpec);

  public void private_method(String methodSpec);

  public void static_method(String methodSpec);

  public void end_method();
}
