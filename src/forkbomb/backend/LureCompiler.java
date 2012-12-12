package forkbomb.backend;

import forkbomb.backend.bytemarks.*;
import wci.intermediate.*;
import forkbomb.intermediate.icodeimpl.*;
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
      Mercury.debug("Generating " + node.toString() + " " +
          node.getAttribute(VALUE).toString());
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

      int index = (Integer)node.getAttribute(ID);
      /* Generate bytecode for the expression to be assigned. */
      (new Generator(node.getChildren().get(0))).generate();
      instructor.astore(index);
    }

    private void generateVariable() {
      instructor.aload((Integer)node.getAttribute(ID));
    }

    private void generateCall() {
      String slug = (String)node.getAttribute(VALUE);
      int arity = node.getChildren().size();
      /* Check if user defined or builtin and invoke virtual or static
       * accordingly.
       */
      if (slug.equals("lure/lang/Function/call")) {
        instructor.aload((Integer)node.getAttribute(ID));
        for (ICodeNode arg : node.getChildren()) {
          (new Generator(arg)).generate();
        }
        instructor.invokevirtual(methodSignature(slug, arity));
      } else {
        for (ICodeNode arg : node.getChildren()) {
          (new Generator(arg)).generate();
        }
        instructor.invokestatic(methodSignature(slug, arity));
      }
    }

    private void generateFunction() {
      /* Set up separate instructor for Function sublass. */
      Instructor mainInstructor = instructor;
      String className = (String)node.getAttribute(VALUE);
      instructor = new JasminInstructor(className, "lure/lang/Function");
      instructor.public_method("<init>()V");
      instructor.aload(0);
      instructor.invokenonvirtual("lure/lang/Function/<init>()V");
      instructor._return();
      instructor.end_method();
      instructor.public_method(methodSignature("call", 1));
      /* for the length of the call method, local0 is `this` ignore it. */
      instructor.limit_stack(32);
      instructor.limit_locals(32);
      for (ICodeNode n : node.getChildren()) {
        (new Generator(n)).generate();
      }

      /* Restore original instructor. */
      instructor.areturn();
      instructor.end_method();
      instructor.finish();
      instructor = mainInstructor;
      /* XXX Leave reference to class on stack. */
      instructor._new(className);
      instructor.dup();
      instructor.invokespecial(className + "/<init>()V");
      /* pop symbol table */
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
