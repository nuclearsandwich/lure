package forkbomb.frontend;

import wci.intermediate.*;
import forkbomb.intermediate.SymTabFactory;
import forkbomb.intermediate.symtabimpl.*;
import forkbomb.intermediate.icodeimpl.*;
import forkbomb.intermediate.ICodeFactory;

/* Walks a Lure abstract syntax tree and transforms it into
 * an ICode representation. */
public class LureASTParser implements LureParserVisitor {

  private ICodeNode newRoot;
  private SymTabStack symbolTable;

  public ICodeNode parse(SimpleNode root) {
    symbolTable = SymTabFactory.createSymTabStack();
    root.jjtAccept(this, null);
    return newRoot;
  }

  public Object visit(SimpleNode node, Object data) {
    return null;
  }

  public Object visit(ASTScript node, Object data) {
    ICodeNode script = ICodeFactory.createICodeNode(ICodeNodeTypeImpl.SCRIPT);
    script.setAttribute(ICodeKeyImpl.VALUE, "LureMain");

    for (int i = 0; i < node.jjtGetNumChildren(); i++) {
      ICodeNode n = (ICodeNode)node.jjtGetChild(i).jjtAccept(this, null);
      script.addChild(n);
    }
    newRoot = script;

    return script;
  }

  public Object visit(ASTConditionalExpression node, Object data) {
    ICodeNode ifn = ICodeFactory.createICodeNode(ICodeNodeTypeImpl.IF);
    for (int i = 0; i < node.jjtGetNumChildren(); i++) {
      ifn.addChild((ICodeNode)node.jjtGetChild(i).jjtAccept(this, null));
    }

    return ifn;
  }

  public Object visit(ASTLoopExpression node, Object data) {
    return null;
  }

  public Object visit(ASTWhileExpression node, Object data) {
    return null;
  }

  public Object visit(ASTString node, Object data) {
    ICodeNode str = ICodeFactory.createICodeNode(ICodeNodeTypeImpl.STRING_CONSTANT);
    str.setAttribute(ICodeKeyImpl.VALUE, node.jjtGetValue());
    return str;
  }

  public Object visit(ASTAssignmentExpression node, Object data) {
    ICodeNode assign = ICodeFactory.createICodeNode(ICodeNodeTypeImpl.ASSIGN);
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
    ICodeNode i = ICodeFactory.createICodeNode(ICodeNodeTypeImpl.INTEGER_CONSTANT);
    i.setAttribute(ICodeKeyImpl.VALUE, Integer.parseInt(str));
    return i;
  }

  public Object visit(ASTFunctionLiteralExpression node, Object data) {
    return null;
  }

  public Object visit(ASTArgumentList node, Object data) {
    return null;
  }

  public Object visit(ASTFunctionInvocationExpression node, Object data) {
    return null;
  }

  public Object visit(ASTVariableAccess node, Object data) {
    ICodeNode access = ICodeFactory.createICodeNode(ICodeNodeTypeImpl.VARIABLE);
    SymTabEntry e = symbolTable.lookupLocal((String)node.jjtGetValue());
    access.setAttribute(ICodeKeyImpl.VALUE, e.getName());
    access.setAttribute(ICodeKeyImpl.ID, e.getIndex());
    return null;
  }
}
