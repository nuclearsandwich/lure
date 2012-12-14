package forkbomb.intermediate.icodeimpl;

import wci.intermediate.ICodeNodeType;

/**
 * <h1>ICodeNodeType</h1>
 *
 * <p>Node types of the intermediate code parse tree.</p>
 *
 * <p>Copyright (c) 2012 by Steven! Ragnarök</p>
 */
public enum ICodeNodeTypeImpl implements ICodeNodeType {
  // Program structure
  SCRIPT, FUNCTION,

  // Statements
  ASSIGN, LOOP, TEST, CALL, IF, SELECT, SELECT_BRANCH, NO_OP,

  // Operands
  VARIABLE, INTEGER_CONSTANT, STRING_CONSTANT, NEWARRAY;
}
