package forkbomb.backend;

import forkbomb.backend.bytemarks.*;
import wci.intermediate.*;
import forkbomb.intermediate.icodeimpl.*;
import forkbomb.intermediate.symtabimpl.*;
import forkbomb.util.Mercury;
import lure.LureConstants;

import static forkbomb.intermediate.icodeimpl.ICodeKeyImpl.*;

public class LureCompiler {
  private ICodeNode rootNode, currentNode;
  private Instructor instructor;

  public void printICTree(ICodeNode root) {
    for (ICodeNode n : root.getChildren()) {
      printICTree(n);
    }
  }

  public void compile(ICodeNode root) {
    if (System.getenv("CLASSPATH") != null) {
      JasminInstructor.setOutputDirectory(System.getenv("CLASSPATH"));
    }
    instructor = new JasminInstructor((String)root.getAttribute(VALUE));

    (new Generator(root)).generate();
  }

  private class Generator {
    private ICodeNode node;
    public Generator(ICodeNode node) {
      this.node = node;
    }

    public void generate() {
      Mercury.debug("Generating " + node.toString());
      switch((ICodeNodeTypeImpl)node.getType()) {
        case SCRIPT:
          generateScript();
          return;
        case ASSIGN:
          generateAssign();
          return;
        case STRING_CONSTANT:
          generateStringConstant();
          return;
        case INTEGER_CONSTANT:
          generateIntegerConstant();
          return;
        case VARIABLE:
          generateVariable();
          return;
        case CALL:
          generateCall();
          return;
        case FUNCTION:
          generateFunction();
          return;
        case IF:
          generateIf();
          return;
      }
    }

    private void generateScript() {
      instructor.static_method("main([Ljava/lang/String;)V");
      // TODO limit locals to symtab count;
      instructor.limit_stack(32);
      instructor.limit_locals(32);
      for (ICodeNode n : node.getChildren()) {
        Generator g = new Generator(n);
        g.generate();
      }
      instructor._return();
      instructor.end_method();
      instructor.finish();
    }

    private void generateStringConstant() {
      instructor.ldc((String)node.getAttribute(VALUE));
    }

    private void generateIntegerConstant() {
      instructor.ldc((Integer)node.getAttribute(VALUE));
      instructor.invokestatic("java/lang/Integer/valueOf(I)Ljava/lang/Integer;");
    }

    private void generateAssign() {
      if (node.getChildren().size() > 1) {
        Mercury.warn("Multiple children of ASSIGN");
      }

      SymTabEntry e = (SymTabEntry)node.getAttribute(VALUE);

      /* Generate bytecode for the expression to be assigned. */
      (new Generator(node.getChildren().get(0))).generate();
      instructor.astore(e.getIndex());
    }

    private void generateVariable() {
      SymTabEntry e = (SymTabEntry)node.getAttribute(VALUE);

      if (e.isGlobal()) {
        Mercury.warn("Globals not yet supported, loading null");
        instructor.aload_null();
      } else {
        instructor.aload(e.getIndex());
      }
    }

    private void generateCall() {
      SymTabEntry e = (SymTabEntry)node.getAttribute(VALUE);
      int arity = node.getChildren().size();
      /* Check if user defined or global and invoke virtual or static
       * accordingly.
       */
      if (e.isGlobal()) {
        for (ICodeNode arg : node.getChildren()) {
          (new Generator(arg)).generate();
        }
        instructor.invokestatic(
          methodSignature((String)e.getAttribute(SymTabKeyImpl.FUNCTION_SLUG), arity));
      } else {
        instructor.aload(e.getIndex());
        for (ICodeNode arg : node.getChildren()) {
          (new Generator(arg)).generate();
        }
        instructor.invokevirtual(
            methodSignature(LureConstants.FUNCTION_CALL_SLUG, arity));
      }
    }

    private void generateFunction() {
      /* Set up separate instructor for Function sublass. */
      Instructor mainInstructor = instructor;
      String className = (String)node.getAttribute(VALUE);
      instructor = new JasminInstructor(className, LureConstants.FUNCTION_CLASS);
      instructor.public_method("<init>()V");
      instructor.aload(0);
      instructor.invokenonvirtual(LureConstants.FUNCTION_INIT);
      instructor._return();
      instructor.end_method();
      // XXX LOLARITYCHECKINGWAHT?
      instructor.public_method(
          methodSignature(LureConstants.FUNCTION_METHOD_NAME, 1));
      instructor.limit_stack(32);
      instructor.limit_locals(32);
      for (ICodeNode n : node.getChildren()) {
        (new Generator(n)).generate();
      }

      instructor.areturn();
      instructor.end_method();
      instructor.finish();
      /* Restore original instructor. */
      instructor = mainInstructor;
      instructor._new(className);
      instructor.dup();
      instructor.invokespecial(className + "/<init>()V");
    }

    public void generateIf() {
      if (node.getChildren().size() > 3) {
        Mercury.fatal("Dude, Conditional has too many kids.");
      }
      /* Code to put test expression on the stack */
      /* Invoke test method (returns 0 or 1) */
      /* ifeq jump to truthy expression */
      /* otherwise fall to falsy expression, then jump over truthy */
    }

    /* Helpers */
    private String methodSignature(String slug, int arity) {
      StringBuilder s = new StringBuilder();
      s.append(slug);
      s.append("(");
      for (int i = 0; i < arity; i++) {
        s.append("Ljava/lang/Object;");
      }
      s.append(")Ljava/lang/Object;");
      return s.toString();
    }
  }
}
