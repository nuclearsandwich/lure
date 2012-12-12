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

    return null;
  }

  public Object visit(ASTLoopExpression node, Object data) {
    return null;
  }

  public Object visit(ASTWhileExpression node, Object data) {
    return null;
  }

  public Object visit(ASTString node, Object data) {
    ICodeNode str = ICodeFactory.createICodeNode(STRING_CONSTANT);
    str.setAttribute(ICodeKeyImpl.VALUE, node.jjtGetValue());
    return str;
  }

  public Object visit(ASTAssignmentExpression node, Object data) {
    ICodeNode assign = ICodeFactory.createICodeNode(ASSIGN);
    SymTabEntry e = symbolTable.enterLocal((String)node.jjtGetValue());
    assign.setAttribute(ICodeKeyImpl.VALUE, e.getName());
    assign.setAttribute(ICodeKeyImpl.ID, e.getIndex());
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
    SymTabEntry e = symbolTable.enterLocal("__this__");
    Mercury.debug("Index of __this__: " + e.getIndex());
    // Tempted to reload predefineds here instead of nonlocal lookup.
    Object args = node.jjtGetChild(0).jjtAccept(this, null);
    for (int i = 1; i < node.jjtGetNumChildren(); i++) {
      function.addChild((ICodeNode)node.jjtGetChild(i).jjtAccept(this, null));
    }
    function.setAttribute(ICodeKeyImpl.VALUE, getNextFunctionClass());
    /* Restore symbol table */
    symbolTable.pop();
    return function;
  }

  public Object visit(ASTArgumentList node, Object data) {
    ArrayList<String> params = (ArrayList<String>)node.jjtGetValue();
    for (String p : params) {
      SymTabEntry e = symbolTable.enterLocal(p);
      Mercury.debug("Index of " + e.getName() + ": " + e.getIndex());
    }

    return null;
  }

  public Object visit(ASTFunctionInvocationExpression node, Object data) {
    ICodeNode fun = ICodeFactory.createICodeNode(CALL);
    SimpleNode funAccess = (SimpleNode)node.jjtGetChild(0);
    SymTabEntry e = symbolTable.lookupLocal((String)funAccess.jjtGetValue());

    if (e == null) {
      Mercury.fatal("No known value for identifier " + (String)funAccess.jjtGetValue());
    }

    if (e.getDefinition() == DefinitionImpl.BUILTIN_FUNCTION) {
      fun.setAttribute(ICodeKeyImpl.VALUE,
          e.getAttribute(SymTabKeyImpl.FUNCTION_SLUG));
    } else { //if (e.getDefinition() == DefinitionImpl.FUNCTION) {
      fun.setAttribute(ICodeKeyImpl.VALUE, "lure/lang/Function/call");
    }
    fun.setAttribute(ICodeKeyImpl.ID, e.getIndex());

    for (int i = 1; i < node.jjtGetNumChildren(); i++) {
      fun.addChild((ICodeNode)node.jjtGetChild(i).jjtAccept(this, null));
    }
    return fun;
  }

  public Object visit(ASTVariableAccess node, Object data) {
    ICodeNode access = ICodeFactory.createICodeNode(VARIABLE);
    SymTabEntry e = symbolTable.lookupLocal((String)node.jjtGetValue());
    access.setAttribute(ICodeKeyImpl.VALUE, e.getName());
    access.setAttribute(ICodeKeyImpl.ID, e.getIndex());
    return access;
  }

  private String getNextFunctionClass() {
    return "lure/userland/Function_" + globalFunctionCounter++;
  }
}
