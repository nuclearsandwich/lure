package forkbomb.frontend;

import java.util.ArrayList;

import wci.intermediate.*;
import forkbomb.intermediate.SymTabFactory;
import forkbomb.intermediate.symtabimpl.*;
import forkbomb.intermediate.icodeimpl.*;
import forkbomb.intermediate.ICodeFactory;
import forkbomb.util.Mercury;
import lure.LureConstants;

import static forkbomb.intermediate.icodeimpl.ICodeNodeTypeImpl.*;

/* Walks a Lure abstract syntax tree and transforms it into
 * an ICode representation. */
public class LureASTParser implements LureParserVisitor {

  private ICodeNode newRoot;
  private SymTabStack symbolTable;
  private int globalFunctionCounter;

  public ICodeNode parse(SimpleNode root) {
    globalFunctionCounter = 0;
    /* Nesting Level 0 is the Global scope */
    symbolTable = SymTabFactory.createSymTabStack();
    Predefined.initialize(symbolTable);
    root.jjtAccept(this, null);
    return newRoot;
  }

  public Object visit(SimpleNode node, Object data) {
    return null;
  }

  public Object visit(ASTScript node, Object data) {
    ICodeNode script = ICodeFactory.createICodeNode(SCRIPT);
    script.setAttribute(ICodeKeyImpl.VALUE, "LureMain");
    /* push the main program scope */
    symbolTable.push();

    for (int i = 0; i < node.jjtGetNumChildren(); i++) {
      ICodeNode n = (ICodeNode)node.jjtGetChild(i).jjtAccept(this, null);
      script.addChild(n);
    }
    newRoot = script;

    return script;
  }

  public Object visit(ASTConditionalExpression node, Object data) {
    ICodeNode ifn = ICodeFactory.createICodeNode(IF);
    for (int i = 0; i < node.jjtGetNumChildren(); i++) {
      ifn.addChild((ICodeNode)node.jjtGetChild(i).jjtAccept(this, null));
    }

    return ifn;
  }

  public Object visit(ASTWhileExpression node, Object data) {
    ICodeNode hwhile = ICodeFactory.createICodeNode(LOOP);
    for (int i = 0; i < node.jjtGetNumChildren(); i++) {
      ICodeNode n = (ICodeNode)node.jjtGetChild(i).jjtAccept(this, null);
      hwhile.addChild(n);
    }

    return hwhile;
  }

  public Object visit(ASTString node, Object data) {
    ICodeNode str = ICodeFactory.createICodeNode(STRING_CONSTANT);
    str.setAttribute(ICodeKeyImpl.VALUE, node.jjtGetValue());
    return str;
  }

  public Object visit(ASTAssignmentExpression node, Object data) {
    ICodeNode assign = ICodeFactory.createICodeNode(ASSIGN);
    String varName = (String)node.jjtGetValue();
    /* check if re-assignment */
    SymTabEntry e = symbolTable.lookupLocal(varName);
    if (e == null) {
      e = symbolTable.enterLocal(varName);
    }
    assign.setAttribute(ICodeKeyImpl.VALUE, e);

    for (int i = 0; i < node.jjtGetNumChildren(); i++) {
      assign.addChild((ICodeNode)node.jjtGetChild(i).jjtAccept(this, null));
    }
    return assign;
  }

  public Object visit(ASTNumber node, Object data) {
    String str = (String)node.jjtGetValue();
    ICodeNode i = ICodeFactory.createICodeNode(INTEGER_CONSTANT);
    i.setAttribute(ICodeKeyImpl.VALUE, Integer.parseInt(str));
    return i;
  }

  public Object visit(ASTFunctionLiteralExpression node, Object data) {
    ICodeNode function = ICodeFactory.createICodeNode(FUNCTION);
    symbolTable.push();
    /* Because call is nonstatic, we have a pesky this referenced on the stack.
     * Assign it to a special identifier that we can ignore.
     */
    SymTabEntry e = symbolTable.enterLocal("__recur__");

    // XXX Note arity somehow using typeimpl.
    Object args = node.jjtGetChild(0).jjtAccept(this, null);

    for (int i = 1; i < node.jjtGetNumChildren(); i++) {
      function.addChild((ICodeNode)node.jjtGetChild(i).jjtAccept(this, null));
    }

    function.setAttribute(ICodeKeyImpl.VALUE, getNextFunctionClass());
    /* Restore symbol table */
    SymTab s = symbolTable.pop();
    return function;
  }

  public Object visit(ASTArgumentList node, Object data) {
    ArrayList<String> params = (ArrayList<String>)node.jjtGetValue();
    for (String p : params) {
      SymTabEntry e = symbolTable.enterLocal(p);
    }

    return null;
  }

  public Object visit(ASTNewArray node, Object value) {
    return ICodeFactory.createICodeNode(NEWARRAY);
  }

  public Object visit(ASTFunctionInvocationExpression node, Object data) {
    ICodeNode fun = ICodeFactory.createICodeNode(CALL);
    SimpleNode funAccess = (SimpleNode)node.jjtGetChild(0);
    String funName = (String)funAccess.jjtGetValue();
    SymTabEntry e = symbolTable.lookupLocal(funName);

    if ((e == null) && ((e = symbolTable.lookupGlobal(funName)) == null)) {
      Mercury.fatal("No known value for identifier " + funName);
    }

    fun.setAttribute(ICodeKeyImpl.VALUE, e);

    for (int i = 1; i < node.jjtGetNumChildren(); i++) {
      fun.addChild((ICodeNode)node.jjtGetChild(i).jjtAccept(this, null));
    }
    return fun;
  }

  public Object visit(ASTVariableAccess node, Object data) {
    ICodeNode access = ICodeFactory.createICodeNode(VARIABLE);
    String varName = (String)node.jjtGetValue();
    SymTabEntry e = symbolTable.lookupLocal(varName);
    if ((e == null) && ((e = symbolTable.lookupGlobal(varName)) == null)) {
      Mercury.fatal("No known value for identifier " + varName);
    }
    access.setAttribute(ICodeKeyImpl.VALUE, e);
    return access;
  }

  private String getNextFunctionClass() {
    return "lure/userland/Function_" + globalFunctionCounter++;
  }
}
