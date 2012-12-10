package forkbomb.backend;

import forkbomb.backend.bytemarks.*;
import wci.intermediate.*;
import forkbomb.intermediate.icodeimpl.*;

public class LureCompiler {
  private ICodeNode rootNode, currentNode;
  private Instructor instructor;

  public void printICTree(ICodeNode root) {
    System.out.println(root.toString());
    for (ICodeNode n : root.getChildren()) {
      printICTree(n);
    }
  }

  public void compile(ICodeNode root) {
    instructor = new JasminInstructor((String)root.getAttribute(ICodeKeyImpl.VALUE));
    (new Generator(root)).generate();
  }

  private class Generator {
    private ICodeNode node;
    public Generator(ICodeNode node) {
      this.node = node;
    }

    public void generate() {
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
      }
    }

    private void generateScript() {
      instructor.static_method("main([Ljava/lang/String;)V");
      // TODO limit locals to symtab count;
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
      instructor.ldc((String)node.getAttribute(ICodeKeyImpl.VALUE));
    }

    private void generateAssign() {
      // lookup index in symbol table.
      // astore index, object
    }
  }
}
